package com.tcs.sgv.eis.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.IncomeTaxRules;
import com.tcs.sgv.allowance.service.SalaryRules;
import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
import com.tcs.sgv.allowance.service.SalaryRules_7thPay;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.dao.BrokenPeriodDAOImpl;
import com.tcs.sgv.dcps.dao.LedgerReportDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstBrokenPeriodPay;
import com.tcs.sgv.dcps.valueobject.RltBrokenPeriodAllow;
import com.tcs.sgv.dcps.valueobject.RltBrokenPeriodDeduc;
import com.tcs.sgv.eis.dao.EmpInfoDAO;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.service.GenerateBillService;
import com.tcs.sgv.eis.service.PayrollCalculationServiceImpl;
import com.tcs.sgv.eis.valueobject.AllwValCustomVO;
import com.tcs.sgv.eis.valueobject.DeductionCustomVO;
import com.tcs.sgv.eis.valueobject.EmpBrokenPeriodVO;
import com.tcs.sgv.eis.valueobject.EmpPaybillLoanVO;
import com.tcs.sgv.eis.valueobject.EmpPaybillVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

import com.tcs.sgv.payroll.util.PayrollConstants;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.eis.valueobject.HrPayEmpSalaryTxn;
import java.math.BigDecimal;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.eis.dao.PayComponentMasterDAOImpl;
import com.tcs.sgv.eis.service.PayrollCalculationServiceImpl;
import com.tcs.sgv.eis.valueobject.HrPayAllowDedMst;

public class GenerateBillServiceCoreLogic {

	Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Map executeCoreLogic(Map inputMap) throws Exception {

		Map resultMap = inputMap;
		if (resultMap.containsKey("payBillVO"))
			resultMap.remove("payBillVO");

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

		/*
		 * long dcpsId = Long.parseLong(resourceBundle.getString("dcpsId")); long
		 * dcpsDaId = Long.parseLong(resourceBundle.getString("dcpsDaId")); long
		 * dcpsPayId = Long.parseLong(resourceBundle.getString("dcpsPayId")); long
		 * dcpsDelayId = Long.parseLong(resourceBundle.getString("dcpsDelayId"));
		 * 
		 * long dedycType = Long.parseLong(resourceBundle.getString("deducLookupId"));
		 */

		long loanLookupId = Long.parseLong(resourceBundle.getString("loanLookupId"));
		long advLookupId = Long.parseLong(resourceBundle.getString("advLookupId"));

		long sixthPayCommId = Long.parseLong(resourceBundle.getString("commissionSixId"));
		long commissionFiveId = Long.parseLong(resourceBundle.getString("commissionFiveId"));
		long commissionFourId = Long.parseLong(resourceBundle.getString("commissionFourId"));
		long commissionThreeId = Long.parseLong(resourceBundle.getString("commissionThreeId"));
		long commissionTwoId = Long.parseLong(resourceBundle.getString("commissionTwoId"));
		long commissionOneId = Long.parseLong(resourceBundle.getString("commissionOneId"));
		long commissionSevenId = Long.parseLong(resourceBundle.getString("commissionSevenId"));

		long commissionNewSevenId = Long.parseLong(resourceBundle.getString("commissionNewSevenId"));

		long hrrId = Long.parseLong(resourceBundle.getString("hrrId"));

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		long advEMI = 0;

		// Calendar calGiven = Calendar.getInstance();

		try {

			int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
			int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());
			long psrNo = 0;
			// int fromView = Integer.parseInt(inputMap.get("fromView")!=null
			// ?inputMap.get("fromView").toString():"-1");

			// logger.info("months given from input map is "+monthGiven+" and year
			// "+yearGiven);

			/*
			 * InputVO.setMonth(monthGiven); InputVO.setYear(yearGiven);
			 */

			/*
			 * OrgUserpostRlt orgUPRlt = (OrgUserpostRlt)inputMap.get("orgUPRlt");
			 * 
			 * 
			 * int orgUpRltSize = inputMap.get("orgUpRltSize")!=null?Integer.valueOf
			 * (inputMap.get("orgUpRltSize").toString()):2;
			 * 
			 * daysOfPost = inputMap.get("daysOfPost")!=null?Integer.valueOf(inputMap
			 * .get("daysOfPost").toString()):-1;
			 */

			int grade = 0;
			long scale_start_amt = 0;

			EmpPaybillVO empPaybillVO = (EmpPaybillVO) inputMap.get("hrEisOtherDtls");

			// HrEisEmpMst hrEisEmpMst =hrEisOtherDtls.getHrEisEmpMst();
			long empId = empPaybillVO.getEisEmpId();

			Map loginMap = (Map) inputMap.get("baseLoginMap");
			long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
			// long dbId=99;
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,
					serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
			// PayComponentMasterDAOImpl payComponentMasterDAOImpl = new
			// PayComponentMasterDAOImpl(HrPayAllowDedMst.class,serv.getSessionFactory());

			// logger.info("cmnDatabaseMst in Core Logic is " + cmnDatabaseMst);
			// logger.info("DB id is " + cmnDatabaseMst.getDbDescription());

			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			/*
			 * long locId = StringUtility.convertToLong(loginMap.get("locationId"
			 * ).toString()); long langId =
			 * StringUtility.convertToLong(loginMap.get("langId").toString());
			 */

			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			// long empCurrentStatus=1;//hardcoded for getting the active
			// allowance of the emp
			// EmpAllwMapDAOImpl empAllowDAO = new
			// EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			// List<HrPayEmpallowMpg> empAllowVOList = new ArrayList();
			// List<HrPayDeductionDtls> empDeducVOList = new ArrayList();

			HrPayPaybill payBillVO = new HrPayPaybill();
			long payBillId = inputMap.get("payBillId") != null ? Long.valueOf(inputMap.get("payBillId").toString()) : 0;
			payBillVO.setId(payBillId);

			OrgPostMst billPostMst = new OrgPostMst();
			billPostMst.setPostId(empPaybillVO.getPostId());
			payBillVO.setOrgPostMst(billPostMst);
			payBillVO.setEmpLname(empPaybillVO.getEmpLname());

			long empid = empPaybillVO.getEisEmpId();
			long orgEmpId = empPaybillVO.getOrgEmpId();

			// logger.info("**********************************************************the
			// emp id is "+empid);
			// logger.info("Core Logic PerEmployee Start Time" +
			// System.currentTimeMillis());
			// long basic=0;
			long CurrBasic = 0;
			int isAvailedHRA = 0;

			String city = "";
			boolean isHandicapped = true;
			// long incomeTax=0;

			// basic=empPaybillVO.getBasicAmt();
			Calendar cal2 = Calendar.getInstance();
			cal2.set(Calendar.YEAR, yearGiven);
			cal2.set(Calendar.MONTH, monthGiven);
			cal2.set(Calendar.DATE, 1);

			// Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			/*
			 * langId=0; try{ langId =StringUtility.convertToLong(loginDetailsMap
			 * .get("langId").toString()); } catch(Exception e) { logger.info(
			 * "excepiton while converting to long value from core logic");
			 * logger.error("Error is: "+ e.getMessage()); }
			 */
			// PayBillDAOImpl paybillDAO = new
			// PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());

			// SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

			// end by manoj for HrEmpTraMpg related issues

			// logger.info(" isAvailedHRA in coreLogic is: "+isAvailedHRA);
			// by manoj used for getting city category from city_id of other
			// detail

			// incomeTax=Math.round(empPaybillVO.getIncomeTax());

			logger.info("the currBasic " + CurrBasic);
			logger.info("the city " + city);

			logger.info("the grade  " + grade);
			logger.info("the isHandicapped " + isHandicapped);
			logger.info("the scale_start_amt " + scale_start_amt);
			logger.info("isAvailedHRA : " + isAvailedHRA);
			// ended post if

			// generating Input Map for input
			long payCommissionId = empPaybillVO.getPayCommissionId();
			logger.info("Pay Commission is --->" + payCommissionId);
			Map input = this.generatePassMap(inputMap);

			Map inputRuleEngine = this.generatePassMapForRuleEngine(inputMap);
			// calculating all allowances

			/* Added By Shivram */
			Long basicForSvnPC = Long.valueOf(0L);
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

			// Added by lekhchand TA 2022
			Long sevenPcLevel = Long.valueOf(0L);
			Long PaycommissionId = Long.valueOf(0L);
			PaycommissionId = dao.getSvnPayCommission(empId);
			sevenPcLevel = dao.getSvnPCLevel(empId);
			if (sevenPcLevel == null || sevenPcLevel == 0) {
				sevenPcLevel = dao.getSvnPCLevel2(empId);
			}

			/* Added Naveen Priya Sinha NPS 2022 */
			BigDecimal emplyrAllowContri = new BigDecimal(0);
			BigDecimal empDeducContri = new BigDecimal(0);
			BigDecimal emplyrDeducContri = new BigDecimal(0);

			/* Endded by Naveen Priya Sinha NPS 2022 */
			// Endded by lekhchand TA 2022
			basicForSvnPC = dao.getRevisedSvnPCBasic(empId);
			if ((basicForSvnPC.longValue() > 0L)
					&& (((monthGiven >= 10) && (yearGiven == 2016)) || (yearGiven >= 2017))) {
				payBillVO.setBasic0101(basicForSvnPC.longValue());
			} else {
				/* Ended By Shivram */
				payBillVO.setBasic0101(Math.round((Double) input.get("revBasic")));
			}
			if (input.get("DP") != null && String.valueOf(input.get("DP")).length() > 0) {
				logger.info("Dp value set in core logic is " + Math.round((Double) input.get("DP")));
				payBillVO.setAllow0119(Math.round((Double) input.get("DP")));
			}
			double parResult = 0;

			List<AllwValCustomVO> allowTypeMst = new ArrayList();
			Map empAllowCompoMap = (HashMap) (inputMap.get("empAllowCompoMap") != null
					? inputMap.get("empAllowCompoMap")
					: new HashMap());
			if (empAllowCompoMap.containsKey(empPaybillVO.getEisEmpId())) {
				// logger.info("Allowance mapping map found for emp id " +
				// empPaybillVO.getEisEmpId());
				// logger.info(" " +
				// empAllowCompoMap.get(empPaybillVO.getEisEmpId()));
				allowTypeMst = (List) (empAllowCompoMap.get(empPaybillVO.getEisEmpId()) != null
						? empAllowCompoMap.get(empPaybillVO.getEisEmpId())
						: new ArrayList());
			}
			empAllowCompoMap = null;
			SalaryRules salaryRules = new SalaryRules();
			SalaryRules_6thPay salaryRuls6thPay = new SalaryRules_6thPay();

			/* Added By Shivram */
			SalaryRules_7thPay salaryRuls7thPay = new SalaryRules_7thPay();
			/* Ended By Shivram */

			Map allowEdpMap = (HashMap) (inputMap.containsKey("allowEdpMap") ? inputMap.get("allowEdpMap")
					: new HashMap());

			String edpCode = null;
			double totalCompAllw = 0;
			// logger.info("allowList whole size is "+allowTypeMst.size());
			// logger.info("allow List is "+allowTypeMst);

			// GenericDaoHibernateImpl daoHibernateImpl = new
			// GenericDaoHibernateImpl(HrPayAllowTypeMst.class);
			// daoHibernateImpl.setSessionFactory(serv.getSessionFactory());
			BigDecimal amt = new BigDecimal(0);
			// inputRuleEngine
			Map<Long, HrPayEmpSalaryTxn> ruleMap = new HashMap<Long, HrPayEmpSalaryTxn>();
			if (empPaybillVO.getPayCommissionId() != PayrollConstants.PAYROLL_COSOLIDATED_PAYCOMMISSION) {
				ruleMap = (new PayrollCalculationServiceImpl()).getAllRuleBasedPayCompValue(empId, inputRuleEngine,
						serv, Long.valueOf(inputRuleEngine.get(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN).toString()),
						inputMap);
			}
			// logger.info("ruleMap is "+ruleMap);
			if (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_PADHAMANABHAN_PAYCOMMISSION
					|| empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SHETTY_PAYCOMMISSION) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				if (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SHETTY_PAYCOMMISSION) {
					empSalaryTxn.setAmount(new BigDecimal(0));
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_HRA_ID);
					ruleMap.put(PayrollConstants.PAYROLL_HRA_ID, empSalaryTxn);
				}
				empSalaryTxn = new HrPayEmpSalaryTxn();
				empSalaryTxn.setAmount(new BigDecimal(0));
				empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_TA_ID);

				ruleMap.put(PayrollConstants.PAYROLL_TA_ID, empSalaryTxn);
			}
			int billMonth = Integer.parseInt(inputMap.get("monthGiven").toString());
			int billyear = Integer.parseInt(inputMap.get("yearGiven").toString());
			if (billMonth == 3 && billyear == 2012
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION
							|| empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_NONGOVT_PAYCOMMISSION
							|| empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FOURTH_PAYCOMMISSION
							|| empPaybillVO
									.getPayCommissionId() == PayrollConstants.PAYROLL_PADHAMANABHAN_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);

				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew
							.getAmount();/*
											 * BigDecimal tempDaVal = tempDaAmt . multiply ( BigDecimal . valueOf (
											 * PayrollConstants . PAYROLL_DA_RATE_OLD )); BigDecimal tempDaValNew =
											 * tempDaVal . divide ( BigDecimal . valueOf ( PayrollConstants .
											 * PAYROLL_DA_RATE_NEW ),0, BigDecimal . ROUND_HALF_UP );
											 */
					// BigDecimal tempDaVal =
					// tempDaAmt.multiply(BigDecimal.valueOf(100));
					// BigDecimal exactMidVal =
					// tempDaVal.divide(BigDecimal.valueOf(PayrollConstants.PAYROLL_DA_RATE_NEW),0,BigDecimal.ROUND_HALF_UP);
					BigDecimal tempDaValNew = tempBasic
							.multiply(BigDecimal.valueOf(PayrollConstants.PAYROLL_DA_RATE_OLD));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);

					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old ");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}
			// added by sunitha
			if (billMonth < 11 && billyear <= 2012
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);

				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					// logger.info("Basic Before Adding"+tempBasic.doubleValue());
					// tempBasic =
					// tempBasic.add(BigDecimal.valueOf(payBillVO.getAllow0119()));
					// logger.info("Basic After Adding"+tempBasic.doubleValue());
					/*
					 * BigDecimal tempDaVal = tempDaAmt.multiply(BigDecimal.valueOf
					 * (PayrollConstants.PAYROLL_DA_RATE_OLD)); BigDecimal tempDaValNew =
					 * tempDaVal.divide(BigDecimal.valueOf(PayrollConstants
					 * .PAYROLL_DA_RATE_NEW),0,BigDecimal.ROUND_HALF_UP);
					 */
					// BigDecimal tempDaVal =
					// tempDaAmt.multiply(BigDecimal.valueOf(100));
					// BigDecimal exactMidVal =
					// tempDaVal.divide(BigDecimal.valueOf(PayrollConstants.PAYROLL_DA_RATE_NEW),0,BigDecimal.ROUND_HALF_UP);
					BigDecimal tempDaValNew = tempBasic
							.multiply(BigDecimal.valueOf(PayrollConstants.PAYROLL_NEW_DA_SIXTH_PAY));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);
					// logger.info(" gayathri tempDaValNew"+tempDaValNew);
					logger.info("sunitha finalVal" + finalVal);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}
			// ended by sunitha

			// added by vaibhav : start
			if (billMonth >= 5 && billMonth <= 9 && billyear == 2013
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);

				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					// tempBasic =
					// tempBasic.add(BigDecimal.valueOf(payBillVO.getAllow0119()));
					// logger.info("Basic After Adding"+tempBasic.doubleValue());
					/*
					 * BigDecimal tempDaVal = tempDaAmt.multiply(BigDecimal.valueOf
					 * (PayrollConstants.PAYROLL_DA_RATE_OLD)); BigDecimal tempDaValNew =
					 * tempDaVal.divide(BigDecimal.valueOf(PayrollConstants
					 * .PAYROLL_DA_RATE_NEW),0,BigDecimal.ROUND_HALF_UP);
					 */
					// BigDecimal tempDaVal =
					// tempDaAmt.multiply(BigDecimal.valueOf(100));
					// BigDecimal exactMidVal =
					// tempDaVal.divide(BigDecimal.valueOf(PayrollConstants.PAYROLL_DA_RATE_NEW),0,BigDecimal.ROUND_HALF_UP);
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(80));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);
					// logger.info(" gayathri tempDaValNew"+tempDaValNew);
					logger.info("sunitha finalVal" + finalVal);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}

			}

			if (billyear == 2014
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					if (billMonth < 5) {
						BigDecimal tempBasic = empSalaryTxnNew.getAmount();
						logger.info("Basic Before Adding" + tempBasic.doubleValue());
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(90));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
					}

					else {

						BigDecimal tempBasic = empSalaryTxnNew.getAmount();
						logger.info("Basic Before Adding" + tempBasic.doubleValue());
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(100));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);

					}
				}
			}
			// Added BY rosahnn**************
			if ((billMonth == 1 && billyear == 2015)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {

					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(100));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);
					// logger.info(" gayathri tempDaValNew"+tempDaValNew);
					logger.info("sunitha finalVal" + finalVal);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);

				}
			}

			if ((billMonth > 1 && billMonth < 10 && billyear == 2015)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {

					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(107));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);
					// logger.info(" gayathri tempDaValNew"+tempDaValNew);
					logger.info("sunitha finalVal" + finalVal);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);

				}
			}

			if ((billMonth >= 10 && billyear == 2015)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {

					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(113));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);
					// logger.info(" gayathri tempDaValNew"+tempDaValNew);
					logger.info("sunitha finalVal" + finalVal);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);

				}
			}

			// newly added

			if ((billyear == 2016)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					if (billMonth == 1) {
						BigDecimal tempBasic = empSalaryTxnNew.getAmount();
						logger.info("Basic Before Adding" + tempBasic.doubleValue());
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(113));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
					}

					else if (billMonth > 1 && billMonth < 9) {

						BigDecimal tempBasic = empSalaryTxnNew.getAmount();
						logger.info("Basic Before Adding" + tempBasic.doubleValue());
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(119));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);

					}

					else if (billMonth >= 9) {

						BigDecimal tempBasic = empSalaryTxnNew.getAmount();
						logger.info("Basic Before Adding" + tempBasic.doubleValue());
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(125));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
						// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
						ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);

					}

				}
			}

			// added by saurabh for DA rate of 2017

			if (billyear == 2017
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth <= 3) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(125));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
					} else if (billMonth > 3 && billMonth <= 8) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(132));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
					} else {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(136));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
					}
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}

			// ended by saurabh for DA RATE changes for 2017

			// added by saurabh s for DA rate of 2018

			if (billyear == 2018
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth == 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(136));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
					}
					// ---Added By Shivram
					else if (billMonth >= 1 && billMonth <= 9) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(139));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
					}
					// ---Added By Shivram
					else if (billMonth >= 10 && billMonth <= 12) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(142));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
						// logger.info(" gayathri tempDaValNew"+tempDaValNew);
						logger.info("sunitha finalVal" + finalVal);
					}
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}

			// ended by Saurabh S for DA RATE changes for 2018

			// -------Added By Shivram 25012019
			if (billyear == 2019
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1) && (billMonth <= 6)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(142L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 7) && (billMonth <= 11)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(154L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if (billMonth == 12) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(164L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
				}
				empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
				ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);

			}

			if ((billyear == 2020)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(164L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
				}
				empSalaryTxn.setAllwDedCode(8L);
				ruleMap.put(Long.valueOf(8L), empSalaryTxn);
			}

			if ((billyear == 2021)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(164L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
				}
				empSalaryTxn.setAllwDedCode(8L);
				ruleMap.put(Long.valueOf(8L), empSalaryTxn);
			}

			if ((billyear == 2022)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1 && billMonth <= 2) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(189L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 3) && (billMonth <= 7)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(196L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 8)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(203L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("sunitha finalVal" + finalVal);
					}
				}
				empSalaryTxn.setAllwDedCode(8L);
				ruleMap.put(Long.valueOf(8L), empSalaryTxn);
			}

			if ((billyear >= 2023)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1 && billMonth <6) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(212L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("sunitha finalVal" + finalVal);
					} else if (billMonth >=6) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(221L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("sunitha finalVal" + finalVal);
					}

				}
				empSalaryTxn.setAllwDedCode(8L);
				ruleMap.put(Long.valueOf(8L), empSalaryTxn);
			}

			// --------End By Shivram

			// ----Added By Shivram 2019 DA Rate 7PC Changes
			// add 7PC DA condition
			if (billyear == 2019
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SEVENTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1) && (billMonth <= 6)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(9L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("Shivram tempDaValNew --- " + tempDaValNew);
						this.logger.info("Shivram finalVal ----" + finalVal);
					} else if ((billMonth >= 7) && (billMonth <= 11)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(12L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("Shivram tempDaValNew --- " + tempDaValNew);
						this.logger.info("Shivram finalVal ----" + finalVal);
					} else if (billMonth == 12) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(17L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("Shivram tempDaValNew --- " + tempDaValNew);
						this.logger.info("Shivram finalVal ----" + finalVal);
					}
					logger.info("DA rate is taking old for 7th Pay 9 Shivram");
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					logger.info("DA rate is taking old for 7th Pay 9 Shivram" + empSalaryTxn.toString());
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}
			if ((billyear == 2020)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SEVENTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(17L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("Shivram tempDaValNew --- " + tempDaValNew);
						this.logger.info("Shivram finalVal ----" + finalVal);
					}
					this.logger.info("DA rate is taking old for 7th Pay 9 Shivram");
					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 7th Pay 9 Shivram" + empSalaryTxn.toString());
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2021)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SEVENTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(17L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info("Shivram tempDaValNew --- " + tempDaValNew);
						this.logger.info("Shivram finalVal ----" + finalVal);
					}
					this.logger.info("DA rate is taking old for 7th Pay 9 Shivram");
					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 7th Pay 9 Shivram" + empSalaryTxn.toString());
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2022)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SEVENTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1 && billMonth <= 2) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(17L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					} else if ((billMonth >= 3) && (billMonth <= 7)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(31L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("Shivram finalVal ----" + finalVal);
					} else if ((billMonth >= 8)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(34L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("Shivram finalVal ----" + finalVal);
					}

					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 7th Pay 9 Shivram" + empSalaryTxn.toString());
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear >= 2023) && (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SEVENTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1 &&  billMonth < 6)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(38L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("naveen Sinha finalVal ----" + finalVal);
					}else if (billMonth>=6) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(42L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("naveen Sinha finalVal ----" + finalVal);
					}  

					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 7th Pay 9 Shivram" + empSalaryTxn.toString());
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			// -------End By Shivram

			// added by vaibhav : end
			if (billMonth == 3 && billyear == 2012
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);

				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic
							.multiply(BigDecimal.valueOf(PayrollConstants.PAYROLL_OLD_DA_FIFTH_PAY));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);

					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 5th Pay");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}
			// code by Mum dev

			else if (billMonth <= 11 && billyear == 2012
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);

				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic
							.multiply(BigDecimal.valueOf(PayrollConstants.OLD_FIFTH_PAYCOMMISION_DA_RATE));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);

					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking old for 139 5th Pay");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}

			else if (((billMonth >= 8 && billyear == 2014) || (billMonth >= 1 && billyear == 2015))
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);

				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(200));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
					empSalaryTxn.setAmount(finalVal);
					// logger.info("roshan tempBasic amt"+tempBasic);
					logger.info("roshan fianl amt" + finalVal);
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					// logger.info("DA rate is taking 200 for 5th Pay");
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}

			// Edited by Akshay on 07/12/2017
			else if ((billyear == 2016)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if (billMonth == 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(223));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
					} else if (billMonth > 1 && billMonth < 9) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(234));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
					} else if (billMonth >= 9 && billMonth <= 12) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(245));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}

			else if ((billyear == 2017)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if (billMonth >= 1 && billMonth <= 7) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(245));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
					} else if (billMonth == 8 || billMonth == 9) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(256));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
					} else if (billMonth > 9) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(264));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}
			// added by saurabh s for 5th pc da rate
			else if ((billyear == 2018)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if (billMonth == 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(264));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
					} else if (billMonth >= 2 && billMonth <= 12) {// ------Added
																	// By
																	// Shivram
																	// 30012019
																	// (billMonth
																	// >=2 &&
																	// billMonth
																	// <= 12)
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(268));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						logger.info("Testingggggg " + finalVal);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}
			// Ended by saurabh s for da rate

			// ------Added By Shivram 30012019
			else if ((billyear == 2019)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = ruleMap.get(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if (billMonth >= 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(268));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(PayrollConstants.PAYROLL_DA_ID);
					ruleMap.put(PayrollConstants.PAYROLL_DA_ID, empSalaryTxn);
				}
			}

			else if ((billyear >= 2020)
					&& (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if (billMonth >= 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(268L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}

			for (int i = 0; i < allowTypeMst.size(); i++) {
				parResult = 0;
				long allowCode = 0;
				allowCode = ((AllwValCustomVO) allowTypeMst.get(i)).getAllwID();
				if (allowEdpMap.get("" + allowCode) != null)
					edpCode = allowEdpMap.get("" + allowCode).toString();
				else
					edpCode = "";

				Class paybillClass = payBillVO.getClass();
				Method method = null;

				// performance improvement - Manish
				// HrPayAllowTypeMst hrPayAllowTypeMst =
				// (HrPayAllowTypeMst)daoHibernateImpl.read(allowCode);
				// ended

				long allwDedId = ((AllwValCustomVO) allowTypeMst.get(i)).getAllwDedId();
				logger.info("Allow Deduc Id is " + allwDedId + " for allw Mster Pk is " + allwDedId + " for empId is "
						+ empId);
				if (allwDedId != 0) {
					HrPayEmpSalaryTxn empSalaryTxn = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(allwDedId));
					if (empSalaryTxn != null) {
						String cityClassHRA = "";
						String cityHRAStr = "";
						char cityHRA = '\000';

						if (empPaybillVO.getPostCityClass() != null) {
							cityClassHRA = empPaybillVO.getPostCityClass();
							char[] cityClassCharHRA = cityClassHRA.toCharArray();
							cityHRA = cityClassCharHRA[0];
							cityHRAStr = String.valueOf(cityHRA);
						}

						/* Added By Shivram */
						if ((basicForSvnPC.longValue() > 0) && (((monthGiven >= 7) && (yearGiven == 2017))
								|| (yearGiven > 2017) && ((monthGiven < 10) && (yearGiven == 2021)))) {
							if (allwDedId == 17) {
								amt = empSalaryTxn.getAmount();
								// int amount = Integer.parseInt(empSalaryTxn.getAmount().toString());
								Long amount = basicForSvnPC;
								if ((cityHRAStr.equals("X"))) {
									if (amount < 5400) {
										amt = BigDecimal.valueOf(5400);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount)
												.multiply(BigDecimal.valueOf(24));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
										/* Added By Shivram 13062019 */
										if (amt.intValue() < 5400) {
											amt = BigDecimal.valueOf(5400);
										}
										/* Ended By Shivram 13062019 */
									}
								} else if ((cityHRAStr.equals("Y"))) {
									if (amount < 3600) {
										amt = BigDecimal.valueOf(3600);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount)
												.multiply(BigDecimal.valueOf(16));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
										/* Added By Shivram 13062019 */
										if (amt.intValue() < 3600) {
											amt = BigDecimal.valueOf(3600);
										}
										/* Ended By Shivram 13062019 */
									}
								} else if ((cityHRAStr.equals("Z"))) {
									if (amount < 1800) {
										amt = BigDecimal.valueOf(1800);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount)
												.multiply(BigDecimal.valueOf(8));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
										/* Added By Shivram 13062019 */
										if (amt.intValue() < 1800) {
											amt = BigDecimal.valueOf(1800);
										}
										/* Ended By Shivram 13062019 */
									}
								} else {
									logger.info("Calculate 7PC DA =======" + amt);
									amt = BigDecimal.valueOf(basicForSvnPC);
								}
							} else {
								logger.info("Calculate HRA=======" + amt);
								amt = empSalaryTxn.getAmount();
							}
						}

						else if ((basicForSvnPC.longValue() > 0) && (((monthGiven >= 10) && (yearGiven == 2021))
								|| ((monthGiven >= 1) && (yearGiven >= 2022)))) {
							if (allwDedId == 17) {
								amt = empSalaryTxn.getAmount();
								// int amount = Integer.parseInt(empSalaryTxn.getAmount().toString());
								Long amount = basicForSvnPC;
								if ((cityHRAStr.equals("X"))) {
									if (amount < 5400) {
										amt = BigDecimal.valueOf(5400);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount)
												.multiply(BigDecimal.valueOf(27));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
										/* Added By Shivram 13062019 */
										if (amt.intValue() < 5400) {
											amt = BigDecimal.valueOf(5400);
										}
										/* Ended By Shivram 13062019 */
									}
								} else if ((cityHRAStr.equals("Y"))) {
									if (amount < 3600) {
										amt = BigDecimal.valueOf(3600);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount)
												.multiply(BigDecimal.valueOf(18));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
										/* Added By Shivram 13062019 */
										if (amt.intValue() < 3600) {
											amt = BigDecimal.valueOf(3600);
										}
										/* Ended By Shivram 13062019 */
									}
								} else if ((cityHRAStr.equals("Z"))) {
									if (amount < 1800) {
										amt = BigDecimal.valueOf(1800);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount)
												.multiply(BigDecimal.valueOf(9));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_HALF_UP);
										/* Added By Shivram 13062019 */
										if (amt.intValue() < 1800) {
											amt = BigDecimal.valueOf(1800);
										}
										/* Ended By Shivram 13062019 */
									}
								} else {
									logger.info("Calculate 7PC DA =======" + amt);
									amt = BigDecimal.valueOf(basicForSvnPC);
								}
							} else {
								logger.info("Calculate HRA=======" + amt);
								amt = empSalaryTxn.getAmount();
							}
						} else {
							logger.info("Calculate six pay  HRA=======" + amt);
							amt = empSalaryTxn.getAmount();
						}
						/* Added by Naveen Priya Sinha 09052022 */

						if ((basicForSvnPC.longValue() > 0) && (PaycommissionId.longValue() == 700005)
								&& ((billMonth >= 4) && (billyear >= 2022))) {
							if (allwDedId == 15) {
								amt = new BigDecimal(0);
								if (basicForSvnPC > 0 && (cityHRAStr.equals("X"))) {
									if (empPaybillVO.getIsPhyHandicapped().equals("TRUE")) {
										if ((sevenPcLevel >= 1 && sevenPcLevel <= 6 && basicForSvnPC >= 24200)) {
											amt = BigDecimal.valueOf(5400);
										} else if ((sevenPcLevel >= 1 && sevenPcLevel <= 6)) {
											amt = BigDecimal.valueOf(2250);
										} else if ((sevenPcLevel >= 7 && sevenPcLevel <= 19)) {
											amt = BigDecimal.valueOf(5400);
										} else if ((sevenPcLevel >= 20)) {
											amt = BigDecimal.valueOf(10800);
										}
									}
									// else if((empPaybillVO.getIsPhyHandicapped().equals("false"))){
									else {
										if (sevenPcLevel >= 1 && sevenPcLevel <= 6 && basicForSvnPC >= 24200) {
											amt = BigDecimal.valueOf(2700);
										} else if (sevenPcLevel >= 1 && sevenPcLevel <= 6) {
											amt = BigDecimal.valueOf(1000);
										} else if ((sevenPcLevel >= 7 && sevenPcLevel <= 19)) {
											amt = BigDecimal.valueOf(2700);
										} else if ((sevenPcLevel >= 20)) {
											amt = BigDecimal.valueOf(5400);
										}
									}
								} else if (cityHRAStr.equals("Y") || (cityHRAStr.equals("Z"))) {
									if (empPaybillVO.getIsPhyHandicapped().equals("TRUE")) {
										if ((sevenPcLevel >= 1 && sevenPcLevel <= 6 && basicForSvnPC >= 24200)) {
											amt = BigDecimal.valueOf(2700);
										} else if ((sevenPcLevel >= 1 && sevenPcLevel <= 6)) {
											amt = BigDecimal.valueOf(2250);
										} else if ((sevenPcLevel >= 7 && sevenPcLevel <= 19)) {
											amt = BigDecimal.valueOf(2700);
										} else if ((sevenPcLevel >= 20)) {
											amt = BigDecimal.valueOf(5400);
										}
									} else {
										if (sevenPcLevel >= 1 && sevenPcLevel <= 6 && basicForSvnPC >= 24200) {
											amt = BigDecimal.valueOf(1350);
										} else if (sevenPcLevel >= 1 && sevenPcLevel <= 6) {
											amt = BigDecimal.valueOf(675);
										} else if ((sevenPcLevel >= 7 && sevenPcLevel <= 19)) {
											amt = BigDecimal.valueOf(1350);
										} else if ((sevenPcLevel >= 20)) {
											amt = BigDecimal.valueOf(2700);
										}
									}
								}
							}

						}
						logger.info("NPS in if 20032023 " + allwDedId + " from gui based rule engine is ");

					}
					/** NPS calculation By Naveen Priya Sinha */
					/* NPS contribution for Employer Contribution (NPS 14%)=208 */
					logger.info("NPS 20032023 " + allwDedId + " from gui based rule engine is ");
					if (allwDedId == 208) {
						HrPayEmpSalaryTxn empSalaryTxn1 = new HrPayEmpSalaryTxn();
						Double tempDA = 0.0;
						BigDecimal basicDA;
						Double tempBasic = payBillVO.getBasic0101();
						logger.info("FOR NPS " + allwDedId + " from gui based rule engine is tembasic" + tempBasic + " "
								+ yearGiven);
						logger.info("FOR NPS " + allwDedId + " from gui based rule engine is basicForSvnPC"
								+ basicForSvnPC);
						logger.info("FOR NPS " + allwDedId + " from gui based rule engine is payBillVO.getAllow9207()"
								+ payBillVO.getAllow9207());

						if ((basicForSvnPC.longValue() > 0L) && (yearGiven >= 2017)) {
							tempDA = payBillVO.getAllow9207();
						} else {
							tempDA = payBillVO.getAllow0103();
						}
						basicDA = new BigDecimal(tempBasic + tempDA);
						emplyrDeducContri = basicDA.multiply(BigDecimal.valueOf(14L));
						amt = emplyrDeducContri.divide(BigDecimal.valueOf(100L), 0, BigDecimal.ROUND_UP);
						
						/* NPS Allowance by arrear */
						// added by for DCPS Online Allowance and deduction DCPS
						double RegularAllowDedamt1 = 0;
						double RegularAllowDedamt3 = 0;
						double RegularAllowDedamt2 = 0;
						double RegularAllowDedamt4 = 0;

						Map empDCPSValNPSOnlineMap = (Map) inputMap.get("empDCPSValNPSOnlineMap");
						if (empDCPSValNPSOnlineMap.containsKey(orgEmpId)) {
							Map dcpsValNPSOnlineMap = (HashMap) empDCPSValNPSOnlineMap.get(orgEmpId);
							logger.info("NPS Online Allowance and deduction DCPS Map found for emp id " + orgEmpId + " Map is "
									+ dcpsValNPSOnlineMap);
							if (dcpsValNPSOnlineMap.containsKey(700046l))
								RegularAllowDedamt1 = Double.valueOf(dcpsValNPSOnlineMap.get(700046l).toString());
							if (dcpsValNPSOnlineMap.containsKey(700047l))
								RegularAllowDedamt2 = Double.valueOf(dcpsValNPSOnlineMap.get(700047l).toString());
							if (dcpsValNPSOnlineMap.containsKey(700049l))
								RegularAllowDedamt3 = Double.valueOf(dcpsValNPSOnlineMap.get(700049l).toString());
							if (dcpsValNPSOnlineMap.containsKey(700048l))
								RegularAllowDedamt4 = Double.valueOf(dcpsValNPSOnlineMap.get(700048l).toString());// 700046
						}
							empDCPSValNPSOnlineMap = null;
							BigDecimal tempNPSAllow  ;
							
						 	tempNPSAllow = new BigDecimal(RegularAllowDedamt1 + RegularAllowDedamt2 + RegularAllowDedamt3+ RegularAllowDedamt4);
						 	amt=amt.add(tempNPSAllow);
				 
							/* NPS Allowance and deduction by arrear */
							
							/*NPS NAVEEN */
						
						empSalaryTxn1.setAmount(amt);
						empSalaryTxn1.setAllwDedCode(PayrollConstants.PAYROLL_EMPR_NPS_ID);
						ruleMap.put(PayrollConstants.PAYROLL_EMPR_NPS_ID, empSalaryTxn1);
						logger.info("NPS " + allwDedId + " from gui based rule engine is " + amt);
						

						
					}

					/** NPS calculation By Naveen Priya Sinha */

				}

				else {
					String methodName = "calculate" + ((AllwValCustomVO) allowTypeMst.get(i)).getAllowDesc();
					// logger.info("method name is "+methodName+" edpCode "+edpCode);
					Class class1 = null;
					class1 = salaryRuls6thPay.getClass();
					if (payCommissionId == commissionFiveId
							|| payCommissionId == PayrollConstants.PAYROLL_SHETTY_PAYCOMMISSION
							|| payCommissionId == PayrollConstants.PAYROLL_COSOLIDATED_PAYCOMMISSION)
						class1 = salaryRules.getClass();
					if (payCommissionId == commissionNewSevenId) {
						class1 = salaryRuls7thPay.getClass();
					}
					try {
						method = class1.getMethod(methodName, Map.class);
						logger.info("method found for " + methodName);
					} catch (Exception e) {
						logger.info("No Rule found for " + ((AllwValCustomVO) allowTypeMst.get(i)).getAllowDesc());
					}
					if (method != null) {
						if (payCommissionId == commissionFiveId
								|| payCommissionId == PayrollConstants.PAYROLL_SHETTY_PAYCOMMISSION
								|| payCommissionId == PayrollConstants.PAYROLL_COSOLIDATED_PAYCOMMISSION)
							parResult = (Double) method.invoke(salaryRules, input);
						else if (payCommissionId == commissionNewSevenId) {
							parResult = ((Double) method.invoke(salaryRuls7thPay, new Object[] { input }))
									.doubleValue();
						} else
							parResult = (Double) method.invoke(salaryRuls6thPay, input);
						parResult = Math.round(parResult);
						logger.info("Result is " + parResult + "for allow code" + allowCode + " allow name is "
								+ ((AllwValCustomVO) allowTypeMst.get(i)).getAllowDesc() + " and edp code is "
								+ edpCode);
					}
				}
				String mthdName = "setAllow" + edpCode;
				try {
					method = paybillClass.getMethod(mthdName, double.class);
					if (allwDedId != 0) {
						method.invoke(payBillVO, amt.doubleValue());
						totalCompAllw += amt.doubleValue();
					} else {
						method.invoke(payBillVO, parResult);
						totalCompAllw += parResult;
					}
				} catch (Exception e) {
					logger.info("Exeception occured in Core Logic " + e);
					logger.error("Error is: " + e.getMessage());
				}

			}

			allowTypeMst = null;
			
			
			
			// calculation of rule based allowance ends

			// double tempHRA = payBillVO.getAllow0110();
			logger.info("HRA set for payBillVO-->" + payBillVO.getAllow0110());

			// 20 jan 2012 check here
			double totalNonCompAllw = 0;
			// List<HrPayEdpCompoMpg> edpListEditable =
			// (List)inputMap.get("edpListEditable");
			// DeptCompMPGDAOImpl deptcompo = new
			// DeptCompMPGDAOImpl(HrPayLocComMpg.class,
			// serv.getSessionFactory());
			// String editableAllowIDs = "";
			// Map editableAllowValues = new HashMap();
			Map<Long, List<AllwValCustomVO>> mapEditableAllowance = (Map<Long, List<AllwValCustomVO>>) inputMap
					.get("mapEditableAllowance");

			List<AllwValCustomVO> customVo = mapEditableAllowance.get(empid);
			mapEditableAllowance = null;
			double Val = 0;
			String edp = "";
			if (customVo != null)
				for (int i = 0; i < customVo.size(); i++) {
					Val = 0;
					long compoId = customVo.get(i).getAllwID(); // empAllowVOList.get(i).getHrPayAllowTypeMst().getAllowCode();
					Val = customVo.get(i).getAllowanceVal();// (double)empAllowVOList.get(i).getEmpAllowAmount();
					Val = Math.round(Val);
					edp = allowEdpMap.get("" + compoId).toString();
					String payBillMthdName = "setAllow" + edp;
					Class pay = payBillVO.getClass();
					Method payMthd = pay.getMethod(payBillMthdName, double.class);
					payMthd.invoke(payBillVO, Val);
					logger.info(
							"Value is for editable Allowance method-->" + payBillMthdName + "---and Value is " + Val);
					totalNonCompAllw += Val;

				}
			customVo = null;
			// 21 jan 2012 end
			logger.info("total of noncomputaional Allowances is " + totalNonCompAllw);
			// calculation of HRA and HRR

			Map hrrMap = input;
			hrrMap.put("otherDtlsVO", empPaybillVO);

			// added by khushal for hrr calculation
			double hrr = 0;
			// double oldHra=0;
			// double revisedHra=0;

			logger.info("For Emp Id--" + empId + " total of computaional allowances including HRA-->" + totalCompAllw);

			// double gross =
			// totalCompAllw+totalNonCompAllw+Math.round((Double)input.get("revBasic"));
			// double gross =
			// totalCompAllw+totalNonCompAllw+Math.round((Double)input.get("revBasic"));
			/* Added By Shivram */
			double gross = 0;
			if ((basicForSvnPC.longValue() > 0)
					&& (((monthGiven >= 10) && (yearGiven == 2016)) || (yearGiven >= 2017))) {
				gross = totalCompAllw + totalNonCompAllw + basicForSvnPC.longValue();
			} else {
				gross = totalCompAllw + totalNonCompAllw + Math.round(((Double) input.get("revBasic")).doubleValue());
			}
			/* Ended By Shivram */
			// DeductionDtlsDAOImpl deductionDaoImpl=new
			// DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			Map deducEdpMap = (HashMap) (inputMap.containsKey("deducEdpMap") ? inputMap.get("deducEdpMap")
					: new HashMap());
			logger.info("deducEdpMap--->" + deducEdpMap.toString());

			List<DeductionCustomVO> deducTypeMst = new ArrayList();
			Map deducAllowCompoMap = (HashMap) (inputMap.get("empDeducCompoMap") != null
					? inputMap.get("empDeducCompoMap")
					: new HashMap());
			if (deducAllowCompoMap.containsKey(empPaybillVO.getEisEmpId())) {
				logger.info("Deduction mapping map found for emp id " + empPaybillVO.getEisEmpId());
				logger.info(" " + deducAllowCompoMap.get(empPaybillVO.getEisEmpId()));
				deducTypeMst = (List) (deducAllowCompoMap.get(empPaybillVO.getEisEmpId()) != null
						? deducAllowCompoMap.get(empPaybillVO.getEisEmpId())
						: new ArrayList());
				deducAllowCompoMap = null;
			}

			hrr = empPaybillVO.getQtrRentAmt();
			// logger.info("hrr value from EmpDeducDtls is "+hrr);

			// boolean flag =false;
			boolean isHRRMapped = false;

			// daoHibernateImpl = new
			// GenericDaoHibernateImpl(HrPayDeducTypeMst.class);
			// daoHibernateImpl.setSessionFactory(serv.getSessionFactory());

			double totalCompDeduc = 0;
			// double revenueStamp = 0;
			for (int i = 0; i < deducTypeMst.size(); i++) {

				parResult = 0;
				long deducCode = 0;
				deducCode = deducTypeMst.get(i).getDeducId();
				edpCode = deducEdpMap.get("" + deducCode).toString();

				// HrPayDeducTypeMst hrPayDeducTypeMst =
				// (HrPayDeducTypeMst)daoHibernateImpl.read(deducCode);
				long allwDedId = deducTypeMst.get(i).getAllwDedId();
				// logger.info("Allow Deduc Id is "+allwDedId+" for deduc Mster Pk is
				// "+allwDedId
				// + " for empId is "+empId);

				Class paybillClass = payBillVO.getClass();
				Method method = null;
				if (allwDedId != 0) {
					HrPayEmpSalaryTxn empSalaryTxn = ruleMap.get(allwDedId);
					if (empSalaryTxn != null)
						amt = empSalaryTxn.getAmount();
					else {
						amt = new BigDecimal(0);
					}

					// amt = new BigDecimal(0);
					// logger.info("amt got from gui based rule engine is "+amt);
				} else {
					String methodName = "calculate" + deducTypeMst.get(i).getDeducDesc();
					Class class1 = null;
					if (payCommissionId == commissionFiveId
							|| payCommissionId == PayrollConstants.PAYROLL_SHETTY_PAYCOMMISSION
							|| payCommissionId == PayrollConstants.PAYROLL_COSOLIDATED_PAYCOMMISSION) {
						class1 = salaryRules.getClass();
					} else if (payCommissionId == commissionNewSevenId) {
						class1 = salaryRuls7thPay.getClass();
					} else// if(payCommissionId == sixthPayCommId)
					{
						class1 = salaryRuls6thPay.getClass();
					}
					/*
					 * else throw new Exception("commission id is null");
					 */try {
						logger.info("The name for method in generatebillservice corelogic is:" + methodName);
						method = class1.getMethod(methodName, Map.class);

					} catch (Exception e) {
						logger.info("Exception occured in core logic ....." + e);
					}
					if (method != null) {
						if (payCommissionId == commissionFiveId
								|| payCommissionId == PayrollConstants.PAYROLL_SHETTY_PAYCOMMISSION
								|| payCommissionId == PayrollConstants.PAYROLL_COSOLIDATED_PAYCOMMISSION)
							parResult = (Double) method.invoke(salaryRules, input);
						else
							// if(payCommissionId == sixthPayCommId)
							parResult = (Double) method.invoke(salaryRuls6thPay, input);
						/*
						 * else throw new Exception("Commission ID not Defined");
						 */
						parResult = Math.round(parResult);
						logger.info("Result is " + parResult + "for deduc code" + deducCode);
					}
				}
				String mthdName = "setDeduc" + edpCode;
				try {
					method = paybillClass.getMethod(mthdName, double.class);
					if (allwDedId != 0) {

						/*
						 * if (allwDedId == 135 && amt.doubleValue() == 1) { revenueStamp =
						 * amt.doubleValue(); logger.info("Revenue Stamp Amount Set as setDeduc9135 " +
						 * revenueStamp); }
						 */

						method.invoke(payBillVO, amt.doubleValue());
						totalCompDeduc += amt.doubleValue();
					} else {
						method.invoke(payBillVO, parResult);
						totalCompDeduc += parResult;
					}
					logger.info("Method Name is " + mthdName + " setting value is " + parResult + " deducCode is "
							+ deducCode);
				} catch (Exception e) {
					logger.error("Error is: " + e.getMessage());
					logger.info("Exception occured in core logic ....." + e);
				}

				if (deducCode == hrrId)
					isHRRMapped = true;
			}
			deducTypeMst = null;
			logger.info("Total Computational Deduction before considering DCPS Integration" + totalCompDeduc);

			// //for DCPS>>>> Integration...
			// DeductionDtlsDAOImpl empDuducDtlsDAO = new
			// DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			// changed By ankit for DCPS
			// totalCompDeduc -=
			// (payBillVO.getDeduc9534()+payBillVO.getDeduc9535()+payBillVO.getDeduc9536()+payBillVO.getDeduc9537());
			totalCompDeduc -= (payBillVO.getDeduc9535() + payBillVO.getDeduc9536() + payBillVO.getDeduc9537());
			// added by Khushal for DCPS
			double amt1 = 0;
			double amt2 = 0;
			double amt3 = 0;
			double amt4 = 0;

			long deducEmpId = empPaybillVO.getOrgEmpId();

			Map empDCPSValMap = (Map) inputMap.get("empDCPSValMap");
			if (empDCPSValMap.containsKey(deducEmpId)) {
				Map dcpsValMap = (HashMap) empDCPSValMap.get(deducEmpId);
				logger.info("DCPS Map found for emp id " + deducEmpId + " Map is " + dcpsValMap);
				if (dcpsValMap.containsKey(700046l))
					amt1 = Double.valueOf(dcpsValMap.get(700046l).toString());
				if (dcpsValMap.containsKey(700047l))
					amt2 = Double.valueOf(dcpsValMap.get(700047l).toString());
				if (dcpsValMap.containsKey(700049l))
					amt3 = Double.valueOf(dcpsValMap.get(700049l).toString());
				if (dcpsValMap.containsKey(700048l))
					amt4 = Double.valueOf(dcpsValMap.get(700048l).toString());// 700046
			}
			empDCPSValMap = null;

			/* NPS Deduction by arrear */
			// added by for DCPS Online Allowance and deduction DCPS
			double RegularDedamt1 = 0;
			double RegularDedamt3 = 0;
			double RegularDedamt2 = 0;
			double RegularDedamt4 = 0;

			Map empDCPSValNPSOnlineDeducMap = (Map) inputMap.get("empDCPSValNPSOnlineMap");
			if (empDCPSValNPSOnlineDeducMap.containsKey(deducEmpId)) {
				Map dcpsValNPSOnlineDeucMap = (HashMap) empDCPSValNPSOnlineDeducMap.get(deducEmpId);
				logger.info("NPS Online Allowance and deduction DCPS Map found for emp id " + deducEmpId + " Map is "
						+ dcpsValNPSOnlineDeucMap);
				if (dcpsValNPSOnlineDeucMap.containsKey(700046l))
					RegularDedamt1 = Double.valueOf(dcpsValNPSOnlineDeucMap.get(700046l).toString());
				if (dcpsValNPSOnlineDeucMap.containsKey(700047l))
					RegularDedamt2 = Double.valueOf(dcpsValNPSOnlineDeucMap.get(700047l).toString());
				if (dcpsValNPSOnlineDeucMap.containsKey(700049l))
					RegularDedamt3 = Double.valueOf(dcpsValNPSOnlineDeucMap.get(700049l).toString());
				if (dcpsValNPSOnlineDeucMap.containsKey(700048l))
					RegularDedamt4 = Double.valueOf(dcpsValNPSOnlineDeucMap.get(700048l).toString());// 700046
			}
			empDCPSValNPSOnlineDeducMap = null;
			logger.info("NPS Online Allowance and deduction DCPS :" + payBillVO.getDeduc9142());
			logger.info("RegularAllowDedamt1:::" + payBillVO.getDeduc9534());
			logger.info("RegularAllowDedamt1:::" + payBillVO.getDeduc9535());
			logger.info("RegularAllowDedamt1:::" + payBillVO.getDeduc9536());
			logger.info("RegularAllowDedamt1:::" + payBillVO.getDeduc9537());
		
			Double tempNPSDeduc = 0D;
			tempNPSDeduc = payBillVO.getDeduc9142();
			Double RegularDedamtFnal=RegularDedamt1 + RegularDedamt2 + RegularDedamt3+ RegularDedamt4;
			payBillVO.setDeduc9142(tempNPSDeduc + RegularDedamtFnal);
			
			/* NPS Allowance and decution by arrear */

			logger.info("NPS DEDUCTION :" + payBillVO.getDeduc9142());
			logger.info("amt1:::" + payBillVO.getDeduc9534());
			logger.info("amt2:::" + payBillVO.getDeduc9535());
			logger.info("amt3:::" + payBillVO.getDeduc9536());
			logger.info("amt4:::" + payBillVO.getDeduc9537());
			logger.info("" + amt1 + " " + amt2 + "" + amt3 + "" + amt4 + " eis  empId " + empPaybillVO.getOrgEmpId());
			// payBillVO.setDeduc9534(amt);
			// Commented by Ankit
			// payBillVO.setDeduc9534(amt1);

			Map<Long, List<DeductionCustomVO>> mapEditableDeduction = (Map<Long, List<DeductionCustomVO>>) inputMap
					.get("mapEditableDeduction");
			List<DeductionCustomVO> customVO = mapEditableDeduction.get(empid);
			mapEditableDeduction = null;

			// Commented by Ankit
			// totalCompDeduc+= payBillVO.getDeduc9534();

			// 20 jan 2012 check here
			logger.info("total of computational deductions is" + totalCompDeduc);

			// List<HrEisEmpCompMpg> deducListEditable =
			// deptcompo.getAllActiveEditableDeducByEmpId(locId,payCommissionId,empid,monthGiven,yearGiven);
			// DeductionDtlsDAOImpl deducDao = new
			// DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());

			edp = "";
			// HrPayDeductionDtls hrPayDeductionDtls=null;

			// String editableDeducIDs = "";

			/*
			 * for(int deducCounter=0;deducCounter<deducListEditable.size();deducCounter
			 * ++){ long compoId = new
			 * Long(deducListEditable.get(deducCounter).getCompId()); editableDeducIDs =
			 * editableDeducIDs+ compoId + ","; }
			 */
			/*
			 * if(editableDeducIDs!=null && editableDeducIDs.length()>2) editableDeducIDs =
			 * editableDeducIDs.substring(0,editableDeducIDs.length()-1);
			 * 
			 * List<HrPayDeductionDtls> deducEditableList=new
			 * ArrayList<HrPayDeductionDtls>(); if(editableDeducIDs!=null &&
			 * editableDeducIDs.trim()!="") deducEditableList
			 * =deducDao.getHrPayDeductionDtls
			 * (empid,empCurrentStatus,editableDeducIDs,monthGiven,yearGiven);
			 */
			double totalNonCompDeduc = 0;
			// 21 jan 2012

			if (customVO != null)
				for (int i = 0; i < customVO.size(); i++) {
					Val = 0;
					long compoId = customVO.get(i).getDeducId(); // deducEditableList.get(i).getHrPayDeducTypeMst().getDeducCode();
					// logger.info("compoId:::"+compoId);
					if (compoId != hrrId) {
						Val = (double) customVO.get(i).getDeductionVal();// deducEditableList.get(i).getEmpDeducAmount();

						Val = Math.round(Val);

						logger.info("compo Id for deduc is " + compoId + " And value is " + Val);
						if (deducEdpMap.containsKey("" + compoId))
							edp = deducEdpMap.get("" + compoId).toString();

						// logger.info("edp::"+edp);
						String payBillMthdName = "";
						if (edp != "") {
							// logger.info("inside ::::") ;
							payBillMthdName = "setDeduc" + edp;
							Class pay = payBillVO.getClass();
							Method payMthd = pay.getMethod(payBillMthdName, double.class);
							payMthd.invoke(payBillVO, Val);
							totalNonCompDeduc += Val;
						}
					}
				}
			totalNonCompDeduc = totalNonCompDeduc - payBillVO.getDeduc9535() - payBillVO.getDeduc9536()
					- payBillVO.getDeduc9537();
			// if(customVO != null){
			// for(int i=0;i<customVO.size();i++){
			// if(customVO.get(i).getDeducId() == 120)
			payBillVO.setDeduc9535(amt2);
			// if(customVO.get(i).getDeducId() == 121)
			payBillVO.setDeduc9536(amt3);
			// if(customVO.get(i).getDeducId() == 122)
			payBillVO.setDeduc9537(amt4);
			// payBillVO.setDeduc9142(payBillVO.getDeduc9142());
			logger.info("NPS :" + payBillVO.getDeduc9142());
			// payBillVO.setDeduc9135(revenueStamp); // comment by lekhchand revenueStamp
			// }
			// }
			
			
			totalNonCompDeduc = totalNonCompDeduc + payBillVO.getDeduc9535() + payBillVO.getDeduc9536()
					+ payBillVO.getDeduc9537()+RegularDedamtFnal;
			// 21 jan 2012 end
			if (isHRRMapped == true) {
				totalNonCompDeduc += hrr;
				payBillVO.setDeduc9550(Math.round(hrr));
				isHRRMapped = false;
			}
			logger.info("Total Non Computational Deduction before considering DCPS Integration" + totalNonCompDeduc
					+ " including HRR--" + hrr);

			// for emp mst
			HrEisEmpMst hrEisObj = new HrEisEmpMst();
			// hrEisObj = (HrEisEmpMst) gImpl.read(empid);
			hrEisObj.setEmpId(empid);
			logger.info("Emp id to be set in paybil table is " + empid);
			payBillVO.setHrEisEmpMst(hrEisObj);

			// manish for total amount
			psrNo = empPaybillVO.getPostPSRNo();
			payBillVO.setPsrNo(psrNo);

			// Added by manish for broken period functionality
			Map<Long, List<EmpBrokenPeriodVO>> empBrokenMap = (HashMap) (inputMap.get("empBrokenMap") != null
					? inputMap.get("empBrokenMap")
					: new HashMap());
			BrokenPeriodDAOImpl brokenPeriodDAOImpl = new BrokenPeriodDAOImpl(MstBrokenPeriodPay.class,
					serv.getSessionFactory());

			List<EmpBrokenPeriodVO> brokenPeriodList = empBrokenMap.containsKey(empPaybillVO.getEisEmpId())
					? empBrokenMap.get(empPaybillVO.getEisEmpId())
					: new ArrayList();
			List<RltBrokenPeriodAllow> brokenAllw = null;
			List<RltBrokenPeriodDeduc> brokenDeduc = null;

			HrPayPaybill paybill = new HrPayPaybill();
			Class cls = paybill.getClass();
			Method paybillMethod = null;
			Method paybillGetter = null;
			double totalAllowance = 0;
			double totalDeduction = 0;

			paybill.setId(payBillId);
			paybill.setOrgPostMst(billPostMst);

			for (int i = 0; i < brokenPeriodList.size(); i++) {
				EmpBrokenPeriodVO mst = (EmpBrokenPeriodVO) brokenPeriodList.get(i);
				// logger.info("mst.getBrokenPeriodId() is " +
				// mst.getBrokenPeriodId());
				brokenAllw = brokenPeriodDAOImpl.selectAllBrokenPeriodAllowancesForBrknPrdId(mst.getBrokenPeriodId());
				brokenDeduc = brokenPeriodDAOImpl.selectAllBrokenPeriodDeductionsForBrknPrdId(mst.getBrokenPeriodId());

				paybill.setBasic0101(mst.getBasicAmount() + paybill.getBasic0101());
				for (int k = 0; k < brokenAllw.size(); k++) {
					// logger.info("Setting allowances from Broken period");
					// logger.info("Allow Map is " + allowEdpMap);
					if (brokenAllw.get(k) != null)
						logger.info("Allow code is " + brokenAllw.get(k).getAllowCode());
					else
						// logger.info("brokenAllw is null");
						logger.info("brokenAllw::" + brokenAllw.get(k));
					logger.info("getAllowCode::" + brokenAllw.get(k).getAllowCode());

					logger.info("getAllowCode2::" + allowEdpMap.get("" + brokenAllw.get(k).getAllowCode().toString()));
					if (brokenAllw.get(k) != null && brokenAllw.get(k).getAllowCode() != 0
							&& allowEdpMap.get("" + brokenAllw.get(k).getAllowCode().toString()) != null) {
						// if(brokenAllw.get(k)!=null &&
						// brokenAllw.get(k).getAllowCode()!=0) {
						// if(allowEdpMap.get(""+brokenAllw.get(k).getAllowCode().toString())!=null)
						// {
						// logger.info("Setting allowances from Broken period 2");
						String methodName = "setAllow"
								+ allowEdpMap.get("" + brokenAllw.get(k).getAllowCode().toString());
						paybillMethod = cls.getMethod(methodName, double.class);
						String getterMethod = "getAllow"
								+ allowEdpMap.get("" + brokenAllw.get(k).getAllowCode().toString());
						paybillGetter = cls.getMethod(getterMethod, null);
						paybillMethod.invoke(paybill, (brokenAllw.get(k).getAllowValue())
								+ (Double.parseDouble(paybillGetter.invoke(paybill, null).toString())));
						logger.info("Value for Broken Period Allowance " + brokenAllw.get(k).getAllowCode().toString()
								+ " in allowance loop is----" + brokenAllw.get(k).getAllowValue());
						totalAllowance += brokenAllw.get(k).getAllowValue();
					}
				}
				brokenAllw = null;
				logger.info("Total Allowance value after adding broken period allowances::" + totalAllowance);
				for (int j = 0; j < brokenDeduc.size(); j++) {

					// logger.info("Deduc Map is " + deducEdpMap);
					// logger.info("brokenDeduc:;"+brokenDeduc.get(j));
					// logger.info("brokenDeduc:;"+brokenDeduc.get(j).getDeducCode());
					// logger.info("brokenDeduc2::"+deducEdpMap.get(""+brokenDeduc.get(j).getDeducCode().toString()));
					if (brokenDeduc.get(j) != null && brokenDeduc.get(j).getDeducCode() != 0
							&& deducEdpMap.get("" + brokenDeduc.get(j).getDeducCode().toString()) != null) {
						String methodName = "setDeduc"
								+ deducEdpMap.get("" + brokenDeduc.get(j).getDeducCode().toString());
						paybillMethod = cls.getMethod(methodName, double.class);
						String getterMethod = "getDeduc"
								+ deducEdpMap.get("" + brokenDeduc.get(j).getDeducCode().toString());
						paybillGetter = cls.getMethod(getterMethod, null);
						paybillMethod.invoke(paybill, (brokenDeduc.get(j).getDeducValue())
								+ (Double.parseDouble(paybillGetter.invoke(paybill, null).toString())));
						logger.info("Value for Broken Period Deduction " + brokenDeduc.get(j).getDeducCode()
								+ " in Deduction loop is----" + brokenDeduc.get(j).getDeducValue());
						totalDeduction += brokenDeduc.get(j).getDeducValue();
					}
				}
				brokenDeduc = null;
				logger.info("Total Decution value after adding broken period allowances::" + totalDeduction);

			}
			brokenPeriodList = null;
			brokenAllw = null;
			brokenDeduc = null;
			totalAllowance += paybill.getBasic0101();
			logger.info("Total Allowance  after adding basic---" + paybill.getBasic0101() + "---" + totalAllowance);
			// logger.info("going to check whether Broken period entry is there or not");
			boolean isBrokenEntryExist = empBrokenMap.containsKey(empPaybillVO.getEisEmpId());
			empBrokenMap = null;
			if (isBrokenEntryExist) {
				logger.info("Inside if, Broken period entry found for emp id " + empId);
				paybill.setEmpLname(empPaybillVO.getEmpLname());
				payBillVO = paybill;
				gross = totalAllowance;
			}
			// ended

			double totLoan = 0;
			double faTaPa = 0;
			List lstHrPayLoanEmpDtls = null;
			List lstHrPayAdvEmpDtls = null;
			List totalLoanList = new ArrayList();

			// Loan part
			Map<Long, List> empLoanMap = (Map) inputMap.get("empLoanMap");
			// logger.info("Map for Loan is " + empLoanMap);

			List empLoanList = empLoanMap.containsKey(empId) ? empLoanMap.get(empId) : new ArrayList();
			empLoanMap = null;
			logger.info("Loan entry found for empId " + empId);
			for (int i = 0; i < empLoanList.size(); i++) {
				EmpPaybillLoanVO empPaybillLoanVO = (EmpPaybillLoanVO) empLoanList.get(i);
				// long empLoanId = empPaybillLoanVO.getEmpLoanId();

				// for 10-20 days issues by manoj
				edpCode = empPaybillLoanVO.getEdpCode() != null ? empPaybillLoanVO.getEdpCode() : "";
				long compoType = empPaybillLoanVO.getCompoType();
				String compoCode = empPaybillLoanVO.getCompoCode();

				orgEmpId = empPaybillVO.getOrgEmpId();
				// logger.info("You are Entered in the Loan Part:-");

				if (compoType == loanLookupId) {
					String payBillMthdName = "setLoan" + edpCode;
					String payBillIntMthdName = "setLoanInt" + edpCode;
					// logger.info("The Component Code is :-"+compoCode);
					Map loanMap = inputMap;
					loanMap.put("compoCode", compoCode);
					loanMap.put("month", monthGiven);
					loanMap.put("year", yearGiven);
					loanMap.put("EmpPaybillLoanVO", empPaybillLoanVO);
					if (payBillVO != null)
						loanMap.put("paybillVO", payBillVO);

					Map loanResultMap = new GenerateBillLoanAndAdvanceHelper().calculateLoans(loanMap);
					long loanEmi = loanResultMap.get("loanEmi") != null
							? Long.valueOf(loanResultMap.get("loanEmi").toString())
							: 0;
					long loanIntEmi = loanResultMap.get("loanIntEmi") != null
							? Long.valueOf(loanResultMap.get("loanIntEmi").toString())
							: 0;
					lstHrPayLoanEmpDtls = (List) (loanResultMap.get("lstHrPayLoanEmpDtls") != null
							? loanResultMap.get("lstHrPayLoanEmpDtls")
							: null);
					if (lstHrPayLoanEmpDtls != null && lstHrPayLoanEmpDtls.size() > 0) {
						// logger.info("Going to add in final list from Loan part - size to be added is
						// "
						// + lstHrPayLoanEmpDtls.size());
						logger.info("Going to add in final list from Loan part - size to be added is "
								+ lstHrPayLoanEmpDtls.size());
						totalLoanList.addAll(lstHrPayLoanEmpDtls);
					}
					lstHrPayLoanEmpDtls = null;

					Map deactivateLoanRecords = (Map) (loanResultMap.get("deactivateLoanRecords") != null
							? loanResultMap.get("deactivateLoanRecords")
							: null);

					loanResultMap = null;
					if (deactivateLoanRecords != null) {
						logger.info("Deactive employee loan map size is " + deactivateLoanRecords.size());
						logger.info("Deactive employee loan map is " + deactivateLoanRecords);
					}

					if (deactivateLoanRecords != null && deactivateLoanRecords.size() > 0) {
						int changedMonth = 0;
						int changedYear = 0;
						Calendar calChangedRec = Calendar.getInstance();
						if ((calChangedRec.get(Calendar.MONTH) + 1) >= 12) {
							changedMonth = 1;
							changedYear = calChangedRec.get(Calendar.YEAR) + 1; // next
																				// year
							// logger.info("last month of year, so setting next month as 1 and next year");
							logger.info("Next month and year is " + changedMonth + " " + changedYear);
						} else {
							changedMonth = calChangedRec.get(Calendar.MONTH) + 1;
							changedYear = calChangedRec.get(Calendar.YEAR);
							// logger.info("Not last month, so setting next month and next year");
							logger.info("Next month and year is " + changedMonth + " " + changedYear);
						}
						inputMap.put("changedMonth", changedMonth);
						inputMap.put("changedYear", changedYear);
						inputMap.put("changedPostId", payBillVO.getOrgPostMst().getPostId());
						inputMap.put("cmnDatabaseMst", cmnDatabaseMst);
						inputMap.put("OrgUserMst", orgUserMst);
						inputMap.put("OrgPostMst", orgPostMst);

						long changedRecPK = new GenerateBillServiceHelper().insertChangedRecord(inputMap);
						// logger.info("Changed Records are inserted for employees whose loans are
						// deactivated");
						logger.info("Changed Records are inserted - PK is " + changedRecPK);
					}

					deactivateLoanRecords = null;

					Class payBill = payBillVO.getClass();
					if (loanEmi > 0) {
						Object[] objArgs = new Object[] { new Double(loanEmi) };
						Method payBillMthd = payBill.getMethod(payBillMthdName, new Class[] { double.class });
						payBillMthd.invoke(payBillVO, objArgs);
						totLoan += loanEmi;
					} else {
						Object[] objArgsInt = new Object[] { new Double(Math.round(loanIntEmi)) };
						Method payBillIntMthd = payBill.getMethod(payBillIntMthdName, new Class[] { double.class });
						payBillIntMthd.invoke(payBillVO, objArgsInt);
						totLoan += loanIntEmi;
					}
					// code to be added for nnet

					// code ended
					// logger.info("Changed Records are inserted for employees whose loans are
					// deactivated");

					// logger.info("List size for loan insertion is " +
					// lstHrPayLoanEmpDtls.size());
					logger.info("Loan Emi Is " + loanEmi + "loan int is " + loanIntEmi);
				} else if (compoType == advLookupId)// for advances
				{
					// double totAdv=0;
					// logger.info("Enter in the Advances");

					Map advanceMap = inputMap;
					advanceMap.put("orgEmpId", orgEmpId);
					advanceMap.put("compoCode", compoCode);
					advanceMap.put("month", monthGiven);
					advanceMap.put("year", yearGiven);
					advanceMap.put("EmpPaybillLoanVO", empPaybillLoanVO);
					// This EMI will be for whole month, so need to calculate
					// for particular days.
					Map advResultMap = new GenerateBillLoanAndAdvanceHelper().calculateAdances(advanceMap);
					lstHrPayAdvEmpDtls = (List) (advResultMap.get("lstHrPayLoanEmpDtls") != null
							? advResultMap.get("lstHrPayLoanEmpDtls")
							: null);

					if (lstHrPayAdvEmpDtls != null) {
						// logger.info("Final List size for advance insertion is - size to be added is "
						// + lstHrPayAdvEmpDtls.size());
						totalLoanList.addAll(lstHrPayAdvEmpDtls);
					}
					lstHrPayAdvEmpDtls = null;
					Map deactivateAdvRecords = (Map) (advResultMap.get("deactivateAdvanceRecords") != null
							? advResultMap.get("deactivateAdvanceRecords")
							: null);
					if (deactivateAdvRecords != null && deactivateAdvRecords.size() > 0) {
						int changedMonth = 0;
						int changedYear = 0;
						Calendar calChangedRec = Calendar.getInstance();
						if ((calChangedRec.get(Calendar.MONTH) + 1) >= 12) {
							changedMonth = 1;
							changedYear = calChangedRec.get(Calendar.YEAR) + 1; // next
																				// year
							// logger.info("last month of year, so setting next month as 1 and next year");
							// logger.info("Next month and year is " +
							// changedMonth + " " + changedYear);
						} else {
							changedMonth = calChangedRec.get(Calendar.MONTH) + 1;
							changedYear = calChangedRec.get(Calendar.YEAR);
							// logger.info("Not last month, so setting next month and next year");
							// logger.info("Next month and year is " +
							// changedMonth + " " + changedYear);
						}
						inputMap.put("changedMonth", changedMonth);
						inputMap.put("changedYear", changedYear);
						inputMap.put("changedPostId", payBillVO.getOrgPostMst().getPostId());
						inputMap.put("cmnDatabaseMst", cmnDatabaseMst);
						inputMap.put("OrgUserMst", orgUserMst);
						inputMap.put("OrgPostMst", orgPostMst);

						// inputMap.put("orgPostMst",payBillVO.getOrgPostMst());
						long changedRecPK = new GenerateBillServiceHelper().insertChangedRecord(inputMap);
						// logger.info("Changed Records are inserted for employees whose loans are
						// deactivated");
						logger.info("Changed Records are inserted - PK is " + changedRecPK);

					}
					advEMI = advResultMap.get("advEMI") != null ? Long.valueOf(advResultMap.get("advEMI").toString())
							: 0;

					advResultMap = null;

					String payBillMthdName = "setAdv" + edpCode;
					Class payBill = payBillVO.getClass();
					Object[] objArgs = new Object[] { new Double(Math.round(advEMI)) };
					Method payBillMthd = payBill.getMethod(payBillMthdName, new Class[] { double.class });
					payBillMthd.invoke(payBillVO, objArgs);

					if (edpCode.equals("5059") || edpCode.equals("5053") || edpCode.equals("5052")
							|| edpCode.equals("5068"))
						faTaPa += advEMI;
					else
						totLoan += advEMI;

					// code to be added for nnet

					// code ended

					// logger.info("Advance Emi Is "+advEMI);
				}
			}
			empLoanList = null;

			Map empDCPSValMp = (Map) inputMap.get("empDCPSValMap");
			deducEmpId = empPaybillVO.getOrgEmpId();
			if (empDCPSValMp.containsKey(deducEmpId)) {
				Map dcpsValMap = (HashMap) empDCPSValMp.get(deducEmpId);
				if (dcpsValMap.containsKey(700047l)) {
					amt2 = Double.valueOf(dcpsValMap.get(700047l).toString());
					payBillVO.setDeduc9535(amt2);
					totalDeduction += amt2;
				}
				if (dcpsValMap.containsKey(700049l)) {
					amt3 = Double.valueOf(dcpsValMap.get(700049l).toString());
					payBillVO.setDeduc9536(amt3);
					totalDeduction += amt3;
				}
				if (dcpsValMap.containsKey(700048l)) {
					amt4 = Double.valueOf(dcpsValMap.get(700048l).toString());// 700046
					payBillVO.setDeduc9537(amt4);
					totalDeduction += amt4;
				}
			}

			logger.info("After");
			// logger.info("size of lstHrPayAdvEmpDtls is " +
			// lstHrPayAdvEmpDtls.size());
			// logger.info("size of lstHrPayLoanEmpDtls is " +
			// lstHrPayLoanEmpDtls.size());
			logger.info("totalLoanList size is " + totalLoanList.size());
			logger.info("total of loan and advances is " + totLoan);
			logger.info("total of totalCompDeduc is " + totalCompDeduc);
			logger.info("total of totalNonCompDeduc is " + totalNonCompDeduc);
			logger.info("total of hrr is " + hrr);
			// ended loan part

			// logger.info("total of non computational Deduction is "+totalNonCompDeduc);

			double totalDeduc = totalCompDeduc + totalNonCompDeduc + totLoan;

			if (isBrokenEntryExist) {
				totalDeduc = totalDeduction + totLoan;
				logger.info("TotalDeduc Inside Loop if Broken Period is set for the employee--->" + totalDeduc);

			}

			double totalGross = gross - totalDeduc - faTaPa;
			logger.info(
					"totalDeduc is totalCompDeduc+totalNonCompDeduc+totLoan or totalDeduction+totLoan" + totalDeduc);
			logger.info("faTaPa is " + faTaPa);
			logger.info("totalGross is gross-totalDeduc-faTaPa " + totalGross);
			logger.info("Gross is " + gross);
			// ended

			payBillVO.setGrossAmt(gross - faTaPa);

			double basicBkup = Double.parseDouble(input.get("basic").toString());
			double basicAndDPBkup = Double.parseDouble(input.get("basicAndDP").toString());

			logger.info("basicBkup--->" + basicBkup + "***basicAndDPBkup--->" + basicAndDPBkup);
			input.put("basic", gross);
			input.put("basicAndDP", gross);
			double pt = 0;
			// added by khushal for Pt calculation

			Map<Long, Long> isPTMappedMap = (HashMap) (inputMap.get("isPTMappedMap") != null
					? inputMap.get("isPTMappedMap")
					: new HashMap());

			// empDeducVOList=empAllowDAO.getEmpdeducDtls(empid,35,empCurrentStatus,monthGiven,yearGiven);//for
			// PT
			if (isPTMappedMap.containsKey(Long.valueOf(empid))) {
				// logger.info("Inside PT condition If PT is given");
				/*
				 * if(payCommissionId == commissionFiveId || payCommissionId ==
				 * commissionSevenId) pt = salaryRules.calculatePT(input); else
				 * if(payCommissionId == sixthPayCommId || payCommissionId == commissionFourId
				 * || payCommissionId == commissionThreeId || payCommissionId == commissionTwoId
				 * || payCommissionId == commissionOneId) pt =
				 * salaryRuls6thPay.calculatePT(input);
				 */
				inputRuleEngine.put(PayrollConstants.PAYROLL_PARAMID_GROSSPAY,
						BigDecimal.valueOf(gross).divide(BigDecimal.valueOf(1), 0, BigDecimal.ROUND_HALF_UP));

				BigDecimal ptVal = (new PayrollCalculationServiceImpl()).getRuleCompoValue(inputRuleEngine, serv,
						inputMap, PayrollConstants.PAYROLL_PT_ID);
				if (ptVal.compareTo(BigDecimal.ZERO) >= 0) {
					/*
					 * HrPayEmpSalaryTxn empSalaryTxn = ruleMap.get(PayrollConstants.PAYROLL_PT_ID);
					 * if(empSalaryTxn != null) { pt=empSalaryTxn.getAmount().doubleValue();
					 */
					pt = ptVal.doubleValue();
					// added by roshan
					if ((gross - faTaPa) < 10001) {
						// logger.info("hiii");
						pt = 175;
					} else {
						// logger.info("hiii not ");
						pt = 200;
					}
					// end by roshan

					// logger.info("Pt i got from rule engine is:"+pt);
					// System.out.println("Pt i got from rule engine is:"+pt);
					// }

				} else {
					if (payCommissionId == commissionFiveId || payCommissionId == commissionSevenId)
						pt = salaryRules.calculatePT(input);
					else if (payCommissionId == sixthPayCommId || payCommissionId == commissionFourId
							|| payCommissionId == commissionThreeId || payCommissionId == commissionTwoId
							|| payCommissionId == commissionOneId)
						pt = salaryRuls6thPay.calculatePT(input);
					logger.info("Pt i got from excel is " + pt);
				}

				if ((billMonth == 2) && (pt == PayrollConstants.PT_BEFORE_FEB)) {
					pt = PayrollConstants.PT_FOR_FEB;
				}

				if (!isBrokenEntryExist) {
					// logger.info("Inside If calculate GROSS based PT if broken is not given::::");

					totalDeduc = totalDeduc - payBillVO.getDeduc9570();
					totalGross = totalGross + payBillVO.getDeduc9570();

					logger.info("pt is:::" + pt);

					payBillVO.setDeduc9570(pt);

					totalDeduc = totalDeduc + pt;
					totalGross = totalGross - pt;

				}

				input.put("basic", basicBkup);
				input.put("basicAndDP", basicAndDPBkup);

			} // ended by khushal
			isPTMappedMap = null;
			logger.info("Gross  Amount gross-faTaPa" + (gross - faTaPa));
			logger.info("Deduc Amount Just before putting iv VO is " + totalDeduc);
			logger.info("Net Amount Just before putting iv VO is " + totalGross);

			payBillVO.setTotalDed(totalDeduc);
			payBillVO.setNetTotal(totalGross);

			resultMap.put("lstHrPayLoanEmpDtls", totalLoanList);

			resultMap.put("payBillVO", payBillVO);
			logger.info("Core Logic PerEmployee End Time" + System.currentTimeMillis());
		} catch (Exception e) {
			logger.error("Error occured in generateBillservice corelogic: " + e.getMessage());
			// logger.info("Error occured in generateBillservice corelogic "+e);
			throw new Exception(e);

		}
		return resultMap;
	}

	/*
	 * public long calMonthlyIncomeTax(Map objectArgs){ ServiceLocator serv =
	 * (ServiceLocator)objectArgs.get("serviceLocator");
	 * 
	 * //HttpServletRequest request = (HttpServletRequest)
	 * objectArgs.get("requestObj"); //long empId=302133; //long empId =
	 * Long.parseLong(request.getParameter("empId")); //int monthGiven=
	 * Integer.parseInt(request.getParameter("month")); //int yearGiven =
	 * Integer.parseInt(request.getParameter("year")); //double currentGrossAmt =
	 * 435; //double currProfTax = 60; //double currFoodAdvAmt = 240; //double
	 * currFestAdvAmt = 200; //int monthGiven=12; //int yearGiven=2007;
	 * 
	 * 
	 * // Fetch the values from the objectArgs Map of current Month.
	 * 
	 * 
	 * long empId = Long.parseLong(objectArgs.get("empId").toString()); double
	 * currentGrossAmt =
	 * Double.parseDouble(objectArgs.get("grossAmount").toString()); double
	 * currProfTax = Double.parseDouble(objectArgs.get("curretPtofTax").toString());
	 * double currFoodAdvAmt =
	 * Double.parseDouble(objectArgs.get("currFoodAdvAmt").toString()); double
	 * currFestAdvAmt =
	 * Double.parseDouble(objectArgs.get("currFestAdvAmt").toString()); int
	 * monthGiven= Integer.parseInt(objectArgs.get("monthGiven").toString()); int
	 * yearGiven=Integer.parseInt(objectArgs.get("yearGiven").toString());
	 * logger.info("The EmployeeId is:-"+empId+"Gross Amount:-"+currentGrossAmt+
	 * "Proffessional Tax:-"+currProfTax+"Food Advance Amount:-"+currFoodAdvAmt+
	 * "Festival Advance Amount:-"+currFestAdvAmt);
	 * 
	 * 
	 * 
	 * long totalInvestAmt=0l; // Total Investment Amount of the current Financial
	 * Year. double approvedInvestAmt = 0d; // Total Approved Investment Amount.
	 * long totalExemptAmt=0l; //Total Exemption Amount of the Current Financial
	 * Year. Map incomeTaxData = new HashMap(); //Map of Past months' Total Gross
	 * Amount,Income Tax,Proffessional Tax of Current Financial Year. double
	 * totalPastProffTaxAmt=0d; // Total Past Months' Proffessional Tax Amount of
	 * the current Financial Year. double totalNextProfTaxAmt = 0d; //Total Next
	 * months' Proffessional Tax Amount of the Current Financial Year. double
	 * totalProfTaxAmt = 0d; // Total Proffessioanl Tax of the Current Financial
	 * Year. double totalNextFoodAdvaceAmt=0d; // Total Next Months' Food Advance
	 * Amount of the Current Financial Year. double totalNextFestAdvaceAmt=0d; //
	 * Total Next Months' Festival Advance Amount of the Current Financial Year.
	 * double totalAllowancesAmt=0d; // Total of all Allowances of perticular
	 * employee. double totalPastGrossAmt=0d; // Total Past Months' Gross Amount of
	 * the current Financial Year.
	 * 
	 * double totalPaidIncomeTax = 0d; // Total Past Months' Income Tax Amount of
	 * the current Financial Year. double totalIncomeTax = 0d; // Total Income Tax
	 * Amount of the Current financial Year. double totalRemainedIt = 0d; // Total
	 * Remained Income Tax of the current financial Year. long monthlyIncomeTax =
	 * 0l; // Income tax of the next months of the current financial year. double
	 * totalNextGrossAmt = 0d; // Total Next Months' Gross Amount of the Current
	 * Financial Year. double totalGrossAmt = 0d; //Total Gross Amount of the
	 * current financial year. double netTaxableIncome = 0d; // Total Taxable Income
	 * of the current financial year. String gender = "M";
	 * 
	 * IncomeTaxRules incomeTaxRules = new IncomeTaxRules();
	 * 
	 * 
	 * long foodLoanTypeId = 2; long festivalLoanTypeId = 16; int nextMonths=0;
	 * 
	 * try { PayBillDAOImpl payBillDaoImpl = new
	 * PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory()); incomeTaxData =
	 * payBillDaoImpl.getPastIncTaxData(empId,monthGiven,yearGiven);
	 * logger.info("You are in the Income Tax Calaulation"); // Calculation for the
	 * Total Gross Amount if(monthGiven > 3){ nextMonths = 15 - monthGiven; } else{
	 * nextMonths = 3 - monthGiven; }
	 * logger.info("The Next Months are:-"+nextMonths); totalPastGrossAmt = (Double)
	 * incomeTaxData.get("totalPastGrossAmt");
	 * logger.info("The PastGross Amount is:-"+totalPastGrossAmt);
	 * totalAllowancesAmt = payBillDaoImpl.getAllowancesTotal(empId); //Monthly
	 * Allowances total of a perticular (by considering the zero leave).
	 * logger.info("Total of All Allowances is:-"+totalAllowancesAmt);
	 * totalNextFoodAdvaceAmt = payBillDaoImpl.getTotNextAdvacesAmt(empId,
	 * foodLoanTypeId, monthGiven);
	 * logger.info("Total Next Food Advance Amount is:-" +totalNextFoodAdvaceAmt);
	 * totalNextFestAdvaceAmt = payBillDaoImpl.getTotNextAdvacesAmt(empId,
	 * festivalLoanTypeId, monthGiven);
	 * logger.info("Total Next Festival Advance Amount is:-"+totalNextFestAdvaceAmt
	 * );
	 * 
	 * 
	 * 
	 * // Equation for the Gross = AllowancesTotal - (Pay Recovery + Food Advance
	 * EMI + Festival Advace EMI)
	 * 
	 * totalNextGrossAmt = (totalAllowancesAmt * nextMonths) -
	 * (totalNextFoodAdvaceAmt + totalNextFestAdvaceAmt + currFoodAdvAmt +
	 * currFestAdvAmt);
	 * logger.info("Total Next Gross Amount is:-"+totalNextGrossAmt); totalGrossAmt
	 * = totalPastGrossAmt + currentGrossAmt + totalNextGrossAmt;
	 * logger.info("Total Gross Amount is:-"+totalGrossAmt);
	 * 
	 * // Total Investment, Exemption and the deduction under chapter-6.
	 * totalInvestAmt = payBillDaoImpl.getInvestAmtOfCurrFinYear(empId); // Call for
	 * fetching the Total Investment Amount of the Current Financial Year.
	 * totalExemptAmt = payBillDaoImpl.getExemptAmtOfCurrFinYear(empId); // Call for
	 * fetching the Total Exemption and Deduction Chapter-6 Amount of the Current
	 * Financial Year. approvedInvestAmt=incomeTaxRules.getInvestmentAmount
	 * ((double)totalInvestAmt);
	 * logger.info("Total Investment Amount is:-"+totalInvestAmt);
	 * logger.info("Total approved Investment Amount is:-"+approvedInvestAmt);
	 * logger.info("Total Investment Amount is:-"+totalInvestAmt+
	 * "\n Total Exemption Amount is:-"+totalExemptAmt);
	 * 
	 * // Proffessioanl Tax Calculateion totalPastProffTaxAmt = (Double)
	 * incomeTaxData.get("totalPastProffTaxAmt"); totalNextProfTaxAmt =
	 * payBillDaoImpl.getNextProfTax(empId);
	 * logger.info("Total Past Proffessional Tax is:-"+totalPastProffTaxAmt);
	 * logger.info("Total Next Proffessional Tax is:-"+totalNextProfTaxAmt *
	 * nextMonths); totalProfTaxAmt = totalPastProffTaxAmt + currProfTax +
	 * (totalNextProfTaxAmt * nextMonths);
	 * 
	 * // Calculation for the Income Tax of an employee.
	 * 
	 * totalPaidIncomeTax = (Double) incomeTaxData.get("totalPastIncomeTax");
	 * logger.info("Total Paid Income Tax amount is:-"+totalPaidIncomeTax);
	 * netTaxableIncome = totalGrossAmt - (totalProfTaxAmt + approvedInvestAmt +
	 * totalExemptAmt);
	 * logger.info("Total Next Income Tax amount is:-"+netTaxableIncome);
	 * totalIncomeTax = incomeTaxRules.calculateIncomeTax(netTaxableIncome, 1,
	 * gender); logger.info("Total Income Tax is:-"+totalIncomeTax); totalRemainedIt
	 * = totalIncomeTax - totalPaidIncomeTax;
	 * logger.info("Total Remained Income Tax is:-"+totalRemainedIt); nextMonths +=
	 * 1; monthlyIncomeTax =(long) totalRemainedIt / nextMonths ;
	 * logger.info("The Monthly Income Tax Amount is:-"+monthlyIncomeTax); } catch
	 * (Exception e){ logger.error("Error is: "+ e.getMessage()); } return
	 * monthlyIncomeTax;
	 * 
	 * 
	 * }
	 */
	public long calMonthlyIncomeTax(Map objectArgs) {
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		// HttpServletRequest request = (HttpServletRequest)
		// objectArgs.get("requestObj");
		// long empId=302133;
		// long empId = Long.parseLong(request.getParameter("empId"));
		// int monthGiven= Integer.parseInt(request.getParameter("month"));
		// int yearGiven = Integer.parseInt(request.getParameter("year"));
		// double currentGrossAmt = 435;
		// double currProfTax = 60;
		// double currFoodAdvAmt = 240;
		// double currFestAdvAmt = 200;
		// int monthGiven=12;
		// int yearGiven=2007;

		// Fetch the values from the objectArgs Map of current Month.

		long empId = Long.parseLong(objectArgs.get("empId").toString());
		double currentGrossAmt = Double.parseDouble(objectArgs.get("grossAmount").toString());
		double currProfTax = Double.parseDouble(objectArgs.get("curretPtofTax").toString());
		double currFoodAdvAmt = Double.parseDouble(objectArgs.get("currFoodAdvAmt").toString());
		double currFestAdvAmt = Double.parseDouble(objectArgs.get("currFestAdvAmt").toString());
		int monthGiven = Integer.parseInt(objectArgs.get("monthGiven").toString());
		int yearGiven = Integer.parseInt(objectArgs.get("yearGiven").toString());
		logger.info(
				"The EmployeeId is:-" + empId + "Gross Amount:-" + currentGrossAmt + "Proffessional Tax:-" + currProfTax
						+ "Food Advance Amount:-" + currFoodAdvAmt + "Festival Advance Amount:-" + currFestAdvAmt);

		long totalInvestAmt = 0l; // Total Investment Amount of the current
									// Financial Year.
		double approvedInvestAmt = 0d; // Total Approved Investment Amount.
		long totalExemptAmt = 0l; // Total Exemption Amount of the Current
									// Financial Year.
		Map incomeTaxData = new HashMap(); // Map of Past months' Total Gross
											// Amount,Income Tax,Proffessional
											// Tax of Current Financial Year.
		double totalPastProffTaxAmt = 0d; // Total Past Months' Proffessional
											// Tax Amount of the current
											// Financial Year.
		double totalNextProfTaxAmt = 0d; // Total Next months' Proffessional Tax
											// Amount of the Current Financial
											// Year.
		double totalProfTaxAmt = 0d; // Total Proffessioanl Tax of the Current
										// Financial Year.
		double totalNextFoodAdvaceAmt = 0d; // Total Next Months' Food Advance
											// Amount of the Current Financial
											// Year.
		double totalNextFestAdvaceAmt = 0d; // Total Next Months' Festival
											// Advance Amount of the Current
											// Financial Year.
		double totalAllowancesAmt = 0d; // Total of all Allowances of perticular
										// employee.
		double totalPastGrossAmt = 0d; // Total Past Months' Gross Amount of the
										// current Financial Year.

		double totalPaidIncomeTax = 0d; // Total Past Months' Income Tax Amount
										// of the current Financial Year.
		double totalIncomeTax = 0d; // Total Income Tax Amount of the Current
									// financial Year.
		double totalRemainedIt = 0d; // Total Remained Income Tax of the current
										// financial Year.
		long monthlyIncomeTax = 0l; // Income tax of the next months of the
									// current financial year.
		double totalNextGrossAmt = 0d; // Total Next Months' Gross Amount of the
										// Current Financial Year.
		double totalGrossAmt = 0d; // Total Gross Amount of the current
									// financial year.
		double netTaxableIncome = 0d; // Total Taxable Income of the current
										// financial year.
		HrEisEmpMst hrEisEmpMst;
		EmpInfoDAO empInfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
		String gender;

		IncomeTaxRules incomeTaxRules = new IncomeTaxRules();

		long foodLoanTypeId = 2;
		long festivalLoanTypeId = 16;
		int nextMonths = 0;

		try {
			PayBillDAOImpl payBillDaoImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			incomeTaxData = payBillDaoImpl.getPastIncTaxData(empId, monthGiven, yearGiven);
			// logger.info("You are in the Income Tax Calaulation");
			// Calculation for the Total Gross Amount
			if (monthGiven > 3) {
				nextMonths = 15 - monthGiven;
			} else {
				nextMonths = 3 - monthGiven;
			}
			hrEisEmpMst = empInfoDao.read(empId);
			gender = hrEisEmpMst.getEmpGender().toString();
			// logger.info("The Next Months are:-"+nextMonths);
			totalPastGrossAmt = (Double) incomeTaxData.get("totalPastGrossAmt");
			// logger.info("The PastGross Amount is:-"+totalPastGrossAmt);
			totalAllowancesAmt = payBillDaoImpl.getAllowancesTotal(empId); // Monthly
																			// Allowances
																			// total
																			// of
																			// a
																			// perticular
																			// (by
																			// considering
																			// the
																			// zero
																			// leave).
			// logger.info("Total of All Allowances is:-"+totalAllowancesAmt);
			// logger.info("Total of All Allowances is:-"+totalAllowancesAmt);
			totalNextFoodAdvaceAmt = payBillDaoImpl.getTotNextAdvacesAmt(empId, foodLoanTypeId, monthGiven);
			// logger.info("Total Next Food Advance Amount is:-"+totalNextFoodAdvaceAmt);
			totalNextFestAdvaceAmt = payBillDaoImpl.getTotNextAdvacesAmt(empId, festivalLoanTypeId, monthGiven);
			// logger.info("Total Next Festival Advance Amount
			// is:-"+totalNextFestAdvaceAmt);

			// Equation for the Gross = AllowancesTotal - (Pay Recovery + Food
			// Advance EMI + Festival Advace EMI)

			totalNextGrossAmt = (totalAllowancesAmt * nextMonths)
					- (totalNextFoodAdvaceAmt + totalNextFestAdvaceAmt + currFoodAdvAmt + currFestAdvAmt);
			// logger.info("Total Next Gross Amount is:-"+totalNextGrossAmt);
			totalGrossAmt = totalPastGrossAmt + currentGrossAmt + totalNextGrossAmt;
			logger.info("Total Gross Amount is:-" + totalGrossAmt);

			// Total Investment, Exemption and the deduction under chapter-6.
			totalInvestAmt = payBillDaoImpl.getInvestAmtOfCurrFinYear(empId); // Call
																				// for
																				// fetching
																				// the
																				// Total
																				// Investment
																				// Amount
																				// of
																				// the
																				// Current
																				// Financial
																				// Year.
			totalExemptAmt = payBillDaoImpl.getExemptAmtOfCurrFinYear(empId); // Call
																				// for
																				// fetching
																				// the
																				// Total
																				// Exemption
																				// and
																				// Deduction
																				// Chapter-6
																				// Amount
																				// of
																				// the
																				// Current
																				// Financial
																				// Year.
			approvedInvestAmt = incomeTaxRules.getInvestmentAmount((double) totalInvestAmt);
			// logger.info("Total Investment Amount is:-"+totalInvestAmt);
			// logger.info("Total approved Investment Amount is:-"+approvedInvestAmt);
			// logger.info("Total Investment Amount is:-"+totalInvestAmt+"\n Total Exemption
			// Amount is:-"+totalExemptAmt);

			// Proffessioanl Tax Calculateion
			totalPastProffTaxAmt = (Double) incomeTaxData.get("totalPastProffTaxAmt");
			totalNextProfTaxAmt = payBillDaoImpl.getNextProfTax(empId);
			// logger.info("Total Past Proffessional Tax is:-"+totalPastProffTaxAmt);
			// logger.info("Total Next Proffessional Tax is:-"+totalNextProfTaxAmt
			// * nextMonths);
			totalProfTaxAmt = totalPastProffTaxAmt + currProfTax + (totalNextProfTaxAmt * nextMonths);

			// Calculation for the Income Tax of an employee.

			totalPaidIncomeTax = (Double) incomeTaxData.get("totalPastIncomeTax");
			// logger.info("Total Paid Income Tax amount is:-"+totalPaidIncomeTax);
			netTaxableIncome = totalGrossAmt - (totalProfTaxAmt + approvedInvestAmt + totalExemptAmt);
			// logger.info("Total Next Income Tax amount is:-"+netTaxableIncome);
			totalIncomeTax = incomeTaxRules.calculateIncomeTax(netTaxableIncome, 1, gender);
			// logger.info("Total Income Tax is:-"+totalIncomeTax);
			totalRemainedIt = totalIncomeTax - totalPaidIncomeTax;
			// logger.info("Total Remained Income Tax is:-"+totalRemainedIt);
			nextMonths += 1;
			monthlyIncomeTax = (long) totalRemainedIt / nextMonths;
			// logger.info("The Monthly Income Tax Amount is:-"+monthlyIncomeTax);
			if (monthlyIncomeTax < 0)
				monthlyIncomeTax = 0;
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return monthlyIncomeTax;

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Date addDate(Date actualDate, int increment) {
		logger.info("The actualDate is---->>>>" + actualDate);
		GenerateBillService genbillService = new GenerateBillService();
		Date date = new Date();
		date.setTime(actualDate.getTime());
		int days = genbillService.dayofDate(date);
		date.setDate(days + increment);
		logger.info("After increment the new date is--->" + date);
		return date;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Map generatePassMap(Map inputMap) {
		// logger.info("in generate Map service");
		Map passMap = new HashMap();

		EmpPaybillVO empPaybillVO = (EmpPaybillVO) inputMap.get("hrEisOtherDtls");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResourceBundle constant = ResourceBundle.getBundle("resources.eis.eis_Constants");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long sixthPayCommId = Long.parseLong(resourceBundle.getString("commissionSixId"));
		// long commissionFiveId =
		// Long.parseLong(resourceBundle.getString("commissionFiveId"));
		long commissionFourId = Long.parseLong(resourceBundle.getString("commissionFourId"));
		long commissionThreeId = Long.parseLong(resourceBundle.getString("commissionThreeId"));
		long commissionTwoId = Long.parseLong(resourceBundle.getString("commissionTwoId"));
		long commissionOneId = Long.parseLong(resourceBundle.getString("commissionOneId"));
		long commissionSevenId = Long.parseLong(resourceBundle.getString("commissionNewSevenId"));
		int contractEmpType = Integer.parseInt(resourceBundle.getString("contract"));
		int fixedEmpType = Integer.parseInt(resourceBundle.getString("fixed"));
		boolean fullmonthcal = Boolean.parseBoolean(constant.getString("fullMonthCalculation"));
		// logic for days of post
		// passMap.put("daysOfPost", 15);

		// PayBillDAOImpl payDao= new
		// PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		// long postId= empPaybillVO.getPostId();
		int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
		int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());

		passMap.put("serviceLocator", serv);
		passMap.put("month", monthGiven);
		passMap.put("year", yearGiven);
		passMap.put("fullMonthCal", fullmonthcal);

		Calendar calGiven = Calendar.getInstance();
		calGiven.set(Calendar.YEAR, yearGiven);
		calGiven.set(Calendar.MONTH, (monthGiven - 1));
		calGiven.set(Calendar.DAY_OF_MONTH, 1);

		int maxDay = calGiven.getActualMaximum(5);
		passMap.put("maxDaysMonth", maxDay);

		long empType = empPaybillVO.getEmpType();
		// logger.info("empType is"+empType);
		passMap.put("empType", empType);
		long grade = empPaybillVO.getGradeId();
		passMap.put("grade", grade);
		long Designation = 0;

		/*
		 * long empid=empPaybillVO.getEisEmpId(); long orgEmpId =
		 * empPaybillVO.getOrgEmpId();
		 */

		long scale_start_amt = 0;
		long scale_end_amt = 0;

		String jdcpsId = resourceBundle.getString("judgeIds");
		String[] list = jdcpsId.split(",");

		boolean isJudge = false;

		if (empType != contractEmpType && empType != fixedEmpType) {
			Designation = empPaybillVO.getDesigId();
			logger.info("Designation   is **********" + Designation);
			for (int k = 0; k < list.length; k++) {
				logger.info("first dsgnid is **********" + list[k]);
				// if(list[k]==Designation)
				if (Designation == (int) (Long.parseLong(list[k]))) {
					isJudge = true;
					break;
				}
			}
			logger.info("isJudge   is **********" + isJudge);
			scale_start_amt = empPaybillVO.getScaleStartAmt();
			scale_end_amt = empPaybillVO.getScaleEndAmt();
		} else {
			Designation = 0;
			scale_start_amt = empPaybillVO.getScaleStartAmt();
			scale_end_amt = empPaybillVO.getScaleEndAmt();
		}

		logger.info("isJudge out if else  is **********" + isJudge);
		passMap.put("isJudge", isJudge);
		passMap.put("designation", Designation);
		passMap.put("scaleStartAmt", scale_start_amt);
		passMap.put("scaleEndAmt", scale_end_amt);

		long CurrBasic = empPaybillVO.getBasicAmt();
		passMap.put("basic", CurrBasic);

		int daysOfPost = maxDay;

		passMap.put("daysOfPost", daysOfPost);

		double revisedBasic = Math.round((CurrBasic * daysOfPost) / maxDay);
		passMap.put("revBasic", revisedBasic);

		/*
		 * SimpleDateFormat sdfWA = new SimpleDateFormat("dd-MMM-yyyy"); String
		 * currentDt=sdfWA.format(new Date()); long userIdEmp =
		 * empPaybillVO.getUserId();
		 */

		if (empPaybillVO.getGradeId() != 0 && empPaybillVO.getEisEmpId() != 0) {

			if (empPaybillVO.getGisGradeCode() != 0 && empPaybillVO.getGisGradeId() != 0) {
				passMap.put("groupCode", empPaybillVO.getGisGradeCode());
				passMap.put("groupId", empPaybillVO.getGisGradeId());
				logger.info("grade ID------" + empPaybillVO.getGisGradeId() + "and " + empPaybillVO.getGisGradeCode());
				logger.info(
						"GISGROUPID ID------" + empPaybillVO.getGisGradeId() + "and " + empPaybillVO.getGisGradeCode());

			} else {
				passMap.put("groupCode", empPaybillVO.getGradeCode());
				passMap.put("groupId", empPaybillVO.getGradeId());
				logger.info(
						"Else -----grade ID------" + empPaybillVO.getGradeId() + "and " + empPaybillVO.getGradeCode());

			}
		} else {
			passMap.put("groupCode", 0);
			passMap.put("groupId", 0);
		}
		int isAvailedHRA = empPaybillVO.getIsAvailedHRA();
		passMap.put("isAvailedHRA", isAvailedHRA);

		Date doj = empPaybillVO.getEmpDOJ();
		// Date expDate = empPaybillVO.getEmpSrvcExp();

		passMap.put("doj", doj);

		// vpf end
		SalaryRules rules = new SalaryRules();
		long gradePay = 0;
		boolean isHandicapped = Boolean.parseBoolean(empPaybillVO.getIsPhyHandicapped().toLowerCase());
		passMap.put("isHandicapped", isHandicapped);
		// passMap.put("cityId",hrEisOtherDtls.getCity() );
		double DP = 0;
		long payCommissionId = empPaybillVO.getPayCommissionId();
		if (payCommissionId == sixthPayCommId || payCommissionId == commissionFourId
				|| payCommissionId == commissionThreeId || payCommissionId == commissionTwoId
				|| payCommissionId == commissionOneId) {
			gradePay = empPaybillVO.getGradePay();
			passMap.put("basicAndDP", revisedBasic + DP);

		} else if (payCommissionId != 0) { // For Contractual,Fix Pay SGD is
											// null
			// Modified by manish becasuse in case of fifth Pay Commmission
			// grade pay is not theere
			gradePay = 0;
			Map DPMap = new HashMap();
			DPMap.put("empType", empType);
			DPMap.put("revBasic", revisedBasic);
			DP = Math.round(rules.calculateDP(DPMap));

			DPMap = null;
			// logger.info("in generate map DP value is "+ DP);
			passMap.put("DP", DP);
			passMap.put("basicAndDP", revisedBasic + DP);
		} else {
			gradePay = 0;
		}
		passMap.put("gradePay", gradePay);

		// logger.info("basicAndDp is "+passMap.get("basicAndDP"));

		/*
		 * long userIdGpf = empPaybillVO.getUserId(); EmpGpfDtlsDAOImpl gpfDtlsDAO = new
		 * EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory ()); List
		 * gpfGradeList = gpfDtlsDAO.getAllGpfDetailsbyUserId(userIdGpf); long
		 * gpfGrade=0; if(gpfGradeList!=null && gpfGradeList.size()>0) { Iterator itr =
		 * gpfGradeList.iterator(); Object[] rowList = (Object[]) itr.next();
		 * if(rowList[3]!=null) gpfGrade= rowList[3] != null && !rowList[3].equals("")?
		 * Long.parseLong(rowList[3].toString()):0;
		 * logger.info("GPF Class for employee is " + gpfGrade); }
		 * 
		 * passMap.put("gpfGrade",gpfGrade);
		 */
		// passMap.put("empDOJ",hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpDoj());

		/*
		 * EmpGpfDtlsDAOImpl gpfDao = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class
		 * ,serv.getSessionFactory());
		 * 
		 * List gpfAcctList = gpfDao.getAllGpfDetails(orgEmpId); String gpfAcct=""; int
		 * gpfAcctFlag=0; if(gpfAcctList!=null && gpfAcctList.size()>0) { Object[] row =
		 * (Object[])gpfAcctList.get(0); if(row[0]!=null) gpfAcct
		 * =(String)row[0].toString().trim(); logger.info
		 * ("the value of gpfAccount no  is "+gpfAcct.length()+" for the emp id "
		 * +orgEmpId); if(!gpfAcct.equals("") && gpfAcct.length()>0) gpfAcctFlag=1;
		 * logger.info("GPF Flag is " + gpfAcctFlag); } passMap.put("gpfFlag",
		 * gpfAcctFlag);
		 */

		// List<CityCatMpg> cityCatVO = new ArrayList();
		String city = "";
		/*
		 * cityCatVO = cityCatDAO.getListByColumnAndValue("cityId",
		 * hrEisOtherDtls.getCity()); if(cityCatVO!=null && cityCatVO.size()>0) {
		 * city=cityCatVO.get(0).getCategoryId(); }
		 */

		if (empPaybillVO.getPostCityClass() != null && empPaybillVO.getPostCityClass().trim() != "") {
			city = empPaybillVO.getPostCityClass();
		}
		this.logger.info("city" + city);
		if (city != null && city != "") {
			passMap.put("city", city.substring(10, city.length()) != null ? city.substring(10, city.length()) : "A");
		}
		this.logger.info("city" + city);

		this.logger.info("vdd");

		// added by Ankit
		// ATS Incentive(30) =100
		// ATS Incentive(50)=101
		// Force-1 Incentive(100)=102
		// Force-1 Incentive(25)=103
		passMap.put("isAvailForce100", 0);
		passMap.put("isAvailForce25", 0);
		passMap.put("isAvailATS30", 0);
		passMap.put("isAvailATS50", 0);
		passMap.put("quarterId", 0); // not used, but fetched from Map in
										// CalculateHRA in Rule Engine

		// temp values,needs to be removed
		passMap.put("isVehicleAvail", "FALSE");
		passMap.put("distance", 5);
		logger.info("From core logic passing in the map " + isHandicapped + " " + city + " " + gradePay + " "
				+ daysOfPost + " " + maxDay + " " + fullmonthcal);
		// ended

		// ended
		return passMap;

	}

	public Map generatePassMapForRuleEngine(Map inputMap) {
		// logger.info("in generate Map service");

		// logger.info("inside generatePassMapForRuleEngine method of
		// GenerateBillServiceCoreLogic");
		Map passMap = new HashMap();

		EmpPaybillVO empPaybillVO = (EmpPaybillVO) inputMap.get("hrEisOtherDtls");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

		/*
		 * ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		 * ResourceBundle constant =
		 * ResourceBundle.getBundle("resources.eis.eis_Constants"); ResourceBundle
		 * resourceBundle = ResourceBundle.getBundle("resources.Payroll"); long
		 * sixthPayCommId = Long.parseLong(resourceBundle.getString("commissionSixId"));
		 * int contractEmpType = Integer.parseInt(resourceBundle.getString("contract"));
		 * int fixedEmpType = Integer.parseInt(resourceBundle.getString("fixed"));
		 * boolean fullmonthcal =
		 * Boolean.parseBoolean(constant.getString("fullMonthCalculation")); //logic for
		 * days of post //passMap.put("daysOfPost", 15);
		 * 
		 * PayBillDAOImpl payDao= new
		 * PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory()); long postId=
		 * empPaybillVO.getPostId(); int monthGiven =
		 * Integer.parseInt(inputMap.get("monthGiven").toString()); int yearGiven =
		 * Integer.parseInt(inputMap.get("yearGiven").toString());
		 */
		//
		// logger.info("inside generatePassMapForRuleEngine method of
		// GenerateBillServiceCoreLogic logger 1");

		Map<Integer, Object> paramValueMap = new HashMap<Integer, Object>();

		// paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DEPT,
		// empDeptCode);
		// paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_OFFICE,
		// empOfficeCode);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GRADE, empPaybillVO.getGradeCode());
		logger.info("Grade code -------" + empPaybillVO.getGradeCode());
		// logger.info("inside generatePassMapForRuleEngine method of
		// GenerateBillServiceCoreLogic logger 2");
		// commented by saurabh s
		if (empPaybillVO.getGisGradeCode() != 0)
			paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GRADE, empPaybillVO.getGisGradeCode());
		logger.info(" GIS Grade code -------" + empPaybillVO.getGisGradeCode());

		// paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYROLLEMPCTGRY,
		// empPayrollEmpCtgryCode);
		// paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GENDER,
		// empGender);

		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DESGN, empPaybillVO.getDesigId());
		// logger.info("inside generatePassMapForRuleEngine method of
		// GenerateBillServiceCoreLogic logger 3");
		// paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_POSTTYPE,
		// empPostType);
		if (empPaybillVO.getIsPhyHandicapped() != null && empPaybillVO.getIsPhyHandicapped().equals("TRUE"))
			paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PHYCHALLENGED, 1);
		else
			paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PHYCHALLENGED, 0);
		// logger.info("inside generatePassMapForRuleEngine method of
		// GenerateBillServiceCoreLogic logger 4");
		String city = empPaybillVO.getPostCityClass();
		// logger.info("The current city is as follows:"+city);
		String cityLookup = city.substring(10, city.length()) != null ? city.substring(10, city.length()) : "A";
		cityLookup += " City Ctgry";
		logger.info("city Look up is " + cityLookup);
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
		List<CmnLookupMst> list = cmnLookupMstDAOImpl.getListByColumnAndValue("lookupShortName", cityLookup);

		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_CITY, list.get(0).getLookupId());
		// paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_QUARTERTYPE,
		// empQuarterTypeCode);
		logger.info("city Look up id is " + list.get(0).getLookupId());
		// paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_BASICPAY,
		// empPaybillVO.getBasicAmt()- (empPaybillVO.getGradePay() !=
		// 0?empPaybillVO.getGradePay():0));
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_BASICPAY, empPaybillVO.getBasicAmt());
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GRADEPAY, empPaybillVO.getGradePay());
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYSCALE, empPaybillVO.getScaleId());
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN, empPaybillVO.getPayCommissionId());

		if (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_NONGOVT_PAYCOMMISSION
				|| empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_PADHAMANABHAN_PAYCOMMISSION
				|| empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_FOURTH_PAYCOMMISSION)
			paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN, PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION);
		if (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SHETTY_PAYCOMMISSION)
			paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN, PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION);

		/* Added By Shivram */
		if (empPaybillVO.getPayCommissionId() == PayrollConstants.PAYROLL_SEVENTH_PAYCOMMISSION) {
			// paramValueMap.put(Integer.valueOf(14),
			// PayrollConstants.PAYROLL_SEVENTH_PAYCOMMISSION);
			paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN,
					PayrollConstants.PAYROLL_SEVENTH_PAYCOMMISSION);
			paramValueMap.put(Integer.valueOf(7), Long.valueOf(empPaybillVO.getSevenBasicAmt()));
			logger.info("empPaybillVO.getSevenBasicAmt()---------------------- " + empPaybillVO.getSevenBasicAmt());
		}
		/* Ended By Shivram */

		String yearDoj = "" + empPaybillVO.getEmpDOJ();
		String monthDOJ = yearDoj.substring(5, 7);
		String dayDOJ = yearDoj.substring(8, 10);
		String yearDOJ = yearDoj.substring(0, 4);

		logger.info(new Date().getYear() + 1900);
		logger.info("yearDOJ " + yearDOJ);
		logger.info("yearDoj " + yearDoj);
		paramValueMap.put(PayrollConstants.PAYROLL_DOJ_YEAR_ID, yearDOJ);
		// added by saurabh s
		// if(new Date().getYear()+1900 != Integer.parseInt(yearDOJ))
		// commented by Saurabh S
		if (new Date().getYear() + 1900 != Integer.parseInt(yearDOJ)
				|| ((new Date().getYear() + 1900 == Integer.parseInt(yearDOJ)) && Integer.parseInt(monthDOJ) == 1
						&& Integer.parseInt(dayDOJ) == 1))
			// if(new Date().getYear()+1900 != Integer.parseInt(yearDOJ))
			paramValueMap.put(PayrollConstants.PAYROLL_DOJ_YEAR_ID, 1);

		logger.info("required year is " + empPaybillVO.getEmpDOJ());

		int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
		int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());

		if (yearGiven > 2014) {
			paramValueMap.put(PayrollConstants.PAYBILL_YEAR, 2014);
			paramValueMap.put(PayrollConstants.PAYBILL_MONTH, 4);
		}

		else if (yearGiven == 2014 && monthGiven >= 4 && empPaybillVO.getPayCommissionId() == 2500341) {
			paramValueMap.put(PayrollConstants.PAYBILL_YEAR, 2014);
			paramValueMap.put(PayrollConstants.PAYBILL_MONTH, 4);
		}

		else if (yearGiven == 2014 && monthGiven >= 4 && empPaybillVO.getPayCommissionId() == 2500340) {
			paramValueMap.put(PayrollConstants.PAYBILL_YEAR, 2014);
			paramValueMap.put(PayrollConstants.PAYBILL_MONTH, 1);
		}

		// added by shekhar
		else if (yearGiven == 2014 && monthGiven >= 4 && empPaybillVO.getPayCommissionId() == 2500342) {
			paramValueMap.put(PayrollConstants.PAYBILL_YEAR, 2014);
			paramValueMap.put(PayrollConstants.PAYBILL_MONTH, 1);
		}

		else if (yearGiven == 2014 && monthGiven < 4) {
			paramValueMap.put(PayrollConstants.PAYBILL_YEAR, 2014);
			paramValueMap.put(PayrollConstants.PAYBILL_MONTH, 1);
		}

		else if (yearGiven < 2014) {
			paramValueMap.put(PayrollConstants.PAYBILL_YEAR, 2014);
			paramValueMap.put(PayrollConstants.PAYBILL_MONTH, 1);
		}
		// Added by Kinjal for New GIS Rates:Start

		/*
		 * if((yearGiven==2020 && monthGiven>1) || yearGiven>=2020) {
		 * paramValueMap.put(64,2016); paramValueMap.put(16,1); }
		 */
		/*
		 * if((yearGiven==2016 && monthGiven>1) || yearGiven>2016) {
		 * paramValueMap.put(PayrollConstants.CURRENT_YEAR, 2016);
		 * paramValueMap.put(PayrollConstants.CURRENT_MONTH, 2); } else {
		 * paramValueMap.put(PayrollConstants.CURRENT_YEAR, 2015);
		 * paramValueMap.put(PayrollConstants.CURRENT_MONTH, 1); } //Added by Kinjal for
		 * New GIS Rates:End
		 */

		// Added by Kinjal for New Group Accident Policy:Start
		if ((yearGiven == 2016 && monthGiven > 1) || (yearGiven == 2017 && monthGiven < 8)) {
			paramValueMap.put(PayrollConstants.CURRENT_YEAR, 2016);
			paramValueMap.put(PayrollConstants.CURRENT_MONTH, 2);
		} else if ((yearGiven >= 2017 && monthGiven > 7) || (yearGiven <= 2023 && monthGiven <= 1)) {
			paramValueMap.put(PayrollConstants.CURRENT_YEAR, 2017);
			paramValueMap.put(PayrollConstants.CURRENT_MONTH, 8);
			logger.info("  empPaybillVO.getEmpGroup()2023 --" + paramValueMap);
		}
		// Added by Kinjal for New Group Accident Policy:End
		// Added By Naveen Sinha New Group Accident Policy
		else if ((yearGiven >= 2023 && monthGiven > 1) && empPaybillVO.getEmpClassGroup().equals("A")) {
			paramValueMap.put(PayrollConstants.CURRENT_YEARA, 2023);
			paramValueMap.put(PayrollConstants.CURRENT_MONTHA, 2);

		} else if ((yearGiven >= 2023 && monthGiven > 1) && empPaybillVO.getEmpClassGroup().equals("B")) {
			paramValueMap.put(PayrollConstants.CURRENT_YEARB, 2023);
			paramValueMap.put(PayrollConstants.CURRENT_MONTHB, 2);
		} else if ((yearGiven >= 2023 && monthGiven > 1) && empPaybillVO.getEmpClassGroup().equals("C")) {

			paramValueMap.put(PayrollConstants.CURRENT_YEARC, 2023);
			paramValueMap.put(PayrollConstants.CURRENT_MONTHC, 2);
		} else if ((yearGiven >= 2023 && monthGiven > 1) && empPaybillVO.getEmpClassGroup().equals("D")) {
			paramValueMap.put(PayrollConstants.CURRENT_YEARD, 2023);
			paramValueMap.put(PayrollConstants.CURRENT_MONTHD, 2);
		}
		return paramValueMap;

	}

}
