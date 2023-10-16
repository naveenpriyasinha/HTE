package com.tcs.sgv.eis.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisQualDtl;
import com.tcs.sgv.eis.valueobject.HrEisQualDtlTxn;

public class EmpEducationVOGEN extends ServiceImpl 
{
	private static final Log logger = LogFactory.getLog(EmpEducationVOGEN.class);		
	public ResultObject empEducationVOGEN (Map<String, Object> objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);		
		try
		{
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			/**IFMS - Starts*/
			boolean blnWorkFlowEnabled = true;
			
			if (request.getParameter("workFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
			}
			
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;
			logger.info("iUserId====================="+iUserId);
			/**IFMS - Ends*/
			
			String lStrFlag =StringUtility.getParameter("flag",request);				
			objectArgs.put("flag", lStrFlag);
			if("getSubQualification".equalsIgnoreCase(lStrFlag))
			{
				String cmbid = StringUtility.getParameter("cmbid",request);
				objectArgs.put("cmbid", cmbid);				
			}
			else if("getDiscipline".equalsIgnoreCase(lStrFlag))
			{
				String cmbid=StringUtility.getParameter("cmbid",request);
				objectArgs.put("cmbid", cmbid);
			}
			else if("AddOrEditEducationDtls".equalsIgnoreCase(lStrFlag))
			{					
				resObj=addOrEditQuaDtls(objectArgs, blnWorkFlowEnabled); 				/** IFMS*/
			}
			else if("SubmitEducationDtls".equalsIgnoreCase(lStrFlag)) 
			{						
				resObj=submitQualificationDtls(objectArgs , blnWorkFlowEnabled);		/** IFMS*/
			}
			else if("deleteEmpEducationDtls".equals(lStrFlag))
			{
				String deleteId_srNo =StringUtility.getParameter("delid",request);				
				objectArgs.put("deleteId", deleteId_srNo);
			}
			else if("getDraftData".equals(lStrFlag))
			{				
				String reqId = StringUtility.getParameter("reqId",request);
				objectArgs.put("reqId", reqId);
			}
			else if("deleteDraftData".equals(lStrFlag))
			{
				String reqId = StringUtility.getParameter("reqId",request);
				objectArgs.put("reqId", reqId);			
			}
			else if("EducationSaveAsDraft".equals(lStrFlag))
			{
				resObj=saveDraftDataintoVO(objectArgs);
			}
			else if("getPendingRecord".equals(lStrFlag))
			{
				String reqId =StringUtility.getParameter("reqId",request);				
				objectArgs.put("reqId", reqId);				
			}
			else if("AddDraftDtlsinTable".equals(lStrFlag))
			{
				String reqId =StringUtility.getParameter("reqId",request);										
				objectArgs.put("reqId", reqId);				
			}
			
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); /** IFMS*/
			objectArgs.put("userId", iUserId); /** IFMS*/
			
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{	
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setResultValue(objectArgs);
			logger.error("Exception while setting a flag for Emp Education Service in VOGEN ",e);						
		}
		return resObj;
	}
	private ResultObject saveDraftDataintoVO(Map<String, Object> objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");			
			HrEisQualDtlTxn lObjQual=new HrEisQualDtlTxn();			
			String QualificationStr=StringUtility.getParameter("Qualification",request);
			String SubQualificationStr=StringUtility.getParameter("SubQualification",request);
			String year = StringUtility.getParameter("yrpassing",request);
			String cmnQuaId="Select",yearPassId="Select";
			String cmnSubQuaId="Select",unitObj="Select";
			if(QualificationStr.equalsIgnoreCase("Select")) {logger.info("==in==");}
			else if(QualificationStr.equalsIgnoreCase("")) {logger.info("==in==");}
			else {cmnQuaId=StringUtility.getParameter("Qualification",request);}
			
			if(SubQualificationStr.equalsIgnoreCase("Select")) {logger.info("==in==");}
			else if(SubQualificationStr.equalsIgnoreCase("")) {logger.info("==in==");}
			else {cmnSubQuaId=StringUtility.getParameter("SubQualification",request);}								
			
			CmnLookupMst cmnLookupQuaId = new CmnLookupMst();
			cmnLookupQuaId.setLookupName(cmnQuaId);
			CmnLookupMst cmnLookupSubQuaId = new CmnLookupMst();
			cmnLookupSubQuaId.setLookupName(cmnSubQuaId);			
			lObjQual.setCmnLookupMstByQualificationId(cmnLookupQuaId);
			lObjQual.setCmnLookupMstBySubQualificationId(cmnLookupSubQuaId);
			
			String decipline = StringUtility.getParameter("decipline",request);
			if(decipline.equalsIgnoreCase("Select")) {logger.info("==in==");}
			else if(decipline.equalsIgnoreCase("")) {decipline="Select";}
			CmnLookupMst cmnLookupdeciplineId = new CmnLookupMst();
			cmnLookupdeciplineId.setLookupName(decipline);
			lObjQual.setCmnLookupMstByDicipline(cmnLookupdeciplineId);
			
			String courseCategory= StringUtility.getParameter("courseCategory",request);
			if(courseCategory.equalsIgnoreCase("Select")) {logger.info("==in==");}
			else if(courseCategory.equalsIgnoreCase("")) {courseCategory="Select";}			
			CmnLookupMst cmnLookupcourseCategoryId = new CmnLookupMst();
			cmnLookupcourseCategoryId.setLookupName(courseCategory);
			lObjQual.setCmnLookupMstByCourseCategory(cmnLookupcourseCategoryId);
			
			lObjQual.setUniInstituteBoard(StringUtility.getParameter("UniBoard",request));
			
			if(year.equalsIgnoreCase("Select")) {logger.info("==in==");}
			else if(year.equalsIgnoreCase("")) {logger.info("==in==");}
			else {yearPassId=year;}
			logger.info("year PAss ID " + yearPassId);
			lObjQual.setYearOfPassing(Long.valueOf(yearPassId));			
			
			String unitStrObj = StringUtility.getParameter("unit",request);		
			if(unitStrObj.equalsIgnoreCase("Select")) {logger.info("==in==");}
			else if(unitStrObj.equalsIgnoreCase("")) {logger.info("==in==");}
			else {unitObj=unitStrObj;}			
			CmnLookupMst cmnLookupunitId = new CmnLookupMst();			
			cmnLookupunitId.setLookupName(unitObj);						
			lObjQual.setCmnLookupMstByUnitsOfMarks(cmnLookupunitId);			
			
			lObjQual.setMarksScored(StringUtility.getParameter("Marks",request));						
			
			lObjQual.setActionFlag("P");			
			
			objectArgs.put("QualificationDtls", lObjQual);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);			
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while saving a data as Draft in VOGEN ",e);
		}
		return objRes;
	}
	private ResultObject addOrEditQuaDtls(Map<String, Object> objectArgs, boolean blnWorkFlowEnabled) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{		
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			
			HrEisQualDtlTxn lObjQual =  null;
			HrEisQualDtl lObjQualMst =  null;
			
			if (blnWorkFlowEnabled)
			{
				lObjQual = new HrEisQualDtlTxn();
			}
			else
			{
				lObjQualMst =new HrEisQualDtl();
			}
			
			String lStrValidatestr="";
			String cmnQuaId="Select",yearPassId="Select";
			String cmnSubQuaId="Select",unitObj="Select";
			cmnQuaId=StringUtility.getParameter("Qualification",request);
			cmnSubQuaId=StringUtility.getParameter("SubQualification",request);										
			String uniBoard=StringUtility.getParameter("UniBoard",request);	
			String marks=StringUtility.getParameter("Marks",request);
			
			String sendAttachmentId = StringUtility.getParameter("sendAttachmentId", request);	
			
			if(sendAttachmentId.trim().equals("")==false && sendAttachmentId.trim().equalsIgnoreCase("0")==false && sendAttachmentId.trim()!=null && sendAttachmentId.trim().equalsIgnoreCase("null")==false)
			{
				long lomgObjattchmentId = StringUtility.convertToLong(sendAttachmentId.trim());
				if(lomgObjattchmentId!=0)
				{				
					CmnAttachmentMst cmnAttachmentMst = new CmnAttachmentMst();
					cmnAttachmentMst.setAttachmentId(lomgObjattchmentId);
					
					if(blnWorkFlowEnabled)
					{
						lObjQual.setCmnAttachmentMst(cmnAttachmentMst);            /** IFMS*/
					}
					else
					{
						lObjQualMst.setCmnAttachmentMst(cmnAttachmentMst);
					}
				}
			}
			CmnLookupMst cmnLookupQuaId = new CmnLookupMst();
			cmnLookupQuaId.setLookupName(cmnQuaId);
			CmnLookupMst cmnLookupSubQuaId = new CmnLookupMst();
			cmnLookupSubQuaId.setLookupName(cmnSubQuaId);
			
			yearPassId = StringUtility.getParameter("yrpassing",request);
			
			
			String decipline = StringUtility.getParameter("discipline",request);			
			String courseCategory= StringUtility.getParameter("courseCategory",request);
			
			CmnLookupMst cmnLookupdeciplineId = new CmnLookupMst();
			cmnLookupdeciplineId.setLookupName(decipline);
			
			CmnLookupMst cmnLookupcourseCategoryId = new CmnLookupMst();
			cmnLookupcourseCategoryId.setLookupName(courseCategory);
			
			String unitStrObj = StringUtility.getParameter("unit",request);			
			unitObj=(unitStrObj);
			CmnLookupMst cmnLookupunitId = new CmnLookupMst();
			cmnLookupunitId.setLookupName(unitObj);			
			
			String str= StringUtility.getParameter("srno",request);
			
			/** IFMS*/
			long srNo = StringUtility.convertToLong((StringUtility.getParameter("srno",request)));

			long Rno= StringUtility.convertToLong(StringUtility.getParameter("rowNumber",request));
			CmnAttachmentMst attachmentMst =new CmnAttachmentMst();
			attachmentMst.setAttachmentId(Rno);
				
			if (blnWorkFlowEnabled) 													/** IFMS*/
			{
				lObjQual.setCmnLookupMstByQualificationId(cmnLookupQuaId);
				lObjQual.setCmnLookupMstBySubQualificationId(cmnLookupSubQuaId);
				lObjQual.setCmnLookupMstByDicipline(cmnLookupdeciplineId);
				lObjQual.setUniInstituteBoard(uniBoard);	
				
				if(!yearPassId.equalsIgnoreCase("Select"))
				{
					lObjQual.setYearOfPassing(Long.valueOf(yearPassId));
				}
				
				lObjQual.setCmnLookupMstByCourseCategory(cmnLookupcourseCategoryId);
				lObjQual.setCmnLookupMstByUnitsOfMarks(cmnLookupunitId);
				lObjQual.setMarksScored(marks);
				lObjQual.setActionFlag("P");
			}
			else
			{
				lObjQualMst.setSrNo(srNo);
				lObjQualMst.setCmnLookupMstByQualificationId(cmnLookupQuaId);
				lObjQualMst.setCmnLookupMstBySubQualificationId(cmnLookupSubQuaId);
				lObjQualMst.setCmnLookupMstByDicipline(cmnLookupdeciplineId);
				lObjQualMst.setUniInstituteBoard(uniBoard);

				if(!yearPassId.equalsIgnoreCase("Select"))
				{
					lObjQualMst.setYearOfPassing(Long.valueOf(yearPassId));
				}

				lObjQualMst.setCmnLookupMstByCourseCategory(cmnLookupcourseCategoryId);
				lObjQualMst.setCmnLookupMstByUnitsOfMarks(cmnLookupunitId);
				lObjQualMst.setMarksScored(marks);
				String xmlFileIdStr = FileUtility.voToXmlFileByXStream(lObjQualMst);

				if(!"".equals(lStrValidatestr))
				{
					xmlFileIdStr=xmlFileIdStr+"$/$"+lStrValidatestr;
				}
				objectArgs.put("ajaxKey",xmlFileIdStr);
				logger.info("Education xmlFileIdStrMst for lObjQualMst in addOrEditQuaDtls VoGen=================="+xmlFileIdStr);//End IFMS
				logger.info("Value of blnWorkFlowEnabled in addOrEditQuaDtls VoGen At End=============="+blnWorkFlowEnabled);
			}
			
			if(cmnQuaId==null ||  cmnQuaId.trim().equalsIgnoreCase("Select") || cmnQuaId.trim().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.qualification,"; }
			if(cmnSubQuaId==null || cmnSubQuaId.trim().equalsIgnoreCase("Select") || cmnSubQuaId.trim().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.subqualification,"; }
								    		    		    	
	    	if(cmnQuaId.trim().equals("edu_Higher_Secondary_School")==false  && cmnQuaId.trim().equals("edu_Secondary_School")==false && cmnQuaId.trim().equals("edu_Primary_School")==false)
	   		{
	    		if(courseCategory == null|| courseCategory.equalsIgnoreCase("Select") || courseCategory.trim().equalsIgnoreCase(""))
	    		{lStrValidatestr=lStrValidatestr+"eis.courseCategory,";}  		
	    	}
	    	else
	    	{
	    		courseCategory="Select";
	    		decipline="Select";
	    	}
	    	if(uniBoard == null || uniBoard.trim().equalsIgnoreCase("")){ lStrValidatestr=lStrValidatestr+"eis.uni_ins_board,"; }
	    	if(yearPassId==null || yearPassId.equalsIgnoreCase("Select") || yearPassId.trim().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.year_of_pass,"; }
	    	if(unitStrObj==null || unitStrObj.equalsIgnoreCase("Select") || unitStrObj.trim().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.unit,"; }
	    	if(marks==null || marks.equalsIgnoreCase("Select") || marks.trim().equalsIgnoreCase("")){lStrValidatestr=lStrValidatestr+"eis.marks,"; }

	    	if (blnWorkFlowEnabled)									 /** IFMS*/
			{
		    	if("0".equals(str) && lStrValidatestr.equalsIgnoreCase("")==true)
				{
					lObjQual.setRequestFlag("A");
				}
				else if (lStrValidatestr.equalsIgnoreCase("")==true)
				{				
					lObjQual.setRequestFlag("U");
					lObjQual.setRowNumber(StringUtility.convertToLong(str));
				}
				else if(!lStrValidatestr.equalsIgnoreCase("") && !"0".equals(str))
				{
					lObjQual.setRequestFlag("N");
					lObjQual.setRowNumber(StringUtility.convertToLong(str));
				}
		    	
		    	String xmlFileIdStr = FileUtility.voToXmlFileByXStream(lObjQual);
		    	
		    	if(!"".equals(lStrValidatestr))
		    	{
					xmlFileIdStr=xmlFileIdStr+"$/$"+lStrValidatestr;
		    	}
					objectArgs.put("ajaxKey",xmlFileIdStr);
					logger.info("Education xmlFileIdStr for lObjQual in  addOrEditQuaDtls VoGen (In Workflow)=================="+xmlFileIdStr);//End IFMS
			}
						    
			objectArgs.put("lStrValidatestr", lStrValidatestr);
			objectArgs.put("QualificationDtls", lObjQual);
			
			objectArgs.put("QualificationMstDtls", lObjQualMst);
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); /** IFMS*/
			
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while setting VO for Emp Education Service in VOGEN",e);
		}
		return objRes;
	}
	private ResultObject submitQualificationDtls(Map<String, Object> objectArgs, boolean blnWorkFlowEnabled) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");			
			String[] txnXML = StringUtility.getParameterValues("encXML", request);
			String [] enc_rowNumForAttachment = StringUtility.getParameterValues("encXML_rowNumber", request);
			/**IFMS*/
			if(blnWorkFlowEnabled)
			{
				logger.info("================For Work-Flow is Enable=========");
				String deleteEduDtls = StringUtility.getParameter("deleteEduDtls", request);
				objectArgs.put("enc_rowNumForAttachment", enc_rowNumForAttachment);
				objectArgs.put("deleteEduDtls", deleteEduDtls);		
			}
			else
			{
				String updateRecordXMLFileA[] = StringUtility.getParameterValues("addedPunch", request);
				String [] addedPunch_rowNumForAttachment = StringUtility.getParameterValues("addedPunch_rowNumber", request);
				Map vOMap = FileUtility.getUpdatedDeletedVOListFromXML(updateRecordXMLFileA, addedPunch_rowNumForAttachment);
				
				
				logger.info("================For Work-Flow is Enable enc_rowNumForAttachment========="+ enc_rowNumForAttachment.length);
				
				List updatedVOList = (List) vOMap.get("upDatedVOList");
				List deletedVOList = (List) vOMap.get("deletedVOList");
				List notModifiedVOList = (List) vOMap.get("notModifiedVOList");
				
				objectArgs.put("upDatedVOList", updatedVOList);
				objectArgs.put("deletedVOList", deletedVOList);
				objectArgs.put("notModifiedVOList", notModifiedVOList);
				
				objectArgs.put("enc_rowNumForAttachment", enc_rowNumForAttachment);
				objectArgs.put("addedPunch_rowNumForAttachment", addedPunch_rowNumForAttachment);
			}
				
			objectArgs.put("encXML", txnXML);
			
			objectArgs.put("draft", StringUtility.getParameter("draft", request));			
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled); /** IFMS*/
		}
		catch(Exception e)
		{
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setResultValue(objectArgs);
			logger.error("Exception while getting a XML File Array in VOGEN ",e);
		}
		return resObj;
	}
}
