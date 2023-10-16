package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.eis.service.HrmsCommonMessageServImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.reports.util.ReportDBConnection;

public class IncumbencyReportDAOImpl extends DefaultReportDataFinder implements ReportDataFinder{

	private static final Log logger = LogFactory.getLog(IncumbencyReportDAOImpl.class);
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";
	
	public ReportVO getUserReportVO(ReportVO report, Object criteria)
	throws ReportException {
		logger.info("==In Dynamic Report Method=userReportVo======");

		// Show the current Department, District , Location and Branch (if any) of the loggedIn user as selected - By kshitij
		String[] def = {"-1"};
		Hashtable sessionKeys = (Hashtable) ((Hashtable)criteria).get(IReportConstants.SESSION_KEYS);
			
		Map loginDetailsMap =(HashMap)sessionKeys.get("loginDetailsMap");
		
		CmnLocationMst locationMst = new CmnLocationMst();
		locationMst = (CmnLocationMst)loginDetailsMap.get("locationVO");
		
		Long departmentId  = locationMst.getDepartmentId();
		logger.info("departmentId -- "+departmentId+"  district :: "+locationMst.getLocDistrictId().toString()+" Location :: "+locationMst.getLocationCode());
		
		report.setParameterValue("DepartmentName", String.valueOf(departmentId));
		report.setParameterValue("District", String.valueOf(locationMst.getLocDistrictId()));
		
		String[] locIdArray= {String.valueOf(locationMst.getLocId())};
		report.setParameterValue("Location",locIdArray);
		
		OrgPostDetailsRlt orgPostDetailsRlt=(OrgPostDetailsRlt) loginDetailsMap.get("loggedinPostDetailsRlt");
		
		if(orgPostDetailsRlt.getCmnBranchMst()!= null){
			logger.info("Branch Name : "+orgPostDetailsRlt.getCmnBranchMst().getBranchName()+" BranchId : "+orgPostDetailsRlt.getCmnBranchMst().getBranchId());
			String[] branchIdArray = {String.valueOf(orgPostDetailsRlt.getCmnBranchMst().getBranchId())};
			orgPostDetailsRlt.getOrgDesignationMst().getDsgnId();
			report.setParameterValue("Branch",branchIdArray);
		}
		else{
			report.setParameterValue("Branch",def);
		}
		return report;
}
	
	public List getBranchName(String[] locId,String langIdSTr, String branchId) 
	{
		String langId = langIdSTr;
		List branchList = new ArrayList();
		Connection lCon = null;
		CmnBranchMst branchObj = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try 
		{
			lCon = DBConnection.getConnection();
			
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";

			if ("en_US".equalsIgnoreCase(langId)
					|| "1".equalsIgnoreCase(langId)) {
				langId="1";
			} else {
				langId="2";
			}
			//added by fathima
			StringBuffer LocIdLst = new StringBuffer();
			if(locId!=null && locId.length>0 && !("-1").equals(locId[0]))
			{
				for(int i=0;i<locId.length;i++)
				{
					LocIdLst.append(locId[i]).append(",");
				}
			}
			String strLocId = "0";
			logger.info("LocIdLst=="+LocIdLst.toString());
			if(LocIdLst != null && LocIdLst.length()>0)
			{
				strLocId = LocIdLst.substring(0, LocIdLst.length()-1);
			}
			logger.info("strLocId=="+strLocId.toString());
			
			sql = new StringBuffer("SELECT BRANCH_NAME,BRANCH_ID FROM cmn_branch_mst where " +
					"BRANCH_CODE in(SELECT BRANCH_CODE FROM cmn_branchloc_mpg cbm,cmn_location_mst clm where " +
					"clm.LOCATION_CODE=cbm.LOCATION_CODE and clm.loc_id in ("+strLocId+")) and LANG_ID='"+langId+"' ");
			//ends..
			lStrSqlQuery = "";
			lStrSqlQuery = sql.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);

			lRs = lPStmt.executeQuery();

			while (lRs.next()) {
				branchObj = new CmnBranchMst();
				branchObj.setBranchId(lRs.getLong("BRANCH_ID"));
				branchObj.setBranchName(lRs.getString("BRANCH_NAME"));
				branchList.add(branchObj);
			}
		} catch (Exception e) {
			logger.error("Exception In getBranchName Incubency Report ..............>>",e);
		}
		finally
		{
			//ReportDBConnection.closeConnectionObjects(lRs, lPStmt, null, lCon);
		}
		return branchList;
	}
	public List getDesignationName(String langId,String locId) 
	{
	//	String langId=langIdStr;
		List designationList = new ArrayList();
		Connection lCon = null;
		OrgDesignationMst dsgnMSt = new OrgDesignationMst();
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try {

				lCon = DBConnection.getConnection();
	
				StringBuffer sql = new StringBuffer();
				
				sql =new StringBuffer("select dsgn_id,dsgn_name from org_designation_mst where activate_flag = 1 and lang_id=? order by dsgn_name");
				lPStmt = lCon.prepareStatement(sql.toString());
				
				if ("en_US".equalsIgnoreCase(langId) || "1".equalsIgnoreCase(langId)) 
				{
					lPStmt.setLong(1, Long.valueOf(1l));
				}
				else 
				{
					lPStmt.setLong(1, Long.valueOf(2l));
				}
								
				lRs = lPStmt.executeQuery();
				
				while (lRs.next()) {
					dsgnMSt = new OrgDesignationMst();
					dsgnMSt.setDsgnId(lRs.getLong("dsgn_id"));
					dsgnMSt.setDsgnName(lRs.getString("dsgn_name"));
					designationList.add(dsgnMSt);
				}
			
			
			} 
		catch (Exception e) 
		{
			logger.error("Exception In getDesignationName Report ..............>> ",e);
		}
		finally
		{
			//ReportDBConnection.closeConnectionObjects(lRs, lPStmt, null, lCon);
		}
		return designationList;
	}
	
	public List<CmnLookupMst> getTenureLookupName(String langId,String locId) 
	{
		logger.info("In getCmnLookupMstListByLookupName==============");
		List<CmnLookupMst> arrCmnLookupMstList = new ArrayList<CmnLookupMst>();
		Connection lCon = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try
		{
			lCon = DBConnection.getConnection();
			String lStrSqlQuery = "";
			String lookupName = "Tenure";
			
			StringBuffer sql = new StringBuffer("SELECT c1.lookup_name, c1.lookup_desc FROM cmn_lookup_mst c, cmn_lookup_mst c1 ");
			sql.append("WHERE c.lookup_name = '"+lookupName+"'  AND c1.parent_lookup_id =c.lookup_id AND c1.lang_id=? order by c1.order_no");
			lStrSqlQuery = sql.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);

			if ("en_US".equalsIgnoreCase(langId) || "1".equalsIgnoreCase(langId)) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}

			lRs = lPStmt.executeQuery();
			
			CmnLookupMst cmnLookupMstObj = null;
			while (lRs.next())
			{
				cmnLookupMstObj = new CmnLookupMst();
				cmnLookupMstObj.setLookupName(lRs.getString("lookup_name"));
				cmnLookupMstObj.setLookupDesc(lRs.getString("lookup_desc"));
				arrCmnLookupMstList.add(cmnLookupMstObj);
			}
		} 

		catch (Exception e)
		{
				logger.error("Exception In getTenureLookupName ",e);
		}
		finally
		{
			//ReportDBConnection.closeConnectionObjects(lRs, lPStmt, null, lCon);
		}
		return arrCmnLookupMstList;
	}
	
	public Collection findReportData(ReportVO report, Object criteria) throws ReportException 
	{
		Connection lCon = null;
		Statement lStmt = null;
		PreparedStatement lPStmt =null;
		ResultSet lRs = null;
		
		ArrayList DataList = new ArrayList();
		
		try 
		{
			lCon = DBConnection.getConnection();
			lStmt = (Statement) lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/eis/eis_incumbency", new Locale(report.getLangId()));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			
			StringBuilder sWhereClause = new StringBuilder();
			StringBuilder sFromClause = new StringBuilder();
			
			String LangId = report.getLangId();
			long langId = 0l;
			
			if (LangId.equalsIgnoreCase("en_US") || LangId.equalsIgnoreCase("1")) {
				langId = 1l;
			}
			else {
				langId = 2l;
			}
			
			/**Parameter From Parameter Page - start*/
			
			String[] locId = (String[])report.getParameterValue("Location");
			logger.info("locId======"+locId);
			String[] branchId = (String[])report.getParameterValue("Branch");
			logger.info("branchId======"+branchId);
			String dsgnId = report.getParameterValue("Designation").toString();
			logger.info("dsgnId======"+dsgnId);
			String empName = report.getParameterValue("Employee_name").toString().toLowerCase();
			logger.info("empName======"+empName);
			Date onDate = report.getParameterValue("On_Date") != null && !("").equals(String.valueOf(report.getParameterValue("On_Date"))) ? StringUtility.convertStringToDate(String.valueOf(report.getParameterValue("On_Date"))) : new Date();
			logger.info("onDate======"+onDate);
			String tenureLookupName = report.getParameterValue("Tenure").toString();
			logger.info("tenureLookupName======"+tenureLookupName);
			int intYears = report.getParameterValue("Years") != null && !("").equals(String.valueOf(report.getParameterValue("Years"))) ? Integer.parseInt(String.valueOf(report.getParameterValue("Years"))) : 0;
			logger.info("intYears======"+intYears);
			int intMonths = report.getParameterValue("Months") != null && !("").equals(String.valueOf(report.getParameterValue("Months"))) ? Integer.parseInt(String.valueOf(report.getParameterValue("Months"))) : 0;
			logger.info("intMonths======"+intMonths);
			
			/**Parameter From Parameter Page - end*/
			
			ArrayList tblData = new ArrayList();
			
			ArrayList trow1 = new ArrayList();
			trow1.add(resourceBundle.getString("eis.INCMBNCY_ON_DATE")+" "+simpleDateFormat.format(onDate));
			tblData.add(trow1);
			
			if(!("-1").equals(tenureLookupName))
			{
				StyleVO[] styleVO = new StyleVO[1];
				styleVO[0] = new StyleVO();
				styleVO[0].setStyleId(IReportConstants.BORDER_TOP);
				styleVO[0].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_MEDIUM); 
				
				String strLookupDesc="";
				String strQuery = "select clm.lookup_desc as lookup_desc from cmn_lookup_mst clm where clm.lookup_name='"+tenureLookupName+"' and clm.lang_id="+langId;
				logger.info("strQuery=="+strQuery);
				lRs = lStmt.executeQuery(strQuery);
				logger.info("lRs=="+lRs);
				while(lRs.next())
				{
					strLookupDesc = lRs.getString("lookup_desc");
				}
				lRs.close();
				
				ArrayList trow2 = new ArrayList();
				String strTenure = ""; 
				if(langId == 1l)
				{
					strTenure = resourceBundle.getString("eis.INCMBNCY_HEADER_1")+" "+strLookupDesc+" "+intYears+" "+resourceBundle.getString("eis.INCMBNCY_YEAR")+" "+intMonths+" "+resourceBundle.getString("eis.INCMBNCY_MONTH")+" "+resourceBundle.getString("eis.INCMBNCY_HEADER_2");
				}
				else
				{
					strTenure = intYears+" "+resourceBundle.getString("eis.INCMBNCY_YEAR")+" "+intMonths+" "+resourceBundle.getString("eis.INCMBNCY_MONTH")+" "+strLookupDesc+" "+resourceBundle.getString("eis.INCMBNCY_HEADER_2")+" "+resourceBundle.getString("eis.INCMBNCY_HEADER_1");
				}
				trow2.add(strTenure);
				//tblData.add(new StyledData(trow2,styleVO));
				tblData.add(trow2);
			}
				
			TabularData tabularData = new TabularData(tblData);
			tabularData.addStyle(IReportConstants.BACKGROUNDCOLOR, IReportConstants.VALUE_FONT_COLOR_GRAY);
			tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
			tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_SMALL);
			tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			tabularData.addStyle(IReportConstants.ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			report.setAdditionalHeader(tabularData);
			
			int noOfDays = (intYears * 365) + (intMonths * 30);
			logger.info("noOfDays======"+noOfDays);
			
			StringBuffer LocIdLst = new StringBuffer();
			for(int i=0;i<locId.length;i++)
			{
				LocIdLst.append(locId[i]).append(",");
			}
			Timestamp startDate = new Timestamp(onDate.getTime());
			
			if(empName != null && !("").equals(empName))
			{
				sWhereClause.append(" and lower(concat(concat(trim(oem.emp_fname), ' '), case when oem.emp_mname is null or oem.emp_mname='' then trim(oem.emp_lname) else concat(concat(trim(oem.emp_mname), ' '), trim(oem.emp_lname)) end)) like '%"+empName+"%'");
			}
			if(branchId!=null && branchId.length>0 && !("-1").equals(branchId[0]))
			{
				StringBuffer branchIdLst = new StringBuffer();
				for(int i=0;i<branchId.length;i++)
				{
					branchIdLst.append(branchId[i]).append(",");
				}
				sWhereClause.append(" and opdr.branch_id in ("+branchIdLst.substring(0, branchIdLst.length()-1)+")");
			}
			if(dsgnId != null && !("-1").equals(dsgnId))
			{
				sFromClause.append(" ,org_emp_dsgn_mpg oedm_dsgn");
				sWhereClause.append(" and oedm_dsgn.activate_flag = 1 and oem.emp_id = oedm_dsgn.emp_id and oedm_dsgn.dsgn_id = "+dsgnId);
			}
			
			StringBuilder stringBuilder = new StringBuilder("select oem.user_id as user_id, oem.emp_fname as first_name, oem.emp_mname as middle_name, oem.emp_lname as last_name,");
			stringBuilder.append(" oem.emp_gpf_num as gpf_num,odm.dsgn_name as dsgn_name,clm.loc_name as loc_name,opdr.post_name as post_name,our.start_date as start_date, our.end_date ");
			stringBuilder.append(" , DATEDIFF(?, our.start_date) as tenure,");
			stringBuilder.append("cam.ADDRESS_ID, cam.MASTER_LOOKUPID, cam.ADDTYPE_LOOKUPID, clm_adr.LOOKUP_NAME,");
			stringBuilder.append(" cam.HOUSE_NAME, cam.SOC_BUILD_NAME, cam.STREET, cam.AREA, cam.FALIYU, cam.OTHER_DETAILS,");
			stringBuilder.append(" cam.CITYVILLAGE_NAME, cam.TALUKA_NAME, cam.DISTRICT_NAME, cam.STATE_NAME, cam.PINCODE,");
			stringBuilder.append(" cam.VILLAGE_ID, cvm_disp.village_name, cam.TALUKA_ID, ctm_disp.taluka_name,");
			stringBuilder.append(" cam.DISTRICT_ID, cdm_disp.district_name, cam.CITY_ID, cctm_disp.city_name,");
			stringBuilder.append(" cam.STATE_ID, csm_disp.state_name, cam.COUNTRY_ID, ccm_disp.country_name, ccm_disp.country_code ");
			stringBuilder.append(" from org_emp_mst oem left join org_emp_dsgn_mpg oedm on oem.emp_id = oedm.emp_id and case when oedm.end_date is null then oedm.start_date <= ? else (oedm.start_date <= ? and oedm.end_date >= ? ) end ");
			stringBuilder.append(" left join org_designation_mst odm on oedm.dsgn_id= odm.dsgn_id,");
			stringBuilder.append(" org_userpost_rlt our,");
			stringBuilder.append(" org_post_details_rlt opdr,");
			stringBuilder.append(" cmn_location_mst clm,");
			stringBuilder.append(" org_emp_mst oem_eng ");
			stringBuilder.append(" left join hr_eis_emp_mst heem on heem.emp_mpg_id = oem_eng.emp_id ");
			stringBuilder.append(" left join cmn_address_mst cam on heem.emp_native_place_address_id = cam.address_id ");
			stringBuilder.append(" left join cmn_lookup_mst clm_adr on clm_adr.LOOKUP_ID = cam.ADDTYPE_LOOKUPID ");
			stringBuilder.append(" left join cmn_country_mst ccm on ccm.COUNTRY_ID = cam.COUNTRY_ID ");
			stringBuilder.append(" left join cmn_country_mst ccm_disp on ccm_disp.COUNTRY_CODE = ccm.COUNTRY_CODE and ccm_disp.lang_id = "+langId+" ");
			stringBuilder.append(" left join cmn_state_mst csm on csm.STATE_ID = cam.STATE_ID ");
			stringBuilder.append(" left join cmn_state_mst csm_disp on csm_disp.STATE_CODE = csm.STATE_CODE and csm_disp.lang_id = "+langId+" ");
			stringBuilder.append(" left join cmn_city_mst cctm on cctm.CITY_ID = cam.CITY_ID ");
			stringBuilder.append(" left join cmn_city_mst cctm_disp on cctm_disp.CITY_CODE = cctm.CITY_CODE and cctm_disp.lang_id = "+langId+" ");
			stringBuilder.append(" left join cmn_district_mst cdm on cdm.DISTRICT_ID = cam.DISTRICT_ID ");
			stringBuilder.append(" left join cmn_district_mst cdm_disp on cdm_disp.DISTRICT_CODE = cdm.DISTRICT_CODE and cdm_disp.lang_id = "+langId+" ");
			stringBuilder.append(" left join cmn_taluka_mst ctm on cTm.TALUKA_ID = cam.TALUKA_ID ");
			stringBuilder.append(" left join cmn_taluka_mst ctm_disp on ctm_disp.TALUKA_CODE = ctm.TALUKA_CODE and ctm_disp.lang_id = "+langId+" ");
			stringBuilder.append(" left join cmn_village_mst cvm on cvm.VILLAGE_ID = cam.VILLAGE_ID ");
			stringBuilder.append(" left join cmn_village_mst cvm_disp on cvm_disp.VILLAGE_CODE = cvm.VILLAGE_CODE and cvm_disp.lang_id = "+langId+" ");
			stringBuilder.append(sFromClause);
			stringBuilder.append(" where oem.user_id = our.user_id"); 
			stringBuilder.append(" and oem_eng.user_id = our.user_id and oem_eng.lang_id = 1 ");
			stringBuilder.append(" and our.post_id = opdr.post_id and opdr.lang_id = ? and oem.lang_id = ? "); 
			stringBuilder.append(" and opdr.loc_id = clm.loc_id ");
			stringBuilder.append(" and case when our.end_date is null then our.start_date <= ? else (our.start_date <= ? and our.end_date >= ? ) end");
			stringBuilder.append(" and clm.loc_id in ("+LocIdLst.substring(0, LocIdLst.length()-1)+")");
			stringBuilder.append(sWhereClause);
			stringBuilder.append(" order by tenure");
				
			lPStmt = lCon.prepareStatement(stringBuilder.toString());
			lPStmt.setTimestamp(1, startDate);
			lPStmt.setTimestamp(2, startDate);
			lPStmt.setTimestamp(3, startDate);
			lPStmt.setTimestamp(4, startDate);
			lPStmt.setLong(5, langId);
			lPStmt.setLong(6, langId);
			lPStmt.setTimestamp(7, startDate);
			lPStmt.setTimestamp(8, startDate);
			lPStmt.setTimestamp(9, startDate);
			logger.info("Query==="+lPStmt.toString());
			
			lRs=lPStmt.executeQuery();
			ArrayList incumbencyList = null;
			long lCounter=1;
			while(lRs.next())
			{
				incumbencyList = new ArrayList();
				incumbencyList.add(String.valueOf(lCounter));
				
				String strEmpName="-";
				if(lRs.getString("first_name") != null && !("").equals(lRs.getString("first_name")) && lRs.getString("middle_name") != null && !("").equals(lRs.getString("middle_name")) && lRs.getString("last_name") != null && !("").equals(lRs.getString("last_name")))
				{
					strEmpName = lRs.getString("first_name") + " " + lRs.getString("middle_name") + " " + lRs.getString("last_name");
				}
				else if(lRs.getString("first_name") != null && !("").equals(lRs.getString("first_name")) && lRs.getString("middle_name") != null && !("").equals(lRs.getString("middle_name")))
				{
					strEmpName = lRs.getString("first_name") + " " + lRs.getString("middle_name");
				}
				else if(lRs.getString("first_name") != null && !("").equals(lRs.getString("first_name")) && lRs.getString("last_name") != null && !("").equals(lRs.getString("last_name")))
				{
					strEmpName = lRs.getString("first_name") + " " + lRs.getString("last_name");
				}
				else if(lRs.getString("first_name") != null && !("").equals(lRs.getString("first_name")))
				{
					strEmpName = lRs.getString("first_name");
				}
				//incumbencyList.add(strEmpName);
				//incumbencyList.add(HrmsCommonMessageServImpl.getHyperLinkOnEmployeeName(strEmpName,lRs.getLong("user_id")));
				
				if(lRs.getString("gpf_num") != null)
				{
					incumbencyList.add(lRs.getString("gpf_num"));
				}
				else
				{
					incumbencyList.add("-");
				}
				
				String strAddress = this.getCommaSeprateAddress(lRs);
				if(!("").equals(strAddress))
				{
					incumbencyList.add(strAddress);
				}
				else
				{
					incumbencyList.add("-");
				}
				
				if(lRs.getString("dsgn_name") != null)
				{
					incumbencyList.add(lRs.getString("dsgn_name"));
				}
				else
				{
					incumbencyList.add("-");
				}
				
				if(lRs.getString("loc_name") != null)
				{
					incumbencyList.add(lRs.getString("loc_name"));
				}
				else
				{
					incumbencyList.add("-");
				}
				
				if(lRs.getString("post_name") != null)
				{
					incumbencyList.add(lRs.getString("post_name"));
				}
				else
				{
					incumbencyList.add("-");
				}
				
				if(lRs.getString("start_date") != null)
				{
					incumbencyList.add(simpleDateFormat.format(lRs.getDate("start_date")));
				}
				else
				{
					incumbencyList.add("-");
				}
				
				int noOfDaysFromDB = Integer.parseInt(lRs.getString("tenure"));
				int years = noOfDaysFromDB/365;
				int months = (noOfDaysFromDB%365)/30;
				int days = (noOfDaysFromDB%365)%30;
				
				if(lRs.getString("tenure") != null)
				{
					incumbencyList.add(years+" "+resourceBundle.getString("eis.INCMBNCY_YEAR")+", "+months+" "+resourceBundle.getString("eis.INCMBNCY_MONTH")+", "+days+" "+resourceBundle.getString("eis.INCMBNCY_DAY"));
				}
				else
				{
					incumbencyList.add("-");
				}
				
				if(("Grtr_Than_Tenure").equals(tenureLookupName))
				{
					if(noOfDaysFromDB > noOfDays)
					{
						logger.info("Inside greater than");
						DataList.add(incumbencyList);
						lCounter++;
					}
				}
				else if(("Grtr_Eql_Tenure").equals(tenureLookupName))
				{
					if(noOfDaysFromDB >= noOfDays)
					{
						logger.info("Inside greater than or equal to");
						DataList.add(incumbencyList);
						lCounter++;
					}
				}
				else if(("Less_Than_Tenure").equals(tenureLookupName))
				{
					if(noOfDaysFromDB < noOfDays)
					{
						logger.info("Inside less than");
						DataList.add(incumbencyList);
						lCounter++;
					}
				}
				else if(("Less_Eql_Tenure").equals(tenureLookupName))
				{
					if(noOfDaysFromDB <= noOfDays)
					{
						logger.info("Inside less than or edual to");
						DataList.add(incumbencyList);
						lCounter++;
					}
				}
				else if(("Equal_To_Tenure").equals(tenureLookupName))
				{
					if(noOfDaysFromDB == noOfDays)
					{
						logger.info("Inside equal to");
						DataList.add(incumbencyList);
						lCounter++;
					}
				}
				else
				{
					logger.info("Inside not selected");
					DataList.add(incumbencyList);
					lCounter++;
				}
			}
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		finally
		{
			//ReportDBConnection.closeConnectionObjects(lRs, null, lStmt, lCon);
		}
		return DataList;
	}
	private String getCommaSeprateAddress(ResultSet lRs)
	{
		String addressString = "";
		try
		{
			long addressId = lRs.getString("ADDRESS_ID") != null ? lRs.getLong("ADDRESS_ID") : 0l; 
			logger.info("addressId======"+addressId);
			String addressType = lRs.getString("LOOKUP_NAME");
			logger.info("addressType======"+addressType);
			String houseName = lRs.getString("HOUSE_NAME"); 
			logger.info("houseName======"+houseName);
			String socBuildingName = lRs.getString("SOC_BUILD_NAME");
			logger.info("socBuildingName======"+socBuildingName);
			String street = lRs.getString("STREET");
			logger.info("street======"+street);
			String area = lRs.getString("AREA");
			logger.info("area======"+area);
			String faliyu = lRs.getString("FALIYU"); 
			logger.info("faliyu======"+faliyu);
			String cityVillageName = lRs.getString("CITYVILLAGE_NAME");
			logger.info("cityVillageName======"+cityVillageName);
			String strTalukaName = lRs.getString("TALUKA_NAME");
			logger.info("strTalukaName======"+strTalukaName);
			String strDistrictName = lRs.getString("DISTRICT_NAME");
			logger.info("strDistrictName======"+strDistrictName);
			String strStateName = lRs.getString("STATE_NAME");
			logger.info("strStateName======"+strStateName);
			String pincode = lRs.getString("PINCODE");
			logger.info("pincode======"+pincode);
			String villageName = lRs.getString("village_name");
			logger.info("villageName======"+villageName);
			String talukaName = lRs.getString("taluka_name");
			logger.info("talukaName======"+talukaName);
			String districtName = lRs.getString("district_name");
			logger.info("districtName======"+districtName);
			String cityName = lRs.getString("city_name");
			logger.info("cityName======"+cityName);
			String stateName = lRs.getString("state_name");
			logger.info("stateName======"+stateName);
			String countryName = lRs.getString("country_name");
			logger.info("countryName======"+countryName);
			String countryCode = lRs.getString("country_code");
			logger.info("countryCode======"+countryCode);
			
			if(addressId != 0)
			{
				if(addressType.equals("Village"))
				{
					if(houseName != null && !("").equals(houseName) && !("null").equals(houseName))
					{
						addressString = houseName;
					}
					if(faliyu != null && !("").equals(faliyu) && !("null").equals(faliyu))
					{
						if(addressString.equals(""))
						{
							addressString = faliyu;
						}
						else
						{
							addressString = addressString + ", " +faliyu;
						}
					}
					
					if(addressString.equals(""))
					{
						addressString = villageName;
					}
					else
					{
						addressString = addressString + ", " +villageName;
					}
					addressString = addressString + ", " +talukaName;
					addressString = addressString + ", " +districtName;
					addressString = addressString + ", " +stateName;
					addressString = addressString + ", " +countryName;
					
					if(pincode != null && !("").equals(pincode) && !("null").equals(pincode))
					{
						addressString = addressString + " - " +pincode;
					}
					logger.info("addressString===village==="+addressString);
				}
				else if(addressType.equals("City"))
				{
					if(houseName != null && !("").equals(houseName) && !("null").equals(houseName))
					{
						addressString = houseName;
					}
					if(socBuildingName != null && !("").equals(socBuildingName) && !("null").equals(socBuildingName))
					{
						if(addressString.equals(""))
						{
							addressString = socBuildingName;
						}
						else
						{
							addressString = addressString + ", " +socBuildingName;
						}
					}
					if(street != null && !("").equals(street) && !("null").equals(street))
					{
						if(addressString.equals(""))
						{
							addressString = street;
						}
						else
						{
							addressString = addressString + ", " +street;
						}
					}
					if(area != null && !("").equals(area) && !("null").equals(area))
					{
						if(addressString.equals(""))
						{
							addressString = area;
						}
						else
						{
							addressString = addressString + ", " +area;
						}
					}
					
					if(addressString.equals(""))
					{
						addressString = cityName;
					}
					else
					{
						addressString = addressString + ", " +cityName;
					}
					addressString = addressString + ", " +stateName;
					addressString = addressString + ", " +countryName;
					
					if(pincode != null && !("").equals(pincode) && !("null").equals(pincode))
					{
						addressString = addressString + " - " +pincode;
					}
					logger.info("addressString===city==="+addressString);
				}
				else if(addressType.equals("EmployeeAddress"))
				{
					if(houseName != null && !("").equals(houseName) && !("null").equals(houseName))
					{
						addressString = houseName;
					}
					if(socBuildingName != null && !("").equals(socBuildingName) && !("null").equals(socBuildingName))
					{
						if(addressString.equals(""))
						{
							addressString = socBuildingName;
						}
						else
						{
							addressString = addressString + ", " +socBuildingName;
						}
					}
					if(street != null && !("").equals(street) && !("null").equals(street))
					{
						if(addressString.equals(""))
						{
							addressString = street;
						}
						else
						{
							addressString = addressString + ", " +street;
						}
					}
					if(addressString.equals(""))
					{
						addressString = area;
					}
					else
					{
						addressString = addressString + ", " +area;
					}
					
					if(talukaName != null && !("").equals(talukaName) && !("null").equals(talukaName))
					{
						addressString = addressString + ", " +talukaName;
					}
					else if(strTalukaName != null && !("").equals(strTalukaName) && !("null").equals(strTalukaName))
					{
						addressString = addressString + ", " +strTalukaName;
					}
					
					if(districtName != null && !("").equals(districtName) && !("null").equals(districtName))
					{
						addressString = addressString + ", " +districtName;
					}
					else if(strDistrictName != null && !("").equals(strDistrictName) && !("null").equals(strDistrictName))
					{
						addressString = addressString + ", " +strDistrictName;
					} 
					
					if(stateName != null && !("").equals(stateName) && !("null").equals(stateName))
					{
						addressString = addressString + ", " +stateName;
					}
					else if(strStateName != null && !("").equals(strStateName) && !("null").equals(strStateName))
					{
						addressString = addressString + ", " +strStateName;
					} 
					addressString = addressString + ", " +countryName;
					if(pincode != null && !("").equals(pincode) && !("null").equals(pincode))
					{
						addressString = addressString + " - " +pincode;
					}
					logger.info("addressString===Employee Address==="+addressString);
				}
				else if(addressType.equals("OtherRadioAddress"))
				{
					if(houseName != null && !("").equals(houseName) && !("null").equals(houseName))
					{
						addressString = houseName;
					}
					if(socBuildingName != null && !("").equals(socBuildingName) && !("null").equals(socBuildingName))
					{
						if(addressString.equals(""))
						{
							addressString = socBuildingName;
						}
						else
						{
							addressString = addressString + ", " +socBuildingName;
						}
					}
					if(area != null && !("").equals(area) && !("null").equals(area))
					{
						if(addressString.equals(""))
						{
							addressString = area;
						}
						else
						{
							addressString = addressString + ", " +area;
						}
					}
					if(addressString.equals(""))
					{
						addressString = cityVillageName;
					}
					else
					{
						addressString = addressString + ", " +cityVillageName;
					}
					if(strTalukaName != null && !("").equals(strTalukaName) && !("null").equals(strTalukaName))
					{
						addressString = addressString + ", " +strTalukaName;
					}
					
					if(("1").equals(countryCode))
					{
						addressString = addressString + ", " +districtName;
						addressString = addressString + ", " +stateName;
						addressString = addressString + ", " +countryName;
						if(pincode != null && !("").equals(pincode) && !("null").equals(pincode))
						{
							addressString = addressString + " - " +pincode;
						}
					}
					else
					{
						if(strDistrictName != null && !("").equals(strDistrictName) && !("null").equals(strDistrictName))
						{
							addressString = addressString + ", " +strDistrictName;
						}
						addressString = addressString + ", " +strStateName;
						addressString = addressString + ", " +countryName;
						if(pincode != null && !("").equals(pincode) && !("null").equals(pincode))
						{
							addressString = addressString + " - " +pincode;
						}
					}
					logger.info("addressString===Other Address==="+addressString);
				}
			}
		}
		catch (Exception e)
		{
			logger.error("Error In getCommaSeprateAddress ",e);
		}
		return addressString;
	}

}
