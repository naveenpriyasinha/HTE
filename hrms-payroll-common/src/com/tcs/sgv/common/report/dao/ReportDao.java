package com.tcs.sgv.common.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
/**
 * @author 217977
 *
 */

public class ReportDao extends DefaultReportDataFinder implements ReportDataFinder{

	Log logger = LogFactory.getLog(getClass());
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";
	
	public Collection findReportData (ReportVO report, Object criteria)
	throws ReportException {
		//System.out.println("Entered Inot DAO >>>");
		logger.info("Inside findReportData of ReportDao Class::");
		
		String langId = report.getLangId();
		String locId = report.getLocId();
		Connection lCon = null;
		Statement lStmt = null;
		Statement lStmt1 = null;		
		Session hibSession = null;		
		ArrayList DataList = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//Added by Kunal for setting up font size
		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO();
		baseFont[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		baseFont[0].setStyleValue("13");
		
		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER, baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT, baseFont);
		report.setReportTemplate(rt);
		
		Hashtable requestKeys = (Hashtable) ((Hashtable) criteria).get(IReportConstants.REQUEST_KEYS);
		
		try {
			lCon = DBConnection.getConnection();
			lStmt = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			lStmt1 = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
			List pendingList = null;
		
			Hashtable sessionKeys = (Hashtable) ((Hashtable) criteria).get(IReportConstants.SESSION_KEYS);
			Map loginDetail = (HashMap) sessionKeys.get("loginDetailsMap");
		
			Long empId = (Long) loginDetail.get("empId");
			Long userId=(Long) loginDetail.get("userId");
			Long langID=(Long) loginDetail.get("langId");
			PreparedStatement lPStmt = null;
			ResultSet lRs = null;
			ResultSet lRs2 = null;
			ResultSet Corr2 = null;
			ResultSet Corr1 = null;
			ResultSet lRs3 = null;
			ResultSet lRs4 = null;
			StringBuffer sql = new StringBuffer();
			StringBuffer Modid=new StringBuffer();
			StringBuffer corrId=new StringBuffer();
			StringBuffer modcorrid=new StringBuffer();
			
			StringBuffer lookupidforfile=new StringBuffer();
			StringBuffer fileDtls=new StringBuffer();
			String lStrSqlQuery = "";
			
			String moduleid="330022,330023,330024,300025,300010,300011,300012,300015,330026";
			String corrmoduleid="300013,300005,300006,300007,300020,300021,320401,320400,330027,330028,330029,330030,300001,300002,300003";
			String appname=null;
			/*String fromdate="null";
			String todate="null";*/
			String fromdate=null;
			String todate=null;
			String appName[]=null;
			String NoOfDays="";
			String operator="";
			String appliedDt=null;
			String appliedTo=null;
			String appType=null;
			
			SimpleDateFormat simpledt = new SimpleDateFormat("yyyy-MM-dd");
			
			Modid = new StringBuffer("select req_id,mod_id from hr_mod_emp_rlt where mod_id in("+moduleid+" )");
			lStrSqlQuery = "";
			lStrSqlQuery = Modid.toString();
			lPStmt = lCon.prepareStatement(lStrSqlQuery);		
			lRs2 = lPStmt.executeQuery();
			
			modcorrid = new StringBuffer("select req_id,mod_id from hr_mod_emp_rlt where mod_id in("+corrmoduleid+" )");
			lStrSqlQuery = "";
			lStrSqlQuery = modcorrid.toString();
			lPStmt = lCon.prepareStatement(lStrSqlQuery);		
			Corr1 = lPStmt.executeQuery();
			
			if(report.getReportCode().equals("300050") || report.getReportCode().equals("300053")){
				
				logger.info("Inside 300050 ::");
				
//				Map fileMap = new HashMap();
				Map postMap = new HashMap();
				Map modMap = new HashMap();
				Map createddate=new HashMap();
				ArrayList postId=new ArrayList();
				ArrayList fileId=new ArrayList();
				String reqID=null;
				String jobRefId=null;
				
				
				
				if(report.getParameterValue("appname") != null && !report.getParameterValue("appname").equals("")){
					appname=report.getParameterValue("appname").toString();
				}
				
				
				NoOfDays=report.getParameterValue("NoOfDays").toString();
				//System.out.println("NoOfDays IN DAO >> "+NoOfDays);
				
				operator=report.getParameterValue("operator").toString();
				//System.out.println("operator IN DAO >> "+operator);
				
				fromdate=report.getParameterValue("fromdate").toString();
				
				todate=report.getParameterValue("todate").toString();
				//System.out.println("TO DATE in DAO >> "+todate);
				
				appliedDt=report.getParameterValue("appliedDt").toString();
				//System.out.println("TO appliedDt in DAO >> "+appliedDt);
				
				appliedTo=report.getParameterValue("appliedTo").toString();
				//System.out.println("Applied TO in DAO >> "+appliedTo);
				
				appType=report.getParameterValue("apptype").toString();
				
				if(appType.equals("f")){
					
					if(appname!=null && !appname.equals("0"))
					{
						moduleid=appname;
						//System.out.println("module ID"+moduleid);
					}
					
					
					if(report.getParameterValue("operator").toString().equals("0"))
					{
						if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
							
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
						}
						
					}else if (report.getParameterValue("operator").toString().equals("1")) {
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
						
					}else if(report.getParameterValue("operator").toString().equals("2")){
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
						
					}
					
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);		
//					System.out.println("lRs Query:: "+lPStmt);
					lRs = lPStmt.executeQuery();
					
					while(lRs.next()){
						//System.out.println("Inside lRs while loop:::");
						jobRefId=lRs.getString("job_ref_id");
						
						lRs2.beforeFirst();
						while(lRs2.next()){
							
							reqID=String.valueOf(lRs2.getLong("req_id"));
							
							if(jobRefId.equals(reqID)){
								
								/*fileId.add(lRs.getString("job_seq_id"));
								postMap.put(lRs.getString("job_seq_id"), lRs.getString("lst_act_post_id"));
								postId.add(lRs.getString("lst_act_post_id"));
								modMap.put(lRs.getString("job_seq_id"), lRs2.getLong("mod_id"));
								createddate.put(lRs.getString("job_seq_id"), lRs.getDate("crt_dt"));*/
								
								fileId.add(lRs.getString("job_ref_id"));
								postMap.put(lRs.getString("job_ref_id"), lRs.getString("lst_act_post_id"));
								postId.add(lRs.getString("lst_act_post_id"));
								modMap.put(lRs.getString("job_ref_id"), lRs2.getLong("mod_id"));
								createddate.put(lRs.getString("job_ref_id"), lRs.getDate("crt_dt"));
							}
						}
					}
				}else if(appType.equals("c")){
					if(appname!=null && !appname.equals("0"))
					{
						corrmoduleid=appname;
						//System.out.println("module ID"+moduleid);
					}
					
					
					if(report.getParameterValue("operator").toString().equals("0"))
					{
						if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
							
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='Correspondence'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
						}
						
					}else if (report.getParameterValue("operator").toString().equals("1")) {
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
						
					}else if(report.getParameterValue("operator").toString().equals("2")){
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
						
					}
					
					
					//corrId = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='Correspondence' order  by crt_dt desc");
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);
//					System.out.println("Corr Query >>> "+lPStmt);
					Corr2 = lPStmt.executeQuery();
					
					while(Corr2.next()){
						//System.out.println("Inside lRs while loop:::");
						jobRefId=Corr2.getString("job_ref_id");
						
						Corr1.beforeFirst();
						while(Corr1.next()){
							
							reqID=String.valueOf(Corr1.getLong("req_id"));
							
							if(jobRefId.equals(reqID)){
								
								fileId.add(Corr2.getString("job_ref_id"));
								postMap.put(Corr2.getString("job_ref_id"), Corr2.getString("lst_act_post_id"));
								postId.add(Corr2.getString("lst_act_post_id"));
								modMap.put(Corr2.getString("job_ref_id"), Corr1.getLong("mod_id"));
								createddate.put(Corr2.getString("job_ref_id"), Corr2.getDate("crt_dt"));
							}
						}
					}
				}else if(appType.equals("b")){
					
					//File
					if(appname!=null && !appname.equals("0"))
					{
						moduleid=appname;
						//System.out.println("module ID"+moduleid);
					}
					
					
					if(report.getParameterValue("operator").toString().equals("0"))
					{
						if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
							
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
						}
						
					}else if (report.getParameterValue("operator").toString().equals("1")) {
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
						
					}else if(report.getParameterValue("operator").toString().equals("2")){
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
						
					}
					
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);		
					lRs = lPStmt.executeQuery();
					
					while(lRs.next()){
						//System.out.println("Inside lRs while loop:::");
						jobRefId=lRs.getString("job_ref_id");
						
						lRs2.beforeFirst();
						while(lRs2.next()){
							
							reqID=String.valueOf(lRs2.getLong("req_id"));
							
							if(jobRefId.equals(reqID)){
								
								fileId.add(lRs.getString("job_ref_id"));
								postMap.put(lRs.getString("job_ref_id"), lRs.getString("lst_act_post_id"));
								postId.add(lRs.getString("lst_act_post_id"));
								modMap.put(lRs.getString("job_ref_id"), lRs2.getLong("mod_id"));
								createddate.put(lRs.getString("job_ref_id"), lRs.getDate("crt_dt"));
							}
						}
					}
					
					
					//Correspondence
					if(appname!=null && !appname.equals("0"))
					{
						corrmoduleid=appname;
						//System.out.println("module ID"+moduleid);
					}
					
					
					if(report.getParameterValue("operator").toString().equals("0"))
					{
						if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
							
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='Correspondence'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
						}
						
					}else if (report.getParameterValue("operator").toString().equals("1")) {
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
						
					}else if(report.getParameterValue("operator").toString().equals("2")){
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
						
					}
					
					
					//corrId = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending')) and job_title='Correspondence' order  by crt_dt desc");
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);
					Corr2 = lPStmt.executeQuery();
					
					while(Corr2.next()){
						//System.out.println("Inside lRs while loop:::");
						jobRefId=Corr2.getString("job_ref_id");
						
						Corr1.beforeFirst();
						while(Corr1.next()){
							
							reqID=String.valueOf(Corr1.getLong("req_id"));
							
							if(jobRefId.equals(reqID)){
								
								fileId.add(Corr2.getString("job_ref_id"));
								postMap.put(Corr2.getString("job_ref_id"), Corr2.getString("lst_act_post_id"));
								postId.add(Corr2.getString("lst_act_post_id"));
								modMap.put(Corr2.getString("job_ref_id"), Corr1.getLong("mod_id"));
								createddate.put(Corr2.getString("job_ref_id"), Corr2.getDate("crt_dt"));
							}
						}
					}
					
				}
			
				
				String postidstring=null;
				
				for (Iterator iter = postId.iterator(); iter.hasNext();) {
					String element = (String) iter.next();
					if(postidstring!=null)
					{
					postidstring=postidstring+element+",";
					}
					else
					{
						postidstring=element+",";
					}
				}
				Map locName=new HashMap();
				Map dsgnMap=new HashMap();
				Map userNameMap = new HashMap();
				if(postidstring!=null)
				{
					
					
					sql=null;
					
					sql = new StringBuffer("SELECT cmnlocMst.loc_name as LocName, desgMst.dsgn_name as DsgnName, userPos.user_id as user_id, userPos.post_id as post_id FROM org_designation_mst desgMst, cmn_location_mst cmnlocMst, org_post_details_rlt postDetRlt, org_userpost_rlt userPos ") ;
					sql.append(" WHERE  cmnlocMst.loc_id in(SELECT postDltRlt.loc_id FROM org_post_details_rlt postDltRlt ");
					sql.append(" WHERE postDltRlt.post_id  in (SELECT orguserPostRlt.post_id FROM org_userpost_rlt orguserPostRlt ");
					sql.append(" WHERE orguserPostRlt.post_id in("+postidstring.substring(0, postidstring.length()-1)+") and orguserPostRlt.activate_flag=1 )AND postDltRlt.lang_id="+langID+") AND desgMst.dsgn_id in(SELECT postDltRlt.dsgn_id FROM org_post_details_rlt postDltRlt ");
					sql.append(" WHERE postDltRlt.post_id  in (SELECT orguserPostRlt.post_id FROM org_userpost_rlt orguserPostRlt ");
					sql.append(" WHERE orguserPostRlt.post_id in("+postidstring.substring(0, postidstring.length()-1)+") and orguserPostRlt.activate_flag=1)AND postDltRlt.lang_id="+langID+") AND userPos.post_id = postDetRlt.post_id AND postDetRlt.loc_id=cmnlocMst.loc_id AND postDetRlt.lang_id="+langID+" AND postDetRlt.dsgn_id=desgMst.dsgn_id AND userPos.activate_flag=1");
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);		
					lRs = lPStmt.executeQuery();
					
					
					ArrayList useridlist=new ArrayList();
					Map userMap = new HashMap();
					
					
					
					while(lRs.next()){
						for (Iterator iter = fileId.iterator(); iter.hasNext();) {
							String element = (String) iter.next();
							

							if(Long.parseLong(postMap.get(element).toString())==lRs.getLong("post_id"))
							{
								userMap.put(element,lRs.getLong("user_id"));
								locName.put(element,lRs.getString("LocName"));
								dsgnMap.put(element, lRs.getString("DsgnName"));
								
								useridlist.add(lRs.getLong("user_id"));
							}
						}
					}
					
					
					
					String useridstring=null;
					for (Iterator iter = useridlist.iterator(); iter.hasNext();) {
						Long element = (Long) iter.next();
						if(useridstring!=null)
						{
						useridstring=useridstring+element+",";
						}
						else
						{
							useridstring=element+",";
						}
					}
					
					
					sql=null;
					sql = new StringBuffer("select * from org_emp_mst where lang_id="+langID+" and user_id in ("+useridstring.substring(0, useridstring.length()-1)+")");
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);		
					lRs = lPStmt.executeQuery();
					
					
					while(lRs.next()){
						//System.out.println("User ID in query:: "+lRs.getLong("user_id"));
						for (Iterator iter = fileId.iterator(); iter.hasNext();) {
							String element = (String) iter.next();
							//System.out.println("UserID in map :: "+userMap.get(element));
							if(Long.parseLong(userMap.get(element).toString())==lRs.getLong("user_id"))
							{
								userNameMap.put(element,lRs.getString("emp_fname")+" "+lRs.getString("emp_mname")+" "+lRs.getString("emp_lname"));
							}
						}
					}
				}
				
				
				Integer index=new Integer(1);
				Date crtdate = null;
				
				for (Iterator iter = fileId.iterator(); iter.hasNext();) {
					String element = (String) iter.next();
					
					pendingList=new ArrayList();
					if(Long.parseLong(modMap.get(element).toString())==330023)
					{
						pendingList.add("GPF ADVANCE");
					}
					if(Long.parseLong(modMap.get(element).toString())==330022)
					{
						pendingList.add("GPF SUBSCRIPTION");
					}
					if(Long.parseLong(modMap.get(element).toString())==330024)
					{
						pendingList.add("GPF WITHDRAWAL");
					}
					if(Long.parseLong(modMap.get(element).toString())==300025)
					{
						pendingList.add("Resignation");
					}
					if(Long.parseLong(modMap.get(element).toString())==300013)
					{
						pendingList.add("Quarter Allocation");
					}
					if(Long.parseLong(modMap.get(element).toString())==300010)
					{
						pendingList.add("NOC Passport");
					}
					if(Long.parseLong(modMap.get(element).toString())==300011)
					{
						pendingList.add("NOC Foreign Visit");
					}
					if(Long.parseLong(modMap.get(element).toString())==300012)
					{
						pendingList.add("Foreign Visit Report");
					}
					if(Long.parseLong(modMap.get(element).toString())==300005)
					{
						pendingList.add("Qualification");
					}
					if(Long.parseLong(modMap.get(element).toString())==300006)
					{
						pendingList.add("Nominee");
					}
					if(Long.parseLong(modMap.get(element).toString())==300007)
					{
						pendingList.add("Family");
					}
					if(Long.parseLong(modMap.get(element).toString())==300020)
					{
						pendingList.add("Change Employee Profile");
					}
					if(Long.parseLong(modMap.get(element).toString())==300021)
					{
						pendingList.add("Change Employee  Address");
					}
					if(Long.parseLong(modMap.get(element).toString())==300015)
					{
						pendingList.add("LTC");
					}
					if(Long.parseLong(modMap.get(element).toString())==320401)
					{
						pendingList.add("Asset Submission Of Actuals");
					}
					if(Long.parseLong(modMap.get(element).toString())==320400)
					{
						pendingList.add("Permission to Purchase/Sell Asset");
					}
					
					if(Long.parseLong(modMap.get(element).toString())==330029)
					{
						pendingList.add("Travel Advance");
					}
					if(Long.parseLong(modMap.get(element).toString())==330030)
					{
						pendingList.add("Travel Reimbursment");
					}
					if(Long.parseLong(modMap.get(element).toString())==330028)
					{
						pendingList.add("Travel Cancellation");
					}
					if(Long.parseLong(modMap.get(element).toString())==330027)
					{
						pendingList.add("Travel Pre-Sanction/Post Facto");
					}
					
					if(Long.parseLong(modMap.get(element).toString())==330026)
					{
						pendingList.add("Gpf New Account ");
					}
					if(Long.parseLong(modMap.get(element).toString())==300001)
					{
						pendingList.add("Apply for leave");
					}
					if(Long.parseLong(modMap.get(element).toString())==300002)
					{
						pendingList.add("Cancel Leave");
					}
					if(Long.parseLong(modMap.get(element).toString())==300003)
					{
						pendingList.add("Joining Leave");
					}
					
					pendingList.add(element);
					
					if(userNameMap.get(element)!=null)
					{
						pendingList.add(userNameMap.get(element)+" ("+dsgnMap.get(element)+")");
					}
					else
					{
						pendingList.add("-");
					}
					if(locName.get(element)!=null)
					{
						pendingList.add(locName.get(element));
					}
					else 
					{
						pendingList.add("-");
					}
					
					crtdate=StringUtility.convertStringToDate(sdf.format(createddate.get(element)));
					pendingList.add(sdf.format(createddate.get(element)));
					long noofdays=deltaDays(crtdate,new Date());
					pendingList.add(noofdays);
					
					if(Long.parseLong(modMap.get(element).toString())==330023)
					{
						pendingList.add("330023");
						pendingList.add(index);
					}
					if(Long.parseLong(modMap.get(element).toString())==330022)
					{
						pendingList.add("330022");
						pendingList.add(index+1);
					}
					if(Long.parseLong(modMap.get(element).toString())==330024)
					{
						pendingList.add("330024");
						pendingList.add(index+2);
					}
					if(Long.parseLong(modMap.get(element).toString())==300025)
					{
						pendingList.add("300025");
						pendingList.add(index+3);
					}
					if(Long.parseLong(modMap.get(element).toString())==300013)
					{
						pendingList.add("300013");
						pendingList.add(index+4);
					}
					if(Long.parseLong(modMap.get(element).toString())==300010)
					{
						pendingList.add("300010");
						pendingList.add(index+5);
					}
					if(Long.parseLong(modMap.get(element).toString())==300011)
					{
						pendingList.add("300011");
						pendingList.add(index+6);
					}
					if(Long.parseLong(modMap.get(element).toString())==300012)
					{
						pendingList.add("300012");
						pendingList.add(index+7);
					}
					if(Long.parseLong(modMap.get(element).toString())==300005)
					{
						pendingList.add("300005");
						pendingList.add(index+8);
					}
					if(Long.parseLong(modMap.get(element).toString())==300006)
					{
						pendingList.add("300006");
						pendingList.add(index+9);
					}
					if(Long.parseLong(modMap.get(element).toString())==300007)
					{
						pendingList.add("300007");
						pendingList.add(index+10);
					}
					if(Long.parseLong(modMap.get(element).toString())==300020)
					{
						pendingList.add("300020");
						pendingList.add(index+11);
					}
					if(Long.parseLong(modMap.get(element).toString())==300021)
					{
						pendingList.add("300021");
						pendingList.add(index+12);
					}
					if(Long.parseLong(modMap.get(element).toString())==300015)
					{
						pendingList.add("300015");
						pendingList.add(index+13);
					}
					if(Long.parseLong(modMap.get(element).toString())==320401)
					{
						pendingList.add("320401");
						pendingList.add(index+14);
					}
					if(Long.parseLong(modMap.get(element).toString())==320400)
					{
						pendingList.add("320400");
						pendingList.add(index+15);
					}
					
					 if(Long.parseLong(modMap.get(element).toString())==330029)
					{
						pendingList.add("330029");
						pendingList.add(index+16);
					}
					if(Long.parseLong(modMap.get(element).toString())==330030)
					{
						pendingList.add("330030");
						pendingList.add(index+17);
					}
					if(Long.parseLong(modMap.get(element).toString())==330028)
					{
						pendingList.add("330028");
						pendingList.add(index+18);
					}
					if(Long.parseLong(modMap.get(element).toString())==330027)
					{
						pendingList.add("330027");
						pendingList.add(index+19);
					}
					if(Long.parseLong(modMap.get(element).toString())==330026)
					{
						pendingList.add("330026");
						pendingList.add(index+20);
					}
					if(Long.parseLong(modMap.get(element).toString())==300001)
					{
						pendingList.add("300001");
						pendingList.add(index+21);
					}
					if(Long.parseLong(modMap.get(element).toString())==300002)
					{
						pendingList.add("300002");
						pendingList.add(index+22);
					}
					if(Long.parseLong(modMap.get(element).toString())==300003)
					{
						pendingList.add("330027");
						pendingList.add(index+23);
					}
					
					DataList.add(pendingList);
				}
			}
			

			if(report.getReportCode().equals("300051") || report.getReportCode().equals("300054")){

				logger.info("Inside 300051 ::");
				
				Map postMap = new HashMap();
				Map modMap = new HashMap();
				Map createddate=new HashMap();
				Map approveddate=new HashMap();
				ArrayList postId=new ArrayList();
				ArrayList fileId=new ArrayList();
				ArrayList approvefilelist=new ArrayList();
				String reqID=null;
				String jobRefId=null;
				
				
				if(report.getParameterValue("appname") !=null && !report.getParameterValue("appname").equals("")){
					appname=report.getParameterValue("appname").toString();
					//System.out.println("appname In DAO >>"+appname);
				}
				NoOfDays=report.getParameterValue("NoOfDays").toString();
				//System.out.println("NoOfDays IN DAO >> "+NoOfDays);
				
				operator=report.getParameterValue("operator").toString();
				//System.out.println("operator IN DAO >> "+operator);
				
				fromdate=report.getParameterValue("fromdate").toString();
				//System.out.println("fromdate in DAO >> "+fromdate);
				
				todate=report.getParameterValue("todate").toString();
				//System.out.println("TO DATE in DAO >> "+todate);
				
				appliedDt=report.getParameterValue("appliedDt").toString();
				//System.out.println("TO appliedDt in DAO >> "+appliedDt);
				
				appliedTo=report.getParameterValue("appliedTo").toString();
				//System.out.println("Applied TO in DAO >> "+appliedTo);
				
				appType=report.getParameterValue("apptype").toString();
				
				
				if(appType.equals("f")){
					
					if(appname!=null && !appname.equals("0"))
					{
						moduleid=appname;
						//System.out.println("module ID"+moduleid);
					}
					
					
					if(report.getParameterValue("operator").toString().equals("0"))
					{
						if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
							//sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(fromdate))+"' and '"+simpledt.format(StringUtility.convertStringToDate(todate))+"' order  by crt_dt desc");
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
						}
						
					}else if (report.getParameterValue("operator").toString().equals("1")) {
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
						
					}else if(report.getParameterValue("operator").toString().equals("2")){
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
						
					}
						
						lStrSqlQuery = "";
						lStrSqlQuery = sql.toString();
						lPStmt = lCon.prepareStatement(lStrSqlQuery);	
//						System.out.println("lRs Query >>> "+lPStmt);
						lRs = lPStmt.executeQuery();
						
						while(lRs.next()){
							jobRefId=lRs.getString("job_ref_id");
							
							lRs2.beforeFirst();
							while(lRs2.next()){
								
								reqID=String.valueOf(lRs2.getLong("req_id"));
								
								if(jobRefId.equals(reqID)){
									
									fileId.add(lRs.getString("job_ref_id"));
									approvefilelist.add(lRs.getString("job_ref_id"));
									modMap.put(lRs.getString("job_ref_id"), lRs2.getLong("mod_id"));
									createddate.put(lRs.getString("job_ref_id"), lRs.getDate("crt_dt"));
								}
							}
						}
				}else if(appType.equals("c")){
					
					if(appname!=null && !appname.equals("0"))
					{
						corrmoduleid=appname;
						//System.out.println("module ID"+moduleid);
					}
					
					
					if(report.getParameterValue("operator").toString().equals("0"))
					{
						if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
							//sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(fromdate))+"' and '"+simpledt.format(StringUtility.convertStringToDate(todate))+"' order  by crt_dt desc");
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='Correspondence'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
						}
						
					}else if (report.getParameterValue("operator").toString().equals("1")) {
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
						
					}else if(report.getParameterValue("operator").toString().equals("2")){
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
						
					}
					
					//corrId = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='Correspondence' order  by crt_dt desc");
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);	
					Corr2 = lPStmt.executeQuery();
					
					while(Corr2.next()){
						//System.out.println("Inside lRs while loop:::");
						jobRefId=Corr2.getString("job_ref_id");
						
						Corr1.beforeFirst();
						while(Corr1.next()){
							
							reqID=String.valueOf(Corr1.getLong("req_id"));
							
							if(jobRefId.equals(reqID)){
								
								fileId.add(Corr2.getString("job_ref_id"));
								postMap.put(Corr2.getString("job_ref_id"), Corr2.getString("lst_act_post_id"));
								postId.add(Long.parseLong(Corr2.getString("lst_act_post_id")));
								modMap.put(Corr2.getString("job_ref_id"), Corr1.getLong("mod_id"));
								createddate.put(Corr2.getString("job_ref_id"), Corr2.getDate("crt_dt"));
								approveddate.put(Corr2.getString("job_ref_id"), Corr2.getDate("lst_upd_dt"));
							}
						}
					}
					
				}else if(appType.equals("b")){
					
					//File
					if(appname!=null && !appname.equals("0"))
					{
						moduleid=appname;
						//System.out.println("module ID"+moduleid);
					}
					
					
					if(report.getParameterValue("operator").toString().equals("0"))
					{
						if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
							//sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(fromdate))+"' and '"+simpledt.format(StringUtility.convertStringToDate(todate))+"' order  by crt_dt desc");
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
						}
						
					}else if (report.getParameterValue("operator").toString().equals("1")) {
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
						
					}else if(report.getParameterValue("operator").toString().equals("2")){
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
						
					}
						
						lStrSqlQuery = "";
						lStrSqlQuery = sql.toString();
						lPStmt = lCon.prepareStatement(lStrSqlQuery);
//						System.out.println("Corrss Query >>> "+lPStmt);
						lRs = lPStmt.executeQuery();
						
						while(lRs.next()){
							jobRefId=lRs.getString("job_ref_id");
							
							lRs2.beforeFirst();
							while(lRs2.next()){
								
								reqID=String.valueOf(lRs2.getLong("req_id"));
								
								if(jobRefId.equals(reqID)){
									
									fileId.add(lRs.getString("job_ref_id"));
									approvefilelist.add(lRs.getString("job_ref_id"));
									modMap.put(lRs.getString("job_ref_id"), lRs2.getLong("mod_id"));
									createddate.put(lRs.getString("job_ref_id"), lRs.getDate("crt_dt"));
								}
							}
						}
						
						//Correspondence
						if(appname!=null && !appname.equals("0"))
						{
							corrmoduleid=appname;
							//System.out.println("module ID"+moduleid);
						}
						
						
						if(report.getParameterValue("operator").toString().equals("0"))
						{
							if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
								//sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(fromdate))+"' and '"+simpledt.format(StringUtility.convertStringToDate(todate))+"' order  by crt_dt desc");
								sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='Correspondence'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
							}
							
						}else if (report.getParameterValue("operator").toString().equals("1")) {
							
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
							
						}else if(report.getParameterValue("operator").toString().equals("2")){
							
							sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
							
						}
						
						//corrId = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='Correspondence' order  by crt_dt desc");
						lStrSqlQuery = "";
						lStrSqlQuery = sql.toString();
						lPStmt = lCon.prepareStatement(lStrSqlQuery);	
						Corr2 = lPStmt.executeQuery();
						
						while(Corr2.next()){
							//System.out.println("Inside lRs while loop:::");
							jobRefId=Corr2.getString("job_ref_id");
							
							Corr1.beforeFirst();
							while(Corr1.next()){
								
								reqID=String.valueOf(Corr1.getLong("req_id"));
								
								if(jobRefId.equals(reqID)){
									
									fileId.add(Corr2.getString("job_ref_id"));
									postMap.put(Corr2.getString("job_ref_id"), Corr2.getString("lst_act_post_id"));
									postId.add(Long.parseLong(Corr2.getString("lst_act_post_id")));
									modMap.put(Corr2.getString("job_ref_id"), Corr1.getLong("mod_id"));
									createddate.put(Corr2.getString("job_ref_id"), Corr2.getDate("crt_dt"));
									approveddate.put(Corr2.getString("job_ref_id"), Corr2.getDate("lst_upd_dt"));
								}
							}
						}	
				}
				
				
					if(!approvefilelist.isEmpty()){
						
						String fileidstring=null;
						
						for (Iterator iter = approvefilelist.iterator(); iter.hasNext();) {
							String element = (String) iter.next();
							if(fileidstring!=null)
							{
								fileidstring=fileidstring+element+",";
							}
							else
							{
								fileidstring=element+",";
							}
						}
						
						sql = new StringBuffer("select max(approve_counter),file_id,crt_post,crt_dt,lst_upd_post,lst_upd_dt from  fms_file_app_track  where  file_id in ("+fileidstring.substring(0, fileidstring.length()-1)+") group by file_id") ;
						lStrSqlQuery = "";
						lStrSqlQuery = sql.toString();
						lPStmt = lCon.prepareStatement(lStrSqlQuery);		
						lRs = lPStmt.executeQuery();
						while(lRs.next()){
							for (Iterator iter = fileId.iterator(); iter.hasNext();) {
								String element = (String) iter.next();
								String createdpost=String.valueOf(lRs.getLong("file_id"));
								if(element.equals(createdpost))
								{
									
									postMap.put(element, lRs.getLong("crt_post"));
									postId.add(lRs.getLong("crt_post"));
									approveddate.put(element, lRs.getDate("crt_dt"));
									
									/*postMap.put(element, lRs.getLong("lst_upd_post"));
									postId.add(lRs.getLong("lst_upd_post"));
									approveddate.put(element, lRs.getDate("lst_upd_dt"));*/
								}
								
							}
						}
					}
					
					Map locName=new HashMap();
					Map dsgnMap=new HashMap();
					String postidstring=null;
					
					for (Iterator iter = postId.iterator(); iter.hasNext();) {
						Long element = (Long) iter.next();
						if(postidstring!=null)
						{
						postidstring=postidstring+element+",";
						}
						else
						{
							postidstring=element+",";
						}
					}
					
					Map userNameMap = new HashMap();
					if(postidstring!=null)
					{
						
						sql=null;
						sql = new StringBuffer("SELECT cmnlocMst.loc_name as LocName, desgMst.dsgn_name as DsgnName, userPos.user_id as user_id, userPos.post_id as post_id FROM org_designation_mst desgMst, cmn_location_mst cmnlocMst, org_post_details_rlt postDetRlt, org_userpost_rlt userPos ") ;
						sql.append(" WHERE  cmnlocMst.loc_id in(SELECT postDltRlt.loc_id FROM org_post_details_rlt postDltRlt ");
						sql.append(" WHERE postDltRlt.post_id  in (SELECT orguserPostRlt.post_id FROM org_userpost_rlt orguserPostRlt ");
						sql.append(" WHERE orguserPostRlt.post_id in("+postidstring.substring(0, postidstring.length()-1)+") and orguserPostRlt.activate_flag=1 )AND postDltRlt.lang_id="+langID+") AND desgMst.dsgn_id in(SELECT postDltRlt.dsgn_id FROM org_post_details_rlt postDltRlt ");
						sql.append(" WHERE postDltRlt.post_id  in (SELECT orguserPostRlt.post_id FROM org_userpost_rlt orguserPostRlt ");
						sql.append(" WHERE orguserPostRlt.post_id in("+postidstring.substring(0, postidstring.length()-1)+") and orguserPostRlt.activate_flag=1)AND postDltRlt.lang_id="+langID+") AND userPos.post_id = postDetRlt.post_id AND postDetRlt.loc_id=cmnlocMst.loc_id AND postDetRlt.lang_id="+langID+" AND postDetRlt.dsgn_id=desgMst.dsgn_id AND userPos.activate_flag=1");
						lStrSqlQuery = "";
						lStrSqlQuery = sql.toString();
						lPStmt = lCon.prepareStatement(lStrSqlQuery);		
						lRs = lPStmt.executeQuery();
						
						
						ArrayList useridlist=new ArrayList();
						Map userMap = new HashMap();
						
						
						while(lRs.next()){
							for (Iterator iter = fileId.iterator(); iter.hasNext();) {
								String element = (String) iter.next();
								/*Added to avoid Null Pointer*/
									//if(postMap.containsKey(element)){
										if(Long.parseLong(postMap.get(element).toString())==lRs.getLong("post_id"))
										{
											userMap.put(element,lRs.getLong("user_id"));
											locName.put(element,lRs.getString("LocName"));
											dsgnMap.put(element, lRs.getString("DsgnName"));
											useridlist.add(lRs.getLong("user_id"));
											
										}
									//}
							}
						}
						
						String useridstring=null;
						for (Iterator iter = useridlist.iterator(); iter.hasNext();) {
							Long element = (Long) iter.next();
							if(useridstring!=null)
							{
							useridstring=useridstring+element+",";
							}
							else
							{
								useridstring=element+",";
							}
						}
						
						sql=null;
						sql = new StringBuffer("select * from org_emp_mst where lang_id="+langID+" and user_id in ("+useridstring.substring(0, useridstring.length()-1)+")");
						lStrSqlQuery = "";
						lStrSqlQuery = sql.toString();
						lPStmt = lCon.prepareStatement(lStrSqlQuery);		
						lRs = lPStmt.executeQuery();
						
						while(lRs.next()){
							for (Iterator iter = fileId.iterator(); iter.hasNext();) {
								String element = (String) iter.next();
								/*Added to avoid Null Pointer*/
								//if(userMap.containsKey(element)){
										if(Long.parseLong(userMap.get(element).toString())==lRs.getLong("user_id"))
										{
											userNameMap.put(element,lRs.getString("emp_fname")+" "+lRs.getString("emp_mname")+" "+lRs.getString("emp_lname"));
										}
								//}else{
								//	userNameMap.put(element, lRs.getString("emp_fname")+" "+lRs.getString("emp_mname")+" "+lRs.getString("emp_lname"));
								//}
							}
						}
					}
					
					
					Integer index=new Integer(1);
					Date crtdate = null;
					
					
					for (Iterator iter = fileId.iterator(); iter.hasNext();) {
						String element = (String) iter.next();
					
						pendingList=new ArrayList();
						if(Long.parseLong(modMap.get(element).toString())==330023)
						{
							pendingList.add("GPF ADVANCE");
						}
						if(Long.parseLong(modMap.get(element).toString())==330022)
						{
							pendingList.add("GPF SUBSCRIPTION");
						}
						if(Long.parseLong(modMap.get(element).toString())==330024)
						{
							pendingList.add("GPF WITHDRAWAL");
						}
						if(Long.parseLong(modMap.get(element).toString())==300025)
						{
							pendingList.add("RESIGNATION");
						}
						if(Long.parseLong(modMap.get(element).toString())==300013)
						{
							pendingList.add("QUARTER ALLOTMENT");
						}
						if(Long.parseLong(modMap.get(element).toString())==300010)
						{
							pendingList.add("NOC Passport");
						}
						if(Long.parseLong(modMap.get(element).toString())==300011)
						{
							pendingList.add("NOC Foreign Visit");
						}
						if(Long.parseLong(modMap.get(element).toString())==300012)
						{
							pendingList.add("Foreign Visit Report");
						}
						if(Long.parseLong(modMap.get(element).toString())==300005)
						{
							pendingList.add("Qualification");
						}
						if(Long.parseLong(modMap.get(element).toString())==300006)
						{
							pendingList.add("Nominee");
						}
						if(Long.parseLong(modMap.get(element).toString())==300007)
						{
							pendingList.add("Family");
						}
						if(Long.parseLong(modMap.get(element).toString())==300020)
						{
							pendingList.add("Change Employee Profile");
						}
						if(Long.parseLong(modMap.get(element).toString())==300021)
						{
							pendingList.add("Change Employee Address");
						}
						if(Long.parseLong(modMap.get(element).toString())==300015)
						{
							pendingList.add("LTC");
						}
						if(Long.parseLong(modMap.get(element).toString())==320400)
						{
							pendingList.add("Permission to Purchase/Sell Asset");
						}
						if(Long.parseLong(modMap.get(element).toString())==320401)
						{
							pendingList.add("Asset Submission Of Actuals");
						}
						if(Long.parseLong(modMap.get(element).toString())==330029)
						{
							pendingList.add("Travel Advance");
						}
						if(Long.parseLong(modMap.get(element).toString())==330030)
						{
							pendingList.add("Travel Reimbursment");
						}if(Long.parseLong(modMap.get(element).toString())==330028)
						{
							pendingList.add("Travel Cancellation");
						}
						if(Long.parseLong(modMap.get(element).toString())==330027)
						{
							pendingList.add("Travel Pre-Sanction/Post Facto");
						}
						if(Long.parseLong(modMap.get(element).toString())==330026)
						{
							pendingList.add("Gpf New Account ");
						}
						if(Long.parseLong(modMap.get(element).toString())==300001)
						{
							pendingList.add("Apply for leave");
						}
						if(Long.parseLong(modMap.get(element).toString())==300002)
						{
							pendingList.add("Cancel Leave");
						}
						if(Long.parseLong(modMap.get(element).toString())==300003)
						{
							pendingList.add("Joining Leave");
						}
						
						pendingList.add(element);
					
						if(userNameMap.get(element)!=null)
						{
							pendingList.add(userNameMap.get(element)+" ("+dsgnMap.get(element)+")");
						}
						else
						{
							pendingList.add("-");
						}
						if(locName.get(element)!=null)
						{
							pendingList.add(locName.get(element));
						}
						else
						{
							pendingList.add("-");
						}
						
						crtdate=StringUtility.convertStringToDate(sdf.format(createddate.get(element)));
						if(approveddate.get(element)!=null){
							Date appdate=StringUtility.convertStringToDate(sdf.format(approveddate.get(element)));
							
							pendingList.add(sdf.format(createddate.get(element)));
							pendingList.add(sdf.format(approveddate.get(element)));
							long noofdays=deltaDays(crtdate,appdate);
							pendingList.add(noofdays);
						}
						
						if(Long.parseLong(modMap.get(element).toString())==330023)
						{
							pendingList.add("330023");
							pendingList.add(index);
						}
						if(Long.parseLong(modMap.get(element).toString())==330022)
						{
							pendingList.add("330022");
							pendingList.add(index+1);
						}
						if(Long.parseLong(modMap.get(element).toString())==330024)
						{
							pendingList.add("330024");
							pendingList.add(index+2);
						}
						if(Long.parseLong(modMap.get(element).toString())==300025)
						{
							pendingList.add("300025");
							pendingList.add(index+3);
						}
						if(Long.parseLong(modMap.get(element).toString())==300013)
						{
							pendingList.add("300013");
							pendingList.add(index+4);
						}
						
						
						if(Long.parseLong(modMap.get(element).toString())==300010)
						{
							pendingList.add("300010");
							pendingList.add(index+5);
						}
						if(Long.parseLong(modMap.get(element).toString())==300011)
						{
							pendingList.add("300011");
							pendingList.add(index+6);
						}
						if(Long.parseLong(modMap.get(element).toString())==300012)
						{
							pendingList.add("300012");
							pendingList.add(index+7);
						}
						if(Long.parseLong(modMap.get(element).toString())==300005)
						{
							pendingList.add("300005");
							pendingList.add(index+8);
						}
						if(Long.parseLong(modMap.get(element).toString())==300006)
						{
							pendingList.add("300006");
							pendingList.add(index+9);
						}
						if(Long.parseLong(modMap.get(element).toString())==300007)
						{
							pendingList.add("300007");
							pendingList.add(index+10);
						}
						if(Long.parseLong(modMap.get(element).toString())==300020)
						{
							pendingList.add("300020");
							pendingList.add(index+11);
						}
						if(Long.parseLong(modMap.get(element).toString())==300021)
						{
							pendingList.add("300021");
							pendingList.add(index+12);
						}
						if(Long.parseLong(modMap.get(element).toString())==300015)
						{
							pendingList.add("300015");
							pendingList.add(index+13);
						}
						if(Long.parseLong(modMap.get(element).toString())==320401)
						{
							pendingList.add("320401");
							pendingList.add(index+14);
						}
						if(Long.parseLong(modMap.get(element).toString())==320400)
						{
							pendingList.add("320400");
							pendingList.add(index+15);
						}
						if(Long.parseLong(modMap.get(element).toString())==330029)
						{
							pendingList.add("330029");
							pendingList.add(index+16);
						}
						if(Long.parseLong(modMap.get(element).toString())==330030)
						{
							pendingList.add("330030");
							pendingList.add(index+17);
						}
						if(Long.parseLong(modMap.get(element).toString())==330028)
						{
							pendingList.add("330028");
							pendingList.add(index+18);
						}
						if(Long.parseLong(modMap.get(element).toString())==330027)
						{
							pendingList.add("330027");
							pendingList.add(index+19);
						}
						if(Long.parseLong(modMap.get(element).toString())==330026)
						{
							pendingList.add("330026");
							pendingList.add(index+20);
						}
						if(Long.parseLong(modMap.get(element).toString())==300001)
						{
							pendingList.add("300001");
							pendingList.add(index+21);
						}
						if(Long.parseLong(modMap.get(element).toString())==300002)
						{
							pendingList.add("300002");
							pendingList.add(index+22);
						}
						if(Long.parseLong(modMap.get(element).toString())==300003)
						{
							pendingList.add("330027");
							pendingList.add(index+23);
						}	
							
						/*pendingList.add("");
						pendingList.add("");
						pendingList.add("");*/
						DataList.add(pendingList);
					}
			}
			
			if(report.getReportCode().equals("300052") || report.getReportCode().equals("300055")){
				logger.info("Inside 300052::");
				
				Map postMap = new HashMap();
				Map modMap = new HashMap();
				Map createddate=new HashMap();
				ArrayList postId=new ArrayList();
				ArrayList fileId=new ArrayList();
				Map approveddate=new HashMap();
				String reqID=null;
				String jobRefId=null;
				Map statusmap=new HashMap();
				List approvefileidlist=new ArrayList();
				List pendingfilelist=new ArrayList();
				
				
				lookupidforfile = new StringBuffer("select lookup_id,lookup_name from cmn_lookup_mst where lookup_name= 'fms_Approve' or lookup_name='fms_Pending'");
				lStrSqlQuery = "";
				lStrSqlQuery = lookupidforfile.toString();
				lPStmt = lCon.prepareStatement(lStrSqlQuery);
				lRs3 = lPStmt.executeQuery();
				
				ArrayList pendingid=new ArrayList();
				ArrayList approvedid=new ArrayList();
				while(lRs3.next())
				{
					if(lRs3.getString("lookup_name").equals("fms_Pending"))
					{
						pendingid.add(lRs3.getLong("lookup_id"));
					}
					if(lRs3.getString("lookup_name").equals("fms_Approve"))
					{
						approvedid.add(lRs3.getLong("lookup_id"));
					}
				}
				
				
				if(report.getParameterValue("appname") !=null && !report.getParameterValue("appname").equals("")){
					appname=report.getParameterValue("appname").toString();
					//System.out.println("appname In DAO >>"+appname);
				}
				NoOfDays=report.getParameterValue("NoOfDays").toString();
				//System.out.println("NoOfDays IN DAO >> "+NoOfDays);
				
				operator=report.getParameterValue("operator").toString();
				//System.out.println("operator IN DAO >> "+operator);
				
				fromdate=report.getParameterValue("fromdate").toString();
				//System.out.println("fromdate in DAO >> "+fromdate);
				
				todate=report.getParameterValue("todate").toString();
				//System.out.println("TO DATE in DAO >> "+todate);
				
				appliedDt=report.getParameterValue("appliedDt").toString();
				//System.out.println("TO appliedDt in DAO >> "+appliedDt);
				
				appliedTo=report.getParameterValue("appliedTo").toString();
				//System.out.println("Applied TO in DAO >> "+appliedTo);
				
				
				
				//File
				if(appname!=null && !appname.equals("0"))
				{
					moduleid=appname;
				}
				
				if(report.getParameterValue("operator").toString().equals("0"))
				{
					if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
						
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending' or lookup_name='fms_Approve')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
					}
					
				}else if (report.getParameterValue("operator").toString().equals("1")) {
					
					sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending' or lookup_name='fms_Approve')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
					
				}else if(report.getParameterValue("operator").toString().equals("2")){
					
					sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Pending' or lookup_name='fms_Approve')) and job_title='file'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
				}
					
					
				
				lStrSqlQuery = "";
				lStrSqlQuery = sql.toString();
				lPStmt = lCon.prepareStatement(lStrSqlQuery);
//				System.out.println("lRs Query >>> "+lPStmt);
				lRs = lPStmt.executeQuery();
				
				String fileids=null;
				int counter = 1;
				while(lRs.next())
				{
					if(fileids!=null)
					{
						fileids=fileids+","+lRs.getString("job_ref_id");
					}
					else
					{
						fileids=lRs.getString("job_ref_id");
					}
				}
			
				fileDtls = new StringBuffer("SELECT * FROM fms_file_mst f where file_id in("+fileids+")");
				lStrSqlQuery = "";
				lStrSqlQuery = fileDtls.toString();
				Map filestagemap=new HashMap();
				lPStmt = lCon.prepareStatement(lStrSqlQuery);		
				lRs4 = lPStmt.executeQuery();
				
				while(lRs4.next())
				{
					filestagemap.put(lRs4.getString("file_id"), lRs4.getLong("file_stage"));
				}
				
				
				//Correspondence
				
				if(appname!=null && !appname.equals("0"))
				{
					corrmoduleid=appname;
					//System.out.println("module ID"+moduleid);
				}
				
				
				if(report.getParameterValue("operator").toString().equals("0"))
				{
					if((appliedDt != null && !appliedDt.equals("")) && (appliedTo != null && !appliedTo.equals(""))){
						//sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT file_id FROM fms_file_mst f where file_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+moduleid+" ) and user_id="+userId+") and file_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve')) and job_title='file'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(fromdate))+"' and '"+simpledt.format(StringUtility.convertStringToDate(todate))+"' order  by crt_dt desc");
						sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve' or lookup_name='fms_Pending')) and job_title='Correspondence'and crt_dt between '"+simpledt.format(StringUtility.convertStringToDate(appliedDt))+"' and '"+appliedTo+"' order  by crt_dt desc");
					}
					
				}else if (report.getParameterValue("operator").toString().equals("1")) {
					
					sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve' or lookup_name='fms_Pending')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') > '"+appliedTo+"' order  by crt_dt desc");
					
				}else if(report.getParameterValue("operator").toString().equals("2")){
					
					sql = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve' or lookup_name='fms_Pending')) and job_title='Correspondence'and DATE_FORMAT(crt_dt,'%Y-%m-%d') < '"+appliedTo+"' order  by crt_dt desc");
					
				}
				
				
				//corrId = new StringBuffer("select * from wf_job_mst where job_ref_id in (SELECT corr_id FROM fms_corr_mst f where corr_id in(select req_id from hr_mod_emp_rlt where mod_id in ("+corrmoduleid+" ) and user_id="+userId+") and corr_stage in(select lookup_id from cmn_lookup_mst where lookup_name='fms_Approve' or lookup_name='fms_Pending')) and job_title='Correspondence' order  by crt_dt desc");
				lStrSqlQuery = "";
				lStrSqlQuery = sql.toString();
				lPStmt = lCon.prepareStatement(lStrSqlQuery);
//				System.out.println("Corrs Query >>> "+lPStmt);
				Corr2 = lPStmt.executeQuery();
				
				String corrids=null;
				
				while(Corr2.next())
				{
					if(corrids!=null)
					{
						corrids=corrids+","+Corr2.getString("job_ref_id");
					}
					else
					{
						corrids=Corr2.getString("job_ref_id");
					}
				}
			
				fileDtls = new StringBuffer("SELECT * FROM fms_corr_mst f where corr_id in("+corrids+")");
				lStrSqlQuery = "";
				lStrSqlQuery = fileDtls.toString();
				Map corrstagemap=new HashMap();
				lPStmt = lCon.prepareStatement(lStrSqlQuery);		
				lRs4 = lPStmt.executeQuery();
				
				while(lRs4.next())
				{
					corrstagemap.put(lRs4.getString("corr_id"), lRs4.getLong("corr_stage"));
				}
				
				
				lRs.beforeFirst();
				while(lRs.next() ){
				
					
					jobRefId=lRs.getString("job_ref_id");

					lRs2.beforeFirst();
					while(lRs2.next() ){
						
						reqID=String.valueOf(lRs2.getLong("req_id"));

						if(jobRefId.equals(reqID)){
							
							fileId.add(lRs.getString("job_ref_id"));
						for (Iterator iter = pendingid.iterator(); iter.hasNext();) {
							Long element = (Long) iter.next();


							if(element==Long.parseLong(filestagemap.get(lRs.getString("job_ref_id")).toString()))
							{

							postMap.put(lRs.getString("job_ref_id"), lRs.getString("lst_act_post_id"));
							postId.add(lRs.getString("lst_act_post_id"));
							pendingfilelist.add(lRs.getString("job_ref_id"));
							statusmap.put(lRs.getString("job_ref_id"), "Pending");

							}
							
							
						}
						for (Iterator itera = approvedid.iterator(); itera.hasNext();) {
							Long element1 = (Long) itera.next();
							if(element1==Long.parseLong(filestagemap.get(lRs.getString("job_ref_id")).toString()))
							{

							approvefileidlist.add(lRs.getString("job_ref_id"));
							statusmap.put(lRs.getString("job_ref_id"), "Approve");
							}
						}
							
							modMap.put(lRs.getString("job_ref_id"), lRs2.getLong("mod_id"));
							createddate.put(lRs.getString("job_ref_id"), lRs.getDate("crt_dt"));
						}
					}
				}
				
				Corr2.beforeFirst();
				while(Corr2.next() ){
				
					
					jobRefId=Corr2.getString("job_ref_id");

					Corr1.beforeFirst();
					while(Corr1.next() ){
						
						reqID=String.valueOf(Corr1.getLong("req_id"));

						if(jobRefId.equals(reqID)){
							
							fileId.add(Corr2.getString("job_ref_id"));
							for (Iterator iter = pendingid.iterator(); iter.hasNext();) {
								Long element = (Long) iter.next();
	
	
								if(element==Long.parseLong(corrstagemap.get(Corr2.getString("job_ref_id")).toString()))
								{
	
								postMap.put(Corr2.getString("job_ref_id"), Corr2.getString("lst_act_post_id"));
								postId.add(Corr2.getString("lst_act_post_id"));
								pendingfilelist.add(Corr2.getString("job_ref_id"));
								statusmap.put(Corr2.getString("job_ref_id"), "Pending");
	
								}
							}
							
							for (Iterator itera = approvedid.iterator(); itera.hasNext();) {
								Long element1 = (Long) itera.next();
								if(element1==Long.parseLong(corrstagemap.get(Corr2.getString("job_ref_id")).toString()))
								{
									postMap.put(Corr2.getString("job_ref_id"), Corr2.getString("lst_act_post_id"));
									postId.add(Corr2.getString("lst_act_post_id"));
									approveddate.put(Corr2.getString("job_ref_id"), Corr2.getDate("lst_upd_dt"));
									approvefileidlist.add(Corr2.getString("job_ref_id"));
									statusmap.put(Corr2.getString("job_ref_id"), "Approve");
								}
							}
							
							modMap.put(Corr2.getString("job_ref_id"), Corr1.getLong("mod_id"));
							createddate.put(Corr2.getString("job_ref_id"), Corr2.getDate("crt_dt"));
						}
					}
				}
				
				
				if(!approvefileidlist.isEmpty())
				{
					String fileidstring=null;
	
					for (Iterator iter = approvefileidlist.iterator(); iter.hasNext();) {
						String element = (String) iter.next();
						if(fileidstring!=null)
						{
							fileidstring=fileidstring+element+",";
						}
						else
						{
							fileidstring=element+",";
						}
					}
					
					sql = new StringBuffer("select max(approve_counter),file_id,crt_post,crt_dt from  fms_file_app_track  where  file_id in ("+fileidstring.substring(0, fileidstring.length()-1)+") group by file_id") ;
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);		
					lRs = lPStmt.executeQuery();
					while(lRs.next()){
						for (Iterator iter = fileId.iterator(); iter.hasNext();) {
							String element = (String) iter.next();
							String createdpost=String.valueOf(lRs.getLong("file_id"));
								if(element.equals(createdpost))
								{
									postMap.put(element, lRs.getString("crt_post"));
									postId.add(lRs.getString("crt_post"));
									approveddate.put(element, lRs.getDate("crt_dt"));
								}
						}
					}
				
				}
				
				
				String postidstring=null;

				for (Iterator iter = postId.iterator(); iter.hasNext();) {
					String element = (String) iter.next();
					if(postidstring!=null)
					{
						postidstring=postidstring+element+",";
					}
					else
					{
						postidstring=element+",";
					}
				}
				Map locName=new HashMap();
				Map dsgnMap=new HashMap();
				Map userNameMap = new HashMap();
				if(postidstring!=null)
				{
					sql=null;
					sql = new StringBuffer("SELECT cmnlocMst.loc_name as LocName, desgMst.dsgn_name as DsgnName, userPos.user_id as user_id, userPos.post_id as post_id FROM org_designation_mst desgMst, cmn_location_mst cmnlocMst, org_post_details_rlt postDetRlt, org_userpost_rlt userPos ") ;
					sql.append(" WHERE  cmnlocMst.loc_id in(SELECT postDltRlt.loc_id FROM org_post_details_rlt postDltRlt ");
					sql.append(" WHERE postDltRlt.post_id  in (SELECT orguserPostRlt.post_id FROM org_userpost_rlt orguserPostRlt ");
					sql.append(" WHERE orguserPostRlt.post_id in("+postidstring.substring(0, postidstring.length()-1)+") and orguserPostRlt.activate_flag=1 )AND postDltRlt.lang_id="+langID+") AND desgMst.dsgn_id in(SELECT postDltRlt.dsgn_id FROM org_post_details_rlt postDltRlt ");
					sql.append(" WHERE postDltRlt.post_id  in (SELECT orguserPostRlt.post_id FROM org_userpost_rlt orguserPostRlt ");
					sql.append(" WHERE orguserPostRlt.post_id in("+postidstring.substring(0, postidstring.length()-1)+") and orguserPostRlt.activate_flag=1)AND postDltRlt.lang_id="+langID+") AND userPos.post_id = postDetRlt.post_id AND postDetRlt.loc_id=cmnlocMst.loc_id AND postDetRlt.lang_id="+langID+" AND postDetRlt.dsgn_id=desgMst.dsgn_id AND userPos.activate_flag=1");
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);		
					lRs = lPStmt.executeQuery();
					
					ArrayList useridlist=new ArrayList();
					Map userMap = new HashMap();

					while(lRs.next()){
						for (Iterator iter = fileId.iterator(); iter.hasNext();) {
							String element = (String) iter.next();

							if(Long.parseLong(postMap.get(element).toString())==lRs.getLong("post_id"))
							{
								userMap.put(element,lRs.getLong("user_id"));
								locName.put(element,lRs.getString("LocName"));
								dsgnMap.put(element, lRs.getString("DsgnName"));
								
								useridlist.add(lRs.getLong("user_id"));
							}
						}
					}
					
					String useridstring=null;
					for (Iterator iter = useridlist.iterator(); iter.hasNext();) {
						Long element = (Long) iter.next();
						if(useridstring!=null)
						{
						useridstring=useridstring+element+",";
						}
						else
						{
							useridstring=element+",";
						}
					}


					sql=null;
					sql = new StringBuffer("select * from org_emp_mst where lang_id="+langID+" and user_id in ("+useridstring.substring(0, useridstring.length()-1)+")");
					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);		
					lRs = lPStmt.executeQuery();
					
					while(lRs.next()){
						for (Iterator iter = fileId.iterator(); iter.hasNext();) {
							String element = (String) iter.next();
							if(Long.parseLong(userMap.get(element).toString())==lRs.getLong("user_id"))
							{
								userNameMap.put(element,lRs.getString("emp_fname")+" "+lRs.getString("emp_mname")+" "+lRs.getString("emp_lname"));
							}
						}
					}
				}

				
				Integer index=new Integer(1);
				Date crtdate = null;
				
				for (Iterator iter = fileId.iterator(); iter.hasNext();) {
					String element = (String) iter.next();

					pendingList=new ArrayList();
					if(Long.parseLong(modMap.get(element).toString())==330023)
					{
						pendingList.add("GPF ADVANCE");
					}
					if(Long.parseLong(modMap.get(element).toString())==330022)
					{
						pendingList.add("GPF SUBSCRIPTION");
					}
					if(Long.parseLong(modMap.get(element).toString())==330024)
					{
						pendingList.add("GPF WITHDRAWAL");
					}
					if(Long.parseLong(modMap.get(element).toString())==300025)
					{
						pendingList.add("RESIGNATION");
					}
					if(Long.parseLong(modMap.get(element).toString())==300013)
					{
						pendingList.add("QUARTER ALLOTMENT");
					}
					if(Long.parseLong(modMap.get(element).toString())==300010)
					{
						pendingList.add("NOC Passport");
					}
					if(Long.parseLong(modMap.get(element).toString())==300011)
					{
						pendingList.add("NOC Foreign Visit");
					}
					if(Long.parseLong(modMap.get(element).toString())==300012)
					{
						pendingList.add("Foreign Visit Report");
					}
					if(Long.parseLong(modMap.get(element).toString())==300005)
					{
						pendingList.add("Qualification");
					}
					if(Long.parseLong(modMap.get(element).toString())==300006)
					{
						pendingList.add("Nominee");
					}
					if(Long.parseLong(modMap.get(element).toString())==300007)
					{
						pendingList.add("Family");
					}
					if(Long.parseLong(modMap.get(element).toString())==300020)
					{
						pendingList.add("Change Employee Profile");
					}
					if(Long.parseLong(modMap.get(element).toString())==300021)
					{
						pendingList.add("Change Employee Address");
					}
					if(Long.parseLong(modMap.get(element).toString())==300015)
					{
						pendingList.add("LTC");
					}
					if(Long.parseLong(modMap.get(element).toString())==320400)
					{
						pendingList.add("Permission to Purchase/Sell Asset");
					}
					if(Long.parseLong(modMap.get(element).toString())==320401)
					{
						pendingList.add("Asset Submission Of Actuals");
					}
					if(Long.parseLong(modMap.get(element).toString())==330029)
					{
						pendingList.add("Travel Advance");
					}
					if(Long.parseLong(modMap.get(element).toString())==330030)
					{
						pendingList.add("Travel Reimbursment");
					}if(Long.parseLong(modMap.get(element).toString())==330028)
					{
						pendingList.add("Travel Cancellation");
					}
					if(Long.parseLong(modMap.get(element).toString())==330027)
					{
						pendingList.add("Travel Pre-Sanction/Post Facto");
					}
					if(Long.parseLong(modMap.get(element).toString())==330026)
					{
						pendingList.add("Gpf New Account ");
					}
					if(Long.parseLong(modMap.get(element).toString())==300001)
					{
						pendingList.add("Apply for leave");
					}
					if(Long.parseLong(modMap.get(element).toString())==300002)
					{
						pendingList.add("Cancel Leave");
					}
					if(Long.parseLong(modMap.get(element).toString())==300003)
					{
						pendingList.add("Joining Leave");
					}
					
					
					pendingList.add(element);

					if(statusmap.get(element).toString().equals("Pending"))
					{
						pendingList.add("<font color='Red'><b>"+"Pending"+"</b></font>");
						if(userNameMap.get(element)!=null)
						{
						pendingList.add(userNameMap.get(element)+" ("+dsgnMap.get(element)+")");
						}
						else
						{
							pendingList.add("-");
						}
						pendingList.add("-");
						if(locName.get(element)!=null)
						{
						pendingList.add(locName.get(element));
						}
						else 
						{
							pendingList.add("-");
						}
						pendingList.add(sdf.format(createddate.get(element)));
						pendingList.add("-");
						crtdate=StringUtility.convertStringToDate(sdf.format(createddate.get(element)));

						
						long noofdays=deltaDays(crtdate,new Date());
						pendingList.add(noofdays);
						pendingList.add("-");
					}
					if(statusmap.get(element).toString().equals("Approve"))
					{
						pendingList.add("<font color='Green'><b>"+"Approve"+"</b></font>");
						pendingList.add("-");
						if(userNameMap.get(element)!=null)
						{
						pendingList.add(userNameMap.get(element)+" ("+dsgnMap.get(element)+")");
						}
						else
						{
							pendingList.add("-");
						}
						if(locName.get(element)!=null)
						{
						pendingList.add(locName.get(element));
						}
						else 
						{
							pendingList.add("-");
						}

						pendingList.add(sdf.format(createddate.get(element)));

						pendingList.add(approveddate.get(element));
						pendingList.add("-");
						crtdate=StringUtility.convertStringToDate(sdf.format(createddate.get(element)));
						Date appdate=StringUtility.convertStringToDate(sdf.format(approveddate.get(element)));

						
						long noofdays=deltaDays(crtdate,appdate);
						pendingList.add(noofdays);
					}
					if(Long.parseLong(modMap.get(element).toString())==330023)
					{
						pendingList.add("330023");
						pendingList.add(index);
					}
					if(Long.parseLong(modMap.get(element).toString())==330022)
					{

						pendingList.add("330022");
						pendingList.add(index+1);
					}
					if(Long.parseLong(modMap.get(element).toString())==330024)
					{

						pendingList.add("330024");
						pendingList.add(index+2);
					}
					if(Long.parseLong(modMap.get(element).toString())==300025)
					{

						pendingList.add("300025");
						pendingList.add(index+3);
					}
					if(Long.parseLong(modMap.get(element).toString())==300013)
					{

						pendingList.add("300013");
						pendingList.add(index+4);
					}
					if(Long.parseLong(modMap.get(element).toString())==300010)
					{
						pendingList.add("300010");
						pendingList.add(index+5);
					}
					if(Long.parseLong(modMap.get(element).toString())==300011)
					{
						pendingList.add("300011");
						pendingList.add(index+6);
					}
					if(Long.parseLong(modMap.get(element).toString())==300012)
					{
						pendingList.add("300012");
						pendingList.add(index+7);
					}
					if(Long.parseLong(modMap.get(element).toString())==300005)
					{
						pendingList.add("300005");
						pendingList.add(index+8);
					}
					if(Long.parseLong(modMap.get(element).toString())==300006)
					{
						pendingList.add("300006");
						pendingList.add(index+9);
					}
					if(Long.parseLong(modMap.get(element).toString())==300007)
					{
						pendingList.add("300007");
						pendingList.add(index+10);
					}
					if(Long.parseLong(modMap.get(element).toString())==300020)
					{
						pendingList.add("300020");
						pendingList.add(index+11);
					}
					if(Long.parseLong(modMap.get(element).toString())==300021)
					{
						pendingList.add("300021");
						pendingList.add(index+12);
					}
					if(Long.parseLong(modMap.get(element).toString())==300015)
					{
						pendingList.add("300015");
						pendingList.add(index+13);
					}
					if(Long.parseLong(modMap.get(element).toString())==320401)
					{
						pendingList.add("320401");
						pendingList.add(index+14);
					}
					if(Long.parseLong(modMap.get(element).toString())==320400)
					{
						pendingList.add("320400");
						pendingList.add(index+15);
					}
					if(Long.parseLong(modMap.get(element).toString())==330029)
					{
						pendingList.add("330029");
						pendingList.add(index+16);
					}
					if(Long.parseLong(modMap.get(element).toString())==330030)
					{
						pendingList.add("330030");
						pendingList.add(index+17);
					}
					if(Long.parseLong(modMap.get(element).toString())==330028)
					{
						pendingList.add("330028");
						pendingList.add(index+18);
					}
					if(Long.parseLong(modMap.get(element).toString())==330027)
					{
						pendingList.add("330027");
						pendingList.add(index+19);
					}
					if(Long.parseLong(modMap.get(element).toString())==330026)
					{
						pendingList.add("330026");
						pendingList.add(index+20);
					}
					if(Long.parseLong(modMap.get(element).toString())==300001)
					{
						pendingList.add("300001");
						pendingList.add(index+21);
					}
					if(Long.parseLong(modMap.get(element).toString())==300002)
					{
						pendingList.add("300002");
						pendingList.add(index+22);
					}
					if(Long.parseLong(modMap.get(element).toString())==300003)
					{
						pendingList.add("330027");
						pendingList.add(index+23);
					}	
						
						
					DataList.add(pendingList);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				lCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		logger.info("findReportDate method executed successfully");
		return DataList;
	}
	
	private long deltaDays(Date Createddate, Date today)
	{
		long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
	    long deltaMillis = today.getTime() - Createddate.getTime();
	    deltaMillis = deltaMillis / MILLIS_PER_DAY;
	    deltaMillis = Math.abs( deltaMillis );
	    return deltaMillis;
	}
}
