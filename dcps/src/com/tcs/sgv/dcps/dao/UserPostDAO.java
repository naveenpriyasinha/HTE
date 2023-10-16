package com.tcs.sgv.dcps.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;


public class UserPostDAO extends GenericDaoHibernateImpl<UserPostCustomVO, Long>  {
	Log logger = LogFactory.getLog(getClass());
	public UserPostDAO(Class<UserPostCustomVO> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getAllEmpDataFromUserId(long langId,String locationCode, String lPostName, String PsrNo, String BillNo, String Dsgn, long empId )
	{
		List userPostList = null;


		/*SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			   session =sessionFactory.openSession();*/
		Session session = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append("  select  m.emp_id, concat(concat(concat(m.emp_fname, ' '), concat(m.emp_mname, ' ')), concat(m.emp_lname, ' ')), u.emp_post_id, postmst.post_name, postmst.post_id, postmst.dsgn_id,  dsgnmst.dsgn_name,  usermst.user_id, pf.psr_no   ");
		if(BillNo != null && !(BillNo.trim()).equals("")){
			sb.append("  , mp.bill_no  , u.start_date, u.end_date from org_emp_mst  m,   org_userpost_rlt     u, org_user_mst         usermst, org_post_details_rlt postmst, org_post_mst  p,   org_designation_mst  dsgnmst,  HR_PAY_POST_PSR_MPG pf left outer join hr_pay_bill_subhead_mpg mp on pf.bill_no = mp.bill_subhead_id ");        
		}else{
			sb.append(" ,(select mp.bill_no  from hr_pay_bill_subhead_mpg mp where  pf.bill_no = mp.bill_subhead_id) BillNo  , u.start_date, u.end_date from org_emp_mst  m,   org_userpost_rlt     u, org_user_mst         usermst, org_post_details_rlt postmst, org_post_mst  p,   org_designation_mst  dsgnmst,  HR_PAY_POST_PSR_MPG pf    ");
		}
		sb.append("  where m.user_id = u.user_id and u.post_id = postmst.post_id and  postmst.post_id = pf.post_id and  usermst.user_id = u.user_id and p.post_id = postmst.post_id and  p.post_id = u.post_id and dsgnmst.dsgn_id = postmst.dsgn_id and p.location_code = '"+locationCode+"' and m.lang_id="+langId );
		if(PsrNo != null && !(PsrNo.trim()).equals(""))
			sb.append("  and pf.psr_no = " + PsrNo );
		else if(BillNo != null && !(BillNo.trim()).equals(""))
			sb.append("  and mp.bill_no  = " + BillNo );
		else if(Dsgn != null && !(Dsgn.trim()).equals(""))
			sb.append("  and  upper(dsgnmst.dsgn_name) like  upper('%" + Dsgn+ "%')  ");
		else if(lPostName != null && !(lPostName.trim()).equals(""))
			sb.append("  and  upper(postmst.post_name) like  upper('%" + lPostName+ "%') ");  
		else if(empId != 0 )
			sb.append("  and m.emp_id = " + empId);

		sb.append("  order by m.emp_fname , m.emp_mname , m.emp_lname" );
		Query sqlquery = session.createSQLQuery(sb.toString());
		logger.info("Query---->>"+sqlquery.toString());

		userPostList = sqlquery.list();

		return userPostList;
	}	
	// Mehtod added w.r.t. Single screen for new employee configuration in payroll
	public List getConfigEmpData(long langId,long locId, String lPostName, String PsrNo, String BillNo, String Dsgn, long empId )
	{
		List userPostList = null;

		Session session = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append("  select  m.emp_id, concat(concat(concat(m.emp_fname, ' '), concat(m.emp_mname, ' ')), concat(m.emp_lname, ' ')), u.emp_post_id, postmst.post_name, postmst.post_id, postmst.dsgn_id,  dsgnmst.dsgn_name,  usermst.user_id, pf.psr_no   ");
		if(BillNo != null && !(BillNo.trim()).equals("")){
			sb.append("  , mp.bill_no  , u.start_date, u.end_date from org_emp_mst  m,   org_userpost_rlt     u, org_user_mst         usermst, org_post_details_rlt postmst, org_post_mst  p,   org_designation_mst  dsgnmst,  HR_PAY_POST_PSR_MPG pf left outer join hr_pay_bill_subhead_mpg mp on pf.bill_no = mp.bill_subhead_id ");        
		}else{
			sb.append(" ,(select mp.bill_no  from hr_pay_bill_subhead_mpg mp where  pf.bill_no = mp.bill_subhead_id) BillNo  , u.start_date, u.end_date from org_emp_mst  m,   org_userpost_rlt     u, org_user_mst         usermst, org_post_mst  p,   org_designation_mst  dsgnmst, org_post_details_rlt postmst left outer join HR_PAY_POST_PSR_MPG  pf on postmst.post_id = pf.post_id    ");
		}
		sb.append("  where m.user_id = u.user_id and u.post_id = postmst.post_id and  usermst.user_id = u.user_id and p.post_id = postmst.post_id and  p.post_id = u.post_id and dsgnmst.dsgn_id = postmst.dsgn_id and postmst.loc_id = '"+locId+"' and m.lang_id="+langId );
		if(PsrNo != null && !(PsrNo.trim()).equals(""))
			sb.append("  and pf.psr_no = " + PsrNo );
		else if(BillNo != null && !(BillNo.trim()).equals(""))
			sb.append("  and mp.bill_no  = " + BillNo );
		else if(Dsgn != null && !(Dsgn.trim()).equals(""))
			sb.append("  and  upper(dsgnmst.dsgn_name) like  upper('%" + Dsgn+ "%')  ");
		else if(lPostName != null && !(lPostName.trim()).equals(""))
			sb.append("  and  upper(postmst.post_name) like  upper('%" + lPostName+ "%') ");  
		else if(empId != 0 )
			sb.append("  and m.emp_id = " + empId);

		sb.append("  order by m.emp_fname , m.emp_mname , m.emp_lname  " );
		Query sqlquery = session.createSQLQuery(sb.toString());
		logger.info("Query---->>"+sqlquery.toString());

		userPostList = sqlquery.list();

		return userPostList;
	}	
	// ended by rahul

	public List getAllEmpDataFromUserId(long empId,long langId)
	{
		List userPostList = null;
		Session session = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append(" select m.emp_id, m.emp_fname, m.emp_mname, m.emp_lname, u.emp_post_id,postmst.post_name,postmst.post_id,postmst.dsgn_id,dsgnmst.dsgn_name,usermst.user_id  ");
		sb.append("  from org_emp_mst  m,org_userpost_rlt u, org_user_mst usermst,org_post_details_rlt  postmst,org_post_mst  p ,org_designation_mst dsgnmst  ");
		sb.append("  where m.user_id = u.user_id  and u.post_id=postmst.post_id and usermst.user_id=u.user_id and p.post_id=postmst.post_id  and p.post_id=u.post_id and dsgnmst.dsgn_id=postmst.dsgn_id and m.lang_id='"+langId+"'and m.emp_id="+empId);
		Query sqlquery = session.createSQLQuery(sb.toString());
		logger.info("Query---->>"+sqlquery.toString());
		userPostList = sqlquery.list();
		return userPostList;
	}	
	public List getAllUserDataFromuserId(long langId)
	{
		List userList = null;
		Session session = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append("select m.emp_id,m.emp_fname,m.emp_mname,m.emp_lname,usermst.user_id,usermst.user_name  ");
		sb.append("  from org_emp_mst m,org_user_mst usermst  ");
		sb.append("  where m.user_id = usermst.user_id and m.lang_id='"+langId+"'");
		sb.append("  and usermst.user_id  in (select h.user_id from org_userpost_rlt  h where h.activate_flag=0 and sysdate > h.end_date )");



		Query sqlquery = session.createSQLQuery(sb.toString());

		logger.info("Query---->>"+sqlquery.toString());

		userList = sqlquery.list();

		return userList;
	}	
	public List getAllUserDataFromUserId(long langId)
	{
		List userList = null;
		Session session = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append("select m.emp_id,m.emp_fname,m.emp_mname,m.emp_lname,usermst.user_id,usermst.user_name  ");
		sb.append("  from org_emp_mst m,org_user_mst usermst  ");
		sb.append("  where m.user_id = usermst.user_id  and  m.lang_id="+langId);
		Query sqlquery = session.createSQLQuery(sb.toString());
		logger.info("Query---->>"+sqlquery.toString());
		userList = sqlquery.list();
		return userList;
	}	

	public List getAllUserDataFromEmpId(long empId,long langId)
	{
		List userList = null;
		Session session = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append("select m.emp_id,m.emp_fname,m.emp_mname,m.emp_lname,usermst.user_id,usermst.user_name  ");
		sb.append("  from org_emp_mst m,org_user_mst usermst  ");
		sb.append("  where m.user_id = usermst.user_id and m.lang_id='"+langId+"' and m.emp_id="+empId);
		Query sqlquery = session.createSQLQuery(sb.toString());

		logger.info("Query---->>"+sqlquery.toString());

		userList = sqlquery.list();

		return userList;
	}	

	public List getAllUserPostRltDatabyDesg(long loc_id,long desgId)
	{
		List userpostrlt = null;
		Session hibSession = getSession();
		logger.info("Before Execution loc_id :-"+loc_id);
		logger.info("Before Execution desgId :-"+desgId);
		StringBuffer sb = new StringBuffer();

		sb.append("  select pd.post_id,  pd.post_name, P.PSR_NO, (select mp.bill_group_id from mst_dcps_bill_group mp where  p.bill_no = mp.bill_group_id)  BillNo ");
		sb.append(" from org_post_details_rlt pd,HR_PAY_POST_PSR_MPG p  where pd.dsgn_id = '" + desgId + "' and P.POST_ID = PD.POST_ID and  ");	   
		sb.append(" pd.loc_id in  (select cmnLocMst.loc_id from cmn_location_mst cmnLocMst where cmnLocMst.loc_id = '" + loc_id + "' or cmnLocMst.parent_loc_id = '" + loc_id + "') and pd.lang_id = 1 and ");
		sb.append(" (pd.post_id not in (select prlt.post_id from org_userpost_rlt prlt where prlt.end_date is  null or prlt.activate_flag = 1 ))  ");
		sb.append("  order by pd.post_name  ");
		Query query = hibSession.createSQLQuery(sb.toString());
		userpostrlt = query.list();
		logger.info("List size is:-"+userpostrlt.size());

		return userpostrlt;
	}	

	public List getAllDesgMasterData(long langId)
	{
		List desig_mst=new ArrayList();
		Session hibSession = getSession();
		String hql="from OrgDesignationMst a where a.cmnLanguageMst.langId='"+langId + "' and activateFlag = 1  order by a.dsgnName";
		Query myQuery=hibSession.createQuery(hql);
		desig_mst=myQuery.list();
		return desig_mst;
	}


	public List getAllEmpDatabyUserId(long langId,long userId)
	{
		List emp_mst=new ArrayList();
		Session hibSession = getSession();
		String hql="select * from org_emp_mst empmst where empmst.user_id="+userId;
		Query myQuery=hibSession.createSQLQuery(hql);
		emp_mst=myQuery.list();
		return emp_mst;
	}
	public List getAllUserDataNotInUserPost(long langId)
	{
		List userList = null;
		Session session = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append("  select  m.emp_id,concat(concat(m.emp_fname,' '), m.emp_mname) ,m.emp_lname,usermst.user_id,usermst.user_name  ");
		sb.append("  from org_emp_mst m,org_user_mst usermst  ");
		sb.append("  where m.user_id = usermst.user_id and m.lang_id='"+langId+"'");
		sb.append("  and (usermst.user_id  in (select h.user_id from org_userpost_rlt  h where h.activate_flag=0 and sysdate > h.end_date )");
		sb.append("  or usermst.user_id not in (select h.user_id from org_userpost_rlt h))");	
		sb.append("  order by m.emp_fname,m.emp_mname,m.emp_lname ");


		Query sqlquery = session.createSQLQuery(sb.toString());

		logger.info("Query---->>"+sqlquery.toString());

		userList = sqlquery.list();

		return userList;
	}	

	public List getAllEmpDataFromUserIdNotInUserPostbyDate(long langId)
	{
		List userPostList = null;


		/*SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		   session =sessionFactory.openSession();*/
		Session session = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append("select m.emp_id,m.emp_fname,m.emp_mname,m.emp_lname,u.emp_post_id,postmst.post_name,postmst.post_id,postmst.dsgn_id,dsgnmst.dsgn_name,usermst.user_id ");
		sb.append("  from org_emp_mst m,org_userpost_rlt u,org_user_mst usermst,org_post_details_rlt postmst,org_post_mst p,org_designation_mst dsgnmst  ");
		sb.append("  where usermst.user_id = m.user_id and usermst.user_id = u.user_id and u.post_id = postmst.post_id and dsgnmst.dsgn_id = postmst.dsgn_id and u.user_id = m.user_id and  ");
		sb.append("postmst.post_id = p.post_id and usermst.user_id in (select distinct (h.user_id) from org_userpost_rlt h where h.activate_flag = 1 and TO_CHAR(Sysdate, 'YYYY/MM/DD') >  TO_CHAR(h.end_date, 'YYYY/MM/DD'))and m.lang_id="+langId);
		Query sqlquery = session.createSQLQuery(sb.toString());

		logger.info("Query---->>"+sqlquery.toString());

		userPostList = sqlquery.list();

		return userPostList;
	}	

	public List getAllUserPostRltDatabyDesgByDate(long loc_id,long desgId)
	{
		List userpostrlt = null;
		Session hibSession = getSession();
		logger.info("Before Execution:-");

		StringBuffer sb = new StringBuffer();
		sb.append(" select pd.post_id,pd.post_name ");
		sb.append(" from  org_post_details_rlt pd ");
		sb.append(" where pd.dsgn_id = '"+desgId+"' ");
		sb.append(" and  pd.loc_id in (select cmnLocMst.loc_id from cmn_location_mst cmnLocMst where cmnLocMst.loc_id ='"+ loc_id+"' or cmnLocMst.parent_loc_id = '"+loc_id+"' )" ) ;
		sb.append(" and pd.lang_id = 1 ");
		sb.append(" and pd.post_id in (select  post_id from org_userpost_rlt h where  h.activate_flag ='1' and sysdate > h.end_date) ");
		sb.append(" and pd.post_id not in (select post_id from RLT_DCPS_PAYROLL_EMP  where loc_id='"+loc_id+"')");
		sb.append("  order by pd.post_name  ");

		Query query = hibSession.createSQLQuery(sb.toString());
		userpostrlt = query.list();
		logger.info("List size is:-"+userpostrlt.size());

		return userpostrlt;
	}	

	public List getParentPosts(long desgId){
		List lstParentPost = new ArrayList();
		Session hibSession = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append(" from OrgPostDetailsRlt pd ");
		sb.append(" where pd.orgDesignationMst.dsgnId = "+desgId);
		logger.info("The Query String is:-"+sb.toString());
		Query query = hibSession.createQuery(sb.toString());
		lstParentPost = query.list();
		logger.info("Parent Post List size is:-"+lstParentPost.size());
		return lstParentPost;
	}

	public List getPostName(long locId){
		List postNameList = new ArrayList();
		Session hibSession = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append(" select pd.post_name,pd.post_id  from org_post_details_rlt pd  ");
		sb.append(" where pd.loc_id="+locId+" and pd.dsgn_id in (select ds.dsgn_id from org_designation_mst ds where ds.activate_flag=1)   order by  pd.post_name  ");
		logger.info("The Query String is:-"+sb.toString());
		Query query = hibSession.createSQLQuery(sb.toString());
		postNameList = query.list();
		logger.info("Parent Post List size is testing dao:-"+postNameList.size());
		return postNameList;
	}	

	public List getPostNameForDisplay(long locId,String lPostName,String PsrNo,String BillNo,String Dsgn ){
		List postNameList = new ArrayList();
		Session hibSession = getSession();
		
		StringBuffer sb = new StringBuffer();
		sb.append("  select pd.post_name,pd.post_id,(select concat(concat(concat(o.emp_fname, ' '), concat(o.emp_mname, ' ')),concat( o.emp_lname,' ')) from org_emp_mst o, org_userpost_rlt up ");
		sb.append("  where  o.lang_id = 1 and o.user_id = up.user_id and up.post_id = pd.post_id and up.end_date is null and up.activate_flag = 1),ds.dsgn_name , P.PSR_NO ");
		if(BillNo != null && !(BillNo.trim()).equals(""))
			//sb.append("  , mp.bill_no from org_post_details_rlt pd, org_designation_mst ds, HR_PAY_POST_PSR_MPG p left outer join hr_pay_bill_subhead_mpg mp on p.bill_no = mp.bill_subhead_id ");        
			sb.append("  , mp.DESCRIPTION from org_post_details_rlt pd, org_designation_mst ds, HR_PAY_POST_PSR_MPG p left outer join mst_dcps_bill_group mp on p.bill_no = mp.BILL_GROUP_ID ");
		else
		{
			sb.append("  , (select mp.DESCRIPTION  from mst_dcps_bill_group mp where  p.bill_no = mp.BILL_GROUP_ID) BillNo,org.post_type_lookup_id,cmn.lookup_name from org_post_details_rlt pd, org_designation_mst ds,org_post_mst org, cmn_lookup_mst cmn" );
			sb.append("  , HR_PAY_POST_PSR_MPG p ");
		}
		sb.append("  where pd.loc_id in (select location_code from org_ddo_mst where post_id in(select ZP_DDO_POST_ID from rlt_zp_ddo_map where rept_ddo_code=(select ddo_code from org_ddo_mst where location_code= '" + locId +"' ))) and P.POST_ID = PD.POST_ID and  pd.dsgn_id = ds.dsgn_id and ds.lang_id = 1  " );
		if(PsrNo != null && !(PsrNo.trim()).equals(""))
			sb.append("  and p.psr_no = " + PsrNo );
		else if(BillNo != null && !(BillNo.trim()).equals(""))
			sb.append("  and mp.bill_no  = " + BillNo );
		else if(Dsgn != null && !(Dsgn.trim()).equals(""))
			sb.append("  and  upper(ds.dsgn_name) like  upper('%" + Dsgn+ "%')  ");
		else
			sb.append("  and  upper(pd.post_name) like  upper('%" + lPostName+ "%') ");   
		sb.append(" and pd.post_id=org.post_id ");
		sb.append(" and cmn.lookup_id=org.post_type_lookup_id and org.activate_flag=1 ");
		//sb.append("   order by pd.post_name  ");
		sb.append("   order by pd.CREATED_DATE desc  ");

		logger.info("The Query String is:-"+sb.toString());
		Query query = hibSession.createSQLQuery(sb.toString());
		postNameList = query.list();
		logger.info("Parent Post List size is:-"+postNameList.size());
		logger.info("Parent Post List size is:-"+postNameList.listIterator());
		return postNameList;
	}		
	public List getPostNameForDisplayThruEmpId(long locId, String empId, long langId)
	{
		List postNameList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" select pd.post_name,  pd.post_id, concat(concat(concat(o.emp_fname, ' '), concat(o.emp_mname, ' ')), concat(o.emp_lname, ' ')), ds.dsgn_name, p.psr_no, (select mp.bill_no  from hr_pay_bill_subhead_mpg mp  ");
		sb.append(" where  p.bill_no = mp.bill_subhead_id) BillNo  from org_emp_mst o,  org_userpost_rlt  up,  HR_PAY_POST_PSR_MPG p , org_post_details_rlt    pd, org_designation_mst     ds  where o.lang_id = 1 and o.user_id = up.user_id  ");
		sb.append(" and up.post_id = pd.post_id and pd.post_id = p.post_id and  pd.loc_id = p.loc_id and up.activate_flag=1");
		sb.append(" and o.emp_id = "+ empId +" and  pd.loc_id = " +locId + " and pd.dsgn_id = ds.dsgn_id and ds.lang_id = " + langId + " order by pd.post_name ");
		logger.info("The Query String is:-"+sb.toString());
		Query query = hibSession.createSQLQuery(sb.toString());
		postNameList = query.list();
		logger.info("Parent Post List size is:-"+postNameList.size());
		logger.info("Parent Post List size is:-"+postNameList.listIterator());
		return postNameList;

	}		


	public List getAllUserPostRltDatabyDesgNew(long loc_id,long desgId)
	{
		List userpostrlt = null;
		String ddoCode=null;
		Session hibSession = getSession();
		//logger.info("Before Execution:-");

		//added by vaibhav tyagi:start
		StringBuffer str = new StringBuffer();
		str.append("select ddo_code from org_ddo_mst where location_code="+loc_id);
		logger.info( "execyting Query......."+str.toString());

		Query sbQuery = hibSession.createSQLQuery(str.toString());
		ddoCode=sbQuery.uniqueResult().toString();
		//added by vaibhav tyagi:end

		StringBuffer sb = new StringBuffer();

		//changed by vaibhav tyagi
		sb.append("  select pd.post_id,  pd.post_name, sb.subject_name ");
			sb.append(" from org_post_details_rlt pd, FRM_SUB_POSTID_MPG sb , org_post_mst mst  where mst.POST_ID = pd.POST_ID and mst.ACTIVATE_FLAG = 1  and pd.dsgn_id = " + desgId + " and pd.POST_ID=sb.POST_ID and");	   
			sb.append(" pd.loc_id =  "+ loc_id + " and pd.lang_id = 1 and");
		    sb.append(" pd.post_id not in (select prlt.post_id from org_userpost_rlt prlt,org_post_details_rlt pd1 where pd1.post_id = prlt.post_Id and (prlt.end_date is  null or prlt.activate_flag = 1) and pd1.loc_id="+loc_id+")");
		    sb.append(" order by pd.post_name  ");

/*		//added by vaibhav tyagi:start
		sb.append("select a.POST_ID,b.POST_NAME, sb.subject_name from ");
		sb.append("org_post_mst a inner join ORG_POST_DETAILS_RLT b on b.POST_ID=a.POST_ID ");
		sb.append("inner join FRM_SUB_POSTID_MPG sb on b.POST_ID=sb.POST_ID ");
		sb.append("left outer join ORG_USERPOST_RLT c on c.POST_ID=a.POST_ID and c.ACTIVATE_FLAG=1 ");
		sb.append("inner join HR_PAY_OFFICEPOST_MPG d on d.POST_ID=a.POST_ID ");
		sb.append("inner join MST_DCPS_DDO_OFFICE e on e.DCPS_DDO_OFFICE_MST_ID=d.OFFICE_ID ");
		sb.append("where b.LOC_ID = '"+loc_id+"' and c.POST_ID is null and a.LOCATION_CODE = '"+loc_id+"' and (a.END_DATE > '2013-02-05' or a.END_DATE is null) ");
		sb.append("and d.post_Id not in (select RL.post_Id from rlt_dcps_payroll_emp RL,mst_dcps_emp EM where RL.dcps_Emp_Id = EM.dcps_Emp_Id and RL.post_Id is not null and EM.ddo_Code = '"+ddoCode+"' and EM.reg_Status not in (1,2)) ");
		sb.append("and b.DSGN_ID = "+desgId );
		sb.append(" and a.ACTIVATE_FLAG = 1 and POST_TYPE_LOOKUP_ID  in  (10001198130,10001198129) order by b.post_name");
		//added by vaibhav tyagi:end
*/
		logger.info( "execyting Query......."+sb.toString());

		Query query = hibSession.createSQLQuery(sb.toString());
		userpostrlt = query.list();
		logger.info("List size is:-"+userpostrlt.size());

		return userpostrlt;
	}
	//added by manish 
	public OrgPostMst getOrgPostFromUserId(long userId)
	{
		OrgPostMst postMst=new OrgPostMst();
		Session hibSession = getSession();
		String query="";
		List tempList= new ArrayList();

		query=" select rlt.orgPostMstByPostId from OrgUserpostRlt rlt where rlt.orgUserMst.userId="+userId;

		logger.info("query is getOrgPostFromUserId in UserPostDAO  "+query);

		Query queryList = hibSession.createQuery(query);
		tempList = queryList.list();
		if(tempList != null && tempList.size() > 0)
			postMst =(OrgPostMst)tempList.get(0);

		return postMst;
	}

	//ended by manish 


	//added by abhilash

	public List getPostNameForDisplayWithSearch(long locId,String lPostName,String PsrNo,String BillNo,String Dsgn )
	{
		List postNameList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("  select pd.post_name,pd.post_id,(select concat(concat(concat(o.emp_fname, ' '), concat(o.emp_mname, ' ')),concat( o.emp_lname,' ')) from org_emp_mst o, org_userpost_rlt up ");
		sb.append("  where  o.lang_id = 1 and o.user_id = up.user_id and up.post_id = pd.post_id and up.end_date is null and up.activate_flag = 1),ds.dsgn_name , P.PSR_NO ");
		sb.append("  , (select mp.DESCRIPTION  from mst_dcps_bill_group mp where  p.bill_no = mp.BILL_GROUP_ID) BillNo,org.post_type_lookup_id,cmn.lookup_name from org_post_details_rlt pd, org_designation_mst ds,org_post_mst org, cmn_lookup_mst cmn" );
		sb.append("  , HR_PAY_POST_PSR_MPG p ");
		sb.append("  where pd.loc_id = '" + locId +"' and P.POST_ID = PD.POST_ID and  pd.dsgn_id = ds.dsgn_id and ds.lang_id = 1  " );
		sb.append("  and  upper(pd.post_name) like  upper('%" + lPostName+ "%') ");   
		sb.append(" and pd.post_id=org.post_id ");
		sb.append(" and cmn.lookup_id=org.post_type_lookup_id ");
		//sb.append("   order by pd.post_name  ");
		sb.append("   order by pd.CREATED_DATE desc  ");

		logger.info("The Query String is:-"+sb.toString());
		logger.info("The Query lPostName is:-"+lPostName);


		Query query = hibSession.createSQLQuery(sb.toString());
		postNameList = query.list();
		logger.info("Parent Post List size is:-"+postNameList.size());
		logger.info("Parent Post List size is:-"+postNameList.listIterator());
		return postNameList;
	}

	//ended by abhilash 

	public List getPostLookupIdFromPostId(long postId)
	{
		List postLookupIdList = new ArrayList();
		String postLookupId ="";

		Session hibSession = getSession();

		StringBuffer buffer = new StringBuffer();
		buffer.append(" select P.POST_TYPE_LOOKUP_ID,P.POST_ID from ORG_POST_MST p where p.POST_ID="+postId );

		Query query = hibSession.createSQLQuery(buffer.toString());
		postLookupIdList = query.list();
		/*	 if(postLookupIdList.size()>0)
			 {
				 postLookupId = postLookupIdList.get(0).toString();
			 }
			 else
			 {
				 postLookupId="";
			 }*/
		logger.info("The Query getPostLookupIdFromPostId is:-"+query.toString());
		return postLookupIdList;
	}

}
