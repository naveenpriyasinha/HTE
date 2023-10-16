package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnCountryMstDAO;
import com.tcs.sgv.common.dao.CmnCountryMstDAOImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDao;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAO;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
//import com.tcs.sgv.ess.gpf.valueobject.HrGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmpInfoService extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());
	int msg = 0;

	public ResultObject insertEmpData(Map objectArgs) {
		logger.info("IN TEST SRVC insertEmpData");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		try {

			HrEisEmpMst empMst = (HrEisEmpMst) objectArgs.get("empMst");
			OrgEmpMst orgEmpMst = (OrgEmpMst) objectArgs.get("orgEmpMst");
			
			//added by ravysh
			String FromBasicDtlsNew=objectArgs.get("FromBasicDtlsNew")!=null?(String)objectArgs.get("FromBasicDtlsNew"):"";
			long otherId = objectArgs.get("otherId")!=null?Long.parseLong(objectArgs.get("otherId").toString()):0;
			objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
            objectArgs.put("otherId", otherId);
			//OrgGradeMst orgGradeMst=(OrgGradeMst)objectArgs.get("orgGradeMst");
			// OrgEmpMst orgEmpMst = null;
			HrEisEmpMst hrEisEmpMst = null;
			Date sysdate = new Date();
			EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,
					serv.getSessionFactory());
			EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv
					.getSessionFactory());

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			long userId = StringUtility.convertToLong(loginDetailsMap.get(
					"userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(
					OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId")
					.toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(
					CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			OrgEmpMst orgempMst = orgEmpDAOImpl.getEmployeeVO(userId);
			
			/*
			 * long
			 * locId=StringUtility.convertToLong(loginDetailsMap.get("locationId"
			 * ).toString()); CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new
			 * CmnLocationMstDaoImpl
			 * (CmnLocationMst.class,serv.getSessionFactory()); CmnLocationMst
			 * cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
			 */

			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap
					.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null && !cmnLocationMst
					.getLocationCode().trim().equals("")) ? cmnLocationMst
					.getLocationCode() : "";

			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			long languageId = 1;

			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl
					.read(languageId);

			long postId = StringUtility.convertToLong(loginDetailsMap.get(
					"primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(
					OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			OrgGradeMst orgGradeMst=null;
			long gradeId=0;
			if(!objectArgs.get("gradeId").toString().equals(""))
			{
				 gradeId=Long.parseLong(objectArgs.get("gradeId").toString());
			logger.info(" the value of the gradeId is ::"+gradeId);
			OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
			 orgGradeMst =orgGradeMstDaoImpl.read(gradeId);
			}
			Date sysDate = new Date();
			// long locationId =
			// StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			// for update purpose
			// empMst = empinfodao.read(new Long(2));

			// Added By Urvin shah

			CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			CmnCountryMstDAOImpl countryDao = new CmnCountryMstDAOImpl(
					CmnCountryMst.class, serv.getSessionFactory());

			CmnLookupMst cmnLookupMstByCastId = new CmnLookupMst();
			CmnLookupMst cmnLookupMstBySubCastId = new CmnLookupMst();
			CmnLookupMst cmnLookupMstByCategory = new CmnLookupMst();
			//CmnLookupMst cmnLookupMstByGradeId= new CmnLookupMst();
			CmnLookupMst cmnLangMstMotherTounge = null;
			CmnCountryMst cmnCountryMstNationality = null;
			CmnLookupMst cmnLookupMstByReligionId = new CmnLookupMst();

			if (!objectArgs.get("txtCasteIdInt").toString().equals("")) {
				cmnLookupMstByCastId = cmnLookupDao.read(Long
						.parseLong(objectArgs.get("txtCasteIdInt").toString()));
			}

			if (!objectArgs.get("txtSubCasteStr").toString().equals("")) {
				cmnLookupMstBySubCastId = cmnLookupDao
						.read(Long.parseLong(objectArgs.get("txtSubCasteStr")
								.toString()));
			}
			if (!objectArgs.get("cmbCategoryStr").toString().equals("")) {
				cmnLookupMstByCategory = cmnLookupDao
						.read(Long.parseLong(objectArgs.get("cmbCategoryStr")
								.toString()));
			}
			/*if (!objectArgs.get("gradeId").toString().equals("")) {
				cmnLookupMstByGradeId = cmnLookupDao
						.read(Long.parseLong(objectArgs.get("gradeId")
								.toString()));
			}*/
			/*long lgradeId=0;
			OrgGradeMst lGradeMst=new OrgGradeMst();
			if (!objectArgs.get("gradeId").toString().equals(""))
			{
			 lgradeId=(Long)objectArgs.get("gradeId");
			OrgGradeMstDaoImpl lDaoImpl=new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
			lGradeMst=lDaoImpl.read(lgradeId);
			}*/
			logger.info("txtMotherTngStr here in service "
					+ objectArgs.get("txtMotherTngStr").toString());
			logger.info("::::::::::1:::::::");
			if (!objectArgs.get("txtMotherTngStr").toString().equals("")
					|| !objectArgs.get("txtMotherTngStr").toString().equals(
							"-1")) {
				logger.info("::::::::::2:::::::");
				cmnLangMstMotherTounge = cmnLookupDao
						.read(Long.parseLong(objectArgs.get("txtMotherTngStr")
								.toString()));
				logger.info("::::::::::3:::::::");

			}
			logger.info("::::::::::4:::::::");
			logger.info("txtNationalityStr here in service 1"
					+ objectArgs.get("txtNationalityStr").toString());

			if (!objectArgs.get("txtNationalityStr").toString().equals("")
					|| !objectArgs.get("txtNationalityStr").toString().equals(
							"-1")) {
				logger.info("::::::::::5:::::::");
				cmnCountryMstNationality = countryDao.read(Long
						.parseLong(objectArgs.get("txtNationalityStr")
								.toString()));
				logger.info("::::::::::6:::::::");
				// logger.info("cmnCountryMstNationality desc ehre is here in service "+cmnCountryMstNationality.getCountryName());
				logger.info("::::::::::7:::::::");
			}

			if (!objectArgs.get("txtReligionIdInt").toString().equals("")) {
				cmnLookupMstByReligionId = cmnLookupDao.read(Long
						.parseLong(objectArgs.get("txtReligionIdInt")
								.toString()));
			}
			/*
			 * if(objectArgs.get("marritalStatus").toString().equals("-1")){
			 * CmnLookupMst cmnLookupMstByMarritalStatus =
			 * cmnLookupDao.read((Long)objectArgs.get("marritalStatus")); }
			 */

			// CmnLookupMst cmnLookupMstByBirthPlace =
			// cmnLookupDao.read((Long)objectArgs.get("txtBirthPlcStr"));
			// CmnLookupMst cmnLookupMstByNativePlace =
			// cmnLookupDao.read((Long)objectArgs.get("txtNativePlcStr"));
			// CmnLookupMst cmnLookupMstByMotherTounge =
			// cmnLookupDao.read((Long)objectArgs.get("txtMotherTngStr"));

			// End.
			// Added By Urvin shah.
			GenericDaoHibernateImpl generateDao = new GenericDaoHibernateImpl(
					HrEisProofDtl.class);
			generateDao.setSessionFactory(serv.getSessionFactory());
			HrEisProofDtl hrEisProofDtl = new HrEisProofDtl();
			/* #################################################### */
			// Added By Varun For GPF A/C Date:-31-07-2008
			GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(
					HrPayGpfBalanceDtls.class);
			genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
			HrPayGpfBalanceDtls hrGpfBalanceDtls = new HrPayGpfBalanceDtls();
			long userID = 0;
			// Ended By Varun For GPF A/C Date:-31-07-2008
			/* #################################################### */

			if (objectArgs.get("hrEisProofDtl") != null)
				hrEisProofDtl = (HrEisProofDtl) objectArgs.get("hrEisProofDtl");
			IdGenerator IdGen = new IdGenerator();
			// End.
			long empId = Long.parseLong(objectArgs.get("empId").toString());

			if (objectArgs.get("edit") != null
					&& objectArgs.get("edit").toString().equalsIgnoreCase("Y")) {
				logger
						.info(" insertEmpData inside if service-------edit mode---------------");

				hrEisEmpMst = empinfodao.read(empId);

				/* #################################################### */
				// Added By Varun For GPF A/C Date:-31-07-2008
				userID = hrEisEmpMst.getOrgEmpMst().getOrgUserMst().getUserId();
				logger.info("the value of the userID is ::"+userID);
				hrGpfBalanceDtls = (HrPayGpfBalanceDtls) genericDaoHibernateImpl
						.read(userID);
//				logger.info("hrGpfBalanceDtls size" + hrGpfBalanceDtls);
				if (hrGpfBalanceDtls != null) {
					logger.info("GPF NUMBER IS:->"+ hrGpfBalanceDtls.getGpfAccNo()+" with org gpf number "+orgEmpMst.getEmpGPFnumber()+" end");
					if(orgEmpMst.getEmpGPFnumber()!=null && !orgEmpMst.getEmpGPFnumber().equalsIgnoreCase(""))
						{
							logger.info("inside if");
							hrGpfBalanceDtls.setGpfAccNo(orgEmpMst.getEmpGPFnumber());
						}
					else
						{
						logger.info("inside else");
							hrGpfBalanceDtls.setGpfAccNo(" ");
						}
					logger.info("after GPF NUMBER IS:->"+ hrGpfBalanceDtls.getGpfAccNo());
					hrGpfBalanceDtls.setOrgGradeMst(orgGradeMst);
					 
					genericDaoHibernateImpl.update(hrGpfBalanceDtls);
				} else {
					// logger.info("GPF A/C No is:->"+orgEmpMst1.getEmpGPFnumber().trim());
					HrPayGpfBalanceDtls gpfBalanceDtls = new HrPayGpfBalanceDtls();
					gpfBalanceDtls.setGpfAccNo(orgEmpMst.getEmpGPFnumber());
					gpfBalanceDtls.setUserId(userID);
					gpfBalanceDtls.setOrgGradeMst(orgGradeMst);
					// logger.info("Before Insertion");
					
					//hrGpfBalanceDtls.setOrgGradeMst(lGradeMst);
					//hrGpfBalanceDtls.setOrgGradeMst(orgempMst.getOrgGradeMst());
					hrGpfBalanceDtls.setOrgGradeMst(orgGradeMst);
					hrGpfBalanceDtls.setTrnCounter(1);
					//hrGpfBalanceDtls.setOrgGradeMst(cmnLookupMstByGradeId);
					hrGpfBalanceDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrGpfBalanceDtls.setCmnLocationMst(cmnLocationMst);
					hrGpfBalanceDtls.setCreatedDate(sysDate);
					hrGpfBalanceDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					hrGpfBalanceDtls.setOrgUserMstByCreatedByUser(orgUserMst);
					//hrGpfBalanceDtls.setOrgGradeMst(orgGradeMst);
					genericDaoHibernateImpl.create(gpfBalanceDtls);
				}
				// Ended By Varun For GPF A/C Date:-31-07-2008
				/* #################################################### */

				OrgEmpMst objOrgEmpMst = orgEmpDAOImpl.read(hrEisEmpMst
						.getOrgEmpMst().getEmpId());
				// For org_emp_mst.
				// logger.info("ObjOrgEmpMst:-"+orgEmpMst);
				// objOrgEmpMst.setEmpFname(orgEmpMst.getEmpFname());
				// objOrgEmpMst.setEmpMname(orgEmpMst.getEmpMname());
				// objOrgEmpMst.setEmpLname(orgEmpMst.getEmpLname());
				objOrgEmpMst.setEmpDob(orgEmpMst.getEmpDob());
				objOrgEmpMst.setEmpDoj(orgEmpMst.getEmpDoj());
				objOrgEmpMst.setEmpGPFnumber(orgEmpMst.getEmpGPFnumber());
				// objOrgEmpMst.setStartDate(orgEmpMst.getStartDate());
				// objOrgEmpMst.setEmpPrefix(orgEmpMst.getEmpPrefix());
				objOrgEmpMst.setEmpSrvcExp(orgEmpMst.getEmpSrvcExp());
				objOrgEmpMst.setEmpSrvcFlag(orgEmpMst.getEmpSrvcFlag());
				// objOrgEmpMst.setEndDate(orgEmpMst.getEndDate());
				// objOrgEmpMst.setActivateFlag(orgEmpMst.getActivateFlag());
				// objOrgEmpMst.setStartDate(sysDate);

				objOrgEmpMst.setOrgUserMstByUpdatedBy(orgUserMst);
				objOrgEmpMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				objOrgEmpMst.setUpdatedDate(sysDate);

				orgEmpDAOImpl.update(objOrgEmpMst);
				logger.info("The Record is updated in to the org_Emp_mst");

				// For Hr_Eis_Emp_Mst

				orgEmpMst = orgEmpDAOImpl.read(hrEisEmpMst.getOrgEmpMst()
						.getEmpId());
				hrEisEmpMst.setEmail(empMst.getEmail());
				// hrEisEmpMst.setEmpSalutationId(empMst.getEmpSalutationId());
				hrEisEmpMst.setOrgEmpMst(orgEmpMst);
				hrEisEmpMst.setEmpGender(empMst.getEmpGender());

				// hrEisEmpMst.setEmpLeaveDt(empMst.getEmpLeaveDt());
				// hrEisEmpMst.setEmpStatusId(empMst.getEmpStatusId());
				hrEisEmpMst.setEmpApptLtrDt(empMst.getEmpApptLtrDt());
				hrEisEmpMst.setEmpConfDueDt(empMst.getEmpConfDueDt());
				// hrEisEmpMst.setEmpSalDisbMd(empMst.getEmpSalDisbMd());
				hrEisEmpMst.setEmpHobby(empMst.getEmpHobby());
				// hrEisEmpMst.setEmpRecruitSrc(empMst.getEmpRecruitSrc());
				hrEisEmpMst.setEmpType(empMst.getEmpType());
				// hrEisEmpMst.setEmpSubCaste(empMst.getEmpSubCaste());
				// hrEisEmpMst.setCmnLookupMstByEmpSubCasteId(cmnLookupMstBySubCastId);
				// hrEisEmpMst.setEmpCategory(empMst.getEmpCategory());

				/*
				 * hrEisEmpMst.setEmpBirthPlace(empMst.getEmpBirthPlace());
				 * hrEisEmpMst.setEmpNativePlace(empMst.getEmpNativePlace());
				 */

				if (cmnCountryMstNationality != null) {
					logger.info("::::::::::21:::::::");
					hrEisEmpMst
							.setCmnCountryMstByEmpNationality(cmnCountryMstNationality);
					logger.info("::::::::::22:::::::");
				}/*
				 * done by golelogger.info(
				 * " cmnCountryMstNationality here in srvc after settin "
				 * +cmnCountryMstNationality.getCountryName());
				 */
				if (cmnLangMstMotherTounge != null) {
					logger.info("::::::::::8:::::::");
					hrEisEmpMst.setCmnLanguageMst(cmnLangMstMotherTounge);
					logger.info("::::::::::9:::::::");
				}
				// logger.info(" cmnLangMstMotherTounge here in srvc after settin "+cmnLangMstMotherTounge.getLookupDesc());

				// hrEisEmpMst.setCmnLanguageMst(cmnLangMstMotherTounge);
				// hrEisEmpMst.setEmpReligionId(empMst.getEmpReligionId());
				// hrEisEmpMst.setEmpCasteId(empMst.getEmpCasteId());
				// hrEisEmpMst.setCmnLookupMstByEmpCasteId(cmnLookupMstByCastId);
				// hrEisEmpMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrEisEmpMst.setOrgPostMst(orgPostMst);
				hrEisEmpMst.setOrgUserMstByUpdatedBy(orgUserMst);
				hrEisEmpMst.setUpdatedDate(sysdate);
				hrEisEmpMst.setContactNo(empMst.getContactNo());
				if (langId != 1) {

					String cmnLookupName = cmnLookupDao.read(
							empMst.getEmpRecruitSrc()).getLookupName();
					CmnLookupMst cmnLookupMst = cmnLookupDao
							.getLookUpVOByLookUpNameAndLang(cmnLookupName,
									langId);
					hrEisEmpMst.setEmpRecruitSrc(cmnLookupMst.getLookupId());

					// cmnLookupName=cmnLookupDao.read(Long.parseLong(empMst.getCmnLookupMstByEmpReligionId().)).getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							empMst.getCmnLookupMstByEmpReligionId()
									.getLookupId(), 1);
					// hrEisEmpMst.setEmpReligionId(String.valueOf(cmnLookupMst.getLookupId()));
					hrEisEmpMst.setCmnLookupMstByEmpReligionId(cmnLookupMst);

					cmnLookupName = cmnLookupDao.read(
							Long.parseLong(empMst.getEmpSalDisbMd()))
							.getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, 1);
					hrEisEmpMst.setEmpSalDisbMd(String.valueOf(cmnLookupMst
							.getLookupId()));

					cmnLookupName = cmnLookupDao.read((empMst.getEmpType()))
							.getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, 1);
					hrEisEmpMst.setEmpType((cmnLookupMst.getLookupId()));

					// cmnLookupName=cmnLookupDao.read((empMst.getEmpCategory())).getLookupName();
					if (empMst.getCmnLookupMstByEmpCategoryId() != null) {
						cmnLookupMst = new CmnLookupMst();
						cmnLookupMst = cmnLookupDao
								.getLookUpVOByLookUpIDAndLang(empMst
										.getCmnLookupMstByEmpCategoryId()
										.getLookupId(), 1);
						hrEisEmpMst
								.setCmnLookupMstByEmpCategoryId(cmnLookupMst);
					}
					cmnLookupName = cmnLookupDao.read(
							Long.parseLong((empMst.getEmpStatusId())))
							.getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, 1);
					hrEisEmpMst.setEmpStatusId(String.valueOf((cmnLookupMst
							.getLookupId())));
				} else {
					hrEisEmpMst.setEmpRecruitSrc(empMst.getEmpRecruitSrc());

					hrEisEmpMst.setEmpSalDisbMd(empMst.getEmpSalDisbMd());
					hrEisEmpMst.setEmpType(empMst.getEmpType());
					hrEisEmpMst.setEmpStatusId(empMst.getEmpStatusId());
					if (cmnLookupMstByCategory != null) {
						hrEisEmpMst
								.setCmnLookupMstByEmpCategoryId(cmnLookupMstByCategory);
					}
					if (cmnLookupMstByReligionId != null) {
						hrEisEmpMst
								.setCmnLookupMstByEmpReligionId(cmnLookupMstByReligionId);
					}

					if (cmnLookupMstByCastId != null) {
						hrEisEmpMst
								.setCmnLookupMstByEmpCasteId(cmnLookupMstByCastId);
					}
					if (cmnLookupMstBySubCastId != null) {
						hrEisEmpMst
								.setCmnLookupMstByEmpSubCasteId(cmnLookupMstBySubCastId);
					}
					if (cmnLangMstMotherTounge != null) {
						logger.info("::::::::::10:::::::");
						hrEisEmpMst.setCmnLanguageMst(cmnLangMstMotherTounge);// removed
																				// by
																				// AG
						logger.info("::::::::::11:::::::");
					}
				}
				objectArgs.put("msg", "2");
				// orgEmpDAOImpl.update(orgEmpMst);

				empinfodao.update(hrEisEmpMst);
				// Added By Urvin Shah For Updating the PAN No.
				HrEisProofDtl eisProofDtl = new HrEisProofDtl();
				List lstProof = generateDao.getListByColumnAndValue(
						"orgPostMstByUserId", orgEmpMst.getOrgUserMst());
				
				if (lstProof != null && lstProof.size() > 0
						&& lstProof.get(0) != null) {
					
					eisProofDtl = (HrEisProofDtl) lstProof.get(0);
				}

				if (eisProofDtl != null
						&& eisProofDtl.getOrgPostMstByUserId() != null) {
					
					if (hrEisProofDtl.getProofNum().equals("")
							|| hrEisProofDtl.getProofNum() == null)
						eisProofDtl.setProofNum(" ");
					else
						eisProofDtl.setProofNum(hrEisProofDtl.getProofNum()
								.trim());
					eisProofDtl.setUpdatedDate(sysDate);
					eisProofDtl.setOrgUserMstByUpdatedBy(orgUserMst);
					eisProofDtl.setOrgUserMstByUpdatedByPost(orgPostMst);
					generateDao.update(eisProofDtl);
				} else {
					// Temp. Hard-Code for the PAN=300166.
					
					CmnLookupMst proofLookupId = new CmnLookupMst();
					long proofId = 300166;
					long proofDtlId = IdGen.PKGenerator("hr_eis_proof_dtl",
							objectArgs);
					hrEisProofDtl.setSrNo(proofDtlId);
					
					proofLookupId = cmnLookupDao.read(proofId);
					hrEisProofDtl.setCmnLookupMst(proofLookupId);
					hrEisProofDtl.setCmnDatabaseMst(cmnDatabaseMst);
					hrEisProofDtl.setCmnLocationMst(cmnLocationMst);
					hrEisProofDtl.setCreatedDate(sysDate);
					hrEisProofDtl.setOrgPostMstByCreatedByPost(orgPostMst);
					hrEisProofDtl.setOrgUserMstByCreatedBy(orgUserMst);
					hrEisProofDtl.setOrgPostMstByUserId(orgEmpMst
							.getOrgUserMst());
					logger.info("Sr. No is:-" + hrEisProofDtl.getSrNo());
					logger.info("Proof No is:-" + hrEisProofDtl.getProofNum());
					logger.info("Database Id is:-"
							+ hrEisProofDtl.getCmnDatabaseMst().getDbId());
					logger.info("ProofType Name is:-"
							+ hrEisProofDtl.getCmnLookupMst().getLookupName());
					generateDao.create(hrEisProofDtl);
				}
				// End.

				msg = 1;
			} else {
				logger.info(" -------insert mode---------------");

				long employeeId = IdGen.PKGenerator("hr_eis_emp_mst",
						objectArgs);
				long lnEmpId = empMst.getEmpId();
				empMst.setEmpId(employeeId);

				if (orgEmpMst == null) {
					logger.info("Null object ");

				}

				CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(
						CmnLookupMst.class, serv.getSessionFactory());
				/*
				 * CmnLookupMst cmnLookupMst = null; orgEmpMst.setEmpId(id);
				 * orgEmpMst.setCmnLanguageMst(cmnLanguageMst);
				 * 
				 * OrgUserMstDaoImpl orgUserDaoImpl = new
				 * OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				 * long uId=300061; OrgUserMst objOrgUserMst =
				 * orgUserDaoImpl.read(uId);
				 * orgEmpMst.setOrgUserMst(objOrgUserMst);
				 * 
				 * 
				 * orgEmpMst.setOrgUserMstByCreatedBy(orgUserMst);
				 * orgEmpMst.setOrgPostMstByCreatedByPost(orgPostMst); long
				 * lookupId = 1; cmnLookupMst = cmnLookupDao.read(lookupId);
				 * orgEmpMst.setCmnLookupMst(cmnLookupMst);
				 * orgEmpMst.setCreatedDate(sysdate);
				 * orgEmpDAOImpl.create(orgEmpMst);
				 * logger.info("Record Inserrted in Org_emp_mst");
				 */

				// objOrgEmpMst = orgEmpDAOImpl.read(empId);
				// empMst.setEmpId(id);
				orgEmpMst = new OrgEmpMst();
				boolean flag = true;
				orgEmpMst = orgEmpDAOImpl.read(lnEmpId);
				OrgEmpMst orgEmpMstgu = new OrgEmpMst();
				if (langId != 1) {
					OrgUserMst ElementCode = new OrgUserMst();
					ElementCode = orgEmpMst.getOrgUserMst();

					List<OrgEmpMst> orgEmpMstList = empinfodao.getOrgData(
							ElementCode, 1);
					if (orgEmpMstList.size() > 0) {
						orgEmpMstgu = (orgEmpMstList.get(0));
					} else {
						flag = false;
					}
				} else {
					orgEmpMst.setEmpGPFnumber(orgEmpMst.getEmpGPFnumber());
					orgEmpMstgu = (orgEmpMst);
				}
				// cmnCountryMstNationality;
				if (cmnCountryMstNationality != null) {
					logger.info("::::::::::23:::::::");
					empMst
							.setCmnCountryMstByEmpNationality(cmnCountryMstNationality);/*
																						 * done
																						 * by
																						 * gole
																						 */
					logger.info("::::::::::24:::::::");
				}
				if (cmnLangMstMotherTounge != null) {
					logger.info("::::::::::12:::::::");
					empMst.setCmnLanguageMst(cmnLangMstMotherTounge);
					logger.info("::::::::::13:::::::");
				}
				empMst.setOrgEmpMst(orgEmpMstgu);
				empMst.setCmnDatabaseMst(cmnDatabaseMst);
				// empMst.setCmnLanguageMst(cmnLanguageMst);
				empMst.setCmnLocationMst(cmnLocationMst);
				empMst.setOrgUserMstByCreatedBy(orgUserMst);

				// For CMN LOOK UP CHANGES

				if (langId != 1) {

					String cmnLookupName = cmnLookupDao.read(
							empMst.getEmpRecruitSrc()).getLookupName();
					CmnLookupMst cmnLookupMst = cmnLookupDao
							.getLookUpVOByLookUpNameAndLang(cmnLookupName,
									langId);
					if (cmnLookupMst != null)
						empMst.setEmpRecruitSrc(cmnLookupMst.getLookupId());

					/*
					 * cmnLookupName=cmnLookupDao.read(Long.parseLong(empMst.getEmpReligionId
					 * ())).getLookupName(); cmnLookupMst = new CmnLookupMst();
					 * cmnLookupMst =
					 * cmnLookupDao.getLookUpVOByLookUpNameAndLang
					 * (cmnLookupName, 1); if(cmnLookupMst!=null)
					 * empMst.setEmpReligionId
					 * (String.valueOf(cmnLookupMst.getLookupId()));
					 */

					cmnLookupName = cmnLookupDao.read(
							Long.parseLong(empMst.getEmpSalDisbMd()))
							.getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, 1);
					if (cmnLookupMst != null)
						empMst.setEmpSalDisbMd(String.valueOf(cmnLookupMst
								.getLookupId()));

					cmnLookupName = cmnLookupDao.read((empMst.getEmpType()))
							.getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, 1);
					if (cmnLookupMst != null)
						empMst.setEmpType((cmnLookupMst.getLookupId()));

					/*
					 * cmnLookupName=cmnLookupDao.read((empMst.getEmpCategory()))
					 * .getLookupName(); cmnLookupMst = new CmnLookupMst();
					 * cmnLookupMst =
					 * cmnLookupDao.getLookUpVOByLookUpNameAndLang
					 * (cmnLookupName, 1); if(cmnLookupMst!=null)
					 * empMst.setEmpCategory((cmnLookupMst.getLookupId()));
					 */

					cmnLookupName = cmnLookupDao.read(
							Long.parseLong((empMst.getEmpStatusId())))
							.getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, 1);
					if (cmnLookupMst != null)
						empMst.setEmpStatusId(String.valueOf((cmnLookupMst
								.getLookupId())));
				} else {
					if (cmnLookupMstByCategory != null) {
						empMst
								.setCmnLookupMstByEmpCategoryId(cmnLookupMstByCategory);
					}
					if (cmnLookupMstByCastId != null) {
						empMst
								.setCmnLookupMstByEmpCasteId(cmnLookupMstByCastId);
					}
					if (cmnLookupMstBySubCastId != null) {
						empMst
								.setCmnLookupMstByEmpSubCasteId(cmnLookupMstBySubCastId);
					}
					if (cmnLookupMstByReligionId != null) {
						empMst
								.setCmnLookupMstByEmpReligionId(cmnLookupMstByReligionId);
					}
					if (cmnLangMstMotherTounge != null) {
						empMst.setCmnLanguageMst(cmnLangMstMotherTounge);// removed
																			// by
																			// AG
					}
				}
				// Added By Urvin Shah.
				// HrEisProofDtl
				long proofDtlId = IdGen.PKGenerator("hr_eis_proof_dtl",
						objectArgs);
				hrEisProofDtl.setSrNo(proofDtlId);
				// Temp. Hard-Code for the PAN=300166.
				CmnLookupMst proofLookupId = new CmnLookupMst();
				long proofId = 300166;
				proofLookupId = cmnLookupDao.read(proofId);
				hrEisProofDtl.setCmnLookupMst(proofLookupId);
				hrEisProofDtl.setCmnDatabaseMst(cmnDatabaseMst);
				hrEisProofDtl.setCmnLocationMst(cmnLocationMst);
				hrEisProofDtl.setOrgPostMstByUserId(orgEmpMst.getOrgUserMst());
				hrEisProofDtl.setCreatedDate(sysDate);
				hrEisProofDtl.setOrgUserMstByCreatedBy(orgUserMst);
				hrEisProofDtl.setOrgPostMstByCreatedByPost(orgPostMst);

				generateDao.create(hrEisProofDtl);
				// end.

				// empMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				// empMst.setOrgUserMstByUpdatedBy(orgUserMst);
				empMst.setCreatedDate(sysdate);
				empMst.setTrnCounter(new Integer(1));
				// empMst.setUpdatedDate(sysdate);
				objectArgs.put("msg", "1");
				if (flag)
					empinfodao.create(empMst);
				logger.info("The Record is Inserted in to the Emp_mst");
				OrgEmpMst orgEmpMst1 = (OrgEmpMst) objectArgs.get("orgEmpMst");
				OrgEmpMst objOrgEmpMst = orgEmpDAOImpl.read(empMst
						.getOrgEmpMst().getEmpId());
				// For org_emp_mst.
				// logger.info("ObjOrgEmpMst:-"+orgEmpMst);
				// objOrgEmpMst.setEmpFname(orgEmpMst.getEmpFname());
				// objOrgEmpMst.setEmpMname(orgEmpMst.getEmpMname());
				// objOrgEmpMst.setEmpLname(orgEmpMst.getEmpLname());
				objOrgEmpMst.setEmpDob(orgEmpMst.getEmpDob());
				objOrgEmpMst.setEmpDoj(orgEmpMst.getEmpDoj());
				// objOrgEmpMst.setStartDate(orgEmpMst.getStartDate());
				// objOrgEmpMst.setEmpPrefix(orgEmpMst.getEmpPrefix());
				objOrgEmpMst.setEmpSrvcExp(orgEmpMst.getEmpSrvcExp());
				objOrgEmpMst.setEmpSrvcFlag(orgEmpMst.getEmpSrvcFlag());
				
				objOrgEmpMst.setEmpGPFnumber(orgEmpMst1.getEmpGPFnumber());
				// objOrgEmpMst.setEndDate(orgEmpMst.getEndDate());
				// objOrgEmpMst.setActivateFlag(orgEmpMst.getActivateFlag());
				// objOrgEmpMst.setStartDate(sysDate);

				/* #################################################### */
				// Added By Varun For GPF A/C Date:-31-07-2008
				/*
				 * long empID = orgEmpMst1.getEmpId(); OrgEmpMst orgempmst = new
				 * OrgEmpMst();
				 * orgempmst=(OrgEmpMst)genericDaoHibernateImpl.read(empID);
				 * userID = orgempmst.getOrgUserMst().getUserId();
				 */
				userID = Long.parseLong(objectArgs.get("userID").toString());

				// logger.info("GPF A/C No is:->"+orgEmpMst1.getEmpGPFnumber().trim());
				hrGpfBalanceDtls.setGpfAccNo(orgEmpMst1.getEmpGPFnumber());
				hrGpfBalanceDtls.setUserId(userID);
				// logger.info("Before Insertion");
				hrGpfBalanceDtls.setTrnCounter(1);
				hrGpfBalanceDtls.setCmnDatabaseMst(cmnDatabaseMst);
				hrGpfBalanceDtls.setCmnLocationMst(cmnLocationMst);
				hrGpfBalanceDtls.setOrgGradeMst(orgGradeMst);
				//hrGpfBalanceDtls.setOrgGradeMst(orgEmpMst.getOrgGradeMst());
				hrGpfBalanceDtls.setCreatedDate(sysDate);

				hrGpfBalanceDtls.setOrgPostMstByCreatedByPost(orgPostMst);
				hrGpfBalanceDtls.setOrgUserMstByCreatedByUser(orgUserMst);
				genericDaoHibernateImpl.create(hrGpfBalanceDtls);
				// logger.info("After GPF Insertion");
				// Ended By Varun For GPF A/C Date:-31-07-2008
				/* #################################################### */

				objOrgEmpMst.setOrgUserMstByUpdatedBy(orgUserMst);
				objOrgEmpMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				objOrgEmpMst.setUpdatedDate(sysDate);

				orgEmpDAOImpl.update(objOrgEmpMst);
				logger.info("The Record is updated in to the org_Emp_mst");

			}

			// EmpInfoDAOImpl empinfodao = new
			// EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			List userList = empinfodao.getUserListByLogin(langId, userId);
			List<OrgUserMst> orgUserList = new ArrayList();
			for (Iterator iter = userList.iterator(); iter.hasNext();) {
				Object[] row = (Object[]) iter.next();
				OrgUserMst userMst = new OrgUserMst();
				String id = row[0].toString();
				userMst.setUserId(Long.parseLong(id));
				orgUserList.add(userMst);
			}
			/*
			 * List actionList =
			 * empinfodao.getAllEmpDatabyDept(0,locationId);//empinfodao
			 * .getAllEmpData(); List<HrEisEmpMst> empList = new ArrayList();
			 * for (int i=0;i<actionList.size();i++) { HrEisEmpMst hrEisempMst=
			 * new HrEisEmpMst(); hrEisempMst = (HrEisEmpMst)actionList.get(i);
			 * HrEisEmpMst hrEisempMstgu= new HrEisEmpMst();
			 * hrEisempMstgu.setEmpId(hrEisempMst.getEmpId());
			 * hrEisempMstgu.setEmail(hrEisempMst.getEmail());
			 * //hrEisempMstgu.setEmpMotherTongue
			 * (hrEisempMst.getEmpMotherTongue());
			 * hrEisempMstgu.setEmpNationality(hrEisempMst.getEmpNationality());
			 * hrEisempMstgu.setContactNo(hrEisempMst.getContactNo());
			 * hrEisempMstgu.setEmpType(hrEisempMst.getEmpType()); if(langId!=1)
			 * { OrgUserMst ElementCode= new OrgUserMst();
			 * ElementCode=hrEisempMst.getOrgEmpMst().getOrgUserMst();
			 * 
			 * List<OrgEmpMst> orgEmpMstList =
			 * empinfodao.getOrgData(ElementCode, langId);
			 * if(orgEmpMstList.size()>0) {
			 * hrEisempMstgu.setOrgEmpMst(orgEmpMstList.get(0)); } else {
			 * hrEisempMstgu.setOrgEmpMst(hrEisempMst.getOrgEmpMst()); } } else
			 * { hrEisempMstgu.setOrgEmpMst(hrEisempMst.getOrgEmpMst()); }
			 * 
			 * empList.add(hrEisempMstgu);
			 * 
			 * 
			 * }
			 * 
			 * objectArgs.put("actionList", empList);
			 */

			resultObject.setResultCode(ErrorConstants.SUCCESS);

			// added by ankit Bhatt for merging the screens.
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			String empAllRec = "";
			if (!objectArgs.get("empAllRec").toString().equals(""))
				empAllRec = objectArgs.get("empAllRec").toString();
			long empID = 0;
			if (!objectArgs.get("empId").toString().equals(""))
				empID = Long.parseLong(objectArgs.get("empId").toString());

			logger.info("empAllRec in insert/update employee is " + empAllRec);
			if (msg == 1)
				objectArgs.put("MESSAGECODE", 300006);
			else
				objectArgs.put("MESSAGECODE", 300005);

			// resultObject.setViewName("empViewList");
			// added by Ankit Bhatt for merging screens
			if (msg == 1 && empAllRec.equalsIgnoreCase("true") == true) {
				objectArgs.put("empId", empID);
				objectArgs.put("empAllRec", "true");
				resultObject.setViewName("empEditListEmpAllRec");

			}

			else if (msg == 1) {
				resultObject.setViewName("empEditList");
			}

			else if ((msg != 1) && empAllRec.equalsIgnoreCase("true") == true) {
				objectArgs.put("empId", empID);
				objectArgs.put("empAllRec", "true");
				resultObject.setViewName("EmployeeMasterEmpAllRec");

			}

			else {
				resultObject.setViewName("EmployeeMaster");
			}

			if (empMst != null)
				empID = empMst.getEmpId();
			objectArgs.put("empId", empID);
			objectArgs.put("empAllRecFlag", "true");
			logger.info("Emp ID in Redirecting page " + empID);
			// ended by Ankit Bhatt.
			resultObject.setResultValue(objectArgs);
			logger.info("INSETED SUCCESSFULLY");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return resultObject;
	}

	public ResultObject getEmpData(Map objectArgs) {
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv
				.getSessionFactory());
		EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv
				.getSessionFactory());
		Map voToService = (Map) objectArgs.get("voToServiceMap");
        
		////////// for update pay bill
		String updatePaybillFlg="";
		int paybillMonth=0;
		int paybillYear=0;
		String empName = "";
		if((voToService.get("Employee_srchNameText_EmpInfoSearch")!=null&&!voToService.get("Employee_srchNameText_EmpInfoSearch").equals("")))
			empName=(String)voToService.get("Employee_srchNameText_EmpInfoSearch").toString();
		objectArgs.put("empName", empName);
		if (voToService.get("updatePaybillFlg") != null)
			updatePaybillFlg = voToService.get("updatePaybillFlg").toString();
	    if(voToService.get("paybillMonth")!=null)
	    	paybillMonth=Integer.parseInt(voToService.get("paybillMonth").toString());
	    if(voToService.get("paybillYear")!=null)
	    	paybillYear=Integer.parseInt(voToService.get("paybillYear").toString());
		
		objectArgs.put("updatePaybillFlg",updatePaybillFlg);
	    objectArgs.put("paybillMonth", paybillMonth);
	    objectArgs.put("paybillYear", paybillYear);
		
		// Added by Ankit Bhatt
		String empAllRec = "";
		if (voToService.get("empAllRec") != null)
			empAllRec = voToService.get("empAllRec").toString();
		logger.info("empAllRec " + empAllRec);
		// ended by Ankit Bhatt

		
		//added by ravysh
		
		String EmpMpgFlag = "";
		long employeeId = 0;
		long otherId = 0;
		long empId = 0;
		String FromBasicDtlsNew = voToService.get("FromBasicDtlsNew")!=null?(String)voToService.get("FromBasicDtlsNew"):"";
		
		if(FromBasicDtlsNew != null && FromBasicDtlsNew  != "")
		{
				if((voToService.get("Employee_ID_EmpInfoSearch")!=null&&!voToService.get("Employee_ID_EmpInfoSearch").equals("")))
					empId=Long.parseLong( voToService.get("Employee_ID_EmpInfoSearch").toString());
				if((voToService.get("otherId")!=null&&!voToService.get("otherId").equals("")))
					otherId=Long.parseLong( voToService.get("otherId").toString());
		}		
		objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
		objectArgs.put("otherId", otherId);
		objectArgs.put("empName", empName);
		
//long gradeId=0;
		String empIdStr = "";

		try {

			// by manoj for employee search
			if (voToService.get("Employee_ID_EmpInfoSearch") != null
					&& !voToService.get("Employee_ID_EmpInfoSearch").toString()
							.equals("")) {
				empIdStr = (String) voToService
						.get("Employee_ID_EmpInfoSearch");

				logger.info("the emp id from search employee " + empIdStr);
				// end by manoj for employee search
			}
			
			if (voToService.get("EmpMpg") != null
					&& !voToService.get("EmpMpg").toString().equals("")) {

				EmpMpgFlag = (String) voToService.get("EmpMpg");
				logger.info("EmpMpgFlag is:-->" + EmpMpgFlag);
			}

			StringBuffer data = new StringBuffer();
			IdGenerator idGen = new IdGenerator();
			// Map loginDetailsMap
			// =(Map)session.getAttribute("loginDetailsMap");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			
			
			OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
			
			long userId = StringUtility.convertToLong(loginDetailsMap.get(
					"userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(
					OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			// long langId =
			// StringUtility.convertToLong(loginMap.get("langId").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			// long locationId =
			// StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			// Start Added Bu Urvin.
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap
					.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null && !cmnLocationMst
					.getLocationCode().trim().equals("")) ? cmnLocationMst
					.getLocationCode() : "";
			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			// End
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			// List<CmnLookupMst> religionList=
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Religion",1);
			// List<CmnLookupMst> religionList =
			// cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Religion",
			// langId);

			// List<CmnLookupMst> categoryList=
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Category",langId);
			List<CmnLookupMst> salmodeList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Salary Mode", langId);
			List<CmnLookupMst> emptypeList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Emp Type", langId);
			List<CmnLookupMst> recruitList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Rec Source", langId);
			List<CmnLookupMst> statusList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("StatusId", langId);
			List<CmnLookupMst> saluList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Salutation", langId);

			// Start Added By Urvin Shah.
			List<CmnLookupMst> categoryList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("emp_Category", langId);
			List<CmnLookupMst> castList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("emp_Caste", langId);
			List<CmnLookupMst> subCastList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("emp_SubCaste", langId);
			// List<CmnLookupMst> marritalStatusList =
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Emp_Marital_Status",langId);
			List<CmnLookupMst> religionList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("emp_Religion", langId);
			List<OrgGradeMst> classList = (List<OrgGradeMst>) orgGradeMstDaoImpl.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);
			List<CmnLookupMst> languageList = cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("language_list", langId);

			logger.info("updatePaybillFlg-------------is----->" + updatePaybillFlg);
			logger.info("Size of saluList is----->" + saluList.size());
			logger.info("Size of categoryList is----->" + categoryList.size());
			logger.info("Size of salmodeList is----->" + salmodeList.size());
			logger.info("Size of religionList is----->" + religionList.size());
			logger.info("Size of castList is----->" + castList.size());
			logger.info("Size of subCastList is----->" + subCastList.size());
			logger.info("Size of languageList is----->" + languageList.size());
			logger.info("Size of classList is ------>" + classList.size());
			String edit = "";

			if (voToService.get("edit") != null) {
				edit = (String) voToService.get("edit").toString();
			}
			logger.info("the value of edit is " + edit);

			if (!edit.equals(null) && edit.equalsIgnoreCase("Y")) {
				logger.info("I m in edit mode ");
				empId = (voToService.get("empId").toString() != null && !voToService
						.get("empId").toString().equals("")) ? Long
						.parseLong(voToService.get("empId").toString()) : 0;
				/*long gradeId =(voToService.get("gradeId").toString() != null && !voToService
						.get("gradeId").toString().equals("")) ? Long
						.parseLong(voToService.get("gradeId").toString()) : 0;*/
						
					
				logger.info("The Employee Id is:-" + empId);

				// List actionList = empinfodao.getEmpIdData(empId);
				HrEisEmpMst hreiempMst = null;
				HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
				if (empId != 0)
					hreiempMst = empinfodao.read(empId);
				// long idEmpId = idGen.PKGenerator("hr_eis_emp_mst",
				// objectArgs);
				// hrEisEmpMst.setEmpId(hreiempMst.getEmpId());
				// hrEisEmpMst.setEmpId(idEmpId);

				// logger.info("The hr_eis_emp list size is "+actionList.size());

		
			
		
				if (hreiempMst.getCmnLookupMstByEmpReligionId() != null) {
					castList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
							.getListByColumnAndValue("parentLookupId",
									hreiempMst.getCmnLookupMstByEmpReligionId()
											.getLookupId());
					logger.info("The size is:-" + castList.size());
				}
				if (hreiempMst.getCmnLookupMstByEmpCasteId() != null) {
					subCastList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
							.getListByColumnAndValue("parentLookupId",
									hreiempMst.getCmnLookupMstByEmpCasteId()
											.getLookupId());
					logger.info("The size is:-" + subCastList.size());
				}

				OrgEmpMst orgEmpMstgu = new OrgEmpMst();
				orgEmpMstgu.setEmpId(hreiempMst.getOrgEmpMst().getEmpId());
				OrgGradeMst orgGradeMst=null;
				String lgradename="";
				String GradeName="";
				long GradeId=0;
				OrgEmpMst empVO =orgEmpDAOImpl.read(hreiempMst.getOrgEmpMst().getEmpId());
				
				HrPayGpfBalanceDtls hrpaygpfDtls=new HrPayGpfBalanceDtls();
				
				EmpGpfDtlsDAOImpl empGpfDAo = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
				
				if(empVO.getOrgUserMst().getUserId()!=0)
				{
					long  userID= empVO.getOrgUserMst().getUserId();
					List<HrPayGpfBalanceDtls>	hrPayGpfBalanceDtls=empGpfDAo.getListByColumnAndValue("userId", userID);
					if (hrPayGpfBalanceDtls != null && hrPayGpfBalanceDtls.size() > 0 && hrPayGpfBalanceDtls.get(0) != null)
					{
						hrpaygpfDtls=hrPayGpfBalanceDtls.get(0);
						//lgradename=hrpaygpfDtls.getOrgGradeMst().getGradeName();
						
						if(hrpaygpfDtls.getOrgGradeMst()!=null )
						{
						GradeId=hrpaygpfDtls.getOrgGradeMst().getGradeId();
						}
						else
						{
							GradeId=empVO.getOrgGradeMst().getGradeId();
						}
						orgGradeMst =orgGradeMstDaoImpl.read(GradeId);
						
					}
				}
				/*if(voToService.get("gradeId")!=null && !voToService.get("gradeId").toString().equals(""))
				{
					logger.info("In  IFFFFFFFFFFFFFF gradeId before..."+gradeId);
					logger.info("In  IFFFFFFFFFFFFFF gradeId before..."+gradeId);
					
					gradeId=(Long)(voToService.get("gradeId"));					
					logger.info("the value of the gradeID is ::"+gradeId);
					orgGradeMst =orgGradeMstDaoImpl.read(gradeId);
					logger.info("orgGradeMst::"+orgGradeMst.getGradeId()+" name::"+orgGradeMst.getGradeName());
					//orgEmpMstgu.setOrgGradeMst(orgGradeMst);
					lgradename=orgGradeMst.getGradeName();
				}*/
				
				hrpaygpfDtls.setOrgGradeMst(orgGradeMst)	;	
				
				//orgGradeMst.setGradeId(hreiempMst.getOrgEmpMst().getOrgGradeMst().getGradeId());
				// hrEisEmpMst.setEmpBirthPlace(hreiempMst.getEmpBirthPlace());
				hrEisEmpMst.setEmpGender(hreiempMst.getEmpGender());
				hrEisEmpMst.setEmpId(hreiempMst.getEmpId());
				// hrEisEmpMst.setEmpNativePlace(hreiempMst.getEmpNativePlace());
				hrEisEmpMst.setCmnLanguageMst(hreiempMst.getCmnLanguageMst());
				if (langId != 1) {
					CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(
							CmnLookupMst.class, serv.getSessionFactory());
					String cmnLookupName = cmnLookupDao.read(
							hreiempMst.getEmpRecruitSrc()).getLookupName();
					CmnLookupMst cmnLookupMst = cmnLookupDao
							.getLookUpVOByLookUpNameAndLang(cmnLookupName,
									langId);
					if (cmnLookupMst != null)
						hrEisEmpMst
								.setEmpRecruitSrc(cmnLookupMst.getLookupId());

					/*
					 * cmnLookupName=cmnLookupDao.read(Long.parseLong(hreiempMst.
					 * getEmpReligionId())).getLookupName();
					 */
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							hreiempMst.getCmnLookupMstByEmpReligionId()
									.getLookupId(), langId);
					if (cmnLookupMst != null)
						hrEisEmpMst
								.setCmnLookupMstByEmpReligionId(cmnLookupMst);

					cmnLookupName = cmnLookupDao.read(
							Long.parseLong(hreiempMst.getEmpSalDisbMd()))
							.getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setEmpSalDisbMd(String.valueOf(cmnLookupMst
								.getLookupId()));

					cmnLookupName = cmnLookupDao
							.read((hreiempMst.getEmpType())).getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setEmpType((cmnLookupMst.getLookupId()));

					// cmnLookupName=cmnLookupDao.read((hreiempMst.getEmpCategory())).getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							hreiempMst.getCmnLookupMstByEmpCategoryId()
									.getLookupId(), langId);
					if (cmnLookupMst != null)
						hrEisEmpMst
								.setCmnLookupMstByEmpCategoryId(cmnLookupMst);

					cmnLookupName = cmnLookupDao.read(
							Long.parseLong((hreiempMst.getEmpStatusId())))
							.getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(
							cmnLookupName, langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setEmpStatusId(String.valueOf((cmnLookupMst
								.getLookupId())));
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							hreiempMst.getCmnLookupMstByEmpCasteId()
									.getLookupId(), langId);
					if (cmnLookupMst != null) {
						hrEisEmpMst.setCmnLookupMstByEmpCasteId(cmnLookupMst);
					}
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							hreiempMst.getCmnLookupMstByEmpSubCasteId()
									.getLookupId(), langId);
					if (cmnLookupMst != null) {
						hrEisEmpMst
								.setCmnLookupMstByEmpSubCasteId(cmnLookupMst);
					}
				} else {
					hrEisEmpMst.setEmpRecruitSrc(hreiempMst.getEmpRecruitSrc());
					hrEisEmpMst.setCmnLookupMstByEmpReligionId(hreiempMst
							.getCmnLookupMstByEmpReligionId());
					hrEisEmpMst.setEmpSalDisbMd(hreiempMst.getEmpSalDisbMd());
					hrEisEmpMst.setEmpType(hreiempMst.getEmpType());
					hrEisEmpMst.setEmpStatusId(hreiempMst.getEmpStatusId());
					// hrEisEmpMst.setEmpCategory(hreiempMst.getEmpCategory());
					hrEisEmpMst.setCmnLookupMstByEmpCategoryId(hreiempMst
							.getCmnLookupMstByEmpCategoryId());
				}

				hrEisEmpMst.setEmpHobby(hreiempMst.getEmpHobby());
				if (hreiempMst.getCmnCountryMstByEmpNationality() != null)
					hrEisEmpMst.setCmnCountryMstByEmpNationality(hreiempMst
							.getCmnCountryMstByEmpNationality());
				hrEisEmpMst.setCmnLookupMstByEmpCasteId(hreiempMst
						.getCmnLookupMstByEmpCasteId());
				hrEisEmpMst.setEmpConfDueDt(hreiempMst.getEmpConfDueDt());
				// hrEisEmpMst.setEmpSubCaste(hreiempMst.getEmpSubCaste());
				hrEisEmpMst.setCmnLookupMstByEmpSubCasteId(hreiempMst
						.getCmnLookupMstByEmpSubCasteId());
				hrEisEmpMst.setEmail(hreiempMst.getEmail());
				hrEisEmpMst.setContactNo(hreiempMst.getContactNo());
				// rEisEmpMst.
				if (langId != 1) {
					OrgUserMst ElementCode = new OrgUserMst();
					ElementCode = hreiempMst.getOrgEmpMst().getOrgUserMst();

					List<OrgEmpMst> orgEmpMstList = empinfodao.getOrgData(
							ElementCode, langId);
					if (orgEmpMstList.size() > 0) {
						orgEmpMstgu = (orgEmpMstList.get(0));
					} else {
						orgEmpMstgu = hreiempMst.getOrgEmpMst();
					}
				} else {
					orgEmpMstgu = hreiempMst.getOrgEmpMst();
				}
				//    		
				hrEisEmpMst.setOrgEmpMst(orgEmpMstgu);

				// Added By Urvin shah.
				HrEisProofDtl hrEisProofDtl = new HrEisProofDtl();
				logger.info("Inside update Mode");
				GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(
						HrEisProofDtl.class);
				genericDaoHibernateImpl.setSessionFactory(serv
						.getSessionFactory());
				List lstProof = genericDaoHibernateImpl
						.getListByColumnAndValue("orgPostMstByUserId",
								orgEmpMstgu.getOrgUserMst());
				if (lstProof != null && lstProof.size() > 0
						&& lstProof.get(0) != null) {
					hrEisProofDtl = (HrEisProofDtl) lstProof.get(0);
					logger.info("The Proof No is:-"
							+ hrEisProofDtl.getProofNum());
				}
				objectArgs.put("hrEisProofDtl", hrEisProofDtl);
				// End.
				objectArgs.put("GradeId", GradeId);
				objectArgs.put("orgGradeMst", orgGradeMst);
				objectArgs.put("orgEmpMst", orgEmpMstgu);
				objectArgs.put("hrEisEmpMst", hrEisEmpMst);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				// added by Ankit Bhatt for Merging the Screens.

				// For country selection in empEditList.jsp...
				// Will be responsible to populate country names in Nationality
				// combobox
				CmnCountryMstDAO cmnCountryMstDao = new CmnCountryMstDAOImpl(
						CmnCountryMst.class, serv.getSessionFactory());
				List<CmnCountryMst> countryNamesList = (List<CmnCountryMst>) cmnCountryMstDao
						.getAllCountries(langId);
				logger.info("Size of countryNamesList is----->"
						+ countryNamesList.size());
				// End.
				objectArgs.put("countryNamesList", countryNamesList);

				if (empAllRec.equalsIgnoreCase("Y")) {
					resultObject.setViewName("empEditListEmpAllRec");
					objectArgs.put("empAllRec", "true");

				} else {
					// ended by Ankit Bhatt
					if(FromBasicDtlsNew.equals("YES"))
						resultObject.setViewName("empEditListEmpAllRec");	
					else
					resultObject.setViewName("empEditList");
				}
				// resultObject.setViewName("empAllRec");

			}
			// added by Ankit Bhatt.

			else if (EmpMpgFlag.equalsIgnoreCase("Y")) {
				logger.info("EmpMpgFlag = " + EmpMpgFlag);
				objectArgs.put("code", "1"); // code will be used to redirect to
												// Allowance Mapping Screen
				List empNames = empinfodao.getEmpNamesFromOtherDtls();

				List<HrEisEmpMst> empNamesList = new ArrayList();
				for (int i = 0; i < empNames.size(); i++) {
					HrEisEmpMst hrEisempMst = new HrEisEmpMst();
					hrEisempMst = (HrEisEmpMst) empNames.get(i);
					HrEisEmpMst hrEisempMstgu = new HrEisEmpMst();
					hrEisempMstgu.setEmpId(hrEisempMst.getEmpId());

					if (langId != 1) {
						OrgUserMst ElementCode = new OrgUserMst();
						ElementCode = hrEisempMst.getOrgEmpMst()
								.getOrgUserMst();

						List<OrgEmpMst> orgEmpMstList = empinfodao.getOrgData(
								ElementCode, langId);
						if (orgEmpMstList.size() > 0) {
							hrEisempMstgu.setOrgEmpMst(orgEmpMstList.get(0));
						} else {
							hrEisempMstgu.setOrgEmpMst(hrEisempMst
									.getOrgEmpMst());
						}
					} else {
						hrEisempMstgu.setOrgEmpMst(hrEisempMst.getOrgEmpMst());
					}

					empNamesList.add(hrEisempMstgu);

				}

				objectArgs.put("empList", empNamesList);
				resultObject.setViewName("showEmpNamesInAllowMap");
			} else if (EmpMpgFlag.equalsIgnoreCase("N")) {
				logger.info("EmpMpgFlag = " + EmpMpgFlag);
				objectArgs.put("code", "0");// code will be used to redirect to
											// Deduction Detail Screen
				List empNames = empinfodao.getEmpNamesFromOtherDtls();

				List<HrEisEmpMst> empNamesList = new ArrayList();
				for (int i = 0; i < empNames.size(); i++) {
					HrEisEmpMst hrEisempMst = new HrEisEmpMst();
					hrEisempMst = (HrEisEmpMst) empNames.get(i);
					HrEisEmpMst hrEisempMstgu = new HrEisEmpMst();
					hrEisempMstgu.setEmpId(hrEisempMst.getEmpId());

					if (langId != 1) {
						OrgUserMst ElementCode = new OrgUserMst();
						ElementCode = hrEisempMst.getOrgEmpMst()
								.getOrgUserMst();

						List<OrgEmpMst> orgEmpMstList = empinfodao.getOrgData(
								ElementCode, langId);
						if (orgEmpMstList.size() > 0) {
							hrEisempMstgu.setOrgEmpMst(orgEmpMstList.get(0));
						} else {
							hrEisempMstgu.setOrgEmpMst(hrEisempMst
									.getOrgEmpMst());
						}
					} else {
						hrEisempMstgu.setOrgEmpMst(hrEisempMst.getOrgEmpMst());
					}

					empNamesList.add(hrEisempMstgu);

				}

				objectArgs.put("empList", empNamesList);
				resultObject.setViewName("showEmpNamesInAllowMap");
			} else {
				if (empAllRec.equalsIgnoreCase("Y") == true) {
					
					objectArgs.put("empId", empIdStr);
					objectArgs.put("empAllRec", "true");
					resultObject.setViewName("empViewListEmpAllRec");

				} else {
					// ended by Ankit Bhatt
					if(FromBasicDtlsNew.equals("YES"))
						resultObject.setViewName("empViewListEmpAllRec");
					else
					resultObject.setViewName("empViewList");
				}
				// resultObject.setViewName("empViewList");
			}

			// resultObject.setViewName("empViewList");
			// ended by Ankit Bhatt.

			// by manoj for employee search
			List<HrEisEmpMst> actionList = new ArrayList();
			if (empIdStr != null && !empIdStr.equals("")) {
				// Added by mrugesh
				Map loginDetails = (Map) objectArgs.get("baseLoginMap");
				long languageId = 1;
				cmnLanguageMst = cmnLanguageMstDaoImpl.read(languageId);
				// Ended by mrugesh

				empId = Long.parseLong(empIdStr);
				logger.info("the emp id from else if is " + empId);
				EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class, serv
						.getSessionFactory());
				OrgEmpMst orgEmpInfoMst = orgEmpDao.getEngGujEmployee(orgEmpDao
						.read(empId), languageId);

				actionList = empinfodao.getHrEmpFromOrgEmp(orgEmpInfoMst);
				objectArgs.put("actionList", actionList);

			} else {
				/*
				 * List userList =empinfodao.getUserListByLogin(langId,userId);
				 * List <OrgUserMst> orgUserList = new ArrayList(); for
				 * (Iterator iter = userList.iterator(); iter.hasNext();) {
				 * Object[] row = (Object[])iter.next(); OrgUserMst userMst =
				 * new OrgUserMst(); String id=row[0].toString();
				 * userMst.setUserId(Long.parseLong(id));
				 * orgUserList.add(userMst); }
				 */
				logger.info("The locationcode is----->>>"+locationCode);
				actionList =null;
				//empinfodao.getAllEmpDatabyDept(0, locationCode,					langId);// empinfodao.getAllEmpData();
				objectArgs.put("actionList", actionList);
				objectArgs.put("ViewFlag", "Ture");
			}
			logger.info("the size of list is " + actionList.size());
			List<HrEisEmpMst> empList = new ArrayList();
			for (int i = 0; i < actionList.size(); i++) {
				HrEisEmpMst hrEisempMst = new HrEisEmpMst();
				hrEisempMst = (HrEisEmpMst) actionList.get(i);
				HrEisEmpMst hrEisempMstgu = new HrEisEmpMst();
				hrEisempMstgu.setEmpId(hrEisempMst.getEmpId());
				hrEisempMstgu.setEmail(hrEisempMst.getEmail());
				// hrEisempMstgu.setEmpMotherTongue(hrEisempMst.getEmpMotherTongue());
				hrEisempMstgu.setCmnCountryMstByEmpNationality(hrEisempMst
						.getCmnCountryMstByEmpNationality());
				// hrEisempMstgu.setEmpNationality(hrEisempMst.getEmpNationality());
				hrEisempMstgu.setContactNo(hrEisempMst.getContactNo());
				hrEisempMstgu.setEmpType(hrEisempMst.getEmpType());

				if (langId != 1) {
					OrgUserMst ElementCode = new OrgUserMst();
					ElementCode = hrEisempMst.getOrgEmpMst().getOrgUserMst();

					List<OrgEmpMst> orgEmpMstList = empinfodao.getOrgData(
							ElementCode, langId);
					if (orgEmpMstList.size() > 0) {
						hrEisempMstgu.setOrgEmpMst(orgEmpMstList.get(0));
					} else {
						hrEisempMstgu.setOrgEmpMst(hrEisempMst.getOrgEmpMst());
					}
				} else {
					hrEisempMstgu.setOrgEmpMst(hrEisempMst.getOrgEmpMst());
				}

				empList.add(hrEisempMstgu);
				objectArgs.put("actionList", empList);

			}

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			// resultObject.setResultValue(map);
			List orgEmpList = orgEmpDAOImpl.getAllEmployees();

			// objectArgs.put("actionList", actionList);
			// OrgEmpMst orgEmpMst = new OrgEmpMst();
			// orgEmpMst.get

			// map.put("actionList", actionList);
			objectArgs.put("religionList", religionList);
			objectArgs.put("categoryList", categoryList);
			objectArgs.put("salmodeList", salmodeList);
			objectArgs.put("emptypeList", emptypeList);
			objectArgs.put("recruitList", recruitList);
			objectArgs.put("statusList", statusList);
			objectArgs.put("saluList", saluList);
			objectArgs.put("classList", classList);
			// Start Added By Urvin Shah.
			logger.info("Size  CastList is:-" + castList.size());
			logger.info("Size  SubCastList is:-" + subCastList.size());
			objectArgs.put("castList", castList);
			objectArgs.put("subCastList", subCastList);
			objectArgs.put("languageList", languageList);

			// End.
			logger.info("U are out of the way:-");
			resultObject.setResultValue(objectArgs);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return resultObject;
	}

	public ResultObject newEmpData(Map objectArgs) {
		logger.info("IN Emp Master Data newEmpData");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		HttpSession session = request.getSession();
		try {

			// long langId =
			// StringUtility.convertToLong(loginMap.get("langId").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			EmpInfoDAOImpl empInfoDaoImpl = new EmpInfoDAOImpl(
					HrEisEmpMst.class, serv.getSessionFactory());
			// Map map = new HashMap();
			String empAllRec = "";
			if (request.getParameter("empAllRec") != null)
				empAllRec = request.getParameter("empAllRec").toString();
			logger.info("empAllRec " + empAllRec);

			long empID = 0;
			String empIdStr = request.getParameter("empId");
			
			String empname = "";
			if((request.getParameter("empName")!=null&&!request.getParameter("empName").equals("")))
				empname=request.getParameter("empName").toString();
			
			if (empIdStr != null && !empIdStr.equals("")) {
				empID = Long.parseLong(empIdStr);
			}
			logger.info("the emp id from search employee " + empIdStr);
			OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap
					.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null && !cmnLocationMst
					.getLocationCode().trim().equals("")) ? cmnLocationMst
					.getLocationCode() : "";
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			// List<CmnLookupMst> religionList=
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Religion",1);
			// List<CmnLookupMst> religionList =
			// cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Religion",
			// langId);
			// logger.info("size ===> "+religionList.size());

			// long locationId =
			// StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			/*
			 * long userId =
			 * StringUtility.convertToLong(loginDetailsMap.get("userId"
			 * ).toString()); OrgUserMstDaoImpl orgUserMstDaoImpl=new
			 * OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			 * OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			 */
			// Start Added Bu Urvin.
			// End
			/*
			 * List userList =empInfoDaoImpl.getUserListByLogin(langId,userId);
			 * List <OrgUserMst> orgUserList = new ArrayList(); for (Iterator
			 * iter = userList.iterator(); iter.hasNext();) { Object[] row =
			 * (Object[])iter.next(); OrgUserMst userMst = new OrgUserMst();
			 * String id=row[0].toString();
			 * userMst.setUserId(Long.parseLong(id)); orgUserList.add(userMst);
			 * }
			 */

			List<OrgEmpMst> empList = empInfoDaoImpl.getAllOrgDatabyDept(
					locationCode, langId);// empInfoDaoImpl.getOrgEmpData(langId);
			// List<CmnLookupMst> categoryList=
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Category",langId);
			List<CmnLookupMst> salmodeList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Salary Mode", langId);
			List<CmnLookupMst> emptypeList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Emp Type", langId);
			List<CmnLookupMst> recruitList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Rec Source", langId);
			List<CmnLookupMst> statusList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("StatusId", langId);
			List<CmnLookupMst> saluList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Salutation", langId);

			// Start Added By Urvin Shah.
			List<CmnLookupMst> categoryList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("emp_Category", langId);
			// List<CmnLookupMst> castList=
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("emp_Caste",langId);
			// List<CmnLookupMst> subCastList=
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("emp_SubCaste",langId);
			// List<CmnLookupMst> marritalStatusList =
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Emp_Marital_Status",langId);
			List<CmnLookupMst> religionList = (List<CmnLookupMst>) cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("emp_Religion", langId);
			List<OrgGradeMst> classList = (List<OrgGradeMst>) orgGradeMstDaoImpl.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);
			// List<CmnLanguageMst> languageList =
			// (List<CmnLanguageMst>)cmnDatabaseMstDaoImpl.list("Grade of Emp",langId);
			// varun
			List<CmnLookupMst> languageList = cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("language_list", langId);
			// End.

			// map.put("actionList", actionList);

			objectArgs.put("empList", empList);
			objectArgs.put("religionList", religionList);
			objectArgs.put("categoryList", categoryList);
			objectArgs.put("salmodeList", salmodeList);
			objectArgs.put("emptypeList", emptypeList);
			objectArgs.put("recruitList", recruitList);
			objectArgs.put("statusList", statusList);
			objectArgs.put("saluList", saluList);
			objectArgs.put("classList", classList);
			// Start Added By Urvin

			// objectArgs.put("castList",castList);
			// objectArgs.put("subCastList",subCastList);
			objectArgs.put("languageList", languageList);

			// End.

			// For country selection in empEditList.jsp...
			// Will be responsible to populate country names in Nationality
			// combobox
			CmnCountryMstDAO cmnCountryMstDao = new CmnCountryMstDAOImpl(
					CmnCountryMst.class, serv.getSessionFactory());
			List<CmnCountryMst> countryNamesList = (List<CmnCountryMst>) cmnCountryMstDao
					.getAllCountries(langId);
			logger.info("Size of countryNamesList in newEmpData is----->"
					+ countryNamesList.size());
			// End.
			objectArgs.put("countryNamesList", countryNamesList);

			EmpDAOImpl empDaoImpl = new EmpDAOImpl(OrgEmpMst.class, serv
					.getSessionFactory());
		
			long UserId = 0;
			if (empID != 0) {
				OrgEmpMst orgempMst = empDaoImpl.getEmployeeVO(empID);
				UserId = orgempMst.getOrgUserMst().getUserId();

				GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(
						HrPayGpfBalanceDtls.class);
				genericDaoHibernateImpl.setSessionFactory(serv
						.getSessionFactory());
				HrPayGpfBalanceDtls hrGpfBalanceDtls = new HrPayGpfBalanceDtls();
				hrGpfBalanceDtls = (HrPayGpfBalanceDtls) genericDaoHibernateImpl
						.read(UserId);
				String GradeName = hrGpfBalanceDtls.getOrgGradeMst()
						.getGradeName();

				

				objectArgs.put("GradeName", GradeName);
			}
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			if (empAllRec.equalsIgnoreCase("Y") == true) {
				
				objectArgs.put("empId", empIdStr);
				objectArgs.put("empAllRec", "true");
				resultObject.setViewName("EmployeeMasterEmpAllRec");

			} else {
				// ended by Ankit Bhatt
				resultObject.setViewName("EmployeeMaster");
			}
			// resultObject.setViewName("");
			objectArgs.put("empName", empname);
			resultObject.setResultValue(objectArgs);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return resultObject;
	}

	// to be changed
	public ResultObject findEmpName(Map objectArgs) {
		logger.info("IN findEmpName Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		// LoginDetails objLoginDetails
		// =(LoginDetails)session.getAttribute("loginDetails");
		// long langId = objLoginDetails.getLangId();

		String empName = "";

		StringBuffer propertyList = new StringBuffer();
		try {
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			if (voToService.get("emp_first_name") != null
					&& !voToService.get("emp_first_name").toString().equals("")) {

				empName = voToService.get("emp_first_name").toString()
						.toLowerCase();
			}
			List empNames = new ArrayList();
			if (voToService.get("orgflag") != null
					&& voToService.get("orgflag").toString().equals("true")) {
				EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(
						HrEisEmpMst.class, serv.getSessionFactory());
				empNames = empinfodao.findorgEmpName(empName, langId);

				for (Iterator iterDist = empNames.iterator(); iterDist
						.hasNext();) {
					OrgEmpMst OrgEmpMst = (OrgEmpMst) iterDist.next();
					long empId = OrgEmpMst.getEmpId();
					String empFirstNameFound = OrgEmpMst.getEmpFname();
					String empMiddleNameFound = OrgEmpMst.getEmpMname()!=null?OrgEmpMst.getEmpMname():"";
					String empLastNameFound = OrgEmpMst.getEmpLname();
					logger.info("Emp Name in service iterator "
							+ empFirstNameFound);
					propertyList.append("<emp-mapping>");
					propertyList.append("<emp-Id>").append(empId).append(
							"</emp-Id>");
					propertyList.append("<emp-first_name>").append(
							empFirstNameFound).append("</emp-first_name>");
					propertyList.append("<emp-middle_name>").append(
							empMiddleNameFound).append("</emp-middle_name>");
					propertyList.append("<emp-last_name>").append(
							empLastNameFound).append("</emp-last_name>");
					propertyList.append("</emp-mapping>");
				}
			} else {
				EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(
						HrEisEmpMst.class, serv.getSessionFactory());
				empNames = empinfodao.findEmpName(empName, langId);

				for (Iterator iterDist = empNames.iterator(); iterDist
						.hasNext();) {
					HrEisEmpMst empMstObj = (HrEisEmpMst) iterDist.next();
					long empId = empMstObj.getEmpId();
					String empFirstNameFound = empMstObj.getOrgEmpMst()
							.getEmpFname();
					String empMiddleNameFound =  empMstObj.getOrgEmpMst().getEmpMname()!=null?empMstObj.getOrgEmpMst().getEmpMname():"";
					String empLastNameFound = empMstObj.getOrgEmpMst()
							.getEmpLname();
					logger.info("Emp Name in service iterator "
							+ empFirstNameFound);
					propertyList.append("<emp-mapping>");
					propertyList.append("<emp-Id>").append(empId).append(
							"</emp-Id>");
					propertyList.append("<emp-first_name>").append("<![CDATA[")
							.append(empFirstNameFound).append("]]>").append(
									"</emp-first_name>");
					propertyList.append("<emp-middle_name>")
							.append("<![CDATA[").append(empMiddleNameFound)
							.append("]]>").append("</emp-middle_name>");
					propertyList.append("<emp-last_name>").append("<![CDATA[")
							.append(empLastNameFound).append("]]>").append(
									"</emp-last_name>");
					propertyList.append("</emp-mapping>");
				}
			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key",
					propertyList.toString()).toString();
			result.put("ajaxKey", stateNameIdStr);
			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");
		} catch (Exception e) {
			logger.info("Exception occures...");
			e.printStackTrace();
			logger.info("Exception Occures.");
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}

	public ResultObject getOrgEmployeeDetails(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Map voToService = (Map)objectArgs.get("voToServiceMap");
        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        StringBuffer bfEmpDtls = new StringBuffer();
        String dateofBirth="";
        String dateOfJoining="";
        String dateOfExpiry="";
        String GradeName="";
        try{
        	
            SimpleDateFormat sdfObj = new SimpleDateFormat("dd/MM/yyyy");
            
            long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
        	EmpDAOImpl empDaoImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
        	long empId =0;
        	if(voToService.get("Employee_ID_EmpSearch")!=null && !voToService.get("Employee_ID_EmpSearch").toString().equals(""))
        	{
        		
        		empId=StringUtility.convertToLong(voToService.get("Employee_ID_EmpSearch").toString());
        	}
        	if(voToService.get("emp_id")!=null && !voToService.get("emp_id").toString().equals(""))
    		{
    		empId=StringUtility.convertToLong(voToService.get("emp_id").toString());
    		}
        	
        	OrgEmpMst orgempMst = empDaoImpl.getEmployeeVO(empId);
        	EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
        	long UserId=orgempMst.getOrgUserMst().getUserId();
        	OrgEmpMst orgEmpMst= new  OrgEmpMst();
			orgEmpMst.setEmpId(orgempMst.getEmpId());
			
			long GradeId =orgempMst.getOrgGradeMst().getGradeId();
			
			/*GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrPayGpfBalanceDtls.class);
			genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
			EmpGpfDtlsDAO gpfDAo = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
			logger.info("the value of the UserId::"+UserId);
			List<HrPayGpfBalanceDtls> empgpfDtls  = gpfDAo.getListByColumnAndValue("userId",UserId);
			logger.info("the value of the empgpfDtls::"+empgpfDtls.size());
			if(!empgpfDtls.isEmpty())
			{
				HrPayGpfBalanceDtls empdtlVO =empgpfDtls.get(0);
				GradeName = empdtlVO.getOrgGradeMst().getGradeName();
				logger.info("the value of the GradeName::"+GradeName);
			}
			logger.info("the value of the empgpfDtls::"+empgpfDtls.size());
			*/
			
			long empID=0;	
			if(langId!=1)
    		{
        		OrgUserMst ElementCode= new  OrgUserMst();
        		ElementCode=orgempMst.getOrgUserMst();
    			List<OrgEmpMst> orgEmpMstListgu = empinfodao.getOrgData(ElementCode, langId);
    			List<OrgEmpMst> orgEmpMstList = empinfodao.getOrgData(ElementCode, 1);
    			if(orgEmpMstList.size()>0&&orgEmpMstList.size()>0)
    			{
    				orgEmpMst=(orgEmpMstListgu.get(0));
    				empID=(orgEmpMstList.get(0).getEmpId());
    			}
    			else
    			{
    				orgEmpMst=(orgempMst);
    				empID=orgempMst.getEmpId();
    			}
    			logger.info("*********1********"+empID);
    		}
    		else
    		{
    			orgEmpMst=(orgempMst);
				empID=orgempMst.getEmpId();
				logger.info("*******2**********"+empID);
    		}
			logger.info("*****************"+empID);
			dateofBirth = sdfObj.format(orgEmpMst.getEmpDob());
        	dateOfJoining = sdfObj.format(orgEmpMst.getEmpDoj());
        	Date doj = orgEmpMst.getEmpDoj();
        	
        	//added by gampa
        	
        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        	
        	String dojYear = sdf1.format(doj);
        	
        	Calendar docCal = new  GregorianCalendar();
        	
        	docCal.setTime(doj);
        	docCal.set(Calendar.YEAR, Integer.parseInt(dojYear)+1);
        	
        	String doC = sdfObj.format(docCal.getTime());
        	
        	//end by gampa
        	if(orgEmpMst.getEmpSrvcExp()!=null)
        	dateOfExpiry = sdfObj.format(orgEmpMst.getEmpSrvcExp());
        	bfEmpDtls.append("<empMapping>");
        	bfEmpDtls.append("<empFirstName>").append("<![CDATA[").append(orgEmpMst.getEmpFname()).append("]]>").append("</empFirstName>");
        	bfEmpDtls.append("<empMiddleName>").append("<![CDATA[").append(orgEmpMst.getEmpMname()!=null?orgEmpMst.getEmpMname():"").append("]]>").append("</empMiddleName>");
        	bfEmpDtls.append("<empLastName>").append("<![CDATA[").append(orgEmpMst.getEmpLname()).append("]]>").append("</empLastName>");
        	bfEmpDtls.append("<empDOB>").append("<![CDATA[").append(dateofBirth).append("]]>").append("</empDOB>");
        	bfEmpDtls.append("<empDOJ>").append("<![CDATA[").append(dateOfJoining).append("]]>").append("</empDOJ>");
        	bfEmpDtls.append("<empServiceExpiryDate>").append("<![CDATA[").append(dateOfExpiry).append("]]>").append("</empServiceExpiryDate>");
        	bfEmpDtls.append("<empSalutation>").append("<![CDATA[").append(orgEmpMst.getEmpPrefix()).append("]]>").append("</empSalutation>");
        	bfEmpDtls.append("<empId>").append(empID).append("</empId>");
        	
        	
        	//Added  by Varun
        	bfEmpDtls.append("<userId>").append(orgEmpMst.getOrgUserMst().getUserId()).append("</userId>");
        	//Ended by Varun
        	
//        	Added  by Gampa

        	bfEmpDtls.append("<doc>").append("<![CDATA[").append(doC).append("]]>").append("</doc>");
        	bfEmpDtls.append("<GradeName>").append(GradeId).append("</GradeName>");
        	//Ended by Gampa
        	
        	bfEmpDtls.append("</empMapping>");
        	//Map result = new HashMap();
            String empMapping = new AjaxXmlBuilder().addItem("ajax_key", bfEmpDtls.toString()).toString();
            logger.info("The String is:-"+empMapping);
            
            objectArgs.put("ajaxKey", empMapping);
            objectArgs.put("readFlag",true);
            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);
            resultObject.setViewName("ajaxData");
            logger.info("Record is been inserted");
        }
        catch(Exception e){
        	logger.info("Exception occures...");
        	e.printStackTrace();   		
    		resultObject.setResultValue(objectArgs);
    		resultObject.setThrowable(e);
    		resultObject.setResultCode(-1);
    		resultObject.setViewName("errorPage");
    	}		
    return resultObject;
	}

	public ResultObject getOrgEmpData(Map objectArgs) {
		logger.info("IN Emp Master Data getEmpData method");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv
				.getSessionFactory());
		EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv
				.getSessionFactory());
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		OrgEmpMst orgEmpMst = null;

		try {

			StringBuffer data = new StringBuffer();

			// Map loginDetailsMap
			// =(Map)session.getAttribute("loginDetailsMap");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			// long langId =
			// StringUtility.convertToLong(loginMap.get("langId").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			logger.info("**************************" + langId);
			List empNames = empinfodao.findorgEmpName("", langId);
			objectArgs.put("empList", empNames);
			resultObject.setViewName("showOrgEmpNames");
			resultObject.setResultValue(objectArgs);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return resultObject;

	}

	// by manoj for employee search services
	public ResultObject chkEmpDetail(Map objectArgs) {
		logger.info("IN chkEmpDetail Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		// LoginDetails objLoginDetails
		// =(LoginDetails)session.getAttribute("loginDetails");
		// long langId = objLoginDetails.getLangId();

		long empId = 0;
		String emp_allow_flag = "";

		StringBuffer propertyList = new StringBuffer();
		try {
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			if (voToService.get("Employee_ID_EmpSearch") != null
					&& !voToService.get("Employee_ID_EmpSearch").toString()
							.equals("")) {
				empId = Long.parseLong(voToService.get("Employee_ID_EmpSearch")
						.toString().toLowerCase());
			}
			if (voToService.get("Emp_allow") != null
					&& !voToService.get("Emp_allow").toString().equals("")) {

				emp_allow_flag = voToService.get("Emp_allow").toString();
			}
			EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,
					serv.getSessionFactory());
			List<HrEisEmpMst> empList = empinfodao.getListByColumnAndValue(
					"orgEmpMst.empId", empId);
			String emptype = "";
			logger.info("the size of emp list is " + empList.size());
			long hrEmpId = 0;

			/*
			 * // Start Added By Urvin Shah.
			 * 
			 * //CmnLanguageMstDao cmnLanguageMstDao = new
			 * CmnLanguageMstDaoImpl(
			 * CmnLanguageMst.class,serv.getSessionFactory()); CmnLookupMstDAO
			 * cmnLookupMstDAO = new
			 * CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			 * long religionId=0; long castId=0; long lookupId; String
			 * lookupDesc; CmnLookupMst cmnLookupMst=new CmnLookupMst();
			 * logger.info("Religion Id:_"+voToService.get("religionId"));
			 * religionId = voToService.get("religionId")!=null ||
			 * voToService.get("religionId").toString()!="-1"
			 * ?Long.parseLong(voToService.get("religionId").toString()):0;
			 * logger.info("Religion Id is:-"+religionId); castId =
			 * voToService.get("castId")!=null ||
			 * voToService.get("castId")!="-1"
			 * ?Long.parseLong(voToService.get("emp_caste_id").toString()):0;
			 * logger.info("Cast Id is:-"+castId);
			 * logger.info("Cast Id is:-"+castId); if(castId!=0){ List
			 * subCastList =
			 * cmnLookupMstDAO.getAllSiblingsByChildLookUpId(castId);
			 * logger.info("SubCastList size is:-"+subCastList.size());
			 * propertyList.append("<subCast-mapping>"); for(int
			 * i=0;i<subCastList.size();i++){ cmnLookupMst =
			 * (CmnLookupMst)subCastList.get(i); lookupId =
			 * cmnLookupMst.getLookupId(); lookupDesc =
			 * cmnLookupMst.getLookupDesc();
			 * propertyList.append("<lookup-Id>").append
			 * ("<![CDATA[").append(lookupId
			 * ).append("]]>").append("</lookup-Id>");//-1 if emp info not
			 * entered into hrEisEmpMSt
			 * propertyList.append("<lookup-Desc>").append
			 * ("<![CDATA[").append(lookupDesc
			 * ).append("]]>").append("</lookup-Desc>"); }
			 * propertyList.append("</subCast-mapping>"); } else if
			 * (religionId!=0){ List castList =
			 * cmnLookupMstDAO.getAllSiblingsByChildLookUpId(castId);
			 * logger.info("castList size is:-"+castList.size());
			 * propertyList.append("<Cast-mapping>"); for(int
			 * i=0;i<castList.size();i++){ cmnLookupMst =
			 * (CmnLookupMst)castList.get(i); lookupId =
			 * cmnLookupMst.getLookupId(); lookupDesc =
			 * cmnLookupMst.getLookupDesc();
			 * propertyList.append("<lookup-Id>").append
			 * ("<![CDATA[").append(lookupId
			 * ).append("]]>").append("</lookup-Id>");//-1 if emp info not
			 * entered into hrEisEmpMSt
			 * propertyList.append("<lookup-Desc>").append
			 * ("<![CDATA[").append(lookupDesc
			 * ).append("]]>").append("</lookup-Desc>"); }
			 * propertyList.append("</Cast-mapping>"); }
			 * 
			 * // End.
			 * 
			 * else {
			 */
			if (empList != null && empList.size() > 0) {
				HrEisEmpMst hrEisEmpMst = empList.get(0);
				hrEmpId = hrEisEmpMst.getEmpId();
				
				emptype = "" + hrEisEmpMst.getEmpType();
				
				logger.info(hrEmpId + " HrEisEmpMst Emp Name in service "
						+ hrEisEmpMst.getOrgEmpMst().getEmpFname());

				if (emp_allow_flag.equalsIgnoreCase("y")) {
					OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(
							HrEisOtherDtls.class, serv.getSessionFactory());
					List<HrEisOtherDtls> otherDtlsList = otherDao
							.getListByColumnAndValue("hrEisEmpMst", hrEisEmpMst);
					String otherDtlsFlag = "";
					long otherDtlsEmpId = 0;
					if (otherDtlsList != null && otherDtlsList.size() > 0) {
						otherDtlsEmpId = otherDtlsList.get(0).getHrEisEmpMst()
								.getEmpId();
						otherDtlsFlag = "y";// other detail for the emp is
											// entered

						logger
								.info("Emp Information is entered into otherDtls "
										+ otherDtlsList.get(0)
												.getotherCurrentBasic());
						propertyList.append("<emp-mapping>");
						propertyList.append("<emp-Id>").append(otherDtlsFlag)
								.append("</emp-Id>");
						propertyList.append("<emp-type>").append(emptype)
								.append("</emp-type>");
						propertyList.append("</emp-mapping>");
					} else {
						otherDtlsFlag = "n";// other detail for the emp is not
											// entered
						logger
								.info("Emp Information is not entered into otherDtls");
						propertyList.append("<emp-mapping>");
						propertyList.append("<emp-Id>").append(otherDtlsFlag)
								.append("</emp-Id>");
						propertyList.append("<emp-type>").append(emptype)
								.append("</emp-type>");
						propertyList.append("</emp-mapping>");
					}
				} else {
					propertyList.append("<emp-mapping>");
					propertyList.append("<emp-Id>").append(hrEmpId).append(
							"</emp-Id>");
					propertyList.append("<emp-type>").append(emptype).append(
							"</emp-type>");
					propertyList.append("</emp-mapping>");
				}
			} else {
				logger.info("Emp Information is not entered into hrEisEmpMSt");
				propertyList.append("<emp-mapping>");
				propertyList.append("<emp-Id>").append("-1")
						.append("</emp-Id>");// -1 if emp info not entered into
												// hrEisEmpMSt
				propertyList.append("<emp-type>").append(emptype).append(
						"</emp-type>");
				propertyList.append("</emp-mapping>");
			}
			// }
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key",
					propertyList.toString()).toString();
			
			result.put("ajaxKey", stateNameIdStr);
			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");
		} catch (Exception e) {
			logger.info("Exception occures...");
			e.printStackTrace();
			logger.info("Exception Occures.");
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}

	public ResultObject getCastSubCastData(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		try {
			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			logger.info("Lang Id is:-" + langId);
			long religionId = 0;
			long castId = 0;
			long lookupId;
			String lookupDesc;
			StringBuffer xmlString = new StringBuffer();
			CmnLookupMst cmnLookupMst = new CmnLookupMst();

			if (voToService.get("religionId") != null
					&& !voToService.get("religionId").toString().equals("-1"))
				religionId = Long.parseLong(voToService.get("religionId")
						.toString());
			// religionId = voToService.get("religionId")!=null ||
			// voToService.get("religionId").toString()!="-1"?Long.parseLong(voToService.get("religionId").toString()):0;
			castId = voToService.get("castId") != null
					&& !voToService.get("castId").toString().equals("-1") ? Long
					.parseLong(voToService.get("castId").toString())
					: 0;
			logger.info("Cast Id:-" + castId);
			if (castId != 0) {
				logger.info("Inside IF" + castId);
				List subCastList = cmnLookupMstDAO.getListByColumnAndValue(
						"parentLookupId", castId);
				xmlString.append("<subCast-mapping>");
				for (int i = 0; i < subCastList.size(); i++) {
					cmnLookupMst = (CmnLookupMst) subCastList.get(i);
					lookupId = cmnLookupMst.getLookupId();
					lookupDesc = cmnLookupMst.getLookupDesc();
					xmlString.append("<lookup-Id>").append("<![CDATA[").append(
							lookupId).append("]]>").append("</lookup-Id>");// -1
																			// if
																			// emp
																			// info
																			// not
																			// entered
																			// into
																			// hrEisEmpMSt
					xmlString.append("<lookup-Desc>").append("<![CDATA[")
							.append(lookupDesc).append("]]>").append(
									"</lookup-Desc>");
				}
				xmlString.append("</subCast-mapping>");
			} else {
				List castList = cmnLookupMstDAO.getListByColumnAndValue(
						"parentLookupId", religionId);
				logger.info("the List is:-" + castList.size());
				xmlString.append("<Cast-mapping>");
				for (int i = 0; i < castList.size(); i++) {
					cmnLookupMst = (CmnLookupMst) castList.get(i);
					lookupId = cmnLookupMst.getLookupId();
					lookupDesc = cmnLookupMst.getLookupDesc();
					xmlString.append("<lookup-Id>").append(lookupId).append(
							"</lookup-Id>");// -1 if emp info not entered into
											// hrEisEmpMSt
					xmlString.append("<lookup-Desc>").append(lookupDesc)
							.append("</lookup-Desc>");
				}
				xmlString.append("</Cast-mapping>");
			}
			Map result = new HashMap();
			String lookupDtlsStr = new AjaxXmlBuilder().addItem("ajax_key",
					xmlString.toString()).toString();
			logger.info("the string is " + xmlString.toString());
			result.put("ajaxKey", lookupDtlsStr);
			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");

		} catch (Exception e) {
			logger.info("Exception occures...");
			e.printStackTrace();
			logger.info("Exception Occures.");
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}
}
// end of to be changed
