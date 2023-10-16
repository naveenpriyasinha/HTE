package com.tcs.sgv.hr.payincrement.service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;

public class EmpPayIncVOGen extends ServiceImpl{
	Log logger = LogFactory.getLog(getClass());
	public ResultObject getMonthAndYearVoGen(Map objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			int intMonth= request.getParameter("selMonth") != null ? Integer.parseInt(StringUtility.getParameter("selMonth", request)) : -1;
			int intYear= request.getParameter("selYear") != null ? Integer.parseInt(StringUtility.getParameter("selYear", request)) : 0;
			
			logger.info("selMonth===="+intMonth);
			objectArgs.put("selMonth", intMonth);
			objectArgs.put("selYear", intYear);
			
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resObj;
	}
	
	public ResultObject showEmpNextPayIncrVOGen(Map objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			String UserId = (request.getParameter("UserId") != null && !request.getParameter("UserId").equals("")) ? StringUtility.getParameter("UserId",request):"";
			logger.info("InSide VOGen UserId===="+UserId);
			
			String PayFixId = (request.getParameter("PayFixId") != null && !request.getParameter("PayFixId").equals("")) ? StringUtility.getParameter("PayFixId",request):"";
			logger.info("InSide VOGen PayFixId===="+PayFixId);
			
			String lwp = (request.getParameter("lwp") != null && !request.getParameter("lwp").equals("")) ? StringUtility.getParameter("lwp",request):"";
			logger.info("InSide VOGen PayFixId===="+lwp);
			
			String duedate = (request.getParameter("duedate") != null && !request.getParameter("duedate").equals("")) ? StringUtility.getParameter("duedate",request):"";
			logger.info("InSide VOGen PayFixId===="+duedate);
			
			String effDate = (request.getParameter("effDate") != null && !request.getParameter("effDate").equals("")) ? StringUtility.getParameter("effDate",request):"";
			logger.info("InSide VOGen PayFixId===="+effDate);
			
			objectArgs.put("UserId", UserId);
			objectArgs.put("PayFixId", PayFixId);
			objectArgs.put("lwp", lwp);
			objectArgs.put("duedate", duedate);
			objectArgs.put("effDate", effDate);
			
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resObj;
	}
	
	public ResultObject generatePayIncrXmlVOGen(Map objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			long lngPayIncrId=0;
			if (request.getParameter("hdnPayIncrId") != null && !StringUtility.getParameter("hdnPayIncrId", request).equals(""))
			{
				lngPayIncrId =  Long.parseLong(StringUtility.getParameter("hdnPayIncrId", request));
			}
			
			char defType = StringUtility.convertToChar((StringUtility.getParameter("defType", request)));
			long lngNewBasicPay = (request.getParameter("newBasicPay")!= null && !request.getParameter("newBasicPay").equals("")) ? Long.parseLong(StringUtility.getParameter("newBasicPay", request)) : 0 ;
			logger.info("lngNewBasicPay======="+lngNewBasicPay);
			Date dtNxtIncDt =  request.getParameter("NextIncrDate") != null ? StringUtility.convertStringToDate(StringUtility.getParameter("NextIncrDate", request)): null;
			logger.info("dtNxtIncDt======="+dtNxtIncDt);
			String strRemark = request.getParameter("remark") != null ? StringUtility.getParameter("remark", request) : "";
			logger.info("strRemark======="+strRemark);
			
			long selectedUserId=0;
			if (request.getParameter("hdnUserId") != null && !StringUtility.getParameter("hdnUserId", request).equals(""))
			{
				selectedUserId =  Long.parseLong(StringUtility.getParameter("hdnUserId", request));
			}
			HrPayfixMst hrPayfixMst = new HrPayfixMst();
			
			/*hrPayfixMst.setRevPay(lngNewBasicPay);
			hrPayfixMst.setDiffinedType(defType);*/
			hrPayfixMst.setNxtIncrDate(dtNxtIncDt);
			hrPayfixMst.setRemarks(strRemark);
			hrPayfixMst.setPayfixId(lngPayIncrId);
			
			String xmlFileId = FileUtility.voToXmlFileByXStream(hrPayfixMst);
			logger.info("=============== xmlFileId=========="+xmlFileId);
			
			objectArgs.put("ajaxKey", xmlFileId);
			
			resObj.setViewName("ajaxData");
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resObj;
	}
	
	public ResultObject SaveInDBEmpPayIncrDtlsVO(Map objServiceArgs)
	 {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			
			long UserId = (request.getParameter("hdnUserId") != null && !request.getParameter("hdnUserId").equals("")) ? Long.parseLong(StringUtility.getParameter("hdnUserId",request)):0;
			logger.info("UserId====="+UserId);
			
			String[] txnPayIncrXML = StringUtility.getParameterValues("encXMLpay", request);
			List<HrPayfixMst> addedPayIncrVOList=FileUtility.xmlFilesToVoByXStream(txnPayIncrXML);
			
			String updatePayIncrRecordXMLFile[] = StringUtility.getParameterValues("encXMLpayIncr", request);
			Map vOMapPayIncr = FileUtility.getUpdatedDeletedVOListFromXML(updatePayIncrRecordXMLFile);
			
			List updatedPayIncrVOList = (List) vOMapPayIncr.get("upDatedVOList");
			List deletedPayIncrVOList = (List) vOMapPayIncr.get("deletedVOList");
			List notModifiedPayIncrVOList = (List) vOMapPayIncr.get("notModifiedVOList");
			
			
			
			logger.info("=============lstHrPayfixMstDtlsVO in vogen======================"+addedPayIncrVOList.size());
			logger.info("=============updatedPayFixnVOList in vogen======================"+updatedPayIncrVOList.size());
			logger.info("=============deletedPayFixnVOList in vogen======================"+deletedPayIncrVOList.size());
			logger.info("=============notModifiedPayFixnVOList in vogen======================"+notModifiedPayIncrVOList.size());
			
			objServiceArgs.put("addedPayIncrVOList", addedPayIncrVOList);
			objServiceArgs.put("updatedPayIncrVOList", updatedPayIncrVOList);
			objServiceArgs.put("deletedPayIncrVOList", deletedPayIncrVOList);
			objServiceArgs.put("notModifiedPayIncrVOList", notModifiedPayIncrVOList);
			objServiceArgs.put("userId", UserId);			
			objRes.setResultValue(objServiceArgs);			
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objRes;
	 }
	
	public ResultObject generateEmpIncrementMap(Map objServiceArgs)
	 {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			StringTokenizer strToken;// = new StringTokenizer(" ");
			String index="";
			String indeaxval[];
			long billId = 0;
			String monthStr = null;
			String yearStr = null;
			String incrtype=null;
			List index1=new ArrayList();
    			int empIncrCntr = Integer.parseInt(request.getParameter("counter").toString());
    			if(StringUtility.getParameter("billId",request)!= null && !StringUtility.getParameter("billId",request).equals(""))
    			{
    				billId = Long.parseLong(StringUtility.getParameter("billId",request));
    			}
    			
    			monthStr = StringUtility.getParameter("month",request)!= null && !StringUtility.getParameter("month",request).equals("")?StringUtility.getParameter("month",request):" ";
    			yearStr =  StringUtility.getParameter("year",request)!= null && !StringUtility.getParameter("year",request).equals("")?StringUtility.getParameter("year",request):" ";
    			//Added By Jigna Raval on 2-7-2009
    			incrtype=StringUtility.getParameter("selIncrtype",request)!= null && !StringUtility.getParameter("selIncrtype",request).equals("")?StringUtility.getParameter("year",request):" ";
    			
    			index=(request.getParameter("index").toString());
    			indeaxval=index.split(",");
    		
			String strIncrTypeFlag=request.getParameter("selIncrtype").toString();
			List lstPayfixId = new ArrayList();
			List lstEmpIncrStatus = new ArrayList();
			List lstIncrAmount = new ArrayList();
			//Added by Jigna Raval
			List lstCurrentPay=new ArrayList();
			List lstCurrentPayScale=new ArrayList();
			List lstUserId=new ArrayList();
			
			//End by Jigna Raval
			logger.info("=========empIncrCntr=====in vogen==>"+indeaxval.length);
			for(int i=0;i<indeaxval.length;i++)
			{
			if(indeaxval[i]!=null && !((indeaxval[i].toString()).equals(" ")) && !((indeaxval[i].toString()).equals("")) && (indeaxval[i].toString())!=" " && (indeaxval[i].toString())!="" && (indeaxval[i].toString())!="null")
			{
				logger.info("________________in if-->"+indeaxval[i]);
				index1.add(indeaxval[i]);
			}
		
			}
			for(int i=0;i<empIncrCntr;i++){
				
				logger.info("The Check Value is:-"+request.getParameter("payFixId"+String.valueOf(index1.get(i))));
				if(request.getParameter("payFixId"+String.valueOf(index1.get(i)))!=null)
				{
				strToken = new StringTokenizer(request.getParameter(("payFixId"+(String.valueOf(index1.get(i))))));
				lstPayfixId.add(strToken.nextToken());
				
				lstEmpIncrStatus.add(strToken.nextToken());
				}
				logger.info(request.getParameter("currPay"+String.valueOf(index1.get(i))));
				//System.out.println(request.getParameter("currPay"+index1.get(i))+"The Current Emoployee Increment Status is:-"+lstEmpIncrStatus.get(iString.valueOf(index1.get(i))));
				
				
				lstIncrAmount.add(request.getParameter("dueNextIncrAmtt"+String.valueOf(index1.get(i))));
				
				
				logger.info(request.getParameter("currPayScale"+String.valueOf(index1.get(i)))+"The Increment Amount is:-"+lstIncrAmount.get(i));
				//System.out.println(request.getParameter("currPayScale"+String.valueOf(index1.get(i)))+"The Increment Amount is:-"+lstIncrAmount.get(i));
				
				//Added by Jigna Raval
				lstCurrentPayScale.add(request.getParameter("currPayScale"+String.valueOf(index1.get(i))));
				lstCurrentPay.add(request.getParameter("currPay"+String.valueOf(index1.get(i))));
				lstUserId.add(request.getParameter("UserId"+String.valueOf(index1.get(i))));
				
				//End by Jigna Raval
			}
			
			objServiceArgs.put("empIncrCntr", empIncrCntr);
			objServiceArgs.put("lstPayfixId", lstPayfixId);
			objServiceArgs.put("lstEmpIncrStatus", lstEmpIncrStatus);
			objServiceArgs.put("lstIncrAmount", lstIncrAmount);		
			
			//Added by Jigna Raval
			objServiceArgs.put("lstCurrentPayScale", lstCurrentPayScale);
			objServiceArgs.put("lstCurrentPay", lstCurrentPay);
			objServiceArgs.put("strIncrTypeFlag", strIncrTypeFlag);
			objServiceArgs.put("lstUserId", lstUserId);
		
			objServiceArgs.put("billId", billId);
			objServiceArgs.put("monthStr", monthStr);
			objServiceArgs.put("yearStr", yearStr);
			objServiceArgs.put("incrtype", incrtype);
			
			
			//End by Jigna Raval
			
			objRes.setResultValue(objServiceArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			objRes.setResultCode(ErrorConstants.ERROR);
		}
		return objRes;
	 }
	

}
