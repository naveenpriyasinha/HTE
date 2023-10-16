/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 18, 2011		Vihan Khatri								
 *******************************************************************************
 */
/**
 * Class Description - 
 *
 *
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0
 * Mar 18, 2011
 */

package com.tcs.sgv.dcps.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.dao.MatchContriEntryDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Feb 9, 2011
 */
public class MatchedContriEntriesReport extends DefaultReportDataFinder {

	private static final Logger gLogger = Logger
			.getLogger(DCPSEmployeeAcknowledgementReport.class);
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";

	Map requestAttributes = null;

	SessionFactory lObjSessionFactory = null;

	ServiceLocator serviceLocator = null;

	public Collection findReportData(ReportVO report, Object criteria)
			throws ReportException {


		String locId = report.getLocId();
		
		Connection con = null;

		criteria.getClass();

		Statement smt = null;
		ResultSet rs = null;
		ArrayList dataList = new ArrayList();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		// for Center Alignment format
		StyleVO[] CenterAlignVO = new StyleVO[2];
		CenterAlignVO[0] = new StyleVO();
		CenterAlignVO[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		CenterAlignVO[0]
				.setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
		CenterAlignVO[1] = new StyleVO();
		CenterAlignVO[1].setStyleId(IReportConstants.BORDER);
		CenterAlignVO[1]
				.setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);

		StyleVO[] noBorder = new StyleVO[1];
		noBorder[0] = new StyleVO();
		noBorder[0].setStyleId(IReportConstants.BORDER);
		noBorder[0].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);

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

			Long locationId = (Long) loginDetail.get("locationId");

			String StrSqlQuery = "";

			if (report.getReportCode().equals("700030")) {

				// report.setStyleList(noBorder);

				ArrayList rowList = new ArrayList();

				Long treasuryCode = Long.valueOf((String) report
						.getParameterValue("treasuryCode"));
				String fromDate = ((String) report
						.getParameterValue("fromDate"));
				String toDate = ((String) report.getParameterValue("toDate"));
				String yearDesc = ((String) report
						.getParameterValue("yearDesc"));
				Long yearId = Long.valueOf((String) report
						.getParameterValue("yearId"));
				String lStrDDOCode = null;
				String lStrDDOName = null;

				String MatchedOrUnMatched = (String) report
						.getParameterValue("MatchedOrUnMatched");

				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
						serviceLocator.getSessionFactory());

				String url = "";

				if (MatchedOrUnMatched.equals("Matched")) {
					url = "ifms.htm?actionFlag=loadMatchContriEntryForm&yearId="
							+ yearId
							+ "&fromDate="
							+ fromDate
							+ "&toDate="
							+ toDate;
				}
				if (MatchedOrUnMatched.equals("UnMatched")) {
					url = "ifms.htm?actionFlag=loadUnMatchContriEntryForm&yearId="
							+ yearId
							+ "&fromDate="
							+ fromDate
							+ "&toDate="
							+ toDate;
				}

				StyleVO[] lObjStyleVO = new StyleVO[report.getStyleList().length];
				lObjStyleVO = report.getStyleList();
				for (Integer lInt = 0; lInt < report.getStyleList().length; lInt++) {
					if (lObjStyleVO[lInt].getStyleId() == 26) {
						lObjStyleVO[lInt].setStyleValue(url);
					}
				}

				String fromDateQuery = sdf2.format(sdf1.parse(fromDate));
				String toDateQuery = sdf2.format(sdf1.parse(toDate));

				StringBuilder SBQuery = new StringBuilder();

				if (MatchedOrUnMatched.equals("Matched")) {

					SBQuery
							.append(" SELECT CV.treasury_code,CV.voucher_no,CV.voucher_date,CV.voucher_amount,BG.SCHEME_CODE,TN.bill_no, TN.bill_date,");
					SBQuery
							.append(" TN.voucher_no,TN.voucher_date,TN.to_scheme,TN.dcps_amount,DM.ddo_code,'' AS VCStatus1 ,'' AS VCStatus2 ,CV.manually_matched");
					SBQuery
							.append(" FROM mst_dcps_contri_voucher_dtls CV,mst_dcps_treasurynet_data TN, sgvc_fin_year_mst FY ,");
					SBQuery.append(" mst_dcps_bill_group BG,org_ddo_mst DM  ");
					SBQuery.append(" WHERE ");
					SBQuery.append(" ((CV.voucher_no = TN.voucher_no AND CV.voucher_date = TN.voucher_date AND CV.voucher_amount = TN.dcps_amount) OR (CV.manually_matched = 1))");
					SBQuery.append(" AND BG.bill_group_ID = CV.bill_group_id");
					SBQuery.append(" AND FY.fin_year_desc = TN.year_desc ");
					SBQuery.append(" AND FY.fin_year_id = CV.year_id");
					SBQuery.append(" AND BG.SCHEME_CODE = TN.to_scheme");

					SBQuery.append(" AND TN.dcps_amount = CV.voucher_amount ");

					SBQuery.append(" AND DM.ddo_code = CV.ddo_code");
					SBQuery.append(" AND CV.treasury_code = '" + treasuryCode
							+ "'");
					SBQuery.append(" AND CV.voucher_date BETWEEN '"
							+ fromDateQuery + "' AND '" + toDateQuery + "'");
					//SBQuery.append(" GROUP BY CV.voucher_no");

				}
				if (MatchedOrUnMatched.equals("UnMatched")) {

					SBQuery
							.append(" SELECT CV.treasury_code, cv.voucher_no, cv.voucher_date,cv.voucher_amount,BG.SCHEME_CODE,TN.bill_no, TN.bill_date,");
					SBQuery
							.append(" tn.voucher_no, tn.voucher_date,TN.to_scheme,tn.dcps_amount,cv.ddo_code ,'V-A' AS VCStatus1 ,'' AS VCStatus2  ");
					SBQuery
							.append(" FROM mst_dcps_contri_voucher_dtls cv LEFT JOIN mst_dcps_treasurynet_data tn ON cv.voucher_no = tn.voucher_no, ");
					SBQuery.append(" mst_dcps_bill_group BG");
					SBQuery
							.append(" WHERE tn.voucher_no IS NULL AND CV.treasury_code =' "
									+ treasuryCode + "' ");
					SBQuery.append(" AND CV.voucher_date BETWEEN '"
							+ fromDateQuery + "' AND '" + toDateQuery + "'");

					SBQuery
							.append("  AND  BG.bill_group_id = cv.bill_group_id");
					
					SBQuery.append(" AND CV.manually_matched <> 1");

					SBQuery.append(" UNION");
					SBQuery
							.append(" SELECT CV.treasury_code, cv.voucher_no, cv.voucher_date,cv.voucher_amount,to_char(cv.bill_group_id),TN.bill_no, TN.bill_date,");
					SBQuery
							.append(" tn.voucher_no, tn.voucher_date,TN.to_scheme,tn.dcps_amount,tn.ddo_code ,'' AS VCStatus1,'V-A' AS VCStatus2");
					SBQuery
							.append(" FROM mst_dcps_treasurynet_data tn LEFT JOIN mst_dcps_contri_voucher_dtls cv ON cv.voucher_no = tn.voucher_no");
					SBQuery
							.append(" WHERE CV.voucher_no IS NULL AND tn.treasury_code =' "
									+ treasuryCode + "' ");
					SBQuery.append(" AND tn.voucher_date BETWEEN '"
							+ fromDateQuery + "' AND '" + toDateQuery + "'");
					
					SBQuery.append(" AND CV.manually_matched <> 1");

					SBQuery.append(" UNION ");
					SBQuery
							.append(" SELECT CV.treasury_code, cv.voucher_no, cv.voucher_date,cv.voucher_amount,BG.SCHEME_CODE,TN.bill_no, TN.bill_date,");
					SBQuery
							.append(" tn.voucher_no, tn.voucher_date,TN.to_scheme,tn.dcps_amount,cv.ddo_code ,'V-B' AS VCStatus1,'V-B' AS VCStatus2");
					SBQuery
							.append(" FROM mst_dcps_contri_voucher_dtls cv , mst_dcps_treasurynet_data tn , mst_dcps_bill_group BG, sgvc_fin_year_mst FY");
					SBQuery
							.append(" WHERE cv.voucher_no = tn.voucher_no AND cv.voucher_date <> tn.voucher_date AND CV.treasury_code = '"
									+ treasuryCode + "'");
					SBQuery.append(" AND cv.voucher_date BETWEEN '"
							+ fromDateQuery + "' AND '" + toDateQuery + "'");
					SBQuery
							.append(" AND  BG.bill_group_ID = CV.bill_group_id ");
					SBQuery
							.append(" AND FY.fin_year_desc = TN.year_desc  AND FY.fin_year_id = CV.year_id ");
					
					SBQuery.append(" AND CV.manually_matched <> 1");

					SBQuery.append(" UNION");
					SBQuery
							.append(" SELECT CV.treasury_code, cv.voucher_no, cv.voucher_date,cv.voucher_amount,BG.SCHEME_CODE,TN.bill_no, TN.bill_date,");
					SBQuery
							.append(" tn.voucher_no, tn.voucher_date,TN.to_scheme,tn.dcps_amount,cv.ddo_code ,'V-B' AS VCStatus1,'V-B' AS VCStatus2");
					SBQuery
							.append(" FROM mst_dcps_contri_voucher_dtls cv , mst_dcps_treasurynet_data tn , mst_dcps_bill_group BG, sgvc_fin_year_mst FY");
					SBQuery
							.append(" WHERE cv.voucher_no = tn.voucher_no AND cv.voucher_date = tn.voucher_date AND cv.voucher_amount <> tn.dcps_amount AND CV.treasury_code = '"
									+ treasuryCode + "'");
					SBQuery.append(" AND cv.voucher_date BETWEEN '"
							+ fromDateQuery + "' AND '" + toDateQuery + "'");
					SBQuery
							.append(" AND  BG.bill_group_ID = CV.bill_group_id ");
					SBQuery
							.append(" AND FY.fin_year_desc = TN.year_desc  AND FY.fin_year_id = CV.year_id");
					
					SBQuery.append(" AND CV.manually_matched <> 1");

				}

				StrSqlQuery = SBQuery.toString();
				rs = smt.executeQuery(StrSqlQuery);
				Integer counter = 1;

				String urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=700031&action=generateReport&DirectReport=TRUE&displayOK=TRUE";

				while (rs.next()) {

					rowList = new ArrayList();

					rowList.add(new StyledData(counter, CenterAlignVO));

					if (rs.getString("VCStatus1") != null  && !rs.getString("VCStatus1").equals("")) {
						rowList.add(new StyledData(rs.getString("VCStatus1"),
								CenterAlignVO));
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}
	
					rowList.add(new URLData(rs.getString(2), urlPrefix
									+ "&treasuryCode=" + rs.getString(1)
									+ "&voucherNo=" + rs.getString(2) + "&fromDate="
									+ fromDate + "&toDate=" + toDate + "&yearDesc="
									+ yearDesc + "&yearId=" + yearId
									+ "&MatchedOrUnMatched=" + MatchedOrUnMatched));

					if (!(rs.getString(3) == null)) {
						if (!(rs.getString(3).equals(""))) {
							rowList.add(new StyledData(sdf1.format(sdf3
									.parse(rs.getString(3).toString())),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(4) == null)) {
						if (!rs.getString(4).equals("")) {
							rowList.add(new StyledData(rs.getString(4),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(5) == null)) {
						if (!rs.getString(5).equals("")) {
							rowList.add(new StyledData(rs.getString(5),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (rs.getString("VCStatus2") != null && !rs.getString("VCStatus2").equals("")) {
						rowList.add(new StyledData(rs.getString("VCStatus2"),
								CenterAlignVO));
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(6) == null)) {
						if (!rs.getString(6).equals("")) {
							rowList.add(new StyledData(rs.getString(6),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(7) == null)) {
						if (!(rs.getString(7).equals(""))) {
							rowList.add(new StyledData(sdf1.format(sdf3
									.parse(rs.getString(7).toString())),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(8) == null)) {
						if (!rs.getString(8).equals("")) {
							rowList.add(new StyledData(rs.getString(8),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(9) == null)) {
						if (!(rs.getString(9).equals(""))) {
							rowList.add(new StyledData(sdf1.format(sdf3
									.parse(rs.getString(9).toString())),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(10) == null)) {
						if (!rs.getString(10).equals("")) {
							rowList.add(new StyledData(rs.getString(10),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(11) == null)) {
						if (!rs.getString(11).equals("")) {
							rowList.add(new StyledData(rs.getString(11),
									CenterAlignVO));
						} else {
							rowList.add(new StyledData("", CenterAlignVO));
						}
					} else {
						rowList.add(new StyledData("", CenterAlignVO));
					}

					if (!(rs.getString(12).equals("") || rs.getString(12) == null)) {
						lStrDDOCode = rs.getString(12).toString().trim();
						lStrDDOName = lObjDcpsCommonDAO
								.getDdoNameForCode(lStrDDOCode);

						rowList.add(new StyledData(rs.getString(12) + space(3)
								+ lStrDDOName, CenterAlignVO));
					} else {
						lStrDDOCode = "";
						lStrDDOName = "";

						rowList.add(new StyledData("", CenterAlignVO));
					}

					dataList.add(rowList);

					counter = counter + 1;
				}
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

			} catch (Exception e) {
				e.printStackTrace();
				gLogger.error("Exception :" + e, e);
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
