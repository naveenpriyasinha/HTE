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

public class ApproveEducationDtlsVOGEN extends ServiceImpl
{
	private static final Log logger = LogFactory.getLog(ApproveEducationDtlsVOGEN.class);	
	public ResultObject SubmitEducationDtlsForApproveVOGEN(Map<String, Object> objectArgs)
	{
		ResultObject objRes =new ResultObject();	
		try
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqId = StringUtility.getParameter("reqId",request) != null ? StringUtility.getParameter("reqId",request) : "";
			String empId = StringUtility.getParameter("empId",request) != null ? StringUtility.getParameter("empId",request) : "";			
			String approveDtls = StringUtility.getParameter("approveDtls",request) != null ? StringUtility.getParameter("approveDtls",request) : "";
			String flag = StringUtility.getParameter("flag",request) != null ? StringUtility.getParameter("flag",request) : "";
			
			logger.info("==========reqId============"+ reqId);
			logger.info("==========empId============"+ empId);
			logger.info("==========approveDtls Invogen============"+ approveDtls);
			
			HttpSession session = request.getSession();
			String corrsIdStr="";
			
			if(request.getParameter("corrId")!=null){
				corrsIdStr=request.getParameter("corrId");
			}else{
			 corrsIdStr=session.getAttribute("WorkFlowCorrespondence_CorrId").toString();
			}
			
			objectArgs.put("corrsIdStr", corrsIdStr);
			
			objectArgs.put("flag", flag);
			objectArgs.put("approveDtls", approveDtls);
			objectArgs.put("reqId", reqId);
			objectArgs.put("empId", empId);				
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
		}catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while setting a reqId and Empid for Family Service in SubmitEducationDtlsForApproveVOGEN ",e);						
		}
		return objRes;
	}
}
