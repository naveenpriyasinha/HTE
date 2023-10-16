/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 18, 2011		Kapil Devani								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

//com.tcs.sgv.dcps.service.ChangesFormServiceImpl
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

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
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.ChangesFormDAO;
import com.tcs.sgv.dcps.dao.ChangesFormDAOImpl;
import com.tcs.sgv.dcps.dao.DdoProfileDAO;
import com.tcs.sgv.dcps.dao.DdoProfileDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.valueobject.DcpsCadreMst;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.HstDcpsChanges;
import com.tcs.sgv.dcps.valueobject.HstDcpsNomineeChanges;
import com.tcs.sgv.dcps.valueobject.HstDcpsOfficeChanges;
import com.tcs.sgv.dcps.valueobject.HstDcpsOtherChanges;
import com.tcs.sgv.dcps.valueobject.HstDcpsPersonalChanges;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.TrnDcpsChanges;
import com.tcs.sgv.wf.delegate.WorkFlowDelegate;

/**
 * Class Description -
 * 
 * 
 * @author Kapil Devani
 * @version 0.1
 * @since JDK 5.0 Mar 18, 2011
 */
public class ChangesFormServiceImpl extends ServiceImpl implements ChangesFormService {

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

	public ResultObject loadChangesForm(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {

			setSessionInfo(inputMap);

			ChangesFormDAO lObjChangesDAO = new ChangesFormDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrUserType = StringUtility.getParameter("User", request);
			inputMap.put("UserType", lStrUserType);

			List lLstChanges = IFMSCommonServiceImpl.getLookupValues("DCPS_Changes", SessionHelper.getLangId(inputMap),
					inputMap);
			inputMap.put("CHANGESLIST", lLstChanges);

			String lStrDdoCode = null;
			if (lStrUserType.equals("DDOAsst")) {
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			} else if (lStrUserType.equals("DDO")) {
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			List lLstParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);

			List lLstDesignation = lObjDcpsCommonDAO.getAllDesignation((Long) objParentDept[0], gLngLangId);
			inputMap.put("lLstDesignation", lLstDesignation);

			String lStrDesignationId = StringUtility.getParameter("DesignationId", request);

			if (!(lStrDesignationId.equals(""))) {
				List empList = lObjChangesDAO.getAllDcpsEmployees(lStrDesignationId, lStrDdoCode);
				inputMap.put("empList", empList);

				inputMap.put("DesignationId", lStrDesignationId);
			}

			String lStrChanges = StringUtility.getParameter("Changes", request);

			if (!(lStrChanges.equals(""))) {
				inputMap.put("Changes", lStrChanges);
			}

			String lStrType = StringUtility.getParameter("Type", request);
			inputMap.put("Type", lStrType);

			resObj.setResultValue(inputMap);
			resObj.setViewName("DCPSChanges");

		} catch (Exception e) {
			// e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject loadChangesDrafts(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lLstDesignation = null;
		List ChangesDraftsList = null;

		try {

			setSessionInfo(inputMap);

			String hidElementId = StringUtility.getParameter("elementId", request);
			inputMap.put("hidElementId", hidElementId);

			String lStrUserType = StringUtility.getParameter("User", request);
			inputMap.put("UserType", lStrUserType);

			ChangesFormDAO lObjChangesDAO = new ChangesFormDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrDdoCode = null;
			if (lStrUserType.equals("DDOAsst")) {
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			} else if (lStrUserType.equals("DDO")) {
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}

			gLogger.info("hi i am ddo" + lStrDdoCode);

			// code to find the district
			String districtID = lObjChangesDAO.districtName(lStrDdoCode);
			gLogger.info("district id found is" + districtID);
			// code to find the taluka
			List talukaList = lObjChangesDAO.allTaluka(districtID);

			List lLstParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);

			lLstDesignation = lObjDcpsCommonDAO.getAllDesignation((Long) objParentDept[0], gLngLangId);
			inputMap.put("lLstDesignation", lLstDesignation);
			String lStrDesignationId = null;
			if (!StringUtility.getParameter("DesignationId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("DesignationId", request) != null) {
				lStrDesignationId = StringUtility.getParameter("DesignationId", request);
			}
			gLogger.info("hiii i m roshan for changes drafts");

			// added by roshan
			String talukaId = null;
			String ddoSelected = null;

			if ((StringUtility.getParameter("taluka", request) != null)
					&& (StringUtility.getParameter("taluka", request) != "")
					&& (Long.parseLong(StringUtility.getParameter("taluka", request)) != -1)) {
				talukaId = StringUtility.getParameter("taluka", request);
			}

			if ((StringUtility.getParameter("ddoCode", request) != null)
					&& (StringUtility.getParameter("ddoCode", request) != "")) {
				ddoSelected = StringUtility.getParameter("ddoCode", request);
			}
			// commented by roshan
			// if (!(lStrDesignationId.equals("")))
			ChangesDraftsList = lObjChangesDAO.getChangesDraftsForDesig(lStrDesignationId, lStrUserType, lStrDdoCode,
					talukaId, ddoSelected);
			gLogger.info("size in serive is" + ChangesDraftsList.size());
			inputMap.put("ChangesDraftsList", ChangesDraftsList);
			inputMap.put("DesignationId", lStrDesignationId);
			inputMap.put("talukaList", talukaList);
			inputMap.put("talukaId", talukaId);
			inputMap.put("ddoSelected", ddoSelected);
			resObj.setResultValue(inputMap);
			resObj.setViewName("DCPSChangesDrafts");

		} catch (Exception e) {
			// e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject changesPersonalDetails(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Long dcpsChangesId = null;
		HstDcpsChanges lObjHstDcpsChanges = null;
		HstDcpsPersonalChanges lObjHstDcpsPersonalChanges = null;
		Optional<HstDcpsPersonalChanges> lObjHstDcpsPersonalChanges1 = null;

		Long HstDcpsPersonalChangesId = null;
		String lStrDesignationDraft = null;
		MstEmp MstEmpObj = null;
		String lStrDdoCode = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;

		try {
			setSessionInfo(inputMap);

			ChangesFormDAO lObjChangesDAO = new ChangesFormDAOImpl(HstDcpsPersonalChanges.class,
					serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Long lLngEmpID = Long.parseLong(StringUtility.getParameter("EmpId", request));

			if (!StringUtility.getParameter("dcpsChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsChangesId", request) != null) {
				dcpsChangesId = Long.valueOf(StringUtility.getParameter("dcpsChangesId", request));

				lObjHstDcpsChanges = lObjChangesDAO.getChangesDetails(dcpsChangesId);

				HstDcpsPersonalChangesId = lObjChangesDAO.getPersonalChangesIdforChangesId(dcpsChangesId);
				lObjHstDcpsPersonalChanges = (HstDcpsPersonalChanges) lObjChangesDAO.read(HstDcpsPersonalChangesId);
				// lObjHstDcpsPersonalChanges1 = (Optional<HstDcpsPersonalChanges>)
				// lObjChangesDAO.read(HstDcpsPersonalChangesId);
				lStrDesignationDraft = StringUtility.getParameter("designationDraft", request);

			}
			gLogger.info("dcpsChangesId****" + dcpsChangesId);
			MstEmpObj = lObjChangesDAO.getEmpDetails(lLngEmpID);

			lObjRltDcpsPayrollEmp = lObjChangesDAO.getEmpPayrollDetailsForEmpId(lLngEmpID);

			inputMap.put("lStrDesignationDraft", lStrDesignationDraft);
			inputMap.put("dcpsChangesId", dcpsChangesId);
			inputMap.put("lObjHstDcpsChanges", lObjHstDcpsChanges);
			inputMap.put("lObjHstDcpsPersonalChanges", lObjHstDcpsPersonalChanges);
			// Added by Naveen Priya Sinha dated 13012023 for character to supported on jsp
			// on with jdk 1.8
			String empGenderChange = null;
			if (lObjHstDcpsPersonalChanges != null) {
				/* if(!Optional.ofNullable(lObjHstDcpsPersonalChanges).isPresent()) { */
				if (lObjHstDcpsPersonalChanges.getGender() != ' ' || lObjHstDcpsPersonalChanges.getGender() != null) {
					inputMap.put("empGenderChange", lObjHstDcpsPersonalChanges.getGender().toString());
				} else {
					inputMap.put("empGenderChange", "");
				}
			} else {
				inputMap.put("empGenderChange", "");
			}
			String empGenderEmp = null;
			if (Optional.ofNullable(MstEmpObj).isPresent()) {
				inputMap.put("empGenderEmp", MstEmpObj.getGender().toString());
			} else {
				inputMap.put("empGenderEmp", "");
			}

			// Ended by Naveen Priya Sinha dated 13012023
			inputMap.put("lObjEmpData", MstEmpObj);
			inputMap.put("lObjRltDcpsPayrollEmp", lObjRltDcpsPayrollEmp);
			inputMap.put("phychallanged", lObjRltDcpsPayrollEmp.getPhyPTApplicable().toString());

			// Get States
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			inputMap.put("STATENAMES", lLstState);

			// Get Salutation List from Lookup Master
			List listSalutation = IFMSCommonServiceImpl.getLookupValues("Salutation", SessionHelper.getLangId(inputMap),
					inputMap);
			inputMap.put("listSalutation", listSalutation);

			// Get Designations

			// Get the Cadre list from the database
			// /List listCadres = lObjDcpsCommonDAO.getCadres();
			// inputMap.put("CADRELIST", listCadres);

			// Set the date of joining limit as 01-Jan-2005
			inputMap.put("lDtJoiDtLimit", "01/01/2005");

			Date lDtcurDate = SessionHelper.getCurDate();
			inputMap.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));

			String lStrDesignation = StringUtility.getParameter("designationId", request);
			inputMap.put("lStrDesignation", lStrDesignation);

			String lStrChangesType = StringUtility.getParameter("changesType", request);
			inputMap.put("lStrChangesType", lStrChangesType);

			String lStrUserType = StringUtility.getParameter("UserType", request);
			inputMap.put("UserType", lStrUserType);

			if (lStrUserType.equals("DDOAsst")) {
				inputMap.put("EditForm", "Y");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				List UserList = getHierarchyUsers(inputMap, lStrUserType);
				inputMap.put("UserList", UserList);
				inputMap.put("ForwardToPost", UserList.get(UserList.size() - 1));
			} else if (lStrUserType.equals("DDO")) {
				inputMap.put("EditForm", "N");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			inputMap.put("DDOCODE", lStrDdoCode);

			List lLstParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);
			List lLstDesignation = lObjDcpsCommonDAO.getAllDesignation((Long) objParentDept[0], gLngLangId);
			inputMap.put("lLstDesignation", lLstDesignation);

			resObj.setResultValue(inputMap);
			resObj.setViewName("ChangePersonalDetails");

		} catch (Exception e) {
			// e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject changesOfficeDetails(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Long dcpsChangesId = null;
		HstDcpsChanges lObjHstDcpsChanges = null;
		HstDcpsOfficeChanges lObjHstDcpsOfficeChanges = null;
		Long HstDcpsOfficeChangesId = null;
		String lStrDesignationDraft = null;
		MstEmp MstEmpObj = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;
		DdoOffice lObjDdoOfficeVO = null;
		String lStrDdoCode = null;
		Long lLongDdoOfficeId = null;
		// Date dobOfEmployee=null;

		try {
			setSessionInfo(inputMap);

			ChangesFormDAO lObjChangesDAO = new ChangesFormDAOImpl(HstDcpsOfficeChanges.class,
					serv.getSessionFactory());

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			NewRegDdoDAO lObjNewRegDdoDAOForCadre = new NewRegDdoDAOImpl(DcpsCadreMst.class, serv.getSessionFactory());

			DdoProfileDAO lObjDdoProfileDAO = new DdoProfileDAOImpl(null, serv.getSessionFactory());

			// Gets values for hidden variables for going back
			String hidDcpsId = StringUtility.getParameter("hidDcpsId", request);
			String hidEmpName = StringUtility.getParameter("hidEmpName", request);
			String hidBirthDate = StringUtility.getParameter("hidBirthDate", request);
			inputMap.put("hidDcpsId", hidDcpsId);
			inputMap.put("hidEmpName", hidEmpName);
			inputMap.put("hidBirthDate", hidBirthDate);

			String hidSevarthId = StringUtility.getParameter("hidSevarthId", request).trim();
			inputMap.put("hidSevarthId", hidSevarthId);

			String hidName = StringUtility.getParameter("hidName", request).trim();
			inputMap.put("hidName", hidName);

			String FromSearchEmp = StringUtility.getParameter("FromSearchEmp", request).trim();
			inputMap.put("FromSearchEmp", FromSearchEmp);

			String FromChangesOfficeElement = StringUtility.getParameter("FromChangesOfficeElement", request).trim();
			inputMap.put("FromChangesOfficeElement", FromChangesOfficeElement);

			// Get List of current Data
			Long lLngEmpID = Long.parseLong(StringUtility.getParameter("EmpId", request));

			if (!StringUtility.getParameter("dcpsChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsChangesId", request) != null) {
				dcpsChangesId = Long.valueOf(StringUtility.getParameter("dcpsChangesId", request));

				lObjHstDcpsChanges = lObjChangesDAO.getChangesDetails(dcpsChangesId);

				HstDcpsOfficeChangesId = lObjChangesDAO.getOfficeChangesIdforChangesId(dcpsChangesId);
				lObjHstDcpsOfficeChanges = (HstDcpsOfficeChanges) lObjChangesDAO.read(HstDcpsOfficeChangesId);

				lStrDesignationDraft = StringUtility.getParameter("designationDraft", request);

			}

			MstEmpObj = lObjChangesDAO.getEmpDetails(lLngEmpID);
			lObjRltDcpsPayrollEmp = lObjChangesDAO.getEmpPayrollDetailsForEmpId(lLngEmpID);

			Date dobOfEmployee = lObjChangesDAO.getDobForTheEmployee(lLngEmpID);

			inputMap.put("lStrDesignationDraft", lStrDesignationDraft);
			inputMap.put("dcpsChangesId", dcpsChangesId);
			inputMap.put("lObjHstDcpsChanges", lObjHstDcpsChanges);
			inputMap.put("lObjHstDcpsOfficeChanges", lObjHstDcpsOfficeChanges);
			inputMap.put("lObjEmpData", MstEmpObj);
			inputMap.put("lObjRltDcpsPayrollEmp", lObjRltDcpsPayrollEmp);
			inputMap.put("empDOB", dobOfEmployee);

			// Putting master's data for displaying in upper part of JSP

			inputMap.put("parentDeptId", MstEmpObj.getParentDept());
			inputMap.put("parentDeptDesc",
					lObjDcpsCommonDAO.getLocNameforLocId(Long.valueOf(MstEmpObj.getParentDept())));
			inputMap.put("cadreId", MstEmpObj.getCadre());
			inputMap.put("cadreDesc", lObjDcpsCommonDAO.getCadreNameforCadreId(Long.valueOf(MstEmpObj.getCadre())));
			inputMap.put("designationId", MstEmpObj.getDesignation());
			inputMap.put("designationDesc",
					lObjDcpsCommonDAO.getDesigNameFromId(Long.valueOf(MstEmpObj.getDesignation())));
			inputMap.put("payCommissionId", MstEmpObj.getPayCommission());
			inputMap.put("payCommissionDesc",
					lObjDcpsCommonDAO.getCmnLookupNameFromId(Long.valueOf(MstEmpObj.getPayCommission())));
			inputMap.put("ddoOfficeIdMst", MstEmpObj.getCurrOff());
			inputMap.put("ddoOfficeDescMst",
					lObjDcpsCommonDAO.getDddoOfficeNameNameforId(Long.valueOf(MstEmpObj.getCurrOff())));

			// Get list of Pay commission from lookup master
			List listPayCommission = IFMSCommonServiceImpl.getLookupValues("PayCommissionDCPS",
					SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listPayCommission", listPayCommission);

			SimpleDateFormat lObjSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			DcpsCadreMst lObjMstCadre = null;

			DdoOffice lObjDdoOfficeVOMst = null;

			if (MstEmpObj != null) {

				if (MstEmpObj.getCurrOff() != null) {
					lLongDdoOfficeId = Long.valueOf(MstEmpObj.getCurrOff());
					lObjDdoOfficeVOMst = lObjNewRegDdoDAO.getDdoOfficeVO(lLongDdoOfficeId);
				}

				if (MstEmpObj.getCadre() != null && !MstEmpObj.getCadre().equalsIgnoreCase("")) {

					String lStrDobEmp = lObjSimpleDateFormat.format(MstEmpObj.getDob());
					String lStrWithoutYear = lStrDobEmp.substring(0, 6);

					lObjMstCadre = (DcpsCadreMst) lObjNewRegDdoDAOForCadre.read(Long.valueOf(MstEmpObj.getCadre()));
					String lStrGroupName = lObjDcpsCommonDAO
							.getCmnLookupNameFromId(Long.valueOf(lObjMstCadre.getGroupId().trim()));
					Long SuperAnnuationAgeMst = lObjMstCadre.getSuperAntunAge();
					Long lLongBirthYear = Long.valueOf(lStrDobEmp.substring(6));
					Long lLongRetiringYear = lLongBirthYear + SuperAnnuationAgeMst;
					String lStrRetiringYear = lStrWithoutYear + lLongRetiringYear.toString();

					inputMap.put("GroupNameMst", lStrGroupName.trim());
					inputMap.put("SuperAnnAgeMst", SuperAnnuationAgeMst);
					inputMap.put("SuperAnnDateMst", lStrRetiringYear);

					Long lLngFieldDept = Long.parseLong(MstEmpObj.getParentDept());
					Long.parseLong(MstEmpObj.getCadre());
					List lLstDesignation = lObjDcpsCommonDAO.getDesigsForPFDAndCadre(lLngFieldDept);
					inputMap.put("lLstDesignationMst", lLstDesignation);

					/*
					 * List lLstOfficesForPostMst = null ; if(lObjRltDcpsPayrollEmp.getPostId() !=
					 * null && lObjRltDcpsPayrollEmp.getPostId()!= -1) { lLstOfficesForPostMst =
					 * lObjDcpsCommonDAO.getOfficesForPost (lObjRltDcpsPayrollEmp.getPostId());
					 * inputMap.put("lLstOfficesForPostMst", lLstOfficesForPostMst);
					 * 
					 * Iterator it = lLstOfficesForPostMst.iterator(); while(it.hasNext()) {
					 * ComboValuesVO lObjComboValuesVO = (ComboValuesVO) it.next(); lLongDdoOfficeId
					 * =Long.valueOf(lObjComboValuesVO.getId()); if(lLongDdoOfficeId != -1)
					 * lObjDdoOfficeVO = lObjNewRegDdoDAO.getDdoOfficeVO(lLongDdoOfficeId); } }
					 * 
					 * }
					 */

					if (MstEmpObj.getPayCommission() != null && !MstEmpObj.getPayCommission().equalsIgnoreCase("")) {
						Map voToServiceMap = new HashMap();
						voToServiceMap.put("commissionId", MstEmpObj.getPayCommission());

						inputMap.put("voToServiceMap", voToServiceMap);

						resObj = serv.executeService("GetScalefromDesg", inputMap);

						List PayScaleListMst = (List) inputMap.get("PayScaleList");
						inputMap.put("PayScaleListMst", PayScaleListMst);

						String payScaleId = null;
						String payScaleDesc = null;

						for (Object lObj : PayScaleListMst) {
							ComboValuesVO lObjComboVo = (ComboValuesVO) lObj;
							if (lObjComboVo.getId().equals(MstEmpObj.getPayScale())) {
								payScaleId = lObjComboVo.getId();
								payScaleDesc = lObjComboVo.getDesc();
							}
						}

						// Putting master's data for displaying in upper part of
						// JSP
						inputMap.put("payScaleId", payScaleId);
						inputMap.put("payScaleDesc", payScaleDesc);

						List lListReasonsForSalaryChangeMst = null;
						Long lLongPaycommissionMst = Long.valueOf(MstEmpObj.getPayCommission());

						if (lLongPaycommissionMst == 700015l) {
							lListReasonsForSalaryChangeMst = IFMSCommonServiceImpl.getLookupValues(
									"ReasonsForSalaryChangeIn5PC", SessionHelper.getLangId(inputMap), inputMap);
						}
						if (lLongPaycommissionMst == 700016l) {
							lListReasonsForSalaryChangeMst = IFMSCommonServiceImpl.getLookupValues(
									"ReasonsForSalaryChangeIn6PC", SessionHelper.getLangId(inputMap), inputMap);
						}

						inputMap.put("lListReasonsForSalaryChangeMst", lListReasonsForSalaryChangeMst);

					}

					List listCadresMst = lObjDcpsCommonDAO.getCadreForDept(Long.valueOf(MstEmpObj.getParentDept()));
					inputMap.put("CADRELISTMST", listCadresMst);

				}

			}

			if (lObjHstDcpsOfficeChanges != null) {

				if (lObjHstDcpsOfficeChanges.getCurrOff() != null) {
					lLongDdoOfficeId = Long.valueOf(lObjHstDcpsOfficeChanges.getCurrOff());
					lObjDdoOfficeVO = lObjNewRegDdoDAO.getDdoOfficeVO(lLongDdoOfficeId);
					inputMap.put("ddoOfficeId", lObjHstDcpsOfficeChanges.getCurrOff());
					inputMap.put("ddoOfficeDesc", lObjDcpsCommonDAO
							.getDddoOfficeNameNameforId(Long.valueOf(lObjHstDcpsOfficeChanges.getCurrOff())));
				}

				if (lObjHstDcpsOfficeChanges.getCadre() != null) {

					MstEmp MstEmpForGettingDOB = (MstEmp) lObjNewRegDdoDAO
							.read(lObjHstDcpsOfficeChanges.getDcpsEmpId());

					lObjMstCadre = (DcpsCadreMst) lObjNewRegDdoDAOForCadre
							.read(Long.valueOf(lObjHstDcpsOfficeChanges.getCadre()));
					String lStrGroupName = lObjDcpsCommonDAO
							.getCmnLookupNameFromId(Long.valueOf(lObjMstCadre.getGroupId().trim()));
					Long SuperAnnuationAge = lObjMstCadre.getSuperAntunAge();

					String lStrDobEmp = lObjSimpleDateFormat.format(MstEmpForGettingDOB.getDob());
					String lStrWithoutYear = lStrDobEmp.substring(0, 6);
					Long lLongBirthYear = Long.valueOf(lStrDobEmp.substring(6));
					Long lLongRetiringYear = lLongBirthYear + SuperAnnuationAge;
					String lStrRetiringYear = lStrWithoutYear + lLongRetiringYear.toString();

					inputMap.put("GroupName", lStrGroupName.trim());
					inputMap.put("SuperAnnAge", SuperAnnuationAge);
					inputMap.put("SuperAnnDate", lStrRetiringYear);

					// Long lLngFieldDept =
					// Long.parseLong(lObjHstDcpsOfficeChanges.getParentDept());
					// Long lLngCadreType =
					// Long.parseLong(lObjHstDcpsOfficeChanges.getCadre());
					// List lLstDesignation =
					// lObjDcpsCommonDAO.getDesigsForPFDAndCadre(lLngFieldDept);
					// inputMap.put("lLstDesignation", lLstDesignation);

					List lListReasonsForSalaryChange = null;
					Long lLongPaycommission = null;
					if (lObjHstDcpsOfficeChanges.getPayCommission() != null) {
						lLongPaycommission = Long.valueOf(lObjHstDcpsOfficeChanges.getPayCommission());
					} else {
						lLongPaycommission = Long.valueOf(MstEmpObj.getPayCommission());
					}

					if (lLongPaycommission == 700015l) {
						lListReasonsForSalaryChange = IFMSCommonServiceImpl.getLookupValues(
								"ReasonsForSalaryChangeIn5PC", SessionHelper.getLangId(inputMap), inputMap);
					}
					if (lLongPaycommission == 700016l) {
						lListReasonsForSalaryChange = IFMSCommonServiceImpl.getLookupValues(
								"ReasonsForSalaryChangeIn6PC", SessionHelper.getLangId(inputMap), inputMap);
					}

					inputMap.put("lListReasonsForSalaryChange", lListReasonsForSalaryChange);

				}

				List PayScaleList = null;

				if (lObjHstDcpsOfficeChanges.getPayCommission() != null
						&& !lObjHstDcpsOfficeChanges.getPayCommission().equalsIgnoreCase("")) {
					Map voToServiceMap = new HashMap();
					voToServiceMap.put("commissionId", lObjHstDcpsOfficeChanges.getPayCommission());
					inputMap.put("voToServiceMap", voToServiceMap);
					resObj = serv.executeService("GetScalefromDesg", inputMap);
					PayScaleList = (List) inputMap.get("PayScaleList");
					inputMap.put("PayScaleList", PayScaleList);
				} else {
					Map voToServiceMap = new HashMap();
					voToServiceMap.put("commissionId", MstEmpObj.getPayCommission());
					inputMap.put("voToServiceMap", voToServiceMap);
					resObj = serv.executeService("GetScalefromDesg", inputMap);
					PayScaleList = (List) inputMap.get("PayScaleList");
					inputMap.put("PayScaleList", PayScaleList);
				}

				List lLstOfficesForPost = null;
				List listCadres = null;

				if (lObjHstDcpsOfficeChanges.getParentDept() != null) {
					listCadres = lObjDcpsCommonDAO
							.getCadreForDept(Long.valueOf(lObjHstDcpsOfficeChanges.getParentDept()));
				} else {
					listCadres = lObjDcpsCommonDAO.getCadreForDept(Long.valueOf(MstEmpObj.getParentDept()));
				}

				inputMap.put("CADRELIST", listCadres);

				if (lObjHstDcpsOfficeChanges.getPostId() != null && lObjHstDcpsOfficeChanges.getPostId() != -1) {
					lLstOfficesForPost = lObjDcpsCommonDAO.getOfficesForPost(lObjHstDcpsOfficeChanges.getPostId());
				} else {
					lLstOfficesForPost = lObjDcpsCommonDAO.getOfficesForPost(lObjRltDcpsPayrollEmp.getPostId());
				}
				inputMap.put("lLstOfficesForPost", lLstOfficesForPost);

			}

			List lLstAllDesignations = lObjDdoProfileDAO.getAllDesignation(gLngLangId);
			inputMap.put("lLstAllDesignations", lLstAllDesignations);

			inputMap.put("lObjDdoOfficeVOMst", lObjDdoOfficeVOMst);
			inputMap.put("lObjDdoOfficeVO", lObjDdoOfficeVO);

			String lStrUserType = StringUtility.getParameter("UserType", request);
			inputMap.put("UserType", lStrUserType);

			if (lStrUserType.equals("DDOAsst")) {
				inputMap.put("EditForm", "Y");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				List UserList = getHierarchyUsers(inputMap, lStrUserType);
				inputMap.put("UserList", UserList);
				inputMap.put("ForwardToPost", UserList.get(UserList.size() - 1));
			} else if (lStrUserType.equals("DDO")) {
				inputMap.put("EditForm", "N");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			inputMap.put("DDOCODE", lStrDdoCode);

			// Get the office list from the database

			List listOfficeNames = lObjDcpsCommonDAO.getCurrentOffices(lStrDdoCode);
			inputMap.put("OFFICELIST", listOfficeNames);

			// Get Designations
			List lLstParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);
			List lLstDesignation = lObjDcpsCommonDAO.getAllDesignation((Long) objParentDept[0], gLngLangId);
			inputMap.put("lLstDesignation", lLstDesignation);

			List listParentDept = lObjDcpsCommonDAO.getAllHODDepartment(
					Long.parseLong(gObjRsrcBndle.getString("DCPS.CurrentFieldDeptID")), gLngLangId);
			inputMap.put("listParentDept", listParentDept);

			// Set the date of joining limit as 01-Jan-2005
			inputMap.put("lDtJoiDtLimit", "01/01/2005");

			String lStrDesignation = StringUtility.getParameter("designationId", request);
			inputMap.put("lStrDesignation", lStrDesignation);

			String lStrChangesType = StringUtility.getParameter("changesType", request);
			inputMap.put("lStrChangesType", lStrChangesType);

			resObj.setResultValue(inputMap);
			resObj.setViewName("ChangeOfficeDetails");

		} catch (Exception e) {
			// e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject changesOtherDetails(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Long dcpsChangesId = null;
		HstDcpsChanges lObjHstDcpsChanges = null;
		HstDcpsOtherChanges lObjHstDcpsOtherChanges = null;
		Long HstDcpsOtherChangesId = null;
		String lStrDesignationDraft = null;
		MstEmp MstEmpObj = null;
		RltDcpsPayrollEmp RltDcpsPayrollEmpObj = null;
		String lStrDdoCode = null;

		try {

			setSessionInfo(inputMap);

			ChangesFormDAO lObjChangesDAO = new ChangesFormDAOImpl(HstDcpsOtherChanges.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			NewRegDdoDAO lObjNewRegPayrollDdoDAO = new NewRegDdoDAOImpl(RltDcpsPayrollEmp.class,
					serv.getSessionFactory());
			NewRegDdoDAO lObjNewRegDdoDAOForCadre = new NewRegDdoDAOImpl(DcpsCadreMst.class, serv.getSessionFactory());

			// Get List of current Data
			Long lLngEmpID = Long.parseLong(StringUtility.getParameter("EmpId", request));
			if (!StringUtility.getParameter("dcpsChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsChangesId", request) != null) {
				dcpsChangesId = Long.valueOf(StringUtility.getParameter("dcpsChangesId", request));

				lObjHstDcpsChanges = lObjChangesDAO.getChangesDetails(dcpsChangesId);

				HstDcpsOtherChangesId = lObjChangesDAO.getOtherChangesIdforChangesId(dcpsChangesId);
				lObjHstDcpsOtherChanges = (HstDcpsOtherChanges) lObjChangesDAO.read(HstDcpsOtherChangesId);

				lStrDesignationDraft = StringUtility.getParameter("designationDraft", request);

			}

			MstEmpObj = lObjChangesDAO.getEmpDetails(lLngEmpID);

			RltDcpsPayrollEmpObj = lObjNewRegPayrollDdoDAO.getPayrollVOForEmpId(MstEmpObj.getDcpsEmpId());
			inputMap.put("lStrDesignationDraft", lStrDesignationDraft);
			inputMap.put("dcpsChangesId", dcpsChangesId);
			inputMap.put("lObjHstDcpsChanges", lObjHstDcpsChanges);
			inputMap.put("lObjHstDcpsOtherChanges", lObjHstDcpsOtherChanges);
			inputMap.put("lObjEmpData", MstEmpObj);
			inputMap.put("dcpsOrGpf", MstEmpObj.getDcpsOrGpf().toString());

			inputMap.put("lObjRltDcpsPayrollEmp", RltDcpsPayrollEmpObj);

			// Added for displaying group
			DcpsCadreMst lObjMstCadre = null;
			lObjMstCadre = (DcpsCadreMst) lObjNewRegDdoDAOForCadre.read(Long.valueOf(MstEmpObj.getCadre()));
			inputMap.put("SuperAnnAge", lObjMstCadre.getSuperAntunAge());

			String lStrGroupName = lObjDcpsCommonDAO
					.getCmnLookupNameFromId(Long.valueOf(lObjMstCadre.getGroupId().trim()));
			inputMap.put("GroupName", lStrGroupName.trim());
			// inputMap.put("GroupName", lObjMstCadre.getGroupId());

			String lStrUserType = StringUtility.getParameter("UserType", request);
			inputMap.put("UserType", lStrUserType);
			if (lStrUserType.equals("DDOAsst")) {
				inputMap.put("EditForm", "Y");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				List UserList = getHierarchyUsers(inputMap, lStrUserType);
				inputMap.put("UserList", UserList);
				inputMap.put("ForwardToPost", UserList.get(UserList.size() - 1));
			} else if (lStrUserType.equals("DDO")) {
				inputMap.put("EditForm", "N");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			inputMap.put("DDOCODE", lStrDdoCode);

			// Get the Bank Names

			List lLstBankNames = lObjDcpsCommonDAO.getBankNames();
			inputMap.put("BANKNAMES", lLstBankNames);

			// Get the BankBranchNames
			if (MstEmpObj != null) {
				if (MstEmpObj.getBankName() != null) {

					List lLstBrachNames = lObjDcpsCommonDAO.getBranchNames(Long.valueOf(MstEmpObj.getBankName()));

					inputMap.put("BRANCHNAMESMST", lLstBrachNames);
				}
			}

			if (lObjHstDcpsOtherChanges != null) {
				if (lObjHstDcpsOtherChanges.getBankName() != null) {
					List lLstBrachNames = lObjDcpsCommonDAO
							.getBranchNames(Long.valueOf(lObjHstDcpsOtherChanges.getBankName()));
					inputMap.put("BRANCHNAMES", lLstBrachNames);
				}
			}

			// Get AcDcpsMaintainedBy from Lookup Master
			List lLstPFAccntMntdByDCPS = IFMSCommonServiceImpl.getLookupValues("AccountMaintainedByForDCPSEmp",
					SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("lLstPFAccntMntdByDCPS", lLstPFAccntMntdByDCPS);

			// Get PF Account Maintained from lookup Master
			List lLstPFAccntMntdBy = IFMSCommonServiceImpl.getLookupValues("AccountMaintaindedBy",
					SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("lLstPFAccntMntdBy", lLstPFAccntMntdBy);

			// Get PF Series from lookup Master for Above half in Changes Form
			List lLstPFSeriesMst = null;
			String lStrAcMntndByMst = RltDcpsPayrollEmpObj.getAcMaintainedBy();
			String MumbaiOrNagpurAGMst = null;
			if (lStrAcMntndByMst != null && !lStrAcMntndByMst.equals("")) {
				if (lStrAcMntndByMst.equals("700092")) {
					lLstPFSeriesMst = IFMSCommonServiceImpl.getLookupValues("PF_Series",
							SessionHelper.getLangId(inputMap), inputMap);
					MumbaiOrNagpurAGMst = "Yes";
				} // PF Series for AG Mumbai
				else if (lStrAcMntndByMst.equals("700093")) {
					lLstPFSeriesMst = IFMSCommonServiceImpl.getLookupValues("PF_Series_AG_Nagpur",
							SessionHelper.getLangId(inputMap), inputMap);
					MumbaiOrNagpurAGMst = "Yes";
				} // PF Series for AG Nagpur
				else {
					MumbaiOrNagpurAGMst = "No";
				}
			}
			inputMap.put("lLstPFSeriesMst", lLstPFSeriesMst);
			inputMap.put("MumbaiOrNagpurAGMst", MumbaiOrNagpurAGMst);

			// Get PF Series from lookup Master for below half in Changes Form
			List lLstPFSeries = null;
			String MumbaiOrNagpurAG = null;
			if (lObjHstDcpsOtherChanges != null) {
				if (lObjHstDcpsOtherChanges.getAcMaintainedBy() != null) {
					String lStrAcMntndBy = lObjHstDcpsOtherChanges.getAcMaintainedBy();

					if (lStrAcMntndBy != null && !lStrAcMntndBy.equals("")) {
						if (lStrAcMntndBy.equals("700092")) {
							lLstPFSeries = IFMSCommonServiceImpl.getLookupValues("PF_Series",
									SessionHelper.getLangId(inputMap), inputMap);
							MumbaiOrNagpurAG = "Yes";
						} // PF Series for AG Mumbai
						else if (lStrAcMntndBy.equals("700093")) {
							lLstPFSeries = IFMSCommonServiceImpl.getLookupValues("PF_Series_AG_Nagpur",
									SessionHelper.getLangId(inputMap), inputMap);
							MumbaiOrNagpurAG = "Yes";
						} // PF Series for AG Nagpur
						else {
							MumbaiOrNagpurAG = "No";
						}
					}
				}
			} else {
				MumbaiOrNagpurAG = MumbaiOrNagpurAGMst;
				lLstPFSeries = lLstPFSeriesMst;
			}

			inputMap.put("lLstPFSeries", lLstPFSeries);
			inputMap.put("MumbaiOrNagpurAG", MumbaiOrNagpurAG);

			String lStrDesignation = StringUtility.getParameter("designationId", request);
			inputMap.put("lStrDesignation", lStrDesignation);

			String lStrChangesType = StringUtility.getParameter("changesType", request);
			inputMap.put("lStrChangesType", lStrChangesType);

			resObj.setResultValue(inputMap);
			resObj.setViewName("ChangeOtherDetails");

		} catch (Exception e) {
			// e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject changesNomineeDetails(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Long dcpsChangesId = null;
		HstDcpsChanges lObjHstDcpsChanges = null;
		String lStrDesignationDraft = null;
		MstEmp MstEmpObj = null;
		List<MstEmpNmn> NomineesList = null;
		List<HstDcpsNomineeChanges> NomineesHstList = null;
		Integer lIntTotalNominees = null;
		String lStrDdoCode = null;

		try {
			setSessionInfo(inputMap);

			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			ChangesFormDAO lObjChangesDAO = new ChangesFormDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			// Get List of current Data
			Long lLngEmpID = Long.parseLong(StringUtility.getParameter("EmpId", request));

			// Added

			if (!StringUtility.getParameter("dcpsChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsChangesId", request) != null) {
				dcpsChangesId = Long.valueOf(StringUtility.getParameter("dcpsChangesId", request));

				Long latestRefIdForNomineeChanges = lObjChangesDAO.getLatestRefIdForNomineeChanges(lLngEmpID,
						dcpsChangesId);
				NomineesHstList = lObjChangesDAO.getNomineesFromHst(latestRefIdForNomineeChanges, lLngEmpID);

				lIntTotalNominees = NomineesHstList.size();

				lObjHstDcpsChanges = lObjChangesDAO.getChangesDetails(dcpsChangesId);

				lStrDesignationDraft = StringUtility.getParameter("designationDraft", request);

			}

			MstEmpObj = lObjChangesDAO.getEmpDetails(lLngEmpID);
			NomineesList = lObjChangesDAO.getNominees(lLngEmpID.toString());
			lIntTotalNominees = NomineesList.size();

			inputMap.put("NomineesHstList", NomineesHstList);
			inputMap.put("NomineesList", NomineesList);
			inputMap.put("lStrDesignationDraft", lStrDesignationDraft);
			inputMap.put("dcpsChangesId", dcpsChangesId);
			inputMap.put("lObjHstDcpsChanges", lObjHstDcpsChanges);
			inputMap.put("lObjEmpData", MstEmpObj);
			inputMap.put("EmployeeID", lLngEmpID);
			inputMap.put("lIntTotalNominees", lIntTotalNominees);

			List listRelationship = IFMSCommonServiceImpl.getLookupValues("Relationship",
					SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listRelationship", listRelationship);

			Date lDtcurDate = SessionHelper.getCurDate();
			inputMap.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));

			String lStrUserType = StringUtility.getParameter("UserType", request);
			inputMap.put("UserType", lStrUserType);

			if (lStrUserType.equals("DDOAsst")) {
				inputMap.put("EditForm", "Y");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				List UserList = getHierarchyUsers(inputMap, lStrUserType);
				inputMap.put("UserList", UserList);
				inputMap.put("ForwardToPost", UserList.get(UserList.size() - 1));
			} else if (lStrUserType.equals("DDO")) {
				inputMap.put("EditForm", "N");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			inputMap.put("DDOCODE", lStrDdoCode);

			String lStrDesignation = StringUtility.getParameter("designationId", request);
			inputMap.put("lStrDesignation", lStrDesignation);

			String lStrChangesType = StringUtility.getParameter("changesType", request);
			inputMap.put("lStrChangesType", lStrChangesType);

			resObj.setResultValue(inputMap);
			resObj.setViewName("ChangeNomineeDetails");

		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject changesPhotoAndSignatureDetails(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try {
			resObj.setResultValue(inputMap);
			setSessionInfo(inputMap);
			List lLngNewPhotoSignList = null;
			Long lLngNewPhotoAttachmentId = null;
			Long lLngNewSignAttachmentId = null;
			Long lLngPhotoAttachmentId = null;
			Long lLngSignAttachmentId = null;
			Long dcpsChangesId = null;
			MstEmp MstEmpObj = null;
			String lStrDesignationDraft = null;
			HstDcpsChanges lObjHstDcpsChanges = null;
			ChangesFormDAO lObjChangesDAO = new ChangesFormDAOImpl(TrnDcpsChanges.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			Long srNo = 0L;
			// Get List of current Data
			Long lLngEmpID = Long.parseLong(StringUtility.getParameter("EmpId", request));
			if (!StringUtility.getParameter("dcpsChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsChangesId", request) != null) {
				dcpsChangesId = Long.valueOf(StringUtility.getParameter("dcpsChangesId", request));

				lObjHstDcpsChanges = lObjChangesDAO.getChangesDetails(dcpsChangesId);

				StringUtility.getParameter("designationDraft", request);

				lLngNewPhotoSignList = lObjChangesDAO.getPhotoSignNewValue(dcpsChangesId);

				if (!lLngNewPhotoSignList.get(0).toString().equals("")) {
					lLngNewPhotoAttachmentId = Long.parseLong(lLngNewPhotoSignList.get(0).toString());
				} else {
					lLngNewPhotoAttachmentId = Long.parseLong("1");
				}

				if (!lLngNewPhotoSignList.get(1).toString().equals("")) {
					lLngNewPhotoAttachmentId = Long.parseLong(lLngNewPhotoSignList.get(1).toString());
				} else {
					lLngNewPhotoAttachmentId = Long.parseLong("1");
				}

				/* Code to display new Photo */
				CmnAttachmentMstDAO lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,
						serv.getSessionFactory());
				CmnAttachmentMst lObjCmnAttachmentMst = new CmnAttachmentMst();

				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
						.findByAttachmentId(Long.parseLong(lLngNewPhotoAttachmentId.toString()));

				Set<CmnAttachmentMpg> cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();
				srNo = 0L;
				CmnAttachmentMpg cmnAttachmentMpg = null;
				Iterator<CmnAttachmentMpg> cmnAttachmentMpgIterator = null;
				if (cmnAttachmentMpgs != null) {
					cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();

					cmnAttachmentMpg = new CmnAttachmentMpg();

					cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();

					for (int j = 0; j < cmnAttachmentMpgs.size(); j++) {
						cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
						if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("photo")) {
							srNo = cmnAttachmentMpg.getSrNo();

						}
					}
				}

				inputMap.put("Photo", lObjCmnAttachmentMst);
				inputMap.put("PhotoId", lLngNewPhotoAttachmentId);
				inputMap.put("PhotosrNo", srNo);

				inputMap.put("lObjHstDcpsChanges", lObjHstDcpsChanges);
				inputMap.put("dcpsChangesId", dcpsChangesId);

				lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
				lObjCmnAttachmentMst = new CmnAttachmentMst();
				if (lLngNewSignAttachmentId != null) {
					lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
							.findByAttachmentId(Long.parseLong(lLngNewSignAttachmentId.toString()));
				}
				cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();
				cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();

				cmnAttachmentMpg = new CmnAttachmentMpg();

				cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();

				for (int j = 0; j < cmnAttachmentMpgs.size(); j++) {
					cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
					if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("signature")) {
						srNo = cmnAttachmentMpg.getSrNo();

					}
				}

				inputMap.put("Sign", lObjCmnAttachmentMst);
				inputMap.put("SignId", lLngNewSignAttachmentId);
				inputMap.put("SignsrNo", srNo);
				/* Code to add new photo ends */

				lStrDesignationDraft = StringUtility.getParameter("designationDraft", request);

			}

			/* Code to add old photo */
			MstEmpObj = lObjChangesDAO.getEmpDetails(lLngEmpID);
			CmnAttachmentMstDAO lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,
					serv.getSessionFactory());
			CmnAttachmentMst lObjCmnAttachmentMst = new CmnAttachmentMst();
			Set<CmnAttachmentMpg> cmnAttachmentMpgs = null;
			CmnAttachmentMpg cmnAttachmentMpg = null;
			Iterator<CmnAttachmentMpg> cmnAttachmentMpgIterator = null;

			lLngPhotoAttachmentId = MstEmpObj.getPhotoAttachmentID();

			if (lLngPhotoAttachmentId != null) {
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
						.findByAttachmentId(Long.parseLong(lLngPhotoAttachmentId.toString()));

				cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();
				cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();

				cmnAttachmentMpg = new CmnAttachmentMpg();

				cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
				srNo = 0L;
				for (int j = 0; j < cmnAttachmentMpgs.size(); j++) {
					cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
					if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("photo")) {
						srNo = cmnAttachmentMpg.getSrNo();

					}
				}
			}

			inputMap.put("PhotoId1", lLngPhotoAttachmentId);
			inputMap.put("PhotosrNo1", srNo);

			lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
			lObjCmnAttachmentMst = new CmnAttachmentMst();

			lLngSignAttachmentId = MstEmpObj.getSignatureAttachmentID();

			if (lLngSignAttachmentId != null) {
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
						.findByAttachmentId(Long.parseLong(lLngSignAttachmentId.toString()));

				cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();
				cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();

				cmnAttachmentMpg = new CmnAttachmentMpg();

				cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();

				for (int j = 0; j < cmnAttachmentMpgs.size(); j++) {
					cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
					if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("signature")) {
						srNo = cmnAttachmentMpg.getSrNo();

					}
				}

			}

			inputMap.put("SignId1", lLngSignAttachmentId);
			inputMap.put("SignsrNo1", srNo);
			/* Code to add old photo ends */
			inputMap.put("lStrDesignationDraft", lStrDesignationDraft);
			String lStrUserType = StringUtility.getParameter("UserType", request);
			inputMap.put("UserType", lStrUserType);
			String lStrDesignation = StringUtility.getParameter("designationId", request);
			inputMap.put("lStrDesignation", lStrDesignation);

			String lStrChangesType = StringUtility.getParameter("changesType", request);
			inputMap.put("lStrChangesType", lStrChangesType);

			inputMap.put("dcpsEmpId", lLngEmpID);

			String lStrDdoCode = null;
			if (lStrUserType.equals("DDOAsst")) {
				inputMap.put("EditForm", "Y");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				List UserList = getHierarchyUsers(inputMap, lStrUserType);
				inputMap.put("UserList", UserList);
				inputMap.put("ForwardToPost", UserList.get(UserList.size() - 1));
			} else if (lStrUserType.equals("DDO")) {
				inputMap.put("EditForm", "N");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			inputMap.put("DDOCODE", lStrDdoCode);

			resObj.setResultValue(inputMap);
			resObj.setViewName("ChangePhotoAndSignatureDetails");

		} catch (Exception e) {
			// e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject updatePersonalDtls(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		HstDcpsChanges lObjChangesData = null;
		Long lLongEmployeeId = null;

		Long lLongDcpsHstChangesId = null;
		Long lLongChangesId = null;
		Long lLongPersonalChangesId = null;

		try {

			setSessionInfo(inputMap);

			ChangesFormDAO lObjChangesFormDAO = new ChangesFormDAOImpl(HstDcpsChanges.class, serv.getSessionFactory());

			String lStrEmployeeId = StringUtility.getParameter("empId", request);
			lLongEmployeeId = Long.valueOf(lStrEmployeeId);

			if (!StringUtility.getParameter("dcpsHstChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsHstChangesId", request) != null) {
				lLongDcpsHstChangesId = Long.valueOf(StringUtility.getParameter("dcpsHstChangesId", request));

				inputMap.put("dcpsHstChangesId", lLongDcpsHstChangesId);
				gLogger.error(" dcpsHstChangesId-------- : " + lLongDcpsHstChangesId);
			}

			lObjChangesData = (HstDcpsChanges) inputMap.get("lObjChangesData");

			List<TrnDcpsChanges> lObjTrnDcpsChangesList = (List<TrnDcpsChanges>) inputMap.get("lObjTrnDcpsChangesList");

			if (lLongDcpsHstChangesId == null) {
				lLongChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_CHANGES", inputMap);

				lObjChangesData.setDcpsChangesId(lLongChangesId);
				lObjChangesData.setDcpsEmpId(lLongEmployeeId);
				lObjChangesData.setTypeOfChanges("PersonalDetails");
				lObjChangesData.setFormStatus(0L);
				lObjChangesFormDAO.create(lObjChangesData);

				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {
					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLongEmployeeId);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("PersonalDetails");
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));
				}
			}
			if (lLongDcpsHstChangesId != null) {

				lObjChangesData.setDcpsChangesId(lLongDcpsHstChangesId);
				lObjChangesData.setDcpsEmpId(lLongEmployeeId);
				lObjChangesData.setTypeOfChanges("PersonalDetails");
				lObjChangesData.setFormStatus(0L);
				lObjChangesFormDAO.update(lObjChangesData);

				lObjChangesFormDAO.deleteTrnVOForDcpsChangesId(lLongDcpsHstChangesId);

				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {

					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);

					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongDcpsHstChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLongEmployeeId);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("PersonalDetails");
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));

				}
			}

			HstDcpsPersonalChanges lObjHstDcpsPersonalChanges = (HstDcpsPersonalChanges) inputMap
					.get("lObjHstDcpsPersonalChanges");

			if (lLongDcpsHstChangesId == null) {
				lLongPersonalChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_PERSONAL_CHANGES", inputMap);
				lObjHstDcpsPersonalChanges.setDcpsEmpId(lLongEmployeeId);
				lObjHstDcpsPersonalChanges.setDcpsPersonalChangesId(lLongPersonalChangesId);
				lObjHstDcpsPersonalChanges.setDcpsChangesId(lLongChangesId);
				lObjChangesFormDAO.create(lObjHstDcpsPersonalChanges);
			}
			if (lLongDcpsHstChangesId != null) {
				lLongPersonalChangesId = lObjChangesFormDAO.getPersonalChangesIdforChangesId(lLongDcpsHstChangesId);
				lObjHstDcpsPersonalChanges.setDcpsEmpId(lLongEmployeeId);
				lObjHstDcpsPersonalChanges.setDcpsPersonalChangesId(lLongPersonalChangesId);
				lObjHstDcpsPersonalChanges.setDcpsChangesId(lLongDcpsHstChangesId);
				lObjChangesFormDAO.update(lObjHstDcpsPersonalChanges);
			}

			lBlFlag = true;

		} catch (Exception ex) {

			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			// ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);

		}

		String lSBStatus = getResponseXMLDocUpdtOrFwd(lBlFlag, lObjChangesData.getDcpsChangesId()).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;

	}

	public ResultObject updateOfficeDtls(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		HstDcpsChanges lObjChangesData = null;
		Boolean lBlFlag = false;
		Long lLongDcpsHstChangesId = null;
		Long lLongChangesId = null;
		Long lLongOfficeChangesId = null;

		try {

			setSessionInfo(inputMap);
			ChangesFormDAO lObjChangesFormDAO = new ChangesFormDAOImpl(HstDcpsChanges.class, serv.getSessionFactory());

			String lStrEmployeeId = StringUtility.getParameter("empId", request);
			Long lLongEmployeeId = Long.valueOf(lStrEmployeeId);

			if (!StringUtility.getParameter("dcpsHstChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsHstChangesId", request) != null) {
				lLongDcpsHstChangesId = Long.valueOf(StringUtility.getParameter("dcpsHstChangesId", request));
			}

			lObjChangesData = (HstDcpsChanges) inputMap.get("lObjChangesData");

			List<TrnDcpsChanges> lObjTrnDcpsChangesList = (List<TrnDcpsChanges>) inputMap.get("lObjTrnDcpsChangesList");

			if (lLongDcpsHstChangesId == null) {
				lLongChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_CHANGES", inputMap);

				lObjChangesData.setDcpsChangesId(lLongChangesId);
				lObjChangesData.setDcpsEmpId(lLongEmployeeId);
				lObjChangesData.setTypeOfChanges("OfficeDetails");
				lObjChangesFormDAO.create(lObjChangesData);

				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {
					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLongEmployeeId);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("OfficeDetails");
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));
				}
			}
			if (lLongDcpsHstChangesId != null) {
				lObjChangesData.setDcpsChangesId(lLongDcpsHstChangesId);
				lObjChangesData.setDcpsEmpId(lLongEmployeeId);
				lObjChangesData.setTypeOfChanges("OfficeDetails");
				lObjChangesFormDAO.update(lObjChangesData);

				lObjChangesFormDAO.deleteTrnVOForDcpsChangesId(lLongDcpsHstChangesId);

				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {

					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);

					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongDcpsHstChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLongEmployeeId);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("OfficeDetails");
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));

				}
			}

			HstDcpsOfficeChanges lObjHstDcpsOfficeChanges = (HstDcpsOfficeChanges) inputMap
					.get("lObjHstDcpsOfficeChanges");

			if (lLongDcpsHstChangesId == null) {
				lLongOfficeChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_OFFICE_CHANGES", inputMap);
				lObjHstDcpsOfficeChanges.setDcpsEmpId(lLongEmployeeId);
				lObjHstDcpsOfficeChanges.setDcpsOfficeChangesId(lLongOfficeChangesId);
				lObjHstDcpsOfficeChanges.setDcpsChangesId(lLongChangesId);
				lObjChangesFormDAO.create(lObjHstDcpsOfficeChanges);

			}
			if (lLongDcpsHstChangesId != null) {
				lLongOfficeChangesId = lObjChangesFormDAO.getOfficeChangesIdforChangesId(lLongDcpsHstChangesId);
				lObjHstDcpsOfficeChanges.setDcpsEmpId(lLongEmployeeId);
				lObjHstDcpsOfficeChanges.setDcpsOfficeChangesId(lLongOfficeChangesId);
				lObjHstDcpsOfficeChanges.setDcpsChangesId(lLongDcpsHstChangesId);
				lObjChangesFormDAO.update(lObjHstDcpsOfficeChanges);
			}

			lBlFlag = true;

		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			// ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
		}

		String lSBStatus = getResponseXMLDocUpdtOrFwd(lBlFlag, lObjChangesData.getDcpsChangesId()).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;

	}

	public ResultObject updateOtherDtls(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		HstDcpsChanges lObjChangesData = null;

		try {
			setSessionInfo(inputMap);

			ChangesFormDAO lObjChangesFormDAO = new ChangesFormDAOImpl(HstDcpsChanges.class, serv.getSessionFactory());

			String lStrEmployeeId = StringUtility.getParameter("empId", request);
			Long lLongEmployeeId = Long.valueOf(lStrEmployeeId);

			lObjChangesData = (HstDcpsChanges) inputMap.get("lObjChangesData");

			Long lLongDcpsHstChangesId = null;
			Long lLongChangesId = null;
			Long lLongOtherChangesId = null;

			if (!StringUtility.getParameter("dcpsHstChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsHstChangesId", request) != null) {
				lLongDcpsHstChangesId = Long.valueOf(StringUtility.getParameter("dcpsHstChangesId", request));
			}

			List<TrnDcpsChanges> lObjTrnDcpsChangesList = (List<TrnDcpsChanges>) inputMap.get("lObjTrnDcpsChangesList");

			if (lLongDcpsHstChangesId == null) {
				lLongChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_CHANGES", inputMap);

				lObjChangesData.setDcpsChangesId(lLongChangesId);
				lObjChangesData.setDcpsEmpId(lLongEmployeeId);
				lObjChangesData.setTypeOfChanges("OtherDetails");
				lObjChangesFormDAO.create(lObjChangesData);
				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {
					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLongEmployeeId);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("OtherDetails");
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));
				}
			}

			if (lLongDcpsHstChangesId != null) {
				lObjChangesData.setDcpsChangesId(lLongDcpsHstChangesId);
				lObjChangesData.setDcpsEmpId(lLongEmployeeId);
				lObjChangesData.setTypeOfChanges("OtherDetails");
				lObjChangesFormDAO.update(lObjChangesData);

				lObjChangesFormDAO.deleteTrnVOForDcpsChangesId(lLongDcpsHstChangesId);

				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {

					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);

					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongDcpsHstChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLongEmployeeId);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("OtherDetails");
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));

				}
			}

			HstDcpsOtherChanges lObjHstDcpsOtherChanges = (HstDcpsOtherChanges) inputMap.get("lObjHstDcpsOtherChanges");

			if (lLongDcpsHstChangesId == null) {
				lLongOtherChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_OTHER_CHANGES", inputMap);
				lObjHstDcpsOtherChanges.setDcpsEmpId(lLongEmployeeId);
				lObjHstDcpsOtherChanges.setDcpsOtherChangesId(lLongOtherChangesId);
				lObjHstDcpsOtherChanges.setDcpsChangesId(lLongChangesId);
				lObjChangesFormDAO.create(lObjHstDcpsOtherChanges);

			}
			if (lLongDcpsHstChangesId != null) {
				lLongOtherChangesId = lObjChangesFormDAO.getOtherChangesIdforChangesId(lLongDcpsHstChangesId);
				lObjHstDcpsOtherChanges.setDcpsEmpId(lLongEmployeeId);
				lObjHstDcpsOtherChanges.setDcpsOtherChangesId(lLongOtherChangesId);
				lObjHstDcpsOtherChanges.setDcpsChangesId(lLongDcpsHstChangesId);
				lObjChangesFormDAO.update(lObjHstDcpsOtherChanges);
			}

			lBlFlag = true;

		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			// ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
		}

		String lSBStatus = getResponseXMLDocUpdtOrFwd(lBlFlag, lObjChangesData.getDcpsChangesId()).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;

	}

	public ResultObject updateNomineeDtls(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		HstDcpsChanges lObjChangesData = null;

		Long lLongDcpsHstChangesId = null;
		Long lLongChangesId = null;
		Long dcpsNomineeChangesId = null;
		Long lLongChangesNomineeRefId = null;
		Long lLongChangesNomineeRefIdForChanges = null;

		try {

			setSessionInfo(inputMap);
			ChangesFormDAO lObjChangesFormDAO = new ChangesFormDAOImpl(HstDcpsChanges.class, serv.getSessionFactory());

			String lStrEmpId = StringUtility.getParameter("empId", request);
			Long lLngEmpID = Long.parseLong(lStrEmpId);

			lObjChangesData = (HstDcpsChanges) inputMap.get("lObjChangesData");

			if (!StringUtility.getParameter("dcpsHstChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsHstChangesId", request) != null) {
				lLongDcpsHstChangesId = Long.valueOf(StringUtility.getParameter("dcpsHstChangesId", request));
			}

			List<TrnDcpsChanges> lObjTrnDcpsChangesList = (List<TrnDcpsChanges>) inputMap.get("lObjTrnDcpsChangesList");

			lLongChangesNomineeRefId = lObjChangesFormDAO.getNextRefIdForHstNomineeChanges(lLngEmpID);
			lLongChangesNomineeRefIdForChanges = lLongChangesNomineeRefId + 1;

			if (lLongDcpsHstChangesId == null) {
				lLongChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_CHANGES", inputMap);
				lObjChangesData.setDcpsChangesId(lLongChangesId);
				lObjChangesData.setDcpsEmpId(lLngEmpID);
				lObjChangesData.setTypeOfChanges("NomineeDetails");
				lObjChangesFormDAO.create(lObjChangesData);

				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {
					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLngEmpID);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("NomineeDetails");
					lObjTrnDcpsChangesList.get(lInt).setFieldName("changesNomineeRefId");
					lObjTrnDcpsChangesList.get(lInt).setOldValue(lLongChangesNomineeRefId.toString());
					lObjTrnDcpsChangesList.get(lInt).setNewValue(lLongChangesNomineeRefIdForChanges.toString());
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));
				}
			}
			if (lLongDcpsHstChangesId != null) {
				lObjChangesData.setDcpsChangesId(lLongDcpsHstChangesId);
				lObjChangesData.setDcpsEmpId(lLngEmpID);
				lObjChangesData.setTypeOfChanges("NomineeDetails");
				lObjChangesFormDAO.update(lObjChangesData);

				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {
					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongDcpsHstChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLngEmpID);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("NomineeDetails");
					lObjTrnDcpsChangesList.get(lInt).setFieldName("changesNomineeRefId");
					Long HighestRefId = lObjChangesFormDAO.getNextRefIdForHstNomineeChanges(lLngEmpID) - 1;
					Long NextRefId = HighestRefId + 1;
					lObjTrnDcpsChangesList.get(lInt).setOldValue(HighestRefId.toString());
					lObjTrnDcpsChangesList.get(lInt).setNewValue(NextRefId.toString());
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));
				}
			}

			HstDcpsNomineeChanges[] lArrHstDcpsNomineeChanges = (HstDcpsNomineeChanges[]) inputMap
					.get("lArrHstDcpsNomineeChanges");

			HstDcpsNomineeChanges[] lArrDcpsNomineesFromMst = (HstDcpsNomineeChanges[]) inputMap
					.get("lArrDcpsNomineesFromMst");

			if (lLongDcpsHstChangesId == null) {

				for (Integer lInt = 0; lInt < lArrDcpsNomineesFromMst.length; lInt++) {

					dcpsNomineeChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_NOMINEE_CHANGES", inputMap);
					lArrDcpsNomineesFromMst[lInt].setDcpsNomineeChangesId(dcpsNomineeChangesId);
					lArrDcpsNomineesFromMst[lInt].setDcpsChangesId(lLongChangesId);
					lArrDcpsNomineesFromMst[lInt].setChangesNomineeRefId(lLongChangesNomineeRefId);
					lArrDcpsNomineesFromMst[lInt].setDcpsEmpId(lLngEmpID);
					lObjChangesFormDAO.create(lArrDcpsNomineesFromMst[lInt]);
				}

				for (Integer lInt = 0; lInt < lArrHstDcpsNomineeChanges.length; lInt++) {

					dcpsNomineeChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_NOMINEE_CHANGES", inputMap);
					lArrHstDcpsNomineeChanges[lInt].setDcpsNomineeChangesId(dcpsNomineeChangesId);
					lArrHstDcpsNomineeChanges[lInt].setDcpsChangesId(lLongChangesId);
					lArrHstDcpsNomineeChanges[lInt].setChangesNomineeRefId(lLongChangesNomineeRefIdForChanges);
					lArrHstDcpsNomineeChanges[lInt].setDcpsEmpId(lLngEmpID);
					lObjChangesFormDAO.create(lArrHstDcpsNomineeChanges[lInt]);
				}

			}
			if (lLongDcpsHstChangesId != null) {

				for (Integer lInt = 0; lInt < lArrHstDcpsNomineeChanges.length; lInt++) {

					dcpsNomineeChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_NOMINEE_CHANGES", inputMap);
					lArrHstDcpsNomineeChanges[lInt].setDcpsNomineeChangesId(dcpsNomineeChangesId);
					lArrHstDcpsNomineeChanges[lInt].setDcpsChangesId(lLongDcpsHstChangesId);
					lArrHstDcpsNomineeChanges[lInt].setChangesNomineeRefId(lLongChangesNomineeRefId);
					lArrHstDcpsNomineeChanges[lInt].setDcpsEmpId(lLngEmpID);
					lObjChangesFormDAO.create(lArrHstDcpsNomineeChanges[lInt]);
				}

			}

			lBlFlag = true;

		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			// ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
		}

		String lSBStatus = getResponseXMLDocUpdtOrFwd(lBlFlag, lObjChangesData.getDcpsChangesId()).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;

	}

	public ResultObject updatePhotoAndSignDtls(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		HstDcpsChanges lObjChangesData = null;

		Long lLongDcpsHstChangesId = null;
		Long lLongChangesId = null;
		Long lLongOldPhotoAttachmentId = null;
		Long lLongOldSignAttachmentId = null;
		Long lLongNewPhotoAttachmentId = null;
		Long lLongNewSignAttachmentId = null;

		try {
			setSessionInfo(inputMap);

			ChangesFormDAO lObjChangesFormDAO = new ChangesFormDAOImpl(HstDcpsChanges.class, serv.getSessionFactory());

			String lStrEmployeeId = StringUtility.getParameter("empId", request);
			Long lLongEmployeeId = Long.valueOf(lStrEmployeeId);

			lObjChangesData = (HstDcpsChanges) inputMap.get("lObjChangesData");

			if (!StringUtility.getParameter("dcpsHstChangesId", request).equalsIgnoreCase("")
					&& StringUtility.getParameter("dcpsHstChangesId", request) != null) {
				lLongDcpsHstChangesId = Long.valueOf(StringUtility.getParameter("dcpsHstChangesId", request));
			}

			List<TrnDcpsChanges> lObjTrnDcpsChangesList = (List<TrnDcpsChanges>) inputMap.get("lObjTrnDcpsChangesList");

			if (!StringUtility.getParameter("oldPhotoAttachmentId", request).equals("")
					&& StringUtility.getParameter("oldPhotoAttachmentId", request) != null) {
				lLongOldPhotoAttachmentId = Long.valueOf(StringUtility.getParameter("oldPhotoAttachmentId", request));
			}

			if (!StringUtility.getParameter("oldSignAttachmentId", request).equals("")
					&& StringUtility.getParameter("oldSignAttachmentId", request) != null) {
				lLongOldSignAttachmentId = Long.valueOf(StringUtility.getParameter("oldSignAttachmentId", request));
			}

			resObj = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);

			resObj = serv.executeService("FILE_UPLOAD_SRVC", inputMap);

			Map attachMap = (Map) resObj.getResultValue();

			if (attachMap.get("AttachmentId_Photo") != null) {
				lLongNewPhotoAttachmentId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_Photo")));
			}

			if (attachMap.get("AttachmentId_Sign") != null) {
				lLongNewSignAttachmentId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_Sign")));
			}

			if (lLongDcpsHstChangesId == null) {
				lLongChangesId = IFMSCommonServiceImpl.getNextSeqNum("HST_DCPS_CHANGES", inputMap);

				lObjChangesData.setDcpsChangesId(lLongChangesId);
				lObjChangesData.setDcpsEmpId(lLongEmployeeId);
				lObjChangesData.setTypeOfChanges("PhotoAndSignDetails");
				lObjChangesFormDAO.create(lObjChangesData);
				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {
					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLongEmployeeId);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("PhotoAndSignDetails");
					if (lInt == 0) {
						lObjTrnDcpsChangesList.get(lInt).setFieldName("PhotoId");
						if (lLongOldPhotoAttachmentId != null) {
							lObjTrnDcpsChangesList.get(lInt).setOldValue(lLongOldPhotoAttachmentId.toString());
						} else {
							lObjTrnDcpsChangesList.get(lInt).setOldValue("");
						}

						if (lLongNewPhotoAttachmentId != null) {

							lObjTrnDcpsChangesList.get(lInt).setNewValue(lLongNewPhotoAttachmentId.toString());
						} else {
							lObjTrnDcpsChangesList.get(lInt).setNewValue("");
						}
					}
					if (lInt == 1) {
						lObjTrnDcpsChangesList.get(lInt).setFieldName("SignatureId");
						if (lLongOldSignAttachmentId != null) {
							lObjTrnDcpsChangesList.get(lInt).setOldValue(lLongOldSignAttachmentId.toString());
						} else {
							lObjTrnDcpsChangesList.get(lInt).setOldValue("");
						}

						if (lLongNewSignAttachmentId != null) {

							lObjTrnDcpsChangesList.get(lInt).setNewValue(lLongNewSignAttachmentId.toString());
						} else {
							lObjTrnDcpsChangesList.get(lInt).setNewValue("");
						}
					}
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));
				}
			}

			// Below Part not checked till now
			if (lLongDcpsHstChangesId != null) {
				lObjChangesData.setDcpsChangesId(lLongDcpsHstChangesId);
				lObjChangesData.setDcpsEmpId(lLongEmployeeId);
				lObjChangesData.setTypeOfChanges("PhotoAndSignDetails");
				lObjChangesFormDAO.update(lObjChangesData);

				lObjChangesFormDAO.deleteTrnVOForDcpsChangesId(lLongDcpsHstChangesId);

				for (Integer lInt = 0; lInt < lObjTrnDcpsChangesList.size(); lInt++) {
					Long lLongChangesIdpK = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CHANGES", inputMap);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesIdPk(lLongChangesIdpK);
					lObjTrnDcpsChangesList.get(lInt).setDcpsChangesId(lLongDcpsHstChangesId);
					lObjTrnDcpsChangesList.get(lInt).setDcpsEmpId(lLongEmployeeId);
					lObjTrnDcpsChangesList.get(lInt).setTypeOfChanges("PhotoAndSignDetails");
					if (lInt == 0) {
						lObjTrnDcpsChangesList.get(lInt).setFieldName("PhotoId");
						if (lLongOldPhotoAttachmentId != null) {
							lObjTrnDcpsChangesList.get(lInt).setOldValue(lLongOldPhotoAttachmentId.toString());
						} else {
							lObjTrnDcpsChangesList.get(lInt).setOldValue("");
						}

						if (lLongNewPhotoAttachmentId != null) {

							lObjTrnDcpsChangesList.get(lInt).setNewValue(lLongNewPhotoAttachmentId.toString());
						} else {
							lObjTrnDcpsChangesList.get(lInt).setNewValue("");
						}
					}
					if (lInt == 1) {
						lObjTrnDcpsChangesList.get(lInt).setFieldName("SignatureId");
						if (lLongOldSignAttachmentId != null) {
							lObjTrnDcpsChangesList.get(lInt).setOldValue(lLongOldSignAttachmentId.toString());
						} else {
							lObjTrnDcpsChangesList.get(lInt).setOldValue("");
						}

						if (lLongNewSignAttachmentId != null) {

							lObjTrnDcpsChangesList.get(lInt).setNewValue(lLongNewSignAttachmentId.toString());
						} else {
							lObjTrnDcpsChangesList.get(lInt).setNewValue("");
						}
					}
					lObjChangesFormDAO.create(lObjTrnDcpsChangesList.get(lInt));
				}
			}

			lBlFlag = true;

		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			// ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
		}

		String lSBStatus = getResponseXMLDocUpdtOrFwd(lBlFlag, lObjChangesData.getDcpsChangesId()).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;

	}

	public ResultObject forwardChangesToDDO(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		Boolean lBlFlag;
		HstDcpsChanges lObjHstDcpsChanges = null;

		try {
			/* Sets the Session Information */
			setSessionInfo(objectArgs);

			/* Initializes variables and DAOs */
			lBlFlag = false;
			ChangesFormDAO lObjChangesFormDAO = new ChangesFormDAOImpl(HstDcpsChanges.class, serv.getSessionFactory());

			/*
			 * Gets the PostId and Changes Id from request to forward the changes to
			 */
			String toPost = StringUtility.getParameter("ForwardToPost", request).toString();

			/* Gets the level Id from resource bundle */
			String toLevel = gObjRsrcBndle.getString("DCPS.DDO");

			/* Puts above values into Map to create WorkFlow */
			objectArgs.put("toPost", toPost);
			objectArgs.put("toPostId", toPost);
			objectArgs.put("toLevel", toLevel);

			objectArgs.put("jobTitle", gObjRsrcBndle.getString("DCPS.ChangesForm"));
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.ChangesFormID")));

			/*
			 * Gets the Personal Changes Ids from request to forward the changes to
			 */
			Long lLongDcpsChangesId = Long.valueOf(StringUtility.getParameter("dcpsChangesId", request));

			/*
			 * Creates WorkFlow and Forwards the dcpsPersonalChanges to DDO and also sets
			 * the FormStatus to 1.
			 */

			objectArgs.put("Pkvalue", lLongDcpsChangesId);
			createWF(objectArgs);
			WorkFlowDelegate.forward(objectArgs);

			lObjHstDcpsChanges = new HstDcpsChanges();
			lObjHstDcpsChanges = (HstDcpsChanges) lObjChangesFormDAO.read(lLongDcpsChangesId);
			lObjHstDcpsChanges.setFormStatus(0L);
			lObjChangesFormDAO.update(lObjHstDcpsChanges);

			List<TrnDcpsChanges> TrnDcpsChangesList = lObjChangesFormDAO
					.getChangesFromTrnForChangesId(lObjHstDcpsChanges.getDcpsChangesId());

			for (Integer lInt = 0; lInt < TrnDcpsChangesList.size(); lInt++) {
				TrnDcpsChangesList.get(lInt).setFormStatus(0L);
				lObjChangesFormDAO.update(TrnDcpsChangesList.get(lInt));
			}

			lBlFlag = true;

		} catch (Exception ex) {
			gLogger.error(" Error is : " + ex, ex);
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			// ex.printStackTrace();
			return resObj;
		}

		/* Generates the XML response and sends the success flag */
		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setResultValue(objectArgs);
		resObj.setViewName("ajaxData");

		return resObj;
	}

	public ResultObject rejectChangesToDDOAsst(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		String strPKValue;

		try {

			setSessionInfo(objectArgs);
			strPKValue = StringUtility.getParameter("dcpsChangesId", request).toString().trim();

			String lStrRemarks = StringUtility.getParameter("sentBackRemarks", request).toString().trim();

			objectArgs.put("FromPostId", gStrPostId);
			objectArgs.put("SendNotification", lStrRemarks);
			objectArgs.put("jobTitle", gObjRsrcBndle.getString("DCPS.ChangesForm"));
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.ChangesFormID")));

			objectArgs.put("Pkvalue", strPKValue);
			WorkFlowDelegate.returnDoc(objectArgs);

			// Update the Registration form status to -1 suggesting it is
			// rejected

			ChangesFormDAO lObjChangesFormDAO = new ChangesFormDAOImpl(HstDcpsChanges.class, serv.getSessionFactory());

			Long lLongPKValue = Long.parseLong(strPKValue);
			HstDcpsChanges lObjHstDcpsChanges = (HstDcpsChanges) lObjChangesFormDAO.read(lLongPKValue);

			// Set the value in Read VO
			lObjHstDcpsChanges.setFormStatus(-1L);
			lObjHstDcpsChanges.setUpdatedUserId(gLngUserId);
			lObjHstDcpsChanges.setUpdatedPostId(gLngPostId);
			lObjHstDcpsChanges.setUpdatedDate(gDtCurrDt);
			lObjHstDcpsChanges.setSentBackRemarks(lStrRemarks);

			lObjChangesFormDAO.update(lObjHstDcpsChanges);

		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			ex.printStackTrace();
			return resObj;
		}

		String lSBStatus = getResponseXMLDoc(true).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;
	}

	public ResultObject approveChangesByDDO(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = null;
		HstDcpsChanges lObjHstDcpsChanges = null;

		try {
			/* Sets the Session Information */
			setSessionInfo(objectArgs);

			/* Initializes variables and DAOs */
			lBlFlag = false;

			ChangesFormDAO lObjChangesFormDAO = new ChangesFormDAOImpl(HstDcpsChanges.class, serv.getSessionFactory());

			Long lLongDcpsChangesId = Long
					.valueOf(StringUtility.getParameter("dcpsChangesId", request).toString().trim());

			lObjHstDcpsChanges = (HstDcpsChanges) lObjChangesFormDAO.read(lLongDcpsChangesId);

			if (lObjHstDcpsChanges.getTypeOfChanges().equals("PersonalDetails")) {

				ExchangePersonalDetailsBtnMstAndHst(lObjHstDcpsChanges, objectArgs);
			}

			if (lObjHstDcpsChanges.getTypeOfChanges().equals("OfficeDetails")) {

				ExchangeOfficeDetailsBtnMstAndHst(lObjHstDcpsChanges, objectArgs);
			}

			if (lObjHstDcpsChanges.getTypeOfChanges().equals("OtherDetails")) {

				ExchangeOtherDetailsBtnMstAndHst(lObjHstDcpsChanges, objectArgs);
			}

			if (lObjHstDcpsChanges.getTypeOfChanges().equals("NomineeDetails")) {
				ExchangeNomineeDetailsBtnMstAndHst(lObjHstDcpsChanges, objectArgs);
			}

			if (lObjHstDcpsChanges.getTypeOfChanges().equals("PhotoAndSignDetails")) {
				ExchangePhotoAndSignDetailsBtnMstAndHst(lObjHstDcpsChanges);
			}

			List<TrnDcpsChanges> TrnDcpsChangesList = lObjChangesFormDAO
					.getChangesFromTrnForChangesId(lObjHstDcpsChanges.getDcpsChangesId());

			lObjHstDcpsChanges.setFormStatus(1L);
			lObjChangesFormDAO.update(lObjHstDcpsChanges);

			for (Integer lInt = 0; lInt < TrnDcpsChangesList.size(); lInt++) {
				TrnDcpsChangesList.get(lInt).setFormStatus(1L);
				lObjChangesFormDAO.update(TrnDcpsChangesList.get(lInt));
			}

			lBlFlag = true;

		} catch (Exception ex) {
			gLogger.error(" Error is : " + ex, ex);
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			// ex.printStackTrace();
			return resObj;
		}

		/* Generates the XML response and sends the success flag */
		String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;
	}

	private void createWF(Map inputMap) {

		try {
			setSessionInfo(inputMap);

			Long PKValue = Long.parseLong(inputMap.get("Pkvalue").toString());
			String subjectName = gObjRsrcBndle.getString("DCPS.ChangesForm");
			String lStrPostId = SessionHelper.getPostId(inputMap).toString();
			Long lLngHierRefId = WorkFlowHelper.getHierarchyByPostIDAndDescription(lStrPostId, subjectName, inputMap);

			inputMap.put("Hierarchy_ref_id", lLngHierRefId);
			inputMap.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.ChangesFormID")));
			inputMap.put("Pkvalue", PKValue);
			inputMap.put("DisplayJobTitle", gObjRsrcBndle.getString("DCPS.ChangesForm"));

			WorkFlowDelegate.create(inputMap);
		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
			// e.printStackTrace();
		}
	}

	private List getHierarchyUsers(Map inputMap, String lStrUser) {

		List UserList = null;
		String subjectName = null;

		try {
			setSessionInfo(inputMap);

			Integer llFromLevelId = 0;
			UserList = new ArrayList<String>();

			if (lStrUser.equals("DDOAsst")) {
				subjectName = gObjRsrcBndle.getString("DCPS.ChangesForm");
			}

			Long lLngHierRefId = WorkFlowHelper.getHierarchyByPostIDAndDescription(gStrPostId, subjectName, inputMap);

			llFromLevelId = WorkFlowHelper.getLevelFromPostMpg(gStrPostId, lLngHierRefId, inputMap);

			List rsltList = WorkFlowHelper.getUpperPost(gStrPostId, lLngHierRefId, llFromLevelId, inputMap);

			Object[] lObjNextPost = null;

			for (Integer lInt = 0; lInt < rsltList.size(); lInt++) {

				lObjNextPost = (Object[]) rsltList.get(lInt);

				if (!(lObjNextPost.equals(null))) {
					UserList.add(lObjNextPost[0].toString());
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
		}
		return UserList;
	}

	private StringBuilder getResponseXMLDoc(Boolean lBlFlag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<txtGroup>");
		lStrBldXML.append(lBlFlag);
		lStrBldXML.append("</txtGroup>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDocUpdtOrFwd(Boolean lBlFlag, Long lLngDcpsChangesId) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<lBlFlag>");
		lStrBldXML.append(lBlFlag);
		lStrBldXML.append("</lBlFlag>");
		lStrBldXML.append("<lLngDcpsChangesId>");
		lStrBldXML.append(lLngDcpsChangesId);
		lStrBldXML.append("</lLngDcpsChangesId>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private void ExchangePersonalDetailsBtnMstAndHst(HstDcpsChanges lObjHstDcpsChanges, Map objectArgs)
			throws Exception {

		HstDcpsPersonalChanges lObjHstDcpsPersonalChanges = null;
		HstDcpsPersonalChanges lObjTempHstDcpsPersonalChanges = null;
		MstEmp lObjMstEmp = null;
		Long dcpsPersonalChangesIdPk = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;

		ChangesFormDAO lObjPersonalChangesFormDAO = new ChangesFormDAOImpl(HstDcpsPersonalChanges.class,
				serv.getSessionFactory());
		dcpsPersonalChangesIdPk = lObjPersonalChangesFormDAO
				.getPersonalChangesIdforChangesId(lObjHstDcpsChanges.getDcpsChangesId());
		lObjHstDcpsPersonalChanges = (HstDcpsPersonalChanges) lObjPersonalChangesFormDAO.read(dcpsPersonalChangesIdPk);
		NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
		lObjMstEmp = (MstEmp) lObjNewRegDdoDAO.read(lObjHstDcpsChanges.getDcpsEmpId());

		lObjRltDcpsPayrollEmp = lObjNewRegDdoDAO.getPayrollVOForEmpId(lObjHstDcpsChanges.getDcpsEmpId());

		lObjTempHstDcpsPersonalChanges = (HstDcpsPersonalChanges) lObjHstDcpsPersonalChanges.clone();

		lObjHstDcpsPersonalChanges.setBuilding_address(lObjMstEmp.getBuilding_address());
		lObjHstDcpsPersonalChanges.setBuilding_street(lObjMstEmp.getBuilding_street());
		lObjHstDcpsPersonalChanges.setCellNo(lObjMstEmp.getCellNo());
		lObjHstDcpsPersonalChanges.setCntctNo(lObjMstEmp.getCntctNo());
		lObjHstDcpsPersonalChanges.setDistrict(lObjMstEmp.getDistrict());
		lObjHstDcpsPersonalChanges.setDob(lObjMstEmp.getDob());
		lObjHstDcpsPersonalChanges.setDoj(lObjMstEmp.getDoj());
		lObjHstDcpsPersonalChanges.setPhychallanged(lObjRltDcpsPayrollEmp.getPhychallanged());
		lObjHstDcpsPersonalChanges.setEmailId(lObjMstEmp.getEmailId());
		lObjHstDcpsPersonalChanges.setFather_or_husband(lObjMstEmp.getFather_or_husband());
		lObjHstDcpsPersonalChanges.setGender(lObjMstEmp.getGender());
		lObjHstDcpsPersonalChanges.setLandmark(lObjMstEmp.getLandmark());
		lObjHstDcpsPersonalChanges.setLocality(lObjMstEmp.getLocality());
		lObjHstDcpsPersonalChanges.setName(lObjMstEmp.getName());
		lObjHstDcpsPersonalChanges.setName_marathi(lObjMstEmp.getName_marathi());
		lObjHstDcpsPersonalChanges.setPANNo(lObjMstEmp.getPANNo());
		lObjHstDcpsPersonalChanges.setPincode(lObjMstEmp.getPincode());
		lObjHstDcpsPersonalChanges.setSalutation(lObjMstEmp.getSalutation());
		lObjHstDcpsPersonalChanges.setState(lObjMstEmp.getState());
		lObjHstDcpsPersonalChanges.setUIDNo(lObjMstEmp.getUIDNo());
		lObjHstDcpsPersonalChanges.setEIDNo(lObjMstEmp.getEIDNo());
		lObjHstDcpsPersonalChanges.setUpdatedDate(gDtCurDate);
		lObjHstDcpsPersonalChanges.setUpdatedPostId(gLngPostId);
		lObjHstDcpsPersonalChanges.setUpdatedUserId(gLngUserId);

		lObjPersonalChangesFormDAO.update(lObjHstDcpsPersonalChanges);

		if (lObjTempHstDcpsPersonalChanges.getBuilding_address() != null) {
			lObjMstEmp.setBuilding_address(lObjTempHstDcpsPersonalChanges.getBuilding_address());
		}
		if (lObjTempHstDcpsPersonalChanges.getBuilding_street() != null) {
			lObjMstEmp.setBuilding_street(lObjTempHstDcpsPersonalChanges.getBuilding_street());
		}
		if (lObjTempHstDcpsPersonalChanges.getCellNo() != null) {
			lObjMstEmp.setCellNo(lObjTempHstDcpsPersonalChanges.getCellNo());
		}
		if (lObjTempHstDcpsPersonalChanges.getCntctNo() != null) {
			lObjMstEmp.setCntctNo(lObjTempHstDcpsPersonalChanges.getCntctNo());
		}
		if (lObjTempHstDcpsPersonalChanges.getDistrict() != null) {
			lObjMstEmp.setDistrict(lObjTempHstDcpsPersonalChanges.getDistrict());
		}
		if (lObjTempHstDcpsPersonalChanges.getDob() != null) {
			lObjMstEmp.setDob(lObjTempHstDcpsPersonalChanges.getDob());
		}
		if (lObjTempHstDcpsPersonalChanges.getDoj() != null) {
			lObjMstEmp.setDoj(lObjTempHstDcpsPersonalChanges.getDoj());
		}
		if (lObjTempHstDcpsPersonalChanges.getEmailId() != null) {
			lObjMstEmp.setEmailId(lObjTempHstDcpsPersonalChanges.getEmailId());
		}
		if (lObjTempHstDcpsPersonalChanges.getFather_or_husband() != null) {
			lObjMstEmp.setFather_or_husband(lObjTempHstDcpsPersonalChanges.getFather_or_husband());
		}
		if (lObjTempHstDcpsPersonalChanges.getGender() != null) {
			lObjMstEmp.setGender(lObjTempHstDcpsPersonalChanges.getGender());
		}
		if (lObjTempHstDcpsPersonalChanges.getLandmark() != null) {
			lObjMstEmp.setLandmark(lObjTempHstDcpsPersonalChanges.getLandmark());
		}
		if (lObjTempHstDcpsPersonalChanges.getLocality() != null) {
			lObjMstEmp.setLocality(lObjTempHstDcpsPersonalChanges.getLocality());
		}
		if (lObjTempHstDcpsPersonalChanges.getName() != null) {
			lObjMstEmp.setName(lObjTempHstDcpsPersonalChanges.getName());
		}
		if (lObjTempHstDcpsPersonalChanges.getName_marathi() != null) {
			lObjMstEmp.setName_marathi(lObjTempHstDcpsPersonalChanges.getName_marathi());
		}
		if (lObjTempHstDcpsPersonalChanges.getPANNo() != null) {
			lObjMstEmp.setPANNo(lObjTempHstDcpsPersonalChanges.getPANNo());
		}
		if (lObjTempHstDcpsPersonalChanges.getPincode() != null) {
			lObjMstEmp.setPincode(lObjTempHstDcpsPersonalChanges.getPincode());
		}
		if (lObjTempHstDcpsPersonalChanges.getSalutation() != null) {
			lObjMstEmp.setSalutation(lObjTempHstDcpsPersonalChanges.getSalutation());
		}
		if (lObjTempHstDcpsPersonalChanges.getState() != null) {
			lObjMstEmp.setState(lObjTempHstDcpsPersonalChanges.getState());
		}
		if (lObjTempHstDcpsPersonalChanges.getUIDNo() != null) {
			lObjMstEmp.setUIDNo(lObjTempHstDcpsPersonalChanges.getUIDNo());
		}
		if (lObjTempHstDcpsPersonalChanges.getEIDNo() != null) {
			lObjMstEmp.setEIDNo(lObjTempHstDcpsPersonalChanges.getEIDNo());
		}
		lObjMstEmp.setUpdatedDate(gDtCurDate);
		lObjMstEmp.setUpdatedPostId(gLngPostId);
		lObjMstEmp.setUpdatedUserId(gLngUserId);

		if (lObjTempHstDcpsPersonalChanges.getPhychallanged() != null) {
			lObjRltDcpsPayrollEmp.setPhychallanged(lObjTempHstDcpsPersonalChanges.getPhychallanged());
		}

		// VO Passed to Payroll
		objectArgs.put("ChangedPersonalVO", lObjTempHstDcpsPersonalChanges);
		ResultObject objRes = serv.executeService("changePersonalDtlsInPayroll", objectArgs);

		lObjPersonalChangesFormDAO.update(lObjRltDcpsPayrollEmp);
		lObjPersonalChangesFormDAO.update(lObjMstEmp);

	}

	private void ExchangeOfficeDetailsBtnMstAndHst(HstDcpsChanges lObjHstDcpsChanges, Map objectArgs) throws Exception {

		HstDcpsOfficeChanges lObjHstDcpsOfficeChanges = null;
		HstDcpsOfficeChanges lObjTempHstDcpsOfficeChanges = null;
		MstEmp lObjMstEmp = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;
		Long dcpsOfficeChangesIdPk = null;

		ChangesFormDAO lObjOfficeChangesFormDAO = new ChangesFormDAOImpl(HstDcpsOfficeChanges.class,
				serv.getSessionFactory());
		dcpsOfficeChangesIdPk = lObjOfficeChangesFormDAO
				.getOfficeChangesIdforChangesId(lObjHstDcpsChanges.getDcpsChangesId());
		lObjHstDcpsOfficeChanges = (HstDcpsOfficeChanges) lObjOfficeChangesFormDAO.read(dcpsOfficeChangesIdPk);
		NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
		lObjMstEmp = (MstEmp) lObjNewRegDdoDAO.read(lObjHstDcpsChanges.getDcpsEmpId());
		lObjRltDcpsPayrollEmp = lObjNewRegDdoDAO.getPayrollVOForEmpId(lObjHstDcpsChanges.getDcpsEmpId());

		lObjTempHstDcpsOfficeChanges = (HstDcpsOfficeChanges) lObjHstDcpsOfficeChanges.clone();

		lObjHstDcpsOfficeChanges.setParentDept(lObjMstEmp.getParentDept());
		lObjHstDcpsOfficeChanges.setCadre(lObjMstEmp.getCadre());
		lObjHstDcpsOfficeChanges.setGroup(lObjMstEmp.getGroup());
		lObjHstDcpsOfficeChanges.setCurrOff(lObjMstEmp.getCurrOff());
		lObjHstDcpsOfficeChanges.setRemarks(lObjMstEmp.getRemarks());
		lObjHstDcpsOfficeChanges.setFirstDesignation(lObjMstEmp.getFirstDesignation());
		lObjHstDcpsOfficeChanges.setAppointmentDate(lObjMstEmp.getAppointmentDate());
		lObjHstDcpsOfficeChanges.setCurrPostJoiningDate(lObjRltDcpsPayrollEmp.getCurrPostJoiningDate());
		/*
		 * lObjHstDcpsOfficeChanges.setCurrCadreJoiningDate(lObjRltDcpsPayrollEmp
		 * .getCurrCadreJoiningDate());
		 */
		lObjHstDcpsOfficeChanges.setDesignation(lObjMstEmp.getDesignation());
		lObjHstDcpsOfficeChanges.setPayCommission(lObjMstEmp.getPayCommission());
		lObjHstDcpsOfficeChanges.setPayScale(lObjMstEmp.getPayScale());
		lObjHstDcpsOfficeChanges.setBasicPay(lObjMstEmp.getBasicPay());

		lObjHstDcpsOfficeChanges.setUpdatedDate(gDtCurDate);
		lObjHstDcpsOfficeChanges.setUpdatedPostId(gLngPostId);
		lObjHstDcpsOfficeChanges.setUpdatedUserId(gLngUserId);

		lObjOfficeChangesFormDAO.update(lObjHstDcpsOfficeChanges);

		// code that decides which variables to pass for executing payroll's
		// service

		String groupId = null;
		String designationToBePassed = null;
		String payScaleToBePassed = null;
		String payCommissionToBePassed = null;
		String basicToBePassed = null;
		if (lObjTempHstDcpsOfficeChanges.getDesignation() != null) {
			designationToBePassed = lObjTempHstDcpsOfficeChanges.getDesignation();
		} else {
			designationToBePassed = lObjMstEmp.getDesignation();
		}
		if (lObjTempHstDcpsOfficeChanges.getPayScale() != null) {
			payScaleToBePassed = lObjTempHstDcpsOfficeChanges.getPayScale();
		} else {
			payScaleToBePassed = lObjMstEmp.getPayScale();
		}
		if (lObjTempHstDcpsOfficeChanges.getCadre() != null) {
			if (!"".equals(lObjTempHstDcpsOfficeChanges.getCadre())) {
				groupId = lObjOfficeChangesFormDAO
						.getGroupIdForCadreId(Long.valueOf(lObjTempHstDcpsOfficeChanges.getCadre()));
			}
		} else {
			if (!"".equals(lObjMstEmp.getCadre())) {
				groupId = lObjOfficeChangesFormDAO.getGroupIdForCadreId(Long.valueOf(lObjMstEmp.getCadre()));
			}
		}
		if (lObjTempHstDcpsOfficeChanges.getPayCommission() != null) {
			payCommissionToBePassed = lObjTempHstDcpsOfficeChanges.getPayCommission();
		} else {
			payCommissionToBePassed = lObjMstEmp.getPayCommission();
		}
		if (lObjTempHstDcpsOfficeChanges.getBasicPay() != null) {
			basicToBePassed = lObjTempHstDcpsOfficeChanges.getBasicPay().toString();
		} else {
			basicToBePassed = lObjMstEmp.getBasicPay().toString();
		}

		// code ends

		if (lObjTempHstDcpsOfficeChanges.getParentDept() != null) {
			lObjMstEmp.setParentDept(lObjTempHstDcpsOfficeChanges.getParentDept());
		}
		if (lObjTempHstDcpsOfficeChanges.getCadre() != null) {
			lObjMstEmp.setCadre(lObjTempHstDcpsOfficeChanges.getCadre());
		}
		if (lObjTempHstDcpsOfficeChanges.getGroup() != null) {
			lObjMstEmp.setGroup(lObjTempHstDcpsOfficeChanges.getGroup());
		}
		if (lObjTempHstDcpsOfficeChanges.getCurrOff() != null) {
			lObjMstEmp.setCurrOff(lObjTempHstDcpsOfficeChanges.getCurrOff());
		}
		if (lObjTempHstDcpsOfficeChanges.getDesignation() != null) {
			lObjMstEmp.setDesignation(lObjTempHstDcpsOfficeChanges.getDesignation());
		}
		if (lObjTempHstDcpsOfficeChanges.getPayCommission() != null) {
			lObjMstEmp.setPayCommission(lObjTempHstDcpsOfficeChanges.getPayCommission());
		}
		if (lObjTempHstDcpsOfficeChanges.getRemarks() != null) {
			lObjMstEmp.setRemarks(lObjTempHstDcpsOfficeChanges.getRemarks());
		}
		if (lObjTempHstDcpsOfficeChanges.getFirstDesignation() != null) {
			lObjMstEmp.setFirstDesignation(lObjTempHstDcpsOfficeChanges.getFirstDesignation());
		}
		if (lObjTempHstDcpsOfficeChanges.getAppointmentDate() != null) {
			lObjMstEmp.setAppointmentDate(lObjTempHstDcpsOfficeChanges.getAppointmentDate());
		}
		if (lObjTempHstDcpsOfficeChanges.getPayScale() != null) {
			lObjMstEmp.setPayScale(lObjTempHstDcpsOfficeChanges.getPayScale());
		}
		if (lObjTempHstDcpsOfficeChanges.getBasicPay() != null) {
			lObjMstEmp.setBasicPay(lObjTempHstDcpsOfficeChanges.getBasicPay());
		}
		if (lObjTempHstDcpsOfficeChanges.getCurrPostJoiningDate() != null) {
			lObjRltDcpsPayrollEmp.setCurrPostJoiningDate(lObjTempHstDcpsOfficeChanges.getCurrPostJoiningDate());
		}
		/*
		 * if (lObjTempHstDcpsOfficeChanges.getCurrCadreJoiningDate() != null) {
		 * lObjRltDcpsPayrollEmp .setCurrCadreJoiningDate(lObjTempHstDcpsOfficeChanges
		 * .getCurrCadreJoiningDate()); }
		 */

		lObjMstEmp.setUpdatedDate(gDtCurDate);
		lObjMstEmp.setUpdatedPostId(gLngPostId);
		lObjMstEmp.setUpdatedUserId(gLngUserId);

		lObjOfficeChangesFormDAO.update(lObjRltDcpsPayrollEmp);
		lObjOfficeChangesFormDAO.update(lObjMstEmp);

		// Code to Send Variables to Payroll

		objectArgs.put("empId", lObjTempHstDcpsOfficeChanges.getDcpsEmpId());
		objectArgs.put("dsgnId", designationToBePassed);
		objectArgs.put("scaleId", payScaleToBePassed);
		objectArgs.put("groupId", groupId);
		objectArgs.put("orderId", lObjHstDcpsChanges.getLetterNo());
		objectArgs.put("reasonForScaleChange", lObjTempHstDcpsOfficeChanges.getReasonForPSChange());
		if (lObjTempHstDcpsOfficeChanges.getOtherReasonForPSChange() != null) {
			objectArgs.put("reasonForScaleChangeOther", lObjTempHstDcpsOfficeChanges.getOtherReasonForPSChange());
		} else {
			objectArgs.put("reasonForScaleChangeOther", "");
		}
		objectArgs.put("commissionId", payCommissionToBePassed);
		objectArgs.put("WEFDate", lObjTempHstDcpsOfficeChanges.getWithEffectFromDate());
		objectArgs.put("FromDDO", "YES");
		objectArgs.put("basic", basicToBePassed);

		if (lObjTempHstDcpsOfficeChanges.getPayScale() != null) {
			ResultObject objRes = serv.executeService("scaleChangeService", objectArgs);

			if (objRes.getResultCode() == ErrorConstants.ERROR) {
				throw new Exception();
			}
		}

		// Code ends

	}

	private void ExchangeOtherDetailsBtnMstAndHst(HstDcpsChanges lObjHstDcpsChanges, Map objectArgs) throws Exception {

		HstDcpsOtherChanges lObjHstDcpsOtherChanges = null;
		HstDcpsOtherChanges lObjTempHstDcpsOtherChanges = null;
		MstEmp lObjMstEmp = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;
		Long dcpsOtherChangesIdPk = null;

		ChangesFormDAO lObjOtherChangesFormDAO = new ChangesFormDAOImpl(HstDcpsOtherChanges.class,
				serv.getSessionFactory());
		dcpsOtherChangesIdPk = lObjOtherChangesFormDAO
				.getOtherChangesIdforChangesId(lObjHstDcpsChanges.getDcpsChangesId());
		lObjHstDcpsOtherChanges = (HstDcpsOtherChanges) lObjOtherChangesFormDAO.read(dcpsOtherChangesIdPk);
		NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
		lObjMstEmp = (MstEmp) lObjNewRegDdoDAO.read(lObjHstDcpsChanges.getDcpsEmpId());
		lObjRltDcpsPayrollEmp = lObjNewRegDdoDAO.getPayrollVOForEmpId(lObjHstDcpsChanges.getDcpsEmpId());

		lObjTempHstDcpsOtherChanges = (HstDcpsOtherChanges) lObjHstDcpsOtherChanges.clone();

		lObjHstDcpsOtherChanges.setBankName(lObjMstEmp.getBankName());
		lObjHstDcpsOtherChanges.setBranchName(lObjMstEmp.getBranchName());
		lObjHstDcpsOtherChanges.setBankAccountNo(lObjMstEmp.getBankAccountNo());
		lObjHstDcpsOtherChanges.setIFSCCode(lObjMstEmp.getIFSCCode());

		// DCPS Account No and maintained by
		lObjHstDcpsOtherChanges.setAcDcpsMaintainedBy(lObjMstEmp.getAcDcpsMaintainedBy());
		lObjHstDcpsOtherChanges.setAcMntndByOthers(lObjMstEmp.getAcMntndByOthers());
		lObjHstDcpsOtherChanges.setAcNonSRKAEmp(lObjMstEmp.getAcNonSRKAEmp());

		// GPF Account No, PF Series and maintained by
		lObjHstDcpsOtherChanges.setAcMaintainedBy(lObjRltDcpsPayrollEmp.getAcMaintainedBy());
		lObjHstDcpsOtherChanges.setPfSeries(lObjRltDcpsPayrollEmp.getPfSeries());
		lObjHstDcpsOtherChanges.setPfSeriesDesc(lObjRltDcpsPayrollEmp.getPfSeriesDesc());
		lObjHstDcpsOtherChanges.setPfAcNo(lObjRltDcpsPayrollEmp.getPfAcNo());

		lObjHstDcpsOtherChanges.setUpdatedDate(gDtCurDate);
		lObjHstDcpsOtherChanges.setUpdatedPostId(gLngPostId);
		lObjHstDcpsOtherChanges.setUpdatedUserId(gLngUserId);

		lObjOtherChangesFormDAO.update(lObjHstDcpsOtherChanges);

		if (lObjTempHstDcpsOtherChanges.getBankName() != null) {
			lObjMstEmp.setBankName(lObjTempHstDcpsOtherChanges.getBankName());
		}
		if (lObjTempHstDcpsOtherChanges.getBranchName() != null) {
			lObjMstEmp.setBranchName(lObjTempHstDcpsOtherChanges.getBranchName());
		}
		if (lObjTempHstDcpsOtherChanges.getBankAccountNo() != null) {
			lObjMstEmp.setBankAccountNo(lObjTempHstDcpsOtherChanges.getBankAccountNo());
		}
		if (lObjTempHstDcpsOtherChanges.getIFSCCode() != null) {
			lObjMstEmp.setIFSCCode(lObjTempHstDcpsOtherChanges.getIFSCCode());
		}

		// DCPS Account No and maintained by

		if (lObjTempHstDcpsOtherChanges.getAcDcpsMaintainedBy() != null) {
			lObjMstEmp.setAcDcpsMaintainedBy(lObjTempHstDcpsOtherChanges.getAcDcpsMaintainedBy());
		}
		if (lObjTempHstDcpsOtherChanges.getAcMntndByOthers() != null) {
			lObjMstEmp.setAcMntndByOthers(lObjTempHstDcpsOtherChanges.getAcMntndByOthers());
		}
		if (lObjTempHstDcpsOtherChanges.getAcNonSRKAEmp() != null) {
			lObjMstEmp.setAcNonSRKAEmp(lObjTempHstDcpsOtherChanges.getAcNonSRKAEmp());
		}

		// GPF Account No, PF Series and maintained by

		if (lObjTempHstDcpsOtherChanges.getAcMaintainedBy() != null) {
			lObjRltDcpsPayrollEmp.setAcMaintainedBy(lObjTempHstDcpsOtherChanges.getAcMaintainedBy());
		}
		if (lObjTempHstDcpsOtherChanges.getPfSeries() != null) {
			lObjRltDcpsPayrollEmp.setPfSeries(lObjTempHstDcpsOtherChanges.getPfSeries());
		}
		if (lObjTempHstDcpsOtherChanges.getPfSeriesDesc() != null) {
			lObjRltDcpsPayrollEmp.setPfSeriesDesc(lObjTempHstDcpsOtherChanges.getPfSeriesDesc());
		}
		if (lObjTempHstDcpsOtherChanges.getPfAcNo() != null) {
			lObjRltDcpsPayrollEmp.setPfAcNo(lObjTempHstDcpsOtherChanges.getPfAcNo());
		}

		lObjMstEmp.setUpdatedDate(gDtCurDate);
		lObjMstEmp.setUpdatedPostId(gLngPostId);
		lObjMstEmp.setUpdatedUserId(gLngUserId);

		// VO Passed to Payroll
		objectArgs.put("ChangedOtherVO", lObjTempHstDcpsOtherChanges);
		ResultObject objRes = serv.executeService("changePersonalDtlsInPayroll", objectArgs);

		lObjOtherChangesFormDAO.update(lObjRltDcpsPayrollEmp);
		lObjOtherChangesFormDAO.update(lObjMstEmp);

	}

	private void ExchangeNomineeDetailsBtnMstAndHst(HstDcpsChanges lObjHstDcpsChanges, Map objectArgs)
			throws Exception {

		List<HstDcpsNomineeChanges> lListNomineeFromHst = null;
		List<MstEmpNmn> lArrMstEmpNmn = new ArrayList<MstEmpNmn>();
		Long lLongLatestRefId = null;
		Long lLongEmpId = null;
		MstEmpNmn lObjMstEmpNmn = null;
		Long lLngNomineeId = null;

		ChangesFormDAO lObjOtherChangesFormDAO = new ChangesFormDAOImpl(HstDcpsOtherChanges.class,
				serv.getSessionFactory());

		NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmpNmn.class, serv.getSessionFactory());

		lLongLatestRefId = lObjOtherChangesFormDAO.getLatestRefIdForNomineeChanges(lObjHstDcpsChanges.getDcpsEmpId(),
				lObjHstDcpsChanges.getDcpsChangesId());
		lLongEmpId = lObjHstDcpsChanges.getDcpsEmpId();

		lListNomineeFromHst = lObjOtherChangesFormDAO.getNomineesFromHst(lLongLatestRefId, lLongEmpId);

		lObjNewRegDdoDAO.deleteNomineesForGivenEmployee(lLongEmpId);

		for (Integer lInt = 0; lInt < lListNomineeFromHst.size(); lInt++) {
			lObjMstEmpNmn = new MstEmpNmn();
			lObjMstEmpNmn.setDcpsEmpId(lObjNewRegDdoDAO.getEmpVOForEmpId(lListNomineeFromHst.get(lInt).getDcpsEmpId()));
			lObjMstEmpNmn.setAddress1(lListNomineeFromHst.get(lInt).getAddress1());
			lObjMstEmpNmn.setName(lListNomineeFromHst.get(lInt).getName());
			lObjMstEmpNmn.setDob(lListNomineeFromHst.get(lInt).getDob());// changes for dob of nominee added by Saurabh
																			// S
			lObjMstEmpNmn.setRlt(lListNomineeFromHst.get(lInt).getRlt());
			lObjMstEmpNmn.setShare(lListNomineeFromHst.get(lInt).getShare());
			lObjMstEmpNmn.setLangId(gLngLangId);
			lObjMstEmpNmn.setLocId(Long.valueOf(gStrLocationCode));
			lObjMstEmpNmn.setDbId(gLngDBId);
			lObjMstEmpNmn.setCreatedDate(gDtCurDate);
			lObjMstEmpNmn.setCreatedPostId(gLngPostId);
			lObjMstEmpNmn.setCreatedUserId(gLngUserId);
			lObjMstEmpNmn.setUpdatedDate(gDtCurDate);
			lObjMstEmpNmn.setUpdatedPostId(gLngPostId);
			lObjMstEmpNmn.setUpdatedUserId(gLngUserId);

			lArrMstEmpNmn.add(lObjMstEmpNmn);
		}

		for (Integer lInt = 0; lInt < lArrMstEmpNmn.size(); lInt++) {
			lLngNomineeId = IFMSCommonServiceImpl.getNextSeqNum("MST_DCPS_EMP_NMN", objectArgs);
			lArrMstEmpNmn.get(lInt).setDcpsEmpNmnId(lLngNomineeId);
			lObjNewRegDdoDAO.create(lArrMstEmpNmn.get(lInt));
		}
	}

	private void ExchangePhotoAndSignDetailsBtnMstAndHst(HstDcpsChanges lObjHstDcpsChanges) throws Exception {

		MstEmp lObjMstEmp = null;
		TrnDcpsChanges lObjTrnDcpsChanges = null;
		String fieldNamePhoto = "PhotoId"; // Deliberately hard coded
		String fieldNameSign = "SignatureId"; // Deliberately hard coded
		Long lLongTrnChangesInPhotoIdPk = null;
		Long lLongTrnChangesInSignIdPk = null;
		Long lLongChangedPhotoId = null;
		Long lLongChangedSignId = null;

		ChangesFormDAO lObjPhotoAndSignChangesFormDAO = new ChangesFormDAOImpl(TrnDcpsChanges.class,
				serv.getSessionFactory());

		lLongTrnChangesInPhotoIdPk = lObjPhotoAndSignChangesFormDAO
				.getPkFromTrnForTheChangeInPhotoSign(lObjHstDcpsChanges.getDcpsChangesId(), fieldNamePhoto);
		lObjTrnDcpsChanges = (TrnDcpsChanges) lObjPhotoAndSignChangesFormDAO.read(lLongTrnChangesInPhotoIdPk);
		if (lObjTrnDcpsChanges.getNewValue() != null) {
			lLongChangedPhotoId = Long.valueOf(lObjTrnDcpsChanges.getNewValue());
		}

		lObjTrnDcpsChanges = null;

		lLongTrnChangesInSignIdPk = lObjPhotoAndSignChangesFormDAO
				.getPkFromTrnForTheChangeInPhotoSign(lObjHstDcpsChanges.getDcpsChangesId(), fieldNameSign);
		lObjTrnDcpsChanges = (TrnDcpsChanges) lObjPhotoAndSignChangesFormDAO.read(lLongTrnChangesInSignIdPk);
		if (lObjTrnDcpsChanges.getNewValue() != null) {
			lLongChangedSignId = Long.valueOf(lObjTrnDcpsChanges.getNewValue());
		}

		NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

		lObjMstEmp = (MstEmp) lObjNewRegDdoDAO.read(lObjHstDcpsChanges.getDcpsEmpId());

		if (lLongChangedPhotoId != null) {
			lObjMstEmp.setPhotoAttachmentID(lLongChangedPhotoId);
		}
		if (lLongChangedSignId != null) {
			lObjMstEmp.setSignatureAttachmentID(lLongChangedSignId);
		}

		lObjMstEmp.setUpdatedDate(gDtCurDate);
		lObjMstEmp.setUpdatedPostId(gLngPostId);
		lObjMstEmp.setUpdatedUserId(gLngUserId);

		lObjPhotoAndSignChangesFormDAO.update(lObjMstEmp);

	}

	public ResultObject popUpReasonsForSalaryChange(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lListReasonsForSalaryChange = null;
		Integer lNoOfReasons = 0;
		Long lLongPaycommission = null;

		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */

			/* Gets SchemeCode from request */
			if (!StringUtility.getParameter("cmbPayCommission", request).equalsIgnoreCase("")) {
				lLongPaycommission = Long.valueOf(StringUtility.getParameter("cmbPayCommission", request));
			}

			if (lLongPaycommission == 700015l) {
				lListReasonsForSalaryChange = IFMSCommonServiceImpl.getLookupValues("ReasonsForSalaryChangeIn5PC",
						SessionHelper.getLangId(inputMap), inputMap);
			} else if (lLongPaycommission == 700016l) {
				lListReasonsForSalaryChange = IFMSCommonServiceImpl.getLookupValues("ReasonsForSalaryChangeIn6PC",
						SessionHelper.getLangId(inputMap), inputMap);
			} else {
				lListReasonsForSalaryChange = IFMSCommonServiceImpl.getLookupValues("ReasonsForSalaryChangeIn6PC",
						SessionHelper.getLangId(inputMap), inputMap);
			}
			lNoOfReasons = lListReasonsForSalaryChange.size();

			/*
			 * Gets the all the schemeNames whose scheme code starts with given scheme code
			 * and finds total schemes
			 */

			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			// e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

		/*
		 * Generates XML response for all schemes found whose scheme code starts with
		 * given scheme code
		 */
		String lSBStatus = getResponseXMLDocToDisplayReasons(lNoOfReasons, lListReasonsForSalaryChange).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	private StringBuilder getResponseXMLDocToDisplayReasons(Integer lNoOfReasons, List lListReasonsForSalaryChange) {

		StringBuilder lStrBldXML = new StringBuilder();
		lNoOfReasons = lListReasonsForSalaryChange.size();

		lStrBldXML.append("<XMLDOC>");

		lStrBldXML.append("<lNoOfReasons>");
		lStrBldXML.append(lNoOfReasons);
		lStrBldXML.append("</lNoOfReasons>");

		Iterator it = lListReasonsForSalaryChange.iterator();

		while (it.hasNext()) {
			CmnLookupMst lObjCmnLookupMst = (CmnLookupMst) it.next();

			lStrBldXML.append("<ReasonId>");
			lStrBldXML.append("<![CDATA[");
			lStrBldXML.append(lObjCmnLookupMst.getLookupId());
			lStrBldXML.append("]]>");
			lStrBldXML.append(" </ReasonId>");

			lStrBldXML.append(" <ReasonDesc>");
			lStrBldXML.append(lObjCmnLookupMst.getLookupDesc());
			lStrBldXML.append(" </ReasonDesc>");
		}

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
}
