package com.tcs.sgv.lcm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.lcm.dao.LcDivisionAccMstDAOImpl;
import com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl;
import com.tcs.sgv.lcm.valueobject.LcAccMstVO;
import com.tcs.sgv.lcm.valueobject.LcDivisionDtlsVO;
import com.tcs.sgv.lcm.valueobject.MstLcDivisionAccount;
import com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting;



public class LcDivisionAccMstServiceImpl 
extends ServiceImpl implements LcDivisionAccMstService 
{
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	
	public ResultObject getInitialLcAccMstDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info("..............Inside the lcDivisionAccMstServiceImpl..............");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			LcDivisionAccMstDAOImpl lLcDivAccDaoImpl = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
			
			Long lLngLangId = SessionHelper.getLangId(objectArgs);			
			String lStrLocId = SessionHelper.getLocationCode(objectArgs);
			logger.info("Loc code of logged in user is :: "+ lStrLocId);
			HashMap lHmpDist = new HashMap();
			String lStrDistrict="";
			
			ArrayList lArrDeptDtls = new ArrayList();
			//ArrayList lArrDistDtls = new ArrayList(); 
			ArrayList lArrBankDtls = new ArrayList();
			
			
			
			String lStrDeptLookupId= (bundleConst.getString("LC_DEPT_LOOKUP_ID"));
			String lStrStateId=(bundleConst.getString("LC_STATE_ID"));
			
			lArrDeptDtls = (ArrayList)lLcDivAccDaoImpl.getDepartmentLookup(lStrDeptLookupId, lLngLangId);	
			logger.info("----------------------- LC Dept Array List : ------"+lArrDeptDtls.toString());			
			
			lHmpDist = (HashMap)lLcDivAccDaoImpl.getDistrictDtls(lStrLocId,lLngLangId);
			lStrDistrict=(String)lHmpDist.get("districtname");
			
			logger.info("----------------------- LC Logged in district : ------"+lStrDistrict);
			
			lArrBankDtls = (ArrayList)lLcDivAccDaoImpl.getBankDtls(lLngLangId);
			logger.info("----------------------- LC Bank Array List : ------"+lArrBankDtls.toString());
			
			request.setAttribute("districtname", lStrDistrict);
			
			objectArgs.put("lArrDeptDtls", lArrDeptDtls);
			objectArgs.put("lArrBankDtls", lArrBankDtls);
			objectArgs.put("districtname", lStrDistrict);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcAccMst");
			logger.info("------------------end of lcDivisionAccMstServiceImpl-----------------------");
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}
	
	public ResultObject getDivisions(Map objectArgs)
	{
		logger.info("...................Inside the getDivisionName..............");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");		
		//System.out.println("-------service 1----------");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			//System.out.println("-------service 2----------");
			LcDivisionAccMstDAOImpl lLcDivAccDaoImpl = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
			String lStrLocCode = SessionHelper.getLocationCode(objectArgs);
			Long lLngLangId = SessionHelper.getLangId(objectArgs);
			
			//System.out.println("-------service 3----------");
			ArrayList lArrDivDtls = null;
			
			String lStrDeptId = StringUtility.getParameter("id_dept", request);
			logger.info("-----	id_dept in getDivisionName------"+lStrDeptId); 
			//System.out.println("-------service 4----------");
		
			if(!(lStrDeptId.equals("0")))
			{
				//System.out.println("-------service 5----------");
				lArrDivDtls = new ArrayList();
				lArrDivDtls = (ArrayList)lLcDivAccDaoImpl.getDivisionName(lStrDeptId, lLngLangId,lStrLocCode);
				//System.out.println("-------service 6----------");
				ArrayList lArrDivNames = new ArrayList();
				/*for(int i=0;i<lArrDivDtls.size();i++)
				{
					CommonVO lCmnVO= new CommonVO();
					lArrDivNames.add(lCmnVO.getCommonDesc());
				}*/
				 //System.out.println("-------service 7----------");
				 String lStrAjaxString = new AjaxXmlBuilder().addItems(lArrDivDtls,"commonDesc","commonId").toString();
				 //new AjaxXmlBuilder().
				 //System.out.println("-------service 8 ajax string ----------"+lStrAjaxString);
				 logger.info(lStrAjaxString);				
				 logger.info("Value of Ajax String in getDivisionName-"+lStrAjaxString);
				
				 objectArgs.put("ajaxKey", lStrAjaxString);
				 objectArgs.put("id_division",lArrDivDtls);
				 objRes.setResultValue(objectArgs);
				 objRes.setViewName("ajaxData");
				
				/*objectArgs.put("lArrDivDtls", lArrDivDtls);
				request.setAttribute("lArrDivDtls", lArrDivDtls);
				logger.info("----------------------- LC division Array List : ------"+lArrDivDtls.toString());
				
				objRes.setResultValue(objectArgs);
				objRes.setViewName("lcAccMst");
				*/
			}
			else
			{

				logger.info("In else part of  in getDivisionName-");
				lArrDivDtls = new ArrayList();
				lArrDivDtls.add("--select--");
				String lStrAjaxString = new AjaxXmlBuilder().addItemAsCData("--select--","0").toString();
				 objectArgs.put("ajaxKey", lStrAjaxString);
				 objectArgs.put("id_division",lArrDivDtls);
				 objRes.setResultValue(objectArgs);
				 objRes.setViewName("ajaxData");
			}
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}
	
	public ResultObject getDivisionDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info("Inside the getDivisionDtls..............");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			LcDivisionAccMstDAOImpl lLcDivAccDaoImpl = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
			
			Long lLngLangId = SessionHelper.getLangId(objectArgs);			
			
			ArrayList lArrDivDtls = new ArrayList();
			String lStrDivCode="";
			
			if(!(StringUtility.getParameter("id_division", request).equals("0")))
			{
				lStrDivCode = StringUtility.getParameter("id_division", request);
				
				logger.info("Div Code is :  in getDivisionDtls-"+ lStrDivCode);
				lArrDivDtls = (ArrayList)lLcDivAccDaoImpl.getDivisionDtls(lStrDivCode,lLngLangId);
				LcDivisionDtlsVO lObjDivDtlVO = (LcDivisionDtlsVO)lArrDivDtls.get(0);
				
				String tempResult = "";
				if(lObjDivDtlVO != null)
				{
					tempResult= new AjaxXmlBuilder().addItemAsCData("txtDivCode",lObjDivDtlVO.getDivisionCode()).addItemAsCData("txtAddress1",lObjDivDtlVO.getAdd1()).addItemAsCData("txtAddress2",lObjDivDtlVO.getAdd2()).addItemAsCData("txtPin",lObjDivDtlVO.getPinCode()).toString();
				}
				logger.info("---------Ajax String  in getDivisionDtls "+tempResult);
				
				objectArgs.put("ajaxKey",tempResult);
				objRes.setViewName("ajaxData");
				objRes.setResultValue(objectArgs);
		 }
			else
			{
				logger.info("in else part of  in getDivisionDtls ");
				String tempResult = "";
				tempResult= new AjaxXmlBuilder().addItemAsCData("txtAddress1","-").addItemAsCData("txtAddress2","-").addItemAsCData("txtPin","-").toString();
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
	
	public ResultObject getBankBranchDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		logger.info("Inside the getBankBranchDtls..............");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			LcDivisionAccMstDAOImpl lLcDivAccDaoImpl = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
			HttpSession hs = request.getSession();
			String lStrBankCode="";
			ArrayList lArrBankBranchDtls = new ArrayList();
			
			if (!StringUtility.getParameter("id_bank",request).equals("")) {
				lStrBankCode =(StringUtility.getParameter("id_bank",request));
			}			 
			
			lArrBankBranchDtls = (ArrayList)lLcDivAccDaoImpl.getBankBranchDtls(lStrBankCode);
		     String lStrAjaxString = new AjaxXmlBuilder().addItems(lArrBankBranchDtls,"commonDesc","commonId").toString();
			 objectArgs.put("ajaxKey", lStrAjaxString);
			 objectArgs.put("id_branch",lArrBankBranchDtls);
			 request.setAttribute("id_branch", lArrBankBranchDtls);
			 objRes.setResultValue(objectArgs);
			 objRes.setViewName("ajaxData");
		}
		catch(Exception e)
		{
			logger.info("Error: ");
			e.printStackTrace();
		}
		return objRes;
	}
	
	public ResultObject insertLcAccDtls(Map objectArgs)
	{
		logger.info("---------Inside the insertLcAccDtls-------------------");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		try
		{
			logger.info("---------inside try block----------");	
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			String lStrLocCode = SessionHelper.getLocationCode(objectArgs);
			logger.info("--------before calling DAO constructor---------");
			LcDivisionAccMstDAOImpl lLcDivAccDaoImpl = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
			
			logger.info("-------before getting lObjMstLcAccVO from request");		
			MstLcDivisionAccount lObjMstLcAccVO = (MstLcDivisionAccount)objectArgs.get("lObjLcAccMstVo");
			String lStrLocationCode =(String)objectArgs.get("lStrLocationCode");
			String lStrAdd1 =(String)objectArgs.get("lStrAdd1");
			String lStrAdd2 =(String)objectArgs.get("lStrAdd2");
			String lStrPin = (String)objectArgs.get("lStrPin");
			HashMap lMpBudMap = (HashMap)objectArgs.get("budgetMap");
			
			logger.info("-------after getting lObjMstLcAccVO from request");
			
			Long lLngLangId = SessionHelper.getLangId(objectArgs);
			String lStrLangId = lLngLangId.toString();
			int lILangId = Integer.parseInt(lStrLangId);
			
			String lStrDivCode=(lObjMstLcAccVO.getDivisionCode().toString());
			logger.info("---------INSIDE SRVC DIVISION lStrDivCode----------------"+lStrDivCode); 
			String lStrAccResult=lLcDivAccDaoImpl.verifyExistingAccout(lStrDivCode);
			logger.info("---------INSIDE SRVC ACCOUNT EXISTING RESULT-------"+lStrAccResult);
			
			if(lStrAccResult.equals(""))
			{
				logger.info("---------INSIDE SRVC IN IF----------------"); 
				lLcDivAccDaoImpl.create(lObjMstLcAccVO);
			//	String lStrdistCode = (lLcDivAccDaoImpl.getDistrictDtls(lStrLocCode, lILangId)).get("districtid").toString();//getting from return hashmap
				//lLcDivAccDaoImpl.updateLocation(lStrDivCode,lStrdistCode,lStrLocationCode,lStrAdd1,lStrAdd2,lStrPin);
			    request.setAttribute("Result","true");
			    request.setAttribute("AccNo",lObjMstLcAccVO.getLcAccNo().toString());
			  //  lLcDivAccDaoImpl.insertAccData(lObjMstLcAccVO,(String)objectArgs.get("lStrLocationCode"),(String)objectArgs.get("lStrLocationCode"),lStrdistCode,lStrDivCode,(String)objectArgs.get("lStrDept"));
			}
			else
			{
				logger.info("---------INSIDE SRVC IN ELSE----------------"); 
				request.setAttribute("Result","false");
				request.setAttribute("AccNo",lStrAccResult);
			}
			
			//--------Refresh the Page-----------------------
			
			ArrayList lArrDeptDtls = new ArrayList();
			ArrayList lArrDistDtls = new ArrayList();
			ArrayList lArrBankDtls = new ArrayList();
			
			String lStrDeptLookupId= (bundleConst.getString("LC_DEPT_LOOKUP_ID"));
			String lStrStateId=(bundleConst.getString("LC_STATE_ID"));
			String lStrLocId = SessionHelper.getLocationCode(objectArgs);
			HashMap lHmpDist = new HashMap();
			String lStrDistrict="";
			logger.info("Loc code of logged in user is :: "+ lStrLocId);
			lArrDeptDtls = (ArrayList)lLcDivAccDaoImpl.getDepartmentLookup(lStrDeptLookupId, lLngLangId);	
			logger.info("----------------------- LC Dept Array List : ------"+lArrDeptDtls.toString());			
			
			lHmpDist = (HashMap)lLcDivAccDaoImpl.getDistrictDtls(lStrLocId,lLngLangId);
			logger.info("----------------------- LC Dist Array List : ------"+lArrDistDtls.toString());
			
			lStrDistrict=(String)lHmpDist.get("districtname");
			lArrBankDtls = (ArrayList)lLcDivAccDaoImpl.getBankDtls(lLngLangId);
			logger.info("----------------------- LC Bank Array List : ------"+lArrBankDtls.toString());
			
			objectArgs.put("districtname", lStrDistrict);
			objectArgs.put("lArrDeptDtls", lArrDeptDtls);
			objectArgs.put("lArrBankDtls", lArrBankDtls);
			//-----------------------------------------------
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcAccMst");
			logger.info("-------Returning from service...");
			
		}
		catch(Exception e)
		{
			logger.info("Error: "  + e);
		}
		return objRes;
	}
	
	public ResultObject getAccDetails(Map objectArgs)
	{
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrLangId=String.valueOf(SessionHelper.getLangId(objectArgs));
		Long lLngPostId = SessionHelper.getPostId(objectArgs);
		ArrayList lArrAccInfo = new ArrayList();
		
		try
		{
			logger.info("---------inside try block----------");	
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			LCReportQueryDAOImpl lObjRptQryDao = new LCReportQueryDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			LcDivisionAccMstDAOImpl lObjAccMst = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
			
			ArrayList lArrDivList =lObjRptQryDao.getDivisionsOfLoginTsry(lStrLangId, lLngPostId);
			ArrayList lArrDivisions = new ArrayList();
			//System.out.println("No of divs under treasury is :: "+lArrDivList.size());
			
			for(int i=0;i<lArrDivList.size();i++)
			{
				lArrDivisions.add(((ComboValuesVO)lArrDivList.get(i)).getId());
			}
			
			
			lArrAccInfo=(ArrayList)lObjAccMst.getDivAccInfo(lArrDivisions);
			
			objectArgs.put("AccList", lArrAccInfo);
			request.setAttribute("AccList", lArrAccInfo);
			//-----------------------------------------------
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcAccMstDtls");
			
			logger.info("-------Returning from service...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return objRes;
	}
	
	public ResultObject updateLcAccount(Map objectArgs)
	{
		logger.info("================ Inside Update Account Service method ::::===============");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Long lLngLangId = SessionHelper.getLangId(objectArgs);
		
		LcDivisionAccMstDAOImpl lObjAccMst = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
		Long lLngAccNo = new Long(0);
		LcAccMstVO lObjVO = new LcAccMstVO();
		
		ArrayList lArrBankDtls = (ArrayList)lObjAccMst.getBankDtls(lLngLangId);
		
		logger.info("#################  Getting parameter Account No from jsp is ::: "+request.getParameter("accNo"));
		
		try
		{
			if(request.getParameter("accNo") != null)
			{
				lLngAccNo = Long.parseLong(((request.getParameter("accNo").toString())));
			}
			else
			{
				throw new Exception(" Acount Numder is Null from request Object ");
			}
			lObjVO=lObjAccMst.getUpdateAccInfo(lLngAccNo);
			logger.info("====================== came from DAO into service with Update Account Data");
			
			request.setAttribute("AccVO", lObjVO);
			objectArgs.put("AccVO", lObjVO);
			objectArgs.put("lArrBankDtls", lArrBankDtls);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lcAccMstUpdt");
			
		}
		catch(Exception e) 
		{
			logger.error("!!!! Error  in Account master Service Update method !!!!", e);
			logger.equals(e.toString());
			e.printStackTrace();
		}
		
		return objRes;
	}
	
	public ResultObject getUnderCodes(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			//System.out.println("-------service 2----------");
			LcDivisionAccMstDAOImpl lLcDivAccDaoImpl = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
			String lStrLocCode = SessionHelper.getLocationCode(objectArgs);
			Long lLngLangId = SessionHelper.getLangId(objectArgs);
			
			//System.out.println("-------service 3----------");
			ArrayList lArrUnercodeDtls = null;
			
			String lStrDeptId = StringUtility.getParameter("id_dept", request);
			logger.info("-----	id_dept in getDivisionName------"+lStrDeptId); 
			//System.out.println("-------service 4----------");
		
			if(!(lStrDeptId.equals("0")))
			{
				//System.out.println("-------service 5----------");
				lArrUnercodeDtls = new ArrayList();
				lArrUnercodeDtls = (ArrayList)lLcDivAccDaoImpl.getUnderCodes(lStrDeptId, lLngLangId,lStrLocCode);
				//System.out.println("-------service 6----------");
				ArrayList lArrDivNames = new ArrayList();
				
				String lStrAjaxString = new AjaxXmlBuilder().addItems(lArrUnercodeDtls,"commonDesc","commonId").toString();
				 //new AjaxXmlBuilder().
				 //System.out.println("-------service 8 ajax string ----------"+lStrAjaxString);
				 logger.info(lStrAjaxString);				
				 logger.info("Value of Ajax String in getDivisionName-"+lStrAjaxString);
				
				 objectArgs.put("ajaxKey", lStrAjaxString);
				 objectArgs.put("txtUnderCode",lArrUnercodeDtls);
				 objRes.setResultValue(objectArgs);
				 objRes.setViewName("ajaxData");
			}
			else
			{

				logger.info("In else part of  in getDivisionName-");
				lArrUnercodeDtls = new ArrayList();
				lArrUnercodeDtls.add("--select--");
				String lStrAjaxString = new AjaxXmlBuilder().addItemAsCData("--select--","0").toString();
				 objectArgs.put("ajaxKey", lStrAjaxString);
				 objectArgs.put("id_division",lArrUnercodeDtls);
				 objRes.setResultValue(objectArgs);
				 objRes.setViewName("ajaxData");
			}
		}
		catch(Exception e)
		{
			logger.error("Error ::", e);
			e.printStackTrace();
		}
		return objRes;
	}
	
	
}


