package com.tcs.sgv.dcps.report;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.ReportsDAO;
import com.tcs.sgv.common.dao.reports.ReportsDAOImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.EnglishDecimalFormat;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;

public class Form2ReportPayArrearDdoNAsst extends DefaultReportDataFinder implements ReportDataFinder {
	private static final Logger gLogger = Logger.getLogger(PrintFormR2ConsolidatedReport.class);
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";
	public static String newline = System.getProperty("line.separator");

	Map requestAttributes = null;
	ServiceLocator serviceLocator = null;
	SessionFactory lObjSessionFactory = null;

	public Collection findReportData(ReportVO report, Object criteria) throws ReportException {

		report.getLangId();

		report.getLocId();

		Connection con = null;

		criteria.getClass();

		Statement smt = null;
		ResultSet rs = null;
		ReportsDAO reportsDao = new ReportsDAOImpl();
		ArrayList dataList = new ArrayList();
		ArrayList td = null;
		ReportVO RptVo = null;
		ReportVO RptVoForHiddenColumns = null;

		TabularData td1 = null;
		TabularData td2 = null;

		try {
			requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			lObjSessionFactory = serviceLocator.getSessionFactorySlave();

			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			SessionFactory lObjSessionFactory = serviceLocator.getSessionFactorySlave();
			con = lObjSessionFactory.getCurrentSession().connection();
			smt = con.createStatement();

			new StringBuffer();
			StyleVO[] rowsFontsVO = new StyleVO[5];
			rowsFontsVO[0] = new StyleVO();
			rowsFontsVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
			rowsFontsVO[0].setStyleValue("26");
			rowsFontsVO[1] = new StyleVO();
			rowsFontsVO[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			rowsFontsVO[1].setStyleValue("5");
			rowsFontsVO[2] = new StyleVO();
			rowsFontsVO[2].setStyleId(IReportConstants.BACKGROUNDCOLOR);
			rowsFontsVO[2].setStyleValue("white");
			rowsFontsVO[3] = new StyleVO();
			rowsFontsVO[3].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			rowsFontsVO[3].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
			rowsFontsVO[4] = new StyleVO();
			rowsFontsVO[4].setStyleId(IReportConstants.BORDER);
			rowsFontsVO[4].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);

			StyleVO[] normalFontRightAlign = new StyleVO[2];
			normalFontRightAlign[0] = new StyleVO();
			normalFontRightAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			normalFontRightAlign[0].setStyleValue("10");
			normalFontRightAlign[1] = new StyleVO();
			normalFontRightAlign[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			normalFontRightAlign[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);

			StyleVO[] boldAndBigFontCenterAlign = new StyleVO[3];
			boldAndBigFontCenterAlign[0] = new StyleVO();
			boldAndBigFontCenterAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			boldAndBigFontCenterAlign[0].setStyleValue("14");
			boldAndBigFontCenterAlign[1] = new StyleVO();
			boldAndBigFontCenterAlign[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldAndBigFontCenterAlign[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
			boldAndBigFontCenterAlign[2] = new StyleVO();
			boldAndBigFontCenterAlign[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			boldAndBigFontCenterAlign[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);

			StyleVO[] boldFontLeftAlign = new StyleVO[3];
			boldFontLeftAlign[0] = new StyleVO();
			boldFontLeftAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			boldFontLeftAlign[0].setStyleValue("10");
			boldFontLeftAlign[1] = new StyleVO();
			boldFontLeftAlign[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldFontLeftAlign[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
			boldFontLeftAlign[2] = new StyleVO();
			boldFontLeftAlign[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			boldFontLeftAlign[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);

			StyleVO[] boldFontRightAlign = new StyleVO[3];
			boldFontRightAlign[0] = new StyleVO();
			boldFontRightAlign[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			boldFontRightAlign[0].setStyleValue("10");
			boldFontRightAlign[1] = new StyleVO();
			boldFontRightAlign[1].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldFontRightAlign[1].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
			boldFontRightAlign[2] = new StyleVO();
			boldFontRightAlign[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			boldFontRightAlign[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);

			StyleVO[] noBorder = new StyleVO[1];
			noBorder[0] = new StyleVO();
			noBorder[0].setStyleId(IReportConstants.BORDER);
			noBorder[0].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);

			if (report.getReportCode().equals("700085")) {

				report.setStyleList(rowsFontsVO);

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				dateFormat.format(date);
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				String additionalHeader = "<i><font size=\"1px\"> "
						+ "(As referred to in para no. 14,15,17 & 28 of Government Resolution,Finance Department,No. CPS 1007/18/SER-4,dated 7 July,2007)" + "</font></i>";
				report.setAdditionalHeader(additionalHeader);

				Map lMapSessionAttributes = null;
				LoginDetails lObjLoginVO = null;
				Long gLngPostId = null;

				lMapSessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
				lObjLoginVO = (LoginDetails) lMapSessionAttributes.get("loginDetails");
				lObjLoginVO.getLocation().getLocationCode();
				lObjLoginVO.getLangId();
				gLngPostId = lObjLoginVO.getLoggedInPost().getPostId();

				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serviceLocator.getSessionFactory());
				String lStrDDOCode = null;
				String User = (String) report.getParameterValue("User");
				if(User!=null && !User.equals("")){
					lStrDDOCode= (String) report.getParameterValue("DDOCode");
				}
				else {
					lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
					if(lStrDDOCode.equals(null)) {
						lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
					}
				}
				gLogger.info("sdbg"+lStrDDOCode);
				//lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
				String lStrDDOName = lObjDcpsCommonDAO.getDdoNameForCode(lStrDDOCode);
				
				DdoOffice lObjDdoOffice = null;
				lObjDdoOffice = lObjDcpsCommonDAO.getDdoMainOffice(lStrDDOCode);
				String lStrDDOOffice = "";
				
				if(lObjDdoOffice != null)
				{
					lStrDDOOffice = lObjDdoOffice.getDcpsDdoOfficeName();
				}
				String lStrTreasuryCode = lObjDcpsCommonDAO.getTreasuryCodeForDDO(lStrDDOCode);
				//String lStrAdmBudgetCode =  lObjDcpsCommonDAO.getAdminBudgetCodeForDDO(lStrDDOCode);

				String lStrBillGroupId = (String) report.getParameterValue("billGroupId");
				String lStrYearId = (String) report.getParameterValue("yearId");
				String lStrMonthId = (String) report.getParameterValue("monthId");

				ArrayList rowList = new ArrayList();
				rowList.add(new StyledData("Schedule Showing Employer's contribution towards Tier 1 of the New Defined Contribution Pension Scheme", boldFontLeftAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Name of Office: " + lStrDDOOffice, boldFontLeftAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Name of DDO/Code No.:" + lStrDDOName + space(10) + lStrDDOCode, boldFontLeftAlign));
				dataList.add(rowList);

				/*
				rowList = new ArrayList();
				rowList.add(new StyledData("Department Code: " + lStrAdmBudgetCode, boldFontLeftAlign));
				dataList.add(rowList);
				*/

				String lStrYearCode = lObjDcpsCommonDAO.getYearCodeForYearId(Long.valueOf(lStrYearId));
				String lStrMonth = lObjDcpsCommonDAO.getMonthForId(Long.valueOf(lStrMonthId));
				
				Long lLongMonthId = Long.valueOf(lStrMonthId.trim());
				if(lLongMonthId == 1l || lLongMonthId == 2l || lLongMonthId == 3l)
				{
					Long lLongYearCode = Long.valueOf(lStrYearCode);
					lLongYearCode = lLongYearCode + 1;
					lStrYearCode = lLongYearCode.toString();
				}

				rowList = new ArrayList();
				rowList.add(new StyledData("For the Month of " + lStrMonth + space(2) + lStrYearCode, boldFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Region/Treasury/Sub-Treasury Code:" + lStrTreasuryCode, boldFontRightAlign));
				dataList.add(rowList);

				StringBuffer lSBQuery = new StringBuffer();

				//HPP.PO as basic, HPP.DA,ddo.ddo_code,
				lSBQuery.append(" select EM.dcps_id,EM.emp_name,HPP.SVNPC_DCPS_RECO");
				lSBQuery.append(" from ifms.HR_PAY_PAYBILL  HPP,ifms.mst_dcps_emp EM");
				lSBQuery.append(" ,ifms.hr_eis_emp_mst HE,ifms.PAYBILL_HEAD_MPG PHM");
				lSBQuery.append(" , ifms.MST_DCPS_DDO_OFFICE as ddo");
				lSBQuery.append(" WHERE EM.ORG_EMP_MST_ID = HE.EMP_MPG_ID  ");
				lSBQuery.append(" and HPP.EMP_ID = HE.EMP_ID");
				lSBQuery.append(" and PHM.PAYBILL_ID = HPP.PAYBILL_GRP_ID");
				lSBQuery.append(" and HPP.LOC_ID=ddo.LOC_ID");
				lSBQuery.append(" and HPP.SVNPC_DCPS_RECO <> 0 ");
				lSBQuery.append(" and PHM.BILL_NO = :billGroupId");
				lSBQuery.append(" and PHM.PAYBILL_MONTH = :month");
				lSBQuery.append(" and PHM.PAYBILL_YEAR = " + Long.valueOf(lStrYearCode));
				lSBQuery.append(" and PHM.APPROVE_FLAG in (0,1)");
				lSBQuery.append(" AND EM.REG_STATUS = 1");
				lSBQuery.append(" AND EM.dcps_id IS NOT NULL");
				lSBQuery.append(" ORDER BY EM.emp_name ASC");

				Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
				SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
				Query.setLong("month", Long.valueOf(lStrMonthId));
				Query.setLong("billGroupId", Long.valueOf(lStrBillGroupId));

				List lLstFinal = Query.list();
				Long lLongSRNo = 1l;
				Double lDoubleTotalContribution = 0d;

				ArrayList dataListForTable = new ArrayList();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				if (lLstFinal != null && !lLstFinal.isEmpty()) {
					Iterator it = lLstFinal.iterator();

					while (it.hasNext()) {
						// For PageBreak

						Object[] tuple = (Object[]) it.next();
						td = new ArrayList();
						td.add(lLongSRNo); // SR No
						
						if (tuple[1] != null) // name
						{
							td.add(tuple[1].toString());
						} else {
							td.add("");
						}

						if (tuple[0] != null) // DCPS ID
						{
							td.add(tuple[0].toString());
						} else {
							td.add("");
						}

						if (tuple[2] != null) // recovery 
						{
							td.add(Round(Double.parseDouble(tuple[2].toString()), 0));
							lDoubleTotalContribution = Double
							.parseDouble(tuple[2].toString())
							+ lDoubleTotalContribution;
						} else {
							td.add("");
						}
						td.add(""); // Remarks
						//td.add(""); // others
						dataListForTable.add(td);

						lLongSRNo++;
					}
				}

				if (dataListForTable.size() != 0) {
					RptVo = reportsDao.getReport("700098", report.getLangId(), report.getLocId()); // The 10 column report for table
					td1 = new TabularData(dataListForTable);
					(td1).setRelatedReport(RptVo);
					rowList = new ArrayList();
					rowList.add(td1);
					dataList.add(rowList);
				}

				ArrayList dataListForTableWithHiddenColumns = new ArrayList();
				RptVoForHiddenColumns = reportsDao.getReport("700098", report.getLangId(), report.getLocId());
				ReportColumnVO[] lArrReportColumnVOs = RptVoForHiddenColumns.getReportColumns();
				lArrReportColumnVOs[0].setHidden("Y");
				lArrReportColumnVOs[1].setHidden("Y");
				lArrReportColumnVOs[2].setColumnHeader("");
				lArrReportColumnVOs[2].setColumnName("");
				lArrReportColumnVOs[2].setColumnWidth(71);
				lArrReportColumnVOs[3].setColumnHeader("");
				lArrReportColumnVOs[3].setColumnName("");
				lArrReportColumnVOs[3].setColumnWidth(20);
				lArrReportColumnVOs[4].setColumnHeader("");
				lArrReportColumnVOs[4].setColumnName("");
				lArrReportColumnVOs[4].setColumnWidth(9);
				/*lArrReportColumnVOs[5].setColumnHeader("");
				lArrReportColumnVOs[5].setColumnName("");
				lArrReportColumnVOs[5].setColumnWidth(9);*/
				RptVoForHiddenColumns.setReportColumns(lArrReportColumnVOs);

				StringBuffer lStrThirdLastLineInTable = new StringBuffer("Total Amount of Employee's Contribution - Head Of Account 8342- other Deposits, 117 - ");
				lStrThirdLastLineInTable.append("Government Employee's Defined Contribution Pension Scheme (02)(01)- Defined Contribution Pension Scheme,");
				lStrThirdLastLineInTable.append("Government Employee's Contribution Tier-1(8432-508-1),32-Contributions");

				StringBuffer lStrSecondLastLineInTable = new StringBuffer("Add - Employer's contribution(not applicable in case of Government Employees)");
				lStrSecondLastLineInTable.append("Head of Account 8432-Other Deposits,117-Government Employees Defined Contribution Pension scheme");

				StringBuffer lStrLastLineInTable = new StringBuffer("Grand Total");

				td = new ArrayList();
				td.add(new StyledData(lStrThirdLastLineInTable, rowsFontsVO));
				td.add(lDoubleTotalContribution);
				td.add("" + space(1));

				dataListForTableWithHiddenColumns.add(td);

				td = new ArrayList();
				td.add(new StyledData(lStrSecondLastLineInTable, rowsFontsVO));
				td.add(0);
				td.add("" + space(1));

				dataListForTableWithHiddenColumns.add(td);

				td = new ArrayList();
				td.add(new StyledData(lStrLastLineInTable, rowsFontsVO));
				td.add(lDoubleTotalContribution);
				td.add("" + space(1));
				dataListForTableWithHiddenColumns.add(td);

				td2 = new TabularData(dataListForTableWithHiddenColumns);
				(td2).setRelatedReport(RptVoForHiddenColumns);
				rowList = new ArrayList();
				rowList.add(td2);
				dataList.add(rowList);

				/* Page Break for Certificate */
				rowList = new ArrayList();
				rowList.add(new PageBreak());
				dataList.add(rowList);
				/* Page Break Ends */

				rowList = new ArrayList();
				rowList.add(new StyledData("Total Amount Ruppes: " + lDoubleTotalContribution + "/-", rowsFontsVO));
				dataList.add(rowList);

				String lStrTotAmtInWords = EnglishDecimalFormat.convertWithSpace(new BigDecimal(lDoubleTotalContribution));

				rowList = new ArrayList();
				rowList.add(new StyledData("In words " + lStrTotAmtInWords, rowsFontsVO));
				dataList.add(rowList);

				Object[] lObjScheme = lObjDcpsCommonDAO.getSchemeNameFromBillGroup(Long.valueOf(lStrBillGroupId));

				rowList = new ArrayList();
				rowList.add(new StyledData("Under the Major Head Of Account " + lObjScheme[1].toString(), rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Show the details of Service Head of account here", rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Gross Amount Of the bill/challan Rs.=", rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Net Amount Of the bill/challan Rs.=", rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Challan No." + space(30) + "& Date.", rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("CERTIFICATE", boldAndBigFontCenterAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Certiffied that I have personally verified the correctness of the details in this schedule and they are found to be correct.",
						rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Date:" + space(35) + "Date of Encashment:", rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("" + space(1), rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Signature", normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData(lStrDDOName, normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				if(lObjDdoOffice != null)
				{
					rowList.add(new StyledData(lObjDdoOffice.getDcpsDdoOfficeAddress1(), normalFontRightAlign));
				}
				else
				{
					rowList.add(new StyledData("", normalFontRightAlign));
				}
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add("For Use of Audit Officer:");
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList
						.add(new StyledData(
								"1: Certified htat the name amount of the individual's deduction and the total shown in column(8) have been checked with reference to the bill,vide,paragraph 224 of the Audit Manual",
								rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("2: Certified that the rate of pay as shown in column (5) has been verified with the amount actually drawn in the bill ", rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("3: Certified that challan for Rs." + space(10) + "is attached to this schedule", rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("" + space(1), rowsFontsVO));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Signature", normalFontRightAlign));
				dataList.add(rowList);

				rowList = new ArrayList();
				rowList.add(new StyledData("Treasury Officer", normalFontRightAlign));
				dataList.add(rowList);

				String lStrTreasuryCityForDDO = lObjDcpsCommonDAO.getTreasuryCityForDDO(lStrDDOCode);

				rowList = new ArrayList();
				rowList.add(new StyledData(lStrTreasuryCityForDDO, normalFontRightAlign));
				dataList.add(rowList);

			}
		}

		catch (Exception e) {
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

	public List getBillGroup(Hashtable otherArgs, String lStrLangId, String lStrLocCode) {
		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");
		Long lLongPostId = null;
		if (loginMap.containsKey("loggedInPost")) {
			lLongPostId = (Long) loginMap.get("loggedInPost");
		}

		DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, ServiceLocator.getServiceLocator().getSessionFactory());

		String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(lLongPostId);
		String lStrBufLang="";
		Query lObjQuery=null;
		List<Object> lArrBillGroups = new ArrayList<Object>();

		try {
			Session lObjSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			if(lStrDdoCode==null) {
					lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(lLongPostId);
				  lStrBufLang = "SELECT dcpsDdoBillGroupId, dcpsDdoBillDescription FROM MstDcpsBillGroup WHERE (dcpsDdoCode = :dcpsDdoCode or substr(DDO_CODE,3,4)=:parentDcpsDDO)and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') ORDER BY dcpsDdoBillDescription";
				    lObjQuery = lObjSession.createQuery(lStrBufLang);
					lObjQuery.setString("dcpsDdoCode", lStrDdoCode);
					lObjQuery.setString("parentDcpsDDO", lStrDdoCode.substring(0,4));
				}else
					{
						  lStrBufLang = "SELECT dcpsDdoBillGroupId, dcpsDdoBillDescription FROM MstDcpsBillGroup WHERE dcpsDdoCode = :dcpsDdoCode and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') ORDER BY dcpsDdoBillDescription";
						    lObjQuery = lObjSession.createQuery(lStrBufLang);
							lObjQuery.setString("dcpsDdoCode", lStrDdoCode);
					}
			

			

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			Object[] lArrData = null;

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					lArrBillGroups.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}

		return lArrBillGroups;
	}

	public List getMonth(String lStrLangId, String lStrLocCode) {
		List<Object> lArrMonths = new ArrayList<Object>();
		try {
			Session lObjSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();

			String lStrBufLang = "SELECT monthId, monthName FROM SgvaMonthMst WHERE langId = :langId ORDER BY monthNo";

			Query lObjQuery = lObjSession.createQuery(lStrBufLang);
			lObjQuery.setString("langId", lStrLangId);

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			Object[] lArrData = null;

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					lArrMonths.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}

		return lArrMonths;
	}

	public List getYear(String lStrLangId, String lStrLocId) {

		List<Object> lArrYears = new ArrayList<Object>();
		try {
			Session lObjSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			/* This added code for dyanmic fin year BY Naveen Priya Sinha*/
			Date date = new Date();
			String ymd =new SimpleDateFormat("yyyyMMdd").format(date); 
			String Year=new SimpleDateFormat("yyyy").format(date); 
			String Month=new SimpleDateFormat("MM").format(date); 
			String Day=new SimpleDateFormat("dd").format(date); 
			String lStrBufLang = "";
			
			if(Integer.parseInt(Month)<4) {
				int year1 = Integer.parseInt(Year)-1;
				 lStrBufLang = "SELECT finYearId, finYearDesc FROM SgvcFinYearMst WHERE langId = :langId and finYearCode BETWEEN '2007' AND '"+year1+"' ORDER BY finYearCode";
			} else {
				lStrBufLang = "SELECT finYearId, finYearDesc FROM SgvcFinYearMst WHERE langId = :langId and finYearCode BETWEEN '2007' AND '"+Year+"' ORDER BY finYearCode";
			}
			/* END for dyanmic fin year BY Naveen Priya Sinha*/
			
			//String lStrBufLang = "SELECT finYearId, finYearDesc FROM SgvcFinYearMst WHERE langId = :langId and finYearCode BETWEEN '2007' AND '"+Year+"' ORDER BY finYearCode";//changed by Tejashree

			Query lObjQuery = lObjSession.createQuery(lStrBufLang);
			lObjQuery.setString("langId", lStrLangId);

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			Object[] lArrData = null;

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					lArrYears.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}

		return lArrYears;
	}

	public String space(int noOfSpace) {
		String blank = "";
		for (int i = 0; i < noOfSpace; i++) {
			blank += "\u00a0";
		}
		return blank;
	}

	public String getNewline() {
		String getNewline = "";
		getNewline = "\\u000d\\u000a";
		return getNewline;
	}

	public static double Round(double Rval, int Rpl) {
		double p = Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return tmp / p;
	}
}
