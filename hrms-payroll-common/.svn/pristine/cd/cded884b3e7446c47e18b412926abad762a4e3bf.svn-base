package com.tcs.sgv.common.report.service;

/**
 * @author 217977
 *
 */


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;



public class HRMSReportVOGeneratorImpl  extends ServiceImpl implements HRMSReportVOGEN{
	Log logger = LogFactory.getLog(getClass());
	
	public final static String REQUEST_OBJ="requestObj";
	
	
	
	public ResultObject getFileIDforreport(Map objectArgs) 
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get(REQUEST_OBJ);
	    ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
	   
	     try{
	    	String fileId=StringUtility.getParameter("fileId", request);
	    	String moduleId=StringUtility.getParameter("moduleId", request);
	    	objectArgs.put("fileId",fileId);
	    	objectArgs.put("moduleId",moduleId);
	    	retObj.setResultCode(ErrorConstants.SUCCESS);
	        retObj.setResultValue(objectArgs);
	       	}catch(Exception e)
	     	{
		      	logger.error("Error while populating file details from hrms report in vogen",e);
		    	retObj.setResultCode(ErrorConstants.ERROR);
		    	retObj.setResultCode(-1);
	     	}
	    //System.out.println("getFileIDforreport of VOGEN executed successfully::");
	    return retObj;
	}
	
	public ResultObject getDtlsforReport(Map objectArgs) 
	{
		//System.out.println("Inside Vogen::: ");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get(REQUEST_OBJ);
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
   
	     try{
	    	String appname=StringUtility.getParameter("appname", request);
	    	String appstatus=StringUtility.getParameter("appstatus", request);
	    	String fromdate=null;
	    	String todate=null;
	    	String noOfDays="";
	    	String operator="-1";
	    	String appliedDt=null;
	    	String apptype=null;
	    	
	    	/*System.out.println("appname in Voegen:: "+appname);
	    	System.out.println("appstatus in Vogen:: "+appstatus);
	    	System.out.println("fromdate"+StringUtility.getParameter("fromdate", request));
	    	System.out.println("toDate"+StringUtility.getParameter("todate", request));
	    	System.out.println("No of Days"+StringUtility.getParameter("NoOfDays", request));
	    	System.out.println("Operator"+StringUtility.getParameter("operator", request));
	    	System.out.println("Apptype"+StringUtility.getParameter("apptype", request));*/
	    	
	    	if(StringUtility.getParameter("NoOfDays",request)!=""){
	    		
	    		noOfDays=StringUtility.getParameter("NoOfDays",request);
	    		//System.out.println("The NoOfDays is ::>>"+noOfDays);
	    		objectArgs.put("NoOfDays",noOfDays);
	    	}
	    	
	    	if(StringUtility.getParameter("operator",request)!="-1"){
	    		
	    		operator=StringUtility.getParameter("operator",request);
	    		//System.out.println("The Operator is :>>"+operator);
	    		objectArgs.put("operator", operator);
	    	}
	    	
	    	if(StringUtility.getParameter("fromdate", request)!=""){
	    		
	    		//System.out.println("Inside FrmDate if cond. in VOGEN>> "+StringUtility.getParameter("fromdate", request));
	    		fromdate=StringUtility.getParameter("fromdate", request);
	    		objectArgs.put("fromdate",fromdate);
	    	}
	    	
	    	if(StringUtility.getParameter("todate", request)!=""){
	    		
	    		//System.out.println("Inside toDate if cond. in VOGEN :: "+StringUtility.getParameter("todate", request));
	    		todate=StringUtility.getParameter("todate", request);
	    		objectArgs.put("todate",todate);
	    	}
	    	
	    	if(StringUtility.getParameter("appliedDt", request) != ""){
	    		
	    		appliedDt=StringUtility.getParameter("appliedDt", request);
	    		//System.out.println("Applied DAte in VOGEN :: "+appliedDt);
	    		objectArgs.put("appliedDt", appliedDt);
	    	}
	    	
	    	if(StringUtility.getParameter("apptype",request)!="-1"){
	    		
	    		apptype=StringUtility.getParameter("apptype",request);
	    		//System.out.println("The apptype is :>>"+apptype);
	    		objectArgs.put("apptype", apptype);
	    	}
	    	 
	    	objectArgs.put("appname",appname);
	    	objectArgs.put("appstatus",appstatus);
	    	
	    	
	    	retObj.setResultCode(ErrorConstants.SUCCESS);
	        retObj.setResultValue(objectArgs);
	       	}catch(Exception e)
	     	{
		      	logger.error("Error while populating file details from hrms report in vogen",e);
		    	retObj.setResultCode(ErrorConstants.ERROR);
		    	retObj.setResultCode(-1);
	     	}
	    return retObj;
		}
}