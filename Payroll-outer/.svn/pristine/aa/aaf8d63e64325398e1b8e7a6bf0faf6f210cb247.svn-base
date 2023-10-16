package com.tcs.sgv.lcm.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.dao.budget.BudHdDAOImpl;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstBPNCodeVO;
import com.tcs.sgv.common.dao.BudgetHdDtlsDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvaBudbpnMapping;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptPaymentDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;
import com.tcs.sgv.lcm.dao.LcAdviceReceiptDAOImpl;
import com.tcs.sgv.lcm.dao.LcDistributionMstDAOImpl;
import com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcBudgetPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDistributionBudHd;
import com.tcs.sgv.lcm.valueobject.TrnLcDtlPosting;

/**
 * @author 602106
 *
 */
public class LcAdviceReceiptServiceImpl extends ServiceImpl implements LcAdviceReceiptService {
		
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	Log logger = LogFactory.getLog(getClass());
	
	
		public ResultObject getInitAdviceDtls(Map objectArgs)
		{	
			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			logger.info("Inside the lcDivisionAccMstServiceImpl..............");
			
			
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			try
			{				
				
				LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
				//BudHdDAOImpl lObjBudDaoImpl = new BudHdDAOImpl(serv.getSessionFactory()); 
				//BudExpEstDmdVO lObjBudExpEstDmdVO = null;
				//HttpSession hs = request.getSession();
				Long lLngLangId = SessionHelper.getLangId(objectArgs);
				
				//String lStrLangId = lLngLangId.toString();
				String lStrLocId = bundleConst.getString("LC.Loc1");
				logger.info("----------------loc code---::"+SessionHelper.getLocationCode(objectArgs));
				logger.info("----------------loc id---::"+SessionHelper.getLocationId(objectArgs));
			    //int lILangId = Integer.parseInt(lStrLangId);
				
				FinancialYearDAOImpl lObjFinYrDAO = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
				long liFinYr = lObjFinYrDAO.getFinYearIdByCurDate();
				
				String lStrOldLangId = lObjLcAdvRcptDAOImpl.getStringLangId(lLngLangId);
				

				//ArrayList lArrDeduction=new ArrayList();
				
				//ArrayList lArrDemandNo=new ArrayList();
				//ArrayList lArrMjrHd=new ArrayList();
				
				HashMap lHmpDemMjrHd = null;
				HashMap lHmpSubMjrHd = null;
				HashMap lHmpMinHd = null;
				HashMap lHmpSubHd = null;
				

				lHmpDemMjrHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllMjrHds(lStrOldLangId, lStrLocId,liFinYr);
				request.setAttribute("MjrHdMap", lHmpDemMjrHd.get("mjrmap"));
				request.setAttribute("demandlist",lHmpDemMjrHd.get("demandlist"));
				logger.info("==============after calling getAllMjrHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl" );
				logger.info(" size of lHmpDemMjrHd is :: "+lHmpDemMjrHd.size());
				
				
				lHmpSubMjrHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllSubMjrHds(lStrOldLangId, lStrLocId,liFinYr);
				request.setAttribute("SubMjrHd", lHmpSubMjrHd);
				logger.info("==============after calling getAllSubMjrHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl ");
				logger.info("size of lHmpSubMjrHd is ::  "+lHmpSubMjrHd.size());
		
				lHmpMinHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllMinHds(lStrOldLangId, lStrLocId,liFinYr);
				request.setAttribute("MinHd", lHmpMinHd);
				logger.info("==============after calling getAllMinHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl" );
				logger.info(" size of lHmpMinHd is ::  "+lHmpMinHd.size());
		
				lHmpSubHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllSubHds(lStrOldLangId, lStrLocId,liFinYr);
				request.setAttribute("SubHd", lHmpSubHd);
				logger.info("==============after calling getAllSubHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl ");
				logger.info("size of lHmpSubHd is ::  "+lHmpSubHd.size());
			
					
				objRes.setResultValue(objectArgs);
				objRes.setViewName("lcAdviceReceived");
				logger.info("------------------end of service---------");
				
			}
			catch(Exception e)
			{
				logger.error("Error in getInitAdviceDtls of LcAdviceReceiptServiceImpl ", e);
				logger.info("Error in getInitAdviceDtls of LcAdviceReceiptServiceImpl ");
				e.printStackTrace();
			}
		return objRes;
	}
		
	public ResultObject getDivisionInformation(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info("Inside the getDivisionInformation..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLangId = lLngLangId.toString();
		long lILangId = Integer.parseInt(lStrLangId);
		
		String lStrLocationCode=SessionHelper.getLocationCode(objectArgs);
		
		try
		{	
			LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
			LcDivisionInformationVO divInformationVO = null;			
			
			if(!(StringUtility.getParameter("txtDivisionCode", request).trim().equals("")))
			{
				String lStrDivShrtNm = "";
				String lStrDivCode = "";
				StringTokenizer strToken=null;
				lStrDivShrtNm=StringUtility.getParameter("txtDivisionCode", request);
				lStrDivShrtNm=lStrDivShrtNm.trim();
				logger.info("---------lStrDivShrtNm------------"+lStrDivShrtNm);
				
				lStrDivCode=lLcDistributionDaoImpl.getLocationCodeByTsryCodeAndDivCode(lStrLocationCode, lStrDivShrtNm, lLngLangId);
				logger.info("---------lStrDivCode------------"+lStrDivCode);
				
				boolean lBDivCodeVerifyResult=lObjLcAdvRcptDAOImpl.getDivisionCodeVerification(lStrLocationCode, lStrDivCode, lLngLangId);
				logger.info("--------DIVISION VERIFICATION RESULT-----------"+lBDivCodeVerifyResult);
				
				logger.info("--------Div code is :  in getDivisionDtls-----"+ lStrDivCode);
				divInformationVO = (LcDivisionInformationVO)lObjLcAdvRcptDAOImpl.getDivisionInformation(lStrDivCode,lILangId);				
				
				String tempResult = "";
				if(lBDivCodeVerifyResult==true)
				{
					if(divInformationVO != null )
					{
						logger.info("--1--"+ divInformationVO.getDivisionId());
						logger.info("--2--"+ divInformationVO.getLc_order_id());
						logger.info("--3--"+ divInformationVO.getLc_valid_from());
						
						String lStrchkDtDt="-";
						if(divInformationVO.getLc_valid_from() != null) 
						{
							 lStrchkDtDt = divInformationVO.getLc_valid_from().toString();
							
							 strToken=new StringTokenizer(lStrchkDtDt,"-");
							 String yyyy=strToken.nextToken();
							 String mm=strToken.nextToken();
							 String dd=strToken.nextToken();
							 lStrchkDtDt=dd+"/"+mm+"/"+yyyy; 
						}
						logger.info("--3--Afetr Convert--"+ lStrchkDtDt);
						logger.info("--4--"+ divInformationVO.getOpening_lc());
						logger.info("--5--"+ divInformationVO.getDivision_name());
						logger.info("--6--"+ divInformationVO.getDepartment_name());
						String lStrDeptName=divInformationVO.getDepartment_name();
						
						String lStrDivName="-";
						if(divInformationVO.getDivision_name() != null)
						{
							lStrDivName=divInformationVO.getDivision_name();
							lStrDivName=lStrDivName.replaceAll("&", "n");
						}
						logger.info("--7--"+ divInformationVO.getDistrict_name());
						
						tempResult= new AjaxXmlBuilder().addItemAsCData("txtDivisionId",(divInformationVO.getDivisionId() == null)?"-":divInformationVO.getDivisionId()).addItemAsCData("txtLcOrderId",(divInformationVO.getLc_order_id()== null)?"-":divInformationVO.getLc_order_id()).addItemAsCData("txtLcValidFrom",lStrchkDtDt).addItemAsCData("txtOpeningLcBal",(divInformationVO.getOpening_lc()==null)?"-":divInformationVO.getOpening_lc()).addItemAsCData("txtDivName",(lStrDivName==null)?"-":lStrDivName).addItemAsCData("txtDeptName",(divInformationVO.getDepartment_name()==null)?"-":divInformationVO.getDepartment_name()).addItemAsCData("txtDistName",(divInformationVO.getDistrict_name()==null)?"-":divInformationVO.getDistrict_name()).addItemAsCData("hidDistCode",(divInformationVO.getDistrictCode()==null)?"-":divInformationVO.getDistrictCode()).addItemAsCData("hidDeptCode",(divInformationVO.getDepartmentCode()==null)?"-":divInformationVO.getDepartmentCode()).toString();
					}
				}
				else
				{
					tempResult= new AjaxXmlBuilder().addItemAsCData("txtDivisionId","--").addItemAsCData("txtLcOrderId","--").addItemAsCData("txtLcValidFrom","--").addItemAsCData("txtOpeningLcBal","--").addItemAsCData("txtDivName","--").addItemAsCData("txtDeptName","--").addItemAsCData("txtDistName","--").toString();
				}
				logger.info("---------Ajax String  in getDivisionDtls-------------- "+tempResult);
				
				objectArgs.put("ajaxKey",tempResult);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(objectArgs);
		   }
			else
			{
				logger.info("in else part of  in getDivisionDtls ");
				String tempResult = "";
				tempResult= new AjaxXmlBuilder().addItemAsCData("txtDivisionId","-").addItemAsCData("txtLcOrderId","-").addItemAsCData("txtLcValidFrom","-").addItemAsCData("txtOpeningLcBal","-").addItemAsCData("txtDivName","-").addItemAsCData("txtDeptName","-").addItemAsCData("txtDistName","-").toString();
				objectArgs.put("ajaxKey",tempResult);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(objectArgs);
			}
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}	
	
	public ResultObject getChequeVerification(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info("..............Inside the getChequeVerification..............");
		logger.info("-----------INSIDE getChequeVerification--------------------");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLangId = lLngLangId.toString();
		long lILangId = Integer.parseInt(lStrLangId);
		
		String lStrLocationCode=SessionHelper.getLocationCode(objectArgs);
		
		try
		{	
			LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
				
			logger.info("----"+StringUtility.getParameter("txtDivisionCode", request));
			if(!(StringUtility.getParameter("txtDivisionCode", request).equals("")))
			{
				String lStrDivShrtNm = "";
				String lStrDivCode = "";
				String lStrChqNo="";
				String lStrChqVerifyResult="";
				
				lStrDivShrtNm=StringUtility.getParameter("txtDivisionCode", request);
				lStrDivShrtNm=lStrDivShrtNm.trim();
				logger.info("---------lStrDivShrtNm------------"+lStrDivShrtNm);
				
				lStrDivCode=lLcDistributionDaoImpl.getLocationCodeByTsryCodeAndDivCode(lStrLocationCode, lStrDivShrtNm, lLngLangId);
				logger.info("---------lStrDivCode------------"+lStrDivCode);
					
				
				if(!(StringUtility.getParameter("txtChqValidate", request).equals("")))
				{
				    
					lStrChqNo=StringUtility.getParameter("txtChqValidate", request);
					logger.info("--------Div code is :  in getChequeVerification-----"+ lStrDivCode);
					logger.info("--------lStrChqNo is :  in getChequeVerification-----"+ lStrChqNo);
					lStrChqVerifyResult = (String)lObjLcAdvRcptDAOImpl.getChequeNoVerification(lStrChqNo, lStrDivCode, lILangId);			
					logger.info("----CHQ VERIFIACTION RESULT--------"+lStrChqVerifyResult);
					String tempResult = "";				
					
					StringBuffer lStrBuff =new StringBuffer();
					lStrBuff.append("<chqVerify>");
					lStrBuff.append(lStrChqVerifyResult);
					lStrBuff.append("</chqVerify>");
					
					if(!(lStrChqVerifyResult.equals("AlreadyIssued")))
					{
						logger.info("IN IF 1----");
						if(lStrChqVerifyResult.equals("InvalidSeries"))
						{
							logger.info("IN IF 2----");
							tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
						}
						tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
					}
					else
					{
						logger.info("IN ELSE----");
						tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
					}
					logger.info("---------Ajax String  in getChequeVerification-------------- "+tempResult);
					
					objectArgs.put("ajaxKey",tempResult);
					objRes.setViewName("ajaxData");
					objRes.setResultValue(objectArgs);
				}
				else
				{
					String tempResult = "";
					StringBuffer lStrBuff =new StringBuffer();
					lStrBuff.append("<chqVerify>");
					lStrBuff.append("-");
					lStrBuff.append("</chqVerify>");
					
					tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
					objectArgs.put("ajaxKey",tempResult);
					objRes.setViewName("ajaxData");
					objRes.setResultValue(objectArgs);
				}
		   }
			else
			{
				logger.info("in else part of  in getChequeVerification ");
				String tempResult = "";
				StringBuffer lStrBuff =new StringBuffer();
				lStrBuff.append("<chqVerify>");
				lStrBuff.append("invalidDivCode");
				lStrBuff.append("</chqVerify>");
				
				tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
				objectArgs.put("ajaxKey",tempResult);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(objectArgs);
			}
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}	
	
	public ResultObject getAdviceVerification(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info("..............Inside the getAdviceVerification..............");
		logger.info("-----------INSIDE getAdviceVerification--------------------");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLangId = lLngLangId.toString();
		long lILangId = Integer.parseInt(lStrLangId);
		
		String lStrLocationCode=SessionHelper.getLocationCode(objectArgs);
		
		try
		{	
			LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
				
			logger.info("----"+StringUtility.getParameter("txtDivisionCode", request));
			if(!(StringUtility.getParameter("txtDivisionCode", request).equals("")))
			{
				String lStrDivShrtNm = "";
				String lStrDivCode = "";
				String lStrAdviceNo="";
				String lStrAdviceVerifyResult="";
				
				lStrDivShrtNm=StringUtility.getParameter("txtDivisionCode", request);
				lStrDivShrtNm=lStrDivShrtNm.trim();
				logger.info("---------lStrDivShrtNm------------"+lStrDivShrtNm);
				
				lStrDivCode=lLcDistributionDaoImpl.getLocationCodeByTsryCodeAndDivCode(lStrLocationCode, lStrDivShrtNm, lLngLangId);
				logger.info("---------lStrDivCode------------"+lStrDivCode);
					
				
				if(!(StringUtility.getParameter("txtAdviceNo", request).equals("")))
				{
				    
					lStrAdviceNo=StringUtility.getParameter("txtAdviceNo", request);
					logger.info("--------Div code is :  in getChequeVerification-----"+ lStrDivCode);
					logger.info("--------lStrAdviceNo is :  in getChequeVerification-----"+ lStrAdviceNo);
					
					FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
					long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
					//System.out.println("------FINYEAR ID---------"+lIFinYrId);
					
					lStrAdviceVerifyResult = (String)lObjLcAdvRcptDAOImpl.getAdviceNoVerification(lStrAdviceNo, lStrDivCode, lILangId,lIFinYrId);			
					logger.info("----ADVICE NO VERIFIACTION RESULT--------"+lStrAdviceVerifyResult);
					String tempResult = "";				
					
					StringBuffer lStrBuff =new StringBuffer();
					lStrBuff.append("<adviceNoVerify>");
					lStrBuff.append(lStrAdviceVerifyResult);
					lStrBuff.append("</adviceNoVerify>");
					
					/*if(!(lStrAdviceVerifyResult.equals("AlreadyIssued")))
					{
						logger.info("IN IF ----");						
						tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
					}
					else
					{
						logger.info("IN ELSE----");
						tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
					}*/
					
					tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
					logger.info("---------Ajax String  in getAdviceVerification-------------- "+tempResult);
					
					objectArgs.put("ajaxKey",tempResult);
					objRes.setViewName("ajaxData");
					objRes.setResultValue(objectArgs);
				}
				else
				{
					String tempResult = "";
					StringBuffer lStrBuff =new StringBuffer();
					lStrBuff.append("<adviceNoVerify>");
					lStrBuff.append("-");
					lStrBuff.append("</adviceNoVerify>");
					
					tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
					objectArgs.put("ajaxKey",tempResult);
					objRes.setViewName("ajaxData");
					objRes.setResultValue(objectArgs);
				}
		   }
			else
			{
				logger.info("in else part of  in getChequeVerification ");
				String tempResult = "";
				StringBuffer lStrBuff =new StringBuffer();
				lStrBuff.append("<adviceNoVerify>");
				lStrBuff.append("invalidDivCode");
				lStrBuff.append("</adviceNoVerify>");
				
				tempResult= new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
				objectArgs.put("ajaxKey",tempResult);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(objectArgs);
			}
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}	
	
	public ResultObject insertLcDeductionPosting(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info(".........Inside the insertLcDeductionPosting Method of ADVICE RECEIVED SRVC..............");	
		
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		String lStrLocCode = SessionHelper.getLocationCode(objectArgs);
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		try
		{
			
			LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			LCReportQueryDAOImpl lcReportQueryDao = new LCReportQueryDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());			
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
			TrnLcDtlPosting lObjLcDtlPostingVo = null;//TrnLcDtlPosting VO
			
			Long lLngTrnCntr=lLcDistributionDaoImpl.getTrnCntr();
			
			TrnLcChequePosting lcChequePostingVO =null;//LcChequePosting VO
			ArrayList lArrChequePosting=null;
			
			TrnLcBudgetPosting lcBudgetPostingVO =null;//LcChequePosting VO
			ArrayList lArrBudgetPosting=null;
			
			TrnLcDeductionPosting lcDeductionVo=null;//LcDeductionPosting VO
			ArrayList lArrDeductionPosting=null;
			
			boolean lBDtlPostingResult=false;
			boolean lBChqPostingResult = false;
			boolean lBBudPostingResult=false;	
			boolean lBInsertStatus = true;
			
			String lStrDssChqResult="";
			String lStrDssBudResult="";
			String lStrDssDedResult="";
			
			if(objectArgs.get("lObjLcDtlPostingVo") != null)
			{
				lObjLcDtlPostingVo=(TrnLcDtlPosting)objectArgs.get("lObjLcDtlPostingVo");
				lObjLcDtlPostingVo.setTrnCntr(new BigDecimal(lLngTrnCntr));
				logger.info("-------DTL POSTING VO in SERV--------"+lObjLcDtlPostingVo.toString());
			}
			
			if(objectArgs.get("lArrChequePosting") != null)
			{
				lArrChequePosting=(ArrayList)objectArgs.get("lArrChequePosting");
				logger.info("-----SIZE OF lArrChequePosting ARRLIST IN SERV--------"+lArrChequePosting.size());
			}
			if(objectArgs.get("lArrBudgetPosting") != null)
			{	
				lArrBudgetPosting=(ArrayList)objectArgs.get("lArrBudgetPosting");
				logger.info("-----SIZE OF lArrBudgetPosting ARRLIST IN SERV--------"+lArrBudgetPosting.size());
			}
			if(objectArgs.get("lArrDeductionPosting") != null)
			{
				lArrDeductionPosting=(ArrayList)objectArgs.get("lArrDeductionPosting");
				logger.info("-----SIZE OF lArrDeductionPosting ARRLIST IN SERV--------"+lArrDeductionPosting.size());
			}
			
			
			//Save the data in LcDtlPosting table by calling Method of DAO Impl
			if(lObjLcDtlPostingVo != null)
			{
				lBDtlPostingResult=lObjLcAdvRcptDAOImpl.saveLcDtlPosting(lObjLcDtlPostingVo);
				
				logger.info("-------DTL POSTING RESULT--------"+lBDtlPostingResult);
				if(!lBDtlPostingResult)
				{
					lBInsertStatus=false;
				}
			}
			
			String lStrBankCode=lObjLcDtlPostingVo.getBankCode().toString();
			
			
			String lStrAdvApproved = lObjLcDtlPostingVo.getAdviceApproved().toString();
			String lStrTsryCode=lObjLcAdvRcptDAOImpl.getLoggedInTsryCode(lStrLocCode, lLngLangId);
			//save the data in LcChequePosting table by calling Method of DAO Impl
			for(int i=0;i<lArrChequePosting.size();i++)
			{
				lcChequePostingVO = new TrnLcChequePosting();
				
				lcChequePostingVO = (TrnLcChequePosting)lArrChequePosting.get(i);
				logger.info("-------CHQ VO in SERV--------"+lcChequePostingVO.toString());
				
				lBChqPostingResult = lObjLcAdvRcptDAOImpl.saveLcChequePosting(lcChequePostingVO);
				
				Map returnMap=insertDSSChqData(lcChequePostingVO,serv,objectArgs,lStrTsryCode,lStrBankCode);
				if(returnMap.containsKey("flag"))
					lStrDssChqResult=returnMap.get("flag").toString();
				
				logger.info("--------CHQ INSERT RESULT--------"+lBChqPostingResult);
				if(!lBChqPostingResult)
				{
					lBInsertStatus=false;
				}
			}
			
			
			logger.info("=======================After saving chq details==================================");
			//save the data in LcBudgetPosting table by calling Method of DAO Impl
			double lDblTotExpAmt=0.0;
			for(int i=0;i<lArrBudgetPosting.size();i++)
			{
				lcBudgetPostingVO = new TrnLcBudgetPosting();
				
				lcBudgetPostingVO = (TrnLcBudgetPosting)lArrBudgetPosting.get(i);
				logger.info("-------BUDGET VO in SERV--------"+lcBudgetPostingVO.toString());
				lDblTotExpAmt += Double.parseDouble(lcBudgetPostingVO.getExpAmt().toString());
				logger.info("Dept code of whole advice is :: "+(String)objectArgs.get("deptcode"));
				lBBudPostingResult = lObjLcAdvRcptDAOImpl.saveLcBudgetPosting(lcBudgetPostingVO);
				
				Map resultMap=insertDSSBudData(lcBudgetPostingVO,serv,(String)objectArgs.get("distcode"),objectArgs,lStrAdvApproved,lStrTsryCode,lLngLangId);
				if(resultMap.containsKey("flag"))
					lStrDssBudResult=(String)resultMap.get("flag").toString();
				
				logger.info("--------BUD INSERT RESULT--------"+lBBudPostingResult);
				if(!lBBudPostingResult)
				{
					lBInsertStatus=false;
				}
			}
			
			
			
			
			
			logger.info("----------TOTAL EXP AMT AT INSERT------------"+lDblTotExpAmt);
			
			//save the data in LcDeductionPosting table by Hibernate code Generation
			Serializable lStrInsertRes="";
			double lDblDedAmt=0;
			for(int i=0;i<lArrDeductionPosting.size();i++)
			{
				lcDeductionVo = new TrnLcDeductionPosting();
				
				lcDeductionVo = (TrnLcDeductionPosting)lArrDeductionPosting.get(i);
				logger.info("-------lcDeductionVo VO in SERV--------"+lcDeductionVo.toString());
				
				
				lStrInsertRes =lObjLcAdvRcptDAOImpl.create(lcDeductionVo);
				Map returnMap=insertDSSDeductionData(lcDeductionVo,serv,(String)objectArgs.get("distcode"),lObjLcDtlPostingVo.getLcExpId(),objectArgs,lStrAdvApproved,lStrTsryCode);
				if(returnMap.containsKey("flag"))
					lStrDssDedResult=(String)returnMap.get("flag").toString();
			    
				logger.info("=============Result of "+i+" insert row is :: "+lStrInsertRes);
				lDblDedAmt+=lcDeductionVo.getAmount().doubleValue();
				ArrayList temp = new ArrayList();
			
			}
			
           //here--
			DssDataServiceImpl lObjDSS = new DssDataServiceImpl();
			RptExpenditureDtls lObjExpVO = new RptExpenditureDtls();
			LcAdviceReceiptDAOImpl lObjLC = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			HashMap lHmpAmt=null;
		//	HashMap lHmpEDPStruct= (HashMap)lObjLC.getEDPBudStructure(dedVO.getEdpCode());
			
			String lStrLcTypeCode=bundleConst.getString("LC.EXP_TYPE_CODE");
			
			lObjExpVO.setDeptCode(bundleConst.getString((String)objectArgs.get("deptcode")));
			lObjExpVO.setDistrictCode((String)objectArgs.get("distcode"));
			lObjExpVO.setTsryCode(lStrTsryCode);
			lObjExpVO.setExpNo(lObjLcDtlPostingVo.getLcExpId());
			logger.info("------------FFFFF------"+lStrLcTypeCode);
			lObjExpVO.setExpTypeCode(lStrLcTypeCode);
			/*if(lStrAdvApproved.equals("0"))
				lObjExpVO.setExpDt(new Date());*/
			if(lStrAdvApproved.equals("0"))
			{
				lObjExpVO.setExpDt(new Date());
				lObjExpVO.setExpStatusCode(bundleConst.getString("LC.AUDIT_APPROVED"));
				lObjExpVO.setExpStatusDt(new Date());
			}
			else
			{
				lObjExpVO.setExpStatusCode(bundleConst.getString("LC.AUDIT_PENDING"));
				lObjExpVO.setExpStatusDt(new Date());
			}
			
			
			//lObjExpVO.setExpTypeCode();
			//lObjExpVO.setClsExpCode(budVO.g);
			//lObjExpVO.setFundTypeCode(budVO.getFund().toString());
			//lObjExpVO.setBudTypeCode(bundleConst.getString(""));
			//lObjExpVO.setDemandNo(budVO.getDemandNo());
			//lObjExpVO.setScheme(budVO.getSchemeNo());
			//lObjExpVO.setMjrHd(budVO.getMjrHd().toString());
			//lObjExpVO.setMinHd(budVO.getMinHd().toString());
			//lObjExpVO.setSubMjrHd(budVO.getSubMjrHd().toString());
			//lObjExpVO.setSubHd(budVO.getSubHd().toString());
			//lObjExpVO.setDtlHd(budVO.getDtlHd().toString());
			
			lObjExpVO.setFinYrId(new BigDecimal(lObjLcDtlPostingVo.getFinYearId()));
			
			if(lStrAdvApproved.equals("0"))
				lObjExpVO.setExpDt(new Date());
			
			/*lHmpAmt = new HashMap();
			lHmpAmt.put("amount", lDblDedAmt);
			lHmpAmt.put("calculate", "false");
			lObjExpVO.setDedctnbAmt(lHmpAmt);
			//lObjExpVO.setExpStatusCode(expStatusCode);
*/			NumberFormat testNumberFormat = NumberFormat.getInstance();

			logger.info("-----------DED AMT IN SRVC-------------------  "+lDblDedAmt);
			logger.info("-----------DED AMT IN SRVC-------------------  "+(0-lDblDedAmt));
			logger.info("-----------DED AMT IN SRVC-------------------  "+testNumberFormat.format(lDblDedAmt));
			logger.info("-----------DED AMT IN SRVC-------------------  "+testNumberFormat.parse(testNumberFormat.format(lDblDedAmt)));
			
			String lStrDedAmt ="-"+testNumberFormat.parse(testNumberFormat.format(lDblDedAmt)).toString();			
			logger.info("------------------------------------"+lStrDedAmt);
			
			lHmpAmt = new HashMap();
			lHmpAmt.put("amount", lStrDedAmt);
			lObjExpVO.setNetAmt(lHmpAmt);
			
			/*lHmpAmt = new HashMap();
		  //lHmpAmt.put("amount", budVO.getExpAmt().toString());
			lObjExpVO.setExpAmt(lHmpAmt);*/
			
			lHmpAmt = new HashMap();
			lHmpAmt.put("map", objectArgs);
			lHmpAmt.put("RptExpenditureVO", lObjExpVO);
			
			HashMap lHmpReturnMap = lObjDSS.insertExpData(lHmpAmt);
			logger.info("========Hash Map after calling DSS Service(For Dummy Entry) insertExpData is :: "+lHmpReturnMap);
			
			//--LOGIC for ADVICE APPROVAL--& UPDATE AMT IN DISTRIBUTION-----------
			boolean updateApproveAmtResult=false;
			String lStrDivCode="";
			String lStrSign="-";
			lStrAdvApproved = lObjLcDtlPostingVo.getAdviceApproved().toString();
			if(lObjLcDtlPostingVo.getLocCode() != null)
				lStrDivCode=lObjLcDtlPostingVo.getDivisionCode().toString();
			
			logger.info("------------APPROVED FLAG in SERV AT INSERTION--------------"+lStrAdvApproved);
			logger.info("------------LC lILcDivisionId ID in SERV AT INSERTION--------------"+lStrDivCode);
			if(lStrAdvApproved.equals("0") && lBBudPostingResult==true)
			{
				updateApproveAmtResult=(boolean)lcReportQueryDao.updateApprovedLcAmount(lStrDivCode,lDblTotExpAmt,lStrSign);
				logger.info("---UPDATE LC APPROVED AMT RESULT IN INSERT SEVC------"+updateApproveAmtResult);
			}
			else
			{
				logger.info("--- NOT UPDATE LC APPROVED AMT  IN INSERT SEVC------"+updateApproveAmtResult);
			}
			
			//---------------------------------------------
			
			logger.info("\n=======================Size of chq arr is :: "+lBDtlPostingResult);
			logger.info("\n=======================Size of chq arr is :: "+lBChqPostingResult);
			logger.info("\n=====================Size of bud arr is :: "+lBBudPostingResult);
			
			//--------------------------------for jsp onLoad data-----------------------------------
			
			//BudHdDAOImpl lObjBudDaoImpl = new BudHdDAOImpl(serv.getSessionFactory()); 
			//BudExpEstDmdVO lObjBudExpEstDmdVO = null;
			//HttpSession hs = request.getSession();
			String lStrInsertStatus="";
			if(lBInsertStatus)
				lStrInsertStatus="true";
			else
				lStrInsertStatus="false";
			
			request.setAttribute("insertflag", lStrInsertStatus);
			
			
			
			//String lStrLangId = lLngLangId.toString();
			String lStrLocId = bundleConst.getString("LC.Loc1");
		    //int lILangId = Integer.parseInt(lStrLangId);
			
			FinancialYearDAOImpl lObjFinYrDAO = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			long liFinYr = lObjFinYrDAO.getFinYearIdByCurDate();
			
			String lStrOldLangId = lObjLcAdvRcptDAOImpl.getStringLangId(lLngLangId);
	
			
			HashMap lHmpDemMjrHd = null;
			HashMap lHmpSubMjrHd = null;
			HashMap lHmpMinHd = null;
			HashMap lHmpSubHd = null;
			

			lHmpDemMjrHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllMjrHds(lStrOldLangId, lStrLocId,liFinYr);
			request.setAttribute("MjrHdMap", lHmpDemMjrHd.get("mjrmap"));
			request.setAttribute("demandlist",lHmpDemMjrHd.get("demandlist"));
			logger.info("==============after calling getAllMjrHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl" );
			logger.info(" size of lHmpDemMjrHd is :: "+lHmpDemMjrHd.size());
			
			
			lHmpSubMjrHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllSubMjrHds(lStrOldLangId, lStrLocId,liFinYr);
			request.setAttribute("SubMjrHd", lHmpSubMjrHd);
			logger.info("==============after calling getAllSubMjrHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl ");
			logger.info("size of lHmpSubMjrHd is ::  "+lHmpSubMjrHd.size());
	
			lHmpMinHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllMinHds(lStrOldLangId, lStrLocId,liFinYr);
			request.setAttribute("MinHd", lHmpMinHd);
			logger.info("==============after calling getAllMinHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl" );
			logger.info(" size of lHmpMinHd is ::  "+lHmpMinHd.size());
	
			lHmpSubHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllSubHds(lStrOldLangId, lStrLocId,liFinYr);
			request.setAttribute("SubHd", lHmpSubHd);
			logger.info("==============after calling getAllSubHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl ");
			logger.info("size of lHmpSubHd is ::  "+lHmpSubHd.size());
		
				
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcAdviceReceived");
			logger.info("------------------end of service---------");
			
			
			
			//--------------------------------for jsp onLoad data-----------------------------------
			logger.info("======AFTER SAVED DATA IN ADVICE RECEIVED SERV==========");
			
			logger.info("======AFTER SAVED DATA IN ADVICE RECEIVED SERV=========="+lStrDssChqResult+lStrDssChqResult+lStrDssChqResult);
			if(lStrDssChqResult.equals("false") || lStrDssBudResult.equals("false") || lStrDssDedResult.equals("false"))
			{
				objRes.setResultValue(null);				
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");				
			}
			
		}
		catch(Exception e)
		{
			logger.error("Error: in insertLcDeductionPosting  "+e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
		}
		return objRes;
	}
	
	public ResultObject getMjrHdByDemand(Map objectArgs)
	{
		
		logger.info("--------inside getMjrHdByDemand------------");
			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			try 
			{
				HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				String lStrSrc="";
				String lStrSearchString = "";
				String lStrTargetCmb="";
				for(int lIntCntr =1;lIntCntr<=5;lIntCntr++)
				{
					lStrSearchString = "cmbDemandNo"+lIntCntr;
					lStrTargetCmb= "cmbMjrHd"+lIntCntr;
					if(request.getParameter(lStrSearchString) != null)
					{
						lStrSrc=(String)request.getParameter(lStrSearchString);
						break;
					}
				}

				logger.info("Source combo value is :: "+lStrSrc);
				
				HttpSession hs = request.getSession();
				Long lLngLangId = SessionHelper.getLangId(objectArgs);
				String lStrLangId = lLngLangId.toString();
				long lILangId = Integer.parseInt(lStrLangId);
				LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
				String lStrOldLangId = lObjLcAdvRcptDAOImpl.getStringLangId(lLngLangId);
				
				ArrayList lArrMjrHd = new ArrayList();
				ArrayList lArrFinalList=new ArrayList();

				CommonVO lCmnVO = new  CommonVO();
				lCmnVO.setCommonId("0");
				lCmnVO.setCommonDesc("--select--");
				lArrFinalList.add(lCmnVO);
				lArrMjrHd=(ArrayList) lObjLcAdvRcptDAOImpl.getMjrHdByDemand(lStrSrc, lStrOldLangId);
				for (int i=0;i<lArrMjrHd.size();i++)
				{
					lArrFinalList.add(lArrMjrHd.get(i));
				}
				
				//objectArgs.put("lArrMjrHd", lArrFinalList);
				logger.info("----------------------- LC lArrMjrHd list in getInitAdviceDtls() of LcAdviceReceiptServiceImpl ------"+lArrFinalList.size());
				
				String lStrDataString = new String();
				lStrDataString=new AjaxXmlBuilder().addItems(lArrFinalList, "commonDesc", "commonId").toString();
	
				objectArgs.put("ajaxKey",lStrDataString);
			
				objRes.setResultValue(objectArgs);
				objRes.setViewName("ajaxData");
			} 
			catch (NumberFormatException e)
			{
				e.printStackTrace();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return objRes;
	}
	
	public ResultObject getSubMjrHd(Map objectArgs)
	{
		logger.info("Inside getSubMjrHd of LcAdviceReceiptServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
		String langId=lObjLcAdvRcptDAOImpl.getStringLangId(lLngLangId);
		String locId = bundleConst.getString("LC.Loc1");
		Long lLngLocId = SessionHelper.getLocationId(objectArgs);
		String lStrLocCode = SessionHelper.getLocationCode(objectArgs);
		
		try {
			SessionFactory sessionFactory = serv.getSessionFactory();			
			BudHdDAOImpl BudHdDAOImpl = new BudHdDAOImpl(sessionFactory);
			
			String deptId = request.getParameter("deptId");
			String demandCode = "";
			String mjrHeadCode ="";
		
			
			String lStrDemandName = "";
			String lStrMjrHdName="";
			
			for(int lIntCntr =1;lIntCntr<=5;lIntCntr++)
			{
				lStrDemandName = "cmbDemandNo"+lIntCntr;
				lStrMjrHdName= "cmbMjrHd"+lIntCntr;
				if(request.getParameter(lStrDemandName) != null && request.getParameter(lStrMjrHdName) != null && !(((String)request.getParameter(lStrMjrHdName)).equals("0")) )
				{
					logger.info("-------------2 :in if condition-------");
					
					demandCode=(String)request.getParameter(lStrDemandName);
					mjrHeadCode=(String)request.getParameter(lStrDemandName);
					break;
				}
			}
			logger.info("----------after for loop-----------");
			
			String hdType = "E";
			
			
			String status = "Active";
		
			logger.info("before ---------if(deptLocId!=null) result is:: "+lStrLocCode );
			if(lStrLocCode!=null) {
				deptId = new LocationDAOImpl(CmnLocationMst.class,sessionFactory).getDeptCodeByLocId(lStrLocCode, langId);
				logger.info("DEPT ID -----------------: " +deptId);
				logger.info("DEPT ID -----------------: " +deptId);
			}
			logger.info("-------------after if(deptLocId)-----------------");
			ArrayList lArrSbMjrHdVO = new ArrayList();
			BudHdDAOImpl budHdDAO = new BudHdDAOImpl(sessionFactory);			
			logger.info("---------before 2---------------");
			ArrayList lArrDeptVO = budHdDAO.getAllBPNCode(deptId, langId, locId);
			logger.info("---------before 3---------------"+lArrDeptVO.size());
			if (lArrDeptVO!=null){
				logger.info("---------before 4---------------");
				Iterator it = lArrDeptVO.iterator();
				logger.info("---------5---------------");
				while(it.hasNext()) {
					logger.info("---------6---------------");
					BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) it.next();
					logger.info("---------7---------------");
					lArrSbMjrHdVO.addAll(budHdDAO.getSbMjrHds(bpnCodeVO.getBPNCode(), demandCode, mjrHeadCode, hdType, langId, locId, status));
					logger.info("---------8---------------");
					
				}
				logger.info("---------9---------------");
			}
			logger.info("---------10---------------");
			String xmlData = new AjaxXmlBuilder().addItems(lArrSbMjrHdVO, "sbMjrHdCode","sbMjrHdCode").toString();
			objectArgs.put("ajaxKey", xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		logger.info("returning from service---------------");
		}   catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			e.printStackTrace();
		}
		return objRes;	
	}

	public Map insertDSSChqData(TrnLcChequePosting chqVO,ServiceLocator serv,Map objArgs,String lStrTsryCode,String lStrBankCode)
	{
		RptPaymentDtls paymentVo =new RptPaymentDtls();
		DssDataServiceImpl lObjDSS = new DssDataServiceImpl();
		
		String chqTypeCode=bundleConst.getString("LC.CHQ_TYPE_CODE");
		String chqStatusCode=bundleConst.getString("LC.CHQ_STATUS_CODE_UNPAID");
		
		paymentVo.setTsryCode(lStrTsryCode);
		paymentVo.setChqNo(chqVO.getChequeNo());
		paymentVo.setAmount(chqVO.getChequeAmt());
		paymentVo.setChqTypeCode(chqTypeCode);
		paymentVo.setFinYrId(new BigDecimal(chqVO.getFinYearId()));
		paymentVo.setPartyName(chqVO.getPartyName());
		paymentVo.setChqStatusDt(new Date());
		paymentVo.setChqStatusCode(chqStatusCode);
		paymentVo.setBankCode(lStrBankCode);
		
		Map inputMap =new HashMap();
		inputMap.put("map", objArgs);
		inputMap.put("RptPaymentVO", paymentVo);
		
		Map returnMap=lObjDSS.insertPaymentData(inputMap);
		
		return returnMap;
	}
	
	public Map insertDSSBudData(TrnLcBudgetPosting budVO,ServiceLocator serv, String lStrDistCode,Map objArgs,String lStrAdvApproved,String lStrTsryCode,long lLngLangId)
	{
		DssDataServiceImpl lObjDSS = new DssDataServiceImpl();
		RptExpenditureDtls lObjExpVO = new RptExpenditureDtls();
		LcAdviceReceiptDAOImpl lObjLC = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
		SgvaBudbpnMapping lObjBudBpnVO = new SgvaBudbpnMapping();
		BudgetHdDtlsDAOImpl lObjBudDAOImpl = new BudgetHdDtlsDAOImpl(budVO.getFinYearId(),serv.getSessionFactory());
		
		String lStrDeptId="";
		
		lObjBudBpnVO=lObjBudDAOImpl.getBPNInfoFrmDmd(budVO.getDemandNo(), lLngLangId);
		lStrDeptId=lObjBudBpnVO.getDeptId();
		String lStrDeptCode = lObjLC.getDeptCode(lStrDeptId, lLngLangId);
		
		HashMap lHmpAmt=null;
		String lStrCls="";
		String lStrBud="";
		String lStrFundType="";
		
		String lStrLcTypeCode=bundleConst.getString("LC.EXP_TYPE_CODE");
		
		
		
		logger.info("=======detp code :: "+lStrDeptCode);
        //logger.info("=======detp2 code :: "+bundleConst.getString(lStrDeptCode));
		lObjExpVO.setDeptCode(lStrDeptCode);
		lObjExpVO.setDistrictCode(lStrDistCode);
		lObjExpVO.setTsryCode(lStrTsryCode);
		lObjExpVO.setDdoCode(budVO.getDrwOff());
		lObjExpVO.setExpNo(budVO.getLcBudId());
		lObjExpVO.setExpTypeCode(lStrLcTypeCode);
		lStrCls ="LC.CLASS_DSS"+budVO.getClassOfExp().toString();
		lStrBud="LC.BUD_DSS"+budVO.getBudgetType().toString();
		lObjExpVO.setClsExpCode((String)bundleConst.getString(lStrCls));
		
		lStrFundType=bundleConst.getString("LC.FUND_DSS"+budVO.getFund().toString());
		
		lObjExpVO.setFundTypeCode(lStrFundType);
		lObjExpVO.setBudTypeCode((String)bundleConst.getString(lStrBud));
		//lObjExpVO.setBudTypeCode("100047");
		lObjExpVO.setDemandNo(budVO.getDemandNo());
		lObjExpVO.setScheme(budVO.getSchemeNo());
		lObjExpVO.setMjrHd(budVO.getMjrHd().toString());
		lObjExpVO.setMinHd(budVO.getMinHd().toString());
		lObjExpVO.setSubMjrHd(budVO.getSubMjrHd().toString());
		lObjExpVO.setSubHd(budVO.getSubHd().toString());
		lObjExpVO.setDtlHd(budVO.getDtlHd().toString());
		lObjExpVO.setFinYrId(new BigDecimal(budVO.getFinYearId()));	
		
		if(lStrAdvApproved.equals("0"))
		{
			lObjExpVO.setExpDt(new Date());
			lObjExpVO.setExpStatusCode(bundleConst.getString("LC.AUDIT_APPROVED"));
			lObjExpVO.setExpStatusDt(new Date());
		}
		else
		{
			lObjExpVO.setExpStatusCode(bundleConst.getString("LC.AUDIT_PENDING"));
			lObjExpVO.setExpStatusDt(new Date());
		}
		
		/*lHmpAmt = new HashMap();
		lHmpAmt.put("amount", budVO.getExpAmt().toString());
		lHmpAmt.put("calculate", "false");
		lObjExpVO.setGrossAmnt(lHmpAmt);
		//lObjExpVO.setExpStatusCode(expStatusCode);
		lHmpAmt = new HashMap();
		lHmpAmt.put("amount", budVO.getExpAmt().toString());
		lObjExpVO.setNetAmt(lHmpAmt);
		
		lHmpAmt = new HashMap();
		lHmpAmt.put("amount", budVO.getExpAmt().toString());
		lObjExpVO.setExpAmt(lHmpAmt);*/
		
		
		RptExpEdpDtls edpDssObj=new RptExpEdpDtls();
		
		edpDssObj.setAmount(budVO.getExpAmt());
		edpDssObj.setObjHd(budVO.getObjHd());
		edpDssObj.setEdpType('0');
		edpDssObj.setTrnExpEdpId(budVO.getLcBudId());
		
		ArrayList lArrLst=new ArrayList();
		lArrLst.add(edpDssObj);
		
		
		lHmpAmt = new HashMap();
		
		lHmpAmt.put("map", objArgs);
		lHmpAmt.put("RptExpenditureVO", lObjExpVO);
		lHmpAmt.put("RptExpEdpVOArrLst", lArrLst);
		
		HashMap lHmpReturnMap = lObjDSS.insertExpData(lHmpAmt);
		//--->>need to 
		logger.info("=======================return map after calling DSS insertExpData service is  :: "+lHmpReturnMap);
		
		return lHmpReturnMap;
	}
	
	public Map insertDSSDeductionData(TrnLcDeductionPosting dedVO,ServiceLocator serv,String lStrDistCode,BigDecimal lStrLCExpId,Map objArgs,String lStrAdvApproved,String lStrTsryCode)
	{
		DssDataServiceImpl lObjDSS = new DssDataServiceImpl();
		RptReceiptDtls lObjExpVO = new RptReceiptDtls();
		LcAdviceReceiptDAOImpl lObjLC = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
		HashMap lHmpEDPStruct= (HashMap)lObjLC.getEDPBudStructure(dedVO.getEdpCode());
		HashMap lHmpAmt= new HashMap();
		String lStrMjrhd="";
		String lStrSbmjrhd="";
		String lStrMinhd="";
		String lStrSbhd="";
		String lStrObjhd="";
		String lStrDtlhd="";
		lObjExpVO.setDistrictCode(lStrDistCode);
		lObjExpVO.setRcptNo(lStrLCExpId);
		String lStrLcTypeCode=bundleConst.getString("LC.EXP_TYPE_CODE");
		
		
		//lObjExpVO.setRcptTypeCode(rcptTypeCode);
		//lObjExpVO.setChallanCatgCode(challanCatgCode);
		//lObjExpVO.setFundTypeCode(fundTypeCode);
		if(lHmpEDPStruct.get("mjrhd") != null)
		{
			lStrMjrhd=lHmpEDPStruct.get("mjrhd").toString();
		}
		if(lHmpEDPStruct.get("sbmjrhd") != null)
		{
			lStrSbmjrhd=lHmpEDPStruct.get("sbmjrhd").toString();
		}
		if(lHmpEDPStruct.get("minhd") != null)
		{
			lStrMinhd=lHmpEDPStruct.get("minhd").toString();
		}
		if(lHmpEDPStruct.get("sbhd") != null)
		{
			lStrSbhd=lHmpEDPStruct.get("sbhd").toString();
		}
		if(lHmpEDPStruct.get("objhd") != null)
		{
			lStrObjhd=lHmpEDPStruct.get("objhd").toString();
		}
		if(lHmpEDPStruct.get("dtlhd") != null)
		{
			lStrDtlhd=lHmpEDPStruct.get("dtlhd").toString();
		}
	
		lObjExpVO.setMjrHd(lStrMjrhd);
		lObjExpVO.setSubMjrHd(lStrSbmjrhd);
		lObjExpVO.setMinHd(lStrMinhd);
		lObjExpVO.setSubHd(lStrSbhd);
		lObjExpVO.setObjHd(lStrObjhd);
		lObjExpVO.setObjHd(lStrDtlhd);
		lObjExpVO.setAmount(dedVO.getAmount());
		lObjExpVO.setEdpCode(dedVO.getEdpCode());
		lObjExpVO.setFinYrId(new BigDecimal(dedVO.getFinYearId()));
		lObjExpVO.setTrnReceiptId(dedVO.getLcDedId());
		lObjExpVO.setChallanCatgCode(bundleConst.getString("LC.CHALAN_CODE"));
		lObjExpVO.setRcptTypeCode(lStrLcTypeCode);
		
		lObjExpVO.setTsryCode(lStrTsryCode);
		
		if(lStrAdvApproved.equals("0"))
		{
			lObjExpVO.setRevenueDt(new Date());
			lObjExpVO.setRcptStatusCode(bundleConst.getString("LC.APPROVED"));
			lObjExpVO.setRcptStatusDate(new Date());
		}
		else
		{
			lObjExpVO.setRcptStatusCode(bundleConst.getString("LC.PENDING"));
			lObjExpVO.setRcptStatusDate(new Date());
		}
		
		ArrayList lArrDedLst = new ArrayList();
		lArrDedLst.add(lObjExpVO);
		lHmpAmt = new HashMap();
		
		lHmpAmt.put("map", objArgs);
		lHmpAmt.put("RptReceiptVOArrLst", lArrDedLst);
		
		HashMap dssReturnMap = lObjDSS.insertReceiptData(lHmpAmt);
		logger.info("=======================return map after calling DSS insertReceiptData service is  :: "+dssReturnMap);
		
		return dssReturnMap;
	}
	
}
