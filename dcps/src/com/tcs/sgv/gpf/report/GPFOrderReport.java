package com.tcs.sgv.gpf.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.FinancialYearDAO;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.dao.reports.ReportsDAO;
import com.tcs.sgv.common.dao.reports.ReportsDAOImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.dcps.report.SixPayArrearAmountDepositionReportSchedule;
import com.tcs.sgv.gpf.dao.GPFRequestProcessDAO;
import com.tcs.sgv.gpf.dao.GPFRequestProcessDAOImpl;
import com.tcs.sgv.gpf.dao.ScheduleGenerationDAO;
import com.tcs.sgv.gpf.dao.ScheduleGenerationDAOImpl;

public class GPFOrderReport extends DefaultReportDataFinder implements ReportDataFinder {

	private static final Logger gLogger = Logger.getLogger(SixPayArrearAmountDepositionReportSchedule.class);
	public static String newline = System.getProperty("line.separator");
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";

	Map requestAttributes = null;
	ServiceLocator serviceLocator = null;
	SessionFactory lObjSessionFactory = null;

	public Collection findReportData(ReportVO report, Object criteria) throws ReportException {

		report.getLangId();
		report.getLocId();

		Connection con = null;

		requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		lObjSessionFactory = serviceLocator.getSessionFactorySlave();
		
		criteria.getClass();

		Statement smt = null;
		ResultSet rs = null;
		new ReportsDAOImpl();
		TabularData td = null;
		ReportVO RptVo = null;
		ReportsDAO reportsDao = new ReportsDAOImpl();
		
		ArrayList dataList = new ArrayList();
		try {

			con = lObjSessionFactory.getCurrentSession().connection();
			smt = con.createStatement();
			StyleVO[] rowsFontsVO = new StyleVO[5];
			rowsFontsVO[0] = new StyleVO();
			rowsFontsVO[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			rowsFontsVO[0].setStyleValue("12");
			rowsFontsVO[1] = new StyleVO();
			rowsFontsVO[1].setStyleId(IReportConstants.BACKGROUNDCOLOR);
			rowsFontsVO[1].setStyleValue("white");
			rowsFontsVO[2] = new StyleVO();
			rowsFontsVO[2].setStyleId(IReportConstants.BORDER);
			rowsFontsVO[2].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			rowsFontsVO[3] = new StyleVO();
			rowsFontsVO[3].setStyleId(26);
			rowsFontsVO[3].setStyleValue("JavaScript:self.close()");
			rowsFontsVO[4] = new StyleVO();
			rowsFontsVO[4].setStyleId(IReportConstants.REPORT_PAGINATION);
			rowsFontsVO[4].setStyleValue("NO");

			StyleVO[] noBorder = new StyleVO[1];
			noBorder[0] = new StyleVO();
			noBorder[0].setStyleId(IReportConstants.BORDER);
			noBorder[0].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);			

			report.setStyleList(rowsFontsVO);
			report.initializeDynamicTreeModel();
			report.initializeTreeModel();
			report.setStyleList(rowsFontsVO);

			StyleVO[] centerUnderlineBold = new StyleVO[4];
			centerUnderlineBold[0] = new StyleVO();
			centerUnderlineBold[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerUnderlineBold[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			centerUnderlineBold[1] = new StyleVO();
			centerUnderlineBold[1].setStyleId(IReportConstants.STYLE_TEXT_DECORATION);
			centerUnderlineBold[1].setStyleValue(IReportConstants.VALUE_STYLE_TEXT_DECORATION_UNDERLINE);
			centerUnderlineBold[2] = new StyleVO();
			centerUnderlineBold[2].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerUnderlineBold[2].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
			centerUnderlineBold[3] = new StyleVO();
			centerUnderlineBold[3].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			centerUnderlineBold[3].setStyleValue("14");

			StyleVO[] rightAlign = new StyleVO[2];
			rightAlign[0] = new StyleVO();
			rightAlign[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			rightAlign[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
			rightAlign[1] = new StyleVO();
			rightAlign[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			rightAlign[1].setStyleValue("12");

			StyleVO[] boldVO = new StyleVO[2];
			boldVO[0] = new StyleVO();
			boldVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
			boldVO[1] = new StyleVO();
			boldVO[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			boldVO[1].setStyleValue("14");

			// for Center Alignment format
			StyleVO[] CenterAlignVO = new StyleVO[3];
			CenterAlignVO[0] = new StyleVO();
			CenterAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			CenterAlignVO[0].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			CenterAlignVO[1] = new StyleVO();
			CenterAlignVO[1].setStyleId(IReportConstants.BORDER);
			CenterAlignVO[1].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			CenterAlignVO[2] = new StyleVO();
			CenterAlignVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			CenterAlignVO[2].setStyleValue("12");

			if (report.getReportCode().equals("800007")) {

				String reqType = (String) report.getParameterValue("reqType");
				String gpfAccNo = (String) report.getParameterValue("gpfAccNo");
				String lStrOrderId = (String) report.getParameterValue("orderNo");
				Long sancAmount = Math.round(Double.parseDouble((String) report.getParameterValue("sancAmount")));
				String empName = (String) report.getParameterValue("empName");
				Long basicSalary = Math.round(Double.valueOf((String) report.getParameterValue("basicSalary")));

				//String lStrJoiningDate = (String) report.getParameterValue("joiningDate");
				//String lStrRetirementDate = (String) report.getParameterValue("superAnnDate");

				//Integer retirementMonths = ;
				//Integer serviceMonths = ;
				
				SimpleDateFormat lObjDateFormate = new SimpleDateFormat("dd/MM/yyyy");

				Date today = new Date();
				//Date joiningDate = lObjDateFormate.parse(lStrJoiningDate);
				//Date retirementDate = lObjDateFormate.parse(lStrRetirementDate);

				FinancialYearDAO lObjFinancialYearDAO = new FinancialYearDAOImpl(null, serviceLocator
						.getSessionFactory());
				GPFRequestProcessDAO lObjGPFRequestProcess = new GPFRequestProcessDAOImpl(null, serviceLocator.getSessionFactory());
				Integer lIntFinYrId = lObjFinancialYearDAO.getFinYearIdByCurDate();
				Long lLngPreFinYearId = lIntFinYrId.longValue() - 1;
				Double OpeningBalance = lObjGPFRequestProcess.getOpeningBalForCurrYear(gpfAccNo,lIntFinYrId.longValue());
				ScheduleGenerationDAO lObjScheduleGenerationDAO = new ScheduleGenerationDAOImpl(null, serviceLocator
						.getSessionFactory());
				String preFinYear = lObjScheduleGenerationDAO.getFinYearCodeForFinYearId(lLngPreFinYearId);
				String lStrcurrFinYearCode = lObjScheduleGenerationDAO.getFinYearCodeForFinYearId(lIntFinYrId.longValue());
				lStrcurrFinYearCode = lStrcurrFinYearCode.substring(0,4);
				Date startDate = lObjScheduleGenerationDAO.getStartDateOfFinancialYear(lIntFinYrId.longValue());
				
				List lLstaccountBalance = lObjGPFRequestProcess.getGPFAccountBalance(gpfAccNo,lIntFinYrId.longValue());
				Object[] lObjGPFAccountBal = null;
				if (lLstaccountBalance != null && lLstaccountBalance.size() > 0) {
					lObjGPFAccountBal = (Object[]) lLstaccountBalance.get(0);					
				}
				
				Double lDblWithdrawalSanc = 0d;
				Object[] lObjhistory =null;
				List lLstAdvanceHistoryDtls = lObjGPFRequestProcess.getAdvanceHistory(gpfAccNo,lIntFinYrId.longValue());
				if (lLstAdvanceHistoryDtls != null && lLstAdvanceHistoryDtls.size() > 0) {
					for(Integer lIntcnt=0;lIntcnt<lLstAdvanceHistoryDtls.size();lIntcnt++){
						lObjhistory = (Object[]) lLstAdvanceHistoryDtls.get(lIntcnt);					
						lDblWithdrawalSanc += (Double) lObjhistory[1];
					}				    					
				}

				Integer startMonth = startDate.getMonth();
				Integer startYear = startDate.getYear();

				Integer currDate = today.getDate();
				Integer currMonth = today.getMonth()+1;
				Integer currYear = today.getYear()+1900;
				
				report.setStyleList(noBorder);
				report.setStyleList(rowsFontsVO);
				ArrayList rowList = new ArrayList();

				rowList.add("" + space(1));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Order : Reference No. "+lStrOrderId, boldVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(newline);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(newline);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Subject   :  To approve request amount from GPF A/C. " + gpfAccNo,
						CenterAlignVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(newline);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("  : Order : ", centerUnderlineBold));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						" As per rule 23 of GPF Revised Rules, Directorate of Accounts and Treasuries hereby approves withdrawal of "
								+ reqType + " amount (i.e. Rs " + sancAmount + "/- ) from the account of Mr./Ms./Mrs. "
								+ empName + " having GPF A/C No. " + gpfAccNo + " .", rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(newline);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Through amount approved for withdrawal to Mr./Ms. " + empName
						+ " is more than their 6 months salary, it is not more "
						+ "than 90% of amount accumumlated in their A/C. Their Basic Monthly salary is Rs. "
						+ basicSalary + "/- .", rowsFontsVO));
				dataList.add(rowList);

//				rowList = new ArrayList();
//				rowList.add(new StyledData("This is to certify that " + retirementMonths
//						+ " months are there for retirement of Mr./Ms./Mrs. " + empName + " and he/she has "
//						+ "completed " + serviceYears + " years  and " + serviceMonths
//						+ " months of service. Following is the account balance as on " + today + " in the "
//						+ "account of Mr./Ms./Mrs. " + empName, rowsFontsVO));
//				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add("" + space(1));
				dataList.add(rowList);
				
				ArrayList sixRowsLeft = new ArrayList();

				rowList = new ArrayList();
				rowList.add(new StyledData("1.  Balance in the year " + preFinYear + " brought down " , rowsFontsVO));
				rowList.add("Rs." + OpeningBalance);
				sixRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("2.  Amount deposited between 01/04/" + lStrcurrFinYearCode + " to "
						+ currDate + "/" + currMonth + "/" + currYear + " " , rowsFontsVO));
				rowList.add("Rs." + lObjGPFAccountBal[0]);
				sixRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("3.  Amount refundable during 01/04/" + lStrcurrFinYearCode + " - "
						+ currDate + "/" + + currMonth + "/" + currYear + " " , rowsFontsVO));
				rowList.add("Rs." + lObjGPFAccountBal[1]);
				sixRowsLeft.add(rowList);

				Double total = Math.round(OpeningBalance)+Double.parseDouble(lObjGPFAccountBal[0].toString())+Double.parseDouble(lObjGPFAccountBal[1].toString());
				
				rowList = new ArrayList();
				rowList.add(new StyledData("4.  Total of 1 + 2 + 3 ", rowsFontsVO));
				rowList.add("Rs." +total);
				sixRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("5.  Amount withdrawn between 01/04/" + lStrcurrFinYearCode + " - "
						+ currDate + "/" + + currMonth + "/" + currYear + " "+space(4), rowsFontsVO));
				rowList.add("Rs." + lDblWithdrawalSanc);
				sixRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("6.  Balance as on date of approval of withdrawal ", rowsFontsVO));
				rowList.add("Rs." +(total-lDblWithdrawalSanc));
				sixRowsLeft.add(rowList);

				
				td = new TabularData(sixRowsLeft);			
				RptVo = reportsDao.getReport("800009", report.getLangId(),report.getLocId());				
				ReportColumnVO[] lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(60);
				lArrReportColumnVO[1].setColumnWidth(40);
				(td).setRelatedReport(RptVo);
				(td).setStyles(noBorder);
				rowList = new ArrayList();
				rowList.add(td);
				dataList.add(rowList);
				
				rowList = new ArrayList();
				rowList.add(new StyledData(" Director" + space(10), rightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(" Accounts and Treasuries", rightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(" Maharashtra State, Mumbai", rightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(newline);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(newline);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add("CC- ");
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add("1.  Head of Department, Mumbai");
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add("2.  Pay and Accounts Office, Mumbai");
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add("3.  Accounts Officer, Accounts and Treasuries, Mumbai");
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add("4.  Joint Director, DAT");
				dataList.add(rowList);

			}

		} catch (Exception e) {			
			gLogger.error("Exception :" + e, e);
		} finally {
			try {
				if (smt != null) {
					smt.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}

				smt = null;
				rs = null;
				con = null;

			} catch (Exception e1) {				
				gLogger.error("Exception :" + e1, e1);

			}
		}
		return dataList;
	}

	public String space(int noOfSpace) {
		String blank = "";
		for (int i = 0; i < noOfSpace; i++) {
			blank += "\u00a0";
		}
		return blank;
	}

}
