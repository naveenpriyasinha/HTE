package com.tcs.sgv.dss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.apps.dao.budget.BudFinYrDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.dao.DSSQueryDAOImpl;
public class dssServiceImpl extends ServiceImpl {

	ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/dss/DSSConstants");
	private static final Logger glogger=Logger.getLogger(dssServiceImpl.class);
	
	public ResultObject getOptions(Map inputMap)
	{
		glogger.info("------------------------------------------------in the service------------");
		glogger.info("----------------------- in the option method ----------");
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		CmnLookupMstDAOImpl cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());	
		List l_exp_rec  = (List)cmnDAO.getAllChildren("exp_rec");
		glogger.info(((List)cmnDAO.getAllChildren("exp_rec")));
		glogger.info("List size is ---------"+l_exp_rec.size());
		glogger.info("List size for prefixes :: " +l_exp_rec.toString());
		inputMap.put("exp_rec",l_exp_rec);
		
	    cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());	
		List loptionDate  = cmnDAO.getAllChildren("optionDuration");
		glogger.info("List size for prefixes :: " +loptionDate.toString());
		inputMap.put("optionDate",loptionDate);
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		objRes.setResultValue(inputMap);
		objRes.setViewName("topNTreasuryRpt");		
		glogger.info("Before leaving method----");
		return objRes;
		
	}
	
	public ResultObject getDDO(Map inputMap)
	{
		glogger.info("------------------------------------------------in the service------------");
		glogger.info("----------------------- in the getDDO method ----------");
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		Long lLngLocId = SessionHelper.getLocationId(inputMap);
		String lstrLocId = "LC1";
		
		
		ArrayList lFinYear=new ArrayList();
		ArrayList lArrLocation = null;
		DSSQueryDAOImpl lObjRptQryDAOImpl = new DSSQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
		String lStrLangId= lObjRptQryDAOImpl.getStrLangId(lLngLangId);
		lArrLocation=lObjRptQryDAOImpl.getLocation(lStrLangId, lstrLocId);
		inputMap.put("location", lArrLocation);
		ArrayList lArrDDO = null;
		lObjRptQryDAOImpl = new DSSQueryDAOImpl();
		lStrLangId= lObjRptQryDAOImpl.getStrLangId(lLngLangId);
		lArrDDO = lObjRptQryDAOImpl.getDDO(lStrLangId, lstrLocId,lObjRsrcBndle.getString("default"));
		inputMap.put("DDO",lArrDDO);
		
		BudFinYrDAOImpl finDAO= new BudFinYrDAOImpl();
		try
		{
			 lFinYear=finDAO.getFinanceYr(lStrLangId, "LC1");
			 glogger.info("the finyear list is "+ lFinYear);
		}catch(Exception e)
		{
			glogger.info("Exception is:"+e.getMessage());
		}
		inputMap.put("finYear",lFinYear);
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		objRes.setResultValue(inputMap);
		objRes.setViewName("DDORpt");		
		glogger.info("Before leaving method----");
		return objRes;
	}
	
	public ResultObject getAJDDO(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		glogger.info("Inside the getDivisionDtls..............");
		
		try
		{
				HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj"); 
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				DSSQueryDAOImpl lObjRptQryDAOImpl = new DSSQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
				Long lLngLangId = SessionHelper.getLangId(objectArgs);
				Long lLngLocId = SessionHelper.getLocationId(objectArgs);
				String lStrLangId= lObjRptQryDAOImpl.getStrLangId(lLngLangId);
				HttpSession hs = request.getSession();
				ArrayList lArrDivDtls = new ArrayList();
				int lIDivId=0;
				String lstrLocId = "LC1";
				String str = (StringUtility.getParameter("locId", request));
				
					glogger.info("loc id is -"+ str);
					ComboValuesVO vo= new ComboValuesVO(  );
					vo.setId("0");
					vo.setDesc("-Select-");
					lArrDivDtls.add(vo);
					ArrayList lArrTemp = (ArrayList)lObjRptQryDAOImpl.getDDO(lStrLangId,lstrLocId,str);
					for (int i=0;i<lArrTemp.size();i++)
					{
						lArrDivDtls.add(lArrTemp.get(i));
					}
					glogger.info("the list size in ajax class is "+lArrDivDtls.size());
					String tempResult = "";
				
					tempResult = new AjaxXmlBuilder().addItems(lArrDivDtls,"desc","id").toString();
				
					glogger.info("---------Ajax String in getDivisionDtls "+tempResult);
					objectArgs.put("ajaxKey",tempResult);
					objectArgs.put("AudSelect", lArrDivDtls);
					objRes.setResultValue(objectArgs);
					objRes.setViewName("ajaxData");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.info("Error: " + e);
		}
		return objRes;
		} 
	
	/* Added By Manesh */
	public ResultObject getTypes(Map inputMap)
	{
		glogger.info("------------Inside getTypes------- ");
		ArrayList lFinYear=new ArrayList();
		
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

		CmnLookupMstDAOImpl cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());	
		List l_exp_rec1  = (List)cmnDAO.getAllChildren("BudjetType");
		glogger.info("Size is:"+l_exp_rec1.size());
		
		inputMap.put("Budget_Type",l_exp_rec1);
		glogger.info("bud cmplt---:"+serv.getSessionFactory());
		Long lLngLangID=SessionHelper.getLangId(inputMap);
		glogger.info("LongLangid is:"+lLngLangID);
		
		DSSQueryDAOImpl lObjRptQryDAOImpl=new DSSQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
		//DSSQueryDAO  lObjRptQryDAOImpl= (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
		glogger.info("lObjRptQryDAOImpl-------------"+lObjRptQryDAOImpl);
		String lStrLangId=lObjRptQryDAOImpl.getStrLangId(lLngLangID);
		glogger.info("strlangid is:"+lStrLangId);
		
		BudFinYrDAOImpl finDAO= new BudFinYrDAOImpl();
		try
		{
			 lFinYear=finDAO.getFinanceYr(lStrLangId, "LC1");
			 glogger.info("the finyear list is "+ lFinYear.toString());
		}
		
		catch(Exception e)
		{
			glogger.info("Exception is:"+e.getMessage());
		}
		inputMap.put("FinYear",lFinYear);
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		objRes.setResultValue(inputMap);
		objRes.setViewName("plnlcssRpt");	
		glogger.info("--->Before returning from getTypes");
		return objRes;
	}
	/* End dBy Maneesh*/
	
}	
