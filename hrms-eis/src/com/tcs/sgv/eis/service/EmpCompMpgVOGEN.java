package com.tcs.sgv.eis.service;

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

public class EmpCompMpgVOGEN extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	
	
		public ResultObject InsertEmpCompMpgDtlsVOGEN(Map ObjectArgs)
		{
			logger.info("===> in VOGEN InsertEmpCompMpgDtlsVOGEN...........");
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			HttpServletRequest request = (HttpServletRequest) ObjectArgs.get("requestObj");
			
			try 
			{
				String srNoDeduction = StringUtility.getParameter("srNoDeduction", request);
				ObjectArgs.put("srNoDeduction", srNoDeduction);
				
				String srNoAllown = StringUtility.getParameter("srNoAllown", request);
				ObjectArgs.put("srNoAllown", srNoAllown);
				
				String lStrEffectDate = StringUtility.getParameter("EffectDate", request);
				ObjectArgs.put("lStrEffectDate", lStrEffectDate);
				
				String hdnAllowList = StringUtility.getParameter("hdnAllowList", request);
				ObjectArgs.put("hdnAllowList", hdnAllowList);
				
				String hdnDeductList = StringUtility.getParameter("hdnDeductList", request);
				ObjectArgs.put("hdnDeductList", hdnDeductList);
				
				String SizeofChkValofAllow = StringUtility.getParameter("hdncheckedvalueofAllow", request);
				ObjectArgs.put("SizeofChkValofAllow", SizeofChkValofAllow);
				
				String SizeofChkValofDedu = StringUtility.getParameter("hdncheckedvalueofdeduct", request);
				ObjectArgs.put("SizeofChkValofDedu", SizeofChkValofDedu);
				
				String hdnEmpId = StringUtility.getParameter("hdnEmpId", request);
				ObjectArgs.put("hdnEmpId", hdnEmpId);
				
				String Remarks = StringUtility.getParameter("Remarks", request);
				ObjectArgs.put("Remarks", Remarks);
				
				String PayCommission = StringUtility.getParameter("PayCommission", request);
				ObjectArgs.put("PayCommission", PayCommission);
				
				Date EffectDate = StringUtility.convertStringToDate(lStrEffectDate);
				ObjectArgs.put("EffectDate", EffectDate);
				 
				//String selcheckBoxAllow;
				String[] lArrDeductList=null;
				String[] lArrallowList=null;
				
				if(hdnAllowList!=null || hdnAllowList !="")
				{
					lArrallowList = hdnAllowList.split(",");
					logger.info("====> 1 . .. in VOGEN lArrallowList :: "+lArrallowList);
				}
				
				if(hdnDeductList!=null || hdnDeductList!="")
				{
					lArrDeductList = hdnDeductList.split(",");
					logger.info("====> 1. .. in VOGEN lArrDeductList :: "+lArrDeductList);
				}
				
				ObjectArgs.put("lArrallowList", lArrallowList);
				ObjectArgs.put("lArrDeductList", lArrDeductList);
				
				logger.info("====> in VOGEN srNoAllown :: "+srNoAllown);
				logger.info("====> in VOGEN srNoDeduction :: "+srNoDeduction);
				logger.info("====> in VOGEN EffectDate :: "+EffectDate);
				logger.info("====> in VOGEN hdnAllowList :: "+hdnAllowList);
				logger.info("====> in VOGEN hdnDeductList :: "+hdnDeductList);
				logger.info("====> in VOGEN SizeofChkValofAllow :: "+SizeofChkValofAllow);
				logger.info("====> in VOGEN SizeofChkValofDedu :: "+SizeofChkValofDedu);
				logger.info("====> 2  ... in VOGEN lArrallowList :: "+lArrallowList);
				logger.info("====> 2  ... in VOGEN lArrDeductList :: "+lArrDeductList);
				logger.info("====> in VOGEN hdnEmpId :: "+hdnEmpId);
				logger.info("====> in VOGEN EffectDate :: "+EffectDate);
				
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
