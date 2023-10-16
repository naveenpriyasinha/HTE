package com.tcs.sgv.eis.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ApproveEducationDAOImpl;
import com.tcs.sgv.eis.dao.EmpEducationDAO;
import com.tcs.sgv.eis.dao.EmpEducationDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisQualDtl;
import com.tcs.sgv.eis.valueobject.HrEisQualDtlTxn;
import com.tcs.sgv.eis.valueobject.HrEisQualDtlTxnId;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.wf.interfaces.WFConstants;

public class EmpEducationServiceImpl extends ServiceImpl implements EmpEducationService
{
	private static final Log logger = LogFactory.getLog(EmpEducationServiceImpl.class);
	public final static long EMP_EDUCATION_MODID=300005;
	public final static String DOC_ID="300011";
	public final static String CORR_DESC="Qualification Details Correspondence";
	
	public ResultObject getEducationalDetails(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{					
			boolean blnWorkFlowEnabled = true;
			if (objectArgs.get("blnWorkFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
			}

			objectArgs.put("blnWorkFlowEnabled",blnWorkFlowEnabled); //IFMS
			long iUserId = (objectArgs.get("userId") != null && !objectArgs.get("userId").toString().equals("")) ? Long.parseLong(objectArgs.get("userId").toString()) : 0;
			objectArgs.put("userId",iUserId); 

			String lStrFlag=objectArgs.get("flag").toString();
			logger.info("Employee Education Service Flag :: "+ lStrFlag);

			if("getComboDetails".equalsIgnoreCase(lStrFlag))
			{				
				objRes=getComboDtls(objectArgs);// Calling Function for Filling Combos In JSP

				if (blnWorkFlowEnabled) 	
				{
					objRes=getEmployeeEducationDtls(objectArgs);
				}
				else
				{
					objRes = getEmployeeQualificationVOList(objectArgs, iUserId);
				}
			}
			else if("getSubQualification".equalsIgnoreCase(lStrFlag))
			{
				String cmbid=objectArgs.get("cmbid").toString();				
				objRes=getComboDetail(objectArgs,cmbid);
			}
			else if("getDiscipline".equalsIgnoreCase(lStrFlag))
			{
				String cmbid=objectArgs.get("cmbid").toString();				
				objRes=getComboDetail(objectArgs,cmbid);
			}
			else if("AddOrEditEducationDtls".equalsIgnoreCase(lStrFlag))
			{				
				objRes=getComboDtls(objectArgs);	
				objRes=addorEditEmpEducationDtls(objectArgs, blnWorkFlowEnabled);	
			}			
			else if("SubmitEducationDtls".equalsIgnoreCase(lStrFlag))
			{
				if (blnWorkFlowEnabled)
				{
					objRes=getComboDtls(objectArgs);					
					objRes=submitEmpEducationDtls(objectArgs);
				}
				else
				{
					logger.info("For Work Flow Disable In Service================="+ iUserId);
					objRes = saveEmpEducationDtls(objectArgs,blnWorkFlowEnabled, iUserId);

				}
			}
			else if("EducationSaveAsDraft".equals(lStrFlag))  // when user click save as draft button
			{				
				objRes=educationSaveAsDraft(objectArgs);
			}
			else if("deleteEmpEducationDtls".equals(lStrFlag))
			{
				String deleteStrId=objectArgs.get("deleteId").toString();
				Long deleteId = Long.parseLong(deleteStrId.trim()); 
				objRes=deleteEmpEducationDtlsFromMaster(objectArgs,deleteId);			
			}	
			else if("getDraftRequest".equals(lStrFlag))   // when user click Draft Reuest
			{		
				objRes=getComboDtls(objectArgs);	
				objRes=getDraftData(objectArgs);
			}
			else if("getDraftData".equals(lStrFlag))    // when ueser click on view link at that time to show that draft reecord(s)
			{		
				objRes=getComboDtls(objectArgs);	
				long reqId= Long.parseLong(objectArgs.get("reqId").toString().trim());	
				objRes=getDraftDataOnRequestId(objectArgs,reqId);
			}
			else if("getPendingRecord".equals(lStrFlag))  // when user click on the view link on the Education page to see pending request
			{	
				objRes=getComboDtls(objectArgs);
				long reqId= Long.parseLong(objectArgs.get("reqId").toString().trim());
				objRes=getEmpEduPendinRequest(objectArgs,reqId);
			}
			else if("AddDraftDtlsinTable".equals(lStrFlag))  // When user click on select a draft record and want to see in the education page at that time
			{
				String reqId =objectArgs.get("reqId").toString();				
				objRes=addDraftDtlsinTable(objectArgs,reqId);
			}
			else if("deleteDraftData".equals(lStrFlag))
			{				
				String reqId = objectArgs.get("reqId").toString();
				objRes=deleteDraftRequest(objectArgs,reqId);				
			}
		}catch (Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while setting a flag for Emp Education Service",e);
		}		
		return objRes;
	}	
	
	public ResultObject getEmployeeQualificationVOList(Map<String, Object> objectArgs, long iUserId)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{																		    					    	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	

			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());	

			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(iUserId);		 	    	 				 		    					 			  	 							 

			EmpEducationDAO educationDAO = new EmpEducationDAOImpl(HrEisQualDtl.class, serv.getSessionFactory());

			List<HrEisQualDtl> hrEisEmpQualificationVOList = educationDAO.getEmpEducationDtls(orgUserMst);

			ArrayList xmlFileName = new ArrayList();

			/**	For Attachment*/
			CmnAttachmentMstDAO cmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
			request.getSession().removeAttribute("attachmentBiometricEducation_EDITVO");	
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

			int iCounter = 0;
			for (HrEisQualDtl hrEisEmpQualification : hrEisEmpQualificationVOList) 
			{
				if(hrEisEmpQualification!=null)
				{
					iCounter++;

					hrEisEmpQualification.getSrNo();

					String strLookupName = hrEisEmpQualification.getCmnLookupMstByQualificationId() != null ? hrEisEmpQualification.getCmnLookupMstByQualificationId().getLookupName() : "";
					if(!"".equals(strLookupName))
					{
						hrEisEmpQualification.setCmnLookupMstByQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLookupName, langId));
					}

					String strSubQualificationIdLookupName = hrEisEmpQualification.getCmnLookupMstBySubQualificationId() != null ? hrEisEmpQualification.getCmnLookupMstBySubQualificationId().getLookupName() : "";
					if(!"".equals(strSubQualificationIdLookupName))
					{
						hrEisEmpQualification.setCmnLookupMstBySubQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strSubQualificationIdLookupName, langId));
					}

					String strDiciplineLookupName = hrEisEmpQualification.getCmnLookupMstByDicipline() != null ? hrEisEmpQualification.getCmnLookupMstByDicipline().getLookupName() : "";
					if(!"".equals(strDiciplineLookupName))
					{
						hrEisEmpQualification.setCmnLookupMstByDicipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strDiciplineLookupName, langId));
					}

					String strCourseCategoryLookupName = hrEisEmpQualification.getCmnLookupMstByCourseCategory() != null ? hrEisEmpQualification.getCmnLookupMstByCourseCategory().getLookupName() : "";
					if(!"".equals(strCourseCategoryLookupName))
					{
						hrEisEmpQualification.setCmnLookupMstByCourseCategory(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strCourseCategoryLookupName, langId));
					}

					String strUnitsOfMarksLookupName = hrEisEmpQualification.getCmnLookupMstByUnitsOfMarks() != null ? hrEisEmpQualification.getCmnLookupMstByUnitsOfMarks().getLookupName() : "";
					if(!"".equals(strUnitsOfMarksLookupName))	
					{
						hrEisEmpQualification.setCmnLookupMstByUnitsOfMarks(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strUnitsOfMarksLookupName, langId));
					}

					String strMarksScored = hrEisEmpQualification.getMarksScored()!= null? hrEisEmpQualification.getMarksScored() : "";				
					hrEisEmpQualification.setMarksScored(strMarksScored);

					String strUniInstituteBoard = hrEisEmpQualification.getUniInstituteBoard() != null ? hrEisEmpQualification.getUniInstituteBoard() : "";				
					hrEisEmpQualification.setUniInstituteBoard(strUniInstituteBoard);

					hrEisEmpQualification.getYearOfPassing();

					//For Attachment
					if(hrEisEmpQualification.getCmnAttachmentMst()!=null)
					{
						CmnAttachmentMst cmnAttachmentMst = cmnAttachmentMstDAO.findByAttachmentId(hrEisEmpQualification.getCmnAttachmentMst().getAttachmentId());
						EisWFmessageDisplay.removeAttachmentFromSession(request.getSession(),"attachmentBiometricEducation",iCounter);
						request.getSession().setAttribute("attachmentBiometricEducation"+iCounter+"_EDITVO", cmnAttachmentMst);
						hrEisEmpQualification.setCmnAttachmentMst(cmnAttachmentMst);
					}
					String temp = FileUtility.voToXmlFileByXStream(hrEisEmpQualification);
					xmlFileName.add(temp);
				}
			}
			objectArgs.put("xmlFilePathNameForMulAddRec", xmlFileName);
			objectArgs.put("hrEisEmpQualificationVOList", hrEisEmpQualificationVOList);

			logger.info("xmlFilePathNameForMulAddRec In getEmployeeQualificationVOList Method of EmpEducationServiceImpl============="+ xmlFileName);
			logger.info("hrEisEmpQualificationVOList In getEmployeeQualificationVOList Method of EmpEducationServiceImpl============="+ hrEisEmpQualificationVOList.size());

			/**   For Employee Data That is Shown At Top Of Header   **/
			if (iUserId != 0)
			{	
				objectArgs.put("EmpInfo_userId",iUserId);
				objectArgs.put("EmpInfo_PayFix",true);
				objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
			}
			/**End OF Employee Data **/

			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);	
			objRes.setViewName("WorkFlowDisableEducationDtls");

		}catch (Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting a Qualification Dtls For Jsp View in Service VO",e);
		}
		return objRes;
	}
	
	@SuppressWarnings("unchecked")
	public ResultObject saveEmpEducationDtls(Map<String, Object> objectArgs,boolean blnWorkFlowEnabled, long iUserId)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	

		try{												
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
			Map loginMap = (Map) objectArgs.get("baseLoginMap");

			/** Get The User ID*/
			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);	
			/** End Of The User ID*/

			/**  Get The selected user Person's User Id*/
			OrgUserMst selectedOrgUserMst = null;
			if (!blnWorkFlowEnabled)
			{
				selectedOrgUserMst = orgUserMstDaoImpl.read(iUserId);
			}

			/**  Get The Person Post */			 			 	    	 
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    	 			 
			/**End of the geting Person Post Code*/

			/** Getting a DB ID*/								 
			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			/** End og DB Id code*/

			/** Getting a Lang Id Code */
			long langId = Long.parseLong(loginMap.get("langId").toString());				 
			/** End of Lang ID Code*/

			/** Getting a Loc ID Code*/
			long locId =  Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			/** End of Loc ID */

			EmpEducationDAOImpl QuaDaoImplObj = new EmpEducationDAOImpl(HrEisQualDtl.class,serv.getSessionFactory());
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

			List upDatedVOList= (List) objectArgs.get("upDatedVOList");
			List deletedVOList= (List) objectArgs.get("deletedVOList");		

			String[] enc_rowNumForAttachment =(String[])objectArgs.get("enc_rowNumForAttachment");						/**for Insert*/
			String[] addedPunch_rowNumForAttachment = (String[]) objectArgs.get("addedPunch_rowNumForAttachment");	/**for update*/

			logger.info("addedPunch_rowNumber===================="+addedPunch_rowNumForAttachment.length);//Change
			logger.info("upDatedVOList.size() For Updation ===================="+upDatedVOList.size());
			Date currDate = new Date();
			Date date = new java.sql.Timestamp((new java.util.Date()).getTime());
			
			if(upDatedVOList!=null)
			{
				ListIterator liForUpdateVO = upDatedVOList.listIterator();
				int j=0;
				HrEisQualDtl eisEmpEducationDtls = new HrEisQualDtl();
				CmnAttachmentMst cmnAttachmentMst = null;
				while(liForUpdateVO.hasNext())
				{				 
					eisEmpEducationDtls =(HrEisQualDtl)liForUpdateVO.next();
					logger.info("Updateing Existing Record in DB........................" + eisEmpEducationDtls.getSrNo());
					if( eisEmpEducationDtls.getSrNo()!=0)
					{
						HrEisQualDtl updateHrEisEmpEducationDtls=(HrEisQualDtl)QuaDaoImplObj.read(eisEmpEducationDtls.getSrNo());

						String rowNumber  =  addedPunch_rowNumForAttachment[j];//change

						updateHrEisEmpEducationDtls.setCmnLookupMstByCourseCategory(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpEducationDtls.getCmnLookupMstByCourseCategory().getLookupName(),langId));
						updateHrEisEmpEducationDtls.setCmnLookupMstByDicipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpEducationDtls.getCmnLookupMstByDicipline().getLookupName(),langId));
						updateHrEisEmpEducationDtls.setCmnLookupMstByQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpEducationDtls.getCmnLookupMstByQualificationId().getLookupName(),langId));
						updateHrEisEmpEducationDtls.setCmnLookupMstBySubQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpEducationDtls.getCmnLookupMstBySubQualificationId().getLookupName(),langId));
						updateHrEisEmpEducationDtls.setCmnLookupMstByUnitsOfMarks(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(eisEmpEducationDtls.getCmnLookupMstByUnitsOfMarks().getLookupName(),langId));
						updateHrEisEmpEducationDtls.setDeleteFlag("N");

						/**Start----For Attachment*/			 
						objectArgs.put("rowNumber", rowNumber);
						objectArgs.put("attachmentName", "attachmentBiometricEducation"); 
						ResultObject resultObj1 = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
						objectArgs = (Map) resultObj1.getResultValue();			
						Long lAttachId= null;
						ResultObject resultObjAttch = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
						objectArgs = (Map) resultObjAttch.getResultValue();
						lAttachId = (Long) objectArgs.get("AttachmentId_attachmentBiometricEducation");
						if(lAttachId!=null)
						{
							cmnAttachmentMst = new CmnAttachmentMst();
							cmnAttachmentMst.setAttachmentId(lAttachId);
							updateHrEisEmpEducationDtls.setCmnAttachmentMst(cmnAttachmentMst);
						}	
						else
						{
							updateHrEisEmpEducationDtls.setCmnAttachmentMst(updateHrEisEmpEducationDtls.getCmnAttachmentMst());
						}	
						/**End---- of Attachment*/				 	

						updateHrEisEmpEducationDtls.setMarksScored(eisEmpEducationDtls.getMarksScored());
						updateHrEisEmpEducationDtls.setUniInstituteBoard(eisEmpEducationDtls.getUniInstituteBoard());
						updateHrEisEmpEducationDtls.setYearOfPassing(eisEmpEducationDtls.getYearOfPassing());

						updateHrEisEmpEducationDtls.setCmnLocationMst(cmnLocationMst);
						updateHrEisEmpEducationDtls.setCmnDatabaseMst(cmnDatabaseMst);
						updateHrEisEmpEducationDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						updateHrEisEmpEducationDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						updateHrEisEmpEducationDtls.setUpdatedDate(currDate);

						QuaDaoImplObj.update(updateHrEisEmpEducationDtls);
						j++;
					}
				}
			}

			/**Start------For Deletion Of Records*/ 
			if(deletedVOList!=null)
			{
				ListIterator liForDeletVO = deletedVOList.listIterator();
				HrEisQualDtl eisEmpEducationDtls =new HrEisQualDtl();
				while(liForDeletVO.hasNext())
				{
					eisEmpEducationDtls =(HrEisQualDtl)liForDeletVO.next();
					logger.info("Deleting  Existing Record in DB........................" + eisEmpEducationDtls.getSrNo());
					HrEisQualDtl updateHrEisEmpEducationDtls=(HrEisQualDtl)QuaDaoImplObj.read(eisEmpEducationDtls.getSrNo());
					updateHrEisEmpEducationDtls.setDeleteFlag("Y");

					updateHrEisEmpEducationDtls.setCmnLocationMst(cmnLocationMst);
					updateHrEisEmpEducationDtls.setCmnDatabaseMst(cmnDatabaseMst);
					updateHrEisEmpEducationDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
					updateHrEisEmpEducationDtls.setOrgUserMstByUpdatedBy(orgUserMst);

					//QuaDaoImplObj.delete(updateHrEisEmpEducationDtls);
					QuaDaoImplObj.update(updateHrEisEmpEducationDtls);
				}
			}
			/**Ends-----For Deletion Of Records*/ 

			/**Start------for Inserting New Record*/

			String[] txnXML = (String[]) objectArgs.get("encXML");		
			List<HrEisQualDtl> lstlockupTxnVO = FileUtility.xmlFilesToVoByXStream(txnXML);

			HrEisQualDtl lVObject =  null;
			int j1=0;

			logger.info("=========== enc_rowNumForAttachment======"+enc_rowNumForAttachment.length);

			for(Iterator i = lstlockupTxnVO.iterator(); i.hasNext();) 
			{			    	 
				Object Obj = i.next();
				if(Obj != null)
				{
					Class<? extends Object> c=Obj.getClass();				    	 
					if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisQualDtl"))
					{
						lVObject = (HrEisQualDtl)Obj;	

						/**Generateing a Req_Id  for HR_EIS_EMP_FAMILY_DTLS*/	
						long reqId = 0;
						reqId = IDGenerateDelegate.getNextId("hr_eis_qual_dtl", objectArgs);
						logger.info("Inserting known ReqID PK..... " + reqId);
						/**end of req Id code***/	

						lVObject.setCmnLookupMstByQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByQualificationId().getLookupName(), langId));			    		 
						lVObject.setCmnLookupMstBySubQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstBySubQualificationId().getLookupName(), langId));	    		 				    		 	    		
						lVObject.setCmnLookupMstByUnitsOfMarks(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByUnitsOfMarks().getLookupName(), langId));
						lVObject.setCmnLookupMstByDicipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByDicipline().getLookupName(), langId));
						lVObject.setCmnLookupMstByCourseCategory(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByCourseCategory().getLookupName(), langId));

						lVObject.setDeleteFlag("N");

						String rowNumber  =  enc_rowNumForAttachment[j1];
						objectArgs.put("rowNumber", rowNumber);
						objectArgs.put("attachmentName", "attachmentBiometricEducation"); 
						ResultObject resultObj1 = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
						objectArgs = (Map) resultObj1.getResultValue();			
						Long lAttachId= null;
						ResultObject resultObjAttch = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
						objectArgs = (Map) resultObjAttch.getResultValue();
						lAttachId = (Long) objectArgs.get("AttachmentId_attachmentBiometricEducation");
						logger.info("Attachment Id In insert Mode ===:"+lAttachId);
						CmnAttachmentMst cmnAttachmentMst = null;
						
						if(lAttachId!=null)
						{
							cmnAttachmentMst = new CmnAttachmentMst();
							cmnAttachmentMst.setAttachmentId(lAttachId);
							lVObject.setCmnAttachmentMst(cmnAttachmentMst);
						}	
						else
						{
							lVObject.setCmnAttachmentMst(null);
						}			    		

						lVObject.setSrNo(reqId);

						if (blnWorkFlowEnabled) 
						{
							lVObject.setOrgUserMstByUserId(orgUserMst);
						}
						else
						{
							lVObject.setOrgUserMstByUserId(selectedOrgUserMst);
						}

						lVObject.setCmnLocationMst(cmnLocationMst);
						lVObject.setCmnDatabaseMst(cmnDatabaseMst);
						lVObject.setOrgUserMstByCreatedBy(orgUserMst); 
						lVObject.setOrgPostMstByCreatedByPost(orgPostMst);
						lVObject.setCreatedDate(date);
						lVObject.setTrnCounter(Integer.valueOf(1));
						logger.info("Emp Qualification Pk Id For MST Table:::::: "+ lVObject.getSrNo());
						QuaDaoImplObj.create(lVObject);						    	 
					}
					j1++;
				}
			} 
			objRes = getEmployeeQualificationVOList(objectArgs, iUserId); // IFMS
			objRes=getComboDtls(objectArgs);

			objRes.setResultValue(objectArgs); 
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("WorkFlowDisableEducationDtls");
		}catch (Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Submitting a Qualification Dtls",e);
		}
		return objRes;
	}

	@SuppressWarnings("unchecked")
	public ResultObject deleteEmpEducationDtlsFromMaster(Map<Object, Object> objectArgs, long delete_ID)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{							
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");					 
			Map loginMap = (Map) objectArgs.get("baseLoginMap");				

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);		 	    	 				 		    					 			  	 							 

			EmpEducationDAOImpl QuaDaoImplObj = new EmpEducationDAOImpl(HrEisQualDtl.class,serv.getSessionFactory());
			List delteEmpEduDtls=QuaDaoImplObj.deleteReqId(orgUserMst,delete_ID);
			Iterator it = delteEmpEduDtls.iterator();
			while(it.hasNext())
			{					
				HrEisQualDtl empQuaDtls = (HrEisQualDtl)it.next();
				empQuaDtls.setDeleteFlag("Y");
				QuaDaoImplObj.update(empQuaDtls);			  		 			  		 			  		 
			}					 			     
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax","a").toString();				 
			objectArgs.put("ajaxKey", tempStr);				 
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("ajaxData");				 					
		}	
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while deleting a Emp Education Master in Service",e);
		}
		return objRes;
	}

	public ResultObject addDraftDtlsinTable(Map<Object, Object> objectArgs, String reqId) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{									
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");					 
			Map loginMap = (Map) objectArgs.get("baseLoginMap");				

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);		 	    	 

			StringTokenizer st= new StringTokenizer(reqId,",");
			long request_ID = 1;				 
			List<Object> addDraftDtlsLstObj = new ArrayList<Object>();
			StringBuffer sbXML = new StringBuffer();
			EmpEducationDAOImpl QuaDaoImplObj = new EmpEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			EmpEducationDAOImpl empEduDAOObj = new EmpEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory()); 

			while(st.hasMoreTokens())
			{
				request_ID=Long.parseLong(st.nextElement().toString().trim());
				List tempDraftData=QuaDaoImplObj.addOrdeleteDrftDtlsLstReqId(orgUserMst,request_ID);

				List requestLstObj = empEduDAOObj.findParentIdForthisReequest(request_ID,orgUserMst);
				ListIterator removeLstItr = requestLstObj.listIterator();
				while(removeLstItr.hasNext())
				{
					HrEisQualDtlTxn row = (HrEisQualDtlTxn)removeLstItr.next(); 
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
			}
			long langId = Long.parseLong(loginMap.get("langId").toString());
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			if(!addDraftDtlsLstObj.isEmpty())
			{												 						 				    	 	
				ListIterator itr= addDraftDtlsLstObj.listIterator();			 			
				HrEisQualDtlTxn lVObject=null;
				HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
				CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
				request.getSession().removeAttribute("attachmentBiometricEducation_EDITVO");	
				int iCounter=0;
				CmnAttachmentMstDAO attachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
				while(itr.hasNext()) 
				{
					iCounter++;

					String lStrValidatestr="";
					lVObject = (HrEisQualDtlTxn)itr.next();					    	

					String strLookupName = lVObject.getCmnLookupMstByQualificationId() != null ? lVObject.getCmnLookupMstByQualificationId().getLookupName() : "";

					if (!"".equals(strLookupName))
					{
						lVObject.setCmnLookupMstByQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLookupName, langId));
					}

					String strSubQualificationIdLookupName = lVObject.getCmnLookupMstBySubQualificationId() != null ? lVObject.getCmnLookupMstBySubQualificationId().getLookupName() : "";				

					if (!"".equals(strSubQualificationIdLookupName))
					{
						lVObject.setCmnLookupMstBySubQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strSubQualificationIdLookupName, langId));
					}

					String strDiciplineLookupName = lVObject.getCmnLookupMstByDicipline() != null ? lVObject.getCmnLookupMstByDicipline().getLookupName() : "";

					if (!"".equals(strDiciplineLookupName))
					{
						lVObject.setCmnLookupMstByDicipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strDiciplineLookupName, langId));
					}

					String strCourseCategoryLookupName = lVObject.getCmnLookupMstByCourseCategory() != null ? lVObject.getCmnLookupMstByCourseCategory().getLookupName() : "";				

					if (!"".equals(strCourseCategoryLookupName))
					{
						lVObject.setCmnLookupMstByCourseCategory(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strCourseCategoryLookupName, langId));
					}

					String strUnitsOfMarksLookupName = lVObject.getCmnLookupMstByUnitsOfMarks() != null ? lVObject.getCmnLookupMstByUnitsOfMarks().getLookupName() : "";				

					if (!"".equals(strUnitsOfMarksLookupName))
					{
						lVObject.setCmnLookupMstByUnitsOfMarks(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strUnitsOfMarksLookupName, langId));
					}

					String strMarksScored = lVObject.getMarksScored()!= null? lVObject.getMarksScored() : "";				

					if (!"".equals(strMarksScored))
					{
						lVObject.setMarksScored(strMarksScored);
					}

					if(lVObject.getCmnAttachmentMst()!=null)
					{						    		
						lVObject.setCmnAttachmentMst(attachmentMstDAO.read(lVObject.getCmnAttachmentMst().getAttachmentId()));
						CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(lVObject.getCmnAttachmentMst().getAttachmentId());
						request.getSession().setAttribute("attachmentBiometricEducation"+iCounter+"_EDITVO", cmnAttachmentMst);
					}
					String cmnQuaId ="";
					if(lVObject.getCmnLookupMstByQualificationId()!=null)
					{
						cmnQuaId = lVObject.getCmnLookupMstByQualificationId().getLookupName();
					}

					if(lVObject.getCmnLookupMstByQualificationId()!=null)
					{
						if(lVObject.getCmnLookupMstByQualificationId().getLookupName().equalsIgnoreCase("Select") || lVObject.getCmnLookupMstByQualificationId().getLookupName().equals(""))
						{
							lStrValidatestr=lStrValidatestr+"eis.qualification,";
						}
					}
					if(lVObject.getCmnLookupMstBySubQualificationId()!=null)
					{
						if(lVObject.getCmnLookupMstBySubQualificationId().getLookupName().equalsIgnoreCase("Select") || lVObject.getCmnLookupMstBySubQualificationId().getLookupName().equals(""))
						{
							lStrValidatestr=lStrValidatestr+"eis.subqualification,";
						}
					}
					if(lVObject.getUniInstituteBoard() == null || lVObject.getUniInstituteBoard().trim().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.uni_ins_board,"; }

					if(!"".equals(cmnQuaId))
					{
						if(cmnQuaId.trim().equals("edu_Higher_Secondary_School")==false  && cmnQuaId.trim().equals("edu_Secondary_School")==false && cmnQuaId.trim().equals("edu_Primary_School")==false)
						{
							if(lVObject.getCmnLookupMstByCourseCategory()==null || lVObject.getCmnLookupMstByCourseCategory().getLookupDesc().equalsIgnoreCase("Select")==true)
							{
								lStrValidatestr=lStrValidatestr+"eis.courseCategory,";
							}
						}					    	
					}
					if(lVObject.getYearOfPassing()==0){lStrValidatestr=lStrValidatestr+"eis.year_of_pass,"; }

					if(lVObject.getCmnLookupMstByUnitsOfMarks()!=null)
					{
						if(lVObject.getCmnLookupMstByUnitsOfMarks().getLookupName().equalsIgnoreCase("Select") || lVObject.getCmnLookupMstByUnitsOfMarks().getLookupName().equals(""))
						{
							lStrValidatestr=lStrValidatestr+"eis.unit,";
						}
					}
					if(lVObject.getMarksScored()==null || lVObject.getMarksScored().trim().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.marks,"; }

					String strXMLObj = FileUtility.voToXmlFileByXStream(lVObject);					  		 					  		 
					sbXML.append("<DraftQua>");
					if(lVObject.getCmnLookupMstByQualificationId()!=null  && lVObject.getCmnLookupMstByQualificationId().getLookupDesc().equalsIgnoreCase("Select")==false)
					{								
						sbXML.append(lVObject.getCmnLookupMstByQualificationId().getLookupDesc());								
					}
					else
					{
						sbXML.append("null");
					}
					sbXML.append("</DraftQua>");

					sbXML.append("<DraftSubQua>");
					if(lVObject.getCmnLookupMstBySubQualificationId()!=null && lVObject.getCmnLookupMstBySubQualificationId().getLookupDesc().equalsIgnoreCase("Select")==false)
					{							
						sbXML.append(lVObject.getCmnLookupMstBySubQualificationId().getLookupDesc());
					}
					else {sbXML.append("null");}							
					sbXML.append("</DraftSubQua>");

					sbXML.append("<DraftUnitInst>");
					if(lVObject.getUniInstituteBoard()!=null && lVObject.getUniInstituteBoard().equals("")==false)
					{							
						sbXML.append(lVObject.getUniInstituteBoard());
					}
					else {sbXML.append("null");}
					sbXML.append("</DraftUnitInst>");

					sbXML.append("<DraftUnit>");
					if(lVObject.getCmnLookupMstByUnitsOfMarks()!=null && lVObject.getCmnLookupMstByUnitsOfMarks().getLookupDesc().equalsIgnoreCase("Select")==false)
					{												
						sbXML.append(lVObject.getCmnLookupMstByUnitsOfMarks().getLookupDesc());
					}
					else{sbXML.append("null");}
					sbXML.append("</DraftUnit>");

					sbXML.append("<DraftYear>");
					if(lVObject.getYearOfPassing()!=0)
					{							
						sbXML.append(lVObject.getYearOfPassing());
					}
					else
					{
						sbXML.append("null");
					}
					sbXML.append("</DraftYear>");

					sbXML.append("<DraftMark>");
					if(lVObject.getMarksScored()!=null && lVObject.getMarksScored().equals("")==false)
					{sbXML.append(lVObject.getMarksScored());}							
					else
					{sbXML.append("null");}								
					sbXML.append("</DraftMark>");

					sbXML.append("<RequestFlag>");
					if(lVObject.getRequestFlag()!=null && lVObject.getRequestFlag().equals("")==false)
					{sbXML.append(lVObject.getRequestFlag());}							
					else
					{sbXML.append("null");}							
					sbXML.append("</RequestFlag>");

					sbXML.append("<DraftDici>");
					if(lVObject.getCmnLookupMstByDicipline()!=null  && lVObject.getCmnLookupMstByDicipline().getLookupDesc().equalsIgnoreCase("Select")==false)
					{
						sbXML.append(lVObject.getCmnLookupMstByDicipline().getLookupDesc());
					}
					else
					{
						sbXML.append("null");
					}
					sbXML.append("</DraftDici>");

					sbXML.append("<DraftCate>");
					if(lVObject.getCmnLookupMstByCourseCategory()!=null  && lVObject.getCmnLookupMstByCourseCategory().getLookupDesc().equalsIgnoreCase("Select")==false)
					{
						sbXML.append(lVObject.getCmnLookupMstByCourseCategory().getLookupDesc());
					}
					else
					{
						sbXML.append("null");
					}
					sbXML.append("</DraftCate>");

					sbXML.append("<Attchment>");
					if(lVObject.getCmnAttachmentMst()!=null)
					{
						sbXML.append(lVObject.getCmnAttachmentMst().getAttachmentId());
					}
					else
					{
						sbXML.append("null");
					}
					sbXML.append("</Attchment>");

					if(lStrValidatestr.equalsIgnoreCase("")==false)
					{strXMLObj=strXMLObj+"$/$"+lStrValidatestr;}

					sbXML.append("<XMLFile>");
					sbXML.append(strXMLObj);
					sbXML.append("</XMLFile>");		

					logger.info("lStrValidatestr in addDraftDtlsinTable Method======="+lStrValidatestr);
				}					    				 
				/***********  end of getting a pending dtls code*********/						
			}				 			    
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();
			logger.info("tempXML for Draft Request id is:: " + tempStr);			     
			objectArgs.put("ajaxKey", tempStr);
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);							 	  		
			objRes.setViewName("ajaxData");				 					
		}	
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting a Draft Dtls For All Request  for Education in Service",e);
		}
		return objRes;
	}
	
	public ResultObject getDraftDataOnRequestId(Map<Object, Object> objectArgs, long reqId) 
	{	
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{									
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");					 

			Map loginMap = (Map) objectArgs.get("baseLoginMap");				

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);		 	    	 
			long langId = Long.parseLong(loginMap.get("langId").toString());

			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			EmpEducationDAOImpl QuaDaoImplObj = new EmpEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			List deleteDraftLstObj=QuaDaoImplObj.getDrftDtlsLstonReqID(orgUserMst,reqId);				
			if(!deleteDraftLstObj.isEmpty())
			{							
				StringBuffer sbXML = new StringBuffer();				
				sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");										
				ListIterator litr = deleteDraftLstObj.listIterator();
				int count=1;
				while (litr.hasNext()) 
				{
					HrEisQualDtlTxn ht = (HrEisQualDtlTxn)litr.next();		

					String strLookupName = ht.getCmnLookupMstByQualificationId() != null ? ht.getCmnLookupMstByQualificationId().getLookupName() : "";

					if (!"".equals(strLookupName))
					{
						ht.setCmnLookupMstByQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLookupName, langId));
					}

					String strSubQualificationIdLookupName = ht.getCmnLookupMstBySubQualificationId() != null ? ht.getCmnLookupMstBySubQualificationId().getLookupName() : "";				

					if (!"".equals(strSubQualificationIdLookupName))
					{
						ht.setCmnLookupMstBySubQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strSubQualificationIdLookupName, langId));
					}

					String strDiciplineLookupName = ht.getCmnLookupMstByDicipline() != null ? ht.getCmnLookupMstByDicipline().getLookupName() : "";

					if (!"".equals(strDiciplineLookupName))
					{
						ht.setCmnLookupMstByDicipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strDiciplineLookupName, langId));
					}

					String strCourseCategoryLookupName = ht.getCmnLookupMstByCourseCategory() != null ? ht.getCmnLookupMstByCourseCategory().getLookupName() : "";				

					if (!"".equals(strCourseCategoryLookupName))
					{
						ht.setCmnLookupMstByCourseCategory(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strCourseCategoryLookupName, langId));
					}

					String strUnitsOfMarksLookupName = ht.getCmnLookupMstByUnitsOfMarks() != null ? ht.getCmnLookupMstByUnitsOfMarks().getLookupName() : "";				

					if (!"".equals(strUnitsOfMarksLookupName))
					{
						ht.setCmnLookupMstByUnitsOfMarks(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strUnitsOfMarksLookupName, langId));
					}

					String strMarksScored = ht.getMarksScored()!= null? ht.getMarksScored() : "";				

					if (!"".equals(strMarksScored))
					{
						ht.setMarksScored(strMarksScored);
					}


					sbXML.append("<DraftSrNo>");
					sbXML.append(count);
					sbXML.append("</DraftSrNo>");
					count++;

					sbXML.append("<DraftQua>");
					if(ht.getCmnLookupMstByQualificationId()!=null  && ht.getCmnLookupMstByQualificationId().getLookupDesc().equalsIgnoreCase("Select")==false)
					{sbXML.append(ht.getCmnLookupMstByQualificationId().getLookupDesc());}							
					else	{sbXML.append("null");}
					sbXML.append("</DraftQua>");

					sbXML.append("<DraftSubQua>");
					if(ht.getCmnLookupMstBySubQualificationId()!=null  && ht.getCmnLookupMstBySubQualificationId().getLookupDesc().equalsIgnoreCase("Select")==false)
					{							
						sbXML.append(ht.getCmnLookupMstBySubQualificationId().getLookupDesc());
					}
					else {sbXML.append("null");}							
					sbXML.append("</DraftSubQua>");

					sbXML.append("<DraftUnitInst>");
					if(ht.getUniInstituteBoard()!=null && ht.getUniInstituteBoard().equals("")==false)
					{							
						sbXML.append(ht.getUniInstituteBoard());
					}
					else {sbXML.append("null");}													
					sbXML.append("</DraftUnitInst>");

					sbXML.append("<DraftDici>");
					if(ht.getCmnLookupMstByDicipline()!=null && ht.getCmnLookupMstByDicipline().getLookupDesc().equalsIgnoreCase("Select")==false)
					{
						sbXML.append(ht.getCmnLookupMstByDicipline().getLookupDesc());
					}
					else
					{
						sbXML.append("null");
					}
					sbXML.append("</DraftDici>");

					sbXML.append("<DraftCate>");
					if(ht.getCmnLookupMstByCourseCategory()!=null && ht.getCmnLookupMstByCourseCategory().getLookupDesc().equalsIgnoreCase("Select")==false)
					{
						sbXML.append(ht.getCmnLookupMstByCourseCategory().getLookupDesc());
					}
					else
					{
						sbXML.append("null");
					}
					sbXML.append("</DraftCate>");

					sbXML.append("<DraftUnit>");
					if(ht.getCmnLookupMstByUnitsOfMarks()!=null && ht.getCmnLookupMstByUnitsOfMarks().getLookupDesc().equalsIgnoreCase("Select")==false)
					{												
						sbXML.append(ht.getCmnLookupMstByUnitsOfMarks().getLookupDesc());
					}
					else{sbXML.append("null");}
					sbXML.append("</DraftUnit>");

					sbXML.append("<DraftYear>");
					if(ht.getYearOfPassing()!=0)
					{sbXML.append(ht.getYearOfPassing());}							
					else
					{sbXML.append("null");}
					sbXML.append("</DraftYear>");


					sbXML.append("<RequestFlag>");
					if(ht.getRequestFlag()!=null && ht.getRequestFlag().equals("")==false)
					{sbXML.append(ht.getRequestFlag());}							
					else
					{sbXML.append("null");}							
					sbXML.append("</RequestFlag>");

					sbXML.append("<DraftMark>");
					if(ht.getMarksScored()!=null && ht.getMarksScored().equals("")==false)
					{sbXML.append(ht.getMarksScored());}							
					else
					{sbXML.append("null");}								
					sbXML.append("</DraftMark>");
				}
				String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();				 
				logger.info("tempXML for Draft Request id is::" + tempStr);
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
			logger.error("Exception while getting a Draft Dtls on Request ID  for Education in Service",e);
		}
		return objRes;
	}
	
	public ResultObject getDraftData(Map<Object, Object> objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{									
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");					 

			Map loginMap = (Map) objectArgs.get("baseLoginMap");				

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);				 	    	 

			EmpEducationDAOImpl QuaDaoImplObj = new EmpEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			List draftLstObj=QuaDaoImplObj.getDrftDtlsLst(orgUserMst);
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
			ApproveEducationDAOImpl appEmpEduDAO = new ApproveEducationDAOImpl(HrEisQualDtl.class,serv.getSessionFactory());
			List approveDatalstObj = appEmpEduDAO.getApproveRequestonUserId(orgUserMst);				 
			objectArgs.put("ApprovedData", approveDatalstObj);
			objRes.setResultValue(objectArgs); 	  	
		}	
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting a Draft Dtls For All Request  for Education in Service",e);
		}
		return objRes; 
	}

	public ResultObject educationSaveAsDraft(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try
		{					       
			HrEisQualDtlTxn lObjQual=(HrEisQualDtlTxn) objectArgs.get("QualificationDtls");	

			String xmlFileIdStr = FileUtility.voToXmlFileByXStream(lObjQual);
			logger.info("Emp Education Save Draft  xml file generated is::::::::" + xmlFileIdStr);
			objectArgs.put("ajaxKey",xmlFileIdStr);			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ajaxData");						    	
		}	
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while in making xml file of Qualification Draft Dtls in Service",e);
		}
		return objRes; 				          
	}
	
	public ResultObject getEmpEduPendinRequest(Map<Object, Object> objectArgs,long reqId)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{ 
			if(objRes==null || objectArgs == null ) 
			{ 
				objRes.setResultCode(-1); 
				return objRes; 
			}
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			 

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);	

			long langId = Long.parseLong(loginMap.get("langId").toString());

			EmpEducationDAOImpl QualificationDAO = new EmpEducationDAOImpl(HrEisQualDtl.class,serv.getSessionFactory());			 	
			List PendingReq =QualificationDAO.getPendingRequestLst(reqId,orgUserMst);
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			/************ Getting a Persons Pending Request ************/			

			ListIterator itr= PendingReq.listIterator();			 			
			HrEisQualDtlTxn lVObject=null;	
			StringBuffer sbXML = new StringBuffer();
			if(PendingReq.isEmpty()==false)
			{
				while(itr.hasNext()) 
				{
					lVObject = (HrEisQualDtlTxn)itr.next();

					String strLookupName = lVObject.getCmnLookupMstByQualificationId() != null ? lVObject.getCmnLookupMstByQualificationId().getLookupName() : "";

					if (!"".equals(strLookupName))
					{
						lVObject.setCmnLookupMstByQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLookupName, langId));
					}

					String strSubQualificationIdLookupName = lVObject.getCmnLookupMstBySubQualificationId() != null ? lVObject.getCmnLookupMstBySubQualificationId().getLookupName() : "";				

					if (!"".equals(strSubQualificationIdLookupName))
					{
						lVObject.setCmnLookupMstBySubQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strSubQualificationIdLookupName, langId));
					}

					String strDiciplineLookupName = lVObject.getCmnLookupMstByDicipline() != null ? lVObject.getCmnLookupMstByDicipline().getLookupName() : "";

					if (!"".equals(strDiciplineLookupName))
					{
						lVObject.setCmnLookupMstByDicipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strDiciplineLookupName, langId));
					}

					String strCourseCategoryLookupName = lVObject.getCmnLookupMstByCourseCategory() != null ? lVObject.getCmnLookupMstByCourseCategory().getLookupName() : "";				

					if (!"".equals(strCourseCategoryLookupName))
					{
						lVObject.setCmnLookupMstByCourseCategory(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strCourseCategoryLookupName, langId));
					}

					String strUnitsOfMarksLookupName = lVObject.getCmnLookupMstByUnitsOfMarks() != null ? lVObject.getCmnLookupMstByUnitsOfMarks().getLookupName() : "";				

					if (!"".equals(strUnitsOfMarksLookupName))
					{
						lVObject.setCmnLookupMstByUnitsOfMarks(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strUnitsOfMarksLookupName, langId));
					}

					String strMarksScored = lVObject.getMarksScored()!= null? lVObject.getMarksScored() : "";				

					if (!"".equals(strMarksScored))
					{
						lVObject.setMarksScored(strMarksScored);
					}

					sbXML.append("<PendingQua>");
					sbXML.append(lVObject.getCmnLookupMstByQualificationId().getLookupDesc());
					sbXML.append("</PendingQua>");

					sbXML.append("<PendingSubQua>");
					sbXML.append(lVObject.getCmnLookupMstBySubQualificationId().getLookupDesc());
					sbXML.append("</PendingSubQua>");

					sbXML.append("<PendingUnitInst>");
					sbXML.append(lVObject.getUniInstituteBoard());
					sbXML.append("</PendingUnitInst>");

					sbXML.append("<PendingYear>");												
					sbXML.append(lVObject.getYearOfPassing());
					sbXML.append("</PendingYear>");

					sbXML.append("<PendingDici>");
					if(lVObject.getCmnLookupMstByDicipline()!=null && lVObject.getCmnLookupMstByDicipline().getLookupDesc().equalsIgnoreCase("Select")==false)
					{
						sbXML.append(lVObject.getCmnLookupMstByDicipline().getLookupDesc());
					}
					else
					{
						sbXML.append("-");
					}
					sbXML.append("</PendingDici>");

					sbXML.append("<PendingCate>");
					if(lVObject.getCmnLookupMstByCourseCategory()!=null && lVObject.getCmnLookupMstByCourseCategory().getLookupDesc().equalsIgnoreCase("Select")==false)
					{
						sbXML.append(lVObject.getCmnLookupMstByCourseCategory().getLookupDesc());
					}
					else
					{
						sbXML.append("-");
					}
					sbXML.append("</PendingCate>");

					sbXML.append("<PendingUnit>");
					if(lVObject.getCmnLookupMstByUnitsOfMarks()!=null && lVObject.getCmnLookupMstByUnitsOfMarks().getLookupDesc().equalsIgnoreCase("Select")==false)
					{
						sbXML.append(lVObject.getCmnLookupMstByUnitsOfMarks().getLookupDesc());
					}
					else
					{
						sbXML.append("-");
					}
					sbXML.append("</PendingUnit>");

					sbXML.append("<PendingMark>");
					sbXML.append(lVObject.getMarksScored());
					sbXML.append("</PendingMark>");

					sbXML.append("<PendingreqFlag>");
					sbXML.append(lVObject.getRequestFlag());
					sbXML.append("</PendingreqFlag>");
				}
			}			
			/***********  end of getting a pending dtls code*********/
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();				 
			logger.info("tempXML for Draft Request id is:: " + tempStr);
			objectArgs.put("ajaxKey", tempStr);
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);							 	  		
			objRes.setViewName("ajaxData");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting a Qualification Dtls in Service",e);
		}
		return objRes;
	}
	
	public ResultObject getEmployeeEducationDtls(Map<Object, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{ 
			if(objRes==null || objectArgs == null ) 
			{ 
				objRes.setResultCode(-1); 
				return objRes; 
			}
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");


			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			long userID = Long.parseLong(loginMap.get("userId").toString());	
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst= orgUserMstDaoImpl.read(userID);
			long langId = Long.parseLong(loginMap.get("langId").toString());	

			EmpEducationDAOImpl QualificationDAO = new EmpEducationDAOImpl(HrEisQualDtl.class,serv.getSessionFactory());			 			 
			List QualificationLstObj = QualificationDAO.getEmpDtlsSortedByDate(orgUserMst);			

			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());

			ListIterator itr= QualificationLstObj.listIterator();

			List<Object> empEdulstObj = new ArrayList<Object>();
			List<String> voToXmllstObj = new ArrayList<String>();
			List<String> requesetLstObj = new ArrayList<String>();

			CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
			request.getSession().removeAttribute("attachmentBiometricEducation_EDITVO");	

			int iCounter = 0;
			EmpEducationDAOImpl empEduDAOObj = new EmpEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory()); 

			while(itr.hasNext())
			{		
				iCounter++;

				HrEisQualDtl lObjCO1 =(HrEisQualDtl)itr.next();

				String strLookupName = lObjCO1.getCmnLookupMstByQualificationId() != null ? lObjCO1.getCmnLookupMstByQualificationId().getLookupName() : "";

				if (!"".equals(strLookupName))
				{
					lObjCO1.setCmnLookupMstByQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strLookupName, langId));
				}

				String strSubQualificationIdLookupName = lObjCO1.getCmnLookupMstBySubQualificationId() != null ? lObjCO1.getCmnLookupMstBySubQualificationId().getLookupName() : "";				

				if (!"".equals(strSubQualificationIdLookupName))
				{
					lObjCO1.setCmnLookupMstBySubQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strSubQualificationIdLookupName, langId));
				}

				String strDiciplineLookupName = lObjCO1.getCmnLookupMstByDicipline() != null ? lObjCO1.getCmnLookupMstByDicipline().getLookupName() : "";

				if (!"".equals(strDiciplineLookupName))
				{
					lObjCO1.setCmnLookupMstByDicipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strDiciplineLookupName, langId));
				}

				String strCourseCategoryLookupName = lObjCO1.getCmnLookupMstByCourseCategory() != null ? lObjCO1.getCmnLookupMstByCourseCategory().getLookupName() : "";				

				if (!"".equals(strCourseCategoryLookupName))
				{
					lObjCO1.setCmnLookupMstByCourseCategory(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strCourseCategoryLookupName, langId));
				}

				String strUnitsOfMarksLookupName = lObjCO1.getCmnLookupMstByUnitsOfMarks() != null ? lObjCO1.getCmnLookupMstByUnitsOfMarks().getLookupName() : "";				

				if (!"".equals(strUnitsOfMarksLookupName))
				{
					lObjCO1.setCmnLookupMstByUnitsOfMarks(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strUnitsOfMarksLookupName, langId));
				}

				String strMarksScored = lObjCO1.getMarksScored()!= null? lObjCO1.getMarksScored() : "";				

				if (!"".equals(strMarksScored))
				{
					lObjCO1.setMarksScored(strMarksScored);
				}

				String strUniInstituteBoard = lObjCO1.getUniInstituteBoard() != null ? lObjCO1.getUniInstituteBoard() : "";				
				lObjCO1.setUniInstituteBoard(strUniInstituteBoard);

				lObjCO1.getYearOfPassing();

				if(lObjCO1.getCmnAttachmentMst()!=null)
				{
					CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(lObjCO1.getCmnAttachmentMst().getAttachmentId());
					request.getSession().setAttribute("attachmentBiometricEducation"+iCounter+"_EDITVO", cmnAttachmentMst);
					lObjCO1.setCmnAttachmentMst(cmnAttachmentMst);
				}
				String xmlFileIdStr = FileUtility.voToXmlFileByXStream(lObjCO1);				
				voToXmllstObj.add(xmlFileIdStr);

				/* Checking for the Record whether this record is avilable in TRN or NOt */ 
				List requestLstObj = empEduDAOObj.findRequestForthisRecord(lObjCO1.getSrNo(),orgUserMst);
				ListIterator reqLstItr = requestLstObj.listIterator();
				boolean flag=true;				
				while(reqLstItr.hasNext())
				{
					HrEisQualDtlTxn row = (HrEisQualDtlTxn)reqLstItr.next();	

					if(row.getRowNumber().equals(lObjCO1.getSrNo()))
					{
						requesetLstObj.add("No");						
						flag=false;
					}
				}
				if(flag==true){requesetLstObj.add("Yes");}				
				empEdulstObj.add(lObjCO1);				
			}									

			/************ Getting a Persons Pending Request ************/
			EmpEducationDAOImpl PendingDtlsDAO = new EmpEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			List personRequestLstObj = PendingDtlsDAO.getEmpRequest(orgUserMst);
			List<Object> requestLstObj = new ArrayList<Object>();
			itr= personRequestLstObj.listIterator();			
			if(personRequestLstObj.isEmpty()==false)
			{
				while(itr.hasNext()) 
				{
					try
					{
						Object[] addRow = new Object[3]; 
						Object row[] = (Object[])itr.next();
						long reqId= (Long)row[2]; // Correspondence Id
	
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
			objRes=getDraftData(objectArgs);
			/*** End of Getting a  Draft Request  ****/
			objectArgs.put("requesetLstObj", requesetLstObj);
			objectArgs.put("XmlFile",voToXmllstObj);
			objectArgs.put("QualificationDtls",empEdulstObj);
			objectArgs.put("RequestDtls",requestLstObj);	

			/**   For Employee Data   **/
			objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); 
			/**End OF Employee Data **/
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("EducationDtls");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting a Qualification Dtls in Service (In Workflow)",e);
		}
		return objRes;
	}
	
	public ResultObject getComboDtls(Map<String, Object> objectArgs) {
		ResultObject objRes = new ResultObject();		
		try 
		{ 
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());			

			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());			

			List unitlookUpLstObj = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("edu_Unit", langId);
			List QualookUpLstObj = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("edu_Qualifications", langId);	
			List courseCategoryLstObj = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("edu_courseCategory", langId);
			List<Object> lLstDiscipline = new ArrayList<Object>();
			List<Object> lookupLstObj_SubQua= new ArrayList<Object>();
			ListIterator litr = QualookUpLstObj.listIterator();				
			while (litr.hasNext()) 
			{					
				CmnLookupMst cmnLookUpObj = (CmnLookupMst) litr.next();					
				List temp = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang(cmnLookUpObj.getLookupName(), langId);					
				for(int tempCount=0;tempCount<temp.size();tempCount++)
				{											
					lookupLstObj_SubQua.add(temp.get(tempCount));
					CmnLookupMst cmnLookupMst = (CmnLookupMst) temp.get(tempCount);
					List discTempLst = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang(cmnLookupMst.getLookupName(), langId);
					if(discTempLst.isEmpty()==false)
					{
						for(int tempC=0;tempC<discTempLst.size();tempC++)
						{						
							lLstDiscipline.add(discTempLst.get(tempC));
						}
					}
				}				
			}				

			List arYear = new ArrayList();
			Calendar calendar = new GregorianCalendar();
			for(int startYr = calendar.get(Calendar.YEAR); startYr >= 1950; startYr--)
			{
				arYear.add(startYr);
			}

			objectArgs.put("Unit", unitlookUpLstObj);
			objectArgs.put("Qualifications", QualookUpLstObj);	
			objectArgs.put("courseCategory", courseCategoryLstObj);
			objectArgs.put("Discipline",lLstDiscipline);
			objectArgs.put("SubQualifications", lookupLstObj_SubQua);
			objectArgs.put("arYear", arYear);

			objRes.setResultValue(objectArgs);				
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting a Combo Options Dtls in Service",e);
		}
		return objRes;
	}

	public ResultObject getComboDetail(Map objectArgs,String lintCmbid)
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

			long langId = Long.parseLong(loginMap.get("langId").toString());			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List lLstObj = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang(lintCmbid, langId);			
			/* AJAX Code Insert */
			StringBuffer sbXML = new StringBuffer();				
			sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");				

			if(lLstObj.isEmpty()==false)
			{
				ListIterator litr = lLstObj.listIterator();
				while (litr.hasNext()) 
				{
					CmnLookupMst cmnLookUpObj = (CmnLookupMst) litr.next();
					sbXML.append("<SubQualification>");
					sbXML.append(cmnLookUpObj.getLookupDesc());
					sbXML.append("</SubQualification>");
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
			logger.error("Exception while getting a SubQualification Combo options Dtls in Service",e);
		}
		return objRes;
	}

	public ResultObject addorEditEmpEducationDtls(Map objectArgs, boolean blnWorkFlowEnabled)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try{																		    					    	
			String xmlFileIdStr = "";
			if (blnWorkFlowEnabled)										//IFMS
			{		
				HrEisQualDtlTxn lObjQual=(HrEisQualDtlTxn) objectArgs.get("QualificationDtls");			
				xmlFileIdStr = FileUtility.voToXmlFileByXStream(lObjQual);
			}
			else
			{
				HrEisQualDtl lObjQualMst =(HrEisQualDtl) objectArgs.get("QualificationMstDtls");			
				xmlFileIdStr = FileUtility.voToXmlFileByXStream(lObjQualMst);
			}

			String lStrValidatestr = objectArgs.get("lStrValidatestr").toString();
			if(lStrValidatestr.equalsIgnoreCase("")==false)
			{
				xmlFileIdStr=xmlFileIdStr+"$/$"+lStrValidatestr;
			}
			logger.info("Emp Education xml file generated is::::::::" + xmlFileIdStr);			
			objectArgs.put("ajaxKey",xmlFileIdStr);			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ajaxData");					
		}catch (Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while adding a Qualification Dtls in Xml in Service VO",e);
		}
		return objRes;
	}	

	public ResultObject submitEmpEducationDtls(Map<Object, Object> objectArgs)
	{		 		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try{												
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	

			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			boolean submitFlag = false;

			long userID = Long.parseLong(loginMap.get("userId").toString());

			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);	

			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    	 			 


			/*  Find The Maximum REqId  To Enter The Record*/

			EmpEducationDAOImpl QuaDaoImplObj = new EmpEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());

			/* Getting a DB ID*/								 
			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			/* End og DB Id code*/

			long langId = Long.parseLong(loginMap.get("langId").toString());				 

			/* Getting a Loc ID Code*/
			long locId =  Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			/* End of Loc ID */

			/*Generateing a Req  _ id */
			long reqId = IDGenerateDelegate.getNextId("hr_eis_qual_dtl_txn",objectArgs);
			logger.info("Pk Genraated for hr_eis_qual_dtl_txn ::::::   "+reqId);
			/*end of req Id code***/
			long srNo=0;
			HrEisQualDtlTxn lVObject=new HrEisQualDtlTxn();			  	 
			String[] txnXML = (String[]) objectArgs.get("encXML");		
			String[] enc_rowNumForAttachment =(String[])objectArgs.get("enc_rowNumForAttachment");

			List<HrEisQualDtlTxn> lstlockupTxnVO = FileUtility.xmlFilesToVoByXStream(txnXML);

			/**Checking a Code whether the request save as a draft or not***/
			String draft =objectArgs.get("draft").toString();			     
			/** end of draft or not **/
			boolean workFlaow=false;
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			int j1=1,i1=0;

			long corrsId=0; //sunil
			List<HrEisQualDtlTxn> arHrEisQualDtlTxn = new ArrayList<HrEisQualDtlTxn>(); //sunil
			CmnAttachmentMst cmnAttachmentMst = null;
			HrEisQualDtlTxnId lObjCOid = null;
			Date currDate = new java.sql.Timestamp((new java.util.Date()).getTime());

			for(Iterator i = lstlockupTxnVO.iterator(); i.hasNext();) 
			{			    	 
				Object Obj = i.next();
				if(Obj != null)
				{
					Class<? extends Object> c=Obj.getClass();				    	 
					if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisQualDtlTxn"))
					{
						lVObject = (HrEisQualDtlTxn)Obj;	

						lVObject.setCmnLookupMstByQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByQualificationId().getLookupName(), langId));			    		 
						lVObject.setCmnLookupMstBySubQualificationId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstBySubQualificationId().getLookupName(), langId));	    		 				    		 	    		
						lVObject.setCmnLookupMstByUnitsOfMarks(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByUnitsOfMarks().getLookupName(), langId));
						lVObject.setCmnLookupMstByDicipline(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByDicipline().getLookupName(), langId));
						lVObject.setCmnLookupMstByCourseCategory(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByCourseCategory().getLookupName(), langId));

						String rowNumber  =  enc_rowNumForAttachment[i1];

						objectArgs.put("rowNumber", rowNumber);
						objectArgs.put("attachmentName", "attachmentBiometricEducation"); 
						ResultObject resultObj1 = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
						objectArgs = (Map) resultObj1.getResultValue();			
						Long lAttachId= null;
						ResultObject resultObjAttch = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
						objectArgs = (Map) resultObjAttch.getResultValue();
//						logger.info("{objectArgs  :   " + objectArgs + "\n");
						lAttachId = (Long) objectArgs.get("AttachmentId_attachmentBiometricEducation");
						logger.info("lAttachId  :  "+ lAttachId);
						if(lAttachId!=null)
						{
							cmnAttachmentMst = new CmnAttachmentMst();						    	 
							cmnAttachmentMst.setAttachmentId(lAttachId);
							logger.info("IN ATTACHMENT ID>>>>>>>>>>>>>>>>>>>>"+lAttachId);
							lVObject.setCmnAttachmentMst(cmnAttachmentMst);
						}	
						else
						{
							lVObject.setCmnAttachmentMst(null);
						}
						srNo=srNo+1;
						lObjCOid =new HrEisQualDtlTxnId();					 
						lObjCOid.setReqId(reqId);					 
						lObjCOid.setSrNo(srNo);						 
						if("1".equals(draft)) {lVObject.setDraftFlag("Y"); submitFlag=false; lVObject.setCheckStatus("Y"); }
						else { lVObject.setDraftFlag("N");
						submitFlag=true;
						workFlaow=true;
						lVObject.setActionFlag("P");
						logger.info("Parent qualification  Id : " + lVObject.getRowNumber());							 	
						lVObject.setRowNumber(lVObject.getRowNumber());
						lVObject.setCheckStatus("Y"); //sunil
						}		
						logger.info("lObjQual:::::::" +lVObject.getYearOfPassing());
						lVObject.setId(lObjCOid);
						lVObject.setOrgUserMstByUserId(orgUserMst);						  	 
						lVObject.setCmnLocationMst(cmnLocationMst);
						lVObject.setCmnDatabaseMst(cmnDatabaseMst);
						lVObject.setOrgUserMstByCreatedBy(orgUserMst); 
						lVObject.setOrgPostMstByCreatedByPost(orgPostMst);
						lVObject.setCreatedDate(currDate);

						arHrEisQualDtlTxn.add(lVObject);
					}
					j1++;
					i1++;
				}
			} 

			/**************Delete Data From the Emp Education Dtls******************/
			String deleteEduDtls = objectArgs.get("deleteEduDtls").toString();
			logger.info("deleteEduDtls ::: " +deleteEduDtls);
			StringTokenizer st= new StringTokenizer(deleteEduDtls,",");				 
			String deleteEduStrArrObj[]= null;
			EmpEducationDAOImpl QuaDaoImplObj1 = new EmpEducationDAOImpl(HrEisQualDtl.class,serv.getSessionFactory());
			
			while(st.hasMoreTokens())
			{					 
				String deleteEduStrObj= st.nextElement().toString();
				logger.info("deleteEduStrObj :::: " +deleteEduStrObj);
				deleteEduStrArrObj= new String[1];					 
				deleteEduStrArrObj[0]= deleteEduStrObj; 
				List deleteLstObj = FileUtility.xmlFilesToVoByXStream(deleteEduStrArrObj);

				for(Iterator j  = deleteLstObj.iterator(); j.hasNext();) 
				{			    		 
					Object obj = j.next();	

					if(obj != null)
					{					    		 
						Class<? extends Object> c=obj.getClass();
						logger.info("c ::::: " + c);
						if(c.toString().contains("com.tcs.sgv.eis.valueobject.HrEisQualDtlTxn"))
						{
							HrEisQualDtlTxn delVObject = (HrEisQualDtlTxn)obj;
							logger.info("delVObject : " +delVObject.getId().getReqId());
							logger.info("delVObject : " +delVObject.getId().getSrNo());
							delVObject = (HrEisQualDtlTxn) QuaDaoImplObj.read(delVObject.getId());
							if(delVObject!=null)
							{
								delVObject.setOrgUserMstByUserId(orgUserMst);						    	 
								QuaDaoImplObj.delete(delVObject);
							}
						}
						else 
						{	
							submitFlag=true;
							HrEisQualDtl ht = (HrEisQualDtl)obj;
							ht=(HrEisQualDtl) QuaDaoImplObj1.read(ht.getSrNo());
							ht.setDeleteFlag("Y");
							ht.setOrgUserMstByUpdatedBy(orgUserMst);
							ht.setOrgPostMstByUpdatedByPost(orgPostMst);				    
							ht.setUpdatedDate(currDate);
							QuaDaoImplObj1.update(ht);
						}
					}
				}
			}
			if("1".equals(draft) && submitFlag!=true) {submitFlag=false; }
			else { submitFlag=true; }
			if(submitFlag==false)
			{
				objectArgs.put("draft","Y");
			}

			if(submitFlag==true && workFlaow==true)
			{
				corrsId=HrmsCommonMessageServImpl.createCorr(objectArgs, serv, DOC_ID,CORR_DESC);//For Creating Corrospondance Id
				logger.info("corrsId=========In EmpEducationServiceImpl==="+corrsId);
				objectArgs.put("modId", EMP_EDUCATION_MODID);
				objectArgs.put("appReqId", corrsId);
				HrmsCommonMessageServImpl.createModEmpRltTuple(serv, objectArgs, userID);
				objRes=HrmsCommonMessageServImpl.getForwardToDetail(serv, objectArgs, DOC_ID, corrsId);   
				String msg="EMP_EDU_REQUEST";
				objectArgs.put("status", "PENDING"); 
				objectArgs.put("msg", msg);  
			}
			else
			{
				objectArgs.put("draft","Y");//For message view for submit page
				objRes.setViewName("SubmitMessagePage");
			}	

			ListIterator ltrHrEisQualDtlTxn =  arHrEisQualDtlTxn.listIterator(); //sunil
			HrEisQualDtlTxn objHrEisQualDtlTxn = null;

			while (ltrHrEisQualDtlTxn.hasNext()) 
			{
				objHrEisQualDtlTxn = (HrEisQualDtlTxn) ltrHrEisQualDtlTxn.next();
				objHrEisQualDtlTxn.setCorrsId(corrsId);
				QuaDaoImplObj.create(objHrEisQualDtlTxn);
			}
			objRes.setResultValue(objectArgs); 	  	
			objRes.setResultCode(ErrorConstants.SUCCESS);
		}catch (Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Submitte a Qualification Dtls",e);
		}
		return objRes;
	}

	public ResultObject deleteDraftRequest(Map<Object, Object> objectArgs, String reqId) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);	
		try{						
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
			Map loginMap = (Map) objectArgs.get("baseLoginMap");

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);	

			StringBuffer sbXML = new StringBuffer();			
			EmpEducationDAOImpl empEduDAOImpl = new EmpEducationDAOImpl(HrEisQualDtlTxn.class,serv.getSessionFactory());
			List lLstObj = empEduDAOImpl.deleteDraftReqId(orgUserMst, Long.parseLong(reqId.trim()));		
			ListIterator li = lLstObj.listIterator();			
			while(li.hasNext())
			{				
				HrEisQualDtlTxn ht = (HrEisQualDtlTxn)li.next();
				sbXML.append("<Link>");				
				sbXML.append(ht.getRowNumber());
				sbXML.append("</Link>");
				empEduDAOImpl.delete(ht);				
			}
			/* AJAX Code Insert */

			sbXML.append("<DeleteDraft>");
			sbXML.append(reqId);
			sbXML.append("</DeleteDraft>");											
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();
			logger.info("tempXML for Delete Draft is:: " + tempStr);			
			objectArgs.put("ajaxKey", tempStr);			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ajaxData");
		}catch(Exception e)
		{			
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while deleting draft of Qualification Dtls",e);
		}
		return objRes;
	}
}		 