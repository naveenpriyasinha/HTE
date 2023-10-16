package com.tcs.sgv.reports;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.core.service.ServiceLocator;

/**
 * Class Description -
 * 
 * 
 * @author Vaibhav Tyagi
 * @version 0.1
 * @since JDK 7.0 April 11,2014
 */
public class MasterReportDAO extends DefaultReportDataFinder {
	private static final Logger gLogger = Logger.getLogger("MasterReportDAO");
	public static String newline = System.getProperty("line.separator");
	ServiceLocator serviceLocator = null;
	SessionFactory gObjSessionFactory = null;
	String gStrLocCode = null;
	Long gLngLangId = null;
	Long gLngPostId = null;
	Map lMapSeriesHeadCode = null;
	Map requestAttributes = null;
	Session ghibSession = null;

	public Collection findReportData(ReportVO report, Object criteria) throws ReportException {
		ArrayList dataList = new ArrayList();
		requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		gObjSessionFactory = serviceLocator.getSessionFactorySlave();

		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		StyleVO[] rowsFontsVO = new StyleVO[3];
		rowsFontsVO[0] = new StyleVO();
		rowsFontsVO[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		rowsFontsVO[0].setStyleValue("10");
		rowsFontsVO[1] = new StyleVO();
		rowsFontsVO[1].setStyleId(IReportConstants.BACKGROUNDCOLOR);
		rowsFontsVO[1].setStyleValue("white");
		rowsFontsVO[2] = new StyleVO();
		rowsFontsVO[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		rowsFontsVO[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
		report.setStyleList(rowsFontsVO);

		StyleVO[] rowsVO = new StyleVO[3];
		rowsVO[0] = new StyleVO();
		rowsVO[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		rowsVO[0].setStyleValue("12");
		rowsVO[1] = new StyleVO();
		rowsVO[1].setStyleId(IReportConstants.BACKGROUNDCOLOR);
		rowsVO[1].setStyleValue("#B5BABA");
		rowsVO[2] = new StyleVO();
		rowsVO[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		rowsVO[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
		report.setStyleList(rowsVO);

		StyleVO[] rowsStateVO = new StyleVO[3];
		rowsStateVO[0] = new StyleVO();
		rowsStateVO[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		rowsStateVO[0].setStyleValue("12");
		rowsStateVO[1] = new StyleVO();
		rowsStateVO[1].setStyleId(IReportConstants.BACKGROUNDCOLOR);
		rowsStateVO[1].setStyleValue("#9B9E9E");
		rowsStateVO[2] = new StyleVO();
		rowsStateVO[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		rowsStateVO[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
		report.setStyleList(rowsStateVO);

		String regionCode = null;
		String districtCode = null;
		String adminType = null;
		String month = null;
		String year = null;

		ArrayList row = null;
		StyledData rowData = null;
		ResultSet mainReport = null;

		try {

			regionCode = (String) report.getParameterValue("listRegion");
			districtCode = "-1";
			adminType = "-1";
			month = (String) report.getParameterValue("listmonth");
			year = (String) report.getParameterValue("listYear");

			Map employeeConfReport = getEmployeeConfReport(regionCode, districtCode, adminType);
			Map paybillGeneration = getPaybillGenerationReport(regionCode, districtCode, adminType, month, year);
			gLogger.info("paybillGeneration size is 118 " + paybillGeneration);
			gLogger.info("paybillGeneration size is " + paybillGeneration.size());

			mainReport = getMainTotal(regionCode, districtCode, adminType);

			String prvRegionName = null;
			Long prvRegionCode = 0l;
			Long stateTotalSchoolConfig = 0l;
			Long regionTotalSchoolConfig = 0l;
			Long stateSchoolApproved = 0l;
			Long regionSchoolApproved = 0l;
			Long stateDataEntryInitiate = 0l;
			Long regionDataEntryInitiate = 0l;
			Long stateSancPost = 0l;
			Long regionSancPost = 0l;
			Long stateTotalEmpWorking = 0l;
			Long regionTotalEmpWorking = 0l;
			Long stateTotalEmpConfig = 0l;
			Long regionTotalEmpConfig = 0l;
			Long stateDraftEmp = 0l;
			Long regionDraftEmp = 0l;
			Long statePendingEmp = 0l;
			Long regionPendingEmp = 0l;
			Long stateApprovedEmp = 0l;
			Long regionApprovedEmp = 0l;
			Long statePaybillGenerated = 0l;
			Long regionPaybillGenerated = 0l;
			Long statePaybillConsolidated = 0l;
			Long regionPaybillConsolidated = 0l;
			Long stateEmpCovered = 0l;
			Long regionEmpCovered = 0l;
			Long regionPaybillForwarded = 0l;
			Long statePaybillForwarded = 0l;
			Long regionEmpPayLocked = 0l;
			Long stateEmpPayLocked = 0l;
			Long regionPaybillLocked = 0l;
			Long statePaybillLocked = 0l;
			Long regionEmpPayGenerated = 0l;
			Long stateEmpPayGenerated = 0l;
			Long regionEmpPayForwarded = 0l;
			Long stateEmpPayForwarded = 0l;

			String urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=8000066&action=generateReport&DirectReport=TRUE&displayOK=FALSE";
			String urlPrefix1 = "ifms.htm?actionFlag=reportService&reportCode=8000068&action=generateReport&DirectReport=TRUE&displayOK=FALSE";

			gLogger.info("mainReport size is " + mainReport.getFetchSize());
			if (mainReport != null) {
				while (mainReport.next()) {
					String regionId = mainReport.getString(1).toString();
					String districtId = mainReport.getString(2).toString();
					String adminId = mainReport.getString(3).toString();
					String regionName = mainReport.getString(4).toString();
					String districtName = mainReport.getString(5).toString();
					String adminName = mainReport.getString(6).toString();
					String totalSchoolConf = mainReport.getString(7).toString();
					String totalSchoolApproved = mainReport.getString(8).toString();
					String totalSancPost = mainReport.getString(9).toString();
					String totalEmpWorking = mainReport.getString(10).toString();

					String totalEmpConfig = null;
					String draftEmp = null;
					String pendingEmp = null;
					String approvedEmp = null;
					String[] empConfigDetails = null;
					String dataEntryInitiated = null;
					if (employeeConfReport.containsKey(regionId + "~" + districtId + "~" + adminId)) {
						empConfigDetails = employeeConfReport.get(regionId + "~" + districtId + "~" + adminId)
								.toString().split("~");
						dataEntryInitiated = empConfigDetails[0].toString();
						totalEmpConfig = empConfigDetails[1].toString();
						draftEmp = empConfigDetails[2].toString();
						pendingEmp = empConfigDetails[3].toString();
						approvedEmp = empConfigDetails[4].toString();
					}

					else {

						gLogger.info(" else  employeeConfReport *******#####************ ");
						dataEntryInitiated = "0";
						totalEmpConfig = "0";
						draftEmp = "0";
						pendingEmp = "0";
						approvedEmp = "0";
					}

					String noOfPaybillGenerated = null;
					String noOfPaybillForwarded = null;
					String noOfPaybillConsolidated = null;
					String noOfEmployeesCovered = null;
					String[] paybillGenerationDetails = null;
					Long noOfEmpWhosePaybillLocked = 0l;
					Long noOfPaybillsLocked = 0l;
					Long noOfEmpWhosePaybillGenerated = 0l;
					Long noOfEmpWhosePaybillForwarded = 0l;
					gLogger.info("Map values " + paybillGeneration.get(regionId + "~" + districtId + "~" + adminId));
					if (paybillGeneration.containsKey(regionId + "~" + districtId + "~" + adminId)) {
						gLogger.info(" inside paybillGeneration *******#####************ ");
						paybillGenerationDetails = paybillGeneration.get(regionId + "~" + districtId + "~" + adminId)
								.toString().split("~");
						noOfPaybillGenerated = paybillGenerationDetails[0].toString();
						gLogger.info(" noOfPaybillGenerated********* " + noOfPaybillGenerated);
						noOfPaybillConsolidated = paybillGenerationDetails[1].toString();
						gLogger.info(" noOfPaybillConsolidated********* " + noOfPaybillConsolidated);
						noOfEmployeesCovered = paybillGenerationDetails[2].toString();
						gLogger.info(" noOfEmployeesCovered********* " + noOfEmployeesCovered);
						noOfPaybillForwarded = paybillGenerationDetails[3].toString();
						gLogger.info(" noOfPaybillForwarded********* " + noOfPaybillForwarded);
						noOfEmpWhosePaybillLocked = Long.parseLong(paybillGenerationDetails[4].toString());
						gLogger.info(" No of emp whose paybill is locked.********* " + noOfEmpWhosePaybillLocked);
						noOfPaybillsLocked = Long.parseLong(paybillGenerationDetails[5].toString());
						gLogger.info(" No of paybill locked.********* " + noOfPaybillsLocked);
						noOfEmpWhosePaybillGenerated = Long.parseLong(paybillGenerationDetails[6].toString());
						gLogger.info(" No of emp whose paybill is generated.********* " + noOfEmpWhosePaybillGenerated);
						noOfEmpWhosePaybillForwarded = Long.parseLong(paybillGenerationDetails[7].toString());
						gLogger.info(" No of emp whose paybill is forwarded.********* " + noOfEmpWhosePaybillForwarded);
					}

					else {
						noOfPaybillGenerated = "0";
						noOfPaybillConsolidated = "0";
						noOfEmployeesCovered = "0";
						noOfPaybillForwarded = "0";
						noOfEmpWhosePaybillLocked = 0l;
						noOfPaybillsLocked = 0l;
						noOfEmpWhosePaybillGenerated = 0l;
						noOfEmpWhosePaybillForwarded = 0l;
					}

					if (prvRegionCode == 0 || prvRegionCode.equals(Long.parseLong(regionId))) {
						gLogger.info(" inside prvRegionCode *******#####************ ");

						prvRegionName = regionName;
						prvRegionCode = Long.parseLong(regionId);

						regionTotalSchoolConfig += Long.parseLong(totalSchoolConf);
						stateTotalSchoolConfig += Long.parseLong(totalSchoolConf);

						regionSchoolApproved += Long.parseLong(totalSchoolApproved);
						stateSchoolApproved += Long.parseLong(totalSchoolApproved);

						regionDataEntryInitiate += Long.parseLong(dataEntryInitiated);
						stateDataEntryInitiate += Long.parseLong(dataEntryInitiated);

						regionSancPost += Long.parseLong(totalSancPost);
						stateSancPost += Long.parseLong(totalSancPost);

						regionTotalEmpWorking += Long.parseLong(totalEmpWorking);
						stateTotalEmpWorking += Long.parseLong(totalEmpWorking);

						regionTotalEmpConfig += Long.parseLong(totalEmpConfig);
						stateTotalEmpConfig += Long.parseLong(totalEmpConfig);

						regionDraftEmp += Long.parseLong(draftEmp);
						stateDraftEmp += Long.parseLong(draftEmp);

						regionPendingEmp += Long.parseLong(pendingEmp);
						statePendingEmp += Long.parseLong(pendingEmp);

						regionApprovedEmp += Long.parseLong(approvedEmp);
						stateApprovedEmp += Long.parseLong(approvedEmp);

						regionPaybillGenerated += Long.parseLong(noOfPaybillGenerated);
						statePaybillGenerated += Long.parseLong(noOfPaybillGenerated);

						regionPaybillConsolidated += Long.parseLong(noOfPaybillConsolidated);
						statePaybillConsolidated += Long.parseLong(noOfPaybillConsolidated);

						regionEmpCovered += Long.parseLong(noOfEmployeesCovered);
						stateEmpCovered += Long.parseLong(noOfEmployeesCovered);

						regionPaybillForwarded += Long.parseLong(noOfPaybillForwarded);
						statePaybillForwarded += Long.parseLong(noOfPaybillForwarded);

						// added by Saurabh s
						regionEmpPayLocked += noOfEmpWhosePaybillLocked;
						stateEmpPayLocked += noOfEmpWhosePaybillLocked;

						regionPaybillLocked += noOfPaybillsLocked;
						statePaybillLocked += noOfPaybillsLocked;

						regionEmpPayGenerated += noOfEmpWhosePaybillGenerated;
						stateEmpPayGenerated += noOfEmpWhosePaybillGenerated;

						regionEmpPayForwarded += noOfEmpWhosePaybillForwarded;
						stateEmpPayForwarded += noOfEmpWhosePaybillForwarded;

						// ended by saurabh S

						// 1
						row = new ArrayList();
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(regionName);
						row.add(rowData);

						// 2
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(districtName);
						row.add(rowData);

						row.add(new URLData(adminName, urlPrefix + "&adminTpye=" + adminId + "&districtId=" + districtId
								+ "&month=" + month + "&year=" + year + "&regionCode=" + prvRegionCode));
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalSchoolConf);
						row.add(rowData);

						// 6
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalSchoolApproved);
						row.add(rowData);

						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalSancPost);
						row.add(rowData);
						// gLogger.info(" totalSancPost ******************* "+totalSancPost );
						// 9

						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalEmpWorking);
						row.add(rowData);

						// gLogger.info(" totalEmpWorking ******************* "+totalEmpWorking );

						// 10
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalEmpConfig);
						row.add(rowData);

						// 11
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(draftEmp);
						row.add(rowData);

						// 12
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(pendingEmp);
						row.add(rowData);

						// 13
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(approvedEmp);
						row.add(rowData);

						// 16
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillGenerated);
						row.add(rowData);

						// 17
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillForwarded);
						row.add(rowData);

						// 18
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillConsolidated);
						row.add(rowData);

						// 23
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillsLocked);
						row.add(rowData);

						// 24
						row.add(new URLData(noOfEmpWhosePaybillGenerated,
								urlPrefix1 + "&month=" + month + "&year=" + year + "&regionCode=" + regionCode
										+ "&districtId=" + districtId + "&adminType=" + adminId + "&statusFlag=" + "1"
										+ "&ReportType=" + "Master"));

						// 25
						row.add(new URLData(noOfEmpWhosePaybillForwarded,
								urlPrefix1 + "&month=" + month + "&year=" + year + "&regionCode=" + regionCode
										+ "&districtId=" + districtId + "&adminType=" + adminId + "&statusFlag=" + "2"
										+ "&ReportType=" + "Master"));

						// 19
						row.add(new URLData(noOfEmployeesCovered,
								urlPrefix1 + "&month=" + month + "&year=" + year + "&regionCode=" + regionCode
										+ "&districtId=" + districtId + "&adminType=" + adminId + "&statusFlag=" + "3"
										+ "&ReportType=" + "Master"));

						// 22

						row.add(new URLData(noOfEmpWhosePaybillLocked,
								urlPrefix1 + "&month=" + month + "&year=" + year + "&regionCode=" + regionCode
										+ "&districtId=" + districtId + "&adminType=" + adminId + "&statusFlag=" + "4"
										+ "&ReportType=" + "Master"));

						// ended by Saurabh s
						dataList.add(row);

					} else if (!prvRegionCode.equals(Long.parseLong(regionId))) {
						gLogger.info(" inside else  prvRegionCode *******#####************ ");

						// 1
						row = new ArrayList();
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData("");
						row.add(rowData);

						// 2
						if (prvRegionName != null) {
							rowData = new StyledData();
							rowData.setStyles(rowsVO);
							rowData.setData(prvRegionName + " Division Total");
							row.add(rowData);
						}

						else {
							rowData = new StyledData();
							rowData.setStyles(rowsVO);
							rowData.setData("Division Total");
							row.add(rowData);
						}

						// 3
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData("");
						row.add(rowData);

						// 5
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionTotalSchoolConfig);
						row.add(rowData);

						// 6
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionSchoolApproved);
						row.add(rowData);

						// 8
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionSancPost);
						row.add(rowData);

						// 9
						gLogger.info(" regionTotalEmpWorking mainReport ******************* " + regionTotalEmpWorking);
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionTotalEmpWorking);
						row.add(rowData);

						// 10
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionTotalEmpConfig);
						row.add(rowData);

						// 11
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionDraftEmp);
						row.add(rowData);

						// 12
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionPendingEmp);
						row.add(rowData);

						// 13
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionApprovedEmp);
						row.add(rowData);

						// 16
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionPaybillGenerated);
						row.add(rowData);

						// 17
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionPaybillForwarded);
						row.add(rowData);

						// 18
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionPaybillConsolidated);
						row.add(rowData);

						// added by Saurabh s
						// 23
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionPaybillLocked);
						row.add(rowData);

						// 24
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionEmpPayGenerated);
						row.add(rowData);

						// 25
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionEmpPayForwarded);
						row.add(rowData);

						// 19
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionEmpCovered);
						row.add(rowData);

						// 22
						// added by Saurabh s
						rowData = new StyledData();
						rowData.setStyles(rowsVO);
						rowData.setData(regionEmpPayLocked);
						row.add(rowData);

						// ended by Saurabh s
						dataList.add(row);

						prvRegionName = regionName;
						prvRegionCode = Long.parseLong(regionId);

						regionTotalSchoolConfig = Long.parseLong(totalSchoolConf);
						stateTotalSchoolConfig += Long.parseLong(totalSchoolConf);

						regionSchoolApproved = Long.parseLong(totalSchoolApproved);
						stateSchoolApproved += Long.parseLong(totalSchoolApproved);

						regionDataEntryInitiate = Long.parseLong(dataEntryInitiated);
						stateDataEntryInitiate += Long.parseLong(dataEntryInitiated);

						regionSancPost = Long.parseLong(totalSancPost);
						stateSancPost += Long.parseLong(totalSancPost);

						regionTotalEmpWorking = Long.parseLong(totalEmpWorking);
						stateTotalEmpWorking += Long.parseLong(totalEmpWorking);

						regionTotalEmpConfig = Long.parseLong(totalEmpConfig);
						stateTotalEmpConfig += Long.parseLong(totalEmpConfig);

						regionDraftEmp = Long.parseLong(draftEmp);
						stateDraftEmp += Long.parseLong(draftEmp);

						regionPendingEmp = Long.parseLong(pendingEmp);
						statePendingEmp += Long.parseLong(pendingEmp);

						regionApprovedEmp = Long.parseLong(approvedEmp);
						stateApprovedEmp += Long.parseLong(approvedEmp);

						regionPaybillGenerated = Long.parseLong(noOfPaybillGenerated);
						statePaybillGenerated += Long.parseLong(noOfPaybillGenerated);

						regionPaybillConsolidated = Long.parseLong(noOfPaybillConsolidated);
						statePaybillConsolidated += Long.parseLong(noOfPaybillConsolidated);

						regionEmpCovered = Long.parseLong(noOfEmployeesCovered);
						stateEmpCovered += Long.parseLong(noOfEmployeesCovered);

						regionPaybillForwarded = Long.parseLong(noOfPaybillForwarded);
						statePaybillForwarded += Long.parseLong(noOfPaybillForwarded);

						// added by Saurabh s
						regionEmpPayLocked = noOfEmpWhosePaybillLocked;
						stateEmpPayLocked += noOfEmpWhosePaybillLocked;

						regionPaybillLocked = noOfPaybillsLocked;
						statePaybillLocked += noOfPaybillsLocked;

						regionEmpPayGenerated = noOfEmpWhosePaybillGenerated;
						stateEmpPayGenerated += noOfEmpWhosePaybillGenerated;

						regionEmpPayForwarded = noOfEmpWhosePaybillForwarded;
						stateEmpPayForwarded += noOfEmpWhosePaybillForwarded;
						// ended by saurabh S

						// 1
						row = new ArrayList();
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(regionName);
						row.add(rowData);

						// 2
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(districtName);
						row.add(rowData);

						// 3

						row.add(new URLData(adminName, urlPrefix + "&adminTpye=" + adminId + "&districtId=" + districtId
								+ "&month=" + month + "&year=" + year + "&regionCode=" + prvRegionCode));

						// 5
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalSchoolConf);
						row.add(rowData);

						// 6
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalSchoolApproved);
						row.add(rowData);

						// 7

						// 8
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalSancPost);
						row.add(rowData);

						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalEmpWorking);
						row.add(rowData);

						// 10
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(totalEmpConfig);
						row.add(rowData);

						// 11
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(draftEmp);
						row.add(rowData);

						// 12
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(pendingEmp);
						row.add(rowData);

						// 13
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(approvedEmp);
						row.add(rowData);

						// 16
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillGenerated);
						row.add(rowData);

						// 17
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillForwarded);
						row.add(rowData);

						// 18
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillConsolidated);
						row.add(rowData);

						// added by Saurabh s
						// 23
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillsLocked);
						row.add(rowData);

						// 24
						row.add(new URLData(noOfEmpWhosePaybillGenerated,
								urlPrefix1 + "&month=" + month + "&year=" + year + "&regionCode=" + regionCode
										+ "&districtId=" + districtId + "&adminType=" + adminId + "&statusFlag=" + "1"
										+ "&ReportType=" + "Master"));

						// 25
						row.add(new URLData(noOfEmpWhosePaybillForwarded,
								urlPrefix1 + "&month=" + month + "&year=" + year + "&regionCode=" + regionCode
										+ "&districtId=" + districtId + "&adminType=" + adminId + "&statusFlag=" + "2"
										+ "&ReportType=" + "Master"));

						// 19
						row.add(new URLData(noOfEmployeesCovered,
								urlPrefix1 + "&month=" + month + "&year=" + year + "&regionCode=" + regionCode
										+ "&districtId=" + districtId + "&adminType=" + adminId + "&statusFlag=" + "3"
										+ "&ReportType=" + "Master"));

						// 22
						// added by Saurabh s
						row.add(new URLData(noOfEmpWhosePaybillLocked,
								urlPrefix1 + "&month=" + month + "&year=" + year + "&regionCode=" + regionCode
										+ "&districtId=" + districtId + "&adminType=" + adminId + "&statusFlag=" + "4"
										+ "&ReportType=" + "Master"));

						dataList.add(row);

					}
				}
				// 1
				row = new ArrayList();
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData("");
				row.add(rowData);

				// 2
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(prvRegionName + " Division Total");
				row.add(rowData);

				// 3
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData("");
				row.add(rowData);

				// 5
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionTotalSchoolConfig);
				row.add(rowData);

				// 6
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionSchoolApproved);
				row.add(rowData);

				// 7

				// 8
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionSancPost);
				row.add(rowData);

				// 9

				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionTotalEmpWorking);
				row.add(rowData);

				// 10
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionTotalEmpConfig);
				row.add(rowData);

				// 11
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionDraftEmp);
				row.add(rowData);

				// 12
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPendingEmp);
				row.add(rowData);

				// 13
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionApprovedEmp);
				row.add(rowData);

				// 16
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPaybillGenerated);
				row.add(rowData);

				// 17
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPaybillForwarded);
				row.add(rowData);

				// 18
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPaybillConsolidated);
				row.add(rowData);

				// added by Saurabh s
				// 23
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPaybillLocked);
				row.add(rowData);

				// 24
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionEmpPayGenerated);
				row.add(rowData);

				// 25
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionEmpPayForwarded);
				row.add(rowData);

				// 19
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionEmpCovered);
				row.add(rowData);

				// 22
				// added by Saurabh s
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionEmpPayLocked);
				row.add(rowData);

				// ended by Saurabh s

				dataList.add(row);

				if (Long.parseLong(regionCode) == 9) {
					// 1

					gLogger.info(" inside regionCode*******#####************ ");

					row = new ArrayList();
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData("");
					row.add(rowData);

					// 2
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData("State Total");
					row.add(rowData);

					// 3
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData("");
					row.add(rowData);

					// 5
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateTotalSchoolConfig.toString());
					row.add(rowData);

					// 6
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateSchoolApproved.toString());
					row.add(rowData);

					// 8
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateSancPost.toString());
					row.add(rowData);

					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateTotalEmpWorking.toString());
					row.add(rowData);

					// 10
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateTotalEmpConfig.toString());
					row.add(rowData);

					// 11
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateDraftEmp.toString());
					row.add(rowData);

					// 12
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(statePendingEmp.toString());
					row.add(rowData);

					// 13
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateApprovedEmp.toString());
					row.add(rowData);

					// 16
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(statePaybillGenerated.toString());
					row.add(rowData);

					// 17
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(statePaybillForwarded.toString());
					row.add(rowData);

					// 18
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(statePaybillConsolidated.toString());
					row.add(rowData);

					// added by Saurabh s
					// 23
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(statePaybillLocked);
					row.add(rowData);

					// 24
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateEmpPayGenerated);
					row.add(rowData);

					// 25
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateEmpPayForwarded);
					row.add(rowData);

					// 19
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateEmpCovered.toString());
					row.add(rowData);

					// 22
					// added by Saurabh s
					rowData = new StyledData();
					rowData.setStyles(rowsStateVO);
					rowData.setData(stateEmpPayLocked);
					row.add(rowData);

					// ended by Saurabh s
					dataList.add(row);
					gLogger.info(" inside dataList*******#####************ " + dataList.size());
				}

			}
		} catch (Exception e) {
			gLogger.info("findReportData(): Exception is" + e, e);
		}
		regionCode = null;
		districtCode = null;
		adminType = null;
		month = null;
		year = null;
		mainReport = null;
		row = null;
		rowData = null;
		return dataList;
	}

	private Map getPaybillGenerationReport(String regionCode, String districtCode, String adminType, String month,
			String year) throws SQLException {
		Map paybillGeneration = new HashMap();

		StringBuilder lSBQuery = new StringBuilder();

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		CallableStatement lClbStmnt = null;
		SessionFactory lObjSessionFactory = serviceLocator.getSessionFactorySlave();
		gLogger.info("Inside getPaybillGenerationReport ");
		gLogger.info("Inside regionCode " + regionCode);
		gLogger.info("Inside districtCode " + districtCode);
		gLogger.info("Inside adminType " + adminType);
		gLogger.info("Inside month " + month);
		gLogger.info("Inside year " + year);
		try {
			if (Long.parseLong(regionCode) != 9) {
				con = lObjSessionFactory.getCurrentSession().connection();
				smt = con.createStatement();
				lSBQuery.append("{call masterReportDataPaybillConfig(?,?,?)}");
				lClbStmnt = con.prepareCall(lSBQuery.toString());
				lClbStmnt.setString(1, regionCode);
				lClbStmnt.setLong(2, Long.parseLong(month));
				lClbStmnt.setLong(3, Long.parseLong(year));
				rs = lClbStmnt.executeQuery();

				while (rs.next()) {
					paybillGeneration.put(
							rs.getString(1).toString() + "~" + rs.getString(2).toString() + "~"
									+ rs.getString(3).toString(),
							rs.getString(4).toString() + "~" + rs.getString(5).toString() + "~"
									+ rs.getString(6).toString() + "~" + rs.getString(7).toString() + "~"
									+ rs.getString(8).toString() + "~" + rs.getString(9).toString() + "~"
									+ rs.getString(10).toString() + "~" + rs.getString(11).toString());
				}
			}

			else {
				for (int i = 1; i <= 8; i++) {
					lSBQuery = new StringBuilder();
					con = lObjSessionFactory.getCurrentSession().connection();
					smt = con.createStatement();
					lSBQuery.append("{call masterReportDataPaybillConfig(?,?,?)}");
					lClbStmnt = con.prepareCall(lSBQuery.toString());
					lClbStmnt.setString(1, i + "");
					lClbStmnt.setLong(2, Long.parseLong(month));
					lClbStmnt.setLong(3, Long.parseLong(year));
					rs = lClbStmnt.executeQuery();

					while (rs.next()) {

						// paybillGeneration.put(rs.getString(1).toString()+"~"+rs.getString(2).toString()+"~"+rs.getString(3).toString(),
						// rs.getString(4).toString()+"~"+rs.getString(5).toString()+"~"+rs.getString(6).toString()+"~"+rs.getString(7).toString());
						paybillGeneration.put(
								rs.getString(1).toString() + "~" + rs.getString(2).toString() + "~"
										+ rs.getString(3).toString(),
								rs.getString(4).toString() + "~" + rs.getString(5).toString() + "~"
										+ rs.getString(6).toString() + "~" + rs.getString(7).toString() + "~"
										+ rs.getString(8).toString() + "~" + rs.getString(9).toString() + "~"
										+ rs.getString(10).toString() + "~" + rs.getString(11).toString());
					}
				}
			}
			gLogger.info("map emp values " + paybillGeneration);
		} catch (Exception e) {
			gLogger.error("Exception in getPaybillGenerationReport " + e.getMessage());
		} finally {
			if (smt != null) {
				smt.close();
			}

			if (con != null) {
				con.close();
			}

			smt = null;
			con = null;
		}
		return paybillGeneration;
	}

	private Map getEmployeeConfReport(String regionCode, String districtCode, String adminType) throws SQLException {
		Map empConf = new HashMap();
		StringBuilder lSBQuery = new StringBuilder();

		/*
		 * lSBQuery.
		 * append(" SELECT dist.REGION_CODE,dist.DISTRICT_ID,admin.id,count(distinct emp.ddo_code) as data_entry,"
		 * ); lSBQuery.
		 * append(" count(case when emp.ZP_STATUS in (-1,0,2,3,4,10) then 1 else null end ) as Total_EMployee,"
		 * ); lSBQuery.
		 * append(" count(case when emp.ZP_STATUS in (-1,0) then 1 else null end ) as draft,"
		 * ); lSBQuery.
		 * append(" count(case when emp.ZP_STATUS in (2,3,4) then 1 else null end ) as pending,"
		 * ); lSBQuery.
		 * append(" count(case when emp.ZP_STATUS =10 then 1 else null end ) as approved"
		 * ); lSBQuery.append(" FROM rlt_zp_ddo_map rlt");
		 * lSBQuery.append(" inner join ORG_DDO_MST ddo on rlt.zp_ddo_code=ddo.ddo_code"
		 * ); lSBQuery.
		 * append(" inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code and upper(off.ddo_office)='YES'"
		 * ); lSBQuery.
		 * append(" inner join mst_dcps_emp emp on emp.ddo_code=rlt.zp_ddo_code");
		 * lSBQuery.
		 * append(" inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID");
		 * lSBQuery.
		 * append(" inner join ZP_REGION_NAME_MST region on dist.REGION_CODE=region.REGION_CODE"
		 * ); lSBQuery.
		 * append(" INNER join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.ID");
		 * lSBQuery.append(" where rlt.STATUS <>-2"); if(Long.parseLong(regionCode)!=-1
		 * && Long.parseLong(regionCode)!=9){
		 * lSBQuery.append(" and region.REGION_CODE='"+regionCode+"'"); }
		 * if(Long.parseLong(districtCode)!=-1){
		 * lSBQuery.append(" and dist.DISTRICT_ID="+districtCode+""); }
		 * if(Long.parseLong(adminType)!=-1){
		 * lSBQuery.append(" and admin.id="+adminType+""); } lSBQuery.
		 * append(" group by dist.REGION_CODE,dist.DISTRICT_ID,admin.id,dist.REGION_ORDER"
		 * ); lSBQuery.append(" order by dist.REGION_CODE,dist.REGION_ORDER,admin.id");
		 * 
		 * Session ghibSession =
		 * ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession
		 * (); SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		 * gLogger.info("Query is "+lSBQuery.toString());
		 * if(Long.parseLong(regionCode)!=-1 && Long.parseLong(regionCode)!=9){
		 * Query.setLong("regionCode", Long.valueOf(regionCode)); }
		 * if(Long.parseLong(districtCode)!=-1){ Query.setLong("districtCode",
		 * Long.valueOf(districtCode)); } if(Long.parseLong(adminType)!=-1){
		 * Query.setLong("adminType", Long.valueOf(adminType)); }
		 * 
		 * lLstFinal = Query.list(); if(lLstFinal!=null)
		 * gLogger.info("+lLstFinal size is "+lLstFinal.size()); Iterator itr =
		 * lLstFinal.iterator(); Object[] obj = null; while(itr.hasNext()){ obj =
		 * (Object[]) itr.next();
		 * empConf.put(obj[0].toString()+"~"+obj[1].toString()+"~"+obj[2].toString(),
		 * obj[3].toString()+"~"+obj[4].toString()+"~"+obj[5].toString()+"~"+obj[6].
		 * toString()+"~"+obj[7].toString()); } gLogger.info("map emp values "+empConf);
		 * obj=null; lLstFinal=null; itr=null;
		 */

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		CallableStatement lClbStmnt = null;
		SessionFactory lObjSessionFactory = serviceLocator.getSessionFactorySlave();
		gLogger.info("Inside getEmployeeConfReport ");
		try {
			con = lObjSessionFactory.getCurrentSession().connection();
			smt = con.createStatement();
			lSBQuery.append("{call masterReportDataEmpConfigFinal(?)}");
			lClbStmnt = con.prepareCall(lSBQuery.toString());
			gLogger.info("lClbStmnt hiiiiiiii " + lSBQuery.toString());
			lClbStmnt.setString(1, regionCode);
			rs = lClbStmnt.executeQuery();

			while (rs.next()) {
				empConf.put(
						rs.getString(1).toString() + "~" + rs.getString(2).toString() + "~"
								+ rs.getString(3).toString(),
						rs.getString(4).toString() + "~" + rs.getString(5).toString() + "~" + rs.getString(6).toString()
								+ "~" + rs.getString(7).toString() + "~" + rs.getString(8).toString());
			}
			gLogger.info("map emp values " + empConf);
		} catch (Exception e) {
			gLogger.error("Exception in getEmployeeConfReport " + e.getMessage());
		} finally {
			if (smt != null) {
				smt.close();
			}

			if (con != null) {
				con.close();
			}

			smt = null;
			con = null;
		}
		return empConf;
	}

	private ResultSet getMainTotal(String regionCode, String districtCode, String adminType) throws SQLException {
		StringBuilder lSBQuery = new StringBuilder();

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		CallableStatement lClbStmnt = null;
		SessionFactory lObjSessionFactory = serviceLocator.getSessionFactorySlave();
		gLogger.info("Inside getMainTotal ");
		try {
			con = lObjSessionFactory.getCurrentSession().connection();
			smt = con.createStatement();
			lSBQuery.append("{call masterReportData(?)}");
			lClbStmnt = con.prepareCall(lSBQuery.toString());
			lClbStmnt.setString(1, regionCode);
			rs = lClbStmnt.executeQuery();
		} catch (Exception e) {
			gLogger.error("Exception in getMainTotal " + e.getMessage());
		} finally {
			if (smt != null) {
				smt.close();
			}

			if (con != null) {
				con.close();
			}

			smt = null;
			con = null;
		}
		return rs;
	}

	public List getListOfRegion(String lStrLangId, String lStrLocId) {

		List<Object> regionList = new ArrayList<Object>();
		try {
			Session lObjSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT REGION_CODE,REGION_NAME FROM ZP_REGION_NAME_MST");

			Query lObjQuery = lObjSession.createSQLQuery(sb.toString());

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("9");
			lObjComboValuesVO.setDesc("All Regions");
			regionList.add(lObjComboValuesVO);
			Object[] lArrData = null;

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					regionList.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}

		return regionList;
	}

	public List getDistrictForRegion(String selecttedRegion, Hashtable otherArgs, String lStrLangId,
			String lStrLocCode) {
		Hashtable sessionKeys = (Hashtable) (otherArgs).get(IReportConstants.SESSION_KEYS);
		Map loginMap = (Map) sessionKeys.get("loginDetailsMap");

		List<Object> districtListForRegion = new ArrayList<Object>();

		try {
			Session lObjSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();

			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer
					.append("SELECT DISTRICT_ID,DISTRICT_NAME FROM CMN_DISTRICT_MST WHERE STATE_ID=15 AND LANG_ID=1 ");
			if (Long.parseLong(selecttedRegion) != 9) {
				queryBuffer.append(" AND REGION_CODE=:selecttedRegion ");
			}
			queryBuffer.append(" ORDER BY DISTRICT_NAME ");

			Query lObjQuery = lObjSession.createSQLQuery(queryBuffer.toString());
			if (Long.parseLong(selecttedRegion) != 9) {
				lObjQuery.setString("selecttedRegion", selecttedRegion);
			}

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			Object[] lArrData = null;

			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("----- Select -----");
			districtListForRegion.add(lObjComboValuesVO);

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					districtListForRegion.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}

		return districtListForRegion;
	}

	public List getAdminTypeList(String lStrLangId, String lStrLocId) {

		List<Object> adminTypeList = new ArrayList<Object>();
		try {
			Session lObjSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT ID, ADMIN_NAME FROM ZP_ADMIN_NAME_MST  order by ADMIN_ORDER asc");

			Query lObjQuery = lObjSession.createSQLQuery(sb.toString());

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			Object[] lArrData = null;

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					adminTypeList.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}

		return adminTypeList;
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
			int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
			Session lObjSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();

			String lStrBufLang = "SELECT finYearCode, finYearCode FROM SgvcFinYearMst WHERE langId = :langId and finYearCode BETWEEN '2007' AND "
					+ CurrentYear + " ORDER BY finYearCode";

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
}
