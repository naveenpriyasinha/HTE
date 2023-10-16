package com.tcs.sgv.dcps.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.ColumnVo;
import com.tcs.sgv.common.helper.ExcelExportHelper;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.util.query.DateUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoWiseExpenditureReportDAO;
import com.tcs.sgv.dcps.dao.DdoWiseExpenditureReportDAOImpl;
import com.tcs.sgv.eis.dao.EmpStatisticsDDOwiseDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;

public class DDOWiseReportFormServiceImp extends ServiceImpl {
	
 
	/**
	 * 
	 * **/
	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */
	
	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	/* Global Variable for User Name */
	String gloginName = null;
	
	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gloginName 	=	SessionHelper.getUserName(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gDtCurrDt = SessionHelper.getCurDate();
		} catch (Exception e) {

		}
	}

	public ResultObject  ddowiseReportForm(Map inputMap) throws Exception {
		
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		setSessionInfo(inputMap);
		List ddoWiseExpenditureRptList=null;
		String monthNames[] = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};

		String region=null;
		String year=null;
		String month=null;
		String monthName=null;
		String ddoCode=null;
		String[] userDDO;
		List officeList = null; 		
		String flag=null;
		String currYear=null;
		gLogger.info("Welcome to ddowise report for MDC"+gloginName);
			try {
				setSessionInfo(inputMap);
				DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				DdoWiseExpenditureReportDAO lObjDddowiseExpenditureRptDao = new DdoWiseExpenditureReportDAOImpl(null, serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				flag = StringUtility.getParameter("flag", request);
				System.out.println(" flag ++++++++++++++++++"+flag+"+++++"+lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId));
				Date date = new Date();
				currYear=new SimpleDateFormat("yyyy").format(date); 
				region = StringUtility.getParameter("region", request);
				year = StringUtility.getParameter("year", request);
				month = StringUtility.getParameter("month", request);
				if(!month.isEmpty() && !month.equals("0") )
				{
					monthName=monthNames[Integer.parseInt(month)-1];
				}
				System.out.println("Month Name: "+monthName);
				Map loginMap = (Map) inputMap.get("baseLoginMap");
				long langId = Long.parseLong(loginMap.get("langId").toString());
				List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang(
									"Month", langId); 
				if(!flag.equals(null) && !flag.isEmpty()) {
					 	 
						ddoCode=StringUtility.getParameter("searchTxt", request);
						if(ddoCode.isEmpty() || ddoCode.equals("0"))
						{  
							userDDO=gloginName.split("_");
							if(userDDO.length>1) {
								ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(Long.parseLong(userDDO[0]));
							}
							else if(!gLngPostId.equals("200460")) {
								ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
								
							}else {
								ddoCode="All";
							}
							if(ddoCode==null) {
								ddoCode="";
							}
						}
						gLogger.info("Inside IF when post request occur::: "+getClass()+" Exception are "+ ddoCode);
							 
						ddoWiseExpenditureRptList = lObjDddowiseExpenditureRptDao.getDDOWiseExpenditure(ddoCode,region, year, month, flag);
						inputMap.put("year",year);
						inputMap.put("month",month);
						inputMap.put("monthName",monthName);
						inputMap.put("searchTxt",ddoCode);
						inputMap.put("reportDataList",ddoWiseExpenditureRptList);
						System.out.println("Resultset "+ddoWiseExpenditureRptList.size());
						System.out.println("SIZE: "+ddoWiseExpenditureRptList.size());
						
					} else {
						flag="report";
						year=new SimpleDateFormat("yyyy").format(date); 
						userDDO=gloginName.split("_");
						//gLogger.info("Inside Else default request ddoCode::: "+ userDDO.length);
						if(userDDO.length>1) {
							ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId-1);
						}
						else if(!gLngPostId.equals("200460")){
							ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
						}else {
							ddoCode="All";
						}
						gLogger.info("Inside Else default request ddoCode::: "+ ddoCode+gLngPostId);
						gLogger.info("Inside Else default request region::: "+ region);
						gLogger.info("Inside Else default request year::: "+ year);
						gLogger.info("Inside Else default request month::: "+ month);
						gLogger.info("Inside Else default request flag::: "+ flag);
						gLogger.info("Inside Else default request occur::: "+getClass()+" Exception are "+ ddoCode); 
						ddoWiseExpenditureRptList = lObjDddowiseExpenditureRptDao.getDDOWiseExpenditure(ddoCode,region, year, month, flag);
						inputMap.put("year",year);
						inputMap.put("month",month);
						inputMap.put("monthName",monthName);
						inputMap.put("searchTxt",ddoCode);
						inputMap.put("reportDataList",ddoWiseExpenditureRptList);
						gLogger.info("Inside Else default request occur::: "+getClass()+" Exception are "+ ddoCode);
					} 
				officeList =  lObjDddowiseExpenditureRptDao.getSubDDOs(gLngPostId);
				inputMap.put("officeList",officeList);
				inputMap.put("currYear",currYear);
				
				inputMap.put("monthList",monthList);
				resObj.setResultValue(inputMap);	
				resObj.setViewName("DDOWISEREPORT"); 
				 
				
			}
			catch (Exception e) {
				gLogger.info("Exception occurr"+getClass()+" Exception are "+ e.getMessage());
			}
			finally {
				gLogger.info(getClass()+ " Welcome to ddowise report for MDC");
			}
		
			
		return resObj;
	}
	
	
	
	public ResultObject generateDdoWiseExcel(Map inputMap)
    {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		setSessionInfo(inputMap);
		List ddoWiseExpenditureRptList=null;
		String monthNames[] = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};

		String region=null;
		String year=null;
		String month=null;
		String monthName=null;
		String ddoCode=null;
		String[] userDDO;
		List officeList = null; 		
		String flag=null;
		String currYear=null;
		gLogger.info("Welcome to ddowise export to excel report for "+gloginName);
			try {
				setSessionInfo(inputMap);
				Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
				DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				DdoWiseExpenditureReportDAO lObjDddowiseExpenditureRptDao = new DdoWiseExpenditureReportDAOImpl(null, serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
				flag = StringUtility.getParameter("flag", request);
				System.out.println(" flag ++++++++++++++++++"+flag+"+++++"+lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId));
				Date date = new Date();
				currYear=new SimpleDateFormat("yyyy").format(date); 
				region = StringUtility.getParameter("region", request);
				year = StringUtility.getParameter("year", request);
				month = StringUtility.getParameter("month", request);
				if(!month.isEmpty() && !month.equals("0") )
				{
					monthName=monthNames[Integer.parseInt(month)-1];
				}
				System.out.println("Month Name: "+monthName);
				Map loginMap = (Map) inputMap.get("baseLoginMap");
				long langId = Long.parseLong(loginMap.get("langId").toString());
				List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang(
									"Month", langId); 
				if(!flag.equals(null) && !flag.isEmpty()) {
					 	 
						ddoCode=StringUtility.getParameter("searchTxt", request);
						if(ddoCode.isEmpty() || ddoCode.equals("0"))
						{  
							userDDO=gloginName.split("_");
							if(userDDO.length>1) {
								ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(Long.parseLong(userDDO[0]));
							}
							else if(!gLngPostId.equals("200460")) {
								ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
								
							}else {
								ddoCode="All";
							}
							if(ddoCode==null) {
								ddoCode="";
							}
						}	 
						ddoWiseExpenditureRptList = lObjDddowiseExpenditureRptDao.getDDOWiseExpenditure(ddoCode,region, year, month, flag);
						
						 
						
					} else {
						flag="report";
						year=new SimpleDateFormat("yyyy").format(date); 
						userDDO=gloginName.split("_");
						//gLogger.info("Inside Else default request ddoCode::: "+ userDDO.length);
						if(userDDO.length>1) {
							ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId-1);
						}
						else if(!gLngPostId.equals("200460")){
							ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
						}else {
							ddoCode="All";
						}
						gLogger.info("Inside Else default request ddoCode::: "+ ddoCode+gLngPostId);
						gLogger.info("Inside Else default request region::: "+ region);
						gLogger.info("Inside Else default request year::: "+ year);
						gLogger.info("Inside Else default request month::: "+ month);
						gLogger.info("Inside Else default request flag::: "+ flag);
						gLogger.info("Inside Else default request occur::: "+getClass()+" Exception are "+ ddoCode); 
						ddoWiseExpenditureRptList = lObjDddowiseExpenditureRptDao.getDDOWiseExpenditure(ddoCode,region, year, month, flag);
						
						gLogger.info("Inside Else default request occur::: "+getClass()+" Exception are "+ ddoCode);
					} 
			 
				 ReportExportHelper objExport = new ReportExportHelper();
			 	List columnvalue = new ArrayList();
				List mainValue=new ArrayList();
				Map output = new HashMap();
				String lSBOut = "";
				String exportTo=DBConstants.DIS_EXCELFILE;
				String lineSeperator = System.getProperty("line.separator");
			 	List<Object> lLstInnerList = new ArrayList<Object>();
				List<Object> lLstArrOuter = new ArrayList<Object>();
				Object Obj[];
				/*Map.ZP_DDO_CODE,DDO.DDO_NAME,BILLGRP.DESCRIPTION,"
					 + "sum(BILL.BILL_GROSS_AMT),sum(BILL.BILL_NET_AMOUNT) */
				if (ddoWiseExpenditureRptList != null && !ddoWiseExpenditureRptList.isEmpty()) {
					Iterator it = ddoWiseExpenditureRptList.iterator();
					while (it.hasNext()) {
						Obj = (Object[]) it.next();
						lLstInnerList = new ArrayList<Object>();
						
						lLstInnerList.add(Obj[0]!=null ? Obj[0] : "-");//DDO_CODE
						lLstInnerList.add(Obj[1]!=null ? Obj[1] : "-");//DDO_NAME
						lLstInnerList.add(Obj[16]!=null ?(Long) Obj[3] : "-");//Gross Amount
						lLstInnerList.add(Obj[16]!=null ?(Long) Obj[4] : "-");//Net Amount
						lLstArrOuter.add(lLstInnerList);
					}
				}
				  ColumnVo[] excelBillReportHeading=new ColumnVo[20];
		          excelBillReportHeading[0]=new ColumnVo("DDO CODE ", 2, 13, 0,false,false); 
		          excelBillReportHeading[1]=new ColumnVo("Employee Name",2,50,0,true,false);
		          excelBillReportHeading[2]=new ColumnVo("GROSS AMOUNT",2,13,0,false,true);
		          excelBillReportHeading[3]=new ColumnVo("NET AMOUNT",2,13,0,false,true);
		          columnvalue.add(excelBillReportHeading);
		          mainValue.add(lLstArrOuter);
				
		          StringBuffer lSbHeader= new StringBuffer();
		          lSbHeader.append("Table showing details of DDO Wise Expenditure Report");
		          lSbHeader.append("\n");
		          lSbHeader.append(" of\n");
		          if(!month.isEmpty())
		        	  lSbHeader.append(monthName+",\n");
			  		  else
			  			lSbHeader.append("N.A,\n");
		          if(!year.isEmpty())
		          lSbHeader.append(year+",\n");
		          else lSbHeader.append("N.A."+",\n");
		          
		           String lStrFooter= "";
		          int nMode= 131;
		          
		          ExcelExportHelper exph = new ExcelExportHelper();
		          byte[] aryOut = null;
		          String[] param = new String[1];
		  		  List Headerdata = new ArrayList();
		  		  List footerdata = new ArrayList();
		  		  param[0] = "";

		  		  Headerdata.add(lSbHeader.toString());
		  		  StringBuffer footer =new StringBuffer();
		  		  footer = footer.append("Table showing details of DDO Wise Expenditure Report");
		  		  if(!ddoCode.isEmpty() && !ddoCode.equals(null))
		  		  footer = footer.append(ddoCode+"-");
		  		  else
		  			  footer = footer.append("N.A");
		  		  footer = footer.append("NOTE: The Fetched amount as Consolidated bill as Pending and Approved Status.");
		  		  footer = footer.append("\n");
		  		  footerdata.add(footer.toString());
		  		  
		  		  aryOut = exph.getExcelReportPrintFormat(mainValue, columnvalue, param, Headerdata, footerdata);
		  		String lStrExportTo = DBConstants.DIS_EXCELFILE;
		  		Map lDetailMap = new HashMap();
				if ((DBConstants.DIS_ONSCREEN).equals(lStrExportTo)) {
					lDetailMap.put(DBConstants.DIS_ONSCREEN, aryOut);
				} else if ((DBConstants.DIS_EXCELFILE).equals(lStrExportTo)) {
					lDetailMap.put(DBConstants.DIS_EXCELFILE, aryOut);
				}
				inputMap.put("FileName", "DDO Wise Reports for "+ddoCode);
				objExport.getExportData(resObj, lStrExportTo, inputMap, lDetailMap, false);
				resObj.setResultValue(inputMap);
				
		          
    	}
    	catch(Exception e){
    		resObj = new ResultObject(ErrorConstants.ERROR);
    		resObj.setResultCode(-1);
    		resObj.setViewName("errorPage");
			gLogger.info(e);
    	}
    	return resObj;
    }
    
	
	
}
