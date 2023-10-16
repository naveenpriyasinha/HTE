package com.tcs.sgv.hr.payfixation.service;

import java.util.Date;
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
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;

public class EmpPayfixationVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject getUserID(Map objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			long UserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)):0;
			logger.info("InSide VOGen UserID===="+UserId);
			objectArgs.put("userId", UserId);
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resObj;
	}
	
	public ResultObject generatePayFixtnMap(Map objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objServiceArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			try 
			{
				if (objRes == null || objServiceArgs == null)
				{
					objRes.setResultCode(-1);
					return objRes;
				}
				long lngPayFixtnId=0;
				if (request.getParameter("hdnPayFixationId") != null && !StringUtility.getParameter("hdnPayFixationId", request).equals(""))
				{
					lngPayFixtnId =  Long.parseLong(StringUtility.getParameter("hdnPayFixationId", request));
				}
				
				
				//'payFixationDate','selReasonPay','selNewPayScale','newBasicPay','NextIncrDate','remark');
				Date dtPayFixDt =  request.getParameter("payFixationDate") != null ? StringUtility.convertStringToDate(StringUtility.getParameter("payFixationDate", request)): null;
				logger.info("dtPayFixDt======="+dtPayFixDt);
				String strReasonPay = request.getParameter("selReasonPay") != null ? StringUtility.getParameter("selReasonPay", request) : "";
				logger.info("strReasonPay======="+strReasonPay);
				long lngNewPayScale = request.getParameter("selNewPayScale") != null ? Long.parseLong(StringUtility.getParameter("selNewPayScale", request)):0;
				logger.info("strNewPayScale======="+lngNewPayScale);
				long lngNewBasicPay = (request.getParameter("newBasicPay")!= null && !request.getParameter("newBasicPay").equals("")) ? Long.parseLong(StringUtility.getParameter("newBasicPay", request)) : 0 ;
				logger.info("lngNewBasicPay======="+lngNewBasicPay);
				Date dtNxtIncDt =  request.getParameter("NextIncrDate") != null ? StringUtility.convertStringToDate(StringUtility.getParameter("NextIncrDate", request)): null;
				logger.info("dtNxtIncDt======="+dtNxtIncDt);
				String strRemark = request.getParameter("remark") != null ? StringUtility.getParameter("remark", request) : "";
				logger.info("strRemark======="+strRemark);
				
				char chRecType=StringUtility.convertToChar((StringUtility.getParameter("recType", request)));
				logger.info("chRecType====="+chRecType);
				HrPayfixMst objHrPayfixMst = new HrPayfixMst();
				objHrPayfixMst.setPayfixId(lngPayFixtnId);
				
				CmnLookupMst cmnLookupMstByReasonPay=new CmnLookupMst();
				cmnLookupMstByReasonPay.setLookupName(strReasonPay);
				
				HrEisScaleMst hrEisScaleMst = new HrEisScaleMst();
				hrEisScaleMst.setScaleId(lngNewPayScale);
				
				/*if(dtPayFixDt!=null)
					objHrPayfixMst.setPayFixDate(dtPayFixDt);
				if(cmnLookupMstByReasonPay!=null)
					objHrPayfixMst.setCmnLookupMst(cmnLookupMstByReasonPay);
				if(hrEisScaleMst!=null)
					objHrPayfixMst.setRevPayScale(hrEisScaleMst);
				if(lngNewBasicPay!=0)
					objHrPayfixMst.setRevPay(lngNewBasicPay);
				if(dtNxtIncDt != null)
					objHrPayfixMst.setNxtIncrDate(dtNxtIncDt);
				if(!strRemark.equals(""))
					objHrPayfixMst.setRemarks(strRemark);
				if(chRecType=='C')
				{	
					objHrPayfixMst.setFlagType('Y');
					logger.info("value inside if");
				}
				else
				{	
					objHrPayfixMst.setFlagType('N');
					logger.info("value inside else");
				}*/
				objServiceArgs.put("objHrPayfixMst", objHrPayfixMst);
				
				/*String xmlFileId = FileUtility.voToXmlFileByXStream(objHrPayfixMst);
				logger.info("=============== xmlFileId=========="+xmlFileId);
				objServiceArgs.put("ajaxKey", xmlFileId);
				objRes.setViewName("ajaxData");*/
				
				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setResultValue(objServiceArgs);
			
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objRes;
	}
	
	public ResultObject SaveInDBEmpPayFixtnDtlsVO(Map objServiceArgs)
	 {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			
			long iUserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)) : 0;
			logger.info("iUserId====="+iUserId);
			
			String[] txnPayFixnXML = StringUtility.getParameterValues("encXMLpay", request);
			List<HrPayfixMst> lstHrPayfixMstDtlsVO=FileUtility.xmlFilesToVoByXStream(txnPayFixnXML);
			
			String updatePayFixnRecordXMLFile[] = StringUtility.getParameterValues("encXMLpayFixtn", request);
			Map vOMapPayFixn = FileUtility.getUpdatedDeletedVOListFromXML(updatePayFixnRecordXMLFile);
			
			List updatedPayFixnVOList = (List) vOMapPayFixn.get("upDatedVOList");
			List deletedPayFixnVOList = (List) vOMapPayFixn.get("deletedVOList");
			List notModifiedPayFixnVOList = (List) vOMapPayFixn.get("notModifiedVOList");
			
			
			
			logger.info("=============lstHrPayfixMstDtlsVO in vogen======================"+lstHrPayfixMstDtlsVO.size());
			logger.info("=============updatedPayFixnVOList in vogen======================"+updatedPayFixnVOList.size());
			logger.info("=============deletedPayFixnVOList in vogen======================"+deletedPayFixnVOList.size());
			logger.info("=============notModifiedPayFixnVOList in vogen======================"+notModifiedPayFixnVOList.size());
			
			objServiceArgs.put("lstHrPayfixMstDtlsVO", lstHrPayfixMstDtlsVO);
			objServiceArgs.put("updatedPayFixnVOList", updatedPayFixnVOList);
			objServiceArgs.put("deletedPayFixnVOList", deletedPayFixnVOList);
			objServiceArgs.put("notModifiedPayFixnVOList", notModifiedPayFixnVOList);
			objServiceArgs.put("userId", iUserId);
			
			objRes.setResultValue(objServiceArgs);
			
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objRes;
	 }
	
	
	public ResultObject showPayFixationOptnVOGen(Map objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			long UserId = (request.getParameter("UserId") != null && !request.getParameter("UserId").equals("")) ? Long.parseLong(StringUtility.getParameter("UserId",request)):0;
			logger.info("InSide VOGen UserId===="+UserId);
			
			long NewPayScale = (request.getParameter("NewPayScale") != null && !request.getParameter("NewPayScale").equals("")) ? Long.parseLong(StringUtility.getParameter("NewPayScale",request).toString()):0;
			logger.info("InSide VOGen NewPayScale===="+NewPayScale);
			
			String PayFixDate =(request.getParameter("PayFixDate") != null && !request.getParameter("PayFixDate").equals("")) ?(StringUtility.getParameter("PayFixDate",request)):"";
			logger.info("InSide VOGen PayFixDate===="+PayFixDate);
			
			objectArgs.put("lngUserId", UserId);
			objectArgs.put("strNewPayScale", NewPayScale);
			objectArgs.put("strNewPayFixDate", PayFixDate);
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resObj;
	}
}
