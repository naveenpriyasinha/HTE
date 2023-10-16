package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class SearchEmployeeOnBillNoVOGen extends ServiceImpl{

	public ResultObject VoToService(Map objectArgs)
	{
		Log logger = LogFactory.getLog(getClass());
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);	
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		try
		{
			String name = StringUtility.getParameter("empName", request);
			String bill = StringUtility.getParameter("bill", request);
			String action =  StringUtility.getParameter("action", request);
			String empName = name.replace(' ', '%');
			objectArgs.put("name", empName);
			
			objectArgs.put("bill", bill);
			objectArgs.put("action", action);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			retObj.setResultValue(objectArgs);
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			retObj.setResultCode(-1);
			retObj.setThrowable(e);
			retObj.setViewName("errorPage");
		}
		return retObj;
	}
}
