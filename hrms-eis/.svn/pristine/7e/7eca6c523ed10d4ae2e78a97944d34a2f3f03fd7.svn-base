package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.eis.service.HrmsCommonMessageServImpl;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.reports.util.ReportDBConnection;

public class PostIncumbencyReportDAOImpl extends DefaultReportDataFinder implements ReportDataFinder
{

	private static final Log logger = LogFactory.getLog(IncumbencyReportDAOImpl.class);
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";

		public ReportVO getUserReportVO(ReportVO report, Object criteria)
			throws ReportException {
				logger.info("==In Dynamic Report Method=userReportVo======");
				SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdffromDate = new SimpleDateFormat("dd/MM/yyyy");
				
				String str = sdffromDate.format(new Date());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				String startDate = "01/01/" + calendar.get(Calendar.YEAR);
				
				report.setParameterValue("Dateto", sdfToDate.format(new Date()));
				report.setParameterValue("Datefrom", startDate);
				// report.setReportName(resourceBundle.getString("HRMS.govQtrStatReport"));
				
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
				
				String[] locCodeArray= {locationMst.getLocationCode().toString()};
				report.setParameterValue("Location",locCodeArray);
				
				OrgPostDetailsRlt orgPostDetailsRlt=(OrgPostDetailsRlt) loginDetailsMap.get("loggedinPostDetailsRlt");
				
				if(orgPostDetailsRlt.getCmnBranchMst()!= null){
					logger.info("Branch Name : "+orgPostDetailsRlt.getCmnBranchMst().getBranchName()+" BranchId : "+orgPostDetailsRlt.getCmnBranchMst().getBranchId());
					String[] branchIdArray = {String.valueOf(orgPostDetailsRlt.getCmnBranchMst().getBranchId())};
					orgPostDetailsRlt.getOrgDesignationMst().getDsgnId();
					report.setParameterValue("Branch",branchIdArray);
					if(orgPostDetailsRlt.getOrgDesignationMst()!= null){
						logger.info("Designation ID : "+orgPostDetailsRlt.getOrgDesignationMst().getDsgnId()+" Post ID : "+orgPostDetailsRlt.getOrgPostMst().getPostId());
						report.setParameterValue("Designation",String.valueOf(orgPostDetailsRlt.getOrgDesignationMst().getDsgnId()));
						 if(orgPostDetailsRlt.getOrgPostMst() != null){
							 report.setParameterValue("Post",String.valueOf(orgPostDetailsRlt.getOrgPostMst().getPostId()));
						 }
					}
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

	public List<OrgPostDetailsRlt> getPostName(String[] locationCode,String[] branchId,String dsgId,String langIdStr, String dsgnId) 
	{
		String langId=langIdStr;
		ArrayList postDetailsList = new ArrayList();
		
		Connection lCon = null;
		Statement lStmt = null;
		Statement lStmt1 = null;
		OrgPostDetailsRlt postDtlRlt = new OrgPostDetailsRlt();

		PreparedStatement lPStmt = null;
		ResultSet lRs = null;

		PreparedStatement lPStmt1 = null;
		ResultSet lRs1 = null;
		try 
		{
			if(locationCode.length > 0)
			{
				lCon = DBConnection.getConnection();
				lStmt = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				lStmt1 = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);


				StringBuffer sql = new StringBuffer();
				StringBuffer sql1 = new StringBuffer();
				String lStrSqlQuery = "";

				if ("en_US".equalsIgnoreCase(langId)|| "1".equalsIgnoreCase(langId)) {langId="1";}
				else {langId="2";}

				String locationCodeString="";
				for(int i=0;i<locationCode.length;i++)
				{
					locationCodeString="'"+locationCode[i]+"',"+locationCodeString;
				}
				if(locationCodeString.length()!=0)
				{
					locationCodeString=locationCodeString.substring(0, (locationCodeString.length()-1));		
				}
				logger.info("locationCodeString================>>"+locationCodeString);

				String branchIdString="";
				for(int i=0;i<branchId.length;i++)
				{
					branchIdString=branchId[i]+","+branchIdString;
				}
				if(branchIdString.length()!=0)
				{
					branchIdString=branchIdString.substring(0, (branchIdString.length()-1));		
				}

				logger.info("branchIdString================>>"+branchIdString);

				if(branchIdString.trim().equals("") || branchIdString.trim().equals("-1") || branchIdString.trim().equals("-1,-1"))
				{
					sql =new StringBuffer("SELECT * FROM org_post_details_rlt where loc_id in(select loc_id from cmn_location_mst " +
							"where location_code in("+locationCodeString+") and lang_id='"+langId+"') and lang_id='"+langId+"' and dsgn_id='"+dsgId+"' order by post_name ");
				}
				else
				{
					sql =new StringBuffer("SELECT * FROM org_post_details_rlt where loc_id in(select loc_id from cmn_location_mst " +
							"where location_code in("+locationCodeString+") and branch_id in("+branchIdString+") and lang_id='"+langId+"') and lang_id='"+langId+"' and dsgn_id='"+dsgId+"' order by post_name ");
				}

				lStrSqlQuery = "";
				lStrSqlQuery = sql.toString();

				lPStmt = lCon.prepareStatement(lStrSqlQuery);

				lRs = lPStmt.executeQuery();
				logger.info("dsgId================="+dsgId);
				while (lRs.next()) 
				{
					postDtlRlt = new OrgPostDetailsRlt();
					postDtlRlt.setPostDetailId(Long.parseLong(lRs.getString("post_id")));
					postDtlRlt.setPostName(lRs.getString("post_name"));
					postDetailsList.add(postDtlRlt);
				}
				logger.info("postDetailsList==========="+postDetailsList.size());
			}
		} 
		catch (Exception e) 
		{
			logger.error("Exception In getDesignationName Report ..............>> ",e);
		}
		finally
		{
			try 
			{
				if(lPStmt!=null) {lPStmt.close();}
				if(lPStmt1!=null) {lPStmt1.close();}
				if(lCon!=null) {lCon.close();}
				if(lStmt1!=null) {lStmt1.close();}
				if(lStmt!=null) {lStmt.close();}
				if(lRs1!=null) {lRs1.close();}
				if(lRs!=null) {lRs.close();}
			} catch (SQLException e) 
			{
				logger.error("Connection closing error in Report DAO file hrms-common ",e);
			}
		}
		return postDetailsList;
	}


	public Collection findReportData(ReportVO report, Object criteria) throws ReportException 
	{
		Connection lCon = null;
		Statement lStmt = null;
		PreparedStatement lPStmt =null;
		ResultSet lRs = null;
		ArrayList DataList = new ArrayList();

		Date curDate=new Date();
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

			if (LangId.equalsIgnoreCase("en_US") || LangId.equalsIgnoreCase("1")) {langId = 1l;}
			else {langId = 2l;}

			/**Parameter From Parameter Page - start*/

			String[] locId = (String[])report.getParameterValue("Location");
			logger.info("locId======"+locId);

			String[] branchId = (String[])report.getParameterValue("Branch");
			logger.info("branchId======"+branchId);

			String dsgnId = report.getParameterValue("Designation").toString();
			logger.info("dsgnId======"+dsgnId);

			String strPost = report.getParameterValue("Post").toString().toLowerCase();
			logger.info("strPost======"+strPost);

			Date fromDate = report.getParameterValue("Datefrom") != null && !("").equals(String.valueOf(report.getParameterValue("Datefrom"))) ? StringUtility.convertStringToDate(String.valueOf(report.getParameterValue("Datefrom"))) : null;
			logger.info("fromDate======"+fromDate);

			Date toDate = report.getParameterValue("Dateto") != null && !("").equals(String.valueOf(report.getParameterValue("Dateto"))) ? StringUtility.convertStringToDate(String.valueOf(report.getParameterValue("Dateto"))) : null;
			logger.info("toDate======"+toDate);

			String tenureLookupName = report.getParameterValue("Tenure").toString();
			logger.info("tenureLookupName======"+tenureLookupName);

			int intYears = report.getParameterValue("Years") != null && !("").equals(String.valueOf(report.getParameterValue("Years"))) ? Integer.parseInt(String.valueOf(report.getParameterValue("Years"))) : 0;
			logger.info("intYears======"+intYears);

			int intMonths = report.getParameterValue("Months") != null && !("").equals(String.valueOf(report.getParameterValue("Months"))) ? Integer.parseInt(String.valueOf(report.getParameterValue("Months"))) : 0;
			logger.info("intMonths======"+intMonths);

			/**Parameter From Parameter Page - end*/

			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			ArrayList tblData = new ArrayList();

			ArrayList trow1 = new ArrayList();

			/*if(fromDate!=null)
			{
				trow1.add(resourceBundle.getString("eis.INCMBNCY_ON_DATE")+" "+simpleDateFormat.format(fromDate));
				tblData.add(trow1);
			}*/


			if(!("-1").equals(tenureLookupName))
			{
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

			int noOfDays = (intYears * 365) + (intMonths * 30);
			logger.info("noOfDays======"+noOfDays);

			String LocIdLst = "";
			for(int i=0;i<locId.length;i++)
			{
				LocIdLst="'"+locId[i]+"',"+LocIdLst;

				//LocIdLst.append(locId[i]).append(",");
			}
			logger.info("LocIdLst========="+LocIdLst.toString());

			Timestamp startDate=null;
			Timestamp endDate=null;
			if(fromDate!=null && !("").equals(fromDate))
			{
				startDate = new Timestamp(fromDate.getTime());
				logger.info("startDate=============>>"+startDate);
				sWhereClause.append(" and oupr.start_date >= '"+dateFormat.format(fromDate)+"'");
			}
			if(toDate!=null && !("").equals(toDate))
			{
				endDate = new Timestamp(toDate.getTime());
				logger.info("endDate=============>>"+endDate);
				sWhereClause.append(" and (oupr.end_date <='"+dateFormat.format(toDate)+"' or oupr.end_date is null )");
			}

			if(branchId!=null && branchId.length>0 && !("-1").equals(branchId[0]))
			{
				StringBuffer branchIdLst = new StringBuffer();
				for(int i=0;i<branchId.length;i++)
				{
					branchIdLst.append(branchId[i]).append(",");
				}
				logger.info("branchIdLst========="+branchIdLst.toString());
				sWhereClause.append(" and opdr.branch_id in ("+branchIdLst.substring(0, branchIdLst.length()-1)+")");
			}


			if(dsgnId != null && !("-1").equals(dsgnId))
			{
				sWhereClause.append(" and opdr.lang_id = "+langId+" and  opdr.dsgn_id = "+dsgnId);
			}

			if(strPost != null && !("-1").equals(strPost))
			{
				sWhereClause.append(" and opdr.post_id = "+strPost);
			}


			StringBuilder stringBuilder = new StringBuilder("select oupr.user_id as user_id,oem.emp_fname as first_name, oem.emp_mname as middle_name, oem.emp_lname as last_name,");
			stringBuilder.append(" opm.location_code as loc_code,opdr.post_name as post_name,oupr.start_date as start_date, oupr.end_date ");
			stringBuilder.append(" , case when oupr.end_date is not null then DATEDIFF(oupr.end_date,oupr.start_date) else DATEDIFF('"+dateFormat.format(curDate)+"',oupr.start_date) end as tenure ");

			stringBuilder.append(" from org_userpost_rlt oupr left join org_emp_mst oem on oupr.user_id = oem.user_id ");
			stringBuilder.append(" left join org_post_details_rlt opdr on opdr.post_id = oupr.post_id ");
			stringBuilder.append(" left join cmn_branch_mst cbm on opdr.branch_id = cbm.branch_id ");
			stringBuilder.append(" left join org_post_mst opm on opm.post_id = opdr.post_id");

			stringBuilder.append(" where oupr.user_id = oem.user_id "); 
			stringBuilder.append(" and oupr.post_id = opdr.post_id and opdr.lang_id = ? and oem.lang_id = ? "); 

			if(fromDate!=null && toDate!=null)
			{
				stringBuilder.append(" and case when oupr.end_date is null then oupr.start_date >= '"+dateFormat.format(fromDate)+"' else (oupr.start_date >= '"+dateFormat.format(fromDate)+"' and oupr.end_date <= '"+dateFormat.format(toDate)+"' ) end");
			}
			else if(fromDate!=null)
			{
				stringBuilder.append(" and oupr.start_date >= '"+dateFormat.format(fromDate)+"' ");
			}
			else if(toDate!=null)
			{
				stringBuilder.append(" and oupr.end_date <= '"+dateFormat.format(toDate)+"' ");
			}

			stringBuilder.append(" and opm.location_code in ("+LocIdLst.substring(0, LocIdLst.length()-1)+")");
			stringBuilder.append(sWhereClause);
			stringBuilder.append(" order by start_date desc");
			
			logger.info("Query=====stringBuilder.toString()==="+stringBuilder.toString());

			lPStmt = lCon.prepareStatement(stringBuilder.toString());

			lPStmt.setLong(1, langId);
			lPStmt.setLong(2, langId);

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

				if(lRs.getString("start_date") != null){incumbencyList.add(simpleDateFormat.format(lRs.getDate("start_date")));}
				else{incumbencyList.add("-");}

				if(lRs.getString("end_date") != null){incumbencyList.add(simpleDateFormat.format(lRs.getDate("end_date")));}
				else{incumbencyList.add("-");}

				int noOfDaysFromDB = Integer.parseInt(lRs.getString("tenure"));
				int years = noOfDaysFromDB/365;
				int months = (noOfDaysFromDB%365)/30;
				int days = (noOfDaysFromDB%365)%30;

				if(lRs.getString("tenure") != null){incumbencyList.add(years+" "+resourceBundle.getString("eis.INCMBNCY_YEAR")+", "+months+" "+resourceBundle.getString("eis.INCMBNCY_MONTH")+", "+days+" "+resourceBundle.getString("eis.INCMBNCY_DAY"));}
				else{incumbencyList.add("-");}

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
	
}
