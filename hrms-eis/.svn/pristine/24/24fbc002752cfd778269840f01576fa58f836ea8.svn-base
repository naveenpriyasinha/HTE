package com.tcs.sgv.eis.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.AllowTypeMasterDAOImpl;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.eis.dao.GroupManagementDAOImpl;
import com.tcs.sgv.eis.dao.HrEisGdMpgDAOImpl;
import com.tcs.sgv.eis.dao.SchedulerDAOImpl;
import com.tcs.sgv.eis.dao.getGradDesgMapDAO;
import com.tcs.sgv.eis.valueobject.GrpMgmtDisplayVO;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisGrpMangMst;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.ess.dao.OrgDesignationMstDao;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDao;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.user.dao.UserPostDAO;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;

public class GroupManagementServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg = 0;

	public ResultObject showGroupManagementService(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("*********GetDesignations*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		/*
		 * String editflag=""; long GdMapId;
		 */
		try

		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			logger
					.info("Coming into the Service method GroupManagementServiceImpl");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			HttpSession session = request.getSession();
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			// long
			// typeId=StringUtility.convertToLong(loginDetailsMap.get("typeId").toString());

			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			List arDesignationVO = new ArrayList();
			List classTypeList = new ArrayList();
			List typeList = new ArrayList();
			// Getting Class Type Details
			OrgGradeMstDao orgGradeMstDao = new OrgGradeMstDaoImpl(
					OrgGradeMst.class, serv.getSessionFactory());
			classTypeList = orgGradeMstDao.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);
			logger.info(" ClassTypelist.size::" + classTypeList.size());
			// Ends
			// Getting Designation List
			UserPostDAO userDAO = new UserPostDAO(UserPostCustomVO.class, serv.getSessionFactory());
			arDesignationVO = userDAO.getAllDesgMasterData(langId);
			logger.info(" arDesignationVOList.size::" + arDesignationVO.size());
			// Ends

			// Getting type List

			GroupManagementDAOImpl type = new GroupManagementDAOImpl(
					HrEisGrpMangMst.class, serv.getSessionFactory());
			String typeId = StringUtility.getParameter("TypeId", request);
			typeList = type.forTypeCombo(typeId);
			logger.info(" typeList.size:::::::::::::::::::::::::::::::::::::::::::"+ typeList.size());
			// Ends

			objectArgs.put("classTypeList", classTypeList);
			objectArgs.put("desigList", arDesignationVO);
			objectArgs.put("typeList", typeList);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			logger.info("::::::::::::: End of Service Directing 2 JSP::::::::::::");
			resultObject.setViewName("showGroupManagement");

		}

		catch (Exception e) {

			logger.info("Exception Ocuures...showGroupManagement Service");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg","There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");

		}
		return resultObject;
	}

	public ResultObject getDesinationsFromClass(Map objectArgs) {
		logger.info("*********GetDesignations*********");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try {
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			HttpSession session = request.getSession();
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class, serv.getSessionFactory());
			long gradeId = 0;
			long desigId = 0;
			logger.info("gradeId in Service:---> "+ StringUtility.getParameter("gradeId", request).toString());
			if (StringUtility.getParameter("Class", request) != null)
				gradeId = Long.valueOf(StringUtility.getParameter("gradeId",request));

			logger.info("gradeId in Service:--->" + gradeId);

			GroupManagementDAOImpl GrpMangDAO = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
			List<HrEisGdMpg> desigList = (List) GrpMangDAO.getdesigsfromgrades(gradeId);
			logger.info("lstHrEisGdMpg:--->" + desigList.size());

			Log logger = LogFactory.getLog(getClass());

			StringBuffer designationStr = new StringBuffer();
			if (desigList != null) {
				for (int i = 0; i < desigList.size(); i++) {
					HrEisGdMpg hrEisGdMpg = desigList.get(i);
					designationStr.append("<gradeId>");

					designationStr.append("<GdMapId>").append(hrEisGdMpg.getGdMapId()).append("</GdMapId>");
					designationStr.append("<SgdGradeId>").append(hrEisGdMpg.getOrgDesignationMst().getDsgnId()).append("</SgdGradeId>");
					designationStr.append("<Designations-name>").append((String) hrEisGdMpg.getOrgDesignationMst().getDsgnName()).append("</Designations-name>");
					designationStr.append("</gradeId>");
				}
			}

			String searchDeptList = new AjaxXmlBuilder().addItem("ajax_key",
					designationStr.toString()).toString();
			objectArgs.put("ajaxKey", searchDeptList);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");

		}

		catch (Exception e) {
			resultObject.setThrowable(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}

	public ResultObject getComponentlistByType(Map objectArgs) {
		logger.info("*********getComponentlistByType is called*********");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try {
			// Map voToService = (Map)objectArgs.get("voToServiceMap");
			HttpSession session = request.getSession();
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class, serv.getSessionFactory());
			long typeId = 0L;
			long Component = 0L;
			logger.info("selType in getComponentlistByType Service:---> "+ StringUtility.getParameter("lookupId", request).toString());
			if (StringUtility.getParameter("lookupId", request) != null && !(StringUtility.getParameter("lookupId", request).equals("")))
				typeId = Long.parseLong(StringUtility.getParameter("lookupId",request));

			logger.info("selType in Service:--->" + typeId);

			if (typeId == 300134) {
				GroupManagementDAOImpl GrpMangDAO = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
				List<HrPayAllowTypeMst> allowList = (List) GrpMangDAO.getAllowancesFromType(typeId);
				logger.info("lstHrEisGdMpg in getComponentlistByType Allowances:--->"+ allowList.size());

				Log logger = LogFactory.getLog(getClass());

				StringBuffer lSbAllowance = new StringBuffer();
				if (allowList != null) {
					for (int i = 0; i < allowList.size(); i++) {
						HrPayAllowTypeMst hrPayAllowTypeMst = allowList.get(i);
						lSbAllowance.append("<typeId>");
						lSbAllowance.append("<AllowCode>").append(hrPayAllowTypeMst.getAllowCode()).append("</AllowCode>");
						lSbAllowance.append("<AlowName>").append(hrPayAllowTypeMst.getAllowName()).append("</AlowName>");
						lSbAllowance.append("</typeId>");
					}
				}

				String searchComponentList = new AjaxXmlBuilder().addItem("ajax_key", lSbAllowance.toString()).toString();
				objectArgs.put("ajaxKey", searchComponentList);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ajaxData");

			} else {

				GroupManagementDAOImpl GrpMangDAO = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
				List<HrPayDeducTypeMst> deducList = (List) GrpMangDAO.getDeductionFromType(typeId);
				logger.info("lstHrEisGdMpg getComponentlistByType Deduction:--->"+ deducList.size());

				Log logger = LogFactory.getLog(getClass());

				StringBuffer componentList = new StringBuffer();
				if (deducList != null) {
					for (int i = 0; i < deducList.size(); i++) {
						HrPayDeducTypeMst hrPayDeducTypeMst = deducList.get(i);
						componentList.append("<typeId>");
						componentList.append("<DedCode>").append(hrPayDeducTypeMst.getDeducCode()).append("</DedCode>");
						componentList.append("<DedName>").append(hrPayDeducTypeMst.getDeducName()).append("</DedName>");
						componentList.append("</typeId>");
					}
				}

				String searchComponentList = new AjaxXmlBuilder().addItem("ajax_key", componentList.toString()).toString();
				objectArgs.put("ajaxKey", searchComponentList);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ajaxData");

			}
		}

		catch (Exception e) {
			resultObject.setThrowable(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setViewName("errorPage");
		}
		return resultObject;

	}

	public ResultObject saveGroupManagementService(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("In saveGroupManagementService method:::::::::::::::::::");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		String editflag = "";
		long GdMapId;

		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			logger.info("Coming into the Service method showGroupManagementService");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			HttpSession session = request.getSession();
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			// long
			// typeId=StringUtility.convertToLong(loginDetailsMap.get("typeId").toString());

			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			long loggedInpostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);

			long loggedInUser = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst loggedInOrgUserMst = orgUserMstDaoImpl.read(loggedInUser);

			String editFlag = StringUtility.getParameter("edit", request);
			logger.info("editFlag:::::::::::::::::::::::::::::::: "+editFlag);
			GroupManagementDAOImpl groupManagementDAOImpl = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			Date sysdate = new Date();

			String TypeId = objectArgs.get("TypeId").toString();
			logger.info("TypeId = " + TypeId + "::::");
			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			cmnLookupMst = cmnLookupMstDAOImpl.read(Long.parseLong(TypeId));
			String CompId = objectArgs.get("ComponentId").toString();
			logger.info("ComponentId = " + CompId + "***************************************");
			long ComponentId = Long.parseLong(objectArgs.get("ComponentId").toString());
			logger.info("ComponentId = " + ComponentId + "::::");

			String StrtDate = objectArgs.get("StartDt").toString();
			logger.info("StrtDate = " + StrtDate + "::::");
			if (StrtDate != null && !StrtDate.trim().equals(""))
				startDate = (Date) df.parseObject(StrtDate);

			String EndDate = objectArgs.get("EndDt").toString();
			logger.info("EndDate = " + EndDate + "::::");
			if (EndDate != null && !EndDate.trim().equals(""))
				endDate = (Date) df.parseObject(EndDate);
			long Amount = Long.parseLong(objectArgs.get("Amount").toString());
			logger.info("Amount = " + Amount + "::::");
			/*
			 * if(Amount!= && !Amount==("")) endDate =
			 * (Date)df.parseObject(EndDate);
			 */

			long ClassId = Long.parseLong(objectArgs.get("ClassId").toString());

			OrgGradeMst orgGradeMst = new OrgGradeMst();
			OrgGradeMstDao gradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
			orgGradeMst = gradeMstDao.read(ClassId);

			logger.info("ClassId = " + ClassId + "::::");

			String designId = objectArgs.get("designation").toString();
			logger.info("designId checking:::::::::::::::::::::::::::::::::: " +designId.substring(1));
			
			if(designId.startsWith(","))
			{
				designId = designId.substring(1, designId.length());
				logger.info("DesignId2222222222:::::::::::::::"+designId);
			}
			
			logger.info("DesignId = " + designId.split(",") + "::::" + designId.substring(0).equalsIgnoreCase(","));
			logger.info("DesignId3333333333333333:::::::::::::::"+designId);
			/*
			 * String strDesgIds = (String)objectArgs.get("designation");
			 * 
			 * StringTokenizer desgTokens = new StringTokenizer(strDesgIds,",");
			 * 
			 * long[] desgIds = new long[desgTokens.countTokens()]; int i=0;
			 * 
			 * String desgId = desgTokens.nextElement().toString();
			 * logger.info("next token and element is " + desgId); try{
			 * desgIds[i++] = Long.parseLong(desgId); }catch(Exception exp) {
			 * logger.error("desgId not a number of is larger that type long");
			 * exp.printStackTrace(); }
			 * logger.info("desg added in Arrary...Element is " + desgId);
			 */

			// String cmnlocation = objectArgs.get("cmnlocation").toString();
			// logger.info("cmnlocation = " + cmnlocation+"::::");

			// This is for single record entry into database
			
			if(!editFlag.equalsIgnoreCase("Y"))
			{
			IdGenerator idGen = new IdGenerator();
			long groupManagementId = idGen.PKGenerator("hr_eis_grp_mang_mst",
					objectArgs);
			HrEisGrpMangMst hrEisGrpMangMst = new HrEisGrpMangMst();
			hrEisGrpMangMst.setGroupManagementId(groupManagementId);
			hrEisGrpMangMst.setAmount(Amount);
			hrEisGrpMangMst.setCmnDatabaseMst(cmnDatabaseMst);
			hrEisGrpMangMst.setCmnLanguageMst(cmnLanguageMst);
			hrEisGrpMangMst.setCmnLocationMst(cmnLocationMst);
			hrEisGrpMangMst.setCmnLookupMst(cmnLookupMst);
			hrEisGrpMangMst.setComponentId(ComponentId);
			hrEisGrpMangMst.setCreatedDate(sysdate);
			hrEisGrpMangMst.setDesignationId(designId);
			hrEisGrpMangMst.setEndDate(endDate);
			hrEisGrpMangMst.setOrgGradeMst(orgGradeMst);
			hrEisGrpMangMst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
			// hrEisGrpMangMst.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
			hrEisGrpMangMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			// hrEisGrpMangMst.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
			hrEisGrpMangMst.setStartDate(startDate);
			// hrEisGrpMangMst.setUpdatedDate(sysdate);

			groupManagementDAOImpl.create(hrEisGrpMangMst);
			
			objectArgs.put("msg", "Record Inserted Successfully");
			}
			else
			{
				
				long groupManagementId = Long.parseLong(objectArgs.get("groupManagementId").toString());

				HrEisGrpMangMst hrEisGrpMangMst = groupManagementDAOImpl.read(groupManagementId);
				hrEisGrpMangMst.setGroupManagementId(groupManagementId);
				hrEisGrpMangMst.setAmount(Amount);
				/*hrEisGrpMangMst.setCmnDatabaseMst(cmnDatabaseMst);
				hrEisGrpMangMst.setCmnLanguageMst(cmnLanguageMst);
				hrEisGrpMangMst.setCmnLocationMst(cmnLocationMst);*/
				hrEisGrpMangMst.setCmnLookupMst(cmnLookupMst);
				hrEisGrpMangMst.setComponentId(ComponentId);
				hrEisGrpMangMst.setCreatedDate(sysdate);
				hrEisGrpMangMst.setDesignationId(designId);
				hrEisGrpMangMst.setEndDate(endDate);
				hrEisGrpMangMst.setOrgGradeMst(orgGradeMst);
				//hrEisGrpMangMst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				 hrEisGrpMangMst.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
				//hrEisGrpMangMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				 hrEisGrpMangMst.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
				hrEisGrpMangMst.setStartDate(startDate);
				 hrEisGrpMangMst.setUpdatedDate(sysdate);

				groupManagementDAOImpl.update(hrEisGrpMangMst);
				objectArgs.put("msg", "Record Updated Successfully");
			}
			/*
			 * resultObject.setResultCode(ErrorConstants.SUCCESS);
			 * resultObject.setResultValue(objectArgs);logger.info(
			 * "::::::::::::: End of saveGroupManagementService ::::::::::::");
			 * resultObject.setViewName("showGroupManagement");
			 */

			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "GroupManagementView");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			logger.info("End of saveGroupManagementService method:::::::: " +objectArgs.get("msg") );
			objectArgs.put("redirectMap", redirectMap);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("redirect view");

		}

		catch (Exception e) {

			logger.info("Exception Ocuures...showGroupManagement Service");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");

		}

		return resultObject;

	}

	// Getting Data from Table

public ResultObject getGroupMangData(Map objectArgs) {
		logger.info("*********getGroupMangData is called*********");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		try {
			HttpSession session = request.getSession();
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			
			GroupManagementDAOImpl GrpMangDAO = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
			List<HrEisGrpMangMst> grpMangDataList = (List) GrpMangDAO.getGroupMangData(locId);
			String component = "";
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Iterator it = grpMangDataList.iterator();
			logger.info("grpMangDataList size is :::::: "+ grpMangDataList.size());

			List allData = new ArrayList();

			for (int i = 0; i < grpMangDataList.size(); i++) 
			{
				GrpMgmtDisplayVO displayVO = new GrpMgmtDisplayVO();
				//HrEisGrpMangMst gMangData = new HrEisGrpMangMst();
				HrEisGrpMangMst rowList = grpMangDataList.get(i);
				long grpMangId = rowList.getGroupManagementId();
				displayVO.setGrpMangId(grpMangId);
				String type = rowList.getCmnLookupMst().getLookupShortName();
				logger.info("type is:::::::::" + type);
				/*CmnLookupMst cmnLookupMst = new CmnLookupMst();
				CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				cmnLookupMst = cmnLookupMstDAOImpl.read(Long.parseLong(typeId));
				logger.info("Type is:::::::::"+ cmnLookupMst.getLookupShortName());*/
				displayVO.setTypeName(type);

				//long typecode = Long.parseLong(type.toString());
				long compId = rowList.getComponentId();
				logger.info("compId is:::::::::" + compId);
				//logger.info("typecode::::::::::::::::::: " + typecode);
				if (type.equalsIgnoreCase("Allowance")) {
					HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
					AllowTypeMasterDAOImpl allowTypeMasterDAOImpl = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class, serv.getSessionFactory());
					hrPayAllowTypeMst = allowTypeMasterDAOImpl.read(compId);
					component = hrPayAllowTypeMst.getAllowName();
					displayVO.setComponentName(component);
					logger.info("AllowId::::::::::::::"+ hrPayAllowTypeMst.getAllowName());
					logger.info("component1::::::::::::::" + component);
					} 
				else 
				{
					HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
					DeducTypeMasterDAOImpl deducDao = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class, serv.getSessionFactory());
					hrPayDeducTypeMst = deducDao.read(compId);
					component = hrPayDeducTypeMst.getDeducName();
					displayVO.setComponentName(component);
					logger.info("DeductionId::::::::::::::"+ hrPayDeducTypeMst.getDeducName());
					logger.info("component2::::::::::::::" + component);
				}

				//Date startDate = (Date) rowList[2];
				//Date startDate = sdf.parse ((String)rowList[2]);
				//logger.info("startDate is:::::::::" + startDate);
				//Date startDate = sdf.parse(sdDate);
				String startDate = sdf.format(rowList.getStartDate());
				displayVO.setStartDate(startDate);
				//Date endDate = (Date) rowList[3];
				Date endDt = rowList.getEndDate();
				logger.info("End Date from Database " + (endDt!=null));
				if(endDt!=null)                                      // checking for null
				{
				String endDate = sdf.format(endDt);
				logger.info("endDate is:::::::::" + endDate);
				displayVO.setEndDate(endDate);
				}
				else
				{
				  displayVO.setEndDate("");
				}
				  
				long amount = rowList.getAmount();
				logger.info("amount is:::::::::" + amount);
				displayVO.setAmount(amount);

				long classId = rowList.getOrgGradeMst().getGradeId();
				logger.info("class:::::::::::::::::::: " +classId);
				OrgGradeMst orgGradeMst = new OrgGradeMst();
				OrgGradeMstDao gradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
				orgGradeMst = gradeMstDao.read(classId);
				logger.info("classId is:::::::::" + classId);
				logger.info("class is:::::::::" + orgGradeMst.getGradeName());
				displayVO.setGradeName(orgGradeMst.getGradeName());

				String strDsgnId = rowList.getDesignationId();
				logger.info("strDsgnId:::::::::::::::::::::::::::: " + strDsgnId);
				StringTokenizer dsgnTokenizer = new StringTokenizer(strDsgnId,",");				
				logger.info("dsgnTokenizer:::::::::::::::::::::::::::: " + dsgnTokenizer);
				while (dsgnTokenizer.hasMoreTokens()) {
					String dsgnId = dsgnTokenizer.nextToken();
					logger.info("dsgnId11111111111 is:::::::::::::::::::::: "+dsgnId);
					long desgnId = dsgnId!= null && !dsgnId.trim().equals("") ? Long.parseLong(dsgnId) : 0;
					logger.info("desgnId is:::::::::::::::::::::: "+desgnId);
					OrgDesignationMst orgDesignationMst = new OrgDesignationMst();
					OrgDesignationMstDao desgnMstDao = new OrgDesignationMstDaoImpl(OrgDesignationMst.class, serv.getSessionFactory());
					if(desgnId!=0) {
					 orgDesignationMst = desgnMstDao.read(desgnId);
					String designation = orgDesignationMst.getDsgnName();
					logger.info("desgnId is:::::::::" + desgnId);
					logger.info("designation is:::::::::" + designation);
					if (displayVO.getDsgnName() != null && !displayVO.getDsgnName().trim().equals(""))
						displayVO.setDsgnName(displayVO.getDsgnName() + ","+ designation);
					else
						displayVO.setDsgnName(designation);
					logger.info("designation is:::::::::" + designation);
					}
					
					
				}

				allData.add(displayVO);
			}

			objectArgs.put("gMangData", allData);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			logger.info("::::::::::::: End of Service Directing 2 JSP::::::::::::");
			resultObject.setViewName("GroupManagementView");

		} catch (Exception e) {

			logger.info("Exception Ocuures...showGroupManagement Service");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");

		}

		return resultObject;

	}


//public ResultObject updateGrpMangDtls(Map objectArgs)
public ResultObject updateGrpMangDtls(Map objectArgs)
{
	logger.info("in updateGrpMangDtls");
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

	try {

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
		List desigList1 = new ArrayList();
		List arDesignationVO = new ArrayList();
		List classTypeList = new ArrayList();
		List typeList = new ArrayList();
		// Getting Class Type Details
		OrgGradeMstDao orgGradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
		classTypeList = orgGradeMstDao.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);
		logger.info(" ClassTypelist.size::" + classTypeList.size());
		// Ends
		// Getting Designation List
		UserPostDAO userDAO = new UserPostDAO(UserPostCustomVO.class, serv.getSessionFactory());
		arDesignationVO = userDAO.getAllDesgMasterData(langId);
		//List<UserPostCustomVO> deList =userDAO.getAllDesgMasterData(langId);
		//deList.get(0).getDsgnId();
		logger.info(" arDesignationVOList.size::" + arDesignationVO.size());
		// Ends

		// Getting type List

		GroupManagementDAOImpl typeName = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
		String typeId = StringUtility.getParameter("TypeId", request);
		//logger.info("typeId::::::::::::::::: " + typeId);
		typeList = typeName.forTypeCombo(typeId);
		logger.info(" typeList.size:::::::::::::::::::::::::::::::::::::::::::"+ typeList.size());
		// Ends


		//long groupManagementId = 0;
		long groupManagementId = Long.parseLong(StringUtility.getParameter("groupManagementId", request).toString());
		logger.info("groupManagementId #########################" + groupManagementId);
		String edit = StringUtility.getParameter("edit", request).toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");

		
		String component = "";

		GroupManagementDAOImpl GrpMangDAO = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
		logger.info("muniiiiiiiiii     ");
		HrEisGrpMangMst hrEisGrpMangMst = GrpMangDAO.read(groupManagementId);
	//	logger.info("lstCompDtls size is:::::::::::::: "+lstCompDtls.size());
		//HrEisGrpMangMst hrEisGrpMangMst = new HrEisGrpMangMst();
		long typeCode = 0;
		String type = "";
		long compId = 0;
		String startDate = "";
		String endDate = "";
		long amount = 0;
		String className = "";
		String desg = "";
		String stDate = "";
		Date date = new Date();
		Date strtDate = new Date();
		Date eDate = new Date();
		List compList = new ArrayList();
		List desgmList1 = new ArrayList();
		if (hrEisGrpMangMst != null) 
		{
		//	hrEisGrpMangMst = lstCompDtls.get(0);
			typeCode = hrEisGrpMangMst.getCmnLookupMst().getLookupId();
			if (typeCode == 300134) {
				//GroupManagementDAOImpl GrpMangDAo = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
				//compList = (List) GrpMangDAO.getAllowancesFromType(typeCode);
				//logger.info("In updateGrpMangDtls method getting ComponentlistBy Allowance:--->"+ compList.size());
				compList = (List)GrpMangDAO.getAllowancesFromType(typeCode);
				//long a = componentList.get(0).getAllowCode();
			} 
			else {
				//GroupManagementDAOImpl GrpMangDAO = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
				compList = (List) GrpMangDAO.getDeductionFromType(typeCode);
			
			
			}
			
			type = hrEisGrpMangMst.getCmnLookupMst().getLookupShortName();
			compId = hrEisGrpMangMst.getComponentId();
			if (type.equalsIgnoreCase("Allowance")) {
				HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
				AllowTypeMasterDAOImpl allowTypeMasterDAOImpl = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class, serv.getSessionFactory());
				hrPayAllowTypeMst = allowTypeMasterDAOImpl.read(compId);
				component = hrPayAllowTypeMst.getAllowName();
				} 
			else 
			{
				HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
				DeducTypeMasterDAOImpl deducDao = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class, serv.getSessionFactory());
				hrPayDeducTypeMst = deducDao.read(compId);
				component = hrPayDeducTypeMst.getDeducName();
			}
			
			strtDate = hrEisGrpMangMst.getStartDate();
			startDate = hrEisGrpMangMst.getStartDate().toString();
			stDate = sdf.format(strtDate);
			date = sdf.parse(stDate);

			//Date date = sdf.parse(startDate);
			//Date strtDate = StringUtility.convertStringToDate(startDate);
			
		
			//logger.info("date::::::::::: "+date);
			//stDate = sdf.parse(startDate);
			//stDate1 = StringUtility.convertStringToDate(stDate);
			//logger.info("stDate1:::::::::::::::::: "+stDate1);
			Date endDt = hrEisGrpMangMst.getEndDate();
			if(endDt!=null) 
			{
			endDate = sdf.format(hrEisGrpMangMst.getEndDate());
			eDate = sdf.parse(endDate);
			}
			
			eDate = endDt;
			
			amount = hrEisGrpMangMst.getAmount();
			className = hrEisGrpMangMst.getOrgGradeMst().getGradeName();
			long classId = hrEisGrpMangMst.getOrgGradeMst().getGradeId();
			//GroupManagementDAOImpl GrpMangDAO = new GroupManagementDAOImpl(HrEisGrpMangMst.class, serv.getSessionFactory());
			desigList1 = (List) GrpMangDAO.getdesigsfromgrades(classId);
			logger.info("lstHrEisGdMpg:--->" + desigList1.size());
			
			
			desg = hrEisGrpMangMst.getDesignationId();
			String[] desgList;
			hrEisGrpMangMst.setStartDate(hrEisGrpMangMst.getStartDate());
			desgList = desg.split(",");
			for(int i=0; i<desgList.length; i++)
			{
			 desgmList1.add(desgList[i]);
			}
			logger.info("Going 2 StringTokenizer in UpdateGrpMangment:::::::::::::::::::");
			logger.info("Going 2 StringTokenizer in UpdateGrpMangment:::::::::::::::::::"+desgmList1.size());
	
		    
			
		}			
		Map map = new HashMap();
		map = objectArgs;
		map.put("edit", edit);
		map.put("compId",compId );
		map.put("groupManagementId", groupManagementId);
		map.put("type", type);
		map.put("component", component);
		map.put("startDate", date);
		map.put("endDate", eDate);
		map.put("amount", amount);
		map.put("className", className);
		map.put("desg", desg);
		map.put("desigList1", desigList1);
		map.put("arDesignationVOSize", arDesignationVO.size());
		map.put("classTypeList", classTypeList);
		map.put("typeList", typeList);
		map.put("compList", compList);
		map.put("desgnList1", desgmList1);
	//	objectArgs.put("msg", "Updated Successfully");
		
		map.put("hrEisGrpMangMst", hrEisGrpMangMst);
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setResultValue(map);
		resultObject.setViewName("showGroupManagement");
		logger.info("Redirecting 2 JSP#####################################");

	}

	catch (Exception e) {
		logger.error("Error is: "+ e.getMessage());
		objectArgs.put("msg", "There is some problem. Please Try Again.");
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("errorInsert");
	}

	return resultObject;
}






}
