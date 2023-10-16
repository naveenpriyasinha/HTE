package com.tcs.sgv.reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
 * @author Vaibhav Tyagi
 * @version 0.1
 * @since JDK 7.0 April 11,2014
 */
public class SchoolConfigurationReportDAO extends DefaultReportDataFinder {
	private static final Logger gLogger = Logger.getLogger("SchoolConfigurationReportDAO");
	public static String newline = System.getProperty("line.separator");
	ServiceLocator serviceLocator = null;
	SessionFactory gObjSessionFactory = null;
	String gStrLocCode = null;
	Long gLngLangId = null;
	Long gLngPostId = null;
	Map lMapSeriesHeadCode = null;
	Map requestAttributes = null;
	Session ghibSession = null;

	public Collection findReportData(ReportVO report, Object criteria) throws ReportException 
	{		
		ArrayList dataList = new ArrayList();
		requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		gObjSessionFactory = serviceLocator.getSessionFactorySlave();

		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		/*ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		SessionFactory lObjSessionFactory = serviceLocator.getSessionFactorySlave();*/
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

		String ddoCode=null;
		String adminType=null;
		String districtId=null;
		String month=null;
		String year=null;

		List row = null;
		StyledData rowData = null;

		try {
			ddoCode = (String) report.getParameterValue("ddoCode");
			adminType= (String) report.getParameterValue("adminType");
			districtId= (String) report.getParameterValue("districtId");
			month = (String)report.getParameterValue("month");
			year = (String)report.getParameterValue("year");
			gLogger.info("adminType:::"+adminType);
			gLogger.info("ddoCode:::"+ddoCode);
			
			Map paybillGeneration= getPaybillGenerationReport(ddoCode,adminType,districtId,month,year);
			Map empInForwardedpaybill = getEmployeeCountInForwardedPaybill(ddoCode,adminType,districtId);
			List mainReport= getMainTotal(ddoCode,adminType,districtId);

			Iterator itr = mainReport.iterator();
			Object[] obj = null;

			while(itr.hasNext()){
				obj= (Object[]) itr.next();
				String districtName= obj[0].toString();
				String adminName= obj[1].toString();
				String officeName = obj[2].toString();
				String levelOneDDoCode= obj[3].toString();
				String totalEmpConf = obj[4].toString();
				String totalEmpApproved = obj[5].toString();
				String noOfPaybillGenerated = null;
				String noOfPaybillForwarded= null;
				String[] paybillConf=null;
				if(paybillGeneration.containsKey(levelOneDDoCode)){
					paybillConf = paybillGeneration.get(levelOneDDoCode).toString().split("~");
					noOfPaybillGenerated = paybillConf[0].toString();
					noOfPaybillForwarded = paybillConf[1].toString();
				}
				else{
					noOfPaybillGenerated = "0";
					noOfPaybillForwarded = "0";
				}

				String noOfEmpInForwardedPaybill = null;
				if(empInForwardedpaybill.containsKey(levelOneDDoCode)){
					noOfEmpInForwardedPaybill = empInForwardedpaybill.get(levelOneDDoCode).toString();
				}

				else{
					noOfEmpInForwardedPaybill = "0";
				}

				String percentageCoverage = null;
				if(!totalEmpApproved.equals("0")){
					Double percentage= (Double.parseDouble(noOfEmpInForwardedPaybill)/Double.parseDouble(totalEmpApproved))*100;
					percentageCoverage =  percentage.toString();
				}

				else{
					percentageCoverage="Not Available";
				}


				//1
				row = new ArrayList();
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(districtName);
				row.add(rowData);

				//2
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(adminName);
				row.add(rowData);

				//3
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(officeName);
				row.add(rowData);


				//4
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(levelOneDDoCode);
				row.add(rowData);

				//5
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(Long.parseLong(totalEmpConf));
				row.add(rowData);

				//6
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(Long.parseLong(totalEmpApproved));
				row.add(rowData);

				//7
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(Long.parseLong(noOfPaybillGenerated));
				row.add(rowData);

				//8
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(Long.parseLong(noOfPaybillForwarded));
				row.add(rowData);

				//9
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(Long.parseLong(noOfEmpInForwardedPaybill));
				row.add(rowData);

				//10
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				gLogger.info("percentageCoverage***1******** is "+percentageCoverage);
				if(percentageCoverage.length()>5 && !percentageCoverage.equals("Not Available"))
					percentageCoverage=percentageCoverage.substring(0,5);
				gLogger.info("percentageCoverage******2***** is "+percentageCoverage);
				rowData.setData(percentageCoverage);
				row.add(rowData);

				dataList.add(row);
			}
			paybillGeneration = null;
			empInForwardedpaybill = null;
			mainReport=null;
		} catch (Exception e) {
			gLogger.info("findReportData(): Exception is" + e, e);
		} 
		ddoCode=null;
		adminType=null;
		row = null;
		rowData = null;
		return dataList;
	}

	private List getMainTotal(String ddoCode,String adminType, String districtId) {
		List lLstFinal =null;
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" SELECT dist.DISTRICT_NAME, admin.ADMIN_NAME,off.OFF_NAME,rlt.ZP_DDO_CODE, ");
		lSBQuery.append(" count(case when emp.ZP_STATUS in(-1,0,2,3,4,10) then 1 else null end) as total_emp_conf,");
		lSBQuery.append(" count(case when emp.ZP_STATUS =10 then 1 else null end) as total_emp_approved,admin.ADMIN_ORDER");
		lSBQuery.append(" FROM rlt_zp_ddo_map rlt");
		lSBQuery.append(" inner join org_ddo_mst ddo on ddo.DDO_CODE=rlt.ZP_DDO_CODE");
		lSBQuery.append(" inner join MST_DCPS_DDO_OFFICE off on ddo.DDO_CODE=off.DDO_CODE and upper(off.DDO_OFFICE)='YES'");
		lSBQuery.append(" left outer join mst_dcps_emp emp on emp.DDO_CODE = rlt.ZP_DDO_CODE");
		lSBQuery.append(" inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID");
		lSBQuery.append(" inner join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.id");
		lSBQuery.append(" where rlt.STATUS<>-2 and off.district=:districtId and ddo.ddo_type=:adminType");
		if(Long.parseLong(adminType)==2){
			lSBQuery.append(" and rlt.FINAL_DDO_CODE= :ddoCode");
		}
		else{
			lSBQuery.append(" and rlt.REPT_DDO_CODE= :ddoCode");
		}
		lSBQuery.append(" group by dist.DISTRICT_NAME, admin.ADMIN_NAME,off.OFF_NAME,rlt.ZP_DDO_CODE,admin.ADMIN_ORDER");
		lSBQuery.append(" order by dist.DISTRICT_NAME, admin.ADMIN_NAME,off.OFF_NAME,rlt.ZP_DDO_CODE,admin.ADMIN_ORDER");

		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is "+lSBQuery.toString());
		Query.setParameter("ddoCode", ddoCode);
		Query.setParameter("districtId", districtId);
		Query.setParameter("adminType", adminType);

		lLstFinal = Query.list();
		return lLstFinal;
	}

	private Map getPaybillGenerationReport(String ddoCode,String adminType, String districtId,String month,String year) {
		Map paybillGeneration=new HashMap();
		List lLstFinal =null;
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" SELECT rlt.ZP_DDO_CODE,");
		lSBQuery.append(" count(case when paybill.APPROVE_FLAG in(0,1) and paybill.STATUS in(0,2,3,4) then 1 else null end),");
		lSBQuery.append(" count(case when paybill.APPROVE_FLAG =0 and paybill.STATUS in(2,3) then 1 else null end)");
		lSBQuery.append(" FROM rlt_zp_ddo_map rlt");
		lSBQuery.append(" inner join org_ddo_mst ddo on rlt.ZP_DDO_CODE=ddo.DDO_CODE");
		lSBQuery.append(" inner join PAYBILL_HEAD_MPG paybill on ddo.LOCATION_CODE=paybill.LOC_ID and paybill.APPROVE_FLAG in(0,1)");
		lSBQuery.append(" where rlt.STATUS <>-2 and paybill.paybill_month=:month and paybill.paybill_year=:year");
		if(Long.parseLong(adminType)==2){
			lSBQuery.append(" and rlt.FINAL_DDO_CODE= :ddoCode");
		}
		else{
			lSBQuery.append(" and rlt.REPT_DDO_CODE= :ddoCode");
		}
		lSBQuery.append(" group by rlt.ZP_DDO_CODE");

		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is "+lSBQuery.toString());
		Query.setParameter("ddoCode", ddoCode);
		Query.setLong("month", Long.valueOf(month));
		Query.setLong("year", Long.valueOf(year));

		lLstFinal = Query.list();

		Iterator itr = lLstFinal.iterator();
		Object[] obj = null;
		while(itr.hasNext()){
			obj = (Object[]) itr.next();
			paybillGeneration.put(obj[0].toString(), obj[1].toString()+"~"+obj[2].toString());
		}
		gLogger.info("map values "+paybillGeneration);
		obj=null;
		itr=null;
		lLstFinal=null;
		return paybillGeneration;
	}

	private Map getEmployeeCountInForwardedPaybill(String ddoCode,String adminType, String districtId) {
		Map forwardEmpConf=new HashMap();
		List lLstFinal =null;
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" SELECT rlt.ZP_DDO_CODE,");
		lSBQuery.append(" count(distinct emp_id)");
		lSBQuery.append(" FROM rlt_zp_ddo_map rlt");
		lSBQuery.append(" inner join org_ddo_mst ddo on rlt.ZP_DDO_CODE = ddo.DDO_CODE");
		lSBQuery.append(" inner join PAYBILL_HEAD_MPG paybill on ddo.LOCATION_CODE=paybill.LOC_ID and paybill.APPROVE_FLAG in(0) and paybill.STATUS in(2,3)");
		lSBQuery.append(" inner join HR_PAY_PAYBILL pay on paybill.PAYBILL_ID=pay.PAYBILL_GRP_ID");
		lSBQuery.append(" where rlt.STATUS<>-2 and pay.emp_id is not null");
		if(Long.parseLong(adminType)==2){
			lSBQuery.append(" and rlt.FINAL_DDO_CODE= :ddoCode");
		}
		else{
			lSBQuery.append(" and rlt.REPT_DDO_CODE= :ddoCode");
		}
		lSBQuery.append(" group by rlt.ZP_DDO_CODE");

		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is "+lSBQuery.toString());
		Query.setParameter("ddoCode", ddoCode);

		lLstFinal = Query.list();

		Iterator itr = lLstFinal.iterator();
		Object[] obj = null;
		while(itr.hasNext()){
			obj = (Object[]) itr.next();
			forwardEmpConf.put(obj[0].toString(), obj[1].toString());
		}
		obj=null;
		lLstFinal=null;
		itr=null;
		return forwardEmpConf;
	}
}
