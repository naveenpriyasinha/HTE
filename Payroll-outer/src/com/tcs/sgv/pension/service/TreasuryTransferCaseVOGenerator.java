package com.tcs.sgv.pension.service;

import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * Treasury Transfer Case Send / Receive VO Generator.
 * 
 * @author Sagar Patel
 * @version 1.0
 */
public class TreasuryTransferCaseVOGenerator extends ServiceImpl implements VOGeneratorService
{
    /* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for ResourceBundle */
    private static ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    
    /**
     * Generates VO for insertion of TA Bill Data
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject generateMap(Map lMapInput)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
        
        TrnPensionRqstHdr lobjPensionRqstHdr = null;
        
        String lStrNewTreasury = null;
    	String lStrNewSubTreasury = null;
    	String lStrNewTrsryLocId = null;
        String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
                
        try
        { 
        	String lStrPPONO = StringUtility.getParameter("txttfrPPONO", request).trim();
        	String lStrNewState = StringUtility.getParameter("cmblstNewState", request).trim();
        	String lStrSaveFlg = StringUtility.getParameter("hidReceiveFlg", request).trim();
        	
        	lobjPensionRqstHdr = lObjCommonPensionDAO.getTrnPensionRqstHdrDtls(lStrPPONO,lStrStatus);
       
        	if(lStrSaveFlg != null && lStrSaveFlg.equalsIgnoreCase("ReceiveCase"))
	        {
                String[] lStrPPONOAry = StringUtility.getParameterValues("hidPPONO", request);
                String[] lStrACNoAry = StringUtility.getParameterValues("txtAccountNo", request);
                String[] lStrBankAry = StringUtility.getParameterValues("cmblstBank", request);
                String[] lStrBranchAry = StringUtility.getParameterValues("cmblstBranch", request);
                
                lMapInput.put("TrsryPPONOAry", lStrPPONOAry);
                lMapInput.put("NewACNoAry", lStrACNoAry);
                lMapInput.put("NewBankAry", lStrBankAry);
                lMapInput.put("NewBranchAry", lStrBranchAry);
	        }
        	
        	if(lStrNewState != null && lStrNewState.equalsIgnoreCase("1"))
	        {	
	        	lStrNewTreasury = StringUtility.getParameter("cmblstNewTrsry", request).trim();
	        	lStrNewSubTreasury = StringUtility.getParameter("cmblstNewSubTrsry", request).trim();
	        	
	        	if(lStrNewSubTreasury != null && !lStrNewSubTreasury.equalsIgnoreCase("-1"))
	        	{
	        		lStrNewTrsryLocId = lStrNewSubTreasury;
	        		
	        	}else {
	        		if(lStrNewTreasury != null && !lStrNewTreasury.equalsIgnoreCase("-1"))
	        		{
		        		lStrNewTrsryLocId = lStrNewTreasury;
		        	}
	        	}
	        	
	        }
	        else
	        {
	        	lStrNewTreasury = StringUtility.getParameter("txtlstNewTrsry", request).trim();
	        	lStrNewSubTreasury = StringUtility.getParameter("txtlstNewSubTrsry", request).trim();
	        }
        	
	        if(lStrSaveFlg != null && lStrSaveFlg.equalsIgnoreCase("ReceiveCase")){
        		
	        	lStrSaveFlg = "SaveReceiveCase";
        	} else {
        		lStrSaveFlg = "SaveSendCase";
        	}
	        lMapInput.put("TrsryPPONO", lStrPPONO);
	        lMapInput.put("SaveFlage", lStrSaveFlg);
	        lMapInput.put("NewState", lStrNewState);
	        lMapInput.put("NewTreasuryCode", lStrNewTrsryLocId);
            lMapInput.put("TrnPensionRqstHdrVO" , lobjPensionRqstHdr);            
          
            objRes.setResultValue(lMapInput);
        }
        catch(Exception e)
        {   
            gLogger.error("Error is : ", e);
            objRes.setResultValue(null);
            objRes.setThrowable(e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");           
        }
        return objRes;
    }
    
}