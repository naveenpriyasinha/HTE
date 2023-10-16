package com.tcs.sgv.dcps.service;

import com.tcs.sgv.allowance.service.SalaryRules;
import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.BrokenPeriodDAO;
import com.tcs.sgv.dcps.dao.BrokenPeriodDAOImpl;
import com.tcs.sgv.dcps.dao.LedgerReportDAOImpl;
import com.tcs.sgv.dcps.valueobject.BrokenPeriodPayCustomVO;
import com.tcs.sgv.dcps.valueobject.MstBrokenPeriodPay;
import com.tcs.sgv.dcps.valueobject.RltBrokenPeriodAllow;
import com.tcs.sgv.dcps.valueobject.RltBrokenPeriodDeduc;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.HrPayOfficePostMpgDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentMasterDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupMstDAOImpl;
import com.tcs.sgv.eis.dao.PayComponentRuleGroupParamRltDAOImpl;
import com.tcs.sgv.eis.service.PayrollCalculationServiceImpl;
import com.tcs.sgv.eis.valueobject.AllwValCustomVO;
import com.tcs.sgv.eis.valueobject.DeductionCustomVO;
import com.tcs.sgv.eis.valueobject.EmpPaybillVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayAllowDedMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEmpSalaryTxn;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpMst;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpParamRlt;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.payroll.util.PayrollConstants;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BrokenPeriodPayServiceImpl extends ServiceImpl {
	private final Log logger = LogFactory.getLog(getClass());
	private String gStrPostId = null;
	private String gStrUserId = null;
	private String gStrLocale = null;
	private Locale gLclLocale = null;
	private Long gLngLangId = null;
	private Long gLngDBId = null;
	private Date gDtCurDate = null;
	private HttpServletRequest request = null;
	private ServiceLocator serv = null;
	private HttpSession session = null;
	Long gLngPostId = null;
	Long gLngUserId = null;
	Date gDtCurrDt = null;
	String gStrLocationCode = null;
	static HashMap sMapUserLoc = new HashMap();
	String gStrUserLocation = null;
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");

	private void setSessionInfo(Map inputMap) {
		try {
			this.request = ((HttpServletRequest) inputMap.get("requestObj"));
			this.session = this.request.getSession();
			this.serv = ((ServiceLocator) inputMap.get("serviceLocator"));
			this.gLclLocale = new Locale(SessionHelper.getLocale(this.request));
			this.gStrLocale = SessionHelper.getLocale(this.request);
			this.gLngLangId = SessionHelper.getLangId(inputMap);
			this.gLngPostId = SessionHelper.getPostId(inputMap);
			this.gStrPostId = this.gLngPostId.toString();
			this.gLngUserId = SessionHelper.getUserId(inputMap);
			this.gStrUserId = this.gLngUserId.toString();
			this.gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			this.gLngDBId = SessionHelper.getDbId(inputMap);
			this.gDtCurDate = SessionHelper.getCurDate();
		} catch (Exception localException) {
		}
	}

	public ResultObject searchEmployee(Map inputMap) throws Exception {
		ResultObject objRes = new ResultObject(0);

		HrEisEmpMst lObjHrEisEmpVO = null;
		Long lLongYearId = null;
		Long lLongMonthId = null;
		int generated = 0;
		try {
			setSessionInfo(inputMap);
			String lStrSearchName = StringUtility.getParameter("txtSearchName", this.request).toUpperCase();

			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			BrokenPeriodDAO lObjBrokenPeriodDAO = new BrokenPeriodDAOImpl(null, this.serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());

			List lLstYears = lObjDcpsCommonDAO.getFinyears();

			List lLstMonths = lObjDcpsCommonDAO.getMonths();

			inputMap.put("YEARS", lLstYears);
			inputMap.put("MONTHS", lLstMonths);

			String lStrYear = StringUtility.getParameter("yearId", this.request);
			String lStrMonth = StringUtility.getParameter("monthId", this.request);
			if (!lStrYear.equals("")) {
				lLongYearId = Long.valueOf(lStrYear);
			}
			if (!lStrMonth.equals("")) {
				lLongMonthId = Long.valueOf(lStrMonth);
			}
			inputMap.put("yearId", lStrYear);
			inputMap.put("monthId", lStrMonth);
			inputMap.put("yearGiven",
					Integer.valueOf(lObjBrokenPeriodDAO.getyearfromFinYear(lStrYear, Integer.parseInt(lStrMonth))));
			inputMap.put("monthGiven", lStrMonth);
			if (lStrSearchName != "") {
				List lListReasonsForBrokenPeriod = IFMSCommonServiceImpl.getLookupValues("ReasonsForBrokenPeriod",
						SessionHelper.getLangId(inputMap), inputMap);
				inputMap.put("lListReasonsForBrokenPeriod", lListReasonsForBrokenPeriod);

				List lLstSinglEmployee = lObjBrokenPeriodDAO.SearchEmployeeWithName(lStrSearchName, locId);
				if (lLstSinglEmployee == null) {
					inputMap.put("SearchStatus", "false");
				} else {
					inputMap.put("SearchStatus", "true");
					inputMap.put("lLstSinglEmployee", lLstSinglEmployee);
					String fName = null;
					if (lLstSinglEmployee.get(1) != null) {
						fName = lLstSinglEmployee.get(1).toString().concat(" ");
					} else {
						fName = "";
					}
					String mName = null;
					if (lLstSinglEmployee.get(2) != null) {
						mName = lLstSinglEmployee.get(2).toString().concat(" ");
					} else {
						mName = "";
					}
					String lName = null;
					if (lLstSinglEmployee.get(3) != null) {
						lName = lLstSinglEmployee.get(3).toString();
					} else {
						lName = "";
					}
					Date superAnnuationDate = null;
					if (lLstSinglEmployee.get(6) != null) {
						superAnnuationDate = (Date) lLstSinglEmployee.get(6);
					}
					int allowMonthGpf = 0;
					int allowyearGpf = 0;
					Calendar calc = Calendar.getInstance();
					calc.setTime(superAnnuationDate);
					calc.add(2, -3);
					Date allowedGPFDate = calc.getTime();
					allowMonthGpf = superAnnuationDate.getMonth() + 1;
					allowyearGpf = superAnnuationDate.getYear() + 1900;
					inputMap.put("allowMonthGpf", Integer.valueOf(allowMonthGpf));
					inputMap.put("allowyearGpf", Integer.valueOf(allowyearGpf));
					inputMap.put("superAnnuationDate", superAnnuationDate);
					String EmpId = lLstSinglEmployee.get(0).toString();

					lObjHrEisEmpVO = lObjBrokenPeriodDAO.getHrEisEmpMstVOForEmpMpgId(Long.valueOf(EmpId));

					Long lLongHrEisEmpId = Long.valueOf(lObjHrEisEmpVO.getEmpId());

					long emloyeeId = Long.valueOf(lObjHrEisEmpVO.getEmpId()).longValue();
					this.logger.info("Employeeid from hreisempmst is **********" + emloyeeId);

					String sevarthId = lObjBrokenPeriodDAO.getSevarthId(emloyeeId);
					this.logger.info("sevarthId from hreisempmst is **********" + sevarthId);
					inputMap.put("sevarthId", sevarthId);

					String Designation = lObjBrokenPeriodDAO.getDesignationName(EmpId);

					String OfficeName = lObjBrokenPeriodDAO.getOfficeName(EmpId);
					String GPFOrDCPSNo = lObjBrokenPeriodDAO.getGPFOrDCPSNo(sevarthId);
					inputMap.put("orgEmpId", EmpId);
					inputMap.put("lLongHrEisEmpId", lLongHrEisEmpId);
					inputMap.put("EmpId", lLongHrEisEmpId);
					inputMap.put("EmpName", fName.concat(mName.concat(lName)));
					inputMap.put("Designation", Designation);
					inputMap.put("OfficeName", OfficeName);
					inputMap.put("GPFOrDCPSNo", GPFOrDCPSNo);

					List lListAllowancesForEmp = lObjBrokenPeriodDAO.getAllowancesListForGivenEmp(lLongHrEisEmpId);
					List lListDeductionsForEmp = lObjBrokenPeriodDAO.getDeductionsListForGivenEmp(lLongHrEisEmpId);
					Iterator it = lListAllowancesForEmp.iterator();
					while (it.hasNext()) {
						Object[] row = (Object[]) it.next();
						this.logger.info("broken allow" + row[0] + "   " + row[1]);
					}
					it = null;
					it = lListDeductionsForEmp.iterator();
					while (it.hasNext()) {
						Object[] row = (Object[]) it.next();
						this.logger.info("broken allo" + row[0] + "   " + row[1]);
					}
					inputMap.put("AllowancesList", lListAllowancesForEmp);
					inputMap.put("DeductionsList", lListDeductionsForEmp);
					if (!lObjBrokenPeriodDAO.checkBrokenPeriodPayExistsOrNot(lLongHrEisEmpId, lLongYearId, lLongMonthId)
							.booleanValue()) {
						inputMap.put("PaysAddedBefore", "No");
					} else {
						List<MstBrokenPeriodPay> lListAddedBrokenPeriodPays = lObjBrokenPeriodDAO
								.getAddedBrokenPeriodPaysForEmp(lLongHrEisEmpId, lLongYearId, lLongMonthId);
						List DataForDisplayList = new ArrayList();
						for (Integer lInt = Integer.valueOf(0); lInt.intValue() < lListAddedBrokenPeriodPays
								.size(); lInt = Integer.valueOf(lInt.intValue() + 1)) {
							List lListAddedAllowances = new ArrayList();
							List lListAddedAllowancesNew = new ArrayList();
							List lListTempAddedAllowances = new ArrayList();
							List lListAddedDeductions = new ArrayList();
							List lListAddedDeductionsNew = new ArrayList();
							List lListTempAddedDeductions = new ArrayList();
							BrokenPeriodPayCustomVO brokenPeriodPayCustomVO = new BrokenPeriodPayCustomVO();

							MstBrokenPeriodPay brokenPeriodPay = (MstBrokenPeriodPay) lListAddedBrokenPeriodPays
									.get(lInt.intValue());
							brokenPeriodPayCustomVO.setFromDate(brokenPeriodPay.getFromDate());
							brokenPeriodPayCustomVO.setToDate(brokenPeriodPay.getToDate());
							brokenPeriodPayCustomVO.setNoOfDays(brokenPeriodPay.getNoOfDays());
							brokenPeriodPayCustomVO.setBasicPay(brokenPeriodPay.getBasicPay());
							brokenPeriodPayCustomVO.setNetPay(brokenPeriodPay.getNetPay());
							brokenPeriodPayCustomVO.setReason(brokenPeriodPay.getReason());
							brokenPeriodPayCustomVO.setRemarks(brokenPeriodPay.getRemarks());

							lListTempAddedAllowances = lObjBrokenPeriodDAO.getAddedAllowancesForEmp(
									((MstBrokenPeriodPay) lListAddedBrokenPeriodPays.get(lInt.intValue()))
											.getBrokenPeriodId());
							lListAddedAllowances.addAll(lListTempAddedAllowances);
							for (int i = 0; i < (lListAllowancesForEmp != null ? lListAllowancesForEmp.size()
									: 0); i++) {
								Long allowCode = (Long) ((Object[]) lListAllowancesForEmp.get(i))[0];
								boolean found = false;
								for (int j = 0; j < (lListAddedAllowances != null ? lListAddedAllowances.size()
										: 0); j++) {
									Object[] data = (Object[]) lListAddedAllowances.get(j);
									if (Arrays.asList(data).contains(allowCode)) {
										lListAddedAllowancesNew.add(data);
										found = true;
										break;
									}
								}
								if (!found) {
									String allowDesc = (String) ((Object[]) lListAllowancesForEmp.get(i))[1];
									Object[] newData = { Integer.valueOf(0), Integer.valueOf(0), allowCode,
											Integer.valueOf(0), allowDesc };
									lListAddedAllowancesNew.add(newData);
								}
							}
							brokenPeriodPayCustomVO.setAllowList(lListAddedAllowancesNew);

							lListTempAddedDeductions = lObjBrokenPeriodDAO.getAddedDeductionsForEmp(
									((MstBrokenPeriodPay) lListAddedBrokenPeriodPays.get(lInt.intValue()))
											.getBrokenPeriodId());
							lListAddedDeductions.addAll(lListTempAddedDeductions);
							for (int i = 0; i < (lListDeductionsForEmp != null ? lListDeductionsForEmp.size()
									: 0); i++) {
								Long deducCode = (Long) ((Object[]) lListDeductionsForEmp.get(i))[0];
								boolean found = false;
								for (int j = 0; j < (lListAddedDeductions != null ? lListAddedDeductions.size()
										: 0); j++) {
									Object[] data = (Object[]) lListAddedDeductions.get(j);
									if (Arrays.asList(data).contains(deducCode)) {
										lListAddedDeductionsNew.add(data);
										found = true;
										break;
									}
								}
								if (!found) {
									String deducDesc = (String) ((Object[]) lListDeductionsForEmp.get(i))[1];
									Object[] newData = { Integer.valueOf(0), Integer.valueOf(0), deducCode,
											Integer.valueOf(0), deducDesc };
									lListAddedDeductionsNew.add(newData);
								}
							}
							brokenPeriodPayCustomVO.setDeductList(lListAddedDeductionsNew);
							DataForDisplayList.add(brokenPeriodPayCustomVO);
						}
						inputMap.put("DataForDisplayList", DataForDisplayList);
						inputMap.put("BrokenPeriodPayListSize", Integer.valueOf(lListAddedBrokenPeriodPays.size()));
						inputMap.put("PaysAddedBefore", "Yes");
						inputMap.put("Generated", Integer.valueOf(generated));
					}
					Long lLongYrId = Long
							.valueOf(lObjBrokenPeriodDAO.payBilYr(lLongMonthId.longValue(), lLongYearId.longValue()));
					generated = lObjBrokenPeriodDAO.isGenerated(lLongHrEisEmpId.longValue(), lLongMonthId.longValue(),
							lLongYrId.longValue());

					inputMap.put("Generated", Integer.valueOf(generated));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
		}
		objRes.setResultValue(inputMap);
		objRes.setViewName("BrokenPeriodPay");

		return objRes;
	}

	public ResultObject saveBrokenPeriodPay(Map inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");
		Boolean lBlFlag = Boolean.valueOf(false);
		Boolean lBlFirstTimeSave = null;
		Long lLongBrknPrdIdForDelete = null;
		try {
			setSessionInfo(inputMap);
			BrokenPeriodDAO lObjBrokenPeriodDAO = new BrokenPeriodDAOImpl(MstBrokenPeriodPay.class,
					this.serv.getSessionFactory());

			MstBrokenPeriodPay[] lArrMstBrokenPeriodPay = (MstBrokenPeriodPay[]) inputMap.get("lArrMstBrokenPeriodPay");
			List<RltBrokenPeriodAllow> lListRltBrokenPeriodAllow = (List) inputMap.get("lListBrokenPeriodAllows");
			List<RltBrokenPeriodDeduc> lListRltBrokenPeriodDeduc = (List) inputMap.get("lListBrokenPeriodDeducs");

			Long lLongYear = Long.valueOf(StringUtility.getParameter("year", this.request).trim());
			Long lLongMonth = Long.valueOf(StringUtility.getParameter("month", this.request).trim());
			Long lLongEisEmpId = Long.valueOf(StringUtility.getParameter("eisEmpId", this.request).trim());
			lBlFirstTimeSave = Boolean.valueOf(!lObjBrokenPeriodDAO
					.checkBrokenPeriodPayExistsOrNot(lLongEisEmpId, lLongYear, lLongMonth).booleanValue());
			if (!lBlFirstTimeSave.booleanValue()) {
				List<MstBrokenPeriodPay> lListBrokenPeriodPayList = lObjBrokenPeriodDAO
						.getAddedBrokenPeriodPaysForEmp(lLongEisEmpId, lLongYear, lLongMonth);
				for (Integer lInt = Integer.valueOf(0); lInt.intValue() < lListBrokenPeriodPayList
						.size(); lInt = Integer.valueOf(lInt.intValue() + 1)) {
					lLongBrknPrdIdForDelete = ((MstBrokenPeriodPay) lListBrokenPeriodPayList.get(lInt.intValue()))
							.getBrokenPeriodId();
					lObjBrokenPeriodDAO.deleteAllBrokenPeriodAllowancesForBrknPrdId(lLongBrknPrdIdForDelete);
					lObjBrokenPeriodDAO.deleteAllBrokenPeriodDeductionsForBrknPrdId(lLongBrknPrdIdForDelete);
					lObjBrokenPeriodDAO.deleteAllBrokenPeriodPaysForPk(lLongBrknPrdIdForDelete);
				}
			}
			for (Integer lInt = Integer.valueOf(0); lInt.intValue() < lArrMstBrokenPeriodPay.length; lInt = Integer
					.valueOf(lInt.intValue() + 1)) {
				lObjBrokenPeriodDAO.create(lArrMstBrokenPeriodPay[lInt.intValue()]);
			}
			for (Integer lInt = Integer.valueOf(0); lInt.intValue() < lListRltBrokenPeriodAllow.size(); lInt = Integer
					.valueOf(lInt.intValue() + 1)) {
				lObjBrokenPeriodDAO.create(lListRltBrokenPeriodAllow.get(lInt.intValue()));
			}
			for (Integer lInt = Integer.valueOf(0); lInt.intValue() < lListRltBrokenPeriodDeduc.size(); lInt = Integer
					.valueOf(lInt.intValue() + 1)) {
				lObjBrokenPeriodDAO.create(lListRltBrokenPeriodDeduc.get(lInt.intValue()));
			}
			lBlFlag = Boolean.valueOf(true);
		} catch (Exception ex) {
			resObj.setResultValue(null);
			this.logger.error(" Error is : " + ex, ex);
			resObj.setThrowable(ex);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
			ex.printStackTrace();
			return resObj;
		}
		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	private StringBuilder getResponseXMLDoc(Boolean flag) {
		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private Map getRuleValues(String strEmpId, Map inputMap, long finYearId) {
		Map ruleMap = new HashMap();
		Map loginMap = (Map) inputMap.get("baseLoginMap");

		long locId = Long.parseLong(loginMap.get("locationId").toString());
		try {
			PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, this.serv.getSessionFactory());
			PayComponentMasterDAOImpl payComponentMasterDAOImpl = new PayComponentMasterDAOImpl(HrPayAllowDedMst.class,
					this.serv.getSessionFactory());
			PayComponentRuleGroupMstDAOImpl payComponentRuleGroupMstDAOImpl = new PayComponentRuleGroupMstDAOImpl(
					HrPayRuleGrpMst.class, this.serv.getSessionFactory());
			PayComponentRuleGroupParamRltDAOImpl payComponentRuleGroupParamRltDAOImpl = new PayComponentRuleGroupParamRltDAOImpl(
					HrPayRuleGrpParamRlt.class, this.serv.getSessionFactory());
			BrokenPeriodDAOImpl brokenDao = new BrokenPeriodDAOImpl(null, this.serv.getSessionFactory());

			EmpPaybillVO hrEisOtherDtlsObj = new EmpPaybillVO();

			hrEisOtherDtlsObj.setEisEmpId(Long.parseLong(inputMap.get("lLongHrEisEmpId").toString()));
			List empObjectList = brokenDao.getEmpBasicDetails(inputMap.get("lLongHrEisEmpId").toString());

			long qtrRentAmt = 0L;

			this.logger.info("empObjectList is...." + empObjectList.size());
			if ((empObjectList != null) && (empObjectList.size() > 0)) {
				for (int i = 0; i < empObjectList.size(); i++) {
					Object[] row = (Object[]) empObjectList.get(i);
					if (row != null) {
						long orgEmpId = row[0] != null ? Long.valueOf(row[0].toString()).longValue() : 0L;
						long eisEmpId = row[1] != null ? Long.valueOf(row[1].toString()).longValue() : 0L;
						long basicAmt = row[2] != null ? Long.valueOf(row[2].toString()).longValue() : 0L;
						long incomeTax = row[3] != null ? Long.valueOf(row[3].toString()).longValue() : 0L;
						long gradeId = row[4] != null ? Long.valueOf(row[4].toString()).longValue() : 0L;
						long desigId = row[5] != null ? Long.valueOf(row[5].toString()).longValue() : 0L;
						long scaleId = row[6] != null ? Long.valueOf(row[6].toString()).longValue() : 0L;
						long scaleStartAmt = row[7] != null ? Long.valueOf(row[7].toString()).longValue() : 0L;
						long scaleEndAmt = row[8] != null ? Long.valueOf(row[8].toString()).longValue() : 0L;
						String dcpsOrGPF = row[9] != null ? row[9].toString() : "";
						long postId = row[10] != null ? Long.valueOf(row[10].toString()).longValue() : 0L;
						long userId = row[11] != null ? Long.valueOf(row[11].toString()).longValue() : 0L;
						long payCommissionId = row[12] != null ? Long.valueOf(row[12].toString()).longValue() : 0L;
						long empType = row[13] != null ? Long.valueOf(row[13].toString()).longValue() : 0L;
						long gradeCode = row[14] != null ? Long.valueOf(row[14].toString()).longValue() : 0L;
						int isAvailedHRA = row[15] != null ? Integer.valueOf(row[15].toString()).intValue() : 0;
						Date empDOB = row[16] != null ? (Date) row[16] : null;
						Date empSrvcExp = row[17] != null ? (Date) row[17] : null;
						Date empDOJ = row[18] != null ? (Date) row[18] : null;
						String isPhyHandicapped = row[19] != null ? row[19].toString() : "";
						long gradePay = row[20] != null ? Long.valueOf(row[20].toString()).longValue() : 0L;
						long empCity = row[21] != null ? Long.valueOf(row[21].toString()).longValue() : 0L;

						long postPSRNo = row[22] != null ? Long.valueOf(row[22].toString()).longValue() : 0L;
						long otherDtlsId = row[23] != null ? Long.valueOf(row[23].toString()).longValue() : 0L;
						String empLname = row[24] != null ? String.valueOf(row[24]) : "";

						long svnPCBasicAmt = row[25] != null ? Long.valueOf(row[25].toString()).longValue() : 0L;
						long sevenPCGradePay = row[26] != null ? Long.valueOf(row[26].toString()).longValue() : 0L;

						this.logger.info("before IF");
						if ((orgEmpId != 0L) && (eisEmpId != 0L) && (desigId != 0L) && (gradeId != 0L)
								&& (scaleId != 0L) && (postId != 0L) && (userId != 0L)) {
							this.logger.info("Emp DOJ is ::" + empDOJ);
							hrEisOtherDtlsObj.setOrgEmpId(orgEmpId);
							hrEisOtherDtlsObj.setEisEmpId(eisEmpId);
							hrEisOtherDtlsObj.setDesigId(desigId);
							hrEisOtherDtlsObj.setGradeId(gradeId);
							hrEisOtherDtlsObj.setScaleId(scaleId);
							hrEisOtherDtlsObj.setScaleStartAmt(scaleStartAmt);
							hrEisOtherDtlsObj.setScaleEndAmt(scaleEndAmt);
							hrEisOtherDtlsObj.setDcpsOrGPF(dcpsOrGPF);
							hrEisOtherDtlsObj.setPostId(postId);
							hrEisOtherDtlsObj.setIncomeTax(incomeTax);
							hrEisOtherDtlsObj.setUserId(userId);
							if (svnPCBasicAmt > 0L) {
								hrEisOtherDtlsObj.setBasicAmt(svnPCBasicAmt);

								hrEisOtherDtlsObj.setPayCommissionId(payCommissionId);
							} else {
								hrEisOtherDtlsObj.setPayCommissionId(payCommissionId);
								hrEisOtherDtlsObj.setBasicAmt(basicAmt);
							}
							hrEisOtherDtlsObj.setEmpType(empType);
							hrEisOtherDtlsObj.setGradeCode(gradeCode);
							hrEisOtherDtlsObj.setIsAvailedHRA(isAvailedHRA);
							hrEisOtherDtlsObj.setEmpDOB(empDOB);
							hrEisOtherDtlsObj.setEmpDOJ(empDOJ);
							hrEisOtherDtlsObj.setEmpSrvcExp(empSrvcExp);
							hrEisOtherDtlsObj.setIsPhyHandicapped(isPhyHandicapped);
							hrEisOtherDtlsObj.setGradePay(gradePay);
							hrEisOtherDtlsObj.setEmpCity(empCity);

							hrEisOtherDtlsObj.setPostPSRNo(postPSRNo);
							hrEisOtherDtlsObj.setOtherDtlsId(otherDtlsId);
							hrEisOtherDtlsObj.setEmpLname(empLname);
							hrEisOtherDtlsObj.setSevenBasicAmt(svnPCBasicAmt);

							this.logger.info("svnPCBasicAmt=" + svnPCBasicAmt);
							if (svnPCBasicAmt > 0L) {
								inputMap.put("basicAmt", Integer.valueOf(Math.round((float) svnPCBasicAmt)));
							} else {
								inputMap.put("basicAmt", Integer.valueOf(Math.round((float) basicAmt)));
							}
							inputMap.put("orgEmpId", Long.valueOf(orgEmpId));
							inputMap.put("lLongHrEisEmpId", Long.valueOf(eisEmpId));
							inputMap.put("EmpId", Long.valueOf(eisEmpId));
							inputMap.put("GPFOrDCPSNo", dcpsOrGPF);

							inputMap.put("svnPCBasicAmt", Integer.valueOf(Math.round((float) svnPCBasicAmt)));
						} else {
							this.logger.error(
									"OrgEmpId is zero, or hreisempid is 0 or desig or grade or scale or post id is 0");
						}
					}
					row = null;
				}
			}
			List<HrPayRuleGrpMst> activeAllowanceRuleList = payComponentRuleGroupMstDAOImpl.getAllActiveRuleList(2);
			inputMap.put("activeAllowanceRuleList", activeAllowanceRuleList);
			activeAllowanceRuleList = null;

			List<HrPayRuleGrpMst> activeDeductionRuleList = payComponentRuleGroupMstDAOImpl
					.getAllActiveDeductionRuleList();
			inputMap.put("activeDeductionRuleList", activeDeductionRuleList);
			activeDeductionRuleList = null;

			List activeAllowRuleParamMpgList = payComponentRuleGroupParamRltDAOImpl.getAllActiveRuleParamMpgList(2);
			inputMap.put("activeAllowRuleParamMpgList", activeAllowRuleParamMpgList);
			activeAllowRuleParamMpgList = null;

			List activeDeducRuleParamMpgList = payComponentRuleGroupParamRltDAOImpl
					.getAllActiveDeductionRuleParamMpgList();
			inputMap.put("activeDeducRuleParamMpgList", activeDeducRuleParamMpgList);
			activeDeducRuleParamMpgList = null;

			List<HrPayRuleGrpMst> activeAllowanceUsedInFormulaRuleList = payComponentRuleGroupMstDAOImpl
					.getAllActiveRuleList(2, 1);
			inputMap.put("activeAllowanceUsedInFormulaRuleList", activeAllowanceUsedInFormulaRuleList);
			activeAllowanceUsedInFormulaRuleList = null;

			List<HrPayRuleGrpMst> activeDeductionUsedInFormulaRuleList = payComponentRuleGroupMstDAOImpl
					.getAllActiveDeductionRuleList(1);
			inputMap.put("activeDeductionUsedInFormulaRuleList", activeDeductionUsedInFormulaRuleList);
			activeDeductionUsedInFormulaRuleList = null;

			List activeAllowUsedInFormulaRuleParamMpgList = payComponentRuleGroupParamRltDAOImpl
					.getAllActiveRuleParamMpgList(2, 1);
			inputMap.put("activeAllowUsedInFormulaRuleParamMpgList", activeAllowUsedInFormulaRuleParamMpgList);
			activeAllowUsedInFormulaRuleParamMpgList = null;

			List activeDeducUsedInFormulaRuleParamMpgList = payComponentRuleGroupParamRltDAOImpl
					.getAllActiveDeductionRuleParamMpgList(1);
			inputMap.put("activeDeducUsedInFormulaRuleParamMpgList", activeDeducUsedInFormulaRuleParamMpgList);
			activeDeducUsedInFormulaRuleParamMpgList = null;

			List<HrPayAllowDedMst> ruleBasedAllowanceList = payComponentMasterDAOImpl.getPayActiveComponets(2);
			inputMap.put("ruleBasedAllowanceList", ruleBasedAllowanceList);
			ruleBasedAllowanceList = null;

			List<HrPayAllowDedMst> ruleBasedDeductionList = payComponentMasterDAOImpl.getPayActiveComponets(3);
			inputMap.put("ruleBasedDeductionList", ruleBasedDeductionList);
			ruleBasedDeductionList = null;

			long langId = StringUtility.convertToLong(loginMap.get("langId").toString()).longValue();
			List ruleBasedAllowDeducUsedInFormulaList = payComponentMasterDAOImpl.getPayCompUsedInFormula(0, 0L,
					langId);
			if ((ruleBasedAllowDeducUsedInFormulaList != null) && (!ruleBasedAllowDeducUsedInFormulaList.isEmpty())) {
				int dataListSize = ruleBasedAllowDeducUsedInFormulaList.size();
				StringBuilder sbr = null;
				HrPayAllowDedMst allowDedMst = null;
				long commissionId = 0L;
				Object[] data = null;
				String sbrString = null;
				for (int ctr = 0; ctr < dataListSize; ctr++) {
					data = (Object[]) ruleBasedAllowDeducUsedInFormulaList.get(ctr);
					allowDedMst = (data != null) && (data.length > 0) && (data[0] != null) ? (HrPayAllowDedMst) data[0]
							: new HrPayAllowDedMst();
					commissionId = (data != null) && (data.length > 1) && (data[1] != null)
							? ((Long) data[1]).longValue()
							: 0L;

					sbr = new StringBuilder(String.valueOf(commissionId)).append("_");
					switch (allowDedMst.getType()) {
					case 2:
						sbr.append(2);
						break;
					case 3:
						sbr.append(3);
						break;
					default:
						sbr = null;
					}
					sbrString = String.valueOf(sbr);
					if (inputMap.containsKey(sbrString)) {
						List<HrPayAllowDedMst> tempDataList = (List) inputMap.get(sbrString);
						tempDataList.add(allowDedMst);
						inputMap.put(sbrString, tempDataList);
						tempDataList = null;
					} else {
						List<HrPayAllowDedMst> tempDataList = new ArrayList();
						tempDataList.add(allowDedMst);
						inputMap.put(sbrString, tempDataList);
						tempDataList = null;
					}
				}
			}
			ruleBasedAllowDeducUsedInFormulaList = null;

			int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
			int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());

			Calendar tempCalGiven = Calendar.getInstance();
			tempCalGiven.set(1, yearGiven);
			tempCalGiven.set(2, monthGiven - 1);
			tempCalGiven.set(5, 1);
			Date givenDate = tempCalGiven.getTime();
			List empQtrRentList = brokenDao.getQtrAmount(strEmpId, givenDate);
			this.logger.info("Quater Fetching Query End time" + System.currentTimeMillis());
			Map qtrMap = new HashMap();
			for (int qtrCounter = 0; qtrCounter < empQtrRentList.size(); qtrCounter++) {
				Object[] row = (Object[]) empQtrRentList.get(qtrCounter);
				long leisEmpId = row[0] != null ? Long.valueOf(row[0].toString()).longValue() : 0L;
				int qtrRent = row[1] != null ? Integer.valueOf(row[1].toString()).intValue() : 0;
				if (!qtrMap.containsKey(Long.valueOf(leisEmpId))) {
					qtrMap.put(Long.valueOf(leisEmpId), Integer.valueOf(qtrRent));
				}
			}
			empQtrRentList = null;

			Map empGisMap = new HashMap();
			this.logger.info("GIS Fetching Query Start time" + System.currentTimeMillis());
			List empGisList = brokenDao.getEisGrade(strEmpId);
			this.logger.info("GIS Fetching Query End time" + System.currentTimeMillis());
			Object[] row = null;
			int empGisSize = empGisList.size();
			for (int gisCounter = 0; gisCounter < empGisSize; gisCounter++) {
				row = (Object[]) empGisList.get(gisCounter);
				long gisGradeId = row[0] != null ? Long.valueOf(row[0].toString()).longValue() : 0L;
				long gisGradeCode = row[1] != null ? Long.valueOf(row[1].toString()).longValue() : 0L;
				long empId = row[2] != null ? Long.valueOf(row[2].toString()).longValue() : 0L;
				if (!empGisMap.containsKey(Long.valueOf(empId))) {
					empGisMap.put(Long.valueOf(empId), gisGradeId + "~" + gisGradeCode);
					hrEisOtherDtlsObj.setGradeId(gisGradeId);
					hrEisOtherDtlsObj.setGisGradeCode(gisGradeCode);
				}
			}
			empGisList = null;

			inputMap.put("hrEisOtherDtls", hrEisOtherDtlsObj);
			Map empOfficeMap = new HashMap();
			HrPayOfficePostMpgDAOImpl hrPayOfficePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(
					HrPayOfficepostMpg.class, this.serv.getSessionFactory());
			this.logger.info("Office Post Fetching Query Start time" + System.currentTimeMillis());
			List officePostList = brokenDao.getOfficeClass(hrEisOtherDtlsObj.getOrgEmpId());
			this.logger.info("Office Post Fetching Query End time" + System.currentTimeMillis());
			int officePostListSize = officePostList.size();
			row = null;
			for (int officeCnt = 0; officeCnt < officePostListSize; officeCnt++) {
				row = (Object[]) officePostList.get(officeCnt);
				long officePostId = row[0] != null ? Long.valueOf(row[0].toString()).longValue() : 0L;
				String OfficeCityClass = row[1] != null ? String.valueOf(row[1].toString()) : "";
				if (!empOfficeMap.containsKey(Long.valueOf(officePostId))) {
					empOfficeMap.put(Long.valueOf(officePostId), OfficeCityClass);
					hrEisOtherDtlsObj.setPostCityClass(OfficeCityClass);
				}
			}
			officePostList = null;

			Map empDCPSValMap = new HashMap();
			this.logger.info("Congtribution Fetching Query Start time" + System.currentTimeMillis());
			List empDCPSValList = brokenDao.getDCPSValues(strEmpId, monthGiven, finYearId);
			this.logger.info("Contribution Fetching Query End time" + System.currentTimeMillis());
			if ((empDCPSValList != null) && (empDCPSValList.size() > 0)) {
				empDCPSValMap = generateDCPSMap(empDCPSValList);
				this.logger.info("DCPS Map in GenerateBillCalculation is " + empDCPSValMap);
			}
			inputMap.put("empDCPSValMap", empDCPSValMap);
			empDCPSValList = null;

			int isPTMappedList = brokenDao.isComponenetMapped(strEmpId, 2500135L, 35L);
			Boolean isPTMappedMap = Boolean.valueOf(false);
			inputMap.put("isPTMapped", "0");
			if (isPTMappedList > 0) {
				inputMap.put("isPTMapped", "1");
			}
			this.logger.info("Allowance Fetching Query Start time" + System.currentTimeMillis());
			List<HrPayAllowTypeMst> allowTypeMstList = brokenDao.getEmpMappedAllownace(Long.parseLong(strEmpId),
					this.gStrLocationCode);
			this.logger.info("Allowance Fetching Query End time" + System.currentTimeMillis());
			this.logger.info("Emp compo mapping list for Allowance size is " + allowTypeMstList.size());
			Map<Long, List<AllwValCustomVO>> empAllowCompoMap = generateEmpAllowCompoMap(allowTypeMstList);
			inputMap.put("empAllowCompoMap", empAllowCompoMap);

			allowTypeMstList = null;

			DeptCompMPGDAOImpl deptCompMPGDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class,
					this.serv.getSessionFactory());
			inputMap.put("mapEditableAllowance", generatemapForAllEditableAllowance(
					brokenDao.getAllActiveEditableAllowance(Long.parseLong(strEmpId), this.gStrLocationCode)));
			inputMap.put("mapEditableDeduction", generatemapForAllEditableDeduction(
					brokenDao.getAllActiveEditableDeduction(Long.parseLong(strEmpId), this.gStrLocationCode)));
			deptCompMPGDAOImpl = null;

			DeductionDtlsDAOImpl deductionDaoImpl = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,
					this.serv.getSessionFactory());
			this.logger.info("Deductions Fetching Query Start time" + System.currentTimeMillis());
			List<HrPayDeducTypeMst> deducTypeMstList = brokenDao.getEmpMappedDeduction(Long.parseLong(strEmpId),
					this.gStrLocationCode);
			this.logger.info("Deductions Fetching Query End time" + System.currentTimeMillis());
			this.logger.info("Emp compo mapping list for Deduction size is " + deducTypeMstList.size());
			Map<Long, List<DeductionCustomVO>> empDeducCompoMap = generateEmpDeducCompoMap(deducTypeMstList);
			inputMap.put("empDeducCompoMap", empDeducCompoMap);
			deductionDaoImpl = null;
			deducTypeMstList = null;

			OtherDetailDAOImpl detailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,
					this.serv.getSessionFactory());
			Map allowEdpMap = detailDAOImpl.getEdpAllwMap(0L, locId);
			inputMap.put("allowEdpMap", allowEdpMap);

			inputMap = executeCoreLogic(inputMap);
		} catch (Exception ex) {
			this.logger.error(" Error is : " + ex, ex);
		}
		return inputMap;
	}

	public Map generateEmpAllowCompoMap(List empAllowCompoList) {
		Map empAllowCompoMap = new HashMap();
		List<AllwValCustomVO> empAllwCompoLst = new ArrayList();
		long prevEmpId = 0L;
		if ((empAllowCompoList != null) && (empAllowCompoList.size() > 0)) {
			int size = empAllowCompoList.size();
			Object[] row = null;
			for (int i = 0; i < size; i++) {
				row = (Object[]) empAllowCompoList.get(i);
				if (row != null) {
					long empId = row[0] != null ? Long.valueOf(row[0].toString()).longValue() : 0L;
					if (prevEmpId == 0L) {
						prevEmpId = empId;
					}
					if (prevEmpId != empId) {
						this.logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						this.logger.info("empAllwCompoLst is " + empAllwCompoLst);

						empAllowCompoMap.put(Long.valueOf(prevEmpId), empAllwCompoLst);
						empAllwCompoLst = new ArrayList();
						prevEmpId = empId;
					}
					if (i == empAllowCompoList.size() - 1) {
						empAllowCompoMap.put(Long.valueOf(empId), empAllwCompoLst);
					}
					long allowId = row[1] != null ? Long.valueOf(row[1].toString()).longValue() : 0L;
					String allowDesc = row[2] != null ? String.valueOf(row[2].toString()) : "";
					long allwDedId = row[3] != null ? Long.valueOf(row[3].toString()).longValue() : 0L;
					AllwValCustomVO allwCustomVO = new AllwValCustomVO();
					if ((empId != 0L) && (allowId != 0L)) {
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

	public Map generateDCPSMap(List empDCPSValList) {
		Map<Long, Map> empDCPSMap = new HashMap();
		long prevEmpId = 0L;
		Map revisedEmpDCPSList = new HashMap();
		if ((empDCPSValList != null) && (empDCPSValList.size() > 0)) {
			Object[] row = null;
			for (int i = 0; i < empDCPSValList.size(); i++) {
				row = (Object[]) empDCPSValList.get(i);
				if (row != null) {
					long empId = row[2] != null ? Long.valueOf(row[2].toString()).longValue() : 0L;
					double contributionAmt = row[0] != null ? Double.valueOf(row[0].toString()).doubleValue() : 0.0D;
					long contriType = row[1] != null ? Long.valueOf(row[1].toString()).longValue() : 0L;
					if (prevEmpId == 0L) {
						prevEmpId = empId;
					}
					if (prevEmpId != empId) {
						this.logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						this.logger.info("revisedEmpDCPSList is " + revisedEmpDCPSList);

						empDCPSMap.put(Long.valueOf(prevEmpId), revisedEmpDCPSList);
						revisedEmpDCPSList = new HashMap();
						prevEmpId = empId;
					}
					if (i == empDCPSValList.size() - 1) {
						empDCPSMap.put(Long.valueOf(empId), revisedEmpDCPSList);
					}
					if (!revisedEmpDCPSList.containsKey(Long.valueOf(contriType))) {
						revisedEmpDCPSList.put(Long.valueOf(contriType), Double.valueOf(contributionAmt));
					}
				}
			}
		}
		return empDCPSMap;
	}

	public Map<Long, List<AllwValCustomVO>> generatemapForAllEditableAllowance(List inputlist) {
		Map<Long, List<AllwValCustomVO>> returnMap = new HashMap();
		if ((inputlist != null) && (inputlist.size() > 0)) {
			int size = inputlist.size();
			Object[] row = null;
			for (int i = 0; i < size; i++) {
				row = (Object[]) inputlist.get(i);
				Long empId = Long.valueOf(row[0] != null ? Long.valueOf(row[1].toString()).longValue() : 0L);
				if (row != null) {
					if (returnMap.get(empId) == null) {
						List<AllwValCustomVO> tempList = new ArrayList();
						AllwValCustomVO allwValCustomVO = new AllwValCustomVO();
						allwValCustomVO.setAllwID(Long.valueOf(row[2].toString()).longValue());
						allwValCustomVO.setAllowanceVal(Long.valueOf(row[3].toString()).longValue());
						tempList.add(allwValCustomVO);
						allwValCustomVO = null;
						returnMap.put(empId, tempList);
						tempList = null;
					} else {
						List<AllwValCustomVO> tempList = (List) returnMap.get(empId);
						AllwValCustomVO allwValCustomVO = new AllwValCustomVO();
						allwValCustomVO.setAllwID(Long.valueOf(row[2].toString()).longValue());
						allwValCustomVO.setAllowanceVal(Long.valueOf(row[3].toString()).longValue());
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
		Map<Long, List<DeductionCustomVO>> returnMap = new HashMap();
		if ((inputlist != null) && (inputlist.size() > 0)) {
			int size = inputlist.size();
			Object[] row = null;
			Long empId = Long.valueOf(0L);
			for (int i = 0; i < size; i++) {
				row = (Object[]) inputlist.get(i);
				if (row != null) {
					empId = Long.valueOf(row[0] != null ? Long.valueOf(row[1].toString()).longValue() : 0L);
					this.logger.info(" - r0 " + row[0].toString() + " r1 " + row[1].toString() + " r2 "
							+ row[2].toString() + " r3 " + row[3].toString());
					if (returnMap.get(empId) == null) {
						this.logger.info(" -- new list created for " + empId);

						List<DeductionCustomVO> tempList = new ArrayList();
						DeductionCustomVO deducValCustomVO = new DeductionCustomVO();
						deducValCustomVO.setDeducId(Long.valueOf(row[2].toString()).longValue());
						deducValCustomVO.setDeductionVal(Double.valueOf(row[3].toString()).doubleValue());
						tempList.add(deducValCustomVO);
						deducValCustomVO = null;

						returnMap.put(empId, tempList);
						tempList = null;
					} else {
						this.logger.info(" -- existing list used for " + empId);

						List<DeductionCustomVO> tempList = (List) returnMap.get(empId);
						DeductionCustomVO deducValCustomVO = new DeductionCustomVO();
						deducValCustomVO.setDeducId(Long.valueOf(row[2].toString()).longValue());
						deducValCustomVO.setDeductionVal(Double.valueOf(row[3].toString()).doubleValue());
						tempList.add(deducValCustomVO);
						deducValCustomVO = null;
						returnMap.put(empId, tempList);
						tempList = null;
					}
				}
			}
		}
		return returnMap;
	}

	public Map executeCoreLogic(Map inputMap) throws Exception {
		Map resultMap = inputMap;
		if (resultMap.containsKey("payBillVO")) {
			resultMap.remove("payBillVO");
		}
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long loanLookupId = Long.parseLong(resourceBundle.getString("loanLookupId"));
		long advLookupId = Long.parseLong(resourceBundle.getString("advLookupId"));

		long sixthPayCommId = Long.parseLong(resourceBundle.getString("commissionSixId"));
		long commissionFiveId = Long.parseLong(resourceBundle.getString("commissionFiveId"));
		long commissionFourId = Long.parseLong(resourceBundle.getString("commissionFourId"));
		long commissionThreeId = Long.parseLong(resourceBundle.getString("commissionThreeId"));
		long commissionTwoId = Long.parseLong(resourceBundle.getString("commissionTwoId"));
		long commissionOneId = Long.parseLong(resourceBundle.getString("commissionOneId"));
		long commissionSevenId = Long.parseLong(resourceBundle.getString("commissionSevenId"));

		long hrrId = Long.parseLong(resourceBundle.getString("hrrId"));
		BrokenPeriodDAO objBrokenPeriodDAO = new BrokenPeriodDAOImpl(null, this.serv.getSessionFactory());
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		long advEMI = 0L;
		try {
			long psrNo = 0L;
			int grade = 0;
			long scale_start_amt = 0L;
			EmpPaybillVO empPaybillVO = (EmpPaybillVO) inputMap.get("hrEisOtherDtls");
			long empId = empPaybillVO.getEisEmpId();
			Map loginMap = (Map) inputMap.get("baseLoginMap");
			long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString()).longValue();
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,
					serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = (CmnDatabaseMst) cmnDatabaseMstDaoImpl.read(Long.valueOf(dbId));
			this.logger.info("cmnDatabaseMst in Core Logic is " + cmnDatabaseMst);
			this.logger.info("DB id is " + cmnDatabaseMst.getDbDescription());
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString()).longValue();
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = (OrgUserMst) orgUserMstDaoImpl.read(Long.valueOf(userId));
			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString()).longValue();
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = (OrgPostMst) orgPostMstDaoImpl.read(Long.valueOf(postId));
			HrPayPaybill payBillVO = new HrPayPaybill();
			long payBillId = inputMap.get("payBillId") != null
					? Long.valueOf(inputMap.get("payBillId").toString()).longValue()
					: 0L;
			long empid = empPaybillVO.getEisEmpId();
			long orgEmpId = empPaybillVO.getOrgEmpId();

			this.logger.info("**********************************************************the emp id is " + empid);
			this.logger.info("Core Logic PerEmployee Start Time" + System.currentTimeMillis());

			long CurrBasic = 0L;
			int isAvailedHRA = 0;

			String city = "";
			boolean isHandicapped = true;
			this.logger.info(" isAvailedHRA in coreLogic is: " + isAvailedHRA);
			this.logger.info("the currBasic " + CurrBasic);
			this.logger.info("the city " + city);
			this.logger.info("the grade  " + grade);
			this.logger.info("the isHandicapped " + isHandicapped);
			this.logger.info("the scale_start_amt " + scale_start_amt);
			this.logger.info("isAvailedHRA : " + isAvailedHRA);
			long payCommissionId = empPaybillVO.getPayCommissionId();
			this.logger.info("Pay Commission is --->" + payCommissionId);
			Map input = generatePassMap(inputMap);

			  /* Added Naveen Priya Sinha NPS 2022 */
	         BigDecimal emplyrAllowContri=new BigDecimal(0);
	         BigDecimal empDeducContri=new BigDecimal(0);
	         BigDecimal emplyrDeducContri=new BigDecimal(0);
			Long basicForSvnPC = Long.valueOf(0L);
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			basicForSvnPC = dao.getRevisedSvnPCBasic(empId);
			// Added by lekhchand TA 2022
			Long sevenPcLevel = Long.valueOf(0L);
			Long PaycommissionId = Long.valueOf(0L);
			PaycommissionId = dao.getSvnPayCommission(empId);

			sevenPcLevel = dao.getSvnPCLevel(empId);
			if (sevenPcLevel == null || sevenPcLevel == 0) {
				sevenPcLevel = dao.getSvnPCLevel2(empId);
			}
			this.logger.info("check lekhchand  sevenPcLevel=  " + sevenPcLevel + " Emp Id is= " + empId);

			inputMap.put("hrEisOtherDtls", empPaybillVO);
			Map inputRuleEngine = generatePassMapForRuleEngine(inputMap);
			payBillVO.setBasic0101(Math.round(((Double) input.get("revBasic")).doubleValue()));
			if ((input.get("DP") != null) && (String.valueOf(input.get("DP")).length() > 0)) {
				this.logger
						.info("Dp value set in core logic is " + Math.round(((Double) input.get("DP")).doubleValue()));
				payBillVO.setAllow0119(Math.round(((Double) input.get("DP")).doubleValue()));
			}
			double parResult = 0.0D;
			String strEmpId = inputMap.get("lLongHrEisEmpId").toString();

			List<AllwValCustomVO> allowTypeMst = new ArrayList();
			Map empAllowCompoMap = (HashMap) (inputMap.get("empAllowCompoMap") != null
					? inputMap.get("empAllowCompoMap")
					: new HashMap());
			if (empAllowCompoMap.containsKey(Long.valueOf(empPaybillVO.getEisEmpId()))) {
				this.logger.info("Allowance mapping map found for emp id " + empPaybillVO.getEisEmpId());
				this.logger.info(" " + empAllowCompoMap.get(Long.valueOf(empPaybillVO.getEisEmpId())));
				allowTypeMst = (List) (empAllowCompoMap.get(Long.valueOf(empPaybillVO.getEisEmpId())) != null
						? empAllowCompoMap.get(Long.valueOf(empPaybillVO.getEisEmpId()))
						: new ArrayList());
			}
			empAllowCompoMap = null;
			SalaryRules salaryRules = new SalaryRules();
			SalaryRules_6thPay salaryRuls6thPay = new SalaryRules_6thPay();

			Map allowEdpMap = (HashMap) (inputMap.containsKey("allowEdpMap") ? inputMap.get("allowEdpMap")
					: new HashMap());

			String edpCode = null;
			double totalCompAllw = 0.0D;
			this.logger.info("allowList whole size is " + allowTypeMst.size());
			BigDecimal amt = new BigDecimal(0);
			Map<Long, HrPayEmpSalaryTxn> ruleMap = new HashMap();
			this.logger.info("Physically Handi is :::: " + inputRuleEngine.get(Integer.valueOf(60)));
			if (empPaybillVO.getPayCommissionId() != 2500342L) {
				ruleMap = new PayrollCalculationServiceImpl().getAllRuleBasedPayCompValue(empId, inputRuleEngine, serv,
						Long.valueOf(inputRuleEngine.get(Integer.valueOf(14)).toString()).longValue(), inputMap);
			}
			this.logger.info("ruleMap is " + ruleMap);
			if ((empPaybillVO.getPayCommissionId() == 2500344L) || (empPaybillVO.getPayCommissionId() == 2500346L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				if (empPaybillVO.getPayCommissionId() == 2500346L) {
					empSalaryTxn.setAmount(new BigDecimal(0));
					empSalaryTxn.setAllwDedCode(17L);
					ruleMap.put(Long.valueOf(17L), empSalaryTxn);
				}
				empSalaryTxn = new HrPayEmpSalaryTxn();
				empSalaryTxn.setAmount(new BigDecimal(0));
				empSalaryTxn.setAllwDedCode(15L);

				ruleMap.put(Long.valueOf(15L), empSalaryTxn);
			}
			int billMonth = Integer.parseInt(inputMap.get("monthGiven").toString());
			int billyear = Integer.parseInt(inputMap.get("yearGiven").toString());
			if ((billMonth == 3) && (billyear == 2012)
					&& ((empPaybillVO.getPayCommissionId() == 2500341L)
							|| (empPaybillVO.getPayCommissionId() == 2500343L)
							|| (empPaybillVO.getPayCommissionId() == 2500345L)
							|| (empPaybillVO.getPayCommissionId() == 2500344L))) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(58L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billMonth < 11) && (billyear <= 2012) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(65L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					this.logger.info("sunitha finalVal" + finalVal);

					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2013) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1) && (billMonth < 5)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(72L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 5) && (billMonth <= 9)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(80L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(90L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2014) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth < 5) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(90L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(100L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billMonth == 1) && (billyear == 2015) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(100L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					this.logger.info("sunitha finalVal" + finalVal);

					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billMonth > 1) && (billMonth < 10) && (billyear == 2015)
					&& (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(107L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					this.logger.info("sunitha finalVal" + finalVal);

					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billMonth >= 10) && (billyear == 2015) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(113L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					this.logger.info("sunitha finalVal" + finalVal);

					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2016) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					if (billMonth == 1) {
						BigDecimal tempBasic = empSalaryTxnNew.getAmount();
						this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(113L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);

						empSalaryTxn.setAllwDedCode(8L);

						ruleMap.put(Long.valueOf(8L), empSalaryTxn);
					} else if ((billMonth > 1) && (billMonth < 9)) {
						BigDecimal tempBasic = empSalaryTxnNew.getAmount();
						this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(119L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);

						empSalaryTxn.setAllwDedCode(8L);

						ruleMap.put(Long.valueOf(8L), empSalaryTxn);
					} else if (billMonth >= 9) {
						BigDecimal tempBasic = empSalaryTxnNew.getAmount();
						this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(125L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);

						empSalaryTxn.setAllwDedCode(8L);

						ruleMap.put(Long.valueOf(8L), empSalaryTxn);
					}
				}
			}
			if ((billyear == 2017) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth <= 3) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(125L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth > 3) && (billMonth <= 8)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(132L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(136L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2018) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth == 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(136L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 1) && (billMonth <= 9)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(139L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 10) && (billMonth <= 12)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(142L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2019) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1) && (billMonth <= 6)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(142L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 7) && (billMonth <= 11)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(154L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" gayathri tempDaValNew" + tempDaValNew);
						this.logger.info("sunitha finalVal" + finalVal);
					} else if (billMonth == 12) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(164L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" gayathri tempDaValNew" + tempDaValNew);
						this.logger.info("sunitha finalVal" + finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2020) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1 && billMonth <= 12) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(164L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}

			if ((billyear == 2021) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1) && (billMonth <= 9)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(164L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 10) && (billMonth <= 12)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(189L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			// added by lekhchand seven pc DA 2022
			if ((billyear == 2022) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
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
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear >= 2023) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1 && billMonth < 6)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(212L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 6)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(221L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("sunitha finalVal" + finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			// Start seven pc DA 2019 lekhchand
			if ((billyear == 2019) && (empPaybillVO.getPayCommissionId() == 2500347L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1) && (billMonth <= 6)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(9L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" gayathri tempDaValNew" + tempDaValNew);
						this.logger.info("sunitha finalVal" + finalVal);
					}
					if ((billMonth >= 7) && (billMonth <= 11)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(12L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" Shivram 681 tempDaValNew" + tempDaValNew);
						this.logger.info("Shivram 681 finalVal" + finalVal);
					}
					if (billMonth == 12) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(17L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" Shivram 681 tempDaValNew" + tempDaValNew);
						this.logger.info("Shivram 681 finalVal" + finalVal);
					}
					this.logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			if ((billyear == 2020) && (empPaybillVO.getPayCommissionId() == 2500347L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1 && billMonth <= 12) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(17L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" gayathri tempDaValNew" + tempDaValNew);
						this.logger.info("sunitha finalVal" + finalVal);
					}
					this.logger.info("DA rate is taking old for 7th Pay 65 Tejashree");
					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}

			if ((billyear == 2021) && (empPaybillVO.getPayCommissionId() == 2500347L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if ((billMonth >= 1) && (billMonth <= 9)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(17L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" gayathri tempDaValNew" + tempDaValNew);
					} else if ((billMonth >= 10) && (billMonth <= 12)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(28L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					}
					this.logger.info("DA rate is taking old for 7th Pay 65 Tejashree");
					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			// added by lekhchand DA seven pc 2022
			if ((billyear == 2022) && (empPaybillVO.getPayCommissionId() == 2500347L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1 && billMonth <= 2) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(17L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" gayathri tempDaValNew" + tempDaValNew);
						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 3) && (billMonth <= 7)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(31L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" gayathri tempDaValNew" + tempDaValNew);
						this.logger.info("sunitha finalVal" + finalVal);
					} else if ((billMonth >= 8)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(34L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" gayathri tempDaValNew" + tempDaValNew);
						this.logger.info("sunitha finalVal" + finalVal);
					}
					this.logger.info("DA rate is taking old for 7th Pay 65 Tejashree");
					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}
			// added by lekhchand DA seven pc 2023
			if ((billyear >= 2023) && (empPaybillVO.getPayCommissionId() == 2500347L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					if (billMonth >= 1 && billMonth < 6) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(38L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
						this.logger.info(" naveen Sinha tempDaValNew" + tempDaValNew);
						this.logger.info("naveen Sinha finalVal" + finalVal);
					}else if (billMonth>=6) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(42L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);

						this.logger.info("naveen Sinha finalVal ----" + finalVal);
					}  

					this.logger.info("DA rate is taking old for 7th Pay 65 Tejashree");
					empSalaryTxn.setAllwDedCode(8L);
					this.logger.info("DA rate is taking old for 6th Pay 65 gayathri");
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}

			if ((billMonth == 3) && (billyear == 2012) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(127L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			} else if ((billMonth <= 11) && (billyear == 2012) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(139L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			} else if ((billMonth >= 2) && (billyear == 2014) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());

					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(183L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					this.logger.info("roshan fianl amt" + finalVal);
					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			} else if ((billMonth >= 1) && (billyear == 2015) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();

				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					this.logger.info("Basic Before Adding" + tempBasic.doubleValue());
					BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(200L));
					BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
					empSalaryTxn.setAmount(finalVal);

					this.logger.info("roshan fianl amt" + finalVal);
					empSalaryTxn.setAllwDedCode(8L);

					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			} else if ((billyear == 2016) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if (billMonth == 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(223L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					} else if ((billMonth > 1) && (billMonth < 9)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(234L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					} else if ((billMonth >= 9) && (billMonth <= 12)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(245L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			} else if ((billyear == 2017) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if ((billMonth >= 1) && (billMonth <= 7)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(245L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					} else if ((billMonth == 8) || (billMonth == 9)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(256L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					} else if (billMonth > 9) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(264L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			} else if ((billyear == 2018) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if (billMonth == 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(264L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					} else if ((billMonth >= 2) && (billMonth <= 12)) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(268L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			} else if ((billyear == 2019) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
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
			} else if ((billyear >= 2020 || billyear <= 2022) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
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
			} else if ((billyear >= 2023) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
				HrPayEmpSalaryTxn empSalaryTxn = new HrPayEmpSalaryTxn();
				HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(5L));
				if (empSalaryTxnNew != null) {
					BigDecimal tempBasic = empSalaryTxnNew.getAmount();
					if (billMonth >= 1) {
						BigDecimal tempDaValNew = tempBasic.multiply(BigDecimal.valueOf(296L));
						BigDecimal finalVal = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxn.setAmount(finalVal);
					}
					empSalaryTxn.setAllwDedCode(8L);
					ruleMap.put(Long.valueOf(8L), empSalaryTxn);
				}
			}

			for (int i = 0; i < allowTypeMst.size(); i++) {
				parResult = 0.0D;
				long allowCode = 0L;
				allowCode = ((AllwValCustomVO) allowTypeMst.get(i)).getAllwID();
				if (allowEdpMap.get(allowCode) != null) {
					edpCode = allowEdpMap.get(allowCode).toString();
				} else {
					edpCode = "";
				}
				Class paybillClass = payBillVO.getClass();
				Method method = null;

				long allwDedId = ((AllwValCustomVO) allowTypeMst.get(i)).getAllwDedId();
				this.logger.info("Allow Deduc Id is " + allwDedId + " for allw Mster Pk is " + allwDedId
						+ " for empId is " + empId);
				this.logger.info("empPaybillVO.getPostCityClass()=" + empPaybillVO.getPostCityClass());
				if (allwDedId != 0L) {
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
						if ((basicForSvnPC.longValue() > 0L)
								&& (((billMonth >= 7) && (billyear == 2017)) || (billyear > 2017))
								&& ((billMonth < 10) && (billyear == 2021))) {
							if (allwDedId == 17L) {
								amt = empSalaryTxn.getAmount();

								Long amount = basicForSvnPC;
								if (cityHRAStr.equals("X")) {
									if (amount.longValue() < 5400L) {
										amt = BigDecimal.valueOf(5400L);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount.longValue())
												.multiply(BigDecimal.valueOf(24L));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
										if (amt.intValue() < 5400) {
											amt = BigDecimal.valueOf(5400L);
										}
									}
								} else if (cityHRAStr.equals("Y")) {
									if (amount.longValue() < 3600L) {
										amt = BigDecimal.valueOf(3600L);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount.longValue())
												.multiply(BigDecimal.valueOf(16L));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
										if (amt.intValue() < 3600) {
											amt = BigDecimal.valueOf(3600L);
										}
									}
								} else if (cityHRAStr.equals("Z")) {
									if (amount.longValue() < 1800L) {
										amt = BigDecimal.valueOf(1800L);
									} else {
										BigDecimal tempDaValNew = BigDecimal.valueOf(amount.longValue())
												.multiply(BigDecimal.valueOf(8L));
										amt = tempDaValNew.divide(BigDecimal.valueOf(100L), 0, 4);
										if (amt.intValue() < 1800) {
											amt = BigDecimal.valueOf(1800L);
										}
									}
								} else {
									this.logger.info("Calculate 7PC DA =======" + amt);
									amt = BigDecimal.valueOf(basicForSvnPC.longValue());
								}
							} else {
								this.logger.info("Calculate HRA=======" + amt);
								amt = empSalaryTxn.getAmount();
							}
						} else if ((basicForSvnPC.longValue() > 0) && (((billMonth >= 10) && (billyear == 2021))
								|| ((billMonth >= 1) && (billyear >= 2022)))) {
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
						}

						/* Added By Naveen Priya Sinha TA 2022 */

						if ((basicForSvnPC.longValue() > 0) && (PaycommissionId.longValue() == 700005)
								&& ((billMonth >= 4) && (billyear >= 2022))) {
							if (allwDedId == 15) {
								// amt = new BigDecimal(0);
								amt = empSalaryTxn.getAmount();
								if (basicForSvnPC > 0 && (cityHRAStr.equals("X"))) {
									if (empPaybillVO.getIsPhyHandicapped().equals("TRUE")) {
										if ((sevenPcLevel >= 1 && sevenPcLevel <= 6) && basicForSvnPC >= 24200) {
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
										if (sevenPcLevel >= 1 && sevenPcLevel <= 6 && basicForSvnPC >= 24200) {
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
						} /* Ended By Naveen Priya Sinha TA 2022 */
						
						//NPS
						/* if ((basicForSvnPC.longValue() > 0) && (PaycommissionId.longValue() == 700005)
								&& ((billMonth >= 4) && (billyear >= 2023))) {
							if (allwDedId == 208) {
								double tempDA = 0.0;
								BigDecimal basicDA;
								Double tempBasic = payBillVO.getBasic0101();
								//HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(208L));
								HrPayEmpSalaryTxn empSalary = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(207L));
								if ((basicForSvnPC.longValue() > 0L) && (billyear >= 2023)) {
									tempDA = empSalary.getAmount().doubleValue();
								} else {
									tempDA = payBillVO.getAllow0103();
								}
								basicDA = new BigDecimal(tempBasic + tempDA);
								emplyrDeducContri = basicDA.multiply(BigDecimal.valueOf(14L));
								amt = emplyrDeducContri.divide(BigDecimal.valueOf(100L), 0, 4);
								logger.info("NPS " + allwDedId + " from gui based rule engine is " + amt);
								
							}
							
						} */
						//NPS 
						
						
					}
					
				} else {
					String methodName = "calculate" + ((AllwValCustomVO) allowTypeMst.get(i)).getAllowDesc();

					Class class1 = null;
					class1 = salaryRuls6thPay.getClass();
					if ((payCommissionId == commissionFiveId) || (payCommissionId == 2500346L)
							|| (payCommissionId == 2500342L)) {
						class1 = salaryRules.getClass();
					}
					try {
						method = class1.getMethod(methodName, new Class[] { Map.class });
						this.logger.info("method found for " + methodName);
					} catch (Exception e) {
						this.logger.info("No Rule found for " + ((AllwValCustomVO) allowTypeMst.get(i)).getAllowDesc());
					}
					if (method != null) {
						if ((payCommissionId == commissionFiveId) || (payCommissionId == 2500346L)
								|| (payCommissionId == 2500342L)) {
							parResult = ((Double) method.invoke(salaryRules, new Object[] { input })).doubleValue();
						} else {
							parResult = ((Double) method.invoke(salaryRuls6thPay, new Object[] { input }))
									.doubleValue();
						}
						parResult = Math.round(parResult);
						this.logger.info("Result is " + parResult + "for allow code" + allowCode + " allow name is "
								+ ((AllwValCustomVO) allowTypeMst.get(i)).getAllowDesc() + " and edp code is "
								+ edpCode);
					}
				}
				if ((basicForSvnPC.longValue() > 0L)
						&& (((billMonth >= 7) && (billyear == 2017)) || (billyear > 2017))) {
					try {
						if (allwDedId == 17L) {
							HrPayEmpSalaryTxn empSalaryTxn = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(17L));
							empSalaryTxn.setAmount(amt);
							ruleMap.put(Long.valueOf(17L), empSalaryTxn);
						} else if (allwDedId == 15L) {
							HrPayEmpSalaryTxn empSalaryTxn = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(15L));
							empSalaryTxn.setAmount(amt);
							ruleMap.put(Long.valueOf(15L), empSalaryTxn);
						}

					} catch (Exception e) {
						this.logger.info("Exeception occured in Core Logic " + e);
						this.logger.error("Error is: " + e.getMessage());
					}
				}
				/** NPS allowances 208 14% employer calculation By Naveen Priya Sinha */
				/*if ((basicForSvnPC.longValue() > 0) && (PaycommissionId.longValue() == 700005)
						&& ((billMonth >= 3) && (billyear >= 2023))) {
					if (allwDedId == 208) {
						double tempDA = 0.0;
						BigDecimal basicDA;
						Double tempBasic = payBillVO.getBasic0101();
						HrPayEmpSalaryTxn empSalaryTxnNew = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(208L));
						HrPayEmpSalaryTxn empSalary = (HrPayEmpSalaryTxn) ruleMap.get(Long.valueOf(207L));
						if ((basicForSvnPC.longValue() > 0L) && (billyear >= 2023)) {
							tempDA = empSalary.getAmount().doubleValue();
						} else {
							tempDA = payBillVO.getAllow0103();
						}
						basicDA = new BigDecimal(tempBasic + tempDA);
						emplyrDeducContri = basicDA.multiply(BigDecimal.valueOf(14L));
						amt = emplyrDeducContri.divide(BigDecimal.valueOf(100L), 0, 4);
						empSalaryTxnNew.setAmount(amt);
						ruleMap.put(Long.valueOf(208L), empSalaryTxnNew);
						logger.info("NPS " + allwDedId + " from gui based rule engine is " + amt);
						
					}
				}*/
				/** NPS allowances 208 14% employer calculation By Naveen Priya Sinha */
				
			}
			Set allowDedSet = ruleMap.keySet();
			Iterator iterator = allowDedSet.iterator();
			Map empValMap = new HashMap();
			while (iterator.hasNext()) {
				HrPayEmpSalaryTxn empSalaryTxn = (HrPayEmpSalaryTxn) ruleMap.get(iterator.next());
				empValMap.put(Long.valueOf(empSalaryTxn.getAllwDedCode()), empSalaryTxn.getAmount());
			}
			Object[] allowDedIdArray = allowDedSet.toArray();
			StringBuffer allowDedStr = new StringBuffer();
			for (int i = 0; i < allowDedIdArray.length; i++) {
				if (i == 0) {
					allowDedStr.append(allowDedIdArray[i]);
				} else {
					allowDedStr.append(",");
					allowDedStr.append(allowDedIdArray[i]);
				}
			}
			List allowCompMapped = objBrokenPeriodDAO.allowComponenentsMapped(strEmpId, 2500134);
			this.logger.info(allowCompMapped);
			List allowId = objBrokenPeriodDAO.getAllowIDfrmAllowdedCode(allowDedStr.toString());
			List dedId = objBrokenPeriodDAO.getDedIDfrmAllowdedCode(allowDedStr.toString());
			int noOfdays = Integer.parseInt(inputMap.get("totalNoOfDays").toString());
			int fractionOfDays = Integer.parseInt(inputMap.get("lIntNoofdays").toString());
			List allowRuleList = new ArrayList();
			List dedRuleList = new ArrayList();
			Iterator it = allowCompMapped.iterator();
			while (it.hasNext()) {
				Object[] allowArr = new Object[2];
				Object[] key = (Object[]) it.next();
				 
				if (empValMap.containsKey(Long.valueOf(Long.parseLong(key[1].toString())))) {
					allowArr[0] = Long.valueOf(Long.parseLong(key[0].toString()));
					allowRuleList.add(allowArr[0]);
					allowArr[1] = empValMap.get(Long.valueOf(Long.parseLong(key[1].toString())));
					BigDecimal allowVal = new BigDecimal(allowArr[1].toString());
					allowVal = allowVal.multiply(BigDecimal.valueOf(fractionOfDays));
					allowVal = allowVal.divide(BigDecimal.valueOf(noOfdays), 0, 4);
					allowRuleList.add(allowVal);
					totalCompAllw += Double.valueOf(allowVal.toString()).doubleValue();
					this.logger.info("allowArr[0]=" + allowArr[0] + "allowArr=" + allowVal);
					this.logger.info("totalCompAllw...." + totalCompAllw);
				}
			}
			it = null;
			it = dedId.iterator();
			while (it.hasNext()) {
				Object[] dedArr = new Object[2];
				Object[] key = (Object[]) it.next();
				
				
				if (empValMap.containsKey(Long.valueOf(Long.parseLong(key[1].toString())))) {
					dedArr[0] = Long.valueOf(Long.parseLong(key[0].toString()));
					dedRuleList.add(dedArr[0]);
					dedArr[1] = empValMap.get(Long.valueOf(Long.parseLong(key[1].toString())));
					BigDecimal dedVal = new BigDecimal(dedArr[1].toString());
					if ((!dedArr[0].toString().equals("101")) && (!dedArr[0].toString().equals("86"))) {
						dedVal = dedVal.multiply(BigDecimal.valueOf(fractionOfDays));
						dedVal = dedVal.divide(BigDecimal.valueOf(noOfdays), 0, 4);
					}
					dedRuleList.add(dedVal);
					this.logger.info("dedRuleList[0]=" + dedArr[0] + "dedArr=" + dedVal);
				}
			}
			double totalNonCompAllw = 0.0D;
			Map<Long, List<AllwValCustomVO>> mapEditableAllowance = (Map) inputMap.get("mapEditableAllowance");

			List<AllwValCustomVO> customVo = (List) mapEditableAllowance.get(Long.valueOf(empid));
			mapEditableAllowance = null;
			double Val = 0.0D;
			String edp = "";
			if (customVo != null) {
				for (int i = 0; i < customVo.size(); i++) {
					Val = 0.0D;
					long compoId = ((AllwValCustomVO) customVo.get(i)).getAllwID();
					Val = ((AllwValCustomVO) customVo.get(i)).getAllowanceVal();
					Val = Math.round(Val);
					allowRuleList.add(Long.valueOf(compoId));
					BigDecimal allowVal = new BigDecimal(Val);
					allowVal = allowVal.multiply(BigDecimal.valueOf(fractionOfDays));
					allowVal = allowVal.divide(BigDecimal.valueOf(noOfdays), 0, 4);
					allowRuleList.add(allowVal);
				}
			}
			customVo = null;

			this.logger.info("total of noncomputaional Allowances is " + totalNonCompAllw);

			double hrr = 0.0D;
			double gross = totalCompAllw + totalNonCompAllw
					+ Math.round(((Double) input.get("revBasic")).doubleValue());
			List<DeductionCustomVO> deducTypeMst = new ArrayList();

			int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
			int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());
			Calendar tempCalGiven = Calendar.getInstance();
			tempCalGiven.set(1, yearGiven);
			tempCalGiven.set(2, monthGiven - 1);
			tempCalGiven.set(5, 1);
			Date givenDate = tempCalGiven.getTime();
			BrokenPeriodDAOImpl brokenDao = new BrokenPeriodDAOImpl(null, serv.getSessionFactory());

			List empQtrRentList = brokenDao.getQtrAmount(strEmpId, givenDate);
			this.logger.info("Quater Fetching Query End time" + System.currentTimeMillis());
			Map qtrMap = new HashMap();
			String qtrRent = null;
			for (int qtrCounter = 0; qtrCounter < empQtrRentList.size(); qtrCounter++) {
				Object[] row = (Object[]) empQtrRentList.get(qtrCounter);
				long leisEmpId = row[0] != null ? Long.valueOf(row[0].toString()).longValue() : 0L;
				qtrRent = row[1] != null ? row[1].toString() : "0";
				if (!qtrMap.containsKey(Long.valueOf(leisEmpId))) {
					qtrMap.put(Long.valueOf(leisEmpId), qtrRent);
				}
			}
			hrr = qtrRent != null ? Long.parseLong(qtrRent) : 0L;
			this.logger.info("hrr value from EmpDeducDtls is " + hrr);
			dedRuleList.add("28");
			double roundedHrr = hrr * fractionOfDays / noOfdays;
			dedRuleList.add(Long.valueOf(Math.round(roundedHrr)));
			long deducEmpId = empPaybillVO.getOrgEmpId();

			Object isPtMapped = inputMap.get("isPTMapped");
			if ((isPtMapped != null) && (Integer.parseInt(isPtMapped.toString()) > 0)) {
				inputRuleEngine.put(Integer.valueOf(15),
						BigDecimal.valueOf(gross).divide(BigDecimal.valueOf(1L), 0, 4));
				double pt = 0.0D;
				BigDecimal ptVal = null;
				this.logger.info("gross iss........." + gross);
				if (gross >= 10000.0D) {
					ptVal = new BigDecimal(200);
				} else {
					ptVal = new BigDecimal(175);
				}
				if (ptVal.compareTo(BigDecimal.ZERO) >= 0) {
					pt = ptVal.doubleValue();
					this.logger.info("Pt i got from rule engine is:" + pt);
				} else {
					if ((payCommissionId == commissionFiveId) || (payCommissionId == commissionSevenId)) {
						pt = salaryRules.calculatePT(input);
					} else if ((payCommissionId == sixthPayCommId) || (payCommissionId == commissionFourId)
							|| (payCommissionId == commissionThreeId) || (payCommissionId == commissionTwoId)
							|| (payCommissionId == commissionOneId)) {
						pt = salaryRuls6thPay.calculatePT(input);
					}
					this.logger.info("Pt i got from excel is " + pt);
				}
				if ((billMonth == 2) && (pt == 200.0D)) {
					pt = 300.0D;
				}
				this.logger.info("Core Logic PerEmployee End Time" + System.currentTimeMillis());
				dedRuleList.add("35");

				dedRuleList.add(Double.valueOf(pt));
			}
			Map<Long, List<DeductionCustomVO>> mapEditableDeduction = (Map) inputMap.get("mapEditableDeduction");
			List<DeductionCustomVO> customVO = (List) mapEditableDeduction.get(Long.valueOf(empid));
			mapEditableDeduction = null;
			if (customVO != null) {
				for (int i = 0; i < customVO.size(); i++) {
					Val = 0.0D;
					BigDecimal dedVal = new BigDecimal(Val);
					long compoId = ((DeductionCustomVO) customVO.get(i)).getDeducId();
					this.logger.info("compoId:::" + compoId);
					if (compoId != hrrId) {
						Val = ((DeductionCustomVO) customVO.get(i)).getDeductionVal();

						Val = Math.round(Val);
						if (((compoId == 72L) || (compoId == 36L) || (compoId == 76L) || (compoId == 75L)
								|| (compoId == 78L) || (compoId == 77L))
								&& (!isGPFApplicable(billMonth, billyear, empPaybillVO.getEmpSrvcExp()))) {
							Val = 0.0D;
						}
						dedVal = dedVal.multiply(BigDecimal.valueOf(fractionOfDays));
						dedVal = dedVal.divide(BigDecimal.valueOf(noOfdays), 0, 4);
					}
					dedRuleList.add(Long.valueOf(compoId));
					dedRuleList.add(dedVal);
				}
			}
			double basicAmt = Double.valueOf(inputMap.get("basicAmt").toString()).doubleValue();
			this.logger.info("basicAmt b4 round =" + basicAmt);
			basicAmt = basicAmt * fractionOfDays / noOfdays;
			resultMap.put("basicAmt", Long.valueOf(Math.round(basicAmt)));
			resultMap.put("dedRuleList", dedRuleList);
			resultMap.put("allowRuleList", allowRuleList);
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error("Error occured in generateBillservice corelogic: " + e.getMessage());
			throw new Exception(e);
		}
		return resultMap;
	}

	public Map generatePassMap(Map inputMap) {
		Map passMap = new HashMap();

		EmpPaybillVO empPaybillVO = (EmpPaybillVO) inputMap.get("hrEisOtherDtls");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResourceBundle constant = ResourceBundle.getBundle("resources.eis.eis_Constants");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long sixthPayCommId = Long.parseLong(resourceBundle.getString("commissionSixId"));

		long commissionFourId = Long.parseLong(resourceBundle.getString("commissionFourId"));
		long commissionThreeId = Long.parseLong(resourceBundle.getString("commissionThreeId"));
		long commissionTwoId = Long.parseLong(resourceBundle.getString("commissionTwoId"));
		long commissionOneId = Long.parseLong(resourceBundle.getString("commissionOneId"));

		int contractEmpType = Integer.parseInt(resourceBundle.getString("contract"));
		int fixedEmpType = Integer.parseInt(resourceBundle.getString("fixed"));
		boolean fullmonthcal = Boolean.parseBoolean(constant.getString("fullMonthCalculation"));

		int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
		int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());

		passMap.put("serviceLocator", serv);
		passMap.put("month", Integer.valueOf(monthGiven));
		passMap.put("year", Integer.valueOf(yearGiven));
		passMap.put("fullMonthCal", Boolean.valueOf(fullmonthcal));

		Calendar calGiven = Calendar.getInstance();
		calGiven.set(1, yearGiven);
		calGiven.set(2, monthGiven - 1);
		calGiven.set(5, 1);

		int maxDay = calGiven.getActualMaximum(5);
		passMap.put("maxDaysMonth", Integer.valueOf(maxDay));

		long empType = empPaybillVO.getEmpType();
		this.logger.info("empType is" + empType);
		passMap.put("empType", Long.valueOf(empType));
		long grade = empPaybillVO.getGradeId();
		passMap.put("grade", Long.valueOf(grade));
		long Designation = 0L;

		long scale_start_amt = 0L;
		long scale_end_amt = 0L;

		String jdcpsId = resourceBundle.getString("judgeIds");
		String[] list = jdcpsId.split(",");

		boolean isJudge = false;
		if ((empType != contractEmpType) && (empType != fixedEmpType)) {
			Designation = empPaybillVO.getDesigId();
			this.logger.info("Designation   is **********" + Designation);
			for (int k = 0; k < list.length; k++) {
				this.logger.info("first dsgnid is **********" + list[k]);
				if (Designation == (int) Long.parseLong(list[k])) {
					isJudge = true;
					break;
				}
			}
			this.logger.info("isJudge   is **********" + isJudge);
			scale_start_amt = empPaybillVO.getScaleStartAmt();
			scale_end_amt = empPaybillVO.getScaleEndAmt();
		} else {
			Designation = 0L;
			scale_start_amt = empPaybillVO.getScaleStartAmt();
			scale_end_amt = empPaybillVO.getScaleEndAmt();
		}
		this.logger.info("isJudge out if else  is **********" + isJudge);
		passMap.put("isJudge", Boolean.valueOf(isJudge));
		passMap.put("designation", Long.valueOf(Designation));
		passMap.put("scaleStartAmt", Long.valueOf(scale_start_amt));
		passMap.put("scaleEndAmt", Long.valueOf(scale_end_amt));

		long CurrBasic = empPaybillVO.getBasicAmt();

		passMap.put("basic", Long.valueOf(CurrBasic));

		int daysOfPost = maxDay;

		passMap.put("daysOfPost", Integer.valueOf(daysOfPost));

		double revisedBasic = Math.round((float) (CurrBasic * daysOfPost / maxDay));
		passMap.put("revBasic", Double.valueOf(revisedBasic));
		if ((empPaybillVO.getGradeId() != 0L) && (empPaybillVO.getEisEmpId() != 0L)) {
			if ((empPaybillVO.getGisGradeCode() != 0L) && (empPaybillVO.getGisGradeId() != 0L)) {
				passMap.put("groupCode", Long.valueOf(empPaybillVO.getGisGradeCode()));
				passMap.put("groupId", Long.valueOf(empPaybillVO.getGisGradeId()));
			} else {
				passMap.put("groupCode", Long.valueOf(empPaybillVO.getGradeCode()));
				passMap.put("groupId", Long.valueOf(empPaybillVO.getGradeId()));
			}
		} else {
			passMap.put("groupCode", Integer.valueOf(0));
			passMap.put("groupId", Integer.valueOf(0));
		}
		int isAvailedHRA = empPaybillVO.getIsAvailedHRA();
		passMap.put("isAvailedHRA", Integer.valueOf(isAvailedHRA));

		Date doj = empPaybillVO.getEmpDOJ();
		passMap.put("doj", doj);
		SalaryRules rules = new SalaryRules();
		long gradePay = 0L;
		boolean isHandicapped = false;
		this.logger
				.info("empPaybillVO.getIsPhyHandicapped()******ROSHAN*****-----" + empPaybillVO.getIsPhyHandicapped());
		if ((empPaybillVO.getIsPhyHandicapped() != null) && (!empPaybillVO.getIsPhyHandicapped().equals(""))) {
			isHandicapped = Boolean.parseBoolean(empPaybillVO.getIsPhyHandicapped().toLowerCase());
			this.logger.info("isHandicapped-----" + isHandicapped);
			passMap.put("isHandicapped", Boolean.valueOf(isHandicapped));
		}
		double DP = 0.0D;
		long payCommissionId = empPaybillVO.getPayCommissionId();
		if ((payCommissionId == sixthPayCommId) || (payCommissionId == commissionFourId)
				|| (payCommissionId == commissionThreeId) || (payCommissionId == commissionTwoId)
				|| (payCommissionId == commissionOneId)) {
			gradePay = empPaybillVO.getGradePay();
			passMap.put("basicAndDP", Double.valueOf(revisedBasic + DP));
		} else if (payCommissionId != 0L) {
			gradePay = 0L;
			Map DPMap = new HashMap();
			DPMap.put("empType", Long.valueOf(empType));
			DPMap.put("revBasic", Double.valueOf(revisedBasic));
			DP = Math.round(rules.calculateDP(DPMap));

			DPMap = null;

			passMap.put("DP", Double.valueOf(DP));
			passMap.put("basicAndDP", Double.valueOf(revisedBasic + DP));
		} else {
			gradePay = 0L;
		}
		passMap.put("gradePay", Long.valueOf(gradePay));

		String city = "";
		if ((empPaybillVO.getPostCityClass() != null) && (empPaybillVO.getPostCityClass().trim() != "")) {
			city = empPaybillVO.getPostCityClass();
		}
		if (city != null) {
			if (city.length() > 10) {
				passMap.put("city",
						city.substring(10, city.length()) != null ? city.substring(10, city.length()) : "A");
			} else {
				passMap.put("city", "A");
			}
		}
		passMap.put("isAvailForce100", Integer.valueOf(0));
		passMap.put("isAvailForce25", Integer.valueOf(0));
		passMap.put("isAvailATS30", Integer.valueOf(0));
		passMap.put("isAvailATS50", Integer.valueOf(0));
		passMap.put("quarterId", Integer.valueOf(0));
		passMap.put("isVehicleAvail", "FALSE");
		passMap.put("distance", Integer.valueOf(5));
		this.logger.info("From core logic passing in the map " + isHandicapped + " " + city + " " + gradePay + " "
				+ daysOfPost + " " + maxDay + " " + fullmonthcal);
		return passMap;
	}

	public Map generatePassMapForRuleEngine(Map inputMap) {
		Map passMap = new HashMap();

		EmpPaybillVO empPaybillVO = (EmpPaybillVO) inputMap.get("hrEisOtherDtls");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

		Map<Integer, Object> paramValueMap = new HashMap();
		paramValueMap.put(Integer.valueOf(3), Long.valueOf(empPaybillVO.getGradeCode()));
		if (empPaybillVO.getGisGradeCode() != 0L) {
			paramValueMap.put(Integer.valueOf(3), Long.valueOf(empPaybillVO.getGisGradeCode()));
		}
		paramValueMap.put(Integer.valueOf(6), Long.valueOf(empPaybillVO.getDesigId()));
		if ((empPaybillVO.getIsPhyHandicapped() != null) && (empPaybillVO.getIsPhyHandicapped().equals("TRUE"))) {
			paramValueMap.put(Integer.valueOf(60), Integer.valueOf(1));
		} else {
			paramValueMap.put(Integer.valueOf(60), Integer.valueOf(0));
		}
		this.logger.info("Physically Handicapped is " + paramValueMap.get(Integer.valueOf(60)));
		String city = empPaybillVO.getPostCityClass();
		String cityLookup = "";

		cityLookup = (city != null) && (city.length() > 10) && (city.substring(10, city.length()) != null)
				? city.substring(10, city.length())
				: "A";

		cityLookup = cityLookup + " City Ctgry";
		this.logger.info("city Look up is " + cityLookup);
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
		List<CmnLookupMst> list = cmnLookupMstDAOImpl.getListByColumnAndValue("lookupShortName", cityLookup);
		paramValueMap.put(Integer.valueOf(17), Long.valueOf(((CmnLookupMst) list.get(0)).getLookupId()));
		this.logger.info("city Look up id is " + ((CmnLookupMst) list.get(0)).getLookupId());
		paramValueMap.put(Integer.valueOf(5), Long.valueOf(empPaybillVO.getBasicAmt()));
		paramValueMap.put(Integer.valueOf(55), Long.valueOf(empPaybillVO.getGradePay()));
		paramValueMap.put(Integer.valueOf(13), Long.valueOf(empPaybillVO.getScaleId()));
		paramValueMap.put(Integer.valueOf(14), Long.valueOf(empPaybillVO.getPayCommissionId()));
		if ((empPaybillVO.getPayCommissionId() == 2500343L) || (empPaybillVO.getPayCommissionId() == 2500344L)
				|| (empPaybillVO.getPayCommissionId() == 2500345L)) {
			paramValueMap.put(Integer.valueOf(14), Integer.valueOf(2500341));
		}
		if (empPaybillVO.getPayCommissionId() == 2500346L) {
			paramValueMap.put(Integer.valueOf(14), Integer.valueOf(2500340));
		}
		String yearDoj = "" + empPaybillVO.getEmpDOJ();
		this.logger.info("yearDoj is...... " + yearDoj);
		if ((yearDoj != null) && (!yearDoj.equals("null"))) {
			String monthDOJ = yearDoj.substring(5, 7);
			String dayDOJ = yearDoj.substring(8, 10);
			String yearDOJ = yearDoj.substring(0, 4);
			this.logger.info(Integer.valueOf(new Date().getYear() + 1900));
			paramValueMap.put(Integer.valueOf(16), yearDOJ);
			if ((new Date().getYear() + 1900 != Integer.parseInt(yearDOJ))
					|| ((new Date().getYear() + 1900 == Integer.parseInt(yearDOJ)) && (Integer.parseInt(monthDOJ) == 1)
							&& (Integer.parseInt(dayDOJ) == 1))) {
				paramValueMap.put(Integer.valueOf(16), Integer.valueOf(1));
			}
			this.logger.info("required year is " + empPaybillVO.getEmpDOJ());
		}
		int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
		int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());
		if (yearGiven > 2014) {
			paramValueMap.put(Integer.valueOf(62), Integer.valueOf(2014));
			paramValueMap.put(Integer.valueOf(63), Integer.valueOf(4));
		} else if ((yearGiven == 2014) && (monthGiven >= 4) && (empPaybillVO.getPayCommissionId() == 2500341L)) {
			paramValueMap.put(Integer.valueOf(62), Integer.valueOf(2014));
			paramValueMap.put(Integer.valueOf(63), Integer.valueOf(4));
		} else if ((yearGiven == 2014) && (monthGiven >= 4) && (empPaybillVO.getPayCommissionId() == 2500340L)) {
			paramValueMap.put(Integer.valueOf(62), Integer.valueOf(2014));
			paramValueMap.put(Integer.valueOf(63), Integer.valueOf(1));
		} else if ((yearGiven == 2014) && (monthGiven < 4)) {
			paramValueMap.put(Integer.valueOf(62), Integer.valueOf(2014));
			paramValueMap.put(Integer.valueOf(63), Integer.valueOf(1));
		} else if (yearGiven < 2014) {
			paramValueMap.put(Integer.valueOf(62), Integer.valueOf(2014));
			paramValueMap.put(Integer.valueOf(63), Integer.valueOf(1));
		}
		return paramValueMap;
	}

	private boolean isGPFApplicable(int givenMonth, int givenYear, Date serviceExpDate) {
		Calendar calc = Calendar.getInstance();
		calc.setTime(serviceExpDate);
		calc.add(2, -3);
		Date allowedGPFDate = calc.getTime();
		this.logger.info("givenYear=" + givenYear + " givenMonth=" + givenMonth + " allowedGPFDate.getMonth()"
				+ allowedGPFDate.getMonth() + " <allowedGPFDate.getYear()" + allowedGPFDate.getYear());
		if (givenYear == allowedGPFDate.getYear() + 1900) {
			if (givenMonth <= allowedGPFDate.getMonth() + 1) {
				return true;
			}
			return false;
		}
		if (givenYear < allowedGPFDate.getYear() + 1900) {
			return true;
		}
		return false;
	}

	public Map generateEmpDeducCompoMap(List empAllowCompoList) {
		Map empAllowCompoMap = new HashMap();
		List<AllwValCustomVO> empAllwCompoLst = new ArrayList();
		long prevEmpId = 0L;
		if ((empAllowCompoList != null) && (empAllowCompoList.size() > 0)) {
			int size = empAllowCompoList.size();
			Object[] row = null;
			for (int i = 0; i < size; i++) {
				row = (Object[]) empAllowCompoList.get(i);
				if (row != null) {
					long empId = row[0] != null ? Long.valueOf(row[0].toString()).longValue() : 0L;
					if (prevEmpId == 0L) {
						prevEmpId = empId;
					}
					if (prevEmpId != empId) {
						this.logger.info("Prev emp id is " + prevEmpId + " new Emp Id is " + empId);
						this.logger.info("empAllwCompoLst is " + empAllwCompoLst);

						empAllowCompoMap.put(Long.valueOf(prevEmpId), empAllwCompoLst);
						empAllwCompoLst = new ArrayList();
						prevEmpId = empId;
					}
					if (i == empAllowCompoList.size() - 1) {
						empAllowCompoMap.put(Long.valueOf(empId), empAllwCompoLst);
					}
					long allowId = row[1] != null ? Long.valueOf(row[1].toString()).longValue() : 0L;
					String allowDesc = row[2] != null ? String.valueOf(row[2].toString()) : "";
					long allwDedId = row[3] != null ? Long.valueOf(row[3].toString()).longValue() : 0L;
					AllwValCustomVO allwCustomVO = new AllwValCustomVO();
					if ((empId != 0L) && (allowId != 0L)) {
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

	public ResultObject calculateEmployeeSalary(Map<String, Object> inputMap) {
		ServiceLocator lObjServLoctr = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject(0, "FAIL");
		try {
			setSessionInfo(inputMap);

			int lLongYear = Integer.valueOf(StringUtility.getParameter("year", this.request).trim()).intValue();
			int lLongMonth = Integer.valueOf(StringUtility.getParameter("month", this.request).trim()).intValue();
			Long lLongEisEmpId = Long.valueOf(StringUtility.getParameter("eisEmpId", this.request).trim());
			int lIntNoofdays = Integer.valueOf(StringUtility.getParameter("noOfDays", this.request).trim()).intValue();
			PayBillDAOImpl paydao = new PayBillDAOImpl(null, this.serv.getSessionFactory());
			inputMap.put("lLongHrEisEmpId", lLongEisEmpId);
			inputMap.put("monthId", Integer.valueOf(lLongMonth));
			inputMap.put("yearGiven", Integer.valueOf(lLongYear));
			inputMap.put("monthGiven", Integer.valueOf(lLongMonth));
			Calendar cal2 = Calendar.getInstance();
			cal2.set(1, lLongYear);
			cal2.set(2, lLongMonth - 1);
			Date finYrDate = cal2.getTime();
			int totalNoOfDays = cal2.getActualMaximum(5);
			cal2 = null;
			this.logger
					.info("lLongMonth=" + lLongMonth + " lLongYear=" + lLongYear + " totalNoOfDays=" + totalNoOfDays);
			SgvcFinYearMst finYrMst = paydao.getFinYrInfo(finYrDate, Long.valueOf(1L));
			inputMap.put("totalNoOfDays", Integer.valueOf(totalNoOfDays));
			inputMap.put("lIntNoofdays", Integer.valueOf(lIntNoofdays));

			this.logger.info("finYrMst.getFinYearId()***************" + finYrMst.getFinYearId());
			inputMap.put("yearId", Long.valueOf(finYrMst.getFinYearId()));
			inputMap.put("monthId", Integer.valueOf(lLongMonth));
			Map ruleValueMap = getRuleValues(lLongEisEmpId.toString(), inputMap, finYrMst.getFinYearId());
			List dedRuleList = (ArrayList) ruleValueMap.get("dedRuleList");
			List allowRuleList = (ArrayList) ruleValueMap.get("allowRuleList");
			String basicAmt = ruleValueMap.get("basicAmt").toString();
			StringBuilder lStrBldXML = new StringBuilder();
			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<dedRuleList>");
			lStrBldXML.append("<![CDATA[");
			lStrBldXML.append(dedRuleList);
			lStrBldXML.append("]]>");
			lStrBldXML.append("</dedRuleList>");
			lStrBldXML.append("<allowRuleList>");
			lStrBldXML.append("<![CDATA[");
			lStrBldXML.append(allowRuleList);
			lStrBldXML.append("]]>");
			lStrBldXML.append("</allowRuleList>");
			lStrBldXML.append("<basicAmt>");
			lStrBldXML.append("<![CDATA[");
			lStrBldXML.append(basicAmt);
			lStrBldXML.append("]]>");
			lStrBldXML.append("</basicAmt>");
			lStrBldXML.append("</XMLDOC>");
			String lStrTempResult = null;
			this.logger.info("ajax res " + lStrBldXML.toString());
			lStrTempResult = new AjaxXmlBuilder().addItem("ajax_Key", lStrBldXML.toString()).toString();
			inputMap.put("ajaxKey", lStrTempResult);
			resObj.setResultCode(0);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
			this.logger.error(" Error is : " + e, e);
		}
		return resObj;
	}
}
