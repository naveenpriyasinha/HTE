package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEmpTraMpg;

public class HrEmpTraMpgVogenImpl extends ServiceImpl  {
	
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject generateMap(Map objServiceArgs) 
	{
		logger.info("Inside generateMap of HrEmpTraMpgVogenImpl ------");
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try{
		HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
						
		HrEmpTraMpg hrEmpTraMpg = new HrEmpTraMpg();
		
		String editModeTraMapping = (String)objServiceArgs.get("editModeForTraMapping");
		
		long distance=0;
		String vehiAvailed="FALSE";
		String vehiNumber="";
		String fromDate="";
		String toDate="";
		Date vehiAvailDate=null;
		Date vehiLeaveDate=null;
		long id =0;
				
		 if( request.getParameter("distance")!=null && !request.getParameter("distance").equals("")){
			 distance = Long.parseLong(request.getParameter("distance").toString());
			 
		 }
		 if( request.getParameter("vehiAvail")!=null && !request.getParameter("vehiAvail").equals("")){
			 vehiAvailed = request.getParameter("vehiAvail");
			 if(vehiAvailed.equals(""))
				 vehiAvailed="FALSE"; 
						 
		 }
		 
		 if( request.getParameter("vehiNo")!=null && !request.getParameter("vehiNo").equals("")){
			 vehiNumber = request.getParameter("vehiNo");
			
		 }
		
		 if( request.getParameter("fromDate")!=null && !request.getParameter("fromDate").equals("")){
			 fromDate = request.getParameter("fromDate");
			 vehiAvailDate=StringUtility.convertStringToDate(fromDate);
			 
			 
		 }
		 if( request.getParameter("toDate")!=null && !request.getParameter("toDate").equals("")){
			 toDate = request.getParameter("toDate");
			 vehiLeaveDate=StringUtility.convertStringToDate(toDate);
			 
			 
		 }
		 if( request.getParameter("taAvailed")!=null && !request.getParameter("taAvailed").equals("")){
			String strId = request.getParameter("taAvailed");
			if(strId!=null && !strId.equals(""))
			{
				id = Long.parseLong(strId);
			}
			
		 }
		 
		 
		 logger.info("distance "+distance);
		 logger.info("vehiAvailed "+vehiAvailed);
		 logger.info("vehiNumber "+vehiNumber);
		 logger.info("vehiAvailDate "+vehiAvailDate);
		 logger.info("vehiLeaveDate "+vehiLeaveDate);
		 logger.info("taAvailed "+id);
		 
		 
		 hrEmpTraMpg.setDistance(distance);
		 hrEmpTraMpg.setVehicalAvailed(vehiAvailed);
		 hrEmpTraMpg.setVehicalNo(vehiNumber);
		 hrEmpTraMpg.setVehicalAvailDate(vehiAvailDate);
		 hrEmpTraMpg.setVehicalLeaveDate(vehiLeaveDate);
		 
		objServiceArgs.put("hrEmpTraMpgVo",hrEmpTraMpg);
		objServiceArgs.put("traMpgId",id);
		retObj.setResultValue(objServiceArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		
		}catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST ",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}

}
