package com.tcs.sgv.lcm.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.lcm.dao.LcChequeBookDAOImpl;
import com.tcs.sgv.lcm.dao.LcDistributionMstDAOImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcChequeBook;
import com.tcs.sgv.lcm.valueobject.TrnLcDistributionBudHd;

public class LcChequeBookServiceImpl 
extends ServiceImpl implements LcChequeBookService 
{
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject getChequeBookDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info(".............Inside the getChequeBookDtls..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLangId = lLngLangId.toString();
		int lILangId = Integer.parseInt(lStrLangId);
		String lStrLoggedLocCode=SessionHelper.getLocationCode(objectArgs);
		
		LcChequeBookDAOImpl lcChequeBookDao=new LcChequeBookDAOImpl(TrnLcChequeBook.class,serv.getSessionFactory());
		LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
		
		try
		{
			String lStrLocShrtNm="";
			String lStrDivCode = "";
			
			if(!StringUtility.getParameter("txtDivisionShrtNm", request).equals(""))
				lStrLocShrtNm=StringUtility.getParameter("txtDivisionShrtNm", request);
			lStrLocShrtNm=lStrLocShrtNm.trim();
			logger.info("Div code is :  in getDivisionDtls-"+ lStrLocShrtNm);
			
			lStrDivCode=lLcDistributionDaoImpl.getLocationCodeByTsryCodeAndDivCode(lStrLoggedLocCode, lStrLocShrtNm, lLngLangId);
			logger.info("Div code is :  in getDivisionDtls-"+ lStrDivCode);
			
			ArrayList lArrChequeBook=null;
			
			lArrChequeBook=(ArrayList)lcChequeBookDao.getChequeBookDtls(lStrDivCode,lILangId);
			
			if(lArrChequeBook.size() <= 0)
			{
				LcDivisionInformationVO lcDivInformationVO = new LcDivisionInformationVO();
				lcDivInformationVO.setDepartmentCode(lStrLocShrtNm);
				lArrChequeBook.add(lcDivInformationVO);
				
				request.setAttribute("Result", "false");
			}
			else
			{				
				request.setAttribute("Result", "true");
			}			
			
			logger.info("-------SIZE OF CHEQUE BOOK ARRLST IN CHQ BOOK SERV--------"+lArrChequeBook.size());
			
			objectArgs.put("lArrChequeBook", lArrChequeBook);
			
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcChkBookMst");
			
		}
		catch(Exception e)
		{
			logger.error("Error in getChequeBookDtls of LcChequeBookMstServiceImpl ", e);
			logger.info("Error in getChequeBookDtls of LcChequeBookMstServiceImpl ");
			e.printStackTrace();
		}
		return objRes;
	}	
	
	public ResultObject insertLcChequeBook(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info(".........Inside the insertLcChequeBook Method..............");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			LcChequeBookDAOImpl lcChequeBookDao=new LcChequeBookDAOImpl(TrnLcChequeBook.class,serv.getSessionFactory());
			
			TrnLcChequeBook lcChequeBookVo = null; // VO of Distribution Mst
			
			if(objectArgs.get("lcChequeBookVo") != null)
			{
				lcChequeBookVo=(TrnLcChequeBook)objectArgs.get("lcChequeBookVo");
				logger.info("-------lcChequeBookVo VO in SERV--------"+lcChequeBookVo.toString());				
			    lcChequeBookDao.saveLcChequeBook(lcChequeBookVo);
			    request.setAttribute("SeriesValidation", "true");
			    logger.info("-------CHEQUE BOOK DTL SAVED SUCCESSFULLY--------");
			}
			else
			{
				request.setAttribute("SeriesValidation", "false");
				logger.info("-------IN ELSE PART OF VALIDATION--------");
			}	
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcChkBookMst");
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}

}
