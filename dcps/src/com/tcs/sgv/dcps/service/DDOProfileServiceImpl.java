/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Feb 24, 2011		Kapil Devani								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

//tcs.sgv.dcps.service.DDOProfileServiceImpl
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.ArrearsDAO;
import com.tcs.sgv.dcps.dao.ArrearsDAOImpl;
import com.tcs.sgv.dcps.dao.DdoProfileDAO;
import com.tcs.sgv.dcps.dao.DdoProfileDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.dao.SearchEmployeeDAO;
import com.tcs.sgv.dcps.dao.SearchEmployeeDAOImpl;
import com.tcs.sgv.dcps.valueobject.HstEmp;
import com.tcs.sgv.dcps.valueobject.MstDcpsSchemes;
import com.tcs.sgv.dcps.valueobject.MstDcpsTierICntrnbtn;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsDdoScheme;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;

public class DDOProfileServiceImpl extends ServiceImpl implements
		DDOProfileService {

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
			//e.printStackTrace();
		}

	}

	/**
	 * service method to used to populate the branch names Combo box according
	 * to the selected bank names
	 * 
	 * @param Map
	 *            <String,Object> inputMap
	 * @return ResultObject
	 */

	public ResultObject populateBranchNamesUsingAjax(
			Map<String, Object> lMapInputMap) {

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS,
				"FAIL");
		HttpServletRequest lObjRequest = (HttpServletRequest) lMapInputMap
				.get("requestObj");
		ServiceLocator lObjServLoctr = (ServiceLocator) lMapInputMap
				.get("serviceLocator");

		try {
			DdoProfileDAO ddoProfileDAO = new DdoProfileDAOImpl(null,
					lObjServLoctr.getSessionFactory());
			Long lLngBankName = Long.parseLong(StringUtility.getParameter(
					"cmbBankName", lObjRequest));

			List lLstBranchNames = ddoProfileDAO.getBranchNames(lLngBankName);

			String lStrTempResult = null;
			if (lLstBranchNames != null) {
				lStrTempResult = new AjaxXmlBuilder().addItems(lLstBranchNames,
						"desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		} catch (Exception e) {
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

	/**
	 * service method to used to save DDO Information into Databse
	 * 
	 * @param Map
	 *            <String,Object> inputMap
	 * @return ResultObject
	 */
	private StringBuilder getResponseXMLDoc(boolean flag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDocForSelDeseltn(boolean flag,
			String lStrDeselectionDate) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("<DeselectionDate>");
		lStrBldXML.append(lStrDeselectionDate);
		lStrBldXML.append("</DeselectionDate>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDoc(boolean flag, boolean otherFlag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("<OtherFlag>");
		lStrBldXML.append(otherFlag);
		lStrBldXML.append("</OtherFlag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject displaySchemeNameForCode(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");

		String lStrSchemeCode = null;
		List<MstDcpsSchemes> lListSchemes = null;
		Integer lNoOfSchemes = 0;
		setSessionInfo(inputMap);

		try {
			lStrSchemeCode = StringUtility.getParameter("txtSchemeCode",
					request);
			DdoProfileDAO DdoProfileDAOImplObj = new DdoProfileDAOImpl(
					RltDcpsDdoScheme.class, serv.getSessionFactory());

			lListSchemes = DdoProfileDAOImplObj
					.getSchemeNamesFromCode(lStrSchemeCode);
			lNoOfSchemes = lListSchemes.size();

			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			//e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		String lSBStatus = getResponseXMLDocToDisplaySchemeName(lNoOfSchemes,
				lListSchemes).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	private StringBuilder getResponseXMLDocToDisplaySchemeName(
			Integer lNoOfSchemes, List<MstDcpsSchemes> lListSchemes) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");

		lStrBldXML.append("<lNoOfSchemes>");
		lStrBldXML.append(lNoOfSchemes);
		lStrBldXML.append("</lNoOfSchemes>");
		for (int lInt = 0; lInt < lListSchemes.size(); lInt++) {
			lStrBldXML.append("<SchemeName" + lInt + ">");
			
			lStrBldXML.append("<![CDATA[");
			lStrBldXML.append(lListSchemes.get(lInt).getSchemeName());
			lStrBldXML.append("]]>");
			
			lStrBldXML.append("</SchemeName" + lInt + ">");
			lStrBldXML.append("<SchemeCode" + lInt + ">");
			lStrBldXML.append(lListSchemes.get(lInt).getSchemeCode());
			lStrBldXML.append("</SchemeCode" + lInt + ">");
		}

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject attachGroup(Map<String, Object> inputMap) {

		Log logger = LogFactory.getLog(getClass());
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");

		boolean lBlFlag = false;

		try {

			NewRegDdoDAO dcpsNewRegistrationDao = new NewRegDdoDAOImpl(
					MstEmp.class, serv.getSessionFactory());

			String lEmployeeIDListString = StringUtility.getParameter("lCheck",
					request);
			String[] lEmployeeIDList = lEmployeeIDListString.split(",");
			String lStrBillGroupId = StringUtility.getParameter("lRadio",
					request);

			for (int lInt = 0; lInt < lEmployeeIDList.length; lInt++) {
				MstEmp lObjDcpsEmpMst = (MstEmp) dcpsNewRegistrationDao
						.read(Long.valueOf(lEmployeeIDList[lInt]));
				lObjDcpsEmpMst.setBillGroupId(Long.valueOf(lStrBillGroupId));
				lBlFlag = true;
			}

		} catch (Exception e) {
			//e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error(" Error in getDigiSig " + e, e);
		}

		String lSBStatus = getResponseXMLDocForBillGroup(lBlFlag).toString();

		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");

		return resObj;
	}

	public ResultObject populateDistrictsUsingAjax(
			Map<String, Object> lMapInputMap) {

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS,
				"FAIL");
		HttpServletRequest lObjRequest = (HttpServletRequest) lMapInputMap
				.get("requestObj");
		ServiceLocator lObjServLoctr = (ServiceLocator) lMapInputMap
				.get("serviceLocator");

		try {
			DdoProfileDAO ddoProfileDAO = new DdoProfileDAOImpl(
					CmnDistrictMst.class, lObjServLoctr.getSessionFactory());
			String lStrCurrState = StringUtility.getParameter("cmbState",
					lObjRequest);

			// Call the functions to get list of designations for the selected
			// office
			List lLstDistricts = ddoProfileDAO.getDistricts(Long
					.parseLong(lStrCurrState));

			String lStrTempResult = null;
			if (lLstDistricts != null) {
				lStrTempResult = new AjaxXmlBuilder().addItems(lLstDistricts,
						"desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		} catch (Exception e) {
			//e.printStackTrace();
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

	public ResultObject populateTalukaUsingAjax(Map<String, Object> lMapInputMap) {

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS,
				"FAIL");
		HttpServletRequest lObjRequest = (HttpServletRequest) lMapInputMap
				.get("requestObj");
		ServiceLocator lObjServLoctr = (ServiceLocator) lMapInputMap
				.get("serviceLocator");

		try {
			DdoProfileDAO ddoProfileDAO = new DdoProfileDAOImpl(
					CmnDistrictMst.class, lObjServLoctr.getSessionFactory());
			String lStrCurrDst = StringUtility.getParameter("cmbDist",
					lObjRequest);

			// Call the functions to get list of designations for the selected
			// office
			List lLstTaluka = ddoProfileDAO.getTaluka(Long
					.parseLong(lStrCurrDst));

			String lStrTempResult = null;
			if (lLstTaluka != null) {
				lStrTempResult = new AjaxXmlBuilder().addItems(lLstTaluka,
						"desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		} catch (Exception e) {
			//e.printStackTrace();
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

	private StringBuilder getResponseXMLDocForBillGroup(Boolean lStr) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<txtGroup>");
		lStrBldXML.append(lStr);
		lStrBldXML.append("</txtGroup>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.DDOProfileService#loadDdoEmpSelection(java.util
	 * .Map)
	 */
	public ResultObject loadDdoEmpDeselction(Map inputMap) throws Exception {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		DdoProfileDAO ddoProfileDAO = null;
		List lLstDesignation = null;
		List lLstReasons = null;
		List lLstEmpDeselect = null;
		String lStrDdoCode = null;
		String lStrEmpName = null;
		String lStrSevaarthId = null;
		Long lLngDesigId = null;
		String lStrDesigId = null;

		try {
			setSessionInfo(inputMap);

			ddoProfileDAO = new DdoProfileDAOImpl(DDOProfileServiceImpl.class,serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lLstReasons = IFMSCommonServiceImpl.getLookupValues("Reason For Deslection", gLngLangId, inputMap);

		lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			//lStrDdoCode
			List lLstParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);

			lLstDesignation = lObjDcpsCommonDAO.getAllDesignation((Long) objParentDept[0], gLngLangId);

			lStrDesigId = StringUtility.getParameter("Designation", request).toString();
			lLngDesigId = Long.parseLong(lStrDesigId.trim());

			if (StringUtility.getParameter("fromSearch", request).trim().equals("Yes")) {
				lStrEmpName = StringUtility.getParameter("txtEmployeeName",request).trim().toUpperCase();
				lStrSevaarthId = StringUtility.getParameter("txtSevaarthId",request).trim().toUpperCase();
				lLstEmpDeselect = ddoProfileDAO.getEmpListForDeselection(lStrDdoCode, lLngDesigId, lStrEmpName, lStrSevaarthId);
			} else {
				if (lLngDesigId != -1l) {
					lLstEmpDeselect = ddoProfileDAO.getEmpListForDeselection(lStrDdoCode, lLngDesigId, lStrEmpName,lStrSevaarthId);
				}
			}

			inputMap.put("lStrSelectedDesig", lStrDesigId);
			inputMap.put("DESELECTEMPLIST", lLstEmpDeselect);
			inputMap.put("REASONLIST", lLstReasons);
			inputMap.put("DESIGNATIONLIST", lLstDesignation);

			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			inputMap.put("gDtCurDate", lObjDateFormat.format(gDtCurDate));

			objRes.setResultValue(inputMap);
			objRes.setViewName("DCPSddoEmpDeselect");

		}

		catch (Exception e) {

			//e.printStackTrace();
			gLogger.error("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}

		return objRes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.DDOProfileService#loadSearchDeselction(java.
	 * util.Map)
	 */
	public ResultObject loadDdoEmpSelection(Map inputMap) throws Exception {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		DdoProfileDAO ddoProfileDAO = null;
		List lLstReasons = null;
		List lLstEmpSelect = null;
		List lLstOffices = null;
		String lStrDdoCode = null;
		String lStrSevarthId = null;
		String lStrEmpName = null;
		Boolean lBlMultipleEntriesInHstEmpForEmpId = false;
		Long lLongDcpsEmpId = null;

		try {
			setSessionInfo(inputMap);
			
			ddoProfileDAO = new DdoProfileDAOImpl(DDOProfileServiceImpl.class,serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lLstReasons = IFMSCommonServiceImpl.getLookupValues("Reason For Deslection", gLngLangId, inputMap);
			
			
			lStrDdoCode = ddoProfileDAO.getDdoCodeForDDO(gLngPostId);

			String lStrRequestForSearch = StringUtility.getParameter("requestForSearch", request).trim();
			
			
			if (lStrRequestForSearch.equals("Yes")) {
				
				lStrSevarthId = StringUtility.getParameter("txtSevaarthId",request).trim().toUpperCase();
				lStrEmpName = StringUtility.getParameter("txtEmployeeName",request).trim().toUpperCase();
				
				lLongDcpsEmpId = ddoProfileDAO.getDcpsEmpIdFromSevaarthIdOrName(lStrEmpName, lStrSevarthId);
				if(ddoProfileDAO.checkMultipleEntryInHstEmpForEmpIdOrNot(lLongDcpsEmpId))
				{
					lBlMultipleEntriesInHstEmpForEmpId = true;
				}
				lLstEmpSelect = ddoProfileDAO.getEmpListForSelection(lStrEmpName, lStrSevarthId,lBlMultipleEntriesInHstEmpForEmpId);
			} else {
				//lLstEmpSelect = ddoProfileDAO.getEmpListForSelection(lStrEmpName, lStrSevarthId);
			}

		//	lLstOffices = lObjDcpsCommonDAO.getCurrentOffices(lStrDdoCode);
			
			lLstOffices = lObjDcpsCommonDAO.getSubOffc(gStrPostId);
			
			List lLstBillGroup = lObjDcpsCommonDAO.getBillGroups(lStrDdoCode);
			inputMap.put("BillGroupList", lLstBillGroup);

			inputMap.put("OFFICESLIST", lLstOffices);
			inputMap.put("SELECTEMPLIST", lLstEmpSelect);
			inputMap.put("REASONLIST", lLstReasons);
			//inputMap.put("DDOCODE", lStrDdoCode);
			objRes.setResultValue(inputMap);
			objRes.setViewName("DCPSddoEmpSelect");

		}

		catch (Exception e) {
			//e.printStackTrace();
			gLogger.error("Exception is " + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}

		return objRes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.DDOProfileService#ddoEmpDeselection(java.util
	 * .Map)
	 */
	public ResultObject ddoEmpDeselection(Map inputMap) throws Exception {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		Boolean lBlFlagForDateComparison = true;
		Long lLngHstDcpsId = null;
		HstEmp lObjHstEmp = null;
		Long lLongEmpId = null;
		MstEmp lObjMstEmp = null;

		try {
			setSessionInfo(inputMap);

			DdoProfileDAO ddoProfileMstEmpDAO = new DdoProfileDAOImpl(
					MstEmp.class, serv.getSessionFactory());

			DdoProfileDAO ddoProfileHstEmpDAO = new DdoProfileDAOImpl(
					HstEmp.class, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");

			String lStrArrearsApprovalLimitDate = null;
			Date lDtArrearsApprovalLimitDate = null;

			String lStrEmpId = StringUtility.getParameter("Emp_Id", request)
					.toString().trim();
			String[] lStrArrEmpId = lStrEmpId.split("~");
			
			String lStrDatesOfDeselection = StringUtility.getParameter(
					"DatesOfDeselection", request).trim();
			String[] lStrArrDatesOfDeselection = lStrDatesOfDeselection
					.split("~");
			Date[] lDateArrDatesOfDeselection = new Date[lStrArrDatesOfDeselection.length];
			

			String lStrYearCode = null;
			Long lLongYearCode = null;
			Long lLongYearId = null;
			List lArrayListEmdIds = new ArrayList();

			for (Integer lInt = 0; lInt < lStrArrDatesOfDeselection.length; lInt++) {
				if (lStrArrDatesOfDeselection[lInt] != null
						&& !"".equals(lStrArrDatesOfDeselection[lInt].trim())) {
					lDateArrDatesOfDeselection[lInt] = simpleDateFormat
							.parse(lStrArrDatesOfDeselection[lInt]);

					// Code Added for date checking entry in arrears before
					// deselection

					if (ddoProfileMstEmpDAO.checkArrearsExistForEmp(Long
							.valueOf(lStrArrEmpId[lInt]))) {
						lStrYearCode = lStrArrDatesOfDeselection[lInt].trim()
								.substring(6, 10).trim();

						Calendar lCalYearCodePassed = Calendar.getInstance();
						lCalYearCodePassed
								.setTime(lDateArrDatesOfDeselection[lInt]);

						lStrArrearsApprovalLimitDate = "01/06/";
						lStrArrearsApprovalLimitDate = lStrArrearsApprovalLimitDate
								.trim().concat(lStrYearCode);
						lDtArrearsApprovalLimitDate = simpleDateFormat
								.parse(lStrArrearsApprovalLimitDate);

						Calendar lCalArrearsApprovalLimit = Calendar
								.getInstance();
						lCalArrearsApprovalLimit
								.setTime(lDtArrearsApprovalLimitDate);

						if (lCalYearCodePassed.before(lCalArrearsApprovalLimit)) {
							lLongYearCode = Long.valueOf(lStrYearCode) - 1l;
							lStrYearCode = lLongYearCode.toString();
						}

						lLongYearId = lObjDcpsCommonDAO
								.getFinYearIdForYearCode(lStrYearCode);
						lBlFlagForDateComparison = ddoProfileMstEmpDAO
								.checkArrearsApprovedBeforeDeselection(Long
										.valueOf(lStrArrEmpId[lInt]),
										lLongYearId);
						if (!lBlFlagForDateComparison) {
							break;
						}
					}
				}
			}

			if (lBlFlagForDateComparison) {
				
				String lStrReasonForDeselection = StringUtility.getParameter(
						"ReasonForDeselection", request).trim();
				String[] lStrArrReasonForDeselection = lStrReasonForDeselection
						.split("~");
				
				String lStrRemarksForDeselection = StringUtility.getParameter(
						"Remarks", request).trim();
				String[] lStrArrRemarksForDeselection = lStrRemarksForDeselection
						.split("~");
				
				String lStrOrderNo = StringUtility.getParameter(
						"orderNo", request).trim();
				String[] lStrArrOrderNo = lStrOrderNo
						.split("~");
				
				String lStrOrderDate = StringUtility.getParameter(
						"orderDate", request).trim();
				String[] lStrArrOrderDate = lStrOrderDate
						.split("~");
				
				Date[] lDateArrOrderDate = new Date[lStrArrDatesOfDeselection.length];
				
				for (Integer index = 0; index < lStrArrEmpId.length; index++) {

					lLongEmpId = Long.parseLong(lStrArrEmpId[index]);
					lObjMstEmp = (MstEmp) ddoProfileMstEmpDAO.read(lLongEmpId);
					lObjMstEmp.setDdoCode(null);
					//lObjMstEmp.setBillGroupId(null);
					
					if (lStrArrReasonForDeselection[index].equals("700053")) // If
					// Reason
					// is
					// 'On
					// deputation'
					{
						lObjMstEmp.setEmpOnDeptn(2);
					}
					lObjMstEmp.setDeselectionDate(lDateArrDatesOfDeselection[index]);

					lLngHstDcpsId = ddoProfileHstEmpDAO.getHstEmpPkVal(lLongEmpId);
					lObjHstEmp = (HstEmp) ddoProfileHstEmpDAO.read(lLngHstDcpsId);
					lObjHstEmp.setReasonForDeselection(lStrArrReasonForDeselection[index]);
					
					if(lStrArrOrderNo[index] != null && !"".equals(lStrArrOrderNo[index]))
					{
						lObjHstEmp.setOrderNo(lStrArrOrderNo[index].trim());
					}
					
					if(lStrArrOrderDate[index] != null && !"".equals(lStrArrOrderDate[index]))
					{
						lDateArrOrderDate[index] = simpleDateFormat.parse(lStrArrOrderDate[index].trim());
						lObjHstEmp.setOrderDate(lDateArrOrderDate[index]);
					}
					
					if(!"".equals(lStrArrRemarksForDeselection[index]))
					{
						lObjHstEmp.setRemarksForDeselection(lStrArrRemarksForDeselection[index]);
					}
					lObjHstEmp.setEndDate(lDateArrDatesOfDeselection[index]);
					lObjHstEmp.setUpdatedUserId(gLngUserId);
					lObjHstEmp.setUpdatedPostId(gLngPostId);
					lObjHstEmp.setUpdatedDate(gDtCurDate);
					
					lArrayListEmdIds.add(lObjMstEmp.getOrgEmpMstId());

					ddoProfileHstEmpDAO.update(lObjHstEmp);
					ddoProfileMstEmpDAO.update(lObjMstEmp);
				}
				
				inputMap.put("EmpIdArray", lArrayListEmdIds);
				inputMap.put("DatesOfDeSelectionArray", lDateArrDatesOfDeselection);
				
				ResultObject resObj = serv.executeService("deSelectEmployee",
						inputMap);

				if (resObj.getResultCode() == ErrorConstants.ERROR) {
					throw new Exception();
				}
				
			}
			
			lBlFlag = true;

		}

		catch (Exception e) {
			//e.printStackTrace();
			gLogger.error("Exception is " + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag, lBlFlagForDateComparison)
				.toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();
		inputMap.put("ajaxKey", lStrResult);

		objRes.setViewName("ajaxData");
		objRes.setResultValue(inputMap);
		return objRes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.DDOProfileService#ddoEmpSelection(java.util.Map)
	 */
	public ResultObject ddoEmpSelection(Map inputMap) throws Exception {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		DdoProfileDAO ddoProfileDAO = null;
		NewRegDdoDAO lObjNewRegDdoDAO = null;
		HstEmp lObjHstEmp = null;
		Long lLongEmpId = null;
		Long lLongHstDcpsId = null;
		Boolean lBlFlag = false;
		MstEmp lObjMstEmp = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;
		String lStrDsgnId = null;
		String lstrLocId = null;

		try {
			setSessionInfo(inputMap);

			ddoProfileDAO = new DdoProfileDAOImpl(MstEmp.class, serv
					.getSessionFactory());

			lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv
					.getSessionFactory());
			
			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(
					RltDcpsPayrollEmp.class, serv.getSessionFactory());
			
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

		//	String lStrDdoCode = StringUtility.getParameter("ddoCode", request).toString().trim();
			String lStrDdoCode="";
			String lStrEmpIds = StringUtility.getParameter("Emp_Id", request)
					.toString().trim();
			String[] lStrArrEmpIds = lStrEmpIds.split("~");
			String lStrDatesOfSelection = StringUtility.getParameter(
					"DatesOfSelection", request);
			String[] lStrArrDatesOfSelection = lStrDatesOfSelection.split("~");

			Date[] lDateArrDatesOfSelection = new Date[lStrArrDatesOfSelection.length];
			for (Integer lInt = 0; lInt < lStrArrDatesOfSelection.length; lInt++) {
				if (lStrArrDatesOfSelection[lInt] != null
						&& !"".equals(lStrArrDatesOfSelection[lInt].trim())) {
					lDateArrDatesOfSelection[lInt] = simpleDateFormat
							.parse(lStrArrDatesOfSelection[lInt]);
				}
			}

			String lStrOffices = StringUtility.getParameter("offices", request);
			String[] lStrArrOffices = lStrOffices.split("~");
			
			String lStrPosts = StringUtility.getParameter("posts", request);
			String[] lStrArrPosts = lStrPosts.split("~");
			Long[] lLongArrPosts = new Long[lStrArrPosts.length];
			for (Integer lInt = 0; lInt < lStrArrPosts.length; lInt++) {

				if(lStrArrPosts[lInt] != null && !lStrArrPosts[lInt].equals("null") && !lStrArrPosts[lInt].equals("-1") )
				{
					lLongArrPosts[lInt] = Long.parseLong(lStrArrPosts[lInt]);
				}
				else
				{
					lLongArrPosts[lInt] = null;
				}
			}
			
			String lStrBillGroups = StringUtility.getParameter("billGroups", request).trim();
			String[] lStrArrBillGroups = lStrBillGroups.split("~");
			System.out.println("fffff"+lStrArrBillGroups);
			Long[] lLongArrBillGroups = new Long[lStrArrBillGroups.length];
			for (Integer lInt = 0; lInt < lStrArrBillGroups.length; lInt++) {
				if(lStrArrBillGroups[lInt] != null && !lStrArrBillGroups[lInt].equals("null") && !lStrArrBillGroups[lInt].equals("-1"))
				{
					lLongArrBillGroups[lInt] = Long.parseLong(lStrArrBillGroups[lInt]);
				}
				else
				{
					lLongArrBillGroups[lInt] = null;
				}
			}
			
			List lArrayListEmdIds = new ArrayList();
			List lArrayListPostIds = new ArrayList();
			List lArrayListBillGroups = new ArrayList();
			for (Integer index = 0; index < lStrArrEmpIds.length; index++) 
			{
				lArrayListPostIds.add(lStrArrPosts[index]);
				lArrayListBillGroups.add(lLongArrBillGroups[index]);
			}
			
			//List lLstParentDept = new ArrayList();
			List lLstParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
			String lStrNewParentDept = null;
			Long lLongNewCadre = null;
			Long lLongCadreCode = null;
			
			if(lLstParentDept != null && !lLstParentDept.isEmpty())
			{
				Object[] objParentDept = (Object[]) lLstParentDept.get(0);
				if(objParentDept[0] != null)
				{
					lStrNewParentDept = objParentDept[0].toString();
				}
			}
			
			for (Integer index = 0; index < lStrArrEmpIds.length; index++) {

				if (!"".equalsIgnoreCase(lStrArrEmpIds[index])
						&& lStrArrEmpIds[index] != null) {
					lLongEmpId = Long.parseLong(lStrArrEmpIds[index]);
				}

				lObjMstEmp = (MstEmp) ddoProfileDAO.read(lLongEmpId);
				if(lObjMstEmp.getCadre() != null && !"".equals(lObjMstEmp.getCadre()))
				{
					lLongCadreCode = lObjDcpsCommonDAO.getCadreCodeforCadreId(Long.valueOf(lObjMstEmp.getCadre()));
				}
				lStrDdoCode=lObjDcpsCommonDAO.getDDofromOffc(lStrArrOffices[index]);
				//ADDED BY KINJAL FOR JOINING AND RELIEVING :START
				lstrLocId=lObjDcpsCommonDAO.getLocationCodeForDDO(lStrDdoCode);
				//ADDED BY KINJAL FOR JOINING AND RELIEVING :END
				lObjMstEmp.setDdoCode(lStrDdoCode);
				String lStrCurrffc=lObjDcpsCommonDAO.getCurrOffc(lStrArrOffices[index]);
				lObjMstEmp.setCurrOff(lStrCurrffc);
				lObjMstEmp.setLocId(Long.parseLong(lstrLocId));
				lObjMstEmp.setEmpTransferred(1l);
				
				//Changes Parent Department and cadre of the Employee when he/she is transferred to a DDO of new Parent Department
				lLstParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
				if(lLstParentDept != null && !lLstParentDept.isEmpty())
				{
					Object[] objParentDept = (Object[]) lLstParentDept.get(0);
					if(objParentDept[0] != null)
					{
						lStrNewParentDept = objParentDept[0].toString();
					}
				}
				if(lStrNewParentDept != null)
				{
					lObjMstEmp.setParentDept(lStrNewParentDept);
					lLongNewCadre = lObjDcpsCommonDAO.getCadreIdforCadreCodeAndFieldDept(lLongCadreCode, Long.valueOf(lStrNewParentDept.trim()));
				}
				if(lLongNewCadre != null)
				{
					lObjMstEmp.setCadre(lLongNewCadre.toString());
				}
				
				if(lLongArrBillGroups[index] != null)
				{
					lObjMstEmp.setBillGroupId(lLongArrBillGroups[index] );
				}
				
				lArrayListEmdIds.add(lObjMstEmp.getOrgEmpMstId());
				
				lObjRltDcpsPayrollEmp = lObjNewRegDdoDAO.getPayrollVOForEmpId(lLongEmpId);
				
				if(lLongArrPosts[index] != null)
				{
					lObjRltDcpsPayrollEmp.setPostId(lLongArrPosts[index]);
					lStrDsgnId = lObjSearchEmployeeDAO.getDesigFromPost(lLongArrPosts[index]);
					lObjMstEmp.setDesignation(lStrDsgnId);
				}
				
				lObjNewRegDdoDAO.update(lObjRltDcpsPayrollEmp);
				ddoProfileDAO.update(lObjMstEmp);
				List locList=lObjDcpsCommonDAO.getDDODtls(lStrDdoCode);
				gStrLocationCode=locList.get(0).toString();
				lObjHstEmp = new HstEmp();
				lLongHstDcpsId = IFMSCommonServiceImpl.getNextSeqNum(
						"hst_dcps_emp_details", inputMap);
				lObjHstEmp.setHstdcpsId(lLongHstDcpsId);
				lObjHstEmp.setDbId(gLngDBId);
				lObjHstEmp.setDcpsEmpId(lLongEmpId);
				lObjHstEmp.setLocId(Long.parseLong(gStrLocationCode));
				lObjHstEmp.setDdoCode(lStrDdoCode);
				lObjHstEmp.setStartDate(lDateArrDatesOfSelection[index]);
				lObjHstEmp.setCreatedUserId(gLngUserId);
				lObjHstEmp.setCreatedPostId(gLngPostId);
				lObjHstEmp.setCreatedDate(gDtCurDate);
				ddoProfileDAO.create(lObjHstEmp);
				lLstParentDept = null;
				lStrNewParentDept = null;
				lLongNewCadre = null;
				lLongCadreCode = null;
				
				
			}
			
			inputMap.put("EmpIdArray", lArrayListEmdIds);
			inputMap.put("NewPostArray", lArrayListPostIds);
			inputMap.put("BillGroupArray", lArrayListBillGroups);
			inputMap.put("DatesOfSelectionArray",lDateArrDatesOfSelection);
			
			ResultObject resObj = serv.executeService("doTransfer",
					inputMap);

			if (resObj.getResultCode() == ErrorConstants.ERROR) {
				throw new Exception();
			}
		
			lBlFlag = true;

			String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					lSBStatus).toString();
			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		}

		catch (Exception e) {
			//e.printStackTrace();
			gLogger.error("Exception is " + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			throw e;
		}

		return objRes;

	}

	public ResultObject loadDCPSTierDDO(Map<String, Object> inputMap)
			throws Exception {

		// TODO Auto-generated method stub

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		List lLstTypeOfArrear = null;
		List lLstDesignation = null;
		List lLstDepartment = null;
		String lStrDesignation = null;
		List lLstTierDtlsFromDesig = null;
		String lStrTierType = null;
		try {
			setSessionInfo(inputMap);

			lStrTierType = StringUtility.getParameter("TierType", request);

			lLstTypeOfArrear = IFMSCommonServiceImpl
					.getLookupValues("TypeOfArrear", SessionHelper
							.getLangId(inputMap), inputMap);
			gLogger.info("here is the size of event list :"
					+ lLstTypeOfArrear.size());
			Long lLngDepartmentId = Long.valueOf(gObjRsrcBndle
					.getString("DCPS.DEPARTMENTID"));
			ArrearsDAO lObjcmnDCPSArrearsDAO = new ArrearsDAOImpl(null, serv
					.getSessionFactory());
			lLstDepartment = lObjcmnDCPSArrearsDAO.getAllDepartment(
					lLngDepartmentId, SessionHelper.getLangId(inputMap));

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			List lLstParentDept = lObjDcpsCommonDAO
					.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);

			lLstDesignation = lObjDcpsCommonDAO.getAllDesignation(
					(Long) objParentDept[0], gLngLangId);

			if (StringUtility.getParameter("TierDraft", request) != null
					&& StringUtility.getParameter("TierDraft", request)
							.length() > 0) {
				StringUtility.getParameter("TierDraft", request);
				lStrDesignation = StringUtility.getParameter("Designation",
						request);
				lObjcmnDCPSArrearsDAO = new ArrearsDAOImpl(
						MstDcpsTierICntrnbtn.class, serv.getSessionFactory());
				lLstTierDtlsFromDesig = lObjcmnDCPSArrearsDAO
						.getTierDtlFromDesig(lStrDesignation);
				inputMap.put("TierDraftList", lLstTierDtlsFromDesig);

			}
			inputMap.put("TierType", lStrTierType);
			inputMap.put("lLstDepartment", lLstDepartment);
			inputMap.put("lLstDesignation", lLstDesignation);
			inputMap.put("lLstTypeOfArrear", lLstTypeOfArrear);
			gLogger.info("Load Sucessful");
		}

		catch (Exception e) {
			gLogger.error("Error is;" + e, e);
			//e.printStackTrace();
		}
		resObj.setResultValue(inputMap);
		resObj.setViewName("dcpsArrearsTierI");
		return resObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.DDOProfileService#loadSixPCArrearAmountScheduleDDO
	 * (java.util.Map)
	 */
	public ResultObject displayIFSCCodeForBranch(Map inputMap) throws Exception {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator servLoc = (ServiceLocator) inputMap
				.get("serviceLocator");
		DdoProfileDAO ddoProfileDAO = null;

		try {
			setSessionInfo(inputMap);
			Long lLongbranchName = Long.valueOf(StringUtility.getParameter(
					"cmbBranchName", request).toString().trim());

			ddoProfileDAO = new DdoProfileDAOImpl(DDOProfileServiceImpl.class,
					servLoc.getSessionFactory());

			String micrCode = ddoProfileDAO.getIFSCCodeForBranch(lLongbranchName);

			String lSBStatus = getResponseXMLDocforIFSCCode(micrCode)
					.toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		}

		catch (Exception e) {
			gLogger.error(
					"Exception occured in searchPensionCaseList exception is "
							+ e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject displayIFSCCodeForBranchBsrCode(Map inputMap)
			throws Exception {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator servLoc = (ServiceLocator) inputMap
				.get("serviceLocator");
		DdoProfileDAO ddoProfileDAO = null;

		try {
			setSessionInfo(inputMap);

			String lStrbranchName = StringUtility.getParameter("cmbBranchName",
					request).toString().trim();
			gLogger.info("lStrbranchName "+lStrbranchName);
			
			

			ddoProfileDAO = new DdoProfileDAOImpl(DDOProfileServiceImpl.class,
					servLoc.getSessionFactory());

			String micrCode = ddoProfileDAO
					.getIFSCCodeForBranchBsrCode(lStrbranchName);

			String lSBStatus = getResponseXMLDocforIFSCCode(micrCode)
					.toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		}

		catch (Exception e) {
			gLogger.error(
					"Exception occured in searchPensionCaseList exception is "
							+ e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	private StringBuilder getResponseXMLDocforIFSCCode(String micrCode) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<IFSCCode>");
		lStrBldXML.append(micrCode);
		lStrBldXML.append("</IFSCCode>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;

	}

	public ResultObject detachGroup(Map<String, Object> inputMap) {

		Log logger = LogFactory.getLog(getClass());
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");

		boolean lBlFlag = false;

		try {

			NewRegDdoDAO dcpsNewRegistrationDao = new NewRegDdoDAOImpl(
					MstEmp.class, serv.getSessionFactory());

			String lEmployeeIDListString = StringUtility.getParameter(
					"dcpsEmpIds", request);
			String[] lEmployeeIDList = lEmployeeIDListString.split("~");

			for (int lInt = 0; lInt < lEmployeeIDList.length; lInt++) {
				MstEmp lObjDcpsEmpMst = (MstEmp) dcpsNewRegistrationDao
						.read(Long.valueOf(lEmployeeIDList[lInt]));
				lObjDcpsEmpMst.setBillGroupId(null);
				lBlFlag = true;
			}

		} catch (Exception e) {
			//e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error(" Error in getDigiSig " + e, e);
		}

		String lSBStatus = getResponseXMLDocForBillGroup(lBlFlag).toString();

		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");

		return resObj;
	}

	public ResultObject getVacantPostsInOffice(Map<String, Object> lMapInputMap) {

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		List lLstVacantPostsInOffice = null;
		String lStrOffice = null;
		String lStrDdoCode = null;
		String lStrDesig = null;
		String officeSelected=null;
		
		ServiceLocator serv = (ServiceLocator)lMapInputMap.get("serviceLocator");
		Map loginDetailsMap = (Map) lMapInputMap.get("baseLoginMap");

		Map voToService = (Map)lMapInputMap.get("voToServiceMap");

		try {

			/* Sets the Session Information */
			setSessionInfo(lMapInputMap);

			/* Initializes the DAO */
			DdoProfileDAO lObjDdoProfileDAO = new DdoProfileDAOImpl(DDOProfileServiceImpl.class, serv.getSessionFactory());
			lStrOffice = StringUtility.getParameter("cmbOffice", request).trim();
			
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lStrDdoCode = lObjDcpsCommonDAO.getDDofromOffc(lStrOffice);
			gLogger.info("DDO code: "+lStrDdoCode );
			
			lStrDesig = StringUtility.getParameter("cmbDesig", request).trim();
			gLogger.info("the lStrDesig selected is"+lStrDesig);
			
			officeSelected = StringUtility.getParameter("cmbOfficeForChangePost", request).trim();
			gLogger.info("the lStrDesig selected is"+lStrDesig);
			gLogger.info("the office selected is"+officeSelected);
			
			String ddoCode=null;

			//if (!(lStrOffice.equals(""))) {
			//	lLstVacantPostsInOffice = lObjDdoProfileDAO.getVacantPostsInOffice(Long.valueOf(lStrOffice),lStrDdoCode);
			//}
			
			if((lStrOffice!=null) && (lStrOffice!="") && (Long.parseLong(lStrOffice)!=-1)){
				ddoCode = lObjDcpsCommonDAO.getDDofromOffc(lStrOffice);
				//ddoCode = lStrOffice;
				gLogger.info("hii i m in if condition");
			}
			//code to find ddo code by roshan
			else{
				
				ddoCode=lObjDdoProfileDAO.getDdoCodeForOffice(officeSelected);
				lStrOffice=officeSelected;
			}
			
			gLogger.info("hiiii ddo code is"+ddoCode);
			if (!(ddoCode.equals(""))||!(lStrOffice.equals(""))) {
				lLstVacantPostsInOffice = lObjDdoProfileDAO.getAllVacantPostsInOffice(Long.valueOf(lStrOffice),ddoCode,lStrDesig);//modified by shreya
			}
			
			

			String lStrTempResult = null;
			if (lLstVacantPostsInOffice != null) {
				lStrTempResult = new AjaxXmlBuilder().addItems(lLstVacantPostsInOffice, "desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		} catch (Exception e) {
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

	public ResultObject chkDtLaterThanDeSelectionDt(Map<String, Object> inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = true;
		HstEmp lObjHstEmp = null;
		Date lDtDeselectionDate = null;
		Date lDtSelectionDate = null;
		String lStrDeselectionDate = null;
		String lStrSelectionDate = null;
		String lStrDcpsEmpId = null;

		try {

			setSessionInfo(inputMap);
			DdoProfileDAO lObjDdoProfileDAO = new DdoProfileDAOImpl(null, serv
					.getSessionFactory());

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			lStrDcpsEmpId = StringUtility.getParameter("dcpsEmpId", request).trim();
			
			lStrSelectionDate = StringUtility.getParameter("selectionDate",request).trim();
			lDtSelectionDate = sdf.parse(lStrSelectionDate);

			Long lLngDcpsEmpId = null;
			if (!lStrDcpsEmpId.equals("")) {
				lLngDcpsEmpId = Long.valueOf(lStrDcpsEmpId);
			}

			lObjHstEmp = lObjDdoProfileDAO.getHstEmpVOLatest(lLngDcpsEmpId);
			lDtDeselectionDate = lObjHstEmp.getEndDate();
			
			if(lDtDeselectionDate != null)
			{
				lStrDeselectionDate = sdf.format(lDtDeselectionDate);
				if (lDtSelectionDate.before(lDtDeselectionDate)) {
					lBlFlag = false;
				}
			}
			
			String lSBStatus = getResponseXMLDocForSelDeseltn(lBlFlag,
					lStrDeselectionDate).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");
			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			//e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}

		return resObj;
	}
	//added by Samadhan
	public ResultObject populateCityUsingAjax(Map<String, Object> lMapInputMap) {

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS,
				"FAIL");
		HttpServletRequest lObjRequest = (HttpServletRequest) lMapInputMap
				.get("requestObj");
		ServiceLocator lObjServLoctr = (ServiceLocator) lMapInputMap
				.get("serviceLocator");

		try {
			DdoProfileDAO ddoProfileDAO = new DdoProfileDAOImpl(
					CmnDistrictMst.class, lObjServLoctr.getSessionFactory());
			String lStrCurrDst = StringUtility.getParameter("cmbDist",
					lObjRequest);

			// Call the functions to get list of designations for the selected
			// office
			List lLstTaluka = ddoProfileDAO.getCity(Long.parseLong(lStrCurrDst));

			String lStrTempResult = null;
			if (lLstTaluka != null) {
				lStrTempResult = new AjaxXmlBuilder().addItems(lLstTaluka,
						"desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		} catch (Exception e) {
			//e.printStackTrace();
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

}
