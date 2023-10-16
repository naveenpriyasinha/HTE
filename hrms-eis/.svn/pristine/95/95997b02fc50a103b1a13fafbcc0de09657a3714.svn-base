package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.PsrBillNumberCustomVO;

public class PsrBillNumberVOGen extends ServiceImpl
{	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject PsrBillNumber(Map<String, Object> objectArgs) 
	{
		try
		{
			logger.info("PsrBillNumberVOGen PsrBillNumber is  Called");		
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");									
			String billno = "";	
			String PsrNo  = "";
			String Dsgn   = "";
			String empId  = "";
			if(StringUtility.getParameter("BillNo",request)!= null && !StringUtility.getParameter("BillNo",request).equals(""))
				billno = StringUtility.getParameter("BillNo", request);
			if(StringUtility.getParameter("PsrNo",request)!= null && !StringUtility.getParameter("PsrNo",request).equals(""))
				PsrNo = StringUtility.getParameter("PsrNo", request);
			if(StringUtility.getParameter("Dsgn",request)!= null && !StringUtility.getParameter("Dsgn",request).equals(""))
				Dsgn = StringUtility.getParameter("Dsgn", request);
			if(StringUtility.getParameter("empId",request)!= null && !StringUtility.getParameter("empId",request).equals(""))
				empId = StringUtility.getParameter("empId", request);
			
			logger.info("In vogen : billno " + billno + " psr no " + PsrNo +" Dsgn " + Dsgn + " empId " + empId); 
			objectArgs.put("billno",billno);
			objectArgs.put("PsrNo",PsrNo);
			objectArgs.put("Dsgn",Dsgn);
			objectArgs.put("empId",empId);
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);	                
	        logger.info("inside the generate PsrBillNumber BillNo. is : " + billno );
		}
		catch(Exception e)
		{
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...approvePayBill");
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());		
		}		
		return retObj;
	}
	public ResultObject UpdatePsrNumberVOGen(Map<String, Object> objectArgs) 
	{
		try
		{
			logger.info("UpdatePsrNumberVOGen Psr Numbers is  Called");		
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			long listlength = request.getParameter("listSize") != null ? Long
					.parseLong(StringUtility.getParameter("listSize", request)): 0;
			List psrlist = new ArrayList();
			List pkids = new ArrayList();
			logger.info("list length is:-->>>>>" + listlength);
			for(int i = 1; i <= listlength; i++)
			{
				psrlist.add(StringUtility.getParameter("txtpsr" + i, request));
				pkids.add(StringUtility.getParameter("id" + i, request));
				
			}	
			objectArgs.put("pkids",pkids);
			objectArgs.put("psrlist",psrlist);
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...//**//**//**");
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());		
		}		
		return retObj;
	}
	public ResultObject AjaxPsrNumberVO(Map<String, Object> objectArgs) 
	{
		try
		{
			logger.info("AjaxPsrNumberVO is  Called");		
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			String NewPsrNumber = StringUtility.getParameter("NewPsrNumber", request).toString();
			objectArgs.put("NewPsrNumber",NewPsrNumber);
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...//**//**//**");
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());		
		}		
		return retObj;
	}
}
	
	