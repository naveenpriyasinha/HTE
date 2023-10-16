package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDao;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
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
import com.tcs.sgv.eis.dao.EmpPreEmplDtlsDAO;
import com.tcs.sgv.eis.dao.EmpPreEmplDtlsDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisPreEmplDtl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.dao.CmnOrganizationMstDao;
import com.tcs.sgv.hod.common.dao.CmnOrganizationMstDaoImpl;
import com.tcs.sgv.hod.common.delegate.AddressDelegate;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;
import com.tcs.sgv.hod.common.valueobject.CmnOrganizationMst;

public class EmpPreEmplDtlsServiceImpl extends ServiceImpl implements EmpPreEmplDtlsService
{
	private static final Log logger = LogFactory.getLog(EmpPreEmplDtlsServiceImpl.class);
	
	public ResultObject getEmpPreEmplDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{	
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			
			boolean blnWorkFlowEnabled = true;
			if (objectArgs.get("blnWorkFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
			}
			logger.info("blnWorkFlowEnabled in service ====="+blnWorkFlowEnabled);
			
			long userId=0;
			if(blnWorkFlowEnabled) 
			{
				userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			}
			else
			{
				userId = StringUtility.convertToLong(objectArgs.get("userId").toString());
			}
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			List<CmnLookupMst> arTypeOfOrgInfo = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("OrgType",langId);
			
			List<CmnLookupMst> arIsContinue = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("yes/no_srvc", langId);
							
			EmpPreEmplDtlsDAO PreEmplDtlsDAOImpl=new EmpPreEmplDtlsDAOImpl(HrEisPreEmplDtl.class, serv.getSessionFactory());
			List<HrEisPreEmplDtl> PreEmplVOList=PreEmplDtlsDAOImpl.getPreEmplDtlVOList(userId);
			
			HrEisPreEmplDtl objPreEmpl=new HrEisPreEmplDtl();
			ArrayList xmlFileNmPreEmpl = new ArrayList();
			List jobProfileList = new ArrayList();
			
			for (Iterator j = PreEmplVOList.iterator(); j.hasNext();)
			{
				objPreEmpl =(HrEisPreEmplDtl)j.next();	
				
				if(objPreEmpl.getCmnOrganizationMst()!=null)
				{	
					objPreEmpl.getCmnOrganizationMst().getOrganizationName();
					objPreEmpl.getCmnOrganizationMst().getOrganizationShortName();
					if(objPreEmpl.getCmnOrganizationMst().getCmnLookupMst() != null && objPreEmpl.getCmnOrganizationMst().getCmnLookupMst().getLookupName()!= null)
					{	
						String strLookupname=objPreEmpl.getCmnOrganizationMst().getCmnLookupMst().getLookupName();
						objPreEmpl.getCmnOrganizationMst().setCmnLookupMst(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLookupname, langId));
					}
				}
				
				if(objPreEmpl.getDateOfJoining() != null)
				{	
					objPreEmpl.getDateOfJoining();
				}
				if(objPreEmpl.getDateOfReleving() != null)
				{	
					objPreEmpl.getDateOfReleving();
				}
				if(objPreEmpl.getDesignation() != null)
				{	
					objPreEmpl.getDesignation();
				}
				if(objPreEmpl.getJobProfile() != null)
				{
					objPreEmpl.getJobProfile();
				}
				if(objPreEmpl.getRemarks() != null)
				{
					objPreEmpl.getRemarks();
				}
				if(objPreEmpl.getDurationYears() != null)
				{
					objPreEmpl.setDurationYears(objPreEmpl.getDurationYears());
				}
				
				if(objPreEmpl.getDurationMonths() != null)
				{
					objPreEmpl.setDurationMonths(objPreEmpl.getDurationMonths());
				}
				
				if(objPreEmpl.getDurationDays() != null)
				{
					objPreEmpl.setDurationDays(objPreEmpl.getDurationDays());
				}
				
				String strIscontinue = objPreEmpl.getCmnLookupMstByIsContinue() != null ?objPreEmpl.getCmnLookupMstByIsContinue().getLookupName() : "";
				if(!"".equals(strIscontinue))
				{
					objPreEmpl.setCmnLookupMstByIsContinue(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strIscontinue, langId));
				}
				
				/**Address  Start*/					
				/* commented by sandip - start
				if(objPreEmpl.getCmnOrganizationMst()!=null && objPreEmpl.getCmnOrganizationMst().getCmnAddressMst() != null)
				{	
					CmnAddressMst objCmnAddressMst =  objPreEmpl.getCmnOrganizationMst().getCmnAddressMst();
						
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
					//till here
				}
				commented by sandip -end*/
				
				//added by sandip - start
				if(objPreEmpl!=null && objPreEmpl.getCmnOrganizationMst()!=null)
				{
					FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
					FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
					objectArgs.put("addrName", "preEmployerAddress1");
					objectArgs.put("addressObject", objPreEmpl.getCmnOrganizationMst().getCmnAddressMst());
					objRes = serv.executeService(servDetails, objectArgs);
				}
				//added by sandip - end
				if (objPreEmpl.getJobProfile() != null)
				{
					jobProfileList.add(objPreEmpl.getJobProfile().replaceAll("\r\n","<br>"));
				}
				
				String tempPreEmpl = FileUtility.voToXmlFileByXStream(objPreEmpl);
				xmlFileNmPreEmpl.add(tempPreEmpl);
			}
			
			if (userId != 0)
			{
				if (!blnWorkFlowEnabled)
				{
					objectArgs.put("EmpInfo_userId",userId);
					objectArgs.put("EmpInfo_PayFix",true);
				}
				
				objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
			} 
            
			objectArgs.put("arIsContinue", arIsContinue);
			objectArgs.put("xmlFileNmPreEmpl", xmlFileNmPreEmpl);
			objectArgs.put("PreEmplVOList", PreEmplVOList);
			objectArgs.put("arTypeOfOrgInfo", arTypeOfOrgInfo);
			objectArgs.put("blnWorkFlowEnabled",blnWorkFlowEnabled); 
			objectArgs.put("selectedUserId", userId);//for multiple users
			objectArgs.put("jobProfileList", jobProfileList);
			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			if(blnWorkFlowEnabled)
			{
				objRes.setViewName("EmpProfessionalDetl");	
			}
			else
			{
				objRes.setViewName("WorkFlowDisableEmpProfessionalDetl");
			}
		} 
		catch (Exception e) 
	    {  
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getEmpPreEmplDtls method in EmpPreEmplDetlsSErviceImpl Service",e);
	    }
	return objRes;
	}
	
	public ResultObject savePreEmplDtlsInDB(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		boolean blnWorkFlowEnabled = true;
		if (objectArgs.get("blnWorkFlowEnabled") != null)
		{
			blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
		}
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());	
				
				long userId=0;
				if(blnWorkFlowEnabled) 
				{
					userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				}
				else
				{
					userId = StringUtility.convertToLong(objectArgs.get("userId").toString());
				}
				
				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
				/*End of the geting user id Code*/
				
				/*  Get The Person Post */			 			 	    	 
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
				/*End of the geting Person Post Code*/	    	 	    		    	

				/* Getting a DB ID*/								 
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/
			
				/* Getting a Loc ID Code */
				long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
				/* End of Loc ID */
				
				/* Generatting a Lang Id code */
				CmnLanguageMstDao objCmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
				CmnLanguageMst objCmnLanguageMst = objCmnLanguageMstDao.read(langId);
				
				
				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnOrganizationMstDao OrganizationMstDaoImpl =new CmnOrganizationMstDaoImpl(CmnOrganizationMst.class,serv.getSessionFactory());
				EmpPreEmplDtlsDAO PreEmplDtlsDAOImpl=new EmpPreEmplDtlsDAOImpl(HrEisPreEmplDtl.class,serv.getSessionFactory());
				//CmnOrganizationMstDao addressMstDtlsDAOImpl=CmnOrganizationMstDaoImpl(CmnAddressMst.class,serv.getSessionFactory());
				
				CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				List<HrEisPreEmplDtl> lstHrEisEmpPreEmplDtlsVO =(List<HrEisPreEmplDtl>) objectArgs.get("lstHrEisEmpPreEmplDtlsVO");
				HrEisPreEmplDtl objEisEmpPreEmplDtls = new HrEisPreEmplDtl();
				CmnOrganizationMst objCmnOrganizationMst = null;
				Date currDate = new java.util.Date();
				List updatedPreEmplVOList = (List) objectArgs.get("updatedPreEmplVOList");
				List deletedPreEmplVOList = (List) objectArgs.get("deletedPreEmplVOList");
				if(deletedPreEmplVOList!=null)
				{
				  ListIterator litr = deletedPreEmplVOList.listIterator();
				  while(litr.hasNext())
				  {	
					objEisEmpPreEmplDtls=(HrEisPreEmplDtl)litr.next();
					if(objEisEmpPreEmplDtls.getEmpPreemplPk()!=0)
					{
						HrEisPreEmplDtl readobHrEisEmpPreEmplDtls=(HrEisPreEmplDtl)PreEmplDtlsDAOImpl.read(objEisEmpPreEmplDtls.getEmpPreemplPk());
						PreEmplDtlsDAOImpl.delete(readobHrEisEmpPreEmplDtls);
					}
				  } 
				}
				if(updatedPreEmplVOList!=null)
				{				
					ListIterator litr = updatedPreEmplVOList.listIterator();
					while(litr.hasNext())
					{	
						objEisEmpPreEmplDtls=(HrEisPreEmplDtl)litr.next();
						
						objCmnOrganizationMst = objEisEmpPreEmplDtls.getCmnOrganizationMst();
						
						if(objEisEmpPreEmplDtls.getEmpPreemplPk()!=0)
						{
							HrEisPreEmplDtl readobHrEisEmpPreEmplDtls=(HrEisPreEmplDtl)PreEmplDtlsDAOImpl.read(objEisEmpPreEmplDtls.getEmpPreemplPk());
							if (objEisEmpPreEmplDtls.getCmnOrganizationMst() != null && objEisEmpPreEmplDtls.getCmnOrganizationMst().getOrganizationId() != 0)
							{
								CmnOrganizationMst readobCmnOrganizationMst=(CmnOrganizationMst)OrganizationMstDaoImpl.read(objEisEmpPreEmplDtls.getCmnOrganizationMst().getOrganizationId());
								
								
								/*Generateing a pk  for CMN_ADDRESS_MST*/	
								long lngaddressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
								/*end of req Id code***/
								 
								 if(objCmnOrganizationMst.getCmnAddressMst()!=null)
					    		 {								
									 objectArgs.put("mode","ADD");
					    			 CmnAddressMst objCmnAddressMst = objCmnOrganizationMst.getCmnAddressMst();
					    			 objCmnAddressMst.setAddressId(lngaddressId);
					    			 objCmnAddressMst = AddressDelegate.setAddressObjectFields(objCmnAddressMst,objectArgs);
					    			 objCmnOrganizationMst.setCmnAddressMst(objCmnAddressMst); 
					    			 OrganizationMstDaoImpl.create(objCmnAddressMst);	
					    			 readobCmnOrganizationMst.setCmnAddressMst(objCmnAddressMst);
					    		 }	
								readobCmnOrganizationMst.setOrganizationName(objEisEmpPreEmplDtls.getCmnOrganizationMst().getOrganizationName());
								readobCmnOrganizationMst.setOrganizationShortName(objEisEmpPreEmplDtls.getCmnOrganizationMst().getOrganizationShortName());
								readobCmnOrganizationMst.setCmnLookupMst(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objEisEmpPreEmplDtls.getCmnOrganizationMst().getCmnLookupMst().getLookupName(), langId));
								readobCmnOrganizationMst.setOrgPostMstByUpdatedByPost(orgPostMst);
								readobCmnOrganizationMst.setOrgUserMstByUpdatedBy(orgUserMst);
								readobCmnOrganizationMst.setUpdatedDate(currDate);
								OrganizationMstDaoImpl.update(readobCmnOrganizationMst);
							}
							readobHrEisEmpPreEmplDtls.setDateOfJoining(objEisEmpPreEmplDtls.getDateOfJoining());
							readobHrEisEmpPreEmplDtls.setDateOfReleving(objEisEmpPreEmplDtls.getDateOfReleving());
							readobHrEisEmpPreEmplDtls.setDesignation(objEisEmpPreEmplDtls.getDesignation());
							readobHrEisEmpPreEmplDtls.setJobProfile(objEisEmpPreEmplDtls.getJobProfile());
							readobHrEisEmpPreEmplDtls.setRemarks(objEisEmpPreEmplDtls.getRemarks());
							/*Start Change done by sunil on 31/05/08*/
							
							if(objEisEmpPreEmplDtls.getCmnLookupMstByIsContinue()!=null && !objEisEmpPreEmplDtls.getCmnLookupMstByIsContinue().getLookupName().equals(""))
							{
								readobHrEisEmpPreEmplDtls.setCmnLookupMstByIsContinue(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(objEisEmpPreEmplDtls.getCmnLookupMstByIsContinue().getLookupName(), langId));
							}
							
							readobHrEisEmpPreEmplDtls.setDurationYears(objEisEmpPreEmplDtls.getDurationYears());
							readobHrEisEmpPreEmplDtls.setDurationMonths(objEisEmpPreEmplDtls.getDurationMonths());
							readobHrEisEmpPreEmplDtls.setDurationDays(objEisEmpPreEmplDtls.getDurationDays());
							
							/*Ends Change done by sunil on 31/05/08*/
							readobHrEisEmpPreEmplDtls.setOrgPostMstByUpdatedPost(orgPostMst);
							readobHrEisEmpPreEmplDtls.setOrgUserMstByUpdatedBy(orgUserMst);
							readobHrEisEmpPreEmplDtls.setUpdatedDate(currDate);
							PreEmplDtlsDAOImpl.update(readobHrEisEmpPreEmplDtls);
						}
					}	
				}
				
				for(Iterator i = lstHrEisEmpPreEmplDtlsVO.iterator(); i.hasNext();)
				{
					objEisEmpPreEmplDtls = (HrEisPreEmplDtl)i.next();
										
					// start code to genetate pk
					long lOrganizationId = IDGenerateDelegate.getNextId("cmn_organization_mst", objectArgs);
					 //start code to genetate pk
					objCmnOrganizationMst = objEisEmpPreEmplDtls.getCmnOrganizationMst();
					objCmnOrganizationMst.setOrganizationId(lOrganizationId);
					objCmnOrganizationMst.setCmnLookupMst(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objCmnOrganizationMst.getCmnLookupMst().getLookupName(), langId));
					objCmnOrganizationMst.setOrganizationName(objCmnOrganizationMst.getOrganizationName());
					objCmnOrganizationMst.setOrganizationShortName(objCmnOrganizationMst.getOrganizationShortName());
					
				    //start to save address in db.................
					
					/*Generateing a pk  for CMN_ADDRESS_MST*/	
					long lngaddressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
					/*end of req Id code***/
					
					 if(objCmnOrganizationMst.getCmnAddressMst()!=null)
		    		 {								
		    			 objectArgs.put("mode","ADD");
		    			 CmnAddressMst objCmnAddressMst = objCmnOrganizationMst.getCmnAddressMst();
		    			 objCmnAddressMst.setAddressId(lngaddressId);
		    			 objCmnAddressMst = AddressDelegate.setAddressObjectFields(objCmnAddressMst,objectArgs);
		    			 objCmnOrganizationMst.setCmnAddressMst(objCmnAddressMst); 
		    			 OrganizationMstDaoImpl.create(objCmnAddressMst);					
		    		 }	
					
					objCmnOrganizationMst.setCmnLanguageMst(objCmnLanguageMst);
					objCmnOrganizationMst.setOrgUserMstByCreatedBy(orgUserMst);
					objCmnOrganizationMst.setOrgPostMstByCreatedByPost(orgPostMst);
					objCmnOrganizationMst.setCreatedDate(currDate);
					objCmnOrganizationMst.setCmnDatabaseMst(cmnDatabaseMst);
					objCmnOrganizationMst.setCmnLocationMst(cmnLocationMst);
					OrganizationMstDaoImpl.create(objCmnOrganizationMst);
					//start of insert into db for hr_eis_emp_pre_empl_dtls
					//start of generating pk 
					long preEmplId = IDGenerateDelegate.getNextId("hr_eis_preempl_dtl", objectArgs);
					
					if (blnWorkFlowEnabled) 
					{
						objEisEmpPreEmplDtls.setOrgUserMstByUserId(orgUserMst);
					}
					else
					{
						objEisEmpPreEmplDtls.setOrgUserMstByUserId(orgUserMst);
					}
					
					//end of generating pk 
					objEisEmpPreEmplDtls.setEmpPreemplPk(preEmplId);
					objEisEmpPreEmplDtls.setCmnOrganizationMst(objCmnOrganizationMst);
					
					/*Start Change done by sunil on 31/05/08*/
					
					if(objEisEmpPreEmplDtls.getCmnLookupMstByIsContinue()!=null && !objEisEmpPreEmplDtls.getCmnLookupMstByIsContinue().getLookupName().equals(""))
					{
						objEisEmpPreEmplDtls.setCmnLookupMstByIsContinue(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(objEisEmpPreEmplDtls.getCmnLookupMstByIsContinue().getLookupName(), langId));
					}
					
					objEisEmpPreEmplDtls.setDurationDays(objEisEmpPreEmplDtls.getDurationDays());
					objEisEmpPreEmplDtls.setDurationDays(objEisEmpPreEmplDtls.getDurationMonths());
					objEisEmpPreEmplDtls.setDurationDays(objEisEmpPreEmplDtls.getDurationYears());
					
					/*Ends Change done by sunil on 31/05/08*/
					
					objEisEmpPreEmplDtls.setOrgUserMstByCreatedBy(orgUserMst);
					objEisEmpPreEmplDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					objEisEmpPreEmplDtls.setCmnDatabaseMst(cmnDatabaseMst);
					objEisEmpPreEmplDtls.setCreatedDate(currDate);
					objEisEmpPreEmplDtls.setCmnLocationMst(cmnLocationMst);
					PreEmplDtlsDAOImpl.create(objEisEmpPreEmplDtls);
			   }
				objectArgs.put("blnWorkFlowEnabled",blnWorkFlowEnabled);
				//end of save
				this.getEmpPreEmplDtls(objectArgs);
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
				
				if(blnWorkFlowEnabled)
				{
					objRes.setViewName("EmpProfessionalDetl");	
				}
				else
				{
					objRes.setViewName("WorkFlowDisableEmpProfessionalDetl");
				}
			}	
		}
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling savePreEmplDtlsInDB method in EmpPreEmplDetlsSErviceImpl Service",e);
		}
		return objRes;
	}	
}
