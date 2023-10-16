package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrItExemptEmpDtls;

public class EmpExemptDtlsVOGen extends ServiceImpl{
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapEmpExemptDtls(Map objectArgs) {
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		List lstExemptType = new ArrayList();
		try {
		logger.info("Non Government Deduction Master VOGEN Called");						
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		String editMode;
		long exemptTypeId;
		int counter;		
		long exemptAmount;
		Date sysDate = new Date();
		logger.info("System Date is:-"+sysDate);
		editMode = StringUtility.getParameter("edit", request);
		logger.info("The Value of Edit is :-"+editMode);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");				
		List lstHrExemptEmpDtls = new ArrayList();
		List lstEmpId = new ArrayList();
		HrItExemptEmpDtls hrItExemptEmpDtls = null;
		//HrPayDeducTypeMst hrPayDeducTypeMst = null;
		if(editMode.equalsIgnoreCase("Y")) {
			long empExemptDtlsId = Long.parseLong(StringUtility.getParameter("empExemptDtlsId", request));
			hrItExemptEmpDtls = new HrItExemptEmpDtls();							
			exemptAmount = Long.parseLong(StringUtility.getParameter("exemptAmount", request));	
			hrItExemptEmpDtls.setAmount(exemptAmount);
			objectArgs.put("empExemptDtlsId",empExemptDtlsId);
			objectArgs.put("exemptEmpDtls",hrItExemptEmpDtls);
			objectArgs.put("editMode",editMode);
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);			
		}
		else {
			logger.info("Record Counter is:-"+StringUtility.getParameter("recCounter",request).toString());
			counter = Integer.parseInt(StringUtility.getParameter("recCounter",request).toString());
			logger.info("Counter is:-"+counter);
			for (int i=0;i<counter;i++) {				
				hrItExemptEmpDtls = new HrItExemptEmpDtls();
				logger.info("Employee Id is:-"+Long.parseLong(StringUtility.getParameter("empId"+i, request)));
				lstEmpId.add(Long.parseLong(StringUtility.getParameter("empId"+i, request)));	
				logger.info("Exemption Type Id is:-"+Long.parseLong(StringUtility.getParameter("exemptTypeId"+i, request)));
				exemptTypeId = Long.parseLong(StringUtility.getParameter("exemptTypeId"+i, request));
				logger.info("Amount:-"+Long.parseLong(StringUtility.getParameter("exemptionAmount"+i, request)));
				exemptAmount = Long.parseLong(StringUtility.getParameter("exemptionAmount"+i, request));
				lstExemptType.add(exemptTypeId);				
				hrItExemptEmpDtls.setAmount(exemptAmount);				
				hrItExemptEmpDtls.setCreatedDate(sysDate);			
				lstHrExemptEmpDtls.add(i, hrItExemptEmpDtls);
			}			
			objectArgs.put("lstExemptType",lstExemptType);			
			objectArgs.put("lstEmpId",lstEmpId);			
			objectArgs.put("counter", counter);			
			objectArgs.put("lstHrExemptEmpDtls",lstHrExemptEmpDtls);			
			objectArgs.put("editMode",editMode);			
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("U are out of VOGEN");	
		}
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
