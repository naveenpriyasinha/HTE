package com.tcs.sgv.dcps.service;

//com.tcs.sgv.dcps.service.DCPSNewRegistrationServiceImpl
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.math.BigDecimal;
import com.tcs.sgv.acl.valueobject.AclPostroleRlt;
import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.Qualification;
import com.tcs.sgv.common.dao.QualificationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.helper.WorkFlowHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.PasswordEncryption;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.AttachmentHelper;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoBillGroupDAO;
import com.tcs.sgv.dcps.dao.DdoBillGroupDAOImpl;
import com.tcs.sgv.dcps.dao.DdoProfileDAO;
import com.tcs.sgv.dcps.dao.DdoProfileDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAO;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAOImpl;
import com.tcs.sgv.dcps.dao.OfflineContriDAO;
import com.tcs.sgv.dcps.dao.OfflineContriDAOImpl;
import com.tcs.sgv.dcps.valueobject.DcpsCadreMst;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.HstEmp;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDdoAsst;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.fms.valueobject.WfHierachyPostMpg;
import com.tcs.sgv.wf.delegate.WorkFlowDelegate;

public class NewRegDdoServiceImpl extends ServiceImpl implements NewRegDdoService
{

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
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");

	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap)
	{

		try
		{
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
		}
		catch (Exception e)
		{

		}

	}

	public ResultObject viewApprovedForms(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
		try
		{

			setSessionInfo(inputMap);
			long locId = Long.parseLong(loginMap.get("locationId").toString());
			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			//commented by rosahn
			//String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			//added by roshan to find Loggedin DDO Code.
			PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
			OrgDdoMst ddoMst  = null;
			if(ddoList!=null && ddoList.size()>0) {
				 ddoMst = ddoList.get(0);
			}
			//ended
			String lStrDdoCode = null;
			if(ddoMst!=null)
				lStrDdoCode = ddoMst.getDdoCode();
			
			List empList = lObjNewRegDdoDAO.getApprovedFormsForDDO(lStrDdoCode);
			inputMap.put("empList", empList);

			resObj.setResultValue(inputMap);
			resObj.setViewName("NewRegApprovedForms");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject viewFormsForwardedByAsst(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{
			//gLogger.info("hi i am kinjal in service new for approve");
			setSessionInfo(inputMap);

			String lStrUserZP = "ReportingDDO";
			String lStrUseZP = "Approval";
			//String lStrUseZP = "";

			inputMap.put("User", lStrUserZP.trim());
			inputMap.put("Use", lStrUseZP.trim());
			gLogger.info("hi i am kinjal in service"+lStrUserZP);
			gLogger.info("hi i am kinjal in service"+lStrUseZP);
			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			inputMap.put("DDOCODE", lStrDdoCode);

			//String ATOPostIdForDDO = null;
			//List UserList = getHierarchyUsersZP(inputMap);

			//ATOPostIdForDDO = UserList.get(0).toString();

			/*
			List lListFormsForATORejection = lObjNewRegDdoDAO
					.getApprovalByDDODatesforAll(lStrDdoCode, ATOPostIdForDDO);

			
			SimpleDateFormat lObjSimpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			Object[] lObjArr = null;
			List lLongListEmpIds = new ArrayList();
			List lDateListApprovalByDDO = new ArrayList();
			List lLongListEmpIdsForRejection = new ArrayList();
			Integer lIntDaysDifference = null;

			for (Integer lInt = 0; lInt < lListFormsForATORejection.size(); lInt++) {
				lObjArr = (Object[]) lListFormsForATORejection.get(lInt);
				if (lObjArr[1] == null) {
					continue;
				}
				lLongListEmpIds.add(Long.valueOf(lObjArr[0].toString()));
				lDateListApprovalByDDO.add(lObjArr[1]);
				lIntDaysDifference = daysBetween(lObjSimpleDateFormat
						.parse(lObjArr[1].toString()), gDtCurDate);
				if (lIntDaysDifference > 30) {
					lLongListEmpIdsForRejection.add(Long.valueOf(lObjArr[0]
							.toString()));
				}
			}

			inputMap.put("empIdsForPhysicalFormsNotReceived",
					lLongListEmpIdsForRejection);
			resObj = serv.executeService("rejectRequestForPhyFormNotRcvd",
					inputMap);
			
			*/

			//commented by roshan
			//List empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP);
			//added by vaibhav tyagi: start
			String ddoSelected=null;
			String reptddoSelected=null;
			if((StringUtility.getParameter("ddoCode", request).trim()!=null)||(StringUtility.getParameter("ddoCode", request).trim()!="")||(Long.parseLong(StringUtility.getParameter("ddoCode", request).trim())!=-1)){
				ddoSelected = StringUtility.getParameter("ddoCode", request).trim();
			}
			List asstDDO= lObjNewRegDdoDAO.getAllAsstDDOList(lStrDdoCode);
			inputMap.put("asstDDO", asstDDO);
			inputMap.put("ddoSelected", ddoSelected);
			List empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP,reptddoSelected,ddoSelected);
			//added by vaibhav tyagi: end
			inputMap.put("empList", empList);

			inputMap.put("EditForm", "N");

			resObj.setResultValue(inputMap);
			resObj.setViewName("NewRegForwardedForms");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject viewDraftForms(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List empList = null;

		try
		{

			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrUserZP = StringUtility.getParameter("User", request).trim();
			inputMap.put("User", lStrUserZP);

			String lStrUseZP = "";
			inputMap.put("Use", lStrUseZP);

			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			inputMap.put("DDOCODE", lStrDdoCode);

			List lLstParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);

			List lLstDesignation = lObjDcpsCommonDAO.getAllDesignation((Long) objParentDept[0], gLngLangId);
			inputMap.put("lLstDesignation", lLstDesignation);

			String lStrSearchCriteria = StringUtility.getParameter("searchCriteria", request);
			String lStrSearchValue = StringUtility.getParameter("searchValue", request);

			if (!lStrSearchCriteria.equals(""))
			{
				if (lStrSearchCriteria.equals("Designation"))
				{
					empList = lObjNewRegDdoDAO.getAllDcpsEmployeesForDesig(lStrUserZP, gStrPostId, lStrDdoCode, lStrSearchValue);
					inputMap.put("DesignationId", lStrSearchValue.trim());
					inputMap.put("CaseStatus", "");
				}
				if (lStrSearchCriteria.equals("Case Status"))
				{
					empList = lObjNewRegDdoDAO.getAllDcpsEmployeesForCaseStatus(lStrUserZP, gStrPostId, lStrDdoCode, lStrSearchValue);
					inputMap.put("CaseStatus", lStrSearchValue.trim());
					inputMap.put("DesignationId", "");
				}
				inputMap.put("SearchCriteria", lStrSearchCriteria.trim());
			}
			else
			{
				//commented by vaibhav tyagi
				//empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP);
				//added by vaibhav tyagi: start
				String reptddoSelected=null;
				System.out.println("lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP,reptddoSelected,lStrDdoCode "
				+ lStrUserZP+" , "+gStrPostId+" , "+lStrDdoCode+" , "+ lStrUseZP+" , "+reptddoSelected+" , "+lStrDdoCode);
				empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP,reptddoSelected,lStrDdoCode);
				//added by vaibhav tyagi: endinputMap.put("DesignationId", "");
				inputMap.put("DesignationId", "");
				inputMap.put("CaseStatus", "");
				inputMap.put("SearchCriteria", "");
			}

			String isDcpsGrAvl = lObjDcpsCommonDAO.getGrType(lStrDdoCode);
			System.out.println("---- isDcpsGrAvl---" + isDcpsGrAvl);

			inputMap.put("isDcpsGrAvl", isDcpsGrAvl);
			inputMap.put("empList", empList);
			inputMap.put("EditForm", "Y");

			resObj.setResultValue(inputMap);
			resObj.setViewName("NewRegDrafts");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject loadRegistrationForm(Map inputMap) throws Exception  // find by teju for hour basic 
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		//gLogger.info("loadRegistrationForm inside1 ");

		try
		{

			setSessionInfo(inputMap);

			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			QualificationDAOImpl quadDAO = new QualificationDAOImpl(Qualification.class, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			if (request != null)
			{
				String lStrEmpId = StringUtility.getParameter("empId", request);
				if (!lStrEmpId.equals(""))
				{
					Long lLngEmpID = Long.parseLong(lStrEmpId);
					MstEmp MstEmpObj = (MstEmp) lObjNewRegDdoDAO.read(lLngEmpID);
					inputMap.put("lObjEmpData", MstEmpObj);
				}
			}
			String lStrDdoCode = null;
			//String lStrUser = StringUtility.getParameter("User", request).trim();
			String lStrUser = "ZPDDOAsst";
			inputMap.put("User", lStrUser);

			inputMap.put("EditForm", "Y");
			lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			inputMap.put("DDOCODE", lStrDdoCode);

			// Get Employee code for New Registration

			// Long lLongDcpsEmpIdNewReg =
			// IFMSCommonServiceImpl.getNextSeqNum(
			// "MST_DCPS_EMP", inputMap);

			// Get the StateNames
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			inputMap.put("STATENAMES", lLstState);

			// Get the office list from the database
			List listOfficeNames = lObjDcpsCommonDAO.getCurrentOffices(lStrDdoCode);
			inputMap.put("OFFICELIST", listOfficeNames);

			//Get Qualification 
			List qualificationDetails = quadDAO.getQualification();
			inputMap.put("QualificationList", qualificationDetails);

			// Get the Bank Names
			List lLstBankNames = lObjDcpsCommonDAO.getBankNames();
			inputMap.put("BANKNAMES", lLstBankNames);
			
			//get Appointment List
		    List lLstppointments = lObjDcpsCommonDAO.getAppointment();
		    inputMap.put("Appointments", lLstppointments);
		    
		  

			// Get Class List from lookup Master
			List listCityClass = IFMSCommonServiceImpl.getLookupValues("DCPS_OFFICE_CLASS", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listCityClass", listCityClass);

			// Get Salutation List from Lookup Master
			List listSalutation = IFMSCommonServiceImpl.getLookupValues("Salutation", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listSalutation", listSalutation);

			// Get type of Pay Commission from lookup Master
			List listPayCommission = IFMSCommonServiceImpl.getLookupValues("PayCommissionDCPS", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listPayCommission", listPayCommission);

			// Get Type of RelationList from lookup Master
			List listRelationship = IFMSCommonServiceImpl.getLookupValues("RelationList", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listRelationship", listRelationship);

			// Set the current date for validation of date of birth
			Date lDtcurDate = SessionHelper.getCurDate();
			inputMap.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));

			// Set the date of joining limit as 01-Nov-2005
			inputMap.put("lDtJoiDtLimit", "01/11/2005");

			// Get PF Account Maintained from lookup Master
			List lLstPFAccntMntdBy = IFMSCommonServiceImpl.getLookupValues("AccountMaintaindedBy", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("lLstPFAccntMntdBy", lLstPFAccntMntdBy);

			// Get AcDcpsMaintainedBy from Lookup Master
			List lLstPFAccntMntdByDCPS = IFMSCommonServiceImpl.getLookupValues("AccountMaintainedByForDCPSEmp", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("lLstPFAccntMntdByDCPS", lLstPFAccntMntdByDCPS);

			DdoProfileDAO lObjDdoProfileDAO = new DdoProfileDAOImpl(null, serv.getSessionFactory());
			List lLstAllDesignations = lObjDdoProfileDAO.getAllDesignation(gLngLangId);
			inputMap.put("lLstAllDesignations", lLstAllDesignations);

			// Code Added to get all states List
			
			

			List lLstAllStates = lObjDcpsCommonDAO.getStates(1l);
			inputMap.put("lLstAllStates", lLstAllStates);

			List lStrParentDept = lObjDcpsCommonDAO.getParentDeptForDDO(lStrDdoCode);
			List listParentDept = null;

			if (lStrParentDept != null)
			{
				Object[] lListObj = (Object[]) lStrParentDept.get(0);

				Long ParentDeptId = Long.valueOf(lListObj[0].toString());
				String ParentDeptDesc = lListObj[1].toString();
				inputMap.put("ParentDeptIdByDefault", ParentDeptId);
				inputMap.put("ParentDeptDescByDefault", ParentDeptDesc);

				listParentDept = lObjDcpsCommonDAO.getAllHODDepartment(Long.parseLong(gObjRsrcBndle.getString("DCPS.CurrentFieldDeptID")), gLngLangId);
				inputMap.put("listParentDept", listParentDept);

				// Get the Cadre list from the database
				List listCadres = lObjDcpsCommonDAO.getCadreForDept(ParentDeptId);
				inputMap.put("CADRELIST", listCadres);

				List lLstDesignation = lObjDcpsCommonDAO.getDesigsForPFDAndCadre(ParentDeptId);

				inputMap.put("lLstDesignation", lLstDesignation);

			}

			String lStrLocationCode = lObjDcpsCommonDAO.getLocationCodeForDDO(lStrDdoCode);
			List UserList = getHierarchyUsersZP(inputMap, lStrLocationCode);
			inputMap.put("UserList", UserList);

			/*
			List ATOUserList = getAsstHierarchyUsers(inputMap);
			inputMap.put("ATOUserList", ATOUserList);
			*/

			/* Changes for GIS Details */
			List lLstGISDetails = IFMSCommonServiceImpl.getLookupValues("GISDetails", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("lLstGISDetails", lLstGISDetails);

			List lLstGISGroup = IFMSCommonServiceImpl.getLookupValues("GISGroup", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("lLstGISGroup", lLstGISGroup);

			/* Changes for GIS Details ends */
			/*Changes for DCPS yes/No */

			String isDcpsGrAvl = lObjDcpsCommonDAO.getGrType(lStrDdoCode);
			System.out.println("---- isDcpsGrAvl---" + isDcpsGrAvl);
			inputMap.put("isDcpsGrAvl", isDcpsGrAvl);

			resObj.setResultValue(inputMap);
			resObj.setViewName("NewRegFormZP");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	/**
	 * service method to used to populate the Combo Boxes when Page is Loaded
	 * 
	 * @param Map
	 *            <String,Object> inputMap
	 * @return ResultObject
	 */

	public ResultObject popUpEmpDtls(Map inputMap) throws Exception
	{
		gLogger.error(" INSIDE  popUpEmpDtls: ");

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		String lStrEmpId = null;
		MstEmp MstEmpObj = null;
		DcpsCadreMst lObjMstCadre = null;
		RltDcpsPayrollEmp RltDcpsPayrollEmpObj = null;
		Set<CmnAttachmentMpg> cmnAttachmentMpgs = null;
		Long lLngPhotoAttachmentId = null;
		Long lLngSignAttachmentId = null;
		Iterator<CmnAttachmentMpg> cmnAttachmentMpgIterator = null;
		CmnAttachmentMpg cmnAttachmentMpg = null;
		Long lLngFieldDept = null;

		String LocZP = null;

		try
		{
			setSessionInfo(inputMap);

			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			NewRegDdoDAO lObjNewRegDdoDAOForCadre = new NewRegDdoDAOImpl(DcpsCadreMst.class, serv.getSessionFactory());
			NewRegDdoDAO lObjNewRegPayrollDdoDAO = new NewRegDdoDAOImpl(RltDcpsPayrollEmp.class, serv.getSessionFactory());
			QualificationDAOImpl quadDAO = new QualificationDAOImpl(Qualification.class, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			if (request != null)
			{
				lStrEmpId = StringUtility.getParameter("empId", request).trim();

				NewRegDdoDAOImpl lObjNewRegDDODAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
				List lListLocationCode = lObjNewRegDDODAO.getLocationCodeForward(lStrEmpId);
				LocZP = (String) lListLocationCode.get(0);

				if (!lStrEmpId.equals(""))
				{
					Long lLngEmpID = Long.parseLong(lStrEmpId);
					MstEmpObj = (MstEmp) lObjNewRegDdoDAO.read(lLngEmpID);
					inputMap.put("lObjEmpData", MstEmpObj);
					inputMap.put("empGenderEmp", MstEmpObj.getGender().toString());
					inputMap.put("dcpsOrGpf", MstEmpObj.getDcpsOrGpf().toString());

					if (lObjNewRegDdoDAO.checkDcpsEmpPayrollIdForEmpIdExists(lLngEmpID))
					{
						Long lLngDcpsPayrollId = lObjNewRegDdoDAO.getDcpsEmpPayrollIdForEmpId(lLngEmpID);
						RltDcpsPayrollEmpObj = (RltDcpsPayrollEmp) lObjNewRegPayrollDdoDAO.read(lLngDcpsPayrollId);
						inputMap.put("lObjEmpPayrollData", RltDcpsPayrollEmpObj);
						
						
					}

				}
			}

			SimpleDateFormat lObjSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			String lStrDobEmp = lObjSimpleDateFormat.format(MstEmpObj.getDob());
			Long cadre = 0l;
			gLogger.error(" INSIDE 3 popUpEmpDtls: ");
			
			lLngFieldDept = Long.parseLong(MstEmpObj.getParentDept());
			List lLstDesignation = lObjDcpsCommonDAO.getDesigsForPFDAndCadre(lLngFieldDept);
			inputMap.put("lLstDesignation", lLstDesignation);

			if (MstEmpObj.getCadre() != null && !MstEmpObj.getCadre().equalsIgnoreCase(""))
			{
				gLogger.error(" INSIDE 2 popUpEmpDtls: ");

				cadre = Long.valueOf(MstEmpObj.getCadre());
				lObjMstCadre = (DcpsCadreMst) lObjNewRegDdoDAOForCadre.read(cadre);
				inputMap.put("SuperAnnAge", lObjMstCadre.getSuperAntunAge());

				String lStrGroupName = lObjDcpsCommonDAO.getCmnLookupNameFromId(Long.valueOf(lObjMstCadre.getGroupId().trim()));
				inputMap.put("GroupName", lStrGroupName.trim());
				/*if (MstEmpObj.getParentDept() != null)
				{
					gLogger.error(" INSIDE 3 popUpEmpDtls: ");
					lLngFieldDept = Long.parseLong(MstEmpObj.getParentDept());
					List lLstDesignation = lObjDcpsCommonDAO.getDesigsForPFDAndCadre(lLngFieldDept);
					inputMap.put("lLstDesignation", lLstDesignation);
				}*/
				// inputMap.put("GroupName", lObjMstCadre.getGroupId());

				//List lLstppointments = lObjDcpsCommonDAO.getAppointment();
			    //inputMap.put("Appointments", lLstppointments);
			    
			   
				/*
				String lStrWithoutYear = lStrDobEmp.substring(0, 6);
				Long SuperAnnuationAge = lObjMstCadre.getSuperAntunAge();
				Long lLongBirthYear = Long.valueOf(lStrDobEmp.substring(6));
				Long lLongRetiringYear = lLongBirthYear + SuperAnnuationAge;
				String lStrRetiringYear = lStrWithoutYear
						+ lLongRetiringYear.toString();
				*/

				String lStrRetiringYear = null;
				if (MstEmpObj.getSuperAnndate() != null)
				{
					if (!"".equals(lStrRetiringYear))
					{
						lStrRetiringYear = lObjSimpleDateFormat.format(MstEmpObj.getSuperAnndate());
					}
				}

				inputMap.put("SuperAnnDate", lStrRetiringYear);

				List lLstOfficesForPost = null;
				if (RltDcpsPayrollEmpObj.getPostId() != null && RltDcpsPayrollEmpObj.getPostId() != -1)
				{
					lLstOfficesForPost = lObjDcpsCommonDAO.getOfficesForPost(RltDcpsPayrollEmpObj.getPostId());
				}
				inputMap.put("lLstOfficesForPost", lLstOfficesForPost);
			}
			
			List lLstppointments = null;
			if(MstEmpObj.getEmpAppointment()!=null){
				lLstppointments = lObjDcpsCommonDAO.getAppointment();
			}
			inputMap.put("Appointments", lLstppointments);

			/*
			 * Check if Pay commission is added, then get list of concerned
			 * payscales
			 */

			List PayScaleList = null;
			if (MstEmpObj.getPayCommission() != null && !MstEmpObj.getPayCommission().equalsIgnoreCase(""))
			{

				if (!MstEmpObj.getPayCommission().equals("700337"))
				{
					Map voToServiceMap = new HashMap();
					voToServiceMap.put("commissionId", MstEmpObj.getPayCommission());

					inputMap.put("voToServiceMap", voToServiceMap);

					resObj = serv.executeService("GetScalefromDesg", inputMap);
					PayScaleList = (List) inputMap.get("PayScaleList");
				}
				inputMap.put("PayScaleList", PayScaleList);
			}

			if (MstEmpObj.getDesignation() != null && !MstEmpObj.getDesignation().equalsIgnoreCase(""))
			{
				Map voToServiceMap = new HashMap();

				voToServiceMap.put("dsgnId", MstEmpObj.getDesignation());
				inputMap.put("voToServiceMap", voToServiceMap);
				resObj = serv.executeService("GetPostfromDesignation", inputMap);

				List CurrentPostList = (List) inputMap.get("CurrentPostList");
				inputMap.put("CurrentPostList", CurrentPostList);
				Long postId=RltDcpsPayrollEmpObj.getPostId();
				String postDesc=lObjNewRegDdoDAO.getPostDesc(postId);
				inputMap.put("postDesc", postDesc);
			}

			/* code for accessing Ddo Office VO from Office Id */

			if (MstEmpObj.getCurrOff() != null)
			{
				Long lLongDdoOfficeId = Long.valueOf(MstEmpObj.getCurrOff());
				DdoOffice lObjDdoOfficeVO = lObjNewRegDdoDAO.getDdoOfficeVO(lLongDdoOfficeId);
				inputMap.put("lObjDdoOfficeVO", lObjDdoOfficeVO);
			}

			// Added for viewing Photo and signature
			CmnAttachmentMstDAO lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
			CmnAttachmentMst lObjCmnAttachmentMst = null;

			if (MstEmpObj.getPhotoAttachmentID() != null && MstEmpObj.getPhotoAttachmentID().doubleValue() > 0)
			{
				lObjCmnAttachmentMst = new CmnAttachmentMst();
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO.findByAttachmentId(Long.parseLong(MstEmpObj.getPhotoAttachmentID().toString()));

				cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();

				if (lObjCmnAttachmentMst != null)
				{
					lLngPhotoAttachmentId = lObjCmnAttachmentMst.getAttachmentId();
				}
				if (lObjCmnAttachmentMst != null && lObjCmnAttachmentMst.getCmnAttachmentMpgs() != null)
				{
					cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();
				}
				cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
				Long srNo = 0L;
				for (Integer lInt = 0; lInt < cmnAttachmentMpgs.size(); lInt++)
				{
					cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
					if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("photo"))
					{
						srNo = cmnAttachmentMpg.getSrNo();
						inputMap.put("Photo", lObjCmnAttachmentMst);
						inputMap.put("PhotoId", lLngPhotoAttachmentId);
						inputMap.put("PhotosrNo", srNo);
					}
				}
			}

			if (MstEmpObj.getSignatureAttachmentID() != null && MstEmpObj.getSignatureAttachmentID().doubleValue() > 0)
			{
				lObjCmnAttachmentMst = new CmnAttachmentMst();
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO.findByAttachmentId(Long.parseLong(MstEmpObj.getSignatureAttachmentID().toString()));

				cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();

				if (lObjCmnAttachmentMst != null)
				{
					lLngSignAttachmentId = lObjCmnAttachmentMst.getAttachmentId();
				}
				if (lObjCmnAttachmentMst != null && lObjCmnAttachmentMst.getCmnAttachmentMpgs() != null)
				{
					cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();
				}

				cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
				Long srNo = 0L;
				for (Integer lInt = 0; lInt < cmnAttachmentMpgs.size(); lInt++)
				{
					cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
					if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("signature"))
					{
						srNo = cmnAttachmentMpg.getSrNo();
						inputMap.put("Sign", lObjCmnAttachmentMst);
						inputMap.put("SignId", lLngSignAttachmentId);
						inputMap.put("SignsrNo", srNo);
					}
				}
			}

			// Added for viewing photo and signature overs
			// Get the StateNames

			List<MstEmpNmn> NomineesList = lObjNewRegDdoDAO.getNominees(lStrEmpId);
			inputMap.put("NomineesList", NomineesList);

			Integer lIntTotalNominees = NomineesList.size();
			inputMap.put("lIntTotalNominees", lIntTotalNominees);

			String lStrDdoCode = null;
			String lStrUser = StringUtility.getParameter("User", request).trim();
			String lStrUse = StringUtility.getParameter("Use", request).trim();
			String lStrZPFormStatus = StringUtility.getParameter("ZPFormStatus", request).trim();
			inputMap.put("User", lStrUser);
			inputMap.put("Use", lStrUse);
			inputMap.put("ZPFormStatus", lStrZPFormStatus);

			if (lStrUser.equals("ZPDDO") || lStrUser.equals("ReportingDDO") || lStrUser.equals("FinalDDO") || lStrUser.equals("SpecialDDO"))
			{

				inputMap.put("EditForm", "N");
				//lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
				//inputMap.put("DDOCODE", lStrDdoCode);

			}

			else if (lStrUser.equals("ZPDDOAsst"))
			{

				inputMap.put("ZPDDOAsst", "user");
				inputMap.put("EditForm", "Y");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				inputMap.put("DDOCODE", lStrDdoCode);

			}

			/* Changes for GIS Details */
			List lLstGISDetails = IFMSCommonServiceImpl.getLookupValues("GISDetails", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("lLstGISDetails", lLstGISDetails);

			List lLstGISGroup = IFMSCommonServiceImpl.getLookupValues("GISGroup", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("lLstGISGroup", lLstGISGroup);

			/* Changes for GIS Details ends */

			String lStrFromSearch = StringUtility.getParameter("FromSearchEmp", request);

			inputMap.put("lStrFromSearch", lStrFromSearch);

			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			inputMap.put("STATENAMES", lLstState);

			// Get the office list from the database
			List listOfficeNames = lObjDcpsCommonDAO.getCurrentOffices(lStrDdoCode);
			inputMap.put("OFFICELIST", listOfficeNames);

			// Get Salutation List from Lookup Master
			List listSalutation = IFMSCommonServiceImpl.getLookupValues("Salutation", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listSalutation", listSalutation);

			lLngFieldDept = Long.parseLong(MstEmpObj.getParentDept());
			// Long lLngPayCmnId = Long.parseLong(MstEmpObj.getPayCommission());

			List listRelationship = IFMSCommonServiceImpl.getLookupValues("RelationList", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listRelationship", listRelationship);

			// Get the Bank Names
			List lLstBankNames = lObjDcpsCommonDAO.getBankNames();
			inputMap.put("BANKNAMES", lLstBankNames);

			// Get the BankBranchNames
			if (MstEmpObj.getBankName() != null)
			{
				List lLstBrachNames = lObjDcpsCommonDAO.getBranchNames(Long.valueOf(MstEmpObj.getBankName()));
				inputMap.put("BRANCHNAMES", lLstBrachNames);
			}
			
			List qualificationDetails = quadDAO.getQualification();
			inputMap.put("QualificationList", qualificationDetails);

			// Get the list of all  states
			List lLstAllStates = lObjDcpsCommonDAO.getStates(1l);
			inputMap.put("lLstAllStates", lLstAllStates);

			// Get the Cadre list from the database
			List listCadres = lObjDcpsCommonDAO.getCadreForDept(lLngFieldDept);
			inputMap.put("CADRELIST", listCadres);

			List listParentDept = lObjDcpsCommonDAO.getAllHODDepartment(Long.parseLong(gObjRsrcBndle.getString("DCPS.CurrentFieldDeptID")), gLngLangId);
			inputMap.put("listParentDept", listParentDept);

			List listPayCommission = IFMSCommonServiceImpl.getLookupValues("PayCommissionDCPS", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listPayCommission", listPayCommission);

			// Set the current date for validation of date of birth
			Date lDtcurDate = SessionHelper.getCurDate();
			inputMap.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));

			// Set the date of joining limit as 01-Nov-2005
			inputMap.put("lDtJoiDtLimit", "01/11/2005");

			// Get PF Account Maintained from lookup Master
			List lLstPFAccntMntdBy = IFMSCommonServiceImpl.getLookupValues("AccountMaintaindedBy", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("lLstPFAccntMntdBy", lLstPFAccntMntdBy);

			// Get AcDcpsMaintainedBy from Lookup Master
			List lLstPFAccntMntdByDCPS = IFMSCommonServiceImpl.getLookupValues("AccountMaintainedByForDCPSEmp", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("lLstPFAccntMntdByDCPS", lLstPFAccntMntdByDCPS);

			// Get PF Series from lookup Master
			List lLstPFSeries = null;
			String lStrAcMntndBy = RltDcpsPayrollEmpObj.getAcMaintainedBy();
			String MumbaiOrNagpurAG = null;
			if (lStrAcMntndBy != null && !lStrAcMntndBy.equals(""))
			{
				if (lStrAcMntndBy.equals("700092"))
				{
					lLstPFSeries = IFMSCommonServiceImpl.getLookupValues("PF_Series", SessionHelper.getLangId(inputMap), inputMap);
					MumbaiOrNagpurAG = "Yes";
				} // PF Series for AG Mumbai
				else if (lStrAcMntndBy.equals("700093"))
				{
					lLstPFSeries = IFMSCommonServiceImpl.getLookupValues("PF_Series_AG_Nagpur", SessionHelper.getLangId(inputMap), inputMap);
					MumbaiOrNagpurAG = "Yes";
				} // PF Series for AG Nagpur
				else
				{
					MumbaiOrNagpurAG = "No";
				}
			}
			inputMap.put("lLstPFSeries", lLstPFSeries);
			inputMap.put("MumbaiOrNagpurAG", MumbaiOrNagpurAG);

			DdoProfileDAO lObjDdoProfileDAO = new DdoProfileDAOImpl(null, serv.getSessionFactory());
			List lLstAllDesignations = lObjDdoProfileDAO.getAllDesignation(gLngLangId);
			inputMap.put("lLstAllDesignations", lLstAllDesignations);

			List UserList = getHierarchyUsersZP(inputMap, LocZP);
			inputMap.put("UserList", UserList);

			/*
			List ATOUserList = getAsstHierarchyUsers(inputMap);
			inputMap.put("ATOUserList", ATOUserList);
			*/

			// Get the type of user
			String isDcpsGrAvl="2";
			if(lStrDdoCode!=null)
				isDcpsGrAvl = lObjDcpsCommonDAO.getGrType(lStrDdoCode);
			//System.out.println("---- isDcpsGrAvl---" + isDcpsGrAvl);
			inputMap.put("isDcpsGrAvl", isDcpsGrAvl);
			
			resObj.setResultValue(inputMap);
			//resObj.setViewName("DCPSRegistrationForm");
			resObj.setViewName("NewRegFormZP");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject viewAttachmentForDCPS(Map mp)
	{

		ResultObject objRes = new ResultObject(-1, "FAIL");

		try
		{
			setSessionInfo(mp);
			StringBuilder lStrData = new StringBuilder();
			byte allBytesInBlob[] = new byte[0];
			objRes = new ResultObject(0, "FAIL");
			Map fileItemArrayListMap = AttachmentHelper.fileItemArrayListMap;
			String key = "";
			String rowNumber = request.getParameter("rowNumber");
			String attachmentName = request.getParameter("attachmentUniqeName");
			Integer rowIndex = 0;
			if (request.getParameter("rowIndex") != null)
			{
				rowIndex = Integer.parseInt(request.getParameter("rowIndex"));
			}
			Integer rowCount = 0;
			if (request.getParameter("rowCount") != null)
			{
				rowCount = Integer.parseInt(request.getParameter("rowCount"));
			}
			rowIndex -= rowCount;
			if (rowNumber != null && rowNumber.length() >= 1)
			{
				key = (new StringBuilder(String.valueOf(request.getSession().getAttribute("name")))).append(attachmentName).append(request.getSession().getId()).append(rowNumber).toString();
			}
			else
			{
				key = (new StringBuilder(String.valueOf(request.getSession().getAttribute("name")))).append(attachmentName).append(request.getSession().getId()).toString();
			}
			ArrayList attachmentList = (ArrayList) fileItemArrayListMap.get(key);
			if (attachmentList != null && !attachmentList.isEmpty())
			{
				FileItem fileItem = (FileItem) attachmentList.get(attachmentList.size() - 1);
				allBytesInBlob = fileItem.get();
			}
			mp.put("buteArray", allBytesInBlob);
			lStrData.append("<XMLDOC>");
			lStrData.append("<HEADDESC>");
			lStrData.append(allBytesInBlob);
			lStrData.append("</HEADDESC>");
			lStrData.append("</XMLDOC>");

			objRes.setResultValue(mp);
			objRes.setViewName("viewAttachment");
			//System.out.println("End of Service to view");
		}
		catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);

		}
		return objRes;
	}

	public ResultObject saveDCPSEmpData(Map inputMap)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		MstEmp lObjEmpData = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;
		Boolean lBlFlag;
		Long lLngEmpId = 0l;
		Long lLngDcpsPayrollEmpId = 0l;

		try
		{
			setSessionInfo(inputMap);

			lObjEmpData = new MstEmp();
			lObjEmpData = (MstEmp) inputMap.get("DCPSEmpData");
			lBlFlag = false;

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			objRes = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);

			objRes = serv.executeService("FILE_UPLOAD_SRVC", inputMap);

			Map attachMap = (Map) objRes.getResultValue();

			Long lLngAttachId = 0L;
			if (attachMap.get("AttachmentId_Photo") != null)
			{
				lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_Photo")));
				lObjEmpData.setPhotoAttachmentID(lLngAttachId);
			}

			if (attachMap.get("AttachmentId_Sign") != null)
			{
				lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_Sign")));
				lObjEmpData.setSignatureAttachmentID(lLngAttachId);
			}

			lLngEmpId = IFMSCommonServiceImpl.getNextSeqNum("MST_DCPS_EMP", inputMap);
			lObjEmpData.setDcpsEmpId(lLngEmpId);
			lObjEmpData.setSevarthId(lLngEmpId.toString());
			lObjNewRegDdoDAO.create(lObjEmpData);

			lObjRltDcpsPayrollEmp = new RltDcpsPayrollEmp();
			lObjRltDcpsPayrollEmp = (RltDcpsPayrollEmp) inputMap.get("DCPSEmpPayrollData");
			lLngDcpsPayrollEmpId = IFMSCommonServiceImpl.getNextSeqNum("RLT_DCPS_PAYROLL_EMP", inputMap);
			lObjRltDcpsPayrollEmp.setDcpsEmpId(lLngEmpId);
			lObjRltDcpsPayrollEmp.setDcpsPayrollEmpId(lLngDcpsPayrollEmpId);
			lObjNewRegDdoDAO.create(lObjRltDcpsPayrollEmp);
			lBlFlag = true;

			// Long lFormStatus = lObjEmpData.getFormStatus();
			// Create the workflow only when it is a new request
			// if (lFormStatus == 1)
			// { createWF(inputMap); }

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			gLogger.error(" Error is : " + ex, ex);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			return resObj;
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag, lObjEmpData.getDcpsEmpId()).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		gLogger.error("Ajax result is " + lStrResult);
		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;

	}

	/**
	 * 
	 * Service to update the Employee's DCPS Data
	 * 
	 * @author Kapil Devani
	 * @param inputMap
	 * @return
	 */
	public ResultObject updateDCPSEmpData(Map inputMap)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		MstEmp lObjEmpUpdatedData = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;
		Boolean lBlFlag;
		Boolean lBlPayrollVOExistsOrNot = true;
		RltDcpsPayrollEmp lObjTempRltDcpsPayrollEmp = null;

		try
		{
			setSessionInfo(inputMap);

			lObjEmpUpdatedData = new MstEmp();
			lObjEmpUpdatedData = (MstEmp) inputMap.get("DCPSEmpData");

			lObjRltDcpsPayrollEmp = (RltDcpsPayrollEmp) inputMap.get("DCPSEmpPayrollData");

			lBlFlag = false;

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			resObj = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);

			resObj = serv.executeService("FILE_UPLOAD_SRVC", inputMap);

			Map attachMap = (Map) resObj.getResultValue();

			Long lLngAttachId = 0L;
			if (attachMap.get("AttachmentId_Photo") != null)
			{
				lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_Photo")));
				lObjEmpUpdatedData.setPhotoAttachmentID(lLngAttachId);
			}

			if (attachMap.get("AttachmentId_Sign") != null)
			{
				lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_Sign")));
				lObjEmpUpdatedData.setSignatureAttachmentID(lLngAttachId);
			}

			lObjNewRegDdoDAO.update(lObjEmpUpdatedData);

			lObjTempRltDcpsPayrollEmp = lObjNewRegDdoDAO.getPayrollVOForEmpId(lObjEmpUpdatedData.getDcpsEmpId());

			if (lObjTempRltDcpsPayrollEmp == null)
			{
				lBlPayrollVOExistsOrNot = false;
			}

			if (lBlPayrollVOExistsOrNot)
			{
				lObjNewRegDdoDAO.update(lObjRltDcpsPayrollEmp);
			}

			Long lLngFormStatus = lObjEmpUpdatedData.getFormStatus();
			if (lLngFormStatus == 1L)
			{
				createWF(inputMap);
			}

			lBlFlag = true;

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is : " + ex, ex);
			return resObj;
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag, lObjEmpUpdatedData.getDcpsEmpId()).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;

	}

	/*
	 * Method to generate the xml response for Ajax
	 */

	/**
	 * service method to used to populate the Designations names Combo box
	 * according to the selected Current Office
	 * 
	 * @param Map
	 *            <String,Object> lMapInputMap
	 * @return ResultObject
	 */

	public ResultObject populateDesigsUsingAjax(Map<String, Object> lMapInputMap)
	{

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			setSessionInfo(lMapInputMap);

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrCurrOffice = StringUtility.getParameter("cmbCurrentOffice", request);

			// Call the functions to get list of designations for the selected
			// office
			List lLstDesignations = lObjDcpsCommonDAO.getDesignations(lStrCurrOffice);

			String lStrTempResult = null;
			if (lLstDesignations != null)
			{
				lStrTempResult = new AjaxXmlBuilder().addItems(lLstDesignations, "desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

	/**
	 * service method to used to populate the Group name TextBox according to
	 * the selected Cadre
	 * 
	 * @param Map
	 *            <String,Object> inputMap
	 * @return ResultObject
	 */

	// Old One
	/*
		public ResultObject populateGroupUsingAjax(Map<String, Object> inputMap)
				throws Exception {

			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			String lStrGroupName = null;
			Long SuperAnnuationAge = null;
			String lStrRetiringYear = null;
			List finalList = null;
			Long lLongCadreId = null;
			// Get the group name for the cadre name selected
			try {

				setSessionInfo(inputMap);

				String lStrCadreId = StringUtility
						.getParameter("cmbCadre", request).trim();

				if (!lStrCadreId.equalsIgnoreCase("")) {
					lLongCadreId = Long.valueOf(lStrCadreId);
				}

				String lStrDobEmp = StringUtility.getParameter("dobEmp", request);
				String lStrWithoutYear = lStrDobEmp.substring(0, 6);

				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class,
						serv.getSessionFactory());

				finalList = lObjNewRegDdoDAO.getGroupName(lLongCadreId);

				Object[] groupAndSuperAnnAge = (Object[]) finalList.get(0);

				if (finalList != null && finalList.size() > 0) {

					lStrGroupName = groupAndSuperAnnAge[0].toString();
					SuperAnnuationAge = Long.valueOf(groupAndSuperAnnAge[1]
							.toString());
					Long lLongBirthYear = Long.valueOf(lStrDobEmp.substring(6));
					Long lLongRetiringYear = lLongBirthYear + SuperAnnuationAge;
					lStrRetiringYear = lStrWithoutYear
							+ lLongRetiringYear.toString();

				}
			} catch (Exception ex) {
				objRes.setResultValue(null);
				objRes.setThrowable(ex);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
				ex.printStackTrace();
				return objRes;
			}

			String lSBStatus = getResponseXMLDocForGroup(lStrGroupName,
					SuperAnnuationAge, lStrRetiringYear).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
					.toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");
			return objRes;

		}
		
	*/

	// New One Modified 
	public ResultObject populateGroupUsingAjax(Map<String, Object> inputMap) throws Exception
	{
		
		gLogger.error("populateGroupUsingAjax service ");

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		String lStrGroupName = null;
		Long SuperAnnuationAge = null;
		String lStrRetiringYear = null;
		List finalList = null;
		Long lLongCadreId = null;
		Date lDtDobEmp = null;
		Integer lIntDay = null;
		Integer lIntMonth = null;
		Integer lIntYear = null;
		String lStrDay = null;
		String lStrMonth = null;
		String lStrYear = null;

		// Get the group name for the cadre name selected
		try
		{

			setSessionInfo(inputMap);

			String lStrCadreId = StringUtility.getParameter("cmbCadre", request).trim();

			
			gLogger.error("lStrCadreId "+lStrCadreId);
			
			if (!lStrCadreId.equalsIgnoreCase(""))
			{
				lLongCadreId = Long.valueOf(lStrCadreId);
			}
			String lStrDobEmp = StringUtility.getParameter("dobEmp", request);
			gLogger.error("lStrDobEmp "+lStrDobEmp);
			if (!lStrDobEmp.equals(""))
			{

				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

				finalList = lObjNewRegDdoDAO.getGroupName(lLongCadreId);

				if (finalList != null && finalList.size() > 0)
				{

					Object[] groupAndSuperAnnAge = (Object[]) finalList.get(0);

					lStrGroupName = groupAndSuperAnnAge[0].toString();
					SuperAnnuationAge = Long.valueOf(groupAndSuperAnnAge[1].toString());

					String lArrStrDOB[] = lStrDobEmp.split("/");
					Long lLongBirthYear = Long.valueOf(lArrStrDOB[2]);
					Long lLongRetiringYear = lLongBirthYear + SuperAnnuationAge;

					lDtDobEmp = IFMSCommonServiceImpl.getDateFromString(lStrDobEmp);
					Calendar lCalendar = Calendar.getInstance();
					lCalendar.set(lLongRetiringYear.intValue(), lDtDobEmp.getMonth(), lDtDobEmp.getDate());
					lIntDay = lCalendar.get(lCalendar.DATE);
					lIntMonth = lCalendar.get(lCalendar.MONTH) + 1;
					lIntYear = lCalendar.get(lCalendar.YEAR);

					if (lIntDay.equals(1))
					{
						if (lIntMonth.equals(1))
						{
							lStrDay = "31";
							lStrMonth = "12";
							lStrYear = String.valueOf(lLongBirthYear - 1L + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(2))
						{
							lStrDay = "31";
							lStrMonth = "1";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(3))
						{
							lStrDay = String.valueOf((((lIntYear % 4 == 0) && ((!(lIntYear % 100 == 0)) || (lIntYear % 400 == 0))) ? 29 : 28));
							lStrMonth = "2";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(4))
						{
							lStrDay = "31";
							lStrMonth = "3";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(5))
						{
							lStrDay = "30";
							lStrMonth = "4";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(6))
						{
							lStrDay = "31";
							lStrMonth = "5";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(7))
						{
							lStrDay = "30";
							lStrMonth = "6";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(8))
						{
							lStrDay = "31";
							lStrMonth = "7";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(9))
						{
							lStrDay = "31";
							lStrMonth = "8";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(10))
						{
							lStrDay = "30";
							lStrMonth = "9";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(11))
						{
							lStrDay = "31";
							lStrMonth = "10";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
						else if (lIntMonth.equals(12))
						{
							lStrDay = "30";
							lStrMonth = "11";
							lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);
						}
					}
					else
					{
						lStrDay = String.valueOf(lCalendar.getActualMaximum(lCalendar.DAY_OF_MONTH));
						lStrMonth = lIntMonth.toString();
						lStrYear = String.valueOf(lLongBirthYear + SuperAnnuationAge);

					}
					lStrRetiringYear = lStrDay + "/" + lStrMonth + "/" + lStrYear;
				}
			}
		}
		catch (Exception ex)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			return objRes;
		}

		String lSBStatus = getResponseXMLDocForGroup(lStrGroupName, SuperAnnuationAge, lStrRetiringYear).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		objRes.setResultValue(inputMap);
		objRes.setViewName("ajaxData");
		return objRes;

	}

	public ResultObject getOfficeDetails(Map<String, Object> inputMap) throws Exception
	{

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		List finalList = null;

		try
		{

			setSessionInfo(inputMap);
			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			Long lLngOfficeId = Long.parseLong(StringUtility.getParameter("officeId", request).trim());

			finalList = lObjNewRegDdoDAO.getOfficeDetails(lLngOfficeId);

		}
		catch (Exception ex)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			//ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
			return objRes;
		}

		String lSBStatus = getResponseXMLDocForOffice(finalList).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		gLogger.error(" lStrResult is : " + lStrResult);
		inputMap.put("ajaxKey", lStrResult);
		objRes.setResultValue(inputMap);
		objRes.setViewName("ajaxData");
		return objRes;

	}

	/**
	 * service method to used to save DCPS Nominee Details into Databse
	 * 
	 * @param Map
	 *            <String,Object> inputMap
	 * @return ResultObject
	 */

	public ResultObject saveNomineeDetails(Map inputMap)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		Boolean lBlFlag;
		Integer SaveOrUpdateNominee;

		try
		{
			setSessionInfo(inputMap);

			MstEmpNmn[] lArrNomineeDtls = (MstEmpNmn[]) inputMap.get("DCPSNomineeDtls");

			lBlFlag = false;

			SaveOrUpdateNominee = (Integer) inputMap.get("SaveOrUpdateNominee");

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			// Save the data for nominees in the database

			if (SaveOrUpdateNominee == 1)
			{
				for (Integer lInt = 0; lInt < lArrNomineeDtls.length; lInt++)
				{
					Long lLngNmnId = IFMSCommonServiceImpl.getNextSeqNum("MST_DCPS_EMP_NMN", inputMap);
					lArrNomineeDtls[lInt].setDcpsEmpNmnId(lLngNmnId);
					lObjNewRegDdoDAO.create(lArrNomineeDtls[lInt]);
					lBlFlag = true;
				}

			}
			if (SaveOrUpdateNominee > 1)
			{

				// dcpsNewRegistrationDAO
				// .deleteNomineesForGivenEmployee(lLngEmpID);

				for (Integer lInt = 0; lInt < lArrNomineeDtls.length; lInt++)
				{
					Long lLngNmnId = IFMSCommonServiceImpl.getNextSeqNum("MST_DCPS_EMP_NMN", inputMap);
					lArrNomineeDtls[lInt].setDcpsEmpNmnId(lLngNmnId);
					lObjNewRegDdoDAO.create(lArrNomineeDtls[lInt]);
					lBlFlag = true;
				}

			}

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
			return resObj;
		}

		String lSBStatus = getResponseXMLDocForNominee(lBlFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	/**
	 * 
	 * <H3>Description -</H3> Method to populate the worklist of all saved cases
	 * for DDO Assistant
	 * 
	 * 
	 * @author Kapil Devani
	 * @param inputMap
	 * @return
	 * @throws Exception
	 */

	/*
	 * This method will get the list of hierarchy users at the next level
	 */

	private List getHierarchyUsers(Map inputMap)
	{

		List UserList = null;

		try
		{

			setSessionInfo(inputMap);
			Integer llFromLevelId = 0;
			UserList = new ArrayList<String>();

			// Get the Subject Name
			String subjectName = gObjRsrcBndle.getString("DCPS.RegistrationForm");

			// Get the Hierarchy Id
			NewRegDdoDAOImpl lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			Long lLngHierRefId;

			lLngHierRefId = WorkFlowHelper.getHierarchyByPostIDAndDescription(gStrPostId, subjectName, inputMap);

			// Get the From level Id
			llFromLevelId = WorkFlowHelper.getLevelFromPostMpg(gStrPostId, lLngHierRefId, inputMap);

			// Get the List of Post ID of the users at the next Level
			List rsltList = WorkFlowHelper.getUpperPost(gStrPostId, lLngHierRefId, llFromLevelId, inputMap);

			Object[] lObjNextPost = null;

			for (Integer lInt = 0; lInt < rsltList.size(); lInt++)
			{

				lObjNextPost = (Object[]) rsltList.get(lInt);

				if (!(lObjNextPost.equals(null)))
				{
					UserList.add(lObjNextPost[0].toString());
				}
			}

		}
		catch (Exception e)
		{
			//e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
		}
		return UserList;
	}

	private List getHierarchyUsersZP(Map inputMap, String LocZP)
	{

		List UserList = null;

		try
		{

			setSessionInfo(inputMap);
			Integer llFromLevelId = 0;
			UserList = new ArrayList<String>();

			// Get the Subject Name.......DCPS Registration Form
			String subjectName = gObjRsrcBndle.getString("DCPS.RegistrationForm");

			// Get the Hierarchy Id
			NewRegDdoDAOImpl lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			Long lLngHierRefId;
			if (LocZP != null && !LocZP.equalsIgnoreCase(""))
			{
				List HireRefID = lObjNewRegDdoDAO.getHirechyRefIDforForward(gStrPostId, subjectName, LocZP);
				//String lLngHierRefId1=(String)HireRefID.get(0);
				
				gLogger.info("HireRefID"+HireRefID.get(0));
				lLngHierRefId = Long.valueOf(HireRefID.get(0).toString());
				//lLngHierRefId=Long.valueOf(lLngHierRefId1);
			}
			else
			{
				lLngHierRefId = WorkFlowHelper.getHierarchyByPostIDAndDescription(gStrPostId, subjectName, inputMap);
			}

			// Get the From level Id
			llFromLevelId = WorkFlowHelper.getLevelFromPostMpg(gStrPostId, lLngHierRefId, inputMap);

			// Get the List of Post ID of the users at the next Level
			List rsltList = WorkFlowHelper.getUpperPost(gStrPostId, lLngHierRefId, llFromLevelId, inputMap);

			Object[] lObjNextPost = null;

			for (Integer lInt = 0; lInt < rsltList.size(); lInt++)
			{

				lObjNextPost = (Object[]) rsltList.get(lInt);

				if (!(lObjNextPost.equals(null)))
				{
					UserList.add(lObjNextPost[0].toString());
				}
			}

		}
		catch (Exception e)
		{
			//e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
		}
		return UserList;
	}

	/* Function to get hierarchy from Asst to ATo */
	private List getAsstHierarchyUsers(Map inputMap)
	{

		List UserList = null;

		try
		{

			setSessionInfo(inputMap);
			Integer llFromLevelId = 0;
			UserList = new ArrayList<String>();

			// Get the Subject Name
			String subjectName = gObjRsrcBndle.getString("DCPS.RegistrationForm");

			// Get the Hierarchy Id
			Long lLngHierRefId = WorkFlowHelper.getHierarchyByPostIDAndDescription(gStrPostId, subjectName, inputMap);

			// Get the From level Id
			llFromLevelId = 20;

			// Get the List of Post ID of the users at the next Level
			List rsltList = WorkFlowHelper.getUpperPost(gStrPostId, lLngHierRefId, llFromLevelId, inputMap);

			Object[] lObjNextPost = null;

			for (Integer lInt = 0; lInt < rsltList.size(); lInt++)
			{

				lObjNextPost = (Object[]) rsltList.get(lInt);

				if (!(lObjNextPost.equals(null)))
				{
					UserList.add(lObjNextPost[0].toString());
				}
			}

		}
		catch (Exception e)
		{
			//e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
		}
		return UserList;
	}

	/**
	 * 
	 * <H3>Description -</H3> Function to forward the request to DDO from DDO
	 * Asst.
	 * 
	 * 
	 * @author Kapil Devani
	 * @param objectArgs
	 * @return
	 */
	public ResultObject forwardRequestToDDO(Map objectArgs)
	{

		//gLogger.info("-----------------------------forwardRequestToDDO-----------------------------");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag;
		Long lLongDCPSEmpId;

		try
		{

			setSessionInfo(objectArgs);

			lLongDCPSEmpId = 0l;
			lBlFlag = false;

			String lStrApproveFlag = StringUtility.getParameter("flag", request).toString();
			String toPost = null;

			// ApproveToPost not used
			/*
			if (lStrApproveFlag.equals("1")) {
				toPost = StringUtility.getParameter("ApproveToPost", request)
						.toString();
			} else {
				toPost = StringUtility.getParameter("ForwardToPost", request)
						.toString();
			}
			*/
			toPost = StringUtility.getParameter("ForwardToPost", request).toString();

			String toLevel = gObjRsrcBndle.getString("DCPS.REPTDDO");
			String strPKValue = StringUtility.getParameter("Emp_Id", request).toString().trim();

			// Split the array to get the ID of forms selected
			String[] strArrPKValue = strPKValue.split("~");

			objectArgs.put("toPost", toPost);
			objectArgs.put("toPostId", toPost);
			objectArgs.put("toLevel", toLevel);

			objectArgs.put("jobTitle", gObjRsrcBndle.getString("DCPS.RegistrationForm"));
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.RegistrationFormID")));

			// Iterates more than 1 time if more than 1 form are selected
			for (Integer index = 0; index < strArrPKValue.length; index++)
			{
				objectArgs.put("Pkvalue", strArrPKValue[index]);

				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				MstEmp lObjDcpsEmpMst = (MstEmp) lObjNewRegDdoDAO.read(lLongPKValue);
				lObjDcpsEmpMst.setFormStatus(1L);
				lObjDcpsEmpMst.setRegStatus(0L);
				lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
				lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
				lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
				lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);
				if (lStrApproveFlag.equals("1"))
				{
					lObjDcpsEmpMst.setApprovalByDDODate(gDtCurrDt);
				}
				lLongDCPSEmpId = lObjDcpsEmpMst.getDcpsEmpId();

				Long lLongDraftOrRejected = 0l;
				if (!"".equals(StringUtility.getParameter("ZPFormStatus", request).trim()))
				{
					lLongDraftOrRejected = Long.valueOf(StringUtility.getParameter("ZPFormStatus", request).trim());
				}
				if (lLongDraftOrRejected == 0l && lObjDcpsEmpMst.getZpStatus() == 0l) //commented by teju for testing
				{
					createWF(objectArgs);
				}

				lObjDcpsEmpMst.setZpStatus(2l);

				lObjNewRegDdoDAO.update(lObjDcpsEmpMst);
				WorkFlowDelegate.forward(objectArgs);
				lBlFlag = true;
				// Update the Registration form status to 0 suggesting it is
				// in progress

				/*
				
				if (lStrApproveFlag.equals("1")
						&& lObjDcpsEmpMst.getDcpsOrGpf().equals('N')) {

					lObjDcpsEmpMst.setApprovalByDDODate(gDtCurrDt);
					lObjDcpsEmpMst.setRegStatus(2L);
					lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
					lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
					lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
					lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);

					objectArgs.put("dcpsEmpId", lLongPKValue);

					ResultObject objRes = serv.executeService("createEmployee",
							objectArgs);

					if (objRes.getResultCode() == ErrorConstants.ERROR) {

						throw new Exception();
					}

					Long lLngOrgEmpMstId = Long.parseLong(objectArgs.get(
							"orgEmpMstId").toString());
					lObjDcpsEmpMst.setOrgEmpMstId(lLngOrgEmpMstId);

					// objectArgs.put("EmpIdFromGPF", lLongPKValue);

					// resObj = serv.executeService("SIXPC_ARREARS_VOGEN",
					// objectArgs);

					// resObj = serv.executeService("SIXPC_ARREARS_SRVC",
					// objectArgs);
					String lStrDdoCode = lObjDcpsEmpMst.getDdoCode();

					HstEmp lObjHstEmp = new HstEmp();

					Long lLongHstEmpIdPk = IFMSCommonServiceImpl.getNextSeqNum(
							"hst_dcps_emp_details", objectArgs);

					lObjHstEmp.setHstdcpsId(lLongHstEmpIdPk);

					lObjHstEmp.setDbId(gLngDBId);
					lObjHstEmp.setDcpsEmpId(lLongPKValue);
					lObjHstEmp.setLocId(Long.parseLong(gStrLocationCode));
					lObjHstEmp.setDdoCode(lStrDdoCode);
					lObjHstEmp.setStartDate(gDtCurDate);
					lObjHstEmp.setCreatedUserId(gLngUserId);
					lObjHstEmp.setCreatedPostId(gLngPostId);
					lObjHstEmp.setCreatedDate(gDtCurDate);

					lObjNewRegDdoDAO.create(lObjHstEmp);

					// Archive Form 1 for GPF Employee
					NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(
							MstEmp.class, serv.getSessionFactory());
					lObjNewRegTreasuryDAO.ArchiveNewRegForm(lObjDcpsEmpMst,
							serv);

				} else {
					}
					*/

			}

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
			return resObj;
		}

		String lSBStatus = getResponseXMLDoc(lBlFlag, lLongDCPSEmpId).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setResultValue(objectArgs);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	/**
	 * <H3>Description -</H3>
	 * 
	 * 
	 * 
	 * @author Vihan Khatri
	 * @param objectArgs
	 */

	/**
	 * 
	 * Forwards the request to the treasury upon verification by DDO
	 * 
	 * 
	 * @author Kapil Devani
	 * @param objectArgs
	 * @return
	 */
	public ResultObject forwardRequestToTreasury(Map objectArgs)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		String strPKValue = null;
		Boolean lBlFlag = false;
		String lStrDcpsOrGpf = null;
		Long lLongHstEmpIdPk = null;
		String lStrDdoCode = null;
		String lStrSevarthId = "";
		Boolean lBlBillGroupFlag = false;
		gLogger.info("i am in forwardRequestToTreasury");
		try
		{

			setSessionInfo(objectArgs);
			String toPost = StringUtility.getParameter("ForwardToPost", request).toString();
			String toLevel = gObjRsrcBndle.getString("DCPS.REPTDDO");
			strPKValue = StringUtility.getParameter("Emp_Id", request).toString().trim();

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			Long lLongPKValue = Long.parseLong(strPKValue);
			MstEmp lObjDcpsEmpMst = (MstEmp) lObjNewRegDdoDAO.read(lLongPKValue);
			objectArgs.put("dcpsEmpId", lLongPKValue);
			Date lDtDOJDt = lObjDcpsEmpMst.getDoj();
			if (lObjDcpsEmpMst.getDcpsOrGpf().equals('Y'))
			{
				//gLogger.info("i am in forwardRequestToTreasury for dcps");
				lStrDcpsOrGpf = "DCPS";

				objectArgs.put("toPost", toPost);
				objectArgs.put("toPostId", toPost);
				objectArgs.put("toLevel", toLevel);

				objectArgs.put("jobTitle", gObjRsrcBndle.getString("DCPS.RegistrationForm"));
				objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.RegistrationFormID")));

				objectArgs.put("Pkvalue", strPKValue);
				WorkFlowDelegate.forward(objectArgs);

				lObjDcpsEmpMst.setApprovalByDDODate(gDtCurrDt);
				lObjDcpsEmpMst.setRegStatus(0L);
				lObjDcpsEmpMst.setPhyRcvdFormStatus(0l);
				lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
				lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
				lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
				lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);

				lObjDcpsEmpMst.setZpStatus(2L);

				lStrSevarthId = generateSeevarthIdNewLogic(objectArgs);

				lObjNewRegDdoDAO.update(lObjDcpsEmpMst);
			}
			if (lObjDcpsEmpMst.getDcpsOrGpf().equals('N'))
			{
				//gLogger.info("i am in forwardRequestToTreasury for gpf");
				lStrDcpsOrGpf = "GPF";

				lObjDcpsEmpMst.setApprovalByDDODate(gDtCurrDt);
				lObjDcpsEmpMst.setRegStatus(2L);
				lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
				lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
				lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
				lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);

				lObjDcpsEmpMst.setZpStatus(10L);

				lStrDdoCode = lObjDcpsEmpMst.getDdoCode();

				objectArgs.put("ddoCode", lStrDdoCode);

				ResultObject objRes = serv.executeService("createEmployee", objectArgs);

				if (objRes.getResultCode() == ErrorConstants.ERROR)
				{
					//gLogger.info("error aa gya");
					throw new Exception();
				}

				Long lLngOrgEmpMstId = Long.parseLong(objectArgs.get("orgEmpMstId").toString());
				lObjDcpsEmpMst.setOrgEmpMstId(lLngOrgEmpMstId);
				//gLogger.info("error nhi gya");
				String lStrBillGroupId = "";

				if (objectArgs.containsKey("billNo"))
				{
					if (objectArgs.get("billNo") != null)
					{
						if (!objectArgs.get("billNo").toString().equals(""))
						{
							lStrBillGroupId = objectArgs.get("billNo").toString();
							lBlBillGroupFlag = true;
						}
					}
				}

				lStrSevarthId = objectArgs.get("sevarthId").toString();
				
				gLogger.info("lStrSevarthId mil gya"+lStrSevarthId);
				Long lLongBillGroupId = null;

				if (!lStrBillGroupId.equals(""))
				{
					lLongBillGroupId = Long.valueOf(lStrBillGroupId);
				}

				lObjDcpsEmpMst.setBillGroupId(lLongBillGroupId);
				lObjDcpsEmpMst.setSevarthId(lStrSevarthId);
				lObjNewRegDdoDAO.update(lObjDcpsEmpMst);

				// objectArgs.put("EmpIdFromGPF", lLongPKValue);

				// resObj = serv.executeService("SIXPC_ARREARS_VOGEN",
				// objectArgs);

				// resObj = serv.executeService("SIXPC_ARREARS_SRVC",
				// objectArgs);

				HstEmp lObjHstEmp = new HstEmp();

				lLongHstEmpIdPk = IFMSCommonServiceImpl.getNextSeqNum("hst_dcps_emp_details", objectArgs);
				
				gLogger.info("lLongHstEmpIdPk mil gya"+lLongHstEmpIdPk);
				
				gLogger.info(" nya lLongHstEmpIdPk mil gya"+lLongHstEmpIdPk);
				lObjHstEmp.setHstdcpsId(lLongHstEmpIdPk);

				lObjHstEmp.setDbId(gLngDBId);
				lObjHstEmp.setDcpsEmpId(lLongPKValue);
				lObjHstEmp.setLocId(Long.parseLong(gStrLocationCode));
				lObjHstEmp.setDdoCode(lStrDdoCode);
				//lObjHstEmp.setStartDate(gDtCurDate);
				lObjHstEmp.setStartDate(lDtDOJDt);
				lObjHstEmp.setCreatedUserId(gLngUserId);
				lObjHstEmp.setCreatedPostId(gLngPostId);
				lObjHstEmp.setCreatedDate(gDtCurDate);

				lObjNewRegDdoDAO.create(lObjHstEmp);

				// Archive Form 1 for GPF Employee
				NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());
				lObjNewRegTreasuryDAO.ArchiveNewRegForm(lObjDcpsEmpMst, serv);

			}

			lBlFlag = true;

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
			return resObj;
		}

		String lSBStatus = getResponseXMLDocForDDOFwd(lBlFlag, lStrDcpsOrGpf, lStrSevarthId, lBlBillGroupFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		gLogger.error(" Ajax data is : " +lStrResult);
		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;
	}

	/**
	 * 
	 * Service called before the update page is opened.
	 * 
	 * 
	 * 
	 * @author Kapil Devani
	 * @param inputMap
	 * @return
	 * @throws Exception
	 */
	public ResultObject showAndUpdateForm(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{
			setSessionInfo(inputMap);

			// Flag to check if the request has come from Draft list of
			// completed
			// list: 1 For Draft 2 For Completed
			Integer iDraftFlag = Integer.parseInt(StringUtility.getParameter("Draft", request));

			inputMap.put("DraftFlag", iDraftFlag);
			String lStrEmpId = StringUtility.getParameter("Emp_Id", request);

			Long lLngEmpID = Long.parseLong(lStrEmpId);

			MstEmp lObjEmpData = (MstEmp) inputMap.get("DCPSEmpData");

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lObjEmpData = (MstEmp) lObjNewRegDdoDAO.read(lLngEmpID);
			inputMap.put("lObjEmpData", lObjEmpData);// find by teju  for per hours 
			inputMap.put("dcpsOrGpf", lObjEmpData.getDcpsOrGpf().toString());
			String lStrCurrOff = lObjEmpData.getCurrOff();

			List listDesignation = lObjDcpsCommonDAO.getDesignations(lStrCurrOff);
			inputMap.put("DESIGNATIONLIST", listDesignation);

			List listRelationship = IFMSCommonServiceImpl.getLookupValues("RelationList", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("RELATIONLIST", listRelationship);

			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			List listOfficeNames = lObjDcpsCommonDAO.getCurrentOffices(lStrDdoCode);

			inputMap.put("OFFICELIST", listOfficeNames);
			List listCadres = lObjDcpsCommonDAO.getCadres();

			inputMap.put("CADRELIST", listCadres);

			List listParentDept = lObjDcpsCommonDAO.getAllDepartment(Long.parseLong(gObjRsrcBndle.getString("DCPS.DEPARTMENTID")), gLngLangId);
			inputMap.put("listParentDept", listParentDept);

			Date lDtcurDate = SessionHelper.getCurDate();
			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			inputMap.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
			inputMap.put("lDtJoiDtLimit", "01/11/2005");
			inputMap.put("DDOCODE", "12345");

			resObj.setViewName("DCPSRegistrationForm");
			resObj.setResultValue(inputMap);

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
			return resObj;
		}

		return resObj;
	}

	public ResultObject rejectRequestDDO(Map objectArgs)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		String strPKValue = "";

		try
		{

			setSessionInfo(objectArgs);
			strPKValue = StringUtility.getParameter("Emp_Id", request).toString().trim();

			String lStrRemarks = StringUtility.getParameter("remarks", request).toString().trim();

			objectArgs.put("FromPostId", gStrPostId);
			objectArgs.put("SendNotification", lStrRemarks);
			objectArgs.put("jobTitle", gObjRsrcBndle.getString("DCPS.RegistrationForm"));
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.RegistrationFormID")));

			objectArgs.put("Pkvalue", strPKValue);
			WorkFlowDelegate.returnDoc(objectArgs);

			// Update the Registration form status to -1 suggesting it is
			// rejected

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			Long lLongPKValue = Long.parseLong(strPKValue);
			MstEmp lObjDcpsEmpMst = (MstEmp) lObjNewRegDdoDAO.read(lLongPKValue);

			// Set the value in Read VO
			lObjDcpsEmpMst.setRegStatus(-1L);
			lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
			lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
			lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
			lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);
			lObjDcpsEmpMst.setSentBackRemarks(lStrRemarks);
			lObjDcpsEmpMst.setFormStatus(0L);

			lObjDcpsEmpMst.setZpStatus(-1L);

			lObjNewRegDdoDAO.update(lObjDcpsEmpMst);

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
			return resObj;
		}

		String lSBStatus = getResponseXMLDoc(true, Long.parseLong(strPKValue)).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		objectArgs.put("ajaxKey", lStrResult);
		resObj.setViewName("ajaxData");
		resObj.setResultValue(objectArgs);

		return resObj;
	}

	private void createWF(Map inputMap)
	{

		try
		{

			Long PKValue = Long.parseLong(inputMap.get("Pkvalue").toString());
			setSessionInfo(inputMap);

			String subjectName = gObjRsrcBndle.getString("DCPS.RegistrationForm");
			String lStrPostId = SessionHelper.getPostId(inputMap).toString();
			Long lLngHierRefId = WorkFlowHelper.getHierarchyByPostIDAndDescription(lStrPostId, subjectName, inputMap);

			inputMap.put("Hierarchy_ref_id", lLngHierRefId);
			inputMap.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.RegistrationFormID")));
			inputMap.put("Pkvalue", PKValue);
			inputMap.put("DisplayJobTitle", gObjRsrcBndle.getString("DCPS.RegistrationForm"));

			WorkFlowDelegate.create(inputMap);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
		}

	}

	public Integer calculateAge(Date dob)
	{

		SimpleDateFormat simpleDateFormatObj = new SimpleDateFormat("yyyy");
		Integer age;
		Integer birthYear = Integer.parseInt(simpleDateFormatObj.format(dob));
		Date gDtCurrDt = DBUtility.getCurrentDateFromDB();
		String currYearStr = simpleDateFormatObj.format(gDtCurrDt);
		Integer currYear = Integer.parseInt(currYearStr);
		age = currYear - birthYear;
		return age;
	}

	/*
	 * Method to used to generate the XML Response
	 */

	private StringBuilder getResponseXMLDocForGroup(String lStrGroup, Long lLongSuperAnnAge, String lStrRetiringYear)
	{

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<txtGroup>");
		lStrBldXML.append(lStrGroup.trim());
		lStrBldXML.append("</txtGroup>");
		lStrBldXML.append("<txtSuperAnnAge>");
		lStrBldXML.append(lLongSuperAnnAge);
		lStrBldXML.append("</txtSuperAnnAge>");
		lStrBldXML.append("<lStrRetiringYear>");
		lStrBldXML.append(lStrRetiringYear.trim());
		lStrBldXML.append("</lStrRetiringYear>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDocForOffice(List finalList)
	{

		StringBuilder lStrBldXML = new StringBuilder();

		Object obj[] = (Object[]) finalList.get(0);

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<txtAddress1>");
		if (obj[0] != null)
		{
			lStrBldXML.append(obj[0].toString().trim());
		}
		else
		{
			lStrBldXML.append("");
		}
		lStrBldXML.append("</txtAddress1>");
		lStrBldXML.append("<txtContact1>");
		if (obj[1] != null)
		{
			lStrBldXML.append(obj[1].toString().trim());
		}
		else
		{
			lStrBldXML.append("");
		}
		lStrBldXML.append("</txtContact1>");
		lStrBldXML.append("<txtContact2>");
		if (obj[2] != null)
		{
			lStrBldXML.append(obj[2].toString().trim());
		}
		else
		{
			lStrBldXML.append("");
		}
		lStrBldXML.append("</txtContact2>");
		lStrBldXML.append("<txtContact3>");
		if (obj[3] != null)
		{
			lStrBldXML.append(obj[3].toString().trim());
		}
		else
		{
			lStrBldXML.append("");
		}
		lStrBldXML.append("</txtContact3>");
		lStrBldXML.append("<email>");
		if (obj[4] != null)
		{
			lStrBldXML.append(obj[4].toString().trim());
		}
		else
		{
			lStrBldXML.append("");
		}
		lStrBldXML.append("</email>");
		lStrBldXML.append("<txtAddress2>");
		if (obj[5] != null)
		{
			lStrBldXML.append(obj[5].toString().trim());
		}
		else
		{
			lStrBldXML.append("");
		}
		lStrBldXML.append("</txtAddress2>");
		lStrBldXML.append("<txtOfficeCityClass>");
		if (obj[6] != null)
		{
			lStrBldXML.append(obj[6].toString().trim());
		}
		else
		{
			lStrBldXML.append("");
		}
		lStrBldXML.append("</txtOfficeCityClass>");

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDocForNominee(Boolean flag)
	{

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<FLAG>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</FLAG>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDoc(Boolean flag, Long empID)
	{

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("<EMPID>");
		lStrBldXML.append(empID);
		lStrBldXML.append("</EMPID>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDoc(Boolean flag)
	{

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDocToMapDDOAsst(Boolean flag, String lStrUserName, String lStrPassword)
	{

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");

		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");

		lStrBldXML.append("<UserName>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(lStrUserName.trim());
		lStrBldXML.append("]]>");
		lStrBldXML.append("</UserName>");

		lStrBldXML.append("<Password>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(lStrPassword.trim());
		lStrBldXML.append("]]>");
		lStrBldXML.append("</Password>");

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDocForDDOFwd(Boolean flag, String dcpsOrGpf, String lStrSevarthId, Boolean lBlBillGroupFlag)
	{

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("<DcpsOrGPF>");
		lStrBldXML.append(dcpsOrGpf.trim());
		lStrBldXML.append("</DcpsOrGPF>");
		lStrBldXML.append("<SevarthId>");
		lStrBldXML.append(lStrSevarthId.trim());
		lStrBldXML.append("</SevarthId>");
		lStrBldXML.append("<BillGroupFlag>");
		lStrBldXML.append(lBlBillGroupFlag);
		lStrBldXML.append("</BillGroupFlag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public static Integer daysBetween(Date sPassedDate, Date ePassedDate)
	{

		Calendar sDate = Calendar.getInstance();
		sDate.setTime(sPassedDate);
		Calendar eDate = Calendar.getInstance();
		eDate.setTime(ePassedDate);

		Calendar d = (Calendar) sDate.clone();
		Integer dBetween = 0;
		while (d.before(eDate))
		{
			d.add(Calendar.DAY_OF_MONTH, 1);
			dBetween++;
		}
		dBetween = dBetween - 1;
		return dBetween;
	}

	public ResultObject getDesigsForPFDAndCadre(Map<String, Object> lMapInputMap)
	{

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Long lLngParentDept = null;
		Long cadre =0l;
		try
		{

			/* Sets the Session Information */
			setSessionInfo(lMapInputMap);

			/* Initializes the DAO */
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			if (!StringUtility.getParameter("listParentDept", request).equalsIgnoreCase(""))
			{
				lLngParentDept = Long.parseLong(StringUtility.getParameter("listParentDept", request));
			}
			if (!StringUtility.getParameter("cmbCadre", request).equalsIgnoreCase(""))
			{
				cadre = Long.parseLong(StringUtility.getParameter("cmbCadre", request));
			}

			/*
			 * Gets the branch names from the bank name and sends them using
			 * AJAX
			 */
			List lLstDesignation = lObjDcpsCommonDAO.getDesigsForPFDAndCadre(lLngParentDept,cadre);

			String lStrTempResult = null;
			if (lLstDesignation != null)
			{
				lStrTempResult = new AjaxXmlBuilder().addItems(lLstDesignation, "desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

	public ResultObject getLookupValuesForParentAG(Map<String, Object> lMapInputMap)
	{

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Long lLngParentLookupId = null;
		String lStrTypeOfAG = null;
		try
		{

			/* Sets the Session Information */
			setSessionInfo(lMapInputMap);

			/* Initializes the DAO */
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			if (!StringUtility.getParameter("typeOfAG", request).equalsIgnoreCase(""))
			{
				lStrTypeOfAG = StringUtility.getParameter("typeOfAG", request);
			}

			if (lStrTypeOfAG.equals("700092"))
			{
				lLngParentLookupId = 700098l;
			}

			if (lStrTypeOfAG.equals("700093"))
			{
				lLngParentLookupId = 700181l;
			}

			/*
			 * Gets the branch names from the bank name and sends them using
			 * AJAX
			 */
			List lLstLookupValues = lObjDcpsCommonDAO.getLookupValuesForParent(lLngParentLookupId);

			String lStrTempResult = null;
			if (lLstLookupValues != null)
			{
				lStrTempResult = new AjaxXmlBuilder().addItems(lLstLookupValues, "desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

	public ResultObject loadFormListForDDO(Map inputMap)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrDdoCode = null;

		try
		{

			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			getHierarchyUsers(inputMap);
			lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);

			List AllFormsList = lObjNewRegDdoDAO.getFormListForDDO(lStrDdoCode);
			inputMap.put("AllFormsList", AllFormsList);

			resObj.setResultValue(inputMap);
			resObj.setViewName("FormListForDDO");

		}
		catch (Exception e)
		{
			//e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject getFirstDesignationForAutoComplete(Map<String, Object> inputMap) throws Exception
	{

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		List finalList = null;
		String strFirstDesig = null;
		// Get the group name for the cadre name selected
		try
		{

			setSessionInfo(inputMap);

			strFirstDesig = StringUtility.getParameter("searchKey", request).trim();
			//System.out.println(strFirstDesig);
			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			finalList = lObjNewRegDdoDAO.getDesigsForAutoComplete(strFirstDesig);

			String lStrTempResult = null;
			if (finalList != null)
			{
				lStrTempResult = new AjaxXmlBuilder().addItems(finalList, "desc", "id", true).toString();
			}
			inputMap.put("ajaxKey", lStrTempResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");

		}
		catch (Exception ex)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			//ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
			return objRes;
		}

		return objRes;

	}

	public ResultObject popOfficesForPost(Map<String, Object> lMapInputMap)
	{

		ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			/* Sets the Session Information */
			setSessionInfo(lMapInputMap);

			/* Initializes the DAO */
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			/* Gets the bank name from request */
			Long lLngPostId = Long.parseLong(StringUtility.getParameter("cmbCurrentPost", request));

			/*
			 * Gets the branch names from the bank name and sends them using
			 * AJAX
			 */
			List lListCadres = lObjDcpsCommonDAO.getOfficesForPost(lLngPostId);

			String lStrTempResult = null;
			if (lListCadres != null)
			{
				lStrTempResult = new AjaxXmlBuilder().addItems(lListCadres, "desc", "id", true).toString();
			}
			lMapInputMap.put("ajaxKey", lStrTempResult);
			lObjResultObj.setResultValue(lMapInputMap);
			lObjResultObj.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			lObjResultObj.setResultValue(null);
			lObjResultObj.setThrowable(e);
			lObjResultObj.setResultCode(ErrorConstants.ERROR);
			lObjResultObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}
		return lObjResultObj;
	}

	public ResultObject deleteNewRegDraft(Map<String, Object> inputMap)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlSuccessFlag = null;
		MstEmp lObjMstEmp = null;

		try
		{

			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			String lStrDcpsEmpIds = StringUtility.getParameter("draftDcpsEmpIds", request).trim();

			String[] lStrArrDcpsEmpIds = lStrDcpsEmpIds.split("~");
			Long[] lLongArrDcpsEmpIds = new Long[lStrArrDcpsEmpIds.length];

			for (Integer lInt = 0; lInt < lStrArrDcpsEmpIds.length; lInt++)
			{
				if (lStrArrDcpsEmpIds[lInt] != null && !"".equals(lStrArrDcpsEmpIds[lInt]))
				{
					lLongArrDcpsEmpIds[lInt] = Long.valueOf(lStrArrDcpsEmpIds[lInt]);
				}
			}

			for (Integer lInt = 0; lInt < lStrArrDcpsEmpIds.length; lInt++)
			{
				lObjMstEmp = (MstEmp) lObjNewRegDdoDAO.read(lLongArrDcpsEmpIds[lInt]);
				lObjNewRegDdoDAO.deleteNomineesForGivenEmployee(lLongArrDcpsEmpIds[lInt]);
				lObjNewRegDdoDAO.deleteRltPayrollEmpForGivenEmployee(lLongArrDcpsEmpIds[lInt]);
				lObjNewRegDdoDAO.delete(lObjMstEmp);
			}

			lBlSuccessFlag = true;
			String lSBStatus = getResponseXMLDoc(lBlSuccessFlag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");
			resObj.setResultValue(inputMap);

		}
		catch (Exception e)
		{
			//e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error in getDigiSig " + e, e);
		}

		return resObj;

	}

	public ResultObject loadMapDDOAsst(Map inputMap)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrDdoCode = null;
		String lStrRequestForSearch = null;
		List EmpList = null;
		String lStrSevaarthId = null;
		String lStrName = null;

		try
		{

			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);

			lStrRequestForSearch = StringUtility.getParameter("requestForSearch", request).trim();
			lStrSevaarthId = StringUtility.getParameter("txtSevaarthId", request).trim();
			lStrName = StringUtility.getParameter("txtEmployeeName", request).trim();

			if (lStrRequestForSearch.equals("Yes"))
			{
				EmpList = lObjNewRegDdoDAO.getAllApprovedEmpsUnderDDO(lStrDdoCode, lStrSevaarthId, lStrName);
			}

			inputMap.put("EmpList", EmpList);

			resObj.setResultValue(inputMap);
			resObj.setViewName("MapDDOAsst");

		}
		catch (Exception e)
		{
			//e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is : " + e, e);
		}

		return resObj;

	}

	public ResultObject MapDDOAsst(Map inputMap)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		Boolean lBlCheckLocationInWFFlag = false;
		String lStrDcpsEmpId = null;
		String lStrOrgEmpId = null;
		String lStrRequestFor = null;
		String lStrDcpsEmpIds = null;
		String lStrOrgEmpIds = null;
		Long lLongDcpsEmpId = null;
		Long lLongOrgEmpMstId = null;
		Long lLongPostId = null;
		Long lLongUserId = null;
		Long lLongDDOPostId = null;
		Long lLongRltDDOAsstId = null;
		Long lLongAclPostRoleId = null;
		RltDdoAsst lObjRltDdoAsst = null;
		AclPostroleRlt lObjAclPostroleRlt = null;
		WfHierachyPostMpg lObjWfHierachyPostMpg = null;
		AclRoleMst lObjAclRoleMst = null;
		OrgPostMst lObjOrgPostMst = null;
		OrgPostMst lObjCreatedByOrgPostMst = null;
		OrgUserMst lObjCreatedByOrgUserMst = null;
		Long lLongRoleIdOfDDOAsst = 700001l; // Fixed. Never change.

		Long lLongWFOrgPostMpgMst = null;
		Long lLongWFOrgUserMpgMst = null;
		Long lLongHierarchyRefId = null;
		Long lLongHierarchySeqId = null;
		Long lLongCreatedByUserId = null;

		String lStrUserName = "";
		String lStrPassword = "";

		try
		{
			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lStrRequestFor = StringUtility.getParameter("requestFor", request).trim();
			lStrDcpsEmpIds = StringUtility.getParameter("dcpsEmpIds", request).toString().trim();
			lStrOrgEmpIds = StringUtility.getParameter("orgEmpMstIds", request).toString().trim();
			String[] lArrStrDcpsEmpIds = lStrDcpsEmpIds.split("~");
			String[] lArrStrOrgEmpIds = lStrOrgEmpIds.split("~");

			if (lStrRequestFor.equals("Assign"))
			{
				// Assigns the role of DDO Asst to the employees selected
				for (Integer lInt = 0; lInt < lArrStrDcpsEmpIds.length; lInt++)
				{
					if (lArrStrDcpsEmpIds[lInt] != null && !(lArrStrDcpsEmpIds[lInt].equals("")) && lArrStrOrgEmpIds[lInt] != null && !(lArrStrOrgEmpIds[lInt].equals("")))
					{
						lLongDcpsEmpId = Long.valueOf(lArrStrDcpsEmpIds[lInt].trim());
						lLongOrgEmpMstId = Long.valueOf(lArrStrOrgEmpIds[lInt].trim());

						lLongPostId = lObjNewRegDdoDAO.getPostForEmpId(lLongOrgEmpMstId);
						lLongUserId = lObjNewRegDdoDAO.getUserIdForEmpId(lLongOrgEmpMstId);
						lLongDDOPostId = gLngPostId;
						lLongCreatedByUserId = lObjNewRegDdoDAO.getUserIdForPostId(gLngPostId);

						lObjNewRegDdoDAO.unlockAccountForOrgEmpId(lLongOrgEmpMstId);

						Object[] lObjUserNameAndPwd = lObjNewRegDdoDAO.getUserNameAndPwdForEmpId(lLongOrgEmpMstId);

						if (lObjUserNameAndPwd[0] != null)
						{
							lStrUserName = lObjUserNameAndPwd[0].toString().trim();
						}
						if (lObjUserNameAndPwd[1] != null)
						{
							lStrPassword = lObjUserNameAndPwd[1].toString().trim();
						}

						// creates entry in rlt_dcps_ddo_asst table if not there between that DDO and Assistant 
						if (!(lObjNewRegDdoDAO.checkEntryInRltDDOAsstTable(lLongPostId, lLongDDOPostId)))
						{
							lObjRltDdoAsst = new RltDdoAsst();
							lObjRltDdoAsst.setAsstPostId(lLongPostId);
							lObjRltDdoAsst.setDdoPostId(lLongDDOPostId);

							lLongRltDDOAsstId = IFMSCommonServiceImpl.getNextSeqNum("rlt_dcps_ddo_asst", inputMap);
							lObjRltDdoAsst.setRltDdoAsstId(lLongRltDDOAsstId);
							lObjNewRegDdoDAO.create(lObjRltDdoAsst);
						}

						// creates entry in ACL_POSTROLE_RLT table to map role of Assistant to that DDO
						if (!(lObjNewRegDdoDAO.checkEntryInAclPostRoleTable(lLongPostId)))
						{

							/*
							lObjAclRoleMst = lObjNewRegDdoDAO.getRoleVOForRoleId(lLongRoleIdOfDDOAsst);
							lObjOrgPostMst = lObjNewRegDdoDAO.getPostVOForPostId(lLongPostId);
							lObjCreatedByOrgPostMst = lObjNewRegDdoDAO.getPostVOForPostId(lLongDDOPostId);
							lObjCreatedByOrgUserMst = lObjNewRegDdoDAO.getUserVOForUserId(lLongCreatedByUserId);
							
							lObjAclPostroleRlt = new AclPostroleRlt();
							lObjAclPostroleRlt.setAclRoleMst(lObjAclRoleMst);
							lObjAclPostroleRlt.setOrgPostMst(lObjOrgPostMst);
							lObjAclPostroleRlt.setOrgPostMstByCreatedByPost(lObjCreatedByOrgPostMst);
							lObjAclPostroleRlt.setStartDate(gDtCurDate);
							lObjAclPostroleRlt.setPostRoleId(lLongAclPostRoleId);
							lObjAclPostroleRlt.setOrgUserMstByCreatedBy(lObjCreatedByOrgUserMst);
							//lObjAclPostroleRlt.setCmnLookupMstByActivate(arg0);
							lObjNewRegDdoDAO.create(lObjAclPostroleRlt);
							*/

							lLongAclPostRoleId = IFMSCommonServiceImpl.getNextSeqNum("acl_postrole_rlt", inputMap);
							lObjNewRegDdoDAO.insertAclPostRoleRlt(lLongAclPostRoleId, lLongRoleIdOfDDOAsst, lLongPostId, lLongDDOPostId, gDtCurDate);

						}

						// creates entry in WF_ORG_POST_MPG_MST table

						if (!(lObjNewRegDdoDAO.checkEntryInWFOrgPostMpgMst(lLongPostId)))
						{
							lLongWFOrgPostMpgMst = IFMSCommonServiceImpl.getNextSeqNum("wf_org_post_mpg_mst", inputMap);
							lObjNewRegDdoDAO.insertWFOrgPostMpg(lLongPostId);
						}

						if (!(lObjNewRegDdoDAO.checkEntryInWFOrgUserMpgMst(lLongUserId)))
						{
							lLongWFOrgUserMpgMst = IFMSCommonServiceImpl.getNextSeqNum("wf_org_usr_mpg_mst", inputMap);
							lObjNewRegDdoDAO.insertWFOrgUsrMpg(lLongUserId);
						}

						// creates entries in WF_HIERARCHY_REFERENCE_MST table

						List lListHierarchyRefIds = lObjNewRegDdoDAO.getAllHierarchyRefIdsForLocation(Long.valueOf(gStrLocationCode));
						for (Integer k = 0; k < lListHierarchyRefIds.size(); k++)
						{
							lLongHierarchyRefId = Long.valueOf(lListHierarchyRefIds.get(k).toString());
							if (!(lObjNewRegDdoDAO.checkEntryInWfHierachyPostMpg(lLongHierarchyRefId, lLongPostId)))
							{
								lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);
								lObjNewRegDdoDAO.insertWfHierachyPostMpg(lLongHierarchySeqId, lLongHierarchyRefId, lLongPostId, lLongCreatedByUserId, gDtCurDate, Long.valueOf(gStrLocationCode));
							}
							lBlCheckLocationInWFFlag = true;
						}

						//lObjNewRegDdoDAO.updateDDOAsstStatusInMstEmp(lLongDcpsEmpId,lStrRequestFor);

					}
				}

				if (lBlCheckLocationInWFFlag)
				{
					lBlFlag = true;
				}

			}

			if (lStrRequestFor.equals("DeAssign"))
			{
				// De-assigns the role of DDO Asst to the employees selected and locks the account of those employees
				for (Integer lInt = 0; lInt < lArrStrDcpsEmpIds.length; lInt++)
				{
					if (lArrStrDcpsEmpIds[lInt] != null && !(lArrStrDcpsEmpIds[lInt].equals("")) && lArrStrOrgEmpIds[lInt] != null && !(lArrStrOrgEmpIds[lInt].equals("")))
					{
						lLongDcpsEmpId = Long.valueOf(lArrStrDcpsEmpIds[lInt]);
						lLongOrgEmpMstId = Long.valueOf(lArrStrOrgEmpIds[lInt]);
						lObjNewRegDdoDAO.lockAccountForOrgEmpId(lLongOrgEmpMstId);
						//lObjNewRegDdoDAO.updateDDOAsstStatusInMstEmp(lLongDcpsEmpId,lStrRequestFor);
					}
				}

				lBlFlag = true;
			}

			IFMSCommonServiceImpl lObjIFMSCommonServiceImpl = new IFMSCommonServiceImpl();
			lObjIFMSCommonServiceImpl.clearCacheForMapDDOAsst(serv.getSessionFactory());
			lObjIFMSCommonServiceImpl.clearCacheForMapDDOAsst(serv.getSessionFactorySlave());

			if (lBlFlag)
			{
				lObjNewRegDdoDAO.updateDDOAsstStatusInMstEmp(lLongDcpsEmpId, lStrRequestFor);
			}

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			gLogger.error(" Error is : " + ex, ex);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//ex.printStackTrace();
			return resObj;
		}

		String lSBStatus = getResponseXMLDocToMapDDOAsst(lBlFlag, lStrUserName, lStrPassword).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;

	}

	public String generateSeevarthIdNewLogic(Map inputMap) throws Exception
	{
		String lStrSevarthEmpCode = "";
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		MstEmp lObjEmpData = null;

		try
		{

			ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
			NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());
			OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = null;

			Long lLngEmpId = Long.parseLong(String.valueOf(inputMap.get("dcpsEmpId")));
			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			lObjEmpData = new MstEmp();
			lObjEmpData = (MstEmp) lObjNewRegTreasuryDAO.read(lLngEmpId);

			String lStrEmpFname = lObjEmpData.getName();
			Character lCharGender = lObjEmpData.getGender();

			Date lDateBirthDate = lObjEmpData.getDob();
			String lStrBirthDate = lObjDateFormat.format(lDateBirthDate);

			lStrEmpFname = lStrEmpFname.replace('.', ' ');
			lStrEmpFname = lStrEmpFname.replaceAll("\\s+", " ");

			String Names[] = lStrEmpFname.split(" ");
			String Fname = null;
			String Mname = null;
			String Lname = null;

			if (Names.length >= 3)
			{
				Fname = Names[0].substring(0, 1);
				Mname = Names[1].substring(0, 1);
				Lname = Names[2].substring(0, 1);

			}

			else if (Names.length == 2)
			{
				Fname = Names[0].substring(0, 1);
				Mname = Names[1].substring(0, 1);
				//Lname = ".";

				Lname = Names[1].substring(1, 2);

			}

			else if (Names.length == 1)
			{
				Fname = Names[0].substring(0, 1);
				//Mname = ".";
				//Lname = ".";

				Mname = Names[0].substring(1, 2);
				Lname = Names[0].substring(2, 3);
			}

			cmnLocationMst = otherDtlsDao.getCmnLocationMst(lObjEmpData.getDdoCode());
			String lStrOfficeName = cmnLocationMst.getLocShortName().substring(0, 3).toUpperCase();
			//changes for ZP
			//to add first 2 digits of Admin
			String adminOfficeMst = lObjEmpData.getDdoCode();
			adminOfficeMst = adminOfficeMst.substring(0, 2);
			String lStrTempSevarthCode = adminOfficeMst + lStrOfficeName + Fname + Mname + Lname + lCharGender.toString() + lStrBirthDate.substring(8, 10);
			;

			long curentIdCount = otherDtlsDao.getCount(lStrTempSevarthCode);
			long tempIdCount = curentIdCount + 01;
			String tempCountVar = String.format("%2s", tempIdCount).replace(' ', '0');

			if (curentIdCount == 0)
			{
				lStrSevarthEmpCode = lStrSevarthEmpCode + "01";
			}
			lStrSevarthEmpCode = lStrTempSevarthCode + tempCountVar;

			gLogger.info("end of  generate seevarth id method with sevaarth Id value:" + lStrSevarthEmpCode);

		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//ex.printStackTrace();
			gLogger.error(" Error is : " + ex, ex);
		}

		return lStrSevarthEmpCode;
	}

	public ResultObject checkIfNameExists(Map<String, Object> inputMap)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;

		try
		{

			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv.getSessionFactory());

			String lStrName = StringUtility.getParameter("txtName", request).trim();

			if (!"".equals(lStrName))
			{

				lBlFlag = lObjNewRegDdoDAO.checkIfNameExists(lStrName);
			}

			String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");
			resObj.setResultValue(inputMap);

		}
		catch (Exception e)
		{
			//e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error in getDigiSig " + e, e);
		}

		return resObj;

	}

	public ResultObject viewFormsForwardedByAsstZpRepoDDO(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{
			//gLogger.info("hi i am kinjal in service");
			setSessionInfo(inputMap);

			String lStrUseZP = StringUtility.getParameter("Use", request).trim();
			System.out.println("The valueof the variabl is..."+lStrUseZP);
			inputMap.put("Use", lStrUseZP);
			gLogger.info("hi i am kinjal in service"+lStrUseZP);
			String lStrUserZP = StringUtility.getParameter("User", request).trim();
			inputMap.put("User", lStrUserZP);
			gLogger.info("hi i am kinjal in service"+lStrUserZP);
			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			/*
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			inputMap.put("DDOCODE", lStrDdoCode);
			*/

			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			//added by vaibhav tyagi: start
			String ddoSelected=null;
			String reptddoSelected=null;
			if((StringUtility.getParameter("ddoCode", request).trim()!=null)||(StringUtility.getParameter("ddoCode", request).trim()!="")||(Long.parseLong(StringUtility.getParameter("ddoCode", request).trim())!=-1)){
				ddoSelected = StringUtility.getParameter("ddoCode", request).trim();
			}
			List asstDDO= lObjNewRegDdoDAO.getAllAsstDDOList(lStrDdoCode);
			inputMap.put("asstDDO", asstDDO);
			inputMap.put("ddoSelected", ddoSelected);
			List empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP,reptddoSelected,ddoSelected);
			//added by vaibhav tyagi: end
			
			//commented by vaibhav tyagi
			//List empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP);
			
			inputMap.put("empList", empList);

			inputMap.put("EditForm", "N");

			resObj.setResultValue(inputMap);
			resObj.setViewName("FormListforRepoDDO");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject viewFormsForwardedByAsstZpFinalDDO(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			setSessionInfo(inputMap);

			String lStrUseZP = StringUtility.getParameter("Use", request).trim();
			inputMap.put("Use", lStrUseZP);

			String lStrUserZP = StringUtility.getParameter("User", request).trim();
			inputMap.put("User", lStrUserZP);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrDdoCode = null;

			//added by vaibhav tyagi: start
			lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			inputMap.put("DDOCODE", lStrDdoCode);

			
			String reptddoSelected=null;
			String ddoSelected=null;
			if((StringUtility.getParameter("reptddoCode", request).trim()!=null)||(StringUtility.getParameter("reptddoCode", request).trim()!="")||(Long.parseLong(StringUtility.getParameter("reptddoCode", request).trim())!=-1)){
				reptddoSelected = StringUtility.getParameter("reptddoCode", request).trim();
			}
			
			if((StringUtility.getParameter("asstddoCode", request).trim()!=null)||(StringUtility.getParameter("asstddoCode", request).trim()!="")||(Long.parseLong(StringUtility.getParameter("asstddoCode", request).trim())!=-1)){
				ddoSelected = StringUtility.getParameter("asstddoCode", request).trim();
			}
			
			List reptDDO= lObjNewRegDdoDAO.getAllReptDDOListByFinalDDO(lStrDdoCode);
			List asstDDO= null;
			if((reptddoSelected!=null)||(reptddoSelected!="")||Long.parseLong(reptddoSelected)!=-1){
			asstDDO= lObjNewRegDdoDAO.getAllAsstDDOListByFinalDDO(lStrDdoCode,reptddoSelected);
			}
			inputMap.put("reptDDO", reptDDO);
			inputMap.put("asstDDO", asstDDO);
			inputMap.put("reptddoSelected", reptddoSelected);
			inputMap.put("ddoSelected", ddoSelected);
			List empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP,reptddoSelected,ddoSelected);
			//added by vaibhav tyagi: end
			
			//commented by vaibhav tyagi
			//List empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP);inputMap.put("empList", empList);
			inputMap.put("empList", empList);
			inputMap.put("EditForm", "N");

			resObj.setResultValue(inputMap);
			resObj.setViewName("FormListforFinalDDO");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject viewFormsForwardedByAsstZpSpecialDDO(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			setSessionInfo(inputMap);

			String lStrUseZP = StringUtility.getParameter("Use", request).trim();
			inputMap.put("Use", lStrUseZP);

			String lStrUserZP = StringUtility.getParameter("User", request).trim();
			inputMap.put("User", lStrUserZP);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrDdoCode = null;

			//added by vaibhav tyagi: start
			lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			inputMap.put("DDOCODE", lStrDdoCode);

			
			String reptddoSelected=null;
			String ddoSelected=null;
			if((StringUtility.getParameter("reptddoCode", request).trim()!=null)||(StringUtility.getParameter("reptddoCode", request).trim()!="")||(Long.parseLong(StringUtility.getParameter("reptddoCode", request).trim())!=-1)){
				reptddoSelected = StringUtility.getParameter("reptddoCode", request).trim();
			}
			
			if((StringUtility.getParameter("asstddoCode", request).trim()!=null)||(StringUtility.getParameter("asstddoCode", request).trim()!="")||(Long.parseLong(StringUtility.getParameter("asstddoCode", request).trim())!=-1)){
				ddoSelected = StringUtility.getParameter("asstddoCode", request).trim();
			}
			
			List reptDDO= lObjNewRegDdoDAO.getAllReptDDOListByFinalDDO(lStrDdoCode);
			List asstDDO= null;
			if((reptddoSelected!=null)||(reptddoSelected!="")||Long.parseLong(reptddoSelected)!=-1){
			asstDDO= lObjNewRegDdoDAO.getAllAsstDDOListByFinalDDO(lStrDdoCode,reptddoSelected);
			}
			inputMap.put("reptDDO", reptDDO);
			inputMap.put("asstDDO", asstDDO);
			inputMap.put("reptddoSelected", reptddoSelected);
			inputMap.put("ddoSelected", ddoSelected);
			List empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZP(lStrUserZP, gStrPostId, lStrDdoCode, lStrUseZP,reptddoSelected,ddoSelected);
			//added by vaibhav tyagi: end
			inputMap.put("empList", empList);

			inputMap.put("EditForm", "N");
			

			resObj.setResultValue(inputMap);
			resObj.setViewName("FormListforSpecialDDO");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject rejectRequestZP(Map objectArgs)
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{
			//gLogger.info("hi i m to reject ");
			setSessionInfo(objectArgs);

			NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());
			String strPKValue = StringUtility.getParameter("Emp_Id", request).toString().trim();

			String[] strArrPKValue = strPKValue.split("~");

			String toPost = lObjNewRegTreasuryDAO.getDDOAsstPostIdForEmpId(strArrPKValue[0]);
			String toLevel = gObjRsrcBndle.getString("DCPS.ZPDDOASST");

			objectArgs.put("toPost", toPost);
			objectArgs.put("toPostId", toPost);
			objectArgs.put("toLevel", toLevel);

			objectArgs.put("jobTitle", gObjRsrcBndle.getString("DCPS.RegistrationForm"));
			objectArgs.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.RegistrationFormID")));

			for (Integer index = 0; index < strArrPKValue.length; index++)
			{
				objectArgs.put("Pkvalue", strArrPKValue[index]);

				WorkFlowDelegate.forward(objectArgs);

				// Update the Registration form status to -1 suggesting it is
				// rejected

				Long lLongPKValue = Long.parseLong(strArrPKValue[index]);
				MstEmp lObjDcpsEmpMst = (MstEmp) lObjNewRegTreasuryDAO.read(lLongPKValue);

				// Set the value in Read VO
				lObjDcpsEmpMst.setRegStatus(-1L);
				lObjDcpsEmpMst.setPhyRcvdFormStatus(null);
				lObjDcpsEmpMst.setPhyRcvdDate(null);

				String lStrRemarks = StringUtility.getParameter("remarks", request).trim();

				lObjDcpsEmpMst.setSentBackRemarks(lStrRemarks);

				lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
				lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
				lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
				lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);
				lObjDcpsEmpMst.setFormStatus(0L);

				lObjDcpsEmpMst.setZpStatus(-1l);

				lObjNewRegTreasuryDAO.update(lObjDcpsEmpMst);

			}

			objectArgs.put("ajaxKey", "Success");
			resObj.setViewName("ajaxData");
			resObj.setResultValue(objectArgs);
		}
		catch (Exception ex)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(ex);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//ex.printStackTrace();
			return resObj;
		}
		return resObj;
	}

	/*public ResultObject FwdFromRepoDDO(Map inputMap) throws Exception
	{

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		Boolean lStrflag;

		try
		{
			Long lLongHstEmpIdPk = null;
			Long lLongPKValue = null;
			setSessionInfo(inputMap);
			lStrflag = false;
			MstEmp lObjDcpsEmpMst;
			NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());
			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			String lStrEmpIds = StringUtility.getParameter("Emp_Id", request).trim();
			String[] lStrArrEmpIds = lStrEmpIds.split("~");
			Boolean lBlBillGroupFlag;
			String toPost = StringUtility.getParameter("ForwardToPost", request).toString();
			String toLevel = gObjRsrcBndle.getString("DCPS.FINALDDO");
			String lStrSevarthId = "";
			String lStrDcpsOrGpf = "";
			String lStrDdoCode = "";

			for (Integer i = 0; i < lStrArrEmpIds.length; i++)
			{

				inputMap.put("toPost", toPost);
				inputMap.put("toPostId", toPost);
				inputMap.put("toLevel", toLevel);

				inputMap.put("jobTitle", gObjRsrcBndle.getString("DCPS.RegistrationForm"));
				inputMap.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.RegistrationFormID")));

				inputMap.put("Pkvalue", lStrArrEmpIds[i].trim());

				WorkFlowDelegate.forward(inputMap);
				lObjDcpsEmpMst = (MstEmp) lObjNewRegTreasuryDAO.read(Long.valueOf(lStrArrEmpIds[i].trim()));
				if (lObjDcpsEmpMst.getDcpsOrGpf().equals('Y'))
				{

					lStrDcpsOrGpf = "DCPS";

					//lObjDcpsEmpMst.setPhyRcvdFormStatus(1L);
					//lObjDcpsEmpMst.setPhyRcvdDate(gDtCurrDt);
					lObjDcpsEmpMst.setApprovalByDDODate(gDtCurrDt);
					lObjDcpsEmpMst.setRegStatus(0L);
					lObjDcpsEmpMst.setPhyRcvdFormStatus(0l);
					lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
					lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
					lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
					lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);
					lObjDcpsEmpMst.setZpStatus(3l);
					lStrSevarthId = generateSeevarthIdNewLogic(inputMap);
					lObjNewRegTreasuryDAO.update(lObjDcpsEmpMst);
					lStrflag = true;
				}

			}
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			return objRes;
		}

		String lSBStatus = getResponseXMLDoc(lStrflag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		inputMap.put("ajaxKey", lStrResult);
		objRes.setResultValue(inputMap);
		objRes.setViewName("ajaxData");
		return objRes;

	}*/
	
	public ResultObject FwdFromRepoDDO(Map inputMap) throws Exception
	{

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		Boolean lStrflag;

		try
		{
			Long lLongHstEmpIdPk = null;
			Long lLongPKValue = null;
			setSessionInfo(inputMap);
			lStrflag = false;
			MstEmp lObjDcpsEmpMst;
			NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());
			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
			String lStrEmpIds = StringUtility.getParameter("Emp_Id", request).trim();
			String[] lStrArrEmpIds = lStrEmpIds.split("~");
			Boolean lBlBillGroupFlag;
			String toPost = StringUtility.getParameter("ForwardToPost", request).toString();
			String toLevel = gObjRsrcBndle.getString("DCPS.FINALDDO");
			String lStrSevarthId = "";
			String lStrDcpsOrGpf = "";
			String lStrDdoCode = "";

			for (Integer i = 0; i < lStrArrEmpIds.length; i++)
			{

				inputMap.put("toPost", toPost);
				inputMap.put("toPostId", toPost);
				inputMap.put("toLevel", toLevel);

				inputMap.put("jobTitle", gObjRsrcBndle.getString("DCPS.RegistrationForm"));
				inputMap.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.RegistrationFormID")));

				inputMap.put("Pkvalue", lStrArrEmpIds[i].trim());

				WorkFlowDelegate.forward(inputMap);
				lObjDcpsEmpMst = (MstEmp) lObjNewRegTreasuryDAO.read(Long.valueOf(lStrArrEmpIds[i].trim()));
				if (lObjDcpsEmpMst.getDcpsOrGpf().equals('Y'))
				{

					lStrDcpsOrGpf = "DCPS";

					//lObjDcpsEmpMst.setPhyRcvdFormStatus(1L);
					//lObjDcpsEmpMst.setPhyRcvdDate(gDtCurrDt);
					lObjDcpsEmpMst.setApprovalByDDODate(gDtCurrDt);
					lObjDcpsEmpMst.setRegStatus(0L);
					lObjDcpsEmpMst.setPhyRcvdFormStatus(0l);
					lObjDcpsEmpMst.setRegStatusUpdtdDate(gDtCurrDt);
					lObjDcpsEmpMst.setUpdatedUserId(gLngUserId);
					lObjDcpsEmpMst.setUpdatedPostId(gLngPostId);
					lObjDcpsEmpMst.setUpdatedDate(gDtCurrDt);
					//gLogger.info("hi i m here in FwdFromRepoDDO");
					lObjDcpsEmpMst.setZpStatus(3l);
					lStrSevarthId = generateSeevarthIdNewLogic(inputMap);
					lObjNewRegTreasuryDAO.update(lObjDcpsEmpMst);
					lStrflag = true;
				}

			}
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			return objRes;
		}

		String lSBStatus = getResponseXMLDoc(lStrflag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		inputMap.put("ajaxKey", lStrResult);
		objRes.setResultValue(inputMap);
		objRes.setViewName("ajaxData");
		return objRes;

	}

	public ResultObject FwdFromFinalDDO(Map inputMap) throws Exception
	{

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		Boolean lStrflag;

		try
		{
			setSessionInfo(inputMap);
			lStrflag = false;
			MstEmp lObjDcpsEmpMst;
			NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());

			String lStrEmpIds = StringUtility.getParameter("Emp_Id", request).trim();
			String[] lStrArrEmpIds = lStrEmpIds.split("~");

			String toPost = StringUtility.getParameter("ForwardToPost", request).toString();
			String toLevel = gObjRsrcBndle.getString("DCPS.SPECIALDDO");

			inputMap.put("toPost", toPost);
			inputMap.put("toPostId", toPost);
			inputMap.put("toLevel", toLevel);

			inputMap.put("jobTitle", gObjRsrcBndle.getString("DCPS.RegistrationForm"));
			inputMap.put("Docid", Long.parseLong(gObjRsrcBndle.getString("DCPS.RegistrationFormID")));

			for (Integer i = 0; i < lStrArrEmpIds.length; i++)
			{

				inputMap.put("Pkvalue", lStrArrEmpIds[i].trim());

				WorkFlowDelegate.forward(inputMap);
				lObjDcpsEmpMst = (MstEmp) lObjNewRegTreasuryDAO.read(Long.valueOf(lStrArrEmpIds[i].trim()));
				//lObjDcpsEmpMst.setPhyRcvdFormStatus(1L);
				//lObjDcpsEmpMst.setPhyRcvdDate(gDtCurrDt);

				lObjDcpsEmpMst.setZpStatus(4l);

				lObjNewRegTreasuryDAO.update(lObjDcpsEmpMst);
				lStrflag = true;

			}
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			return objRes;
		}

		String lSBStatus = getResponseXMLDoc(lStrflag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		inputMap.put("ajaxKey", lStrResult);
		objRes.setResultValue(inputMap);
		objRes.setViewName("ajaxData");
		return objRes;

	}
	public ResultObject deleteNomineeDetails(Map objectArgs) throws Exception{
		//gLogger.info("Entering into delete data");
	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	
	setSessionInfo(objectArgs);
	
	//gLogger.info("Entering into delete data2");
	ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
	//gLogger.info("Entering into delete data3");
	//NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
	NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
	//HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	//gLogger.info("hiii");
	
	String nomineeID= StringUtility.getParameter("nomineeId", request);
	gLogger.info("nomineeID"+nomineeID);
	long countOfSameNominee=lObjNewRegDdoDAO.deleteNomineeDetails(nomineeID);
	boolean flag= (countOfSameNominee>0)?false:true;
				
	String xmlData = getResponseXMLDocSaveData(flag).toString();
	String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", xmlData).toString();

	gLogger.info("********************************************" + lStrResult);

	objectArgs.put("ajaxKey", lStrResult);
	objRes.setResultValue(objectArgs);
	objRes.setResultCode(ErrorConstants.SUCCESS);
	objRes.setViewName("ajaxData");
	return objRes;
	}
	 private StringBuilder getResponseXMLDocSaveData(boolean isPresent)
		{
			StringBuilder lStrBldXML = new StringBuilder();
			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<flag>");
			lStrBldXML.append(isPresent);
			lStrBldXML.append("</flag>");
			lStrBldXML.append("</XMLDOC>");

			return lStrBldXML;
		}

	//added by roshan for getting the list to update the bank...

	 public ResultObject getEmployeeListForBankDetailsUpdate(Map inputMap) throws Exception
		{

			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			try
			{
				//gLogger.info("in getEmployeeListForBankDetailsUpdate");
				setSessionInfo(inputMap);

				String lStrUserZP = "zpDDO";
				String lStrUseZP = "bank_barnch_update";
				//String lStrUseZP = "";

				inputMap.put("User", lStrUserZP.trim());
				inputMap.put("Use", lStrUseZP.trim());
				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

				String empIds=null;
				String cmbBank=null;
				String cmbBranch=null;
				String accountNumber=null;
				

				if((StringUtility.getParameter("empIds", request)!=null)&&(StringUtility.getParameter("empIds", request)!="")){
					empIds= StringUtility.getParameter("empIds", request);
					cmbBank= StringUtility.getParameter("bankId", request);	
					cmbBranch=StringUtility.getParameter("branchId", request);
					accountNumber=StringUtility.getParameter("acntNo", request);
					
					String[] lstrIds = empIds.split("~");
					String[] bank = cmbBank.split("~");
					String[] branch=cmbBranch.split("~");
					String[] acnt=accountNumber.split("~");
					
					String[] lstrEmpIDs = new String[lstrIds.length];
					String[] bankArr = new String[bank.length];
					String[] lstrNewBasic= new String[branch.length];
					String[] accntNo=new String[acnt.length];
					
					for (Integer lInt = 0; lInt < lstrIds.length; lInt++)
					{
						if (lstrIds[lInt] != null && !"".equals(lstrIds[lInt]))
						{
							lstrEmpIDs[lInt] = lstrIds[lInt];
							bankArr[lInt] = bank[lInt];
							lstrNewBasic[lInt]=branch[lInt];
							accntNo[lInt]=acnt[lInt];
								
							gLogger.info("hii********** "+lstrEmpIDs[lInt]);
							gLogger.info("hii********** "+bankArr[lInt]);
							gLogger.info("hii********** "+lstrNewBasic[lInt]);
							gLogger.info("hii********** "+accntNo[lInt]);
						}
					}

					for (Integer lInt = 0; lInt < lstrIds.length; lInt++)
					{
						lObjNewRegDdoDAO.updateBankDetails(lstrEmpIDs[lInt],bankArr[lInt],lstrNewBasic[lInt],accntNo[lInt]);

					}
				}
				
				
				
				
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				gLogger.info("hi "+lStrDdoCode);
				List empList = lObjNewRegDdoDAO.getAllDcpsEmployeesZPForBankUpdate(lStrDdoCode);
				inputMap.put("EditForm", "N");
				inputMap.put("empList", empList);
				//added by roshan for performance tuning
				empList=null;
				//ended by roshan for performance tuning
				// Get the Bank Names
				List lLstBankNames = lObjNewRegDdoDAO.getBankNames();
				inputMap.put("BANKNAMES", lLstBankNames);
				lLstBankNames=null;
				
				resObj.setResultValue(inputMap);
				resObj.setViewName("employeeListForBankDetailsUpdate");

			}
			catch (Exception e)
			{
				resObj.setResultValue(null);
				resObj.setThrowable(e);
				resObj.setResultCode(ErrorConstants.ERROR);
				resObj.setViewName("errorPage");
			}

			return resObj;
		}

	 
		
		//added by roshan for percentage of basic field

	 public ResultObject getEmployeeListForBasicUpdate(Map inputMap)
	            throws Exception {

	 

	        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	        try {
	            setSessionInfo(inputMap);

	 

	            String lStrUserZP = "zpDDO";
	            String lStrUseZP = "bank_barnch_update";
	            // String lStrUseZP = "";

	 

	            inputMap.put("User", lStrUserZP.trim());
	            inputMap.put("Use", lStrUseZP.trim());
	            NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class,
	                    serv.getSessionFactory());
	            String empIds = null;
	            String perBasic = null;
	            String newBasic = null;
	            /*
	             * String fromDate=null; String toDate=null;
	             */
	            String wefDate = null;
	            Double revisedBasic = null;
	            String payCom = null;
	            BigDecimal basic = null;

	 

	            if ((StringUtility.getParameter("empIds", request) != null)
	&& (StringUtility.getParameter("empIds", request) != "")) {

	 

	                empIds = StringUtility.getParameter("empIds", request);
	                perBasic = StringUtility.getParameter("perBasic", request);
	                newBasic = StringUtility.getParameter("newBasic", request);
	                /*
	                 * fromDate=StringUtility.getParameter("fromDate", request);
	                 * toDate=StringUtility.getParameter("toDate", request);
	                 */
	                wefDate = StringUtility.getParameter("wEffectDate", request);
	                payCom = StringUtility.getParameter("payCom", request);

	 

	                String[] lstrIds = empIds.split("~");
	                String[] lstrPervalue = perBasic.split("~");
	                String[] newBasicArr = newBasic.split("~");
	                /*
	                 * String[] empToDate=toDate.split("~"); String[]
	                 * empFromDate=fromDate.split("~");
	                 */
	                String[] withEFDate = wefDate.split("~");
	                String[] empPayCommisionId = payCom.split("~");
	                String[] lstrEmpIDs = new String[lstrIds.length];
	                String[] lstrPerValueArr = new String[lstrPervalue.length];
	                String[] lstrNewBasic = new String[newBasicArr.length];
	                /*
	                 * String[] fromDateArr= new String[empFromDate.length];
	                 * String[] toDateArr= new String[empToDate.length];
	                 */
	                String[] withEFDateArr = new String[withEFDate.length];
	                String[] empPayCommisionId1 = new String[empPayCommisionId.length];

	 

	                for (Integer lInt = 0; lInt < lstrIds.length; lInt++) {
	                    if (lstrIds[lInt] != null && !"".equals(lstrIds[lInt])) {
	                        lstrEmpIDs[lInt] = lstrIds[lInt];
	                        lstrPerValueArr[lInt] = lstrPervalue[lInt];
	                        lstrNewBasic[lInt] = newBasicArr[lInt];
	                        withEFDateArr[lInt.intValue()] = withEFDate[lInt
	                                .intValue()];
	                        empPayCommisionId1[lInt] = empPayCommisionId[lInt];
	                        String[] tempWefDate = withEFDateArr[lInt.intValue()]
	                                .split("/");
	                        withEFDateArr[lInt.intValue()] = (tempWefDate[1] + "/"
	                                + tempWefDate[0] + "/" + tempWefDate[2]);

	 

	                    }
	                }

	 

	                for (Integer lInt = 0; lInt < lstrIds.length; lInt++) {
	                    revisedBasic = Double.parseDouble(lstrNewBasic[lInt]);
	                    basic = BigDecimal.valueOf(revisedBasic);
	                    basic = basic.divide(BigDecimal.valueOf(1), 0,
	                            BigDecimal.ROUND_UP);
	                    lObjNewRegDdoDAO.updateDetails(lstrEmpIDs[lInt.intValue()],
	                            lstrPerValueArr[lInt.intValue()], basic.toString(),
	                            withEFDateArr[lInt.intValue()],
	                            empPayCommisionId1[lInt]);

	 

	                }

	                inputMap.put("messages", "Record Updated Successfully..!!");

	            }
	            DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
	                    serv.getSessionFactory());
	            String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
	            List empList = lObjNewRegDdoDAO
	                    .getEmployeeListForBasicUpdates(lStrDdoCode);
	            inputMap.put("EditForm", "N");
	            inputMap.put("empList", empList);
	            inputMap.put("ddoCode", lStrDdoCode);
	           

	 

	            // added by roshan for performance tuning
	            empList = null;
	            // ended by roshan for performance tuning

	 

	            resObj.setResultValue(inputMap);
	            resObj.setViewName("getEmployeeListForBasicUpdate");

	 

	        } catch (Exception e) {
	            resObj.setResultValue(null);
	            resObj.setThrowable(e);
	            resObj.setResultCode(ErrorConstants.ERROR);
	            resObj.setViewName("errorPage");
	        }

	 

	        return resObj;
	    }

	 

	
		
		//START added by samadhan for validateAccNo 
		public ResultObject validateAccNo(Map objectArgs)
		{
			//gLogger.info("inside chkEmpTypeByDsgn");
			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			String bankId=null;
			String accNoLength=null;
			
			
			String lStrResult = null;
			try {
				
				
				bankId=StringUtility.getParameter("bankId", request).trim();
				gLogger.info("--------bankId--------:"+bankId);
				
				
				
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				
				
				accNoLength=lObjDcpsCommonDAO.getaccNoLengthForBankId(bankId);
				
				
				String lSBStatus = getResponseXMLDocForValidateAccNo(accNoLength).toString();
				lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
				
				objectArgs.put("ajaxKey", lStrResult);
				objRes.setResultValue(objectArgs);
				objRes.setViewName("ajaxData");
				
			} catch (Exception e) {
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
			}
			return objRes;
		}
		
		private StringBuilder getResponseXMLDocForValidateAccNo(String status) {

			StringBuilder lStrBldXML = new StringBuilder();

			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<Flag>");
			lStrBldXML.append(status);
			lStrBldXML.append("</Flag>");
			lStrBldXML.append("</XMLDOC>");

			return lStrBldXML;
		}
		
		//START added by samadhan for pin code validation
		
		public ResultObject validatePinCode(Map objectArgs)
		{
			//gLogger.info("inside validatePinCode");
			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			String pinCode=null;
			Long pinCodeCount=null;
			
			
			String lStrResult = null;
			try {
				
				
				pinCode=StringUtility.getParameter("pinCode", request).trim();
				gLogger.info("--------pinCode--------:"+pinCode);
				
				
				Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
				long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
				PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
				OrgDdoMst ddoMst  = null;
				if(ddoList!=null && ddoList.size()>0) {
					 ddoMst = ddoList.get(0);
				}
				
				String ddoCode = null;
				if(ddoMst!=null)
				 ddoCode = ddoMst.getDdoCode();
				gLogger.info("--------ddoCode--------:"+ddoCode);
				
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				
				String districtID=lObjDcpsCommonDAO.getDistrictId(ddoCode);
				pinCodeCount=lObjDcpsCommonDAO.getPinCodeCount(pinCode,districtID);
				
				String status=null;
				if(pinCodeCount==0){
					status="wrong";
				}

				else if(pinCodeCount > 0){
					status="correct";
				}
				String lSBStatus = getResponseXMLDocForValidatePin(status).toString();
				lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
				
				objectArgs.put("ajaxKey", lStrResult);
				objRes.setResultValue(objectArgs);
				objRes.setViewName("ajaxData");
				
			} catch (Exception e) {
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
			}
			return objRes;
		}
		private StringBuilder getResponseXMLDocForValidatePin(String status) {

			StringBuilder lStrBldXML = new StringBuilder();

			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<Flag>");
			lStrBldXML.append(status);
			lStrBldXML.append("</Flag>");
			lStrBldXML.append("</XMLDOC>");

			return lStrBldXML;
		}
		
		//END added by samadhan for pin code validation
		//START added by samadhan for UID number uniqeness validation ajax
		
		public ResultObject validateUIDUniqeness(Map objectArgs)
		{
			//gLogger.info("inside validateUIDUniqeness");
			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			String UID=null;
			
			Long finalCheckFlag=0l;
			String lStrResult = null;
			try {
				
				
				/*UID=StringUtility.getParameter("UID", request).trim();
				gLogger.info("--------UID--------:"+UID);*/
				
				//BankDetailsUpdateDao lObjBankDetailsUpdateDaoImpl=new BankDetailsUpdateDaoImpl(TrnDcpsContribution.class,serv.getSessionFactory());
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				
				String uidNo=null;
				Long dcpsEmpID=null;
				int countUIDNo=0;
				if (StringUtility.getParameter("uid", request) != null && !StringUtility.getParameter("uid", request).equals("") && Long.parseLong(StringUtility.getParameter("uid", request)) != -1) {
					uidNo = StringUtility.getParameter(
							"uid", request).trim();
					gLogger.info("--------UID--------:"+uidNo);
					dcpsEmpID = Long.parseLong(StringUtility.getParameter(
							"dcpsEmpID", request).trim());
					finalCheckFlag=lObjDcpsCommonDAO.checkUIDNumber(uidNo,dcpsEmpID);
				}
				
				String status=null;
				if(finalCheckFlag>0){
					status="wrong";
				}

				else{
					status="correct";
				}
				String lSBStatus = getResponseXMLDocForDDOFwd(status).toString();
				lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
				
				objectArgs.put("ajaxKey", lStrResult);
				objRes.setResultValue(objectArgs);
				objRes.setViewName("ajaxData");
				
			} catch (Exception e) {
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
			}
			return objRes;
		}
		
		private StringBuilder getResponseXMLDocForDDOFwd(String status) {

			StringBuilder lStrBldXML = new StringBuilder();

			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<Flag>");
			lStrBldXML.append(status);
			lStrBldXML.append("</Flag>");
			lStrBldXML.append("</XMLDOC>");

			return lStrBldXML;
		}
		//END added by samadhan for UID number uniqeness validation ajax
		
		
		//Added by Abhishek
		public ResultObject getAssistantDDOList(Map inputMap) throws Exception
		{

			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			try
			{
				//gLogger.info("inside test service : viewApprovedForms");
				setSessionInfo(inputMap);
				String strPostId = "";
				String strUserId = "";
				strPostId = gLngPostId.toString();
				strUserId = gLngUserId.toString();
				gLogger.info("strPostId: "+strPostId);
				gLogger.info("strUserId: "+strUserId);

				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv.getSessionFactory());
				String strDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(Long.parseLong(strPostId));
				gLogger.info("############################strDDOCode############################: "+strDDOCode);
				List lstAstDDODtls = lObjNewRegDdoDAO.getEmpDtls(strDDOCode);
				inputMap.put("lstAstDDODtls", lstAstDDODtls);
				resObj.setResultValue(inputMap);
				resObj.setViewName("getAssistantDDOList");

			}
			catch (Exception e)
			{
				resObj.setResultValue(null);
				resObj.setThrowable(e);
				resObj.setResultCode(ErrorConstants.ERROR);
				resObj.setViewName("errorPage");
			}

			return resObj;
		}
		
		public ResultObject updatetAssistantDDOListDetails(Map inputMap) throws Exception
		{

			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			try
			{
				//gLogger.info("inside test service : updatetAssistantDDOListDetails");
				setSessionInfo(inputMap);
				
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv.getSessionFactory());
					
				
	            String headOfAcctCode="";
	            String DDOCode="";
	            
	            headOfAcctCode = StringUtility.getParameter("headOfAcctCode", request);
	            DDOCode = StringUtility.getParameter("DDOCode", request);
	            										
	            
	            String arrHeadOfAcctCode[]=headOfAcctCode.split("~");
	            String arrDDOCode[]=DDOCode.split("~");
	            
	            gLogger.info("Head Of Account Code :"+headOfAcctCode);
	            gLogger.info("DDO Code : "+DDOCode);
	            
	            for(int i=0;i<arrDDOCode.length;i++){
	            	lObjNewRegDdoDAO.updateHeadOfAccountCode(arrHeadOfAcctCode[i],arrDDOCode[i]);	
	            }
	 			
	           
				String strDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(Long.parseLong(gLngPostId.toString()));
				gLogger.info("############################strDDOCode############################: "+strDDOCode);
				List lstAstDDODtls = lObjNewRegDdoDAO.getEmpDtls(strDDOCode);
				inputMap.put("lstAstDDODtls", lstAstDDODtls);
				resObj.setResultValue(inputMap);
				resObj.setViewName("getAssistantDDOList");

			}
			catch (Exception e) {
				resObj.setResultValue(null);
				resObj.setThrowable(e);
				resObj.setResultCode(ErrorConstants.ERROR);
				resObj.setViewName("errorPage");
			}
			return resObj;
		}
		
		public ResultObject getBranchList(Map<String, Object> lMapInputMap) {
			//gLogger.info("hiii i m for branch name");

			ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS,
			"FAIL");
			List branchList = null;
			String cmbBank=null;
			ServiceLocator serv = (ServiceLocator)lMapInputMap.get("serviceLocator");
			Map loginDetailsMap = (Map) lMapInputMap.get("baseLoginMap");

			Map voToService = (Map)lMapInputMap.get("voToServiceMap");
		
			try {

				/* Sets the Session Information */
				setSessionInfo(lMapInputMap);

				/* Initializes the DAO */
				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
				
				cmbBank = StringUtility.getParameter("cmbBank", request).trim();
				gLogger.info("hhi bank name  is"+cmbBank);
				
				if((cmbBank!=null) && (cmbBank!="") && (Long.parseLong(cmbBank)!=-1)){
					
					branchList=lObjNewRegDdoDAO.getBranchList(cmbBank);
				}
				
				

				String lStrTempResult = null;
				if (branchList != null) {
					lStrTempResult = new AjaxXmlBuilder().addItems(
							branchList, "desc", "id", true).toString();
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
		
		//Added By Tejashree for Rate Per Hours//
		public ResultObject getEmployeeListofRateperHours(Map inputMap) throws Exception
		{

			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			PasswordEncryption objPasswordEncryption = new PasswordEncryption();
			try
			{
				//gLogger.info("in getEmployeeListForBasicUpdate");
				setSessionInfo(inputMap);

				String lStrUserZP = "zpDDO";
				String lStrUseZP = "bank_barnch_update";
				//String lStrUseZP = "";

				inputMap.put("User", lStrUserZP.trim());
				inputMap.put("Use", lStrUseZP.trim());
				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());
				String empIds=null;
				String perBasic=null;
				String newBasic=null;
				String wefDate=null;
				Double revisedBasic=null;
				Double revisedBasic1=null;
				Double revisedBasic2=null;
				Double basic=null;
				Double basic1=null;
				
				String genTokenNo=null;
				if((StringUtility.getParameter("genTokenNo", request)!=null)&&(StringUtility.getParameter("genTokenNo", request)!="")){
					genTokenNo= StringUtility.getParameter("genTokenNo", request);
					genTokenNo=genTokenNo.substring(0,genTokenNo.length()-1);
					gLogger.info("hiii genTokenNo is.:::::::"+genTokenNo);
				}
				
				String genRandomNo=null;
				if((StringUtility.getParameter("genRandomNo", request)!=null)&&(StringUtility.getParameter("genRandomNo", request)!="")){
					genRandomNo= StringUtility.getParameter("genRandomNo", request);
					
					gLogger.info("hiii genRandomNo is.:::::::"+genRandomNo);
				}
				String tokenNo = "120"+"getE"+genRandomNo;
				String encToken = objPasswordEncryption.crypt(tokenNo);
				
				if((StringUtility.getParameter("empIds", request)!=null)&&(StringUtility.getParameter("empIds", request)!="")){
					//if (genTokenNo.equals(encToken)){
					empIds= StringUtility.getParameter("empIds", request);
					perBasic= StringUtility.getParameter("perBasic", request);	
					newBasic=StringUtility.getParameter("newBasic", request);
					wefDate=StringUtility.getParameter("wEffectDate", request);
								
					String[] lstrIds = empIds.split("~");
					String[] lstrPervalue = perBasic.split("~");
					String[] newBasicArr=newBasic.split("~");
					String[] withEFDate=wefDate.split("~");
					
					String[] lstrEmpIDs = new String[lstrIds.length];
					String[] lstrPerValueArr = new String[lstrPervalue.length];
					String[] lstrNewBasic= new String[newBasicArr.length];
					String[] withEFDateArr= new String[withEFDate.length];
					
					for (Integer lInt = 0; lInt < lstrIds.length; lInt++)
					{
						if (lstrIds[lInt] != null && !"".equals(lstrIds[lInt]))
						{
							lstrEmpIDs[lInt] = lstrIds[lInt];
							lstrPerValueArr[lInt] = lstrPervalue[lInt];
							lstrNewBasic[lInt]=newBasicArr[lInt];
							withEFDateArr[lInt]=withEFDate[lInt];
								
							gLogger.info("hii********** "+lstrEmpIDs[lInt]);
							gLogger.info("hii********** "+lstrPerValueArr[lInt]);//400
							gLogger.info("hii********** "+lstrNewBasic[lInt]);
							gLogger.info("hii********** "+withEFDateArr[lInt]);
							
						}
					}

					for (Integer lInt = 0; lInt < lstrIds.length; lInt++)
					{
						revisedBasic=Double.parseDouble(lstrNewBasic[lInt]);
						basic= revisedBasic;                  //     BigDecimal.valueOf(revisedBasic);
						//basic=basic.divide(BigDecimal.valueOf(1), 0, BigDecimal.ROUND_UP);
						
						revisedBasic1=Double.parseDouble(lstrPerValueArr[lInt]);
						
						basic1= revisedBasic1;                //BigDecimal.valueOf(revisedBasic1);
						//basic1 //basic1.divide(BigDecimal.valueOf(1), 0, BigDecimal.ROUND_UP);
						revisedBasic2=(basic * basic1);
						

			lObjNewRegDdoDAO.updateDetailsRateperhour(lstrEmpIDs[lInt],lstrPerValueArr[lInt],basic.toString(),withEFDateArr[lInt],revisedBasic2.toString());

					}
				//}
				}
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				gLogger.info("hi "+lStrDdoCode);
				List empList = lObjNewRegDdoDAO.getEmployeeListofRateperHours(lStrDdoCode);
				int empsize=empList.size();
				gLogger.info("The size of given list is:"+empList.size());
				inputMap.put("EditForm", "N");
				inputMap.put("empList", empList);
				inputMap.put("ddoCode", lStrDdoCode);
				inputMap.put("Empsize", empsize);

				//added by roshan for performance tuning
				empList=null;
				//ended by roshan for performance tuning

				resObj.setResultValue(inputMap);
				resObj.setViewName("getEmployeeListofRateperHours");

			}
			catch (Exception e)
			{
				resObj.setResultValue(null);
				resObj.setThrowable(e);
				resObj.setResultCode(ErrorConstants.ERROR);
				resObj.setViewName("errorPage");
			}

			return resObj;
		}
	
		public ResultObject chkDCPSIDalreadyExists(Map objectArgs) throws Exception
		{

			

			//gLogger.info("inside validateUIDUniqeness");
			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			String UID=null;
			
			Long finalCheckFlag=0l;
			Long dcpsIdExixts = null;
			String lStrResult = null;
			String empName = null;
			try {
				
				
				/*UID=StringUtility.getParameter("UID", request).trim();
				gLogger.info("--------UID--------:"+UID);*/
				
				//BankDetailsUpdateDao lObjBankDetailsUpdateDaoImpl=new BankDetailsUpdateDaoImpl(TrnDcpsContribution.class,serv.getSessionFactory());
				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv.getSessionFactory());
				String dcpsId=null;
				Long dcpsEmpID=null;
				int countUIDNo=0;
				if (StringUtility.getParameter("dcpsId", request) != null && !StringUtility.getParameter("dcpsId", request).equals("")) {
					dcpsId = StringUtility.getParameter(
							"dcpsId", request).trim();
					gLogger.info("--------dcpsId--------:"+dcpsId);
				
				 dcpsIdExixts=lObjNewRegDdoDAO.chkDCPSIDalreadyExists(dcpsId);
				 empName=lObjNewRegDdoDAO.chkempNameforDCPSIDalreadyExists(dcpsId);
				}
				
				String count=null;
				if(dcpsIdExixts==0){
					count="NA";
				}else {
					count="Y";
				}
				
				String lSBStatus = getResponseXMLDocForDCPSID(count,empName).toString();
				lStrResult = new AjaxXmlBuilder().addItem("ajax_key",lSBStatus).toString();
				
				objectArgs.put("ajaxKey",lStrResult);
				objRes.setResultValue(objectArgs);
				objRes.setViewName("ajaxData");
				
			} catch (Exception e) {
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
			}
			return objRes;
		
		}
		
		private StringBuilder getResponseXMLDocForDCPSID(String status,String empName) {

			StringBuilder lStrBldXML = new StringBuilder();

			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<Flag>");
			lStrBldXML.append(status);
			lStrBldXML.append("</Flag>");
			lStrBldXML.append("<empFlag>");
			lStrBldXML.append(empName);
			lStrBldXML.append("</empFlag>");
			lStrBldXML.append("</XMLDOC>");

			return lStrBldXML;
		}
		
		public ResultObject chkPANalreadyExists(Map objectArgs) throws Exception
		{

			

			//gLogger.info("inside validateUIDUniqeness");
			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			String UID=null;
			
			Long finalCheckFlag=0l;
			Long panNoExixts = null;
			String lStrResult = null;
			String empName = null;
			try {
				
				
				/*UID=StringUtility.getParameter("UID", request).trim();
				gLogger.info("--------UID--------:"+UID);*/
				
				//BankDetailsUpdateDao lObjBankDetailsUpdateDaoImpl=new BankDetailsUpdateDaoImpl(TrnDcpsContribution.class,serv.getSessionFactory());
				NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv.getSessionFactory());
				String panNo=null;
				String dcpsEmpID=null;
				String sevaarthId=null;
				int countUIDNo=0;
				if (StringUtility.getParameter("panNo", request) != null && !StringUtility.getParameter("panNo", request).equals("") 
						&& StringUtility.getParameter("dcpsEmpId", request) != null && !StringUtility.getParameter("dcpsEmpId", request).equals("")) {
					panNo = StringUtility.getParameter(
							"panNo", request).trim();
					dcpsEmpID = StringUtility.getParameter(
							"dcpsEmpId", request).trim();
					gLogger.info("--------panNo--------:"+panNo);
					gLogger.info("--------dcpsEmpId--------:"+dcpsEmpID);
				
					panNoExixts=lObjNewRegDdoDAO.chkPANalreadyExists(panNo,dcpsEmpID);
				 empName=lObjNewRegDdoDAO.chkempNameforPANalreadyExists(panNo);
				}else if(StringUtility.getParameter("panNo", request) != null && !StringUtility.getParameter("panNo", request).equals("") 
						&& StringUtility.getParameter("sevarthId", request) != null && !StringUtility.getParameter("sevarthId", request).equals("")) {
					panNo = StringUtility.getParameter(
							"panNo", request).trim();
					sevaarthId = StringUtility.getParameter(
							"sevarthId", request).trim();
					gLogger.info("--------panNo--------:"+panNo);
					gLogger.info("--------sevarthId--------:"+sevaarthId);
				
					panNoExixts=lObjNewRegDdoDAO.chkPANalreadyExistsforCSRF(panNo,sevaarthId);
				 empName=lObjNewRegDdoDAO.chkempNameforPANalreadyExists(panNo);
					
				}
				else {
					panNo = StringUtility.getParameter(
							"panNo", request).trim();
					dcpsEmpID = StringUtility.getParameter(
							"dcpsEmpId", request).trim();
					gLogger.info("--------panNo--------:"+panNo);
					gLogger.info("--------dcpsEmpId--------:"+dcpsEmpID);
				
					panNoExixts=lObjNewRegDdoDAO.chkPANalreadyExistsForEmpConfig(panNo);
				 empName=lObjNewRegDdoDAO.chkempNameforPANalreadyExists(panNo);
				}
				/*if (StringUtility.getParameter("dcpsEmpId", request) != null && !StringUtility.getParameter("dcpsEmpId", request).equals("")) {
					dcpsEmpID = StringUtility.getParameter(
							"dcpsEmpId", request).trim();
					gLogger.info("--------panNo--------:"+panNo);
					
					panNoExixts=lObjNewRegDdoDAO.chkPANalreadyExists(panNo,dcpsEmpID);
					empName=lObjNewRegDdoDAO.chkempNameforPANalreadyExists(panNo);
				}*/
				
				String count=null;
				if(panNoExixts==0){
					count="NA";
				}else {
					count="Y";
				}
				
				String lSBStatus = getResponseXMLDocForPAN(count,empName).toString();
				lStrResult = new AjaxXmlBuilder().addItem("ajax_key",lSBStatus).toString();
				
				objectArgs.put("ajaxKey",lStrResult);
				objRes.setResultValue(objectArgs);
				objRes.setViewName("ajaxData");
				
			} catch (Exception e) {
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
			}
			return objRes;
		
		}
		private StringBuilder getResponseXMLDocForPAN(String status,String empName) {

			StringBuilder lStrBldXML = new StringBuilder();

			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<Flag>");
			lStrBldXML.append(status);
			lStrBldXML.append("</Flag>");
			lStrBldXML.append("<empFlag>");
			lStrBldXML.append(empName);
			lStrBldXML.append("</empFlag>");
			lStrBldXML.append("</XMLDOC>");

			return lStrBldXML;
		}
		
		//Ended By Tejashree//
}
