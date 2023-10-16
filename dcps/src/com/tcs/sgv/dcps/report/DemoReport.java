package com.tcs.sgv.dcps.report;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import com.tcs.sgv.dcps.dao.OfflineContriDAO;
import com.tcs.sgv.dcps.dao.OfflineContriDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;

public class DemoReport extends DefaultReportDataFinder implements ReportDataFinder {
	
	
	private static final Logger gLogger = Logger.getLogger(PrintFormR2ConsolidatedReport.class);
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";
	public static String newline = System.getProperty("line.separator");

	Map requestAttributes = null;
	ServiceLocator serviceLocator = null;
	SessionFactory lObjSessionFactory = null;
	
	Statement smt = null;

	Connection con = null;
	public Collection findReportData(ReportVO report, Object criteria) throws ReportException {
		
		requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		lObjSessionFactory = serviceLocator.getSessionFactorySlave();

		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		SessionFactory lObjSessionFactory = serviceLocator.getSessionFactorySlave();
		con = lObjSessionFactory.getCurrentSession().connection();
		try {
			smt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new StringBuffer();
		StyleVO[] rowsFontsVO = new StyleVO[5];
		rowsFontsVO[0] = new StyleVO();
		rowsFontsVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
		rowsFontsVO[0].setStyleValue("26");
		rowsFontsVO[1] = new StyleVO();
		rowsFontsVO[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		rowsFontsVO[1].setStyleValue("10");
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
		ArrayList dataListForTable = new ArrayList();
		List td=new ArrayList();
		if (report.getReportCode().equals("9000295"))
		{
			String lStrYearId = (String) report.getParameterValue("yearId");
			String lStrMonthId = (String) report.getParameterValue("monthId");
			System.out.println("the value of string id is"+lStrYearId);
			System.out.println("the value of string id is"+lStrMonthId);
			
			StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append(" select region.REGION_NAME, region.REGION_CODE, dist.district_name, dist.DISTRICT_ID, admin.ADMIN_NAME, admin.ADMIN_CODE,");
			lSBQuery.append(" nvl(sum(case when cons.STATUS = 0 then head.NO_OF_EMP else NULL end),0) as employ_in_cons_created_bill,");
			lSBQuery.append(" nvl(sum(case when cons.STATUS = 0 then head.BILL_GROSS_AMT else NULL end),0) as gross_amount_for_cons_bill,");
			lSBQuery.append(" nvl(sum(case when cons.STATUS = 0 then head.BILL_NET_AMOUNT else NULL end),0) as net_amount_for_cons_bill,");
			lSBQuery.append(" nvl(sum(case when cons.STATUS = 5 then head.NO_OF_EMP  else NULL end),0) as employ_in_cons_fwd_bill,");
			lSBQuery.append(" nvl(sum(case when cons.STATUS = 5 then head.BILL_GROSS_AMT  else NULL end),0) as gross_amount_for_fwd_cons_bill,");
			lSBQuery.append(" nvl(sum(case when cons.STATUS = 5 then head.BILL_NET_AMOUNT  else NULL end),0) as net_amount_for_fwd_cons_bill");
			lSBQuery.append(" from RLT_ZP_DDO_MAP zp inner join org_ddo_mst ddo on zp.ZP_DDO_CODE = ddo.DDO_CODE and zp.status in (0,1)");
			lSBQuery.append(" inner join paybill_head_mpg head on head.loc_id = ddo.location_code");
			lSBQuery.append(" inner join rlt_ddo_org rlt on rlt.ddo_code = ddo.ddo_code");
			lSBQuery.append(" inner join cmn_location_mst loc on loc.location_code = rlt.location_code");
			lSBQuery.append(" inner join cmn_district_mst dist on dist.DISTRICT_ID = loc.loc_district_id");
			lSBQuery.append(" inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE = ddo.ddo_type");
			lSBQuery.append(" inner join ZP_REGION_NAME_MST region on region.REGION_CODE = dist.REGION_CODE");
			lSBQuery.append(" inner join consolidated_bill_mpg consmpg on consmpg.paybill_id = head.paybill_id");
			lSBQuery.append(" inner join consolidated_bill_mst cons on cons.cons_bill_id = consmpg.cons_bill_id");
			lSBQuery.append(" where head.PAYBILL_MONTH =:month and head.PAYBILL_YEAR =:year");
			lSBQuery.append(" group by region.REGION_NAME, region.REGION_CODE, dist.district_name,dist.DISTRICT_ID, admin.ADMIN_NAME, admin.ADMIN_CODE");

			Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
			SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
			Query.setLong("month", Long.valueOf(lStrMonthId));
			Query.setLong("year", Long.valueOf(lStrYearId));
			List lLstFinal = Query.list();
			
			
			
			StringBuilder lSBQuery1 = new StringBuilder();
			lSBQuery1.append(" select region.REGION_NAME, region.REGION_CODE, dist.district_name,dist.DISTRICT_ID, admin.ADMIN_NAME, admin.ADMIN_CODE,");
			lSBQuery1.append(" nvl(sum(case when head.APPROVE_FLAG = 1 then head.NO_OF_EMP else NULL end),0) as employ_in_aprov_bill,");
			lSBQuery1.append(" nvl(sum(case when head.APPROVE_FLAG = 1 then head.BILL_GROSS_AMT else NULL end),0) as gross_amount_for_aprov_bill,");
			lSBQuery1.append(" nvl(sum(case when head.APPROVE_FLAG = 1 then head.BILL_NET_AMOUNT else NULL end),0) as net_amount_for_aprov_bill");
			lSBQuery1.append(" from RLT_ZP_DDO_MAP zp inner join org_ddo_mst ddo on zp.ZP_DDO_CODE = ddo.DDO_CODE and zp.status in (0,1)");
			lSBQuery1.append(" inner join paybill_head_mpg head on head.loc_id = ddo.location_code");
			lSBQuery1.append(" inner join rlt_ddo_org rlt on rlt.ddo_code = ddo.ddo_code");
			lSBQuery1.append(" inner join cmn_location_mst loc on loc.location_code = rlt.location_code");
			lSBQuery1.append(" inner join cmn_district_mst dist on dist.DISTRICT_ID = loc.loc_district_id");
			lSBQuery1.append(" inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE = ddo.ddo_type");
			lSBQuery1.append(" inner join ZP_REGION_NAME_MST region on region.REGION_CODE = dist.REGION_CODE");
			lSBQuery1.append(" where head.PAYBILL_MONTH =:month and head.PAYBILL_YEAR =:year");
			lSBQuery1.append(" group by region.REGION_NAME, region.REGION_CODE, dist.district_name,dist.DISTRICT_ID, admin.ADMIN_NAME, admin.ADMIN_CODE");
			Session ghibSession1 = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
			SQLQuery Query1= ghibSession1.createSQLQuery(lSBQuery1.toString());
			Query1.setLong("month", Long.valueOf(lStrMonthId));
			Query1.setLong("year", Long.valueOf(lStrYearId));
			List lLstFinal1 = Query1.list();
			System.out.println("the value of dao in second query"+lLstFinal1.size());
			Object objRejected[];
		if (lLstFinal != null && !lLstFinal.isEmpty())
			{
				Iterator it = lLstFinal.iterator();

				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					td = new ArrayList();
					if (tuple[0] != null) // name
					{
						td.add(tuple[0].toString());
					} else {
						td.add("");
					}
					if (tuple[2] != null) // name
					{
						td.add(tuple[2].toString());
					} else {
						td.add("");
					}
					if (tuple[4] != null) // name
					{
						td.add(tuple[4].toString());
					} else {
						td.add("");
					}
					if (tuple[6] != null) // name
					{
						td.add(tuple[6].toString());
					} else {
						td.add("");
					}	
					if (tuple[7] != null) // name
					{
						td.add(tuple[7].toString());
					} else {
						td.add("");
					}
					if (tuple[8] != null) // name
					{
						td.add(tuple[8].toString());
					} else {
						td.add("");
					}
					if (tuple[9] != null) // name
					{
						td.add(tuple[9].toString());
					} else {
						td.add("");
					}
					if (tuple[10] != null) // name
					{
						td.add(tuple[10].toString());
					} else {
						td.add("");
					}
					if (tuple[11] != null) // name
					{
						td.add(tuple[11].toString());
					} else {
						td.add("");
					}
						if (lLstFinal1 != null && !lLstFinal1.isEmpty()){
							Iterator it1 = lLstFinal1.iterator();
							while (it1.hasNext()) {
								 objRejected = (Object[])it1.next();
								 
								 if((objRejected[1].equals(tuple[1])) && (objRejected[3].equals(tuple[3])) &&(objRejected[5].equals(tuple[5]))){
										td.add(objRejected[6].toString());
										td.add(objRejected[7].toString());
										td.add(objRejected[8].toString());
										break;
									}
							}
						
							}
					
					
					
					
					
					
					dataListForTable.add(td);
					}

			
			}
		}
		
		
		return dataListForTable;
		
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

			String lStrBufLang = "SELECT finYearCode, finYearCode FROM SgvcFinYearMst WHERE langId = :langId and finYearCode BETWEEN '2007' AND '2016' ORDER BY finYearCode";

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
