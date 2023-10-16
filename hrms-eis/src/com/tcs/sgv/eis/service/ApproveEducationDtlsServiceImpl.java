package com.tcs.sgv.eis.service;

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
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ApproveEducationDAO;
import com.tcs.sgv.eis.dao.ApproveEducationDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisQualDtl;
import com.tcs.sgv.eis.valueobject.HrEisQualDtlTxn;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ApproveEducationDtlsServiceImpl extends ServiceImpl implements ApproveEducationDtlsService
{
	private static final Log logger = LogFactory.getLog(ApproveEducationDtlsServiceImpl.class);
	static long statusLookupId=18;	
	
	public ResultObject getPendingEducationDtls (Map<String, List<Object>> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			ApproveEducationDAO appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			List pendingReqlstObj = appEduDAO.getAllPendingRequest();					
			
			ListIterator li = pendingReqlstObj.listIterator();
			List<Object[]> temp = new ArrayList<Object[]>();
			while(li.hasNext())
			{
				Object[] row = (Object[])li.next();		
				OrgEmpMst og= (OrgEmpMst) row[2];
				row[2] = og.getEmpId();				
				temp.add(row);
			}
			objectArgs.put("QualificationDtls",pendingReqlstObj);						
			objRes.setResultValue(objectArgs);			
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ApproveEduDtls");
		}catch (Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting pending request for Emp Education Service ",e);
		}		
		return objRes;		
	}
	
	public ResultObject getEducationPendingDtls (Map<String, Object> objectArgs)
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{												
			logger.info("getEducationPendingDtls=========Inside===========");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
        	
			EmpEducationServiceImpl empEduServDtls = new EmpEducationServiceImpl();
			objRes=empEduServDtls.getComboDtls(objectArgs);
			
			long CorrsId = 0;
			try
			{
				CorrsId = Long.parseLong(objectArgs.get("corrsIdStr").toString());
			}
			catch(Exception e){logger.error(e);}
			logger.info("CorrsId======In Service====="+CorrsId);
			
			ApproveEducationDAO appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());	
			/**Start===Getting a Request Id from CorrsId */
			long reqId =appEduDAO.getCurrentRequestIdFromCorrsId(CorrsId); 
			logger.info("=========reqId======"+reqId);
			/**Ends===Getting a Request Id from CorrsId */
			
			/*Getting a Emp Id from reqId */
			long userIdLongObj = appEduDAO.getEmpIdFromReqId(reqId);			
			List pendingReqlstObj = new ArrayList();
			List approveDatalstObj = new ArrayList();			
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst userIdObj = orgUserMstDaoImpl.read(userIdLongObj);
			/*End of Getting a Emp Id from reqId*/
			appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());			
			pendingReqlstObj = appEduDAO.getPendingRequestonReqIdandEmpId(reqId);
			
			EmpEducationServiceImpl empEduServcObj = new EmpEducationServiceImpl();			
			objRes=empEduServcObj.getComboDtls(objectArgs);
			ApproveEducationDAO appEmpEduDAO = new ApproveEducationDAOImpl(HrEisQualDtl.class,serv.getSessionFactory());
			approveDatalstObj = appEmpEduDAO.getApproveRequestonEmpId(userIdObj,reqId);
			ListIterator li = approveDatalstObj.listIterator();			
			while(li.hasNext())
			{
				li.next();				
			}
					
			objectArgs.put("ApprovedData", approveDatalstObj);			
			objectArgs.put("PendingDtls",pendingReqlstObj);
			objectArgs.put("reqId",reqId);
			objectArgs.put("empId",userIdLongObj);
			/*Gettina Person Info 	*/			
			li = pendingReqlstObj.listIterator();
			java.util.Date d = null;
			while(li.hasNext())
			{
				HrEisQualDtlTxn ht = (HrEisQualDtlTxn) li.next();
				d = ht.getCreatedDate();				
			}	
			
			/***Show emp info jsp**/
			
			/*HrModEmpRlt hrModEmpRlt=new HrModEmpRlt();
			ApproveEducationDAO daoMod=new ApproveEducationDAOImpl(HrModEmpRlt.class,serv.getSessionFactory());
			ApproveEducationDAO daoEisOther = new ApproveEducationDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			ApproveEducationDAO gpfDaoDor=new ApproveEducationDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			List otherDtl=gpfDaoDor.getEmpDtls(userIdLongObj);
			OrgEmpMst empDet = (OrgEmpMst) otherDtl.get(0);
			long empId=empDet.getEmpId();
			
			OrgEmpMst orgEmpMst=new OrgEmpMst();
			ApproveEducationDAO daoEmpMst=new ApproveEducationDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
			orgEmpMst=(OrgEmpMst)daoEmpMst.read(empId);
			
			HrModMst hrModMst=new HrModMst();
			hrModMst.setModId(300005);
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
			
			EmpDetVO= GemEmpinfo.findByEmpIDOtherDetail(userIdLongObj, langId);//current data (address)
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
			
			objectArgs.put("modId",300005l);
			objectArgs.put("requestId", CorrsId);
			objectArgs.put("forUserId", userIdLongObj);
			objRes = serv.executeService("SHOW_INBOX_EMP_DETAIL", objectArgs);
			/***end of Show emp info jsp**/
			
			objectArgs.put("empName","Hardik");			
			objectArgs.put("department","TCS-Gandhinagar");
			objectArgs.put("designation","Retainer");
			objectArgs.put("date",d);
			/*End of Gettign a Person Info */
			objRes.setResultValue(objectArgs);			
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ShowEducationDtls");					
		}
		catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting pending and approve request for Emp Education Service in Approve Service ",e);
		}		
		return objRes;
	}
	
	public ResultObject forwardEducationDtlsForApproval(Map<String, Object> objectArgs)//Added By Sunil
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{
			logger.info("forwardEducationDtlsForApproval==============Inside==");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");				
			long CorrsId = 0;
			try
			{
				CorrsId = Long.parseLong(objectArgs.get("corrsIdStr").toString());
			}
			catch(Exception e){logger.error(e);}
			logger.info("corrs_Id============"+CorrsId);

			String approveDtls = objectArgs.get("approveDtls").toString();

			ApproveEducationDAO appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			long reqId =appEduDAO.getCurrentRequestIdFromCorrsId(CorrsId); 

			List pendingReqlstObj = appEduDAO.getPendingRequestonReqIdandEmpId(reqId);
			ListIterator lItrHrEisQualDtlTxn = pendingReqlstObj.listIterator();			

			HrEisQualDtlTxn hrEisEmpQuaDtlsTrn =  null;

			StringTokenizer strTknSrNo = new StringTokenizer(approveDtls,",");

			List<Long> listQualSrNo = new ArrayList<Long>();

			while(strTknSrNo.hasMoreTokens())
				listQualSrNo.add(Long.parseLong(strTknSrNo.nextElement().toString().trim()));

			logger.info("============= listQualSrNo================"+ listQualSrNo);

			while (lItrHrEisQualDtlTxn.hasNext())
			{
				hrEisEmpQuaDtlsTrn = (HrEisQualDtlTxn)lItrHrEisQualDtlTxn.next();

				Long lngSrNo = hrEisEmpQuaDtlsTrn.getId().getSrNo();

				if (listQualSrNo.contains(lngSrNo))
				{
					hrEisEmpQuaDtlsTrn.setCheckStatus("Y");
				}
				else
				{
					hrEisEmpQuaDtlsTrn.setCheckStatus("N");
				}

				appEduDAO.update(hrEisEmpQuaDtlsTrn);
			}
			objectArgs.put("approveDtls",approveDtls);
			objRes=getEducationPendingDtls(objectArgs);
			objRes.setResultValue(objectArgs);			
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ShowEducationDtls");	
		}catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while forwarding Corrspondance Request to next ApproveEducationDtlsServiceImpl",e);
		}		
		return objRes;
	}
	
	public ResultObject saveEduApproveDtls(Map<String, Object> objectArgs)	
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

			/**  Get The Person Post */			 			 	    	 
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    	 			 
			/**End of the geting Person Post Code*/

			long locId =  Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			OrgPostMstDaoImpl orgDaoImpl  = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMstVoFrom =  orgDaoImpl.getPrimaryPost(StringUtility.convertToLong(loginMap.get("userId").toString()), langId);

			
			long CorrsId =Long.parseLong(objectArgs.get("corrId").toString());
			logger.info("corrs_Id============"+CorrsId);
			
			/**Start===Getting a Request Id from CorrsId*/
			ApproveEducationDAO appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			long reqId =appEduDAO.getCurrentRequestIdFromCorrsId(CorrsId); 
			
			appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());			
			List pendingReqlstObj = appEduDAO.getPendingRequestonReqIdandEmpId(reqId);
			ListIterator li = pendingReqlstObj.listIterator();	
			
			ApproveEducationDAO updateEmpEduDAO = new ApproveEducationDAOImpl(HrEisQualDtl.class,serv.getSessionFactory());
			logger.info("=========pendingReqlstObj==========="+ pendingReqlstObj.size());
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
			
			while(li.hasNext())
			{
				long member_ID=-1;	
				HrEisQualDtlTxn hrEisEmpQuaDtlsTrn = (HrEisQualDtlTxn)li.next();
				boolean flag=true;
				
				logger.info("=========hrEisEmpQuaDtlsTrn.getCheckStatus()==========="+hrEisEmpQuaDtlsTrn.getCheckStatus());
				
				if (hrEisEmpQuaDtlsTrn.getCheckStatus().equals("Y"))
				{
					flag=false;
					member_ID = hrEisEmpQuaDtlsTrn.getId().getSrNo();
				}
				
				logger.info("=========hrEisEmpQuaDtlsTrn.getRequestFlag()==========="+ hrEisEmpQuaDtlsTrn.getRequestFlag());
				logger.info("=========flag==========="+ flag);
				logger.info("=========member_ID==========="+ member_ID);
				
				HrEisQualDtl hrEisEmpQuaDtls = null;
				
				if(hrEisEmpQuaDtlsTrn.getRequestFlag().equals("A")  && flag==false && member_ID!=-1)
				{
					hrEisEmpQuaDtls = new HrEisQualDtl();						 			  	 			  	 			  			     			    			     			     			    					    	 			  		 			    				  		 						  	 				  							  											 							 					
					/*objectArgs.put("tablename", "hr_eis_qual_dtl");
					objectArgs.put("serviceLocator", serv);		
					ResultObject resultObj = serv.executeService("GENERATE_ID_SRVC", objectArgs);				
					int receivedcode1 = resultObj.getResultCode();
					if (receivedcode1 == -1) {
						return objRes;
					}
					Map resultMap = (Map) resultObj.getResultValue();				
					long srNo= (Long) resultMap.get("newID");*/
					long srNo = IDGenerateDelegate.getNextId("hr_eis_qual_dtl", objectArgs);
					logger.info("Inside Approval Block for Insertion============================"+srNo);

					hrEisEmpQuaDtls.setSrNo(srNo);

					hrEisEmpQuaDtls.setCmnLookupMstByDicipline(hrEisEmpQuaDtlsTrn.getCmnLookupMstByDicipline());
					hrEisEmpQuaDtls.setCmnLookupMstByCourseCategory(hrEisEmpQuaDtlsTrn.getCmnLookupMstByCourseCategory());

					hrEisEmpQuaDtls.setUniInstituteBoard(hrEisEmpQuaDtlsTrn.getUniInstituteBoard());
					hrEisEmpQuaDtls.setCmnLookupMstByUnitsOfMarks(hrEisEmpQuaDtlsTrn.getCmnLookupMstByUnitsOfMarks());
					hrEisEmpQuaDtls.setMarksScored(hrEisEmpQuaDtlsTrn.getMarksScored());						 
					hrEisEmpQuaDtls.setYearOfPassing(hrEisEmpQuaDtlsTrn.getYearOfPassing());						 
					hrEisEmpQuaDtls.setCmnLookupMstByQualificationId(hrEisEmpQuaDtlsTrn.getCmnLookupMstByQualificationId());
					hrEisEmpQuaDtls.setCmnLookupMstBySubQualificationId(hrEisEmpQuaDtlsTrn.getCmnLookupMstBySubQualificationId());

					hrEisEmpQuaDtls.setTrnCounter(1);
					hrEisEmpQuaDtls.setOrgUserMstByUserId(hrEisEmpQuaDtlsTrn.getOrgUserMstByUserId());					  	 
					hrEisEmpQuaDtls.setCmnLocationMst(cmnLocationMst);
					hrEisEmpQuaDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrEisEmpQuaDtls.setOrgUserMstByCreatedBy(orgUserMst); 
					hrEisEmpQuaDtls.setOrgPostMstByCreatedByPost(orgPostMstVoFrom);
					hrEisEmpQuaDtls.setCreatedDate(currDate);
					hrEisEmpQuaDtls.setCmnAttachmentMst(hrEisEmpQuaDtlsTrn.getCmnAttachmentMst());
					hrEisEmpQuaDtls.setDeleteFlag("N");
					appEduDAO.create(hrEisEmpQuaDtls);

					hrEisEmpQuaDtlsTrn.setActionFlag("A");
					//appEduDAO.update(hrEisEmpQuaDtlsTrn);
				}
				else if (hrEisEmpQuaDtlsTrn.getRequestFlag().equals("U") && flag==false  && member_ID!=-1)
				{
					logger.info("Inside Approval Block for Updation============================");
					hrEisEmpQuaDtls = new HrEisQualDtl();
					if(hrEisEmpQuaDtlsTrn.getRowNumber() != null)
					{
						long parentId = hrEisEmpQuaDtlsTrn.getRowNumber();						
												 
						List updateEmpEduDtls = updateEmpEduDAO.getEmpEduDtslForApprove(hrEisEmpQuaDtlsTrn.getOrgUserMstByUserId(),parentId);						 
						ListIterator updateli = updateEmpEduDtls.listIterator();						 
						while(updateli.hasNext())
						{
							hrEisEmpQuaDtls = (HrEisQualDtl)updateli.next();
						}	
						hrEisEmpQuaDtls.setCmnLookupMstByDicipline(hrEisEmpQuaDtlsTrn.getCmnLookupMstByDicipline());
						hrEisEmpQuaDtls.setCmnLookupMstByCourseCategory(hrEisEmpQuaDtlsTrn.getCmnLookupMstByCourseCategory());

						hrEisEmpQuaDtls.setUniInstituteBoard(hrEisEmpQuaDtlsTrn.getUniInstituteBoard());
						hrEisEmpQuaDtls.setCmnLookupMstByUnitsOfMarks(hrEisEmpQuaDtlsTrn.getCmnLookupMstByUnitsOfMarks());
						hrEisEmpQuaDtls.setMarksScored(hrEisEmpQuaDtlsTrn.getMarksScored());						 
						hrEisEmpQuaDtls.setYearOfPassing(hrEisEmpQuaDtlsTrn.getYearOfPassing());						 
						hrEisEmpQuaDtls.setCmnLookupMstByQualificationId(hrEisEmpQuaDtlsTrn.getCmnLookupMstByQualificationId());
						hrEisEmpQuaDtls.setCmnLookupMstBySubQualificationId(hrEisEmpQuaDtlsTrn.getCmnLookupMstBySubQualificationId());							 
						hrEisEmpQuaDtls.setOrgUserMstByCreatedBy(orgUserMst);					  	 
						hrEisEmpQuaDtls.setCmnLocationMst(cmnLocationMst);
						hrEisEmpQuaDtls.setCmnDatabaseMst(cmnDatabaseMst);					     
						hrEisEmpQuaDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						hrEisEmpQuaDtls.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);		
						hrEisEmpQuaDtls.setUpdatedDate(currDate);
						updateEmpEduDAO.update(hrEisEmpQuaDtls);
						hrEisEmpQuaDtls.setCmnAttachmentMst(hrEisEmpQuaDtlsTrn.getCmnAttachmentMst());
						hrEisEmpQuaDtlsTrn.setActionFlag("A");
						hrEisEmpQuaDtlsTrn.setCheckStatus("Y");//change By Sunil
						//appEduDAO.update(hrEisEmpQuaDtlsTrn);
					}
				}
				else if(hrEisEmpQuaDtlsTrn.getRequestFlag().equals("D") && flag==false  && member_ID!=-1)
				{	
					logger.info("Inside Approval Block for Deletion============================");
					hrEisEmpQuaDtls = new HrEisQualDtl();
					long parentId = hrEisEmpQuaDtlsTrn.getRowNumber();
					List deleteEmpEduDtls = appEduDAO.getEmpEduDtslForApprove(hrEisEmpQuaDtlsTrn.getOrgUserMstByUserId(),parentId);						
					ListIterator deleteli = deleteEmpEduDtls.listIterator();
					while(deleteli.hasNext())							
					{							
						hrEisEmpQuaDtls = (HrEisQualDtl)deleteli.next();
					}
					hrEisEmpQuaDtls.setOrgUserMstByUpdatedBy(orgUserMst);
					hrEisEmpQuaDtls.setOrgPostMstByUpdatedByPost(hrEisEmpQuaDtlsTrn.getOrgPostMstByUpdatedByPost());				    
					hrEisEmpQuaDtls.setUpdatedDate(currDate);

					hrEisEmpQuaDtls.setDeleteFlag("Y");
					appEduDAO.update(hrEisEmpQuaDtls);
					hrEisEmpQuaDtlsTrn.setActionFlag("A");
					//appEduDAO.update(hrEisEmpQuaDtlsTrn);
				}
				else
				{	
					hrEisEmpQuaDtlsTrn.setActionFlag("R");
					//appEduDAO.update(hrEisEmpQuaDtlsTrn);
				}
				hrEisEmpQuaDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpQuaDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrEisEmpQuaDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				appEduDAO.update(hrEisEmpQuaDtlsTrn);
			}
		}catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Approving Corrspondance Request In ApproveEducationDtlsServiceImpl",e);
		}
		return objRes;
	}
	
	public ResultObject rejectEducationDtlsReqest(Map<String, Object> objectArgs)	
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
			logger.info("corrs_Id In rejectEducationDtlsReqest============"+CorrsId);
			
			/**Start===Getting a Request Id from CorrsId*/
			ApproveEducationDAO appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			long reqId =appEduDAO.getCurrentRequestIdFromCorrsId(CorrsId); 
			appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());			
			List pendingReqlstObj = appEduDAO.getPendingRequestonReqIdandEmpId(reqId);
			logger.info("rejectFamilyDtlsReqest Size========"+pendingReqlstObj.size());
			ListIterator pendingListItr = pendingReqlstObj.listIterator();	
			HrEisQualDtlTxn hrEisEmpQuaDtlsTrn=null;
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());
			while(pendingListItr.hasNext())
			{
				hrEisEmpQuaDtlsTrn = (HrEisQualDtlTxn)pendingListItr.next();
				hrEisEmpQuaDtlsTrn.setActionFlag("R");
				hrEisEmpQuaDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpQuaDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
				hrEisEmpQuaDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				appEduDAO.update(hrEisEmpQuaDtlsTrn);
			}
		}catch (Exception e)
		{						
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Resecting Corrspondance Request In ApproveEducationDtlsServiceImpl",e);
		}
		return objRes;
	}
	
	public ResultObject cancleEducationRequestSRVC(Map<String, Object> objectArgs) 
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);	
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
			logger.info("corrs_Id In cancellation of EducationDtlsReqest============"+CorrsId);
			
			/**Start===Getting a Request Id from CorrsId*/
			ApproveEducationDAO appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			long reqId =appEduDAO.getCurrentRequestIdFromCorrsId(CorrsId); 
			appEduDAO = new ApproveEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());			
			List pendingReqlstObj = appEduDAO.getPendingRequestonReqIdandEmpId(reqId);
			logger.info("Cancle EductionDtlsReqest Size========"+pendingReqlstObj.size());
			ListIterator pendingListItr = pendingReqlstObj.listIterator();	
			HrEisQualDtlTxn hrEisEmpQuaDtlsTrn=null;
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());	
			while(pendingListItr.hasNext())
			{
				hrEisEmpQuaDtlsTrn = (HrEisQualDtlTxn)pendingListItr.next();
				hrEisEmpQuaDtlsTrn.setActionFlag("C");
				hrEisEmpQuaDtlsTrn.setUpdatedDate(currDate);
				hrEisEmpQuaDtlsTrn.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
				hrEisEmpQuaDtlsTrn.setOrgUserMstByUpdatedBy(orgUserMst);
				appEduDAO.update(hrEisEmpQuaDtlsTrn);
			}
		} catch (Exception e) 
		{
			logger.error("Error in cancleEducationRequestSRVC method  "+e);
		}
		return resultObject;
	}
}
