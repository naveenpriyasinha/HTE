package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.dao.LedgerReportDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.HrPayOfficePostMpgDAOImpl;
import com.tcs.sgv.eis.dao.LoanIntRecoverDAOImpl;
import com.tcs.sgv.eis.dao.LoanPrinRecoverDAOImpl;
import com.tcs.sgv.eis.dao.NonGovDeducDAO;
import com.tcs.sgv.eis.dao.NonGovDeducDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentMasterDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupMstDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupParamRltDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillNonGovCalc;
import com.tcs.sgv.eis.util.GenerateBillServiceCoreLogic;
import com.tcs.sgv.eis.valueobject.AllwValCustomVO;
import com.tcs.sgv.eis.valueobject.DeductionCustomVO;
import com.tcs.sgv.eis.valueobject.EmpBrokenPeriodVO;
import com.tcs.sgv.eis.valueobject.EmpNonGovVO;
import com.tcs.sgv.eis.valueobject.EmpPaybillLoanVO;
import com.tcs.sgv.eis.valueobject.EmpPaybillVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayAllowDedMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.eis.valueobject.HrPayPayslipNonGovt;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpMst;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpParamRlt;
import com.tcs.sgv.eis.valueobject.LoanRecoverVO;
import com.tcs.sgv.eis.valueobject.PaybillEmpCompMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.payroll.util.PayrollConstants;

@SuppressWarnings({ "unchecked" })
public class GenerateBillCalculation {
	Log logger = LogFactory.getLog(getClass());

	/**
	 * Method will calculate all the allowances and deductions and insert data in
	 * HR_PAY_PAYBILL table.
	 * 
	 * @param objectArgs
	 *            - Map type
	 * @return Map
	 * @throws Exception
	 */

	Map empDedSchemeMap = new HashMap();

	public Map generateBillCalculation(Map objectArgs) throws Exception {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

		String billTypeCmb = (String) objectArgs.get("billTypeCmb");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		// Session currSession = (Session) objectArgs.get("currentSession");
		// Boolean fromPayBillScheduler = objectArgs.get("fromPayBillScheduler")
		// != null ? (Boolean) objectArgs.get("fromPayBillScheduler") : false;

		int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
		int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
		int from_Month = objectArgs.get("from_Month") != null
				? Integer.parseInt(objectArgs.get("from_Month").toString())
				: -1;
		int from_Year = objectArgs.get("from_Year") != null ? Integer.parseInt(objectArgs.get("from_Year").toString())
				: -1;
		long paybillGrpId = objectArgs.get("paybillGrpId") != null
				? Long.parseLong(objectArgs.get("paybillGrpId").toString())
				: 0;
		// long billNo = objectArgs.get("billNo") != null ?
		// Long.parseLong(objectArgs.get("billNo").toString()) : 0;
		String empIdsStr = objectArgs.get("empIdsStr") != null ? objectArgs.get("empIdsStr").toString() : null;
		String postIdsStr = objectArgs.get("postIdsStr") != null ? objectArgs.get("postIdsStr").toString() : null;
		String orgEmpIdsStr = objectArgs.get("orgEmpIdsStr") != null ? objectArgs.get("orgEmpIdsStr").toString() : null;
		SgvcFinYearMst sgvcFinYearMst = (SgvcFinYearMst) (objectArgs.get("currentFinYr") != null
				? objectArgs.get("currentFinYr")
				: null);
		long pkSeqHrPayPaybill = Long.parseLong(objectArgs.get("pkSeqHrPayPaybill").toString());
		objectArgs.remove("pkSeqHrPayPaybill");
		if (empIdsStr != null && empIdsStr.length() > 2)
			empIdsStr = empIdsStr.substring(0, empIdsStr.length() - 1);

		if (postIdsStr != null && postIdsStr.length() > 2)
			postIdsStr = postIdsStr.substring(0, postIdsStr.length() - 1);

		if (orgEmpIdsStr != null && orgEmpIdsStr.length() > 2)
			orgEmpIdsStr = orgEmpIdsStr.substring(0, orgEmpIdsStr.length() - 1);

		// added by Ankit Bhatt for getting total Gross and net amt of Bill
		long billGrossAmt = 0;
		long billNetAmt = 0;
		// ended

		int contractEmpType = Integer.parseInt(resourceBundle.getString("contract"));
		int fixedEmpType = Integer.parseInt(resourceBundle.getString("fixed"));

		int dcpsId = Integer.parseInt(resourceBundle.getString("dcpsId"));
		int dcpsDaId = Integer.parseInt(resourceBundle.getString("dcpsDaId"));
		int dcpsPayId = Integer.parseInt(resourceBundle.getString("dcpsPayId"));
		int dcpsDelayId = Integer.parseInt(resourceBundle.getString("dcpsDelayId"));

		List<EmpPaybillVO> hrEisOtherDtls = (List<EmpPaybillVO>) (objectArgs.get("hrEisOtherDtls") != null
				? objectArgs.get("hrEisOtherDtls")
				: new ArrayList<HrEisOtherDtls>());
		// Map<Long,EmpPaybillVO> empPaybillVOMap =
		// (HashMap)(objectArgs.get("empPaybillVOMap")!=null?objectArgs.get("empPaybillVOMap"):new
		// HashMap<Long,EmpPaybillVO>());
		// List vacantPostIdList
		// =(List)(objectArgs.get("vacantPostIdList")!=null?objectArgs.get("vacantPostIdList"):new
		// ArrayList());

		long paybillGenerated = 0;
		int counter = 0; // used for checking nextSeqNumber of HR_PAY_PAYBILL
		// long payBillIdCnt = 0;
		long spID = 0;
		long ppID = 0;
		String fest_adv_id = resourceBundle.getString("FEST_ADV_EDP_ID");
		String food_adv_id = resourceBundle.getString("FOOD_EDP_ID");
		String gpf_adv_id = resourceBundle.getString("GPF_ADVANCE_EDP_ID");
		String pay_recovery_id = resourceBundle.getString("PAY_RECV_EDP_ID");
		long bonusId = 0;

		// paybillGrpId = 0;

		Date sysdate = new Date();
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		// OrgPostMst orgPostMst = new OrgPostMst();
		IdGenerator idGen = new IdGenerator();

		long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

		long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,
				serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
		cmnDatabaseMstDaoImpl = null;

		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,
				serv.getSessionFactory());
		CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
		cmnLocationMstDaoImpl = null;

		/*
		 * long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		 * CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new
		 * CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
		 * CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
		 */

		long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		orgPostMstDaoImpl = null;

		PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		PayComponentMasterDAOImpl payComponentMasterDAOImpl = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class,
				serv.getSessionFactory());
		PayComponentRuleGroupMstDAOImpl payComponentRuleGroupMstDAOImpl = new PayComponentRuleGroupMstDAOImpl(
				HrPayRuleGrpMst.class, serv.getSessionFactory());
		PayComponentRuleGroupParamRltDAOImpl payComponentRuleGroupParamRltDAOImpl = new PayComponentRuleGroupParamRltDAOImpl(
				HrPayRuleGrpParamRlt.class, serv.getSessionFactory());

		Calendar tempCalGiven = Calendar.getInstance();
		tempCalGiven.set(Calendar.YEAR, yearGiven);
		tempCalGiven.set(Calendar.MONTH, (monthGiven - 1));
		tempCalGiven.set(Calendar.DAY_OF_MONTH, 1);
		Date givenDate = tempCalGiven.getTime();

		long billNo = Long.valueOf(objectArgs.get("billNo").toString());
		logger.info("Loan Fetching Query start time" + System.currentTimeMillis());
		List empLoanLst = payDao.getEmpLoanData(empIdsStr, locId, billNo, givenDate);
		logger.info("Loan Fetching Query End time" + System.currentTimeMillis());
		Map<Long, List> empLoanMap = generateEmpLoanVOs(empLoanLst);
		objectArgs.put("empLoanMap", empLoanMap);

		empLoanLst = null;

		logger.info("Interest Loan Fetching Query start time" + System.currentTimeMillis());
		LoanIntRecoverDAOImpl hrLoanIntRecoverDao = new LoanIntRecoverDAOImpl(HrLoanEmpIntRecoverDtls.class,
				serv.getSessionFactory());
		List empIntRecoverList = hrLoanIntRecoverDao.getAllData(empIdsStr, locId, billNo, givenDate);
		logger.info("Interest Loan Fetching Query End time" + System.currentTimeMillis());
		Map<Long, LoanRecoverVO> loanIntRecoverMap = generateLoanRecoverMap(empIntRecoverList);
		objectArgs.put("loanIntRecoverMap", loanIntRecoverMap);

		loanIntRecoverMap = null;
		empIntRecoverList = null;

		logger.info("Principal Loan Fetching Query start time" + System.currentTimeMillis());
		LoanPrinRecoverDAOImpl hrLoanPrinRecoverDao = new LoanPrinRecoverDAOImpl(HrLoanEmpPrinRecoverDtls.class,
				serv.getSessionFactory());
		List empPrincRecoverList = hrLoanPrinRecoverDao.getAllData(empIdsStr, locId, billNo, givenDate);
		logger.info("Principal Loan Fetching Query End time" + System.currentTimeMillis());
		Map<Long, LoanRecoverVO> loanPrincRecoverMap = generateLoanRecoverMap(empPrincRecoverList);
		objectArgs.put("loanPrincRecoverMap", loanPrincRecoverMap);

		loanPrincRecoverMap = null;
		empPrincRecoverList = null;

		// added by manish dnt change this

		List<HrPayRuleGrpMst> activeAllowanceRuleList = (List<HrPayRuleGrpMst>) payComponentRuleGroupMstDAOImpl
				.getAllActiveRuleList(PayrollConstants.PAYROLL_ALLOWANCE_TYPE);
		objectArgs.put("activeAllowanceRuleList", activeAllowanceRuleList);
		activeAllowanceRuleList = null;

		List<HrPayRuleGrpMst> activeDeductionRuleList = (List<HrPayRuleGrpMst>) payComponentRuleGroupMstDAOImpl
				.getAllActiveDeductionRuleList();
		objectArgs.put("activeDeductionRuleList", activeDeductionRuleList);
		activeDeductionRuleList = null;

		List activeAllowRuleParamMpgList = (List) payComponentRuleGroupParamRltDAOImpl
				.getAllActiveRuleParamMpgList(PayrollConstants.PAYROLL_ALLOWANCE_TYPE);
		objectArgs.put("activeAllowRuleParamMpgList", activeAllowRuleParamMpgList);
		activeAllowRuleParamMpgList = null;

		List activeDeducRuleParamMpgList = payComponentRuleGroupParamRltDAOImpl.getAllActiveDeductionRuleParamMpgList();
		objectArgs.put("activeDeducRuleParamMpgList", activeDeducRuleParamMpgList);
		activeDeducRuleParamMpgList = null;

		List<HrPayRuleGrpMst> activeAllowanceUsedInFormulaRuleList = (List<HrPayRuleGrpMst>) payComponentRuleGroupMstDAOImpl
				.getAllActiveRuleList(PayrollConstants.PAYROLL_ALLOWANCE_TYPE, 1);
		objectArgs.put("activeAllowanceUsedInFormulaRuleList", activeAllowanceUsedInFormulaRuleList);
		activeAllowanceUsedInFormulaRuleList = null;

		List<HrPayRuleGrpMst> activeDeductionUsedInFormulaRuleList = (List<HrPayRuleGrpMst>) payComponentRuleGroupMstDAOImpl
				.getAllActiveDeductionRuleList(1);
		objectArgs.put("activeDeductionUsedInFormulaRuleList", activeDeductionUsedInFormulaRuleList);
		activeDeductionUsedInFormulaRuleList = null;

		List activeAllowUsedInFormulaRuleParamMpgList = payComponentRuleGroupParamRltDAOImpl
				.getAllActiveRuleParamMpgList(PayrollConstants.PAYROLL_ALLOWANCE_TYPE, 1);
		objectArgs.put("activeAllowUsedInFormulaRuleParamMpgList", activeAllowUsedInFormulaRuleParamMpgList);
		activeAllowUsedInFormulaRuleParamMpgList = null;

		List activeDeducUsedInFormulaRuleParamMpgList = payComponentRuleGroupParamRltDAOImpl
				.getAllActiveDeductionRuleParamMpgList(1);
		objectArgs.put("activeDeducUsedInFormulaRuleParamMpgList", activeDeducUsedInFormulaRuleParamMpgList);
		activeDeducUsedInFormulaRuleParamMpgList = null;

		List<HrPayAllowDedMst> ruleBasedAllowanceList = (List<HrPayAllowDedMst>) payComponentMasterDAOImpl
				.getPayActiveComponets(PayrollConstants.PAYROLL_ALLOWANCE_TYPE);
		objectArgs.put("ruleBasedAllowanceList", ruleBasedAllowanceList);
		ruleBasedAllowanceList = null;

		List<HrPayAllowDedMst> ruleBasedDeductionList = (List<HrPayAllowDedMst>) payComponentMasterDAOImpl
				.getPayActiveComponets(PayrollConstants.PAYROLL_DEDUCTION_TYPE);
		objectArgs.put("ruleBasedDeductionList", ruleBasedDeductionList);
		ruleBasedDeductionList = null;

		long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		List ruleBasedAllowDeducUsedInFormulaList = payComponentMasterDAOImpl.getPayCompUsedInFormula(0, 0, langId);
		// List<HrPayAllowDedMst> ruleBasedDeductionUsedInFormulaList =
		// (List<HrPayAllowDedMst>)
		// payComponentMasterDAOImpl.getPayCompUsedInFormula(0, 0, langId);

		/**
		 * 
		 * Logic to seperate Allow/Ded List based on the Commision Id Starts
		 * 
		 */
		if (ruleBasedAllowDeducUsedInFormulaList != null && !ruleBasedAllowDeducUsedInFormulaList.isEmpty()) {
			int dataListSize = ruleBasedAllowDeducUsedInFormulaList.size();
			StringBuilder sbr = null;
			HrPayAllowDedMst allowDedMst = null;
			long commissionId = 0;
			Object[] data = null;
			String sbrString = null;
			for (int ctr = 0; ctr < dataListSize; ctr++) {
				data = (Object[]) ruleBasedAllowDeducUsedInFormulaList.get(ctr);
				allowDedMst = data != null && data.length > 0 && data[0] != null ? (HrPayAllowDedMst) data[0]
						: new HrPayAllowDedMst();
				commissionId = data != null && data.length > 1 && data[1] != null ? (Long) data[1] : 0;

				sbr = new StringBuilder(String.valueOf(commissionId)).append("_");
				switch (allowDedMst.getType()) {
				case PayrollConstants.PAYROLL_ALLOWANCE_TYPE:
					sbr.append(PayrollConstants.PAYROLL_ALLOWANCE_TYPE);
					break;

				case PayrollConstants.PAYROLL_DEDUCTION_TYPE:
					sbr.append(PayrollConstants.PAYROLL_DEDUCTION_TYPE);
					break;

				default:
					sbr = null;
					break;

				}
				sbrString = String.valueOf(sbr);
				if (objectArgs.containsKey(sbrString)) {
					List<HrPayAllowDedMst> tempDataList = (List<HrPayAllowDedMst>) objectArgs.get(sbrString);
					tempDataList.add(allowDedMst);
					objectArgs.put(sbrString, tempDataList);
					tempDataList = null;
				} else {
					List<HrPayAllowDedMst> tempDataList = new ArrayList<HrPayAllowDedMst>();
					tempDataList.add(allowDedMst);
					objectArgs.put(sbrString, tempDataList);
					tempDataList = null;
				}

			}
		}
		ruleBasedAllowDeducUsedInFormulaList = null;

		// ended by manish

		Map qtrMap = new HashMap();
		logger.info("Quater Fetching Query Start time" + System.currentTimeMillis());
		List empQtrRentList = payDao.getEmpQtrData(empIdsStr, locId, billNo, givenDate);
		logger.info("Quater Fetching Query End time" + System.currentTimeMillis());
		for (int qtrCounter = 0; qtrCounter < empQtrRentList.size(); qtrCounter++) {
			Object[] row = (Object[]) empQtrRentList.get(qtrCounter);
			long eisEmpId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
			int qtrRent = row[1] != null ? Integer.valueOf(row[1].toString()) : 0;

			if (!qtrMap.containsKey(eisEmpId)) {
				qtrMap.put(eisEmpId, qtrRent);
			}
		}

		empQtrRentList = null;

		Map empGisMap = new HashMap();
		logger.info("GIS Fetching Query Start time" + System.currentTimeMillis());
		List empGisList = payDao.getGroupIdFromEmpId(empIdsStr, locId, billNo, givenDate);
		logger.info("GIS Fetching Query End time" + System.currentTimeMillis());
		Object[] row = null;
		int empGisSize = empGisList.size();
		for (int gisCounter = 0; gisCounter < empGisSize; gisCounter++) {
			row = (Object[]) empGisList.get(gisCounter);
			// long gisGradeId = 100064;
			long gisGradeId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
			long gisGradeCode = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
			logger.info("grade id is :::::" + gisGradeId);
			System.out.print("grade id is :::" + gisGradeId);
			// change for ZP
			/*
			 * long gisGradeCode = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
			 * ended
			 */
			// long gisGradeCode = 2;
			logger.info("gisGradeCode::::" + gisGradeCode);
			System.out.print("gisGradeCode::::" + gisGradeId);
			long empId = row[2] != null ? Long.valueOf(row[2].toString()) : 0;

			if (!empGisMap.containsKey(empId)) {
				empGisMap.put(empId, gisGradeId + "~" + gisGradeCode);
			}
		}

		empGisList = null;

		Map empOfficeMap = new HashMap();
		HrPayOfficePostMpgDAOImpl hrPayOfficePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class,
				serv.getSessionFactory());
		logger.info("Office Post Fetching Query Start time" + System.currentTimeMillis());
		List officePostList = hrPayOfficePostMpgDAOImpl.getOfficeClass(postIdsStr, locId, billNo, givenDate);
		logger.info("Office Post Fetching Query End time" + System.currentTimeMillis());
		int officePostListSize = officePostList.size();
		row = null;
		for (int officeCnt = 0; officeCnt < officePostListSize; officeCnt++) {
			row = (Object[]) officePostList.get(officeCnt);
			long officePostId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
			String OfficeCityClass = row[1] != null ? String.valueOf(row[1].toString()) : "";

			if (!empOfficeMap.containsKey(officePostId)) {
				empOfficeMap.put(officePostId, OfficeCityClass);
			}
		}

		officePostList = null;

		Map empDCPSValMap = new HashMap();
		EmpCompMpgDAOImpl empCompoMpg = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class, serv.getSessionFactory());
		logger.info("Congtribution Fetching Query Start time" + System.currentTimeMillis());
		List empDCPSValList = empCompoMpg.getDCPSValues(orgEmpIdsStr, monthGiven, sgvcFinYearMst.getFinYearId(), locId,
				billNo, givenDate);
		logger.info("Contribution Fetching Query End time" + System.currentTimeMillis());
		if (empDCPSValList != null && empDCPSValList.size() > 0) {
			empDCPSValMap = generateDCPSMap(empDCPSValList);
			logger.info("DCPS Map in GenerateBillCalculation is " + empDCPSValMap);
		}
		/*NPS Allowance and decution by arrear*/
		Map empDCPSValNPSOnlineMap= new HashMap();
		List empDCPSValNPSOnlineList = empCompoMpg.getNPSOnlineDCPSValues(orgEmpIdsStr, monthGiven, sgvcFinYearMst.getFinYearId(), locId,
				billNo, givenDate);
		logger.info("Contribution Allowance and decution Query End time" + System.currentTimeMillis());
		if (empDCPSValNPSOnlineList != null && empDCPSValNPSOnlineList.size() > 0) {
			empDCPSValNPSOnlineMap = generateDCPSMap(empDCPSValNPSOnlineList);
			logger.info("NPS Online Allowance and deduction DCPS Map in GenerateBillCalculation is " + empDCPSValNPSOnlineMap);
		}
		objectArgs.put("empDCPSValNPSOnlineMap", empDCPSValNPSOnlineMap);
		/*NPS Allowance and decution by arrear*/
		

		objectArgs.put("empDCPSValMap", empDCPSValMap);
		empDCPSValList = null;

		/*
		 * Calendar calGiven = Calendar.getInstance(); calGiven.set(Calendar.YEAR,
		 * yearGiven); calGiven.set(Calendar.MONTH, (monthGiven - 1));
		 * calGiven.set(Calendar.DAY_OF_MONTH, 1);
		 */
		/*
		 * Date givenDate = calGiven.getTime(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("MM/dd/yyyy"); String strGivenDate= sdf.format(givenDate);
		 */

		EmpAllwMapDAOImpl empAllwMapDAOImpl = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class, serv.getSessionFactory());
		logger.info("Allowance Fetching Query Start time" + System.currentTimeMillis());
		List<HrPayAllowTypeMst> allowTypeMstList = empAllwMapDAOImpl.getMappedAllowancesList(empIdsStr, locId, billNo,
				givenDate);
		logger.info("Allowance Fetching Query End time" + System.currentTimeMillis());
		logger.info("Emp compo mapping list for Allowance size is " + allowTypeMstList.size());
		Map<Long, List<AllwValCustomVO>> empAllowCompoMap = generateEmpAllowCompoMap(allowTypeMstList);
		objectArgs.put("empAllowCompoMap", empAllowCompoMap);

		allowTypeMstList = null;

		// 20 jan 2012 changes here
		DeptCompMPGDAOImpl deptCompMPGDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
		objectArgs.put("mapEditableAllowance", generatemapForAllEditableAllowance(
				deptCompMPGDAOImpl.getAllActiveEditableAllowance(empIdsStr, locId, billNo, givenDate)));
		objectArgs.put("mapEditableDeduction", generatemapForAllEditableDeduction(
				deptCompMPGDAOImpl.getAllActiveEditableDeduction(empIdsStr, locId, billNo, givenDate)));
		deptCompMPGDAOImpl = null;

		DeductionDtlsDAOImpl deductionDaoImpl = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,
				serv.getSessionFactory());
		logger.info("Deductions Fetching Query Start time" + System.currentTimeMillis());
		List<HrPayDeducTypeMst> deducTypeMstList = deductionDaoImpl.getMappedDeductionsList(empIdsStr, locId, billNo,
				givenDate);
		logger.info("Deductions Fetching Query End time" + System.currentTimeMillis());
		logger.info("Emp compo mapping list for Deduction size is " + deducTypeMstList.size());
		Map<Long, List<DeductionCustomVO>> empDeducCompoMap = generateEmpDeducCompoMap(deducTypeMstList);
		objectArgs.put("empDeducCompoMap", empDeducCompoMap);
		deductionDaoImpl = null;
		deducTypeMstList = null;

		NonGovDeducDAO nonGovDeducDAO = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class, serv.getSessionFactory());
		logger.info("Non Gov Fetching Query Start time" + System.currentTimeMillis());
		List empNonGovList = nonGovDeducDAO.getNonGovDeducDataByEmps(empIdsStr, locId, billNo, givenDate);
		logger.info("Non Gov Fetching Query End time" + System.currentTimeMillis());
		Map<Long, Map<Integer, EmpNonGovVO>> empNonGovMap = generateNonGovMap(empNonGovList);
		objectArgs.put("empNonGovMap", empNonGovMap);

		empNonGovList = null;
		empNonGovMap = null;

		List checkNonGovPayslipList = null;
		// nonGovDeducDAO.checkNonGovtPayslipEntries(empIdsStr, monthGiven,
		// yearGiven);
		Map<Long, Map<Integer, Long>> nonGovPayslipMap = generatePayslipNonGovMap(checkNonGovPayslipList);
		objectArgs.put("nonGovPayslipMap", nonGovPayslipMap);

		checkNonGovPayslipList = null;
		nonGovPayslipMap = null;
		nonGovDeducDAO = null;

		List<EmpBrokenPeriodVO> empBrokenList = payDao.getBrokenPeriodData(empIdsStr, monthGiven,
				sgvcFinYearMst.getFinYearId(), locId, billNo, givenDate);
		logger.info("Broken Fetching Query Start time" + System.currentTimeMillis());
		Map<Long, List<EmpBrokenPeriodVO>> empBrokenMap = generateBrokenMap(empBrokenList);
		logger.info("Broken Fetching Query End time" + System.currentTimeMillis());
		objectArgs.put("empBrokenMap", empBrokenMap);

		empBrokenList = null;
		empBrokenMap = null;

		// long empCurrentStatus=1; //Active components
		List isPTMappedList = empAllwMapDAOImpl.isComponenetMapped(empIdsStr, 2500135, 35, locId, billNo, givenDate);// for
																														// PT
		Map<Long, Long> isPTMappedMap = generatePTMap(isPTMappedList);
		objectArgs.put("isPTMappedMap", isPTMappedMap);

		isPTMappedList = null;
		isPTMappedMap = null;
		/*
		 * PayrollCommonDAO payrollCommonDAO =new
		 * PayrollCommonDAO(serv.getSessionFactory()); Map allowEdpMap =
		 * payrollCommonDAO.getEdpAllwMap(0, locId); Map deducEdpMap =
		 * payrollCommonDAO.getEdpDeducMap(0, locId);
		 */
		OtherDetailDAOImpl detailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
		Map allowEdpMap = detailDAOImpl.getEdpAllwMap(0, locId);
		Map deducEdpMap = detailDAOImpl.getEdpDeducMap(0, locId);

		detailDAOImpl = null;

		objectArgs.put("allowEdpMap", allowEdpMap);
		objectArgs.put("deducEdpMap", deducEdpMap);

		allowEdpMap = null;
		deducEdpMap = null;

		int paybill_Month = monthGiven;
		int paybill_Year = yearGiven;
		if (billTypeCmb.equals("multiplemonthpaybill")) {

			paybill_Month = from_Month;
			paybill_Year = from_Year;
		}

		Calendar calc = Calendar.getInstance();
		calc.set(Calendar.MONTH, monthGiven - 1);
		calc.set(Calendar.YEAR, yearGiven);
		java.util.Date approvalDate = calc.getTime();
		calc = null;
		logger.info("the approval date is " + approvalDate);

		HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, serv.getSessionFactory());
		List<HrPayEdpCompoMpg> edpListEditable = hrEdp.getAllData();
		objectArgs.put("edpListEditable", edpListEditable);

		edpListEditable = null;
		hrEdp = null;

		List<HrPayPaybill> payBillVOParentList = new ArrayList<HrPayPaybill>();
		List<PaybillEmpCompMpg> paybillEmpCompoMpgList = new ArrayList<PaybillEmpCompMpg>();
		List<List<HrPayPaybillLoanDtls>> hrPayPaybillLoanParentDtlsList = new ArrayList<List<HrPayPaybillLoanDtls>>();
		List<List<HrPayPayslipNonGovt>> payslipNonGovtsParentList = new ArrayList<List<HrPayPayslipNonGovt>>();

		int hrEisOtherDtlsSize = hrEisOtherDtls.size();
		int lIntTotalPksHrPayPaybillEmpCompMpg = (hrEisOtherDtls.size() * 3);
		objectArgs.put("counter", lIntTotalPksHrPayPaybillEmpCompMpg);
		Long pkSeqHrPayPaybillEmpCompMpg = idGen.PKGenerator("HR_PAY_PAYBILL_EMP_COMP_MPG", objectArgs);
		objectArgs.remove("counter");

		boolean allowGeneration = true;
		nonGovDeducDAO = null;
		String empName = null;
		Long eisEmpIdForNGR = null;

		for (int cnt = 0; cnt < hrEisOtherDtlsSize; cnt++) {
			// by manoj for the 10-20 day issue

			// Set orguserPostrlt =
			// hrEisOtherDtls.get(cnt).getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();
			// OrgUserMst userMst =
			// hrEisOtherDtls.get(cnt).getHrEisEmpMst().getOrgEmpMst().getOrgUserMst();

			// HrEisEmpMst hrEisMst = hrEisOtherDtls.get(cnt).getHrEisEmpMst();

			/*
			 * Map maxDaysMap = generateBillServ.checkMaxDayOfPostRecord(orguserPostrlt,
			 * paybill_Month, paybill_Year); maxDaysOfPost =
			 * Long.parseLong(maxDaysMap.get("maxDaysOfPost").toString());
			 * maxDaysUserPostRltId = Long.parseLong(maxDaysMap.get("maxDaysUserPostRltId"
			 * ).toString()); maxDaysUserId =
			 * Long.parseLong(maxDaysMap.get("maxDaysUserId").toString()); daysOfPost =
			 * maxDaysMap.get("daysOfPost")!=null?Long.parseLong(maxDaysMap
			 * .get("daysOfPost").toString()):0;
			 */
			HrPayPaybill payBillVO = null;

			if (!billTypeCmb.equals("multiplemonthpaybill")) {
				// added by Ravysh to prevent entries with zero amount in
				// multiple
				// month supplimentary bill

				payBillVO = new HrPayPaybill();

				// Map inputMap = objectArgs;

				EmpPaybillVO hrEisOtherDtlsObj = hrEisOtherDtls.get(cnt);
				if (qtrMap.containsKey(hrEisOtherDtlsObj.getEisEmpId())) {
					logger.info("Quearter amount to be set for " + hrEisOtherDtlsObj.getEisEmpId() + " is ");
					logger.info(" " + qtrMap.get(hrEisOtherDtlsObj.getEisEmpId()).toString());
					hrEisOtherDtlsObj
							.setQtrRentAmt(Long.valueOf(qtrMap.get(hrEisOtherDtlsObj.getEisEmpId()).toString()));
				}

				if (empGisMap.containsKey(hrEisOtherDtlsObj.getEisEmpId())) {
					String gisDtls = empGisMap.get(hrEisOtherDtlsObj.getEisEmpId()).toString();
					StringTokenizer strToken = new StringTokenizer(gisDtls, "~");
					gisDtls = null;
					hrEisOtherDtlsObj.setGradeId(strToken.hasMoreTokens() ? Long.valueOf(strToken.nextToken()) : 0);
					hrEisOtherDtlsObj
							.setGisGradeCode(strToken.hasMoreTokens() ? Long.valueOf(strToken.nextToken()) : 0);
					strToken = null;
				}

				if (empOfficeMap.containsKey(hrEisOtherDtlsObj.getPostId())) {
					hrEisOtherDtlsObj.setPostCityClass(empOfficeMap.get(hrEisOtherDtlsObj.getPostId()).toString());
					logger.info("The value of empOfficeMap.get(hrEisOtherDtlsObj.getPostId()).toString()"
							+ empOfficeMap.get(hrEisOtherDtlsObj.getPostId()).toString());
				}

				// added by saurabh for generate paybill
				/*
				 * logger.info("The current setPostCityClass is" +
				 * hrEisOtherDtlsObj.getPostCityClass());
				 */
				// inputMap.put("monthGiven",monthGiven);
				objectArgs.put("monthGiven", paybill_Month);
				objectArgs.put("yearGiven", paybill_Year);
				objectArgs.put("hrEisOtherDtls", hrEisOtherDtlsObj);
				hrEisOtherDtlsObj = null;

				objectArgs.put("fest_adv_id", fest_adv_id);
				objectArgs.put("food_adv_id", food_adv_id);
				objectArgs.put("spID", spID);
				objectArgs.put("ppID", ppID);
				objectArgs.put("gpf_adv_id", gpf_adv_id);
				objectArgs.put("pay_recovery_id", pay_recovery_id);

				// added by Ankit Bhatt for getting Bonus
				// value
				objectArgs.put("bonusId", bonusId);
				// ended

				// by manoj for 10-20 days issue of one
				// record

				// end by manoj for 10-20 days issue of one
				// record
				// Added By Urvin Shah
				objectArgs.put("orgUserMst", orgUserMst);
				objectArgs.put("orgPostMst", orgPostMst);
				objectArgs.put("cmnDatabaseMst", cmnDatabaseMst);
				objectArgs.put("cmnLocationMst", cmnLocationMst);
				// End Urvin Shah

				long payBillId = 0;

				// payBillId = idGen.PKGenerator("HR_PAY_PAYBILL", objectArgs);
				payBillId = ++pkSeqHrPayPaybill;
				GenerateBillServiceCoreLogic coreLogic = new GenerateBillServiceCoreLogic();
				Map resultMap = null;
				// Method is already synchronized, so
				// commenting synchronized block - Ankit
				// synchronized (GenerateBillService.class) {
				objectArgs.put("payBillId", payBillId);
				// Kishan - need to change below method

				resultMap = coreLogic.executeCoreLogic(objectArgs);
				// commented by saurabh for paybill validation
				/*
				 * if (objectArgs.get("allowGeneration") == null)
				 * objectArgs.put("allowGeneration", "true"); // }
				 */ // logger.info("resultMap after core logic in GenerateBillService "
					// + resultMap);
				if (!resultMap.containsKey("payBillVO")) {
					logger.info("Contains Paybill VO postId" + resultMap.get("postId").toString());
				} else {
					payBillVO = (HrPayPaybill) resultMap.get("payBillVO");
					payBillVO.setScaleId(hrEisOtherDtls.get(cnt).getScaleId());
					// commented by saurabh for paybill validation
					// payBillVO.setAccNo(hrEisOtherDtls.get(cnt).getAccNo());
					payBillVO.setId(payBillId);
					// Added by Paurav for saving Group Id
					if (paybillGrpId == 0) {
						paybillGrpId = payBillId;
					}
					// System.out.println("Paybill Group ID set in calculate file is "
					// + paybillGrpId);
					payBillVO.setPaybillGrpId(paybillGrpId);
					// ended By Paurav
					HrEisEmpMst hrEisMst = new HrEisEmpMst();
					logger.info("HrEisEmpMst going to be added in Paybill");
					hrEisMst.setEmpId(hrEisOtherDtls.get(cnt).getEisEmpId());
					payBillVO.setHrEisEmpMst(hrEisMst);
					logger.info("HrEisEmpMst added in Paybill");
					payBillVO.setCmnDatabaseMst(cmnDatabaseMst);
					payBillVO.setCmnLocationMst(cmnLocationMst);
					payBillVO.setCreatedDate(sysdate);
					payBillVO.setOrgUserMstByCreatedBy(orgUserMst);
					logger.info("orgPostMst going to be added in Paybill");
					payBillVO.setOrgPostMstByCreatedByPost(orgPostMst);
					logger.info("orgPostMst added in Paybill");
					// Added by Mrugesh for Trn Counter
					payBillVO.setTrnCounter(new Integer(1));

					// added by ravysh for adding month and
					// year in HR_PAY_PAYBILL table
					payBillVO.setMonth(paybill_Month);
					payBillVO.setYear(paybill_Year);

					if (hrEisOtherDtls != null && hrEisOtherDtls.get(cnt) != null) {
						logger.info("otherDtls going to be added in Paybill");
						HrEisOtherDtls otherDtls = new HrEisOtherDtls();
						otherDtls.setOtherId(hrEisOtherDtls.get(cnt).getOtherDtlsId());
						payBillVO.setHrEisOtherDtls(otherDtls);
						otherDtls = null;
						logger.info("otherDtls added in Paybill");
						payBillVO.setOtherTrnCntr(1); // need to be fetched from
						// other dtls once
						// history done
					}

					/*
					 * if (yearGiven != -1 && monthGiven != -1) { // Modified By Mrugesh
					 * paybillheadmpgVO.setMonth(monthGiven); paybillheadmpgVO.setYear(yearGiven);
					 * // Ended
					 * 
					 * // added by ravysh for multiple // month suppl. bill if
					 * (billTypeCmb.equals("multiplemonthpaybill")) { paybillheadmpgVO
					 * .setMonth(calSupplBill.get(Calendar.MONTH) + 1); paybillheadmpgVO
					 * .setYear(calSupplBill.get(Calendar.YEAR)); } // ended by ravysh } else {
					 * SimpleDateFormat sdf = new SimpleDateFormat("MM"); Date date = new Date();
					 * String month = sdf.format(date);
					 * 
					 * if (month.charAt(0) == '0') { month = month.substring(1, month.length()); }
					 * 
					 * sdf = new SimpleDateFormat("yyyy"); String year = sdf.format(date);
					 * 
					 * // Modified By Mrugesh paybillheadmpgVO.setMonth(Integer.parseInt(month));
					 * paybillheadmpgVO.setYear(Integer.parseInt(year)); // Ended }
					 */

					// added by ravysh for integration with
					// HDIITS method to calculate Leave salary
					// boolean userLSExist=false;
					// userLSExist=empLSDAO.checkUserByUSerId(payBillVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId());
					/*
					 * if (LS != 0) payBillVO.setLs(LS);
					 */// Adding GradePay
					long empType = hrEisOtherDtls.get(cnt).getEmpType();
					logger.info("empType:::" + empType);
					double gpay = 0;
					if (empType != contractEmpType && empType != fixedEmpType)
						gpay = hrEisOtherDtls.get(cnt).getGradePay();
					logger.info("gpay of is::" + gpay);
					payBillVO.setGpay0101(gpay);

					payBillVO.setGrossAmt(payBillVO.getGrossAmt());
					payBillVO.setNetTotal(payBillVO.getNetTotal());
					// ended by ravysh for hdiits
					logger.info("Paybill going to be created");
					// payDao.create(payBillVO);
					/*
					 * if(fromPayBillScheduler) currSession.save(payBillVO); else
					 * payDao.create(payBillVO);
					 */
					// nillbill
					/*
					 * if (payBillVO.getNetTotal() > 0) { objectArgs.put("positiveAmount", "true");
					 * }
					 * 
					 * // /checking IFSC code for each employee // to enalbe ifsc code validation
					 * for CMP only then remove // comemnt frm below line and comment 2nd line //
					 * if( (isCmpDdoLocation && hrEisOtherDtls.get(cnt)!=null && //
					 * hrEisOtherDtls.get(cnt).getEisEmpId()!=0)){ if ((hrEisOtherDtls.get(cnt) !=
					 * null && hrEisOtherDtls.get( cnt).getEisEmpId() != 0)) { if
					 * ((hrEisOtherDtls.get(cnt).getIfscCode().equals("") || hrEisOtherDtls
					 * .get(cnt).getIfscCode().equals(null))) { objectArgs.put("allowGeneration",
					 * "false"); objectArgs.put("ifscCodeAbsent", "true");
					 * 
					 * empName = hrLoanIntRecoverDao .getEmpNameFromEmpId(hrEisOtherDtls
					 * .get(cnt).getEisEmpId()); if (objectArgs.get("ifscAbsentEmpName") != null)
					 * objectArgs.put("ifscAbsentEmpName", objectArgs .get("ifscAbsentEmpName") +
					 * ", " + empName); else objectArgs.put("ifscAbsentEmpName", empName);
					 * logger.info("allowGeneration  " + allowGeneration);
					 * logger.info("payBillVO.getNetTotal() added is  " +
					 * objectArgs.get("ifscAbsentEmpName")); } if
					 * (hrEisOtherDtls.get(cnt).getIfscCode().length() != 11) {
					 * objectArgs.put("allowGeneration", "false"); objectArgs.put("ifscCodeInValid",
					 * "true");
					 * 
					 * empName = hrLoanIntRecoverDao .getEmpNameFromEmpId(hrEisOtherDtls
					 * .get(cnt).getEisEmpId()); if (objectArgs.get("ifscCodeInValidEmpName") !=
					 * null) objectArgs.put("ifscCodeInValidEmpName", objectArgs
					 * .get("ifscCodeInValidEmpName") + ", " + empName); else
					 * objectArgs.put("ifscCodeInValidEmpName", empName);
					 * logger.info("payBillVO.getNetTotal() added is  " +
					 * objectArgs.get("ifscCodeInValidEmpName")); } } if ((hrEisOtherDtls.get(cnt)
					 * != null && hrEisOtherDtls.get( cnt).getEisEmpId() != 0) &&
					 * payBillVO.getNetTotal() == 0) { logger.info("payBillVO.getNetTotal() " +
					 * payBillVO.getNetTotal()); logger.info("payBillVO.getNetTotal() added is  " +
					 * hrEisOtherDtls.get(cnt).getEisEmpId()); objectArgs.put("allowGeneration",
					 * "false"); objectArgs.put("zeroAmount", "true"); empName = hrLoanIntRecoverDao
					 * .getEmpNameFromEmpId(hrEisOtherDtls.get(cnt) .getEisEmpId()); if
					 * (objectArgs.get("empName") != null) objectArgs.put("empName",
					 * objectArgs.get("empName") + ", " + empName); else objectArgs.put("empName",
					 * empName); logger.info("payBillVO.getNetTotal() added is  " +
					 * objectArgs.get("empName"));
					 * 
					 * } if ((hrEisOtherDtls.get(cnt) != null && hrEisOtherDtls.get(
					 * cnt).getEisEmpId() != 0) && payBillVO.getNetTotal() <= 0) {
					 * objectArgs.put("allowGeneration", "false"); objectArgs.put("negativeAmount",
					 * "true");
					 * 
					 * logger.info("net total of employe in calculation : " +
					 * payBillVO.getNetTotal()); logger.info("negativeAmount in calculation : " +
					 * objectArgs.get("negativeAmount")); empName = hrLoanIntRecoverDao
					 * .getEmpNameFromEmpId(hrEisOtherDtls.get(cnt) .getEisEmpId()); if
					 * (objectArgs.get("negEmpName") != null) objectArgs.put("negEmpName",
					 * objectArgs .get("negEmpName") + ", " + empName); else
					 * objectArgs.put("negEmpName", empName); } if ((hrEisOtherDtls.get(cnt) != null
					 * && hrEisOtherDtls.get( cnt).getEisEmpId() != 0) && (payBillVO.getAccNo() ==
					 * null || payBillVO .getAccNo().trim().equals(""))) { // uncomment when null
					 * acc is added objectArgs.put("allowGeneration", "false");
					 * objectArgs.put("nullAccount", "true"); logger.info("nullAccount: " +
					 * objectArgs.get("negativeAmount")); empName = hrLoanIntRecoverDao
					 * .getEmpNameFromEmpId(hrEisOtherDtls.get(cnt) .getEisEmpId()); if
					 * (objectArgs.get("nullEmpName") != null) objectArgs.put("nullEmpName",
					 * objectArgs .get("nullEmpName") + ", " + empName); else
					 * objectArgs.put("nullEmpName", empName); } // nillbill
					 * 
					 * // / validations for NGR by Shailesh on 15-11-2013 : start // to apply
					 * NGR-Net validation on CMP DDO un-comment below // line // if(isCmpDdoLocation
					 * && hrEisOtherDtls.get(cnt)!=null && //
					 * hrEisOtherDtls.get(cnt).getEisEmpId()!=0 ){ if (hrEisOtherDtls.get(cnt) !=
					 * null && hrEisOtherDtls.get(cnt).getEisEmpId() != 0) {
					 * logger.info("checking net - ngr "); Map empNonGovMapObjArgs = (Map)
					 * objectArgs .get("empNonGovMap"); Map empNonGovMapObj = (Map)
					 * empNonGovMapObjArgs .get(hrEisOtherDtls.get(cnt).getEisEmpId()); if
					 * (empNonGovMapObj != null && !empNonGovMapObj.isEmpty()) { List<EmpNonGovVO>
					 * empNonGovVo = new ArrayList<EmpNonGovVO>( empNonGovMapObj.values()); long
					 * empNgrAmountSum = 0; if (empNonGovVo != null && !empNonGovVo.isEmpty()) for
					 * (EmpNonGovVO obj : empNonGovVo) { logger .info("ngrEmpID : " + obj.getEmpId()
					 * + " ngrCode " + obj.getDeducCode() + " ngr amounnt " + obj.getAmount());
					 * empNgrAmountSum = empNgrAmountSum + obj.getAmount(); }
					 * 
					 * long finalNetAfterNGR = (long) payBillVO .getNetTotal() - empNgrAmountSum;
					 * 
					 * if (empNgrAmountSum != 0l && finalNetAfterNGR <= 0) {
					 * objectArgs.put("allowGeneration", "false");
					 * objectArgs.put("negativeNetAfterNGR", "true");
					 * 
					 * empName = hrLoanIntRecoverDao .getEmpNameFromEmpId(hrEisOtherDtls
					 * .get(cnt).getEisEmpId()); if (objectArgs .get("negativeNetAfterNGREmpName")
					 * != null) objectArgs .put( "negativeNetAfterNGREmpName", objectArgs
					 * .get("negativeNetAfterNGREmpName") + ", " + empName); else objectArgs.put(
					 * "negativeNetAfterNGREmpName", empName); logger.info("allowGeneration  " +
					 * allowGeneration); logger .info("payBillVO.getNetTotal() added is  " +
					 * objectArgs .get("negativeNetAfterNGR")); } empNonGovVo = null; }
					 * empNonGovMapObj = null; empNonGovMapObjArgs = null; }
					 */
					// / validations for NGR by Shailesh on 15-11-2013 : end
					
					//added by Tejashree for GPF Subscription limit should not exceed 5lacs
					if( ( hrEisOtherDtls.get(cnt)!=null && hrEisOtherDtls.get(cnt).getEisEmpId()!=0)){
						EmpAllwMapDAOImpl dao = new EmpAllwMapDAOImpl(null, serv.getSessionFactory());
                        Long regStatus= dao.getDcpsEmp(hrEisOtherDtls.get(cnt).getEisEmpId());
                        if(regStatus == 2){
                            //logger.error("after regstatus  "+hrEisOtherDtls.get(cnt).getEisEmpId());
                        long gpfsumcount = dao.getgpfSubscritptionsum(hrEisOtherDtls.get(cnt).getEisEmpId(),yearGiven,monthGiven);
                        double gpfsubscription = (payBillVO.getDeduc9703()+payBillVO.getDeduc9702()+payBillVO.getDeduc9583() 
                        + payBillVO.getDeduc9780() + payBillVO.getDeduc9216() + payBillVO.getDeduc9217() + payBillVO.getDeduc1037() + payBillVO.getDeduc1040() + payBillVO.getDeduc9705()
                        + payBillVO.getDeduc9704() + gpfsumcount);

            
                        if(gpfsubscription > 500000)
                        {
                            logger.error("allowGeneration  for GpfCodeAbsent "+allowGeneration);
                            objectArgs.put("allowGeneration", "false");
                            objectArgs.put("GpfCodeAbsent", "true");

                            empName=hrLoanIntRecoverDao.getEmpNameFromEmpId(hrEisOtherDtls.get(cnt).getEisEmpId());
                            if(objectArgs.get("GpfSubsAbsentEmpName")!=null)
                                objectArgs.put("GpfSubsAbsentEmpName",objectArgs.get("GpfSubsAbsentEmpName")+", "+empName);
                            else
                                objectArgs.put("GpfSubsAbsentEmpName", empName);

                        }




                        }                         
                    }
					payBillVOParentList.add(payBillVO);
					// objectArgs.put("payBillVO_Batch", payBillVO);

					logger.info("Paybill created");

					paybillGenerated++;
					// change by manoj for vacant post issue
					if (counter == 0) {
						// payBillIdCnt = payBillId;
						counter++;
					}

					// added by Ankit Bhatt - For refining
					// the code
					// GenerateBillServiceCoreLogic should
					// not contain any insert/update, so
					// moving code from
					// CoreLogic to This file

					// GenericDaoHibernateImpl paybillLoanDtlsDao = new
					// GenericDaoHibernateImpl(HrPayPaybillLoanDtls.class);
					// paybillLoanDtlsDao.setSessionFactory(serv.getSessionFactory());
					logger.info("resultMap.containsKey(lstHrPayLoanEmpDtls) "
							+ resultMap.containsKey("lstHrPayLoanEmpDtls"));
					if (resultMap.containsKey("lstHrPayLoanEmpDtls")) {
						List lstHrPayPaybillLoanDtls = (List) (resultMap.get("lstHrPayLoanEmpDtls") != null
								? resultMap.get("lstHrPayLoanEmpDtls")
								: null);
						logger.info("List size for loan insertion is " + lstHrPayPaybillLoanDtls.size());
						List<HrPayPaybillLoanDtls> hrPayPaybillLoanDtlsList = new ArrayList<HrPayPaybillLoanDtls>();
						if (lstHrPayPaybillLoanDtls != null) {
							int size = lstHrPayPaybillLoanDtls.size();
							for (int loanCnt = 0; loanCnt < size; loanCnt++) {
								// HrPayPaybillLoanDtls hrPayPaybillLoanDtls =
								// new HrPayPaybillLoanDtls();
								HrPayPaybillLoanDtls hrPayPaybillLoanDtls = (HrPayPaybillLoanDtls) lstHrPayPaybillLoanDtls
										.get(loanCnt);
								hrPayPaybillLoanDtls.setPaybillId(payBillVO);
								logger.info("Going to insert loan data " + hrPayPaybillLoanDtls.getId());
								// paybillLoanDtlsDao.getSession().saveOrUpdate(hrPayPaybillLoanDtls);
								hrPayPaybillLoanDtlsList.add(hrPayPaybillLoanDtls);
								hrPayPaybillLoanDtls = null;
								/*
								 * if(fromPayBillScheduler) currSession.saveOrUpdate (hrPayPaybillLoanDtls);
								 * else paybillLoanDtlsDao .getSession().saveOrUpdate( hrPayPaybillLoanDtls );
								 */
							}
							hrPayPaybillLoanParentDtlsList.add(hrPayPaybillLoanDtlsList);
						}
						hrPayPaybillLoanDtlsList = null;
						lstHrPayPaybillLoanDtls = null;
						// objectArgs.put("hrPayPaybillLoanDtlsList_Batch",
						// hrPayPaybillLoanDtlsList);
					}

					// ended

					// changed by Ankit Bhatt
					objectArgs.put("empId", hrEisMst.getEmpId());
					hrEisMst = null;
					objectArgs.put("paybill_Month", paybill_Month);
					objectArgs.put("paybill_Year", paybill_Year);
					objectArgs.put("approvalDate", approvalDate);
					objectArgs.put("paybillVO", payBillVO);

					// temp commented
					// Kishan - need to change below method
					int replyCode = new GenerateBillNonGovCalc().insertPaybillNonGovData(objectArgs);
					if (replyCode != 0) {
						logger.info("Reply code from Non Gov is " + replyCode);
						throw new Exception("Non Gov Error Occured..!");
					}
					List<HrPayPayslipNonGovt> payslipNonGovtsList = (ArrayList<HrPayPayslipNonGovt>) objectArgs
							.get("payslipNonGovtsList_Batch");
					objectArgs.remove("payslipNonGovtsList_Batch");
					payslipNonGovtsParentList.add(payslipNonGovtsList);

					payslipNonGovtsList = null;

					// ended
					if (payBillVO != null) {
						billGrossAmt += payBillVO.getGrossAmt();
						billNetAmt += payBillVO.getNetTotal();
					}
					// ended by Ankit Bhatt
					// Added By Amish to Maintain History

					/*
					 * int lIntTotalPksHrPayPaybillEmpCompMpg = (hrEisOtherDtls.size()3);
					 * objectArgs.put("counter", lIntTotalPksHrPayPaybillEmpCompMpg); Long
					 * pkSeqHrPayPaybillEmpCompMpg =
					 * idGen.PKGenerator("HR_PAY_PAYBILL_EMP_COMP_MPG", objectArgs);
					 * objectArgs.remove("counter");
					 */
					long eisEmpId = hrEisOtherDtls.get(cnt).getEisEmpId();
					logger.info("Employee Id is" + eisEmpId);
					List<AllwValCustomVO> allowTypeMstComp = new ArrayList();
					List<DeductionCustomVO> empDeducCompoLst = new ArrayList();
					List empLoanList = null;
					PaybillEmpCompMpg paybillEmpCompMpg = null;
					long paybillEmpCompId = 0;
					String compIdStr = "";
					// Added by vivek for scheme-Deduction mapping
					String headOfAccCode = "";
					long listSize = 0;
					long compoType = 0;

					// For Allowance
					compoType = 2500134;
					if (empAllowCompoMap.containsKey(eisEmpId))
						allowTypeMstComp = (List) (empAllowCompoMap.get(eisEmpId) != null
								? empAllowCompoMap.get(eisEmpId)
								: new ArrayList());
					listSize = allowTypeMstComp.size();
					logger.info("Computational Alloonwace List  is" + listSize);
					for (int i = 0; i < listSize; i++) {
						if (i != listSize - 1)
							compIdStr = compIdStr + allowTypeMstComp.get(i).getAllwID() + ",";
						else
							compIdStr = compIdStr + allowTypeMstComp.get(i).getAllwID();
					}
					/*
					 * if(mapEditableAllowance.containsKey(eisEmpId)) allowTypeMstComp = (List)
					 * (mapEditableAllowance.get(eisEmpId)!=null ?
					 * mapEditableAllowance.get(eisEmpId):new ArrayList()); listSize = 0;
					 * if(allowTypeMstComp != null && !allowTypeMstComp.isEmpty()) listSize =
					 * allowTypeMstComp.size();
					 * logger.info("Editable Alloonwace List  is"+listSize); for(int
					 * i=0;i<listSize;i++) { if(i != listSize-1) compIdStr = compIdStr +
					 * allowTypeMstComp.get(i).getAllwID() +","; else compIdStr = compIdStr +
					 * allowTypeMstComp.get(i).getAllwID(); }
					 */
					if (listSize > 0) {
						paybillEmpCompMpg = new PaybillEmpCompMpg();
						paybillEmpCompId = ++pkSeqHrPayPaybillEmpCompMpg;
						paybillEmpCompMpg.setPaybillEmpCompId(paybillEmpCompId);
						paybillEmpCompMpg.setPaybillHeadMpg(paybillGrpId);
						paybillEmpCompMpg.setPaybillID(payBillVO.getId());
						paybillEmpCompMpg.setCompoType(compoType);

						// added by saurabh for paybill
						/*
						 * if ((compIdStr.charAt(compIdStr.length() - 1)) == ',') compIdStr =
						 * compIdStr.substring(0, compIdStr .length() - 1);
						 */
						paybillEmpCompMpg.setCompoId(compIdStr);

						paybillEmpCompoMpgList.add(paybillEmpCompMpg);
					}
					compIdStr = "";
					headOfAccCode = "";
					// Allowances End
					// Deduction Start
					compoType = 2500135;
					if (empDeducCompoMap.containsKey(eisEmpId))
						empDeducCompoLst = (List) (empDeducCompoMap.get(eisEmpId) != null
								? empDeducCompoMap.get(eisEmpId)
								: new ArrayList());
					listSize = 0;
					if (empDeducCompoLst != null && !empDeducCompoLst.isEmpty())
						listSize = empDeducCompoLst.size();
					logger.info("Computational Deduction List  is" + listSize);
					for (int i = 0; i < listSize; i++) {
						if (empDeducCompoLst.get(i).getDeducId() != dcpsId
								&& empDeducCompoLst.get(i).getDeducId() != dcpsDelayId
								&& empDeducCompoLst.get(i).getDeducId() != dcpsDaId
								&& empDeducCompoLst.get(i).getDeducId() != dcpsPayId) {
							if (i != listSize - 1) {

								compIdStr = compIdStr + empDeducCompoLst.get(i).getDeducId() + ",";
								if (empDedSchemeMap
										.get(eisEmpId + "~" + (empDeducCompoLst.get(i).getDeducId())) != null)
									headOfAccCode = headOfAccCode + empDedSchemeMap
											.get(eisEmpId + "~" + (empDeducCompoLst.get(i).getDeducId())) + ",";

							} else {
								compIdStr = compIdStr + empDeducCompoLst.get(i).getDeducId();
								if (empDedSchemeMap
										.get(eisEmpId + "~" + (empDeducCompoLst.get(i).getDeducId())) != null)
									headOfAccCode = headOfAccCode + empDedSchemeMap
											.get(eisEmpId + "~" + (empDeducCompoLst.get(i).getDeducId()));

							}

						}
					}

					if (hrEisOtherDtls.get(cnt).getDcpsOrGPF().equalsIgnoreCase("Y")) {
						if (compIdStr.endsWith(","))
							compIdStr += dcpsId + "," + dcpsDelayId + "," + dcpsPayId + "," + dcpsDaId;
						else {

							if (!compIdStr.equals("")) {
								compIdStr += "," + dcpsId + "," + dcpsDelayId + "," + dcpsPayId + "," + dcpsDaId;
							} else {
								compIdStr += dcpsId + "," + dcpsDelayId + "," + dcpsPayId + "," + dcpsDaId;
							}
						}

					}

					/*
					 * if (hrEisOtherDtls.get(cnt).getDcpsOrGPF() .equalsIgnoreCase("Y")) { if
					 * (compIdStr.endsWith(",")) compIdStr += dcpsId + "," + dcpsDelayId + "," +
					 * dcpsPayId + "," + dcpsDaId; else compIdStr += "," + dcpsId + "," +
					 * dcpsDelayId + "," + dcpsPayId + "," + dcpsDaId; }
					 */
					/*
					 * if(mapEditableDeduction.containsKey(eisEmpId)) empDeducCompoLst = (List)
					 * (mapEditableDeduction.get(eisEmpId)!=null ?
					 * mapEditableDeduction.get(eisEmpId):new ArrayList()); listSize = 0; listSize =
					 * empDeducCompoLst.size(); logger.info("Editable Deduction List  is"+listSize);
					 * for(int i=0;i<listSize;i++) { if(i != listSize-1) compIdStr = compIdStr +
					 * empDeducCompoLst.get(i).getDeducId() +","; else compIdStr = compIdStr +
					 * empDeducCompoLst.get(i).getDeducId(); }
					 */
					if (listSize > 0) {
						paybillEmpCompMpg = new PaybillEmpCompMpg();
						// paybillEmpCompId =
						// idGen.PKGenerator("HR_PAY_PAYBILL_EMP_COMP_MPG",
						// objectArgs);
						paybillEmpCompId = ++pkSeqHrPayPaybillEmpCompMpg;
						paybillEmpCompMpg.setPaybillEmpCompId(paybillEmpCompId);
						paybillEmpCompMpg.setPaybillHeadMpg(paybillGrpId);
						paybillEmpCompMpg.setPaybillID(payBillVO.getId());
						paybillEmpCompMpg.setCompoType(compoType);
						logger.info("compIdStr **********************  is" + compIdStr);
						// added by saurabh for paybill generation
						/*
						 * if (compIdStr != null && compIdStr.length() > 0 &&
						 * (compIdStr.charAt(compIdStr.length() - 1)) == ',') compIdStr =
						 * compIdStr.substring(0, compIdStr .length() - 1); if (compIdStr.endsWith(","))
						 * compIdStr = compIdStr.substring(0, compIdStr .length() - 1);
						 */
						paybillEmpCompMpg.setCompoId(compIdStr);
						// paybillEmpCompMpg.setHeadOfAccCode(headOfAccCode);
						logger.info("headOfAccCode=" + headOfAccCode);
						paybillEmpCompoMpgList.add(paybillEmpCompMpg);
					}
					compIdStr = "";
					// Deduction End
					// Loans / Advances Start
					compoType = 2500137;
					logger.info("Map for Loan is " + empLoanMap);
					if (empLoanMap.containsKey(eisEmpId))
						empLoanList = empLoanMap.containsKey(eisEmpId) ? empLoanMap.get(eisEmpId) : new ArrayList();
					listSize = 0;
					if (empLoanList != null && !empLoanList.isEmpty())
						listSize = empLoanList.size();
					logger.info("Loan List  is" + listSize);
					for (int i = 0; i < listSize; i++) {
						logger.info("Inside Loan Part" + i);
						EmpPaybillLoanVO empPaybillLoanVO = (EmpPaybillLoanVO) empLoanList.get(i);
						if (i != listSize - 1)
							compIdStr = compIdStr + empPaybillLoanVO.getLoanAdvId() + ",";
						else
							compIdStr = compIdStr + empPaybillLoanVO.getLoanAdvId();
					}
					if (listSize > 0) {
						paybillEmpCompMpg = new PaybillEmpCompMpg();
						paybillEmpCompId = ++pkSeqHrPayPaybillEmpCompMpg;
						paybillEmpCompMpg.setPaybillEmpCompId(paybillEmpCompId);
						paybillEmpCompMpg.setPaybillHeadMpg(paybillGrpId);
						paybillEmpCompMpg.setPaybillID(payBillVO.getId());
						paybillEmpCompMpg.setCompoType(compoType);
						// added by saurabh for paybill generation
						/*
						 * if ((compIdStr.charAt(compIdStr.length() - 1)) == ',') compIdStr =
						 * compIdStr.substring(0, compIdStr .length() - 1);
						 */
						paybillEmpCompMpg.setCompoId(compIdStr);
						paybillEmpCompoMpgList.add(paybillEmpCompMpg);
						paybillEmpCompMpg = null;
					}
					// Loans / Advances End
					// Ended By Amish
				}
				resultMap = null;
				payBillVO = null;
				// Post List - Loop ends

				// for 10-20 days issue by manoj
			} // added by ravysh for if block
		}
		objectArgs.put("pkSeqHrPayPaybill", pkSeqHrPayPaybill);
		hrEisOtherDtls = null;

		objectArgs.put("payBillVOParentList_Batch", payBillVOParentList);
		objectArgs.put("paybillEmpCompoMpgList", paybillEmpCompoMpgList);
		objectArgs.put("hrPayPaybillLoanParentDtlsList_Batch", hrPayPaybillLoanParentDtlsList);
		objectArgs.put("payslipNonGovtsParentList_Batch", payslipNonGovtsParentList);

		paybill_Month++;

		if (paybill_Month == 13) {
			paybill_Month = 1;
			paybill_Year++;
		}
		allowEdpMap = null;
		allowTypeMstList = null;
		checkNonGovPayslipList = null;
		deducTypeMstList = null;
		deducEdpMap = null;
		edpListEditable = null;
		empAllowCompoMap = null;
		empBrokenList = null;
		empBrokenMap = null;
		empDCPSValList = null;
		empDCPSValMap = null;
		empDeducCompoMap = null;
		empGisList = null;
		empGisMap = null;
		empIntRecoverList = null;
		empLoanLst = null;
		empLoanMap = null;
		empNonGovList = null;
		empNonGovMap = null;
		empOfficeMap = null;
		empPrincRecoverList = null;
		empQtrRentList = null;
		isPTMappedMap = null;
		isPTMappedList = null;
		loanIntRecoverMap = null;
		loanPrincRecoverMap = null;
		nonGovPayslipMap = null;
		qtrMap = null;
		sgvcFinYearMst = null;
		// Kishan
		billTypeCmb = null;
		loginMap = null;
		empIdsStr = null;
		postIdsStr = null;
		orgEmpIdsStr = null;
		fest_adv_id = null;
		food_adv_id = null;
		gpf_adv_id = null;
		pay_recovery_id = null;
		sysdate = null;
		serv = null;
		idGen = null;
		orgUserMst = null;
		cmnDatabaseMst = null;
		cmnLocationMst = null;
		orgPostMst = null;
		empAllwMapDAOImpl = null;
		payDao = null;
		approvalDate = null;
		payBillVOParentList = null;
		hrPayPaybillLoanParentDtlsList = null;
		payslipNonGovtsParentList = null;

		objectArgs.put("paybill_Month", paybill_Month);
		objectArgs.put("paybill_Year", paybill_Year);
		objectArgs.put("paybillGrpId", paybillGrpId);
		objectArgs.put("paybillGenerated", paybillGenerated);
		objectArgs.put("billGrossAmt", billGrossAmt);
		objectArgs.put("billNetAmt", billNetAmt);

		return objectArgs;
	}

	public Map<Long, List> generateEmpLoanVOs(List empLoanList) {
		Map<Long, List> loanMap = new HashMap<Long, List>();
		long prevEmpId = 0;
		List revisedEmpLoanList = new ArrayList();
		if (empLoanList != null && empLoanList.size() > 0)
			for (int i = 0; i < empLoanList.size(); i++) {
				Object[] row = (Object[]) empLoanList.get(i);
				if (row != null) {
					long empId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					if (prevEmpId == 0)
						prevEmpId = empId;
					if (prevEmpId != empId) {
						logger.info("Going to put in Map");
						logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						logger.info("revisedEmpLoanList is " + revisedEmpLoanList);

						loanMap.put(prevEmpId, revisedEmpLoanList);
						revisedEmpLoanList = new ArrayList();
						prevEmpId = empId;
					}
					if (i == (empLoanList.size() - 1)) {
						loanMap.put(empId, revisedEmpLoanList);
					}
					EmpPaybillLoanVO paybillLoanVO = new EmpPaybillLoanVO();
					paybillLoanVO.setEmpLoanId(row[0] != null ? Long.valueOf(row[0].toString()) : 0);
					paybillLoanVO.setEmpId(row[1] != null ? Long.valueOf(row[1].toString()) : 0);
					paybillLoanVO.setLoanAdvId(row[2] != null ? Long.valueOf(row[2].toString()) : 0);
					paybillLoanVO.setLoanPrinAmt(row[3] != null ? Long.valueOf(row[3].toString()) : 0);
					paybillLoanVO.setLoanInterestAmt(row[4] != null ? Long.valueOf(row[4].toString()) : 0);
					paybillLoanVO.setLoanPrinInstNo(row[5] != null ? Long.valueOf(row[5].toString()) : 0);
					paybillLoanVO.setLoanIntInstNo(row[6] != null ? Long.valueOf(row[6].toString()) : 0);
					paybillLoanVO.setLoanAccountNo(row[8] != null ? row[8].toString() : "");
					paybillLoanVO.setLoanDate(row[9] != null ? (Date) row[9] : null);
					paybillLoanVO.setLoanIntEmiAmt(row[10] != null ? Long.valueOf(row[10].toString()) : 0);
					paybillLoanVO.setLoanPrinEmiAmt(row[11] != null ? Long.valueOf(row[11].toString()) : 0);
					paybillLoanVO.setLoanSancOrderNo(row[12] != null ? String.valueOf(row[12].toString()) : "");
					paybillLoanVO.setLoanActivateFlag(row[13] != null ? Integer.valueOf(row[13].toString()) : 0);
					paybillLoanVO.setLoanOddinstno(row[14] != null ? Long.valueOf(row[14].toString()) : 0);
					paybillLoanVO.setLoanOddinstAmt(row[15] != null ? Long.valueOf(row[15].toString()) : 0);
					paybillLoanVO.setVoucherNo(row[16] != null ? row[16].toString() : "");
					paybillLoanVO.setVoucherDate(row[17] != null ? (Date) row[17] : null);
					paybillLoanVO.setLoanSancOrderdate(row[18] != null ? (Date) row[18] : null);
					paybillLoanVO.setCompoType(row[19] != null ? Long.valueOf(row[19].toString()) : 0);
					paybillLoanVO.setEdpCode(row[20] != null ? row[20].toString() : "");
					paybillLoanVO.setCompoCode(row[21] != null ? row[21].toString() : "");
					paybillLoanVO
							.setMulLoanRecoveryMode(row[22] != null ? Integer.valueOf(String.valueOf(row[22])) : 0);
					paybillLoanVO.setMulLoanInstRecvd(row[23] != null ? Integer.valueOf(String.valueOf(row[23])) : 1);
					paybillLoanVO.setMulLoanAmtRecvd(row[24] != null ? Long.valueOf(String.valueOf(row[24])) : 0);
					paybillLoanVO.setMulLoanRecRemarks(row[25] != null ? row[25].toString() : "");

					logger.info("Setting loan date to " + paybillLoanVO.getLoanDate());
					logger.info("DB Loan Date " + row[9]);
					revisedEmpLoanList.add(paybillLoanVO);

				}
			}
		logger.info("Before returning from generateLoanVO Map is " + loanMap);
		return loanMap;
	}

	public Map<Long, LoanRecoverVO> generateLoanRecoverMap(List loanRecoverList) {
		Map<Long, LoanRecoverVO> loanPrincRecoverMap = new HashMap();
		if (loanRecoverList != null && loanRecoverList.size() > 0)
			for (int i = 0; i < loanRecoverList.size(); i++) {
				Object[] row = (Object[]) loanRecoverList.get(i);
				if (row != null) {
					long empLoanId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
					long recoeveredAmount = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					int loanRecoveredInst = row[2] != null ? Integer.valueOf(row[2].toString()) : 0;
					int oddInstInstallment = row[3] != null ? Integer.valueOf(row[3].toString()) : 0;
					long oddInstAmount = row[4] != null ? Long.valueOf(row[4].toString()) : 0;

					if (empLoanId != 0) {
						LoanRecoverVO loanPrincRecoverVO = new LoanRecoverVO();
						loanPrincRecoverVO.setEmpLoanId(empLoanId);
						loanPrincRecoverVO.setRecoveredAmount(recoeveredAmount);
						loanPrincRecoverVO.setRecoveredInst(loanRecoveredInst);
						loanPrincRecoverVO.setOddInstAmount(oddInstInstallment);
						loanPrincRecoverVO.setOddInstAmount(oddInstAmount);
						loanPrincRecoverMap.put(empLoanId, loanPrincRecoverVO);
					}
				}
			}
		return loanPrincRecoverMap;
	}

	public Map generateDCPSMap(List empDCPSValList) {

		Map<Long, Map> empDCPSMap = new HashMap<Long, Map>();
		long prevEmpId = 0;
		Map revisedEmpDCPSList = new HashMap();
		if (empDCPSValList != null && empDCPSValList.size() > 0) {
			Object[] row = null;
			for (int i = 0; i < empDCPSValList.size(); i++) {
				row = (Object[]) empDCPSValList.get(i);
				if (row != null) {
					long empId = row[2] != null ? Long.valueOf(row[2].toString()) : 0;
					double contributionAmt = row[0] != null ? Double.valueOf(row[0].toString()) : 0;
					long contriType = row[1] != null ? Long.valueOf(row[1].toString()) : 0;

					if (prevEmpId == 0)
						prevEmpId = empId;
					if (prevEmpId != empId) {
						logger.info("Going to put in Map");
						logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						logger.info("revisedEmpDCPSList is " + revisedEmpDCPSList);

						empDCPSMap.put(prevEmpId, revisedEmpDCPSList);
						revisedEmpDCPSList = new HashMap();
						prevEmpId = empId;
					}
					if (i == (empDCPSValList.size() - 1)) {
						empDCPSMap.put(empId, revisedEmpDCPSList);
					}
					if (!revisedEmpDCPSList.containsKey(contriType))
						revisedEmpDCPSList.put(contriType, contributionAmt);

				}
			}
		}
		return empDCPSMap;
	}

	public Map generateEmpAllowCompoMap(List empAllowCompoList) {
		Map empAllowCompoMap = new HashMap();
		List<AllwValCustomVO> empAllwCompoLst = new ArrayList();
		long prevEmpId = 0;
		if (empAllowCompoList != null && empAllowCompoList.size() > 0) {
			int size = empAllowCompoList.size();
			Object[] row = null;
			for (int i = 0; i < size; i++) {
				row = (Object[]) empAllowCompoList.get(i);
				if (row != null) {
					long empId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
					if (prevEmpId == 0)
						prevEmpId = empId;
					if (prevEmpId != empId) {
						logger.info("Going to put in Map");
						logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						logger.info("empAllwCompoLst is " + empAllwCompoLst);

						empAllowCompoMap.put(prevEmpId, empAllwCompoLst);
						empAllwCompoLst = new ArrayList();
						prevEmpId = empId;
					}
					if (i == (empAllowCompoList.size() - 1)) {
						empAllowCompoMap.put(empId, empAllwCompoLst);
					}
					long allowId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					String allowDesc = row[2] != null ? String.valueOf(row[2].toString()) : "";
					long allwDedId = row[3] != null ? Long.valueOf(row[3].toString()) : 0;
					AllwValCustomVO allwCustomVO = new AllwValCustomVO();
					if (empId != 0 && allowId != 0) {
						allwCustomVO.setAllwID(allowId);
						allwCustomVO.setAllowDesc(allowDesc);
						allwCustomVO.setAllwDedId(allwDedId);
						empAllwCompoLst.add(allwCustomVO);
					}
					allwCustomVO = null;
				}
			}
		}
		return empAllowCompoMap;
	}

	/*
	 * public Map<Long, Map<Integer, EmpNonGovVO>> generateNonGovMap(List
	 * empNonGovList) { Map<Long, Map<Integer, EmpNonGovVO>> empNonGovMap = new
	 * HashMap(); Map<Integer, EmpNonGovVO> empNonGovLst = new HashMap<Integer,
	 * EmpNonGovVO>(); long prevEmpId = 0; if (empNonGovList != null &&
	 * empNonGovList.size() > 0) { Object[] row = null; int size =
	 * empNonGovList.size(); for (int i = 0; i < size; i++) { row = (Object[])
	 * empNonGovList.get(i); if (row != null) { long empId = row[1] != null ?
	 * Long.valueOf(row[1].toString()) : 0; if (prevEmpId == 0) prevEmpId = empId;
	 * if (prevEmpId != empId) { logger.info("Going to put in Map");
	 * logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
	 * logger.info("emp Non gov list is " + empNonGovLst);
	 * 
	 * empNonGovMap.put(prevEmpId, empNonGovLst); empNonGovLst = new
	 * HashMap<Integer, EmpNonGovVO>(); prevEmpId = empId; } if (i ==
	 * (empNonGovList.size() - 1)) { empNonGovMap.put(empId, empNonGovLst); } int
	 * deducCode = row[2] != null ? Integer.valueOf(row[2].toString()) : 0; long
	 * amount = row[3] != null ? Long.valueOf(row[3].toString()) : 0; EmpNonGovVO
	 * empNonGovVO = new EmpNonGovVO(); if (empId != 0 && deducCode != 0) {
	 * empNonGovVO.setEmpId(empId); empNonGovVO.setDeducCode(deducCode);
	 * empNonGovVO.setAmount(amount); if (!empNonGovLst.containsKey(deducCode))
	 * empNonGovLst.put(deducCode, empNonGovVO);
	 * 
	 * } empNonGovVO = null; } } } return empNonGovMap; }
	 */

	// Added by Roshan
	public Map<Long, Map<Integer, EmpNonGovVO>> generateNonGovMap(List empNonGovList) {
		Map<Long, Map<Integer, EmpNonGovVO>> empNonGovMap = new HashMap();
		Map<Integer, EmpNonGovVO> empNonGovLst = new HashMap<Integer, EmpNonGovVO>();
		long prevEmpId = 0;
		if (empNonGovList != null && empNonGovList.size() > 0) {
			Object[] row = null;
			int size = empNonGovList.size();
			for (int i = 0; i < size; i++) {
				row = (Object[]) empNonGovList.get(i);
				if (row != null) {
					long empId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					if (prevEmpId == 0)
						prevEmpId = empId;
					if (prevEmpId != empId) {
						logger.info("Going to put in Map");
						logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						logger.info("emp Non gov list is " + empNonGovLst);

						empNonGovMap.put(prevEmpId, empNonGovLst);
						empNonGovLst = new HashMap<Integer, EmpNonGovVO>();
						prevEmpId = empId;
					}
					if (i == (empNonGovList.size() - 1)) {
						empNonGovMap.put(empId, empNonGovLst);
					}
					int deducCode = row[2] != null ? Integer.valueOf(row[2].toString()) : 0;
					long amount = row[3] != null ? Long.valueOf(row[3].toString()) : 0;
					// String nonGovType=row[4].toString(); //commented by
					// poonam for ngr amount
					// Added by roshan for NGR Paybill//
					// logger.info("emp nonGovType is " +
					// nonGovType);//commented by poonam
					EmpNonGovVO empNonGovVO = new EmpNonGovVO();
					if (empId != 0 && deducCode != 0) {
						empNonGovVO.setEmpId(empId);
						empNonGovVO.setDeducCode(deducCode);
						empNonGovVO.setAmount(amount);
						// empNonGovVO.setOtherRecType(nonGovType);//commented
						// by poonam
						if (!empNonGovLst.containsKey(deducCode))
							empNonGovLst.put(deducCode, empNonGovVO);

					}
					empNonGovVO = null;
				}
			}
		}
		return empNonGovMap;
	}

	public Map<Long, Map<Integer, Long>> generatePayslipNonGovMap(List empNonGovList) {
		Map<Long, Map<Integer, Long>> empNonGovMap = new HashMap();
		Map<Integer, Long> empPayslipNonGovMap = new HashMap<Integer, Long>();
		long prevEmpId = 0;
		if (empNonGovList != null && empNonGovList.size() > 0) {
			int size = empNonGovList.size();
			Object[] row = null;
			for (int i = 0; i < size; i++) {
				row = (Object[]) empNonGovList.get(i);
				if (row != null) {
					long empId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					if (prevEmpId == 0)
						prevEmpId = empId;
					if (prevEmpId != empId) {
						logger.info("Going to put in Map");
						logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						logger.info("emp Non gov list is " + empPayslipNonGovMap);

						empNonGovMap.put(prevEmpId, empPayslipNonGovMap);
						empPayslipNonGovMap = new HashMap<Integer, Long>();
						prevEmpId = empId;
					}
					if (i == (empNonGovList.size() - 1)) {
						empNonGovMap.put(empId, empPayslipNonGovMap);
					}
					long nonGovId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
					int deducCode = row[2] != null ? Integer.valueOf(row[2].toString()) : 0;
					if (empId != 0 && deducCode != 0) {
						if (!empPayslipNonGovMap.containsKey(deducCode))
							empPayslipNonGovMap.put(deducCode, nonGovId);
					}
				}

			}
		}
		return empNonGovMap;
	}

	public Map<Long, Long> generateNonGovPayslipMap(List nonGovPayslipList) {
		Map<Long, Long> nonGovPayslipMap = new HashMap<Long, Long>();
		if (nonGovPayslipList != null && nonGovPayslipList.size() > 0)
			for (int i = 0; i < nonGovPayslipList.size(); i++) {
				Object[] row = (Object[]) nonGovPayslipList.get(i);
				if (row != null) {
					long empId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
					long nonGovtId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					if (!nonGovPayslipMap.containsKey(empId))
						nonGovPayslipMap.put(empId, nonGovtId);
				}
			}
		return nonGovPayslipMap;
	}

	public Map<Long, Long> generatePTMap(List PTList) {
		Map<Long, Long> PTMap = new HashMap<Long, Long>();
		if (PTList != null && PTList.size() > 0) {
			Object[] row = null;
			for (int i = 0; i < PTList.size(); i++) {
				row = (Object[]) PTList.get(i);
				if (row != null) {
					long duducId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
					long empId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					if (!PTMap.containsKey(empId))
						PTMap.put(empId, duducId);
				}
			}
		}
		return PTMap;
	}

	public Map<Long, List<EmpBrokenPeriodVO>> generateBrokenMap(List empBrokenList) {
		Map<Long, List<EmpBrokenPeriodVO>> brokenMap = new HashMap<Long, List<EmpBrokenPeriodVO>>();
		List<EmpBrokenPeriodVO> brokenList = new ArrayList<EmpBrokenPeriodVO>();
		long prevEmpId = 0;
		if (empBrokenList != null && empBrokenList.size() > 0) {
			int size = empBrokenList.size();
			Object[] row = null;
			for (int i = 0; i < size; i++) {
				row = (Object[]) empBrokenList.get(i);
				if (row != null) {
					long empId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;

					if (prevEmpId == 0)
						prevEmpId = empId;
					if (prevEmpId != empId) {
						logger.info("Going to put in Map");
						logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						logger.info("emp Non gov list is " + brokenList);

						if (!brokenMap.containsKey(prevEmpId))
							brokenMap.put(prevEmpId, brokenList);
						brokenList = new ArrayList();
						prevEmpId = empId;
					}
					if (i == (empBrokenList.size() - 1)) {
						if (!brokenMap.containsKey(empId))
							brokenMap.put(empId, brokenList);
					}

					long brokenPeriodId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					long basicAmt = row[2] != null ? Long.valueOf(row[2].toString()) : 0;
					if (empId != 0 && brokenPeriodId != 0) {
						EmpBrokenPeriodVO brokenVO = new EmpBrokenPeriodVO();
						brokenVO.setEmpId(empId);
						brokenVO.setBrokenPeriodId(brokenPeriodId);
						brokenVO.setBasicAmount(basicAmt);
						brokenList.add(brokenVO);
						brokenVO = null;
					}
				}
			}
		}
		return brokenMap;
	}

	public Map generateEmpDeducCompoMap(List empDeducCompoList) {
		Map empDeducCompoMap = new HashMap();
		List<DeductionCustomVO> empDeducCompoLst = new ArrayList();
		long prevEmpId = 0;
		if (empDeducCompoList != null && empDeducCompoList.size() > 0) {
			Object[] row = null;
			for (int i = 0; i < empDeducCompoList.size(); i++) {
				row = (Object[]) empDeducCompoList.get(i);
				if (row != null) {
					long empId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
					if (prevEmpId == 0)
						prevEmpId = empId;
					if (prevEmpId != empId) {
						logger.info("Going to put in Map");
						logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						logger.info("deducAllwCompoLst is " + empDeducCompoLst);

						empDeducCompoMap.put(prevEmpId, empDeducCompoLst);
						empDeducCompoLst = new ArrayList();
						prevEmpId = empId;
					}
					if (i == (empDeducCompoList.size() - 1)) {
						empDeducCompoMap.put(empId, empDeducCompoLst);
					}
					long deducId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					String deducDesc = row[2] != null ? String.valueOf(row[2].toString()) : "";
					long allwDedId = row[3] != null ? Long.valueOf(row[3].toString()) : 0;
					DeductionCustomVO deducCustomVO = new DeductionCustomVO();
					if (empId != 0 && deducId != 0) {
						deducCustomVO.setDeducId(deducId);
						deducCustomVO.setDeducDesc(deducDesc);
						deducCustomVO.setAllwDedId(allwDedId);
						empDeducCompoLst.add(deducCustomVO);
					}
					deducCustomVO = null;
				}
			}
		}
		return empDeducCompoMap;
	}

	/**
	 * Method will return days of post for given User post Rlt object.
	 * 
	 * @param OrgUserpostRlt
	 * @return long - Days of post
	 */
	public long daysOfPost(Map OrgUserpostRlt) {
		int monthGiven = OrgUserpostRlt.get("month") != null ? Integer.parseInt(OrgUserpostRlt.get("month").toString())
				: -1;
		int yearGiven = OrgUserpostRlt.get("year") != null ? Integer.parseInt(OrgUserpostRlt.get("year").toString())
				: -1;
		OrgUserpostRlt orgUPRlt = (OrgUserpostRlt) (OrgUserpostRlt.get("orgUPRlt") != null
				? OrgUserpostRlt.get("orgUPRlt")
				: null);

		long daysOfPost = 0;
		int startDays = 0;
		int startMonth = 0;
		int startYear = 0;

		int endMonth = 0;
		int endYear = 0;
		int endDays = 0;

		long maxDay = 0;
		long orgUPEndMilli = 0;
		GenerateBillService billService = new GenerateBillService();

		Calendar calGiven = Calendar.getInstance();
		calGiven.set(Calendar.YEAR, yearGiven);
		calGiven.set(Calendar.MONTH, (monthGiven - 1));
		calGiven.set(Calendar.DAY_OF_MONTH, 1);
		maxDay = calGiven.getActualMaximum(5);
		Date givenDate = calGiven.getTime();

		if (orgUPRlt != null) {
			if (orgUPRlt.getEndDate() != null) {
				endDays = billService.dayofDate(orgUPRlt.getEndDate());
				endMonth = billService.monthofDate((orgUPRlt.getEndDate()));
				endYear = billService.yearofDate((orgUPRlt.getEndDate()));

				logger.info("the value of endDays " + endDays + " and startDays is " + startDays);
				if (endMonth == monthGiven && endYear == yearGiven) // End date
				// in
				// current
				// month and
				// year.
				{
					orgUPEndMilli = orgUPRlt.getEndDate().getTime();

					// days = (orgUPEndMilli - orgUPStartMilli)/MILLIS_IN_DAY;
					if (startMonth == monthGiven && startYear == yearGiven) // Start
					// and
					// end
					// date
					// both
					// are
					// in
					// the
					// same
					// month
					// and
					// year.
					{
						daysOfPost = endDays - (startDays - 1);
						logger.info("Total Days:-" + daysOfPost);
					} else
					// start date is not in current month but end date is in
					// current month.
					{
						daysOfPost = endDays;
						logger.info("Total No. Days:-" + daysOfPost);
					}
				} else
				// End date is not in current month and year.
				{
					if (startMonth == monthGiven && startYear == yearGiven) // if
					// startDate
					// is
					// in
					// current
					// month.
					{
						daysOfPost = maxDay - (startDays - 1);
					} else if (givenDate.after(orgUPRlt.getStartDate()) && givenDate.before(orgUPRlt.getEndDate())) // if
					// whole
					// current
					// month
					// is
					// between
					// the
					// start
					// and
					// end
					// date.
					{
						daysOfPost = maxDay;
						logger.info("when start date is less than current date " + daysOfPost);
					} else
					// if whole current month is not between the start and end
					// date.
					{
						logger.info("no of post is not set");
					}
				}
			} else
			// if End Date is null.
			{

				if (startMonth == monthGiven && startYear == yearGiven) // if
				// startDate
				// is in
				// current
				// month.
				{
					daysOfPost = maxDay - (startDays - 1);
				} else if (givenDate.after(orgUPRlt.getStartDate())) // if
				// startdate
				// is
				// less
				// then
				// current
				// date.
				{
					daysOfPost = maxDay;
				} else
					// if start date is greater then current date.
					logger.info("from end date is null .no of post is not set");
			}

		}
		return daysOfPost;
	}

	// 20 jan 2012
	public Map<Long, List<AllwValCustomVO>> generatemapForAllEditableAllowance(List inputlist) {
		Map<Long, List<AllwValCustomVO>> returnMap = new HashMap<Long, List<AllwValCustomVO>>();
		if (inputlist != null && inputlist.size() > 0) {
			int size = inputlist.size();
			Object[] row = null;
			for (int i = 0; i < size; i++) {
				row = (Object[]) inputlist.get(i);
				Long empId = row[0] != null ? Long.valueOf(row[1].toString()) : 0;
				if (row != null) {
					// long empId =
					// row[0]!=null?Long.valueOf(row[0].toString()):0;
					if (returnMap.get(empId) == null)// if new employee
					// enconutered
					{
						List<AllwValCustomVO> tempList = new ArrayList<AllwValCustomVO>();
						AllwValCustomVO allwValCustomVO = new AllwValCustomVO();
						allwValCustomVO.setAllwID(Long.valueOf(row[2].toString()));
						allwValCustomVO.setAllowanceVal(Long.valueOf(row[3].toString()));
						tempList.add(allwValCustomVO);
						allwValCustomVO = null;
						returnMap.put(empId, tempList);
						tempList = null;
					} else {
						List<AllwValCustomVO> tempList = returnMap.get(empId);
						AllwValCustomVO allwValCustomVO = new AllwValCustomVO();
						allwValCustomVO.setAllwID(Long.valueOf(row[2].toString()));
						allwValCustomVO.setAllowanceVal(Long.valueOf(row[3].toString()));
						tempList.add(allwValCustomVO);
						allwValCustomVO = null;
						returnMap.put(empId, tempList);
						tempList = null;
					}
				}
			}
		}
		return returnMap;
	}

	public Map<Long, List<DeductionCustomVO>> generatemapForAllEditableDeduction(List inputlist) {
		Map<Long, List<DeductionCustomVO>> returnMap = new HashMap<Long, List<DeductionCustomVO>>();
		if (inputlist != null && inputlist.size() > 0) {
			int size = inputlist.size();
			Object[] row = null;
			Long empId = 0L;
			for (int i = 0; i < size; i++) {
				row = (Object[]) inputlist.get(i);
				if (row != null) {
					empId = row[0] != null ? Long.valueOf(row[1].toString()) : 0;
					logger.info(" - r0 " + row[0].toString() + " r1 " + row[1].toString() + " r2 " + row[2].toString()
							+ " r3 " + row[3].toString());
					/*
					 * //System.out.println("ITERATION "+i+" - r0 "+row[0].toString (
					 * )+" r1 "+row[1].toString()+" r2 "+row[2].toString()+" r3 "
					 * +row[3].toString()); //System.out.println(Long.parseLong(row
					 * [1].toString())+" hashkey " +Long.valueOf((row[1].toString()).hashCode()));
					 */
					if (returnMap.get(empId) == null)// if new employee
					// enconutered
					{
						logger.info(" -- new list created for " + empId);
						// //System.out.println(" -- new list created for "+empId);
						List<DeductionCustomVO> tempList = new ArrayList<DeductionCustomVO>();
						DeductionCustomVO deducValCustomVO = new DeductionCustomVO();
						deducValCustomVO.setDeducId(Long.valueOf(row[2].toString()));
						deducValCustomVO.setDeductionVal(Double.valueOf(row[3].toString()));
						tempList.add(deducValCustomVO);
						deducValCustomVO = null;
						/*
						 * //System.out.println(" --- new list created for "+deducValCustomVO
						 * .getDeductionVal()); //System.out.println("r0 "+row[0] .toString()+" r1 "+row
						 * [1].toString()+" r2 "+row[2].toString
						 * ()+" r3 "+row[3].toString());//System.out.println(
						 * "--------------------------------------------------entered into list" );
						 */
						returnMap.put(empId, tempList);
						tempList = null;
					} else {
						logger.info(" -- existing list used for " + empId);
						logger.info(" -- existing list used for " + empId);
						List<DeductionCustomVO> tempList = returnMap.get(empId);
						DeductionCustomVO deducValCustomVO = new DeductionCustomVO();
						deducValCustomVO.setDeducId(Long.valueOf(row[2].toString()));
						deducValCustomVO.setDeductionVal(Double.valueOf(row[3].toString()));
						tempList.add(deducValCustomVO);
						deducValCustomVO = null;
						/*
						 * //System.out.println(" --- existing list used for "+deducValCustomVO
						 * .getDeductionVal()); //System.out.println("r0 "+row[0] .toString()+" r1 "+row
						 * [1].toString()+" r2 "+row[2].toString
						 * ()+" r3 "+row[3].toString());//System.out.println(
						 * "--------------------------------------------------entered existing into list"
						 * );
						 */returnMap.put(empId, tempList);
						tempList = null;
					}
					/*
					 * //System.out.println(" return map "+returnMap.toString());
					 */
				}
			}
		}
		/*
		 * //System.out.
		 * println("**************************************77101910450**************************************\n "
		 * + returnMap.get(Long.valueOf("77101910450")));//System.out.println(
		 * "**************************************77101910439**************************************\n "
		 * + returnMap.get(Long.valueOf("77101910439")));//System.out.println(
		 * "**************************************77101910445**************************************\n "
		 * + returnMap.get(Long.valueOf("77101910445")));//System.out.println(
		 * "**************************************771019104244**************************************\n "
		 * + returnMap.get(Long.valueOf("771019104244")));//System.out.println(
		 * "**************************************77101910484**************************************\n "
		 * + returnMap.get(Long.valueOf("77101910484")));//System.out.println(
		 * "**************************************771019104226**************************************\n "
		 * + returnMap.get(Long.valueOf("771019104226")));//System.out.println(
		 * "**************************************771019104195**************************************\n "
		 * + returnMap.get(Long.valueOf("771019104195")));
		 */
		return returnMap;
	}

	// 20 jan 2012 end
}