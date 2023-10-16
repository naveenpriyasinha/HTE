package com.tcs.sgv.common.reports;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.apps.dao.budget.BudExpEstDAO;
import com.tcs.sgv.apps.dao.budget.BudExpEstDAOImpl;
import com.tcs.sgv.apps.dao.budget.BudFinYrDAO;
import com.tcs.sgv.apps.dao.budget.BudFinYrDAOImpl;
import com.tcs.sgv.apps.dao.budget.BudHdDAO;
import com.tcs.sgv.apps.dao.budget.BudHdDAOImpl;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstDeptVO;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.CommonRptQueryDAO;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.core.service.ServiceLocator;

public class CommonReportDAOImpl extends DefaultReportDataFinder implements
		ReportDataFinder {

	private static final Logger glogger=Logger.getLogger(CommonReportDAOImpl.class);
	
	private SessionFactory lObjSessionFactory = null;
	private StyleVO[] reportStyleVO = null;
	
	public Collection findReportData(ReportVO report, Object criteria)
			throws ReportException {
		
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serviceLocator = (ServiceLocator) requestAttributes .get("serviceLocator");

		// get the SessionFactory instance for using it to fetch data...
		lObjSessionFactory = serviceLocator.getSessionFactory();

		List lArrReportData = new ArrayList();
		
		reportStyleVO = new StyleVO[1];
   	 	reportStyleVO[0]=new StyleVO();
   	 	reportStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
   	 	reportStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 

		if (report.getReportCode().equals("1")) // StateWise Report
		{
			lArrReportData = getStateWiseProfileRpt(report);
		}
		else if (report.getReportCode().equals("3")) // Department Profile Report - Major Head Wise
		{
			lArrReportData = getMajorHeadWiseProfileRpt(report);
		}
		else if (report.getReportCode().equals("5")) // Department Profile Report - Scheme Wise
		{
			lArrReportData = getSchemeWiseProfileRpt(report);
		}
		else if (report.getReportCode().equals("7")) // DeptWise Report
		{
			lArrReportData = getDeptWiseProfileRpt(report);
		}
		else if (report.getReportCode().equals("9")) // DeptWise PlanNonPlanWise Report
        {
            lArrReportData = getDeptPlanNonPlanWiseRpt(report);
        }
		else if (report.getReportCode().equals("25")) // DeptWise Report
		{
			lArrReportData = getStateProfileReport(report);
		}
		else if(report.getReportCode().equals("11"))
		{
			lArrReportData = getSrcOfInfoData(report);
		}
		else if (report.getReportCode().equals("12")) // DeptWise Report
		{
			lArrReportData = getSrcOfInfoData(report);
		}
		else if (report.getReportCode().equals("27")) // DeptWise Report
		{
			lArrReportData = getDeptGrantPlanExp(report);
		}

		return lArrReportData;
	}
	
	private List getStateWiseProfileRpt(ReportVO lObjReport)
	{
		glogger.info("Going into getStateWiseProfileRpt");
		List lArrReturn = new ArrayList();
		ArrayList StyleVO=new ArrayList();
		
		CommonRptQueryDAO lObjRptDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		String lStrFinYr = (String) lObjReport.getParameterValue("finYear");
		
		glogger.info("lStrFinYr is : "+ lStrFinYr + " lObjReport.getLangId() is :" + lObjReport.getLangId());
		
		glogger.info("lObjRptDAO is : " + lObjRptDAO.getClass());
		
		HashMap lHashData = lObjRptDAO.getStateProfileAmt(lStrFinYr, lObjReport.getLangId());
		
		ArrayList<String> lArrInner = new ArrayList<String>();
        lArrInner.add("Expenditure");
        lArrInner.add((lHashData.get("ExpAmt") != null) ? (lHashData.get("ExpAmt").toString()) : "0");   // Expenditure
        lArrInner.add("0");   // Actuals
        lArrInner.add((lHashData.get("ActualExpAmt") != null) ? (lHashData.get("ActualExpAmt").toString()) : "0");   // Liabilities
        lArrReturn.add(lArrInner);
        
        lArrInner = new ArrayList();
        lArrInner.add("Receipt");
        lArrInner.add((lHashData.get("RcptAmt") != null) ? (lHashData.get("RcptAmt").toString()) : "0");   // Receipt
        lArrInner.add("0");   // Actuals
        lArrInner.add("0");   // Liabilities
        
        lArrReturn.add(lArrInner);

		
		return lArrReturn;
	}
	
	private List getDeptWiseProfileRpt(ReportVO lObjReport) {

		List lArrReturn = new ArrayList();
		
		String lStrFinYr = (String) lObjReport.getParameterValue("finYear");
		String lStrPlanType = (String) lObjReport.getParameterValue("planType");
		
	       SimpleDateFormat sdOld = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	        Date lDtStartDate = new Date();
	        Date lDtEndDate = new Date();
       
		BudExpEstDAO lObjExpEstDAO = new BudExpEstDAOImpl(lObjSessionFactory);
		
		glogger.info("PlanType is : "+ lStrPlanType);
		
		CommonRptQueryDAO lObjRptQueryDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
		
		//List lArrDeptList = lObjRptQueryDAO.getAllDept(lObjReport.getLangId());
		NumberFormat lnfLong=NumberFormat.getNumberInstance();
        lnfLong.setGroupingUsed(false);
        lnfLong.setMaximumFractionDigits(3);
        lnfLong.setMinimumFractionDigits(3);
        
		List lArrDeptList = null;
		
		try
		{
			lArrDeptList = lObjExpEstDAO.getAllDept(lObjReport.getLangId()); 
		}
		catch(Exception e)
		{
			glogger.error("Error in execution of CommonRptDAOImpl and Error is : " + e,e);
			e.printStackTrace();
		}
		HashMap lhshDateMap = getDates("","");
		String lStrFromDate = lhshDateMap.get("fromdate").toString();
		String lStrToDate = lhshDateMap.get("todate").toString();
		//System.out.println("Dates from getdates method : "+lStrFromDate+" and "+lStrToDate );
		try
		{
				
			lDtStartDate = sd.parse(lStrFromDate);
	        lDtEndDate = sd.parse(lStrToDate);
		}
		
		catch(Exception e)
		{
			glogger.info("Exception in getDeptWiseProfileRpt while parsing date : ");
			e.printStackTrace();
		}
		
		//System.out.println("Dates after parsing : "+lDtStartDate+" and "+lDtEndDate );
        

		
		HashMap lHashDeptBEAmt = lObjRptQueryDAO.getAllDeptBEAmt(lStrFinYr, lStrPlanType, lObjReport.getLangId());
		//HashMap lHashDeptGrantData = lObjRptQueryDAO.getAllDeptGrantAmt(lStrFinYr, lStrPlanType, lObjReport.getLangId());
		HashMap lHashDeptActulAmt = lObjRptQueryDAO.getAllDeptActualsAmt(lStrFinYr, lStrPlanType, lObjReport.getLangId());
		HashMap lHashLiabAmt = lObjRptQueryDAO.getAllDeptLiabAmt(lStrFinYr, lStrPlanType, lObjReport.getLangId());
		Map lHashCurrYrExpData = lObjRptQueryDAO.getAllDeptPLNPCSSCurrExpAmt(lDtStartDate, lDtEndDate, lObjReport.getLangId());
		Map lHashGrantData = lObjRptQueryDAO.getAllDeptGrantCollAmt(lStrFinYr,lDtStartDate, lDtEndDate,lObjReport.getLangId());
		
		
		BudExpEstDeptVO lObjDeptVO = new BudExpEstDeptVO();
		
		int lIntCnt=0;
        double lLngBEAmt = 0;
       
        double lLngActulAmt = 0;
        double lLngLiabAmt = 0;
        
        double lLngActulalsDiff =0;
        double lLngBEAmtDiff =0;
        double lLngGrantAmtDiff =0;
        double lLngLiabAmtDiff =0;
        
        long lLngBETotal=0;
        long lLngGrantTotal=0;
        long lLngActTotal=0;
        long lLngLiabTotal=0;
        for(Object lObjTmp : lArrDeptList)
        {
          ArrayList lArrInner = new ArrayList();
          
          lObjDeptVO = (BudExpEstDeptVO) lObjTmp;
          
     	  double lLngGrantAmt = 0;
          double lLongActualPL;
          double lLongActualNP;
          double lLongActualCSS;
          double lLongActualTotal;
          long liActualPL=0;
          long liActualNP=0;
          long liActualCSS=0;
          long liActualTotal=0;
          
        
          if(lHashCurrYrExpData.containsKey(lObjDeptVO.getDeptID()+"_PL"))
          {
        	  liActualPL= Math.round(Double.parseDouble(lHashCurrYrExpData.get(lObjDeptVO.getDeptID()+"_PL").toString())/10000000);
        	  lLngActulalsDiff +=((Double.parseDouble(lHashCurrYrExpData.get(lObjDeptVO.getDeptID()+"_PL").toString())/10000000)-liActualPL);
        	 
          }
          if(lHashCurrYrExpData.containsKey(lObjDeptVO.getDeptID()+"_NP"))
          {
        	  liActualNP= Math.round(Double.parseDouble(lHashCurrYrExpData.get(lObjDeptVO.getDeptID()+"_NP").toString())/10000000);
        	  lLngActulalsDiff +=((Double.parseDouble(lHashCurrYrExpData.get(lObjDeptVO.getDeptID()+"_NP").toString())/10000000)-liActualNP);
        	 
          }
          if(lHashCurrYrExpData.containsKey(lObjDeptVO.getDeptID()+"_CSS"))
          {
        	  liActualCSS= Math.round(Double.parseDouble(lHashCurrYrExpData.get(lObjDeptVO.getDeptID()+"_CSS").toString())/10000000);
        	  lLngActulalsDiff +=((Double.parseDouble(lHashCurrYrExpData.get(lObjDeptVO.getDeptID()+"_CSS").toString())/10000000)-liActualCSS);
        	 
          }

          liActualTotal=liActualPL+liActualNP+liActualCSS;
          lLngBEAmt = (lHashDeptBEAmt.get(lObjDeptVO.getDeptID()) != null) ? Double.parseDouble(lHashDeptBEAmt.get(lObjDeptVO.getDeptID()).toString()) : 0;
          
          if(lHashGrantData.containsKey(lObjDeptVO.getDeptID()+"_PL"))
          {
        	  lLngGrantAmt += Double.parseDouble(lHashGrantData.get(lObjDeptVO.getDeptID()+"_PL").toString());
          }
          if(lHashGrantData.containsKey(lObjDeptVO.getDeptID()+"_NP"))
          {
        	  lLngGrantAmt += Double.parseDouble(lHashGrantData.get(lObjDeptVO.getDeptID()+"_PL").toString());
          }
          if(lHashGrantData.containsKey(lObjDeptVO.getDeptID()+"_CSS"))
          {
        	  lLngGrantAmt += Double.parseDouble(lHashGrantData.get(lObjDeptVO.getDeptID()+"_PL").toString());
          }
          
          
          lLngActulAmt = (lHashDeptActulAmt.get(lObjDeptVO.getDeptID()) != null) ? Double.parseDouble(lHashDeptActulAmt.get(lObjDeptVO.getDeptID()).toString()) : 0;
          lLngLiabAmt = (lHashLiabAmt.get(lObjDeptVO.getDeptID()) != null) ? Double.parseDouble(lHashLiabAmt.get(lObjDeptVO.getDeptID()).toString()) : 0;
          
          lArrInner.add(++lIntCnt);      // No
          lArrInner.add(lObjDeptVO.getDeptName()); // Dept Name
          
          lArrInner.add(Math.round(lLngBEAmt));  // BE
          lLngBEAmtDiff += lLngBEAmt-Math.round(lLngBEAmt);
          lLngBETotal+=lLngBEAmt;
          
          lArrInner.add(Math.round(lLngGrantAmt/10000000)); // Grant Amt
          lLngGrantAmtDiff += (lLngGrantAmt/10000000)-Math.round(lLngGrantAmt/10000000);
          lLngGrantTotal+=(lLngGrantAmt/10000000);
          
          lArrInner.add(Math.round(lLngActulAmt)+liActualTotal); // Actual Exp.
          lLngActTotal+=(Math.round(lLngActulAmt)+liActualTotal);
          
          
          lArrInner.add(Math.round(lLngLiabAmt)); // Liabilities
          lLngLiabAmtDiff += lLngLiabAmt-Math.round(lLngLiabAmt);
          lLngLiabTotal+=Math.round(lLngLiabAmt);
          
          lArrInner.add("View"); // Major Head Wise Report 
          lArrInner.add("View"); // Scheme Wise Report 
          lArrInner.add(lStrFinYr); // FinYr
          lArrInner.add(lObjDeptVO.getDeptID()); // DeptId
          lArrInner.add(lStrPlanType); // PlanType
          
          //glogger.debug("lArrInner is : " + lArrInner);
          
          lArrReturn.add(lArrInner);
        }
        
        ArrayList lArrInner = new ArrayList();
        lArrInner.add("");
        lArrInner.add("Total");
        lArrInner.add("<b>"+lLngBETotal+"</b>");
        lArrInner.add("<b>"+lLngGrantTotal+"</b>");
        lArrInner.add("<b>"+lLngActTotal+"</b>");
        lArrInner.add("<b>"+lLngLiabTotal+"</b>");
        lArrInner.add("");
        lArrInner.add("");
        lArrInner.add("");
        lArrInner.add("");
        lArrInner.add("");
        lArrReturn.add(lArrInner);
        
        lArrInner = new ArrayList();
        lArrInner.add("");
        lArrInner.add("Rounded Difference");
        lArrInner.add(lnfLong.format(lLngBEAmtDiff));
        lArrInner.add(lnfLong.format(lLngGrantAmtDiff));
        lArrInner.add(lnfLong.format(lLngActulalsDiff));
        lArrInner.add(lnfLong.format(lLngLiabAmtDiff));
        lArrInner.add("");
        lArrInner.add("");
        lArrInner.add("");
        lArrInner.add("");
        lArrInner.add("");
        lArrReturn.add(lArrInner);
        return lArrReturn;
    }
	
	private List getMajorHeadWiseProfileRpt(ReportVO lObjReport)
	{
		glogger.info("Going into getMajorHeadWiseProfileRpt");
		List lArrReturn = new ArrayList();
		
		BudHdDAO lObjHdDAO = new BudHdDAOImpl(lObjSessionFactory);
				
		CommonRptQueryDAO lObjRptDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		String lStrFinYr = (String) lObjReport.getParameterValue("finYear");
		String lStrDeptName = (String) lObjReport.getParameterValue("deptName");
		String lStrPlanType = (String) lObjReport.getParameterValue("planType");
		glogger.info("lStrFinYr is : "+ lStrFinYr + " lObjReport.getLangId() is :" + lObjReport.getLangId());
		glogger.info("lObjRptDAO is : " + lObjRptDAO.getClass());
		
		ArrayList lArrayListData =  lObjHdDAO.getAllMjrHdsForDept(lStrDeptName, lObjReport.getLangId(), "LC1");
		HashMap lHashMjrHdBEAmt = lObjRptDAO.getAllMjrHdBEAmt(lStrFinYr, lStrDeptName, lStrPlanType, lObjReport.getLangId());
		HashMap lHashMjrHdGrantAmt = lObjRptDAO.getAllMjrHdGrantAmt(lStrFinYr, lStrDeptName, lStrPlanType, lObjReport.getLangId());
		HashMap lHashMjrHdActualExp = lObjRptDAO.getAllMjrHdActualExp(lStrFinYr, lStrDeptName, lStrPlanType,lObjReport.getLangId());
		HashMap getAllMjrHdLaibilityAmt = lObjRptDAO.getAllMjrHdLiabilityAmt(lStrFinYr, lStrDeptName, lStrPlanType,lObjReport.getLangId());
		
		HashMap lHmpMjrWiseAmt=lObjRptDAO.getMajorHeadWiseActuals("9",  lObjReport.getLangId(),lStrPlanType);
		
		
		
		NumberFormat lnfLong = NumberFormat.getInstance();
		lnfLong.setGroupingUsed(false);
		lnfLong.setMaximumFractionDigits(3);
		lnfLong.setMinimumFractionDigits(2);
		double lDBudDiff=0;
		double lDGrantDiff=0;
		double lDActuDiff=0;
		double lDLiabDiff=0;
		long lLngBudTotal=0;
		long lLngGrantTotal=0;
		long lLngActTotal=0;
		long lLngLiabTotal=0;
		for(int IntCounter = 0; IntCounter < lArrayListData.size(); ++IntCounter)
		{
			long lLngBud=0;
			long lLngGrant=0;
			long lLngActuals=0;
			long lLngMjrAmt=0;
			long lDLiab=0;
		
			ArrayList lArrInner = new ArrayList();
			ComboValuesVO lObjComboValue = (ComboValuesVO) lArrayListData.get(IntCounter);
			lArrInner.add(String.valueOf(IntCounter + 1));
			lArrInner.add(lObjComboValue.getId());
			lArrInner.add(lObjComboValue.getDesc());
			
			lLngBud= Math.round(Double.parseDouble((lHashMjrHdBEAmt.containsKey(lObjComboValue.getId()) ? lHashMjrHdBEAmt.get(lObjComboValue.getId()).toString() : 0).toString())/10000);
			lDBudDiff +=((Double.parseDouble((lHashMjrHdBEAmt.containsKey(lObjComboValue.getId()) ? lHashMjrHdBEAmt.get(lObjComboValue.getId()).toString() : 0).toString())/10000)-lLngBud);
			lArrInner.add(lLngBud);
			lLngBudTotal+=lLngBud;

			lLngGrant= Math.round(Double.parseDouble((lHashMjrHdGrantAmt.containsKey(lObjComboValue.getId()) ? lHashMjrHdGrantAmt.get(lObjComboValue.getId()).toString() : 0).toString())/10000);
			lDGrantDiff +=((Double.parseDouble((lHashMjrHdGrantAmt.containsKey(lObjComboValue.getId()) ? lHashMjrHdGrantAmt.get(lObjComboValue.getId()).toString() : 0).toString())/10000)-lLngGrant);
			lArrInner.add(lLngGrant);
			lLngGrantTotal+=lLngGrant;

			lLngActuals= Math.round(Double.parseDouble((lHashMjrHdActualExp.containsKey(lObjComboValue.getId()) ? lHashMjrHdActualExp.get(lObjComboValue.getId()).toString() : 0).toString())/10000);
			lLngMjrAmt = Math.round(Double.parseDouble((lHmpMjrWiseAmt.containsKey(lObjComboValue.getId()) ? lHmpMjrWiseAmt.get(lObjComboValue.getId()).toString() : 0).toString())/10000000);
			lDActuDiff +=(((Double.parseDouble((lHashMjrHdActualExp.containsKey(lObjComboValue.getId()) ? lHashMjrHdActualExp.get(lObjComboValue.getId()).toString() : 0).toString())/10000)-lLngActuals)
							+(Double.parseDouble((lHmpMjrWiseAmt.containsKey(lObjComboValue.getId()) ? lHmpMjrWiseAmt.get(lObjComboValue.getId()).toString() : 0).toString())/10000000)-lLngMjrAmt) ;
			lArrInner.add(lLngActuals+lLngMjrAmt);
			lLngActTotal+=(lLngActuals+lLngMjrAmt);

			lDLiab= Math.round(Double.parseDouble((getAllMjrHdLaibilityAmt.containsKey(lObjComboValue.getId()) ? getAllMjrHdLaibilityAmt.get(lObjComboValue.getId()).toString() : 0).toString())/10000);
			lDLiabDiff +=((Double.parseDouble((getAllMjrHdLaibilityAmt.containsKey(lObjComboValue.getId()) ? getAllMjrHdLaibilityAmt.get(lObjComboValue.getId()).toString() : 0).toString())/10000)-lDLiab);
			lArrInner.add(lDLiab);
			lLngLiabTotal+=lDLiab;

			lArrReturn.add(lArrInner);
		}
		
		ArrayList lArrInner = new ArrayList();
		lArrInner.add("");
		lArrInner.add("");
		lArrInner.add("Total");
		
		StyledData dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lLngBudTotal);
		lArrInner.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lLngGrantTotal);
		lArrInner.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lLngActTotal);
		lArrInner.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lLngLiabTotal);
		lArrInner.add(dataStyle);
		lArrReturn.add(lArrInner);
		
		lArrInner = new ArrayList();
		lArrInner.add("");
		lArrInner.add("");
		lArrInner.add("Rounded Difference");
		lArrInner.add(lnfLong.format(lDBudDiff));
		lArrInner.add(lnfLong.format(lDGrantDiff));
		lArrInner.add(lnfLong.format(lDActuDiff));
		lArrInner.add(lnfLong.format(lDLiabDiff));
		lArrReturn.add(lArrInner);

		return lArrReturn;
	}
	
	private List getSchemeWiseProfileRpt(ReportVO lObjReport)
	{
		glogger.info("Going into getMajorHeadWiseProfileRpt");
		List lArrReturn = new ArrayList();
		
		BudHdDAO lObjHdDAO = new BudHdDAOImpl(lObjSessionFactory);
		
		CommonRptQueryDAO lObjRptDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptDAO.setSessionFactory(lObjSessionFactory);
		
		String lStrFinYr = (String) lObjReport.getParameterValue("finYear");
		String lStrDeptName = (String) lObjReport.getParameterValue("deptName");
		String lStrPlanType = (String) lObjReport.getParameterValue("planType");
		glogger.info("lStrFinYr is : "+ lStrFinYr + " lObjReport.getLangId() is :" + lObjReport.getLangId());
		glogger.info("lObjRptDAO is : " + lObjRptDAO.getClass());
		
		ArrayList lArrayListData =  lObjHdDAO.getAllSchemesForDept(lStrDeptName, lObjReport.getLangId(), "LC1");
		HashMap lHashSchemeBEAmt = lObjRptDAO.getAllSchemeBEAmt(lStrFinYr, lStrDeptName, lStrPlanType, lObjReport.getLangId());
		HashMap lHashSchemeGrantAmt = lObjRptDAO.getAllSchemeGrantAmt(lStrFinYr, lStrDeptName, lStrPlanType, lObjReport.getLangId(), "LC1");
		HashMap lHashSchemeActualExp = lObjRptDAO.getAllSchemeActualExp(lStrFinYr, lStrDeptName, lStrPlanType, lObjReport.getLangId());
		HashMap lHashSchemeLiabilityAmt = lObjRptDAO.getAllSchemeLiabilityAmt(lStrFinYr, lStrDeptName, lStrPlanType, lObjReport.getLangId());
				
		for(int IntCounter = 0; IntCounter < lArrayListData.size(); ++IntCounter)
		{
				ArrayList<String> lArrInner = new ArrayList<String>();
				ComboValuesVO lObjComboValue = (ComboValuesVO) lArrayListData.get(IntCounter);
				lArrInner.add(String.valueOf(IntCounter + 1));
				lArrInner.add(lObjComboValue.getId());
				lArrInner.add(lObjComboValue.getDesc());
				lArrInner.add(lHashSchemeBEAmt.containsKey(lObjComboValue.getId()) ? lHashSchemeBEAmt.get(lObjComboValue.getId()).toString() : "0");
				lArrInner.add(lHashSchemeGrantAmt.containsKey(lObjComboValue.getId()) ? lHashSchemeGrantAmt.get(lObjComboValue.getId()).toString() : "0");
				lArrInner.add(lHashSchemeActualExp.containsKey(lObjComboValue.getId()) ? lHashSchemeActualExp.get(lObjComboValue.getId()).toString() : "0");
				lArrInner.add(lHashSchemeLiabilityAmt.containsKey(lObjComboValue.getId()) ? lHashSchemeLiabilityAmt.get(lObjComboValue.getId()).toString() : "0");
    
				lArrReturn.add(lArrInner);
		}		
				
		return lArrReturn;			
	}
	
	private List getDeptPlanNonPlanWiseRpt(ReportVO lObjReport)
    {
        glogger.info("Going into getDeptPlanNonPlanWiseRpt");
        
        String lStrFinYr = (String) lObjReport.getParameterValue("finYear");
        String lStrFromDate = (String) lObjReport.getParameterValue("Datefrom");
        String lStrToDate = (String) lObjReport.getParameterValue("Dateto");
        
        glogger.info("lStrFromDate is : "+ lStrFromDate + " lStrToDate is :" + lStrToDate);
        
        SimpleDateFormat sdOld = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        BudFinYrDAO lObjFinYrDAO = new BudFinYrDAOImpl();
        Date lDtStartDate = new Date();
        Date lDtEndDate = new Date();
        Date lDtCurrFinYrStartDt = new Date();
        
        Map lHashFinYrInfo = null;
        Map lHashPrevYrFinInfo = null;
        
        // Code commented to get the Date Difference
        // int lIntDateDiff = 0;
        int lIntDateDiff = 1;
        int lIntPrvFinYr = 0;
        
        try
        {   
            lDtStartDate = sd.parse(sd.format(sdOld.parse(lStrFromDate)));
            lDtEndDate = sd.parse(sd.format(sdOld.parse(lStrToDate)));
            
            glogger.info("lDtStartDate is : "+ lDtStartDate + " lDtEndDate is :" + lDtEndDate);
            
            lHashFinYrInfo = lObjFinYrDAO.getFinYrInfo(Integer.parseInt(lStrFinYr));
            lIntPrvFinYr = Integer.parseInt((String)lHashFinYrInfo.get("PREV_FIN_YR"));
            lHashPrevYrFinInfo = lObjFinYrDAO.getFinYrInfo(lIntPrvFinYr);
        }
        catch(Exception e)
        {
            glogger.error("Error in parsing the date" + e,e);
        }
        
        lDtCurrFinYrStartDt = (Date) lHashFinYrInfo.get("FROM_DATE");
        
        Date lDtPrevFinYrStartDate = (Date) lHashPrevYrFinInfo.get("FROM_DATE");
        Date lDtPrevFinYrEndDate = (Date) lHashPrevYrFinInfo.get("TO_DATE");
        
        //  Code commented to get the Date Difference
        //lIntDateDiff = (int) ((lDtEndDate.getTime() - lDtStartDate.getTime())/(1000*60*60*24));  
            
        List<List> lArrReturn = new ArrayList<List>();        
        
        List lArrDeptList = null;
        BudExpEstDAO lObjExpEstDAO = new BudExpEstDAOImpl(lObjSessionFactory);
        
        CommonRptQueryDAO lObjRptDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
        lObjRptDAO.setSessionFactory(lObjSessionFactory);
        
        // Code commented to get the maximum months
        //int lIntMaxMnth = lObjRptDAO.getMaxMonthForGrnt(lStrFinYr);
        int lIntMaxMnth = 1;
        
        NumberFormat lnfLong=NumberFormat.getNumberInstance();
        lnfLong.setGroupingUsed(false);
        lnfLong.setMaximumFractionDigits(3);
        lnfLong.setMinimumFractionDigits(3);
        
        try
        {
            lArrDeptList = lObjExpEstDAO.getAllDept(lObjReport.getLangId()); 
        }
        catch(Exception e)
        {
            glogger.error("Error in execution of getDeptPlanNonPlanWiseRpt and Error is : " + e,e);
            e.printStackTrace();
        }
        
        int lIntCnt=0;
        
        double lDblPLBudAmt=0 , lDblNPBudAmt=0 , lDblCSSBudAmt=0; 
        double lDblPLGrntAmt=0 , lDblNPGrntAmt=0 , lDblCSSGrntAmt=0;
        double lDblPLCurrExpAmt=0, lDblNPCurrExpAmt=0, lDblCSSCurrExpAmt=0;
        double lDblPLProgExpAmt=0, lDblNPProgExpAmt=0, lDblCSSProgExpAmt=0;
        
        double lDblPrevPLProgExpAmt=0, lDblPrevNPProgExpAmt=0 , lDblPrevCSSProgExpAmt=0;
        double lDblPrevProgTotal = 0;

        
        double lDblPLCurrExpAmtFrmBill=0, lDblNPCurrExpAmtFrmBill=0 , lDblCSSCurrExpAmtFrmBill=0;
        double lDblPLProgExpAmtFrmBill=0 , lDblNPProgExpAmtFrmBill=0, lDblCSSProgExpAmtFrmBill=0;

        
        // Total Variables.        
        long lLngTotalPLBudAmt=0 , lLngTotalNPBudAmt=0 , lLngTotalCSSBudAmt=0; 
        long lLngTotalPLGrntAmt=0 , lLngTotalNPGrntAmt=0 , lLngTotalCSSGrntAmt=0;
        long lLngTotalPLCurrExpAmt=0, lLngTotalNPCurrExpAmt=0, lLngTotalCSSCurrExpAmt=0;
        long lLngTotalPLProgExpAmt=0, lLngTotalNPProgExpAmt=0, lLngTotalCSSProgExpAmt=0;
        
        // Difference Variables
        double lDblTotalDiffPLBudAmt=0 , lDblTotalDiffNPBudAmt=0 , lDblTotalDiffCSSBudAmt=0; 
        double lDblTotalDiffPLGrntAmt=0 , lDblTotalDiffNPGrntAmt=0 , lDblTotalDiffCSSGrntAmt=0;
        double lDblTotalDiffPLCurrExpAmt=0, lDblTotalDiffNPCurrExpAmt=0, lDblTotalDiffCSSCurrExpAmt=0;
        double lDblTotalDiffPLProgExpAmt=0, lDblTotalDiffNPProgExpAmt=0, lDblTotalDiffCSSProgExpAmt=0;
        
        BudExpEstDeptVO lObjDeptVO = new BudExpEstDeptVO();
        
        Map lHashBudData = lObjRptDAO.getAllDeptPLNPCSSBudAmt(lStrFinYr, lObjReport.getLangId());
        Map lHashCurrYrExpData = lObjRptDAO.getAllDeptPLNPCSSCurrExpAmt(lDtStartDate, lDtEndDate, lObjReport.getLangId());
        Map lHashCurrYrProgData = lObjRptDAO.getAllDeptPLNPCSSCurrExpAmt(lDtCurrFinYrStartDt, lDtEndDate, lObjReport.getLangId());
        
        glogger.info("lHashCurrYrExpData is : " + lHashCurrYrExpData);
        glogger.info("lHashCurrYrProgData is : " + lHashCurrYrProgData);
             
        // Commented to fetch the grant data from Kamlesh's collected data.
        //Map lHashGrantData = ObjRptDAO.getAllDeptPLNPCSSGrantAmt(lStrFinYr,lObjReport.getLangId());
        Map lHashGrantData = lObjRptDAO.getAllDeptGrantCollAmt(lStrFinYr,lDtStartDate, lDtEndDate,lObjReport.getLangId());
        
        //Map lHashPrevYrProgAmt = lObjRptDAO.getAllDeptPLNPCSSCurrExpAmt(lDtPrevFinYrStartDate, lDtPrevFinYrEndDate, lObjReport.getLangId());
        
        Map lHashCurrYrExpDataFrmBill = lObjRptDAO.getAllDeptPLNPCSSActualExpAmt(lStrFinYr,lDtStartDate, lDtEndDate, lObjReport.getLangId());
        Map lHashCurrYrProgDataFrmBill =lObjRptDAO.getAllDeptPLNPCSSActualExpAmt(lStrFinYr,lDtCurrFinYrStartDt, lDtEndDate, lObjReport.getLangId());
        
        glogger.info("lHashCurrYrExpDataFrmBill is : " + lHashCurrYrExpDataFrmBill);
        glogger.info("lHashCurrYrProgDataFrmBill is : " + lHashCurrYrProgDataFrmBill);
        
        String lStrDeptId = "";
        double lDblRETotal = 0;
        
        double lDblPercntGrnt = 0;
        double lDblPercntBud = 0;
        long lLnglTotalGrant = 0;
        long lLngTotalBudAmt=0;
        
        int lIntTmpDateDiff = (int) ((lDtEndDate.getTime() - lDtStartDate.getTime())/(1000*60*60*24));  
        HashMap lHshMapTemp = getDates("",lStrToDate);
        int lIntPrgDiffDays = Integer.parseInt(lHshMapTemp.get("prgDifference").toString());
        glogger.info("Progressive difference in Days -- "+lIntPrgDiffDays);
        
        for(Object lObjTmp : lArrDeptList)
        {
          ArrayList<Object> lArrInner = new ArrayList<Object>();
          
          lObjDeptVO = (BudExpEstDeptVO) lObjTmp;
          lStrDeptId= lObjDeptVO.getDeptID();
          
          lDblPLBudAmt = (lHashBudData.get(lStrDeptId + "_PL") != null) ? Long.parseLong(lHashBudData.get(lStrDeptId + "_PL").toString()) : 0;
          lLngTotalPLBudAmt +=  Math.round(lDblPLBudAmt / 10000000);
          lDblTotalDiffPLBudAmt += Math.round(lDblPLBudAmt / 10000000) - (lDblPLBudAmt / 10000000); 
          
          lDblNPBudAmt = (lHashBudData.get(lStrDeptId + "_NP") != null) ? Long.parseLong(lHashBudData.get(lStrDeptId + "_NP").toString()) : 0;
          lLngTotalNPBudAmt +=  Math.round(lDblNPBudAmt / 10000000);
          lDblTotalDiffNPBudAmt += Math.round(lDblNPBudAmt / 10000000)- (lDblNPBudAmt / 10000000);
     
          lDblCSSBudAmt = (lHashBudData.get(lStrDeptId + "_CSS") != null) ? Long.parseLong(lHashBudData.get(lStrDeptId + "_CSS").toString()) : 0;
          lLngTotalCSSBudAmt +=  Math.round(lDblCSSBudAmt / 10000000);
          lDblTotalDiffCSSBudAmt += Math.round(lDblCSSBudAmt / 10000000)- (lDblCSSBudAmt / 10000000);
                  
          lDblPLGrntAmt = (lHashGrantData.get(lStrDeptId + "_PL") != null) ? 
          (Long.parseLong(lHashGrantData.get(lStrDeptId + "_PL").toString()) *  lIntDateDiff) /lIntMaxMnth : 0;
          lLngTotalPLGrntAmt +=  Math.round(lDblPLGrntAmt / 10000000);
          lDblTotalDiffPLGrntAmt += Math.round(lDblPLGrntAmt / 10000000) - (lDblPLGrntAmt / 10000000);
          
          lDblNPGrntAmt = (lHashGrantData.get(lStrDeptId + "_NP") != null) ? 
          (Long.parseLong(lHashGrantData.get(lStrDeptId + "_NP").toString()) *  lIntDateDiff) /lIntMaxMnth : 0;
          lLngTotalNPGrntAmt +=  Math.round(lDblNPGrntAmt / 10000000);
          lDblTotalDiffNPGrntAmt += Math.round(lDblNPGrntAmt / 10000000) - (lDblNPGrntAmt / 10000000);
          
          lDblCSSGrntAmt = (lHashGrantData.get(lStrDeptId + "_CSS") != null) ? 
          (Long.parseLong(lHashGrantData.get(lStrDeptId + "_CSS").toString()) *  lIntDateDiff) /lIntMaxMnth : 0;
          lLngTotalCSSGrntAmt +=  Math.round(lDblCSSGrntAmt / 10000000);
          lDblTotalDiffCSSGrntAmt += Math.round(lDblCSSGrntAmt / 10000000) - (lDblCSSGrntAmt / 10000000);
          
          lDblPLCurrExpAmt = (lHashCurrYrExpData.get(lStrDeptId + "_PL") != null) ? Long.parseLong(lHashCurrYrExpData.get(lStrDeptId + "_PL").toString()) : 0;
          lDblNPCurrExpAmt = (lHashCurrYrExpData.get(lStrDeptId + "_NP") != null) ? Long.parseLong(lHashCurrYrExpData.get(lStrDeptId + "_NP").toString()) : 0;
          lDblCSSCurrExpAmt = (lHashCurrYrExpData.get(lStrDeptId + "_CSS") != null) ? Long.parseLong(lHashCurrYrExpData.get(lStrDeptId + "_CSS").toString()) : 0;
          
          lDblPLProgExpAmt = (lHashCurrYrProgData.get(lStrDeptId + "_PL") != null) ? Long.parseLong(lHashCurrYrProgData.get(lStrDeptId + "_PL").toString()) : 0;
          lDblNPProgExpAmt = (lHashCurrYrProgData.get(lStrDeptId + "_NP") != null) ? Long.parseLong(lHashCurrYrProgData.get(lStrDeptId + "_NP").toString()) : 0;
          lDblCSSProgExpAmt = (lHashCurrYrProgData.get(lStrDeptId + "_CSS") != null) ? Long.parseLong(lHashCurrYrProgData.get(lStrDeptId + "_CSS").toString()) : 0;

          lDblPLCurrExpAmtFrmBill= (lHashCurrYrExpDataFrmBill.get(lStrDeptId + "_PL") != null) ? Long.parseLong(lHashCurrYrExpDataFrmBill.get(lStrDeptId + "_PL").toString()) : 0; 
          lDblNPCurrExpAmtFrmBill= (lHashCurrYrExpDataFrmBill.get(lStrDeptId + "_NP") != null) ? Long.parseLong(lHashCurrYrExpDataFrmBill.get(lStrDeptId + "_NP").toString()) : 0;
          // Not getting CSS as of now.
          lDblCSSCurrExpAmtFrmBill= (lHashCurrYrExpDataFrmBill.get(lStrDeptId + "_CSS") != null) ? Long.parseLong(lHashCurrYrExpDataFrmBill.get(lStrDeptId + "_CSS").toString()) : 0;
          
          lDblPLProgExpAmtFrmBill= (lHashCurrYrProgDataFrmBill.get(lStrDeptId + "_PL") != null) ? Long.parseLong(lHashCurrYrProgDataFrmBill.get(lStrDeptId + "_PL").toString()) : 0;
          lDblNPProgExpAmtFrmBill= (lHashCurrYrProgDataFrmBill.get(lStrDeptId + "_NP") != null) ? Long.parseLong(lHashCurrYrProgDataFrmBill.get(lStrDeptId + "_NP").toString()) : 0;
//        Not getting CSS as of now.
          lDblCSSProgExpAmtFrmBill= (lHashCurrYrProgDataFrmBill.get(lStrDeptId + "_CSS") != null) ? Long.parseLong(lHashCurrYrProgDataFrmBill.get(lStrDeptId + "_CSS").toString()) : 0;
          
          lArrInner.add(++lIntCnt);      // No
          lArrInner.add(lObjDeptVO.getDeptName()); // Dept Name
          
          lArrInner.add(getDisplayAmt(Math.round(lDblPLBudAmt / 10000000)));	// Budget Exp. Plan
          lArrInner.add(getDisplayAmt(Math.round(lDblNPBudAmt / 10000000)));	// Budget Exp. NonPlan
          lArrInner.add(getDisplayAmt(Math.round(lDblCSSBudAmt / 10000000)));	// Budget Exp. CSS
          
          StyledData dataStyle = new StyledData();
          dataStyle.setStyles(reportStyleVO);
          dataStyle.setData( getDisplayAmt(Math.round(lDblPLBudAmt / 10000000) + Math.round(lDblNPBudAmt / 10000000) 
        		  + Math.round(lDblCSSBudAmt / 10000000)) );
          lArrInner.add(dataStyle);	// Budget Exp. Total
          
          
         
          lArrInner.add(getDisplayAmt(Math.round(lDblPLGrntAmt / 10000000)));	// Grant Plan
          lArrInner.add(getDisplayAmt(Math.round(lDblNPGrntAmt / 10000000)));	// Grant NonPlan
          lArrInner.add(getDisplayAmt(Math.round(lDblCSSGrntAmt / 10000000)));	// Grant CSS
          
          dataStyle = new StyledData();
          dataStyle.setStyles(reportStyleVO);
          dataStyle.setData(getDisplayAmt(Math.round(lDblPLGrntAmt / 10000000) + Math.round(lDblNPGrntAmt / 10000000) + 
        		  Math.round(lDblCSSGrntAmt / 10000000)));
          lArrInner.add(dataStyle);	// Grant Total
          
          lLngTotalPLCurrExpAmt += Math.round((lDblPLCurrExpAmt + lDblPLCurrExpAmtFrmBill) / 10000000);
          lDblTotalDiffPLCurrExpAmt += Math.round((lDblPLCurrExpAmt + lDblPLCurrExpAmtFrmBill) / 10000000)
          - ((lDblPLCurrExpAmt + lDblPLCurrExpAmtFrmBill) / 10000000);
 
          lArrInner.add(getDisplayAmt(Math.round((lDblPLCurrExpAmt + lDblPLCurrExpAmtFrmBill) / 10000000)));
   
          lLngTotalNPCurrExpAmt += Math.round((lDblNPCurrExpAmt + lDblNPCurrExpAmtFrmBill) / 10000000);
          lDblTotalDiffNPCurrExpAmt += Math.round((lDblNPCurrExpAmt + lDblNPCurrExpAmtFrmBill) / 10000000)
          - ((lDblNPCurrExpAmt + lDblNPCurrExpAmtFrmBill) / 10000000);
          
          lArrInner.add(getDisplayAmt(Math.round((lDblNPCurrExpAmt + lDblNPCurrExpAmtFrmBill) / 10000000)));

          lLngTotalCSSCurrExpAmt += Math.round((lDblCSSCurrExpAmt + lDblCSSCurrExpAmtFrmBill) / 10000000);
          lDblTotalDiffCSSCurrExpAmt += Math.round((lDblCSSCurrExpAmt + lDblCSSCurrExpAmtFrmBill) / 10000000)
          - ((lDblCSSCurrExpAmt + lDblCSSCurrExpAmtFrmBill) / 10000000);
          
          lArrInner.add(getDisplayAmt(Math.round((lDblCSSCurrExpAmt + lDblCSSCurrExpAmtFrmBill) / 10000000)));
          
          dataStyle = new StyledData();
          dataStyle.setStyles(reportStyleVO);
          dataStyle.setData(getDisplayAmt(Math.round((lDblPLCurrExpAmt + lDblPLCurrExpAmtFrmBill) / 10000000) + 
        		  Math.round((lDblNPCurrExpAmt + lDblNPCurrExpAmtFrmBill) / 10000000) +
        		  Math.round((lDblCSSCurrExpAmt + lDblCSSCurrExpAmtFrmBill) / 10000000)));
          lArrInner.add(dataStyle);
          
          lLngTotalPLProgExpAmt += Math.round((lDblPLProgExpAmt + lDblPLProgExpAmtFrmBill) / 10000000);
          lDblTotalDiffPLProgExpAmt += Math.round((lDblPLProgExpAmt + lDblPLProgExpAmtFrmBill) / 10000000)
          - ((lDblPLProgExpAmt + lDblPLProgExpAmtFrmBill) / 10000000);
          
          lArrInner.add(getDisplayAmt(Math.round((lDblPLProgExpAmt + lDblPLProgExpAmtFrmBill) / 10000000)));
          
          lLngTotalNPProgExpAmt += Math.round((lDblNPProgExpAmt + lDblNPProgExpAmtFrmBill) / 10000000);
          lDblTotalDiffNPProgExpAmt += Math.round((lDblNPProgExpAmt + lDblNPProgExpAmtFrmBill) / 10000000)
          - ((lDblNPProgExpAmt + lDblNPProgExpAmtFrmBill) / 10000000);
          
          lArrInner.add(getDisplayAmt(Math.round((lDblNPProgExpAmt + lDblNPProgExpAmtFrmBill) / 10000000)));
          
          lLngTotalCSSProgExpAmt += Math.round((lDblCSSProgExpAmt + lDblCSSProgExpAmtFrmBill) / 10000000);
          lDblTotalDiffCSSProgExpAmt += Math.round((lDblCSSProgExpAmt + lDblCSSProgExpAmtFrmBill) / 10000000)
          - ((lDblCSSProgExpAmt + lDblCSSProgExpAmtFrmBill) / 10000000);
          lArrInner.add(getDisplayAmt(Math.round((lDblCSSProgExpAmt + lDblCSSProgExpAmtFrmBill) / 10000000)));
          
          dataStyle = new StyledData();
          dataStyle.setStyles(reportStyleVO);
          dataStyle.setData(getDisplayAmt(Math.round((lDblPLProgExpAmt + lDblPLProgExpAmtFrmBill) / 10000000) +
        		  Math.round((lDblNPProgExpAmt + lDblNPProgExpAmtFrmBill) / 10000000) +
        		  Math.round((lDblCSSProgExpAmt + lDblCSSProgExpAmtFrmBill) / 10000000)));
          lArrInner.add( dataStyle);
          
          /*lDblPrevPLProgExpAmt = (lHashPrevYrProgAmt.get(lStrDeptId + "_PL") != null) ? Long.parseLong(lHashPrevYrProgAmt.get(lStrDeptId + "_PL").toString()) : 0;
          lDblPrevNPProgExpAmt = (lHashPrevYrProgAmt.get(lStrDeptId + "_NP") != null) ? Long.parseLong(lHashPrevYrProgAmt.get(lStrDeptId + "_NP").toString()) : 0;
          lDblPrevCSSProgExpAmt = (lHashPrevYrProgAmt.get(lStrDeptId + "_CSS") != null) ? Long.parseLong(lHashPrevYrProgAmt.get(lStrDeptId + "_CSS").toString()) : 0;
          
          lDblRETotal = lObjRptDAO.getTotalREAmt(lStrFinYr, lStrDeptId);
          lDblPrevProgTotal = ((lDblPrevPLProgExpAmt + lDblPrevNPProgExpAmt + lDblPrevCSSProgExpAmt) * 100) / lDblRETotal ;
          
          glogger.info("lDblPrevProgTotal is : " + (lDblPrevPLProgExpAmt + lDblPrevNPProgExpAmt + lDblPrevCSSProgExpAmt) + " and RE is  :" + lDblRETotal);*/
          
          lLnglTotalGrant = Math.round(lDblPLGrntAmt / 10000000) + Math.round(lDblNPGrntAmt / 10000000) + 
		  Math.round(lDblCSSGrntAmt / 10000000);
  
		  if(lLnglTotalGrant == 0)
		  {
			  lLnglTotalGrant = 1;
		  }
		  
		  lDblPercntGrnt = ((Math.round((lDblPLCurrExpAmt + lDblPLCurrExpAmtFrmBill) / 10000000) + 
		  Math.round((lDblNPCurrExpAmt + lDblNPCurrExpAmtFrmBill) / 10000000) +
		  Math.round((lDblCSSCurrExpAmt + lDblCSSCurrExpAmtFrmBill) / 10000000)) * 100 * 91) 
		  / (lLnglTotalGrant * lIntTmpDateDiff);
		  // 91 hardcoded for total grant period. Need to change afterwards.
		  
		  lLngTotalBudAmt = Math.round(lDblPLBudAmt / 10000000) + Math.round(lDblNPBudAmt / 10000000) 
					  + Math.round(lDblCSSBudAmt / 10000000);
		  
		  if(lLngTotalBudAmt ==0)
		  {
			  lLngTotalBudAmt = 1;
		  }
		  
		  lDblPercntBud = ((Math.round((lDblPLProgExpAmt + lDblPLProgExpAmtFrmBill) / 10000000)
				+ Math.round((lDblNPProgExpAmt + lDblNPProgExpAmtFrmBill) / 10000000) 
				+ Math.round((lDblCSSProgExpAmt + lDblCSSProgExpAmtFrmBill) / 10000000)) * 100) / lLngTotalBudAmt;

          lArrInner.add(Math.round(lDblPercntGrnt));
          lArrInner.add(Math.round(lDblPercntBud));
          
         // lArrInner.add(lDblPrevProgTotal > 0 ? lnfLong.format(lDblPrevProgTotal) : "-");
          lArrInner.add("-");
          
          lArrReturn.add(lArrInner);
        }
        
        ArrayList<Object> lArrInner = new ArrayList<Object>();
        lArrInner.add("");
        StyledData dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData("Total");
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalPLBudAmt));
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalNPBudAmt));
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalCSSBudAmt));
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalPLBudAmt + lLngTotalNPBudAmt + lLngTotalCSSBudAmt));
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalPLGrntAmt));
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalNPGrntAmt));
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalCSSGrntAmt));
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalPLGrntAmt + lLngTotalNPGrntAmt + lLngTotalCSSGrntAmt));
        lArrInner.add(dataStyle);
        
        double ldbPerPlanPrg = 0;
        double ldbPerNPPrg = 0;
        double ldbPerCSSPrg = 0;
        double ldbTotalPerPrg = 0;
        
        ldbPerPlanPrg = (lLngTotalPLCurrExpAmt * 100)/((lLngTotalPLGrntAmt*lIntPrgDiffDays)/91);
        ldbPerNPPrg = (lLngTotalNPCurrExpAmt * 100)/((lLngTotalNPGrntAmt*lIntPrgDiffDays)/91);
        ldbPerCSSPrg = (lLngTotalCSSCurrExpAmt * 100)/((lLngTotalCSSGrntAmt*lIntPrgDiffDays)/91);
        ldbTotalPerPrg = ((lLngTotalPLCurrExpAmt + lLngTotalNPCurrExpAmt + lLngTotalCSSCurrExpAmt) * 100)/(((lLngTotalPLGrntAmt + lLngTotalNPGrntAmt + lLngTotalCSSGrntAmt)*lIntTmpDateDiff)/91);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalPLCurrExpAmt)+"("+Math.round(ldbPerPlanPrg)+"%)");
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalNPCurrExpAmt)+"("+Math.round(ldbPerNPPrg)+"%)");
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalCSSCurrExpAmt)+"("+Math.round(ldbPerCSSPrg)+"%)");
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalPLCurrExpAmt + lLngTotalNPCurrExpAmt + lLngTotalCSSCurrExpAmt)+"("+Math.round(ldbTotalPerPrg)+"%)");
        lArrInner.add(dataStyle);
        
        ldbPerPlanPrg = 0;
        ldbPerNPPrg = 0;
        ldbPerCSSPrg = 0;
        ldbTotalPerPrg = 0;
        
        ldbPerPlanPrg = (lLngTotalPLProgExpAmt * 100)/((lLngTotalPLGrntAmt*lIntPrgDiffDays)/91);
        ldbPerNPPrg = (lLngTotalNPProgExpAmt * 100)/((lLngTotalNPGrntAmt*lIntPrgDiffDays)/91);
        ldbPerCSSPrg = (lLngTotalCSSProgExpAmt * 100)/((lLngTotalCSSGrntAmt*lIntPrgDiffDays)/91);
        ldbTotalPerPrg = ((lLngTotalPLProgExpAmt + lLngTotalNPProgExpAmt + lLngTotalCSSProgExpAmt) * 100)/(((lLngTotalPLGrntAmt + lLngTotalNPGrntAmt + lLngTotalCSSGrntAmt)*lIntPrgDiffDays)/91);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalPLProgExpAmt)+"("+Math.round(ldbPerPlanPrg)+"%)");
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalNPProgExpAmt)+"("+Math.round(ldbPerNPPrg)+"%)");
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalCSSProgExpAmt)+"("+Math.round(ldbPerCSSPrg)+"%)");
        lArrInner.add(dataStyle);
        
        dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(getDisplayAmt(lLngTotalPLProgExpAmt + lLngTotalNPProgExpAmt + lLngTotalCSSProgExpAmt)+"("+Math.round(ldbTotalPerPrg)+"%)");
        lArrInner.add(dataStyle);
        
        
        lDblPercntGrnt = (((lLngTotalPLCurrExpAmt + lLngTotalNPCurrExpAmt + lLngTotalCSSCurrExpAmt) * 100 * 91) 
		  / ((lLngTotalPLGrntAmt + lLngTotalNPGrntAmt + lLngTotalCSSGrntAmt) * lIntTmpDateDiff));
		  // 91 hardcoded for total grant period. Need to change afterwards.
		  
		lLngTotalBudAmt = lLngTotalPLBudAmt + lLngTotalNPBudAmt + lLngTotalCSSBudAmt;
		  
		if(lLngTotalBudAmt ==0)
		{
			lLngTotalBudAmt = 1;
		}
		  
		lDblPercntBud = ((lLngTotalPLProgExpAmt + lLngTotalNPProgExpAmt + lLngTotalCSSProgExpAmt) * 100) / lLngTotalBudAmt;
        
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(Math.round(lDblPercntGrnt));
		lArrInner.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(Math.round(lDblPercntBud));
        lArrInner.add(dataStyle);
        //lArrInner.add("");
        //lArrInner.add("");
        lArrInner.add("-");
        
        lArrReturn.add(lArrInner);
        
        lArrInner = new ArrayList<Object>();
        lArrInner.add("");
        lArrInner.add("Rounding Mismatch");
        lArrInner.add(lnfLong.format(lDblTotalDiffPLBudAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffNPBudAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffCSSBudAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffPLBudAmt + lDblTotalDiffNPBudAmt + lDblTotalDiffCSSBudAmt));
        
        lArrInner.add(lnfLong.format(lDblTotalDiffPLGrntAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffNPGrntAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffCSSGrntAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffPLGrntAmt + lDblTotalDiffNPGrntAmt + lDblTotalDiffCSSGrntAmt));
        
        lArrInner.add(lnfLong.format(lDblTotalDiffPLCurrExpAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffNPCurrExpAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffCSSCurrExpAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffPLCurrExpAmt + lDblTotalDiffNPCurrExpAmt + lDblTotalDiffCSSCurrExpAmt));
        
        lArrInner.add(lnfLong.format(lDblTotalDiffPLProgExpAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffNPProgExpAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffCSSProgExpAmt));
        lArrInner.add(lnfLong.format(lDblTotalDiffPLProgExpAmt + lDblTotalDiffNPProgExpAmt + lDblTotalDiffCSSProgExpAmt));
        
        lArrInner.add("-");
        lArrInner.add("-");
        lArrInner.add("-");
        
        lArrReturn.add(lArrInner);     
        
        return lArrReturn;
    }

	
	private List getStateProfileReport(ReportVO lObjReport)
	{
		glogger.info("Going into getStateProfileReport");
		String lStrFromDate = null;
		String lStrPrevFromDate = null;
		String lStrToDate = null;
		String lStrPrevToDate = null;
		String lStrLangId = "";
		String lStrFundNm = "";
		int lintFinYear = 0;
		long ldiffDays = 0;
		int lIntPrgDiffDays = 0;
		int lintPrevFinYear = 0;
		
		CommonRptQueryDAO lObjRptQueryDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
		
		glogger.info("FromDate == "+lObjReport.getParameterValue("Datefrom"));
		glogger.info("fromDate == "+lObjReport.getParameterValue("Datefrom"));

		if(lObjReport.getParameterValue("Datefrom")!=null)
			lStrFromDate = (String)lObjReport.getParameterValue("Datefrom");
		
		if(lObjReport.getParameterValue("Dateto")!=null)
			lStrToDate = (String)lObjReport.getParameterValue("Dateto");
		
		HashMap lhshDateMap = getDates(lStrFromDate,lStrToDate);
		lStrFromDate = lhshDateMap.get("fromdate").toString();
		lStrToDate = lhshDateMap.get("todate").toString();
		ldiffDays = Long.parseLong(lhshDateMap.get("difference").toString());
		lIntPrgDiffDays = Integer.parseInt(lhshDateMap.get("prgDifference").toString());
			
		//------------------------------------------------
		
		glogger.info("--------- Calling getPreviousDates ---------");
		HashMap lhshPrevDateMap = getPreviousDates(lStrFromDate,lStrToDate);
		lStrPrevFromDate=lhshPrevDateMap.get("fromdate").toString();
		lStrPrevToDate=lhshPrevDateMap.get("todate").toString();
		
		glogger.info("FromDate after getDates() -- "+lStrFromDate);
		glogger.info("ToDate after getDates() -- "+lStrToDate);
		glogger.info("Difference in Dates after getDates() -- "+ldiffDays);
		glogger.info("Progressive Difference in Dates after getDates() -- "+lIntPrgDiffDays);
		glogger.info("Previous FromDate -- "+lStrPrevFromDate);
		glogger.info("Previous ToDate -- "+lStrPrevToDate);
		
		lintFinYear = lObjRptQueryDAO.getFinancialYear(lStrFromDate,lStrToDate);
		lintPrevFinYear=6;//<--need to get this from some method
		glogger.info("FinYear -------- "+lintFinYear);
		
		lStrLangId = lObjReport.getLangId();
		
		List lArrMainDataLst = new ArrayList();
		
		NumberFormat lnfLong=NumberFormat.getNumberInstance();
        lnfLong.setGroupingUsed(false);
        lnfLong.setMaximumFractionDigits(3);
        lnfLong.setMinimumFractionDigits(3);
		
		HashMap lhshConsolidatedFund = getConsolidatedFund(lintFinYear, lStrLangId,lStrFromDate,lStrToDate);
		HashMap lhshContingencyFund = getContingencyPublicFund(lintFinYear, lStrLangId,lStrFromDate,lStrToDate,4);
		HashMap lhshPublicFund = getContingencyPublicFund(lintFinYear, lStrLangId,lStrFromDate,lStrToDate,5);
		
		ArrayList lArrReturn = new ArrayList();
		lArrReturn.add(" <b>I-Consolidated Fund</b>");
		for(int i=0;i<20;i++)
			lArrReturn.add("");
		lArrMainDataLst.add(lArrReturn);
		
		//-------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		long lBudPLRevRecp =  0;
		long lBudNPRevRecp = 0;
		long lBudCSSRevRecp = 0;
		long lBudTotalRevRecp = 0;
		long lGrantPLRevRecp =  0;
		long lGrantNPRevRecp = 0;
		long lGrantCSSRevRecp = 0;
		long lGrantTotalRevRecp = 0;
		long lCurrPLRevRecp =  0;
		long lCurrNPRevRecp = 0;
		long lCurrCSSRevRecp = 0;
		long lCurrTotalRevRecp = 0;
		long lPrgPLRevRecp =  0;
		long lPrgNPRevRecp = 0;
		long lPrgCSSRevRecp = 0;
		long lPrgTotalRevRecp = 0;
		
		double ldbBudPLFracPart = 0.0;
		double ldbBudNPFracPart = 0.0;
		double ldbBudCSSFracPart = 0.0;
		double ldbBudTotalFracPart = 0.0;
		double ldbGrantPLFracPart = 0.0;
		double ldbGrantNPFracPart = 0.0;
		double ldbGrantCSSFracPart = 0.0;
		double ldbGrantTotalFracPart = 0.0;
		double ldbCurrPLFracPart = 0.0;
		double ldbCurrNPFracPart = 0.0;
		double ldbCurrCSSFracPart = 0.0;
		double ldbCurrTotalFracPart = 0.0;
		double ldbPrgPLFracPart = 0.0;
		double ldbPrgNPFracPart = 0.0;
		double ldbPrgCSSFracPart = 0.0;
		double ldbPrgTotalFracPart = 0.0;
		
		lStrFundNm = "RevRecp";
		lArrReturn.add("1) Revenue Receipt");
		//-----------------Budget Start---------------
		lBudPLRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_PL").toString()));
		ldbBudPLFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_PL").toString()) - lBudPLRevRecp;
		lArrReturn.add(lBudPLRevRecp!=0?lBudPLRevRecp:"-");

		lBudNPRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_NP").toString()));
		ldbBudNPFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_NP").toString()) - lBudNPRevRecp;
		lArrReturn.add(lBudNPRevRecp!=0?lBudNPRevRecp:"-");
		
		lBudCSSRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_CSS").toString()));
		ldbBudCSSFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_CSS").toString()) - lBudCSSRevRecp;
		lArrReturn.add(lBudCSSRevRecp!=0?lBudCSSRevRecp:"-");
		
		lBudTotalRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()));
		ldbBudTotalFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()) - lBudTotalRevRecp;
		
		StyledData dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(Math.round(lBudTotalRevRecp));
		lArrReturn.add(lBudTotalRevRecp!=0?dataStyle:"-");
		//-----------------Budget End---------------
		
		//-----------------Grant Start---------------
		/*lGrantPLRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_PL").toString()));
		ldbGrantPLFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_PL").toString()) - lGrantPLRevRecp;
		lArrReturn.add(lGrantPLRevRecp!=0?lGrantPLRevRecp:"-");

		lGrantNPRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_NP").toString()));
		ldbGrantNPFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_NP").toString()) - lGrantNPRevRecp;
		lArrReturn.add(lGrantNPRevRecp!=0?lGrantNPRevRecp:"-");
		
		
		lGrantCSSRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_CSS").toString()));
		ldbGrantCSSFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_CSS").toString()) - lGrantCSSRevRecp;
		lArrReturn.add(lGrantCSSRevRecp!=0?lGrantCSSRevRecp:"-");
		
		
		lGrantTotalRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()));
		ldbGrantTotalFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()) - lGrantTotalRevRecp;
		lArrReturn.add(lGrantTotalRevRecp!=0?"<b>"+lGrantTotalRevRecp+"</b>":"-");*/
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData("-");
		lArrReturn.add(dataStyle);
		//-----------------Grant End---------------
		
		//-----------------Curr Start---------------
		lCurrPLRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_PL").toString()));
		ldbCurrPLFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_PL").toString()) - lCurrPLRevRecp;
		lArrReturn.add(lCurrPLRevRecp!=0?lCurrPLRevRecp:"-");

		lCurrNPRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_NP").toString()));
		ldbCurrNPFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_NP").toString()) - lCurrNPRevRecp;
		lArrReturn.add(lCurrNPRevRecp!=0?lCurrNPRevRecp:"-");
		
		lCurrCSSRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_CSS").toString()));
		ldbCurrCSSFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_CSS").toString()) - lCurrCSSRevRecp;
		lArrReturn.add(lCurrCSSRevRecp!=0?lCurrCSSRevRecp:"-");
		
		lCurrTotalRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_Total").toString()));
		ldbCurrTotalFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_Total").toString()) - lCurrTotalRevRecp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lCurrTotalRevRecp);
		lArrReturn.add(lCurrTotalRevRecp!=0?dataStyle:"-");
		//-----------------Curr End---------------
		
		//-----------------Prg Start---------------
		lPrgPLRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_PL").toString()));
		ldbPrgPLFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_PL").toString()) - lPrgPLRevRecp;
		lArrReturn.add(lPrgPLRevRecp!=0?lPrgPLRevRecp:"-");

		lPrgNPRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_NP").toString()));
		ldbCurrNPFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_NP").toString()) - lPrgNPRevRecp;
		lArrReturn.add(lPrgNPRevRecp!=0?lPrgNPRevRecp:"-");
		
		lPrgCSSRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_CSS").toString()));
		ldbPrgCSSFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_CSS").toString()) - lPrgCSSRevRecp;
		lArrReturn.add(lPrgCSSRevRecp!=0?lPrgCSSRevRecp:"-");
		
		lPrgTotalRevRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()));
		ldbPrgTotalFracPart = Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()) - lPrgTotalRevRecp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lPrgTotalRevRecp);
		lArrReturn.add(lPrgTotalRevRecp!=0?dataStyle:"-");
		//-----------------Prg End---------------
		
		/*if(lGrantTotalRevRecp!=0)
			lArrReturn.add((int)(lCurrTotalRevRecp*100*91)/(lGrantTotalRevRecp*ldiffDays));
		else*/
			lArrReturn.add("-");
		
		if(lBudTotalRevRecp!=0)
			lArrReturn.add((int)(lPrgTotalRevRecp*100)/lBudTotalRevRecp);
		else
			lArrReturn.add("-");
		
		/*int lintPerCurr = (int)(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_PercentOfCurrPrv").toString()));
		lArrReturn.add(lintPerCurr!=0?lintPerCurr:"-");
		
		int lintPerPrg = (int)(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_PercentOfProg").toString())); 
		lArrReturn.add(lintPerPrg!=0?lintPerPrg:"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrMainDataLst.add(lArrReturn);
	
		//--------------------------------------------------------------------------------
		lArrReturn = new ArrayList();
		long lBudPLRevExp =  0;
		long lBudNPRevExp = 0;
		long lBudCSSRevExp = 0;
		long lBudTotalRevExp = 0;
		long lGrantPLRevExp =  0;
		long lGrantNPRevExp = 0;
		long lGrantCSSRevExp = 0;
		long lGrantTotalRevExp = 0;
		long lCurrPLRevExp =  0;
		long lCurrNPRevExp = 0;
		long lCurrCSSRevExp = 0;
		long lCurrTotalRevExp = 0;
		long lPrgPLRevExp =  0;
		long lPrgNPRevExp = 0;
		long lPrgCSSRevExp = 0;
		long lPrgTotalRevExp = 0;
		
		lStrFundNm = "RevExp";
		lArrReturn.add("2) Revenue Expenditure");
//		-----------------Budget Start---------------
		lBudPLRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_PL").toString()));
		ldbBudPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_PL").toString()) - lBudPLRevExp;
		lArrReturn.add(lBudPLRevExp!=0?lBudPLRevExp:"-");

		lBudNPRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_NP").toString()));
		ldbBudNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_NP").toString()) - lBudNPRevExp;
		lArrReturn.add(lBudNPRevExp!=0?lBudNPRevExp:"-");
		
		lBudCSSRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_CSS").toString()));
		ldbBudCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_CSS").toString()) - lBudCSSRevExp;
		lArrReturn.add(lBudCSSRevExp!=0?lBudCSSRevExp:"-");
		
	//	lBudTotalRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()));
		lBudTotalRevExp = lBudPLRevExp+lBudNPRevExp+lBudCSSRevExp;
		ldbBudTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()) - lBudTotalRevExp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lBudTotalRevExp);
		lArrReturn.add(lBudTotalRevExp!=0?dataStyle:"-");
		//-----------------Budget End---------------
		
//		-----------------Grant Start---------------
		lGrantPLRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_PL").toString()));
		ldbGrantPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_PL").toString()) - lGrantPLRevExp;
		lArrReturn.add(lGrantPLRevExp!=0?lGrantPLRevExp:"-");

		lGrantNPRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_NP").toString()));
		ldbGrantNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_NP").toString()) - lGrantNPRevExp;
		lArrReturn.add(lGrantNPRevExp!=0?lGrantNPRevExp:"-");
		
		lGrantCSSRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_CSS").toString()));
		ldbGrantCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_CSS").toString()) - lGrantCSSRevExp;
		lArrReturn.add(lGrantCSSRevExp!=0?lGrantCSSRevExp:"-");
		
		//lGrantTotalRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()));
		lGrantTotalRevExp=lGrantPLRevExp+lGrantNPRevExp+lGrantCSSRevExp;
		ldbGrantTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()) - lGrantTotalRevExp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lGrantTotalRevExp);
		lArrReturn.add(lGrantTotalRevExp!=0?dataStyle:"-");
		//-----------------Grant End---------------
		
		//-----------------Curr Start---------------
		lCurrPLRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_PL").toString()));
		ldbCurrPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_PL").toString()) - lCurrPLRevExp;
		lArrReturn.add(lCurrPLRevExp!=0?lCurrPLRevExp:"-");

		lCurrNPRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_NP").toString()) - lCurrNPRevExp;
		lArrReturn.add(lCurrNPRevExp!=0?lCurrNPRevExp:"-");
		
		lCurrCSSRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_CSS").toString()));
		ldbCurrCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_CSS").toString()) - lCurrCSSRevExp;
		lArrReturn.add(lCurrCSSRevExp!=0?lCurrCSSRevExp:"-");
		
		//lCurrTotalRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_Total").toString()));
		lCurrTotalRevExp=lCurrPLRevExp+lCurrNPRevExp+lCurrCSSRevExp;
		ldbCurrTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_Total").toString()) - lCurrTotalRevExp;
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lCurrTotalRevExp);
		lArrReturn.add(lCurrTotalRevExp!=0?dataStyle:"-");
		//-----------------Curr End---------------
		
		//-----------------Prg Start---------------
		lPrgPLRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_PL").toString()));
		ldbPrgPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_PL").toString()) - lPrgPLRevExp;
		lArrReturn.add(lPrgPLRevExp!=0?lPrgPLRevExp:"-");

		lPrgNPRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_NP").toString()) - lPrgNPRevExp;
		lArrReturn.add(lPrgNPRevExp!=0?lPrgNPRevExp:"-");
		
		lPrgCSSRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_CSS").toString()));
		ldbPrgCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_CSS").toString()) - lPrgCSSRevExp;
		lArrReturn.add(lPrgCSSRevExp!=0?lPrgCSSRevExp:"-");
		
		//lPrgTotalRevExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()));
		lPrgTotalRevExp=lPrgPLRevExp+lPrgNPRevExp+lPrgCSSRevExp;
		ldbPrgTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()) - lPrgTotalRevExp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lPrgTotalRevExp);
		lArrReturn.add(lPrgTotalRevExp!=0?dataStyle:"-");
		//-----------------Prg End---------------
		glogger.info("---------------------------------------------------------------------------------");
		glogger.info("current total rev exp = "+lCurrTotalRevExp);
		glogger.info("grant total rev exp = "+lGrantTotalRevExp);
		glogger.info("diff of days : "+ldiffDays);
		if(lGrantTotalRevExp!=0)
			lArrReturn.add((int)(lCurrTotalRevExp*100*91)/(lGrantTotalRevExp*ldiffDays));
		else
			lArrReturn.add("-");
		
		if(lBudTotalRevExp!=0)
			lArrReturn.add((int)(lPrgTotalRevExp*100)/lBudTotalRevExp);
		else
			lArrReturn.add("-");
		
		//lintPerCurr = (int)(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_PercentOfCurrPrv").toString()));
		//lArrReturn.add(lintPerCurr!=0?lintPerCurr:"-");
		lArrReturn.add("-");
		
		//lintPerPrg = (int)(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_PercentOfProg").toString())); 
		//lArrReturn.add(lintPerPrg!=0?lintPerPrg:"-");
		lArrReturn.add("-");
		
		lArrMainDataLst.add(lArrReturn);
		
//		--------------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		lArrReturn.add("3) Surplus/Deficit(1-2)");
/*		lArrReturn.add((lBudPLRevRecp - lBudPLRevExp)!=0?(lBudPLRevRecp - lBudPLRevExp):"-");
		lArrReturn.add((lBudNPRevRecp - lBudNPRevExp)!=0?(lBudNPRevRecp - lBudNPRevExp):"-");
		lArrReturn.add((lBudCSSRevRecp - lBudCSSRevExp)!=0?(lBudCSSRevRecp - lBudCSSRevExp):"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lBudTotalRevRecp - lBudTotalRevExp));
		lArrReturn.add((lBudTotalRevRecp - lBudTotalRevExp)!=0?dataStyle:"-");
		
/*		lArrReturn.add((lGrantPLRevRecp - lGrantPLRevExp)!=0?(lGrantPLRevRecp - lGrantPLRevExp):"-");
		lArrReturn.add((lGrantNPRevRecp - lGrantNPRevExp)!=0?(lGrantNPRevRecp - lGrantNPRevExp):"-");
		lArrReturn.add((lGrantCSSRevRecp - lGrantCSSRevExp)!=0?(lGrantCSSRevRecp - lGrantCSSRevExp):"-");*/
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		//lArrReturn.add((lGrantTotalRevRecp - lGrantTotalRevExp)!=0?"<b>"+(lGrantTotalRevRecp - lGrantTotalRevExp)+"</b>":"-");
		
/*		lArrReturn.add((lCurrPLRevRecp - lCurrPLRevExp)!=0?(lCurrPLRevRecp - lCurrPLRevExp):"-");
		lArrReturn.add((lCurrNPRevRecp - lCurrNPRevExp)!=0?(lCurrNPRevRecp - lCurrNPRevExp):"-");
		lArrReturn.add((lCurrCSSRevRecp - lCurrCSSRevExp)!=0?(lCurrCSSRevRecp - lCurrCSSRevExp):"-");*/
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lCurrTotalRevRecp - lCurrTotalRevExp));
		lArrReturn.add((lCurrTotalRevRecp - lCurrTotalRevExp)!=0?dataStyle:"-");
		
/*		lArrReturn.add((lPrgPLRevRecp - lPrgPLRevExp)!=0?(lPrgPLRevRecp - lPrgPLRevExp):"-");
		lArrReturn.add((lPrgNPRevRecp - lPrgNPRevExp)!=0?(lPrgNPRevRecp - lPrgNPRevExp):"-");
		lArrReturn.add((lPrgCSSRevRecp - lPrgCSSRevExp)!=0?(lPrgCSSRevRecp - lPrgCSSRevExp):"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lPrgTotalRevRecp - lPrgTotalRevExp));
		lArrReturn.add((lPrgTotalRevRecp - lPrgTotalRevExp)!=0?dataStyle:"-");
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrMainDataLst.add(lArrReturn);
		
		//------------------------------------------------------------------------------
	
		lArrReturn = new ArrayList();
		long lBudPLCapRecp =  0;
		long lBudNPCapRecp = 0;
		long lBudCSSCapRecp = 0;
		long lBudTotalCapRecp = 0;
		long lGrantPLCapRecp =  0;
		long lGrantNPCapRecp = 0;
		long lGrantCSSCapRecp = 0;
		long lGrantTotalCapRecp = 0;
		long lCurrPLCapRecp =  0;
		long lCurrNPCapRecp = 0;
		long lCurrCSSCapRecp = 0;
		long lCurrTotalCapRecp = 0;
		long lPrgPLCapRecp =  0;
		long lPrgNPCapRecp = 0;
		long lPrgCSSCapRecp = 0;
		long lPrgTotalCapRecp = 0;
		
		lStrFundNm = "CapRecp";
		lArrReturn.add("4) Capital Receipt");
//		-----------------Budget Start---------------
		lBudPLCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_PL").toString()));
		ldbBudPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_PL").toString()) - lBudPLCapRecp;
		lArrReturn.add(lBudPLCapRecp!=0?lBudPLCapRecp:"-");

		lBudNPCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_NP").toString()));
		ldbBudNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_NP").toString()) - lBudNPCapRecp;
		lArrReturn.add(lBudNPCapRecp!=0?lBudNPCapRecp:"-");
		
		lBudCSSCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_CSS").toString()));
		ldbBudCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_CSS").toString()) - lBudCSSCapRecp;
		lArrReturn.add(lBudCSSCapRecp!=0?lBudCSSCapRecp:"-");
		
		lBudTotalCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()));
		ldbBudTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()) - lBudTotalCapRecp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lBudTotalCapRecp);
		lArrReturn.add(lBudTotalCapRecp!=0?dataStyle:"-");
		//-----------------Budget End---------------
		
//		-----------------Grant Start---------------
		/*lGrantPLCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_PL").toString()));
		ldbGrantPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_PL").toString()) - lGrantPLCapRecp;
		lArrReturn.add(lGrantPLCapRecp!=0?lGrantPLCapRecp:"-");

		lGrantNPCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_NP").toString()));
		ldbGrantNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_NP").toString()) - lGrantNPCapRecp;
		lArrReturn.add(lGrantNPCapRecp!=0?lGrantNPCapRecp:"-");
		
		lGrantCSSCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_CSS").toString()));
		ldbGrantCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_CSS").toString()) - lGrantCSSCapRecp;
		lArrReturn.add(lGrantCSSCapRecp!=0?lGrantCSSCapRecp:"-");
		
		lGrantTotalCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()));
		ldbGrantTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()) - lGrantTotalCapRecp;
		lArrReturn.add(lGrantTotalCapRecp!=0?"<b>"+lGrantTotalCapRecp+"</b>":"-");*/
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData("-");
		lArrReturn.add(dataStyle);
		//-----------------Grant End---------------
		
		//-----------------Curr Start---------------
		lCurrPLCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_PL").toString()));
		ldbCurrPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_PL").toString()) - lCurrPLCapRecp;
		lArrReturn.add(lCurrPLCapRecp!=0?lCurrPLCapRecp:"-");

		lCurrNPCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_NP").toString()) - lCurrNPCapRecp;
		lArrReturn.add(lCurrNPCapRecp!=0?lCurrNPCapRecp:"-");
		
		lCurrCSSCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_CSS").toString()));
		ldbCurrCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_CSS").toString()) - lCurrCSSCapRecp;
		lArrReturn.add(lCurrCSSCapRecp!=0?lCurrCSSCapRecp:"-");
		
		lCurrTotalCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_Total").toString()));
		ldbCurrTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_Total").toString()) - lCurrTotalCapRecp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lCurrTotalCapRecp);
		lArrReturn.add(lCurrTotalCapRecp!=0?dataStyle:"-");
		//-----------------Curr End---------------
		
		//-----------------Prg Start---------------
		lPrgPLCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_PL").toString()));
		ldbPrgPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_PL").toString()) - lPrgPLCapRecp;
		lArrReturn.add(lPrgPLCapRecp!=0?lPrgPLCapRecp:"-");

		lPrgNPCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_NP").toString()) - lPrgNPCapRecp;
		lArrReturn.add(lPrgNPCapRecp!=0?lPrgNPCapRecp:"-");
		
		lPrgCSSCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_CSS").toString()));
		ldbPrgCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_CSS").toString()) - lPrgCSSCapRecp;
		lArrReturn.add(lPrgCSSCapRecp!=0?lPrgCSSCapRecp:"-");
		
		lPrgTotalCapRecp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()));
		ldbPrgTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()) - lPrgTotalCapRecp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lPrgTotalCapRecp);
		lArrReturn.add(lPrgTotalCapRecp!=0?dataStyle:"-");
		//-----------------Prg End---------------
		
		/*if(lGrantTotalCapRecp!=0)
			lArrReturn.add((int)(lCurrTotalCapRecp*100*91)/(lGrantTotalCapRecp*ldiffDays));
		else*/
			lArrReturn.add("-");
		
		if(lBudTotalCapRecp!=0)
			lArrReturn.add((int)(lPrgTotalCapRecp*100)/lBudTotalCapRecp);
		else
			lArrReturn.add("-");
		
		//lintPerCurr = (int)(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_PercentOfCurrPrv").toString()));
		//lArrReturn.add(lintPerCurr!=0?lintPerCurr:"-");
		lArrReturn.add("-");
		
		//lintPerPrg = (int)(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_PercentOfProg").toString())); 
		//lArrReturn.add(lintPerPrg!=0?lintPerPrg:"-");
		lArrReturn.add("-");
		lArrMainDataLst.add(lArrReturn);
		
		//--------------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		long lBudPLCapExp =  0;
		long lBudNPCapExp = 0;
		long lBudCSSCapExp = 0;
		long lBudTotalCapExp = 0;
		long lGrantPLCapExp =  0;
		long lGrantNPCapExp = 0;
		long lGrantCSSCapExp = 0;
		long lGrantTotalCapExp = 0;
		long lCurrPLCapExp =  0;
		long lCurrNPCapExp = 0;
		long lCurrCSSCapExp = 0;
		long lCurrTotalCapExp = 0;
		long lPrgPLCapExp =  0;
		long lPrgNPCapExp = 0;
		long lPrgCSSCapExp = 0;
		long lPrgTotalCapExp = 0;
		
		lStrFundNm = "CapExp";
		lArrReturn.add("5) Captial Expenditure");
//		-----------------Budget Start---------------
		lBudPLCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_PL").toString()));
		ldbBudPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_PL").toString()) - lBudPLCapExp;
		lArrReturn.add(lBudPLCapExp!=0?lBudPLCapExp:"-");

		lBudNPCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_NP").toString()));
		ldbBudNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_NP").toString()) - lBudNPCapExp;
		lArrReturn.add(lBudNPCapExp!=0?lBudNPCapExp:"-");
		
		lBudCSSCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_CSS").toString()));
		ldbBudCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_CSS").toString()) - lBudCSSCapExp;
		lArrReturn.add(lBudCSSCapExp!=0?lBudCSSCapExp:"-");
		
		//lBudTotalCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()));
		lBudTotalCapExp=lBudPLCapExp+lBudNPCapExp+lBudCSSCapExp;
		ldbBudTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Bud_Total").toString()) - lBudTotalCapExp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lBudTotalCapExp);
		lArrReturn.add(lBudTotalCapExp!=0?dataStyle:"-");
		//-----------------Budget End---------------
		
//		-----------------Grant Start---------------
		lGrantPLCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_PL").toString()));
		ldbGrantPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_PL").toString()) - lGrantPLCapExp;
		lArrReturn.add(lGrantPLCapExp!=0?lGrantPLCapExp:"-");

		lGrantNPCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_NP").toString()));
		ldbGrantNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_NP").toString()) - lGrantNPCapExp;
		lArrReturn.add(lGrantNPCapExp!=0?lGrantNPCapExp:"-");
		
		lGrantCSSCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_CSS").toString()));
		ldbGrantCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_CSS").toString()) - lGrantCSSCapExp;
		lArrReturn.add(lGrantCSSCapExp!=0?lGrantCSSCapExp:"-");
		
		//lGrantTotalCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()));
		lGrantTotalCapExp=lGrantPLCapExp+lGrantNPCapExp+lGrantCSSCapExp;
		ldbGrantTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Grant_Total").toString()) - lGrantTotalCapExp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lGrantTotalCapExp);
		lArrReturn.add(lGrantTotalCapExp!=0?dataStyle:"-");
		//-----------------Grant End---------------
		
		//-----------------Curr Start---------------
		lCurrPLCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_PL").toString()));
		ldbCurrPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_PL").toString()) - lCurrPLCapExp;
		lArrReturn.add(lCurrPLCapExp!=0?lCurrPLCapExp:"-");

		lCurrNPCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_NP").toString()) - lCurrNPCapExp;
		lArrReturn.add(lCurrNPCapExp!=0?lCurrNPCapExp:"-");
		
		lCurrCSSCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_CSS").toString()));
		ldbCurrCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_CSS").toString()) - lCurrCSSCapExp;
		lArrReturn.add(lCurrCSSCapExp!=0?lCurrCSSCapExp:"-");
		
		//lCurrTotalCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_Total").toString()));
		lCurrTotalCapExp=lCurrPLCapExp+lCurrNPCapExp+lCurrCSSCapExp;
		ldbCurrTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Curr_Total").toString()) - lCurrTotalCapExp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lCurrTotalCapExp);
		lArrReturn.add(lCurrTotalCapExp!=0?dataStyle:"-");
		//-----------------Curr End---------------
		
		//-----------------Prg Start---------------
		lPrgPLCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_PL").toString()));
		ldbPrgPLFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_PL").toString()) - lPrgPLCapExp;
		lArrReturn.add(lPrgPLCapExp!=0?lPrgPLCapExp:"-");

		lPrgNPCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_NP").toString()) - lPrgNPCapExp;
		lArrReturn.add(lPrgNPCapExp!=0?lPrgNPCapExp:"-");
		
		lPrgCSSCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_CSS").toString()));
		ldbPrgCSSFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_CSS").toString()) - lPrgCSSCapExp;
		lArrReturn.add(lPrgCSSCapExp!=0?lPrgCSSCapExp:"-");
		
		//lPrgTotalCapExp = Math.round(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()));
		lPrgTotalCapExp=lPrgPLCapExp+lPrgNPCapExp+lPrgCSSCapExp;
		ldbPrgTotalFracPart += Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_Prg_Total").toString()) - lPrgTotalCapExp;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lPrgTotalCapExp);
		lArrReturn.add(lPrgTotalCapExp!=0?dataStyle:"-");
		//-----------------Prg End---------------
		
		if(lGrantTotalCapExp!=0)
			lArrReturn.add((int)(lCurrTotalCapExp*100*91)/(lGrantTotalCapExp*ldiffDays));
		else
			lArrReturn.add("-");
		
		if(lBudTotalCapExp!=0)
			lArrReturn.add((int)(lPrgTotalCapExp*100)/lBudTotalCapExp);
		else
			lArrReturn.add("-");
		
		//lintPerCurr = (int)(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_PercentOfCurrPrv").toString()));
		//lArrReturn.add(lintPerCurr!=0?lintPerCurr:"-");
		lArrReturn.add("-");
		
		
		//lintPerPrg = (int)(Double.parseDouble(lhshConsolidatedFund.get(lStrFundNm+"_PercentOfProg").toString())); 
		//lArrReturn.add(lintPerPrg!=0?lintPerPrg:"-");
		lArrReturn.add("-");
		
		lArrMainDataLst.add(lArrReturn);
		
		//--------------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		lArrReturn.add("6) Surplus/Deficit(4-5)");
/*		lArrReturn.add((lBudPLCapRecp - lBudPLCapExp)!=0?(lBudPLCapRecp - lBudPLCapExp):"-");
		lArrReturn.add((lBudNPCapRecp - lBudNPCapExp)!=0?(lBudNPCapRecp - lBudNPCapExp):"-");
		lArrReturn.add((lBudCSSCapRecp - lBudCSSCapExp)!=0?(lBudCSSCapRecp - lBudCSSCapExp):"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lBudTotalCapRecp - lBudTotalCapExp));
		lArrReturn.add((lBudTotalCapRecp - lBudTotalCapExp)!=0?dataStyle:"-");
		
/*		lArrReturn.add((lGrantPLCapRecp - lGrantPLCapExp)!=0?(lGrantPLCapRecp - lGrantPLCapExp):"-");
		lArrReturn.add((lGrantNPCapRecp - lGrantNPCapExp)!=0?(lGrantNPCapRecp - lGrantNPCapExp):"-");
		lArrReturn.add((lGrantCSSCapRecp - lGrantCSSCapExp)!=0?(lGrantCSSCapRecp - lGrantCSSCapExp):"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		//lArrReturn.add((lGrantTotalCapRecp - lGrantTotalCapExp)!=0?"<b>"+(lGrantTotalCapRecp - lGrantTotalCapExp)+"</b>":"-");
		
/*		lArrReturn.add((lCurrPLCapRecp - lCurrPLCapExp)!=0?(lCurrPLCapRecp - lCurrPLCapExp):"-");
		lArrReturn.add((lCurrNPCapRecp - lCurrNPCapExp)!=0?(lCurrNPCapRecp - lCurrNPCapExp):"-");
		lArrReturn.add((lCurrCSSCapRecp - lCurrCSSCapExp)!=0?(lCurrCSSCapRecp - lCurrCSSCapExp):"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lCurrTotalCapRecp - lCurrTotalCapExp));
		lArrReturn.add((lCurrTotalCapRecp - lCurrTotalCapExp)!=0?dataStyle:"-");
		
/*		lArrReturn.add((lPrgPLCapRecp - lPrgPLCapExp)!=0?(lPrgPLCapRecp - lPrgPLCapExp):"-");
		lArrReturn.add((lPrgNPCapRecp - lPrgNPCapExp)!=0?(lPrgNPCapRecp - lPrgNPCapExp):"-");
		lArrReturn.add((lPrgCSSCapRecp - lPrgCSSCapExp)!=0?(lPrgCSSCapRecp - lPrgCSSCapExp):"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lPrgTotalCapRecp - lPrgTotalCapExp));
		lArrReturn.add((lPrgTotalCapRecp - lPrgTotalCapExp)!=0?dataStyle:"-");
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrMainDataLst.add(lArrReturn);
		
//		-------------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		lArrReturn.add("7) Consolidated Fund Receipts (1+4)");
		lArrReturn.add((lBudPLRevRecp + lBudPLCapRecp)!=0?(lBudPLRevRecp + lBudPLCapRecp):"-");
		lArrReturn.add((lBudNPRevRecp + lBudNPCapRecp)!=0?(lBudNPRevRecp + lBudNPCapRecp):"-");
		lArrReturn.add((lBudCSSRevRecp + lBudCSSCapRecp)!=0?(lBudCSSRevRecp + lBudCSSCapRecp):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lBudTotalRevRecp + lBudTotalCapRecp));
		lArrReturn.add((lBudTotalRevRecp + lBudTotalCapRecp)!=0?dataStyle:"-");
		
		lArrReturn.add((lGrantPLRevRecp + lGrantPLCapRecp)!=0?(lGrantPLRevRecp + lGrantPLCapRecp):"-");
		lArrReturn.add((lGrantNPRevRecp + lGrantNPCapRecp)!=0?(lGrantNPRevRecp + lGrantNPCapRecp):"-");
		lArrReturn.add((lGrantCSSRevRecp + lGrantCSSCapRecp)!=0?(lGrantCSSRevRecp + lGrantCSSCapRecp):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lGrantTotalRevRecp + lGrantTotalCapRecp));
		lArrReturn.add((lGrantTotalRevRecp + lGrantTotalCapRecp)!=0?dataStyle:"-");
		
		lArrReturn.add((lCurrPLRevRecp + lCurrPLCapRecp)!=0?(lCurrPLRevRecp + lCurrPLCapRecp):"-");
		lArrReturn.add((lCurrNPRevRecp + lCurrNPCapRecp)!=0?(lCurrNPRevRecp + lCurrNPCapRecp):"-");
		lArrReturn.add((lCurrCSSRevRecp + lCurrCSSCapRecp)!=0?(lCurrCSSRevRecp + lCurrCSSCapRecp):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lCurrTotalRevRecp + lCurrTotalCapRecp));
		lArrReturn.add((lCurrTotalRevRecp + lCurrTotalCapRecp)!=0?dataStyle:"-");
		
		lArrReturn.add((lPrgPLRevRecp + lPrgPLCapRecp)!=0?(lPrgPLRevRecp + lPrgPLCapRecp):"-");
		lArrReturn.add((lPrgNPRevRecp + lPrgNPCapRecp)!=0?(lPrgNPRevRecp + lPrgNPCapRecp):"-");
		lArrReturn.add((lPrgCSSRevRecp + lPrgCSSCapRecp)!=0?(lPrgCSSRevRecp + lPrgCSSCapRecp):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lPrgTotalRevRecp + lPrgTotalCapRecp));
		lArrReturn.add((lPrgTotalRevRecp + lPrgTotalCapRecp)!=0?dataStyle:"-");
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrMainDataLst.add(lArrReturn);
		
//		------------------------------------------------------------------------------- 
		
		lArrReturn = new ArrayList();
		lArrReturn.add("8) Consolidated Fund Expenditure (2+5)");
		lArrReturn.add((lBudPLRevExp + lBudPLCapExp)!=0?(lBudPLRevExp + lBudPLCapExp):"-");
		lArrReturn.add((lBudNPRevExp + lBudNPCapExp)!=0?(lBudNPRevExp + lBudNPCapExp):"-");
		lArrReturn.add((lBudCSSRevExp + lBudCSSCapExp)!=0?(lBudCSSRevExp + lBudCSSCapExp):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lBudTotalRevExp + lBudTotalCapExp));
		lArrReturn.add((lBudTotalRevExp + lBudTotalCapExp)!=0?dataStyle:"-");
		
		lArrReturn.add((lGrantPLRevExp + lGrantPLCapExp)!=0?(lGrantPLRevExp + lGrantPLCapExp):"-");
		lArrReturn.add((lGrantNPRevExp + lGrantNPCapExp)!=0?(lGrantNPRevExp + lGrantNPCapExp):"-");
		lArrReturn.add((lGrantCSSRevExp + lGrantCSSCapExp)!=0?(lGrantCSSRevExp + lGrantCSSCapExp):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lGrantTotalRevExp + lGrantTotalCapExp));
		lArrReturn.add((lGrantTotalRevExp + lGrantTotalCapExp)!=0?dataStyle:"-");
		
		lArrReturn.add((lCurrPLRevExp + lCurrPLCapExp)!=0?(lCurrPLRevExp + lCurrPLCapExp):"-");
		lArrReturn.add((lCurrNPRevExp + lCurrNPCapExp)!=0?(lCurrNPRevExp + lCurrNPCapExp):"-");
		lArrReturn.add((lCurrCSSRevExp + lCurrCSSCapExp)!=0?(lCurrCSSRevExp + lCurrCSSCapExp):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lCurrTotalRevExp + lCurrTotalCapExp));
		lArrReturn.add((lCurrTotalRevExp + lCurrTotalCapExp)!=0?dataStyle:"-");
		
		lArrReturn.add((lPrgPLRevExp + lPrgPLCapExp)!=0?(lPrgPLRevExp + lPrgPLCapExp):"-");
		lArrReturn.add((lPrgNPRevExp + lPrgNPCapExp)!=0?(lPrgNPRevExp + lPrgNPCapExp):"-");
		lArrReturn.add((lPrgCSSRevExp + lPrgCSSCapExp)!=0?(lPrgCSSRevExp + lPrgCSSCapExp):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lPrgTotalRevExp + lPrgTotalCapExp));
		lArrReturn.add((lPrgTotalRevExp + lPrgTotalCapExp)!=0?dataStyle:"-");
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrMainDataLst.add(lArrReturn);
		
//		-------------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		long lBudPL =  0;
		long lBudNP = 0;
		long lBudCSS = 0;
		long lBudTotal = 0;
		long lGrantPL =  0;
		long lGrantNP = 0;
		long lGrantCSS = 0;
		long lGrantTotal = 0;
		long lCurrPL =  0;
		long lCurrNP = 0;
		long lCurrCSS = 0;
		long lCurrTotal = 0;
		long lPrgPL =  0;
		long lPrgNP = 0;
		long lPrgCSS = 0;
		long lPrgTotal = 0;
		
		lArrReturn.add("9) Net Consolidated Fund (7-8)");
		lBudPL =  (lBudPLRevRecp + lBudPLCapRecp)-(lBudPLRevExp + lBudPLCapExp);
		lBudNP = (lBudNPRevRecp + lBudNPCapRecp)-(lBudNPRevExp + lBudNPCapExp);
		lBudCSS = (lBudCSSRevRecp + lBudCSSCapRecp)-(lBudCSSRevExp + lBudCSSCapExp);
		lBudTotal = (lBudTotalRevRecp + lBudTotalCapRecp)-(lBudTotalRevExp + lBudTotalCapExp);
		lGrantPL =  (lGrantPLRevRecp + lGrantPLCapRecp)-(lGrantPLRevExp + lGrantPLCapExp);
		lGrantNP = (lGrantNPRevRecp + lGrantNPCapRecp)-(lGrantNPRevExp + lGrantNPCapExp);
		lGrantCSS = (lGrantCSSRevRecp + lGrantCSSCapRecp)-(lGrantCSSRevExp + lGrantCSSCapExp);
		lGrantTotal = (lGrantTotalRevRecp + lGrantTotalCapRecp)-(lGrantTotalRevExp + lGrantTotalCapRecp);
		lCurrPL =  (lCurrPLRevRecp + lCurrPLCapRecp)-(lCurrPLRevExp + lCurrPLCapExp);
		lCurrNP = (lCurrNPRevRecp + lCurrNPCapRecp)-(lCurrNPRevExp + lCurrNPCapExp);
		lCurrCSS = (lCurrCSSRevRecp + lCurrCSSCapRecp)-(lCurrCSSRevExp + lCurrCSSCapExp);
		lCurrTotal = (lCurrTotalRevRecp + lCurrTotalCapRecp)-(lCurrTotalRevExp + lCurrTotalCapExp);
		lPrgPL =  (lPrgPLRevRecp + lPrgPLCapRecp)-(lPrgPLRevExp + lPrgPLCapExp);
		lPrgNP = (lPrgNPRevRecp + lPrgNPCapRecp)-(lPrgNPRevExp + lPrgNPCapExp);
		lPrgCSS = (lPrgCSSRevRecp + lPrgCSSCapRecp)-(lPrgCSSRevExp + lPrgCSSCapExp);
		lPrgTotal = (lPrgTotalRevRecp + lPrgTotalCapRecp)-(lPrgTotalRevExp + lPrgTotalCapExp);
		
/*		lArrReturn.add(lBudPL!=0?lBudPL:"-");
		lArrReturn.add(lBudNP!=0?lBudNP:"-");
		lArrReturn.add(lBudCSS!=0?lBudCSS:"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lBudTotal);
		lArrReturn.add(lBudTotal!=0?dataStyle:"-");
		
/*		lArrReturn.add(lGrantPL!=0?lGrantPL:"-");
		lArrReturn.add(lGrantNP!=0?lGrantNP:"-");
		lArrReturn.add(lGrantCSS!=0?lGrantCSS:"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		//lArrReturn.add(lGrantTotal!=0?"<b>"+lGrantTotal+"</b>":"-");
		
/*		lArrReturn.add(lCurrPL!=0?lCurrPL:"-");
		lArrReturn.add(lCurrNP!=0?lCurrNP:"-");
		lArrReturn.add(lCurrCSS!=0?lCurrCSS:"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lCurrTotal);
		lArrReturn.add(lCurrTotal!=0?dataStyle:"-");
		
/*		lArrReturn.add(lPrgPL!=0?lPrgPL:"-");
		lArrReturn.add(lPrgNP!=0?lPrgNP:"-");
		lArrReturn.add(lPrgCSS!=0?lPrgCSS:"-");*/
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lPrgTotal);
		lArrReturn.add(lPrgTotal!=0?dataStyle:"-");
		
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrReturn.add("-");
		lArrMainDataLst.add(lArrReturn);
		
//		-------------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData("II-Contingency Fund");
		lArrReturn.add(dataStyle);
		for(int i=0;i<20;i++)
			lArrReturn.add("");
		lArrMainDataLst.add(lArrReturn);
	
//		-------------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		long lBudPLConFund =  0;
		long lBudNPConFund = 0;
		long lBudCSSConFund = 0;
		long lBudTotalConFund = 0;
		long lGrantPLConFund =  0;
		long lGrantNPConFund = 0;
		long lGrantCSSConFund = 0;
		long lGrantTotalConFund = 0;
		long lCurrPLConFund =  0;
		long lCurrNPConFund = 0;
		long lCurrCSSConFund = 0;
		long lCurrTotalConFund = 0;
		long lPrgPLConFund =  0;
		long lPrgNPConFund = 0;
		long lPrgCSSConFund = 0;
		long lPrgTotalConFund = 0;
		
		lStrFundNm = "ConFund";
		lArrReturn.add("10) Contingency(NET)");
//		-----------------Budget Start---------------
		lBudPLConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Bud_PL").toString()));
		ldbBudPLFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Bud_PL").toString()) - lBudPLConFund;
		lArrReturn.add(lBudPLConFund!=0?lBudPLConFund:"-");

		lBudNPConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Bud_NP").toString()));
		ldbBudNPFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Bud_NP").toString()) - lBudNPConFund;
		lArrReturn.add(lBudNPConFund!=0?lBudNPConFund:"-");
		
		lBudCSSConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Bud_CSS").toString()));
		ldbBudCSSFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Bud_CSS").toString()) - lBudCSSConFund;
		lArrReturn.add(lBudCSSConFund!=0?lBudCSSConFund:"-");
		
		lBudTotalConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Bud_Total").toString()));
		ldbBudTotalFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Bud_Total").toString()) - lBudTotalConFund;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lBudTotalConFund);
		lArrReturn.add(lBudTotalConFund!=0?dataStyle:"-");
		//-----------------Budget End---------------
		
//		-----------------Grant Start---------------
		lGrantPLConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_PL").toString()));
		ldbGrantPLFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_PL").toString()) - lGrantPLConFund;
		lArrReturn.add(lGrantPLConFund!=0?lGrantPLConFund:"-");

		lGrantNPConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_NP").toString()));
		ldbGrantNPFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_NP").toString()) - lGrantNPConFund;
		lArrReturn.add(lGrantNPConFund!=0?lGrantNPConFund:"-");
		
		lGrantCSSConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_CSS").toString()));
		ldbGrantCSSFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_CSS").toString()) - lGrantCSSConFund;
		lArrReturn.add(lGrantCSSConFund!=0?lGrantCSSConFund:"-");
		
		lGrantTotalConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_Total").toString()));
		ldbGrantTotalFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Grant_Total").toString()) - lGrantTotalConFund;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lGrantTotalConFund);
		lArrReturn.add(lGrantTotalConFund!=0?dataStyle:"-");
		//-----------------Grant End---------------
		
		//-----------------Curr Start---------------
		lCurrPLConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Curr_PL").toString()));
		ldbCurrPLFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Curr_PL").toString()) - lCurrPLConFund;
		lArrReturn.add(lCurrPLConFund!=0?lCurrPLConFund:"-");

		lCurrNPConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Curr_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Curr_NP").toString()) - lCurrNPConFund;
		lArrReturn.add(lCurrNPConFund!=0?lCurrNPConFund:"-");
		
		lCurrCSSConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Curr_CSS").toString()));
		ldbCurrCSSFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Curr_CSS").toString()) - lCurrCSSConFund;
		lArrReturn.add(lCurrCSSConFund!=0?lCurrCSSConFund:"-");
		
		lCurrTotalConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Curr_Total").toString()));
		ldbCurrTotalFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Curr_Total").toString()) - lCurrTotalConFund;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lCurrTotalConFund);
		lArrReturn.add(lCurrTotalConFund!=0?dataStyle:"-");
		//-----------------Curr End---------------
		
		//-----------------Prg Start---------------
		lPrgPLConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_PL").toString()));
		ldbPrgPLFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_PL").toString()) - lPrgPLConFund;
		lArrReturn.add(lPrgPLConFund!=0?lPrgPLConFund:"-");

		lPrgNPConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_NP").toString()) - lPrgNPConFund;
		lArrReturn.add(lPrgNPConFund!=0?lPrgNPConFund:"-");
		
		lPrgCSSConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_CSS").toString()));
		ldbPrgCSSFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_CSS").toString()) - lPrgCSSConFund;
		lArrReturn.add(lPrgCSSConFund!=0?lPrgCSSConFund:"-");
		
		lPrgTotalConFund = Math.round(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_Total").toString()));
		ldbPrgTotalFracPart += Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_Prg_Total").toString()) - lPrgTotalConFund;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lPrgTotalConFund);
		lArrReturn.add(lPrgTotalConFund!=0?dataStyle:"-");
		//-----------------Prg End---------------
		
		if(lGrantTotalConFund!=0)
			lArrReturn.add((int)(lCurrTotalConFund*100*91)/(lGrantTotalConFund*ldiffDays));
		else
			lArrReturn.add("-");
		
		if(lBudTotalConFund!=0)
			lArrReturn.add((int)(lPrgTotalConFund*100)/lBudTotalConFund);
		else
			lArrReturn.add("-");
		
		//lintPerCurr = (int)(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_PercentOfCurrPrv").toString()));
		//lArrReturn.add(lintPerCurr!=0?lintPerCurr:"-");
		lArrReturn.add("-");
		
		//lintPerPrg = (int)(Double.parseDouble(lhshContingencyFund.get(lStrFundNm+"_PercentOfProg").toString())); 
		//lArrReturn.add(lintPerPrg!=0?lintPerPrg:"-");
		lArrReturn.add("-");
		
		lArrMainDataLst.add(lArrReturn);
		
		//--------------------------------------------------------------------------------
		
		
		glogger.info("After adding Contingency Fund");
		lArrReturn = new ArrayList();
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData("III-Public Account</b>");
		lArrReturn.add(dataStyle);
		for(int i=0;i<20;i++)
			lArrReturn.add("");
		lArrMainDataLst.add(lArrReturn);
		
//		----------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();
		long lBudPLPubFund =  0;
		long lBudNPPubFund = 0;
		long lBudCSSPubFund = 0;
		long lBudTotalPubFund = 0;
		long lGrantPLPubFund =  0;
		long lGrantNPPubFund = 0;
		long lGrantCSSPubFund = 0;
		long lGrantTotalPubFund = 0;
		long lCurrPLPubFund =  0;
		long lCurrNPPubFund = 0;
		long lCurrCSSPubFund = 0;
		long lCurrTotalPubFund = 0;
		long lPrgPLPubFund =  0;
		long lPrgNPPubFund = 0;
		long lPrgCSSPubFund = 0;
		long lPrgTotalPubFund = 0;
		
		lStrFundNm = "PubFund";
		lArrReturn.add("11) Public Account(NET)");
//		-----------------Budget Start---------------
		lBudPLPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Bud_PL").toString()));
		ldbBudPLFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Bud_PL").toString()) - lBudPLPubFund;
		lArrReturn.add(lBudPLPubFund!=0?lBudPLPubFund:"-");

		lBudNPPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Bud_NP").toString()));
		ldbBudNPFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Bud_NP").toString()) - lBudNPPubFund;
		lArrReturn.add(lBudNPPubFund!=0?lBudNPPubFund:"-");
		
		lBudCSSPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Bud_CSS").toString()));
		ldbBudCSSFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Bud_CSS").toString()) - lBudCSSPubFund;
		lArrReturn.add(lBudCSSPubFund!=0?lBudCSSPubFund:"-");
		
		lBudTotalPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Bud_Total").toString()));
		ldbBudTotalFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Bud_Total").toString()) - lBudTotalPubFund;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lBudTotalPubFund);
		lArrReturn.add(lBudTotalPubFund!=0?dataStyle:"-");
		//-----------------Budget End---------------
		
//		-----------------Grant Start---------------
		lGrantPLPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Grant_PL").toString()));
		ldbGrantPLFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Grant_PL").toString()) - lGrantPLPubFund;
		lArrReturn.add(lGrantPLPubFund!=0?lGrantPLPubFund:"-");

		lGrantNPPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Grant_NP").toString()));
		ldbGrantNPFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Grant_NP").toString()) - lGrantNPPubFund;
		lArrReturn.add(lGrantNPPubFund!=0?lGrantNPPubFund:"-");
		
		lGrantCSSPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Grant_CSS").toString()));
		ldbGrantCSSFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Grant_CSS").toString()) - lGrantCSSPubFund;
		lArrReturn.add(lGrantCSSPubFund!=0?lGrantCSSPubFund:"-");
		
		lGrantTotalPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Grant_Total").toString()));
		ldbGrantTotalFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Grant_Total").toString()) - lGrantTotalPubFund;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lGrantTotalPubFund);
		lArrReturn.add(lGrantTotalPubFund!=0?dataStyle:"-");
		//-----------------Grant End---------------
		
		//-----------------Curr Start---------------
		lCurrPLPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_PL").toString()));
		ldbCurrPLFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_PL").toString()) - lCurrPLPubFund;
		lArrReturn.add(lCurrPLPubFund!=0?lCurrPLPubFund:"-");

		lCurrNPPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_NP").toString()) - lCurrNPPubFund;
		lArrReturn.add(lCurrNPPubFund!=0?lCurrNPPubFund:"-");
		
		lCurrCSSPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_CSS").toString()));
		ldbCurrCSSFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_CSS").toString()) - lCurrCSSPubFund;
		lArrReturn.add(lCurrCSSPubFund!=0?lCurrCSSPubFund:"-");
		
		lCurrTotalPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_Total").toString()));
		ldbCurrTotalFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Curr_Total").toString()) - lCurrTotalPubFund;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lCurrTotalPubFund);
		lArrReturn.add(lCurrTotalPubFund!=0?dataStyle:"-");
		//-----------------Curr End---------------
		
		//-----------------Prg Start---------------
		lPrgPLPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_PL").toString()));
		ldbPrgPLFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_PL").toString()) - lPrgPLPubFund;
		lArrReturn.add(lPrgPLPubFund!=0?lPrgPLPubFund:"-");

		lPrgNPPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_NP").toString()));
		ldbCurrNPFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_NP").toString()) - lPrgNPPubFund;
		lArrReturn.add(lPrgNPPubFund!=0?lPrgNPPubFund:"-");
		
		lPrgCSSPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_CSS").toString()));
		ldbPrgCSSFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_CSS").toString()) - lPrgCSSPubFund;
		lArrReturn.add(lPrgCSSPubFund!=0?lPrgCSSPubFund:"-");
		
		lPrgTotalPubFund = Math.round(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_Total").toString()));
		ldbPrgTotalFracPart += Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_Prg_Total").toString()) - lPrgTotalPubFund;
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lPrgTotalPubFund);
		lArrReturn.add(lPrgTotalPubFund!=0?dataStyle:"-");
		//-----------------Prg End---------------
		
		if(lGrantTotalPubFund!=0)
			lArrReturn.add((int)(lCurrTotalPubFund*100*91)/(lGrantTotalPubFund*ldiffDays));
		else
			lArrReturn.add("-");
		
		if(lBudTotalPubFund!=0)
			lArrReturn.add((int)(lPrgTotalPubFund*100)/lBudTotalPubFund);
		else
			lArrReturn.add("-");
		
		//lintPerCurr = (int)(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_PercentOfCurrPrv").toString()));
		//lArrReturn.add(lintPerCurr!=0?lintPerCurr:"-");
		lArrReturn.add("-");
		
		//lintPerPrg = (int)(Double.parseDouble(lhshPublicFund.get(lStrFundNm+"_PercentOfProg").toString())); 
		//lArrReturn.add(lintPerPrg!=0?lintPerPrg:"-");
		lArrReturn.add("-");
	
		lArrMainDataLst.add(lArrReturn);
		
		//--------------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();	
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData("Total Transactions(I+II+III)");
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lBudPL+lBudPLConFund+lBudPLPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lBudNP+lBudNPConFund+lBudNPPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lBudCSS+lBudCSSConFund+lBudCSSPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lBudTotal+lBudTotalConFund+lBudTotalPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lGrantPL+lGrantPLConFund+lGrantPLPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lGrantNP+lGrantNPConFund+lGrantNPPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lGrantCSS+lGrantCSSConFund+lGrantCSSPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lGrantTotal+lGrantTotalConFund+lGrantTotalPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lCurrPL+lCurrPLConFund+lCurrPLPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lCurrNP+lCurrNPConFund+lCurrNPPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lCurrCSS+lCurrCSSConFund+lCurrCSSPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lCurrTotal+lCurrTotalConFund+lCurrTotalPubFund));
		lArrReturn.add(dataStyle);
		
		/*double ldbPerPlanPrg = 0;
        double ldbPerNPPrg = 0;
        double ldbPerCSSPrg = 0;
        double ldbTotalPerPrg = 0;
        
        ldbPerPlanPrg = ((lPrgPL+lPrgPLConFund+lPrgPLPubFund) * 100)/(((lGrantPL+lGrantPLConFund+lGrantPLPubFund)*lIntPrgDiffDays)/91);
        ldbPerNPPrg = ((lPrgNP+lPrgNPConFund+lPrgNPPubFund) * 100)/(((lGrantNP+lGrantNPConFund+lGrantNPPubFund)*lIntPrgDiffDays)/91);
        ldbPerCSSPrg = ((lPrgCSS+lPrgCSSConFund+lPrgCSSPubFund) * 100)/(((lGrantCSS+lGrantCSSConFund+lGrantCSSPubFund)*lIntPrgDiffDays)/91);
        ldbTotalPerPrg = ((lPrgTotal+lPrgTotalConFund+lPrgTotalPubFund)* 100)/(((lGrantTotal+lGrantTotalConFund+lGrantTotalPubFund)*lIntPrgDiffDays)/91);

		
		lArrReturn.add("<b>"+(lPrgPL+lPrgPLConFund+lPrgPLPubFund)+"("+ldbPerPlanPrg+")");
		lArrReturn.add("<b>"+(lPrgNP+lPrgNPConFund+lPrgNPPubFund)+"("+ldbPerNPPrg+")");
		lArrReturn.add("<b>"+(lPrgCSS+lPrgCSSConFund+lPrgCSSPubFund)+"("+ldbPerCSSPrg+")");
		lArrReturn.add("<b>"+(lPrgTotal+lPrgTotalConFund+lPrgTotalPubFund)+"("+ldbTotalPerPrg+")");*/
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lPrgPL+lPrgPLConFund+lPrgPLPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lPrgNP+lPrgNPConFund+lPrgNPPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lPrgCSS+lPrgCSSConFund+lPrgCSSPubFund));
		lArrReturn.add(dataStyle);
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData((lPrgTotal+lPrgTotalConFund+lPrgTotalPubFund));
		lArrReturn.add(dataStyle);
		
		if((lGrantTotal+lGrantTotalConFund+lGrantTotalPubFund)!=0)
		{
			dataStyle = new StyledData();
			dataStyle.setStyles(reportStyleVO);
			dataStyle.setData(((int)((lCurrTotal+lCurrTotalConFund+lCurrTotalPubFund)*100*91)/((lGrantTotal+lGrantTotalConFund+lGrantTotalPubFund)*ldiffDays)));
			lArrReturn.add(dataStyle);
		}
		else
		{
			dataStyle = new StyledData();
	        dataStyle.setStyles(reportStyleVO);
	        dataStyle.setData("-");
			lArrReturn.add(dataStyle);
		}
		if((lBudTotal+lBudTotalConFund+lBudTotalPubFund)!=0)
		{
			dataStyle = new StyledData();
	        dataStyle.setStyles(reportStyleVO);
	        dataStyle.setData(((int)((lPrgTotal+lPrgTotalConFund+lPrgTotalPubFund)*100)/(lBudTotal+lBudTotalConFund+lBudTotalPubFund)));
			lArrReturn.add(dataStyle);
		}
		else
		{
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
		lArrMainDataLst.add(lArrReturn);
		
//		-----------------------------------------------------------------------------
		
		lArrReturn = new ArrayList();	
		lArrReturn.add("Rounded Difference");
		lArrReturn.add(ldbBudPLFracPart!=0?lnfLong.format(ldbBudPLFracPart):"-");
		lArrReturn.add(ldbBudNPFracPart!=0?lnfLong.format(ldbBudNPFracPart):"-");
		lArrReturn.add(ldbBudCSSFracPart!=0?lnfLong.format(ldbBudCSSFracPart):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lnfLong.format(ldbBudTotalFracPart));
		lArrReturn.add(ldbBudTotalFracPart!=0?dataStyle:"-");
		
		lArrReturn.add(ldbGrantPLFracPart!=0?lnfLong.format(ldbGrantPLFracPart):"-");
		lArrReturn.add(ldbGrantNPFracPart!=0?lnfLong.format(ldbGrantNPFracPart):"-");
		lArrReturn.add(ldbGrantCSSFracPart!=0?lnfLong.format(ldbGrantCSSFracPart):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lnfLong.format(ldbGrantTotalFracPart));
		lArrReturn.add(ldbGrantTotalFracPart!=0?dataStyle:"-");
		
		lArrReturn.add(ldbCurrPLFracPart!=0?lnfLong.format(ldbCurrPLFracPart):"-");
		lArrReturn.add(ldbCurrNPFracPart!=0?lnfLong.format(ldbCurrNPFracPart):"-");
		lArrReturn.add(ldbCurrCSSFracPart!=0?lnfLong.format(ldbCurrCSSFracPart):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lnfLong.format(ldbCurrTotalFracPart));
		lArrReturn.add(ldbCurrTotalFracPart!=0?dataStyle:"-");
		
		lArrReturn.add(ldbPrgPLFracPart!=0?lnfLong.format(ldbPrgPLFracPart):"-");
		lArrReturn.add(ldbPrgNPFracPart!=0?lnfLong.format(ldbPrgNPFracPart):"-");
		lArrReturn.add(ldbPrgCSSFracPart!=0?lnfLong.format(ldbPrgCSSFracPart):"-");
		
		dataStyle = new StyledData();
        dataStyle.setStyles(reportStyleVO);
        dataStyle.setData(lnfLong.format(ldbPrgTotalFracPart));
		lArrReturn.add(ldbPrgTotalFracPart!=0?dataStyle:"-");
		
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
		lArrMainDataLst.add(lArrReturn);
		
//		-----------------------------------------------------------------------------
		
		
		return lArrMainDataLst;
	}
	
	
	private HashMap getConsolidatedFund(int lintFinYrId, String lStrLangId, String lStrFromDt, String lStrToDt)
	{
		glogger.info("-- In getConsolidatedFund() -- ");
		HashMap lhshConsolidatedFundMap = new HashMap();
		String lStrPrevFromDate = null;
		String lStrPrevToDate = null;

		CommonRptQueryDAO lObjRptQueryDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
		
		ArrayList lArrSubConsFund = (ArrayList)lObjRptQueryDAO.getSubFundMjrRange(3);
		glogger.info("Size of Sub funds for ConsolidatedFund -- "+lArrSubConsFund.size());
		
		HashMap lhshPrevDateMap = getPreviousDates(lStrFromDt,lStrToDt);
		lStrPrevFromDate=lhshPrevDateMap.get("fromdate").toString();
		lStrPrevToDate=lhshPrevDateMap.get("todate").toString();

		for(int i=0;i<lArrSubConsFund.size();i++)
		{
			HashMap lhshSubConFundMap = (HashMap) lArrSubConsFund.get(i);
			glogger.info("FundName -- "+lhshSubConFundMap.get("FundName"));
			
			if(lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Revenue Receipt") || 
					lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Capital Receipt"))
			{
				glogger.info("FundName -- "+lhshSubConFundMap.get("FundName"));
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
				double lPrevProgTxt_Total=0;
				double lPercentOfProg = 0;

				ArrayList lArrSubRevRecp = (ArrayList)lObjRptQueryDAO.getSubFundMjrRange(Integer.parseInt(lhshSubConFundMap.get("FundId").toString()));
				glogger.info("Size of Sub Funds for "+lhshSubConFundMap.get("FundName")+" -- "+lArrSubRevRecp.size());
				
				HashMap lhshMapSubRevRecp = null;
				for(int j=0;j< lArrSubRevRecp.size();j++)
				{
					lhshMapSubRevRecp = (HashMap) lArrSubRevRecp.get(j);
					ArrayList lArrMjrHdRange = getMjrHdRange(lhshMapSubRevRecp.get("MjrHdRange").toString() );
					HashMap lhshMapConRecpAmt = lObjRptQueryDAO.getConsolidatedRcpBudAmt(lArrMjrHdRange,lintFinYrId);
					ltotalRecp = ltotalRecp + Double.parseDouble(lhshMapConRecpAmt.get("ConsolidatedReceiptTotal").toString());

					/*HashMap lhshGrantData = lObjRptQueryDAO.getGrantAmt(lArrMjrHdRange, lintFinYrId, "en_US");
					lGrantAmt_PL = lGrantAmt_PL + Double.parseDouble(lhshGrantData.get("GrantAmt_PL").toString());
					lGrantAmt_NP = lGrantAmt_NP + Double.parseDouble(lhshGrantData.get("GrantAmt_NP").toString());
					lGrantAmt_CSS = lGrantAmt_CSS + Double.parseDouble(lhshGrantData.get("GrantAmt_CSS").toString());
					ltotalGrant = ltotalGrant + Double.parseDouble(lhshGrantData.get("GrantAmt_Total").toString());*/
					
					HashMap lhshCurrData = lObjRptQueryDAO.getCurrentExpAmt(lArrMjrHdRange, lStrFromDt, lStrToDt);
					HashMap lhshCurrDataText = lObjRptQueryDAO.getTextRecptData(lArrMjrHdRange, 9, lStrFromDt, lStrToDt);
					lCurrAmt_PL = lCurrAmt_PL + Double.parseDouble(lhshCurrData.get("CurrentExp_PL").toString());
					//lCurrAmt_PL = lCurrAmt_PL +Double.parseDouble(lhshCurrDataText.get("Txt_PL").toString());
					
					lCurrAmt_NP = lCurrAmt_NP + Double.parseDouble(lhshCurrData.get("CurrentExp_NP").toString());
					//lCurrAmt_NP = lCurrAmt_NP + Double.parseDouble(lhshCurrDataText.get("Txt_NP").toString());
					
					//lCurrAmt_CSS = lCurrAmt_CSS + Double.parseDouble(lhshCurrDataText.get("Txt_CSS").toString());
					
					lCurrAmt_Total = lCurrAmt_Total + Double.parseDouble(lhshCurrData.get("CurrentExp_Total").toString());
					lCurrAmt_Total = lCurrAmt_Total +Double.parseDouble(lhshCurrDataText.get("Txt_Total").toString());
					
					HashMap lhshPrgData = lObjRptQueryDAO.getProgressiveExpAmt(lArrMjrHdRange, lintFinYrId, lStrToDt);
					HashMap lhshPrgDataText = lObjRptQueryDAO.getTextRecptData(lArrMjrHdRange, 9, "",lStrToDt);
					lPrgAmt_PL = lPrgAmt_PL + Double.parseDouble(lhshPrgData.get("ProgExp_PL").toString());
					//lPrgAmt_PL = lPrgAmt_PL + Double.parseDouble(lhshPrgDataText.get("Txt_PL").toString());
					
					lPrgAmt_NP = lPrgAmt_NP + Double.parseDouble(lhshPrgData.get("ProgExp_NP").toString());
					//lPrgAmt_NP = lPrgAmt_NP + Double.parseDouble(lhshPrgDataText.get("Txt_NP").toString());
					
					//lPrgAmt_CSS = lPrgAmt_CSS + Double.parseDouble(lhshPrgDataText.get("Txt_CSS").toString());
					
					lPrgAmt_Total = lPrgAmt_Total + Double.parseDouble(lhshPrgData.get("ProgExp_Total").toString());
					lPrgAmt_Total = lPrgAmt_Total + Double.parseDouble(lhshPrgDataText.get("Txt_Total").toString());
					
					/*glogger.info("---Before Calling Recp Data of Previous Yr--");
					HashMap lhshPrevCurrDataText = lObjRptQueryDAO.getTextRecptData(lArrMjrHdRange, 8, lStrPrevFromDate, lStrPrevToDate);
					lPrevCurrTxt_Total = lPrevCurrTxt_Total + Double.parseDouble(lhshPrevCurrDataText.get("Txt_Total").toString());
					lPrevRE = lPrevRE + lObjRptQueryDAO.getTotalREAmt(lArrMjrHdRange, "6"); 
					
					HashMap lhshPrevProgDataText = lObjRptQueryDAO.getTextRecptData(lArrMjrHdRange, 8, "", lStrPrevToDate);
					lPrevProgTxt_Total=lPrevProgTxt_Total+Double.parseDouble(lhshPrevProgDataText.get("Txt_Total").toString());*/
				}
				
				String lStrFundNm = "";
				if(lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Revenue Receipt"))
					lStrFundNm = "RevRecp";
				else 
					lStrFundNm = "CapRecp";
				
				lhshConsolidatedFundMap.put(lStrFundNm+"_Bud_PL", 0);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Bud_NP", 0);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Bud_CSS", 0);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Bud_Total", ltotalRecp);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Grant_PL", lGrantAmt_PL);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Grant_NP", lGrantAmt_NP);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Grant_CSS", lGrantAmt_CSS);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Grant_Total", ltotalGrant);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Curr_PL", lCurrAmt_PL);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Curr_NP", lCurrAmt_NP);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Curr_CSS", lCurrAmt_CSS);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Curr_Total", lCurrAmt_Total);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Prg_PL", lPrgAmt_PL);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Prg_NP", lPrgAmt_NP);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Prg_CSS", lPrgAmt_CSS);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Prg_Total", lPrgAmt_Total);
				
				/*if(lPrevRE!=0)
				{
					lPercentOfCurr=((lPrevCurrTxt_Total*100)/lPrevRE);
					lPercentOfProg=((lPrevProgTxt_Total*100)/lPrevRE);
				}
				else	
				{
					lPercentOfCurr=0;
					lPercentOfProg=0;
				}
				glogger.info("PrevCurrTxt Total == "+lPrevCurrTxt_Total);
				glogger.info("lPrevProgTxt_Total == "+lPrevProgTxt_Total);
				glogger.info("lPrevRE == "+lPrevRE);
				
				lhshConsolidatedFundMap.put(lStrFundNm+"_PercentOfCurrPrv", lPercentOfCurr);
				lhshConsolidatedFundMap.put(lStrFundNm+"_PercentOfProg", lPercentOfProg);
				glogger.info("PercentOfCurrPrv for "+lStrFundNm+" is == "+lPercentOfCurr);
				glogger.info("PercentOfProg for "+lStrFundNm+" is == "+lPercentOfProg);*/
			}
			else if(lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Revenue Expenditure") || 
					lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Capital Expenditure(Incl. Loans)"))
			{
				glogger.info("FundName -- "+lhshSubConFundMap.get("FundName"));
				double lPLTotal = 0;
				double lNPTotal = 0;
				double lCSSTotal = 0;
				double lCurrTotal = 0;
				double lPrevCurrTxt_Total = 0;
				double lPrevRE = 0;
				double lPercentOfCurr = 0;
				double lPrevProgTxt_Total=0;
				double lPercentOfProg = 0;
				String lStrFundNm = "";
				
				if(lhshSubConFundMap.get("FundName").toString().equalsIgnoreCase("Revenue Expenditure"))
					lStrFundNm = "RevExp";
				else 
					lStrFundNm = "CapExp";
				
				HashMap lhshMapRevExp = lObjRptQueryDAO.getFundMjrRange(Integer.parseInt(lhshSubConFundMap.get("FundId").toString()));
				ArrayList lArrMjrHdRange = getMjrHdRange(lhshMapRevExp.get("MjrHdRange").toString());
				glogger.info("MjrHdRange for "+lhshSubConFundMap.get("FundName")+" -- "+lArrMjrHdRange);
				
				HashMap lhshMapRevExpAmt = lObjRptQueryDAO.getConsolidatedExpBudAmt(lArrMjrHdRange,lintFinYrId);
				
				lPLTotal = Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_PL_CH").toString());
				lPLTotal = lPLTotal + Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_PL_VT").toString());
				lhshConsolidatedFundMap.put(lStrFundNm+"_Bud_PL",lPLTotal);
				glogger.info(lStrFundNm+"_Bud_PL == "+lPLTotal);
				
				lNPTotal = Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_NP_CH").toString());
				lNPTotal = lNPTotal + Double.parseDouble(lhshMapRevExpAmt.get("ConsExp_NP_VT").toString());
				
				lCSSTotal = lObjRptQueryDAO.getCSSAmount(lArrMjrHdRange, lintFinYrId);
				lhshConsolidatedFundMap.put(lStrFundNm+"_Bud_CSS",lCSSTotal);
				
				lNPTotal = lNPTotal - lCSSTotal;
				lhshConsolidatedFundMap.put(lStrFundNm+"_Bud_NP",lNPTotal);
				glogger.info(lStrFundNm+"_Bud_CSS == "+lCSSTotal);
				glogger.info(lStrFundNm+"_Bud_NP == "+lNPTotal);
				
				lhshConsolidatedFundMap.put(lStrFundNm+"_Bud_Total",lPLTotal+lNPTotal+lCSSTotal);
				
				HashMap lhshGrantData = lObjRptQueryDAO.getGrantAmt(lArrMjrHdRange, lintFinYrId, "en_US");
				lhshConsolidatedFundMap.put(lStrFundNm+"_Grant_PL", lhshGrantData.get("GrantAmt_PL"));
				lhshConsolidatedFundMap.put(lStrFundNm+"_Grant_NP", lhshGrantData.get("GrantAmt_NP"));
				lhshConsolidatedFundMap.put(lStrFundNm+"_Grant_CSS", lhshGrantData.get("GrantAmt_CSS"));
				lhshConsolidatedFundMap.put(lStrFundNm+"_Grant_Total", Double.parseDouble(lhshGrantData.get("GrantAmt_Total").toString()));
				
				lPLTotal = 0;
				lNPTotal = 0;
				lCSSTotal = 0;
				
				HashMap lhshCurrData = lObjRptQueryDAO.getCurrentExpAmt(lArrMjrHdRange, lStrFromDt, lStrToDt);
				HashMap lhshCurrDataText = lObjRptQueryDAO.getTextExpData(lArrMjrHdRange, 9, lStrFromDt, lStrToDt);
		
				lPLTotal = Double.parseDouble(lhshCurrData.get("CurrentExp_PL").toString());
				lPLTotal += Double.parseDouble(lhshCurrDataText.get("Txt_PL").toString());
				lhshConsolidatedFundMap.put(lStrFundNm+"_Curr_PL",lPLTotal);
				
				lNPTotal = Double.parseDouble(lhshCurrData.get("CurrentExp_NP").toString());
				lNPTotal += Double.parseDouble(lhshCurrDataText.get("Txt_NP").toString());
				lhshConsolidatedFundMap.put(lStrFundNm+"_Curr_NP",lNPTotal);
				
				lCSSTotal += Double.parseDouble(lhshCurrDataText.get("Txt_CSS").toString());;
				lhshConsolidatedFundMap.put(lStrFundNm+"_Curr_CSS",lCSSTotal);
				
				lCurrTotal = lPLTotal+ lNPTotal+lCSSTotal;
				lhshConsolidatedFundMap.put(lStrFundNm+"_Curr_Total",lCurrTotal);
				
				lPLTotal = 0;
				lNPTotal = 0;
				lCSSTotal = 0;
				HashMap lhshPrgData = lObjRptQueryDAO.getProgressiveExpAmt(lArrMjrHdRange, lintFinYrId, lStrToDt);
				HashMap lhshPrgDataText = lObjRptQueryDAO.getTextExpData(lArrMjrHdRange, 9, "", lStrToDt);
				
				lPLTotal = Double.parseDouble(lhshPrgData.get("ProgExp_PL").toString());
				lPLTotal += Double.parseDouble(lhshPrgDataText.get("Txt_PL").toString());
				lhshConsolidatedFundMap.put(lStrFundNm+"_Prg_PL",lPLTotal);
				
				lNPTotal = Double.parseDouble(lhshPrgData.get("ProgExp_NP").toString());
				lNPTotal += Double.parseDouble(lhshPrgDataText.get("Txt_NP").toString());	
				lhshConsolidatedFundMap.put(lStrFundNm+"_Prg_NP",lNPTotal);
				
				lCSSTotal += Double.parseDouble(lhshPrgDataText.get("Txt_CSS").toString());;
				lhshConsolidatedFundMap.put(lStrFundNm+"_Prg_CSS",lCSSTotal);
				
				lCurrTotal = lPLTotal+ lNPTotal+lCSSTotal;
				lhshConsolidatedFundMap.put(lStrFundNm+"_Prg_Total",lCurrTotal);
				
				/*lPrevRE = lPrevRE + lObjRptQueryDAO.getTotalREAmt(lArrMjrHdRange, "6");
				HashMap lhshPrevCurrDataText = lObjRptQueryDAO.getTextExpData(lArrMjrHdRange, 8, lStrPrevFromDate, lStrPrevToDate);
				lPrevCurrTxt_Total = lPrevCurrTxt_Total + Double.parseDouble(lhshPrevCurrDataText.get("Txt_Total").toString());
				
				HashMap lhshPrevProgDataText = lObjRptQueryDAO.getTextExpData(lArrMjrHdRange, 8, "", lStrPrevToDate);
				lPrevProgTxt_Total=lPrevProgTxt_Total+Double.parseDouble(lhshPrevProgDataText.get("Txt_Total").toString());
				
				if(lPrevRE!=0)
				{
					lPercentOfCurr=((lPrevCurrTxt_Total*100)/lPrevRE);
					lPercentOfProg=((lPrevProgTxt_Total*100)/lPrevRE);
				}
				else
				{
					lPercentOfCurr=0;
					lPercentOfProg=0;
				}
				glogger.info("PrevCurrTxt Total == "+lPrevCurrTxt_Total);
				glogger.info("lPrevProgTxt_Total == "+lPrevProgTxt_Total);
				glogger.info("lPrevRE == "+lPrevRE);
				
				lhshConsolidatedFundMap.put(lStrFundNm+"_PercentOfCurrPrv", lPercentOfCurr);
				lhshConsolidatedFundMap.put(lStrFundNm+"_PercentOfProg", lPercentOfProg);
				glogger.info("PercentOfCurrPrv for "+lStrFundNm+" is == "+lPercentOfCurr);
				glogger.info("PercentOfProg for "+lStrFundNm+" is == "+lPercentOfProg);*/
			}
		}
		return lhshConsolidatedFundMap;
	}
	

	private HashMap getContingencyPublicFund(int lintFinYrId, String lStrLangId, String lStrFromDt, String lStrToDt,int lintFundId)
	{
		glogger.info("-- In getContingencyPublicFund() -- ");
		String lStrFundName = "";
		double ltotalContAmount = 0;
		String lStrPrevFromDate = "";
		String lStrPrevToDate = "";
		double lPrevCurrTxt_Total = 0;
		double lPrevRE = 0;
		double lPercentOfCurr = 0;
		double lPrevProgTxt_Total=0;
		double lPercentOfProg = 0;
		double lPLTotal = 0;
		double lNPTotal = 0;
		double lCSSTotal = 0;
		double lCurrTotal = 0;
		
		HashMap lhshPrevDateMap = getPreviousDates(lStrFromDt,lStrToDt);
		lStrPrevFromDate=lhshPrevDateMap.get("fromdate").toString();
		lStrPrevToDate=lhshPrevDateMap.get("todate").toString();

		HashMap lhshContingencyFundMap = new HashMap();
		
		CommonRptQueryDAO lObjRptQueryDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);

		HashMap lhshSubConFundMap = (HashMap)lObjRptQueryDAO.getFundMjrRange(lintFundId);
		glogger.info("Fund Range for ContingencyPublicFund -- "+lhshSubConFundMap.get("MjrHdRange"));
		
		if(lintFundId==4)
			lStrFundName = "ConFund";
		else if(lintFundId==5)
			lStrFundName = "PubFund";
		
		ArrayList lArrMjrHdRange = getMjrHdRange(lhshSubConFundMap.get("MjrHdRange").toString());
		HashMap lhshMapContRecpAmt = lObjRptQueryDAO.getPublicContingencyFund(lArrMjrHdRange,lintFinYrId,lStrLangId);
		ltotalContAmount = ltotalContAmount + Double.parseDouble(lhshMapContRecpAmt.get("Cont_Public_FundTotal").toString());
	
		lhshContingencyFundMap.put(lStrFundName+"_Bud_PL", 0);
		lhshContingencyFundMap.put(lStrFundName+"_Bud_NP", 0);
		lhshContingencyFundMap.put(lStrFundName+"_Bud_CSS", 0);
		lhshContingencyFundMap.put(lStrFundName+"_Bud_Total", ltotalContAmount );
		glogger.info("Total in getContingencyPublicFund() == "+lhshContingencyFundMap.get(lStrFundName+"_Bud_Total"));
		
		HashMap lhshGrantData = lObjRptQueryDAO.getGrantAmt(lArrMjrHdRange, lintFinYrId, "en_US");
		lhshContingencyFundMap.put(lStrFundName+"_Grant_PL", lhshGrantData.get("GrantAmt_PL"));
		lhshContingencyFundMap.put(lStrFundName+"_Grant_NP", lhshGrantData.get("GrantAmt_NP"));
		lhshContingencyFundMap.put(lStrFundName+"_Grant_CSS", lhshGrantData.get("GrantAmt_CSS"));
		lhshContingencyFundMap.put(lStrFundName+"_Grant_Total", Double.parseDouble(lhshGrantData.get("GrantAmt_Total").toString()));
		
		HashMap lhshCurrExpData = lObjRptQueryDAO.getCurrentExpAmt(lArrMjrHdRange, lStrFromDt, lStrToDt);
		HashMap lhshCurrExpDataText = lObjRptQueryDAO.getTextExpData(lArrMjrHdRange, 9, lStrFromDt, lStrToDt);
		HashMap lhshCurrRecpDataText = lObjRptQueryDAO.getTextRecptData(lArrMjrHdRange, 9, lStrFromDt, lStrToDt);
		
		lPLTotal = Double.parseDouble(lhshCurrExpData.get("CurrentExp_PL").toString());
		lhshContingencyFundMap.put(lStrFundName+"_Curr_PL",lPLTotal);
		
		lNPTotal = Double.parseDouble(lhshCurrExpData.get("CurrentExp_NP").toString());
		lhshContingencyFundMap.put(lStrFundName+"_Curr_NP",lNPTotal);
		
		lhshContingencyFundMap.put(lStrFundName+"_Curr_CSS",lCSSTotal);
		
		lCurrTotal = Double.parseDouble(lhshCurrExpData.get("CurrentExp_Total").toString());
		lCurrTotal = lCurrTotal + Double.parseDouble(lhshCurrExpDataText.get("Txt_Total").toString());
		
		if(lintFundId==4)
		{
			lCurrTotal = Double.parseDouble(lhshCurrRecpDataText.get("Txt_Total").toString()) - lCurrTotal;
		}
		else if(lintFundId==5)
		{
			lCurrTotal = Double.parseDouble(lhshCurrRecpDataText.get("Txt_Total").toString()) - lCurrTotal;
		}
		
		lhshContingencyFundMap.put(lStrFundName+"_Curr_Total",lCurrTotal);
		
		lPLTotal = 0;
		lNPTotal = 0;
		lCSSTotal = 0;
		HashMap lhshPrgData = lObjRptQueryDAO.getProgressiveExpAmt(lArrMjrHdRange, lintFinYrId, lStrToDt);
		HashMap lhshPrgExpDataText = lObjRptQueryDAO.getTextExpData(lArrMjrHdRange, 9, "", lStrToDt);
		HashMap lhshPrgDataText = lObjRptQueryDAO.getTextRecptData(lArrMjrHdRange, 9, "", lStrToDt);
		
		lPLTotal = Double.parseDouble(lhshPrgData.get("ProgExp_PL").toString());
		lhshContingencyFundMap.put(lStrFundName+"_Prg_PL",lPLTotal);
		
		lNPTotal = Double.parseDouble(lhshPrgData.get("ProgExp_NP").toString());
		lhshContingencyFundMap.put(lStrFundName+"_Prg_NP",lNPTotal);
		
		lhshContingencyFundMap.put(lStrFundName+"_Prg_CSS",lCSSTotal);
		
		lCurrTotal = Double.parseDouble(lhshPrgData.get("ProgExp_Total").toString());
		lCurrTotal = lCurrTotal + Double.parseDouble(lhshPrgExpDataText.get("Txt_Total").toString());
		
		if(lintFundId==4)
		{
			lCurrTotal = Double.parseDouble(lhshPrgDataText.get("Txt_Total").toString()) - lCurrTotal;
		}
		else if(lintFundId==5)
		{
			lCurrTotal = Double.parseDouble(lhshPrgDataText.get("Txt_Total").toString()) - lCurrTotal;
		}
		
		lhshContingencyFundMap.put(lStrFundName+"_Prg_Total",lCurrTotal);
		
		/*lPrevRE = lPrevRE + lObjRptQueryDAO.getTotalREAmt(lArrMjrHdRange, "6");
		HashMap lhshPrevCurrDataText = lObjRptQueryDAO.getTextRecptData(lArrMjrHdRange, 8, lStrPrevFromDate, lStrPrevToDate);
		lPrevCurrTxt_Total = lPrevCurrTxt_Total + Double.parseDouble(lhshPrevCurrDataText.get("Txt_Total").toString());
		
		HashMap lhshPrevProgDataText = lObjRptQueryDAO.getTextRecptData(lArrMjrHdRange, 8, "", lStrPrevToDate);
		lPrevProgTxt_Total=lPrevProgTxt_Total+Double.parseDouble(lhshPrevProgDataText.get("Txt_Total").toString());

		if(lPrevRE != 0)
		{
			lPercentOfCurr=((lPrevCurrTxt_Total*100)/lPrevRE);
			lPercentOfProg=((lPrevProgTxt_Total*100)/lPrevRE);
		}
		else
		{
			lPercentOfCurr=0;
			lPercentOfProg=0;
		}
		glogger.info("PrevCurrTxt Total == "+lPrevCurrTxt_Total);
		glogger.info("lPrevProgTxt_Total == "+lPrevProgTxt_Total);
		glogger.info("lPrevRE == "+lPrevRE);
		
		lhshContingencyFundMap.put(lStrFundName+"_PercentOfCurrPrv", lPercentOfCurr);
		lhshContingencyFundMap.put(lStrFundName+"_PercentOfProg", lPercentOfProg);

		glogger.info("PercentOfCurrPrv for "+lStrFundName+" is == "+lPercentOfCurr);
		glogger.info("PercentOfProg for "+lStrFundName+" is == "+lPercentOfProg);*/
		glogger.info("Before Returning from getContingencyPublicFund()");
		return  lhshContingencyFundMap;
	}
	
	private ArrayList getMjrHdRange(String lStrRange)
	{
		glogger.info("In getMjrHdRange: RangeString == "+lStrRange);
		ArrayList lArrRangeLst = new ArrayList();
		String[] lStrMjHdRange = null;
		if(lStrRange.contains("-"))
		{
			lStrMjHdRange = lStrRange.split("-");
			if(lStrMjHdRange!=null)
			{
				lArrRangeLst.add(lStrMjHdRange[0]);
				lArrRangeLst.add(lStrMjHdRange[1]);
			}
		}
		else
		{
			lArrRangeLst.add(lStrRange);
		}
		glogger.info("MjrHD Range in getMjrHdRange() -- "+lArrRangeLst.toString());
		return lArrRangeLst;
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
		try
		{
			//if(lStrFrmDate!=null && lStrToDate!=null )
			if(!lStrFrmDate.equals("") && !lStrToDate.equals(""))
			{
				glogger.info("In Both Not Null");
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
				
				lFromDate= sdf.parse(lStrFrmDate);
				lToDate = sdf.parse(lStrToDate);
				lGCfrmDate.setTime(lFromDate);
				lGCtoDate.setTime(lToDate);
				int liDateIdx=lGCfrmDate.DATE;
				int liDate = lGCfrmDate.get(liDateIdx);
				int liMnthIdx=lGCfrmDate.MONTH;
				int liMnth = lGCfrmDate.get(liMnthIdx);
				liMnth++;
				int liYrIdx=lGCfrmDate.YEAR;
				int liYr = lGCfrmDate.get(liYrIdx);
				String lStrFromDate = liYr+"-"+liMnth+"-"+liDate;
				
				liDateIdx=lGCtoDate.DATE;
				liDate = lGCtoDate.get(liDateIdx);
				liMnthIdx=lGCtoDate.MONTH;
				liMnth = lGCtoDate.get(liMnthIdx);
				liMnth++;
				liYrIdx=lGCtoDate.YEAR;
				liYr = lGCtoDate.get(liYrIdx);
				String lStringToDate = liYr+"-"+liMnth+"-"+liDate;
				
				lLongDifference = (lToDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
				lLngPrgDifference = (lToDate.getTime() - lPrgFromDt.getTime()) / (1000*60*60*24);
				hmp.put("fromdate",lStrFromDate);
				hmp.put("todate",lStringToDate);
				hmp.put("difference",lLongDifference);
				hmp.put("prgDifference",lLngPrgDifference);

			}
			//else if(lStrFrmDate!=null && lStrToDate==null)
			else if(!lStrFrmDate.equals("") && lStrToDate.equals(""))
			{
				glogger.info("In FromDate Not Null - ToDate NULL ");
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
				
				lGCcurrDate.setTime(lDateCurrDate);
				lFromDate= sdf.parse(lStrFrmDate);
				lGCfrmDate.setTime(lFromDate);
				lGCtoDate.setTime(lDateCurrDate);
				int liDateIdx=lGCfrmDate.DATE;
				int liDate = lGCfrmDate.get(liDateIdx);
				int liMnthIdx=lGCfrmDate.MONTH;
				int liMnth = lGCfrmDate.get(liMnthIdx);
				liMnth++;
				int liYrIdx=lGCfrmDate.YEAR;
				int liYr = lGCfrmDate.get(liYrIdx);
				
				int liCurrMnthIdx=lGCtoDate.MONTH;
				int liCurrMnth = lGCtoDate.get(liCurrMnthIdx);
				liCurrMnth++;
				
				if(liCurrMnth>=1 && liCurrMnth<5)
				{
					liYr--;
				}
				String lStrFromDate = liYr+"-"+liMnth+"-"+liDate;
				
				liDateIdx=lGCtoDate.DATE;
				liDate = lGCtoDate.get(liDateIdx);
				

				liYrIdx=lGCtoDate.YEAR;
				liYr = lGCtoDate.get(liYrIdx);
				String lStringToDate = liYr+"-"+liCurrMnth+"-"+liDate;
				
				lLongDifference = (lDateCurrDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
				lLngPrgDifference = (lDateCurrDate.getTime() - lPrgFromDt.getTime()) / (1000*60*60*24);
				hmp.put("fromdate",lStrFromDate);
				hmp.put("todate",lStringToDate);
				hmp.put("difference",lLongDifference);
				hmp.put("prgDifference",lLngPrgDifference);
				
			}
			//else if(lStrFrmDate==null && lStrToDate!=null)
			else if(lStrFrmDate.equals("") && !lStrToDate.equals(""))
			{
				glogger.info("In FromDate Null - ToDate NOT NULL ");
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
				
				lGCcurrDate.setTime(lDateCurrDate);
				lIyrId=lGCcurrDate.YEAR;
				y=lGCcurrDate.get(lIyrId);
				String lStrFrmDateTmp = "01/04/"+y;
				lFromDate= sdf.parse(lStrFrmDateTmp);
				lToDate = sdf.parse(lStrToDate);
				lGCfrmDate.setTime(lFromDate);
				lGCtoDate.setTime(lToDate);
				int liDateIdx=lGCfrmDate.DATE;
				int liDate = lGCfrmDate.get(liDateIdx);
				int liMnthIdx=lGCfrmDate.MONTH;
				int liMnth = lGCfrmDate.get(liMnthIdx);
				liMnth++;
				int liYrIdx=lGCfrmDate.YEAR;
				int liYr = lGCfrmDate.get(liYrIdx);
				
				int liCurrMnthIdx=lGCtoDate.MONTH;
				int liCurrMnth = lGCtoDate.get(liCurrMnthIdx);
				liCurrMnth++;
				if(liCurrMnth>=1 && liCurrMnth<5)
				{
					liYr--;
				}
				
				String lStrFromDate = liYr+"-"+liMnth+"-"+liDate;
				
				liDateIdx=lGCtoDate.DATE;
				liDate = lGCtoDate.get(liDateIdx);
				
				liYrIdx=lGCtoDate.YEAR;
				liYr = lGCtoDate.get(liYrIdx);
				String lStringToDate = liYr+"-"+liCurrMnth+"-"+liDate;
				
				lLongDifference = (lToDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
				lLngPrgDifference = (lToDate.getTime() - lPrgFromDt.getTime()) / (1000*60*60*24);
				hmp.put("fromdate",lStrFromDate);
				hmp.put("todate",lStringToDate);
				hmp.put("difference",lLongDifference);
				hmp.put("prgDifference",lLngPrgDifference);
			}
			//else if(lStrFrmDate==null && lStrToDate==null)
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
				
				lGCcurrDate.setTime(lDateCurrDate);
				lIyrId=lGCcurrDate.YEAR;
				y=lGCcurrDate.get(lIyrId);
				String lStrFrmDateTmp = "01/04/"+y;
				lFromDate= sdf.parse(lStrFrmDateTmp);
				lGCfrmDate.setTime(lFromDate);
				lGCtoDate.setTime(lDateCurrDate);
				int liDateIdx=lGCfrmDate.DATE;
				int liDate = lGCfrmDate.get(liDateIdx);
				int liMnthIdx=lGCfrmDate.MONTH;
				int liMnth = lGCfrmDate.get(liMnthIdx);
				liMnth++;
				int liYrIdx=lGCfrmDate.YEAR;
				int liYr = lGCfrmDate.get(liYrIdx);
				
				int liCurrMnthIdx=lGCtoDate.MONTH;
				int liCurrMnth = lGCtoDate.get(liCurrMnthIdx);
				liCurrMnth++;
				if(liCurrMnth>=1 && liCurrMnth<5)
				{
					liYr--;
				}
				String lStrFromDate = liYr+"-"+liMnth+"-"+liDate;
				
				liDateIdx=lGCtoDate.DATE;
				liDate = lGCtoDate.get(liDateIdx);
				
				
				liYrIdx=lGCtoDate.YEAR;
				liYr = lGCtoDate.get(liYrIdx);
				String lStringToDate = liYr+"-"+liCurrMnth+"-"+liDate;
				
				lLongDifference = (lDateCurrDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
				lLngPrgDifference = (lDateCurrDate.getTime() - lPrgFromDt.getTime()) / (1000*60*60*24);
				hmp.put("fromdate",lStrFromDate);
				hmp.put("todate",lStringToDate);
				hmp.put("difference",lLongDifference);
				hmp.put("prgDifference",lLngPrgDifference);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return hmp;
	}
	
	public HashMap getPreviousDates(String lStrFrmDate,String lStrToDate)
	{
		//System.out.println("----------Inside getDates() method---------");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		HashMap hmp=new HashMap();
		Date lFromDate;
		Date lToDate;
		long lLongDifference;
		try
		{
				GregorianCalendar lGCfrmDate= new GregorianCalendar();
				GregorianCalendar lGCtoDate= new GregorianCalendar();
				lFromDate= sdf.parse(lStrFrmDate);
				lToDate = sdf.parse(lStrToDate);
				lGCfrmDate.setTime(lFromDate);
				lGCtoDate.setTime(lToDate);
				int liDateIdx=lGCfrmDate.DATE;
				int liDate = lGCfrmDate.get(liDateIdx);
				int liMnthIdx=lGCfrmDate.MONTH;
				int liMnth = lGCfrmDate.get(liMnthIdx);
				liMnth++;
				int liYrIdx=lGCfrmDate.YEAR;
				int liYr = lGCfrmDate.get(liYrIdx);
				liYr--;
				String lStrFromDate = liYr+"-"+liMnth+"-"+liDate;
				
				liDateIdx=lGCtoDate.DATE;
				liDate = lGCtoDate.get(liDateIdx);
				liMnthIdx=lGCtoDate.MONTH;
				liMnth = lGCtoDate.get(liMnthIdx);
				liMnth++;
				liYrIdx=lGCtoDate.YEAR;
				liYr = lGCtoDate.get(liYrIdx);
				liYr--;
				String lStringToDate = liYr+"-"+liMnth+"-"+liDate;
				
				hmp.put("fromdate",lStrFromDate);
				hmp.put("todate",lStringToDate);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hmp;
	}
	
	private List getSrcOfInfoData(ReportVO report)
	{
		ArrayList lArrSrcInfoData = new ArrayList();
		
		ArrayList lArrSrcInfoRow1 = new ArrayList();
		lArrSrcInfoRow1.add("Budget Estimates            : Finance Dept.(IWDMS Budget)");
		lArrSrcInfoData.add(lArrSrcInfoRow1);
		lArrSrcInfoRow1 = new ArrayList();
		lArrSrcInfoRow1.add("Grant Released              : Finance Dept.(IWDMS)");
		lArrSrcInfoData.add(lArrSrcInfoRow1);
		lArrSrcInfoRow1 = new ArrayList();
		lArrSrcInfoRow1.add("Curr. Year Exp.             : DAT(EDP Cell)");
		lArrSrcInfoData.add(lArrSrcInfoRow1);
		lArrSrcInfoRow1 = new ArrayList();
		lArrSrcInfoRow1.add("Curr. Year Progressive Exp. : DAT(EDP Cell)");
		lArrSrcInfoData.add(lArrSrcInfoRow1);
		
		return lArrSrcInfoData;
	}
	
	private String getDisplayAmt(long n)
	{
	    return ((n > 0) ? (n + "") : "-"); 
	}
	
	private HashMap getDeptCapExpPlanAmt(int lintFinYear,String lStrLangId, String lStrFromDate,String lStrToDate,String strFundName)
	{
		glogger.info("-- In  getDeptCapExpPlanAmt -- ");
		
		CommonRptQueryDAO lObjRptQueryDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);

		HashMap lhshCapExpMap = (HashMap)lObjRptQueryDAO.getStrFundMjrRange(strFundName);//got MajorHeadRange
		
		glogger.info("---Fund Range for  getDeptCapExpPlanAmt -- "+lhshCapExpMap.get("MjrHdRange"));
		
		ArrayList lArrMajhdRange= getMjrHdRange(lhshCapExpMap.get("MjrHdRange").toString());//got Splitted Range
		
		HashMap lhshMapCapExpAmount = lObjRptQueryDAO.getDeptCapExpText(lArrMajhdRange,lStrLangId, lStrFromDate,lStrToDate);//got deptid,sum
		
		return  lhshMapCapExpAmount ;
	}

	private List getDeptGrantPlanExp(ReportVO lObjReport) 
	{
			glogger.info("------Going into getDeptGrantPlanExp changed------");
			List lArrMainDataLst = new ArrayList();
			int lIntDateDiff = 1;
		    int lIntPrvFinYr = 0;
		    int lIntMaxMnth = 1;
			ArrayList lArrDeptId=null;
			String lStrFromDate = "";
			String lStrToDate = "";
			String lStrLangId = "";
			String lStrFundNm = "";
			String lStrCapFundNm="";
			int lintFinYear = 0;
			String lStrFinYear=null;
			long lngGrantRelTotal=0;
		    double lDblPLGrntAmt=0;
		    double lDblTotalDiffPLGrntAmt=0;
		    long lLngTotalPLGrntAmt=0;
		    double lEstAmtTotal=0;
		    double lDblRevTotal=0;
		    double lDblCapTotal=0;
			double lGrantRelTotal=0;
			double lDblNPGrntAmt=0;
			double lGrantTotal=0;
			double lDblTotalDiffNPGrntAmt=0;
			double lDblCSSGrntAmt=0;
			double lDblTotalDiffCSSGrntAmt=0;
			double lngEstGrandTotal=0;
			long lngEstAmtTotal = 0;
			long lngRevTotal=0;
			long lngCapTotal=0;
			long lLngTotalNPGrntAmt=0;
			long lLngTotalCSSGrntAmt=0;
		    long ldiffDays=0;
		    long lngProreta=0;
		    
		    Date lDtStartDate = new Date();
		    Date lDtEndDate = new Date();
		    SimpleDateFormat sdOld = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	    	
	   	 
	        
		    NumberFormat lnfLong=NumberFormat.getNumberInstance();
	        lnfLong.setGroupingUsed(false);
	        lnfLong.setMaximumFractionDigits(3);
	        lnfLong.setMinimumFractionDigits(3);
			
			CommonRptQueryDAO lObjRptQueryDAO =(CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
			lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);
			
			String lStrPlanType = (String) lObjReport.getParameterValue("planType");
			glogger.info("PlanType is : "+ lStrPlanType);
			BudExpEstDAO lObjExpEstDAO = new BudExpEstDAOImpl(lObjSessionFactory);
			
			
			glogger.info("FromDate == "+lObjReport.getParameterValue("Datefrom"));
			
			glogger.info("ToDate == "+lObjReport.getParameterValue("Dateto"));
	
			if(lObjReport.getParameterValue("Datefrom")!=null)
				lStrFromDate = (String)lObjReport.getParameterValue("Datefrom");
			
			if(lObjReport.getParameterValue("Dateto")!=null)
				lStrToDate = (String)lObjReport.getParameterValue("Dateto");
			
			
			HashMap lhshDateMap = getDates(lStrFromDate,lStrToDate);
			
			lStrFromDate = lhshDateMap.get("fromdate").toString();
			lStrToDate = lhshDateMap.get("todate").toString();
			ldiffDays = Long.parseLong(lhshDateMap.get("difference").toString());
			
			try
	        {  
				glogger.info("-Inside try of Date Formet--");
	            lDtStartDate = sd.parse(lStrFromDate );
	            lDtEndDate = sd.parse(lStrToDate);
	            
	            glogger.info("lDtStartDate is : "+  lDtStartDate + " lDtEndDate is :" +  lDtEndDate);
	            //System.out.println("StartDate:"+lDtStartDate);
	            //System.out.println("StartDate:"+lDtEndDate);
	            //System.out.println("Date Difference is:"+ldiffDays);
	        }
	        catch(Exception e)
	        {
	            glogger.error("Error in parsing the date" + e,e);
	        }
	        
			
			lintFinYear = Integer.parseInt(lObjReport.getParameterValue("finYear").toString());
			lStrFinYear=lObjReport.getParameterValue("finYear").toString();
			glogger.info("-------FinYear -------- "+lintFinYear);
			lStrLangId = lObjReport.getLangId();
		
			ArrayList lArrDeptList=new ArrayList();
			
			try
			{
				lArrDeptList = lObjExpEstDAO.getAllDept(lObjReport.getLangId()); 
			}
			catch(Exception e)
			{
				glogger.error("Error in execution of getDeptGrantPlanExp and Error is : " + e,e);
				e.printStackTrace();
			}
			
			
			ArrayList lArrInner=null;
			lStrFundNm="Revenue Expenditure";
			lStrCapFundNm="Capital Expenditure(Incl. Loans)";
			
			HashMap lhshEstimatedAmt = getEstimatedPlanAmt(lintFinYear, lStrLangId,lStrFromDate,lStrToDate,lStrFundNm);
			HashMap lhshGrantReleaseAmt=getGrantReleasePlanAmt(lStrFinYear, lDtStartDate,lDtEndDate,lStrLangId,lStrFundNm);
			HashMap lhshDeptRevExpAmt= getDeptRevExpPlanAmt(lintFinYear, lStrLangId,lStrFromDate,lStrToDate,lStrFundNm);
			HashMap lhshDeptCapExpAmt= getDeptCapExpPlanAmt(lintFinYear, lStrLangId,lStrFromDate,lStrToDate,lStrCapFundNm);
			
		
			ArrayList lArrEstReturn=new ArrayList();
		
			for(int i=0;i<lArrDeptList.size();i++)
			{
				lArrInner=new ArrayList();
				lArrInner.add(i+1);
				BudExpEstDeptVO lObjBudExpEstVO=(BudExpEstDeptVO)lArrDeptList.get(i);
				
				lArrInner.add(lObjBudExpEstVO.getDeptName());
				
				if(lhshEstimatedAmt.containsKey(lObjBudExpEstVO.getDeptID()))
				{
					lngEstAmtTotal = Math.round(Double.parseDouble(lhshEstimatedAmt.get(lObjBudExpEstVO.getDeptID()).toString()));
					lEstAmtTotal += Double.parseDouble(lhshEstimatedAmt.get(lObjBudExpEstVO.getDeptID()).toString()) - lngEstAmtTotal;
					lArrInner.add(lngEstAmtTotal);
				}
				
				else 
				{
				    lArrInner.add("-");
				}	 
						 
				 String lStrDeptId=lObjBudExpEstVO.getDeptID().toString();
				 
				 lDblPLGrntAmt = (lhshGrantReleaseAmt.get(lStrDeptId + "_PL") != null) ? 
				 (Long.parseLong(lhshGrantReleaseAmt.get(lStrDeptId + "_PL").toString()) *  lIntDateDiff) /lIntMaxMnth : 0;
				 lLngTotalPLGrntAmt +=  Math.round(lDblPLGrntAmt / 10000000);
				 
				 lDblTotalDiffPLGrntAmt += Math.round(lDblPLGrntAmt / 10000000) - (lDblPLGrntAmt / 10000000);
		
				 lDblNPGrntAmt = (lhshGrantReleaseAmt.get(lStrDeptId + "_NP") != null) ? 
				 (Long.parseLong(lhshGrantReleaseAmt.get(lStrDeptId + "_NP").toString()) *  lIntDateDiff) /lIntMaxMnth : 0;
				 lLngTotalNPGrntAmt +=  Math.round(lDblNPGrntAmt / 10000000);
				 lDblTotalDiffNPGrntAmt += Math.round(lDblNPGrntAmt / 10000000) - (lDblNPGrntAmt / 10000000);
				          
				  lDblCSSGrntAmt = (lhshGrantReleaseAmt.get(lStrDeptId + "_CSS") != null) ? 
				  (Long.parseLong(lhshGrantReleaseAmt.get(lStrDeptId + "_CSS").toString()) *  lIntDateDiff) /lIntMaxMnth : 0;
				  lLngTotalCSSGrntAmt +=  Math.round(lDblCSSGrntAmt / 10000000);
				 lDblTotalDiffCSSGrntAmt += Math.round(lDblCSSGrntAmt / 10000000) - (lDblCSSGrntAmt / 10000000);
				 
				  
				 lDblPLGrntAmt=(Math.round(lDblPLGrntAmt / 10000000));	// Grant Plan
				 lDblNPGrntAmt =(Math.round(lDblNPGrntAmt / 10000000));	// Grant NonPlan
				 lDblCSSGrntAmt =(Math.round(lDblCSSGrntAmt / 10000000));	// Grant CSS
				
		          lngGrantRelTotal=( lLngTotalPLGrntAmt  +  lLngTotalNPGrntAmt+ lLngTotalCSSGrntAmt );
		          lArrInner.add(lngGrantRelTotal);	
		          
		          
		         if(lhshDeptRevExpAmt.containsKey(lObjBudExpEstVO.getDeptID()))
		          {
		        	  glogger.info("Inside if of lhshDeptRevExpAmt--");
		        	  lngRevTotal=Math.round(Double.parseDouble(lhshDeptRevExpAmt.get(lObjBudExpEstVO.getDeptID()).toString()));
		        	  lDblRevTotal+= Double.parseDouble(lhshDeptRevExpAmt.get(lObjBudExpEstVO.getDeptID()).toString()) - lngRevTotal;
		        	  lArrInner.add(lngRevTotal);
		          }
		          else
		          {
		        	  lArrInner.add("-");  
		          }
					
		         if(lhshDeptCapExpAmt.containsKey(lObjBudExpEstVO.getDeptID()))
		          {
		        	  glogger.info("Inside if of lhshDeptCapExpAmt--");
		        	  lngCapTotal=Math.round(Double.parseDouble(lhshDeptCapExpAmt.get(lObjBudExpEstVO.getDeptID()).toString()));
		        	  lDblCapTotal+= Double.parseDouble(lhshDeptCapExpAmt.get(lObjBudExpEstVO.getDeptID()).toString()) - lngCapTotal;
		        	  lArrInner.add(lngCapTotal);
		          }
		          else
		          {
		        	  lArrInner.add("-");  
		          }
					
					lArrInner.add("<b>"+lngRevTotal+lngCapTotal+"</b>");
					//lArrInner.add("-".toString());
					lngProreta=(((lngRevTotal+lngCapTotal)*100)*ldiffDays)/(lngGrantRelTotal*91);
					lArrInner.add(lngProreta);
					
					lArrMainDataLst.add(lArrInner);
			}
				
			 return lArrMainDataLst;
	}
	
	
	private HashMap getEstimatedPlanAmt(int lintFinYear, String lStrLangId,String lStrFromDate,String lStrToDate,String strFundName)
	{
		glogger.info("-- In getEstimatedAmt() -- ");
		
		ArrayList lArrMapEstAmt=new ArrayList();
		
		CommonRptQueryDAO lObjRptQueryDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);

		HashMap lhshMjrHdMap = (HashMap)lObjRptQueryDAO.getStrFundMjrRange(strFundName);//got MajorHeadRange
		
		glogger.info("---Fund Range for DeptBudEstAmt -- "+lhshMjrHdMap.get("MjrHdRange"));
		
		ArrayList lArrMajhdRange= getMjrHdRange(lhshMjrHdMap.get("MjrHdRange").toString());
		
		HashMap lhshMapEstAmount = lObjRptQueryDAO.getDeptBudEst(lArrMajhdRange,lintFinYear, lStrLangId);
	
		return lhshMapEstAmount;
	}
	
	private HashMap getGrantReleasePlanAmt(String lStrFinYear,Date lDtStartDate,Date lDtEndDate,String lStrLangId, String strFundName)
	{
		glogger.info("-- In  getGrantReleasePlanAmt -- ");
		
		
		CommonRptQueryDAO lObjRptQueryDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);

		HashMap lhshGrantRelMap = (HashMap)lObjRptQueryDAO.getStrFundMjrRange(strFundName);//got MajorHeadRange
		
		glogger.info("---Fund Range for  getGrantReleasePlanAmt -- "+lhshGrantRelMap.get("MjrHdRange"));
		
		ArrayList lArrMjrHdRange= getMjrHdRange(lhshGrantRelMap.get("MjrHdRange").toString());//got Splitted Range
		HashMap lhshMapGrantRelAmount =(HashMap)lObjRptQueryDAO.getAllDeptGrantCollAmt(lStrFinYear,lDtStartDate,lDtEndDate,lStrLangId);
	
		
		return  lhshMapGrantRelAmount ;
	}
	
	private HashMap getDeptRevExpPlanAmt(int lintFinYear,String lStrLangId, String lStrFromDate,String lStrToDate,String strFundName)
	{
		glogger.info("-- In  getDeptRevExpPlanAmt -- ");
		
		CommonRptQueryDAO lObjRptQueryDAO =  (CommonRptQueryDAO) DAOFactory.Create("com.tcs.sgv.common.dao.CommonRptQueryDAOImpl");
		lObjRptQueryDAO.setSessionFactory(lObjSessionFactory);

		HashMap lhshRevExpMap = (HashMap)lObjRptQueryDAO.getStrFundMjrRange(strFundName);//got MajorHeadRange
		
		glogger.info("---Fund Range for  getDeptRevExpPlanAmt -- "+lhshRevExpMap.get("MjrHdRange"));
		
		ArrayList lArrMajhdRange= getMjrHdRange(lhshRevExpMap.get("MjrHdRange").toString());//got Splitted Range
		
		HashMap lhshMapRevExpAmount = lObjRptQueryDAO.getDeptRevExpText(lArrMajhdRange,lStrLangId, lStrFromDate,lStrToDate);//got deptid,sum
		
		return  lhshMapRevExpAmount ;
	}
}
