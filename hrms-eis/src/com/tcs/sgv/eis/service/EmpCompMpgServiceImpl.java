package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.EmpDtlsCustomVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompGrpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayCompGrpMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class EmpCompMpgServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings({ "unchecked", "deprecation" })
	public ResultObject getEmpDataForMapping(Map objectArgs) {

		logger.info("===> getEmpDataForMapping............. ");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		EmpCompMpgDAOImpl empCompMpgDAO = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class, serv.getSessionFactory());

		EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
		EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
		Map voToService = (Map) objectArgs.get("voToServiceMap");

		String empAllRec = "";
		/*
		 * if (voToService.get("empAllRec") != null) empAllRec =
		 * voToService.get("empAllRec").toString(); logger.info("empAllRec " +
		 * empAllRec);
		 */
		if (voToService.get("empAllRec") != null) {
			empAllRec = voToService.get("empAllRec") != null ? (String) voToService.get("empAllRec") : "";
		}

		String EmpMpgFlag = "";
		long otherId = 0;
		long empId = 0;
		String FromBasicDtlsNew = voToService.get("FromBasicDtlsNew") != null
				? (String) voToService.get("FromBasicDtlsNew")
				: "";

		if (FromBasicDtlsNew != null && FromBasicDtlsNew != "") {
			if ((voToService.get("Employee_ID_EmpInfoSearch") != null
					&& !voToService.get("Employee_ID_EmpInfoSearch").equals("")))
				empId = Long.parseLong(voToService.get("Employee_ID_EmpInfoSearch").toString());
			if ((voToService.get("otherId") != null && !voToService.get("otherId").equals("")))
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
					&& !voToService.get("Employee_ID_EmpInfoSearch").toString().equals("")) {
				empIdStr = (String) voToService.get("Employee_ID_EmpInfoSearch");

				logger.info("the emp id from search employee " + empIdStr);
				// end by manoj for employee search
			}

			if (voToService.get("EmpMpg") != null && !voToService.get("EmpMpg").toString().equals("")) {

				EmpMpgFlag = (String) voToService.get("EmpMpg");
				logger.info("EmpMpgFlag is:-->" + EmpMpgFlag);
			}

			/*
			 * StringBuffer data = new StringBuffer(); IdGenerator idGen = new
			 * IdGenerator();
			 */
			// Map loginDetailsMap
			// =(Map)session.getAttribute("loginDetailsMap");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());

			/*
			 * long userId = StringUtility.convertToLong(loginDetailsMap.get(
			 * "userId").toString()); OrgUserMstDaoImpl orgUserMstDaoImpl = new
			 * OrgUserMstDaoImpl( OrgUserMst.class, serv.getSessionFactory());
			 */
			// OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			// long langId =
			// StringUtility.convertToLong(loginMap.get("langId").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			/*
			 * CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
			 * CmnLanguageMst.class, serv.getSessionFactory());
			 */
			// CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			// long locationId =
			// StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			// Start Added Bu Urvin.
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null
					&& !cmnLocationMst.getLocationCode().trim().equals("")) ? cmnLocationMst.getLocationCode() : "";
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

			if (!edit.equals(null) && edit.equalsIgnoreCase("Y") || viewflag.equalsIgnoreCase("Y")) {
				logger.info("I m in edit mode ");
				if (!viewflag.equalsIgnoreCase("Y")) {
					empId = (voToService.get("empId").toString() != null
							&& !voToService.get("empId").toString().equals(""))
									? Long.parseLong(voToService.get("empId").toString())
									: 0;
				} else {

					empId = Long.valueOf(StringUtility.getParameter("hdnEmpId", request));
					logger.info("==> viewflag value of :: " + viewflag);
				}

				logger.info("The Employee Id is:-" + empId);

				// List actionList = empinfodao.getEmpIdData(empId);
				// PsrPostMpgDAOImpl psrDAOImp = new
				// PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());

				HrEisEmpMst hreiempMst = null;
				if (empId != 0)
					hreiempMst = empinfodao.read(empId);

				OrgEmpMst orgEmpMstgu = new OrgEmpMst();
				orgEmpMstgu.setEmpId(hreiempMst.getOrgEmpMst().getEmpId());
				OrgGradeMst orgGradeMst = null;
				long GradeId = 0;
				OrgEmpMst empVO = orgEmpDAOImpl.read(hreiempMst.getOrgEmpMst().getEmpId());
				//
				HrPayGpfBalanceDtls hrpaygpfDtls = new HrPayGpfBalanceDtls();

				EmpGpfDtlsDAOImpl empGpfDAo = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,
						serv.getSessionFactory());

				if (empVO.getOrgUserMst().getUserId() != 0) {
					long userID = empVO.getOrgUserMst().getUserId();
					List<HrPayGpfBalanceDtls> hrPayGpfBalanceDtls = empGpfDAo.getListByColumnAndValue("userId", userID);
					if (hrPayGpfBalanceDtls != null && hrPayGpfBalanceDtls.size() > 0
							&& hrPayGpfBalanceDtls.get(0) != null) {
						hrpaygpfDtls = hrPayGpfBalanceDtls.get(0);

						if (hrpaygpfDtls.getOrgGradeMst() != null) {
							GradeId = hrpaygpfDtls.getOrgGradeMst().getGradeId();
						} else {
							GradeId = empVO.getOrgGradeMst().getGradeId();
						}
						orgGradeMst = orgGradeMstDaoImpl.read(GradeId);

					}
				}
				hrpaygpfDtls.setOrgGradeMst(orgGradeMst);

				// orgGradeMst.setGradeId(hreiempMst.getOrgEmpMst().getOrgGradeMst().getGradeId());
				// hrEisEmpMst.setEmpBirthPlace(hreiempMst.getEmpBirthPlace());

				logger.info("=====> Strar...............");
				logger.info("===> Empid :: " + empId);
				long loggedInpostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());

				logger.info("====> loggedInpostId :: " + loggedInpostId);
				DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class,
						serv.getSessionFactory());
				List<OrgPostDetailsRlt> locIds = deptcomoMpgDAOImpl.getLocIds(loggedInpostId);
				long locId = locIds.get(0).getCmnLocationMst().getLocId();
				logger.info("insertSchemeDtls location id ::" + locId);

				String locname = locIds.get(0).getCmnLocationMst().getLocName();
				logger.info("==> locname :: " + locname);

				EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,
						serv.getSessionFactory());
				DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,
						serv.getSessionFactory());
				// List<HrPayAllowTypeMst> allowList = empAllwMapDAO.getAllAllowanceType();
				// logger.info("==> in insertDeptCompMPGDtls allowList.size::" +
				// allowList.size());

				// List<HrPayDeducTypeMst> deducActionList = empDuducDtlsDAO.getDeductionType();
				// logger.info("==> in insertDeptCompMPGDtls deducActionList.size::" +
				// deducActionList.size());

				// objectArgs.put("AllowSize", allowList.size());
				// objectArgs.put("DeductionSize",deducActionList.size());

				// objectArgs.put("allowList", allowList);
				// objectArgs.put("deducActionList", deducActionList);
				objectArgs.put("locname", locname);

				/// Code For Getting value of Payid form Emp ID
				long hdnPayCommission = -1;
				if (!CloseFlag.equalsIgnoreCase("Y")) {
					if (viewflag.equalsIgnoreCase("Y")) {
						hdnPayCommission = Long.valueOf(StringUtility.getParameter("hdnPayCommission", request));
						logger.info("==> putting value of :: " + hdnPayCommission);

					}
				}

				// EmpCompMpgDAOImpl empCompMpgDAO = new
				// EmpCompMpgDAOImpl(HrEisEmpCompMpg.class,serv.getSessionFactory());
				List<HrEisOtherDtls> hreisOtherDtlsList = empCompMpgDAO.getEmpPayID(empId);

				logger.info("===> hreisOtherDtlsList.size :: " + hreisOtherDtlsList.size());
				StringBuffer lStrPayId = new StringBuffer();
				long sevenPcBasic = 0; /* Added By Shivram */
				for (HrEisOtherDtls xyz : hreisOtherDtlsList) {
					if (xyz.getHrEisSgdMpg() != null) {
						lStrPayId.append(String
								.valueOf(xyz.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId()));
						sevenPcBasic = Long.valueOf(xyz.getOtherCurrentSevenBasic()); /* Added By Shivram */
					}
				}
				logger.info("===> lStrPayId :: " + lStrPayId.toString());
				long payid = 0;
				if (!(lStrPayId.toString()).equals("")) {
					payid = Long.valueOf(lStrPayId.toString());
				}
				if (!(lStrPayId.toString()).equals("") && !viewflag.equalsIgnoreCase("Y")) {
					hdnPayCommission = payid;
				}
				if ((!lStrPayId.toString().equals("")) && (!viewflag.equalsIgnoreCase("Y"))
						&& (sevenPcBasic > 0)) { /* Added By Shivram */
					hdnPayCommission = 2500347;
				}

				logger.info("==> hdnPayCommission :: " + hdnPayCommission);

				objectArgs.put("PayCommissionID", hdnPayCommission);
				objectArgs.put("flag", payid);

				/// End Code For Getting value of Payid form Emp ID

				// =========> Code For Getting Mapped Allowance Comopent Mapped With Dept Comp
				// Mpg

				List<HrPayLocComMpg> MappedComoId = empAllwMapDAO.getMappedCompoIDfromCompMpg(locId);
				StringBuffer MappedCompoId = new StringBuffer();
				for (HrPayLocComMpg xyz : MappedComoId) {
					if (xyz.getCmnLookupMst() != null) {
						MappedCompoId.append(String.valueOf(xyz.getCompId()) + ",");
					}
				}

				if (MappedCompoId.length() != 0) {
					MappedCompoId.deleteCharAt(MappedCompoId.length() - 1);
				}
				logger.info("====> str :: " + MappedComoId.size() + "=====> " + MappedCompoId);

				String lStrMappedComoId = MappedCompoId.toString();
				if (lStrMappedComoId.equals(null) || lStrMappedComoId.equals("")) {
					lStrMappedComoId = "0";
				}

				logger.info("==> Passing value Allow:: " + lStrMappedComoId);

				// =========> End Code For Getting Mapped Allowance Comopent Mapped With Dept
				// Comp Mpg

				// =========> Code For Getting Mapped Deduction Comopent Mapped With Dept Comp
				// Mpg

				List<HrPayLocComMpg> MappedCompoIdForDedu = empDuducDtlsDAO.getMappedCompoIDfromCompMpg(locId);
				StringBuffer MappedCompoIdDedu = new StringBuffer();
				for (HrPayLocComMpg xyz : MappedCompoIdForDedu) {
					if (xyz.getCmnLookupMst() != null) {
						MappedCompoIdDedu.append(String.valueOf(xyz.getCompId()) + ",");
					}
				}

				if (MappedCompoIdDedu.length() != 0) {
					MappedCompoIdDedu.deleteCharAt(MappedCompoIdDedu.length() - 1);
				}
				logger.info("====> str :: " + MappedCompoIdForDedu.size() + "=====> " + MappedCompoIdDedu);

				String lStrMappedComoIdDedu = MappedCompoIdDedu.toString();
				if (lStrMappedComoIdDedu.equals(null) || lStrMappedComoIdDedu.equals("")) {
					lStrMappedComoIdDedu = "0";
				}

				logger.info("===> For checking payCommonissionId :: " + hdnPayCommission);
				// Javed
				if (hdnPayCommission == 2500340 || hdnPayCommission == 2500346) {
					hdnPayCommission = 2500340;

				} else {
					hdnPayCommission = 2500341;
				}
				logger.info("===> After For checking payCommonissionId :: " + lStrMappedComoId);

				List<HrPayAllowTypeMst> MappedallowList = empAllwMapDAO.getAllowListfromDeptCompMpg(hdnPayCommission,
						lStrMappedComoId);
				logger.info("====> MappedallowList Size:: " + MappedallowList.size());
				objectArgs.put("MappedallowListSize", MappedallowList.size());
				objectArgs.put("MappedallowList", MappedallowList);

				List<HrPayDeducTypeMst> MappedDeduList = empDuducDtlsDAO.getDeduListfromDeptCompMpg(hdnPayCommission,
						lStrMappedComoIdDedu);
				logger.info("====> MappedDeduList Size:: " + MappedDeduList.size());
				objectArgs.put("MappedDeduListSize", MappedDeduList.size());
				objectArgs.put("MappedDeduList", MappedDeduList);
				Date WEFDate = empCompMpgDAO.getWEFDataFromEmpId(empId);
				String remarks = empCompMpgDAO.getRemarks(empId);
				logger.info("====> For View in Service WEFDate :: " + WEFDate);
				objectArgs.put("WEFDate", WEFDate);
				objectArgs.put("remarks", remarks);
				// Ended Added By Javed

				HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
				hrEisEmpMst.setEmpGender(hreiempMst.getEmpGender());
				hrEisEmpMst.setEmpId(hreiempMst.getEmpId());
				// hrEisEmpMst.setEmpNativePlace(hreiempMst.getEmpNativePlace());
				hrEisEmpMst.setCmnLanguageMst(hreiempMst.getCmnLanguageMst());
				if (langId != 1) {
					CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,
							serv.getSessionFactory());
					String cmnLookupName = cmnLookupDao.read(hreiempMst.getEmpRecruitSrc()).getLookupName();
					CmnLookupMst cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(cmnLookupName, langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setEmpRecruitSrc(cmnLookupMst.getLookupId());

					/*
					 * cmnLookupName=cmnLookupDao.read(Long.parseLong(hreiempMst.
					 * getEmpReligionId())).getLookupName();
					 */
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							hreiempMst.getCmnLookupMstByEmpReligionId().getLookupId(), langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setCmnLookupMstByEmpReligionId(cmnLookupMst);

					cmnLookupName = cmnLookupDao.read(Long.parseLong(hreiempMst.getEmpSalDisbMd())).getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(cmnLookupName, langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setEmpSalDisbMd(String.valueOf(cmnLookupMst.getLookupId()));

					cmnLookupName = cmnLookupDao.read((hreiempMst.getEmpType())).getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(cmnLookupName, langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setEmpType((cmnLookupMst.getLookupId()));

					// cmnLookupName=cmnLookupDao.read((hreiempMst.getEmpCategory())).getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							hreiempMst.getCmnLookupMstByEmpCategoryId().getLookupId(), langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setCmnLookupMstByEmpCategoryId(cmnLookupMst);

					cmnLookupName = cmnLookupDao.read(Long.parseLong((hreiempMst.getEmpStatusId()))).getLookupName();
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(cmnLookupName, langId);
					if (cmnLookupMst != null)
						hrEisEmpMst.setEmpStatusId(String.valueOf((cmnLookupMst.getLookupId())));
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							hreiempMst.getCmnLookupMstByEmpCasteId().getLookupId(), langId);
					if (cmnLookupMst != null) {
						hrEisEmpMst.setCmnLookupMstByEmpCasteId(cmnLookupMst);
					}
					cmnLookupMst = new CmnLookupMst();
					cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
							hreiempMst.getCmnLookupMstByEmpSubCasteId().getLookupId(), langId);
					if (cmnLookupMst != null) {
						hrEisEmpMst.setCmnLookupMstByEmpSubCasteId(cmnLookupMst);
					}
				} else {
					hrEisEmpMst.setEmpRecruitSrc(hreiempMst.getEmpRecruitSrc());
					hrEisEmpMst.setCmnLookupMstByEmpReligionId(hreiempMst.getCmnLookupMstByEmpReligionId());
					hrEisEmpMst.setEmpSalDisbMd(hreiempMst.getEmpSalDisbMd());
					hrEisEmpMst.setEmpType(hreiempMst.getEmpType());
					hrEisEmpMst.setEmpStatusId(hreiempMst.getEmpStatusId());
					// hrEisEmpMst.setEmpCategory(hreiempMst.getEmpCategory());
					hrEisEmpMst.setCmnLookupMstByEmpCategoryId(hreiempMst.getCmnLookupMstByEmpCategoryId());
				}

				hrEisEmpMst.setEmpHobby(hreiempMst.getEmpHobby());
				if (hreiempMst.getCmnCountryMstByEmpNationality() != null)
					hrEisEmpMst.setCmnCountryMstByEmpNationality(hreiempMst.getCmnCountryMstByEmpNationality());
				hrEisEmpMst.setCmnLookupMstByEmpCasteId(hreiempMst.getCmnLookupMstByEmpCasteId());
				hrEisEmpMst.setEmpConfDueDt(hreiempMst.getEmpConfDueDt());
				// hrEisEmpMst.setEmpSubCaste(hreiempMst.getEmpSubCaste());
				hrEisEmpMst.setCmnLookupMstByEmpSubCasteId(hreiempMst.getCmnLookupMstByEmpSubCasteId());
				hrEisEmpMst.setEmail(hreiempMst.getEmail());
				hrEisEmpMst.setContactNo(hreiempMst.getContactNo());
				// rEisEmpMst.
				if (langId != 1) {
					OrgUserMst ElementCode = new OrgUserMst();
					ElementCode = hreiempMst.getOrgEmpMst().getOrgUserMst();

					List<OrgEmpMst> orgEmpMstList = empinfodao.getOrgData(ElementCode, langId);
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

				Date DOJofEmp = hrEisEmpMst.getOrgEmpMst().getEmpDoj();

				logger.info("====> DOJofEmp  :: " + DOJofEmp);
				objectArgs.put("DOJofEmp", DOJofEmp);
				objectArgs.put("hrEisEmpMst", hrEisEmpMst);
				resultObject.setResultCode(ErrorConstants.SUCCESS);

				Calendar cal = Calendar.getInstance();
				java.util.Date today = cal.getTime();
				logger.info("===> today :: " + today);
				objectArgs.put("current_date", today);

				if (!CloseFlag.equalsIgnoreCase("Y")) {
					resultObject.setViewName("EmpCompMpg");
				} else {
					resultObject.setViewName("empViewListEmpCompMpg");
				}
				// resultObject.setViewName("EmpCompMpg");
			} else {
				logger.info("===> ForViewName FromBasicDtlsNew No");
				resultObject.setViewName("empViewListEmpCompMpg");
			}

			if (empId != 0) {
				logger.info("====> Before DesgName empId :: " + empId);
				logger.info("em adfs hgrd h" + empCompMpgDAO);
				String DesgName = empCompMpgDAO.getDesigNameFromEmpId(String.valueOf(empId));
				logger.info("====> DesgName :: " + DesgName);
				objectArgs.put("DesgName", DesgName);
			}

			logger.info("====> before in empIdStr!=null............ " + empIdStr);
			List actionList = new ArrayList();
			if (empIdStr != null && !empIdStr.equals("")) // if Emp Search is yes
			{

				// Added by mrugesh
				// Map loginDetails = (Map) objectArgs.get("baseLoginMap");
				long languageId = 1;
				// cmnLanguageMst = cmnLanguageMstDaoImpl.read(languageId);
				// Ended by mrugesh

				empId = Long.parseLong(empIdStr);
				logger.info("the emp id from else if is " + empId);
				/*
				 * EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class, serv
				 * .getSessionFactory());
				 */
				// OrgEmpMst orgEmpInfoMst = orgEmpDao.getEngGujEmployee(orgEmpDao.read(empId),
				// languageId);

				actionList = empinfodao.getHrEmpFromOrgEmp(empId, languageId, locationCode);

				logger.info("getHrEmpFromOrgEmp*****************" + actionList.size());
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
				logger.info("====> in if empIdStr!=null............ " + empList.size());
				objectArgs.put("actionList", empList);

			} else {

				logger.info("The locationcode is----->>>" + locationCode);
				// actionList = empinfodao.getAllEmpDatabyDept(0, locationCode,langId);//
				// empinfodao.getAllEmpData();

				actionList = empinfodao.getAllEmpDatabyDept(locationCode, langId);

				logger.info("the size of list is " + actionList.size());
				List<EmpDtlsCustomVO> empList = new ArrayList();

				for (int i = 0; i < actionList.size(); i++) {
					EmpDtlsCustomVO empdtlsCustom = new EmpDtlsCustomVO();

					Object[] row = (Object[]) actionList.get(i);
					empdtlsCustom.setEmpId(((Long) (row[0])).longValue());
					empdtlsCustom.setEmpName(row[1].toString());
					if (row[2] != null)
						empdtlsCustom.setEmpType(((Long) (row[2])).longValue());
					if(row[3].equals("Y"))
					{
						empdtlsCustom.setDcpsorgpf("DCPS");
					}else{
						empdtlsCustom.setDcpsorgpf("GPF");
					}
				

					empList.add(empdtlsCustom);
				}
				logger.info("====> in else empIdStr==null............ " + empList.size());
				objectArgs.put("actionList", empList);
				objectArgs.put("ViewFlag", "Ture");
			}

			resultObject.setResultCode(ErrorConstants.SUCCESS);

			// objectArgs.put("actionList", actionList);
			logger.info("U are out of the way:-");
			logger.info("Values from Map before passing " + objectArgs.get("MappedDeduListSize") + " 2 "
					+ objectArgs.get("MappedallowListSize"));
			if (edit.equalsIgnoreCase("Y") && empId != 0) {
				objectArgs.put("isDCPS", empCompMpgDAO.isDCPS(empId));
				objectArgs.put("DCPSorGPF", Character.valueOf(empCompMpgDAO.isDCPS1(empId)));
				objectArgs.put("DCG", empCompMpgDAO.isDCPS2(empId));
				objectArgs.put("isNewConfig", empCompMpgDAO.isNewConfig(empId));
				objectArgs.put("isDCPSStop", Boolean.valueOf(empCompMpgDAO.isDCPSStop(empId)));
				logger.info(" Employee id " + empId + " isDCPS= " + objectArgs.get("isDCPS") + " isNewConfig="
						+ objectArgs.get("isNewConfig"));
			} else {
				objectArgs.put("isDCPS", false);
				objectArgs.put("isDCPSStop", Boolean.valueOf(false));
				objectArgs.put("isNewConfig", false);
			}
			resultObject.setResultValue(objectArgs);
		} catch (Exception e) {

			logger.error("Error is: " + e.getMessage());
		}

		return resultObject;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public ResultObject InsertEmpCompMpgDtlsService(Map ObjectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) ObjectArgs.get("serviceLocator");
		try {
			HrEisEmpCompMpg hrEisEmpCompMpgVO = null;
			String[] lArrallowList = null;
			String[] lArrDeductList = null;
			String lStrEffectDate = "";
			long Month = 0;
			long Year = 0;
			long SizeofChkValofAllow = 0;
			long SizeofChkValofDedu = 0;
			long hdnEmpId = 0;
			Date EffectDate = null;
			String Remarks = "";
			long PayCommission = 0;
			/*
			 * long PayCommissionDispay=0; Date dtEffectDate=null;
			 */
			String hdnAllowList = null;
			String hdnDeductList = null;

			Map loginMap = (Map) ObjectArgs.get("baseLoginMap");
			long locIdFromMap = Long.valueOf(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl locMstDao = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = locMstDao.read(locIdFromMap);

			long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,
					serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMstByUserId = orgUserMstDaoImpl.read(userId);

			long loggedInpostId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(loggedInpostId);

			if (ObjectArgs.get("lArrallowList") != null) {
				lArrallowList = (String[]) (ObjectArgs.get("lArrallowList"));
			}
			if (ObjectArgs.get("lArrDeductList") != null) {
				lArrDeductList = (String[]) (ObjectArgs.get("lArrDeductList"));
			}
			if (ObjectArgs.get("SizeofChkValofAllow") != null) {
				SizeofChkValofAllow = Long.parseLong(ObjectArgs.get("SizeofChkValofAllow").toString());
			}
			if (ObjectArgs.get("SizeofChkValofDedu") != null) {
				SizeofChkValofDedu = Long.parseLong(ObjectArgs.get("SizeofChkValofDedu").toString());
			}
			if (ObjectArgs.get("lStrEffectDate") != null) {
				lStrEffectDate = (ObjectArgs.get("lStrEffectDate").toString());
			}

			if (ObjectArgs.get("hdnEmpId") != null) {
				hdnEmpId = Long.parseLong(ObjectArgs.get("hdnEmpId").toString());
			}
			if (ObjectArgs.get("EffectDate") != null) {
				EffectDate = StringUtility.convertStringToDate(lStrEffectDate);
			}
			if (ObjectArgs.get("Remarks") != null) {
				Remarks = (ObjectArgs.get("Remarks").toString());
			}
			if (ObjectArgs.get("hdnAllowList") != null) {
				hdnAllowList = (ObjectArgs.get("hdnAllowList").toString());
			}
			if (ObjectArgs.get("hdnDeductList") != null) {
				hdnDeductList = (ObjectArgs.get("hdnDeductList").toString());
			}
			if (ObjectArgs.get("PayCommission") != null) {
				PayCommission = Long.parseLong(ObjectArgs.get("PayCommission").toString());
			}
			// PayCommissionDispay = PayCommission;

			Month = Long.valueOf(lStrEffectDate.substring(3, 5));
			Year = Long.valueOf(lStrEffectDate.substring(6, 10));
			logger.info("====> Month :: " + Month + "==> Year :: " + Year);

			/*
			 * logger.info("====> in Service SizeofChkValofAllow :: "+SizeofChkValofAllow);
			 * logger.info("====> in Service SizeofChkValofDedu :: "+SizeofChkValofDedu);
			 * logger.info("====> in Service lArrallowList :: "+lArrallowList.length);
			 * logger.info("====> in Service lArrDeductList :: "+lArrDeductList.length);
			 * logger.info("====> in Service lStrEffectDate :: "+lStrEffectDate);
			 * logger.info("====> in Service hdnEmpId :: "+hdnEmpId);
			 * logger.info("====> in Service EffectDate :: "+EffectDate);
			 * logger.info("====> in Service Remarks :: "+Remarks);
			 * logger.info("====> in Service PayCommission :: "+PayCommission);
			 */

			// logger.info("====> in Service lArrallowList :: "+lArrallowList[0]);
			// logger.info("====> in Service lArrDeductList :: "+lArrDeductList[0]);

			long AllownID = 2500134;
			long DeductID = 2500135;

			GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(CmnLookupMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			CmnLookupMst cmnLookupMstAllowID = (CmnLookupMst) genDao.read(AllownID);
			CmnLookupMst cmnLookupMstDeductID = (CmnLookupMst) genDao.read(DeductID);
			cmnLookupMstAllowID.getLookupId();
			cmnLookupMstDeductID.getLookupId();

			GenericDaoHibernateImpl genDaoempmst = new GenericDaoHibernateImpl(OrgEmpMst.class);
			genDaoempmst.setSessionFactory(serv.getSessionFactory());
			EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			HrEisEmpMst hrEisEmpMst = empinfoDao.read(hdnEmpId);
			logger.info("Hidden Emp id is " + hdnEmpId);
			EmpCompMpgDAOImpl empCompMpgDAOImpl = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class,
					serv.getSessionFactory());

			// Code of Insert=================================================
			// HrEisEmpCompMpg hrEisEmpCompMpgVO=null;

			HrEisEmpCompGrpMst updateHrEisComGrpMst = null;
			// HrEisEmpCompMpg updatehreisMpg=null;
			HrEisEmpCompGrpMst OldMstVo = null;
			HrEisEmpCompGrpMst NewHrEisEmpGrpMst = new HrEisEmpCompGrpMst();

			String EffectD = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("dd/MM/yyyy").parse(lStrEffectDate));
			logger.info("==> in serice EffectD :: " + EffectD);
			/*
			 * //// Added For New
			 * 
			 * List<HrEisEmpCompMpg>PreviousAddedList =
			 * empCompMpgDAOImpl.getPeviousMappedCompoIDfromCompMpg(hdnEmpId); StringBuffer
			 * PreviousMappedCompoId = new StringBuffer(); for(HrEisEmpCompMpg xyz
			 * :PreviousAddedList) { if(xyz.getCmnLookupMst()!=null) {
			 * PreviousMappedCompoId.append(String.valueOf(xyz.getCompId())+","); } }
			 * 
			 * if(PreviousMappedCompoId.length()!=0) {
			 * PreviousMappedCompoId.deleteCharAt(PreviousMappedCompoId.length()-1); }
			 * logger.info("====> str :: "+PreviousAddedList.size()+
			 * "=====> "+PreviousMappedCompoId );
			 * 
			 * String PreviouslStrMappedComoId = PreviousMappedCompoId.toString();
			 * if(PreviouslStrMappedComoId.equals(null) ||
			 * PreviouslStrMappedComoId.equals("")) { PreviouslStrMappedComoId = "0"; }
			 * 
			 * logger.info("==>Previous Mapped value Allow:: "+PreviouslStrMappedComoId);
			 * String CurrentMappedList = hdnAllowList+","+hdnDeductList;
			 * logger.info("==> CurrentMappedList :: "+CurrentMappedList);
			 * 
			 * String[] mapingAllow = hdnAllowList.split(",");
			 * 
			 * List<HrEisEmpCompMpg> NewAddedListAllow =
			 * empCompMpgDAOImpl.getNewMappedCompoIDfromCompMpg(hdnEmpId,CurrentMappedList,
			 * PreviouslStrMappedComoId); List<HrEisEmpCompMpg> RemovedAddedListAllow =
			 * empCompMpgDAOImpl.getRemovedMappedCompoIDfromCompMpg(hdnEmpId,
			 * PreviouslStrMappedComoId,hdnAllowList,AllownID); List<HrEisEmpCompMpg>
			 * RemovedAddedListDeduct =
			 * empCompMpgDAOImpl.getRemovedMappedCompoIDfromCompMpg(hdnEmpId,
			 * PreviouslStrMappedComoId,hdnDeductList,DeductID);
			 * 
			 * List<HrEisEmpCompMpg> SameAsListAllow =
			 * empCompMpgDAOImpl.getSameMappedCompoIDfromCompMpg(hdnEmpId,CurrentMappedList,
			 * PreviouslStrMappedComoId,AllownID); List<HrEisEmpCompMpg> SameAsListDeduct =
			 * empCompMpgDAOImpl.getSameMappedCompoIDfromCompMpg(hdnEmpId,CurrentMappedList,
			 * PreviouslStrMappedComoId,DeductID);
			 * 
			 * List<HrEisEmpCompMpg> finalInsertList = new ArrayList(); boolean
			 * flagRemoved=false; boolean flagSame = false;
			 * 
			 * for(int i=0;i<mapingAllow.length;i++) { int j=0;
			 * 
			 * for(j=0;j<RemovedAddedListAllow.size();j++) {
			 * if(mapingAllow[i].equals(RemovedAddedListAllow.get(j).getCompId())) {
			 * flagRemoved= true; HrEisEmpCompMpg removeObj = RemovedAddedListAllow.get(j);
			 * HrEisEmpCompMpg newRemoveobj = new HrEisEmpCompMpg(); IdGenerator idGen1 =
			 * new IdGenerator(); Long id1=
			 * idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs); logger.
			 * info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is "+id1);
			 * 
			 * newRemoveobj.setCmnLookupMst(removeObj.getCmnLookupMst());
			 * newRemoveobj.setCompId(removeObj.getCompId());
			 * newRemoveobj.setEmpCompId(id1);//generate new pk
			 * newRemoveobj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
			 * newRemoveobj.setIsactive(0);
			 * newRemoveobj.setStartDate(removeObj.getStartDate());
			 * newRemoveobj.setEndDate(EffectDate); finalInsertList.add(newRemoveobj);
			 * break;
			 * 
			 * } }
			 * 
			 * for(j=0;j<SameAsListAllow.size();j++) {
			 * if(mapingAllow[i].equals(SameAsListAllow.get(j).getCompId())) { flagSame=
			 * true; HrEisEmpCompMpg SameObj = RemovedAddedListAllow.get(j); HrEisEmpCompMpg
			 * newSameAsobj = new HrEisEmpCompMpg(); IdGenerator idGen1 = new IdGenerator();
			 * Long id1= idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs); logger.
			 * info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is "+id1);
			 * newSameAsobj.setCmnLookupMst(SameObj.getCmnLookupMst());
			 * newSameAsobj.setCompId(SameObj.getCompId());
			 * newSameAsobj.setEmpCompId(id1);//generate new pk
			 * newSameAsobj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
			 * newSameAsobj.setIsactive(0);
			 * newSameAsobj.setStartDate(SameObj.getStartDate());
			 * newSameAsobj.setEndDate(EffectDate); finalInsertList.add(newSameAsobj);
			 * 
			 * break;
			 * 
			 * } }
			 * 
			 * if(!flagSame && !flagRemoved) { HrEisEmpCompMpg newObj = new
			 * HrEisEmpCompMpg(); IdGenerator idGen1 = new IdGenerator(); Long id1=
			 * idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs); logger.
			 * info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is "+id1);
			 * newObj.setCmnLookupMst(cmnLookupMstAllowID);
			 * newObj.setCompId(Long.parseLong(mapingAllow[i]));
			 * newObj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
			 * newObj.setEmpCompId(id1);//generate new pk newObj.setIsactive(1);
			 * newObj.setStartDate(EffectDate); finalInsertList.add(newObj);
			 * 
			 * }
			 * 
			 * 
			 * }
			 * 
			 * 
			 * logger.info("===> in Service :: NewAddedList ::"+finalInsertList.size());
			 * logger.info("===> in Service :: RemovedAddedList ::"+finalInsertList.size());
			 * logger.info("===> in Service :: SameAsList ::"+SameAsList.size());
			 * 
			 * //// Added For New
			 */
			// Allow Previous
			List<HrEisEmpCompMpg> PreviousAddedListAllow = empCompMpgDAOImpl
					.getPeviousMappedCompoIDfromCompMpg(hdnEmpId, AllownID);
			StringBuffer PreviousMappedCompoIdAllow = new StringBuffer();
			for (HrEisEmpCompMpg xyz : PreviousAddedListAllow) {
				if (xyz.getCmnLookupMst() != null) {
					PreviousMappedCompoIdAllow.append(String.valueOf(xyz.getCompId()) + ",");
				}
			}

			if (PreviousMappedCompoIdAllow.length() != 0) {
				PreviousMappedCompoIdAllow.deleteCharAt(PreviousMappedCompoIdAllow.length() - 1);
			}
			logger.info("====> str :: " + PreviousAddedListAllow.size() + "=====> " + PreviousMappedCompoIdAllow);

			String PreviouslStrMappedComoIdAllow = PreviousMappedCompoIdAllow.toString();
			if (PreviouslStrMappedComoIdAllow.equals(null) || PreviouslStrMappedComoIdAllow.equals("")) {
				PreviouslStrMappedComoIdAllow = "0";
			}

			logger.info("==>PreviouslStrMappedComoIdAllow value Allow:: " + PreviouslStrMappedComoIdAllow);
			logger.info("==>Current Allow Mapped :: " + hdnAllowList);

			String hdnAllowListRemove = "";
			if (hdnAllowList != null && hdnAllowList.length() > 0) {
				if (hdnAllowList.charAt(hdnAllowList.length() - 1) == ',') {
					logger.info("====> Allow in char at..");
					hdnAllowListRemove = hdnAllowList.substring(0, (hdnAllowList.length() - 1));
					logger.info("====> Allow RemoveChar :: " + hdnAllowListRemove);
				} else {
					hdnAllowListRemove = hdnAllowList;
				}
			}

			String hdnDeductListRemove = "";
			if (hdnDeductList != null && hdnDeductList.length() > 0) {
				if (hdnDeductList.charAt(hdnDeductList.length() - 1) == ',') {
					logger.info("====> Deduct in char at..");
					hdnDeductListRemove = hdnDeductList.substring(0, (hdnDeductList.length() - 1));
					logger.info("====> Deduct RemoveChar :: " + hdnDeductListRemove);
				} else {
					hdnDeductListRemove = hdnDeductList;
				}
				logger.info("==>Current Deduct Mapped :: " + hdnDeductList);
			}

			// Allow Previous
			//// Deduct Previous
			List<HrEisEmpCompMpg> PreviousAddedListDeduct = empCompMpgDAOImpl
					.getPeviousMappedCompoIDfromCompMpg(hdnEmpId, DeductID);
			StringBuffer PreviousMappedCompoIdDeduct = new StringBuffer();
			for (HrEisEmpCompMpg xyz : PreviousAddedListDeduct) {
				if (xyz.getCmnLookupMst() != null) {
					PreviousMappedCompoIdDeduct.append(String.valueOf(xyz.getCompId()) + ",");
				}
			}

			if (PreviousMappedCompoIdDeduct.length() != 0) {
				PreviousMappedCompoIdDeduct.deleteCharAt(PreviousMappedCompoIdDeduct.length() - 1);
			}
			logger.info("====> str :: " + PreviousAddedListDeduct.size() + "=====> " + PreviousMappedCompoIdDeduct);

			String PreviouslStrMappedComoIdDeduct = PreviousMappedCompoIdDeduct.toString();
			if (PreviouslStrMappedComoIdDeduct.equals(null) || PreviouslStrMappedComoIdDeduct.equals("")) {
				PreviouslStrMappedComoIdDeduct = "0";
			}

			logger.info("==>PreviouslStrMappedComoIdDeduct value Allow:: " + PreviouslStrMappedComoIdDeduct);

			//// Deduct Previous
			if (hdnAllowListRemove.equals("")) {
				hdnAllowListRemove = "0";
			}

			if (hdnDeductListRemove.equals("")) {
				hdnDeductListRemove = "0";
			}

			logger.info("===> before Passing Remove Allow in(" + PreviouslStrMappedComoIdAllow + ") and not in("
					+ hdnAllowListRemove + ")");
			logger.info("===> before Passing Remove Deduct in(" + PreviouslStrMappedComoIdDeduct + ") and not in("
					+ hdnDeductListRemove + ")");

			logger.info("===> before Passing Remove in(" + PreviouslStrMappedComoIdAllow + ") and not in("
					+ hdnAllowListRemove + ")");
			List<HrEisEmpCompMpg> RemovedAddedListAllow = empCompMpgDAOImpl.getRemovedMappedCompoIDfromCompMpg(hdnEmpId,
					PreviouslStrMappedComoIdAllow, hdnAllowListRemove, AllownID);
			List<HrEisEmpCompMpg> RemovedAddedListDeduct = empCompMpgDAOImpl.getRemovedMappedCompoIDfromCompMpg(
					hdnEmpId, PreviouslStrMappedComoIdDeduct, hdnDeductListRemove, DeductID);
			logger.info("===> in Service :: RemovedAddedListAllow ::" + RemovedAddedListAllow.size());
			logger.info("===> in Service :: RemovedAddedListDeduct ::" + RemovedAddedListDeduct.size());

			List<HrEisEmpCompMpg> SameAsListAllow = empCompMpgDAOImpl.getSameMappedCompoIDfromCompMpg(hdnEmpId,
					hdnAllowListRemove, PreviouslStrMappedComoIdAllow, AllownID);
			List<HrEisEmpCompMpg> SameAsListDeduct = empCompMpgDAOImpl.getSameMappedCompoIDfromCompMpg(hdnEmpId,
					hdnDeductListRemove, PreviouslStrMappedComoIdDeduct, DeductID);

			logger.info("===> in Service :: SameAsListAllow ::" + SameAsListAllow.size());
			logger.info("===> in Service :: SameAsListDeduct ::" + SameAsListDeduct.size());

			List<HrEisEmpCompGrpMst> ListHrEisCompGrpMst = null;
			ListHrEisCompGrpMst = (List<HrEisEmpCompGrpMst>) empCompMpgDAOImpl.getMstDataIsPresent(hdnEmpId, EffectD);
			logger.info("====> ListHrEisCompGrpMst.Size in Service :: " + ListHrEisCompGrpMst.size());

			List ListhrEisComFromEmpID = null;
			ListhrEisComFromEmpID = (List) empCompMpgDAOImpl.getMstDataFromEmpID(hdnEmpId);

			long grpId = 0;
			if (ListHrEisCompGrpMst != null && ListHrEisCompGrpMst.size() > 0) {
				grpId = ListHrEisCompGrpMst.get(0).getEmpCompGrpId();
				OldMstVo = ListHrEisCompGrpMst.get(0);
			}

			List<HrPayCompGrpMst> ListHrPayCompGrpMst = null;
			ListHrPayCompGrpMst = (List<HrPayCompGrpMst>) empCompMpgDAOImpl.getHrPayCompGrpMst(locIdFromMap);
			HrPayCompGrpMst hrpaycomGrpMst = null;

			if (ListHrPayCompGrpMst != null && ListHrPayCompGrpMst.size() > 0) {
				hrpaycomGrpMst = ListHrPayCompGrpMst.get(0);
			}

			if (true) {

				// List<HrEisEmpCompMpg> list = new ArrayList<HrEisEmpCompMpg>();

				for (int i = 0; i < ListhrEisComFromEmpID.size(); i++) {
					logger.info("===> in ListhrPayComFromLoc save and update method :: " + i);
					updateHrEisComGrpMst = (HrEisEmpCompGrpMst) ListhrEisComFromEmpID.get(i);
					logger.info("====> id in for :: " + updateHrEisComGrpMst.getEmpCompGrpId());
					updateHrEisComGrpMst.setIsactive(0);
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(updateHrEisComGrpMst);

				}

				IdGenerator idGen = new IdGenerator();
				Long id = idGen.PKGenerator("HR_EIS_EMP_COMPONENT_GRP_MST", ObjectArgs);
				// Long id=
				// IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_GRP_MST",ObjectArgs);

				logger.info(" HR_EIS_EMP_COMPONENT_GRP_MST ******************the id generated is " + id);
				NewHrEisEmpGrpMst.setEmpCompGrpId(id);
				NewHrEisEmpGrpMst.setHrEisEmpMst(hrEisEmpMst);
				NewHrEisEmpGrpMst.setMonth(Month);
				NewHrEisEmpGrpMst.setYear(Year);
				NewHrEisEmpGrpMst.setPayComissionId(PayCommission);
				NewHrEisEmpGrpMst.setWefDate(EffectDate);
				NewHrEisEmpGrpMst.setIsactive(1);
				NewHrEisEmpGrpMst.setHrPayCompGrpMst(hrpaycomGrpMst);
				NewHrEisEmpGrpMst.setRemarks(Remarks);
				NewHrEisEmpGrpMst.setCreatedBy(orgPostMst);
				NewHrEisEmpGrpMst.setCreatedDate(new Date());
				NewHrEisEmpGrpMst.setUpdatedBy(orgPostMst);

				serv.getSessionFactory().getCurrentSession().saveOrUpdate(NewHrEisEmpGrpMst);
				//// Added For New

				// String CurrentMappedList = hdnAllowList+","+hdnDeductList;
				// logger.info("==> CurrentMappedList :: "+CurrentMappedList);

				String[] mapingAllow = null;
				String[] mapingDeduct = null;
				if (hdnAllowList != null && !hdnAllowList.equals(""))
					mapingAllow = hdnAllowList.split(",");
				// System.out.println("Hi Manish Enjoy this "+mapingAllow);
				if (hdnDeductList != null && !hdnDeductList.equals(""))
					mapingDeduct = hdnDeductList.split(",");

				String[] mapingRemoveAllow = PreviouslStrMappedComoIdAllow.split(",");
				String[] mapingRemoveDeduct = PreviouslStrMappedComoIdDeduct.split(",");

				logger.info("====> mapingRemoveAllow :: " + mapingRemoveAllow.length);
				logger.info("====> mapingRemoveAllow :: " + RemovedAddedListAllow.size());

				/*
				 * List<HrEisEmpCompMpg> NewAddedListAllow =
				 * empCompMpgDAOImpl.getNewMappedCompoIDfromCompMpg(hdnEmpId,CurrentMappedList,
				 * PreviouslStrMappedComoId);
				 */
				/*
				 * logger.info("===> before Passing Remove in("
				 * +PreviouslStrMappedComoIdAllow+") and not in("+hdnAllowList+")");
				 * List<HrEisEmpCompMpg> RemovedAddedListAllow =
				 * empCompMpgDAOImpl.getRemovedMappedCompoIDfromCompMpg(hdnEmpId,
				 * PreviouslStrMappedComoIdAllow,hdnAllowList,AllownID); List<HrEisEmpCompMpg>
				 * RemovedAddedListDeduct =
				 * empCompMpgDAOImpl.getRemovedMappedCompoIDfromCompMpg(hdnEmpId,
				 * PreviouslStrMappedComoIdDeduct,hdnDeductList,DeductID);
				 * 
				 * logger.info("===> in Service :: RemovedAddedListAllow ::"
				 * +RemovedAddedListAllow.size());
				 * logger.info("===> in Service :: RemovedAddedListDeduct ::"
				 * +RemovedAddedListDeduct.size());
				 * 
				 * List<HrEisEmpCompMpg> SameAsListAllow =
				 * empCompMpgDAOImpl.getSameMappedCompoIDfromCompMpg(hdnEmpId,hdnAllowList,
				 * PreviouslStrMappedComoIdAllow,AllownID); List<HrEisEmpCompMpg>
				 * SameAsListDeduct =
				 * empCompMpgDAOImpl.getSameMappedCompoIDfromCompMpg(hdnEmpId,hdnDeductList,
				 * PreviouslStrMappedComoIdDeduct,DeductID);
				 * 
				 * logger.info("===> in Service :: SameAsListAllow ::"+SameAsListAllow.size());
				 * logger.info("===> in Service :: SameAsListDeduct ::"+SameAsListDeduct.size())
				 * ;
				 */

				List<HrEisEmpCompMpg> finalInsertList = new ArrayList();
				// boolean flagRemoved=false;
				boolean flagSame = false;
				// Allow Insert
				for (int i = 0; i < mapingRemoveAllow.length; i++) {
					int j = 0;
					// flagRemoved= false;
					// flagSame=false;
					for (j = 0; j < RemovedAddedListAllow.size(); j++) {

						logger.info("====> Removed value ::" + mapingRemoveAllow[i] + "::"
								+ RemovedAddedListAllow.get(j).getCompId());
						if (mapingRemoveAllow[i].equals("" + RemovedAddedListAllow.get(j).getCompId())) {
							logger.info("===>in adding remove entry... ");
							// flagRemoved= true;
							logger.info("====> in if RemovedAddedListAllow..... ");
							HrEisEmpCompMpg removeObj = RemovedAddedListAllow.get(j);
							HrEisEmpCompMpg newRemoveobj = new HrEisEmpCompMpg();
							IdGenerator idGen1 = new IdGenerator();
							Long id1 = idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", ObjectArgs);
							// Long id1=
							// IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs);
							logger.info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is " + id1);

							newRemoveobj.setCmnLookupMst(removeObj.getCmnLookupMst());
							newRemoveobj.setCompId(removeObj.getCompId());
							newRemoveobj.setEmpCompId(id1);// generate new pk
							newRemoveobj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
							newRemoveobj.setIsactive(0);
							newRemoveobj.setStartDate(removeObj.getStartDate());
							newRemoveobj.setEndDate(EffectDate);
							newRemoveobj.setRemarks(removeObj.getRemarks());
							newRemoveobj.setUpdatedByPost(loggedInpostId);
							newRemoveobj.setUpdatedDate(new Date());
							finalInsertList.add(newRemoveobj);
							// 10 jan 2012 remove hreisotherdtls isAvailedHRA
							if (hrEisEmpMst != null && RemovedAddedListAllow.get(j).getCompId() == 9L) {
								empCompMpgDAOImpl.updateHRAinHrEisOtherDtls(hrEisEmpMst.getEmpId(), 0);
								logger.info(" HR EIS OTHER DTLS isAvailedHRA set 0");
							}

							break;

						}
					}
				}

				int mapedAllwLength = 0;
				if (mapingAllow != null)
					mapedAllwLength = mapingAllow.length;
				for (int i = 0; i < mapedAllwLength; i++) {
					int j = 0;
					// flagRemoved= false;
					flagSame = false;

					for (j = 0; j < SameAsListAllow.size(); j++) {
						logger.info("====> Same Value Allow ::" + mapingAllow[i] + "::"
								+ SameAsListAllow.get(j).getCompId());
						if (mapingAllow[i].equals("" + SameAsListAllow.get(j).getCompId())) {
							flagSame = true;
							logger.info("====> in if SameAsListAllow..... ");
							HrEisEmpCompMpg SameObj = SameAsListAllow.get(j);
							HrEisEmpCompMpg newSameAsobj = new HrEisEmpCompMpg();
							IdGenerator idGen1 = new IdGenerator();
							Long id1 = idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", ObjectArgs);
							// Long id1=
							// IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs);
							logger.info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is " + id1);
							newSameAsobj.setCmnLookupMst(SameObj.getCmnLookupMst());
							newSameAsobj.setCompId(SameObj.getCompId());
							newSameAsobj.setEmpCompId(id1);// generate new pk
							newSameAsobj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
							newSameAsobj.setIsactive(SameObj.getIsactive());
							newSameAsobj.setStartDate(SameObj.getStartDate());
							newSameAsobj.setRemarks(SameObj.getRemarks());
							newSameAsobj.setUpdatedByPost(loggedInpostId);
							newSameAsobj.setUpdatedDate(new Date());
							// newSameAsobj.setEndDate(EffectDate);
							finalInsertList.add(newSameAsobj);

							break;

						}
					}

					if (!flagSame) {
						logger.info("====> in if New Data Insert ..... ");
						HrEisEmpCompMpg newObj = new HrEisEmpCompMpg();
						IdGenerator idGen1 = new IdGenerator();
						Long id1 = idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", ObjectArgs);
						// Long id1=
						// IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs);
						logger.info(" HR_EIS_EMP_COMPONENT_MPG **New ID Gen NewData :: " + id1);
						newObj.setCmnLookupMst(cmnLookupMstAllowID);
						newObj.setCompId(Long.parseLong(mapingAllow[i]));
						newObj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
						newObj.setEmpCompId(id1);// generate new pk
						newObj.setIsactive(1);
						newObj.setStartDate(EffectDate);
						newObj.setRemarks(Remarks);
						finalInsertList.add(newObj);

					}

					// 10 jan 2012 remove hreisotherdtls isAvailedHRA
					if (mapingAllow[i].equalsIgnoreCase("9") && hrEisEmpMst != null) {
						// 10 jan 2012 remove hreisotherdtls isAvailedHRA

						empCompMpgDAOImpl.updateHRAinHrEisOtherDtls(hrEisEmpMst.getEmpId(), 1);
						logger.info(" HR EIS OTHER DTLS isAvailedHRA set 1");
					}

				}
				// End of Allow Insert

				///// Deduct Insert

				for (int i = 0; i < mapingRemoveDeduct.length; i++) {
					int j = 0;
					for (j = 0; j < RemovedAddedListDeduct.size(); j++) {

						logger.info("====> Same Value Remove ::" + mapingRemoveDeduct[i] + "::"
								+ RemovedAddedListDeduct.get(j).getCompId());
						if (mapingRemoveDeduct[i].equals("" + RemovedAddedListDeduct.get(j).getCompId())) {
							// flagRemoved= true;
							logger.info("===>in if RemovedAddedListDeduct ");

							HrEisEmpCompMpg removeObj = RemovedAddedListDeduct.get(j);
							HrEisEmpCompMpg newRemoveobj = new HrEisEmpCompMpg();
							IdGenerator idGen1 = new IdGenerator();
							Long id1 = idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", ObjectArgs);
							// Long id1=
							// IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs);
							logger.info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is " + id1);

							newRemoveobj.setCmnLookupMst(removeObj.getCmnLookupMst());
							newRemoveobj.setCompId(removeObj.getCompId());
							newRemoveobj.setEmpCompId(id1);// generate new pk
							newRemoveobj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
							newRemoveobj.setIsactive(0);
							newRemoveobj.setStartDate(removeObj.getStartDate());
							newRemoveobj.setEndDate(EffectDate);
							newRemoveobj.setRemarks(removeObj.getRemarks());
							newRemoveobj.setUpdatedByPost(loggedInpostId);
							newRemoveobj.setUpdatedDate(new Date());
							finalInsertList.add(newRemoveobj);
							break;

						}
					}

				}
				int mappedDeducLength = 0;
				if (mapingDeduct != null)
					mappedDeducLength = mapingDeduct.length;

				for (int i = 0; i < mappedDeducLength; i++) {
					int j = 0;
					// flagRemoved= false;
					flagSame = false;

					/*
					 * for(j=0;j<RemovedAddedListDeduct.size();j++) {
					 * if(mapingDeduct.length<=mapingRemoveDeduct.length) {
					 * logger.info("====> Same Value Remove ::"+mapingRemoveDeduct[i]+"::"+
					 * RemovedAddedListDeduct.get(j).getCompId());
					 * if(mapingRemoveDeduct[i].equals(""+RemovedAddedListDeduct.get(j).getCompId())
					 * ) { flagRemoved= true; logger.info("===>in if RemovedAddedListDeduct ");
					 * 
					 * HrEisEmpCompMpg removeObj = RemovedAddedListDeduct.get(j); HrEisEmpCompMpg
					 * newRemoveobj = new HrEisEmpCompMpg(); IdGenerator idGen1 = new IdGenerator();
					 * Long id1= idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs); logger.
					 * info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is "+id1);
					 * 
					 * newRemoveobj.setCmnLookupMst(removeObj.getCmnLookupMst());
					 * newRemoveobj.setCompId(removeObj.getCompId());
					 * newRemoveobj.setEmpCompId(id1);//generate new pk
					 * newRemoveobj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
					 * newRemoveobj.setIsactive(0);
					 * newRemoveobj.setStartDate(removeObj.getStartDate());
					 * newRemoveobj.setEndDate(EffectDate); finalInsertList.add(newRemoveobj);
					 * break;
					 * 
					 * } } }
					 */

					for (j = 0; j < SameAsListDeduct.size(); j++) {
						logger.info("====> Same Value Deduct ::" + mapingDeduct[i] + "::"
								+ SameAsListDeduct.get(j).getCompId());
						if (mapingDeduct[i].equals("" + SameAsListDeduct.get(j).getCompId())) {
							flagSame = true;
							logger.info("===>in if SameAsListDeduct ");
							HrEisEmpCompMpg SameObj = SameAsListDeduct.get(j);
							HrEisEmpCompMpg newSameAsobj = new HrEisEmpCompMpg();
							IdGenerator idGen1 = new IdGenerator();
							Long id1 = idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", ObjectArgs);
							// Long id1=
							// IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs);
							logger.info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is " + id1);
							newSameAsobj.setCmnLookupMst(SameObj.getCmnLookupMst());
							newSameAsobj.setCompId(SameObj.getCompId());
							newSameAsobj.setEmpCompId(id1);// generate new pk
							newSameAsobj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
							newSameAsobj.setIsactive(SameObj.getIsactive());
							newSameAsobj.setStartDate(SameObj.getStartDate());
							// newSameAsobj.setEndDate(EffectDate);
							newSameAsobj.setRemarks(SameObj.getRemarks());
							newSameAsobj.setUpdatedByPost(loggedInpostId);
							newSameAsobj.setUpdatedDate(new Date());
							finalInsertList.add(newSameAsobj);

							break;

						}
					}

					if (!flagSame) {
						HrEisEmpCompMpg newObj = new HrEisEmpCompMpg();
						IdGenerator idGen1 = new IdGenerator();
						Long id1 = idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", ObjectArgs);
						// Long id1=
						// IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs);
						logger.info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is " + id1);
						newObj.setCmnLookupMst(cmnLookupMstDeductID);
						newObj.setCompId(Long.parseLong(mapingDeduct[i]));
						newObj.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
						newObj.setEmpCompId(id1);// generate new pk
						newObj.setIsactive(1);
						newObj.setStartDate(EffectDate);
						newObj.setRemarks(Remarks);
						finalInsertList.add(newObj);

					}

				}
				/// End of Deduct Insert

				for (int i = 0; i < finalInsertList.size(); i++) {
					logger.info("===> in save and update method :: " + i);
					HrEisEmpCompMpg hep = (HrEisEmpCompMpg) finalInsertList.get(i);
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
					ObjectArgs.put("msg", "Records Inserted Successfully");
				}

				/*
				 * logger.info("===> in Service :: RemovedAddedList ::"+finalInsertList.size());
				 * logger.info("===> in Service :: SameAsList ::"+SameAsList.size());
				 */

				//// Added For New
				/*
				 * if(lArrallowList!=null) { for(int i=0;i<lArrallowList.length;i++) {
				 * hrEisEmpCompMpgVO = new HrEisEmpCompMpg(); IdGenerator idGen1 = new
				 * IdGenerator(); Long id1=
				 * idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs); logger.
				 * info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is "+id1);
				 * hrEisEmpCompMpgVO.setEmpCompId(id1);
				 * hrEisEmpCompMpgVO.setCompId(Long.valueOf(lArrallowList[i]));
				 * hrEisEmpCompMpgVO.setCmnLookupMst(cmnLookupMstAllowID);
				 * hrEisEmpCompMpgVO.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
				 * hrEisEmpCompMpgVO.setIsactive(1); list.add(hrEisEmpCompMpgVO);
				 * logger.info("====> hrEisEmpCompMpgVO :: "+hrEisEmpCompMpgVO.getEmpCompId());
				 * }
				 * 
				 * logger.info("====> 1 list.size :: "+list.size()); for(int
				 * i=0;i<list.size();i++) { logger.info("===> in save and update method :: "+i);
				 * HrEisEmpCompMpg hep =(HrEisEmpCompMpg)list.get(i);
				 * serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
				 * ObjectArgs.put("msg","Inserted Successfully"); } } List<HrEisEmpCompMpg>
				 * listDeduct = new ArrayList<HrEisEmpCompMpg>(); if(lArrDeductList!=null) {
				 * for(int h=0;h<lArrDeductList.length;h++) { hrEisEmpCompMpgVO = new
				 * HrEisEmpCompMpg(); IdGenerator idGen1 = new IdGenerator(); Long id2=
				 * idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs); logger.
				 * info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is "+id2);
				 * hrEisEmpCompMpgVO.setEmpCompId(id2);
				 * hrEisEmpCompMpgVO.setCompId(Long.valueOf(lArrDeductList[h]));
				 * hrEisEmpCompMpgVO.setCmnLookupMst(cmnLookupMstDeductID);
				 * hrEisEmpCompMpgVO.setHrEisEmpCompGrpMst(NewHrEisEmpGrpMst);
				 * hrEisEmpCompMpgVO.setIsactive(1); listDeduct.add(hrEisEmpCompMpgVO);
				 * logger.info("====> hrEisEmpCompMpgVO :: "+hrEisEmpCompMpgVO.getEmpCompId());
				 * }
				 * 
				 * 
				 * GenericDaoHibernateImpl genDao1 = new
				 * GenericDaoHibernateImpl(HrPayLocComMpg.class);
				 * genDao1.setSessionFactory(serv.getSessionFactory()); HrEisEmpCompMpg hepdedu
				 * = null; logger.info("====> 2 listDeduct.size :: "+listDeduct.size()); for(int
				 * i=0;i<listDeduct.size();i++) { hepdedu =(HrEisEmpCompMpg)listDeduct.get(i);
				 * genDao1.create(hepdedu); ObjectArgs.put("msg","Inserted Successfully"); } }
				 */
				// added For HRPAYCHANGEDRECORDS
				Map mapForChangedRecords = new HashMap();
				mapForChangedRecords = ObjectArgs;
				long changedEmpId = hdnEmpId;
				EmpInfoDAOImpl infoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
				HrEisEmpMst empMst = infoDAOImpl.read(hdnEmpId);
				long chUser = empMst.getOrgEmpMst().getOrgUserMst().getUserId();
				NewEmployeeConfigDAOImpl newEmpDao = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,
						serv.getSessionFactory());
				List postList = newEmpDao.getListByColumnAndValue("orgUserMst.userId", chUser);
				long postIdNew = ((OrgUserpostRlt) postList.get(0)).getOrgPostMstByPostId().getPostId();
				// long locId=Long.valueOf(loginMap.get("locationId").toString());
				long locId = Long.valueOf(loginMap.get("locationId").toString());

				orgPostMst = orgPostMstDaoImpl.read(postIdNew);
				// mapForChangedRecords.put("changedPostId",postIdNew);
				mapForChangedRecords.put("changedPostId", postIdNew);
				mapForChangedRecords.put("changedEmpId", changedEmpId);
				mapForChangedRecords.put("locId", locId);
				mapForChangedRecords.put("serviceLocator", serv);
				mapForChangedRecords.put("OrgPostMst", orgPostMst);
				mapForChangedRecords.put("OrgUserMst", empMst.getOrgEmpMst().getOrgUserMst());
				mapForChangedRecords.put("cmnDatabaseMst", cmnDatabaseMst);
				GenerateBillServiceHelper generateBillServiceHelper = new GenerateBillServiceHelper();
				long changedRecordPK = generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"
						+ changedRecordPK);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"
						+ changedRecordPK);

				// ended

			} else {
				// added by khushal for adding remarks
				ListhrEisComFromEmpID = (List) empCompMpgDAOImpl.getMstDataFromEmpID(hdnEmpId);
				for (int i = 0; i < ListhrEisComFromEmpID.size(); i++) {
					logger.info("===> in ListhrPayComFromLoc save and update method :: " + i);
					updateHrEisComGrpMst = (HrEisEmpCompGrpMst) ListhrEisComFromEmpID.get(i);
					logger.info("====> id in for :: " + updateHrEisComGrpMst.getEmpCompGrpId());
					updateHrEisComGrpMst.setWefDate(EffectDate);
					updateHrEisComGrpMst.setRemarks(Remarks);
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(updateHrEisComGrpMst);

				}
				// ended by khushal

				logger.info("======> in else...........................");
				List<HrEisEmpCompMpg> list = new ArrayList<HrEisEmpCompMpg>();
				HrEisEmpCompMpg hreisempcompMpg = null;

				// For Allowance Insert.......................
				for (int i = 0; i < SizeofChkValofAllow; i++) {
					long allowId = StringUtility.convertToLong(lArrallowList[i]);
					hreisempcompMpg = empCompMpgDAOImpl.getDataIDisPresent(hdnEmpId, allowId, grpId, AllownID);
					if (hreisempcompMpg == null) {
						hrEisEmpCompMpgVO = new HrEisEmpCompMpg();
						IdGenerator idGen1 = new IdGenerator();
						Long id1 = idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", ObjectArgs);
						// Long id1=IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs);
						logger.info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is " + id1);
						hrEisEmpCompMpgVO.setEmpCompId(id1);
						hrEisEmpCompMpgVO.setCompId(Long.valueOf(lArrallowList[i]));
						hrEisEmpCompMpgVO.setCmnLookupMst(cmnLookupMstAllowID);
						hrEisEmpCompMpgVO.setHrEisEmpCompGrpMst(OldMstVo);
						hrEisEmpCompMpgVO.setIsactive(1);
						hrEisEmpCompMpgVO.setStartDate(EffectDate);
						hrEisEmpCompMpgVO.setRemarks(Remarks);
						list.add(hrEisEmpCompMpgVO);
						// logger.info("====> hrEisEmpCompMpgVO :: "+hrEisEmpCompMpgVO.getEmpCompId());
					} else {
						hreisempcompMpg.setCmnLookupMst(cmnLookupMstAllowID);
						hreisempcompMpg.setHrEisEmpCompGrpMst(OldMstVo);

						if (hreisempcompMpg.getIsactive() == 0) {// if Already in DB but not 1 so Update as New Effect
																	// Date
							hreisempcompMpg.setStartDate(EffectDate);
							hreisempcompMpg.setRemarks(Remarks);
						}

						hreisempcompMpg.setIsactive(1);
						hreisempcompMpg.setEndDate(null);
						list.add(hreisempcompMpg);
					}

				}

				logger.info("====> 1 list.size :: " + list.size());
				for (int i = 0; i < list.size(); i++) {
					logger.info("===> in save and update method :: " + i);
					HrEisEmpCompMpg hep = (HrEisEmpCompMpg) list.get(i);
					hep.setStartDate(EffectDate);
					hep.setRemarks(Remarks);
					serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep);
					ObjectArgs.put("msg", "Records Inserted Successfully");
				}
				// Allowance Insert End...........................

				// Deduction Inser Start...................
				List<HrEisEmpCompMpg> listDeduct = new ArrayList<HrEisEmpCompMpg>();
				for (int i = 0; i < SizeofChkValofDedu; i++) {
					long DeductId = StringUtility.convertToLong(lArrDeductList[i]);
					hreisempcompMpg = (HrEisEmpCompMpg) empCompMpgDAOImpl.getDataIDisPresent(hdnEmpId, DeductId, grpId,
							DeductID);

					if (hreisempcompMpg == null) {
						hrEisEmpCompMpgVO = new HrEisEmpCompMpg();
						IdGenerator idGen1 = new IdGenerator();
						Long id2 = idGen1.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", ObjectArgs);
						// Long id2=
						// IDGenerateDelegate.getNextId("HR_EIS_EMP_COMPONENT_MPG",ObjectArgs);
						logger.info(" HR_EIS_EMP_COMPONENT_MPG ******************the id generated is " + id2);
						hrEisEmpCompMpgVO.setEmpCompId(id2);
						hrEisEmpCompMpgVO.setCompId(Long.valueOf(lArrDeductList[i]));
						hrEisEmpCompMpgVO.setCmnLookupMst(cmnLookupMstDeductID);
						hrEisEmpCompMpgVO.setHrEisEmpCompGrpMst(OldMstVo);
						hrEisEmpCompMpgVO.setIsactive(1);
						hrEisEmpCompMpgVO.setStartDate(EffectDate);
						hrEisEmpCompMpgVO.setRemarks(Remarks);
						listDeduct.add(hrEisEmpCompMpgVO);
						/*
						 * logger.info("====> hrEisEmpCompMpgVO :: "+hrEisEmpCompMpgVO.getEmpCompId());
						 */
					} else {
						hreisempcompMpg.setCmnLookupMst(cmnLookupMstDeductID);
						hreisempcompMpg.setHrEisEmpCompGrpMst(OldMstVo);
						if (hreisempcompMpg.getIsactive() == 0) {// if Already in DB but not 1 so Update as New Effect
																	// Date
							hreisempcompMpg.setStartDate(EffectDate);
							hreisempcompMpg.setRemarks(Remarks);
						}
						hreisempcompMpg.setIsactive(1);
						hreisempcompMpg.setEndDate(null);
						listDeduct.add(hreisempcompMpg);
						/*
						 * logger.info("====> hrEisEmpCompMpgVO :: "+hrEisEmpCompMpgVO.getEmpCompId());
						 */
					}

				}

				GenericDaoHibernateImpl genDao1 = new GenericDaoHibernateImpl(HrPayLocComMpg.class);
				genDao1.setSessionFactory(serv.getSessionFactory());
				HrEisEmpCompMpg hepdedu = null;
				logger.info("====> 2 listDeduct.size :: " + listDeduct.size());
				for (int i = 0; i < listDeduct.size(); i++) {
					hepdedu = (HrEisEmpCompMpg) listDeduct.get(i);
					hepdedu.setStartDate(EffectDate);
					hepdedu.setRemarks(Remarks);
					genDao1.create(hepdedu);
					ObjectArgs.put("msg", "Records Inserted Successfully");
				}

				// Deduction Insert End........................

				// added by khushal

				cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
				Map loginDetailsMap = (Map) ObjectArgs.get("baseLoginMap");
				dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());

				long userIdFromVogen = hrEisEmpMst.getOrgEmpMst().getOrgUserMst().getUserId();

				OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);
				cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
				Set set = orgUserMstNew.getOrgUserpostRlts();
				OrgUserpostRlt tempObj = (OrgUserpostRlt) set.iterator().next();

				long locId = Long.valueOf(loginMap.get("locationId").toString());

				Map mapForChangedRecords = ObjectArgs;

				mapForChangedRecords.put("changedPostId", tempObj.getOrgPostMstByPostId().getPostId());
				// mapForChangedRecords.put("changedEmpId",empID);
				mapForChangedRecords.put("serviceLocator", serv);
				mapForChangedRecords.put("locId", locId);
				mapForChangedRecords.put("OrgPostMst", tempObj.getOrgPostMstByPostId());
				mapForChangedRecords.put("OrgUserMst", orgUserMstNew);
				mapForChangedRecords.put("cmnDatabaseMst", cmnDatabaseMst);
				GenerateBillServiceHelper generateBillServiceHelper = new GenerateBillServiceHelper();
				long changedRecordPK = generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"
						+ changedRecordPK);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"
						+ changedRecordPK);
				// ended by khushal
				ObjectArgs.put("msg", "Records Updated Successfully");
			}
			// End of Insert Code

			//// setting 0 values for all allownces mapped with employee

			String[] allowList = lArrallowList;
			String[] deducList = lArrDeductList;
			EmpAllwMapDAOImpl empAllwMapDAOImpl = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,
					serv.getSessionFactory());
			DeductionDtlsDAOImpl empDeducImpl = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,
					serv.getSessionFactory());
			HrPayEmpallowMpg empAllwMpgMst = null;
			logger.info("allow list to be inserted is " + allowList.length);
			logger.info("deducList to be inserted is " + deducList.length);

			for (int i = 0; i < allowList.length; i++) {
				if (!allowList[i].equals("")) {
					long allowCode = Long.parseLong(allowList[i]);
					empAllwMpgMst = (HrPayEmpallowMpg) empAllwMapDAOImpl
							.getHrPayEmpallowMpg(hrEisEmpMst.getOrgEmpMst().getEmpId(), allowCode, -1, -1);
					if (empAllwMpgMst == null) {
						empAllwMpgMst = new HrPayEmpallowMpg();
						IdGenerator idGen = new IdGenerator();
						logger.info("in allownace loop new insertion else part :: allowCode" + allowCode + " emp id is "
								+ hrEisEmpMst.getEmpId() + " month is " + -1 + " year is " + -1);
						Long id = idGen.PKGenerator("HR_PAY_EMPALLOW_MPG", ObjectArgs);
						// Long id= IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG",ObjectArgs);
						logger.info("****************************the id generated is " + id);
						empAllwMpgMst.setAllowCode(id);
						hrEisEmpMst.setEmpId(hrEisEmpMst.getEmpId());

						HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
						hrPayAllowTypeMst.setAllowCode(allowCode);
						empAllwMpgMst.setMonth(-1);
						empAllwMpgMst.setYear(-1);
						empAllwMpgMst.setEmpAllowAmount(0); // NEED TO BE CHANGED. on first time calculate..
						empAllwMpgMst.setHrEisEmpMst(hrEisEmpMst);
						empAllwMpgMst.setHrPayAllowTypeMst(hrPayAllowTypeMst);
						empAllwMpgMst.setEmpCurrentStatus(1);
						empAllwMpgMst.setCreatedDate(new java.util.Date());
						empAllwMpgMst.setCmnDatabaseMst(cmnDatabaseMst);
						empAllwMpgMst.setOrgPostMstByCreatedByPost(orgPostMst);
						empAllwMpgMst.setCmnLocationMst(cmnLocationMst);
						empAllwMpgMst.setOrgUserMstByCreatedBy(orgUserMstByUserId);
						empAllwMpgMst.setTrnCounter(new Integer(1));
						empAllwMapDAOImpl.create(empAllwMpgMst);
					}
				}
			}

			for (int i = 0; i < deducList.length; i++) {
				logger.info("====> deducList[i] :: " + deducList[i]);
				if (!deducList[i].equals("")) {
					long deducCode = Long.parseLong(deducList[i]);
					List<HrPayDeductionDtls> deduclst = empDeducImpl
							.getDeductionDtls(hrEisEmpMst.getOrgEmpMst().getEmpId(), deducCode, -1, -1);

					if (deduclst == null || deduclst.size() == 0 || deduclst.get(0) == null) {
						HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();
						IdGenerator idGen = new IdGenerator();
						logger.info("in deduction insertion loop::: inserting deduc " + deducCode + " emp id is "
								+ hrEisEmpMst.getEmpId());
						Long deducid = idGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", ObjectArgs);
						// Long deducid =IDGenerateDelegate.getNextId("HR_PAY_DEDUCTION_DTLS",
						// ObjectArgs);
						logger.info("****************************the id generated is " + deducid);
						// empAllwMpg.setAllowCode(deducid);
						hrPayDeductionDtls.setDeducDtlsCode(deducid);
						hrPayDeductionDtls.setCreatedDate(new java.util.Date());
						hrPayDeductionDtls.setCmnDatabaseMst(cmnDatabaseMst);
						hrPayDeductionDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						hrPayDeductionDtls.setUpdatedDate(new java.util.Date());
						hrPayDeductionDtls.setOrgPostMstByCreatedByPost(orgPostMst);
						hrPayDeductionDtls.setCmnLocationMst(cmnLocationMst);
						hrPayDeductionDtls.setOrgUserMstByCreatedBy(orgUserMstByUserId);
						hrPayDeductionDtls.setEmpCurrentStatus(1);
						HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
						hrPayDeducTypeMst.setDeducCode(deducCode);
						hrPayDeductionDtls.setMonth(-1);
						hrPayDeductionDtls.setYear(-1);
						hrPayDeductionDtls.setEmpDeducAmount(0);
						hrPayDeductionDtls.setHrPayDeducTypeMst(hrPayDeducTypeMst);
						hrPayDeductionDtls.setHrEisEmpMst(hrEisEmpMst);
						hrPayDeductionDtls.setTrnCounter(new Integer(1));
						empDeducImpl.create(hrPayDeductionDtls);
					}
				}

			}

			///// end setting values
			/*
			 * //For Getting Data ISActive or Not For Active Compo_gropu_id?? List
			 * HrEisEmpCompMpgList=null; HrEisEmpCompMpgList=(List)
			 * empCompMpgDAOImpl.getDataIDisPresent(lArrallowList,lArrDeductList,
			 * SizeofChkValofAllow,SizeofChkValofDedu,grpId ) ;
			 * 
			 * if(HrEisEmpCompMpgList.size()!=0) {
			 * logger.info("===> HrPayLocMpgList :: "+HrEisEmpCompMpgList.size()); for(int
			 * i=0;i<HrEisEmpCompMpgList.size();i++) {
			 * logger.info("===> in getDataIDisPresent save and update method :: "+i);
			 * HrEisEmpCompMpg hep =(HrEisEmpCompMpg)HrEisEmpCompMpgList.get(i);
			 * logger.info("====> id in for :: "+hep.getEmpCompId()); hep.setIsactive(0);
			 * serv.getSessionFactory().getCurrentSession().saveOrUpdate(hep); } }
			 * 
			 * // End For Getting Data ISActive or Not For Active Compo_gropu_id??
			 */
			/*
			 * // IS_ACTIVE 0 Converting When PayCommission changed.... List
			 * ListhrEisMpgData=null; ListhrEisMpgData = (List)
			 * empCompMpgDAOImpl.getHrEisMpgDataFromEmpID(hdnEmpId);
			 * 
			 * 
			 * for(int i=0;i<ListhrEisMpgData.size();i++) {
			 * logger.info("===> in ListhrEisMpgData save and update method :: "+i);
			 * updatehreisMpg =(HrEisEmpCompMpg)ListhrEisMpgData.get(i);
			 * logger.info("====> id in for :: "+updatehreisMpg.getEmpCompId());
			 * updatehreisMpg.setIsactive(0);
			 * 
			 * } // IS_ACTIVE 0 Converting When PayCommission changed....
			 */ EmpCompMpgDAOImpl empCompMpgDAO = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class,
					serv.getSessionFactory());
			if (hdnEmpId != 0) {
				Date WEFDate = empCompMpgDAO.getWEFDataFromEmpId(hdnEmpId);
				logger.info("====> For View in Service WEFDate :: " + WEFDate);
				ObjectArgs.put("WEFDate", WEFDate);
				ObjectArgs.put("remarks", Remarks);
			}

			if (hdnEmpId != 0) {
				logger.info("====> Before DesgName empId :: " + hdnEmpId);
				String DesgName = empCompMpgDAO.getDesigNameFromEmpId(String.valueOf(hdnEmpId));
				logger.info("====> DesgName :: " + DesgName);
				ObjectArgs.put("DesgName", DesgName);
			}

			ObjectArgs.put("PayCommissionID", PayCommission);
			/*
			 * EmpDAOImpl orgEmpDAOImpl = new EmpDAOImpl(OrgEmpMst.class, serv
			 * .getSessionFactory());
			 */
			EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());

			OrgEmpMst orgEmpMstgu = new OrgEmpMst();
			orgEmpMstgu.setEmpId(hrEisEmpMst.getOrgEmpMst().getEmpId());
			// OrgGradeMst orgGradeMst=null;
			// long GradeId=0;
			// OrgEmpMst empVO =orgEmpDAOImpl.read(hrEisEmpMst.getOrgEmpMst().getEmpId());
			//

			HrEisEmpMst hrEisEmpMstNew = new HrEisEmpMst();
			hrEisEmpMstNew.setEmpGender(hrEisEmpMst.getEmpGender());
			hrEisEmpMstNew.setEmpId(hrEisEmpMst.getEmpId());
			// Map loginDetailsMap = (Map) ObjectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			// hrEisEmpMst.setEmpNativePlace(hreiempMst.getEmpNativePlace());
			hrEisEmpMstNew.setCmnLanguageMst(hrEisEmpMst.getCmnLanguageMst());
			if (langId != 1) {
				CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				String cmnLookupName = cmnLookupDao.read(hrEisEmpMst.getEmpRecruitSrc()).getLookupName();
				CmnLookupMst cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(cmnLookupName, langId);
				if (cmnLookupMst != null)
					hrEisEmpMstNew.setEmpRecruitSrc(cmnLookupMst.getLookupId());

				/*
				 * cmnLookupName=cmnLookupDao.read(Long.parseLong(hreiempMst.
				 * getEmpReligionId())).getLookupName();
				 */

				cmnLookupMst = new CmnLookupMst();
				cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
						hrEisEmpMst.getCmnLookupMstByEmpReligionId().getLookupId(), langId);
				if (cmnLookupMst != null)
					hrEisEmpMstNew.setCmnLookupMstByEmpReligionId(cmnLookupMst);

				cmnLookupName = cmnLookupDao.read(Long.parseLong(hrEisEmpMst.getEmpSalDisbMd())).getLookupName();
				cmnLookupMst = new CmnLookupMst();
				cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(cmnLookupName, langId);
				if (cmnLookupMst != null)
					hrEisEmpMstNew.setEmpSalDisbMd(String.valueOf(cmnLookupMst.getLookupId()));

				cmnLookupName = cmnLookupDao.read((hrEisEmpMst.getEmpType())).getLookupName();
				cmnLookupMst = new CmnLookupMst();
				cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(cmnLookupName, langId);
				if (cmnLookupMst != null)
					hrEisEmpMstNew.setEmpType((cmnLookupMst.getLookupId()));

				// cmnLookupName=cmnLookupDao.read((hreiempMst.getEmpCategory())).getLookupName();
				cmnLookupMst = new CmnLookupMst();
				cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
						hrEisEmpMst.getCmnLookupMstByEmpCategoryId().getLookupId(), langId);
				if (cmnLookupMst != null)
					hrEisEmpMstNew.setCmnLookupMstByEmpCategoryId(cmnLookupMst);

				cmnLookupName = cmnLookupDao.read(Long.parseLong((hrEisEmpMst.getEmpStatusId()))).getLookupName();
				cmnLookupMst = new CmnLookupMst();
				cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpNameAndLang(cmnLookupName, langId);
				if (cmnLookupMst != null)
					hrEisEmpMstNew.setEmpStatusId(String.valueOf((cmnLookupMst.getLookupId())));
				cmnLookupMst = new CmnLookupMst();
				cmnLookupMst = cmnLookupDao
						.getLookUpVOByLookUpIDAndLang(hrEisEmpMst.getCmnLookupMstByEmpCasteId().getLookupId(), langId);
				if (cmnLookupMst != null) {
					hrEisEmpMstNew.setCmnLookupMstByEmpCasteId(cmnLookupMst);
				}
				cmnLookupMst = new CmnLookupMst();
				cmnLookupMst = cmnLookupDao.getLookUpVOByLookUpIDAndLang(
						hrEisEmpMst.getCmnLookupMstByEmpSubCasteId().getLookupId(), langId);
				if (cmnLookupMst != null) {
					hrEisEmpMstNew.setCmnLookupMstByEmpSubCasteId(cmnLookupMst);
				}
			} else {
				hrEisEmpMstNew.setEmpRecruitSrc(hrEisEmpMst.getEmpRecruitSrc());
				hrEisEmpMstNew.setCmnLookupMstByEmpReligionId(hrEisEmpMst.getCmnLookupMstByEmpReligionId());
				hrEisEmpMstNew.setEmpSalDisbMd(hrEisEmpMst.getEmpSalDisbMd());
				hrEisEmpMstNew.setEmpType(hrEisEmpMst.getEmpType());
				hrEisEmpMstNew.setEmpStatusId(hrEisEmpMst.getEmpStatusId());
				// hrEisEmpMst.setEmpCategory(hreiempMst.getEmpCategory());
				hrEisEmpMstNew.setCmnLookupMstByEmpCategoryId(hrEisEmpMst.getCmnLookupMstByEmpCategoryId());
			}

			hrEisEmpMstNew.setEmpHobby(hrEisEmpMst.getEmpHobby());
			if (hrEisEmpMst.getCmnCountryMstByEmpNationality() != null)
				hrEisEmpMst.setCmnCountryMstByEmpNationality(hrEisEmpMst.getCmnCountryMstByEmpNationality());
			hrEisEmpMstNew.setCmnLookupMstByEmpCasteId(hrEisEmpMst.getCmnLookupMstByEmpCasteId());
			hrEisEmpMstNew.setEmpConfDueDt(hrEisEmpMst.getEmpConfDueDt());
			// hrEisEmpMst.setEmpSubCaste(hreiempMst.getEmpSubCaste());
			hrEisEmpMstNew.setCmnLookupMstByEmpSubCasteId(hrEisEmpMst.getCmnLookupMstByEmpSubCasteId());
			hrEisEmpMstNew.setEmail(hrEisEmpMst.getEmail());
			hrEisEmpMstNew.setContactNo(hrEisEmpMst.getContactNo());
			// rEisEmpMst.
			if (langId != 1) {
				OrgUserMst ElementCode = new OrgUserMst();
				ElementCode = hrEisEmpMst.getOrgEmpMst().getOrgUserMst();

				List<OrgEmpMst> orgEmpMstList = empinfodao.getOrgData(ElementCode, langId);
				if (orgEmpMstList.size() > 0) {
					orgEmpMstgu = (orgEmpMstList.get(0));
				} else {
					orgEmpMstgu = hrEisEmpMst.getOrgEmpMst();
				}
			} else {
				orgEmpMstgu = hrEisEmpMst.getOrgEmpMst();
			}
			//
			hrEisEmpMstNew.setOrgEmpMst(orgEmpMstgu);

			ObjectArgs.put("hrEisEmpMst", hrEisEmpMstNew);

			long empId = hrEisEmpMst.getEmpId();
			/// Code For Getting value of Payid form Emp ID
			logger.info("Passing value of emp iD :: " + empId);

			List<HrEisOtherDtls> hreisOtherDtlsList = empCompMpgDAO.getEmpPayID(empId);

			logger.info("===> hreisOtherDtlsList.size :: " + hreisOtherDtlsList.size());
			StringBuffer lStrPayId = new StringBuffer();
			for (HrEisOtherDtls xyz : hreisOtherDtlsList) {
				if (xyz.getHrEisSgdMpg() != null) {
					lStrPayId.append(
							String.valueOf(xyz.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId()));
				}
			}
			logger.info("===> lStrPayId :: " + lStrPayId.toString());
			long payid = 0;
			if (!(lStrPayId.toString()).equals("")) {
				payid = Long.valueOf(lStrPayId.toString());
			}

			/// End Code For Getting value of Payid form Emp ID

			// =========> Code For Getting Mapped Allowance Comopent Mapped With Dept Comp
			// Mpg

			logger.info("====> loggedInpostId :: " + loggedInpostId);
			DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class,
					serv.getSessionFactory());
			List<OrgPostDetailsRlt> locIds = deptcomoMpgDAOImpl.getLocIds(loggedInpostId);
			long locId = locIds.get(0).getCmnLocationMst().getLocId();
			logger.info("insertSchemeDtls location id ::" + locId);

			String locname = locIds.get(0).getCmnLocationMst().getLocName();
			logger.info("==> locname :: " + locname);
			ObjectArgs.put("locname", locname);

			DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,
					serv.getSessionFactory());
			EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class, serv.getSessionFactory());
			List<HrPayLocComMpg> MappedComoId = empAllwMapDAO.getMappedCompoIDfromCompMpg(locId);
			StringBuffer MappedCompoId = new StringBuffer();
			for (HrPayLocComMpg xyz : MappedComoId) {
				if (xyz.getCmnLookupMst() != null) {
					MappedCompoId.append(String.valueOf(xyz.getCompId()) + ",");
				}
			}

			if (MappedCompoId.length() != 0) {
				MappedCompoId.deleteCharAt(MappedCompoId.length() - 1);
			}
			logger.info("====> str :: " + MappedComoId.size() + "=====> " + MappedCompoId);

			String lStrMappedComoId = MappedCompoId.toString();
			if (lStrMappedComoId.equals(null) || lStrMappedComoId.equals("")) {
				lStrMappedComoId = "0";
			}

			logger.info("==> Passing value Allow:: " + lStrMappedComoId);
			List<HrPayAllowTypeMst> MappedallowList = empAllwMapDAO.getAllowListfromDeptCompMpg(payid,
					lStrMappedComoId);

			logger.info("====> MappedallowList Size:: " + MappedallowList.size());
			ObjectArgs.put("MappedallowListSize", MappedallowList.size());
			ObjectArgs.put("MappedallowList", MappedallowList);
			// =========> End Code For Getting Mapped Allowance Comopent Mapped With Dept
			// Comp Mpg

			// =========> Code For Getting Mapped Deduction Comopent Mapped With Dept Comp
			// Mpg

			List<HrPayLocComMpg> MappedCompoIdForDedu = empDuducDtlsDAO.getMappedCompoIDfromCompMpg(locId);
			StringBuffer MappedCompoIdDedu = new StringBuffer();
			for (HrPayLocComMpg xyz : MappedCompoIdForDedu) {
				if (xyz.getCmnLookupMst() != null) {
					MappedCompoIdDedu.append(String.valueOf(xyz.getCompId()) + ",");
				}
			}

			if (MappedCompoIdDedu.length() != 0) {
				MappedCompoIdDedu.deleteCharAt(MappedCompoIdDedu.length() - 1);
			}
			logger.info("====> str :: " + MappedCompoIdForDedu.size() + "=====> " + MappedCompoIdDedu);

			String lStrMappedComoIdDedu = MappedCompoIdDedu.toString();
			if (lStrMappedComoIdDedu.equals(null) || lStrMappedComoIdDedu.equals("")) {
				lStrMappedComoIdDedu = "0";
			}

			logger.info("==> Passing value Dedu :: " + lStrMappedComoIdDedu);
			List<HrPayDeducTypeMst> MappedDeduList = empDuducDtlsDAO.getDeduListfromDeptCompMpg(payid,
					lStrMappedComoIdDedu);

			logger.info("====> MappedDeduList Size:: " + MappedDeduList.size());
			ObjectArgs.put("MappedDeduListSize", MappedDeduList.size());
			ObjectArgs.put("MappedDeduList", MappedDeduList);

			// =========> Code For Getting Mapped Deduction Comopent Mapped With Dept Comp
			// Mpg

			Calendar cal = Calendar.getInstance();
			java.util.Date today = cal.getTime();
			logger.info("===> today :: " + today);
			ObjectArgs.put("current_date", today);
			this.logger.info(" Employee id " + hdnEmpId);
			if (hdnEmpId != 0L) {
				ObjectArgs.put("isDCPSStop", Boolean.valueOf(empCompMpgDAO.isDCPSStop(hdnEmpId)));
				ObjectArgs.put("isDCPS", Boolean.valueOf(empCompMpgDAO.isDCPS(empId)));
				this.logger.info(" isDCPS= " + ObjectArgs.get("isDCPS"));
			} else {
				ObjectArgs.put("isDCPS", Boolean.valueOf(false));
				ObjectArgs.put("isDCPSStop", Boolean.valueOf(false));
			}
			ObjectArgs.put("DCPSorGPF", Character.valueOf(empCompMpgDAO.isDCPS1(empId)));
			ObjectArgs.put("DCG", empCompMpgDAO.isDCPS2(empId));

			resultObject.setViewName("EmpCompMpg");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(ObjectArgs);

			logger.info("Records Inserted Successfully");

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public ResultObject getBillMonthYearData(Map ObjectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) ObjectArgs.get("serviceLocator");
		try {
			Map loginDetailsMap = (Map) ObjectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			CmnLookupMstDAOImpl cmnLookupMstObj = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			List lMonthList = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("Month", langId);
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			long finYearId = Long.parseLong(resourceBundle.getString("finYearId"));
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			EmpCompMpgDAOImpl empCompMpg = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class, serv.getSessionFactory());
			List billNoList = empCompMpg.getBillNo(finYearId, locId);
			/*
			 * PayBillDAOImpl payBillDAO= new
			 * PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory()); List billNoList
			 * = payBillDAO.getBillNo(finYearId,locId);
			 */

			// ----------to get year for dropdown---------------------
			ArrayList lArrYrLst = new ArrayList();
			Calendar currentCal = Calendar.getInstance();
			int year = currentCal.get(Calendar.YEAR);
			logger.info("-----YEAR---------" + year);

			for (int yr = 1970; yr <= year; yr++) {
				lArrYrLst.add(yr);
			}

			logger.info("=====> in service getBillMonthYearData billNoList :: " + billNoList.size());
			logger.info("=====> in service getBillMonthYearData lMonthList :: " + lMonthList.size());
			logger.info("=====> in service getBillMonthYearData lArrYrLst :: " + lArrYrLst.size());

			ObjectArgs.put("billNoList", billNoList);
			ObjectArgs.put("lMonthList", lMonthList);
			ObjectArgs.put("lArrYrLst", lArrYrLst);

			resultObject.setViewName("ViewOuterFirst");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(ObjectArgs);

			logger.info("View ViewOuterFirst SUCCESSFULLY");

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public ResultObject getAllowDeductIDFromEmpId(Map objectArgs) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try {
			// Map voToService = (Map) objectArgs.get("voToServiceMap");
			long EmpId = 0;
			if (StringUtility.getParameter("EmpId", request) != null)
				EmpId = Long.valueOf(StringUtility.getParameter("EmpId", request).toString());
			logger.info("EmpId EmpId is ------------>>>>>***" + EmpId);

			EmpCompMpgDAOImpl empCompMpg = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class, serv.getSessionFactory());

			// Date date=new Date();

			List<HrEisEmpCompMpg> listHrPayDeptCompAllow = empCompMpg.getDataAllowChcked(EmpId);
			List<HrEisEmpCompMpg> listHrPayDeptCompDeduct = empCompMpg.getDataDeductChcked(EmpId);

			logger.info("=====> getAllowDeductIDFromDept listHrPayDeptCompAllow :: " + listHrPayDeptCompAllow.size());
			logger.info("=====> getAllowDeductIDFromDept listHrPayDeptCompDeduct :: " + listHrPayDeptCompDeduct.size());

			StringBuffer StrBufAllow = new StringBuffer();
			// StrBufAllow.append("<MainHead>");
			if (listHrPayDeptCompAllow != null) {
				StrBufAllow.append("<AllowHead>");
				for (int i = 0; i < listHrPayDeptCompAllow.size(); i++) {
					HrEisEmpCompMpg hreisEmpComMpg = listHrPayDeptCompAllow.get(i);

					logger.info(hreisEmpComMpg.getCompId());
					StrBufAllow.append("<AllowID>").append(hreisEmpComMpg.getCompId()).append("</AllowID>");

				}
				StrBufAllow.append("</AllowHead>");
			}
			if (listHrPayDeptCompDeduct != null) {
				StrBufAllow.append("<DeductHead>");
				for (int i = 0; i < listHrPayDeptCompDeduct.size(); i++) {
					HrEisEmpCompMpg hreisEmpComMpg = listHrPayDeptCompDeduct.get(i);

					logger.info(hreisEmpComMpg.getCompId());
					StrBufAllow.append("<DeductID>").append(hreisEmpComMpg.getCompId()).append("</DeductID>");

				}
				StrBufAllow.append("</DeductHead>");
			}

			// StrBufAllow.append("</MainHead>");
			logger.info("====> " + StrBufAllow.toString());
			logger.info("====> StrBufAllow :: " + StrBufAllow.toString());
			String AllowDeductCheckedList = new AjaxXmlBuilder().addItem("ajax_key", StrBufAllow.toString()).toString();
			objectArgs.put("ajaxKey", AllowDeductCheckedList);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");

		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;

	}

}