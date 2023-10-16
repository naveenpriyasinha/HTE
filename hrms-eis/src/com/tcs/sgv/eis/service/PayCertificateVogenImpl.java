package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class PayCertificateVogenImpl  extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject generatePayCertificate(Map objectArgs) 
	{
		try{
		logger.info(" VoGen generatePayCertificate Called");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");									
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String selectedEmpId = request.getParameter("txtEmpId");
        
        objectArgs.put("selectedEmpId", selectedEmpId);
        objectArgs.put("month",month);
        objectArgs.put("year",year);
        logger.info("inside the generate LedgerForm VOGEN " + month + "--" + year + "\nemp id is " + selectedEmpId);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);		
		}
	   catch(Exception e){
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...");
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());			
			
		}		
		return retObj;
	}
}
