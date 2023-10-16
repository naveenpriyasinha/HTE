package com.tcs.sgv.reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
 * @author Saurabh Shejul;
 * @version 0.1
 * @since JDK 7.0 May 25,2018
 */


public class EmployeeWiseSalaryReportDAO extends DefaultReportDataFinder {
	
	private static final Logger gLogger = Logger.getLogger("EmployeeWiseSalaryReportDAO");
	public static String newline = System.getProperty("line.separator");
	ServiceLocator serviceLocator = null;
	SessionFactory gObjSessionFactory = null;
	String gStrLocCode = null;
	Long gLngLangId = null;
	Long gLngPostId = null;
	Map lMapSeriesHeadCode = null;
	Map requestAttributes = null;
	Session ghibSession = null;
	
	public Collection findReportData(ReportVO report, Object criteria) throws ReportException{
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
		
		String month=null;
		String year=null;
		String regionCode=null;
		String adminId=null;
		String districtId=null;
		String ReportType = null;
		String ddoCode = null;
		
		List employeeReport = null;
		ArrayList row = null;
		StyledData rowData = null;
		
		
    try{
			
			month = (String)report.getParameterValue("month");
			year = (String)report.getParameterValue("year");
			regionCode = (String)report.getParameterValue("regionCode");
			adminId = (String)report.getParameterValue("adminType");
			districtId = (String)report.getParameterValue("districtId");
			ReportType = (String)report.getParameterValue("reportType");
			ddoCode = (String)report.getParameterValue("ddoCode");
			gLogger.info("month------" + month);
			gLogger.info("year------" + year);
			gLogger.info("regionCode------" + regionCode);
			gLogger.info("districtId------" + districtId);
			gLogger.info("adminId------" + adminId);
			
			
			if(ReportType.equalsIgnoreCase("Region"))
			{
			 employeeReport= getRegionEmpSalaryReport(month, year, regionCode, districtId, adminId);
			}
			
			else if(ReportType.equalsIgnoreCase("Admin"))
			{
			employeeReport= getAdminEmpSalaryReport(month, year, regionCode, districtId, adminId,ddoCode);	
			}
			
			
			Iterator itr = employeeReport.iterator();
			Object[] obj = null;
			
			    Long count = 1l;
			    String employeeName=null;
				String sevaarthId=null;
				String grossAmount=null;
				String netAmount=null;
				String voucherNo=null;
				String voucherDate=null;
				
				
				while(itr.hasNext())
				{
					
					obj= (Object[]) itr.next();
					
					employeeName= obj[0].toString();
					sevaarthId= obj[1].toString();
					grossAmount = obj[2].toString();
					netAmount= obj[3].toString();
					voucherNo = obj[4].toString();
					voucherDate= obj[5].toString();
					
					
					
					//1
					row = new ArrayList();
					
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(count);
					row.add(rowData);
					
					
					//2
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(employeeName);
					row.add(rowData);

					//3
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(sevaarthId);
					row.add(rowData);

					//4
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(grossAmount);
					row.add(rowData);


					//5
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(netAmount);
					row.add(rowData);
					
					//6
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(voucherNo);
					row.add(rowData);


					//7
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(voucherDate);
					row.add(rowData);
					
					dataList.add(row);
					count++;

}
				employeeReport=null;
    }
    catch (Exception e) {
	gLogger.info("findReportData(): Exception is" + e, e);
}
return dataList; 

}
	
	private List getRegionEmpSalaryReport(String month,String year,String regionCode,String districtId,String adminId) {
		List lLstFinal =null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL,pay.VOUCHER_NO,to_char(pay.VOUCHER_DATE,'DD-MM-YYYY')  ");
		lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");
		lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
		lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID ");
		lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
		lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE ");
		lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1) ");
		lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE ");
		lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE ");
		lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES'  ");
		lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID ");
		lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE ");
		lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID ");
		lSBQuery.append(" where pay.APPROVE_FLAG= 1 and pay.STATUS= 4  ");
		lSBQuery.append(" and pay.PAYBILL_YEAR = "+year+"  and pay.PAYBILL_MONTH= "+month+" ");
		lSBQuery.append(" and DIST.DISTRICT_ID= "+districtId+"  and ADMIN.ADMIN_CODE= "+adminId+" AND REGION.REGION_CODE = "+regionCode+" ");
		lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL, pay.VOUCHER_NO,pay.VOUCHER_DATE  ");
		
		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is is is "+lSBQuery.toString());
		lLstFinal = Query.list();
		return lLstFinal;
	}
	
	private List getAdminEmpSalaryReport(String month,String year,String regionCode,String districtId,String adminId, String ddoCode) {
		List lLstFinal =null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL,pay.VOUCHER_NO,to_char(pay.VOUCHER_DATE,'DD-MM-YYYY')  ");
		lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");
		lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
		lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID ");
		lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID ");
		lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE ");
		lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1) ");
		lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE ");
		lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE ");
		lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES'  ");
		lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID ");
		lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE ");
		lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID ");
		lSBQuery.append(" where pay.APPROVE_FLAG= 1 and pay.STATUS= 4  ");
		lSBQuery.append(" and pay.PAYBILL_YEAR = "+year+"  and pay.PAYBILL_MONTH= "+month+" and ddo.DDO_CODE= "+ddoCode+" ");
		lSBQuery.append(" and DIST.DISTRICT_ID= "+districtId+"  and ADMIN.ADMIN_CODE= "+adminId+" AND REGION.REGION_CODE = "+regionCode+" ");
		lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL, pay.VOUCHER_NO,pay.VOUCHER_DATE  ");
		
		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is is is "+lSBQuery.toString());
		lLstFinal = Query.list();
		return lLstFinal;
	}

}
