package com.tcs.sgv.eis.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnCountryMstDAOImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
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
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl_WF;
import com.tcs.sgv.eis.dao.EmpInfoDAO_WF;
import com.tcs.sgv.eis.dao.FamilyDtlsDAO;
import com.tcs.sgv.eis.dao.FamilyDtlsDAOImpl;
import com.tcs.sgv.eis.dao.NomineeDtlsDAO;
import com.tcs.sgv.eis.dao.NomineeDtlsDAOImpl;
import com.tcs.sgv.eis.dao.getEmpEngLoginId;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxn;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxnId;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtl;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.dao.AddressDAO;
import com.tcs.sgv.hod.common.dao.AddressDAOImpl;
import com.tcs.sgv.hod.common.delegate.AddressDelegate;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;
import com.tcs.sgv.wf.interfaces.WFConstants;

public class FamilyDetailsServiceImpl extends ServiceImpl implements FamilyDetailsService 
{
	private static final Log logger = LogFactory.getLog(FamilyDetailsServiceImpl.class);
	public final static long EMP_FAMILY_MODID=300007;
	public final static String DOC_ID="300007";
	public final static String CORR_DESC="Family Details Correspondence";
	List<HrEisFamilyDtlTxn> arHrEisFamilyDtlTxn = new ArrayList<HrEisFamilyDtlTxn>(); 

	public ResultObject getFamilyDetails(Map<String,Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			
//			 IFMS - Starts
			boolean blnWorkFlowEnabled = true;
			if (objectArgs.get("blnWorkFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
			}
			    logger.info("blnWorkFlowEnabled In Service ===================="+blnWorkFlowEnabled);
			   
			    objectArgs.put("blnWorkFlowEnabled",blnWorkFlowEnabled); 
			    long iUserId = (objectArgs.get("userId") != null && !objectArgs.get("userId").toString().equals("")) ? Long.parseLong(objectArgs.get("userId").toString()) : 0;
			    objectArgs.put("userId",iUserId); 
			    
//			 IFMS - Ends
			
			String lStrFlag = objectArgs.get("flag").toString();
			objRes = getComboDtls(objectArgs,blnWorkFlowEnabled,iUserId);
			if ("getComboDetails".equalsIgnoreCase(lStrFlag)) {
					if(blnWorkFlowEnabled){							//IFMS
						logger.info("Inside before getFamilyInfo====================== " );
						objRes = getFamilyInfo(objectArgs);
					}
					else{
						logger.info("Inside before getEmployeeFamilyVOList====================== " );
						objRes=getEmployeeFamilyVOList(objectArgs,blnWorkFlowEnabled,iUserId);
					}
			} 
			else if ("SubmitFamilyDtls".equalsIgnoreCase(lStrFlag)) {
				if (blnWorkFlowEnabled)
				{
					objRes = submitFamilyDtls(objectArgs);
				}
				else
				{
					logger.info("For WorkFlow Disable ==========================");
					objRes = saveEmpFamilyDtls(objectArgs,blnWorkFlowEnabled, iUserId);//IFMS
				}
			}
			else if("selEmpType".equalsIgnoreCase(lStrFlag))// Change By sunil on 04/06/08 for Employment Dtls 
			{
				String cmbid=objectArgs.get("cmbid").toString();				
				objRes=getSelEmpTypeComboDtls(objectArgs,cmbid);
			}
			else if("getFamilyPendingRecordForView".equalsIgnoreCase(lStrFlag)) {
				String strReqId =objectArgs.get("reqId").toString();
				long reqId = Long.parseLong(strReqId.trim());
				objRes = getFamilyPendingRecordForView(objectArgs,reqId,1);
			}
			else if("getFamilyDraftRecordForView".equalsIgnoreCase(lStrFlag))
			{
				String strReqId =objectArgs.get("reqId").toString();
				long reqId = Long.parseLong(strReqId.trim());
				objRes = getFamilyPendingRecordForView(objectArgs,reqId,2);
			}
			else if("deleteFamilyDraftRecord".equalsIgnoreCase(lStrFlag))
			{
				String strReqId =objectArgs.get("reqId").toString();
				long reqId = Long.parseLong(strReqId.trim());				
				objRes = deleteDraftRequestDtls(objectArgs,reqId);
			}
			else if ("openFamilyDraftRequest".equalsIgnoreCase(lStrFlag))
			{
				String strReqId =objectArgs.get("reqId").toString();
				long reqId = Long.parseLong(strReqId.trim());				
				objRes = openDraftRequestDtls(objectArgs,reqId);
			}
		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getFamilyDetails method in FamilyDetailsServiceImpl Service",e);
		}
		return objRes;
	}				
	
	
	/**
	 * This Method is used for getting all family existing Records
	 * @param objectArgs,blnWorkFlowEnabled,iUserId
	 * @return objRes
	 */
	private ResultObject getEmployeeFamilyVOList(Map<String, Object> objectArgs,boolean blnWorkFlowEnabled, long iUserId)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("Inside After getEmployeeFamilyVOList====================== " );
		try {
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			
				
		
		 OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		 
		 /*  Get The selected user Person's User Id*/
		OrgUserMst selectedOrgUserMst = null;
		if (!blnWorkFlowEnabled)
		{
			selectedOrgUserMst = orgUserMstDaoImpl.read(iUserId);
		}
		 
		FamilyDtlsDAO FamilyDAO = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
		
		List<HrEisFamilyDtl> hrEisEmpfamilyVOList = FamilyDAO.getEmpFamilyMaxMemberID(selectedOrgUserMst);
		
		CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
		request.getSession().removeAttribute("attachmentBiometricFamily_EDITVO");
		
		List<String> checkDependRecOnNomineeLstObj = new ArrayList<String>();
		ArrayList xmlFileName = new ArrayList();
		ListIterator litr= hrEisEmpfamilyVOList.listIterator();
		HrEisFamilyDtl hrFamilyDtls=new HrEisFamilyDtl();
		
		int iCounter = 0;
		
		CmnCountryMstDAOImpl cmnCountryMstDAOImpl = new CmnCountryMstDAOImpl(CmnCountryMst.class,serv.getSessionFactory());//change by sunil for nationality
		OrgDepartmentMstDaoImpl departmentMstDaoImpl = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());// Change By sunil on 04/06/08 for Employment Dtls
		FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
		NomineeDtlsDAOImpl nomineeDtlsDAOImpl = new NomineeDtlsDAOImpl(HrEisNomineeDtl.class,serv.getSessionFactory());

		while(litr.hasNext())
		{
			
			hrFamilyDtls =(HrEisFamilyDtl)litr.next();
			if(hrFamilyDtls!=null)
			{
				iCounter++;	
				if(hrFamilyDtls.getMemberId() != 0){hrFamilyDtls.getMemberId();}
				else{ hrFamilyDtls.setMemberId(0);}
				
				String strFmFirstName = hrFamilyDtls.getFmFirstName() != null ? hrFamilyDtls.getFmFirstName() : "";
				hrFamilyDtls.setFmFirstName(strFmFirstName);
				
				String strFmLastName = hrFamilyDtls.getFmLastName() != null ? hrFamilyDtls.getFmLastName() : "";
				hrFamilyDtls.setFmLastName(strFmLastName);
				
				String strFmMiddleName = hrFamilyDtls.getFmMiddleName() != null ? hrFamilyDtls.getFmMiddleName() : "";
				hrFamilyDtls.setFmMiddleName(strFmMiddleName);
				
				String strFmRelationLookupName = hrFamilyDtls.getCmnLookupMstByFmRelation() != null ? hrFamilyDtls.getCmnLookupMstByFmRelation().getLookupName() : "";
				
				if(!"".equals(strFmRelationLookupName))
				{
					hrFamilyDtls.setCmnLookupMstByFmRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmRelationLookupName, langId));
				}
				
				String strFmRelationOther = hrFamilyDtls.getFmRelationOther() != null ? hrFamilyDtls.getFmRelationOther() : "";
				hrFamilyDtls.setFmRelationOther(strFmRelationOther);
				
				String strFmGenderLookupName = hrFamilyDtls.getCmnLookupMstByFmGender() != null ? hrFamilyDtls.getCmnLookupMstByFmGender().getLookupName() : "";
				
				if(!"".equals(strFmGenderLookupName))
				{
					hrFamilyDtls.setCmnLookupMstByFmGender(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmGenderLookupName, langId));
				}
				
				hrFamilyDtls.getFmDateOfBirth();
				
				String strFmDeadOrAliveLookupName = hrFamilyDtls.getCmnLookupMstByFmDeadOrAlive() != null ? hrFamilyDtls.getCmnLookupMstByFmDeadOrAlive().getLookupName() : "";
				
				if(!"".equals(strFmDeadOrAliveLookupName))
				{
					hrFamilyDtls.setCmnLookupMstByFmDeadOrAlive(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmDeadOrAliveLookupName, langId));
				}
				
				hrFamilyDtls.getDependentOrNot();
				hrFamilyDtls.getDateOfDemise();
				
				String strMaritalStatusLookupName = hrFamilyDtls.getCmnLookupMstByFmMaritalStatus() != null ? hrFamilyDtls.getCmnLookupMstByFmMaritalStatus().getLookupName() : "";
				
				if(!"".equals(strMaritalStatusLookupName))
				{
					hrFamilyDtls.setCmnLookupMstByFmMaritalStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strMaritalStatusLookupName, langId));
				}
				
				String strFmQualificationLookupName = hrFamilyDtls.getCmnLookupMstByFmQualification() != null ? hrFamilyDtls.getCmnLookupMstByFmQualification().getLookupName() : "";
				
				if(!"".equals(strFmQualificationLookupName))
				{
					hrFamilyDtls.setCmnLookupMstByFmQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmQualificationLookupName, langId));
				}
				
				String strFmSubQualificationLookupName = hrFamilyDtls.getCmnLookupMstBySubQualification() != null ? hrFamilyDtls.getCmnLookupMstBySubQualification().getLookupName() : "";
				if(!"".equals(strFmSubQualificationLookupName))
				{
					hrFamilyDtls.setCmnLookupMstBySubQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmSubQualificationLookupName, langId));
				}

				String strFmDicsiplineLookupName = hrFamilyDtls.getCmnLookupMstByDiscipline() != null ? hrFamilyDtls.getCmnLookupMstByDiscipline().getLookupName() : "";
				if(!"".equals(strFmDicsiplineLookupName))
				{
					hrFamilyDtls.setCmnLookupMstByDiscipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmDicsiplineLookupName, langId));
				}
				
				String strFmEmploymentStatusLookupName = hrFamilyDtls.getCmnLookupMstByFmEmploymentStatus() != null ? hrFamilyDtls.getCmnLookupMstByFmEmploymentStatus().getLookupName() : "";
				
				if(!"".equals(strFmEmploymentStatusLookupName))
				{
					hrFamilyDtls.setCmnLookupMstByFmEmploymentStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmEmploymentStatusLookupName, langId));
				}
					
				String strFmOccupationLookupName = hrFamilyDtls.getCmnLookupMstByFmOccupation() != null ? hrFamilyDtls.getCmnLookupMstByFmOccupation().getLookupName() : "select";
				
				if(!"".equals(strFmOccupationLookupName))
				{
					hrFamilyDtls.setCmnLookupMstByFmOccupation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmOccupationLookupName, langId));
				}
				
				if(hrFamilyDtls.getCmnCountryMstByFmNationality()!=null && !hrFamilyDtls.getCmnCountryMstByFmNationality().getCountryCode().equals(""))
				{
					hrFamilyDtls.setCmnCountryMstByFmNationality(cmnCountryMstDAOImpl.getCountryVOByCountryCodeAndLangId(hrFamilyDtls.getCmnCountryMstByFmNationality().getCountryCode(), langId));
				}

				if(hrFamilyDtls.getOrgDepartmentMstByFmDept()!=null && !hrFamilyDtls.getOrgDepartmentMstByFmDept().getDepCode().equals(""))
				{
					hrFamilyDtls.setOrgDepartmentMstByFmDept(departmentMstDaoImpl.getDepartmentVOByDepartmentCodeAndLangId(hrFamilyDtls.getOrgDepartmentMstByFmDept().getDepCode(), langId));
				}
				
				hrFamilyDtls.getAnnualIncome();
				hrFamilyDtls.getCompanyName();
				hrFamilyDtls.getDesignation();
				hrFamilyDtls.getFmRemarks();
				
				/**Address  Start*/					
				/* commented by sandip - start
				if(hrFamilyDtls.getCmnAddressMst()!=null)
				{	
					CmnAddressMst objCmnAddressMst =  hrFamilyDtls.getCmnAddressMst();
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
				if(hrFamilyDtls!=null)
				{
					FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
					objectArgs.put("addrName", "FamilyAddress");
					objectArgs.put("addressObject", hrFamilyDtls.getCmnAddressMst());
					objRes = serv.executeService(servDetails, objectArgs);
				}
				//added by sandip - end
				
				if(hrFamilyDtls.getCmnAttachmentMst()!=null)
				{
					CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(hrFamilyDtls.getCmnAttachmentMst().getAttachmentId());
					EisWFmessageDisplay.removeAttachmentFromSession(request.getSession(),"attachmentBiometricFamily",iCounter);
					request.getSession().setAttribute("attachmentBiometricFamily"+iCounter+"_EDITVO", cmnAttachmentMst);
					hrFamilyDtls.setCmnAttachmentMst(cmnAttachmentMst);
				}
				
				xmlFileName.add(FileUtility.voToXmlFileByXStream(hrFamilyDtls));
				

				/** Start getting Record That are Nominated */
			
				List nomineeLstObj = new ArrayList();
				nomineeLstObj = nomineeDtlsDAOImpl.getNomineeRecordCheckWithFamilyRec(iUserId,hrFamilyDtls.getMemberId());					

				if(nomineeLstObj.isEmpty()==false)
				{
					checkDependRecOnNomineeLstObj.add("deleteYes");
				}
				else
				{
					checkDependRecOnNomineeLstObj.add("deleteNo");
				}
				/** End getting Record That are Nominated */
			}
		}	
		
		objectArgs.put("xmlFilePathNameForMulAddRec", xmlFileName);
		objectArgs.put("hrEisEmpFamilyVOList", hrEisEmpfamilyVOList);
		
		logger.info("-========== successfuly executed =============");
		logger.info("-========== xmlFilePathNameForMulAddRec ============="+ xmlFileName);
		
		if (iUserId != 0)
		{	
			if (!blnWorkFlowEnabled)
			{
				objectArgs.put("EmpInfo_userId",iUserId);
				objectArgs.put("EmpInfo_PayFix",true);
			}
			objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
		}
		
		/** Start getting Record That are Nominated */
		Iterator itr=checkDependRecOnNomineeLstObj.iterator();
		while(itr.hasNext())
		{
			logger.info("itr.next();======="+itr.next());
		}
		logger.info("checkDependRecOnNomineeLstObj=======Size====="+checkDependRecOnNomineeLstObj.size());
		objectArgs.put("checkDependRecOnNomineeLstObj", checkDependRecOnNomineeLstObj);
		
		/** End getting Record That are Nominated */
		
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("WorkFlowDisableFamilyDetails");
		
	}catch (Exception e)
	{			
		objRes.setThrowable(e);
		objRes.setResultCode(ErrorConstants.ERROR);
		objRes.setResultValue(objectArgs);
		logger.error("Exception while getting  Family Dtls in Service ",e);
	}
		return objRes;	
	}

	/**This Method is used to submit Data in DataBase
	 *@param objectArgs,blnWorkFlowEnabled,iUserId
	 *@return objRes
	 */
	private ResultObject saveEmpFamilyDtls(Map<String, Object> objectArgs,boolean blnWorkFlowEnabled, long iUserId) {
	
	logger.info("Inside Save Family Details======================");
	
	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	
	try {
		
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			
		
			/* Get The Login Id of The Person */
			long userID = Long.parseLong(loginMap.get("userId").toString());
			getEmpEngLoginId g=  new getEmpEngLoginId(OrgEmpMst.class,serv.getSessionFactory());
			long empID = g.getEmpEnglishLoginId(userID);
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			orgEmpMst.setEmpId(empID);
			
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			
//			 IFMS - starts
			 /*  Get The selected user Person's User Id*/
			OrgUserMst selectedOrgUserMst = null;
			if (!blnWorkFlowEnabled)
			{
				selectedOrgUserMst = orgUserMstDaoImpl.read(iUserId);
			}
			/*End of the geting Person ID Code*/
			/* IFMS - ends */

			/* End of the geting Person ID Code */

			/* Get The Person Post */
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			/* End of the geting Person Post Code */

			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
			/* End og DB Id code */

			/* Getting a Lang Id Code */
			long langId = Long.parseLong(loginMap.get("langId").toString());
			/* End of Lang ID Code */

			/* Getting a Loc ID Code */
			long locId = Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			/* End of Loc ID */
			/* Find The Maximum Srno To Enter The Record */
		
			logger.info("For WorkFlow Disable==========================");
			FamilyDtlsDAO FamilyDaoImplObjMst = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class, serv.getSessionFactory());
			
			 logger.info("FOR DB UPDATION IN SERVICE=====================");
			 
    		 FamilyDtlsDAO FamilyDaoImplObj = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class, serv.getSessionFactory());
    		 CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
    		 AddressDAO addressDao = new AddressDAOImpl(CmnAddressMst.class, serv.getSessionFactory());
    		 
    		 CmnCountryMstDAOImpl cmnCountryMstDAOImpl = new CmnCountryMstDAOImpl(CmnCountryMst.class,serv.getSessionFactory());//change by sunil for nationality
 			 OrgDepartmentMstDaoImpl departmentMstDaoImpl = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());// Change By sunil on 04/06/08 for Employment Dtls
    		 
			 List upDatedVOList= (List) objectArgs.get("upDatedVOList");
			 List deletedVOList= (List) objectArgs.get("deletedVOList");	
			 
			 String[] enc_rowNumForAttachment =(String[])objectArgs.get("enc_rowNumForAttachment");//for Insert
			 String []  addedPunch_rowNumForAttachment = (String[]) objectArgs.get("addedPunch_rowNumForAttachment");//for update
			 logger.info("addedPunch_rowNumForAttachment"+addedPunch_rowNumForAttachment.length);
			 
			
			 //logger.info("upDatedVOList.size()===================="+upDatedVOList.size());
			 HrEisFamilyDtl eisEmpFamilyDtls = null;
			 Date currDate  = new Date();
			 
			 if(upDatedVOList!=null)
			 { 
				 ListIterator liForUpdateVO = upDatedVOList.listIterator();
				 int j=0;//change
				 while(liForUpdateVO.hasNext())
				 {				 
					 //HrEisFamilyDtl eisEmpFamilyDtls =new HrEisFamilyDtl();
					 eisEmpFamilyDtls =(HrEisFamilyDtl)liForUpdateVO.next();
					 if(eisEmpFamilyDtls.getMemberId()!=0)
					 {
						 logger.info("Updateing Existing Record in DB........................" + eisEmpFamilyDtls.getMemberId());
						 long memId= eisEmpFamilyDtls.getMemberId();
						 HrEisFamilyDtl updateHrEisEmpFamilyDtls=(HrEisFamilyDtl)FamilyDaoImplObj.read(memId);
						
						 logger.info("eisEmpFamilyDtls.getMemberId()======="+eisEmpFamilyDtls.getMemberId());
						 logger.info("eisEmpFamilyDtls.getFmFirstName()======="+eisEmpFamilyDtls.getFmFirstName());
						
						 updateHrEisEmpFamilyDtls.setFmFirstName(eisEmpFamilyDtls.getFmFirstName());
						 updateHrEisEmpFamilyDtls.setFmLastName(eisEmpFamilyDtls.getFmLastName());
						 updateHrEisEmpFamilyDtls.setFmMiddleName(eisEmpFamilyDtls.getFmMiddleName());
						 updateHrEisEmpFamilyDtls.setFmDateOfBirth(eisEmpFamilyDtls.getFmDateOfBirth());
						 updateHrEisEmpFamilyDtls.setFmRelationOther(eisEmpFamilyDtls.getFmRelationOther());
						 updateHrEisEmpFamilyDtls.setFmRemarks(eisEmpFamilyDtls.getFmRemarks());
						 
						 /** Start For AttachMent Updation */ 
	
						 String rowNumber  =  addedPunch_rowNumForAttachment[j];//change
						 logger.info("addedPunch_rowNumForAttachment[j];"+addedPunch_rowNumForAttachment[j]+"  RowNo"+j);
						 objectArgs.put("rowNumber", rowNumber);
				    	 objectArgs.put("attachmentName", "attachmentBiometricFamily"); 
						 ResultObject resultObj1 = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
						 objectArgs = (Map) resultObj1.getResultValue();			
						 Long lAttachId= null;
						 ResultObject resultObjAttch = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
						 objectArgs = (Map) resultObjAttch.getResultValue();
						 lAttachId = (Long) objectArgs.get("AttachmentId_attachmentBiometricFamily");
						 logger.info("Updated AttachmentId===="+lAttachId);
						 
						 if(lAttachId!=null)
				    	 {
				    			CmnAttachmentMst cmnAttachmentMst = new CmnAttachmentMst();
								cmnAttachmentMst.setAttachmentId(lAttachId);
								updateHrEisEmpFamilyDtls.setCmnAttachmentMst(cmnAttachmentMst);
				    	 }	
				    	else
				    	{
				    		updateHrEisEmpFamilyDtls.setCmnAttachmentMst(eisEmpFamilyDtls.getCmnAttachmentMst());
				    	}	
						 /** Ends For AttachMent Updation */ 
									 
						 /* Address - starts */
						 
						 /*Generateing a Req_Id  for CMN_ADDRESS_MST*/	
							long addressId = 0;
							addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
							logger.info("Inserting known MamberID PK..... " + addressId);
				   		 /*end of req Id code***/		
							//logger.info("eisEmpFamilyDtls.getCmnAddressMst()========"+eisEmpFamilyDtls.getCmnAddressMst().getArea());
			    		 if(eisEmpFamilyDtls.getCmnAddressMst()!=null && addressId!=0)
			    		 {								
			    			 objectArgs.put("mode","ADD");
			    			 CmnAddressMst cm = eisEmpFamilyDtls.getCmnAddressMst();
			    			 cm.setAddressId(addressId);
			    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
			    			 logger.info("cm==========================="+cm.getAddressId());
			    			 addressDao.create(cm);
			    			 updateHrEisEmpFamilyDtls.setCmnAddressMst(cm);
			    		 }
			    		 else
			    		 {
			    			 updateHrEisEmpFamilyDtls.setCmnAddressMst(null);
			    		 }
						 /* Address - ends */
						 
			    		 //updateHrEisEmpFamilyDtls.setCmnAddressMst(eisEmpFamilyDtls.getCmnAddressMst());
						 updateHrEisEmpFamilyDtls.setOtherOccupation(eisEmpFamilyDtls.getOtherOccupation());
						 
						 if(eisEmpFamilyDtls.getCmnLookupMstByFmGender()!=null)
						 {
							 updateHrEisEmpFamilyDtls.setCmnLookupMstByFmGender(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstByFmGender().getLookupName(), langId));
						 }
		
						 if(eisEmpFamilyDtls.getCmnLookupMstByFmDeadOrAlive()!=null)
						 {
							 updateHrEisEmpFamilyDtls.setCmnLookupMstByFmDeadOrAlive(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstByFmDeadOrAlive().getLookupName(), langId));
						 }
						
						 if(eisEmpFamilyDtls.getCmnLookupMstByFmEmploymentStatus()!=null)
						 {
							 updateHrEisEmpFamilyDtls.setCmnLookupMstByFmEmploymentStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstByFmEmploymentStatus().getLookupName(), langId));
						 }
				
						 if(eisEmpFamilyDtls.getCmnLookupMstByFmMaritalStatus()!=null)
						 {
							 updateHrEisEmpFamilyDtls.setCmnLookupMstByFmMaritalStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstByFmMaritalStatus().getLookupName(), langId));
						 }
			
						 if(eisEmpFamilyDtls.getCmnLookupMstByFmOccupation()!=null)
						 {
							 updateHrEisEmpFamilyDtls.setCmnLookupMstByFmOccupation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstByFmOccupation().getLookupName(), langId));
						 }
						
						 if(eisEmpFamilyDtls.getCmnLookupMstByFmQualification()!=null)
						 {
							 updateHrEisEmpFamilyDtls.setCmnLookupMstByFmQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstByFmQualification().getLookupName(), langId));
						 }
						 
						 if(eisEmpFamilyDtls.getCmnLookupMstBySubQualification()!=null)
						 {
							updateHrEisEmpFamilyDtls.setCmnLookupMstBySubQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstBySubQualification().getLookupName(), langId));
						 }

 						 if(eisEmpFamilyDtls.getCmnLookupMstByDiscipline()!=null)
 						 {
							updateHrEisEmpFamilyDtls.setCmnLookupMstByDiscipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstByDiscipline().getLookupName(), langId));
 						 }
						 
						 if(eisEmpFamilyDtls.getCmnLookupMstByFmRelation()!=null)
						 {
							 updateHrEisEmpFamilyDtls.setCmnLookupMstByFmRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpFamilyDtls.getCmnLookupMstByFmRelation().getLookupName(), langId));
						 }
						 
						 if(eisEmpFamilyDtls.getCmnCountryMstByFmNationality()!=null && !eisEmpFamilyDtls.getCmnCountryMstByFmNationality().getCountryCode().equals("") )//change by sunil for nationality
						 {
							updateHrEisEmpFamilyDtls.setCmnCountryMstByFmNationality(cmnCountryMstDAOImpl.getCountryVOByCountryCodeAndLangId(eisEmpFamilyDtls.getCmnCountryMstByFmNationality().getCountryCode(), langId));
						 }

						 if(eisEmpFamilyDtls.getOrgDepartmentMstByFmDept()!=null && !eisEmpFamilyDtls.getOrgDepartmentMstByFmDept().getDepCode().equals(""))// Change By sunil on 04/06/08 for Employment Dtls
						 {
							 updateHrEisEmpFamilyDtls.setOrgDepartmentMstByFmDept(departmentMstDaoImpl.getDepartmentVOByDepartmentCodeAndLangId(eisEmpFamilyDtls.getOrgDepartmentMstByFmDept().getDepCode(), langId));
						 }
						 else
						 {
							 updateHrEisEmpFamilyDtls.setOrgDepartmentMstByFmDept(eisEmpFamilyDtls.getOrgDepartmentMstByFmDept());
						 }
					 
						 updateHrEisEmpFamilyDtls.setDateOfDemise(eisEmpFamilyDtls.getDateOfDemise());
						 updateHrEisEmpFamilyDtls.setAnnualIncome(eisEmpFamilyDtls.getAnnualIncome());
						 updateHrEisEmpFamilyDtls.setCompanyName(eisEmpFamilyDtls.getCompanyName());
						 updateHrEisEmpFamilyDtls.setDesignation(eisEmpFamilyDtls.getDesignation());// Change By sunil on 04/06/08 for Employment Dtls
									 
						 updateHrEisEmpFamilyDtls.setCmnDatabaseMst(cmnDatabaseMst);
						 updateHrEisEmpFamilyDtls.setCmnLocationMst(cmnLocationMst);
						 updateHrEisEmpFamilyDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						 updateHrEisEmpFamilyDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						 updateHrEisEmpFamilyDtls.setUpdatedDate(currDate);
						 
						 FamilyDaoImplObj.update(updateHrEisEmpFamilyDtls);
						 j++;
					}
				 }
			 }
			 
			 //Logical Deleition
			 ListIterator liForDeletVO = deletedVOList.listIterator();
			 while(liForDeletVO.hasNext())
			 {
				 //HrEisFamilyDtl eisEmpFamilyDtls =new HrEisFamilyDtl();
				 eisEmpFamilyDtls =(HrEisFamilyDtl)liForDeletVO.next();
				 logger.info("Deleting  Existing Record in DB........................" + eisEmpFamilyDtls.getMemberId());
				 HrEisFamilyDtl updateHrEisEmpFamilyDtls=(HrEisFamilyDtl)FamilyDaoImplObj.read(eisEmpFamilyDtls.getMemberId());
				 updateHrEisEmpFamilyDtls.setDeleteFlag("Y");
				 
				 updateHrEisEmpFamilyDtls.setCmnDatabaseMst(cmnDatabaseMst);
				 updateHrEisEmpFamilyDtls.setCmnLocationMst(cmnLocationMst);							 
				 updateHrEisEmpFamilyDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
				 updateHrEisEmpFamilyDtls.setOrgUserMstByUpdatedBy(orgUserMst);
			
				 FamilyDaoImplObj.update(updateHrEisEmpFamilyDtls);
				 //FamilyDaoImplObj.delete(updateHrEisEmpFamilyDtls);
			 }
			 
//INSERT RECORD
			 
			 HrEisFamilyDtl lVObject = new HrEisFamilyDtl();
			 String[] txnXML = (String[]) objectArgs.get("encXML");			
			 List lstlockupTxnVO = FileUtility.xmlFilesToVoByXStream(txnXML);
			 
			//	String[] enc_rowNumForAttachment =(String[])objectArgs.get("enc_rowNumForAttachment");
				logger.info("enc_rowNumForAttachment  :   " + enc_rowNumForAttachment.length);
				int i1=0;
			 for (Iterator i = lstlockupTxnVO.iterator(); i.hasNext();) {
				 Object Obj = i.next();
		    	 if(Obj != null)
	    		 {
			    	 Class<? extends Object> c=Obj.getClass();				    	 
			    	 if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisFamilyDtl"))
			    	 {			    		 
			    		 lVObject = (HrEisFamilyDtl) Obj;
			    		 
			    /*Generateing a Req_Id  for HR_EIS_EMP_FAMILY_DTLS*/	
							long reqId = 0;
							reqId = IDGenerateDelegate.getNextId("hr_eis_family_dtl", objectArgs);
							logger.info("Inserting known MamberID PK..... " + reqId);
				/*end of req Id code***/		
						 lVObject.setMemberId(reqId);
						 
			    		 if(lVObject.getCmnLookupMstByFmGender()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmGender(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmGender().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmDeadOrAlive()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmDeadOrAlive(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmDeadOrAlive().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmEmploymentStatus()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmEmploymentStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmEmploymentStatus().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmMaritalStatus()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmMaritalStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmMaritalStatus().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmQualification()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmQualification().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstBySubQualification()!=null)
			    		 {
							lVObject.setCmnLookupMstBySubQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstBySubQualification().getLookupName(), langId));
			    		 }
						 if(lVObject.getCmnLookupMstByDiscipline()!=null)
						 {
							lVObject.setCmnLookupMstByDiscipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByDiscipline().getLookupName(), langId));
						 }
			    		 if(lVObject.getCmnLookupMstByFmRelation()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmRelation().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmOccupation()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmOccupation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmOccupation().getLookupName(), langId));			    		 
			    		 }
			    		 if(lVObject.getCmnCountryMstByFmNationality()!=null && !lVObject.getCmnCountryMstByFmNationality().getCountryCode().equals(""))//change by sunil for Nationality
			    		 {
							lVObject.setCmnCountryMstByFmNationality(cmnCountryMstDAOImpl.getCountryVOByCountryCodeAndLangId(lVObject.getCmnCountryMstByFmNationality().getCountryCode(), langId));
			    		 }
			    		 if(lVObject.getOrgDepartmentMstByFmDept()!=null && !lVObject.getOrgDepartmentMstByFmDept().getDepCode().equals(""))// Change By sunil on 04/06/08 for Employment Dtls
			    		 {
			    			 lVObject.setOrgDepartmentMstByFmDept(departmentMstDaoImpl.getDepartmentVOByDepartmentCodeAndLangId(lVObject.getOrgDepartmentMstByFmDept().getDepCode(), langId));
			    		 }
							
			    		 lVObject.setDeleteFlag("N");
			    		 
			    		 lVObject.setOtherOccupation(lVObject.getOtherOccupation());

//For Attachment	    		 
			    		 String rowNumber  =  enc_rowNumForAttachment[i1];
					     objectArgs.put("rowNumber", rowNumber);
					     objectArgs.put("attachmentName", "attachmentBiometricFamily"); 
					     ResultObject resultObj1 = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
					     objectArgs = (Map) resultObj1.getResultValue();			
					     Long lAttachId= null;
					     ResultObject resultObjAttch = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
					     objectArgs = (Map) resultObjAttch.getResultValue();
//					     logger.info("{objectArgs  :   " + objectArgs + "\n");
					     lAttachId = (Long) objectArgs.get("AttachmentId_attachmentBiometricFamily");
//					     logger.info("lAttachId  :  "+ lAttachId);
					     if(lAttachId!=null)
					     {
					    	 CmnAttachmentMst cmnAttachmentMst = new CmnAttachmentMst();						    	 
					    	 cmnAttachmentMst.setAttachmentId(lAttachId);
					    	 lVObject.setCmnAttachmentMst(cmnAttachmentMst);
					     }	
					     else
					     {
					    	 lVObject.setCmnAttachmentMst(null);
					     }
					     
	//End Attachment		    		 
			    		 lVObject.setCmnLocationMst(cmnLocationMst);
			    		 lVObject.setOrgUserMstByUserId(selectedOrgUserMst);
			    		 lVObject.setCmnDatabaseMst(cmnDatabaseMst);
			    		 lVObject.setOrgUserMstByCreatedBy(orgUserMst);
			    		 lVObject.setOrgPostMstByCreatedByPost(orgPostMst);
			    		 lVObject.setCreatedDate(currDate);
			    		  
			    		 /*Generateing a Req_Id  for CMN_ADDRESS_MST*/	
							long addressId = 0;
							addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
							logger.info("Inserting known MamberID PK..... " + addressId);
				   		 /*end of req Id code***/			    		 
			    		 if(lVObject.getCmnAddressMst()!=null)
			    		 {								
			    			 objectArgs.put("mode","ADD");
			    			 CmnAddressMst cm = lVObject.getCmnAddressMst();
			    			 cm.setAddressId(addressId);
			    			 
			    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
			    						    			
			    			 lVObject.setCmnAddressMst(cm); 
			    			 FamilyDaoImplObjMst.create(cm);					
			    		 }	
			    		 logger.info("getCmnLookupMstByFmRelation   "  +lVObject.getCmnLookupMstByFmRelation());
			    		 lVObject.setTrnCounter(Integer.valueOf(1));
			    		 FamilyDaoImplObjMst.create(lVObject);					
				/*End for Address*/			
			
			    	 }i1++;
	    		 }
		}
		logger.info("Execution is Successful=======================");
		logger.info("Ins Save Data iUserId======"+iUserId);
		
		//Start Change 
			objRes=getEmployeeFamilyVOList(objectArgs,blnWorkFlowEnabled,iUserId);
			objRes = getComboDtls(objectArgs,blnWorkFlowEnabled,iUserId);
		//End Change
			
		objRes.setResultValue(objectArgs);						
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("WorkFlowDisableFamilyDetails");				//Change
	} 
	catch (Exception e) {		
		objRes.setThrowable(e);
		objRes.setResultCode(ErrorConstants.ERROR);
		objRes.setResultValue(objectArgs);
		logger.error("Exception while Calling saveEmpFamilyDtls method in FamilyDetailsServiceImpl Service",e);	
	}
	return objRes;
}	




	public ResultObject openDraftRequestDtls(Map<String, Object> objectArgs, long reqId) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{									
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");					 	        	
				Map loginMap = (Map) objectArgs.get("baseLoginMap");				
				 				
				 /*  Get The Login Id of The Person*/
				 getEmpEngLoginId g=  new getEmpEngLoginId(OrgEmpMst.class,serv.getSessionFactory());	
				 long userId = Long.parseLong(loginMap.get("userId").toString());
				 
				 long EmpID = g.getEmpEnglishLoginId(userId);
				 OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
				 OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
				 OrgEmpMst orgEmpMst = new OrgEmpMst();
				 orgEmpMst.setEmpId(EmpID);			 	    	 
				 
		    	 /*End of the geting Person ID Code*/				
				 long request_ID = 1;				 
				 List<Object> addDraftDtlsLstObj = new ArrayList<Object>();
				 StringBuffer sbXML = new StringBuffer();											 
				 request_ID=reqId;					 				 				 				
				 FamilyDtlsDAO fMDaoImplObj = new FamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
				 List tempDraftData=fMDaoImplObj.addDraftDtls(orgUserMst,request_ID);				 				
				 ListIterator removeLstItr = tempDraftData.listIterator();
				 while(removeLstItr.hasNext())
				 {
					 HrEisFamilyDtlTxn row = (HrEisFamilyDtlTxn)removeLstItr.next(); 
				 	 sbXML.append("<RemoveRow>");
			    	 sbXML.append(row.getRowNumber());
					 sbXML.append("</RemoveRow>");
				 }
				 sbXML.append("<ReqId>");
				 sbXML.append(request_ID);
				 sbXML.append("</ReqId>");
				 
				 Iterator it = tempDraftData.iterator();
				 while(it.hasNext())
				 {
					 addDraftDtlsLstObj.add(it.next());					
				 }				 				
			     if(!addDraftDtlsLstObj.isEmpty())
				 {												 						 				    	 	
						ListIterator itr= addDraftDtlsLstObj.listIterator();			 			
						HrEisFamilyDtlTxn hrEisEmpFmDtlsTrn=null;	
						HrEisFamilyDtlTxn FmObj = new HrEisFamilyDtlTxn();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					    while(itr.hasNext()) 
					    {
					    	hrEisEmpFmDtlsTrn = (HrEisFamilyDtlTxn)itr.next();						    													   
							String lStrValidatestr="";
							
							if(hrEisEmpFmDtlsTrn.getFmLastName()==null || hrEisEmpFmDtlsTrn.getFmLastName().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.LAST_NAME,";}
							FmObj.setFmLastName(hrEisEmpFmDtlsTrn.getFmLastName());	
					    	FmObj.setFmMiddleName(hrEisEmpFmDtlsTrn.getFmMiddleName());
							
					    	if(hrEisEmpFmDtlsTrn.getFmFirstName()==null || hrEisEmpFmDtlsTrn.getFmFirstName().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.FIRST_NAME,";}
					    	FmObj.setFmFirstName(hrEisEmpFmDtlsTrn.getFmFirstName());							
							FmObj.setFmRemarks(hrEisEmpFmDtlsTrn.getFmRemarks());
							FmObj.setFmRelationOther(hrEisEmpFmDtlsTrn.getFmRelationOther());
							
							/**Seting a Id**/
							HrEisFamilyDtlTxnId hrEisEmpFamilyDtlsTrnId= new HrEisFamilyDtlTxnId();
							hrEisEmpFamilyDtlsTrnId.setMemberId(hrEisEmpFmDtlsTrn.getId().getMemberId());
							hrEisEmpFamilyDtlsTrnId.setReqId(hrEisEmpFmDtlsTrn.getId().getReqId());
							FmObj.setId(hrEisEmpFamilyDtlsTrnId);
							/**End of Setting ID*/
							
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation()!=null)
							{	
									if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation().getLookupName().equalsIgnoreCase("Select")){lStrValidatestr=lStrValidatestr+"eis.Relation,";}
									CmnLookupMst cmnLookupMst2 = new CmnLookupMst();									
									cmnLookupMst2.setLookupName(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation().getLookupDesc());
									FmObj.setCmnLookupMstByFmRelation(cmnLookupMst2);
							}
							else{lStrValidatestr=lStrValidatestr+"eis.Relation,";}
							
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender()!=null)
							{
								if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender().getLookupName().equalsIgnoreCase("Select")){lStrValidatestr=lStrValidatestr+"eis.GENDER,";}	
								CmnLookupMst cmnLookupMst3 = new CmnLookupMst();
								cmnLookupMst3.setLookupName(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender().getLookupDesc());							
								FmObj.setCmnLookupMstByFmGender(cmnLookupMst3);								
							}
							else{lStrValidatestr=lStrValidatestr+"eis.GENDER,";}
							
							if(hrEisEmpFmDtlsTrn.getFmDateOfBirth()!=null)
							{
								Date setd= StringUtility.convertStringToDate(sdf.format(hrEisEmpFmDtlsTrn.getFmDateOfBirth()));
								FmObj.setFmDateOfBirth(setd);
							}	
							else{lStrValidatestr=lStrValidatestr+"eis.DATE_OF_BIRTH,";}
							
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive().getLookupName().equalsIgnoreCase("fm_Dead") )
								lStrValidatestr=lStrValidatestr+"eis.date_Demise,";
							
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive()!=null)
							{
								if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive().getLookupName().equalsIgnoreCase("Select")){lStrValidatestr=lStrValidatestr+"eis.status,";}	
								CmnLookupMst cmnLookupMst8 = new CmnLookupMst();
								cmnLookupMst8.setLookupName(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive().getLookupDesc());
								FmObj.setCmnLookupMstByFmDeadOrAlive(cmnLookupMst8);
							}
							else{lStrValidatestr=lStrValidatestr+"eis.status,";}
							
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus()!=null)
							{
								if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus().getLookupName().equalsIgnoreCase("Select")){lStrValidatestr=lStrValidatestr+"eis.Marital_Status,";}	
								CmnLookupMst cmnLookupMst6 = new CmnLookupMst();
								cmnLookupMst6.setLookupName(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus().getLookupDesc());
								FmObj.setCmnLookupMstByFmMaritalStatus(cmnLookupMst6);															
							}
							else{lStrValidatestr=lStrValidatestr+"eis.Marital_Status,";} 
							
							//change done by sunil 29/05/08
							/*if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification()!=null)
							{
								if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification().getLookupName().equalsIgnoreCase("Select")){lStrValidatestr=lStrValidatestr+"eis.Qualification,";}	
								CmnLookupMst cmnLookupMst5 = new CmnLookupMst();
								cmnLookupMst5.setLookupName(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification().getLookupDesc());
								FmObj.setCmnLookupMstByFmQualification(cmnLookupMst5);
							}
							else{lStrValidatestr=lStrValidatestr+"eis.Qualification,";}*/
							
							
							CmnLookupMst cmnLookupMstObj=hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive();
							if(cmnLookupMstObj==null || !cmnLookupMstObj.getLookupName().equalsIgnoreCase("fm_Dead"))
							{
								if(hrEisEmpFmDtlsTrn.getDependentOrNot() != null && !hrEisEmpFmDtlsTrn.getDependentOrNot().equals(""))
								{
									if(hrEisEmpFmDtlsTrn.getDependentOrNot().equalsIgnoreCase("Select")){lStrValidatestr=lStrValidatestr+"eis.fm_dependant_dtls,";}
									FmObj.setDependentOrNot(hrEisEmpFmDtlsTrn.getDependentOrNot());
								}
								else{lStrValidatestr=lStrValidatestr+"eis.fm_dependant_dtls,";}
								
								if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus()!=null) 
								{
									if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupName().equalsIgnoreCase("Select")){lStrValidatestr=lStrValidatestr+"eis.Employment_Status,";}
									CmnLookupMst cmnLookupMst1 = new CmnLookupMst();
									cmnLookupMst1.setLookupName(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupDesc());
									FmObj.setCmnLookupMstByFmEmploymentStatus(cmnLookupMst1);
								}
								else{lStrValidatestr=lStrValidatestr+"eis.Employment_Status,";}
								
								if(hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality()!=null && !hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality().getCountryCode().equalsIgnoreCase("Select") && !hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality().getCountryCode().equals(""))//change by sunil for nationality
								{
									FmObj.setCmnCountryMstByFmNationality(hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality());
								}
								else{lStrValidatestr=lStrValidatestr+"eis.Nationality,";} 
	
								if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation().getLookupName().equalsIgnoreCase("Government_org"))
								{						
									if(hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept()!=null && !hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept().getDepCode().equalsIgnoreCase("Select") && !hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept().getDepCode().equals(""))// Change By sunil on 04/06/08 for Employment Dtls
									{
										FmObj.setOrgDepartmentMstByFmDept(hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept());
									}
									else{lStrValidatestr=lStrValidatestr+"eis.Dept,";} 
								}
	
								//Change By sunil on 04/06/08 for Employment Dtls
								if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation()!=null)
								{
									if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus()!=null && !hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupName().equalsIgnoreCase("fm_Unemployed") && !hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupName().equalsIgnoreCase("fm_Student"))
									{
										if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation().getLookupName().equalsIgnoreCase("Select")){lStrValidatestr=lStrValidatestr+"eis.occupation,";}
									}
									CmnLookupMst cmnLookupMst4 = new CmnLookupMst();
									cmnLookupMst4.setLookupName(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation().getLookupDesc());
									FmObj.setCmnLookupMstByFmOccupation(cmnLookupMst4);
								}
								
								/****** Address Code ***/	
								if(hrEisEmpFmDtlsTrn.getCmnAddressMst()!=null)
								{
									/* commented by sandip - start
									objectArgs.put("mode","ADD");																			
									CmnAddressMst cm = AddressDelegate.setAddressObjectFields(hrEisEmpFmDtlsTrn.getCmnAddressMst(),objectArgs);
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
									FmObj.setCmnAddressMst(cm1);
									commented by sandip - end*/
									
									//hrEisEmpFmDtlsTrn.setCmnAddressMst(cm1);
									
									//added by sandip - start
									FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
									FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
									objectArgs.put("addrName", "DraftFamilyAddress");
									objectArgs.put("addressObject", hrEisEmpFmDtlsTrn.getCmnAddressMst());
									objRes = serv.executeService(servDetails, objectArgs);
									Map addressMap = (Map) objectArgs.get("DraftFamilyAddress");
									CmnAddressMst addressMst = (CmnAddressMst)addressMap.get("addressVO");
									FmObj.setCmnAddressMst(addressMst);
									//added by sandip - end
								}
								else{lStrValidatestr=lStrValidatestr+"eis.FamilyMemAdd,";}
								/*******Address Code Ends**/
					    	}
							
							FmObj.setRequestFlag(hrEisEmpFmDtlsTrn.getRequestFlag());
							FmObj.setRowNumber(hrEisEmpFmDtlsTrn.getRowNumber());
							String strXMLObj = FileUtility.voToXmlFileByXStream(hrEisEmpFmDtlsTrn);
							if(lStrValidatestr.equals("")==false)
							{
								strXMLObj=strXMLObj+"$/$"+lStrValidatestr;
							}
							sbXML.append("<EmpStatus>");											
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupDesc().equalsIgnoreCase("Select")==false){
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupDesc());
							}else{sbXML.append("null");}
							sbXML.append("</EmpStatus>");	
							
							sbXML.append("<FmNationality>");//change by sunil for Nationality											
							if(hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality()!=null && hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality().getCountryId()!=0)
							{
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality().getCountryName());
							}else{sbXML.append("null");}
							sbXML.append("</FmNationality>");	

							sbXML.append("<FmDept>");// Change By sunil on 04/06/08 for Employment Dtls										
							if(hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept()!=null && !hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept().getDepCode().equals(""))
							{
								sbXML.append(hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept().getDepName());
							}else{sbXML.append("null");}
							sbXML.append("</FmDept>");
							
							sbXML.append("<Marital>");											
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus().getLookupDesc().equalsIgnoreCase("Select")==false){
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus().getLookupDesc());
							}else{sbXML.append("null");}
							sbXML.append("</Marital>");				

							String  d=null;
							if(hrEisEmpFmDtlsTrn.getFmDateOfBirth()==null){}					  						  		
							else
							{
								d = sdf.format(hrEisEmpFmDtlsTrn.getFmDateOfBirth());
							}					  		 
							sbXML.append("<DOB>");						  		 
							sbXML.append(d);
							sbXML.append("</DOB>");
														
							sbXML.append("<Gender>");
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender().getLookupDesc().equalsIgnoreCase("Select")==false){
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender().getLookupDesc());
							}else{sbXML.append("null");}
							sbXML.append("</Gender>");				

							sbXML.append("<Qualification>");
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification().getLookupDesc().equalsIgnoreCase("Select")==false){
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification().getLookupDesc());
							}else{sbXML.append("null");}
							sbXML.append("</Qualification>");

							sbXML.append("<Relation>");	
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation().getLookupDesc().equalsIgnoreCase("Select")==false){
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation().getLookupDesc());
							}else{sbXML.append("null");}
							sbXML.append("</Relation>");											

							sbXML.append("<DeadOrAlive>");
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive()!=null){
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive().getLookupDesc());
							}else{sbXML.append("null");}
							sbXML.append("</DeadOrAlive>");				

							sbXML.append("<LastName>");				
							if(hrEisEmpFmDtlsTrn.getFmLastName()!=null && hrEisEmpFmDtlsTrn.getFmLastName().trim().equals("")==false)
							{
								sbXML.append(hrEisEmpFmDtlsTrn.getFmLastName());					
							}				
							else{sbXML.append("null");}
							sbXML.append("</LastName>");

							sbXML.append("<FirstName>");
							if(hrEisEmpFmDtlsTrn.getFmFirstName()!=null && hrEisEmpFmDtlsTrn.getFmFirstName().trim().equals("")==false)
							{
								sbXML.append(hrEisEmpFmDtlsTrn.getFmFirstName());					
							}				
							else{sbXML.append("null");}																	
							sbXML.append("</FirstName>");

							sbXML.append("<MidName>");
							if(hrEisEmpFmDtlsTrn.getFmMiddleName()!=null && hrEisEmpFmDtlsTrn.getFmMiddleName().trim().equals("")==false)
							{
								sbXML.append(hrEisEmpFmDtlsTrn.getFmMiddleName());					
							}				
							else{sbXML.append("null");}							
							sbXML.append("</MidName>");

							sbXML.append("<Occupation>");
							if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation().getLookupDesc().equalsIgnoreCase("Select")==false){
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation().getLookupDesc());
							}else{sbXML.append("null");}
							sbXML.append("</Occupation>");

							sbXML.append("<Remarks>");
							if(hrEisEmpFmDtlsTrn.getFmRemarks()!=null && hrEisEmpFmDtlsTrn.getFmRemarks().trim().equals("")==false)
							{
								sbXML.append(hrEisEmpFmDtlsTrn.getFmRemarks());					
							}				
							else{sbXML.append("null");}
							sbXML.append("</Remarks>");

							sbXML.append("<RequestFlag>");
							sbXML.append(hrEisEmpFmDtlsTrn.getRequestFlag());
							if(hrEisEmpFmDtlsTrn.getRequestFlag()!=null && hrEisEmpFmDtlsTrn.getRequestFlag().trim().equals("")==false)
							{
								sbXML.append(hrEisEmpFmDtlsTrn.getRequestFlag());					
							}				
							else{sbXML.append("null");}
							sbXML.append("</RequestFlag>");												
								
							sbXML.append("<Attchment>");											
							if(hrEisEmpFmDtlsTrn.getCmnAttachmentMst()!=null){
								sbXML.append(hrEisEmpFmDtlsTrn.getCmnAttachmentMst().getAttachmentId());
							}else{sbXML.append("null");}
							sbXML.append("</Attchment>");	
							
							sbXML.append("<XMLFile>");
							sbXML.append(strXMLObj);
							sbXML.append("</XMLFile>");
					    }
					    String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();				 
						logger.info("tempXML for Draft Request id is:: " + tempStr);
						objectArgs.put("ajaxKey", tempStr);
						/***********  end of getting a pending dtls code*********/						
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
 			logger.error("Exception while getting a Draft Dtls For All Request  for Family in Service",e);
 		}
 		return objRes;
	}

	public ResultObject deleteDraftRequestDtls(Map<String, Object> objectArgs, long reqId)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			/* Get The Login Id of The Person */						
			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			getEmpEngLoginId g=  new getEmpEngLoginId(OrgEmpMst.class,serv.getSessionFactory());
			long EmpID = g.getEmpEnglishLoginId(userID);
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			orgEmpMst.setEmpId(EmpID);
			/* End of the geting Person ID Code */
			/* Getting a pending  a request for the Family */
			FamilyDtlsDAO familyDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class, serv.getSessionFactory());
			List fmDtlsLstObj=new ArrayList();			
			fmDtlsLstObj = familyDAOImpl.getFamilyDraftRecordForView(reqId,orgUserMst);				
			ListIterator li = fmDtlsLstObj.listIterator();
			StringBuffer sbXML = new StringBuffer();
			sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			while(li.hasNext())
			{
				HrEisFamilyDtlTxn hrEisEmpFmDtlsTrn = (HrEisFamilyDtlTxn)li.next();				
				sbXML.append("<Link>");				
				sbXML.append(hrEisEmpFmDtlsTrn.getRowNumber());
				sbXML.append("</Link>");					
				familyDAOImpl.delete(hrEisEmpFmDtlsTrn);
			}
			sbXML.append("<DeleteDraft>");
			sbXML.append(reqId);
			sbXML.append("</DeleteDraft>");	
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();				 
			logger.info("tempXML for Family  Dtls is:: " + tempStr);
			objectArgs.put("ajaxKey", tempStr);
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);							 	  		
			objRes.setViewName("ajaxData");						
		}catch(Exception e)
		{
			objRes.setThrowable(e);
 			objRes.setResultCode(ErrorConstants.ERROR);
 			objRes.setResultValue(objectArgs);
 			logger.error("Exception while getting a Family Pending Dtls For View ",e);
		}
		return objRes;
	}

	public ResultObject getFamilyPendingRecordForView(Map<String, Object> objectArgs, long reqId,int flag)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			/* Get The Login Id of The Person */
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			getEmpEngLoginId g=  new getEmpEngLoginId(OrgEmpMst.class,serv.getSessionFactory());
			long EmpID = g.getEmpEnglishLoginId(userID);
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			orgEmpMst.setEmpId(EmpID);
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			/* End of the geting Person ID Code */
			/* Getting a pending  a request for the Family */
			FamilyDtlsDAO familyDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class, serv.getSessionFactory());
			List fmDtlsLstObj=new ArrayList();
			if(flag==1)
			{
				fmDtlsLstObj = familyDAOImpl.getFamilyPendingRecordForView(reqId,orgUserMst);
			}
			else if(flag==2)
			{
				fmDtlsLstObj = familyDAOImpl.getFamilyDraftRecordForView(reqId,orgUserMst);								
			}
			OrgDepartmentMstDaoImpl departmentMstDaoImpl = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());// Change By sunil on 04/06/08 for Employment Dtls
			CmnCountryMstDAOImpl cmnCountryMstDAOImpl = new CmnCountryMstDAOImpl(CmnCountryMst.class,serv.getSessionFactory());//change by sunil for nationality
			ListIterator li = fmDtlsLstObj.listIterator();
			StringBuffer sbXML = new StringBuffer();		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while(li.hasNext())
			{
				HrEisFamilyDtlTxn hrEisEmpFmDtlsTrn = (HrEisFamilyDtlTxn)li.next();
				
				String strFmFirstName = hrEisEmpFmDtlsTrn.getFmFirstName() != null ? hrEisEmpFmDtlsTrn.getFmFirstName() : "";
				hrEisEmpFmDtlsTrn.setFmFirstName(strFmFirstName);
				
				String strFmLastName = hrEisEmpFmDtlsTrn.getFmLastName() != null ? hrEisEmpFmDtlsTrn.getFmLastName() : "";
				hrEisEmpFmDtlsTrn.setFmLastName(strFmLastName);
				
				String strFmMiddleName = hrEisEmpFmDtlsTrn.getFmMiddleName() != null ? hrEisEmpFmDtlsTrn.getFmMiddleName() : "";
				hrEisEmpFmDtlsTrn.setFmMiddleName(strFmMiddleName);
				
				String strFmRelationLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation().getLookupName() : "";
				
				if (!"".equals(strFmRelationLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstByFmRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmRelationLookupName, langId));
				}
				
				String strFmRelationOther = hrEisEmpFmDtlsTrn.getFmRelationOther() != null ? hrEisEmpFmDtlsTrn.getFmRelationOther() : "";
				hrEisEmpFmDtlsTrn.setFmRelationOther(strFmRelationOther);
				
				String strFmGenderLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender().getLookupName() : "";
				
				if (!"".equals(strFmGenderLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstByFmGender(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmGenderLookupName, langId));
				}
				
				hrEisEmpFmDtlsTrn.getFmDateOfBirth();
				
				String strFmDeadOrAliveLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive().getLookupName() : "";
				
				if (!"".equals(strFmDeadOrAliveLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstByFmDeadOrAlive(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmDeadOrAliveLookupName, langId));
				}
				
				hrEisEmpFmDtlsTrn.getDependentOrNot();
				hrEisEmpFmDtlsTrn.getDateOfDemise();
				
				String strMaritalStatusLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus().getLookupName() : "";
				
				if (!"".equals(strMaritalStatusLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstByFmMaritalStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strMaritalStatusLookupName, langId));
				}
				
				String strFmQualificationLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification().getLookupName() : "";
				
				if (!"".equals(strFmQualificationLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstByFmQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmQualificationLookupName, langId));
				}
				
				String strFmSubQualificationLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstBySubQualification() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstBySubQualification().getLookupName() : "";
				if(!"".equals(strFmSubQualificationLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstBySubQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmSubQualificationLookupName, langId));
				}

				String strFmDicsiplineLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstByDiscipline() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstByDiscipline().getLookupName() : "";
				if(!"".equals(strFmDicsiplineLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstByDiscipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmDicsiplineLookupName, langId));
				}
				
				String strFmEmploymentStatusLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupName() : "";
				
				if (!"".equals(strFmEmploymentStatusLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstByFmEmploymentStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmEmploymentStatusLookupName, langId));
				}
					
				String strFmOccupationLookupName = hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation() != null ? hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation().getLookupName() : "select";
				
				if (!"".equals(strFmOccupationLookupName))
				{
					hrEisEmpFmDtlsTrn.setCmnLookupMstByFmOccupation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFmOccupationLookupName, langId));
				}
				
				if(hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality()!=null && !hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality().getCountryCode().equals(""))//change by sunil for nationality
				{
					hrEisEmpFmDtlsTrn.setCmnCountryMstByFmNationality(cmnCountryMstDAOImpl.getCountryVOByCountryCodeAndLangId(hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality().getCountryCode(), langId));
				}

				if(hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept()!=null && !hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept().getDepCode().equals(""))// Change By sunil on 04/06/08 for Employment Dtls
				{
					hrEisEmpFmDtlsTrn.setOrgDepartmentMstByFmDept(departmentMstDaoImpl.getDepartmentVOByDepartmentCodeAndLangId(hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept().getDepCode(), langId));
				}
				
				hrEisEmpFmDtlsTrn.getAnnualIncome();
				hrEisEmpFmDtlsTrn.getCompanyName();
				hrEisEmpFmDtlsTrn.getDesignation();
				hrEisEmpFmDtlsTrn.getFmRemarks();
				
				
				sbXML.append("<EmpStatus>");
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupDesc().equalsIgnoreCase("Select")==false)
				{
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmEmploymentStatus().getLookupDesc());
				}
				else{sbXML.append("null");}
				sbXML.append("</EmpStatus>");
				
				sbXML.append("<Marital>");
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus().getLookupDesc().equalsIgnoreCase("Select")==false)
				{							
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmMaritalStatus().getLookupDesc());						
				}
				else{sbXML.append("null");}
				sbXML.append("</Marital>");		
				
				String date=null;
		  		if(hrEisEmpFmDtlsTrn.getFmDateOfBirth()==null){logger.info("");}					  						  		
		  		else{date=sdf.format(hrEisEmpFmDtlsTrn.getFmDateOfBirth());}						
				sbXML.append("<DOB>");				
				sbXML.append(date);
				sbXML.append("</DOB>");				
				
				sbXML.append("<Gender>");
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender().getLookupDesc().equalsIgnoreCase("Select")==false)
				{
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmGender().getLookupDesc());								
				}
				else{sbXML.append("null");}
				sbXML.append("</Gender>");
				
				sbXML.append("<Qualification>");
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification().getLookupDesc().equalsIgnoreCase("Select")==false)
				{								
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmQualification().getLookupDesc());					
				}				
				else{sbXML.append("null");}
				sbXML.append("</Qualification>");
				
				sbXML.append("<SubQualification>");
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstBySubQualification()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstBySubQualification().getLookupDesc().equalsIgnoreCase("Select")==false)
				{								
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstBySubQualification().getLookupDesc());					
				}				
				else{sbXML.append("null");}
				sbXML.append("</SubQualification>");
				
				sbXML.append("<discipline>");
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstByDiscipline()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByDiscipline().getLookupDesc().equalsIgnoreCase("Select")==false)
				{								
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByDiscipline().getLookupDesc());					
				}				
				else{sbXML.append("null");}
				sbXML.append("</discipline>");
				
				sbXML.append("<Relation>");	
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation().getLookupDesc().equalsIgnoreCase("Select")==false)
				{							
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmRelation().getLookupDesc());															
				}				
				else{sbXML.append("null");}
				sbXML.append("</Relation>");
				
				sbXML.append("<DeadOrAlive>");
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive()!=null)
				{									
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmDeadOrAlive().getLookupDesc());								
				}
				else{sbXML.append("null");}
				sbXML.append("</DeadOrAlive>");
				
				sbXML.append("<LastName>");
				if(hrEisEmpFmDtlsTrn.getFmLastName()!=null  && hrEisEmpFmDtlsTrn.getFmLastName().trim().equals("")==false)
				{								
					sbXML.append(hrEisEmpFmDtlsTrn.getFmLastName());					
				}				
				else{sbXML.append("null");}
				sbXML.append("</LastName>");
				
				sbXML.append("<FirstName>");
				if(hrEisEmpFmDtlsTrn.getFmFirstName()!=null  && hrEisEmpFmDtlsTrn.getFmFirstName().trim().equals("")==false)
				{					
					sbXML.append(hrEisEmpFmDtlsTrn.getFmFirstName());									
				}
				else{sbXML.append("null");}
				sbXML.append("</FirstName>");
				
				sbXML.append("<MidName>");
				if(hrEisEmpFmDtlsTrn.getFmMiddleName()!=null && hrEisEmpFmDtlsTrn.getFmMiddleName().trim().equals("")==false)
				{
					sbXML.append(hrEisEmpFmDtlsTrn.getFmMiddleName());					
				}				
				else{sbXML.append("null");}
				sbXML.append("</MidName>");
				
				sbXML.append("<Occupation>");
				if(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation()!=null && hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation().getLookupDesc().equalsIgnoreCase("Select")==false)
				{					
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnLookupMstByFmOccupation().getLookupDesc());					
				}				
				else{sbXML.append("null");}
				sbXML.append("</Occupation>");
				
				sbXML.append("<Remarks>");
				if(hrEisEmpFmDtlsTrn.getFmRemarks()!=null  && hrEisEmpFmDtlsTrn.getFmRemarks().trim().equals("")==false)
				{					
					sbXML.append(hrEisEmpFmDtlsTrn.getFmRemarks());					
				}
				else{sbXML.append("null");}
				sbXML.append("</Remarks>");
				
				sbXML.append("<RequestFlag>");
				if(hrEisEmpFmDtlsTrn.getRequestFlag()!=null  && hrEisEmpFmDtlsTrn.getRequestFlag().trim().equals("")==false)
				{					
					sbXML.append(hrEisEmpFmDtlsTrn.getRequestFlag());					
				}
				else{sbXML.append("null");}
				sbXML.append("</RequestFlag>");
				
				sbXML.append("<Attchment>");											
				if(hrEisEmpFmDtlsTrn.getCmnAttachmentMst()!=null){
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnAttachmentMst().getAttachmentId());
				}else{sbXML.append("null");}
				sbXML.append("</Attchment>");
				
				sbXML.append("<FmNationality>");//change by sunil for Nationality											
				if(hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality()!=null && hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality().getCountryId()!=0)
				{
					sbXML.append(hrEisEmpFmDtlsTrn.getCmnCountryMstByFmNationality().getCountryName());
				}else{sbXML.append("null");}
				sbXML.append("</FmNationality>");	

				sbXML.append("<FmDept>");// Change By sunil on 04/06/08 for Employment Dtls										
				if(hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept()!=null && !hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept().getDepCode().equals(""))
				{
					sbXML.append(hrEisEmpFmDtlsTrn.getOrgDepartmentMstByFmDept().getDepName());
				}else{sbXML.append("null");}
				sbXML.append("</FmDept>");
			}
			 String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();				 
			 logger.info("tempXML for Family  Dtls is:: " + tempStr);
			 objectArgs.put("ajaxKey", tempStr);
			 objRes.setResultValue(objectArgs);
			 objRes.setResultCode(ErrorConstants.SUCCESS);							 	  		
			 objRes.setViewName("ajaxData");						
		}catch(Exception e)
		{
			objRes.setThrowable(e);
 			objRes.setResultCode(ErrorConstants.ERROR);
 			objRes.setResultValue(objectArgs);
 			logger.error("Exception while getting a Family Pending Dtls For View ",e);
		}
		return objRes;
	}
	
	public ResultObject submitFamilyDtls(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			
			boolean submitFlag=false;
			/* Get The Login Id of The Person */
			long userID = Long.parseLong(loginMap.get("userId").toString());
			
			getEmpEngLoginId g=  new getEmpEngLoginId(OrgEmpMst.class,serv.getSessionFactory());
			long empID = g.getEmpEnglishLoginId(userID);
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			orgEmpMst.setEmpId(empID);
						
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);

			/* End of the geting Person ID Code */

			/* Get The Person Post */
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			/* End of the geting Person Post Code */

			/* Find The Maximum Srno To Enter The Record */

			long srNo = 0;
			FamilyDtlsDAO FamilyDaoImplObj = new FamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class, serv.getSessionFactory());			

			/* End Getting Max Srno */
			
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

			/*Generateing a Req  _ id */
			/*objectArgs.put("tablename", "hr_eis_family_dtl_txn");
			objectArgs.put("serviceLocator", serv);		
			ResultObject resultObj = serv.executeService("GENERATE_ID_SRVC", objectArgs);
			int receivedcode = resultObj.getResultCode();
			if (receivedcode == -1) {
				return objRes;
			}
			Map resultMap = (Map) resultObj.getResultValue();
			long reqId = (Long) resultMap.get("newID");*/
			long reqId = IDGenerateDelegate.getNextId("hr_eis_family_dtl_txn", objectArgs);
			/*end of req Id code***/
			String draft =objectArgs.get("draft").toString();			
			HrEisFamilyDtlTxn lVObject = new HrEisFamilyDtlTxn();
			String[] txnXML = (String[]) objectArgs.get("encXML");		
			String[] enc_rowNumForAttachment =(String[])objectArgs.get("enc_rowNumForAttachment");
//			logger.info("enc_rowNumForAttachment  :   " + enc_rowNumForAttachment.length);
			for (int h=0;h<enc_rowNumForAttachment.length;h++)
			{
//				logger.info("Data  :  " + enc_rowNumForAttachment[h]);
			}
			List lstlockupTxnVO = FileUtility.xmlFilesToVoByXStream(txnXML);
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			CmnCountryMstDAOImpl cmnCountryMstDAOImpl = new CmnCountryMstDAOImpl(CmnCountryMst.class,serv.getSessionFactory());//change by sunil for nationality
			OrgDepartmentMstDaoImpl departmentMstDaoImpl = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());// Change By sunil on 04/06/08 for Employment Dtls
			int j1=1,i1=0;
			boolean workFlaow=false;
			
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
			
			for (Iterator i = lstlockupTxnVO.iterator(); i.hasNext();) {
				 Object Obj = i.next();
		    	 if(Obj != null)
	    		 {
			    	 Class<? extends Object> c=Obj.getClass();				    	 
			    	 if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxn"))
			    	 {			    		 
			    		 lVObject = (HrEisFamilyDtlTxn) Obj;
			    		 if(lVObject.getCmnLookupMstByFmGender()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmGender(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmGender().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmDeadOrAlive()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmDeadOrAlive(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmDeadOrAlive().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmEmploymentStatus()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmEmploymentStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmEmploymentStatus().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmMaritalStatus()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmMaritalStatus(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmMaritalStatus().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmQualification()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmQualification().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstBySubQualification()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstBySubQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstBySubQualification().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByDiscipline()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByDiscipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByDiscipline().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmRelation()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmRelation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmRelation().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnLookupMstByFmOccupation()!=null)
			    		 {
			    			 lVObject.setCmnLookupMstByFmOccupation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByFmOccupation().getLookupName(), langId));
			    		 }
			    		 if(lVObject.getCmnCountryMstByFmNationality()!=null &&!lVObject.getCmnCountryMstByFmNationality().getCountryCode().equals(""))//change by sunil for nationality
			    		 {
			    			 lVObject.setCmnCountryMstByFmNationality(cmnCountryMstDAOImpl.getCountryVOByCountryCodeAndLangId(lVObject.getCmnCountryMstByFmNationality().getCountryCode(), langId));
			    		 }

			    		 if(lVObject.getOrgDepartmentMstByFmDept()!=null && !lVObject.getOrgDepartmentMstByFmDept().getDepCode().equals(""))// Change By sunil on 04/06/08 for Employment Dtls
			    		 {
			    			 lVObject.setOrgDepartmentMstByFmDept(departmentMstDaoImpl.getDepartmentVOByDepartmentCodeAndLangId(lVObject.getOrgDepartmentMstByFmDept().getDepCode(), langId));
			    		 }
			    		 String rowNumber  =  enc_rowNumForAttachment[i1];
//					     logger.info("rowNumber:   " + rowNumber + "   j1 :  " + j1);
					     
					     objectArgs.put("rowNumber", rowNumber);
					     objectArgs.put("attachmentName", "attachmentBiometricFamily"); 
					     ResultObject resultObj1 = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
					     objectArgs = (Map) resultObj1.getResultValue();			
					     Long lAttachId= null;
					     ResultObject resultObjAttch = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
					     objectArgs = (Map) resultObjAttch.getResultValue();
//					     logger.info("{objectArgs  :   " + objectArgs + "\n");
					     lAttachId = (Long) objectArgs.get("AttachmentId_attachmentBiometricFamily");
//					     logger.info("lAttachId  :  "+ lAttachId);
					     if(lAttachId!=null)
					     {
					    	 CmnAttachmentMst cmnAttachmentMst = new CmnAttachmentMst();						    	 
					    	 cmnAttachmentMst.setAttachmentId(lAttachId);
					    	 lVObject.setCmnAttachmentMst(cmnAttachmentMst);
					     }	
					     else
					     {
					    	 lVObject.setCmnAttachmentMst(null);
					     }
					     
			    		 HrEisFamilyDtlTxnId htId = new HrEisFamilyDtlTxnId();
			    		 srNo = srNo + 1;
			    		 htId.setReqId(reqId);
			    		 htId.setMemberId(srNo);				
			    		 lVObject.setId(htId);	
			    		 if("1".equals(draft)) {lVObject.setDraftFlag("Y");}
			    		 else { 	
			    			 lVObject.setDraftFlag("N");	
			    			 workFlaow=true;
			    			// if(lVObject.getRequestFlag()==null){lVObject.setRequestFlag("D");}
			    			 logger.info("Family Parent  Id : " + lVObject.getRowNumber());			    			 
			    		 }								
			    		 lVObject.setActionFlag("P");			    		 
			    		 lVObject.setCmnLocationMst(cmnLocationMst);
			    		 lVObject.setOrgUserMstByUserId(orgUserMst);
			    		 lVObject.setCmnDatabaseMst(cmnDatabaseMst);
			    		 lVObject.setOrgUserMstByCreatedBy(orgUserMst);
			    		 lVObject.setOrgPostMstByCreatedByPost(orgPostMst);
			    		 lVObject.setCreatedDate(currDate);
			    		 /*lVObject.setOrgUserMstByUpdatedBy(orgUserMst);
			    		 lVObject.setOrgPostMstByUpdatedByPost(orgPostMst);
			    		 lVObject.setUpdatedDate(new java.sql.Timestamp((new java.util.Date()).getTime()));					
			    		 Generateing a Req  _ id  for Address*/
			    		 /*objectArgs.put("tablename", "CMN_ADDRESS_MST");
			    		 objectArgs.put("serviceLocator", serv);		
			    		 ResultObject resultObj3 = serv.executeService("GENERATE_ID_SRVC", objectArgs);				
			    		 int receivedcode1 = resultObj3.getResultCode();
			    		 if (receivedcode1 == -1) {
			    			 return objRes;
			    		 }
			    		 Map resultMap1 = (Map) resultObj3.getResultValue();				
			    		 long addressId= (Long) resultMap1.get("newID");*/
			    		 long addressId = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
			    		 /*end of req Id code***/			    		 
			    		 if(lVObject.getCmnAddressMst()!=null)
			    		 {								
			    			 objectArgs.put("mode","ADD");
			    			 CmnAddressMst cm = lVObject.getCmnAddressMst();
			    			 cm.setAddressId(addressId);
			    			 cm = AddressDelegate.setAddressObjectFields(cm,objectArgs);
			    			 //CmnLookupMst cmn = new CmnLookupMst();					
			    			 //cmn.setLookupId(300141);
			    			 //if(cm.getCmnLookupMstAddTypeLookupid()==null){cm.setCmnLookupMstAddTypeLookupid(cmn);}			    			
			    			 lVObject.setCmnAddressMst(cm); 
			    			 FamilyDaoImplObj.create(cm);					
			    		 }	
			    		 logger.info("getCmnLookupMstByFmRelation   "  +lVObject.getCmnLookupMstByFmRelation());	
			    		 lVObject.setCheckStatus("Y"); //Sunil
			    		 this.arHrEisFamilyDtlTxn.add(lVObject);//Sunil
			    		 //FamilyDaoImplObj.create(lVObject);					
				/*End for Address*/				
				/******Audit Code ****/				 
				/* AuditDelegate.doAudit(objectArgs, "Family Data Inserted", id);*/ 				  
				 /***   End of Audit Code***************/
			    	 }
			    	 j1++;
			    	 i1++;
	    		 }
			}
			/**************Delete Data From the Emp Education Dtls******************/
	    	 String deleteFmDtls = objectArgs.get("deleteArr").toString();	    	 
	    	 StringTokenizer st= new StringTokenizer(deleteFmDtls,",");				 
			 while(st.hasMoreTokens())
			 {					 
				 String deleteFmStrObj= st.nextElement().toString();				 
				 String deleteEduStrArrObj[]= new String[1];
				 deleteEduStrArrObj[0]= deleteFmStrObj; 
				 List deleteLstObj = FileUtility.xmlFilesToVoByXStream(deleteEduStrArrObj);						 			    	 
		    	 for(Iterator j  = deleteLstObj.iterator(); j.hasNext();) 
			     {			    		 
		    		 Object obj = j.next();			    		 
		    		 if(obj != null)
		    		 {					    		 
			    		 Class<? extends Object> c=obj.getClass();					    		
			    		 if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxn"))
				    	 {				    			 
			    			 HrEisFamilyDtlTxn delVObject = (HrEisFamilyDtlTxn)obj;	
			    			 delVObject = (HrEisFamilyDtlTxn) FamilyDaoImplObj.read(delVObject.getId());
			    			 delVObject.setOrgUserMstByUserId(orgUserMst);			    							    			
			    			 FamilyDaoImplObj.delete(delVObject);	
				    	 }
			    		 else 
			    		 {
			    			 workFlaow=true;
			    			 HrEisFamilyDtl ht = (HrEisFamilyDtl)obj;
			    			 srNo=srNo+1;			    			 
			    			 deleteEmpFamilyDtls(objectArgs,ht.getMemberId(),reqId,srNo,draft,cmnLocationMst,cmnLangMst,cmnDatabaseMst,orgPostMst,orgUserMst);			    			 
			    		 }
		    		 }
			     }
	    	 }
			 if("1".equals(draft)) {submitFlag=false;}
			 else {submitFlag=true;}
			 long corr_id=0;
			 if(workFlaow==true && submitFlag)
			 {
				 corr_id=HrmsCommonMessageServImpl.createCorr(objectArgs, serv, DOC_ID,CORR_DESC);//For Creating Corrospondance Id
				 logger.info("corrsId=========In EmpFamilyServiceImpl==="+corr_id);
			 }
			 
			 /*Sunil - Starts*/
			 ListIterator ltrHrEisFamilyDtlTxn =  this.arHrEisFamilyDtlTxn.listIterator(); //sunil
			 HrEisFamilyDtlTxn objHrEisFamilyDtlTxn = null;

			 while (ltrHrEisFamilyDtlTxn.hasNext()) {
				 objHrEisFamilyDtlTxn = (HrEisFamilyDtlTxn) ltrHrEisFamilyDtlTxn.next();
				 objHrEisFamilyDtlTxn.setCorrsId(corr_id);
				 FamilyDaoImplObj.create(objHrEisFamilyDtlTxn);
			 }
			 /*Sunil  - Ends*/
			 
	    	 /***************************End of Delete Code ***********************/
			if(submitFlag==false)
			{
				objectArgs.put("draft","Y");
			}
			
			if(workFlaow==true && submitFlag!=false)
			{				
				objectArgs.put("modId", EMP_FAMILY_MODID);
				objectArgs.put("appReqId", corr_id);
				HrmsCommonMessageServImpl.createModEmpRltTuple(serv, objectArgs, userID);
				objRes=HrmsCommonMessageServImpl.getForwardToDetail(serv, objectArgs, DOC_ID, corr_id);   
				String msg="EMP_FAMLIY_REQUEST";
				objectArgs.put("status", "PENDING"); 
				objectArgs.put("msg", msg); 
			}
			else
			{
				objectArgs.put("draft","Y");//For message view for submit page
				objRes.setViewName("SubmitMessagePage");
			}
			
			objRes.setResultValue(objectArgs);						
			objRes.setResultCode(ErrorConstants.SUCCESS);
		} 
		catch (Exception e) {		
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling submitFamilyDtls method in FamilyDetailsServiceImpl Service",e);	
		}
		return objRes;
	}	

	public ResultObject getFamilyInfo(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			 // IFMS
		//	 boolean blnWorkFlowEnabled = true;
			 
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			/** Get The Person Family Info *** */
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long userID = Long.parseLong(loginMap.get("userId").toString());
			
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);					
						
			long langId = Long.parseLong(loginMap.get("langId").toString());
			
			FamilyDtlsDAO FamilyDAO = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class, serv.getSessionFactory());
			List<String> dependantNomnDtls = new ArrayList<String>();			
			List FamilyLstObj = FamilyDAO.getEmpFamilyDtls(orgUserMst);			
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			
			CmnCountryMstDAOImpl cmnCountryMstDAOImpl = new CmnCountryMstDAOImpl(CmnCountryMst.class,serv.getSessionFactory());//change by sunil for nationality
			OrgDepartmentMstDaoImpl departmentMstDaoImpl = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());// Change By sunil on 04/06/08 for Employment Dtls

			CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
			request.getSession().removeAttribute("attachmentBiometricFamily_EDITVO");
			
			List<String> checkRequestLstObj = new ArrayList<String>();
			List<Object> depenDantLstObj = new ArrayList<Object>();
			List<HrEisFamilyDtl> FamilylstObj = new ArrayList<HrEisFamilyDtl>();
			List<String> voToXmllstObj = new ArrayList<String>();
			ListIterator itr = FamilyLstObj.listIterator();
			
			int iCounter = 0;
			
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
			FamilyDtlsDAO familyDaoObj = new FamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory()); 
			NomineeDtlsDAOImpl nomineeDtlsDAOImpl = new NomineeDtlsDAOImpl(HrEisNomineeDtl.class,serv.getSessionFactory());
			
			while (itr.hasNext()) 
			{								
				iCounter++;
				HrEisFamilyDtl FmObj = (HrEisFamilyDtl) itr.next();
				/*HrEisFamilyDtl FmXmlVObj = new HrEisFamilyDtl();
				
				FmObjId.setMemberId(FmObj.getId().getMemberId());								
				FmXmlVObj.setId(FmObjId);*/			
				if(FmObj.getCmnLookupMstByFmRelation()!=null)
				{
					String cmnLookupId= FmObj.getCmnLookupMstByFmRelation().getLookupName();												
					CmnLookupMst cmnLookupRelationId = new CmnLookupMst();
					cmnLookupRelationId.setLookupName(cmnLookupId);
					cmnLookupRelationId = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstByFmRelation(cmnLookupRelationId);	
				}
				if(FmObj.getCmnLookupMstByFmGender()!=null)
				{
					String cmnLookupId= FmObj.getCmnLookupMstByFmGender().getLookupName();
					CmnLookupMst cmnLookupGenderId = new CmnLookupMst();
					cmnLookupGenderId.setLookupName(cmnLookupId);
					cmnLookupGenderId = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstByFmGender(cmnLookupGenderId);
				}
				if(FmObj.getCmnLookupMstByFmEmploymentStatus()!=null)
				{
					String cmnLookupId= FmObj.getCmnLookupMstByFmEmploymentStatus().getLookupName();
					CmnLookupMst cmnLookupEmpStaId = new CmnLookupMst();
					cmnLookupEmpStaId.setLookupName(cmnLookupId);
					cmnLookupEmpStaId = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstByFmEmploymentStatus(cmnLookupEmpStaId);
				}
				if(FmObj.getCmnAttachmentMst()!=null)
				{
					CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(FmObj.getCmnAttachmentMst().getAttachmentId());
					EisWFmessageDisplay.removeAttachmentFromSession(request.getSession(),"attachmentBiometricFamily",iCounter);
					request.getSession().setAttribute("attachmentBiometricFamily"+iCounter+"_EDITVO", cmnAttachmentMst);
					FmObj.setCmnAttachmentMst(cmnAttachmentMst);
				}
				
				CmnLookupMst cmnLookupOccuId = new CmnLookupMst();				
				if(FmObj.getCmnLookupMstByFmOccupation()!=null)
				{
					String cmnLookupId = FmObj.getCmnLookupMstByFmOccupation().getLookupName();
					cmnLookupOccuId.setLookupName(cmnLookupId);					
					cmnLookupOccuId = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstByFmOccupation(cmnLookupOccuId);
				}
				else
				{
					cmnLookupOccuId = null;
					FmObj.setCmnLookupMstByFmOccupation(cmnLookupOccuId);
				}
				
				if(FmObj.getCmnLookupMstByFmMaritalStatus()!=null)
				{					
					String cmnLookupId= FmObj.getCmnLookupMstByFmMaritalStatus().getLookupName();
					CmnLookupMst cmnLookupMaritalStatusId = new CmnLookupMst();
					cmnLookupMaritalStatusId.setLookupName(cmnLookupId);
					cmnLookupMaritalStatusId = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstByFmMaritalStatus(cmnLookupMaritalStatusId);
				}
				if(FmObj.getCmnLookupMstByFmQualification()!=null)
				{					
					String cmnLookupId= FmObj.getCmnLookupMstByFmQualification().getLookupName();
					CmnLookupMst cmnLookupQuaId = new CmnLookupMst();
					cmnLookupQuaId.setLookupName(cmnLookupId);
					cmnLookupQuaId = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstByFmQualification(cmnLookupQuaId);
				}	
				if(FmObj.getCmnLookupMstBySubQualification()!=null)
				{					
					String cmnLookupId= FmObj.getCmnLookupMstBySubQualification().getLookupName();
					CmnLookupMst cmnLookupSubQuaId = new CmnLookupMst();
					cmnLookupSubQuaId.setLookupName(cmnLookupId);
					cmnLookupSubQuaId = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstBySubQualification(cmnLookupSubQuaId);
				}	
				
				if(FmObj.getCmnLookupMstByDiscipline()!=null)
				{					
					String cmnLookupId= FmObj.getCmnLookupMstByDiscipline().getLookupName();
					CmnLookupMst cmnLookupDicsiId = new CmnLookupMst();
					cmnLookupDicsiId.setLookupName(cmnLookupId);
					cmnLookupDicsiId = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstByDiscipline(cmnLookupDicsiId);
				}
				if(FmObj.getCmnLookupMstByFmDeadOrAlive()!=null)
				{
					String 	cmnLookupId=FmObj.getCmnLookupMstByFmDeadOrAlive().getLookupName();
					CmnLookupMst cmnLookupMstByFmDeadOrAlive = new CmnLookupMst();					
					cmnLookupMstByFmDeadOrAlive.setLookupName(cmnLookupId);
					cmnLookupMstByFmDeadOrAlive = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cmnLookupId, langId);
					FmObj.setCmnLookupMstByFmDeadOrAlive(cmnLookupMstByFmDeadOrAlive);
				}
				/***********************/
				
				if(FmObj.getCmnCountryMstByFmNationality()!=null &&!FmObj.getCmnCountryMstByFmNationality().getCountryCode().equals(""))//change by sunil for nationality
				{
					FmObj.setCmnCountryMstByFmNationality(cmnCountryMstDAOImpl.getCountryVOByCountryCodeAndLangId(FmObj.getCmnCountryMstByFmNationality().getCountryCode(), langId));
				}

				if(FmObj.getOrgDepartmentMstByFmDept()!=null && !FmObj.getOrgDepartmentMstByFmDept().getDepCode().equals(""))// Change By sunil on 04/06/08 for Employment Dtls
				{
					FmObj.setOrgDepartmentMstByFmDept(departmentMstDaoImpl.getDepartmentVOByDepartmentCodeAndLangId(FmObj.getOrgDepartmentMstByFmDept().getDepCode(), langId));
				}
				
				/**Address  Start*/	
				/* commented by sandip - start
				if(FmObj.getCmnAddressMst()!=null)
				if(FmObj.getCmnAddressMst().getAddressId()!=0)
				{
					objectArgs.put("mode","ADD");																			
					CmnAddressMst cm = AddressDelegate.setAddressObjectFields(FmObj.getCmnAddressMst(),objectArgs);
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
					//FmXmlVObj.setCmnAddressMst(cm1);
				}
				commented by sandip - end*/
				
				/*objectArgs.put("addressId", new Long(FmObj.getAddressId().getAddressId()));
				objectArgs.put("addrName", "familyPersonAddress");					
				FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
				FrmServiceMst servDetails = servDetailsImpl.readService("GET_ADDRESS_VO");									
				objRes=serv.executeService(servDetails, objectArgs);				
				Map tempMap =(Map) objectArgs.get("familyPersonAddress");
				FmXmlVObj.setAddressId((CmnAddressMst) tempMap.get("addressVO"));					
				objectArgs.remove("familyPersonAddress");*/
				//FmXmlVObj.setAddressId(FmObj.getAddressId());
				
				//added by sandip - start
				if(FmObj!=null)
				{
					FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
					objectArgs.put("addrName", "getFamilyAddress");
					objectArgs.put("addressObject", FmObj.getCmnAddressMst());
					objRes = serv.executeService(servDetails, objectArgs);
				}
				//added by sandip - end
				/*********** Address End***********************/					
				String xmlFileIdStr = FileUtility.voToXmlFileByXStream(FmObj);
				voToXmllstObj.add(xmlFileIdStr);				
				FamilylstObj.add(FmObj);
				
				/* Checking for the Record whether this record is avilable in TRN or NOt */
				List requestLstObj = familyDaoObj.findRequestForthisRecord(FmObj.getMemberId(),orgUserMst);
				ListIterator reqLstItr = requestLstObj.listIterator();				
				boolean flag=true;				
				while(reqLstItr.hasNext())
				{
					HrEisFamilyDtlTxn row = (HrEisFamilyDtlTxn)reqLstItr.next();						
					if(row.getRowNumber() == FmObj.getMemberId())
					{
						//checkRequestLstObj.add("No");
						if(row.getDraftFlag().equalsIgnoreCase("Y"))
						{
							checkRequestLstObj.add("Draft");
						}
						else
						{
							checkRequestLstObj.add("Pending");
						}
						flag=false;
					}
				}					
				if(flag==true)
				{																														
					//List nomineeLstObj = new ArrayList();
					List nomineeLstObj = nomineeDtlsDAOImpl.getNomineeRecordCheckWithFmKey(userID,FmObj.getMemberId());					
					if(nomineeLstObj.isEmpty()==false){checkRequestLstObj.add("delete");}
					else{checkRequestLstObj.add("Yes");}						
				}				
									
			}			
			/* End Of the Person Family Info */
			/************ Getting a Persons Pending Request ************/
			FamilyDtlsDAO familyPendingDaoObj = new FamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
			List<Object> requestLstObj = new ArrayList<Object>();
			List personRequestLstObj = familyPendingDaoObj.getEmpRequest(orgUserMst);			
			itr= personRequestLstObj.listIterator();			
			if(personRequestLstObj.isEmpty()==false)
			{
			    while(itr.hasNext()) 
			    {
			    	try
			    	{
			    		Object[] addRow = new Object[3];
			    		Object row[] = (Object[])itr.next();			    				    	
			    		long reqId= (Long)row[2];
			    		logger.info("==============Correspondence============="+ reqId);
			    		addRow[0]=row[0];
			    		addRow[1]=row[1];		
			    		objectArgs.put("jobTitle", WFConstants.JOB_TITLE_CORR);
						objRes=HrmsCommonMessageServImpl.getForwardToDetail(serv, objectArgs, DOC_ID, reqId); 
			    		addRow[2]=objectArgs.get("fwdTo");
			    		requestLstObj.add(addRow);
			    	}catch(Exception e){logger.info("Erorr in fetching pending request===>"+e);}
			    }
			}
			/***********  end of getting a pending dtls code*********/
			/*** Code for Getting a Draft Request****/
			FamilyDtlsDAO draftRequestDaoImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
			List tempdraftRequest = draftRequestDaoImpl.getEmpDraftRequest(orgUserMst);
			List<Object> draftRequest = new ArrayList<Object>();
			itr= tempdraftRequest.listIterator();			
			if(tempdraftRequest.isEmpty()==false)
			{
			    while(itr.hasNext()) 
			    {
			    	Object row[] = (Object[])itr.next();				    	
			    	draftRequest.add(row);
			    }
			} 
			
			/*Get all Dependent Dtls And Nominee Dtls*/
			
			List temp = new ArrayList();
			NomineeDtlsDAO noDtlsDAOImpl = new NomineeDtlsDAOImpl(HrEisNomineeDtl.class,serv.getSessionFactory());
			temp = noDtlsDAOImpl.getApprovedNomineeDataOnUserMst(orgUserMst);
			ListIterator li_temp= temp.listIterator();			
			while(li_temp.hasNext())
			{
				dependantNomnDtls.add(li_temp.next().toString());				
			}						
			/*End of Getting a Dtls*/
			/*** End of Getting a  Draft Request  ****/				
			objectArgs.put("dependantNomnDtls", dependantNomnDtls);
			objectArgs.put("DepenDantDtls", depenDantLstObj);
			logger.info("draftRequest ::  " + draftRequest.size());
			objectArgs.put("draftRequest",draftRequest);
			objectArgs.put("PendingDtls",requestLstObj);
		    objectArgs.put("requesetLstObj", checkRequestLstObj);
			objectArgs.put("XmlFile", voToXmllstObj);			
			objectArgs.put("FamilyDtls", FamilylstObj);	
			objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
		//	objectArgs.put("workFlowEnabled",blnWorkFlowEnabled); // IFMS
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("FamilyDetails");
		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting Family Employee Dtls ", e);
		}
		return objRes;
	}

	public ResultObject getComboDtls(Map<String, Object> objectArgs,boolean blnWorkFlowEnabled ,long selectedUserId) 
	{
		ResultObject objRes = new ResultObject();
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
						
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long userId = Long.parseLong(loginDetailsMap.get("userId").toString());
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

			List EmpStalookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Employment_Status",langId);
			List GenderlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("fm_Gender", langId);
			List MaritallookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Marital_Status", langId);
			List FmQualookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("edu_Qualifications",langId);
			List RelationlookUpList= null;
			List FmOccupationList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("fm_Occupation", langId);
			List FmDeadOrAliveList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("fm_DeadOrAlive", langId);
			List DepedentLookupLst = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Fm_Dependent", langId);
			
			HrEisEmpMst hrEisEmpMstObj=null;
			EmpInfoDAO_WF empInfoDAO =new EmpInfoDAOImpl_WF(HrEisEmpMst.class,serv.getSessionFactory());
			
			if(blnWorkFlowEnabled)
			{
				hrEisEmpMstObj=empInfoDAO.getHrEmpVOOnUserId(userId, 1);
			}
			else
			{
				hrEisEmpMstObj=empInfoDAO.getHrEmpVOOnUserId(selectedUserId, 1);
			}
			
			FamilyDtlsDAO familyDtlsDAO=new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			String restrictedLookupName=""; 
			
			if(hrEisEmpMstObj!=null && hrEisEmpMstObj.getEmpGender() != null)
			{
				if(hrEisEmpMstObj.getEmpGender() == 'M')
				{
					restrictedLookupName="fm_Husband";
					RelationlookUpList = familyDtlsDAO.getAllRelationByLookUpNameAndLang("fm_Relation", langId,restrictedLookupName);
				}
				else if (hrEisEmpMstObj.getEmpGender() == 'F')
				{
					restrictedLookupName="fm_Wife";
					RelationlookUpList = familyDtlsDAO.getAllRelationByLookUpNameAndLang("fm_Relation", langId,restrictedLookupName);
				}
				else
				{
					RelationlookUpList = familyDtlsDAO.getAllRelationByLookUpNameAndLang("fm_Relation", langId,restrictedLookupName);
				}
			}
			else
			{
				RelationlookUpList = familyDtlsDAO.getAllRelationByLookUpNameAndLang("fm_Relation", langId,restrictedLookupName);
			}
			
//			change by sunil for Nationality 
			CmnCountryMstDAOImpl cmnCountryMstObj = new CmnCountryMstDAOImpl(CmnCountryMst.class, serv.getSessionFactory());
			List<CmnCountryMst> cmnCountryMstList=cmnCountryMstObj.getAllCountries(langId);
			logger.info("cmnCountryMstList===="+cmnCountryMstList.size());
			objectArgs.put("cmnCountryMstList", cmnCountryMstList);


			FamilyDtlsDAOImpl familyDtlsDAOImpl  =new FamilyDtlsDAOImpl(HrEisFamilyDtl.class, serv.getSessionFactory());//Change By sunil on 04/06/08 for Employment Dtls 
			List DeptList=familyDtlsDAOImpl.getAllDepartmentByLangId(langId);
			logger.info("DeptList====="+DeptList.size());
			objectArgs.put("DeptList", DeptList);
			
			objectArgs.put("DepedentLookupLst", DepedentLookupLst);
			objectArgs.put("DeadOrAlive",FmDeadOrAliveList);
			objectArgs.put("Relation", RelationlookUpList);
			objectArgs.put("EmploymentStatus", EmpStalookUpList);
			objectArgs.put("Gender", GenderlookUpList);
			objectArgs.put("Marital", MaritallookUpList);
			objectArgs.put("FmQualookUpList", FmQualookUpList);
			objectArgs.put("Occupation", FmOccupationList);
			objRes.setResultValue(objectArgs);
											
		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getComboDtls method in FamilyDetailsServiceImpl Service",e);
		}
		return objRes;
	}
	public ResultObject getDraftData(Map<String, Object> objectArgs)
	{
		ResultObject objRes = new ResultObject();
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");			
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			
			 /*  Get The Login Id of The Person*/
			 
			 long userID = Long.parseLong(loginMap.get("userId").toString());
			 OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			 OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			 getEmpEngLoginId g=  new getEmpEngLoginId(OrgEmpMst.class,serv.getSessionFactory());
			 long EmpID = g.getEmpEnglishLoginId(userID);
			 OrgEmpMst orgEmpMst = new OrgEmpMst();
			 orgEmpMst.setEmpId(EmpID);			 	    	 
			 
	    	 /*End of the geting Person ID Code*/
			 FamilyDtlsDAO FamilyDaoImplObj = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			 List draftLstObj=FamilyDaoImplObj.getDrftDtlsLst(orgUserMst);
			 List<Object[]> draftDataLstObj = new ArrayList<Object[]>(); 
		     if(!draftLstObj.isEmpty())
			 {							
				 	ListIterator li = draftLstObj.listIterator();
				 	while(li.hasNext())
				 	{
				 		Object row[] = (Object[])li.next();						 		
				 		draftDataLstObj.add(row);
				 	}
				 	objectArgs.put("draftRequest",draftDataLstObj);						 	
			 }				 
		     else
		     {
		    	 logger.info("Emp Education xml draft data not found ::::::::");
		     }		     			 			 						
			objRes.setResultValue(objectArgs);
		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getDraftData method in FamilyDetailsServiceImpl Service",e);
		}
		return objRes;
	}
	public ResultObject getFamilyPendingRequestForView(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject();
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");			
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			
			 /*  Get The Login Id of The Person*/
			 long userID = Long.parseLong(loginMap.get("userId").toString());
			 OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			 OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			 getEmpEngLoginId g=  new getEmpEngLoginId(OrgEmpMst.class,serv.getSessionFactory());
			 long EmpID = g.getEmpEnglishLoginId(userID);
			 
			 OrgEmpMst orgEmpMst = new OrgEmpMst();
			 orgEmpMst.setEmpId(EmpID);			 	    	 
			 
	    	 /*End of the geting Person ID Code*/
			 FamilyDtlsDAO FamilyDaoImplObj = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			 List draftLstObj=FamilyDaoImplObj.getDrftDtlsLst(orgUserMst);
			 List<Object[]> draftDataLstObj = new ArrayList<Object[]>(); 
		     if(!draftLstObj.isEmpty())
			 {							
				 	ListIterator li = draftLstObj.listIterator();
				 	while(li.hasNext())
				 	{
				 		Object row[] = (Object[])li.next();						 		
				 		draftDataLstObj.add(row);
				 	}
				 	objectArgs.put("draftRequest",draftDataLstObj);						 	
			 }				 
		     else
		     {
		    	 logger.info("Emp Education xml draft data not found ::::::::");
		     }		     			 			 						
			objRes.setResultValue(objectArgs);
		} catch (Exception e) {			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getFamilyPendingRequestForView method in FamilyDetailsServiceImpl Service",e);
		}
		return objRes;
	}	
	
	public void deleteEmpFamilyDtls(Map<String, Object> objectArgs, long memberId, long reqId, long srNo, String draft, CmnLocationMst cmnLocationMst, CmnLanguageMst cmnLangMst, CmnDatabaseMst cmnDatabaseMst, OrgPostMst orgPostMst, OrgUserMst orgUserMst) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			//Map loginMap = (Map) objectArgs.get("baseLoginMap");			

			/* Get The Login Id of The Person */																
			FamilyDtlsDAO fmDAO = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			
			List deleteList = fmDAO.deletRequest(memberId);			
			HrEisFamilyDtl hrEisEmpFamilyDtls = (HrEisFamilyDtl)deleteList.get(0);
			HrEisFamilyDtlTxn hrEisEmpFamilyDtlsTrn = new HrEisFamilyDtlTxn();
			if(draft.equalsIgnoreCase("1")){hrEisEmpFamilyDtlsTrn.setDraftFlag("Y");}
			else {hrEisEmpFamilyDtlsTrn.setDraftFlag("N");}
			HrEisFamilyDtlTxnId hrEisEmpFamilyDtlsTrnId = new HrEisFamilyDtlTxnId();
			hrEisEmpFamilyDtlsTrnId.setReqId(reqId);
			hrEisEmpFamilyDtlsTrnId.setMemberId(srNo);
			hrEisEmpFamilyDtlsTrn.setId(hrEisEmpFamilyDtlsTrnId);
			hrEisEmpFamilyDtlsTrn.setActionFlag("P");
			hrEisEmpFamilyDtlsTrn.setRequestFlag("D");			
			hrEisEmpFamilyDtlsTrn.setRowNumber(memberId);		
			
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstByFmEmploymentStatus(hrEisEmpFamilyDtls.getCmnLookupMstByFmEmploymentStatus());
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstByFmGender(hrEisEmpFamilyDtls.getCmnLookupMstByFmGender());
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstByFmMaritalStatus(hrEisEmpFamilyDtls.getCmnLookupMstByFmMaritalStatus());
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstByFmOccupation(hrEisEmpFamilyDtls.getCmnLookupMstByFmOccupation());					
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstByFmQualification(hrEisEmpFamilyDtls.getCmnLookupMstByFmQualification());
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstByFmRelation(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation());
			
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstBySubQualification(hrEisEmpFamilyDtls.getCmnLookupMstBySubQualification());
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstByDiscipline(hrEisEmpFamilyDtls.getCmnLookupMstByDiscipline());
			hrEisEmpFamilyDtlsTrn.setOrgDepartmentMstByFmDept(hrEisEmpFamilyDtls.getOrgDepartmentMstByFmDept());
			hrEisEmpFamilyDtlsTrn.setCmnCountryMstByFmNationality(hrEisEmpFamilyDtls.getCmnCountryMstByFmNationality());
			hrEisEmpFamilyDtlsTrn.setOtherOccupation(hrEisEmpFamilyDtls.getOtherOccupation());//Change 
			
			hrEisEmpFamilyDtlsTrn.setFmRelationOther(hrEisEmpFamilyDtls.getFmRelationOther());
			hrEisEmpFamilyDtlsTrn.setCmnAddressMst(hrEisEmpFamilyDtls.getCmnAddressMst());
			hrEisEmpFamilyDtlsTrn.setFmFirstName(hrEisEmpFamilyDtls.getFmFirstName());
			hrEisEmpFamilyDtlsTrn.setFmMiddleName(hrEisEmpFamilyDtls.getFmMiddleName());
			hrEisEmpFamilyDtlsTrn.setFmLastName(hrEisEmpFamilyDtls.getFmLastName());
			hrEisEmpFamilyDtlsTrn.setFmDateOfBirth(hrEisEmpFamilyDtls.getFmDateOfBirth());
			hrEisEmpFamilyDtlsTrn.setCmnLookupMstByFmDeadOrAlive(hrEisEmpFamilyDtls.getCmnLookupMstByFmDeadOrAlive());
			hrEisEmpFamilyDtlsTrn.setFmRelationOther(hrEisEmpFamilyDtls.getFmRelationOther());
			hrEisEmpFamilyDtlsTrn.setFmRemarks(hrEisEmpFamilyDtls.getFmRemarks());
			
			hrEisEmpFamilyDtlsTrn.setCmnDatabaseMst(cmnDatabaseMst);			
			hrEisEmpFamilyDtlsTrn.setCmnLocationMst(cmnLocationMst);
			hrEisEmpFamilyDtlsTrn.setCreatedDate(new java.sql.Timestamp((new java.util.Date()).getTime()));
			hrEisEmpFamilyDtlsTrn.setUpdatedDate(new java.sql.Timestamp((new java.util.Date()).getTime()));
			hrEisEmpFamilyDtlsTrn.setOrgUserMstByUserId(orgUserMst);
			hrEisEmpFamilyDtlsTrn.setOrgPostMstByCreatedByPost(orgPostMst);
			hrEisEmpFamilyDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMst);
			hrEisEmpFamilyDtlsTrn.setOrgUserMstByCreatedBy(orgUserMst);
			hrEisEmpFamilyDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
//			fmDAO.create(hrEisEmpFamilyDtlsTrn);//Sunil
			hrEisEmpFamilyDtlsTrn.setCheckStatus("Y"); //Sunil
			this.arHrEisFamilyDtlTxn.add(hrEisEmpFamilyDtlsTrn);//Sunil
		} catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting Family Employee Dtls ", e);
		}
	}
	public ResultObject getSelEmpTypeComboDtls(Map objectArgs,String strCmbid)// Change By sunil on 04/06/08 for Employment Dtls 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try{						
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}												

			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
			Map loginMap = (Map) objectArgs.get("baseLoginMap");

			/*Getting a Lang Id*/
			long langId = Long.parseLong(loginMap.get("langId").toString());			
			/* End Of getting a Lang ID*/

			List lLstObj=new ArrayList();
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			if("fm_Employed".equals(strCmbid))
			{
				lLstObj = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("OrgType",langId);
			}	
			if("fm_self_employeed".equals(strCmbid))
			{
				lLstObj = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("SelfEmpType",langId);
			}	

			/* AJAX Code Insert */
			StringBuffer sbXML = new StringBuffer();				
			sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");				
			if(lLstObj.isEmpty()==false)
			{
				ListIterator litr = lLstObj.listIterator();
				while (litr.hasNext()) 
				{
					CmnLookupMst cmnLookUpObj = (CmnLookupMst) litr.next();
					sbXML.append("<Occupation>");
					sbXML.append(cmnLookUpObj.getLookupDesc());
					sbXML.append("</Occupation>");
					sbXML.append("<ID>");
					sbXML.append(cmnLookUpObj.getLookupName());
					sbXML.append("</ID>");
				}
			}			
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();
			logger.info("tempXML is::" + tempStr);
			objectArgs.put("ajaxKey", tempStr);			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ajaxData");
			/* Ajax Code End */	
		}catch (Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting a Qualification Combo Dtls in Service",e);
		}
		return objRes;
	}
}
