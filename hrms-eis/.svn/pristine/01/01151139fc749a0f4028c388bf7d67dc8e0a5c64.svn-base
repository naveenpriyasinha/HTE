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
import com.tcs.sgv.eis.valueobject.HrEisSrvcexamDtl;

public class EmpServiceExaminationDtlsVOGENImpl extends ServiceImpl
{
	private static final Log logger = LogFactory.getLog(EmpServiceExaminationDtlsVOGENImpl.class);
	
	public ResultObject generateSrvcExamMap(Map objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			try 
			{
				long lngExamId=0;
				
				if (request.getParameter("hdnExamId") != null && !StringUtility.getParameter("hdnExamId", request).equals(""))
				{
					lngExamId =  Long.parseLong(StringUtility.getParameter("hdnExamId", request));
				}
				HrEisSrvcexamDtl objHrEisEmpSrvcexamDtls=new HrEisSrvcexamDtl();
				objHrEisEmpSrvcexamDtls.setEmpSrvcexamDtlsId(lngExamId);
				
				String strExmId = request.getParameter("drodnQualifngExm") != null ? StringUtility.getParameter("drodnQualifngExm", request) : "";
				String strPreSrvcId=request.getParameter("drodnPreServc") != null ? StringUtility.getParameter("drodnPreServc", request) : "";
				String strMnthOfPass =request.getParameter("drodnMonthOfPass") != null ? StringUtility.getParameter("drodnMonthOfPass", request): "0";
				long lngYearOfPass = (request.getParameter("drodnYearFPass")!= null && !request.getParameter("drodnYearFPass").equals("")) ? Long.parseLong(StringUtility.getParameter("drodnYearFPass", request)) : 0 ;
				double dblMarksObt = (request.getParameter("txtMarksObt") != null && !request.getParameter("txtMarksObt").equals(""))? Double.parseDouble(StringUtility.getParameter("txtMarksObt", request)): 0;
				double dblOutOf = (request.getParameter("txtMarksOutOf") != null && !request.getParameter("txtMarksOutOf").equals(""))? Double.parseDouble(StringUtility.getParameter("txtMarksOutOf", request)) : 0;
				String strClassDiv = request.getParameter("drodnClasDivn") != null ? StringUtility.getParameter("drodnClasDivn", request) : "0";
				String strResult=request.getParameter("drodnResult") != null ? StringUtility.getParameter("drodnResult", request) : "";
				
				CmnLookupMst cmnLookupMstByExamLookupId=new CmnLookupMst();
				cmnLookupMstByExamLookupId.setLookupName(strExmId);
				
				CmnLookupMst cmnLookupMstByPreserviceLookupId=new CmnLookupMst();
				cmnLookupMstByPreserviceLookupId.setLookupName(strPreSrvcId);
				
				CmnLookupMst cmnLookupMstByPassingMonthLookupId = new CmnLookupMst();
				cmnLookupMstByPassingMonthLookupId.setLookupName(strMnthOfPass);
				
				CmnLookupMst cmnLookupMstByClassDivLookupId=new CmnLookupMst();
				cmnLookupMstByClassDivLookupId.setLookupName(strClassDiv);
				
				CmnLookupMst cmnLookupMstByResult = new CmnLookupMst();
				cmnLookupMstByResult.setLookupName(strResult);
				
				objHrEisEmpSrvcexamDtls.setCmnLookupMstByExamLookupId(cmnLookupMstByExamLookupId);
				objHrEisEmpSrvcexamDtls.setCmnLookupMstByPreserviceLookupId(cmnLookupMstByPreserviceLookupId);
				objHrEisEmpSrvcexamDtls.setCmnLookupMstByPassingMonthLookupId(cmnLookupMstByPassingMonthLookupId);
				objHrEisEmpSrvcexamDtls.setPassingYear(lngYearOfPass);
				objHrEisEmpSrvcexamDtls.setMarksObtainted(dblMarksObt);
				objHrEisEmpSrvcexamDtls.setMarksOutOf(dblOutOf);
				objHrEisEmpSrvcexamDtls.setCmnLookupMstByClassDivLookupId(cmnLookupMstByClassDivLookupId);
				objHrEisEmpSrvcexamDtls.setCmnLookupMstByResultLookupId(cmnLookupMstByResult);
				
				String xmlFileId = FileUtility.voToXmlFileByXStream(objHrEisEmpSrvcexamDtls);
				
				
				logger.info("=============== xmlFileId=========="+xmlFileId);
				
				objServiceArgs.put("ajaxKey", xmlFileId);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(objServiceArgs);
			
			} 
			catch (Exception e) 
			{
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setResultValue(objServiceArgs);
				logger.error("Exception while Calling generateSrvcExamMap method in EmpServiceExaminationDtlsVOGENImpl Service",e);
			}
		}
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while Calling generateSrvcExamMap method in EmpServiceExaminationDtlsVOGENImpl Service",e);
		}
		return objRes;
	}
	
	 public ResultObject SaveInDBEmpServcExamDtlsVO(Map objServiceArgs)
	 {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;
			
			String[] txnSrvcExamXML = StringUtility.getParameterValues("encXMLExm", request);
			List<HrEisSrvcexamDtl> lstHrEisEmpSrvcexamDtlsVO=FileUtility.xmlFilesToVoByXStream(txnSrvcExamXML);
			
			String updateExamRecordXMLFileA[] = StringUtility.getParameterValues("encXMLSrvcExam", request);
			Map vOMapExam = FileUtility.getUpdatedDeletedVOListFromXML(updateExamRecordXMLFileA);
			
			List updatedExamVOList = (List) vOMapExam.get("upDatedVOList");
			List deletedExamVOList = (List) vOMapExam.get("deletedVOList");
			List notModifiedExamVOList = (List) vOMapExam.get("notModifiedVOList");
			
			objServiceArgs.put("updatedExamVOList", updatedExamVOList);
			objServiceArgs.put("deletedExamVOList", deletedExamVOList);
			objServiceArgs.put("notModifiedExamVOList", notModifiedExamVOList);
			objServiceArgs.put("userId", iUserId);
			
			logger.info("=============updatedExamVOList======================"+updatedExamVOList.size());
			logger.info("=============deletedExamVOList======================"+deletedExamVOList.size());
			logger.info("=============updatedExamVOList======================"+notModifiedExamVOList.size());
			
			
			objServiceArgs.put("lstHrEisEmpSrvcexamDtlsVO", lstHrEisEmpSrvcexamDtlsVO);
			objRes.setResultValue(objServiceArgs);
			
		}
		
		catch (Exception e) 
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objServiceArgs);
			logger.error("Exception while Calling SaveInDBEmpServcExamDtlsVO method in EmpServiceExaminationDtlsVOGENImpl Service",e);
		}
		return objRes;
	 }
		
}
