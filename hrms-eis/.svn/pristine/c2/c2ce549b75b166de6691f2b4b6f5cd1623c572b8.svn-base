package com.tcs.sgv.eis.dao;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;

public class DisplayPendingWorkDaoImpl extends GenericDaoHibernateImpl implements DisplayPendingWorkDao{
	
	Logger logger = Logger.getLogger(DisplayPendingWorkDaoImpl.class );
	Session hibSession=null;
	public DisplayPendingWorkDaoImpl(Class<DisplayPendingWorkDaoImpl> class1,
			SessionFactory sessionFactory) {	
		super(class1);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}
	@Override
	
	
	public String findPendingWork(String ddoCode) {
		String pendingWork=null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get OfficeList list in a Taluka for a perticular DDO---");
		
		sb.append("SELECT Taluka from MST_dcps_ddo_Office where ddo_code ='"+ddoCode+"' and  ddo_office='Yes'");
		//end by roshan
		logger.info("---- get office in a taluka---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		pendingWork = (String)query.uniqueResult();
		logger.info("pendingWork"+pendingWork);
		return pendingWork;
	}
	@Override
	public int findTotalNumberOfschool(String ddoCode) {
		List list = null;
		int count = 0;
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where status=0 and (REPT_ddo_code="+ddoCode+" or final_ddo_code="+ddoCode+")");

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  list = query.list();
			
			 if(list!= null && list.size()>0)
			 {
				 count = Integer.parseInt(list.get(0).toString());
				 
			 }
			 
	 
	
	return count;
	}
	
	public int findGPFCount(String ddoCode){
		
		Session session = getSession();
		List list = null;
		int count = 0;
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append(" Select count(1)");
		  sb.append(" FROM Mst_dcps_emp EM,Rlt_zp_Ddo_Map ZP ");
		  sb.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL > 2 and EM.zp_Status = 2");

		  sb.append(" AND ZP.REPT_DDO_POST_ID =" +
		  		"(select distinct REPT_DDO_POST_ID from ifms.rlt_zp_ddo_map where rept_ddo_code="+ddoCode+" ) " +
		  		"and EM.dcps_Or_Gpf='N'");
			
		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());
		  list = query.list();
			
			 if(list!= null && list.size()>0)
			 {
				 count = Integer.parseInt(list.get(0).toString());
				 
			 }
			 
	 
	
	return count;
	}
	@Override
	public int findDCPSCount(String ddoCode) {
		Session session = getSession();
		List list = null;
		int count = 0;
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append(" Select count(1)");
		  sb.append(" FROM Mst_dcps_emp EM,Rlt_zp_Ddo_Map ZP ");
		  sb.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL > 2 and EM.zp_Status = 2");

		  sb.append(" AND ZP.REPT_DDO_POST_ID =" +
		  		"(select distinct REPT_DDO_POST_ID from ifms.rlt_zp_ddo_map where rept_ddo_code="+ddoCode+" ) " +
		  		"and EM.dcps_Or_Gpf='Y'");
			
		  logger.info("---- retrieve usertype---" + sb.toString()); Query query = session.createSQLQuery(sb.toString());

		  list = query.list();
			
			 if(list!= null && list.size()>0)
			 {
				 count = Integer.parseInt(list.get(0).toString());
				 
			 }
			 
	 
	
	return count;
	}
	@Override
	public int findTotalCountOfPendingOffice(String ddoCode) {
		Session session = getSession();
		List list = null;
		int count = 0;
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("SELECT count(1) FROM MST_DCPS_DDO_OFFICE " +
		  		"where STATUS_FLAG=0 and DCPS_DDO_OFFICE_MST_ID in (SELECT OFFICE_ID FROM ifms.SH_DDO_OFFICE_RQT_MPG where REP_POST_ID=(select" +
		  		" distinct REPT_DDO_POST_ID from ifms.rlt_zp_ddo_map where rept_ddo_code="+ddoCode+" or final_ddo_code="+ddoCode+"))");

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  list = query.list();
			
			 if(list!= null && list.size()>0)
			 {
				 count = Integer.parseInt(list.get(0).toString());
				 
			 }
			 
	 
	
	return count;
	}
	@Override
	public int findDCPSCountByLevelThree(String ddoCode) {
		Session session = getSession();
		List list = null;
		int count = 0;
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append(" Select count(1)");
		  sb.append(" FROM Mst_dcps_emp EM,Rlt_zp_Ddo_Map ZP ");
		  sb.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL > 2 and EM.zp_Status = 3");

		  sb.append(" AND ZP.final_DDO_POST_ID =" +
		  		"(select distinct final_DDO_POST_ID from ifms.rlt_zp_ddo_map where final_ddo_code="+ddoCode+" ) " +
		  		"and EM.dcps_Or_Gpf='Y'");
			
		  logger.info("---- retrieve usertype---" + sb.toString()); 
		  Query query = session.createSQLQuery(sb.toString());
		  list = query.list();
			
			 if(list!= null && list.size()>0)
			 {
				 count = Integer.parseInt(list.get(0).toString());
				 
			 }
			 
	 
	
	return count;
	}
	@Override
	public int findGPFCountByLevelThree(String ddoCode) {
		Session session = getSession();
		List list = null;
		int count = 0;
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");
		  sb.append(" Select count(1)");
		  sb.append(" FROM Mst_dcps_emp EM,Rlt_zp_Ddo_Map ZP ");
		  sb.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL > 2 and EM.zp_Status = 3");

		  sb.append(" AND ZP.REPT_DDO_POST_ID =" +
		  		"(select distinct REPT_DDO_POST_ID from ifms.rlt_zp_ddo_map where rept_ddo_code="+ddoCode+" ) " +
		  		"and EM.dcps_Or_Gpf='N'");
			
		  logger.info("---- retrieve usertype---" + sb.toString()); 
		  Query query = session.createSQLQuery(sb.toString());
		  
		  list = query.list();
			
			 if(list!= null && list.size()>0)
			 {
				 count = Integer.parseInt(list.get(0).toString());
				 
			 }
			 
	 
	
	return count;
	}
	
	public long findCountDraftForms(String ddoCode){
		Session session = getSession();
		List list = null;
		int count = 0;
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");
		  sb.append(" Select count(1) from mst_dcps_emp where zp_status in (0,-1) and form_status=0 and ddo_code="+ddoCode+"");	
		  logger.info("---- retrieve usertype---" + sb.toString()); 
		  Query query = session.createSQLQuery(sb.toString());
		  
		  list = query.list();
			
			 if(list!= null && list.size()>0)
			 {
				 count = Integer.parseInt(list.get(0).toString());
				 
			 }
			 
	 
	
	return count;
	}public long findUsertype(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where rept_ddo_code="+ddoCode);

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}

	@Override
	public long checkUser(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where ZP_ddo_code="+ddoCode);

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}


	public long findUsertypeToCheckLevel3(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where Final_ddo_code="+ddoCode);

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}


	public long findUsertypeToCheckLevel4(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where Special_ddo_code="+ddoCode);

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}
	@Override
	public List getMissedEmpStatisticsDDOwise(String strDdocode) {
		
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		Session session = getSession();
		logger.info("hi i Finding Missing Employee");
		lSBQuery
		.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.zp_Status,EM.sentBack_Remarks,EM.gender,EM.ddo_Code,em.form_status,em.reg_status");
		lSBQuery.append(" FROM Mst_dcps_emp EM,Rlt_zp_Ddo_Map ZP ");
		lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL >= 2 and EM.zp_Status <= 4");
		lSBQuery.append(" AND EM.DDO_code ="+strDdocode+"");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		EmpList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return EmpList;
	}

	public void sendToLevel1(String strDcpsEmpIds) {
		logger.info("mstdcpsemp is for update the name is by roshan "+strDcpsEmpIds);
		Session hibSession = getSession();
		String query="update mst_dcps_emp set " +
				"zp_status=-1, form_status=0, reg_status=0" +
				" where dcps_emp_id="+strDcpsEmpIds+"";
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		logger.info("query for update status is "+query.toString());
		sqlQuery.executeUpdate();
	}
	public List getApproveEmployeeDDOwise(String strDdocode){
		
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		Session session = getSession();
		logger.info("hi i Finding Approve  Employee");
		lSBQuery
		.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.sevarth_id,EM.dob,EM.doj,EM.EMP_SERVEND_DT,EM.gender,em.dcps_Or_Gpf");
		lSBQuery.append(" FROM Mst_dcps_emp EM,Rlt_zp_Ddo_Map ZP ");
		lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL >= 2 and EM.zp_Status > 4");
		lSBQuery.append(" AND EM.DDO_code ="+strDdocode+"");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		EmpList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return EmpList;
	}
	public void deleteSelectedEmployee(String strDcpsEmpIds){
		logger.info("mstdcpsemp is for update the name is by roshan "+strDcpsEmpIds);
		Session hibSession = getSession();
		String query="update mst_dcps_emp set " +
				"ddo_code=null, uid_no=null, reg_status=0" +
				" where dcps_emp_id="+strDcpsEmpIds+"";
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		logger.info("query for update status is "+query.toString());
		sqlQuery.executeUpdate();
		}
	public void detachPost(String empId){
		logger.info("mstdcpsemp is for update the name is by roshan "+empId);
		Session hibSession = getSession();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("update ORG_USERPOST_RLT set activate_flag=0 where USER_ID = (select user_id from ORG_EMP_MST where EMP_ID  =(select org_emp_mst_id from mst_dcps_emp where dcps_emp_id="+empId+"))");
		Query sqlQuery = hibSession.createSQLQuery(lSBQuery.toString());
		logger.info("query for update status is "+lSBQuery.toString());
		sqlQuery.executeUpdate();
	}
	public void updateServiceExpiryDate(String empId, String serviceEndDate){
		logger.info("mstdcpsemp is about to update");
		Session hibSession = getSession();
		String query="update mst_dcps_emp set " +
				" SUPER_ANN_DATE='"+serviceEndDate+"',EMP_SERVEND_DT='"+serviceEndDate+"' " +
				" where dcps_emp_id="+empId+"";
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		logger.info("query for update is "+query.toString());
		sqlQuery.executeUpdate();
		
		String query2="update org_emp_mst set EMP_SRVC_EXP='"+serviceEndDate+"' where " +
				"emp_id=(select org_emp_mst_id from mst_dcps_emp where dcps_emp_id ="+empId+")";
		Query sqlQuery2 = hibSession.createSQLQuery(query2.toString());
		logger.info("query for update org_emp_mst is "+query2.toString());
		sqlQuery2.executeUpdate();
		
		String query3="update org_post_mst set activate_flag=1 where POST_ID in (select post_id from ORG_USERPOST_RLT" +
				" where user_id in (select user_id from org_emp_mst where emp_id in (select org_emp_mst_id " +
				"from MST_DCPS_EMP where dcps_emp_id= "+empId+")))";
		Query sqlQuery3 = hibSession.createSQLQuery(query3.toString());
		logger.info("query for update org_emp_mst is "+query3.toString());
		sqlQuery3.executeUpdate();
		
		String query4="update org_userpost_rlt set activate_flag=1 where POST_ID in (select post_id from ORG_USERPOST_RLT" +
		" where user_id in (select user_id from org_emp_mst where emp_id in (select org_emp_mst_id " +
		"from MST_DCPS_EMP where dcps_emp_id= "+empId+")))";
		Query sqlQuery4 = hibSession.createSQLQuery(query4.toString());
		logger.info("query for update org_emp_mst is "+query4.toString());
		sqlQuery4.executeUpdate();
		
		String query5="update org_userpost_rlt set activate_flag=1 where POST_ID in (select post_id from ORG_USERPOST_RLT" +
		" where user_id in (select user_id from org_emp_mst where emp_id in (select org_emp_mst_id " +
		"from MST_DCPS_EMP where dcps_emp_id= "+empId+")))";
		Query sqlQuery5 = hibSession.createSQLQuery(query5.toString());
		logger.info("query for update org_emp_mst is "+query5.toString());
		sqlQuery5.executeUpdate();

	}
	
	public List getMissedEmployeeDDOwise(String strDdocode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		Session session = getSession();
		logger.info("hi i Finding Approve  Employee");
		lSBQuery
		.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.doj,EM.gender,em.dcps_Or_Gpf");
		lSBQuery.append(" FROM Mst_dcps_emp EM,Rlt_zp_Ddo_Map ZP ");
		lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL >= 2 and EM.zp_Status <= 4");
		lSBQuery.append(" AND EM.DDO_code ="+strDdocode+"");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		EmpList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return EmpList;
	}
	//added by roshan for change user name page.
	public long getDDOHistory(String ddoCode){
		List list = null;
		int count = 0;
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from HR_PAY_DDO_HISTORY where ddo_code="+ddoCode+"");

		  logger.info("---- retrieve history---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  list = query.list();
			
			 if(list!= null && list.size()>0)
			 {
				 count = Integer.parseInt(list.get(0).toString());
				 
			 }
	return count;	
	}

	public List getDDoHistoryDetails(String ddoCode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> ddoList = null;
		Session session = getSession();
		logger.info("hi i Finding ddo history");
		lSBQuery.append("select sr_no,ddo_code,ddo_name,from_date,to_date from HR_PAY_DDO_HISTORY where DDO_code ="+ddoCode+"");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		ddoList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return ddoList;
	}
	public String getDetails(Long lngUserId){
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		String ExistingddoDtls = null;
		Session session = getSession();
		logger.info("hi i Finding ddo history");
		lSBQuery.append("select concat(emp_fname,emp_lname) from org_emp_mst where user_id ="+lngUserId+"");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		ExistingddoDtls = (String)lQuery.uniqueResult();
		logger.info("query is"+lQuery.toString());
		return ExistingddoDtls;	
	}
	public String getCretaedDate(Long lngUserId){
		
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		String ExistingddoCreatedDate = null;
		Session session = getSession();
		logger.info("hi i Finding ddo history");
		lSBQuery.append("select Created_Date from org_emp_mst where user_id ="+lngUserId+"");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		//DateUtility().convertToDDMMYYYY(objFwdTotal[9].toString().split(" ")[0])
		ExistingddoCreatedDate = lQuery.uniqueResult().toString();
		logger.info("query is"+lQuery.toString());
		return ExistingddoCreatedDate;		
	}
	
	public void insertDetails(String ddoCode, String ddoName, String fromDate,
			String toDate){
		Session session=getSession();
		logger.info("---- insert hr_pay_ddo_history DAo---");
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ifms.HR_PAY_DDO_HISTORY values ((select max(sr_no)+1 from ifms.HR_PAY_DDO_HISTORY),'"+ddoCode+"','"+ddoName+"',:startingDate,:endDate,sysdate)");
		logger.info("---- insert hr_pay_ddo_history DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		query.setParameter("startingDate", fromDate);
		query.setParameter("endDate", toDate);
		logger.info("---- query---"+sb);
		query.executeUpdate();
	}
	public void updateDetails(Long SrNo,
			String updatedDdoName, String updatedFromDate,String updatedTodate){
		Session session=getSession();
		logger.info("---- update hr_pay_ddo_history DAo---");
		StringBuffer sb = new StringBuffer();
		sb.append("update ifms.HR_PAY_DDO_HISTORY set ddo_name='"+updatedDdoName+"',from_date='"+updatedFromDate+"',to_date=:endDate where sr_no="+SrNo+" ");
		logger.info("---- upadte hr_pay_ddo_history DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		
		query.setParameter("endDate", updatedTodate);
		logger.info("---- query---"+sb);
		query.executeUpdate();
	}
	public void insertNewDetails(String ddoCode, String updatedDdoName,
			String updatedFromDate, String updatedToDate){
		Session session=getSession();
		logger.info("---- insert hr_pay_ddo_history DAo---");
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ifms.HR_PAY_DDO_HISTORY values ((select max(sr_no)+1 from ifms.HR_PAY_DDO_HISTORY),'"+ddoCode+"','"+updatedDdoName+"',:startingDate,:endDate,sysdate)");
		logger.info("---- insert hr_pay_ddo_history DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- query---"+sb);
		query.setParameter("startingDate", updatedFromDate);
		query.setParameter("endDate", updatedToDate);
		query.executeUpdate();
	}
	public void updateDdoNameInOrgEmpMst(Long lngUserId,
			String strDdoPersonalName){

		StringBuilder lSBQuery = new StringBuilder();
		logger.info("hiii i m to update the name in org_emp_mst whose user_id is"+lngUserId);
		Session session=getSession();
		try{
			lSBQuery.append("update org_emp_mst set emp_fname = '"+strDdoPersonalName+"',EMP_MNAME=' ', EMP_LNAME=' ' where user_id="+lngUserId+" ");
		//	lSBQuery.append("in (select user_id from ORG_USERPOST_RLT where post_id ");
		//	lSBQuery.append("in (select ddo_post_id from RLT_DCPS_DDO_ASST where ASST_POST_ID = "+lLngDdoAstPostId+"))");
			Query lQuery = session.createSQLQuery(lSBQuery.toString());
			lQuery.executeUpdate();
			logger.info("hiii i m to update the name in org_emp_mst"+lQuery.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	public List getSchoolList(String strDdocode){
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List schoolList = null;
		Session session = getSession();
		logger.info("hi i Finding school");
		lSBQuery.append(" Select zp_ddo_code,rept_ddo_code,final_ddo_code,special_ddo_code,status from rlt_zp_ddo_map ");
		lSBQuery.append(" where ((zp_ddo_code='"+strDdocode+"') or (rept_ddo_code='"+strDdocode+"') or (final_ddo_code='"+strDdocode+"') or (special_ddo_code='"+strDdocode+"')) order by zp_ddo_code asc");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		schoolList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return schoolList;
	}
	@Override
	public void deleteSchool(String ddoCode) {
		Session session=getSession();
		logger.info("---- update rlt_zp_ddo_map ---");
		StringBuffer sb = new StringBuffer();
		sb.append("update rlt_zp_ddo_map set status=-2 where zp_ddo_code=:ddoCode ");
		logger.info("---- update rlt_zp_ddo_map ---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		query.setParameter("ddoCode", ddoCode);
		logger.info("---- query---"+sb);
		query.executeUpdate();
		
		
		StringBuffer sb1 = new StringBuffer();
		sb1.append("update mst_dcps_ddo_office set dice_Code=null,district=null where ddo_code='"+ddoCode+"' ");
		Query query1 = session.createSQLQuery(sb1.toString());
		
		logger.info("---- query---"+sb1);
		query1.executeUpdate();
		
		StringBuffer sb2 = new StringBuffer();
		 sb2.append("update ORG_USER_MST set ACTIVATE_FLAG=0 where USER_ID in " +
					"(SELECT USER_ID FROM ORG_USERPOST_RLT where POST_ID in " +
					"(SELECT ZP_DDO_POST_ID FROM RLT_ZP_DDO_MAP where ZP_DDO_CODE="+ddoCode+"))" +
					" OR USER_NAME = '"+ddoCode+"_AST'");
		Query query2 = session.createSQLQuery(sb2.toString());
		logger.info("---- query---"+sb2);
		query2.executeUpdate();
		
	}
	
	
	public List getResult(String query) {
		
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		Session session = getSession();
		logger.info("hi i Finding Missing Employee");
		lSBQuery.append("Select "+query+"");
		
		lQuery = session.createSQLQuery(lSBQuery.toString());
		logger.info("query is"+lQuery.toString());
		EmpList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return EmpList;
	}

	//START added by samadhan for update details on home page
	public String getEmail(String ddoCode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		String email = null;
		List lstEmail=null;
		Session session = getSession();
		logger.info("Get email for ddo : "+ddoCode);
		lSBQuery.append("SELECT coalesce(EMAIL,'Not Available') FROM MST_DCPS_DDO_OFFICE where DDO_CODE = '"+ddoCode+"' and upper(DDO_OFFICE) = 'YES'");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		
		//email = lQuery.uniqueResult().toString();
		lstEmail=lQuery.list();
		if(lstEmail!= null && lstEmail.size()>0)
		 {
			email = lstEmail.get(0).toString();
			 
		 }
		else{
			email="Not Available";
		}
		
		
		logger.info("query to get email is"+lQuery.toString());
		return email;
	}

	public String getMobileNo(String ddoCode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		String mobleNo = null;
		List lstMobileNo=null;
		Session session = getSession();
		logger.info("Get mobile no for ddo : "+ddoCode);
		lSBQuery.append("SELECT coalesce(cast(TEL_NO2 as varchar),'Not Available') FROM MST_DCPS_DDO_OFFICE where DDO_CODE = '"+ddoCode+"' and upper(DDO_OFFICE) = 'YES'");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		
		//mobleNo = lQuery.uniqueResult().toString();
		
		lstMobileNo = lQuery.list();
		if(lstMobileNo!= null && lstMobileNo.size()>0)
		 {
			mobleNo = lstMobileNo.get(0).toString();
			 
		 }
		else{
			mobleNo="Not Available";
		}
		
		logger.info("query to get TEL_NO2(mobile) is"+lQuery.toString());
		return mobleNo;
		
		
	}
	
	public void updateUDise(String udise, String strDdocode) {
		
		Session session=getSession();
		logger.info("---- update MST_DCPS_DDO_OFFICE set udise:"+udise+" strDdocode:"+strDdocode);
		StringBuffer sb = new StringBuffer();
		
		sb.append("update MST_DCPS_DDO_OFFICE set DICE_CODE = '"+udise+"' where DDO_CODE = '"+strDdocode+"' and upper(DDO_OFFICE) = 'YES'");
		
		Query query = session.createSQLQuery(sb.toString());
		
		logger.info("---- query to update udise MST_DCPS_DDO_OFFICE---"+sb);
		query.executeUpdate();
		
	}
	
	public void updateMobileNo(String mobileNo, String strDdocode) {
		
		Session session=getSession();
		logger.info("---- update MST_DCPS_DDO_OFFICE set mobileNo:"+mobileNo+" strDdocode:"+strDdocode);
		StringBuffer sb = new StringBuffer();
		
		sb.append("update MST_DCPS_DDO_OFFICE set TEL_NO2 = '"+mobileNo+"' where DDO_CODE = '"+strDdocode+"' and upper(DDO_OFFICE) = 'YES'");
		
		Query query = session.createSQLQuery(sb.toString());
		
		logger.info("---- query to update mobileNo MST_DCPS_DDO_OFFICE---"+sb);
		query.executeUpdate();
		
	}
	
	public void updateEmail(String email, String strDdocode) {
		Session session=getSession();
		logger.info("---- update MST_DCPS_DDO_OFFICE set email:"+email+" strDdocode:"+strDdocode);
		StringBuffer sb = new StringBuffer();
		
		sb.append("update MST_DCPS_DDO_OFFICE set EMAIL = '"+email+"' where DDO_CODE = '"+strDdocode+"' and upper(DDO_OFFICE) = 'YES'");
		
		Query query = session.createSQLQuery(sb.toString());
		
		logger.info("---- query to update email MST_DCPS_DDO_OFFICE---"+sb);
		query.executeUpdate();
		
	}

	public String getInstituteName(String ddoCode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		String instituteName = null;
		Session session = getSession();
		logger.info("Get office name for ddo : "+ddoCode);
		lSBQuery.append("SELECT OFF_NAME FROM MST_DCPS_DDO_OFFICE where DDO_CODE = '"+ddoCode+"' and upper(DDO_OFFICE) = 'YES'");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		
		instituteName = lQuery.uniqueResult().toString();
		logger.info("query to get instituteName is"+lQuery.toString());
		return instituteName;
	}
	
	public String getInstituteNameForDiseCode(String diseCode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		String instituteName = null;
		Session session = getSession();
		logger.info("Get office name for diseCode: "+diseCode);
		lSBQuery.append("SELECT OFFICE_NAME FROM ZP_ADMIN_DICE_MPG where DICE_CODE = '"+diseCode+"'");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		
		instituteName = lQuery.uniqueResult().toString();
		logger.info("query to get instituteName is"+lQuery.toString());
		return instituteName;
	}

	public void updateInstituteName(String instituteName, String udise , String lStrDdocode) {
		Session session=getSession();
		
		logger.info("---- update MST_DCPS_DDO_OFFICE set instituteName:"+instituteName+" udise:"+udise);
		StringBuffer sb = new StringBuffer();
		sb.append("update MST_DCPS_DDO_OFFICE set OFF_NAME = '"+instituteName+"' where DICE_CODE = '"+udise+"'");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- query to update OFF_NAME MST_DCPS_DDO_OFFICE---"+sb);
		query.executeUpdate();
		
		logger.info("---- update ORG_DDO_MST set instituteName:"+instituteName+" lStrDdocode:"+lStrDdocode);
		StringBuffer sb1 = new StringBuffer();
		sb1.append("update ORG_DDO_MST set DDO_NAME = '"+instituteName+"' where DDO_CODE = '"+lStrDdocode+"'");
		Query query1 = session.createSQLQuery(sb1.toString());
		logger.info("---- query to update DDO_NAME ORG_DDO_MST---"+sb1);
		query1.executeUpdate();
		
	}
	
	public String getTanNo(String strDdocode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List lstTanNo=null;
		String tanNo = null;
		Session session = getSession();
		logger.info("Get TAN no for ddo : "+strDdocode);
		lSBQuery.append("SELECT coalesce(TAN_NO,'Not Available') FROM ORG_DDO_MST where  DDO_CODE = '"+strDdocode+"' ");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		
		lstTanNo = lQuery.list();
		if(lstTanNo!= null && lstTanNo.size()>0)
		 {
			tanNo = lstTanNo.get(0).toString();
			 
		 }
		else{
			tanNo="Not Available";
		}
		logger.info("query to get tanNo is"+lQuery.toString());
		logger.info("*********tanNo*********: "+tanNo);
		return tanNo;
	}
	
	public void updateTanNo(String tanNo, String strDdocode) {
		Session session=getSession();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("update ORG_DDO_MST set TAN_NO = '"+tanNo+"' where DDO_CODE = '"+strDdocode+"' ");
		
		Query query = session.createSQLQuery(sb.toString());
		
		logger.info("---- query to update tan no ORG_DDO_MMST---"+sb.toString());
		query.executeUpdate();
		
	}
	
public void updateSecretQuestionAndAnswer(String secretQuestion,
		String secretAnswer, String strDdocode) {
		
		Session session=getSession();
		logger.info("---- update ORG_USER_MST set secretQuestion:"+secretQuestion+" secretAnswer: "+secretAnswer+" strDdocode:"+strDdocode);
		StringBuffer sb = new StringBuffer();
		
		if(secretAnswer!=null && secretAnswer!="" && secretQuestion!=null && secretQuestion!="")
		{
			sb.append("update ORG_USER_MST set SECRET_QUE_OTHER = '"+secretQuestion+"' , SECRET_ANSWER = '"+secretAnswer+"' where USER_NAME = '"+strDdocode+"'");
			
			Query query = session.createSQLQuery(sb.toString());
			
			logger.info("---- query to update secretQuestion,secretAnswer ORG_USER_MST---"+sb.toString());
			query.executeUpdate();
			
		}
		else
		{
			logger.info("---- query to update secretQuestion,secretAnswer ORG_USER_MST--- secretQuestion is NULL and secretAnswer is NULL");
		}
		
		
		
	}
@Override
public Blob getAttachment() {
	logger.info("inside getAttachment");
	Blob result= null;
	String strQuery=null;
	
	
    strQuery = "SELECT final_attachment FROM CMN_ATTDOC_MST where SR_NO=(select SR_NO from CMN_ATTACHMENT_MPG where ATTACHMENT_ID=991000054388 )";
	logger.info(strQuery);
	Query query = hibSession.createSQLQuery(strQuery);
	result = (Blob) query.uniqueResult();
	
	return result;
}


	
	
	
	
	//END added by samadhan for update details on home page
}