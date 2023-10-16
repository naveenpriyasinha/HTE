package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrPaySignatureDtls;

public class EmployeeSignatureDtlsVOGEN extends ServiceImpl
{
	private static final 	Log logger = LogFactory.getLog(EmployeeSignatureDtlsVOGEN.class);
	
	public ResultObject generateMapForEmployeeSignatureDtls(Map objectArgs)
	{	
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
		try
	    {
		long locId=0;
		long id=0;
		logger.info("EmployeeSignatureDtlsVOGEN generateMapFOrEmployeeSignatureDtls Called");
		String editMode = StringUtility.getParameter("edit",request);
		Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");	
		locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");
		HrPaySignatureDtls hrPaySignatureDtls=new HrPaySignatureDtls();
		
		if(editMode.equalsIgnoreCase("N")) 
		{		
			
			hrPaySignatureDtls = new HrPaySignatureDtls();			
			objectArgs.put("edit","N");
		}
        else
        {         
		 String Id = StringUtility.getParameter("Id", request);
		 if( Id!=null && !Id.equals(""))
		 {
			 id = Long.parseLong(Id);
		  }
		 hrPaySignatureDtls = new HrPaySignatureDtls();
		 hrPaySignatureDtls.setId(id);
		 objectArgs.put("edit","Y");
        }
		
		long empId = 0;
		long dsngId=0;
		Date dsgnStartDt = null;
		Date dsgnEndDt = null;
		
		if(StringUtility.getParameter("cmbEmpName", request)!=null && !StringUtility.getParameter("cmbEmpName", request).equals(""))
		{
			empId = Long.parseLong(StringUtility.getParameter("cmbEmpName", request));
		}
		if(StringUtility.getParameter("cmbDesignationDesc", request)!=null && !StringUtility.getParameter("cmbDesignationDesc", request).equals(""))
		{
			dsngId = Long.parseLong(StringUtility.getParameter("cmbDesignationDesc", request).toString());
		}
		if(StringUtility.getParameter("txtStartDate", request)!=null && !StringUtility.getParameter("txtStartDate", request).equals(""))
		{
			dsgnStartDt = StringUtility.convertStringToDate(StringUtility.getParameter("txtStartDate", request));
		}
		if(StringUtility.getParameter("txtEndDate", request)!=null && !StringUtility.getParameter("txtEndDate", request).equals(""))
		{
			dsgnEndDt = StringUtility.convertStringToDate(StringUtility.getParameter("txtEndDate", request));
		}
					
		hrPaySignatureDtls.setStartDate(dsgnStartDt);
		hrPaySignatureDtls.setEndDate(dsgnEndDt);
					
		objectArgs.put("empSignatureDtls", hrPaySignatureDtls);
		objectArgs.put("dsngId", dsngId);
		objectArgs.put("empId", empId);
		
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
	    }
	catch(PropertyValueException pe)
	   {
		logger.info("Exception in generateMapFOrEmployeeSignatureDtls For EmployeeSignatureDetails-----"+pe);
		logger.info("setViewName-->errorPage");
		Map result = new HashMap();
		result.put("MESSAGECODE",300004);
		retObj.setResultValue(result);
		retObj.setThrowable(pe);
		retObj.setResultCode(-1);
		retObj.setViewName("errorPage");
	   }
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		
		return retObj;	
	}		
	
	
}
