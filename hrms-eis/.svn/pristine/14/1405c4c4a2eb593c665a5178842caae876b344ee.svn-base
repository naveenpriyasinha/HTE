package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

public class CadreMasterVOGEN extends ServiceImpl{
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);	

	public ResultObject updateCadreMaster(Map objectArgs){
		try {
		logger.info("CadreMasterVOGEN updateCadreMaster Called:::::::::::");						
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		//ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		logger.info("CadreMasterVOGEN updateCadreMaster Called:::::::::::");
		
		long cadreId = Long.parseLong(StringUtility.getParameter("cadreId", request));
		String cadreName = StringUtility.getParameter("cadreName", request);
		String cadreCode = StringUtility.getParameter("cadreCode", request);
		String cadreDesc = StringUtility.getParameter("cadreDesc", request);
		
		logger.info("cadreId in VOGEN:::::::::::::::::::::" +cadreId);
		logger.info("cadreName in VOGEN:::::::::::::::::::::" +cadreName);
		logger.info("cadreCode in VOGEN:::::::::::::::::::::" +cadreCode);
		logger.info("cadreDesc in VOGEN:::::::::::::::::::::" +cadreDesc);
		
		objectArgs.put("cadreId",cadreId);
		objectArgs.put("cadreName",cadreName);
		objectArgs.put("cadreCode",cadreCode);
		objectArgs.put("cadreDesc",cadreDesc);
					
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);

		}

		catch(Exception e){		
			objectArgs.put("MESSAGECODE",3001);
			retObj.setResultValue(objectArgs);			
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");		
			return retObj;	
	}
		return retObj;
}
}
