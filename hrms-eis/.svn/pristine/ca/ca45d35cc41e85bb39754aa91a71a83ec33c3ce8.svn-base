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


public class DeactivatePaybillVOGenImpl extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);	
	public ResultObject getEmployees(Map objectArgs)
	{
		try
		{
			logger.info("UpdatePsrNumberVOGen Psr Numbers is  Called");		
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			//long listlength = request.getParameter("length") != null ? Long.parseLong(StringUtility.getParameter("length", request)): 0;
			String postId = (StringUtility.getParameter("allPostId", request).toString() != null) && !(StringUtility.getParameter("allPostId", request).toString().equals("")) ? StringUtility.getParameter("allPostId", request).toString() : "";
			String startDate = (StringUtility.getParameter("allStartDates", request).toString() != null) && !(StringUtility.getParameter("allStartDates", request).toString().equals("")) ? StringUtility.getParameter("allStartDates", request).toString() : "";
			String endDate = (StringUtility.getParameter("allEndDates", request).toString() != null) && !(StringUtility.getParameter("allEndDates", request).toString().equals("")) ? StringUtility.getParameter("allEndDates", request).toString() : "";
			String Flag = StringUtility.getParameter("Flag", request);
			
			//System.out.print(postId);
			
			String[] postIds = postId.split(",");
			String[] startDates = startDate.split(",");
			String[] endDates = endDate.split(",");
			
			//logger.info(postId);
			//logger.info(startDate);
			//logger.info(endDate);
			//List postIdList = new ArrayList();
			objectArgs.put("postIds", postIds);
			objectArgs.put("startDates", startDates);
			objectArgs.put("endDates", endDates);
			objectArgs.put("flag",Flag);
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
			return resultObject;
		}
		return retObj;
	}

	//public ResultObject filterEmployees(Map ObjectArgs)
	//{
		
		
	//}
}
