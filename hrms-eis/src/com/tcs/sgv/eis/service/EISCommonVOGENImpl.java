package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class EISCommonVOGENImpl extends ServiceImpl  {

	private static final Log logger = LogFactory.getLog(EISCommonVOGENImpl.class);	
	
	public ResultObject getRequestData(Map<String, Object> objectArgs)
	{
		ResultObject objRes =new ResultObject();
		
		try
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;
			long iEmpId = (request.getParameter("empId") != null && !request.getParameter("empId").equals(""))? Long.parseLong(StringUtility.getParameter("empId",request)) : 0;
			String lStrFlag=request.getParameter("flag")!=null ? request.getParameter("flag"):"";
			String strDepartmentName=request.getParameter("departmentName")!=null ? request.getParameter("departmentName"):"";
			
			objectArgs.put("userId", iUserId);
			objectArgs.put("empId", iEmpId);
			objectArgs.put("lStrFlag", lStrFlag);
			objectArgs.put("strDepartmentName", strDepartmentName);
			
			boolean blnWorkFlowEnabled = true;
			if (request.getParameter("workFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
			}
			
			logger.info("=== In VOGEN :UserId =========="+ iUserId);
			
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled);
			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
		}catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while setting a UserId and empId in VOGEN ",e);						
		}
		return objRes;
	}
	
}




