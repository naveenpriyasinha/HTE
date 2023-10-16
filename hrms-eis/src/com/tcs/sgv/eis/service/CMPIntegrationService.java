/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Sep 7, 2012		Vrajesh Raval								
 *******************************************************************************
 */
package com.tcs.sgv.eis.service;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import bds.authorization.MapConverter;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.helper.BaseControllerServiceExecuter;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;



public class CMPIntegrationService {

	private final static Logger gLogger = Logger.getLogger(CMPIntegrationService.class);

	public String generateCMPFile(String lStrXML) {

		boolean isError = false;
		StringBuilder lSBStatusCode = new StringBuilder();
		String lTmpXML = "";
		XStream lObjXStream = new XStream(new DomDriver());
		lObjXStream.alias("collection", Map.class);
		lObjXStream.registerConverter(new MapConverter());
		Map lMapXMLData = (Map) lObjXStream.fromXML(lStrXML);
		gLogger.info("lMapXMLData	"+lMapXMLData);
		String authNo=lMapXMLData.get("authNo").toString().trim();
		Long lAuthNo=0L;
		try {
			gLogger.info("CMPIntegrationService  WebService	");
		    if(authNo!=null && authNo.length()>0){
		    	authNo=authNo.trim();
		    	if (isValidLongNumber(authNo)){
		    		lAuthNo=Long.parseLong(authNo);
		    	}
		    	else {
					lSBStatusCode.append("03~");
					isError = true;
				}
		    }
			gLogger.info("isError is :" + isError);
			if (!isError) {
				gLogger.info("inside to call method to save detials");
				ServiceLocator servLoc = ServiceLocator.getServiceLocator();
				Map ObjectArgs = new HashedMap();
				ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
				BaseControllerServiceExecuter baseController = servLoc.getServiceExecuter();
				ObjectArgs.put("serviceNameForThr", "generateExcelForBill");
				ObjectArgs.put("authNo", lAuthNo.toString());
				ObjectArgs.put("serviceLocator", servLoc);
				ObjectArgs.put("webServiceCall", "WBS");
				gLogger.info("going to call method to save detials");
				//resultObject = baseController.offlineServiceExecuter(ObjectArgs);
				resultObject = servLoc.executeService("generateExcelForBill", ObjectArgs);

				gLogger.info("Return from Offline Service Executer");
				
				gLogger.info("-----------Enter to non error loop");
				
				if (ObjectArgs.get("isError") != null && !((Boolean) ObjectArgs.get("isError"))) {
					String retMsg="<?xml version='1.0' encoding='UTF-8' ?>"+	ObjectArgs.get("EmployeeRecord").toString();
					gLogger.info("retMsg is :" + retMsg);
					return retMsg;
				}
				
				else if (ObjectArgs.get("status") != null) {
					lSBStatusCode.append(ObjectArgs.get("status"));
					return "<?xml version='1.0' encoding='UTF-8' ?><errorStatus>"+lSBStatusCode.toString()+"</errorStatus>";
				}
				gLogger.info("-----------exir to non error loop");
				
			}
		} catch (Exception e) {
			gLogger.error("Exception is e:" + e);
			lSBStatusCode.append("07~");
		}
		
		return "<?xml version='1.0' encoding='UTF-8' ?><errorStatus>"+lSBStatusCode.toString()+"</errorStatus>";
		
	}
	
	private boolean isValidLongNumber(String lStrValue) {

		boolean isNumber = false;
		try {
			Long.parseLong(lStrValue.trim());
			isNumber = true;
		} catch (NumberFormatException e) {
			isNumber = false;
		}
		return isNumber;
	}
	
	/*public static void main(String[] args){
		CMPIntegrationService cis=new CMPIntegrationService();
		System.out.println(cis.generateCMPFile(""));
	}*/
}
