package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrItSectionMst;

public class SectionMasterVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	long sectId;
	
	public ResultObject generateMapForSectionMaster(Map objectArgs) 
	{
		try{
		logger.info("**********Inside SectionMasterVOGen generateMapForSectionMaster*************");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
		HrItSectionMst hrItSectionMst = new HrItSectionMst();
				
		String editMode = StringUtility.getParameter("edit",request);
		logger.info("The value of editMode is----->"+editMode);
		if(editMode.equalsIgnoreCase("N")) 
		{			
			objectArgs.put("edit","N");
		}
        else
        {
         logger.info("************** Inside Upate mode of SectionMasterVOGen**********");	
		 String sectid = StringUtility.getParameter("sectId",request);
		 logger.info("The sectId from request is-------->"+sectid);
		 if( sectid!=null && !sectid.equals("")){
			 sectId = Long.parseLong(sectid);
		  }
		 hrItSectionMst.setSectId(sectId);
		 objectArgs.put("edit","Y");
        }
		String sectionCode=(StringUtility.getParameter("sectionCode",request)!=null&&!(StringUtility.getParameter("sectionCode",request).equals(""))?(StringUtility.getParameter("sectionCode",request)):" ").toString();
		String sectionName = (StringUtility.getParameter("sectionName",request)!=null&&!(StringUtility.getParameter("sectionName",request).equals(""))?(StringUtility.getParameter("sectionName",request)):" ").toString();
		String sectionDesc = (StringUtility.getParameter("sectionDesc",request)!=null&&!(StringUtility.getParameter("sectionDesc",request).equals(""))?(StringUtility.getParameter("sectionDesc",request)):" ").toString();
		String startDate = (StringUtility.getParameter("sectionStartDate",request)!=null&&!(StringUtility.getParameter("sectionStartDate",request).equals(""))?(StringUtility.getParameter("sectionStartDate",request)):" ").toString();
		String endDate = (StringUtility.getParameter("sectionEndDate",request)!=null&&!(StringUtility.getParameter("sectionEndDate",request).equals(""))?(StringUtility.getParameter("sectionEndDate",request)):" ").toString();
		long statusid=Long.parseLong(((StringUtility.getParameter("status",request)!=null&&!(StringUtility.getParameter("status",request).equals(""))?(StringUtility.getParameter("status",request)):0).toString()));
		Date startdate=null;
		Date enddate=null;
		
		logger.info("The sectionCode is------->"+sectionCode);
		logger.info("The sectionName is------->"+sectionName);
		logger.info("The sectionDescription is------->"+sectionDesc);
		logger.info("The startDate is--------->"+startDate);
		logger.info("The endDate is--------->"+endDate);
		logger.info("The statusid is------->"+statusid);
		
		if(startDate!=null && !startDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			startdate = sdf.parse(startDate);
		}
		
		if(endDate!=null && !endDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			enddate = sdf.parse(endDate);
		}
		
		
		hrItSectionMst.setSectCode(sectionCode);
		hrItSectionMst.setSectName(sectionName);
		hrItSectionMst.setSectDesc(sectionDesc);
		hrItSectionMst.setStartDate(startdate);
		hrItSectionMst.setEndDate(enddate);
		hrItSectionMst.setActivateFlag(statusid);
		
		objectArgs.put("sectionData",hrItSectionMst);	
		//objectArgs.put("empId", empId);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);		
		}
		
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());			
			
		}		
		return retObj;
	}		
}
