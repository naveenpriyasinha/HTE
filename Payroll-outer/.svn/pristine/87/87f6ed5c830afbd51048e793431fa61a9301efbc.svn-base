package com.tcs.sgv.lcm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptPaymentDtls;
import com.tcs.sgv.lcm.dao.LcChequeReconciliationDAOImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcChequeBook;

public class LcChequeReconciliationServiceImpl 
extends ServiceImpl implements LcChequeReconciliationService
{
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject getChequeReconciliationDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info(".............Inside the getChequeReconciliationDtls..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLangId = lLngLangId.toString();
		int lILangId = Integer.parseInt(lStrLangId);		
		
		try
		{
			long lLngChqNo =0;
			if(!StringUtility.getParameter("txtChqNo", request).equals(""))
				lLngChqNo=Long.parseLong(StringUtility.getParameter("txtChqNo", request));
			logger.info("lIChqNo is :  in getChequeReconciliationDtls---"+ lLngChqNo);
			LcChequeReconciliationDAOImpl chqReconciliationDao =new LcChequeReconciliationDAOImpl(TrnLcChequeBook.class,serv.getSessionFactory());
			
			LcDivisionInformationVO  lcDivInfoVo=null;
			String lStrChqDate="";
			String lStrChqAmt="";
			
			lcDivInfoVo=(LcDivisionInformationVO)chqReconciliationDao.getChequeReconciliationDtls(lLngChqNo);
			logger.info("----------------AFTER METHOD CALLING--------------");
			String lStrAjaxString="";
			if(lcDivInfoVo != null)
			{
				if(lcDivInfoVo.getDepartmentCode().equals(""))
				{
					if(lcDivInfoVo.getDepartment_name().equals(""))
					{
						lStrChqDate=lcDivInfoVo.getLc_order_id();
						lStrChqAmt=lcDivInfoVo.getOpening_lc();
						
						 StringTokenizer strToken =null;
						 strToken=new StringTokenizer(lStrChqDate,"-");
						 String yyyy=strToken.nextToken();
						 String mm=strToken.nextToken();
						 String dd=strToken.nextToken();
						 lStrChqDate=dd+"/"+mm+"/"+yyyy; 
						
						//logger.info("---3rd --1------2----"+lStrChqDate+"---"+lStrChqAmt);
						//lStrAjaxString = new AjaxXmlBuilder().addItem("txtChqIssueDt",lStrChqDate).addItem("txtLcChqAmt",lStrChqAmt).toString();
					}
					else
					{
						lStrChqDate="---";
						lStrChqAmt="0";
						//logger.info("---2rd --1------2----");
						//lStrAjaxString = new AjaxXmlBuilder().addItem("txtChqIssueDt","---").addItem("txtLcChqAmt","0").toString();
					}
				}
				else
				{
					lStrChqDate="--";
					lStrChqAmt="0";
					//logger.info("---1rd --1------2----");
					//lStrAjaxString = new AjaxXmlBuilder().addItem("txtChqIssueDt","--").addItem("txtLcChqAmt","0").toString();
				}
			}
			else
			{
				lStrChqDate="-";
				lStrChqAmt="0";
				//lStrAjaxString = new AjaxXmlBuilder().addItem("txtChqIssueDt","-").addItem("txtLcChqAmt","0").toString();
			}			
			
			logger.info("-----AJAX DATA--------------   "+lStrChqDate+lStrChqAmt);
		    StringBuffer lStrBuff =new StringBuffer();
			lStrBuff.append("<chqReconciliation>");			
			lStrBuff.append(lStrChqDate);
			lStrBuff.append("</chqReconciliation>");
			lStrBuff.append("<chqReconciliation>");			
			lStrBuff.append(lStrChqAmt);
			lStrBuff.append("</chqReconciliation>");
			
			lStrAjaxString = new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
			logger.info("-----AJAX DATA---lStrAjaxString-----------   "+lStrAjaxString);
			
			objectArgs.put("ajaxKey", lStrAjaxString);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(objectArgs);
		}
		catch(Exception e)
		{
			logger.error("Error in getChequeCancelDtls of LcChequeCancelServiceImpl ", e);
			logger.info("Error in getChequeCancelDtls of LcChequeCancelServiceImpl ");
			e.printStackTrace();
		}
		return objRes;
	}
	
	public ResultObject updateChequeqReconciliation(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info(".............Inside the updateChequeqReconciliation..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLangId = lLngLangId.toString();
		int lILangId = Integer.parseInt(lStrLangId);
		
		LcChequeReconciliationDAOImpl chqReconciliationDao =new LcChequeReconciliationDAOImpl(TrnLcChequeBook.class,serv.getSessionFactory());
		
		try
		{
			long lLngChqNo=0;
			String lStrChqClrDt="";
			boolean lBChqClrDtResult=false;
			
			if(StringUtility.getParameter("txtChqNo", request) != null && !StringUtility.getParameter("txtChqNo", request).equals(""))
			{
				 lLngChqNo = Long.parseLong(StringUtility.getParameter("txtChqNo", request));
				 logger.info("-------lLngChqNo----------"+lLngChqNo);
			}
			if(StringUtility.getParameter("txtChqPaidDt", request) != null && !StringUtility.getParameter("txtChqPaidDt", request).equals(""))
			{
				lStrChqClrDt = (String)StringUtility.getParameter("txtChqPaidDt", request);
				 logger.info("-------lStrChqClrDt----------"+lStrChqClrDt);
			}
			
			
			lBChqClrDtResult=chqReconciliationDao.updateChqClearDateInReconciliation(lLngChqNo, lStrChqClrDt);
			logger.info("----------CHQ CLR RESULT--------"+lBChqClrDtResult);
			
			//-----DSS DATA SERVICE----------------------------------------------------
			DssDataServiceImpl dssOnj=new DssDataServiceImpl();
			RptPaymentDtls paymentDss=null;
			Map inputMap=new HashMap();
			String chqTypeCode=bundleConst.getString("LC.CHQ_TYPE_CODE");
			String chqStatusCode=bundleConst.getString("LC.CHQ_STATUS_CODE_PAID");
			
			logger.info("---------paymentDss---"+chqTypeCode+"-------"+lLngChqNo);
			inputMap.put("map", objectArgs);
			inputMap.put("chqTypeCode", chqTypeCode);
			inputMap.put("chqNo", lLngChqNo);
			
			paymentDss=dssOnj.getPaymentData(inputMap);
			logger.info("---------paymentDss-----------"+paymentDss);
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			Date chqClrDt=sdf.parse(lStrChqClrDt);
			
			if(paymentDss != null)
			{
				paymentDss.setChqClrDt(chqClrDt);
				paymentDss.setChqStatusCode(chqStatusCode);
				paymentDss.setChqStatusDt(new Date());
				
				inputMap=new HashMap();
				
				inputMap.put("map", objectArgs);
				inputMap.put("RptPaymentVO", paymentDss);
				
				dssOnj.updatePaymentData(inputMap);			
			}
			else
			{
				logger.info("---------RETURN paymentDss Object is NULL in ChequeReconciliationServiceImpl-----------");
			}
			//---------------------------------------------------------------------
			
			
			request.setAttribute("lStrChqClrDt", lStrChqClrDt);
			if(lBChqClrDtResult==true)
				request.setAttribute("Result", "true");
			else
				request.setAttribute("Result", "false");
			
			objRes.setViewName("lcChequeReconciliation");
			objRes.setResultValue(objectArgs);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objRes;
	}
	
}

