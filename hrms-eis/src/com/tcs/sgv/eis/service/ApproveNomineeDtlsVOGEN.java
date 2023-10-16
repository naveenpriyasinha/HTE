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
public class ApproveNomineeDtlsVOGEN  extends ServiceImpl 
{
	private static final Log logger = LogFactory.getLog(ApproveNomineeDtlsVOGEN.class);
	 
	public ResultObject ShowNomineeDtlsForApprove(Map<String, Object> objectArgs)   // 300363
	{			
		ResultObject objRes = new ResultObject();
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqId = StringUtility.getParameter("reqId",request) != null ? StringUtility.getParameter("reqId",request) : "";			
			objectArgs.put("reqId",reqId);
			
			/*Divyesh-Starts*/
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
			
			logger.info("==========WorkFlowCorrespondence_CorrId Invogen============"+ corrsIdStr);
			/*Divyesh - Ends */
			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);	
		}
		catch(Exception e)
		{
			objRes.setResultCode(ErrorConstants.ERROR);		
			logger.info("Error While Getting a Nominee Reques Id for approval ,ShowNomineeDtlsForApprove called",e);
		}
		return objRes;
	}
		
	public ResultObject SubmitNomineeDtlsForApproveVOGEN(Map<String, Object> objectArgs)   // 300365
	{			
		ResultObject objRes = new ResultObject();
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqId = StringUtility.getParameter("reqId",request);			
			objectArgs.put("reqId",reqId);
			String empId = StringUtility.getParameter("empId",request);			
			objectArgs.put("empId",empId);
			String arr = StringUtility.getParameter("memberId",request);			
			objectArgs.put("arr",arr);
			String flag = StringUtility.getParameter("flag",request);
			objectArgs.put("flag", flag);
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);	
		}
		catch(Exception e)
		{
			objRes.setResultCode(ErrorConstants.ERROR);		
			logger.info("Error While Getting a Nominee Reques Id for approval ,ShowNomineeDtlsForApprove called",e);
		}
		return objRes;
	}
}

