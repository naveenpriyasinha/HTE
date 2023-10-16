/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Aug 16, 2011		Shivani Rana								
 *******************************************************************************
 */
package com.tcs.sgv.pensionpay.report;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.common.valueobject.MstBank;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.pensionpay.dao.CommonPensionDAO;
import com.tcs.sgv.pensionpay.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionRqstHdr;

/**
 * Class Description -
 * 
 * 
 * @author 365450
 * @version 0.1
 * @since JDK 5.0 Aug 16, 2011
 */
public class PensionpayReportDAOImpl extends DefaultReportDataFinder implements ReportDataFinder {

	private static final Logger gLogger = Logger.getLogger("PensionpayReports");
	private PensionpayQueryDAO gObjRptQueryDAO = null;
	ServiceLocator serviceLocator = null;
	SessionFactory gObjSessionFactory = null;
	String gStrLocCode = null;
	Long gLngLangId = null;
	Map lMapSeriesHeadCode = null;
	Session ghibSession = null;

	/**
	 * Global Variable for Resource Bundle
	 */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionpay/PensionCaseConstants");

	private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pensionpay/PensionConstants");

	public Collection findReportData(ReportVO lObjReport, Object criteria) throws ReportException {

		List lLstDataList = new ArrayList();
		Map lMapRequestAttributes = null;
		Map lMapSessionAttributes = null;
		LoginDetails lObjLoginVO = null;
		try {
			lMapRequestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			lMapSessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
			lObjLoginVO = (LoginDetails) lMapSessionAttributes.get("loginDetails");
			serviceLocator = (ServiceLocator) lMapRequestAttributes.get("serviceLocator");
			gObjSessionFactory = serviceLocator.getSessionFactorySlave();
			gStrLocCode = lObjLoginVO.getLocation().getLocationCode();
			gLngLangId = lObjLoginVO.getLangId();
			ghibSession = gObjSessionFactory.getCurrentSession();
			gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);

			gObjRptQueryDAO.setReportHeaderAndFooter(lObjReport, lObjLoginVO);

			if (lObjReport.getReportCode().equals("365455")) {
				lLstDataList = getPensionCaseTrackingReport(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365458")) {
				lLstDataList = getPensionAllocationReportData(lObjReport, gLngLangId, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365456")) {
				lLstDataList = getBillTrackingReport(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365462")) {
				lLstDataList = getBankBranchWisePensionerCount(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365463")) {
				lLstDataList = getPensionerCountMonthWise(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365464")) {
				lLstDataList = getArrearPaymentDtls(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365466")) {
				lLstDataList = getSixPayArrearDtls(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365465")) {
				lLstDataList = getRecoveryReport(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365467")) {
				lLstDataList = getFirstPmntCases(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365469")) {
				lLstDataList = getArchivedCases(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365470")) {
				lLstDataList = getSchemeWisePaymentDtls(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365471")) {
				lLstDataList = getBankWisePaymentDtls(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365472")) {
				lLstDataList = getBranchWisePaymentDtls(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365473")) {
				lLstDataList = getAGFirstPayStatement(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365475")) {
				lLstDataList = getCVPPaymentDtls(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365476")) {
				lLstDataList = getDCRGPaymentDtls(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365477")) {
				lLstDataList = getConsolidatedAuditorwiseRpt(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365478")) {
				lLstDataList = getChangeStatementRpt(lObjReport, gStrLocCode);
			}
		} catch (Exception e) {
			gLogger.info("findReportData(): Exception is" + e, e);
		}
		return lLstDataList;
	}

	/**
	 * 
	 * 
	 * 
	 * <H3>Method to get allocation report data -</H3>
	 * 
	 * @author 365450
	 * @param lObjReport
	 * @param lLngLangId
	 * @param lStrLocCode
	 * @return
	 */
	public List getPensionAllocationReportData(ReportVO lObjReport, Long lLngLangId, String lStrLocCode) {

		String lStrBranchCode = null;
		String lStrHeadCode = null;
		String lStrForMonth = null;
		String lStrForYear = null;
		Integer lIntMonthYear = null;
		List<TrnPensionBillDtls> lLstRptData = null;
		List dataList = new ArrayList();
		List rowList = null;
		Double lDblTotalAllocAmount = 0.0;
		Map<BigDecimal, String> lMapHeadCodeSeries = new HashMap<BigDecimal, String>();
		CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(gObjSessionFactory);
		try {
			lStrForMonth = (String) lObjReport.getParameterValue("forMonth");
			lStrForYear = (String) lObjReport.getParameterValue("forYear");
			lStrBranchCode = (String) lObjReport.getParameterValue("branchCode");
			lStrHeadCode = (String) lObjReport.getParameterValue("headCode");
			lMapHeadCodeSeries = lObjCommonPensionDAO.getAllHeadCodeSeriesMap();
			String lStrMonthYear = null;
			if (Integer.parseInt(lStrForMonth) < 10) {
				lStrMonthYear = lStrForYear + "0" + lStrForMonth;
			} else {
				lStrMonthYear = lStrForYear + lStrForMonth;
			}
			if (lStrMonthYear != null) {
				lIntMonthYear = Integer.valueOf(lStrMonthYear);
			}

			gObjRptQueryDAO = new PensionpayQueryDAOImpl(TrnPensionBillDtls.class, gObjSessionFactory);
			lLstRptData = gObjRptQueryDAO.getPensionAllocationDetails(lIntMonthYear, lStrBranchCode, lStrHeadCode);
			if (lLstRptData != null && !lLstRptData.isEmpty()) {
				for (TrnPensionBillDtls lObjTrnPensionBillDtls : lLstRptData) {
					rowList = new ArrayList();
					rowList.add(((lObjTrnPensionBillDtls.getLedgerNo() != null) ? lObjTrnPensionBillDtls.getLedgerNo() : "") + " / "
							+ ((lObjTrnPensionBillDtls.getPageNo() != null) ? lObjTrnPensionBillDtls.getPageNo() : ""));
					rowList.add(lObjTrnPensionBillDtls.getPpoNo());
					rowList.add(lObjTrnPensionBillDtls.getPensionerName());
					rowList.add(lObjTrnPensionBillDtls.getAllcationBf1436().intValue());
					rowList.add(lObjTrnPensionBillDtls.getAllcationBf11156().intValue());
					rowList.add(lObjTrnPensionBillDtls.getAllcationAf11156().intValue());
					rowList.add(lObjTrnPensionBillDtls.getAllcationAf10560().intValue());
					rowList.add(lObjTrnPensionBillDtls.getAllcationAfZp().intValue());
					lDblTotalAllocAmount = lObjTrnPensionBillDtls.getAllcationBf1436().doubleValue() + lObjTrnPensionBillDtls.getAllcationBf11156().doubleValue()
							+ lObjTrnPensionBillDtls.getAllcationAf11156().doubleValue() + lObjTrnPensionBillDtls.getAllcationAf10560().doubleValue()
							+ lObjTrnPensionBillDtls.getAllcationAfZp().doubleValue();
					rowList.add(lDblTotalAllocAmount.intValue());
					rowList.add(lMapHeadCodeSeries.get(lObjTrnPensionBillDtls.getHeadCode()));
					dataList.add(rowList);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return dataList;
	}

	public List<ComboValuesVO> getBankList(Hashtable otherArgs, String lStrLangId, String lStrLocCode) {

		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");
		Long lLngLangId = null;
		if (loginMap.containsKey("langId")) {
			lLngLangId = (Long) loginMap.get("langId");
		}
		List<Object[]> lLstResult = new ArrayList<Object[]>();
		List<ComboValuesVO> lLstBanks = new ArrayList<ComboValuesVO>();
		ComboValuesVO lObjComboValueVO = null;

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append("select mb.bankCode, mb.bankName ");
		lSBQuery.append("from MstBank mb where ");
		lSBQuery.append("mb.langId=:langId and mb.activateFlag=:activeFlag order by mb.bankName");

		try {
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setLong("langId", lLngLangId);
			lQuery.setParameter("activeFlag", Long.valueOf(1));

			lLstResult = lQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {
				for (Object[] lArrObj : lLstResult) {
					lObjComboValueVO = new ComboValuesVO();
					lObjComboValueVO.setId(lArrObj[0].toString());
					lObjComboValueVO.setDesc(lArrObj[1].toString());
					lLstBanks.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}

		return lLstBanks;
	}

	public List<ComboValuesVO> getBranchListFromBankCode(String lStrBankCode, Hashtable otherArgs, String lStrLangId, String lStrLocId) {

		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");
		Long lLngLangId = null;
		String lStrLocCode = "";
		if (loginMap.containsKey("langId")) {
			lLngLangId = (Long) loginMap.get("langId");
		}
		if (loginMap.containsKey("locationId")) {
			lStrLocCode = loginMap.get("locationId").toString();
		}
		List<ComboValuesVO> lLstBankBrank = new ArrayList<ComboValuesVO>();
		StringBuilder lSBQuery = new StringBuilder();
		List<Object[]> lLstResult = new ArrayList<Object[]>();
		ComboValuesVO lObjComboValueVO = null;
		try {
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			lSBQuery.append("SELECT branchCode,branchName ");
			lSBQuery.append("FROM RltBankBranch  WHERE ");
			lSBQuery.append("bankCode =:bankCode AND langId =:langId AND locationCode= :locationCode order by branchName");

			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setLong("langId", lLngLangId);
			hqlQuery.setLong("bankCode", Long.valueOf(lStrBankCode));
			hqlQuery.setString("locationCode", lStrLocCode);
			hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
			lLstResult = hqlQuery.list();
			if (lLstResult != null && lLstResult.size() > 0) {
				for (Object[] lArrObj : lLstResult) {
					lObjComboValueVO = new ComboValuesVO();
					lObjComboValueVO.setId(lArrObj[0].toString());
					lObjComboValueVO.setDesc(lArrObj[1].toString());
					lLstBankBrank.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
		}
		return lLstBankBrank;
	}

	public List<ComboValuesVO> getAuditorList(Hashtable otherArgs, String lStrLangId, String lStrLocCode) {

		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");
		List<ComboValuesVO> lLstAuditors = new ArrayList<ComboValuesVO>();
		List<Object[]> lLstResult = new ArrayList<Object[]>();
		ComboValuesVO lObjComboValueVO = null;

		try {
			if (loginMap.containsKey("locationId")) {
				lStrLocCode = loginMap.get("locationId").toString();
			}

			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			StringBuffer lSBBillQry = new StringBuffer();
			lSBBillQry.append("SELECT oup.orgPostMstByPostId.postId,concat(concat(concat(concat(oem.empFname,' '),oem.empMname),' '),oem.empLname) \n");
			lSBBillQry.append(" FROM AclRoleMst arm,AclPostroleRlt apr,OrgUserpostRlt oup,OrgEmpMst oem, OrgPostMst opm \n");
			lSBBillQry.append(" WHERE arm.roleId=apr.aclRoleMst.roleId \n");

			lSBBillQry.append("AND oup.orgPostMstByPostId.postId=apr.orgPostMst.postId \n");
			lSBBillQry.append("AND oup.orgUserMst.userId = oem.orgUserMst.userId \n");
			lSBBillQry.append("AND oup.orgPostMstByPostId.postId=opm.postId\n");
			lSBBillQry.append("AND arm.roleId=:roleId \n");
			lSBBillQry.append("AND opm.locationCode=:locationCode \n");
			lSBBillQry.append("order by 1 \n");

			Query hqlQuery = ghibSession.createQuery(lSBBillQry.toString());
			hqlQuery.setLong("locationCode", Long.parseLong(lStrLocCode));
			hqlQuery.setLong("roleId", Long.parseLong(bundleConst.getString("AUDITOR.ROLEID")));
			hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
			lLstResult = hqlQuery.list();
			if (lLstResult != null && lLstResult.size() > 0) {
				for (Object[] lArrObj : lLstResult) {
					lObjComboValueVO = new ComboValuesVO();
					lObjComboValueVO.setId(lArrObj[0].toString());
					lObjComboValueVO.setDesc(lArrObj[1].toString());
					lLstAuditors.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
		}
		return lLstAuditors;

	}

	public List<ComboValuesVO> getPensionCategoryList(String lStrLangId, String lStrLocCode) {

		StringBuffer lSBQuery = new StringBuffer();
		List<Object[]> lLstResult = new ArrayList<Object[]>();
		List<ComboValuesVO> lLstPnsnCategory = new ArrayList<ComboValuesVO>();
		ComboValuesVO lObjComboValusVO = null;
		try {
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			lSBQuery.append("Select \n");
			lSBQuery.append("headCode,series \n");
			lSBQuery.append("from \n");
			lSBQuery.append("MstPensionHeadcode  \n");

			Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			lLstResult = hqlQuery.list();

			for (Object[] lArrObj : lLstResult) {
				lObjComboValusVO = new ComboValuesVO();
				if (lArrObj[0] != null && lArrObj[1] != null) {
					lObjComboValusVO.setId(lArrObj[0].toString());
					lObjComboValusVO.setDesc(lArrObj[1].toString());
					lLstPnsnCategory.add(lObjComboValusVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
		}
		return lLstPnsnCategory;
	}

	public List<ComboValuesVO> getMonths(String lStrLangId, String lStrLocCode) throws Exception {

		List<Object[]> lLstResult = null;
		ComboValuesVO lObjComboValueVO = null;
		List<ComboValuesVO> lLstMonths = new ArrayList<ComboValuesVO>();
		try {
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			StringBuffer lSBQuery = new StringBuffer("select monthNo,monthName from SgvaMonthMst where langId = :langId order by monthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("langId", lStrLangId);
			lLstResult = lQuery.list();

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (Object[] lArrObj : lLstResult) {
					if (lArrObj[0] != null && lArrObj[1] != null) {
						lObjComboValueVO = new ComboValuesVO();
						lObjComboValueVO.setId(lArrObj[0].toString());
						lObjComboValueVO.setDesc(lArrObj[1].toString());
						lLstMonths.add(lObjComboValueVO);
					}
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			throw e;
		}
		return lLstMonths;
	}

	public List<ComboValuesVO> getYears(String lStrLangId, String lStrLocCode) throws Exception {

		List<String> lLstResult = null;
		ComboValuesVO lObjComboValueVO = null;
		List<ComboValuesVO> lLstYears = new ArrayList<ComboValuesVO>();

		try {
			int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);//added by Tejashree for current year
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			/*StringBuffer lSBQuery = new StringBuffer("select finYearCode from SgvcFinYearMst where langId = :lLangId order by finYearCode");*/
			StringBuffer lSBQuery = new StringBuffer("select finYearCode from SgvcFinYearMst where langId = :lLangId and  finYearCode <="+CurrentYear+" order by finYearCode");//changed by Tejashree for year list upto current year
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("lLangId", lStrLangId);
			lLstResult = lQuery.list();
			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (String lStrYearCode : lLstResult) {
					if (lStrYearCode != null) {
						lObjComboValueVO = new ComboValuesVO();
						lObjComboValueVO.setId(lStrYearCode);
						lObjComboValueVO.setDesc(lStrYearCode);
						lLstYears.add(lObjComboValueVO);
					}
				}
			}

		} catch (Exception e) {
			gLogger.error("Exception::" + e.getMessage(), e);
			throw (e);
		}
		return lLstYears;
	}

	public List getPensionCaseTrackingReport(ReportVO lObjReport, String lStrLocationCode) {

		List lArrReturn = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(TrnPensionRqstHdr.class, gObjSessionFactory);
		String lStrFromDate = null;
		String lStrToDate = null;
		String lStrTreasuryName = null;
		String lStrBankName = null;
		String lStrBranchName = null;
		String lStrPensionerName = null;
		String lStrAccountNumber = null;
		String lStrPpoNo = null;
		try {

			lStrFromDate = lObjReport.getParameterValue("InwardDatefrom").toString();
			lStrToDate = lObjReport.getParameterValue("InwardDateto").toString();

			if (!lObjReport.getParameterValue("treasuryCode").equals("-1")) {
				lStrTreasuryName = lObjReport.getParameterValue("treasuryCode").toString();
			}
			if (!lObjReport.getParameterValue("bankCode").equals("-1")) {
				lStrBankName = lObjReport.getParameterValue("bankCode").toString();
			}
			if (!lObjReport.getParameterValue("branchCode").equals("-1")) {
				lStrBranchName = lObjReport.getParameterValue("branchCode").toString();
			}
			if (!lObjReport.getParameterValue("pensionerName").equals("")) {
				lStrPensionerName = lObjReport.getParameterValue("pensionerName").toString().toUpperCase().trim();
			}
			if (!lObjReport.getParameterValue("accountNumber").equals("")) {
				lStrAccountNumber = lObjReport.getParameterValue("accountNumber").toString();
			}
			if (!lObjReport.getParameterValue("PPONo").equals("")) {
				lStrPpoNo = lObjReport.getParameterValue("PPONo").toString().trim();
			}

			lArrReturn = gObjRptQueryDAO.getPensionCaseTrackingReport(lObjReport, lStrTreasuryName, lStrFromDate, lStrToDate, lStrTreasuryName, lStrBankName, lStrBranchName,
					lStrPensionerName, lStrAccountNumber, lStrPpoNo);
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return lArrReturn;
	}

	/*
	 * List getBillTypeList(String lStrLangId, String lStrLocId) { List
	 * lLstBillType = new ArrayList();
	 * 
	 * try { ComboValuesVO lObjCombo1 = new ComboValuesVO(); ComboValuesVO
	 * lObjCombo2 = new ComboValuesVO(); ComboValuesVO lObjCombo3 = new
	 * ComboValuesVO(); ComboValuesVO lObjCombo4 = new ComboValuesVO();
	 * 
	 * lObjCombo1.setId("9");
	 * lObjCombo1.setDesc(gObjRsrcBndle.getString("PPMT.PENSION"));
	 * lLstBillType.add(lObjCombo1);
	 * 
	 * lObjCombo2.setId("10");
	 * lObjCombo2.setDesc(gObjRsrcBndle.getString("PPMT.CVP"));
	 * lLstBillType.add(lObjCombo2);
	 * 
	 * lObjCombo3.setId("11");
	 * lObjCombo3.setDesc(gObjRsrcBndle.getString("PPMT.DCRG"));
	 * lLstBillType.add(lObjCombo3);
	 * 
	 * lObjCombo4.setId("44");
	 * lObjCombo4.setDesc(gObjRsrcBndle.getString("PPMT.MONTHLY"));
	 * lLstBillType.add(lObjCombo4);
	 * 
	 * System.out.println("lLstBillType.size() is" + lLstBillType.size());
	 * 
	 * } catch (Exception e) {
	 * gLogger.error("In  class :::: getBillTypeList method :::: Error is  : " +
	 * e, e); }
	 * 
	 * return lLstBillType;
	 * 
	 * }
	 */
	public List getBillTrackingReport(ReportVO lObjReport, String lStrLocationCode) {

		List lArrList = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrFromDate = null;
		String lStrToDate = null;
		String lStrBillNo = null;
		String lStrBillType = null;
		try {
			if (lObjReport.getParameterValue("BillNo") != null) {
				lStrBillNo = lObjReport.getParameterValue("BillNo").toString();
			}

			lStrFromDate = lObjReport.getParameterValue("BillDatefrom").toString();
			lStrToDate = lObjReport.getParameterValue("BillDateto").toString();
			lStrBillType = lObjReport.getParameterValue("BillType").toString();
			if (lStrFromDate.length() > 0 && lStrToDate.length() > 0 && lStrBillType.length() > 0) {
				lArrList = gObjRptQueryDAO.getBillTrackingReport(lObjReport, lStrLocationCode, lStrFromDate, lStrToDate, lStrBillNo, lStrBillType);
			}
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return lArrList;
	}

	public List getBillTypeList(String lStrLangId, String lStrLocId) {

		List lLstBillType = new ArrayList();
		try {
			ComboValuesVO lObjCombo1 = new ComboValuesVO();
			lObjCombo1.setId(gObjRsrcBndle.getString("PPMT.PENSION"));
			lObjCombo1.setDesc(gObjRsrcBndle.getString("PPMT.PENSION"));
			lLstBillType.add(lObjCombo1);

			ComboValuesVO lObjCombo2 = new ComboValuesVO();
			lObjCombo2.setId(gObjRsrcBndle.getString("PPMT.CVP"));
			lObjCombo2.setDesc(gObjRsrcBndle.getString("PPMT.CVP"));
			lLstBillType.add(lObjCombo2);

			ComboValuesVO lObjCombo3 = new ComboValuesVO();
			lObjCombo3.setId(gObjRsrcBndle.getString("PPMT.DCRG"));
			lObjCombo3.setDesc(gObjRsrcBndle.getString("PPMT.DCRG"));
			lLstBillType.add(lObjCombo3);

			ComboValuesVO lObjCombo4 = new ComboValuesVO();
			lObjCombo4.setId(gObjRsrcBndle.getString("PPMT.MONTHLY"));
			lObjCombo4.setDesc(gObjRsrcBndle.getString("PPMT.MONTHLY"));
			lLstBillType.add(lObjCombo4);
		} catch (Exception e) {
			gLogger.error("In  class BillTrackingReportDAOImpl :::: getBillTypeList method :::: Error is  : " + e, e);
		}
		return lLstBillType;
	}

	public List getBankBranchWisePensionerCount(ReportVO lObjReport, String lStrLocationCode) {

		List lArrList = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrBankCode = null;
		String lStrBranchCode = null;
		List dataList = new ArrayList();
		List rowList = null;
		int lIntSerialNo = 0;

		// for Right Alignment format
		StyleVO[] RightAlignVO = new StyleVO[3];
		RightAlignVO[0] = new StyleVO();
		RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
		RightAlignVO[1] = new StyleVO();
		RightAlignVO[1].setStyleId(IReportConstants.BORDER);
		RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
		RightAlignVO[2] = new StyleVO();
		RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		RightAlignVO[2].setStyleValue("14");

		try {

			lStrBankCode = lObjReport.getParameterValue("bankCode").toString();
			lStrBranchCode = lObjReport.getParameterValue("branchCode").toString();

			if (lStrBankCode.length() > 0 && lStrBranchCode.length() > 0) {
				lArrList = gObjRptQueryDAO.getBankBranchWisePensionerCount(lStrBankCode, lStrBranchCode, lStrLocationCode);

				if (lArrList != null && !lArrList.isEmpty()) {
					Iterator it = lArrList.iterator();
					while (it.hasNext()) {
						lIntSerialNo++;
						Object[] tuple = (Object[]) it.next();
						rowList = new ArrayList();
						rowList.add(lIntSerialNo);
						rowList.add(tuple[0].toString());
						rowList.add(tuple[1].toString());
						rowList.add(tuple[2].toString());
						rowList.add(tuple[3].toString());
						rowList.add(new StyledData(tuple[4].toString(), RightAlignVO));

						dataList.add(rowList);
					}
				}
			}
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return dataList;
	}

	public List getPensionerCountMonthWise(ReportVO lObjReport, String lStrLocationCode) {

		List lArrList = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrBankCode = null;
		String lStrBranchCode = null;
		String lStrForMonth = null;
		String lStrForYear = null;
		String lStrPayForMonthYear = null;
		List dataList = new ArrayList();

		List rowList = null;
		int lIntSerialNo = 0;
		Double lLngGrossAmt = null;
		Double lLngNetAmt = null;
		Double lLngRecovryAmt = null;

		// for Right Alignment format
		StyleVO[] RightAlignVO = new StyleVO[3];
		RightAlignVO[0] = new StyleVO();
		RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
		RightAlignVO[1] = new StyleVO();
		RightAlignVO[1].setStyleId(IReportConstants.BORDER);
		RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
		RightAlignVO[2] = new StyleVO();
		RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		RightAlignVO[2].setStyleValue("14");

		try {

			lStrBankCode = lObjReport.getParameterValue("bankCode").toString();
			lStrBranchCode = lObjReport.getParameterValue("branchCode").toString();
			lStrForYear = lObjReport.getParameterValue("forYear").toString();
			lStrForMonth = lObjReport.getParameterValue("forMonth").toString();
			if (lStrForMonth != null || !("").equals(lStrForMonth)) {
				if (Integer.parseInt(lStrForMonth) < 10) {
					lStrPayForMonthYear = lStrForYear + "0" + lStrForMonth;
				} else {
					lStrPayForMonthYear = lStrForYear + lStrForMonth;
				}
			}

			if (lStrBankCode.length() > 0 && lStrBranchCode.length() > 0) {
				lArrList = gObjRptQueryDAO.getPensionerCountForMonth(lStrBankCode, lStrBranchCode, lStrPayForMonthYear, lStrLocationCode);

				if (lArrList != null && !lArrList.isEmpty()) {
					Iterator it = lArrList.iterator();
					while (it.hasNext()) {
						lIntSerialNo++;
						Object[] tuple = (Object[]) it.next();

						rowList = new ArrayList();
						rowList.add(lIntSerialNo);
						rowList.add(tuple[0].toString());
						rowList.add(tuple[1].toString());
						rowList.add(tuple[2].toString());
						rowList.add(tuple[3].toString());
						rowList.add(new StyledData(tuple[4].toString(), RightAlignVO));
						if (tuple[5] != null) {

							lLngGrossAmt = Double.parseDouble(tuple[5].toString());
							// rowList.add(lLngGrossAmt);

						}
						rowList.add(new StyledData(lLngGrossAmt, RightAlignVO));
						if (tuple[6] != null) {
							lLngRecovryAmt = Double.parseDouble(tuple[6].toString());
							// rowList.add(lLngRecovryAmt);

						}
						rowList.add(new StyledData(lLngRecovryAmt, RightAlignVO));
						if (tuple[7] != null) {
							lLngNetAmt = Double.parseDouble(tuple[7].toString());
							// rowList.add(lLngNetAmt);

						}
						rowList.add(new StyledData(lLngNetAmt, RightAlignVO));
						dataList.add(rowList);
					}
				}
			}
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return dataList;
	}

	public List getConsolidatedAuditorwiseRpt(ReportVO lObjReport, String lStrLocationCode) {

		List lArrList = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrAudPostId = null;
		String lStrForMonth = null;
		String lStrForYear = null;
		String lStrPayForMonthYear = null;
		List dataList = new ArrayList();

		List rowList = null;
		int lIntSerialNo = 0;
		Double lLngGrossAmt = null;
		Double lLngNetAmt = null;
		Double lLngRecovryAmt = null;

		// for Right Alignment format
		StyleVO[] RightAlignVO = new StyleVO[3];
		RightAlignVO[0] = new StyleVO();
		RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
		RightAlignVO[1] = new StyleVO();
		RightAlignVO[1].setStyleId(IReportConstants.BORDER);
		RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
		RightAlignVO[2] = new StyleVO();
		RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		RightAlignVO[2].setStyleValue("14");

		try {

			lStrAudPostId = lObjReport.getParameterValue("postId").toString();
			lStrForYear = lObjReport.getParameterValue("forYear").toString();
			lStrForMonth = lObjReport.getParameterValue("forMonth").toString();

			if (lStrForMonth != null || !("").equals(lStrForMonth)) {
				if (Integer.parseInt(lStrForMonth) < 10) {
					lStrPayForMonthYear = lStrForYear + "0" + lStrForMonth;
				} else {
					lStrPayForMonthYear = lStrForYear + lStrForMonth;
				}
			}

			if (lStrAudPostId.length() > 0 && lStrPayForMonthYear.length() > 0) {
				lArrList = gObjRptQueryDAO.getAuditorwisePensionerCountForMonth(lStrAudPostId, lStrPayForMonthYear, lStrLocationCode);

				if (lArrList != null && !lArrList.isEmpty()) {
					Iterator it = lArrList.iterator();
					while (it.hasNext()) {
						lIntSerialNo++;
						Object[] tuple = (Object[]) it.next();

						rowList = new ArrayList();
						rowList.add(lIntSerialNo);
						rowList.add(tuple[0].toString());
						rowList.add(tuple[1].toString());
						rowList.add(tuple[2].toString());
						rowList.add(tuple[3].toString());
						rowList.add(new StyledData(tuple[4].toString(), RightAlignVO));
						if (tuple[5] != null) {

							lLngGrossAmt = Double.parseDouble(tuple[5].toString());
							// rowList.add(lLngGrossAmt);

						}
						rowList.add(new StyledData(lLngGrossAmt, RightAlignVO));
						if (tuple[6] != null) {
							lLngRecovryAmt = Double.parseDouble(tuple[6].toString());
							// rowList.add(lLngRecovryAmt);

						}
						rowList.add(new StyledData(lLngRecovryAmt, RightAlignVO));
						if (tuple[7] != null) {
							lLngNetAmt = Double.parseDouble(tuple[7].toString());
							// rowList.add(lLngNetAmt);

						}
						rowList.add(new StyledData(lLngNetAmt, RightAlignVO));
						dataList.add(rowList);
					}
				}
			}
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return dataList;
	}

	public List getArrearPaymentDtls(ReportVO lObjReport, String lStrLocationCode) {

		List lArrList = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrBankCode = null;
		String lStrBranchCode = null;
		String lStrForMonth = null;
		String lStrForYear = null;
		String lStrPayForMonthYear = null;
		List dataList = new ArrayList();

		List rowList = null;
		int lIntSerialNo = 0;
		Double lDbArrearAmnt = 0.0;
		Double lDbTiArrearAmnt = 0.0;
		Double lDbTotalArrearAmnt = 0.0;

		// for Right Alignment format
		StyleVO[] RightAlignVO = new StyleVO[3];
		RightAlignVO[0] = new StyleVO();
		RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
		RightAlignVO[1] = new StyleVO();
		RightAlignVO[1].setStyleId(IReportConstants.BORDER);
		RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
		RightAlignVO[2] = new StyleVO();
		RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		RightAlignVO[2].setStyleValue("14");

		try {

			lStrBankCode = lObjReport.getParameterValue("bankCode").toString();
			lStrBranchCode = lObjReport.getParameterValue("branchCode").toString();
			lStrForYear = lObjReport.getParameterValue("forYear").toString();
			lStrForMonth = lObjReport.getParameterValue("forMonth").toString();
			if (lStrForMonth != null || !("").equals(lStrForMonth)) {
				if (Integer.parseInt(lStrForMonth) < 10) {
					lStrPayForMonthYear = lStrForYear + "0" + lStrForMonth;
				} else {
					lStrPayForMonthYear = lStrForYear + lStrForMonth;
				}
			}

			if (lStrBankCode.length() > 0 && lStrBranchCode.length() > 0) {
				lArrList = gObjRptQueryDAO.getArrearDtlsBankBranchWise(lStrBankCode, lStrBranchCode, lStrPayForMonthYear, lStrLocationCode);

				if (lArrList != null && !lArrList.isEmpty()) {
					Iterator it = lArrList.iterator();
					while (it.hasNext()) {
						lIntSerialNo++;
						Object[] tuple = (Object[]) it.next();

						if (tuple[4] != null) {

							lDbArrearAmnt = Double.parseDouble(tuple[4].toString());
						}
						if (tuple[5] != null) {

							lDbTiArrearAmnt = Double.parseDouble(tuple[5].toString());
						}

						lDbTotalArrearAmnt = lDbArrearAmnt + lDbTiArrearAmnt;

						if (lDbTotalArrearAmnt > 0.0) {
							rowList = new ArrayList();
							rowList.add(lIntSerialNo);
							rowList.add(tuple[0].toString());
							rowList.add(tuple[1].toString());
							rowList.add(tuple[2].toString());
							rowList.add(tuple[3].toString());
							rowList.add(new StyledData(lDbTotalArrearAmnt, RightAlignVO));

							dataList.add(rowList);
						}
					}
				}
			}
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return dataList;
	}

	public List getRecoveryReport(ReportVO lObjReport, String lStrLocationCode) {

		List lArrList = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrBankCode = null;
		String lStrBranchCode = null;
		String lStrForMonth = null;
		String lStrForYear = null;
		String lStrPayForMonthYear = null;
		String lStrSchemeCode = null;
		List dataList = new ArrayList();

		List rowList = null;
		int lIntSerialNo = 0;
		Double lDbRecovAmt = 0.0;
		// for Right Alignment format
		StyleVO[] RightAlignVO = new StyleVO[3];
		RightAlignVO[0] = new StyleVO();
		RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
		RightAlignVO[1] = new StyleVO();
		RightAlignVO[1].setStyleId(IReportConstants.BORDER);
		RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
		RightAlignVO[2] = new StyleVO();
		RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		RightAlignVO[2].setStyleValue("14");

		try {

			lStrBankCode = lObjReport.getParameterValue("bankCode").toString();
			lStrBranchCode = lObjReport.getParameterValue("branchCode").toString();
			lStrForYear = lObjReport.getParameterValue("forYear").toString();
			lStrForMonth = lObjReport.getParameterValue("forMonth").toString();
			if (lStrForMonth != null || !("").equals(lStrForMonth)) {
				if (Integer.parseInt(lStrForMonth) < 10) {
					lStrPayForMonthYear = lStrForYear + "0" + lStrForMonth;
				} else {
					lStrPayForMonthYear = lStrForYear + lStrForMonth;
				}
			}

			lStrSchemeCode = lObjReport.getParameterValue("schemeCode").toString();
			if (lStrBankCode.length() > 0 && lStrBranchCode.length() > 0) {
				lArrList = gObjRptQueryDAO.getRecoveryReport(lStrBankCode, lStrBranchCode, lStrPayForMonthYear, lStrSchemeCode, lStrLocationCode);

				if (lArrList != null && !lArrList.isEmpty()) {
					Iterator it = lArrList.iterator();
					while (it.hasNext()) {
						lIntSerialNo++;
						Object[] tuple = (Object[]) it.next();

						// if (lDbTotalArrearAmnt > 0.0) {
						rowList = new ArrayList();
						rowList.add(lIntSerialNo);
						rowList.add(tuple[0].toString());
						rowList.add(tuple[1].toString());
						rowList.add(tuple[2].toString());
						rowList.add(tuple[3].toString());
						if (tuple[4] != null) {
							lDbRecovAmt = Double.parseDouble(tuple[4].toString());
						}
						rowList.add(new StyledData(lDbRecovAmt, RightAlignVO));
						rowList.add(tuple[5].toString());
						dataList.add(rowList);
						// }
					}
				}
			}
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return dataList;
	}

	public List getSixPayArrearDtls(ReportVO lObjReport, String lStrLocationCode) {

		List lArrReturn = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrBankCode = null;
		String lStrBranchCode = null;
		String lStrPpoNo = null;
		try {
			lStrBankCode = lObjReport.getParameterValue("bankCode").toString();
			lStrBranchCode = lObjReport.getParameterValue("branchCode").toString();
			if (lObjReport.getParameterValue("PpoNo") != "") {
				lStrPpoNo = lObjReport.getParameterValue("PpoNo").toString();
			}

			lArrReturn = gObjRptQueryDAO.getSixPayArrearDtls(lStrBankCode, lStrBranchCode, lStrPpoNo, lStrLocationCode);
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return lArrReturn;
	}

	public List getFirstPmntCases(ReportVO lObjReport, String lStrLocationCode) {

		List lArrReturn = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrTreasuryCode = null;
		String lStrPPONo = null;
		try {
			lStrTreasuryCode = lObjReport.getParameterValue("treasuryCode").toString();
			if (lObjReport.getParameterValue("PpoNo") != "") {
				lStrPPONo = lObjReport.getParameterValue("PpoNo").toString();
			}
			if (lStrTreasuryCode.length() > 0) {
				lArrReturn = gObjRptQueryDAO.getFirstPmntCases(lStrTreasuryCode, lStrPPONo);
			}
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return lArrReturn;
	}

	public List getArchivedCases(ReportVO lObjReport, String lStrLocationCode) {

		List lArrReturn = new ArrayList();
		gObjRptQueryDAO = new PensionpayQueryDAOImpl(null, gObjSessionFactory);
		String lStrTreasuryCode = null;
		String lStrPPONo = null;
		try {
			lStrTreasuryCode = lObjReport.getParameterValue("treasuryCode").toString();
			if (lObjReport.getParameterValue("PpoNo") != "") {
				lStrPPONo = lObjReport.getParameterValue("PpoNo").toString();
			}
			if (lStrTreasuryCode.length() > 0) {
				lArrReturn = gObjRptQueryDAO.getArchivedCases(lStrTreasuryCode, lStrPPONo);
			}
		} catch (Exception e) {
			gLogger.error(e.getMessage(), e);

		}
		return lArrReturn;
	}

	public List<ComboValuesVO> getBankNameList(Hashtable otherArgs, String lStrLangId, String lStrLocCode) {

		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");
		Long lLngLangId = null;
		if (loginMap.containsKey("langId")) {
			lLngLangId = (Long) loginMap.get("langId");
		}
		List<Object[]> lLstResult = new ArrayList<Object[]>();
		List<ComboValuesVO> lLstBanks = new ArrayList<ComboValuesVO>();
		ComboValuesVO lObjComboValueVO = null;

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append("select mb.bankCode, mb.bankName ");
		lSBQuery.append("from MstBank mb where ");
		lSBQuery.append("mb.langId=:langId and mb.activateFlag=:activeFlag order by mb.bankName");

		try {
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setLong("langId", lLngLangId);
			lQuery.setParameter("activeFlag", Long.valueOf(1));

			lLstResult = lQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {
				for (Object[] lArrObj : lLstResult) {
					lObjComboValueVO = new ComboValuesVO();
					lObjComboValueVO.setId(lArrObj[0].toString());
					lObjComboValueVO.setDesc(lArrObj[1].toString());
					lLstBanks.add(lObjComboValueVO);
				}

				lObjComboValueVO = new ComboValuesVO();
				lObjComboValueVO.setId("0");
				lObjComboValueVO.setDesc("All");
				lLstBanks.add(lObjComboValueVO);
			}

		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}

		return lLstBanks;
	}

	public List<ComboValuesVO> getBranchNameListFromBankCode(String lStrBankCode, Hashtable otherArgs, String lStrLangId, String lStrLocId) {

		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");
		Long lLngLangId = null;
		String lStrLocCode = "";
		if (loginMap.containsKey("langId")) {
			lLngLangId = (Long) loginMap.get("langId");
		}
		if (loginMap.containsKey("locationId")) {
			lStrLocCode = loginMap.get("locationId").toString();
		}
		List<ComboValuesVO> lLstBankBrank = new ArrayList<ComboValuesVO>();
		StringBuilder lSBQuery = new StringBuilder();
		List<Object[]> lLstResult = new ArrayList<Object[]>();
		ComboValuesVO lObjComboValueVO = null;
		try {
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			if (Long.valueOf(lStrBankCode) != 0L) {
				lSBQuery.append("SELECT branchCode,branchName ");
				lSBQuery.append("FROM RltBankBranch  WHERE ");
				lSBQuery.append("bankCode =:bankCode AND langId =:langId AND locationCode= :locationCode order by branchName");

				Query hqlQuery = ghibSession.createQuery(lSBQuery.toString());
				hqlQuery.setLong("langId", lLngLangId);
				hqlQuery.setLong("bankCode", Long.valueOf(lStrBankCode));
				hqlQuery.setString("locationCode", lStrLocCode);
				hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
				lLstResult = hqlQuery.list();
				if (lLstResult != null && lLstResult.size() > 0) {
					for (Object[] lArrObj : lLstResult) {
						lObjComboValueVO = new ComboValuesVO();
						lObjComboValueVO.setId(lArrObj[0].toString());
						lObjComboValueVO.setDesc(lArrObj[1].toString());
						lLstBankBrank.add(lObjComboValueVO);
					}
					lObjComboValueVO = new ComboValuesVO();
					lObjComboValueVO.setId("0");
					lObjComboValueVO.setDesc("All");
					lLstBankBrank.add(lObjComboValueVO);
				}
			} else {
				lObjComboValueVO = new ComboValuesVO();
				lObjComboValueVO.setId("0");
				lObjComboValueVO.setDesc("All");
				lLstBankBrank.add(lObjComboValueVO);
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
		}
		return lLstBankBrank;
	}

	public List<ComboValuesVO> getSchemeCodeList(Hashtable otherArgs, String lStrLangId, String lStrLocCode) {

		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");
		Long lLngLangId = null;
		if (loginMap.containsKey("langId")) {
			lLngLangId = (Long) loginMap.get("langId");
		}
		List<Object[]> lLstResult = new ArrayList<Object[]>();
		List<ComboValuesVO> lLstSchemes = new ArrayList<ComboValuesVO>();
		ComboValuesVO lObjComboValueVO = null;

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append("select ms.schemeCode, ms.schemeId ");
		lSBQuery.append("from MstScheme ms where ");
		lSBQuery.append("ms.langId=:langId order by ms.schemeCode");

		try {
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setLong("langId", lLngLangId);

			lLstResult = lQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {
				for (Object[] lArrObj : lLstResult) {
					lObjComboValueVO = new ComboValuesVO();
					lObjComboValueVO.setId(lArrObj[1].toString());
					lObjComboValueVO.setDesc(lArrObj[0].toString());
					lLstSchemes.add(lObjComboValueVO);
				}

				lObjComboValueVO = new ComboValuesVO();
				lObjComboValueVO.setId("0");
				lObjComboValueVO.setDesc("All");
				lLstSchemes.add(lObjComboValueVO);
			}

		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}

		return lLstSchemes;
	}

	public List<ComboValuesVO> getAllTreasury(Hashtable otherArgs, String lStrLangId, String lStrLocId) {

		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");
		Long lLngLangId = null;
		if (loginMap.containsKey("langId")) {
			lLngLangId = (Long) loginMap.get("langId");
		}
		List<Object[]> lLstResult = new ArrayList<Object[]>();
		List<ComboValuesVO> lLstTreasury = new ArrayList<ComboValuesVO>();
		ComboValuesVO lObjComboValueVO = null;

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" SELECT locationCode,locName   ");
		lSBQuery.append(" FROM CmnLocationMst ");
		lSBQuery.append(" WHERE cmnLanguageMst.langId =:langId AND departmentId IN (100003) ");
		lSBQuery.append(" order by locName ");

		try {
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setLong("langId", lLngLangId);

			lLstResult = lQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {
				for (Object[] lArrObj : lLstResult) {
					lObjComboValueVO = new ComboValuesVO();
					lObjComboValueVO.setId(lArrObj[0].toString());
					lObjComboValueVO.setDesc(lArrObj[1].toString());
					lLstTreasury.add(lObjComboValueVO);
				}
			}

		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}

		return lLstTreasury;
	}

	public List<ComboValuesVO> getSchemeCodeForBill(Hashtable otherArgs, String lStrLangId, String lStrLocCode) {

		List<String> lLstResult = new ArrayList<String>();
		List<ComboValuesVO> lLstSchemes = new ArrayList<ComboValuesVO>();
		ComboValuesVO lObjComboValueVO = null;

		try {

			StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append("select distinct schemeCode ");
			lSBQuery.append("from RltPensionHeadcodeChargable where ");
			lSBQuery.append("billType = :billType");
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setString("billType", "PENSION");

			lLstResult = lQuery.list();

			if (lLstResult != null && lLstResult.size() > 0) {
				for (String lStrSchemeCode : lLstResult) {
					lObjComboValueVO = new ComboValuesVO();
					lObjComboValueVO.setId(lStrSchemeCode);
					lObjComboValueVO.setDesc(lStrSchemeCode);
					lLstSchemes.add(lObjComboValueVO);
				}

			}

		} catch (Exception e) {
			//e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
		}

		return lLstSchemes;
	}

	public List<String> getSchemeCodeListForPensionBill() {

		List<String> lLstResult = new ArrayList<String>();

		try {

			StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append("select distinct schemeCode ");
			lSBQuery.append("from RltPensionHeadcodeChargable where ");
			lSBQuery.append("billType = :billType");
			ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setString("billType", "PENSION");

			lLstResult = lQuery.list();

		} catch (Exception e) {
			//e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
		}

		return lLstResult;
	}

	public List getSchemeWisePaymentDtls(ReportVO lObjReport, String lStrLocCode) {

		String lStrSchemeCode = null;
		String lStrForMonth = null;
		String lStrForYear = null;
		List lLstRptData = null;
		List dataList = new ArrayList();
		List rowList = null;

		Double lDbGrossAmount = 0.0;
		Double lDbDeductionAmount = 0.0;
		Double lDbNetAmount = 0.0;
		List<String> lLstSchemeCode = new ArrayList<String>();
		Map<String, String> lMapSchemeCode = new HashMap<String, String>();
		try {
			lStrForMonth = (String) lObjReport.getParameterValue("forMonth");
			lStrForYear = (String) lObjReport.getParameterValue("forYear");
			lStrSchemeCode = (String) lObjReport.getParameterValue("SchemeCode");

			if (!"-1".equals(lStrSchemeCode)) {
				lLstSchemeCode.add(lStrSchemeCode);
			} else {
				lLstSchemeCode = getSchemeCodeListForPensionBill();
			}
			CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(gObjSessionFactory);
			lMapSchemeCode = lObjCommonPensionDAO.getPaymentSchemeCodeMap(lLstSchemeCode);

			String lStrMonthYear = null;
			if (Integer.parseInt(lStrForMonth) < 10) {
				lStrMonthYear = lStrForYear + "0" + lStrForMonth;
			} else {
				lStrMonthYear = lStrForYear + lStrForMonth;
			}
			StyleVO[] RightAlignVO = new StyleVO[3];
			RightAlignVO[0] = new StyleVO();
			RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
			RightAlignVO[1] = new StyleVO();
			RightAlignVO[1].setStyleId(IReportConstants.BORDER);
			RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			RightAlignVO[2] = new StyleVO();
			RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			RightAlignVO[2].setStyleValue("14");

			String urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=365471&action=generateReport&DirectReport=TRUE&displayOK=TRUE";

			gObjRptQueryDAO = new PensionpayQueryDAOImpl(TrnPensionBillDtls.class, gObjSessionFactory);
			lLstRptData = gObjRptQueryDAO.getSchemeWisePaymentDtls(lStrMonthYear, lStrSchemeCode, lStrLocCode);
			if (lLstRptData != null && !lLstRptData.isEmpty()) {

				Iterator it = lLstRptData.iterator();
				while (it.hasNext()) {

					Object[] tuple = (Object[]) it.next();
					rowList = new ArrayList();
					rowList.add(new URLData(tuple[0].toString(), urlPrefix + "&forMonth=" + lStrForMonth + "&forYear=" + lStrForYear + "&schemeCode=" + tuple[0].toString()
							+ "&flag=Y"));
					rowList.add(lMapSchemeCode.get(tuple[0].toString()));
					if (tuple[1] != null) {
						lDbGrossAmount = Double.parseDouble(tuple[1].toString());
					}
					rowList.add(new StyledData(lDbGrossAmount.longValue(), RightAlignVO));
					if (tuple[2] != null) {
						lDbDeductionAmount = Double.parseDouble(tuple[2].toString());
					}
					rowList.add(new StyledData(lDbDeductionAmount.longValue(), RightAlignVO));
					if (tuple[3] != null) {
						lDbNetAmount = Double.parseDouble(tuple[3].toString());
					}
					rowList.add(new StyledData(lDbNetAmount.longValue(), RightAlignVO));

					dataList.add(rowList);

				}
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return dataList;
	}

	public List getBankWisePaymentDtls(ReportVO lObjReport, String lStrLocCode) {

		String lStrSchemeCode = null;
		String lStrMonthYear = null;
		List lLstRptData = null;
		List dataList = new ArrayList();
		List rowList = null;
		String lStrForMonth = null;
		String lStrForYear = null;
		String lStrBankCode = null;
		String lStrFlag = "";
		Double lDbGrossAmount = 0.0;
		Double lDbDeductionAmount = 0.0;
		Double lDbNetAmount = 0.0;

		try {
			lStrForMonth = (String) lObjReport.getParameterValue("forMonth");
			lStrForYear = (String) lObjReport.getParameterValue("forYear");
			lStrBankCode = (String) lObjReport.getParameterValue("bankCode");
			lStrFlag = (String) lObjReport.getParameterValue("flag");
			if (lStrFlag == null || "".equals(lStrFlag)) {
				StyleVO[] lArrStyleVO = lObjReport.getStyleList();
				lArrStyleVO[0].setStyleValue("ifms.htm?actionFlag=reportService&reportCode=365471&action=parameterPage");
			}
			// lStrMonthYear = (String)
			// lObjReport.getParameterValue("forMonthYear");
			if (Integer.parseInt(lStrForMonth) < 10) {
				lStrMonthYear = lStrForYear + "0" + lStrForMonth;
			} else {
				lStrMonthYear = lStrForYear + lStrForMonth;
			}
			lStrSchemeCode = (String) lObjReport.getParameterValue("schemeCode");

			StyleVO[] RightAlignVO = new StyleVO[3];
			RightAlignVO[0] = new StyleVO();
			RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
			RightAlignVO[1] = new StyleVO();
			RightAlignVO[1].setStyleId(IReportConstants.BORDER);
			RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			RightAlignVO[2] = new StyleVO();
			RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			RightAlignVO[2].setStyleValue("14");

			String urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=365472&action=generateReport&DirectReport=TRUE&displayOK=TRUE";

			gObjRptQueryDAO = new PensionpayQueryDAOImpl(TrnPensionBillDtls.class, gObjSessionFactory);
			lLstRptData = gObjRptQueryDAO.getBankWisePaymentDtls(lStrMonthYear, lStrSchemeCode, lStrBankCode, lStrLocCode);
			if (lLstRptData != null && !lLstRptData.isEmpty()) {

				Iterator it = lLstRptData.iterator();
				while (it.hasNext()) {

					Object[] tuple = (Object[]) it.next();
					rowList = new ArrayList();
					if (lStrFlag != null && !"".equals(lStrFlag)) {
						if ("Y".equals(lStrFlag)) {
							rowList.add(new URLData(tuple[0].toString(), urlPrefix + "&forMonth=" + lStrForMonth + "&forYear=" + lStrForYear + "&schemeCode=" + lStrSchemeCode
									+ "&bankCode=" + tuple[0].toString() + "&flag=Y"));
						}
					} else {
						rowList.add(tuple[0]);
					}
					rowList.add(tuple[1]);
					if (tuple[2] != null) {
						lDbGrossAmount = Double.parseDouble(tuple[2].toString());
					}
					rowList.add(new StyledData(lDbGrossAmount.longValue(), RightAlignVO));
					if (tuple[3] != null) {
						lDbDeductionAmount = Double.parseDouble(tuple[3].toString());
					}
					rowList.add(new StyledData(lDbDeductionAmount.longValue(), RightAlignVO));
					if (tuple[4] != null) {
						lDbNetAmount = Double.parseDouble(tuple[4].toString());
					}
					rowList.add(new StyledData(lDbNetAmount.longValue(), RightAlignVO));

					dataList.add(rowList);

				}
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return dataList;
	}

	public List getBranchWisePaymentDtls(ReportVO lObjReport, String lStrLocCode) {

		String lStrSchemeCode = null;
		String lStrForMonth = null;
		String lStrForYear = null;
		String lStrMonthYear = null;
		String lStrBankCode = null;
		String lStrBranchCode = null;
		String lStrFlag = "";
		List lLstRptData = null;
		List dataList = new ArrayList();
		List rowList = null;
		Double lDbGrossAmount = 0.0;
		Double lDbDeductionAmount = 0.0;
		Double lDbNetAmount = 0.0;

		try {
			// lStrMonthYear = (String)
			// lObjReport.getParameterValue("forMonthYear");
			lStrForMonth = (String) lObjReport.getParameterValue("forMonth");
			lStrForYear = (String) lObjReport.getParameterValue("forYear");
			lStrSchemeCode = (String) lObjReport.getParameterValue("schemeCode");
			lStrBankCode = (String) lObjReport.getParameterValue("bankCode");
			lStrBranchCode = (String) lObjReport.getParameterValue("branchCode");
			lStrFlag = (String) lObjReport.getParameterValue("flag");
			if (lStrFlag == null || "".equals(lStrFlag)) {
				StyleVO[] lArrStyleVO = lObjReport.getStyleList();
				lArrStyleVO[0].setStyleValue("ifms.htm?actionFlag=reportService&reportCode=365472&action=parameterPage");
			}

			if (Integer.parseInt(lStrForMonth) < 10) {
				lStrMonthYear = lStrForYear + "0" + lStrForMonth;
			} else {
				lStrMonthYear = lStrForYear + lStrForMonth;
			}
			StyleVO[] RightAlignVO = new StyleVO[3];
			RightAlignVO[0] = new StyleVO();
			RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
			RightAlignVO[1] = new StyleVO();
			RightAlignVO[1].setStyleId(IReportConstants.BORDER);
			RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			RightAlignVO[2] = new StyleVO();
			RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			RightAlignVO[2].setStyleValue("14");

			gObjRptQueryDAO = new PensionpayQueryDAOImpl(TrnPensionBillDtls.class, gObjSessionFactory);
			lLstRptData = gObjRptQueryDAO.getBranchWisePaymentDtls(lStrMonthYear, lStrSchemeCode, lStrBankCode, lStrBranchCode, lStrLocCode);
			if (lLstRptData != null && !lLstRptData.isEmpty()) {

				Iterator it = lLstRptData.iterator();
				while (it.hasNext()) {

					Object[] tuple = (Object[]) it.next();
					rowList = new ArrayList();
					rowList.add(tuple[0]);

					rowList.add(tuple[1]);
					if (tuple[2] != null) {
						lDbGrossAmount = Double.parseDouble(tuple[2].toString());
					}
					rowList.add(new StyledData(lDbGrossAmount.longValue(), RightAlignVO));
					if (tuple[3] != null) {
						lDbDeductionAmount = Double.parseDouble(tuple[3].toString());
					}
					rowList.add(new StyledData(lDbDeductionAmount.longValue(), RightAlignVO));
					if (tuple[4] != null) {
						lDbNetAmount = Double.parseDouble(tuple[4].toString());
					}
					rowList.add(new StyledData(lDbNetAmount.longValue(), RightAlignVO));

					dataList.add(rowList);

				}
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return dataList;
	}

	public List getAGFirstPayStatement(ReportVO lObjReport, String lStrLocCode) {

		String lStrFormVoucherDate = null;
		String lStrToVoucherDate = null;
		String lStrTreasuryCode = null;
		String lStrPPONo = null;
		Date lDtFromVoucherDate = null;
		Date lDtToVoucherDate = null;
		Long lLngTreasuryCode = null;
		List lLstRptData = new ArrayList();
		List dataList = new ArrayList();
		List rowList = null;
		SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Double lDbGrossBeforeDiff = 0D;
		String lStrPensionerCode = null;
		Map<String, List<Object[]>> lMapDCRGDtls = new HashMap<String, List<Object[]>>();
		List<Object[]> lLstDtls = new ArrayList<Object[]>();
		List<Object[]> lLstCVPFromToDateAmount = null;
		String lStrCVPFromToDateAmount = "";
		Object[] lArrObj = null;
		Object[] tuple = null;
		try {

			lStrFormVoucherDate = (String) lObjReport.getParameterValue("formVoucherDate");
			lStrToVoucherDate = (String) lObjReport.getParameterValue("toVoucherDate");
			lStrTreasuryCode = (String) lObjReport.getParameterValue("treasuryCode");
			lStrPPONo = (String) lObjReport.getParameterValue("ppoNo");
			if (!"".equals(lStrFormVoucherDate)) {
				lDtFromVoucherDate = StringUtility.convertStringToDate(lStrFormVoucherDate);
			}
			if (!"".equals(lStrToVoucherDate)) {
				lDtToVoucherDate = StringUtility.convertStringToDate(lStrToVoucherDate);
				lDtToVoucherDate.setDate(lDtToVoucherDate.getDate() + 1);
			}
			if (!"".equals(lStrTreasuryCode)) {
				lLngTreasuryCode = Long.parseLong(lStrTreasuryCode);
			}

			gObjRptQueryDAO = new PensionpayQueryDAOImpl(TrnPensionBillDtls.class, gObjSessionFactory);
			lLstRptData = gObjRptQueryDAO.getAGFirstPayStatement(lDtFromVoucherDate, lDtToVoucherDate, lLngTreasuryCode, lStrPPONo.trim());

			if (lLstRptData != null && !lLstRptData.isEmpty()) {

				Iterator it = lLstRptData.iterator();
				while (it.hasNext()) {
					tuple = (Object[]) it.next();

					lStrPensionerCode = tuple[20].toString();

					lLstDtls = lMapDCRGDtls.get(lStrPensionerCode);

					lArrObj = new Object[3];

					if (tuple[21] != null) {
						lArrObj[0] = lObjDateFormat.format(tuple[21]);
					} else {
						lArrObj[0] = "";
					}
					if (tuple[22] != null) {
						lArrObj[1] = lObjDateFormat.format(tuple[22]);
					} else {
						lArrObj[1] = "";
					}
					if (tuple[23] != null) {
						Double lLngCVPAmount = Double.parseDouble(tuple[23].toString());
						lArrObj[2] = lLngCVPAmount.longValue();
					} else {
						lArrObj[2] = "";
					}

					if (lLstDtls != null) {
						lLstDtls.add(lArrObj);
					} else {
						lLstDtls = new ArrayList<Object[]>();
						lLstDtls.add(lArrObj);
					}

					lMapDCRGDtls.put(lStrPensionerCode, lLstDtls);
				}

				it = lLstRptData.iterator();
				while (it.hasNext()) {

					tuple = (Object[]) it.next();
					rowList = new ArrayList();
					lStrCVPFromToDateAmount = "";
					if (tuple[0] != null) {
						rowList.add(tuple[0]);
					} else {
						rowList.add("");
					}
					if (tuple[1] != null) {
						rowList.add(tuple[1]);
					} else {
						rowList.add("");
					}
					if (tuple[2] != null) {
						rowList.add(tuple[2]);
					} else {
						rowList.add("");
					}
					if (tuple[3] != null) {
						rowList.add(lObjDateFormat.format(tuple[3]));
					} else {
						rowList.add("");
					}
					if (tuple[4] != null) {
						rowList.add(tuple[4]);
					} else {
						rowList.add("");
					}
					if (tuple[5] != null) {
						rowList.add(lObjDateFormat.format(tuple[5]));
					} else {
						rowList.add("");
					}
					if (tuple[6] != null) {
						rowList.add(tuple[6]);
					} else {
						rowList.add("");
					}
					if (tuple[7] != null) {
						rowList.add(lObjDateFormat.format(tuple[7]));
					} else {
						rowList.add("");
					}
					lLstCVPFromToDateAmount = lMapDCRGDtls.get(tuple[20].toString());
					if (lLstCVPFromToDateAmount != null) {
						for (Object[] lArrObjBeneficiaryDtls : lLstCVPFromToDateAmount) {

							lStrCVPFromToDateAmount += lArrObjBeneficiaryDtls[0].toString() + " - " + lArrObjBeneficiaryDtls[1].toString() + " - "
									+ lArrObjBeneficiaryDtls[2].toString() + "\n";
						}
					}
					rowList.add(lStrCVPFromToDateAmount);

					if (tuple[8] != null) {
						rowList.add(tuple[8]);
					} else {
						rowList.add("");
					}
					if (tuple[9] != null) {
						rowList.add(tuple[9]);
					} else {
						rowList.add("");
					}
					if (tuple[10] != null) {
						rowList.add(tuple[10]);
					} else {
						rowList.add("");
					}
					if (tuple[11] != null) {
						rowList.add(tuple[11]);
					} else {
						rowList.add("");
					}
					if (tuple[12] != null) {
						rowList.add(tuple[12]);
					} else {
						rowList.add("");
					}
					if (tuple[13] != null) {
						rowList.add(tuple[13]);
					} else {
						rowList.add("");
					}
					if (tuple[14] != null) {
						rowList.add(tuple[14]);
					} else {
						rowList.add("");
					}
					if (tuple[15] != null) {
						rowList.add(tuple[15]);
					} else {
						rowList.add("");
					}
					if (tuple[17] != null && tuple[16] != null) {
						lDbGrossBeforeDiff = Double.parseDouble(tuple[17].toString()) - Double.parseDouble(tuple[16].toString());
					}
					rowList.add(lDbGrossBeforeDiff);

					if (tuple[16] != null) {
						rowList.add(tuple[16]);
					} else {
						rowList.add("");
					}
					if (tuple[17] != null) {
						rowList.add(tuple[17]);
					} else {
						rowList.add("");
					}
					if (tuple[18] != null) {
						rowList.add(tuple[18]);
					} else {
						rowList.add("");
					}
					if (tuple[19] != null) {
						rowList.add(tuple[19]);
					} else {
						rowList.add("");
					}
					dataList.add(rowList);
				}

			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return dataList;
	}

	public List getCVPPaymentDtls(ReportVO lObjReport, String lStrLocCode) {

		String lStrFormVoucherDate = null;
		String lStrToVoucherDate = null;
		String lStrTreasuryCode = null;
		String lStrPPONo = null;
		Date lDtFromVoucherDate = null;
		Date lDtToVoucherDate = null;
		Long lLngTreasuryCode = null;
		List lLstRptData = new ArrayList();
		List dataList = new ArrayList();
		List rowList = null;
		SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {

			lStrFormVoucherDate = (String) lObjReport.getParameterValue("formVoucherDate");
			lStrToVoucherDate = (String) lObjReport.getParameterValue("toVoucherDate");
			lStrTreasuryCode = (String) lObjReport.getParameterValue("treasuryCode");
			lStrPPONo = (String) lObjReport.getParameterValue("ppoNo");
			if (!"".equals(lStrFormVoucherDate)) {
				lDtFromVoucherDate = StringUtility.convertStringToDate(lStrFormVoucherDate);
			}
			if (!"".equals(lStrToVoucherDate)) {
				lDtToVoucherDate = StringUtility.convertStringToDate(lStrToVoucherDate);
				lDtToVoucherDate.setDate(lDtToVoucherDate.getDate() + 1);
			}
			if (!"".equals(lStrTreasuryCode)) {
				lLngTreasuryCode = Long.parseLong(lStrTreasuryCode);
			}

			StyleVO[] RightAlignVO = new StyleVO[3];
			RightAlignVO[0] = new StyleVO();
			RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
			RightAlignVO[1] = new StyleVO();
			RightAlignVO[1].setStyleId(IReportConstants.BORDER);
			RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			RightAlignVO[2] = new StyleVO();
			RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			RightAlignVO[2].setStyleValue("14");

			StyleVO[] CenterAlignVO = new StyleVO[3];
			CenterAlignVO[0] = new StyleVO();
			CenterAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			CenterAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			CenterAlignVO[1] = new StyleVO();
			CenterAlignVO[1].setStyleId(IReportConstants.BORDER);
			CenterAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			CenterAlignVO[2] = new StyleVO();
			CenterAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			CenterAlignVO[2].setStyleValue("14");

			gObjRptQueryDAO = new PensionpayQueryDAOImpl(TrnPensionBillDtls.class, gObjSessionFactory);
			lLstRptData = gObjRptQueryDAO.getCVPPaymentDtls(lDtFromVoucherDate, lDtToVoucherDate, lLngTreasuryCode, lStrPPONo.trim());

			if (lLstRptData != null && !lLstRptData.isEmpty()) {

				Iterator it = lLstRptData.iterator();
				while (it.hasNext()) {

					Object[] tuple = (Object[]) it.next();
					rowList = new ArrayList();

					if (tuple[0] != null) {
						rowList.add(tuple[0]);
					} else {
						rowList.add("");
					}
					if (tuple[1] != null) {
						rowList.add(tuple[1]);
					} else {
						rowList.add("");
					}

					if (tuple[2] != null) {
						rowList.add(tuple[2]);
					} else {
						rowList.add("");
					}
					if (tuple[3] != null) {
						rowList.add(new StyledData(lObjDateFormat.format(tuple[3]), CenterAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[4] != null) {
						rowList.add(tuple[4]);
					} else {
						rowList.add("");
					}
					if (tuple[5] != null) {
						rowList.add(new StyledData(lObjDateFormat.format(tuple[5]), CenterAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[6] != null) {
						rowList.add(new StyledData(tuple[6], RightAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[7] != null) {
						rowList.add(tuple[7].toString());
					} else {
						rowList.add("");
					}
					if (tuple[8] != null) {
						rowList.add(new StyledData(lObjDateFormat.format(tuple[8]), CenterAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[9] != null) {
						rowList.add(tuple[9]);
					} else {
						rowList.add("");
					}
					dataList.add(rowList);
				}
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return dataList;
	}

	public List getDCRGPaymentDtls(ReportVO lObjReport, String lStrLocCode) {

		String lStrFormVoucherDate = null;
		String lStrToVoucherDate = null;
		String lStrTreasuryCode = null;
		String lStrPPONo = null;
		Date lDtFromVoucherDate = null;
		Date lDtToVoucherDate = null;
		Long lLngTreasuryCode = null;
		List lLstRptData = new ArrayList();
		List dataList = new ArrayList();
		List rowList = null;
		SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Map<BigInteger, List<Object[]>> lMapDCRGDtls = new HashMap<BigInteger, List<Object[]>>();
		List<Object[]> lLstDtls = new ArrayList<Object[]>();
		BigInteger lBigIntBillNo = null;
		List<Object[]> lLstBeneficiaryDtls = null;
		String lStrBeneficiaryDtls;
		Object[] lArrObj = null;
		try {

			lStrFormVoucherDate = (String) lObjReport.getParameterValue("formVoucherDate");
			lStrToVoucherDate = (String) lObjReport.getParameterValue("toVoucherDate");
			lStrTreasuryCode = (String) lObjReport.getParameterValue("treasuryCode");
			lStrPPONo = (String) lObjReport.getParameterValue("ppoNo");

			if (!"".equals(lStrFormVoucherDate)) {
				lDtFromVoucherDate = StringUtility.convertStringToDate(lStrFormVoucherDate);
			}
			if (!"".equals(lStrToVoucherDate)) {
				lDtToVoucherDate = StringUtility.convertStringToDate(lStrToVoucherDate);
				lDtToVoucherDate.setDate(lDtToVoucherDate.getDate() + 1);
			}
			if (!"".equals(lStrTreasuryCode)) {
				lLngTreasuryCode = Long.parseLong(lStrTreasuryCode);
			}

			StyleVO[] RightAlignVO = new StyleVO[3];
			RightAlignVO[0] = new StyleVO();
			RightAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			RightAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
			RightAlignVO[1] = new StyleVO();
			RightAlignVO[1].setStyleId(IReportConstants.BORDER);
			RightAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			RightAlignVO[2] = new StyleVO();
			RightAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			RightAlignVO[2].setStyleValue("14");

			StyleVO[] CenterAlignVO = new StyleVO[3];
			CenterAlignVO[0] = new StyleVO();
			CenterAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			CenterAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			CenterAlignVO[1] = new StyleVO();
			CenterAlignVO[1].setStyleId(IReportConstants.BORDER);
			CenterAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			CenterAlignVO[2] = new StyleVO();
			CenterAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			CenterAlignVO[2].setStyleValue("14");

			gObjRptQueryDAO = new PensionpayQueryDAOImpl(TrnPensionBillDtls.class, gObjSessionFactory);
			lLstRptData = gObjRptQueryDAO.getDCRGPaymentDtls(lDtFromVoucherDate, lDtToVoucherDate, lLngTreasuryCode, lStrPPONo.trim());

			if (lLstRptData != null && !lLstRptData.isEmpty()) {
				Iterator it = lLstRptData.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();

					lBigIntBillNo = new BigInteger(tuple[1].toString());

					lLstDtls = lMapDCRGDtls.get(lBigIntBillNo);

					lArrObj = new Object[2];
					lArrObj[0] = tuple[12];
					lArrObj[1] = tuple[13];

					if (lLstDtls != null) {
						lLstDtls.add(lArrObj);
					} else {
						lLstDtls = new ArrayList<Object[]>();
						lLstDtls.add(lArrObj);
					}

					lMapDCRGDtls.put(lBigIntBillNo, lLstDtls);
				}
				it = lLstRptData.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();

					rowList = new ArrayList();

					if (tuple[0] != null) {
						rowList.add(tuple[0]);
					} else {
						rowList.add("");
					}
					if (tuple[2] != null) {
						rowList.add(tuple[2]);
					} else {
						rowList.add("");
					}
					if (tuple[3] != null) {
						rowList.add(tuple[3]);
					} else {
						rowList.add("");
					}

					if (tuple[4] != null) {
						rowList.add(new StyledData(lObjDateFormat.format(tuple[4]), CenterAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[5] != null) {
						rowList.add(tuple[5]);
					} else {
						rowList.add("");
					}

					if (tuple[6] != null) {
						rowList.add(new StyledData(lObjDateFormat.format(tuple[6]), CenterAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[7] != null) {
						rowList.add(new StyledData(tuple[7], RightAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[8] != null) {
						rowList.add(new StyledData(tuple[8], RightAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[9] != null) {
						rowList.add(new StyledData(tuple[9], RightAlignVO));
					} else {
						rowList.add("");
					}
					if (tuple[10] != null) {
						rowList.add(tuple[10].toString());
					} else {
						rowList.add("");
					}

					if (tuple[11] != null) {
						rowList.add(new StyledData(lObjDateFormat.format(tuple[11]), CenterAlignVO));
					} else {
						rowList.add("");
					}

					lLstBeneficiaryDtls = lMapDCRGDtls.get(tuple[1]);
					lStrBeneficiaryDtls = "";
					Double lDbAmt;

					for (Object[] lArrObjBeneficiaryDtls : lLstBeneficiaryDtls) {
						lDbAmt = Double.parseDouble(lArrObjBeneficiaryDtls[1].toString());
						lStrBeneficiaryDtls += lArrObjBeneficiaryDtls[0].toString() + " - " + lDbAmt.longValue() + "\n";
					}
					rowList.add(lStrBeneficiaryDtls);
					dataList.add(rowList);
				}

			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return dataList;
	}

	public List getChangeStatementRpt(ReportVO lObjReport, String lStrLocCode) {
		String lStrYear = null;
		String lStrMonth = null;
		String lStrAuditorPostId = null;
		String lStrMonthYear = null;
		Integer lIntMonthYear = 0;
		Long lLngAuditorPostId = null;
		List lLstRptData = new ArrayList();
		List dataList = new ArrayList();
		List rowList = null;
		Integer lIntSrNoCnt = 1;
		try {

			lStrYear = (String) lObjReport.getParameterValue("year");
			lStrMonth = (String) lObjReport.getParameterValue("month");
			lStrAuditorPostId = (String) lObjReport.getParameterValue("auditorPostId");

			if (Integer.parseInt(lStrMonth) < 10) {
				lStrMonthYear = lStrYear + "0" + lStrMonth;
			} else {
				lStrMonthYear = lStrYear + lStrMonth;
			}
			if (lStrMonthYear != null) {
				lIntMonthYear = Integer.valueOf(lStrMonthYear);
			}
			if (!"-1".equals(lStrAuditorPostId)) {
				lLngAuditorPostId = Long.parseLong(lStrAuditorPostId);
			}

			gObjRptQueryDAO = new PensionpayQueryDAOImpl(MstBank.class, gObjSessionFactory);
			lLstRptData = gObjRptQueryDAO.getChangeStatementData(lLngAuditorPostId, lIntMonthYear, lStrLocCode, gLngLangId.toString());

			if (lLstRptData != null && !lLstRptData.isEmpty()) {
				Iterator it = lLstRptData.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();

					rowList = new ArrayList();

					rowList.add(lIntSrNoCnt);

					if (tuple[0] != null) {
						rowList.add(tuple[0]);
					} else {
						rowList.add("");
					}
					if (tuple[1] != null) {
						rowList.add(tuple[1]);
					} else {
						rowList.add("");
					}
					if (tuple[2] != null) {
						if (tuple[2].equals("WithATO Approval")) {
							rowList.add("With ATO for Approval");
						} else {
							rowList.add(tuple[2]);
						}
					} else {
						rowList.add("");
					}
					if (tuple[3] != null) {
						if (!"".equals(tuple[3].toString().trim())) {
							rowList.add(tuple[3]);
						} else {
							rowList.add("Not Mapped with Auditor");
						}
					} else {
						rowList.add("Not Mapped with Auditor");
					}
					dataList.add(rowList);
					lIntSrNoCnt++;
				}
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return dataList;
	}
	
	/*aDDED by Swati
	
		public List<ComboValuesVO> getMonthsForHTE(String lStrLangId, String lStrLocCode) throws Exception {

			List<Object[]> lLstResult = null;
			ComboValuesVO lObjComboValueVO = null;
			List<ComboValuesVO> lLstMonths = new ArrayList<ComboValuesVO>();
			try {
				ghibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
				StringBuffer lSBQuery = new StringBuffer();
				lSBQuery.append	("select a.month,a.month_name from " );
				lSBQuery.append	("(SELECT case when month_id >=3 then month_id-3 else month_id+9 end as month,month_name FROM sGVA_MONTH_MST where LANG_ID='en_US') a order by a.month "); 
				Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
				//lQuery.setParameter("langId", lStrLangId);
				lLstResult = lQuery.list();

				if (lLstResult != null && !lLstResult.isEmpty()) {
					for (Object[] lArrObj : lLstResult) {
						if (lArrObj[0] != null && lArrObj[1] != null) {
							lObjComboValueVO = new ComboValuesVO();
							lObjComboValueVO.setId(lArrObj[0].toString());
							lObjComboValueVO.setDesc(lArrObj[1].toString());
							lLstMonths.add(lObjComboValueVO);
						}
					}
				}
			} catch (Exception e) {
				gLogger.error("Error is :" + e, e);
				throw e;
			}
			return lLstMonths;
		}*/
}
