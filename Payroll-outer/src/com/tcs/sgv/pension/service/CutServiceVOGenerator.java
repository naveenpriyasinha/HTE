package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;

/**
 * TrnPensionItCutDtls VO generator class implementation
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */

public class CutServiceVOGenerator extends ServiceImpl implements VOGeneratorService
{
    /* Global Variable for Logger Class */
	private Log gLogger = LogFactory.getLog(getClass());

    /**
     * It generates TrnPensionItCutDtls VOs for different types of Cuts.
     * @param Map objectArgs
     * @return ResultObject objRes 
     */
    public ResultObject generateMap(Map objArgs)
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objArgs.get("requestObj");
        BigDecimal lBDPenReqId = null; 
        String lStrPensionerCode = null;
        String lStrTypeFlag = null;
        try
        {	
            lBDPenReqId = (new BigDecimal(StringUtility.getParameter("pension_request_id",request).trim()));
            lStrPensionerCode = StringUtility.getParameter("pensioner_code",request).trim();
            lStrTypeFlag = StringUtility.getParameter("type_flag",request).trim(); 
            
            objArgs.put("PensionRequestId",lBDPenReqId);
            objArgs.put("PensionerCode", lStrPensionerCode);    
            objArgs.put("TypeFlag", lStrTypeFlag);
            List<TrnPensionItCutDtls> lLstTrnPensionItCutDtls = generateTrnPensionItCutDtlsVOList(objArgs); 
	        objArgs.put("TrnPensionItCutDtlsVOList", lLstTrnPensionItCutDtls); 
        }
        catch (Exception e)
        {
        	gLogger.error(" Error is : " + e, e);
        	objRes.setResultValue(null);
        	objRes.setThrowable(e);
        	objRes.setResultCode(ErrorConstants.ERROR);
        	objRes.setViewName("errorPage");
        }
        objRes.setResultValue(objArgs);
        return objRes;
    }

    /**
     * It generates TrnPensionItCutDtls VOs for different types of Cuts.
     * @param Map lMapInput
     * @return List<TrnPensionItCutDtls> lLstTrnPensionItCutDtls
     */
    private List<TrnPensionItCutDtls> generateTrnPensionItCutDtlsVOList(Map lMapInput) throws Exception
    {
    	List<TrnPensionItCutDtls> lLstTrnPensionItCutDtls = new ArrayList<TrnPensionItCutDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        TrnPensionItCutDtls lObjTrnPensionItCutDtls = new TrnPensionItCutDtls();
        
        long lBDPenReqId = 0;
        String lStrPensionerCode = null;
        
        String[] lStrCmbFromMonth = null;
        String[] lStrCmbFromYear = null;
        String[] lStrCmbToMonth = null;
        String[] lStrCmbToYear = null;
        String[] lStrAmt = null;
        String[] lStrRmks = null;
        
        BigDecimal lPostId= null;
		BigDecimal lUserId = null;
        
        String lStrFromDate = null;
        String lStrToDate = null;
        String lStrCmbFromMonthVal = null;
        String lStrCmbToMonthVal = null;
        String lStrTypeFlag = null;
        String lStrPermanentAmnt = null;
        String lStrPermanentRemarks = null;
    	Double dblPPAmount = 0D;
        
        try
        {	
        	lBDPenReqId = Long.parseLong(StringUtility.getParameter("pension_request_id",request).trim());        	       	
        	lStrPensionerCode = StringUtility.getParameter("pensioner_code",request).trim();        	
        	lStrTypeFlag = StringUtility.getParameter("type_flag",request).trim();
        	
        	lUserId = new BigDecimal(SessionHelper.getUserId(lMapInput)); 
			lPostId = new BigDecimal(SessionHelper.getPostId(lMapInput));
        	
        	lStrCmbFromMonth = StringUtility.getParameterValues("cmbFromMonth", request);        	
        	lStrCmbFromYear = StringUtility.getParameterValues("cmbFromYear", request);        	
        	lStrCmbToMonth = StringUtility.getParameterValues("cmbToMonth", request);        	
        	lStrCmbToYear = StringUtility.getParameterValues("cmbToYear", request);        	
            lStrAmt = StringUtility.getParameterValues("txtAmount", request);            
            lStrRmks = StringUtility.getParameterValues("txtRemarks", request);            
            lStrPermanentAmnt = StringUtility.getParameter("txtAmountPP", request);            
            lStrPermanentRemarks = StringUtility.getParameter("txtRemarksPP", request);
            if(lStrPermanentAmnt != null && lStrPermanentAmnt.length() > 0)
                dblPPAmount = Double.parseDouble(lStrPermanentAmnt);
            
        	if(("PT".equals(lStrTypeFlag) || "TemporaryBenefit".equals(lStrTypeFlag)) && lStrPermanentAmnt.length() > 0 && dblPPAmount > 0 )
            {	
            	//setting values to VO...   
            	lObjTrnPensionItCutDtls.setFromMonth(0);
            	lObjTrnPensionItCutDtls.setToMonth(0);            	
            	lObjTrnPensionItCutDtls.setPensionRequestId(lBDPenReqId);            	
            	lObjTrnPensionItCutDtls.setPensionerCode(lStrPensionerCode);
            	if(lStrPermanentAmnt.length() == 0)
                {
            		lObjTrnPensionItCutDtls.setAmount(new BigDecimal(0));
                }
            	else
            	{
            		lObjTrnPensionItCutDtls.setAmount(new BigDecimal(lStrPermanentAmnt));
            	}
            	//=============================================.....
            	if(lStrTypeFlag.equals("TemporaryBenefit"))
            	{
            		lObjTrnPensionItCutDtls.setTypeFlag("PermanentBenefit");
            	}
            	else
            	{
            		lObjTrnPensionItCutDtls.setTypeFlag("PP");
            	}
            	//=============================================.....
            	
            	if(lStrPermanentRemarks.length() == 0)
                {
                	lObjTrnPensionItCutDtls.setRemarks(" ");
                }
                else
                {                	
                	lObjTrnPensionItCutDtls.setRemarks(lStrPermanentRemarks);
                }
            	lObjTrnPensionItCutDtls.setCreatedPostId(lPostId);
            	lObjTrnPensionItCutDtls.setCreatedUserId(lUserId);
            	lObjTrnPensionItCutDtls.setCreatedDate(new Date());
            	lLstTrnPensionItCutDtls.add(lObjTrnPensionItCutDtls); 
            }
        	
        	for (int lIntCnt = 0; lIntCnt < lStrAmt.length; ++lIntCnt)
            {
    			//generating Two digit month_no properly to insert into TrnPensionItCutDtls...
        		if(lStrAmt[lIntCnt] != null && !lStrAmt[lIntCnt].equals("0.00"))
        		{
	    			if(Integer.parseInt(lStrCmbFromMonth[lIntCnt])<10)
	    			{
	    				lStrCmbFromMonthVal="0"+lStrCmbFromMonth[lIntCnt];
	    			}
	    			else
	    			{
	    				lStrCmbFromMonthVal = lStrCmbFromMonth[lIntCnt];
	    			}
	    			if(Integer.parseInt(lStrCmbToMonth[lIntCnt])<10)
	    			{ 
	    				lStrCmbToMonthVal="0"+lStrCmbToMonth[lIntCnt];
	    			}  
	    			else
	    			{
	    				lStrCmbToMonthVal = lStrCmbToMonth[lIntCnt];
	    			}
	    			
	    			//generating From and To date combining Month and Year... 
	    			
	    			lStrFromDate = lStrCmbFromYear[lIntCnt]+lStrCmbFromMonthVal;
	    			lStrToDate   = lStrCmbToYear[lIntCnt]+lStrCmbToMonthVal;    			
	    			lObjTrnPensionItCutDtls = new TrnPensionItCutDtls();    			
	    			lObjTrnPensionItCutDtls.setFromMonth(Integer.parseInt(lStrFromDate));
	    			lObjTrnPensionItCutDtls.setToMonth(Integer.parseInt(lStrToDate));    			
	    			lObjTrnPensionItCutDtls.setAmount(new BigDecimal(lStrAmt[lIntCnt]));
	                if(lStrRmks[lIntCnt].length() == 0)
	                {
	                	lObjTrnPensionItCutDtls.setRemarks(" ");
	                }
	                else
	                {                	
	                	lObjTrnPensionItCutDtls.setRemarks(lStrRmks[lIntCnt]);
	                }
	                lObjTrnPensionItCutDtls.setPensionRequestId(lBDPenReqId);
	            	lObjTrnPensionItCutDtls.setPensionerCode(lStrPensionerCode);
	            	lObjTrnPensionItCutDtls.setTypeFlag(lStrTypeFlag);
	            	
	            	lObjTrnPensionItCutDtls.setCreatedPostId(lPostId);
	            	lObjTrnPensionItCutDtls.setCreatedUserId(lUserId);
	            	lObjTrnPensionItCutDtls.setCreatedDate(new Date());
	            	
	                lLstTrnPensionItCutDtls.add(lObjTrnPensionItCutDtls); 
        		}
            }	        
        }
        catch (Exception e)
        {
        	gLogger.info("Error is : "+e,e);  
        	throw(e);
        }
        return lLstTrnPensionItCutDtls;
    }
}

