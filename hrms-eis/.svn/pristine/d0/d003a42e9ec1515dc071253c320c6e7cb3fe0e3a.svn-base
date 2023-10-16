package com.tcs.sgv.eis.service;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpChgnameTxn;

public class EmpChangeNameVOGen extends ServiceImpl{
	
	private static final Log logger = LogFactory.getLog(EmpChangeNameVOGen.class);
	
	public ResultObject submitEmpChangeNameReqVOGen(Map objectArgs)
	{
		
		logger.info("In the submitEmpChangeNameReqVOGen******************");
		
		ResultObject resObj=new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		try 
		{
			String prefixStr = StringUtility.getParameter("SalutationTxt", request);
			String empFNameEngStr = StringUtility.getParameter("empFirstNameTxt", request);
			String empMNameEngStr = StringUtility.getParameter("empMiddleNameTxt", request);
			String empLNameEngStr = StringUtility.getParameter("empLastNameTxt", request);
			String empFNameGujStr = StringUtility.getParameter("empFirstNameGujTxt", request);
			String empMNameGujStr = StringUtility.getParameter("empMiddleNameGujTxt", request);
			String empLNameGujStr = StringUtility.getParameter("empLastNameGujTxt", request);
			String gazetteNumStr = StringUtility.getParameter("GazetteNum", request);
			String gazetteDateStr = request.getParameter("gazetteDate") != null ? StringUtility.getParameter("gazetteDate", request) : "";
			String gazetteEfeStartDateStr = request.getParameter("gazetteEfeStartDate") != null ? StringUtility.getParameter("gazetteEfeStartDate", request) : "";
			String issuingAuthoStr = StringUtility.getParameter("issuingAuthoTxt", request);
			
			Date gazetteDate = null;
			Date gazetteEfeStartDate = null;
			
			if(!"".equals(gazetteNumStr))
			{
				gazetteDate=StringUtility.convertStringToDate(gazetteDateStr);
			}
			if(!"".equals(gazetteEfeStartDateStr))
			{
				gazetteEfeStartDate = StringUtility.convertStringToDate(gazetteEfeStartDateStr);
			}
			
			HrEisEmpChgnameTxn hrEisEmpChgnameTxnEng = new HrEisEmpChgnameTxn();
			HrEisEmpChgnameTxn hrEisEmpChgnameTxnGuj = new HrEisEmpChgnameTxn();
			
			hrEisEmpChgnameTxnEng.setEmpPrefix(prefixStr);
			hrEisEmpChgnameTxnEng.setEmpFname(empFNameEngStr);
			hrEisEmpChgnameTxnEng.setEmpLname(empLNameEngStr);
			hrEisEmpChgnameTxnEng.setEmpMname(empMNameEngStr);
			hrEisEmpChgnameTxnEng.setGazetteDate(gazetteDate);
			hrEisEmpChgnameTxnEng.setGazetteEstartDate(gazetteEfeStartDate);
			hrEisEmpChgnameTxnEng.setGazetteNo(gazetteNumStr);
			hrEisEmpChgnameTxnEng.setIssuingAutho(issuingAuthoStr);
			
			hrEisEmpChgnameTxnGuj.setEmpFname(empFNameGujStr);
			hrEisEmpChgnameTxnGuj.setEmpLname(empLNameGujStr);
			hrEisEmpChgnameTxnGuj.setEmpMname(empMNameGujStr);
			
			objectArgs.put("hrEisEmpChgnameTxnEng", hrEisEmpChgnameTxnEng);
			objectArgs.put("hrEisEmpChgnameTxnGuj", hrEisEmpChgnameTxnGuj);
			
			resObj.setResultValue(objectArgs);
			
		}
		catch (Exception e) 
		{
			logger.error(e);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}	
	
	
	public ResultObject getReqIdForChangeNameVOGen(Map objectArgs)
	{
			logger.info("In the generateMap For Request ID******************");
			ResultObject object=new ResultObject(ErrorConstants.SUCCESS);
			HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
			try {               		        
					HttpSession session = request.getSession();
					String reqId="";
					
					if(request.getParameter("corrId")!=null){
						reqId=request.getParameter("corrId");
					}else{
						reqId=session.getAttribute("WorkFlowCorrespondence_CorrId").toString();
					}
					
					//Long reqId=StringUtility.convertToLong(StringUtility.getParameter(REQ_ID,request));
					logger.info("the Request Id is ::>>"+reqId);
					objectArgs.put("ReqId",reqId);
		     }
		catch (Exception e) {
			logger.error(e);
			}
			object.setResultValue(objectArgs);
			return object;
	}
}
