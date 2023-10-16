package com.tcs.sgv.eis.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisBiometricDtl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmrgcycntcDtl;
import com.tcs.sgv.eis.valueobject.HrEisLangPrfcncyDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpcontactMst;
import com.tcs.sgv.hod.common.valueobject.CmnContactMst;

public class EmpInfoVOGen_WF extends ServiceImpl 
{
	private static final Log logger = LogFactory.getLog(EmpInfoVOGen_WF.class);

	public ResultObject generateEmpMap(Map<String, Object> objectArgs) 
	{
		logger.info("IN EMPLOYEE INFORMATION VOGEN generateEmpMap method");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			HrEisEmpMst hrEisEmpMst= new  HrEisEmpMst();			           

			/**Starts----------->Gettinga Emp Known Language Details**/
			String[] langXML = StringUtility.getParameterValues("langXML", request);            
			String[] addedlangXML = StringUtility.getParameterValues("addedlangXML", request);
			objectArgs.put("langXML", langXML);

			Map vOMap = FileUtility.getUpdatedDeletedVOListFromXML(addedlangXML);
			List updatedVOList = (List) vOMap.get("upDatedVOList");			
			List deletedVOList = (List) vOMap.get("deletedVOList");
			List notModifiedVOList = (List) vOMap.get("notModifiedVOList");		

			objectArgs.put("upDatedVOList", updatedVOList);
			objectArgs.put("deletedVOList", deletedVOList);
			objectArgs.put("notModifiedVOList", notModifiedVOList);
			/**End-------------->Gettinga Emp Known Language Dtls****/

			/**Starts----------->Getting Person Emergency Contact Details***/	
			HrEisEmrgcycntcDtl hrEisEmpEmergencyContact = new HrEisEmrgcycntcDtl();
			String emergencyRadioValue = "";
			emergencyRadioValue = StringUtility.getParameter("radEmergencyDtls", request);

			objectArgs.put("emergencyRadioValue", emergencyRadioValue);

			if(emergencyRadioValue.trim().equalsIgnoreCase("O")==true)
			{
				String  lStrotrPersonFirstName= StringUtility.getParameter("otherPersonFName", request);
				String  lStrotrPersonMiddleName= StringUtility.getParameter("otherPersonMName", request);
				String  lStrotrPersonLastName= StringUtility.getParameter("otherPersonLName", request);

				String  lStrotrEmergencyRel = ""; 
				lStrotrEmergencyRel = StringUtility.getParameter("otherEmergencyRelation", request);

				String  lStrotrRelationtxt = "";
				lStrotrRelationtxt = StringUtility.getParameter("otherRelation", request);

				hrEisEmpEmergencyContact.setContactFirstName(lStrotrPersonFirstName);
				hrEisEmpEmergencyContact.setContactMiddleName(lStrotrPersonMiddleName);
				hrEisEmpEmergencyContact.setContactLastName(lStrotrPersonLastName);
				hrEisEmpEmergencyContact.setOtherRelation(lStrotrRelationtxt);  

				CmnLookupMst cmnLookupMst = new CmnLookupMst();
				cmnLookupMst.setLookupName(lStrotrEmergencyRel);
				hrEisEmpEmergencyContact.setCmnLookupMst(cmnLookupMst);

				hrEisEmpEmergencyContact.setContactType('O');
			}
			else
			{                    		
				String lStrfamilyMemberName= "";
				lStrfamilyMemberName = StringUtility.getParameter("familyPersonName", request);

				objectArgs.put("familyMemberName", lStrfamilyMemberName);
				hrEisEmpEmergencyContact.setContactType('F');  
			}
			Long  lLongotrEmergencyResiPhone = null;
			lLongotrEmergencyResiPhone = StringUtility.convertToLong(StringUtility.getParameter("otherEmergencyResidencePhone", request));

			Long  lLongotherEmergencyOfficePhone = null; 
			lLongotherEmergencyOfficePhone = StringUtility.convertToLong(StringUtility.getParameter("otherEmergencyOfficePhone", request));

			Long  lLongtherEmergencyMobile = null; 
			lLongtherEmergencyMobile = StringUtility.convertToLong(StringUtility.getParameter("otherEmergencyMobile", request));

			String  lStrotherEmeregencyEmail = ""; 
			lStrotherEmeregencyEmail = StringUtility.getParameter("otherEmergencyEmail", request);

			Long  lLongotherEmergencyFax = null; 
			lLongotherEmergencyFax = StringUtility.convertToLong(StringUtility.getParameter("otherEmergencyFax", request));

			CmnContactMst cmnContactMst = new CmnContactMst();
			cmnContactMst.setEmail(lStrotherEmeregencyEmail);
			cmnContactMst.setFax(lLongotherEmergencyFax);
			cmnContactMst.setMobile(lLongtherEmergencyMobile);
			cmnContactMst.setOfficePhone(lLongotherEmergencyOfficePhone);
			cmnContactMst.setResidencePhone(lLongotrEmergencyResiPhone);

			hrEisEmpEmergencyContact.setCmnContactMst(cmnContactMst);
			objectArgs.put("EmergencyVO", hrEisEmpEmergencyContact);        //Emergency VO Obj
			/**End-------------->Gettinga Emergency Contact Details****/	

			/**Starts-------------->Getting Employee Phy Challenged Dtls****/
			HrEisBiometricDtl hrPersonBiometricDtls = new HrEisBiometricDtl();	
			String lStrEmpPhyChallenged = StringUtility.getParameter("radPhyChallenged", request);            	
			if(lStrEmpPhyChallenged.trim().equalsIgnoreCase("Y")==true)
			{            		                
				String lStrTypeOfDisablilty = StringUtility.getParameter("type_Of_Disability",request);
				CmnLookupMst cmnLookUpDisabilityType = new CmnLookupMst();
				cmnLookUpDisabilityType.setLookupName(lStrTypeOfDisablilty);
				hrPersonBiometricDtls.setCmnLookupMstByEmpTypeOfDisability(cmnLookUpDisabilityType);

				String lStrEmpDisabilityDtls = StringUtility.getParameter("empDisabilityDtls",request);
				hrPersonBiometricDtls.setEmpDisabilityDetails(lStrEmpDisabilityDtls);
				hrPersonBiometricDtls.setEmpPhyChallenged('Y');
			} 
			else 
			{
				hrPersonBiometricDtls.setCmnLookupMstByEmpTypeOfDisability(null);
				hrPersonBiometricDtls.setEmpDisabilityDetails(null);
				hrPersonBiometricDtls.setEmpPhyChallenged('N');        		
			}            	
			String lStrEmpBG = StringUtility.getParameter("empBg",request);
			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			cmnLookupMst.setLookupName(lStrEmpBG);
			hrPersonBiometricDtls.setCmnLookupMstByEmpBloodGroup(cmnLookupMst);

			String lStrEmpIdentificationMark = StringUtility.getParameter("empIdentificationMark",request);
			hrPersonBiometricDtls.setEmpIdentificationMark(lStrEmpIdentificationMark);

			double lStrEmpWeight = (StringUtility.getParameter("empWeight",request) != null &&  !StringUtility.getParameter("empWeight",request).equals("")) ? Double.valueOf(StringUtility.getParameter("empWeight",request)) : 0.0;
			hrPersonBiometricDtls.setEmpWeight(lStrEmpWeight);

			double lStrEmpHeight = (StringUtility.getParameter("empHeight",request) != null &&  !StringUtility.getParameter("empHeight",request).equals("")) ? Double.valueOf(StringUtility.getParameter("empHeight",request)) : 0.0; 
			hrPersonBiometricDtls.setEmpHeight(lStrEmpHeight);

			double lStrEmpChest = (StringUtility.getParameter("empChest",request) != null &&  !StringUtility.getParameter("empChest",request).equals("")) ? Double.valueOf(StringUtility.getParameter("empChest",request)) : 0.0;
			hrPersonBiometricDtls.setEmpChest(lStrEmpChest);    
			
			hrEisEmpMst.setHrPersonBiometricDtls(hrPersonBiometricDtls);  // BioMatric VO            
			/**Ends-------------->Getting Employee Phy Challenged Dtls****/

			/**Ends-------------->Getting getting ORGEMPCONTACTMST Dtls*/

			// Phone	
			String lStrEmpPhoneStd = "";
			lStrEmpPhoneStd = StringUtility.getParameter("emp_Phone_std", request);
			if(lStrEmpPhoneStd.length()>3)
			{
				lStrEmpPhoneStd=lStrEmpPhoneStd.substring(0, 2);
			}
			else if (lStrEmpPhoneStd.length()<3)
			{
				if(lStrEmpPhoneStd.length()==2){lStrEmpPhoneStd="0"+lStrEmpPhoneStd;}
				if(lStrEmpPhoneStd.length()==1){lStrEmpPhoneStd="00"+lStrEmpPhoneStd;}
				if(lStrEmpPhoneStd.length()==0){lStrEmpPhoneStd="000"+lStrEmpPhoneStd;}									
			}				
			String lStrEmpPhoneNum = "";
			lStrEmpPhoneNum = StringUtility.getParameter("emp_Phone_Num", request);
			String lStrEmpContactPhoneNum = ""; 
			lStrEmpContactPhoneNum = lStrEmpPhoneStd + lStrEmpPhoneNum;

			OrgEmpcontactMst empcontactMstPhone = new OrgEmpcontactMst();				
			empcontactMstPhone.setContactNumber(lStrEmpContactPhoneNum);
			objectArgs.put("Phone",empcontactMstPhone);

			// Mobile
			String lStrEmpMobileNumber = "";
			lStrEmpMobileNumber = StringUtility.getParameter("emp_Mobile_No", request);

			OrgEmpcontactMst empcontactMstMobile = new OrgEmpcontactMst();				
			empcontactMstMobile.setContactNumber(lStrEmpMobileNumber);
			objectArgs.put("Mobile",empcontactMstMobile);

			// E-Mail
			String lStrEmpEmailNumber = "";
			lStrEmpEmailNumber = StringUtility.getParameter("emp_email", request);

			OrgEmpcontactMst empcontactMstEmail = new OrgEmpcontactMst();
			CmnLookupMst emilCmnLookupMst = new CmnLookupMst();							
			empcontactMstEmail.setCmnLookupMst(emilCmnLookupMst);
			empcontactMstEmail.setContactNumber(lStrEmpEmailNumber);
			objectArgs.put("Email",empcontactMstEmail);

			// radio Button
			String lStrEmpGender ="";
			lStrEmpGender = StringUtility.getParameter("gender", request);
			if(lStrEmpGender.trim().equalsIgnoreCase("M"))
			{
				hrEisEmpMst.setEmpGender('M');
			}
			else
			{
				hrEisEmpMst.setEmpGender('F');
			}
		                  
			objectArgs.put("empMst", hrEisEmpMst);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			resultObject.setThrowable(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
			logger.error("Exception thrown while getting values from request from generateEmpMap Method of EmpInfoVogen_wf",e);
		}		
		return resultObject;
	}	

	public ResultObject addEmpKnownLanguage(Map<String, Object> objectArgs)
	{
		ResultObject objRes = new ResultObject();
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			long srNo= 0;
			if(StringUtility.getParameter("srNo", request).toString()!=null)
			{
				srNo = StringUtility.convertToLong(StringUtility.getParameter("srNo", request));
			}
			String cmn_lang_mst = StringUtility.getParameter("emp_lang_known", request);
			String emp_proficiency=StringUtility.getParameter("emp_proficiency", request);

			
			HrEisLangPrfcncyDtl hrEisLanguageProficiency = new HrEisLangPrfcncyDtl();
			CmnLookupMst cmnLookupMstByLanguageId = new CmnLookupMst();
			cmnLookupMstByLanguageId.setLookupName(cmn_lang_mst);
			hrEisLanguageProficiency.setCmnLanguageMstByLanguageId(cmnLookupMstByLanguageId);

			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			cmnLookupMst.setLookupName(emp_proficiency);			
			hrEisLanguageProficiency.setCmnLookupMst(cmnLookupMst);
			hrEisLanguageProficiency.setLangProfPkId(srNo);

			String xmlStrObj = FileUtility.voToXmlFileByXStream(hrEisLanguageProficiency);
			objectArgs.put("ajaxKey", xmlStrObj);	
			objRes.setResultValue(objectArgs);	
			objRes.setViewName("ajaxData");
			objRes.setResultCode(ErrorConstants.SUCCESS);			
		}catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Error While  getting values from request for  a Employee Known Language in addEmpKnownLanguage Method of of EmpInfoVogen_wf VOGEN",e);
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
			String flag =  StringUtility.getParameter("flag", request);
			if(!"".equals(flag))
			{
				objectArgs.put("family", flag);
			}
			objectArgs.put("cmbId", cmbId);			
			objRes.setResultValue(objectArgs);				
			objRes.setResultCode(ErrorConstants.SUCCESS);			
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Error While gtting Emp  Next Combo Dtls",e);
		}
		return objRes;
	}

}
