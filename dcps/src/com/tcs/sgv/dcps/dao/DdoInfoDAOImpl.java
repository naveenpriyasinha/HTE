/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 19, 2011		Bhargav Trivedi								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.util.query.DateUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.DDOInformationDetail;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAOImpl;


/**
 * Class Description -
 * 
 * 
 * @author Bhargav Trivedi
 * @version 0.1
 * @since JDK 5.0 Mar 19, 2011
 */
public class DdoInfoDAOImpl extends GenericDaoHibernateImpl implements DdoInfoDAO {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger.getLogger(TrnPnsnProcInwardPensionDAOImpl.class);

	public DdoInfoDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	public DDOInformationDetail getDdoInfo(String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("FROM DDOInformationDetail");
		lSBQuery.append(" WHERE ddoCode = :ddoCode ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);

		DDOInformationDetail lObjDdoInformation = (DDOInformationDetail) lQuery.uniqueResult();

		return lObjDdoInformation;
	}

	public Boolean checkDdoExistOrNot(String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;
		Boolean DdoExistsOrNot = false;
		List ddoList = null;

		lSBQuery.append("FROM DDOInformationDetail");
		lSBQuery.append(" WHERE ddoCode = :ddoCode ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);

		ddoList = lQuery.list();
		if (ddoList.size() != 0) {
			DdoExistsOrNot = true;
		}

		return DdoExistsOrNot;
	}

	public OrgDdoMst getDdoInformation(String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("FROM OrgDdoMst");
		lSBQuery.append(" WHERE ddoCode = :ddoCode ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);

		OrgDdoMst ddoList = (OrgDdoMst) lQuery.uniqueResult();

		return ddoList;
	}
	
	public void updateDdoName(Long lLngDdoAstPostId, String lStrName)
	{
		StringBuilder lSBQuery = new StringBuilder();
		
		try{
			lSBQuery.append("update org_emp_mst set emp_fname = '"+lStrName+"' where user_id ");
			lSBQuery.append("in (select user_id from ORG_USERPOST_RLT where post_id ");
			lSBQuery.append("in (select ddo_post_id from RLT_DCPS_DDO_ASST where ASST_POST_ID = "+lLngDdoAstPostId+"))");
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//added by roshan
	public List getApproveDdoInformation(Long postId)
	
	{
		Session hibSession = getSession();
		
		String query=" select org.DDO_CODE,org.DDO_NAME,org.DDO_OFFICE,org.STATUS_FLAG from org_ddo_mst org inner join rlt_Zp_ddo_map rlt on rlt.ZP_DDO_CODE=org.DDO_CODE and rlt.REPT_DDO_POST_ID=:PostId)";
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		sqlQuery.setParameter("PostId", postId);
		logger.info("Query for Approve DDO information Data---->>>>"+sqlQuery);
		logger.info("--ApproveDDO----"+sqlQuery.list());
		if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
		{
			return sqlQuery.list();
		}
		else
		{
			return null;
		}
	}
//added by roshan
	public void updateDdoNameInOrgEmpMst(Long lngUserId,
			String strDdoPersonalName){

		StringBuilder lSBQuery = new StringBuilder();
		logger.info("hiii i m to update the name in org_emp_mst whose user_id is"+lngUserId);
		
		try{
			lSBQuery.append("update org_emp_mst set emp_fname = '"+strDdoPersonalName+"',EMP_MNAME=' ', EMP_LNAME=' ' where user_id="+lngUserId+" ");
		//	lSBQuery.append("in (select user_id from ORG_USERPOST_RLT where post_id ");
		//	lSBQuery.append("in (select ddo_post_id from RLT_DCPS_DDO_ASST where ASST_POST_ID = "+lLngDdoAstPostId+"))");
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.executeUpdate();
			logger.info("hiii i m to update the name in org_emp_mst"+lQuery.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
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
		lSBQuery.append("select emp_fname || emp_mname  || emp_lname from org_emp_mst where user_id ="+lngUserId+"");
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
	
	public void insertDetails(String strPkforHRPayDdoHistory,String ddoCode, String ddoName, String fromDate,
			String toDate, String status, String userId){
		Session session=getSession();
		logger.info("---- insert hr_pay_ddo_history DAo---");
		StringBuffer sb = new StringBuffer();
		//sb.append("insert into ifms.HR_PAY_DDO_HISTORY values ((select max(sr_no)+1 from ifms.HR_PAY_DDO_HISTORY),'"+ddoCode+"',:ddoName,:fromDate,:toDate,sysdate)");
		sb.append("insert into ifms.HR_PAY_DDO_HISTORY values (:strPkforHRPayDdoHistory,'"+ddoCode+"',:ddoName,:fromDate,:toDate,sysdate,'"+status+"','"+userId+"')");
		logger.info("---- insert hr_pay_ddo_history DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		query.setParameter("strPkforHRPayDdoHistory", strPkforHRPayDdoHistory);
		query.setParameter("ddoName", ddoName);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		gLogger.info("---parameters for query:---strPkforHRPayDdoHistory:"+strPkforHRPayDdoHistory+" ddoName:"+ddoName+" fromDate:"+fromDate+" toDate"+toDate);
		logger.info("---- query---"+sb);
		query.executeUpdate();
	}
	public void updateDetails(Long SrNo,
			String updatedDdoName, String updatedFromDate,String updatedTodate){
		Session session=getSession();
		logger.info("---- update hr_pay_ddo_history DAo---");
		StringBuffer sb = new StringBuffer();
		if(updatedTodate!=null && !updatedTodate.isEmpty() ){
		sb.append("update ifms.HR_PAY_DDO_HISTORY set ddo_name='"+updatedDdoName+"',from_date='"+updatedFromDate+"',to_date='"+updatedTodate+"',status=0 where sr_no="+SrNo+" ");
		}else{
			logger.info("---- insert in else part---");
			sb.append("update ifms.HR_PAY_DDO_HISTORY set ddo_name='"+updatedDdoName+"',from_date='"+updatedFromDate+"',to_date=null,status=0 where sr_no="+SrNo+" ");
		}
		logger.info("---- upadte hr_pay_ddo_history DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		//query.setParameter("updatedDdoName", updatedDdoName);
		//query.setParameter("updatedFromDate", updatedFromDate);
		//query.setParameter("updatedTodate", updatedTodate);
		logger.info("---- query---"+sb);
		query.executeUpdate();
	}
	public void insertNewDetails(String strPkforHRPayDdoHistory,String ddoCode, String updatedDdoName,
			String updatedFromDate, String updatedToDate,String status,String userId){
		Session session=getSession();
		logger.info("---- insert hr_pay_ddo_history DAo---");
		StringBuffer sb = new StringBuffer();
		if(updatedToDate!=null && !updatedToDate.isEmpty() ){
			sb.append("insert into ifms.HR_PAY_DDO_HISTORY values ("+strPkforHRPayDdoHistory+",'"+ddoCode+"','"+updatedDdoName+"','"+updatedFromDate+"','"+updatedToDate+"',sysdate,0,'"+userId+"')");
		}else{
			logger.info("---- insert in else part---");
			sb.append("insert into ifms.HR_PAY_DDO_HISTORY values ("+strPkforHRPayDdoHistory+",'"+ddoCode+"','"+updatedDdoName+"','"+updatedFromDate+"',null,sysdate,0,'"+userId+"')");
		}
		logger.info("---- insert hr_pay_ddo_history DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- query---"+sb);
		query.executeUpdate();
	}
	//added by samadhan
	public String getDDOtype(String strDdoCode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		String ddoType=null;
		String typeCheck=null;
		Session session = getSession();
		logger.info("inside getDDOtype and ddoCode to check type: "+strDdoCode);
		
		lSBQuery.append(" select distinct concat(concat(USED_AS_LEVEL2,USED_AS_LEVEL3),USED_AS_LEVEL4) from CMN_DDO_MST where DDO_CODE = '"+strDdoCode+"'");

		logger.info("==Query to check ddo type:== "+lSBQuery.toString());
		lQuery = session.createSQLQuery(lSBQuery.toString());
		//if(lQuery.list().get(0)!=null && !lQuery.list().get(0).equals(""))
		List lstDdoType=null;
		lstDdoType=lQuery.list();
		
		if(lstDdoType!=null && lstDdoType.size()>0)
		{
			gLogger.info("+++++inside if+++++");
			typeCheck = lstDdoType.get(0).toString();
			
		}
		
		return typeCheck;
	}

	
	public List getLevel1DDOList(String strDdoCode) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List ddoL1List = null;
		Session session = getSession();
		logger.info("Getting level 1 ddo list from level2 ddo");
		lSBQuery.append("SELECT DISTINCT ZP_DDO_CODE FROM  RLT_ZP_DDO_MAP where REPT_DDO_CODE =  '"+strDdoCode+"'");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		ddoL1List = lQuery.list();
		logger.info("query for ddoL1List is"+lQuery.toString());
		return ddoL1List;
		
	}

	
	public List getLevel2DDOList(String strDdoCode) {
		
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List ddoL2List = null;
		Session session = getSession();
		logger.info("Getting level 2 ddo list from level3 ddo");
		lSBQuery.append("SELECT DISTINCT REPT_DDO_CODE FROM RLT_ZP_DDO_MAP where FINAL_DDO_CODE = '"+strDdoCode+"'");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		ddoL2List = lQuery.list();
		logger.info("query for ddoL2List is"+lQuery.toString());
		return ddoL2List;
	}

	
	public List getLevel3DDOList(String strDdoCode) {
		
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List ddoL3List = null;
		Session session = getSession();
		logger.info("Getting level 3 ddo list from level4 ddo");
		lSBQuery.append("SELECT DISTINCT FINAL_DDO_CODE FROM RLT_ZP_DDO_MAP where SPECIAL_DDO_CODE = '"+strDdoCode+"'");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		ddoL3List = lQuery.list();
		logger.info("query for ddoL3List is"+lQuery.toString());
		return ddoL3List;
	}
	public List getDDoHistoryDetailsForApprove(String ddoCode) {
		logger.info("inside getDDoHistoryDetailsForApprove");
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> ddoList = null;
		Session session = getSession();
		logger.info("hello hiiiiiiiiiiiiiii");
		lSBQuery.append("select sr_no,ddo_code,ddo_name,from_date,to_date,status,user_id from HR_PAY_DDO_HISTORY where DDO_code in ("+ddoCode+") and status=0");
		lQuery = session.createSQLQuery(lSBQuery.toString());
		ddoList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return ddoList;
	}

	
	

	
	public void updateStatus(String srNo, String userID, String ddoName) {
		
		Session session=getSession();
		logger.info("---- inside updateStatus for updating status of ddo in HR_PAY_DDO_HISTORY---");
		StringBuffer sb = new StringBuffer();
		sb.append("update HR_PAY_DDO_HISTORY set status='1' where sr_No='"+srNo+"'");
		logger.info("---- Status updated---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- query to update status---"+sb);
		query.executeUpdate();
		
		
	}

	
	public void updateDDOName(String srNo, String userID, String ddoName) {
		
		Session session=getSession();
		//update name in org_emp_mst updateDdoNameInOrgEmpMst
		logger.info("---- inside updateStatus for updating ddo name in org_emp_mst---");
		StringBuffer sb = new StringBuffer();
		sb.append("update org_emp_mst set emp_fname = '"+ddoName+"',EMP_MNAME=' ', EMP_LNAME=' ' where user_id="+userID+" ");
		Query lQuery = session.createSQLQuery(sb.toString());
		lQuery.executeUpdate();
		logger.info("hiii i m to update the name in org_emp_mst: "+sb);
	}
	public void rejectDDOHistory(String srNo, String userID, String ddoName) {
		Session session=getSession();
		logger.info("---- inside rejectDDOHistory for updating status of ddo in HR_PAY_DDO_HISTORY---");
		StringBuffer sb = new StringBuffer();
		sb.append("update HR_PAY_DDO_HISTORY set status='2' where sr_No='"+srNo+"'");
		logger.info("---- Status updated---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- query to update status---"+sb);
		query.executeUpdate();
		
	}


	public String getUserName(Long lngUserId) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		String userName=null;
		
		Session session = getSession();
		logger.info("inside getUserName and lngUserId to check type: "+lngUserId);
		
		lSBQuery.append(" SELECT EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME FROM ORG_EMP_MST where USER_ID = '"+lngUserId+"'");

		logger.info("==Query to get ddo name:== "+lSBQuery.toString());
		lQuery = session.createSQLQuery(lSBQuery.toString());
		userName=lQuery.list().get(0).toString();
		return userName;
	}


	public void updateDDONameORGDDOMST(String ddoName, String strDdoCode) {

		Session session=getSession();
		//update name in ORG_DDO_MST updateDDONameORGDDOMST
		logger.info("---- inside updateDDONameORGDDOMST for updating ddo name in ORG_DDO_MST---");
		StringBuffer sb = new StringBuffer();
		sb.append("update ORG_DDO_MST set DDO_PERSONAL_NAME = '"+ddoName+"' where DDO_CODE = '"+strDdoCode+"'");
		Query lQuery = session.createSQLQuery(sb.toString());
		lQuery.executeUpdate();
		logger.info("hiii i m to update the name in ORG_DDO_MST: "+sb);
		
		
	}
	public List getDDoHistoryFromSecondLevel(String strDdoCode)
	{	
		
		List approv;
		Session session=getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select sr_no,ddo_code,ddo_name,from_date,to_date,status,user_id from HR_PAY_DDO_HISTORY where DDO_code in ("+strDdoCode+") and status=0");
		Query lQuery = session.createSQLQuery(sb.toString());
		approv=lQuery.list();
		logger.info("hiii i m to update the name in ORG_DDO_MST: "+sb);
		return approv;
		
	}
}
