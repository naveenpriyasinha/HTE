/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Aug 12, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.report;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.jfree.util.StringUtils;

import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.dao.reports.ReportsDAO;
import com.tcs.sgv.common.dao.reports.ReportsDAOImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.util.EnglishDecimalFormat;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.dao.PostEmpContriDAO;
import com.tcs.sgv.dcps.dao.PostEmpContriDAOImpl;
import com.tcs.sgv.dcps.dao.SancBudgetDAO;
import com.tcs.sgv.dcps.dao.SancBudgetDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.PostEmpContri;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Feb 9, 2011
 */
public class SancBudgetReport extends DefaultReportDataFinder {

	private static final Logger gLogger = Logger
			.getLogger(DCPSEmployeeDetailReport.class);
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";

	Map requestAttributes = null;
	ServiceLocator serviceLocator = null;
	SessionFactory lObjSessionFactory = null;
	static ResourceBundle lBudConstantsBundle = ResourceBundle.getBundle(
			"resources/dcps/dcpsLabels", Locale.getDefault());

	public Collection findReportData(ReportVO report, Object criteria)
			throws ReportException {

		report.getLangId();

		report.getLocId();
		Connection con = null;

		criteria.getClass();

		Statement smt = null;
		ResultSet rs = null;
		TabularData td = null;
		TabularData td1 = null;
		TabularData td2 = null;
		TabularData td3 = null;
		TabularData td4 = null;
		TabularData td5 = null;

		ArrayList dataList = new ArrayList();
		ReportVO RptVo = null;
		ReportsDAO reportsDao = null;
		reportsDao = new ReportsDAOImpl();

		StyleVO[] normalFont = new StyleVO[2];
		normalFont[0] = new StyleVO();
		normalFont[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		normalFont[0].setStyleValue("8");
		normalFont[1] = new StyleVO();
		normalFont[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		normalFont[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);

		StyleVO[] smallItalic = new StyleVO[3];
		smallItalic[0] = new StyleVO();
		smallItalic[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		smallItalic[0].setStyleValue("6");
		smallItalic[1] = new StyleVO();
		smallItalic[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		smallItalic[1]
				.setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
		smallItalic[2] = new StyleVO();
		smallItalic[2].setStyleId(IReportConstants.STYLE_FONT_STYLE);
		smallItalic[2].setStyleValue(IReportConstants.VALUE_FONT_STYLE_ITALIC);

		StyleVO[] boldFontLeftAlign = new StyleVO[3];
		boldFontLeftAlign[0] = new StyleVO();
		boldFontLeftAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		boldFontLeftAlign[0].setStyleValue("8");
		boldFontLeftAlign[1] = new StyleVO();
		boldFontLeftAlign[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldFontLeftAlign[1]
				.setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
		boldFontLeftAlign[2] = new StyleVO();
		boldFontLeftAlign[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		boldFontLeftAlign[2]
				.setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);

		StyleVO[] boldFontRightAlign = new StyleVO[3];
		boldFontRightAlign[0] = new StyleVO();
		boldFontRightAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		boldFontRightAlign[0].setStyleValue("8");
		boldFontRightAlign[1] = new StyleVO();
		boldFontRightAlign[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldFontRightAlign[1]
				.setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
		boldFontRightAlign[2] = new StyleVO();
		boldFontRightAlign[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		boldFontRightAlign[2]
				.setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);

		StyleVO[] boldAndBigFont = new StyleVO[2];
		boldAndBigFont[0] = new StyleVO();
		boldAndBigFont[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		boldAndBigFont[0].setStyleValue("12");
		boldAndBigFont[1] = new StyleVO();
		boldAndBigFont[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldAndBigFont[1]
				.setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);

		StyleVO[] boldAndBigFontCenterAlign = new StyleVO[3];
		boldAndBigFontCenterAlign[0] = new StyleVO();
		boldAndBigFontCenterAlign[0]
				.setStyleId(IReportConstants.STYLE_FONT_SIZE);
		boldAndBigFontCenterAlign[0].setStyleValue("12");
		boldAndBigFontCenterAlign[1] = new StyleVO();
		boldAndBigFontCenterAlign[1]
				.setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldAndBigFontCenterAlign[1]
				.setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
		boldAndBigFontCenterAlign[2] = new StyleVO();
		boldAndBigFontCenterAlign[2]
				.setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		boldAndBigFontCenterAlign[2]
				.setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);

		StyleVO[] boldFontCenterAlign = new StyleVO[3];
		boldFontCenterAlign[0] = new StyleVO();
		boldFontCenterAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		boldFontCenterAlign[0].setStyleValue("8");
		boldFontCenterAlign[1] = new StyleVO();
		boldFontCenterAlign[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldFontCenterAlign[1]
				.setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
		boldFontCenterAlign[2] = new StyleVO();
		boldFontCenterAlign[2]
				.setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		boldFontCenterAlign[2]
				.setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);

		StyleVO[] normalFontCenterAlign = new StyleVO[2];
		normalFontCenterAlign[0] = new StyleVO();
		normalFontCenterAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		normalFontCenterAlign[0].setStyleValue("8");
		normalFontCenterAlign[1] = new StyleVO();
		normalFontCenterAlign[1]
				.setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		normalFontCenterAlign[1]
				.setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);

		StyleVO[] normalFontRightAlign = new StyleVO[2];
		normalFontRightAlign[0] = new StyleVO();
		normalFontRightAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		normalFontRightAlign[0].setStyleValue("8");
		normalFontRightAlign[1] = new StyleVO();
		normalFontRightAlign[1]
				.setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		normalFontRightAlign[1]
				.setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);

		StyleVO[] noBorder = new StyleVO[4];
		noBorder[0] = new StyleVO();
		noBorder[0].setStyleId(IReportConstants.BORDER);
		noBorder[0].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
		noBorder[1] = new StyleVO();
		noBorder[1].setStyleId(IReportConstants.ROWS_PER_PAGE);
		noBorder[1].setStyleValue("40");
		noBorder[2] = new StyleVO();
		noBorder[2].setStyleId(IReportConstants.NO_HEADER);
		noBorder[2].setStyleValue(IReportConstants.VALUE_YES);
		noBorder[3] = new StyleVO();
		noBorder[3].setStyleId(IReportConstants.NO_FOOTER);
		noBorder[3].setStyleValue(IReportConstants.VALUE_YES);

		StyleVO[] noBorderAndSelfClose = new StyleVO[5];
		noBorderAndSelfClose[0] = new StyleVO();
		noBorderAndSelfClose[0].setStyleId(IReportConstants.BORDER);
		noBorderAndSelfClose[0]
				.setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
		noBorderAndSelfClose[1] = new StyleVO();
		noBorderAndSelfClose[1].setStyleId(IReportConstants.ROWS_PER_PAGE);
		noBorderAndSelfClose[1].setStyleValue("70");
		noBorderAndSelfClose[2] = new StyleVO();
		noBorderAndSelfClose[2].setStyleId(IReportConstants.NO_HEADER);
		noBorderAndSelfClose[2].setStyleValue(IReportConstants.VALUE_YES);
		noBorderAndSelfClose[3] = new StyleVO();
		noBorderAndSelfClose[3].setStyleId(IReportConstants.NO_FOOTER);
		noBorderAndSelfClose[3].setStyleValue(IReportConstants.VALUE_YES);
		noBorderAndSelfClose[4] = new StyleVO();
		noBorderAndSelfClose[4].setStyleId(26);
		noBorderAndSelfClose[4].setStyleValue("JavaScript:self.close()");

		String lStrNewLine = StringUtils.getLineSeparator();

		try {

			requestAttributes = (Map) ((Map) criteria)
					.get(IReportConstants.REQUEST_ATTRIBUTES);
			serviceLocator = (ServiceLocator) requestAttributes
					.get("serviceLocator");
			lObjSessionFactory = serviceLocator.getSessionFactorySlave();

			Map requestAttributes = (Map) ((Map) criteria)
					.get(IReportConstants.REQUEST_ATTRIBUTES);
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes
					.get("serviceLocator");
			SessionFactory lObjSessionFactory = serviceLocator
					.getSessionFactorySlave();
			con = lObjSessionFactory.getCurrentSession().connection();
			smt = con.createStatement();
			Map sessionKeys = (Map) ((Map) criteria)
					.get(IReportConstants.SESSION_KEYS);
			Map loginDetail = (HashMap) sessionKeys.get("loginDetailsMap");

			loginDetail.get("locationId");

			new StringBuffer();
			if (report.getReportCode().equals("700032")) {

				report.setStyleList(noBorderAndSelfClose);

				String lStrPostEmpContriId = null;
				Long lLongPostEmpContriId = null;
				lStrPostEmpContriId = (String) report
						.getParameterValue("postEmpContriPkId");

				if (!"".equals(lStrPostEmpContriId)) {
					lLongPostEmpContriId = Long.valueOf(lStrPostEmpContriId);
				}

				PostEmpContriDAO objPostEmpContriDAO = new PostEmpContriDAOImpl(
						PostEmpContri.class, serviceLocator.getSessionFactory());
				SancBudgetDAO lObjSancBudgetDAO = new SancBudgetDAOImpl(null,
						serviceLocator.getSessionFactory());

				PostEmpContri lObjPostEmpContri = (PostEmpContri) objPostEmpContriDAO
						.read(lLongPostEmpContriId);

				ArrayList rowList = new ArrayList();

				ArrayList firstThreeRowsLeft = new ArrayList();

				rowList = new ArrayList();
				rowList.add(new StyledData("TreasuryCode", boldFontLeftAlign));
				rowList.add(new StyledData(": 7101", boldFontLeftAlign));
				firstThreeRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("DDO Code", boldFontLeftAlign));
				rowList.add(new StyledData(": 7101003272", boldFontLeftAlign));
				firstThreeRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Scheme Code", boldFontLeftAlign));
				rowList.add(new StyledData(": 27010642", boldFontLeftAlign));
				firstThreeRowsLeft.add(rowList);

				td = new TabularData(firstThreeRowsLeft);
				RptVo = reportsDao.getReport("700033", report.getLangId(),
						report.getLocId()); // A 2 column Report
				ReportColumnVO[] lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(20);
				lArrReportColumnVO[1].setColumnWidth(80);
				(td).setRelatedReport(RptVo);
				(td).setStyles(noBorder);
				rowList = new ArrayList();
				rowList.add(td);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("FORM M.T.R. 45-A",
						boldAndBigFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("(See Rule 406-A)",
						boldFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Simple Receipt",
						boldFontCenterAlign));
				dataList.add(rowList);

				ArrayList fourRowsInMiddle = new ArrayList();

				rowList = new ArrayList();
				rowList.add(new StyledData("Treasury Name", boldFontLeftAlign));
				rowList.add(new StyledData(": PAY & Accounts Office Mumbai",
						boldFontLeftAlign));
				rowList.add(new StyledData(" ", boldFontRightAlign));
				rowList.add(new StyledData(" ", boldFontLeftAlign));
				fourRowsInMiddle.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Token No", boldFontLeftAlign));
				rowList.add(new StyledData(": ", boldFontLeftAlign));
				rowList.add(new StyledData("Bill No", boldFontRightAlign));
				rowList
						.add(new StyledData(": "
								+ lObjPostEmpContri.getBillNo(),
								boldFontLeftAlign));
				fourRowsInMiddle.add(rowList);

				Date lDtCurrdate = SessionHelper.getCurDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String lStrCurrDate = null;
				if (lDtCurrdate != null) {
					lStrCurrDate = sdf.format(lDtCurrdate);
				}

				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
						serviceLocator.getSessionFactory());
				String lStrYearCode = lObjDcpsCommonDAO
						.getYearCodeForYearId(lObjPostEmpContri.getFinYear());
				String lStrMonth = lObjDcpsCommonDAO.getMonthForId(Long
						.valueOf(lObjPostEmpContri.getContriMonth()));

				rowList = new ArrayList();
				rowList.add(new StyledData("Voucher No", boldFontLeftAlign));
				rowList.add(new StyledData(": ", boldFontLeftAlign));
				rowList.add(new StyledData("Date", boldFontRightAlign));
				rowList.add(new StyledData(": " + lStrCurrDate,
						boldFontLeftAlign));
				fourRowsInMiddle.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Voucher Date", boldFontLeftAlign));
				rowList.add(new StyledData(": ", boldFontLeftAlign));
				rowList.add(new StyledData("Month/Year", boldFontRightAlign));
				rowList.add(new StyledData(": " + lStrMonth + space(2)
						+ lStrYearCode, boldFontLeftAlign));
				fourRowsInMiddle.add(rowList);

				td = new TabularData(fourRowsInMiddle);
				RptVo = reportsDao.getReport("700035", report.getLangId(),
						report.getLocId()); // A 4 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(20);
				lArrReportColumnVO[1].setColumnWidth(50);
				lArrReportColumnVO[2].setColumnWidth(15);
				lArrReportColumnVO[3].setColumnWidth(15);
				(td).setRelatedReport(RptVo);
				(td).setStyles(noBorder);
				rowList = new ArrayList();
				rowList.add(td);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("HEAD OF ACCOUNT",
						boldAndBigFontCenterAlign));
				dataList.add(rowList);

				ArrayList sevenRowsLeft = new ArrayList();

				rowList = new ArrayList();
				rowList.add(new StyledData("Administrative Department",
						normalFont));
				rowList.add(new StyledData(": Finance Department", normalFont));
				sevenRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Demand No", normalFont));
				rowList.add(new StyledData(": G-6", normalFont));
				sevenRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Major Head", normalFont));
				rowList.add(new StyledData(
						": 2071 Pension & Other Pensionary benefits",
						normalFont));
				sevenRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Minor Head", normalFont));
				rowList.add(new StyledData(": 01 Civil", normalFont));
				sevenRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Sub Head", normalFont));
				rowList
						.add(new StyledData(
								": 117 Government Contribution for Defined Contributory Pension SChemes",
								normalFont));
				sevenRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Detailed Head", normalFont));
				rowList.add(new StyledData(": (01) Government's Contribution",
						normalFont));
				sevenRowsLeft.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("", normalFont));
				rowList
						.add(new StyledData(
								": (01)(04) Pensionary Charges (20710642)",
								normalFont));
				sevenRowsLeft.add(rowList);

				td = new TabularData(sevenRowsLeft);
				RptVo = reportsDao.getReport("700033", report.getLangId(),
						report.getLocId()); // A 2 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(25);
				lArrReportColumnVO[1].setColumnWidth(75);
				(td).setRelatedReport(RptVo);
				(td).setStyles(noBorder);
				rowList = new ArrayList();
				rowList.add(td);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Object of expenditure (20710642) Payment for Professional and social service",
								normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Shri./Smt Deputy Director ,State Record Keeping Agency,Defined Contribution Pension Scheme for Month of June-2011",
								normalFont));
				dataList.add(rowList);

				Long lFloatBillAmount = lObjPostEmpContri.getBillAmount();
				String lStrBillAmountInWords = EnglishDecimalFormat
						.convertWithSpace(new BigDecimal(lFloatBillAmount));

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"(1) Total Amount :     -------------------------------------------------------------------------"
										+ space(55)
										+ "Rs. "
										+ lFloatBillAmount
										+ "/-", boldFontLeftAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("(In words: "
						+ lStrBillAmountInWords + " )", normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("(2) Less by Transfer Credit to -",
						boldFontLeftAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Major head 8342-Other Deposits 117 Defined Contribution",
								normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Pension Scheme for Government employees", normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"(01)(01) Defined Contribution Pension Scheme",
						normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Government's Contribution Tier-1",
						normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"(8342-209-9) 32-Contributions"
										+ space(10)
										+ "Amount:-  -------------------------------------------"
										+ space(30) + "Rs. " + lFloatBillAmount
										+ "/-", normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("(In words: "
						+ lStrBillAmountInWords + " )", normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"(3) Balance:- "
										+ space(3)
										+ "-------------------------------------------------------------------------"
										+ space(55) + "Rs. NIL",
								boldFontLeftAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Received the sum of Rupees in cash Rs. NIL",
						normalFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Sanctioned by State Record Keeping Agency,Defined Contribution Pension Scheme",
								normalFontCenterAlign));
				dataList.add(rowList);

				ArrayList threeRowsAndTwoColumnsInTheEnd = new ArrayList();

				Long lLongSancBudget = lObjSancBudgetDAO
						.getTotalBudget(lObjPostEmpContri.getFinYear());
				Long lLongExpenditure = objPostEmpContriDAO
						.getExpenditure(lObjPostEmpContri.getFinYear());
				Long lLongBalance = lLongSancBudget - lLongExpenditure;

				rowList = new ArrayList();
				rowList
						.add(new StyledData("Sanctioned Budget(Rs.)",
								normalFont));
				rowList.add(new StyledData(": " + lLongSancBudget + "/-"
						+ space(10) + "Amount (Plan/Non-Plan)",
						boldFontLeftAlign));
				threeRowsAndTwoColumnsInTheEnd.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Expenditure including this bill(Rs.)", normalFont));
				rowList.add(new StyledData(": " + lLongExpenditure + "/-",
						boldFontLeftAlign));
				threeRowsAndTwoColumnsInTheEnd.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Balance Grant(Rs.)", normalFont));
				rowList.add(new StyledData(": " + lLongBalance + "/-",
						boldFontLeftAlign));
				threeRowsAndTwoColumnsInTheEnd.add(rowList);

				td = new TabularData(threeRowsAndTwoColumnsInTheEnd);
				RptVo = reportsDao.getReport("700033", report.getLangId(),
						report.getLocId()); // A 2 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(30);
				lArrReportColumnVO[1].setColumnWidth(70);
				(td).setRelatedReport(RptVo);
				(td).setStyles(noBorder);
				rowList = new ArrayList();
				rowList.add(td);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"On Account : of Government's contribution under the Defined Contribution Pension Scheme",
								normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Sanctioned By : Govt. of Maharashtra, Finance Dept.,G.R.No DCPS 1007/18/Seva-4 Dated : 07/07/2007",
								normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Received Payment",
						normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------",
								normalFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"As per Sr.No. 18 of Government Resolution dated 07/07/2007, it is certified that the Governement's Contribution under the Defined Contribution Pension"
										+ lStrNewLine, normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Scheme is Rs " + lFloatBillAmount
						+ "/- " + "(Rs. in words " + lStrBillAmountInWords
						+ " only). The amount of " + lFloatBillAmount
						+ "/- Shown in this ", normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"bill tallies with this amount and this amount is correct/exact as per the records of this office."
										+ lStrNewLine + lStrNewLine, normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Signature & Designation",
						normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Deputy Director",
						normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("State Record Keeping Agency",
						normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Defined Contribution Pension Scheme",
						normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Maharashtra State,Mumbai",
						normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("FOR USE IN TREASURY",
						boldAndBigFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Pay Rs.: " + space(100)
						+ "In Word Rs.-", normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Date:", normalFont));
				dataList.add(rowList);

				// First Page completed

				rowList = new ArrayList();
				rowList.add(new PageBreak());
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("FORM M.T.R.6",
						boldAndBigFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("(See Rule 112)",
						boldFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("(Obverse)", boldFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData("Challan No.2", boldFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Challan of Cash paid into the Treasury/Sub-treasury/State/Reserve Bank of India at MUMBAI"
										+ lStrNewLine, boldFontCenterAlign));
				dataList.add(rowList);

				ArrayList threeColumnsInSecondPage = new ArrayList();

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Amount collected under Defined Contribution Pension Scheme",
								normalFont));
				rowList.add(new StyledData("", normalFont));
				rowList.add(new StyledData("", normalFont));
				threeColumnsInSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"which is to be deposited to M.H. 8342-Others Deposits",
								normalFont));
				rowList.add(new StyledData(lFloatBillAmount, normalFont));
				rowList.add(new StyledData("00", normalFont));
				threeColumnsInSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"117-Defined Contribution Pension Schemefor Government Employees",
								normalFont));
				rowList.add(new StyledData("", normalFont));
				rowList.add(new StyledData("", normalFont));
				threeColumnsInSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"(01)(01)Defined Contribution Pension Scheme,",
						normalFont));
				rowList.add(new StyledData("", normalFont));
				rowList.add(new StyledData("", normalFont));
				threeColumnsInSecondPage.add(rowList);

				Long lLongOrgId = lObjSancBudgetDAO
						.getOrgIdForYearId(lObjPostEmpContri.getFinYear());
				String lStrEmplrSchemeCode = lObjSancBudgetDAO
						.getSchemeCodeForOrgId(lLongOrgId);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Government's Contribution, Tier-I ("
								+ lStrEmplrSchemeCode + ")", normalFont));
				rowList.add(new StyledData("", normalFont));
				rowList.add(new StyledData("", normalFont));
				threeColumnsInSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Signature." + space(25) + "Total*:", normalFont));
				rowList.add(new StyledData(lFloatBillAmount, normalFont));
				rowList.add(new StyledData("00", normalFont));
				threeColumnsInSecondPage.add(rowList);

				td2 = new TabularData(threeColumnsInSecondPage);
				RptVo = reportsDao.getReport("700035", report.getLangId(),
						report.getLocId()); // A 4 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(60);
				lArrReportColumnVO[0]
						.setColumnHeader("Full Particulars of the remittance and the authority(if any)");
				lArrReportColumnVO[0]
						.setColumnName("Full Particulars of the remittance and the authority(if any)");
				lArrReportColumnVO[0].setColumnLevel(1);

				lArrReportColumnVO[3].setColumnWidth(40);
				lArrReportColumnVO[3].setColumnHeader("Amount");
				lArrReportColumnVO[3].setColumnName("Amount");
				lArrReportColumnVO[3].setColumnLevel(1);

				lArrReportColumnVO[1].setColumnWidth(20);
				lArrReportColumnVO[1].setParentColumnId(lArrReportColumnVO[3]
						.getColumnId());
				lArrReportColumnVO[1].setColumnHeader("Rs.");
				lArrReportColumnVO[1].setColumnName("Rs.");
				lArrReportColumnVO[1].setColumnLevel(2);

				lArrReportColumnVO[2].setColumnWidth(20);
				lArrReportColumnVO[2].setParentColumnId(lArrReportColumnVO[3]
						.getColumnId());
				lArrReportColumnVO[2].setColumnHeader("P.");
				lArrReportColumnVO[2].setColumnName("P.");
				lArrReportColumnVO[2].setColumnLevel(2);

				(td2).setRelatedReport(RptVo); // td for second page bottom left
												// part
				// (td).setStyles(noBorder);

				ArrayList twoColumnsInSecondPageTop = new ArrayList();

				rowList = new ArrayList();
				rowList
						.add(new StyledData("By whom tendered(Name)",
								normalFont));
				rowList.add(new StyledData("", normalFont));
				twoColumnsInSecondPageTop.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("", normalFont));
				rowList.add(new StyledData(
						"Deputy Director ,State Record Keeping Agency,",
						normalFont));
				twoColumnsInSecondPageTop.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Name(or Designation) and address of the Person on whose behalf money is paid",
								normalFont));
				rowList
						.add(new StyledData(
								"New Administrative Building, Madam Cama Road,Opp. Mantralaya,Mumbai - 400 032",
								normalFont));
				twoColumnsInSecondPageTop.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("", normalFont));
				rowList.add(new StyledData("", normalFont));
				twoColumnsInSecondPageTop.add(rowList);

				rowList = new ArrayList();
				rowList.add("");
				rowList.add("");
				twoColumnsInSecondPageTop.add(rowList);

				td1 = new TabularData(twoColumnsInSecondPageTop);
				RptVo = reportsDao.getReport("700033", report.getLangId(),
						report.getLocId()); // A 2 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(45);
				lArrReportColumnVO[1].setColumnWidth(55);
				(td1).setRelatedReport(RptVo);
				(td1).setStyles(noBorder); // td for second page top left part

				ArrayList LeftColumnSecondPage = new ArrayList();
				rowList = new ArrayList();
				rowList.add(td1);
				LeftColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(td2);
				LeftColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("*(In Words) Rupees: "
						+ lStrBillAmountInWords, normalFont));
				LeftColumnSecondPage.add(rowList);

				td3 = new TabularData(LeftColumnSecondPage);
				RptVo = reportsDao.getReport("700032", report.getLangId(),
						report.getLocId()); // A 1 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(100);
				(td3).setRelatedReport(RptVo);
				(td3).setStyles(noBorder); // td for second page left Column

				Character lCharFirstDigit = lStrEmplrSchemeCode.charAt(0);
				Character lCharSecondDigit = lStrEmplrSchemeCode.charAt(1);
				Character lCharThirdDigit = lStrEmplrSchemeCode.charAt(2);
				Character lCharFourDigit = lStrEmplrSchemeCode.charAt(3);
				Character lCharFifthDigit = lStrEmplrSchemeCode.charAt(5);
				Character lCharSixthDigit = lStrEmplrSchemeCode.charAt(6);
				Character lCharSeventhDigit = lStrEmplrSchemeCode.charAt(7);
				Character lCharEighthDigit = lStrEmplrSchemeCode.charAt(9);

				ArrayList EightDigitsList = new ArrayList();
				rowList = new ArrayList();
				rowList.add(lCharFirstDigit);
				rowList.add(lCharSecondDigit);
				rowList.add(lCharThirdDigit);
				rowList.add(lCharFourDigit);
				rowList.add(lCharFifthDigit);
				rowList.add(lCharSixthDigit);
				rowList.add(lCharSeventhDigit);
				rowList.add(lCharEighthDigit);
				EightDigitsList.add(rowList);

				td4 = new TabularData(EightDigitsList);
				RptVo = reportsDao.getReport("700036", report.getLangId(),
						report.getLocId()); // A 8 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				(td4).setRelatedReport(RptVo); // td for eight digits

				ArrayList RightColumnSecondPage = new ArrayList();
				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Head Of Account : 8342-Other Deposits,", normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"117- Defined Contribution Pension Scheme for ",
						normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData("Government Employees.", normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"(01)(01)Defined Contribution Pension Scheme,",
						normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Government's Contribution,Tier-I,"
						+ lStrEmplrSchemeCode, normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("32 Contributions", normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(td4);
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Order to the Bank", normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Correct.Receive and grant receipts.", normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Date : 09/08/2011", normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"Signature and full designation of the officer ordering",
								normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("the money to be paid in.",
						normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"Deputy Director, State Record Keeping Agency,",
						normalFontRightAlign));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"New Administrative building,Madam Cama Road, ",
						normalFontRightAlign));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Opp.Mantralaya, Mumbai-400 032.",
						normalFontRightAlign));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"To be used only in the case of remittances to the Bank",
								normalFont));
				RightColumnSecondPage.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						"through an officer of the Government.", normalFont));
				RightColumnSecondPage.add(rowList);

				td5 = new TabularData(RightColumnSecondPage);
				RptVo = reportsDao.getReport("700032", report.getLangId(),
						report.getLocId()); // A 1 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(100);
				(td5).setRelatedReport(RptVo);
				(td5).setStyles(noBorder); // td for second page right Column

				ArrayList twoColumnsInSecondPage = new ArrayList();

				rowList = new ArrayList();
				rowList.add(td3);
				rowList.add(td5);
				twoColumnsInSecondPage.add(rowList);

				td = new TabularData(twoColumnsInSecondPage);
				RptVo = reportsDao.getReport("700033", report.getLangId(),
						report.getLocId()); // A 2 column Report
				lArrReportColumnVO = RptVo.getReportColumns();
				lArrReportColumnVO[0].setColumnWidth(60);
				lArrReportColumnVO[0]
						.setColumnHeader("<font size='1'><i>To be filled in by the remitter</i></font>");
				lArrReportColumnVO[0]
						.setColumnName("<font size='1'><i>To be filled in by the remitter</i></font>");
				lArrReportColumnVO[1].setColumnWidth(40);
				lArrReportColumnVO[1]
						.setColumnHeader("<font size='1'><i>To be filled in by the Department Officer or the Treasury</i></font>");
				lArrReportColumnVO[1]
						.setColumnName("<font size='1'><i>To be filled in by the Department Officer or the Treasury</i></font>");
				(td).setRelatedReport(RptVo);
				// (td1).setStyles(noBorder); // td for second page top left
				// part

				rowList = new ArrayList();
				rowList.add(td);
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Received payment of Rs. ("
						+ lFloatBillAmount + "/-)" + space(15) + "Date: "
						+ lStrCurrDate, normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(
						lStrNewLine + lStrNewLine + lStrNewLine + lStrNewLine
								+ lStrNewLine + lStrNewLine, normalFont));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Treasurer." + space(75)
						+ "Accountant" + space(75) + "Treasury Officer/Agent",
						normalFont));
				dataList.add(rowList);

			}

		} catch (Exception e) {
			e.printStackTrace();
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
				e1.printStackTrace();
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
