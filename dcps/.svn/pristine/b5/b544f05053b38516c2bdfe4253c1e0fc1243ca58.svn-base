/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	May 30, 2011		Meeta Thacker								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.PostEmpContriDAO;
import com.tcs.sgv.dcps.dao.PostEmpContriDAOImpl;
import com.tcs.sgv.dcps.valueobject.PostEmpContri;

/**
 * Class Description -
 * 
 * 
 * @author Meeta Thacker
 * @version 0.1
 * @since JDK 5.0 May 30, 2011
 */
public class PostEmpContriServiceImpl extends ServiceImpl implements PostEmpContriService {

	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");

	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
		} catch (Exception e) {

		}
	}

	private StringBuilder getResponseXMLDoc(boolean flag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject loadPostEmpContri(Map<String, Object> inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

		try {
			setSessionInfo(inputMap);

			DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			PostEmpContriDAO objPostEmpContriDAO = new PostEmpContriDAOImpl(null, serv.getSessionFactory());

			String lStrUserType = StringUtility.getParameter("UserType", request).trim();
			inputMap.put("UserType", lStrUserType);
			List lLstFinYear = null;

			String lStrCurrFinYear = StringUtility.getParameter("finYear", request).trim();
			String lStrCurrMonth = StringUtility.getParameter("contriMonth", request).trim();

			Long lLngFinYear = 0L;
			if (!(lStrCurrFinYear.equals(""))) {
				lLngFinYear = Long.parseLong(lStrCurrFinYear);
			}

			Long lLngMonth = null;
			if (!(lStrCurrMonth.equals(""))) {
				lLngMonth = Long.parseLong(lStrCurrMonth);
			}

			lLstFinYear = objDcpsCommonDAO.getFinyears();
			List lLstMonths = objDcpsCommonDAO.getMonths();

			inputMap.put("FinYear", lLngFinYear);
			inputMap.put("ContriMonth", lStrCurrMonth);

			inputMap.put("lLstFinYear", lLstFinYear);
			inputMap.put("MONTHS", lLstMonths);

			Long lLngSanctionedBudget = 0L;

			Long lLngExpenditure = 0L;

			Long lLngAvailable = 0L;

			String lStrBillNo = null;

			Long lLngExcessAmount = 0L;

			if (lLngFinYear != 0) {
				lLngSanctionedBudget = objPostEmpContriDAO.getSancBudget(lLngFinYear);

				lLngExpenditure = objPostEmpContriDAO.getExpenditure(lLngFinYear);
			}

			lLngAvailable = lLngSanctionedBudget - lLngExpenditure;

			if (lLngAvailable < 0) {
				lLngAvailable = 0L;
			}

			lStrBillNo = objPostEmpContriDAO.getBillNumber(lLngFinYear);

			lLngExcessAmount = objPostEmpContriDAO.getExcessAmount(lLngFinYear);

			List lLstSavedContributions = objPostEmpContriDAO.getAllContributions(lStrUserType, lLngFinYear,
					lStrCurrMonth);
			Integer totalRecords = lLstSavedContributions.size();
			inputMap.put("lListContri", lLstSavedContributions);
			inputMap.put("totalRecords", totalRecords);
			inputMap.put("SancBudget", lLngSanctionedBudget);
			inputMap.put("Expenditure", lLngExpenditure);
			inputMap.put("AvailBudget", lLngAvailable);
			inputMap.put("BillNo", lStrBillNo);
			inputMap.put("ExcessAmount", lLngExcessAmount);

			String lFinYearCode = null;
			Double lDoubleExpInCurrBill = 0d;
			Long lLongExpInCurrBill = 0l;
			if (lLngMonth != null) {
				lFinYearCode = objDcpsCommonDAO.getFinYearCodeForYearId(lLngFinYear);
				lDoubleExpInCurrBill = objPostEmpContriDAO.getExpInCurrBill(lFinYearCode, lLngMonth);
				lLongExpInCurrBill = Long.valueOf(BigDecimal.valueOf(lDoubleExpInCurrBill).toBigInteger().toString());
				inputMap.put("ExpInCurrBill", lLongExpInCurrBill);
			}

		} catch (Exception e) {
			gLogger.error("Error is;" + e, e);
			e.printStackTrace();
		}

		resObj.setResultValue(inputMap);
		resObj.setViewName("DCPSPostEmpContribution");
		return resObj;
	}

	public ResultObject savePostEmpContri(Map<String, Object> inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Boolean lBFlag = false;

		try {
			setSessionInfo(inputMap);
			PostEmpContriDAO dcpsPostEmpContri = new PostEmpContriDAOImpl(PostEmpContriDAO.class, serv
					.getSessionFactory());
			DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			PostEmpContri objPostEmpContri = (PostEmpContri) inputMap.get("PostEmpContri");

			dcpsPostEmpContri.create(objPostEmpContri);

			String lStrYear = StringUtility.getParameter("finYear", request).trim();
			String lStrMonth = StringUtility.getParameter("contriMonth", request).trim();
			Long lLongYear = null;
			Long lLongMonth = null;

			if (!(lStrYear.equals(""))) {
				lLongYear = Long.parseLong(lStrYear);
			}

			if (!(lStrMonth.equals(""))) {
				lLongMonth = Long.parseLong(lStrMonth);
			}

			String lStrBillNo = StringUtility.getParameter("billNo", request).trim();
			String lStrYearCOde = objDcpsCommonDAO.getFinYearCodeForYearId(lLongYear);
			dcpsPostEmpContri.updateBillNoAndYearIdForPostEmpcontri(lStrBillNo, lLongYear, lStrYearCOde, lLongMonth);

			lBFlag = true;

			String lStrUserType = StringUtility.getParameter("UserType", request).trim();
			inputMap.put("UserType", lStrUserType);
		} catch (Exception e) {

			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error in getDigiSig " + e, e);
		}
		String lSBStatus = getResponseXMLDoc(lBFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	public ResultObject actionOnPostEmpContri(Map<String, Object> inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Boolean lBFlag = false;

		try {
			setSessionInfo(inputMap);
			PostEmpContriDAO objPostEmpContriDAO = new PostEmpContriDAOImpl(PostEmpContri.class, serv
					.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			String postEmpContriIds = StringUtility.getParameter("postEmpContriIds", request).trim();
			String action = StringUtility.getParameter("action", request).trim();
			String[] strArrPKValue = postEmpContriIds.split("~");

			for (int index = 0; index < strArrPKValue.length; index++) {

				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				PostEmpContri lObjPostEmpContri = (PostEmpContri) objPostEmpContriDAO.read(lLongPKValue);
				lObjPostEmpContri.setStatusFlag(action.charAt(0));
				lObjPostEmpContri.setUpdatedUserId(gLngUserId);
				lObjPostEmpContri.setUpdatedPostId(gLngPostId);
				lObjPostEmpContri.setUpdatedDate(gDtCurDate);

				if (action.equals("A")) {

					Long lLngExpenditure = objPostEmpContriDAO.getExpenditure(lObjPostEmpContri.getFinYear())
							+ lObjPostEmpContri.getBillAmount().longValue();

					lObjPostEmpContri.setExpenditureTillDate(lLngExpenditure);
					objPostEmpContriDAO.updateVoucherPostEmpStatusOnApproval(lObjPostEmpContri.getBillNo(),
							lObjPostEmpContri.getFinYear());
					String lStrFinYearCode = lObjDcpsCommonDAO.getFinYearCodeForYearId(lObjPostEmpContri.getFinYear());
					objPostEmpContriDAO.updateTrnDcpsContributionList(lStrFinYearCode, Long.parseLong(lObjPostEmpContri
							.getContriMonth()));
				}
				objPostEmpContriDAO.update(lObjPostEmpContri);
			}

			lBFlag = true;
			String lStrUserType = StringUtility.getParameter("UserType", request).trim();
			inputMap.put("UserType", lStrUserType);
		} catch (Exception e) {

			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error in getDigiSig " + e, e);
		}
		String lSBStatus = getResponseXMLDoc(lBFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	public ResultObject getSancBudgetForFinYear(Map<String, Object> inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAO */
			PostEmpContriDAO objPostEmpContriDAO = new PostEmpContriDAOImpl(null, serv.getSessionFactory());

			Long finYearId = Long.valueOf(StringUtility.getParameter("finYearId", request).trim());

			Long lLngSanctionedBudget = objPostEmpContriDAO.getSancBudget(finYearId);

			Long lLngExpenditure = objPostEmpContriDAO.getExpenditure(finYearId);
			String lStrBillNo = objPostEmpContriDAO.getBillNumber(finYearId);

			Long lLngExcessAmount = objPostEmpContriDAO.getExcessAmount(finYearId);

			String lSBSancBudget = getResponseXMLDocForSancBudget(lLngSanctionedBudget, lLngExpenditure, lStrBillNo,
					lLngExcessAmount).toString();

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBSancBudget).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");

		} catch (Exception e) {

			gLogger.error(" Error is : " + e, e);
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");

		}

		return resObj;
	}

	private StringBuilder getResponseXMLDocForSancBudget(Long sanBudget, Long expnTillDt, String lStrBillNumber,
			Long lLngExcessAmount) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<sancbudget>");
		lStrBldXML.append(sanBudget);
		lStrBldXML.append("</sancbudget>");
		lStrBldXML.append("<expnTillDt>");
		lStrBldXML.append(expnTillDt);
		lStrBldXML.append("</expnTillDt>");
		lStrBldXML.append("<BillNo>");
		lStrBldXML.append(lStrBillNumber);
		lStrBldXML.append("</BillNo>");
		lStrBldXML.append("<ExcessAmount>");
		lStrBldXML.append(lLngExcessAmount);
		lStrBldXML.append("</ExcessAmount>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject savePostEmpContriRows(Map<String, Object> inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Boolean lBFlag = false;
		String lStrBillno = null;
		Long lLongPostEmplrYearId = null;
		Long lLongEmplrVoucherNo = null;
		Date lDateEmplrVoucherDate = null;

		try {
			setSessionInfo(inputMap);
			PostEmpContriDAO objPostEmpContriDAO = new PostEmpContriDAOImpl(PostEmpContri.class, serv
					.getSessionFactory());
			String postEmpContriIds = StringUtility.getParameter("postEmpContriIds", request).trim();
			String billNo = StringUtility.getParameter("billNo", request).trim();
			String billAmount = StringUtility.getParameter("billAmount", request).trim();
			String voucherNo = StringUtility.getParameter("voucherNo", request).trim();
			String voucherDate = StringUtility.getParameter("voucherDate", request).trim();
			String receiptDate = StringUtility.getParameter("receiptDate", request).trim();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String[] strArrPKValue = postEmpContriIds.split(":");
			String[] strArrBillNo = billNo.split(":");
			String[] strArrBillAmount = billAmount.split(":");
			String[] strArrVoucherNo = voucherNo.split(":");
			String[] strArrVoucherDate = voucherDate.split(":");
			receiptDate.split(":");

			for (int index = 0; index < strArrPKValue.length; index++) {

				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				PostEmpContri lObjPostEmpContri = (PostEmpContri) objPostEmpContriDAO.read(lLongPKValue);
				if (strArrBillNo[index] != null && !(strArrBillNo[index].equals(""))) {
					lObjPostEmpContri.setBillNo(strArrBillNo[index]);
				} else {
					lObjPostEmpContri.setBillNo("");
				}
				if (strArrBillAmount[index] != null && !(strArrBillAmount[index].equals(""))) {
					lObjPostEmpContri.setBillAmount(Long.parseLong(strArrBillAmount[index]));
				} else {
					lObjPostEmpContri.setBillAmount(0L);
				}
				if (strArrVoucherNo[index] != null && !(strArrVoucherNo[index].equals(""))
						&& !(strArrVoucherNo[index].equals("NULL"))) {

					lObjPostEmpContri.setVoucherNo(Long.parseLong(strArrVoucherNo[index]));
				} else {
					lObjPostEmpContri.setVoucherNo(null);
				}
				if (strArrVoucherDate[index] != null && !(strArrVoucherDate[index].equals(""))
						&& !(strArrVoucherDate[index].equals("NULL"))) {
					lObjPostEmpContri.setVoucherDate(simpleDateFormat.parse(strArrVoucherDate[index]));
				} else {
					lObjPostEmpContri.setVoucherDate(null);
				}

				lObjPostEmpContri.setUpdatedUserId(gLngUserId);
				lObjPostEmpContri.setUpdatedPostId(gLngPostId);
				lObjPostEmpContri.setUpdatedDate(gDtCurDate);

				objPostEmpContriDAO.update(lObjPostEmpContri);

				// Updates voucher Details in MST_DCPS_CONTRI_VOUCHER_DTLS
				lStrBillno = lObjPostEmpContri.getBillNo();
				lLongPostEmplrYearId = lObjPostEmpContri.getFinYear();
				if (strArrVoucherNo[index] != null && !(strArrVoucherNo[index].equals("")) && !(strArrVoucherNo[index].equals("NULL")) ) {
					lLongEmplrVoucherNo = Long.parseLong(strArrVoucherNo[index]);
				}
				if (strArrVoucherDate[index] != null && !(strArrVoucherDate[index].equals(""))
						&& !(strArrVoucherDate[index].equals("NULL"))) {
					lDateEmplrVoucherDate = simpleDateFormat.parse(strArrVoucherDate[index]);
				}

				objPostEmpContriDAO.updatePostEmplrVoucherDtlsOfApprovedBills(lStrBillno, lLongPostEmplrYearId,
						lLongEmplrVoucherNo, lDateEmplrVoucherDate);

			}

			lBFlag = true;
			String lStrUserType = StringUtility.getParameter("UserType", request).trim();
			inputMap.put("UserType", lStrUserType);
		} catch (Exception e) {

			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		String lSBStatus = getResponseXMLDoc(lBFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}
}
