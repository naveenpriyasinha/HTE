package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxn;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class FamilyDtlsVOGEN extends ServiceImpl 
{

	private static final Log logger = LogFactory.getLog(FamilyDtlsVOGEN.class);	
	public ResultObject empFamilyDtlsVOGEN(Map<String, Object> objectArgs)
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try {					
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	

//			IFMS - Starts
			boolean blnWorkFlowEnabled = true;

			if (request.getParameter("workFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
			}

			logger.info("blnWorkFlowEnabled In empFamilyDtlsVOGEN======"+blnWorkFlowEnabled);
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;

			// IFMS - Ends

			String lStrFlag = request.getParameter("flag") != null ? request.getParameter("flag") : "";

			objectArgs.put("flag", lStrFlag);								
			if("SubmitFamilyDtls".equalsIgnoreCase(lStrFlag)) 
			{											
				String draft =StringUtility.getParameter("draft",request);						
				objectArgs.put("draft", draft);
				String deleteArr =StringUtility.getParameter("DeleteArr",request);						
				objectArgs.put("deleteArr", deleteArr);						
				objRes=submitFamilyDtls(objectArgs,blnWorkFlowEnabled);//IFMS

			}
			else if("getFamilyPendingRecordForView".equalsIgnoreCase(lStrFlag))
			{
				String reqId =StringUtility.getParameter("reqId",request);				
				objectArgs.put("reqId", reqId);						
			}
			else if("deleteEmpFamilyRecord".equals(lStrFlag))
			{
				String delId =StringUtility.getParameter("delId",request);				
				objectArgs.put("delId", delId);
			}
			else if("getFamilyDraftRecordForView".equalsIgnoreCase(lStrFlag) || "deleteFamilyDraftRecord".equalsIgnoreCase(lStrFlag) || "openFamilyDraftRequest".equalsIgnoreCase(lStrFlag))
			{
				String reqId =StringUtility.getParameter("reqId",request);				
				objectArgs.put("reqId", reqId);					
			}				
			else if("selEmpType".equalsIgnoreCase(lStrFlag))// Change By sunil on 04/06/08 for Employment Dtls
			{
				String cmbid = StringUtility.getParameter("cmbid",request);
				objectArgs.put("cmbid", cmbid);				
			}

			objectArgs.put("userId", iUserId); //IFMS
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); //IFMS
			objRes.setResultValue(objectArgs);
		}catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while setting a flag for Family Service in VOGEN ",e);						
		}
		return objRes;
	}
	public ResultObject submitFamilyDtls(Map<String, Object> objectArgs, boolean blnWorkFlowEnabled) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		logger.info("Inside SubmitFamilyDtls Vogen ::: Value of blnWorkFlowEnabled"+blnWorkFlowEnabled);
		try {					
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String [] enc_rowNumForAttachment = StringUtility.getParameterValues("encXML_rowNumber", request);

			String[] txnXML = StringUtility.getParameterValues("encXML", request);								
			objectArgs.put("encXML", txnXML);
			objectArgs.put("enc_rowNumForAttachment", enc_rowNumForAttachment);


			//IFMS		
			if(blnWorkFlowEnabled==false)
			{
				logger.info("blnWorkFlowEnabled in VoGen for submitFamilyDtls =============");
				String updateRecordXMLFileA[] = StringUtility.getParameterValues("addedPunch", request);
				String [] addedPunch_rowNumForAttachment = StringUtility.getParameterValues("addedPunch_rowNumber", request);
				Map vOMap = FileUtility.getUpdatedDeletedVOListFromXML(updateRecordXMLFileA, addedPunch_rowNumForAttachment);

				List updatedVOList = (List) vOMap.get("upDatedVOList");
				List deletedVOList = (List) vOMap.get("deletedVOList");
				List notModifiedVOList = (List) vOMap.get("notModifiedVOList");

				ArrayList rowNumsForUpdatedVO =(ArrayList) vOMap.get("rowNumsForUpdatedVO");
				ArrayList rowNumsForDeletedVO =(ArrayList) vOMap.get("rowNumsForDeletedVO");
				ArrayList rowNumsForNotModifiedVO =(ArrayList) vOMap.get("rowNumsForNotModifiedVO");

				objectArgs.put("upDatedVOList", updatedVOList);
				objectArgs.put("deletedVOList", deletedVOList);
				objectArgs.put("notModifiedVOList", notModifiedVOList);

				objectArgs.put("rowNumsForUpdatedVO", rowNumsForUpdatedVO);
				objectArgs.put("rowNumsForDeletedVO", rowNumsForDeletedVO);
				objectArgs.put("rowNumsForNotModifiedVO", rowNumsForNotModifiedVO);
				objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); // IFMS
				objectArgs.put("addedPunch_rowNumForAttachment", addedPunch_rowNumForAttachment); // IFMS

			}


		}catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while submitting family dtls in VOGEN ",e);						
		}
		return objRes;
	}
	public ResultObject addOrEditFamilyVOGEN(Map<String, Object> objectArgs) {		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try {	
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		

			boolean blnWorkFlowEnabled = true;//IFMS-Starts
			if (StringUtility.getParameter("workFlowEnabled",request) != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
				logger.info("Value of blnWorkFlowEnabled in addOrEditFamilyVOGEN=============="+blnWorkFlowEnabled);
			}
//			IFMS-Ends

			HrEisFamilyDtlTxn FmObj=null;
			HrEisFamilyDtl FmObjMst=null; 

			if(blnWorkFlowEnabled){                       //IFMS
				FmObj = new HrEisFamilyDtlTxn();
			}
			else {
				FmObjMst =new HrEisFamilyDtl();  }//IFMS

			String lStrValidatestr="";
			String sendAttachmentId = StringUtility.getParameter("sendAttachmentId", request);	
			logger.info("AttachmentId in Vogen=============:: "+sendAttachmentId);
			if(sendAttachmentId.trim().equals("")==false && sendAttachmentId.trim().equalsIgnoreCase("0")==false && sendAttachmentId.trim()!=null && sendAttachmentId.trim().equalsIgnoreCase("null")==false)
			{
				long lomgObjattchmentId = StringUtility.convertToLong(sendAttachmentId.trim());
				if(lomgObjattchmentId!=0)
				{				
					CmnAttachmentMst cmnAttachmentMst = new CmnAttachmentMst();
					cmnAttachmentMst.setAttachmentId(lomgObjattchmentId);

					if(blnWorkFlowEnabled){FmObj.setCmnAttachmentMst(cmnAttachmentMst); }//IFMS
					else {FmObjMst.setCmnAttachmentMst(cmnAttachmentMst); }
					//FmObj.setCmnAttachmentMst(cmnAttachmentMst);
				}
			}

			String lStrCompareStrLast=StringUtility.getParameter("lastName",request);
			if(lStrCompareStrLast == null || "".equalsIgnoreCase(lStrCompareStrLast)){lStrValidatestr=lStrValidatestr+"eis.LAST_NAME,";}

			String lStrCompareStr = StringUtility.getParameter("firstName",request);
			if(lStrCompareStr == null || "".equalsIgnoreCase(lStrCompareStr)){lStrValidatestr=lStrValidatestr+"eis.FIRST_NAME,";}

			String cmnRelationId="";
			String validateStr="";
			validateStr = StringUtility.getParameter("Relation",request);
			if(validateStr == null || "Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){lStrValidatestr=lStrValidatestr+"eis.Relation,";cmnRelationId="Select";}
			else {cmnRelationId = validateStr;}

			String cmnGenderId=StringUtility.getParameter("Gender",request);
			validateStr = StringUtility.getParameter("Gender",request);
			if(validateStr == null ||  "Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){lStrValidatestr=lStrValidatestr+"eis.GENDER,";cmnGenderId="Select";}
			else {cmnGenderId = validateStr;}	

			validateStr=StringUtility.getParameter("DOB",request);
			if("".equals(validateStr)){lStrValidatestr=lStrValidatestr+"eis.DATE_OF_BIRTH,";}
			else{
				/*IFMS */			if(blnWorkFlowEnabled){FmObj.setFmDateOfBirth(StringUtility.convertStringToDate(validateStr));}
				else {FmObjMst.setFmDateOfBirth(StringUtility.convertStringToDate(validateStr));}
			}

			String cmnDeadAlive;
			validateStr = StringUtility.getParameter("status",request);
			if(validateStr == null ||  "Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){lStrValidatestr=lStrValidatestr+"eis.status,";cmnDeadAlive=null;}
			else {cmnDeadAlive = validateStr;}

			validateStr=StringUtility.getParameter("date_Demise",request); // Change By sunil on 04/06/08 for Dead Family Member Dtls 
			if("".equals(validateStr))
			{
				if(blnWorkFlowEnabled){FmObj.setDateOfDemise(null);}//IFMS
				else {FmObjMst.setDateOfDemise(null);}
				if( StringUtility.getParameter("status",request).equalsIgnoreCase("fm_Dead"))
				{
					lStrValidatestr=lStrValidatestr+"eis.date_Demise,";
				}
			}				
			else 
			{	
				if(blnWorkFlowEnabled)								//IFMS
				{
					FmObj.setDateOfDemise(StringUtility.convertStringToDate(validateStr));
				}
				else 
				{
						FmObjMst.setDateOfDemise(StringUtility.convertStringToDate(validateStr));
				}
			}

			String cmnMaritalStatusId="";
			validateStr =StringUtility.getParameter("MaritalStatus",request);
			if(validateStr == null || "Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){lStrValidatestr=lStrValidatestr+"eis.Marital_Status,";cmnMaritalStatusId="Select";}
			else {cmnMaritalStatusId = validateStr;}


			String cmnEmpStaId="";
			String cmnDependentId="";
			String cmnOccuId="";
			String cmnQuaId="";	
			String strOtherOccupation="";	
			String strSubQualificationLookup="";
			String strDiciplineLookup="";
			CmnCountryMst cmnCountryMstObj=null;
			OrgDepartmentMst orgDeparmentMstObj=null;

			if(!StringUtility.getParameter("status",request).equalsIgnoreCase("fm_Dead"))
			{
				cmnQuaId =StringUtility.getParameter("Qualification",request);

				validateStr =StringUtility.getParameter("dependentValue",request);
				if(validateStr == null || "Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){lStrValidatestr=lStrValidatestr+"eis.fm_dependant_dtls,";cmnDependentId="";}
				else {cmnDependentId = validateStr;}

				validateStr = StringUtility.getParameter("Employment",request);
				if(validateStr == null || "Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){lStrValidatestr=lStrValidatestr+"eis.Employment_Status,";cmnEmpStaId="Select";}
				else {cmnEmpStaId = validateStr;}

				//				change by sunil for Nationality
				String countryIdStr = StringUtility.getParameter("Nationality",request);
				if(countryIdStr != null && !"Select".equalsIgnoreCase(countryIdStr)  && !"".equals(countryIdStr))
				{
					cmnCountryMstObj = new CmnCountryMst();
					cmnCountryMstObj.setCountryCode(countryIdStr);
				}else
				{
					lStrValidatestr=lStrValidatestr+"eis.Nationality,";
				}

				String deparmentCodeStr = StringUtility.getParameter("Dept",request);
				if(StringUtility.getParameter("Occupation",request).equalsIgnoreCase("Government_org"))
				{
					if(deparmentCodeStr != null &&  !"Select".equalsIgnoreCase(deparmentCodeStr) && !"".equals(deparmentCodeStr))
					{
						if(!deparmentCodeStr.equalsIgnoreCase("other"))
						{	
							orgDeparmentMstObj = new OrgDepartmentMst();
							orgDeparmentMstObj.setDepCode(deparmentCodeStr);
						}
					}else
					{
						lStrValidatestr=lStrValidatestr+"eis.Dept,";
						orgDeparmentMstObj = new OrgDepartmentMst();
						orgDeparmentMstObj.setDepCode("Select");
					}
				}

				validateStr =StringUtility.getParameter("Occupation",request);

				if(!StringUtility.getParameter("Employment",request).equalsIgnoreCase("Select") && !StringUtility.getParameter("Employment",request).equalsIgnoreCase("fm_Unemployed") && !StringUtility.getParameter("Employment",request).equalsIgnoreCase("fm_Student"))
				{
					if(validateStr == null || "Select".equalsIgnoreCase(validateStr) || "".equals(validateStr))
					{
						lStrValidatestr=lStrValidatestr+"eis.occupation,";cmnOccuId="Select";
					}
					else {cmnOccuId = validateStr;}
				}
				else{cmnOccuId = validateStr;}

				strOtherOccupation=StringUtility.getParameter("other_occu",request);

				/****** Address Code ***/

				ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");					
				FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, servLoc.getSessionFactory());
				FrmServiceMst servDetails = servDetailsImpl.readService("ADDRESS_VOGENERATOR");				
				objRes=servLoc.executeService(servDetails, objectArgs);					
				if(objectArgs.containsKey("familyPersonAddress"))						
				{
					CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get("familyPersonAddress");
					String strAddressValidate = StringUtility.getParameter("rdoAddressfamilyPersonAddress", request);						
					/*IFMS */				if(blnWorkFlowEnabled){FmObj.setCmnAddressMst(cmnAddressMst);}
					else{FmObjMst.setCmnAddressMst(cmnAddressMst);}

					if(cmnAddressMst.getCmnVillageMst()==null || cmnAddressMst.getCmnCityMst()==null || strAddressValidate.equalsIgnoreCase("OtherRadioAddress")==true)
					{
						if(cmnAddressMst.getCmnCityMst()==null && cmnAddressMst.getCmnVillageMst()==null  && strAddressValidate.equalsIgnoreCase("OtherRadioAddress")==false)
						{								
							lStrValidatestr=lStrValidatestr+"eis.FamilyMemAdd,";
							/*IFMS */						if(blnWorkFlowEnabled){FmObj.setCmnAddressMst(null);}
							else{FmObjMst.setCmnAddressMst(null);}

						}
						if(cmnAddressMst.getCmnCityMst()!=null)
						{
							if(cmnAddressMst.getCmnCityMst().getCityCode().equalsIgnoreCase("Select"))
							{								
								lStrValidatestr=lStrValidatestr+"eis.FamilyMemAdd,";									
								if(blnWorkFlowEnabled){FmObj.setCmnAddressMst(null);}
								/*IFMS */							else{FmObjMst.setCmnAddressMst(null);}
							}
						}
						else if(cmnAddressMst.getCmnVillageMst()!=null) 
						{												
							if(cmnAddressMst.getCmnVillageMst()!=null)	
							{
								if(cmnAddressMst.getCmnVillageMst().getVillageCode().equalsIgnoreCase("Select"))
								{									
									lStrValidatestr=lStrValidatestr+"eis.FamilyMemAdd,";
									/*IFMS */							if(blnWorkFlowEnabled){FmObj.setCmnAddressMst(null);}
									else{FmObjMst.setCmnAddressMst(null);}
								}
							}
						}
						else{logger.info("");}
					}						
				}
				else
				{									
					lStrValidatestr=lStrValidatestr+"eis.FamilyMemAdd,";
				}
				/*******Address Code Ends**/
				strSubQualificationLookup = StringUtility.getParameter("SubQualification",request);
				strDiciplineLookup = StringUtility.getParameter("discipline",request);
			}

			CmnLookupMst cmnLookupRelationId = new CmnLookupMst();
			cmnLookupRelationId.setLookupName(cmnRelationId);
			CmnLookupMst cmnLookupGenderId = new CmnLookupMst();
			cmnLookupGenderId.setLookupName(cmnGenderId);
			CmnLookupMst cmnLookupEmpStaId = new CmnLookupMst();
			cmnLookupEmpStaId.setLookupName(cmnEmpStaId);
			CmnLookupMst cmnLookupOccuId = new CmnLookupMst();
			cmnLookupOccuId.setLookupName(cmnOccuId);
			CmnLookupMst cmnLookupMaritalStatusId = new CmnLookupMst();
			cmnLookupMaritalStatusId.setLookupName(cmnMaritalStatusId);
			CmnLookupMst cmnLookupQuaId = new CmnLookupMst();
			cmnLookupQuaId.setLookupName(cmnQuaId);
			CmnLookupMst cmnLookupDeadOrAlive = new CmnLookupMst();
			cmnLookupDeadOrAlive.setLookupName(cmnDeadAlive);	
			
			CmnLookupMst cmnLookupSubQuaId = new CmnLookupMst();
			cmnLookupSubQuaId.setLookupName(strSubQualificationLookup);
			CmnLookupMst cmnLookupDiscId = new CmnLookupMst();
			cmnLookupDiscId.setLookupName(strDiciplineLookup);

			if(blnWorkFlowEnabled){										//IFMS
				FmObj.setFmFirstName(lStrCompareStr);				
				FmObj.setOtherOccupation(strOtherOccupation);
				FmObj.setFmMiddleName(StringUtility.getParameter("middleName",request));
				FmObj.setFmLastName(lStrCompareStrLast);
				FmObj.setFmRemarks(StringUtility.getParameter("Remarks",request));								
				FmObj.setFmRelationOther(StringUtility.getParameter("otherRelation",request));				
				FmObj.setCompanyName(StringUtility.getParameter("Name_of_Company",request));				
				FmObj.setAnnualIncome(StringUtility.getParameter("annual_income",request));
				FmObj.setDesignation(StringUtility.getParameter("designation",request));
				FmObj.setDependentOrNot(cmnDependentId);
				FmObj.setCmnLookupMstByFmEmploymentStatus(cmnLookupEmpStaId);
				FmObj.setCmnLookupMstByFmRelation(cmnLookupRelationId);
				FmObj.setCmnLookupMstByFmGender(cmnLookupGenderId);
				FmObj.setCmnLookupMstByFmOccupation(cmnLookupOccuId);			
				FmObj.setCmnLookupMstByFmQualification(cmnLookupQuaId);			
				FmObj.setCmnLookupMstByFmMaritalStatus(cmnLookupMaritalStatusId);
				FmObj.setCmnLookupMstByFmDeadOrAlive(cmnLookupDeadOrAlive);
				FmObj.setCmnCountryMstByFmNationality(cmnCountryMstObj);//change by sunil for Nationality
				FmObj.setOrgDepartmentMstByFmDept(orgDeparmentMstObj);// Change By sunil on 04/06/08 for Employment Dtls 
				FmObj.setCmnLookupMstBySubQualification(cmnLookupSubQuaId);
				FmObj.setCmnLookupMstByDiscipline(cmnLookupDiscId); 
			}
			else{
				FmObjMst.setFmFirstName(StringUtility.getParameter("firstName",request));
				FmObjMst.setFmMiddleName(StringUtility.getParameter("middleName",request));
				FmObjMst.setFmLastName(StringUtility.getParameter("lastName",request));
				FmObjMst.setFmRemarks(StringUtility.getParameter("Remarks",request));								
				FmObjMst.setFmRelationOther(StringUtility.getParameter("otherRelation",request));				
				FmObjMst.setCompanyName(StringUtility.getParameter("Name_of_Company",request));				
				FmObjMst.setAnnualIncome(StringUtility.getParameter("annual_income",request));
				FmObjMst.setDesignation(StringUtility.getParameter("designation",request));
				FmObjMst.setDependentOrNot(cmnDependentId);
				FmObjMst.setOtherOccupation(strOtherOccupation);
				FmObjMst.setCmnLookupMstByFmEmploymentStatus(cmnLookupEmpStaId);
				FmObjMst.setCmnLookupMstByFmRelation(cmnLookupRelationId);
				FmObjMst.setCmnLookupMstByFmGender(cmnLookupGenderId);
				FmObjMst.setCmnLookupMstByFmOccupation(cmnLookupOccuId);			
				FmObjMst.setCmnLookupMstByFmQualification(cmnLookupQuaId);			
				FmObjMst.setCmnLookupMstByFmMaritalStatus(cmnLookupMaritalStatusId);
				FmObjMst.setCmnLookupMstByFmDeadOrAlive(cmnLookupDeadOrAlive);
				FmObjMst.setCmnCountryMstByFmNationality(cmnCountryMstObj);//change by sunil for Nationality
				FmObjMst.setOrgDepartmentMstByFmDept(orgDeparmentMstObj);// Change By sunil on 04/06/08 for Employment Dtls 
				FmObjMst.setCmnLookupMstBySubQualification(cmnLookupSubQuaId); 
				FmObjMst.setCmnLookupMstByDiscipline(cmnLookupDiscId);
			}


			if(blnWorkFlowEnabled)  //IFMS
			{
				if(!lStrValidatestr.equalsIgnoreCase(""))
				{
					FmObj.setDraftFlag("Y");
				}
				else
				{
					FmObj.setDraftFlag("N");
				}
				long parentId = StringUtility.convertToLong((StringUtility.getParameter("parentId",request)));

				if(parentId==0 && lStrValidatestr.equalsIgnoreCase("")==true){FmObj.setRequestFlag("A");}
				else if(lStrValidatestr.equalsIgnoreCase("")==true)
				{
					FmObj.setRequestFlag("U");

					FmObj.setRowNumber(parentId);
				}
				else if(parentId!=0 && lStrValidatestr.equalsIgnoreCase("")==false)
				{

					FmObj.setRequestFlag("N");
					FmObj.setRowNumber(parentId);
				}
			}

			if(blnWorkFlowEnabled)//IFMS
			{
				String xmlFileIdStr = FileUtility.voToXmlFileByXStream(FmObj);
				if(!"".equals(lStrValidatestr))
				{
					xmlFileIdStr=xmlFileIdStr+"$/$"+lStrValidatestr;
				}
				objectArgs.put("ajaxKey",xmlFileIdStr);
				logger.info("Emp Family xml file generated is:::::::: " + xmlFileIdStr);
			}
			else
			{
				long parentId = StringUtility.convertToLong((StringUtility.getParameter("parentId",request)));
				FmObjMst.setMemberId(parentId);
				String xmlFileIdStr = FileUtility.voToXmlFileByXStream(FmObjMst);

				lStrValidatestr = "";

				if(!"".equals(lStrValidatestr))
				{
					xmlFileIdStr=xmlFileIdStr+"$/$"+lStrValidatestr;
				}
				objectArgs.put("ajaxKey",xmlFileIdStr);
				logger.info("===========Family xmlFileIdStrMst=================="+xmlFileIdStr);//End IFMS
			}

			logger.info("Value of blnWorkFlowEnabled in addOrEditFamilyVOGEN At End=============="+blnWorkFlowEnabled);
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); // IFMS
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ajaxData");				
		}catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while adding a Family dtls  in familyVOGEN ",e);						
		}
		return objRes;
	}
	public ResultObject addOrEditFamilyDraftDtls(Map<String, Object> objectArgs) {		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try {					
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");				 				
			HrEisFamilyDtlTxn FmObj = new HrEisFamilyDtlTxn();		
			//Map loginMap = (Map) objectArgs.get("baseLoginMap");			
			//long empID = Long.parseLong(loginMap.get("empId").toString());							
			FmObj.setFmFirstName(StringUtility.getParameter("firstName",request));					
			FmObj.setFmMiddleName(StringUtility.getParameter("middleName",request));					
			FmObj.setFmLastName(StringUtility.getParameter("lastName",request));	
			FmObj.setFmRemarks(StringUtility.getParameter("Remarks",request));				
			FmObj.setFmRelationOther(StringUtility.getParameter("otherRelation",request));

			FmObj.setCompanyName(StringUtility.getParameter("Name_of_Company",request));
			FmObj.setAnnualIncome(StringUtility.getParameter("annual_income",request));
			FmObj.setDesignation(StringUtility.getParameter("designation",request));
			String validateStr=StringUtility.getParameter("date_Demise",request);
			if("".equals(validateStr)){logger.info("");}
			else {					
				FmObj.setDateOfDemise(StringUtility.convertStringToDate(validateStr));
			}	

			validateStr = StringUtility.getParameter("Relation",request);
			String setCmbValue = "Select";
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}
			String cmnRelationId=setCmbValue;

			validateStr = StringUtility.getParameter("Gender",request);
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}
			String cmnGenderId=setCmbValue;	

			validateStr = StringUtility.getParameter("Employment",request);
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}
			String cmnEmpStaId=setCmbValue;

			validateStr = StringUtility.getParameter("Occupation",request);
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}				
			String cmnOccuId=setCmbValue;

			validateStr = StringUtility.getParameter("MaritalStatus",request);
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}				
			String cmnMaritalStatusId=setCmbValue;

			validateStr = StringUtility.getParameter("Qualification",request);
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}				
			String cmnQuaId=setCmbValue;
			
			validateStr = StringUtility.getParameter("SubQualification",request);
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}				
			String cmnSubQuaId=setCmbValue;
			
			validateStr = StringUtility.getParameter("discipline",request);
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}				
			String cmnDiscQuaId=setCmbValue;

			validateStr = StringUtility.getParameter("status",request);
			if("Select".equals(validateStr) || "select".equals(validateStr) || "".equals(validateStr)){setCmbValue="Select";}
			else {setCmbValue = validateStr;}				
			String cmnDeadAlive=setCmbValue;

			CmnCountryMst cmnCountryMstObj=null;
			String countryIdStr = StringUtility.getParameter("Nationality",request);
			if(countryIdStr != null && !"Select".equals(countryIdStr)  && !"select".equals(countryIdStr) && !"".equals(countryIdStr))
			{
				cmnCountryMstObj = new CmnCountryMst();
				cmnCountryMstObj.setCountryCode(countryIdStr);
			}

			// Change By sunil on 04/06/08 for Employment Dtls 
			OrgDepartmentMst orgDeparmentMstObj=null;
			String deparmentCodeStr = StringUtility.getParameter("Dept",request);
			if(deparmentCodeStr != null &&  !"Select".equals(deparmentCodeStr)  &&  !"select".equals(deparmentCodeStr) && !"".equals(deparmentCodeStr))
			{
				orgDeparmentMstObj = new OrgDepartmentMst();
				orgDeparmentMstObj.setDepCode(deparmentCodeStr);
			}

			CmnLookupMst cmnLookupRelationId = new CmnLookupMst();
			cmnLookupRelationId.setLookupName(cmnRelationId);
			CmnLookupMst cmnLookupGenderId = new CmnLookupMst();
			cmnLookupGenderId.setLookupName(cmnGenderId);
			CmnLookupMst cmnLookupEmpStaId = new CmnLookupMst();
			cmnLookupEmpStaId.setLookupName(cmnEmpStaId);
			CmnLookupMst cmnLookupOccuId = new CmnLookupMst();
			cmnLookupOccuId.setLookupName(cmnOccuId);
			CmnLookupMst cmnLookupMaritalStatusId = new CmnLookupMst();
			cmnLookupMaritalStatusId.setLookupName(cmnMaritalStatusId);
			CmnLookupMst cmnLookupQuaId = new CmnLookupMst();
			cmnLookupQuaId.setLookupName(cmnQuaId);
			CmnLookupMst cmnLookupDeadOrAlive = new CmnLookupMst();
			cmnLookupDeadOrAlive.setLookupName(cmnDeadAlive);
			CmnLookupMst cmnLookupSubQuaId = new CmnLookupMst();
			cmnLookupSubQuaId.setLookupName(cmnSubQuaId);
			CmnLookupMst cmnLookupDiscId = new CmnLookupMst();
			cmnLookupDiscId.setLookupName(cmnDiscQuaId);
			
			FmObj.setCmnLookupMstByFmEmploymentStatus(cmnLookupEmpStaId);
			FmObj.setCmnLookupMstByFmRelation(cmnLookupRelationId);
			FmObj.setCmnLookupMstByFmGender(cmnLookupGenderId);
			FmObj.setCmnLookupMstByFmOccupation(cmnLookupOccuId);			
			FmObj.setCmnLookupMstByFmQualification(cmnLookupQuaId);			
			FmObj.setCmnLookupMstByFmMaritalStatus(cmnLookupMaritalStatusId);
			FmObj.setCmnLookupMstByFmDeadOrAlive(cmnLookupDeadOrAlive);
			FmObj.setCmnLookupMstBySubQualification(cmnLookupSubQuaId);
			FmObj.setCmnLookupMstByDiscipline(cmnLookupDiscId); 
			
			validateStr=StringUtility.getParameter("DOB",request);
			if("".equals(validateStr)){logger.info("");}
			else {FmObj.setFmDateOfBirth(StringUtility.convertStringToDate(validateStr));}		

			long parentId = StringUtility.convertToLong((StringUtility.getParameter("parentId",request)));				
			if(parentId==0){logger.info("");}
			else{
				FmObj.setRequestFlag("N");
				FmObj.setRowNumber(parentId);
			}

			/****** Address Code ***/															
			ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");			
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, servLoc.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl.readService("ADDRESS_VOGENERATOR");												
			objRes=servLoc.executeService(servDetails, objectArgs);
			if(objectArgs.containsKey("familyPersonAddress"))  {					
				CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get("familyPersonAddress");					
				FmObj.setCmnAddressMst(cmnAddressMst);				
			}
			/*******Address Code Ends**/
			FmObj.setDraftFlag("Y");				
			String xmlFileIdStr = FileUtility.voToXmlFileByXStream(FmObj);
			logger.info("Emp Family xml file generated is:::::::: " + xmlFileIdStr);
			objectArgs.put("ajaxKey",xmlFileIdStr);	
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ajaxData");				
		}catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while adding a Family Draft dtls  in VO, FamilyVOGEN ",e);						
		}
		return objRes;
	}
	
	public ResultObject checkSubmitFamilyDtls(Map<String, Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		
		try 
		{	
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String[] txnXML = StringUtility.getParameterValues("encXML", request);				
			String deleteFmDtls =StringUtility.getParameter("DeleteArr",request).toString();	
			
			logger.info("==========deleteFmDtls Records MemberId Arr============"+ deleteFmDtls);
		
			List lstlockupTxnVO = FileUtility.xmlFilesToVoByXStream(txnXML);
			
			String strFatherLokpName="fm_rel_Father";
			String strMotherLokpName="fm_rel_Mother";
			int iFatherCounter=0;
			int iMotherCounter=0;
			
			HrEisFamilyDtlTxn txnVObject = new HrEisFamilyDtlTxn();
			HrEisFamilyDtl mstVObject = new HrEisFamilyDtl();
			
			List<HrEisFamilyDtlTxn>listTxnVO=new ArrayList<HrEisFamilyDtlTxn>();
			Map<Long,String> htFamilyMstVO =new HashMap<Long,String>();
			List<Long> removeDelRecList=new ArrayList<Long>();
			
			/**Start----> getting Deleted  Object*/
			StringTokenizer st= new StringTokenizer(deleteFmDtls,",");			
			while(st.hasMoreTokens())
			{					 
				String deleteFmStrObj= st.nextElement().toString();
				
				logger.info("=========deleteFmStrObj========="+ deleteFmStrObj);
				logger.info("=========deleted Member Id======"+ deleteFmStrObj.substring(0, deleteFmStrObj.indexOf("/")));
				
				if (deleteFmStrObj.indexOf("/") != -1)
				{
					try{
						removeDelRecList.add(Long.parseLong(deleteFmStrObj.substring(0, deleteFmStrObj.indexOf("/"))));
					}catch(Exception e){logger.info("Error in parsing ---------------");}
				}
			}
			/**Ends----> getting Deleted  Object*/
			
			for (Iterator i = lstlockupTxnVO.iterator(); i.hasNext();) 
			{
				Object Obj = i.next();
				if(Obj != null)
				{
					Class<? extends Object> c=Obj.getClass();				    	 
					if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxn"))
					{			    		 
						txnVObject = (HrEisFamilyDtlTxn) Obj;
						listTxnVO.add(txnVObject);
					}
					else
					{
						mstVObject = (HrEisFamilyDtl) Obj;
						
						if (!removeDelRecList.contains(mstVObject.getMemberId()))
						{
							String strRelationLookupName = mstVObject.getCmnLookupMstByFmRelation().getLookupName();
							htFamilyMstVO.put(mstVObject.getMemberId(), strRelationLookupName);
							
							if (strRelationLookupName.equals(strFatherLokpName))
							{
								iFatherCounter++;
							}
							else if (strRelationLookupName.equals(strMotherLokpName))
							{
								iMotherCounter++;
							}
						}
					}
				}
			}
			
			logger.info("=============listTxnVO================"+ listTxnVO.size());
			logger.info("=============htFamilyMstVO============"+ htFamilyMstVO);
			logger.info("=============removeDelRecList========="+ removeDelRecList);
			
			logger.info("==========iFatherCounter before======="+ iFatherCounter);
			logger.info("==========iMotherCounter before======="+ iMotherCounter);
						
			
			/**Start----> Opration on  New Added Or Updated Records*/
			for (Iterator txnItr = listTxnVO.iterator(); txnItr.hasNext();) 
			{
				txnVObject=(HrEisFamilyDtlTxn)txnItr.next();
				String strTxnRelation=txnVObject.getCmnLookupMstByFmRelation().getLookupName();
				Long lngFamilyTxnRowNo = txnVObject.getRowNumber();
				
				if(txnVObject.getRequestFlag().equalsIgnoreCase("A"))
				{
					if(strTxnRelation.equalsIgnoreCase(strFatherLokpName))
					{
						iFatherCounter++;
					}
					else if(strTxnRelation.equalsIgnoreCase(strMotherLokpName))
					{
						iMotherCounter++;
					}
				}
				else if(txnVObject.getRequestFlag().equalsIgnoreCase("U"))
				{
					String strFamilyRelationName = htFamilyMstVO.get(lngFamilyTxnRowNo);
					
					if(strTxnRelation.equalsIgnoreCase(strFatherLokpName))
					{
						if (!strTxnRelation.equalsIgnoreCase(strFamilyRelationName))
						{
							iFatherCounter++;
							
							if (strFamilyRelationName.equalsIgnoreCase(strMotherLokpName))
							{
								iMotherCounter--;
							}
						}
					}
					else if(strTxnRelation.equalsIgnoreCase(strMotherLokpName))
					{
						if (!strTxnRelation.equalsIgnoreCase(strFamilyRelationName))
						{
							iMotherCounter++;
							
							if (strFamilyRelationName.equalsIgnoreCase(strFatherLokpName))
							{
								iFatherCounter--;
							}
						}
					}
					else
					{
						if (strFamilyRelationName.equalsIgnoreCase(strFatherLokpName))
						{
							iFatherCounter--;
						}
						else if (strFamilyRelationName.equalsIgnoreCase(strMotherLokpName))
						{
							iMotherCounter--;
						}
					}
				}
			}
				
			/**Ends----> Opration on  New Added Or Updated Records*/
			
			logger.info("======================iFatherCounter after============"+ iFatherCounter);
			logger.info("======================iMotherCounter after============"+ iMotherCounter);
			
			StringBuffer sbXML = new StringBuffer();
			
			if (iFatherCounter <=1 && iMotherCounter<=1)
				objectArgs.put("ajaxKey", "");		
			else
			{
				sbXML.append("<FatherRec>");
				if(iFatherCounter>1){sbXML.append("eis.DuplicateFatherRec");}
				else{sbXML.append("YES");}
				sbXML.append("</FatherRec>");
				
				sbXML.append("<MotherRec>");
				if(iMotherCounter>1){sbXML.append("eis.DuplicateMotherRec");}
				else{sbXML.append("YES");}
				sbXML.append("</MotherRec>");
			
				String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();
				logger.info("Checked Duplication record for Father ad Mother in submitFamilyDtls Method:::::::: " + tempStr);
				objectArgs.put("ajaxKey", tempStr);
			}
			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ajaxData");		
		}
		catch(Exception e)
		{	
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while checkSubmitFamilyDtls family dtls in VOGEN ",e);						
		}
		return objRes;
	}
}
