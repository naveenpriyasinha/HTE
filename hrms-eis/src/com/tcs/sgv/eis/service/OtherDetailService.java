package com.tcs.sgv.eis.service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnCityMstDAOImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.HstDcpsOtherChanges;
import com.tcs.sgv.dcps.valueobject.HstDcpsPersonalChanges;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.eis.dao.CmnlookupMstDAOImpl;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.eis.dao.OtherDetailDAO;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PsrPostMpgDAOImpl;
import com.tcs.sgv.eis.dao.empPunishmentDAOImpl;
import com.tcs.sgv.eis.dao.getGradDesgMapDAO;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.CommAllOtherDetailsVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMpg;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrEmpPunishmentDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.eis.valueobject.MstPayrollDesignationMst;
import com.tcs.sgv.eis.valueobject.PayrollCustomVO;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;
import com.tcs.sgv.user.dao.UserPostDAO;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;

public class OtherDetailService extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	ResourceBundle constantsBundle = ResourceBundle.getBundle("../resources/Payroll");

	// long finYearId = Long.parseLong(
	// constantsBundle.getString("currentFinYearId").toString() ); 22
	long finYearId = Long.parseLong(constantsBundle.getString("finYearId").toString()); // 21

	/**
	 * @author : varun sharma
	 * @purpose: get "location wise" and "financial year wise" all available bill
	 *           numbers.
	 * @param :
	 *            objectArgs
	 */
	public ResultObject getBillNos(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		try {
			OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			Map loginMap = (Map) objectArgs.get("baseLoginMap");

			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			logger.info("locId is: " + locId);

			List<MstDcpsBillGroup> billNos = null;

			billNos = otherDtlsDao.getBillNos(locId, finYearId);
			logger.info("bill no count is: " + billNos.size());

			// put in map
			objectArgs.put("billNoList", billNos);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);// put in result object
			resultObject.setViewName("employeeCommissionMpg");// set view name

		} catch (Exception e) {
			resultObject = new ResultObject(ErrorConstants.ERROR);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.info(e);
		}

		return resultObject;
	}// end method

	/**
	 * @author : varun sharma
	 * @purpose: gets bill number wise all employees which are not in 6th pay
	 * @param :
	 *            objectArgs
	 */
	public ResultObject getOldCommissionEmployees(Map objectArgs) {
		logger.info("Fetching all employees which does not belong to sixth pay");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

		try {
			OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long billId = Long.parseLong(request.getParameter("billId"));

			logger.info("billId is: " + billId);
			logger.info("locId is: " + locId);
			logger.info("langId is: " + langId);

			// fetch all those employees which are not in 6th pay commission yet.
			List employeeList = otherDtlsDao.getBillwiseAllOldCommissionEmployeees(langId, locId, billId);
			logger.info("Total number of employees which are not in 6th pay are : " + employeeList.size());

			Iterator iterator = employeeList.iterator();
			// List oldEmpList = new ArrayList();

			StringBuffer oldEmpList = new StringBuffer();

			while (iterator.hasNext()) {

				Object[] record = (Object[]) iterator.next();
				logger.info("< record set >");
				logger.info(record[0].toString());
				logger.info(record[1].toString());
				logger.info("< /record set >");

				oldEmpList.append("<oldEmpList>");
				oldEmpList.append("<empId>").append(record[0]).append("</empId>");
				oldEmpList.append("<empName>").append(record[1]).append("</empName>");
				oldEmpList.append("</oldEmpList>");

			} // end while

			String oldEmployeesList = new AjaxXmlBuilder().addItem("ajax_key", oldEmpList.toString()).toString();

			// put in map
			objectArgs.put("ajaxKey", oldEmployeesList);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);// put in result object
			resultObject.setViewName("ajaxData");// set view name

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
			resultObject = new ResultObject(ErrorConstants.ERROR);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}

		return resultObject;
	}// end method

	/**
	 * @author : varun sharma
	 * @purpose: upgrades employees to new commission. i.e. 6th pay commission
	 * @param :
	 *            objectArgs
	 */
	public ResultObject upgradeEmployeesCommission(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		long locId = 0;

		try {

			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			// post id
			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			// user id
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMstUpdatedBy = orgUserMstDaoImpl.read(userId);

			// locId
			locId = StringUtility.convertToLong(loginMap.get("locationId").toString());

			OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,
					serv.getSessionFactory());

			long billId = 0; // billId (pk) : bill in which employee belong
			billId = Long.parseLong(voToService.get("billNoCmboBx").toString());
			int counter = 0; // No. of employees to be upgraded
			counter = Integer.parseInt(voToService.get("hiddenCounter").toString());

			logger.info("billId is: " + billId);
			logger.info("counter is: " + counter);

			for (int i = 0; i < counter; i++) {
				long empId = 0; // employee who has opted to upgrade
				long basicSalary = 0; // new basic salary of employee
				String effectiveDate = ""; // effective date from which employee accepts to be in new commission

				empId = Long.parseLong(voToService.get("empId" + i).toString()); // OrgEmpMSt.empId
				basicSalary = Long.parseLong(voToService.get("txtBasicPay" + i).toString());
				effectiveDate = voToService.get("txtEffectiveDate" + i).toString();

				logger.info("*******************************************");
				logger.info("empId" + i + " is: " + empId);
				logger.info("basicSalary" + i + " is: " + basicSalary);
				logger.info("effectiveDate" + i + " is: " + effectiveDate);

				// Get new SgdId from old ones
				List<HrEisSgdMpg> empList = otherDetailDAOImpl.getSgdId(empId);
				logger.info("upgraded employees list size : " + empList.size());

				if (empList != null) {
					if (empList.get(0) != null) {
						HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();

						// read current information of this employee
						hrEisOtherDtls = otherDetailDAOImpl.getOtherData(empId);// OrgEmpMst.empId

						// set new sgdId
						hrEisOtherDtls.setHrEisSgdMpg((HrEisSgdMpg) empList.get(0));
						logger.info("New SgdID is: " + hrEisOtherDtls.getHrEisSgdMpg().getSgdMapId());

						// set new basic
						if (basicSalary > 0) {
							hrEisOtherDtls.setotherCurrentBasic(basicSalary);
							logger.info("new basic salary is: " + hrEisOtherDtls.getotherCurrentBasic());
						}

						// set effective date
						Date date = new Date(effectiveDate);
						logger.info("effective date of acceptance is: " + date);
						hrEisOtherDtls.setCommissionAcceptanceDate(date);

						// set updated by post
						hrEisOtherDtls.setOrgPostMstByUpdatedByPost(orgPostMst);

						// set updated by user
						hrEisOtherDtls.setOrgUserMstByUpdatedBy(orgUserMstUpdatedBy);

						// set updated date
						hrEisOtherDtls.setUpdatedDate(new Date());

						try {
							// update
							otherDetailDAOImpl.update(hrEisOtherDtls);
							logger.info("Other Details records updated successfully..");

							// calculate allowance and deductions as per new basic
							EmpAllwMapService empAllwMapService = new EmpAllwMapService();

							objectArgs.put("empOtherVO", hrEisOtherDtls);
							objectArgs.put("editMode", "Y");

							resultObject = empAllwMapService.insertEmpAllowData(objectArgs);
							logger.info("Employee Allowance Mapping records updated successfully..");

						} catch (Exception e) {
							logger.info("Exception occured in updating employee to new commission");
							logger.error("Error is: " + e.getMessage());
						}
						logger.info("*******************************************");

					}
				} else {
					// intimate user that no unique mapping exists for Scale + Grade + Design +
					// Grade Pay + Pay Band.
					// possibly new SgdId is required?
					logger.info("no unique mapping exists");
				}

			} // end for

			List<MstDcpsBillGroup> billNos = null;

			billNos = otherDetailDAOImpl.getBillNos(locId, finYearId);
			logger.info("bill no count is: " + billNos.size());

			// put in map
			objectArgs.put("billNoList", billNos);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);// put in result object
			resultObject.setViewName("employeeCommissionMpg");// set view name

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
			resultObject = new ResultObject(ErrorConstants.ERROR);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}

		return resultObject;
	}// end method

	public ResultObject insertOtherData(Map objectArgs) {
		Calendar calTime = Calendar.getInstance();
		logger.info("The Inintial time for the Other Details' Service is:-" + calTime.getTimeInMillis());
		logger.info("IN insertOtherData of OTHER DETAIL SERVICE ");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		try {
			HrEisOtherDtls otherDtlsVO = (HrEisOtherDtls) objectArgs.get("otherDtlsVO");
			int isAvailedHRA = otherDtlsVO.getIsAvailedHRA();

			OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			// String editMode = objectArgs.get("editMode").toString();
			Date sysdate = new Date();
			GenericDaoHibernateImpl gImpl = null;
			Calendar calIncrementDate;
			Map loginMap = (Map) objectArgs.get("baseLoginMap");

			ResourceBundle payrollConstants = ResourceBundle.getBundle("resources.Payroll");

			long fifthPayCommLookupId = Long.parseLong(payrollConstants.getString("commissionFive"));
			long sixthPayCommLookupId = Long.parseLong(payrollConstants.getString("commissionSix"));

			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMst orgUserMst = null;
			if (loginMap.get("loggedInUserMst") != null) {
				orgUserMst = (OrgUserMst) loginMap.get("loggedInUserMst");
			} else {
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
				orgUserMst = orgUserMstDaoImpl.read(userId);
				loginMap.put("loggedInUserMst", orgUserMst);
			}

			long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
			CmnDatabaseMst cmnDatabaseMst = null;
			if (loginMap.get("loggedInDBMst") != null) {
				cmnDatabaseMst = (CmnDatabaseMst) loginMap.get("loggedInDBMst");
			} else {
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,
						serv.getSessionFactory());
				cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
				loginMap.put("loggedInDBMst", cmnDatabaseMst);
			}

			// Modify By Urvin Shah
			/*
			 * long
			 * locId=StringUtility.convertToLong(loginMap.get("locationId").toString());
			 * CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new
			 * CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			 * CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
			 */
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");

			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			CmnLanguageMst cmnLanguageMst = null;
			if (loginMap.get("loggedInLangMst") != null) {
				cmnLanguageMst = (CmnLanguageMst) loginMap.get("loggedInLangMst");
			} else {
				CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,
						serv.getSessionFactory());
				cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
				loginMap.put("loggedInLangMst", cmnLanguageMst);
			}

			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMst orgPostMst = null;
			if (loginMap.get("primaryPostMst") != null) {
				orgPostMst = (OrgPostMst) loginMap.get("primaryPostMst");
			} else {
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				orgPostMst = orgPostMstDaoImpl.read(postId);
				loginMap.put("primaryPostMst", orgPostMst);
			}
			Date IncrementDate = null;
			Date effectiveDate = null;
			IncrementDate = (Date) objectArgs.get("incrementDate");
			logger.info("the date of increment in service" + IncrementDate);
			effectiveDate = (Date) objectArgs.get("effectiveDate");
			// for update purpose

			String editMode = objectArgs.get("edit").toString();
			if (objectArgs.get("edit") != null && objectArgs.get("edit").toString().equalsIgnoreCase("Y")) {
				logger.info(" insertOtherData inside if service-------edit mode---------------");

				long otherId = Long.parseLong(objectArgs.get("otherId").toString());

				logger.info("Other id for getting all other details data is " + otherId);

				CommAllOtherDetailsVO otherData = otherdtlsDao.getAllOtherDetailsByOtherId(otherId, langId);

				objectArgs.put("billGroupId", otherData.getDcpsDdoBillGroupId());
				// otherDtlsVO=otherdtlsDao.read(otherId);
				otherDtlsVO = otherData.getOtherDetailsVO();

				/*
				 * HrEisEmpMst hrEisObj=new HrEisEmpMst(); gImpl = new
				 * GenericDaoHibernateImpl(HrEisEmpMst.class);
				 * gImpl.setSessionFactory(serv.getSessionFactory()); hrEisObj = (HrEisEmpMst)
				 * gImpl.read(Long.parseLong(objectArgs.get("empId").toString()));
				 * 
				 * otherDtlsVO.setHrEisEmpMst(hrEisObj);
				 */

				// HrEisSgdMpg hrEisSgdMpg=new HrEisSgdMpg();
				long MonthsForIncrement = 0;
				double scale_start_amt = 0;
				double GradePayExcell = 0;
				long sgdMapId = Long.parseLong(objectArgs.get("sgdMapId").toString());
				HrEisSgdMpg hrEisSgdMpg = null;
				if (sgdMapId == otherData.getSgdMapId()) {
					scale_start_amt = otherData.getHrEisScaleMst().getScaleStartAmt();
					GradePayExcell = otherData.getHrEisScaleMst().getScaleGradePay();
					hrEisSgdMpg = otherData.getHrEisSgdMpg();
				} else {
					gImpl = new GenericDaoHibernateImpl(HrEisSgdMpg.class);
					gImpl.setSessionFactory(serv.getSessionFactory());
					hrEisSgdMpg = (HrEisSgdMpg) gImpl.read(Long.parseLong(objectArgs.get("sgdMapId").toString()));

					// added by Ankit-Mrugesh
					if (hrEisSgdMpg != null) {
						// MonthsForIncrement=hrEisSgdMpg.getHrEisScaleMst().getMonthsForIncrement();
						scale_start_amt = hrEisSgdMpg.getHrEisScaleMst().getScaleStartAmt();
						GradePayExcell = hrEisSgdMpg.getHrEisScaleMst().getScaleGradePay();
					}
				}
				objectArgs.put("scale_start_amt", scale_start_amt);
				objectArgs.put("GradePayExcell", GradePayExcell);
				logger.info(
						"::::::::::::::::::::::::::::::::::::::::: WHILE EDIT MODE scale_start_amt in Other detail Service is  =  "
								+ scale_start_amt);
				logger.info(
						"::::::::::::::::::::::::::::::::::::::::: WHILE EDIT MODE GradePayExcell  in Other detail Service is  =  "
								+ GradePayExcell);

				// Commented By Paurav for getting data from common vo
				// Date
				// joiningDate=(Date)otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getEmpDoj();3
				Date joiningDate = otherData.getEmpDoj();
				SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
				String txtYear = sdfObj.format(joiningDate);
				int year = Integer.parseInt(txtYear);
				sdfObj = new SimpleDateFormat("MM");
				int month = Integer.parseInt(sdfObj.format(joiningDate));

				year += MonthsForIncrement / 12;
				month += MonthsForIncrement % 12;
				year += month / 12;
				month %= 12;
				sdfObj = new SimpleDateFormat("dd");
				int day = Integer.parseInt(sdfObj.format(joiningDate));
				day = 1;// always increment Date should be 1st
				sdfObj = new SimpleDateFormat("dd/MM/yyyy");
				calIncrementDate = new GregorianCalendar((int) year, month - 1, day);
				// String IncrementDate = sdfObj.format(calIncrementDate.getTime());

				otherDtlsVO.setHrEisSgdMpg(hrEisSgdMpg);

				/*
				 * HrEisDeptMst deptObj=new HrEisDeptMst(); gImpl = new
				 * GenericDaoHibernateImpl(HrEisDeptMst.class);
				 * gImpl.setSessionFactory(serv.getSessionFactory()); deptObj = (HrEisDeptMst)
				 * gImpl.read(Long.parseLong(objectArgs.get("deptId").toString()));
				 * 
				 * otherDtlsVO.setHrEisDeptMst(deptObj);
				 */

				/*
				 * if(objectArgs.get("quaterId").toString()!=null &&
				 * !objectArgs.get("quaterId").toString().equals("") &&
				 * !objectArgs.get("quaterId").toString().equals("0")) { HrQuaterTypeMst
				 * hrQuaterTypeMst = new HrQuaterTypeMst(); gImpl = new
				 * GenericDaoHibernateImpl(HrQuaterTypeMst.class);
				 * gImpl.setSessionFactory(serv.getSessionFactory()); hrQuaterTypeMst =
				 * (HrQuaterTypeMst)
				 * gImpl.read(Long.parseLong(objectArgs.get("quaterId").toString()));
				 * otherDtlsVO.setHrQuaterTypeMst(hrQuaterTypeMst); } else {
				 * otherDtlsVO.setHrQuaterTypeMst(null); }
				 */

				otherDtlsVO.setIncomeTax(Long.parseLong(objectArgs.get("incomeTax").toString()));
				otherDtlsVO.setCity(Long.parseLong(objectArgs.get("cityId").toString()));
				otherDtlsVO.setPhyChallenged(objectArgs.get("ishandicapped").toString());
				// otherDtlsVO.setIsAvailedHRA(isAvailedHRA);
				// added by Ankit Bhatt
				int isSis = 0;
				if (objectArgs.get("isSis") != null)
					isSis = Integer.parseInt(objectArgs.get("isSis").toString());
				// otherDtlsVO.setIsSis(isSis);
				// ended

				// added by japen pathak
				int ats30 = 0;
				int ats50 = 0;
				int force25 = 0;
				int force100 = 0;

				if (objectArgs.get("ATS30") != null)
					ats30 = Integer.parseInt(objectArgs.get("ATS30").toString());
				// otherDtlsVO.setAtsIncentive30(ats30);

				if (objectArgs.get("ATS50") != null)
					ats50 = Integer.parseInt(objectArgs.get("ATS50").toString());
				// otherDtlsVO.setAtsIncentive50(ats50);

				if (objectArgs.get("force25") != null)
					force25 = Integer.parseInt(objectArgs.get("force25").toString());
				// otherDtlsVO.setForce1Incentive25(force25);

				if (objectArgs.get("force100") != null)
					force100 = Integer.parseInt(objectArgs.get("force100").toString());
				// otherDtlsVO.setForce1Incentive100(force100);

				// ended by japen pathak

				otherDtlsVO.setotherCurrentBasic(Long.parseLong(objectArgs.get("initialBasic").toString()));

				otherDtlsVO.setUpdatedDate(sysdate);
				otherDtlsVO.setOrgPostMstByUpdatedByPost(orgPostMst);
				otherDtlsVO.setOrgUserMstByUpdatedBy(orgUserMst);
				otherDtlsVO.setIncrementDate(IncrementDate);
				otherDtlsVO.setCommissionAcceptanceDate(effectiveDate);
				// logger.info("increment date is from service1=====>"+IncrementDate);
				otherDtlsVO.setMedAllowance(Long.parseLong(objectArgs.get("medAllowance").toString()));
				otherDtlsVO.setUniformAvailed(Long.parseLong(objectArgs.get("uniformAvailed").toString()));
				// otherDtlsVO.setSis1979Flag(objectArgs.get("sis1979Flag")!=null?objectArgs.get("sis1979Flag").toString():"");
				// otherDtlsVO.setOthFamilyPlanning(objectArgs.get("FamilyPlannig")!=null?objectArgs.get("FamilyPlannig").toString():"");
				// otherDtlsVO.setFamilyPlnAmt(objectArgs.get("FamilyPlnAmt")!=null?Integer.parseInt(objectArgs.get("FamilyPlnAmt").toString()):0);
				/*
				 * //for Personal pay if(otherDtlsVO.getHrEisSgdMpg()!=null) { EmpAllwMapDAOImpl
				 * empAllwMapDAO = new
				 * EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				 * HrPayEmpallowMpg empAllwMpg= new HrPayEmpallowMpg(); long
				 * PPAY_CODE=Long.parseLong(constantsBundle.getString("PPAY_CODE")); String
				 * cols[] = {"hrEisEmpMst","hrPayAllowTypeMst"}; HrPayAllowTypeMst
				 * hrPayAllowTypeMst = new HrPayAllowTypeMst();
				 * hrPayAllowTypeMst.setAllowCode(PPAY_CODE); Object vals[] =
				 * {otherDtlsVO.getHrEisEmpMst(),hrPayAllowTypeMst}; List <HrPayEmpallowMpg>
				 * empAllwMpglist = empAllwMapDAO.getListByColumnAndValue(cols, vals); double
				 * empAllowAmount=empAllwMpg.getEmpAllowAmount();
				 * 
				 * if(otherDtlsVO.getOthFamilyPlanning().equals("n")) empAllowAmount=0; else
				 * if(otherDtlsVO.getOthFamilyPlanning().equals("y")) { empAllowAmount =
				 * otherDtlsVO.getFamilyPlnAmt();
				 * if(otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleHigherEndAmt()>0&&
				 * otherDtlsVO.getotherCurrentBasic()>=otherDtlsVO.getHrEisSgdMpg().
				 * getHrEisScaleMst().getScaleEndAmt()) empAllowAmount=
				 * otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleHigherIncrAmt(); else
				 * empAllowAmount=
				 * otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleIncrAmt(); }
				 * 
				 * if(empAllwMpglist.size()>0) { empAllwMpg= empAllwMpglist.get(0);
				 * empAllwMpg.setEmpAllowAmount(empAllowAmount);
				 * empAllwMapDAO.update(empAllwMpg); } else { IdGenerator idGen = new
				 * IdGenerator(); Long id= idGen.PKGenerator("HR_PAY_EMPALLOW_MPG",objectArgs);
				 * logger.info("****************************the id generated is "+id);
				 * empAllwMpg.setAllowCode(id); empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
				 * empAllwMpg.setCmnLocationMst(cmnLocationMst);
				 * empAllwMpg.setCreatedDate(sysdate);
				 * empAllwMpg.setEmpAllowAmount(empAllowAmount);
				 * empAllwMpg.setEmpCurrentStatus(1);
				 * empAllwMpg.setHrEisEmpMst(otherDtlsVO.getHrEisEmpMst());
				 * empAllwMpg.setHrPayAllowTypeMst(hrPayAllowTypeMst);
				 * empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
				 * empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
				 * empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
				 * empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst); empAllwMpg.setTrnCounter(new
				 * Integer(1)); empAllwMpg.setUpdatedDate(sysdate);
				 * empAllwMapDAO.create(empAllwMpg); } }
				 */
				otherdtlsDao.update(otherDtlsVO);

				objectArgs.put("commonOtherData", otherData);
				Map mapForChangedRecords = objectArgs;

				// logger.info(" checking---------->>>>>>>>>>>>> " +
				// otherDtlsVO.getHrEisEmpMst().getOrgPostMst().getPostId());
				// long postIdNew = otherDtlsVO.getHrEisEmpMst().getOrgPostMst().getPostId();
				// long postIdNew = otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst()
				// long changedEmpId = otherDtlsVO.getHrEisEmpMst().getEmpId();

				// Commented By Paurav for getting data from common vo
				// long changedEmpId = otherDtlsVO.getHrEisEmpMst().getEmpId();
				long changedEmpId = otherData.getEmpId();

				// Commented By Paurav for getting data from common VO
				// long chUser =
				// otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
				long chUser = otherData.getUserId();

				// Commented By Paurav for getting data from commonVO
				/*
				 * NewEmployeeConfigDAOImpl newEmpDao = new
				 * NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory()); List
				 * postList = newEmpDao.getListByColumnAndValue("orgUserMst.userId", chUser);
				 * long postIdNew =
				 * ((OrgUserpostRlt)postList.get(0)).getOrgPostMstByPostId().getPostId();
				 */
				long postIdNew = otherData.getPostId();

				// long locId=Long.valueOf(loginMap.get("locationId").toString());
				long locId = Long.valueOf(loginMap.get("locationId").toString());

				mapForChangedRecords.put("changedPostId", postIdNew);
				mapForChangedRecords.put("changedEmpId", changedEmpId);
				mapForChangedRecords.put("locId", locId);
				mapForChangedRecords.put("serviceLocator", serv);
				mapForChangedRecords.put("OrgPostMst", orgPostMst);
				mapForChangedRecords.put("OrgUserMst", orgUserMst);
				mapForChangedRecords.put("cmnDatabaseMst", cmnDatabaseMst);
				GenerateBillServiceHelper generateBillServiceHelper = new GenerateBillServiceHelper();
				long changedRecordPK = generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"
						+ changedRecordPK);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"
						+ changedRecordPK);

				String empAllRecOther = "";
				if (StringUtility.getParameter("empAllRec", request) != null
						&& !StringUtility.getParameter("empAllRec", request).equals("")) {
					empAllRecOther = StringUtility.getParameter("empAllRec", request);
				}
				logger.info("empAllRecOther is =========>" + empAllRecOther);
				if (empAllRecOther.equalsIgnoreCase("true") == true)
					objectArgs.put("empAllRec", "true");

				else
					objectArgs.put("empAllRec", "false");

				if (objectArgs.get("orderDataFlag").toString().equals("Y")) {
					IdGenerator idgen = new IdGenerator();
					Long Pk = idgen.PKGenerator("HR_EIS_SCALE_MPG", objectArgs);
					// Long Pk=IDGenerateDelegate.getNextId("HR_EIS_SCALE_MPG", objectArgs);
					logger.info("Pk::::::::::" + Pk);
					HrEisScaleMpg mst = new HrEisScaleMpg();
					mst.setId(Pk);
					CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
					CmnLookupMst lookUp = cmnDao.read((Long) (objectArgs.get("reason")));
					if (lookUp != null)
						mst.setCmnLookupMst(lookUp);
					mst.setHrEisEmpMst(otherData.getHrEisEmpMst());
					mst.setHrEisSgdMpg(hrEisSgdMpg);
					mst.setOrder(objectArgs.get("orderNo").toString());
					// mst.setRemarks(objectArgs.get("remarks").toString());
					mst.setWefDate((Date) (objectArgs.get("orderDate")));
					serv.getSessionFactory().getCurrentSession().save(mst);
				}

			} else {
				logger.info(" insertOtherData inside if service-------insert mode---------------");

				IdGenerator idgen = new IdGenerator();
				Long otherId = idgen.PKGenerator("HR_EIS_OTHER_DTLS", objectArgs);
				// Long otherId=IDGenerateDelegate.getNextId("HR_EIS_OTHER_DTLS", objectArgs);
				logger.info("****************************the id generated is " + otherId);

				otherDtlsVO.setOtherId(otherId);

				logger.info("the set uniform code is " + otherDtlsVO.getUniformAvailed());
				String empAllRecOther = "";
				if (StringUtility.getParameter("empAllRec", request) != null
						&& !StringUtility.getParameter("empAllRec", request).equals("")) {
					empAllRecOther = StringUtility.getParameter("empAllRec", request);
				}
				logger.info("empAllRecOther is =========>" + empAllRecOther);
				// HrEisEmpMst hrEisObj=new HrEisEmpMst();
				HrEisEmpMst hrEisObj = null;
				gImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
				gImpl.setSessionFactory(serv.getSessionFactory());
				// changed by manoj for home integration of E - profile

				// hrEisObj = (HrEisEmpMst)
				// gImpl.read(Long.parseLong(objectArgs.get("empId").toString()));

				EmpInfoDAOImpl empInfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());

				long empId = StringUtility
						.convertToLong(objectArgs.get("empId") != null ? objectArgs.get("empId").toString() : "");
				logger.info("varun: empId in other details which is sent to empAllwMapService is: "
						+ objectArgs.get("empId"));
				List empMstList = empInfoDao.getHrEmpFromOrgEmpId(Long.parseLong(objectArgs.get("empId").toString()));// gImpl.getListByColumnAndValue("orgEmpMst.empId",
																														// Long.parseLong(objectArgs.get("empId").toString()));
				logger.info("Number of emp in list which are sent to empAllwMapService is : " + empMstList.size());
				if (empMstList != null && empMstList.size() > 0) {
					hrEisObj = (HrEisEmpMst) empMstList.get(0);
				}
				// end of changed by manoj for home integration of E - profile
				otherDtlsVO.setHrEisEmpMst(hrEisObj);

				HrEisSgdMpg hrEisSgdMpg = new HrEisSgdMpg();
				gImpl = new GenericDaoHibernateImpl(HrEisSgdMpg.class);
				gImpl.setSessionFactory(serv.getSessionFactory());
				hrEisSgdMpg = (HrEisSgdMpg) gImpl.read(Long.parseLong(objectArgs.get("sgdMapId").toString()));

				long MonthsForIncrement = 0;
				double scale_start_amt = 0;
				double GradePayExcell = 0;
				// Added by Mrugesh
				// String IncrementDate=null;
				if (StringUtility.getParameter("sgdMapId", request) != null
						&& !StringUtility.getParameter("sgdMapId", request).equals("")) {
					// sgdMapId=Long.parseLong(StringUtility.getParameter("sgdMapId",request));
					// logger.info("sgdMapId " + sgdMapId);
					// MonthsForIncrement=hrEisSgdMpg.getHrEisScaleMst().getMonthsForIncrement();
					scale_start_amt = hrEisSgdMpg.getHrEisScaleMst().getScaleStartAmt();
					GradePayExcell = hrEisSgdMpg.getHrEisScaleMst().getScaleGradePay();
					// MonthsForIncrement=hrEisSgdMpg.getHrEisScaleMst().getMonthsForIncrement();

					Date joiningDate = (Date) otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getEmpDoj();
					SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
					String txtYear = sdfObj.format(joiningDate);
					int year = Integer.parseInt(txtYear);
					sdfObj = new SimpleDateFormat("MM");
					int month = Integer.parseInt(sdfObj.format(joiningDate));

					year += MonthsForIncrement / 12;
					month += MonthsForIncrement % 12;
					year += month / 12;
					month %= 12;
					sdfObj = new SimpleDateFormat("dd");
					int day = Integer.parseInt(sdfObj.format(joiningDate));
					day = 1;// always increment Date should be 1st
					sdfObj = new SimpleDateFormat("dd/MM/yyyy");
					calIncrementDate = new GregorianCalendar((int) year, month - 1, day);
					// IncrementDate = sdfObj.format(calIncrementDate.getTime());
					otherDtlsVO.setHrEisSgdMpg(hrEisSgdMpg);
				} else {
					logger.info("The value of months for increment is----->" + MonthsForIncrement);
					otherDtlsVO.setHrEisSgdMpg(null);
				}
				// Ended by Mrugesh
				logger.info(
						"::::::::::::::::::::::::::::::::::::::::: WHILE INSERT MODE scale_start_amt in Other detail Service is  =  "
								+ scale_start_amt);
				logger.info(
						"::::::::::::::::::::::::::::::::::::::::: WHILE INSERT MODE GradePayExcell in Other detail Service is  =  "
								+ GradePayExcell);
				objectArgs.put("scale_start_amt", scale_start_amt);
				objectArgs.put("GradePayExcell", GradePayExcell);

				/*
				 * HrEisDeptMst deptObj=new HrEisDeptMst(); gImpl = new
				 * GenericDaoHibernateImpl(HrEisDeptMst.class);
				 * gImpl.setSessionFactory(serv.getSessionFactory()); deptObj = (HrEisDeptMst)
				 * gImpl.read(Long.parseLong(objectArgs.get("deptId").toString()));
				 * 
				 * otherDtlsVO.setHrEisDeptMst(deptObj);
				 */

				/*
				 * if(objectArgs.get("quaterId").toString()!=null&&!objectArgs.get("quaterId").
				 * toString().equals("")&&!objectArgs.get("quaterId").toString().equals("0")) {
				 * HrQuaterTypeMst hrQuaterTypeMst = new HrQuaterTypeMst(); gImpl = new
				 * GenericDaoHibernateImpl(HrQuaterTypeMst.class);
				 * gImpl.setSessionFactory(serv.getSessionFactory()); hrQuaterTypeMst =
				 * (HrQuaterTypeMst)
				 * gImpl.read(Long.parseLong(objectArgs.get("quaterId").toString()));
				 * otherDtlsVO.setHrQuaterTypeMst(hrQuaterTypeMst); } else {
				 * otherDtlsVO.setHrQuaterTypeMst(null); }
				 */
				otherDtlsVO.setUpdatedDate(sysdate);
				otherDtlsVO.setCreatedDate(sysdate);
				otherDtlsVO.setCmnDatabaseMst(cmnDatabaseMst);
				otherDtlsVO.setOrgPostMstByUpdatedByPost(orgPostMst);
				otherDtlsVO.setOrgPostMstByCreatedByPost(orgPostMst);
				otherDtlsVO.setOrgUserMstByUpdatedBy(orgUserMst);
				otherDtlsVO.setOrgUserMstByCreatedBy(orgUserMst);
				logger.info("increment date is from service2=====>" + IncrementDate);
				otherDtlsVO.setIncrementDate(IncrementDate);
				otherDtlsVO.setCommissionAcceptanceDate(effectiveDate);
				// otherDtlsVO.setFamilyPlnAmt(objectArgs.get("FamilyPlnAmt")!=null?Integer.parseInt(objectArgs.get("FamilyPlnAmt").toString()):0);
				// otherDtlsVO.setIsAvailedHRA(isAvailedHRA);
				otherDtlsVO.setTrnCounter(new Integer(1));

				logger.info("isAvailedHRA in new entry is: " + otherDtlsVO.getIsAvailedHRA());

				/*
				 * // for Personal pay
				 * if(otherDtlsVO.getOthFamilyPlanning().equals("y")&&otherDtlsVO.getHrEisSgdMpg
				 * ()!=null) { EmpAllwMapDAOImpl empAllwMapDAO = new
				 * EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				 * HrPayEmpallowMpg empAllwMpg= new HrPayEmpallowMpg(); long
				 * PPAY_CODE=Long.parseLong(constantsBundle.getString("PPAY_CODE"));
				 * HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
				 * hrPayAllowTypeMst.setAllowCode(PPAY_CODE); double
				 * empAllowAmount=empAllwMpg.getEmpAllowAmount();
				 * 
				 * empAllowAmount = otherDtlsVO.getFamilyPlnAmt();
				 * if(otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleHigherEndAmt()>0&&
				 * otherDtlsVO.getotherCurrentBasic()>=otherDtlsVO.getHrEisSgdMpg().
				 * getHrEisScaleMst().getScaleEndAmt()) empAllowAmount=
				 * otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleHigherIncrAmt(); else
				 * empAllowAmount=
				 * otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleIncrAmt();
				 * 
				 * 
				 * //create only { IdGenerator idGen = new IdGenerator(); Long id=
				 * idGen.PKGenerator("HR_PAY_EMPALLOW_MPG",objectArgs);
				 * logger.info("****************************the id generated is "+id);
				 * empAllwMpg.setAllowCode(id); empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
				 * empAllwMpg.setCmnLocationMst(cmnLocationMst);
				 * empAllwMpg.setCreatedDate(sysdate);
				 * empAllwMpg.setEmpAllowAmount(empAllowAmount);
				 * empAllwMpg.setEmpCurrentStatus(1);
				 * logger.info("setting HrEisEmpMst object in otherDtlsVo and empId is: "+
				 * otherDtlsVO.getHrEisEmpMst());
				 * empAllwMpg.setHrEisEmpMst(otherDtlsVO.getHrEisEmpMst());
				 * empAllwMpg.setHrPayAllowTypeMst(hrPayAllowTypeMst);
				 * empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
				 * empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
				 * empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
				 * empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst); empAllwMpg.setTrnCounter(new
				 * Integer(1)); empAllwMpg.setUpdatedDate(sysdate);
				 * empAllwMapDAO.create(empAllwMpg); } }
				 */
				if (empAllRecOther.equalsIgnoreCase("true") == true)
					objectArgs.put("empAllRec", "true");

				else
					objectArgs.put("empAllRec", "false");
				otherdtlsDao.create(otherDtlsVO);

			}

			objectArgs.put("empOtherVO", otherDtlsVO);

			// for hrTraEmpMpg by manoj
			objectArgs.put("editModeForTraMapping", editMode);
			Calendar beforeTraTime = Calendar.getInstance();
			logger.info("The Before Transport Service time for the is:-" + beforeTraTime.getTimeInMillis());

			ResultObject resultObj = serv.executeService("HrEmpTraMpgVogenImpl", objectArgs);
			objectArgs = (Map) resultObj.getResultValue();

			ResultObject resultObj1 = serv.executeService("HrEmpTraMpgServiceImpl", objectArgs);
			objectArgs = (Map) resultObj1.getResultValue();

			// end for hrTraEmpMpg by manoj
			// added by japen
			List<CmnLookupMst> reason = null;
			CmnLookupMstDAOImpl lookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			long commId = Long.valueOf(StringUtility.getParameter("payCommissionCombo", request));
			if (commId == 5) {
				reason = lookupDao.getAllChildrenByLookUpNameAndLang("Reason-ScaleRev-5th", 1);
				objectArgs.put("reason", reason);
			} else if (commId == 6) {
				reason = lookupDao.getAllChildrenByLookUpNameAndLang("Reason-ScaleRev-6th", 1);
				objectArgs.put("reason", reason);
			} else {
				reason = new ArrayList<CmnLookupMst>();
				objectArgs.put("reason", reason);
			}

			// ended by japen

			objectArgs.put("editMode", "Y");
			resultObject.setResultValue(objectArgs);
			logger.info("the record entered succesfully");
			Calendar endCalTime = Calendar.getInstance();
			logger.info("The End time for the Other Details' Service is:-" + endCalTime.getTimeInMillis());
		}

		catch (Exception e) {
			logger.error("Error is: " + e.getMessage());

			Map result = new HashMap();
			result.put("MESSAGECODE", 1007);
			resultObject.setResultValue(result);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");

		}

		return resultObject;
	}

	public ResultObject getOtherData(Map objectArgs) {
		logger.info("inside getOtherData of other detail service");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			OtherDetailDAOImpl otherDtlsDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());

			//// parameters for update paybill
			String updatePaybillFlg = "n";
			int paybillMonth = 0;
			int paybillYear = 0;
			//// parameters for update paybill

			Map loginMap = (Map) objectArgs.get("baseLoginMap");

			//
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long languageId = 1;
			// Modify By Urvin Shah
			// long locationId =
			// StringUtility.convertToLong(loginMap.get("locationId").toString());

			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null
					&& !cmnLocationMst.getLocationCode().trim().equals("")) ? cmnLocationMst.getLocationCode() : "";
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,
					serv.getSessionFactory());

			Map voToService = (Map) objectArgs.get("voToServiceMap");
			String employeeName = "";
			if ((voToService.get("Employee_srchNameText_EmpOtherSearch") != null
					&& !voToService.get("Employee_srchNameText_EmpOtherSearch").equals("")))
				employeeName = (String) voToService.get("Employee_srchNameText_EmpOtherSearch").toString();

			// added by Ankit Bhatt for merging screens
			String empAllRec = "";
			if (voToService.get("empAllRec") != null)
				empAllRec = voToService.get("empAllRec").toString();
			logger.info("empAllRec ion other Details service " + empAllRec);
			// ended by Ankit Bhatt

			StringBuffer data = new StringBuffer();

			String edit = "";
			long gradeId = 0;

			// by manoj for employee search
			String empIdStrLoan = (String) voToService.get("Employee_ID_EmpLoanSearch");
			String empIdStr = (String) voToService.get("Employee_ID_EmpOtherSearch");
			logger.info("the emp id from search employee " + empIdStr);
			// end by manoj for employee search

			if (voToService.get("edit") != null) {
				edit = (String) voToService.get("edit").toString();
			}
			logger.info("the value of edit is " + edit);
			if (!edit.equals(null) && edit.equalsIgnoreCase("Y")) {
				logger.info("I m in edit mode getOtherData");

				if (voToService.get("updatePaybillFlg") != null) {
					updatePaybillFlg = voToService.get("updatePaybillFlg").toString();
				}
				if (voToService.get("paybillMonth") != null) {
					paybillMonth = Integer.parseInt(voToService.get("paybillMonth").toString());
				}
				if (voToService.get("paybillYear") != null) {
					paybillYear = Integer.parseInt(voToService.get("paybillYear").toString());
				}

				logger.info("test update paybill parameters in other details service" + updatePaybillFlg + " "
						+ paybillMonth + " " + paybillYear);
				logger.info(voToService.get("otherId"));

				long sid = 0;
				sid = Long.parseLong(
						(voToService.get("otherId") != null && !voToService.get("otherId").toString().equals("")
								? voToService.get("otherId").toString()
								: "0"));
				long employeeId = 0;
				HrEisOtherDtls hrEisotherDtls = new HrEisOtherDtls();

				if (sid == 0) {
					employeeId = Long.parseLong(
							(voToService.get("empId").toString() != null && voToService.get("empId").toString() != "")
									? voToService.get("empId").toString()
									: "0");
					hrEisotherDtls = otherDtlsDAO.getOtherData(employeeId);
					logger.info("Employee Id if sid==0-:-" + employeeId);
				} else {
					hrEisotherDtls = otherDtlsDAO.read(sid);
					logger.info("Employee Id if sid is not zero:-" + employeeId);
				}
				// logger.info("The Other Employee Id
				// is:-"+hrEisotherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());
				// HrEisEmpMst empMstVO= (HrEisEmpMst)objectArgs.get("empMstVO");

				// added by ankit Bhatt for merging two screens - Other Dtls and Allow Mapping

				Map voToServiceMap = new HashMap();
				voToServiceMap.put("EmpAllowMpg", "Y");
				voToServiceMap.put("EmpId", String.valueOf(hrEisotherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId()));
				objectArgs.put("voToServiceMap", voToServiceMap);
				logger.info("Going to execute EmpAllowMap service from OtherDtls service");
				ResultObject allowResultObj = serv.executeService("empAllowMap", objectArgs);
				objectArgs = (Map) allowResultObj.getResultValue();
				logger.info("finish EmpAllowMap service from OtherDtls service");
				// ended by Ankit Bhatt.

				objectArgs.put("updatePaybillFlg", updatePaybillFlg);
				objectArgs.put("paybillMonth", paybillMonth);
				objectArgs.put("paybillYear", paybillYear);

				EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
				// EmpDAOImpl
				long userId = hrEisotherDtls.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
				/////////////////////////////////////////////////////////////
				long empId = hrEisotherDtls.getHrEisEmpMst().getEmpId();

				// added by Ankit Bhatt
				empPunishmentDAOImpl empPunishmentDAO = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,
						serv.getSessionFactory());
				boolean isPunished = empPunishmentDAO.isEmpPunished(empId);
				logger.info("Employee is Punished or not " + isPunished);

				objectArgs.put("isPunished", isPunished);
				// ended

				String gradeName = "";
				String postName = "";
				String desigName = "";
				String empName = "";
				List<OrgPostDetailsRlt> postDtlsList;

				EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
				EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
				// OrgEmpMst orgEmpMst = empDAOImpl.read(empId);
				getGradDesgMapDAO gradDesgMapDAO = new getGradDesgMapDAO(HrEisGdMpg.class, serv.getSessionFactory());
				HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
				GradDesgScaleMapDAO gradDesgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class,
						serv.getSessionFactory());
				EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
				HrEisEmpMst empMst = empinfodao.read(empId);
				OrgEmpMst orgEmpMst = empDAOImpl.read(empMst.getOrgEmpMst().getEmpId());
				postDtlsList = otherDtlsDAO.getPostDetailData(langId, orgEmpMst.getOrgUserMst().getUserId());
				if (langId != 1) {
					long LanguageID = 1;
					orgEmpMst = empInfoDAOImpl.getEmployee(orgEmpMst.getOrgUserMst(), langId);
					gradeName = orgEmpMst.getOrgGradeMst().getGradeName();
					postDtlsList = otherDtlsDAO.getPostDetailData(langId, orgEmpMst.getOrgUserMst().getUserId());
				}
				empName = orgEmpMst.getEmpFname() + " " + orgEmpMst.getEmpMname() + " " + orgEmpMst.getEmpLname();
				// added by Mrugesh for passing Emp Type in Edit Screen
				long empType = hrEisotherDtls.getHrEisEmpMst().getEmpType();
				// ended
				List<HrEisSgdMpg> hrEisSgdMpgList = new ArrayList();
				gradeName = orgEmpMst.getOrgGradeMst().getGradeName();
				gradeId = orgEmpMst.getOrgGradeMst().getGradeId();
				long gdmap = 0;
				if (postDtlsList.size() > 0) {
					logger.info("If PostDetails Size > 0:-" + postDtlsList.size());

					postName = postDtlsList.get(0).getPostName();
					desigName = postDtlsList.get(0).getOrgDesignationMst().getDsgnName();
					hrEisGdMpg = gradDesgMapDAO.getGDMapFromGD(orgEmpMst.getOrgGradeMst(),
							postDtlsList.get(0).getOrgDesignationMst(), cmnLocationMst);
					if (hrEisGdMpg.getGdMapId() != 0)
						gdmap = hrEisGdMpg.getGdMapId();
					hrEisSgdMpgList = gradDesgScaleMapDAO.getScalefromGD(hrEisGdMpg.getGdMapId(), langId);
					GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class, serv.getSessionFactory());
					DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,
							serv.getSessionFactory());
					String gradeElementCode = orgEmpMst.getOrgGradeMst().getGradeCode();
					String desigElementCode = postDtlsList.get(0).getOrgDesignationMst().getDsgnCode();
					List<OrgGradeMst> grade = gradeDao.getGradeName(gradeElementCode, langId);
					List<OrgDesignationMst> desig = desigMasterDAO.getDesigName(desigElementCode, langId);
					if (desig.size() > 0 && grade.size() > 0) {
						gradeName = grade.get(0).getGradeName();
						desigName = desig.get(0).getDsgnName();
					}
				}
				String gdmapid = "" + gdmap + "";

				postDtlsList = otherDtlsDAO.getPostDetailData(langId, userId);
				HrEisSgdMpg hrEisSgdMpg = hrEisotherDtls.getHrEisSgdMpg();

				for (Iterator iter = hrEisSgdMpgList.iterator(); iter.hasNext();) {
					logger.info("for hrEisSgdMpgList Size > 0:-" + hrEisSgdMpgList.size());
					String scaleDesc = "";
					HrEisSgdMpg hrEisDgdMpgObj = new HrEisSgdMpg();
					hrEisDgdMpgObj = (HrEisSgdMpg) iter.next();
					long scaleId = hrEisDgdMpgObj.getSgdMapId();
					long startAmt = hrEisDgdMpgObj.getHrEisScaleMst().getScaleStartAmt();
					long incrAmt = hrEisDgdMpgObj.getHrEisScaleMst().getScaleIncrAmt();
					long endAmt = hrEisDgdMpgObj.getHrEisScaleMst().getScaleEndAmt();
					long gradePay = hrEisDgdMpgObj.getHrEisScaleMst().getScaleGradePay();
					if (gradePay > 0)
						scaleDesc = String.valueOf(startAmt) + "-" + String.valueOf(endAmt) + "/"
								+ String.valueOf(gradePay);
					else
						scaleDesc = String.valueOf(startAmt) + "-" + String.valueOf(incrAmt) + "-"
								+ String.valueOf(endAmt);

					if (hrEisDgdMpgObj.getHrEisScaleMst().getScaleHigherEndAmt() != 0)
						scaleDesc += "-" + hrEisDgdMpgObj.getHrEisScaleMst().getScaleHigherIncrAmt() + "-"
								+ hrEisDgdMpgObj.getHrEisScaleMst().getScaleHigherEndAmt();

					logger.info("SGD Id:-" + scaleId);
					logger.info("Scale Description:-" + scaleDesc);
				}
				// added by Ankit-Mrugesh
				long scaleId = 0;
				if (hrEisSgdMpg != null) {
					logger.info("hrEisSgdMpg not null-");
					scaleId = hrEisotherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleId();
				}
				objectArgs.put("scaleId", scaleId);
				objectArgs.put("empName", empName);
				objectArgs.put("scaleList", hrEisSgdMpgList);
				objectArgs.put("gradeName", gradeName);
				objectArgs.put("postName", postName);
				objectArgs.put("desigName", desigName);
				objectArgs.put("actionList", hrEisotherDtls);
				objectArgs.put("otherList", hrEisotherDtls);
				objectArgs.put("empType", empType);
				objectArgs.put("gradeId", gradeId);

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);

				CmnLanguageMst cmnlanguageMst = new CmnLanguageMst();
				cmnlanguageMst.setLangId(langId);

				OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,
						serv.getSessionFactory());
				List quaterTypeList = otherdtlsDao.getQuaterType();
				logger.info("Quaters Types are :-" + quaterTypeList.size());
				objectArgs.put("quaterTypeList", quaterTypeList);

				CmnCityMstDAOImpl cityDao = new CmnCityMstDAOImpl(CmnCityMst.class, serv.getSessionFactory());
				List<CmnCityMst> citylist = otherdtlsDao.getAllcity(cmnlanguageMst);

				objectArgs.put("cityList", citylist);

				// for hrEmpTraMpg by manoj
				objectArgs.put("empId", empId);
				objectArgs.put("otherId", sid);

				ResultObject resultObj1 = serv.executeService("getHrEmpTraMpgServiceImpl", objectArgs);
				objectArgs = (Map) resultObj1.getResultValue();

				// end for hrEmpTraMpg by manoj

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				// added by Ankit Bhatt for Merging the Screens.
				if (empAllRec.equalsIgnoreCase("true") == true) {
					resultObject.setViewName("otherEditListempAllRec");
					objectArgs.put("empAllRec", "true");
				} else
				// ended by Ankit Bhatt
				{
					objectArgs.put("empAllRec", "false");
					resultObject.setViewName("otherEditList");
				}
				resultObject.setResultValue(objectArgs);
				logger.info("inside 1st");
			}
			// by manoj for employee search
			else if (empIdStr != null && !empIdStr.equals("")) {
				logger.info("empIdStr!=null && !empIdStr.equals()");
				long empId = Long.parseLong(empIdStr);
				// added by Ankit Bhatt for checking emp is Punished or not
				empPunishmentDAOImpl empPunishmentDAO = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,
						serv.getSessionFactory());
				boolean isPunished = empPunishmentDAO.isEmpPunished(empId);
				logger.info("Employee is Punished or not " + isPunished);

				// ended by Ankit Bhatt.
				List actionList = new ArrayList();
				List otherList = otherDtlsDAO.getAllOtherData(locationCode, langId, empId);
				// Instead of making objects of empmaster ,othedetails,dfesignation strins have
				// been used as VO as it is only for display to avoid whole of objects
				Object[] row;
				if (otherList != null && otherList.size() > 0) {
					for (int k = 0; k < otherList.size(); k++) {

						PayrollCustomVO payrollCustomVO = new PayrollCustomVO();
						row = (Object[]) otherList.get(k);
						payrollCustomVO.setOtherId(Long.parseLong(row[0] != null ? row[0].toString() : "0"));
						payrollCustomVO.setEmpName(row[1] != null ? row[1].toString() : "");
						payrollCustomVO.setGradeName(row[2] != null ? row[2].toString() : "");
						payrollCustomVO.setDsgnName(row[3] != null ? row[3].toString() : "");
						payrollCustomVO.setCurrentBasic(Long.parseLong(row[4] != null ? row[4].toString() : "0"));
						actionList.add(payrollCustomVO);
					}
				}

				// Map map = new HashMap();
				objectArgs.put("actionList", actionList);

				// added by Ankit Bhatt.
				objectArgs.put("isPunished", isPunished);
				// ended by Ankit Bhatt

				resultObject.setResultCode(ErrorConstants.SUCCESS);

				// added by Ankit Bhatt for Merging the Screens.
				if (empAllRec.equalsIgnoreCase("true") == true) {
					resultObject.setViewName("OtherViewListEmpAllRec");
					objectArgs.put("empAllRec", "true");
					objectArgs.put("empId", empId);

				} else {
					objectArgs.put("empId", "0");
					objectArgs.put("empAllRec", "false");
					resultObject.setViewName("ViewListOther");
				}
				// ended by Ankit Bhatt

				resultObject.setResultValue(objectArgs);
				logger.info("inside 2nd");

			}
			// end by manoj for employee search
			else {
				logger.info("Inside Else");
				List actionList = new ArrayList();
				// logger.info("the Location Id is:-"+locationId);
				List otherList = otherDtlsDAO.getAllOtherData(locationCode, langId, 0);
				// Instead of making objects of empmaster ,othedetails,dfesignation strins have
				// been used as VO as it is only for display to avoid whole of objects
				Object[] row;
				Date startDate;
				Date endDate;
				Date currDate = new Date();
				Calendar calCurr = Calendar.getInstance();
				calCurr.set(currDate.getYear() + 1900, currDate.getMonth() + 1, currDate.getDate());

				calCurr.set(Calendar.DATE, calCurr.getActualMaximum(Calendar.DATE));
				logger.info("The Date Isllllllll:------" + calCurr.get(Calendar.DATE) + "/"
						+ calCurr.get(Calendar.MONTH) + "/" + calCurr.get(Calendar.YEAR));
				currDate = (Date) calCurr.getTime();
				currDate.setDate(currDate.getDate() - 1);
				currDate.setMonth(currDate.getMonth() - 1);
				logger.info("The Date Isllllllll:------" + currDate);

				if (otherList != null && otherList.size() > 0) {
					for (int k = 0; k < otherList.size(); k++) {
						String tempBuffer = "";
						PayrollCustomVO payrollCustomVO = new PayrollCustomVO();
						row = (Object[]) otherList.get(k);
						startDate = (Date) row[5];
						endDate = (Date) row[6];

						if (endDate == null || endDate.after(currDate)) {
							logger.info("Before execution");
							payrollCustomVO.setOtherId(Long.parseLong(row[0] != null ? row[0].toString() : "0"));
							payrollCustomVO.setEmpName(row[1] != null ? row[1].toString() : "");
							payrollCustomVO.setGradeName(row[2] != null ? row[2].toString() : "");
							payrollCustomVO.setDsgnName(row[3] != null ? row[3].toString() : "");

							String otherbasic = "";
							String basicdtl = "";
							if (Long.parseLong(row[4].toString()) != 0) {
								logger.info("the value of the row4 is ::" + Long.parseLong(row[4].toString()));
								NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
								tempBuffer = currencyFormatter
										.format(Long.parseLong(row[4].toString().replace("$", "")));
								logger.info("the value of the tempbuffer is ::" + tempBuffer);
								otherbasic = tempBuffer.replace(".00", "");
								basicdtl = otherbasic.replace("$", "");
								logger.info("the value of the basicdtl is ::" + basicdtl);
								logger.info("the value of the otherbasic is ::" + otherbasic);

							}
							payrollCustomVO.setCurrencycurrentBasic(basicdtl);
							actionList.add(payrollCustomVO);
						}
					}
				}

				logger.info("actionList.size in other dtls " + actionList.size());
				objectArgs.put("actionList", actionList);

				// added by Ankit Bhatt for merging screens
				objectArgs.put("empId", "0");
				objectArgs.put("empAllRec", "false");

				// ended by Ankit Bhatt
				resultObject.setResultValue(objectArgs);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ViewListOther");
				objectArgs.put("ViewFlag", "True");

				logger.info("inside last");

			}
			objectArgs.put("empName", employeeName);
			resultObject.setResultValue(objectArgs);
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");

		}

		return resultObject;
	}

	public ResultObject fillCombo(Map objectArgs) {
		logger.info("inside fillCombo ");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			// for logindetailmap
			// HttpServletRequest request = (HttpServletRequest)
			// objectArgs.get("requestObj");
			// HttpSession session=request.getSession();
			// end here for logindetailmap

			// Map loginMap =(Map)session.getAttribute("loginMap");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");

			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long languageId = 1;
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,
					serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(languageId);

			/*
			 * HttpServletRequest request = (HttpServletRequest)
			 * objectArgs.get("requestObj"); HttpSession session = request.getSession();
			 * LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
			 * long langId=loginDetails.getLangId(); Map loginMap
			 * =(Map)session.getAttribute("loginMap"); long
			 * lid=Long.parseLong(loginMap.get("langId").toString()); CmnLanguageMstDaoImpl
			 * cmnLanguageMstDaoImpl=new
			 * CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			 * CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(lid);
			 */

			OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			List emplist = otherdtlsDao.getAllEmpId();
			List empIdList = new ArrayList();
			/*
			 * for(int i=0;i<emplist.size();i++) { empIdList.add(emplist.get(i).getEmpId());
			 * }
			 */

			// This will fetch employee name
			HrEisEmpMst empMstVO = (HrEisEmpMst) objectArgs.get("empMstVO");
			EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			long userId = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
			// List userList =empinfoDao.getUserListByLogin(langId,userId);
			/*
			 * List <OrgUserMst> orgUserList = new ArrayList(); for (Iterator iter =
			 * userList.iterator(); iter.hasNext();) { Object[] row = (Object[])iter.next();
			 * OrgUserMst userMst = new OrgUserMst(); String id=row[0].toString();
			 * userMst.setUserId(Long.parseLong(id)); orgUserList.add(userMst); }
			 */
			List<HrEisEmpMst> empList = otherdtlsDao.getAllOtherEmpData(userId);

			objectArgs.put("empList", empList);
			CmnLanguageMst cmnlanguageMst = new CmnLanguageMst();
			cmnlanguageMst.setLangId(langId);
			// This will fetch department name
			/*
			 * HrEisDeptMst deptMstVO= (HrEisDeptMst)objectArgs.get("deptMstVO");
			 * AddDeptDAOImpl deptDao = new
			 * AddDeptDAOImpl(HrEisDeptMst.class,serv.getSessionFactory()); List
			 * <HrEisDeptMst> deptList = deptDao.getAllDeptMasterData(cmnlanguageMst);
			 * 
			 * List<HrEisDeptMst> deptlist = new ArrayList(); GradeMasterDAO gradeMasterDAO
			 * = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory()); for (int
			 * i=0;i<deptList.size();i++) { HrEisDeptMst hrEisDeptMst=new HrEisDeptMst();
			 * hrEisDeptMst=(HrEisDeptMst)deptList.get(i); HrEisDeptMst hrEisDeptMstgu=new
			 * HrEisDeptMst(); hrEisDeptMstgu.setDeptName(hrEisDeptMst.getDeptName());
			 * boolean flag=false; if(langId!=1) { long LangId=1; long ElementCode=
			 * hrEisDeptMst.getElementCode();
			 * 
			 * List<HrEisDeptMst> dept = deptDao.getDeptName(ElementCode, LangId);
			 * if(dept.size()>0) { hrEisDeptMstgu.setDeptId(dept.get(0).getDeptId()); flag =
			 * true; } } else { hrEisDeptMstgu.setDeptId(hrEisDeptMst.getDeptId()); flag =
			 * true; } if(flag) deptlist.add(hrEisDeptMstgu);
			 * 
			 * }
			 * 
			 * objectArgs.put("deptList", deptlist);
			 */

			// This will fetch grade name
			OrgGradeMst gradeMstVO = (OrgGradeMst) objectArgs.get("gradeMstVO");
			GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class, serv.getSessionFactory());
			List<OrgGradeMst> graderesultSet = gradeDao.getAllGradeMasterData(cmnlanguageMst);

			objectArgs.put("gradeList", graderesultSet);

			// this will fetch city names

			CmnCityMstDAOImpl cityDao = new CmnCityMstDAOImpl(CmnCityMst.class, serv.getSessionFactory());
			List<CmnCityMst> citylist = otherdtlsDao.getAllcity(cmnlanguageMst);

			objectArgs.put("cityList", citylist);

			// this will fetch quater list
			List quaterTypeList = otherdtlsDao.getQuaterType();
			logger.info("Quaters Types are :-" + quaterTypeList.size());
			objectArgs.put("quaterTypeList", quaterTypeList);
			// objectArgs.put("gradeId", gradeId);

			resultObject.setResultCode(ErrorConstants.SUCCESS);

			// added by Ankit Bhatt for merging the Screens
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

			// added by ravysh for merged screen
			String MergedScreen = "";
			if (request.getParameter("MergedScreen") != null)
				MergedScreen = request.getParameter("MergedScreen");
			objectArgs.put("MergedScreen", MergedScreen);
			// changes by ravysh ends

			String empname = "";
			if ((request.getParameter("empName") != null && !request.getParameter("empName").equals("")))
				empname = request.getParameter("empName").toString();

			String empAllRec = "";
			if (request.getParameter("empAllRec") != null)
				empAllRec = request.getParameter("empAllRec");
			long empId = 0;
			if (request.getParameter("empId") != null)
				empId = Long.valueOf(request.getParameter("empId").toString());
			if (!empAllRec.equals("") && empAllRec.equalsIgnoreCase("true") == true) {
				EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
				OrgEmpMst orgEmpMst = empDAO.read(empId);
				objectArgs.put("empName", orgEmpMst.getEmpPrefix() + " " + orgEmpMst.getEmpFname() + " "
						+ orgEmpMst.getEmpMname() + " " + orgEmpMst.getEmpLname());
				resultObject.setViewName("newOtherMasterEmpAllRec");
				objectArgs.put("empId", empId);
				objectArgs.put("empAllRec", "true");
			} else {
				objectArgs.put("empAllRec", "false");
				// ended by Ankit Bhatt
				resultObject.setViewName("newOtherMaster");
			}
			objectArgs.put("empName", empname);
			resultObject.setResultValue(objectArgs);

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");

		}
		return resultObject;
	}

	public ResultObject checkEmpName(Map objectArgs) {

		logger.info("Inside Check");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		Map voToService = (Map) objectArgs.get("voToServiceMap");
		StringBuffer empNameBf = new StringBuffer();
		try {

			String strEmpId = voToService.get("newEmpId").toString();
			logger.info("The Value is:-" + strEmpId);

			long empId = 0;
			if (!strEmpId.equals(null) && !strEmpId.equals(""))
				empId = Long.parseLong(strEmpId);
			logger.info("The Employee Id is:-" + empId);
			OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			// HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
			String check = "";
			long id = 0;
			EmpInfoDAOImpl hrEisEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			List<HrEisEmpMst> eisEmpDtlsList = hrEisEmpDao.getListByColumnAndValue("orgEmpMst.empId", empId);

			if (eisEmpDtlsList == null || eisEmpDtlsList.size() == 0) {
				logger.info("Element size =" + eisEmpDtlsList.size());
				check = "false";
				empNameBf.append("<empNameMapping>");
				empNameBf.append("<availability>").append("-1").append("</availability>");
				empNameBf.append("</empNameMapping>");
			} else {
				empId = eisEmpDtlsList.get(0).getOrgEmpMst().getEmpId();
				List<HrEisOtherDtls> eisOtherDtlsList = otherDtlsDao.getEmpAvailable(empId);
				if (eisOtherDtlsList == null || eisOtherDtlsList.size() == 0) {
					logger.info("eisOtherDtlsList size =" + eisOtherDtlsList.size());
					check = "false";
					empNameBf.append("<empNameMapping>");
					empNameBf.append("<availability>").append(id).append("</availability>");
					empNameBf.append("</empNameMapping>");
				} else {

					check = "true";
					id = eisOtherDtlsList.get(0).getOtherId();
					empNameBf.append("<empNameMapping>");
					empNameBf.append("<availability>").append(id).append("</availability>");
					empNameBf.append("</empNameMapping>");
				}
			}

			Map map = new HashMap();
			String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();

			logger.info(" the string buffer is :" + empNameMapping);
			map.put("ajaxKey", empNameMapping);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(map);
			resultObject.setViewName("ajaxData");
			logger.info(" SERVICE COMPLETE :");
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}

	public ResultObject getOtherDetailsByEmp(Map objectArgs) {

		logger.info("Get Employee Ot");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		Map voToService = (Map) objectArgs.get("voToServiceMap");
		StringBuffer empNameBf = new StringBuffer();
		StringBuffer scaleBF = new StringBuffer();
		try {

			String strEmpId = voToService.get("newEmpId").toString();
			logger.info("The Value is:-" + strEmpId);

			long empId = 0;
			String gradeName = "";
			String postName = "";
			String desigName = "";
			List<OrgPostDetailsRlt> postDtlsList;
			if (!strEmpId.equals(null) && !strEmpId.equals(""))
				empId = Long.parseLong(strEmpId);
			logger.info("The Employee Id is:-" + empId);
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");

			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			OtherDetailDAOImpl otherDtlsDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());

			EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			// changed by manoj for home integration of E - profile

			HrEisEmpMst empMst = new HrEisEmpMst();// empinfodao.read(empId);

			List empMstList = empinfodao.getHrEmpFromOrgEmpId(empId);// getListByColumnAndValue("orgEmpMst.empId",
																		// empId);

			if (empMstList != null && empMstList.size() > 0) {
				empMst = (HrEisEmpMst) empMstList.get(0);
			}
			// end of changed by manoj for home integration of E - profile
			long emptypeid = 0;
			emptypeid = empMst.getEmpType();
			logger.info("The emptypeid is------>" + emptypeid);
			empNameBf.append("<empTypeId>");
			empNameBf.append("<empType>").append(emptypeid).append("</empType>");
			empNameBf.append("</empTypeId>");

			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());

			OrgEmpMst orgEmpMst = empDAOImpl.read(empMst.getOrgEmpMst().getEmpId());
			getGradDesgMapDAO gradDesgMapDAO = new getGradDesgMapDAO(HrEisGdMpg.class, serv.getSessionFactory());
			HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
			GradDesgScaleMapDAO gradDesgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class,
					serv.getSessionFactory());

			postDtlsList = otherDtlsDAO.getPostDetailData(langId, orgEmpMst.getOrgUserMst().getUserId());
			if (langId != 1) {
				long languageId = 1;
				orgEmpMst = empInfoDAOImpl.getEmployee(orgEmpMst.getOrgUserMst(), langId);
				gradeName = orgEmpMst.getOrgGradeMst().getGradeName();
				postDtlsList = otherDtlsDAO.getPostDetailData(langId, orgEmpMst.getOrgUserMst().getUserId());
			}
			long gradeId = orgEmpMst.getOrgGradeMst().getGradeId();
			List<HrEisSgdMpg> hrEisSgdMpgList = new ArrayList();
			gradeName = orgEmpMst.getOrgGradeMst().getGradeName();
			long gdmap = 0;
			GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class, serv.getSessionFactory());
			DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class, serv.getSessionFactory());
			if (postDtlsList.size() > 0) {
				postName = postDtlsList.get(0).getPostName();
				desigName = postDtlsList.get(0).getOrgDesignationMst().getDsgnName();

				hrEisGdMpg = gradDesgMapDAO.getGDMapFromGD(orgEmpMst.getOrgGradeMst(),
						postDtlsList.get(0).getOrgDesignationMst(), cmnLocationMst);
				if (hrEisGdMpg.getGdMapId() != 0)
					gdmap = hrEisGdMpg.getGdMapId();
				hrEisSgdMpgList = gradDesgScaleMapDAO.getScalefromGD(hrEisGdMpg.getGdMapId(), langId);
				String gradeElementCode = orgEmpMst.getOrgGradeMst().getGradeCode();
				String desigElementCode = postDtlsList.get(0).getOrgDesignationMst().getDsgnCode();
				List<OrgGradeMst> grade = gradeDao.getGradeName(gradeElementCode, langId);
				List<OrgDesignationMst> desig = desigMasterDAO.getDesigName(desigElementCode, langId);
				if (desig.size() > 0 && grade.size() > 0) {
					gradeName = grade.get(0).getGradeName();
					desigName = desig.get(0).getDsgnName();
				}
			}
			String gdmapid = "" + gdmap + "";
			empNameBf.append("<otherDetailsByEmp-mapping>");
			empNameBf.append("<gradeName>").append("<![CDATA[").append(gradeName).append("]]>").append("</gradeName>");
			empNameBf.append("<designationName>").append("<![CDATA[").append(desigName).append("]]>")
					.append("</designationName>");
			empNameBf.append("<postName>").append(postName).append("</postName>");
			empNameBf.append("<gradeId>").append("<![CDATA[").append(gradeId).append("]]>").append("</gradeId>");
			// empNameBf.append("<GDMap>").append(gdmapid).append("</GDMap>");
			empNameBf.append("</otherDetailsByEmp-mapping>");
			long sgdMapId = 0;
			long startAmt = 0;
			long incrAmt = 0;
			long endAmt = 0;
			long gradePay = 0;
			String scaleName = "";
			for (Iterator iter = hrEisSgdMpgList.iterator(); iter.hasNext();) {
				HrEisSgdMpg hrEisSgdMpg = new HrEisSgdMpg();
				hrEisSgdMpg = (HrEisSgdMpg) iter.next();
				sgdMapId = hrEisSgdMpg.getSgdMapId();
				startAmt = hrEisSgdMpg.getHrEisScaleMst().getScaleStartAmt();
				incrAmt = hrEisSgdMpg.getHrEisScaleMst().getScaleIncrAmt();
				endAmt = hrEisSgdMpg.getHrEisScaleMst().getScaleEndAmt();
				gradePay = hrEisSgdMpg.getHrEisScaleMst().getScaleGradePay();
				if (gradePay != 0)
					scaleName = startAmt + "-" + endAmt + "(" + gradePay + ")";
				else
					scaleName = startAmt + "-" + incrAmt + "-" + endAmt;

				if (hrEisSgdMpg.getHrEisScaleMst().getScaleHigherEndAmt() != 0)
					scaleName += "-" + hrEisSgdMpg.getHrEisScaleMst().getScaleHigherIncrAmt() + "-"
							+ hrEisSgdMpg.getHrEisScaleMst().getScaleHigherEndAmt();

				empNameBf.append("<Scale-mapping>");
				empNameBf.append("<Scales-Id>").append(sgdMapId).append("</Scales-Id>");
				empNameBf.append("<Scales-name>").append("<![CDATA[").append(scaleName).append("]]>")
						.append("</Scales-name>");
				empNameBf.append("</Scale-mapping>");
			}

			String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();
			logger.info(" the string buffer is :" + empNameMapping);
			objectArgs.put("ajaxKey", empNameMapping);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");
			logger.info(" SERVICE COMPLETE :");
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}

	/*
	 * public ResultObject fetchQuaterType(Map objectArgs){
	 * logger.info("inside fillCombo ");
	 * 
	 * ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	 * HttpServletRequest request = (HttpServletRequest)
	 * objectArgs.get("requestObj"); ServiceLocator serv =
	 * (ServiceLocator)objectArgs.get("serviceLocator"); StringBuffer
	 * quaterTypeBf=new StringBuffer(); HttpSession session = request.getSession();
	 * LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
	 * long langId=loginDetails.getLangId(); try{
	 * 
	 * String strEmpId = request.getParameter("newEmpId").toString();
	 * logger.info("The Value is:-"+strEmpId);
	 * 
	 * long empId=0; if(!strEmpId.equals("")) empId=Long.parseLong(strEmpId);
	 * logger.info("The Employee Id is:-"+empId); OtherDetailDAOImpl otherDtlsDao =
	 * new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
	 * //HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls(); String check=""; List
	 * quaterTypeList=otherDtlsDao.getQuaterType(langId);
	 * logger.info("Element size ="+quaterTypeList.size());
	 * logger.info("Element size is:" + quaterTypeList.size());
	 * if(eisOtherDtlsList.size()==0){ check="false";
	 * empNameBf.append("<empNameMapping>");
	 * empNameBf.append("<availability>").append(check).append("</availability>");
	 * empNameBf.append("</empNameMapping>"); }else{ check="true";
	 * empNameBf.append("<empNameMapping>");
	 * empNameBf.append("<availability>").append(check).append("</availability>");
	 * empNameBf.append("</empNameMapping>"); }
	 * 
	 * Map map = new HashMap(); String empNameMapping = new
	 * AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();
	 * 
	 * logger.info(" the string buffer is :"+empNameMapping); map.put("ajaxKey",
	 * empNameMapping); resultObject.setResultCode(ErrorConstants.SUCCESS);
	 * resultObject.setResultValue(map); resultObject.setViewName("ajaxData");
	 * logger.info(" SERVICE COMPLETE :"); logger.info("Element size is:" +
	 * eisOtherDtlsList.size()); } catch(Exception e){ logger.error("Error is: "+
	 * e.getMessage()); } return resultObject; }
	 */

	public ResultObject editOtherDtlsOnPayIncrPayFixation(Map objectArgs) {

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");

		int counter = Integer.parseInt(objectArgs.get("counter").toString());
		long langId = Long.parseLong(loginMap.get("langId").toString());
		List lstUser = new ArrayList();
		List lstRvsdPayAmt = new ArrayList();
		List lstHrEisEmpMst = new ArrayList();
		List lstNextIncrDate = new ArrayList();
		List lstRevPayScale = new ArrayList();
		HrEisOtherDtls hrEisOtherDtls;
		OrgEmpMst orgEmpMst;
		OrgUserMst orgUserMst;
		EmpDAO empDao = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
		EmpInfoDAOImpl empInfoDAO = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
		HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
		OtherDetailDAO otherDetailDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
		EmpAllwMapService empAllwMapService = new EmpAllwMapService();
		long gdMapId = 0; // Grade - Designation Mapping
		long revisedScaleId = 0; // Scale Id
		GradDesgScaleMapDAO desgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class, serv.getSessionFactory());
		List lstSGDMap = new ArrayList();
		List lstNotUpdated = new ArrayList(); // List of Employees which are not updated.
		HrEisSgdMpg eisSgdMpg = new HrEisSgdMpg(); // ScaleGradeDesignation Id.
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");
		// userPostDao.g
		try {
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMstUpdatedBy = orgUserMstDaoImpl.read(userId);

			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			if (counter > 0) {
				lstUser = (List) objectArgs.get("lstUser");
				lstRvsdPayAmt = (List) objectArgs.get("lstRvsdPayAmt");
				lstNextIncrDate = (List) objectArgs.get("lstNextIncrDate");
				lstRevPayScale = (List) objectArgs.get("lstRevisedPayScale");
			}

			for (int i = 0; i < counter; i++) {
				// (OrgUserMst)lstUser.get(i);
				orgEmpMst = empDao.getEmpFromUser((OrgUserMst) lstUser.get(i), langId);
				// hrEisEmpMst = empInfoDAO.get
				lstHrEisEmpMst = empInfoDAO.getListByColumnAndValue("orgEmpMst", orgEmpMst);
				if (lstHrEisEmpMst != null || lstHrEisEmpMst.size() > 0) {
					hrEisEmpMst = (HrEisEmpMst) lstHrEisEmpMst.get(0);
					hrEisOtherDtls = otherDetailDAO.getOtherData(hrEisEmpMst.getOrgEmpMst().getEmpId());
					logger.info("The Employee Id is:-" + hrEisOtherDtls.getHrEisEmpMst());
					gdMapId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg().getGdMapId();
					revisedScaleId = 0;
					if (lstRevPayScale.get(i) != null) {
						revisedScaleId = Long.parseLong(lstRevPayScale.get(i).toString());
					}
					if (revisedScaleId != 0) {
						lstSGDMap = desgScaleMapDAO.getScaleGradeDesgMasterData(gdMapId, revisedScaleId,
								cmnLocationMst.getLocId());
						if (lstSGDMap != null && !lstSGDMap.isEmpty()) {
							// eisSgdMpg = (HrEisSgdMpg) lstSGDMap.get(0);
							hrEisOtherDtls.setHrEisSgdMpg((HrEisSgdMpg) lstSGDMap.get(0));
						} else {
							lstNotUpdated.add(orgEmpMst);
							logger.info("The List size of the Not Updated Records is:-" + lstNotUpdated.size());
							continue;
						}
					}
					hrEisOtherDtls.setotherCurrentBasic(Long.parseLong(lstRvsdPayAmt.get(i).toString()));
					hrEisOtherDtls.setIncrementDate((Date) lstNextIncrDate.get(i));
					hrEisOtherDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
					hrEisOtherDtls.setOrgUserMstByUpdatedBy(orgUserMstUpdatedBy);
					hrEisOtherDtls.setUpdatedDate(new Date());

					otherDetailDAO.update(hrEisOtherDtls);
					logger.info("Other Details Record get Updated:-");
					objectArgs.put("empOtherVO", hrEisOtherDtls);
					objectArgs.put("editMode", "Y");
					resultObject = empAllwMapService.insertEmpAllowData(objectArgs);
					logger.info("Employee Allowance Mapping get Updated");
				}
			}
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
			Map result = new HashMap();
			result.put("MESSAGECODE", 1007);
			resultObject.setResultValue(result);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");

		}
		return resultObject;
	}

	public ResultObject changeScaleForEmp(Map objectArgs) throws Exception {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		Map loginMap = (Map) objectArgs.get("baseLoginMap");

		boolean isTransfer = false;

		// to map a post with a particular bill

		if (objectArgs.get("NewBillGroup") != null && Long.valueOf(objectArgs.get("NewBillGroup").toString()) != -1
				&& !objectArgs.get("NewBillGroup").toString().equals("") && objectArgs.get("NewPost") != null) {
			PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,
					serv.getSessionFactory());
			HrPayPsrPostMpg payPsrPostMpg = psrPostMpgDAOImpl
					.getHrPayPostPsrMpg(Long.valueOf(objectArgs.get("NewPost").toString()));
			payPsrPostMpg.setBillNo(Long.valueOf(objectArgs.get("NewBillGroup").toString()));
			psrPostMpgDAOImpl.update(payPsrPostMpg);

		}
		Date WEFDate = null;
		if (objectArgs.get("WEFDate") != null) {
			WEFDate = (Date) objectArgs.get("WEFDate");
		} else {
			WEFDate = new Date();
		}
		// to transfer an employee
		OtherDetailServiceHelper detailServiceHelper = new OtherDetailServiceHelper(serv);
		if (objectArgs.get("NewPost") != null && objectArgs.get("orgEmpId") != null) {

			GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(OrgEmpMst.class);
			genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
			OrgEmpMst orgEmpMstloggedId = (OrgEmpMst) genericDaoHibernateImpl
					.read(Long.valueOf(objectArgs.get("orgEmpId").toString()));

			long userPostRltPk = IDGenerateDelegate.getNextId("ORG_USERPOST_RLT", objectArgs);
			logger.info("destination post id is " + objectArgs.get("NewPost").toString() + "source empId is "
					+ objectArgs.get("orgEmpId").toString());
			logger.info("Primary key is " + userPostRltPk);
			detailServiceHelper.tranferEmployee(Long.valueOf(objectArgs.get("NewPost").toString()),
					Long.valueOf(objectArgs.get("orgEmpId").toString()), userPostRltPk, isTransfer, WEFDate,
					orgEmpMstloggedId);
		}
		logger.info("in change scale service ...");

		CmnlookupMstDAOImpl cmnDao = new CmnlookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
		long empId = 0, dsgnId = 0, scaleId = 0, groupId = 0, reasonForScaleChange = 0, commissionId = 0;
		String orderId = "", remarks = "";

		String basic = null;
		GenericDaoHibernateImpl genDao = null;
		OrgDesignationMst orgDesignationMst = null;
		HrEisScaleMst hrEisScaleMst = null;
		OrgGradeMst orgGradeMst = null;
		CmnLookupMst cmnLookupMst = null;

		long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMstUpdatedBy = orgUserMstDaoImpl.read(userId);

		/// Code to restrict duplicate entry of GD SGD as for ddo location code
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");

		PayBillDAOImpl billDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

		String ddoCode = "";
		OrgDdoMst ddoMst = new OrgDdoMst();
		if (objectArgs.get("ddoCode") != null) {
			ddoCode = objectArgs.get("ddoCode").toString();
			ddoMst = billDAOImpl.getOrgDdoMstFromDDOCode(ddoCode);
			genDao = new GenericDaoHibernateImpl(CmnLocationMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			cmnLocationMst = (CmnLocationMst) genDao.read(Long.parseLong(ddoMst.getLocationCode()));
			logger.info("DDO Code is--->" + ddoCode);

		}

		if (objectArgs.get("empId") != null && !objectArgs.get("empId").toString().equals("")) {
			empId = StringUtility.convertToLong(objectArgs.get("empId").toString());
		}

		if (objectArgs.get("dsgnId") != null && !objectArgs.get("dsgnId").toString().equals("")) {
			dsgnId = StringUtility.convertToLong(objectArgs.get("dsgnId").toString());
			genDao = new GenericDaoHibernateImpl(OrgDesignationMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			orgDesignationMst = (OrgDesignationMst) genDao.read(dsgnId);
			logger.info("dsgnId is--->" + dsgnId);
		}
		if (objectArgs.get("scaleId") != null && !objectArgs.get("scaleId").toString().equals("")) {
			scaleId = StringUtility.convertToLong(objectArgs.get("scaleId").toString());
			genDao = new GenericDaoHibernateImpl(HrEisScaleMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			hrEisScaleMst = (HrEisScaleMst) genDao.read(scaleId);
			logger.info("scaleId is--->" + scaleId);
		}

		long parentLocId = cmnLocationMst.getParentLocId();
		AdminOrgPostDtlDaoImpl adminOrgPostDtlDaoImpl = new AdminOrgPostDtlDaoImpl(OrgPostMst.class,
				serv.getSessionFactory());

		List<MstPayrollDesignationMst> lst = adminOrgPostDtlDaoImpl.getMstDcpsDsgnObject(parentLocId, dsgnId);
		MstPayrollDesignationMst mst = new MstPayrollDesignationMst();
		logger.info("lst size is--->" + lst.size());
		if (lst != null && lst.size() > 0)
			mst = lst.get(0);
		long cadreTypeId = mst.getCadreTypeId(); // cadre must come

		long grpName = Long.parseLong(new Long(cadreTypeId).toString());
		logger.info("grpName  is--->" + grpName);
		CmnLookupMst loonkupGrd = cmnDao.read(grpName);

		genDao = new GenericDaoHibernateImpl(OrgGradeMst.class);
		genDao.setSessionFactory(serv.getSessionFactory());
		// logger.info(loonkupGrd.getLookupShortName().toString());
		groupId = Long.parseLong(loonkupGrd.getLookupShortName().toString());

		logger.info("groupId  is--->" + groupId);

		GradDesgScaleMapDAO sgdDao = new GradDesgScaleMapDAO(HrEisSgdMpg.class, serv.getSessionFactory());

		orgGradeMst = (OrgGradeMst) genDao.read(groupId);

		/*
		 * if(objectArgs.get("groupId")!=null &&
		 * !objectArgs.get("groupId").toString().equals("")) { groupId =
		 * StringUtility.convertToLong(objectArgs.get("groupId").toString());
		 * CmnLookupMst cmnLookupMst2 = cmnDao.read(groupId); groupId =
		 * StringUtility.convertToLong(cmnLookupMst2.getLookupShortName()); genDao = new
		 * GenericDaoHibernateImpl(OrgGradeMst.class);
		 * genDao.setSessionFactory(serv.getSessionFactory());
		 * 
		 * 
		 * }
		 */

		if (objectArgs.get("orderId") != null && !objectArgs.get("orderId").toString().equals("")) {
			orderId = objectArgs.get("orderId").toString();
		}
		if (objectArgs.get("remarks") != null && !objectArgs.get("remarks").toString().equals("")) {
			remarks = objectArgs.get("remarks").toString();
		}
		if (objectArgs.get("reasonForScaleChange") != null
				&& !objectArgs.get("reasonForScaleChange").toString().equals("")) {
			reasonForScaleChange = StringUtility.convertToLong(objectArgs.get("reasonForScaleChange").toString());

			cmnLookupMst = cmnDao.read(reasonForScaleChange);
		}
		if (objectArgs.get("commissionId") != null && !objectArgs.get("commissionId").toString().equals("")) {
			commissionId = StringUtility.convertToLong(objectArgs.get("commissionId").toString());
		}

		if (objectArgs.get("basic") != null) {
			basic = (String) objectArgs.get("basic");
		}

		logger.info("emp id is " + empId);

		genDao = new GenericDaoHibernateImpl(MstEmp.class);
		genDao.setSessionFactory(serv.getSessionFactory());
		MstEmp dcpsEmp = (MstEmp) genDao.read(empId);
		empId = dcpsEmp.getOrgEmpMstId();
		if (dcpsEmp != null) {
			genDao = new GenericDaoHibernateImpl(HrEisOtherDtls.class);
			genDao.setSessionFactory(serv.getSessionFactory());

			// temp code
			/*
			 * long hreisempId = dcpsEmp.getOrgEmpMstId(); genDao = new
			 * GenericDaoHibernateImpl(HrEisEmpMst.class);
			 * genDao.setSessionFactory(serv.getSessionFactory()); empId =
			 * ((HrEisEmpMst)genDao.read(hreisempId)).getOrgEmpMst().getEmpId();
			 */

			// code ends here

			// HrEisOtherDtls otherDtlsVO =
			// (HrEisOtherDtls)genDao.getListByColumnAndValue("hrEisEmpMst.orgEmpMst.empId",
			// dcpsEmp.getOrgEmpMstId()).get(0);
			logger.info("passing data is " + empId);
			OtherDetailDAOImpl otherDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			HrEisOtherDtls otherDtlsVO = otherDAO.getOtherData(empId);

			//// internally managing gd ..sgd mapping ...

			GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class, serv.getSessionFactory());
			sgdDao = new GradDesgScaleMapDAO(HrEisSgdMpg.class, serv.getSessionFactory());
			HrEisGdMpg gdMpg = null;
			HrEisSgdMpg sgdMpg = null;
			List<HrEisGdMpg> gdList = gradeDao.getDuplicateData(groupId, dsgnId, cmnLocationMst.getLocId());
			IdGenerator idGen = new IdGenerator();
			if (gdList != null && gdList.size() > 0) {
				gdMpg = gdList.get(0);
				long gdId = gdMpg.getGdMapId();

				List<HrEisSgdMpg> sgdMpglist = sgdDao.getScaleGradeDesgMasterData(gdId, scaleId,
						cmnLocationMst.getLocId());
				if (sgdMpglist != null && sgdMpglist.size() > 0) {
					sgdMpg = sgdMpglist.get(0);

				} else {
					// insert in sgd mapping
					sgdMpg = new HrEisSgdMpg();
					long sgdId = idGen.PKGenerator("HR_EIS_SGD_MPG", objectArgs);
					// long sgdId = IDGenerateDelegate.getNextId("HR_EIS_SGD_MPG", objectArgs);
					logger.info("sgdId::::::::::" + sgdId);
					sgdMpg.setSgdMapId(sgdId);
					sgdMpg.setCmnDatabaseMst(otherDtlsVO.getCmnDatabaseMst());
					// sgdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
					sgdMpg.setCmnLocationMst(cmnLocationMst);
					sgdMpg.setCreatedDate(new java.util.Date());
					sgdMpg.setHrEisGdMpg(gdMpg);
					sgdMpg.setHrEisScaleMst(hrEisScaleMst);
					sgdMpg.setOrgPostMstByCreatedByPost(otherDtlsVO.getOrgPostMstByCreatedByPost());
					sgdMpg.setOrgUserMstByCreatedBy(otherDtlsVO.getOrgUserMstByCreatedBy());
					sgdDao.create(sgdMpg);
				}

			} else {
				// insert gdmpg
				gdMpg = new HrEisGdMpg();
				long gdId = idGen.PKGenerator("HR_EIS_GD_MPG", objectArgs);
				// long gdId = IDGenerateDelegate.getNextId("HR_EIS_GD_MPG", objectArgs);
				logger.info("gdId::::::::::" + gdId);
				gdMpg.setCmnDatabaseMst(otherDtlsVO.getCmnDatabaseMst());
				// gdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
				gdMpg.setCmnLocationMst(cmnLocationMst);
				gdMpg.setCreatedDate(new java.util.Date());
				gdMpg.setGdMapId(gdId);
				gdMpg.setOrgDesignationMst(orgDesignationMst);
				gdMpg.setOrgGradeMst(orgGradeMst);
				gdMpg.setOrgPostMstByCreatedByPost(otherDtlsVO.getOrgPostMstByCreatedByPost());
				gdMpg.setOrgUserMstByCreatedBy(otherDtlsVO.getOrgUserMstByCreatedBy());
				genDao = new GenericDaoHibernateImpl(HrEisGdMpg.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				genDao.create(gdMpg);

				// insert in sgd mapping

				sgdMpg = new HrEisSgdMpg();
				long sgdId = idGen.PKGenerator("HR_EIS_SGD_MPG", objectArgs);
				// long sgdId = IDGenerateDelegate.getNextId("HR_EIS_SGD_MPG", objectArgs);
				logger.info("sgdId:::::::::::::::::" + sgdId);
				sgdMpg.setSgdMapId(sgdId);
				sgdMpg.setCmnDatabaseMst(otherDtlsVO.getCmnDatabaseMst());
				// sgdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
				sgdMpg.setCmnLocationMst(cmnLocationMst);
				sgdMpg.setCreatedDate(new java.util.Date());
				sgdMpg.setHrEisGdMpg(gdMpg);
				sgdMpg.setHrEisScaleMst(hrEisScaleMst);
				sgdMpg.setOrgPostMstByCreatedByPost(otherDtlsVO.getOrgPostMstByCreatedByPost());
				sgdMpg.setOrgUserMstByCreatedBy(otherDtlsVO.getOrgUserMstByCreatedBy());
				sgdDao.create(sgdMpg);

			}

			// Code to Restrict Duplicate Entry of GD SGD Ends Here
			//// ends
			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			PayBillDAOImpl pbDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

			// added by manish
			logger.info("Post Id before checking FromDDO  is " + postId);
			if (objectArgs.get("FromDDO").toString().equals("NO"))
				postId = pbDao.getDDOPostIdForDDOAsst(postId);
			logger.info("Post Id after checking FromDDO  is " + postId);
			// ended by manish
			OrgDdoMst mstDdo = (OrgDdoMst) pbDao.getDDOCodeByLoggedInPost(postId).get(0);
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMstCreatedBy = orgPostMstDaoImpl.read(postId);

			/*
			 * OrgUserMstDaoImpl orgUserMstDaoImpl=new
			 * OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); OrgUserMst
			 * orgUserMst
			 * =orgUserMstDaoImpl.read(otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().
			 * getOrgUserMst().getUserId());
			 */
			// genDao= new GenericDaoHibernateImpl(OrgUserpostRlt.class);
			// genDao.setSessionFactory(serv.getSessionFactory());
			// List userPostList = genDao.getListByColumnAndValue(new
			// String[]{"orgUserMst.userId","activateFlag" }, new
			// Long[]{otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId(),1L});
			UserPostDAO userDaoImpl = new UserPostDAO(UserPostCustomVO.class, serv.getSessionFactory());
			logger.info("other dtls Vo is " + otherDtlsVO);
			logger.info("other id is " + otherDtlsVO.getOtherId());
			logger.info(" otherDtlsVO.getHrEisEmpMst() " + otherDtlsVO.getHrEisEmpMst());
			logger.info("  otherDtlsVO.getHrEisEmpMst().getOrgEmpMst() " + otherDtlsVO.getHrEisEmpMst().getOrgEmpMst());
			logger.info("  otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst()  "
					+ otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst());
			logger.info("  otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId() "
					+ otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId());
			OrgPostMst orgPostMst = userDaoImpl
					.getOrgPostFromUserId(otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId());

			logger.info("Fetched Post Id :" + orgPostMst.getPostId());

			IdGenerator idgen = new IdGenerator();

			/*
			 * Long pkRemarks = idgen.PKGenerator("HR_PAY_REMARKS_MST", objectArgs);
			 * HrPayRemarksMst hrPayRemarksMst = new HrPayRemarksMst();
			 * hrPayRemarksMst.setRemarkId(pkRemarks);
			 * hrPayRemarksMst.setRemarksDate(WEFDate);
			 * hrPayRemarksMst.setOrgDdoMst(mstDdo);
			 * hrPayRemarksMst.setOrgPostMstByCreatedByPost(orgPostMstCreatedBy);
			 * hrPayRemarksMst.setOrgUserMstByCreatedBy(orgUserMstUpdatedBy);
			 * hrPayRemarksMst.setHrEisEmpMst(otherDtlsVO.getHrEisEmpMst());
			 * hrPayRemarksMst.setOrgPostMst(orgPostMst);
			 * hrPayRemarksMst.setRemarks(remarks); genDao = new
			 * GenericDaoHibernateImpl(HrPayRemarksMst.class);
			 * genDao.setSessionFactory(serv.getSessionFactory());
			 * genDao.create(hrPayRemarksMst);
			 */

			Long Pk = idgen.PKGenerator("HR_EIS_SCALE_MPG", objectArgs);
			// Long Pk=IDGenerateDelegate.getNextId("HR_EIS_SCALE_MPG", objectArgs);
			logger.info("Pk:::::::::::::" + Pk);
			HrEisScaleMpg scaleMst = new HrEisScaleMpg();
			scaleMst.setId(Pk);
			scaleMst.setCmnLookupMst(cmnLookupMst);
			scaleMst.setHrEisEmpMst(otherDtlsVO.getHrEisEmpMst());
			scaleMst.setHrEisSgdMpg(sgdMpg);
			scaleMst.setOrder(orderId);
			// mst.setRemarks(hrPayRemarksMst);
			// mst.setHrPayRemarksMst(hrPayRemarksMst);
			scaleMst.setWefDate((Date) (objectArgs.get("orderDate")));
			genDao = new GenericDaoHibernateImpl(HrEisScaleMpg.class);
			genDao.setSessionFactory(serv.getSessionFactory());

			genDao.create(scaleMst);

			/// other dtls Vo update
			genDao = new GenericDaoHibernateImpl(HrEisOtherDtls.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			otherDtlsVO.setHrEisSgdMpg(sgdMpg);
			if (!basic.equals(null)) {
				Double basicd = Double.parseDouble(basic);
				otherDtlsVO.setotherCurrentBasic(basicd.longValue());
				
			}
			genDao.update(otherDtlsVO);

			logger.info("exiting service");

		}

		return resultObject;
	}

	public ResultObject changePersonalDtlsInPayroll(Map objectArgs) throws Exception {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		logger.info("Inside changePersonalDtlsInPayroll----* ");
		OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
		CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
		DcpsCommonDAOImpl dcpsCommonDAOImpl = new DcpsCommonDAOImpl(MstEmp.class, serv.getSessionFactory());
		GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(OrgEmpMst.class);
		genDAO.setSessionFactory(serv.getSessionFactory());

		HstDcpsPersonalChanges hstDcpsPersonalChanges = (HstDcpsPersonalChanges) objectArgs.get("ChangedPersonalVO");
		HstDcpsOtherChanges hstDcpsOtherChanges = (HstDcpsOtherChanges) objectArgs.get("ChangedOtherVO");

		Map changedDtlsMap = new HashMap();
		boolean isDAOCallable = false;
		long lLongEisEmpId = 0;
		long lLongDcpsEmpId = 0;
		long lLongOrgEmpId = 0;

		if (hstDcpsPersonalChanges != null) {
			char changedGender = 'A';
			String physicallyChallenged = "";
			String changedSalutation = "";
			Date changedDOJ = null;
			Date changedDOB = null;
			String empFname = "";
			String empMname = "";
			String empLname = "";
			String lStrArrempFullname[] = null;

			logger.info("If hstDcpsPersonalChanges is not null ");
			lLongDcpsEmpId = hstDcpsPersonalChanges.getDcpsEmpId();
			logger.info("DCPS Employee Id" + lLongDcpsEmpId);
			lLongEisEmpId = otherDtlsDao.getEisEmpIdUsingDcpsId(lLongDcpsEmpId);
			lLongOrgEmpId = dcpsCommonDAOImpl.getEmpVOForEmpId(lLongDcpsEmpId).getOrgEmpMstId();

			if (lLongEisEmpId != 0) {
				OrgEmpMst orgEmpMst = (OrgEmpMst) genDAO.read(lLongOrgEmpId);
				changedDtlsMap.put("eisEmpId", lLongEisEmpId);
				changedDtlsMap.put("orgEmpId", lLongOrgEmpId);
				changedDtlsMap.put("userId", orgEmpMst.getOrgUserMst().getUserId());

				logger.info("Org Employee Id" + lLongOrgEmpId);
				logger.info("Eis Employee Id" + lLongEisEmpId);
				if (hstDcpsPersonalChanges.getGender() != null) {
					changedGender = hstDcpsPersonalChanges.getGender();
					changedDtlsMap.put("changedGender", changedGender);
					isDAOCallable = true;
				}
				if (hstDcpsPersonalChanges.getPhychallanged() != null && hstDcpsPersonalChanges.getPhychallanged() != ""
						&& hstDcpsPersonalChanges.getPhychallanged().length() > 0) {
					physicallyChallenged = hstDcpsPersonalChanges.getPhychallanged().trim();
					changedDtlsMap.put("physicallyChallenged", physicallyChallenged);
					isDAOCallable = true;
				}
				if (hstDcpsPersonalChanges.getSalutation() != null && hstDcpsPersonalChanges.getSalutation() != ""
						&& hstDcpsPersonalChanges.getSalutation().length() > 0) {
					long salId = Long.parseLong(hstDcpsPersonalChanges.getSalutation().trim());
					changedSalutation = cmnDao.read(salId).getLookupName().trim();
					changedDtlsMap.put("changedSalutation", changedSalutation);
					isDAOCallable = true;
				}
				if (hstDcpsPersonalChanges.getDoj() != null) {
					changedDOJ = hstDcpsPersonalChanges.getDoj();
					changedDtlsMap.put("changedDOJ", changedDOJ);
					isDAOCallable = true;
				}
				if (hstDcpsPersonalChanges.getDob() != null) {
					changedDOB = hstDcpsPersonalChanges.getDob();
					changedDtlsMap.put("changedDOB", changedDOB);
					isDAOCallable = true;
				}
				if (hstDcpsPersonalChanges.getPANNo() != null && hstDcpsPersonalChanges.getPANNo() != ""
						&& hstDcpsPersonalChanges.getPANNo().length() > 0) {
					String changedPAN = hstDcpsPersonalChanges.getPANNo().toString();
					changedDtlsMap.put("changedPAN", changedPAN);
					isDAOCallable = true;
				}
				if (hstDcpsPersonalChanges.getName() != null && hstDcpsPersonalChanges.getName() != ""
						&& hstDcpsPersonalChanges.getName().length() > 0) {
					lStrArrempFullname = hstDcpsPersonalChanges.getName().split(" ");
					empLname = lStrArrempFullname[lStrArrempFullname.length - 1];
					empMname = lStrArrempFullname[lStrArrempFullname.length - 2];
					for (int lNameIndex = 0; lNameIndex <= lStrArrempFullname.length - 3; lNameIndex++) {
						empFname += lStrArrempFullname[lNameIndex];
					}
					changedDtlsMap.put("empMname", empMname);
					changedDtlsMap.put("empFname", empFname);
					changedDtlsMap.put("empLname", empLname);
					isDAOCallable = true;
				}
				if (isDAOCallable)
					otherDtlsDao.changePersonalDtls(changedDtlsMap);
			}
		}

		logger.info("Successfully Completed Personal Changes And Going For Other Changes");
		isDAOCallable = false;
		if (hstDcpsOtherChanges != null) {
			String changedPFSeries = "";
			String changedPFAcctNo = "";
			String changedBankActNo = "";
			String changedBankBranch = "";

			logger.info("If hstDcpsPersonalChanges is not null ");
			lLongDcpsEmpId = hstDcpsOtherChanges.getDcpsEmpId();
			logger.info("DCPS Employee Id" + lLongDcpsEmpId);
			lLongEisEmpId = otherDtlsDao.getEisEmpIdUsingDcpsId(lLongDcpsEmpId);
			lLongOrgEmpId = dcpsCommonDAOImpl.getEmpVOForEmpId(lLongDcpsEmpId).getOrgEmpMstId();

			OrgEmpMst orgEmpMst = (OrgEmpMst) genDAO.read(lLongOrgEmpId);
			changedDtlsMap.put("eisEmpId", lLongEisEmpId);
			changedDtlsMap.put("orgEmpId", lLongOrgEmpId);
			changedDtlsMap.put("userId", orgEmpMst.getOrgUserMst().getUserId());

			logger.info("Org Employee Id" + lLongOrgEmpId);
			logger.info("Eis Employee Id" + lLongEisEmpId);

			if (lLongEisEmpId != 0) {
				logger.info("Inside changedPFSeries");
				if (hstDcpsOtherChanges.getPfSeriesDesc() != null && hstDcpsOtherChanges.getPfSeriesDesc() != ""
						&& hstDcpsOtherChanges.getPfSeriesDesc().length() > 0) {
					logger.info("Inside ");
					changedPFSeries = hstDcpsOtherChanges.getPfSeriesDesc().trim();
					changedDtlsMap.put("PFSeries", changedPFSeries);
					isDAOCallable = true;
				}
				if (hstDcpsOtherChanges.getPfAcNo() != null && hstDcpsOtherChanges.getPfAcNo() != ""
						&& hstDcpsOtherChanges.getPfAcNo().length() > 0) {
					logger.info("Inside changedPFAcctNo");
					changedPFAcctNo = hstDcpsOtherChanges.getPfAcNo().trim();
					changedDtlsMap.put("PFActNo", changedPFAcctNo);
					isDAOCallable = true;
				}
				if (hstDcpsOtherChanges.getBankAccountNo() != null && hstDcpsOtherChanges.getBankAccountNo() != ""
						&& hstDcpsOtherChanges.getBankAccountNo().length() > 0) {
					logger.info("Inside changedBankActNo");
					changedBankActNo = hstDcpsOtherChanges.getBankAccountNo().trim();
					changedDtlsMap.put("BankActNo", changedBankActNo);
					isDAOCallable = true;
				}
				if (hstDcpsOtherChanges.getBranchName() != null && hstDcpsOtherChanges.getBranchName() != ""
						&& hstDcpsOtherChanges.getBranchName().length() > 0) {
					logger.info("Inside changedBankBranch");
					changedBankBranch = hstDcpsOtherChanges.getBranchName().trim();
					changedDtlsMap.put("BankBranch", changedBankBranch);
					isDAOCallable = true;
				}
				if (isDAOCallable)
					otherDtlsDao.changeOtherDtls(changedDtlsMap);
			}
		}

		logger.info("Successfully Exiting from changePersonalDtlsInPayroll");
		return resultObject;
	}// end method

}// end class