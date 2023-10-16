package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnCountryMstDAOImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.CmnTalukaMst;
import com.tcs.sgv.common.valueobject.CmnVillageMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl_WF;
import com.tcs.sgv.eis.dao.FamilyDtlsDAO;
import com.tcs.sgv.eis.dao.FamilyDtlsDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisBiometricDtl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmrgcycntcDtl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisLangPrfcncyDtl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpcontactMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.delegate.AddressDelegate;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;
import com.tcs.sgv.hod.common.valueobject.CmnContactMst;

public class EmpInfoService_WF extends ServiceImpl 
{
	private static final Log logger = LogFactory.getLog(EmpInfoService_WF.class);
	public final long ENG_LANG_ID = 1;
	public final long GU_LANG_ID = 2;
	
	public ResultObject insertEmpData(Map objectArgs) 
	{
		logger.info("=============Control in insertEmpData method of EmpInfoService_WF Service===========");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Date sysdate = new Date();
		try 
		{
			EmpInfoDAOImpl_WF empinfodao = new EmpInfoDAOImpl_WF(HrEisEmpMst.class,serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long eng_Empid=1;
			eng_Empid = empinfodao.getEngLishEmpId(userId);

			HrEisEmpMst updateHrEisEmpMst = (HrEisEmpMst) empinfodao.getHrEisEmpMstDtls(eng_Empid);
			HrEisEmpMst hrEisEmpMst = (HrEisEmpMst) objectArgs.get("empMst");
			if (updateHrEisEmpMst != null)
			{
				hrEisEmpMst.setEmpId(updateHrEisEmpMst.getEmpId());
			}
			
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			OrgEmpMst orgEmpMst = new OrgEmpMst();
			orgEmpMst = empinfodao.getOrgEmpMstVO(eng_Empid);
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			EmpInfoDAOImpl_WF empInfoDAOImpl = new EmpInfoDAOImpl_WF(HrEisLangPrfcncyDtl.class, serv.getSessionFactory());
			
			/** STARTS----------->For Update ,Deleted and Insertion Known Language In HrEisLangPrfcncyDtl *** */
			
			List upDatedVOList = (List) objectArgs.get("upDatedVOList");
			List deletedVOList = (List) objectArgs.get("deletedVOList");
			ListIterator liForUpdateLangVO = upDatedVOList.listIterator();
			
			HrEisLangPrfcncyDtl hrEisLanguageProficiency = null;
			
			if(liForUpdateLangVO!=null)
			{
				while(liForUpdateLangVO.hasNext()) 
				{
					hrEisLanguageProficiency = new HrEisLangPrfcncyDtl();
					hrEisLanguageProficiency = (HrEisLangPrfcncyDtl) liForUpdateLangVO.next();
					HrEisLangPrfcncyDtl updateHrEisLanguageProficiency = (HrEisLangPrfcncyDtl) empInfoDAOImpl.read(hrEisLanguageProficiency.getLangProfPkId());
					updateHrEisLanguageProficiency.setCmnLookupMst(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrEisLanguageProficiency.getCmnLookupMst().getLookupName(), langId));
					updateHrEisLanguageProficiency.setCmnLanguageMstByLanguageId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrEisLanguageProficiency.getCmnLanguageMstByLanguageId().getLookupName(),langId));
					updateHrEisLanguageProficiency.setOrgPostMstByUpdatedByPost(orgPostMst);
					updateHrEisLanguageProficiency.setOrgUserMstByUpdatedBy(orgUserMst);
					updateHrEisLanguageProficiency.setUpdatedDate(sysdate);
					empinfodao.update(updateHrEisLanguageProficiency);
				}
			}
			ListIterator liForDeletLangVO = deletedVOList.listIterator();
			if(liForDeletLangVO!=null)
			{
				while (liForDeletLangVO.hasNext()) 
				{
					hrEisLanguageProficiency = new HrEisLangPrfcncyDtl();
					hrEisLanguageProficiency = (HrEisLangPrfcncyDtl) liForDeletLangVO.next();
					HrEisLangPrfcncyDtl updateHrEisLanguageProficiency = (HrEisLangPrfcncyDtl) empInfoDAOImpl.read(hrEisLanguageProficiency.getLangProfPkId());
					empinfodao.delete(updateHrEisLanguageProficiency);
				}
			}
			
			String[] langXML = (String[]) objectArgs.get("langXML");
			List lstLangVO = FileUtility.xmlFilesToVoByXStream(langXML);
			for (Iterator i = lstLangVO.iterator(); i.hasNext();) 
			{
				hrEisLanguageProficiency = new HrEisLangPrfcncyDtl();
				hrEisLanguageProficiency = (HrEisLangPrfcncyDtl) i.next();
				hrEisLanguageProficiency.setCmnLanguageMstByLanguageId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrEisLanguageProficiency.getCmnLanguageMstByLanguageId().getLookupName(), langId));
				hrEisLanguageProficiency.setCmnLookupMst(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrEisLanguageProficiency.getCmnLookupMst().getLookupName(), langId));
				
				/** Starts of req Id code** */
				long langProfPkId = 0;
				langProfPkId = IDGenerateDelegate.getNextId("hr_eis_langprfcncy_dtl", objectArgs);
				/** end of req Id code** */
				
				hrEisLanguageProficiency.setLangProfPkId(langProfPkId);
				hrEisLanguageProficiency.setCmnDatabaseMst(cmnDatabaseMst);
				hrEisLanguageProficiency.setCmnLocationMst(cmnLocationMst);
				hrEisLanguageProficiency.setCreatedDate(sysdate);
				hrEisLanguageProficiency.setOrgPostMstByCreatedByPost(orgPostMst);
				hrEisLanguageProficiency.setOrgUserMstByCreatedBy(orgUserMst);
				hrEisLanguageProficiency.setOrgUserMstByUserId(orgUserMst);
				empinfodao.create(hrEisLanguageProficiency);
			}

			/** ENDS----------->For Update ,Deleted and Insertion Known Language In HrEisLangPrfcncyDtl *** */
			
			/** Starts----------->For Update ,Deleted and Insertion details In HrEisBiometricDtl *** */
			
			HrEisBiometricDtl hrPersonBiometricDtls = new HrEisBiometricDtl();
			EmpInfoDAOImpl_WF hrPersonBioDAO = new EmpInfoDAOImpl_WF(HrEisBiometricDtl.class, serv.getSessionFactory());
			HrEisBiometricDtl updateHrPersonBiometricDtls = new HrEisBiometricDtl();
			if (updateHrEisEmpMst != null && updateHrEisEmpMst.getHrPersonBiometricDtls() != null) 
			{
				updateHrPersonBiometricDtls = (HrEisBiometricDtl) hrPersonBioDAO.read(updateHrEisEmpMst.getHrPersonBiometricDtls().getBiometricId());
			} 
			else
			{
				updateHrPersonBiometricDtls = null;
			}

			hrPersonBiometricDtls = hrEisEmpMst.getHrPersonBiometricDtls();
			if (updateHrPersonBiometricDtls == null) 
			{
				hrPersonBiometricDtls.setCmnLookupMstByEmpBloodGroup(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrPersonBiometricDtls.getCmnLookupMstByEmpBloodGroup().getLookupName(), langId));
				if (hrPersonBiometricDtls.getEmpPhyChallenged().equals('Y') == true) 
				{
					hrPersonBiometricDtls.setCmnLookupMstByEmpTypeOfDisability(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrPersonBiometricDtls.getCmnLookupMstByEmpTypeOfDisability().getLookupName(), langId));
				}
				else 
				{
					hrPersonBiometricDtls.setCmnLookupMstByEmpTypeOfDisability(null);
					hrPersonBiometricDtls.setEmpDisabilityDetails("");
				}
				hrPersonBiometricDtls.setCreatedDate(sysdate);
				hrPersonBiometricDtls.setOrgPostMstByCreatedByPost(orgPostMst);
				hrPersonBiometricDtls.setOrgUserMstByCreatedBy(orgUserMst);
				hrPersonBiometricDtls.setCmnDatabaseMst(cmnDatabaseMst);
				hrPersonBiometricDtls.setCmnLocationMst(cmnLocationMst);
				long biometricId = 0;
				
				/** *Generateing a Req _ id for Address** */
				biometricId = IDGenerateDelegate.getNextId("hr_eis_biometric_dtl", objectArgs);
				logger.info("Pk For hr_eis_biometric_dtl========="+biometricId);
				/** **end of req Id code** */

				hrPersonBiometricDtls.setBiometricId(biometricId);
				empinfodao.create(hrPersonBiometricDtls);
				updateHrEisEmpMst.setHrPersonBiometricDtls(hrPersonBiometricDtls);
			} else 
			{
				hrPersonBiometricDtls.setCmnLookupMstByEmpBloodGroup(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrPersonBiometricDtls.getCmnLookupMstByEmpBloodGroup().getLookupName(), langId));
				if (hrPersonBiometricDtls.getEmpPhyChallenged().equals('Y') == true) 
				{
					updateHrPersonBiometricDtls.setCmnLookupMstByEmpTypeOfDisability(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrPersonBiometricDtls.getCmnLookupMstByEmpTypeOfDisability().getLookupName(), langId));
				}
				else 
				{
					updateHrPersonBiometricDtls.setCmnLookupMstByEmpTypeOfDisability(null);
					updateHrPersonBiometricDtls.setEmpDisabilityDetails("");
				}
				updateHrPersonBiometricDtls.setCmnLookupMstByEmpBloodGroup(hrPersonBiometricDtls.getCmnLookupMstByEmpBloodGroup());
				updateHrPersonBiometricDtls.setEmpChest(hrPersonBiometricDtls.getEmpChest());
				updateHrPersonBiometricDtls.setEmpWeight(hrPersonBiometricDtls.getEmpWeight());
				updateHrPersonBiometricDtls.setEmpHeight(hrPersonBiometricDtls.getEmpHeight());
				updateHrPersonBiometricDtls.setEmpDisabilityDetails(hrPersonBiometricDtls.getEmpDisabilityDetails());
				updateHrPersonBiometricDtls.setEmpIdentificationMark(hrPersonBiometricDtls.getEmpIdentificationMark());
				updateHrPersonBiometricDtls.setEmpPhyChallenged(hrPersonBiometricDtls.getEmpPhyChallenged());
				updateHrPersonBiometricDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
				updateHrPersonBiometricDtls.setUpdatedDate(sysdate);
				updateHrPersonBiometricDtls.setOrgUserMstByUpdatedBy(orgUserMst);
				empinfodao.update(updateHrPersonBiometricDtls);
				updateHrEisEmpMst.setHrPersonBiometricDtls(updateHrPersonBiometricDtls);
			}
			/** Ends----------->For Update ,Deleted and Insertion details In HrEisBiometricDtl *** */
			

			/** Starts----------->For Update ,Deleted and Insertion Person Emergency Contact Details *** */
			
			CmnContactMst updateCmnContactMst = null;
			HrEisEmrgcycntcDtl hrEisEmpEmergencyContact = new HrEisEmrgcycntcDtl();
			hrEisEmpEmergencyContact.setOrgUserMstByUserId(orgUserMst);
			HrEisEmrgcycntcDtl updateHrEisEmpEmergencyContact = (HrEisEmrgcycntcDtl) empinfodao.getHrEisEmpEmergencyContactData(userId);
			hrEisEmpEmergencyContact = (HrEisEmrgcycntcDtl) objectArgs.get("EmergencyVO");
			
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
			
			if (updateHrEisEmpEmergencyContact == null)
			{
				hrEisEmpEmergencyContact.setUserId(userId);
			}
			else 
			{
				EmpInfoDAOImpl_WF hrPersonEmergencyDAO = new EmpInfoDAOImpl_WF(CmnContactMst.class, serv.getSessionFactory());
				updateCmnContactMst = (CmnContactMst) hrPersonEmergencyDAO.read(updateHrEisEmpEmergencyContact.getCmnContactMst().getSrNo());
			}
			
			/** Inserting Cmn Contact Mst* */
			CmnContactMst cmnContactMst = hrEisEmpEmergencyContact.getCmnContactMst();
			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			
			cmnLookupMst = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang("Select", langId);
			cmnContactMst.setCmnLookupMst(cmnLookupMst);

			if (updateHrEisEmpEmergencyContact != null && updateCmnContactMst != null && updateCmnContactMst.getSrNo() != 0) 
			{
				updateCmnContactMst.setEmail(cmnContactMst.getEmail());
				updateCmnContactMst.setFax(cmnContactMst.getFax());
				updateCmnContactMst.setMobile(cmnContactMst.getMobile());
				updateCmnContactMst.setOfficePhone(cmnContactMst.getOfficePhone());
				updateCmnContactMst.setResidencePhone(cmnContactMst.getResidencePhone());
				updateHrEisEmpEmergencyContact.setCmnContactMst(updateCmnContactMst);
				hrEisEmpEmergencyContact.setCmnContactMst(updateCmnContactMst);
				updateCmnContactMst.setCmnDatabaseMst(cmnDatabaseMst);
				updateCmnContactMst.setCmnLanguageMst(cmnLanguageMst);
				updateCmnContactMst.setCmnLocationMst(cmnLocationMst);
				updateCmnContactMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				updateCmnContactMst.setUpdatedBy(userId);
				updateCmnContactMst.setUpdatedDate(sysdate);
				empinfodao.update(updateCmnContactMst);
			} 
			else if (updateCmnContactMst == null) 
			{
				long cmnContactMstSrNo = 0;
				cmnContactMstSrNo = IDGenerateDelegate.getNextId("cmn_contact_mst", objectArgs);
				logger.info("PK For cmn_contact_mst for person Contact Dtls======="+cmnContactMstSrNo);
				
				cmnContactMst.setCmnDatabaseMst(cmnDatabaseMst);
				cmnContactMst.setCmnLanguageMst(cmnLanguageMst);
				cmnContactMst.setCmnLocationMst(cmnLocationMst);
				cmnContactMst.setOrgPostMstByCreatedByPost(orgPostMst);
				cmnContactMst.setCreatedBy(userId);
				cmnContactMst.setCreatedDate(sysdate);
				cmnContactMst.setSrNo(cmnContactMstSrNo);
				empinfodao.create(cmnContactMst);
				if (updateHrEisEmpEmergencyContact != null)
				{
					updateHrEisEmpEmergencyContact.setCmnContactMst(cmnContactMst);
				}
				hrEisEmpEmergencyContact.setCmnContactMst(cmnContactMst);
			}
			/** End Of Creating Cmn Contact Mst* */
			
			if (objectArgs.containsKey("familyMemberName") && objectArgs.get("familyMemberName") != null) 
			{
				String lStrFamilyMemberFirstName = (String) objectArgs.get("familyMemberName");
				if(lStrFamilyMemberFirstName.equalsIgnoreCase("Select")==false && lStrFamilyMemberFirstName.equalsIgnoreCase("")==false)
				{
					long familyMemberID = Long.valueOf(lStrFamilyMemberFirstName.trim());
					if(updateHrEisEmpEmergencyContact!=null)
					{
						updateHrEisEmpEmergencyContact.setFamilyId(familyMemberID);
						updateHrEisEmpEmergencyContact.setCmnAddressMst(null);
					}
					hrEisEmpEmergencyContact.setFamilyId(familyMemberID);
					hrEisEmpEmergencyContact.setCmnAddressMst(null);
					if (updateHrEisEmpEmergencyContact == null) 
					{
						hrEisEmpEmergencyContact.setCmnLookupMst(null);
						hrEisEmpEmergencyContact.setContactFirstName(null);
						hrEisEmpEmergencyContact.setContactLastName(null);
						hrEisEmpEmergencyContact.setContactMiddleName(null);
						hrEisEmpEmergencyContact.setContactType('F');
						hrEisEmpEmergencyContact.setOtherRelation(null);
	
						hrEisEmpEmergencyContact.setCmnDatabaseMst(cmnDatabaseMst);
						hrEisEmpEmergencyContact.setCmnLocationMst(cmnLocationMst);
						hrEisEmpEmergencyContact.setOrgPostMstByCreatedByPost(orgPostMst);
						hrEisEmpEmergencyContact.setOrgUserMstByCreatedBy(orgUserMst);
						hrEisEmpEmergencyContact.setOrgUserMstByUserId(orgUserMst);
						hrEisEmpEmergencyContact.setCreatedDate(new java.util.Date());
	
						empinfodao.create(hrEisEmpEmergencyContact);
					} 
					else 
					{
						updateHrEisEmpEmergencyContact.setCmnLookupMst(null);
						updateHrEisEmpEmergencyContact.setContactFirstName(null);
						updateHrEisEmpEmergencyContact.setContactLastName(null);
						updateHrEisEmpEmergencyContact.setContactMiddleName(null);
						updateHrEisEmpEmergencyContact.setContactType('F');
						updateHrEisEmpEmergencyContact.setOtherRelation(null);
	
						updateHrEisEmpEmergencyContact.setCmnDatabaseMst(cmnDatabaseMst);
						updateHrEisEmpEmergencyContact.setCmnLocationMst(cmnLocationMst);
						updateHrEisEmpEmergencyContact.setUpdatedDate(new java.util.Date());
						updateHrEisEmpEmergencyContact.setOrgPostMstByUpdatedByPost(orgPostMst);
						updateHrEisEmpEmergencyContact.setOrgUserMstByUpdatedBy(orgUserMst);
						updateHrEisEmpEmergencyContact.setOrgUserMstByUserId(orgUserMst);
						empinfodao.update(updateHrEisEmpEmergencyContact);
					}
				}
			}
			else 
			{
				hrEisEmpEmergencyContact.setCmnLookupMst(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang("fm_rel_Other", langId));
				
				if (updateHrEisEmpEmergencyContact != null && updateHrEisEmpEmergencyContact.getCmnAddressMst() != null) 
				{
					objectArgs.put("addressId", updateHrEisEmpEmergencyContact.getCmnAddressMst().getAddressId());
					objectArgs.put("addrName", "otherEmergencyAddress");
					ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");
					servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, servLoc.getSessionFactory());
					FrmServiceMst servDetails = servDetailsImpl.readService("EDIT_ADDRESS_VOGENERATOR");

					resultObject = servLoc.executeService(servDetails,objectArgs);
					servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, servLoc.getSessionFactory());
					servDetails = servDetailsImpl.readService("EDIT_ADDRESS_DETAILS");
					resultObject = servLoc.executeService(servDetails,objectArgs);

					if (objectArgs.containsKey("otherEmergencyAddress")) 
					{
						CmnAddressMst cmnAddressMst = (CmnAddressMst) objectArgs.get("otherEmergencyAddress");
						updateHrEisEmpEmergencyContact.setCmnAddressMst(cmnAddressMst);
					} 
					else 
					{
						logger.info("Other Emergency Address  Not Found"+ userId);
					}
				}
				else 
				{
					FrmServiceMst servDetails = servDetailsImpl.readService("ADDRESS_VOGENERATOR");
					resultObject=serv.executeService(servDetails, objectArgs);	
		    		
					if(objectArgs.containsKey("otherEmergencyAddress"))
					{							
						hrEisEmpEmergencyContact.setCmnAddressMst((CmnAddressMst)objectArgs.get("otherEmergencyAddress"));
					}
					
					if (hrEisEmpEmergencyContact.getCmnAddressMst() != null) 
					{
						long addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
						logger.info("addressId============"+addressId);
						
						objectArgs.put("mode", "ADD");
						CmnAddressMst cm = hrEisEmpEmergencyContact.getCmnAddressMst();
						cm.setAddressId(addressId);
						cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
						cm.setAddressId(addressId);
						empinfodao.create(cm);
						if(updateHrEisEmpEmergencyContact!=null)
						{
							updateHrEisEmpEmergencyContact.setCmnAddressMst(cm);							
						}
						
						hrEisEmpEmergencyContact.setCmnAddressMst(cm);
					}
				}
				hrEisEmpEmergencyContact.setCmnLookupMst(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrEisEmpEmergencyContact.getCmnLookupMst().getLookupName(), langId));
				if (updateHrEisEmpEmergencyContact == null) 
				{
					hrEisEmpEmergencyContact.setUserId(userId);
					hrEisEmpEmergencyContact.setCmnDatabaseMst(cmnDatabaseMst);
					hrEisEmpEmergencyContact.setCmnLocationMst(cmnLocationMst);
					hrEisEmpEmergencyContact.setFamilyId(0);
					hrEisEmpEmergencyContact.setOrgPostMstByCreatedByPost(orgPostMst);
					hrEisEmpEmergencyContact.setOrgUserMstByCreatedBy(orgUserMst);
					hrEisEmpEmergencyContact.setOrgUserMstByUserId(orgUserMst);
					hrEisEmpEmergencyContact.setCreatedDate(new java.util.Date());
					empinfodao.create(hrEisEmpEmergencyContact);
				} 
				else 
				{
					updateHrEisEmpEmergencyContact.setCmnLookupMst(hrEisEmpEmergencyContact.getCmnLookupMst());
					updateHrEisEmpEmergencyContact.setFamilyId(0);
					updateHrEisEmpEmergencyContact.setContactFirstName(hrEisEmpEmergencyContact.getContactFirstName());
					updateHrEisEmpEmergencyContact.setContactLastName(hrEisEmpEmergencyContact.getContactLastName());
					updateHrEisEmpEmergencyContact.setContactMiddleName(hrEisEmpEmergencyContact.getContactMiddleName());
					updateHrEisEmpEmergencyContact.setContactType(hrEisEmpEmergencyContact.getContactType());
					updateHrEisEmpEmergencyContact.setOtherRelation(hrEisEmpEmergencyContact.getOtherRelation());
					updateHrEisEmpEmergencyContact.setUserId(userId);

					updateHrEisEmpEmergencyContact.setCmnDatabaseMst(cmnDatabaseMst);
					updateHrEisEmpEmergencyContact.setCmnLocationMst(cmnLocationMst);
					updateHrEisEmpEmergencyContact.setUpdatedDate(new java.util.Date());
					updateHrEisEmpEmergencyContact.setOrgPostMstByUpdatedByPost(orgPostMst);
					updateHrEisEmpEmergencyContact.setOrgUserMstByUpdatedBy(orgUserMst);
					updateHrEisEmpEmergencyContact.setOrgUserMstByUserId(orgUserMst);
					empinfodao.update(updateHrEisEmpEmergencyContact);
				}
			}
			/** ENDS----------->For Update ,Deleted and Insertion Person Emergency Contact Details *** */

			updateHrEisEmpMst.setOrgEmpMst(orgEmpMst);
			updateHrEisEmpMst.setCmnDatabaseMst(cmnDatabaseMst);
			updateHrEisEmpMst.setCmnLocationMst(cmnLocationMst);

			updateHrEisEmpMst.setOrgPostMst(orgPostMst);
			updateHrEisEmpMst.setOrgUserMstByUpdatedBy(orgUserMst);
			updateHrEisEmpMst.setUpdatedDate(sysdate);
			
			empinfodao.update(updateHrEisEmpMst);
			
			objectArgs.put("draft", "A");//for viewing submit page Message
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("SubmitMessagePage");
		} 
		catch (Exception e) 
		{
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setThrowable(e);
			resultObject.setResultValue(objectArgs);
			logger.error("Error While Updating person info in insertEmpData ServiceImpl", e);
		}
		return resultObject;
	}

	public ResultObject getComboDtls(Map<String, Object> objectArgs) 
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			List<CmnLookupMst> empRelation = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("fm_Relation", langId);
			List<CmnLookupMst> empBG = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("emp_BG", langId);
			List<CmnLookupMst> proficiencyList = (List<CmnLookupMst>) cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("empinfo_Proficiency",langId);
			List<CmnLookupMst> disabilityList = (List<CmnLookupMst>) cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Type_Of_Disability",langId);
			List<CmnLookupMst> langList= cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("language_list",langId);
			
			/* Start--->Getting a Alive Family Member */
			List familyName = new ArrayList();
			FamilyDtlsDAO fmDtlsDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class, serv.getSessionFactory());
			familyName = fmDtlsDAOImpl.getAliveEmpFamilyDtls(orgUserMst);
			logger.info("list of Alive family member===============Size==="+familyName.size());
			/* End--->Getting a Alive Family Member*/
			
			objectArgs.put("langList", langList);
			objectArgs.put("familyName", familyName);
			objectArgs.put("empBG", empBG);
			objectArgs.put("empRelation", empRelation);
			objectArgs.put("proficiencyList", proficiencyList);
			objectArgs.put("disabilityList", disabilityList);
		} catch (Exception e) 
		{
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setThrowable(e);
			resultObject.setResultValue(objectArgs);
			logger.error("Error While getting Combo Dtls form getComboDtls method in EmpInfoService_WF", e);
		}
		return resultObject;
	}

	public ResultObject getEmpData(Map<String, Object> objectArgs) 
	{
		logger.info("IN Emp Master Data getEmpData method");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		long eng_Empid = 1;
		try 
		{
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			EmpInfoDAOImpl_WF empinfodao = new EmpInfoDAOImpl_WF(HrEisEmpMst.class, serv.getSessionFactory());
			
			
			eng_Empid = empinfodao.getEngLishEmpId(userId); // getting English EmpId
			logger.info("I m in edit mode For Eng_ID: " + eng_Empid);
			
			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			List actionList = empinfodao.getEmpIdData(eng_Empid, cmnLanguageMst); // Contains HrEisEmpMst object according to English language.
			if(actionList!=null && !actionList.isEmpty())
			{
				hrEisEmpMst = (HrEisEmpMst) actionList.get(0);
			}
			
			CmnCountryMstDAOImpl cmnCountryMstDAOImpl = new CmnCountryMstDAOImpl(CmnCountryMst.class,serv.getSessionFactory());
			if(hrEisEmpMst!=null)
			{
				if(hrEisEmpMst.getCmnCountryMstByEmpNationality()!=null &&!hrEisEmpMst.getCmnCountryMstByEmpNationality().getCountryCode().equals(""))
				{
						hrEisEmpMst.setCmnCountryMstByEmpNationality(cmnCountryMstDAOImpl.getCountryVOByCountryCodeAndLangId(hrEisEmpMst.getCmnCountryMstByEmpNationality().getCountryCode(), langId));
				}
			}
			
			/**Starts------->Getting OrgEmpMst Object: Language Specific*/
			
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			List orgEmpMstList= empinfodao.getOrgEmpMstVOList(userId,langId); 
			if(orgEmpMstList!=null && !orgEmpMstList.isEmpty())
			{
				orgEmpMst = (OrgEmpMst) orgEmpMstList.get(0);
				objectArgs.put("orgEmpMstObj", orgEmpMst); // OrgEmpMst : Language Specific
			}
			else
			{
				objectArgs.put("orgEmpMstObj", orgEmpMst);	// OrgEmpMst : Language Specific
			}
			
			/**Ends------->Getting OrgEmpMst Object: Language Specific*/
			
			/** Stats--------->Getting Language Proficiancy Dtls*/
			List xmlFile = new ArrayList();
			List lArrObj = new ArrayList();
			lArrObj = empinfodao.getAllEmpKnownLanguage(userId); 
			ListIterator litr = lArrObj.listIterator();
			while (litr.hasNext()) 
			{
				HrEisLangPrfcncyDtl eisLanguageProficiency = (HrEisLangPrfcncyDtl) litr.next();
				String strLanguageLookupName = eisLanguageProficiency.getCmnLanguageMstByLanguageId() != null ? eisLanguageProficiency.getCmnLanguageMstByLanguageId().getLookupName() : "";
				if(!"".equals(strLanguageLookupName))
				{
					eisLanguageProficiency.setCmnLanguageMstByLanguageId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLanguageLookupName, langId));
				}
				
				String strLanguageProficiencyLookupName = eisLanguageProficiency.getCmnLookupMst() != null ? eisLanguageProficiency.getCmnLookupMst().getLookupName() : "";
				if(!"".equals(strLanguageProficiencyLookupName))
				{
					eisLanguageProficiency.setCmnLookupMst(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLanguageProficiencyLookupName, langId));
				}
				
				xmlFile.add(FileUtility.voToXmlFileByXStream(eisLanguageProficiency));
			}
			
			if(hrEisEmpMst!=null)
			{
				if(hrEisEmpMst.getCmnLanguageMst()!=null &&!hrEisEmpMst.getCmnLanguageMst().getLookupName().equals(""))
				{
						hrEisEmpMst.setCmnLanguageMst(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(hrEisEmpMst.getCmnLanguageMst().getLookupName(), langId));
				}
			}
			
			objectArgs.put("xmlFile", xmlFile);
			objectArgs.put("lArrObj", lArrObj);
			objectArgs.put("editList", hrEisEmpMst);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			/**Start--------------> Getting a Person Emergency Details * */
			EmpInfoDAOImpl_WF getPersonEmergencyDtls = new EmpInfoDAOImpl_WF(HrEisEmrgcycntcDtl.class, serv.getSessionFactory());
			HrEisEmrgcycntcDtl hrEisEmpEmergencyContact = new HrEisEmrgcycntcDtl();
			hrEisEmpEmergencyContact = (HrEisEmrgcycntcDtl) getPersonEmergencyDtls.read(userId);
			
			/**Start---------> For Other Family Member Address*/
			if(hrEisEmpEmergencyContact != null && hrEisEmpEmergencyContact.getCmnAddressMst()!=null)
			{
				CmnAddressMst objCmnAddressMst =  hrEisEmpEmergencyContact.getCmnAddressMst();
				if(objCmnAddressMst.getAddressId()!=0)
				{
					if (objCmnAddressMst.getCmnStateMst() != null)
					{
						objCmnAddressMst.getCmnStateMst().getStateCode();
					}
					
					if (objCmnAddressMst.getCmnCountryMst() != null)
					{
						objCmnAddressMst.getCmnCountryMst().getCountryCode();
					}
					
					objCmnAddressMst.getArea();
					objCmnAddressMst.getCmnLookupMstAddTypeLookupid().getLookupName();
					objCmnAddressMst.getMasterLookupid();
					
					if (objCmnAddressMst.getCmnLookupMstAddTypeLookupid().getLookupName().toString().equalsIgnoreCase("City"))
					{
						if (objCmnAddressMst.getCmnCityMst() != null)
						{
							objCmnAddressMst.getCmnCityMst().getCityCode();
						}
					}
					else
					{
						if(objCmnAddressMst.getCmnVillageMst()!=null)
						{
							objCmnAddressMst.getCmnVillageMst().getVillageCode();
						}
						
						if(objCmnAddressMst.getCmnDistrictMst()!=null)
						{
							objCmnAddressMst.getCmnDistrictMst().getDistrictCode();
						}
						
						if(objCmnAddressMst.getCmnTalukaMst()!=null)
						{
							objCmnAddressMst.getCmnTalukaMst().getTalukaCode();
						}
					}
					
					if(objCmnAddressMst.getImpLandmark()!= null)
					{
						objCmnAddressMst.getImpLandmark().getLandmarkCode();
					}
					
					objCmnAddressMst.getFaliyu();
					objCmnAddressMst.getHouseName();
					objCmnAddressMst.getImpLandmark();
					objCmnAddressMst.getOtherDetails();
					objCmnAddressMst.getPincode();
					objCmnAddressMst.getSocBuildName();
					objCmnAddressMst.getStreet();	
				}
			}
			/**Ends--------------> For Other Family Member Address*/
			
			if (hrEisEmpEmergencyContact != null) 
			{
				CmnContactMst cmnContactMst = hrEisEmpEmergencyContact.getCmnContactMst();
				if (cmnContactMst != null) 
				{
					EmpInfoDAOImpl_WF getPersonContactDtls = new EmpInfoDAOImpl_WF(CmnContactMst.class, serv.getSessionFactory());
					cmnContactMst = (CmnContactMst) getPersonContactDtls.read(hrEisEmpEmergencyContact.getCmnContactMst().getSrNo());
					hrEisEmpEmergencyContact.setCmnContactMst(cmnContactMst);
				}
				objectArgs.put("hrEisEmpEmergencyContact",hrEisEmpEmergencyContact);
				
				String strRelationOther = hrEisEmpEmergencyContact.getCmnLookupMst() != null ? hrEisEmpEmergencyContact.getCmnLookupMst().getLookupName() : "";
				if(!"".equals(strRelationOther))
				{
					hrEisEmpEmergencyContact.setCmnLookupMst(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strRelationOther, langId));
				}

				String OtherAddressXmlFile = FileUtility.voToXmlFileByXStream(hrEisEmpEmergencyContact);
				logger.info("OtherAddressXmlFile========"+OtherAddressXmlFile);
				objectArgs.put("OtherAddressXmlFile", OtherAddressXmlFile);
				
				if(hrEisEmpEmergencyContact.getFamilyId()!=0)
				{
					objectArgs.put("cmbId",hrEisEmpEmergencyContact.getFamilyId());
					objectArgs.put("family","family");
					resultObject = getEmpNextCmbDtls(objectArgs);					
					objectArgs.put("fmId",hrEisEmpEmergencyContact.getFamilyId());
				}
			}
			/**Ends--------------> Getting a Person Emergency Details * */
			
			CmnLookupMst objCmnLookupMst=cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang("fm_rel_Other", langId);
			String strOtherLookupDesc=objCmnLookupMst.getLookupDesc();
			objectArgs.put("strOtherLookupDesc",strOtherLookupDesc);
			
			/** Set Address Data */
			
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
			objectArgs.put("addrName", "birthPlaceAddress");
			objectArgs.put("addressObject", hrEisEmpMst.getCmnAddressMstByEmpBirthPlaceAddressId());
			resultObject = serv.executeService(servDetails, objectArgs);
			
			objectArgs.put("addrName", "nativePlaceAddress");
			objectArgs.put("addressObject", hrEisEmpMst.getCmnAddressMstByEmpNativePlaceAddressId());
			resultObject = serv.executeService(servDetails, objectArgs);
	
			objectArgs.put("addrName", "personPermanentAddress");
			objectArgs.put("addressObject", hrEisEmpMst.getCmnAddressMstByEmpPermanentAddressId());
			resultObject = serv.executeService(servDetails, objectArgs);
			
			objectArgs.put("addrName", "personCurrentAddress");
			objectArgs.put("addressObject", hrEisEmpMst.getCmnAddressMstByEmpCurrentAddressId());
			resultObject = serv.executeService(servDetails, objectArgs);
			
			resultObject = getComboDtls(objectArgs);//Calling getComboDtls for getting combo dtls
			
		
			CmnLookupMst cmnLookupMst = null;
			CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			
			if (hrEisEmpMst.getCmnLookupMstByEmpReligionId() != null)
			{
				cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(hrEisEmpMst.getCmnLookupMstByEmpReligionId().getLookupName(), langId);
				
				if (cmnLookupMst != null)
				{
					hrEisEmpMst.setCmnLookupMstByEmpReligionId(cmnLookupMst);
				}
			}
			
			if (hrEisEmpMst.getCmnLookupMstByEmpCasteId() != null)
			{
				cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(hrEisEmpMst.getCmnLookupMstByEmpCasteId().getLookupName(), langId);
				
				if (cmnLookupMst != null)
				{
					hrEisEmpMst.setCmnLookupMstByEmpCasteId(cmnLookupMst);
				}
			}
			   
		    if (hrEisEmpMst.getCmnLookupMstByEmpSubCasteId() != null)
		    {
		    	cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(hrEisEmpMst.getCmnLookupMstByEmpSubCasteId().getLookupName(), langId);
		    	
		    	if (cmnLookupMst != null)
		    	{
		    		hrEisEmpMst.setCmnLookupMstByEmpSubCasteId(cmnLookupMst);
		    	}
		    }
		   
		    if (hrEisEmpMst.getCmnLookupMstByEmpCategoryId() != null)
		    {
		    	cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(hrEisEmpMst.getCmnLookupMstByEmpCategoryId().getLookupName(), langId);
		    		
		    	if (cmnLookupMst != null)	
		    	{
		    		hrEisEmpMst.setCmnLookupMstByEmpCategoryId(cmnLookupMst);
		    	}
		    }
		   
		    if (hrEisEmpMst.getCmnLookupMstByEmpMaritalStatusId() != null)
		    {
		    	cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(hrEisEmpMst.getCmnLookupMstByEmpMaritalStatusId().getLookupName(), langId);
		    	
		    	if (cmnLookupMst != null)
		    	{
		    		hrEisEmpMst.setCmnLookupMstByEmpMaritalStatusId(cmnLookupMst);
		    	}
		    }
			
			
			/**Statrt-----------> Getting a Physical Dtls From HrEisBiometricDtl Table */
			if (hrEisEmpMst.getHrPersonBiometricDtls() != null) 
			{
				HrEisBiometricDtl hrPersonBiometricDtls = new HrEisBiometricDtl();
				EmpInfoDAOImpl_WF getPersonBioDtls = new EmpInfoDAOImpl_WF(HrEisBiometricDtl.class, serv.getSessionFactory());
				hrPersonBiometricDtls = (HrEisBiometricDtl) getPersonBioDtls.read(hrEisEmpMst.getHrPersonBiometricDtls().getBiometricId());
				hrEisEmpMst.setHrPersonBiometricDtls(hrPersonBiometricDtls);				
				
				CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
				
				/*if (hrPersonBiometricDtls.getCmnAttachmentMstByEmpPhotoAttachmentId() != null) 
				{
					CmnAttachmentMst thumbCmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(hrPersonBiometricDtls.getCmnAttachmentMstByEmpPhotoAttachmentId().getAttachmentId());
					
					if (thumbCmnAttachmentMst != null && thumbCmnAttachmentMst.getAttachmentId() != 0)
					{
						objectArgs.put("thumbAttachment", thumbCmnAttachmentMst);
						srNo  = empinfodao.getAttachmentSrNo(thumbCmnAttachmentMst.getAttachmentId(),BiometricsUtils.LEFT_THUMB_SLAP);
						logger.info("srNo==============thumb"+srNo);
						if(srNo!=0)
						{
							objectArgs.put("attchId1",thumbCmnAttachmentMst.getAttachmentId());
							objectArgs.put("mpgSrNo1",srNo);
						}
					}
					else
						objectArgs.put("attchId1","");
				}*/
				
				if (hrPersonBiometricDtls.getCmnAttachmentMstByEmpPhotoAttachmentId() != null) 
				{
					CmnAttachmentMst photoCmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(hrPersonBiometricDtls.getCmnAttachmentMstByEmpPhotoAttachmentId().getAttachmentId());
					
					if (photoCmnAttachmentMst != null && photoCmnAttachmentMst.getAttachmentId() != 0)
					{
						long photoAttachmentMpgSrNo  = empinfodao.getAttachmentSrNo(photoCmnAttachmentMst.getAttachmentId(),true);
						logger.info("sphotoAttachmentMpgSrNo============"+photoAttachmentMpgSrNo);
						if(photoAttachmentMpgSrNo!=0)
						{
							objectArgs.put("photoAttachment", photoCmnAttachmentMst);
							objectArgs.put("attchId",photoCmnAttachmentMst.getAttachmentId());										
							objectArgs.put("mpgSrNo",photoAttachmentMpgSrNo);
						}
						
						long thumbAttachmentMpgSrNo  = empinfodao.getAttachmentSrNo(photoCmnAttachmentMst.getAttachmentId(),false); //BiometricsUtils.LEFT_THUMB_SLAP
						logger.info("thumbAttachmentMpgSrNo========="+thumbAttachmentMpgSrNo);
						if(thumbAttachmentMpgSrNo!=0)
						{
							objectArgs.put("thumbAttachment", photoCmnAttachmentMst);
							objectArgs.put("attchId1",photoCmnAttachmentMst.getAttachmentId());
							objectArgs.put("mpgSrNo1",thumbAttachmentMpgSrNo);
						}
					}
					else
					{
						objectArgs.put("attchId","");
						objectArgs.put("attchId1","");
					}
				}
			}			
			/**Ends-----------> Getting a Physical Dtls From HrEisBiometricDtl Table */
			
			
			/** Starts--------------->getting Org EmpContact Mst Dtls** */
			String lStrPhoneNo = "";
			String lStrMobileNo = "";
			String lStrEmail = "";

			EmpInfoDAOImpl_WF orgEmpContactMstDao = new EmpInfoDAOImpl_WF(OrgEmpcontactMst.class, serv.getSessionFactory());
			CmnLookupMstDAOImpl cmnLookupMstDAOImplForContactMst = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

			CmnLookupMst mobileCmnLookupMst = new CmnLookupMst();
			mobileCmnLookupMst = cmnLookupMstDAOImplForContactMst.getLookUpVOByLookUpNameAndLang("emp_Contact_Mobile", langId);
			OrgEmpcontactMst mobileOrgEmpcontactMst = orgEmpContactMstDao.getOrgEmpContactMstDtls(eng_Empid, mobileCmnLookupMst);
			if (mobileOrgEmpcontactMst != null) 
			{
				lStrMobileNo = mobileOrgEmpcontactMst.getContactNumber();
			}

			CmnLookupMst phoneCmnLookupMst = new CmnLookupMst();
			phoneCmnLookupMst = cmnLookupMstDAOImplForContactMst.getLookUpVOByLookUpNameAndLang("emp_Contact_Phone", langId);
			OrgEmpcontactMst phoneOrgEmpcontactMst = orgEmpContactMstDao.getOrgEmpContactMstDtls(eng_Empid, phoneCmnLookupMst);
			if(phoneOrgEmpcontactMst != null)
			{
				lStrPhoneNo = phoneOrgEmpcontactMst.getContactNumber();
			}

			CmnLookupMst emilCmnLookupMst = new CmnLookupMst();
			emilCmnLookupMst = cmnLookupMstDAOImplForContactMst.getLookUpVOByLookUpNameAndLang("emp_Contact_Email", langId);
			OrgEmpcontactMst emailOrgEmpcontactMst = orgEmpContactMstDao.getOrgEmpContactMstDtls(eng_Empid, emilCmnLookupMst);
			if (emailOrgEmpcontactMst != null) 
			{
				lStrEmail = emailOrgEmpcontactMst.getContactNumber();
			}

			objectArgs.put("phone", lStrPhoneNo);
			objectArgs.put("mobile", lStrMobileNo);
			objectArgs.put("email", lStrEmail);
			
			/** Ends--------------->getting Org EmpContact Mst Dtls** */
			
			/** Start------>Check for employee Eixist OR not*/
			OrgEmpMst empMst = empinfodao.getEmployee(orgUserMst, 1l);
			if(empMst!=null)
			{
				List hrEisEmpMstLst = empinfodao.getEmpIdData(empMst.getEmpId());
				if(hrEisEmpMstLst!=null && !hrEisEmpMstLst.isEmpty())
				{
					objectArgs.put("empFlag", true);
				}
			}
			/**Ends------> Check for employee Eixist OR not*/
			
			resultObject.setViewName("empEditList_WF");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
		} catch (Exception e) 
		{
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setThrowable(e);
			resultObject.setResultValue(objectArgs);
			logger.error("Error While getting Employee Dtls form getEmpData method in EmpInfoService_WF", e);
		}
		return resultObject;
	}
		
	public ResultObject getEmpNextCmbDtls(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			long langId = Long.parseLong(loginMap.get("langId").toString());
			
			String cmbId = objectArgs.get("cmbId").toString();
			StringBuffer sbXML = new StringBuffer();
			if (objectArgs.containsKey("family")) 
			{
				FamilyDtlsDAO fmFamilyDtlsDAO = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
				HrEisFamilyDtl hrEisEmpFamilyDtls = (HrEisFamilyDtl) fmFamilyDtlsDAO.read(Long.valueOf(cmbId));
				
				if(hrEisEmpFamilyDtls!=null) 
				{
					String strFmRelationLookupName = hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation() != null ? hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupName() : "";

					if(!"".equals(strFmRelationLookupName))
					{
						hrEisEmpFamilyDtls.setCmnLookupMstByFmRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmRelationLookupName, langId));
					}
				}
				
				sbXML.append("<Relation>");
				sbXML.append(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupDesc());//Added by sunil for other relation (15/05/08)
				sbXML.append("</Relation>");				
				sbXML.append("<Other>");
				sbXML.append(hrEisEmpFamilyDtls.getFmRelationOther());
				sbXML.append("</Other>");
				
				if(hrEisEmpFamilyDtls.getCmnAddressMst()!=null && hrEisEmpFamilyDtls.getCmnAddressMst().getAddressId()!=0)
				{
					objectArgs.put("mode","ADD");																			
					CmnAddressMst cm = AddressDelegate.setAddressObjectFields(hrEisEmpFamilyDtls.getCmnAddressMst(),objectArgs);
					CmnAddressMst cm1 = new CmnAddressMst();														
					CmnLookupMst cmnLookupMst7 = cm.getCmnLookupMstAddTypeLookupid();
					CmnLookupMst cmnLookupMst = new CmnLookupMst();
					cmnLookupMst.setLookupName(cmnLookupMst7.getLookupName());
					cm1.setCmnLookupMstAddTypeLookupid(cmnLookupMst);
					cm1.setArea(cm.getArea());
					cm1.setMasterLookupid(cm.getMasterLookupid());
					cm1.setLangId(cm.getLangId());
					cm1.setLocId(cm.getLocId());
					cm1.setDbId(cm.getDbId());
												
					if(cm1.getCmnLookupMstAddTypeLookupid().getLookupName().toString().equalsIgnoreCase("City"))
					{
						CmnCityMst cmnCity = new CmnCityMst();
						cmnCity.setCityCode(cm.getCmnCityMst().getCityCode());
						cm1.setCmnCityMst(cmnCity);
					}
					else 
					{	
						if(cm.getCmnVillageMst()!=null)
						{	
							CmnVillageMst cmnVillageMst = new CmnVillageMst();
							cmnVillageMst.setVillageCode(cm.getCmnVillageMst().getVillageCode());
							cm1.setCmnVillageMst(cmnVillageMst);
						}
						if(cm.getCmnDistrictMst()!=null)								
						{
							CmnDistrictMst cmDistrictMst = new CmnDistrictMst();
							cmDistrictMst.setDistrictCode(cm.getCmnDistrictMst().getDistrictCode());
							cm1.setCmnDistrictMst(cmDistrictMst);
						}							
						if(cm.getCmnTalukaMst()!=null)
						{
							CmnTalukaMst cmnTalukaMst = new CmnTalukaMst();
							cmnTalukaMst.setTalukaCode(cm.getCmnTalukaMst().getTalukaCode());
							cm1.setCmnTalukaMst(cmnTalukaMst);
				    	}
					}														
					cm1.setFaliyu(cm.getFaliyu());
					cm1.setHouseName(cm.getHouseName());
					cm1.setImpLandmark(cm.getImpLandmark());
					cm1.setOtherDetails(cm.getOtherDetails());
					cm1.setPincode(cm.getPincode());
					cm1.setSocBuildName(cm.getSocBuildName());
					cm1.setStreet(cm.getStreet());												
				}
				String addressStr=FileUtility.voToXmlFileByXStream(hrEisEmpFamilyDtls);					
				sbXML.append("<Address>");
				sbXML.append(addressStr);
				sbXML.append("</Address>");
			} else 
			{
				List<CmnLookupMst> cmbList = (List<CmnLookupMst>) cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang(cmbId, langId);
				ListIterator li = cmbList.listIterator();
				while (li.hasNext()) {
					CmnLookupMst cmnLookUpObj = (CmnLookupMst) li.next();
					sbXML.append("<Value>");
					sbXML.append(cmnLookUpObj.getLookupDesc());
					sbXML.append("</Value>");
					sbXML.append("<Id>");
					sbXML.append(cmnLookUpObj.getLookupName());
					sbXML.append("</Id>");
				}
			}
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();
			objectArgs.put("ajaxKey", tempStr);
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);	
			objRes.setResultValue(objectArgs);
			logger.error("Error While getting family dtls for emergancy contact dtls of Employee in EmpInfoService_WF", e);
		}
		return objRes;
	}
}