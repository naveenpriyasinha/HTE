package com.tcs.sgv.lcm.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.ess.valueobject.OrgPostMst;



public class LCReportDAOImpl
extends DefaultReportDataFinder implements ReportDataFinder
{
	//private static final Logger glogger=Logger.getLogger(CommonReportDAOImpl.class);
	Log glogger = LogFactory.getLog(getClass());
	
	private SessionFactory lObjSessionFactory = null;
	private StyleVO[] reportStyleVO = null;
	
	public Collection findReportData(ReportVO report, Object criteria) throws ReportException 
	{
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serviceLocator = (ServiceLocator) requestAttributes .get("serviceLocator");	
		
		//--------GET LOGGED IN DTLS-------------------------------------------------------
		
		Map sessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
		LoginDetails loginVO = (LoginDetails)sessionAttributes.get("loginDetails");
		long lLngLoginLangId=loginVO.getLangId();
		glogger.info("-----------LC REPORT DAO,LOGGED IN LANG ID----------"+lLngLoginLangId);		
		OrgPostMst postVO=null;
		postVO=(OrgPostMst)loginVO.getLoggedInPost();
		long lLngLoginPostId=postVO.getPostId();
		String lStrLoginLocCode=postVO.getLocationCode();
		
		glogger.info("-----------LC REPORT DAO,LOGGED IN POST ID----------"+lLngLoginPostId);
		glogger.info("-----------LC REPORT DAO,LOGGED IN LOCATION CODE----------"+lStrLoginLocCode);
		//glogger.info("-----------LC REPORT DAO,LOGGED IN POST NAME----------"+postVO. .getPostName());
		
		FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serviceLocator.getSessionFactory());
		long lIFinYrId=(long)lObjFinYearDAOImpl.getFinYearIdByCurDate();
		glogger.info("------FINYEAR ID At DAO IMPL---------"+lIFinYrId);
		glogger.info("------FINYEAR ID At DAO IMPL---------"+lIFinYrId);
		//-----------------------------------------------------------------------------------
		
		// get the SessionFactory instance for using it to fetch data...
		lObjSessionFactory = serviceLocator.getSessionFactory();
		
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		//LcReportsQueryDAO lObjRptDAO =  (LcReportsQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LcReportsQueryDAO");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);

		List lArrReportData = new ArrayList();
		/*HttpServletRequest request =(HttpServletRequest) report.;
		Long createdPostId=Httprequest.getSessionhelper.getPostId();*/

		
		reportStyleVO = new StyleVO[1];
   	 	reportStyleVO[0]=new StyleVO();
   	 	reportStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
   	 	reportStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 
	   	if (report.getReportCode().equals("150001")) // LC_REPORT_B----formb
	 	{
	 		glogger.info("Going into getAllDivisonUnderPost");
	 		lArrReportData = getAllDivisonUnderPost(report,lStrLoginLocCode,lLngLoginLangId);
	 	}

   	    if (report.getReportCode().equals("150005")) // LC Expenditure Report
		{
			lArrReportData = getLcExpenditureReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode);
		}
   	    if (report.getReportCode().equals("150006")) // LC Usage Report
		{
			lArrReportData = getLcUsageReport(report,lLngLoginLangId);
		}
     	if (report.getReportCode().equals("150007")) // LC Usage Report DivisionWise
		{
			lArrReportData = getLcUsageReportDivisionWise(report,lLngLoginLangId);
		}
     	if (report.getReportCode().equals("150008")) // LC Treasury Report for Verification
		{
			lArrReportData = getLcTsryReportForVerification(report,lLngLoginLangId,lLngLoginPostId,lIFinYrId);
		}
     	if (report.getReportCode().equals("150009")) // Division wise Paid Cheque Report
		{
			lArrReportData = getDivisionWisePaidChequesReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150010")) // Division wise Paid Cheque Report Division Details
		{
			lArrReportData = getDivisionWisePaidChequesReportDivisionDtls(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150011")) // Division wise LC Payment Ledger Report
		{
			lArrReportData = getDivisionWiseLCPaymentLedgerReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150012")) // LC Payment Approval Report
		{
			lArrReportData = getLCPaymentApprovalReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150013")) // LC Cheque Status Report for DAT
		{
			lArrReportData = getLCChequeStatusReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150014")) // LC Expenditure Tracking for DAT
		{
			lArrReportData = getLCExpenditureTrackingDATReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150015")) // LC Expenditure Tracking for DAT
		{
			lArrReportData = getLCExpenditureTrackingDATReportDeptDtls(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150016")) // LC Expenditure Tracking for DAT
		{
			lArrReportData = getLCExpenditureTrackingDATReportTsryWise(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150017")) // LC Expenditure Tracking for DAT
		{
			lArrReportData = getLCExpenditureTrackingDATReportTsryDtls(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	if (report.getReportCode().equals("150018")) // LC Ledger Accounts Report
		{
			lArrReportData = getLCLedgerAccountsReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId,lObjRptDAO);
		}
     	if (report.getReportCode().equals("150019")) // LC Division Dashboard Report
		{
			lArrReportData = getLCDivisionDashboardReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
     	
     	if (report.getReportCode().equals("150021")) // LC Balance Approval Report
		{
			lArrReportData = getLCBalanceApprovalReport(report,lLngLoginLangId,lLngLoginPostId,lStrLoginLocCode,lIFinYrId);
		}
   	    return lArrReportData;
   	    //Method findReportData End Here................................
	}   
	
	//..................Method defination Start Here................................
	private List getLcExpenditureReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String lStrLoginLocCode)
	{
		glogger.info("---------INSIDE getLcExpenditureReport 150005-------------");
		
		String lStrFinYr="";
		int lIAdviceNo=0;
		String lStrMonthCode="";
		String lStrBankCode="";
		String lStrLcValidFrmDt="";
		String lStrLcValidToDt="";
		String lStrEntryDtFrmDt="";
		String lStrEntryDtToDt="";
		String lStrAdvStatus="";
		
		ArrayList lArrReturn = null;		
		
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		lStrFinYr = (String) lObjReport.getParameterValue("finYear");
		glogger.info("-------lStrFinYr is-------- : "+ lStrFinYr);
		
		if(lObjReport.getParameterValue("adviceNo") !=null && !lObjReport.getParameterValue("adviceNo").toString().equals(""))
		{
			lIAdviceNo=Integer.parseInt((String) lObjReport.getParameterValue("adviceNo"));
			glogger.info("-------lIAdviceNo is-------- : "+ lIAdviceNo);
		}
		if(lObjReport.getParameterValue("month") !=null && !lObjReport.getParameterValue("month").toString().equals("") && !lObjReport.getParameterValue("month").toString().equals("-1"))
		{
			lStrMonthCode=(String) lObjReport.getParameterValue("month");
			glogger.info("-------lStrMonthCode is-------- : "+ lStrMonthCode);
		}
		if(lObjReport.getParameterValue("bank") !=null && !lObjReport.getParameterValue("bank").toString().equals("") && !lObjReport.getParameterValue("bank").toString().equals("-1"))
		{
			lStrBankCode=(String) lObjReport.getParameterValue("bank");
			glogger.info("-------lStrBankCode is-------- : "+ lStrBankCode);
		}
		if(lObjReport.getParameterValue("lcValidfrom") !=null && !lObjReport.getParameterValue("lcValidfrom").toString().equals(""))
		{
			lStrLcValidFrmDt=(String) lObjReport.getParameterValue("lcValidfrom");
			glogger.info("-------lStrLcValidFrmDt is-------- : "+ lStrLcValidFrmDt);
		}
		if(lObjReport.getParameterValue("lcValidto") !=null && !lObjReport.getParameterValue("lcValidto").toString().equals(""))
		{
			lStrLcValidToDt=(String) lObjReport.getParameterValue("lcValidto");
			glogger.info("-------lStrLcValidToDt is-------- : "+ lStrLcValidToDt);
		}
		if(lObjReport.getParameterValue("entryDatefrom") !=null && !lObjReport.getParameterValue("entryDatefrom").toString().equals(""))
		{
			lStrEntryDtFrmDt=(String) lObjReport.getParameterValue("entryDatefrom");
			glogger.info("-------lStrEntryDtFrmDt is-------- : "+ lStrEntryDtFrmDt);
		}
		if(lObjReport.getParameterValue("entryDateto") !=null && !lObjReport.getParameterValue("entryDateto").toString().equals(""))
		{
			lStrEntryDtToDt=(String) lObjReport.getParameterValue("entryDateto");
			glogger.info("-------lStrEntryDtToDt is-------- : "+ lStrEntryDtToDt);
		}
		if(lObjReport.getParameterValue("status") !=null && !lObjReport.getParameterValue("status").toString().equals("") && !lObjReport.getParameterValue("status").toString().equals("-1"))
		{
			lStrAdvStatus=(String) lObjReport.getParameterValue("status");
			glogger.info("-------lStrAdvStatus is-------- : "+ lStrAdvStatus);
		}
		
		// Set Current Date if Date is not entered
		Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150005 Current Date---------------------------"+currDate);
		
		/*if(lStrLcValidFrmDt.equals(""))
			lStrLcValidFrmDt=currDate;*/
		if(lStrLcValidToDt.equals(""))
			lStrLcValidToDt=currDate;
		if(lStrEntryDtFrmDt.equals(""))
			lStrEntryDtFrmDt=currDate;
		if(lStrEntryDtToDt.equals(""))
			lStrEntryDtToDt=currDate;
		
		glogger.info("lObjRptDAO is : " + lObjRptDAO.getClass());
		
		long lLngLoginLocId=(long)lObjRptDAO.getLocIdByPostId(lLngLoginPostId,lLngLoginLangId);//A method which return the LocId of logged in branch
		
		lArrReturn = lObjRptDAO.getLcExpenditureReport(lStrFinYr,lIAdviceNo,lStrMonthCode,lStrBankCode,lStrLcValidFrmDt,lStrLcValidToDt,lStrEntryDtFrmDt,lStrEntryDtToDt,lLngLoginLangId,lStrLoginLocCode,lStrAdvStatus);
		
		return lArrReturn;
	}
		
	private List getLcUsageReport(ReportVO lObjReport,long lLngLoginLangId)
	{
		glogger.info("---------INSIDE getLcUsageReport-------------");
		///--------------
		/*
		ReportColumnVO[] rptclmn =  lObjReport.getColumnsToDisplay();
		glogger.info("-----------HDDDD_------------"+rptclmn[0]);
		glogger.info("-----------HDDDD_------------"+rptclmn[0].getHidden());
		rptclmn[0].setHidden("Y");
		glogger.info("-----------HDDDD_------------"+rptclmn[0].getHidden());
		rptclmn[0].setURL("ifms.htm?actionFlag=reportService&reportCode=150007&action=generateReport&directReport=True&DistrictCode=<urlv1>&Treasury=<urlv2>");
		rptclmn[0].setURLValueColumn("DistrictCode,TreasuryName");
		*/
	
		//----------------
		ArrayList lArrReturn = null;		
		
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		lArrReturn = lObjRptDAO.getTreasuryLcUsageReport(lLngLoginLangId);
		
		return lArrReturn;
	}
	
	private List getLcUsageReportDivisionWise(ReportVO lObjReport,long lLngLoginLangId)
	{
		glogger.info("---------INSIDE getLcUsageReportDivisionWise-------------");
		
		ArrayList lArrReturn = null;		
		long lIDistId=0;
		String lStrTsryName="";
		
		glogger.info("--------DISTRICT CODE VALUE------"+lObjReport.getParameterValue("DistrictCode"));
		if(lObjReport.getParameterValue("DistrictCode") !=null )
		{
			lIDistId=Integer.parseInt((String) lObjReport.getParameterValue("DistrictCode"));
			glogger.info("-------lIDistCode is-------- : "+ lIDistId);
		}
		if(lObjReport.getParameterValue("Treasury") !=null )
		{
			lStrTsryName=lObjReport.getParameterValue("Treasury").toString();
			glogger.info("-------lStrTsryName is-------- : "+ lStrTsryName);
		}
		
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		lArrReturn = lObjRptDAO.getDivisionLcUsageReport(lIDistId,lLngLoginLangId);
		
		lObjReport.setParameterValue("Treasury", lStrTsryName);
		
		return lArrReturn;
	}
	
	private List getLcTsryReportForVerification(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,long lIFinYrId)
	{
        glogger.info("---------INSIDE getLcTsryReportForVerification 150008-------------");
		
		String lStrDivCode="";
		int lIAdviceNo=0;
		String lStrMonthCode="";
		String lStrBankCode="";
		String lStrDeptCode="";
		String lStrApprovedCode="";
		String lStrFrmDt="";
		String lStrToDt="";
		
		ArrayList lArrReturn = null;		
		
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		//-------------
		
		//-------------
		
		if(lObjReport.getParameterValue("divCode") !=null && !lObjReport.getParameterValue("divCode").toString().equals("") && !lObjReport.getParameterValue("divCode").toString().equals("-1"))
		{
			lStrDivCode=(String) lObjReport.getParameterValue("divCode");
			glogger.info("-------lStrDivCode is-------- : "+ lStrDivCode);
		}
		if(lObjReport.getParameterValue("adviceNo") !=null && !lObjReport.getParameterValue("adviceNo").toString().equals(""))
		{
			lIAdviceNo=Integer.parseInt((String) lObjReport.getParameterValue("adviceNo"));
			glogger.info("-------lIAdviceNo is-------- : "+ lIAdviceNo);
		}
		if(lObjReport.getParameterValue("month") !=null && !lObjReport.getParameterValue("month").toString().equals("") && !lObjReport.getParameterValue("month").toString().equals("-1"))
		{
			lStrMonthCode=(String) lObjReport.getParameterValue("month");
			glogger.info("-------lStrMonthCode is-------- : "+ lStrMonthCode);
		}
		if(lObjReport.getParameterValue("bank") !=null && !lObjReport.getParameterValue("bank").toString().equals("") && !lObjReport.getParameterValue("bank").toString().equals("-1"))
		{
			lStrBankCode=(String) lObjReport.getParameterValue("bank");
			glogger.info("-------lStrBankCode is-------- : "+ lStrBankCode);
		}
		if(lObjReport.getParameterValue("dept") !=null && !lObjReport.getParameterValue("dept").toString().equals("") && !lObjReport.getParameterValue("dept").toString().equals("-1"))
		{
			lStrDeptCode=(String) lObjReport.getParameterValue("dept");
			glogger.info("-------lStrDeptCode is-------- : "+ lStrDeptCode);
		}
		if(lObjReport.getParameterValue("approved") !=null && !lObjReport.getParameterValue("approved").toString().equals("") && !lObjReport.getParameterValue("approved").toString().equals("-1"))
		{
			lStrApprovedCode=(String) lObjReport.getParameterValue("approved");
			glogger.info("-------lStrApprovedCode is-------- : "+ lStrApprovedCode);
		}
		if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("dateto");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}
		
		// Set Current Date if Date is not entered
		Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150013 Current Date---------------------------"+currDate);
		
		if(lStrFrmDt.equals(""))
			lStrFrmDt=currDate;
		if(lStrToDt.equals(""))
			lStrToDt=currDate;
		
		glogger.info("lObjRptDAO is : " + lObjRptDAO.getClass());
		
		long lLngLoginLocId=(long)lObjRptDAO.getLocIdByPostId(lLngLoginPostId,lLngLoginLangId);//A method which return the LocId of logged in branch
		
		lArrReturn = lObjRptDAO.getLcTsryReportForVerification(lStrDivCode,lIAdviceNo,lStrMonthCode,lStrBankCode,lStrDeptCode,lStrApprovedCode,lStrFrmDt,lStrToDt,lLngLoginLangId,lLngLoginLocId,lIFinYrId);
		
        //--------------
		
		ReportColumnVO[] rptclmn =  lObjReport.getColumnsToDisplay();
		glogger.info("-----------HDDDD_------------"+rptclmn[12]);		
		rptclmn[12].setURL("ifms.htm?actionFlag=approveLcAdviceReceived&LcExpId=<urlv1>&DivisionId=<urlv2>&AdviceCode=<urlv3>&MonthCode=<urlv4>&BankCode=<urlv5>&DepartmentId=<urlv6>&ApprovedId=<urlv7>&FromDate=<urlv8>&ToDate=<urlv9>");		
		rptclmn[12].setURLValueColumn("LcExpId,DivisionId,AdviceCode,MonthCode,BankCode,DepartmentId,ApprovedId,FromDate,ToDate");
		//----------------
		
		return lArrReturn;
    }
	
	
	//=====================DIVISION WISE PAID CHEQUE REPORT(AG) 150009=================================================	
	private List getDivisionWisePaidChequesReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		String lStrArrDivCode[]=null;
		String lStrFrmDt="";
		String lStrToDt="";
		
		ArrayList lArrReturnLst=null;
		
		if(lObjReport.getParameterValue("divCode") !=null && !lObjReport.getParameterValue("divCode").toString().equals("") && !lObjReport.getParameterValue("divCode").toString().equals("-1"))
		{
			lStrArrDivCode=(String[]) lObjReport.getParameterValue("divCode");
			glogger.info("-------lArrDivCodeLst size  is-------- : "+ lStrArrDivCode.length);
		}
		if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("dateto");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}
		
		// Set Current Date if Date is not entered
		Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150013 Current Date---------------------------"+currDate);
		
		if(lStrFrmDt.equals(""))
			lStrFrmDt=currDate;
		if(lStrToDt.equals(""))
			lStrToDt=currDate;
		
		lArrReturnLst = lObjRptDAO.getDivisionWisePaidChequesReport(lStrArrDivCode,lStrFrmDt,lStrToDt,lLngLoginLangId,lIFinYrId,LstrLoginLocCode);
		
		
		lObjReport.setReportName("DivisionWise Summary Of Paid Cheques");
		
		if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
		{
			String lStrPara="from "+lStrFrmDt+" to "+lStrToDt;
			lObjReport.setParameterValue("Paid Cheque Report", lStrPara);		
		}
		if(!lStrFrmDt.equals("") && lStrToDt.equals(""))
		{
			String lStrPara="from "+lStrFrmDt+" till Today";
			lObjReport.setParameterValue("Paid Cheque Report", lStrPara);		
		}
		if(lStrFrmDt.equals("") && !lStrToDt.equals(""))
		{
			String lStrPara="till "+lStrToDt;
			lObjReport.setParameterValue("Paid Cheque Report", lStrPara);		
		}
		
		return lArrReturnLst;
	}
	
	private List getDivisionWisePaidChequesReportDivisionDtls(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		String lStrDivCode="";
		String lStrDivShrtNm="";
		String lStrDivName="";
		String lStrFrmDt="";
		String lStrToDt="";
		
		ArrayList lArrReturnLst=null;

		glogger.info("---------"+lObjReport.getHiddenParametersString());	
		
		if(lObjReport.getParameterValue("DivisionCode") !=null && !lObjReport.getParameterValue("DivisionCode").toString().equals(""))
		{
			lStrDivCode=(String) lObjReport.getParameterValue("DivisionCode");
			glogger.info("-------lStrDivCode is-------- : "+ lStrDivCode);
		}
		if(lObjReport.getParameterValue("DivisionName") !=null && !lObjReport.getParameterValue("DivisionName").toString().equals(""))
		{
			lStrDivName=(String) lObjReport.getParameterValue("DivisionName");
			glogger.info("-------lStrDivName is-------- : "+ lStrDivName);
			lStrDivName=lStrDivName.replaceAll("&", "n");
		}
		
		if(lObjReport.getParameterValue("ChqClrFromDt") !=null && !lObjReport.getParameterValue("ChqClrFromDt").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("ChqClrFromDt");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("ChqClrToDt") !=null && !lObjReport.getParameterValue("ChqClrToDt").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("ChqClrToDt");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}		
		if(lObjReport.getParameterValue("DivShrtNm") !=null && !lObjReport.getParameterValue("DivShrtNm").toString().equals(""))
		{
			lStrDivShrtNm=(String) lObjReport.getParameterValue("DivShrtNm");
			glogger.info("-------lStrDivShrtNm is-------- : "+ lStrDivShrtNm);
		}
		
		lArrReturnLst = lObjRptDAO.getDivisionWisePaidChequesReportDivisionDtls(lStrDivCode,lStrFrmDt,lStrToDt,lLngLoginLangId,lIFinYrId);
		
		String lStrReportName ="DivisionWise Statement showing the details of payment";
		if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
		{
			 lStrReportName=lStrReportName+" from "+lStrFrmDt+" to "+lStrToDt;
					
		}
		lObjReport.setReportName(lStrReportName);
		
		lObjReport.setParameterValue("DivisionCode", lStrDivShrtNm);
		lObjReport.setParameterValue("DivisionName", lStrDivName);
		
		return lArrReturnLst;
	}
	//=========================================================================================================
	
	//=============DIVISION WISE LC PAYMENT LEDGER REPORT - 150011 ======================================================
	private List getDivisionWiseLCPaymentLedgerReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		String lStrDivCode="";		
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrAccDt="";
		
		ArrayList lArrReturnLst=null;
		
		if(lObjReport.getParameterValue("divCode") !=null && !lObjReport.getParameterValue("divCode").toString().equals("") && !lObjReport.getParameterValue("divCode").toString().equals("-1"))
		{
			lStrDivCode=(String) lObjReport.getParameterValue("divCode");
			glogger.info("-------lStrDivCode size  is-------- : "+ lStrDivCode);
		}
		if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("dateto");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}
		if(lObjReport.getParameterValue("accDate") !=null && !lObjReport.getParameterValue("accDate").toString().equals(""))
		{
			lStrAccDt=(String) lObjReport.getParameterValue("accDate");
			glogger.info("-------lStrAccDt is-------- : "+ lStrAccDt);
		}
		
		// Set Current Date if Date is not entered
		Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150013 Current Date---------------------------"+currDate);
		
		if(lStrFrmDt.equals(""))
			lStrFrmDt=currDate;
		if(lStrToDt.equals(""))
			lStrToDt=currDate;
		
		lArrReturnLst = lObjRptDAO.getDivisionWiseLCPaymentLedgerReport(lStrDivCode, lStrFrmDt, lStrToDt,lStrAccDt, lLngLoginLangId, lIFinYrId);
		
		
		ArrayList lArrDivCodeAndNm=null;
		lArrDivCodeAndNm=lObjRptDAO.getDivShrtNmAndDivNm(lStrDivCode, lLngLoginLangId);
		ComboValuesVO comboVo=null;
		comboVo=(ComboValuesVO)lArrDivCodeAndNm.get(0);
		
		String lStrDivShrtName="";
		String lStrDivName="";
		
		lStrDivShrtName=comboVo.getId();
		lStrDivName=comboVo.getDesc();
		glogger.info("------DIVISION CODE 4 SHOW-------------"+lStrDivShrtName);
		glogger.info("------DIVISION NAME 4 SHOW-------------"+lStrDivName);
		
		String lStrReportName ="DivisionWise LC Payment Ledger Report";
		if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
		{
			 lStrReportName=lStrReportName+" from "+lStrFrmDt+" to "+lStrToDt;
					
		}
		lObjReport.setReportName(lStrReportName);
		
		lObjReport.setParameterValue("DivisionCode", lStrDivShrtName);
		lObjReport.setParameterValue("DivisionName", lStrDivName);
		
		return lArrReturnLst;
	}	
	//==========================================================================================================
	
	//============= LC PAYMENT APPROVAL REPORT - 150012 ======================================================
	private List getLCPaymentApprovalReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		String lStrDivCode="";		
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrAdviceNo="";
		
		ArrayList lArrReturnLst=null;
		
		if(lObjReport.getParameterValue("divCode") !=null && !lObjReport.getParameterValue("divCode").toString().equals("") && !lObjReport.getParameterValue("divCode").toString().equals("-1"))
		{
			lStrDivCode=(String) lObjReport.getParameterValue("divCode");
			glogger.info("-------lStrDivCode size  is-------- : "+ lStrDivCode);
		}
		if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("dateto");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}
		if(lObjReport.getParameterValue("adviceNo") !=null && !lObjReport.getParameterValue("adviceNo").toString().equals(""))
		{
			lStrAdviceNo=(String) lObjReport.getParameterValue("adviceNo");
			glogger.info("-------lStrAdviceNo is-------- : "+ lStrAdviceNo);
		}
		
		//Set Current Date if Date is not entered
		Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150013 Current Date---------------------------"+currDate);
		
		if(lStrFrmDt.equals(""))
			lStrFrmDt=currDate;
		if(lStrToDt.equals(""))
			lStrToDt=currDate;
		
		lArrReturnLst = lObjRptDAO.getLCPaymentApprovalReport(lStrDivCode, lStrFrmDt, lStrToDt,lStrAdviceNo, lLngLoginLangId, lIFinYrId,LstrLoginLocCode);
		
		if(!lStrDivCode.equals(""))
		{
			ReportColumnVO[] rptColVo =lObjReport.getReportColumns();	
			glogger.info("-------------"+lObjReport.getColumnsToDisplayCount());
			rptColVo[5].setGroupByOrder(0);			
			
			ArrayList lArrDivCodeAndNm=null;
			lArrDivCodeAndNm=lObjRptDAO.getDivShrtNmAndDivNm(lStrDivCode, lLngLoginLangId);
			ComboValuesVO comboVo=null;
			comboVo=(ComboValuesVO)lArrDivCodeAndNm.get(0);
			
			String lStrDivShrtName="";
			String lStrDivName="";
			
			lStrDivShrtName=comboVo.getId();
			lStrDivName=comboVo.getDesc();
			glogger.info("------DIVISION CODE 4 SHOW-------------"+lStrDivShrtName);
			glogger.info("------DIVISION NAME 4 SHOW-------------"+lStrDivName);
			
			lObjReport.setParameterValue("DivisionCode", lStrDivShrtName);
			lObjReport.setParameterValue("DivisionName", lStrDivName);
		}
		lStrDivCode="";
		String lStrReportName ="LC Payment Approval Report";
		if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
		{
			 lStrReportName=lStrReportName+" from "+lStrFrmDt+" to "+lStrToDt;
					
		}
		lObjReport.setReportName(lStrReportName);
		
		
		
		return lArrReturnLst;
	}	
	//==========================================================================================================
	
	//=========LC CHEQUE STATUS REPORT - 150013 ================================================================
	private List getLCChequeStatusReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);		
		glogger.info("-----------------INSIDE 150013---------------------------");
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrChqNo="";
		String lStrDivCode="";
		String lStrDeptCode="";
		String lStrPaidUnpaid="";
		
		ArrayList lArrReturnLst=null;
		
		try
		{
			if(lObjReport.getParameterValue("division") !=null && !lObjReport.getParameterValue("division").toString().equals("") && !lObjReport.getParameterValue("division").toString().equals("-1"))
			{
				lStrDivCode=(String) lObjReport.getParameterValue("division");
				glogger.info("-------lStrDivCode   is-------- : "+ lStrDivCode);
			}
			if(lObjReport.getParameterValue("department") !=null && !lObjReport.getParameterValue("department").toString().equals("") && !lObjReport.getParameterValue("department").toString().equals("-1"))
			{
				lStrDeptCode=(String) lObjReport.getParameterValue("department");
				glogger.info("-------lStrDeptCode   is-------- : "+ lStrDeptCode);
			}
			if(lObjReport.getParameterValue("paidUnpaid") !=null && !lObjReport.getParameterValue("paidUnpaid").toString().equals("") && !lObjReport.getParameterValue("paidUnpaid").toString().equals("-1"))
			{
				lStrPaidUnpaid=(String) lObjReport.getParameterValue("paidUnpaid");
				glogger.info("-------lStrPaidUnpaid   is-------- : "+ lStrPaidUnpaid);
			}
			if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
			{
				lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
				glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
			}
			if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
			{
				lStrToDt=(String) lObjReport.getParameterValue("dateto");
				glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
			}
			if(lObjReport.getParameterValue("chequeNo") !=null && !lObjReport.getParameterValue("chequeNo").toString().equals(""))
			{
				lStrChqNo=(String) lObjReport.getParameterValue("chequeNo");
				glogger.info("-------lStrChqNo is-------- : "+ lStrChqNo);
			}
			
			// Set Current Date if Date is not entered
			Date currDt=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String currDate=sdf.format(currDt);
			glogger.info("-----------------INSIDE 150013 Current Date---------------------------"+currDate);
			
			if(lStrFrmDt.equals(""))
				lStrFrmDt=currDate;
			if(lStrToDt.equals(""))
				lStrToDt=currDate;
			
			lArrReturnLst = lObjRptDAO.getLCChequeStatusReport(lStrDivCode,lStrDeptCode,lStrPaidUnpaid, lStrFrmDt, lStrToDt,lStrChqNo, lLngLoginLangId, lIFinYrId,LstrLoginLocCode);
			
			String lStrReportName="LC Cheque Status Report ";
			if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
			{
				 lStrReportName=lStrReportName+" from "+lStrFrmDt+" to "+lStrToDt;						
			}
			lObjReport.setReportName(lStrReportName);
			
			
		}
		catch(Exception se)
		{
			se.printStackTrace();
			glogger.error( "SQLException::"+se.getMessage(), se );
		}
		return lArrReturnLst;
	}	
	
	//===========================================================================================================
	
   //=========LC EXPENDITURE TRACKING DAT REPORT - 150014 ================================================================
	private List getLCExpenditureTrackingDATReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);		
		glogger.info("-----------------INSIDE 150014---------------------------");
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrDeptCode="";
		String lStrPlanNonPlan="";
		String lStrRevenueCapital="";
		
		String lStrDeptTsryWise="";
		
		ArrayList lArrReturnLst=null;
		
		
		if(lObjReport.getParameterValue("department") !=null && !lObjReport.getParameterValue("department").toString().equals("") && !lObjReport.getParameterValue("department").toString().equals("-1"))
		{
			lStrDeptCode=(String) lObjReport.getParameterValue("department");
			glogger.info("-------lStrDeptCode   is-------- : "+ lStrDeptCode);
		}
		if(lObjReport.getParameterValue("planNonPlan") !=null && !lObjReport.getParameterValue("planNonPlan").toString().equals("") && !lObjReport.getParameterValue("planNonPlan").toString().equals("-1"))
		{
			lStrPlanNonPlan=(String) lObjReport.getParameterValue("planNonPlan");
			glogger.info("-------lStrPlanNonPlan   is-------- : "+ lStrPlanNonPlan);
		}
		if(lObjReport.getParameterValue("revenueCapital") !=null && !lObjReport.getParameterValue("revenueCapital").toString().equals("") && !lObjReport.getParameterValue("revenueCapital").toString().equals("-1"))
		{
			lStrRevenueCapital=(String) lObjReport.getParameterValue("revenueCapital");
			glogger.info("-------lStrRevenueCapital   is-------- : "+ lStrRevenueCapital);
		}
		if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("dateto");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}
		
		if(lObjReport.getParameterValue("deptWiseTsryWise") !=null && !lObjReport.getParameterValue("deptWiseTsryWise").toString().equals("") && !lObjReport.getParameterValue("deptWiseTsryWise").toString().equals("-1"))
		{
			lStrDeptTsryWise=(String) lObjReport.getParameterValue("deptWiseTsryWise");
			glogger.info("-------lStrDeptTsryWise   is-------- : "+ lStrDeptTsryWise);
		}
		
		// Set Current Date if Date is not entered
		Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150014 Current Date---------------------------"+currDate);
		
		if(lStrFrmDt.equals(""))
			lStrFrmDt=currDate;
		if(lStrToDt.equals(""))
			lStrToDt=currDate;
		
		
		
		 glogger.info("-----------------INSIDE 150014 B4 Method Calling---------------------------");
		lArrReturnLst = lObjRptDAO.getLCExpenditureTrackingDATReportDeptWise(lStrDeptCode,lStrPlanNonPlan,lStrRevenueCapital, lStrFrmDt, lStrToDt, lLngLoginLangId, lIFinYrId,LstrLoginLocCode);
	
	
		
		String lStrReportNm="LC Expenditure Tracking Department Wise From "+lStrFrmDt+" To "+lStrToDt;
		
		lObjReport.setReportName(lStrReportNm);
		
		
		
		return lArrReturnLst;
	}	
	
	private List getLCExpenditureTrackingDATReportDeptDtls(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);		
		glogger.info("-----------------INSIDE 150015---------------------------");
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrDeptCode="";
		String lStrDeptNm="";
		String lStrPlanNonPlan="";
		String lStrRevenueCapital="";
		
		
		ArrayList lArrReturnLst=null;
		
		
		if(lObjReport.getParameterValue("DepartmentCode") !=null && !lObjReport.getParameterValue("DepartmentCode").toString().equals("") )
		{
			lStrDeptCode=(String) lObjReport.getParameterValue("DepartmentCode");
			glogger.info("-------lStrDeptCode   is-------- : "+ lStrDeptCode);
		}
		if(lObjReport.getParameterValue("DepartmentNm") !=null && !lObjReport.getParameterValue("DepartmentNm").toString().equals("") )
		{
			lStrDeptNm=(String) lObjReport.getParameterValue("DepartmentNm");
			glogger.info("-------lStrDeptNm   is-------- : "+ lStrDeptNm);
		}
		if(lObjReport.getParameterValue("PNpCode") !=null && !lObjReport.getParameterValue("PNpCode").toString().equals("") )
		{
			lStrPlanNonPlan=(String) lObjReport.getParameterValue("PNpCode");
			glogger.info("-------lStrPlanNonPlan   is-------- : "+ lStrPlanNonPlan);
		}
		if(lObjReport.getParameterValue("RevCapCode") !=null && !lObjReport.getParameterValue("RevCapCode").toString().equals(""))
		{
			lStrRevenueCapital=(String) lObjReport.getParameterValue("RevCapCode");
			glogger.info("-------lStrRevenueCapital   is-------- : "+ lStrRevenueCapital);
		}
		if(lObjReport.getParameterValue("FromDt") !=null && !lObjReport.getParameterValue("FromDt").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("FromDt");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("ToDt") !=null && !lObjReport.getParameterValue("ToDt").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("ToDt");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}
		
		
		glogger.info("-----------------INSIDE 150015 B4 Method Calling---------------------------");
		lArrReturnLst = lObjRptDAO.getLCExpenditureTrackingDATReportDeptDtls(lStrDeptCode,lStrPlanNonPlan,lStrRevenueCapital, lStrFrmDt, lStrToDt, lLngLoginLangId, lIFinYrId,LstrLoginLocCode);
		
		String lStrReportNm="LC Expenditure Tracking Department Details From "+lStrFrmDt+" To "+lStrToDt;
		
		lObjReport.setReportName(lStrReportNm);
		
		return lArrReturnLst;
	}	
	//================================================================================================
	
	//==========LC Expenditure Report Tsry Wise - 150016 =======================================
	private List getLCExpenditureTrackingDATReportTsryWise(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);		
		glogger.info("-----------------INSIDE 150016---------------------------");
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrDeptCode="";		
		String lStrPlanNonPlan="";
		String lStrRevenueCapital="";
		String lStrDeptTsryWise="";
		
		ArrayList lArrReturnLst=null;
		
		if(lObjReport.getParameterValue("department") !=null && !lObjReport.getParameterValue("department").toString().equals("") && !lObjReport.getParameterValue("department").toString().equals("-1"))
		{
			lStrDeptCode=(String) lObjReport.getParameterValue("department");
			glogger.info("-------lStrDeptCode   is-------- : "+ lStrDeptCode);
		}
		if(lObjReport.getParameterValue("planNonPlan") !=null && !lObjReport.getParameterValue("planNonPlan").toString().equals("") && !lObjReport.getParameterValue("planNonPlan").toString().equals("-1"))
		{
			lStrPlanNonPlan=(String) lObjReport.getParameterValue("planNonPlan");
			glogger.info("-------lStrPlanNonPlan   is-------- : "+ lStrPlanNonPlan);
		}
		if(lObjReport.getParameterValue("revenueCapital") !=null && !lObjReport.getParameterValue("revenueCapital").toString().equals("") && !lObjReport.getParameterValue("revenueCapital").toString().equals("-1"))
		{
			lStrRevenueCapital=(String) lObjReport.getParameterValue("revenueCapital");
			glogger.info("-------lStrRevenueCapital   is-------- : "+ lStrRevenueCapital);
		}
		if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("dateto");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}		
		if(lObjReport.getParameterValue("deptWiseTsryWise") !=null && !lObjReport.getParameterValue("deptWiseTsryWise").toString().equals("") && !lObjReport.getParameterValue("deptWiseTsryWise").toString().equals("-1"))
		{
			lStrDeptTsryWise=(String) lObjReport.getParameterValue("deptWiseTsryWise");
			glogger.info("-------lStrDeptTsryWise   is-------- : "+ lStrDeptTsryWise);
		}
		
		// Set Current Date if Date is not entered
		Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150016 Current Date---------------------------"+currDate);
		
		if(lStrFrmDt.equals(""))
			lStrFrmDt=currDate;
		if(lStrToDt.equals(""))
			lStrToDt=currDate;
		
		
		lArrReturnLst = lObjRptDAO.getLCExpenditureTrackingDATReportTsryWise(lStrDeptCode,lStrPlanNonPlan,lStrRevenueCapital, lStrFrmDt, lStrToDt, lLngLoginLangId, lIFinYrId,LstrLoginLocCode);
		
		String lStrReportNm="LC Expenditure Tracking Treasury Wise From "+lStrFrmDt+" To "+lStrToDt;
		
		lObjReport.setReportName(lStrReportNm);
		return lArrReturnLst;
	}
	
	private List getLCExpenditureTrackingDATReportTsryDtls(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);		
		glogger.info("-----------------INSIDE 150017---------------------------");
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrTsryCode="";
		String lStrTsryNm="";
		String lStrPlanNonPlan="";
		String lStrRevenueCapital="";
		String lStrDeptCode="";
		
		ArrayList lArrReturnLst=null;
		
		
		if(lObjReport.getParameterValue("TsryCode") !=null && !lObjReport.getParameterValue("TsryCode").toString().equals("") )
		{
			lStrTsryCode=(String) lObjReport.getParameterValue("TsryCode");
			glogger.info("-------lStrTsryCode   is-------- : "+ lStrTsryCode);
		}
		if(lObjReport.getParameterValue("TsryNm") !=null && !lObjReport.getParameterValue("TsryNm").toString().equals("") )
		{
			lStrTsryNm=(String) lObjReport.getParameterValue("TsryNm");
			glogger.info("-------lStrTsryNm   is-------- : "+ lStrTsryNm);
		}
		if(lObjReport.getParameterValue("PNpCode") !=null && !lObjReport.getParameterValue("PNpCode").toString().equals("") )
		{
			lStrPlanNonPlan=(String) lObjReport.getParameterValue("PNpCode");
			glogger.info("-------lStrPlanNonPlan   is-------- : "+ lStrPlanNonPlan);
		}
		if(lObjReport.getParameterValue("RevCapCode") !=null && !lObjReport.getParameterValue("RevCapCode").toString().equals(""))
		{
			lStrRevenueCapital=(String) lObjReport.getParameterValue("RevCapCode");
			glogger.info("-------lStrRevenueCapital   is-------- : "+ lStrRevenueCapital);
		}
		if(lObjReport.getParameterValue("FromDt") !=null && !lObjReport.getParameterValue("FromDt").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("FromDt");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("ToDt") !=null && !lObjReport.getParameterValue("ToDt").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("ToDt");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}
		if(lObjReport.getParameterValue("DeptCode") !=null && !lObjReport.getParameterValue("DeptCode").toString().equals("") )
		{
			lStrDeptCode=(String) lObjReport.getParameterValue("DeptCode");
			glogger.info("-------lStrDeptCode   is-------- : "+ lStrDeptCode);
		}
		
		
		glogger.info("-----------------INSIDE 150017 B4 Method Calling---------------------------");
		lArrReturnLst = lObjRptDAO.getLCExpenditureTrackingDATReportTsryDtls(lStrTsryCode,lStrDeptCode,lStrPlanNonPlan,lStrRevenueCapital, lStrFrmDt, lStrToDt, lLngLoginLangId, lIFinYrId,LstrLoginLocCode);
		
		String lStrReportNm="LC Expenditure Tracking Department Details From "+lStrFrmDt+" To "+lStrToDt;
		
		lObjReport.setReportName(lStrReportNm);
		
		return lArrReturnLst;
	}
	//===========================================================================================================
	
	
	//========LC FORM B - 150001==============================================================================
	private List getAllDivisonUnderPost(ReportVO lObjReport,String lStrLoginLocCode,long lLngLoginLangId)
	{
		glogger.info("Going into getAllDivisonUnderPost");
		List lArrReturn = new ArrayList();
		
		LCReportQueryDAOImpl lObjRptDAO =  (LCReportQueryDAOImpl) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		//LcReportsQueryDAO lObjRptDAO =  (LcReportsQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LcReportsQueryDAO");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		String lStrFromDate="";
		String lStrToDate="";
		String lStrDivisionCode[] =null; 
		String lStrLcNo= "";
		ArrayList lArrSplitLC=new ArrayList();
		String lStrLCcode="";
		
		if(lObjReport.getParameterValue("divCode")!=null)
			lStrDivisionCode=(String[]) lObjReport.getParameterValue("divCode");
		if(lObjReport.getParameterValue("lcNo")!=null)
			lStrLcNo = (String) lObjReport.getParameterValue("lcNo");
		if(lObjReport.getParameterValue("datefrom")!=null)
			 lStrFromDate = (String)lObjReport.getParameterValue("datefrom");		
		if(lObjReport.getParameterValue("dateto")!=null)
			lStrToDate = (String)lObjReport.getParameterValue("dateto");
		
		
		//lObjRptDAO.getAllDivisonUnderPost(lStrDivisionCode,lStrLcNo,lStrFromDate,lStrToDate);
		
		glogger.info("lObjRptDAO is : " + lObjRptDAO.getClass());
	

		ArrayList lArrData = lObjRptDAO.getAllDivisonUnderPost(lStrDivisionCode,lStrLcNo,lStrFromDate,lStrToDate,lStrLoginLocCode,lLngLoginLangId);
		glogger.info("going into arraydata");
		ArrayList lArrInner = new ArrayList();
		glogger.info(lArrData.size());
	    for(int i=0;i<lArrData.size();i++)
	    {
	    	lArrInner = new ArrayList();
	    	HashMap lHashmap = (HashMap) lArrData.get(i);
	    	lArrInner.add(lHashmap.get("LcNo").toString());
	    	lArrInner.add(lHashmap.get("SenderCode").toString());
	    	lArrInner.add(lHashmap.get("Party Reference No.").toString());
	    	lArrInner.add(lHashmap.get("Party Reference Date").toString());
	    	lArrInner.add(lHashmap.get("InwardDate").toString());
	    	lArrInner.add(lHashmap.get("Inward No.").toString());
	    	lArrInner.add(lHashmap.get("Code").toString());
	    	lArrInner.add(lHashmap.get("IssueDate").toString());
	    	lArrInner.add(lHashmap.get("Amount").toString());
	    	lArrReturn.add(lArrInner);
	    	
	    	lArrInner = new ArrayList();
	    	lArrSplitLC=getSplitedLCNo(lHashmap.get("LcNo").toString());
	    	lStrLCcode="FORM-C"+" "+lArrSplitLC.get(0)+"/"+lArrSplitLC.get(1)+"/"+lArrSplitLC.get(2)+"/"+"FORM-C"+"/"+lArrSplitLC.get(3);
	    	
	    	lArrInner.add("");
	    	lArrInner.add(lStrLCcode);
	    	lArrInner.add("");
	    	lArrInner.add("");
	    	lArrInner.add("");
	    	lArrInner.add("");
	    	lArrInner.add("");
	    	lArrInner.add("");
	    	lArrInner.add(lHashmap.get("Amount").toString());
	    	lArrReturn.add(lArrInner);
	    	
	    }  

		return lArrReturn;
	}


	//=============================================================================================
	public List getLCLedgerAccountsReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String lStrLoginLocCode,long lIFinYrId,LCReportQueryDAO lObjRptDAO)
	{
		
		String[] lStrArrDivCode =null;
		ArrayList lArrLAReportData = new ArrayList();
		ArrayList lArrRetList = new ArrayList();
		String lStrFromDate="";
		String lStrToDate="";
		String lStrACDate = "";
		String lStrChqNo=null;
		String lStrLangId = String.valueOf(lLngLoginLangId);
		String lTmp =  (new Long(lLngLoginLangId).toString());
		glogger.info("================long lang id :: "+lLngLoginLangId);
		glogger.info("================String lang id :: "+lStrLangId);
		glogger.info("================String lang id 2:: "+lTmp);
		
		SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
		Date lDtCur = new Date();
		String lStrCurDt = sdf1.format(lDtCur);
		ArrayList lArrDivList = new ArrayList();
		
		
		
		//---------getting parameters from para page
		if(lObjReport.getParameterValue("divCode") !=null && !lObjReport.getParameterValue("divCode").toString().equals("") && !lObjReport.getParameterValue("divCode").toString().equals("-1"))
			lStrArrDivCode=(String[]) lObjReport.getParameterValue("divCode");
		if(lObjReport.getParameterValue("datefrom")!=null)
			 lStrFromDate = (String)lObjReport.getParameterValue("datefrom");		
		if(lObjReport.getParameterValue("dateto")!=null)
			lStrToDate = (String)lObjReport.getParameterValue("dateto");
		if(lObjReport.getParameterValue("ACdate")!=null)
			lStrACDate = (String)lObjReport.getParameterValue("ACdate");
		if(lObjReport.getParameterValue("ChequeNo")!=null)
			lStrChqNo = (String)lObjReport.getParameterValue("ChequeNo");
		
		
		
			//------------if Account date is null then only check for from date and to date
		if(lStrACDate.equals("") )
		{
			//-----------if from date and to date is not entered then set current date.
			if(lStrFromDate.equals(""))
				lStrFromDate=lStrCurDt;
			if(lStrToDate.equals(""))
				lStrToDate=lStrCurDt;
		}
		glogger.info("From date is :: "+lStrFromDate);
		glogger.info("To date is :: "+lStrToDate);
		glogger.info("Acc date is :: "+lStrACDate);
		glogger.info("Chq no is :: "+lStrChqNo);


		//Logic to prepare Arraylist of divisions from para page....
		if(lStrArrDivCode[0].equals("-1") && lStrArrDivCode.length==1 )
		{
			glogger.info("============ division is not selected========== :: "+lStrArrDivCode.length);
			ArrayList lArrTemp= lObjRptDAO.getDivisionsOfLoginTsry( lStrLangId, lLngLoginPostId);
			for(int lICnt=0;lICnt<lArrTemp.size();lICnt++)
			{
				lArrDivList.add(((ComboValuesVO)lArrTemp.get(lICnt)).getId());
			}
		}
		else
		{
			glogger.info("============ division is selected==========");
			
			if(lStrArrDivCode[0].equals("-1"))
			{
				for(int lICnt=1;lICnt<lStrArrDivCode.length;lICnt++)
				{
					lArrDivList.add(lStrArrDivCode[lICnt]);
				}
			}
			else
			{
				for(int lICnt=0;lICnt<lStrArrDivCode.length;lICnt++)
				{
					lArrDivList.add(lStrArrDivCode[lICnt]);
				}
			}
		}
		
		
		lArrRetList=(ArrayList)lObjRptDAO.getLCLedgerAccountsReport(lArrDivList,lStrACDate,lStrFromDate,lStrToDate,lStrChqNo);
		
		
		
		
		
		return lArrRetList;
	}
	public ArrayList getSplitedLCNo(String lstrLCno)
	{
		glogger.info("-------Inside getSplitedLCNo-------- ");
		glogger.info("Actual LC no.After passing is:"+lstrLCno);
		String lStrLCRange = "";
		String[] lArrLCRange = null;
		ArrayList lArrRangeLst = new ArrayList();
	
		try
		{
			if(lstrLCno.contains("/"))
			{
				lArrLCRange=lstrLCno.split("/");
				if(lArrLCRange!=null)
				{
					lArrRangeLst.add(lArrLCRange[0]);
					lArrRangeLst.add(lArrLCRange[1]);
					lArrRangeLst.add(lArrLCRange[2]);
					lArrRangeLst.add(lArrLCRange[3]);
				}
			}
			
			//lStrLCRange=(String)lArrLCRange[3];
			glogger.info("Splitted LC no is:"+lStrLCRange);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return lArrRangeLst;
	}
	
    //=========LC DIVISION DASHBOARD REPORT - 150019 ================================================================
	private List getLCDivisionDashboardReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);		
		glogger.info("-----------------INSIDE 150019---------------------------");
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrDivCode="";		
		
		ArrayList lArrReturnLst=null;		
		
		if(lObjReport.getParameterValue("divCode") !=null && !lObjReport.getParameterValue("divCode").toString().equals("") && !lObjReport.getParameterValue("divCode").toString().equals("-1"))
		{
			lStrDivCode=(String) lObjReport.getParameterValue("divCode");
			glogger.info("-------lStrDivCode   is-------- : "+ lStrDivCode);
		}		
		/*if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("dateto");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}*/
		
		
		// Set Current Date if Date is not entered
		/*Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150019 Current Date---------------------------"+currDate);
		
		if(lStrFrmDt.equals(""))
			lStrFrmDt=currDate;
		if(lStrToDt.equals(""))
			lStrToDt=currDate;*/
		
		glogger.info("-----------------INSIDE 150019 B4 Method Calling---------------------------");
		lArrReturnLst = lObjRptDAO.getLCDivisionDashboardReport(lStrDivCode,lStrFrmDt, lStrToDt, lLngLoginLangId, lIFinYrId,LstrLoginLocCode);
	
		String lStrReportNm="LC Division Dashboard";
		
		lObjReport.setReportName(lStrReportNm);
		
		return lArrReturnLst;
	}	
	
	//********************************* LC BALANCE APPROVAL REPORT -- 150021 ***********************************
	private List getLCBalanceApprovalReport(ReportVO lObjReport,long lLngLoginLangId,long lLngLoginPostId,String LstrLoginLocCode,long lIFinYrId)
	{
		LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);		
		glogger.info("-----------------INSIDE 150021---------------------------");
		String lStrFrmDt="";
		String lStrToDt="";
		String lStrArrDivCode[]=null;		
		
		ArrayList lArrReturnLst=null;		
		
		if(lObjReport.getParameterValue("divCode") !=null && !lObjReport.getParameterValue("divCode").toString().equals("") && !lObjReport.getParameterValue("divCode").toString().equals("-1"))
		{
			lStrArrDivCode=(String[]) lObjReport.getParameterValue("divCode");
			glogger.info("-------lStrArrDivCode   is-------- : "+ lStrArrDivCode);
		}		
		if(lObjReport.getParameterValue("datefrom") !=null && !lObjReport.getParameterValue("datefrom").toString().equals(""))
		{
			lStrFrmDt=(String) lObjReport.getParameterValue("datefrom");
			glogger.info("-------lStrFrmDt is-------- : "+ lStrFrmDt);
		}
		if(lObjReport.getParameterValue("dateto") !=null && !lObjReport.getParameterValue("dateto").toString().equals(""))
		{
			lStrToDt=(String) lObjReport.getParameterValue("dateto");
			glogger.info("-------lStrToDt is-------- : "+ lStrToDt);
		}
		
		
		// Set Current Date if Date is not entered
		Date currDt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currDate=sdf.format(currDt);
		glogger.info("-----------------INSIDE 150021 Current Date---------------------------"+currDate);
		
		if(lStrFrmDt.equals(""))
			lStrFrmDt=currDate;
		if(lStrToDt.equals(""))
			lStrToDt=currDate;
		
		glogger.info("-----------------INSIDE 150021getLCBalanceApprovalReport B4 Method Calling---------------------------");
		lArrReturnLst = lObjRptDAO.getLCBalanceApprovalReport(lStrArrDivCode,lStrFrmDt, lStrToDt, lLngLoginLangId, lIFinYrId,LstrLoginLocCode);
	
		String lStrReportNm="LC Balance Approval report from "+lStrFrmDt+" to "+lStrToDt;
		
		lObjReport.setReportName(lStrReportNm);
		
		String lStrTsryName=lObjRptDAO.getTsryNameByLoggedInTsryCode(LstrLoginLocCode);
		
		lObjReport.setParameterValue("Treasury",lStrTsryName);
		
		return lArrReturnLst;
	}	
	//******************************************************************************************************		
}
