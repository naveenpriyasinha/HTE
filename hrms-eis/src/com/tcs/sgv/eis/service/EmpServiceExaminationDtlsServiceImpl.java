package com.tcs.sgv.eis.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpServiceExaminationDtlsDAO;
import com.tcs.sgv.eis.dao.EmpServiceExaminationDtlsDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisSrvcexamDtl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmpServiceExaminationDtlsServiceImpl extends ServiceImpl implements EmpServiceExaminationDtlsService
{
	private static final Log logger = LogFactory.getLog(EmpServiceExaminationDtlsServiceImpl.class);
	
	public ResultObject getEmpExamDtls(Map<String, Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long userId = StringUtility.convertToLong(objectArgs.get("userId").toString());
				
				//------to get list of months from lookupmst for dropdown
				CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				List<CmnLookupMst> arMonthsInfo = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Mnth",langId);
				List<CmnLookupMst> arPreSrvcInfo=cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("yes/no_srvc", langId);
				List<CmnLookupMst> arClassDivInfo=cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("ClassDiv", langId);
				List<CmnLookupMst> arQualifInfo=cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("qualExam", langId);
				List<CmnLookupMst> arResultInfo=cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Result", langId);
				
				//----------to get year for dropdown---------------------		
				ArrayList lArrYrLst=new ArrayList();
				Calendar currentCal = Calendar.getInstance();
				int year = currentCal.get(Calendar.YEAR);
				logger.info("-----YEAR---------"+year);
				
				for(int yr=1970;yr<=year;yr++)
			    {
			    	lArrYrLst.add(yr);
			    }
				
			    //for getting data from DB
			    EmpServiceExaminationDtlsDAO ExamDAOImpl=new EmpServiceExaminationDtlsDAOImpl(HrEisSrvcexamDtl.class, serv.getSessionFactory());
			    List<HrEisSrvcexamDtl> ExamVOList=ExamDAOImpl.getExamDtlVOList(userId);

				HrEisSrvcexamDtl objExam=new HrEisSrvcexamDtl();
				ArrayList xmlFileNmExam = new ArrayList();
				for (Iterator j = ExamVOList.iterator(); j.hasNext();)
				{
					objExam =(HrEisSrvcexamDtl)j.next();
					
					//to retreive from db acc to lang id
					String strExamLookup=objExam.getCmnLookupMstByExamLookupId().getLookupName();
					objExam.setCmnLookupMstByExamLookupId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strExamLookup, langId));
					
					//to retreive from db acc to lang id
					String strPreSrLookup=objExam.getCmnLookupMstByPreserviceLookupId().getLookupName();
					objExam.setCmnLookupMstByPreserviceLookupId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strPreSrLookup, langId));
					
					//to retreive from db acc to lang id
					if(objExam.getCmnLookupMstByPassingMonthLookupId() != null && objExam.getCmnLookupMstByPassingMonthLookupId().getLookupName()!= null)
					{	
						String strPassMonthLookup=objExam.getCmnLookupMstByPassingMonthLookupId().getLookupName();
						objExam.setCmnLookupMstByPassingMonthLookupId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strPassMonthLookup, langId));
					}
					objExam.getPassingYear();
					objExam.getMarksObtainted();
					objExam.getMarksOutOf();
					//to retreive from db acc to lang id
					if(objExam.getCmnLookupMstByClassDivLookupId() != null && objExam.getCmnLookupMstByClassDivLookupId().getLookupName() != null )
					{	
						String strClassDivLookup=objExam.getCmnLookupMstByClassDivLookupId().getLookupName();
						objExam.setCmnLookupMstByClassDivLookupId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strClassDivLookup, langId));
					}
					
					if(objExam.getCmnLookupMstByResultLookupId() != null && objExam.getCmnLookupMstByResultLookupId().getLookupName() != null )
					{	
						String strResultLookup=objExam.getCmnLookupMstByResultLookupId().getLookupName();
						objExam.setCmnLookupMstByResultLookupId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strResultLookup, langId));
					}
					String tempExam = FileUtility.voToXmlFileByXStream(objExam);
					logger.info("xml during get====="+tempExam);
					xmlFileNmExam.add(tempExam);
				}
				
				if (userId != 0)
				{	
					objectArgs.put("EmpInfo_userId",userId);
					objectArgs.put("EmpInfo_PayFix",true);
					objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
				}
				
				objectArgs.put("xmlFileNmExam", xmlFileNmExam);
				objectArgs.put("ExamVOList", ExamVOList);			    
				objectArgs.put("arMonthsInfo", arMonthsInfo);
				objectArgs.put("arPreSrvcInfo", arPreSrvcInfo);
				objectArgs.put("arClassDivInfo", arClassDivInfo);
				objectArgs.put("arResultInfo", arResultInfo);
				objectArgs.put("arQualifInfo", arQualifInfo);
				objectArgs.put("lArrYrLst", lArrYrLst);
				objectArgs.put("selectedUserId", userId);
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setViewName("EmpExamintnDetl");
			}
		} 
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling getEmpExamDtls method in EmpServiceExaminationDtlsServiceImpl Service",e);
		}
		return objRes;
	}
	
	public ResultObject saveExamDtlsInDB(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long loginUserId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());	
				long userId = StringUtility.convertToLong(objectArgs.get("userId").toString());
				
				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); 
				OrgUserMst selectedOrgUserMst = orgUserMstDaoImpl.read(userId);	    	 
				/*End of the geting user id Code*/
				
				/*  Get Login user id */
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(loginUserId);	    	 
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
				
				
				List<HrEisSrvcexamDtl> lstHrEisEmpSrvcexamDtlsVO=(List<HrEisSrvcexamDtl>) objectArgs.get("lstHrEisEmpSrvcexamDtlsVO");
				HrEisSrvcexamDtl objSrvcExam = new HrEisSrvcexamDtl();
				
				HrEisSrvcexamDtl obHrEisEmpSrvcexamDtls =  null;
				Date currDate = new Date();
				
				EmpServiceExaminationDtlsDAO objServiceExaminationDtlsDAOImpl= new EmpServiceExaminationDtlsDAOImpl(HrEisSrvcexamDtl.class, serv.getSessionFactory());
				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				List updatedExamVOList = (List) objectArgs.get("updatedExamVOList");
				List deletedExamVOList = (List) objectArgs.get("deletedExamVOList");
				if(deletedExamVOList!=null)
				{
				  ListIterator litr = deletedExamVOList.listIterator();
				  while(litr.hasNext())
				  {	
					obHrEisEmpSrvcexamDtls=(HrEisSrvcexamDtl)litr.next();  
					if(obHrEisEmpSrvcexamDtls.getEmpSrvcexamDtlsId()!=0)
					{
						HrEisSrvcexamDtl readobHrEisEmpSrvcexamDtls=(HrEisSrvcexamDtl)objServiceExaminationDtlsDAOImpl.read(obHrEisEmpSrvcexamDtls.getEmpSrvcexamDtlsId());
						objServiceExaminationDtlsDAOImpl.delete(readobHrEisEmpSrvcexamDtls);
					}
				  } 
				}
				
				if(updatedExamVOList!=null)
				{				
					ListIterator litr = updatedExamVOList.listIterator();
					while(litr.hasNext())
					{		
						obHrEisEmpSrvcexamDtls = new HrEisSrvcexamDtl();
						obHrEisEmpSrvcexamDtls=(HrEisSrvcexamDtl)litr.next();
				        if(obHrEisEmpSrvcexamDtls.getEmpSrvcexamDtlsId()!=0)
						{
							HrEisSrvcexamDtl readobHrEisEmpSrvcexamDtls=(HrEisSrvcexamDtl)objServiceExaminationDtlsDAOImpl.read(obHrEisEmpSrvcexamDtls.getEmpSrvcexamDtlsId());
												
							readobHrEisEmpSrvcexamDtls.setCmnLookupMstByExamLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(obHrEisEmpSrvcexamDtls.getCmnLookupMstByExamLookupId().getLookupName(), langId));
							readobHrEisEmpSrvcexamDtls.setCmnLookupMstByPreserviceLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(obHrEisEmpSrvcexamDtls.getCmnLookupMstByPreserviceLookupId().getLookupName(), langId));
							readobHrEisEmpSrvcexamDtls.setCmnLookupMstByPassingMonthLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(obHrEisEmpSrvcexamDtls.getCmnLookupMstByPassingMonthLookupId().getLookupName(),langId));
							readobHrEisEmpSrvcexamDtls.setCmnLookupMstByPassingMonthLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(obHrEisEmpSrvcexamDtls.getCmnLookupMstByPassingMonthLookupId().getLookupName(),langId));
							readobHrEisEmpSrvcexamDtls.setPassingYear(obHrEisEmpSrvcexamDtls.getPassingYear());
							readobHrEisEmpSrvcexamDtls.setMarksObtainted(obHrEisEmpSrvcexamDtls.getMarksObtainted());
							readobHrEisEmpSrvcexamDtls.setMarksOutOf(obHrEisEmpSrvcexamDtls.getMarksOutOf());
							readobHrEisEmpSrvcexamDtls.setCmnLookupMstByClassDivLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(obHrEisEmpSrvcexamDtls.getCmnLookupMstByClassDivLookupId().getLookupName(), langId));
							readobHrEisEmpSrvcexamDtls.setCmnLookupMstByResultLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(obHrEisEmpSrvcexamDtls.getCmnLookupMstByResultLookupId().getLookupName(), langId));
							readobHrEisEmpSrvcexamDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
							readobHrEisEmpSrvcexamDtls.setUpdatedBy(orgUserMst);
							readobHrEisEmpSrvcexamDtls.setUpdatedDate(currDate);
							objServiceExaminationDtlsDAOImpl.update(readobHrEisEmpSrvcexamDtls);
													
						}
					}	
				}
				
				for(Iterator i = lstHrEisEmpSrvcexamDtlsVO.iterator(); i.hasNext();)
				{
					objSrvcExam = (HrEisSrvcexamDtl)i.next();
					/*objectArgs.put("tablename", "hr_eis_srvcexam_dtl");
					objectArgs.put("serviceLocator", serv);
					ResultObject resultObj = serv.executeService("GENERATE_ID_SRVC", objectArgs);
					int receivedcode = resultObj.getResultCode();
					if (receivedcode == -1)
					{
						return objRes;
					}

					Map resultMap = (Map) resultObj.getResultValue();
					long ServcExamId = (Long) resultMap.get("newID");*/
					long ServcExamId = IDGenerateDelegate.getNextId("hr_eis_srvcexam_dtl", objectArgs);
					
					CmnLookupMst objExamIdLookupMst=objSrvcExam.getCmnLookupMstByExamLookupId();
					objSrvcExam.setCmnLookupMstByExamLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objExamIdLookupMst.getLookupName(), langId));
					
					CmnLookupMst objPrevSrvcLookupMst=objSrvcExam.getCmnLookupMstByPreserviceLookupId();
					objSrvcExam.setCmnLookupMstByPreserviceLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objPrevSrvcLookupMst.getLookupName(), langId));
										
					CmnLookupMst objMonthOfPassLookupMst=objSrvcExam.getCmnLookupMstByPassingMonthLookupId();
					if (objMonthOfPassLookupMst != null && objMonthOfPassLookupMst.getLookupName() != null && !objMonthOfPassLookupMst.getLookupName().equals("0"))
					{	
						objSrvcExam.setCmnLookupMstByPassingMonthLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objMonthOfPassLookupMst.getLookupName(), langId));
					}	
					else
					{	
						objSrvcExam.setCmnLookupMstByPassingMonthLookupId(null);	
					}	
					
					
					CmnLookupMst objClassDivsnLookupMst=objSrvcExam.getCmnLookupMstByClassDivLookupId();
					if(objClassDivsnLookupMst != null && objClassDivsnLookupMst.getLookupName() != null && !objClassDivsnLookupMst.getLookupName().equals("0"))
					{
						objSrvcExam.setCmnLookupMstByClassDivLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objClassDivsnLookupMst.getLookupName(), langId));
					}
					else
					{
						objSrvcExam.setCmnLookupMstByClassDivLookupId(null);
					}
					
					CmnLookupMst objResultLookupMst=objSrvcExam.getCmnLookupMstByResultLookupId();
					if(objResultLookupMst != null && objResultLookupMst.getLookupName() != null && !objResultLookupMst.getLookupName().equals("0"))
					{
						objSrvcExam.setCmnLookupMstByResultLookupId(cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang(objResultLookupMst.getLookupName(), langId));
					}
					else
					{
						objSrvcExam.setCmnLookupMstByResultLookupId(null);
					}
					
					objSrvcExam.setEmpSrvcexamDtlsId(ServcExamId);
					objSrvcExam.setCreatedBy(orgUserMst);
					objSrvcExam.setOrgPostMstByCreatedByPost(orgPostMst);
					objSrvcExam.setCmnDatabaseMst(cmnDatabaseMst);
					objSrvcExam.setOrgUserMst(selectedOrgUserMst);
					objSrvcExam.setCmnLocationMst(cmnLocationMst);
					objSrvcExam.setCreatedDate(currDate);
					objServiceExaminationDtlsDAOImpl.create(objSrvcExam);
				 }
					this.getEmpExamDtls(objectArgs);
					objRes.setResultValue(objectArgs);
					objRes.setResultCode(ErrorConstants.SUCCESS);
					objRes.setViewName("EmpExamintnDetl");
			}
		}	
			
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Calling saveExamDtlsInDB method in EmpServiceExaminationDtlsServiceImpl Service",e);
		}
		return objRes;
	}
	
}	
	
	
	

