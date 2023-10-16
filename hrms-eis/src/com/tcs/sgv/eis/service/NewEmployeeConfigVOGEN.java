package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.OrderHeadPostmpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.eis.valueobject.HrPayOfficeMst;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class NewEmployeeConfigVOGEN extends ServiceImpl implements VOGeneratorService{

	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantsBundle = ResourceBundle
	.getBundle("resources.Payroll");

	public final int DEPT_ID = Integer.parseInt(constantsBundle
			.getString("GAD"));

	public ResultObject genNewEmpConfigVOGEN(Map objServiceArgs) 
	{
		Calendar calTime = Calendar.getInstance();
		logger.info("The Inintial time for the genNewEmpConfigVOGEN is:-"+calTime.getTimeInMillis());		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			// Saving info regarding new user
			OrgEmpMst orgEmpMstEng = new OrgEmpMst();
			OrgEmpMst orgEmpMstGuj = new OrgEmpMst();
			SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");		
			Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
			long loggedInUser = Long.parseLong(loginDetailsMap.get("userId").toString());			
			OrgUserMst loggedInOrgUserMst = new OrgUserMst();
			loggedInOrgUserMst.setUserId(loggedInUser);
			
			String flag = StringUtility.getParameter("flag", request); //Flag to decide insertion or updation
			
			long loggedInPost = Long.parseLong(loginDetailsMap.get("primaryPostId").toString());			
			OrgPostMst loggedInOrgPostMst=new OrgPostMst();
			loggedInOrgPostMst.setPostId(loggedInPost);	

			//Existing User Id For Update 
			String gpfAccno="";
			if(StringUtility.getParameter("gpfAcctNo", request)!=null&&!StringUtility.getParameter("gpfAcctNo", request).equals(""))
            	gpfAccno= StringUtility.getParameter("gpfAcctNo", request);
			String userIdForedit = StringUtility.getParameter("userId", request) != null ? StringUtility.getParameter("userId", request) : "";
			String prefix = StringUtility.getParameter("Salutation", request) != null ? StringUtility.getParameter("Salutation", request) : "";
			String empFName = StringUtility.getParameter("emp_first_name", request) != null ? StringUtility.getParameter("emp_first_name", request) : "";
			String empMName = StringUtility.getParameter("emp_middle_name", request) != null ? StringUtility.getParameter("emp_middle_name", request) : "";
			String empLName = StringUtility.getParameter("emp_last_name", request) != null ? StringUtility.getParameter("emp_last_name", request) : "";
			String userGradeId = StringUtility.getParameter("selClass", request) != null ? StringUtility.getParameter("selClass", request) : "";
			String dateOfBirth = StringUtility.getParameter("DOB", request) != null ? StringUtility.getParameter("DOB", request) : "";
			String dateOfJoin = StringUtility.getParameter("DOJ", request) != null ? StringUtility.getParameter("DOJ", request) : "";
			String dateOfRet = StringUtility.getParameter("DOR", request) != null ? StringUtility.getParameter("DOR", request) : "";
			String lStrPostId = StringUtility.getParameter("postId", request) != null ? StringUtility.getParameter("postId", request) : "0";
			String lStrOhpId = StringUtility.getParameter("ohpId", request) != null ? StringUtility.getParameter("ohpId", request) : "0";
			String lStrorderHeadpostId = StringUtility.getParameter("orderHeadpostId", request) != null ? StringUtility.getParameter("orderHeadpostId", request) : "0";
			//Order Details
			String lStrOrderNo = StringUtility.getParameter("txtOrderName", request) != null ? StringUtility.getParameter("txtOrderName", request) : "";
			String lOrderMstId = StringUtility.getParameter("lOrderMstId", request) != null && !StringUtility.getParameter("lOrderMstId", request).equals("") ? StringUtility.getParameter("lOrderMstId", request) : "0";
			long lOrderId = Long.parseLong(lOrderMstId);
			String lStrOrderStartDate = StringUtility.getParameter("txtOrderStartDate", request) != null ? StringUtility.getParameter("txtOrderStartDate", request) : "";
			logger.info("lStrOrderStartDate::::"+lStrOrderStartDate);
			String lStrOrderEndDate = StringUtility.getParameter("txtOrderEndDate", request) != null ? StringUtility.getParameter("txtOrderEndDate", request) : "";
			logger.info("lStrOrderEndDate::::"+lStrOrderEndDate);
			String lStrBillHeadId =  StringUtility.getParameter("billNo", request) != null ? StringUtility.getParameter("billNo", request) : "0";
			long lOhpId =(lStrorderHeadpostId != null && !"".equals(lStrorderHeadpostId)) ? Long.parseLong(lStrorderHeadpostId) : 0;
			long ohpId =(lStrOhpId != null && !"".equals(lStrOhpId)) ? Long.parseLong(lStrOhpId) : 0;
			long postIdTOEdit =(lStrPostId != null && !"".equals(lStrPostId)) ? Long.parseLong(lStrPostId) : 0;
			long userID = (userIdForedit != null && !"".equals(userIdForedit)) ? Long.parseLong(userIdForedit) : 0;			
			OrgUserMst orgUserMst = new OrgUserMst();
			orgUserMst.setUserId(userID);
			long ugdId =(userGradeId != null && !"".equals(userGradeId)) ? Long.parseLong(userGradeId) : 0;
			OrgGradeMst gradeMst = new OrgGradeMst();
			gradeMst.setGradeId(ugdId);	
			logger.info("the grade mst is code is "+userGradeId);

			Date DOB = StringUtility.convertStringToDate(dateOfBirth);
			Date DOJ = StringUtility.convertStringToDate(dateOfJoin);
			Date DOR = StringUtility.convertStringToDate(dateOfRet);
			//Setting OrgUserMst
			SimpleDateFormat Dateformat=new SimpleDateFormat("ddMMMyyyy");
			String BirthDate=Dateformat.format(DOB);
			String userName = empFName.substring(0,1)+empLName+BirthDate;
			if(userName.length()>18)
				userName= userName.substring(0, 18);
			if(!gpfAccno.equals(""))
				userName=gpfAccno;
			userName = userName.replaceAll("/", "");
			String PassWord="a";
			String firstLogin = "N";

			orgUserMst.setUserName(userName);
			orgUserMst.setPassword(PassWord);
			orgUserMst.setActivateFlag(1);			
			orgUserMst.setStartDate(DOJ);			
			orgUserMst.setCreatedDate(new Date());
			orgUserMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			orgUserMst.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);			
			orgUserMst.setFirstlogin(firstLogin);
			orgUserMst.setSecretAnswer(userName);
			orgUserMst.setPwdchangedDate(new Date());
			orgUserMst.setSecretQueCode("Secret_Other");
			orgUserMst.setSecretQueOther("Enter your Username");			
			//
			//Setting OrgEmpMst
			orgEmpMstEng.setEmpPrefix(prefix);
			orgEmpMstEng.setEmpFname(empFName);
			orgEmpMstEng.setEmpMname(empMName);
			orgEmpMstEng.setEmpLname(empLName);
			orgEmpMstEng.setOrgGradeMst(gradeMst);
			orgEmpMstEng.setOrgUserMst(orgUserMst);			
			orgEmpMstEng.setEmpDob(DOB);
			orgEmpMstEng.setEmpDoj(DOJ);
			orgEmpMstEng.setActivateFlag(1);
			orgEmpMstEng.setEmpSrvcExp(DOR);
			orgEmpMstEng.setEmpSrvcFlag(1);
			orgEmpMstEng.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
			orgEmpMstEng.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			orgEmpMstEng.setCreatedDate(new Date());

			orgEmpMstGuj.setEmpPrefix(prefix);
			orgEmpMstGuj.setEmpFname(empFName);
			orgEmpMstGuj.setEmpMname(empMName);
			orgEmpMstGuj.setEmpLname(empLName);	
			orgEmpMstGuj.setOrgGradeMst(gradeMst);
			orgEmpMstGuj.setOrgUserMst(orgUserMst);
			orgEmpMstGuj.setEmpDob(DOB);
			orgEmpMstGuj.setEmpDoj(DOJ);
			orgEmpMstGuj.setActivateFlag(1);
			orgEmpMstGuj.setEmpSrvcExp(StringUtility.convertStringToDate(dateOfRet));
			orgEmpMstGuj.setEmpSrvcFlag(1);
			orgEmpMstGuj.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
			orgEmpMstGuj.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			orgEmpMstGuj.setCreatedDate(new Date());
			//			
			objServiceArgs.put("empInfoGuj", orgEmpMstGuj);
			objServiceArgs.put("empInfoEng", orgEmpMstEng);
			// Saving info regarding new user ends			
			// Saving info regarding User Post Mpg
			OrgUserpostRlt orgUserpostRlt = new OrgUserpostRlt();
			String selDesg = StringUtility.getParameter("selDesignation", request) != null ? StringUtility.getParameter("selDesignation", request) : "";
			logger.info("Designation Name---------->>"+selDesg);
			String selPost = StringUtility.getParameter("selPost", request) != null ? StringUtility.getParameter("selPost", request) : "";
			String selPostFlag = StringUtility.getParameter("selPost", request) != null ? StringUtility.getParameter("selPost", request) : "";
			objServiceArgs.put("selPostFlag", selPostFlag);
			logger.info("selPost Name---------->>"+selPost);
			String dtStartDate = StringUtility.getParameter("startDate", request) != null ? StringUtility.getParameter("startDate", request) : "";
			logger.info("startDate Name---------->>"+dtStartDate);
			String dtEndDate = StringUtility.getParameter("endDate", request) != null ? StringUtility.getParameter("endDate", request) : ""; 
			logger.info("endDate Name---------->>"+dtEndDate);
			String strOfficeType = request.getParameter("endDate") != null ? StringUtility.getParameter("selOfficeType", request) : "";
			logger.info("endDate Name---------->>"+strOfficeType);
			String strOfficeName = request.getParameter("selOfficeName") != null ? StringUtility.getParameter("selOfficeName", request) : "";
			logger.info("selOfficeName Name---------->>"+strOfficeName);
			String lUserPostRltId = (StringUtility.getParameter("lUserPostRltId", request) != null && !StringUtility.getParameter("lUserPostRltId", request).trim().equals("")) ? StringUtility.getParameter("lUserPostRltId", request) : "0";
			logger.info("The hdnUserId id is:-"+lUserPostRltId);
			long checkId = Long.parseLong(lUserPostRltId);
			logger.info("checking id is:---->"+checkId);


			if (checkId == 0)
			{
				orgUserpostRlt.setActivateFlag(1l);
			}
			else
			{
				logger.info("inside else");
/*				String ActivateFlag = StringUtility.getParameter("rdoActiveFlag", request) != null ? StringUtility.getParameter("rdoActiveFlag", request) : "";
				logger.info("Activate Flag is -------->"+ActivateFlag);

				long Activateflag = Long.parseLong(ActivateFlag);
				logger.info("Activate flag is -------->"+Activateflag);*/
				if(dtEndDate!= null && !"".equals(dtEndDate))
					orgUserpostRlt.setActivateFlag(0l);
				else
					orgUserpostRlt.setActivateFlag(1l);				
			}
			/*String userIdStr;
			if(StringUtility.convertToLong(lUserPostRltId)==0)
				 userIdStr = StringUtility.getParameter("selUserName", request) != null ? StringUtility.getParameter("selUserName", request) : "";
			else
			{
				logger.info("Inside else-------------->");
				userIdStr = StringUtility.getParameter("hiddenUserId", request) != null ? StringUtility.getParameter("hiddenUserId", request) : "";
				logger.info("The hiddenUserId id is----->"+userIdStr);				
			}
			long SelecteduserId = StringUtility.convertToLong(userIdStr);
			OrgUserMst orgUserMst = new OrgUserMst();
			orgUserMst.setUserId(SelecteduserId);*/

			// block for- if post selected from combo
			orgUserpostRlt.setOrgUserMst(orgUserMst);
			OrgPostMst selOrgPostMst = new OrgPostMst();
			long selPostId=0;
			if(!selPost.equals("") && !selPost.equals("0"))
				selPostId = userIdForedit != null ? Long.parseLong(selPost) : 0;
			else
				selPostId = postIdTOEdit;
			logger.info("selPostId:::"+selPostId);
			selOrgPostMst.setPostId(selPostId);
			orgUserpostRlt.setOrgPostMstByPostId(selOrgPostMst);
			// block for- if post selected from combo
			if(dtEndDate!= null && !"".equals(dtEndDate))
				orgUserpostRlt.setActivateFlag(0l);
			else
				orgUserpostRlt.setActivateFlag(1l);
			
			orgUserpostRlt.setEndDate(StringUtility.convertStringToDate(dtEndDate));
			orgUserpostRlt.setStartDate(StringUtility.convertStringToDate(dtStartDate));

			objServiceArgs.put("selDesg", selDesg);
			objServiceArgs.put("strOfficeType", strOfficeType);
			objServiceArgs.put("strOfficeName", strOfficeName);
			objServiceArgs.put("lUserPostRltId", lUserPostRltId);
			objServiceArgs.put("orgUserpostRlt", orgUserpostRlt);
			objServiceArgs.put("ohpId", ohpId);
			objServiceArgs.put("lOhpId", lOhpId);
			objServiceArgs.put("selPostId", selPostId);
			
			
			// Saving info regarding User Post Mpg ends

			// Saving info regarding New Post and its mapping with new User
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
			long psrId = 0;
			String postName = "";
			String flag1 = "add";
			
			logger.info("Flag is:-->>" + flag);
			
			
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			///if (locId == DEPT_ID || locId == 300024 || locId == 300030) 
			{
				HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				logger.info("Inside If Condition GAD");
				long psrpostId = 0;

				psrId = request.getParameter("psr") != null ? Long.parseLong(StringUtility.getParameter("psr", request)): 0;				

				if (flag.equals(flag1)) 
				{
							logger.info("Inside If");
							psrpostId = 0;// request.getParameter("psrpostId") != null ?
							// Long.parseLong(StringUtility.getParameter("psrpostId",
							// request)) : 0;
				} 
				else
				{
							logger.info("Inside else");
							psrpostId = request.getParameter("psrpostId") != null ? Long
									.parseLong(StringUtility.getParameter("psrpostId",
											request))
											: 0;
				}

				logger.info("Psr Number is:-->>>>>" + psrId);

				logger.info("psrpostId Number is:-->>>>>" + psrpostId);
				if (flag.equals(flag1) && !selPostFlag.equals("0")) 
				{
					psrpostId = request.getParameter("psrpostId") != null ? Long.parseLong(StringUtility.getParameter("psrpostId",
									request))
									: 0;
				}
				hrPayPsrPostMpg.setPsrId(psrId);
				hrPayPsrPostMpg.setPsrPostId(psrpostId);

				objServiceArgs.put("hrPayPsrPostMpg", hrPayPsrPostMpg);
			}


			// Ended By Varun For Psr-Post Mpg

			OrgPostMst orgPostMst = new OrgPostMst();
			if (lStrPostId.equals("") == false) 
			{
				logger.info("Editing Post By Admin Screen Post Id : "
						+ lStrPostId);
				if(!selPostFlag.equals(""))
					orgPostMst.setPostId(Long.valueOf(selPostFlag));
				else
					orgPostMst.setPostId(Long.valueOf(lStrPostId));
			}
			OrgPostDetailsRlt orgPostDetailsRlt_en = new OrgPostDetailsRlt();
			OrgPostDetailsRlt orgPostDetailsRlt_gu = new OrgPostDetailsRlt();

			long lActivateFlag = request.getParameter("rdoActiveFlag") != null ? Long.parseLong(StringUtility.getParameter("rdoActiveFlag",request)): 0; 
			logger.info("The Value is:-" + lActivateFlag);
			String changePostCheck = StringUtility.getParameter("changePostCheck", request)!= null ? StringUtility.getParameter("changePostCheck", request) : "";
			String prevPostEndDate = StringUtility.getParameter("prevPostEndDate", request) != null ? StringUtility.getParameter("prevPostEndDate", request) : "";
			objServiceArgs.put("changePostCheck", changePostCheck); 
			objServiceArgs.put("prevPostEndDate", prevPostEndDate);
			if(dtEndDate!= null && !"".equals(dtEndDate))
				orgPostMst.setActivateFlag(0l);
			else
				orgPostMst.setActivateFlag(1l);
			//orgPostMst.setActivateFlag(lActivateFlag); 

			String parentPostlStr = StringUtility.getParameter("parentPostCmb",request);
			if (parentPostlStr.equals("") == false) 
			{
				orgPostMst.setParentPostId(Long.valueOf(parentPostlStr));
			}

			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			String postTypeLookupName = StringUtility.getParameter("postTypeCmb", request);

			if (postTypeLookupName != null && !postTypeLookupName.equals("")) 
			{
				cmnLookupMst.setLookupName(postTypeLookupName);
				orgPostMst.setPostTypeLookupId(cmnLookupMst);
			} 
			else 
			{
				orgPostMst.setCmnLookupMst(null);
			}
			String postLevelNum = StringUtility.getParameter("postLevelNum",request);
			if (postLevelNum.equals("") == false) 
			{
				orgPostMst.setPostLevelId(Long.valueOf(postLevelNum));
			}

			String startDatelStr = StringUtility.getParameter("startDate",request);
			String endDatelStr = StringUtility.getParameter("endDate", request);
			if (startDatelStr.equals("") == false) 
			{
				orgPostMst.setStartDate(StringUtility.convertStringToDate(startDatelStr));
			}
			if (endDatelStr.equals("") == false) 
			{
				orgPostMst.setEndDate(StringUtility.convertStringToDate(endDatelStr));
			}

			String lStrBranch = request.getParameter("branchCmb") != null ? StringUtility.getParameter("branchCmb", request): "";
			if (lStrBranch.equals("") == false) 
			{
				CmnBranchMst cmnBranchMst = new CmnBranchMst();
				// cmnBranchMst.setBranchId(Long.valueOf(lStrBranch));
				// //commented by divya
				cmnBranchMst.setBranchCode(lStrBranch); // added by divya
				objServiceArgs.put("cmnBranchMst", cmnBranchMst);
			}

			String lStrDesignation = request.getParameter("selDesignation") != null ? StringUtility.getParameter("selDesignation", request): "";
			if (lStrDesignation.equals("") == false) 
			{
				OrgDesignationMst orgDesignationMst = new OrgDesignationMst();
				// orgDesignationMst.setDsgnId(Long.valueOf(lStrDesignation));
				// //commented by divya
				orgDesignationMst.setDsgnCode(lStrDesignation); // added by
				// divya
				objServiceArgs.put("orgDesignationMst", orgDesignationMst);
			}
			
			if (locationCode.equals("") == false) 
			{
				cmnLocationMst = new CmnLocationMst();
				// cmnLocationMst.setLocId(Long.valueOf(lStrLocation));
				// //commented by divya
				cmnLocationMst.setLocationCode(locationCode); // added by divya
				objServiceArgs.put("cmnLocationMst", cmnLocationMst);
			}

			orgPostDetailsRlt_gu.setPostName(StringUtility.getParameter(
					"postNameTxt_gu", request));
			orgPostDetailsRlt_gu.setPostShortName(StringUtility.getParameter(
					"postShrtNametxt_gu", request));

			if (locId == DEPT_ID && flag.equals(flag1)) {
				postName = StringUtility.getParameter("postNameTxt", request)
				+ "_" + psrId;
				logger.info("Post Name in VOGEN is:--->>>>>" + postName);
			} else {
				postName = StringUtility.getParameter("postNameTxt", request);
			}
			//...>>>>>>>>>>>>>>>>>>>  hemant >>>>>>>>>>>>>>>>>>>>>>>>>>>
			long EmpId = 0;
			if(StringUtility.getParameter("EmpId", request)!=null && !StringUtility.getParameter("EmpId", request).equals(""))
				EmpId=Long.parseLong(StringUtility.getParameter("EmpId", request));
			logger.info("EmpId:---> " + EmpId);
			objServiceArgs.put("EmpId",EmpId);
			//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<		
			orgPostDetailsRlt_en.setPostName(postName);
			orgPostDetailsRlt_en.setPostShortName(StringUtility.getParameter(
					"postShrtNametxt", request));

			//added by ravysh for office post mapping in cash2 dept
			if(locId==Long.parseLong(constantsBundle.getString("cash2Admin")))
			{
				HrPayOfficepostMpg hrOfficePostMpg = new HrPayOfficepostMpg();
				OrgPostMst orgPostMstOfficePostMpg = new OrgPostMst();
				String lStrRemarks = StringUtility.getParameter("remarksTxt", request);
				String lStrPreviousPost = request.getParameter("previousPostCmb") != null ? StringUtility.getParameter("previousPostCmb", request): "";
				String lStrOffice = request.getParameter("officeCmb") != null ? StringUtility.getParameter("officeCmb", request): "";
				hrOfficePostMpg.setStartDate(StringUtility.convertStringToDate(startDatelStr));	
				hrOfficePostMpg.setEndDate(StringUtility.convertStringToDate(endDatelStr));	
				HrPayOfficeMst hrPayOfficeMst = new HrPayOfficeMst();
				hrPayOfficeMst.setOfficeId(Long.parseLong(lStrOffice));
				/*hrOfficePostMpg.setRemarks(lStrRemarks);
				hrOfficePostMpg.setHrPayOfficeMst(hrPayOfficeMst);*/
				if(!lStrPreviousPost.equals(""))
				{
					orgPostMstOfficePostMpg.setPostId(Long.parseLong(lStrPreviousPost));		
					//hrOfficePostMpg.setOrgPostMstParentPostId(orgPostMstOfficePostMpg);
				}
				objServiceArgs.put("hrOfficePostMpg", hrOfficePostMpg);	

				logger.info("Previous Postid is:-->>>>>" + lStrPreviousPost);
				logger.info("Office Id is:-->>>>>" + lStrOffice);
				logger.info("Remarks entered:-->>>>>" + lStrRemarks);
			}
			//end by ravysh


			objServiceArgs.put("orgPostDetailsRlt_gu", orgPostDetailsRlt_gu);
			objServiceArgs.put("orgPostDetailsRlt_en", orgPostDetailsRlt_en);
			objServiceArgs.put("orgPostMst", orgPostMst);
			objServiceArgs.put("flag", flag);

			// Saving info regarding New Post and its mapping with new User Ends
			
			// Saving info regarding Emp Master Screen - gpf Details
			HrEisEmpMst hrEisEmpMst= new  HrEisEmpMst();
			
			String txtStatusIdStr = "";
            String txtSalModeStr = "";
            String txtHobbyStr = "";
            long   cmbTypeLng=0;
            long contactNo=0;
            long   cmbRecruitSrcLng=0;
            String txtSubCasteStr = "";
            String   cmbCategoryStr="";
            String txtNationalityStr = "";
            String txtMotherTngStr = "";
            String txtReligionIdInt= "";
            String txtCasteIdInt = "";
            long empId=0;
            String salutationId="";
            String email="";
            long employeeId=0;
            
            long gradeId=0;
			String panNo = "";
			/*long userID = 0;*/
			HrEisProofDtl hrEisProofDtl = new HrEisProofDtl();
            long marritalStatus = 301322;
            
            if(StringUtility.getParameter("empEmail", request)!=null && !StringUtility.getParameter("empEmail", request).equals(""))
            {
            	email= StringUtility.getParameter("empEmail", request);
            }
            if(StringUtility.getParameter("contactNo", request)!=null && !StringUtility.getParameter("contactNo", request).equals(""))
            {
            	contactNo= Long.parseLong(StringUtility.getParameter("contactNo", request));
            }
            if(StringUtility.getParameter("Salutation", request)!=null&&!StringUtility.getParameter("Salutation", request).equals(""))
            	salutationId= StringUtility.getParameter("Salutation", request);
            
            if(StringUtility.getParameter("gradeName", request)!= null && !StringUtility.getParameter("gradeName", request).equals(""))
            gradeId=Long.parseLong(StringUtility.getParameter("gradeName", request));
			if (StringUtility.getParameter("empPAN", request) != null && !StringUtility.getParameter("empPAN", request).equals(""))
				panNo = StringUtility.getParameter("empPAN", request);
			hrEisProofDtl.setProofNum(panNo);
			/*if (StringUtility.getParameter("userID", request) != null && !StringUtility.getParameter("userID", request).equals(""))
				userID = Long.parseLong(StringUtility.getParameter("userID", request));*/
			
			char chrisgender = StringUtility.getParameter("gender", request).charAt(0);
            
            if(StringUtility.getParameter("employeeId", request)!=null && !StringUtility.getParameter("employeeId", request).equals("")) {
            	employeeId= Long.parseLong(StringUtility.getParameter("employeeId", request).toString());
            }
			
            /*String dob = StringUtility.getParameter("emp_dob", request);
            Date dateofBirth=null;
            if(dob!=null && !dob.equals(""))
            	dateofBirth=StringUtility.convertStringToDate(dob);
            
            orgEmpMst.setEmpDob(dateofBirth);
            
            String emp_doj = StringUtility.getParameter("emp_doj", request);
            Date dateofJoining=null;
            if(emp_doj!=null && !emp_doj.equals(""))
            	dateofJoining=StringUtility.convertStringToDate(emp_doj);
            
            orgEmpMst.setEmpDoj(dateofJoining);
            
            String emp_conf_due_dt = StringUtility.getParameter("emp_conf", request);
            Date empConfDuedt=null;
            if(emp_conf_due_dt!=null && !emp_conf_due_dt.equals(""))
            	empConfDuedt=StringUtility.convertStringToDate(emp_conf_due_dt);
            
            String emp_leave_dt = StringUtility.getParameter("emp_leave_dt", request);
            Date leaveDate=null;
            if(emp_leave_dt!=null && !emp_leave_dt.equals(""))
            	leaveDate=StringUtility.convertStringToDate(emp_leave_dt);
            
            orgEmpMst.setEmpSrvcExp(leaveDate);*/
            OrgEmpMst gpfNoOrgEmpMst = new OrgEmpMst();
            logger.info("gpfAccno in vogen ::" +gpfAccno);
            gpfNoOrgEmpMst.setEmpGPFnumber(gpfAccno);        
            objServiceArgs.put("gpfNoOrgEmpMst", gpfNoOrgEmpMst);
            if(StringUtility.getParameter("EmpStatus", request)!=null && !StringUtility.getParameter("EmpStatus", request).equals(""))
            {
            	txtStatusIdStr= StringUtility.getParameter("EmpStatus", request);
            }
            if(StringUtility.getParameter("SalMode", request)!=null && !StringUtility.getParameter("SalMode", request).equals(""))
            {
            	txtSalModeStr= StringUtility.getParameter("SalMode", request);
            }
            
            if(StringUtility.getParameter("emp_hobby", request)!=null && !StringUtility.getParameter("emp_hobby", request).equals(""))
            {
            	txtHobbyStr =StringUtility.getParameter("emp_hobby", request);
            }
            if(StringUtility.getParameter("EmpType", request)!=null && !StringUtility.getParameter("EmpType", request).equals(""))
            {
            	cmbTypeLng = Long.parseLong(StringUtility.getParameter("EmpType", request));
            }
            if(StringUtility.getParameter("EmpRecSrc", request)!=null && !StringUtility.getParameter("EmpRecSrc", request).equals(""))
            {
            	 cmbRecruitSrcLng = Long.parseLong(StringUtility.getParameter("EmpRecSrc", request));
            }
            if(StringUtility.getParameter("emp_sub_caste", request)!=null && !StringUtility.getParameter("emp_sub_caste", request).equals(""))
            {
            	txtSubCasteStr= StringUtility.getParameter("emp_sub_caste", request);
            }
            if(StringUtility.getParameter("Category", request)!=null && !StringUtility.getParameter("Category", request).equals(""))
            {
            	cmbCategoryStr= StringUtility.getParameter("Category", request);
            }
            
            String empNationaliltyStr =  StringUtility.getParameter("emp_nationality", request);
            logger.info("empNationaliltyStr here is "+empNationaliltyStr);
            if(StringUtility.getParameter("emp_nationality", request)!=null && !StringUtility.getParameter("emp_nationality", request).equals(""))
            {
            	txtNationalityStr =StringUtility.getParameter("emp_nationality", request);
            	logger.info("txtNationalityStr here in if loop is "+txtNationalityStr);
            }
            String motherTounge =  StringUtility.getParameter("motherTounge", request);
            logger.info("motherTounge here is "+motherTounge);
            
            if(StringUtility.getParameter("emp_mother_tongue", request)!=null && !StringUtility.getParameter("emp_mother_tongue", request).equals(""))
            {
            	txtMotherTngStr =StringUtility.getParameter("emp_mother_tongue", request);
            	logger.info("txtMotherTngStr here in if loop is "+txtMotherTngStr);
            }
            
//          chrMaritalStatus = StringUtility.getParameter("cstMarStatus", request).charAt(0);
            if(StringUtility.getParameter("Religion", request)!=null && !StringUtility.getParameter("Religion", request).equals(""))
            {
            	txtReligionIdInt =  StringUtility.getParameter("Religion", request);
            }
            if(StringUtility.getParameter("emp_caste_id", request)!=null && !StringUtility.getParameter("emp_caste_id", request).equals(""))
            {
            	txtCasteIdInt =  StringUtility.getParameter("emp_caste_id", request);
            }
            String edit="";
            if(StringUtility.getParameter("edit", request)!=null && !StringUtility.getParameter("edit", request).equals(""))
            {
            	edit= StringUtility.getParameter("edit", request);
            }

            String empAllRec="";
            if(StringUtility.getParameter("empAllRec", request)!=null && !StringUtility.getParameter("empAllRec", request).equals(""))
            {
            	empAllRec= StringUtility.getParameter("empAllRec", request);
            }
            logger.info("empAllRec in VOGEn is " + empAllRec);

			// Setter Of hr_eis_emp_mst.
            hrEisEmpMst.setEmpId(employeeId);
            hrEisEmpMst.setContactNo(contactNo);
            hrEisEmpMst.setEmail(email);
            hrEisEmpMst.setEmpGender(chrisgender);
            hrEisEmpMst.setEmpStatusId(txtStatusIdStr);
            hrEisEmpMst.setEmpApptLtrDt(DOJ); //TODO ConfDate should be added 1 year in DOJ
            hrEisEmpMst.setEmpApptLtrNo("1");
            hrEisEmpMst.setEmpConfDueDt(DOJ);//TODO ConfDate should be added 1 year in DOJ
            hrEisEmpMst.setEmpSalDisbMd(txtSalModeStr);
            hrEisEmpMst.setEmpHobby(txtHobbyStr);
            hrEisEmpMst.setEmpRecruitSrc(cmbRecruitSrcLng);
            hrEisEmpMst.setEmpType(cmbTypeLng);
			
    		if(!flag.equals(null) && !flag.equalsIgnoreCase("edit")) {
    			if(empAllRec.equalsIgnoreCase("Y")==true)
    			{
                	logger.info(" edit mode of emp master from vogen ");
        			String empno=StringUtility.getParameter("empId", request);
        			if(!empno.equals(null))
        			{	
        				empId=Long.parseLong(empno);
        			}
        			logger.info("The Empployee Id:-"+empId);
        			objServiceArgs.put("empId", empId);	 
        			objServiceArgs.put("empAllRec","true");
    			}
    			else
    				objServiceArgs.put("empAllRec","false");	
    			logger.info(" insert mode of emp master from vogen ");            	           	
            }
            else {           	
            	logger.info(" edit mode of emp master from vogen ");
    			String empno=StringUtility.getParameter("employeeId", request);
    			if(!empno.equals(null) )
    			{	
    				empId=Long.parseLong(empno);
    				if(empAllRec.equalsIgnoreCase("Y")==true)
        			objServiceArgs.put("empAllRec","true");
    				else
    				objServiceArgs.put("empAllRec","false");	
    			}
    			logger.info("The Empployee Id:-"+empId);
    			objServiceArgs.put("empId", empId);	   

            }
    		logger.info("Cast id is:-"+txtCasteIdInt);
    		logger.info("cmbCategoryStr id is:-"+cmbCategoryStr);
    		logger.info("txtSubCasteStr id is:-"+txtSubCasteStr);
    		logger.info("from vogen txtMotherTngStr id is:-"+txtMotherTngStr+" txtNationalityStr "+txtNationalityStr);
    		logger.info("txtReligionIdInt id is:-"+txtReligionIdInt);
    		//empId = Long.parseLong(StringUtility.getParameter("empId", request));
    		objServiceArgs.put("edit", edit);
    		objServiceArgs.put("gradeId",gradeId); // Setting gpf class mapping grade
    		/*##########################################*/
			//Added By Varun For GPF Account 
    		objServiceArgs.put("userID", userID);
    		//Ended By Varun For GPF Account 
    		/*##########################################*/
			
    		objServiceArgs.put("empId", empId);
    		//objServiceArgs.put("employeeId", employeeId);    		
    		
			objServiceArgs.put("hrEisEmpMst", hrEisEmpMst);
//			 Added By Urvin Shah.			
			objServiceArgs.put("hrEisProofDtl", hrEisProofDtl);
			// End.
			//Added By Urvin.
			objServiceArgs.put("txtSubCasteStr",txtSubCasteStr);
			objServiceArgs.put("cmbCategoryStr",cmbCategoryStr);
			/*objServiceArgs.put("txtBirthPlcStr",txtBirthPlcStr);
			 
			objServiceArgs.put("txtNativePlcStr",txtNativePlcStr);*/
			
			objServiceArgs.put("txtNationalityStr",txtNationalityStr);
			objServiceArgs.put("txtMotherTngStr",txtMotherTngStr);
			objServiceArgs.put("txtReligionIdInt",txtReligionIdInt);
			objServiceArgs.put("txtCasteIdInt",txtCasteIdInt);
			
			// Saving info regarding Emp Master Screen - gpf Details Ends
			
			// Saving info related to Order head post mapping			
			long orderId = 0;			
			if(StringUtility.getParameter("order", request)!=null)
        		orderId=Long.parseLong(StringUtility.getParameter("order", request));
        	logger.info("order:---> " + orderId);        	  	
        	long billNo = 0;
  	        String postSearchFlag="n";
  	        if(StringUtility.getParameter("billNo", request)!=null)
  	        	billNo=Long.parseLong(StringUtility.getParameter("billNo", request));
  	        logger.info("billNo in multiple method---> " + billNo);

  	        if(StringUtility.getParameter("postSearch", request)!=null)
  	        	postSearchFlag = StringUtility.getParameter("postSearch", request);
  	        objServiceArgs.put("order",orderId);      
  	        objServiceArgs.put("postSearchFlag",postSearchFlag);
  	        objServiceArgs.put("billNo",billNo);
  	        			
			// Saving info related to Order head post mapping ends
  	        
  	        // Saving Bank Dtls of employee
  	        HrEisBankDtls bankDtlsVO = new HrEisBankDtls();
  	        bankDtlsVO = new HrEisBankDtls();
  	        String editMode = StringUtility.getParameter("edit",request);
  	        long bankDtlsId = 0;
  	        objServiceArgs.put("edit","N");
  	        if(flag.equalsIgnoreCase("add")) 
  	        {			
  	        	objServiceArgs.put("edit","N");
  	        }
  	        else
  	        {         
  	        	String BankDtlsId = StringUtility.getParameter("bankDtlsId", request);
  	        	if( BankDtlsId!=null && !BankDtlsId.equals("")){
  	        		bankDtlsId = Long.parseLong(BankDtlsId);
  	        	}
  	        	
  	        	bankDtlsVO.setBankDtlId(bankDtlsId);
  	        	objServiceArgs.put("edit","Y");
  	        }
  	        long bankId = 0;
  	        long branchId = 0;
  	        String accNo = "" ;
  	        long accType = 0;
  	        Date bankAcctStartDt = null;
  	        if(StringUtility.getParameter("cmbBankName", request)!=null && !StringUtility.getParameter("cmbBankName", request).equals(""))
  	        {
  	        	bankId = Long.parseLong(StringUtility.getParameter("cmbBankName", request));
  	        }

  	        if(StringUtility.getParameter("txtStartDate", request)!=null && !StringUtility.getParameter("txtStartDate", request).equals(""))
  	        {
  	        	bankAcctStartDt = StringUtility.convertStringToDate(StringUtility.getParameter("txtStartDate", request));
  	        }

  	        if(StringUtility.getParameter("cmbBranchName", request)!=null && !StringUtility.getParameter("cmbBranchName", request).equals(""))
  	        {
  	        	branchId = Long.parseLong(StringUtility.getParameter("cmbBranchName", request));
  	        }

  	        if(StringUtility.getParameter("txtAccNo", request)!=null && !StringUtility.getParameter("txtAccNo", request).equals(""))
  	        {
  	        	accNo = (StringUtility.getParameter("txtAccNo", request));
  	        	logger.info("bankAccNo:::"+accNo);
  	        }

  	        if(StringUtility.getParameter("cmbAccType", request)!=null && !StringUtility.getParameter("cmbAccType", request).equals(""))
  	        {
  	        	accType = Long.parseLong(StringUtility.getParameter("cmbAccType", request));
  	        }

  	        HrEisBankMst hrEisBankMst = new HrEisBankMst();
  	        hrEisBankMst.setBankId(bankId);

  	        HrEisBranchMst hrEisBranchMst = new HrEisBranchMst();
  	        hrEisBranchMst.setBranchId(branchId);
  	        
  	        bankDtlsVO.setBankAcctNo(accNo);
  	        bankDtlsVO.setBankAcctStartDt(bankAcctStartDt);
  	        bankDtlsVO.setBankAcctType(accType);
  	       /* bankDtlsVO.setHrEisBankMst(hrEisBankMst);
  	        bankDtlsVO.setHrEisBranchMst(hrEisBranchMst);*/
  	        //bankDtlsVO.setHrEisEmpMst(hrEisEmpMst);
  	        logger.info(" ****************************BankMasterVO " + bankDtlsVO);
  	        objServiceArgs.put("bankDtls",bankDtlsVO);		
  	        // Saving Bank Dtls of employee ends
  	        // Saving Order details of employee Starts
  	        HrPayOrderMst hrPayOrderMstVo= new HrPayOrderMst();
  	        hrPayOrderMstVo.setOrderName(lStrOrderNo);
  	        Date orderStartDt = StringUtility.convertStringToDate(lStrOrderStartDate);
  	        Date orderEndDt = StringUtility.convertStringToDate(lStrOrderEndDate);
  	        hrPayOrderMstVo.setOrderDate(orderStartDt);
  	        hrPayOrderMstVo.setEndDate(orderEndDt);
  	        hrPayOrderMstVo.setOrgUserMstByCreatedBy(loggedInOrgUserMst);  	      	
  	        hrPayOrderMstVo.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
  	      	/*hrPayOrderMstVo.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
  	      	hrPayOrderMstVo.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);*/
  	      	hrPayOrderMstVo.setLocationCode(locationCode);
  	        hrPayOrderMstVo.setTrnCounter(1);
  	        hrPayOrderMstVo.setAttachment_Id(0);
  	        objServiceArgs.put("hrPayOrderMstVo",hrPayOrderMstVo);
  	        objServiceArgs.put("lStrBillHeadId",lStrBillHeadId);
  	        objServiceArgs.put("lOrderId",lOrderId);
  	      
  	        // Saving Order details of employee ends
			retObj.setResultValue(objServiceArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			Calendar endCalTime = Calendar.getInstance();
			logger.info("The End time time for the genNewEmpConfigVOGEN is:-"+endCalTime.getTimeInMillis());
		}
		catch(Exception e)
		{			
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}

	public ResultObject generateMap(Map arg0) {
		return null;
	}

}
