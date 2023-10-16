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
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEmpPunishmentDtls;

public class empPunishmentVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	long pmtId;
	
	public ResultObject generateMapForPunishment(Map objectArgs) 
	{
		try{
			
			/// Code for attachment	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				ResultObject resultObj = serv.executeService("FILE_UPLOAD_VOGEN",objectArgs);		
				Map resultMap = (Map) resultObj.getResultValue();			
				
				///////////////////
		logger.info("**********Inside empPunishmentVOGen generateMapForPunishment*************");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
		HrEmpPunishmentDtls hrEmpPunishmentDtls = new HrEmpPunishmentDtls();
				
		String editMode = StringUtility.getParameter("edit",request);
		logger.info("The edit from punishment is------------>>>>"+editMode);
		  //long bankId = 0; 
		if(editMode.equalsIgnoreCase("N")) 
		{		
			
			objectArgs.put("edit","N");
		}
        else
        {       
		String punishmentId = StringUtility.getParameter("pmtId", request);
		 logger.info("The punishmentId form VOGen is------->"+punishmentId);
		 if( punishmentId!=null && !punishmentId.equals("")){
			 pmtId = Long.parseLong(punishmentId);
			 objectArgs.put("pmtId", pmtId);
		  }
		 hrEmpPunishmentDtls.setPunishmentId(pmtId);
		 
		 objectArgs.put("edit","Y");
       }
		
		long empId=Long.parseLong(((StringUtility.getParameter("empName",request)!=null&&!(StringUtility.getParameter("empName",request).equals(""))?(StringUtility.getParameter("empName",request)):0).toString()));
		
		String reason = (StringUtility.getParameter("reason",request)!=null&&!(StringUtility.getParameter("reason",request).equals(""))?(StringUtility.getParameter("reason",request)):" ").toString();
		String startDate = (StringUtility.getParameter("startDate",request)!=null&&!(StringUtility.getParameter("startDate",request).equals(""))?(StringUtility.getParameter("startDate",request)):" ").toString();
		String endDate = (StringUtility.getParameter("endDate",request)!=null&&!(StringUtility.getParameter("endDate",request).equals(""))?(StringUtility.getParameter("endDate",request)):" ").toString();
		
		Date startdt=null;
		Date enddt=null;
		logger.info("The employee id is------->"+empId);
		
		
		if(startDate!=null && !startDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			startdt = sdf.parse(startDate);
		}
		
		if(endDate!=null && !endDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			enddt = sdf.parse(endDate);
		}
		
		
		hrEmpPunishmentDtls.setReason(reason);
		hrEmpPunishmentDtls.setStartDate(startdt);
		hrEmpPunishmentDtls.setEndDate(enddt);
		
		objectArgs.put("punishmentDtls",hrEmpPunishmentDtls);
		objectArgs.put("empId", empId);
		resultMap = objectArgs;
		//retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		retObj.setResultValue(resultMap);
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
