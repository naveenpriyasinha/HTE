package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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
import com.tcs.sgv.eis.dao.CoCurricularDAO;
import com.tcs.sgv.eis.dao.CoCurricularDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisCuriculrDtl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class CoCurServiceImpl extends ServiceImpl implements CoCurricularService 
{
	private static final Log logger = LogFactory.getLog(CoCurServiceImpl.class);	
	static long statusLookupId = 18;
	/*
	 * (non-Javadoc)
	 * @see com.tcs.sgv.eis.personal.service.CoCurricularService#getCoCurricularlDetails(java.util.Map)
	 * return ResultObj
	 */
	public ResultObject getCoCurricularlDetails(Map<String, Object> objectArgs) 
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		/** IFMS - Starts*/
		boolean blnWorkFlowEnabled = true;
		
		if (objectArgs.get("blnWorkFlowEnabled") != null)
		{
			blnWorkFlowEnabled = Boolean.valueOf(objectArgs.get("blnWorkFlowEnabled").toString());
		}
		
		objectArgs.put("blnWorkFlowEnabled",blnWorkFlowEnabled); 
	    long iUserId = (objectArgs.get("userId") != null && !objectArgs.get("userId").toString().equals("")) ? Long.parseLong(objectArgs.get("userId").toString()) : 0;
	    objectArgs.put("userId",iUserId); 
	    
	   /** IFMS - Ends*/
		
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			String lStrFlag =objectArgs.get("flag").toString();
			logger.info("CoCurricular Page Flag ::  " + lStrFlag);
			
			if ("getComboDetails".equalsIgnoreCase(lStrFlag)) {												
				objRes = getComboDtls(objectArgs);
				objRes = GetPersonInfo(objectArgs, blnWorkFlowEnabled, iUserId); /** IFMS*/			
			} 
			else if ("getSubTypeOfCoCurricular".equalsIgnoreCase(lStrFlag)) 
			{
				String cmbid = objectArgs.get("cmbid").toString();		
				objRes = getComboDetail(objectArgs, cmbid);
			}
			else if ("AddCoCurricularlDtls".equalsIgnoreCase(lStrFlag)) 
			{				
				objRes = getComboDtls(objectArgs);
				objRes = addCoCurricularDtls(objectArgs);
			} 					
			else if("SubmitCoCurricularDtls".equalsIgnoreCase(lStrFlag)) {				
				objRes = getComboDtls(objectArgs);
				objRes=submitCoCurricularDtls(objectArgs, blnWorkFlowEnabled, iUserId); /** IFMS*/	
			} 	
			
			
			
		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while calling Co Curricular Methods ",e);
		}
		return objRes;
	}
	public ResultObject getComboDtls(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject();		
		try 
		{ 
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");			
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			
			/*Getting a Lang Id*/
			long langId = Long.parseLong(loginMap.get("langId").toString());			
			/* End Of getting a Lang ID*/
			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());			
			
			List<CmnLookupMst> CoCurrlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("CoCurricular", langId);											
			List<CmnLookupMst> CompAtLookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("co_Competed_At", langId);			
			List<Object> lookupLstObj_SubCoCurr=new ArrayList<Object>();
						
			ListIterator<CmnLookupMst> litr = CoCurrlookUpList.listIterator();				
			while (litr.hasNext()) 
			{
				CmnLookupMst cmnLookUpObj = (CmnLookupMst) litr.next();				
				List temp= cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang(cmnLookUpObj.getLookupName(), langId);
				for(int tempCount=0;tempCount<temp.size();tempCount++)
				{						
					lookupLstObj_SubCoCurr.add(temp.get(tempCount));
				}
			}
			cmnLookupMstDAOImpl=null;						
			
			List arYear = new ArrayList();
			Calendar calendar = new GregorianCalendar();
			
			for(int startYr = calendar.get(Calendar.YEAR); startYr >= 1950; startYr--)
			{
				arYear.add(startYr);
				logger.info("startYr=="+startYr);
			}
			objectArgs.put("arYear", arYear);
			objectArgs.put("Competed_at", CompAtLookUpList);	
			objectArgs.put("Curricular", CoCurrlookUpList);
			objectArgs.put("SubCurricular", lookupLstObj_SubCoCurr);	
			objRes.setResultValue(objectArgs);
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting Combo Dtls of Co Curricular Data ",e);	
		}
		return objRes;
	}
	/*
	 * (non-Javadoc)
	 * @see com.tcs.sgv.eis.personal.service.CoCurricularService#getComboDetail(java.util.Map, int)
	 * return ResultObj
	 */
	public ResultObject getComboDetail(Map<String, Object> objectArgs, String lintcmbid) {
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{			
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");										
			/*  Get The Login Language of The Person*/
			long langId = Long.parseLong(loginMap.get("langId").toString());
			/*  End of getting Person Language*/
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			/*long cmnLookupId = Long.parseLong(lintcmbid);								
			CmnLookupMst cmnLookupObj=cmnLookupMstDAOImpl.getLookUpVOByLookUpIDAndLang(cmnLookupId,langId);*/						
			List SubCoCurrlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang(lintcmbid, langId);
			
			/* AJAX Code Insert */
			
				StringBuffer sbXML = new StringBuffer();				
				sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +				
							 "<SubTypeOfCoCurricular>" +
							 "--Select--" +
							 "</SubTypeOfCoCurricular>" +
							 "<SubTypeOfCoCurricularID>" +
							 "Select" +
							 "</SubTypeOfCoCurricularID>");
				ListIterator litr = SubCoCurrlookUpList.listIterator();
				while (litr.hasNext()) 
				{
					CmnLookupMst cmnLookUpObj = (CmnLookupMst) litr.next();
					sbXML.append("<SubTypeOfCoCurricular>");
					sbXML.append(cmnLookUpObj.getLookupDesc());
					sbXML.append("</SubTypeOfCoCurricular>" +
								 "<SubTypeOfCoCurricularID>");
					sbXML.append(cmnLookUpObj.getLookupName());
					sbXML.append("</SubTypeOfCoCurricularID>");
				}					
				
				String tempStr = new AjaxXmlBuilder().addItem("key_ajax",sbXML.toString()).toString();																					
				logger.info("tempXML is::" + tempStr);
				objectArgs.put("ajaxKey", tempStr);			
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);			
				objRes.setViewName("ajaxData");
				cmnLookupMstDAOImpl=null;				
			/* Ajax Code End */
				
		} catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting Sub type of Co Curricular Data ",e);			
		}
		return objRes;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tcs.sgv.eis.personal.service.CoCurricularService#addCoCurricularDtls(java.util.Map, int)
	 * return ResultObj
	 */
	public ResultObject addCoCurricularDtls(Map<String, Object> objectArgs) 
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{				
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}				
			HrEisCuriculrDtl lObjCO = (HrEisCuriculrDtl) objectArgs.get("CoCurricularDtls");			
			String xmlFileIdStr = FileUtility.voToXmlFileByXStream(lObjCO);			
			logger.info("Co Curricular xml file generated is::::::::" + xmlFileIdStr);
			objectArgs.put("ajaxKey", xmlFileIdStr);						
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);			
			objRes.setViewName("ajaxData");			  			
		} 
		catch (Exception e) 
		{
			objRes.setThrowable(e);			
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Adding CoCurricular Data ",e);		
		}
		return objRes;
	}	
	 

	public ResultObject submitCoCurricularDtls(Map<String, Object> objectArgs, boolean blnWorkFlowEnabled, long iUserId) 
	{	
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);		 
		try
		{ 
			logger.info("========== in service userid======="+ iUserId);
			logger.info("========== in service blnWorkFlowEnabled======="+ blnWorkFlowEnabled);
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");	

			/*  Get The Login Id of The Person*/
			long userID = Long.parseLong(loginMap.get("userId").toString());			 
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); 
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);	    	 

			/*End of the geting Person ID Code*/
			
			/* IFMS - starts */
			
			/*  Get The selected user Person's User Id*/
			OrgUserMst selectedOrgUserMst = null;
			if (!blnWorkFlowEnabled)
			{
				selectedOrgUserMst = orgUserMstDaoImpl.read(iUserId);
			}
			/*End of the geting Person ID Code*/
			/* IFMS - ends */


			/*  Get The Person Post */			 			 	    	 
			long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    	 			 
			/*End of the geting Person Post Code*/	    	 	    		    	

			/* Getting a DB ID*/								 
			long dbId = Long.parseLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			/* End og DB Id code*/

			/* Getting a Lang Id Code*/
			long langId = Long.parseLong(loginMap.get("langId").toString());			
			/* End of Lang ID Code*/

			/* Getting a Loc ID Code*/
			long locId =  Long.parseLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);				 
			/* End of Loc ID*/	

			/*Delete All Employee Dtls*/
				    	 
			CoCurricularDAO coCurrDAO = new CoCurricularDAOImpl(HrEisCuriculrDtl.class,serv.getSessionFactory());									
			String []  added_rowNumForAttachment = (String[]) objectArgs.get("added_rowNumForAttachment");
			List updatedVOList=(List)objectArgs.get("upDatedVOList");
			List deletedVOList=(List)objectArgs.get("deletedVOList");
			//List notModifiedVOList=(List)objectArgs.get("notModifiedVOList");
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			
			if(deletedVOList!=null)
			{
				ListIterator litr = deletedVOList.listIterator();
				while(litr.hasNext())
				{																
					HrEisCuriculrDtl cocurricular =(HrEisCuriculrDtl)litr.next();
					if(cocurricular.getSrNo()!=0)
					{
						HrEisCuriculrDtl readCoCurrObj = (HrEisCuriculrDtl) coCurrDAO.read(cocurricular.getSrNo());
						coCurrDAO.delete(readCoCurrObj);
					}
				}
			}
			
			if(updatedVOList!=null)
			{				
				ListIterator litr = updatedVOList.listIterator();
				int j=0;
				CmnAttachmentMst cmnAttachmentMst =  null;
				Date currDate = new Date();
				while(litr.hasNext())
				{																
					HrEisCuriculrDtl cocurricular =(HrEisCuriculrDtl)litr.next(); 			
					if(cocurricular.getSrNo()!=0)
					{
						HrEisCuriculrDtl readCoCurrObj = (HrEisCuriculrDtl) coCurrDAO.read(cocurricular.getSrNo());
						String rowNumber  =  added_rowNumForAttachment[j];
						readCoCurrObj.setSrNo(cocurricular.getSrNo());						
						
			    		objectArgs.put("rowNumber", rowNumber);
			    		objectArgs.put("attachmentName", "attachmentBiometricCocurricular"); 
						ResultObject resultObj1 = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
						objectArgs = (Map) resultObj1.getResultValue();			
						Long lAttachId= null;
						ResultObject resultObjAttch = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
						objectArgs = (Map) resultObjAttch.getResultValue();
						lAttachId = (Long) objectArgs.get("AttachmentId_attachmentBiometricCocurricular");
						
						
			    		if(lAttachId!=null)
			    		{
			    			cmnAttachmentMst = new CmnAttachmentMst();
							cmnAttachmentMst.setAttachmentId(lAttachId);
							readCoCurrObj.setCmnAttachmentMst(cmnAttachmentMst);
			    		}	
			    		else
			    		{
			    			
			    			readCoCurrObj.setCmnAttachmentMst(readCoCurrObj.getCmnAttachmentMst());
			    		}	
			    		
			    		if(cocurricular.getCmnLookupMstByCocurricularId().getLookupName()!=null && !cocurricular.getCmnLookupMstByCocurricularId().getLookupName().equals(""))
			    		{
			    			readCoCurrObj.setCmnLookupMstByCocurricularId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cocurricular.getCmnLookupMstByCocurricularId().getLookupName(), langId));
			    		}
			    		
			    		if(cocurricular.getCmnLookupMstBySubCocurricularId().getLookupName()!=null && !cocurricular.getCmnLookupMstBySubCocurricularId().getLookupName().equals(""))
			    		{
			    			readCoCurrObj.setCmnLookupMstBySubCocurricularId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cocurricular.getCmnLookupMstBySubCocurricularId().getLookupName(), langId));
			    		}
			    		
			    		if(cocurricular.getCmnLookupMstByCompetedAtId().getLookupName()!=null && !cocurricular.getCmnLookupMstByCompetedAtId().getLookupName().equals(""))
			    		{
			    			readCoCurrObj.setCmnLookupMstByCompetedAtId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(cocurricular.getCmnLookupMstByCompetedAtId().getLookupName(), langId));
			    		}
			    						
			    		if(cocurricular.getYearId()!=0)
			    		{
			    			readCoCurrObj.setYearId(cocurricular.getYearId());
			    		}
			    		
			    		readCoCurrObj.setSpecialAchievement(cocurricular.getSpecialAchievement());
						readCoCurrObj.setCmnLocationMst(cmnLocationMst);
						readCoCurrObj.setCmnDatabaseMst(cmnDatabaseMst);
						readCoCurrObj.setUpdatedDate(currDate);
						readCoCurrObj.setOrgPostMstByUpdatedByPost(orgPostMst);					
						readCoCurrObj.setOrgUserMstByUpdatedBy(orgUserMst);
						coCurrDAO.update(readCoCurrObj);
						j++;
					}									
				}
			}
			/*while(li.hasNext())
			{
				HrEisEmpCocurricular coCurrObj= (HrEisEmpCocurricular)li.next();					 					 					 
				coCurrDAO.delete(coCurrObj);
			}*/			 
			/*  End Delete Reocrd */

			/* Insert into Table*/  			  	 
			String[] txnXML = (String[]) objectArgs.get("encXML");			
			String []  enc_rowNumForAttachment = (String[]) objectArgs.get("enc_rowNumForAttachment");			
			List lstlockupTxnVO = FileUtility.xmlFilesToVoByXStream(txnXML);
			int j=0;
			HrEisCuriculrDtl lVObject = null;
			Date currDate = new java.util.Date();
			for(Iterator i = lstlockupTxnVO.iterator(); i.hasNext();) 
			{
				lVObject = new HrEisCuriculrDtl();
				lVObject = (HrEisCuriculrDtl)i.next();
				String rowNumber  =  enc_rowNumForAttachment[j];				
				
				if(lVObject.getCmnLookupMstByCocurricularId().getLookupName()!=null && !lVObject.getCmnLookupMstByCocurricularId().getLookupName().equals(""))
				{
					lVObject.setCmnLookupMstByCocurricularId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByCocurricularId().getLookupName(), langId));
				}
				
				if(lVObject.getCmnLookupMstBySubCocurricularId().getLookupName()!=null && !lVObject.getCmnLookupMstBySubCocurricularId().getLookupName().equals(""))
				{
					lVObject.setCmnLookupMstBySubCocurricularId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstBySubCocurricularId().getLookupName(), langId));
				}
				
				if(lVObject.getCmnLookupMstByCompetedAtId().getLookupName()!=null && !lVObject.getCmnLookupMstByCompetedAtId().getLookupName().equals(""))
				{
					lVObject.setCmnLookupMstByCompetedAtId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(lVObject.getCmnLookupMstByCompetedAtId().getLookupName(), langId));
				}
	    		
	    		lVObject.setTrnCounter(Integer.valueOf(1)); 	    		
	    		/*CoCurricular Id Generate*/
	    		Long lAttachId = null;				
					// For attachment - Start
	    		
	    		objectArgs.put("rowNumber", rowNumber);
	    		objectArgs.put("attachmentName", "attachmentBiometricCocurricular"); 
				ResultObject resultObj1 = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
				objectArgs = (Map) resultObj1.getResultValue();							
				ResultObject resultObjAttch = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
				objectArgs = (Map) resultObjAttch.getResultValue();
				lAttachId = (Long) objectArgs.get("AttachmentId_attachmentBiometricCocurricular");
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
	    		/* PK Genearation Code  */
	    		
	    		/*objectArgs.put("tablename", "hr_eis_curiculr_dtl");
				objectArgs.put("serviceLocator", serv);		
				ResultObject resultObj = serv.executeService("GENERATE_ID_SRVC", objectArgs);
				int receivedcode = resultObj.getResultCode();
				if (receivedcode == -1) 
				{
					return objRes;
				}
				Map resultMap = (Map) resultObj.getResultValue();						
				long srNo=(Long) resultMap.get("newID");*/
				
				long srNo = IDGenerateDelegate.getNextId("hr_eis_curiculr_dtl", objectArgs);
				
	    		/*End of Id Generate*/
				lVObject.setSrNo(srNo);	
				
				if (blnWorkFlowEnabled) 
				{
					lVObject.setOrgUserMstByUserId(orgUserMst);
				}
				else
				{
					lVObject.setOrgUserMstByUserId(selectedOrgUserMst);
				}

				/*For Attachment 
				objectArgs.put("rowNumber", String.valueOf(cnt));
				cnt++;
				objectArgs.put("attachmentName", "attachmentBiometric");
				objRes = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
				objectArgs = (Map<String, Object>) objRes.getResultValue();
				objRes = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
				objectArgs = (Map<String, Object>) objRes.getResultValue();                    
				if (objectArgs.get("AttachmentVO_attachmentBiometric") != null)
				{
					lVObject.setCmnAttachmentMst((CmnAttachmentMst) objectArgs.get("AttachmentVO_attachmentBiometric"));
				}
				End Of Attachment */
				
				lVObject.setCmnLocationMst(cmnLocationMst);
				lVObject.setCmnDatabaseMst(cmnDatabaseMst);
				lVObject.setOrgUserMstByCreatedBy(orgUserMst); 
				lVObject.setOrgPostMstByCreatedByPost(orgPostMst);
				lVObject.setCreatedDate(currDate);
				
				coCurrDAO.create(lVObject);
				j++;
			} 
			/*End of Insertion Code*/							
				 
			objectArgs.put("draft","A");//For message view for submit page
			
			if(!blnWorkFlowEnabled)//IFMS
			{
				objRes = GetPersonInfo(objectArgs, blnWorkFlowEnabled, iUserId); // IFMS
				objRes = getComboDtls(objectArgs);
			}
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			if(!blnWorkFlowEnabled)//IFMS
			{
				objRes.setViewName("WorkFlowDisableCoCurricularDtls");
			}
			else
			{
				objRes.setViewName("SubmitMessagePage");
			}
		}
		catch (Exception e) 
		{ 
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while submitting Co Curricular Dtls ",e);
		} 
		return objRes; 
	}
	public ResultObject GetPersonInfo(Map<String, Object> objectArgs, boolean blnWorkFlowEnabled, long iUserId) // IFMS
	{			
			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
			try
			 { 
				 if(objRes==null || objectArgs == null ) 
				 { 
					 objRes.setResultCode(-1); 
					 return objRes; 
				 }
				 HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
				 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				 Map loginMap = (Map) objectArgs.get("baseLoginMap");
				 long langId = Long.parseLong(loginMap.get("langId").toString());
				 
				 /*Get The Person Previous Info*/
				 /* Getting a Person Info*/
					long userID = Long.parseLong(loginMap.get("userId").toString());
					OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); 
					OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);	
		    	 /* End of getting Person  ID Info*/
					
					/* IFMS - starts */
					if (iUserId != 0)
					{	
						if (!blnWorkFlowEnabled)
						{
							objectArgs.put("EmpInfo_userId",iUserId);
							objectArgs.put("EmpInfo_PayFix",true);
						}
						objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
					}
		                	
					/*  Get The selected user Person's User Id*/
					OrgUserMst selectedOrgUserMst = null;
					if (!blnWorkFlowEnabled)
					{
						selectedOrgUserMst = orgUserMstDaoImpl.read(iUserId);
					}
					/*End of the geting Person ID Code*/
					/* IFMS - ends */
					
					
				 CoCurricularDAOImpl coCurrDAO = new CoCurricularDAOImpl(HrEisCuriculrDtl.class,serv.getSessionFactory());
				 
				 List coCurrLstObj = coCurrDAO.getEmpCoCurricularDetails(blnWorkFlowEnabled ? orgUserMst :selectedOrgUserMst);
				 
				/* Get The Multiple Vo Of Co Curricular Details  */
				request.getSession().removeAttribute("attachmentBiometricCocurricular_EDITVO");				 			      			     						    
				ListIterator itr= coCurrLstObj.listIterator();
				List<HrEisCuriculrDtl> CoCurrlstObj = new ArrayList<HrEisCuriculrDtl>();
				List<String> voToXmllstObj = new ArrayList<String>();
				CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
				
				CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				
				int iCounter = 0;
				
				while(itr.hasNext())
				{
					
					HrEisCuriculrDtl lObjCO =(HrEisCuriculrDtl)itr.next();	
					if(lObjCO!=null)
					{
						iCounter++;
						String strCocurricularIdLookupName = lObjCO.getCmnLookupMstByCocurricularId() != null ? lObjCO.getCmnLookupMstByCocurricularId().getLookupName() : "";
						if(!"".equals(strCocurricularIdLookupName))
						{
							lObjCO.setCmnLookupMstByCocurricularId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strCocurricularIdLookupName, langId));
						}
					
						String strCompetedAtIdIdLookupName = lObjCO.getCmnLookupMstByCompetedAtId() != null ? lObjCO.getCmnLookupMstByCompetedAtId().getLookupName() : "";
						if(!"".equals(strCompetedAtIdIdLookupName))
						{
							lObjCO.setCmnLookupMstByCompetedAtId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strCompetedAtIdIdLookupName, langId));
						}
					
						String strSubCocurricularIdLookupName = lObjCO.getCmnLookupMstBySubCocurricularId() != null ? lObjCO.getCmnLookupMstBySubCocurricularId().getLookupName() : "";
						if(!"".equals(strSubCocurricularIdLookupName))	
						{
							lObjCO.setCmnLookupMstBySubCocurricularId(cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strSubCocurricularIdLookupName, langId));
						}
						
						if(lObjCO.getCmnAttachmentMst()!=null)
						{
							CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(lObjCO.getCmnAttachmentMst().getAttachmentId());
							EisWFmessageDisplay.removeAttachmentFromSession(request.getSession(),"attachmentBiometricCocurricular",iCounter);
							request.getSession().setAttribute("attachmentBiometricCocurricular"+iCounter+"_EDITVO", cmnAttachmentMst);
							lObjCO.setCmnAttachmentMst(cmnAttachmentMst);
						}					
						String xmlFileIdStr = FileUtility.voToXmlFileByXStream(lObjCO);					
						voToXmllstObj.add(xmlFileIdStr);					
						StringBuffer temp=new StringBuffer();
						String specialAchievement =lObjCO.getSpecialAchievement();
						
						if (specialAchievement != null && !specialAchievement.equals(""))
						{
							for(int i=0;i<specialAchievement.length();i++)
							{
								Character c = specialAchievement.charAt(i);
								
								int asciiCode = Integer.valueOf(c).intValue();
								if(asciiCode==13){logger.info("10");}
								else if(asciiCode!=10)
								{
									temp.append(c);
								}
								else
								{							
									temp.append("&amp;#x0D;");
								}
							}
						}
						lObjCO.setSpecialAchievement(temp.toString());
						CoCurrlstObj.add(lObjCO);					
					}
				}
				/*End Of the Person Previous Info*/
				
				int l=CoCurrlstObj.size();
				if(l==0){l=1;}
				
				objectArgs.put("rowNumber",l+1);
				objectArgs.put("XmlFile",voToXmllstObj);
				objectArgs.put("CoCurricularDtls",CoCurrlstObj);
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);		        
				if(blnWorkFlowEnabled)
				{
					objRes = serv.executeService("SHOW_EMP_DETAIL", objectArgs); //For Employee Data
					objRes.setViewName("CoCurricular");
				}
				else
				{
						objRes.setViewName("WorkFlowDisableCoCurricularDtls");
				}
				
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while getting Co Curricular Employee Dtls ",e);
		}
		return objRes;
	 }	 
}	 