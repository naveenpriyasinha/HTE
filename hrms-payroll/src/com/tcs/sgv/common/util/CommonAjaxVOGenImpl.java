package com.tcs.sgv.common.util;
//Comment By Maruthi For import Organisation.
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class CommonAjaxVOGenImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());

	public ResultObject voToService(Map objectArgs)
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		
		try
		{
		 Map map = objectArgs;
		 Enumeration paraNames = request.getParameterNames();
		 while(paraNames.hasMoreElements())
         {
        	String paraName = (String)paraNames.nextElement();
        	String value = StringUtility.getParameter(paraName,request);
        	map.put(paraName, value);   
        	logger.info("Value in Map is from vo to service method" + paraName + ":--->" + value);
        }
		 objectArgs.put("voToServiceMap", map);
		 retObj.setResultValue(objectArgs);
	     retObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return retObj;
	}
	
	public ResultObject voToServiceArray(Map objectArgs)
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		
		try
		{
		 Map map = objectArgs;
		 Enumeration paraNames = request.getParameterNames();
		 while(paraNames.hasMoreElements())
         {
        	String paraName = (String)paraNames.nextElement();
        	String[] value = StringUtility.getParameterValues(paraName,request);
        	map.put(paraName, value);   
        	//logger.info("Value in Map is from vo to service method" + paraName + ":--->" +value.length+" :-------->"+ value);
        }
		 objectArgs.put("voToServiceMap", map);
		 retObj.setResultValue(objectArgs);
	     retObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return retObj;
	}

}
