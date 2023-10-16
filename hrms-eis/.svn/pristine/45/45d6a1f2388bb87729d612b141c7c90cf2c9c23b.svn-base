package com.tcs.sgv.eis.service;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmployeeStatisticsViewDAOImpl;
import com.tcs.sgv.eis.valueobject.EmployeeStatisticsViewVO;
import com.tcs.sgv.eis.valueobject.InstitueConfigurationViewVO;
import com.tcs.sgv.eis.zp.zpDdoOffice.service.ZpDDOOfficeServiceImpl;

public class EmployeeStatisticsViewServiceImpl extends ServiceImpl{

	private static Logger logger = Logger.getLogger( ZpDDOOfficeServiceImpl.class );
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/eis/zp/zpAdminOffice/ZpAdminOfficeLabels_en_US");
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private final Date todayDate = new Date();
	/* Global Variable for PostId */
	private Long POST_ID = null;
	/* Global Variable for UserId */
	private Long USER_ID = null;
	/* Global Variable for LangId */
	private Long LANG_ID = null;
	/* Global Variable for LocationId */
	private String LOC_ID = "";
	
	
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

		}

	}
	
	public ResultObject demoDisplayService(Map objArgs)
	{
		logger.info("Within the demoDisplayService");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		//HttpServletRequest request = (HttpServletRequest) objArgs.get("requestObj");
		ServiceLocator serviceLocator =  (ServiceLocator) objArgs.get("serviceLocator");
		Map loginMap = (Map)objArgs.get("baseLoginmap");
		EmployeeStatisticsViewDAOImpl employeeStatisticsViewDAOImpl = new EmployeeStatisticsViewDAOImpl(EmployeeStatisticsViewVO.class,serviceLocator.getSessionFactory());
		try{
			
			List demoViewLst = new ArrayList();
			
			demoViewLst = employeeStatisticsViewDAOImpl.getDemoDisplayValues();
			logger.info("Size of Demo Value"+demoViewLst.size());						
			objArgs.put("demoViewLst", demoViewLst);			
			objRes.setResultValue(objArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("shalarthReportFilter");
			
		}catch(Exception e)
		{
			logger.info("Null pointer Exception in demoDisplayService");
			logger.error("Error is "+e.getMessage());
		}
		
		
		return objRes;
	}

	//start by roshan
	public ResultObject employeeStatisticsReport(Map objectArgs) throws Exception//gayathri
	{
		logger.info("Entering into  employeeStatisticsReport of EmployeeStatisticsViewServiceImpl");
		HttpServletRequest requests = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		EmployeeStatisticsViewDAOImpl employeeStatisticsViewDAOImpl = new EmployeeStatisticsViewDAOImpl(EmployeeStatisticsViewDAOImpl.class,serviceLocator.getSessionFactory());
		
		long district_id = 0;
		long saveasdraft = 0;
		long forwadedfromdraft = 0;
		long formapproved = 0;
		long totalnumberofemployees = 0;
		long balancenumberofemployees = 0;
		long sno;
		
		long noOfSchoolsPending=0;
		//start by roshan
		String districtName=null;
		String typeOfSchool=null;
		long noOfschoolsConfigured=0;
		long noOfSchoolsApproved=0;
		long noOfschoolsRejected=0;
		long dataEntryInitiated=0;
		long noOfEmployeeSavedOrForwarded=0;
		//end by roshan
		long schoolvalidationpendingbyDDO=0;
		long schoolvalidationapprovedbyDDO=0;
		long totalApproveSchholsShalarth=0;
		long noOfschoolsPendingShalarth=0;
		long noOfschoolsConfiguredShalarth=0;
		long noOfschoolsRejectedShalarth=0;
		Long adminoffice_id=null;
		long noOfschoolsValidatedbyLvl2ddoShalarth=0;
		try{
			

			List demoViewLst = new ArrayList();
			List adminOfficeLst = new ArrayList();
			demoViewLst = employeeStatisticsViewDAOImpl.getDemoDisplayValues();
			adminOfficeLst=employeeStatisticsViewDAOImpl.getAdminOfficeValues();
			logger.info("Size of admin officelist Value"+adminOfficeLst.size());
			objectArgs.put("demoViewLst", demoViewLst);
			objectArgs.put("adminOfficeLst", adminOfficeLst);
			String districtid =StringUtility.getParameter("district_id",requests);
			logger.info("District ID ::::::::::"+districtid);
			String adminofficeid =StringUtility.getParameter("adminoffice_id",requests);
			logger.info("adminofficeid ::::::::::"+adminofficeid);
			if(districtid!=null && districtid!=""){
				logger.info("sunitha ------");
				district_id = Long.parseLong(districtid);
				adminoffice_id = Long.parseLong(adminofficeid);
				
			
			
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			//added by roshan
			districtName=employeeStatisticsViewDAOImpl.findDistrictName(district_id);
			typeOfSchool=employeeStatisticsViewDAOImpl.typeOfSchool(adminoffice_id);
			dataEntryInitiated=employeeStatisticsViewDAOImpl.NoOfSchoolDataEntryInitiated(district_id,adminoffice_id);
			noOfEmployeeSavedOrForwarded=employeeStatisticsViewDAOImpl.noOfEmployeeSavedOrForwarded(district_id,adminoffice_id);
			//end by roshan
			totalApproveSchholsShalarth = employeeStatisticsViewDAOImpl.totalApproveSchholsShalarth(district_id,adminoffice_id);
			logger.info("noOfSchoolsApproved ::::::::"+totalApproveSchholsShalarth);
			noOfschoolsPendingShalarth = employeeStatisticsViewDAOImpl.noOfschoolsPendingShalarth(district_id,adminoffice_id);
			logger.info("noOfschoolsPendingShalarth ::::::::"+noOfschoolsPendingShalarth);
			noOfschoolsConfiguredShalarth = employeeStatisticsViewDAOImpl.noOfschoolsConfiguredShalarth(district_id,adminoffice_id);
			logger.info("noOfschoolsConfiguredShalarth ::::::::"+noOfschoolsConfiguredShalarth);
			noOfschoolsRejectedShalarth = employeeStatisticsViewDAOImpl.noOfschoolsRejectedShalarth(district_id,adminoffice_id);
			logger.info("noOfschoolsRejectedShalarth ::::::::"+noOfschoolsRejectedShalarth);
			
			noOfschoolsValidatedbyLvl2ddoShalarth = employeeStatisticsViewDAOImpl.noOfschoolsValidatedbyLvl2ddoShalarth(district_id,adminoffice_id);
			logger.info("noOfschoolsValidatedbyLvl2ddoShalarth ::::::::"+noOfschoolsValidatedbyLvl2ddoShalarth);
			}
			//Added by roshan
			objectArgs.put("districtName",districtName);
			objectArgs.put("typeOfSchool",typeOfSchool);
			
			objectArgs.put("dataEntryInitiated",dataEntryInitiated);
			objectArgs.put("noOfEmployeeSavedOrForwarded",noOfEmployeeSavedOrForwarded);
			objectArgs.put("schoolvalidationpendingbyDDO",schoolvalidationpendingbyDDO);
			objectArgs.put("schoolvalidationapprovedbyDDO",schoolvalidationapprovedbyDDO);
			objectArgs.put("noOfSchoolsApproved",noOfSchoolsApproved);
			objectArgs.put("noOfSchoolsPending",noOfSchoolsPending);
			objectArgs.put("noOfschoolsConfigured",noOfschoolsConfigured);
			objectArgs.put("noOfschoolsConfigured",noOfschoolsConfigured);
			//added by roshan
			
			
			//objectArgs.put("noOfschoolsRejected",noOfschoolsRejected);
			objectArgs.put("noOfschoolsRejected",noOfschoolsRejectedShalarth);
			objectArgs.put("district_id",district_id);
			objectArgs.put("saveasdraft",saveasdraft);
			objectArgs.put("forwadedfromdraft",forwadedfromdraft);
			objectArgs.put("formapproved",formapproved);
			objectArgs.put("totalnumberofemployees",totalnumberofemployees);
			objectArgs.put("balancenumberofemployees",balancenumberofemployees);
			//added by sunitha for shalarath
			
			objectArgs.put("totalApproveSchholsShalarth",totalApproveSchholsShalarth);
			objectArgs.put("noOfschoolsPendingShalarth",noOfschoolsPendingShalarth);
			objectArgs.put("noOfschoolsConfiguredShalarth",noOfschoolsConfiguredShalarth);
			objectArgs.put("noOfschoolsValidatedbyLvl2ddoShalarth",noOfschoolsValidatedbyLvl2ddoShalarth);
			
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("employeeStatisticsReport");
		}
		catch(Exception e)
		{
			logger.info("Null Pointer Exception Ocuures...approveDdoOfficeDataList");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");	
		}
		return objRes;
	}
	
	
	//end by roshan
	
	
	public ResultObject employeeViewStatisticsReportNew(Map objectArgs)
	{
		logger.info("Entering into  employeeStatisticsReport of EmployeeStatisticsViewServiceImpl");
		//HttpServletRequest requests = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		EmployeeStatisticsViewDAOImpl employeeStatisticsViewDAOImpl = new EmployeeStatisticsViewDAOImpl(EmployeeStatisticsViewDAOImpl.class,serviceLocator.getSessionFactory());
	try
	{
		//List<EmployeeStatisticsViewVO> employeeStatisticsViewVOlst = new ArrayList<EmployeeStatisticsViewVO>();
		//employeeStatisticsViewVOlst = employeeStatisticsViewDAOImpl.viewEmployeeStatisticsReport();
		//logger.info("employeeStatisticsViewVOlst ::::::::::"+employeeStatisticsViewVOlst.size());
		//objectArgs.put("employeeStatisticsViewVOlst",employeeStatisticsViewVOlst);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("employeeStatisticsReport");
		
	}catch(Exception e)
	{
		logger.info("Null Pointer Exception Ocuures...employeeViewStatisticsReport");
		logger.error("Error is: "+ e.getMessage());
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		objRes.setResultValue(objectArgs);
		objRes.setViewName("errorInsert");	
	}
		return objRes;
	}
	public ResultObject schoolDataValidation(Map objectArgs) throws Exception//sunitha
	{
		logger.info("Entering into  schoolDataValidation of EmployeeStatisticsViewServiceImpl");
		HttpServletRequest requests = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		EmployeeStatisticsViewDAOImpl employeeStatisticsViewDAOImpl = new EmployeeStatisticsViewDAOImpl(EmployeeStatisticsViewDAOImpl.class,serviceLocator.getSessionFactory());
		
		long district_id = 0;
		long saveasdraft = 0;
		long forwadedfromdraft = 0;
		long formapproved = 0;
		long totalnumberofemployees = 0;
		long balancenumberofemployees = 0;
		long sno;
		long noOfSchoolsApproved=0;
		long noOfSchoolsPending=0;
		long noOfschoolsConfigured=0;
		long noOfschoolsRejected=0;
		long schoolvalidationpendingbyDDO=0;
		long schoolvalidationapprovedbyDDO=0;
		try{
			
			List demoViewLst = new ArrayList();
			demoViewLst = employeeStatisticsViewDAOImpl.getDemoDisplayValues();
			//logger.info("Size of Demo Value"+demoViewLst.size());
			objectArgs.put("demoViewLst", demoViewLst);
			
			String districtid =StringUtility.getParameter("district_id",requests);
			logger.info("District ID ::::::::::"+districtid);
			
			if(districtid!=null && districtid!=""){
				district_id = Long.parseLong(districtid);
			
			//EmployeeStatisticsViewVO employeeStatisticsViewVO = new EmployeeStatisticsViewVO();
			
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			
			//List<EmployeeStatisticsViewVO> employeeStatisticsViewVOlst = new ArrayList<EmployeeStatisticsViewVO>();
			///IdGenerator idgenu=new IdGenerator();
			//sno=employeeStatisticsViewDAOImpl.getmaxsnoid();
						
			//logger.info("SNO :::::::"+sno);
			//.getNextId("EMPLOYEESTATISTICS",objectArgs);
			
			//employeeStatisticsViewVO.setSno(sno);
			//logger.info("SNO :::::::"+sno);
			
			
			
			
			//Changed by roshan.....on 25-12-12
			noOfschoolsConfigured = employeeStatisticsViewDAOImpl.totalApproveSchhols(district_id);
			
			logger.info("noOfschoolsConfigured ::::::::"+noOfschoolsConfigured);
			noOfschoolsRejected = employeeStatisticsViewDAOImpl.noOfschoolsRejected(district_id);
			logger.info("noOfschoolsRejected ::::::::"+noOfschoolsRejected);
			schoolvalidationpendingbyDDO = employeeStatisticsViewDAOImpl.schoolvalidationpendingbyDDO(district_id);
			logger.info("schoolvalidationpendingbyDDO ::::::::"+schoolvalidationpendingbyDDO);
			schoolvalidationapprovedbyDDO = employeeStatisticsViewDAOImpl.schoolvalidationapprovedbyDDO(district_id);
			logger.info("schoolvalidationapprovedbyDDO ::::::::"+schoolvalidationapprovedbyDDO);
			
			}
			
			objectArgs.put("schoolvalidationpendingbyDDO",schoolvalidationpendingbyDDO);
			objectArgs.put("schoolvalidationapprovedbyDDO",schoolvalidationapprovedbyDDO);
			
			objectArgs.put("noOfschoolsConfigured",noOfschoolsConfigured);
			
			objectArgs.put("district_id",district_id);
			objectArgs.put("saveasdraft",saveasdraft);
		
			
			
		/*	employeeStatisticsViewVOlst = employeeStatisticsViewDAOImpl.viewEmployeeStatisticsReport();
			logger.info("employeeStatisticsViewVOlst ::::::::::"+employeeStatisticsViewVOlst.size());*/
			
			//objectArgs.put("employeeStatisticsViewVOlst",employeeStatisticsViewVOlst);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("schoolValidation");
		}
		catch(Exception e)
		{
			logger.info("Null Pointer Exception Ocuures...approveDdoOfficeDataList");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");	
		}
		return objRes;
	}
	
	
	public ResultObject employeeDataValidation(Map objectArgs) throws Exception//sunitha
	{
		logger.info("Entering into  employeeStatisticsReport of EmployeeStatisticsViewServiceImpl");
		HttpServletRequest requests = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		EmployeeStatisticsViewDAOImpl employeeStatisticsViewDAOImpl = new EmployeeStatisticsViewDAOImpl(EmployeeStatisticsViewVO.class,serviceLocator.getSessionFactory());
		
		long district_id = 0;
		long saveasdraft = 0;
		long forwadedfromdraft = 0;
		long formapproved = 0;
		long totalnumberofemployees = 0;
		long balancenumberofemployees = 0;
		long ddocode=0;
		long sno;
		long noOfSchoolsApproved=0;
		long noOfSchoolsPending=0;
		long noOfschoolsConfigured=0;
		long noOfschoolsRejected=0;
		long schoolvalidationpendingbyDDO=0;
		long schoolvalidationapprovedbyDDO=0;
		try{
			
			List demoViewLst = new ArrayList();
			demoViewLst = employeeStatisticsViewDAOImpl.getDemoDisplayValues();
			//logger.info("Size of Demo Value"+demoViewLst.size());
			objectArgs.put("demoViewLst", demoViewLst);
			
			String districtId =StringUtility.getParameter("district_id",requests);
			logger.info("DDO Code ::::::::::"+districtId);
						
			if(districtId!=null && districtId!=""){
				district_id = Long.parseLong(districtId);
			}
			String ddocde =StringUtility.getParameter("ddocode",requests);
			logger.info("DDO Code ::::::::::"+ddocde);
			
			if(ddocde!=null && ddocde!=""){
				ddocode = Long.parseLong(ddocde);
			}
			EmployeeStatisticsViewVO employeeStatisticsViewVO = new EmployeeStatisticsViewVO();
			
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			
			List<EmployeeStatisticsViewVO> employeeStatisticsViewVOlst = new ArrayList<EmployeeStatisticsViewVO>();
			///IdGenerator idgenu=new IdGenerator();
			//sno=employeeStatisticsViewDAOImpl.getmaxsnoid();
						
			//logger.info("SNO :::::::"+sno);
			//.getNextId("EMPLOYEESTATISTICS",objectArgs);
			
			//employeeStatisticsViewVO.setSno(sno);
			//logger.info("SNO :::::::"+sno);
			
			saveasdraft = employeeStatisticsViewDAOImpl.countsaveasdraft(district_id);
			logger.info("Save as Draft :::::::"+saveasdraft);
			//objRes.put("saveasdraft",saveasdraft);
			employeeStatisticsViewVO.setSaveasdraft(saveasdraft);
			
			forwadedfromdraft = employeeStatisticsViewDAOImpl.countforwadedfromdraft(district_id);
			logger.info("Forwaded from Draft :::::::"+forwadedfromdraft);
			employeeStatisticsViewVO.setForwadedfromdraft(forwadedfromdraft);
			
			formapproved = employeeStatisticsViewDAOImpl.countformapproved(district_id);
			logger.info("Form Approved ::::::::"+formapproved);
			employeeStatisticsViewVO.setFormapproved(formapproved);
			
			totalnumberofemployees = employeeStatisticsViewDAOImpl.totalNoOfEmployee(district_id);
			logger.info("Total Number of Employees ::::::::"+totalnumberofemployees);
			employeeStatisticsViewVO.setTotalnumberofemployees(totalnumberofemployees);
			
			balancenumberofemployees = totalnumberofemployees-formapproved;
			logger.info("Balance Number of Employees ::::::::"+balancenumberofemployees);
			employeeStatisticsViewVO.setBalancenumberofemployees(balancenumberofemployees);
			
			//employeeStatisticsViewDAOImpl.create(employeeStatisticsViewVO);
			//logger.info("::::::::::Insertion Successful::::::::::::");
			
//			noOfSchoolsApproved = employeeStatisticsViewDAOImpl.totalApproveSchhols(ddocode);
//			logger.info("noOfSchoolsApproved ::::::::"+noOfSchoolsApproved);
//			noOfSchoolsPending = employeeStatisticsViewDAOImpl.noOfschoolsPending(ddocode);
//			logger.info("noOfSchoolsPending ::::::::"+noOfSchoolsPending);
//			noOfschoolsConfigured = employeeStatisticsViewDAOImpl.noOfschoolsConfigured(ddocode);
//			logger.info("noOfschoolsConfigured ::::::::"+noOfschoolsConfigured);
//			noOfschoolsRejected = employeeStatisticsViewDAOImpl.noOfschoolsRejected(ddocode);
//			logger.info("noOfschoolsRejected ::::::::"+noOfschoolsRejected);
//			schoolvalidationpendingbyDDO = employeeStatisticsViewDAOImpl.schoolvalidationpendingbyDDO(ddocode);
//			logger.info("schoolvalidationpendingbyDDO ::::::::"+schoolvalidationpendingbyDDO);
//			schoolvalidationapprovedbyDDO = employeeStatisticsViewDAOImpl.schoolvalidationapprovedbyDDO(ddocode);
//			logger.info("schoolvalidationapprovedbyDDO ::::::::"+schoolvalidationapprovedbyDDO);
			
			
			
			
			objectArgs.put("schoolvalidationpendingbyDDO",schoolvalidationpendingbyDDO);
			objectArgs.put("schoolvalidationapprovedbyDDO",schoolvalidationapprovedbyDDO);
			objectArgs.put("noOfSchoolsApproved",noOfSchoolsApproved);
			objectArgs.put("noOfSchoolsPending",noOfSchoolsPending);
			objectArgs.put("noOfschoolsConfigured",noOfschoolsConfigured);
			objectArgs.put("noOfschoolsRejected",noOfschoolsRejected);
			objectArgs.put("district_id",district_id);
			objectArgs.put("saveasdraft",saveasdraft);
			objectArgs.put("forwadedfromdraft",forwadedfromdraft);
			objectArgs.put("formapproved",formapproved);
			objectArgs.put("totalnumberofemployees",totalnumberofemployees);
			objectArgs.put("balancenumberofemployees",balancenumberofemployees);
			
		/*	employeeStatisticsViewVOlst = employeeStatisticsViewDAOImpl.viewEmployeeStatisticsReport();
			logger.info("employeeStatisticsViewVOlst ::::::::::"+employeeStatisticsViewVOlst.size());*/
			
			//objectArgs.put("employeeStatisticsViewVOlst",employeeStatisticsViewVOlst);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("employeeValidation");
		}
		catch(Exception e)
		{
			logger.info("Null Pointer Exception Ocuures...approveDdoOfficeDataList");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");	
		}
		return objRes;
	}
	

	
	
}
