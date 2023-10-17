package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportParameterVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.util.DBConnection;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class payrollReportDeductionDAO extends DefaultReportDataFinder
		implements ReportDataFinder {

	ResourceBundle locStrsBundle;

	private static Logger logger = Logger.getLogger(PayrollReportsDAO.class);

	private StyleVO[] selfCloseVO = null;

	private ResultSet lRs1 = null;

	final String CheckIfNull(Object lStra) {
		String lStrb = "";
		if (lStra != null) {
			lStrb = (((String) lStra).trim());

		}
		return lStrb;
	}

	public Collection findReportData(ReportVO report, Object criteria)
			throws ReportException {
		String langName = report.getLangId();
		int finalpagesize = 20;
		String locId = report.getLocId();
		Connection lCon = null;
		Statement lStmt = null;

		Map requestKeys = (Map) ((Map) criteria)
				.get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map) requestKeys.get("serviceMap");
		Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");
		CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap
				.get("locationVO");
		String locationName = locationVO.getLocName();
		long locationId = locationVO.getLocId();
		String locationNameincaps = locationName.toUpperCase();

		String upperfooter = "UNDER SECRETARY TO GOVT.";
		String lowerfooter = "GANDHINAGAR"
				+ System.getProperty("line.separator") + "Code No. PG3";
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle("resources.Payroll");
		ResourceBundle constantsBundle = ResourceBundle
				.getBundle("resources.eis.eis_Constants");
		ArrayList DataList = new ArrayList();
		StyleVO[] boldStyleVO = new StyleVO[1];
		boldStyleVO[0] = new StyleVO();
		boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
		StyledData dataStyle = null;
		StyleVO[] colorStyleVO = new StyleVO[1];
		colorStyleVO[0] = new StyleVO();
		colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
		colorStyleVO[0].setStyleValue("blue");
		selfCloseVO = new StyleVO[1];
		selfCloseVO[0] = new StyleVO();
		selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
		selfCloseVO[0].setStyleValue("javascript:self.close()");

		// Added by Urvin for setting up font size
		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO();
		baseFont[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		baseFont[0].setStyleValue("13");

		/*
		 * baseFont[1] = new StyleVO( ); baseFont[1].setStyleId(
		 * IReportConstants.STYLE_FONT_COLOR ); baseFont[1].setStyleValue(
		 * IReportConstants.VALUE_FONT_COLOR_DARKGRAY );
		 */
		// report.addReportStyles(baseFont);
		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER, baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT, baseFont);
		report.setReportTemplate(rt);
		// Added by Ravish for setting up font size

		// added by Samir Joshi fro each page Header
		ReportAttributeVO ravo1 = new ReportAttributeVO();
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_LOCATION);
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_ON_EACH_PAGE);
		ravo1.setAlignment(IReportConstants.HEADER_ALIGN_CENTER);
		report.addReportAttributeItem(ravo1);
		report.setAdditionalHeader("");
		// ended by samir joshi
		// Map requestAttributes = (Map) ((Map)
		// criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serv1 = (ServiceLocator) requestKeys
				.get("serviceLocator");

		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,
				serv1.getSessionFactory());
		boolean isBillDefined = paybillDAO.isBillsDefined(locationId);
		// Added by Mrugesh/Samir

		StyleVO[] styleVOPgBrk = null;
		styleVOPgBrk = new StyleVO[2];

		styleVOPgBrk[0] = new StyleVO();
		styleVOPgBrk[0]
				.setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
		styleVOPgBrk[0].setStyleValue("yes");
		styleVOPgBrk[0] = new StyleVO();

		styleVOPgBrk[1] = new StyleVO();
		styleVOPgBrk[1].setStyleId(IReportConstants.SHOW_REPORT_WHEN_NO_DATA);
		styleVOPgBrk[1].setStyleValue("no");

		report.addReportStyles(styleVOPgBrk);

		// Ended
		try {
			lCon = DBConnection.getConnection();
			lStmt = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			logger.info("getReportCode" + report.getReportCode());
			dataStyle = new StyledData();
			String portType = "";
			String BillNo = "";
			if (report.getReportCode().equals("3")
					|| report.getReportCode().equals("30")
					|| report.getReportCode().equals("31")
					|| report.getReportCode().equals("32")
					|| report.getReportCode().equals("33")
					|| report.getReportCode().equals("34")) {

				// Added by Akshay
				String fname = CheckIfNull(report.getParameterValue("FName"));
				String lname = CheckIfNull(report.getParameterValue("LName"));
				// Ended by Akshay

				// hardcoded
				long AISGradeCode = Long.parseLong(constantsBundle
						.getString("AISGradeCode"));

				StringBuffer lsb = new StringBuffer();

				String empid = CheckIfNull(report
						.getParameterValue("employeeName"));
				String Department = "";
				String Grade = "";
				String Scale = "";
				String Designation = "";
				String month = "";
				String year = "";
				String GroupBy = "";
				String ReportType = "";
				GroupBy = CheckIfNull(report.getParameterValue("Group By"));
				Department = CheckIfNull(report.getParameterValue("Department"));
				if (Department.equals("") || Department.equals("-1"))
					Department = locationId + "";
				// added by samir joshi for bill no wise report

				String BillNoinner = "";// GAD specific
				BillNoinner = CheckIfNull(report.getParameterValue("Bill No"));
				StringTokenizer st1 = new StringTokenizer(BillNoinner, "~");
				int l = 0;
				String subheadCode = "";
				String classIds = "";
				String desgnIds = "";
				while (st1.hasMoreTokens()) {
					if (l == 0)
						subheadCode = st1.nextToken();
					else if (l == 1)
						classIds = st1.nextToken();
					else if (l == 2)
						desgnIds = st1.nextToken();
					else if (l == 3)
						portType = st1.nextToken();
					else if (l == 4)
						BillNo = st1.nextToken();
					l++;
				}
				String DeptName = locationNameincaps;
				// ended by samir joshi/////
				// for report footer
				/*
				 * ReportAttributeVO ravo = new ReportAttributeVO();
				 * 
				 * ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				 * ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				 * ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
				 * 
				 * ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);
				 * 
				 * //add the attribute to the report instance
				 * report.addReportAttributeItem(ravo);
				 */
				ArrayList tblData = new ArrayList();

				ArrayList trow1 = new ArrayList();

				trow1.add(" ");
				trow1.add(" ");
				trow1.add(" " + System.getProperty("line.separator"));
				tblData.add(trow1);// added first row of the tabular data

				ArrayList trow2 = new ArrayList();

				trow2.add(" ");
				trow2.add(" ");
				trow2.add(" " + System.getProperty("line.separator"));
				tblData.add(trow2);// added second row of the tabular data

				ArrayList trow4 = new ArrayList();

				trow4.add(" ");
				trow4.add(" ");
				trow4.add("UNDER SECRRETARY TO GOVT. ");
				tblData.add(trow4);// added second row of the tabular data

				ArrayList trow5 = new ArrayList();

				trow5.add(" ");
				trow5.add(" ");
				trow5.add("GENERAL ADMIN. DEPTT.");
				tblData.add(trow5);// added second row of the tabular data

				ArrayList trow3 = new ArrayList();

				trow3.add(" ");
				trow3.add(" ");
				trow3.add("GANDHINAGAR");

				tblData.add(trow3);// added third row of the tabular data

				ArrayList trow6 = new ArrayList();
				trow6.add(" ");
				trow6.add(" ");
				trow6.add("Code No.PG3");

				tblData.add(trow6);// added sixth row of the tabular data

				TabularData tabularData = new TabularData(tblData);// initialize
																	// tabular
																	// data
				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY,
						IReportConstants.VALUE_FONT_FAMILY_ARIAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE,
						IReportConstants.VALUE_FONT_STYLE_NORMAL);
				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE,
						IReportConstants.VALUE_FONT_SIZE_LARGER);
				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT,
						IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
				tabularData.addStyle(IReportConstants.BORDER, "No");

				report.setGrandTotalTemplate(tabularData);
				report.setGroupByTotalTemplate(tabularData);
				//
				Grade = CheckIfNull(report.getParameterValue("Grade"));
				Scale = CheckIfNull(report.getParameterValue("Scale"));
				Designation = CheckIfNull(report
						.getParameterValue("Designation"));
				month = CheckIfNull(report.getParameterValue("Month"));
				year = CheckIfNull(report.getParameterValue("Year"));
				ReportType = CheckIfNull(report
						.getParameterValue("Report Type"));
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				java.util.Date startMonthDate = cal.getTime();
				String startDate = sdfObj.format(startMonthDate);

				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);

				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				cal.set(Calendar.DAY_OF_MONTH, totalDays);

				java.util.Date endMonthDate = cal.getTime();

				String endDate = sdfObj.format(endMonthDate);
				boolean flag = true;
				Map sessionKeys = (Map) ((Map) criteria)
						.get(IReportConstants.SESSION_KEYS);

				Map requestAttributes = (Map) ((Map) criteria)
						.get(IReportConstants.REQUEST_ATTRIBUTES);
				ServiceLocator serviceLocator = (ServiceLocator) requestAttributes
						.get("serviceLocator");

				SessionFactory sessionFactory = serviceLocator
						.getSessionFactory();
				Session session = sessionFactory.getCurrentSession();

				Session hibSession = sessionFactory.getCurrentSession();

				String demandNo = "";// CheckIfNull(report.getParameterValue("demandNo"));
				// (String)requestAttributes.get("demandNo");
				String mjrHead = "";// CheckIfNull(report.getParameterValue("mjrHead"));
				String subMjrHead = "";// CheckIfNull(report.getParameterValue("subMjrHead"));
				String mnrHead = "";// CheckIfNull(report.getParameterValue("mnrHead"));
				String subHead = CheckIfNull(report
						.getParameterValue("subHead"));
				// String dtlHead =
				// CheckIfNull(report.getParameterValue("dtlHead"));
				String subheadflag = subHead;
				logger.info("Head Details are :- demandNo = " + demandNo
						+ " Major Head is = " + mjrHead
						+ " Sub Major Head is = " + subMjrHead
						+ " Minor Head is = " + mnrHead + " Sub Head is = "
						+ subHead);

				String subHeadId = "";
				if (subHead != null && !subHead.equals("")
						&& !subHead.equals("-1")) {

					StringTokenizer st = new StringTokenizer(subHead, "~");
					int i = 0;
					while (st.hasMoreTokens()) {
						if (i == 0)
							demandNo = st.nextToken();
						else if (i == 1)
							mjrHead = st.nextToken();
						else if (i == 2)
							subMjrHead = st.nextToken();
						else if (i == 3)
							mnrHead = st.nextToken();
						else
							subHead = st.nextToken();
						i++;
					}
					if (subheadflag.indexOf("~") <= 0) {
						demandNo = CheckIfNull(report
								.getParameterValue("demandNo"));
						mjrHead = CheckIfNull(report
								.getParameterValue("mjrHead"));
						subMjrHead = CheckIfNull(report
								.getParameterValue("subMjrHead"));
						mnrHead = CheckIfNull(report
								.getParameterValue("mnrHead"));
						logger
								.info("  sdfhdfgthfr Head Details are :- demandNo = "
										+ demandNo
										+ " Major Head is = "
										+ mjrHead
										+ " Sub Major Head is = "
										+ subMjrHead
										+ " Minor Head is = "
										+ mnrHead + " Sub Head is = " + subHead);
					}
					logger
							.info("        sdgdfhgfHead Details are :- demandNo = "
									+ demandNo
									+ " Major Head is = "
									+ mjrHead
									+ " Sub Major Head is = "
									+ subMjrHead
									+ " Minor Head is = "
									+ mnrHead
									+ " Sub Head is = " + subHead);
					GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(
							SgvaBudsubhdMst.class);
					ServiceLocator serv = (ServiceLocator) requestAttributes
							.get("serviceLocator");
					genDAO.setSessionFactory(serv.getSessionFactory());
					CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(
							CmnLanguageMst.class, serv.getSessionFactory());
					CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl
							.getLangVOByShortName(langName);
					long langId = cmnLanguageMst.getLangId();
					PayBillDAOImpl payDAO = new PayBillDAOImpl(
							HrPayPaybill.class, serv.getSessionFactory());
					SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(
							startMonthDate, langId);

					String cols[] = { "demandCode", "budmjrhdCode",
							"budsubmjrhdCode", "budminhdCode", "budsubhdCode",
							"langId", "finYrId" };// ,"finYrId"
					Object vals[] = { demandNo, mjrHead, subMjrHead, mnrHead,
							subHead, langName, finYrMst.getFinYearId() };// ,String.valueOf(finYrMst.getFinYearId())
					List<SgvaBudsubhdMst> subHeadList = genDAO
							.getListByColumnAndValue(cols, vals);
					month = CheckIfNull(report.getParameterValue("Month"));
					year = CheckIfNull(report.getParameterValue("Year"));

					logger.info("*******************finYr*************"
							+ finYrMst.getFinYearId());
					SgvaBudsubhdMst subHeadObj = subHeadList.get(0);
					subHeadId = String.valueOf(subHeadObj.getBudsubhdId());
					logger.info("Paybill Subhead ID from following details is "
							+ subHeadId + " from size " + subHeadList.size());
				}
				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);
				report.setReportName(DeptName
						+ System.getProperty("line.separator")
						+ "Schedule of Total Deductions for the month of  "
						+ Month + ". " + year
						+ System.getProperty("line.separator")
						+ System.getProperty("line.separator") + "Bill No:"
						+ BillNo);
				// Added By Mrugesh
				String deducType = null;
				if (report.getReportCode().equals("30"))
					deducType = constantsBundle.getString("miscellaneous");

				if (report.getReportCode().equals("31"))
					deducType = constantsBundle.getString("insurence");

				if (report.getReportCode().equals("32"))
					deducType = constantsBundle.getString("profTax");

				if (report.getReportCode().equals("33"))
					deducType = constantsBundle.getString("deduction");

				if (report.getReportCode().equals("34"))
					deducType = constantsBundle.getString("incomeTax");

				if (deducType != null && deducType != "")
					ReportType = deducType;
				// Ended
				if (!ReportType.equals("") && !ReportType.equals("-1")) {
					if (ReportType.equals("Misc")) {
						report
								.setReportName(DeptName
										+ System.getProperty("line.separator")
										+ "Schedule of MISCELLANEOUS Recovery for the month of  "
										+ Month + ". " + year
										+ System.getProperty("line.separator")
										+ System.getProperty("line.separator")
										+ "Bill No:" + BillNo);
					} else if (ReportType.equals("Insurance")) {

						logger.info(Grade + "equals(" + AISGradeCode);
						if (Long.parseLong(Grade) == AISGradeCode) {
							report
									.setReportName(DeptName
											+ System
													.getProperty("line.separator")
											+ "Statement showing the Deductions of A.I.S. officer's Group Insurance Scheme for the month of  "
											+ Month
											+ ". "
											+ year
											+ System
													.getProperty("line.separator")
											+ System
													.getProperty("line.separator")
											+ "Bill No:" + BillNo);
						} else {
							report
									.setReportName(DeptName
											+ System
													.getProperty("line.separator")
											+ "Schedule of Insurance Deductions for the month of  "
											+ Month
											+ ". "
											+ year
											+ System
													.getProperty("line.separator")
											+ System
													.getProperty("line.separator")
											+ "Bill No:" + BillNo);
						}

					} else if (ReportType.equals("PT")) {
						report
								.setReportName(DeptName
										+ System.getProperty("line.separator")
										+ "Schedule of PROFESSIONAL TAX Deductions for the month of  "
										+ Month + ". " + year
										+ System.getProperty("line.separator")
										+ System.getProperty("line.separator")
										+ "Bill No:" + BillNo);
					} else if (ReportType.equals("Deduction")) {
						report.setReportName(DeptName
								+ System.getProperty("line.separator")
								+ "CHECKING List for the month of " + Month
								+ ". " + year
								+ System.getProperty("line.separator")
								+ "****Not to be attached with paybill****"
								+ System.getProperty("line.separator")
								+ System.getProperty("line.separator")
								+ "Bill No:" + BillNo);
					} else if (ReportType.equals("IT")) {
						report
								.setReportName(DeptName
										+ System.getProperty("line.separator")
										+ "Schedule of Income TAX Deductions for the month of  "
										+ Month + ". " + year
										+ System.getProperty("line.separator")
										+ "TAN:"
										+ System.getProperty("line.separator")
										+ System.getProperty("line.separator")
										+ "Bill No:" + BillNo);

					}
				}
				ReportColumnVO[] rptCol = report.getReportColumns();
				ReportParameterVO[] parVO = report.getParameters();
				ReportColumnVO clms[] = report.getLeafColumns();

				int x = 0;
				int y = 0;
				x = clms.length;
				// logger.info("column length is=====>"+clms.length);
				y = x;
				int o = 0;
				/*
				 * for(int col=0;col<x;col++) {
				 * if(clms[col].getHidden().equalsIgnoreCase("y")) o++; y--;
				 * //logger.info(clms[col]+"************inside************************************************"+col+"hide
				 * column list===>"+o); }
				 */

				ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
				// logger.info(y+"**********"+rptCol2.length);
				int colspan = rptCol2.length;
				for (int t = 0; t < rptCol2.length; t++) {

					// logger.info(t+"**********"+rptCol2[t].getColumnId()+"*****"+rptCol2[t].getHidden());

					if (!ReportType.equals("") && !ReportType.equals("-1")) {
						if (ReportType.equals("Misc")) {
							if (rptCol2[t].getColumnId() == 13
									|| rptCol2[t].getColumnId() == 1
									|| rptCol2[t].getColumnId() == 14
									|| rptCol2[t].getColumnId() == 15
									|| rptCol2[t].getColumnId() == 16
									|| rptCol2[t].getColumnId() == 17)
								colspan--;
						} else if (ReportType.equals("Insurance")) {

							if (rptCol2[t].getColumnId() == 13
									|| rptCol2[t].getColumnId() == 1
									|| rptCol2[t].getColumnId() == 14
									|| rptCol2[t].getColumnId() == 15
									|| rptCol2[t].getColumnId() == 18
									|| rptCol2[t].getColumnId() == 19)
								colspan--;
						} else if (ReportType.equals("PT")) {
							if (rptCol2[t].getColumnId() == 13
									|| rptCol2[t].getColumnId() == 21
									|| rptCol2[t].getColumnId() == 22
									|| rptCol2[t].getColumnId() == 1
									|| rptCol2[t].getColumnId() == 14
									|| rptCol2[t].getColumnId() == 16
									|| rptCol2[t].getColumnId() == 17
									|| rptCol2[t].getColumnId() == 18
									|| rptCol2[t].getColumnId() == 19)
								colspan--;
						} else if (ReportType.equals("Deduction")) {
							if (rptCol2[t].getColumnId() == 13
									|| rptCol2[t].getColumnId() == 1
									|| rptCol2[t].getColumnId() == 15
									|| rptCol2[t].getColumnId() == 14
									|| rptCol2[t].getColumnId() == 16
									|| rptCol2[t].getColumnId() == 17
									|| rptCol2[t].getColumnId() == 18
									|| rptCol2[t].getColumnId() == 19)
								colspan--;
						} else if (ReportType.equals("IT")) {
							if (rptCol2[t].getColumnId() == 5
									|| rptCol2[t].getColumnId() == 12
									|| rptCol2[t].getColumnId() == 23
									|| rptCol2[t].getColumnId() == 13
									|| rptCol2[t].getColumnId() == 14
									|| rptCol2[t].getColumnId() == 15
									|| rptCol2[t].getColumnId() == 16
									|| rptCol2[t].getColumnId() == 17
									|| rptCol2[t].getColumnId() == 18
									|| rptCol2[t].getColumnId() == 19)
								colspan--;

						}

					}

				}

				// SortingHelper Helper = new SortingHelper(DataList);
				if (!ReportType.equals("") && !ReportType.equals("-1")) {
					// logger.info("the 2nd column value on start
					// if===>"+rptCol[1].getHidden());
					if (ReportType.equals("Misc")) {
						rptCol[12].setHidden("y");
						rptCol[13].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[1].setHidden("y");

					} else if (ReportType.equals("Insurance")) {
						rptCol[12].setHidden("y");
						rptCol[13].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[17].setHidden("y");
						rptCol[1].setHidden("y");
						if (Long.parseLong(Grade) == AISGradeCode) {
							rptCol[15].setColumnHeader("Month");
							rptCol[16].setColumnHeader("Amount of Dedu.");
							rptCol[19]
									.setColumnHeader("Salary Budget Head Vr. No./Date");
							rptCol[20]
									.setColumnHeader("Accounting Budget Head");
							rptCol[21].setColumnHeader("Remarks");
						}

					} else if (ReportType.equals("PT")) {
						rptCol[12].setHidden("y");
						rptCol[13].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[17].setHidden("y");
						rptCol[20].setHidden("y");
						rptCol[21].setHidden("y");
						rptCol[1].setHidden("y");

					} else if (ReportType.equals("Deduction")) {
						rptCol[13].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[17].setHidden("y");
						rptCol[12].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[1].setHidden("y");

					} else if (ReportType.equals("IT")) {
						// rptCol[13].setHidden("y");
						rptCol[5].setHidden("y");
						rptCol[14].setHidden("y");
						rptCol[18].setHidden("y");
						rptCol[15].setHidden("y");
						rptCol[16].setHidden("y");
						rptCol[17].setHidden("y");
						rptCol[19].setHidden("y");
						rptCol[20].setHidden("y");
						rptCol[21].setHidden("y");
						rptCol[22].setHidden("y");
						rptCol[23].setHidden("n");
						// rptCol[24].setHidden("n");

					}
					// logger.info("the 2nd column value on end
					// if===>"+rptCol[1].getHidden());

				}
				// logger.info("the 2nd column value on end
				// if===>"+rptCol[1].getHidden());

				int totallength = rptCol.length;// 23
				int collength = rptCol.length;// 23
				/*
				 * for(int col=0;col<totallength;col++) {
				 * if(rptCol[col].getHidden().equalsIgnoreCase("y"))
				 * collength--;
				 * logger.info(collength+"************inside************************************************"+totallength); }
				 */
				// logger.info(collength+"************************************************************"+totallength);

				String query = " select";
				query += "  dtl.hrEisEmpMst.orgEmpMst.empId ,";
				// query+="dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName
				// ,";
				// query+="dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId
				// ,dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";
				query += " pst.orgDesignationMst.dsgnShrtName , ";
				query += " pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";

				query += " dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
				query += " dtl.otherCurrentBasic ,";
				// query+="dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt || '-' ||
				// dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt || '-'
				// ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt ,";
				query += "  scale.scaleStartAmt || '-' || scale.scaleIncrAmt || '-' ||scale.scaleEndAmt || '-' ||scale.scaleHigherIncrAmt || '-' ||scale.scaleHigherEndAmt, ";
				query += " sum(pay.totalDed+pay.adv7057+pay.adv7058) ,";
				query += " sum(pay.netTotal) , ";
				query += " sum(pay.grossAmt+pay.adv7057+pay.adv7058) , ";
				query += " sum(pay.it),sum(pay.deduc9550), ";
				query += " sum(pay.deduc9570),sum(pay.deduc9910),sum(pay.deduc9581),sum(pay.deduc9582),sum(pay.deduc9583),sum(pay.deduc9584),(select max(m.reason) from HrMiscRecoverDtls m where  m.hrEisEmpMst.orgEmpMst.empId=pay.hrEisEmpMst.orgEmpMst.empId and m.recoverDate>='"
						+ startDate
						+ "' and m.recoverDate<='"
						+ endDate
						+ "' group by pay.hrEisEmpMst.orgEmpMst.empId  ),sum(pay.surcharge) ";
				if (ReportType.equals("IT")) {
					query += "  ,proof.proofNum ";
				}
				if (isBillDefined)// this will always be at last so that no
									// need to change the sequence if new column
									// comes
				{
					query += "  ,psrmpg.psrId ";
				}
				query += " from HrPayPaybill pay, ";
				query += " HrEisOtherDtls as dtl ";
				query += " left outer join dtl.HrEisSgdMpg as sgd ";
				query += " left outer join sgd.HrEisGdMpg as gd ";
				query += "left outer join sgd.HrEisScaleMst scale, ";

				query += " OrgUserpostRlt           USRPST, ";
				query += " HrPayOrderHeadMpg ORDHD, ";
				query += " HrPayOrderHeadPostMpg ORDPST, ";
				query += " OrgPostDetailsRlt pst ";
				if (ReportType.equals("IT")) {
					query += " ,HrEisProofDtl proof ";
				}
				if (isBillDefined) {
					query += "  ,HrPayPsrPostMpg psrmpg ";
				}
				query += " where  ";
				if (isBillDefined) {
					query += "  psrmpg.postId=pay.orgPostMst.postId and ";
				}
				if (ReportType.equals("IT")) {
					query += "  proof.orgPostMstByUserId=dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId and";
					query += " pay.orgPostMst.postId = ORDPST.postId and ";
				}

				query += " pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId ";

				query += " and ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where pay.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )";
				query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
				query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
				query += " and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
				query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
				// query += " and USRPST.activateFlag=1 ";

				query += "and  (USRPST.endDate is null or ";
				query += "  (USRPST.endDate>='" + startDate
						+ "' and USRPST.startDate<='" + endDate + "'      ))";
				query += "  and pay.approveFlag in (0,1) ";

				if (!empid.equals("") && !empid.equals("-1"))
					query += "and dtl.hrEisEmpMst.empId = '" + empid + "'";

				// Added by Akshay
				if (!lname.equals("") && !lname.equals(" ")) {
					query += ("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"
							+ lname.toLowerCase() + "%'");
				}

				if (!fname.equals("") && !fname.equals(" ")) {
					query += ("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"
							+ fname.toLowerCase() + "%'");
				}
				// Ended by Akshay
				if (subHeadId != null && !subHeadId.equals("")
						&& !subHeadId.equals("-1")) {
					query += ("  and ORDHD.subheadId  = '" + subHeadId + "'");
				}

				if (!Grade.equals("") && !Grade.equals("-1"))
					query += "and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  = '"
							+ Grade + "'";
				// query+="and dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId =
				// '"+Grade+"'";

				if (!Scale.equals("") && !Scale.equals("-1"))
					query += "and dtl.HrEisSgdMpg.HrEisScaleMst.scaleId = '"
							+ Scale + "'";

				if (!Designation.equals("") && !Designation.equals("-1"))
					query += "and pst.orgDesignationMst.dsgnId = '"
							+ Designation + "'";
				// query+="and
				// dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId =
				// '"+Designation+"'";

				if (!Department.equals("") && !Department.equals("-1"))
					query += " and pst.cmnLocationMst.locId=" + Department
							+ "  ";
				if (isBillDefined && !BillNo.equals("")) {

					query += "  and  pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "
							+ BillNo + " ) )  and ";
					query += "   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="
							+ BillNo
							+ " )  and pst.orgPostMst.postId = pay.orgPostMst.postId  ";
				} else {
					if (subheadCode != null && !subheadCode.equals("")
							&& !subheadCode.equals("-1")) {
						query += "  and  ORDHD.subheadId =" + subheadCode + " ";
					}
					if (classIds != null && !classIds.equals("")
							&& !classIds.equals("-1")) {
						query += "  and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("
								+ classIds + ")";
					}
					if (desgnIds != null && !desgnIds.equals("")
							&& !desgnIds.equals("-1")) {
						query += "  and pst.orgDesignationMst.dsgnId in ("
								+ desgnIds + ") ";
					}

					if (portType != null && !portType.equals("")
							&& !portType.equals("-1")) {
						query += "  and pst.orgPostMst.postTypeLookupId in ("
								+ portType + ") ";
					}
				}
				if (!month.equals("") && !month.equals("-1"))
					query += " and pay.month='" + month + "'";

				if (!year.equals("") && !year.equals("-1"))
					query += "and pay.year= '" + year + "'";

				if (!ReportType.equals("") && !ReportType.equals("-1")) {
					if (ReportType.equals("Misc")) {
						query += "and pay.deduc9910!=0 ";
					} else if (ReportType.equals("Insurance")) {
						query += " and (((pay.deduc9583!=0 and pay.deduc9584!=0)) ";
						query += " or ((pay.deduc9581!=0 and pay.deduc9582!=0)) )";
					} else if (ReportType.equals("PT")) {
						query += " and pay.deduc9570!=0 ";
					} else if (ReportType.equals("IT")) {
						query += " and pay.it!=0 ";
						query += " and pay.orgPostMst.postId = ORDPST.postId ";
					}

				}

				query += " group by  ";
				if (isBillDefined) {
					query += "  psrmpg.psrId, ";
				}
				if (ReportType.equals("IT")) {
					query += "  proof.proofNum, ";
				}
				query += " pay.hrEisEmpMst.orgEmpMst.empId,pay.hrEisEmpMst.empId,dtl.hrEisEmpMst.orgEmpMst.empId,   ";
				// query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId, ";
				// query+="
				// dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName, ";
				// query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";

				query += " pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, ";
				query += " pst.orgDesignationMst.dsgnShrtName, ";
				query += " pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";

				query += "dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
				query += " dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
				query += " dtl.hrEisEmpMst.orgEmpMst.empLname,  ";

				query += " scale.scaleStartAmt,scale.scaleHigherIncrAmt,scale.scaleHigherEndAmt,  ";
				query += " scale.scaleIncrAmt,  ";
				query += " scale.scaleEndAmt,dtl.phyChallenged,dtl.otherCurrentBasic  ";

				// query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt, ";
				// query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt, ";
				// query+="
				// dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt,dtl.otherCurrentBasic
				// ";
				if (isBillDefined) {
					query += " order by  psrmpg.psrId ";
				} else
					query += " order by pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.orgEmpMst.empId ";
				logger.info("***Query for Deduction details**" + query);

				Query sqlQuery = hibSession.createQuery(query);
				ArrayList dataList = new ArrayList();
				List RowList = sqlQuery.list();
				logger.info("*************************" + RowList.size());
				int cnt = 1;
				long empId = 0;
				double I_TaxTotal = 0;
				double P_TaxTotal = 0;
				double I_FTotal = 0;
				double S_FTotal = 0;
				double Misc_RecoveryTotal = 0;
				double Gross_AmountTotal = 0;
				double Total_DeductionTotal = 0;
				double Net_AmountTotal = 0;
				Iterator itr = RowList.iterator();

				double ITTotal = 0;
				double surchargeTotal = 0;
				double PTTotal = 0;
				double IFTotal = 0;
				double SFTotal = 0;
				double miscRecTotal = 0;
				double grossAmtTotal = 0;
				double totaldedTotal = 0;
				double nettotalTotal = 0;

				while (itr.hasNext()) {
					Object[] rowList = (Object[]) itr.next();
					long EmpId = Long
							.parseLong((rowList[0] != null ? rowList[0]
									.toString() : "").toString());
					String designation = (String) (rowList[1] != null ? rowList[1]
							: "");
					long gradeId = Long
							.parseLong((rowList[2] != null ? rowList[2]
									.toString() : "").toString());
					String Class = (String) (rowList[3] != null ? rowList[3]
							: "");
					String Name = (String) (rowList[4] != null ? rowList[4]
							: "");
					long basic = Long
							.parseLong((rowList[5] != null ? rowList[5] : "")
									.toString());
					String scale = (rowList[6] != null ? rowList[6] : "")
							.toString();
					double totalded = Double
							.parseDouble((rowList[7] != null ? rowList[7] : "")
									.toString());
					double nettotal = Double
							.parseDouble((rowList[8] != null ? rowList[8] : "")
									.toString());
					double grossAmt = Double
							.parseDouble((rowList[9] != null ? rowList[9] : "")
									.toString());
					double IT = Double
							.parseDouble((rowList[10] != null ? rowList[10]
									: "").toString());
					double PF = Double
							.parseDouble((rowList[11] != null ? rowList[11]
									: "").toString());
					double PT = Double
							.parseDouble((rowList[12] != null ? rowList[12]
									: "").toString());
					double miscRec = Double
							.parseDouble((rowList[13] != null ? rowList[13]
									: "").toString());
					double SISIF = Double
							.parseDouble((rowList[14] != null ? rowList[14]
									: "").toString());
					double SISSF = Double
							.parseDouble((rowList[15] != null ? rowList[15]
									: "").toString());
					double AISIF = Double
							.parseDouble((rowList[16] != null ? rowList[16]
									: "").toString());
					double AISSF = Double
							.parseDouble((rowList[17] != null ? rowList[17]
									: "").toString());
					String reason = (rowList[18] != null ? rowList[18] : "")
							.toString();
					double surcharge = Double
							.parseDouble((rowList[19] != null ? rowList[19]
									: "").toString());
					String panNo = "";
					double itTotal = 0;
					// double PTTotal=0;
					if (Long.parseLong(Grade) == AISGradeCode) {
						rptCol[15].setColumnHeader("Month");
						rptCol[16].setColumnHeader("Amount of Dedu.");
						rptCol[19]
								.setColumnHeader("Salary Budget Head Vr. No./Date");
						rptCol[20].setColumnHeader("Accounting Budget Head");
						rptCol[21].setColumnHeader("Remarks");
					}

					if (ReportType.equals("IT")) {
						panNo = (rowList[20] != null ? rowList[20] : "")
								.toString();
						nettotal = surcharge + IT;
					} else if (ReportType.equals("PT")) {
						// PTTotal+=PT;
					}

					// String
					// panNo=(rowList[20]!=null?rowList[20]:"").toString();

					ArrayList row = new ArrayList();
					/*
					 * if(cnt%25==0) { row.add(new PageBreak());
					 * row.add("Data");
					 *  }
					 */
					// else
					{
						row.add(cnt);
						row.add(panNo);
						row.add(Name);
						row.add(designation);
						// row.add(gradeId);
						row.add(Class);
						// for fixed pay
						if (scale.equals("---0-0") || scale.equals("----")) {
							scale = ((Long) basic).toString();
						}
						scale = scale.replaceAll("-0-0", "");
						row.add(scale);
						row.add(panNo);
						row.add("");
						row.add("");
						row.add("");
						row.add("");
						row.add("");
						row.add(Math.round(IT));
						row.add(Math.round(surcharge));
						if (ReportType.equals("PT")) {
							row.add(Math.round(grossAmt));
						} else
							row.add(Math.round(PT));
						// row.add(PF);
						if (gradeId != AISGradeCode) {
							row.add(Math.round(SISIF));
							row.add(Math.round(SISSF));
						} else {

							row.add(month + "/" + year);
							row.add(Math.round(AISIF + AISSF));
						}
						row.add(Math.round(miscRec));
						row.add(reason);
						if (Long.parseLong(Grade) == AISGradeCode) {
							row.add("A.I.S.");
							row.add("Insurance Scheme");
						} else {
							if (ReportType.equals("PT")) {
								row.add(Math.round(PT));
								rptCol[14].setColumnHeader("Gross Amount");
								rptCol[19].setColumnHeader("P. Tax");
							} else
								row.add(Math.round(grossAmt));
							row.add(Math.round(totalded));

						}

						if (ReportType.equals("IT")) {
							row.add("");
							row.add("");
							row.add(Math.round(nettotal));
						} else {
							if (Long.parseLong(Grade) == AISGradeCode) {
								row.add("");
							} else {
								row.add(Math.round(nettotal));

							}
						}

						row.add((cnt - 1) / 12 + 1);
					}
					DataList.add(row);
					cnt++;

					if (cnt % 25 == 0) {
						row = new ArrayList();
						row.add(new PageBreak());
						row.add("Data");
						DataList.add(row);
					}

					ITTotal += IT;
					surchargeTotal += surcharge;
					PTTotal += PT;
					IFTotal += SISIF + AISIF;
					SFTotal += SISSF + AISSF;
					miscRecTotal += miscRec;
					grossAmtTotal += grossAmt;
					totaldedTotal += totalded;
					nettotalTotal += nettotal;

				}

				ArrayList row = new ArrayList();
				row.add("");
				row.add("");
				row.add("");
				row.add("Total");
				row.add("");
				row.add("");
				row.add("");
				row.add("");
				row.add("");
				row.add("");
				row.add("");
				row.add("");

				if (Long.parseLong(Grade) == AISGradeCode) {
					row.add("");// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(ITTotal)));
					row.add("");// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(surchargeTotal)));
					row.add("");// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(PTTotal)));
					row.add("");// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(IFTotal)));
					row.add(Math.round(IFTotal + SFTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(SFTotal)));
					row.add("");// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(miscRecTotal)));
				} else {
					row.add(Math.round(ITTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(ITTotal)));
					row.add(Math.round(surchargeTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(surchargeTotal)));
					if (ReportType.equals("PT"))
						row.add("");// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(PTTotal)));
					else
						row.add(Math.round(PTTotal));
					row.add(Math.round(IFTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(IFTotal)));
					row.add(Math.round(SFTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(SFTotal)));
					row.add(Math.round(miscRecTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(miscRecTotal)));

				}
				row.add("");
				if (!ReportType.equals("PT")) {
					if (Long.parseLong(Grade) == AISGradeCode) {
						row.add("");
					} else {
						row.add(Math.round(grossAmtTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(grossAmtTotal)));
					}

				} else {
					if (ReportType.equals("PT"))
						row.add(Math.round(PTTotal));
					else
						row.add("");
				}

				if (Long.parseLong(Grade) == AISGradeCode) {
					row.add("");
				} else {
					row.add(Math.round(totaldedTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(totaldedTotal)));
				}

				if (ReportType.equals("IT")) {
					row.add("");
					row.add("");
					row.add(Math.round(nettotalTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(nettotalTotal)));
				} else {
					if (Long.parseLong(Grade) == AISGradeCode) {
						row.add("");
					} else {
						row.add(Math.round(nettotalTotal));// +System.getProperty("line.separator")+ConvertNumbersToWord.convert(Math.round(nettotalTotal)));
					}
				}

				row.add((cnt - 1) / 12 + 1);

				DataList.add(row);
				ArrayList row1 = new ArrayList();

				StyleVO[] centerboldStyleVO = new StyleVO[2];
				centerboldStyleVO[0] = new StyleVO();
				centerboldStyleVO[0]
						.setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO[0]
						.setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				centerboldStyleVO[1] = new StyleVO();
				centerboldStyleVO[1]
						.setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO[1].setStyleValue("Left");
				StyledData dataStyle2 = new StyledData();
				dataStyle2.setStyles(centerboldStyleVO);
				if (ReportType.equals("IT"))
					dataStyle2.setData("Rupees  "
							+ ConvertNumbersToWord.convert(Math
									.round(nettotalTotal)) + " only.");
				else if (ReportType.equals("PT")) {
					dataStyle2.setData("Rupees  "
							+ ConvertNumbersToWord.convert(Math.round(PTTotal))
							+ " only.");
				} else {
					if (Long.parseLong(Grade) == AISGradeCode) {
						dataStyle2.setData("Rupees  "
								+ ConvertNumbersToWord.convert(Math
										.round(IFTotal + SFTotal)) + " only.");
					} else {
						dataStyle2.setData("Rupees  "
								+ ConvertNumbersToWord.convert(Math
										.round(nettotalTotal)) + " only.");
					}

				}

				dataStyle2.setColspan(colspan);
				row1.add(dataStyle2);
				if (ReportType.equals("IT")) {
					for (int c = 0; c < 23; c++)
						row1.add("");
				} else {
					for (int c = 0; c < (totallength - colspan); c++)
						row1.add("");
				}

				DataList.add(row1);
				report.setReportColumns(rptCol);
				report.initializeDynamicTreeModel();
				report.initializeTreeModel();
				logger.info("**********************" + DataList.size());
				return DataList;
			}

		} catch (Exception e) {
			logger.error("Error in ResourceMoniteringDAO" + e.getMessage());
			logger.error("Printing StackTrace");
			logger.error("Error is: "+ e.getMessage());
		} finally {

		}
		return DataList;
	}

	// end by manoj for Festival and foodgrain advance report
	// by manoj for Non Govt Deduction report

	public ReportVO getUserReportVO(ReportVO report, Object criteria)
			throws ReportException {

		logger.info("***********Inside User Report VO *********************");
		Hashtable sessionKeys = (Hashtable) ((Hashtable) criteria)
				.get(IReportConstants.SESSION_KEYS);
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String date = fmt.format(today);
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy");
		String yr = fmt1.format(today);
		fmt1 = new SimpleDateFormat("MM");

		Map requestKeys = (Map) ((Map) criteria)
				.get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map) requestKeys.get("serviceMap");
		Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");
		CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap
				.get("locationVO");
		String locationName = locationVO.getLocName();
		long locationId = locationVO.getLocId();
		String month = fmt1.format(today);

		if (month.charAt(0) == '0') {

			month = month.substring(1, 2);

		}

		if (report.getReportCode().equals("3")
				|| report.getReportCode().equals("6")
				|| report.getReportCode().equals("7")
				|| report.getReportCode().equals("8")
				|| report.getReportCode().equals("10")
				|| report.getReportCode().equals("11")
				|| report.getReportCode().equals("13")) {
			report.setParameterValue("Year", yr);
			report.setParameterValue("Month", month);
		}
		if (report.getReportCode().equals("4")
				|| report.getReportCode().equals("5")
				|| report.getReportCode().equals("9")) {
			report.setParameterValue("Year", yr);
			report.setParameterValue("Month", month);
		}
		/*
		 * if( report.getReportCode().equals("3") ||
		 * report.getReportCode().equals("4") ||
		 * report.getReportCode().equals("5") ||
		 * report.getReportCode().equals("6") ||
		 * report.getReportCode().equals("7") ||
		 * report.getReportCode().equals("8") ||
		 * report.getReportCode().equals("9") ||
		 * report.getReportCode().equals("10")||
		 * report.getReportCode().equals("11")||
		 * report.getReportCode().equals("13")) {
		 * report.setParameterValue("Department",locationId+""); }
		 */

		return report;
	}

}