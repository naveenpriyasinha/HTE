package com.tcs.sgv.reports;

import java.sql.ResultSet;
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
 * @author Saurabh Shejul;
 * @version 0.1
 * @since JDK 7.0 May 24,2018
 */


public class AdminWiseSalaryReportDAO extends DefaultReportDataFinder {
	
	private static final Logger gLogger = Logger.getLogger("AdminWiseSalaryReportDAO");
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
		
		ArrayList row = null;
		StyledData rowData = null;
		
		
		
		try{
			
			month = (String)report.getParameterValue("month");
			year = (String)report.getParameterValue("year");
			regionCode = (String)report.getParameterValue("regionCode");
			districtId = (String)report.getParameterValue("districtId");
			adminId = (String)report.getParameterValue("adminType");
			
			String urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=8000204&action=generateReport&DirectReport=TRUE&displayOK=FALSE";

			//String url = "ifms.htm?actionFlag=loadNPSNSDLGenFiles&cmbYear="+year+"&cmbMonth="+month; 
			
			String url = "ifms.htm?actionFlag=reportService&reportCode=8000202&year="+year+"&month="+month+"&regionCode="+regionCode;
			gLogger.info("URL IS"+url.toString());
			StyleVO[] lObjStyleVO = new StyleVO[report.getStyleList().length]; 
	        lObjStyleVO = report.getStyleList(); 
	        for (Integer lInt = 0; lInt < report.getStyleList().length; lInt++) 
	        { 
	            if (lObjStyleVO[lInt].getStyleId() == 26) 
	            { 
	                lObjStyleVO[lInt].setStyleValue(url); 
	                gLogger.info("URL IS"+url.toString());
	            } 
	        }
			
			List salaryReport= getAdminSalaryReport(month,year,regionCode,adminId,districtId);
			
			String ddoCode= null;
			String offName= null;
			String regionName= null;
			String districtName= null;
			String adminName =null;
			String grossAmount= null;
			String netAmount =null;
			//String totalInstApproved = null;
			String noOfEmpInLockedPaybills = null;
			
			Iterator itr =  salaryReport.iterator();
			Object[] objc = null;
			
			while(itr.hasNext()){
				objc = (Object[]) itr.next();
				
				if(objc[0]!=null){
					ddoCode= objc[0].toString();
				}
				else{
					ddoCode="0"; 
				}
				if(objc[1]!=null){
					offName= objc[1].toString();
				}
				else{
					offName = "0"; 
				}
				if(objc[2]!=null){
					regionName= objc[2].toString();
				}
				else{
					regionName="0"; 
				}
				if(objc[3]!=null){
					districtName= objc[3].toString();
				}
				else{
					districtName = "0"; 
				}
				if(objc[4]!=null){
					adminName= objc[4].toString();
				}
				else{
					adminName = "0";
				}if(objc[5]!=null){
					grossAmount= objc[5].toString();
				}
				else{
					grossAmount = "0";
				}
				if(objc[6]!=null){
					netAmount= objc[6].toString();  
				}
				else{
					netAmount = "0";
				}
				/*if(objc[7]!=null){
					totalInstApproved= objc[7].toString();
				}
				else{
					totalInstApproved = "0";
				}*/
				if(objc[9]!=null){
					noOfEmpInLockedPaybills= objc[9].toString();
				}
				else{
					noOfEmpInLockedPaybills="0";
				}
				
				
				//1
				row = new ArrayList();
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(regionName);
				row.add(rowData);

				//2
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(districtName);
				row.add(rowData);

				//3
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(adminName);
				row.add(rowData);
				
				//1
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(ddoCode);
				row.add(rowData);

				//2
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(offName);
				row.add(rowData);

				/*//6
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(totalInstApproved);
				row.add(rowData);*/

				//7
				/*rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(noOfEmpInLockedPaybills);*/
				row.add(new URLData(noOfEmpInLockedPaybills,urlPrefix+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&reportType="+"Admin"+"&ddoCode="+ddoCode));
				
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

				dataList.add(row);

			
			
		}
			
			
		}
			catch (Exception e) {
			gLogger.info("findReportData(): Exception is" + e, e);
		} 
		
		
		 row = null;
		 rowData = null;
		 return dataList;


	
	
	}
	
	private List getAdminSalaryReport(String month,String year,String regionCode,String adminId,String districtId) {
		List lLstFinal =null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" select mst.DDO_CODE, mst.off_name,region.region_name, dist.district_name, admin.admin_name, ");
		lSBQuery.append(" sum(cons.GROSS_AMT) as MTR44Gross,");
		lSBQuery.append(" sum(cons.NET_AMT) as MTR44NET,");
		lSBQuery.append(" count(rlt.zp_ddo_code) as total_ddo_code, ");
		lSBQuery.append(" count(distinct pay.loc_id) as bill_generated_schools, ");
		lSBQuery.append(" NVL(SUM(CASE WHEN PAY.APPROVE_FLAG IN (1) THEN PAY.NO_OF_EMP ELSE NULL END), 0) AS EMP_IN_LOCKED_BILLS ");
		lSBQuery.append(" from paybill_head_mpg pay ");
		lSBQuery.append(" inner join consolidated_bill_mpg mpg on mpg.paybill_id = pay.paybill_id  ");
		lSBQuery.append(" inner join consolidated_bill_mst cons on cons.cons_bill_id = mpg.cons_bill_id ");
		lSBQuery.append(" inner join org_ddo_mst ddo on ddo.location_code = pay.loc_id ");
		lSBQuery.append(" inner join zp_admin_name_mst admin on admin.admin_code = ddo.ddo_type ");
		lSBQuery.append(" inner join mst_dcps_ddo_office mst on mst.ddo_code = ddo.ddo_code ");
		lSBQuery.append(" inner join cmn_district_mst dist on dist.district_id = mst.district ");
		lSBQuery.append(" inner join zp_region_name_mst region on region.region_code = dist.region_code ");
		lSBQuery.append(" inner join rlt_zp_ddo_map rlt on rlt.zp_ddo_code = ddo.ddo_code ");
		lSBQuery.append(" WHERE RLT.STATUS IN (0,1) AND PAY.PAYBILL_YEAR = "+year+" ");
		lSBQuery.append(" AND PAY.PAYBILL_MONTH = "+month+" AND DDO.DDO_TYPE ="+adminId+" ");
		lSBQuery.append(" and DIST.DISTRICT_ID="+districtId+" AND REGION.REGION_CODE = "+regionCode+" ");
		lSBQuery.append(" group by mst.DDO_CODE,mst.off_name,region.region_name, dist.district_name, admin.admin_name,REGION.REGION_CODE  ");
		
		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is is "+lSBQuery.toString());
		lLstFinal = Query.list();
		return lLstFinal;
	}
	

}
