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

@SuppressWarnings("unchecked")
public class DeptCompMPGVOGenImpl extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());

	public ResultObject getDeptCompMPGDtls(Map objectArgs)
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("getDeptCompMPGDtls getDesigData Called");
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch (Exception e)
		{
			retObj.setResultCode(ErrorConstants.ERROR);
			logger.error("Error is: "+ e.getMessage());
			return retObj;
		}
		return retObj;
	}

	public ResultObject InsertDeptCompMPGDtlsVOgen(Map objectArgs)
	{
		try
		{
			logger.info("insertDeptCompMPGDtlsVogen:::::::::::::::::");
			logger.info("This is Vogeniml::::::::::::::::::::::");
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

			String srNoDeduction = StringUtility.getParameter("srNoDeduction", request);
			objectArgs.put("srNoDeduction", srNoDeduction);

			String srNoAllown = StringUtility.getParameter("srNoAllown", request);
			objectArgs.put("srNoAllown", srNoAllown);

			String cmbDept = StringUtility.getParameter("cmbDept", request);
			objectArgs.put("cmbDept", cmbDept);

			String hdnAllowList = StringUtility.getParameter("hdnAllowList", request);
			objectArgs.put("hdnAllowList", hdnAllowList);

			String hdnDeductList = StringUtility.getParameter("hdnDeductList", request);
			objectArgs.put("hdnDeductList", hdnDeductList);

			String hdnLoanList = StringUtility.getParameter("hdnLoanList", request);
			objectArgs.put("hdnLoanList", hdnLoanList);

			String hdncheckedvalueofAllow = StringUtility.getParameter("hdncheckedvalueofAllow", request);
			objectArgs.put("hdncheckedvalueofAllow", hdncheckedvalueofAllow);

			String hdncheckedvalueofdeduct = StringUtility.getParameter("hdncheckedvalueofdeduct", request);
			objectArgs.put("hdncheckedvalueofdeduct", hdncheckedvalueofdeduct);

			String hdncheckedSizeofLoan = StringUtility.getParameter("hdncheckedSizeofLoan", request);
			objectArgs.put("hdncheckedSizeofLoan", hdncheckedSizeofLoan);

			String lStrWEFDATE = StringUtility.getParameter("WEFDATE", request);
			objectArgs.put("lStrWEFDATE", lStrWEFDATE);
			Date WEFDATE = StringUtility.convertStringToDate(lStrWEFDATE);
			objectArgs.put("WEFDATE", WEFDATE);

			String Remarks = StringUtility.getParameter("Remarks", request);
			objectArgs.put("Remarks", Remarks);

			String applyCmbValue = StringUtility.getParameter("applyCmbValue", request);
			objectArgs.put("applyCmbValue", applyCmbValue);
			
			String hdnallowForAllEmp = StringUtility.getParameter("hdnallowForAllEmp", request);
			objectArgs.put("hdnallowForAllEmp", hdnallowForAllEmp);
			
			String hdndeductForAllEmp = StringUtility.getParameter("hdndeductForAllEmp", request);
			objectArgs.put("hdndeductForAllEmp", hdndeductForAllEmp);
			
			logger.info("==> in VOGEN WEFDATE :: " + WEFDATE);
			logger.info("==> in VOGEN Remarks :: " + Remarks);

			//String selcheckBoxAllow;
			String[] lArrallowList = hdnAllowList.split(",");
			String[] lArrDeductList = hdnDeductList.split(",");
			String[] lArrLoanList = hdnLoanList.split(",");

			objectArgs.put("lArrallowList", lArrallowList);
			objectArgs.put("lArrDeductList", lArrDeductList);
			objectArgs.put("lArrLoanList", lArrLoanList);

			logger.info("====> VoGEN cmbDept :: " + cmbDept);
			logger.info("====> VoGEN hdncheckedvalueofAllow :: " + hdncheckedvalueofAllow);
			logger.info("====> VoGEN hdncheckedvalueofdeduct :: " + hdncheckedvalueofdeduct);
			logger.info("====> VoGEN hdnAllowList :: " + hdnAllowList);
			logger.info("====> VoGEN hdnDeductList :: " + hdnDeductList);
			logger.info("===>  VoGEN hdnLoanList :: " + hdnLoanList);

			logger.info("====> VoGEN srNoAllown :: " + srNoAllown);
			logger.info("====> VoGEN srNoDeduction :: " + srNoDeduction);

			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			return resultObject;
		}
		catch (Exception e)
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
