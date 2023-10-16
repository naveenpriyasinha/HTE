/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 8, 2011		Bhargav Trivedi								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.helper.WorkFlowHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.SixPCArrearsYearlyDAO;
import com.tcs.sgv.dcps.dao.SixPCArrearsYearlyDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsSixPCYearly;
import com.tcs.sgv.wf.delegate.WorkFlowDelegate;

/**
 * Class Description -
 * 
 * 
 * @author Bhargav Trivedi
 * @version 0.1
 * @since JDK 5.0 Apr 8, 2011
 */
public class SixPCArrearsYearlyServiceImpl extends ServiceImpl implements
		SixPCArrearsYearlyService {

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
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

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

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.dcps.service.sixPCArrearsYearlyService#
	 * approveSixthPCArrearsYearlyByDDO(java.util.Map)
	 */
	public ResultObject approveSixthPCArrearsYearlyByDDO(Map objectArgs)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrsixthPCId = null;
		String toPost = null;
		Long lLngHeirRefId = null;
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		String subjectName = null;
		Boolean lBlFlag = false;
		String lStrDdoCode = null;
		Long lLongPKValue = null;
		RltDcpsSixPCYearly lObjRltDcpsSixPCYearly = null;
		
		try {

			setSessionInfo(objectArgs);
			toPost = StringUtility.getParameter("ForwardToPost", request)
					.toString();
			subjectName = gObjRsrcBndle.getString("DCPS.SixthPCArrears");
			lLngHeirRefId = WorkFlowHelper.getHierarchyByPostIDAndDescription(
					gLngPostId.toString(), subjectName, objectArgs);
			String strPKValue = StringUtility.getParameter("SixthPC_Id",
					request).toString().trim();
			String[] strArrPKValue = strPKValue.split("~");

			objectArgs.put("Hierarchy_ref_id", lLngHeirRefId);
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle
					.getString("DCPS.SixthPCArrearsID")));
			objectArgs.put("DisplayJobTitle", subjectName);
			objectArgs.put("HeirRefId", lLngHeirRefId);
			objectArgs.put("toPostId", toPost);
			objectArgs.put("SendNotification", "This is not valid");
			objectArgs.put("jobTitle", subjectName);
			objectArgs.put("toPost", toPost);
			objectArgs.put("Hierarchy_ref_id", lLngHeirRefId);
			objectArgs.put("toLevel", gObjRsrcBndle.getString("DCPS.DDO"));

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					RltDcpsSixPCYearly.class, serv.getSessionFactory());

			Long lLngScheduelId = lObjSixPCArrearsYearlyDAO.getNextScheduleId();
			for (int index = 0; index < strArrPKValue.length; index++) {
				lStrsixthPCId = strArrPKValue[index];
				objectArgs.put("Pkvalue", lStrsixthPCId);

				WorkFlowDelegate.forward(objectArgs);
				lLongPKValue = Long.parseLong(strArrPKValue[index]);
				lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjSixPCArrearsYearlyDAO.read(lLongPKValue);
				lObjRltDcpsSixPCYearly.setRemarks(null);
				lObjRltDcpsSixPCYearly.setStatusFlag('F');
				lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
				lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
				lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
				lObjRltDcpsSixPCYearly.setScheduleId(lLngScheduelId);
				
				lStrDdoCode = lObjSixPCArrearsYearlyDAO.getDdoCodeForDDO(gLngPostId);
				lObjRltDcpsSixPCYearly.setDdoCode(lStrDdoCode);
				
				/*
				lStrDdoCode = lObjRltDcpsSixPCYearly.getDdoCode();
				if (lStrDdoCode == null || lStrDdoCode.length() == 0) {
					lStrDdoCode = lObjSixPCArrearsYearlyDAO.getDdoCodeForDDO(gLngPostId);
					lObjRltDcpsSixPCYearly.setDdoCode(lStrDdoCode);
				}
				*/

				/* Creating the Schedule */

				/* End : Creating the schedule */

			}

			lBlFlag = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.dcps.service.sixPCArrearsYearlyService#
	 * approveSixthPCArrearsYearlyByTO(java.util.Map)
	 */
	public ResultObject approveSixthPCArrearsYearlyByTO(Map objectArgs)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String sixthPCId = null;
		Long lLngDcpsSixPcId = null;
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		String subjectName = null;
		Long lLngAmountPaid = null;
		Boolean lBlFlag = false;
		Long lLongPKValue = null;
		List lListSixPCDtlsOnApproval = null;
		RltDcpsSixPCYearly lObjRltDcpsSixPCYearly = null;

		try {

			setSessionInfo(objectArgs);
			subjectName = gObjRsrcBndle.getString("DCPS.SixthPCArrears");
			String strPKValue = StringUtility.getParameter("SixthPC_Id",
					request).toString().trim();
			String[] strArrPKValue = strPKValue.split("~");
			String lStrAmounts = StringUtility.getParameter("amounts", request);
			String[] lArrStrAmounts = lStrAmounts.split("~");
			String lStrDcpsSixPcId = StringUtility.getParameter("dcpsSixPcId",
					request);
			String[] lStrArrDcpsSixPcId = lStrDcpsSixPcId.split("~");

			objectArgs.put("FromPostId", gStrPostId);
			objectArgs.put("SendNotification", "This is not valid");
			objectArgs.put("jobTitle", subjectName);
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle
					.getString("DCPS.SixthPCArrearsID")));

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					RltDcpsSixPCYearly.class, serv.getSessionFactory());

			String lStrApproveWholeSchedule = StringUtility.getParameter(
					"approveWholeSchedule", request).trim();
			Iterator ItForSixPCApproval = null;
			Object[] lArrObjSixPCDtlsOnApproval = null;

			if (lStrApproveWholeSchedule.equals("yes")) {
				for (int index = 0; index < strArrPKValue.length; index++) {

					lListSixPCDtlsOnApproval = lObjSixPCArrearsYearlyDAO
							.getSixPCDtlsOnApprovalForScheduleId(Long
									.valueOf(strArrPKValue[index]));
					ItForSixPCApproval = lListSixPCDtlsOnApproval.iterator();
					while (ItForSixPCApproval.hasNext()) {
						lArrObjSixPCDtlsOnApproval = (Object[]) ItForSixPCApproval
								.next();
						sixthPCId = lArrObjSixPCDtlsOnApproval[0].toString();
						lLngAmountPaid = Long
								.valueOf(lArrObjSixPCDtlsOnApproval[1]
										.toString());
						lLngDcpsSixPcId = Long
								.valueOf(lArrObjSixPCDtlsOnApproval[2]
										.toString());

						objectArgs.put("Pkvalue", sixthPCId);
						lLongPKValue = Long.parseLong(sixthPCId);
						lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjSixPCArrearsYearlyDAO
								.read(lLongPKValue);
						lObjRltDcpsSixPCYearly.setStatusFlag('A');
						lObjRltDcpsSixPCYearly.setRemarks(null);
						lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
						lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
						lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
						lObjSixPCArrearsYearlyDAO.updateForTO(lLngDcpsSixPcId,
								lLngAmountPaid, gLngPostId, gLngUserId,
								gDtCurDate);

					}
				}
			} else {
				for (int index = 0; index < strArrPKValue.length; index++) {

					sixthPCId = strArrPKValue[index];
					objectArgs.put("Pkvalue", sixthPCId);
					lLongPKValue = Long.parseLong(sixthPCId);
					lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjSixPCArrearsYearlyDAO
							.read(lLongPKValue);
					lObjRltDcpsSixPCYearly.setStatusFlag('A');
					lObjRltDcpsSixPCYearly.setRemarks(null);
					lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
					lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
					lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
					lLngDcpsSixPcId = Long.valueOf(lStrArrDcpsSixPcId[index]
							.trim());
					lLngAmountPaid = Long.valueOf(lArrStrAmounts[index].trim());
					lObjSixPCArrearsYearlyDAO.updateForTO(lLngDcpsSixPcId,
							lLngAmountPaid, gLngPostId, gLngUserId, gDtCurDate);
				}
			}

			lBlFlag = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.sixPCArrearsYearlyService#forwardDCPSArrearsYearly
	 * (java.util.Map)
	 */
	public ResultObject forwardDCPSArrearsYearly(Map objectArgs)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrSixthPCId = null;
		String amounts = null;
		String toPost = null;
		Long lLngHeirRefId = null;
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		String subjectName = null;
		Boolean lBlFlag = false;
		try {

			setSessionInfo(objectArgs);
			toPost = StringUtility.getParameter("ForwardToPost", request)
					.toString();
			subjectName = gObjRsrcBndle.getString("DCPS.SixthPCArrears");
			lLngHeirRefId = WorkFlowHelper.getHierarchyByPostIDAndDescription(
					gLngPostId.toString(), subjectName, objectArgs);
			String strPKValue = StringUtility.getParameter(
					"dcpsSixPCYearlyIds", request).toString().trim();
			String[] strArrPKValue = strPKValue.split("~");
			String lStrAmounts = StringUtility.getParameter("amounts", request);
			String[] lArrStrAmounts = lStrAmounts.split("~");

			objectArgs.put("Hierarchy_ref_id", lLngHeirRefId);
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle
					.getString("DCPS.SixthPCArrearsID")));
			objectArgs.put("DisplayJobTitle", subjectName);
			objectArgs.put("HeirRefId", lLngHeirRefId);
			objectArgs.put("toPostId", toPost);
			objectArgs.put("SendNotification", "This is not valid");
			objectArgs.put("jobTitle", subjectName);
			objectArgs.put("toPost", toPost);
			objectArgs.put("Hierarchy_ref_id", lLngHeirRefId);
			objectArgs.put("toLevel", gObjRsrcBndle.getString("DCPS.DDO"));

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					RltDcpsSixPCYearly.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			
			Long lLngArrAmount[] = new Long[lArrStrAmounts.length + 1];
			for (int index = 0; index < lArrStrAmounts.length; index++) {
				amounts = lArrStrAmounts[index];
				lLngArrAmount[index] = Long.parseLong(amounts);

			}
			for (int index = 0; index < strArrPKValue.length; index++) {
				lStrSixthPCId = strArrPKValue[index];
				objectArgs.put("Pkvalue", lStrSixthPCId);

				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				RltDcpsSixPCYearly lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjSixPCArrearsYearlyDAO
						.read(lLongPKValue);

				if (!(lObjRltDcpsSixPCYearly.getStatusFlag() == 'R')) {
					WorkFlowDelegate.create(objectArgs);
				}

				WorkFlowDelegate.forward(objectArgs);

				lObjRltDcpsSixPCYearly.setDdoCode(lStrDdoCode);
				lObjRltDcpsSixPCYearly.setStatusFlag('F');
				lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
				lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
				lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
				// Long lLngDcpsEmpId =
				// lObjSixPCArrearsYearlyDAO.getDcpsEmpIdFromSirxthPCYearlyId(lLongPKValue);
				// lObjSixPCArrearsYearlyDAO.update(lLngDcpsEmpId, gLngPostId,
				// gLngUserId, gDtCurDate);

				// lObjRltDcpsSixPCYearly.set

			}

			lBlFlag = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.dcps.service.sixPCArrearsYearlyService#
	 * loadSixthPCArrearsYearlyDDO(java.util.Map)
	 */
	public ResultObject loadSixthPCArrearsYearlyDDO(Map inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lListEmp = null;
		List lLstUpperUsers = null;
		String lStrStatusFlag = null;
		String lStrUserType = null;
		String lStrDdoCode = null;
		String lStrDesignation = null;
		Long lLngYearId = null;
		Integer totalRecords = null;
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;

		try {

			setSessionInfo(inputMap);
			Map displayTag = IFMSCommonServiceImpl.getDisplayPara(request);

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			lStrDdoCode = lObjSixPCArrearsYearlyDAO
					.getDdoCodeForDDO(gLngPostId);
			int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
			List lListYears = lObjSixPCArrearsYearlyDAO
					.getYearsForSixPCYearly(CurrentYear);
			lLstUpperUsers = getHierarchyUsers(inputMap);

			List lLstDesignation = null;
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			List lLstParentDept = lObjDcpsCommonDAO
					.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);
			lLstDesignation = lObjDcpsCommonDAO.getAllDesignation(
					(Long) objParentDept[0], gLngLangId);
			inputMap.put("lLstDesignation", lLstDesignation);

			if (StringUtility.getParameter("cmbDesignation", request).length() > 0) {

				lStrDesignation = StringUtility.getParameter("cmbDesignation",
						request);
				inputMap.put("DesignationId", lStrDesignation);

				if (StringUtility.getParameter("yearId", request) != null
						&& !(StringUtility.getParameter("yearId", request)
								.equalsIgnoreCase(""))) {
					lLngYearId = Long.valueOf(StringUtility.getParameter(
							"yearId", request));
				}
				lStrUserType = StringUtility.getParameter("UserType", request);
				lStrStatusFlag = StringUtility.getParameter("StatusFlag",
						request);
				totalRecords = lObjSixPCArrearsYearlyDAO
						.getEmpListForSixPCArrearsYearlyDDOCount(lStrDdoCode,
								lLngYearId, gStrPostId, lStrDesignation);
				lListEmp = lObjSixPCArrearsYearlyDAO
						.getEmpListForSixPCArrearsYearlyDDO(lStrDdoCode,
								lLngYearId, gStrPostId, lStrDesignation,
								displayTag);
			} else {

				lStrUserType = StringUtility.getParameter("UserType", request);
				lStrStatusFlag = StringUtility.getParameter("StatusFlag",
						request);
				if (StringUtility.getParameter("yearId", request) != null
						&& !(StringUtility.getParameter("yearId", request)
								.equalsIgnoreCase(""))) {
					lLngYearId = Long.valueOf(StringUtility.getParameter(
							"yearId", request));
					totalRecords = lObjSixPCArrearsYearlyDAO
							.getEmpListForSixPCArrearsYearlyDDOCount(
									lStrDdoCode, lLngYearId, gStrPostId,
									lStrDesignation);
					lListEmp = lObjSixPCArrearsYearlyDAO
							.getEmpListForSixPCArrearsYearlyDDO(lStrDdoCode,
									lLngYearId, gStrPostId, lStrDesignation,
									displayTag);
				}

			}

			inputMap.put("totalRecords", totalRecords);
			inputMap.put("selectedYearId", lLngYearId);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("UserType", lStrUserType);
			inputMap.put("StatusFlag", lStrStatusFlag);
			inputMap.put("UserList", lLstUpperUsers);
			inputMap.put("lListYears", lListYears);
			inputMap.put("lListEmp", lListEmp);
			resObj.setResultValue(inputMap);
			resObj.setViewName("DcpsSixPCArrearsYearly");

		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error("Error is:" + e, e);
		}

		return resObj;

	}

	private List getHierarchyUsers(Map inputMap) {

		int llFromLevelId = 0;
		List UserList = new ArrayList<String>();
		try {
			setSessionInfo(inputMap);
			// Get the Subject Name
			String subjectName = gObjRsrcBndle.getString("DCPS.SixthPCArrears");

			// Get the Hierarchy Id
			Long lLngHierRefId = WorkFlowHelper
					.getHierarchyByPostIDAndDescription(gStrPostId,
							subjectName, inputMap);

			// Get the From level Id
			llFromLevelId = WorkFlowHelper.getLevelFromPostMpg(gStrPostId,
					lLngHierRefId, inputMap);

			// Get the List of Post ID of the users at the next Level
			List rsltList = WorkFlowHelper.getUpperPost(gStrPostId,
					lLngHierRefId, llFromLevelId, inputMap);

			Object[] lObjNextPost = null;

			for (int i = 0; i < rsltList.size(); i++) {

				lObjNextPost = (Object[]) rsltList.get(i);

				if (!(lObjNextPost.equals(null))) {
					UserList.add(lObjNextPost[0].toString());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return UserList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.sixPCArrearsYearlyService#loadSixthPCArrearsYearlyTO
	 * (java.util.Map)
	 */
	public ResultObject loadSixthPCArrearsYearlyTO(Map inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lListEmp = null;
		List lLstUpperUsers = null;
		List lListYears = null;
		String lStrStatusFlag = null;
		String lStrUserType = null;
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		String lStrDdoCode = null;
		String lStrDesignation = null;
		Long lLngYearId = null;
		Integer totalRecords = null;
		String lStrFinYearDesc = null;
		try {

			setSessionInfo(inputMap);
			Map displayTag = IFMSCommonServiceImpl.getDisplayPara(request);

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			lStrUserType = StringUtility.getParameter("UserType", request);
			List<ComboValuesVO> lLstAllDDOs = lObjSixPCArrearsYearlyDAO
					.getAllDDOListForArrearsForwarded(gStrPostId, lStrUserType);

			lStrStatusFlag = StringUtility.getParameter("StatusFlag", request);
			if (StringUtility.getParameter("scheduleId", request).length() > 0) {
				Long.valueOf(StringUtility.getParameter("scheduleId", request));
			}

			if (StringUtility.getParameter("year", request).length() > 0) {
				Long.valueOf(StringUtility.getParameter("year", request));
			}

			lStrDdoCode = StringUtility.getParameter("ddoCode", request);
			String lStrScheduleId = StringUtility.getParameter("scheduleId",
					request);
			// make query for particular schedule ID ,year Id and ddocode
			if (StringUtility.getParameter("requestforgivenDDO", request)
					.equalsIgnoreCase("yes")) {
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(
						MstEmp.class, serv.getSessionFactory());
				String lStrDdoName = lObjDcpsCommonDAO
						.getDdoNameForCode(lStrDdoCode);
				inputMap.put("DDONAME", lStrDdoName);
				lStrDdoCode = StringUtility.getParameter("ddoCode", request);
				lLngYearId = Long.valueOf(StringUtility.getParameter("yearId",
						request));
				lStrFinYearDesc = lObjDcpsCommonDAO
						.getFinYearForYearId(lLngYearId);
				List lLstDesignation = null;

				List lLstParentDept = lObjDcpsCommonDAO
						.getParentDeptForDDO(lStrDdoCode);
				Object[] objParentDept = (Object[]) lLstParentDept.get(0);
				lLstDesignation = lObjDcpsCommonDAO.getAllDesignation(
						(Long) objParentDept[0], gLngLangId);
				inputMap.put("lLstDesignation", lLstDesignation);
				inputMap.put("selectedDDO", lStrDdoCode);

				if (StringUtility.getParameter("cmbDesignation", request)
						.length() > 0) {

					lStrDesignation = StringUtility.getParameter(
							"cmbDesignation", request);
					inputMap.put("DesignationId", lStrDesignation);

					if (StringUtility.getParameter("yearId", request) != null
							&& !(StringUtility.getParameter("yearId", request)
									.equalsIgnoreCase(""))) {
						lLngYearId = Long.valueOf(StringUtility.getParameter(
								"yearId", request));
					}

					totalRecords = lObjSixPCArrearsYearlyDAO
							.getEmpListForSixPCArrearsYearlyTOCount(
									lStrDdoCode, lLngYearId, gStrPostId,
									lStrDesignation, lStrScheduleId);

					lListEmp = lObjSixPCArrearsYearlyDAO
							.getEmpListForSixPCArrearsYearlyTO(lStrDdoCode,
									lLngYearId, gStrPostId, lStrDesignation,
									displayTag, lStrScheduleId);
				}

				else {

					if (StringUtility.getParameter("yearId", request) != null
							&& !(StringUtility.getParameter("yearId", request)
									.equalsIgnoreCase(""))) {
						lLngYearId = Long.valueOf(StringUtility.getParameter(
								"yearId", request));

						if (lStrScheduleId.equals("")) {

							lListEmp = lObjSixPCArrearsYearlyDAO
									.getEmpScheduleList(lStrDdoCode,
											lLngYearId, gStrPostId,
											lStrDesignation);
							inputMap.put("Schedule", "no");
						} else {
							totalRecords = lObjSixPCArrearsYearlyDAO
									.getEmpListForSixPCArrearsYearlyTOCount(
											lStrDdoCode, lLngYearId,
											gStrPostId, lStrDesignation,
											lStrScheduleId);

							lListEmp = lObjSixPCArrearsYearlyDAO
									.getEmpListForSixPCArrearsYearlyTO(
											lStrDdoCode, lLngYearId,
											gStrPostId, lStrDesignation,
											displayTag, lStrScheduleId);
							inputMap.put("Schedule", "yes");

						}

					}
				}

			}

			new DcpsCommonDAOImpl(MstEmp.class, serv.getSessionFactory());

			int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
			lListYears = lObjSixPCArrearsYearlyDAO.getYearsForSixPCYearly(CurrentYear);
			// lLstUpperUsers = getHierarchyUsers(inputMap);

			inputMap.put("totalRecords", totalRecords);
			inputMap.put("selectedYearId", lLngYearId);
			inputMap.put("lLstAllDDOs", lLstAllDDOs);
			inputMap.put("DDOCODE", lStrDdoCode);

			inputMap.put("UserType", lStrUserType);
			inputMap.put("StatusFlag", lStrStatusFlag);
			inputMap.put("UserList", lLstUpperUsers);
			inputMap.put("lListYears", lListYears);
			inputMap.put("lListEmp", lListEmp);
			inputMap.put("FinYearDesc", lStrFinYearDesc);

			resObj.setResultValue(inputMap);
			resObj.setViewName("DcpsSixPCArrearsYearly");
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error("Error is:" + e, e);
		}

		return resObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.dcps.service.sixPCArrearsYearlyService#
	 * loadsixthPCYearlyInstallment(java.util.Map)
	 */
	public ResultObject loadsixthPCYearlyInstallment(Map inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		String lStrStatusFlag = null;
		String lStrUserType = null;
		List lListEmp = null;
		List lLstUpperUsers = null;
		String lStrDesignation = null;
		Long lLngYearId = null;
		Integer totalRecords = null;
		try {
			setSessionInfo(inputMap);
			Map displayTag = IFMSCommonServiceImpl.getDisplayPara(request);

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			String lStrDdoCode = lObjSixPCArrearsYearlyDAO
					.getDdoCode(gLngPostId);
			
			int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
			gLogger.info("-----CurrentYear"+CurrentYear);
			
			List lListYears = lObjSixPCArrearsYearlyDAO
					.getYearsForSixPCYearly(CurrentYear);
			lLstUpperUsers = getHierarchyUsers(inputMap);

			List lLstDesignation = null;
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			List lLstParentDept = lObjDcpsCommonDAO
					.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);
			lLstDesignation = lObjDcpsCommonDAO.getAllDesignation(
					(Long) objParentDept[0], gLngLangId);
			inputMap.put("lLstDesignation", lLstDesignation);

			if (StringUtility.getParameter("cmbDesignation", request).length() > 0) {

				if (StringUtility.getParameter("cmbDesignation", request)
						.length() > 0) {
					lStrDesignation = StringUtility.getParameter(
							"cmbDesignation", request);
					inputMap.put("DesignationId", lStrDesignation);
				}

				if (StringUtility.getParameter("yearId", request) != null
						&& !(StringUtility.getParameter("yearId", request)
								.equalsIgnoreCase(""))) {
					lLngYearId = Long.valueOf(StringUtility.getParameter(
							"yearId", request));
				}

				lStrUserType = StringUtility.getParameter("UserType", request);
				lStrStatusFlag = StringUtility.getParameter("StatusFlag",
						request);
				totalRecords = lObjSixPCArrearsYearlyDAO
						.getEmpListForSixPCArrearsYearlyCount(lStrDdoCode,
								lLngYearId, lStrDesignation);
				lListEmp = lObjSixPCArrearsYearlyDAO
						.getEmpListForSixPCArrearsYearly(lStrDdoCode,
								lLngYearId, lStrDesignation, displayTag);
			} else {
				lStrUserType = StringUtility.getParameter("UserType", request);
				lStrStatusFlag = StringUtility.getParameter("StatusFlag",
						request);
				if (StringUtility.getParameter("yearId", request) != null
						&& !(StringUtility.getParameter("yearId", request)
								.equalsIgnoreCase(""))) {
					lLngYearId = Long.valueOf(StringUtility.getParameter(
							"yearId", request));
					totalRecords = lObjSixPCArrearsYearlyDAO
							.getEmpListForSixPCArrearsYearlyCount(lStrDdoCode,
									lLngYearId, lStrDesignation);
					lListEmp = lObjSixPCArrearsYearlyDAO
							.getEmpListForSixPCArrearsYearly(lStrDdoCode,
									lLngYearId, lStrDesignation, displayTag);
				}

			}
			inputMap.put("totalRecords", totalRecords);
			inputMap.put("selectedYearId", lLngYearId);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("UserType", lStrUserType);
			inputMap.put("StatusFlag", lStrStatusFlag);
			inputMap.put("UserList", lLstUpperUsers);
			inputMap.put("lListYears", lListYears);
			inputMap.put("lListEmp", lListEmp);
			resObj.setResultValue(inputMap);
			resObj.setViewName("DcpsSixPCArrearsYearly");

		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error("Error is:" + e, e);
		}

		return resObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.dcps.service.sixPCArrearsYearlyService#
	 * loadsixthPCYearlyInstallmentDDO(java.util.Map)
	 */

	public ResultObject rejectSixthPCArrearsYearlyByDDO(Map objectArgs)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String sixthPCId = null;
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		String subjectName = null;
		String lStrRemarks = null;
		Boolean lBlFlag = false;

		try {

			setSessionInfo(objectArgs);
			subjectName = gObjRsrcBndle.getString("DCPS.SixthPCArrears");
			lStrRemarks = StringUtility.getParameter("remarks", request)
					.toString().trim();
			String strPKValue = StringUtility.getParameter("SixthPC_Id",
					request).toString().trim();
			String[] strArrPKValue = strPKValue.split("~");

			objectArgs.put("FromPostId", gStrPostId);
			objectArgs.put("SendNotification", "This is not valid");
			objectArgs.put("jobTitle", subjectName);
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle
					.getString("DCPS.SixthPCArrearsID")));

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					RltDcpsSixPCYearly.class, serv.getSessionFactory());
			for (int index = 0; index < strArrPKValue.length; index++) {
				sixthPCId = strArrPKValue[index];
				objectArgs.put("Pkvalue", sixthPCId);
				// WorkFlowDelegate.create(objectArgs);
				WorkFlowDelegate.returnDoc(objectArgs);
				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				RltDcpsSixPCYearly lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjSixPCArrearsYearlyDAO
						.read(lLongPKValue);
				lObjRltDcpsSixPCYearly.setStatusFlag('R');
				lObjRltDcpsSixPCYearly.setRemarks(lStrRemarks);
				lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
				lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
				lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
				// Long lLngDcpsEmpId =
				// lObjSixPCArrearsYearlyDAO.getDcpsEmpIdFromSirxthPCYearlyId(lLongPKValue);
				// lObjSixPCArrearsYearlyDAO.rejectForDDO(lLngDcpsEmpId,
				// gLngPostId, gLngUserId, gDtCurDate);

			}

			lBlFlag = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.dcps.service.sixPCArrearsYearlyService#
	 * rejectSixthPCArrearsYearlyByTO(java.util.Map)
	 */
	public ResultObject rejectSixthPCArrearsYearlyByTO(Map objectArgs)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrSixthPCId = null;
		String lStrScheduleId = null;
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		String subjectName = null;
		String lStrRemarks = null;
		Boolean lBlFlag = false;
		List<Long> lListSixPCYearlyIdPkForScheduleId = new ArrayList<Long>();
		RltDcpsSixPCYearly lObjRltDcpsSixPCYearly = null;
		Long lLongPKValue = null;

		try {

			setSessionInfo(objectArgs);
			subjectName = gObjRsrcBndle.getString("DCPS.SixthPCArrears");
			String strPKValue = StringUtility.getParameter("SixthPC_Id",
					request).toString().trim();
			lStrRemarks = StringUtility.getParameter("remarks", request)
					.toString().trim();
			String[] strArrPKValue = strPKValue.split("~");

			objectArgs.put("FromPostId", gStrPostId);
			objectArgs.put("SendNotification", "This is not valid");
			objectArgs.put("jobTitle", subjectName);
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle
					.getString("DCPS.SixthPCArrearsID")));

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					RltDcpsSixPCYearly.class, serv.getSessionFactory());

			String lStrRejectWholeSchedule = StringUtility.getParameter(
					"rejectWholeSchedule", request).trim();

			if (lStrRejectWholeSchedule.equals("yes")) {
				for (int index = 0; index < strArrPKValue.length; index++) {
					lStrScheduleId = strArrPKValue[index];
					lListSixPCYearlyIdPkForScheduleId = lObjSixPCArrearsYearlyDAO
							.getSixPCYearlyIdPksForScheduleId(Long
									.valueOf(lStrScheduleId));

					for (Integer lInt = 0; lInt < lListSixPCYearlyIdPkForScheduleId
							.size(); lInt++) {

						lStrSixthPCId = lListSixPCYearlyIdPkForScheduleId.get(
								lInt).toString();
						objectArgs.put("Pkvalue", lStrSixthPCId);
						WorkFlowDelegate.returnDoc(objectArgs);
						lLongPKValue = Long.parseLong(lStrSixthPCId);
						lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjSixPCArrearsYearlyDAO
								.read(lLongPKValue);
						lObjRltDcpsSixPCYearly.setStatusFlag('R');
						lObjRltDcpsSixPCYearly.setRemarks(lStrRemarks);
						lObjRltDcpsSixPCYearly.setScheduleId(null);
						lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
						lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
						lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
					}
				}
			}

			else {
				for (int index = 0; index < strArrPKValue.length; index++) {

					lStrSixthPCId = strArrPKValue[index];
					objectArgs.put("Pkvalue", lStrSixthPCId);
					WorkFlowDelegate.returnDoc(objectArgs);
					lLongPKValue = Long.parseLong(lStrSixthPCId);
					lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjSixPCArrearsYearlyDAO
							.read(lLongPKValue);
					lObjRltDcpsSixPCYearly.setStatusFlag('R');
					lObjRltDcpsSixPCYearly.setRemarks(lStrRemarks);
					lObjRltDcpsSixPCYearly.setScheduleId(null);
					lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
					lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
					lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
				}
			}

			lBlFlag = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.sixPCArrearsYearlyService#saveSixPCArrearsYearly
	 * (java.util.Map)
	 */
	public ResultObject saveSixPCArrearsYearly(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		boolean lBlFlag = false;
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		RltDcpsSixPCYearly lObjRltDcpsSixPCYearly = null;
		Long lLngRltSixPCYearlyId = null;
		String lStrDcpsEmpIds = null;

		try {
			setSessionInfo(inputMap);
			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(
					RltDcpsSixPCYearly.class, serv.getSessionFactory());
			if (StringUtility.getParameter("dcpsSixPCYearlyIds", request) != null
					&& StringUtility
							.getParameter("dcpsSixPCYearlyIds", request)
							.length() > 0) {
				String lStrdcpsSixPCYearlyIds = StringUtility.getParameter(
						"dcpsSixPCYearlyIds", request);
				String[] lStrArrdcpsSixPCYearlyIds = lStrdcpsSixPCYearlyIds
						.split("~");
				Long[] lLongArrdcpsSixPCYearlyIds = new Long[lStrArrdcpsSixPCYearlyIds.length];

				RltDcpsSixPCYearly[] lArrRltDcpsSixPCYearly = (RltDcpsSixPCYearly[]) inputMap
						.get("lArrRltDcpsSixPCYearly");

				for (Integer lInt = 0; lInt < lStrArrdcpsSixPCYearlyIds.length; lInt++) {
					lLongArrdcpsSixPCYearlyIds[lInt] = Long
							.valueOf(lStrArrdcpsSixPCYearlyIds[lInt]);
				}

				for (Integer lInt = 0; lInt < lStrArrdcpsSixPCYearlyIds.length; lInt++) {
					if (lLongArrdcpsSixPCYearlyIds[lInt] == 0l) {
						Long lLongTempdcpsSixPCYearlyId = IFMSCommonServiceImpl
								.getNextSeqNum("RLT_DCPS_SIXPC_YEARLY",
										inputMap);
						lArrRltDcpsSixPCYearly[lInt]
								.setDcpsSixPCYearlyId(lLongTempdcpsSixPCYearlyId);
						lObjSixPCArrearsYearlyDAO
								.create(lArrRltDcpsSixPCYearly[lInt]);
						lBlFlag = true;
					} else {
						lArrRltDcpsSixPCYearly[lInt]
								.setDcpsSixPCYearlyId(lLongArrdcpsSixPCYearlyIds[lInt]);
						lObjSixPCArrearsYearlyDAO
								.update(lArrRltDcpsSixPCYearly[lInt]);
						lBlFlag = true;
					}

				}

			} else {

				List<RltDcpsSixPCYearly> lListRltDcpsSixPCYearly = new ArrayList<RltDcpsSixPCYearly>();
				lListRltDcpsSixPCYearly = (List<RltDcpsSixPCYearly>) inputMap
						.get("lListRltDcpsSixPCYearly");
				lObjRltDcpsSixPCYearly = new RltDcpsSixPCYearly();

				if (StringUtility.getParameter("hidDcpsEmpId", request) != null
						&& StringUtility.getParameter("hidDcpsEmpId", request)
								.length() > 0) {
					lStrDcpsEmpIds = StringUtility.getParameter("hidDcpsEmpId",
							request);
					lStrDcpsEmpIds.split("~");
					if (lListRltDcpsSixPCYearly != null) {
						for (int lIntCnt = 0; lIntCnt < lListRltDcpsSixPCYearly
								.size(); lIntCnt++) {
							lObjRltDcpsSixPCYearly = new RltDcpsSixPCYearly();
							lObjRltDcpsSixPCYearly = lListRltDcpsSixPCYearly
									.get(lIntCnt);
							lLngRltSixPCYearlyId = IFMSCommonServiceImpl
									.getNextSeqNum("rlt_dcps_sixpc_yearly",
											inputMap);
							lObjRltDcpsSixPCYearly
									.setDcpsSixPCYearlyId(lLngRltSixPCYearlyId);
							lObjSixPCArrearsYearlyDAO
									.create(lObjRltDcpsSixPCYearly);
							gLogger
									.info("Record Inserted in table rlt_dcps_sixpc_yearly successfully......");
						}
					}
					lBlFlag = true;
				}

			}

		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			ex.printStackTrace();
			return resObj;
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.dcps.service.sixPCArrearsYearlyService#
	 * loadSixPCArrearAmountSchedule(java.util.Map)
	 */
	public ResultObject loadSixPCArrearAmountSchedule(Map inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		Long yearId = null;

		try {
			setSessionInfo(inputMap);
			List lLstEmp = null;

			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(null,
					serv.getSessionFactory());

			String lStrDdoCode = lObjSixPCArrearsYearlyDAO
					.getDdoCode(gLngPostId);

			String lStrUserType = StringUtility.getParameter("UserType",
					request);
			inputMap.put("UserType", lStrUserType);
			
			int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
			List lListYears = lObjSixPCArrearsYearlyDAO
					.getYearsForSixPCYearly(CurrentYear);
			inputMap.put("lListYears", lListYears);

			if (StringUtility.getParameter("yearId", request) != null
					&& !(StringUtility.getParameter("yearId", request)
							.equalsIgnoreCase(""))) {
				yearId = Long.valueOf(StringUtility.getParameter("yearId",
						request));
				lLstEmp = lObjSixPCArrearsYearlyDAO
						.getEmpListForSixPCArrearAmountSchedule(lStrDdoCode,
								yearId);

				inputMap.put("lLstEmp", lLstEmp);
			}
			inputMap.put("yearId", yearId);
			resObj.setResultValue(inputMap);
			resObj.setViewName("dcpsSixPCArrearAmountSchedule");
			gLogger.info("Load Sucessful");
		} catch (Exception e) {
			gLogger.error("Error is;" + e, e);
			e.printStackTrace();
		}

		return resObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.dcps.service.sixPCArrearsYearlyService#
	 * loadSixPCArrearAmountScheduleDDO(java.util.Map)
	 */
	public ResultObject loadSixPCArrearAmountScheduleDDO(Map inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		SixPCArrearsYearlyDAO lObjSixPCArrearsYearlyDAO = null;
		List lLstEmp = null;
		Long yearId = null;
		try {
			setSessionInfo(inputMap);
			lObjSixPCArrearsYearlyDAO = new SixPCArrearsYearlyDAOImpl(null,
					serv.getSessionFactory());

			String lStrDdoCode = lObjSixPCArrearsYearlyDAO
					.getDdoCodeForDDO(gLngPostId);
			String lStrUserType = StringUtility.getParameter("UserType",
					request);
			inputMap.put("UserType", lStrUserType);
			int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
			List lListYears = lObjSixPCArrearsYearlyDAO
					.getYearsForSixPCYearly(CurrentYear);
			inputMap.put("lListYears", lListYears);

			if (StringUtility.getParameter("yearId", request) != null
					&& !(StringUtility.getParameter("yearId", request)
							.equalsIgnoreCase(""))) {
				yearId = Long.valueOf(StringUtility.getParameter("yearId",
						request));
				lLstEmp = lObjSixPCArrearsYearlyDAO
						.getEmpListForSixPCArrearAmountSchedule(lStrDdoCode,
								yearId);

				inputMap.put("lLstEmp", lLstEmp);
			}
			inputMap.put("yearId", yearId);

			resObj.setResultValue(inputMap);
			resObj.setViewName("dcpsSixPCArrearAmountSchedule");
			gLogger.info("Load Sucessful");
		} catch (Exception e) {
			gLogger.error("Error is;" + e, e);
			e.printStackTrace();
		}
		return resObj;
	}

}
