package com.tcs.sgv.eis.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.tcs.sgv.eis.dao.ApproveFamilyDtlsDAO;
import com.tcs.sgv.eis.dao.ApproveFamilyDtlsDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtlHst;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxn;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
 
public class ApproveFamilyDtlsServiceImpl extends ServiceImpl implements ApproveFamilyDtlsService
{
	private static final Log logger = LogFactory.getLog(ApproveFamilyDtlsServiceImpl.class);	

	
	public ResultObject getApproveFamilyDtls(Map<String, Object> objectArgs)  //300441
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{									
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			ApproveFamilyDtlsDAOImpl appFmDtlsDAOImpl = new ApproveFamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
			List familyPendinglstObj = appFmDtlsDAOImpl.getAllFamilyPendingReq();
			ListIterator li = familyPendinglstObj.listIterator();
			List<Object[]> temp = new ArrayList<Object[]>();
			while(li.hasNext())
			{
				Object[] row = (Object[])li.next();						
				OrgEmpMst og= (OrgEmpMst) row[2];
				row[2] = og.getEmpId();				
				temp.add(row);				
			}
			objectArgs.put("FamilyDtls", temp);			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("ApproveFamilyDtls");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			logger.info("Error Whiel Getting a Family Pending Dtls For Approve",e);									
			objRes.setResultCode(ErrorConstants.ERROR);
		}
		return objRes;
	}
	
	public ResultObject ShowFamilyDtls(Map<String, Object> objectArgs)  //300445
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try{							
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());

			long CorrsId = 0;
			try
			{
				CorrsId = Long.parseLong(objectArgs.get("corrsIdStr").toString());
			}
			catch(Exception e){logger.error(e);}
			logger.info("CorrsId======In Service====="+CorrsId);
			ApproveFamilyDtlsDAOImpl appFmDtlsDAOImpl = new ApproveFamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
			long lreqId = appFmDtlsDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 

			List lstFamilyPendinglstObj = appFmDtlsDAOImpl.getPendingReqData(lreqId);
			List<HrEisFamilyDtlTxn> familyPendinglstObj =new ArrayList<HrEisFamilyDtlTxn>();
			long userIdLongObj=0;
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			if(!lstFamilyPendinglstObj.isEmpty())
			{
				HrEisFamilyDtlTxn hrEisEmpFamilyDtlsTrn = null;
				
				for (Iterator iterPendingRec = lstFamilyPendinglstObj.iterator(); iterPendingRec.hasNext();) 
				{
					hrEisEmpFamilyDtlsTrn = (HrEisFamilyDtlTxn)iterPendingRec.next();

					if (hrEisEmpFamilyDtlsTrn.getCmnCountryMstByFmNationality() != null)
					{
						hrEisEmpFamilyDtlsTrn.getCmnCountryMstByFmNationality().getCountryName();
					}
					
					if(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmOccupation()!=null)
					{
						String strOccuLookupName = hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmOccupation() != null ? hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmOccupation().getLookupName() : "select";
						if(!"".equals(strOccuLookupName))
						{
							hrEisEmpFamilyDtlsTrn.setCmnLookupMstByFmOccupation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strOccuLookupName, langId));
						}
					}
					
					if(hrEisEmpFamilyDtlsTrn.getCmnLookupMstBySubQualification()!=null)
					{
						String strSubQualificationLookupName = hrEisEmpFamilyDtlsTrn.getCmnLookupMstBySubQualification() != null ? hrEisEmpFamilyDtlsTrn.getCmnLookupMstBySubQualification().getLookupName() : "select";
						if(!"".equals(strSubQualificationLookupName))
						{
							hrEisEmpFamilyDtlsTrn.setCmnLookupMstBySubQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strSubQualificationLookupName, langId));
						}
					}
					
					if(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByDiscipline()!=null)
					{
						String strDisciplineLookupName = hrEisEmpFamilyDtlsTrn.getCmnLookupMstByDiscipline() != null ? hrEisEmpFamilyDtlsTrn.getCmnLookupMstByDiscipline().getLookupName() : "select";
						if(!"".equals(strDisciplineLookupName))
						{
							hrEisEmpFamilyDtlsTrn.setCmnLookupMstByDiscipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strDisciplineLookupName, langId));
						}
					}
					
					familyPendinglstObj.add(hrEisEmpFamilyDtlsTrn);
					userIdLongObj = hrEisEmpFamilyDtlsTrn.getOrgUserMstByUserId().getUserId();
				}
				
				objectArgs.put("FamilyDtls", familyPendinglstObj);		
			}
			
			ApproveFamilyDtlsDAOImpl approveFamilyDtlsDAOImpl = new ApproveFamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			List lstFamilyDtlsLstObj = approveFamilyDtlsDAOImpl.getEmpApprovedFamilyDtlsForApprovePage(lreqId);
			List<HrEisFamilyDtlHst> lstFamilyDtlsLstObjHst = new ArrayList<HrEisFamilyDtlHst>();
			
			
			
			if(lstFamilyDtlsLstObj!=null)
			{
				for (Iterator iter = lstFamilyDtlsLstObj.iterator(); iter.hasNext();) 
				{
					HrEisFamilyDtlHst hrEisFamilyDtlHst = (HrEisFamilyDtlHst) iter.next();

					if (hrEisFamilyDtlHst.getCmnCountryMstByFmNationality() != null)
					{
						hrEisFamilyDtlHst.getCmnCountryMstByFmNationality().getCountryName();
					}
					
					/*String addressDesc=objAddressServiceImpl.getAddressDescription(hrEisFamilyDtlHst.getCmnAddressMst(),objectArgs);
					logger.info("getAddressDescription ==========="+addressDesc);
					hrEisFamilyDtlHst.setAddressDesc(addressDesc);*/
					
					if(hrEisFamilyDtlHst.getCmnLookupMstByFmOccupation()!=null)
					{
						String strOccuLookupName = hrEisFamilyDtlHst.getCmnLookupMstByFmOccupation() != null ? hrEisFamilyDtlHst.getCmnLookupMstByFmOccupation().getLookupName() : "select";
						if(!"".equals(strOccuLookupName))
						{
							hrEisFamilyDtlHst.setCmnLookupMstByFmOccupation(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strOccuLookupName, langId));
						}
					}
					if(hrEisFamilyDtlHst.getCmnLookupMstBySubQualification()!=null)
					{
						String strSubQualificationLookupName = hrEisFamilyDtlHst.getCmnLookupMstBySubQualification() != null ? hrEisFamilyDtlHst.getCmnLookupMstBySubQualification().getLookupName() : "select";
						if(!"".equals(strSubQualificationLookupName))
						{
							hrEisFamilyDtlHst.setCmnLookupMstBySubQualification(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strSubQualificationLookupName, langId));
						}
					}
					
					if(hrEisFamilyDtlHst.getCmnLookupMstByDiscipline()!=null)
					{
						String strDisciplineLookupName = hrEisFamilyDtlHst.getCmnLookupMstByDiscipline() != null ? hrEisFamilyDtlHst.getCmnLookupMstByDiscipline().getLookupName() : "select";
						if(!"".equals(strDisciplineLookupName))
						{
							hrEisFamilyDtlHst.setCmnLookupMstByDiscipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strDisciplineLookupName, langId));
						}
					}
					lstFamilyDtlsLstObjHst.add(hrEisFamilyDtlHst);
				}
				objectArgs.put("MstFamilyDtls", lstFamilyDtlsLstObjHst);
			}	
			
			/***Show emp info jsp**/
			objectArgs.put("modId",300007l);
			objectArgs.put("requestId", CorrsId);
			objectArgs.put("forUserId", userIdLongObj);
			objRes = serv.executeService("SHOW_INBOX_EMP_DETAIL", objectArgs);
			/***end of Show emp info jsp**/

			objectArgs.put("reqId",lreqId);		
			FamilyDetailsServiceImpl fmDetailsServiceImpl = new FamilyDetailsServiceImpl();
			objRes=fmDetailsServiceImpl.getComboDtls(objectArgs,true,userIdLongObj);
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("ShowFamilyDtlsForApprove");
		}catch(Exception  e)
		{
			objRes.setThrowable(e);
			logger.info("Error Whiel showing a Family Pending Dtls For Approve",e);									
			objRes.setResultCode(ErrorConstants.ERROR);	
		}
		return objRes;
	}
	
	/*Sunil - Starts*/
	public ResultObject forwardFamilyDtlsForApproval(Map<String, Object> objectArgs)//Added By Sunil
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{
			logger.info("forward Family Dtls For Approval==============Inside==");
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
			
			ApproveFamilyDtlsDAO appFamilyDAOImpl = new ApproveFamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
			long reqId =appFamilyDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 

			List pendingReqlstObj = appFamilyDAOImpl.getPendingReqData(reqId);
			
			ListIterator lItrHrEisFamilyDtlTxn = pendingReqlstObj.listIterator();			

			logger.info("pendingReqlstObj=======Size"+pendingReqlstObj.size());
			//HrEisNomineeDtlTxn hrEisEmpNomineeDtlTxn =  null;
			HrEisFamilyDtlTxn hrEisEmpFamilyDtlTxn =  new HrEisFamilyDtlTxn();
			StringTokenizer strTknSrNo = new StringTokenizer(approveDtls,",");

			List<Long> listFamilySrNo = new ArrayList<Long>();

			while(strTknSrNo.hasMoreTokens())
				listFamilySrNo.add(Long.parseLong(strTknSrNo.nextElement().toString().trim()));

			logger.info("============= listQualSrNo================"+ listFamilySrNo);

			while (lItrHrEisFamilyDtlTxn.hasNext())
			{
				hrEisEmpFamilyDtlTxn = (HrEisFamilyDtlTxn)lItrHrEisFamilyDtlTxn.next();

				Long lngSrNo = hrEisEmpFamilyDtlTxn.getId().getMemberId();
				logger.info("lngSrNo==========="+lngSrNo);

				if (listFamilySrNo.contains(lngSrNo))
				{
					hrEisEmpFamilyDtlTxn.setCheckStatus("Y");
					logger.info("lngSrNo== INSide If=========");
				}
				else
				{
					hrEisEmpFamilyDtlTxn.setCheckStatus("N");
					logger.info("lngSrNo== INSide else=========");
				}
				appFamilyDAOImpl.update(hrEisEmpFamilyDtlTxn);
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
			logger.error("Exception while forwarding Corrspondance Request to next ApproveFamilyDtlsServiceImpl",e);
		}		
		return objRes;
	}
	/*Sunil - Ends*/
	
	/*Sunil - Starts*/
	public ResultObject saveFamilyApproveDtls(Map<String, Object> objectArgs)	//Added By Sunil
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{
			logger.info("========In saveEduApproveDtls============");
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");				
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
		
			String empId=  objectArgs.get("empId").toString();			
			long luserId =Long.parseLong(empId.trim());
			
			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			orgEmpMst.setEmpId(luserId);
			
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			
			/* Get The Person Post */
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			/* End of the geting Person Post Code */

			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

			long locId =  Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			long CorrsId =Long.parseLong(objectArgs.get("corrId").toString());
			logger.info("corrs_Id============"+CorrsId);
			
			/**Start===Getting a Request Id from CorrsId*/
			
			ApproveFamilyDtlsDAO approveFamilyDtlsDAOImpl = new  ApproveFamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
			long lreqId =approveFamilyDtlsDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 
			
			
			ApproveFamilyDtlsDAOImpl appFmDtlsDAOImpl = new ApproveFamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			long member_ID=-1;									
			
			List familyPendinglstObj = appFmDtlsDAOImpl.getFamilyPendingDtlsOnReqId(lreqId);
			ListIterator li=familyPendinglstObj.listIterator();		
			logger.info("familyListObj= In Service========"+familyPendinglstObj.size());
			HrEisFamilyDtl hrEisEmpFamilyDtls = new HrEisFamilyDtl();
			Date currDate = new Date();
			
			while(li.hasNext())
			{				
				HrEisFamilyDtlTxn hrEisEmpFamilyDtlsTrn =(HrEisFamilyDtlTxn) li.next();
				boolean flag=true;
				logger.info("=========hrEisEmpQuaDtlsTrn.getCheckStatus()==========="+hrEisEmpFamilyDtlsTrn.getCheckStatus());
				if (hrEisEmpFamilyDtlsTrn.getCheckStatus().equals("Y"))
				{
					flag=false;
					member_ID = hrEisEmpFamilyDtlsTrn.getId().getMemberId();
				}

				logger.info("=========hrEisEmpQuaDtlsTrn.getRequestFlag()==========="+ hrEisEmpFamilyDtlsTrn.getRequestFlag());
				logger.info("=========flag==========="+ flag);
				logger.info("=========member_ID==========="+ member_ID);
				
				if(hrEisEmpFamilyDtlsTrn.getRequestFlag().equalsIgnoreCase("U") && flag==false && member_ID!=-1)
				{
					hrEisEmpFamilyDtls = appFmDtlsDAOImpl.getMasterRecordForDeleteOrUpdate(hrEisEmpFamilyDtlsTrn.getRowNumber(),hrEisEmpFamilyDtlsTrn.getOrgUserMstByUserId().getUserId());
					if(hrEisEmpFamilyDtls!=null)
					{
						hrEisEmpFamilyDtls.setCmnAddressMst(hrEisEmpFamilyDtlsTrn.getCmnAddressMst());
						hrEisEmpFamilyDtls.setFmFirstName(hrEisEmpFamilyDtlsTrn.getFmFirstName());					
						hrEisEmpFamilyDtls.setFmMiddleName(hrEisEmpFamilyDtlsTrn.getFmMiddleName());					
						hrEisEmpFamilyDtls.setFmLastName(hrEisEmpFamilyDtlsTrn.getFmLastName());	
						hrEisEmpFamilyDtls.setFmRemarks(hrEisEmpFamilyDtlsTrn.getFmRemarks());
						hrEisEmpFamilyDtls.setCmnLookupMstByFmDeadOrAlive(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmDeadOrAlive());	
						hrEisEmpFamilyDtls.setFmRelationOther(hrEisEmpFamilyDtlsTrn.getFmRelationOther());
						hrEisEmpFamilyDtls.setFmDateOfBirth(hrEisEmpFamilyDtlsTrn.getFmDateOfBirth());

						hrEisEmpFamilyDtls.setCompanyName(hrEisEmpFamilyDtlsTrn.getCompanyName());
						hrEisEmpFamilyDtls.setAnnualIncome(hrEisEmpFamilyDtlsTrn.getAnnualIncome());
						hrEisEmpFamilyDtls.setDesignation(hrEisEmpFamilyDtlsTrn.getDesignation());
						hrEisEmpFamilyDtls.setDateOfDemise(hrEisEmpFamilyDtlsTrn.getDateOfDemise());
						hrEisEmpFamilyDtls.setDependentOrNot(hrEisEmpFamilyDtlsTrn.getDependentOrNot());
						hrEisEmpFamilyDtls.setCmnAttachmentMst(hrEisEmpFamilyDtlsTrn.getCmnAttachmentMst());												
						if(hrEisEmpFamilyDtlsTrn.getDependentOrNot().equalsIgnoreCase("Y") && hrEisEmpFamilyDtls.getDependentOrNot().equalsIgnoreCase("N"))
						{	
							hrEisEmpFamilyDtls.setEndDate(null);
							hrEisEmpFamilyDtls.setStartDate(currDate);							
						}
						else if(hrEisEmpFamilyDtlsTrn.getDependentOrNot().equalsIgnoreCase("N") && hrEisEmpFamilyDtls.getDependentOrNot().equalsIgnoreCase("Y"))
						{		
							hrEisEmpFamilyDtls.setEndDate(currDate);							
						}
						
						hrEisEmpFamilyDtls.setCmnCountryMstByFmNationality(hrEisEmpFamilyDtlsTrn.getCmnCountryMstByFmNationality());//change by sunil for nationality
						hrEisEmpFamilyDtls.setOrgDepartmentMstByFmDept(hrEisEmpFamilyDtlsTrn.getOrgDepartmentMstByFmDept());// Change By sunil on 04/06/08 for Employment Dtls 
						
						hrEisEmpFamilyDtls.setCmnLookupMstByFmEmploymentStatus(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmEmploymentStatus());						
						hrEisEmpFamilyDtls.setCmnLookupMstByFmRelation(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmRelation());													
						hrEisEmpFamilyDtls.setCmnLookupMstByFmGender(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmGender());						
						hrEisEmpFamilyDtls.setCmnLookupMstByFmOccupation(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmOccupation());							
						hrEisEmpFamilyDtls.setCmnLookupMstByFmQualification(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmQualification());
						hrEisEmpFamilyDtls.setCmnLookupMstBySubQualification(hrEisEmpFamilyDtlsTrn.getCmnLookupMstBySubQualification());
						hrEisEmpFamilyDtls.setCmnLookupMstByDiscipline(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByDiscipline());
						hrEisEmpFamilyDtls.setCmnLookupMstByFmMaritalStatus(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmMaritalStatus());
						hrEisEmpFamilyDtls.setCmnAttachmentMst(hrEisEmpFamilyDtlsTrn.getCmnAttachmentMst());
						hrEisEmpFamilyDtls.setCmnLocationMst(cmnLocationMst);
						hrEisEmpFamilyDtls.setOrgUserMstByUserId(hrEisEmpFamilyDtlsTrn.getOrgUserMstByUserId());
						hrEisEmpFamilyDtls.setCmnDatabaseMst(cmnDatabaseMst);

						hrEisEmpFamilyDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						hrEisEmpFamilyDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						hrEisEmpFamilyDtls.setUpdatedDate(currDate);

						hrEisEmpFamilyDtls.setDeleteFlag("N");
						appFmDtlsDAOImpl.update(hrEisEmpFamilyDtls);														
					}
					hrEisEmpFamilyDtlsTrn.setActionFlag("A");
					//appFmDtlsDAOImpl.update(hrEisEmpFamilyDtlsTrn);						
				}
				else if(hrEisEmpFamilyDtlsTrn.getRequestFlag().equalsIgnoreCase("D") && flag==false && member_ID!=-1)
				{
					hrEisEmpFamilyDtls = appFmDtlsDAOImpl.getMasterRecordForDeleteOrUpdate(hrEisEmpFamilyDtlsTrn.getRowNumber(),hrEisEmpFamilyDtlsTrn.getOrgUserMstByUserId().getUserId());
					if(hrEisEmpFamilyDtls!=null)
					{	
						hrEisEmpFamilyDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						hrEisEmpFamilyDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						hrEisEmpFamilyDtls.setUpdatedDate(currDate);
						hrEisEmpFamilyDtls.setDeleteFlag("Y");						
						appFmDtlsDAOImpl.update(hrEisEmpFamilyDtls);
					}
					hrEisEmpFamilyDtlsTrn.setActionFlag("A");
					//appFmDtlsDAOImpl.update(hrEisEmpFamilyDtlsTrn);
				}
				else if(hrEisEmpFamilyDtlsTrn.getRequestFlag().equalsIgnoreCase("A") && flag==false)
				{
					/*objectArgs.put("tablename", "hr_eis_family_dtl");
					objectArgs.put("serviceLocator", serv);		
					ResultObject resultObj = serv.executeService("GENERATE_ID_SRVC", objectArgs);				
					int receivedcode1 = resultObj.getResultCode();
					if (receivedcode1 == -1) {
						return objRes;
					}
					Map resultMap = (Map) resultObj.getResultValue();				
					long srNo= (Long) resultMap.get("newID");*/	
					long srNo = IDGenerateDelegate.getNextId("hr_eis_family_dtl", objectArgs);
					logger.info("============Insertion of Family Member==============="+ srNo);
					
					hrEisEmpFamilyDtls = new HrEisFamilyDtl();

					hrEisEmpFamilyDtls.setCmnAddressMst(hrEisEmpFamilyDtlsTrn.getCmnAddressMst());
					hrEisEmpFamilyDtls.setFmFirstName(hrEisEmpFamilyDtlsTrn.getFmFirstName());					
					hrEisEmpFamilyDtls.setFmMiddleName(hrEisEmpFamilyDtlsTrn.getFmMiddleName());					
					hrEisEmpFamilyDtls.setFmLastName(hrEisEmpFamilyDtlsTrn.getFmLastName());	
					hrEisEmpFamilyDtls.setFmRemarks(hrEisEmpFamilyDtlsTrn.getFmRemarks());
					hrEisEmpFamilyDtls.setCmnLookupMstByFmDeadOrAlive(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmDeadOrAlive());	
					hrEisEmpFamilyDtls.setFmRelationOther(hrEisEmpFamilyDtlsTrn.getFmRelationOther());
					hrEisEmpFamilyDtls.setFmDateOfBirth(hrEisEmpFamilyDtlsTrn.getFmDateOfBirth());
					hrEisEmpFamilyDtls.setCompanyName(hrEisEmpFamilyDtlsTrn.getCompanyName());
					hrEisEmpFamilyDtls.setAnnualIncome(hrEisEmpFamilyDtlsTrn.getAnnualIncome());
					hrEisEmpFamilyDtls.setDesignation(hrEisEmpFamilyDtlsTrn.getDesignation());
					hrEisEmpFamilyDtls.setDateOfDemise(hrEisEmpFamilyDtlsTrn.getDateOfDemise());
					hrEisEmpFamilyDtls.setDependentOrNot(hrEisEmpFamilyDtlsTrn.getDependentOrNot());
					hrEisEmpFamilyDtls.setCmnAttachmentMst(hrEisEmpFamilyDtlsTrn.getCmnAttachmentMst());
					if(hrEisEmpFamilyDtlsTrn.getDependentOrNot().equalsIgnoreCase("Y"))
					{														
						hrEisEmpFamilyDtls.setEndDate(null);
						hrEisEmpFamilyDtls.setStartDate(currDate);							
					}						
					
					hrEisEmpFamilyDtls.setCmnCountryMstByFmNationality(hrEisEmpFamilyDtlsTrn.getCmnCountryMstByFmNationality());//change by sunil for nationality
					hrEisEmpFamilyDtls.setOrgDepartmentMstByFmDept(hrEisEmpFamilyDtlsTrn.getOrgDepartmentMstByFmDept());// Change By sunil on 04/06/08 for Employment Dtls
					hrEisEmpFamilyDtls.setCmnLookupMstByFmEmploymentStatus(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmEmploymentStatus());						
					hrEisEmpFamilyDtls.setCmnLookupMstByFmRelation(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmRelation());													
					hrEisEmpFamilyDtls.setCmnLookupMstByFmGender(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmGender());						
					hrEisEmpFamilyDtls.setCmnLookupMstByFmOccupation(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmOccupation());							
					hrEisEmpFamilyDtls.setCmnLookupMstByFmQualification(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmQualification());						
					hrEisEmpFamilyDtls.setCmnLookupMstByFmMaritalStatus(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByFmMaritalStatus());
					hrEisEmpFamilyDtls.setCmnLookupMstBySubQualification(hrEisEmpFamilyDtlsTrn.getCmnLookupMstBySubQualification());
					hrEisEmpFamilyDtls.setCmnLookupMstByDiscipline(hrEisEmpFamilyDtlsTrn.getCmnLookupMstByDiscipline());	
					hrEisEmpFamilyDtls.setCmnLocationMst(cmnLocationMst);
					hrEisEmpFamilyDtls.setOrgUserMstByUserId(hrEisEmpFamilyDtlsTrn.getOrgUserMstByUserId());
					hrEisEmpFamilyDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrEisEmpFamilyDtls.setOrgUserMstByCreatedBy(orgUserMst);
					hrEisEmpFamilyDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					hrEisEmpFamilyDtls.setCreatedDate(currDate);
					
					hrEisEmpFamilyDtls.setDeleteFlag("N");											
					/*Geting a Member Id  */										
					/* End of Getting a Member Id */												
					hrEisEmpFamilyDtls.setMemberId(srNo);
					hrEisEmpFamilyDtls.setTrnCounter(Integer.valueOf(1));

					appFmDtlsDAOImpl.create(hrEisEmpFamilyDtls);
					hrEisEmpFamilyDtlsTrn.setActionFlag("A");
					//appFmDtlsDAOImpl.update(hrEisEmpFamilyDtlsTrn);
				}
				else
				{	
					hrEisEmpFamilyDtlsTrn.setActionFlag("R");
					//appFmDtlsDAOImpl.update(hrEisEmpFamilyDtlsTrn);
				}
				hrEisEmpFamilyDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpFamilyDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				hrEisEmpFamilyDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMst);
				appFmDtlsDAOImpl.update(hrEisEmpFamilyDtlsTrn);
			}
		}catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Approving Family Corrspondance in ApproveFamilyDtlsServiceImpl",e);
		}
		return objRes;
	}
	/*Sunil - Ends*/
	
	public ResultObject rejectFamilyDtlsReqest(Map<String, Object> objectArgs)
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
			logger.info("corrs_Id In rejectFamilyDtlsReqest============"+CorrsId);

			/**Start===Getting a Request Id from CorrsId*/

			ApproveFamilyDtlsDAO approveFamilyDtlsDAOImpl = new  ApproveFamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
			long lreqId =approveFamilyDtlsDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 
			
			ApproveFamilyDtlsDAOImpl appFmDtlsDAOImpl = new ApproveFamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			List familyPendinglstObj = appFmDtlsDAOImpl.getFamilyPendingDtlsOnReqId(lreqId);
			logger.info("rejectFamilyDtlsReqest Size========"+familyPendinglstObj.size());
			ListIterator familyListItr=familyPendinglstObj.listIterator();		
			HrEisFamilyDtlTxn hrEisEmpFamilyDtlsTrn=null;
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
			while(familyListItr.hasNext())
			{				
				hrEisEmpFamilyDtlsTrn =(HrEisFamilyDtlTxn) familyListItr.next();
				hrEisEmpFamilyDtlsTrn.setActionFlag("R");
				hrEisEmpFamilyDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpFamilyDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
				hrEisEmpFamilyDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				appFmDtlsDAOImpl.update(hrEisEmpFamilyDtlsTrn);
			}
		}
		catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Rejecting Family Corrspondance in ApproveFamilyDtlsServiceImpl",e);
		}
		return objRes;
	}
	
	public ResultObject cancleFamilyRequestSRVC(Map<String, Object> objectArgs) 
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
			logger.info("corrs_Id In Cancellation FamilyDtlsReqest============"+CorrsId);

			/**Start===Getting a Request Id from CorrsId*/

			ApproveFamilyDtlsDAO approveFamilyDtlsDAOImpl = new  ApproveFamilyDtlsDAOImpl(HrEisFamilyDtlTxn.class,serv.getSessionFactory());
			long lreqId =approveFamilyDtlsDAOImpl.getCurrentRequestIdFromCorrsId(CorrsId); 
			
			ApproveFamilyDtlsDAOImpl appFmDtlsDAOImpl = new ApproveFamilyDtlsDAOImpl(HrEisFamilyDtl.class,serv.getSessionFactory());
			List familyPendinglstObj = appFmDtlsDAOImpl.getFamilyPendingDtlsOnReqId(lreqId);
			logger.info("CancileFamilyDtlsReqest Size========"+familyPendinglstObj.size());
			ListIterator familyListItr=familyPendinglstObj.listIterator();		
			HrEisFamilyDtlTxn hrEisEmpFamilyDtlsTrn=null;
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
			while(familyListItr.hasNext())
			{				
				hrEisEmpFamilyDtlsTrn =(HrEisFamilyDtlTxn) familyListItr.next();
				hrEisEmpFamilyDtlsTrn.setActionFlag("C");
				hrEisEmpFamilyDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpFamilyDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
				hrEisEmpFamilyDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				appFmDtlsDAOImpl.update(hrEisEmpFamilyDtlsTrn);
			}
		}catch (Exception e) 
		{
			logger.error("Error in cancel FamilyRequestSRVC method  "+e);
		}
		return objRes;
	}
}