package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.EmpDtlsCustomVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrEisEmpEndDate;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class OrgUserPostEndDateServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	public ResultObject getEmpDataForEndDate(Map objectArgs) {
		logger.info("===> getEmpDataForEndDate............. ");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		EmpCompMpgDAOImpl empCompMpgDAO = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class, serv.getSessionFactory());


		EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,
				serv.getSessionFactory());
		EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class,
				serv.getSessionFactory());
		
		Map voToService = (Map) objectArgs.get("voToServiceMap");

		String empAllRec = "";
		/*
		 * if (voToService.get("empAllRec") != null) empAllRec =
		 * voToService.get("empAllRec").toString(); logger.info("empAllRec " +
		 * empAllRec);
		 */
		if (voToService.get("empAllRec") != null) {
			empAllRec = voToService.get("empAllRec") != null ? (String) voToService
					.get("empAllRec") : "";
		}

		String EmpMpgFlag = "";
		long otherId = 0;
		long empId = 0;
		String FromBasicDtlsNew = voToService.get("FromBasicDtlsNew") != null ? (String) voToService
				.get("FromBasicDtlsNew") : "";

		if (FromBasicDtlsNew != null && FromBasicDtlsNew != "") {
			if ((voToService.get("Employee_ID_EmpInfoSearch") != null && !voToService
					.get("Employee_ID_EmpInfoSearch").equals("")))
				empId = Long.parseLong(voToService.get(
						"Employee_ID_EmpInfoSearch").toString());
			if ((voToService.get("otherId") != null && !voToService.get(
					"otherId").equals("")))
				otherId = Long.parseLong(voToService.get("otherId").toString());
		}

		logger.info("===> FromBasicDtlsNew :: " + FromBasicDtlsNew);
		logger.info("===> otherId :: " + otherId);

		objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
		objectArgs.put("otherId", otherId);

		// long gradeId=0;
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

			OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(
					OrgGradeMst.class, serv.getSessionFactory());

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
			// End
			String edit = "";
			String viewflag = "";
			String CloseFlag = "N";

			viewflag = StringUtility.getParameter("viewflag", request);
			logger.info("==> viewflag value of :: " + viewflag);

			CloseFlag = StringUtility.getParameter("CloseFlag", request);
			logger.info("==> CloseFlag value of :: " + CloseFlag);

			if (voToService.get("edit") != null) {
				edit = (String) voToService.get("edit").toString();
			}

			logger.info("the value of edit is " + edit);
			logger.info("====> the value of edit is :: " + edit);
			logger.info("===> EmpMpgFlag :: " + EmpMpgFlag);
			logger.info("===> empAllRec :: " + empAllRec);
			logger.info("===> viewflag :: " + viewflag);

			if (!edit.equals(null) && edit.equalsIgnoreCase("Y")
					|| viewflag.equalsIgnoreCase("Y")) {
				logger.info("I m in edit mode ");
				if (!viewflag.equalsIgnoreCase("Y")) {
					empId = (voToService.get("empId").toString() != null && !voToService
							.get("empId").toString().equals("")) ? Long
							.parseLong(voToService.get("empId").toString()) : 0;
				} else {

					empId = Long.valueOf(StringUtility.getParameter("hdnEmpId",
							request));
					logger.info("==> viewflag value of :: " + viewflag);
				}

				logger.info("The Employee Id is:-" + empId);

				// List actionList = empinfodao.getEmpIdData(empId);
				HrEisEmpMst hreiempMst = null;
				if (empId != 0)
					hreiempMst = empinfodao.read(empId);

				OrgEmpMst orgEmpMstgu = new OrgEmpMst();
				orgEmpMstgu.setEmpId(hreiempMst.getOrgEmpMst().getEmpId());
				OrgGradeMst orgGradeMst = null;
				long GradeId = 0;
				OrgEmpMst empVO = orgEmpDAOImpl.read(hreiempMst.getOrgEmpMst()
						.getEmpId());
				//
				HrPayGpfBalanceDtls hrpaygpfDtls = new HrPayGpfBalanceDtls();

				EmpGpfDtlsDAOImpl empGpfDAo = new EmpGpfDtlsDAOImpl(
						HrPayGpfBalanceDtls.class, serv.getSessionFactory());

				if (empVO.getOrgUserMst().getUserId() != 0) {
					long userID = empVO.getOrgUserMst().getUserId();
					List<HrPayGpfBalanceDtls> hrPayGpfBalanceDtls = empGpfDAo
							.getListByColumnAndValue("userId", userID);
					if (hrPayGpfBalanceDtls != null
							&& hrPayGpfBalanceDtls.size() > 0
							&& hrPayGpfBalanceDtls.get(0) != null) {
						hrpaygpfDtls = hrPayGpfBalanceDtls.get(0);

						if (hrpaygpfDtls.getOrgGradeMst() != null) {
							GradeId = hrpaygpfDtls.getOrgGradeMst()
									.getGradeId();
						} else {
							GradeId = empVO.getOrgGradeMst().getGradeId();
						}
						orgGradeMst = orgGradeMstDaoImpl.read(GradeId);

					}
				}
				hrpaygpfDtls.setOrgGradeMst(orgGradeMst);

				// orgGradeMst.setGradeId(hreiempMst.getOrgEmpMst().getOrgGradeMst().getGradeId());
				// hrEisEmpMst.setEmpBirthPlace(hreiempMst.getEmpBirthPlace());

				// Ended Added By Javed

				HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
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

				// End.
				objectArgs.put("hrEisEmpMst", hrEisEmpMst);
				resultObject.setResultCode(ErrorConstants.SUCCESS);

				resultObject.setViewName("EmpEndDate");

				// resultObject.setViewName("empViewListEmpEndDate");

			} else {
				logger.info("===> ForViewName FromBasicDtlsNew No");
				resultObject.setViewName("empViewListEmpEndDate");
			}

			if (empId != 0) {
				logger.info("====> Before DesgName empId :: " + empId);
				String DesgName = empinfodao.getDesigNameFromEmpId(String
						.valueOf(empId));
				logger.info("====> DesgName :: " + DesgName);
				objectArgs.put("DesgName", DesgName);
			}
			/*
			 * if(empId!=0) {
			 * logger.info("====> Before Post Name empId :: "+empId); String
			 * PostName =
			 * empinfodao.getPostNameFromEmpId(String.valueOf(empId));
			 * logger.info("====> PostName :: "+PostName);
			 * objectArgs.put("PostName", PostName); }
			 */
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			List ListofReason = cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("EmpEndSpc", langId);
			logger.info("===> ListofReason :: " + ListofReason.size());
			objectArgs.put("ListofReason", ListofReason);

			logger.info("====> before in empIdStr!=null............ "
					+ empIdStr);
			List actionList = new ArrayList();
			if (empIdStr != null && !empIdStr.equals("")) // if Emp Search is
															// yes
			{

				// Added by mrugesh
				Map loginDetails = (Map) objectArgs.get("baseLoginMap");
				long languageId = 1;
				cmnLanguageMst = cmnLanguageMstDaoImpl.read(languageId);
				// Ended by mrugesh

				empId = Long.parseLong(empIdStr);
				logger.info("the emp id from else if is " + empId);
				EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,
						serv.getSessionFactory());
				OrgEmpMst orgEmpInfoMst = orgEmpDao.getEngGujEmployee(
						orgEmpDao.read(empId), languageId);

				actionList = empinfodao.getHrEmpFromOrgEmp(empId, languageId,
						locationCode);

				List<EmpDtlsCustomVO> empList = new ArrayList();

				for (int i = 0; i < actionList.size(); i++) {
					EmpDtlsCustomVO empdtlsCustom = new EmpDtlsCustomVO();

					Object[] row = (Object[]) actionList.get(i);
					empdtlsCustom.setEmpId(((Long) (row[0])).longValue());
					empdtlsCustom.setEmpName(row[1].toString());
					if (row[2] != null)
						empdtlsCustom.setEmpType(((Long) (row[2])).longValue());
					if(row[3].equals('Y'))
					{
						empdtlsCustom.setDcpsorgpf("DCPS");
					}else{
						empdtlsCustom.setDcpsorgpf("GPF");
					}					
					empList.add(empdtlsCustom);
				}
				logger.info("====> in if empIdStr!=null............ "
						+ empList.size());
				objectArgs.put("actionList", empList);

			} else {

				logger.info("The locationcode is----->>>" + locationCode);
				// actionList = empinfodao.getAllEmpDatabyDept(0,
				// locationCode,langId);// empinfodao.getAllEmpData();

				actionList = empinfodao.getAllEmpDatabyDept(locationCode,
						langId);

				logger.info("the size of list is " + actionList.size());
				List<EmpDtlsCustomVO> empList = new ArrayList();

				for (int i = 0; i < actionList.size(); i++) {
					EmpDtlsCustomVO empdtlsCustom = new EmpDtlsCustomVO();

					Object[] row = (Object[]) actionList.get(i);
					empdtlsCustom.setEmpId(((Long) (row[0])).longValue());
					empdtlsCustom.setEmpName(row[1].toString());
					if (row[2] != null)
						empdtlsCustom.setEmpType(((Long) (row[2])).longValue());
					if(row[3].equals("DCPS"))
					{
						empdtlsCustom.setDcpsorgpf("DCPS");
					}else{
						empdtlsCustom.setDcpsorgpf("GPF");
					}
					empList.add(empdtlsCustom);
				}
				logger.info("====> in else empIdStr==null............ "
						+ empList.size());
				objectArgs.put("actionList", empList);
				objectArgs.put("ViewFlag", "Ture");
			}

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			this.logger.info("U are out of the way:-");	
		      this.logger.info("Values from Map before passing " + objectArgs.get("MappedDeduListSize") + " 2 " + 	
		          objectArgs.get("MappedallowListSize"));
		      
		      if (edit.equalsIgnoreCase("Y") && empId != 0L) {	
		          objectArgs.put("isDCPS", Boolean.valueOf(empCompMpgDAO.isDCPS(empId)));	
		          objectArgs.put("DCPSorGPF", Character.valueOf(empCompMpgDAO.isDCPS1(empId)));	
		          objectArgs.put("DCG", empCompMpgDAO.isDCPS2(empId));	
		          objectArgs.put("isNewConfig", Boolean.valueOf(empCompMpgDAO.isNewConfig(empId)));	
		          objectArgs.put("isDCPSStop", Boolean.valueOf(empCompMpgDAO.isDCPSStop(empId)));	
		          this.logger.info(" Employee id " + empId + " isDCPS= " + objectArgs.get("isDCPS") + " isNewConfig=" + 	
		              objectArgs.get("isNewConfig"));	
		        } else {	
		          objectArgs.put("isDCPS", Boolean.valueOf(false));	
		          objectArgs.put("isDCPSStop", Boolean.valueOf(false));	
		          objectArgs.put("isNewConfig", Boolean.valueOf(false));	
		        } 

			resultObject.setResultValue(objectArgs);
		} catch (Exception e) {

			logger.error("Error is: " + e.getMessage());
		}

		return resultObject;
	}

	public ResultObject InsertEmpEndDateService(Map ObjectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) ObjectArgs.get("serviceLocator");
		// HttpServletRequest request = (HttpServletRequest)
		// ObjectArgs.get("requestObj");

		try {
			logger.info("===>  in InsertEmpEndDateService service..........");
			Date EmoEndDate = null;
			long hdnEmpId = 0;

			Date OrderDated = null;
			long lReasonForEndid = 0;

			String lEligiPension = "";
			String lEligiGratuity = "";

			String RefLetterNo = "";
			String Remarks = "";

			Map loginDetailsMap = (Map) ObjectArgs.get("baseLoginMap");

			/*
			 * String CloseFlag="N"; CloseFlag =
			 * StringUtility.getParameter("CloseFlag", request);
			 * logger.info("==> CloseFlag value of :: "+CloseFlag);
			 */

			if (ObjectArgs.get("EmpEndDate") != null) {
				EmoEndDate = (Date) (ObjectArgs.get("EmpEndDate") != null ? ObjectArgs
						.get("EmpEndDate") : new Date());
				logger.info("==> in service :: " + EmoEndDate);
			}

			if (ObjectArgs.get("OrderDated") != null) {
				OrderDated = (Date) (ObjectArgs.get("OrderDated") != null ? ObjectArgs
						.get("OrderDated") : new Date());
				logger.info("==> in service OrderDated:: " + OrderDated);
			}

			if (ObjectArgs.get("Remarks") != null) {
				Remarks = ObjectArgs.get("Remarks").toString();
				logger.info("==> in service Remarks:: " + Remarks);
			}

			if (ObjectArgs.get("lReasonForEndid") != null) {
				lReasonForEndid = Long.parseLong(ObjectArgs.get(
						"lReasonForEndid").toString());
				logger.info("==> in service lReasonForEndid:: "
						+ lReasonForEndid);
			}
			if (ObjectArgs.get("lEligiPension") != null) {
				lEligiPension = ObjectArgs.get("lEligiPension").toString();
				logger.info("==> in service lEligiPension:: " + lEligiPension);
			}
			if (ObjectArgs.get("lEligiGratuity") != null) {
				lEligiGratuity = ObjectArgs.get("lEligiGratuity").toString();
				logger.info("==> in service lEligiGratuity:: " + lEligiGratuity);
			}
			if (ObjectArgs.get("RefLetterNo") != null) {
				RefLetterNo = ObjectArgs.get("RefLetterNo").toString();
				logger.info("==> in service RefLetterNo:: " + RefLetterNo);
			}

			if (ObjectArgs.get("hdnEmpId") != null) {
				hdnEmpId = Long
						.parseLong(ObjectArgs.get("hdnEmpId").toString());
				logger.info("==> hdnEmpId :: " + hdnEmpId);
			}

			EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,
					serv.getSessionFactory());

			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost")
					.toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(
					OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMstpostID = orgPostMstDaoImpl.read(postId);

			GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(
					CmnLookupMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			CmnLookupMst cmnLookupMstAllowID = (CmnLookupMst) genDao
					.read(lReasonForEndid);

			EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,
					serv.getSessionFactory());
			HrEisEmpMst hrEisEmpMst = empinfoDao.read(hdnEmpId);
			logger.info("Hidden Emp id is " + hdnEmpId);

			long userId = StringUtility.convertToLong(loginDetailsMap.get(
					"userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(
					OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long locId = Long.parseLong(loginDetailsMap.get("locationId")
					.toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMstVO = cmnLocationMstDaoImpl.read(locId);
			long lEligiPensionID = 0;
			long lEligiGratuityID = 0;

			if (lEligiPension.equalsIgnoreCase("true"))
				lEligiPensionID = 1;
			else
				lEligiPensionID = 0;

			if (lEligiGratuity.equalsIgnoreCase("true"))
				lEligiGratuityID = 1;
			else
				lEligiGratuityID = 0;

			HrEisEmpEndDate hrEisempEndDate = new HrEisEmpEndDate();

			IdGenerator idGen = new IdGenerator();
			Long id = idGen.PKGeneratorWODBLOC("HR_EIS_EMP_END_DATE",
					ObjectArgs);
			// Long id=
			// IDGenerateDelegate.getNextId("HR_EIS_EMP_END_DATE",ObjectArgs);
			logger.info(" HR_EIS_EMP_END_DATE ******************the id generated is "
					+ id);
			hrEisempEndDate.setEndDateId(id);
			hrEisempEndDate.setHrEisEmpMst(hrEisEmpMst);
			hrEisempEndDate.setCmnLookupMstReason(cmnLookupMstAllowID);
			hrEisempEndDate.setPension(lEligiPensionID);
			hrEisempEndDate.setGratuity(lEligiGratuityID);
			hrEisempEndDate.setOrderNo(RefLetterNo);
			hrEisempEndDate.setRemarks(Remarks);
			hrEisempEndDate.setCreatedByPostID(orgPostMstpostID);
			hrEisempEndDate.setEndDate(EmoEndDate);
			hrEisempEndDate.setOrgUserMstByCreatedBy(orgUserMst);
			hrEisempEndDate.setCreatedDate(new Date());
			hrEisempEndDate.setOrderDate(OrderDated);
			hrEisempEndDate.setCmnLocationMst(cmnLocationMstVO);
			serv.getSessionFactory().getCurrentSession()
					.saveOrUpdate(hrEisempEndDate);

			// for change record

			long empPostId = 0;

			BankMasterDAOImpl bankMasterDAOImpl = new BankMasterDAOImpl(
					MstBankPay.class, serv.getSessionFactory());
			empPostId = bankMasterDAOImpl.getEmpPostIdFromEisEmpID(hdnEmpId);
			// System.out.println("empPostId::::::"+empPostId);
			Map mapForChangedRecords = ObjectArgs;
			Map loginMap = (Map) ObjectArgs.get("baseLoginMap");
			long userIdFromVogen = Long.valueOf(loginDetailsMap.get("userId")
					.toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId")
					.toString());
			long loggedInpostId = StringUtility.convertToLong(loginMap.get(
					"primaryPostId").toString());

			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(
					CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
			OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(loggedInpostId);

			mapForChangedRecords.put("changedPostId", empPostId);
			mapForChangedRecords.put("changedEmpId", hdnEmpId);
			mapForChangedRecords.put("serviceLocator", serv);
			mapForChangedRecords.put("locId", locId);
			mapForChangedRecords.put("OrgPostMst", orgPostMst);
			mapForChangedRecords.put("OrgUserMst", orgUserMstNew);
			mapForChangedRecords.put("cmnDatabaseMst", cmnDatabaseMst);
			GenerateBillServiceHelper generateBillServiceHelper = new GenerateBillServiceHelper();
			long changedRecordPK = generateBillServiceHelper
					.insertChangedRecord(mapForChangedRecords);
			// System.out.println("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"
					+ changedRecordPK);

			// ended here
			// EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,
			// serv.getSessionFactory());
			OrgUserpostRlt orguserpostrlt = empinfodao
					.getuserpostrtlfromEmpId(hdnEmpId);

			logger.info("===> orguserpostrlt :: "
					+ orguserpostrlt.getEmpPostId());
			orguserpostrlt.setEndDate(EmoEndDate);
			orguserpostrlt.setActivateFlag(0);
			if (orguserpostrlt != null) {
				changedRecordPK = generateBillServiceHelper
						.insertChangedRecord(mapForChangedRecords);
				serv.getSessionFactory().getCurrentSession()
						.saveOrUpdate(orguserpostrlt);
				ObjectArgs.put("msg", "Updated Successfully");
			}
			// ObjectArgs.put("msg","Updated Successfully");

			String edit = "";
			String viewflag = "";
			String empIdStr = "";
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap
					.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null && !cmnLocationMst
					.getLocationCode().trim().equals("")) ? cmnLocationMst
					.getLocationCode() : "";
			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
					CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			logger.info("====> before in empIdStr!=null............ "
					+ empIdStr);
			List actionList = new ArrayList();
			if (empIdStr != null && !empIdStr.equals("")) // if Emp Search is
															// yes
			{

				// Added by mrugesh
				Map loginDetails = (Map) ObjectArgs.get("baseLoginMap");
				long languageId = 1;
				cmnLanguageMst = cmnLanguageMstDaoImpl.read(languageId);
				// Ended by mrugesh

				hdnEmpId = Long.parseLong(empIdStr);
				logger.info("the emp id from else if is " + hdnEmpId);
				EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,
						serv.getSessionFactory());
				OrgEmpMst orgEmpInfoMst = orgEmpDao.getEngGujEmployee(
						orgEmpDao.read(hdnEmpId), languageId);

				actionList = empinfodao.getHrEmpFromOrgEmp(hdnEmpId,
						languageId, locationCode);

				List<EmpDtlsCustomVO> empList = new ArrayList();

				for (int i = 0; i < actionList.size(); i++) {
					EmpDtlsCustomVO empdtlsCustom = new EmpDtlsCustomVO();

					Object[] row = (Object[]) actionList.get(i);
					empdtlsCustom.setEmpId(((Long) (row[0])).longValue());
					empdtlsCustom.setEmpName(row[1].toString());
					if (row[2] != null)
						empdtlsCustom.setEmpType(((Long) (row[2])).longValue());
					empList.add(empdtlsCustom);
				}
				logger.info("====> in if empIdStr!=null............ "
						+ empList.size());
				ObjectArgs.put("actionList", empList);

			} else {

				logger.info("The locationcode is----->>>" + locationCode);
				// actionList = empinfodao.getAllEmpDatabyDept(0,
				// locationCode,langId);// empinfodao.getAllEmpData();

				actionList = empinfodao.getAllEmpDatabyDept(locationCode,
						langId);

				logger.info("the size of list is " + actionList.size());
				List<EmpDtlsCustomVO> empList = new ArrayList();

				for (int i = 0; i < actionList.size(); i++) {
					EmpDtlsCustomVO empdtlsCustom = new EmpDtlsCustomVO();

					Object[] row = (Object[]) actionList.get(i);
					empdtlsCustom.setEmpId(((Long) (row[0])).longValue());
					empdtlsCustom.setEmpName(row[1].toString());
					if (row[2] != null)
						empdtlsCustom.setEmpType(((Long) (row[2])).longValue());
					empList.add(empdtlsCustom);
				}
				logger.info("====> in else empIdStr==null............ "
						+ empList.size());
				ObjectArgs.put("actionList", empList);
				ObjectArgs.put("ViewFlag", "Ture");
			}

			resultObject.setViewName("empViewListEmpEndDate");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(ObjectArgs);

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;

	}

}
