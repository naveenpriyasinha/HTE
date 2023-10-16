package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;
import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class SalRevMstVOGen extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	long miscId;
	
	public ResultObject insertSalRevMst(Map objectArgs) 
	{
		try{
			
			/// Code for attachment	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				ResultObject resultObj = serv.executeService("FILE_UPLOAD_VOGEN",objectArgs);		
				Map resultMap = (Map) resultObj.getResultValue();			
				
				///////////////////
		logger.info("**********Inside SalRevMstVOGen generateMapForSalRevMst*************");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
		HrPaySalRevMst hrPaySalRevMst = new HrPaySalRevMst();
		RltBillTypeEdp rltBillTypeEdp = new RltBillTypeEdp();
		String editMode = StringUtility.getParameter("edit",request);
		  
		if(editMode.equalsIgnoreCase("N")) 
		{		
			
			hrPaySalRevMst = new HrPaySalRevMst();			
			objectArgs.put("edit","N");
		}
		
		String revOrderNo = (StringUtility.getParameter("orderName",request)!=null&&!(StringUtility.getParameter("orderName",request).equals(""))?(StringUtility.getParameter("orderName",request)):" ").toString();
		String revOrderDate = (StringUtility.getParameter("orderDate",request)!=null&&!(StringUtility.getParameter("orderDate",request).equals(""))?(StringUtility.getParameter("orderDate",request)):" ").toString();
		String revEffFromDate = (StringUtility.getParameter("effFromDate",request)!=null&&!(StringUtility.getParameter("effFromDate",request).equals(""))?(StringUtility.getParameter("effFromDate",request)):" ").toString();
		String revFffToDate = (StringUtility.getParameter("effToDate",request)!=null&&!(StringUtility.getParameter("effToDate",request).equals(""))?(StringUtility.getParameter("effToDate",request)):" ").toString();
		
		long revFreqMnthPaid=Long.parseLong(((StringUtility.getParameter("revFreqMntPaid",request)!=null&&!(StringUtility.getParameter("revFreqMntPaid",request).equals(""))?(StringUtility.getParameter("revFreqMntPaid",request)):0).toString()));
		long revInstallments = Long.parseLong(((StringUtility.getParameter("revInstallments",request)!=null&&!(StringUtility.getParameter("revInstallments",request).equals(""))?(StringUtility.getParameter("revInstallments",request)):0).toString()));
		
		long revType = Long.parseLong(((StringUtility.getParameter("revType",request)!=null&&!(StringUtility.getParameter("revType",request).equals(""))?(StringUtility.getParameter("revType",request)):0).toString()));
		rltBillTypeEdp.setTypeEdpId(revType);
		String revPayOutDate = (StringUtility.getParameter("revPayOutDate",request)!=null&&!(StringUtility.getParameter("revPayOutDate",request).equals(""))?(StringUtility.getParameter("revPayOutDate",request)):" ").toString();
		
		long revStatus = Long.parseLong(((StringUtility.getParameter("rdoActiveFlag",request)!=null&&!(StringUtility.getParameter("rdoActiveFlag",request).equals(""))?(StringUtility.getParameter("rdoActiveFlag",request)):0).toString()));
		String revReason = (StringUtility.getParameter("revReason",request)!=null&&!(StringUtility.getParameter("revReason",request).equals(""))?(StringUtility.getParameter("revReason",request)):" ").toString();
		
		String pblIndependentFlag = (StringUtility.getParameter("rdoPblIndependentFlag",request)!=null&&!(StringUtility.getParameter("rdoPblIndependentFlag",request).equals(""))?(StringUtility.getParameter("rdoPblIndependentFlag",request)):"N").toString();
		
		
		
		int DACashPercenatage = 0;
		DACashPercenatage = Integer.parseInt((StringUtility.getParameter("DACashPercenatage",request)!=null&&!(StringUtility.getParameter("DACashPercenatage",request).equals(""))?(StringUtility.getParameter("DACashPercenatage",request)):"0").toString());

		
		Date revOrderDatefmt=null;
		Date revEffFromDatefmt=null;
		Date revFffToDatefmt=null;
		Date revPayOutDatefmt=null;
		
		if(revOrderDate!=null && !revOrderDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			revOrderDatefmt = sdf.parse(revOrderDate);
		}
		
		if(revEffFromDate!=null && !revEffFromDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			revEffFromDatefmt = sdf.parse(revEffFromDate);
		}
		
		
		
		if(revFffToDate!=null && !revFffToDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			revFffToDatefmt = sdf.parse(revFffToDate);
		}
		
		if(revPayOutDate!=null && !revPayOutDate.equals(" "))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			revPayOutDatefmt = sdf.parse(revPayOutDate);
		}
		
		
		
		hrPaySalRevMst.setRevOrderNo(revOrderNo);
		hrPaySalRevMst.setRevOrderDate(revOrderDatefmt);
		hrPaySalRevMst.setRevEffcFrmDate(revEffFromDatefmt);
		hrPaySalRevMst.setRevEffcToDate(revFffToDatefmt);
		hrPaySalRevMst.setRevFreqMthPaid(revFreqMnthPaid);
		hrPaySalRevMst.setRevInstallments(revInstallments);
		hrPaySalRevMst.setRltBillTypeEdp(rltBillTypeEdp);
		hrPaySalRevMst.setRevPayOutDate(revPayOutDatefmt);
		hrPaySalRevMst.setRevStatus(revStatus);
		hrPaySalRevMst.setRevReason(revReason);
		hrPaySalRevMst.setCashPercentage(DACashPercenatage);
		hrPaySalRevMst.setPblIndependentFlg(pblIndependentFlag);
		objectArgs.put("hrPaySalRevMst",hrPaySalRevMst);	
		
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
	
	public ResultObject updateSalRevMstVOGEN(Map objServiceArgs) 
	{
		Calendar calTime = Calendar.getInstance();
		logger.info("The Inintial time for the updateSalRevMstVOGEN is:-"+calTime.getTimeInMillis());		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");		
			Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
			long loggedInUser = Long.parseLong(loginDetailsMap.get("userId").toString());			
			OrgUserMst loggedInOrgUserMst = new OrgUserMst();
			loggedInOrgUserMst.setUserId(loggedInUser);
			
			long loggedInPost = Long.parseLong(loginDetailsMap.get("primaryPostId").toString());			
			OrgPostMst loggedInOrgPostMst=new OrgPostMst();
			loggedInOrgPostMst.setPostId(loggedInPost);	
			 
			/////////////////////////
			HrPaySalRevMst hrPaySalRevMst = new HrPaySalRevMst();
			RltBillTypeEdp rltBillTypeEdp = new RltBillTypeEdp();
			String revOrderNo = (StringUtility.getParameter("orderName",request)!=null&&!(StringUtility.getParameter("orderName",request).equals(""))?(StringUtility.getParameter("orderName",request)):" ").toString();
			String revOrderDate = (StringUtility.getParameter("orderDate",request)!=null&&!(StringUtility.getParameter("orderDate",request).equals(""))?(StringUtility.getParameter("orderDate",request)):" ").toString();
			String revEffFromDate = (StringUtility.getParameter("effFromDate",request)!=null&&!(StringUtility.getParameter("effFromDate",request).equals(""))?(StringUtility.getParameter("effFromDate",request)):" ").toString();
			String revFffToDate = (StringUtility.getParameter("effToDate",request)!=null&&!(StringUtility.getParameter("effToDate",request).equals(""))?(StringUtility.getParameter("effToDate",request)):" ").toString();

			long revFreqMnthPaid=Long.parseLong(((StringUtility.getParameter("revFreqMntPaid",request)!=null&&!(StringUtility.getParameter("revFreqMntPaid",request).equals(""))?(StringUtility.getParameter("revFreqMntPaid",request)):0).toString()));
			long revInstallments = Long.parseLong(((StringUtility.getParameter("revInstallments",request)!=null&&!(StringUtility.getParameter("revInstallments",request).equals(""))?(StringUtility.getParameter("revInstallments",request)):0).toString()));

			long revType = Long.parseLong(((StringUtility.getParameter("revType",request)!=null&&!(StringUtility.getParameter("revType",request).equals(""))?(StringUtility.getParameter("revType",request)):0).toString()));
			rltBillTypeEdp.setTypeEdpId(revType);
			String revPayOutDate = (StringUtility.getParameter("revPayOutDate",request)!=null&&!(StringUtility.getParameter("revPayOutDate",request).equals(""))?(StringUtility.getParameter("revPayOutDate",request)):" ").toString();

			long revStatus = Long.parseLong(((StringUtility.getParameter("rdoActiveFlag",request)!=null&&!(StringUtility.getParameter("rdoActiveFlag",request).equals(""))?(StringUtility.getParameter("rdoActiveFlag",request)):0).toString()));
			String revReason = (StringUtility.getParameter("revReason",request)!=null&&!(StringUtility.getParameter("revReason",request).equals(""))?(StringUtility.getParameter("revReason",request)):" ").toString();

			String pblIndependentFlag = (StringUtility.getParameter("rdoPblIndependentFlag",request)!=null&&!(StringUtility.getParameter("rdoPblIndependentFlag",request).equals(""))?(StringUtility.getParameter("rdoPblIndependentFlag",request)):"N").toString();
			logger.info("pblIndependentFlag::"+pblIndependentFlag);
			double DACashPercenatage = 0;
			DACashPercenatage = Double.parseDouble((StringUtility.getParameter("DACashPercenatage",request)!=null&&!(StringUtility.getParameter("DACashPercenatage",request).equals(""))?(StringUtility.getParameter("DACashPercenatage",request)):"0").toString());
			
			long salRevId = Long.parseLong(((StringUtility.getParameter("lSalRevId",request)!=null&&!(StringUtility.getParameter("lSalRevId",request).equals(""))?(StringUtility.getParameter("lSalRevId",request)):0).toString()));

			Date revOrderDatefmt=null;
			Date revEffFromDatefmt=null;
			Date revFffToDatefmt=null;
			Date revPayOutDatefmt=null;
			
			if(revOrderDate!=null && !revOrderDate.equals(" "))
			{	
				revOrderDatefmt = sdf.parse(revOrderDate);
			}
			if(revEffFromDate!=null && !revEffFromDate.equals(" "))
			{
				revEffFromDatefmt = sdf.parse(revEffFromDate);
			}			
			if(revFffToDate!=null && !revFffToDate.equals(" "))
			{
				revFffToDatefmt = sdf.parse(revFffToDate);
			}			
			if(revPayOutDate!=null && !revPayOutDate.equals(" "))
			{
				revPayOutDatefmt = sdf.parse(revPayOutDate);
			}
						
			hrPaySalRevMst.setRevOrderNo(revOrderNo);
			hrPaySalRevMst.setRevOrderDate(revOrderDatefmt);
			hrPaySalRevMst.setRevEffcFrmDate(revEffFromDatefmt);
			hrPaySalRevMst.setRevEffcToDate(revFffToDatefmt);
			hrPaySalRevMst.setRevFreqMthPaid(revFreqMnthPaid);
			hrPaySalRevMst.setRevInstallments(revInstallments);
			hrPaySalRevMst.setRltBillTypeEdp(rltBillTypeEdp);
			hrPaySalRevMst.setRevPayOutDate(revPayOutDatefmt);
			hrPaySalRevMst.setRevStatus(revStatus);
			hrPaySalRevMst.setRevReason(revReason);
			hrPaySalRevMst.setCashPercentage(DACashPercenatage);
			hrPaySalRevMst.setPblIndependentFlg(pblIndependentFlag);
			objServiceArgs.put("salRevId",salRevId);	
			objServiceArgs.put("hrPaySalRevMst",hrPaySalRevMst);	
			
			
			//////////////////////////
			
  	        retObj.setResultValue(objServiceArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			Calendar endCalTime = Calendar.getInstance();
			logger.info("The End time time for the updateSalRevMstVOGEN is:-"+endCalTime.getTimeInMillis());
		}
		catch(Exception e)
		{			
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST IN updateSalRevMstVOGEN",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}	
}
