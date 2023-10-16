package com.tcs.sgv.eis.service;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.UpdatePaybillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;

@SuppressWarnings("unchecked")
public class UpdatePaybillVOGen extends ServiceImpl implements VOGeneratorService
{

	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);

	public ResultObject updatePaybillDataAfterBillGeneration(Map objServiceArgs)
	{

		try
		{
			logger.info("Successfully Entered into UpdatePaybillVOGen");

			ServiceLocator serv = (ServiceLocator) objServiceArgs.get("serviceLocator");

			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			Map map = objServiceArgs;
			Enumeration paraNames = request.getParameterNames();
			while (paraNames.hasMoreElements())
			{
				String paraName = (String) paraNames.nextElement();
				String value = StringUtility.getParameter(paraName, request);
				map.put(paraName, value);
				logger.info("Value in Map is from vo to service method----" + paraName + ":--->" + value);
			}

//			Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
//			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			UpdatePaybillDAOImpl updatePaybillDAOImpl = new UpdatePaybillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

//			long paybillGrpId = 0;
			long psrNo = 0;
			int month = 0;
			int year = 0;
			long empId = 0;
			long type = 0;
			double basicPay = 0;
			String payBillMthdName = "";
			String remarks = "";

			if (StringUtility.getParameter("EmployeeId", request) != null && !StringUtility.getParameter("EmployeeId", request).equals(""))
			{
				empId = Long.parseLong(StringUtility.getParameter("EmployeeId", request).toString());
			}
//			if (StringUtility.getParameter("paybillGrpId", request) != null && !StringUtility.getParameter("paybillGrpId", request).equals(""))
//			{
//				paybillGrpId = Long.parseLong(StringUtility.getParameter("paybillGrpId", request));
//			}
			if (StringUtility.getParameter("psrNo", request) != null && !StringUtility.getParameter("psrNo", request).equals(""))
			{
				psrNo = Long.parseLong(StringUtility.getParameter("psrNo", request));
			}
			if (StringUtility.getParameter("paybillMonth", request) != null && !StringUtility.getParameter("paybillMonth", request).equals(""))
			{
				month = Integer.parseInt(StringUtility.getParameter("paybillMonth", request));
			}
			if (StringUtility.getParameter("paybillYear", request) != null && !StringUtility.getParameter("paybillYear", request).equals(""))
			{
				year = Integer.parseInt(StringUtility.getParameter("paybillYear", request));
			}
			if (StringUtility.getParameter("remarks", request) != null && !StringUtility.getParameter("remarks", request).equals(""))
			{
				remarks = StringUtility.getParameter("remarks", request);
			}

			List<HrPayPaybill> paybillList = null;
			HrPayPaybill payBillVO = null;
			paybillList = updatePaybillDAOImpl.getEmpDataByMonthAndYear(empId, month, year);
			logger.info("The size of paybillList from fillPaybillData  is------>>>" + paybillList.size());

			//Added By Amish

			if (paybillList != null && paybillList.size() > 0)
			{
				payBillVO = (HrPayPaybill) paybillList.get(0);
				Class pay = payBillVO.getClass();

				if (StringUtility.getParameter("basic0101", request) != null && !StringUtility.getParameter("basic0101", request).equals(""))
				{
					basicPay = Double.parseDouble(StringUtility.getParameter("basic0101", request));
					payBillVO.setBasic0101(basicPay);
				}

				RltBillTypeEdp rltBillTypeEdp = null;
				String edpCode = null;
				long lValue = 0;

				//Allowances
				type = 2500134;
				List allowanceEdpCodeList = updatePaybillDAOImpl.getEdpCodeForEmpForGeneratedPaybill(month, year, empId, type,0);
				logger.info("Allowance EDP Object:::getEdpCodeForEmpForGeneratedPaybill(month,year,empId,type)" + allowanceEdpCodeList.size());
				if (allowanceEdpCodeList != null && allowanceEdpCodeList.size() > 0)
				{
					for (int i = 0; i < allowanceEdpCodeList.size(); i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) allowanceEdpCodeList.get(i);
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						payBillMthdName = "setAllow" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);

						if (StringUtility.getParameter("allow" + edpCode, request) != null && !StringUtility.getParameter("allow" + edpCode, request).equals(""))
							lValue = Long.parseLong(StringUtility.getParameter("allow" + edpCode, request));

						payMthd.invoke(payBillVO, lValue);
					}
				}

				//Deductions
				type = 2500135;
				List deductionEdpCodeList = updatePaybillDAOImpl.getEdpCodeForEmpForGeneratedPaybill(month, year, empId, type,0);
				logger.info("Deduction EDP Object List:::getEdpCodeForEmpForGeneratedPaybill(month,year,empId,type)" + deductionEdpCodeList.size());
				if (deductionEdpCodeList != null && deductionEdpCodeList.size() > 0)
				{
					for (int i = 0; i < deductionEdpCodeList.size(); i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) deductionEdpCodeList.get(i);
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						payBillMthdName = "setDeduc" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);

						if (StringUtility.getParameter("deduc" + edpCode, request) != null && !StringUtility.getParameter("deduc" + edpCode, request).equals(""))
							lValue = Long.parseLong(StringUtility.getParameter("deduc" + edpCode, request));

						payMthd.invoke(payBillVO, lValue);
					}
				}

				//Loans
				type = 2500137;
				List loanEdpCodeList = updatePaybillDAOImpl.getEdpCodeForLoanAdv(empId, type);
				logger.info("Loan EDP Object List:::getEdpCodeForEmpForGeneratedPaybill(month,year,empId,type)" + loanEdpCodeList.size());
				if (loanEdpCodeList != null && loanEdpCodeList.size() > 0)
				{
					for (int i = 0; i < loanEdpCodeList.size(); i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) loanEdpCodeList.get(i);
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						payBillMthdName = "setLoan" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);

						if (StringUtility.getParameter("loan" + edpCode, request) != null && !StringUtility.getParameter("loan" + edpCode, request).equals(""))
							lValue = Long.parseLong(StringUtility.getParameter("loan" + edpCode, request));

						payMthd.invoke(payBillVO, lValue);
					}
					for (int i = 0; i < loanEdpCodeList.size(); i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) loanEdpCodeList.get(i);
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						payBillMthdName = "setLoanInt" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);

						if (StringUtility.getParameter("loanInt" + edpCode, request) != null && !StringUtility.getParameter("loanInt" + edpCode, request).equals(""))
							lValue = Long.parseLong(StringUtility.getParameter("loanInt" + edpCode, request));

						payMthd.invoke(payBillVO, lValue);
					}
				}

				//Advances
				type = 2500136;
				List advEdpCodeList = updatePaybillDAOImpl.getEdpCodeForLoanAdv(empId, type);
				logger.info("Advance EDP Object List:::getEdpCodeForEmpForGeneratedPaybill(month,year,empId,type)" + advEdpCodeList.size());
				if (advEdpCodeList != null && advEdpCodeList.size() > 0)
				{
					for (int i = 0; i < advEdpCodeList.size(); i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) advEdpCodeList.get(i);
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						payBillMthdName = "setAdv" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);

						if (StringUtility.getParameter("adv" + edpCode, request) != null && !StringUtility.getParameter("adv" + edpCode, request).equals(""))
							lValue = Long.parseLong(StringUtility.getParameter("adv" + edpCode, request));

						payMthd.invoke(payBillVO, lValue);
					}
				}
			}

//			double grossAmt;
//			double surcharge;
//			double totalDed;
//			double netTotal;
//			double gpay0101 = 0;

			/// Code for attachment	
			ResultObject resultObj = serv.executeService("FILE_UPLOAD_VOGEN", objServiceArgs);
			Map resultMap = (Map) resultObj.getResultValue();

			objServiceArgs.put("paybillVO", payBillVO);
			objServiceArgs.put("psrNo", psrNo);
			objServiceArgs.put("paybillMonth", month);
			objServiceArgs.put("paybillYear", year);
			objServiceArgs.put("searchData", "Y");
			objServiceArgs.put("remarks", remarks);
			objServiceArgs.put("empId", empId);

			logger.info("Successfully Exiting from UpdatePaybillVOGen");

			resultMap = objServiceArgs;
			retObj.setResultValue(resultMap);

			retObj.setResultValue(objServiceArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);

		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());

		}
		return retObj;
	}

	@Override
	public ResultObject generateMap(Map arg0)
	{
		return null;
	}
}
