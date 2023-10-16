package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class LedgerFormVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject generateledgerform(Map objectArgs) 
	{
		try{
		logger.info("generateledgerform Called");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");									
        String month = request.getParameter("selMonth");
        String year = request.getParameter("selYear");
        String selectedEmpId = request.getParameter("txtEmpId");
        String billNo = (request.getParameter("billNo")!=null&&!request.getParameter("billNo").equals(""))?request.getParameter("billNo"):"";
        logger.info("the bill no is======>"+billNo);
        objectArgs.put("selectedEmpId", selectedEmpId);
        objectArgs.put("month",month);
        objectArgs.put("year",year);
        objectArgs.put("billNo",billNo);
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
			e.printStackTrace();			
			
		}		
		return retObj;
	}		
}
