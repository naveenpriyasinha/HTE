package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAO;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
/**
 * CVP Bill VO Generator.
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */

public class CVPBillVOGenerator extends ServiceImpl implements VOGeneratorService
{
	/* Global Variable for PostId */
	private Long gLngPostId = null;

	/* Global Variable for UserId */
	private Long gLngUserId = null;

    /* Global Variable for Current Date */
	private Date gDtCurrDt = null;

    /* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());   
    
	private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");  
    
    /**
     * Generates VO for insertion of CVP Bill Data 
     * @param Map:objArgs
     * @return ResultObject
     */    
    public ResultObject generateMap(Map objArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objArgs.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) objArgs.get("serviceLocator");
        List lLstRltPensioncaseBillId=null;
        Long lLngRltPensioncaseBillId=null;
        String lStrBillType=bundleConst.getString("RECOVERY.CVP");	        
        String lStrStatus=bundleConst.getString("STATUS.CRTD");
        Object lObjDataVal = null;	 
        
        RltPensioncaseBillDAO lObjRltPensioncaseBillDAO = new RltPensioncaseBillDAOImpl(RltPensioncaseBill.class,servLoc.getSessionFactory());
    	        
        try
        {	
        	setSessionInfo(objArgs);
            String lStrPPONo = StringUtility.getParameter("txtPPONo", request).trim();
            String lStrPnsnrCode = StringUtility.getParameter("hidDPnsnrCode", request).trim();
            objArgs.put("lStrPnsnrCode", lStrPnsnrCode);
        	if(lStrPPONo != null)
        	{
        		TrnPensionBillHdr lObjPensionBillHdr = generateTrnPensionBillHdrVO(objArgs);
        		TrnPensionBillDtls lObjPensionBillDtls = generateTrnPensionBillDtlsVO(objArgs);
        		objArgs.put("CVPTrnPensionBillHdrVO", lObjPensionBillHdr);
        		objArgs.put("CVPTrnPensionBillDtlsVO", lObjPensionBillDtls);
        		lLstRltPensioncaseBillId = lObjRltPensioncaseBillDAO.getPKForRltPensioncaseBill(lStrPPONo,lStrBillType,lStrStatus);
    	        if(lLstRltPensioncaseBillId != null && lLstRltPensioncaseBillId.size() > 0)
    	        {
    	        	lObjDataVal = (Object) lLstRltPensioncaseBillId.get(0);    	        	
    	        	lLngRltPensioncaseBillId = new Long(lObjDataVal.toString());
    	        }
        		objArgs.put("CVPRltPensioncaseBillId", lLngRltPensioncaseBillId);
        	}	        
	        objRes.setResultValue(objArgs);	       
        }
        catch (Exception e)
        {
        	gLogger.error(" Error is : " + e, e);
        	objRes.setResultValue(null);
        	objRes.setThrowable(e);
        	objRes.setResultCode(ErrorConstants.ERROR);
        	objRes.setViewName("errorPage");
        } 
        return objRes;
    }
    
    /**
     * Generate TrnPensionBillHdrVO from JSP Data
     * @param Map:lMapInput
     * @return TrnPensionBillHdrVO
     */
    private TrnPensionBillHdr generateTrnPensionBillHdrVO(Map lMapInput) throws Exception
    {
    	HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        TrnPensionBillHdr lObjPensionBillHdr =new TrnPensionBillHdr();    
        try
        { 
            lObjPensionBillHdr.setBillDate(gDtCurrDt);
            lObjPensionBillHdr.setForMonth(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(gDtCurrDt)));
            lObjPensionBillHdr.setCreatedUserId(new BigDecimal(gLngUserId));
            lObjPensionBillHdr.setCreatedPostId(new BigDecimal(gLngPostId));
            lObjPensionBillHdr.setCreatedDate(gDtCurrDt);
            lObjPensionBillHdr.setTrnCounter(1);
            if(StringUtility.getParameter("hidHeadcode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setHeadCode(new BigDecimal(StringUtility.getParameter("hidHeadcode", request).trim()));
        	}
        	if(StringUtility.getParameter("hidScheme", request).trim() != "")
        	{
        		lObjPensionBillHdr.setScheme(StringUtility.getParameter("hidScheme", request).trim());
        	}
        }
        catch (Exception e)
        {
        	gLogger.error("Error is : " + e,e); 
        	throw(e);
        }
        return lObjPensionBillHdr;
    }    
    
    /**
     * Generate TrnPensionBillDtlsVO from JSP Data
     * @param Map:lMapInput
     * @return TrnPensionBillDtlsVO
     */
    private TrnPensionBillDtls generateTrnPensionBillDtlsVO(Map lMapInput) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");     
        TrnPensionBillDtls lObjPensionBillDtls =new TrnPensionBillDtls(); 
        List lLstPensionCodeId=null; 
        String lStrPPONo = StringUtility.getParameter("txtPPONo", request).trim();
        String lStrPensionerCode="";
        String lStrRecoveryAmt = StringUtility.getParameter("txtRecoveryAmt", request).trim();
        String lStrNetAmt = StringUtility.getParameter("txtNetAmt", request).trim();
        String lStrStatus=bundleConst.getString("CMN.NEW");
        Object[] lObjData = null;
        TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
        try
        {  
        	if(lStrPPONo != null)
        	{	        	
        		lLstPensionCodeId = lObjTrnPensionRqstHdrDAO.getPksForTrnPensionRqstHdr(lStrStatus, lStrPPONo);
        		 if(lLstPensionCodeId.size() <= 0)
 	            {
 	            	lLstPensionCodeId = lObjTrnPensionRqstHdrDAO.getPksForTrnPensionRqstHdr("APPROVED", lStrPPONo);
 	            }
	            if(lLstPensionCodeId != null && lLstPensionCodeId.size() > 0)
	            {
	            	lObjData = (Object[]) lLstPensionCodeId.get(0);
	            	lStrPensionerCode = lObjData[1].toString();	        	
	            }
	            lObjPensionBillDtls.setReducedPension(new BigDecimal(lStrNetAmt));
	            lObjPensionBillDtls.setPpoNo(lStrPPONo);
		        lObjPensionBillDtls.setPensionerCode(lStrPensionerCode);
		        lObjPensionBillDtls.setRecoveryAmount(new BigDecimal(lStrRecoveryAmt));	
                lObjPensionBillDtls.setTrnCounter(1);
        	}
        }
        catch (Exception e)
        {
        	gLogger.error("Error is : " + e,e); 
        	throw(e);
        }
        return lObjPensionBillDtls;
    }
    
    private void setSessionInfo(Map inputMap)
    {
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
}
