package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAO;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * Medical Reimbursement VO Generator
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public class MedReimburseVOGenerator extends ServiceImpl implements VOGeneratorService
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
            String lStrPPONo = StringUtility.getParameter("txtPPONo", request).trim();
        	if(lStrPPONo != null)
        	{
        		TrnPensionBillHdr lObjPensionBillHdr = generateTrnPensionBillHdrVO(objArgs);
        		TrnPensionBillDtls lObjPensionBillDtls = generateTrnPensionBillDtlsVO(objArgs);
                TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
                                            	
            	lObjTrnPensionMedRembrsmnt = generateTrnPensionMedRembrsmnt(objArgs);  
            	
        		objArgs.put("MRTrnPensionBillHdrVO", lObjPensionBillHdr);
        		objArgs.put("MRTrnPensionBillDtlsVO", lObjPensionBillDtls);
        		objArgs.put("lObjTrnPensionMedRembrsmnt", lObjTrnPensionMedRembrsmnt);
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
        	/*if(StringUtility.getParameter("hidBillHdrId", request) != null)
        	{
	        	lStrPKTrnBillHdr = StringUtility.getParameter("hidBillHdrId", request);
	        	lTrnPensionBillHdr = lObjTrnPensionBillHdrDAO.getUniqueTrnPensionBillHdr(Long.parseLong(lStrPKTrnBillHdr));
	        	
	            String bankCode = lTrnPensionBillHdr.getBankCode();
	            String branchCode = lTrnPensionBillHdr.getBranchCode();
	            BigDecimal headCode = lTrnPensionBillHdr.getHeadCode();
        	}*/
        	
            lObjPensionBillHdr.setBillDate(gDtCurrDt);
            lObjPensionBillHdr.setCreatedUserId(new BigDecimal(gLngUserId));
            lObjPensionBillHdr.setCreatedPostId(new BigDecimal(gLngPostId));
            lObjPensionBillHdr.setCreatedDate(gDtCurrDt);
        	if(StringUtility.getParameter("txtScheme", request).trim() != "")
        	{
        		lObjPensionBillHdr.setScheme(StringUtility.getParameter("txtScheme", request).trim());
        	}
        	if(StringUtility.getParameter("hidBankCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setBankCode(StringUtility.getParameter("hidBankCode", request).trim());
        	}
        	/*else
        	{
        		lObjPensionBillHdr.setBankCode(bankCode);
        	}*/
        	if(StringUtility.getParameter("hidBranchCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setBranchCode(StringUtility.getParameter("hidBranchCode", request).trim());
        	}
        	/*else
        	{
        		lObjPensionBillHdr.setBranchCode(branchCode);
        	}*/
        	if(StringUtility.getParameter("hidHeadCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setHeadCode(new BigDecimal(StringUtility.getParameter("hidHeadCode", request).trim()));
        	}
        	/*else
        	{
        		lObjPensionBillHdr.setHeadCode(headCode);
        	}*/
        	
        	lObjPensionBillHdr.setForMonth(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(gDtCurrDt)));
        }
        catch (Exception e)
        {
        	gLogger.error("Error is : " + e,e); 
        	throw(e);
        }
        return lObjPensionBillHdr;
    }
  
    private TrnPensionMedRembrsmnt generateTrnPensionMedRembrsmnt(Map lMapInput) throws Exception
    {
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        
        String lStrFrmmon = "";
        String lStrFrmyear = "";
        String lStrTomon = "";
        String lStrToyear = "";
        
        String FromMonth = null;
        String ToMonth = null;
        
        String lStrPPoNo = null;
        String lStrRemAmnt = null;
        String lStrGrantedAmnt = null;
        String lStrMAAmnt = null;
        String lStrPKTrnMedRembrsmnt = null; 
        long lLngPKTrnMedRembrsmnt = 0;
        
        try
        {
        	lStrFrmmon =StringUtility.getParameter("txtFromMM", request);
        	lStrFrmyear = StringUtility.getParameter("txtFromYYYY", request);
        	lStrTomon = StringUtility.getParameter("txtToMM", request);
        	lStrToyear = StringUtility.getParameter("txtToYYYY", request);
        	lStrRemAmnt = StringUtility.getParameter("txtReimbrseAmnt", request);
        	lStrGrantedAmnt = StringUtility.getParameter("txtGrantAmnt", request);
        	lStrMAAmnt = StringUtility.getParameter("txtMaAmnt", request);
        	lStrPPoNo = StringUtility.getParameter("txtPPONo", request);
        	lStrPKTrnMedRembrsmnt = StringUtility.getParameter("hidMedRemId", request);
        	if(lStrPKTrnMedRembrsmnt != "")
        	{
        		lLngPKTrnMedRembrsmnt = Long.parseLong(lStrPKTrnMedRembrsmnt);
        	}
        	FromMonth = lStrFrmyear + lStrFrmmon;
        	ToMonth  = lStrToyear + lStrTomon;

        	TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,servLoc.getSessionFactory());        
        	if(lLngPKTrnMedRembrsmnt != 0)
        	{
            	lObjTrnPensionMedRembrsmnt = lObjTrnPensionMedRembrsmntDAO.read(lLngPKTrnMedRembrsmnt);
        	}
        	else
        	{
        		lObjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
        	}
        	
        	/*if(FromMonth != null && FromMonth.trim().length()>0)
        	{
        		lObjTrnPensionMedRembrsmnt.setFrmmonth(Integer.parseInt(FromMonth));
        	}
        	if(ToMonth != null && ToMonth.trim().length()>0)
        	{
        		lObjTrnPensionMedRembrsmnt.setTomonth(Integer.parseInt(ToMonth));
        	}*/
        	if(lStrMAAmnt.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setMaAmnt(new BigDecimal(lStrMAAmnt));
        	}
        	else
        		lObjTrnPensionMedRembrsmnt.setMaAmnt(new BigDecimal(0));
        	if(lStrGrantedAmnt.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setGrantAmnt(new BigDecimal(lStrGrantedAmnt));
        	}
        	else
        		lObjTrnPensionMedRembrsmnt.setGrantAmnt(new BigDecimal(0));
        	if(lStrRemAmnt.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setRemAmnt(new BigDecimal(lStrRemAmnt));
        	}
        	else
        		lObjTrnPensionMedRembrsmnt.setRemAmnt(new BigDecimal(0));
        	if(lStrPPoNo.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setPpoNo(lStrPPoNo);
        	}
        	else
        		lObjTrnPensionMedRembrsmnt.setPpoNo("");
        	
        	if(lObjTrnPensionMedRembrsmnt.getMedRembrsmntId() != 0 )
        	{
        		lObjTrnPensionMedRembrsmnt.setUpdatedDate(gDtCurrDt);
	        	lObjTrnPensionMedRembrsmnt.setUpdatedPostId(new BigDecimal(gLngPostId));
	        	lObjTrnPensionMedRembrsmnt.setUpdatedUserId(new BigDecimal(gLngUserId));
        	}
        	else
        	{
	        	lObjTrnPensionMedRembrsmnt.setCreatedDate(gDtCurrDt);
	        	lObjTrnPensionMedRembrsmnt.setCreatedPostId(new BigDecimal(gLngPostId));
	        	lObjTrnPensionMedRembrsmnt.setCreatedUserId(new BigDecimal(gLngUserId));
        	}
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lObjTrnPensionMedRembrsmnt;
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
        String lStrStatus= "APPROVED";

        String lStrPensionerCode="";
        Object[] lObjData = null;
        
        TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
        
        try
        {  
        	if(lStrPPONo != null)
        	{	        	
        		lLstPensionCodeId = lObjTrnPensionRqstHdrDAO.getPksForTrnPensionRqstHdr(lStrStatus, lStrPPONo);
	            
	            if(lLstPensionCodeId != null && lLstPensionCodeId.size() > 0)
	            {
	            	lObjData = (Object[]) lLstPensionCodeId.get(0);
	            	lStrPensionerCode = lObjData[1].toString();	        	
	            }
	            
	            lObjPensionBillDtls.setPpoNo(lStrPPONo);
		        lObjPensionBillDtls.setPensionerCode(lStrPensionerCode);   
		        
	        	if(StringUtility.getParameter("txtName", request).trim() != "")
	        	{
	        		lObjPensionBillDtls.setPensionerName(StringUtility.getParameter("txtName", request).trim());
	        	}
	        	if(StringUtility.getParameter("txtAcnNo", request).trim() != "")
	        	{
	        		lObjPensionBillDtls.setAccountNo(StringUtility.getParameter("txtAcnNo", request).trim());
	        	}
	        	if(StringUtility.getParameter("txtMaAmnt", request).trim() != "")
	        	{
	        		lObjPensionBillDtls.setMedicalAllowenceAmount(new BigDecimal(StringUtility.getParameter("txtMaAmnt", request).trim()));
	        	}
	        	if(StringUtility.getParameter("txtGrantAmnt", request).trim() != "")
	        	{
	        		lObjPensionBillDtls.setReducedPension(new BigDecimal(StringUtility.getParameter("txtGrantAmnt", request).trim()));
	        	}
		        
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
