package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

// ApproveFamilyDtls  = action Flag
public class ApproveFamilyDtlsVOGEN extends ServiceImpl 
{
	private static final Log logger = LogFactory.getLog(ApproveFamilyDtlsVOGEN.class);	
//getRequestData
	public ResultObject getRequestData(Map<String, Object> objectArgs)
	{
		ResultObject objRes =new ResultObject();	
		try
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqId = StringUtility.getParameter("reqId",request) != null ? StringUtility.getParameter("reqId",request) : "";
			String empId = StringUtility.getParameter("empId",request) != null ? StringUtility.getParameter("empId",request) : "";			
			objectArgs.put("reqId", reqId);
			objectArgs.put("empId", empId);	
			/*Sunil-Starts*/
			String approveDtls = StringUtility.getParameter("approveDtls",request) != null ? StringUtility.getParameter("approveDtls",request) : "";
			objectArgs.put("approveDtls", approveDtls);
			
			HttpSession session = request.getSession();
			String corrsIdStr="";
			
			if(request.getParameter("corrId")!=null){
				corrsIdStr=request.getParameter("corrId");
			}else{
			 corrsIdStr=session.getAttribute("WorkFlowCorrespondence_CorrId").toString();
			}
			
			objectArgs.put("corrsIdStr", corrsIdStr);	
			
			logger.info("==========WorkFlowCorrespondence_CorrId InvoGen============"+ corrsIdStr);
			/*Sunil - Ends */
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
		}catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while setting a reqId and Empid for Family Service in VOGEN ",e);						
		}
		return objRes;
	}
	public ResultObject ApproveFamilyDtlsVOGEN(Map<String, Object> objectArgs) //300446
	{
		ResultObject objRes =new ResultObject();	
		try
		{				
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqId = StringUtility.getParameter("reqId",request);
			String empId = StringUtility.getParameter("empId",request);
			String memberId = StringUtility.getParameter("memberId",request);	
			String flag = StringUtility.getParameter("flag",request);
			objectArgs.put("flag", flag);
			objectArgs.put("reqId", reqId);
			objectArgs.put("empId", empId);	
			objectArgs.put("memberId",memberId);
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
		}catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.info("Error Whiel submitting  a Family Pending Dtls For Approve  in vogen",e);						
		}
		return objRes;
	}
}
