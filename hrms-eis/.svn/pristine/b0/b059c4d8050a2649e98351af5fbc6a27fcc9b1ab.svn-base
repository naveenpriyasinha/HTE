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
 * @since JDK 7.0 May 07,2018
 */

public class EmployeeWiseReportDAO extends DefaultReportDataFinder {
	
	private static final Logger gLogger = Logger.getLogger("EmployeeWiseReportDAO");
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
		String statusFlag = null;
		String ReportType = null;
		String ddoCode = null;
		
		List employeeReport = null;
		List row = null;
		StyledData rowData = null;
		
		try{
			
			month = (String)report.getParameterValue("month");
			year = (String)report.getParameterValue("year");
			regionCode = (String)report.getParameterValue("regionCode");
			adminId = (String)report.getParameterValue("adminType");
			districtId = (String)report.getParameterValue("districtId");
			statusFlag = (String)report.getParameterValue("statusFlag");
			ReportType = (String)report.getParameterValue("ReportType");
			ddoCode = (String)report.getParameterValue("ddoCode");
			gLogger.info("month------" + month);
			gLogger.info("year------" + year);
			gLogger.info("regionCode------" + regionCode);
			gLogger.info("districtId------" + districtId);
			gLogger.info("adminId------" + adminId);
			gLogger.info("adminId------" + statusFlag);
			
			if(ReportType.equalsIgnoreCase("Master"))
			{
			 employeeReport= getEmployeeData(month, year, regionCode, districtId, adminId, statusFlag);
			}
			
			else if(ReportType.equalsIgnoreCase("Admin"))
			{
			employeeReport= getEmployeeData1(month, year, regionCode, districtId, adminId, statusFlag,ddoCode);	
			}
			
			
			
			Iterator itr = employeeReport.iterator();
			Object[] obj = null;
			
			    String employeeName=null;
				String sevaarthId=null;
				String grossAmount=null;
				String netAmount=null;
				
				while(itr.hasNext())
				{
					
					obj= (Object[]) itr.next();
					
					employeeName= obj[0].toString();
					sevaarthId= obj[1].toString();
					grossAmount = obj[2].toString();
					netAmount= obj[3].toString();
					
					
					
					//1
					row = new ArrayList();
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(employeeName);
					row.add(rowData);

					//2
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(sevaarthId);
					row.add(rowData);

					//3
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(grossAmount);
					row.add(rowData);


					//4
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(netAmount);
					row.add(rowData);
					
					dataList.add(row);


				}
				employeeReport=null;

		} catch(Exception e){
			gLogger.info("findReportData(): Exception is" + e, e);
			
		}
		
		adminId = null;
		districtId=null;
		regionCode=null;
		row = null;
		rowData = null;
		
		return dataList;
		
	}
	
	
	private List getEmployeeData(String month,String year,String regionCode,String districtId,String adminId, String statusFlag )
	
	{

		List emplList=null;
		StringBuilder lSBQuery = new StringBuilder();
		
		gLogger.info("month"+month);
		gLogger.info("year is "+year);
		gLogger.info("regionCode is "+regionCode);
		gLogger.info("districtId is "+districtId);
		gLogger.info("adminId is "+adminId);
		gLogger.info("statusFlag is "+statusFlag);

		
		if(statusFlag.equalsIgnoreCase("1") )
		{
			gLogger.info("statusFlag0");
			lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL  ");
			lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");  
			lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
			lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID");
			lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID");
			lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE");
			lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1)");
			lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
			lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE");
			lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES' ");
			lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
			lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
			lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
			lSBQuery.append(" where pay.APPROVE_FLAG= 0 and pay.STATUS= 0 ");
			lSBQuery.append(" and pay.PAYBILL_YEAR ="+year+" and pay.PAYBILL_MONTH="+month+" ");
			lSBQuery.append(" and DIST.DISTRICT_ID="+districtId+" and ADMIN.ADMIN_CODE= "+adminId+" ");
			lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL");
			
			}
		
		else if(statusFlag.equalsIgnoreCase("2"))
		{
			gLogger.info("statusFlag1");
			lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL ");
			lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");
			lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
			lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID");
			lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID");
			lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE");
			lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1)");
			lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
			lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE");
			lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES' ");
			lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
			lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
			lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
			lSBQuery.append(" where pay.APPROVE_FLAG= 0 and pay.STATUS= 2 ");
			lSBQuery.append(" and pay.PAYBILL_YEAR ="+year+" and pay.PAYBILL_MONTH="+month+" ");
			lSBQuery.append(" and DIST.DISTRICT_ID="+districtId+" and ADMIN.ADMIN_CODE= "+adminId+" ");
			lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL");
			
			
			
			}
		
		else if(statusFlag.equalsIgnoreCase("3"))
		{
			gLogger.info("statusFlag2");
			lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL ");
			lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");
			lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
			lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID");
			lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID");
			lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE");
			lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1)");
			lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
			lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE");
			lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES' ");
			lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
			lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
			lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
			lSBQuery.append(" where pay.APPROVE_FLAG IN(0,1) and pay.STATUS= 4 ");
			lSBQuery.append(" and pay.PAYBILL_YEAR ="+year+" and pay.PAYBILL_MONTH="+month+" ");
			lSBQuery.append(" and DIST.DISTRICT_ID="+districtId+" and ADMIN.ADMIN_CODE= "+adminId+" ");
			lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL");
			
			}
		
		else if(statusFlag.equalsIgnoreCase("4"))
		{
			gLogger.info("statusFlagelse");			
			lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL ");
			lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");
			lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
			lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID");
			lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID");
			lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE");
			lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1)");
			lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
			lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE");
			lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES' ");
			lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
			lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
			lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
			lSBQuery.append(" where pay.APPROVE_FLAG= 1 and pay.STATUS= 4 ");
			lSBQuery.append(" and pay.PAYBILL_YEAR ="+year+" and pay.PAYBILL_MONTH="+month+" ");
			lSBQuery.append(" and DIST.DISTRICT_ID="+districtId+" and ADMIN.ADMIN_CODE= "+adminId+" ");
			lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL");
			
			}

		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		Query Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is "+lSBQuery.toString());
		emplList = Query.list();
		return emplList;
	}
	
	
private List getEmployeeData1(String month,String year,String regionCode,String districtId,String adminId, String statusFlag,String ddoCode )
	
	{

		List emplList1=null;
		StringBuilder lSBQuery = new StringBuilder();
		
		gLogger.info("month"+month);
		gLogger.info("year is "+year);
		gLogger.info("regionCode is "+regionCode);
		gLogger.info("districtId is "+districtId);
		gLogger.info("adminId is "+adminId);
		gLogger.info("statusFlag is "+statusFlag);
		gLogger.info("ddoCode is "+ddoCode);


		
		if(statusFlag.equalsIgnoreCase("1") )
		{
			gLogger.info("statusFlag0");
			lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL  ");
			lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");  
			lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
			lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID");
			lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID");
			lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE");
			lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1)");
			lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
			lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE");
			lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES' ");
			lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
			lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
			lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
			lSBQuery.append(" where pay.APPROVE_FLAG= 0 and pay.STATUS= 0 ");
			lSBQuery.append(" and pay.PAYBILL_YEAR ="+year+" and pay.PAYBILL_MONTH="+month+" ");
			lSBQuery.append(" and DIST.DISTRICT_NAME= '"+districtId+"' and ADMIN.ADMIN_CODE= "+adminId+" and ddo.DDO_CODE= "+ddoCode+" ");
			lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL");
			
			}
		
		else if(statusFlag.equalsIgnoreCase("2"))
		{
			gLogger.info("statusFlag1");
			lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL ");
			lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");
			lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
			lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID");
			lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID");
			lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE");
			lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1)");
			lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
			lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE");
			lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES' ");
			lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
			lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
			lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
			lSBQuery.append(" where pay.APPROVE_FLAG= 0 and pay.STATUS= 2 ");
			lSBQuery.append(" and pay.PAYBILL_YEAR ="+year+" and pay.PAYBILL_MONTH="+month+" ");
			lSBQuery.append(" and DIST.DISTRICT_NAME= '"+districtId+"' and ADMIN.ADMIN_CODE= "+adminId+" and ddo.DDO_CODE= "+ddoCode+" ");
			lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL");
			
			
			
			}
		
		else if(statusFlag.equalsIgnoreCase("3"))
		{
			gLogger.info("statusFlag2");
			lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL ");
			lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");
			lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
			lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID");
			lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID");
			lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE");
			lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1)");
			lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
			lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE");
			lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES' ");
			lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
			lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
			lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
			lSBQuery.append(" where pay.APPROVE_FLAG IN(0,1) and pay.STATUS= 4 ");
			lSBQuery.append(" and pay.PAYBILL_YEAR ="+year+" and pay.PAYBILL_MONTH="+month+" ");
			lSBQuery.append(" and DIST.DISTRICT_NAME= '"+districtId+"' and ADMIN.ADMIN_CODE= "+adminId+" and ddo.DDO_CODE= "+ddoCode+" ");
			lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL");
			
			}
		
		else if(statusFlag.equalsIgnoreCase("4"))
		{
			gLogger.info("statusFlagelse");			
			lSBQuery.append(" SELECT emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL ");
			lSBQuery.append(" FROM PAYBILL_HEAD_MPG pay ");
			lSBQuery.append(" inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID = paybill.PAYBILL_GRP_ID");
			lSBQuery.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = paybill.EMP_ID");
			lSBQuery.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID");
			lSBQuery.append(" inner join ORG_DDO_MST ddo on pay.LOC_ID =ddo.LOCATION_CODE");
			lSBQuery.append(" AND PAY.APPROVE_FLAG IN(0,1)");
			lSBQuery.append(" inner join RLT_ZP_DDO_MAP rlt on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
			lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE");
			lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE)='YES' ");
			lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
			lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
			lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
			lSBQuery.append(" where pay.APPROVE_FLAG= 1 and pay.STATUS= 4 ");
			lSBQuery.append(" and pay.PAYBILL_YEAR ="+year+" and pay.PAYBILL_MONTH="+month+" ");
			lSBQuery.append(" and DIST.DISTRICT_NAME= '"+districtId+"' and ADMIN.ADMIN_CODE= "+adminId+" and ddo.DDO_CODE= "+ddoCode+" ");
			lSBQuery.append(" ORDER BY emp.EMP_NAME, emp.SEVARTH_ID, paybill.GROSS_AMT, paybill.NET_TOTAL");
			
			}

		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		Query Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is is "+lSBQuery.toString());
		emplList1 = Query.list();
		return emplList1;
	}



}
