package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.FamilyDtlsDAOImpl;
import com.tcs.sgv.eis.dao.NomineeDtlsDAO;
import com.tcs.sgv.eis.dao.NomineeDtlsDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtlTxn;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtlTxnId;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.dao.AddressDAO;
import com.tcs.sgv.hod.common.dao.AddressDAOImpl;
import com.tcs.sgv.hod.common.delegate.AddressDelegate;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;
import com.tcs.sgv.wf.interfaces.WFConstants;

public class NomineeDetailsServiceImpl extends ServiceImpl implements NomineeDetailsService  
{  
	private static final Log logger = LogFactory.getLog(NomineeDetailsServiceImpl.class);
	List<HrEisNomineeDtlTxn> arHrEisNomineeDtlTxn = new ArrayList<HrEisNomineeDtlTxn>(); 
	public final static long EMP_NOMINEE_MODID=300006;
	public final static String DOC_ID="300010";
	public final static String CORR_DESC="Nominee Details Correspondence";
	public ResultObject getNomineeDetails(Map objectArgs)
	{
	
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{			
			String lStrFlag=objectArgs.get("lStrFlag").toString();
			
			boolean blnWorkFlowEnabled = true;
			if (objectArgs.get("blnWorkFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
			}
			 logger.info("blnWorkFlowEnabled In Service ===================="+blnWorkFlowEnabled);
			   
			    objectArgs.put("blnWorkFlowEnabled",blnWorkFlowEnabled); 
			
			// IFMS - Starts
			long iUserId = Long.parseLong(objectArgs.get("userId").toString());
			objectArgs.put("userId", iUserId);
			// IFMS - Ends
			
					
			if("getComboDetails".equalsIgnoreCase(lStrFlag))
			{
				objRes=getFamilyNomineeInfo(objectArgs, blnWorkFlowEnabled, iUserId);												
			}
		}
		catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getNomineeDetails method in NomineeDetailsServiceImpl Service",e);
		}
		return objRes;
	}

	public ResultObject getFamilyNomineeInfo(Map<String, Object> objectArgs, boolean blnWorkFlowEnabled, long iUserId) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);		
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());			
			List purposelookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Purpose_of_Nomination", langId);
			objectArgs.put("purposeLst", purposelookUpList);
			
			if (!blnWorkFlowEnabled)
			{	
				if (iUserId != 0)
				{	
					objectArgs.put("EmpInfo_userId",iUserId);
					objectArgs.put("EmpInfo_PayFix",true);
					objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
				}
			}
			else
			{
				/**Start==== Changed By Sunil */
				 objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
				 /**Ends==== Changed By Sunil */
			}	
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			if(blnWorkFlowEnabled)
			{
				objRes.setViewName("PurposeofNomination");
			}
			else
			{
				objRes.setViewName("WorkFlowDisablePurposeofNomination");
			}
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getFamilyNomineeInfo method in NomineeDetailsServiceImpl Service",e);
		}
		return objRes;
	}

	@SuppressWarnings("unchecked")
	public ResultObject showNomineePage(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		logger.info("Inside showNomineePage===============");
		try 
		{ 			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
//			 IFMS - Starts
			boolean blnWorkFlowEnabled = true;
			if (objectArgs.get("blnWorkFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
			}
			    logger.info("blnWorkFlowEnabled In Service ===================="+blnWorkFlowEnabled);
			    
			    long iUserId = (objectArgs.get("userId") != null && !objectArgs.get("userId").toString().equals("")) ? Long.parseLong(objectArgs.get("userId").toString()) : 0;
			    objectArgs.put("userId",iUserId);    
//			 IFMS - Ends

			String purposeValue = objectArgs.get("purposeValue").toString();
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			
			
			long userId = Long.parseLong(loginMap.get("userId").toString());//userID
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);		
			
			 /*  Get The selected user Person's User Id*/
			OrgUserMst selectedOrgUserMst = null;
			if (!blnWorkFlowEnabled)
			{
				selectedOrgUserMst = orgUserMstDaoImpl.read((long) iUserId);
			}
			/*End of the geting Person ID Code*/
			
			long langId = Long.parseLong(loginMap.get("langId").toString());//langId
			FamilyDetailsServiceImpl familyDetailsServiceImpl = new FamilyDetailsServiceImpl();
			objRes=familyDetailsServiceImpl.getComboDtls(objectArgs,blnWorkFlowEnabled,iUserId);
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());			
			
			NomineeDtlsDAO nomineeDtlsDAOImpl  = new NomineeDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());			
			
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
			
			List familyLst = new ArrayList();
			
			if (blnWorkFlowEnabled)	
			{	
				if(purposeValue.equalsIgnoreCase("GPF"))
				{
					familyLst	=nomineeDtlsDAOImpl.getEmpFamilyDtlsForFamilyNomination(orgUserMst);
				}
				else
				{
					familyLst = nomineeDtlsDAOImpl.getEmpFamilyDtls(orgUserMst);
				}
			}
			else
			{	if(purposeValue.equalsIgnoreCase("GPF"))
				{
					familyLst	=nomineeDtlsDAOImpl.getEmpFamilyDtlsForFamilyNomination(selectedOrgUserMst);
				}
				else
				{
					familyLst = nomineeDtlsDAOImpl.getEmpFamilyDtls(selectedOrgUserMst);
				}
			}
			List<Object> FamilyName = new ArrayList<Object>();
			List<Object> familyMemeber = new ArrayList<Object>();
			ListIterator li = familyLst.listIterator();			
			while(li.hasNext())
			{				
				HrEisFamilyDtl hrEisEmpFamilyDtls= (HrEisFamilyDtl)li.next();
				
				if(hrEisEmpFamilyDtls.getFmMiddleName() != null && hrEisEmpFamilyDtls.getFmMiddleName().equals("")==false && hrEisEmpFamilyDtls.getCmnLookupMstByFmDeadOrAlive().getLookupName().equalsIgnoreCase("fm_Dead")==false)
				{
					FamilyName.add(hrEisEmpFamilyDtls.getFmFirstName()+" "+hrEisEmpFamilyDtls.getFmMiddleName() + " "+hrEisEmpFamilyDtls.getFmLastName());
				}
				else if(hrEisEmpFamilyDtls.getCmnLookupMstByFmDeadOrAlive().getLookupName().equalsIgnoreCase("fm_Dead")==false)
				{
					FamilyName.add(hrEisEmpFamilyDtls.getFmFirstName()+" "+hrEisEmpFamilyDtls.getFmLastName());
				}
			}
			
			if(blnWorkFlowEnabled)
			{		
				logger.info("For WorkFlow===============================");
				List<Object> requesetLstObj = new ArrayList<Object>();
				/* Getting a  Approved Data List for this purpose */
				CmnLookupMst purposeLookupMst = new CmnLookupMst();			
				CmnLookupMstDAOImpl cMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				purposeLookupMst= cMstDAOImpl.getLookUpVOByLookUpNameAndLang(purposeValue,1);
				List<Object> appprovedDataLstObj=nomineeDtlsDAOImpl.getApprovedNomineeData(orgUserMst,purposeLookupMst);
				/*Getting a Pending a List*/
				List tempPendingLst = nomineeDtlsDAOImpl.getPendingNomineeData(orgUserMst,purposeLookupMst);
				ListIterator li_pending = tempPendingLst.listIterator();
				List pendingDataLstObj = new ArrayList();
				while(li_pending.hasNext())
				{
					try
					{
						Object[] addRow = new Object[3];
						Object row[] = (Object[])li_pending.next();			    				    	
						long reqId= (Long)row[2];
						addRow[0]=row[0];
						addRow[1]=row[1];			
						objectArgs.put("jobTitle", WFConstants.JOB_TITLE_CORR);
						HrmsCommonMessageServImpl msgServImpl =HrmsCommonMessageServImpl.getInstance();
						objRes=msgServImpl.getForwardToDetail(serv, objectArgs, DOC_ID, reqId); 
						addRow[2]=objectArgs.get("fwdTo");
						pendingDataLstObj.add(addRow);
					}catch(Exception e){logger.info("Erorr in fetching pending request===>"+e);}
				}
				List checkLstObj = nomineeDtlsDAOImpl.getPendingDtlsOnEmpId(orgUserMst,purposeLookupMst);
				/*Getting a  Draft Data*/
				List draftData = nomineeDtlsDAOImpl.getDraftData(orgUserMst,purposeLookupMst);
				logger.info("draftData  :   " +draftData.size());
				List chkDraftReqForMember = nomineeDtlsDAOImpl.getDraftReqForMember(orgUserMst,purposeLookupMst);
				List chkPendingReqForMemeber = nomineeDtlsDAOImpl.getPendingReqForMember(orgUserMst,purposeLookupMst);						
				List<Object> XmllstObj = new ArrayList<Object>();			
				double share=0;
				
				FamilyDtlsDAOImpl familyDtlsDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 		
				
				if(appprovedDataLstObj.isEmpty()==false)
				{				
					ListIterator litr = appprovedDataLstObj.listIterator();				
					while(litr.hasNext())
					{									
						HrEisNomineeDtl hrEisEmpNomineeDtls = (HrEisNomineeDtl)litr.next();	
						int indexObj=appprovedDataLstObj.indexOf(hrEisEmpNomineeDtls);
						/*Family Memeber*/										
						if(hrEisEmpNomineeDtls.getFamilyMemberId() != 0){familyMemeber.add(hrEisEmpNomineeDtls.getFamilyMemberId()); }
						else {familyMemeber.add("0");}
						/*For Pending Request And Draft Request Checking */
							
						/*End of Code*/
						/*End of Memeber*/					
						li_pending = checkLstObj.listIterator();
						/*Checking for  Pending Dtls*/
						boolean flag=false;
						boolean memberFlag=false;
						String strFlagType="";
						while(li_pending.hasNext())
						{
							HrEisNomineeDtlTxn hrEisEmpNomineeDtlsTrn=  (HrEisNomineeDtlTxn) li_pending.next();
							
							if(hrEisEmpNomineeDtlsTrn.getRowNumber()!=null && hrEisEmpNomineeDtlsTrn.getRowNumber().equals(hrEisEmpNomineeDtls.getMemberId()))
							{
								flag=true;
								if(hrEisEmpNomineeDtlsTrn.getDraftFlag().equalsIgnoreCase("Y"))
								{
									strFlagType="draft";
								}
								else
								{
									strFlagType="pending";
								}
							}
						}					
						if(flag==false){requesetLstObj.add("Yes");}
						else 
						{
							requesetLstObj.add(strFlagType);						
						}
						/*End of Pending Dtls*/
						HrEisNomineeDtl lVObjNomineeDtls = new HrEisNomineeDtl();
						CmnAddressMst cm = null;
						if(hrEisEmpNomineeDtls.getFamilyMemberId()!=0)
						{
							memberFlag=true;
							
							List familyLstObj=familyDtlsDAOImpl.getTheFamilyMember(userId,hrEisEmpNomineeDtls.getFamilyMemberId());
							
							if (familyLstObj != null && !familyLstObj.isEmpty())
							{
								HrEisFamilyDtl hrEisEmpFamilyDtls = (HrEisFamilyDtl)familyLstObj.get(0);
								cm=hrEisEmpFamilyDtls.getCmnAddressMst();
								if(hrEisEmpFamilyDtls.getFmMiddleName()!=null && hrEisEmpFamilyDtls.getFmMiddleName().equals("")==false)
								{
									lVObjNomineeDtls.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmMiddleName() +" " +hrEisEmpFamilyDtls.getFmLastName());				
								}
								else
								{
									lVObjNomineeDtls.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmLastName());
								}
										
								Date d = StringUtility.convertStringToDate(sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth()));					
								lVObjNomineeDtls.setNomnDob(d);
								
								String cmnLookupId= hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupName();
								logger.info("langId : " +langId);
								CmnLookupMst cmnLookupNomnRelation = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
								logger.info("cmnLookupNomnRelation : " +cmnLookupNomnRelation.getLookupDesc());
								logger.info("cmnLookupNomnRelation id : " +cmnLookupNomnRelation.getLookupId());
								cmnLookupNomnRelation.setLookupName(cmnLookupId);							
								lVObjNomineeDtls.setCmnLookupMstByNomnRelation(cmnLookupNomnRelation);
								
								lVObjNomineeDtls.setNomnOtherRelation(hrEisEmpFamilyDtls.getFmRelationOther());
							}
						}
						else
						{
							lVObjNomineeDtls.setNomnName(hrEisEmpNomineeDtls.getNomnName());
							
							memberFlag=false;	
							Date d = StringUtility.convertStringToDate(sdf.format(hrEisEmpNomineeDtls.getNomnDob()));					
							lVObjNomineeDtls.setNomnDob(d);
							
							String cmnLookupId= hrEisEmpNomineeDtls.getCmnLookupMstByNomnRelation().getLookupName();												
							CmnLookupMst cmnLookupNomnRelation = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
							cmnLookupNomnRelation.setLookupName(cmnLookupId);					
							lVObjNomineeDtls.setCmnLookupMstByNomnRelation(cmnLookupNomnRelation);
							
							lVObjNomineeDtls.setNomnOtherRelation(hrEisEmpNomineeDtls.getNomnOtherRelation());
						}
											
						lVObjNomineeDtls.setOrgUserMstByUserId(hrEisEmpNomineeDtls.getOrgUserMstByUserId());
						lVObjNomineeDtls.setMemberId(hrEisEmpNomineeDtls.getMemberId());
											
						String cmnLookupId= hrEisEmpNomineeDtls.getCmnLookupMstByNomnBenefitTypeId().getLookupName();												
						CmnLookupMst cmnLookupNomnBenefit = new CmnLookupMst();
						cmnLookupNomnBenefit.setLookupName(cmnLookupId);					
						lVObjNomineeDtls.setCmnLookupMstByNomnBenefitTypeId(cmnLookupNomnBenefit);
						
						
						if(hrEisEmpNomineeDtls.getCmnLookupMstByGuardianRelation()!=null)
						{
							cmnLookupId= hrEisEmpNomineeDtls.getCmnLookupMstByGuardianRelation().getLookupName();												
							CmnLookupMst cmnLookupGuardianRel = new CmnLookupMst();
							cmnLookupGuardianRel.setLookupName(cmnLookupId);										
							lVObjNomineeDtls.setCmnLookupMstByGuardianRelation(cmnLookupGuardianRel);
						}
						lVObjNomineeDtls.setGuardianFirstname(hrEisEmpNomineeDtls.getGuardianFirstname());
						lVObjNomineeDtls.setGuardianLastname(hrEisEmpNomineeDtls.getGuardianLastname());
						lVObjNomineeDtls.setGuardianMiddlename(hrEisEmpNomineeDtls.getGuardianMiddlename());
						lVObjNomineeDtls.setGuardianRelationOther(hrEisEmpNomineeDtls.getGuardianRelationOther());
						
						lVObjNomineeDtls.setNomnMinor(hrEisEmpNomineeDtls.getNomnMinor());									
						lVObjNomineeDtls.setNomnSharePercent(hrEisEmpNomineeDtls.getNomnSharePercent());					
						if(memberFlag==false)
						{
							cm=hrEisEmpNomineeDtls.getCmnAddressMstByNomnAddress(); 
						}
						
						
						//added by sandip - start
						if(cm!=null)
						{
							objectArgs.put("addrName", "memberAddress");
							objectArgs.put("addressObject", cm);
							objRes = serv.executeService(servDetails, objectArgs);
							objectArgs.put("mode","ADD");
							Map addressMap = (Map) objectArgs.get("memberAddress");
							CmnAddressMst addressMst = (CmnAddressMst)addressMap.get("addressVO");
							lVObjNomineeDtls.setCmnAddressMstByNomnAddress(addressMst);
						}
						//added by sandip - end
						
						/* commented by sandip - start
						if(cm!=null)
						{
							objectArgs.put("mode","ADD");																			
							cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
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
							
							/*if (cm.getImpLandmark() != null)
							{
								CmnLandmarkMst cmnLandmarkMst = new CmnLandmarkMst();
								cmnLandmarkMst.setLandmarkCode(cm.getImpLandmark().getLandmarkCode());
								cmnCity.setCityCode(cm.getCmnCityMst().getCityCode());
								cm1.setCmnCityMst(cmnCity);
							}
							
							cm1.setFaliyu(cm.getFaliyu());
							cm1.setHouseName(cm.getHouseName());
							cm1.setImpLandmark(cm.getImpLandmark());
							cm1.setOtherDetails(cm.getOtherDetails());
							cm1.setPincode(cm.getPincode());
							cm1.setSocBuildName(cm.getSocBuildName());
							cm1.setStreet(cm.getStreet());							
							lVObjNomineeDtls.setCmnAddressMstByNomnAddress(cm1);
						}
						commented by sandip - end*/
						
						/* commented by sandip - start
						if(hrEisEmpNomineeDtls.getCmnAddressMstByGuardianAddress()!=null)
						{
							objectArgs.put("mode","ADD");																			
							cm = AddressDelegate.setAddressObjectFields(hrEisEmpNomineeDtls.getCmnAddressMstByGuardianAddress(),objectArgs);
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
							lVObjNomineeDtls.setCmnAddressMstByGuardianAddress(cm1);
						}	
						commented by sandip - end*/
						
						//added by sandip - start
						if(hrEisEmpNomineeDtls!=null && hrEisEmpNomineeDtls.getCmnAddressMstByGuardianAddress()!=null)
						{
							objectArgs.put("addrName", "GuardianAddress1");
							objectArgs.put("addressObject", hrEisEmpNomineeDtls.getCmnAddressMstByGuardianAddress());
							objRes = serv.executeService(servDetails, objectArgs);
							objectArgs.put("mode","ADD");
							Map addressMap = (Map) objectArgs.get("GuardianAddress1");
							CmnAddressMst addressMst = (CmnAddressMst)addressMap.get("addressVO");
							lVObjNomineeDtls.setCmnAddressMstByGuardianAddress(addressMst);
						}
						//added by sandip - end
						
						appprovedDataLstObj.set(indexObj, lVObjNomineeDtls);
						String xmlFileIdStr = FileUtility.voToXmlFileByXStream(lVObjNomineeDtls);
						XmllstObj.add(xmlFileIdStr);					
					}
				}			
				/* End Of Approved Data List*/
				ListIterator draftListItr  = chkDraftReqForMember.listIterator(); // Draft Dtls TO check a Family Member
				while(draftListItr.hasNext())
				{
					HrEisNomineeDtlTxn eisEmpNomineeDtlsTrn=(HrEisNomineeDtlTxn)draftListItr.next();
					if(eisEmpNomineeDtlsTrn.getFamilyMemberId()!=0 && familyMemeber.contains(eisEmpNomineeDtlsTrn.getFamilyMemberId())==false)
					{
						familyMemeber.add(eisEmpNomineeDtlsTrn.getFamilyMemberId());
					}
				}
				draftListItr = chkPendingReqForMemeber.listIterator() ;
				while(draftListItr.hasNext())
				{
					HrEisNomineeDtlTxn eisEmpNomineeDtlsTrn=(HrEisNomineeDtlTxn)draftListItr.next();
					if(eisEmpNomineeDtlsTrn.getFamilyMemberId()!=0 && familyMemeber.contains(eisEmpNomineeDtlsTrn.getFamilyMemberId())==false)
					{
						familyMemeber.add(eisEmpNomineeDtlsTrn.getFamilyMemberId());
					}
				}
				/*Adding a Share Details*/
				List chkdraftData= nomineeDtlsDAOImpl.getDraftDataForShareDtls(orgUserMst,purposeLookupMst);	
				ListIterator draftli = chkdraftData.listIterator();
				while(draftli.hasNext())
				{				
					HrEisNomineeDtlTxn dtlsTrn = (HrEisNomineeDtlTxn)draftli.next();
					share=share+dtlsTrn.getNomnSharePercent();
				}
				/*End of Share Details*/
							
				objectArgs.put("share", share);
				objectArgs.put("requesetLstObj", requesetLstObj);
				objectArgs.put("xmlFile", XmllstObj);
				objectArgs.put("draftData",draftData);
				objectArgs.put("approvedData", appprovedDataLstObj);
				objectArgs.put("pendingData", pendingDataLstObj);
				objectArgs.put("familyMemeber", familyMemeber);
			}
			else
			{
//				IFMS
				objRes=getEmployeeNomineeVOList(objectArgs, iUserId);
			}
			objectArgs.put("benefitName", purposeValue);
			objectArgs.put("benefitType",purposeValue);
			objectArgs.put("FamilyName",FamilyName);			
			
			

			
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setResultValue(objectArgs);
			if(blnWorkFlowEnabled)
			{
				objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
				objRes.setViewName("NomineeDetails");	
			}
			else
			{
				objRes.setViewName("WorkFlowDisableNomineeDetails");//IFMS
			}
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling showNomineePage method in NomineeDetailsServiceImpl Service",e);
		}
		return objRes;
	}	

	//IFMS 
	@SuppressWarnings("unchecked")
	private ResultObject getEmployeeNomineeVOList(Map<String, Object> objectArgs, long iUserId)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("Inside After getEmployeeNomineeVOList====================== " );
		try {
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			Map loginMap = (Map) objectArgs.get("baseLoginMap");	
			long langId = Long.parseLong(loginMap.get("langId").toString());
					 
		 /*  Get The Login Id of The Person*/
		 OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		 OrgUserMst orgUserMst = orgUserMstDaoImpl.read(iUserId);		 	    	 				 		    					 			  	 							 
		 
		 String purposeValue = objectArgs.get("purposeValue").toString();
		 NomineeDtlsDAO NomineeDAO = new NomineeDtlsDAOImpl(HrEisNomineeDtl.class,serv.getSessionFactory());
		 CmnLookupMst purposeLookupMst = new CmnLookupMst();
		 
		CmnLookupMstDAOImpl cMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		purposeLookupMst= cMstDAOImpl.getLookUpVOByLookUpNameAndLang(purposeValue,1);
		
		//added by sandip - start
		FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
		FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
		//added by sandip - end
		
		List<HrEisNomineeDtl> hrEisEmpNomineeVOList = NomineeDAO.getNomineeDtls(orgUserMst,purposeLookupMst);
		
		logger.info("Size of NomineeDtls in Service :: "+hrEisEmpNomineeVOList.size());
		
		ArrayList<String> xmlFileName = new ArrayList<String>();
		ListIterator litr= hrEisEmpNomineeVOList.listIterator();
		List<Object> familyMemeber = new ArrayList<Object>();
		
		FamilyDtlsDAOImpl familyDtlsDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 	
		
		while(litr.hasNext())
		{
			HrEisNomineeDtl hrNomineeDtls =(HrEisNomineeDtl)litr.next();
			if(hrNomineeDtls !=null)
			{
				
				String strGuardianRelationLookupName = hrNomineeDtls.getCmnLookupMstByGuardianRelation() != null ? hrNomineeDtls.getCmnLookupMstByGuardianRelation().getLookupName() : "";
				hrNomineeDtls.setCmnLookupMstByGuardianRelation(cMstDAOImpl.getLookUpVOByLookUpNameAndLang(strGuardianRelationLookupName, langId));

				hrNomineeDtls.getCmnLookupMstByNomnBenefitTypeId();
				
				String strNomnRelationLookupName = hrNomineeDtls.getCmnLookupMstByNomnRelation() != null ? hrNomineeDtls.getCmnLookupMstByNomnRelation().getLookupName() : "";
				hrNomineeDtls.setCmnLookupMstByNomnRelation(cMstDAOImpl.getLookUpVOByLookUpNameAndLang(strNomnRelationLookupName, langId));
				
				hrNomineeDtls.getFamilyMemberId();
				hrNomineeDtls.getGuardianFirstname();
				hrNomineeDtls.getGuardianLastname();
				hrNomineeDtls.getGuardianMiddlename();
				hrNomineeDtls.getGuardianRelationOther();
				hrNomineeDtls.getMemberId();
				hrNomineeDtls.getNomnDob();
				hrNomineeDtls.getNomnMinor();
				hrNomineeDtls.getNomnName();
				hrNomineeDtls.getNomnOtherRelation();
				hrNomineeDtls.getNomnSharePercent();
				
				long lnFmMemberId=hrNomineeDtls.getFamilyMemberId();
				if(lnFmMemberId!=0)
				{
					List familyLstObj=familyDtlsDAOImpl.getTheFamilyMember(iUserId,lnFmMemberId);
					if (familyLstObj != null && !familyLstObj.isEmpty())
					{
						HrEisFamilyDtl hrEisEmpFamilyDtls = (HrEisFamilyDtl)familyLstObj.get(0);
						//cm=hrEisEmpFamilyDtls.getCmnAddressMst();
						if(hrEisEmpFamilyDtls.getFmMiddleName()!=null && hrEisEmpFamilyDtls.getFmMiddleName().equals("")==false)
						{
							hrNomineeDtls.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmMiddleName() +" " +hrEisEmpFamilyDtls.getFmLastName());				
						}
						else
						{
							hrNomineeDtls.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmLastName());
						}
									
						Date d = StringUtility.convertStringToDate(sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth()));					
						hrNomineeDtls.setNomnDob(d);
						
						String cmnLookupId= hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupName();
						logger.info("langId : " +langId);
						CmnLookupMst cmnLookupNomnRelation = cMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
						logger.info("cmnLookupNomnRelation : " +cmnLookupNomnRelation.getLookupDesc());
						logger.info("cmnLookupNomnRelation id : " +cmnLookupNomnRelation.getLookupId());
						cmnLookupNomnRelation.setLookupName(cmnLookupId);							
						hrNomineeDtls.setCmnLookupMstByNomnRelation(cmnLookupNomnRelation);
						hrNomineeDtls.setNomnOtherRelation(hrEisEmpFamilyDtls.getFmRelationOther());
						
						//added by sandip - start
						if(hrEisEmpFamilyDtls!=null && hrEisEmpFamilyDtls.getCmnAddressMst()!=null)
						{
							objectArgs.put("addrName", "NomineeAddress1");
							objectArgs.put("addressObject", hrEisEmpFamilyDtls.getCmnAddressMst());
							objRes = serv.executeService(servDetails, objectArgs);
							Map addressMap = (Map) objectArgs.get("NomineeAddress1");
							CmnAddressMst addressMst = (CmnAddressMst)addressMap.get("addressVO");
							hrNomineeDtls.setCmnAddressMstByNomnAddress(addressMst);
						}
						//added by sandip - end
					}
				}
				//added by sandip - start
				else
				{
					if(hrNomineeDtls!=null)
					{
						objectArgs.put("addrName", "NomineeAddress1");
						objectArgs.put("addressObject", hrNomineeDtls.getCmnAddressMstByNomnAddress());
						objRes = serv.executeService(servDetails, objectArgs);
					}
				}
				//added by sandip - end
				
				//For Nominee Address
				/* commented by sandip - start
				if(hrNomineeDtls.getCmnAddressMstByNomnAddress()!=null)
				{	
					CmnAddressMst objCmnAddressMst =  hrNomineeDtls.getCmnAddressMstByNomnAddress();
						
					if(objCmnAddressMst.getAddressId()!=0)
					{
						if (objCmnAddressMst.getCmnStateMst() != null)
							objCmnAddressMst.getCmnStateMst().getStateCode();
						
						if (objCmnAddressMst.getCmnCountryMst() != null)
							objCmnAddressMst.getCmnCountryMst().getCountryCode();
						
						objCmnAddressMst.getArea();
						objCmnAddressMst.getCmnLookupMstAddTypeLookupid().getLookupName();
						objCmnAddressMst.getMasterLookupid();
						
						if (objCmnAddressMst.getCmnLookupMstAddTypeLookupid().getLookupName().toString().equalsIgnoreCase("City"))
						{
							if (objCmnAddressMst.getCmnCityMst() != null)
								objCmnAddressMst.getCmnCityMst().getCityCode();
						}
						else
						{
							if(objCmnAddressMst.getCmnVillageMst()!=null)
								objCmnAddressMst.getCmnVillageMst().getVillageCode();
							
							if(objCmnAddressMst.getCmnDistrictMst()!=null)
								objCmnAddressMst.getCmnDistrictMst().getDistrictCode();
							
							if(objCmnAddressMst.getCmnTalukaMst()!=null)
								objCmnAddressMst.getCmnTalukaMst().getTalukaCode();
						}
						
						if(objCmnAddressMst.getImpLandmark()!= null)
							objCmnAddressMst.getImpLandmark().getLandmarkCode();
						
						objCmnAddressMst.getFaliyu();
						objCmnAddressMst.getHouseName();
						objCmnAddressMst.getImpLandmark();
						objCmnAddressMst.getOtherDetails();
						objCmnAddressMst.getPincode();
						objCmnAddressMst.getSocBuildName();
						objCmnAddressMst.getStreet();	
					}
				}
				commented by sandip - end*/
				
				
				/**End of Nominee Address**/
				
				/**GuardianAddress  Start*/
				/* commented by sandip - start
				if(hrNomineeDtls.getCmnAddressMstByGuardianAddress()!=null)
				{	
					CmnAddressMst objCmnAddressMst =  hrNomineeDtls.getCmnAddressMstByGuardianAddress();
						
					if(objCmnAddressMst.getAddressId()!=0)
					{
						if (objCmnAddressMst.getCmnStateMst() != null)
							objCmnAddressMst.getCmnStateMst().getStateCode();
						
						if (objCmnAddressMst.getCmnCountryMst() != null)
							objCmnAddressMst.getCmnCountryMst().getCountryCode();
						
						objCmnAddressMst.getArea();
						objCmnAddressMst.getCmnLookupMstAddTypeLookupid().getLookupName();
						objCmnAddressMst.getMasterLookupid();
						
						if (objCmnAddressMst.getCmnLookupMstAddTypeLookupid().getLookupName().toString().equalsIgnoreCase("City"))
						{
							if (objCmnAddressMst.getCmnCityMst() != null)
								objCmnAddressMst.getCmnCityMst().getCityCode();
						}
						else
						{
							if(objCmnAddressMst.getCmnVillageMst()!=null)
								objCmnAddressMst.getCmnVillageMst().getVillageCode();
							
							if(objCmnAddressMst.getCmnDistrictMst()!=null)
								objCmnAddressMst.getCmnDistrictMst().getDistrictCode();
							
							if(objCmnAddressMst.getCmnTalukaMst()!=null)
								objCmnAddressMst.getCmnTalukaMst().getTalukaCode();
						}
						
						if(objCmnAddressMst.getImpLandmark()!= null)
							objCmnAddressMst.getImpLandmark().getLandmarkCode();
						
						objCmnAddressMst.getFaliyu();
						objCmnAddressMst.getHouseName();
						objCmnAddressMst.getImpLandmark();
						objCmnAddressMst.getOtherDetails();
						objCmnAddressMst.getPincode();
						objCmnAddressMst.getSocBuildName();
						objCmnAddressMst.getStreet();	
					}
				}
				commented by sandip - end*/
				
				//added by sandip - start
				if(hrNomineeDtls!=null)
				{
					objectArgs.put("addrName", "GrdnAddress");
					objectArgs.put("addressObject", hrNomineeDtls.getCmnAddressMstByGuardianAddress());
					objRes = serv.executeService(servDetails, objectArgs);
				}
				//added by sandip - end
				
				//end Address
				
				xmlFileName.add(FileUtility.voToXmlFileByXStream(hrNomineeDtls));
				
				if (hrNomineeDtls != null && hrNomineeDtls.getFamilyMemberId() != 0)
				{
					
					familyMemeber.add(hrNomineeDtls.getFamilyMemberId());
					
				}
				else
					{familyMemeber.add("0");}
			}
		}	
		
		
		if (iUserId != 0)
		{	
			objectArgs.put("EmpInfo_userId",iUserId);
			objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
		}
		
		objectArgs.put("familyMemeber", familyMemeber);
		
		objectArgs.put("xmlFilePathNameForMulAddRec", xmlFileName);
		objectArgs.put("hrEisEmpNomineeVOList", hrEisEmpNomineeVOList);
		
		logger.info("-========== successfuly executed familyMemeber ============="+ familyMemeber.size());
		logger.info("-========== xmlFilePathNameForMulAddRec ============="+ xmlFileName);
	
		
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
	//	objRes.setViewName("NomineeDetails");
	}catch (Exception e)
	{			
		objRes.setThrowable(e);
		objRes.setResultCode(ErrorConstants.ERROR);
		objRes.setResultValue(objectArgs);
		logger.error("Exception while adding a Qualification Dtls in Service VO",e);
	}
		return objRes;	
	}
	
	//getFamilyMemberDtlsOnRelation
	public ResultObject getFamilyMemberDtlsOnName(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);		
		try 
		{ 			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");			
			String nameStrObj = objectArgs.get("name").toString().trim();
				
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			
			long userId = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

//			 IFMS - Starts
			boolean blnWorkFlowEnabled = true;
			if (objectArgs.get("blnWorkFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
			}
			    logger.info("blnWorkFlowEnabled In Service ===================="+blnWorkFlowEnabled);
			    
			    long iUserId = (objectArgs.get("userId") != null && !objectArgs.get("userId").toString().equals("")) ? Long.parseLong(objectArgs.get("userId").toString()) : 0;
			    objectArgs.put("userId",iUserId);    
//			 IFMS - Ends

//				 IFMS - starts
				 /*  Get The selected user Person's User Id*/
				OrgUserMst selectedOrgUserMst = null;
				if (!blnWorkFlowEnabled)
				{
					selectedOrgUserMst = orgUserMstDaoImpl.read((long) iUserId);
				}
				/*End of the geting Person ID Code*/
				/* IFMS - ends */
			
			FamilyDetailsServiceImpl familyDetailsServiceImpl = new FamilyDetailsServiceImpl();
			objRes=familyDetailsServiceImpl.getComboDtls(objectArgs,blnWorkFlowEnabled,iUserId);
			
			NomineeDtlsDAO nomineeDtlsDAOImpl  = new NomineeDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());				
			
			List familyLst=new ArrayList();
			if(blnWorkFlowEnabled)//IFMS
			{	
				logger.info("For WorkFlow Enable In Service getFamilyMemberDtlsOnName =============");
				familyLst = nomineeDtlsDAOImpl.getEmpFamilyDtls(orgUserMst);	
			}
			else
			{
				logger.info("For WorkFlow Disable In Service getFamilyMemberDtlsOnName =============");
				familyLst = nomineeDtlsDAOImpl.getEmpFamilyDtls(selectedOrgUserMst);
			}
			ListIterator li = familyLst.listIterator();	
			StringBuffer sbXML = new StringBuffer();
			String addressStr=null;
			
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
			
			while(li.hasNext())
			{
				//String addAddressStr=null;								
				HrEisFamilyDtl hrEisEmpFamilyDtls= (HrEisFamilyDtl)li.next();
				String name=null;
				if(hrEisEmpFamilyDtls.getFmMiddleName()!=null && hrEisEmpFamilyDtls.getFmMiddleName().equals("")==false)
				{
					name=hrEisEmpFamilyDtls.getFmFirstName()+" "+hrEisEmpFamilyDtls.getFmMiddleName() + " "+hrEisEmpFamilyDtls.getFmLastName();
				}
				else
				{
					name=hrEisEmpFamilyDtls.getFmFirstName()+ " "+hrEisEmpFamilyDtls.getFmLastName();
				}				
				if(name!=null  && name.equalsIgnoreCase(nameStrObj))
				{															
					HrEisFamilyDtl eisEmpFamilyDtls= new HrEisFamilyDtl();
					
					/* commented by sandip - start
					if(hrEisEmpFamilyDtls.getCmnAddressMst()!=null)
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
						
						if(cm.getCmnCountryMst()!=null)
						{
							CmnCountryMst cmnCountryMst = new CmnCountryMst();
							cmnCountryMst.setCountryCode(cm.getCmnCountryMst().getCountryCode());
							cm1.setCmnCountryMst(cmnCountryMst);								
						}
						if(cm.getCmnStateMst()!=null)
						{
							CmnStateMst cmnStateMst = new CmnStateMst();
							cmnStateMst.setStateCode(cm.getCmnStateMst().getStateCode());
							cm1.setCmnStateMst(cmnStateMst);
						}
						
						if(cm1.getCmnLookupMstAddTypeLookupid().getLookupName().toString().equalsIgnoreCase("City"))
						{
							CmnCityMst cmnCity = new CmnCityMst();
							cmnCity.setCityCode(cm.getCmnCityMst().getCityCode());
							cm1.setCmnCityMst(cmnCity);
						}
						else if("OtherRadioAddress".equalsIgnoreCase(cm.getCmnLookupMstAddTypeLookupid().getLookupName()))
						{
							cm1.setSocBuildName(cm.getSocBuildName());
							cm1.setArea(cm.getArea());
							cm1.setHouseName(cm.getHouseName());
							cm1.setStateName(cm.getStateName());
							cm1.setDistrictName(cm.getDistrictName());
							if(cm.getCmnDistrictMst()!=null)
							{								
								cm1.setCmnDistrictMst(cm.getCmnDistrictMst());
							}
							cm1.setTalukaName(cm.getTalukaName());
							cm1.setCityVilllageName(cm.getCityVilllageName());
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
						eisEmpFamilyDtls.setCmnAddressMst(cm1);
					}
					commented by sandip - end*/
					
					//added by sandip - start
					if(hrEisEmpFamilyDtls!=null && hrEisEmpFamilyDtls.getCmnAddressMst()!=null)
					{
						FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
						objectArgs.put("addrName", "FamilyAddress");
						objectArgs.put("addressObject", hrEisEmpFamilyDtls.getCmnAddressMst());
						objRes = serv.executeService(servDetails, objectArgs);
						objectArgs.put("mode","ADD");
						Map addressMap = (Map) objectArgs.get("FamilyAddress");
						CmnAddressMst addressMst = (CmnAddressMst)addressMap.get("addressVO");
						eisEmpFamilyDtls.setCmnAddressMst(addressMst);
					}
					//added by sandip - end
					
					eisEmpFamilyDtls.setMemberId(hrEisEmpFamilyDtls.getMemberId());
					eisEmpFamilyDtls.setFmFirstName(nameStrObj);
					eisEmpFamilyDtls.setFmRelationOther(hrEisEmpFamilyDtls.getFmRelationOther());
					
					String cmnLookupId= hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupName();												
					CmnLookupMst cmnLookupNomnBenefit = new CmnLookupMst();
					cmnLookupNomnBenefit.setLookupName(cmnLookupId);					
					eisEmpFamilyDtls.setCmnLookupMstByFmRelation(cmnLookupNomnBenefit);
					
					Date date=StringUtility.convertStringToDate(sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth()));
					eisEmpFamilyDtls.setFmDateOfBirth(date);
																			
					
					addressStr=FileUtility.voToXmlFileByXStream(eisEmpFamilyDtls);		
					logger.info("============== addressStr =============="+ addressStr);
					sbXML.append("<Address>");
					sbXML.append(addressStr);
					sbXML.append("</Address>");
					
					/*sbXML.append("<Address>");
					sbXML.append(addressStr);
					sbXML.append("</Address>");*/
					
					/*sbXML.append("<Name>");
					sbXML.append(nameStrObj);
					sbXML.append("</Name>");*/
					
					/*sbXML.append("<Share>");
					sbXML.append("null");
					sbXML.append("</Share>");*/
					
					/*sbXML.append("<Relation>");
					sbXML.append(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupId());				
					sbXML.append("</Relation>");*/
					
					/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String date=null;
					if(hrEisEmpFamilyDtls.getFmDateOfBirth()==null){}					  						  		
					else{date=sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth());}
					sbXML.append("<DOB>");
					sbXML.append(date);				
					sbXML.append("</DOB>");*/
					
					/*sbXML.append("<RelationOther>");
					sbXML.append(hrEisEmpFamilyDtls.getFmRelationOther());				
					sbXML.append("</RelationOther>");*/
					/*
					sbXML.append("<Relation>");
					sbXML.append(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupId());				
					sbXML.append("</Relation>");*/
					
					/*sbXML.append("<houseName>");
					sbXML.append(cmnAddressMst.getHouseName());
					sbXML.append("</houseName>");
					
					sbXML.append("<impLandmark>");
					sbXML.append(cmnAddressMst.getImpLandmark());
					sbXML.append("</impLandmark>");
					
					sbXML.append("<otherDetails>");
					sbXML.append(cmnAddressMst.getOtherDetails());
					sbXML.append("</otherDetails>");									

					sbXML.append("<pincode>");
					sbXML.append(cmnAddressMst.getPincode());
					sbXML.append("</pincode>");
					if(cmnAddressMst.getCmnLookupMstAddTypeLookupid().getLookupName().equalsIgnoreCase("City"))
					{						
						sbXML.append("<cityCode>");
						sbXML.append(cmnAddressMst.getCmnCityMst().getCityCode());
						sbXML.append("</cityCode>");						
						
						sbXML.append("<area>");
						sbXML.append(cmnAddressMst.getArea());
						sbXML.append("</area>");
						
						sbXML.append("<socBuildName>");
						sbXML.append(cmnAddressMst.getSocBuildName());
						sbXML.append("</socBuildName>");
						
						sbXML.append("<street>");
						sbXML.append(cmnAddressMst.getStreet());
						sbXML.append("</street>");
					}
					else												
					{						
						sbXML.append("<villageCode>");
						sbXML.append(cmnAddressMst.getCmnVillageMst().getVillageCode());
						sbXML.append("</villageCode>");
						
						sbXML.append("<faliyu>");
						sbXML.append(cmnAddressMst.getFaliyu());
						sbXML.append("</faliyu>");

						sbXML.append("<districtCode>");
						sbXML.append(cmnAddressMst.getCmnDistrictMst().getDistrictCode());
						sbXML.append("</districtCode>");

						sbXML.append("<talukaCode>");
						sbXML.append(cmnAddressMst.getCmnTalukaMst().getTalukaCode());
						sbXML.append("</talukaCode>");
						 
					}*/	
					break;
				}
			}
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();																					
			logger.info("tempXML is  getFamilyMemberDtlsOnName ::" + tempStr);
			objectArgs.put("ajaxKey", tempStr);													
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");				
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getFamilyMemberDtlsOnName method in NomineeDetailsServiceImpl Service",e);
		}
		return objRes;
	}
	

	@SuppressWarnings("unchecked")
	public ResultObject submitNomineeDtls(Map<String, Object> objectArgs)           // 300356
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("Inside submitNomineeDtls service=================================== ");
		try 
		{	
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			
			boolean submitFlag=false;
			/* Get The Login Id of The Person */								
			
			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);

			/* End of the geting Person ID Code */

			/* Get The Person Post */
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			/* End of the geting Person Post Code */

			
			/* Getting a DB ID */
			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
			/* End og DB Id code */

			/* Getting a Lang Id Code */
			long langId = Long.parseLong(loginMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLangMst = cmnLanguageMstDaoImpl.read(langId);
			/* End of Lang ID Code */

			/* Getting a Loc ID Code */
			long locId = Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			/* End of Loc ID */
			
//			 IFMS - Starts
			boolean blnWorkFlowEnabled = true;
			if (objectArgs.get("blnWorkFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
			}
			    logger.info("blnWorkFlowEnabled In Service submitNomineeDtls===================="+blnWorkFlowEnabled);
			   
			    long iUserId = (objectArgs.get("userId") != null && !objectArgs.get("userId").toString().equals("")) ? Long.parseLong(objectArgs.get("userId").toString()) : 0;
			   // objectArgs.put("userId",iUserId); 
			   logger.info("Value Of iUserId in submitNomineeDtls service=================="+iUserId);
//			 IFMS - Ends
		
			 if(blnWorkFlowEnabled)
				{
					logger.info("====================For submit NomineeDtls =================");
					/* Find The Maximum Srno To Enter The Record */
					NomineeDtlsDAO nomineeDaoImplObj = new NomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class, serv.getSessionFactory());			
					/* End Getting Max Srno */

			
				String draft=objectArgs.get("draft").toString();		
				/*Generateing a Req  _ id */
				/*objectArgs.put("tablename", "hr_eis_nominee_dtl_txn");
				objectArgs.put("serviceLocator", serv);		
				ResultObject resultObj = serv.executeService("GENERATE_ID_SRVC", objectArgs);
				int receivedcode = resultObj.getResultCode();
				if (receivedcode == -1) {
					return objRes;
				}
				Map resultMap = (Map) resultObj.getResultValue();
				long reqId = (Long) resultMap.get("newID");*/
				long reqId = IDGenerateDelegate.getNextId("hr_eis_nominee_dtl_txn", objectArgs);
				/*end of req Id code***/
				long nominId=0;
				String[] txnXML = (String[]) objectArgs.get("encXML");			
				List lstlockupTxnVO = FileUtility.xmlFilesToVoByXStream(txnXML);
				HrEisNomineeDtlTxn lVObject = new HrEisNomineeDtlTxn();
				CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				boolean workFlaow=false;
				
				Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
				
				for (Iterator i = lstlockupTxnVO.iterator(); i.hasNext();) 
				{
					 Object Obj = i.next();
			    	 if(Obj != null)
		    		 {
				    	 Class<? extends Object> c=Obj.getClass();				    	 
				    	 if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisNomineeDtlTxn"))
				    	 {	
				    		 boolean memberFlag=true;	
				    		 lVObject = (HrEisNomineeDtlTxn) Obj;
				    		 if(lVObject.getCmnLookupMstByGuardianRelation() != null)
				    		 {
				    			 lVObject.setCmnLookupMstByGuardianRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByGuardianRelation().getLookupName(), langId));
				    		 }
				    		 lVObject.setCmnLookupMstByNomnRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByNomnRelation().getLookupName(), langId));
				    		 lVObject.setCmnLookupMstByNomnBenefitTypeId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByNomnBenefitTypeId().getLookupName(),1));
				    		 
				    		 HrEisNomineeDtlTxnId hrEisEmpNomineeDtlsTrnId = new HrEisNomineeDtlTxnId();
				    		 hrEisEmpNomineeDtlsTrnId.setReqId(reqId);
				    		 nominId=nominId+1;
				    		 hrEisEmpNomineeDtlsTrnId.setMemberId(nominId);
				    		 lVObject.setId(hrEisEmpNomineeDtlsTrnId);				    		 
				    		 if("1".equals(draft)) {lVObject.setDraftFlag("Y");}
				    		 else if("0".equals(draft))
				    		 {
				    			 lVObject.setDraftFlag("N");
				    			 if(lVObject.getRequestFlag()==null){
				    			 lVObject.setRequestFlag("A");}
				    			 workFlaow=true;
				    			 lVObject.setRowNumber(lVObject.getRowNumber());
				    		 }			    		 
				    		 if(lVObject.getFamilyMemberId()!=0)
				    		 {							    			 
				    			 lVObject.setNomnDob(null);
				    			 lVObject.setNomnName(null);
				    			 lVObject.setNomnOtherRelation(null);			    			 
				    			 lVObject.setCmnLookupMstByNomnRelation(null);
				    			 lVObject.setCmnAddressMstByNomnAddress(null);
				    			 memberFlag=true;
				    		 }
				    		 else
				    		 {
				    			 memberFlag=false;
				    		 }
				    		 lVObject.setActionFlag("P");
				    		 lVObject.setCmnLocationMst(cmnLocationMst);
				    		 lVObject.setOrgUserMstByUserId(orgUserMst);
				    		
				    		 lVObject.setCmnDatabaseMst(cmnDatabaseMst);
				    		 lVObject.setOrgUserMstByCreatedBy(orgUserMst);
				    		 lVObject.setOrgPostMstByCreatedByPost(orgPostMst);
				    		 lVObject.setCreatedDate(currDate);
				    		 if(lVObject.getCmnAddressMstByNomnAddress()!=null && memberFlag==false)
				    		 {								
				    			 /*Generateing a Req  _ id  for Address*/
					    		 /*objectArgs.put("tablename", "CMN_ADDRESS_MST");
					    		 objectArgs.put("serviceLocator", serv);		
					    		 ResultObject resultObj1 = serv.executeService("GENERATE_ID_SRVC", objectArgs);				
					    		 int receivedcode1 = resultObj.getResultCode();
					    		 if (receivedcode1 == -1) {
					    			 return objRes;
					    		 }
					    		 Map resultMap1 = (Map) resultObj1.getResultValue();				
					    		 long addressId= (Long) resultMap1.get("newID");*/
				    			 long addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
					    		 /*end of req Id code***/
				    			 objectArgs.put("mode","ADD");
				    			 CmnAddressMst cm = lVObject.getCmnAddressMstByNomnAddress();
				    			 cm.setAddressId(addressId);
				    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
				    			 CmnLookupMst cmn = new CmnLookupMst();					
				    			 cmn.setLookupId(300141);
				    			 if(cm.getCmnLookupMstAddTypeLookupid()==null){cm.setCmnLookupMstAddTypeLookupid(cmn);}			    			
				    			 lVObject.setCmnAddressMstByNomnAddress(cm); 
				    			 nomineeDaoImplObj.create(cm);					
				    		 }
				    		 if(lVObject.getCmnAddressMstByGuardianAddress()!=null && lVObject.getNomnMinor().equalsIgnoreCase("Y")==true)
				    		 {							
				    			 /*Generateing a Req  _ id  for Address*/
					    		 /*objectArgs.put("tablename", "CMN_ADDRESS_MST");
					    		 objectArgs.put("serviceLocator", serv);		
					    		 ResultObject resultObj1 = serv.executeService("GENERATE_ID_SRVC", objectArgs);				
					    		 int receivedcode1 = resultObj.getResultCode();
					    		 if (receivedcode1 == -1) {
					    			 return objRes;
					    		 }
					    		 Map resultMap1 = (Map) resultObj1.getResultValue();				
					    		 long addressId= (Long) resultMap1.get("newID");*/
				    			 long addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
					    		 /*end of req Id code***/
				    			 objectArgs.put("mode","ADD");
				    			 CmnAddressMst cm = lVObject.getCmnAddressMstByGuardianAddress();			    			 
				    			 cm.setAddressId(addressId);
				    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
				    			 CmnLookupMst cmn = new CmnLookupMst();					
				    			 cmn.setLookupId(300141);
				    			 if(cm.getCmnLookupMstAddTypeLookupid()==null){cm.setCmnLookupMstAddTypeLookupid(cmn);}			    			
				    			 lVObject.setCmnAddressMstByGuardianAddress(cm); 
				    			 nomineeDaoImplObj.create(cm);					
				    		 }				
				    		 
				    		 lVObject.setCheckStatus("Y"); //Divyesh
				    		 //nomineeDaoImplObj.create(lVObject);
				    		 this.arHrEisNomineeDtlTxn.add(lVObject);
				    	 }
		    		 }
				}
				String deleteFmDtls = objectArgs.get("deleteArr").toString();
				StringTokenizer st= new StringTokenizer(deleteFmDtls,",");				 
				while(st.hasMoreTokens())
				{					 
					String deleteFmStrObj= st.nextElement().toString();
					if(deleteFmStrObj!=null)
					{
						String deleteEduStrArrObj[]= new String[1];
						deleteEduStrArrObj[0]= deleteFmStrObj; 
						List deleteLstObj = FileUtility.xmlFilesToVoByXStream(deleteEduStrArrObj);
						for(Iterator j  = deleteLstObj.iterator(); j.hasNext();) 
						{								
							Object obj = j.next();			    		 
							if(obj != null)
							{					    		 
								Class c=obj.getClass();					    		
								if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisNomineeDtlTxn"))
								{								
									HrEisNomineeDtlTxn delVObject = (HrEisNomineeDtlTxn)obj;
									
									delVObject = (HrEisNomineeDtlTxn) nomineeDaoImplObj.read(delVObject.getId());
									
									delVObject.setOrgUserMstByUserId(orgUserMst);
									nomineeDaoImplObj.delete(delVObject);							    			 
								}
								else
								{
									if("0".equals(draft))
						    		{
										workFlaow=true;
						    		}
									HrEisNomineeDtl ht = (HrEisNomineeDtl)obj;
									nominId=nominId+1;			    			 
									deleteEmpNomineeDtls(objectArgs,ht,ht.getMemberId(),reqId,nominId,draft,cmnLocationMst,cmnLangMst,cmnDatabaseMst,orgPostMst,orgUserMst);
								}
							}
						}
					}
				}
				if("1".equals(draft)) {submitFlag=false;}
				else if("0".equals(draft))
				{ 	
					submitFlag=true;
				}
				long corr_id=0;
				if(workFlaow==true && submitFlag)
				{
					corr_id=HrmsCommonMessageServImpl.createCorr(objectArgs, serv, DOC_ID,CORR_DESC);//For Creating Corrospondance Id
					logger.info("corrsId=========In EmpFamilyServiceImpl==="+corr_id);
				}
				
				/*Divyesh - Starts*/
				ListIterator ltrHrEisNomineeDtlTxn =  this.arHrEisNomineeDtlTxn.listIterator(); //sunil
				HrEisNomineeDtlTxn objHrEisNomineeDtlTxn = null;
				
				while (ltrHrEisNomineeDtlTxn.hasNext()) {
					objHrEisNomineeDtlTxn = (HrEisNomineeDtlTxn) ltrHrEisNomineeDtlTxn.next();
					objHrEisNomineeDtlTxn.setCorrsId(corr_id);
					nomineeDaoImplObj.create(objHrEisNomineeDtlTxn);
				}
				/*Divyesh  - Ends*/
				
				objRes.setResultCode(ErrorConstants.SUCCESS);
				if(submitFlag==false)
				{
					objectArgs.put("draft","Y");
				}
				if(workFlaow==true && submitFlag!=false)
				{
					objectArgs.put("modId", EMP_NOMINEE_MODID);
					objectArgs.put("appReqId", corr_id);
					HrmsCommonMessageServImpl.createModEmpRltTuple(serv, objectArgs, userID);
					objRes=HrmsCommonMessageServImpl.getForwardToDetail(serv, objectArgs, DOC_ID, corr_id);   
					String msg="EMP_NOMINEE_REQUEST";
					objectArgs.put("status", "PENDING"); 
					objectArgs.put("msg", msg); 
				}
				else
				{
					objectArgs.put("draft","Y");//For message view for submit page
					objRes.setViewName("SubmitMessagePage");
				}
			}
			else//IFMS
			{
				logger.info("====================For SaveNomineeDtls For IFMS=================");
				SaveNomineeDtls(objectArgs,blnWorkFlowEnabled, iUserId);//IFMS
			}
			
			 if(!blnWorkFlowEnabled)	
			 {
				 objRes=getFamilyNomineeInfo(objectArgs, blnWorkFlowEnabled, iUserId);						
				 objRes.setViewName("WorkFlowDisablePurposeofNomination");
			 }
			objRes.setResultValue(objectArgs);
		}catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Submitting a Nominee dtls  in Service",e);
		}
		return objRes;		
	}
	
//	IFMS
	@SuppressWarnings("unchecked")
	private ResultObject SaveNomineeDtls(Map<String, Object> objectArgs, boolean blnWorkFlowEnabled, long iUserId)          
	{
		logger.info("Value Of iUserId in SaveNomineeDtls service=================="+iUserId);
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{	
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			Map loginMap = (Map) objectArgs.get("baseLoginMap");	
		
			/* Get The Login Id of The Person */								
			
			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			/* End of the geting Person ID Code */
			
			
			OrgUserMst selectedOrgUserMst = null;//IFMS
			if (!blnWorkFlowEnabled)
			{
				selectedOrgUserMst = orgUserMstDaoImpl.read(iUserId);
			}
			
			/* Get The Person Post */
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			/* End of the geting Person Post Code */
						
			/* Getting a DB ID */
			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
			/* End og DB Id code */

			/* Getting a Lang Id Code */
			long langId = Long.parseLong(loginMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			@SuppressWarnings("unused")
			CmnLanguageMst cmnLangMst = cmnLanguageMstDaoImpl.read(langId);
			
			/* End of Lang ID Code */

			/* Getting a Loc ID Code */
			long locId = Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			/* End of Loc ID */

			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			NomineeDtlsDAO nomineeDaoImplObj = new NomineeDtlsDAOImpl(HrEisNomineeDtl.class, serv.getSessionFactory());
			AddressDAO addressDao = new AddressDAOImpl(CmnAddressMst.class, serv.getSessionFactory());
			logger.info("FOR DB UPDATION IN SERVICE=====================");
			 
//    		 FamilyDtlsDAO FamilyDaoImplObj = new FamilyDtlsDAOImpl(HrEisEmpFamilyDtls.class, serv.getSessionFactory());
    
			Date currDate = new java.util.Date();
    		 
			 List upDatedVOList= (List) objectArgs.get("upDatedVOList");
			 List deletedVOList= (List) objectArgs.get("deletedVOList");		
			
			 logger.info("upDatedVOList.size()===================="+upDatedVOList.size());
			 if(upDatedVOList!=null)
			 {
				 ListIterator liForUpdateVO = upDatedVOList.listIterator();
				 while(liForUpdateVO.hasNext())
				 {				 
					 //HrEisNomineeDtl eisEmpNomineeDtls =new HrEisNomineeDtl();
					 HrEisNomineeDtl eisEmpNomineeDtls =(HrEisNomineeDtl)liForUpdateVO.next();
					 
					 logger.info("Updateing Existing Record in DB........................" + eisEmpNomineeDtls.getMemberId());
					 
					 logger.info("Updateing Existing Record in DB for getNomnSharePercent.." + eisEmpNomineeDtls.getNomnSharePercent());
					 
					 long memId= eisEmpNomineeDtls.getMemberId();
					
					 HrEisNomineeDtl updateHrEisEmpNomineeDtls=(HrEisNomineeDtl)nomineeDaoImplObj.read(memId);
				
					 logger.info("eisEmpFamilyDtls.getMemberId()======="+eisEmpNomineeDtls.getMemberId());
					
					
					 updateHrEisEmpNomineeDtls.setFamilyMemberId(eisEmpNomineeDtls.getFamilyMemberId());
					 updateHrEisEmpNomineeDtls.setGuardianFirstname(eisEmpNomineeDtls.getGuardianFirstname());
					 updateHrEisEmpNomineeDtls.setGuardianLastname(eisEmpNomineeDtls.getGuardianLastname());
					 updateHrEisEmpNomineeDtls.setGuardianMiddlename(eisEmpNomineeDtls.getGuardianMiddlename());
					 updateHrEisEmpNomineeDtls.setGuardianRelationOther(eisEmpNomineeDtls.getGuardianRelationOther());
					
					 updateHrEisEmpNomineeDtls.setNomnDob(eisEmpNomineeDtls.getNomnDob());
					 updateHrEisEmpNomineeDtls.setNomnMinor(eisEmpNomineeDtls.getNomnMinor());
					 updateHrEisEmpNomineeDtls.setNomnName(eisEmpNomineeDtls.getNomnName());
					 updateHrEisEmpNomineeDtls.setNomnOtherRelation(eisEmpNomineeDtls.getNomnOtherRelation());
					 updateHrEisEmpNomineeDtls.setNomnSharePercent(eisEmpNomineeDtls.getNomnSharePercent());
					 if(eisEmpNomineeDtls.getCmnLookupMstByGuardianRelation()!=null)
					 {
						 updateHrEisEmpNomineeDtls.setCmnLookupMstByGuardianRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpNomineeDtls.getCmnLookupMstByGuardianRelation().getLookupName(), langId));
					 }
					 if(eisEmpNomineeDtls.getCmnLookupMstByNomnRelation()!=null)
					 {
						 updateHrEisEmpNomineeDtls.setCmnLookupMstByNomnRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpNomineeDtls.getCmnLookupMstByNomnRelation().getLookupName(), langId));
					 }
					 if(eisEmpNomineeDtls.getCmnLookupMstByNomnBenefitTypeId()!=null)
					 {
						 updateHrEisEmpNomineeDtls.setCmnLookupMstByNomnBenefitTypeId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpNomineeDtls.getCmnLookupMstByNomnBenefitTypeId().getLookupName(), 1));
					 }
		 	 
					 updateHrEisEmpNomineeDtls.setDeleteFlag("N");
					 
					 /** Start For Adress Updation*/ 
					 /*if(eisEmpNomineeDtls.getCmnAddressMstByNomnAddress()!=null)
		    		 {		
						 *//**Generateing a Req_Id  for CMN_ADDRESS_MST*//*	
		    				long addressId = 0;
		    				addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
		    				logger.info("Inserting  PK..... " + addressId);
		    				*//**end of req Id code***//*		
		    			
		    			 objectArgs.put("mode","ADD");
		    			 CmnAddressMst cm = eisEmpNomineeDtls.getCmnAddressMstByNomnAddress();
		    			 cm.setAddressId(addressId);
		    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
		    			 CmnLookupMst cmn = new CmnLookupMst();					
		    			 cmn.setLookupId(300141);
		    			 if(cm.getCmnLookupMstAddTypeLookupid()==null)
		    			 {
		    				 cm.setCmnLookupMstAddTypeLookupid(cmn);
		    			 }			    			
		    			 addressDao.create(cm);
		    			 updateHrEisEmpNomineeDtls.setCmnAddressMstByNomnAddress(cm);
		    		 }*/
					 
					 if(eisEmpNomineeDtls.getCmnAddressMstByGuardianAddress()!=null && eisEmpNomineeDtls.getNomnMinor().equalsIgnoreCase("Y")==true)
		    		 {							
						/**Generateing a Req_Id  for CMN_ADDRESS_MST*/	
		    				long addressId = 0;
		    				addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
		    				logger.info("Inserting Guardian Address PK..... " + addressId);
		    			/**end of req Id code***/		
			
		    			 objectArgs.put("mode","ADD");
		    			 CmnAddressMst cm = eisEmpNomineeDtls.getCmnAddressMstByGuardianAddress();			    			 
		    			 cm.setAddressId(addressId);
		    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
		    			 CmnLookupMst cmn = new CmnLookupMst();					
		    			 cmn.setLookupId(300141);
		    			 if(cm.getCmnLookupMstAddTypeLookupid()==null)
		    			 {
		    				 cm.setCmnLookupMstAddTypeLookupid(cmn);
		    			 }	
		    			 addressDao.create(cm);
		    			 updateHrEisEmpNomineeDtls.setCmnAddressMstByGuardianAddress(cm); 
		    		 }	
					 //added by sandip - start
					 if(eisEmpNomineeDtls.getCmnAddressMstByNomnAddress()!=null && eisEmpNomineeDtls.getFamilyMemberId()==0)
					 {
						 long addressId = 0;
		    			 addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
		    			 objectArgs.put("mode","ADD");
			    		 CmnAddressMst cm = eisEmpNomineeDtls.getCmnAddressMstByNomnAddress();			    			 
			    		 cm.setAddressId(addressId);
			    		 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
			    		 CmnLookupMst cmn = new CmnLookupMst();					
			    		 cmn.setLookupId(300141);
			    		 if(cm.getCmnLookupMstAddTypeLookupid()==null)
			    		 {
			    			 cm.setCmnLookupMstAddTypeLookupid(cmn);
			    		 }	
			    		 addressDao.create(cm);
			    		 updateHrEisEmpNomineeDtls.setCmnAddressMstByNomnAddress(cm); 
					 }
					 //added by sandip - end
					 /** Ends For Adress Updation*/
					 
					 updateHrEisEmpNomineeDtls.setCmnDatabaseMst(cmnDatabaseMst);
					 updateHrEisEmpNomineeDtls.setCmnLocationMst(cmnLocationMst);
					 updateHrEisEmpNomineeDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
					 updateHrEisEmpNomineeDtls.setOrgUserMstByUpdatedBy(orgUserMst);
					 updateHrEisEmpNomineeDtls.setUpdatedDate(currDate);
					 
					 nomineeDaoImplObj.update(updateHrEisEmpNomineeDtls);
				 }
			 }
			
			 if(deletedVOList!=null)
			 {
				 ListIterator liForDeletVO = deletedVOList.listIterator();
				 while(liForDeletVO.hasNext())
				 {
					 //HrEisNomineeDtl eisEmpNomineeDtls =new HrEisNomineeDtl();
					 HrEisNomineeDtl eisEmpNomineeDtls =(HrEisNomineeDtl)liForDeletVO.next();
					 logger.info("Deleting  Existing Record in DB........................" + eisEmpNomineeDtls.getMemberId());
					 
					 HrEisNomineeDtl updateHrEisEmpNomineeDtls=(HrEisNomineeDtl)nomineeDaoImplObj.read(eisEmpNomineeDtls.getMemberId());
					 updateHrEisEmpNomineeDtls.setDeleteFlag("Y");
					
					 updateHrEisEmpNomineeDtls.setCmnDatabaseMst(cmnDatabaseMst);
					 updateHrEisEmpNomineeDtls.setCmnLocationMst(cmnLocationMst);							 
					 updateHrEisEmpNomineeDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
					 updateHrEisEmpNomineeDtls.setOrgUserMstByUpdatedBy(orgUserMst);
					 nomineeDaoImplObj.update(updateHrEisEmpNomineeDtls);
					 //nomineeDaoImplObj.delete(updateHrEisEmpNomineeDtls);
				 }
			 }
			
//Insert 			
			String[] txnXML = (String[]) objectArgs.get("encXML");			
			List lstlockupTxnVO = FileUtility.xmlFilesToVoByXStream(txnXML);
			HrEisNomineeDtl lVObject = new HrEisNomineeDtl();
			for (Iterator i = lstlockupTxnVO.iterator(); i.hasNext();) 
			{
				 Object Obj = i.next();
			   	 if(Obj != null)
		    	 {
			    	 Class<? extends Object> c=Obj.getClass();				    	 
			    	 if(c.getName().toString().equals("com.tcs.sgv.eis.valueobject.HrEisNomineeDtl"))
			    	 {	
			    		 boolean memberFlag=true;	
			    		 lVObject = (HrEisNomineeDtl) Obj;
			    		 
			    		 /*Generateing a Req_Id  for hr_eis_emp_nominee_dtls*/	
							long reqId = 0;
							reqId = IDGenerateDelegate.getNextId("hr_eis_nominee_dtl", objectArgs);
							logger.info("Inserting  PK..... " + reqId);
							/*end of req Id code***/		
			    		 
						 if(lVObject.getCmnLookupMstByGuardianRelation() != null)
				    	 {
				    		 lVObject.setCmnLookupMstByGuardianRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByGuardianRelation().getLookupName(), langId));
				    	 }
						 if(lVObject.getCmnLookupMstByNomnRelation()!=null)
						 {
				    		 lVObject.setCmnLookupMstByNomnRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByNomnRelation().getLookupName(), langId));
						 }
						 if(lVObject.getCmnLookupMstByNomnBenefitTypeId()!=null)
						 {
						  lVObject.setCmnLookupMstByNomnBenefitTypeId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByNomnBenefitTypeId().getLookupName(),1));
						 }
						 lVObject.setMemberId(reqId);
				    	if(lVObject.getFamilyMemberId()!=0)
			    		 {				
				    		lVObject.setFamilyMemberId(lVObject.getFamilyMemberId());
				    		lVObject.setNomnName(lVObject.getNomnName());
				    		lVObject.setNomnDob(lVObject.getNomnDob());
				    	
			    			lVObject.setNomnOtherRelation(null);
			    	
			    			lVObject.setNomnSharePercent(lVObject.getNomnSharePercent());

			    			/*CmnAddressMst cm = lVObject.getCmnAddressMstByNomnAddress();
			    			cm.setAddressId(lVObject.getCmnAddressMstByNomnAddress().getAddressId());
			    			lVObject.setCmnAddressMstByNomnAddress(cm);*/
			    			
			    			 //lVObject.setNomnDob(null);
			    			 //lVObject.setNomnName(null);
			    			// lVObject.setNomnOtherRelation(null);			    			 
			    			// lVObject.setCmnLookupMstByNomnRelation(null);
			    			//lVObject.setCmnAddressMstByNomnAddress(lVObject.getCmnAddressMstByNomnAddress());

			    		//	logger.info("CmnAddressMstByNomnAddress============"+lVObject.getCmnAddressMstByNomnAddress().getAddressId());
			    			lVObject.setCmnAddressMstByNomnAddress(null);
			    			 memberFlag=true;
			    		 }
			    		 else{
			    			 
			    			 lVObject.setNomnOtherRelation(lVObject.getNomnOtherRelation());
			    			  memberFlag=false;
			    		 }
			    		 
			    		 lVObject.setCmnLocationMst(cmnLocationMst);
			    		 lVObject.setOrgUserMstByUserId(selectedOrgUserMst);//IFMS
			    		 lVObject.setDeleteFlag("N");
			    		 lVObject.setCmnDatabaseMst(cmnDatabaseMst);
			    		 lVObject.setOrgUserMstByCreatedBy(orgUserMst);
			    		 lVObject.setOrgPostMstByCreatedByPost(orgPostMst);
			    		 lVObject.setCreatedDate(currDate);
			    		 if(lVObject.getCmnAddressMstByNomnAddress()!=null && memberFlag==false)
			    		 {		
        			  /*Generateing a Req_Id  for CMN_ADDRESS_MST*/	
			    				long addressId = 0;
			    				addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
			    				logger.info("Inserting  PK..... " + addressId);
			    	/*end of req Id code***/		
			    			
			    			 objectArgs.put("mode","ADD");
			    			 CmnAddressMst cm = lVObject.getCmnAddressMstByNomnAddress();
			    			 cm.setAddressId(addressId);
			    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
			    			 CmnLookupMst cmn = new CmnLookupMst();					
			    			 cmn.setLookupId(300141);
			    			 if(cm.getCmnLookupMstAddTypeLookupid()==null)
			    			 {cm.setCmnLookupMstAddTypeLookupid(cmn);}			    			
			    			 lVObject.setCmnAddressMstByNomnAddress(cm); 
			    			 
			    			 
			    			 nomineeDaoImplObj.create(cm);					
			    		 }
			    		 if(lVObject.getCmnAddressMstByGuardianAddress()!=null && lVObject.getNomnMinor().equalsIgnoreCase("Y")==true)
			    		 {							
			    		  /*Generateing a Req_Id  for CMN_ADDRESS_MST*/	
			    				long addressId = 0;
			    				addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
			    				logger.info("Inserting Guardian Address PK..... " + addressId);
			    	/*end of req Id code***/		
   			
			    			 objectArgs.put("mode","ADD");
			    			 CmnAddressMst cm = lVObject.getCmnAddressMstByGuardianAddress();			    			 
			    			 cm.setAddressId(addressId);
			    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
			    			 CmnLookupMst cmn = new CmnLookupMst();					
			    			 cmn.setLookupId(300141);
			    			 if(cm.getCmnLookupMstAddTypeLookupid()==null){cm.setCmnLookupMstAddTypeLookupid(cmn);}			    			
			    			 lVObject.setCmnAddressMstByGuardianAddress(cm); 
			    			 nomineeDaoImplObj.create(cm);					
			    		 }				    
			    		 lVObject.setTrnCounter(Integer.valueOf(1));//For History
			    		 nomineeDaoImplObj.create(lVObject);
			    	 }
		    		 }
				}
			 
			//objRes.setViewName("SubmitMessagePage");
			objRes.setViewName("WorkFlowDisablePurposeofNomination");
			objRes.setResultValue(objectArgs);
		}catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Submitting a Nominee dtls  in Service",e);
		}
		return objRes;		
	}
	
	@SuppressWarnings("unchecked")
	public void deleteEmpNomineeDtls(Map<String, Object> objectArgs,HrEisNomineeDtl eisEmpNomineeDtlsVO, long memberId, long reqId, long nominId, String draft, CmnLocationMst cmnLocationMst, CmnLanguageMst cmnLangMst, CmnDatabaseMst cmnDatabaseMst, OrgPostMst orgPostMst, OrgUserMst orgUserMst) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
									
			NomineeDtlsDAO nmDtlsDAOImpl = new NomineeDtlsDAOImpl(HrEisNomineeDtl.class,serv.getSessionFactory());
			eisEmpNomineeDtlsVO.setOrgUserMstByUserId(orgUserMst);			
			eisEmpNomineeDtlsVO.setMemberId(memberId);
			eisEmpNomineeDtlsVO=(HrEisNomineeDtl) nmDtlsDAOImpl.read(eisEmpNomineeDtlsVO.getMemberId());
			HrEisNomineeDtlTxn lVObject = new HrEisNomineeDtlTxn();						
			HrEisNomineeDtlTxnId hrEisEmpNomineeDtlsTrnId = new HrEisNomineeDtlTxnId();
			hrEisEmpNomineeDtlsTrnId.setReqId(reqId);
			hrEisEmpNomineeDtlsTrnId.setMemberId(nominId);
			lVObject.setId(hrEisEmpNomineeDtlsTrnId);
			lVObject.setNomnMinor(eisEmpNomineeDtlsVO.getNomnMinor());
			if(eisEmpNomineeDtlsVO.getFamilyMemberId()!=0)
			{
				lVObject.setFamilyMemberId(eisEmpNomineeDtlsVO.getFamilyMemberId());
			}
			else
			{
				lVObject.setNomnName(eisEmpNomineeDtlsVO.getNomnName());
				lVObject.setNomnDob(eisEmpNomineeDtlsVO.getNomnDob());
				lVObject.setNomnOtherRelation(eisEmpNomineeDtlsVO.getNomnOtherRelation());
				lVObject.setCmnLookupMstByNomnRelation(eisEmpNomineeDtlsVO.getCmnLookupMstByNomnRelation());
				lVObject.setFamilyMemberId(0l);
				if(eisEmpNomineeDtlsVO.getCmnAddressMstByNomnAddress()!=null){lVObject.setCmnAddressMstByNomnAddress(eisEmpNomineeDtlsVO.getCmnAddressMstByNomnAddress());}
			}
			lVObject.setCmnLookupMstByNomnBenefitTypeId(eisEmpNomineeDtlsVO.getCmnLookupMstByNomnBenefitTypeId());
			lVObject.setNomnSharePercent(eisEmpNomineeDtlsVO.getNomnSharePercent());			
			
			lVObject.setGuardianFirstname(eisEmpNomineeDtlsVO.getGuardianFirstname());
			lVObject.setGuardianLastname(eisEmpNomineeDtlsVO.getGuardianLastname());
			lVObject.setGuardianMiddlename(eisEmpNomineeDtlsVO.getGuardianMiddlename());					
			lVObject.setCmnLookupMstByGuardianRelation(eisEmpNomineeDtlsVO.getCmnLookupMstByGuardianRelation());
			lVObject.setGuardianRelationOther(eisEmpNomineeDtlsVO.getGuardianRelationOther());
						
			if(eisEmpNomineeDtlsVO.getCmnAddressMstByGuardianAddress()!=null){lVObject.setCmnAddressMstByGuardianAddress(eisEmpNomineeDtlsVO.getCmnAddressMstByGuardianAddress());}
			
			if("1".equals(draft)) {lVObject.setDraftFlag("Y");}
			else if("0".equals(draft))
			{ 	
				lVObject.setDraftFlag("N");			    			 			    						    							
			}
			lVObject.setActionFlag("P");
			lVObject.setRequestFlag("D");
			lVObject.setRowNumber(memberId);
			lVObject.setCmnLocationMst(cmnLocationMst);
			lVObject.setOrgUserMstByUserId(orgUserMst);
			lVObject.setCmnDatabaseMst(cmnDatabaseMst);
			lVObject.setOrgUserMstByCreatedBy(orgUserMst);
			lVObject.setOrgPostMstByCreatedByPost(orgPostMst);
			lVObject.setCreatedDate(new java.sql.Timestamp((new java.util.Date()).getTime()));
			lVObject.setOrgUserMstByUpdatedBy(orgUserMst);
			lVObject.setOrgPostMstByUpdatedByPost(orgPostMst);
			lVObject.setUpdatedDate(new java.sql.Timestamp((new java.util.Date()).getTime()));			
//			nmDtlsDAOImpl.create(lVObject);//Divyesh
			lVObject.setCheckStatus("Y");//Divyesh
			this.arHrEisNomineeDtlTxn.add(lVObject);		
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Creating a Request for delete Nominee dtls  in Service",e);
		}			
	}

	public ResultObject getPendingDtlsOnReqId(Map<String, Object> objectArgs)   //   300355
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{						
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			

			/* Get The Login Id of The Person */
			long userId = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
			 			
			/***End of getting a Login Id**/
			
			String strReqId=objectArgs.get("reqId").toString();
			long reqId= Long.parseLong(strReqId.trim());			
			NomineeDtlsDAO nomineeDtlsDAOImpl = new NomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
			List pendingDtlsLstObj = nomineeDtlsDAOImpl.getPendingDtlsOnReqId(reqId,orgUserMst);
			ListIterator li = pendingDtlsLstObj.listIterator();
			StringBuffer sbXML = new StringBuffer();	
			
			FamilyDtlsDAOImpl familyDtlsDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(li.hasNext())
			{
				HrEisNomineeDtlTxn hrEisEmpNomineeDtlsTrn = (HrEisNomineeDtlTxn) li.next();
				String str="No";
				if(hrEisEmpNomineeDtlsTrn.getNomnMinor().equals("Y")){str="Yes";}
				sbXML.append("<Minor>");
				sbXML.append(str);
				sbXML.append("</Minor>");
				
				sbXML.append("<Share>");
				sbXML.append(hrEisEmpNomineeDtlsTrn.getNomnSharePercent());
				sbXML.append("</Share>");
				
				
				if(hrEisEmpNomineeDtlsTrn.getFamilyMemberId()!=0)
				{
					List familyLstObj=familyDtlsDAOImpl.getTheFamilyMember(userId,hrEisEmpNomineeDtlsTrn.getFamilyMemberId());
					HrEisFamilyDtl hrEisEmpFamilyDtls = (HrEisFamilyDtl)familyLstObj.get(0);
					sbXML.append("<Name>");
					if(hrEisEmpFamilyDtls.getFmMiddleName() !=null && hrEisEmpFamilyDtls.getFmMiddleName().equals("")==false)
					{
						sbXML.append(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmMiddleName() +" " +hrEisEmpFamilyDtls.getFmLastName());				
					}
					else  {
						sbXML.append(hrEisEmpFamilyDtls.getFmFirstName() + " "+hrEisEmpFamilyDtls.getFmLastName());
					}
					sbXML.append("</Name>");
					
					String s=null;
					if(hrEisEmpFamilyDtls.getFmDateOfBirth()!=null){s=sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth());}					
					sbXML.append("<DOB>");
					sbXML.append(s);
					sbXML.append("</DOB>");
					
					sbXML.append("<Relation>");
					sbXML.append(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupDesc());
					sbXML.append("</Relation>");						
				}
				else
				{
					
					sbXML.append("<Name>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getNomnName());				
					sbXML.append("</Name>");
					
					String s=null;
					if(hrEisEmpNomineeDtlsTrn.getNomnDob()!=null){s=sdf.format(hrEisEmpNomineeDtlsTrn.getNomnDob());}					
					sbXML.append("<DOB>");
					sbXML.append(s);
					sbXML.append("</DOB>");
					
					sbXML.append("<Relation>");
					if(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation()!=null){sbXML.append(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation().getLookupDesc());}
					else{sbXML.append("null");}				
					sbXML.append("</Relation>");						
				}																				
				
				sbXML.append("<Request>");
				sbXML.append(hrEisEmpNomineeDtlsTrn.getRequestFlag());
				sbXML.append("</Request>");
			}
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();																					
			logger.info("tempXML is::" + tempStr);
			objectArgs.put("ajaxKey", tempStr);													
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");				
		}catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Showing a Nominee Pending dtls  in VOGEN",e);
		}
		return objRes;		
	}
		
	@SuppressWarnings("unchecked")
	public ResultObject getDraftDtlsOnReqId(Map<String, Object> objectArgs)   //   300355
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{						
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			

			/* Get The Login Id of The Person */		
			long userId = Long.parseLong(loginMap.get("userId").toString());
			long langId = Long.parseLong(loginMap.get("langId").toString());
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
			
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);						
			/***End of getting a Login Id**/			
			String strReqId=objectArgs.get("reqId").toString();
			long reqId= Long.parseLong(strReqId.trim());
			String flag=objectArgs.get("flag").toString();
			if("delete".equals(flag))
			{				
				NomineeDtlsDAO nomineeDtlsDAOImpl = new NomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
				List pendingDtlsLstObj = nomineeDtlsDAOImpl.getDraftDtlsOnReqId(reqId,orgUserMst);										
				ListIterator li = pendingDtlsLstObj.listIterator();
				StringBuffer sbXML = new StringBuffer();
				sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				while(li.hasNext())
				{
					HrEisNomineeDtlTxn hrEisEmpNomiDtlsTrn = (HrEisNomineeDtlTxn)li.next();	
										
					sbXML.append("<Link>");				
					sbXML.append(hrEisEmpNomiDtlsTrn.getRowNumber());
					sbXML.append("</Link>");					
					sbXML.append("<FamilyMem>");
					sbXML.append(hrEisEmpNomiDtlsTrn.getFamilyMemberId());
					sbXML.append("</FamilyMem>");
					
					nomineeDtlsDAOImpl.delete(hrEisEmpNomiDtlsTrn);					
				}
				sbXML.append("<DeleteDraft>");
				sbXML.append(reqId);
				sbXML.append("</DeleteDraft>");	
				String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();				 
				logger.info("tempXML for nominee delete  Dtls is:: " + tempStr);
				objectArgs.put("ajaxKey", tempStr);
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);							 	  		
				objRes.setViewName("ajaxData");	
			}
			else if("open".equals(flag))
			{				
				boolean setAddressFlag=false;
				NomineeDtlsDAO nomineeDtlsDAOImpl = new NomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
				List pendingDtlsLstObj = nomineeDtlsDAOImpl.getDraftDtlsOnReqId(reqId,orgUserMst);										
				ListIterator li = pendingDtlsLstObj.listIterator();								
				StringBuffer sbXML = new StringBuffer();
				CmnAddressMst cm = new CmnAddressMst();		
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 	
				
				while(li.hasNext())
				{
					HrEisNomineeDtlTxn hrEisEmpNomineeDtlsTrn = (HrEisNomineeDtlTxn)li.next();					
					HrEisNomineeDtlTxn lVObjNomineeDtls = new HrEisNomineeDtlTxn();
					HrEisFamilyDtl hrEisEmpFamilyDtls = new HrEisFamilyDtl();
					String lStrValidatestr="";
					lVObjNomineeDtls.setFamilyMemberId(hrEisEmpNomineeDtlsTrn.getFamilyMemberId());
					if(hrEisEmpNomineeDtlsTrn.getFamilyMemberId()!=0)
					{												
						FamilyDtlsDAOImpl familyDtlsDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
						List familyLstObj=familyDtlsDAOImpl.getTheFamilyMember(userId,hrEisEmpNomineeDtlsTrn.getFamilyMemberId());
						hrEisEmpFamilyDtls = (HrEisFamilyDtl)familyLstObj.get(0);
						if(hrEisEmpFamilyDtls.getFmMiddleName()!=null && hrEisEmpFamilyDtls.getFmMiddleName().trim().equalsIgnoreCase("")==false)
						{
							lVObjNomineeDtls.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmMiddleName() +" " +hrEisEmpFamilyDtls.getFmLastName() );
						}
						else
						{
							lVObjNomineeDtls.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmLastName() );
						}
						lVObjNomineeDtls.setNomnOtherRelation(hrEisEmpFamilyDtls.getFmRelationOther());
						
						String cmnLookupId= hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupName();												
						CmnLookupMst cmnLookupNomnRelation = new CmnLookupMst();
						cmnLookupNomnRelation.setLookupName(cmnLookupId);						
						lVObjNomineeDtls.setCmnLookupMstByNomnRelation(cmnLookupNomnRelation);
						
						Date d = StringUtility.convertStringToDate(sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth()));					
						lVObjNomineeDtls.setNomnDob(d);
						cm=hrEisEmpFamilyDtls.getCmnAddressMst();
						setAddressFlag=true;
					}
					else
					{						
						setAddressFlag=false;	
						lVObjNomineeDtls.setNomnName(hrEisEmpNomineeDtlsTrn.getNomnName());
						lVObjNomineeDtls.setNomnOtherRelation(hrEisEmpNomineeDtlsTrn.getNomnOtherRelation());
						
						if(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation()!=null)
						{
							String cmnLookupId= hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation().getLookupName();												
							CmnLookupMst cmnLookupNomnRelation = new CmnLookupMst();
							cmnLookupNomnRelation.setLookupName(cmnLookupId);					
							lVObjNomineeDtls.setCmnLookupMstByNomnRelation(cmnLookupNomnRelation);
						}
						if(hrEisEmpNomineeDtlsTrn.getNomnDob()!=null)
						{
							Date d = StringUtility.convertStringToDate(sdf.format(hrEisEmpNomineeDtlsTrn.getNomnDob()));					
							lVObjNomineeDtls.setNomnDob(d);
						}
					}
					HrEisNomineeDtlTxnId hDtlsId = new HrEisNomineeDtlTxnId();
					hDtlsId.setReqId(hrEisEmpNomineeDtlsTrn.getId().getReqId());
					hDtlsId.setMemberId(hrEisEmpNomineeDtlsTrn.getId().getMemberId());
					lVObjNomineeDtls.setId(hDtlsId);									
										
					if(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnBenefitTypeId()!=null)
					{
						String cmnLookupId= hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnBenefitTypeId().getLookupName();												
						CmnLookupMst cmnLookupNomnBenefit = new CmnLookupMst();
						cmnLookupNomnBenefit = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
						cmnLookupNomnBenefit.setLookupName(cmnLookupId);					
						lVObjNomineeDtls.setCmnLookupMstByNomnBenefitTypeId(cmnLookupNomnBenefit);									
					}
					
					if(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByGuardianRelation()!=null)
					{
						String cmnLookupId= hrEisEmpNomineeDtlsTrn.getCmnLookupMstByGuardianRelation().getLookupName();												
						CmnLookupMst cmnLookupGuardianRel = new CmnLookupMst();
						cmnLookupGuardianRel = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
						cmnLookupGuardianRel.setLookupName(cmnLookupId);										
						lVObjNomineeDtls.setCmnLookupMstByGuardianRelation(cmnLookupGuardianRel);
					}
					lVObjNomineeDtls.setRowNumber(hrEisEmpNomineeDtlsTrn.getRowNumber());
					lVObjNomineeDtls.setDraftFlag(hrEisEmpNomineeDtlsTrn.getDraftFlag());
					lVObjNomineeDtls.setActionFlag(hrEisEmpNomineeDtlsTrn.getActionFlag());					
					lVObjNomineeDtls.setGuardianFirstname(hrEisEmpNomineeDtlsTrn.getGuardianFirstname());
					lVObjNomineeDtls.setGuardianLastname(hrEisEmpNomineeDtlsTrn.getGuardianLastname());
					lVObjNomineeDtls.setGuardianMiddlename(hrEisEmpNomineeDtlsTrn.getGuardianMiddlename());
					lVObjNomineeDtls.setGuardianRelationOther(hrEisEmpNomineeDtlsTrn.getGuardianRelationOther());
					lVObjNomineeDtls.setNomnMinor(hrEisEmpNomineeDtlsTrn.getNomnMinor());
					
					lVObjNomineeDtls.setRequestFlag(hrEisEmpNomineeDtlsTrn.getRequestFlag());
					lVObjNomineeDtls.setNomnSharePercent(hrEisEmpNomineeDtlsTrn.getNomnSharePercent());
					if(setAddressFlag==true)
					{
						objectArgs.put("mode","ADD");																			
						//cm = AddressDelegate.setAddressObjectFields(cm,objectArgs); commented by sandip
						
						//added by sandip - start
						if(hrEisEmpFamilyDtls!=null && hrEisEmpFamilyDtls.getCmnAddressMst()!=null)
						{
							objectArgs.put("addrName", "setAddressFlag");
							objectArgs.put("addressObject", hrEisEmpFamilyDtls.getCmnAddressMst());
							objRes = serv.executeService(servDetails, objectArgs);
							Map addressMap = (Map) objectArgs.get("setAddressFlag");
							CmnAddressMst addressMst = (CmnAddressMst)addressMap.get("addressVO");
							lVObjNomineeDtls.setCmnAddressMstByNomnAddress(addressMst);
						}
						//added by sandip - end
					}
					else if(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByNomnAddress() != null)
					{
						objectArgs.put("mode","ADD");																			
						//cm = AddressDelegate.setAddressObjectFields(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByNomnAddress(),objectArgs); commented by sandip
						
						//added by sandip - start
						objectArgs.put("addrName", "NomnAddress");
						objectArgs.put("addressObject", hrEisEmpNomineeDtlsTrn.getCmnAddressMstByNomnAddress());
						objRes = serv.executeService(servDetails, objectArgs);
						Map addressMap = (Map) objectArgs.get("NomnAddress");
						CmnAddressMst addressMst = (CmnAddressMst)addressMap.get("addressVO");
						lVObjNomineeDtls.setCmnAddressMstByNomnAddress(addressMst);
						//added by sandip - end
						
					}
					else {cm=null;lStrValidatestr=lStrValidatestr+"eis.NomineeAddress,";}
					
					//if(cm!=null)
					//{						
						/*CmnAddressMst cm1 = new CmnAddressMst();														
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
						cm1.setStreet(cm.getStreet());*/
						
						/* commented by sandip - start
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
						
						if(cm.getCmnCountryMst()!=null)
						{
							CmnCountryMst cmnCountryMst = new CmnCountryMst();
							cmnCountryMst.setCountryCode(cm.getCmnCountryMst().getCountryCode());
							cm1.setCmnCountryMst(cmnCountryMst);								
						}
						if(cm.getCmnStateMst()!=null)
						{
							CmnStateMst cmnStateMst = new CmnStateMst();
							cmnStateMst.setStateCode(cm.getCmnStateMst().getStateCode());
							cm1.setCmnStateMst(cmnStateMst);
						}
						
						if(cm1.getCmnLookupMstAddTypeLookupid().getLookupName().toString().equalsIgnoreCase("City"))
						{
							CmnCityMst cmnCity = new CmnCityMst();
							cmnCity.setCityCode(cm.getCmnCityMst().getCityCode());
							cm1.setCmnCityMst(cmnCity);
						}
						else if("OtherRadioAddress".equalsIgnoreCase(cm.getCmnLookupMstAddTypeLookupid().getLookupName()))
						{
							cm1.setSocBuildName(cm.getSocBuildName());
							cm1.setArea(cm.getArea());
							cm1.setHouseName(cm.getHouseName());
							cm1.setStateName(cm.getStateName());
							cm1.setDistrictName(cm.getDistrictName());
							if(cm.getCmnDistrictMst()!=null)
							{								
								cm1.setCmnDistrictMst(cm.getCmnDistrictMst());
							}
							cm1.setTalukaName(cm.getTalukaName());
							cm1.setCityVilllageName(cm.getCityVilllageName());
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
						lVObjNomineeDtls.setCmnAddressMstByNomnAddress(cm1);
						commented by sandip - end*/
					//}
										
					if(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByGuardianAddress()!=null)
					{
						objectArgs.put("mode","ADD");																			
						//CmnAddressMst guardianAddress = AddressDelegate.setAddressObjectFields(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByGuardianAddress(),objectArgs);
						/*CmnAddressMst cm1 = new CmnAddressMst();														
						CmnLookupMst cmnLookupMst7 = guardianAddress.getCmnLookupMstAddTypeLookupid();
						CmnLookupMst cmnLookupMst = new CmnLookupMst();
						cmnLookupMst.setLookupName(cmnLookupMst7.getLookupName());
						cm1.setCmnLookupMstAddTypeLookupid(cmnLookupMst);
						cm1.setArea(guardianAddress.getArea());
						cm1.setMasterLookupid(guardianAddress.getMasterLookupid());
						cm1.setLangId(guardianAddress.getLangId());
						cm1.setLocId(guardianAddress.getLocId());
						cm1.setDbId(guardianAddress.getDbId());
												
						if(cm1.getCmnLookupMstAddTypeLookupid().getLookupName().toString().equalsIgnoreCase("City"))
						{
							CmnCityMst cmnCity = new CmnCityMst();
							cmnCity.setCityCode(guardianAddress.getCmnCityMst().getCityCode());
							cm1.setCmnCityMst(cmnCity);
						}
						else 
						{	
							if(guardianAddress.getCmnVillageMst()!=null)
							{	
								CmnVillageMst cmnVillageMst = new CmnVillageMst();
								cmnVillageMst.setVillageCode(guardianAddress.getCmnVillageMst().getVillageCode());
								cm1.setCmnVillageMst(cmnVillageMst);
							}
							if(guardianAddress.getCmnDistrictMst()!=null)								
							{
								CmnDistrictMst cmDistrictMst = new CmnDistrictMst();
								cmDistrictMst.setDistrictCode(guardianAddress.getCmnDistrictMst().getDistrictCode());
								cm1.setCmnDistrictMst(cmDistrictMst);
							}							
							if(guardianAddress.getCmnTalukaMst()!=null)
							{
								CmnTalukaMst cmnTalukaMst = new CmnTalukaMst();
								cmnTalukaMst.setTalukaCode(guardianAddress.getCmnTalukaMst().getTalukaCode());
								cm1.setCmnTalukaMst(cmnTalukaMst);
					    	}
						}														
						cm1.setFaliyu(guardianAddress.getFaliyu());
						cm1.setHouseName(guardianAddress.getHouseName());
						cm1.setImpLandmark(guardianAddress.getImpLandmark());
						cm1.setOtherDetails(guardianAddress.getOtherDetails());
						cm1.setPincode(guardianAddress.getPincode());
						cm1.setSocBuildName(guardianAddress.getSocBuildName());
						cm1.setStreet(guardianAddress.getStreet());	*/
						
						/* commented by sandip - start
						CmnAddressMst cm1 = new CmnAddressMst();														
						CmnLookupMst cmnLookupMst7 = guardianAddress.getCmnLookupMstAddTypeLookupid();
						CmnLookupMst cmnLookupMst = new CmnLookupMst();
						cmnLookupMst.setLookupName(cmnLookupMst7.getLookupName());
						cm1.setCmnLookupMstAddTypeLookupid(cmnLookupMst);
						cm1.setArea(guardianAddress.getArea());
						cm1.setMasterLookupid(guardianAddress.getMasterLookupid());
						cm1.setLangId(guardianAddress.getLangId());
						cm1.setLocId(guardianAddress.getLocId());
						cm1.setDbId(guardianAddress.getDbId());
						
						if(guardianAddress.getCmnCountryMst()!=null)
						{							
							CmnCountryMst cmnCountryMst = new CmnCountryMst();
							cmnCountryMst.setCountryCode(guardianAddress.getCmnCountryMst().getCountryCode());
							cm1.setCmnCountryMst(cmnCountryMst);								
						}
						if(guardianAddress.getCmnStateMst()!=null)
						{
							CmnStateMst cmnStateMst = new CmnStateMst();
							cmnStateMst.setStateCode(guardianAddress.getCmnStateMst().getStateCode());
							cm1.setCmnStateMst(cmnStateMst);
						}
						
						if(cm1.getCmnLookupMstAddTypeLookupid().getLookupName().toString().equalsIgnoreCase("City"))
						{
							CmnCityMst cmnCity = new CmnCityMst();
							cmnCity.setCityCode(guardianAddress.getCmnCityMst().getCityCode());
							cm1.setCmnCityMst(cmnCity);
						}
						else if("OtherRadioAddress".equalsIgnoreCase(guardianAddress.getCmnLookupMstAddTypeLookupid().getLookupName()))
						{
							cm1.setSocBuildName(guardianAddress.getSocBuildName());
							cm1.setArea(guardianAddress.getArea());
							cm1.setHouseName(guardianAddress.getHouseName());
							cm1.setStateName(guardianAddress.getStateName());
							cm1.setDistrictName(guardianAddress.getDistrictName());
							if(guardianAddress.getCmnDistrictMst()!=null)
							{								
								cm1.setCmnDistrictMst(guardianAddress.getCmnDistrictMst());
							}
							cm1.setTalukaName(guardianAddress.getTalukaName());
							cm1.setCityVilllageName(guardianAddress.getCityVilllageName());
						}
						else 
						{	
							if(guardianAddress.getCmnVillageMst()!=null)
							{	
								CmnVillageMst cmnVillageMst = new CmnVillageMst();
								cmnVillageMst.setVillageCode(guardianAddress.getCmnVillageMst().getVillageCode());
								cm1.setCmnVillageMst(cmnVillageMst);
							}
							if(guardianAddress.getCmnDistrictMst()!=null)								
							{
								CmnDistrictMst cmDistrictMst = new CmnDistrictMst();
								cmDistrictMst.setDistrictCode(guardianAddress.getCmnDistrictMst().getDistrictCode());
								cm1.setCmnDistrictMst(cmDistrictMst);
							}							
							if(guardianAddress.getCmnTalukaMst()!=null)
							{
								CmnTalukaMst cmnTalukaMst = new CmnTalukaMst();
								cmnTalukaMst.setTalukaCode(guardianAddress.getCmnTalukaMst().getTalukaCode());
								cm1.setCmnTalukaMst(cmnTalukaMst);
					    	}							
						}							
						cm1.setFaliyu(guardianAddress.getFaliyu());
						cm1.setHouseName(guardianAddress.getHouseName());
						cm1.setImpLandmark(guardianAddress.getImpLandmark());
						cm1.setOtherDetails(guardianAddress.getOtherDetails());
						cm1.setPincode(guardianAddress.getPincode());
						cm1.setSocBuildName(guardianAddress.getSocBuildName());
						cm1.setStreet(guardianAddress.getStreet());
						lVObjNomineeDtls.setCmnAddressMstByGuardianAddress(cm1);
						commented by sandip - end*/
						
						//added by sandip - start
						objectArgs.put("addrName", "NomnGrdnAddress");
						objectArgs.put("addressObject", hrEisEmpNomineeDtlsTrn.getCmnAddressMstByGuardianAddress());
						objRes = serv.executeService(servDetails, objectArgs);
						Map addressMap = (Map) objectArgs.get("NomnGrdnAddress");
						CmnAddressMst addressMst = (CmnAddressMst)addressMap.get("addressVO");
						lVObjNomineeDtls.setCmnAddressMstByGuardianAddress(addressMst);
						//added by sandip - end
					}
					
					sbXML.append("<RemoveRow>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getRowNumber());
					sbXML.append("</RemoveRow>");
					
					sbXML.append("<Minor>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getNomnMinor());
					sbXML.append("</Minor>");
					
					sbXML.append("<Share>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getNomnSharePercent());
					sbXML.append("</Share>");
					
					sbXML.append("<Name>");
					if(setAddressFlag==true && hrEisEmpFamilyDtls.getFmMiddleName()!=null && hrEisEmpFamilyDtls.getFmMiddleName().equals("")==false){sbXML.append(hrEisEmpFamilyDtls.getFmFirstName()+" "+ hrEisEmpFamilyDtls.getFmMiddleName()+" "+ hrEisEmpFamilyDtls.getFmLastName());}
					else if(setAddressFlag==true){sbXML.append(hrEisEmpFamilyDtls.getFmFirstName()+" "+ hrEisEmpFamilyDtls.getFmLastName());}
					else 
					{						
						if(hrEisEmpNomineeDtlsTrn.getNomnName()==null || hrEisEmpNomineeDtlsTrn.getNomnName().equals("")==true){lStrValidatestr=lStrValidatestr+"eis.nominee_name,";sbXML.append("null");}
						else {sbXML.append(hrEisEmpNomineeDtlsTrn.getNomnName());}
					}								
					sbXML.append("</Name>");
					
					sbXML.append("<DOB>");
					if(setAddressFlag==true){sbXML.append(sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth()));}
					else if(hrEisEmpNomineeDtlsTrn.getNomnDob()!=null){sbXML.append(sdf.format(hrEisEmpNomineeDtlsTrn.getNomnDob()));}
					else{sbXML.append("null");lStrValidatestr=lStrValidatestr+"eis.DATE_OF_BIRTH,";}
					sbXML.append("</DOB>");
										
					sbXML.append("<Relation>");				
					if(setAddressFlag==true){
						CmnLookupMst cmnLookupGuardianRel = cmnLookupMstDAOImpl.getLookUpVOByLookUpIDAndLang(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupId(), langId);
						sbXML.append(cmnLookupGuardianRel.getLookupDesc());
					}
					else if (hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation()!=null){
						CmnLookupMst cmnLookupGuardianRel = cmnLookupMstDAOImpl.getLookUpVOByLookUpIDAndLang(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation().getLookupId(), langId);
						sbXML.append(cmnLookupGuardianRel.getLookupDesc());
					}
					else{sbXML.append("null");lStrValidatestr=lStrValidatestr+"eis.NomineeGuardianRel,";}
					sbXML.append("</Relation>");
					
					sbXML.append("<Request>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getRequestFlag());
					sbXML.append("</Request>");
					
					sbXML.append("<FamilyMemeber>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getFamilyMemberId());
					sbXML.append("</FamilyMemeber>");					
					String strXMLObj = FileUtility.voToXmlFileByXStream(lVObjNomineeDtls);
					
					if(hrEisEmpNomineeDtlsTrn.getNomnMinor().equalsIgnoreCase("Y"))
					{
						if(hrEisEmpNomineeDtlsTrn.getGuardianFirstname().equalsIgnoreCase("") || hrEisEmpNomineeDtlsTrn.getGuardianFirstname()==null)
						{
							lStrValidatestr=lStrValidatestr+"eis.GuardianFName,";
						}					
						if(hrEisEmpNomineeDtlsTrn.getGuardianLastname().equalsIgnoreCase("") || hrEisEmpNomineeDtlsTrn.getGuardianLastname()==null)
						{
							lStrValidatestr=lStrValidatestr+"eis.GuardianLName,";
						}
						if(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByGuardianAddress()==null)
						{
							lStrValidatestr=lStrValidatestr+"eis.GuardianAddress,";
						}
						if(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByGuardianRelation()==null)
						{
							lStrValidatestr=lStrValidatestr+"eis.GuardianAddress,";
						}
					}
					
					if(lStrValidatestr.equalsIgnoreCase("")==false)
					{
						strXMLObj=strXMLObj+"$/$"+lStrValidatestr;
					}
					sbXML.append("<XMLFile>");
					sbXML.append(strXMLObj);
					sbXML.append("</XMLFile>");
										
				}
				sbXML.append("<DeleteDraft>");
				sbXML.append(reqId);
				sbXML.append("</DeleteDraft>");	
				String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();				 
				logger.info("tempXML for Nominee open Dtls is:: " + tempStr);
				objectArgs.put("ajaxKey", tempStr);
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);							 	  		
				objRes.setViewName("ajaxData");	
			}
			else if("view".equals(flag))
			{				
				NomineeDtlsDAO nomineeDtlsDAOImpl = new NomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());				
				List pendingDtlsLstObj = nomineeDtlsDAOImpl.getDraftDtlsOnReqId(reqId,orgUserMst);								
				ListIterator li = pendingDtlsLstObj.listIterator();
				StringBuffer sbXML = new StringBuffer();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				FamilyDtlsDAOImpl familyDtlsDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
				
				while(li.hasNext())
				{
					HrEisNomineeDtlTxn hrEisEmpNomineeDtlsTrn = (HrEisNomineeDtlTxn) li.next();
					
					sbXML.append("<Minor>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getNomnMinor());
					sbXML.append("</Minor>");
					
					sbXML.append("<Share>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getNomnSharePercent());
					sbXML.append("</Share>");
					
					
					if(hrEisEmpNomineeDtlsTrn.getFamilyMemberId()!=0)
					{
						List familyLstObj=familyDtlsDAOImpl.getTheFamilyMember(userId,hrEisEmpNomineeDtlsTrn.getFamilyMemberId());
						HrEisFamilyDtl hrEisEmpFamilyDtls = (HrEisFamilyDtl)familyLstObj.get(0);
																	
						sbXML.append("<Name>");	
						if(hrEisEmpFamilyDtls.getFmMiddleName() != null && hrEisEmpFamilyDtls.getFmMiddleName().trim().equals("")==false){
							sbXML.append(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmMiddleName() +" " +hrEisEmpFamilyDtls.getFmLastName());				
						}
						else
						{
							sbXML.append(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmLastName());
						}
						sbXML.append("</Name>");
												
						sbXML.append("<DOB>");
						sbXML.append(sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth()));
						sbXML.append("</DOB>");
						
						if(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation()!=null)
						{
							sbXML.append("<Relation>");
							CmnLookupMst cmnLookupGuardianRel = cmnLookupMstDAOImpl.getLookUpVOByLookUpIDAndLang(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupId(), langId);
							sbXML.append(cmnLookupGuardianRel.getLookupDesc());
							sbXML.append("</Relation>");						
						}
						else
						{
							sbXML.append("<Relation>");
							sbXML.append("null");
							sbXML.append("</Relation>");	
						}
					}
					else
					{									
						sbXML.append("<Name>");
						if(hrEisEmpNomineeDtlsTrn.getNomnName()==null || hrEisEmpNomineeDtlsTrn.getNomnName().equals("")==true){sbXML.append("null");}
						else {sbXML.append(hrEisEmpNomineeDtlsTrn.getNomnName());}						
						sbXML.append("</Name>");
												
						sbXML.append("<DOB>");
						if(hrEisEmpNomineeDtlsTrn.getNomnDob()!=null){sbXML.append(sdf.format(hrEisEmpNomineeDtlsTrn.getNomnDob()));}
						else{sbXML.append("null");}
						sbXML.append("</DOB>");
						
						sbXML.append("<Relation>");
						if(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation()!=null) {
							CmnLookupMst cmnLookupGuardianRel = cmnLookupMstDAOImpl.getLookUpVOByLookUpIDAndLang(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation().getLookupId(), langId);
							sbXML.append(cmnLookupGuardianRel.getLookupDesc());}
						else {sbXML.append("null");}
						sbXML.append("</Relation>");						
					}															
					sbXML.append("<Request>");
					sbXML.append(hrEisEmpNomineeDtlsTrn.getRequestFlag());
					sbXML.append("</Request>");									
				}
				String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();																					
				logger.info("tempXML  Nominee   is::" + tempStr);
				objectArgs.put("ajaxKey", tempStr);													
				objRes.setResultCode(ErrorConstants.SUCCESS);			
				objRes.setResultValue(objectArgs);
				objRes.setViewName("ajaxData");	
			}
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
