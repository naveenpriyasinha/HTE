
package com.tcs.sgv.dss.report;


import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.dao.budget.BudFinYrDAOImpl;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.common.business.reports.ChartReportDataFinder;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.chart.data.ChartReportData;
import com.tcs.sgv.common.chart.data.ReportBarDataSet;
import com.tcs.sgv.common.dao.GrantDtlDAOImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.reports.CommonReportDAOImpl;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dss.dao.DSSQueryDAO;

public class DSSReportImpl extends DefaultReportDataFinder implements ChartReportDataFinder,
ReportDataFinder {
	private static final Logger glogger=Logger.getLogger(CommonReportDAOImpl.class);
		
		private SessionFactory lObjSessionFactory = null;
		
		//HashMap gHashChartMap=new HashMap();
		ArrayList lArrChart=new ArrayList();
		ArrayList lArrChartSubFund = new ArrayList();
		
		HashMap lHashChart = new HashMap();
		DSSQueryDAO lObjRptQueryDAO = null;
		DSSQueryDAO gObjRptQueryDAO = null;
		private StyleVO[] reportStyleVO = null;
		
		String gPlantype=new String();
		String gFinYear=new String();
		
		String gStrFromDate = null;
		String lStrPrevFromDate = null;
		String gStrToDate = null;
		String lStrPrevToDate = null;

		List gArrMainDataLst = null;

		double gdbBudPLFracPart = 0.0;
		double gdbBudNPFracPart = 0.0;
		double gdbBudCSSFracPart = 0.0;
		double gdbBudTotalFracPart = 0.0;
		double gdbGrantPLFracPart = 0.0;
		double gdbGrantNPFracPart = 0.0;
		double gdbGrantCSSFracPart = 0.0;
		double gdbGrantTotalFracPart = 0.0;
		double gdbCurrPLFracPart = 0.0;
		double gdbCurrNPFracPart = 0.0;
		double gdbCurrCSSFracPart = 0.0;
		double gdbCurrTotalFracPart = 0.0;
		double gdbPrgPLFracPart = 0.0;
		double gdbPrgNPFracPart = 0.0;
		double gdbPrgCSSFracPart = 0.0;
		double gdbPrgTotalFracPart = 0.0;

		long gdbBudPLTotal = 0;
		long gdbBudNPTotal = 0;
		long gdbBudCSSTotal = 0;
		long gdbBudTotalTotal = 0;
		long gdbGrantPLTotal = 0;
		long gdbGrantNPTotal = 0;
		long gdbGrantCSSTotal = 0;
		long gdbGrantTotalTotal = 0;
		long gdbCurrPLTotal = 0;
		long gdbCurrNPTotal = 0;
		long gdbCurrCSSTotal = 0;
		long gdbCurrTotalTotal = 0;
		long gdbPrgPLTotal = 0;
		long gdbPrgNPTotal = 0;
		long gdbPrgCSSTotal = 0;
		long gdbPrgTotalTotal = 0;

		long gBudPLRevRecp = 0;
		long gBudNPRevRecp = 0;
		long gBudCSSRevRecp = 0;
		long gBudTotalRevRecp = 0;
		long gGrantPLRevRecp = 0;
		long gGrantNPRevRecp = 0;
		long gGrantCSSRevRecp = 0;
		long gGrantTotalRevRecp = 0;
		long gCurrPLRevRecp = 0;
		long gCurrNPRevRecp = 0;
		long gCurrCSSRevRecp = 0;
		long gCurrTotalRevRecp = 0;
		long gPrgPLRevRecp = 0;
		long gPrgNPRevRecp = 0;
		long gPrgCSSRevRecp = 0;
		long gPrgTotalRevRecp = 0;
		
		ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dss/DSSConstants");
		ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/dss/DSSConstants");
		
		public ChartReportData getChartData( ReportVO report, Object criteria )  
		{
			ChartReportData dataset = null;
		
			try
			{
				findReportData(report,criteria);
				Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
				Map sessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
				glogger.info(" Request map ............. "  + requestAttributes.toString());
				LoginDetails loginVO = (LoginDetails)sessionAttributes.get("loginDetails");
				ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
				lObjSessionFactory = serviceLocator.getSessionFactory();
				lObjRptQueryDAO =(DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
				lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
				long langId = loginVO.getLangId();
				if( report.getReportCode().equals(lObjRsrcBndle.getString("REPORT.TopNMjrHD")) )
				{
					ArrayList arrchart = new ArrayList();
					arrchart = (ArrayList)getTopNMajorHead(report,langId);
					dataset = getTopNMajorHeadChart( report , arrchart);
					
				}
				else if(report.getReportCode().equals(lObjRsrcBndle.getString("REPORT.TopNDDO")) )
				{
					ArrayList arrchart = new ArrayList();
					arrchart = (ArrayList)getTopNDDOs(report,langId);
					dataset = getTopNDDOsChart( report , arrchart);
				}
				else if(report.getReportCode().equals(lObjRsrcBndle.getString("REPORT.TOPNBills")) )
				{
					ArrayList arrchart = new ArrayList();
					arrchart = (ArrayList)getTopNBills(report,langId);
					dataset = getTopNBillsChart( report , arrchart);
					
				}
				else if(report.getReportCode().equals(lObjRsrcBndle.getString("REPORT.TopNChallans")))
				{
					ArrayList arrchart = new ArrayList();
					arrchart = (ArrayList)getTopNChallans(report,langId);
					dataset = getTopNChallansChart( report , arrchart);
					
				}
				else if(report.getReportCode().equals(lObjRsrcBndle.getString("REPORT.TopNParty")) )
				{
					ArrayList arrchart = new ArrayList();
					arrchart = (ArrayList)getTopNParties(report,langId);
					dataset = getTopNPartiesChart( report , arrchart);
				}
				else if(report.getReportCode().equals(lObjRsrcBndle.getString("REPORT.TopNParty")) )
				{
					ArrayList arrchart = new ArrayList();
					arrchart = (ArrayList)getTopNParties(report,langId);
					dataset = getTopNPartiesChart( report , arrchart);
				}
				
				else if(report.getReportCode().equals(lObjRsrcBndle.getString("REPORT.TopNTreasuryByExp")))
				{
					ArrayList arrchart = new ArrayList();
					arrchart = (ArrayList)getTopNTreasuryByExp(report,langId);
					dataset = getTopNTreasuryExpChart( report , arrchart);
				}
				else if(report.getReportCode().equals(lObjRsrcBndle.getString("REPORT.TopNTreasuryByRec")))
				{
					ArrayList arrchart = new ArrayList();
					arrchart = (ArrayList)getTopNTreasuryByRec(report,langId);
					dataset = getTopNTreasuryRecChart( report , arrchart);
				}	
			
				if(report.getReportCode().equals(gObjRsrcBndle.getString("StateProfileReport")))
			 	{
			 		report.setReportName("StateProfileReport");
			 		getStateProfileReport(report);
			 		dataset = getStateProfileChart( report , lHashChart );
			 	}
			
				if(report.getReportCode().equals(gObjRsrcBndle.getString("SubFundReport")))
			 	{
			 		report.setReportName("SubFund Report");
			 		getSubFundReport(report);
			 		dataset = getSubFundChart( report , lArrChartSubFund );
			 	}
				
			 	if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSTotalReport")))
			 	{
			 		report.setReportName("DepartmentWise Plan/NonPlan/CSS Report");
			 		dataset = getShibirRatingChart( report , lArrChart );
			 	}
			 	else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSReport")))
			 	{	
			 		report.setReportName("DepartmentWise Plan/NonPlan/CSS Expenditure Report");
			 		dataset = getShibirRatingChart( report , lArrChart );
			 	}
			 	else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanExpenditureReport")))
			 	{	
			 		glogger.info("---Inside DeptWisePlanExpenditureReport of Chart---:");
			 		report.setReportName("DepartmentWise Plan Expenditure Report");
			 		dataset = getShibirRatingChart( report , lArrChart );
			 	}
			}
			 	catch(Exception e)
				{
					glogger.info("----------error---------" + e);
				}
			  
			return dataset;
		}
	
		public Collection findReportData(ReportVO report, Object criteria)
				throws ReportException 
		{	
			gObjRptQueryDAO =(DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			gObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
			
			
		//	ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dss/DSSConstants");
			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			Map sessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
			glogger.info(" Request map ............. "  + requestAttributes.toString());
			
			LoginDetails loginVO = (LoginDetails)sessionAttributes.get("loginDetails");
			Hashtable requestParam = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.REQUEST_KEYS );
			reportStyleVO = new StyleVO[1];
	   	 	reportStyleVO[0]=new StyleVO();
	   	 	reportStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
	   	 	reportStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 
	   	 	
			// get the SessionFactory instance for using it to fetch data...
			
	   	 
			
	   	 	long langId = loginVO.getLangId();
			long locID = loginVO.getLocation().getLocId();
			glogger.info("location Id is:-"+locID);
			
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			// get the SessionFactory instance for using it to fetch data...
			lObjSessionFactory = serviceLocator.getSessionFactory();
			lObjRptQueryDAO =(DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
			glogger.info(" loginVO............. "  + loginVO);
			List lArrReportData = new ArrayList();
			
			
		
			if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TopNDDO"))) // 
			{
				glogger.info("-------In the 160011");
				lArrReportData = getTopNDDOs(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TopNParty"))) // 
			{
				glogger.info("-------In the 160013");
				lArrReportData = getTopNParties(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TopNMjrHD"))) // 
			{
				glogger.info("-------In the 160015");
				lArrReportData = getTopNMajorHead(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TOPNBills"))) // 
			{
				glogger.info("-------In the 160017");
				lArrReportData = getTopNBills(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TopNChallans"))) // 
			{
				glogger.info("-------In the 160019");
				lArrReportData = getTopNChallans(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TopNTreasuryByExp"))) // 
			{
				glogger.info("-------In the 160021");
				lArrReportData = getTopNTreasuryByExp(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TopNTreasuryByRec"))) // 
			{
				glogger.info("-------In the 160023");
				lArrReportData = getTopNTreasuryByRec(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TopNTreasuryByExpD"))) // 
			{
				glogger.info("-------In the 160025");
				lArrReportData = getTopNTreasuryByExp(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TopNTreasuryByRecD"))) // 
			{
				glogger.info("-------In the 160027");
				lArrReportData = getTopNTreasuryByRec(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.TreasuryWiseExpRec"))) // 
			{	
				glogger.info("-------In the 160029");
				lArrReportData = getTreasury(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.SubTreasuryWiseExpRec"))) // 
			{
				glogger.info("-------In the 160037");
				lArrReportData = getsubTreasury(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.DDOWiseGrantExp"))) // 
			{
				glogger.info("-------In the 160039");
				lArrReportData = getDDO(report,langId);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("REPORT.DDOWiseBillDrilledOnExp")))
			{
				glogger.info("in the code 160041");
				lArrReportData = getDDOBill(report,langId);
			}
			
			//added By Maneesh
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanExpenditureReport"))) // DeptWise Report
			{
				glogger.info(" Inside DeptWisePlanExpenditureReport");
				lArrReportData =  getDeptRevCapPlanExp(report,criteria);
			}
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanExpMjrRpt"))) // DeptWise Report
			{
				lArrReportData =  getDeptRevCapPlanExpMjrHd(report,criteria);
			}
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanExpSchemeRpt"))) // DeptWise Report
			{
				lArrReportData =  getDeptRevCapPlanExpScheme(report,criteria);
			}
			
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("StateProfileReport"))) 
			{
				lArrReportData = getStateProfileReport(report);
			}	
			
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("SubFundReport"))) 
			{
				lArrReportData = getSubFundReport(report);
			}	
			
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSReport"))) 	
			{
		        int lintPlanType = Integer.parseInt(report.getParameterValue("plantype").toString());
				if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("PlanBudgetType")))
				{
					gPlantype="Plan";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("NonPlanBudgetType")))
				{
					gPlantype="NonPlan";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("CSSBudgetType")))
				{
					gPlantype="CSS";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("TotalBudgetType")))
				{
					gPlantype="Total";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("AllPlanType")))
				{
					gPlantype="All";
				}
				
				BudFinYrDAOImpl finYr=new BudFinYrDAOImpl();
				
				int lintFinYear = Integer.parseInt(report.getParameterValue("finYear").toString());
				glogger.info("IntFinYear is"+lintFinYear);
				try
				{
					Map map=finYr.getFinYrDesc(lintFinYear);
					gFinYear=map.get("NXT_YR").toString();	
					glogger.info("FinYear is"+gFinYear);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				report.setReportName("<b><U>Department Wise Plan/NonPlan/CSS Expenditure Report</U> <b> <br>  PlanType:"+ gPlantype +" <b><br> <b>FinYear:"+ gFinYear+"<b> ");
				lArrReportData = getDeptPlanNonPlanCSSRpt(report,criteria);
			}
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSTotalReport")))
			{
				glogger.info("Inside DeptWisePlanNonPlanCSSTotalReport");  
				int lintPlanType = Integer.parseInt(report.getParameterValue("plantype").toString());
				if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("AllPlanType")))
				{
					gPlantype="All";
				}
				
				BudFinYrDAOImpl finYr=new BudFinYrDAOImpl();
				
				int lintFinYear = Integer.parseInt(report.getParameterValue("finYear").toString());
				try
				{
					Map map=finYr.getFinYrDesc(lintFinYear);
					gFinYear=map.get("NXT_YR").toString();	
					glogger.info("FinYear is"+gFinYear);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				report.setReportName("<b><U>Department Wise Plan/NonPlan/CSS Expenditure Report</U> <b> <br>  PlanType:"+ gPlantype +" <b><br> <b>FinYear:"+ gFinYear+"<b> ");
				lArrReportData = getDeptPlanNonPlanCSSRpt(report,criteria);
			}
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCssMjrHdRpt"))) // MajorHeadWise Dept.Report
			{
				glogger.info("Inside DeptWisePlanNonPlanCssMjrHdRpt");
				
				int lintPlanType=Integer.parseInt(requestParam.get("planType").toString());
				if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("PlanBudgetType")))
				{
					gPlantype="Plan";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("NonPlanBudgetType")))
				{
					gPlantype="NonPlan";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("CSSBudgetType")))
				{
					gPlantype="CSS";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("TotalBudgetType")))
				{
					gPlantype="Total";
				}
				BudFinYrDAOImpl finYr=new BudFinYrDAOImpl();
				int lintFinYear = Integer.parseInt(requestParam.get("finYear").toString());
				try
				{
					Map map=finYr.getFinYrDesc(lintFinYear);
					gFinYear=map.get("NXT_YR").toString();	
					glogger.info("FinYear is"+gFinYear);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				
				report.setReportName("<b><U>Department Wise Plan/NonPlan/CSS Expenditure Report-MajorHeadWise</U> <b> <br>  PlanType:"+ gPlantype +"<b><br> FinYear:"+ gFinYear +" ");
				lArrReportData =  getDeptPlanNonPlanCssExpMjrHd(report,criteria);
			}
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCssSchemeRpt"))) // DeptWise Report
			{
				int lintPlanType=Integer.parseInt(requestParam.get("planType").toString());
				if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("PlanBudgetType")))
				{
					gPlantype="Plan";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("NonPlanBudgetType")))
				{
					gPlantype="NonPlan";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("CSSBudgetType")))
				{
					gPlantype="CSS";
				}
				else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("TotalBudgetType")))
				{
					gPlantype="Total";
				}
				BudFinYrDAOImpl finYr=new BudFinYrDAOImpl();
				int lintFinYear = Integer.parseInt(requestParam.get("finYear").toString());
				try
				{
					Map map=finYr.getFinYrDesc(lintFinYear);
					gFinYear=map.get("NXT_YR").toString();	
					glogger.info("FinYear is"+gFinYear);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				report.setReportName("<b><U>Department Wise Plan/NonPlan/CSS Expenditure Report-SchemeWise</U> <b> <br>  PlanType:"+ gPlantype +"<b><br> FinYear:"+ gFinYear +" ");
				lArrReportData =  getDeptPlanNonPlanCssExpScheme(report,criteria);
			}
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWiseTotalPlanNonPlanCssMjrHdRpt"))) // DeptWise Report
			{
				int lintPlanType=Integer.parseInt(requestParam.get("planType").toString());
				if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("AllPlanType")))
				{
					gPlantype="All";
				}
				
				BudFinYrDAOImpl finYr=new BudFinYrDAOImpl();
				
				int lintFinYear = Integer.parseInt(requestParam.get("finYear").toString());
				try
				{
					Map map=finYr.getFinYrDesc(lintFinYear);
					gFinYear=map.get("CUR_YR").toString();	
					glogger.info("FinYear is"+gFinYear);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				report.setReportName("<b><U>Department Wise Plan/NonPlan/CSS Expenditure Report-MajoHeadWise</U> <b> <br>  PlanType:"+ gPlantype +" <b><br> <b>FinYear:"+ gFinYear+"<b> ");
				lArrReportData =  getDeptTotalPlanNonPlanCssExpMjrHd(report,criteria);
			}
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWiseTotalPlanNonPlanCssSchemeRpt"))) // DeptWise Report
			{
				int lintPlanType=Integer.parseInt(requestParam.get("planType").toString());
				if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("AllPlanType")))
				{
					gPlantype="All";
				}
				
				BudFinYrDAOImpl finYr=new BudFinYrDAOImpl();
				
				int lintFinYear = Integer.parseInt(requestParam.get("finYear").toString());
				try
				{
					Map map=finYr.getFinYrDesc(lintFinYear);
					gFinYear=map.get("NXT_YR").toString();	
					glogger.info("FinYear is"+gFinYear);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				report.setReportName("<b><U>Department Wise Plan/NonPlan/CSS Expenditure Report-SchemeWise</U> <b> <br>  PlanType:"+ gPlantype +" <b><br> <b>FinYear:"+ gFinYear+"<b> ");
				lArrReportData =  getDeptTotalPlanNonPlanCssExpScheme(report,criteria);
			}
			else if(report.getReportCode().equals(gObjRsrcBndle.getString("InformationSubReport")))
			{
				lArrReportData = getSrcOfInfoData(report);
			}
			
			//Ended By Maneesh
			
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("StateProfileReport"))) 
			{
				lArrReportData = getStateProfileReport(report);
			}
			
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("SubFundReport"))) 
			{
				lArrReportData = getSubFundReport(report);
			}
			else if (report.getReportCode().equals(gObjRsrcBndle.getString("MajorHeadWiseStateProfileReport"))) 
			{
				lArrReportData = getMajorHeadWiseStateProfileReport(report);
			}

			return lArrReportData;
		}


				private List getTopNParties(ReportVO lObjReport,long langId)
		{
			glogger.info("In the gettopNparties report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				String ltimeDuration = lObjReport.getParameterValue("duration").toString();
				glogger.info("after gettting today"+ltimeDuration);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String fromDate = null;
				
				if(ltimeDuration.equals(lObjRsrcBndle.getString("today")))
				{
					fromDate = toDate;
				}
				else if(ltimeDuration.equals(lObjRsrcBndle.getString("tillDate")))
				{
					int year = Integer.parseInt(toDate.substring(0, 4));
					int month = Integer.parseInt(toDate.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
				
					fromDate = nyear+"-04-01"; 
				}
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				String ltopN = lObjReport.getParameterValue("topN").toString();
				glogger.info("top N are:-"+ltopN);
				lArrReturn =  rptQueryDAO.getTopNPartyDtlRpt(ltopN,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		private List getTopNMajorHead(ReportVO lObjReport, long langId)
		{
			glogger.info("In the getTopNMajorHead report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				glogger.info("b4 getting the value of the parameters");
				String ltimeDuration = lObjReport.getParameterValue("duration").toString();
				glogger.info("after gettting today"+ltimeDuration);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String fromDate = null;
				if(ltimeDuration.equals(lObjRsrcBndle.getString("today")))
				{
					fromDate = toDate;
				}
				else if(ltimeDuration.equals(lObjRsrcBndle.getString("tillDate")))
				{
					int year = Integer.parseInt(toDate.substring(0, 4));
					int month = Integer.parseInt(toDate.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
					fromDate = nyear+"-04-01"; 
				}
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				String loption = lObjReport.getParameterValue("option").toString();
				String ltopN = lObjReport.getParameterValue("topN").toString();

				glogger.info("top N are:-"+ltopN);
				lArrReturn =  rptQueryDAO.getTopNMjrheadDtlRpt(loption,ltopN,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		private List getTopNBills(ReportVO lObjReport,long langId)
		{
			glogger.info("In the getTopNBills report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				glogger.info("b4 getting the value of the parameters");
				String ltimeDuration = lObjReport.getParameterValue("duration").toString();
				glogger.info("after gettting today"+ltimeDuration);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String fromDate = null;
				if(ltimeDuration.equals(lObjRsrcBndle.getString("today")))
				{
					fromDate = toDate;
				}
				else if(ltimeDuration.equals(lObjRsrcBndle.getString("tillDate")))
				{
					int year = Integer.parseInt(toDate.substring(0, 4));
					int month = Integer.parseInt(toDate.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
				
					fromDate = nyear+"-04-01"; 
				}
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				
				String ltopN = lObjReport.getParameterValue("topN").toString();

				glogger.info("top N are:-"+ltopN);
				lArrReturn =  rptQueryDAO.getTopNBillsDtlRpt(ltopN,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		private List getTopNChallans(ReportVO lObjReport,long langId)
		{
			glogger.info("In the getTopNChallans report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{

				glogger.info("b4 getting the value of the parameters");
			
				String ltimeDuration = lObjReport.getParameterValue("duration").toString();
				glogger.info("after gettting today"+ltimeDuration);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String fromDate = null;
				if(ltimeDuration.equals(lObjRsrcBndle.getString("today")))
				{
					fromDate = toDate;
				}
				else if(ltimeDuration.equals(lObjRsrcBndle.getString("tillDate")))
				{
					int year = Integer.parseInt(toDate.substring(0, 4));
					int month = Integer.parseInt(toDate.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
					fromDate = nyear+"-04-01"; 
				}
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				
				String ltopN = lObjReport.getParameterValue("topN").toString();

				glogger.info("top N are:-"+ltopN);
				lArrReturn =  rptQueryDAO.getTopNChallansDtlRpt(ltopN,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		private List getTopNDDOs(ReportVO lObjReport, long langId)
		{
			glogger.info("In the getTopNDDOs report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				String ltimeDuration = lObjReport.getParameterValue("duration").toString();
				glogger.info("after gettting today"+ltimeDuration);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String fromDate = null;
				
				if(ltimeDuration.equals(lObjRsrcBndle.getString("today")))
				{
					fromDate = toDate;
				}
				else if(ltimeDuration.equals(lObjRsrcBndle.getString("tillDate")))
				{
					int year = Integer.parseInt(toDate.substring(0, 4));
					int month = Integer.parseInt(toDate.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
					fromDate = nyear+"-04-01"; 
				}
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				glogger.info("b4 getting the value of the parameters");
			
				String ltopN = lObjReport.getParameterValue("topN").toString();

				glogger.info("top N are:-"+ltopN);
				lArrReturn =  rptQueryDAO.getTopNDDOsDtlRpt(ltopN,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
			
		private List getTopNTreasuryByExp(ReportVO lObjReport,long langId)
		{
			glogger.info("In the get topn treasury by exp report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				glogger.info("b4 getting the value of the parameters");
				String ltopN = lObjReport.getParameterValue("topN").toString();
				glogger.info("top N are:-"+ltopN);
				glogger.info("b4 getting today");
				String ltimeDuration = lObjReport.getParameterValue("duration").toString();
				glogger.info("after gettting today"+ltimeDuration);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String fromDate = null;
				if(ltimeDuration.equals(lObjRsrcBndle.getString("today")))
				{
					fromDate = toDate;
				}
				else if(ltimeDuration.equals(lObjRsrcBndle.getString("tillDate")))
				{
					int year = Integer.parseInt(toDate.substring(0, 4));
					int month = Integer.parseInt(toDate.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
				
					fromDate = nyear+"-04-01"; 
				}
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				
				Date toDate1 = sdf.parse(toDate);
				toDate = sdf1.format(toDate1);
				
				Date fromDate1 = sdf.parse(fromDate);
				fromDate = sdf1.format(fromDate1);
				
				lObjReport.setReportName("Top "+ ltopN +"  Treasuries - Expenditure" + "<br>" + "Date From :" + fromDate + "   To: "+toDate);
				
				
				lArrReturn =  rptQueryDAO.getTopNTreasuryByExpDtlRpt(ltopN,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		private List getTopNTreasuryByRec(ReportVO lObjReport,long langId)
		{
			glogger.info("In the top N treasury by receipt report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				glogger.info("b4 getting the value of the parameters");
				String ltopN = lObjReport.getParameterValue("topN").toString();
				String ltimeDuration = lObjReport.getParameterValue("duration").toString();
				glogger.info("after gettting today"+ltimeDuration);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String fromDate = null;
				
				if(ltimeDuration.equals(lObjRsrcBndle.getString("today")))
				{
					fromDate = toDate;
				}
				else if(ltimeDuration.equals(lObjRsrcBndle.getString("tillDate")))
				{
					int year = Integer.parseInt(toDate.substring(0, 4));
					int month = Integer.parseInt(toDate.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
				
					fromDate = nyear+"-04-01"; 
				}
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				
				Date toDate1 = sdf.parse(toDate);
				toDate = sdf1.format(toDate1);
				
				Date fromDate1 = sdf.parse(fromDate);
				fromDate = sdf1.format(fromDate1);
				
				lObjReport.setReportName("Top "+ ltopN +"  Treasuries - Receipt" + "<br>" + "Date From :" + fromDate + "   To: "+toDate);
				
				
				lArrReturn =  rptQueryDAO.getTopNTreasuryByRecDtlRpt(ltopN,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		public ArrayList sortExp(ArrayList locId)
		{
			glogger.info("in the get objection method");
			ArrayList arrInner = new ArrayList();
			ArrayList arrInner1 = new ArrayList();
			glogger.info("Loc size is "+locId);
			for(int i=0;i<locId.size();i++)
			{
				for(int j=i+1;j<locId.size();j++)
				{
					arrInner = (ArrayList)locId.get(i);
					arrInner1 = (ArrayList)locId.get(j);
					long temp1 = Long.parseLong(arrInner.get(1).toString());
					long temp2 = Long.parseLong(arrInner1.get(1).toString());
					glogger.info("values are "+ temp1+" "+ temp2);
					if(temp1 <= temp2)
					{
						locId.set(i, arrInner1);
						locId.set(j, arrInner);
					}
				}
			}
			glogger.info("sorted list is "+locId);
			return locId;
		}

		private List getTreasury(ReportVO lObjReport,long langId)
		{
			glogger.info("In the get topn treasury by exp report");
			String toDate2 = null , fromDate2 = null; ;
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				glogger.info("b4 getting the value of the parameters");
				String fromDate1 = lObjReport.getParameterValue("dateRangefrom").toString();
				String fromDate = null;
				String toDate = null;
				String toDay = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				if(fromDate1.length() == 0)
				{
					
					int year = Integer.parseInt(toDay.substring(0, 4));
					int month = Integer.parseInt(toDay.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
					fromDate = nyear+"-04-01"; 
				}
				else
				{
					fromDate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
				}
				glogger.info("---------------------------------------------"+fromDate+"----------------------");
				String toDate1 = lObjReport.getParameterValue("dateRangeto").toString();
				if(toDate1.length() == 0)
				{
					glogger.info(" in the empty toDate");
					toDate = toDay;
				}
				else
				{
					toDate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
				}
				
				toDate2 = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(toDate));
				fromDate2 = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate));
				
				glogger.info("------------- to Date"+ toDate+"----------------");
				String lOffice = lObjReport.getParameterValue("Office").toString();
				glogger.info("office is :-"+lOffice);
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				
				lObjReport.setReportName("Treasury Report <br>" + rptQueryDAO.getTreasuryName(lOffice) + "<br>" + " Date From - " + fromDate2 + "   To - " + toDate2 + " <br> " );
				
				lArrReturn =  rptQueryDAO.getTreasuryRpt(lOffice,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		private List getsubTreasury(ReportVO lObjReport,long langId)
		{
			glogger.info("In the get sub treasury by exp report");
			String toDate2 = null , fromDate2 = null;
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				
				glogger.info("b4 getting the value of the parameters");
				String lOffice = lObjReport.getParameterValue("locId").toString();
				glogger.info("office is :-"+lOffice);
				
				glogger.info("the report name is " + rptQueryDAO.getTreasuryName(lOffice) + "<br>"+ lObjReport.getReportName());
				
				
				String fromDate = lObjReport.getParameterValue("fromDate").toString();
				glogger.info("office is :-"+fromDate);
				String toDate = lObjReport.getParameterValue("toDate").toString();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				
				toDate2 = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(toDate));
				fromDate2 = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate));
				
				
				
				lObjReport.setReportName(  "  Treasury Report  "+ "<br>"+rptQueryDAO.getTreasuryName(lOffice)+ "<br>" + "  Date From :" + fromDate2 + "  To :" + toDate2 ) ;
				glogger.info("office is :-"+toDate);
				lArrReturn =  rptQueryDAO.getsubTreasuryRpt(lOffice,toDate,fromDate,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		
		private List getDDO(ReportVO lObjReport,long langId)
		{
			glogger.info("In the get topn treasury by exp report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				glogger.info("b4 getting the value of the parameters");
				String fromDate1 = lObjReport.getParameterValue("fromDate").toString();
				String fromDate = null;
				String toDate = null;
				String toDay = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				if(fromDate1.length() == 0)
				{
					glogger.info(" in teh empty fromdate");
					int year = Integer.parseInt(toDay.substring(0, 4));
					int month = Integer.parseInt(toDay.substring(5, 7));
					glogger.info("year is "+year+" month is "+month);
					int nyear = year;
					if(month <=3 && month >=1)
					{
						nyear = year -1;
					}
					fromDate = nyear+"-04-01"; 
				}
				else
				{
					fromDate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
				}
				glogger.info("---------------------------------------------"+fromDate+"----------------------");
				String toDate1 = lObjReport.getParameterValue("toDate").toString();
				if(toDate1.length() == 0)
				{
					glogger.info(" in the empty toDate");
					toDate = toDay;
				}
				else
				{
					toDate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
				}
			
				String lOffice = lObjReport.getParameterValue("office").toString();
				glogger.info("office is :-"+lOffice);
				String lDDO = lObjReport.getParameterValue("ddo").toString();
				glogger.info("DDO is :-"+lDDO);
				String lfinYear = lObjReport.getParameterValue("finYear").toString();
				glogger.info("DDO is :-"+lfinYear);
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				
				Date toDate2 = sdf.parse(toDate);
				toDate = sdf1.format(toDate2);
				
				Date fromDate2 = sdf.parse(fromDate);
				fromDate = sdf1.format(fromDate2);
				
				lObjReport.setReportName("DDO Report" + "<br>" + "Date From :" + fromDate + "   To: "+toDate + "<br>" + rptQueryDAO.getTreasuryName(lOffice)+rptQueryDAO.getDDOName(lDDO));
				
				
				
				lArrReturn =  rptQueryDAO.getDDORpt(lOffice,toDate,fromDate,lDDO,lfinYear,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		private List getDDOBill(ReportVO lObjReport,long langId)
		{
			glogger.info("In the get topn treasury by exp report");
			List lArrReturn = new ArrayList();
			DSSQueryDAO rptQueryDAO =  (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			rptQueryDAO.setSessionFactory(lObjSessionFactory);
			try
			{
				glogger.info("b4 getting the value of the parameters");
				String lOffice = null;
				String fromDate = lObjReport.getParameterValue("fromDate").toString();
				glogger.info("fromDate is :-"+fromDate);
				String toDate = lObjReport.getParameterValue("toDate").toString();
				glogger.info("toDate is :-"+toDate);
				String lDDO = lObjReport.getParameterValue("ddoCode").toString();
				glogger.info("DDO is :-"+lDDO);
				glogger.info(" -------to Date is "+ toDate);
				glogger.info("----------------from Date is "+ fromDate);
				lArrReturn =  rptQueryDAO.getDDOBillRpt(lOffice,toDate,fromDate,lDDO,langId);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lArrReturn;
		}
		
		/* for the chart */
		public ReportBarDataSet getTopNMajorHeadChart( ReportVO report , ArrayList arrchart )
		{
				ReportBarDataSet barDataSet = new ReportBarDataSet(  );
				//report.setExportFormat( IReportConstants.BAR_CHART );
				try
				{
					if(arrchart != null)
					{
						glogger.info("the size of the arr is "+arrchart);	
						for(int i=0; i<(arrchart.size() - 1 ); i++ )
							{
								ArrayList arrInner = new ArrayList();
								arrInner = (ArrayList)arrchart.get(i);
								glogger.info(" the inner array is "+arrInner);
								barDataSet.addValue(Double.parseDouble(arrInner.get(3).toString()), "Top N MajarHead", arrInner.get(2).toString());
							}
					}
					
				}catch(Exception e)
				{
					glogger.info("Exception "+e);
				}
				return barDataSet;
		}
		
		public ReportBarDataSet getTopNDDOsChart( ReportVO report , ArrayList arrchart )
		{
				ReportBarDataSet barDataSet = new ReportBarDataSet(  );
				//report.setExportFormat( IReportConstants.BAR_CHART );
				try
				{

					if(arrchart != null)
					{
						glogger.info("the size of the arr is "+arrchart);	
						for(int i=0; i<arrchart.size(); i++ )
							{
								
								
								ArrayList arrInner = new ArrayList();
								arrInner = (ArrayList)arrchart.get(i);
								glogger.info(" the inner array is "+arrInner);
								barDataSet.addValue(Double.parseDouble(arrInner.get(3).toString()), "Top N DDO", arrInner.get(1).toString());
								
							}
					}
					
				}catch(Exception e)
				{
					glogger.info("Exception "+e);
				}
				return barDataSet;
		}
		
		public ReportBarDataSet getTopNBillsChart( ReportVO report , ArrayList arrchart )
		{
				ReportBarDataSet barDataSet = new ReportBarDataSet(  );
				//report.setExportFormat( IReportConstants.BAR_CHART );
				try
				{

					if(arrchart != null)
					{
						glogger.info("the size of the arr is "+arrchart);	
						for(int i=0; i<(arrchart.size()-1); i++ )
							{
								ArrayList arrInner = new ArrayList();
								arrInner = (ArrayList)arrchart.get(i);
								glogger.info(" the inner array is "+arrInner);
								barDataSet.addValue(Double.parseDouble(arrInner.get(3).toString()), "Top N Bills", arrInner.get(1).toString());
								
							}
					}
					
				}catch(Exception e)
				{
					glogger.info("Exception "+e);
				}
				return barDataSet;
		}
		public ReportBarDataSet getTopNChallansChart( ReportVO report , ArrayList arrchart )
		{
				ReportBarDataSet barDataSet = new ReportBarDataSet(  );
				//report.setExportFormat( IReportConstants.BAR_CHART );
				try
				{

					if(arrchart != null)
					{
						glogger.info("the size of the arr is "+arrchart);	
						for(int i=0; i< (arrchart.size() - 1); i++ )
							{
								
								
								ArrayList arrInner = new ArrayList();
								arrInner = (ArrayList)arrchart.get(i);
								glogger.info(" the inner array is "+arrInner);
								barDataSet.addValue(Double.parseDouble(arrInner.get(3).toString()), "Top N Challans", arrInner.get(1).toString());
								
							}
					}
					
				}catch(Exception e)
				{
					glogger.info("Exception "+e);
				}
				return barDataSet;
		}
		public ReportBarDataSet getTopNPartiesChart( ReportVO report , ArrayList arrchart )
		{
				

				ReportBarDataSet barDataSet = new ReportBarDataSet(  );
				//report.setExportFormat( IReportConstants.BAR_CHART );
				try
				{
					
					if(arrchart != null)
					{
						glogger.info("the size of the arr is "+arrchart);	
						for(int i=0; i<(arrchart.size() - 1); i++ )
							{
								ArrayList arrInner = new ArrayList();
								arrInner = (ArrayList)arrchart.get(i);
								glogger.info(" the inner array is "+arrInner);
								barDataSet.addValue(Double.parseDouble(arrInner.get(4).toString()), "Top N Parties", arrInner.get(1).toString());
								
							}
					}
					
				}catch(Exception e)
				{
					glogger.info("Exception "+e);
				}
				return barDataSet;
		}
		public ReportBarDataSet getTopNTreasuryExpChart( ReportVO report , ArrayList arrchart )
		{
				
				ReportBarDataSet barDataSet = new ReportBarDataSet(  );
				//report.setExportFormat( IReportConstants.BAR_CHART );
				try
				{
				
					if(arrchart != null)
					{
						glogger.info("the size of the arr is "+arrchart);	
						for(int i=0; i<arrchart.size(); i++ )
							{
								ArrayList arrInner = new ArrayList();
								arrInner = (ArrayList)arrchart.get(i);
								glogger.info(" the inner array is "+arrInner);
								barDataSet.addValue(Double.parseDouble(arrInner.get(2).toString()), "Expenditure", arrInner.get(1).toString());
								barDataSet.addValue(Double.parseDouble(arrInner.get(3).toString()), "Receipt", arrInner.get(1).toString());
								
							}
					}
					
				}catch(Exception e)
				{
					glogger.info("Exception "+e);
				}
				return barDataSet;
		}
		
		public ReportBarDataSet getTopNTreasuryRecChart( ReportVO report , ArrayList arrchart )
		{
				ReportBarDataSet barDataSet = new ReportBarDataSet(  );
				//report.setExportFormat( IReportConstants.BAR_CHART );
				try
				{
					
					if(arrchart != null)
					{
						glogger.info("the size of the arr is "+arrchart);	
						for(int i=0; i<arrchart.size(); i++ )
							{
								ArrayList arrInner = new ArrayList();
								arrInner = (ArrayList)arrchart.get(i);
								glogger.info(" the inner array is "+arrInner);
								barDataSet.addValue(Double.parseDouble(arrInner.get(2).toString()), "Receipt", arrInner.get(1).toString());
								barDataSet.addValue(Double.parseDouble(arrInner.get(3).toString()), "Expenditure", arrInner.get(1).toString());
							}
					}
				}catch(Exception e)
				{
					glogger.info("Exception "+e);
				}
				return barDataSet;
		}
		/* End By Shyamal */
		
	
		
	  /* Added By Maneesh*/
		
		public ReportBarDataSet getStateProfileChart(ReportVO report,HashMap alist)
		{
			
				HashMap lHashMap = new HashMap();
				ReportBarDataSet barDataSet = new ReportBarDataSet();
				
				barDataSet.addValue(Double.parseDouble(alist.get("gdbBudTotalTotal").toString()), "Budget","Consolidated");
				
				barDataSet.addValue(Double.parseDouble(alist.get("gdbGrantTotalTotal").toString()), "Grant", "Consolidated");
		
				barDataSet.addValue(Double.parseDouble(alist.get("gdbCurrTotalTotal").toString()), "CurrentExp.", "Consolidated");
				
				barDataSet.addValue(Double.parseDouble(alist.get("gdbPrgTotalTotal").toString()), "ProgExp.", "Consolidated");
				
				
			
				barDataSet.addValue(Double.parseDouble(alist.get("ConFund_Bud_Total").toString()), "Budget","Contigency");
				
				barDataSet.addValue(Double.parseDouble(alist.get("ConFund_Grant_Total").toString()), "Grant", "Contigency");
		
				barDataSet.addValue(Double.parseDouble(alist.get("ConFund_Curr_Total").toString()), "CurrentExp.", "Contigency");
				
				barDataSet.addValue(Double.parseDouble(alist.get("ConFund_Prg_Total").toString()), "ProgExp.", "Contigency");
				
			
				
				barDataSet.addValue(Double.parseDouble(alist.get("PubFund_Bud_Total").toString()), "Budget","PublicFund");
				
				barDataSet.addValue(Double.parseDouble(alist.get("PubFund_Grant_Total").toString()), "Grant", "PublicFund");
		
				barDataSet.addValue(Double.parseDouble(alist.get("PubFund_Curr_Total").toString()), "CurrentExp.", "PublicFund");
				
				barDataSet.addValue(Double.parseDouble(alist.get("PubFund_Prg_Total").toString()), "ProgExp.", "PublicFund");
				
				
			try
			{
				
				glogger.info("bardata set is "+barDataSet.getColumnCount());
				glogger.info("bar data's"+barDataSet.getRowCount());
				glogger.info("After setting values...");
				
			}catch(Exception e)
			{
				glogger.info("Exception "+e);
			}
			return barDataSet;
				
		}
		
		public ReportBarDataSet getSubFundChart(ReportVO report,ArrayList alist)
		{
			HashMap lHashMap = null;
			ReportBarDataSet barDataSet = new ReportBarDataSet();
			glogger.info("hi i  am in getSubFundChart Funciton -----------------------------------");
			try
			{
				glogger.info("the size of the arrlist is " + alist.size() );
				
				for(int i = 0 ; i<= alist.size() ; i++ )
				{
					lHashMap = (HashMap)alist.get(i);
					
					glogger.info(" this is the " + i +" th time ");
					
					
					glogger.info("the bud vlaue " +  lHashMap.get("subFund_Bud").toString());
					glogger.info("the bud vlaue " +  lHashMap.get("subFund_Grant").toString());
					glogger.info("the bud vlaue " +  lHashMap.get("subFund_Curr").toString());
					glogger.info("the bud vlaue " +  lHashMap.get("subFund_Prg").toString());
					
			//		glogger.info("the sub fund Lable " +  lHashMap.get("SubFundLable").toString());
					
					barDataSet.addValue(Double.parseDouble(lHashMap.get("subFund_Bud").toString()), "Budget",lHashMap.get("SubFundLabel").toString());
					barDataSet.addValue(Double.parseDouble(lHashMap.get("subFund_Grant").toString()), "Grant",lHashMap.get("SubFundLabel").toString());
					barDataSet.addValue(Double.parseDouble(lHashMap.get("subFund_Curr").toString()), "CurrentExp.", lHashMap.get("SubFundLabel").toString());
					barDataSet.addValue(Double.parseDouble(lHashMap.get("subFund_Prg").toString()), "ProgExp.",lHashMap.get("SubFundLabel").toString());
					
			/*		barDataSet.addValue(Double.parseDouble(lHashMap.get("subFund_Bud").toString()), "Budget","Hi");
					glogger.info("dopne " + i);
					barDataSet.addValue(Double.parseDouble(lHashMap.get("subFund_Grant").toString()), "Grant","Hi");
					glogger.info("dopne " + i);
					barDataSet.addValue(Double.parseDouble(lHashMap.get("subFund_Curr").toString()), "CurrentExp.", "Hi");
					glogger.info("dopne " + i);
					barDataSet.addValue(Double.parseDouble(lHashMap.get("subFund_Prg").toString()), "ProgExp.","Hi");
					glogger.info("dopne " + i);*/
			
					
				}
				
				glogger.info("bardata set is "+barDataSet.getColumnCount());
				glogger.info("bar data's"+barDataSet.getRowCount());
				glogger.info("After setting values...");
				
			}catch(Exception e)
			{
				glogger.info("Exception "+e);
			}
			return barDataSet;
				
		}
		
		
		public ReportBarDataSet getShibirRatingChart(ReportVO report,ArrayList alist)
		{
			
				HashMap lHashMap = new HashMap();
				ReportBarDataSet barDataSet = new ReportBarDataSet();
			
				try
				{
					glogger.info("hi i am in reportbardataset--------------------------------------" +  alist.size());
					
					for(int i = 0 ; i < 5; i++)
					{
						lHashMap = (HashMap) alist.get(i);
						if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSTotalReport")))
						{
							barDataSet.addValue(Double.parseDouble(lHashMap.get("BudgetTotal").toString()), "Budget",lHashMap.get("DeptName").toString());
							
							barDataSet.addValue(Double.parseDouble(lHashMap.get("GrantTotal").toString()), "Grant", lHashMap.get("DeptName").toString());
					
							barDataSet.addValue(Double.parseDouble(lHashMap.get("CurrentTotal").toString()), "CurrentExp.", lHashMap.get("DeptName").toString());
						}
						
						else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSReport")))	
						{
							barDataSet.addValue(Double.parseDouble(lHashMap.get("BudgetTotal").toString()), "Budget",lHashMap.get("DeptName").toString());
							
							barDataSet.addValue(Double.parseDouble(lHashMap.get("GrantTotal").toString()), "Grant", lHashMap.get("DeptName").toString());
					
							barDataSet.addValue(Double.parseDouble(lHashMap.get("CurrentExp").toString()), "CurrentExp.", lHashMap.get("DeptName").toString());
						}
						
						else if(report.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanExpenditureReport")))
						{

							barDataSet.addValue(Double.parseDouble(lHashMap.get("BudgetTotal").toString()), "Budget",lHashMap.get("DeptName").toString());
							
							barDataSet.addValue(Double.parseDouble(lHashMap.get("BudgetTotal").toString()), "Grant", lHashMap.get("DeptName").toString());
					
							barDataSet.addValue(Double.parseDouble(lHashMap.get("RevenueExp").toString()), "RevenueExp.", lHashMap.get("DeptName").toString());
							
							barDataSet.addValue(Double.parseDouble(lHashMap.get("CapitalExp").toString()), "CurrentExp.", lHashMap.get("DeptName").toString());
						}
							
					}
				
					glogger.info("bardata set is "+barDataSet.getColumnCount());
					glogger.info("bar data's"+barDataSet.getRowCount());
					glogger.info("After setting values...");
				}catch(Exception e)
				{
					glogger.info("Exception "+e);
				}
				return barDataSet;
		}

		
		private List getDeptRevCapPlanExpScheme(ReportVO lObjReport, Object criteria)
		{
			glogger.info("inside getDeptRevCapPlanExpScheme");
			List<ArrayList> lArrReturn = new ArrayList<ArrayList>();
			//HashMap lHshMapDeptMjrHd=null;
			HashMap lhshDeptCurrRevExpAmt=new HashMap();
			HashMap lhshDeptCurrCapExpAmt=new HashMap();
			ArrayList lArrDeptMjrHd=null;
			int lintLangId = 0;
			long ldiffDays=0;

			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);

			HashMap<String,Object> lHashPara=new HashMap<String,Object>();

	    	Hashtable requestParam = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.REQUEST_KEYS );

			glogger.info(requestParam.get("finYear")+"--"+requestParam.get("DeptId")+"--"+
					requestParam.get("planType")+"--"+
					requestParam.get("fromDate")+"--"+requestParam.get("toDate")+"--"+
					requestParam.get("diffdays"));

			lHashPara=getParameters(lObjReport, criteria);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			lintLangId=Integer.parseInt(lHashPara.get("LangId").toString());

			try
			{
				lArrDeptMjrHd = lObjRptQueryDAO.getAllSchemesForDept(lHashPara); 
			}
			catch(Exception e)
			{
				glogger.error("Error in execution of getAllSchemesForDept and Error is : " + e,e);
				e.printStackTrace();
			}


			HashMap lhshEstimatedAmt=lObjRptQueryDAO.getBudEstForScheme(lHashPara);

			HashMap lhshGrantReleaseAmt=lObjRptQueryDAO.getGrantForScheme(lHashPara);

			ArrayList lArrRevExpLst = lObjRptQueryDAO.getMjrRangeForFundName(gObjRsrcBndle.getString("RevFundType"));
			if(!lArrRevExpLst.isEmpty())
			{
				if(lArrRevExpLst.size()>1)
				{
					lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
					lHashPara.put("MjrEndHd",lArrRevExpLst.get(1));
				}
				else
					lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
			}
			else
			{
				glogger.info("MjrhdRange could not be found");
			}


			HashMap lhshDeptRevExpAmt= lObjRptQueryDAO.getExpForSchemeMjrHd(lHashPara);

			ArrayList lArrCapExpLst = lObjRptQueryDAO.getMjrRangeForFundName(gObjRsrcBndle.getString("CapFundType"));
			if(!lArrCapExpLst.isEmpty())
			{
				if(lArrCapExpLst.size()>1)
				{
					lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
					lHashPara.put("MjrEndHd",lArrCapExpLst.get(1));
				}
				else
					lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
			}
			else
			{
				glogger.info("MjrhdRange could not be found");
			}

			HashMap lhshDeptCapExpAmt= lObjRptQueryDAO.getExpForSchemeMjrHd(lHashPara);
			glogger.info("After capital expenditure");

			lHashPara.put("FDate",lHashPara.get("PrgDate"));
			if(!lArrRevExpLst.isEmpty())
			{
				if(lArrRevExpLst.size()>1)
				{
					lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
					lHashPara.put("MjrEndHd",lArrRevExpLst.get(1));
				}
				else
					lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
			}
			else
			{
				glogger.info("MjrhdRange could not be found");
			}

			lhshDeptCurrRevExpAmt= lObjRptQueryDAO.getExpForSchemeMjrHd(lHashPara);

			if(!lArrCapExpLst.isEmpty())
			{
				if(lArrCapExpLst.size()>1)
				{
					lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
					lHashPara.put("MjrEndHd",lArrCapExpLst.get(1));
				}
				else
					lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
			}
			else
			{
				glogger.info("MjrhdRange could not be found");
			}

			lhshDeptCurrCapExpAmt= lObjRptQueryDAO.getExpForSchemeMjrHd(lHashPara);

			lHashPara.put("FDate",lHashPara.get("StartDate"));

			lArrReturn = getDataList(lArrDeptMjrHd, lhshEstimatedAmt, lhshGrantReleaseAmt,
						lhshDeptRevExpAmt, lhshDeptCapExpAmt,lhshDeptCurrRevExpAmt,lhshDeptCurrCapExpAmt, lHashPara, lObjReport.getReportCode());
			return lArrReturn;			
		}		
		private List getDeptRevCapPlanExpMjrHd(ReportVO lObjReport, Object criteria) 
		{
			glogger.info("inside getDeptRevCapPlanExpMjrHd");
			List<ArrayList> lArrReturn = new ArrayList<ArrayList>();
			//HashMap lHshMapDeptMjrHd=null;
			HashMap lhshDeptCurrRevExpAmt=new HashMap();
			HashMap lhshDeptCurrCapExpAmt=new HashMap();
			ArrayList lArrDeptMjrHd=null;
			int lintLangId=0;

			long ldiffDays=0;

			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);

			HashMap<String,Object> lHashPara=new HashMap<String,Object>();

	    	Hashtable requestParam = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.REQUEST_KEYS );

			glogger.info(requestParam.get("finYear")+"-->"+requestParam.get("planType")+"-->"+
					lObjReport.getLangId()+"-->"+requestParam.get("DeptId")+"-->"+
					requestParam.get("fromDate")+"-->"+requestParam.get("toDate")+"-->"+
					requestParam.get("diffdays"));
			lHashPara=getParameters(lObjReport, criteria);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			lintLangId=Integer.parseInt(lHashPara.get("LangId").toString());

			try
			{
				lArrDeptMjrHd = lObjRptQueryDAO.getAllMjrHdsForDept(lHashPara); 
			}
			catch(Exception e)
			{
				glogger.error("Error in execution of getAllMjrHdsForDept and Error is : " + e,e);
				e.printStackTrace();
			}


			HashMap lhshEstimatedAmt=lObjRptQueryDAO.getBudEstForMjrHd(lHashPara);

			HashMap lhshGrantReleaseAmt=lObjRptQueryDAO.getGrantForMjrHd(lHashPara);


			ArrayList lArrRevExpLst = lObjRptQueryDAO.getMjrRangeForFundName(gObjRsrcBndle.getString("RevFundType"));
			if(!lArrRevExpLst.isEmpty())
			{
				if(lArrRevExpLst.size()>1)
				{
					lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
					lHashPara.put("MjrEndHd",lArrRevExpLst.get(1));
				}
				else
					lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
			}
			else
			{
				glogger.info("MjrhdRange could not be found");
			}
			HashMap lhshDeptRevExpAmt= lObjRptQueryDAO.getExpForDeptMjrHd(lHashPara);


			ArrayList lArrCapExpLst = lObjRptQueryDAO.getMjrRangeForFundName(gObjRsrcBndle.getString("CapFundType"));
			if(!lArrCapExpLst.isEmpty())
			{
				if(lArrCapExpLst.size()>1)
				{
					lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
					lHashPara.put("MjrEndHd",lArrCapExpLst.get(1));
				}
				else
					lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
			}
			else
			{
				glogger.info("MjrhdRange could not be found");
			}
			HashMap lhshDeptCapExpAmt= lObjRptQueryDAO.getExpForDeptMjrHd(lHashPara);
			glogger.info("After capital expenditure");

			lHashPara.put("FDate",lHashPara.get("PrgDate"));
			if(!lArrRevExpLst.isEmpty())
			{
				if(lArrRevExpLst.size()>1)
				{
					lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
					lHashPara.put("MjrEndHd",lArrRevExpLst.get(1));
				}
				else
					lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
			}
			else
			{
				glogger.info("MjrhdRange could not be found");
			}
			lhshDeptCurrRevExpAmt=lObjRptQueryDAO.getExpForDeptMjrHd(lHashPara);

			if(!lArrCapExpLst.isEmpty())
			{
				if(lArrCapExpLst.size()>1)
				{
					lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
					lHashPara.put("MjrEndHd",lArrCapExpLst.get(1));
				}
				else
					lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
			}
			else
			{
				glogger.info("MjrhdRange could not be found");
			}
			lhshDeptCurrCapExpAmt= lObjRptQueryDAO.getExpForDeptMjrHd(lHashPara);
			lHashPara.put("FDate",lHashPara.get("StartDate"));

			lArrReturn = getDataList(lArrDeptMjrHd, lhshEstimatedAmt, lhshGrantReleaseAmt,
						lhshDeptRevExpAmt, lhshDeptCapExpAmt,lhshDeptCurrRevExpAmt,lhshDeptCurrCapExpAmt,lHashPara, lObjReport.getReportCode());
			return lArrReturn;			
		}		
		private List getDeptRevCapPlanExp(ReportVO lObjReport,Object criteria) 
		{
				glogger.info("inside getDeptRevCapPlanExp");
				List<ArrayList> lArrReturn = new ArrayList<ArrayList>();
				HashMap lhshDeptCurrRevExpAmt=new HashMap();
				HashMap lhshDeptCurrCapExpAmt=new HashMap();
				ArrayList lArrDept=null;
				int lintLangId=0;
				long lFinYear=0;
				long ldiffDays = 0;

				glogger.info("b4 ");
				HashMap<String,Object> lHashPara=getParameters(lObjReport, criteria);
				ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
				String ToDate=lObjReport.getParameterValue("Dateto").toString();
				lFinYear=Long.parseLong(lObjReport.getParameterValue("finYear").toString());

				try
				{
					lintLangId=Integer.parseInt(lHashPara.get("LangId").toString());
					glogger.info("Int LangId is:"+lintLangId);
					lArrDept = lObjRptQueryDAO.getAllDept(lintLangId); 
				}
				catch(Exception e)
				{
					glogger.error("Error in execution of getAllDept and Error is : " + e,e);
					e.printStackTrace();
				}

				glogger.info("Before lhshEstimatedAmt");
				HashMap lhshEstimatedAmt=lObjRptQueryDAO.getBudEstForDept(lHashPara);
				glogger.info("After lhshEstimatedAmt");
				HashMap lhshGrantReleaseAmt=lObjRptQueryDAO.getDeptGrantAmt(lHashPara);
				glogger.info("After lhshGrantReleaseAmt");

				ArrayList lArrRevExpLst = lObjRptQueryDAO.getMjrRangeForFundName(gObjRsrcBndle.getString("RevFundType"));
				if(!lArrRevExpLst.isEmpty())
				{
					if(lArrRevExpLst.size()>1)
					{
						lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
						lHashPara.put("MjrEndHd",lArrRevExpLst.get(1));
					}
					else
						lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
				}
				else
				{
					glogger.info("MjrhdRange could not be found");
				}
				HashMap lhshDeptRevExpAmt= lObjRptQueryDAO.getExpForDept(lHashPara);


				ArrayList lArrCapExpLst = lObjRptQueryDAO.getMjrRangeForFundName(gObjRsrcBndle.getString("CapFundType"));
				if(!lArrCapExpLst.isEmpty())
				{
					if(lArrCapExpLst.size()>1)
					{
						lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
						lHashPara.put("MjrEndHd",lArrCapExpLst.get(1));
					}
					else
						lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
				}
				else
				{
					glogger.info("MjrhdRange could not be found");
				}
				HashMap lhshDeptCapExpAmt= lObjRptQueryDAO.getExpForDept(lHashPara);
				glogger.info("FirstCall Over;");

				lHashPara.put("FDate",lHashPara.get("PrgDate"));
				glogger.info("Second Call Begins;");

				if(!lArrRevExpLst.isEmpty())
				{
					if(lArrRevExpLst.size()>1)
					{
						lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
						lHashPara.put("MjrEndHd",lArrRevExpLst.get(1));
					}
					else
						lHashPara.put("MjrStartHd",lArrRevExpLst.get(0));
				}
				else
				{
					glogger.info("MjrhdRange could not be found");
				}
				 lhshDeptCurrRevExpAmt= lObjRptQueryDAO.getExpForDept(lHashPara);

				 if(!lArrCapExpLst.isEmpty())
					{
						if(lArrCapExpLst.size()>1)
						{
							lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
							lHashPara.put("MjrEndHd",lArrCapExpLst.get(1));
						}
						else
							lHashPara.put("MjrStartHd",lArrCapExpLst.get(0));
					}
					else
					{
						glogger.info("MjrhdRange could not be found");
					}
				lhshDeptCurrCapExpAmt=lObjRptQueryDAO.getExpForDept(lHashPara);

				lHashPara.put("FDate",lHashPara.get("StartDate"));
				lHashPara.put("Todate", ToDate);
				lHashPara.put("FinYear", lFinYear);

				lArrReturn = getDataList(lArrDept, lhshEstimatedAmt, lhshGrantReleaseAmt,
							lhshDeptRevExpAmt, lhshDeptCapExpAmt,lhshDeptCurrRevExpAmt,lhshDeptCurrCapExpAmt, lHashPara, lObjReport.getReportCode());
				return lArrReturn;			
	}		
		public ArrayList<ArrayList> getDataList(ArrayList lArrDept,HashMap lhshEstimatedAmt, HashMap lhshGrantReleaseAmt,
				HashMap lhshDeptRevExpAmt, HashMap lhshDeptCapExpAmt,HashMap lhshDeptCurrRevExpAmt,HashMap lhshDeptCurrCapExpAmt, HashMap lHashPara, String lStrReportCode)
		{
			//System.out.println("-- In getDataList --");
			ArrayList<ArrayList> lArrReturn = new ArrayList<ArrayList>();
			
			double lDouCapTotal,lDouCurrCapTotal,lDouGrantAmt,lDouRevTotal,lDouRevCurrTotal,lDouBEAmt=0,lDouBEAmtDiff=0,lDouGrantAmtDiff=0,
				lDouREAmtDiff =0,lDouCurrREAmtDiff=0,lDouCEAmtDiff=0,lDouCurrCEAmtDiff=0,lDouTotalExp=0,lDouCurrTotalExp=0,ldbRndDiff=0,ldbCurrRndDiff=0,lDouProreta =0;
			long lLngRndGrantAmt,llngRevExp,llngCurrRevExp,llngCapExp,llngCurrCapExp,llngTotalExp=0,llngCurrTotalExp=0,lLngBETotal=0,lLngGrantTotal=0,lLngRETotal=0,
				lLngCETotal=0,lLngCurrCETotal=0, ldiffDays=0,lLngCurrRETotal=0,lLngCurrTotal=0,lLngCurTotal=0,lLngGrantTotalProreta=0;
			long lFinYear=Long.parseLong(lHashPara.get("FinYear").toString());
			//System.out.println("----FinYear is::-----"+lFinYear);
			
			int liRowCntr = 1;
			int lintPlanType = 0;
			int i;
			String lStrKey = "";
			String lStrKeyTemp = "";
			String lStrValue = "";
			String lStrPlanType = "";
			
			String Todate=lHashPara.get("Todate").toString();
			//System.out.println("--------ToDate---------"+Todate);
			
			
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			//System.out.println("Date diff in getDataList() -- "+ldiffDays);
			
			GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
			
			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);
	        
	        lintPlanType = Integer.parseInt(lHashPara.get("PlanType").toString());
			//System.out.println("Budget Type for getDeptRevCapPlanExp == "+lintPlanType);
				
			 if(lintPlanType!=Integer.parseInt(gObjRsrcBndle.getString("PlanBudgetType")) 
					  && lintPlanType!=Integer.parseInt(gObjRsrcBndle.getString("NonPlanBudgetType"))
					  && lintPlanType!=Integer.parseInt(gObjRsrcBndle.getString("CSSBudgetType")))
			 {
					 lStrPlanType="Total";
			 }
			 else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("PlanBudgetType")))
			 {
					 lStrPlanType="PL";
			 }
			 else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("NonPlanBudgetType")))
			 {
					 lStrPlanType="NP";
			 }
			 else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("CSSBudgetType")))
			 {
					 lStrPlanType="CSS";
			 }
			
			 for( i=0;i<lArrDept.size();i=i+2)
	        {
			  //System.out.println("Inside while in getDataList() ");
	          ArrayList<String> lArrInner = new ArrayList<String>();
	          HashMap gHashChartMap=new HashMap();
	          lDouCapTotal=0;
	          lDouCurrCapTotal=0;
	          lDouGrantAmt=0;
	          lDouRevTotal=0;
	          lDouRevCurrTotal=0;
	          lLngRndGrantAmt=0;
	          llngRevExp=0;
	          llngCurrRevExp=0;
	          llngCapExp=0;
	          llngCurrCapExp=0;
	          llngTotalExp=0;
	          llngCurrTotalExp=0;
	          lArrInner.add(""+liRowCntr++);
	          
	          lStrKey=lArrDept.get(i).toString()+"_"+lStrPlanType; 
	          //System.out.println("----------Key--------- " + lStrKey);
		      lStrKeyTemp=lArrDept.get(i).toString();
		      //System.out.println("----------KeyTemp--------- " + lStrKeyTemp);
		     if(lArrDept.get(i+1)!=null)   	
		     	  lStrValue=lArrDept.get(i+1).toString();
	         
	          //System.out.println("-----------------Value------------ " + lStrValue);
	          
	          gHashChartMap.put("DeptName", lStrValue);
	          //System.out.println("DeptNames in cahrt Are:"+gHashChartMap.get("DeptName"));
	          
	          if(lStrReportCode.equals(gObjRsrcBndle.getString("DeptWisePlanExpenditureReport")))
	        	  lArrInner.add(lStrValue);
	          else
	        	  lArrInner.add(lStrKeyTemp+" : "+lStrValue);	
	          //System.out.println("Key " + lStrKey + "\tValue " +lStrValue);
	    
	          lDouBEAmt = (lhshEstimatedAmt.get(lStrKey) != null) ? Double.parseDouble(lhshEstimatedAmt.get(lStrKey).toString()) : 0;          
	          lArrInner.add(Math.round(lDouBEAmt)!=0?""+Math.round(lDouBEAmt):"-");  // BE 
	          //System.out.println("Estimate Value is:"+Math.round(lDouBEAmt));
	          lDouBEAmtDiff +=(lDouBEAmt-Math.round(lDouBEAmt));
	          lLngBETotal+=Math.round(lDouBEAmt);
	          gHashChartMap.put("BudgetTotal", lLngBETotal);
	          //System.out.println("Budget Total in chart Are:"+gHashChartMap.get("BudgetTotal"));
	          
	          lDouGrantAmt = (lhshGrantReleaseAmt.get(lStrKey) != null) ? Double.parseDouble(lhshGrantReleaseAmt.get(lStrKey).toString())/10000000 : 0;
	          lLngRndGrantAmt=Math.round(lDouGrantAmt);// Grant Amount
	          lArrInner.add(lLngRndGrantAmt!=0?""+lLngRndGrantAmt:"-"); 
	          //System.out.println("Grant is -- "+lLngRndGrantAmt);
	          lDouGrantAmtDiff +=(lDouGrantAmt-lLngRndGrantAmt);
	          lLngGrantTotal+=lLngRndGrantAmt; 
	          gHashChartMap.put("GrantTotal", lLngRndGrantAmt);
	          //System.out.println("Grant Total in chart Are:"+gHashChartMap.get("GrantTotal"));

	          lDouRevTotal = (lhshDeptRevExpAmt.get(lStrKey) != null) ? Double.parseDouble(lhshDeptRevExpAmt.get(lStrKey).toString()) : 0;  
	    	  llngRevExp = Math.round(lDouRevTotal);// RevExp
	          lArrInner.add(llngRevExp!=0?""+llngRevExp:"-");  
	          //System.out.println("Revenue Exp is:"+ llngRevExp);
	          lDouREAmtDiff +=(lDouRevTotal-llngRevExp);
	          lLngRETotal+=llngRevExp;
	          gHashChartMap.put("RevenueExp", llngRevExp);
	          //System.out.println("Revenue Exp. in chart Are:"+gHashChartMap.get("RevenueExp"));
	         
	    	  lDouCapTotal=(lhshDeptCapExpAmt.get(lStrKey) != null) ? Double.parseDouble(lhshDeptCapExpAmt.get(lStrKey).toString()) : 0;
	    	  llngCapExp = Math.round(lDouCapTotal);                      //CapExp
	    	  lArrInner.add(llngCapExp!=0?""+llngCapExp:"-"); 
	    	  //System.out.println("Capital Exp is:"+ llngCapExp);
	    	  lDouCEAmtDiff +=(lDouCapTotal-llngCapExp);
	          lLngCETotal+=llngCapExp;
	          gHashChartMap.put("CapitalExp", llngCapExp);
	          //System.out.println("Capital Exp. in chart Are:"+gHashChartMap.get("CapitalExp"));
	          
	          lDouTotalExp = lDouRevTotal + lDouCapTotal;
	          llngTotalExp=  llngRevExp + llngCapExp;                       //Total(REV+CAP)
	          gHashChartMap.put("TotalExp", llngTotalExp);
	          //System.out.println("Total Exp is:"+ llngTotalExp);
	          ldbRndDiff += (lDouTotalExp-llngTotalExp);
	          lLngCurTotal+=llngTotalExp;
	          lArrInner.add(llngTotalExp!=0?""+llngTotalExp:"-");
	          
	          lDouRevCurrTotal = (lhshDeptCurrRevExpAmt.get(lStrKey) != null) ? Double.parseDouble(lhshDeptCurrRevExpAmt.get(lStrKey).toString()) : 0;  
	    	  llngCurrRevExp = Math.round(lDouRevCurrTotal);                  //CurrentRevExp
	          lArrInner.add(llngCurrRevExp!=0?""+llngCurrRevExp:"-");  
	          //System.out.println("Current Revenue Exp is:"+ llngCurrRevExp);
	          lDouCurrREAmtDiff +=(lDouRevCurrTotal-llngCurrRevExp);
	          lLngCurrRETotal+=llngCurrRevExp;
	          gHashChartMap.put("RevenueExp", llngCurrRevExp);
	          
	          lDouCurrCapTotal=(lhshDeptCurrCapExpAmt.get(lStrKey) != null) ? Double.parseDouble(lhshDeptCurrCapExpAmt.get(lStrKey).toString()) : 0;
	    	  llngCurrCapExp = Math.round(lDouCurrCapTotal);                 //CurrentCapExp
	    	  lArrInner.add(llngCurrCapExp!=0?""+llngCurrCapExp:"-"); 
	    	  //System.out.println("Current Capital Exp is:"+ llngCurrCapExp);
	    	  lDouCurrCEAmtDiff +=(lDouCurrCapTotal-llngCurrCapExp);
	          lLngCurrCETotal+=llngCurrCapExp;
	          gHashChartMap.put("CapitalExp", llngCurrCapExp);
	          
	          lDouCurrTotalExp = lDouRevCurrTotal + lDouCurrCapTotal;
	          llngCurrTotalExp=  llngCurrRevExp + llngCurrCapExp;			//Total(PRGREV+PRGCURR)
	          gHashChartMap.put("TotalExp", llngCurrTotalExp);
	          //System.out.println("Current Total Exp is:"+ llngCurrTotalExp);
	          ldbCurrRndDiff += (lDouCurrTotalExp-llngCurrTotalExp);
	          lLngCurrTotal+=llngCurrTotalExp;
	          lArrInner.add(llngCurrTotalExp!=0?""+llngCurrTotalExp:"-");
	          
		        if( lLngRndGrantAmt==0)										//Proreta
		        {
		        	 lDouProreta=0;
		        }
		        else
		        {
		        	lDouProreta=Math.round((llngTotalExp * 100 * lObjGrantDtlDAOImpl.getGrantPeriod(Todate, lFinYear))/(Math.round(lLngRndGrantAmt)*ldiffDays));//proreta
		        } 
				 lArrInner.add(lDouProreta!=0?""+lDouProreta:"-");
				 
				 lArrChart.add(gHashChartMap);
				 if(lStrReportCode.equals(gObjRsrcBndle.getString("DeptWisePlanExpenditureReport")))
				 {
					 lArrInner.add("View");
					 lArrInner.add("View");
					 lArrInner.add(lHashPara.get("FinYrId").toString());
					 lArrInner.add(lArrDept.get(i).toString());
					 lArrInner.add(lHashPara.get("PlanType").toString());
					 lArrInner.add(lHashPara.get("StartDate").toString());
					 lArrInner.add(lHashPara.get("EndDate").toString());
					 lArrInner.add(lHashPara.get("diffdays").toString());
				 }		 
				 lArrReturn.add(lArrInner);
		     }
			
			 ArrayList<Object> lArrInner = new ArrayList<Object>();
			 lArrInner.add(" ");
			 StyledData dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("Total");
			 lArrInner.add(dataStyle);
				
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngBETotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngGrantTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngRETotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCETotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurrRETotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurrCETotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurrTotal);
			 lArrInner.add(dataStyle);
			// lArrInner.add("-");
			 if(lLngGrantTotal==0||ldiffDays==0)
			 {
				 lLngGrantTotalProreta=0;
			 }
			 else
			 {
				 lLngGrantTotalProreta=Math.round(lLngCurTotal*100*lObjGrantDtlDAOImpl.getGrantPeriod(Todate, lFinYear))/(Math.round(lLngGrantTotal*ldiffDays));
				 //System.out.println("-----------Grant Protreta is---------"+lLngGrantTotalProreta);
				 dataStyle = new StyledData();
			     dataStyle.setStyles(reportStyleVO);
			     dataStyle.setData(lLngGrantTotalProreta);
				 lArrInner.add(dataStyle);
			 }
			
			 if(lStrReportCode.equals(gObjRsrcBndle.getString("DeptWisePlanExpenditureReport")))
			 {
				 lArrInner.add("-");
				 lArrInner.add("-");
				 lArrInner.add("");
				 lArrInner.add("");
				 lArrInner.add("");
				 lArrInner.add("");
				 lArrInner.add("");
				 lArrInner.add("");
			 }
			 lArrReturn.add(lArrInner);
			 
			 lArrInner=new ArrayList<Object>();
			 lArrInner.add("");
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("Rounded Difference");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouBEAmtDiff));
			 lArrInner.add(dataStyle);

			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouGrantAmtDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouREAmtDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouCEAmtDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(ldbRndDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouCurrREAmtDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouCurrCEAmtDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(ldbCurrRndDiff));
			 lArrInner.add(dataStyle);
			 lArrInner.add("-");
			 if(lStrReportCode.equals(gObjRsrcBndle.getString("DeptWisePlanExpenditureReport")))
			 {
				 lArrInner.add("-");
				 lArrInner.add("-");
				 lArrInner.add("");
				 lArrInner.add("");
				 lArrInner.add("");
				 lArrInner.add("");
				 lArrInner.add("");
				 lArrInner.add("");
			 }
			
			 lArrReturn.add(lArrInner);
			 return lArrReturn;			
		}
		

		
		public HashMap getDates(String lStrFrmDate,String lStrToDate)
		{
			glogger.info("----------Inside getDates() method---------");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			HashMap hmp=new HashMap();
			Date lFromDate;
			Date lToDate;
			Date lPrgFromDt = null;
			long lLongDifference = 0;
			long lLngPrgDifference = 0;
			final FastDateFormat dateFormat = FastDateFormat.getInstance("dd/MM/yyyy");
			
			try
			{
				if(!lStrFrmDate.equals("") && !lStrToDate.equals(""))
				{
					glogger.info("In Both Not Null");
					
					GregorianCalendar lGCPrgfrmDate= new GregorianCalendar();
					Date lDatePrgDate = new Date();
					lGCPrgfrmDate.setTime(lDatePrgDate);
					int lIyrId=lGCPrgfrmDate.YEAR;
					int y=lGCPrgfrmDate.get(lIyrId);
					
					int liPrgMnthIdx=lGCPrgfrmDate.MONTH;
					int liPrgMnth = lGCPrgfrmDate.get(liPrgMnthIdx);
					liPrgMnth++;
					if(liPrgMnth>=1 && liPrgMnth<5)
					{
						y--;
					}
					String lStrPrgFrmDateTmp = "01/04/"+y;
					lPrgFromDt= sdf.parse(lStrPrgFrmDateTmp);
					
					lFromDate = sdf.parse(lStrFrmDate);
					lToDate = sdf.parse(lStrToDate);
					
					glogger.info("FromDate -- "+lFromDate);
					glogger.info("ToDate -- "+lToDate);
					
					lLongDifference = (lToDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
					lLngPrgDifference = (lToDate.getTime() - lPrgFromDt.getTime()) / (1000*60*60*24);
					hmp.put("fromdate",lStrFrmDate);
					glogger.info("FromDate:"+hmp.get("fromdate"));
					hmp.put("todate",lStrToDate);
					hmp.put("difference",lLongDifference);
					hmp.put("prgDate",dateFormat.format(lPrgFromDt));
					hmp.put("prgDifference",lLngPrgDifference);

				}
				else if(!lStrFrmDate.equals("") && lStrToDate.equals(""))
				{
					glogger.info("In FromDate Not Null - ToDate NULL ");
					Date lDateCurrDate = new Date();
					
					GregorianCalendar lGCPrgfrmDate= new GregorianCalendar();
					Date lDatePrgDate = new Date();
					lGCPrgfrmDate.setTime(lDatePrgDate);
					int lIyrId=lGCPrgfrmDate.YEAR;
					int y=lGCPrgfrmDate.get(lIyrId);
					
					int liPrgMnthIdx=lGCPrgfrmDate.MONTH;
					int liPrgMnth = lGCPrgfrmDate.get(liPrgMnthIdx);
					liPrgMnth++;
					if(liPrgMnth>=1 && liPrgMnth<5)
					{
						y--;
					}
					String lStrPrgFrmDateTmp = "01/04/"+y;
					lPrgFromDt= sdf.parse(lStrPrgFrmDateTmp);				
		
					lFromDate = sdf.parse(lStrFrmDate);
					glogger.info("FromDate -- "+lStrFrmDate);
					glogger.info("ToDate -- "+dateFormat.format(lDateCurrDate));
					
					lLongDifference = (lDateCurrDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
					lLngPrgDifference = (lDateCurrDate.getTime() - lPrgFromDt.getTime()) / (1000*60*60*24);
					hmp.put("fromdate",lStrFrmDate);
					hmp.put("todate",dateFormat.format(lDateCurrDate));
					hmp.put("difference",lLongDifference);
					hmp.put("prgDate",dateFormat.format(lPrgFromDt));
					hmp.put("prgDifference",lLngPrgDifference);
					
				}
				else if(lStrFrmDate.equals("") && !lStrToDate.equals(""))
				{
					glogger.info("In FromDate Null - ToDate NOT NULL ");
					Date lDateCurrDate = new Date();
					GregorianCalendar lGCcurrDate= new GregorianCalendar();
					
					GregorianCalendar lGCPrgfrmDate= new GregorianCalendar();
					Date lDatePrgDate = new Date();
					lGCPrgfrmDate.setTime(lDatePrgDate);
					int lIyrId=lGCPrgfrmDate.YEAR;
					int y=lGCPrgfrmDate.get(lIyrId);
					
					int liPrgMnthIdx=lGCPrgfrmDate.MONTH;
					int liPrgMnth = lGCPrgfrmDate.get(liPrgMnthIdx);
					liPrgMnth++;
					if(liPrgMnth>=1 && liPrgMnth<5)
					{
						y--;
					}
					String lStrPrgFrmDateTmp = "01/04/"+y;
					lPrgFromDt= sdf.parse(lStrPrgFrmDateTmp);	
					
					lGCcurrDate.setTime(lDateCurrDate);
					lIyrId=lGCcurrDate.YEAR;
					y=lGCcurrDate.get(lIyrId);
					String lStrFrmDateTmp = "01/04/"+y;
					lFromDate= sdf.parse(lStrFrmDateTmp);
					
					lToDate = sdf.parse(lStrToDate);
					glogger.info("FromDate -- "+dateFormat.format(lFromDate));
					glogger.info("ToDate -- "+lStrToDate);
					
					lLongDifference = (lToDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
					lLngPrgDifference = (lToDate.getTime() - lPrgFromDt.getTime()) / (1000*60*60*24);
					hmp.put("fromdate",dateFormat.format(lFromDate));
					hmp.put("todate",lStrToDate);
					hmp.put("difference",lLongDifference);
					hmp.put("prgDate",dateFormat.format(lPrgFromDt));
					hmp.put("prgDifference",lLngPrgDifference);
				}
				else if(lStrFrmDate.equals("") && lStrToDate.equals(""))
				{	
					glogger.info("In both null");
					Date lDateCurrDate = new Date();
					GregorianCalendar lGCcurrDate= new GregorianCalendar();
					GregorianCalendar lGCfrmDate= new GregorianCalendar();
					GregorianCalendar lGCtoDate= new GregorianCalendar();
					
					GregorianCalendar lGCPrgfrmDate= new GregorianCalendar();
					Date lDatePrgDate = new Date();
					lGCPrgfrmDate.setTime(lDatePrgDate);
					int lIyrId=lGCPrgfrmDate.YEAR;
					int y=lGCPrgfrmDate.get(lIyrId);
					
					int liPrgMnthIdx=lGCPrgfrmDate.MONTH;
					int liPrgMnth = lGCPrgfrmDate.get(liPrgMnthIdx);
					liPrgMnth++;
					if(liPrgMnth>=1 && liPrgMnth<5)
					{
						y--;
					}
					String lStrPrgFrmDateTmp = "01/04/"+y;
					lPrgFromDt= sdf.parse(lStrPrgFrmDateTmp);	
					
					String lStrFrmDateTmp = "01/04/"+y;
					lFromDate= sdf.parse(lStrFrmDateTmp);
					
					glogger.info("FromDate -- "+lStrFrmDateTmp);
					glogger.info("ToDate -- "+dateFormat.format(lDateCurrDate));
					
					lLongDifference = (lDateCurrDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
					lLngPrgDifference = (lDateCurrDate.getTime() - lPrgFromDt.getTime()) / (1000*60*60*24);
					hmp.put("fromdate",lStrFrmDateTmp);
					hmp.put("todate",dateFormat.format(lDateCurrDate));
					hmp.put("difference",lLongDifference);
					hmp.put("prgDate",dateFormat.format(lPrgFromDt));
					hmp.put("prgDifference",lLngPrgDifference);
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return hmp;
		}
	
		private List getDeptPlanNonPlanCSSRpt(ReportVO lObjReport, Object criteria)
		{
			
			glogger.info("------Inside getDeptPlanNonPlanCSSRpt-------");
			List<ArrayList> lArrReturn = new ArrayList<ArrayList>();
			//HashMap lHshMapDept=null;
			ArrayList lArrDept=null;
			int lintLangId=0;
		    long ldiffDays=0;
		    long lFinYear=0;
		   
	          		  	    
	        HashMap<String,Object> lHashPara=getParameters(lObjReport, criteria);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			String ToDate=lObjReport.getParameterValue("Dateto").toString();
			lFinYear=Long.parseLong(lObjReport.getParameterValue("finYear").toString());
			try
			{
				lintLangId=Integer.parseInt(lHashPara.get("LangId").toString());
				
				glogger.info("------------->Lang Id"+lintLangId);
				glogger.info("--------------> after Lang ID");
				
				lArrDept = new ArrayList();
				glogger.info("------------------> after ArrayList");
				lArrDept =  lObjRptQueryDAO.getAllDept(lintLangId); 
			}
			catch(Exception e)
			{
				glogger.error("Error in execution of getAllDept and Error is : " + e,e);
				e.printStackTrace();
			}
			
			
			HashMap lhshEstimatedAmt=lObjRptQueryDAO.getBudEstForDept(lHashPara);
			HashMap lhshGrantReleaseAmt=lObjRptQueryDAO.getDeptGrantAmt(lHashPara);
			HashMap lhshDeptCurrExpAmt= lObjRptQueryDAO.getExpForDept(lHashPara);
			
			lHashPara.put("FDate",lHashPara.get("PrgDate"));
			HashMap lhshDeptPrgExpAmt= lObjRptQueryDAO.getExpForDept(lHashPara);
			lHashPara.put("FDate",lHashPara.get("StartDate"));
			lHashPara.put("Todate", ToDate);
			lHashPara.put("FinYear", lFinYear);
			if(lObjReport.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSTotalReport")))
			{
				lArrReturn=getPlNpCssTotalList(lArrDept,lhshEstimatedAmt,lhshGrantReleaseAmt,lhshDeptCurrExpAmt,
						lhshDeptPrgExpAmt,lHashPara,lObjReport.getReportCode());
			}
			else
			{
				lArrReturn = getPlNpCssList(lArrDept, lhshEstimatedAmt, lhshGrantReleaseAmt,
						lhshDeptCurrExpAmt, lhshDeptPrgExpAmt, lHashPara, lObjReport.getReportCode());
			}	
		return lArrReturn;			
		}
		
		private HashMap<String,Object> getParameters(ReportVO lObjReport,Object criteria )
		{
			//System.out.println("--------Inside getParameters---------");
			HashMap <String,Object>lHshPara=new HashMap<String,Object>() ;
			int lintFinYear = 0,lintLangId=0,lintPlanType=0;
			String lStrFromDate = "";
			String lStrToDate = "";
			long ldiffDays=0;
			
			lintLangId=Integer.parseInt(gObjRsrcBndle.getString(lObjReport.getLangId()));
			
			if(lObjReport.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanExpenditureReport"))||
				lObjReport.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSReport"))||
				lObjReport.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSTotalReport")))
			{
				//System.out.println("---------Inside if of getParameters--------");

				//System.out.println("Lang Id  --> "+gObjRsrcBndle.getString(lObjReport.getLangId()));
				//System.out.println("Plan Type --> "+lObjReport.getParameterValue("plantype").toString());
				//System.out.println("FinYrId --> "+lObjReport.getParameterValue("finYear").toString());
				//System.out.println("FromDate --> "+lObjReport.getParameterValue("Datefrom").toString());
				//System.out.println("ToDate --> "+lObjReport.getParameterValue("Dateto").toString());
				
				lintFinYear = Integer.parseInt(lObjReport.getParameterValue("finYear").toString());
				lHshPara.put("FinYrId", new Integer(lintFinYear));	
				lHshPara.put("LangId",new Integer(gObjRsrcBndle.getString(lObjReport.getLangId())));
				
				lintPlanType = Integer.parseInt(lObjReport.getParameterValue("plantype").toString());
				
				if(lObjReport.getReportCode().equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSTotalReport")))
				{
					lintPlanType = 0;
				}
				lHshPara.put("PlanType", lintPlanType);
				
				if(lObjReport.getParameterValue("Datefrom")!=null && !lObjReport.getParameterValue("Datefrom").equals(""))
				{
					lStrFromDate = lObjReport.getParameterValue("Datefrom").toString();
					//System.out.println("-----From Date in Main Report----"+lStrFromDate);
					
				}
				if(lObjReport.getParameterValue("Dateto")!=null && !lObjReport.getParameterValue("Dateto").equals(""))
				{
					lStrToDate = lObjReport.getParameterValue("Dateto").toString();
					//System.out.println("To date in Main Report----- "+lStrToDate);
				}	
				HashMap lhshDateMap = getDates(lStrFromDate,lStrToDate);
				String lStrFromDate1 = lhshDateMap.get("fromdate").toString(); 
				String lStrFromDate2=lhshDateMap.get("fromdate").toString(); 
				lStrToDate = lhshDateMap.get("todate").toString();	
				
				lHshPara.put("FDate",lStrFromDate1);
				lHshPara.put("StartDate",lStrFromDate2);
				lHshPara.put("EndDate",lStrToDate);
				lHshPara.put("PrgDate",lhshDateMap.get("prgDate").toString());
				ldiffDays = Long.parseLong(lhshDateMap.get("difference").toString());
				lHshPara.put("diffdays", ldiffDays);
				//System.out.println("Difference of days:"+ldiffDays);
				//System.out.println("PlanType == "+lObjReport.getParameterValue("plantype"));	
			}

			else 
			{
				//System.out.println("------Inside Else of GetParameters------");
				
				Hashtable requestParam = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.REQUEST_KEYS );
				lintFinYear = Integer.parseInt(requestParam.get("finYear").toString());
				//System.out.println("Financial Year in Majorhead::"+lintFinYear);
				lObjReport.getParameterValue("fromDate");
				//System.out.println("-----------From Date--555-------"+lObjReport.getParameterValue("fromDate"));
				lStrFromDate = requestParam.get("fromDate").toString();
				//System.out.println("From Date in MajorHead::"+lStrFromDate);
				lStrToDate = requestParam.get("toDate").toString();
				
				//System.out.println("ToDate in MajorHead::"+lStrToDate);
				ldiffDays = Long.parseLong(requestParam.get("diffdays").toString());
				//System.out.println("Difference of Days::"+ldiffDays);
				
				HashMap lhshDateMap = getDates(lStrFromDate,lStrToDate);
				lStrFromDate = lhshDateMap.get("fromdate").toString(); 
				//System.out.println("From Date Afterfetching from getDAtes------"+lStrFromDate);
				lStrToDate = lhshDateMap.get("todate").toString();	
				//System.out.println("To Date After fetching from getDates------"+lStrToDate);
				
				lHshPara.put("FDate",lStrFromDate);
				lHshPara.put("EndDate",lStrToDate);
				lHshPara.put("FinYrId", new Integer(lintFinYear));	
				lHshPara.put("PlanType",requestParam.get("planType"));
				lHshPara.put("LangId",new Integer(lintLangId));
				lHshPara.put("DeptId",new Integer(requestParam.get("DeptId").toString()));
				lHshPara.put("PrgDate",lhshDateMap.get("prgDate").toString());
				lHshPara.put("diffdays", ldiffDays);
				
				//System.out.println(lHshPara.get("FinYrId")+"--"+lHshPara.get("PlanType")+"--"+						lHshPara.get("LangId")+"--"+lHshPara.get("DeptId"));
				//System.out.println("FromDate == "+lStrFromDate);
				//System.out.println("ToDate == "+lStrToDate);
				//System.out.println("Difference of days:"+ldiffDays);
			}	
			return lHshPara;
		}


		public ArrayList getPlNpCssList(ArrayList lArrDept,HashMap lhshEstimatedAmt,HashMap lhshGrantReleaseAmt,HashMap lhshDeptCurrExpAmt,HashMap lhshDeptPrgExpAmt,HashMap lHashPara,String lStrReportCode )
		{
			//System.out.println("Inside getPlNpCssList");
			 
			ArrayList<ArrayList>lArrReturn=new ArrayList();
			String lStrKey = "";
			String lStrKeyTemp="";
			String lStrPlanType="";
			String lStrValue="";
			//System.out.println("--------ToDate---------"+lHashPara.get("Todate").toString());
			String Todate=lHashPara.get("Todate").toString();
			//System.out.println("--------ToDate1---------"+Todate);
			
			double lDouPrgTotal,lDouGrantAmt,lDouCurrTotal,lDouBEAmt=0,lDouBEAmtDiff=0,lDouGrantAmtDiff=0,
			lDouCurrAmtDiff =0,lDouPrgAmtDiff=0,lDouTotalExp=0,ldbRndDiff=0,lDouGrantProreta =0,lDouBudProreta=0,
			lDouGrantProretaDiff=0,lDouBudProretaDiff=0;
			long lLngRndGrantAmt,llngCurrExp,llngPrgExp,llngTotalExp,lLngBETotal=0,lLngGrantTotal=0,lLngCurrTotal=0,
			lLngPrgTotal=0, ldiffDays=0,lLngRndBudAmt,lLngTotalGrantProreta=0,lLngTotalBudgetProreta=0,lLngMjrBudProreta=0,
			lLngMjrGrantProreta=0,lLngGrantProretaDiff=0,lLngBudProretaDiff=0;
			
			//System.out.println("---------FinYear--------"+Long.parseLong(lHashPara.get("FinYear").toString()));
			long lFinYear=Long.parseLong(lHashPara.get("FinYear").toString());
			//System.out.println("---------FinYear1--------"+lFinYear);
			int liRowCntr = 1;
			int lintPlanType = 0;
			int i;
			
			
		
			GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
			
			
			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);
			
	        lintPlanType = Integer.parseInt(lHashPara.get("PlanType").toString());
			//System.out.println("Budget Type for getDeptRevCapPlanExp == "+lintPlanType);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			//System.out.println("DiffDays is"+ldiffDays);
			
			if(lintPlanType==-1)
			{
				lStrPlanType="Total";
			}
			else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("PlanBudgetType")))
			{
				lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				lStrPlanType="NP";
			}
			else if(lintPlanType==Integer.parseInt(gObjRsrcBndle.getString("CSSBudgetType")))
			{
				lStrPlanType="CSS";
			}
		
			for( i=0;i<lArrDept.size();i=i+2)
			{
				//System.out.println("Inside while in getPlNpCssList() ");
		          ArrayList<String> lArrInner = new ArrayList<String>();
		          HashMap gHashChartMap = new HashMap();
		          lDouCurrTotal=0;
		          lDouGrantAmt=0;
		          lDouPrgTotal=0;
		          lLngRndGrantAmt=0;
		          llngPrgExp=0;
		          llngCurrExp=0;
		          llngTotalExp=0;
		          lLngRndBudAmt=0;
		          lArrInner.add(""+liRowCntr++);
		          
		          lStrKey=lArrDept.get(i).toString()+"_"+lStrPlanType;
		   
		          lStrKeyTemp=lArrDept.get(i).toString();
		          //System.out.println("----------Key--------- " + lStrKey);
		          if(lArrDept.get(i+1)!=null)
		          {
		        	  lStrValue=lArrDept.get(i+1).toString(); 
		          }
		          //System.out.println("-----------------Value------------ " + lStrValue);
		          gHashChartMap.put("DeptName", lStrValue);
		          //System.out.println("Deptnames in Chartmap is:"+ gHashChartMap.get("DeptName"));
		          
		          if(lStrReportCode.equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSReport")))
		          {
		        	  lArrInner.add(lStrValue);
		          }
		          else
		          lArrInner.add(lStrKeyTemp+" : "+lStrValue);
		      
		          
		          lDouBEAmt = (lhshEstimatedAmt.get(lStrKey)!= null) ? Double.parseDouble(lhshEstimatedAmt.get(lStrKey).toString()) : 0;
		          lLngRndBudAmt=Math.round(lDouBEAmt);
		          lArrInner.add(lLngRndBudAmt!=0?""+lLngRndBudAmt:"-");  // BE 
		          //System.out.println("Estimate Value="+lLngRndBudAmt);
		          lDouBEAmtDiff +=(lDouBEAmt- lLngRndBudAmt);
		          lLngBETotal+=Math.round(lDouBEAmt);
		          gHashChartMap.put("BudgetTotal",lLngRndBudAmt);
		          //System.out.println("BudgetTotal in Chatrmap is:"+gHashChartMap.get("BudgetTotal"));
		          
		           
		          lDouGrantAmt = (lhshGrantReleaseAmt.get(lStrKey) != null) ? Double.parseDouble(lhshGrantReleaseAmt.get(lStrKey).toString())/10000000 : 0;
		          //System.out.println("Double Grant is"+lDouGrantAmt);
		          lLngRndGrantAmt=Math.round(lDouGrantAmt);// Grant Amount
		          lArrInner.add(lLngRndGrantAmt!=0?""+lLngRndGrantAmt:"-"); 
		          //System.out.println("Grant is ="+lLngRndGrantAmt);
		          lDouGrantAmtDiff +=(lDouGrantAmt-lLngRndGrantAmt);
		          lLngGrantTotal+=lLngRndGrantAmt; 
		          gHashChartMap.put("GrantTotal",lLngRndGrantAmt);
		          //System.out.println("Granttotal in ChartMap is:"+gHashChartMap.get("GrantTotal"));
		          
		          lDouCurrTotal = (lhshDeptCurrExpAmt.get(lStrKey) != null) ? Double.parseDouble(lhshDeptCurrExpAmt.get(lStrKey).toString()) : 0;  
		    	  llngCurrExp = Math.round(lDouCurrTotal);// RevExp
		          lArrInner.add(llngCurrExp!=0?""+llngCurrExp:"-");  
		          //System.out.println("Current Exp is:"+ llngCurrExp);
		          lDouCurrAmtDiff +=(lDouCurrTotal-llngCurrExp);
		          lLngCurrTotal+=llngCurrExp;
		          gHashChartMap.put("CurrentExp",llngCurrExp);
		          //System.out.println("Current Exp. in ChatrMap is:"+gHashChartMap.get("CurrentExp"));
		          
		    	  lDouPrgTotal=(lhshDeptPrgExpAmt.get(lStrKey) != null) ? Double.parseDouble(lhshDeptPrgExpAmt.get(lStrKey).toString()) : 0;
		    	  llngPrgExp = Math.round(lDouPrgTotal);//CapExp
		    	  lArrInner.add(llngPrgExp!=0?""+llngPrgExp:"-"); 
		    	  //System.out.println("Progressive Exp is:"+ llngPrgExp);
		    	  lDouPrgAmtDiff +=(lDouPrgTotal-llngPrgExp);
		          lLngPrgTotal+=llngPrgExp;
		         
		          
		          
		          if(lLngRndGrantAmt==0 || ldiffDays==0)
		          {
		        	  lDouGrantProreta=0;
		          }
		          else
		          {
		        	  lDouGrantProreta=(llngCurrExp*100*lObjGrantDtlDAOImpl.getGrantPeriod(Todate, lFinYear))/(Math.round(lLngRndGrantAmt*ldiffDays));
		        	  //System.out.println("---------Progressive Grant Data is::------------"+lDouGrantProreta);
		        	  //lDouGrantProretaDiff+=((lDouCurrTotal*100)/(lDouGrantAmt*ldiffDays))-lDouGrantProreta;
		        	 
		          }
		          
		          lArrInner.add(lDouGrantProreta!=0?""+lDouGrantProreta:"-");
		          
		          if(lLngRndBudAmt==0 || ldiffDays==0)
		          {
		        	  lDouBudProreta=0;
		          }
		          else
		          {
		        	 try
		        	 {
		        	  lDouBudProreta=(llngPrgExp*100)/(Math.round(lLngRndBudAmt));  
		        	 // lDouBudProretaDiff+=((lDouPrgTotal*100)/(lDouBEAmt*ldiffDays))-lDouBudProreta;
		        	  
		        	  
		          }catch(ArithmeticException e)
		          {
		        	  	e.printStackTrace();
		          }
		          }
		          //System.out.println("Budget Proreta is:"+lDouBudProreta);
		          lArrInner.add(lDouBudProreta!=0?""+lDouBudProreta:"-");
		          lArrInner.add("-");
		          lArrChart.add(gHashChartMap);
		         
		         if (lStrReportCode.equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSReport")))
		         {
			         lArrInner.add("View");
					 lArrInner.add("View");
					 lArrInner.add(lHashPara.get("FinYrId").toString());
					 lArrInner.add(lArrDept.get(i).toString());
					 lArrInner.add(lHashPara.get("PlanType").toString());
					 lArrInner.add(lHashPara.get("FDate").toString());
					 lArrInner.add(lHashPara.get("EndDate").toString());
					 lArrInner.add(lHashPara.get("diffdays").toString());
		         }	  
		          lArrReturn.add(lArrInner);
			}
			
			 ArrayList<Object> lArrInner = new ArrayList<Object>();
			 lArrInner.add(" ");
			 StyledData dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("Total");
			 lArrInner.add(dataStyle);
				
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngBETotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngGrantTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurrTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngPrgTotal);
			 lArrInner.add(dataStyle);
			
			 
			 if(lLngGrantTotal==0 || ldiffDays==0)
	          {
				 lLngTotalGrantProreta=0;
				 dataStyle = new StyledData();
			     dataStyle.setStyles(reportStyleVO);
			     dataStyle.setData(lLngTotalGrantProreta);
				 lArrInner.add(dataStyle);
	          }
			 else
			 {
				 try
				 {
					 lLngTotalGrantProreta=Math.round(lLngCurrTotal*100*lObjGrantDtlDAOImpl.getGrantPeriod(Todate, lFinYear))/(Math.round(lLngGrantTotal*ldiffDays));
					 dataStyle = new StyledData();
				     dataStyle.setStyles(reportStyleVO);
				     dataStyle.setData(lLngTotalGrantProreta);
					 lArrInner.add(dataStyle);
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			 }
			 if(lLngBETotal==0)
			 {
				 lLngTotalBudgetProreta=0; 
				 dataStyle = new StyledData();
			     dataStyle.setStyles(reportStyleVO);
			     dataStyle.setData(lLngTotalBudgetProreta);
				 lArrInner.add(dataStyle);
			 }
			 else
			 {
				 try
				 {
					 lLngTotalBudgetProreta=Math.round(lLngPrgTotal*100)/(Math.round(lLngBETotal));
					 dataStyle = new StyledData();
				     dataStyle.setStyles(reportStyleVO);
				     dataStyle.setData(lLngTotalBudgetProreta);
					 lArrInner.add(dataStyle);
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
				
			 }
		
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 lArrReturn.add(lArrInner);
			 
			 lArrInner=new ArrayList();
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("Rounded Difference");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouBEAmtDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouGrantAmtDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouCurrAmtDiff));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDouPrgAmtDiff));
			 lArrInner.add(dataStyle);
			
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
		 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 lArrReturn.add(lArrInner);
			return lArrReturn;

			
		}
		
// Generate Report method for-- Report Code--160003,160047,160049------------------------------------------
		
		
		public ArrayList getPlNpCssTotalList(ArrayList lArrDept,HashMap lhshEstimatedAmt,HashMap lhshGrantReleaseAmt,HashMap lhshDeptCurrExpAmt,HashMap lhshDeptPrgExpAmt,HashMap lHashPara,String lStrReportCode )
		{
			//System.out.println("---------inside getPlNpCssTotalList---------");
			ArrayList<ArrayList>lArrReturn=new ArrayList();
			String lStrKey = "";
			String lStrPlanType="";
			String lStrValue="";
			//System.out.println("--------ToDate---------"+lHashPara.get("Todate").toString());
			String Todate=lHashPara.get("Todate").toString();
			
			double lDouPrgTotal,lDouGrantAmt,lDouCurrTotal,lDouBEAmt=0,lDouBEAmtDiff=0,lDblTotalDiffPLGrntAmt=0,
			lDouCurrAmtDiff =0,lDouPrgAmtDiff=0,lDouTotalExp=0,lDouGrantProreta =0,lDouBudProreta=0,
			lDblNPBudAmt=0,lDblTotalDiffPLBudAmt=0,lDblTotalDiffNPBudAmt=0,lDblTotalDiffCSSBudAmt=0,lDblCSSBudAmt=0
			,lDblNPGrntAmt=0,lDblCSSGrntAmt=0,lDblTotalDiffNPGrntAmt=0,lDblTotalDiffCSSGrntAmt=0,lDblPLCurrExpAmt=0,
			lDblNPCurrExpAmt=0,lDblCSSCurrExpAmt=0,lDblPLProgExpAmt=0,lDblNPProgExpAmt=0,lDblCSSProgExpAmt=0,lDblTotalDiffPLCurrAmt=0
			,lDblTotalDiffNPCurrAmt=0,lDblTotalDiffCSSCurrAmt=0,lDblTotalDiffPLPrgCurrAmt=0,lDblTotalDiffNPPrgCurrAmt=0
			,lDblTotalDiffCSSPrgCurrAmt=0,lDblBudTotalAmt=0,lDblBudTotalDiffAmt=0,lDblGrantTotalAmt=0,lDblGrantTotalDiffAmt=0,
			lDblCurrTotalAmt=0,lDblCurrTotalDiffAmt=0,lDblProgTotalAmt=0,lDblProgTotalDiffAmt=0,lDouGrantProretaDiff=0,lDouBudProretaDiff=0;
			
			long lLngRndGrantAmt,llngCurrExp,llngPrgExp,llngTotalExp=0,lLngBETotal=0,lLngGrantTotal,lLngCurrTotal=0,
			lLngPrgTotal=0, ldiffDays=0,lLngRndBudAmt=0,lLngTotalPLBudAmt,lLngTotalNPBudAmt,lLngTotalCSSBudAmt,
			lLngTotalNPGrntAmt,lLngTotalCSSGrntAmt,lLngTotalPLExpAmt,lLngTotalNPExpAmt,lLngTotalCSSExpAmt,
			lLngTotalPLPrgExpAmt,lLngTotalNPPrgExpAmt,lLngTotalCSSPrgExpAmt,lLngPLTotal=0,lLngNPTotal=0,lLngCSSTotal=0,
			lLngGrantPLAmt=0,lLngGrantNPAmt=0,lLngGrantCSSAmt=0,lLngCurrPLAmt=0,lLngCurrNPAmt=0,lLngCurrCSSAmt=0,lLngPrgPLAmt=0,
			lLngPrgNPAmt=0,lLngPrgCSSAmt=0,lLngBudTotalAmt,lLngBudTotal=0,lLngGrantTotalAmt,lLngGrantAmtTotal=0,
			lLngCurrTotalAmt,lLngCurrAmtTotal=0,lLngProgTotalAmt,lLngProgAmtTotal=0,lLngTotalBudgetProreta=0,lLngTotalGrantProreta=0,
			lLngMjrGrantProreta=0,lLngMjrBudProreta=0,lLngGrantProretaDiff=0,lLngBudProretaDiff=0;
			
			//System.out.println("------------FinYear--------------"+Long.parseLong(lHashPara.get("FinYear").toString()));
			long lFinYear=Long.parseLong(lHashPara.get("FinYear").toString());
			int liRowCntr = 1;
			int lintPlanType = 0;
			int i;
			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(2);
	        lnfLong.setMinimumFractionDigits(2);
			
			GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
			
	        lintPlanType = Integer.parseInt(lHashPara.get("PlanType").toString());
			//System.out.println("Budget Type for getDeptRevCapPlanExp == "+lintPlanType);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			//System.out.println("Difference of days is:"+ldiffDays);
			//System.out.println("Size of ArrayList is:"+lArrDept.size());
			
			
			for( i=0;i<lArrDept.size();i=i+2)
			{
				  //System.out.println("Inside for in getPlNpCssTotalList() ");
				  HashMap gHashChartMap = new HashMap();
		          ArrayList<String> lArrInner = new ArrayList<String>();
		          
		          
		          lDouCurrTotal=0;
		          lDouGrantAmt=0;
		          lLngTotalPLBudAmt=0;
		          lLngTotalNPBudAmt=0;
		          lLngTotalCSSBudAmt=0;
		          lLngBudTotalAmt=0;
		          lLngTotalNPGrntAmt=0;
		          lLngTotalCSSGrntAmt=0;
		          lLngGrantTotalAmt=0;
		          lLngTotalPLExpAmt=0;
		          lLngTotalCSSExpAmt=0;
		          lLngTotalNPExpAmt=0;
		          lLngCurrTotalAmt=0;
		          lLngTotalPLPrgExpAmt=0;
		          lLngTotalNPPrgExpAmt=0;
		          lLngTotalCSSPrgExpAmt=0;
		          lLngProgTotalAmt=0;
		          lLngGrantTotal=0;
		          lArrInner.add(""+liRowCntr++);
		          
		        
		          lStrKey=lArrDept.get(i).toString();
		         
		          //System.out.println("----------Key--------- " + lStrKey);
		        
		          if(lArrDept.get(i+1)!=null)
			        	 
			        	  lStrValue=lArrDept.get(i+1).toString();
		          //System.out.println("-----------------Value------------ " + lStrValue);
		          gHashChartMap.put("DeptName", lStrValue);
		          
		          if(lStrReportCode.equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSTotalReport")))
		          {
		        	  lArrInner.add(lStrValue);
		          }
		          else
		          lArrInner.add(lStrKey+" : "+lStrValue);
		          
		          
		          lDouBEAmt = (lhshEstimatedAmt.get(lStrKey + "_PL") != null) ? Double.parseDouble(lhshEstimatedAmt.get(lStrKey + "_PL").toString()) : 0;
		          //System.out.println("Budget DouPL Amount:"+lDouBEAmt);
		          lLngTotalPLBudAmt +=  Math.round(lDouBEAmt);
		          //System.out.println("Budget LongPL Amount:"+lLngTotalPLBudAmt);
		          lArrInner.add(lLngTotalPLBudAmt!=0?""+lLngTotalPLBudAmt:"-");  // BE_PL
		          lDblTotalDiffPLBudAmt += (lDouBEAmt -lLngTotalPLBudAmt); 
		          lLngPLTotal+=lLngTotalPLBudAmt;
		          
		          lDblNPBudAmt = (lhshEstimatedAmt.get(lStrKey + "_NP") != null) ?  Double.parseDouble(lhshEstimatedAmt.get(lStrKey + "_NP").toString()) : 0;
		          //System.out.println("Budget DouNP Amount:"+lDblNPBudAmt);
		          lLngTotalNPBudAmt +=  Math.round(lDblNPBudAmt);
		          //System.out.println("Budget LongNP Amount:"+lLngTotalNPBudAmt);
		          lArrInner.add(lLngTotalNPBudAmt!=0?""+lLngTotalNPBudAmt:"-");  // BE_NP
		          lDblTotalDiffNPBudAmt +=(lDblNPBudAmt- lLngTotalNPBudAmt);
		          lLngNPTotal+=lLngTotalNPBudAmt;
		     
		          lDblCSSBudAmt = (lhshEstimatedAmt.get(lStrKey + "_CSS") != null) ? Double.parseDouble(lhshEstimatedAmt.get(lStrKey + "_CSS").toString()) : 0;
		          //System.out.println("Budget DouCSS Amount:"+lDblCSSBudAmt);
		          lLngTotalCSSBudAmt +=  Math.round(lDblCSSBudAmt);
		          //System.out.println("Budget CSS Amount:"+lLngTotalCSSBudAmt);
		          lArrInner.add(lLngTotalCSSBudAmt!=0?""+lLngTotalCSSBudAmt:"-"); //BE_CSS
		          lDblTotalDiffCSSBudAmt += (lDblCSSBudAmt-lLngTotalCSSBudAmt);
		          lLngCSSTotal+=lLngTotalCSSBudAmt;
		          
		          lDblBudTotalAmt = (lhshEstimatedAmt.get(lStrKey + "_Total") != null) ? Double.parseDouble(lhshEstimatedAmt.get(lStrKey + "_Total").toString()) : 0;
		          lLngBudTotalAmt +=  Math.round(lDblBudTotalAmt);
		          //System.out.println("Budget Total Amount:"+lLngBudTotalAmt);
		          lArrInner.add(lLngBudTotalAmt!=0?""+lLngBudTotalAmt:"-"); //BE_Total
		          lDblBudTotalDiffAmt += (lDblBudTotalAmt-lLngBudTotalAmt);
		          lLngBudTotal+=lLngBudTotalAmt;
		          gHashChartMap.put("BudgetTotal", lLngBudTotalAmt);
		          
		          lDouGrantAmt = (lhshGrantReleaseAmt.get(lStrKey + "_PL") != null) ?Double.parseDouble(lhshGrantReleaseAmt.get(lStrKey + "_PL").toString())/10000000 : 0;
		          //System.out.println("Grant Amount for DouPL:"+lDouGrantAmt);
		          lLngGrantTotal +=  Math.round(lDouGrantAmt);
		          lArrInner.add(lLngGrantTotal!=0?""+lLngGrantTotal:"-");					//Grant_PL
	              lDblTotalDiffPLGrntAmt += (lDouGrantAmt-lLngGrantTotal);
	              lLngGrantPLAmt+=lLngGrantTotal;
	              
	              lDblNPGrntAmt = (lhshGrantReleaseAmt.get(lStrKey + "_NP") != null) ?Double.parseDouble(lhshGrantReleaseAmt.get(lStrKey + "_NP").toString())/10000000: 0;
	              //System.out.println("Grant Amount for DouNP:"+lDblNPGrntAmt);
	              lLngTotalNPGrntAmt +=  Math.round(lDblNPGrntAmt);
	              lArrInner.add(lLngTotalNPGrntAmt!=0?""+lLngTotalNPGrntAmt:"-");					//Grant_NP
	              lDblTotalDiffNPGrntAmt += (lDblNPGrntAmt - lLngTotalNPGrntAmt);
	              lLngGrantNPAmt+=lLngTotalNPGrntAmt;
	              
	              lDblCSSGrntAmt = (lhshGrantReleaseAmt.get(lStrKey + "_CSS") != null) ?Double.parseDouble(lhshGrantReleaseAmt.get(lStrKey + "_CSS").toString())/10000000 : 0;
	              lLngTotalCSSGrntAmt +=  Math.round(lDblCSSGrntAmt);
	              lArrInner.add(lLngTotalCSSGrntAmt!=0?""+lLngTotalCSSGrntAmt:"-");				//Grant_CSS
	              lDblTotalDiffCSSGrntAmt += (lDblCSSGrntAmt-lLngTotalCSSGrntAmt);
	              lLngGrantCSSAmt+=lLngTotalCSSGrntAmt;
	              
	              lDblGrantTotalAmt = (lhshGrantReleaseAmt.get(lStrKey + "_Total") != null) ? Double.parseDouble(lhshGrantReleaseAmt.get(lStrKey + "_Total").toString())/10000000 : 0;
		          lLngGrantTotalAmt +=  Math.round(lDblGrantTotalAmt);
		          lArrInner.add(lLngGrantTotalAmt!=0?""+lLngGrantTotalAmt:"-"); //Grant_Total
		          lDblGrantTotalDiffAmt += (lDblGrantTotalAmt-lLngGrantTotalAmt);
		          lLngGrantAmtTotal+=lLngGrantTotalAmt;
		          gHashChartMap.put("GrantTotal", lLngGrantTotalAmt);
	              
	              lDblPLCurrExpAmt = (lhshDeptCurrExpAmt.get(lStrKey + "_PL") != null) ? Double.parseDouble(lhshDeptCurrExpAmt.get(lStrKey + "_PL").toString()) : 0;
	              lLngTotalPLExpAmt +=  Math.round(lDblPLCurrExpAmt);
	              lArrInner.add(lLngTotalPLExpAmt!=0?""+lLngTotalPLExpAmt:"-");	//curr_PL
	              lDblTotalDiffPLCurrAmt+=(lDblPLCurrExpAmt-lLngTotalPLExpAmt);
	              lLngCurrPLAmt+=lLngTotalPLExpAmt;
	              
	              lDblNPCurrExpAmt = (lhshDeptCurrExpAmt.get(lStrKey + "_NP") != null) ? Double.parseDouble(lhshDeptCurrExpAmt.get(lStrKey + "_NP").toString()) : 0;
	              lLngTotalNPExpAmt +=  Math.round(lDblNPCurrExpAmt);
	              lArrInner.add(lLngTotalNPExpAmt!=0?""+lLngTotalNPExpAmt:"-");	//curr_NP
	              lDblTotalDiffNPCurrAmt+=(lDblNPCurrExpAmt-lLngTotalNPExpAmt);
	              lLngCurrNPAmt+=lLngTotalNPExpAmt;
	              
	              lDblCSSCurrExpAmt = (lhshDeptCurrExpAmt.get(lStrKey + "_CSS") != null) ?Double.parseDouble(lhshDeptCurrExpAmt.get(lStrKey + "_CSS").toString()) : 0;
	              lLngTotalCSSExpAmt +=  Math.round(lDblCSSCurrExpAmt);
	              lArrInner.add(lLngTotalCSSExpAmt!=0?""+lLngTotalCSSExpAmt:"-");	//curr_CSS
	              
	              lDblTotalDiffCSSCurrAmt+=(lDblCSSCurrExpAmt-lLngTotalCSSExpAmt);
	             
	              lLngCurrCSSAmt+=lLngTotalCSSExpAmt;
	              
	              lDblCurrTotalAmt = (lhshDeptCurrExpAmt.get(lStrKey + "_Total") != null) ? Double.parseDouble(lhshDeptCurrExpAmt.get(lStrKey + "_Total").toString()) : 0;
		          lLngCurrTotalAmt +=  Math.round(lDblCurrTotalAmt);
		          lArrInner.add(lLngCurrTotalAmt!=0?""+lLngCurrTotalAmt:"-"); //Curr_Total
		          lDblCurrTotalDiffAmt += (lDblCurrTotalAmt-lLngCurrTotalAmt);
		          lLngCurrAmtTotal+=lLngCurrTotalAmt;
		          gHashChartMap.put("CurrentTotal", lLngCurrTotalAmt);
	              
	              lDblPLProgExpAmt = (lhshDeptPrgExpAmt.get(lStrKey + "_PL") != null) ? Double.parseDouble(lhshDeptPrgExpAmt.get(lStrKey + "_PL").toString()) : 0;
	              lLngTotalPLPrgExpAmt +=  Math.round(lDblPLProgExpAmt);
	              lArrInner.add(lLngTotalPLPrgExpAmt!=0?""+lLngTotalPLPrgExpAmt:"-");	//Prog_PL
	              lDblTotalDiffPLPrgCurrAmt+=(lDblPLProgExpAmt-lLngTotalPLPrgExpAmt);
	              lLngPrgPLAmt+=lLngTotalPLPrgExpAmt;
	              
	              lDblNPProgExpAmt = (lhshDeptPrgExpAmt.get(lStrKey + "_NP") != null) ? Double.parseDouble(lhshDeptPrgExpAmt.get(lStrKey + "_NP").toString()) : 0;
	              lLngTotalNPPrgExpAmt +=  Math.round(lDblNPProgExpAmt);
	              lArrInner.add(lLngTotalNPPrgExpAmt!=0?""+lLngTotalNPPrgExpAmt:"-");	//Prog_NP
	              lDblTotalDiffNPPrgCurrAmt+=(lDblNPProgExpAmt-lLngTotalNPPrgExpAmt);
	              lLngPrgNPAmt+=lLngTotalNPPrgExpAmt;
	              
	              lDblCSSProgExpAmt = (lhshDeptPrgExpAmt.get(lStrKey + "_CSS") != null) ? Double.parseDouble(lhshDeptPrgExpAmt.get(lStrKey + "_CSS").toString()) : 0;
	              lLngTotalCSSPrgExpAmt +=  Math.round(lDblCSSProgExpAmt);
	              lArrInner.add(lLngTotalCSSPrgExpAmt!=0?""+lLngTotalCSSPrgExpAmt:"-");	//Prog_CSS
	              lDblTotalDiffCSSPrgCurrAmt+=(lDblCSSProgExpAmt-lLngTotalCSSPrgExpAmt);
	              lLngPrgCSSAmt+=lLngTotalCSSPrgExpAmt;
	              
	              lDblProgTotalAmt = (lhshDeptPrgExpAmt.get(lStrKey + "_Total") != null) ? Double.parseDouble(lhshDeptPrgExpAmt.get(lStrKey + "_Total").toString()) : 0;
		          lLngProgTotalAmt +=  Math.round(lDblProgTotalAmt);
		          lArrInner.add(lLngProgTotalAmt!=0?""+lLngProgTotalAmt:"-"); //Prog_Total
		          lDblProgTotalDiffAmt += (lDblProgTotalAmt-lLngProgTotalAmt);
		          lLngProgAmtTotal+=lLngProgTotalAmt;
		          
		          
		          if(lLngGrantTotalAmt==0 || ldiffDays==0)
		          {
		        	  lDouGrantProreta=0;
		          }
		          else
		          {
		        	  lDouGrantProreta=(lLngCurrTotalAmt*100*lObjGrantDtlDAOImpl.getGrantPeriod(Todate, lFinYear))/(Math.round(lLngGrantTotalAmt)*ldiffDays);
		        	
		        	  //System.out.println("Grant Proreta::"+lDouGrantProreta);
		        	 
		        	  
		          }
		          
		          lArrInner.add(lDouGrantProreta!=0?""+lDouGrantProreta:"-");
	             
		          if(lLngBudTotalAmt==0 || ldiffDays==0)
		          {
		        	  lDouBudProreta=0;
		          }
		          else
		          {
		        	 try
		        	 {
		        	  lDouBudProreta=Math.round(lLngProgTotalAmt*100)/(Math.round(lLngBudTotalAmt));   
		        	 
		        	  //System.out.println("Budget Proreta is::"+lDouBudProreta);
		        
		        	 }
		        	 catch(ArithmeticException e)
		        	 {
		        	  	e.printStackTrace();
		        	 }
		          }
		          //System.out.println("Budget Proreta is:"+lDouBudProreta);
		          lArrInner.add(lDouBudProreta!=0?""+lDouBudProreta:"-");   
		       
	              lArrInner.add("-");
	              lArrChart.add(gHashChartMap);
	              
	              if (lStrReportCode.equals(gObjRsrcBndle.getString("DeptWisePlanNonPlanCSSTotalReport")))
	              {
		              lArrInner.add("View");
		              lArrInner.add("View");
		              lArrInner.add(lHashPara.get("FinYrId").toString());
		 			  lArrInner.add(lArrDept.get(i).toString());
		 			  lArrInner.add(lHashPara.get("PlanType").toString());
		 			  lArrInner.add(lHashPara.get("FDate").toString());
		 			  lArrInner.add(lHashPara.get("EndDate").toString());
		 			  lArrInner.add(lHashPara.get("diffdays").toString());
	              }
	              lArrReturn.add(lArrInner);
			}
			
			 ArrayList<Object> lArrInner = new ArrayList<Object>();
			 
			 lArrInner.add(" ");
			 StyledData dataStyle = new StyledData();
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("Total");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngPLTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngNPTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCSSTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngBudTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngGrantPLAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngGrantNPAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngGrantCSSAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngGrantAmtTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurrPLAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurrNPAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurrCSSAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngCurrAmtTotal);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngPrgPLAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngPrgNPAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngPrgCSSAmt);
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lLngProgAmtTotal);
			 lArrInner.add(dataStyle);
			
			if(lLngGrantAmtTotal==0 || ldiffDays==0)
	          {
				lLngTotalGrantProreta=0;
				dataStyle = new StyledData();
			     dataStyle.setStyles(reportStyleVO);
			     dataStyle.setData(lLngTotalGrantProreta);
				 lArrInner.add(dataStyle);
	          }
			else
			{
				try
				{
					 lLngTotalGrantProreta=Math.round(lLngCurrAmtTotal*100*lObjGrantDtlDAOImpl.getGrantPeriod(Todate, lFinYear))/(Math.round(lLngGrantAmtTotal*ldiffDays));
					 dataStyle = new StyledData();
				     dataStyle.setStyles(reportStyleVO);
				     dataStyle.setData(lLngTotalGrantProreta);
					 lArrInner.add(dataStyle);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			if(lLngBudTotal==0)
	          {
				lLngTotalBudgetProreta=0;
				dataStyle = new StyledData();
			     dataStyle.setStyles(reportStyleVO);
			     dataStyle.setData(lLngTotalBudgetProreta);
				 lArrInner.add(dataStyle);
	          }
			else
			{
				try
				{
					 lLngTotalBudgetProreta=Math.round(lLngProgAmtTotal*100)/(Math.round(lLngBudTotal));
					 dataStyle = new StyledData();
				     dataStyle.setStyles(reportStyleVO);
				     dataStyle.setData(lLngTotalBudgetProreta);
					 lArrInner.add(dataStyle);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				 
			}
	
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 lArrReturn.add(lArrInner);
			 
			 
			 lArrInner=new ArrayList();
			 
			 lArrInner.add(" ");
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("Rounded Difference");
			 lArrInner.add(dataStyle);	 
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffPLBudAmt));
			 lArrInner.add(dataStyle);	
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffNPBudAmt));
			 lArrInner.add(dataStyle);	
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffCSSBudAmt));
			 lArrInner.add(dataStyle);	
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblBudTotalDiffAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffPLGrntAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffNPGrntAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffCSSGrntAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblGrantTotalDiffAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffPLCurrAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffNPCurrAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffCSSCurrAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblCurrTotalDiffAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffPLPrgCurrAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffNPPrgCurrAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblTotalDiffCSSPrgCurrAmt));
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(lnfLong.format(lDblProgTotalDiffAmt));
			 lArrInner.add(dataStyle);
			
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
		
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData("-");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 dataStyle = new StyledData();
		     dataStyle.setStyles(reportStyleVO);
		     dataStyle.setData(" ");
			 lArrInner.add(dataStyle);
			 
			 lArrReturn.add(lArrInner);
			 return lArrReturn;
		}
		

		private List getDeptPlanNonPlanCssExpMjrHd(ReportVO lObjReport, Object criteria)
		{
			glogger.info("inside getDeptPlanNonPlanCssExpMjrHd");
			List<ArrayList> lArrReturn = new ArrayList<ArrayList>();
			//HashMap lHshMapDeptMjrHd=null;
			ArrayList lArrDeptMjrHd=null;
			int lintLangId=0;
			
			long ldiffDays=0;
			
			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);
		
			HashMap<String,Object> lHashPara=new HashMap<String,Object>();
		
	    	Hashtable requestParam = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.REQUEST_KEYS );

			glogger.info(requestParam.get("finYear")+"-->"+requestParam.get("planType")+"-->"+
					lObjReport.getLangId()+"-->"+requestParam.get("DeptId")+"-->"+
					requestParam.get("fromDate")+"-->"+requestParam.get("toDate")+"-->"+
					requestParam.get("diffdays"));
			
			lHashPara=getParameters(lObjReport, criteria);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			lintLangId=Integer.parseInt(lHashPara.get("LangId").toString());
			
			try
			{
				lArrDeptMjrHd = lObjRptQueryDAO.getAllMjrHdsForDept(lHashPara); 
			}
			catch(Exception e)
			{
				glogger.error("Error in execution of getAllMjrHdsForDept and Error is : " + e,e);
				e.printStackTrace();
			}
			
		
			HashMap lhshEstimatedAmt=lObjRptQueryDAO.getBudEstForMjrHd(lHashPara);
		
			HashMap lhshGrantReleaseAmt=lObjRptQueryDAO.getGrantForMjrHd(lHashPara);
		
			HashMap lhshDeptCurrExpAmt= lObjRptQueryDAO.getExpForDeptMjrHd(lHashPara);

			lHashPara.put("FDate",lHashPara.get("PrgDate"));
			HashMap lhshDeptPrgExpAmt= lObjRptQueryDAO.getExpForDeptMjrHd(lHashPara);
		//	lHashPara.put("FDate", lHashPara.get("FDate"));
			
			lArrReturn = getPlNpCssList(lArrDeptMjrHd, lhshEstimatedAmt, lhshGrantReleaseAmt,
					lhshDeptCurrExpAmt, lhshDeptPrgExpAmt, lHashPara, lObjReport.getReportCode());
					
			return lArrReturn;
		}
		
		private List getDeptPlanNonPlanCssExpScheme(ReportVO lObjReport, Object criteria)
		{
			glogger.info("inside getDeptPlanNonPlanCssExpScheme");
			List<ArrayList> lArrReturn = new ArrayList<ArrayList>();
			//HashMap lHshMapDeptMjrHd=null;
			ArrayList lArrDeptMjrHd=null;
			int lintLangId = 0;
			long ldiffDays=0;
			
			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);
	        
			HashMap<String,Object> lHashPara=new HashMap<String,Object>();

	    	Hashtable requestParam = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.REQUEST_KEYS );

			glogger.info(requestParam.get("finYear")+"--"+requestParam.get("DeptId")+"--"+
					requestParam.get("planType")+"--"+
					requestParam.get("fromDate")+"--"+requestParam.get("toDate")+"--"+
					requestParam.get("diffdays"));
			
			lHashPara=getParameters(lObjReport, criteria);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			lintLangId=Integer.parseInt(lHashPara.get("LangId").toString());
			
			try
			{
				lArrDeptMjrHd = lObjRptQueryDAO.getAllSchemesForDept(lHashPara); 
			}
			catch(Exception e)
			{
				glogger.error("Error in execution of getAllSchemesForDept and Error is : " + e,e);
				e.printStackTrace();
			}
			
			
			HashMap lhshEstimatedAmt=lObjRptQueryDAO.getBudEstForScheme(lHashPara);
			
			HashMap lhshGrantReleaseAmt=lObjRptQueryDAO.getGrantForScheme(lHashPara);
		
			HashMap lhshDeptCurrExpAmt=lObjRptQueryDAO.getExpForSchemeMjrHd(lHashPara);
			
			lHashPara.put("FDate",lHashPara.get("PrgDate"));
			HashMap lhshDeptPrgExpAmt= lObjRptQueryDAO.getExpForSchemeMjrHd(lHashPara);
			
			lArrReturn = getPlNpCssList(lArrDeptMjrHd, lhshEstimatedAmt, lhshGrantReleaseAmt,
					lhshDeptCurrExpAmt, lhshDeptPrgExpAmt, lHashPara, lObjReport.getReportCode());
			return lArrReturn;			
		}
		
		private List getDeptTotalPlanNonPlanCssExpMjrHd(ReportVO lObjReport, Object criteria)
		{
			glogger.info("inside getDeptTotalPlanNonPlanCssExpMjrHd");
			List<ArrayList> lArrReturn = new ArrayList<ArrayList>();
			//HashMap lHshMapDeptMjrHd=null;
			ArrayList lArrDeptMjrHd=null;
			int lintLangId=0;
			
			long ldiffDays=0;
			
			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);
		
			HashMap<String,Object> lHashPara=new HashMap<String,Object>();
		
	    	Hashtable requestParam = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.REQUEST_KEYS );

			glogger.info(requestParam.get("finYear")+"-->"+requestParam.get("planType")+"-->"+
					lObjReport.getLangId()+"-->"+requestParam.get("DeptId")+"-->"+
					requestParam.get("fromDate")+"-->"+requestParam.get("toDate")+"-->"+
					requestParam.get("diffdays"));
			lHashPara=getParameters(lObjReport, criteria);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			lintLangId=Integer.parseInt(lHashPara.get("LangId").toString());
			
			try
			{
				lArrDeptMjrHd = lObjRptQueryDAO.getAllMjrHdsForDept(lHashPara); 
			}
			catch(Exception e)
			{
				glogger.error("Error in execution of getAllMjrHdsForDept and Error is : " + e,e);
				e.printStackTrace();
			}
			
		
			HashMap lhshEstimatedAmt=lObjRptQueryDAO.getBudEstForMjrHd(lHashPara);
		
			HashMap lhshGrantReleaseAmt=lObjRptQueryDAO.getGrantForMjrHd(lHashPara);
		
			HashMap lhshDeptCurrExpAmt= lObjRptQueryDAO.getExpForDeptMjrHd(lHashPara);

			lHashPara.put("FDate",lHashPara.get("PrgDate"));
			HashMap lhshDeptPrgExpAmt= lObjRptQueryDAO.getExpForDeptMjrHd(lHashPara);
			
			
			lArrReturn = getPlNpCssTotalList(lArrDeptMjrHd, lhshEstimatedAmt, lhshGrantReleaseAmt,
					lhshDeptCurrExpAmt, lhshDeptPrgExpAmt, lHashPara, lObjReport.getReportCode());
					
			return lArrReturn;
		}
		
		private List getDeptTotalPlanNonPlanCssExpScheme(ReportVO lObjReport, Object criteria)
		{
			glogger.info("inside getDeptTotalPlanNonPlanCssExpScheme");
			List<ArrayList> lArrReturn = new ArrayList<ArrayList>();
			//HashMap lHshMapDeptScheme=null;
			ArrayList lArrDeptScheme=null;
			int lintLangId = 0;
			long ldiffDays=0;
			
			NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);
	        
			HashMap<String,Object> lHashPara=new HashMap<String,Object>();

	    	Hashtable requestParam = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.REQUEST_KEYS );

			glogger.info(requestParam.get("finYear")+"--"+requestParam.get("DeptId")+"--"+
					requestParam.get("planType")+"--"+
					requestParam.get("fromDate")+"--"+requestParam.get("toDate")+"--"+
					requestParam.get("diffdays"));
			
			lHashPara=getParameters(lObjReport, criteria);
			ldiffDays=Long.parseLong(lHashPara.get("diffdays").toString());
			lintLangId=Integer.parseInt(lHashPara.get("LangId").toString());
			
			try
			{
				lArrDeptScheme = lObjRptQueryDAO.getAllSchemesForDept(lHashPara); 
			}
			catch(Exception e)
			{
				glogger.error("Error in execution of getAllSchemesForDept and Error is : " + e,e);
				e.printStackTrace();
			}
			
			
			HashMap lhshEstimatedAmt=lObjRptQueryDAO.getBudEstForScheme(lHashPara);
			
			HashMap lhshGrantReleaseAmt=lObjRptQueryDAO.getGrantForScheme(lHashPara);
		
			HashMap lhshDeptCurrExpAmt=lObjRptQueryDAO.getExpForSchemeMjrHd(lHashPara);
			
			lHashPara.put("FDate",lHashPara.get("PrgDate"));
			HashMap lhshDeptPrgExpAmt= lObjRptQueryDAO.getExpForSchemeMjrHd(lHashPara);
			
			lArrReturn = getPlNpCssTotalList(lArrDeptScheme, lhshEstimatedAmt, lhshGrantReleaseAmt,
					lhshDeptCurrExpAmt, lhshDeptPrgExpAmt, lHashPara, lObjReport.getReportCode());
			return lArrReturn;			
		}
	/* End By Maneesh*/	
	 	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		*****************************************************************************************************************
//		***STATE PROFILE REPORT***
//		******************************************************************************************************************

		private List getStateProfileReport(ReportVO lObjReport) 
		{

			glogger.info("************************************ getStateProfileReport*********************************");

//			Variable Declaration of State Profile Report-----------------------------------------------------------

			
			String lStrLangId = null;
			String lStrFundNm = null;

			int fundType = 0;
			int lintFinYear = 0;
			int lIntPrgDiffDays = 0;
			int lintPrevFinYear = 0;

			long ldiffDays = 0;
			long longGrantPeriod = 0;

			HashMap lhshPrevDateMap = null;
			HashMap<String,Double> lhshConsolidatedFund = null;
			HashMap lhshContingencyFund = null;
			HashMap lhshPublicFund = null;
			HashMap lhshDateMap = null;

			NumberFormat lnfLong = null;

			List lArrMainDataLst = null;

			ArrayList lArrReturn = null;

			DSSQueryDAO gObjRptQueryDAO = null;

//			Variable Declaration Completes here (StateProfileReport)---------------------------------------------------------

//			Variable Initialization & Parameter Fetching Starts (StateProfileReport----------------------------------------------------------------

			gObjRptQueryDAO = (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			gObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
			
			GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
			
			

			if (lObjReport.getParameterValue("Datefrom") != "")
				gStrFromDate = (String) lObjReport.getParameterValue("Datefrom");
			else
			{
				gStrFromDate = gObjRptQueryDAO.getFromDate(gObjRptQueryDAO.getTodayDate());
				glogger.info("--------------------hi this is from date :- " + gStrFromDate);
			}
				
			if (lObjReport.getParameterValue("Dateto") != "")
				gStrToDate = (String) lObjReport.getParameterValue("Dateto");
			else
			{
				gStrToDate = gObjRptQueryDAO.getTodayDate();
				glogger.info("--------------------hi this is from date :- " + gStrToDate);
			}
				

			lhshDateMap = getDates(gStrFromDate, gStrToDate);
			ldiffDays = Long.parseLong(lhshDateMap.get("difference").toString());
			lIntPrgDiffDays = Integer.parseInt(lhshDateMap.get("prgDifference").toString());

			lhshPrevDateMap = getPreviousDates(gStrFromDate, gStrToDate);
			lStrPrevFromDate = lhshPrevDateMap.get("fromdate").toString();
			lStrPrevToDate = lhshPrevDateMap.get("todate").toString();

			lintFinYear = gObjRptQueryDAO.getFinancialYear(gStrFromDate, gStrToDate);

			lintPrevFinYear = 6;// <--need to get this from some method
			lStrLangId = lObjReport.getLangId();

			lnfLong = NumberFormat.getNumberInstance();
			lnfLong.setGroupingUsed(false);
			lnfLong.setMaximumFractionDigits(3);
			lnfLong.setMinimumFractionDigits(3);

			lhshConsolidatedFund = getConsolidatedFund(lintFinYear,lStrLangId, gStrFromDate, gStrToDate);
			lhshContingencyFund = getContingencyPublicFund(lintFinYear,lStrLangId, gStrFromDate, gStrToDate, Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")));
			lhshPublicFund = getContingencyPublicFund(lintFinYear,lStrLangId, gStrFromDate, gStrToDate, Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")));

			lArrMainDataLst = new ArrayList();
			lArrReturn = new ArrayList();
			
			longGrantPeriod = lObjGrantDtlDAOImpl.getGrantPeriod(gStrToDate, lintFinYear);

//			Variable Initialization Completes----------------------------------------------------------------------------------------------------------

//			First Row I) Consolidated Fund----------------------------------------------------------------------		

			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFund")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);


//			Second Row 1) Revenue REceipt--------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);


//			Third Row 2) Revenue Expenditure-------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")),true,ldiffDays,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);

//			Fourth Row 3) SurPlus Deficit--------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitRevenue")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);


//			Fifth Row 4) Capital REceipt--------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);


//			Sixth Row 5) Capital Expenditure------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")),true,ldiffDays,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);


//			Seven Row 6) Surplus / Deficit---------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitCapital")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);


//			Eighth Row 7) Consolidated Fund Receipt---------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundReceipt")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);

//			Ninth Row 8) Consolidated Fund Expenditure---------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundExpenditure")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);

//			Tenth Row 9) Net Consolidated Fund---------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("NetConsolidatedFund")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);

//			Eleventh Row II) Contingency Fund-----------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshContingencyFund , Integer.parseInt(gObjRsrcBndle.getString("ContigencyFund")),true,0,longGrantPeriod);		
			lArrMainDataLst.add(lArrReturn);


//			Twelfth Row 10) Contigency(NET)--------------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshContingencyFund , Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);


//			Thirteenth Row III) Public Account-----------------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshContingencyFund , Integer.parseInt(gObjRsrcBndle.getString("PublicAccount")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);


//			Fourteenth Row 11) Public Account(NET)	
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshPublicFund , Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);

//			Fifteenth Row Total(I+II+III)		
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshContingencyFund , Integer.parseInt(gObjRsrcBndle.getString("Total")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);

//			Sixteenth Row 	Rounded Difference
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshContingencyFund , Integer.parseInt(gObjRsrcBndle.getString("RoundedDifference")),true,0,longGrantPeriod);
			lArrMainDataLst.add(lArrReturn);
			glogger.info("Fifteenth Row has been Added");

//			******************************New Coding Completes here for this Function**************************************


//			*Array List Addtion for first row ----------------------------
			lArrReturn = new ArrayList();
			lArrReturn.add(" <b>I-Consolidated Fund</b>");
			for (int i = 0; i < 20; i++)
				lArrReturn.add("");
			lArrReturn.add(100001);
			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			*-----------------------------------------------

			glogger.info("////////////////????????????????First MileStone");
//			-------------------------------------------------------------------

			lArrReturn = new ArrayList();
			long lBudPLRevRecp = 0;
			long lBudNPRevRecp = 0;
			long lBudCSSRevRecp = 0;
			long lBudTotalRevRecp = 0;
			long lGrantPLRevRecp = 0;
			long lGrantNPRevRecp = 0;
			long lGrantCSSRevRecp = 0;
			long lGrantTotalRevRecp = 0;
			long lCurrPLRevRecp = 0;
			long lCurrNPRevRecp = 0;
			long lCurrCSSRevRecp = 0;
			long lCurrTotalRevRecp = 0;
			long lPrgPLRevRecp = 0;
			long lPrgNPRevRecp = 0;
			long lPrgCSSRevRecp = 0;
			long lPrgTotalRevRecp = 0;



			glogger.info("Hi i have passed module1");

			lStrFundNm = "RevRecp";
			lArrReturn.add("1) Revenue Receipt");
			glogger.info("Values is added tp ;ArrReturn");
//			-----------------Budget Start---------------
			lBudPLRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_PL").toString()));

			glogger.info("First values is fetched");

			gdbBudPLFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_PL").toString())
					- lBudPLRevRecp;

			lArrReturn.add(lBudPLRevRecp != 0 ? lBudPLRevRecp : "-");

			lBudNPRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_NP").toString()));

			gdbBudNPFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_NP").toString())
					- lBudNPRevRecp;

			lArrReturn.add(lBudNPRevRecp != 0 ? lBudNPRevRecp : "-");

			lBudCSSRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Bud_CSS").toString()));

			gdbBudCSSFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_CSS").toString())
					- lBudCSSRevRecp;

			lArrReturn.add(lBudCSSRevRecp != 0 ? lBudCSSRevRecp : "-");

			lBudTotalRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Bud_Total").toString()));

			gdbBudTotalFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_Total").toString())
					- lBudTotalRevRecp;

			glogger.info("Hi i have passed module2");
			glogger.info("Data is fetched from Hashmaps");

			StyledData dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(Math.round(lBudTotalRevRecp));
			lArrReturn.add(lBudTotalRevRecp != 0 ? dataStyle : "-");


			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData("-");
			lArrReturn.add(dataStyle);
//			-----------------Grant End---------------

//			-----------------Curr Start---------------
			lCurrPLRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_PL").toString()));
			gdbCurrPLFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_PL").toString())
					- lCurrPLRevRecp;

			lArrReturn.add(lCurrPLRevRecp != 0 ? lCurrPLRevRecp : "-");

			lCurrNPRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_NP").toString()));
			gdbCurrNPFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_NP").toString())
					- lCurrNPRevRecp;
			lArrReturn.add(lCurrNPRevRecp != 0 ? lCurrNPRevRecp : "-");

			lCurrCSSRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_CSS").toString()));
			gdbCurrCSSFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_CSS").toString())
					- lCurrCSSRevRecp;
			lArrReturn.add(lCurrCSSRevRecp != 0 ? lCurrCSSRevRecp : "-");

			lCurrTotalRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_Total").toString()));
			gdbCurrTotalFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_Total").toString())
					- lCurrTotalRevRecp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lCurrTotalRevRecp);
			lArrReturn.add(lCurrTotalRevRecp != 0 ? dataStyle : "-");
//			-----------------Curr End---------------

//			-----------------Prg Start---------------
			lPrgPLRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_PL").toString()));
			gdbPrgPLFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_PL").toString())
					- lPrgPLRevRecp;
			lArrReturn.add(lPrgPLRevRecp != 0 ? lPrgPLRevRecp : "-");

			lPrgNPRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_NP").toString()));
			gdbCurrNPFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_NP").toString())
					- lPrgNPRevRecp;
			lArrReturn.add(lPrgNPRevRecp != 0 ? lPrgNPRevRecp : "-");

			lPrgCSSRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Prg_CSS").toString()));
			gdbPrgCSSFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_CSS").toString())
					- lPrgCSSRevRecp;
			lArrReturn.add(lPrgCSSRevRecp != 0 ? lPrgCSSRevRecp : "-");

			lPrgTotalRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Prg_Total").toString()));
			gdbPrgTotalFracPart = Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_Total").toString())
					- lPrgTotalRevRecp;

			glogger.info("Hi i have passed module3");
			glogger.info("Data is fetched from Hashmaps");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lPrgTotalRevRecp);
			lArrReturn.add(lPrgTotalRevRecp != 0 ? dataStyle : "-");
//			-----------------Prg End---------------

			glogger.info("////////////////????????????????Second MileStone");
			lArrReturn.add("-");

			if (lBudTotalRevRecp != 0)
				lArrReturn.add((int) (lPrgTotalRevRecp * 100) / lBudTotalRevRecp);
			else
				lArrReturn.add("-");

			lArrReturn.add("-");
			lArrReturn.add("-");

//			*Array List Addtion for second row ----------------------------
			lArrReturn.add(Math.round(lhshConsolidatedFund.get(lStrFundNm+"_fundType")));
			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			--------------------------------------------------------------------------------
			lArrReturn = new ArrayList();
			long lBudPLRevExp = 0;
			long lBudNPRevExp = 0;
			long lBudCSSRevExp = 0;
			long lBudTotalRevExp = 0;
			long lGrantPLRevExp = 0;
			long lGrantNPRevExp = 0;
			long lGrantCSSRevExp = 0;
			long lGrantTotalRevExp = 0;
			long lCurrPLRevExp = 0;
			long lCurrNPRevExp = 0;
			long lCurrCSSRevExp = 0;
			long lCurrTotalRevExp = 0;
			long lPrgPLRevExp = 0;
			long lPrgNPRevExp = 0;
			long lPrgCSSRevExp = 0;
			long lPrgTotalRevExp = 0;

			lStrFundNm = "RevExp";
			lArrReturn.add("2) Revenue Expenditure");
//			-----------------Budget Start---------------
			lBudPLRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_PL").toString()));
			gdbBudPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_PL").toString())
					- lBudPLRevExp;
			lArrReturn.add(lBudPLRevExp != 0 ? lBudPLRevExp : "-");

			lBudNPRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_NP").toString()));
			gdbBudNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_NP").toString())
					- lBudNPRevExp;
			lArrReturn.add(lBudNPRevExp != 0 ? lBudNPRevExp : "-");

			lBudCSSRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_CSS").toString()));
			gdbBudCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_CSS").toString())
					- lBudCSSRevExp;
			lArrReturn.add(lBudCSSRevExp != 0 ? lBudCSSRevExp : "-");

//			lBudTotalRevExp =
//			Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()));
			lBudTotalRevExp = lBudPLRevExp + lBudNPRevExp + lBudCSSRevExp;
			gdbBudTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_Total").toString())
					- lBudTotalRevExp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lBudTotalRevExp);
			lArrReturn.add(lBudTotalRevExp != 0 ? dataStyle : "-");
//			-----------------Budget End---------------

//			-----------------Grant Start---------------
			lGrantPLRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Grant_PL").toString()));
			gdbGrantPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Grant_PL").toString())
					- lGrantPLRevExp;
			lArrReturn.add(lGrantPLRevExp != 0 ? lGrantPLRevExp : "-");

			lGrantNPRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Grant_NP").toString()));
			gdbGrantNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Grant_NP").toString())
					- lGrantNPRevExp;
			lArrReturn.add(lGrantNPRevExp != 0 ? lGrantNPRevExp : "-");

			lGrantCSSRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Grant_CSS").toString()));
			gdbGrantCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Grant_CSS").toString())
					- lGrantCSSRevExp;
			lArrReturn.add(lGrantCSSRevExp != 0 ? lGrantCSSRevExp : "-");

			lGrantTotalRevExp = lGrantPLRevExp + lGrantNPRevExp + lGrantCSSRevExp;
			gdbGrantTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Grant_Total").toString())
					- lGrantTotalRevExp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lGrantTotalRevExp);
			lArrReturn.add(lGrantTotalRevExp != 0 ? dataStyle : "-");
//			-----------------Grant End---------------

//			-----------------Curr Start---------------
			lCurrPLRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_PL").toString()));
			gdbCurrPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_PL").toString())
					- lCurrPLRevExp;
			lArrReturn.add(lCurrPLRevExp != 0 ? lCurrPLRevExp : "-");

			lCurrNPRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_NP").toString()));
			gdbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_NP").toString())
					- lCurrNPRevExp;
			lArrReturn.add(lCurrNPRevExp != 0 ? lCurrNPRevExp : "-");

			lCurrCSSRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_CSS").toString()));
			gdbCurrCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_CSS").toString())
					- lCurrCSSRevExp;
			lArrReturn.add(lCurrCSSRevExp != 0 ? lCurrCSSRevExp : "-");

			lCurrTotalRevExp = lCurrPLRevExp + lCurrNPRevExp + lCurrCSSRevExp;
			gdbCurrTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_Total").toString())
					- lCurrTotalRevExp;
			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lCurrTotalRevExp);
			lArrReturn.add(lCurrTotalRevExp != 0 ? dataStyle : "-");
//			-----------------Curr End---------------

//			-----------------Prg Start---------------
			lPrgPLRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_PL").toString()));
			gdbPrgPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_PL").toString())
					- lPrgPLRevExp;
			lArrReturn.add(lPrgPLRevExp != 0 ? lPrgPLRevExp : "-");

			lPrgNPRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_NP").toString()));
			gdbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_NP").toString())
					- lPrgNPRevExp;
			lArrReturn.add(lPrgNPRevExp != 0 ? lPrgNPRevExp : "-");

			lPrgCSSRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_CSS").toString()));
			gdbPrgCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_CSS").toString())
					- lPrgCSSRevExp;
			lArrReturn.add(lPrgCSSRevExp != 0 ? lPrgCSSRevExp : "-");

//			lPrgTotalRevExp =
//			Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()));
			lPrgTotalRevExp = lPrgPLRevExp + lPrgNPRevExp + lPrgCSSRevExp;
			gdbPrgTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_Total").toString())
					- lPrgTotalRevExp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lPrgTotalRevExp);
			lArrReturn.add(lPrgTotalRevExp != 0 ? dataStyle : "-");
//			-----------------Prg End---------------
			System.out
			.println("---------------------------------------------------------------------------------");
			glogger.info("current total rev exp = " + lCurrTotalRevExp);
			glogger.info("grant total rev exp = " + lGrantTotalRevExp);
			glogger.info("diff of days : " + ldiffDays);
			
			if (lGrantTotalRevExp != 0 & ldiffDays !=0)	
				lArrReturn.add((int) (lCurrTotalRevExp * 100 * 182)	/ (lGrantTotalRevExp * (ldiffDays+1)));
			else
				lArrReturn.add("-");

			if (lBudTotalRevExp != 0)
				lArrReturn.add((int) (lPrgTotalRevExp * 100) / lBudTotalRevExp);
			else
				lArrReturn.add("-");

			lArrReturn.add("-");

			lArrReturn.add("-");

			lArrReturn.add("-");//fund Type

			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			--------------------------------------------------------------------------------

			lArrReturn = new ArrayList();
			lArrReturn.add("3) Surplus/Deficit(1-2)");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lBudTotalRevRecp - lBudTotalRevExp));
			lArrReturn.add((lBudTotalRevRecp - lBudTotalRevExp) != 0 ? dataStyle
					: "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lCurrTotalRevRecp - lCurrTotalRevExp));
			lArrReturn.add((lCurrTotalRevRecp - lCurrTotalRevExp) != 0 ? dataStyle
					: "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lPrgTotalRevRecp - lPrgTotalRevExp));
			lArrReturn.add((lPrgTotalRevRecp - lPrgTotalRevExp) != 0 ? dataStyle
					: "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			lArrReturn.add(110001); // fundType

			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);
			glogger.info("??????????????????????????Third MileStone - 971");

//			------------------------------------------------------------------------------

			lArrReturn = new ArrayList();
			long lBudPLCapRecp = 0;
			long lBudNPCapRecp = 0;
			long lBudCSSCapRecp = 0;
			long lBudTotalCapRecp = 0;
			long lGrantPLCapRecp = 0;
			long lGrantNPCapRecp = 0;
			long lGrantCSSCapRecp = 0;
			long lGrantTotalCapRecp = 0;
			long lCurrPLCapRecp = 0;
			long lCurrNPCapRecp = 0;
			long lCurrCSSCapRecp = 0;
			long lCurrTotalCapRecp = 0;
			long lPrgPLCapRecp = 0;
			long lPrgNPCapRecp = 0;
			long lPrgCSSCapRecp = 0;
			long lPrgTotalCapRecp = 0;

			lStrFundNm = "CapRecp";
			lArrReturn.add("4) Capital Receipt");
//			-----------------Budget Start---------------
			lBudPLCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_PL").toString()));
			gdbBudPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_PL").toString())
					- lBudPLCapRecp;
			lArrReturn.add(lBudPLCapRecp != 0 ? lBudPLCapRecp : "-");

			lBudNPCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_NP").toString()));
			gdbBudNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_NP").toString())
					- lBudNPCapRecp;
			lArrReturn.add(lBudNPCapRecp != 0 ? lBudNPCapRecp : "-");

			lBudCSSCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Bud_CSS").toString()));
			gdbBudCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_CSS").toString())
					- lBudCSSCapRecp;
			lArrReturn.add(lBudCSSCapRecp != 0 ? lBudCSSCapRecp : "-");

			lBudTotalCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Bud_Total").toString()));
			gdbBudTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_Total").toString())
					- lBudTotalCapRecp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lBudTotalCapRecp);
			lArrReturn.add(lBudTotalCapRecp != 0 ? dataStyle : "-");
//			-----------------Budget End---------------

//			-----------------Grant Start---------------

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData("-");
			lArrReturn.add(dataStyle);

			glogger.info("??????????????????????????Fourth MileStone - 1028");
//			-----------------Grant End---------------

//			-----------------Curr Start---------------
			lCurrPLCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_PL").toString()));
			gdbCurrPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_PL").toString())
					- lCurrPLCapRecp;
			lArrReturn.add(lCurrPLCapRecp != 0 ? lCurrPLCapRecp : "-");

			lCurrNPCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_NP").toString()));
			gdbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_NP").toString())
					- lCurrNPCapRecp;
			lArrReturn.add(lCurrNPCapRecp != 0 ? lCurrNPCapRecp : "-");

			lCurrCSSCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_CSS").toString()));
			gdbCurrCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_CSS").toString())
					- lCurrCSSCapRecp;
			lArrReturn.add(lCurrCSSCapRecp != 0 ? lCurrCSSCapRecp : "-");

			lCurrTotalCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_Total").toString()));
			gdbCurrTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_Total").toString())
					- lCurrTotalCapRecp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lCurrTotalCapRecp);
			lArrReturn.add(lCurrTotalCapRecp != 0 ? dataStyle : "-");
//			-----------------Curr End---------------

//			-----------------Prg Start---------------
			lPrgPLCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_PL").toString()));
			gdbPrgPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_PL").toString())
					- lPrgPLCapRecp;
			lArrReturn.add(lPrgPLCapRecp != 0 ? lPrgPLCapRecp : "-");

			lPrgNPCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_NP").toString()));
			gdbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_NP").toString())
					- lPrgNPCapRecp;
			lArrReturn.add(lPrgNPCapRecp != 0 ? lPrgNPCapRecp : "-");

			lPrgCSSCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Prg_CSS").toString()));
			gdbPrgCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_CSS").toString())
					- lPrgCSSCapRecp;
			lArrReturn.add(lPrgCSSCapRecp != 0 ? lPrgCSSCapRecp : "-");

			lPrgTotalCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Prg_Total").toString()));
			gdbPrgTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_Total").toString())
					- lPrgTotalCapRecp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lPrgTotalCapRecp);
			lArrReturn.add(lPrgTotalCapRecp != 0 ? dataStyle : "-");
//			-----------------Prg End---------------
			glogger.info("??????????????????????????Fifth MileStone - 1074");

			lArrReturn.add("-");

			if (lBudTotalCapRecp != 0)
				lArrReturn.add((int) (lPrgTotalCapRecp * 100) / lBudTotalCapRecp);
			else
				lArrReturn.add("-");

			lArrReturn.add("-");

			lArrReturn.add("-");

			lArrReturn.add(Math.round(lhshConsolidatedFund.get(lStrFundNm+"_fundType")));

			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			--------------------------------------------------------------------------------

			lArrReturn = new ArrayList();
			long lBudPLCapExp = 0;
			long lBudNPCapExp = 0;
			long lBudCSSCapExp = 0;
			long lBudTotalCapExp = 0;
			long lGrantPLCapExp = 0;
			long lGrantNPCapExp = 0;
			long lGrantCSSCapExp = 0;
			long lGrantTotalCapExp = 0;
			long lCurrPLCapExp = 0;
			long lCurrNPCapExp = 0;
			long lCurrCSSCapExp = 0;
			long lCurrTotalCapExp = 0;
			long lPrgPLCapExp = 0;
			long lPrgNPCapExp = 0;
			long lPrgCSSCapExp = 0;
			long lPrgTotalCapExp = 0;

			lStrFundNm = "CapExp";
			lArrReturn.add("5) Captial Expenditure");
//			-----------------Budget Start---------------
			lBudPLCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_PL").toString()));
			gdbBudPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_PL").toString())
					- lBudPLCapExp;
			lArrReturn.add(lBudPLCapExp != 0 ? lBudPLCapExp : "-");

			lBudNPCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_NP").toString()));
			gdbBudNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_NP").toString())
					- lBudNPCapExp;
			lArrReturn.add(lBudNPCapExp != 0 ? lBudNPCapExp : "-");

			lBudCSSCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_CSS").toString()));
			gdbBudCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_CSS").toString())
					- lBudCSSCapExp;
			lArrReturn.add(lBudCSSCapExp != 0 ? lBudCSSCapExp : "-");

//			lBudTotalCapExp =
//			Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()));
			lBudTotalCapExp = lBudPLCapExp + lBudNPCapExp + lBudCSSCapExp;
			gdbBudTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Bud_Total").toString())
					- lBudTotalCapExp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lBudTotalCapExp);
			lArrReturn.add(lBudTotalCapExp != 0 ? dataStyle : "-");
//			-----------------Budget End---------------

//			-----------------Grant Start---------------
			lGrantPLCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Grant_PL").toString()));
			gdbGrantPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Grant_PL").toString())
					- lGrantPLCapExp;
			lArrReturn.add(lGrantPLCapExp != 0 ? lGrantPLCapExp : "-");

			lGrantNPCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Grant_NP").toString()));
			gdbGrantNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Grant_NP").toString())
					- lGrantNPCapExp;
			lArrReturn.add(lGrantNPCapExp != 0 ? lGrantNPCapExp : "-");

			lGrantCSSCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Grant_CSS").toString()));
			gdbGrantCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Grant_CSS").toString())
					- lGrantCSSCapExp;
			lArrReturn.add(lGrantCSSCapExp != 0 ? lGrantCSSCapExp : "-");

//			lGrantTotalCapExp =
//			Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()));
			lGrantTotalCapExp = lGrantPLCapExp + lGrantNPCapExp + lGrantCSSCapExp;
			gdbGrantTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Grant_Total").toString())
					- lGrantTotalCapExp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lGrantTotalCapExp);
			lArrReturn.add(lGrantTotalCapExp != 0 ? dataStyle : "-");
//			-----------------Grant End---------------

//			-----------------Curr Start---------------
			lCurrPLCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_PL").toString()));
			gdbCurrPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_PL").toString())
					- lCurrPLCapExp;
			lArrReturn.add(lCurrPLCapExp != 0 ? lCurrPLCapExp : "-");

			lCurrNPCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_NP").toString()));
			gdbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_NP").toString())
					- lCurrNPCapExp;
			lArrReturn.add(lCurrNPCapExp != 0 ? lCurrNPCapExp : "-");

			lCurrCSSCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund
					.get(lStrFundNm + "_Curr_CSS").toString()));
			gdbCurrCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_CSS").toString())
					- lCurrCSSCapExp;
			lArrReturn.add(lCurrCSSCapExp != 0 ? lCurrCSSCapExp : "-");

			lCurrTotalCapExp = lCurrPLCapExp + lCurrNPCapExp + lCurrCSSCapExp;
			gdbCurrTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Curr_Total").toString())
					- lCurrTotalCapExp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lCurrTotalCapExp);
			lArrReturn.add(lCurrTotalCapExp != 0 ? dataStyle : "-");
//			-----------------Curr End---------------

//			-----------------Prg Start---------------
			lPrgPLCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_PL").toString()));
			gdbPrgPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_PL").toString())
					- lPrgPLCapExp;
			lArrReturn.add(lPrgPLCapExp != 0 ? lPrgPLCapExp : "-");

			lPrgNPCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_NP").toString()));
			gdbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_NP").toString())
					- lPrgNPCapExp;
			lArrReturn.add(lPrgNPCapExp != 0 ? lPrgNPCapExp : "-");

			lPrgCSSCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_CSS").toString()));
			gdbPrgCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_CSS").toString())
					- lPrgCSSCapExp;
			lArrReturn.add(lPrgCSSCapExp != 0 ? lPrgCSSCapExp : "-");

			lPrgTotalCapExp = lPrgPLCapExp + lPrgNPCapExp + lPrgCSSCapExp;
			gdbPrgTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(
					lStrFundNm + "_Prg_Total").toString())
					- lPrgTotalCapExp;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lPrgTotalCapExp);
			lArrReturn.add(lPrgTotalCapExp != 0 ? dataStyle : "-");
//			-----------------Prg End---------------

			if (lGrantTotalCapExp > 0 && ldiffDays > 0)
				lArrReturn.add((int) (lCurrTotalCapExp * 100 * 182) / (lGrantTotalCapExp * (ldiffDays+1)));
			else
				lArrReturn.add("-");

			if (lBudTotalCapExp != 0)
				lArrReturn.add((int) (lPrgTotalCapExp * 100) / lBudTotalCapExp);
			else
				lArrReturn.add("-");

			lArrReturn.add("-");

			lArrReturn.add("-");

			lArrReturn.add(Math.round(lhshConsolidatedFund.get(lStrFundNm+"_fundType")));

			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			--------------------------------------------------------------------------------

			lArrReturn = new ArrayList();
			lArrReturn.add("6) Surplus/Deficit(4-5)");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lBudTotalCapRecp - lBudTotalCapExp));
			lArrReturn.add((lBudTotalCapRecp - lBudTotalCapExp) != 0 ? dataStyle
					: "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lCurrTotalCapRecp - lCurrTotalCapExp));
			lArrReturn.add((lCurrTotalCapRecp - lCurrTotalCapExp) != 0 ? dataStyle
					: "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lPrgTotalCapRecp - lPrgTotalCapExp));
			lArrReturn.add((lPrgTotalCapRecp - lPrgTotalCapExp) != 0 ? dataStyle
					: "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			lArrReturn.add(110002);//fundType

			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			-------------------------------------------------------------------------------
			glogger.info("??????????????????????????Sixth MileStone - 1268");

			lArrReturn = new ArrayList();
			lArrReturn.add("7) Consolidated Fund Receipts (1+4)");
			lArrReturn
			.add((lBudPLRevRecp + lBudPLCapRecp) != 0 ? (lBudPLRevRecp + lBudPLCapRecp)
					: "-");
			lArrReturn
			.add((lBudNPRevRecp + lBudNPCapRecp) != 0 ? (lBudNPRevRecp + lBudNPCapRecp)
					: "-");
			lArrReturn
			.add((lBudCSSRevRecp + lBudCSSCapRecp) != 0 ? (lBudCSSRevRecp + lBudCSSCapRecp)
					: "-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lBudTotalRevRecp + lBudTotalCapRecp));
			lArrReturn.add((lBudTotalRevRecp + lBudTotalCapRecp) != 0 ? dataStyle
					: "-");

			lArrReturn
			.add((lGrantPLRevRecp + lGrantPLCapRecp) != 0 ? (lGrantPLRevRecp + lGrantPLCapRecp)
					: "-");
			lArrReturn
			.add((lGrantNPRevRecp + lGrantNPCapRecp) != 0 ? (lGrantNPRevRecp + lGrantNPCapRecp)
					: "-");
			lArrReturn
			.add((lGrantCSSRevRecp + lGrantCSSCapRecp) != 0 ? (lGrantCSSRevRecp + lGrantCSSCapRecp)
					: "-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lGrantTotalRevRecp + lGrantTotalCapRecp));
			lArrReturn
			.add((lGrantTotalRevRecp + lGrantTotalCapRecp) != 0 ? dataStyle
					: "-");

			lArrReturn
			.add((lCurrPLRevRecp + lCurrPLCapRecp) != 0 ? (lCurrPLRevRecp + lCurrPLCapRecp)
					: "-");
			lArrReturn
			.add((lCurrNPRevRecp + lCurrNPCapRecp) != 0 ? (lCurrNPRevRecp + lCurrNPCapRecp)
					: "-");
			lArrReturn
			.add((lCurrCSSRevRecp + lCurrCSSCapRecp) != 0 ? (lCurrCSSRevRecp + lCurrCSSCapRecp)
					: "-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lCurrTotalRevRecp + lCurrTotalCapRecp));
			lArrReturn.add((lCurrTotalRevRecp + lCurrTotalCapRecp) != 0 ? dataStyle
					: "-");

			lArrReturn
			.add((lPrgPLRevRecp + lPrgPLCapRecp) != 0 ? (lPrgPLRevRecp + lPrgPLCapRecp)
					: "-");
			lArrReturn
			.add((lPrgNPRevRecp + lPrgNPCapRecp) != 0 ? (lPrgNPRevRecp + lPrgNPCapRecp)
					: "-");
			lArrReturn
			.add((lPrgCSSRevRecp + lPrgCSSCapRecp) != 0 ? (lPrgCSSRevRecp + lPrgCSSCapRecp)
					: "-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lPrgTotalRevRecp + lPrgTotalCapRecp));
			lArrReturn.add((lPrgTotalRevRecp + lPrgTotalCapRecp) != 0 ? dataStyle
					: "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add(110003);

			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			-------------------------------------------------------------------------------

			lArrReturn = new ArrayList();
			lArrReturn.add("8) Consolidated Fund Expenditure (2+5)");
			lArrReturn
			.add((lBudPLRevExp + lBudPLCapExp) != 0 ? (lBudPLRevExp + lBudPLCapExp)
					: "-");
			lArrReturn
			.add((lBudNPRevExp + lBudNPCapExp) != 0 ? (lBudNPRevExp + lBudNPCapExp)
					: "-");
			lArrReturn
			.add((lBudCSSRevExp + lBudCSSCapExp) != 0 ? (lBudCSSRevExp + lBudCSSCapExp)
					: "-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lBudTotalRevExp + lBudTotalCapExp));
			lArrReturn.add((lBudTotalRevExp + lBudTotalCapExp) != 0 ? dataStyle
					: "-");

			lArrReturn
			.add((lGrantPLRevExp + lGrantPLCapExp) != 0 ? (lGrantPLRevExp + lGrantPLCapExp)
					: "-");
			lArrReturn
			.add((lGrantNPRevExp + lGrantNPCapExp) != 0 ? (lGrantNPRevExp + lGrantNPCapExp)
					: "-");
			lArrReturn
			.add((lGrantCSSRevExp + lGrantCSSCapExp) != 0 ? (lGrantCSSRevExp + lGrantCSSCapExp)
					: "-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lGrantTotalRevExp + lGrantTotalCapExp));
			lArrReturn.add((lGrantTotalRevExp + lGrantTotalCapExp) != 0 ? dataStyle
					: "-");

			lArrReturn
			.add((lCurrPLRevExp + lCurrPLCapExp) != 0 ? (lCurrPLRevExp + lCurrPLCapExp)
					: "-");
			lArrReturn
			.add((lCurrNPRevExp + lCurrNPCapExp) != 0 ? (lCurrNPRevExp + lCurrNPCapExp)
					: "-");
			lArrReturn
			.add((lCurrCSSRevExp + lCurrCSSCapExp) != 0 ? (lCurrCSSRevExp + lCurrCSSCapExp)
					: "-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lCurrTotalRevExp + lCurrTotalCapExp));
			lArrReturn.add((lCurrTotalRevExp + lCurrTotalCapExp) != 0 ? dataStyle
					: "-");

			lArrReturn
			.add((lPrgPLRevExp + lPrgPLCapExp) != 0 ? (lPrgPLRevExp + lPrgPLCapExp)
					: "-");
			lArrReturn
			.add((lPrgNPRevExp + lPrgNPCapExp) != 0 ? (lPrgNPRevExp + lPrgNPCapExp)
					: "-");
			lArrReturn
			.add((lPrgCSSRevExp + lPrgCSSCapExp) != 0 ? (lPrgCSSRevExp + lPrgCSSCapExp)
					: "-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData((lPrgTotalRevExp + lPrgTotalCapExp));
			lArrReturn.add((lPrgTotalRevExp + lPrgTotalCapExp) != 0 ? dataStyle
					: "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add(110004); // fundType

			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			-------------------------------------------------------------------------------
			System.out
			.println("??????????????????????????Seventh MileStone - 1361");
			lArrReturn = new ArrayList();
			long lBudPL = 0;
			long lBudNP = 0;
			long lBudCSS = 0;
			long lBudTotal = 0;
			long lGrantPL = 0;
			long lGrantNP = 0;
			long lGrantCSS = 0;
			long lGrantTotal = 0;
			long lCurrPL = 0;
			long lCurrNP = 0;
			long lCurrCSS = 0;
			long lCurrTotal = 0;
			long lPrgPL = 0;
			long lPrgNP = 0;
			long lPrgCSS = 0;
			long lPrgTotal = 0;

			lArrReturn.add("9) Net Consolidated Fund (7-8)");

			lBudPL = (lBudPLRevRecp + lBudPLCapRecp)
			- (lBudPLRevExp + lBudPLCapExp);
			lBudNP = (lBudNPRevRecp + lBudNPCapRecp)
			- (lBudNPRevExp + lBudNPCapExp);
			lBudCSS = (lBudCSSRevRecp + lBudCSSCapRecp)
			- (lBudCSSRevExp + lBudCSSCapExp);
			lBudTotal = (lBudTotalRevRecp + lBudTotalCapRecp)
			- (lBudTotalRevExp + lBudTotalCapExp);
			lGrantPL = (lGrantPLRevRecp + lGrantPLCapRecp)
			- (lGrantPLRevExp + lGrantPLCapExp);
			lGrantNP = (lGrantNPRevRecp + lGrantNPCapRecp)
			- (lGrantNPRevExp + lGrantNPCapExp);
			lGrantCSS = (lGrantCSSRevRecp + lGrantCSSCapRecp)
			- (lGrantCSSRevExp + lGrantCSSCapExp);
			lGrantTotal = (lGrantTotalRevRecp + lGrantTotalCapRecp)
			- (lGrantTotalRevExp + lGrantTotalCapRecp);
			lCurrPL = (lCurrPLRevRecp + lCurrPLCapRecp)
			- (lCurrPLRevExp + lCurrPLCapExp);
			lCurrNP = (lCurrNPRevRecp + lCurrNPCapRecp)
			- (lCurrNPRevExp + lCurrNPCapExp);
			lCurrCSS = (lCurrCSSRevRecp + lCurrCSSCapRecp)
			- (lCurrCSSRevExp + lCurrCSSCapExp);
			lCurrTotal = (lCurrTotalRevRecp + lCurrTotalCapRecp)
			- (lCurrTotalRevExp + lCurrTotalCapExp);
			lPrgPL = (lPrgPLRevRecp + lPrgPLCapRecp)
			- (lPrgPLRevExp + lPrgPLCapExp);
			lPrgNP = (lPrgNPRevRecp + lPrgNPCapRecp)
			- (lPrgNPRevExp + lPrgNPCapExp);
			lPrgCSS = (lPrgCSSRevRecp + lPrgCSSCapRecp)
			- (lPrgCSSRevExp + lPrgCSSCapExp);
			lPrgTotal = (lPrgTotalRevRecp + lPrgTotalCapRecp)
			- (lPrgTotalRevExp + lPrgTotalCapExp);

			glogger.info("Values are generated---------- after seventh");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lBudTotal);
			lArrReturn.add(lBudTotal != 0 ? dataStyle : "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lCurrTotal);
			lArrReturn.add(lCurrTotal != 0 ? dataStyle : "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lPrgTotal);
			lArrReturn.add(lPrgTotal != 0 ? dataStyle : "-");

			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");
			lArrReturn.add("-");

			lArrReturn.add(110005);//fund Type

			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			lArrMainDataLst.add(lArrReturn);

//			-------------------------------------------------------------------------------

			lArrReturn = new ArrayList();
			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData("II-Contingency Fund");
			lArrReturn.add(dataStyle);
			for (int i = 0; i < 20; i++)
				lArrReturn.add("");
			lArrReturn.add(100012);
			lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			lArrReturn.add(lObjReport.getParameterValue("Dateto"));
//			lArrMainDataLst.add(lArrReturn);

//			-------------------------------------------------------------------------------

			lArrReturn = new ArrayList();
			long lBudPLConFund = 0;
			long lBudNPConFund = 0;
			long lBudCSSConFund = 0;
			long lBudTotalConFund = 0;
			long lGrantPLConFund = 0;
			long lGrantNPConFund = 0;
			long lGrantCSSConFund = 0;
			long lGrantTotalConFund = 0;
			long lCurrPLConFund = 0;
			long lCurrNPConFund = 0;
			long lCurrCSSConFund = 0;
			long lCurrTotalConFund = 0;
			long lPrgPLConFund = 0;
			long lPrgNPConFund = 0;
			long lPrgCSSConFund = 0;
			long lPrgTotalConFund = 0;

			lStrFundNm = "ConFund";
			lArrReturn.add("10) Contingency(NET)");

			glogger.info("10) Contingency(NET)");
//			-----------------Budget Start---------------
			lBudPLConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(
			"ConFund_Bud_PL").toString()));
			glogger.info("Fist value is fetched of Contingency fund");
			gdbBudPLFracPart += Double.parseDouble(lhshContingencyFund.get(
					lStrFundNm + "_Bud_PL").toString())
					- lBudPLConFund;
			System.out
			.println("Fist value is fetched of Contingency fund++++++++++++++++++++????????>>>>>>>>>");
			lArrReturn.add(lBudPLConFund != 0 ? lBudPLConFund : "-");

			lBudNPConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(
					lStrFundNm + "_Bud_NP").toString()));
			gdbBudNPFracPart += Double.parseDouble(lhshContingencyFund.get(
					lStrFundNm + "_Bud_NP").toString())
					- lBudNPConFund;
			lArrReturn.add(lBudNPConFund != 0 ? lBudNPConFund : "-");

			lBudCSSConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(
					lStrFundNm + "_Bud_CSS").toString()));
			gdbBudCSSFracPart += Double.parseDouble(lhshContingencyFund.get(
					lStrFundNm + "_Bud_CSS").toString())
					- lBudCSSConFund;
			lArrReturn.add(lBudCSSConFund != 0 ? lBudCSSConFund : "-");

			lBudTotalConFund = Math.round(Double.parseDouble(lhshContingencyFund
					.get(lStrFundNm + "_Bud_Total").toString()));
			gdbBudTotalFracPart += Double.parseDouble(lhshContingencyFund.get(
					lStrFundNm + "_Bud_Total").toString())
					- lBudTotalConFund;

			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(lBudTotalConFund);
			lArrReturn.add(lBudTotalConFund != 0 ? dataStyle : "-");
//			-----------------Budget End---------------

//			-----------------Grant Start---------------

			/* lGrantPLConFund =
			 * Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_PL").toString()));
			 * gdbGrantPLFracPart +=
			 * Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_PL").toString()) -
	  lGrantPLConFund;*/

			lArrReturn.add(lGrantPLConFund != 0 ? lGrantPLConFund : "-");

			/* lGrantNPConFund =
			 * Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_NP").toString()));
			 * gdbGrantNPFracPart +=
			 * Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_NP").toString()) -
	  lGrantNPConFund;*/

			lArrReturn.add(lGrantNPConFund != 0 ? lGrantNPConFund : "-");

			/* lGrantCSSConFund =
			 * Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_CSS").toString()));
			 * gdbGrantCSSFracPart +=
			 * Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_CSS").toString()) -
	  lGrantCSSConFund;*/

			lArrReturn.add(lGrantCSSConFund != 0 ? lGrantCSSConFund : "-");

			/* lGrantTotalConFund =
			 * Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_Total").toString()));
			 * gdbGrantTotalFracPart +=
			 * Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_Total").toString()) -
			 * lGrantTotalConFund;
			 * 
			 */ glogger.info("GrantValues are agenerated");

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lGrantTotalConFund);
			 lArrReturn.add(lGrantTotalConFund != 0 ? dataStyle : "-");
//			 -----------------Grant End---------------

//			 -----------------Curr Start---------------

			 glogger.info("Current Value are generated");
			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lCurrTotalConFund);
			 lArrReturn.add(lCurrTotalConFund != 0 ? 111 : 111);
			 lArrReturn.add(lCurrTotalConFund != 0 ? 222 : 222);
			 lArrReturn.add(lCurrTotalConFund != 0 ? 333 : 333);
			 lArrReturn.add(lCurrTotalConFund != 0 ? dataStyle : "-");
//			 -----------------Curr End---------------

//			 -----------------Prg Start---------------

			 /* lPrgPLConFund =
			  * Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_PL").toString()));
			  * gdbPrgPLFracPart +=
			  * Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_PL").toString()) -
			  * lPrgPLConFund;*/

			 lArrReturn.add(lPrgPLConFund != 0 ? lPrgPLConFund : "-");

			 /* lPrgNPConFund =
			  * Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_NP").toString()));
			  * gdbCurrNPFracPart +=
			  * Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_NP").toString()) -
			  * lPrgNPConFund;*/

			 lArrReturn.add(lPrgNPConFund != 0 ? lPrgNPConFund : "-");

			 /* lPrgCSSConFund =
			  * Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_CSS").toString()));
			  * gdbPrgCSSFracPart +=
			  * Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_CSS").toString()) -
	  lPrgCSSConFund;*/

			 lArrReturn.add(lPrgCSSConFund != 0 ? lPrgCSSConFund : "-");

			 /* lPrgTotalConFund =
			  * Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_Total").toString()));
			  * gdbPrgTotalFracPart +=
			  * Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_Total").toString()) -
	  lPrgTotalConFund;*/

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lPrgTotalConFund);
			 lArrReturn.add(lPrgTotalConFund != 0 ? dataStyle : "-");

			 glogger.info("Progressive valuesare generated");
//			 -----------------Prg End---------------

			 if (lGrantTotalConFund != 0)
				 lArrReturn.add((int) (lCurrTotalConFund * 100 * 182)
						 / (lGrantTotalConFund * (ldiffDays+1)));
			 else
				 lArrReturn.add("-");

			 if (lBudTotalConFund != 0)
				 lArrReturn.add((int) (lPrgTotalConFund * 100) / lBudTotalConFund);
			 else
				 lArrReturn.add("-");

			 lArrReturn.add("-");

			 lArrReturn.add("-");

			 lArrReturn.add(100012);

			 lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			 lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			 lArrMainDataLst.add(lArrReturn);

//			 --------------------------------------------------------------------------------

			 glogger.info("After adding Contingency Fund");
			 lArrReturn = new ArrayList();
			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("III-Public Account</b>");
			 lArrReturn.add(dataStyle);
			 for (int i = 0; i < 20; i++)
				 lArrReturn.add("");
			 lArrReturn.add(100013);
			 lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			 lArrReturn.add(lObjReport.getParameterValue("Dateto"));
//			 lArrMainDataLst.add(lArrReturn);

//			 ----------------------------------------------------------------------------

			 lArrReturn = new ArrayList();
			 long lBudPLPubFund = 0;
			 long lBudNPPubFund = 0;
			 long lBudCSSPubFund = 0;
			 long lBudTotalPubFund = 0;
			 long lGrantPLPubFund = 0;
			 long lGrantNPPubFund = 0;
			 long lGrantCSSPubFund = 0;
			 long lGrantTotalPubFund = 0;
			 long lCurrPLPubFund = 0;
			 long lCurrNPPubFund = 0;
			 long lCurrCSSPubFund = 0;
			 long lCurrTotalPubFund = 0;
			 long lPrgPLPubFund = 0;
			 long lPrgNPPubFund = 0;
			 long lPrgCSSPubFund = 0;
			 long lPrgTotalPubFund = 0;

			 lStrFundNm = "PubFund";
			 lArrReturn.add("11) Public Account(NET)");
			 glogger.info("11) Public Account(NET)");
//			 -----------------Budget Start---------------
			 lBudPLPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Bud_PL").toString()));
			 gdbBudPLFracPart += Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Bud_PL").toString())
					 - lBudPLPubFund;
			 lArrReturn.add(lBudPLPubFund != 0 ? lBudPLPubFund : "-");

			 lBudNPPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Bud_NP").toString()));
			 gdbBudNPFracPart += Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Bud_NP").toString())
					 - lBudNPPubFund;
			 lArrReturn.add(lBudNPPubFund != 0 ? lBudNPPubFund : "-");

			 lBudCSSPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Bud_CSS").toString()));
			 gdbBudCSSFracPart += Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Bud_CSS").toString())
					 - lBudCSSPubFund;
			 lArrReturn.add(lBudCSSPubFund != 0 ? lBudCSSPubFund : "-");

			 lBudTotalPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Bud_Total").toString()));
			 gdbBudTotalFracPart += Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Bud_Total").toString())
					 - lBudTotalPubFund;

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lBudTotalPubFund);
			 lArrReturn.add(lBudTotalPubFund != 0 ? dataStyle : "-");
//			 -----------------Budget End---------------

//			 -----------------Grant Start---------------
			 lGrantPLPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Grant_PL").toString()));
			 gdbGrantPLFracPart += Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Grant_PL").toString())
					 - lGrantPLPubFund;
			 lArrReturn.add(lGrantPLPubFund != 0 ? lGrantPLPubFund : "-");

			 lGrantNPPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Grant_NP").toString()));
			 gdbGrantNPFracPart += Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Grant_NP").toString())
					 - lGrantNPPubFund;
			 lArrReturn.add(lGrantNPPubFund != 0 ? lGrantNPPubFund : "-");

			 lGrantCSSPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Grant_CSS").toString()));
			 gdbGrantCSSFracPart += Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Grant_CSS").toString())
					 - lGrantCSSPubFund;
			 lArrReturn.add(lGrantCSSPubFund != 0 ? lGrantCSSPubFund : "-");

			 lGrantTotalPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Grant_Total").toString()));
			 gdbGrantTotalFracPart += Double.parseDouble(lhshPublicFund.get(
					 lStrFundNm + "_Grant_Total").toString())
					 - lGrantTotalPubFund;

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lGrantTotalPubFund);
			 lArrReturn.add(lGrantTotalPubFund != 0 ? dataStyle : "-");
//			 -----------------Grant End---------------
			 glogger.info("??????????????????????????Eight MileStone - 1028");

//			 -----------------Curr Start---------------

			 /* lCurrPLPubFund =
			  * Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_PL").toString()));
			  * gdbCurrPLFracPart +=
			  * Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_PL").toString()) -
			  * lCurrPLPubFund;*/

			 lArrReturn.add(lCurrPLPubFund != 0 ? lCurrPLPubFund : "-");

			 /* lCurrNPPubFund =
			  * Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_NP").toString()));
			  * gdbCurrNPFracPart +=
			  * Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_NP").toString()) -
			  * lCurrNPPubFund;*/

			 lArrReturn.add(lCurrNPPubFund != 0 ? lCurrNPPubFund : "-");

			 /* lCurrCSSPubFund =
			  * Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_CSS").toString()));
			  * gdbCurrCSSFracPart +=
			  * Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_CSS").toString()) -
	  lCurrCSSPubFund;*/

			 lArrReturn.add(lCurrCSSPubFund != 0 ? lCurrCSSPubFund : "-");

			 /* lCurrTotalPubFund =
			  * Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_Total").toString()));
			  * gdbCurrTotalFracPart +=
			  * Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_Total").toString()) -
			  * lCurrTotalPubFund;*/

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lCurrTotalPubFund);
			 lArrReturn.add(lCurrTotalPubFund != 0 ? dataStyle : "-");
//			 -----------------Curr End---------------

//			 -----------------Prg Start--------------- lPrgPLPubFund =

			 /* Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_PL").toString()));
			  * gdbPrgPLFracPart +=
			  * Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_PL").toString()) -
			  * lPrgPLPubFund;*/

			 lArrReturn.add(lPrgPLPubFund != 0 ? lPrgPLPubFund : "-");

			 /* lPrgNPPubFund =
			  * Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_NP").toString()));
			  * gdbCurrNPFracPart +=
			  * Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_NP").toString()) -
			  * lPrgNPPubFund;*/
			 lArrReturn.add(lPrgNPPubFund != 0 ? lPrgNPPubFund : "-");

			 /* lPrgCSSPubFund =
			  * Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_CSS").toString()));
			  * gdbPrgCSSFracPart +=
			  * Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_CSS").toString()) -
			  * lPrgCSSPubFund;*/
			 lArrReturn.add(lPrgCSSPubFund != 0 ? lPrgCSSPubFund : "-");

			 /* lPrgTotalPubFund =
			  * Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_Total").toString()));
			  * gdbPrgTotalFracPart +=
			  * Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_Total").toString()) -
			  * lPrgTotalPubFund;
			  */

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lPrgTotalPubFund);
			 lArrReturn.add(lPrgTotalPubFund != 0 ? dataStyle : "-");
//			 -----------------Prg End---------------

			 if (lGrantTotalPubFund != 0)
				 lArrReturn.add((int) (lCurrTotalPubFund * 100 * 182)
						 / (lGrantTotalPubFund * (ldiffDays+1)));
			 else
				 lArrReturn.add("-");

			 if (lBudTotalPubFund != 0)
				 lArrReturn.add((int) (lPrgTotalPubFund * 100) / lBudTotalPubFund);
			 else
				 lArrReturn.add("-");

			 lArrReturn.add("-");

			 lArrReturn.add("-");

			 lArrReturn.add(100013);

			 lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			 lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			 lArrMainDataLst.add(lArrReturn);

//			 --------------------------------------------------------------------------------

			 lArrReturn = new ArrayList();

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("Total Transactions(I+II+III)");
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lBudPL + lBudPLConFund + lBudPLPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lBudNP + lBudNPConFund + lBudNPPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lBudCSS + lBudCSSConFund + lBudCSSPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lBudTotal + lBudTotalConFund + lBudTotalPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lGrantPL + lGrantPLConFund + lGrantPLPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lGrantNP + lGrantNPConFund + lGrantNPPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lGrantCSS + lGrantCSSConFund + lGrantCSSPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle
			 .setData((lGrantTotal + lGrantTotalConFund + lGrantTotalPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lCurrPL + lCurrPLConFund + lCurrPLPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lCurrNP + lCurrNPConFund + lCurrNPPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lCurrCSS + lCurrCSSConFund + lCurrCSSPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lCurrTotal + lCurrTotalConFund + lCurrTotalPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lPrgPL + lPrgPLConFund + lPrgPLPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lPrgNP + lPrgNPConFund + lPrgNPPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lPrgCSS + lPrgCSSConFund + lPrgCSSPubFund));
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData((lPrgTotal + lPrgTotalConFund + lPrgTotalPubFund));
			 lArrReturn.add(dataStyle);

			 if ((lGrantTotal + lGrantTotalConFund + lGrantTotalPubFund) != 0) {
				 dataStyle = new StyledData();
				 dataStyle.setStyles(reportStyleVO);
				 if(ldiffDays != 0)
				 {
					 dataStyle .setData(((int) ((lCurrTotal + lCurrTotalConFund + lCurrTotalPubFund) * 100 * 182) / ((lGrantTotal + lGrantTotalConFund + lGrantTotalPubFund) * (ldiffDays+1))));
				 }
				 else
					 dataStyle .setData(((int) ((lCurrTotal + lCurrTotalConFund + lCurrTotalPubFund) * 100 * 182) / ((lGrantTotal + lGrantTotalConFund + lGrantTotalPubFund) )));
					 
				 lArrReturn.add(dataStyle);
			 } else {
				 dataStyle = new StyledData();
				 dataStyle.setStyles(reportStyleVO);
				 dataStyle.setData("-");
				 lArrReturn.add(dataStyle);
			 }
			 if ((lBudTotal + lBudTotalConFund + lBudTotalPubFund) != 0) {
				 dataStyle = new StyledData();
				 dataStyle.setStyles(reportStyleVO);
				 dataStyle
				 .setData(((int) ((lPrgTotal + lPrgTotalConFund + lPrgTotalPubFund) * 100) / (lBudTotal
						 + lBudTotalConFund + lBudTotalPubFund)));
				 lArrReturn.add(dataStyle);
			 } else {
				 dataStyle = new StyledData();
				 dataStyle.setStyles(reportStyleVO);
				 dataStyle.setData("-");
				 lArrReturn.add(dataStyle);
			 }
			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("-");
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("-");
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("-");
			 lArrReturn.add(dataStyle);

			 lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			 lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			 lArrMainDataLst.add(lArrReturn);

//			 -----------------------------------------------------------------------------
			 glogger.info("??????????????????????????Ninth MileStone - 1028");

			 lArrReturn = new ArrayList();
			 lArrReturn.add("Rounded Difference");
			 lArrReturn.add(gdbBudPLFracPart != 0 ? lnfLong.format(gdbBudPLFracPart)
					 : "-");
			 lArrReturn.add(gdbBudNPFracPart != 0 ? lnfLong.format(gdbBudNPFracPart)
					 : "-");
			 lArrReturn.add(gdbBudCSSFracPart != 0 ? lnfLong
					 .format(gdbBudCSSFracPart) : "-");

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lnfLong.format(gdbBudTotalFracPart));
			 lArrReturn.add(gdbBudTotalFracPart != 0 ? dataStyle : "-");

			 lArrReturn.add(gdbGrantPLFracPart != 0 ? lnfLong
					 .format(gdbGrantPLFracPart) : "-");
			 lArrReturn.add(gdbGrantNPFracPart != 0 ? lnfLong
					 .format(gdbGrantNPFracPart) : "-");
			 lArrReturn.add(gdbGrantCSSFracPart != 0 ? lnfLong
					 .format(gdbGrantCSSFracPart) : "-");

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lnfLong.format(gdbGrantTotalFracPart));
			 lArrReturn.add(gdbGrantTotalFracPart != 0 ? dataStyle : "-");

			 lArrReturn.add(gdbCurrPLFracPart != 0 ? lnfLong
					 .format(gdbCurrPLFracPart) : "-");
			 lArrReturn.add(gdbCurrNPFracPart != 0 ? lnfLong
					 .format(gdbCurrNPFracPart) : "-");
			 lArrReturn.add(gdbCurrCSSFracPart != 0 ? lnfLong
					 .format(gdbCurrCSSFracPart) : "-");

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lnfLong.format(gdbCurrTotalFracPart));
			 lArrReturn.add(gdbCurrTotalFracPart != 0 ? dataStyle : "-");

			 lArrReturn.add(gdbPrgPLFracPart != 0 ? lnfLong.format(gdbPrgPLFracPart)
					 : "-");
			 lArrReturn.add(gdbPrgNPFracPart != 0 ? lnfLong.format(gdbPrgNPFracPart)
					 : "-");
			 lArrReturn.add(gdbPrgCSSFracPart != 0 ? lnfLong
					 .format(gdbPrgCSSFracPart) : "-");

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData(lnfLong.format(gdbPrgTotalFracPart));
			 lArrReturn.add(gdbPrgTotalFracPart != 0 ? dataStyle : "-");

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("-");
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("-");
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("-");
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("-");
			 lArrReturn.add(dataStyle);

			 dataStyle = new StyledData();
			 dataStyle.setStyles(reportStyleVO);
			 dataStyle.setData("-");
			 lArrReturn.add(dataStyle);
			 lArrReturn.add(lObjReport.getParameterValue("Datefrom"));
			 lArrReturn.add(lObjReport.getParameterValue("Dateto"));

//			 lArrMainDataLst.add(lArrReturn);

//			 -----------------------------------------------------------------------------
			 glogger.info("??????????????????????????TenthMileStone - 1028"); 

			glogger.info("------------------------Printing of the Arraylist that is going to be passed-------------------");
	 		
	 		
	 		
	 		for (int loop = 0 ; loop < lArrMainDataLst.size() ; loop++)
	 		{
	 			ArrayList innerarr = (ArrayList)lArrMainDataLst.get(loop);
	 			glogger.info(loop +">>>> no of arraylist starts");
	 			
	 			for(int innerloop = 0; innerloop < innerarr.size() ; innerloop++)
	 			{
	 				glogger.info(innerloop + ":-" + innerarr.get(innerloop) );
	 			}
	 		}
	 		
	 		
	 		glogger.info("------------------------ ------------------------------------------------------------------------");
	 		
	 		
			glogger.info("------------------------Printing of the Arraylist that is going to be passed-------------------");
			 
			 
			 
			 return lArrMainDataLst;
		}



		private ArrayList setRow(HashMap lhashMap , int fundType , boolean linkDisplay , long ldiff , long longGrantPeriod )
		{

			ArrayList lArrReturn = null;
			String lStrFundNm = null;
			DSSQueryDAO gObjRptQueryDAO = null;
			long lPercntgBudgt = 0;
			long lPercntgGrant = 0;
			
			



			gObjRptQueryDAO = (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");


//			--------------------------------------------------------------------------------------------------------------------------------------------------------
//			If First Row (I-Consolidated Fund) , Eleventh Row (II-Contigency Fund) , Thirteenth Row (III-Public Account)
//			---------------------------------------------------------------------------------------------------------------------------------------------------------
			if(fundType == Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFund")) || fundType == Integer.parseInt(gObjRsrcBndle.getString("ContigencyFund"))|| fundType == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount")))
			{

				lArrReturn = new ArrayList();

				/* First Row addition*/
				lArrReturn.add(" <b>"+ gObjRptQueryDAO.getFundName(fundType) +"</b>");

				/* Twenty rows Addition*/
				for (int i = 0; i < 20; i++)
					lArrReturn.add("");

				/* Three Rows Addition */
				if(linkDisplay)
					lArrReturn.add(fundType);
				else
					lArrReturn.add("");



				lArrReturn.add(gStrFromDate);
				lArrReturn.add(gStrToDate);
			}

//			---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//			For Rows - 1) Revenue Receipt , 2)RevenueExpenditure , 4) CapitalReceipt ,
//			5) CapitalExpenditure ,  7) ConsolidatedFundReceipt , 
//			8)ConsolidatedFundExpenditure , 10) Contigency(NET) , 11) Public Account(NET)
//			---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			else if (fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundReceipt"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundExpenditure"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
			{


				if(fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")))
					lStrFundNm = "RevRecp";
				else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")))
					lStrFundNm = "RevExp";
				else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt")))
					lStrFundNm = "CapRecp";
				else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")))
					lStrFundNm = "CapExp";
				else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundReceipt")))
					lStrFundNm = "ConFundRecp";
				else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundExpenditure")))
					lStrFundNm = "ConFundExp";
				else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
					lStrFundNm = "ConFund";
				else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
					lStrFundNm = "PubFund";



				lArrReturn = new ArrayList();
				lArrReturn.add(gObjRptQueryDAO.getFundName(fundType));


				if(fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))	
				{
					if(fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")))
					{
						if(fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")))
						{
							gBudPLRevRecp =gBudPLRevRecp - Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()));
							gBudNPRevRecp =gBudNPRevRecp -  Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()));
							gBudCSSRevRecp =gBudCSSRevRecp - Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()));
							gBudTotalRevRecp =gBudTotalRevRecp -  Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()));
							gGrantNPRevRecp =gGrantNPRevRecp - Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()));
							gGrantPLRevRecp =gGrantPLRevRecp -  Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()));
							gGrantCSSRevRecp =gGrantCSSRevRecp - Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()));
							gGrantTotalRevRecp = gGrantTotalRevRecp - ( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString())));
							gCurrPLRevRecp =gCurrPLRevRecp -  Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()));
							gCurrNPRevRecp =gCurrNPRevRecp - ( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString())));
							gCurrCSSRevRecp = gCurrCSSRevRecp - Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()));
							gCurrTotalRevRecp =gCurrTotalRevRecp -  Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()));
							gPrgPLRevRecp =gPrgPLRevRecp -  Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()));
							gPrgNPRevRecp = gPrgNPRevRecp - Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()));
							gPrgCSSRevRecp =gPrgCSSRevRecp -  Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()));
							gPrgTotalRevRecp =gPrgTotalRevRecp -  Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()));
							
						}
						else
						{
							gBudNPRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()));
							gBudPLRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()));
							gBudCSSRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()));
							gBudTotalRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()));
							gGrantPLRevRecp =Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()));
							gGrantNPRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()));
							gGrantCSSRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()));
							gGrantTotalRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()));
							gCurrPLRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()));
							gCurrNPRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()));
							gCurrCSSRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()));
							gCurrTotalRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()));
							gPrgPLRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()));
							gPrgNPRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()));
							gPrgCSSRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()));
							gPrgTotalRevRecp = Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()));
						}
					}


					gdbBudPLFracPart = gdbBudPLFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))));
					gdbBudNPFracPart = gdbBudNPFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))));
					gdbBudCSSFracPart = gdbBudCSSFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))));
					gdbBudTotalFracPart = gdbBudTotalFracPart+ (Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()))));	

					gdbGrantPLFracPart =gdbGrantPLFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()))));
					gdbGrantNPFracPart =gdbGrantNPFracPart+ (Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))));
					gdbGrantCSSFracPart =gdbGrantCSSFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))));
					gdbGrantTotalFracPart =gdbGrantTotalFracPart+ (Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))));

					gdbCurrPLFracPart =gdbCurrPLFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()) - Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString())));
					gdbCurrNPFracPart =gdbCurrNPFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))));
					gdbCurrCSSFracPart =gdbCurrCSSFracPart+ (Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))));
					gdbCurrTotalFracPart =gdbCurrTotalFracPart+ (Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString())	- (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));

					gdbPrgPLFracPart = gdbPrgPLFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString())- (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))));
					gdbPrgNPFracPart =gdbPrgNPFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString())- (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))));
					gdbPrgCSSFracPart =gdbPrgCSSFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString())	- ( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))));
					gdbPrgTotalFracPart =gdbPrgTotalFracPart + (Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()) - (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));

				}



				if(fundType == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
				{

					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) != 0 ? gdbBudPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) != 0 ? gdbBudNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) != 0 ? gdbBudCSSTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) : "-");

					glogger.info("Budget values has been set");
					StyledData dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					gdbBudTotalTotal += Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()));
					dataStyle.setData(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())));
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()))) != 0 ? dataStyle : "-");

					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString())))!= 0 ? gdbGrantPLTotal += (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) != 0 ? gdbGrantNPTotal+= (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) != 0 ? gdbGrantCSSTotal+= (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) : "-");
					glogger.info("grant values has been set");
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					gdbGrantTotalTotal +=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString())));
					
					dataStyle.setData(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString())));
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))) != 0 ? dataStyle : "-");
					glogger.info("Grant Total has been set");

					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) != 0 ? gdbCurrPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) != 0 ? gdbCurrNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) != 0 ? gdbCurrCSSTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) : "-");
					glogger.info("Current values has been set");
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));
					gdbCurrTotalTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString())));
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))) != 0 ? dataStyle : "-");
					
					/*dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData();*/
					
					glogger.info("Current Total values has been set");

					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) != 0 ? gdbPrgPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) != 0 ? gdbPrgNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) : "-");
					lArrReturn.add(( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) != 0 ? gdbPrgTotalTotal+=( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) : "-");
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					gdbPrgTotalTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString())));
					dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))) != 0 ? dataStyle : "-");
					
					
					
					
					glogger.info("Progressive values ahas been set");



					lArrReturn.add("-");

					if (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())) != 0)
					{
						long l = ((int) ((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))) * 100) / Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())));
						if(l != 0)
								lArrReturn.add(l);
						else
							lArrReturn.add("-");
					}
					else
						lArrReturn.add("-");

					lArrReturn.add("-");
					lArrReturn.add("-");
					
					if(linkDisplay)
						lArrReturn.add(fundType);
					else
						lArrReturn.add(" ");
					
					lArrReturn.add(gStrFromDate);
					lArrReturn.add(gStrToDate);

				}
				else
				{
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) != 0 ?(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) : "-");

					glogger.info("Budget values has been set");
					StyledData dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())));
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()))) != 0 ? dataStyle : "-");

					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString())))!= 0 ?  (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) != 0 ?  (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) : "-");
					glogger.info("grant values has been set");
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))));
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))) != 0 ? dataStyle : "-");
					glogger.info("Grant Total has been set");

					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) != 0 ?(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) : "-");
					glogger.info("Current values has been set");
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))) != 0 ? dataStyle : "-");

					glogger.info("Current Total values has been set");

					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) : "-");
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) : "-");
					lArrReturn.add(( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) != 0 ? ( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) : "-");
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					
					dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));
					
					lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))) != 0 ? dataStyle : "-");
					glogger.info("Progressive values ahas been set");


					if(fundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")))
					{

						if ((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))) != 0 & ldiff !=0)	
							lArrReturn.add((int) (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString())) * 100 * longGrantPeriod)	/ ((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))) * (ldiff+1)));
						else
							lArrReturn.add("-");
					}
					else
					{
						lArrReturn.add("-");
					}
					
					

					if (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())) != 0)
					{
						long l = ((int) ((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))) * 100) / Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())));
						if(l != 0)
							lArrReturn.add(l);
						else
							lArrReturn.add("-");
					}
					else
						lArrReturn.add("-");

					lArrReturn.add("-");
					lArrReturn.add("-");
					
					if(linkDisplay)
						lArrReturn.add(fundType);
					else
						lArrReturn.add(" ");
					
					
					lArrReturn.add(gStrFromDate);
					lArrReturn.add(gStrToDate);



				}
			}

			else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitCapital"))||fundType == Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitRevenue")))
			{
				StyledData dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				glogger.info("Hi i am in final else if functiuon::::::::::::::::::::::::::");
				lArrReturn = new ArrayList();
				lArrReturn.add(gObjRptQueryDAO.getFundName(fundType));


			/*	lArrReturn.add(gBudPLRevRecp !=0? gBudPLRevRecp : "-" );
				lArrReturn.add(gBudNPRevRecp !=0? gBudNPRevRecp : "-" );
				lArrReturn.add(gBudCSSRevRecp !=0? gBudCSSRevRecp : "-" );*/
				
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				
				dataStyle.setData(gBudTotalRevRecp !=0? gBudTotalRevRecp : "-" );
				
				
				lArrReturn.add(dataStyle);
				
				/*lArrReturn.add(gGrantPLRevRecp !=0? gGrantPLRevRecp : "-" );
				lArrReturn.add(gGrantNPRevRecp !=0? gGrantNPRevRecp : "-" );
				lArrReturn.add(gGrantCSSRevRecp !=0? gGrantCSSRevRecp : "-" );*/
				
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				
				dataStyle.setData(gGrantTotalRevRecp !=0? gGrantTotalRevRecp : "-");

				lArrReturn.add(dataStyle);
				
				/*lArrReturn.add(gCurrPLRevRecp !=0? gCurrPLRevRecp : "-" );
				lArrReturn.add(gCurrNPRevRecp !=0? gCurrNPRevRecp : "-" );
				lArrReturn.add(gCurrCSSRevRecp !=0? gCurrCSSRevRecp : "-" );
				*/
				
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				
				dataStyle.setData(gCurrTotalRevRecp !=0? gCurrTotalRevRecp : "-");

				lArrReturn.add(dataStyle);
				
								
				/*lArrReturn.add(gPrgPLRevRecp !=0? gPrgPLRevRecp : "-" );
				lArrReturn.add(gPrgNPRevRecp !=0? gPrgNPRevRecp : "-" );
				lArrReturn.add(gPrgCSSRevRecp !=0? gPrgCSSRevRecp : "-" );*/
				
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				
				dataStyle.setData(gPrgTotalRevRecp !=0? gPrgTotalRevRecp : "-");

				lArrReturn.add(dataStyle);
				
				
				
				
				lArrReturn.add("-");
				
				if (gBudTotalRevRecp != 0)
				{
					long l = ((int) ((gPrgTotalRevRecp) * 100) / gBudTotalRevRecp);
					if(l != 0)
						lArrReturn.add(l);
					else
						lArrReturn.add("-");
				}
				else
					lArrReturn.add("-");
				
				lArrReturn.add("-");
				lArrReturn.add("-");
				if(linkDisplay)
					lArrReturn.add(fundType);
				else
					lArrReturn.add(" ");

				lArrReturn.add(gStrFromDate);
				lArrReturn.add(gStrToDate);

				glogger.info("Every thing is added>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}

			else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("NetConsolidatedFund")))
			{
				String lStrFundNm1 = "ConFundRecp";
				String lStrFundNm2 = "ConFundExp";
				glogger.info("Hi i am in final else if functiuon::::::::::::::::::::::::::");
				lArrReturn = new ArrayList();
				lArrReturn.add(gObjRptQueryDAO.getFundName(fundType) );


				lArrReturn.add(gdbBudPLTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Bud_PL").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Bud_PL").toString())))));
				lArrReturn.add(gdbBudNPTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Bud_NP").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Bud_NP").toString())))));
				lArrReturn.add(gdbBudCSSTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Bud_CSS").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Bud_CSS").toString())))));
				lArrReturn.add(gdbBudTotalTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Bud_Total").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Bud_Total").toString())))));
				
				lHashChart.put("gdbBudTotalTotal", gdbBudTotalTotal);
				
				lArrReturn.add(gdbGrantPLTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Grant_PL").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Grant_PL").toString())))));
				lArrReturn.add(((gdbGrantNPTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Grant_NP").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Grant_NP").toString()))))) == 0) ? "-" : gdbGrantNPTotal);
				lArrReturn.add(((gdbGrantCSSTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Grant_CSS").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Grant_CSS").toString()))))) == 0)  ? "-"  : gdbGrantCSSTotal);
				
				lArrReturn.add(((gdbGrantTotalTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Grant_Total").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Grant_Total").toString())))))== 0 ) ? "-" : gdbGrantTotalTotal);
				
				lHashChart.put("gdbGrantTotalTotal", gdbGrantTotalTotal);
				
				lArrReturn.add(((gdbCurrPLTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Curr_PL").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Curr_PL").toString()))))) == 0) ? "-" :gdbCurrPLTotal);
				lArrReturn.add(((gdbCurrNPTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Curr_NP").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Curr_NP").toString()))))) == 0) ? "-" : gdbCurrNPTotal);
				lArrReturn.add(((gdbCurrCSSTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Curr_CSS").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Curr_CSS").toString()))))) == 0) ? "-" :gdbCurrCSSTotal);
				lArrReturn.add(((gdbCurrTotalTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Curr_Total").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Curr_Total").toString()))))) == 0)? "-" :gdbCurrTotalTotal);
				
				lHashChart.put("gdbCurrTotalTotal", gdbCurrTotalTotal);
				
				lArrReturn.add(((gdbPrgPLTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Prg_PL").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Prg_PL").toString())))))==0)? "-" : gdbPrgPLTotal);
				lArrReturn.add(((gdbPrgNPTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Prg_NP").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Prg_NP").toString())))))==0)? "-" :gdbPrgNPTotal);
				lArrReturn.add(((gdbPrgCSSTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Prg_CSS").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Prg_CSS").toString())))))==0)? "-" : gdbPrgCSSTotal);
				lArrReturn.add(((gdbPrgTotalTotal=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm1 + "_Prg_Total").toString())- Math.round(Double.parseDouble(lhashMap.get(lStrFundNm2 + "_Prg_Total").toString())))))==0)? "-" :gdbPrgTotalTotal);
				
				lHashChart.put("gdbPrgTotalTotal", gdbPrgTotalTotal);
				

				lArrReturn.add("-");
				if (Math.round(gdbBudTotalTotal) != 0)
				{
					long l = ((int) ((Math.round(gdbPrgTotalTotal) * 100) / Math.round(gdbBudTotalTotal)));
					if(l != 0)
						lArrReturn.add(l);
					else
						lArrReturn.add("-");
				}
				else
					lArrReturn.add("-");
				
				
				lArrReturn.add("-");
				lArrReturn.add("-");
				if(linkDisplay)
					lArrReturn.add(fundType);
				else
					lArrReturn.add(" ");

				lArrReturn.add(gStrFromDate);
				lArrReturn.add(gStrToDate);



			}

			else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("RoundedDifference")))
			{
				NumberFormat lnfLong = NumberFormat.getNumberInstance();
				lnfLong.setGroupingUsed(false);
				lnfLong.setMaximumFractionDigits(3);
				lnfLong.setMinimumFractionDigits(3);
				StyledData dataStyle = null;
				lArrReturn = new ArrayList();
				lArrReturn.add("Rounded Difference");
				lArrReturn.add(gdbBudPLFracPart != 0 ? lnfLong.format(gdbBudPLFracPart)	: "-");
				lArrReturn.add(gdbBudNPFracPart != 0 ? lnfLong.format(gdbBudNPFracPart): "-");
				lArrReturn.add(gdbBudCSSFracPart != 0 ? lnfLong.format(gdbBudCSSFracPart) : "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(lnfLong.format(gdbBudTotalFracPart));
				lArrReturn.add(gdbBudTotalFracPart != 0 ? dataStyle : "-");

				lArrReturn.add(gdbGrantPLFracPart != 0 ? lnfLong
						.format(gdbGrantPLFracPart) : "-");
				lArrReturn.add(gdbGrantNPFracPart != 0 ? lnfLong
						.format(gdbGrantNPFracPart) : "-");
				lArrReturn.add(gdbGrantCSSFracPart != 0 ? lnfLong
						.format(gdbGrantCSSFracPart) : "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(lnfLong.format(gdbGrantTotalFracPart));
				lArrReturn.add(gdbGrantTotalFracPart != 0 ? dataStyle : "-");

				lArrReturn.add(gdbCurrPLFracPart != 0 ? lnfLong
						.format(gdbCurrPLFracPart) : "-");
				lArrReturn.add(gdbCurrNPFracPart != 0 ? lnfLong
						.format(gdbCurrNPFracPart) : "-");
				lArrReturn.add(gdbCurrCSSFracPart != 0 ? lnfLong
						.format(gdbCurrCSSFracPart) : "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(lnfLong.format(gdbCurrTotalFracPart));
				lArrReturn.add(gdbCurrTotalFracPart != 0 ? dataStyle : "-");

				lArrReturn.add(gdbPrgPLFracPart != 0 ? lnfLong.format(gdbPrgPLFracPart)
						: "-");
				lArrReturn.add(gdbPrgNPFracPart != 0 ? lnfLong.format(gdbPrgNPFracPart)
						: "-");
				lArrReturn.add(gdbPrgCSSFracPart != 0 ? lnfLong
						.format(gdbPrgCSSFracPart) : "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(lnfLong.format(gdbPrgTotalFracPart));
				lArrReturn.add(gdbPrgTotalFracPart != 0 ? dataStyle : "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData("-");
				lArrReturn.add(dataStyle);

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData("-");
				lArrReturn.add(dataStyle);

				lArrReturn.add("-");
				lArrReturn.add("-");

				if(linkDisplay)
					lArrReturn.add(fundType);
				else
					lArrReturn.add(" ");

				lArrReturn.add(gStrFromDate);
				lArrReturn.add(gStrToDate);



			}
			else if(fundType == Integer.parseInt(gObjRsrcBndle.getString("Total")))
			{



				NumberFormat lnfLong = NumberFormat.getNumberInstance();
				lnfLong.setGroupingUsed(false);
				lnfLong.setMaximumFractionDigits(3);
				lnfLong.setMinimumFractionDigits(3);
				StyledData dataStyle = null;



				lArrReturn = new ArrayList();

				lArrReturn.add("<b>Total(I+II+III)<b>");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbBudPLTotal);
				lArrReturn.add(gdbBudPLTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbBudNPTotal);
				lArrReturn.add(gdbBudNPTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbBudCSSTotal);
				lArrReturn.add(gdbBudCSSTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbBudTotalTotal);
				lArrReturn.add(gdbBudTotalTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbGrantPLTotal);
				lArrReturn.add(gdbGrantPLTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbGrantNPTotal);
				lArrReturn.add(gdbGrantNPTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbGrantCSSTotal);
				lArrReturn.add(gdbGrantCSSTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbGrantTotalTotal);
				lArrReturn.add(gdbGrantTotalTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbCurrPLTotal);
				lArrReturn.add(gdbCurrPLTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbCurrNPTotal);
				lArrReturn.add(gdbCurrNPTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbCurrCSSTotal);
				lArrReturn.add(gdbCurrCSSTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbCurrTotalTotal);
				lArrReturn.add(gdbCurrTotalTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbPrgPLTotal);
				lArrReturn.add(gdbPrgPLTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbPrgNPTotal);
				lArrReturn.add(gdbPrgNPTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbPrgCSSTotal);
				lArrReturn.add(gdbPrgCSSTotal != 0 ? (dataStyle)	: "-");

				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbPrgTotalTotal);
				lArrReturn.add(gdbPrgTotalTotal != 0 ? (dataStyle)	: "-");

				lArrReturn.add("-");
				
				if (Math.round(gdbBudTotalTotal) != 0)
				{
					long l = ((int) ((Math.round(gdbPrgTotalTotal) * 100) / Math.round(gdbBudTotalTotal)));
					if(l != 0)
						lArrReturn.add(l);
					else
						lArrReturn.add("-");
				}
				else
					lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");

				if(linkDisplay)
					lArrReturn.add(fundType);
				else
					lArrReturn.add(" ");

				lArrReturn.add(gStrFromDate);
				lArrReturn.add(gStrToDate);
			}



			return lArrReturn;

		}

		private List getSrcOfInfoData(ReportVO report) {
			ArrayList lArrSrcInfoData = new ArrayList();

			ArrayList lArrSrcInfoRow1 = new ArrayList();
			lArrSrcInfoRow1.add("Budget Estimates ");
			lArrSrcInfoRow1.add(": Finance Dept.(IWDMS Budget)");
			lArrSrcInfoData.add(lArrSrcInfoRow1);
			lArrSrcInfoRow1 = new ArrayList();
			lArrSrcInfoRow1.add("Grant Released ");
			lArrSrcInfoRow1.add(": Finance Dept.(IWDMS)");
			lArrSrcInfoData.add(lArrSrcInfoRow1);
			lArrSrcInfoRow1 = new ArrayList();
			lArrSrcInfoRow1.add("Curr. Year Exp.");
			lArrSrcInfoRow1.add(": DAT(EDP Cell)");
			lArrSrcInfoData.add(lArrSrcInfoRow1);
			lArrSrcInfoRow1 = new ArrayList();
			lArrSrcInfoRow1.add("Curr. Year Progressive Exp.");
			lArrSrcInfoRow1.add(": DAT(EDP Cell)");
			lArrSrcInfoData.add(lArrSrcInfoRow1);

			return lArrSrcInfoData;
		}

		/*private ArrayList getMjrHdRange(String lStrRange) {
			glogger.info("In getMjrHdRange: RangeString == " + lStrRange);
			ArrayList lArrRangeLst = new ArrayList();
			String[] lStrMjHdRange = null;
			if (lStrRange.contains("-")) {
				lStrMjHdRange = lStrRange.split("-");
				if (lStrMjHdRange != null) {
					lArrRangeLst.add(lStrMjHdRange[0]);
					lArrRangeLst.add(lStrMjHdRange[1]);
				}
			} else {
				lArrRangeLst.add(lStrRange);
			}
			glogger.info("MjrHD Range in getMjrHdRange() -- "
					+ lArrRangeLst.toString());
			return lArrRangeLst;
		}
*/
		private HashMap getContingencyPublicFund(int lintFinYrId,
				String lStrLangId, String lStrFromDt, String lStrToDt,
				int lintFundId) {
			glogger.info("-- In getContingencyPublicFund() -- ");
			String lStrFundName = "";
			double ltotalContAmount = 0;
			String lStrPrevFromDate = "";
			String lStrPrevToDate = "";
			double lPrevCurrTxt_Total = 0;
			double lPrevRE = 0;
			double lPercentOfCurr = 0;
			double lPrevProgTxt_Total = 0;
			double lPercentOfProg = 0;
			double lPLTotal = 0;
			double lNPTotal = 0;
			double lCSSTotal = 0;
			double lCurrNP = 0;
			double lCurrPL = 0;
			double lCurrCSS = 0;
			double lCurrTotal = 0;
			
			double grant_pl = 0.0;
			double grant_np = 0.0;
			double grant_css = 0.0;
			double grant_total = 0.0;



			HashMap lhshPrevDateMap = getPreviousDates(lStrFromDt, lStrToDt);
			lStrPrevFromDate = lhshPrevDateMap.get("fromdate").toString();
			lStrPrevToDate = lhshPrevDateMap.get("todate").toString();

			System.out
			.println("in getcontengencypublic fund previous date function isn callled safely and data is fetched");

			HashMap lhshContingencyFundMap = new HashMap();

			DSSQueryDAO gObjRptQueryDAO = (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");

			gObjRptQueryDAO.setSessionFactory(lObjSessionFactory);

			HashMap lhshSubConFundMap = (HashMap) gObjRptQueryDAO.getFundMjrRange(lintFundId);

			glogger.info("Fund Range for ContingencyPublicFund -- "	+ lhshSubConFundMap.get("MjrHdRange"));

			if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
				lStrFundName = "ConFund";
			else if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
				lStrFundName = "PubFund";

			ArrayList lArrMjrHdRange = getMjrHdRange(lhshSubConFundMap.get("MjrHdRange").toString());


			if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)"))) {
				lhshContingencyFundMap.put("ConFund_Bud_PL", 0);
				lhshContingencyFundMap.put("ConFund_Bud_NP", 0);
				lhshContingencyFundMap.put("ConFund_Bud_CSS", 0);
				lhshContingencyFundMap.put("ConFund_Bud_Total", ltotalContAmount);
				
				lHashChart.put("ConFund_Bud_Total", ltotalContAmount);
				
			} else if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)"))) {
				lhshContingencyFundMap.put("PubFund_Bud_PL", 0);
				lhshContingencyFundMap.put("PubFund_Bud_NP", 0);
				lhshContingencyFundMap.put("PubFund_Bud_CSS", 0);
				lhshContingencyFundMap.put("PubFund_Bud_Total", ltotalContAmount);
				
				lHashChart.put("PubFund_Bud_Total", ltotalContAmount);

			}
			glogger.info("Total in getContingencyPublicFund() == "+ lhshContingencyFundMap.get(lStrFundName + "_Bud_Total"));

			HashMap lhshGrantData = gObjRptQueryDAO.getGrantAmt(lArrMjrHdRange,lintFinYrId, "en_US");
			
			GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
			
			glogger.info("---------------Grant Amount is going to be calculated for :------------ " + lStrFundName);
			
			grant_pl = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "PL", lArrMjrHdRange);
			grant_np = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "NP", lArrMjrHdRange);
			grant_css = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "CSS", lArrMjrHdRange);
			grant_total = grant_pl + grant_np + grant_css;
			
			glogger.info("-----------gant amount has been calculated ---------------------------------------------");
			
			HashMap lhshCurrExpDataText = gObjRptQueryDAO.getExpenditureData(lArrMjrHdRange, lintFinYrId, lStrFromDt, lStrToDt);
			HashMap lhshCurrRecpDataText = gObjRptQueryDAO.getReceiptData(lArrMjrHdRange, lintFinYrId, lStrFromDt, lStrToDt);


			lCurrPL = Double.parseDouble(lhshCurrExpDataText.get("Exp_PL")
					.toString());
		//	lCurrPL = Double.parseDouble(lhshCurrRecpDataText.get("Receipt_PL").toString()) - lCurrPL;
			lCurrPL = 0 - lCurrPL;
			
			lCurrNP = Double.parseDouble(lhshCurrExpDataText.get("Exp_NP")
					.toString());
		//	lCurrNP = Double.parseDouble(lhshCurrRecpDataText.get("Receipt_NP").toString()) - lCurrNP;
			
			lCurrNP = 0 - lCurrNP;
			
			lCurrCSS = Double.parseDouble(lhshCurrExpDataText.get("Exp_CSS")
					.toString());
		//	lCurrCSS = Double.parseDouble(lhshCurrRecpDataText.get("Receipt_CSS").toString()) - lCurrCSS;
			
			lCurrCSS = 0 - lCurrCSS;
			
			
			lCurrTotal = Double.parseDouble(lhshCurrExpDataText.get("Exp_Total")
					.toString());
			lCurrTotal = Double.parseDouble(lhshCurrRecpDataText.get("Receipt_Total").toString()) - lCurrTotal;
			
			
			
			if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)"))) {
				lhshContingencyFundMap.put("ConFund_Grant_PL",grant_pl );
				lhshContingencyFundMap.put("ConFund_Grant_NP", grant_np);
				lhshContingencyFundMap.put("ConFund_Grant_CSS", grant_css);
				lhshContingencyFundMap.put("ConFund_Grant_Total",grant_total);
				
				lHashChart.put("ConFund_Grant_Total",grant_total);
				
				lhshContingencyFundMap.put("ConFund_Curr_PL",lCurrPL);

				lhshContingencyFundMap.put("ConFund_Curr_NP",lCurrNP);

				lhshContingencyFundMap.put("ConFund_Curr_CSS",lCurrCSS);





			} else if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)"))) {
				lhshContingencyFundMap.put("PubFund_Grant_PL", grant_pl);
				lhshContingencyFundMap.put("PubFund_Grant_NP", grant_np);
				lhshContingencyFundMap.put("PubFund_Grant_CSS", grant_css);
				lhshContingencyFundMap.put("PubFund_Grant_Total",grant_total);
				
				lHashChart.put("PubFund_Grant_Total",grant_total);

				lhshContingencyFundMap.put("PubFund_Curr_PL",lCurrPL);

				lhshContingencyFundMap.put("PubFund_Curr_NP",lCurrNP);

				lhshContingencyFundMap.put("PubFund_Curr_CSS",lCurrCSS);

			}


			/*HashMap lhshCurrExpDataText = gObjRptQueryDAO.getExpenditureData(lArrMjrHdRange, lintFinYrId, lStrFromDt, lStrToDt);
			HashMap lhshCurrRecpDataText = gObjRptQueryDAO.getReceiptData(lArrMjrHdRange, lintFinYrId, lStrFromDt, lStrToDt);
*/
		/*	lCurrTotal = Double.parseDouble(lhshCurrExpDataText.get("Exp_Total")
					.toString());
			lCurrTotal = Double.parseDouble(lhshCurrRecpDataText.get("Receipt_Total").toString()) - lCurrTotal;
*/
			if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
				{
					lhshContingencyFundMap.put("ConFund_Curr_Total", lCurrTotal);
					lHashChart.put("ConFund_Curr_Total", lCurrTotal) ;
				}
			else if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
				{
					lhshContingencyFundMap.put("PubFund_Curr_Total", lCurrTotal);
					lHashChart.put("PubFund_Curr_Total", lCurrTotal);
				}

			lPLTotal = 0;
			lNPTotal = 0;
			lCSSTotal = 0;

			HashMap lhshPrgExpDataText = gObjRptQueryDAO.getExpenditureData(lArrMjrHdRange, lintFinYrId, "", lStrToDt);
			HashMap lhshPrgRcptDataText = gObjRptQueryDAO.getReceiptData(lArrMjrHdRange, lintFinYrId, "", lStrToDt);

			/*lCurrTotal = Double.parseDouble(lhshPrgExpDataText.get("Exp_Total").toString());


			glogger.info("The fund Id:::::::::::::::::::::::::::::::::::::::::::::::::: = " + lintFundId);

			lCurrTotal = Double.parseDouble(lhshPrgRcptDataText.get("Receipt_Total").toString()) - lCurrTotal;
			glogger.info("The values of progressive total islllllllllllllllllllllllllllllllll = " + lCurrTotal);
*/
			

			lCurrPL = Double.parseDouble(lhshPrgExpDataText.get("Exp_PL")
					.toString());
	//		lCurrPL = Double.parseDouble(lhshPrgRcptDataText.get("Receipt_PL").toString()) - lCurrPL;
			
			lCurrPL = 0 - lCurrPL;
			
			lCurrNP = Double.parseDouble(lhshPrgExpDataText.get("Exp_NP")
					.toString());
	//		lCurrNP = Double.parseDouble(lhshPrgRcptDataText.get("Receipt_NP").toString()) - lCurrNP;
			
			lCurrNP = 0 - lCurrNP;
			
			lCurrCSS = Double.parseDouble(lhshPrgExpDataText.get("Exp_CSS")
					.toString());
	//		lCurrCSS = Double.parseDouble(lhshPrgRcptDataText.get("Receipt_CSS").toString()) - lCurrCSS;
			
			lCurrCSS = 0 - lCurrCSS;
			
			
			lCurrTotal = Double.parseDouble(lhshPrgExpDataText.get("Exp_Total")
					.toString());
			lCurrTotal = Double.parseDouble(lhshPrgRcptDataText.get("Receipt_Total").toString()) - lCurrTotal;



			if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
			{
				lhshContingencyFundMap.put("ConFund_Prg_PL",lCurrPL);

				lhshContingencyFundMap.put("ConFund_Prg_NP",lCurrNP);

				lhshContingencyFundMap.put("ConFund_Prg_CSS",lCurrCSS);

				lhshContingencyFundMap.put("ConFund_Prg_Total", lCurrTotal);
				
				lHashChart.put("ConFund_Prg_Total", lCurrTotal);
				
			}
			else if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
			{
				lhshContingencyFundMap.put("PubFund_Prg_PL",lCurrPL);

				lhshContingencyFundMap.put("PubFund_Prg_NP",lCurrNP);

				lhshContingencyFundMap.put("PubFund_Prg_CSS",lCurrCSS);
				lhshContingencyFundMap.put("PubFund_Prg_Total", lCurrTotal);
				
				lHashChart.put("PubFund_Prg_Total", lCurrTotal);
				
			}


			if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
				lhshContingencyFundMap.put("ConFund_fundType", "-");
			else if (lintFundId == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
				lhshContingencyFundMap.put("PubFund_fundType", "-");


			glogger.info("Before Returning from getContingencyPublicFund()");
			return lhshContingencyFundMap;
		}


		private HashMap getConsolidatedFund(int lintFinYrId, String lStrLangId,String lStrFromDt, String lStrToDt)
		{
			double ConFund_Bud_PL = 0;
			double ConFund_Bud_NP = 0;
			double ConFund_Bud_CSS = 0;
			double ConFund_Bud_Total = 0;
			double ConFund_Curr_PL = 0;
			double ConFund_Curr_NP = 0;
			double ConFund_Curr_CSS = 0;
			double ConFund_Curr_Total = 0;
			double ConFund_Prog_PL = 0;
			double ConFund_Prog_NP = 0;
			double ConFund_Prog_CSS = 0;
			double ConFund_Prog_Total = 0;

			double ConFundExp_Bud_PL = 0;
			double ConFundExp_Bud_NP = 0;
			double ConFundExp_Bud_CSS = 0;
			double ConFundExp_Bud_Total = 0;
			double ConFundExp_Grant_PL = 0;
			double ConFundExp_Grant_NP = 0;
			double ConFundExp_Grant_CSS = 0;
			double ConFundExp_Grant_Total = 0;

			double ConFundExp_Curr_PL = 0;
			double ConFundExp_Curr_NP = 0;
			double ConFundExp_Curr_CSS = 0;
			double ConFundExp_Curr_Total = 0;
			double ConFundExp_Prog_PL = 0;
			double ConFundExp_Prog_NP = 0;
			double ConFundExp_Prog_CSS = 0;
			double ConFundExp_Prog_Total = 0;

			glogger.info("-- In getConsolidatedFund() -- ");

			HashMap<String,Double> lhshConsolidatedFundMap = new HashMap<String,Double>();
			String lStrPrevFromDate = null;
			String lStrPrevToDate = null;

			DSSQueryDAO gObjRptQueryDAO = (DSSQueryDAO) DAOFactory
			.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			gObjRptQueryDAO.setSessionFactory(lObjSessionFactory);

			ArrayList lArrSubConsFund = (ArrayList) gObjRptQueryDAO.getSubFundMjrRange(100001);

			glogger.info("Size of Sub funds for ConsolidatedFund -- "	+ lArrSubConsFund.size());

			HashMap lhshPrevDateMap = getPreviousDates(lStrFromDt, lStrToDt);
			lStrPrevFromDate = lhshPrevDateMap.get("fromdate").toString();
			lStrPrevToDate = lhshPrevDateMap.get("todate").toString();
			glogger.info("get Previous date is executed safely and data is fetcehd ");

			for (int i = 0; i < lArrSubConsFund.size(); i++) 
			{
				HashMap lhshSubConFundMap = (HashMap) lArrSubConsFund.get(i);
				glogger.info("FundName -- "+ lhshSubConFundMap.get("FundName"));

				if (lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Revenue Receipt") || lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Capital Receipt")) 
				{
					glogger.info("FundName -- "+ lhshSubConFundMap.get("FundName"));
					double lBudAmt_PL = 0;
					double lBudAmt_NP = 0;
					double lBudAmt_CSS = 0;
					double ltotalRecp = 0;
					double lGrantAmt_PL = 0;
					double lGrantAmt_NP = 0;
					double lGrantAmt_CSS = 0;
					double ltotalGrant = 0;
					double lCurrAmt_PL = 0;
					double lCurrAmt_NP = 0;
					double lCurrAmt_CSS = 0;
					double lCurrAmt_Total = 0;
					double lPrgAmt_PL = 0;
					double lPrgAmt_NP = 0;
					double lPrgAmt_CSS = 0;
					double lPrgAmt_Total = 0;
					double lPrevCurrTxt_Total = 0;
					double lPrevRE = 0;
					double lPercentOfCurr = 0;
					double lPrevProgTxt_Total = 0;
					double lPercentOfProg = 0;


//					double ConFund_Bud_PL = 0;

					ArrayList lArrSubRevRecp = (ArrayList) gObjRptQueryDAO.getSubFundMjrRange(Integer.parseInt(lhshSubConFundMap.get("FundId").toString()));

					glogger.info("Size of Sub Funds for "	+ lhshSubConFundMap.get("FundName") + " -- " + lArrSubRevRecp.size());

					HashMap lhshMapSubRevRecp = null;

					for (int j = 0; j < lArrSubRevRecp.size(); j++) 
					{
						lhshMapSubRevRecp = (HashMap) lArrSubRevRecp.get(j);

						ArrayList lArrMjrHdRange = getMjrHdRange(lhshMapSubRevRecp.get("MjrHdRange").toString());

						HashMap lhshMapConRecpAmt = gObjRptQueryDAO.getConsolidatedRcpBudAmt(lArrMjrHdRange,lintFinYrId);

						lBudAmt_PL = lBudAmt_PL	+ Double.parseDouble(lhshMapConRecpAmt.get("Bud_PL").toString());
						lBudAmt_NP = lBudAmt_NP	+ Double.parseDouble(lhshMapConRecpAmt.get("Bud_NP").toString());
						lBudAmt_CSS = lBudAmt_CSS	+ Double.parseDouble(lhshMapConRecpAmt.get("Bud_CSS").toString());
						ltotalRecp = ltotalRecp	+ Double.parseDouble(lhshMapConRecpAmt.get("ConsolidatedReceiptTotal").toString());

						HashMap lhshCurrDataText = gObjRptQueryDAO.getReceiptData(lArrMjrHdRange, lintFinYrId, lStrFromDt, lStrToDt);


						glogger.info("Function is returneddd++++++++++++++++++++");



						lCurrAmt_Total = lCurrAmt_Total	+ Double.parseDouble(lhshCurrDataText.get("Receipt_Total").toString());

						HashMap lhshPrgDataText = gObjRptQueryDAO.getReceiptData(lArrMjrHdRange, lintFinYrId, "", lStrToDt);

						lPrgAmt_Total = lPrgAmt_Total + Double.parseDouble(lhshPrgDataText.get("Receipt_Total").toString());
					}


					String lStrFundNm = "";
					if (lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Revenue Receipt"))
						lStrFundNm = "RevRecp";
					else
						lStrFundNm = "CapRecp";


					lhshConsolidatedFundMap.put(lStrFundNm + "_Bud_PL", lBudAmt_PL);
					ConFund_Bud_PL = ConFund_Bud_PL + lBudAmt_PL;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Bud_NP", lBudAmt_NP);
					ConFund_Bud_NP = ConFund_Bud_NP + lBudAmt_NP;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Bud_CSS", lBudAmt_CSS);
					ConFund_Bud_CSS = ConFund_Bud_CSS + lBudAmt_CSS;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Bud_Total",ltotalRecp);
					ConFund_Bud_Total = ConFund_Bud_Total + ltotalRecp;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Grant_PL", lGrantAmt_PL);
					lhshConsolidatedFundMap.put(lStrFundNm + "_Grant_NP", lGrantAmt_NP);
					lhshConsolidatedFundMap.put(lStrFundNm + "_Grant_CSS", lGrantAmt_CSS);
					lhshConsolidatedFundMap.put(lStrFundNm + "_Grant_Total", ltotalGrant);


					lhshConsolidatedFundMap.put(lStrFundNm + "_Curr_PL",lCurrAmt_PL);
					ConFund_Curr_PL = ConFund_Curr_PL + lCurrAmt_PL;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Curr_NP",lCurrAmt_NP);
					ConFund_Curr_NP = ConFund_Curr_NP + lCurrAmt_NP;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Curr_CSS",lCurrAmt_CSS);
					ConFund_Curr_CSS = ConFund_Curr_CSS + lCurrAmt_CSS;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Curr_Total",lCurrAmt_Total);

					ConFund_Curr_Total = ConFund_Curr_Total + lCurrAmt_Total;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Prg_PL", lPrgAmt_PL);
					ConFund_Prog_PL = ConFund_Prog_PL + lPrgAmt_PL;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Prg_NP", lPrgAmt_NP);
					ConFund_Prog_NP = ConFund_Prog_NP + lPrgAmt_NP;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Prg_CSS",lPrgAmt_CSS);
					ConFund_Prog_CSS = ConFund_Prog_CSS + lPrgAmt_CSS;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Prg_Total",lPrgAmt_Total);
					ConFund_Prog_Total = ConFund_Prog_Total + lPrgAmt_Total;

					lhshConsolidatedFundMap.put(lStrFundNm + "_fundType",Double.parseDouble(lhshSubConFundMap.get("FundId").toString()));

					if(lStrFundNm.equalsIgnoreCase("CapRecp"))
					{
						glogger.info("Hi iam in ConFundRecp if loop and i will set all the values of ConFund Receipt");
						lhshConsolidatedFundMap.put("ConFundRecp_Bud_PL",ConFund_Bud_PL);
						lhshConsolidatedFundMap.put("ConFundRecp_Bud_NP", ConFund_Bud_NP);
						lhshConsolidatedFundMap.put("ConFundRecp_Bud_CSS",ConFund_Bud_CSS);
						lhshConsolidatedFundMap.put("ConFundRecp_Bud_Total",ConFund_Bud_Total);
						lhshConsolidatedFundMap.put("ConFundRecp_Grant_PL", 0.0);
						lhshConsolidatedFundMap.put("ConFundRecp_Grant_NP", 0.0);
						lhshConsolidatedFundMap.put("ConFundRecp_Grant_CSS",0.0);
						lhshConsolidatedFundMap.put("ConFundRecp_Grant_Total",0.0);
						lhshConsolidatedFundMap.put("ConFundRecp_Curr_PL",ConFund_Curr_PL);
						lhshConsolidatedFundMap.put("ConFundRecp_Curr_NP",ConFund_Curr_NP);
						lhshConsolidatedFundMap.put("ConFundRecp_Curr_CSS",ConFund_Curr_CSS);
						lhshConsolidatedFundMap.put("ConFundRecp_Curr_Total",ConFund_Curr_Total);
						lhshConsolidatedFundMap.put("ConFundRecp_Prg_PL",ConFund_Prog_PL);
						lhshConsolidatedFundMap.put("ConFundRecp_Prg_NP",ConFund_Prog_NP);
						lhshConsolidatedFundMap.put("ConFundRecp_Prg_CSS",ConFund_Prog_CSS);
						lhshConsolidatedFundMap.put("ConFundRecp_Prg_Total",ConFund_Prog_Total);

					}


					lhshConsolidatedFundMap.put("fundId", Double.parseDouble(lhshSubConFundMap.get("FundId").toString()));
					//lhshConsolidatedFundMap.put("fundId", lhshConsolidatedFundMap.put("fundId", lhshSubConFundMap.get("FundId").toString()));


				} else if (lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Revenue Expenditure")
						|| lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Capital Expenditure(Incl. Loans)")) {

					glogger.info("FundName -- "+ lhshSubConFundMap.get("FundName"));
					double lPLTotal = 0;
					double lNPTotal = 0;
					double lCSSTotal = 0;
					double lCurrTotal = 0;
					double lPrevCurrTxt_Total = 0;
					double lPrevRE = 0;
					double lPercentOfCurr = 0;
					double lPrevProgTxt_Total = 0;
					double lPercentOfProg = 0;
					
					double grant_pl = 0.0;
					double grant_np = 0.0;
					double grant_css = 0.0;
					double grant_total = 0.0;
					
					



					String lStrFundNm = "";

					if (lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Revenue Expenditure"))
						lStrFundNm = "RevExp";
					else
						lStrFundNm = "CapExp";

					HashMap lhshMapRevExp = gObjRptQueryDAO.getFundMjrRange(Integer.parseInt(lhshSubConFundMap.get("FundId").toString()));

					ArrayList lArrMjrHdRange = getMjrHdRange(lhshMapRevExp.get("MjrHdRange").toString());

					glogger.info("MjrHdRange for "+ lhshSubConFundMap.get("FundName") + " -- "+ lArrMjrHdRange);

					HashMap lhshMapRevExpAmt = gObjRptQueryDAO.getConsolidatedExpBudAmt(lArrMjrHdRange, lintFinYrId);

					lPLTotal = Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_PL_CH").toString());
					lPLTotal = lPLTotal	+ Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_PL_VT").toString());

					ConFundExp_Bud_PL = ConFundExp_Bud_PL + lPLTotal;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Bud_PL", lPLTotal);

					glogger.info(lStrFundNm + "_Bud_PL == " + lPLTotal);

					lNPTotal = Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_NP_CH").toString());

					lNPTotal = lNPTotal	+ Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_NP_VT").toString());

					lCSSTotal = gObjRptQueryDAO.getCSSAmount(lArrMjrHdRange,lintFinYrId);

					lhshConsolidatedFundMap.put(lStrFundNm + "_Bud_CSS", lCSSTotal);
					
					glogger.info("the css amount is final parth :- " + lCSSTotal);

					ConFundExp_Bud_CSS = ConFundExp_Bud_CSS + lCSSTotal;

					lNPTotal = lNPTotal - lCSSTotal;

					ConFundExp_Bud_NP = ConFundExp_Bud_NP + lNPTotal;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Bud_NP", lNPTotal);

					glogger.info(lStrFundNm + "_Bud_CSS == " + lCSSTotal);
					glogger.info(lStrFundNm + "_Bud_NP == " + lNPTotal);

					ConFundExp_Bud_Total = ConFundExp_Bud_Total + lPLTotal + lNPTotal +  lCSSTotal;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Bud_Total", lPLTotal	+ lNPTotal + lCSSTotal);





				//	HashMap<String,Double> lhshGrantData = gObjRptQueryDAO.getGrantAmt(lArrMjrHdRange, lintFinYrId, "en_US");
					
					GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
					
					//GrantDtlDAO lObjGrantDtlDAOImpl = (GrantDtlDAO) DAOFactory.Create("com.tcs.sgv.common.dao.GrantDtlDAOImpl");
					
					grant_pl = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "PL", lArrMjrHdRange);
					grant_np = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "NP", lArrMjrHdRange);
					grant_css = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "CSS", lArrMjrHdRange);
					grant_total = grant_pl + grant_np + grant_css;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Grant_PL",grant_pl);

					ConFundExp_Grant_PL = ConFundExp_Grant_PL + grant_pl;


					lhshConsolidatedFundMap.put(lStrFundNm + "_Grant_NP",grant_np );
					ConFundExp_Grant_NP = ConFundExp_Grant_NP + grant_np;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Grant_CSS",grant_css);
					ConFundExp_Grant_CSS = ConFundExp_Grant_CSS + grant_css;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Grant_Total", grant_total);

					ConFundExp_Grant_Total = ConFundExp_Grant_Total + grant_total;

					lPLTotal = 0;
					lNPTotal = 0;
					lCSSTotal = 0;




					HashMap<String,Double> lhshCurrDataText = gObjRptQueryDAO.getExpenditureData(lArrMjrHdRange, lintFinYrId, lStrFromDt, lStrToDt);


					lPLTotal += Double.parseDouble(lhshCurrDataText.get("Exp_PL").toString());
					ConFundExp_Curr_PL =ConFundExp_Curr_PL + lPLTotal; 
					lhshConsolidatedFundMap.put(lStrFundNm + "_Curr_PL", lPLTotal);


					lNPTotal += Double.parseDouble(lhshCurrDataText.get("Exp_NP").toString());
					ConFundExp_Curr_NP =ConFundExp_Curr_NP + lNPTotal;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Curr_NP", lNPTotal);

					lCSSTotal += Double.parseDouble(lhshCurrDataText.get("Exp_CSS").toString());
					ConFundExp_Curr_CSS =ConFundExp_Curr_CSS + lCSSTotal;

					lhshConsolidatedFundMap.put(lStrFundNm + "_Curr_CSS", lCSSTotal);
					
					lCurrTotal = lPLTotal + lNPTotal + lCSSTotal;

					//System.out.println("hi this is parth :- the three current values vlaues arew -------------------------");
					//System.out.println("PL :- " + lPLTotal);
					//System.out.println("PL :- " + lNPTotal);
					//System.out.println("PL :- " + lCSSTotal);
					//System.out.println("PL :- " + lCurrTotal);
					//System.out.println("hi this is parth :- the three vlaues arew -------------------------");

					
					ConFundExp_Curr_Total = ConFundExp_Curr_Total + lCurrTotal;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Curr_Total",	lCurrTotal);

					lPLTotal = 0;
					lNPTotal = 0;
					lCSSTotal = 0;
					lCurrTotal= 0;


					HashMap<String,Double> lhshPrgDataText = gObjRptQueryDAO.getExpenditureData(lArrMjrHdRange, lintFinYrId, "", lStrToDt);

					lPLTotal += Double.parseDouble(lhshPrgDataText.get("Exp_PL").toString());
					ConFundExp_Prog_PL = ConFundExp_Prog_PL + lPLTotal;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Prg_PL", lPLTotal);


					lNPTotal += Double.parseDouble(lhshPrgDataText.get("Exp_NP").toString());
					ConFundExp_Prog_NP =ConFundExp_Prog_NP + lNPTotal;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Prg_NP", lNPTotal);

					lCSSTotal += Double.parseDouble(lhshPrgDataText.get("Exp_CSS").toString());
					ConFundExp_Prog_CSS =ConFundExp_Prog_CSS + lCSSTotal;
					lhshConsolidatedFundMap.put(lStrFundNm + "_Prg_CSS", lCSSTotal);

					lCurrTotal = lPLTotal + lNPTotal + lCSSTotal;
					
					//System.out.println("hi this is parth :- the three progressive vlaues arew -------------------------");
					//System.out.println("PL :- " + lPLTotal);
					//System.out.println("PL :- " + lNPTotal);
					//System.out.println("PL :- " + lCSSTotal);
					//System.out.println("PL :- " + lCurrTotal);
					//System.out.println("hi this is parth :- the three vlaues arew -------------------------");
					
					
					ConFundExp_Prog_Total =ConFundExp_Prog_Total + lCurrTotal;
					
					lhshConsolidatedFundMap.put(lStrFundNm + "_Prg_Total",lCurrTotal);

					lhshConsolidatedFundMap.put(lStrFundNm + "_fundType",
							Double.parseDouble(lhshSubConFundMap.get("FundId").toString()));

					lhshConsolidatedFundMap.put("fundId", Double.parseDouble(lhshSubConFundMap.get("FundId").toString()));

					if(lStrFundNm.equalsIgnoreCase("CapExp"))
					{
						glogger.info("Hi iam in ConFundExpenditure if loop and i will set all the values of ConFund Expenditeru");
						lhshConsolidatedFundMap.put("ConFundExp_Bud_PL",ConFundExp_Bud_PL);
						lhshConsolidatedFundMap.put("ConFundExp_Bud_NP",ConFundExp_Bud_NP);
						lhshConsolidatedFundMap.put("ConFundExp_Bud_CSS",ConFundExp_Bud_CSS);
						lhshConsolidatedFundMap.put("ConFundExp_Bud_Total",ConFundExp_Bud_Total);
						lhshConsolidatedFundMap.put("ConFundExp_Grant_PL",ConFundExp_Grant_PL);
						lhshConsolidatedFundMap.put("ConFundExp_Grant_NP",ConFundExp_Grant_NP);
						lhshConsolidatedFundMap.put("ConFundExp_Grant_CSS",ConFundExp_Grant_CSS);
						lhshConsolidatedFundMap.put("ConFundExp_Grant_Total",ConFundExp_Grant_Total);
						lhshConsolidatedFundMap.put("ConFundExp_Curr_PL",ConFundExp_Curr_PL);
						lhshConsolidatedFundMap.put("ConFundExp_Curr_NP",ConFundExp_Curr_NP);
						lhshConsolidatedFundMap.put("ConFundExp_Curr_CSS",ConFundExp_Curr_CSS);
						lhshConsolidatedFundMap.put("ConFundExp_Curr_Total",ConFundExp_Curr_Total);
						lhshConsolidatedFundMap.put("ConFundExp_Prg_PL",ConFundExp_Prog_PL);
						lhshConsolidatedFundMap.put("ConFundExp_Prg_NP",ConFundExp_Prog_NP);
						lhshConsolidatedFundMap.put("ConFundExp_Prg_CSS",ConFundExp_Prog_CSS);
						lhshConsolidatedFundMap.put("ConFundExp_Prg_Total",ConFundExp_Prog_Total);
					}


				}
			}
			return lhshConsolidatedFundMap;
		}



		public HashMap getPreviousDates(String lStrFrmDate, String gStrToDate) {
			glogger.info("----------Inside getDates() method---------");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			glogger.info("sdf object is created");
			sdf.setLenient(false);
			HashMap hmp = new HashMap();
			Date lFromDate;
			Date lToDate;
			long lLongDifference;
			try {
				GregorianCalendar lGCfrmDate = new GregorianCalendar();
				GregorianCalendar lGCtoDate = new GregorianCalendar();
				lFromDate = sdf.parse(lStrFrmDate);
				lToDate = sdf.parse(gStrToDate);
				lGCfrmDate.setTime(lFromDate);
				lGCtoDate.setTime(lToDate);
				int liDateIdx = lGCfrmDate.DATE;
				int liDate = lGCfrmDate.get(liDateIdx);
				int liMnthIdx = lGCfrmDate.MONTH;
				int liMnth = lGCfrmDate.get(liMnthIdx);
				liMnth++;
				int liYrIdx = lGCfrmDate.YEAR;
				int liYr = lGCfrmDate.get(liYrIdx);
				liYr--;
				// String gStrFromDate = liYr+"-"+liMnth+"-"+liDate;
				String gStrFromDate = liDate + "/" + liMnth + "/" + liYr;

				liDateIdx = lGCtoDate.DATE;
				liDate = lGCtoDate.get(liDateIdx);
				liMnthIdx = lGCtoDate.MONTH;
				liMnth = lGCtoDate.get(liMnthIdx);
				liMnth++;
				liYrIdx = lGCtoDate.YEAR;
				liYr = lGCtoDate.get(liYrIdx);
				liYr--;
				// String lStringToDate = liYr+"-"+liMnth+"-"+liDate;
				String lStringToDate = liDate + "/" + liMnth + "/" + liYr;

				hmp.put("fromdate", gStrFromDate);
				hmp.put("todate", lStringToDate);

				glogger.info("try is executed safely");

			} catch (Exception e) {
				e.printStackTrace();
			}
			return hmp;
		}

		public List generateConsolidatedRows(int lintFinYrId ,String lStrLangId, String lStrFromDt,String lStrToDt,boolean linkDisplay)
		{
			ArrayList gArrMainDataLst = new ArrayList();
			long longGrantPeriod = 0;
			
			GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
			
			longGrantPeriod = lObjGrantDtlDAOImpl.getGrantPeriod(lStrToDt, lintFinYrId);

			HashMap lhshConsolidatedFund = getConsolidatedFund(lintFinYrId,	lStrLangId, lStrFromDt, lStrToDt);
			ArrayList lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFund")),linkDisplay,0,longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);
			glogger.info("First Row is generated");

//			Second Row 1) Revenue REceipt
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")),linkDisplay ,0,longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);
			glogger.info("Second Row is generated");

//			Second Row 1) Revenue Expenditure
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")),linkDisplay ,0 ,longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);
			glogger.info("Third Row is generated");

//			Third Row 3) SurPlus Deficit
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitRevenue")),linkDisplay ,0,longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);
			glogger.info("Fourth Row is generated");

//			Fourth Row 4) Capital REceipt
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt")),linkDisplay ,0,longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);
			glogger.info("Fifth Row is generated");

//			Fifth Row 5) Capital Expenditure
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")),linkDisplay ,0,longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);
			glogger.info("SixthRow is generated");

//			Sixth Row 6) Surplus / Deficit
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitCapital")),linkDisplay ,0 ,longGrantPeriod );
			gArrMainDataLst.add(lArrReturn);
			glogger.info("seventh Row is generated");

//			Seventh Row 7) Consolidated Fund Receipt
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundReceipt")),linkDisplay ,0,longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);

			glogger.info("Eightth Row is generated");

//			Eightth Row 8) Consolidated Fund Expenditure
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundExpenditure")),linkDisplay ,0 , longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);
			glogger.info("Ninth Row is generated");

//			Ninth Row 9) Net Consolidated Fund
			lArrReturn = new ArrayList();
			lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("NetConsolidatedFund")),linkDisplay ,0,longGrantPeriod);
			gArrMainDataLst.add(lArrReturn);
			glogger.info("Tenth Row has been Added");

			return gArrMainDataLst;



		}


		public String getHeadName(int i)
		{
			String lretValue = "";

			if(i<10)
				lretValue = "000"+i;
			else if(i<100 & i >= 10)
				lretValue = "00"+i;
			else if(i<1000 & i>=100)
				lretValue = "0"+i;
			else
				lretValue = String.valueOf(i);


			return lretValue;
		}



		private ArrayList getMjrHdRange(String lStrRange) {
			glogger.info("In getMjrHdRange: RangeString == " + lStrRange);
			ArrayList lArrRangeLst = new ArrayList();
			String[] lStrMjHdRange = null;
			if (lStrRange.contains("-")) {
				lStrMjHdRange = lStrRange.split("-");
				if (lStrMjHdRange != null) {
					lArrRangeLst.add(lStrMjHdRange[0]);
					lArrRangeLst.add(lStrMjHdRange[1]);
				}
			} else {
				lArrRangeLst.add(lStrRange);
			}
			glogger.info("MjrHD Range in getMjrHdRange() -- "
					+ lArrRangeLst.toString());
			return lArrRangeLst;
		}
		
		
		
		
		
		
		public List getSubFundReport(ReportVO lObjReport)
		{


			glogger.info("----------------Inside getSubFundReport Function of DSSReportImpl.java --"+lObjReport.getParameterValue("fundType").toString());

			StyledData dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);

			NumberFormat lnfLong = NumberFormat.getNumberInstance();
			lnfLong.setGroupingUsed(false);
			lnfLong.setMaximumFractionDigits(3);
			lnfLong.setMinimumFractionDigits(3); 



//			--------------------------------------Variable Decleration-----------------------------------------------------------------------

			List lArrMainDataLst = null;
			//List lArrMainReturn =null;
			List lArrReturn1 = null;
			

			double ltotalRecp = 0;
			double lGrantAmt_PL = 0;
			double lGrantAmt_NP = 0;
			double lGrantAmt_CSS = 0;
			double ltotalGrant = 0;
			double lCurrAmt_PL = 0;
			double lCurrAmt_NP = 0;
			double lCurrAmt_CSS = 0;
			double lCurrAmt_Total = 0;
			double lPrgAmt_PL = 0;
			double lPrgAmt_NP = 0;
			double lPrgAmt_CSS = 0;
			double lPrgAmt_Total = 0;
			double lPrevCurrTxt_Total = 0;
			double lPrevRE = 0;
			double lPercentOfCurr = 0;
			double lPrevProgTxt_Total = 0;
			double lPercentOfProg = 0;


			String lStrSubFundName ="";
			String lStrLangId = "";
			String lStrToDt = "";
			String lStrFromDt = "";

			long fund_type = 0;
			long ldiffDays = 0;

			int lintFinYrId = 0;
			int lIntPrgDiffDays = 0;

			HashMap lhshMapSubFund=null;
			HashMap lhashMapSubFundData = null;
			HashMap lhshDateMap = null;

			DSSQueryDAO gObjRptQueryDAO = null;

//			--------------------------------------------Variable Initialization & Parameter Fetching----------------------------------------------------

			gObjRptQueryDAO = (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			gObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
			fund_type = Long.parseLong(lObjReport.getParameterValue("fundType").toString());
			lStrFromDt = (lObjReport.getParameterValue("startDate").toString());
			lStrToDt = (lObjReport.getParameterValue("endDate").toString());
			gStrFromDate = lStrFromDt;
			gStrToDate = lStrToDt;
			lintFinYrId = gObjRptQueryDAO.getFinancialYear(lStrFromDt, lStrToDt);
			lStrLangId = lObjReport.getLangId();
			lhshDateMap = getDates(lStrFromDt, lStrToDt);

			ldiffDays = Long.parseLong(lhshDateMap.get("difference").toString());
			lIntPrgDiffDays = Integer.parseInt(lhshDateMap.get("prgDifference")	.toString());

			lArrMainDataLst = new ArrayList();
			List lArrMainReturn = new ArrayList();
			lArrReturn1 = new ArrayList();


//			--------------------------------------------------------------------------------------------------------------------------------------------------------



//			----------------------------------If FundType = 160001 (ConsolidateFund)------------------------------------------------------------------------------------

			if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFund")))
			{
				lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1>  </b> <br> Consolidate Fund  <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				lArrMainReturn = generateConsolidatedRows(lintFinYrId,lStrLangId,lStrFromDt,lStrToDt,false);
				/*ReportColumnVO[] lObjReportColumnVO = lObjReport.getColumnsToDisplay();
				lObjReportColumnVO[3].setHidden("Y");
				lObjReport.setReportColumns(lObjReportColumnVO);
				lObjReport.initializeTreeModel();
				lObjReport.initializeDynamicTreeModel();
				*/
				
			}

//			-----------------------------------If FundType = SurPlusDeficitRevenue---------------------------------------------------------------------------------------

			else if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitRevenue")))
			{
				
				ReportColumnVO lRptClmnVO[] = new ReportColumnVO[30];
				
				
				
				lRptClmnVO = lObjReport.getReportColumns();
				
				
				lRptClmnVO[3].setHidden("true");
	
				lObjReport.setReportColumns(lRptClmnVO);

				lObjReport.setReportName("<h1><U>State Profile Report  </U> </h1>   <br> SurPlusDeficitRevenue <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				
				lObjReport.initializeTreeModel();
				lObjReport.initializeDynamicTreeModel();
				
				
				
				
				ArrayList lArrReturn = null;
				HashMap lhshConsolidatedFund = getConsolidatedFund(lintFinYrId,	lStrLangId, lStrFromDt, lStrToDt);
				
				long longGrantPeriod = 0;
				
				GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
				
				longGrantPeriod = lObjGrantDtlDAOImpl.getGrantPeriod(lStrToDt, lintFinYrId);

				lArrReturn = new ArrayList();
				lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")),false , 0 , longGrantPeriod);

				lArrReturn = new ArrayList();
				lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")),false ,0 , longGrantPeriod);

				lArrReturn = new ArrayList();
				lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitRevenue")),false ,0 , longGrantPeriod);

				

				lArrMainReturn.add(lArrReturn);

			}

//			--------------------------------------------------------------------------------------------------------------------------------------------------------------------

//			-------------------------------------If FundType = SurPlusDeficitCapital--------------------------------------------------------------------------------------------		

			else if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitCapital")))
			{
				
				long longGrantPeriod = 0;
				
				GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
				
				longGrantPeriod = lObjGrantDtlDAOImpl.getGrantPeriod(lStrToDt, lintFinYrId);
				
				lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1>  </b> <br> Surplus Deficit Capital  <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				ArrayList lArrReturn = null;
				HashMap lhshConsolidatedFund = getConsolidatedFund(lintFinYrId,	lStrLangId, lStrFromDt, lStrToDt);

				lArrReturn = new ArrayList();
				lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt")),false ,0 , longGrantPeriod);

				lArrReturn = new ArrayList();
				lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")),false ,0, longGrantPeriod);

				lArrReturn = new ArrayList();
				lArrReturn = setRow(lhshConsolidatedFund , Integer.parseInt(gObjRsrcBndle.getString("SurplusDeficitCapital")),false , 0 , longGrantPeriod);

				lArrMainReturn.add(lArrReturn);
			}

//			------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			/*------------------------------------------If FundType = Net Consolidated Fund-------------------------------------------------------------------------------------------
			 * 												OR 		ConsolidatedFundExpenditure
			 *----------------------------------------------OR		ConsolidatedFundReceipt-------------------------------------------------------------------------------------------				
			 */

			else if( fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("NetConsolidatedFund")) ||fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundExpenditure")) ||fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundReceipt")))
			{
				if( fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("NetConsolidatedFund")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> Net Consolidated Fund  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				else if( fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundExpenditure")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> Consolidated Fund Expenditure  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				else if( fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundReceipt")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> Consolidated Fund Receipt  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );




				List temp = generateConsolidatedRows(lintFinYrId,lStrLangId,lStrFromDt,lStrToDt,false);

				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("NetConsolidatedFund")))
					lArrMainReturn.add(temp.get(temp.size()-1));
				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundExpenditure")))
					lArrMainReturn.add(temp.get(temp.size()-2));
				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("ConsolidatedFundReceipt")))
					lArrMainReturn.add(temp.get(temp.size()-3));

			}


//			----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//			---------------------------------------------If FundType = RevenueReceipt OR CapitalReceipt----------------------------------------------------------------------------------

			else if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")) || fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt")))
			{
				double BudTotal = 0;
				double GrantTotal = 0;
				double CurrTotal = 0;
				double ProgTotal= 0;

				String majorHeadRange = null;
				String lStrlable = null;

				ArrayList lArrSubFund = null;
				ArrayList lArrMjrHdRange = null;

				HashMap lhshMapConRecpAmt = null;
				
				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> Revenue Receipt  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				else if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> Capital Receipt  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );


				//-----------------Generating The First Row , Head Row---------------------------------------------------------------

				lArrMainDataLst = new ArrayList();

				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")))
					lArrMainDataLst.add("<b>I- Revenue Receipt<b>");
				else if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt")))
					lArrMainDataLst.add("<b>I- Capital Receipt<b>");

				for(int k = 0;k<23;k++)
					lArrMainDataLst.add(" ");

				lArrMainReturn.add(lArrMainDataLst);

				//----------------------------------------------------------------------------------------------------
				//----------------------------------------------------------------------------------------------------

				lArrSubFund= (ArrayList) gObjRptQueryDAO.getSubFundMjrRange(fund_type);

				for(int j=0;j<lArrSubFund.size();j++)
				{
					
					HashMap lSubFundHashChart = new HashMap(); 
					
					lhshMapSubFund = (HashMap) lArrSubFund.get(j);
					lArrMainDataLst = new ArrayList();
					lArrMjrHdRange = getMjrHdRange(lhshMapSubFund.get("MjrHdRange").toString());
					lhshMapConRecpAmt = gObjRptQueryDAO.getConsolidatedRcpBudAmt(lArrMjrHdRange,lintFinYrId);

					lStrlable = gObjRptQueryDAO.getFundName(Long.parseLong(lhshMapSubFund.get("FundId").toString()));

					lSubFundHashChart.put("SubFundLabel", lStrlable);
					
					lArrMainDataLst.add((j+1)+")"+lStrlable+"<b>"+ lArrMjrHdRange.toString()+"<b>");
					lArrMainDataLst.add("-"); 
					lArrMainDataLst.add("-"); 
					lArrMainDataLst.add("-");
					BudTotal += Double.parseDouble(lhshMapConRecpAmt.get("ConsolidatedReceiptTotal").toString());

					lArrMainDataLst.add(Math.round(Double.parseDouble(lhshMapConRecpAmt.get("ConsolidatedReceiptTotal").toString())));
					
					lSubFundHashChart.put("subFund_Bud", Math.round(Double.parseDouble(lhshMapConRecpAmt.get("ConsolidatedReceiptTotal").toString())));
					
					glogger.info("budget total ahs been set");

					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");

					lSubFundHashChart.put("subFund_Grant", Math.round(GrantTotal));
					
					glogger.info("grant total ahs been set");


					HashMap lhshCurrDataText = gObjRptQueryDAO.getReceiptData(lArrMjrHdRange, lintFinYrId, lStrFromDt, lStrToDt);

					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					CurrTotal += Double.parseDouble(lhshCurrDataText.get("Receipt_Total").toString());
					//13
					lArrMainDataLst.add(Math.round(Double.parseDouble(lhshCurrDataText.get("Receipt_Total").toString())));
					
					lSubFundHashChart.put("subFund_Curr", Math.round(Double.parseDouble(lhshCurrDataText.get("Receipt_Total").toString())));
					
					glogger.info("current total ahs been set");

					

					HashMap lhshProgDataText = gObjRptQueryDAO.getReceiptData(lArrMjrHdRange, lintFinYrId, "", lStrToDt);

					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					ProgTotal += Double.parseDouble(lhshProgDataText.get("Receipt_Total").toString());
					
					lSubFundHashChart.put("subFund_Prg",Double.parseDouble(lhshProgDataText.get("Receipt_Total").toString()));

					lArrMainDataLst.add(Math.round(Double.parseDouble(lhshProgDataText.get("Receipt_Total").toString())));//15




					glogger.info("Progressive total ahs been set");

					lArrMainDataLst.add("-");
					
					if (Math.round(Double.parseDouble(lhshMapConRecpAmt.get("ConsolidatedReceiptTotal").toString())) != 0)
					{
						long l = ((int) ((Math.round(Double.parseDouble(lhshProgDataText.get("Receipt_Total").toString()))) * 100) / Math.round(Double.parseDouble(lhshMapConRecpAmt.get("ConsolidatedReceiptTotal").toString())));
						if(l != 0)
							lArrMainDataLst.add(l);
						else
							lArrMainDataLst.add("-");
					}
					else
						lArrMainDataLst.add("-");
					
					
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("View");

					if(lArrMjrHdRange.size()==2)
						majorHeadRange = lArrMjrHdRange.get(0)+"~"+lArrMjrHdRange.get(1);
					else
						majorHeadRange = lArrMjrHdRange.get(0)+"~"+lArrMjrHdRange.get(0);

					lArrMainDataLst.add(majorHeadRange);
					lArrMainDataLst.add(lStrFromDt+"~"+lStrToDt+"~"+fund_type+"~"+lStrlable);					

					lArrMainReturn.add(lArrMainDataLst);	
					
					lArrChartSubFund.add(lSubFundHashChart);


				}
				
//				--------------------------------------------------------------------------------------------------------------
//				Generating Rounded Difference Row
//				---------------------------------------------------------------------------------------------------------------				

				lArrMainDataLst = new ArrayList();
				lArrMainDataLst.add("<b>Rounded Difference<b>");
				for(int k = 0;k<20;k++)
				{

					
					if(k==3)
					{
						
						dataStyle = new StyledData();
						dataStyle.setStyles(reportStyleVO);
						dataStyle.setData((Math.round(BudTotal) - BudTotal));
						lArrMainDataLst.add((Math.round(BudTotal) - BudTotal) != 0 ? "<b>" +lnfLong.format((Math.round(BudTotal) - BudTotal))
								 : "-");
						//lArrMainDataLst.add("<b>"+(Math.round(BudTotal) - BudTotal)+"<b>");
						
					}
					else if(k==7)
					{
						
						dataStyle = new StyledData();
						dataStyle.setStyles(reportStyleVO);
						dataStyle.setData((Math.round(GrantTotal) - GrantTotal));
						lArrMainDataLst.add((Math.round(GrantTotal) - GrantTotal) != 0 ? "<b>" +lnfLong.format((Math.round(GrantTotal) - GrantTotal))
								 : "-");
						//lArrMainDataLst.add("<b>"+(Math.round(GrantTotal) - GrantTotal)+"<b>");
						
					}
					else if(k==11)
					{
						dataStyle = new StyledData();
						dataStyle.setStyles(reportStyleVO);
						dataStyle.setData((Math.round(CurrTotal) - CurrTotal));
						lArrMainDataLst.add((Math.round(CurrTotal) - CurrTotal) != 0 ? "<b>" +lnfLong.format((Math.round(CurrTotal) - CurrTotal))
								 : "-");
					//	lArrMainDataLst.add("<b>"+(Math.round(CurrTotal) - CurrTotal)+"<b>");
						
					}
					else if(k==15)
					{
						dataStyle = new StyledData();
						dataStyle.setStyles(reportStyleVO);
						dataStyle.setData((Math.round(ProgTotal) - ProgTotal));
						lArrMainDataLst.add((Math.round(ProgTotal) - ProgTotal) != 0 ? "<b>" +lnfLong.format((Math.round(ProgTotal) - ProgTotal))
								 : "-");
						//lArrMainDataLst.add("<b>"+(Math.round(ProgTotal) - ProgTotal)+"<b>");
						
					}
					else
					{
						lArrMainDataLst.add("-");
					}
				}
				
				lArrMainDataLst.add(" ");
				lArrMainDataLst.add(" ");
				lArrMainDataLst.add(" ");

			//	lArrMainReturn.add(lArrMainDataLst);	
				
				

//				--------------------------------------------------------------------------------------------------------------
//				Generating  Total Row
//				---------------------------------------------------------------------------------------------------------------				

				ArrayList lArrMainDataLst1 = new ArrayList();
				lArrMainDataLst1.add("<b>Total<b>");
				for(int k = 0;k<16;k++)
				{

					
					if(k==3)
					{
						lArrMainDataLst1.add("<b>"+Math.round(BudTotal)+"<b>");
						
					}
					else if(k==7)
					{
						lArrMainDataLst1.add("<b>"+Math.round(GrantTotal)+"<b>");
						
					}
					else if(k==11)
					{
						lArrMainDataLst1.add("<b>"+Math.round(CurrTotal)+"<b>");
						
					}
					else if(k==15)
					{
						lArrMainDataLst1.add("<b>"+Math.round(ProgTotal)+"<b>");
						
					}
					else
					{
						lArrMainDataLst1.add("-");
					}
				}
				
				lArrMainDataLst1.add("-");
				
				if (Math.round(BudTotal) != 0)
				{
					long l = ((int) ((Math.round(ProgTotal)) * 100) / Math.round(BudTotal));
					if(l != 0)
						lArrMainDataLst1.add(l);
					else
						lArrMainDataLst1.add("-");
				}
				else
					lArrMainDataLst1.add("-");
				
				lArrMainDataLst1.add("-");
				lArrMainDataLst1.add("-");
				lArrMainDataLst1.add(" ");
				lArrMainDataLst1.add(" ");
				lArrMainDataLst1.add(" ");

				lArrMainReturn.add(lArrMainDataLst1);
				
				lArrMainReturn.add(lArrMainDataLst);	
			}
//			--------------------------------------------------------------------------------------------------------------------
//			Fund Type = Contigency(NET) OR Public Account(NET)
//			--------------------------------------------------------------------------------------------------------------------		

			else if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")) || fund_type == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
			{
				
				String lStrFundNm = null;
				ArrayList lArrReturn = new ArrayList();
				HashMap lhashMap = getContingencyPublicFund(lintFinYrId,lStrLangId, lStrFromDt, lStrToDt,(int)fund_type);
				
				if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> Contigency (NET)  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				else if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> PublicAccount (NET)  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				
				
				
				if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
					lStrFundNm = "ConFund";
				else if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
						lStrFundNm = "PubFund";	
					

				ArrayList lArrMjrHdRange = getMjrHdRange(gObjRptQueryDAO.getFundRange(fund_type));
				String majorHeadRange = "";
	//------------------------------------added by today night------------------------------------------------------
				
				
				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
					lArrReturn.add("<b>I- Contigency (NET) <b>");
				else if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
					lArrReturn.add("<b>I- Public Account (NET) <b>");

				
				
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) != 0 ? gdbBudPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) != 0 ? gdbBudNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) != 0 ? gdbBudCSSTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) : "-");

				glogger.info("Budget values has been set");
				 dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbBudTotalTotal += Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()))) != 0 ? dataStyle : "-");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString())))!= 0 ? gdbGrantPLTotal += (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) != 0 ? gdbGrantNPTotal+= (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) != 0 ? gdbGrantCSSTotal+= (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) : "-");
				glogger.info("grant values has been set");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbGrantTotalTotal +=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))) != 0 ? dataStyle : "-");
				glogger.info("Grant Total has been set");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) != 0 ? gdbCurrPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) != 0 ? gdbCurrNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) != 0 ? gdbCurrCSSTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) : "-");
				glogger.info("Current values has been set");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))) : "-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbCurrTotalTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));

				glogger.info("Current Total values has been set");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) != 0 ? gdbPrgPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) != 0 ? gdbPrgNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) : "-");
				lArrReturn.add(( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) != 0 ? gdbPrgTotalTotal+=( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) : "-");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))) != 0 ? dataStyle : "-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbPrgTotalTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));
				
				
				glogger.info("Progressive values ahas been set");

				
				
				
				
				
				
				
				
				
				
				
				
				
				
	//---------------------------------------------------------------------------------------------------------------
				
				int size = lArrReturn.size();
				
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				
				lArrReturn.add("View");

				if(lArrMjrHdRange.size()==2)
				{
					
					majorHeadRange = lArrMjrHdRange.get(0).toString()+"~"+lArrMjrHdRange.get(1).toString();
				}
				else
					majorHeadRange = lArrMjrHdRange.get(0).toString()+"~"+lArrMjrHdRange.get(0).toString();

				lArrReturn.add(majorHeadRange);
				lArrReturn.add(lStrFromDt+"~"+lStrToDt+"~"+fund_type+"~MajorHead Range");		
				
				lArrMainReturn.add(lArrReturn);
				
				//---------------------------------------------------------------------------------------------------
				//			Total Row will be added
				//---------------------------------------------------------------------------------------------------
				
				lArrReturn = new ArrayList();
				
				
				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
					lArrReturn.add("<b>Total <b>");
				else if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
					lArrReturn.add("<b>Total <b>");

				
				
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) != 0 ? gdbBudPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) != 0 ? gdbBudNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) != 0 ? gdbBudCSSTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) : "-");

				glogger.info("Budget values has been set");
				 dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbBudTotalTotal += Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()))) != 0 ? dataStyle : "-");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString())))!= 0 ? gdbGrantPLTotal += (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) != 0 ? gdbGrantNPTotal+= (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) != 0 ? gdbGrantCSSTotal+= (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) : "-");
				glogger.info("grant values has been set");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbGrantTotalTotal +=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))) != 0 ? dataStyle : "-");
				glogger.info("Grant Total has been set");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) != 0 ? gdbCurrPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) != 0 ? gdbCurrNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) != 0 ? gdbCurrCSSTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) : "-");
				glogger.info("Current values has been set");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))) != 0 ? dataStyle : "-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbCurrTotalTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));

				glogger.info("Current Total values has been set");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) != 0 ? gdbPrgPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) != 0 ? gdbPrgNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) : "-");
				lArrReturn.add(( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) != 0 ? gdbPrgTotalTotal+=( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) : "-");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))) != 0 ? dataStyle : "-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbPrgTotalTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));
				
				
				glogger.info("Progressive values ahas been set");

				

				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				lArrReturn.add("");
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				lArrReturn.add("-");
				
				lArrMainReturn.add(lArrReturn);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				//--------------------------------------------------------------------------------------------------
				//			Rounded Difference Row will be added
				//--------------------------------------------------------------------------------------------------
				
				lArrReturn = new ArrayList();
				
				NumberFormat lnfLong1 = NumberFormat.getNumberInstance();
				lnfLong1.setGroupingUsed(false);
				lnfLong1.setMaximumFractionDigits(3);
				lnfLong1.setMinimumFractionDigits(3);
				
				
				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
					lArrReturn.add("<b>Rounded Difference <b>");
				else if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
					lArrReturn.add("<b>Rounded Difference <b>");

				
				
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString())))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString())))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString())))) : "-");
				
				
				glogger.info("Budget values has been set");
				 dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()))) != 0 ? dataStyle : "-");

				
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString())))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString())))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString())))) : "-");
				
				
				
				
				
				glogger.info("grant values has been set");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))));
				
				dataStyle.setData(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()));
				
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString())) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))) : "-");
				
				glogger.info("Grant Total has been set");

				

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString())))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString())))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString())))) : "-");
				
				
				
				
				
				
				glogger.info("Current values has been set");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				
				dataStyle.setData(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()));
				
				
				
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString())) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))) : "-");
				
				

				glogger.info("Current Total values has been set");

			
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString())))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString())))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) != 0 ? lnfLong1.format((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString())) - (Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString())))) : "-");
			
				
				
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
			
				
				dataStyle.setData(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()));
				
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString())) != 0 ? lnfLong1.format(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))- Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString())) : "-");
				
				
				
				glogger.info("Progressive values ahas been set");

				

				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				lArrReturn.add("");
				lArrReturn.add("-");
				lArrReturn.add("-");
				lArrReturn.add("-");
				
				lArrReturn.add("-");
				
				lArrMainReturn.add(lArrReturn);
				
				
				
				
				
				
				
				
				
				
				
				
				
				/*//---------------------------------------------------------------------------------------
				
				
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) != 0 ? gdbBudPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) != 0 ? gdbBudNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) != 0 ? gdbBudCSSTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_CSS").toString()))) : "-");

				glogger.info("Budget values has been set");
				StyledData dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbBudTotalTotal += Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString())));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Bud_Total").toString()))) != 0 ? dataStyle : "-");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString())))!= 0 ? gdbGrantPLTotal += (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) != 0 ? gdbGrantNPTotal+= (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) != 0 ? gdbGrantCSSTotal+= (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_CSS").toString()))) : "-");
				glogger.info("grant values has been set");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbGrantTotalTotal +=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Grant_Total").toString()))) != 0 ? dataStyle : "-");
				glogger.info("Grant Total has been set");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) != 0 ? gdbCurrPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) != 0 ? gdbCurrNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_NP").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) != 0 ? gdbCurrCSSTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_CSS").toString()))) : "-");
				glogger.info("Current values has been set");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))) != 0 ? (Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))) : "-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbCurrTotalTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Curr_Total").toString()))));

				glogger.info("Current Total values has been set");

				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) != 0 ? gdbPrgPLTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_PL").toString()))) : "-");
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) != 0 ? gdbPrgNPTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_NP").toString()))) : "-");
				lArrReturn.add(( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) != 0 ? gdbPrgTotalTotal+=( Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_CSS").toString()))) : "-");
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));
				lArrReturn.add((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))) != 0 ? dataStyle : "-");
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(gdbPrgTotalTotal+=(Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))));
				
				
				glogger.info("Progressive values ahas been set");



				lArrReturn.add("-");

				if (gBudTotalRevRecp != 0)
				{
					long l = ((int) ((Math.round(Double.parseDouble(lhashMap.get(lStrFundNm + "_Prg_Total").toString()))) * 100) / gBudTotalRevRecp);
					if(l != 0)
							lArrReturn.add(l);
					else
						lArrReturn.add("-");
				}
				else
					lArrReturn.add("-");

				lArrReturn.add("-");
				lArrReturn.add("-");
				
					lArrReturn.add("View");
				
				lArrReturn.add(gStrFromDate);
				lArrReturn.add(gStrToDate);
				
				*/
				
				
				
				
				//----------------------------------------------------------------------------------------
				
				
				/*ReportColumnVO[] lObjReportColumnVO = lObjReport.getColumnsToDisplay();
				lObjReportColumnVO[21].setHidden("Y");
				lObjReport.setReportColumns(lObjReportColumnVO);
				glogger.info("the name of column that i want to hide is :- " + lObjReportColumnVO[21].getColumnName());
				lObjReport.setParameterValue("clm_"+lObjReportColumnVO[21].getColumnName(), Boolean.TRUE);
				lObjReport.initializeTreeModel();
				lObjReport.initializeDynamicTreeModel();*/
				
				
				
				
				

			}
//			--------------------------------------------------------------------------------------------------------------------------------		
//			--------------------------------------------------------------------------------------------------------------------------------
//			Fund Type = Revenue Expenditure OR Capital Expenditure
//			----------------------------------------------------------------------------------------------------------------------------------

			else if(fund_type == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")) || fund_type == Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")))
			{
				ArrayList lArrMjrHdRange = null;

				HashMap lhshMapRevExpAmt = null;
				HashMap lhshGrantData = null;
				HashMap lhshCurrDataText = null;
				HashMap lhshPrgDataText = null;

				double lPLTotalb = 0.0;
				double lNPTotalb = 0.0;
				double lCSSTotalb = 0.0;
				double lCurrTotalb = 0.0;
				
				double lPLTotalcr = 0.0;
				double lNPTotalcr = 0.0;
				double lCSSTotalcr = 0.0;
				double lCurrTotalcr = 0.0;
				
				double lPLTotalpr = 0.0;
				double lNPTotalpr = 0.0;
				double lCSSTotalpr = 0.0;
				double lCurrTotalpr = 0.0;
				
				double lPLTotalR = 0.0;
				double lNPTotalR = 0.0;
				double lCSSTotalR = 0.0;
				double lCurrTotalR = 0.0;
				 
				double grant_pl = 0.0; 
				double grant_np = 0.0;
				double grant_css = 0.0;
				double grant_total = 0.0; 

				double value1 = 0;

				String majorHeadRange="";
				
				GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
				HashMap lhshDateMap1 = getDates(lStrFromDt, lStrToDt);
				Long ldiffDays1 = Long.parseLong(lhshDateMap1.get("difference").toString());
				
				
				
				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> Revenue Expenditure  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				else if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")))
					lObjReport.setReportName("<h1><b><U>State Profile Report  </U> <b></h1> <br> Capital Expenditure  </b> <br> From : " +lStrFromDt +" To: " + lStrToDt  );
				
				

				//-----------------Generating The First Row , Head Row---------------------------------------------------------------

				lArrMainDataLst = new ArrayList();

				if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")))
					lArrMainDataLst.add("<b>I- Revenue Expenditure <b>");
				else if(fund_type ==  Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")))
					lArrMainDataLst.add("<b>I- Capital Expenditure<b>");

				for(int k = 0;k<23;k++)
					lArrMainDataLst.add(" ");

				lArrMainReturn.add(lArrMainDataLst);

				//----------------------------------------------------------------------------------------------------
				//----------------------------------------------------------------------------------------------------

				lArrMainDataLst = new ArrayList();
				lArrMjrHdRange = getMjrHdRange(gObjRptQueryDAO.getFundRange(fund_type));
				glogger.info("Major Head Range of this type =---> " + lArrMjrHdRange.toString() );

				lhshMapRevExpAmt = gObjRptQueryDAO.getConsolidatedExpBudAmt(lArrMjrHdRange, lintFinYrId);
				lhshGrantData = gObjRptQueryDAO.getGrantAmt(lArrMjrHdRange, lintFinYrId, "en_US");
				lhshCurrDataText = gObjRptQueryDAO.getExpenditureData(lArrMjrHdRange, 20, lStrFromDt, lStrToDt);
				lhshPrgDataText = gObjRptQueryDAO.getExpenditureData(lArrMjrHdRange, 20, "", lStrToDt);

				lPLTotalb = Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_PL_CH").toString());
				lPLTotalb = lPLTotalb	+ Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_PL_VT").toString());
				lNPTotalb = Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_NP_CH").toString());
				lNPTotalb = lNPTotalb	+ Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_NP_VT").toString());
				lCSSTotalb = gObjRptQueryDAO.getCSSAmount(lArrMjrHdRange,lintFinYrId);
				lNPTotalb = lNPTotalb - lCSSTotalb;

				lArrMainDataLst.add("<b>"+lArrMjrHdRange.toString()+"<b>");

				lArrMainDataLst.add(Math.round(lPLTotalb)!=0 ? Math.round(lPLTotalb) : "-" );
				lArrMainDataLst.add(Math.round(lNPTotalb)!=0 ? Math.round(lNPTotalb) : "-" );
				lArrMainDataLst.add(Math.round(lCSSTotalb)!=0 ? Math.round(lCSSTotalb) : "-" );
				lArrMainDataLst.add(Math.round(lPLTotalb+lNPTotalb+lCSSTotalb)!=0 ? Math.round(lPLTotalb+lNPTotalb+lCSSTotalb) : "-" );
				
				grant_pl = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "PL", lArrMjrHdRange);
				grant_np = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "NP", lArrMjrHdRange);
				grant_css = lObjGrantDtlDAOImpl.getGrantAmtForMjrHd(lintFinYrId, "CSS", lArrMjrHdRange);
				grant_total = grant_pl + grant_np + grant_css;

				lArrMainDataLst.add((value1 = grant_pl) != 0 ? Math.round(value1) : "-");
				lArrMainDataLst.add((value1 = grant_np) != 0 ? Math.round(value1) : "-");
				lArrMainDataLst.add((value1 = grant_css) != 0 ? Math.round(value1) : "-");
				lArrMainDataLst.add((value1 = grant_total) != 0 ? Math.round(value1) : "-");


				lArrMainDataLst.add((lPLTotalcr = ((Double.parseDouble(lhshCurrDataText.get("Exp_PL").toString())))) != 0 ? Math.round(lPLTotalcr) : "-");
				lArrMainDataLst.add((lNPTotalcr = ((Double.parseDouble(lhshCurrDataText.get("Exp_NP").toString())))) != 0 ? Math.round(lNPTotalcr) : "-");
				lArrMainDataLst.add((lCSSTotalcr = ((Double.parseDouble(lhshCurrDataText.get("Exp_CSS").toString())))) != 0 ? Math.round(lCSSTotalcr) : "-");
				lArrMainDataLst.add((Math.round((lPLTotalcr)+(lNPTotalcr)+(lCSSTotalcr))) != 0 ? (Math.round(((lPLTotalcr)+(lNPTotalcr)+(lCSSTotalcr)))) : "-" );

				lArrMainDataLst.add((lPLTotalpr = ((Double.parseDouble(lhshPrgDataText.get("Exp_PL").toString())))) != 0 ? Math.round(lPLTotalpr) : "-");
				lArrMainDataLst.add((lNPTotalpr = ((Double.parseDouble(lhshPrgDataText.get("Exp_NP").toString())))) != 0 ? Math.round(lNPTotalpr) : "-");
				lArrMainDataLst.add((lCSSTotalpr = ((Double.parseDouble(lhshPrgDataText.get("Exp_CSS").toString())))) != 0 ? Math.round(lCSSTotalpr) : "-");
				lArrMainDataLst.add((Math.round(((lPLTotalpr)+(lNPTotalpr)+(lCSSTotalpr)))) != 0 ? (Math.round(((lPLTotalpr)+(lNPTotalpr)+(lCSSTotalpr)))) : "-" );

				if ((Math.round(grant_total)) != 0 & ldiffDays1 !=0)	
					lArrMainDataLst.add((int) ((Math.round((lPLTotalcr+lNPTotalcr+lCSSTotalcr))) * 100 * 182)	/ ((Math.round(grant_total)) * (ldiffDays1+1)));
				else
					lArrMainDataLst.add("-");
		
				
				if (Math.round(lPLTotalb+lNPTotalb+lCSSTotalb) != 0)
				{
					long l = ((int) (((Math.round((lPLTotalpr+lNPTotalpr+lCSSTotalpr)))) * 100) / Math.round(lPLTotalb+lNPTotalb+lCSSTotalb));
					if(l != 0)
						lArrMainDataLst.add(l);
					else
						lArrMainDataLst.add("-");
				}
				else
					lArrMainDataLst.add("-");
				
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("View");

				if(lArrMjrHdRange.size()==2)
				{
					//majorHeadRange = "2011~2030";
					majorHeadRange = lArrMjrHdRange.get(0).toString()+"~"+lArrMjrHdRange.get(1).toString();
				}
				else
					majorHeadRange = lArrMjrHdRange.get(0).toString()+"~"+lArrMjrHdRange.get(0).toString();

				lArrMainDataLst.add(majorHeadRange);
				lArrMainDataLst.add(lStrFromDt+"~"+lStrToDt+"~"+fund_type+"~MajorHead Range");			

				lArrMainReturn.add(lArrMainDataLst);
				
				
//				--------------------------------------------------------------------------------------------------------------
//				Generating Rounded Difference Row
//				---------------------------------------------------------------------------------------------------------------				

				lArrMainDataLst = new ArrayList();
				lArrMainDataLst.add("<b>Rounded Difference<b>");
				
				lArrMainDataLst.add((Math.round(lPLTotalb) - lPLTotalb) != 0 ? "<b>" +lnfLong.format((Math.round(lPLTotalb) - lPLTotalb))
						 : "-");
				lArrMainDataLst.add((Math.round(lNPTotalb) - lNPTotalb) != 0 ? "<b>" +lnfLong.format((Math.round(lNPTotalb) - lNPTotalb))
						 : "-");
				lArrMainDataLst.add((Math.round(lCSSTotalb) - lCSSTotalb) != 0 ? "<b>" +lnfLong.format((Math.round(lCSSTotalb) - lCSSTotalb))
						 : "-");
				lArrMainDataLst.add((Math.round(lPLTotalb+lNPTotalb+lCSSTotalb) - (lPLTotalb+lNPTotalb+lCSSTotalb)) != 0 ? "<b>" +lnfLong.format((Math.round(lPLTotalb+lNPTotalb+lCSSTotalb) - (lPLTotalb+lNPTotalb+lCSSTotalb)))
						 : "-");
				
				
				
				
				
				double value2 = 0.0;
				
				value2 = (Math.round(grant_pl)) - (grant_pl);
				lArrMainDataLst.add((value2  != 0 ? "<b>" +lnfLong.format(value2) : "-"));
				
				value2 = (Math.round(grant_np)) - (grant_np);
				lArrMainDataLst.add((value2  != 0 ? "<b>" +lnfLong.format(value2) : "-"));
				
				value2 = (Math.round(grant_css)) - (grant_css);
				lArrMainDataLst.add((value2  != 0 ? "<b>" +lnfLong.format(value2) : "-"));
				
				value2 = (Math.round(grant_total)) - (grant_total);
				lArrMainDataLst.add((value2  != 0 ? "<b>" +lnfLong.format(value2) : "-"));
				

				
				lPLTotalR = (Math.round(Double.parseDouble(lhshCurrDataText.get("Exp_PL").toString()))) - (Double.parseDouble(lhshCurrDataText.get("Exp_PL").toString()));
				lArrMainDataLst.add((lPLTotalR  != 0 ? "<b>" +lnfLong.format(lPLTotalR) : "-"));
				
				lNPTotalR = (Math.round(Double.parseDouble(lhshCurrDataText.get("Exp_NP").toString()))) - (Double.parseDouble(lhshCurrDataText.get("Exp_NP").toString()));
				lArrMainDataLst.add((lNPTotalR  != 0 ? "<b>" +lnfLong.format(lNPTotalR) : "-"));
				
				lCSSTotalR = (Math.round(Double.parseDouble(lhshCurrDataText.get("Exp_CSS").toString()))) - (Double.parseDouble(lhshCurrDataText.get("Exp_CSS").toString()));
				lArrMainDataLst.add((lCSSTotalR  != 0 ? "<b>" +lnfLong.format(lCSSTotalR) : "-"));
				
				value2 = (Math.round(lPLTotalR + lNPTotalR + lCSSTotalR) - ((lPLTotalR + lNPTotalR + lCSSTotalR)));
				lArrMainDataLst.add((value2  != 0 ? "<b>" +lnfLong.format(value2) : "-"));
				
				
				
				lPLTotalR = (Math.round(Double.parseDouble(lhshPrgDataText.get("Exp_PL").toString()))) - (Double.parseDouble(lhshPrgDataText.get("Exp_PL").toString()));
				lArrMainDataLst.add((lPLTotalR  != 0 ? "<b>" +lnfLong.format(lPLTotalR) : "-"));
				
				lNPTotalR = (Math.round(Double.parseDouble(lhshPrgDataText.get("Exp_NP").toString()))) - (Double.parseDouble(lhshPrgDataText.get("Exp_NP").toString()));
				lArrMainDataLst.add((lNPTotalR  != 0 ? "<b>" +lnfLong.format(lNPTotalR) : "-"));
				
				lCSSTotalR = (Math.round(Double.parseDouble(lhshPrgDataText.get("Exp_CSS").toString()))) - (Double.parseDouble(lhshPrgDataText.get("Exp_CSS").toString()));
				lArrMainDataLst.add((lCSSTotalR  != 0 ? "<b>" +lnfLong.format(lCSSTotalR) : "-"));
				
				value2 = (Math.round(lPLTotalR + lNPTotalR + lCSSTotalR) - ((lPLTotalR + lNPTotalR + lCSSTotalR)));
				lArrMainDataLst.add((value2  != 0 ? "<b>" +lnfLong.format(value2) : "-"));
				
				
				
			
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("");

				if(lArrMjrHdRange.size()==2)
				{
					//majorHeadRange = "2011~2030";
					majorHeadRange = lArrMjrHdRange.get(0).toString()+"~"+lArrMjrHdRange.get(1).toString();
				}
				else
					majorHeadRange = lArrMjrHdRange.get(0).toString()+"~"+lArrMjrHdRange.get(0).toString();

				lArrMainDataLst.add(majorHeadRange);
				lArrMainDataLst.add(lStrFromDt+"~"+lStrToDt+"~"+fund_type+"~MajorHead Range");			


				//lArrMainReturn.add(lArrMainDataLst);	
				
				

//				--------------------------------------------------------------------------------------------------------------
//				Generating  Total Row
//				---------------------------------------------------------------------------------------------------------------				

				ArrayList lArrMainDataLst1 = new ArrayList();
				lArrMainDataLst1.add("<b>Total<b>");
				
				lArrMainDataLst1.add(Math.round(lPLTotalb)!=0 ? "<b>"+Math.round(lPLTotalb) : "-" );
				lArrMainDataLst1.add(Math.round(lNPTotalb)!=0 ? "<b>"+Math.round(lNPTotalb) : "-" );
				lArrMainDataLst1.add(Math.round(lCSSTotalb)!=0 ? "<b>"+Math.round(lCSSTotalb) : "-" );
				lArrMainDataLst1.add(Math.round(lPLTotalb+lNPTotalb+lCSSTotalb)!=0 ? "<b>"+ Math.round(lPLTotalb+lNPTotalb+lCSSTotalb) : "-" );

			/*	lArrMainDataLst.add((value1 = (Math.round(Double.parseDouble(lhshGrantData.get("GrantAmt_PL").toString())))) != 0 ? lArrMainDataLst.add((value1 = grant_pl) != 0 ? Math.round(value1) : "-");
				lArrMainDataLst.add((value1 = grant_np) != 0 ? Math.round(value1) : "-");
				lArrMainDataLst.add((value1 = grant_css) != 0 ? Math.round(value1) : "-");
				lArrMainDataLst.add((value1 = grant_total) != 0 ? Math.round(value1) : "-");value1 : "-");
				lArrMainDataLst.add((value1 = (Math.round(Double.parseDouble(lhshGrantData.get("GrantAmt_NP").toString())))) != 0 ?  "<b>"+value1 : "-");
				lArrMainDataLst.add((value1 = (Math.round(Double.parseDouble(lhshGrantData.get("GrantAmt_CSS").toString())))) != 0 ?  "<b>"+value1 : "-");
				lArrMainDataLst.add((value1 = (Math.round(Double.parseDouble(lhshGrantData.get("GrantAmt_Total").toString())))) != 0 ?  "<b>"+value1 : "-");*/
				
				lArrMainDataLst1.add((value1 = grant_pl) != 0 ? "<b>"+Math.round(value1) : "-");
				lArrMainDataLst1.add((value1 = grant_np) != 0 ? "<b>"+Math.round(value1) : "-");
				lArrMainDataLst1.add((value1 = grant_css) != 0 ? "<b>"+Math.round(value1) : "-");
				lArrMainDataLst1.add((value1 = grant_total) != 0 ? "<b>"+Math.round(value1) : "-");


				lArrMainDataLst1.add((lPLTotalcr = (Math.round(Double.parseDouble(lhshCurrDataText.get("Exp_PL").toString())))) != 0 ?  "<b>"+Math.round(lPLTotalcr) : "-");
				lArrMainDataLst1.add((lNPTotalcr = (Math.round(Double.parseDouble(lhshCurrDataText.get("Exp_NP").toString())))) != 0 ?  "<b>"+Math.round(lNPTotalcr) : "-");
				lArrMainDataLst1.add((lCSSTotalcr = (Math.round(Double.parseDouble(lhshCurrDataText.get("Exp_CSS").toString())))) != 0 ?  "<b>"+Math.round(lCSSTotalcr) : "-");
				lArrMainDataLst1.add((Math.round((lPLTotalcr+lNPTotalcr+lCSSTotalcr))) != 0 ?  "<b>"+(Math.round((lPLTotalcr+lNPTotalcr+lCSSTotalcr))) : "-" );

				lArrMainDataLst1.add((lPLTotalpr = (Math.round(Double.parseDouble(lhshPrgDataText.get("Exp_PL").toString())))) != 0 ?  "<b>"+Math.round(lPLTotalpr) : "-");
				lArrMainDataLst1.add((lNPTotalpr = (Math.round(Double.parseDouble(lhshPrgDataText.get("Exp_NP").toString())))) != 0 ?  "<b>"+Math.round(lNPTotalpr) : "-");
				lArrMainDataLst1.add((lCSSTotalpr = (Math.round(Double.parseDouble(lhshPrgDataText.get("Exp_CSS").toString())))) != 0 ?  "<b>"+Math.round(lCSSTotalpr) : "-");
				lArrMainDataLst1.add((Math.round((lPLTotalpr+lNPTotalpr+lCSSTotalpr))) != 0 ?  "<b>"+(Math.round((lPLTotalpr+lNPTotalpr+lCSSTotalpr))) : "-" );

				if ((Math.round(grant_total)) != 0 & ldiffDays1 !=0)	
					lArrMainDataLst1.add((int) ((Math.round((lPLTotalcr+lNPTotalcr+lCSSTotalcr))) * 100 * 182)	/ ((Math.round(grant_total)) * (ldiffDays1+1)));
				else
					lArrMainDataLst1.add("-");
		
				
				if (Math.round(lPLTotalb+lNPTotalb+lCSSTotalb) != 0)
				{
					long l = ((int) (((Math.round((lPLTotalpr+lNPTotalpr+lCSSTotalpr)))) * 100) / Math.round(lPLTotalb+lNPTotalb+lCSSTotalb));
					if(l != 0)
						lArrMainDataLst1.add(l);
					else
						lArrMainDataLst1.add("-");
				}
				else
					lArrMainDataLst1.add("-");
				
				lArrMainDataLst1.add("-");
				lArrMainDataLst1.add("-");

				lArrMainDataLst1.add("");

				if(lArrMjrHdRange.size()==2)
				{
					//majorHeadRange = "2011~2030";
					majorHeadRange = lArrMjrHdRange.get(0).toString()+"~"+lArrMjrHdRange.get(1).toString();
				}
				else
					majorHeadRange = lArrMjrHdRange.get(0).toString()+"~"+lArrMjrHdRange.get(0).toString();

				lArrMainDataLst1.add(majorHeadRange);
				lArrMainDataLst1.add(lStrFromDt+"~"+lStrToDt+"~"+fund_type+"~MajorHead Range");			

				

				lArrMainReturn.add(lArrMainDataLst1);
				
				lArrMainReturn.add(lArrMainDataLst);
			}


			return lArrMainReturn;
		}

		public List getMajorHeadWiseStateProfileReport(ReportVO lObjReport)
		{


//			----------------------------------------------------------------------------------------------------------------------------
//			Variable Declaration of    MajorHeadWiseStateProfileReport
//			---------------------------------------------------------------------------------------------------------------------------
			List lArrMainDataLst = null;
			List lArrMainReturn = new ArrayList();
			List lArrListMjrHD =null;
			
			NumberFormat lnfLong = NumberFormat.getNumberInstance();
			lnfLong.setGroupingUsed(false);
			lnfLong.setMaximumFractionDigits(3);
			lnfLong.setMinimumFractionDigits(3);




			HashMap<String,Double> lHashBudData = null;
			HashMap<String,Double> lHashBudCSSData = null;
			HashMap<String,Double> lHashCurrData = null;
			HashMap<String,Double> lHashPrgData = null;
			HashMap<String,Double> lHashCurrDataExp = null;
			HashMap<String,Double> lHashPrgDataExp = null;
			HashMap<String,Double> lHashGrantData = null;
			HashMap<String,Double> lHashGrantData_pl = null;
			HashMap<String,Double> lHashGrantData_np = null;
			HashMap<String,Double> lHashGrantData_css = null;
			
			

			int lStartLimitRange = 0;
			int lintFinYearId = 0;
			int lfundType = 0;
			int lEndLimitRange =0;



			String lStrLangId = null;
			String MjrHdRange = null;
			String dateString = null;
			String lStrFromDate = null;
			String lStrToDate = null;

			double total_bud_pl = 0.0;
			double total_bud_np = 0.0;
			double total_bud_css = 0.0;
			double total_bud = 0.0;
			double total_grant_pl = 0.0;
			double total_grant_np = 0.0;
			double total_grant_css = 0.0;
			double total_grant = 0.0;
			double total_curr_pl = 0.0;
			double total_curr_np = 0.0;
			double total_curr_css = 0.0;
			double total_curr = 0.0;
			double total_prg_pl = 0.0;
			double total_prg_np = 0.0;
			double total_prg_css = 0.0;
			double total_prg = 0.0;

			double value= 0;		
			double value1= 0;
			double value2= 0;
			double value3= 0;


			StringTokenizer lStrTokenRange = null;
			StringTokenizer lStrTokenDate = null;

			DSSQueryDAO gObjRptQueryDAO = null;

			ArrayList lArrListMjrHD1 = null;

//			-------------------------------------------------------------------------------------------------------------------

//			--------------------------------------------------------------------------------------------------------------------
//			Parameter Fetching & Variable Initialization   of  MajorHeadWiseStateProfileReport
//			---------------------------------------------------------------------------------------------------------------------

			lStrLangId = lObjReport.getLangId();

			MjrHdRange = (String)lObjReport.getParameterValue("arraylist");
			dateString = (String)lObjReport.getParameterValue("dateString");

			gObjRptQueryDAO = (DSSQueryDAO) DAOFactory.Create("com.tcs.sgv.dss.dao.DSSQueryDAOImpl");
			gObjRptQueryDAO.setSessionFactory(lObjSessionFactory);

			lStrTokenRange = new StringTokenizer(MjrHdRange,"~");
			lStartLimitRange = Integer.parseInt(lStrTokenRange.nextToken());
			lEndLimitRange = Integer.parseInt(lStrTokenRange.nextToken());



			lStrTokenDate = new StringTokenizer(dateString,"~");
			lStrFromDate = (lStrTokenDate.nextToken().toString());
			lStrToDate = (lStrTokenDate.nextToken().toString());
			lintFinYearId = gObjRptQueryDAO .getFinancialYear(lStrFromDate, lStrToDate);
			lfundType = Integer.parseInt((lStrTokenDate.nextToken().toString())); 
			String lStrReportHeading = lStrTokenDate.nextToken().toString();

//			-----------------------------------------------------------------------------------------------------------------
//			fundType = Revenue Receipt OR Capital Receipt
//			------------------------------------------------------------------------------------------------------------------

			if(lfundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")) || lfundType ==  Integer.parseInt(gObjRsrcBndle.getString("CapitalReceipt")))
			{

				if(lfundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueReceipt")))
					lObjReport.setReportName("<b><U>State Profile Report - MajorHead Wise</U> <b> <br> Revenue Receipt -  " + lStrReportHeading + " ["+lStartLimitRange + "-" + lEndLimitRange +"]<br> From : "+ lStrFromDate + " To: " + lStrToDate);
				else
					lObjReport.setReportName("<b><U>State Profile Report - MajorHead Wise</U> <b> <br> Capital Receipt -  " + lStrReportHeading + "  ["+lStartLimitRange + "-" + lEndLimitRange +"] <br> From : "+ lStrFromDate + " To: " + lStrToDate);

				lHashBudData = gObjRptQueryDAO.getMjrHDWiseBudRcptData(lStartLimitRange,lEndLimitRange,lintFinYearId,"en_US");
				lHashCurrData = gObjRptQueryDAO.getMjrHDWiseCurrRcptData(lStartLimitRange,lEndLimitRange,lintFinYearId,"en_US",lStrFromDate,lStrToDate);
				lHashPrgData = gObjRptQueryDAO.getMjrHDWiseCurrRcptData(lStartLimitRange,lEndLimitRange,lintFinYearId,"en_US","",lStrToDate);


				for(int i = lStartLimitRange ; i <= lEndLimitRange ; i++)
				{

					lArrMainDataLst = new ArrayList();

					lArrMainDataLst.add(getHeadName(i));
					//	Budget Data  
					lArrMainDataLst.add((value =(Math.round(lHashBudData.get(i+"PL"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashBudData.get(i+"NP"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashBudData.get(i+"CSS"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashBudData.get(i+"Total"))))!= 0 ? Math.round(value) : "-" );
					//	Budget Data 
					//	Grant Data 
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					// 	Grant Data
					//	Current Data
					lArrMainDataLst.add((value =(Math.round(lHashCurrData.get(i+"PL"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashCurrData.get(i+"NP"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashCurrData.get(i+"CSS"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashCurrData.get(i+"Total"))))!= 0 ? Math.round(value) : "-" );
					//	Current Data
					//	Progressive Data
					lArrMainDataLst.add((value =(Math.round(lHashPrgData.get(i+"PL"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashPrgData.get(i+"NP"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashPrgData.get(i+"CSS"))))!= 0 ? Math.round(value) : "-" );
					lArrMainDataLst.add((value =(Math.round(lHashPrgData.get(i+"Total"))))!= 0 ? Math.round(value) : "-" );
					//	Progressive Data

					lArrMainDataLst.add("-");
					
					if (Math.round(lHashBudData.get(i+"Total")) != 0)
					{
						long l = ((int) ((Math.round(lHashPrgData.get(i+"Total")))) * 100) / Math.round(lHashBudData.get(i+"Total"));
						if(l != 0)
							lArrMainDataLst.add(l);
						else
							lArrMainDataLst.add("-");
					}
					else
						lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");

					lArrMainReturn.add(lArrMainDataLst);
				}

//				----------------------------------------------------------------------------------------------------------
//				All rows has been added to ArrayList
//				----------------------------------------------------------------------------------------------------------
				

//				----------------------------Rounded Difference Row will be generated---------------------------------------------------
//				-----------------------------------------------------------------------------------------------------------

				lArrMainDataLst = new ArrayList();

				lArrMainDataLst.add("<b>Rounded Difference<b>");

				lArrMainDataLst.add(total_bud_np != 0 ?  "<b>"+ (Math.round(total_bud_np) - (total_bud_np)) +"<b>" : "-" );
				lArrMainDataLst.add(total_bud_css != 0 ?  "<b>"+ (Math.round(total_bud_css) - total_bud_css) +"<b>" : "-" );
				lArrMainDataLst.add(total_bud != 0 ? "<b>"+ (Math.round(total_bud)- total_bud)+"<b>" : "-" );
				lArrMainDataLst.add(lHashBudData.get("Total_Bud") != 0 ? "<b>"+ lnfLong.format((Math.round(lHashBudData.get("Total_Bud")))- lHashBudData.get("Total_Bud"))+"<b>" : "-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add(lHashCurrData.get("Total_Curr") != 0 ? "<b>"+ lnfLong.format(Math.round(lHashCurrData.get("Total_Curr")- lHashCurrData.get("Total_Curr")))+"<b>" : "-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add(lHashPrgData.get("Total_Curr") != 0 ? "<b>"+ lnfLong.format(Math.round(lHashPrgData.get("Total_Curr") - lHashPrgData.get("Total_Curr")))+"<b>" : "-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("-");


				lArrMainReturn.add(lArrMainDataLst);

//				----------------------------Total Row will be generated---------------------------------------------------
//				-----------------------------------------------------------------------------------------------------------

				lArrMainDataLst = new ArrayList();

				lArrMainDataLst.add("<b>Total<b>");

				lArrMainDataLst.add(total_bud_np != 0 ?  "<b>"+ Math.round(total_bud_np)+"<b>" : "-" );
				lArrMainDataLst.add(total_bud_css != 0 ?  "<b>"+ Math.round(total_bud_css)+"<b>" : "-" );
				lArrMainDataLst.add(total_bud != 0 ? "<b>"+ Math.round(total_bud)+"<b>" : "-" );
				lArrMainDataLst.add(lHashBudData.get("Total_Bud") != 0 ? "<b>"+ Math.round(lHashBudData.get("Total_Bud"))+"<b>" : "0");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add(lHashCurrData.get("Total_Curr") != 0 ? "<b>"+ Math.round(lHashCurrData.get("Total_Curr"))+"<b>" : "0");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add(lHashPrgData.get("Total_Curr") != 0 ? "<b>"+ Math.round(lHashPrgData.get("Total_Curr"))+"<b>" : "0");

				lArrMainDataLst.add("-");
				
				if (Math.round(lHashBudData.get("Total_Bud")) != 0)
				{
					long l = ((int) ((Math.round(lHashPrgData.get("Total_Curr"))) * 100) / Math.round(lHashBudData.get("Total_Bud")));
					if(l != 0)
						lArrMainDataLst.add(l);
					else
						lArrMainDataLst.add("-");
				}
				else
					lArrMainDataLst.add("-");
				
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("-");


				lArrMainReturn.add(lArrMainDataLst);

			}

			
//			-----------------------------------------------------------------------------------------------------------------
//			fundType = PublicAccount(NET) OR Contigency(NET)
//			------------------------------------------------------------------------------------------------------------------

			if(lfundType == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")) || lfundType ==  Integer.parseInt(gObjRsrcBndle.getString("PublicAccount(NET)")))
			{
				GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);
				double current_total = 0.0;
				double prog_total = 0.0;
				double current_total_rounded = 0.0;
				double prog_total_rounded = 0.0;
				
				long current_total_total = 0;
				long prog_total_total = 0;

				if(lfundType == Integer.parseInt(gObjRsrcBndle.getString("Contigency(NET)")))
					lObjReport.setReportName("<b><U>State Profile Report - MajorHead Wise</U> <b> <br> Contigency (NET) -  " + lStrReportHeading + " ["+lStartLimitRange + "-" + lEndLimitRange +"]<br> From : "+ lStrFromDate + " To: " + lStrToDate);
				else
					lObjReport.setReportName("<b><U>State Profile Report - MajorHead Wise</U> <b> <br> PublicAccount (NET) -  " + lStrReportHeading + "  ["+lStartLimitRange + "-" + lEndLimitRange +"] <br> From : "+ lStrFromDate + " To: " + lStrToDate);

				lHashBudData = gObjRptQueryDAO.getMjrHDWiseBudRcptData(lStartLimitRange,lEndLimitRange,lintFinYearId,"en_US");
				
				
				lHashGrantData_pl = lObjGrantDtlDAOImpl.getMjrHDWiseGrantData(lStartLimitRange,lEndLimitRange,lintFinYearId,"PL");
				lHashGrantData_np = lObjGrantDtlDAOImpl.getMjrHDWiseGrantData(lStartLimitRange,lEndLimitRange,lintFinYearId,"NP");
				lHashGrantData_css = lObjGrantDtlDAOImpl.getMjrHDWiseGrantData(lStartLimitRange,lEndLimitRange,lintFinYearId,"CSS");
				
				lHashCurrDataExp = gObjRptQueryDAO.getMjrHdWiseExpenditureData(lStartLimitRange,lEndLimitRange,lintFinYearId,lStrFromDate,lStrToDate);
				lHashPrgDataExp = gObjRptQueryDAO.getMjrHdWiseExpenditureData(lStartLimitRange,lEndLimitRange,lintFinYearId,"",lStrToDate);
				
				lHashCurrData = gObjRptQueryDAO.getMjrHDWiseCurrRcptData(lStartLimitRange,lEndLimitRange,lintFinYearId,"en_US",lStrFromDate,lStrToDate);
				lHashPrgData = gObjRptQueryDAO.getMjrHDWiseCurrRcptData(lStartLimitRange,lEndLimitRange,lintFinYearId,"en_US","",lStrToDate);


				for(int i = lStartLimitRange ; i <= lEndLimitRange ; i++)
				{

					lArrMainDataLst = new ArrayList();

					lArrMainDataLst.add(getHeadName(i));
									
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					
					
					//	Budget Data 
					//	Grant Data Starts--------------------
					
					
					
					
					value = 0;	
					
					value1 = lHashGrantData_pl.get(i+"PL");
					value2 = lHashGrantData_np.get(i+"NP");
					value3 = lHashGrantData_css.get(i+"CSS");
					
					value = value + value1 + value2 + value3;
					
					
					
					

					total_grant_pl = total_grant_pl + value1;
					total_grant_np = total_grant_np + value2;
					total_grant_css = total_grant_css + value3;
					
					lArrMainDataLst.add(value1 != 0 ? Math.round(value1) : "-" );
					lArrMainDataLst.add(value2 != 0 ? Math.round(value2) : "-" );
					lArrMainDataLst.add(value3 != 0 ? Math.round(value3) : "-" );
					lArrMainDataLst.add(value != 0 ? Math.round(value) : "-" );
					
					// 	Grant Data Ends--------------------
					//	Current Data starts-------------------
				
					

					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					
						
					value1 =  lHashCurrDataExp.get(i+"PL");
					value2 =  lHashCurrDataExp.get(i+"NP");
					value3 =  lHashCurrDataExp.get(i+"CSS");
					current_total = value + value1 + value2 + value3;
					
					
					current_total = (lHashCurrData.get(i+"Total")) - current_total;
					current_total_rounded = current_total_rounded + (Math.round(current_total) - current_total);
					
					current_total_total = current_total_total + Math.round(current_total); 
					
					lArrMainDataLst.add(current_total!= 0 ? Math.round(current_total) : "-" );
					//	Current Data Ends------------------
					
					//	Progressive Data Starts--------------
				

					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					
					value1 =  lHashPrgDataExp.get(i+"PL");
					value2 =  lHashPrgDataExp.get(i+"NP");
					value3 =  lHashPrgDataExp.get(i+"CSS");
					prog_total = value + value1 + value2 + value3;
					
					prog_total = (lHashPrgData.get(i+"Total")) - prog_total;
					
					prog_total_rounded = prog_total_rounded + (Math.round(prog_total) - prog_total);
					
					prog_total_total = prog_total_total + Math.round(prog_total);
					
					lArrMainDataLst.add(prog_total!= 0 ? Math.round(prog_total) : "-" );
					
				/*	lArrMainDataLst.add((value =(Math.round(lHashPrgData.get(i+"Total"))))!= 0 ? Math.round(value) : "-" );*/
					//	Progressive Data Ends-----------------

					lArrMainDataLst.add("-");
					
					if (Math.round(lHashBudData.get(i+"Total")) != 0)
					{
						long l = ((int) ((Math.round(lHashPrgData.get(i+"Total")))) * 100) / Math.round(lHashBudData.get(i+"Total"));
						if(l != 0)
							lArrMainDataLst.add(l);
						else
							lArrMainDataLst.add("-");
					}
					else
						lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");

					lArrMainReturn.add(lArrMainDataLst);
				}

//				----------------------------------------------------------------------------------------------------------
//				All rows has been added to ArrayList
//				----------------------------------------------------------------------------------------------------------
				

//				----------------------------Rounded Difference Row will be generated---------------------------------------------------
//				-----------------------------------------------------------------------------------------------------------

				lArrMainDataLst = new ArrayList();

				lArrMainDataLst.add("<b>Rounded Difference<b>");
/*
				lArrMainDataLst.add(total_bud_np != 0 ?  "<b>"+ (Math.round(total_bud_np) - (total_bud_np)) +"<b>" : "-" );
				lArrMainDataLst.add(total_bud_css != 0 ?  "<b>"+ (Math.round(total_bud_css) - total_bud_css) +"<b>" : "-" );
				lArrMainDataLst.add(total_bud != 0 ? "<b>"+ (Math.round(total_bud)- total_bud)+"<b>" : "-" );
				lArrMainDataLst.add(lHashBudData.get("Total_Bud") != 0 ? "<b>"+ lnfLong.format((Math.round(lHashBudData.get("Total_Bud")))- lHashBudData.get("Total_Bud"))+"<b>" : "-");
*/
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add(current_total_rounded != 0 ? "<b>"+ lnfLong.format(current_total_rounded)+"<b>" : "-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add(prog_total_rounded != 0 ? "<b>"+ lnfLong.format(prog_total_rounded)+"<b>" : "-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("-");


				lArrMainReturn.add(lArrMainDataLst);

//				----------------------------Total Row will be generated---------------------------------------------------
//				-----------------------------------------------------------------------------------------------------------

				lArrMainDataLst = new ArrayList();

				lArrMainDataLst.add("<b>Total<b>");

				/*lArrMainDataLst.add(total_bud_np != 0 ?  "<b>"+ Math.round(total_bud_np)+"<b>" : "-" );
				lArrMainDataLst.add(total_bud_css != 0 ?  "<b>"+ Math.round(total_bud_css)+"<b>" : "-" );
				lArrMainDataLst.add(total_bud != 0 ? "<b>"+ Math.round(total_bud)+"<b>" : "-" );
				lArrMainDataLst.add(lHashBudData.get("Total_Bud") != 0 ? "<b>"+ Math.round(lHashBudData.get("Total_Bud"))+"<b>" : "0");
*/
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
					
				lArrMainDataLst.add(total_grant_pl != 0 ? "<b>"+ Math.round(total_grant_pl)+"<b>" : "-");
				lArrMainDataLst.add(total_grant_np != 0 ?  "<b>"+ Math.round(total_grant_np)+"<b>" : "-" );
				lArrMainDataLst.add(total_grant_css != 0 ?  "<b>"+ Math.round(total_grant_css)+"<b>" : "-" );
				lArrMainDataLst.add(total_grant != 0 ? "<b>"+ Math.round(total_grant)+"<b>" : "-" );

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add(current_total_total != 0 ? "<b>"+ Math.round(current_total_total)+"<b>" : "-");

				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add(prog_total_total != 0 ? "<b>"+ Math.round(prog_total_total)+"<b>" : "-");

				lArrMainDataLst.add("-");
				
				if (Math.round(lHashBudData.get("Total_Bud")) != 0)
				{
					long l = ((int) ((Math.round(lHashPrgData.get("Total_Curr"))) * 100) / Math.round(lHashBudData.get("Total_Bud")));
					if(l != 0)
						lArrMainDataLst.add(l);
					else
						lArrMainDataLst.add("-");
				}
				else
					lArrMainDataLst.add("-");
				
				lArrMainDataLst.add("-");

				lArrMainDataLst.add("-");


				lArrMainReturn.add(lArrMainDataLst);

			}

			
			
			
			
			
			
//			-----------------------------------------------------------------------------------------------------------------
//			fundType = Revenue Expenditure OR Capital Expenditure
//			------------------------------------------------------------------------------------------------------------------
			else if(lfundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")) || lfundType == Integer.parseInt(gObjRsrcBndle.getString("CapitalExpenditure")))
			{

				value1 = 0;
				value2 = 0;
				value3 = 0;
				value = 0;

				GrantDtlDAOImpl lObjGrantDtlDAOImpl = new GrantDtlDAOImpl(lObjSessionFactory);


				if(lfundType == Integer.parseInt(gObjRsrcBndle.getString("RevenueExpenditure")))

					lObjReport.setReportName("<b><U>State Profile Report - MajorHead Wise</U> <b> <br> Revenue Expenditure -   " + lStrReportHeading + " ["+lStartLimitRange + "-" + lEndLimitRange +"] <br> From : "+ lStrFromDate + " To: " + lStrToDate);
				else
					lObjReport.setReportName("<b><U>State Profile Report - MajorHead Wise</U> <b> <br>Capital Expenditure -   " + lStrReportHeading + " ["+lStartLimitRange + "-" + lEndLimitRange +"]<br> From : "+ lStrFromDate + " To: " + lStrToDate);

				glogger.info("hi this is morning ");
				
				lHashBudData = gObjRptQueryDAO.getMjrHDWiseBudExpData(lStartLimitRange, lEndLimitRange, lintFinYearId, lStrLangId, lStrFromDate, lStrToDate);
				lHashBudCSSData = gObjRptQueryDAO.getMjrHDWiseBudCSSData(lStartLimitRange, lEndLimitRange, lintFinYearId, lStrLangId, lStrFromDate, lStrToDate);
				
				lHashGrantData_pl = lObjGrantDtlDAOImpl.getMjrHDWiseGrantData(lStartLimitRange,lEndLimitRange,lintFinYearId,"PL");
				lHashGrantData_np = lObjGrantDtlDAOImpl.getMjrHDWiseGrantData(lStartLimitRange,lEndLimitRange,lintFinYearId,"NP");
				lHashGrantData_css = lObjGrantDtlDAOImpl.getMjrHDWiseGrantData(lStartLimitRange,lEndLimitRange,lintFinYearId,"CSS");
				
				lHashCurrData = gObjRptQueryDAO.getMjrHdWiseExpenditureData(lStartLimitRange,lEndLimitRange,lintFinYearId,lStrFromDate,lStrToDate);
				lHashPrgData = gObjRptQueryDAO.getMjrHdWiseExpenditureData(lStartLimitRange,lEndLimitRange,lintFinYearId,"",lStrToDate);




				for(int i = lStartLimitRange ; i <= lEndLimitRange ; i++)
				{

					lArrMainDataLst = new ArrayList();
					double budtotal = 0.0;
					double prgtotal = 0.0;

					lArrMainDataLst.add(getHeadName(i));

					//		Calculation for Budget Data---------------------------------------------------------------		
					value = 0;
					value1 =  lHashBudData.get(i+"PL");
					value2 =  lHashBudData.get(i+"NP");
					value3 =  lHashBudCSSData.get(i+"CSS");
					value2 = value2 - value3;
					value = value + value1 + value2 + value3;
					
					total_bud_pl = total_bud_pl + value1;
					total_bud_np = total_bud_np + value2;
					total_bud_css = total_bud_css + value3;
					//--------------------------------------------------------------------------------------------------

					//		Data Addition of Budget data----------------------------------------------------------------
					
					
					
					lArrMainDataLst.add(value1 != 0 ? Math.round(value1) : "-" );
					lArrMainDataLst.add(value2 != 0 ? Math.round(value2) : "-" );
					lArrMainDataLst.add(value3 != 0 ? Math.round(value3) : "-" );
					lArrMainDataLst.add(value != 0 ? Math.round(value) : "-" );
					budtotal = value;
					//---------------------------------------------------------------------------------------------------


					//		Calculation for Grant Data-------------------------------------------------------------------
					value = 0;	
					
					value1 = lHashGrantData_pl.get(i+"PL");
					value2 = lHashGrantData_np.get(i+"NP");
					value3 = lHashGrantData_css.get(i+"CSS");
					
					value = value + value1 + value2 + value3;

					total_grant_pl = total_grant_pl + value1;
					total_grant_np = total_grant_np + value2;
					total_grant_css = total_grant_css + value3;
					//------------------------------------------------------------------------------------------------------

					//		Data  Addition for Grant Data--------------------------------------------------------------------
					lArrMainDataLst.add(value1 != 0 ? Math.round(value1) : "-" );
					lArrMainDataLst.add(value2 != 0 ? Math.round(value2) : "-" );
					lArrMainDataLst.add(value3 != 0 ? Math.round(value3) : "-" );
					lArrMainDataLst.add(value != 0 ? Math.round(value) : "-" );
					//--------------------------------------------------------------------------------------------------------

					//		Calculation for Current Data----------------------------------------------------------------------
					value = 0;	
					value1 =  lHashCurrData.get(i+"PL");
					value2 =  lHashCurrData.get(i+"NP");
					value3 =  lHashCurrData.get(i+"CSS");
					value = value + value1 + value2 + value3;

					total_curr_pl = total_curr_pl + value1;
					total_curr_np = total_curr_np + value2;
					total_curr_css = total_curr_css + value3;
					//---------------------------------------------------------------------------------------------------------

					//		Data Addition for Current Data---------------------------------------------------------------------		
					lArrMainDataLst.add(value1 != 0 ? Math.round(value1) : "-" );
					lArrMainDataLst.add(value2 != 0 ? Math.round(value2) : "-" );
					lArrMainDataLst.add(value3 != 0 ? Math.round(value3) : "-" );
					lArrMainDataLst.add(value != 0 ? Math.round(value) : "-" );
					//---------------------------------------------------------------------------------------------------------

					//		Calculation for Progressive Data--------------------------------------------------------------------
					value = 0;	
					value1 =  lHashPrgData.get(i+"PL");
					value2 =  lHashPrgData.get(i+"NP");
					value3 =  lHashPrgData.get(i+"CSS");
					value = value + value1 + value2 + value3;

					total_prg_pl = total_prg_pl + value1;
					total_prg_np = total_prg_np + value2;
					total_prg_css = total_prg_css + value3;
					//----------------------------------------------------------------------------------------------------------

					//		Data Addition for Progressive Data------------------------------------------------------------------
					lArrMainDataLst.add(value1 != 0 ? Math.round(value1) : "-" );
					lArrMainDataLst.add(value2 != 0 ? Math.round(value2) : "-" );
					lArrMainDataLst.add(value3 != 0 ? Math.round(value3) : "-" );
					lArrMainDataLst.add(value != 0 ? Math.round(value) : "-" );
					prgtotal = 0.0;
					//------------------------------------------------------------------------------------------------------------

					lArrMainDataLst.add("-");
					
					if (Math.round(prgtotal) != 0)
					{
						long l = ((int) ((Math.round(prgtotal) * 100) / Math.round(prgtotal)));
						if(l != 0)
							lArrMainDataLst.add(l);
						else
							lArrMainDataLst.add("-");
					}
					else
						lArrMainDataLst.add("-");
					
					lArrMainDataLst.add("-");
					lArrMainDataLst.add("-");

					lArrMainReturn.add(lArrMainDataLst);
				}

//				----------------------------------------------------------------------------------------------------------------------
//				All Rows has been added to ArrayList
//				-----------------------------------------------------------------------------------------------------------------------


				//-----------------------------------------------------------------------------	

				//	Rounded Difference Row Formation-------------------------------------------------------------------------------------

				lArrMainDataLst = new ArrayList();

				lArrMainDataLst.add("<b>Rounded Difference<b>");

				lArrMainDataLst.add(total_bud_pl != 0 ? "<b>"+ lnfLong.format((Math.round(total_bud_pl) - total_bud_pl))+"<b>" : "-");
				lArrMainDataLst.add(total_bud_np != 0 ?  "<b>"+ lnfLong.format((Math.round(total_bud_np) - total_bud_np))+"<b>" : "-" );
				lArrMainDataLst.add(total_bud_css != 0 ?  "<b>"+ lnfLong.format((Math.round(total_bud_css) - total_bud_css))+"<b>" : "-" );
				lArrMainDataLst.add(total_bud != 0 ? "<b>"+ lnfLong.format((Math.round(total_bud) - total_bud))+"<b>" : "-" );

				lArrMainDataLst.add(total_grant_pl != 0 ? "<b>"+ lnfLong.format((Math.round(total_grant_pl) - total_grant_pl)) +"<b>" : "-");
				lArrMainDataLst.add(total_grant_np != 0 ?  "<b>"+ lnfLong.format((Math.round(total_grant_np) - total_grant_np))+"<b>" : "-" );
				lArrMainDataLst.add(total_grant_css != 0 ?  "<b>"+ lnfLong.format((Math.round(total_grant_css) - total_grant_css))+"<b>" : "-" );
				lArrMainDataLst.add(total_grant != 0 ? "<b>"+ lnfLong.format((Math.round(total_grant) - total_grant)) +"<b>" : "-" );

				lArrMainDataLst.add(total_curr_pl != 0 ? "<b>"+ lnfLong.format((Math.round(total_curr_pl) - total_curr_pl))+"<b>" : "-");
				lArrMainDataLst.add(total_curr_np != 0 ?  "<b>"+ lnfLong.format((Math.round(total_curr_np) - total_curr_np))+"<b>" : "-" );
				lArrMainDataLst.add(total_curr_css != 0 ?  "<b>"+ lnfLong.format((Math.round(total_curr_css)- total_curr_css))+"<b>" : "-" );
				lArrMainDataLst.add(total_curr != 0 ? "<b>"+ lnfLong.format((Math.round(total_curr) - total_curr))+"<b>" : "-" );

				lArrMainDataLst.add(total_prg_pl != 0 ? "<b>"+ lnfLong.format((Math.round(total_prg_pl) - total_prg_pl))+"<b>" : "-");
				lArrMainDataLst.add(total_prg_np != 0 ?  "<b>"+ lnfLong.format((Math.round(total_prg_np) - total_prg_np))+"<b>" : "-" );
				lArrMainDataLst.add(total_prg_css != 0 ?  "<b>"+ lnfLong.format((Math.round(total_prg_css) - total_prg_css))+"<b>" : "-" );
				lArrMainDataLst.add(total_prg != 0 ? "<b>"+ lnfLong.format((Math.round(total_prg) - total_prg))+"<b>" : "-" );

				
				

				
				
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainReturn.add(lArrMainDataLst);
				
				
				
//				------------------------------------------------------------------------------------------------------------------------
//				Final Total Row Additison
//				--------------------------------------------------------------------------------------------------------------------------

				
				
				
				//	Total Data Calculation---------------------------------------------------

				total_bud = total_bud_pl + total_bud_np + total_bud_css;
				total_grant = total_grant_pl + total_grant_np + total_grant_css;
				total_curr =  total_curr_pl + total_curr_np + total_curr_css;
				total_prg =  total_prg_pl + total_prg_np + total_prg_css;

				//-----------------------------------------------------------------------------	

				//	Total Row Formation-------------------------------------------------------------------------------------

				lArrMainDataLst = new ArrayList();

				lArrMainDataLst.add("<b>Total<b>");

				lArrMainDataLst.add(total_bud_pl != 0 ? "<b>"+ Math.round(total_bud_pl)+"<b>" : "-");
				lArrMainDataLst.add(total_bud_np != 0 ?  "<b>"+ Math.round(total_bud_np)+"<b>" : "-" );
				lArrMainDataLst.add(total_bud_css != 0 ?  "<b>"+ Math.round(total_bud_css)+"<b>" : "-" );
				lArrMainDataLst.add(total_bud != 0 ? "<b>"+ Math.round(total_bud)+"<b>" : "-" );

				lArrMainDataLst.add(total_grant_pl != 0 ? "<b>"+ Math.round(total_grant_pl)+"<b>" : "-");
				lArrMainDataLst.add(total_grant_np != 0 ?  "<b>"+ Math.round(total_grant_np)+"<b>" : "-" );
				lArrMainDataLst.add(total_grant_css != 0 ?  "<b>"+ Math.round(total_grant_css)+"<b>" : "-" );
				lArrMainDataLst.add(total_grant != 0 ? "<b>"+ Math.round(total_grant)+"<b>" : "-" );

				lArrMainDataLst.add(total_curr_pl != 0 ? "<b>"+ Math.round(total_curr_pl)+"<b>" : "-");
				lArrMainDataLst.add(total_curr_np != 0 ?  "<b>"+ Math.round(total_curr_np)+"<b>" : "-" );
				lArrMainDataLst.add(total_curr_css != 0 ?  "<b>"+ Math.round(total_curr_css)+"<b>" : "-" );
				lArrMainDataLst.add(total_curr != 0 ? "<b>"+ Math.round(total_curr)+"<b>" : "-" );

				lArrMainDataLst.add(total_prg_pl != 0 ? "<b>"+ Math.round(total_prg_pl)+"<b>" : "-");
				lArrMainDataLst.add(total_prg_np != 0 ?  "<b>"+ Math.round(total_prg_np)+"<b>" : "-" );
				lArrMainDataLst.add(total_prg_css != 0 ?  "<b>"+ Math.round(total_prg_css)+"<b>" : "-" );
				lArrMainDataLst.add(total_prg != 0 ? "<b>"+ Math.round(total_prg)+"<b>" : "-" );

				
				

				
				
				lArrMainDataLst.add("-");
				
				if (Math.round(total_bud) != 0)
				{
					long l = ((int) ((Math.round(total_prg)) * 100) / Math.round(total_bud));
					if(l != 0)
						lArrMainDataLst.add(l);
					else
						lArrMainDataLst.add("-");
				}
				else
					lArrMainDataLst.add("-");
				
				lArrMainDataLst.add("-");
				lArrMainDataLst.add("-");

				lArrMainReturn.add(lArrMainDataLst);

				//---------------------------------------------------------------------------------------------------------------

			}

			return lArrMainReturn;
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

	