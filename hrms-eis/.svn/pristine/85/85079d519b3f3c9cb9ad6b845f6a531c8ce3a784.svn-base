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

public class OrgUserPostEndDateVOGEN extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	
	
		public ResultObject InsertEmpEndDateVOGEN(Map ObjectArgs)
		{
			//System.out.println("===> in VOGEN InsertEmpEndDateVOGEN...........");
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			HttpServletRequest request = (HttpServletRequest) ObjectArgs.get("requestObj");
			
			try 
			{
				String lStrEndDate="";
				String OrderDate="";
				long lReasonForEndid=0;
				String lEligiPension="";
				String lEligiGratuity="";
				String RefLetterNo="";
				String Remarks="";
				
				
				
				lStrEndDate=(StringUtility.getParameter("EndDate", request)!=null&&!(StringUtility.getParameter("EndDate", request).equals(""))?(StringUtility.getParameter("EndDate", request)):" ").toString();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date EmpEndDate=sdf.parse(lStrEndDate);
				
				//System.out.println("==============> in vogen EmpEndDate :: "+EmpEndDate);
				ObjectArgs.put("EmpEndDate", EmpEndDate);
				
				String hdnEmpId = StringUtility.getParameter("hdnEmpId", request);
				ObjectArgs.put("hdnEmpId", hdnEmpId);
				
				
				OrderDate=(StringUtility.getParameter("OrderDated", request)!=null&&!(StringUtility.getParameter("OrderDated", request).equals(""))?(StringUtility.getParameter("OrderDated", request)):" ").toString();
				Date OrderDated=sdf.parse(OrderDate);
				ObjectArgs.put("OrderDated", OrderDated);
				
				Remarks=(StringUtility.getParameter("Remarks", request)!=null&&!(StringUtility.getParameter("Remarks", request).equals(""))?(StringUtility.getParameter("Remarks", request)):" ").toString();
				ObjectArgs.put("Remarks", Remarks);
				
				
				lReasonForEndid = request.getParameter("ReasonForEnd")!= null && !request.getParameter("ReasonForEnd").equals("") ? Long.parseLong(request.getParameter("ReasonForEnd").toString()):0l;
				ObjectArgs.put("lReasonForEndid",String.valueOf(lReasonForEndid));
				
				lEligiPension=(StringUtility.getParameter("rdoEligiblePen", request)!=null&&!(StringUtility.getParameter("rdoEligiblePen", request).equals(""))?(StringUtility.getParameter("rdoEligiblePen", request)):" ").toString();
				ObjectArgs.put("lEligiPension",lEligiPension);
				
				lEligiGratuity=(StringUtility.getParameter("rdoEligibleGrat", request)!=null&&!(StringUtility.getParameter("rdoEligibleGrat", request).equals(""))?(StringUtility.getParameter("rdoEligibleGrat", request)):" ").toString();
				ObjectArgs.put("lEligiGratuity",lEligiGratuity);
				
				
				RefLetterNo=(StringUtility.getParameter("oderRefNo", request)!=null&&!(StringUtility.getParameter("oderRefNo", request).equals(""))?(StringUtility.getParameter("oderRefNo", request)):" ").toString();
				ObjectArgs.put("RefLetterNo",RefLetterNo);
				
				resultObject.setResultValue(ObjectArgs);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				return resultObject;
			}
			catch(Exception e)
			{
				ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
				Map errorMap = new HashMap();
				errorMap.put("msg", "There is some problem. Please Try Again Later.");
				retObj.setResultCode(-1);
				retObj.setResultValue(errorMap);
				retObj.setViewName("errorInsert");
				logger.error("Error is: "+ e.getMessage());
				return retObj;
			}
		}
	

}
