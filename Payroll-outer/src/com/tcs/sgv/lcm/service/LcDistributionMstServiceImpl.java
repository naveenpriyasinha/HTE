package com.tcs.sgv.lcm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.BudgetHdDtlsDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.lcm.dao.LcDistributionMstDAOImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcDistribution;
import com.tcs.sgv.lcm.valueobject.TrnLcDistributionBudHd;

public class LcDistributionMstServiceImpl 
extends ServiceImpl implements LcDistributionMstService 
{
    Log logger = LogFactory.getLog(getClass());
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
    public LcDistributionMstServiceImpl()
    {
    	logger.info("---------inside LC DistributionMst service---------");
    }
	
	public ResultObject getInitialLcDistributionMstDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info("..............Inside the getInitialLcDistributionMstDtls Method..............");		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		HttpSession session = request.getSession();
		Long lLngLangId = SessionHelper.getLangId(objectArgs);		
		String lStrLoggedLocCode=SessionHelper.getLocationCode(objectArgs);
		
		try
		{
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
			
			ArrayList lArrDistributionTypeDtls = null;			
			logger.info("---------------------------------------------------------------------");
			
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
			
			String lStrTsryLocShrtNm="";
			lStrTsryLocShrtNm=lLcDistributionDaoImpl.getTsryShortName(lStrLoggedLocCode, lLngLangId);
			logger.info("TSRY SHRT NAM-----------------"+lStrTsryLocShrtNm);
			//lStrTsryLocShrtNm="AMD";
			String lStrLcNoFormat="TRY/"+lStrTsryLocShrtNm+"/LC/%";
			String lStrMaxLcNoTsryWise="";
			String lStrLcNo="";
			Long lLngLcNo=null;
			lStrMaxLcNoTsryWise=lLcDistributionDaoImpl.getMaxLcNoTsryWise(lStrLcNoFormat,lIFinYrId);
			if(lStrMaxLcNoTsryWise.equals(""))
			{
				logger.info("-----------IN IF---------------");
				lStrLcNo="0001";
			}
			else
			{
				logger.info("-----------IN ELSE---------------"+lStrMaxLcNoTsryWise);
				StringTokenizer strToken =null;
				String lStrArr[]=lStrMaxLcNoTsryWise.split("/");
				logger.info("-----------IN ELSE---------------"+lStrArr[3]);
				long lLngNm=Long.parseLong(lStrArr[3]);
				lLngLcNo=lLngNm+1;				
				lStrLcNo=lLngLcNo.toString();
				if(lStrLcNo.length()==1)
					lStrLcNo="000"+lStrLcNo;
				if(lStrLcNo.length()==2)
					lStrLcNo="00"+lStrLcNo;
				if(lStrLcNo.length()==3)
					lStrLcNo="0"+lStrLcNo;
				
				logger.info("-----------IN ELSE---------------"+lStrLcNo);
			}
			
			//Code for Auto Generated Sequence
			if(session.getAttribute("AutoGenId") == null)
			{
				logger.info("----------IN IF- getInitialLcDistributionMstDtls----------");
				/*BptmCommonServiceImpl serviceImpl=new BptmCommonServiceImpl();
				//SeqNumServiceImpl serviceImpl = new SeqNumServiceImpl();
				long lLongLcOrdId = serviceImpl.getNextSeqNum("trn_lc_distribution", objectArgs);
				logger.info("------Auto generate ID------"+lLongLcOrdId);*/
			
				//format of LC Number
				String lStrLcOrdNo="TRY/"+lStrTsryLocShrtNm+"/LC/"+lStrLcNo;				
				
				objectArgs.put("LcOrdNo", lStrLcOrdNo);
				session.setAttribute("AutoGenId", lStrLcNo);
			}
			else
			{
				
				String lStrLcOrdNo1=session.getAttribute("AutoGenId").toString();
				String lStrLcOrdNo="TRY/"+lStrTsryLocShrtNm+"/LC/"+lStrLcOrdNo1;	
				
				logger.info("----------IN ELSE-----------"+lStrLcOrdNo);
				objectArgs.put("LcOrdNo", lStrLcOrdNo);
			}
			// Distribution DAO Impl Method to get Distribution Type
			lArrDistributionTypeDtls = (ArrayList)lLcDistributionDaoImpl.getDistributionType(lLngLangId);
			
			objectArgs.put("lArrDistributionTypeDtls", lArrDistributionTypeDtls);			
			logger.info("---LC lArrDistributionTypeDtls : ------"+lArrDistributionTypeDtls.toString());	
			
			
			
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcDistributionMst");
			
			logger.info("-------end of  getInitialLcDistributionMstDtls Method---------");
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}
	
	public ResultObject getDivisionNmAndBalAmt(Map objectArgs)
	{
		logger.info("..............Inside the getDivisionNmAndBalAmt Method..............");		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");		
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
			LcDivisionInformationVO divVO=null;
			
			Long lLngLangId = SessionHelper.getLangId(objectArgs);
			String lStrLoggedLocCode=SessionHelper.getLocationCode(objectArgs);
			
			CommonVO cmnVO = null;	
			
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
			
			String lStrDivCode ="";
			String lStrLocCode="";
			
			String lStrBalAmt="0";
			String lStrDivNm="";
			String lStrDivId="";
			
			//getting the Division code as parameter
			if(!StringUtility.getParameter("txtDivCode", request).equalsIgnoreCase(""))
			{	
				lStrDivCode= StringUtility.getParameter("txtDivCode", request);
				lStrDivCode=lStrDivCode.trim();
				logger.info("-----	lStrDivCode------"+lStrDivCode); 
				
				lStrLocCode=lLcDistributionDaoImpl.getLocationCodeByTsryCodeAndDivCode(lStrLoggedLocCode, lStrDivCode, lLngLangId);
				logger.info("-----	lStrLocCode------"+lStrLocCode); 
			    
			    //calling of Distribution DAo Method to get Div Name,Id and Latest Balance Amt
			    cmnVO = (CommonVO)lLcDistributionDaoImpl.getDivisionNmAndBalAmt(lStrLocCode,lLngLangId,lIFinYrId);
			    
			    lStrBalAmt=cmnVO.getCommonId();
				String lStrDivDesc=cmnVO.getCommonDesc();						
				
				String [] lStrTemp = lStrDivDesc.split("@");
				
				lStrDivNm=lStrTemp[1].toString();
				lStrDivId=lStrTemp[0].toString();
				
				logger.info("-----	cmnVO Div Id------"+lStrDivId); 
				logger.info("-----	cmnVO Div Nm-----"+lStrDivNm); 
				logger.info("-----	cmnVO Amt------"+lStrBalAmt); 
				
				lStrDivNm=lStrDivNm.replaceAll("&", "n");
				
				//*********** Logic for get Division Head Structure ************
				
				 divVO=(LcDivisionInformationVO)lLcDistributionDaoImpl.getDivisionAccountHdStructure(lStrLocCode);
				 logger.info("----------DIVISION HD STRUCTURE---------"+divVO);
				 
				//**************************************************************
			}			
			
		   String lStrAjaxString="";			 
			
		   lStrAjaxString = new AjaxXmlBuilder().addItemAsCData("txtDivisionName",lStrDivNm).addItemAsCData("txtLcBalanceAmt",lStrBalAmt).addItemAsCData("txtProgressiveAmt",lStrBalAmt).addItemAsCData("txtDivisionId",lStrDivId)
												.addItemAsCData("txtDivMjrHd",divVO.getDepartmentCode())
												.addItemAsCData("txtDivSubMjrHd",divVO.getDepartment_name())
												.addItemAsCData("txtDivMinHd",divVO.getDistrictCode())
												.addItemAsCData("txtDivSubHd",divVO.getDistrict_name())
												.addItemAsCData("txtDivDtlHd",divVO.getDivisionId())
												.addItemAsCData("txtDivObjHd",divVO.getDivision_name()).toString();			 
					 
			logger.info("-----	lStrAjaxString------"+lStrAjaxString); 
			 
			objectArgs.put("ajaxKey", lStrAjaxString);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(objectArgs);		 
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}
	
	public ResultObject insertLcDistributionMst(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info(".........Inside the insertLcDistributionMst Method..............");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
			
			Long lLngLangId = SessionHelper.getLangId(objectArgs);
			HttpSession session = request.getSession();
			String lStrLoggedLocCode=SessionHelper.getLocationCode(objectArgs);
			Long lLngTrnCntr=lLcDistributionDaoImpl.getTrnCntr();
			TrnLcDistribution lObjLcdistributionVo = null; // VO of Distribution Mst
			TrnLcDistributionBudHd lcDistributionBudHdVO = null;//VO of DistributionBudHD Mst
			
			ArrayList lArrDistributionBudHd=null;
			String lStrSessionVar="false";
			
			if(objectArgs.get("LcdistributionVo") != null)
			{
				lObjLcdistributionVo=(TrnLcDistribution)objectArgs.get("LcdistributionVo");
				lObjLcdistributionVo.setTrnCntr(new BigDecimal(lLngTrnCntr));
				logger.info("-------DISTRIBUTION VO in SERV--------"+lObjLcdistributionVo.toString());
			}
			
			//Save the data in LcDistributionMst table by calling Method of DAO Impl
			boolean lbResult=false;
			if(lObjLcdistributionVo != null)
				lbResult=lLcDistributionDaoImpl.saveLcDistributionMst(lObjLcdistributionVo);
			logger.info("-------RESULT--------"+lbResult);
			
			if(objectArgs.get("lArrDistributionBudHd") != null)
			{
				lArrDistributionBudHd=(ArrayList)objectArgs.get("lArrDistributionBudHd");
				logger.info("-----SIZE OF ARRLIST IN SERV--------"+lArrDistributionBudHd.size());
			}
			//save the data in LcDistributionBudHd table by Hibernate code Generation
			for(int i=0;i<lArrDistributionBudHd.size();i++)
			{
				lcDistributionBudHdVO = new TrnLcDistributionBudHd();
				
				lcDistributionBudHdVO = (TrnLcDistributionBudHd)lArrDistributionBudHd.get(i);
				logger.info("-------DISTRIBUTION_BUDHD VO in SERV--------"+lcDistributionBudHdVO.toString());
				if(lcDistributionBudHdVO != null)
				{
				    lLcDistributionDaoImpl.create(lcDistributionBudHdVO);
				    request.setAttribute("distributionResult", "true");
				}
				else
				{
					logger.info("-------IN ELSE PART OF distributionResult--------");
					request.setAttribute("distributionResult", "false");
				}
				session.removeAttribute("AutoGenId");
			}			
			
			//---CODE TO REFRESH THE PAGE--------------------
			//Code for Auto Generated Sequence
			
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
			
			String lStrTsryLocShrtNm="";
			lStrTsryLocShrtNm=lLcDistributionDaoImpl.getTsryShortName(lStrLoggedLocCode, lLngLangId);
			logger.info("TSRY SHRT NAM-----------------"+lStrTsryLocShrtNm);
			String lStrMaxLcNoTsryWise="";
			String lStrLcNo="";
			Long lLngLcNo=null;
			String lStrLcNoFormat="TRY/"+lStrTsryLocShrtNm+"/LC/%";
			lStrMaxLcNoTsryWise=lLcDistributionDaoImpl.getMaxLcNoTsryWise(lStrLcNoFormat,lIFinYrId);
			if(lStrMaxLcNoTsryWise.equals(""))
			{
				logger.info("-----------IN IF---------------");
				lStrLcNo="0002";
			}
			else
			{
				logger.info("-----------IN ELSE---------------"+lStrMaxLcNoTsryWise);
				StringTokenizer strToken =null;
				String lStrArr[]=lStrMaxLcNoTsryWise.split("/");
				logger.info("-----------IN ELSE---------------"+lStrArr[3]);
				long lLngNm=Long.parseLong(lStrArr[3]);
				lLngLcNo=lLngNm+2;				
				lStrLcNo=lLngLcNo.toString();
				if(lStrLcNo.length()==1)
					lStrLcNo="000"+lStrLcNo;
				if(lStrLcNo.length()==2)
					lStrLcNo="00"+lStrLcNo;
				if(lStrLcNo.length()==3)
					lStrLcNo="0"+lStrLcNo;
				
				logger.info("-----------IN ELSE---------------"+lStrLcNo);
			}
			
		    if(session.getAttribute("AutoGenId") == null)
		    {		    	
		    	logger.info("-----------IN INSERT AT AUTO GENERATE--------------");
		    	/*BptmCommonServiceImpl serviceImpl=new BptmCommonServiceImpl();
				//SeqNumServiceImpl serviceImpl = new SeqNumServiceImpl();
				long lLongLcOrdId = serviceImpl.getNextSeqNum("trn_lc_distribution", objectArgs);
				logger.info("------Auto generate ID------"+lLongLcOrdId);*/
				session.setAttribute("AutoGenId", lStrLcNo);
				
				//format of LC Number
				String lStrLcOrdNo="TRY/"+lStrTsryLocShrtNm+"/LC/"+lStrLcNo;	
				objectArgs.put("LcOrdNo", lStrLcOrdNo);
		    }
		    else
		    {
		    	long lLongLcOrdId=Long.parseLong(session.getAttribute("AutoGenId").toString());
		    	String lStrLcOrdNo="TRY/"+lStrTsryLocShrtNm+"/LC/"+lLongLcOrdId;
				objectArgs.put("LcOrdNo", lStrLcOrdNo);
		    }
			// Distribution DAO Impl Method to get Distribution Type
			ArrayList lArrDistributionTypeDtls =null;
			lArrDistributionTypeDtls = (ArrayList)lLcDistributionDaoImpl.getDistributionType(lLngLangId);
			
			objectArgs.put("lArrDistributionTypeDtls", lArrDistributionTypeDtls);			
			logger.info("---LC lArrDistributionTypeDtls : ------"+lArrDistributionTypeDtls.toString());	
			
			
			
			//---------END-----------------------------------
			
			
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcDistributionMst");
			
			
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}
	
	public ResultObject getUnderCodeDescription(Map objectArgs)
	{
		logger.info("..............Inside the getUnderCodeDescription Method..............");		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");		
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
			
			long lLngLangId = SessionHelper.getLangId(objectArgs);
			String lStrLoggedLocCode=SessionHelper.getLocationCode(objectArgs);
			
			CommonVO cmnVO = null;			
				
			String lStrDivCode ="";
			String lStrLocCode="";
			String lStrUndCode="";			
			
			String lStrUndDesc="";			
			
			//getting the Division code as parameter
			if(!StringUtility.getParameter("txtDivCode", request).equalsIgnoreCase(""))
			{	
				lStrDivCode= StringUtility.getParameter("txtDivCode", request);
				lStrDivCode=lStrDivCode.trim();
				logger.info("-----	lStrDivCode------"+lStrDivCode); 
				
				lStrUndCode=StringUtility.getParameter("txtSenderCode", request);
				lStrUndCode=lStrUndCode.trim();
				
				lStrLocCode=lLcDistributionDaoImpl.getLocationCodeByTsryCodeAndDivCode(lStrLoggedLocCode, lStrDivCode, lLngLangId);
				logger.info("-----	lStrLocCode------"+lStrLocCode); 
			    
			    //calling of Distribution DAo Method to get Div Name,Id and Latest Balance Amt
				lStrUndDesc = (String)lLcDistributionDaoImpl.getUnderCodeDescription(lStrLocCode,lStrUndCode,lLngLangId);
			    
				logger.info("-----	lStrUndDesc------"+lStrUndDesc);				
				
			}			
			
			String lStrAjaxString="";			 
			
			lStrAjaxString = new AjaxXmlBuilder().addItemAsCData("txtSenderCodeDesc",lStrUndDesc).toString();			 
					 
			logger.info("-----	lStrAjaxString------"+lStrAjaxString); 
			 
			objectArgs.put("ajaxKey", lStrAjaxString);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(objectArgs);		 
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}
	
	public ResultObject verifyDistributionHdStruct(Map objectArgs)
	{
		logger.info("..............Inside the verifyDistributionHdStruct Method..............");		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");		
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
			//System.out.println("------FINYEAR ID---------"+lIFinYrId);
			
			LcDistributionMstDAOImpl lLcDistributionDaoImpl = new LcDistributionMstDAOImpl(TrnLcDistributionBudHd.class,serv.getSessionFactory());
			BudgetHdDtlsDAOImpl budDaoObj=new BudgetHdDtlsDAOImpl(lIFinYrId,serv.getSessionFactory());
			
			
			long lLngLangId = SessionHelper.getLangId(objectArgs);
			String lStrLoggedLocCode=SessionHelper.getLocationCode(objectArgs);
				
			String lStrDemandNo ="";
			String lStrMjrHd="";
			String lStrSubMjrHd="";	
			String lStrMinHd="";		
			String lStrSubHd="";		
			String lStrDtlHd="";		
			String lStrObjHd="";		
			String lStrSchemeNo="";
				
			String lStrMjrHdType="";			
			
			//getting the Division code as parameter for distribution head validation
			if(!StringUtility.getParameter("txtDemandNo", request).equalsIgnoreCase(""))
			{	
				lStrDemandNo= StringUtility.getParameter("txtDemandNo", request);				
				lStrDemandNo=lStrDemandNo.trim();
				lStrMjrHd= StringUtility.getParameter("txtMjrHd", request);				
				lStrMjrHd=lStrMjrHd.trim();
				lStrSubMjrHd= StringUtility.getParameter("txtSubMjrHd", request);				
				lStrSubMjrHd=lStrSubMjrHd.trim();
				lStrMinHd= StringUtility.getParameter("txtMinHd", request);				
				lStrMinHd=lStrMinHd.trim();
				lStrSubHd= StringUtility.getParameter("txtSubHd", request);				
				lStrSubHd=lStrSubHd.trim();
				lStrDtlHd= StringUtility.getParameter("txtDtlHd", request);				
				lStrDtlHd=lStrDtlHd.trim();
				lStrObjHd= StringUtility.getParameter("txtObjHd", request);				
				lStrObjHd=lStrObjHd.trim();
				lStrSchemeNo= StringUtility.getParameter("txtSchemeNo", request);				
				lStrSchemeNo=lStrSchemeNo.trim();
			}
			else if(!StringUtility.getParameter("txtMjrHd", request).equalsIgnoreCase(""))
			{
				lStrMjrHd= StringUtility.getParameter("txtMjrHd", request);				
				lStrMjrHd=lStrMjrHd.trim();
				lStrSubMjrHd= StringUtility.getParameter("txtSubMjrHd", request);				
				lStrSubMjrHd=lStrSubMjrHd.trim();
				lStrMinHd= StringUtility.getParameter("txtMinHd", request);				
				lStrMinHd=lStrMinHd.trim();
				lStrSubHd= StringUtility.getParameter("txtSubHd", request);				
				lStrSubHd=lStrSubHd.trim();
				lStrDtlHd= StringUtility.getParameter("txtDtlHd", request);				
				lStrDtlHd=lStrDtlHd.trim();
				lStrObjHd= StringUtility.getParameter("txtObjHd", request);	
			}
				//System.out.println("-----	lStrDemandNo------"+lStrDemandNo); 
				//System.out.println("-----	lStrMjrHd------"+lStrMjrHd); 
				//System.out.println("-----	lStrSubMjrHd------"+lStrSubMjrHd); 
				//System.out.println("-----	lStrMinHd------"+lStrMinHd); 
				//System.out.println("-----	lStrSubHd------"+lStrSubHd); 
				//System.out.println("-----	lStrDtlHd------"+lStrDtlHd); 
				//System.out.println("-----	lStrObjHd------"+lStrObjHd); 
				//System.out.println("-----	lStrSchemeNo------"+lStrSchemeNo); 
			    
			    //calling of Distribution DAo Method to get Div Name,Id and Latest Balance Amt
				boolean validHdResult=budDaoObj.isValidBudgetHeads(lStrDemandNo, lStrMjrHd, lStrSubMjrHd, lStrMinHd, lStrSubHd, lStrDtlHd, lLngLangId,lStrMjrHdType);
				//lStrUndDesc = (String)lLcDistributionDaoImpl.getUnderCodeDescription(lStrLocCode,lStrUndCode,lLngLangId);
			    //System.out.println("----HEAD STR RESULT------"+validHdResult);
				String budHdValid="";
			    if(validHdResult==true)
			      budHdValid="true";
			    else
			    	budHdValid="false";
			    
			    String lStrAjaxString="";			 
			    StringBuffer lStrBuff =new StringBuffer();
				lStrBuff.append("<budHdValidation>");
				lStrBuff.append(budHdValid);
				lStrBuff.append("</budHdValidation>");
				
				lStrAjaxString = new AjaxXmlBuilder().addItem("ajax_key",lStrBuff.toString()).toString();
				
				//System.out.println("-----	lStrAjaxString------"+lStrAjaxString); 
				 
				objectArgs.put("ajaxKey", lStrAjaxString);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(objectArgs);	
				
			
			
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}

}
