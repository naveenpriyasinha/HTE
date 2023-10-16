package com.tcs.sgv.lcm.service;

import java.util.Map;
import java.util.ResourceBundle;

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
import com.tcs.sgv.lcm.dao.LcChequeCancelDAOImpl;
import com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcChequeBook;
import com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting;


public class LcChequeCancelServiceImpl 
extends ServiceImpl implements LcChequeCancelService
{
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject getChequeDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info(".............Inside the getChequeCancelDtls..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLangId = lLngLangId.toString();
		int lILangId = Integer.parseInt(lStrLangId);
		
		LcChequeCancelDAOImpl lcChequeCancelDao=new LcChequeCancelDAOImpl(TrnLcChequeBook.class,serv.getSessionFactory());
		try
		{
			long lLngChqNo = Long.parseLong(StringUtility.getParameter("txtChqNo", request));
			logger.info("lIChqNo is :  in getChequeCancelDtls---"+ lLngChqNo);
			
			LcDivisionInformationVO  lcDivInfoVo=null;
			
			lcDivInfoVo=(LcDivisionInformationVO)lcChequeCancelDao.getChequeDtls(lLngChqNo);
			logger.info("----------------AFTER METHOD CALLING--------------");
			String lStrAjaxString="";
			if(lcDivInfoVo != null)
			{
				
				if(lcDivInfoVo.getDepartmentCode().equals(""))// getDepartmentCode contain Chq Clear Date
				{
					if(lcDivInfoVo.getDistrictCode().equals(""))// getDistrictCode contain Chq Cancel Date
					{
						long lLngNewBalance=0;
						lLngNewBalance=(Long.parseLong(lcDivInfoVo.getDivision_name()))+(Long.parseLong(lcDivInfoVo.getOpening_lc()));
						logger.info("----------------AFTER METHOD CALLING 2--------------");
						String lStrNewBalance = String.valueOf(lLngNewBalance);
						logger.info("----------------AFTER METHOD CALLING 3--------------");
						
						lStrAjaxString = new AjaxXmlBuilder().addItemAsCData("txtAdviceNo",lcDivInfoVo.getLc_order_id()).addItemAsCData("txtChqAmt",lcDivInfoVo.getOpening_lc()).addItemAsCData("txtChqDt",lcDivInfoVo.getLc_valid_from()).addItemAsCData("txtInFavourOf",lcDivInfoVo.getDepartment_name()).addItemAsCData("txtDivisionCode",lcDivInfoVo.getDivisionId()).addItemAsCData("txtBalanceAmt", lcDivInfoVo.getDivision_name()).addItemAsCData("txtNewBalanceAmt",lStrNewBalance).addItemAsCData("txtDivisionId",lcDivInfoVo.getDistrict_name()).toString();			 
								 
						logger.info("-----	lcDivInfoVo Amt------"+lStrAjaxString); 
					}
					else
					{
						lStrAjaxString = new AjaxXmlBuilder().addItemAsCData("txtAdviceNo","--").addItemAsCData("txtChqAmt","0").addItemAsCData("txtChqDt","--").addItemAsCData("txtInFavourOf","--").addItemAsCData("txtDivisionCode","--").addItemAsCData("txtBalanceAmt", "0").addItemAsCData("txtNewBalanceAmt","0").addItemAsCData("txtDivisionId","--").toString();
					}
				}
				else
				{
					lStrAjaxString = new AjaxXmlBuilder().addItemAsCData("txtAdviceNo","---").addItemAsCData("txtChqAmt","0").addItemAsCData("txtChqDt","---").addItemAsCData("txtInFavourOf","---").addItemAsCData("txtDivisionCode","---").addItemAsCData("txtBalanceAmt", "0").addItemAsCData("txtNewBalanceAmt","0").addItemAsCData("txtDivisionId","---").toString();
				}
			}
			else
			{
				lStrAjaxString = new AjaxXmlBuilder().addItemAsCData("txtAdviceNo","-").addItemAsCData("txtChqAmt","0").addItemAsCData("txtChqDt","-").addItemAsCData("txtInFavourOf","-").addItemAsCData("txtDivisionCode","-").addItemAsCData("txtBalanceAmt", "0").addItemAsCData("txtNewBalanceAmt","0").addItemAsCData("txtDivisionId","-").toString();
			}
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
	
	public ResultObject updateChqCancel(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info(".............Inside the getChequeCancelDtls..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLangId = lLngLangId.toString();
		int lILangId = Integer.parseInt(lStrLangId);
		
		LcChequeCancelDAOImpl lcChequeCancelDao=new LcChequeCancelDAOImpl(TrnLcChequeBook.class,serv.getSessionFactory());
		LCReportQueryDAOImpl lcReportQueryDao = new LCReportQueryDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
		try
		{
			long lLngChqNo=0;
			String lStrReason="";
			double lDblChqBalance=0.0;
			String lStrDivCode="";
			if(StringUtility.getParameter("txtChqNo", request) != null && !StringUtility.getParameter("txtChqNo", request).equals(""))
			{
				 lLngChqNo = Long.parseLong(StringUtility.getParameter("txtChqNo", request));
				 logger.info("-------lLngChqNo----------"+lLngChqNo);
			}
			if(StringUtility.getParameter("txtReason", request) != null && !StringUtility.getParameter("txtReason", request).equals(""))
			{
				 lStrReason = (StringUtility.getParameter("txtReason", request));
				 logger.info("-------lStrReason----------"+lStrReason);
			}
			if(StringUtility.getParameter("txtChqAmt", request) != null && !StringUtility.getParameter("txtChqAmt", request).equals(""))
			{
				lDblChqBalance = Double.parseDouble(StringUtility.getParameter("txtChqAmt", request));
				logger.info("-------lDblChqBalance----------"+lDblChqBalance);
			}
			if(StringUtility.getParameter("txtDivisionId", request) != null && !StringUtility.getParameter("txtDivisionId", request).equals(""))
			{
				lStrDivCode = (StringUtility.getParameter("txtDivisionId", request));
				logger.info("-------lStrDivCode----------"+lStrDivCode);
			}
			
			boolean lbUpdateResult=false;
			String lStrAdviceStatus="";
			boolean lBUpdateBalResult=false;
			String lStrSign="+";
			
			if(lLngChqNo != 0)
			    lbUpdateResult=(boolean)lcChequeCancelDao.updateChqCancel(lLngChqNo,lStrReason);
			logger.info("-------RESULT IN SRVC----------"+lbUpdateResult);
			
			if(lLngChqNo != 0)
				lStrAdviceStatus=(String)lcChequeCancelDao.getAdviceStatusByCheque(lLngChqNo);
			logger.info("-------RESULT IN SRVC-ADVICE STATUS---------"+lStrAdviceStatus);
			
			if(lbUpdateResult==true && lStrAdviceStatus.equals("0"))
			{
				lBUpdateBalResult=lcReportQueryDao.updateApprovedLcAmount(lStrDivCode, lDblChqBalance, lStrSign);
			}
			
			if(lbUpdateResult==true && lBUpdateBalResult==true)
			{
				request.setAttribute("Result", "true");
			}
			else
			{
				request.setAttribute("Result", "false");
			}
			
			objRes.setViewName("lcChqCancel");
			objRes.setResultValue(objectArgs);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objRes;
	}
	
}
