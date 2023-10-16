/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 9, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.tcs.sgv.dcps.dao.ChangeEmpDeptDAO;
import com.tcs.sgv.dcps.dao.ChangeEmpDeptDAOImpl;
import com.tcs.sgv.dcps.dao.MatchContriEntryDAO;
import com.tcs.sgv.dcps.dao.MatchContriEntryDAOImpl;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Jun 9, 2011
 */
public class MatchContriEntryServiceImpl extends ServiceImpl implements
		MatchContriEntryService {

	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrLocationCode = null;

	private Long gLngPostId = null;

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private String gStrUserId = null; /* STRING USER ID */

	/* Global Variable for UserId */
	Long gLngUserId = null;

	private Long gLngDBId = null; /* DB ID */

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			session = request.getSession();
			gStrPostId = SessionHelper.getPostId(inputMap).toString();
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}
	}

	public ResultObject loadMatchContriEntryForm(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListTreasuriesMatchEntries = null;
		Long yearId = null;

		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);

			MatchContriEntryDAO lObjMatchEntryDAO = new MatchContriEntryDAOImpl(
					null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

			/* Get Years */
			List lLstYears = lObjDcpsCommonDAO.getFinyears();

			if (!StringUtility.getParameter("yearId", request)
					.equalsIgnoreCase("")
					&& StringUtility.getParameter("yearId", request) != null) {

				yearId = Long.valueOf(StringUtility.getParameter("yearId",
						request));
				lStrTempFromDate = StringUtility.getParameter("fromDate",
						request);
				lStrTempToDate = StringUtility.getParameter("toDate", request);

				lStrFromDate = sdf2.format(sdf1.parse(lStrTempFromDate));
				lStrToDate = sdf2.format(sdf1.parse(lStrTempToDate));

				lDateFromDate = sdf1.parse(lStrTempFromDate);
				lDateToDate = sdf1.parse(lStrTempToDate);

				lListTreasuriesMatchEntries = lObjMatchEntryDAO
						.getAllTreasuriesForMatchedEntries(lStrFromDate,
								lStrToDate);

				inputMap
						.put("totalRecords", lListTreasuriesMatchEntries.size());
				inputMap.put("selectedYear", yearId);
				inputMap.put("fromDate", lDateFromDate);
				inputMap.put("toDate", lDateToDate);

			}

			inputMap.put("YEARS", lLstYears);
			inputMap.put("ListTreasuriesMatchEntries",
					lListTreasuriesMatchEntries);

			resObj.setResultValue(inputMap);
			resObj.setViewName("DCPSMatchedEntries");

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject loadUnMatchContriEntryForm(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListTreasuriesUnMatchEntries = null;
		Long yearId = null;

		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);

			MatchContriEntryDAO lObjMatchEntryDAO = new MatchContriEntryDAOImpl(
					null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

			/* Get Years */
			List lLstYears = lObjDcpsCommonDAO.getFinyears();

			if (!StringUtility.getParameter("yearId", request)
					.equalsIgnoreCase("")
					&& StringUtility.getParameter("yearId", request) != null) {

				yearId = Long.valueOf(StringUtility.getParameter("yearId",
						request));
				lStrTempFromDate = StringUtility.getParameter("fromDate",
						request);
				lStrTempToDate = StringUtility.getParameter("toDate", request);

				lStrFromDate = sdf2.format(sdf1.parse(lStrTempFromDate));
				lStrToDate = sdf2.format(sdf1.parse(lStrTempToDate));

				lDateFromDate = sdf1.parse(lStrTempFromDate);
				lDateToDate = sdf1.parse(lStrTempToDate);

				lListTreasuriesUnMatchEntries = lObjMatchEntryDAO
						.getAllTreasuriesForUnMatchedEntries(lStrFromDate,
								lStrToDate);

				inputMap.put("totalRecords", lListTreasuriesUnMatchEntries
						.size());
				inputMap.put("selectedYear", yearId);
				inputMap.put("fromDate", lDateFromDate);
				inputMap.put("toDate", lDateToDate);

			}

			inputMap.put("YEARS", lLstYears);
			inputMap.put("ListTreasuriesUnMatchEntries",
					lListTreasuriesUnMatchEntries);

			resObj.setResultValue(inputMap);
			resObj.setViewName("DCPSUnMatchedEntries");

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	public ResultObject loadManualMatch(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListUnMatchedVouchers = null;
		Long yearId = null;
		String lStrTreasuryCode = null;
		Long lLongTreasuryCode = null;

		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);

			MatchContriEntryDAO lObjMatchEntryDAO = new MatchContriEntryDAOImpl(
					null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

			/* Get Years */
			List lLstYears = lObjDcpsCommonDAO.getFinyears();
			
			List lLstTreasury = lObjDcpsCommonDAO.getAllTreasuries();
			
			Boolean lBlFirstTimeLoadFlag = true;

			if (StringUtility.getParameter("requestForMatching", request)
					.equalsIgnoreCase("Yes")) {

				yearId = Long.valueOf(StringUtility.getParameter("yearId",
						request));
				lStrTreasuryCode = StringUtility.getParameter("treasuryCode", request) ;
				lStrTempFromDate = StringUtility.getParameter("fromDate",
						request);
				lStrTempToDate = StringUtility.getParameter("toDate", request);

				lStrFromDate = sdf2.format(sdf1.parse(lStrTempFromDate));
				lStrToDate = sdf2.format(sdf1.parse(lStrTempToDate));

				lDateFromDate = sdf1.parse(lStrTempFromDate);
				lDateToDate = sdf1.parse(lStrTempToDate);

				if(!"".equals(lStrTreasuryCode) && !"".equals(lStrFromDate) && !"".equals(lStrToDate))
				{
					lLongTreasuryCode = Long.valueOf(lStrTreasuryCode);
					//lListUnMatchedVouchers = lObjMatchEntryDAO.getUnMatchedVouchersForMatching(lStrFromDate,lStrToDate,lLongTreasuryCode);
					lListUnMatchedVouchers = lObjMatchEntryDAO.getUnMatchedVouchersAllForMatching(lStrFromDate,lStrToDate,lLongTreasuryCode);
					
				}

				inputMap.put("selectedTreasury", lStrTreasuryCode);
				inputMap.put("selectedYear", yearId);
				inputMap.put("fromDate", lDateFromDate);
				inputMap.put("toDate", lDateToDate);
				
				lBlFirstTimeLoadFlag = false;

			}
			
			if(lListUnMatchedVouchers == null || lListUnMatchedVouchers.size()==0)
			{
				inputMap.put("totalRecords", 0);
			}
			else
			{
				inputMap.put("totalRecords", lListUnMatchedVouchers.size());
			}
			
			inputMap.put("lListUnMatchedVouchers",lListUnMatchedVouchers);
			inputMap.put("YEARS", lLstYears);
			inputMap.put("TreasuryList", lLstTreasury);
			inputMap.put("lBlFirstTimeLoadFlag", lBlFirstTimeLoadFlag);
			

			resObj.setResultValue(inputMap);
			resObj.setViewName("MatchContriManually");

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject matchVouchersWithTreasuryNet(Map<String, Object> inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Boolean lBFlag = false;
		
			try {
				
				setSessionInfo(inputMap);
				
				MatchContriEntryDAO lObjMatchEntryDAO = new MatchContriEntryDAOImpl(
						null, serv.getSessionFactory());
				
				String lStrVoucherIdPks = StringUtility.getParameter("contriVoucherIdPks",
						request);
				
				if(!"".equals(lStrVoucherIdPks))
				{
					String[] lStrArrVoucherIdPks = lStrVoucherIdPks.split("~");
					Long[] lLongArrVoucherIdPks = new Long[lStrArrVoucherIdPks.length];
					for (Integer lInt = 0; lInt < lLongArrVoucherIdPks.length; lInt++) {
						lLongArrVoucherIdPks[lInt] = Long.valueOf(lStrArrVoucherIdPks[lInt]);
						lObjMatchEntryDAO.updateVouchersManuallyMatched(lLongArrVoucherIdPks[lInt]);
					}
				}
			
				lBFlag = true;
			
			} catch (Exception e) {
			
				e.printStackTrace();
				resObj.setResultValue(null);
				resObj.setThrowable(e);
				resObj.setResultCode(ErrorConstants.ERROR);
				resObj.setViewName("errorPage");
			}
			
				String lSBStatus = getResponseXMLDoc(lBFlag).toString();
				String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
						.toString();
				
				inputMap.put("ajaxKey", lStrResult);
				resObj.setResultValue(inputMap);
				resObj.setViewName("ajaxData");
				return resObj;
		
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
}
