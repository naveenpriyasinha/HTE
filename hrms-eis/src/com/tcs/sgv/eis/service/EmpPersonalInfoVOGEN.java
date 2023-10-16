package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisBiometricDtl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisLangPrfcncyDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpcontactMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;
public class EmpPersonalInfoVOGEN extends ServiceImpl
{	
	private static final Log logger = LogFactory.getLog(EmpPersonalInfoVOGEN.class);
	
	public final String ORG_EMP_MST_VOGEN = "orgEmpMstVOgen";
	public final String HR_EIS_EMP_MST_VOGEN = "hrEisEmpMstVOgen";
	
	public final String SERVICE_LOCATOR = "serviceLocator";
	public final String REQUEST_OBJETCT="requestObj";
	
	public final String EMP_TYPE="EmpType";
	
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
	
	public final String EMP_BG="empBg";
  	public final String EMP_IDENTIFICATION_MARK="empIdentificationMark";
  	public final String EMP_WEIGHT="empWeight";
  	public final String EMP_HEIGHT="empHeight";
  	public final String EMP_CHEST="empChest";
	
	public final String ADDRESS_VO_GENERATOR="ADDRESS_VOGENERATOR";
	public final String BIRTH_PLACE_ADDRESS="birthPlaceAddress";
	public final String NATIVE_PLACE_ADDRESS="nativePlaceAddress";
	public final String PERMANENT_PLACE_ADDRESS="permanentPlaceAddress";
	public final String CURRENT_PLACE_ADDRESS="currentPlaceAddress";
	
	public final String PHY_CHALLENGED="radPhyChallenged";
	public final String TYPE_OF_DISABILITY="type_Of_Disability";
	public final String EMP_DISABILITY_DTLS="empDisabilityDtls";
	
	public final String EMP_CONTACT_XML="contactXML";
	public final String EMP_ADDED_CONTACT_XML="addedcontactXML";
	public final String UPDATED_VO_LIST="upDatedVOList";
	public final String DELETED_VO_LIST="deletedVOList";
	public final String NO_MODIFIED_VO_LIST="notModifiedVOList";
	
	public final String PROF_LANG_XML="langXML";
	public final String PROF_ADDEDLANG_XML="addedlangXML";
	public final String UPDATED_LANG_VO_LIST="updatedLangVOList";
	public final String DELETED_LANG_VO_LIST="deletedLangVOList";
	public final String NO_MODIFIED_LANG_VO_LIST="notModifiedLangVOList";
	
	public final String SR_NO="srNo";
	public final String EMP_LANG_KNOW="emp_lang_known";
	public final String EMP_SPEAK_PROFICIENCY="emp_speak_proficiency";
	public final String EMP_READ_PROFICIENCY="emp_read_proficiency";
	public final String EMP_WRITE_PROFICIENCY="emp_write_proficiency";
	
	public final String HDN_EMP_CONTACT_ID ="hdnEmpContactId";
	public final String EMP_CONTACT_TYPE ="emp_contact_type";
	public final String EMP_CONTACT ="emp_contact";
	
	public final String AJAX_KEY="ajaxKey";
	public final String AJAX_DATA="ajaxData";
	

@SuppressWarnings("unchecked")
public ResultObject generateEmpInfoAddMap(Map objectArgs){
	
	logger.info("In the generateEmpInfoAddMap VOGenMethod for EmpPersonalInfoVOGEN ***********************");
	
	ResultObject addObject=new ResultObject(ErrorConstants.SUCCESS);
	HttpServletRequest requestAdd=(HttpServletRequest)objectArgs.get(REQUEST_OBJETCT);
	ServiceLocator servLoc = (ServiceLocator) objectArgs.get(SERVICE_LOCATOR);
	
	OrgEmpMst orgEmpMst  =new OrgEmpMst();
	HrEisEmpMst hrEisEmpMst=new HrEisEmpMst();
	CmnLookupMst cmnLookupMst=new CmnLookupMst();
	
	try {
		 
		
		long iUserId = (requestAdd.getParameter("userId") != null && !requestAdd.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",requestAdd)) : 0;
		logger.info("iUserId In VOGEN ====================="+iUserId);
		
/**** Employee ORG_EMP_MST Dtls****/
		  String add_EmpSalutation=StringUtility.getParameter(EMP_PREFIX,requestAdd);
		  String add_EmpFname=StringUtility.getParameter(EN_EMP_FNAME,requestAdd);
		  String add_EmpLname=StringUtility.getParameter(EN_EMP_LNAME, requestAdd);
		  String add_EmpMname=StringUtility.getParameter(EN_EMP_MNAME,requestAdd);
		  Date add_EmpDob=StringUtility.convertStringToDate(StringUtility.getParameter(EMP_DOB,requestAdd));
		  orgEmpMst.setEmpFname(add_EmpFname);
		  orgEmpMst.setEmpLname(add_EmpLname);
		  orgEmpMst.setEmpMname(add_EmpMname);
		  orgEmpMst.setEmpDob(add_EmpDob);
		  orgEmpMst.setEmpPrefix(add_EmpSalutation);
/**** Employee ORG_EMP_MST Dtls****/		  


/**** Starts Employee HR_EIS_EMP_MST Dtls****/		 
		  String add_EmpGender=StringUtility.getParameter(EMP_GENDER,requestAdd);
		  java.lang.Character addGen= StringUtility.convertToChar(add_EmpGender);
		  hrEisEmpMst.setEmpGender(addGen);
		   
		  String strRadNativeAddress=StringUtility.getParameter("hdNativeAddress",requestAdd);
		  String strRadPermanentAddress=StringUtility.getParameter("hdPermanentAddress",requestAdd);
		  String strRadCurrentAddress=StringUtility.getParameter("hdCurrentAddress",requestAdd);
		  
		  
		  objectArgs.put("strRadNativeAddress", strRadNativeAddress);
		  objectArgs.put("strRadPermanentAddress", strRadPermanentAddress);
		  objectArgs.put("strRadCurrentAddress", strRadCurrentAddress);
		  		  
		  String add_EmpMotherTongue=StringUtility.getParameter(EMP_MOTHER_TONGUE,requestAdd);
		  logger.info("add_EmpMotherTongue============In VoGen"+add_EmpMotherTongue);
		 
		  CmnLookupMst cmnLookupMstByMotherTongue = new CmnLookupMst();
		  cmnLookupMstByMotherTongue.setLookupName(add_EmpMotherTongue);
		  hrEisEmpMst.setCmnLanguageMst(cmnLookupMstByMotherTongue);
		  
		  String strEmpType=StringUtility.getParameter(EMP_TYPE,requestAdd);
		  
		  String add_EmpReligion=StringUtility.getParameter(EMP_RELIGION,requestAdd);
		  CmnLookupMst cmnLookupMstByEmpReligionId = new CmnLookupMst();
		  cmnLookupMstByEmpReligionId.setLookupName(add_EmpReligion);
		  hrEisEmpMst.setCmnLookupMstByEmpReligionId(cmnLookupMstByEmpReligionId);
		  
		  String add_EmpCastId=StringUtility.getParameter(EMP_CAST_ID,requestAdd);
		  CmnLookupMst cmnLookupMstByEmpCasteId = new CmnLookupMst();
		  cmnLookupMstByEmpCasteId.setLookupName(add_EmpCastId);
		  hrEisEmpMst.setCmnLookupMstByEmpCasteId(cmnLookupMstByEmpCasteId);
		 
		  String add_EmpSubCastId=StringUtility.getParameter(EMP_SUBCAST_ID,requestAdd);
		  CmnLookupMst cmnLookupMstByEmpSubCasteId = new CmnLookupMst();
		  cmnLookupMstByEmpSubCasteId.setLookupName(add_EmpSubCastId);
		  hrEisEmpMst.setCmnLookupMstByEmpSubCasteId(cmnLookupMstByEmpSubCasteId);
		  
		  String add_EmpCategory=StringUtility.getParameter(EMP_CATEGORY,requestAdd);
		  CmnLookupMst cmnLookupMstByEmpCategoryId = new CmnLookupMst();
		  cmnLookupMstByEmpCategoryId.setLookupName(add_EmpCategory);
		  hrEisEmpMst.setCmnLookupMstByEmpCategoryId(cmnLookupMstByEmpCategoryId);
		  
		  String add_EmpMaritalStatus=StringUtility.getParameter(EMP_MARITAL_STATUS,requestAdd);
		  CmnLookupMst cmnLookupMstByEmpMaritalStatusId = new CmnLookupMst();
		  cmnLookupMstByEmpMaritalStatusId.setLookupName(add_EmpMaritalStatus);
		  hrEisEmpMst.setCmnLookupMstByEmpMaritalStatusId(cmnLookupMstByEmpMaritalStatusId);
		  
/****Ends Employee HR_EIS_EMP_MST Dtls****/	  
		  
/**** Employee Phy Challenged Dtls****/
		String lStrEmpPhyChallenged =null;
		String lStrTypeOfDisablilty=null;
		String lStrEmpDisabilityDtls =null;
		String lStrEmpBG =null;
	  	String lStrEmpIdentificationMark =null;
	  	double lStrEmpWeight =0.0;
	  	double lStrEmpHeight =0.0;
	  	double lStrEmpChest = 0.0; 
	  	String[] contactXML=null;
  		String[] addedcontactXML =null;
  		String[] langXML=null;
		String[] addedlangXML =null;
		HrEisBiometricDtl hrPersonBiometricDtls = new HrEisBiometricDtl();	
		if(StringUtility.getParameter(PHY_CHALLENGED, requestAdd)!=null){lStrEmpPhyChallenged = StringUtility.getParameter(PHY_CHALLENGED, requestAdd);}   
		
      	if(lStrEmpPhyChallenged.trim().equalsIgnoreCase("Y"))
      	{            		                
      		logger.info("inside type_Of_Disability ::"+lStrEmpPhyChallenged);
      		if(StringUtility.getParameter(TYPE_OF_DISABILITY,requestAdd)!=null){lStrTypeOfDisablilty = StringUtility.getParameter(TYPE_OF_DISABILITY,requestAdd);}
      		CmnLookupMst cmnLookUpDisabilityType = new CmnLookupMst();
      		cmnLookUpDisabilityType.setLookupName(lStrTypeOfDisablilty);
      		hrPersonBiometricDtls.setCmnLookupMstByEmpTypeOfDisability(cmnLookUpDisabilityType);
      		
      		if(StringUtility.getParameter(EMP_DISABILITY_DTLS,requestAdd)!=null && !StringUtility.getParameter(EMP_DISABILITY_DTLS,requestAdd).equals(""))
      		{
      			lStrEmpDisabilityDtls = StringUtility.getParameter(EMP_DISABILITY_DTLS,requestAdd);
      			hrPersonBiometricDtls.setEmpDisabilityDetails(lStrEmpDisabilityDtls);
      		}else {hrPersonBiometricDtls.setEmpDisabilityDetails(lStrEmpDisabilityDtls);}
      		hrPersonBiometricDtls.setEmpPhyChallenged('Y');
      	} 
      	else 
      	{
      		logger.info("outside type_Of_Disability ::"+lStrEmpPhyChallenged);
      		hrPersonBiometricDtls.setCmnLookupMstByEmpTypeOfDisability(null);
      		hrPersonBiometricDtls.setEmpDisabilityDetails(null);
      		hrPersonBiometricDtls.setEmpPhyChallenged('N');        		
      	}           
      	
      	if(StringUtility.getParameter(EMP_BG,requestAdd)!=null)
      	{
      		lStrEmpBG = StringUtility.getParameter(EMP_BG,requestAdd);
      	}
  		cmnLookupMst = new CmnLookupMst();
  		cmnLookupMst.setLookupName(lStrEmpBG);
  		hrPersonBiometricDtls.setCmnLookupMstByEmpBloodGroup(cmnLookupMst);
  		
  		if(StringUtility.getParameter(EMP_IDENTIFICATION_MARK,requestAdd)!=null && !StringUtility.getParameter(EMP_IDENTIFICATION_MARK,requestAdd).equals(""))
  		{
  			lStrEmpIdentificationMark = StringUtility.getParameter(EMP_IDENTIFICATION_MARK,requestAdd);
  			hrPersonBiometricDtls.setEmpIdentificationMark(lStrEmpIdentificationMark);
  		}
  		else 	{	hrPersonBiometricDtls.setEmpIdentificationMark(lStrEmpIdentificationMark);}
  		
  		if(StringUtility.getParameter(EMP_WEIGHT,requestAdd)!=null && !StringUtility.getParameter(EMP_WEIGHT,requestAdd).equals(""))
  		{
  			lStrEmpWeight = Double.valueOf(StringUtility.getParameter(EMP_WEIGHT,requestAdd));
  			hrPersonBiometricDtls.setEmpWeight(lStrEmpWeight);
  		}
  		else {hrPersonBiometricDtls.setEmpWeight(lStrEmpWeight);}
  		
  		if(StringUtility.getParameter(EMP_HEIGHT,requestAdd)!=null && !StringUtility.getParameter(EMP_HEIGHT,requestAdd).equals(""))
  		{
  			lStrEmpHeight = Double.valueOf(StringUtility.getParameter(EMP_HEIGHT,requestAdd));
  			hrPersonBiometricDtls.setEmpHeight(lStrEmpHeight);
  		}
  		else {hrPersonBiometricDtls.setEmpHeight(lStrEmpHeight);}
  		
  		
  		if(StringUtility.getParameter(EMP_CHEST,requestAdd)!=null && !StringUtility.getParameter(EMP_CHEST,requestAdd).equals(""))
  		{
  			lStrEmpChest = Double.valueOf(StringUtility.getParameter(EMP_CHEST,requestAdd));
  			hrPersonBiometricDtls.setEmpChest(lStrEmpChest);   
  		}
  		else {hrPersonBiometricDtls.setEmpChest(lStrEmpChest);}
  		 
  		
      /***End Of Challenged  ***/
  		hrEisEmpMst.setHrPersonBiometricDtls(hrPersonBiometricDtls);  // BioMatric VO            
      /****End Of Employee Phy Dtls ***********/
		  
  		/**Gettinga Emp Known Contact Details**/
  		if(StringUtility.getParameterValues(EMP_CONTACT_XML, requestAdd)!=null){contactXML = StringUtility.getParameterValues(EMP_CONTACT_XML, requestAdd);}
    	if(StringUtility.getParameterValues(EMP_ADDED_CONTACT_XML, requestAdd)!=null){addedcontactXML = StringUtility.getParameterValues(EMP_ADDED_CONTACT_XML, requestAdd);}
    	
    	objectArgs.put(EMP_CONTACT_XML, contactXML);     
    	
    	Map vOMap = FileUtility.getUpdatedDeletedVOListFromXML(addedcontactXML);			
		List updatedVOList = (List) vOMap.get(UPDATED_VO_LIST);			
		List deletedVOList = (List) vOMap.get(DELETED_VO_LIST);
		List notModifiedVOList = (List) vOMap.get(NO_MODIFIED_VO_LIST);		
		
		objectArgs.put(UPDATED_VO_LIST, updatedVOList);
		objectArgs.put(DELETED_VO_LIST, deletedVOList);
		objectArgs.put(NO_MODIFIED_VO_LIST, notModifiedVOList);
		
		/****End of Gettinga Emp Known Contact Dtls****/
		
		  CmnCountryMst cmnCountryMstObj=null;
		  String countryIdStr = StringUtility.getParameter("Nationality",requestAdd);
		  if(countryIdStr != null && !"Select".equals(countryIdStr)  &&  !"select".equals(countryIdStr) && !"".equals(countryIdStr))
		  {
			  cmnCountryMstObj = new CmnCountryMst();
			  cmnCountryMstObj.setCountryCode(countryIdStr);
		  }
		  hrEisEmpMst.setCmnCountryMstByEmpNationality(cmnCountryMstObj);							
		
		
		/**Gettinga Emp Known Language Details**/
 		
		if( StringUtility.getParameterValues(PROF_LANG_XML, requestAdd)!=null){langXML = StringUtility.getParameterValues(PROF_LANG_XML, requestAdd);}            
    	if(StringUtility.getParameterValues(PROF_ADDEDLANG_XML, requestAdd)!=null){addedlangXML = StringUtility.getParameterValues(PROF_ADDEDLANG_XML, requestAdd);}
    	
    	objectArgs.put(PROF_LANG_XML, langXML);            	
    	
    	Map vOLangMap = FileUtility.getUpdatedDeletedVOListFromXML(addedlangXML);			
		List updatedLangVOList = (List) vOLangMap.get(UPDATED_VO_LIST);			
		List deletedLangVOList = (List) vOLangMap.get(DELETED_VO_LIST);
		List notModifiedLangVOList = (List) vOLangMap.get(NO_MODIFIED_VO_LIST);		
		
		objectArgs.put(UPDATED_LANG_VO_LIST, updatedLangVOList);
		objectArgs.put(DELETED_LANG_VO_LIST, deletedLangVOList);
		objectArgs.put(NO_MODIFIED_LANG_VO_LIST, notModifiedLangVOList);
		/****End of Gettinga Emp Known Contact Dtls****/
		
		/** VOGEN - Address - Starts */
		FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, servLoc.getSessionFactory());
		FrmServiceMst servDetails = servDetailsImpl.readService(ADDRESS_VO_GENERATOR);
		addObject = servLoc.executeService(servDetails,objectArgs);
		
		if(objectArgs.containsKey(BIRTH_PLACE_ADDRESS))
		{
			logger.info("Inside Birth Addresss VoGen");
			CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get(BIRTH_PLACE_ADDRESS);
			hrEisEmpMst.setCmnAddressMstByEmpBirthPlaceAddressId(cmnAddressMst);
			logger.info("Birth Id IN VOGEN :::: "+cmnAddressMst.getArea());
		}
		
		if(objectArgs.containsKey(NATIVE_PLACE_ADDRESS))
		{
			CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get(NATIVE_PLACE_ADDRESS);
			hrEisEmpMst.setCmnAddressMstByEmpNativePlaceAddressId(cmnAddressMst);
		}
		
		if(objectArgs.containsKey(PERMANENT_PLACE_ADDRESS))
		{
			CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get(PERMANENT_PLACE_ADDRESS);
			hrEisEmpMst.setCmnAddressMstByEmpPermanentAddressId(cmnAddressMst);
		}
		
		if(objectArgs.containsKey(CURRENT_PLACE_ADDRESS))
		{
			CmnAddressMst cmnAddressMst=(CmnAddressMst)objectArgs.get(CURRENT_PLACE_ADDRESS);
			hrEisEmpMst.setCmnAddressMstByEmpCurrentAddressId(cmnAddressMst);
		}
	
		/** VOGEN - Address - Ends */

		String strDepartmentName=requestAdd.getParameter("hdnstrDepartmentName")!=null ? requestAdd.getParameter("hdnstrDepartmentName"):"";  
		objectArgs.put("strDepartmentName",strDepartmentName);
		
  		objectArgs.put(HR_EIS_EMP_MST_VOGEN,hrEisEmpMst);
  		objectArgs.put(ORG_EMP_MST_VOGEN,orgEmpMst);
  		objectArgs.put("EmpType",strEmpType);
  		objectArgs.put("userId", iUserId); 
		addObject.setResultCode(ErrorConstants.SUCCESS);		
		addObject.setResultValue(objectArgs);
		
	} catch (Exception e) {
		
		addObject.setThrowable(e);
		addObject.setResultCode(ErrorConstants.ERROR);
		addObject.setResultValue(objectArgs);
		logger.error("Error While Executing generateEmpInfoAddMap VoGen ",e);
	}
	return addObject;
}	

public ResultObject addEmpKnownLanguages(Map<String, Object> objectArgs)
{
	long srNo= 0;
	String cmn_lang_mst=null;
	String emp_speak_proficiency=null;
	String emp_read_proficiency=null;
	String emp_write_proficiency=null;
	ResultObject objRes = new ResultObject();
	try 
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get(REQUEST_OBJETCT);	
		
		if(StringUtility.getParameter(SR_NO, request).toString()!=null){srNo = StringUtility.convertToLong(StringUtility.getParameter(SR_NO, request));}
		if(StringUtility.getParameter(EMP_LANG_KNOW, request)!=null){cmn_lang_mst = StringUtility.getParameter(EMP_LANG_KNOW, request);}
		if(StringUtility.getParameter(EMP_SPEAK_PROFICIENCY, request)!=null){emp_speak_proficiency=StringUtility.getParameter(EMP_SPEAK_PROFICIENCY, request);}
		if(StringUtility.getParameter(EMP_READ_PROFICIENCY, request)!=null){emp_read_proficiency=StringUtility.getParameter(EMP_READ_PROFICIENCY, request);}
		if(StringUtility.getParameter(EMP_WRITE_PROFICIENCY, request)!=null){emp_write_proficiency=StringUtility.getParameter(EMP_WRITE_PROFICIENCY, request);}
				
		/*logger.info("===============emp_speak_proficiency============="+emp_speak_proficiency);
		logger.info("===============emp_speak_proficiency============="+emp_read_proficiency);
		logger.info("===============emp_speak_proficiency============="+emp_write_proficiency);*/
		
		HrEisLangPrfcncyDtl hrEisLanguageProficiency = new HrEisLangPrfcncyDtl();
		 /** Change By Sunil For language*/ 
		CmnLookupMst cmnLanguageMstByLanguageId = new CmnLookupMst();
		cmnLanguageMstByLanguageId.setLookupName(cmn_lang_mst);
		hrEisLanguageProficiency.setCmnLanguageMstByLanguageId(cmnLanguageMstByLanguageId);
		
		CmnLookupMst speakProficiency = new CmnLookupMst();
		speakProficiency.setLookupName(emp_speak_proficiency);
		hrEisLanguageProficiency.setCmnLookupMst(speakProficiency);
		
		CmnLookupMst readProficiency = new CmnLookupMst();
		readProficiency.setLookupName(emp_read_proficiency);
		hrEisLanguageProficiency.setCmnLookupMstReadProf(readProficiency);
		
		CmnLookupMst writeProficiency = new CmnLookupMst();
		writeProficiency.setLookupName(emp_write_proficiency);		
		hrEisLanguageProficiency.setCmnLookupMstWriteProf(writeProficiency);

		hrEisLanguageProficiency.setLangProfPkId(srNo);
		
		logger.info("==================For Speak hrEisLanguageProficiency.getCmnLookupMstReadProf().getLookupName() ============="+ hrEisLanguageProficiency.getCmnLookupMst().getLookupName());
		logger.info("==================For READ hrEisLanguageProficiency.getCmnLookupMstReadProf().getLookupName() ============="+ hrEisLanguageProficiency.getCmnLookupMstReadProf().getLookupName());
		logger.info("==================For WRITE hrEisLanguageProficiency.getCmnLookupMstReadProf().getLookupName() ============="+ hrEisLanguageProficiency.getCmnLookupMstWriteProf().getLookupName());
		
		String xmlStrObj = FileUtility.voToXmlFileByXStream(hrEisLanguageProficiency);
		
		logger.info("================== Xml path for language section ============="+ xmlStrObj);
		
		objectArgs.put(AJAX_KEY, xmlStrObj);	
		objRes.setResultValue(objectArgs);	
		objRes.setViewName(AJAX_DATA);
		objRes.setResultCode(ErrorConstants.SUCCESS);			
	}catch(Exception e)
	{
		logger.error("Error is: "+ e.getMessage());
		objRes.setResultCode(ErrorConstants.ERROR);
		objRes.setResultValue(objectArgs);
		logger.error("Error While adding a Employee Known Language ",e);
	}
	return objRes;
}

public ResultObject addEmpContacts(Map<String, Object> objectArgs)
{		
	String cmn_empContact_mst =null;
	String emp_contact=null;
	long hdnEmpContactId= 0;
	ResultObject objRes = new ResultObject();
	try 
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get(REQUEST_OBJETCT);	

		if(request.getParameter(HDN_EMP_CONTACT_ID)!=null){hdnEmpContactId = StringUtility.convertToLong(StringUtility.getParameter(HDN_EMP_CONTACT_ID, request));}
		if(StringUtility.getParameter(EMP_CONTACT_TYPE, request)!=null){cmn_empContact_mst = StringUtility.getParameter(EMP_CONTACT_TYPE, request);}
		if(StringUtility.getParameter(EMP_CONTACT, request)!=null){emp_contact=StringUtility.getParameter(EMP_CONTACT, request);}

		OrgEmpcontactMst orgEmpcontactMst =new OrgEmpcontactMst();
		CmnLookupMst empContactType  = new CmnLookupMst();
		empContactType.setLookupName(cmn_empContact_mst);
		orgEmpcontactMst.setCmnLookupMst(empContactType);

		orgEmpcontactMst.setContactNumber(emp_contact);
		orgEmpcontactMst.setEmpContactId(hdnEmpContactId);
		
		String xmlStrObj = FileUtility.voToXmlFileByXStream(orgEmpcontactMst);
		logger.info("================== FOR EMP_CONTACT ::xmlStrObj path ==========="+xmlStrObj);
		objectArgs.put(AJAX_KEY, xmlStrObj);	
		objRes.setResultValue(objectArgs);	
		objRes.setViewName(AJAX_DATA);
		objRes.setResultCode(ErrorConstants.SUCCESS);			
	}catch(Exception e)
	{
		objRes.setThrowable(e);
		objRes.setResultCode(ErrorConstants.ERROR);
		objRes.setResultValue(objectArgs);
		logger.error("Error While adding a Employee Contact Number In VoGen ",e);
	}
	return objRes;
}

public ResultObject getEmpNextCmbDtls(Map<String, Object> objectArgs)
{
	ResultObject objRes = new ResultObject();
	try 
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");			
		String cmbId = StringUtility.getParameter("cmbId", request);
		objectArgs.put("cmbId", cmbId);			
		objRes.setResultValue(objectArgs);				
		objRes.setResultCode(ErrorConstants.SUCCESS);			
	}
	catch(Exception e)
	{
		objRes.setThrowable(e);
		objRes.setResultCode(ErrorConstants.ERROR);
		objRes.setResultValue(objectArgs);
		logger.error("Error While gtting Emp  Next Combo Dtls in VoGen",e);
	}
	return objRes;
}

}
