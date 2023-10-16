package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;

public class VoluntryPFDtlsVOGenImpl extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	
	
	public ResultObject generateVoluntryProvidandFundMap(Map objServiceArgs) 
	{
		//System.out.println("Inside OtherDetailVOGen--------->");
		//ResultObject retObj = null;
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try{
		HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
		HttpSession session = request.getSession();
		HrPayVpfDtls hrPayVpfDtls = new HrPayVpfDtls(); 
		HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();
		//Getter
		long vpfAmount = Long.parseLong(StringUtility.getParameter("vpfAmount", request));
		int zerovpfMonths=3;
		
		//System.out.println("*************test*******"+StringUtility.getParameter("zerovpfMonths", request));
		if(StringUtility.getParameter("zerovpfMonths", request)!=null&&!StringUtility.getParameter("zerovpfMonths", request).equals("")&&StringUtility.getParameter("zerovpfMonths", request).equals("0"))
		zerovpfMonths=6;
		String editMode = StringUtility.getParameter("edit", request);		
		if(editMode.equalsIgnoreCase("Y")){
			long vpfId = Long.parseLong(StringUtility.getParameter("vpfId", request));			
			hrPayVpfDtls.setPayVpfId(vpfId);			
			logger.info("You are in Edit Mode");
		}				
		else 
		{
		long empId=Long.parseLong(StringUtility.getParameter("empId", request));
		logger.info("The Emp Id is :-"+empId);
		HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
		hrEisEmpMst.setEmpId(empId);
		hrPayVpfDtls.setHrEisEmpMst(hrEisEmpMst);	
		}
		// Setter
		
		hrPayVpfDtls.setVpfAmt(vpfAmount);
		hrPayVpfDtls.setZerovpfMonths(zerovpfMonths);
		// Setter for HrPayDeductionDtls
		
		ResultObject objResult = serv.executeService("FILE_UPLOAD_VOGEN", objServiceArgs);
        logger.info("its here 3");
        objServiceArgs = (Map) objResult.getResultValue();
        logger.info("its here 4");
		
		objServiceArgs.put("editMode", editMode);
		objServiceArgs.put("hrPayVpfDtls", hrPayVpfDtls);
		retObj.setResultValue(objServiceArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);	
		}
			
		catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}

}
