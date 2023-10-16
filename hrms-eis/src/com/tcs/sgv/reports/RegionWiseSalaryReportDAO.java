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
 * @since JDK 7.0 May 22,2018
 */


public class RegionWiseSalaryReportDAO extends DefaultReportDataFinder {

	private static final Logger gLogger = Logger.getLogger("RegionWiseSalaryReportDAO");
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


		String regionCode=null;
		String districtCode=null;
		String adminType=null;
		String month=null;
		String year=null;

		ArrayList row = null;
		StyledData rowData = null;

		try{
			regionCode = (String) report.getParameterValue("listRegion");
			districtCode = "-1";
			adminType = "-1";
			month = (String) report.getParameterValue("listmonth");
			year = (String) report.getParameterValue("listYear");
			List lstpaybillGeneration = getRegionSalaryReport(year,month,regionCode);
			String regionName= null;
			String districtName= null;
			String adminName =null;
			String grossAmount= null;
			String netAmount =null;
			String totalInstApproved = null;
			String noOfEmpInLockedPaybills = null;
			String districtId=null;
			String adminId=null;
			String rgnCode=null;
			String urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=8000203&action=generateReport&DirectReport=TRUE&displayOK=FALSE";
			String urlPrefix1 = "ifms.htm?actionFlag=reportService&reportCode=8000204&action=generateReport&DirectReport=TRUE&displayOK=FALSE";

			
			
			String[] regionSalaryDetails=null;
				Iterator iterobj = lstpaybillGeneration.iterator();
				Object[] objc = null;
				while(iterobj.hasNext()){
					objc = (Object[]) iterobj.next();
					if(objc[0]!=null){
						regionName= objc[0].toString();
					}
					else{
						regionName="0"; 
					}
					if(objc[1]!=null){
						districtName= objc[1].toString();
					}
					else{
						districtName = "0"; 
					}
					if(objc[2]!=null){
						adminName= objc[2].toString();
					}
					else{
						adminName = "0";
					}if(objc[3]!=null){
						grossAmount= objc[3].toString();
					}
					else{
						grossAmount = "0";
					}
					if(objc[4]!=null){
						netAmount= objc[4].toString();  
					}
					else{
						netAmount = "0";
					}
					if(objc[5]!=null){
						totalInstApproved= objc[5].toString();
					}
					else{
						totalInstApproved = "0";
					}
					if(objc[7]!=null){
						noOfEmpInLockedPaybills= objc[7].toString();
					}
					else{
						noOfEmpInLockedPaybills="0";
					}
					if(objc[9]!=null){
						districtId= objc[9].toString();
					}
					else{
						districtId="0";
					}
					if(objc[10]!=null){
						adminId= objc[10].toString();
					}
					else{
						adminId="0";
					}
					if(objc[8]!=null){
						rgnCode= objc[8].toString();
					}
					else{
						rgnCode="0";
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
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(adminName);*/
					row.add(new URLData(adminName,urlPrefix+"&month="+month+"&year="+year+"&regionCode="+rgnCode+"&districtId="+districtId+"&adminType="+adminId));

					

					//6
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(totalInstApproved);
					row.add(rowData);

					//7
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmpInLockedPaybills);*/
					row.add(new URLData(noOfEmpInLockedPaybills,urlPrefix1+"&month="+month+"&year="+year+"&regionCode="+rgnCode+"&districtId="+districtId+"&adminType="+adminId+"&reportType="+"Region"));
					
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

	//private Map getRegionSalaryReport(String year ,String month, String regionCode) {

	private List getRegionSalaryReport(String year ,String month, String regionCode) {
		List lLstFinal =null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" select region.region_name, dist.district_name, admin.admin_name, ");
		lSBQuery.append(" sum(cons.GROSS_AMT) as MTR44Gross,");
		lSBQuery.append(" sum(cons.NET_AMT) as MTR44NET,");
		lSBQuery.append(" count(rlt.zp_ddo_code) as total_ddo_code, ");
		lSBQuery.append(" count(distinct pay.loc_id) as bill_generated_schools, ");
		lSBQuery.append(" NVL(SUM(CASE WHEN PAY.APPROVE_FLAG IN (1) THEN PAY.NO_OF_EMP ELSE NULL END), 0) AS EMP_IN_LOCKED_BILLS ");
		lSBQuery.append(" ,REGION.REGION_CODE ");
		lSBQuery.append(" ,dist.DISTRICT_ID,ADMIN.ID ");
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
		lSBQuery.append(" AND PAY.PAYBILL_MONTH = "+month+"  ");
		if(regionCode.equalsIgnoreCase("9")){
			lSBQuery.append(" AND REGION.REGION_CODE in (1,2,3,4,5,6,7,8,9) ");
		}else{
			lSBQuery.append(" AND REGION.REGION_CODE = "+regionCode+" ");
		}
		lSBQuery.append(" group by region.region_name, dist.district_name, admin.admin_name,REGION.REGION_CODE,dist.DISTRICT_ID,ADMIN.ID ");
		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is "+lSBQuery.toString());
		lLstFinal = Query.list();
		return lLstFinal;
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
			String lStrBufLang = "SELECT finYearCode, finYearCode FROM SgvcFinYearMst WHERE langId = :langId and finYearCode BETWEEN '2007' AND '2018' ORDER BY finYearCode";
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
