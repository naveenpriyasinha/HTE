package com.tcs.sgv.dcps.service;

//com.tcs.sgv.dcps.service.DCPSNewEntryVOGenerator
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.util.Logger;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;

public class NewRegistrationVOGenerator extends ServiceImpl implements
		VOGeneratorService {

	@Override
	public ResultObject generateMap(Map inputMap) {

		// TODO Auto-generated method stub

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		MstEmp lObjEmpData = null;
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;

		try {
			inputMap.put("DCPSEmpData", lObjEmpData);
			lObjEmpData = generateEmpData(inputMap);
			inputMap.put("DCPSEmpData", lObjEmpData);

			lObjRltDcpsPayrollEmp = generateEmpPayrollData(inputMap);
			inputMap.put("DCPSEmpPayrollData", lObjRltDcpsPayrollEmp);

			objRes.setResultValue(inputMap);
		} catch (Exception ex) {
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			ex.printStackTrace();
		}

		return objRes;
	}

	private MstEmp generateEmpData(Map inputMap) throws Exception {

		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator servLoc = (ServiceLocator) inputMap
				.get("serviceLocator");
		NewRegDdoDAO dcpsRegisDAO = new NewRegDdoDAOImpl(MstEmp.class, servLoc
				.getSessionFactory());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String saveOrUpdateFlag = StringUtility.getParameter(
				"saveOrUpdateFlag", request);
		Integer intSaveOrUpdateFlag = Integer.parseInt(saveOrUpdateFlag);
		Long lLngEmpId = 0l;
		MstEmp lObjEmpData = null;
		Long lLongRegStatus = 0L;
		Logger logger = Logger.getLogger(NewRegistrationVOGenerator.class);
		
		
		if (intSaveOrUpdateFlag == 1) {
			if (lObjEmpData == null) {
				lObjEmpData = new MstEmp();
			}
		}

		if (intSaveOrUpdateFlag == 2) {

			lLngEmpId = Long.parseLong(StringUtility.getParameter("empId",
					request));
			lObjEmpData = (MstEmp) dcpsRegisDAO.read(lLngEmpId);
			// lObjEmpData.setFormStatus(MstEmpObj.getFormStatus());
		}
		inputMap.put("lLngEmpId", lLngEmpId);

		// Get the audit purpose details from session helper
		Long gLngPostId = SessionHelper.getPostId(inputMap);
		Long gLngUserId = SessionHelper.getUserId(inputMap);
		Long lLngDbId = SessionHelper.getDbId(inputMap);
		Long lLngLocId = SessionHelper.getLocationId(inputMap);
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		Date gDtCurrDt = DBUtility.getCurrentDateFromDB();

		try {

			String lStrEmpName = StringUtility.getParameter("txtName", request).trim();
			logger.info("lStrEmpName :::::::::::::"+lStrEmpName);
			String lStrNameMarathi = StringUtility.getParameter("txtNameInMarathi", request).trim();
			logger.info("lStrNameMarathi :::::::::::::"+lStrNameMarathi);
			String lStrFatherHusband = StringUtility.getParameter("txtFatherOrHusband", request).trim();
			logger.info("lStrFatherHusband :::::::::::::"+lStrFatherHusband);
			String lStrSalutation = StringUtility.getParameter("cmbSalutation",request).trim();
			logger.info("lStrSalutation :::::::::::::"+lStrSalutation);
			
			//Added By Mayuresh
			
			String lStrAppointment = StringUtility.getParameter("empAppointment", request).trim();
			logger.info("AppointMent :::::::::::::"+lStrAppointment);
			String lStrStartDate = StringUtility.getParameter("startDate", request).trim();
			logger.info("lStrStartDate :::::::::::::"+lStrStartDate);
			String lStrEndDate = StringUtility.getParameter("endDate", request).trim();
			logger.info("lStrEndDate :::::::::::::"+lStrEndDate);
			
			// Ended By Mayuresh
			
			String lStrGender = StringUtility.getParameter("radioGender",request).trim();
			logger.info("lStrGender :::::::::::::"+lStrGender);
			
			String lStrBirthDate = StringUtility.getParameter("txtBirthDate",request).trim();
			logger.info("lStrBirthDate :::::::::::::"+lStrBirthDate);
			
			String lStrSuperAnnDate = StringUtility.getParameter("txtSuperAnnDate",request).trim();
			logger.info("lStrSuperAnnDate :::::::::::::"+lStrSuperAnnDate);
			
			String lStrJoiningDate = StringUtility.getParameter("txtJoiningDate", request).trim();
			logger.info("lStrJoiningDate :::::::::::::"+lStrJoiningDate);
			
			String lStrDesignation = StringUtility.getParameter("cmbDesignation", request).trim();
			logger.info("lStrDesignation :::::::::::::"+lStrDesignation);
			
			String lStrPayCommission = StringUtility.getParameter("cmbPayCommission", request).trim();
			logger.info("lStrPayCommission :::::::::::::"+lStrPayCommission);
			
			String lStrBasicPay = StringUtility.getParameter("txtBasicPay",request).trim();
			logger.info("lStrBasicPay :::::::::::::"+lStrBasicPay);
			
			String lStrPayScale = StringUtility.getParameter("cmbPayScale",request).trim();
			logger.info("lStrPayScale :::::::::::::"+lStrPayScale);
			
			String lStrBuilding = StringUtility.getParameter("txtAddressBuilding", request).trim();
			logger.info("lStrBuilding :::::::::::::"+lStrBuilding);
			
			String lStrStreet = StringUtility.getParameter("txtAddressStreet",request).trim();
			logger.info("lStrStreet :::::::::::::"+lStrStreet);
			
			String lStrLandmark = StringUtility.getParameter("txtLandmark",request).trim();
			logger.info("lStrLandmark :::::::::::::"+lStrLandmark);
			
			String lStrLocality = StringUtility.getParameter("txtLocality",request).trim();
			logger.info("lStrLocality :::::::::::::"+lStrLocality);
			
			String lStrDistrict = StringUtility.getParameter("txtDistrict",request).trim();
			logger.info("lStrDistrict :::::::::::::"+lStrDistrict);
			
			String lStrState = StringUtility.getParameter("cmbState", request).trim();
			logger.info("lStrState :::::::::::::"+lStrState);
			
			String lStrPinCode = StringUtility.getParameter("txtPinCode",request).trim();
			logger.info("lStrPinCode :::::::::::::"+lStrPinCode);
			
			String lStrCellNo = StringUtility.getParameter("txtCellNo", request).trim();
			String lStrContactTelNo = StringUtility.getParameter("txtContactTelNo", request).trim();
			String lStrEmailId = StringUtility.getParameter("txtEmailId",request).trim();

			String lStrDDOCode = StringUtility.getParameter("txtDdoCode",request).trim();

			String lStrParentFldDept = StringUtility.getParameter("listParentDept", request).trim();
			String lStrReasonForChangeInPFD = StringUtility.getParameter("reasonForChangeInPFD", request).trim();
			String lStrCadre = StringUtility.getParameter("cmbCadre", request).trim();
			String lStrGroup = StringUtility.getParameter("txtGroup", request).trim();
			String lStrCurrentOffice = StringUtility.getParameter("cmbCurrentOffice", request).trim();

			String lStrFirstDesignation = StringUtility.getParameter("cmbFirstDesignation", request).trim();
			String lStrAppointmentDate = StringUtility.getParameter("txtJoinParentDeptDate", request).trim();
			String lStrRemarks = StringUtility.getParameter("txtRemarks",request).trim();
			
			String lStrTeaching = StringUtility.getParameter("Teaching",request).trim();
			System.out.println("lStrTeaching:::"+lStrTeaching);
			String lStrtxtinduvisalno = StringUtility.getParameter("txtinduvisalno",request).trim();
			System.out.println("lStrtxtinduvisalno:::"+lStrtxtinduvisalno);
			String lStrtxtinduvisalDate = StringUtility.getParameter("txtinduvisalDate",request).trim();
			System.out.println("lStrtxtinduvisalDate:::"+lStrtxtinduvisalDate);
			
			
			String lStrEIDNo = StringUtility.getParameter("txtEIDNo", request).trim();
			System.out.println("lStrEIDNo:::"+lStrEIDNo);
			
			String lStrUIDNo1 = StringUtility.getParameter("txtUIDNo1", request).trim();
			System.out.println("lStrUIDNo1:::"+lStrUIDNo1);
			
			String lStrUIDNo2 = StringUtility.getParameter("txtUIDNo2", request).trim();
			System.out.println("lStrUIDNo2:::"+lStrUIDNo2);
			
			String lStrUIDNo3 = StringUtility.getParameter("txtUIDNo3", request).trim();
			System.out.println("lStrUIDNo3:::"+lStrUIDNo3);
			
			String lStrUIDNo = lStrUIDNo1.concat(lStrUIDNo2.concat(lStrUIDNo3));
			System.out.println("lStrUIDNo:::"+lStrUIDNo);
			
			String lStrPANNo = StringUtility.getParameter("txtPANNo", request);
			System.out.println("lStrPANNo:::"+lStrPANNo);
			
			String lStrBankName = StringUtility.getParameter("cmbBankName",request).trim();
			System.out.println("lStrBankName:::"+lStrBankName);
			
			String lStrBranchName = StringUtility.getParameter("cmbBranchName",request).trim();
			System.out.println("lStrBranchName:::"+lStrBranchName);
			
			String lStrIFSCCode = StringUtility.getParameter("txtIFSCCode",request).trim();
			System.out.println("lStrIFSCCode:::"+lStrIFSCCode);
			
			String lStrBankAccountNo = StringUtility.getParameter("txtbankAccountNo", request).trim();
			System.out.println("lStrBankAccountNo:::"+lStrBankAccountNo);
			
			String lStrDcpsOrGPF = StringUtility.getParameter("radioDCPS",request).trim();
			System.out.println("lStrDcpsOrGPF:::"+lStrDcpsOrGPF);
			
			String lStrDcpsAcMntndBy = StringUtility.getParameter("dcpsAcntMntndBy", request).trim();
			System.out.println("lStrDcpsAcMntndBy:::"+lStrDcpsAcMntndBy);
			
			
			
			String lStrAcNoNonSRKAEmp = StringUtility.getParameter("txtAcNoForNonSRKAEmp", request).trim();
			System.out.println("lStrAcNoNonSRKAEmp:::"+lStrAcNoNonSRKAEmp);
			
			String lStrAccOthers = StringUtility.getParameter("txtOthersNonSRKAEmp", request).trim();
			
			
			logger.info("lStrAccOthers ***********"+lStrAccOthers);
			
			String ratePerHour = StringUtility.getParameter("ratePerHour", request);
			Long ratePerHours=0l;
			logger.info("ratePerHours ***********"+ratePerHours);
			if(ratePerHour !=null && !ratePerHour.equals("")){
				ratePerHours=Long.parseLong(ratePerHour);
			}
			
			lObjEmpData.setRatePerHour(ratePerHours);
			/*String lStrAcMntndByOthers = StringUtility.getParameter(
					"txtAcNoMntndByOthers", request).trim();*/
			
			String lStrAcntMntndByOtherState = StringUtility.getParameter("dcpsAcntMntndByOtherState", request).trim();
			logger.info("lStrAcntMntndByOtherState ***********"+lStrAcntMntndByOtherState);
			
			String lStrAddressVTC = StringUtility.getParameter("txtAddressVTC", request).trim();
			logger.info("lStrAddressVTC ***********"+lStrAddressVTC);
			
			String lStrBuckleNo = StringUtility.getParameter("txtBuckleNo", request).trim();
			logger.info("lStrBuckleNo ***********"+lStrBuckleNo);
			
			//added by Demolisher
			String moreQuali = null;
			
			String moreQualification = null;
			
			if((StringUtility.getParameter("txtmoreQualification", request)).length()!=0)	
			{
				moreQuali = StringUtility.getParameter("txtmoreQualification", request).trim();
				logger.info("moreQuali ::::::::::::::"+moreQuali);
				
				int moreQualiLength = moreQuali.length();
				logger.info("moreQualiLength ::::::::::::::"+moreQualiLength);
				
				moreQualification = moreQuali.substring(0,moreQuali.length()-1);
				logger.info("moreQualification ::::::::::::::"+moreQualification);
			}	else
			{
				moreQualification = lObjEmpData.getMoreQualification();
			}
			//ended by Demolisher
			
			
			Date dtBirthDate = null;
			if (lStrBirthDate != null && !"".equals(lStrBirthDate.trim())) {
				dtBirthDate = simpleDateFormat.parse(lStrBirthDate.trim());
			}
			
			Date dtSuperAnnDate = null;
			if (lStrSuperAnnDate != null && !"".equals(lStrSuperAnnDate.trim())) {
				dtSuperAnnDate = simpleDateFormat.parse(lStrSuperAnnDate.trim());
			}
			
			Date dtJoiningDate = null;
			if (lStrJoiningDate != null && !"".equals(lStrJoiningDate.trim())) {
				dtJoiningDate = simpleDateFormat.parse(lStrJoiningDate.trim());
			}
			Date dtAppointmentDate = null;
			if (lStrAppointmentDate != null
					&& !"".equals(lStrAppointmentDate.trim())) {
				dtAppointmentDate = simpleDateFormat.parse(lStrAppointmentDate.trim());
			}

			if (lStrNameMarathi != null && !(lStrNameMarathi.equals(""))) {
				lObjEmpData.setName_marathi(lStrNameMarathi.trim());
			}
			if (lStrFatherHusband.equals("")) {
				lStrFatherHusband = "Not Provided";
			}
			lObjEmpData.setFather_or_husband(lStrFatherHusband.trim().toUpperCase());

			if (lStrSalutation != null && !(lStrSalutation.equals(""))) 
			{
				lObjEmpData.setSalutation(lStrSalutation.trim());
			}
			
			
			// Mayuresh 
			
			if (lStrAppointment != null && !(lStrAppointment.equals(""))) 
			{
				lObjEmpData.setEmpAppointment(lStrAppointment);
			}
			
			
			Date dtStartDate = null;
			if (lStrStartDate != null && !"".equals(lStrStartDate.trim())) {
				dtStartDate = simpleDateFormat.parse(lStrStartDate.trim());
			}
			
			
			Date dtEndDate = null;
			if (lStrEndDate != null && !"".equals(lStrEndDate.trim())) 
			{
				logger.info("hii the dat by roshan is "+lStrEndDate.trim());
				dtEndDate = simpleDateFormat.parse(lStrEndDate.trim());
				logger.info("hii the dat by roshan is "+dtEndDate);
				lObjEmpData.setSanctionEnd(dtEndDate);
			}
			
			// End
			
			

			if (lStrParentFldDept != null && !(lStrParentFldDept.equals(""))) {
				lObjEmpData.setParentDept(lStrParentFldDept.trim());
			}

			if (lStrReasonForChangeInPFD != null
					&& !(lStrReasonForChangeInPFD.equals(""))) {
				lObjEmpData.setReasonChangePFD(lStrReasonForChangeInPFD.trim());
			}

			if (lStrCadre != null && !(lStrCadre.equals("-1"))) {
				lObjEmpData.setCadre(lStrCadre.trim());
			}

			if (lStrGroup != null && !(lStrGroup.equals(""))) {
				lObjEmpData.setGroup(lStrGroup.trim());
			}
			if (lStrBasicPay != null && !(lStrBasicPay.equals(""))) {
				lObjEmpData.setBasicPay(Double.parseDouble(lStrBasicPay.trim()));
			}
			if (lStrCurrentOffice != null && !(lStrCurrentOffice.equals("-1"))) {
				lObjEmpData.setCurrOff(lStrCurrentOffice.trim());
			}

			if (lStrDesignation != null && !(lStrDesignation.equals("-1"))) {
				lObjEmpData.setDesignation(lStrDesignation.trim());
			}

			if (lStrPayCommission != null && !(lStrPayCommission.equals("-1"))) {
				lObjEmpData.setPayCommission(lStrPayCommission.trim());
			}
			
			// Code added to store Pay in pay band and grade pay in case of Sixth pay commission
			if(lStrPayCommission.equals("700016"))
			{
				String lStrPayInPayBand = StringUtility.getParameter("txtPayInPayBand", request).trim();
				String lStrGradePay = StringUtility.getParameter("txtGradePay", request).trim();
				if(!"".equals(lStrPayInPayBand))
				{
					lObjEmpData.setPayInPayBand(Long.valueOf(lStrPayInPayBand));
				}
				if(!"".equals(lStrGradePay))
				{
					lObjEmpData.setGradePay(Long.valueOf(lStrGradePay));
				}
			}
			
			// Sets Pay-scale to default 101 for Consolidated pay-commission
			
			if(lStrPayCommission.equals("700337"))
			{
					lObjEmpData.setPayScale("101");
			}
			else
			{
				if (lStrPayScale != null && !(lStrPayScale.equals("-1"))) {
					lObjEmpData.setPayScale(lStrPayScale.trim());
				}
			}

			if (lStrBuilding != null && !(lStrBuilding.equals(""))) {
				lObjEmpData.setBuilding_address(lStrBuilding.trim());
			}

			if (lStrStreet != null && !(lStrStreet.equals(""))) {
				lObjEmpData.setBuilding_street(lStrStreet.trim());
			}

			if (lStrLandmark != null && !(lStrLandmark.equals(""))) {
				lObjEmpData.setLandmark(lStrLandmark.trim());
			}
			if (lStrLocality != null && !(lStrLocality.equals(""))) {
				lObjEmpData.setLocality(lStrLocality.trim());
			}
			if (lStrDistrict != null && !(lStrDistrict.equals(""))) {
				lObjEmpData.setDistrict(lStrDistrict.trim());
			}
			if (lStrState != null && !(lStrState.equals(""))) {
				lObjEmpData.setState(lStrState.trim());
			}
			if (lStrFirstDesignation != null
					&& !(lStrFirstDesignation.equals(""))) {
				lObjEmpData.setFirstDesignation(lStrFirstDesignation.trim());
			}

			if (lStrPinCode != null && !(lStrPinCode.equals(""))) {
				lObjEmpData.setPincode(Long.parseLong(lStrPinCode.trim()));
			}
			if (lStrEmailId != null && !(lStrEmailId.equals(""))) {
				lObjEmpData.setEmailId(lStrEmailId.trim());
			}

			if (lStrCellNo != null && !(lStrCellNo.equals(""))) {
				lObjEmpData.setCellNo(Long.parseLong(lStrCellNo.trim()));
			}

			if (lStrContactTelNo != null && !(lStrContactTelNo.equals(""))) {
				lObjEmpData.setCntctNo(Long.parseLong(lStrContactTelNo.trim()));
			}

			if (lStrRemarks != null && !(lStrRemarks.equals(""))) {
				lObjEmpData.setRemarks(lStrRemarks.trim());
			}

			if (lStrEIDNo != null && !(lStrEIDNo.equals(""))) {
				lObjEmpData.setEIDNo(lStrEIDNo.trim());
			}
			
			if (lStrTeaching != null && !(lStrTeaching.equals(""))) {
				lObjEmpData.setTypeEmp(lStrTeaching.trim());
			}
			
			if (lStrtxtinduvisalno != null && !(lStrtxtinduvisalno.equals(""))) {
				lObjEmpData.setIndivusalno((lStrtxtinduvisalno.trim()));
			}
			
			/*if (lStrPfAccountNo != null && !(lStrPfAccountNo.equals(""))) {
				lObjRltDcpsPayrollEmp.setPfAcNo(lStrPfAccountNo.trim());
			}.
			*/
			Date dtinduDate = null;
			if (lStrtxtinduvisalDate != null && !"".equals(lStrtxtinduvisalDate.trim())) {
				dtinduDate = simpleDateFormat.parse(lStrtxtinduvisalDate.trim());
			}

			if (lStrUIDNo != null && !(lStrUIDNo.equals(""))) {
				lObjEmpData.setUIDNo(lStrUIDNo.trim());
			}

			if (lStrPANNo != null && !(lStrPANNo.equals(""))) {
				lObjEmpData.setPANNo(lStrPANNo.trim());
			}

			if (lStrBankName != null && !(lStrBankName.equals(""))) {
				lObjEmpData.setBankName(lStrBankName.trim());
			}

			if (lStrBranchName != null && !(lStrBranchName.equals(""))) {
				lObjEmpData.setBranchName(lStrBranchName.trim());
			}

			if (lStrIFSCCode != null && !(lStrIFSCCode.equals(""))) {
				lObjEmpData.setIFSCCode(lStrIFSCCode.trim());
			}

			if (lStrBankAccountNo != null && !(lStrBankAccountNo.equals(""))) {
				lObjEmpData.setBankAccountNo(lStrBankAccountNo.trim());
			}

			if (!"".equals(lStrDcpsOrGPF.trim())) {
				lObjEmpData.setDcpsOrGpf(lStrDcpsOrGPF.trim().toCharArray()[0]);
			}

			if (!"".equals(lStrDcpsAcMntndBy.trim())
					&& !(lStrDcpsAcMntndBy.equals("-1"))) {
				lObjEmpData.setAcDcpsMaintainedBy(lStrDcpsAcMntndBy.trim());
			} else {
				lObjEmpData.setAcDcpsMaintainedBy(null);
			}

			if (!"".equals(lStrAcNoNonSRKAEmp.trim())) {
				lObjEmpData.setAcNonSRKAEmp(lStrAcNoNonSRKAEmp.trim());
			}
			
			if (!"".equals(lStrAccOthers.trim())) {
				lObjEmpData.setAcMntndByOthers(lStrAccOthers.trim());
			}
			
			if(!"".equals(lStrAcntMntndByOtherState))
			{
				lObjEmpData.setAcMntndByOtherState(Long.valueOf(lStrAcntMntndByOtherState));
			}
			
			if (!"".equals(lStrAddressVTC.trim())) {
				lObjEmpData.setAddressVTC(lStrAddressVTC.trim());
			}
			
			if(!"".equals(lStrBuckleNo))
			{
				lObjEmpData.setBuckleNo(lStrBuckleNo);
			}
			
			//added by shailesh
			String lStrQualification = StringUtility.getParameter("qualification", request).trim();
			System.out.println("lStrQualification "+lStrQualification);
			if(!"".equals(lStrQualification))
			{
				lObjEmpData.setQualification(lStrQualification);
			}
			lObjEmpData.setName(lStrEmpName.toUpperCase());
			lObjEmpData.setGender(lStrGender.trim().toUpperCase().charAt(0));
			lObjEmpData.setDob(dtBirthDate);
			
			// Mayuresh 
			
			lObjEmpData.setSanctionStart(dtStartDate);
			
			
			// End 
			lObjEmpData.setSuperAnndate(dtSuperAnnDate);
			lObjEmpData.setServEndDate(dtSuperAnnDate);
			lObjEmpData.setDoj(dtJoiningDate);
			lObjEmpData.setIndivisualDate(dtinduDate);
			lObjEmpData.setDdoCode(lStrDDOCode.trim());
			lObjEmpData.setPhyRcvdFormStatus(null);
			lObjEmpData.setEmpOnDeptn(0);
			lObjEmpData.setRegStatus(lLongRegStatus);
			lObjEmpData.setAppointmentDate(dtAppointmentDate);
			lObjEmpData.setRegStatusUpdtdDate(gDtCurrDt);
			lObjEmpData.setPfdChangedBySRKA(0l);

			if (intSaveOrUpdateFlag == 1) {
				lObjEmpData.setFormStatus(0l);
			}
			
			lObjEmpData.setZpStatus(0l);
			lObjEmpData.setDbId(lLngDbId);
			lObjEmpData.setLangId(lLngLangId);
			lObjEmpData.setLocId(lLngLocId);
			if (intSaveOrUpdateFlag == 1) {
				lObjEmpData.setCreatedPostId(gLngPostId);
				lObjEmpData.setCreatedUserId(gLngUserId);
				lObjEmpData.setCreatedDate(gDtCurrDt);
			}
			if (intSaveOrUpdateFlag == 2) {

				lObjEmpData.setUpdatedPostId(gLngPostId);
				lObjEmpData.setUpdatedUserId(gLngUserId);
				lObjEmpData.setUpdatedDate(gDtCurrDt);
			}
			
			lObjEmpData.setMoreQualification(moreQualification);
			// END
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lObjEmpData;
	}

	private RltDcpsPayrollEmp generateEmpPayrollData(Map inputMap)
			throws Exception {

		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator servLoc = (ServiceLocator) inputMap
				.get("serviceLocator");
		NewRegDdoDAO dcpsRegisDAO = new NewRegDdoDAOImpl(
				RltDcpsPayrollEmp.class, servLoc.getSessionFactory());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String lStrPhyChallenged = StringUtility.getParameter("radioHandic",
				request).trim();
		String lStrCurrentPostId = StringUtility.getParameter("cmbCurrentPost",
				request).trim();
		RltDcpsPayrollEmp lObjRltDcpsPayrollEmp = null;
		Boolean PayrollIdExistsOrNot = false;
		Long lLngEmpId = null;

		String lStrDcpsOrGPF = StringUtility.getParameter("radioDCPS", request).trim();

		String saveOrUpdateFlag = StringUtility.getParameter(
				"saveOrUpdateFlag", request).trim();
		Integer intSaveOrUpdateFlag = Integer.parseInt(saveOrUpdateFlag);

		if (intSaveOrUpdateFlag == 1) {
			if (lObjRltDcpsPayrollEmp == null) {
				lObjRltDcpsPayrollEmp = new RltDcpsPayrollEmp();
			}
		}

		if (intSaveOrUpdateFlag == 2) {

			lLngEmpId = Long.parseLong(StringUtility.getParameter("empId",
					request).trim());

			if (dcpsRegisDAO.checkDcpsEmpPayrollIdForEmpIdExists(lLngEmpId)) {

				Long lLngDcpsPayrollId = dcpsRegisDAO
						.getDcpsEmpPayrollIdForEmpId(lLngEmpId);
				lObjRltDcpsPayrollEmp = (RltDcpsPayrollEmp) dcpsRegisDAO
						.read(lLngDcpsPayrollId);
				PayrollIdExistsOrNot = true;

			}
		}

		inputMap.put("lLngEmpId", lLngEmpId);
		inputMap.put("PayrollIdExistsOrNot", PayrollIdExistsOrNot);

		// Get the audit purpose details from session helper
		Long gLngPostId = SessionHelper.getPostId(inputMap);
		Long gLngUserId = SessionHelper.getUserId(inputMap);
		Long lLngDbId = SessionHelper.getDbId(inputMap);
		Long lLngLocId = SessionHelper.getLocationId(inputMap);
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		Long lLngCurrentPostId = null;
		Date gDtCurrDt = DBUtility.getCurrentDateFromDB();

		String lStrJoinPostDate = StringUtility.getParameter("txtJoinPostDate",
				request).trim();
		
		String lStrAcMaintainedBy = StringUtility.getParameter(
				"cmbAcMaintainedBy", request).trim();
		String lStrPFSeries = StringUtility
				.getParameter("cmbPFSeries", request).trim();
		String lStrPfSeriesDesc = StringUtility.getParameter("txtPFSeriesDesc",
				request).trim();
		String lStrPfAccountNo = StringUtility.getParameter("txtPfAccountNo",
				request).trim();

		/* GIS Details */
		String lStrGisApplicable = StringUtility.getParameter(
				"cmbGisApplicable", request).trim();
		
		// Mayuresh
		String lStrGisDescription = StringUtility.getParameter(
				"description", request).trim();
		
		String lStrGisGroup = StringUtility
				.getParameter("cmbGisGroup", request).trim();
		String lStrMembershipDate = StringUtility.getParameter(
				"txtMembershipDate", request).trim();
		
		Date lDtMembershipDate = null;
		if (lStrMembershipDate != null && !"".equals(lStrMembershipDate.trim())) {
			lDtMembershipDate = simpleDateFormat.parse(lStrMembershipDate.trim());
		}

		lObjRltDcpsPayrollEmp.setGisApplicable(Long.parseLong(lStrGisApplicable.trim()));
		
		// Mayuresh
		lObjRltDcpsPayrollEmp.setGisDescription(lStrGisDescription.trim());
		
		lObjRltDcpsPayrollEmp.setGisGroup(Long.parseLong(lStrGisGroup.trim()));
		lObjRltDcpsPayrollEmp.setMembershipDate(lDtMembershipDate);

		// Code changed to hide the issue of mandatory GPF number in payroll
		/*
		if (lStrPfAccountNo.equals("")) {
			lStrPfAccountNo = "123";
		}
		*/

		String lStrPostId = StringUtility.getParameter("cmbCurrentPost",
				request).trim();

		Date dtJoinPostDate = null;
		if (lStrJoinPostDate != null && !"".equals(lStrJoinPostDate.trim())) {
			dtJoinPostDate = simpleDateFormat.parse(lStrJoinPostDate.trim());
		}
		if (lStrPhyChallenged != null && !"".equals(lStrPhyChallenged.trim())) {
			lObjRltDcpsPayrollEmp.setPhychallanged(lStrPhyChallenged.trim());

		}

		if (lStrCurrentPostId != null && !"".equals(lStrCurrentPostId.trim())) {
			lLngCurrentPostId = Long.parseLong(lStrCurrentPostId.trim());
			lObjRltDcpsPayrollEmp.setPostId(lLngCurrentPostId);
		}
	

		if (dtJoinPostDate != null && !(dtJoinPostDate.equals(""))) {
			lObjRltDcpsPayrollEmp.setCurrPostJoiningDate(dtJoinPostDate);
		}

	
		if (lStrDcpsOrGPF.trim().equals("N")) {
			
			if (lStrAcMaintainedBy != null
					&& !(lStrAcMaintainedBy.equals("-1"))) {
				lObjRltDcpsPayrollEmp.setAcMaintainedBy(lStrAcMaintainedBy.trim());
			}

			if (lStrPFSeries != null && !(lStrPFSeries.equals("")) && !(lStrPFSeries.equals("-1"))) {
				lObjRltDcpsPayrollEmp.setPfSeries(lStrPFSeries.trim());
			} else {
				lObjRltDcpsPayrollEmp.setPfSeries(null);
			}

			if (lStrPfSeriesDesc != null && !(lStrPfSeriesDesc.trim().equals(""))) {
				lObjRltDcpsPayrollEmp.setPfSeriesDesc(lStrPfSeriesDesc.trim());
			}

			if (lStrPfAccountNo != null && !(lStrPfAccountNo.equals(""))) {
				lObjRltDcpsPayrollEmp.setPfAcNo(lStrPfAccountNo.trim());
			}
		} else {
			lObjRltDcpsPayrollEmp.setAcMaintainedBy(null);
			lObjRltDcpsPayrollEmp.setPfSeries(null);
			// PF Series Description is stored as "DCPS" for a DCPS Employee as suggested by the Payroll team.
			lObjRltDcpsPayrollEmp.setPfSeriesDesc("DCPS");
			lObjRltDcpsPayrollEmp.setPfAcNo(null);
		}

		if (lStrPostId != null && !lStrPostId.equalsIgnoreCase("")) {
			lObjRltDcpsPayrollEmp.setPostId(Long.parseLong(lStrPostId));
		}

		lObjRltDcpsPayrollEmp.setDbId(lLngDbId);
		lObjRltDcpsPayrollEmp.setLangId(lLngLangId);
		lObjRltDcpsPayrollEmp.setLocId(lLngLocId);
		if (!PayrollIdExistsOrNot) {
			lObjRltDcpsPayrollEmp.setCreatedPostId(gLngPostId);
			lObjRltDcpsPayrollEmp.setCreatedUserId(gLngUserId);
			lObjRltDcpsPayrollEmp.setCreatedDate(gDtCurrDt);
		} else {
			lObjRltDcpsPayrollEmp.setUpdatedPostId(gLngPostId);
			lObjRltDcpsPayrollEmp.setUpdatedUserId(gLngUserId);
			lObjRltDcpsPayrollEmp.setUpdatedDate(gDtCurrDt);
		}
		
		String lStrPhyPTApplicable = StringUtility.getParameter("hidPTApplicableForPhyHandi",
				request).trim();
		if(lStrPhyPTApplicable != null && !"".equals(lStrPhyPTApplicable))
		{
			lObjRltDcpsPayrollEmp.setPhyPTApplicable(lStrPhyPTApplicable.trim().toUpperCase().charAt(0));
		}

		return lObjRltDcpsPayrollEmp;
	}
}
