package com.tcs.sgv.billproc.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.reports.CommonReportDAOImpl;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;

/**
 * com.tcs.sgv.billproc.reprot.ReportDAOImpl
 * 
 * This is Class for reporting framework repors which will be called for all
 * reports generated from reporting framework
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History
 * ===================== 
 * Vidhya M 23-Oct-2007 Made changes for code formatting
 */
public class ReportDAOImpl extends DefaultReportDataFinder implements
		ReportDataFinder {

	private static final Logger glogger = Logger
			.getLogger(CommonReportDAOImpl.class);

	private SessionFactory lObjSessionFactory = null;

	private ServiceLocator serviceLocator = null;

	Long langID = null;
	 /**
	 * Method to find Report Data
	 * @param  ReportVO : report,
	 * 		   Object  : criteria
	 * @return Collection
	 * @author vidhya
	 */
	public Collection findReportData(ReportVO report, Object criteria)
			throws ReportException {
		Map requestAttributes = (Map) ((Map) criteria)
				.get(IReportConstants.REQUEST_ATTRIBUTES);
		Map sessionAttributes = (Map) ((Map) criteria)
				.get(IReportConstants.SESSION_KEYS);
		//System.out.println(" Request map ............. "+ requestAttributes.toString());

		LoginDetails loginVO = (LoginDetails) sessionAttributes
				.get("loginDetails");

		//System.out.println(" loginVO............. " + loginVO);

		long locID = loginVO.getLocation().getLocId();
		long postId = loginVO.getLoggedInPost().getPostId();
		langID = loginVO.getLangId();
		//System.out.println(langID);
		report.setLocId(String.valueOf(locID));

		//System.out.println("location Id is:-" + locID);

		serviceLocator = (ServiceLocator) requestAttributes
				.get("serviceLocator");
		// get the SessionFactory instance for using it to fetch data...
		lObjSessionFactory = serviceLocator.getSessionFactory();

		List lArrReportData = new ArrayList();

		if (report.getReportCode().equals("51")) // Inwarded Bill Report
		{
			lArrReportData = getInwardedReport(report);
		}
		if (report.getReportCode().equals("53")) // Bill Tracking Report
		{
			lArrReportData = getBillTrackingReport(report);
		}
		if (report.getReportCode().equals("55")) // Bill wise Tracking Report
		{
			lArrReportData = getbillWiseTracReport(report);
		}
		if (report.getReportCode().equals("57")) // Audit Tracking Report
		{
			lArrReportData = getAuditTrackingReport(report);
		}
		if (report.getReportCode().equals("59")) // Cheque Delivery Report
		{
			lArrReportData = getChqDeliveryReport(report);
		}
		if (report.getReportCode().equals("61")) // Cheque status Report
		{
			lArrReportData = getCheckStatusRpt(report);
		}
		if (report.getReportCode().equals("63")) // undelivered Cheque Report
		{
			lArrReportData = getUndeliveredChequesReports(report);
		}
		if (report.getReportCode().equals("65")) // Unpaid Cheque Report
		{
			lArrReportData = getUnpaidChequeReport(report);
		}
		if (report.getReportCode().equals("82")) // Inwarded Bill Report
		{
			lArrReportData = getStatusForToken(report);
		}
		if (report.getReportCode().equals("84")) // Inwarded Bill Report
		{
			lArrReportData = getTokenDetails(report);
		}
		if (report.getReportCode().equals("86")) // Inwarded Bill Report
		{
			lArrReportData = getCancelledChequeDetails(report);
		}
		if (report.getReportCode().equals("88")) // Inwarded Bill Report
		{
			lArrReportData = getRenamedDetails(report);
		}
		if (report.getReportCode().equals("90")) // Inwarded Bill Report
		{
			lArrReportData = getRevalidationDetails(report);
		}
		if (report.getReportCode().equals("92")) // 
		{
			lArrReportData = getChequeAdviceCancellationDetails(report);
		}
		if (report.getReportCode().equals("94")) // 
		{
			lArrReportData = getChequeDuplicationDetails(report);
		}
		if (report.getReportCode().equals("96")) // 
		{
			lArrReportData = getTimeBarredDetails(report);
		}
		if (report.getReportCode().equals("98")) // 
		{
			lArrReportData = getOutSideChequeDetails(report);
		}
		if (report.getReportCode().equals("15")) // 
		{
			lArrReportData = getChequeDetails(report);
		}
		if (report.getReportCode().equals("67")) // Auditor Wise Daily Report
													// (Vito)
		{
			lArrReportData = getAudWiseDailyReport(report);
		}
		if (report.getReportCode().equals("69")) // Cheque Advice
													// Report(vito)
		{
			lArrReportData = getChequeAdviceRpt(report);
		}
		if (report.getReportCode().equals("71")) // Auditor Wise Bill Type
													// Report(vito)
		{
			lArrReportData = getAuditorWiseBillTypeReport(report);
		}
		if (report.getReportCode().equals("73")) // Bill Transit Register For
													// Audit(vito)
		{
			lArrReportData = getBillTransitRegReportForAudit(report);
		}
		if (report.getReportCode().equals("75")) // Bill Transit Register
													// After Audit(vito)
		{
			lArrReportData = getBillTransitRegReportAfterAudit(report);
		}
		if (report.getReportCode().equals("77")) // Inwarded Bill Report
		{
			lArrReportData = getChequeDrawnRegister(report);
		}
		if (report.getReportCode().equals("141")) // Cardex Wise Summary
		{
			lArrReportData = getCardexWiseSummary(report);
		}
		/*
		 * if (report.getReportCode().equals("27")) // Cheque Delivery Account
		 * Report { lArrReportData = getChequeDelAccRpt(report); }
		 */
		return lArrReportData;
	}
	/**
	 * Method to get Cardex Wise Summary
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getCardexWiseSummary(ReportVO reportObj) {
		List cardexWiseSummary = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strFormDate = reportObj.getParameterValue("Datefrom")
					.toString();
			String strToDate = reportObj.getParameterValue("Dateto").toString();
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			//System.out.println("Lang is is rpt bhavik" + langID);
			cardexWiseSummary = rptQueryDAO.getCardexWiseSummary(strFormDate,
					strToDate, locID, strLocationCode, langID);
		} catch (Exception ex) {
			System.out
					.println("Error Occured.......................................");
		}
		return cardexWiseSummary;
	}
	/**
	 * Method to get Inwarded Report
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getInwardedReport(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);

		try {
			Long subjectId = Long.parseLong(lObjReport.getParameterValue(
					"billType").toString());
			String fromDate = lObjReport.getParameterValue("InwardedDatefrom")
					.toString();
			String ffromDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate));
			String toDate = lObjReport.getParameterValue("InwardedDateto")
					.toString();
			String ftoDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate));
			Long modeId = Long.parseLong(lObjReport.getParameterValue(
					"inwardMode").toString());
			String mjrHead = (String) lObjReport.getParameterValue("majorHead");
			String ddoCode[] = (String[]) lObjReport
					.getParameterValue("ddoName");
			String audPostId[] = (String[]) lObjReport
					.getParameterValue("auditor");
			Long locId = Long.parseLong(lObjReport.getLocId());

			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getInwardedReport(subjectId, ffromDate,
					ftoDate, modeId, mjrHead, ddoCode, audPostId, locId,
					strLocationCode, langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lArrReturn;
	}
	/**
	 * Method to get Bill Tracking Report
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getBillTrackingReport(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate));
			String toDate = lObjReport.getParameterValue("dateto").toString();
			String ftoDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate));
			Long subjectId = Long.parseLong(lObjReport.getParameterValue(
					"billType").toString());
			String mjrHead = (String) lObjReport.getParameterValue("majorHead");
			String ddoCode[] = (String[]) lObjReport
					.getParameterValue("ddoName");
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			Long tokenNo = null;

			if (lObjReport.getParameterValue("tokenNo") != null
					&& !lObjReport.getParameterValue("tokenNo").equals("")) {
				tokenNo = Long.parseLong(lObjReport
						.getParameterValue("tokenNo").toString());
			}
			lArrReturn = rptQueryDAO.getBillDtlByBTraRpt(subjectId, ffromDate,
					ftoDate, mjrHead, ddoCode, tokenNo, locId, strLocationCode,
					langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lArrReturn;
	}
	/**
	 * Method to get Chq Delivery Report
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getChqDeliveryReport(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			//System.out.println(" Inside the get cheque Delivery report");
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate));
			String toDate = lObjReport.getParameterValue("dateto").toString();
			String ftoDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate));
			Long modeId = Long.parseLong(lObjReport.getParameterValue(
					"inwardMode").toString());
			String ddoCode[] = (String[]) lObjReport
					.getParameterValue("ddoName");
			Long subjectId = Long.parseLong(lObjReport.getParameterValue(
					"billType").toString());
			String audPostId[] = (String[]) lObjReport
					.getParameterValue("auditor");
			Long chequeNo = null;
			if (lObjReport.getParameterValue("chequeNo") != null
					&& !lObjReport.getParameterValue("chequeNo").equals(""))
				chequeNo = Long.parseLong(lObjReport.getParameterValue(
						"chequeNo").toString());

			Double chequeAmt = null;
			if (!lObjReport.getParameterValue("chequeAmt").equals(null)
					&& !lObjReport.getParameterValue("chequeAmt").equals(""))
				chequeAmt = Double.parseDouble(lObjReport.getParameterValue(
						"chequeAmt").toString());
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getChqDetailByDelRpt(subjectId, ffromDate,
					ftoDate, modeId, ddoCode, audPostId, chequeNo, chequeAmt,
					locId, strLocationCode, langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lArrReturn;
	}
	 /**
	 * Method to get bill Wise Trac Report
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getbillWiseTracReport(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		Long billNo = Long.parseLong(lObjReport.getParameterValue("billNo")
				.toString());
		//System.out.println(" bill No is :-" + billNo);
		Long locId = Long.parseLong(lObjReport.getLocId());
		String strLocationCode = getLocationCodeFromLocId(locId);
		lArrReturn = rptQueryDAO.getBillTrackDtls(billNo, strLocationCode,
				langID);
		//System.out.println("Out Side The Array List");
		return lArrReturn;
	}
	/**
	 * Method to  get Audit Tracking Report
	 * @param  ReportVO  : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getAuditTrackingReport(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate));
			String toDate = lObjReport.getParameterValue("dateto").toString();
			String ftoDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate));
			Long subjectId = Long.parseLong(lObjReport.getParameterValue(
					"billType").toString());
			String mjrHead = (String) lObjReport.getParameterValue("majorHead");
			String ddoUserId[] = (String[]) lObjReport
					.getParameterValue("ddoName");
			Long tokenNo = null;
			if (lObjReport.getParameterValue("tokenNo") != null
					&& !lObjReport.getParameterValue("tokenNo").equals("")) {
				tokenNo = Long.parseLong(lObjReport
						.getParameterValue("tokenNo").toString());
			}
			String auditstatus = (String) lObjReport
					.getParameterValue("auditstatus");
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getAuditTrackingRpt(ffromDate, ftoDate,
					subjectId, mjrHead, ddoUserId, tokenNo, auditstatus, locId,
					strLocationCode, langID);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lArrReturn;
	}
	/**
	 * Method to  get Undelivered Cheques Reports
	 * @param  ReportVO  : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getUndeliveredChequesReports(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate));
			String toDate = lObjReport.getParameterValue("dateto").toString();
			String ftoDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate));
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getUndeliveredChequesDetails(ffromDate,
					ftoDate, locId, strLocationCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (lArrReturn);
	}
	 /**
	 * Method to get Unpaid Cheque Report
	 * @param  ReportVO  : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getUnpaidChequeReport(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {

			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate));
			String toDate = lObjReport.getParameterValue("dateto").toString();
			String ftoDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate));
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getUnpaidChequeDetail(ffromDate, ftoDate,
					locId, strLocationCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (lArrReturn);
	}

	// Milind Report start here
	 /**
	 * Method to get Cheque Advice Cancellation Details
	 * @param  ReportVO  : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getChequeAdviceCancellationDetails(ReportVO reportObj) {
		List CancellationList = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strFromDate = reportObj.getParameterValue("Datefrom")
					.toString();
			String strToDate = reportObj.getParameterValue("Dateto").toString();
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			CancellationList = rptQueryDAO.getChequeAdviceCancellationData(
					strFromDate, strToDate, locID, strLocationCode);
		} catch (Exception ex) {
			System.out
					.println("Error Occured.......................................");
		}
		return CancellationList;
	}
	 /**
	 * Method to get Cheque Details
	 * 
	 * @param  ReportVO  : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getChequeDetails(ReportVO reportObj) {
		List chequeList = new ArrayList();
		String strChequeNo = reportObj.getParameterValue("ChequeNO").toString();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		String locID = reportObj.getLocId();
		String strLocationCode = getLocationCodeFromLocId(Long.parseLong(locID));
		chequeList = rptQueryDAO.getChequeData(strChequeNo, strLocationCode);
		return chequeList;
	}
	 /**
	 * Method to get Out Side Cheque Details
	 * 
	 * @param  ReportVO  : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getOutSideChequeDetails(ReportVO reportObj) {
		List outsideChequeList = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strFromDate = reportObj.getParameterValue("Datefrom")
					.toString();
			String strToDate = reportObj.getParameterValue("Dateto").toString();
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			outsideChequeList = rptQueryDAO.getOutSideChequeData(strFromDate,
					strToDate, locID, strLocationCode);
		} catch (Exception ex) {
			System.out
					.println("Error Occured.......................................");
		}
		return outsideChequeList;
	}
	 /**
	 * Method to get Time Barred Details
	 * 
	 * @param  ReportVO  : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getTimeBarredDetails(ReportVO reportObj) {
		List timeBarredList = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strFromDate = reportObj.getParameterValue("Datefrom")
					.toString();
			String strToDate = reportObj.getParameterValue("Dateto").toString();
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			timeBarredList = rptQueryDAO.getTimeBarredData(strFromDate,
					strToDate, locID, strLocationCode);
		} catch (Exception ex) {
			System.out
					.println("Error Occured.......................................");
		}
		return timeBarredList;
	}
	 /**
	 * Method to get Cheque Duplication Details
	 * 
	 * @param  ReportVO : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getChequeDuplicationDetails(ReportVO reportObj) {
		List duplicationList = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strFromDate = reportObj.getParameterValue("Datefrom")
					.toString();
			String strToDate = reportObj.getParameterValue("Dateto").toString();
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			duplicationList = rptQueryDAO.getChequeDuplicationData(strFromDate,
					strToDate, locID, strLocationCode);
		} catch (Exception ex) {
			System.out
					.println("Error Occured.......................................");
		}
		return duplicationList;
	}
	 /**
	 * Method to get Revalidation Details
	 * 
	 * @param  ReportVO : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getRevalidationDetails(ReportVO reportObj) {
		List revalidationList = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strFormDate = reportObj.getParameterValue("Datefrom")
					.toString();
			String strToDate = reportObj.getParameterValue("Dateto").toString();
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			revalidationList = rptQueryDAO.getRevalidationData(strFormDate,
					strToDate, locID, strLocationCode);
		} catch (Exception ex) {
			System.out
					.println("Error Occured.......................................");
		}
		return revalidationList;
	}
	 /**
	 * Method to get Renamed Details
	 * 
	 * @param  ReportVO : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getRenamedDetails(ReportVO reportObj) {
		List renamedList = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strFormDate = reportObj.getParameterValue("Datefrom")
					.toString();
			String strToDate = reportObj.getParameterValue("Dateto").toString();
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			renamedList = rptQueryDAO.getRenamedChequeData(strFormDate,
					strToDate, locID, strLocationCode);
		} catch (Exception ex) {
			System.out
					.println("Error Occured.......................................");
		}
		return renamedList;
	}
	 /**
	 * Method to get Cancelled Cheque Details
	 * 
	 * @param  ReportVO : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getCancelledChequeDetails(ReportVO reportObj) {
		List cancelledChequeList = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strFormDate = reportObj.getParameterValue("Datefrom")
					.toString();
			String strToDate = reportObj.getParameterValue("Dateto").toString();
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			cancelledChequeList = rptQueryDAO.getCancelledChequeData(
					strFormDate, strToDate, locID, strLocationCode);
		} catch (Exception ex) {
			System.out
					.println("Error Occured.......................................");
		}
		return cancelledChequeList;
	}
	 /**
	 * Method to get Token Details
	 * 
	 * @param  ReportVO : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getTokenDetails(ReportVO reportObj) {
		List tokenDetails = null;
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strTokenStatus = reportObj.getParameterValue("Status")
					.toString();
			//System.out.println("Status for the display report="					+ strTokenStatus);
			String locID = reportObj.getLocId();
			String strLocationCode = this.getLocationCodeFromLocId(Long
					.parseLong(locID));
			//System.out.println("Location ID :" + locID);

			tokenDetails = rptQueryDAO.getTokenDetailsData(strTokenStatus,
					locID, strLocationCode);
		} catch (Exception ex) {
			//System.out.println("ERROR-----------------------");
		}
		return tokenDetails;
	}
	 /**
	 * Method to get Status For Token
	 * 
	 * @param  ReportVO : reportObj
	 * @return List
	 * @author vidhya
	 */
	private List getStatusForToken(ReportVO reportObj) {
		//System.out.println("Start execution");
		List statusList = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String strTokenStatus = reportObj.getParameterValue("tokenStatus")
					.toString();
			//System.out.println("TokenStatus=====" + strTokenStatus);
			String locID = reportObj.getLocId();
			String strLocationCode = getLocationCodeFromLocId(Long
					.parseLong(locID));
			statusList = rptQueryDAO.getTokenReportDetails(strTokenStatus,
					locID, strLocationCode);
		} catch (Exception ex) {

		}
		return statusList;
	}
	 /**
	 * Method to get Check Status Report
	 * 
	 * @param  ReportVO lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getCheckStatusRpt(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate));
			String toDate = lObjReport.getParameterValue("dateto").toString();
			String ftoDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate));
			String status = (String) lObjReport.getParameterValue("Status");
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getChequeStatus(ffromDate, ftoDate,
					status, locId, strLocationCode, langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lArrReturn;
	}
	 /**
	 * Method to get Auditor Wise Daily Report
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	// Start Vito Report
	private List getAudWiseDailyReport(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = null;
			String ftoDate = null;
			if (fromDate != null && fromDate.trim().length() > 0) {
				ffromDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(fromDate));
			}
			String toDate = lObjReport.getParameterValue("dateto").toString();
			if (toDate != null && toDate.trim().length() > 0) {
				ftoDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(toDate));
			}
			Long audPostId = Long.parseLong(lObjReport.getParameterValue(
					"auditors").toString());

			Long vitoCode = null;
			if (lObjReport.getParameterValue("vitoCode") != null
					&& !lObjReport.getParameterValue("vitoCode").equals("")) {
				vitoCode = Long.parseLong(lObjReport.getParameterValue(
						"vitoCode").toString());
			}
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getAudWiseDailyRptDtls(ffromDate, ftoDate,
					audPostId, vitoCode, locId, strLocationCode, langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lArrReturn;
	}
	 /**
	 * Method to get Cheque Advice report
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getChequeAdviceRpt(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			//System.out.println(" Inside the get cheque Delivery report");
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = null;
			String ftoDate = null;
			if (fromDate != null && fromDate.trim().length() > 0) {
				ffromDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(fromDate));
			}
			String toDate = lObjReport.getParameterValue("dateto").toString();

			if (toDate != null && toDate.trim().length() > 0) {
				ftoDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(toDate));
			}
			Long vitoCode = null;
			if (lObjReport.getParameterValue("vitoCode") != null
					&& !lObjReport.getParameterValue("vitoCode").equals("")) {
				vitoCode = Long.parseLong(lObjReport.getParameterValue(
						"vitoCode").toString());
			}
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getChequeAdviceRpt(ffromDate, ftoDate,
					vitoCode, locId, strLocationCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lArrReturn;

	}
	 /**
	 * Method to get Auditor Wise Bill Type Report
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getAuditorWiseBillTypeReport(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			String ffromDate = null;
			String ftoDate = null;
			if (fromDate != null && fromDate.trim().length() > 0) {
				ffromDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(fromDate));
			}
			String toDate = lObjReport.getParameterValue("dateto").toString();
			if (toDate != null && toDate.trim().length() > 0) {
				ftoDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(toDate));
			}
			Long billType = Long.parseLong(lObjReport.getParameterValue(
					"cmbBillType").toString());
			String[] audPostId = (String[]) lObjReport
					.getParameterValue("cmbAuditor");
			Long vitoCode = null;
			if (lObjReport.getParameterValue("vitoCode") != null
					&& !lObjReport.getParameterValue("vitoCode").equals("")) {
				vitoCode = Long.parseLong(lObjReport.getParameterValue(
						"vitoCode").toString());
			}
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getAuditorWiseBillDetail(ffromDate,
					ftoDate, billType, audPostId, vitoCode, locId,
					strLocationCode, langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (lArrReturn);
	}
	 /**
	 * Method to get Bill Transit Register Report For Audit
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getBillTransitRegReportForAudit(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String ffromDate = null;
			String ftoDate = null;
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();

			if (fromDate != null && fromDate.trim().length() > 0) {
				ffromDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(fromDate));
			}
			String toDate = lObjReport.getParameterValue("dateto").toString();
			if (toDate != null && toDate.trim().length() > 0) {
				ftoDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(toDate));
			}
			String[] audPostId = (String[]) lObjReport
					.getParameterValue("auditorName");
			Long vitoCode = null;
			if (lObjReport.getParameterValue("vitoCode") != null
					&& !lObjReport.getParameterValue("vitoCode").equals("")) {
				vitoCode = Long.parseLong(lObjReport.getParameterValue(
						"vitoCode").toString());
			}
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getBillTranRegForAudDtls(ffromDate,
					ftoDate, audPostId, vitoCode, locId, strLocationCode,
					langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (lArrReturn);
	}
	 /**
	 * Method to get Bill Transit Register Report After Audit
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getBillTransitRegReportAfterAudit(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {

			String ffromDate = null;
			String ftoDate = null;
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();
			if (fromDate != null && fromDate.trim().length() > 0) {
				ffromDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(fromDate));
			}
			String toDate = lObjReport.getParameterValue("dateto").toString();
			if (toDate != null && toDate.trim().length() > 0) {
				ftoDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(toDate));
			}
			String[] audPostId = (String[]) lObjReport
					.getParameterValue("auditorName");
			String status = lObjReport.getParameterValue("statusName")
					.toString();
			Long vitoCode = null;
			if (lObjReport.getParameterValue("vitoCode") != null
					&& !lObjReport.getParameterValue("vitoCode").equals("")) {
				vitoCode = Long.parseLong(lObjReport.getParameterValue(
						"vitoCode").toString());
			}
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getBillTranRegAfterAudDtls(ffromDate,
					ftoDate, audPostId, status, vitoCode, locId,
					strLocationCode, langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (lArrReturn);
	}
	 /**
	 * Method to get Cheque Drawn Register
	 * 
	 * @param  ReportVO : lObjReport
	 * @return List
	 * @author vidhya
	 */
	private List getChequeDrawnRegister(ReportVO lObjReport) {
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO = (ReportQueryDAO) DAOFactory
				.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try {
			String fromDate = lObjReport.getParameterValue("datefrom")
					.toString();

			String ffromDate = null;
			String ftoDate = null;
			if (fromDate != null && fromDate.trim().length() > 0) {
				ffromDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(fromDate));
			}
			String toDate = lObjReport.getParameterValue("dateto").toString();
			if (toDate != null && toDate.trim().length() > 0) {
				ftoDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(new SimpleDateFormat("dd/MM/yyyy")
								.parse(toDate));
			}
			Long vitoCode = null;
			if (lObjReport.getParameterValue("vitoCode") != null
					&& !lObjReport.getParameterValue("vitoCode").equals("")) {
				vitoCode = Long.parseLong(lObjReport.getParameterValue(
						"vitoCode").toString());
			}
			Long locId = Long.parseLong(lObjReport.getLocId());
			String strLocationCode = getLocationCodeFromLocId(locId);
			lArrReturn = rptQueryDAO.getChequeDrawnRegisterRpt(ffromDate,
					ftoDate, vitoCode, locId, strLocationCode, langID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lArrReturn;
	}

	// End OF Vito Report
	/*
	 * private List getChequeDelAccRpt(ReportVO lObjReport) { List lArrReturn =
	 * new ArrayList(); ReportQueryDAO rptQueryDAO = (ReportQueryDAO)
	 * DAOFactory.Create("com.tcs.sgv.billproc.report.ReportQueryDAOImpl");
	 * rptQueryDAO.setSessionFactory(lObjSessionFactory); //System.out.println("
	 * Inside the get cheque Delivery report"); String
	 * fromDate=lObjReport.getParameterValue("datefrom").toString(); String
	 * toDate=lObjReport.getParameterValue("dateto").toString(); String
	 * majorHead[] =(String[])lObjReport.getParameterValue("majorHead");
	 * lArrReturn = rptQueryDAO.getChequeDelAccRpt(fromDate,toDate,majorHead);
	 * return lArrReturn; }
	 */
	 /**
	 * Main method
	 * 
	 * @param  String : args[]
	 * @return void
	 * @author vidhya
	 */
	public static void main(String args[]) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("07/06/2007");
			String ffromDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			//System.out.println(ffromDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	 /**
	 * Method to getting Location Code From Location Id
	 * 
	 * @param  Long : locId
	 * @return String
	 * @author vidhya
	 */
	private String getLocationCodeFromLocId(Long locId) {
		CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(
				CmnLocationMst.class, serviceLocator.getSessionFactory());
		CmnLocationMst cmnLocationMst = locationDAO.read(locId);
		return cmnLocationMst.getLocationCode();
	}

}
