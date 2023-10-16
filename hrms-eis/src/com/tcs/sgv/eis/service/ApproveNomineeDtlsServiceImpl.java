package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ApproveNomineeDtlsDAO;
import com.tcs.sgv.eis.dao.ApproveNomineeDtlsDAOImpl;
import com.tcs.sgv.eis.dao.FamilyDtlsDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtlHst;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtlTxn;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


public class ApproveNomineeDtlsServiceImpl extends ServiceImpl implements ApproveNomineeDtlsService
{	
	public final String DocId = "";
	private static final Log logger = LogFactory.getLog(ApproveNomineeDtlsServiceImpl.class);	
	public ResultObject getNomineePendingDetails(Map<String, Object> objectArgs)   //   300362
	{
		Log logger = LogFactory.getLog(getClass());	
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			ApproveNomineeDtlsDAOImpl approveNomineeDtlsDAOImpl = new  ApproveNomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
			List pendingLstObj = approveNomineeDtlsDAOImpl.getNomineePendingDtls();
			objectArgs.put("pendingLstObj",pendingLstObj);
			objRes.setViewName("ApproveNomineeDtls");
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
		}catch(Exception e)
		{
			objRes.setResultCode(ErrorConstants.ERROR);
			logger.info("Error in Approve Nominee Dtls,getNomineePendingDetails ",e);
		}
		return objRes;
	}	
	
	
	public ResultObject ShowNomineeDtlsForApprove(Map<String, Object> objectArgs)   //   300364
	{
		Log logger = LogFactory.getLog(getClass());	
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");					
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			

			/* Get The Login Id of The Person */
			
			/*Gettin a Lang ID*/
			long langId = Long.parseLong(loginMap.get("langId").toString());
			/*End of Geeting a a lang id*/			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			
			/*Divyesh - Starts*/
			long CorrsId = 0;
			try
			{
				CorrsId = Long.parseLong(objectArgs.get("corrsIdStr").toString());
			}
			catch(Exception e){logger.error(e);}
			logger.info("CorrsId======In Service====="+CorrsId);
			
			ApproveNomineeDtlsDAO objApproveNomineeDtlsDAO = new ApproveNomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());	
			/**Start===Getting a Request Id from CorrsId */
			 
			
			long reqId = objApproveNomineeDtlsDAO.getCurrentRequestIdFromCorrsId(CorrsId); 
			
			//String reqIdStr = objectArgs.get("reqId").toString();
			//long reqId =Long.parseLong(reqIdStr.trim());	
			/*Divyesh - Ends*/
			
			ApproveNomineeDtlsDAOImpl approveNomineeDtlsDAOImpl = new  ApproveNomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
			List pendingLstObj = approveNomineeDtlsDAOImpl.getNomineePendingDtlsOnReqId(reqId);
			ListIterator li = pendingLstObj.listIterator();
			long userId = 1;
			CmnLookupMst cmnLookupMst =new CmnLookupMst();
			FamilyDtlsDAOImpl familyDtlsDAOImpl = new FamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			while(li.hasNext())
			{
				HrEisNomineeDtlTxn hrEisEmpNomineeDtlsTrn = (HrEisNomineeDtlTxn)li.next();
				OrgUserMst orgUsermst=hrEisEmpNomineeDtlsTrn.getOrgUserMstByUserId();
				userId=orgUsermst.getUserId();
				int index=pendingLstObj.indexOf(hrEisEmpNomineeDtlsTrn);
				cmnLookupMst=hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnBenefitTypeId();
				
				if(hrEisEmpNomineeDtlsTrn.getFamilyMemberId()!=0)
				{
					List familyLstObj=familyDtlsDAOImpl.getTheFamilyMember(userId,hrEisEmpNomineeDtlsTrn.getFamilyMemberId());					
					HrEisFamilyDtl hrEisEmpFamilyDtls = (HrEisFamilyDtl)familyLstObj.get(0);
					if(hrEisEmpFamilyDtls.getFmMiddleName()!=null)
					{
						hrEisEmpNomineeDtlsTrn.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmMiddleName() +" " +hrEisEmpFamilyDtls.getFmLastName() );
					}
					else
					{
						hrEisEmpNomineeDtlsTrn.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() +" " +hrEisEmpFamilyDtls.getFmLastName() );
					}
					hrEisEmpNomineeDtlsTrn.setNomnOtherRelation(hrEisEmpFamilyDtls.getFmRelationOther());
					hrEisEmpNomineeDtlsTrn.setCmnLookupMstByNomnRelation(hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation());
					hrEisEmpNomineeDtlsTrn.setNomnDob(hrEisEmpFamilyDtls.getFmDateOfBirth());
					hrEisEmpNomineeDtlsTrn.setCmnAddressMstByNomnAddress(hrEisEmpFamilyDtls.getCmnAddressMst());
					
					pendingLstObj.set(index,hrEisEmpNomineeDtlsTrn);
				}			
			}	
			
			List appLstObj=approveNomineeDtlsDAOImpl.getApprovedNomineeDataForApproveRecord(cmnLookupMst,reqId,userId);
			ListIterator litr = appLstObj.listIterator();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 	
			
			if(litr!=null)
			{
				while(litr.hasNext())
				{				
					HrEisNomineeDtlHst hrEisEmpNomineeDtls = (HrEisNomineeDtlHst)litr.next();
					int index = appLstObj.indexOf(hrEisEmpNomineeDtls);	
					if(hrEisEmpNomineeDtls.getFamilyMemberId()!=0 )
					{										
						List familyLstObj=familyDtlsDAOImpl.getTheFamilyMember(userId,hrEisEmpNomineeDtls.getFamilyMemberId());
						
						logger.info("==================== familyLstObj ================="+ familyLstObj.size());

						if(familyLstObj!=null && !familyLstObj.isEmpty())
						{
							HrEisFamilyDtl hrEisEmpFamilyDtls = (HrEisFamilyDtl)familyLstObj.get(0);

							if(hrEisEmpFamilyDtls.getFmMiddleName()!=null)
							{
								hrEisEmpNomineeDtls.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmMiddleName() +" " +hrEisEmpFamilyDtls.getFmLastName());				
							}
							else
							{
								hrEisEmpNomineeDtls.setNomnName(hrEisEmpFamilyDtls.getFmFirstName() + " " +hrEisEmpFamilyDtls.getFmLastName());
							}
									
							hrEisEmpNomineeDtls.setNomnDob(StringUtility.convertStringToDate(sdf.format(hrEisEmpFamilyDtls.getFmDateOfBirth())));

							CmnLookupMst cmnLookupNomnRelation = hrEisEmpFamilyDtls.getCmnLookupMstByFmRelation();
							hrEisEmpNomineeDtls.setCmnLookupMstByNomnRelation(cmnLookupNomnRelation);					
							hrEisEmpNomineeDtls.setNomnOtherRelation(hrEisEmpFamilyDtls.getFmRelationOther());
							
							appLstObj.set(index, hrEisEmpNomineeDtls);
						}
					}									
				}
			}
			
			/***Show emp info jsp**/
			
			/*HrModEmpRlt hrModEmpRlt=new HrModEmpRlt();
			ApproveEducationDAO daoMod=new ApproveEducationDAOImpl(HrModEmpRlt.class,serv.getSessionFactory());
			ApproveEducationDAO daoEisOther = new ApproveEducationDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			ApproveEducationDAO gpfDaoDor=new ApproveEducationDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			List otherDtl=gpfDaoDor.getEmpDtls(userId);
			OrgEmpMst empDet = (OrgEmpMst) otherDtl.get(0);
			long empId=empDet.getEmpId();
			
			OrgEmpMst orgEmpMst=new OrgEmpMst();
			ApproveEducationDAO daoEmpMst=new ApproveEducationDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
			orgEmpMst=(OrgEmpMst)daoEmpMst.read(empId);
			
			HrModMst hrModMst=new HrModMst();
			hrModMst.setModId(300006);
			List mod=daoMod.getModEmpRlt(CorrsId, hrModMst);
			hrModEmpRlt=(HrModEmpRlt) mod.get(0);					
			hrModEmpRlt=(HrModEmpRlt)daoMod.read(CorrsId);
			
			GeneralEmpInfoVO EmpDetVO=new GeneralEmpInfoVO();
			EmpDetVO.setSalary(hrModEmpRlt.getBasicSal());
			EmpDetVO.setEmpName(orgEmpMst.getEmpFname()+ " " + orgEmpMst.getEmpMname()+ " " + orgEmpMst.getEmpLname());
			EmpDetVO.setDoj(orgEmpMst.getEmpDoj());
			EmpDetVO.setDor(orgEmpMst.getEmpSrvcExp());
			OrgDesignationMst orgDesigMst=hrModEmpRlt.getDsgnId();
			EmpDetVO.setDesigid(orgDesigMst.getDsgnId());
			
			GeneralEmpInfoDaoImpl GemEmpinfo = new GeneralEmpInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
			
			EmpDetVO= GemEmpinfo.findByEmpIDOtherDetail(userId, langId);//current data (address)
			try
			{
				HrEisScaleMst eisScaleMst=new HrEisScaleMst();
				eisScaleMst.setScaleId(hrModEmpRlt.getScaleId().getScaleId());
			}
			catch(Exception e)
			{}
			
			List otherList=daoEisOther.getEmpOtherDtls(empId);
			if(otherList.size()!=0)
			{
				HrEisOtherDtls eisOtherDtls=(HrEisOtherDtls)otherList.get(0);
				EmpDetVO.setScaleStart(eisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst()
						.getScaleStartAmt());
				EmpDetVO.setScaleInc(eisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst()
						.getScaleIncrAmt());
				EmpDetVO.setScaleEnd(eisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst()
						.getScaleEndAmt());
			}
			
			try
			{
				if(EmpDetVO!=null && EmpDetVO.getAddressId()!=0)
				{
		            AddressServiceImpl addressServiceImpl = new AddressServiceImpl();  
		            AddressDAO addressDAO = new AddressDAOImpl(CmnAddressMst.class,serv.getSessionFactory());
		            CmnAddressMst cmnAddressMst =  addressDAO.read(EmpDetVO.getAddressId());
	            	String stringAddress = addressServiceImpl.getAddressDescription(cmnAddressMst,objectArgs);
	            	objectArgs.put("address", stringAddress);
				}
			}
			catch(Exception e)
			{
				e.getMessage();
			}
			
			objectArgs.put("EmpDet", EmpDetVO);*/
			
			objectArgs.put("modId",300006l);
			objectArgs.put("requestId", CorrsId);
			objectArgs.put("forUserId", userId);
			objRes = serv.executeService("SHOW_INBOX_EMP_DETAIL", objectArgs);
			
			/***end of Show emp info jsp**/
			
			List relationList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("fm_Relation", langId);	
			List purposeLst = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Purpose_of_Nomination",langId);
			objectArgs.put("Relation",relationList);			
			objectArgs.put("purposeLst",purposeLst);
			objectArgs.put("pendingLstObj",pendingLstObj);	
			objectArgs.put("ApprovedData",appLstObj);
			objectArgs.put("reqId",reqId);
			objectArgs.put("empId",userId);
			objRes.setViewName("ShowNomineeDtls");
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
		}catch(Exception e)
		{
			objRes.setResultCode(ErrorConstants.ERROR);
			logger.info("Error in ShowNomineeDtlsForApprove  ",e);
		}
		return objRes;
	}	
	
	
	public ResultObject forwardNomineeDtlsForApproval(Map<String, Object> objectArgs)//Added By Sunil
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{
			logger.info("forwardNomineeDtlsForApproval==============Inside==");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");				
			long CorrsId = 0;
			try
			{
				CorrsId = Long.parseLong(objectArgs.get("corrsIdStr").toString());
			}
			catch(Exception e){logger.error(e);}
			logger.info("corrs_Id============"+CorrsId);

			String approveDtls = objectArgs.get("approveDtls").toString();
			logger.info("approveDtls in frdto============"+approveDtls);
			
			ApproveNomineeDtlsDAO appNomineeDAOImpl = new ApproveNomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
			long reqId =appNomineeDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 

			List pendingReqlstObj = appNomineeDAOImpl.getNomineePendingDtlsOnReqId(reqId);
			
			ListIterator lItrHrEisNomineeDtlTxn = pendingReqlstObj.listIterator();			

			logger.info("pendingReqlstObj=======Size"+pendingReqlstObj.size());
			//HrEisNomineeDtlTxn hrEisEmpNomineeDtlTxn =  null;
			HrEisNomineeDtlTxn hrEisEmpNomineeDtlTxn =  new HrEisNomineeDtlTxn();
			StringTokenizer strTknSrNo = new StringTokenizer(approveDtls,",");

			List<Long> listNomineeSrNo = new ArrayList<Long>();

			while(strTknSrNo.hasMoreTokens())
				listNomineeSrNo.add(Long.parseLong(strTknSrNo.nextElement().toString().trim()));

			logger.info("============= listQualSrNo================"+ listNomineeSrNo);

			while (lItrHrEisNomineeDtlTxn.hasNext())
			{
				hrEisEmpNomineeDtlTxn = (HrEisNomineeDtlTxn)lItrHrEisNomineeDtlTxn.next();

				Long lngSrNo = hrEisEmpNomineeDtlTxn.getId().getMemberId();
				logger.info("lngSrNo==========="+lngSrNo);

				if (listNomineeSrNo.contains(lngSrNo))
				{
					hrEisEmpNomineeDtlTxn.setCheckStatus("Y");
					logger.info("lngSrNo== INSide If=========");
				}
				else
				{
					hrEisEmpNomineeDtlTxn.setCheckStatus("N");
					logger.info("lngSrNo== INSide else=========");
				}

				appNomineeDAOImpl.update(hrEisEmpNomineeDtlTxn);
			}
			//objectArgs.put("approveDtls",approveDtls);
			//objRes=getEducationPendingDtls(objectArgs);
			objRes.setResultValue(objectArgs);			
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			/*objRes.setViewName("ShowEducationDtls");	*/
		}catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while forwarding Corrspondance Request to next ApproveNomineeDtlsServiceImpl",e);
		}		
		return objRes;
	}
	/*Divyesh - Ends*/
	/*Divyesh - Starts*/
	public ResultObject saveNomineeApproveDtls(Map<String, Object> objectArgs)	//Added By Sunil
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{
			logger.info("========In saveEduApproveDtls============");
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");				
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());				 

			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			
			/* Get The Person Post */
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			/* End of the geting Person Post Code */
			
			long locId =  Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			OrgPostMstDaoImpl orgDaoImpl  = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMstVoFrom =  orgDaoImpl.getPrimaryPost(StringUtility.convertToLong(loginMap.get("userId").toString()), langId);
			
			
			long CorrsId =Long.parseLong(objectArgs.get("corrId").toString());
			logger.info("corrs_Id============"+CorrsId);
			
			/**Start===Getting a Request Id from CorrsId*/
			ApproveNomineeDtlsDAOImpl approveNomineeDtlsDAOImpl = new  ApproveNomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
			long reqId =approveNomineeDtlsDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 
					
			List pendingLstObj = approveNomineeDtlsDAOImpl.getNomineePendingDtlsOnReqId(reqId);
			ListIterator li = pendingLstObj.listIterator();
			HrEisNomineeDtl hrEisEmpNomineeDtls = null;
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
			ApproveNomineeDtlsDAOImpl dtlsDAOImpl = new ApproveNomineeDtlsDAOImpl(HrEisNomineeDtl.class,serv.getSessionFactory());
			HrEisNomineeDtl dtlsId = null;
				
			while(li.hasNext())
			{
				HrEisNomineeDtlTxn hrEisEmpNomineeDtlsTrn= (HrEisNomineeDtlTxn)li.next();
				boolean flag=true;
				if (hrEisEmpNomineeDtlsTrn.getCheckStatus().equals("Y"))
				{
					flag=false;
				}
				if(flag==true)
				{
					hrEisEmpNomineeDtlsTrn.setActionFlag("R");
					//approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtlsTrn);
				}
				else if(flag==false)
				{					
					if("A".equalsIgnoreCase(hrEisEmpNomineeDtlsTrn.getRequestFlag()))
					{
						/*objectArgs.put("tablename", "hr_eis_nominee_dtl");
			    		objectArgs.put("serviceLocator", serv);		
			    		ResultObject resultObj = serv.executeService("GENERATE_ID_SRVC", objectArgs);				
			    		int receivedcode1 = resultObj.getResultCode();
			    		if (receivedcode1 == -1) {
			    		 return objRes;
			    		}
			    		Map resultMap = (Map) resultObj.getResultValue();				
			    		long maxMemberId= (Long) resultMap.get("newID");*/
						long maxMemberId = IDGenerateDelegate.getNextId("hr_eis_nominee_dtl", objectArgs);
						hrEisEmpNomineeDtls = new HrEisNomineeDtl();													
						/*Getting Max Member ID */	
						
						/*End of Getting a  Max Memeber Id */
						hrEisEmpNomineeDtls.setOrgUserMstByUserId(hrEisEmpNomineeDtlsTrn.getOrgUserMstByUserId());						
						hrEisEmpNomineeDtls.setMemberId(maxMemberId);							
						
						hrEisEmpNomineeDtls.setFamilyMemberId(hrEisEmpNomineeDtlsTrn.getFamilyMemberId());
						if(hrEisEmpNomineeDtlsTrn.getFamilyMemberId()==0)
						{
							hrEisEmpNomineeDtls.setCmnLookupMstByNomnRelation(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation());
							hrEisEmpNomineeDtls.setCmnAddressMstByNomnAddress(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByNomnAddress());
							hrEisEmpNomineeDtls.setNomnDob(hrEisEmpNomineeDtlsTrn.getNomnDob());
							hrEisEmpNomineeDtls.setNomnName(hrEisEmpNomineeDtlsTrn.getNomnName());
							hrEisEmpNomineeDtls.setNomnOtherRelation(hrEisEmpNomineeDtlsTrn.getNomnOtherRelation());
						}
						else
						{
							hrEisEmpNomineeDtls.setNomnDob(null);
							hrEisEmpNomineeDtls.setNomnName(null);
							hrEisEmpNomineeDtls.setNomnOtherRelation(null);			    			 
							hrEisEmpNomineeDtls.setCmnLookupMstByNomnRelation(null);
							hrEisEmpNomineeDtls.setCmnAddressMstByNomnAddress(null);
						}
						hrEisEmpNomineeDtls.setCmnAddressMstByGuardianAddress(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByGuardianAddress());						
						hrEisEmpNomineeDtls.setCmnLookupMstByNomnBenefitTypeId(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnBenefitTypeId());
						
						hrEisEmpNomineeDtls.setCmnLookupMstByGuardianRelation(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByGuardianRelation());
						hrEisEmpNomineeDtls.setGuardianFirstname(hrEisEmpNomineeDtlsTrn.getGuardianFirstname());
						hrEisEmpNomineeDtls.setGuardianLastname(hrEisEmpNomineeDtlsTrn.getGuardianLastname());
						hrEisEmpNomineeDtls.setGuardianMiddlename(hrEisEmpNomineeDtlsTrn.getGuardianMiddlename());
						hrEisEmpNomineeDtls.setGuardianRelationOther(hrEisEmpNomineeDtlsTrn.getGuardianRelationOther());												
						hrEisEmpNomineeDtls.setNomnMinor(hrEisEmpNomineeDtlsTrn.getNomnMinor());						
						hrEisEmpNomineeDtls.setNomnSharePercent(hrEisEmpNomineeDtlsTrn.getNomnSharePercent());						
						
						hrEisEmpNomineeDtls.setCmnDatabaseMst(cmnDatabaseMst);
						hrEisEmpNomineeDtls.setCmnLocationMst(cmnLocationMst);
						hrEisEmpNomineeDtls.setCreatedDate(currDate);
						hrEisEmpNomineeDtls.setOrgUserMstByCreatedBy(orgUserMst);
						hrEisEmpNomineeDtls.setOrgPostMstByCreatedByPost(orgPostMstVoFrom);						
						hrEisEmpNomineeDtls.setDeleteFlag("N");
						hrEisEmpNomineeDtls.setTrnCounter(Integer.valueOf(1));
						
						approveNomineeDtlsDAOImpl.create(hrEisEmpNomineeDtls);
						
						hrEisEmpNomineeDtlsTrn.setActionFlag("A");
						//approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtlsTrn);
					}
					else if("U".equalsIgnoreCase(hrEisEmpNomineeDtlsTrn.getRequestFlag()))
					{							
						dtlsId = new HrEisNomineeDtl();
						dtlsId.setOrgUserMstByUserId(hrEisEmpNomineeDtlsTrn.getOrgUserMstByUserId());
						dtlsId.setMemberId(hrEisEmpNomineeDtlsTrn.getRowNumber());
						hrEisEmpNomineeDtls=(HrEisNomineeDtl)dtlsDAOImpl.read(dtlsId.getMemberId());
						
						hrEisEmpNomineeDtls.setFamilyMemberId(hrEisEmpNomineeDtlsTrn.getFamilyMemberId());
						hrEisEmpNomineeDtls.setCmnAddressMstByGuardianAddress(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByGuardianAddress());						
						hrEisEmpNomineeDtls.setCmnLookupMstByNomnBenefitTypeId(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnBenefitTypeId());
						hrEisEmpNomineeDtls.setCmnLookupMstByGuardianRelation(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByGuardianRelation());
						hrEisEmpNomineeDtls.setGuardianFirstname(hrEisEmpNomineeDtlsTrn.getGuardianFirstname());
						hrEisEmpNomineeDtls.setGuardianLastname(hrEisEmpNomineeDtlsTrn.getGuardianLastname());
						hrEisEmpNomineeDtls.setGuardianMiddlename(hrEisEmpNomineeDtlsTrn.getGuardianMiddlename());
						hrEisEmpNomineeDtls.setGuardianRelationOther(hrEisEmpNomineeDtlsTrn.getGuardianRelationOther());						
						hrEisEmpNomineeDtls.setNomnMinor(hrEisEmpNomineeDtlsTrn.getNomnMinor());
						hrEisEmpNomineeDtls.setNomnSharePercent(hrEisEmpNomineeDtlsTrn.getNomnSharePercent());						
						hrEisEmpNomineeDtls.setOrgUserMstByUserId(hrEisEmpNomineeDtlsTrn.getOrgUserMstByUserId());						

						if(hrEisEmpNomineeDtlsTrn.getFamilyMemberId()==0)
						{
							hrEisEmpNomineeDtls.setCmnLookupMstByNomnRelation(hrEisEmpNomineeDtlsTrn.getCmnLookupMstByNomnRelation());
							hrEisEmpNomineeDtls.setCmnAddressMstByNomnAddress(hrEisEmpNomineeDtlsTrn.getCmnAddressMstByNomnAddress());
							hrEisEmpNomineeDtls.setNomnDob(hrEisEmpNomineeDtlsTrn.getNomnDob());
							hrEisEmpNomineeDtls.setNomnName(hrEisEmpNomineeDtlsTrn.getNomnName());
							hrEisEmpNomineeDtls.setNomnOtherRelation(hrEisEmpNomineeDtlsTrn.getNomnOtherRelation());
						}
						else
						{
							hrEisEmpNomineeDtls.setNomnDob(null);
							hrEisEmpNomineeDtls.setNomnName(null);
							hrEisEmpNomineeDtls.setNomnOtherRelation(null);			    			 
							hrEisEmpNomineeDtls.setCmnLookupMstByNomnRelation(null);
							hrEisEmpNomineeDtls.setCmnAddressMstByNomnAddress(null);
						}
						
						hrEisEmpNomineeDtls.setCmnDatabaseMst(cmnDatabaseMst);
						hrEisEmpNomineeDtls.setCmnLocationMst(cmnLocationMst);
						hrEisEmpNomineeDtls.setUpdatedDate(currDate);
						hrEisEmpNomineeDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						hrEisEmpNomineeDtls.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
						
						approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtls);
						
						hrEisEmpNomineeDtlsTrn.setActionFlag("A");
						//approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtlsTrn);
					}
					else if("D".equalsIgnoreCase(hrEisEmpNomineeDtlsTrn.getRequestFlag()))
					{
						hrEisEmpNomineeDtls=(HrEisNomineeDtl)dtlsDAOImpl.read(hrEisEmpNomineeDtlsTrn.getRowNumber());
						hrEisEmpNomineeDtls.setDeleteFlag("Y");
						hrEisEmpNomineeDtls.setUpdatedDate(currDate);
						hrEisEmpNomineeDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						hrEisEmpNomineeDtls.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
						
						approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtls);
						
						hrEisEmpNomineeDtlsTrn.setActionFlag("A");
						//approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtlsTrn);
					}
				}
				hrEisEmpNomineeDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpNomineeDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				hrEisEmpNomineeDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMst);
				approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtlsTrn);
			}
		}catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while setting a flag for Emp Nominee Service in VOGEN ",e);
		}
		return objRes;
	}
	/*Divyesh - Ends*/
	
	public ResultObject rejectNomineeDtlsReqest(Map<String, Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
			
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());	
			OrgPostMstDaoImpl orgDaoImpl  = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMstVoFrom =  orgDaoImpl.getPrimaryPost(StringUtility.convertToLong(loginMap.get("userId").toString()), langId);

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			
			long CorrsId =Long.parseLong(objectArgs.get("corrId").toString());
			logger.info("corrs_Id In rejectNomineeDtlsReqest ============"+CorrsId);
			
			/**Start===Getting a Request Id from CorrsId*/
			ApproveNomineeDtlsDAOImpl approveNomineeDtlsDAOImpl = new  ApproveNomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
			long reqId =approveNomineeDtlsDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 
			List nomineePendingLstObj = approveNomineeDtlsDAOImpl.getNomineePendingDtlsOnReqId(reqId);
			logger.info("rejectNomineeDtlsReqest Size========"+nomineePendingLstObj.size());
			
			ListIterator nomineeListIter = nomineePendingLstObj.listIterator();
			HrEisNomineeDtlTxn hrEisEmpNomineeDtlsTrn=null;
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
			while(nomineeListIter.hasNext())
			{
				hrEisEmpNomineeDtlsTrn= (HrEisNomineeDtlTxn)nomineeListIter.next();
				hrEisEmpNomineeDtlsTrn.setActionFlag("R");
				hrEisEmpNomineeDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpNomineeDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
				hrEisEmpNomineeDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtlsTrn);
			}
		}
		catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Rejecting Nominee Corrspondance in ApproveFamilyDtlsServiceImpl",e);
		}
		return objRes;
	}
	
	public ResultObject cancleNomineeRequestSRVC(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
			
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());	
			OrgPostMstDaoImpl orgDaoImpl  = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMstVoFrom =  orgDaoImpl.getPrimaryPost(StringUtility.convertToLong(loginMap.get("userId").toString()), langId);

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			
			long CorrsId =Long.parseLong(objectArgs.get("corrId").toString());
			logger.info("corrs_Id In Cancel NomineeDtlsReqest ============"+CorrsId);
			
			/**Start===Getting a Request Id from CorrsId*/
			ApproveNomineeDtlsDAOImpl approveNomineeDtlsDAOImpl = new  ApproveNomineeDtlsDAOImpl(HrEisNomineeDtlTxn.class,serv.getSessionFactory());
			long reqId =approveNomineeDtlsDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 
			List nomineePendingLstObj = approveNomineeDtlsDAOImpl.getNomineePendingDtlsOnReqId(reqId);
			logger.info("Cancel NomineeDtlsReqest Size========"+nomineePendingLstObj.size());
			
			ListIterator nomineeListIter = nomineePendingLstObj.listIterator();
			HrEisNomineeDtlTxn hrEisEmpNomineeDtlsTrn=null;
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
			while(nomineeListIter.hasNext())
			{
				hrEisEmpNomineeDtlsTrn= (HrEisNomineeDtlTxn)nomineeListIter.next();
				hrEisEmpNomineeDtlsTrn.setActionFlag("C");
				hrEisEmpNomineeDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpNomineeDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
				hrEisEmpNomineeDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				approveNomineeDtlsDAOImpl.update(hrEisEmpNomineeDtlsTrn);
			}
		} catch (Exception e) 
		{
			logger.error("Error in Cancel NomineeRequestSRVC method  "+e);
		}
		return objRes;
	}
}
