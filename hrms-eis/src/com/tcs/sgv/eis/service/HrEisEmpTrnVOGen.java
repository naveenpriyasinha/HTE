package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxn;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxnId;

public class HrEisEmpTrnVOGen extends ServiceImpl
{
	public final String SERVICE_LOCATOR = "serviceLocator";
	public final String REQUEST_OBJETCT="requestObj";
	
	public final String SETVOGEN_EN = "setVOGen";
	public final String SETVOGEN_GU = "setVOGen1";
	public final String SET_VOGEN_APP="setVOGenApp";
	public final String  REQ_ID="reqId";
	public final String  LANG_ID="langId";

	public final String GU_EMP_LNAME ="gu_emp_last_name";
    public final String GU_EMP_FNAME="gu_emp_first_name";
	public final String GU_EMP_MNAME="gu_emp_middle_name";
	public final String EMP_PREFIX="Salutation";
    public final String EN_EMP_FNAME= "emp_first_name";
    public final String EN_EMP_LNAME= "emp_last_name";
    public final String EN_EMP_MNAME= "emp_middle_name";
    
	public final String EMP_RELIGION="Religion";
	public final String EMP_CAST_ID="emp_caste_id";
	public final String EMP_SUBCAST_ID="emp_sub_caste";
	public final String EMP_CATEGORY="Category";
	public final String EMP_STD_CODE="emp_Phone_std";
	public final String EMP_PHONE_NO="emp_Phone_Num";
	public final String EMP_MOBILE_NO="emp_Mobile_No";
	public final String EMP_EMAIL="emp_email";
	public final String EMP_GENDER="gender";
	public final String EMP_DOB="emp_dob";
	public final String EMP_MOTHER_TONGUE ="emp_mother_tongue";
	public final String EMP_MARITAL_STATUS="Marital_Status";
	private static final Log logger = LogFactory.getLog(HrEisEmpTrnVOGen.class);
public ResultObject generateIdMap(Map objectArgs)
{
		logger.info("In the generateMap For Request ID******************");
		ResultObject object=new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request=(HttpServletRequest)objectArgs.get(REQUEST_OBJETCT);
		try {               		        
				HttpSession session = request.getSession();
				String reqId="";
				
				if(request.getParameter("corrId")!=null){
					reqId=request.getParameter("corrId");
				}else{
					reqId=session.getAttribute("WorkFlowCorrespondence_CorrId").toString();
				}
				
				//Long reqId=StringUtility.convertToLong(StringUtility.getParameter(REQ_ID,request));
				logger.info("the Request Id is ::>>"+reqId);
				objectArgs.put("ReqId",reqId);
	     }
	catch (Exception e) {
			object.setThrowable(e);
			object.setResultCode(ErrorConstants.ERROR);
			object.setResultValue(objectArgs);
			logger.error("Exception while Calling generateIdMap method in HrEisEmpTrnVOGen Service",e);
		}
		object.setResultValue(objectArgs);
		return object;
}


public ResultObject generateEmpApprovalMap(Map objectArgs){
		
		logger.info("In the In Side Approval VOGEN ******************");
		
		ResultObject addObject=new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest requestAdd=(HttpServletRequest)objectArgs.get(REQUEST_OBJETCT);
		HrEisEmpDtlTxn hrEisEmpTrn=new HrEisEmpDtlTxn();
				
		try {
				logger.info("*********************In Side Approval VOGEN********************");
				//Long  reqId=StringUtility.convertToLong(StringUtility.getParameter(REQ_ID,requestAdd));
				
				//Change By Sunil
				HttpSession session = requestAdd.getSession();
				String reqId=session.getAttribute("WorkFlowCorrespondence_CorrId").toString();
				objectArgs.put(REQ_ID, reqId);	
				
				logger.info("In the In Side Approval VOGEN ******* reqId ***********"+reqId);
				//objectArgs.put(REQ_ID,reqId);
				objectArgs.put(SET_VOGEN_APP,hrEisEmpTrn);
				
				logger.info("the Emp reqId is          ::>>"+reqId);
				addObject.setResultCode(ErrorConstants.SUCCESS); 
				addObject.setResultValue(objectArgs);
		} catch (Exception e) {addObject.setThrowable(e);
		addObject.setResultCode(ErrorConstants.ERROR);
		addObject.setResultValue(objectArgs);
		logger.error("Exception while Calling generateEmpApprovalMap method in HrEisEmpTrnVOGen Service",e);}
		
		return addObject;
}	

public ResultObject generateEmpAddMap(Map objectArgs){
	
	logger.info("In the generateAddMap VOGenMethod******************");
	
	ResultObject addObject=new ResultObject(ErrorConstants.SUCCESS);
	HttpServletRequest requestAdd=(HttpServletRequest)objectArgs.get(REQUEST_OBJETCT);
	
	HrEisEmpDtlTxn hrEisEmpTrn=new HrEisEmpDtlTxn();
	HrEisEmpDtlTxn hrEisEmpTrn1=new HrEisEmpDtlTxn();
	HrEisEmpDtlTxnId hrEisTrnId1 =new HrEisEmpDtlTxnId();
	try {
		
		  Long  reqId=StringUtility.convertToLong(StringUtility.getParameter(REQ_ID,requestAdd));
		  Long  langId=StringUtility.convertToLong(StringUtility.getParameter(LANG_ID,requestAdd));	
		  hrEisTrnId1.setLangId(langId);
          hrEisTrnId1.setReqId(reqId);
          hrEisEmpTrn1.setId(hrEisTrnId1);
          
// Start for gujrati Name 
          String add_Gu_EmpLname=StringUtility.getParameter(GU_EMP_LNAME,requestAdd);
          logger.info("add_Gu_Emplast  ::"+add_Gu_EmpLname);
          
          String  add_Gu_EmpFname=StringUtility.getParameter(GU_EMP_FNAME,requestAdd);
          logger.info("add_Gu_EmpFast   ::"+ add_Gu_EmpFname);
          
          String add_Gu_EmpMname=StringUtility.getParameter(GU_EMP_MNAME,requestAdd);
          logger.info("add_Gu_EmpMast  ::"+add_Gu_EmpMname);
//Start for gujrati Name
         
		  String add_EmpSalutation=StringUtility.getParameter(EMP_PREFIX,requestAdd);
		  String add_EmpFname=StringUtility.getParameter(EN_EMP_FNAME,requestAdd);
		  String add_EmpLname=StringUtility.getParameter(EN_EMP_LNAME, requestAdd);
		  String add_EmpMname=StringUtility.getParameter(EN_EMP_MNAME,requestAdd);
		  
		  String add_EmpGender=StringUtility.getParameter(EMP_GENDER,requestAdd);
		  java.lang.Character addGen= StringUtility.convertToChar(add_EmpGender);
		  
		  Date add_EmpDob=StringUtility.convertStringToDate(StringUtility.getParameter(EMP_DOB,requestAdd));
		  
		  String add_EmpMotherTongue=StringUtility.getParameter(EMP_MOTHER_TONGUE,requestAdd);
		  String add_EmpReligion=StringUtility.getParameter(EMP_RELIGION,requestAdd);
		  String add_EmpCastId=StringUtility.getParameter(EMP_CAST_ID,requestAdd);
		 
		  String add_EmpSubCastId=StringUtility.getParameter(EMP_SUBCAST_ID,requestAdd);
		  String add_EmpCategory=StringUtility.getParameter(EMP_CATEGORY,requestAdd);
		  String add_EmpMaritalStatus=StringUtility.getParameter(EMP_MARITAL_STATUS,requestAdd);
		  
		  Long add_EmpPhoneStd=null;
		  add_EmpPhoneStd=StringUtility.convertToLong(StringUtility.getParameter(EMP_STD_CODE,requestAdd));
			  
		  Long add_EmpPhoneNum=null;
		  add_EmpPhoneNum=StringUtility.convertToLong(StringUtility.getParameter(EMP_PHONE_NO,requestAdd));

		  Long add_EmpMobileNo=null;
		  String strMobileNo=StringUtility.getParameter(EMP_MOBILE_NO,requestAdd);
		  
		  if(strMobileNo!=null && !strMobileNo.equals(""))
		  {
			  add_EmpMobileNo=StringUtility.convertToLong(StringUtility.getParameter(EMP_MOBILE_NO,requestAdd));
		  }  
		  
		  String add_EmpEMail=StringUtility.getParameter(EMP_EMAIL,requestAdd);
		  
		  hrEisEmpTrn.setEmpPrefix(add_EmpSalutation);
		  hrEisEmpTrn.setEmpFname(add_EmpFname);
		  hrEisEmpTrn.setEmpMname(add_EmpMname);
		  hrEisEmpTrn.setEmpLname(add_EmpLname);
         			  
		  hrEisEmpTrn.setEmpGender(addGen);
          hrEisEmpTrn.setEmpDob(add_EmpDob);		 
          
          HrEisEmpDtlTxnId hrEisTrnId =new HrEisEmpDtlTxnId();
          hrEisTrnId.setLangId(langId);
          hrEisTrnId.setReqId(reqId);
          hrEisEmpTrn.setId(hrEisTrnId);
          
          CmnLookupMst cmnLookupMstByMotherTongue = new CmnLookupMst();
          cmnLookupMstByMotherTongue.setLookupName(add_EmpMotherTongue);
		  hrEisEmpTrn.setCmnLanguageMstByEmpMotherTongueId(cmnLookupMstByMotherTongue);
		  hrEisEmpTrn1.setCmnLanguageMstByEmpMotherTongueId(cmnLookupMstByMotherTongue);						  
		  
		  CmnLookupMst cmnLookupMstByEmpCasteId = new CmnLookupMst();
		  cmnLookupMstByEmpCasteId.setLookupName(add_EmpCastId);
		  hrEisEmpTrn.setCmnLookupMstByEmpCasteId(cmnLookupMstByEmpCasteId);
		  hrEisEmpTrn1.setCmnLookupMstByEmpCasteId(cmnLookupMstByEmpCasteId);
		  
		  CmnLookupMst cmnLookupMstByEmpSubCasteId = new CmnLookupMst();
		  cmnLookupMstByEmpSubCasteId.setLookupName(add_EmpSubCastId);
		  hrEisEmpTrn.setCmnLookupMstByEmpSubCasteId(cmnLookupMstByEmpSubCasteId);
		  hrEisEmpTrn1.setCmnLookupMstByEmpSubCasteId(cmnLookupMstByEmpSubCasteId);
		 
		  CmnLookupMst cmnLookupMstByEmpReligionId = new CmnLookupMst();
		  cmnLookupMstByEmpReligionId.setLookupName(add_EmpReligion);
		  hrEisEmpTrn.setCmnLookupMstByEmpReligionId(cmnLookupMstByEmpReligionId);
		  hrEisEmpTrn1.setCmnLookupMstByEmpReligionId(cmnLookupMstByEmpReligionId);		  
		 
		  CmnLookupMst cmnLookupMstByEmpCategoryId = new CmnLookupMst();
		  cmnLookupMstByEmpCategoryId.setLookupName(add_EmpCategory);
		  hrEisEmpTrn.setCmnLookupMstByEmpCategoryId(cmnLookupMstByEmpCategoryId);							
		  hrEisEmpTrn1.setCmnLookupMstByEmpCategoryId(cmnLookupMstByEmpCategoryId);
		  
		  CmnLookupMst CmnLookupMstByEmpMaritalStatusId = new CmnLookupMst();
		  CmnLookupMstByEmpMaritalStatusId.setLookupName(add_EmpMaritalStatus);
		  hrEisEmpTrn.setCmnLookupMstByEmpMaritalStatusId(CmnLookupMstByEmpMaritalStatusId);							
		  hrEisEmpTrn1.setCmnLookupMstByEmpMaritalStatusId(CmnLookupMstByEmpMaritalStatusId);	
		  
		 
		  hrEisEmpTrn.setEmpStdCode(add_EmpPhoneStd);
		  hrEisEmpTrn.setEmpPhoneNumber(add_EmpPhoneNum);
		  hrEisEmpTrn.setEmpMobileNumber(add_EmpMobileNo);
		  hrEisEmpTrn.setEmpEmail(add_EmpEMail);
		  
		  /*************************/
		  hrEisEmpTrn1.setEmpPrefix(add_EmpSalutation);
		  hrEisEmpTrn1.setEmpFname(add_Gu_EmpFname);
		  hrEisEmpTrn1.setEmpMname(add_Gu_EmpMname);
		  hrEisEmpTrn1.setEmpLname(add_Gu_EmpLname);
         			  
		  hrEisEmpTrn1.setEmpGender(addGen);
          hrEisEmpTrn1.setEmpDob(add_EmpDob);	
          hrEisEmpTrn1.setEmpStdCode(add_EmpPhoneStd);
		  hrEisEmpTrn1.setEmpPhoneNumber(add_EmpPhoneNum);
		  hrEisEmpTrn1.setEmpMobileNumber(add_EmpMobileNo);
		  hrEisEmpTrn1.setEmpEmail(add_EmpEMail);
		  
		  //		Satrts Change by sunil for Nationality On 31/05/08
		  CmnCountryMst cmnCountryMstObj=null;
		  String countryIdStr = StringUtility.getParameter("Nationality",requestAdd);
		  if(countryIdStr != null &&  !"Select".equals(countryIdStr)  &&  !"select".equals(countryIdStr) && !"".equals(countryIdStr))
		  {
			  cmnCountryMstObj = new CmnCountryMst();
			  cmnCountryMstObj.setCountryCode(countryIdStr);
		  }
		  hrEisEmpTrn.setCmnCountryMstByEmpNationality(cmnCountryMstObj);							
		  hrEisEmpTrn1.setCmnCountryMstByEmpNationality(cmnCountryMstObj);
		
		  //Ends Change by sunil for Nationality On 31/05/08
		  
		  objectArgs.put(REQ_ID,reqId);
		  objectArgs.put(LANG_ID,langId);

		  objectArgs.put(SETVOGEN_EN,hrEisEmpTrn);
		  objectArgs.put(SETVOGEN_GU,hrEisEmpTrn1);
		 
		  addObject.setResultValue(objectArgs);
		
	} catch (Exception e) {
		addObject.setThrowable(e);
		addObject.setResultCode(ErrorConstants.ERROR);
		addObject.setResultValue(objectArgs);
		logger.error("Exception while Calling generateEmpAddMap method in HrEisEmpTrnVOGen Service",e);
	}
	
	return addObject;
}	

}
