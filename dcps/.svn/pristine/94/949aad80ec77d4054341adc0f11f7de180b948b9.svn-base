/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jan 31, 2011		Shivani Rana								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

//com.tcs.sgv.dcps.service.DCPSTreasuryServiceImpl

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.tcs.sgv.common.util.ExcelParser;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnAttdocMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.ArrearsDAO;
import com.tcs.sgv.dcps.dao.ArrearsDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.dao.TreasuryDAO;
import com.tcs.sgv.dcps.dao.TreasuryDAOImpl;
import com.tcs.sgv.dcps.valueobject.HstEmpDeputation;
import com.tcs.sgv.dcps.valueobject.MstDcpsContriVoucherDtls;
import com.tcs.sgv.dcps.valueobject.MstDcpsTreasurynetData;
import com.tcs.sgv.dcps.valueobject.MstDummyOffice;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsSixPCYearly;
import com.tcs.sgv.dcps.valueobject.RltPhyFormStatus;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;
import com.tcs.sgv.dcps.valueobject.TrnDcpsDeputationContribution;
import com.tcs.sgv.web.jsp.tags.DateUtilities;
import com.tcs.sgv.wf.delegate.WorkFlowDelegate;

/**
 * Class Description -
 * 
 * 
 * @author Shivani Rana
 * @version 0.1
 * @since JDK 5.0 Jan 31, 2011
 */
public class TreasuryServiceImpl extends ServiceImpl implements TreasuryService {

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
	/* Global Variable for Logger Class */

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

	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	private Long lLngLocId;

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
			lLngLocId = Long.valueOf(SessionHelper.getLocationCode(inputMap));
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gDtCurrDt = SessionHelper.getCurDate();
		} catch (Exception e) {

		}

	}

	/**
	 * Method to load the DDO Codes and DDO Names respective to the Treasury
	 * 
	 * @param inputMap
	 * @return ResultObject
	 */

	public ResultObject loadTOPhyFormRcvdList(Map inputMap) {

		ServiceLocator servLoc = (ServiceLocator) inputMap
				.get("serviceLocator");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		setSessionInfo(inputMap);

		String lStrPostId = SessionHelper.getPostId(inputMap).toString();
		String lStrUserType = null;
		TreasuryDAO dcpsTreasuryDao = new TreasuryDAOImpl(null, servLoc
				.getSessionFactory());

		try {

			List lListForms = dcpsTreasuryDao.getAllDDOListForPhyFormRcvd(
					lStrPostId, lStrUserType);
			inputMap.put("formList", lListForms);
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		resObj.setResultValue(inputMap);
		resObj.setViewName("DCPSPhyFormRcvdList");
		return resObj;

	}

	/**
	 * Method to load the employee forms according to the DDO code selected
	 * 
	 * @param inputMap
	 * @return ResultObject
	 */

	public ResultObject loadTOPhyFormRcvd(Map inputMap) throws Exception {

		ServiceLocator servLoc = (ServiceLocator) inputMap
				.get("serviceLocator");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		TreasuryDAO dcpsTreasuryDao = new TreasuryDAOImpl(null, servLoc
				.getSessionFactory());

		String lStrDDOCode = StringUtility.getParameter("ddoCode", request);
		Long.parseLong(lStrDDOCode);

		try {

			List lListForms = dcpsTreasuryDao.getAllFormsForDDO(lStrDDOCode,
					gStrPostId);
			inputMap.put("formPhyList", lListForms);
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		resObj.setResultValue(inputMap);
		resObj.setViewName("DCPSPhyFormRcvd");
		return resObj;

	}

	/**
	 * Method to submit the physical form received
	 * 
	 * @param inputMap
	 * @return ResultObject
	 */

	public ResultObject submitPhyFormRcvdStatus(Map inputMap) throws Exception {

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		RltPhyFormStatus lObjDcpsPhyFormStatus;
		Boolean lStrflag = false;

		String lStrNos = StringUtility.getParameter("empNos", request);
		String[] lArrdcpsPhyFormRcvdStatusIds = lStrNos.split("~");
		setSessionInfo(inputMap);

		try {

			for (int i = 0; i < lArrdcpsPhyFormRcvdStatusIds.length; i++) {

				String lStrdcpsPhyFormRcvdStatusId = lArrdcpsPhyFormRcvdStatusIds[i]
						.trim();
				Long lLngdcpsPhyFormRcvdStatusId = Long
						.parseLong(lStrdcpsPhyFormRcvdStatusId);

				// Read the DcpsPhyFormStatus VO for changing status
				TreasuryDAO dcpsTreasuryDao = new TreasuryDAOImpl(
						RltPhyFormStatus.class, serv.getSessionFactory());
				lObjDcpsPhyFormStatus = (RltPhyFormStatus) dcpsTreasuryDao
						.read(lLngdcpsPhyFormRcvdStatusId);

				// Set the value in Read VO
				lObjDcpsPhyFormStatus.setPhyFormRcvd(1L);
				lObjDcpsPhyFormStatus.setUpdatedUserId(gLngUserId);
				lObjDcpsPhyFormStatus.setUpdatedPostId(gLngPostId);
				lObjDcpsPhyFormStatus.setUpdatedDate(gDtCurrDt);

				lStrflag = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			return objRes;
		}

		String lSBStatus = getResponseXMLDoc(lStrflag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();
		inputMap.put("ajaxKey", lStrResult);
		objRes.setResultValue(inputMap);
		objRes.setViewName("ajaxData");
		return objRes;

	}

	/**
	 * 
	 * <H3>Description -</H3>
	 * 
	 * 
	 * @author Kapil Devani
	 * @param inputMap
	 * @return
	 * @throws Exception
	 */
	public ResultObject ApproveForm(Map inputMap) throws Exception {

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		MstEmp lObjDcpsEmpMst;
		Boolean lStrflag = false;

		String lStrNos = StringUtility.getParameter("Emp_Id", request);
		String[] lArrEmpNo = lStrNos.split("~");
		setSessionInfo(inputMap);

		String lStrEmpId = null;
		Long lLngEmpId = 0L;

		try {

			for (int i = 0; i < lArrEmpNo.length; i++) {

				lStrEmpId = lArrEmpNo[i].trim();
				lLngEmpId = Long.parseLong(lStrEmpId);

				// Read the DcpsPhyFormStatus VO for changing status
				TreasuryDAO dcpsTreasuryDao = new TreasuryDAOImpl(MstEmp.class,
						serv.getSessionFactory());
				lObjDcpsEmpMst = (MstEmp) dcpsTreasuryDao.read(lLngEmpId);

				// Set the value in Read VO
				// String lStrDcpsId = generateDCPDId(inputMap, lLngEmpId);

				// lObjDcpsEmpMst.setDcpsId(lStrDcpsId);
				lObjDcpsEmpMst.setRegStatus(1L);
				lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
				lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
				lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
				lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);
				lStrflag = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			return objRes;
		}

		String lSBStatus = getResponseXMLDoc(lStrflag, lLngEmpId).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();
		inputMap.put("ajaxKey", lStrResult);
		objRes.setResultValue(inputMap);
		objRes.setViewName("ajaxData");
		return objRes;

	}

	/**
	 * 
	 * Function used to generate the DCPS ID by the treasury
	 * 
	 * 
	 * @author Kapil Devani
	 * @param inputMap
	 * @return
	 */

	/**
	 * 
	 * <H3>Description -</H3>
	 * 
	 * 
	 * 
	 * @author Bhargav Trivedi
	 * @param objectArgs
	 * @return
	 */
	public ResultObject rejectRequest(Map objectArgs) {

		objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		objectArgs.get("serviceLocator");
		setSessionInfo(objectArgs);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {

			String strPKValue = StringUtility.getParameter("Emp_Id", request)
					.toString().trim();

			String[] strArrPKValue = strPKValue.split("~");

			objectArgs.put("FromPostId", gStrPostId);
			objectArgs.put("SendNotification", "This is not valid");
			objectArgs.put("jobTitle", gObjRsrcBndle
					.getString("DCPS.RegistrationForm"));
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle
					.getString("DCPS.RegistrationFormID")));

			for (int index = 0; index < strArrPKValue.length; index++) {
				objectArgs.put("Pkvalue", strArrPKValue[index]);
				WorkFlowDelegate.returnDoc(objectArgs);

				// Update the Registration form status to -1 suggesting it is
				// rejected

				NewRegDdoDAO dcpsNewRegistrationDao = new NewRegDdoDAOImpl(
						MstEmp.class, serv.getSessionFactory());
				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				MstEmp lObjDcpsEmpMst = (MstEmp) dcpsNewRegistrationDao
						.read(lLongPKValue);

				// Set the value in Read VO
				lObjDcpsEmpMst.setRegStatus(-1L);
				lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
				lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
				lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
				lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);

			}

			objectArgs.put("ajaxKey", "Success");
			resObj.setViewName("ajaxData");
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			ex.printStackTrace();
			return resObj;
		}
		return resObj;
	}

	/**
	 * Method to generate the Ajax response
	 * 
	 * @param boolean flag
	 * @return StringBuilder
	 */
	private StringBuilder getResponseXMLDoc(Boolean flag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("  <FLAG>");
		lStrBldXML.append(flag);
		lStrBldXML.append("  </FLAG>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	/*
	 * Method to generate the xml response for Ajax
	 */
	private StringBuilder getResponseXMLDoc(Boolean flag, long empID) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("  <Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("  </Flag>");
		lStrBldXML.append("  <EMPID>");
		lStrBldXML.append(empID);
		lStrBldXML.append("  </EMPID>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.TreasuryService#loadEmpDeputation(java.util.Map)
	 */
	public ResultObject loadEmpDeputation(Map inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		TreasuryDAO lObjTreasuryDAO = null;
		List lLstOffice = null;
		List lLstReasons = null;
		List lLstEmpDeptn = null;
		String lStrQueryString = null;
		String lStrSevarthId = null;
		String lStrRequestForSearch = null;
		String lStrEmpName = null;
		
		try {
			setSessionInfo(inputMap);
			lStrQueryString = StringUtility
					.getParameter("queryString", request);

			lObjTreasuryDAO = new TreasuryDAOImpl(TreasuryServiceImpl.class,
					serv.getSessionFactory());

			lLstReasons = IFMSCommonServiceImpl.getLookupValues(
					"Reason For Deslection", gLngLangId, inputMap);

			lLstOffice = lObjTreasuryDAO.getDummyOffices();
			
			lStrRequestForSearch = StringUtility.getParameter("requestForSearch", request).trim();

			lStrSevarthId = StringUtility.getParameter("txtSevaarthId",
					request).trim();
		
			lStrEmpName = StringUtility.getParameter("txtEmployeeName",
						request);

			lLstEmpDeptn = lObjTreasuryDAO.getEmpDeptn(lStrQueryString,lStrSevarthId,lStrEmpName);

			inputMap.put("EMPDEPUTNLIST", lLstEmpDeptn);
			inputMap.put("REASONLIST", lLstReasons);
			inputMap.put("OFFICELIST", lLstOffice);
			inputMap.put("QueryString", lStrQueryString);

			objRes.setResultValue(inputMap);
			objRes.setViewName("DCPSEmpDeputationInfo");

		}

		catch (Exception e) {
			e.printStackTrace();
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
	 * com.tcs.sgv.dcps.service.TreasuryService#dcpsEmpDeputation(java.util.Map)
	 */
	public ResultObject dcpsEmpDeputation(Map inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		String lStrSevarthId = null;
		TreasuryDAO lObjTreasuryDAO = null;
		String lStrQueryString = null;
		String lStrRequestForSearch = null;
		StringBuilder lStrBldXML = null;
		String lStrEmpName = null;
		List lLstEmpDeptun = null;
		List<CmnLookupMst> lLstReasons = null;
		List lLstOffice = null;
		Boolean lBlSucceccFlag = null;
		MstEmp lObjMstEmp = null;
		HstEmpDeputation lObjHstEmpDeputation = null;
		Long lLongPKValue = null;

		try {
			setSessionInfo(inputMap);
			lStrQueryString = StringUtility.getParameter("hidString", request).trim();
			lStrRequestForSearch = StringUtility.getParameter("requestForSearch", request).trim();

				if (lStrQueryString.equals("Attach")) {

					lObjTreasuryDAO = new TreasuryDAOImpl(null, serv
							.getSessionFactory());
					NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(
							MstEmp.class, serv.getSessionFactory());

					lObjMstEmp = null;
					Long lLngDeputationEmpId = null;
					
					String deputnIds = StringUtility.getParameter("deputnIds",
							request);
					String[] strArrPKValue = deputnIds.split("~");

					List<HstEmpDeputation> lListHstEmpDeputationVO = (List<HstEmpDeputation>) inputMap
							.get("lLstHstEmpDeputationVO");

					for (Integer lInt = 0; lInt < strArrPKValue.length; lInt++) {
						
						lLongPKValue = Long.valueOf(strArrPKValue[lInt]);
						
						// PK = 0 means save, Else update.

						if(lLongPKValue == 0l)	
						{
							lLngDeputationEmpId = IFMSCommonServiceImpl.getNextSeqNum("hst_dcps_emp_deputation",
									inputMap);
							lListHstEmpDeputationVO.get(lInt).setHstdcpsEmpDeptnId(lLngDeputationEmpId);
							lObjTreasuryDAO.create(lListHstEmpDeputationVO.get(lInt));
						}
						else
						{
							lLngDeputationEmpId = lLongPKValue;
							lListHstEmpDeputationVO.get(lInt).setHstdcpsEmpDeptnId(lLngDeputationEmpId);
							lObjTreasuryDAO.update(lListHstEmpDeputationVO.get(lInt));
						}
						
						lObjMstEmp = (MstEmp) lObjNewRegDdoDAO
								.read(lListHstEmpDeputationVO.get(lInt)
										.getDcpsEmpId().getDcpsEmpId());
						lObjMstEmp.setBillGroupId(null);
						lObjMstEmp.setDdoCode(null);
						lObjMstEmp.setCurrOff(lListHstEmpDeputationVO.get(lInt)
								.getOfficeCode());
						lObjMstEmp.setEmpOnDeptn(1);
						lObjNewRegDdoDAO.update(lObjMstEmp);
					}
				}

				if (lStrQueryString.equals("Detach")) {

					lObjTreasuryDAO = new TreasuryDAOImpl(
							HstEmpDeputation.class, serv.getSessionFactory());
					String deputnIds = StringUtility.getParameter("deputnIds",
							request);
					String detachDates = StringUtility.getParameter(
							"detachDates", request);
					String empIds = StringUtility.getParameter("Emp_Id",
							request);
					String[] strArrPKValue = deputnIds.split("~");
					String[] strArrDetachDates = detachDates.split("~");
					String[] strArrEmpIds = empIds.split("~");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(
							MstEmp.class, serv.getSessionFactory());
					for (Integer index = 0; index < strArrPKValue.length; index++) {

						lLongPKValue = Long
								.parseLong(strArrPKValue[index]);
						lObjHstEmpDeputation = (HstEmpDeputation) lObjTreasuryDAO
								.read(lLongPKValue);
						lObjHstEmpDeputation.setDetachDate(sdf
								.parse(strArrDetachDates[index]));
						lObjHstEmpDeputation.setUpdatedUserId(gLngUserId);
						lObjHstEmpDeputation.setUpdatedPostId(gLngPostId);
						lObjHstEmpDeputation.setUpdatedDate(gDtCurDate);
						lObjTreasuryDAO.update(lObjHstEmpDeputation);
						
						lObjMstEmp = (MstEmp) lObjNewRegDdoDAO.read(Long
								.parseLong(strArrEmpIds[index]));
						lObjMstEmp.setCurrOff(null);
						lObjMstEmp.setEmpOnDeptn(0);
						lObjNewRegDdoDAO.update(lObjMstEmp);
					}

				}

				lBlSucceccFlag = true;

				lStrBldXML = getResponseXMLDoc(lStrQueryString, lBlSucceccFlag);
				gLogger.info(" lStrBldXML :: " + lStrBldXML);
				String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
						lStrBldXML.toString()).toString();
				inputMap.put("ajaxKey", lStrResult);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}

		return objRes;

	}

	private StringBuilder getResponseXMLDoc(String lStrQueryString,
			Boolean lBlSuccessFlag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC1>");
		lStrBldXML.append("  <QUERYSTRING>");
		lStrBldXML.append(lStrQueryString);
		lStrBldXML.append("  </QUERYSTRING>");
		lStrBldXML.append("  <SUCCESSFLAG>");
		lStrBldXML.append(lBlSuccessFlag);
		lStrBldXML.append("  </SUCCESSFLAG>");

		lStrBldXML.append("</XMLDOC1>");

		return lStrBldXML;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.TreasuryService#loadSixthPCArrearsYearlyTO(java
	 * .util.Map)
	 */
	public ResultObject loadSixthPCArrearsYearlyTO(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		List lListEmp = null;
		List lLstUpperUsers = null;
		String lStrStatusFlag = null;
		String lStrUserType = null;
		TreasuryDAO lObjTreasuryDAO = null;
		String lStrDdoCode = null;
		try {

			setSessionInfo(inputMap);
			lStrStatusFlag = StringUtility.getParameter("StatusFlag", request);
			lStrUserType = StringUtility.getParameter("UserType", request);
			lObjTreasuryDAO = new TreasuryDAOImpl(MstEmp.class, serv
					.getSessionFactory());
			List<ComboValuesVO> lLstAllForms = lObjTreasuryDAO
					.getAllDDOListForPhyFormRcvd(gStrPostId, lStrUserType);
			Iterator it = lLstAllForms.iterator();
			ComboValuesVO tuple = new ComboValuesVO();
			int lIntLoopJ = 0;
			while (it.hasNext()) {
				tuple = (ComboValuesVO) it.next();
				lStrDdoCode = tuple.getId();
				lIntLoopJ++;
			}

			List lListYears = lObjTreasuryDAO.getYears();
			lLstUpperUsers = getHierarchyUsers(inputMap);

			if (StringUtility.getParameter("yearId", request) != null
					&& !(StringUtility.getParameter("yearId", request)
							.equalsIgnoreCase(""))) {
				Long yearId = Long.valueOf(StringUtility.getParameter("yearId",
						request));
				lListEmp = lObjTreasuryDAO.getEmpListForSixPCArrearsYearlyTO(
						lStrDdoCode, yearId, gStrPostId);
			}
			inputMap.put("lLstAllForms", lLstAllForms);
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
	 * @see
	 * com.tcs.sgv.dcps.service.TreasuryService#loadsixthPCYearlyInstallmentTO
	 * (java.util.Map)
	 */
	public ResultObject loadsixthPCYearlyInstallmentTO(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		List lListEmp = null;
		List lLstUpperUsers = null;
		String lStrStatusFlag = null;
		String lStrUserType = null;
		TreasuryDAO lObjTreasuryDAO = null;
		String lStrDdoCode = null;
		try {

			setSessionInfo(inputMap);
			lObjTreasuryDAO = new TreasuryDAOImpl(MstEmp.class, serv
					.getSessionFactory());
			lStrStatusFlag = StringUtility.getParameter("StatusFlag", request);
			lStrUserType = StringUtility.getParameter("UserType", request);
			List<ComboValuesVO> lLstAllForms = lObjTreasuryDAO
					.getAllDDOListForPhyFormRcvd(gStrPostId, lStrUserType);
			Iterator it = lLstAllForms.iterator();
			ComboValuesVO tuple = new ComboValuesVO();
			int lIntLoopJ = 0;
			while (it.hasNext()) {
				tuple = (ComboValuesVO) it.next();
				lStrDdoCode = tuple.getId();
				lIntLoopJ++;
			}

			List lListYears = lObjTreasuryDAO.getYears();
			lLstUpperUsers = getHierarchyUsers(inputMap);

			if (StringUtility.getParameter("yearId", request) != null
					&& !(StringUtility.getParameter("yearId", request)
							.equalsIgnoreCase(""))) {
				Long yearId = Long.valueOf(StringUtility.getParameter("yearId",
						request));
				lListEmp = lObjTreasuryDAO.getEmpListForSixPCArrearsYearlyTO(
						lStrDdoCode, yearId, gStrPostId);
			}
			inputMap.put("lLstAllForms", lLstAllForms);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("StatusFlag", lStrStatusFlag);
			inputMap.put("UserType", lStrUserType);
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
	 * com.tcs.sgv.dcps.service.TreasuryService#approveSixthPCArrearsYearlyByTO
	 * (java.util.Map)
	 */
	public ResultObject approveSixthPCArrearsYearlyByTO(Map objectArgs) {

		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String sixthPCId = null;
		ArrearsDAO lObjArrearsDAO = null;
		String subjectName = null;
		try {

			setSessionInfo(objectArgs);
			subjectName = gObjRsrcBndle.getString("DCPS.SixthPCArrears");
			String strPKValue = StringUtility.getParameter("SixthPC_Id",
					request).toString().trim();
			String[] strArrPKValue = strPKValue.split("~");

			objectArgs.put("FromPostId", gStrPostId);
			objectArgs.put("SendNotification", "This is not valid");
			objectArgs.put("jobTitle", subjectName);
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle
					.getString("DCPS.SixthPCArrearsID")));

			lObjArrearsDAO = new ArrearsDAOImpl(RltDcpsSixPCYearly.class, serv
					.getSessionFactory());
			for (int index = 0; index < strArrPKValue.length; index++) {
				sixthPCId = strArrPKValue[index];
				objectArgs.put("Pkvalue", sixthPCId);
				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				RltDcpsSixPCYearly lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjArrearsDAO
						.read(lLongPKValue);
				lObjRltDcpsSixPCYearly.setStatusFlag('A');
				lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
				lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
				lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
				// lObjRltDcpsSixPCYearly.set

			}

			objectArgs.put("ajaxKey", "Success");
			resObj.setViewName("ajaxData");
			resObj.setResultValue(objectArgs);

		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.TreasuryService#rejectSixthPCArrearsYearlyByTO
	 * (java.util.Map)
	 */
	public ResultObject rejectSixthPCArrearsYearlyByTO(Map objectArgs) {

		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String sixthPCId = null;
		ArrearsDAO lObjArrearsDAO = null;
		String subjectName = null;
		try {

			setSessionInfo(objectArgs);
			subjectName = gObjRsrcBndle.getString("DCPS.SixthPCArrears");
			String strPKValue = StringUtility.getParameter("SixthPC_Id",
					request).toString().trim();
			String[] strArrPKValue = strPKValue.split("~");

			objectArgs.put("FromPostId", gStrPostId);
			objectArgs.put("SendNotification", "This is not valid");
			objectArgs.put("jobTitle", subjectName);
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle
					.getString("DCPS.SixthPCArrearsID")));

			lObjArrearsDAO = new ArrearsDAOImpl(RltDcpsSixPCYearly.class, serv
					.getSessionFactory());
			for (int index = 0; index < strArrPKValue.length; index++) {
				sixthPCId = strArrPKValue[index];
				objectArgs.put("Pkvalue", sixthPCId);
				// WorkFlowDelegate.create(objectArgs);
				WorkFlowDelegate.returnDoc(objectArgs);
				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				RltDcpsSixPCYearly lObjRltDcpsSixPCYearly = (RltDcpsSixPCYearly) lObjArrearsDAO
						.read(lLongPKValue);
				lObjRltDcpsSixPCYearly.setStatusFlag('R');
				lObjRltDcpsSixPCYearly.setUpdatedUserId(gLngUserId);
				lObjRltDcpsSixPCYearly.setUpdatedPostId(gLngPostId);
				lObjRltDcpsSixPCYearly.setUpdatedDate(gDtCurDate);
				// lObjRltDcpsSixPCYearly.set

			}

			objectArgs.put("ajaxKey", "Success");
			resObj.setViewName("ajaxData");
			resObj.setResultValue(objectArgs);

		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		return resObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.TreasuryService#loadDummyOffice(java.util.Map)
	 */
	public ResultObject loadDummyOffice(Map inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		TreasuryDAO lObjTreasuryDAO = null;
		List lLstDummyOfficeDtls = null;
		try {
			setSessionInfo(inputMap);

			lObjTreasuryDAO = new TreasuryDAOImpl(TreasuryServiceImpl.class,
					serv.getSessionFactory());
			lLstDummyOfficeDtls = lObjTreasuryDAO.getDummyOfficesList(inputMap);
			inputMap.put("DummyOfficeList", lLstDummyOfficeDtls);

			objRes.setResultValue(inputMap);
			objRes.setViewName("DCPSDummyOffice");

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Exception occured in loadDummyOffice exception is "
					+ e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}

		return objRes;
	}

	public ResultObject loadDummyOfficeEntry(Map inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		TreasuryDAO lObjTreasuryDAO = null;
		List listAdminDept = null;
		List lLstDistricts = null;
		MstDummyOffice lObjMstDummyOffice = null;
		Long lLongOfficeId = null;
		String lStrOfficeId = null;
		try {
			setSessionInfo(inputMap);

			lObjTreasuryDAO = new TreasuryDAOImpl(TreasuryServiceImpl.class,
					serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			listAdminDept = lObjDcpsCommonDAO.getAllDepartment(Long
					.parseLong(gObjRsrcBndle.getString("DCPS.DEPARTMENTID")),
					gLngLangId);

			if (!StringUtility.getParameter("dummyOfficeId", request)
					.equalsIgnoreCase("")
					&& StringUtility.getParameter("dummyOfficeId", request) != null) {

				lStrOfficeId = StringUtility.getParameter("dummyOfficeId",
						request);
				lObjMstDummyOffice = lObjTreasuryDAO
						.getDummyOfficeInfo(lStrOfficeId);

			} else {
				lLongOfficeId = IFMSCommonServiceImpl.getNextSeqNum(
						"mst_dcps_dummy_office", inputMap);
				lStrOfficeId = lLongOfficeId.toString();
			}

			lLstDistricts = lObjDcpsCommonDAO.getDistricts(15L);

			// List lLstTalukas = lObjDcpsCommonDAO.getTaluka(lStrCurrDst);

			inputMap.put("lObjMstDummyOffice", lObjMstDummyOffice);
			inputMap.put("OfficeId", lStrOfficeId);
			inputMap.put("listAdminDept", listAdminDept);
			inputMap.put("lLstDistricts", lLstDistricts);
			objRes.setResultValue(inputMap);
			objRes.setViewName("DCPSDummyOfficeEntry");

		} catch (Exception e) {
			e.printStackTrace();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.service.TreasuryService#insertDummyOffice(java.util.Map)
	 */
	public ResultObject insertDummyOffice(Map inputMap) throws Exception {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		TreasuryDAO lObjTreasuryDAO = null;
		MstDummyOffice lObjMstDummyOfficeVO = new MstDummyOffice();

		try {
			setSessionInfo(inputMap);
			String strTransMode = (String) inputMap.get("Mode");
			if (strTransMode.equalsIgnoreCase("Add")) {
				if (lObjMstDummyOfficeVO != null) {
					lObjMstDummyOfficeVO = (MstDummyOffice) inputMap
							.get("lObjMstDummyOfficeVO");

					lObjTreasuryDAO = new TreasuryDAOImpl(MstDummyOffice.class,
							serv.getSessionFactory());
					Integer saveOrUpdateFlag = Integer.parseInt(StringUtility
							.getParameter("saveOrUpdateFlag", request));

					if (saveOrUpdateFlag == 1) {
						lObjTreasuryDAO.create(lObjMstDummyOfficeVO);
					}
					if (saveOrUpdateFlag == 2) {
						lObjTreasuryDAO.update(lObjMstDummyOfficeVO);
					}
				}

			}

			String lSBStatus = getResponseXMLDoc(true,
					Long.valueOf(lObjMstDummyOfficeVO.getDummyOfficeId()))
					.toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject loadChallanForDummy(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		TreasuryDAO lObjTreasuryDAO = null;
		DcpsCommonDAO lObjDcpsCommonDAO = null;
		List lLstOffice = null;
		String lStrDummyOfficeId = null;
		List lListEmployeesFromDummyOffice = null;
		Long monthId = null;
		Long yearId = null;

		try {
			setSessionInfo(inputMap);

			lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			lObjTreasuryDAO = new TreasuryDAOImpl(TreasuryServiceImpl.class,
					serv.getSessionFactory());

			lLstOffice = lObjTreasuryDAO.getDummyOffices();

			if (!StringUtility.getParameter("dummyOfficeId", request)
					.equals("")
					&& StringUtility.getParameter("dummyOfficeId", request) != null) {
				lStrDummyOfficeId = StringUtility.getParameter("dummyOfficeId",
						request);

				monthId = Long.valueOf(StringUtility.getParameter("payMonth",
						request));
				yearId = Long.valueOf(StringUtility.getParameter("payYear",
						request));

				lListEmployeesFromDummyOffice = lObjTreasuryDAO
						.getEmployeesFromDummyOffice(lStrDummyOfficeId,monthId,yearId);

			}

			List lLstMonths = lObjDcpsCommonDAO.getMonths();
			List lLstYears = lObjDcpsCommonDAO.getYears();

			inputMap.put("lListEmployeesFromDummyOffice",
					lListEmployeesFromDummyOffice);
			inputMap.put("dummyOfficeId", lStrDummyOfficeId);
			inputMap.put("monthId", monthId);
			inputMap.put("yearId", yearId);
			inputMap.put("MONTHS", lLstMonths);
			inputMap.put("YEARS", lLstYears);
			inputMap.put("OFFICELIST", lLstOffice);

			resObj.setResultValue(inputMap);
			resObj.setViewName("DCPSChallanEntry");

		}

		catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject saveContriForDeptn(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		Boolean lBlFlag = null;
		Long lLongDeptnContriId = null;
		Long lLongVoucherNo = null;
		Date lDateVoucherDate = null;
		Long lLongVoucherNoEmplr = null;
		Date lDateVoucherDateEmplr = null;
		Double lDoubleVoucherAmount = null;
		Double lDoubleVoucherAmountEmplr = null;

		try {
			setSessionInfo(inputMap);

			TrnDcpsDeputationContribution[] lArrTrnDcpsDeputationContribution = (TrnDcpsDeputationContribution[]) inputMap
					.get("lArrTrnDcpsDeputationContribution");

			TreasuryDAO lObjTreasuryDAO = new TreasuryDAOImpl(
					TreasuryServiceImpl.class, serv.getSessionFactory());

			for (Integer lInt = 0; lInt < lArrTrnDcpsDeputationContribution.length; lInt++) {

				lBlFlag = false;
				lLongDeptnContriId = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_DEPUTATION_CONTRIBUTION", inputMap);
				lArrTrnDcpsDeputationContribution[lInt].setDcpsDeptnContriId(lLongDeptnContriId);
				lObjTreasuryDAO.create(lArrTrnDcpsDeputationContribution[lInt]);
				lBlFlag = true;
				if (lBlFlag) {
					inputMap.put("currentDeptnCntrbtnVO",lArrTrnDcpsDeputationContribution[lInt]);
					
					lLongVoucherNo = Long.valueOf(lArrTrnDcpsDeputationContribution[lInt].getChallanNo());
					lDateVoucherDate = lArrTrnDcpsDeputationContribution[lInt].getChallanDate();
					lLongVoucherNoEmplr = Long.valueOf(lArrTrnDcpsDeputationContribution[lInt].getChallanNoEmplr());
					lDateVoucherDateEmplr =  lArrTrnDcpsDeputationContribution[lInt].getChallanDateEmplr();
					lDoubleVoucherAmount = lArrTrnDcpsDeputationContribution[lInt].getContribution();
					lDoubleVoucherAmountEmplr = lArrTrnDcpsDeputationContribution[lInt].getContributionEmplr();
					
					inputMap.put("lLongVoucherNo", lLongVoucherNo);
					inputMap.put("lDateVoucherDate", lDateVoucherDate);
					inputMap.put("lLongVoucherNoEmplr", lLongVoucherNoEmplr);
					inputMap.put("lDateVoucherDateEmplr", lDateVoucherDateEmplr);
					inputMap.put("lDoubleVoucherAmount",lDoubleVoucherAmount);
					inputMap.put("lDoubleVoucherAmountEmplr",lDoubleVoucherAmountEmplr);
					
					resObj = serv.executeService("insertCntrbtnForDeptn",
							inputMap);
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

	public ResultObject insertCntrbtnForDeptn(Map objectArgs) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Long dcpsEmpId = null;
		MstEmp lObjMstEmp = null;
		TrnDcpsContribution lObjTrnDcpsContribution = null;
		TrnDcpsDeputationContribution lObjTrnDcpsDeputationContribution = (TrnDcpsDeputationContribution) objectArgs
				.get("currentDeptnCntrbtnVO");

		try {

			setSessionInfo(objectArgs);

			TreasuryDAO lObjTreasuryDAO = new TreasuryDAOImpl(MstEmp.class,serv.getSessionFactory());

			dcpsEmpId = Long.valueOf(lObjTrnDcpsDeputationContribution.getDcpsEmpId());

			lObjMstEmp = (MstEmp) lObjTreasuryDAO.read(dcpsEmpId);
			
			Long lLngDcpsContriVoucherId = IFMSCommonServiceImpl.getNextSeqNum("mst_dcps_contri_voucher_dtls", objectArgs);
			MstDcpsContriVoucherDtls lObjMstDcpsContriVoucherDtls = new MstDcpsContriVoucherDtls();
			lObjMstDcpsContriVoucherDtls.setDcpsContriVoucherDtlsId(lLngDcpsContriVoucherId);
			//lObjMstDcpsContriVoucherDtls.setBillGroupId(lObjMstEmp.getBillGroupId());
			//lObjMstDcpsContriVoucherDtls.setDdoCode(lObjMstEmp.getDdoCode());
			lObjMstDcpsContriVoucherDtls.setCreatedDate(gDtCurrDt);
			lObjMstDcpsContriVoucherDtls.setPostId(gLngPostId);
			lObjMstDcpsContriVoucherDtls.setUserId(gLngUserId);
			lObjMstDcpsContriVoucherDtls.setVoucherNo(Long.valueOf(objectArgs.get("lLongVoucherNo").toString()));
			lObjMstDcpsContriVoucherDtls.setVoucherDate((Date) objectArgs.get("lDateVoucherDate"));
			lObjMstDcpsContriVoucherDtls.setEmplrVoucherNo(Long.valueOf(objectArgs.get("lLongVoucherNoEmplr").toString()));
			lObjMstDcpsContriVoucherDtls.setEmplrVoucherDate((Date) objectArgs.get("lDateVoucherDateEmplr"));
			lObjMstDcpsContriVoucherDtls.setTreasuryCode(lObjTrnDcpsDeputationContribution.getLocId());
			lObjMstDcpsContriVoucherDtls.setYearId(lObjTrnDcpsDeputationContribution.getFinYearId());
			lObjMstDcpsContriVoucherDtls.setMonthId(lObjTrnDcpsDeputationContribution.getMonthId());
			lObjMstDcpsContriVoucherDtls.setManuallyMatched(0l);
			lObjMstDcpsContriVoucherDtls.setPostEmplrContriStatus(0l);
			lObjMstDcpsContriVoucherDtls.setVoucherAmount((Double) objectArgs.get("lDoubleVoucherAmount"));
			lObjMstDcpsContriVoucherDtls.setEmplrVoucherAmount((Double) objectArgs.get("lDoubleVoucherAmountEmplr"));
			lObjTreasuryDAO.create(lObjMstDcpsContriVoucherDtls);
			
			// Only Employee Contribution is saved. Employer contribution is not saved.
			
			lObjTrnDcpsContribution = new TrnDcpsContribution();
			Long lLongTempdcpsContributionId = IFMSCommonServiceImpl.getNextSeqNum("TRN_DCPS_CONTRIBUTION", objectArgs);
			lObjTrnDcpsContribution.setDcpsContributionId(lLongTempdcpsContributionId);
			lObjTrnDcpsContribution.setDcpsEmpId(dcpsEmpId);
			lObjTrnDcpsContribution.setRltContriVoucherId(lLngDcpsContriVoucherId);
			lObjTrnDcpsContribution.setTreasuryCode(lObjTrnDcpsDeputationContribution.getLocId());
			
			// Below 3 details are not stored for a deputed employee, so commented.
		/*	
		
			lObjTrnDcpsContribution.setDdoCode(lObjMstEmp.getDdoCode());
			lObjTrnDcpsContribution.setDcpsDdoBillGroupId(lObjMstEmp.getBillGroupId());
			lObjTrnDcpsContribution.setSchemeCode(lObjTreasuryDAO.getSchemeCodeForBillGroupId(lObjMstEmp.getBillGroupId()));
		*/
			
			lObjTrnDcpsContribution.setTypeOfPayment("Deputation"); // This
			lObjTrnDcpsContribution.setPayCommission(lObjMstEmp.getPayCommission());
			lObjTrnDcpsContribution.setFinYearId(lObjTrnDcpsDeputationContribution.getFinYearId());
			lObjTrnDcpsContribution.setMonthId(lObjTrnDcpsDeputationContribution.getMonthId());
			lObjTrnDcpsContribution.setContribution(lObjTrnDcpsDeputationContribution.getContribution());
			lObjTrnDcpsContribution.setStartDate(gDtCurrDt);// This field to be
			lObjTrnDcpsContribution.setEndDate(gDtCurrDt);// This field to be
			lObjTrnDcpsContribution.setLangId(gLngLangId);
			lObjTrnDcpsContribution.setLocId(lLngLocId);
			lObjTrnDcpsContribution.setDbId(gLngDBId);
			lObjTrnDcpsContribution.setCreatedPostId(gLngPostId);
			lObjTrnDcpsContribution.setCreatedUserId(gLngUserId);
			lObjTrnDcpsContribution.setCreatedDate(gDtCurrDt);

			lObjTreasuryDAO.create(lObjTrnDcpsContribution);
			
			objectArgs.put("ajaxKey", "Success");
			resObj.setViewName("ajaxData");
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			ex.printStackTrace();
			return resObj;
		}
		return resObj;
	}

	public ResultObject saveTreasuryNetExcel(Map<Object, Object> inputMap) {

		Object[] xlsData = null;
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			inputMap.put("lLngPostId", gLngPostId);
			Long CreatedPostId = SessionHelper.getPostId(inputMap);
			Long CreatedUserId = SessionHelper.getUserId(inputMap);
			Date CreatedDate = DBUtility.getCurrentDateFromDB();

			// Code For Attachement starts
			Long lObjAttachmentId = null;
			Map attachMap = new HashMap();
			objRes = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);
			objRes = serv.executeService("FILE_UPLOAD_SRVC", inputMap);

			attachMap = (Map) objRes.getResultValue();

			if (attachMap != null) {
				if (attachMap.get("AttachmentId_scan") != null) {
					lObjAttachmentId = Long.parseLong(String.valueOf(attachMap
							.get("AttachmentId_scan")));
				}
				if (lObjAttachmentId != null) {
					CmnAttachmentMst attachmentMst = new CmnAttachmentMst();
					attachmentMst.setAttachmentId(lObjAttachmentId);
				}
			}

			// Code For Attachement ends

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if (lObjAttachmentId != null) {
				CmnAttachmentMstDAOImpl mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(
						CmnAttachmentMst.class, serv.getSessionFactory());
				CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO
						.findByAttachmentId(lObjAttachmentId);
				Iterator lObjIterator = cmnAttachmentMst.getCmnAttachmentMpgs()
						.iterator();
				TreasuryDAO dcpsTreasuryDao = new TreasuryDAOImpl(
						MstDcpsTreasurynetData.class, serv.getSessionFactory());
				MstDcpsTreasurynetData objMstDcpsTreasurynetData = null;
				Long lLngTreasuryNetDataId = null;
				int ddoCodeLength = 0;
				String ddoCode = null;
				while (lObjIterator != null && lObjIterator.hasNext()) {
					CmnAttachmentMpg cmnAttachmentMpg = (CmnAttachmentMpg) lObjIterator
							.next();
					CmnAttdocMst cmnAttDocMst = (CmnAttdocMst) cmnAttachmentMpg
							.getCmnAttdocMsts().iterator().next();

					if (cmnAttDocMst != null) {
						List lObjSheetLst = getDocumentForXLS(cmnAttDocMst,
								request);

						if (lObjSheetLst != null && !lObjSheetLst.isEmpty()) {
							List lObjRowLst = (List) lObjSheetLst.get(0);
							if (lObjRowLst != null && !lObjRowLst.isEmpty()) {
								for (int i = 1; i < lObjRowLst.size(); ++i) {
									xlsData = ((List) lObjRowLst.get(i))
											.toArray();
									objMstDcpsTreasurynetData = new MstDcpsTreasurynetData();
									lLngTreasuryNetDataId = IFMSCommonServiceImpl
											.getNextSeqNum(
													"mst_dcps_treasurynet_Data",
													inputMap);
									ddoCode = xlsData[2].toString();
									ddoCodeLength = xlsData[2].toString()
											.length();
									if (ddoCodeLength > 0 && ddoCodeLength < 6) {
										for (int j = 0; j < (6 - ddoCodeLength); j++) {
											ddoCode = "0" + ddoCode;
										}
									}

									objMstDcpsTreasurynetData
											.setDcpsTreasurynetDataId(lLngTreasuryNetDataId);
									objMstDcpsTreasurynetData
											.setTreasuryCode(Long
													.parseLong((String) xlsData[1]));
									objMstDcpsTreasurynetData
											.setYearDesc((String) xlsData[0]);
									objMstDcpsTreasurynetData
											.setDdoCode(xlsData[1].toString()
													+ ddoCode);
									objMstDcpsTreasurynetData
											.setVorc(((String) xlsData[3])
													.charAt(0));
									objMstDcpsTreasurynetData.setBillNo(Long
											.parseLong((String) xlsData[4]));
									objMstDcpsTreasurynetData.setBillDate(sdf
											.parse((String) xlsData[5]));
									objMstDcpsTreasurynetData.setVoucherNo(Long
											.parseLong((String) xlsData[6]));
									objMstDcpsTreasurynetData
											.setVoucherDate(sdf
													.parse((String) xlsData[7]));
									objMstDcpsTreasurynetData
											.setBillAmount(Double
													.parseDouble((String) xlsData[8]));
									objMstDcpsTreasurynetData
											.setFromScheme((String) xlsData[9]);
									objMstDcpsTreasurynetData
											.setToScheme((String) xlsData[10]);
									objMstDcpsTreasurynetData
											.setDcpsAmount(Double
													.parseDouble((String) xlsData[11]));
									objMstDcpsTreasurynetData
											.setCreatedPostId(CreatedPostId);
									objMstDcpsTreasurynetData
											.setCreatedUserId(CreatedUserId);
									objMstDcpsTreasurynetData
											.setCreatedDate(CreatedDate);
									dcpsTreasuryDao
											.create(objMstDcpsTreasurynetData);
								}

							}
						}
					}
				}

			}
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		} catch (Exception e) {

			gLogger.error(" Error is : " + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}

		return objRes;
	}

	private static List getDocumentForXLS(CmnAttdocMst cmnAttDocMst,
			HttpServletRequest request) throws Exception {

		List lLstReturn = null;
		File lObjFile = null;

		try {

			/** Getting path of temp directory in server. **/
			String serverPathStr = request.getSession().getServletContext()
					.getRealPath("UPLOADED-FILES");

			lObjFile = new File(serverPathStr);
			if (!lObjFile.exists()) {
				lObjFile.mkdir();
			}

			String tempFilePath = serverPathStr
					+ (lObjFile.separator.equals("\\") ? ("\\tempFile_")
							: "tempFile_") + System.currentTimeMillis();
			/** Made one file with name tempFile_currentTimeMillis(). **/
			lObjFile = new File(tempFilePath);
			/** Getting file as a output stream. **/
			FileOutputStream lobjFileOutputStream = new FileOutputStream(
					tempFilePath);
			/** write the attachment blob data into temp file. **/
			lobjFileOutputStream.write(cmnAttDocMst.getFinalAttachment(), 0,
					cmnAttDocMst.getFinalAttachment().length);
			lobjFileOutputStream.flush();
			/** Close the output stream. **/
			lobjFileOutputStream.close();
			/** Parse your Excel file, it will return list of list. **/
			lLstReturn = ExcelParser.parseExcel(lObjFile);

		} catch (Exception e) {
			throw (e);
		} finally {
			if (lObjFile != null) {
				/** Delete the temp file from temp directory in server. **/
				lObjFile.delete();
			}
		}

		return lLstReturn;
	}
}
