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
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtlTxn;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class NomineeDtlsVOGEN extends ServiceImpl 
{
	private static final Log logger = LogFactory.getLog(NomineeDtlsVOGEN.class);
	public ResultObject showNomineePageVOGEN (Map<String, Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{			
			logger.info("Called Vogen======showNomineePageVOGEN======================");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
//			 IFMS - Starts
			boolean blnWorkFlowEnabled = true;
			
			if (request.getParameter("workFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
			}
			
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;
			// IFMS - Ends
			
			String purposeValue = StringUtility.getParameter("purposeValue",request);			
			objectArgs.put("purposeValue",purposeValue);
			
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); // IFMS
			objectArgs.put("userId", iUserId); //IFMS
			
			objRes.setResultValue(objectArgs);
		}catch(Exception e)
		{
			objRes.setResultCode(ErrorConstants.ERROR);				
		}
		return objRes;
	}
	public ResultObject getFamilyMemberDtlsVOGEN(Map<String, Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String name = StringUtility.getParameter("name",request);			
			objectArgs.put("name",name);
			
//			 IFMS - Starts
			boolean blnWorkFlowEnabled = true;
			
			if (request.getParameter("workFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
			}
	
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;
			objectArgs.put("userId", iUserId); //IFMS
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); // IFMS
			objRes.setResultValue(objectArgs);
		}catch(Exception e)
		{
			objRes.setResultCode(ErrorConstants.ERROR);				
		}
		return objRes;
	}
	public ResultObject addOrEditNomineeDtlsVOGEN(Map<String, Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			logger.info("In addOrEditNomineeDtlsVOGEN======================");
			boolean blnWorkFlowEnabled = true;//IFMS
			if (StringUtility.getParameter("workFlowEnabled",request) != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
				logger.info("Value of blnWorkFlowEnabled in addOrEditNomineeVOGEN=============="+blnWorkFlowEnabled);
			}
			HrEisNomineeDtlTxn hrEisEmpNomiDtls=null;
			HrEisNomineeDtl hrEisEmpNomiDtlsMst=null; 
			//IFMS
			if(blnWorkFlowEnabled){
				hrEisEmpNomiDtls = new HrEisNomineeDtlTxn();
			}
			else 
			{
				hrEisEmpNomiDtlsMst =new HrEisNomineeDtl();  //IFMS
			}
				
		//	HrEisEmpNomineeDtlsTrn hrEisEmpNomiDtls = new HrEisEmpNomineeDtlsTrn();						
			String lStrValidatestr="";
			ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");			
			//Map loginMap = (Map) objectArgs.get("baseLoginMap");
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, servLoc.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl.readService("ADDRESS_VOGENERATOR");
			//long userId = Long.parseLong(loginMap.get("userId").toString());
			String family = StringUtility.getParameter("Family",request);
			logger.info("sadjasd==="+family);
			String minor = StringUtility.getParameter("minor",request);
			
			String nomn_name = StringUtility.getParameter("nomn_name",request);
			if(nomn_name == null || "".equalsIgnoreCase(nomn_name))
			{
				lStrValidatestr=lStrValidatestr+"eis.nominee_name,";
			}
			String nomi_otherRelation = StringUtility.getParameter("nomi_otherRelation",request);
			
			logger.info("nomi_otherRelation========="+nomi_otherRelation);
			
			String nomi_DOB = StringUtility.getParameter("nomi_DOB",request);
			if(nomi_DOB.equalsIgnoreCase("") || nomi_DOB.equalsIgnoreCase(null)){lStrValidatestr=lStrValidatestr+"eis.DATE_OF_BIRTH,";}
			else 
			{
				if(blnWorkFlowEnabled)
				{
					hrEisEmpNomiDtls.setNomnDob(StringUtility.convertStringToDate(nomi_DOB));
				}
				else 
				{
					hrEisEmpNomiDtlsMst.setNomnDob(StringUtility.convertStringToDate(nomi_DOB));//IFMS
				}
			}
			if("1".equals(minor))
			{
				String gur_first_name = StringUtility.getParameter("gur_first_name",request);
				if("".equals(gur_first_name)){lStrValidatestr=lStrValidatestr+"eis.GuardianFName,";}
				String gur_middle_name = StringUtility.getParameter("gur_middle_name",request);
				if("".equals(gur_middle_name)){}
				String gur_last_name =  StringUtility.getParameter("gur_last_name",request);
				if("".equals(gur_last_name)){lStrValidatestr=lStrValidatestr+"eis.GuardianLName,";}
				
				String gru_otherRelation =  StringUtility.getParameter("gur_otherRelation",request);
				
				String chkNullOrNot =StringUtility.getParameter("gur_Relation",request);
				String gur_Relation ="Select"; 			
				if(!"".equals(chkNullOrNot) && chkNullOrNot.equalsIgnoreCase("Select")==false){gur_Relation = chkNullOrNot.trim();}
				else{lStrValidatestr=lStrValidatestr+"eis.NomineeGuardianRel,";}
				CmnLookupMst gurRelation = new CmnLookupMst();			
				gurRelation.setLookupName(gur_Relation);                  // Gardian Relation
				
				if(blnWorkFlowEnabled){
					hrEisEmpNomiDtls.setCmnLookupMstByGuardianRelation(gurRelation);
					hrEisEmpNomiDtls.setGuardianRelationOther(gru_otherRelation);
					hrEisEmpNomiDtls.setGuardianFirstname(gur_first_name);
					hrEisEmpNomiDtls.setGuardianLastname(gur_last_name);
					hrEisEmpNomiDtls.setGuardianMiddlename(gur_middle_name);
					hrEisEmpNomiDtls.setNomnOtherRelation(nomi_otherRelation);
					}
					else{ 																//IFMS	
					hrEisEmpNomiDtlsMst.setCmnLookupMstByGuardianRelation(gurRelation);
					hrEisEmpNomiDtlsMst.setGuardianRelationOther(gru_otherRelation);
					hrEisEmpNomiDtlsMst.setGuardianFirstname(gur_first_name);
					hrEisEmpNomiDtlsMst.setGuardianLastname(gur_last_name);
					hrEisEmpNomiDtlsMst.setGuardianMiddlename(gur_middle_name);
					hrEisEmpNomiDtlsMst.setNomnOtherRelation(nomi_otherRelation);
					}
				
				servDetails = servDetailsImpl.readService("ADDRESS_VOGENERATOR");												
				objRes=servLoc.executeService(servDetails, objectArgs);
				String strAddressValidateGur = StringUtility.getParameter("rdoAddressGuardianAddress", request);
				if(objectArgs.containsKey("GuardianAddress"))  
				{				
					CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get("GuardianAddress");	
					if(blnWorkFlowEnabled)
					{
						hrEisEmpNomiDtls.setCmnAddressMstByGuardianAddress(cmnAddressMst);	
					}
					else 
					{
						hrEisEmpNomiDtlsMst.setCmnAddressMstByGuardianAddress(cmnAddressMst);//IFMS
					}
					
					if(cmnAddressMst.getCmnVillageMst()==null || cmnAddressMst.getCmnCityMst()==null || strAddressValidateGur.equalsIgnoreCase("OtherRadioAddress")==true)
					{
						if(cmnAddressMst.getCmnCityMst()==null && cmnAddressMst.getCmnVillageMst()==null && strAddressValidateGur.equalsIgnoreCase("OtherRadioAddress")==false)
						{
							
							lStrValidatestr=lStrValidatestr+"eis.GuardianAddress,";
							if(blnWorkFlowEnabled)
							{
								hrEisEmpNomiDtls.setCmnAddressMstByGuardianAddress(null);
							}
							else 
							{
								hrEisEmpNomiDtlsMst.setCmnAddressMstByGuardianAddress(null);//IFMS
							}
						}
						if(cmnAddressMst.getCmnCityMst()!=null)
						{
							if(cmnAddressMst.getCmnCityMst().getCityCode().equalsIgnoreCase("Select"))
							{
								lStrValidatestr=lStrValidatestr+"eis.GuardianAddress,";
								if(blnWorkFlowEnabled)
								{
									hrEisEmpNomiDtls.setCmnAddressMstByGuardianAddress(null);
								}
								else
								{
									hrEisEmpNomiDtlsMst.setCmnAddressMstByGuardianAddress(null);//IFMS
								}
							}
						}
						else if(cmnAddressMst.getCmnVillageMst()!=null) 
						{
							if(cmnAddressMst.getCmnVillageMst()!=null)
							{
								if(cmnAddressMst.getCmnVillageMst().getVillageCode().equalsIgnoreCase("Select"))
								{
									lStrValidatestr=lStrValidatestr+"eis.GuardianAddress,";
									if(blnWorkFlowEnabled)hrEisEmpNomiDtls.setCmnAddressMstByGuardianAddress(null);
									else hrEisEmpNomiDtlsMst.setCmnAddressMstByGuardianAddress(null);//IFMS
								}
							}
						}
						else
						{
							logger.info("");
						}
					}
				}
				else
				{
					lStrValidatestr=lStrValidatestr+"eis.GuardianAddress,";
				}
			}
			String benefitType =  StringUtility.getParameter("benefitType",request);
			long parentId = StringUtility.convertToLong(StringUtility.getParameter("parentId",request));			
			
			String shareStr=StringUtility.getParameter("nomi_share",request);//For Nominee SharePercentage
			double nomi_share =0; 			
			if(!"".equals(shareStr)){nomi_share=Double.valueOf(shareStr);}
			if(blnWorkFlowEnabled)
			{
				hrEisEmpNomiDtls.setNomnSharePercent(nomi_share);//IFMS
			}
			else
			{
				hrEisEmpNomiDtlsMst.setNomnSharePercent(nomi_share);
			}
						
			String chkNullOrNot ;
			String strAddressValidate = StringUtility.getParameter("rdoAddressNomineeAddress", request);
			objRes=servLoc.executeService(servDetails, objectArgs);
			if(objectArgs.containsKey("NomineeAddress"))  
			{				
				CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get("NomineeAddress");
				if(blnWorkFlowEnabled)
				{
					hrEisEmpNomiDtls.setCmnAddressMstByNomnAddress(cmnAddressMst);
				}
				else 
				{
					hrEisEmpNomiDtlsMst.setCmnAddressMstByNomnAddress(cmnAddressMst);	//IFMS
				}
					
				if(cmnAddressMst.getCmnVillageMst()==null || cmnAddressMst.getCmnCityMst()==null || strAddressValidate.equalsIgnoreCase("OtherRadioAddress")==true)
				{
					if(cmnAddressMst.getCmnCityMst()==null && cmnAddressMst.getCmnVillageMst()==null && strAddressValidate.equalsIgnoreCase("OtherRadioAddress")==false)
					{
						lStrValidatestr=lStrValidatestr+"eis.NomineeAddress,";
						if(blnWorkFlowEnabled)
						{
							hrEisEmpNomiDtls.setCmnAddressMstByNomnAddress(null);
						}
						else 
						{
							hrEisEmpNomiDtlsMst.setCmnAddressMstByNomnAddress(null);//IFMS
						}
					}
					if(cmnAddressMst.getCmnCityMst()!=null)
					{
						if(cmnAddressMst.getCmnCityMst().getCityCode().equalsIgnoreCase("Select"))
						{
							lStrValidatestr=lStrValidatestr+"eis.NomineeAddress,";
							if(blnWorkFlowEnabled)
							{
								hrEisEmpNomiDtls.setCmnAddressMstByNomnAddress(null);
							}
							else 
							{
								hrEisEmpNomiDtlsMst.setCmnAddressMstByNomnAddress(null);//IFMS
							}
						}
					}
					else if(cmnAddressMst.getCmnVillageMst()!=null)  
					{
						if(cmnAddressMst.getCmnVillageMst()!=null)
						if(cmnAddressMst.getCmnVillageMst().getVillageCode().equalsIgnoreCase("Select"))
						{
							lStrValidatestr=lStrValidatestr+"eis.NomineeAddress,";
							if(blnWorkFlowEnabled)
							{
								hrEisEmpNomiDtls.setCmnAddressMstByNomnAddress(null);
							}
							else 
							{
								hrEisEmpNomiDtlsMst.setCmnAddressMstByNomnAddress(null);//IFMS
							}
						}
					}	
					else
					{
						logger.info("");
					}
				}
				else
				{
					lStrValidatestr=lStrValidatestr+"eis.NomineeAddress,";
				}
			}
			
			chkNullOrNot = StringUtility.getParameter("nomi_Relation_id",request);
			String nomi_Relation ="Select"; 
			if("".equals(chkNullOrNot)==false && chkNullOrNot.equalsIgnoreCase("Select")==false){nomi_Relation=chkNullOrNot;}
			else{lStrValidatestr=lStrValidatestr+"eis.NomineeRelation,";}
			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			cmnLookupMst.setLookupName(nomi_Relation);                  // nomni relation
			
			if(blnWorkFlowEnabled)//IFMS
			{
				hrEisEmpNomiDtls.setCmnLookupMstByNomnRelation(cmnLookupMst);
				hrEisEmpNomiDtls.setNomnName(nomn_name);
			}
			else{																	//IFMS
				hrEisEmpNomiDtlsMst.setCmnLookupMstByNomnRelation(cmnLookupMst);
				hrEisEmpNomiDtlsMst.setNomnName(nomn_name);
				}
						
			long fmMemberId= Long.parseLong(family.trim());		
			if(fmMemberId!=0)
			{
				if(blnWorkFlowEnabled)
				{
					hrEisEmpNomiDtls.setFamilyMemberId(fmMemberId);		//IFMS
				}
				else 
				{
					hrEisEmpNomiDtlsMst.setFamilyMemberId(fmMemberId);
				}
			}
			if("1".equals(minor))
			{
				if(blnWorkFlowEnabled)
				{
					hrEisEmpNomiDtls.setNomnMinor("Y");
				}
				else {
					hrEisEmpNomiDtlsMst.setNomnMinor("Y");
				}
			}
			else 
			{
				if(blnWorkFlowEnabled)
				{
					hrEisEmpNomiDtls.setNomnMinor("N");
				}
				else 
				{
					hrEisEmpNomiDtlsMst.setNomnMinor("N");						//IFMS
				}
			}									
		
			String benefitTypeId = benefitType;
			CmnLookupMst cmnbenefitType = new CmnLookupMst();
			cmnbenefitType.setLookupName(benefitTypeId);                // benefit type id
			
			//IFMS		
			if(blnWorkFlowEnabled){
				
				if("".equals(lStrValidatestr)){hrEisEmpNomiDtls.setDraftFlag("N");}
				else {hrEisEmpNomiDtls.setDraftFlag("Y");}			
				hrEisEmpNomiDtls.setActionFlag("P");
				hrEisEmpNomiDtls.setCmnLookupMstByNomnBenefitTypeId(cmnbenefitType);
		
				hrEisEmpNomiDtls.setNomnSharePercent(nomi_share);						
		
				if(parentId==0 && "".equals(lStrValidatestr)){ hrEisEmpNomiDtls.setRequestFlag("A");}								
				else if(parentId!=0 && lStrValidatestr.equalsIgnoreCase("")==false){ hrEisEmpNomiDtls.setRequestFlag("N");}
				else if(parentId!=0){hrEisEmpNomiDtls.setRequestFlag("U");}
				if(parentId==0){logger.info("");}
				else{hrEisEmpNomiDtls.setRowNumber(parentId);}
				
				hrEisEmpNomiDtls.setNomnOtherRelation(nomi_otherRelation);
				
				String xmlFileIdStr = FileUtility.voToXmlFileByXStream(hrEisEmpNomiDtls);
				if(!"".equals(lStrValidatestr))
				{
					xmlFileIdStr=xmlFileIdStr+"$/$"+lStrValidatestr;
				}
				logger.info("Generating Nominee Vo to XML file :::::::: " + xmlFileIdStr);
				objectArgs.put("ajaxKey",xmlFileIdStr);	
			}
//IFMS		
		else{
			    hrEisEmpNomiDtlsMst.setCmnLookupMstByNomnBenefitTypeId(cmnbenefitType);
				hrEisEmpNomiDtlsMst.setMemberId(parentId);
				hrEisEmpNomiDtlsMst.setNomnOtherRelation(nomi_otherRelation);
				
				String xmlFileIdStr = FileUtility.voToXmlFileByXStream(hrEisEmpNomiDtlsMst);
				lStrValidatestr = "";
				if("".equals(lStrValidatestr)==false)
				{
					xmlFileIdStr=xmlFileIdStr+"$/$"+lStrValidatestr;
				}
				logger.info("=========WorkFlow-Enable Generating Nominee VotoXML file :::::::: " + xmlFileIdStr);
				objectArgs.put("ajaxKey",xmlFileIdStr);	
			}
		objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); // IFMS
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);			
		objRes.setViewName("ajaxData");	
		}catch(Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while adding a Nominee dtls  in VOGEN",e);	
		}
		return objRes;
	}
	public ResultObject submitNomineeDtlsVOGEN(Map<String, Object> objectArgs)   //   300355
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("Inside SubmitFamilyDtls  Vogen ::: ");
		try 
		{			
			/*HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String draft = StringUtility.getParameter("draft",request);			
			objectArgs.put("draft",draft);
			String deleteArr =StringUtility.getParameter("DeleteArr",request);				
			objectArgs.put("deleteArr", deleteArr);
			String[] txnXML = StringUtility.getParameterValues("encXML", request);								
			objectArgs.put("encXML", txnXML);
			objRes.setResultValue(objectArgs);*/
			
			//IFMS
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;
			objectArgs.put("userId", iUserId); //IFMS
		
			boolean blnWorkFlowEnabled = true;
			if (StringUtility.getParameter("workFlowEnabled",request) != null)//IFMS
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
				logger.info("Value of blnWorkFlowEnabled in addOrEditNomineeVOGEN=============="+blnWorkFlowEnabled);
			}		
			String[] txnXML = StringUtility.getParameterValues("encXML", request);								
			objectArgs.put("encXML", txnXML);
				
			String[] updateRecordXMLFileA = StringUtility.getParameterValues("addedPunch", request);
			Map vOMap =FileUtility.getUpdatedDeletedVOListFromXML(updateRecordXMLFileA);
		//	logger.info("===============updateRecordXMLFileA[0]=================="+updateRecordXMLFileA[0]);
			
			List updatedVOList = (List) vOMap.get("upDatedVOList");
			logger.info("=========updatedVOList=========="+updatedVOList);
			
			List deletedVOList = (List) vOMap.get("deletedVOList");
			logger.info("=========deletedVOList=========="+deletedVOList);
			
			List notModifiedVOList = (List) vOMap.get("notModifiedVOList");
			logger.info("=========notModifiedVOList=========="+notModifiedVOList);
			
			if(blnWorkFlowEnabled)//IFMS
			{
				logger.info("===================== in ==================================");
				String draft = StringUtility.getParameter("draft",request);			
				objectArgs.put("draft",draft);
				String deleteArr =StringUtility.getParameter("DeleteArr",request);				
				objectArgs.put("deleteArr", deleteArr);
			}
			objectArgs.put("upDatedVOList", updatedVOList);
			objectArgs.put("deletedVOList", deletedVOList);
			objectArgs.put("notModifiedVOList", notModifiedVOList);
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); // IFMS
			objRes.setResultValue(objectArgs);
		}catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Submitting a Nominee dtls  in VOGEN",e);
		}
		return objRes;
	}
	public ResultObject getReqIdToShowPendingDtlsData(Map<String, Object> objectArgs)   //   300355
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqId = StringUtility.getParameter("reqId",request);			
			objectArgs.put("reqId",reqId);		
			objRes.setResultValue(objectArgs);
		}catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Showing a Nominee Pending dtls  in VOGEN",e);
		}
		return objRes;		
	}
	
	public ResultObject getReqIdForDraftDtls(Map<String, Object> objectArgs)   //   300355
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqId = StringUtility.getParameter("reqId",request);
			String flag = StringUtility.getParameter("flag",request);
			objectArgs.put("flag",flag);	
			objectArgs.put("reqId",reqId);		
			objRes.setResultValue(objectArgs);
		}catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Showing a Nominee Pending dtls  in VOGEN",e);
		}
		return objRes;		
	}
}
