package com.tcs.sgv.eis.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;


public class ShowEducationDtlsVOGEN extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());		
	public ResultObject getEducationPendingDtlsVOGEN (Map<String, Object> objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{									
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");			
			String reqId =StringUtility.getParameter("reqId",request);
			String empId = StringUtility.getParameter("empId",request);
			objectArgs.put("reqId", reqId);
			objectArgs.put("empId", empId);			
			String flag =StringUtility.getParameter("flag",request);						
			objectArgs.put("flag", flag);			
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{	
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setResultValue(objectArgs);
			logger.error("Exception while getting a reqId from ApproveEducationDtls.jsp in VOGEN ",e);						
		}
		return resObj;
	}
}
