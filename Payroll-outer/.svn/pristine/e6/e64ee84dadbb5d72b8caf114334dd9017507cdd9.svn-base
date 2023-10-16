package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAO;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAO;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class MRBillVOGenerator extends ServiceImpl implements VOGeneratorService
{
	/* Global Variable for PostId */
	private Long gLngPostId = null;

	/* Global Variable for UserId */
	private Long gLngUserId = null;

    /* Global Variable for Current Date */
	private Date gDtCurrDt = null;

    /* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());   
    
    /**
     * Generates VO for insertion of MR Bill Data 
     * @param Map:objArgs
     * @return ResultObject
     */    
    public ResultObject generateMap(Map objArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objArgs.get("requestObj");
          
        try
        {	
        	setSessionInfo(objArgs);
        	
    		TrnPensionBillHdr lObjPensionBillHdr = generateTrnPensionBillHdrVO(objArgs);
    		objArgs.put("TrnPensionBillHdrId", lObjPensionBillHdr.getTrnPensionBillHdrId());
    		
    		List<TrnPensionBillDtls> lLstPensionBillDtls = generateTrnPensionBillDtls(objArgs);
            TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
                                        	
        	lObjTrnPensionMedRembrsmnt = generateTrnPensionMedRembrsmnt(objArgs);  
        	
    		objArgs.put("MRTrnPensionBillHdrVO", lObjPensionBillHdr);
    		objArgs.put("MRTrnPensionBillDtlsVO", lLstPensionBillDtls);
    		
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
    
    private TrnPensionBillHdr generateTrnPensionBillHdrVO(Map lMapInput) throws Exception
    {
    	HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        TrnPensionBillHdr lObjPensionBillHdr =new TrnPensionBillHdr();          
        String lStrTokenNo = null;
        
        try
        { 
    		String lStrBillType="MR";
    		
     	    lObjPensionBillHdr.setTrnPensionBillHdrId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", lMapInput)));
            //lObjPensionBillHdr.setBillType(lStrBillType);

            lObjPensionBillHdr.setBillDate(gDtCurrDt);
            lObjPensionBillHdr.setCreatedUserId(new BigDecimal(gLngUserId));
            lObjPensionBillHdr.setCreatedPostId(new BigDecimal(gLngPostId));
            lObjPensionBillHdr.setCreatedDate(gDtCurrDt);
           	if(StringUtility.getParameter("hidBankCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setBankCode(StringUtility.getParameter("hidBankCode", request).trim());
        	}
           	if(StringUtility.getParameter("hidBranchCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setBranchCode(StringUtility.getParameter("hidBranchCode", request).trim());
        	}
        	if(StringUtility.getParameter("hidHeadCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setHeadCode(new BigDecimal(StringUtility.getParameter("hidHeadCode", request).trim()));
        	}
        	
        	lObjPensionBillHdr.setForMonth(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(gDtCurrDt)));
        }
        catch (Exception e)
        {
        	gLogger.error("Error is : " + e,e); 
        	throw(e);
        }
        return lObjPensionBillHdr;
    }

    private List<TrnPensionBillDtls> generateTrnPensionBillDtls(Map lMapInput) throws Exception
    {
        List<TrnPensionBillDtls> lLstTrnPensionBillDtls = new ArrayList<TrnPensionBillDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants"); 
        MstPensionerDtlsDAOImpl mstPnsnrDtlsDAOimpl = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class, servLoc
                .getSessionFactory());
        
        String[] lStrPPONo = null;
        String[] lStrPnsnrName = null;
        String lStrAccNo = null;
        String[] lStrNetAmt = null;
                
        TrnPensionBillDtls lObjPensionBillDtls = null;
        
        String lTrnPensionBillHdrId = lMapInput.get("TrnPensionBillHdrId").toString();
        String lStrStatus = "APPROVED";
        
        try
        {
        	List lLstPensionCodeId=null; 
        	
        	lStrPPONo = StringUtility.getParameterValues("hidppoNo", request);
        	lStrPnsnrName = StringUtility.getParameterValues("hidpensionerName", request);
        	lStrNetAmt = StringUtility.getParameterValues("hidgrantAmount", request); 
        	
            String lStrPnsnrCode = null;
            Object[] lObjData = null;
            for (Integer x = 0; x < (lStrPPONo.length) ; x++)
            {
            	lObjPensionBillDtls = new TrnPensionBillDtls();
            	
            	TrnPensionRqstHdrDAO lObjPensionBillDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, servLoc.getSessionFactory());
            	lLstPensionCodeId = lObjPensionBillDAO.getPksForTrnPensionRqstHdr(lStrStatus,lStrPPONo[x].trim());
 	            
 	            if(lLstPensionCodeId != null && !lLstPensionCodeId.isEmpty())
 	            {
 	            	lObjData = (Object[]) lLstPensionCodeId.get(0);	            	
 	            	lStrPnsnrCode = lObjData[1].toString();	  
 	            	lStrAccNo = mstPnsnrDtlsDAOimpl.getACCNo(lStrPnsnrCode);
 	            }
            	
 	           //lObjPensionBillDtls.setPensionRequestId(new Long(lLngPensionReqId));
 	           lObjPensionBillDtls.setPpoNo(lStrPPONo[x].trim());
 	           lObjPensionBillDtls.setPensionerCode(lStrPnsnrCode);
 	           lObjPensionBillDtls.setReducedPension(new BigDecimal(lStrNetAmt[x].trim()));
 	           lObjPensionBillDtls.setAccountNo(lStrAccNo.trim());
 	           lObjPensionBillDtls.setTrnPensionBillHdrId(new Long(lTrnPensionBillHdrId));
 	           lObjPensionBillDtls.setPensionerName(lStrPnsnrName[x].trim());
               lObjPensionBillDtls.setTrnCounter(1);
               
	           lLstTrnPensionBillDtls.add(lObjPensionBillDtls);       	
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            gLogger.error(" Error is : " + e, e);
            throw(e);
        }
        return lLstTrnPensionBillDtls;
    }
    private TrnPensionMedRembrsmnt generateTrnPensionMedRembrsmnt(Map lMapInput) throws Exception
    {
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
    	TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,servLoc.getSessionFactory());

        String lTrnPensionBillHdrId = lMapInput.get("TrnPensionBillHdrId").toString();
        Long lLngPk = 0L;
        try
        {
        	 String[] lStrPKTrnMedRembrsmnt = StringUtility.getParameterValues("hidMedRemId", request);
        	for(int i=0;i<lStrPKTrnMedRembrsmnt.length;i++)
        	{
        		String lStrLngPk = lStrPKTrnMedRembrsmnt[i].trim();
        		lLngPk = Long.valueOf(lStrLngPk);
        		lObjTrnPensionMedRembrsmnt = lObjTrnPensionMedRembrsmntDAO.read(lLngPk);
        		lObjTrnPensionMedRembrsmnt.setBillHdrId(Long.parseLong(lTrnPensionBillHdrId));
        	}
        	
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lObjTrnPensionMedRembrsmnt;
    }
    
    private void setSessionInfo(Map inputMap)
    {
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
}
