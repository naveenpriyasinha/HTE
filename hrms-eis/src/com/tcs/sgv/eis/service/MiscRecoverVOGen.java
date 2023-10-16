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
import com.tcs.sgv.eis.valueobject.HrMiscRecoverDtls;

public class MiscRecoverVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	long miscId;
	
	public ResultObject generateMapForMiscRecovery(Map objectArgs) 
	{
		try{
			
			/// Code for attachment	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				ResultObject resultObj = serv.executeService("FILE_UPLOAD_VOGEN",objectArgs);		
				Map resultMap = (Map) resultObj.getResultValue();			
				
				///////////////////
		logger.info("**********Inside MiscRecoverVOGen generateMapForMiscRecovery*************");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
		HrMiscRecoverDtls hrMiscRecoverDtls = new HrMiscRecoverDtls();
				
		String editMode = StringUtility.getParameter("edit",request);
		  //long bankId = 0; 
		if(editMode.equalsIgnoreCase("N")) 
		{		
			
			hrMiscRecoverDtls = new HrMiscRecoverDtls();			
			objectArgs.put("edit","N");
		}
        else
        {         
		 String miscid = StringUtility.getParameter("miscId", request);
		 logger.info("The miscid form VOGen is------->"+miscid);
		 if( miscid!=null && !miscid.equals("")){
			 miscId = Long.parseLong(miscid);
		  }
		 hrMiscRecoverDtls.setMiscId(miscId);
		 
		 objectArgs.put("edit","Y");
        }
		long otherId=0;
		long empId=Long.parseLong(((StringUtility.getParameter("empName",request)!=null&&!(StringUtility.getParameter("empName",request).equals(""))?(StringUtility.getParameter("empName",request)):0).toString()));
		long amount = Long.parseLong(((StringUtility.getParameter("amount",request)!=null&&!(StringUtility.getParameter("amount",request).equals(""))?(StringUtility.getParameter("amount",request)):0).toString()));
		String reason = (StringUtility.getParameter("reason",request)!=null&&!(StringUtility.getParameter("reason",request).equals(""))?(StringUtility.getParameter("reason",request)):" ").toString();
		String miscDate = (StringUtility.getParameter("date",request)!=null&&!(StringUtility.getParameter("date",request).equals(""))?(StringUtility.getParameter("date",request)):" ").toString();
		long installment = Long.parseLong(((StringUtility.getParameter("installment",request)!=null&&!(StringUtility.getParameter("installment",request).equals(""))?(StringUtility.getParameter("installment",request)):0).toString()));
		long installment_amt = Long.parseLong(((StringUtility.getParameter("installmentAmt",request)!=null&&!(StringUtility.getParameter("installmentAmt",request).equals(""))?(StringUtility.getParameter("installmentAmt",request)):0).toString()));
		String miscEndDate = (StringUtility.getParameter("endDate",request)!=null&&!(StringUtility.getParameter("endDate",request).equals(""))?(StringUtility.getParameter("endDate",request)):" ").toString();
		long edpTypeId = Long.parseLong(((StringUtility.getParameter("recoveryType",request)!=null&&!(StringUtility.getParameter("recoveryType",request).equals(""))?(StringUtility.getParameter("recoveryType",request)):0).toString()));
		Integer miscActivateFlag=(StringUtility.getParameter("miscActivateFlag", request)!=null&&!(StringUtility.getParameter("miscActivateFlag", request).equals(""))?Integer.parseInt(StringUtility.getParameter("miscActivateFlag", request)):1);
		int recoverAmt=(StringUtility.getParameter("recoveredAmt", request)!=null&&!(StringUtility.getParameter("recoveredAmt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("recoveredAmt", request)):0);
		int recoverInst=(StringUtility.getParameter("recoveredInst", request)!=null&&!(StringUtility.getParameter("recoveredInst", request).equals(""))?Integer.parseInt(StringUtility.getParameter("recoveredInst", request)):0);
		String FromBasicDtlsNew=StringUtility.getParameter("FromBasicDtlsNew", request)!=null?(String)StringUtility.getParameter("FromBasicDtlsNew", request):"";
		if(FromBasicDtlsNew !=  null && !FromBasicDtlsNew.equals(""))
			otherId=StringUtility.getParameter("otherId", request)!=null&&!StringUtility.getParameter("otherId", request).equals("")?Long.parseLong(StringUtility.getParameter("otherId", request).toString()):0;
			
		Date date=null;
		Date endDate=null;
		logger.info("The employee id is------->"+empId);
		logger.info("The amount is------->"+amount);
		logger.info("The reason is------->"+reason);
		logger.info("The date is--------->"+miscDate);
		
		if(miscDate!=null && !miscDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			date = sdf.parse(miscDate);
		}
		
		if(miscEndDate!=null && !miscEndDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			endDate = sdf.parse(miscEndDate);
		}
		
		
		hrMiscRecoverDtls.setRecoverAmt(installment_amt);
		hrMiscRecoverDtls.setReason(reason);
		hrMiscRecoverDtls.setRecoverDate(date);
		hrMiscRecoverDtls.setRecoverEndDate(endDate);
		hrMiscRecoverDtls.setInstallment(installment);
		hrMiscRecoverDtls.setTotal_amount(amount);
		hrMiscRecoverDtls.setEdpTypeId(edpTypeId);
		hrMiscRecoverDtls.setMiscActivateFlag(miscActivateFlag);
		hrMiscRecoverDtls.setTot_recv_amt(recoverAmt);
		hrMiscRecoverDtls.setTot_recv_inst(recoverInst);
		
		objectArgs.put("empId",empId);
		objectArgs.put("miscRecovery",hrMiscRecoverDtls);	
		objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
		objectArgs.put("otherId", otherId);
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
