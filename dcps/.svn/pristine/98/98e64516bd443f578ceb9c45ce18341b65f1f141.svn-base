/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 30, 2011		Meeta Thacker								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.SancBudgetDAO;
import com.tcs.sgv.dcps.dao.SancBudgetDAOImpl;
import com.tcs.sgv.dcps.valueobject.SanctionedBudget;

/**
 * Class Description -
 * 
 * 
 * @author Meeta Thacker
 * @version 0.1
 * @since JDK 5.0 Apr 30, 2011
 */
public class SancBudgetServiceImpl extends ServiceImpl implements
		SancBudgetService {

	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private HttpServletRequest request = null; /* REQUEST OBJECT */

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
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

	private StringBuilder getResponseXMLDoc(boolean flag, String finyearId) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("<finYear>");
		lStrBldXML.append(finyearId);
		lStrBldXML.append("</finYear>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject loadSanctionedBudget(Map<String, Object> inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		request = (HttpServletRequest) inputMap.get("requestObj");
		List lLstFinYear = null;
		List lLstOrgType = null;

		try {

			DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			SancBudgetDAO lObjcmnDCPSSancBudgetDAO = new SancBudgetDAOImpl(
					null, serv.getSessionFactory());

			lLstOrgType = objDcpsCommonDAO.getAllOrgType();
			lLstFinYear = objDcpsCommonDAO.getFinyears();
			inputMap.put("lLstDepartment", lLstOrgType);
			inputMap.put("lLstFinYear", lLstFinYear);

			Long finYear = null;
			Long orgType = null;
			String schemeCode = null;

			if (!StringUtility.getParameter("cmbFinyear", request)
					.equalsIgnoreCase("")
					&& StringUtility.getParameter("cmbFinyear", request) != null) {
				finYear = Long.parseLong(StringUtility.getParameter(
						"cmbFinyear", request));
			}
			if (!StringUtility.getParameter("cmbOrgType", request)
					.equalsIgnoreCase("")
					&& StringUtility.getParameter("cmbOrgType", request) != null) {
				orgType = Long.parseLong(StringUtility.getParameter(
						"cmbOrgType", request));
			}
			if (!StringUtility.getParameter("txtSchemeCode", request)
					.equalsIgnoreCase("")
					&& StringUtility.getParameter("txtSchemeCode", request) != null) {
				schemeCode = StringUtility.getParameter("txtSchemeCode",
						request);
			}

			if (finYear != null) {
				List lLstSavedBudgets = lObjcmnDCPSSancBudgetDAO
						.getAllSanctionedBudgets(finYear);
				inputMap.put("SancBudgetList", lLstSavedBudgets);
				inputMap.put("finYear", finYear);
				inputMap.put("orgType", orgType);
				inputMap.put("schemeCode", schemeCode);
			}
		} catch (Exception e) {
			gLogger.error("Error is;" + e, e);
			e.printStackTrace();
		}

		resObj.setResultValue(inputMap);
		resObj.setViewName("DCPSSanctionedBudget");
		return resObj;
	}

	public ResultObject saveSanctionedBudget(Map<String, Object> inputMap)
			throws Exception {

		LogFactory.getLog(getClass());
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		Boolean lBFlag = false;
		Long lLongSancBudgetIdPk = null;
		Long finYear = null;

		try {
			SancBudgetDAO dcpsSanctionedBudget = new SancBudgetDAOImpl(
					SancBudgetDAO.class, serv.getSessionFactory());
			SanctionedBudget lArrDcpsSancBudget = (SanctionedBudget) inputMap
					.get("DCPSSanctionedBudget");

			/* Get Total Budget */
			finYear = Long.parseLong(lArrDcpsSancBudget
					.getDcpsSancBudgetFinYear().toString());
			Long lLngCurrentTotal = dcpsSanctionedBudget
					.getTotalBudget(finYear);

			String lStrCurrType = lArrDcpsSancBudget.getDcpsSancBudgetType();

			Long lLngCurrBudget = lArrDcpsSancBudget.getDcpsSancBudgetAmount();

			if (lStrCurrType.trim().equals("Credit")) {
				lLngCurrentTotal += lLngCurrBudget;
			}

			if (lStrCurrType.trim().equals("Debit")) {
				lLngCurrentTotal -= lLngCurrBudget;
			}

			lArrDcpsSancBudget.setTotalBudget(lLngCurrentTotal);
			/* End: Get Total Budget */

			lLongSancBudgetIdPk = IFMSCommonServiceImpl.getNextSeqNum(
					"MST_DCPS_SANC_BUDGET", inputMap);
			lArrDcpsSancBudget.setDcpsSanctionedBudgetIdPk(lLongSancBudgetIdPk);
			dcpsSanctionedBudget.create(lArrDcpsSancBudget);

			dcpsSanctionedBudget.updateExpenditure(finYear);
			lBFlag = true;

		} catch (Exception e) {

			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		String lSBStatus = getResponseXMLDoc(lBFlag, finYear.toString())
				.toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	public ResultObject populateSchemeCode(Map<String, Object> inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		request = (HttpServletRequest) inputMap.get("requestObj");

		String lStrSchemeCode = null;
		Long lLongOrgId = null;

		try {
			String lStrOrgId = StringUtility
					.getParameter("cmbOrgType", request).trim();

			if (!lStrOrgId.equalsIgnoreCase("")) {
				lLongOrgId = Long.valueOf(lStrOrgId);
			}

			SancBudgetDAO lobjSancBudgetDAO = new SancBudgetDAOImpl(null, serv
					.getSessionFactory());
			lStrSchemeCode = lobjSancBudgetDAO
					.getSchemeCodeForOrgId(lLongOrgId);

		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			ex.printStackTrace();
			return resObj;
		}

		String lSBStatus = getResponseXMLDocForSchemeCode(lStrSchemeCode)
				.toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	private StringBuilder getResponseXMLDocForSchemeCode(String lStrSchemeCode) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<txtSchemeCode>");
		lStrBldXML.append(lStrSchemeCode);
		lStrBldXML.append("</txtSchemeCode>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
}
