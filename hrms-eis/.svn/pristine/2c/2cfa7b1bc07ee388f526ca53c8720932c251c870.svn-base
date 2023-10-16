package com.tcs.sgv.eis.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisCuriculrDtl;

public class CoCurricularVOGEN extends ServiceImpl
{
	private static final Log logger = LogFactory.getLog(CoCurricularVOGEN.class);		
	public ResultObject coCurricularlVOGEN (Map<String, Object> objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			/** IFMS - Starts*/
			boolean blnWorkFlowEnabled = true;
			
			if (request.getParameter("workFlowEnabled") != null)
			{
				blnWorkFlowEnabled = Boolean.valueOf(StringUtility.getParameter("workFlowEnabled",request));
			}	
			
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;
			
			/** IFMS - Ends*/
			
			String lStrFlag =StringUtility.getParameter("flag",request);				
			objectArgs.put("flag", lStrFlag);					
			if("getSubTypeOfCoCurricular".equalsIgnoreCase(lStrFlag))
			{
				String cmbid = StringUtility.getParameter("cmbid",request);
				objectArgs.put("cmbid", cmbid);				
			}
			else if("AddCoCurricularlDtls".equalsIgnoreCase(lStrFlag))
			{
				String srno = StringUtility.getParameter("srno",request);				
				objectArgs.put("srno", srno);				
				resObj=setCoCurricularlVO(objectArgs);
			}
			else if("SubmitCoCurricularDtls".equalsIgnoreCase(lStrFlag)) 
			{							
				resObj=submitCoCurricularDtls(objectArgs); 
			} 			
			
			objectArgs.put("blnWorkFlowEnabled", blnWorkFlowEnabled);   /**IFMS*/
			objectArgs.put("userId", iUserId);  						/**IFMS*/
			
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			
		}
		catch(Exception e)
		{	
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setResultValue(objectArgs);
			logger.error("Exception while setting a flag for Co Curricular Service in VOGEN ",e);						
		}
		return resObj;
	}
	
	
	private ResultObject submitCoCurricularDtls(Map<String, Object> objectArgs) 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			String[] txnXML = StringUtility.getParameterValues("encXML", request);
			String updateRecordXMLFileA[] = StringUtility.getParameterValues("addedencXML",request);
			String [] enc_rowNumForAttachment = StringUtility.getParameterValues("encXML_rowNumber", request);
			String [] added_rowNumForAttachment = StringUtility.getParameterValues("addedencXML_rowNumber", request);			
			
			Map vOMap = FileUtility.getUpdatedDeletedVOListFromXML(updateRecordXMLFileA);			
			List updatedVOList = (List) vOMap.get("upDatedVOList");			
			List deletedVOList = (List) vOMap.get("deletedVOList");
			List notModifiedVOList = (List) vOMap.get("notModifiedVOList");			
			
			objectArgs.put("enc_rowNumForAttachment", enc_rowNumForAttachment);
			objectArgs.put("added_rowNumForAttachment", added_rowNumForAttachment);
			objectArgs.put("upDatedVOList", updatedVOList);
			objectArgs.put("deletedVOList", deletedVOList);
			objectArgs.put("notModifiedVOList", notModifiedVOList);
			objectArgs.put("encXML", txnXML);
			resObj.setResultValue(objectArgs);
		}
		catch(Exception e)
		{
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setResultValue(objectArgs);
			logger.error("Exception while Submitting a XML File Array in Co Curricular VOGEN",e);
		}
		return resObj;
	}


	private ResultObject setCoCurricularlVO (Map<String, Object> objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{		
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");				
			
			HrEisCuriculrDtl lObjCO = new HrEisCuriculrDtl();
			if(!"0".equals(StringUtility.getParameter("srno",request)))
			{
				lObjCO.setSrNo(StringUtility.convertToLong(StringUtility.getParameter("srno",request)));
			}
			
			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			cmnLookupMst.setLookupName(StringUtility.getParameter("CoCurricular",request));						
			lObjCO.setCmnLookupMstByCocurricularId(cmnLookupMst);			
			
			CmnLookupMst cmnLookupMst1 = new CmnLookupMst();
			cmnLookupMst1.setLookupName(StringUtility.getParameter("TypeOfCoCurricular",request));
			lObjCO.setCmnLookupMstBySubCocurricularId(cmnLookupMst1);
			
			
			lObjCO.setYearId(Long.valueOf(StringUtility.getParameter("year_combo",request).trim()));
						
			
			CmnLookupMst cmnLookupMst3 = new CmnLookupMst();
			cmnLookupMst3.setLookupName(StringUtility.getParameter("Competed_at",request));
			lObjCO.setCmnLookupMstByCompetedAtId(cmnLookupMst3);		
			
			lObjCO.setSpecialAchievement(StringUtility.getParameter("Specialachievement",request));					
/*			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			ResultObject resultObj = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
			objectArgs = (Map) resultObj.getResultValue();*/
			
			objectArgs.put("CoCurricularDtls", lObjCO);
			resObj.setResultValue(objectArgs);	
		}
		catch(Exception e)
		{
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setResultValue(objectArgs);
			logger.error("Exception while setting Co Curricular Vo in VOGEN ",e);
		}
		return resObj;
	}
}
