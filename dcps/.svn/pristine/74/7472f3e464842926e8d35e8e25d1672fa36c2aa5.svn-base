package com.tcs.sgv.dcps.service;

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

import com.tcs.sgv.common.dao.FinancialYearDAO;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.PostEmpContriDAO;
import com.tcs.sgv.dcps.dao.PostEmpContriDAOImpl;
import com.tcs.sgv.dcps.dao.TerminalRequestDAO;
import com.tcs.sgv.dcps.dao.TerminalRequestDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsContributionYearly;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.PostEmpContri;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;
import com.tcs.sgv.dcps.valueobject.TrnDcpsMissingCreditsDtls;
import com.tcs.sgv.dcps.valueobject.TrnDcpsTerminalDtls;

public class TerminalRequestServiceImpl extends ServiceImpl implements TerminalRequestService {
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
			gDtCurrDt = SessionHelper.getCurDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultObject loadTerminalRequest(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String lStrUser = null;
		String lStrUse = null;
		String lStrGoPressed = null;
		List listReasonsForTermination = null;
		List listMissingCredits = null;
		String lStrName = null;
		String lStrEmpId = null;
		String lStrDcpsId = null;
		Long lLongEmpId = null;
		String lStrDOJ = null;
		Date lDtDOJ = null;
		String lStrTerminationDate = null;
		Date lDtTerminationDate = null;
		String lStrReasonForTermination = null;
		String TerminalRequestAlreadyRaised = null;

		try {

			setSessionInfo(inputMap);
			TerminalRequestDAO lObjTerminalRequestDAO = new TerminalRequestDAOImpl(null, serv.getSessionFactory());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			listReasonsForTermination = IFMSCommonServiceImpl.getLookupValues("ReasonsForTermination", SessionHelper
					.getLangId(inputMap), inputMap);
			inputMap.put("listReasonsForTermination", listReasonsForTermination);

			lStrUser = StringUtility.getParameter("User", request).trim();
			lStrUse = StringUtility.getParameter("Use", request).trim();
			lStrGoPressed = StringUtility.getParameter("GoPressed", request).trim();

			if (lStrGoPressed.equals("Yes") && lStrUser.equals("DDO") && lStrUse.equals("NewRequest")) {
				lStrEmpId = StringUtility.getParameter("hidDcpsEmpId", request).trim();
				lLongEmpId = Long.valueOf(lStrEmpId);

				lStrName = StringUtility.getParameter("txtEmployeeName", request).trim();
				lStrDOJ = StringUtility.getParameter("hidJoiningDate", request).trim();
				lStrTerminationDate = StringUtility.getParameter("txtTerminationDate", request).trim();
				lStrDcpsId = StringUtility.getParameter("txtDCPSId", request).trim();
				lStrReasonForTermination = StringUtility.getParameter("cmbReasonForTermination", request).trim();
				lDtDOJ = sdf.parse(lStrDOJ);
				lDtTerminationDate = sdf.parse(lStrTerminationDate);

				if (lObjTerminalRequestDAO.checkTerminalRequestRaisedOrNot(lLongEmpId)) {
					TerminalRequestAlreadyRaised = "Yes";
				} else {
					TerminalRequestAlreadyRaised = "No";
					listMissingCredits = lObjTerminalRequestDAO.getAllMissingCreditsForEmp(lLongEmpId, lDtDOJ,
							lDtTerminationDate);
				}

				inputMap.put("TerminalRequestAlreadyRaised", TerminalRequestAlreadyRaised);
				inputMap.put("listMissingCredits", listMissingCredits);
				inputMap.put("lStrName", lStrName);
				inputMap.put("lStrEmpId", lStrEmpId);
				inputMap.put("lStrDcpsId", lStrDcpsId);
				inputMap.put("lStrDOJ", lStrDOJ);
				inputMap.put("lStrTerminationDate", lStrTerminationDate);
				inputMap.put("lStrReasonForTermination", lStrReasonForTermination);

				// Value 0 indicates that it is first time load for the page so
				// from save/update, save will be there.
				inputMap.put("hidTerminalId", 0);

			}

			inputMap.put("hidUser", lStrUser);
			inputMap.put("hidUse", lStrUse);
			inputMap.put("GoPressed", lStrGoPressed);

			resObj.setViewName("TerminalRequest");
			resObj.setResultValue(inputMap);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resObj;
	}

	public ResultObject getEmpDtlsForName(Map<String, Object> inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lListEmpDetails = null;

		try {

			setSessionInfo(inputMap);
			TerminalRequestDAO lObjTerminalRequestDAO = new TerminalRequestDAOImpl(null, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			new SimpleDateFormat("dd/MM/yyyy");

			String lStrEmpName = StringUtility.getParameter("empName", request).trim();
			String lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);

			if (!"".equals(lStrEmpName)) {
				lListEmpDetails = lObjTerminalRequestDAO.getEmpDtlsForName(lStrEmpName, lStrDDOCode);
			}

			String lSBStatus = getResponseXMLDocForEmpDtls(lListEmpDetails).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");
			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject viewTerminalRequests(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String lStrUser = null;
		String lStrUse = null;
		List listTerminalRequests = null;
		String lStrDDOCode = null;

		try {

			setSessionInfo(inputMap);
			TerminalRequestDAO lObjTerminalRequestDAO = new TerminalRequestDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			new SimpleDateFormat("dd/MM/yyyy");

			lStrUser = StringUtility.getParameter("User", request).trim();
			lStrUse = StringUtility.getParameter("Use", request).trim();

			if (lStrUser.equals("DDO")) {
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId).trim();
			}

			listTerminalRequests = lObjTerminalRequestDAO.getAllTerminalRequests(lStrDDOCode, gStrLocationCode,
					lStrUser, lStrUse);

			inputMap.put("listTerminalRequests", listTerminalRequests);
			inputMap.put("hidUser", lStrUser);
			inputMap.put("hidUse", lStrUse);

			resObj.setViewName("TerminalRequestList");
			resObj.setResultValue(inputMap);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resObj;
	}

	private StringBuilder getResponseXMLDocForEmpDtls(List finalList) throws Exception {

		StringBuilder lStrBldXML = new StringBuilder();
		SimpleDateFormat lObjSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat lObjSimpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		Object[] obj = null;

		if (finalList != null) {
			obj = (Object[]) finalList.get(0);
		}

		lStrBldXML.append("<XMLDOC>");

		lStrBldXML.append("<dcpsEmpId>");
		if (obj[0] != null) {
			lStrBldXML.append(obj[0].toString().trim());
		} else {
			lStrBldXML.append("");
		}
		lStrBldXML.append("</dcpsEmpId>");

		lStrBldXML.append("<dcpsId>");
		if (obj[1] != null) {
			lStrBldXML.append(obj[1].toString().trim());
		} else {
			lStrBldXML.append("");
		}
		lStrBldXML.append("</dcpsId>");

		lStrBldXML.append("<doj>");
		if (obj[2] != null) {
			lStrBldXML.append(lObjSimpleDateFormat2.format(lObjSimpleDateFormat1.parse(obj[2].toString().trim()))
					.trim());
		} else {
			lStrBldXML.append("");
		}
		lStrBldXML.append("</doj>");

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject saveOrForwardTerminalRequest(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		Long lLongTerminalId = null;
		String lStrUser = null;
		String lStrUse = null;
		String lStrSaveOrForwardFlag = null;
		String lStrDDOCode = null;
		String lStrTreasuryCode = null;
		Long lLongTreasuryCode = null;
		Long lLongTerminalIdPassed = null;
		Map attachMap = null;
		Long formA_attachId = null;
		Long deathCerti_attachId = null;
		Long r3_attachId = null;

		try {
			setSessionInfo(inputMap);
			TerminalRequestDAO lObjTerminalRequestDAO = new TerminalRequestDAOImpl(TrnDcpsTerminalDtls.class, serv
					.getSessionFactory());

			TerminalRequestDAO lObjTerminalRequestMissingCreditsDAO = new TerminalRequestDAOImpl(
					TrnDcpsMissingCreditsDtls.class, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			lStrUser = StringUtility.getParameter("hidUser", request).trim();
			lStrUse = StringUtility.getParameter("hidUse", request).trim();
			lStrSaveOrForwardFlag = StringUtility.getParameter("saveOrForwardFlag", request).trim();

			// Gets DDO Code and Treasury Code
			if (lStrUser.equals("DDO")) {
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId).trim();
				lStrTreasuryCode = lObjDcpsCommonDAO.getTreasuryCodeForDDO(lStrDDOCode);
				if (!"".equals(lStrTreasuryCode) && lStrTreasuryCode != null) {
					lLongTreasuryCode = Long.valueOf(lStrTreasuryCode);
				}
			}

			TrnDcpsTerminalDtls lObjTrnDcpsTerminalDtls = (TrnDcpsTerminalDtls) inputMap.get("lObjTrnDcpsTerminalDtls");

			// Sets Attachment Ids in TrnDcpsTerminalDtls for User - DDO and Use
			// - NewRequest or FromDraft
			if (lStrUser.equals("DDO") && (lStrUse.equals("NewRequest") || lStrUse.equals("FromDraft"))) {
				resObj = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);

				resObj = serv.executeService("FILE_UPLOAD_SRVC", inputMap);

				attachMap = (Map) resObj.getResultValue();

				if (attachMap != null) {
					if (attachMap.get("AttachmentId_FormA") != null) {
						formA_attachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_FormA")));
						CmnAttachmentMst attachmentMst = new CmnAttachmentMst();
						attachmentMst.setAttachmentId(formA_attachId);
						lObjTrnDcpsTerminalDtls.setFormA_attachId(formA_attachId);
					}
					if (attachMap.get("AttachmentId_DeathCertificate") != null) {
						deathCerti_attachId = Long.parseLong(String.valueOf(attachMap
								.get("AttachmentId_DeathCertificate")));
						CmnAttachmentMst attachmentMst = new CmnAttachmentMst();
						attachmentMst.setAttachmentId(deathCerti_attachId);
						lObjTrnDcpsTerminalDtls.setDeathCerti_attachId(deathCerti_attachId);
					}
					if (attachMap.get("AttachmentId_R3Report") != null) {
						r3_attachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_R3Report")));
						CmnAttachmentMst attachmentMst = new CmnAttachmentMst();
						attachmentMst.setAttachmentId(r3_attachId);
						lObjTrnDcpsTerminalDtls.setR3_attachId(r3_attachId);
					}
				}
			}

			if (lStrUser.equals("DDO") && lStrUse.equals("NewRequest")) {
				lLongTerminalId = IFMSCommonServiceImpl.getNextSeqNum("trn_dcps_terminal_dtls", inputMap);
				lObjTrnDcpsTerminalDtls.setTerminalId(lLongTerminalId);

				if (lStrSaveOrForwardFlag.equals("1")) // save by DDO
				{
					lObjTrnDcpsTerminalDtls.setStatusFlag(0l);
				} else if (lStrSaveOrForwardFlag.equals("2")) // forward by DDO
				// to TO
				{
					lObjTrnDcpsTerminalDtls.setStatusFlag(1l);
				}

				lObjTrnDcpsTerminalDtls.setDdoCode(lStrDDOCode);
				lObjTrnDcpsTerminalDtls.setTreasuryCode(lLongTreasuryCode);

				lObjTrnDcpsTerminalDtls.setCreatedPostId(gLngPostId);
				lObjTrnDcpsTerminalDtls.setCreatedUserId(gLngUserId);
				lObjTrnDcpsTerminalDtls.setCreatedDate(gDtCurrDt);
				lObjTerminalRequestDAO.create(lObjTrnDcpsTerminalDtls);
			} else if (lStrUser.equals("DDO") && lStrUse.equals("FromDraft")) {
				if (lStrSaveOrForwardFlag.equals("1")) // save by DDO
				{
					lObjTrnDcpsTerminalDtls.setStatusFlag(0l);
				} else if (lStrSaveOrForwardFlag.equals("2")) // forward by DDO
				// to TO
				{
					lObjTrnDcpsTerminalDtls.setStatusFlag(1l);
				}
				lObjTrnDcpsTerminalDtls.setUpdatedPostId(gLngPostId);
				lObjTrnDcpsTerminalDtls.setUpdatedUserId(gLngUserId);
				lObjTrnDcpsTerminalDtls.setUpdatedDate(gDtCurrDt);
				lObjTerminalRequestDAO.update(lObjTrnDcpsTerminalDtls);
			} else if (lStrUser.equals("TO")) {
				if (lStrSaveOrForwardFlag.equals("3")) // save by TO
				{
					lObjTrnDcpsTerminalDtls.setStatusFlag(1l);
				} else if (lStrSaveOrForwardFlag.equals("4")) // forward by TO
				// to DDO
				{
					lObjTrnDcpsTerminalDtls.setStatusFlag(2l);
				}
				lObjTrnDcpsTerminalDtls.setUpdatedPostId(gLngPostId);
				lObjTrnDcpsTerminalDtls.setUpdatedUserId(gLngUserId);
				lObjTrnDcpsTerminalDtls.setUpdatedDate(gDtCurrDt);
				lObjTerminalRequestDAO.update(lObjTrnDcpsTerminalDtls);
			} else if (lStrUser.equals("DDO") && lStrUse.equals("FromTO")) {
				if (lStrSaveOrForwardFlag.equals("5")) // Forward to SRKA by DDO
				// after getting from TO
				{
					lObjTrnDcpsTerminalDtls.setStatusFlag(3l);
				}
				lObjTrnDcpsTerminalDtls.setUpdatedPostId(gLngPostId);
				lObjTrnDcpsTerminalDtls.setUpdatedUserId(gLngUserId);
				lObjTrnDcpsTerminalDtls.setUpdatedDate(gDtCurrDt);
				lObjTerminalRequestDAO.update(lObjTrnDcpsTerminalDtls);
			} else if (lStrUser.equals("SRKA")) {
				lObjTrnDcpsTerminalDtls.setStatusFlag(4l);

				lObjTrnDcpsTerminalDtls.setUpdatedPostId(gLngPostId);
				lObjTrnDcpsTerminalDtls.setUpdatedUserId(gLngUserId);
				lObjTrnDcpsTerminalDtls.setUpdatedDate(gDtCurrDt);
				lObjTerminalRequestDAO.update(lObjTrnDcpsTerminalDtls);

				// Entry in contribution yearly table

				TerminalRequestDAO lObjTerminalRequestDAOForYearly = new TerminalRequestDAOImpl(
						MstDcpsContributionYearly.class, serv.getSessionFactory());
				MstDcpsContributionYearly lObjMstDcpsContributionYearly = (MstDcpsContributionYearly) inputMap
						.get("lObjMstDcpsContributionYearly");
				lObjMstDcpsContributionYearly.setCreatedDate(gDtCurDate);
				lObjMstDcpsContributionYearly.setCreatedPostId(gLngPostId);
				lObjMstDcpsContributionYearly.setCreatedUserId(gLngUserId);

				lObjTerminalRequestDAOForYearly.create(lObjMstDcpsContributionYearly);
				// end of Entry in contribution yearly table
			}

			if ((lStrUser.equals("DDO") && (lStrUse.equals("NewRequest") || lStrUse.equals("FromDraft")))
					|| (lStrUser.equals("TO")))

			{
				List<TrnDcpsMissingCreditsDtls> lListTrnDcpsMissingCredits = (List<TrnDcpsMissingCreditsDtls>) inputMap
						.get("lListTrnDcpsMissingCredits");
				TrnDcpsMissingCreditsDtls lObjTrnDcpsMissingCreditsDtls = null;
				Long lLongMissingCreditId = null;
				String lStrHidMissingCreditSPK = null;

				lLongTerminalIdPassed = Long.valueOf(StringUtility.getParameter("hidTerminalId", request).trim());

				Integer lIntTotalMissingCredits = lListTrnDcpsMissingCredits.size();
				for (Integer lInt = 0; lInt < lIntTotalMissingCredits; lInt++) {
					lObjTrnDcpsMissingCreditsDtls = lListTrnDcpsMissingCredits.get(lInt);
					lStrHidMissingCreditSPK = StringUtility.getParameter("hidMissingCreditPK" + (lInt + 1), request)
							.trim();

					if (lStrHidMissingCreditSPK.equals("0")) {
						lLongMissingCreditId = IFMSCommonServiceImpl.getNextSeqNum("trn_dcps_missing_credits_dtls",
								inputMap);
						lObjTrnDcpsMissingCreditsDtls.setMissingCreditId(lLongMissingCreditId);

						// Below 3 lines set terminal Id for the records in
						// Missing Credit table in draft which were not saved
						// earlier
						if (lLongTerminalId == null && lStrUser.equals("DDO") && lStrUse.equals("FromDraft")) {
							lLongTerminalId = lLongTerminalIdPassed;
						}

						lObjTrnDcpsMissingCreditsDtls.setRltTerminalId(lLongTerminalId);

						lObjTrnDcpsMissingCreditsDtls.setDdoCode(lStrDDOCode);
						lObjTrnDcpsMissingCreditsDtls.setTreasuryCode(lLongTreasuryCode);

						lObjTrnDcpsMissingCreditsDtls.setCreatedPostId(gLngPostId);
						lObjTrnDcpsMissingCreditsDtls.setCreatedUserId(gLngUserId);
						lObjTrnDcpsMissingCreditsDtls.setCreatedDate(gDtCurrDt);
						lObjTerminalRequestMissingCreditsDAO.create(lObjTrnDcpsMissingCreditsDtls);
					} else {
						lObjTrnDcpsMissingCreditsDtls.setUpdatedPostId(gLngPostId);
						lObjTrnDcpsMissingCreditsDtls.setUpdatedUserId(gLngUserId);
						lObjTrnDcpsMissingCreditsDtls.setUpdatedDate(gDtCurrDt);
						lObjTerminalRequestMissingCreditsDAO.update(lObjTrnDcpsMissingCreditsDtls);
					}
				}

			}
			/*
			 * 
			 * objRes = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);
			 * 
			 * objRes = serv.executeService("FILE_UPLOAD_SRVC", inputMap);
			 * 
			 * Map attachMap = (Map) objRes.getResultValue();
			 * 
			 * Long lLngAttachId = 0L; if (attachMap.get("AttachmentId_Photo")
			 * != null) { lLngAttachId = Long.parseLong(String.valueOf(attachMap
			 * .get("AttachmentId_Photo")));
			 * lObjEmpData.setPhotoAttachmentID(lLngAttachId); }
			 * 
			 * if (attachMap.get("AttachmentId_Sign") != null) { lLngAttachId =
			 * Long.parseLong(String.valueOf(attachMap
			 * .get("AttachmentId_Sign")));
			 * lObjEmpData.setSignatureAttachmentID(lLngAttachId); }
			 * 
			 * lLngEmpId = IFMSCommonServiceImpl.getNextSeqNum("MST_DCPS_EMP",
			 * inputMap); lObjEmpData.setDcpsEmpId(lLngEmpId);
			 * lObjNewRegDdoDAO.create(lObjEmpData);
			 * 
			 * lObjRltDcpsPayrollEmp = new RltDcpsPayrollEmp();
			 * lObjRltDcpsPayrollEmp = (RltDcpsPayrollEmp) inputMap
			 * .get("DCPSEmpPayrollData"); lLngDcpsPayrollEmpId =
			 * IFMSCommonServiceImpl.getNextSeqNum( "RLT_DCPS_PAYROLL_EMP",
			 * inputMap); lObjRltDcpsPayrollEmp.setDcpsEmpId(lLngEmpId);
			 * lObjRltDcpsPayrollEmp.setDcpsPayrollEmpId(lLngDcpsPayrollEmpId);
			 * lObjNewRegDdoDAO.create(lObjRltDcpsPayrollEmp);
			 */

			lBlFlag = true;

		} catch (Exception ex) {
			resObj.setResultValue(null);
			gLogger.error(" Error is : " + ex, ex);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
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

	public ResultObject popUpTerminalDetails(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String lStrUser = null;
		String lStrUse = null;
		List listMissingCredits = null;
		List listReasonsForTermination = null;
		Long lLongTerminalId = null;
		Long lLongDcpsEmpId = null;
		TrnDcpsTerminalDtls lObjTrnDcpsTerminalDtls = null;
		MstEmp lObjMstEmp = null;
		String lStrGoPressed = null;
		String lStrName = null;
		String lStrEmpId = null;
		String lStrDcpsId = null;
		String lStrDOJ = null;
		Date lDtDOJ = null;
		String lStrTerminationDate = null;
		Date lDtTerminationDate = null;
		String lStrReasonForTermination = null;

		try {

			setSessionInfo(inputMap);
			TerminalRequestDAO lObjTerminalRequestDAO = new TerminalRequestDAOImpl(TrnDcpsTerminalDtls.class, serv
					.getSessionFactory());
			TerminalRequestDAO lObjTerminalMstEmpDAO = new TerminalRequestDAOImpl(MstEmp.class, serv
					.getSessionFactory());

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			listReasonsForTermination = IFMSCommonServiceImpl.getLookupValues("ReasonsForTermination", SessionHelper
					.getLangId(inputMap), inputMap);
			inputMap.put("listReasonsForTermination", listReasonsForTermination);

			lStrUser = StringUtility.getParameter("User", request).trim();
			lStrUse = StringUtility.getParameter("Use", request).trim();
			lLongTerminalId = Long.valueOf(StringUtility.getParameter("terminalId", request).trim());

			lObjTrnDcpsTerminalDtls = (TrnDcpsTerminalDtls) lObjTerminalRequestDAO.read(lLongTerminalId);
			lLongDcpsEmpId = lObjTrnDcpsTerminalDtls.getDcpsEmpId();
			lObjMstEmp = (MstEmp) lObjTerminalMstEmpDAO.read(lLongDcpsEmpId);
			lStrName = lObjMstEmp.getName().trim();
			lStrEmpId = lLongDcpsEmpId.toString();
			lStrDcpsId = lObjMstEmp.getDcpsId();
			lStrDOJ = sdf.format(lObjMstEmp.getDoj());

			lStrGoPressed = StringUtility.getParameter("GoPressed", request).trim();

			if (lStrGoPressed.equals("Yes") && lStrUser.equals("DDO")) {
				lStrTerminationDate = StringUtility.getParameter("txtTerminationDate", request).trim();
				lDtTerminationDate = sdf.parse(lStrTerminationDate);
				lDtDOJ = sdf.parse(lStrDOJ);
				lStrReasonForTermination = StringUtility.getParameter("cmbReasonForTermination", request).trim();
				listMissingCredits = lObjTerminalRequestDAO.getAllMissingCreditsForEmp(lLongDcpsEmpId, lDtDOJ,
						lDtTerminationDate);
			} else {
				listMissingCredits = lObjTerminalRequestDAO.getAllMissingCreditsSavedForTerminalId(lLongTerminalId);
				lStrTerminationDate = sdf.format(lObjTrnDcpsTerminalDtls.getDateOfTermination());
				lStrReasonForTermination = lObjTrnDcpsTerminalDtls.getReasonOfTermination().toString();
			}

			CmnAttachmentMstDAO lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv
					.getSessionFactory());
			CmnAttachmentMst lObjCmnAttachmentMst = null;
			if (lObjTrnDcpsTerminalDtls.getFormA_attachId() != null) {
				lObjCmnAttachmentMst = new CmnAttachmentMst();
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO.findByAttachmentId(lObjTrnDcpsTerminalDtls
						.getFormA_attachId());
				inputMap.put("FormA", lObjCmnAttachmentMst);
			}
			if (lObjTrnDcpsTerminalDtls.getDeathCerti_attachId() != null) {
				lObjCmnAttachmentMst = new CmnAttachmentMst();
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO.findByAttachmentId(lObjTrnDcpsTerminalDtls
						.getDeathCerti_attachId());
				inputMap.put("DeathCertificate", lObjCmnAttachmentMst);
			}
			if (lObjTrnDcpsTerminalDtls.getR3_attachId() != null) {
				lObjCmnAttachmentMst = new CmnAttachmentMst();
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO.findByAttachmentId(lObjTrnDcpsTerminalDtls
						.getR3_attachId());
				inputMap.put("R3Report", lObjCmnAttachmentMst);
			}

			inputMap.put("lStrName", lStrName);
			inputMap.put("lStrEmpId", lStrEmpId);
			inputMap.put("lStrDcpsId", lStrDcpsId);
			inputMap.put("lStrDOJ", lStrDOJ);
			inputMap.put("lStrTerminationDate", lStrTerminationDate);
			inputMap.put("lStrReasonForTermination", lStrReasonForTermination);

			inputMap.put("listMissingCredits", listMissingCredits);
			inputMap.put("hidUser", lStrUser);
			inputMap.put("hidUse", lStrUse);
			inputMap.put("hidTerminalId", lLongTerminalId);

			if (lStrUser.equals("SRKA")) {
				FinancialYearDAO lObjFinancialYearDAO = new FinancialYearDAOImpl(SgvcFinYearMst.class, serv
						.getSessionFactory());
				Long lLngFinYearId = new Long(lObjFinancialYearDAO.getFinYearIdByCurDate());
				lDtTerminationDate = sdf.parse(lStrTerminationDate);
				Double lDblOpeningBal = lObjTerminalRequestDAO.getOpeningBalanceForDcpsId(lStrDcpsId, lLngFinYearId);

				Double lDblEmplrContriPaid = lObjTerminalRequestDAO.getPaidEmployerContributionForYear(lStrDcpsId,
						lLngFinYearId);
				Double lDblEmplrContriPending = lObjTerminalRequestDAO.getPendingEmployerContributionForYear(
						lStrDcpsId, lLngFinYearId);
				Double lDblMissingCredits = lObjTerminalRequestDAO.getTotalMissingCreditsForEmp(lStrDcpsId);
				Double lDblTier2Contri = lObjTerminalRequestDAO.getTier2ContributionForYear(lStrDcpsId, lLngFinYearId);

				Date lDtPreYearEndDate = lObjTerminalRequestDAO.getEndDateForFinYear(lLngFinYearId - 1);
				Long lLngDaysDiff = (lDtTerminationDate.getTime() - lDtPreYearEndDate.getTime())
						/ (1000 * 60 * 60 * 24);
				Double lDblDays = lLngDaysDiff.doubleValue() / 365;
				Double lDblTier2ContriInterest = lDblTier2Contri * lDblDays * 0.08;

				// interest on opening balance
				lLngDaysDiff = (lDtTerminationDate.getTime() - lDtPreYearEndDate.getTime()) / (1000 * 60 * 60 * 24);
				lDblDays = lLngDaysDiff.doubleValue() / 365;
				Double lDblInterestOpenBal = lDblOpeningBal * lDblDays * 0.08;

				// end interest on opening balance

				// interest calculation for current year contribution
				Double lDblInterestForContribution = 0d;
				List lLstCurrYearContribution = lObjTerminalRequestDAO.getContributionTillDate(lStrDcpsId,
						lLngFinYearId);
				Double lDblAmount = 0d;
				Date lDtVoucherDate = null;
				Double lDblInterest = 0d;
				if (lLstCurrYearContribution != null && lLstCurrYearContribution.size() > 0) {
					for (Integer index = 0; index < lLstCurrYearContribution.size(); index++) {
						Object[] lArrObj = (Object[]) lLstCurrYearContribution.get(index);
						lDblAmount = (Double) lArrObj[0];
						lDtVoucherDate = (Date) lArrObj[1];
						if (lDtVoucherDate != null) {
							lLngDaysDiff = (lDtTerminationDate.getTime() - lDtVoucherDate.getTime())
									/ (1000 * 60 * 60 * 24);
							lDblDays = lLngDaysDiff.doubleValue() / 365;
							lDblInterest = lDblAmount * lDblDays * 0.08;
							lDblInterestForContribution = lDblInterestForContribution + lDblInterest;
						}
					}
				}
				// end interest calculation for current year contribution

				// interest calculation for missing credits
				lDblAmount = 0d;
				lDtVoucherDate = null;
				Double lDblInterestForMissingCredits = 0d;
				Integer lIntMissingCreditYear = 0;
				Integer lIntCurrYear = lLngFinYearId.intValue();
				Character lCharMissingCreditsFlag = lObjTrnDcpsTerminalDtls.getMissingCreditEmployerContriFlag();
				List lLstMissingCredits = lObjTerminalRequestDAO.getMissingCreditsForDcpsId(lStrDcpsId);
				Object[] lArrObjMissingCredit = null;
				if (lLstMissingCredits != null && lLstMissingCredits.size() > 0) {
					Boolean lBlFirstYear = true;
					Double lDblInterestPerCredit = 0d;
					Date lDtYearEndDate = null;
					Date lDtYearStartDate = null;
					for (Integer index2 = 0; index2 < lLstMissingCredits.size(); index2++) {
						lArrObjMissingCredit = (Object[]) lLstMissingCredits.get(index2);
						lDblAmount = (Double) lArrObjMissingCredit[0];
						lDtVoucherDate = (Date) lArrObjMissingCredit[1];

						lIntMissingCreditYear = lObjFinancialYearDAO.getFinYearId(sdf.format(lDtVoucherDate));
						lBlFirstYear = true;
						lDblInterestPerCredit = 0d;
						for (Integer index3 = lIntMissingCreditYear; index3 <= lIntCurrYear; index3++) {

							if (index3 == lIntCurrYear) {
								lDtYearStartDate = lObjTerminalRequestDAO.getStartDateForFinYear(index3.longValue());
								if (index3 == lIntMissingCreditYear) {
									lDtYearStartDate = lDtVoucherDate;
								}
								lLngDaysDiff = (lDtTerminationDate.getTime() - lDtYearStartDate.getTime())
										/ (1000 * 60 * 60 * 24);
								lDblDays = lLngDaysDiff.doubleValue() / 365;
								lDblInterest = lDblAmount * lDblDays * 0.08;
								lDblInterestPerCredit = lDblInterestPerCredit + lDblInterest;

							} else {
								if (lBlFirstYear) {
									lDtYearEndDate = lObjTerminalRequestDAO.getEndDateForFinYear(index3.longValue());
									if (lDtVoucherDate != null) {
										lLngDaysDiff = (lDtYearEndDate.getTime() - lDtVoucherDate.getTime())
												/ (1000 * 60 * 60 * 24);
										lDblDays = lLngDaysDiff.doubleValue() / 365;
										lDblInterest = lDblAmount * lDblDays * 0.08;
										lDblInterestPerCredit = lDblInterestPerCredit + lDblInterest;
									}
									lBlFirstYear = false;
								} else {
									lDblInterest = lDblAmount * 0.08;
									lDblInterestPerCredit = lDblInterestPerCredit + lDblInterest;
								}
							}
						}
						lDblInterestForMissingCredits = lDblInterestForMissingCredits + lDblInterestPerCredit;
					}
				}
				if (lCharMissingCreditsFlag == null) {
					lDblEmplrContriPending = lDblEmplrContriPending + lDblMissingCredits;
				} else {
					lDblEmplrContriPaid = lDblEmplrContriPaid + lDblMissingCredits;
				}

				inputMap.put("lDblOpeningBal", lDblOpeningBal);
				inputMap.put("lDblCurrYearContriPaid", lDblEmplrContriPaid);
				inputMap.put("lDblCurrYearContriPending", lDblEmplrContriPending);

				inputMap.put("lDblInterestOpenBal", lDblInterestOpenBal);
				inputMap.put("lDblInterestForContribution", lDblInterestForContribution * 2);
				inputMap.put("lDblInterestForMissingCredits", lDblInterestForMissingCredits);
				inputMap.put("lDblTier2Contri", lDblTier2Contri);
				inputMap.put("lDblTier2ContriInterest", lDblTier2ContriInterest);

				resObj.setViewName("TerminalRequestSRKA");
			} else {
				resObj.setViewName("TerminalRequest");
			}
			resObj.setResultValue(inputMap);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resObj;
	}

	private StringBuilder getResponseXMLDoc(Boolean flag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<FLAG>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</FLAG>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject deleteTerminalRequest(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String lStrTerminalId = null;
		Long lLongTerminalId = null;
		Boolean lBlFlag = false;

		try {

			setSessionInfo(inputMap);
			TerminalRequestDAO lObjTerminalRequestDAO = new TerminalRequestDAOImpl(TrnDcpsTerminalDtls.class, serv
					.getSessionFactory());
			lStrTerminalId = StringUtility.getParameter("terminalId", request).trim();
			lLongTerminalId = Long.valueOf(lStrTerminalId);

			TrnDcpsTerminalDtls lObjDcpsTerminalDtls = (TrnDcpsTerminalDtls) lObjTerminalRequestDAO
					.read(lLongTerminalId);
			lObjTerminalRequestDAO.deleteMissingCreditsSavedForTerminalId(lLongTerminalId);
			lObjTerminalRequestDAO.delete(lObjDcpsTerminalDtls);

			lBlFlag = true;
			String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resObj;
	}

	public ResultObject insertEmployerContribution(Map<String, Object> inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Boolean lBFlag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		try {
			setSessionInfo(inputMap);
			PostEmpContriDAO lObjPostEmpContriDAO = new PostEmpContriDAOImpl(PostEmpContri.class, serv
					.getSessionFactory());
			TerminalRequestDAO lObjTerminalRequestDAO = new TerminalRequestDAOImpl(TrnDcpsContribution.class, serv
					.getSessionFactory());
			TerminalRequestDAO lObjTerminalRequestDAOForTerminal = new TerminalRequestDAOImpl(
					TrnDcpsTerminalDtls.class, serv.getSessionFactory());
			FinancialYearDAO lObjFinancialYearDAO = new FinancialYearDAOImpl(null, serv.getSessionFactory());

			Long lLngFinYear = new Long(lObjFinancialYearDAO.getFinYearIdByCurDate());
			String lStrMonthId = sdf.format(gDtCurDate);
			String lStrBillAmount = StringUtility.getParameter("billAmount", request);
			String lStrDcpsId = StringUtility.getParameter("dcpsId", request);
			String lStrTerminalId = StringUtility.getParameter("terminalId", request);
			Long lLngTerminalId = 0l;
			if (lStrTerminalId != null && !lStrTerminalId.equals("")) {
				lLngTerminalId = Long.parseLong(lStrTerminalId);
			}
			TrnDcpsTerminalDtls lObjTrnDcpsTerminalDtls = (TrnDcpsTerminalDtls) lObjTerminalRequestDAOForTerminal
					.read(lLngTerminalId);
			lObjTrnDcpsTerminalDtls.setMissingCreditEmployerContriFlag('Y');
			lObjTerminalRequestDAOForTerminal.update(lObjTrnDcpsTerminalDtls);

			Double lDblBillAmount = 0d;

			if (lStrBillAmount != null && !lStrBillAmount.equals("")) {
				lDblBillAmount = Double.parseDouble(lStrBillAmount);
			}
			String lStrBillNo = lObjPostEmpContriDAO.getBillNumber(lLngFinYear);

			Long lLngExpenditureTillDate = lObjPostEmpContriDAO.getExpenditure(lLngFinYear)
					+ lDblBillAmount.longValue();
			Long lLngSanctionedBudget = lObjPostEmpContriDAO.getSancBudget(lLngFinYear);
			Long lLngExcessExpenditure = 0l;

			if (lLngExpenditureTillDate > lLngSanctionedBudget) {
				lLngExcessExpenditure = lLngExpenditureTillDate - lLngSanctionedBudget;
			}

			Long lLngDcpsEmpId = lObjTerminalRequestDAO.getDcpsEmpIdForDcpsId(lStrDcpsId);
			TrnDcpsContribution lObjTrnDcpsContribution = null;
			String[] lArrStrColumns = new String[] { "dcpsEmpId", "finYearId", "employerContriFlag" };
			Object[] lArrObjColumns = new Object[] { lLngDcpsEmpId, lLngFinYear, 'N' };
			List lLstContri = lObjTerminalRequestDAO.getListByColumnAndValue(lArrStrColumns, lArrObjColumns);
			if (lLstContri != null && lLstContri.size() > 0) {
				for (Integer index = 0; index < lLstContri.size(); index++) {
					lObjTrnDcpsContribution = (TrnDcpsContribution) lLstContri.get(0);
					lObjTrnDcpsContribution.setEmployerContriFlag('Y');
					lObjTerminalRequestDAO.update(lObjTrnDcpsContribution);
				}
			}
			PostEmpContri lObjPostEmpContri = new PostEmpContri();
			Long lLngPostEmpContriId = IFMSCommonServiceImpl.getNextSeqNum("MST_DCPS_POST_EMPLOYER_CONTRI", inputMap);

			lObjPostEmpContri.setDcpsPostEmpContriIdPk(lLngPostEmpContriId);

			lObjPostEmpContri.setFinYear(lLngFinYear);
			lObjPostEmpContri.setContriMonth(lStrMonthId);
			lObjPostEmpContri.setBillNo(lStrBillNo);
			lObjPostEmpContri.setExpenditureTillDate(lLngExpenditureTillDate);
			lObjPostEmpContri.setExcessExpenditure(lLngExcessExpenditure);
			lObjPostEmpContri.setStatusFlag('A');
			lObjPostEmpContri.setBillAmount(lDblBillAmount.longValue());
			lObjPostEmpContri.setPostId(gLngPostId);
			lObjPostEmpContri.setUserId(gLngUserId);
			lObjPostEmpContri.setCreatedDate(gDtCurDate);
			lObjPostEmpContriDAO.create(lObjPostEmpContri);

			lBFlag = true;

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

	public static double Round(double Rval, int Rpl) {
		double p = Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return tmp / p;
	}
}
