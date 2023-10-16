package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.SgvaBudmjrhdMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BillHeadDao;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.SgvaBudSubHeadMpgDaoImpl;
import com.tcs.sgv.eis.util.BillHeadMasterServiceImplHelper;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadCustomMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class BillHeadMasterServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	ResourceBundle lResourceBundle = ResourceBundle
			.getBundle("resources/Payroll");

	public ResultObject getBillHeadData(Map objectArgs) {
		logger.info("Inside Bill Head Data Service");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try {
			
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");

			// Modify By Urvin Shah
			/*
			 * long
			 * locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			 * CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new
			 * CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			 * CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
			 */
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap
					.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null && !cmnLocationMst
					.getLocationCode().trim().equals("")) ? cmnLocationMst
					.getLocationCode() : "";
			BillHeadDao billHeadMpgDAO = new BillHeadDao(
					HrPayBillHeadCustomVO.class, serv.getSessionFactory());// daoimpl
																			// object
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			HttpSession session = request.getSession();

			// Added By Mrugesh for financial year issue
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(
					SgvcFinYearMst.class, serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			String dt = sdf.format(currDate);
			int finYrId = finYearDAOImpl.getFinYearId(currDate);
			// Ended By Mrugesh
			List resultSet = billHeadMpgDAO.getAllData(locationCode, finYrId);

			HrPayBillHeadCustomVO bhMpg = new HrPayBillHeadCustomVO();

			Iterator it = resultSet.iterator();
			List bhMpgList = new ArrayList();

			for (int i = 0; i < resultSet.size(); i++) {
				bhMpg = new HrPayBillHeadCustomVO();
				Object[] rowList = (Object[]) resultSet.get(i);
				String subHeadName = rowList[0].toString();
				bhMpg.setSubHeadName(subHeadName);

				long subHeadId = Long.parseLong(rowList[1].toString());
				bhMpg.setSubHeadId(subHeadId);

				String demandId = rowList[2].toString();
				bhMpg.setDemandNo(demandId);

				String budmjrhd_code = rowList[3].toString();
				bhMpg.setMjrHead(budmjrhd_code);

				String budsubmjrhd_code = rowList[4].toString();
				bhMpg.setSubMjrHead(budsubmjrhd_code);

				String budminhd_code = rowList[5].toString();
				bhMpg.setMinorHead(budminhd_code);

				long billId = Long.parseLong(rowList[6].toString());
				//bhMpg.setBillId(billId);

				long billHeadId = Long.parseLong(rowList[7].toString());
				bhMpg.setBillHeadId(billHeadId);

				bhMpgList.add(bhMpg);

			}

			objectArgs.put("resultSet", bhMpgList);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);// passing map to result
													// value for get data in jsp
			resultObject.setViewName("BillHeadMpgView");

		} catch (PropertyValueException pe) {
			logger
					.info("Null Pointer Exception Ocuures...insertOrderMasterDtls");
			pe.printStackTrace();
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			/*
			 * logger.info("setViewName-->errorPage"); Map result = new
			 * HashMap(); result.put("MESSAGECODE",300004);
			 * resultObject.setResultValue(result);
			 * resultObject.setThrowable(pe); resultObject.setResultCode(-1);
			 * resultObject.setViewName("errorPage"); pe.printStackTrace();
			 */

		} catch (Exception e) {
			logger
					.info("Null Pointer Exception Ocuures...insertOrderMasterDtls");
			e.printStackTrace();
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			/*
			 * logger.info("setViewName-->errorPage"); Map result = new
			 * HashMap(); result.put("MESSAGECODE",300004);
			 * resultObject.setResultValue(result);
			 * resultObject.setThrowable(e); resultObject.setResultCode(-1);
			 * resultObject.setViewName("errorPage"); e.printStackTrace();
			 */
		}
		return resultObject;
	}

	public ResultObject GetDsgnfromLocation(Map objectArgs) {
		logger.info("*********GetDsgnfromLocation*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		Map voToService = (Map) objectArgs.get("voToServiceMap");

		String editFlag = (String) voToService.get("editFlag");

		Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());

		List billHeadList = new ArrayList();
		BillHeadDao billHeadMpgDAOImpl = new BillHeadDao(
				HrPayBillHeadCustomVO.class, serv.getSessionFactory());
		long userId = Long.parseLong(loginDetailsMap.get("userId").toString());
		billHeadList = billHeadMpgDAOImpl.getAllDsgnFromLocation();
		StringBuffer propertyList = new StringBuffer();

		HrPayBillHeadCustomVO bhMpg = new HrPayBillHeadCustomVO();

		Iterator it = billHeadList.iterator();
		List bhMpgList = new ArrayList();
		String dsgnName = "";
		String dsgnId = "";
		for (int i = 0; i < billHeadList.size(); i++) {
			bhMpg = new HrPayBillHeadCustomVO();
			Object[] rowList = (Object[]) billHeadList.get(i);
			dsgnName = rowList[0].toString();
			bhMpg.setDsgnName(dsgnName);

			dsgnId = (rowList[1].toString());
			bhMpg.setDsgnId(dsgnId);

			bhMpgList.add(bhMpg);

			propertyList.append("<dsgn-mapping>");
			propertyList.append("<dsgnId>").append(dsgnId).append("</dsgnId>");
			propertyList.append("<dsgnName>").append("<![CDATA[").append(
					dsgnName).append("]]>").append("</dsgnName>");
			propertyList.append("</dsgn-mapping>");

		}

		Map result = new HashMap();
		String dsgnData = new AjaxXmlBuilder().addItem("ajax_key",
				propertyList.toString()).toString();
		result.put("ajaxKey", dsgnData);

		resultObject.setResultValue(result);

		resultObject.setViewName("ajaxData");
		logger.info("After Service Called.........\n" + dsgnData);
		return resultObject;

	}

	public ResultObject billheadMaster(Map objectArgs) {
		logger.info("**********Inside billheadMaster**************");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		String editflag = "";
		long billheadId;
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpSession session = request.getSession();
			// added by rahul
			Map baseLoginMap = (Map) objectArgs.get("baseLoginMap");
			long locId = Long.parseLong(baseLoginMap.get("locationId").toString());
			// ended by rahul
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			BillHeadMpgDAOImpl billHeadMpgDAO =null; 
				//new BillHeadMpgDAOImpl(					HrPayBillHeadMpg.class, serv.getSessionFactory());
			BillHeadDao headMasterDAO = new BillHeadDao(
					HrPayBillHeadCustomVO.class, serv.getSessionFactory());
			// OrderMstDAO orderMstDAO = new
			// OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
			Long langId = Long.parseLong(loginDetailsMap.get("langId")
					.toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			OrgGradeMstDaoImpl gradeMstDaoImpl = new OrgGradeMstDaoImpl(
					OrgGradeMst.class, serv.getSessionFactory());
			long postId = StringUtility.convertToLong(loginDetailsMap.get(
					"primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(
					OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			List billHeadList = new ArrayList();
			BillHeadDao billHeadMpgDAOImpl = new BillHeadDao(
					HrPayBillHeadCustomVO.class, serv.getSessionFactory());
			billHeadList = billHeadMpgDAOImpl.getAllDsgnFromLocation();
			// Added By Urvin Shah.
			CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			// End
			
			SgvaBudSubHeadMpgDaoImpl sgvaDao = new SgvaBudSubHeadMpgDaoImpl(SgvaBudsubhdMst.class,serv.getSessionFactory());
			List schemeList = sgvaDao.getSchemeListByPostId(postId);
			
			logger.info(schemeList);

			objectArgs.put("schemeList", schemeList);
			
			HrPayBillHeadCustomVO bhMpg = new HrPayBillHeadCustomVO();

			Iterator itr = billHeadList.iterator();
			List dsgnList = new ArrayList();
			String dsgnName = "";
			String dsgnId = "";
			for (int i = 0; i < billHeadList.size(); i++) {
				bhMpg = new HrPayBillHeadCustomVO();
				Object[] rowList = (Object[]) billHeadList.get(i);
				dsgnName = rowList[0].toString();
				bhMpg.setDsgnName(dsgnName);

				dsgnId = (rowList[1].toString());
				bhMpg.setDsgnId(dsgnId);

				dsgnList.add(bhMpg);
			}

			long userId = StringUtility.convertToLong(loginDetailsMap.get(
					"userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(
					OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			// List orderresultSet = orderMstDAO.getAllData();
			List headresultSet = billHeadMpgDAO.getAllSubhdData();
			// List deptList = orderHeadMpgDAO.getAlldeptData();
			// Start Added By Urvin shah.
			// modified by rahul
			List deptList = null;
			
			
			
		
			/* An admin will be an admin for this particular department itself and not other departments, so no need to show all the departments
			 * If required, below commented code can be used, but don't forget to introduce sorting at DAO itself.
			 */
			
			/* PaybillHeadMpgDAOImpl paybillheadmpgdao = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());
			boolean isRoleAdminFlag = paybillheadmpgdao.isLoggedInUserAdmin(baseLoginMap); */
			boolean isRoleAdminFlag = false;	 
			if (isRoleAdminFlag) {
				logger.info("true::");
				deptList = locationDAO.getListByColumnAndValue(
						"cmnLanguageMst.langId", langId );
				
				logger.info("deptlist.size::" + deptList.size());
			} else {
				logger.info("false::");
				String[] critariaArugs = { "cmnLanguageMst.langId", "locId" };
				Object[] valueArgus = new Object[2];
				valueArgus[0] = langId;
				valueArgus[1] = locId;
				deptList = locationDAO.getListByColumnAndValue(critariaArugs,
						valueArgus);
				logger.info("deptlist.size::" + deptList.size());
			}
			// ended by rahul
			
			//Sorting here itself so as to remove overhead at JSP
			List classList = gradeMstDaoImpl.getListByColumnAndValue(
					new String[]{"cmnLanguageMst.langId"}, new Object[]{langId}, new String[]{"gradeName"});

			logger.info("classList size is" + classList.size());
			Date sysdate = new Date();
			// End by Urvin shah.
			HrPayBillHeadMpg billHeadMpg = new HrPayBillHeadMpg();
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			editflag = voToService.get("updateflag") != null ? (String) voToService
					.get("updateflag")
					: "";
			logger.info("edit flag is:--->" + editflag);

			// Added By Mrugesh for financial year issue
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(
					SgvcFinYearMst.class, serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			String dt = sdf.format(currDate);
			int finYrId = finYearDAOImpl.getFinYearId(currDate);

			//logger.info("the finYrID is ::" + finYrId);
			// Ended By Mrugesh

			if (editflag != null && editflag.equals("Y")) {

				String designationId = "";
				logger.info("inside update");
				HrPayBillHeadCustomVO ohMpg = new HrPayBillHeadCustomVO();
				long bhMapId = voToService.get("billheadID") != null ? Long
						.parseLong((String) voToService.get("billheadID")) : 0;
				logger.info("Bill Head Id:--->" + bhMapId);
				List bhDataList = new ArrayList();
				bhDataList = headMasterDAO.getAllDatabybhId(bhMapId, finYrId);
				logger.info("list size is in bhdatalist is :--->>"
						+ bhDataList.size());
				Iterator it = bhDataList.iterator();
				if (bhDataList != null && bhDataList.size() > 0) {
					Object[] rowList = (Object[]) bhDataList.get(0);

					ohMpg.setBillHeadId(bhMapId);
					String demandNo = rowList[2] != null ? rowList[2]
							.toString() : "";

					ohMpg.setDemandNo(demandNo);
					long billId = rowList[7] != null ? Long
							.parseLong(rowList[7].toString()) : 0;
					logger.info("billId code is:--->>" + billId);
					String locationCode = rowList[9] != null ? rowList[9]
							.toString() : "0";
					logger.info("location code is:--->>" + locationCode);
					ohMpg.setLocationCode(locationCode);

					CmnLocationMst cmnLocationMstBh = locationDAO.read(Long
							.parseLong(locationCode != null
									&& !""
											.equalsIgnoreCase(locationCode
													.trim()) ? locationCode
									: "0"));

					ohMpg.setBillId(null);
					long SubHeadCode = rowList[1] != null ? Long
							.parseLong(rowList[1].toString()) : 0;
							
														
							
						
					ohMpg.setSubHeadCode(SubHeadCode);

					String SubHead = rowList[0] != null ? rowList[0].toString()
							: "";
					/// set element code instead of subhead id	
					
					OrderHeadMpgDAOImpl orderHeadMpgDAOImpl=new
					 OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
					
					//// get element code instead of sub head id 
					long elementCode = Long.parseLong(orderHeadMpgDAOImpl.getElementCodeFromSubHdId(Long.parseLong(SubHead)));
					
					//ohMpg.setSubHeadId(Long.parseLong(SubHead));
					ohMpg.setSubHeadId(elementCode);

					String mjrHeadCode = rowList[4] != null ? rowList[4]
							.toString() : "";
					ohMpg.setMjrHead(mjrHeadCode);

					String subMjrHeadCode = rowList[5] != null ? rowList[5]
							.toString() : "";
					ohMpg.setSubMjrHead(subMjrHeadCode);

					String MnrHeadCode = rowList[6] != null ? rowList[6]
							.toString() : "";
					ohMpg.setMinorHead(MnrHeadCode);
					String SubHeadName = rowList[3] != null ? rowList[3]
							.toString() : "";
					ohMpg.setSubHeadName(SubHeadName);

					String desgn_Id = (String) (rowList[11] != null ? (rowList[11])
							: "");
					ohMpg.setDsgnId(desgn_Id);
					logger.info("Designation Id are:--->" + desgn_Id);

					String grade_Id = (String) (rowList[12] != null ? (rowList[12])
							: "");
					ohMpg.setGradeId(grade_Id);

					logger.info("Grade Id are:--->" + grade_Id);

					/*
					 * StringTokenizer st = new StringTokenizer(desgn_Id, ",",
					 * false);
					 * 
					 * 
					 * 
					 * while (st.hasMoreElements())
					 * 
					 * designationId +=(String) st.nextElement();
					 * logger.info("designation Id is"+designationId.length()+
					 * "desg"+designationId);
					 * 
					 *  // logger.info("Designation Id are:--->>>>"+);
					 * 
					 * 
					 * StringTokenizer st1 = new StringTokenizer(grade_Id, ",",
					 * true); logger.info("Designation Id
					 * are:--->>>>"+st.toString());
					 */

					List designationName = headMasterDAO.getdsgnName(desgn_Id);
					List gradeName = headMasterDAO.getgradeName(grade_Id);

					List restDesignation = headMasterDAO.restdsgnName(desgn_Id);
					List restGrade = headMasterDAO.restgradeName(grade_Id);

					HrPayBillHeadCustomVO destinationList = new HrPayBillHeadCustomVO();

					Iterator itr2 = designationName.iterator();
					List designationList = new ArrayList();
					String dsgnNameDestination = "";
					String dsgnIdDestination = "";
					for (int i = 0; i < designationName.size(); i++) {
						destinationList = new HrPayBillHeadCustomVO();
						Object[] destList = (Object[]) designationName.get(i);
						dsgnIdDestination = destList[0].toString();
						// logger.info("+++++
						// dsgnIdDestination+++++"+dsgnIdDestination);
						destinationList.setDsgnId(dsgnIdDestination);

						dsgnNameDestination = (destList[1].toString());
						// logger.info("+++++
						// dsgnNameDestination+++++"+dsgnNameDestination);
						destinationList.setDsgnName(dsgnNameDestination);

						designationList.add(destinationList);
					}

					HrPayBillHeadCustomVO destinationGrade = new HrPayBillHeadCustomVO();

					Iterator desGradeItr = gradeName.iterator();
					List destinationGradeList = new ArrayList();
					String gradeDesName = "";
					String gradeDestId = "";
					for (int i = 0; i < gradeName.size(); i++) {
						destinationGrade = new HrPayBillHeadCustomVO();
						Object[] desGradeList = (Object[]) gradeName.get(i);
						gradeDestId = desGradeList[0].toString();
						// logger.info("+++++
						// gradeDestId+++++"+gradeDestId);
						destinationGrade.setGradeId(gradeDestId);

						gradeDesName = (desGradeList[1].toString());
						// logger.info("+++++
						// gradeDesName+++++"+gradeDesName);
						destinationGrade.setGradeName(gradeDesName);

						destinationGradeList.add(destinationGrade);
					}

					// /Added By varun

					HrPayBillHeadCustomVO restdestinationList = new HrPayBillHeadCustomVO();

					Iterator restitr = restDesignation.iterator();
					List restdesignationList = new ArrayList();
					String restdsgnNameDestination = "";
					String restdsgnIdDestination = "";
					for (int i = 0; i < restDesignation.size(); i++) {
						restdestinationList = new HrPayBillHeadCustomVO();
						Object[] restdestList = (Object[]) restDesignation
								.get(i);
						restdsgnIdDestination = restdestList[0].toString();
						// logger.info("+++++
						// restdsgnIdDestination+++++"+restdsgnIdDestination);
						restdestinationList.setDsgnId(restdsgnIdDestination);

						restdsgnNameDestination = (restdestList[1].toString());
						// logger.info("+++++
						// restdsgnNameDestination+++++"+restdsgnNameDestination);
						restdestinationList
								.setDsgnName(restdsgnNameDestination);

						restdesignationList.add(restdestinationList);
					}

					HrPayBillHeadCustomVO restdestinationGrade = new HrPayBillHeadCustomVO();

					Iterator restdesGradeItr = restGrade.iterator();
					List restdestinationGradeList = new ArrayList();
					String restgradeDesName = "";
					String restgradeDestId = "";
					for (int i = 0; i < restGrade.size(); i++) {
						restdestinationGrade = new HrPayBillHeadCustomVO();
						Object[] restdesGradeList = (Object[]) restGrade.get(i);
						restgradeDestId = restdesGradeList[0].toString();
						// logger.info("+++++
						// restgradeDestId+++++"+restgradeDestId);
						restdestinationGrade.setGradeId(restgradeDestId);

						restgradeDesName = (restdesGradeList[1].toString());
						// logger.info("+++++
						// restgradeDesName+++++"+restgradeDesName);
						restdestinationGrade.setGradeName(restgradeDesName);

						restdestinationGradeList.add(restdestinationGrade);
					}

					// Ended By Varun

					objectArgs.put("designationList", designationList);
					objectArgs
							.put("destinationGradeList", destinationGradeList);

					objectArgs.put("restdesignationList", restdesignationList);
					objectArgs.put("restdestinationGradeList",
							restdestinationGradeList);

					objectArgs.put("classList", classList);
					logger
							.info("Designation Name List Size is:->>"
									+ designationName.size()
									+ "Grade Name List Size is:--> "
									+ gradeName.size());

					PayBillDAOImpl payBillDAO = new PayBillDAOImpl(
							HrPayPaybill.class, serv.getSessionFactory());
					List mjrHeadDataList = new ArrayList();
					List subMjrHeadDataList = new ArrayList();
					List minorHeadDataList = new ArrayList();
					List subHeadDataList = new ArrayList();
					List demandNoDataList = new ArrayList();
					List demandNoList = payBillDAO.getDemandNoByLocId(
							cmnLocationMstBh.getLocationCode(), cmnLanguageMst
									.getLangShortName(), finYrId);
					List mjrHeadList = payBillDAO.getMjrHeadByDemandNo(
							demandNo, cmnLanguageMst.getLangShortName(),
							finYrId);
					List subMjrHeadList = payBillDAO.getSubMjrHeadByMjrHead(
							demandNo, cmnLanguageMst.getLangShortName(),
							mjrHeadCode, finYrId);
					List minorHeadList = payBillDAO.getMnrHeadByMjrHead(
							demandNo, mjrHeadCode, subMjrHeadCode,
							cmnLanguageMst.getLangShortName(), finYrId);
					List subHeadList = payBillDAO.getSubHeadByMnrHead(demandNo,
							mjrHeadCode, subMjrHeadCode, MnrHeadCode,
							cmnLanguageMst.getLangShortName(), finYrId);

					for (Iterator iter = demandNoList.iterator(); iter
							.hasNext();) {
						Object[] row = (Object[]) iter.next();
						HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();
						String demand_code = row[1] != null ? row[1].toString()
								: "";
						ohmpg.setDemandNo(demand_code);
						demandNoDataList.add(ohmpg);
					}
					HrPayOrderHeadCustomMpg ohmpg = new HrPayOrderHeadCustomMpg();

					for (Iterator iter = mjrHeadList.iterator(); iter.hasNext();) {
						// Object[] row = (Object[])iter.next();
						ohmpg = new HrPayOrderHeadCustomMpg();
						// SgvaBudmjrhdMst sgvaBudmjrhdMst=new
						// SgvaBudmjrhdMst();
						// sgvaBudmjrhdMst=(SgvaBudmjrhdMst)mjrHeadList;
						String MjrHead = iter.next().toString();
						ohmpg.setMjrHead(MjrHead);
						mjrHeadDataList.add(ohmpg);
					}
					for (Iterator iter = subMjrHeadList.iterator(); iter
							.hasNext();) {
						// Object[] row = (Object[])iter.next();
						ohmpg = new HrPayOrderHeadCustomMpg();
						String subMjrHead = iter.next().toString();
						ohmpg.setSubMjrHead(subMjrHead);
						subMjrHeadDataList.add(ohmpg);
					}
					for (Iterator iter = minorHeadList.iterator(); iter
							.hasNext();) {
						// Object[] row = (Object[])iter.next();
						ohmpg = new HrPayOrderHeadCustomMpg();
						String minorHead = iter.next().toString();
						ohmpg.setMinorHead(minorHead);
						minorHeadDataList.add(ohmpg);
					}
					for (Iterator iter = subHeadList.iterator(); iter.hasNext();) {
						Object[] row = (Object[]) iter.next();
						HrPayOrderHeadCustomMpg ohmpg1 = new HrPayOrderHeadCustomMpg();
						
						String subHeadId = row[0] != null ? row[0].toString()
								: "";
						
						//// get element code instead of sub head id 
						String elementcode=orderHeadMpgDAOImpl.getElementCodeFromSubHdId(Long.parseLong(subHeadId));
						
						String subHeadCode = row[1] != null ? row[1].toString()
								: "";
						String subHeadName = row[2] != null ? row[2].toString()
								: "";
						subHeadName = "(" + subHeadCode + ")" + subHeadName;
						ohmpg1.setSubHead(subHeadCode);
						ohmpg1.setSubHeadId(Long.parseLong(elementcode));
						ohmpg1.setSubHeadName(subHeadName);
						subHeadDataList.add(ohmpg1);
					}
					// Added By Urvin Shah.
					//billHeadMpg = billHeadMpgDAO.read(bhMapId);
					ohMpg.setPostType(billHeadMpg.getPostType());
					// End.
					objectArgs.put("demandNoList", demandNoDataList);
					objectArgs.put("mjrHeadList", mjrHeadDataList);
					objectArgs.put("subMjrHeadList", subMjrHeadDataList);
					objectArgs.put("minorHeadList", minorHeadDataList);
					objectArgs.put("subHeadList", subHeadDataList);
					objectArgs.put("hrPayBillHeadMpg", ohMpg);
				}
				objectArgs.put("hrPayBillHeadMpg", ohMpg);

			}

			// Added By Urvin Shah
			List lstPostType = cmnLookupMstDAO
					.getAllChildrenByLookUpNameAndLang("PostType", 1); // Fetch
																		// the
																		// Post
																		// Types
																		// from
																		// the
																		// CmnLookupMst.
			objectArgs.put("lstPostType", lstPostType);
			// End.

			// Added by Mrugesh
			String headChargable = "";
			if (billHeadMpg.getHeadChargable() != null
					&& billHeadMpg.getHeadChargable() != "")
				headChargable = billHeadMpg.getHeadChargable();
			objectArgs.put("headChargable", headChargable);
			// Ended

			// objectArgs.put("orderresultSet", orderresultSet);
			objectArgs.put("headresultSet", headresultSet);
			objectArgs.put("dsgnList", dsgnList);
			objectArgs.put("deptList", deptList);
			objectArgs.put("classList", classList);
			objectArgs.put("result", "success");
			resultObject.setResultValue(objectArgs);
			if (!editflag.equals("Y"))
				resultObject.setViewName("billheadMaster");
			else
				resultObject.setViewName("billheadEdit");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return resultObject;
	}

	// This method will insert and update the bill data
	public ResultObject insertBillHeadDtls(Map objectArgs) 
	{

		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			long msg = 0;
			logger.info("Inside Insert Master Details Service");
			
			
			

			HrPayBillHeadMpg hrPayBillHeadMpgObj = (HrPayBillHeadMpg) objectArgs.get("billHeadMpg");// object of VO
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			logger.info("Bill Number " + hrPayBillHeadMpgObj.getBillId());
			long billID = Long.parseLong(objectArgs.get("bill").toString());
			String designaton = objectArgs.get("designationList").toString();
			String classId = objectArgs.get("classId").toString();
			long subheadId = Long.parseLong(objectArgs.get("head").toString());
			String editFromVO = objectArgs.get("updateflag").toString();
			
			
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());// For Language independent
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			
			BillHeadMpgDAOImpl billHeadMpgDAOImpl =null; 
				//new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class, serv.getSessionFactory());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			
			logger.info("Flag which display go in insert or update "+ editFromVO);
			logger.info("Bill Number is:--->>>" + billID);
			logger.info("designation------------->>>>>>>>>"+ designaton.toString());
			logger.info("classId------------->>>>>>>>>" + classId.toString());
			logger.info("subheadId is:--->>>" + subheadId);
			
			
			long finYearId = 21; // needs to change hard coded for financial year
			
			// Added By Urvin Shah.
			
			CmnLookupMst postTypeLookupId = null;
			
			if (hrPayBillHeadMpgObj.getPostType() != null) 
			{
				postTypeLookupId = new CmnLookupMst();
				postTypeLookupId = hrPayBillHeadMpgObj.getPostType();
			}
			// End.
			
			Date sysdate = new Date();
			String headChargable = "";
			if (hrPayBillHeadMpgObj.getHeadChargable() != null && hrPayBillHeadMpgObj.getHeadChargable() != "")
				headChargable = hrPayBillHeadMpgObj.getHeadChargable();
			
			

			BillHeadMasterServiceImplHelper helper = new BillHeadMasterServiceImplHelper(serv);
			
			long elementCode = subheadId;

			if (editFromVO != null && editFromVO.equalsIgnoreCase("Y"))
			{
				logger.info("Inside Update mode");
				helper.updateBillHeadDtls(hrPayBillHeadMpgObj, headChargable, postTypeLookupId, userId, postId, subheadId, elementCode, finYearId, classId, cmnLocationMst, designaton);
				 objectArgs.put("msg", "Record Updated Successfully");
				msg = 1;// for display message for update
				logger.info("Updated successfully................");
			}

			else if (editFromVO != null && editFromVO.equalsIgnoreCase("N"))
			{

				logger.info("INSIDE Insert Order Details");
				helper.insertBillHeadDtls(postId, postTypeLookupId, headChargable, userId, langId, locId, billID, finYearId, classId, cmnLocationMst, designaton, elementCode);
				objectArgs.put("msg", "Record Inserted Successfully");
				logger.info("Inserted Successfully");

			}

			if (msg == 1)
				objectArgs.put("MESSAGECODE", 300006);// message code from frm_message_mst 300006 for Update
			else
				objectArgs.put("MESSAGECODE", 300005);// message code from  frm_message_mst 300005 for Insert

			resultObject.setResultCode(ErrorConstants.SUCCESS);

			resultObject.setResultValue(objectArgs);
			if (msg == 1)
				resultObject.setViewName("billheadMaster");// For Redirect from
															// message to view
															// jsp
			else
				resultObject.setViewName("billheadMaster");// For Redirect from
															// message to view
															// jsp

			System.out
					.println("Inserted Sucssesfully and End of Insert/Update Method");

		}
		catch (NullPointerException ne) {
			logger.info("Null Pointer Exception Ocuures...insertbillData");
			ne.printStackTrace();
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}

		catch (Exception e) 
		{

			logger.info("Exception Ocuures...insertOuterData");
			e.printStackTrace();
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}
		return resultObject;
	}
	
	

	public ResultObject chkbillHeadunique(Map objectArgs) {
		logger.info("*********chkbillHeadunique*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		// DesigMasterDAO desigMasterDAO = new
		// DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
		long billId = 0;
		String location_Id = "";
		long bhMapId = 0;

		Map voToService = (Map) objectArgs.get("voToServiceMap");
		billId = Long.parseLong((String) voToService.get("bill"));
		location_Id = (String) voToService.get("location_Id");
		bhMapId = Long.parseLong((String) voToService.get("bhMapId"));
		logger.info("Bill Head Mapping Id is-------->>>" + bhMapId);

		Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
		BillHeadMpgDAOImpl billHeadMpgDAOImpl =null;
			//new BillHeadMpgDAOImpl(				HrPayBillHeadMpg.class, serv.getSessionFactory());
		List mpgDataList = billHeadMpgDAOImpl.chkBillHeadDataById(billId,
				location_Id, bhMapId);
		StringBuffer propertyList = new StringBuffer();
		logger.info("Mapping List Size is----->>" + mpgDataList.size());
		propertyList.append("<bh-mapping>");
		if (mpgDataList != null && mpgDataList.size() > 0)
			propertyList.append("<mpgFlag>").append("<![CDATA[").append(
					mpgDataList.size()).append("]]>").append("</mpgFlag>");
		else
			propertyList.append("<mpgFlag>").append("<![CDATA[").append(-1)
					.append("]]>").append("</mpgFlag>");
		propertyList.append("</bh-mapping>");

		String mpgdata = new AjaxXmlBuilder().addItem("ajax_key",
				propertyList.toString()).toString();
		objectArgs.put("ajaxKey", mpgdata);

		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("ajaxData");
		logger.info("After Service Called.........\n" + mpgdata);
		return resultObject;

	}

	public ResultObject getDesignationsByGrade(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try {
			// this method shud be analyzed again as designation can be in more
			// than on ecklassesw
			logger.info("Inside  in getDesignationsByGrade method");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			Map voToService = (Map) objectArgs.get("voToServiceMap");
			String gradeId = (String) voToService.get("gradeId");
			String restGradeId = (String) voToService.get("restGradeId");
			String edit = (String) voToService.get("edit");

            if(gradeId!=null && gradeId.trim().startsWith(",") && gradeId.length() > gradeId.indexOf(",")+1 )
            {
            	gradeId = gradeId.substring(gradeId.indexOf(",")+1);	
            }
            
			logger.info("Grade Id from Request is " + gradeId);
			logger.info("Edit from Request is " + edit);
			BillHeadDao gradeDAO = new BillHeadDao(HrPayBillHeadCustomVO.class,
					serv.getSessionFactory());

			if (edit != null && edit.equalsIgnoreCase("Y")) {

				logger.info("Inside edit flag Yes");
				String restgradeId = (String) voToService.get("restGradeId");
				List gradeListForRemove = gradeDAO.removegradeList(gradeId,
						restGradeId);
				HrPayBillHeadCustomVO bhMpg = new HrPayBillHeadCustomVO();
				StringBuffer desigBuffer = new StringBuffer();
				Iterator itr = gradeListForRemove.iterator();
				List dsgnList = new ArrayList();
				String dsgnName = "";
				String dsgnId = "";
				String gradeName = "";
				String gradeid = "";

				for (int i = 0; i < gradeListForRemove.size(); i++) {
					bhMpg = new HrPayBillHeadCustomVO();
					Object[] rowList = (Object[]) gradeListForRemove.get(i);
					dsgnName = rowList[0].toString() + "("
							+ rowList[3].toString() + ")";
					bhMpg.setDsgnName(dsgnName);

					dsgnId = (rowList[1].toString());
					bhMpg.setDsgnId(dsgnId);

					gradeid = rowList[2].toString();
					bhMpg.setGradeId(gradeid);

					gradeName = (rowList[3].toString());
					bhMpg.setGradeName(gradeName);

					desigBuffer.append("<designation>");
					desigBuffer.append("<desig-code>").append("<![CDATA[")
							.append(dsgnId).append("]]>").append(
									"</desig-code>");
					desigBuffer.append("<desig-name>").append("<![CDATA[")
							.append(dsgnName).append("]]>").append(
									"</desig-name>");
					desigBuffer.append("</designation>");

					dsgnList.add(bhMpg);
				}

				String designationXML = desigBuffer.toString();

				String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key",
						designationXML).toString();
				objectArgs.put("ajaxKey", resultAjaxXml);

				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ajaxData");

			}

			else {

				logger.info("Inside edit flag No");

				List gradeList = gradeDAO.gradeList(gradeId);
				HrPayBillHeadCustomVO bhMpg = new HrPayBillHeadCustomVO();
				StringBuffer desigBuffer = new StringBuffer();
				Iterator itr = gradeList.iterator();
				List dsgnList = new ArrayList();
				String dsgnName = "";
				String dsgnId = "";
				String gradeName = "";
				String gradeid = "";

				for (int i = 0; i < gradeList.size(); i++) {
					bhMpg = new HrPayBillHeadCustomVO();
					Object[] rowList = (Object[]) gradeList.get(i);
					dsgnName = rowList[0].toString() + "("
							+ rowList[3].toString() + ")";
					// logger.info("+++++ dsgnName+++++"+dsgnName);
					bhMpg.setDsgnName(dsgnName);

					dsgnId = (rowList[1].toString());
					// logger.info("+++++ dsgnId+++++"+dsgnId);
					bhMpg.setDsgnId(dsgnId);

					gradeid = rowList[2].toString();
					// logger.info("+++++ gradeid+++++"+gradeid);
					bhMpg.setGradeId(gradeid);

					gradeName = (rowList[3].toString());
					// logger.info("+++++ gradeName+++++"+gradeName);
					bhMpg.setGradeName(gradeName);

					desigBuffer.append("<designation>");
					desigBuffer.append("<desig-code>").append("<![CDATA[")
							.append(dsgnId).append("]]>").append(
									"</desig-code>");
					desigBuffer.append("<desig-name>").append("<![CDATA[")
							.append(dsgnName).append("]]>").append(
									"</desig-name>");
					desigBuffer.append("</designation>");

					dsgnList.add(bhMpg);
				}

				String designationXML = desigBuffer.toString();

				String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key",
						designationXML).toString();
				objectArgs.put("ajaxKey", resultAjaxXml);

				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ajaxData");

			}

		} catch (Exception e) {

			logger
					.info("Exception in PaybillParaServiceImpl in getDesignationsByGrade method "
							+ e);
			e.printStackTrace();
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}
		return resultObject;
	}
	
	
	
	public ResultObject populateHeadStructure(Map objectArgs)
	{
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		logger.info("ajax calling....!!!!!!");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try
		{
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		//StringBuffer ajax = new StringBuffer();
		//ajax.append("<HeadStructure>");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		
		String scheme =  StringUtility.getParameter("HeadStructure", request);
		logger.info("request...."+request);
		logger.info(request.getParameterNames());
		Enumeration paraNames = request.getParameterNames();
		while(paraNames.hasMoreElements())
        {
       	String paraName = (String)paraNames.nextElement();
       	String value = StringUtility.getParameter(paraName,request);
       	
       	logger.info("paraName:::"+paraName+" value::::"+value);
        }
		
		logger.info(request.getParameter("HeadStructure"));
		
		logger.info("scheme:::::::::::::: "+ scheme);
		StringBuffer billNoStr = new StringBuffer();
		billNoStr.append("<HeadStructure>");
		if(scheme !=null && !scheme.equals(""))
		{
			long schemeNo = Long.parseLong(scheme);
			SgvaBudSubHeadMpgDaoImpl sgvaDao = new SgvaBudSubHeadMpgDaoImpl(SgvaBudsubhdMst.class,serv.getSessionFactory());
			List headStructure = sgvaDao.getheadStructure(schemeNo);
			Object[] newArr;
			
			logger.info("head str"+headStructure);
			
			if (headStructure != null) 
			{
				for (int i = 0; i < headStructure.size(); i++) 
				{
					newArr = (Object[])headStructure.iterator().next();
					logger.info("array is :::"+newArr);
					String demandCode = newArr[0].toString();
					String mjrHd = newArr[1].toString();
					String subMjrHd = newArr[2].toString();
					String minorHd = newArr[3].toString();
					String subminor = newArr[4].toString();
					//String BudSubHdId = newArr[5].toString();
					String BudSubHdId = newArr[6].toString();
					
					
					billNoStr.append("<demandCode>").append(demandCode).append("</demandCode>");
					billNoStr.append("<mjrHd>").append(mjrHd).append("</mjrHd>");
					billNoStr.append("<subMjrHd>").append(subMjrHd).append("</subMjrHd>");
					billNoStr.append("<minorHd>").append(minorHd).append("</minorHd>");
					billNoStr.append("<subminor>").append(subminor).append("</subminor>");
					billNoStr.append("<BudSubHdId>").append(BudSubHdId).append("</BudSubHdId>");
					
				}
			}
			
		}
		billNoStr.append("</HeadStructure>");
		String searchHeadStructure = new AjaxXmlBuilder().addItem("ajax_key",billNoStr.toString()).toString();
			
		logger.info(billNoStr);
		
		
		
		objectArgs.put("ajaxKey", searchHeadStructure);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("ajaxData");
		}
		catch(Exception e )
		{
			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

}
