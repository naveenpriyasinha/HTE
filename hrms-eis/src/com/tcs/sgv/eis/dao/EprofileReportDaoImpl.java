package com.tcs.sgv.eis.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mysql.jdbc.Statement;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;

public class EprofileReportDaoImpl extends DefaultReportDataFinder implements ReportDataFinder 
{
	private static final Log logger = LogFactory.getLog(EprofileReportDaoImpl.class);
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";
	public ArrayList getDepartmentName(String langId,String locId)
	{
		logger.info("In getDepartmentName==============");
		ArrayList<OrgDepartmentMst> arrDepartmentDataList = new ArrayList<OrgDepartmentMst>();
		Connection lCon = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";
			
			sql = new StringBuffer("SELECT dep_name,department_code FROM org_department_mst o WHERE o.lang_id=? order by dep_name");

			lStrSqlQuery = "";
			lStrSqlQuery = sql.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);

			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}

			logger.info("lPStmt ::::1 >>>>>>>>>> "+lPStmt.toString());	
			lRs = lPStmt.executeQuery();
			
			int count = lRs.getFetchSize();
			logger.info("Department count=============="+count);
			
			OrgDepartmentMst orgDepartmentMstObj = null;
			while (lRs.next())
			{
				orgDepartmentMstObj = new OrgDepartmentMst();
				orgDepartmentMstObj.setDepName(lRs.getString("dep_name"));
				orgDepartmentMstObj.setDepCode(lRs.getString("department_code"));
				arrDepartmentDataList.add(orgDepartmentMstObj);
			}
		} 

		catch (Exception e)
		{
			StringBuffer lSbError = new StringBuffer(58);
			lSbError.append("Exception In E-Profile report When getting Department List");
			lSbError.append(e);
			String lStrError = lSbError.toString();
			logger.error(lStrError);
		}
		return arrDepartmentDataList;
	}
	public ArrayList getLocationName(String parentParam,String langId,String locId)
	{
		ArrayList<CmnLocationMst> arrLocationDataList = new ArrayList<CmnLocationMst>();
		Connection lCon = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";
			
			StringBuffer sqlOrgDeptMst = new StringBuffer();
			sqlOrgDeptMst = new StringBuffer("SELECT department_id FROM org_department_mst o WHERE o.department_code='"+parentParam+"' AND o.lang_id=?");
			
			lStrSqlQuery = "";
			lStrSqlQuery = sqlOrgDeptMst.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);
			logger.info("lPStmt ::::2 >>> "+lPStmt.toString());
			
			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}
			
			lRs = lPStmt.executeQuery();
			
			lRs.first();

			sql = new StringBuffer("SELECT loc_name,loc_id FROM cmn_location_mst c WHERE c.lang_id=? AND c.department_id="+ lRs.getLong("department_id")+" order by loc_name");

			lStrSqlQuery = "";
			lStrSqlQuery = sql.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);
			logger.info("lPStmt ::::3 >>> "+lPStmt.toString());
			
			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}

			lRs = lPStmt.executeQuery();
			int count = lRs.getFetchSize();
			logger.info("Location count=============="+count);
			
			CmnLocationMst cmnLocationMstObj = null;
			while (lRs.next())
			{
				cmnLocationMstObj = new CmnLocationMst();
				cmnLocationMstObj.setLocId(lRs.getLong("loc_id"));
				cmnLocationMstObj.setLocName(lRs.getString("loc_name"));
				arrLocationDataList.add(cmnLocationMstObj);
			}
		} 

		catch (Exception e)
		{
			StringBuffer lSbError = new StringBuffer(79);
			lSbError.append("Exception In E-Profile report When getting Location List on Selected Department");
			lSbError.append(e);
			String lStrError = lSbError.toString();
			logger.error(lStrError);
		}
		return arrLocationDataList;
	}
	public ArrayList getDesgnationID(String langId,String locId)
	{
		logger.info("In getDesgnationID==============");
		ArrayList<OrgDesignationMst> arrDesgnationDataList = new ArrayList<OrgDesignationMst>();

		Connection lCon = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";
			
			sql = new StringBuffer("SELECT dsgn_id,dsgn_name FROM org_designation_mst o WHERE o.lang_id=? order by dsgn_name");

			lStrSqlQuery = "";
			lStrSqlQuery = sql.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);

			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}

			logger.info("lPStmt Designation>>>>>>>>>> "+lPStmt.toString());	
			lRs = lPStmt.executeQuery();
			
			
			int count = lRs.getFetchSize();
			logger.info("Designation count=============="+count);
			
			OrgDesignationMst orgDesignationMstObj = null;
			while (lRs.next())
			{

				orgDesignationMstObj = new OrgDesignationMst();
				orgDesignationMstObj.setDsgnId(lRs.getLong("dsgn_id"));
				orgDesignationMstObj.setDsgnName(lRs.getString("dsgn_name"));
				arrDesgnationDataList.add(orgDesignationMstObj);
			}
		} 

		catch (Exception e)
		{
			StringBuffer lSbError = new StringBuffer(59);
			lSbError.append("Exception In E-Profile report When getting Designation List");
			lSbError.append(e);
			String lStrError = lSbError.toString();
			logger.error(lStrError);
		}
		return arrDesgnationDataList;
	}
	public ArrayList getReligionLookupName(String langId,String locId)
	{
		logger.info("In getReligionLookupName==============");
		ArrayList<CmnLookupMst> arrReligionLookupDataList = new ArrayList<CmnLookupMst>();
		Connection lCon = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";
			
			sql = new StringBuffer("SELECT c1.lookup_name, c1.lookup_desc FROM cmn_lookup_mst c, cmn_lookup_mst c1 ");
			sql.append("WHERE c.lookup_name = 'emp_Religion'  AND c1.parent_lookup_id =c.lookup_id AND c1.lang_id=? order by c1.order_no");
			lStrSqlQuery = "";
			lStrSqlQuery = sql.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);

			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}

			logger.info("lPStmt ReligionLookup :::4>>>>>>>>>> "+lPStmt.toString());	
			lRs = lPStmt.executeQuery();
			
			int count = lRs.getFetchSize();
			logger.info("ReligionLookup count=============="+count);
			
			CmnLookupMst cmnLookupMstObj = null;
			while (lRs.next())
			{

				cmnLookupMstObj = new CmnLookupMst();
				cmnLookupMstObj.setLookupName(lRs.getString("lookup_name"));
				cmnLookupMstObj.setLookupDesc(lRs.getString("lookup_desc"));
				arrReligionLookupDataList.add(cmnLookupMstObj);
			}
		} 

		catch (Exception e)
		{
			StringBuffer lSbError = new StringBuffer(62);
			lSbError.append("Exception In E-Profile report When getting ReligionLookup List");
			lSbError.append(e);
			String lStrError = lSbError.toString();
			logger.error(lStrError);
		}
		return arrReligionLookupDataList;
	}
	public ArrayList getCastLookupName(String parentParam,String langId,String locId)
	{
		logger.info("In getCastLookupName==============");
		ArrayList<CmnLookupMst> arrCastLookupDataList = new ArrayList<CmnLookupMst>();
		Connection lCon = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";
			
			StringBuffer sqlOrgDeptMst = new StringBuffer();
			sqlOrgDeptMst = new StringBuffer("SELECT lookup_id FROM cmn_lookup_mst c WHERE c.lookup_name='"+parentParam+"' AND c.lang_id=?");
			
			lStrSqlQuery = "";
			lStrSqlQuery = sqlOrgDeptMst.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);
			logger.info("lPStmt:::4>>> "+lPStmt.toString());
			
			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}
			lRs = lPStmt.executeQuery();
			lRs.first();

			sql = new StringBuffer("SELECT lookup_name,lookup_desc FROM cmn_lookup_mst c ");
			sql.append("WHERE lang_id=? And parent_lookup_id="+ lRs.getLong("lookup_id")+" order by lookup_desc");
			lStrSqlQuery = "";
			lStrSqlQuery = sql.toString();
			
			lPStmt = lCon.prepareStatement(lStrSqlQuery);

			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}

			logger.info("lPStmt CastLookup>>>>>>>>>> 5"+lPStmt.toString());	
			lRs = lPStmt.executeQuery();
			
			int count = lRs.getFetchSize();
			logger.info("CastLookup count=============="+count);
			
			CmnLookupMst cmnLookupMstObj = null;
			while (lRs.next())
			{
				cmnLookupMstObj = new CmnLookupMst();
				cmnLookupMstObj.setLookupName(lRs.getString("lookup_name"));
				cmnLookupMstObj.setLookupDesc(lRs.getString("lookup_desc"));
				arrCastLookupDataList.add(cmnLookupMstObj);
			}
		} 
		catch (Exception e)
		{
			StringBuffer lSbError = new StringBuffer(58);
			lSbError.append("Exception In E-Profile report When getting CastLookup List");
			lSbError.append(e);
			String lStrError = lSbError.toString();
			logger.error(lStrError);
		}
		return arrCastLookupDataList;
	}
	public ArrayList getRecSrcLookupName(String langId,String locId)
	{
		logger.info("In getRecSrcLookupName==============");
		ArrayList<CmnLookupMst> arrRecSrcLookupDataList = new ArrayList<CmnLookupMst>();

		Connection lCon = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";
			
			sql = new StringBuffer("SELECT c1.lookup_name, c1.lookup_desc FROM cmn_lookup_mst c, cmn_lookup_mst c1 ");
			sql.append("WHERE c.lookup_name = 'rec_source' AND c1.parent_lookup_id =c.lookup_id AND c1.lang_id=? order by lookup_desc");
			lStrSqlQuery = "";
			lStrSqlQuery = sql.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);

			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}

			logger.info("lPStmt RecSrcLookup>>>>>>>>>> 6"+lPStmt.toString());	
			lRs = lPStmt.executeQuery();
			
			
			int count = lRs.getFetchSize();
			logger.info("RecSrcLookup count=============="+count);
			
			CmnLookupMst cmnLookupMstObj = null;
			while (lRs.next())
			{
				cmnLookupMstObj = new CmnLookupMst();
				cmnLookupMstObj.setLookupName(lRs.getString("lookup_name"));
				cmnLookupMstObj.setLookupDesc(lRs.getString("lookup_desc"));
				arrRecSrcLookupDataList.add(cmnLookupMstObj);
			}
		} 

		catch (Exception e)
		{
			StringBuffer lSbError = new StringBuffer(60);
			lSbError.append("Exception In E-Profile report When getting RecSrcLookup List");
			lSbError.append(e);
			String lStrError = lSbError.toString();
			logger.error(lStrError);
		}
		return arrRecSrcLookupDataList;
	}
	public ArrayList getGenderLookupName(String langId,String locId)
	{
		logger.info("In getGenderLookupName==============");
		ArrayList<CmnLookupMst> arrGenderLookupDataList = new ArrayList<CmnLookupMst>();

		Connection lCon = null;
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";
			
			sql = new StringBuffer("SELECT c1.lookup_name, c1.lookup_desc FROM cmn_lookup_mst c, cmn_lookup_mst c1 ");
			sql.append("WHERE c.lookup_name = 'Gender'  AND c1.parent_lookup_id =c.lookup_id AND c1.lang_id=?;");
			lStrSqlQuery = "";
			lStrSqlQuery = sql.toString();

			lPStmt = lCon.prepareStatement(lStrSqlQuery);

			if (langId.equalsIgnoreCase("en_US") || langId.equalsIgnoreCase("1")) 
			{
				lPStmt.setLong(1, Long.valueOf(1l));
			}
			else 
			{
				lPStmt.setLong(1, Long.valueOf(2l));
			}

			logger.info("lPStmt GenderLookup>>>>>>>>>>7"+lPStmt.toString());	
			lRs = lPStmt.executeQuery();
			
			
			int count = lRs.getFetchSize();
			logger.info("GenderLookup count=============="+count);
			
			CmnLookupMst cmnLookupMstObj = null;
			while (lRs.next())
			{
				cmnLookupMstObj = new CmnLookupMst();
				cmnLookupMstObj.setLookupName(lRs.getString("lookup_name"));
				cmnLookupMstObj.setLookupDesc(lRs.getString("lookup_desc"));
				arrGenderLookupDataList.add(cmnLookupMstObj);
			}
		} 

		catch (Exception e)
		{
			StringBuffer lSbError = new StringBuffer(60);
			lSbError.append("Exception In E-Profile report When getting GenderLookup List");
			lSbError.append(e);
			String lStrError = lSbError.toString();
			logger.error(lStrError);
		}
		return arrGenderLookupDataList;
	}
	public Collection findReportData(ReportVO report, Object criteria) throws ReportException 
	{
		Connection lCon = null;
		Statement lStmt = null;
		ResultSet lRs = null;
		PreparedStatement lPStmt = null;
		
		ArrayList DataList = new ArrayList();
		String lStrSqlQuery = "";
		
		try 
		{
			List empDataList = null;
			lCon = DBConnection.getConnection();
			lStmt = (Statement) lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			String sWhereClause= "";
			String strFromDate="";
			String strToDate="";
			long lCounter=0;
			String religionIdList="";
			String casteIdList="";
			String LangId = report.getLangId();
			long langId = 0l;
			
			if (LangId.equalsIgnoreCase("en_US") || LangId.equalsIgnoreCase("1")) {
				langId = 1l;
			}
			else {
				langId = 2l;
			}
			
			String locId = report.getParameterValue("location_name").toString();
			logger.info("locId======"+locId);
			String dsgnId = report.getParameterValue("dsgn_id").toString();
			logger.info("dsgnId======"+dsgnId);
			String empName = report.getParameterValue("employee_name").toString().toLowerCase();
			logger.info("empName======"+empName);
			String strDateFrom = report.getParameterValue("dojfrom").toString();
			logger.info("dateFrom======"+strDateFrom);
			String strDateTo = report.getParameterValue("dojto").toString();
			logger.info("dateTo======"+strDateTo);
			String genderId = report.getParameterValue("gender_lookupName").toString();
			logger.info("genderId======"+genderId);
			String religionId = report.getParameterValue("religion_lookupName").toString();
			logger.info("religionId======"+religionId);
			String casteId = report.getParameterValue("cast_lookupName").toString();
			logger.info("casteId======"+casteId);
			String departmentCode = report.getParameterValue("department_name").toString();
			String rcrtSrcLookupName = report.getParameterValue("recSrc_lookupName").toString();
			
			if(!"".equals(strDateFrom))
			{
				Date fromDate = StringUtility.convertStringToDate(strDateFrom);
				strFromDate = dateFormat.format(fromDate);
			}
			if(!"".equals(strDateTo))
			{
				Date toDate = StringUtility.convertStringToDate(strDateTo);
				strToDate = dateFormat.format(toDate);
			}
			if(!"-1".equals(religionId))
			{
				religionIdList=this.getCommaSertLookupIdList(religionId, lCon);
			}
			if(!"-1".equals(casteId))
			{
				casteIdList=this.getCommaSertLookupIdList(casteId, lCon);
			}
			
			
			if(!"-1".equals(locId))
			{
				sWhereClause = " and clm.loc_id="+locId;
			}
			else
			{
				String strLocationIds = this.getAllLocationByDepartment(langId, departmentCode, lCon);
				if (!"".equals(strLocationIds))
				{
					sWhereClause = " and clm.loc_id in ("+ strLocationIds +")";
				}
				else
				{
					sWhereClause = " and clm.loc_id in (0)";
				}
			}
			
			if(!"-1".equals(dsgnId))
				sWhereClause = sWhereClause + " and oedg.dsgn_id = "+dsgnId;
			
			if(!"".equals(empName))
				sWhereClause = sWhereClause + " and lower(concat(oem.emp_fname, ' ', case when oem.emp_mname is null then oem.emp_lname else concat(oem.emp_mname, ' ', oem.emp_lname) end)) like '%"+empName+"%'";
			
			if(!"".equals(strFromDate) && !"".equals(strToDate))
			{
				sWhereClause = sWhereClause + " and oem.emp_doj between '"+strFromDate+"' and '"+strToDate+"'";
			}
			else if(!"".equals(strFromDate))
			{
				sWhereClause = sWhereClause + " and oem.emp_doj >= '"+strFromDate+"'";
			}
			
			if(!"".equals(religionIdList))
			{
				sWhereClause = sWhereClause + " and heem.emp_religion_id in ("+religionIdList+")";
			}
			
			if(!"-1".equals(rcrtSrcLookupName))
			{
				sWhereClause = sWhereClause + " and clm_rs.lookup_name = '"+ rcrtSrcLookupName +"'";
			}
			
			if(!"".equals(casteIdList))
			{
				sWhereClause = sWhereClause + " and heem.emp_caste_id in ("+casteIdList+")";
			}
			
			if("Male".equals(genderId))
			{
				sWhereClause = sWhereClause + " and heem.emp_gender='M'";
			}
			else if("Female".equals(genderId))
			{
				sWhereClause = sWhereClause + " and heem.emp_gender='F'";
			}
			else if("Eunuch".equals(genderId))
			{
				sWhereClause = sWhereClause + " and heem.emp_gender='E'";
			}
			
			String strPostLookupIds = this.getCommaSertLookupIdList("Primary_Post", lCon);
				
			/*sql = new StringBuilder("select oem.user_id as user_id,oem_eprf.emp_id as eng_empId,  concat(oem.emp_fname, ' ', case when oem.emp_mname is null then oem.emp_lname else concat(oem.emp_mname, ' ', oem.emp_lname) end) as name,");
			sql.append(" clm_rs_desc.lookup_desc as rcrt_src,oem.emp_dob as dob,oem.emp_doj as doj,odm.dsgn_name as dsgn_name,opm.post_name as post_name,ccm.city_name as city_name,cdm.district_name as dst_name,oupr.start_date as post_date,hpm.rev_pay as pay");
			sql.append(" from org_emp_mst oem,org_emp_dsgn_mpg oedg,org_designation_mst odm,org_emp_mst oem_eprf,cmn_lookup_mst clm_rs,");
			sql.append(" cmn_lookup_mst clm_rs_desc,hr_eis_emp_mst heem,org_post_details_rlt opm,org_userpost_rlt oupr left join  hr_payfix_mst hpm on oupr.user_id = hpm.user_id and pay_fix_date <= sysdate() and nxt_incr_date >=sysdate(),");
			sql.append(" cmn_location_mst clm left join cmn_city_mst ccm on clm.loc_city_id =ccm.city_id left join cmn_district_mst cdm on clm.loc_district_id = cdm.district_id");
			sql.append(" where oem.user_id = oupr.user_id and oupr.userpost_type_lookup_id in (?)  and oem.lang_id = ? and");
			sql.append(" oupr.start_date = (SELEct max(r2.start_date) from org_userpost_rlt r2 where oupr.user_id =r2.user_id) and");
			sql.append(" oem.user_id = oupr.user_id and oupr.activate_flag=1  and oem.lang_id = ? and");
			sql.append(" oupr.post_id = opm.post_id and opm.lang_id = ? and opm.loc_id = clm.loc_id and oedg.dsgn_id=odm.dsgn_id and");
			sql.append(" oem.emp_id = oedg.emp_id and oedg.activate_flag=1 and oem_eprf.emp_id=heem.emp_mpg_id and oem_eprf.lang_id=1");
			sql.append(" and oem.user_id = oem_eprf.user_id and heem.emp_recruit_src = clm_rs.lookup_id");
			sql.append(" and clm_rs.lookup_name = clm_rs_desc.lookup_name and clm_rs_desc.lang_id = ?"+sWhereClause);
			sql.append(" group by oem.user_id order by oem.user_id, hpm.pay_fix_date;");*/
			
			/*sql = new StringBuilder("select oem.user_id as user_id,oem_eprf.emp_id as eng_empId,  concat(oem.emp_fname, ' ', case when oem.emp_mname is null then oem.emp_lname else concat(oem.emp_mname, ' ', oem.emp_lname) end) as name,");
			sql.append(" clm_rs_desc.lookup_desc as rcrt_src,oem.emp_dob as dob,oem.emp_doj as doj,odm.dsgn_name as dsgn_name,opm.post_name as post_name,ccm.city_name as city_name,cdm.district_name as dst_name,oupr.start_date as post_date,hpm.rev_pay as pay");
			sql.append(" from org_emp_mst oem,org_emp_dsgn_mpg oedg,org_designation_mst odm,org_emp_mst oem_eprf,");
			sql.append(" org_post_details_rlt opm,hr_eis_emp_mst heem left join cmn_lookup_mst clm_rs on heem.emp_recruit_src = clm_rs.lookup_id");
			sql.append(" left join cmn_lookup_mst clm_rs_desc on clm_rs.lookup_name = clm_rs_desc.lookup_name and clm_rs_desc.lang_id = ?,");
			sql.append(" org_userpost_rlt oupr left join  hr_payfix_mst hpm on oupr.user_id = hpm.user_id and pay_fix_date <= sysdate() and nxt_incr_date >=sysdate(),");
			sql.append(" cmn_location_mst clm left join cmn_city_mst ccm on clm.loc_city_id =ccm.city_id left join cmn_district_mst cdm on clm.loc_district_id = cdm.district_id");
			sql.append(" where oem.user_id = oupr.user_id and oupr.userpost_type_lookup_id in (?)  and oem.lang_id = ? and");
			sql.append(" oupr.start_date = (SELEct max(r2.start_date) from org_userpost_rlt r2 where oupr.user_id =r2.user_id) and");
			sql.append(" oem.user_id = oupr.user_id and oupr.activate_flag=1  and oem.lang_id = ? and");
			sql.append(" oupr.post_id = opm.post_id and opm.lang_id = ? and opm.loc_id = clm.loc_id and oedg.dsgn_id=odm.dsgn_id and");
			sql.append(" oem.emp_id = oedg.emp_id and oedg.activate_flag=1 and oem_eprf.emp_id=heem.emp_mpg_id and oem_eprf.lang_id=1");
			sql.append(" and oem.user_id = oem_eprf.user_id "+sWhereClause);
			sql.append(" group by oem.user_id order by oem.user_id, hpm.pay_fix_date;");
			*/
			
			String strQualSql = "select distinct(a.user_id) as user_id from hr_Eis_qual_dtl a;";
			ResultSet rSet = lStmt.executeQuery(strQualSql);
			List<Long> lstOfUserOfQual = new ArrayList<Long>();
			while(rSet.next())
			{
				lstOfUserOfQual.add(rSet.getLong("user_id"));
			}
			rSet.close();
			
			String strCntctSql = "select distinct(a.emp_id) as emp_id from org_empcontact_mst a;";
			rSet = lStmt.executeQuery(strCntctSql);
			List<Long> lstOfEmpOfCntct = new ArrayList<Long>();
			while(rSet.next())
			{
				lstOfEmpOfCntct.add(rSet.getLong("emp_id"));
			}
			rSet.close();
			
			StringBuilder stringBuilder = new StringBuilder("select oem.user_id as user_id,oem_eprf.emp_id as eng_empId,concat(oem.emp_fname, ' ', case when oem.emp_mname is null then oem.emp_lname else concat(oem.emp_mname, ' ', oem.emp_lname) end) as name,");
			stringBuilder.append(" clm_rs_desc.lookup_desc as rcrt_src,oem.emp_dob as dob,oem.emp_doj as doj,odm.dsgn_name as dsgn_name,opm.post_name as post_name,ccm.city_name as city_name,cdm.district_name as dst_name,oupr.start_date as post_date,hpm.rev_pay as pay");
			stringBuilder.append(" from org_emp_mst oem left join org_userpost_rlt oupr on oem.user_id = oupr.user_id and oupr.userpost_type_lookup_id in (?) and oupr.activate_flag=1 left join org_post_details_rlt opm on oupr.post_id = opm.post_id and opm.lang_id = ?");
			stringBuilder.append(" left join cmn_location_mst clm on opm.loc_id = clm.loc_id");
			stringBuilder.append(" left join cmn_city_mst ccm on clm.loc_city_id =ccm.city_id");
			stringBuilder.append(" left join cmn_district_mst cdm on clm.loc_district_id = cdm.district_id");
			stringBuilder.append(" left join org_emp_dsgn_mpg oedg on oem.emp_id = oedg.emp_id and oedg.activate_flag=1");
			stringBuilder.append(" left join org_designation_mst odm on oedg.dsgn_id=odm.dsgn_id");
			stringBuilder.append(" left join org_emp_mst oem_eprf on oem_eprf.lang_id=1 and oem.user_id = oem_eprf.user_id");
			stringBuilder.append(" left join hr_eis_emp_mst heem on oem_eprf.emp_id=heem.emp_mpg_id");
			stringBuilder.append(" left join cmn_lookup_mst clm_rs on heem.emp_recruit_src = clm_rs.lookup_id");
			stringBuilder.append(" left join cmn_lookup_mst clm_rs_desc on clm_rs.lookup_name = clm_rs_desc.lookup_name and clm_rs_desc.lang_id = ?");
			stringBuilder.append(" left join hr_payfix_mst hpm on oupr.user_id = hpm.user_id and pay_fix_date <= sysdate() and nxt_incr_date >=sysdate()");
			stringBuilder.append(" and oupr.start_date = (SELEct max(r2.start_date) from org_userpost_rlt r2 where oupr.user_id =r2.user_id)");
			stringBuilder.append(" where oem.lang_id = ? "+sWhereClause);
			stringBuilder.append(" group by oem.user_id order by oem.user_id, hpm.pay_fix_date;");
			
			
		
			lStrSqlQuery = stringBuilder.toString();
			logger.info("====================SQL=============="+ lStrSqlQuery);
			lPStmt = lCon.prepareStatement(lStrSqlQuery);
			lPStmt.setString(1, strPostLookupIds);
			lPStmt.setLong(2, langId);
			lPStmt.setLong(3, langId);
			lPStmt.setLong(4, langId);
			//lPStmt.setLong(5, langId);
			lRs = lPStmt.executeQuery();
			
			while(lRs.next())
			{
				String dsgnName="";
				empDataList = new ArrayList();
				empDataList.add(String.valueOf(++lCounter));
				
				if(lRs.getString("name") != null)
				{
					empDataList.add(lRs.getString("name"));
				}
				else
				{
					empDataList.add("-");
				}
								
				if(lRs.getString("dob") != null)
				{
					empDataList.add(simpleDateFormat.format(lRs.getDate("dob")));
				}
				else
				{
					empDataList.add("-");
				}
				
				if(lRs.getString("doj") != null)
				{
					empDataList.add(simpleDateFormat.format(lRs.getDate("doj")));
				}
				else
				{
					empDataList.add("-");
				}
				
				if(lRs.getString("dsgn_name") != null)
				{
					dsgnName=dsgnName+lRs.getString("dsgn_name")+", ";
				}
				else
				{
					dsgnName=dsgnName+"-"+", ";
				}
				
				if(lRs.getString("post_name") != null)
				{
					dsgnName=dsgnName+lRs.getString("post_name")+", ";
				}
				else
				{
					dsgnName=dsgnName+"-"+", ";
				}
				
				if(lRs.getString("city_name") != null)
				{
					dsgnName=dsgnName+lRs.getString("city_name")+", ";
				}
				else
				{
					dsgnName=dsgnName+"-"+", ";
				}
				
				if(lRs.getString("dst_name") != null)
				{
					dsgnName=dsgnName+lRs.getString("dst_name")+", ";
				}
				else
				{
					dsgnName=dsgnName+"-";
				}
				
				empDataList.add(dsgnName);
				
				if(lRs.getString("post_date") != null)
				{
					empDataList.add(simpleDateFormat.format(lRs.getDate("post_date")));
				}
				else
				{
					empDataList.add("-");
				}
				
				String qualificationName="";
				if(lstOfUserOfQual.contains(lRs.getLong("user_id")))
				{
					qualificationName=this.getQualificationDesc(lRs.getLong("user_id"), langId, lCon);
				}
				
				if(!qualificationName.equals(""))
				{
					empDataList.add(qualificationName);
				}
				else
				{
					empDataList.add("-");
				}
				
				if(lRs.getString("rcrt_src") != null)
				{
					empDataList.add(lRs.getString("rcrt_src"));
				}
				else
				{
					empDataList.add("-");
				}
				
				String strContactDtls="-,-,-";
				if(lstOfEmpOfCntct.contains(lRs.getLong("eng_empId")))
				{
					strContactDtls = this.getTelephoneInfo(lRs.getLong("eng_empId"), lCon);
				}
				empDataList.add(strContactDtls);
				
				if(lRs.getString("pay") != null)
				{
					empDataList.add(lRs.getString("pay"));
				}
				else
				{
					empDataList.add("-");
				}
				
				DataList.add(empDataList);
			}
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return DataList;
	}
	
	private String getCommaSertLookupIdList(String lookupName,Connection lCon) throws SQLException
	{
		ResultSet lRs = null;
		String strLookupIds = "";
		
		String sql="";
		sql="select lookup_id from cmn_lookup_mst where lookup_name='"+lookupName+"'";
		
		try
		{
			Statement lStmt = (Statement) lCon.createStatement();
			lRs=lStmt.executeQuery(sql);
			
			while(lRs.next())
			{
	    		long lookupId = lRs.getLong("lookup_id");
				if (lookupId != 0)
				{
					strLookupIds = strLookupIds + "," + lookupId;
				}
	    	}
	    	if (strLookupIds.length() > 0)
	    	{
	    		strLookupIds = strLookupIds.substring(1);
	    	}
		}
		catch(SQLException sqle)
		{
			logger.info("============== Error in Lookup Select ================"+sqle);
		}
		catch(Exception ex)
		{
			logger.info("============== Error in Lookup Select ================"+ex);
		}
		return strLookupIds;
	}
	private String getQualificationDesc(long userId,long langId,Connection lCon) 
	{
		ResultSet lRs = null;
		String strCommaQualDesc = "";
		
		StringBuffer sql = new StringBuffer("select clm_desc.lookup_desc  as qual_desc,clm_dscp_dsc.lookup_desc as discpline_desc ");
		sql.append("from cmn_lookup_mst clm, cmn_lookup_mst clm_desc,hr_eis_qual_dtl heqd ");
		sql.append("left join cmn_lookup_mst clm_dscp on heqd.dicipline=clm_dscp.lookup_id ");
		sql.append("left join cmn_lookup_mst clm_dscp_dsc on clm_dscp.lookup_name =  clm_dscp_dsc.lookup_name ");
		sql.append("and clm_dscp_dsc.lang_id = "+ langId +" and clm_dscp_dsc.lookup_name != 'SELECT' ");
		sql.append("where heqd.sub_qualification_id=clm.lookup_id and delete_flag='N' and clm.lookup_name = clm_desc.lookup_name and ");
		sql.append("clm_desc.lang_id = "+ langId +" and user_id = "+ userId);
		
		try
		{
			Statement lStmt = (Statement) lCon.createStatement();
			lRs=lStmt.executeQuery(sql.toString());
			while(lRs != null && lRs.next())
			{
				String strQualDesc = "";
				String strQualDesc1 = lRs.getString("qual_desc");
				String strQualDesc2 = lRs.getString("discpline_desc");
				
				if (strQualDesc1 != null && !strQualDesc1.equals(""))
				{
					strQualDesc = strQualDesc1;
				}
				
				if (strQualDesc2 != null && !strQualDesc2.equals(""))
				{
					strQualDesc = strQualDesc + "-" + strQualDesc2;
				}
					
				if (!strQualDesc.equals(""))
				{
					strCommaQualDesc = strCommaQualDesc + "," + strQualDesc;
				}
	    	}
	    	if (strCommaQualDesc.length() > 0)
	    	{
	    		strCommaQualDesc = strCommaQualDesc.substring(1);
	    	}
		}
		catch(SQLException sqle)
		{
			logger.info("============== Error in Qualification Select ================"+sqle);
		}
		catch(Exception ex)
		{
			logger.info("============== Error in Qualification Select ================"+ex);
		}
		
		return strCommaQualDesc;
	}
	private String getTelephoneInfo(long engEmpId,Connection lCon) throws SQLException
	{
		ResultSet lRs = null;
		String strCommaContactNos = "";
		String strResidenceNo = "";
		String strOfficeNo = "";
		String strMobile = "";
		
		StringBuffer sql = new StringBuffer("select clm.lookup_name as lookupName, oecm.contact_number as cntc_no ");
		sql.append("from org_empcontact_mst oecm,cmn_lookup_mst clm ");
		sql.append("where oecm.type_lookup_id = clm.lookup_id and clm.lookup_name in ('emp_Contact_Phone','emp_Contact_PhoneO','emp_Contact_Mobile') and ");
		sql.append("oecm.emp_id = "+ engEmpId +"");
		
		try
		{
			Statement lStmt = (Statement) lCon.createStatement();
			lRs=lStmt.executeQuery(sql.toString());
			
			while(lRs != null && lRs.next())
			{
				if (lRs.getString("lookupName").equals("emp_Contact_Phone"))
				{
					strResidenceNo = strResidenceNo + "/" + lRs.getString("cntc_no");
				}
				else if (lRs.getString("lookupName").equals("emp_Contact_PhoneO"))
				{
					strOfficeNo = strOfficeNo + "/" + lRs.getString("cntc_no");
				}
				else if (lRs.getString("lookupName").equals("emp_Contact_Mobile"))
				{
					strMobile = strMobile + "/" + lRs.getString("cntc_no");
				}
	    	}
			boolean subStringFlag=false;
			if (strResidenceNo.length() > 0)
			{
				strCommaContactNos = strCommaContactNos + ",<br>"+ strResidenceNo.substring(1);
				subStringFlag=true;
			}
			else
			{
				strCommaContactNos = strCommaContactNos + ",-";
			}
			
			if (strOfficeNo.length() > 0)
			{
				strCommaContactNos = strCommaContactNos + ",<br>"+ strOfficeNo.substring(1);
			}
			else
			{
				strCommaContactNos = strCommaContactNos + ",-";
			}
			
			if (strMobile.length() > 0)
			{
				strCommaContactNos = strCommaContactNos + ",<br>"+ strMobile.substring(1);
			}
			else
			{
				strCommaContactNos = strCommaContactNos + ",-";
			}
	    	
	    	
			if(subStringFlag)
			{
				if (strCommaContactNos.length() > 4)
				{
					strCommaContactNos = strCommaContactNos.substring(5);
				}
			}
			else if (strCommaContactNos.length() > 0)
			{
	    			strCommaContactNos = strCommaContactNos.substring(1);
			}
		}
		catch(SQLException sqle)
		{
			logger.info("============== Error in contact Select ================"+sqle);
		}
		catch(Exception ex)
		{
			logger.info("============== Error in contact Select ================"+ex);
		}
		
		return strCommaContactNos;
	}
	
	public String getAllLocationByDepartment(long langId,String departmentCode, Connection lCon)
	{
		ResultSet lRs = null;
		String strLocIds = "";
		
		String sql="";
		sql="select loc_id from cmn_location_mst clm, org_department_mst odm where odm.department_code='"+ departmentCode +"' and odm.lang_id="+ langId +" and clm.department_id=odm.department_id";
		
		try
		{
			Statement lStmt = (Statement) lCon.createStatement();
			lRs=lStmt.executeQuery(sql);
			while(lRs.next())
			{
	    		long lookupId = lRs.getLong("loc_id");
				if (lookupId != 0)
				{
					strLocIds = strLocIds + "," + lookupId;
				}
	    	}
	    	if (strLocIds.length() > 0)
	    	{
	    		strLocIds = strLocIds.substring(1);
	    	}
		}
		catch(SQLException sqle)
		{
			logger.info("============== Error in strLocIds Select ================"+sqle);
		}
		catch(Exception ex)
		{
			logger.info("============== Error in strLocIds Select ================"+ex);
		}
		
		return strLocIds;
		 
	 }
}
