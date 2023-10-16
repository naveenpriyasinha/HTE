package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;

public class EmpInfoVOGen extends ServiceImpl implements VOGeneratorService
{
	Log logger = LogFactory.getLog(getClass());
	//static int i;
	public ResultObject generateEmpMap(Map objectArgs) 
	{
		
		//ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		logger.info("IN EMPLOYEE INFORMATION VOGEN generateEmpMap method");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			//HttpSession session=request.getSession();
			//i++;
			HrEisEmpMst empMst= new  HrEisEmpMst();
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			OrgGradeMst orgGradeMst = new OrgGradeMst();
			/*String lstGenderStr = "";
            String lstReligionStr = "";
            String lstOccupationStr = "";
            String txtCasteStr = "";
            //long   cmbCategoryLng;
            String txtFirstNameStr = "";
            String txtMiddleNameStr = "";
            String txtlastNameStr = "";*/
            String txtStatusIdStr = "";
            String txtSalModeStr = "";
            /*char txtHndCapStr;
            String txtHndCapDescStr = null;
            String txtBldGrpStr = null;
            String txtIdnMrkStr = null;
            String txtfatherNmeStr = null;
            String txtMotherNmeStr = null;
            String txtSpouseNmeStr = null;*/
            String txtHobbyStr = "";
            long   cmbTypeLng=0;
            long contactNo=0;
            long   cmbRecruitSrcLng=0;
            String txtSubCasteStr = "";
            String   cmbCategoryStr="";
            //String txtBirthPlcStr = "";
            //String txtNativePlcStr = "";
            String txtNationalityStr = "";
            String txtMotherTngStr = "";
            //char chrMaritalStatus;
            String txtReligionIdInt= "";
            String txtCasteIdInt = "";
            long empId=0;
            String salutationId="";
            String email="";
            long employeeId=0;
            String gpfAccno="";
            long gradeId=0;
//          Added By Urvin Shah.
			String panNo = "";
			/*##########################################*/
			//Added By Varun For GPF Account 
			long userID = 0;
			//Ended By Varun For GPF Account 
			/*##########################################*/
			HrEisProofDtl hrEisProofDtl = new HrEisProofDtl();
			// End.
            // Added By Urvin.
            long marritalStatus = 301322;
            // Ended by Urvin.
            
            //added by ravysh
            String FromBasicDtlsNew=StringUtility.getParameter("FromBasicDtlsNew", request)!=null?StringUtility.getParameter("FromBasicDtlsNew", request):"";
            long otherId = StringUtility.getParameter("otherId", request)!=null && !(StringUtility.getParameter("otherId", request).equals(""))?Long.parseLong(StringUtility.getParameter("otherId", request)):0;
            objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
            objectArgs.put("otherId", otherId);
            
            if(StringUtility.getParameter("email", request)!=null && !StringUtility.getParameter("email", request).equals(""))
            {
            	email= StringUtility.getParameter("email", request);
            }
            if(StringUtility.getParameter("contactNo", request)!=null && !StringUtility.getParameter("contactNo", request).equals(""))
            {
            	contactNo= Long.parseLong(StringUtility.getParameter("contactNo", request));
            }
            if(StringUtility.getParameter("Salutation", request)!=null&&!StringUtility.getParameter("Salutation", request).equals(""))
            	salutationId= StringUtility.getParameter("Salutation", request);
            if(StringUtility.getParameter("gpfAcctNo", request)!=null&&!StringUtility.getParameter("gpfAcctNo", request).equals(""))
            	gpfAccno= StringUtility.getParameter("gpfAcctNo", request);
            if(StringUtility.getParameter("gradeName", request)!= null && !StringUtility.getParameter("gradeName", request).equals(""))
            gradeId=Long.parseLong(StringUtility.getParameter("gradeName", request));
          //  logger.info("the value of the gradeId is ::"+gradeId);
            //          Added By Urvin Shah
			if (StringUtility.getParameter("empPAN", request) != null && !StringUtility.getParameter("empPAN", request).equals(""))
				panNo = StringUtility.getParameter("empPAN", request);
			hrEisProofDtl.setProofNum(panNo);
			
			// End.
            
            /*if(StringUtility.getParameter("emp_first_name", request)!=null && !StringUtility.getParameter("emp_first_name", request).equals(""))
            {
            	txtFirstNameStr= StringUtility.getParameter("emp_first_name", request);
            }
            if(StringUtility.getParameter("emp_middle_name", request)!=null && !StringUtility.getParameter("emp_middle_name", request).equals(""))
            {
            	txtMiddleNameStr=StringUtility.getParameter("emp_middle_name", request);
            }
            if(StringUtility.getParameter("emp_last_name", request)!=null && !StringUtility.getParameter("emp_last_name", request).equals(""))
            {
            	txtlastNameStr= StringUtility.getParameter("emp_last_name", request);
            }*/

			/*##########################################*/
			//Added By Varun For GPF Account 
            if (StringUtility.getParameter("userID", request) != null && !StringUtility.getParameter("userID", request).equals(""))
			userID = Long.parseLong(StringUtility.getParameter("userID", request));
			//Ended By Varun For GPF Account
			/*##########################################*/
			 
            char chrisgender = StringUtility.getParameter("gender", request).charAt(0);
            
            if(StringUtility.getParameter("employeeId", request)!=null && !StringUtility.getParameter("employeeId", request).equals("")) {
            	employeeId= Long.parseLong(StringUtility.getParameter("employeeId", request).toString());
            }
			
            String dob = StringUtility.getParameter("emp_dob", request);
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

            orgEmpMst.setEmpSrvcExp(leaveDate);
            orgEmpMst.setEmpGPFnumber(gpfAccno);
            orgGradeMst.setGradeId(gradeId);
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
            /*if(StringUtility.getParameter("emp_birth_place", request)!=null && !StringUtility.getParameter("emp_birth_place", request).equals(""))
            {
            	txtBirthPlcStr = StringUtility.getParameter("emp_birth_place", request);
            }
            if(StringUtility.getParameter("emp_native_place", request)!=null && !StringUtility.getParameter("emp_native_place", request).equals(""))
            {
            	txtNativePlcStr =StringUtility.getParameter("emp_native_place", request);
            }*/
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

//added by Ankit Bhatt for merging screens
            
            String empAllRec="";
            if(StringUtility.getParameter("empAllRec", request)!=null && !StringUtility.getParameter("empAllRec", request).equals(""))
            {
            	empAllRec= StringUtility.getParameter("empAllRec", request);
            }
            logger.info("empAllRec in VOGEn is " + empAllRec);
            //ended by Ankit Bhatt
            
            /* Setter of Org_Emp_Mst.
            orgEmpMst.setEmpFname(txtFirstNameStr);
			orgEmpMst.setEmpMname(txtMiddleNameStr);
			orgEmpMst.setEmpLname(txtlastNameStr);
			orgEmpMst.setEmpDob(dateofBirth);
			orgEmpMst.setEmpDoj(dateofJoining);
			orgEmpMst.setActivateFlag(1);
			orgEmpMst.setEmpPrefix("Mr.");
			orgEmpMst.setEmpSrvcExp(leaveDate);
			orgEmpMst.setEmpSrvcFlag(1);
			orgEmpMst.setEndDate(leaveDate);
			orgEmpMst.setStartDate(dateofJoining);
			*/
			// Setter Of hr_eis_emp_mst.
            empMst.setEmpId(employeeId);
            empMst.setContactNo(contactNo);
			empMst.setEmail(email);           	
			//empMst.setEmpSalutationId(salutationId);
			empMst.setEmpGender(chrisgender);			
			//empMst.setEmpLeaveDt(leaveDate);
			empMst.setEmpStatusId(txtStatusIdStr);
			empMst.setEmpApptLtrDt(empConfDuedt);
			empMst.setEmpApptLtrNo("1");
			empMst.setEmpConfDueDt(empConfDuedt);
			empMst.setEmpSalDisbMd(txtSalModeStr);
			empMst.setEmpHobby(txtHobbyStr);
			empMst.setEmpRecruitSrc(cmbRecruitSrcLng);
			empMst.setEmpType(cmbTypeLng);
			
			//empMst.setEmpSubCaste(txtSubCasteStr);
			//empMst.setEmpCategory(cmbCategoryStr);
			//empMst.setEmpBirthPlace(txtBirthPlcStr);
			//empMst.setEmpNativePlace(txtNativePlcStr);
			
			//empMst.setCmnCountryMstByEmpNationality(txtNationalityStr);
			//empMst.setEmpMotherTongue(txtMotherTngStr);
			//empMst.setEmpMaritalStatus('1');
			//empMst.setEmpReligionId(txtReligionIdInt);
			//empMst.setEmpCasteId(txtCasteIdInt);
			
    		if(!edit.equals(null) && !edit.equalsIgnoreCase("Y")) {
    			if(empAllRec.equalsIgnoreCase("Y")==true)
    			{
                	logger.info(" edit mode of emp master from vogen ");
        			String empno=StringUtility.getParameter("empId", request);
        			if(!empno.equals(null))
        			{	
        				empId=Long.parseLong(empno);
        			}
        			logger.info("The Empployee Id:-"+empId);
        			objectArgs.put("empId", empId);	 
        			objectArgs.put("empAllRec","true");
    			}
    			else
    				objectArgs.put("empAllRec","false");	
    			logger.info(" insert mode of emp master from vogen ");            	           	
            }
            else {           	
            	logger.info(" edit mode of emp master from vogen ");
    			String empno=StringUtility.getParameter("employeeId", request);
    			if(!empno.equals(null) )
    			{	
    				empId=Long.parseLong(empno);
    				if(empAllRec.equalsIgnoreCase("Y")==true)
        			objectArgs.put("empAllRec","true");
    				else
    				objectArgs.put("empAllRec","false");	
    			}
    			logger.info("The Empployee Id:-"+empId);
    			objectArgs.put("empId", empId);	   

            }
    		logger.info("Cast id is:-"+txtCasteIdInt);
    		logger.info("cmbCategoryStr id is:-"+cmbCategoryStr);
    		logger.info("txtSubCasteStr id is:-"+txtSubCasteStr);
    		logger.info("from vogen txtMotherTngStr id is:-"+txtMotherTngStr+" txtNationalityStr "+txtNationalityStr);
    		logger.info("txtReligionIdInt id is:-"+txtReligionIdInt);
    		//empId = Long.parseLong(StringUtility.getParameter("empId", request));
    		objectArgs.put("edit", edit);
    		objectArgs.put("gradeId",gradeId);
    		/*##########################################*/
			//Added By Varun For GPF Account 
    		objectArgs.put("userID", userID);
    		//Ended By Varun For GPF Account 
    		/*##########################################*/
			
    		objectArgs.put("empId", empId);
    		//objectArgs.put("employeeId", employeeId);
    		objectArgs.put("orgGradeMst", orgGradeMst);
    		objectArgs.put("orgEmpMst", orgEmpMst);
			objectArgs.put("empMst", empMst);
//			 Added By Urvin Shah.			
			objectArgs.put("hrEisProofDtl", hrEisProofDtl);
			// End.
			//Added By Urvin.
			objectArgs.put("txtSubCasteStr",txtSubCasteStr);
			objectArgs.put("cmbCategoryStr",cmbCategoryStr);
			/*objectArgs.put("txtBirthPlcStr",txtBirthPlcStr);
			 
			objectArgs.put("txtNativePlcStr",txtNativePlcStr);*/
			
			objectArgs.put("txtNationalityStr",txtNationalityStr);
			objectArgs.put("txtMotherTngStr",txtMotherTngStr);
			objectArgs.put("txtReligionIdInt",txtReligionIdInt);
			objectArgs.put("txtCasteIdInt",txtCasteIdInt);

//added by Ankit Bhatt
		//	objectArgs.put("empAllRec","true");
			//ended by ankit Bhatt
			//objectArgs.put("marritalStatus",marritalStatus);
			
			// Ended By Urvin.
			logger.info("U are out of VOGen");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			
			 	

		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			return resultObject;
		}
		
		
		// TODO Auto-generated method stub
		return resultObject;
	}
	public ResultObject generateMap(Map arg0) {
		// TODO Auto-generated method stub
		return null;
	}	

}
