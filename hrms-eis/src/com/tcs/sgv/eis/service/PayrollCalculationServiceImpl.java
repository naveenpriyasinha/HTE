package com.tcs.sgv.eis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;

import com.ibm.icu.text.SimpleDateFormat;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.LedgerReportDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.eis.dao.HrEisScaleMstDao;
import com.tcs.sgv.eis.dao.HrEisScaleMstDaoImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentMasterDAO;
import com.tcs.sgv.eis.dao.PayComponentMasterDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentParamMstDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupMstDAO;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupMstDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupParamRltDAO;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupParamRltDAOImpl;
import com.tcs.sgv.eis.valueobject.AllwValCustomVO;
import com.tcs.sgv.eis.valueobject.EmpPaybillVO;
import com.tcs.sgv.eis.valueobject.HrPayAllowDedMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayRemarksMst;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpMst;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpParamRlt;
import com.tcs.sgv.eis.valueobject.HrPayEmpSalaryTxn;
import com.tcs.sgv.eis.valueobject.HrPayRuleParamMst;
import com.tcs.sgv.payroll.util.PayrollConstants;

/**
 * This class consists of methods to retrieve rule based pay components' values
 * 
 * @class name : PayrollCalculationServiceImpl
 * @author : 229271
 * @version : 1
 */

public class PayrollCalculationServiceImpl extends ServiceImpl {
	private static final Log logger = LogFactory.getLog(PayrollCalculationServiceImpl.class);

	public PayrollCalculationServiceImpl() {

	}

	@SuppressWarnings("unchecked")
	public ResultObject calculateemployeesalary(Map<String, Object> objectArgs) {

		// logger.info("PayrollCalculationServiceImpl--->calculateEmployeeSalary method
		// called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			long reqdEmployeeCode = Long.parseLong(objectArgs.get("reqdEmployeeCode").toString());

			long reqdPayCompCode = Long.parseLong((objectArgs.get("reqdPayCompCode") != null
					&& !objectArgs.get("reqdPayCompCode").toString().equals(""))
							? (objectArgs.get("reqdPayCompCode").toString())
							: "0");

			Map<Integer, Object> paramValueMap = (Map<Integer, Object>) objectArgs.get("paramValueMap");

			Map<Long, HrPayEmpSalaryTxn> empCalculatedSalary = new HashMap<Long, HrPayEmpSalaryTxn>();

			HrPayEmpSalaryTxn empSalTxn = null;

			empCalculatedSalary = getAllRuleBasedPayCompValue(reqdEmployeeCode, paramValueMap, serv, reqdPayCompCode,
					objectArgs);
			// ,objectArgs);

			PayComponentMasterDAO payCompDAO = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class,
					serv.getSessionFactory());
			List<HrPayAllowDedMst> allpayCompList = payCompDAO.getListByColumnAndValue("langId", 1);

			Calendar cal = Calendar.getInstance();
			int currentMonth = cal.get(Calendar.MONTH);
			int currentYear = cal.get(Calendar.YEAR);

			// To set GPF for 3 months retirement case
			if (PayrollConstants.PAYROLL_GPF_RETIREMENT_RULE_FLAG == 1) {
				if ((objectArgs.get("empDateOfRetirement") != null)) {
					Date empDateOfRetirement = (Date) objectArgs.get("empDateOfRetirement");

					Calendar calForGPF = Calendar.getInstance();
					calForGPF.setTime(empDateOfRetirement);
					int retirementMonth = calForGPF.get(Calendar.MONTH);
					int retirementYear = calForGPF.get(Calendar.YEAR);

					int monthDiff = 0;
					if (retirementYear == currentYear) {
						monthDiff = retirementMonth - currentMonth;
					} else if (retirementYear > currentYear) {
						monthDiff = ((retirementYear - 1) - currentYear) * 12;
						monthDiff = monthDiff + (11 - currentMonth) + (retirementMonth);
					}

					if (monthDiff < PayrollConstants.PAYROLL_GPF_RETIREMENT_RULE_MONTH_COUNT) {
						if (empCalculatedSalary.containsKey(PayrollConstants.PAYROLL_GPF_CODE)) {
							empSalTxn = new HrPayEmpSalaryTxn();
							empSalTxn.setEmpId(empCalculatedSalary.get(PayrollConstants.PAYROLL_GPF_CODE).getEmpId());
							empSalTxn.setAllwDedCode(PayrollConstants.PAYROLL_GPF_CODE);
							empSalTxn.setAmount(BigDecimal.ZERO);

							empCalculatedSalary.put(PayrollConstants.PAYROLL_GPF_CODE, empSalTxn);
						}
					}
				}
			}

			if (objectArgs.get("empGPFAccntNo").toString().equals("")) {
				if (empCalculatedSalary.containsKey(PayrollConstants.PAYROLL_GPF_CODE)) {
					empSalTxn = new HrPayEmpSalaryTxn();
					empSalTxn.setEmpId(empCalculatedSalary.get(PayrollConstants.PAYROLL_GPF_CODE).getEmpId());
					empSalTxn.setAllwDedCode(PayrollConstants.PAYROLL_GPF_CODE);
					empSalTxn.setAmount(BigDecimal.ZERO);

					empCalculatedSalary.put(PayrollConstants.PAYROLL_GPF_CODE, empSalTxn);
				}
			}
			// GPF setting ends

			// if((paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_QUARTERTYPE)!=null)&&(!paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_QUARTERTYPE).toString().equals("")))
			// {
			// if(empCalculatedSalary.containsKey(PayrollConstants.PAYROLL_HRA_CODE))
			// {
			// empSalTxn = new HrPayEmpSalaryTxn();
			// empSalTxn.setEmpId(empCalculatedSalary.get(PayrollConstants.PAYROLL_HRA_CODE).getEmpId());
			// empSalTxn.setAllwDedCode(PayrollConstants.PAYROLL_HRA_CODE);
			// empSalTxn.setAmount(BigDecimal.ZERO);
			//
			// empCalculatedSalary.put(PayrollConstants.PAYROLL_HRA_CODE, empSalTxn);
			// }
			// }

			/*
			 * if(PayrollConstants.PAYROLL_PT_RULE_ANNUAL_FLAG==1) { long langId=1;
			 * 
			 * HrPayAllowDedMst PTAllowMst =
			 * payCompDAO.read(PayrollConstants.PAYROLL_PT_CODE);
			 * 
			 * if(PTAllowMst.getRuleBasedFlag()==1) { BigDecimal remainingPT =
			 * BigDecimal.ZERO; BigDecimal empCurrentGross = BigDecimal.ZERO; BigDecimal
			 * empTotalAvailedGross = BigDecimal.ZERO; BigDecimal projectedAnnualGross =
			 * BigDecimal.ZERO; BigDecimal empTotalPTDeducted = BigDecimal.ZERO;
			 * 
			 * PayComponentRuleGroupMstDAO ruleMstDao = new
			 * PayComponentRuleGroupMstDAOImpl(HrPayRuleGrpMst.class,serv.getSessionFactory(
			 * )); List<HrPayRuleGrpMst> ruleGrpMstList =
			 * ruleMstDao.getPayCompRuleList(PayrollConstants.PAYROLL_PT_CODE);
			 * 
			 * PayComponentRuleGroupParamRltDAO ruleRltDao = new
			 * PayComponentRuleGroupParamRltDAOImpl(HrPayRuleGrpParamRlt.class,serv.
			 * getSessionFactory()); List<HrPayRuleGrpParamRlt> ruleParamRltList =
			 * ruleRltDao.getPayCompRuleParamMpgList(PayrollConstants.PAYROLL_PT_CODE);
			 * 
			 * // logic to calculate projected annual gross salary EmployeesSalaryDAO
			 * empSalDao = new
			 * EmployeesSalaryDAOImpl(HrPayEmpSalaryTxn.class,serv.getSessionFactory());
			 * empCurrentGross =
			 * empSalDao.getEmployeeCurrentGrossOrDeduction(reqdEmployeeCode,
			 * PayrollConstants.PAYROLL_ALLOWANCE_TYPE);
			 * 
			 * empTotalAvailedGross =
			 * empSalDao.getEmpCurrentFinYearTotalGrossOrDeductionTillDate(reqdEmployeeCode,
			 * PayrollConstants.PAYROLL_ALLOWANCE_TYPE);
			 * 
			 * empTotalPTDeducted =
			 * empSalDao.getEmpCurrentFinYearTotalPayComp(reqdEmployeeCode,
			 * PayrollConstants.PAYROLL_PT_CODE); if(empCurrentGross.longValue()==0) {
			 * String[] cmprCol = {"langId,type,status"}; Object[] cmprVal =
			 * {1,PayrollConstants.PAYROLL_ALLOWANCE_TYPE,1}; List<HrPayAllowDedMst>
			 * allActiveAllowanceList = payCompDAO.getListByColumnAndValue(cmprCol,
			 * cmprVal);
			 * 
			 * for(HrPayAllowDedMst payComp : allActiveAllowanceList) {
			 * if(empCalculatedSalary.containsKey(payComp.getAllwDedCode())) {
			 * empCurrentGross=empCurrentGross.add(empCalculatedSalary.get(payComp.
			 * getAllwDedCode()).getAmount()); } } }
			 * 
			 * 
			 * 
			 * if(currentMonth<=2) {
			 * empCurrentGross=empCurrentGross.multiply(BigDecimal.valueOf((3-currentMonth))
			 * ); } else {
			 * empCurrentGross=empCurrentGross.multiply(BigDecimal.valueOf((15-currentMonth)
			 * )); } projectedAnnualGross = empTotalAvailedGross.add(empCurrentGross);
			 * 
			 * paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GROSSPAY,
			 * projectedAnnualGross); // logic to calculate projected annual gross salary
			 * ends
			 * 
			 * boolean ruleGrpIdFlag=false; long lowerScaleParamLowLmt=0; long
			 * upperScaleParamLowLmt=0; long lowerScaleGP=0; long upperScaleGP=0; long
			 * lowerScaleIncrAmnt=0; long upperScaleIncrAmnt=0;
			 * 
			 * HrEisScaleMst scaleMstLow = new HrEisScaleMst(); HrEisScaleMst scaleMstUpr =
			 * new HrEisScaleMst(); HrEisScaleMst scaleMstEmp = new HrEisScaleMst();
			 * HrEisScaleMstDao scaleMstDAO = new HrEisScaleMstDaoImpl(HrEisScaleMst.class,
			 * serv.getSessionFactory()); String[] scaleCmprCol =
			 * {"scaleCode","cmnLanguageMst.langId"}; Object[] empScaleColVal =
			 * {Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYSCALE).
			 * toString()),langId}; scaleMstEmp =
			 * scaleMstDAO.getListByColumnAndValue(scaleCmprCol, empScaleColVal).get(0);
			 * 
			 * int compareLowValFlag=2; int compareUprValFlag=2;
			 * 
			 * 
			 * 
			 * 
			 * for(HrPayRuleGrpMst allowanceRuleMst:ruleGrpMstList) { ruleGrpIdFlag=false;
			 * for(HrPayRuleGrpParamRlt allowRuleParamMpg:ruleParamRltList) {
			 * if(allowRuleParamMpg.getRuleGrpId().getRuleGrpId()==allowanceRuleMst.
			 * getRuleGrpId()) {
			 * 
			 * if(allowRuleParamMpg.getParamId().getParamType()==PayrollConstants.
			 * PAYROLL_PARAMETER_FIXED_TYPE) {
			 * if(allowRuleParamMpg.getParamValue().equals(paramValueMap.get(
			 * allowRuleParamMpg.getParamId().getParamId()).toString())) {
			 * ruleGrpIdFlag=true; } else{ ruleGrpIdFlag=false; break; } } else
			 * if((allowRuleParamMpg.getParamId().getParamType()==PayrollConstants.
			 * PAYROLL_PARAMETER_RANGE_TYPE) &&
			 * (allowRuleParamMpg.getParamId().getParamId()!=PayrollConstants.
			 * PAYROLL_PARAMID_PAYSCALE)) { compareLowValFlag=
			 * BigDecimal.valueOf(Long.parseLong(paramValueMap.get(allowRuleParamMpg.
			 * getParamId().getParamId()).toString())).compareTo(allowRuleParamMpg.
			 * getParamLowerValue());
			 * 
			 * if(allowRuleParamMpg.getParamUpperValue().longValue()!=-1) {
			 * compareUprValFlag=BigDecimal.valueOf(Long.parseLong(paramValueMap.get(
			 * allowRuleParamMpg.getParamId().getParamId()).toString())).compareTo(
			 * allowRuleParamMpg.getParamUpperValue()); } else { compareUprValFlag=-1; }
			 * 
			 * if(((compareLowValFlag==0)||(compareLowValFlag==1))&&((compareUprValFlag==0)|
			 * |(compareUprValFlag==-1))) { ruleGrpIdFlag=true; } else{ ruleGrpIdFlag=false;
			 * break; } } else
			 * if((allowRuleParamMpg.getParamId().getParamType()==PayrollConstants.
			 * PAYROLL_PARAMETER_RANGE_TYPE) &&
			 * (allowRuleParamMpg.getParamId().getParamId()==PayrollConstants.
			 * PAYROLL_PARAMID_PAYSCALE)) {
			 * if(allowRuleParamMpg.getParamLowerValue().longValue()!=0) { Object[]
			 * lowScaleColVal = {allowRuleParamMpg.getParamLowerValue().longValue(),langId};
			 * scaleMstLow = scaleMstDAO.getListByColumnAndValue(scaleCmprCol,
			 * lowScaleColVal).get(0);
			 * 
			 * lowerScaleParamLowLmt=scaleMstLow.getScaleStartAmt();
			 * lowerScaleGP=scaleMstLow.getScaleGradePay()!=null?scaleMstLow.
			 * getScaleGradePay():0; lowerScaleIncrAmnt=scaleMstLow.getScaleIncrAmt();
			 * 
			 * } if(allowRuleParamMpg.getParamUpperValue().longValue()!=-1) { Object[]
			 * uprScaleColVal = {allowRuleParamMpg.getParamUpperValue().longValue(),langId};
			 * scaleMstUpr = scaleMstDAO.getListByColumnAndValue(scaleCmprCol,
			 * uprScaleColVal).get(0);
			 * 
			 * upperScaleParamLowLmt=scaleMstUpr.getScaleStartAmt();
			 * upperScaleGP=scaleMstUpr.getScaleGradePay()!=null?scaleMstUpr.
			 * getScaleGradePay():0; upperScaleIncrAmnt=scaleMstUpr.getScaleIncrAmt(); }
			 * 
			 * if(lowerScaleParamLowLmt==0) {
			 * lowerScaleParamLowLmt=scaleMstEmp.getScaleStartAmt(); }
			 * if(upperScaleParamLowLmt==0) {
			 * upperScaleParamLowLmt=scaleMstEmp.getScaleStartAmt(); }
			 * 
			 * if(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
			 * equals("6")) {
			 * 
			 * if((scaleMstEmp.getScaleStartAmt()>lowerScaleParamLowLmt) &&
			 * (scaleMstEmp.getScaleStartAmt()<upperScaleParamLowLmt)) { ruleGrpIdFlag=true;
			 * } else if((scaleMstEmp.getScaleStartAmt()==lowerScaleParamLowLmt) &&
			 * (scaleMstEmp.getScaleGradePay()>=lowerScaleGP)) { ruleGrpIdFlag=true; } else
			 * if((scaleMstEmp.getScaleStartAmt()==upperScaleParamLowLmt) &&
			 * (scaleMstEmp.getScaleGradePay()<=upperScaleGP)) { ruleGrpIdFlag=true; } else
			 * { ruleGrpIdFlag=false; break; }
			 * 
			 * 
			 * } else
			 * if(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
			 * equals("5")) { if((scaleMstEmp.getScaleStartAmt()>lowerScaleParamLowLmt) &&
			 * (scaleMstEmp.getScaleStartAmt()<upperScaleParamLowLmt)) { ruleGrpIdFlag=true;
			 * } else if((scaleMstEmp.getScaleStartAmt()==lowerScaleParamLowLmt) &&
			 * (scaleMstEmp.getScaleIncrAmt()>=lowerScaleIncrAmnt)) { ruleGrpIdFlag=true; }
			 * else if((scaleMstEmp.getScaleStartAmt()==upperScaleParamLowLmt) &&
			 * (scaleMstEmp.getScaleIncrAmt()<=upperScaleIncrAmnt)) { ruleGrpIdFlag=true; }
			 * else { ruleGrpIdFlag=false; break; } } } } }
			 * 
			 * if(ruleGrpIdFlag){
			 * 
			 * if(allowanceRuleMst.getReturnType()==PayrollConstants.
			 * PAYROLL_RETURNTYPE_FIXED) { empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD by
			 * 229271 on 1/27/12 7:28 AM empSalTxn.setEmpCode(reqdEmployeeCode);
			 * empSalTxn.setAllwDedCode(PayrollConstants.PAYROLL_PT_CODE);
			 * 
			 * //add logic to calculate projected PT amount
			 * 
			 * remainingPT = allowanceRuleMst.getReturnValue().subtract(empTotalPTDeducted);
			 * 
			 * int monthsRemaining = 0; if(currentMonth<=2) {
			 * monthsRemaining=3-currentMonth; } else { monthsRemaining=15-currentMonth; }
			 * 
			 * remainingPT = BigDecimal.valueOf(remainingPT.longValue()/monthsRemaining);
			 * 
			 * empSalTxn.setAmount(remainingPT);
			 * 
			 * empCalculatedSalary.put(PayrollConstants.PAYROLL_PT_CODE, empSalTxn); }
			 * 
			 * break; } }
			 * 
			 * } }
			 */

			// for(int i=0;i<allpayCompList.size();i++)
			// {
			// if(empCalculatedSalary.containsKey(allpayCompList.get(i).getAllwDedCode()))
			// {
			// logger.info("##-----PayrollCalculationServiceImpl---calculateEmployeeSalary--##...."+empCalculatedSalary.get(allpayCompList.get(i).getAllwDedCode()).getAmount());
			// logger.info("##-----PayrollCalculationServiceImpl---calculateEmployeeSalary---###..."+allpayCompList.get(i).getAllowDedName());
			// logger.info("##----PayrollCalculationServiceImpl---calculateEmployeeSalary---###..."+empCalculatedSalary.get(allpayCompList.get(i).getAllwDedCode()).getEmpId());
			// }
			//
			// }

			objectArgs.put("empCalculatedSalaryMap", empCalculatedSalary);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			resObj.setThrowable(ex);
			logger.error("Error in PayrollCalculationServiceImpl--->calculateEmployeeSalary", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}

	public Map<Long, HrPayEmpSalaryTxn> getAllRuleBasedPayCompValue(long employeeCode,
			Map<Integer, Object> paramValueMap, ServiceLocator serv, long payCompCode, Map inputMap)
	// ,Map<String, Object> objectArgs)
	{
		// logger.info("PayrollCalculationServiceImpl--->getAllRuleBasedPayCompValue
		// method called");
		logger.info("Paycommission id is " + payCompCode);
		Map<Long, HrPayEmpSalaryTxn> empSalaryMap = new HashMap<Long, HrPayEmpSalaryTxn>();
		try {
			long langId = 1;
			HrPayEmpSalaryTxn empSalTxn = null;

			EmpDAOImpl empDao = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
			OrgEmpMst orgEmp = empDao.read(employeeCode);

			PayComponentMasterDAOImpl payComponentMasterDAOImpl = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class,
					serv.getSessionFactory());

			// PayComponentParamMstDAOImpl payComponentParamMstDAOImpl = new
			// PayComponentParamMstDAOImpl(HrPayRuleParamMst.class,serv.getSessionFactory());
			PayComponentRuleGroupMstDAOImpl payComponentRuleGroupMstDAOImpl = new PayComponentRuleGroupMstDAOImpl(
					HrPayRuleGrpMst.class, serv.getSessionFactory());
			PayComponentRuleGroupParamRltDAOImpl payComponentRuleGroupParamRltDAOImpl = new PayComponentRuleGroupParamRltDAOImpl(
					HrPayRuleGrpParamRlt.class, serv.getSessionFactory());

			// Performance improvement - Manish

			/*
			 * List<HrPayRuleGrpMst> activeAllowanceRuleList = (List<HrPayRuleGrpMst>)
			 * payComponentRuleGroupMstDAOImpl.getAllActiveRuleList(PayrollConstants.
			 * PAYROLL_ALLOWANCE_TYPE);
			 * logger.info("Active allowances are "+activeAllowanceRuleList.size());
			 * List<HrPayRuleGrpMst> activeDeductionRuleList = (List<HrPayRuleGrpMst>)
			 * payComponentRuleGroupMstDAOImpl.getAllActiveDeductionRuleList();
			 * List<HrPayRuleGrpParamRlt> activeAllowRuleParamMpgList =
			 * (List<HrPayRuleGrpParamRlt>)
			 * payComponentRuleGroupParamRltDAOImpl.getAllActiveRuleParamMpgList(
			 * PayrollConstants.PAYROLL_ALLOWANCE_TYPE);
			 * 
			 * logger.info("check  above query " +activeAllowRuleParamMpgList.size());
			 * List<HrPayRuleGrpParamRlt> activeDeducRuleParamMpgList =
			 * (List<HrPayRuleGrpParamRlt>)
			 * payComponentRuleGroupParamRltDAOImpl.getAllActiveDeductionRuleParamMpgList();
			 * 
			 * List<HrPayRuleGrpMst> activeAllowanceUsedInFormulaRuleList =
			 * (List<HrPayRuleGrpMst>)
			 * payComponentRuleGroupMstDAOImpl.getAllActiveRuleList(PayrollConstants.
			 * PAYROLL_ALLOWANCE_TYPE, 1); List<HrPayRuleGrpMst>
			 * activeDeductionUsedInFormulaRuleList = (List<HrPayRuleGrpMst>)
			 * payComponentRuleGroupMstDAOImpl.getAllActiveDeductionRuleList(1);
			 * List<HrPayRuleGrpParamRlt> activeAllowUsedInFormulaRuleParamMpgList =
			 * (List<HrPayRuleGrpParamRlt>)
			 * payComponentRuleGroupParamRltDAOImpl.getAllActiveRuleParamMpgList(
			 * PayrollConstants.PAYROLL_ALLOWANCE_TYPE,1); List<HrPayRuleGrpParamRlt>
			 * activeDeducUsedInFormulaRuleParamMpgList = (List<HrPayRuleGrpParamRlt>)
			 * payComponentRuleGroupParamRltDAOImpl.getAllActiveDeductionRuleParamMpgList(1)
			 * ;
			 * 
			 * List<HrPayAllowDedMst> ruleBasedAllowanceList = (List<HrPayAllowDedMst>)
			 * payComponentMasterDAOImpl.getPayActiveComponets(PayrollConstants.
			 * PAYROLL_ALLOWANCE_TYPE); logger.info(" ruleBasedAllowanceList "
			 * +ruleBasedAllowanceList.size()); List<HrPayAllowDedMst>
			 * ruleBasedDeductionList = (List<HrPayAllowDedMst>)
			 * payComponentMasterDAOImpl.getPayActiveComponets(PayrollConstants.
			 * PAYROLL_DEDUCTION_TYPE);
			 */

			List<HrPayRuleGrpMst> activeAllowanceRuleList = (List<HrPayRuleGrpMst>) inputMap
					.get("activeAllowanceRuleList");
			List<HrPayRuleGrpMst> activeDeductionRuleList = (List<HrPayRuleGrpMst>) inputMap
					.get("activeDeductionRuleList");
			List activeAllowRuleParamMpgList = (List) inputMap.get("activeAllowRuleParamMpgList");
			List activeDeducRuleParamMpgList = (List) inputMap.get("activeDeducRuleParamMpgList");
			List<HrPayRuleGrpMst> activeAllowanceUsedInFormulaRuleList = (List<HrPayRuleGrpMst>) inputMap
					.get("activeAllowanceUsedInFormulaRuleList");
			List<HrPayRuleGrpMst> activeDeductionUsedInFormulaRuleList = (List<HrPayRuleGrpMst>) inputMap
					.get("activeDeductionUsedInFormulaRuleList");

			List activeAllowUsedInFormulaRuleParamMpgList = (List) inputMap
					.get("activeAllowUsedInFormulaRuleParamMpgList");
			List activeDeducUsedInFormulaRuleParamMpgList = (List) inputMap
					.get("activeDeducUsedInFormulaRuleParamMpgList");
			List<HrPayAllowDedMst> ruleBasedAllowanceList = (List<HrPayAllowDedMst>) inputMap
					.get("ruleBasedAllowanceList");
			List<HrPayAllowDedMst> ruleBasedDeductionList = (List<HrPayAllowDedMst>) inputMap
					.get("ruleBasedDeductionList");

			Map<Long, List<AllwValCustomVO>> empAllowCompoMap = (Map) inputMap.get("empAllowCompoMap");
			;
			EmpPaybillVO hrEisOtherDtlsObj = (EmpPaybillVO) inputMap.get("hrEisOtherDtls");

			boolean isCentralDA = false;

			List<AllwValCustomVO> allowTypeMstComp = new ArrayList();
			if (empAllowCompoMap.containsKey(hrEisOtherDtlsObj.getEisEmpId())) {
				allowTypeMstComp = (List) (empAllowCompoMap.get(hrEisOtherDtlsObj.getEisEmpId()) != null
						? empAllowCompoMap.get(hrEisOtherDtlsObj.getEisEmpId())
						: new ArrayList());

				Iterator<AllwValCustomVO> it = allowTypeMstComp.iterator();
				while (it.hasNext()) {
					AllwValCustomVO allwValCustomVO = (AllwValCustomVO) it.next();
					if (allwValCustomVO.getAllwDedId() == 6) {
						isCentralDA = true;
						break;
					}
				}
			}

			// ended - Performance

			// List<HrPayAllowDedMst> ruleBasedAllowanceUsedInFormulaList =
			// (List<HrPayAllowDedMst>)
			// payComponentMasterDAOImpl.getPayCompUsedInFormula(PayrollConstants.PAYROLL_ALLOWANCE_TYPE,
			// payCompCode, langId);
			// List<HrPayAllowDedMst> ruleBasedDeductionUsedInFormulaList =
			// (List<HrPayAllowDedMst>)
			// payComponentMasterDAOImpl.getPayCompUsedInFormula(PayrollConstants.PAYROLL_DEDUCTION_TYPE,
			// payCompCode, langId);
			StringBuilder sbr = new StringBuilder(String.valueOf(payCompCode)).append("_");
			List<HrPayAllowDedMst> ruleBasedAllowanceUsedInFormulaList = (List<HrPayAllowDedMst>) inputMap
					.get(String.valueOf(new StringBuilder(sbr).append(PayrollConstants.PAYROLL_ALLOWANCE_TYPE)));
			List<HrPayAllowDedMst> ruleBasedDeductionUsedInFormulaList = (List<HrPayAllowDedMst>) inputMap
					.get(String.valueOf(new StringBuilder(sbr).append(PayrollConstants.PAYROLL_DEDUCTION_TYPE)));

			int compareLowValFlag = 2;
			int compareUprValFlag = 2;
			String[] formula;
			BigDecimal formulaAmount = BigDecimal.ZERO;
			BigDecimal finalAmount = BigDecimal.ZERO;

			BigDecimal finalGrossAmount = BigDecimal.ZERO;
			finalGrossAmount = finalGrossAmount.add(BigDecimal
					.valueOf(Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_BASICPAY).toString())));
			// finalGrossAmount=finalGrossAmount.add(BigDecimal.valueOf(Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_GRADEPAY).toString())));

			long lowerScaleParamLowLmt = 0;
			long upperScaleParamLowLmt = 0;
			long lowerScaleGP = 0;
			long upperScaleGP = 0;
			long lowerScaleIncrAmnt = 0;
			long upperScaleIncrAmnt = 0;

			HrEisScaleMst scaleMstLow = new HrEisScaleMst();
			HrEisScaleMst scaleMstUpr = new HrEisScaleMst();
			HrEisScaleMst scaleMstEmp = (HrEisScaleMst) inputMap.get("scaleMstEmp");

			HrEisScaleMstDao scaleMstDAO = new HrEisScaleMstDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
			String[] scaleCmprCol = { "scaleId" };// ,"cmnLanguageMst.langId"};
			Object[] empScaleColVal = {
					Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYSCALE).toString()) };// ,langId};

			// Performance Improvement - Manish
			// scaleMstEmp = scaleMstDAO.getListByColumnAndValue(scaleCmprCol,
			// empScaleColVal).get(0);

			// ended

			boolean ruleGrpIdFlag = false;
			// for calculating independent allowances
			if (ruleBasedAllowanceUsedInFormulaList != null && !ruleBasedAllowanceUsedInFormulaList.isEmpty()) {
				for (HrPayAllowDedMst payComp : ruleBasedAllowanceUsedInFormulaList) {
					formulaAmount = BigDecimal.ZERO;
					finalAmount = BigDecimal.ZERO;

					for (HrPayRuleGrpMst allowanceRuleMst : activeAllowanceUsedInFormulaRuleList) {

						ruleGrpIdFlag = false;
						if (allowanceRuleMst.getAllwDedCode() == payComp.getAllwDedCode()) {
							int activeAllowUsedInFormulaRuleParamMpglistSize = activeAllowUsedInFormulaRuleParamMpgList != null
									? activeAllowUsedInFormulaRuleParamMpgList.size()
									: 0;
							for (int counter = 0; counter < activeAllowUsedInFormulaRuleParamMpglistSize; counter++) {
								Object[] data = (Object[]) activeAllowUsedInFormulaRuleParamMpgList.get(counter);
								HrPayRuleGrpParamRlt allowRuleParamMpg = data[0] != null && data.length > 0
										? (HrPayRuleGrpParamRlt) data[0]
										: new HrPayRuleGrpParamRlt();
								long ruleGrpId = data[1] != null && data.length > 1 ? ((Long) data[1]).longValue() : 0;
								int ruleParamId = data[2] != null && data.length > 2 ? ((Integer) data[2]).intValue()
										: 0;
								int ruleParamType = data[3] != null && data.length > 3 ? ((Integer) data[3]).intValue()
										: 0;
								if (ruleGrpId == allowanceRuleMst.getRuleGrpId()) {

									if (ruleParamType == PayrollConstants.PAYROLL_PARAMETER_FIXED_TYPE) {
										if (allowRuleParamMpg.getParamValue()
												.equals(paramValueMap.get(ruleParamId).toString())) {
											ruleGrpIdFlag = true;
										} else {
											ruleGrpIdFlag = false;
											break;
										}
									} else if ((ruleParamType == PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
											&& (ruleParamId != PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
										compareLowValFlag = BigDecimal
												.valueOf(Long.parseLong(paramValueMap.get(ruleParamId).toString()))
												.compareTo(allowRuleParamMpg.getParamLowerValue());

										if (allowRuleParamMpg.getParamUpperValue().longValue() != -1) {
											compareUprValFlag = BigDecimal
													.valueOf(Long.parseLong(paramValueMap.get(ruleParamId).toString()))
													.compareTo(allowRuleParamMpg.getParamUpperValue());
										} else {
											compareUprValFlag = -1;
										}

										if (((compareLowValFlag == 0) || (compareLowValFlag == 1))
												&& ((compareUprValFlag == 0) || (compareUprValFlag == -1))) {
											ruleGrpIdFlag = true;
										} else {
											ruleGrpIdFlag = false;
											break;
										}
									} else if ((ruleParamType == PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
											&& (ruleParamId == PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
										if (allowRuleParamMpg.getParamLowerValue().longValue() != 0) {
											Object[] lowScaleColVal = {
													allowRuleParamMpg.getParamLowerValue().longValue(), langId };
											scaleMstLow = scaleMstDAO
													.getListByColumnAndValue(scaleCmprCol, lowScaleColVal).get(0);

											lowerScaleParamLowLmt = scaleMstLow.getScaleStartAmt();
											lowerScaleGP = scaleMstLow.getScaleGradePay() != 0
													? scaleMstLow.getScaleGradePay()
													: 0;
											lowerScaleIncrAmnt = scaleMstLow.getScaleIncrAmt();

										}
										if (allowRuleParamMpg.getParamUpperValue().longValue() != -1) {
											Object[] uprScaleColVal = {
													allowRuleParamMpg.getParamUpperValue().longValue(), langId };
											scaleMstUpr = scaleMstDAO
													.getListByColumnAndValue(scaleCmprCol, uprScaleColVal).get(0);

											upperScaleParamLowLmt = scaleMstUpr.getScaleStartAmt();
											upperScaleGP = scaleMstUpr.getScaleGradePay() != 0
													? scaleMstUpr.getScaleGradePay()
													: 0;
											upperScaleIncrAmnt = scaleMstUpr.getScaleIncrAmt();
										}

										if (lowerScaleParamLowLmt == 0) {
											lowerScaleParamLowLmt = scaleMstEmp.getScaleStartAmt();
										}
										if (upperScaleParamLowLmt == 0) {
											upperScaleParamLowLmt = scaleMstEmp.getScaleStartAmt();
										}

										if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString()
												.equals("2500341")) {

											if ((scaleMstEmp.getScaleStartAmt() > lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleStartAmt() < upperScaleParamLowLmt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleGradePay() >= lowerScaleGP)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == upperScaleParamLowLmt)
													&& (scaleMstEmp.getScaleGradePay() <= upperScaleGP)) {
												ruleGrpIdFlag = true;
											} else {
												ruleGrpIdFlag = false;
												break;
											}

										} else if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN)
												.toString().equals("2500340")) {
											if ((scaleMstEmp.getScaleStartAmt() > lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleStartAmt() < upperScaleParamLowLmt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleIncrAmt() >= lowerScaleIncrAmnt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == upperScaleParamLowLmt)
													&& (scaleMstEmp.getScaleIncrAmt() <= upperScaleIncrAmnt)) {
												ruleGrpIdFlag = true;
											} else {
												ruleGrpIdFlag = false;
												break;
											}
										}
									}
								}
							}

							if (ruleGrpIdFlag) {
								logger.info("out--allowanceRuleMst.getReturnType() " + allowanceRuleMst.getReturnType()
										+ "PayrollConstants.PAYROLL_RETURNTYPE_FORMULA is "
										+ PayrollConstants.PAYROLL_RETURNTYPE_FORMULA);
								if (allowanceRuleMst.getReturnType() == PayrollConstants.PAYROLL_RETURNTYPE_FIXED) {
									empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD by 229271 on 1/27/12 7:28 AM
									empSalTxn.setEmpId(orgEmp);
									empSalTxn.setAllwDedCode(allowanceRuleMst.getAllwDedCode());
									BigDecimal fixVal = allowanceRuleMst.getReturnValue();
									fixVal = fixVal.divide(BigDecimal.valueOf(1), 0, BigDecimal.ROUND_HALF_UP);
									empSalTxn.setAmount(fixVal);

									empSalaryMap.put(allowanceRuleMst.getAllwDedCode(), empSalTxn);

									finalGrossAmount = finalGrossAmount.add(allowanceRuleMst.getReturnValue());

									paramValueMap.put((int) allowanceRuleMst.getAllwDedCode(), fixVal);

								}

								else if (allowanceRuleMst
										.getReturnType() == PayrollConstants.PAYROLL_RETURNTYPE_FORMULA) {
									logger.info("(allowanceRuleMst.getReturnType() " + allowanceRuleMst.getReturnType()
											+ "PayrollConstants.PAYROLL_RETURNTYPE_FORMULA is "
											+ PayrollConstants.PAYROLL_RETURNTYPE_FORMULA);
									empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD by 229271 on 1/27/12 7:28 AM
									empSalTxn.setEmpId(orgEmp);
									empSalTxn.setAllwDedCode(allowanceRuleMst.getAllwDedCode());

									formula = allowanceRuleMst.getReturnFormula().split("P");

									for (int k = 0; k < formula.length; k++) {
										if ((Long.valueOf(formula[k]))
												.longValue() == PayrollConstants.PAYROLL_BASIC_PAY_CODE) {
											formulaAmount = formulaAmount.add(BigDecimal.valueOf(Long.parseLong(
													paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_BASICPAY)
															.toString())));
										} else if ((Long.valueOf(formula[k]))
												.longValue() == PayrollConstants.PAYROLL_GRADE_PAY_CODE) {
											formulaAmount = formulaAmount.add(BigDecimal.valueOf(Long.parseLong(
													paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_GRADEPAY)
															.toString())));
										}
									}
									finalAmount = allowanceRuleMst.getReturnValue().multiply(formulaAmount);
									finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
											BigDecimal.ROUND_HALF_UP);

									empSalTxn.setAmount(finalAmount);
									// Added by Ankit Thacker to store DA in MAP & to use it for DCPS Regular
									// Recovery
									if (allowanceRuleMst.getAllwDedCode() == 8) {
										/*
										 * if(Long.parseLong(inputMap.get("monthGiven").toString())< 11
										 * &&Long.parseLong(inputMap.get("yearGiven").toString()) <= 2012)
										 * formulaAmount=(new BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW));
										 */

										if (Long.parseLong(inputMap.get("yearGiven").toString()) <= 2012) {
											// logger.info("Year is 2012");
											if (Long.parseLong(inputMap.get("monthGiven").toString()) < 11) {
												finalAmount = (new BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW))
														.multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else {
												finalAmount = (new BigDecimal(72)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}

										} else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2013) {
											// logger.info("Year is 2013");
											logger.info("Month is::"
													+ Long.parseLong(inputMap.get("monthGiven").toString()));
											if (Long.parseLong(inputMap.get("monthGiven").toString()) < 5) {
												// logger.info("Month is less than May");
												finalAmount = (new BigDecimal(72)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else if (Long.parseLong(inputMap.get("monthGiven").toString()) >= 5
													&& Long.parseLong(inputMap.get("monthGiven").toString()) < 10) {
												// logger.info("Month is greater than May");
												finalAmount = (new BigDecimal(80)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}

											else {
												// logger.info("Month is greater than May");
												finalAmount = (new BigDecimal(90)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}
											// logger.info("Not in If Condition");

										}

										else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2014) {
											// logger.info("Year is 2014");

											// logger.info("Month is less than May");
											if (Long.parseLong(inputMap.get("monthGiven").toString()) < 5) {
												finalAmount = (new BigDecimal(90)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else {
												finalAmount = (new BigDecimal(100)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}

											// logger.info("Not in If Condition");

										}

										// Added By roshan on 10-feb 2015
										else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2015) {
											// logger.info("Year is 2015");

											// logger.info("Month is less than May");
											if (Long.parseLong(inputMap.get("monthGiven").toString()) == 1) {
												finalAmount = (new BigDecimal(100)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else if (Long.parseLong(inputMap.get("monthGiven").toString()) > 1
													&& Long.parseLong(inputMap.get("monthGiven").toString()) < 10) {
												finalAmount = (new BigDecimal(107)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else {
												finalAmount = (new BigDecimal(113)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}

											// logger.info("Not in If Condition");

										}

										else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2016) {

											if (Long.parseLong(inputMap.get("monthGiven").toString()) == 1) {
												finalAmount = (new BigDecimal(113)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else if (Long.parseLong(inputMap.get("monthGiven").toString()) >= 2
													&& Long.parseLong(inputMap.get("monthGiven").toString()) <= 8) {
												finalAmount = (new BigDecimal(119)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else {
												finalAmount = (new BigDecimal(125)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}

											/*
											 * else{ finalAmount=(new BigDecimal(119)).multiply(formulaAmount);
											 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
											 * BigDecimal.ROUND_HALF_UP); }
											 */

											// logger.info("Not in If Condition");

										}

										// added by saurabh for DA rate of 2017
										else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2017) {
											// logger.info("Year is 2017");
											if (Long.parseLong(inputMap.get("monthGiven").toString()) <= 3) {
												finalAmount = (new BigDecimal(125)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else if (Long.parseLong(inputMap.get("monthGiven").toString()) > 3
													&& Long.parseLong(inputMap.get("monthGiven").toString()) <= 8) {
												finalAmount = (new BigDecimal(132)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else {
												finalAmount = (new BigDecimal(136)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}

											/*
											 * else{ finalAmount=(new BigDecimal(119)).multiply(formulaAmount);
											 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
											 * BigDecimal.ROUND_HALF_UP); }
											 */

											// logger.info("Not in If Condition");

										} else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2018) {
											// logger.info("Year is 2018");
											if (Long.parseLong(inputMap.get("monthGiven").toString()) == 1) {
												finalAmount = (new BigDecimal(136)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}

											else if (Long.parseLong(inputMap.get("monthGiven").toString()) >= 1
													&& Long.parseLong(inputMap.get("monthGiven").toString()) <= 9) {
												finalAmount = (new BigDecimal(139)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											} else if (Long.parseLong(inputMap.get("monthGiven").toString()) >= 10
													&& Long.parseLong(inputMap.get("monthGiven").toString()) <= 12) {
												finalAmount = (new BigDecimal(142)).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}

											/*
											 * else{ finalAmount=(new BigDecimal(119)).multiply(formulaAmount);
											 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
											 * BigDecimal.ROUND_HALF_UP); }
											 */

											// logger.info("Not in If Condition");

										}

										// -----Added By Shivram 25012019
										else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2019) {
											if ((Long.parseLong(inputMap.get("monthGiven").toString()) >= 1L)
													&& (Long.parseLong(inputMap.get("monthGiven").toString()) <= 6L)) {
												finalAmount = new BigDecimal(142).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);
											} else if ((Long.parseLong(inputMap.get("monthGiven").toString()) >= 7L)
													&& (Long.parseLong(inputMap.get("monthGiven").toString()) <= 11L)) {
												finalAmount = new BigDecimal(154).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);
											} else if (Long.parseLong(inputMap.get("monthGiven").toString()) == 12L) {
												finalAmount = new BigDecimal(164).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);
											}
										} else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2020L) {
											if (Long.parseLong(inputMap.get("monthGiven").toString()) >= 1L
													&& (Long.parseLong(inputMap.get("monthGiven").toString()) <= 12L)) {
												finalAmount = new BigDecimal(164).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);

											}

										} else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2021L) {
											if ((Long.parseLong(inputMap.get("monthGiven").toString()) >= 1L)
													&& (Long.parseLong(inputMap.get("monthGiven").toString()) <= 9L)) {
												finalAmount = new BigDecimal(164).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);
											} else if ((Long.parseLong(inputMap.get("monthGiven").toString()) >= 10L)
													&& (Long.parseLong(inputMap.get("monthGiven").toString()) <= 12L)) {
												finalAmount = new BigDecimal(189).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);
											}
										} else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2022L) {
											if ((Long.parseLong(inputMap.get("monthGiven").toString()) >= 1L)
													&& (Long.parseLong(inputMap.get("monthGiven").toString()) <= 2L)) {
												finalAmount = new BigDecimal(189).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);

											} else if ((Long.parseLong(inputMap.get("monthGiven").toString()) >= 3L)
													&& (Long.parseLong(inputMap.get("monthGiven").toString()) <= 7L)) {
												finalAmount = new BigDecimal(196).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);

											} else if ((Long.parseLong(inputMap.get("monthGiven").toString()) >= 8L)) {
												finalAmount = new BigDecimal(203).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);

											}
										} else if (Long.parseLong(inputMap.get("yearGiven").toString()) == 2023L) {
											if (Long.parseLong(inputMap.get("monthGiven").toString()) >= 1L &&
													Long.parseLong(inputMap.get("monthGiven").toString()) < 6 ) {
												finalAmount = new BigDecimal(212).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);

											}else if (Long.parseLong(inputMap.get("monthGiven").toString()) >= 6L) {
												finalAmount = new BigDecimal(221).multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);

											}
										}

										paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DEARNESS_ALLOWANCE,
												finalAmount);
										logger.info("finalAmount vivek : " + finalAmount + "formulaAmount is "
												+ formulaAmount);

										empSalaryMap.put(allowanceRuleMst.getAllwDedCode(), empSalTxn);

										logger.info("Id is : " + allowanceRuleMst.getAllwDedCode() + " Amt is "
												+ finalAmount);

										finalGrossAmount = finalGrossAmount.add(finalAmount);

										// paramValueMap.put((int)allowanceRuleMst.getAllwDedCode(), finalAmount);
										if ((int) allowanceRuleMst
												.getAllwDedCode() == PayrollConstants.PAYROLL_PARAMID_DEARNESS_PAY) {
											BigDecimal tempBasic = new BigDecimal(finalAmount.doubleValue());
											tempBasic = tempBasic.add(BigDecimal.valueOf(Long.valueOf(paramValueMap
													.get(PayrollConstants.PAYROLL_PARAMID_BASICPAY).toString())));
											logger.info("tempBasic is " + tempBasic);

											paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_BASICPAY, tempBasic);
											paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DEARNESS_PAY,
													finalAmount);
										} else
											paramValueMap.put((int) allowanceRuleMst.getAllwDedCode(), finalAmount);
									}
									break;
								}
							}
						}

					} // calculation of independent allowances ends
				}

				// **********calculation of dependent allowances begins
				ruleGrpIdFlag = false;
				for (HrPayAllowDedMst payComp : ruleBasedAllowanceList) {
					formulaAmount = BigDecimal.ZERO;
					finalAmount = BigDecimal.ZERO;
					logger.info("=======getAllowDedName========" + payComp.getAllowDedName());
					logger.info("=======getAllowDedName========" + payComp.getAllowDedName());
					for (HrPayRuleGrpMst allowanceRuleMst : activeAllowanceRuleList) {

						ruleGrpIdFlag = false;
						if (allowanceRuleMst.getAllwDedCode() == payComp.getAllwDedCode()) {
							int activeAllowRuleParamMpglistSize = activeAllowRuleParamMpgList != null
									? activeAllowRuleParamMpgList.size()
									: 0;
							for (int counter = 0; counter < activeAllowRuleParamMpglistSize; counter++) {
								Object[] data = (Object[]) activeAllowRuleParamMpgList.get(counter);
								HrPayRuleGrpParamRlt allowRuleParamMpg = data[0] != null && data.length > 0
										? (HrPayRuleGrpParamRlt) data[0]
										: new HrPayRuleGrpParamRlt();
								long ruleGrpId = data[1] != null && data.length > 1 ? ((Long) data[1]).longValue() : 0;
								int ruleParamId = data[2] != null && data.length > 2 ? ((Integer) data[2]).intValue()
										: 0;
								int ruleParamType = data[3] != null && data.length > 3 ? ((Integer) data[3]).intValue()
										: 0;
								if (ruleGrpId == allowanceRuleMst.getRuleGrpId()) {

									logger.info("Rule Grp Id is " + allowanceRuleMst.getRuleGrpId()
											+ " parameter type is " + ruleParamType + " param id is " + ruleParamId);
									if (ruleParamType == PayrollConstants.PAYROLL_PARAMETER_FIXED_TYPE) {
										logger.info("Jay Bhjolenath " + payComp.getAllowDedName());
										if (allowRuleParamMpg.getParamValue()
												.equals(paramValueMap.get(ruleParamId) != null
														? paramValueMap.get(ruleParamId).toString()
														: 0)) {
											ruleGrpIdFlag = true;
										} else {
											ruleGrpIdFlag = false;
											break;
										}
									} else if ((ruleParamType == PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
											&& (ruleParamId != PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
										logger.info("Hari ommmmmmmmm " + payComp.getAllowDedName());
										compareLowValFlag = BigDecimal
												.valueOf(Long.parseLong(paramValueMap.get(ruleParamId).toString()))
												.compareTo(allowRuleParamMpg.getParamLowerValue());
										logger.info("Hari ommmmmmmmm 1 " + compareLowValFlag);
										if (allowRuleParamMpg.getParamUpperValue().longValue() != -1) {

											compareUprValFlag = BigDecimal
													.valueOf(Long.parseLong(paramValueMap.get(ruleParamId).toString()))
													.compareTo(allowRuleParamMpg.getParamUpperValue());
											logger.info("Hari ommmmmmmmm moddle stage " + compareUprValFlag);
										} else {
											compareUprValFlag = -1;
										}

										logger.info("Hari ommmmmmmmm 2 " + compareUprValFlag);
										if (((compareLowValFlag == 0) || (compareLowValFlag == 1))
												&& ((compareUprValFlag == 0) || (compareUprValFlag == -1))) {
											ruleGrpIdFlag = true;
										} else {
											ruleGrpIdFlag = false;
											break;
										}
										logger.info("Hari ommmmmmmmm  Flag is " + ruleGrpIdFlag + "bcz upper Flag  "
												+ compareUprValFlag + " lowerFlag =" + compareLowValFlag);
									} else if ((ruleParamType == PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
											&& (ruleParamId == PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
										if (allowRuleParamMpg.getParamLowerValue().longValue() != 0) {
											Object[] lowScaleColVal = {
													allowRuleParamMpg.getParamLowerValue().longValue(), langId };
											scaleMstLow = scaleMstDAO
													.getListByColumnAndValue(scaleCmprCol, lowScaleColVal).get(0);

											lowerScaleParamLowLmt = scaleMstLow.getScaleStartAmt();
											lowerScaleGP = scaleMstLow.getScaleGradePay() != 0
													? scaleMstLow.getScaleGradePay()
													: 0;
											lowerScaleIncrAmnt = scaleMstLow.getScaleIncrAmt();

										}
										if (allowRuleParamMpg.getParamUpperValue().longValue() != -1) {
											Object[] uprScaleColVal = {
													allowRuleParamMpg.getParamUpperValue().longValue(), langId };
											scaleMstUpr = scaleMstDAO
													.getListByColumnAndValue(scaleCmprCol, uprScaleColVal).get(0);

											upperScaleParamLowLmt = scaleMstUpr.getScaleStartAmt();
											upperScaleGP = scaleMstUpr.getScaleGradePay() != 0
													? scaleMstUpr.getScaleGradePay()
													: 0;
											upperScaleIncrAmnt = scaleMstUpr.getScaleIncrAmt();
										}

										if (lowerScaleParamLowLmt == 0) {
											lowerScaleParamLowLmt = scaleMstEmp.getScaleStartAmt();
										}
										if (upperScaleParamLowLmt == 0) {
											upperScaleParamLowLmt = scaleMstEmp.getScaleStartAmt();
										}

										if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString()
												.equals("2500341")) {

											if ((scaleMstEmp.getScaleStartAmt() > lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleStartAmt() < upperScaleParamLowLmt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleGradePay() >= lowerScaleGP)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == upperScaleParamLowLmt)
													&& (scaleMstEmp.getScaleGradePay() <= upperScaleGP)) {
												ruleGrpIdFlag = true;
											} else {
												ruleGrpIdFlag = false;
												break;
											}

										} else if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN)
												.toString().equals("2500340")) {
											if ((scaleMstEmp.getScaleStartAmt() > lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleStartAmt() < upperScaleParamLowLmt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleIncrAmt() >= lowerScaleIncrAmnt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == upperScaleParamLowLmt)
													&& (scaleMstEmp.getScaleIncrAmt() <= upperScaleIncrAmnt)) {
												ruleGrpIdFlag = true;
											} else {
												ruleGrpIdFlag = false;
												break;
											}
										}
										/*
										 * //added by manish else
										 * if(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
										 * equals("2500346")) {
										 * if((scaleMstEmp.getScaleStartAmt()>lowerScaleParamLowLmt) &&
										 * (scaleMstEmp.getScaleStartAmt()<upperScaleParamLowLmt)) { ruleGrpIdFlag=true;
										 * } else if((scaleMstEmp.getScaleStartAmt()==lowerScaleParamLowLmt) &&
										 * (scaleMstEmp.getScaleIncrAmt()>=lowerScaleIncrAmnt)) { ruleGrpIdFlag=true; }
										 * else if((scaleMstEmp.getScaleStartAmt()==upperScaleParamLowLmt) &&
										 * (scaleMstEmp.getScaleIncrAmt()<=upperScaleIncrAmnt)) { ruleGrpIdFlag=true; }
										 * else { ruleGrpIdFlag=false; break; } } //Ended by manish
										 */ }
								}
							}

							if (ruleGrpIdFlag) {

								logger.info("=======getAllwDedCode========" + allowanceRuleMst.getAllwDedCode());
								logger.info("=======getRuleGrpId========" + allowanceRuleMst.getRuleGrpId());
								if (allowanceRuleMst.getReturnType() == PayrollConstants.PAYROLL_RETURNTYPE_FIXED) {
									empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD by 229271 on 1/27/12 7:28 AM

									empSalTxn.setEmpId(orgEmp);
									empSalTxn.setAllwDedCode(allowanceRuleMst.getAllwDedCode());
									// added by roshan
									if (allowanceRuleMst.getAllwDedCode() == 15) {
										logger.info("hi emp id is" + hrEisOtherDtlsObj.getEisEmpId());
										logger.info(
												"hii this is emp Idfggd by roshan" + hrEisOtherDtlsObj.getEisEmpId());
										Long empIDForTA = hrEisOtherDtlsObj.getEisEmpId();
										PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class,
												serv.getSessionFactory());
										// Long oldBasic=payBillDAO.getOldBasic(empIDForTA);
										// Long newBasic=payBillDAO.getNewBasic(empIDForTA);
										Long percentageOfBasic = payBillDAO.getPerOFBAsic(empIDForTA);
										logger.info("hhiii the percentageOfBasic" + percentageOfBasic);
										// logger.info("hhiii the newbASIC IS"+newBasic);
										// logger.info("hhiii the oldBasic IS"+oldBasic);
										logger.info("hhiii the PERCENTAGE OF old BASIC IS" + percentageOfBasic);
										BigDecimal ta = allowanceRuleMst.getReturnValue();
										String travel = ta.toString();
										// Double oBasic=Double.valueOf(oldBasic);
										// Double nBasic=Double.valueOf(newBasic);
										Double perOfBasic = Double.valueOf(percentageOfBasic);
										logger.info("perOfBasic" + perOfBasic);
										Double travelAllw = Double.parseDouble(travel);
										logger.info("hii this is roshan amount by roshan" + ta);
										logger.info("travelAllw is by roshan" + travelAllw);
										BigDecimal travelAllowance = null;
										if (perOfBasic != null) {
											Double ratio = perOfBasic / 100;
											logger.info("the ratio by roshan is" + ratio);
											Double travelAllwnce = ratio * travelAllw;
											logger.info("the travelAllwnce byjjj roshan is" + travelAllwnce);
											travelAllowance = BigDecimal.valueOf(travelAllwnce);
											travelAllowance = travelAllowance.divide(BigDecimal.valueOf(1), 0,
													BigDecimal.ROUND_UP);
										}
										// commented by roshan for city class mapping
										// uncommented by poonam for leave sanction
										long effectiveTADay = 0;
										Double effectivePerDay = 0.0;
										if (Long.parseLong(inputMap.get("monthGiven").toString()) == 5
												|| Long.parseLong(inputMap.get("monthGiven").toString()) == 6) {
											SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
											SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
											List leaveDtls = payBillDAO.getLeaveDtls(empIDForTA,
													Long.parseLong(inputMap.get("yearGiven").toString()));
											String fromdate = null;
											String toDate = null;
											String designation = null;
											String ddo_code = null;
											if (leaveDtls != null && leaveDtls.size() > 0) {
												Iterator IT = leaveDtls.iterator();

												Object[] lObj = null;
												while (IT.hasNext()) {
													lObj = (Object[]) IT.next();
													logger.info("obj len :::" + lObj.length);
													if (lObj[0] != null)
														ddo_code = lObj[0].toString();
													if (lObj[1] != null)
														fromdate = lObj[1].toString();
													if (lObj[2] != null)
														toDate = lObj[2].toString();
													if (lObj[3] != null)
														designation = lObj[3].toString();
												}

												logger.info("ddo_code is *************" + ddo_code);
												logger.info("fromdate is *************" + fromdate);
												logger.info("todate is *************" + toDate);
												logger.info("Designation is************" + designation);
												String payMonth = inputMap.get("monthGiven").toString();
												logger.info("payMonth is************" + payMonth);
												String frmDt = null;
												String toDt = null;
												if (fromdate != null || fromdate != "") {
													frmDt = sdf1.format(sdf2.parse(fromdate));
												}

												if (toDate != null || toDate != "") {
													toDt = sdf1.format(sdf2.parse(toDate));
												}
												logger.info("Form date is **************************" + frmDt);
												logger.info("toDt date is **************************" + toDt);
												String[] frmDtArr = frmDt.split("-");
												String[] toDtArr = toDt.split("-");

												logger.info("ddo_code.substring(0,2) is************"
														+ ddo_code.substring(0, 2));

												logger.info("designation::::::::::" + designation);
												// designation for sanction leave
												if (!designation.equals("99100001130")
														&& !designation.equals("99100001307")
														&& !designation.equals("101111")
														&& !designation.equals("104725")
														&& !designation.equals("104009")
														&& !designation.equals("99100001548")
														&& !designation.equals("102365")
														&& !designation.equals("103175")
														&& !designation.equals("99100001545")
														&& !designation.equals("102083")
														&& !designation.equals("99100001351")
														&& !designation.equals("103099")
														&& !designation.equals("103103")
														&& !designation.equals("101002"))

												{
													// logger.info("in if here not TA deduction");
													travelAllowance = travelAllowance;
												} else {
													// logger.info("Entering into calculating TA SECTION");
													if (Long.parseLong(payMonth) == Long.parseLong(frmDtArr[1])
															&& Long.parseLong(payMonth) < Long.parseLong(toDtArr[1])) {
														effectiveTADay = Long.parseLong(frmDtArr[0]) - 1;
														logger.info("effectiveTADay*************" + effectiveTADay);
													}

													if (Long.parseLong(payMonth) == Long.parseLong(toDtArr[1])
															&& Long.parseLong(payMonth) > Long.parseLong(frmDtArr[1])) {
														if (Long.parseLong(payMonth) == 6)
															effectiveTADay = 30 - (Long.parseLong(toDtArr[0]));
														logger.info("effectiveTADay*************" + effectiveTADay);
													}

													if (Long.parseLong(payMonth) == Long.parseLong(toDtArr[1]) && Long
															.parseLong(payMonth) == Long.parseLong(frmDtArr[1])) {
														if (Long.parseLong(payMonth) == 6)
															effectiveTADay = (29 + Long.parseLong(frmDtArr[0]))
																	- (Long.parseLong(toDtArr[0]));
														else if (Long.parseLong(payMonth) == 5)
															effectiveTADay = (30 + Long.parseLong(frmDtArr[0]))
																	- (Long.parseLong(toDtArr[0]));
														logger.info("effectiveTADay*************" + effectiveTADay);
													}

													// logger.info("The TA FINAL CODE IS SATRTED");
													if (effectiveTADay == 0) {
														travelAllowance = null;
													} else {
														if (Long.parseLong(payMonth) == 6)
															effectivePerDay = Double.valueOf(30)
																	/ Double.valueOf(effectiveTADay);
														if (Long.parseLong(payMonth) == 5)
															effectivePerDay = Double.valueOf(31)
																	/ Double.valueOf(effectiveTADay);
														logger.info("Effective perDay of travel allowance is"
																+ effectivePerDay);
														logger.info("effectiveTADay in percenatge is*************"
																+ effectiveTADay);
														logger.info("The value of the travel allowance is as follows:"
																+ travelAllowance);
														travelAllowance = travelAllowance.divide(
																BigDecimal.valueOf(effectivePerDay), 0,
																BigDecimal.ROUND_UP);
														logger.info(
																"travelAllowance is after calculation of summer leave is ****"
																		+ travelAllowance);
													}
												}
											}

										}
										// uncomment by poonam for leave sanction
										logger.info("travelAllowance::::::::" + travelAllowance);
										empSalTxn.setAmount(travelAllowance);
									} else {
										logger.info("allowanceRuleMst.getReturnValue() value by roshan *********"
												+ allowanceRuleMst.getReturnValue());
										empSalTxn.setAmount(allowanceRuleMst.getReturnValue());
									}
									// end by roshan
									// commeneted by roshan
									// empSalTxn.setAmount(allowanceRuleMst.getReturnValue());
									empSalaryMap.put(allowanceRuleMst.getAllwDedCode(), empSalTxn);

									finalGrossAmount = finalGrossAmount.add(allowanceRuleMst.getReturnValue());

								} else if (allowanceRuleMst
										.getReturnType() == PayrollConstants.PAYROLL_RETURNTYPE_FORMULA) {
									empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD by 229271 on 1/27/12 7:28 AM
									empSalTxn.setEmpId(orgEmp);
									logger.info("Humm Moj Padi Gay HO ....." + allowanceRuleMst.getAllwDedCode());

									empSalTxn.setAllwDedCode(allowanceRuleMst.getAllwDedCode());

									formula = allowanceRuleMst.getReturnFormula().split("P");

									for (int k = 0; k < formula.length; k++) {
										// if(Long.parseLong(formula[k])==PayrollConstants.PAYROLL_BASIC_PAY_CODE)
										// {
										// formulaAmount=formulaAmount.add(
										// BigDecimal.valueOf(Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_BASICPAY).toString())));
										// }
										// else if(Long.parseLong(formula[k])==PayrollConstants.PAYROLL_GRADE_PAY_CODE)
										// {
										// formulaAmount=formulaAmount.add(BigDecimal.valueOf(Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_GRADEPAY).toString())));
										// }

										if (paramValueMap.containsKey(Integer.parseInt(formula[k]))) {
											formulaAmount = formulaAmount.add(BigDecimal.valueOf(Long.parseLong(
													paramValueMap.get(Integer.parseInt(formula[k])).toString())));

										}
									}
									logger.info("formulla Amount is " + formulaAmount + " param 1p  "
											+ paramValueMap.get("5") + "param 2p " + paramValueMap.get("55"));
									finalAmount = allowanceRuleMst.getReturnValue().multiply(formulaAmount);
									finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
											BigDecimal.ROUND_HALF_UP);
									if (isCentralDA == true) {
										if (allowanceRuleMst.getAllwDedCode() == 6) {
											paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DEARNESS_ALLOWANCE,
													finalAmount);
										}
									} else if (allowanceRuleMst.getAllwDedCode() == 8) {// added by Mum dev for DA

										if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString()
												.equals("2500341")) {
											// logger.info("Paycommission is 3500341");

											// Added by Samadhan on 25 july for DA Rate Entry
											long month1, year1;
											month1 = Long.parseLong(inputMap.get("monthGiven").toString());
											logger.info("sAm month selected: " + month1);
											year1 = Long.parseLong(inputMap.get("yearGiven").toString());
											logger.info("sAm year selected: " + year1);
											// Ended by samadhan on 25 july for DA rate entry.

											// COmmented by Samadhan on 25 july for DA Rate Entry
											/*
											 * if(Long.parseLong(inputMap.get("yearGiven").toString()) <= 2012 ){
											 * logger.info("Year is 2012");
											 * if(Long.parseLong(inputMap.get("monthGiven").toString())< 11){
											 * finalAmount=(new
											 * BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW)).multiply(formulaAmount)
											 * ; finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
											 * BigDecimal.ROUND_HALF_UP); } else{ finalAmount=(new
											 * BigDecimal(72)).multiply(formulaAmount);
											 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
											 * BigDecimal.ROUND_HALF_UP); }
											 * 
											 * 
											 * } else if(Long.parseLong(inputMap.get("yearGiven").toString()) == 2013 )
											 * { logger.info("Year is 2013");
											 * if(Long.parseLong(inputMap.get("monthGiven").toString())< 5){
											 * finalAmount=(new BigDecimal(72)).multiply(formulaAmount);
											 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
											 * BigDecimal.ROUND_HALF_UP); } else{ finalAmount=(new
											 * BigDecimal(80)).multiply(formulaAmount);
											 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
											 * BigDecimal.ROUND_HALF_UP); }
											 * 
											 * }
											 */

										} else if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN)
												.toString().equals("2500340")) {
											if (Long.parseLong(inputMap.get("monthGiven").toString()) < 12
													&& Long.parseLong(inputMap.get("yearGiven").toString()) <= 2012) {
												// formulaAmount=(new BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW));

												finalAmount = (new BigDecimal(
														PayrollConstants.OLD_FIFTH_PAYCOMMISION_DA_RATE))
																.multiply(formulaAmount);
												finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
														BigDecimal.ROUND_HALF_UP);
											}
										}

										/*
										 * if(Long.parseLong(inputMap.get("monthGiven").toString())< 11
										 * &&Long.parseLong(inputMap.get("yearGiven").toString()) <= 2012 &&
										 * paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
										 * equals("2500341")){ //formulaAmount=(new
										 * BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW)); finalAmount=(new
										 * BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW)).multiply(formulaAmount);
										 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
										 * BigDecimal.ROUND_HALF_UP); }
										 * if(Long.parseLong(inputMap.get("monthGiven").toString())>= 11
										 * &&Long.parseLong(inputMap.get("yearGiven").toString()) <= 2012 &&
										 * paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
										 * equals("2500341")){ //formulaAmount=(new
										 * BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW)); finalAmount=(new
										 * BigDecimal(72)).multiply(formulaAmount);
										 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
										 * BigDecimal.ROUND_HALF_UP); }
										 * if(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
										 * equals("2500340") && Long.parseLong(inputMap.get("monthGiven").toString())<
										 * 12 &&Long.parseLong(inputMap.get("yearGiven").toString()) <= 2012){
										 * //formulaAmount=(new BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW));
										 * 
										 * finalAmount=(new
										 * BigDecimal(PayrollConstants.OLD_FIFTH_PAYCOMMISION_DA_RATE)).multiply(
										 * formulaAmount); finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
										 * BigDecimal.ROUND_HALF_UP); }
										 * if(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
										 * equals("2500341") && Long.parseLong(inputMap.get("monthGiven").toString())< 5
										 * &&Long.parseLong(inputMap.get("yearGiven").toString()) <= 2013){
										 * //formulaAmount=(new BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW));
										 * 
										 * finalAmount=(new BigDecimal(72)).multiply(formulaAmount);
										 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
										 * BigDecimal.ROUND_HALF_UP); }
										 */
										/*
										 * if(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
										 * equals("2500340") && Long.parseLong(inputMap.get("monthGiven").toString())> 4
										 * &&Long.parseLong(inputMap.get("yearGiven").toString()) >= 2013){
										 * //formulaAmount=(new BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW));
										 * 
										 * finalAmount=(new BigDecimal(72)).multiply(formulaAmount);
										 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100), 0,
										 * BigDecimal.ROUND_HALF_UP); }
										 */
										logger.info("finalAmount vivek : " + finalAmount + "formulaAmount is "
												+ formulaAmount);

										paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DEARNESS_ALLOWANCE,
												finalAmount);
									}
									if (allowanceRuleMst.getAllwDedCode() == 9) {
										if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString()
												.equals("2500340")
												&& Long.parseLong(inputMap.get("monthGiven").toString()) < 12
												&& Long.parseLong(inputMap.get("yearGiven").toString()) <= 2012) {
											// formulaAmount=(new BigDecimal(PayrollConstants.PAYROLL_DA_RATE_NEW));

											finalAmount = (new BigDecimal(
													PayrollConstants.OLD_FIFTH_PAYCOMMISION_DA_RATE))
															.multiply(formulaAmount);
											finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0,
													BigDecimal.ROUND_HALF_UP);
										}
										paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DEARNESS_PAY, finalAmount);
									}
									logger.info(
											"allowanceRuleMst.getAllwDedCode()=" + allowanceRuleMst.getAllwDedCode());
									/* Added By Shivram 15052019 */
									long basicForSvnPC = 0;
									long basicForSixPC = 0;
									if (allowanceRuleMst.getAllwDedCode() == 207) {
										if (((Long.parseLong(inputMap.get("yearGiven").toString()) == 2016)
												&& (Long.parseLong(inputMap.get("monthGiven").toString()) >= 7))
												|| (Long.parseLong(inputMap.get("yearGiven").toString()) >= 2017)) {
											logger.info("final month---"
													+ Long.parseLong(inputMap.get("monthGiven").toString()) + "--year--"
													+ Long.parseLong(inputMap.get("yearGiven").toString()));

											LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,
													serv.getSessionFactory());
											basicForSvnPC = dao.getRevisedSvnPCBasic(hrEisOtherDtlsObj.getEisEmpId())
													.longValue();
											BigDecimal basicForSvnPCDec = new BigDecimal(basicForSvnPC);
											if (basicForSvnPC > 0) {
												if ((Long.parseLong(inputMap.get("yearGiven").toString()) == 2017L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) <= 9L)) {
													finalAmount = new BigDecimal(4).multiply(basicForSvnPCDec);
												} else if (((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2017L)
														&& (Long.parseLong(inputMap.get("monthGiven").toString()) > 9L))
														|| ((Long.parseLong(
																inputMap.get("yearGiven").toString()) == 2018L)
																&& (Long.parseLong(
																		inputMap.get("monthGiven").toString()) < 3L))) {
													finalAmount = new BigDecimal(5).multiply(basicForSvnPCDec);
												} else if ((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2018L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) <= 10L)) {
													finalAmount = new BigDecimal(7).multiply(basicForSvnPCDec);
												} else if (((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2018L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) >= 11L))
														|| ((Long.parseLong(
																inputMap.get("yearGiven").toString()) == 2019L)
																&& (Long.parseLong(inputMap.get("monthGiven")
																		.toString()) <= 6L))) {
													finalAmount = new BigDecimal(9).multiply(basicForSvnPCDec);

													logger.info("finalAmount ---------------- " + finalAmount);
												} else if (((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2019L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) >= 7L))
														|| ((Long.parseLong(
																inputMap.get("yearGiven").toString()) == 2019L)
																&& (Long.parseLong(inputMap.get("monthGiven")
																		.toString()) <= 11L))) {
													finalAmount = new BigDecimal(12).multiply(basicForSvnPCDec);
													logger.info("finalAmount ---------------- " + finalAmount);
												} else if ((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2019L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) == 12L)) {
													finalAmount = new BigDecimal(17).multiply(basicForSvnPCDec);
													logger.info("finalAmount ---------------- " + finalAmount);
												} else if ((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2020L)
														&& (Long.parseLong(inputMap.get("monthGiven").toString()) >= 1L
																|| ((Long.parseLong(
																		inputMap.get("yearGiven").toString()) == 2020L)
																		&& (Long.parseLong(inputMap.get("monthGiven")
																				.toString()) <= 12L)))) {
													finalAmount = new BigDecimal(17).multiply(basicForSvnPCDec);
													logger.info("finalAmount ---------------- " + finalAmount);
												} else if ((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2021L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) >= 1L)) {
													finalAmount = new BigDecimal(17).multiply(basicForSvnPCDec);
													logger.info("finalAmount ---------------- " + finalAmount);
												} else if ((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2022L)
														&& (Long.parseLong(inputMap.get("monthGiven").toString()) >= 1L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) <= 2L)) {
													finalAmount = new BigDecimal(17).multiply(basicForSvnPCDec);
													logger.info("finalAmount ---------------- " + finalAmount);
												} else if ((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2022L)
														&& (Long.parseLong(inputMap.get("monthGiven").toString()) >= 3L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) <= 7L)) {
													finalAmount = new BigDecimal(31).multiply(basicForSvnPCDec);
													logger.info("finalAmount ---------------- " + finalAmount);
												} else if ((Long
														.parseLong(inputMap.get("yearGiven").toString()) == 2022L)
														&& (Long.parseLong(inputMap.get("monthGiven").toString()) >= 8L)
														&& (Long.parseLong(
																inputMap.get("monthGiven").toString()) <= 12L)) {
													finalAmount = new BigDecimal(34).multiply(basicForSvnPCDec);
													logger.info("finalAmount ---------------- " + finalAmount);
												} else if ((Long.parseLong(inputMap.get("yearGiven").toString()) == 2023L) && (Long.parseLong(inputMap.get("monthGiven").toString()) >= 1L && Long.parseLong(inputMap.get("monthGiven").toString()) < 6L)) {
													finalAmount = new BigDecimal(38).multiply(basicForSvnPCDec);
													logger.info("finalAmount ---------------- " + finalAmount);
												}
												else if ((Long.parseLong(inputMap.get("yearGiven").toString()) == 2023L) && (Long.parseLong(inputMap.get("monthGiven").toString()) >= 6L)) {
														finalAmount = new BigDecimal(42).multiply(basicForSvnPCDec);
														logger.info("finalAmount ---------------- " + finalAmount);
													}

												finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0, 4);
											}

											paramValueMap.put(Integer.valueOf(7), finalAmount);
										}
									}

									/* Ended By Shivram */
									/***Tejashree**/
									BigDecimal npsAllowance = new BigDecimal(0);
									if (allowanceRuleMst.getAllwDedCode() == 208) {
										
											if (((Long.parseLong(inputMap.get("yearGiven").toString()) >= 2019)
													&& (Long.parseLong(inputMap.get("monthGiven").toString()) >= 4)) && (Long.parseLong(inputMap.get("yearGiven").toString()) >= 2020)) {
												
												LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,
														serv.getSessionFactory());
												basicForSvnPC = dao.getRevisedSvnPCBasic(hrEisOtherDtlsObj.getEisEmpId())
														.longValue();
												
												BigDecimal tempval=BigDecimal.ZERO;
												BigDecimal tempval2=BigDecimal.ZERO;
												
												
												if(basicForSvnPC >0) {
												tempval = new BigDecimal (basicForSvnPC).add(BigDecimal.valueOf(
														Long.parseLong(paramValueMap.get(Integer.valueOf(7)).toString())));
												
												
												tempval2 = new BigDecimal(14).multiply(tempval);
												finalAmount = tempval2.divide(BigDecimal.valueOf(100L), 0,
														BigDecimal.ROUND_UP);
												
												}else {
													
													basicForSixPC = dao.getRevisedSixPCBasic(hrEisOtherDtlsObj.getEisEmpId())
															.longValue();
													tempval = new BigDecimal (basicForSixPC).add(BigDecimal.valueOf(
															Long.parseLong(paramValueMap.get(Integer.valueOf(8)).toString())));
													
													tempval2 = new BigDecimal(14).multiply(tempval);
													finalAmount = tempval2.divide(BigDecimal.valueOf(100L), 0,
															BigDecimal.ROUND_UP);
												}
											}
									}
									/***Tejashree**/
									logger.info("allowanceRuleMst.getAllwDedCode()==============="
											+ allowanceRuleMst.getAllwDedCode());
									logger.info("Final amount is " + finalAmount);
									empSalTxn.setAmount(finalAmount);

									empSalaryMap.put(allowanceRuleMst.getAllwDedCode(), empSalTxn);

									finalGrossAmount = finalGrossAmount.add(finalAmount);
								}
								break;
							}
						}
					}

				}

				// *********calculation of dependent allowances ends

				// paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GROSSPAY,
				// finalGrossAmount.ROUND_HALF_UP);

				// System.out.println("Gross Amount is "+finalGrossAmount.ROUND_HALF_UP);
				// calculation of independent deductions starts
				/*
				 * ruleGrpIdFlag=false; if(ruleBasedDeductionUsedInFormulaList != null &&
				 * !ruleBasedDeductionUsedInFormulaList.isEmpty()){ for(HrPayAllowDedMst
				 * payComp:ruleBasedDeductionUsedInFormulaList) { formulaAmount=BigDecimal.ZERO;
				 * finalAmount=BigDecimal.ZERO;
				 * 
				 * for(HrPayRuleGrpMst deductionRuleMst:activeDeductionUsedInFormulaRuleList) {
				 * 
				 * ruleGrpIdFlag=false;
				 * if(deductionRuleMst.getAllwDedCode()==payComp.getAllwDedCode()) { int
				 * activeDeducUsedInFormulaRuleParamMpglistSize =
				 * activeDeducUsedInFormulaRuleParamMpgList != null ?
				 * activeDeducUsedInFormulaRuleParamMpgList.size() : 0; for(int
				 * counter=0;counter < activeDeducUsedInFormulaRuleParamMpglistSize ; counter++)
				 * { Object [] data = (Object[]
				 * )activeDeducUsedInFormulaRuleParamMpgList.get(counter); HrPayRuleGrpParamRlt
				 * deductionRuleParamMpg = data[0] != null && data.length > 0 ?
				 * (HrPayRuleGrpParamRlt)data[0]: new HrPayRuleGrpParamRlt() ; long ruleGrpId =
				 * data[1] != null && data.length > 1 ? ((Long)data[1]).longValue() : 0; int
				 * ruleParamId = data[2] != null && data.length > 2 ?
				 * ((Integer)data[2]).intValue() : 0; int ruleParamType = data[3] != null &&
				 * data.length > 3 ? ((Integer)data[3]).intValue() : 0;
				 * if(ruleGrpId==deductionRuleMst.getRuleGrpId()) {
				 * 
				 * if(ruleParamType==PayrollConstants.PAYROLL_PARAMETER_FIXED_TYPE) {
				 * if(deductionRuleParamMpg.getParamValue().equals(paramValueMap.get(ruleParamId
				 * ).toString())) { ruleGrpIdFlag=true; } else{ ruleGrpIdFlag=false; break; } }
				 * else if((ruleParamType==PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE) &&
				 * (ruleParamId!=PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
				 * compareLowValFlag=
				 * BigDecimal.valueOf(Long.parseLong(paramValueMap.get(ruleParamId).toString()))
				 * .compareTo(deductionRuleParamMpg.getParamLowerValue());
				 * 
				 * if(deductionRuleParamMpg.getParamUpperValue().longValue()!=-1) {
				 * compareUprValFlag=BigDecimal.valueOf(Long.parseLong(paramValueMap.get(
				 * ruleParamId).toString())).compareTo(deductionRuleParamMpg.getParamUpperValue(
				 * )); } else { compareUprValFlag=-1; } //compareUprValFlag=new
				 * BigDecimal(paramValueMap.get(ruleParamId).toString()).compareTo(
				 * deductionRuleParamMpg.getParamUpperValue());
				 * 
				 * if(((compareLowValFlag==0)||(compareLowValFlag==1))&&((compareUprValFlag==0)|
				 * |(compareUprValFlag==-1))) { ruleGrpIdFlag=true; } else{ ruleGrpIdFlag=false;
				 * break; } } else
				 * if((ruleParamType==PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE) &&
				 * (ruleParamId==PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
				 * if(deductionRuleParamMpg.getParamLowerValue().longValue()!=0) { Object[]
				 * lowScaleColVal =
				 * {deductionRuleParamMpg.getParamLowerValue().longValue(),langId}; scaleMstLow
				 * = scaleMstDAO.getListByColumnAndValue(scaleCmprCol, lowScaleColVal).get(0);
				 * 
				 * lowerScaleParamLowLmt=scaleMstLow.getScaleStartAmt();
				 * lowerScaleGP=scaleMstLow.getScaleGradePay()!=0?scaleMstLow.getScaleGradePay()
				 * :0; lowerScaleIncrAmnt=scaleMstLow.getScaleIncrAmt();
				 * 
				 * } if(deductionRuleParamMpg.getParamUpperValue().longValue()!=-1) { Object[]
				 * uprScaleColVal =
				 * {deductionRuleParamMpg.getParamUpperValue().longValue(),langId}; scaleMstUpr
				 * = scaleMstDAO.getListByColumnAndValue(scaleCmprCol, uprScaleColVal).get(0);
				 * 
				 * upperScaleParamLowLmt=scaleMstUpr.getScaleStartAmt();
				 * upperScaleGP=scaleMstUpr.getScaleGradePay()!=0?scaleMstUpr.getScaleGradePay()
				 * :0; upperScaleIncrAmnt=scaleMstUpr.getScaleIncrAmt(); }
				 * 
				 * if(lowerScaleParamLowLmt==0) {
				 * lowerScaleParamLowLmt=scaleMstEmp.getScaleStartAmt(); }
				 * if(upperScaleParamLowLmt==0) {
				 * upperScaleParamLowLmt=scaleMstEmp.getScaleStartAmt(); }
				 * 
				 * if(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
				 * equals("2500341")) {
				 * if((scaleMstEmp.getScaleStartAmt()>lowerScaleParamLowLmt) &&
				 * (scaleMstEmp.getScaleStartAmt()<upperScaleParamLowLmt)) { ruleGrpIdFlag=true;
				 * } else if((scaleMstEmp.getScaleStartAmt()==lowerScaleParamLowLmt) &&
				 * (scaleMstEmp.getScaleGradePay()>=lowerScaleGP)) { ruleGrpIdFlag=true; } else
				 * if((scaleMstEmp.getScaleStartAmt()==upperScaleParamLowLmt) &&
				 * (scaleMstEmp.getScaleGradePay()<=upperScaleGP)) { ruleGrpIdFlag=true; } else
				 * { ruleGrpIdFlag=false; break; }
				 * 
				 * } else
				 * if(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString().
				 * equals("2500340")) {
				 * if((scaleMstEmp.getScaleStartAmt()>lowerScaleParamLowLmt) &&
				 * (scaleMstEmp.getScaleStartAmt()<upperScaleParamLowLmt)) { ruleGrpIdFlag=true;
				 * } else if((scaleMstEmp.getScaleStartAmt()==lowerScaleParamLowLmt) &&
				 * (scaleMstEmp.getScaleIncrAmt()>=lowerScaleIncrAmnt)) { ruleGrpIdFlag=true; }
				 * else if((scaleMstEmp.getScaleStartAmt()==upperScaleParamLowLmt) &&
				 * (scaleMstEmp.getScaleIncrAmt()<=upperScaleIncrAmnt)) { ruleGrpIdFlag=true; }
				 * else { ruleGrpIdFlag=false; break; } }
				 * 
				 * } } }
				 * 
				 * if(ruleGrpIdFlag){
				 * 
				 * if(deductionRuleMst.getReturnType()==PayrollConstants.
				 * PAYROLL_RETURNTYPE_FIXED) { empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD by
				 * 229271 on 1/27/12 7:28 AM empSalTxn.setEmpId(orgEmp);
				 * empSalTxn.setAllwDedCode(deductionRuleMst.getAllwDedCode());
				 * empSalTxn.setAmount(deductionRuleMst.getReturnValue());
				 * 
				 * empSalaryMap.put(deductionRuleMst.getAllwDedCode(), empSalTxn);
				 * 
				 * paramValueMap.put((int)deductionRuleMst.getAllwDedCode(),
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * RuleMst.getReturnValue()); } else
				 * if(deductionRuleMst.getReturnType()==PayrollConstants.
				 * PAYROLL_RETURNTYPE_FORMULA) { empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD
				 * by 229271 on 1/27/12 7:28 AM empSalTxn.setEmpId(orgEmp);
				 * empSalTxn.setAllwDedCode(deductionRuleMst.getAllwDedCode());
				 * 
				 * formula=deductionRuleMst.getReturnFormula().split("P");
				 * 
				 * for(int j=0; j<formula.length;j++) {
				 * if(Long.parseLong(formula[j])==PayrollConstants.PAYROLL_BASIC_PAY_CODE) {
				 * formulaAmount=formulaAmount.add(BigDecimal.valueOf(Long.parseLong(
				 * paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_BASICPAY).toString()))); }
				 * else if(Long.parseLong(formula[j])==PayrollConstants.PAYROLL_GRADE_PAY_CODE)
				 * { formulaAmount=formulaAmount.add(BigDecimal.valueOf(Long.parseLong(
				 * paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_GRADEPAY).toString()))); }
				 * 
				 * } finalAmount=deductionRuleMst.getReturnValue().multiply(formulaAmount);
				 * finalAmount=finalAmount.divide(BigDecimal.valueOf(100L), 0,
				 * BigDecimal.ROUND_HALF_UP);
				 * 
				 * empSalTxn.setAmount(finalAmount);
				 * 
				 * empSalaryMap.put(deductionRuleMst.getAllwDedCode(), empSalTxn);
				 * 
				 * paramValueMap.put((int)deductionRuleMst.getAllwDedCode(), finalAmount); }
				 * break; } } }
				 * 
				 * } //calculation of independent deductions ends }
				 */

				// calculation of dependent deductions begins
				ruleGrpIdFlag = false;
				for (HrPayAllowDedMst payComp : ruleBasedDeductionList) {
					formulaAmount = BigDecimal.ZERO;
					finalAmount = BigDecimal.ZERO;

					for (HrPayRuleGrpMst deductionRuleMst : activeDeductionRuleList) {

						ruleGrpIdFlag = false;
						if (deductionRuleMst.getAllwDedCode() == payComp.getAllwDedCode()) {
							int activeDeducRuleParamMpglistSize = activeDeducRuleParamMpgList != null
									? activeDeducRuleParamMpgList.size()
									: 0;
							for (int counter = 0; counter < activeDeducRuleParamMpglistSize; counter++) {
								Object[] data = (Object[]) activeDeducRuleParamMpgList.get(counter);
								HrPayRuleGrpParamRlt deductionRuleParamMpg = data[0] != null && data.length > 0
										? (HrPayRuleGrpParamRlt) data[0]
										: new HrPayRuleGrpParamRlt();
								long ruleGrpId = data[1] != null && data.length > 1 ? ((Long) data[1]).longValue() : 0;
								int ruleParamId = data[2] != null && data.length > 2 ? ((Integer) data[2]).intValue()
										: 0;
								int ruleParamType = data[3] != null && data.length > 3 ? ((Integer) data[3]).intValue()
										: 0;
								if (ruleGrpId == deductionRuleMst.getRuleGrpId()) {
									logger.info("Rule Grp Id is " + deductionRuleMst.getRuleGrpId()
											+ " parameter type is " + ruleParamType + " param id is " + ruleParamId
											+ "return value   " + deductionRuleMst.getReturnValue());
									if (ruleParamType == PayrollConstants.PAYROLL_PARAMETER_FIXED_TYPE) {
										Object temp = paramValueMap.get(ruleParamId) != null
												? paramValueMap.get(ruleParamId).toString()
												: 0;
										logger.info("Param Value q-----:" + deductionRuleParamMpg.getParamValue()
												+ "temp.toString()" + temp.toString());
										if (deductionRuleParamMpg.getParamValue().equals(temp.toString())) {
											// if(deductionRuleParamMpg.getParamValue().equals(paramValueMap.get(ruleParamId).toString()))
											if (deductionRuleParamMpg.getParamValue()
													.equals(paramValueMap.get(ruleParamId).toString())) {
												logger.info("Param Value  from deductionRuleParamMpg:"
														+ deductionRuleParamMpg.getParamValue()
														+ "Param value from paramValueMap"
														+ paramValueMap.get(ruleParamId).toString());
												ruleGrpIdFlag = true;
											} else {
												logger.info("Param Value false  from deductionRuleParamMpg:"
														+ deductionRuleParamMpg.getParamValue()
														+ "Param value false from paramValueMap"
														+ paramValueMap.get(ruleParamId).toString());
												ruleGrpIdFlag = false;
												break;
											}
										}

										else {
											ruleGrpIdFlag = false;
											break;
										}
										logger.info("11 ");
									} else if ((ruleParamType == PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
											&& (ruleParamId != PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
										logger.info("11 " + ruleParamId);
										logger.info("22 " + deductionRuleParamMpg.getParamId());

										String val = paramValueMap.get(ruleParamId).toString();
										long longVal = Math.round(Double.parseDouble(val));

										compareLowValFlag = BigDecimal.valueOf(longVal)
												.compareTo(deductionRuleParamMpg.getParamLowerValue());

										if (deductionRuleParamMpg.getParamUpperValue().longValue() != -1) {
											compareUprValFlag = BigDecimal.valueOf(longVal)
													.compareTo(deductionRuleParamMpg.getParamUpperValue());
										} else {
											compareUprValFlag = -1;
										}
										// compareUprValFlag=new
										// BigDecimal(paramValueMap.get(ruleParamId).toString()).compareTo(deductionRuleParamMpg.getParamUpperValue());

										if (((compareLowValFlag == 0) || (compareLowValFlag == 1))
												&& ((compareUprValFlag == 0) || (compareUprValFlag == -1))) {
											ruleGrpIdFlag = true;
										} else {
											ruleGrpIdFlag = false;
											break;
										}
									} else if ((ruleParamType == PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
											&& (ruleParamId == PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
										if (deductionRuleParamMpg.getParamLowerValue().longValue() != 0) {
											Object[] lowScaleColVal = {
													deductionRuleParamMpg.getParamLowerValue().longValue(), langId };
											scaleMstLow = scaleMstDAO
													.getListByColumnAndValue(scaleCmprCol, lowScaleColVal).get(0);

											lowerScaleParamLowLmt = scaleMstLow.getScaleStartAmt();
											lowerScaleGP = scaleMstLow.getScaleGradePay() != 0
													? scaleMstLow.getScaleGradePay()
													: 0;
											lowerScaleIncrAmnt = scaleMstLow.getScaleIncrAmt();

										}
										if (deductionRuleParamMpg.getParamUpperValue().longValue() != -1) {
											Object[] uprScaleColVal = {
													deductionRuleParamMpg.getParamUpperValue().longValue(), langId };
											scaleMstUpr = scaleMstDAO
													.getListByColumnAndValue(scaleCmprCol, uprScaleColVal).get(0);

											upperScaleParamLowLmt = scaleMstUpr.getScaleStartAmt();
											upperScaleGP = scaleMstUpr.getScaleGradePay() != 0
													? scaleMstUpr.getScaleGradePay()
													: 0;
											upperScaleIncrAmnt = scaleMstUpr.getScaleIncrAmt();
										}

										if (lowerScaleParamLowLmt == 0) {
											lowerScaleParamLowLmt = scaleMstEmp.getScaleStartAmt();
										}
										if (upperScaleParamLowLmt == 0) {
											upperScaleParamLowLmt = scaleMstEmp.getScaleStartAmt();
										}

										if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString()
												.equals("2500341")) {
											if ((scaleMstEmp.getScaleStartAmt() > lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleStartAmt() < upperScaleParamLowLmt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleGradePay() >= lowerScaleGP)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == upperScaleParamLowLmt)
													&& (scaleMstEmp.getScaleGradePay() <= upperScaleGP)) {
												ruleGrpIdFlag = true;
											} else {
												ruleGrpIdFlag = false;
												break;
											}

										} else if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN)
												.toString().equals("2500340")) {
											if ((scaleMstEmp.getScaleStartAmt() > lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleStartAmt() < upperScaleParamLowLmt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == lowerScaleParamLowLmt)
													&& (scaleMstEmp.getScaleIncrAmt() >= lowerScaleIncrAmnt)) {
												ruleGrpIdFlag = true;
											} else if ((scaleMstEmp.getScaleStartAmt() == upperScaleParamLowLmt)
													&& (scaleMstEmp.getScaleIncrAmt() <= upperScaleIncrAmnt)) {
												ruleGrpIdFlag = true;
											} else {
												ruleGrpIdFlag = false;
												break;
											}
										}

									}
								}
							}

							if (ruleGrpIdFlag) {

								if (deductionRuleMst.getReturnType() == PayrollConstants.PAYROLL_RETURNTYPE_FIXED) {
									empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD by 229271 on 1/27/12 7:28 AM
									empSalTxn.setEmpId(orgEmp);
									empSalTxn.setAllwDedCode(deductionRuleMst.getAllwDedCode());
									empSalTxn.setAmount(deductionRuleMst.getReturnValue());

									empSalaryMap.put(deductionRuleMst.getAllwDedCode(), empSalTxn);

								} else if (deductionRuleMst
										.getReturnType() == PayrollConstants.PAYROLL_RETURNTYPE_FORMULA) {
									empSalTxn = new HrPayEmpSalaryTxn(); // NOPMD by 229271 on 1/27/12 7:28 AM
									empSalTxn.setEmpId(orgEmp);
									empSalTxn.setAllwDedCode(deductionRuleMst.getAllwDedCode());

									formula = deductionRuleMst.getReturnFormula().split("P");

									for (int j = 0; j < formula.length; j++) {
										// if(Long.parseLong(formula[j])==PayrollConstants.PAYROLL_BASIC_PAY_CODE)
										// {
										// formulaAmount=formulaAmount.add(BigDecimal.valueOf(Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_BASICPAY).toString())));
										// }
										// else if(Long.parseLong(formula[j])==PayrollConstants.PAYROLL_GRADE_PAY_CODE)
										// {
										// formulaAmount=formulaAmount.add(BigDecimal.valueOf(Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_GRADEPAY).toString())));
										// }
										if (paramValueMap.containsKey(Integer.parseInt(formula[j]))) {
											formulaAmount = formulaAmount.add(BigDecimal.valueOf(Long.parseLong(
													paramValueMap.get(Integer.parseInt(formula[j])).toString())));

										}
									}
									/* Added By Shivram */
									logger.info("deduction code == 32 executed");
									if ((deductionRuleMst.getAllwDedCode() == 32)
											&& (paramValueMap.get(Integer.valueOf(14)).toString().equals("2500340"))) {
										logger.info("deduction code == 32 executed 1");
										formulaAmount = formulaAmount.subtract(BigDecimal.valueOf(
												Long.parseLong(paramValueMap.get(Integer.valueOf(9)).toString())));
									}
									Long month = Long.valueOf(Long.parseLong(inputMap.get("monthGiven").toString()));
									Long basicForSvnPC = Long.valueOf(0L);

									if (((Long.parseLong(inputMap.get("yearGiven").toString()) == 2016)
											&& (Long.parseLong(inputMap.get("monthGiven").toString()) >= 10L))
											|| (Long.parseLong(inputMap.get("yearGiven").toString()) >= 2017L)) {
										LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,
												serv.getSessionFactory());
										basicForSvnPC = dao.getRevisedSvnPCBasic(hrEisOtherDtlsObj.getEisEmpId());
										if (basicForSvnPC.longValue() > 0L) {
											logger.info("deduction code == 32 executed 2");
											formulaAmount = new BigDecimal(0).multiply(formulaAmount);

											formulaAmount = formulaAmount
													.add(BigDecimal.valueOf(basicForSvnPC.longValue()));
											formulaAmount = formulaAmount.add(BigDecimal.valueOf(
													Long.parseLong(paramValueMap.get(Integer.valueOf(7)).toString())));

										}

									} else if ((Long.parseLong(inputMap.get("yearGiven").toString()) == 2016L)
											&& (Long.parseLong(inputMap.get("monthGiven").toString()) >= 7L)
											&& (Long.parseLong(inputMap.get("monthGiven").toString()) <= 9L)) {

										LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,
												serv.getSessionFactory());
										basicForSvnPC = dao.getRevisedSvnPCBasic(hrEisOtherDtlsObj.getEisEmpId());
										if (basicForSvnPC.longValue() > 0L) {

											formulaAmount = BigDecimal.valueOf(
													Long.parseLong(paramValueMap.get(Integer.valueOf(5)).toString()));

											formulaAmount = formulaAmount.add(BigDecimal.valueOf(
													Long.parseLong(paramValueMap.get(Integer.valueOf(9)).toString())));

											formulaAmount = formulaAmount.add(BigDecimal.valueOf(
													Long.parseLong(paramValueMap.get(Integer.valueOf(7)).toString())));
										}
									}

									finalAmount = deductionRuleMst.getReturnValue().multiply(formulaAmount);
									if (deductionRuleMst.getAllwDedCode() == 32 || deductionRuleMst.getAllwDedCode() == 142)// for round up next number
										finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0,
												BigDecimal.ROUND_UP);
									else
										finalAmount = finalAmount.divide(BigDecimal.valueOf(100L), 0,
												BigDecimal.ROUND_HALF_UP);

									empSalTxn.setAmount(finalAmount);

									empSalaryMap.put(deductionRuleMst.getAllwDedCode(), empSalTxn);

								}
								break;
							}
						}
					}

					// calculation of dependent deductions ends
				}
				/* Ended By Shivram */

				empSalTxn = new HrPayEmpSalaryTxn();
				empSalTxn.setEmpId(orgEmp);
				empSalTxn.setAllwDedCode(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				empSalTxn.setAmount(BigDecimal.valueOf(
						Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_BASICPAY).toString())));
				empSalaryMap.put(PayrollConstants.PAYROLL_BASIC_PAY_CODE, empSalTxn);

				empSalTxn = new HrPayEmpSalaryTxn();
				empSalTxn.setEmpId(orgEmp);
				empSalTxn.setAllwDedCode(PayrollConstants.PAYROLL_GRADE_PAY_CODE);
				empSalTxn.setAmount(BigDecimal.valueOf(
						Long.parseLong(paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_GRADEPAY).toString())));
				empSalaryMap.put(PayrollConstants.PAYROLL_GRADE_PAY_CODE, empSalTxn);

				java.util.Set abc = empSalaryMap.keySet();
				Iterator iterator = abc.iterator();
				while (iterator.hasNext()) {
					HrPayEmpSalaryTxn empSalaryTxn = (HrPayEmpSalaryTxn) empSalaryMap.get(iterator.next());

					logger.info("Id Of AllwDedMst is " + empSalaryTxn.getAllwDedCode() + " Amount is "
							+ empSalaryTxn.getAmount());
				}
				logger.info(
						"*****************************************************************************************************************");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			logger.error("Error in PayrollCalculationServiceImpl--->getAllRuleBasedPayCompValue", ex);
		}

		return empSalaryMap;
	}

	public BigDecimal getRuleCompoValue(Map<Integer, Object> paramValueMap, ServiceLocator serv, Map inputMap,
			long allowDedCode) {
		boolean ruleGrpIdFlag = false;

		BigDecimal formulaAmount = BigDecimal.ZERO;
		BigDecimal finalAmount = BigDecimal.ZERO;

		int compareLowValFlag = 2;
		int compareUprValFlag = 2;
		long langId = 1;
		long lowerScaleParamLowLmt = 0;
		long upperScaleParamLowLmt = 0;
		long lowerScaleGP = 0;
		long upperScaleGP = 0;
		long lowerScaleIncrAmnt = 0;
		long upperScaleIncrAmnt = 0;

		String[] formula;

		HrEisScaleMst scaleMstLow = new HrEisScaleMst();
		HrEisScaleMst scaleMstUpr = new HrEisScaleMst();
		HrEisScaleMst scaleMstEmp = (HrEisScaleMst) inputMap.get("scaleMstEmp");

		String[] scaleCmprCol = { "scaleId" };// ,"cmnLanguageMst.langId"};

		HrEisScaleMstDao scaleMstDAO = new HrEisScaleMstDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());

		ArrayList<HrPayRuleGrpMst> activeDeductionUsedInFormulaRuleList = (ArrayList) inputMap
				.get("activeDeductionUsedInFormulaRuleList");
		ArrayList activeDeducUsedInFormulaRuleParamMpgList = (ArrayList) inputMap
				.get("activeDeducUsedInFormulaRuleParamMpgList");

		int activeAllowUsedInFormulaRuleParamMpglistSize = activeDeducUsedInFormulaRuleParamMpgList.size();

		for (HrPayRuleGrpMst allowanceRuleMst : activeDeductionUsedInFormulaRuleList) {

			ruleGrpIdFlag = false;
			if (allowanceRuleMst.getAllwDedCode() == allowDedCode) {

				for (int counter = 0; counter < activeAllowUsedInFormulaRuleParamMpglistSize; counter++) {
					Object[] data = (Object[]) activeDeducUsedInFormulaRuleParamMpgList.get(counter);
					HrPayRuleGrpParamRlt allowRuleParamMpg = data[0] != null && data.length > 0
							? (HrPayRuleGrpParamRlt) data[0]
							: new HrPayRuleGrpParamRlt();
					long ruleGrpId = data[1] != null && data.length > 1 ? ((Long) data[1]).longValue() : 0;
					int ruleParamId = data[2] != null && data.length > 2 ? ((Integer) data[2]).intValue() : 0;
					int ruleParamType = data[3] != null && data.length > 3 ? ((Integer) data[3]).intValue() : 0;
					if (ruleGrpId == allowanceRuleMst.getRuleGrpId()) {

						if (ruleParamType == PayrollConstants.PAYROLL_PARAMETER_FIXED_TYPE) {
							if (allowRuleParamMpg.getParamValue().equals(paramValueMap.get(ruleParamId).toString())) {
								ruleGrpIdFlag = true;
							} else {
								ruleGrpIdFlag = false;
								break;
							}
						} else if ((ruleParamType == PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
								&& (ruleParamId != PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
							compareLowValFlag = BigDecimal
									.valueOf(Long.parseLong(paramValueMap.get(ruleParamId).toString()))
									.compareTo(allowRuleParamMpg.getParamLowerValue());

							if (allowRuleParamMpg.getParamUpperValue().longValue() != -1) {
								compareUprValFlag = BigDecimal
										.valueOf(Long.parseLong(paramValueMap.get(ruleParamId).toString()))
										.compareTo(allowRuleParamMpg.getParamUpperValue());
							} else {
								compareUprValFlag = -1;
							}

							if (((compareLowValFlag == 0) || (compareLowValFlag == 1))
									&& ((compareUprValFlag == 0) || (compareUprValFlag == -1))) {
								ruleGrpIdFlag = true;
							} else {
								ruleGrpIdFlag = false;
								break;
							}
						} else if ((ruleParamType == PayrollConstants.PAYROLL_PARAMETER_RANGE_TYPE)
								&& (ruleParamId == PayrollConstants.PAYROLL_PARAMID_PAYSCALE)) {
							if (allowRuleParamMpg.getParamLowerValue().longValue() != 0) {
								Object[] lowScaleColVal = { allowRuleParamMpg.getParamLowerValue().longValue(),
										langId };
								scaleMstLow = scaleMstDAO.getListByColumnAndValue(scaleCmprCol, lowScaleColVal).get(0);

								lowerScaleParamLowLmt = scaleMstLow.getScaleStartAmt();
								lowerScaleGP = scaleMstLow.getScaleGradePay() != 0 ? scaleMstLow.getScaleGradePay() : 0;
								lowerScaleIncrAmnt = scaleMstLow.getScaleIncrAmt();

							}
							if (allowRuleParamMpg.getParamUpperValue().longValue() != -1) {
								Object[] uprScaleColVal = { allowRuleParamMpg.getParamUpperValue().longValue(),
										langId };
								scaleMstUpr = scaleMstDAO.getListByColumnAndValue(scaleCmprCol, uprScaleColVal).get(0);

								upperScaleParamLowLmt = scaleMstUpr.getScaleStartAmt();
								upperScaleGP = scaleMstUpr.getScaleGradePay() != 0 ? scaleMstUpr.getScaleGradePay() : 0;
								upperScaleIncrAmnt = scaleMstUpr.getScaleIncrAmt();
							}

							if (lowerScaleParamLowLmt == 0) {
								lowerScaleParamLowLmt = scaleMstEmp.getScaleStartAmt();
							}
							if (upperScaleParamLowLmt == 0) {
								upperScaleParamLowLmt = scaleMstEmp.getScaleStartAmt();
							}

							if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString()
									.equals("2500341")) {

								if ((scaleMstEmp.getScaleStartAmt() > lowerScaleParamLowLmt)
										&& (scaleMstEmp.getScaleStartAmt() < upperScaleParamLowLmt)) {
									ruleGrpIdFlag = true;
								} else if ((scaleMstEmp.getScaleStartAmt() == lowerScaleParamLowLmt)
										&& (scaleMstEmp.getScaleGradePay() >= lowerScaleGP)) {
									ruleGrpIdFlag = true;
								} else if ((scaleMstEmp.getScaleStartAmt() == upperScaleParamLowLmt)
										&& (scaleMstEmp.getScaleGradePay() <= upperScaleGP)) {
									ruleGrpIdFlag = true;
								} else {
									ruleGrpIdFlag = false;
									break;
								}

							} else if (paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString()
									.equals("2500340")) {
								if ((scaleMstEmp.getScaleStartAmt() > lowerScaleParamLowLmt)
										&& (scaleMstEmp.getScaleStartAmt() < upperScaleParamLowLmt)) {
									ruleGrpIdFlag = true;
								} else if ((scaleMstEmp.getScaleStartAmt() == lowerScaleParamLowLmt)
										&& (scaleMstEmp.getScaleIncrAmt() >= lowerScaleIncrAmnt)) {
									ruleGrpIdFlag = true;
								} else if ((scaleMstEmp.getScaleStartAmt() == upperScaleParamLowLmt)
										&& (scaleMstEmp.getScaleIncrAmt() <= upperScaleIncrAmnt)) {
									ruleGrpIdFlag = true;
								} else {
									ruleGrpIdFlag = false;
									break;
								}
							}
						}
					}
				}

				if (ruleGrpIdFlag) {

					if (allowanceRuleMst.getReturnType() == PayrollConstants.PAYROLL_RETURNTYPE_FIXED) {

						// return allowanceRuleMst.getReturnValue();
						finalAmount = allowanceRuleMst.getReturnValue();
					} else if (allowanceRuleMst.getReturnType() == PayrollConstants.PAYROLL_RETURNTYPE_FORMULA) {

						formula = allowanceRuleMst.getReturnFormula().split("P");

						for (int k = 0; k < formula.length; k++) {
							if ((Long.valueOf(formula[k])).longValue() == PayrollConstants.PAYROLL_BASIC_PAY_CODE) {
								formulaAmount = formulaAmount.add(BigDecimal.valueOf(Long.parseLong(
										paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_BASICPAY).toString())));
							} else if ((Long.valueOf(formula[k]))
									.longValue() == PayrollConstants.PAYROLL_GRADE_PAY_CODE) {
								formulaAmount = formulaAmount.add(BigDecimal.valueOf(Long.parseLong(
										paramValueMap.get(PayrollConstants.PAYROLL_PARAMID_GRADEPAY).toString())));
							}
						}
						finalAmount = allowanceRuleMst.getReturnValue().multiply(formulaAmount);
						finalAmount = finalAmount.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);

						// return finalAmount;
					}
					break;
				}
			}
		}

		logger.info("The Values i got from  getRuleCompoValue method is " + finalAmount);
		return finalAmount;

	}

}