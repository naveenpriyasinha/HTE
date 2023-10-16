package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class NewEmployeeConfigDAOImpl extends GenericDaoHibernateImpl implements NewEmployeeConfigDAO{

	public NewEmployeeConfigDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	public ArrayList getSelectedPosts(long selPostId,long langId,long locId)
	{
		ArrayList dtlsList = new ArrayList(); 
		Session hibSession = getSession();
        StringBuffer strQuery = new StringBuffer();        
        strQuery.append(" select psr.psr_no,pd.post_name,pd.post_short_name,pm.post_type_lookup_id,pd.branch_id,\n");
        strQuery.append("       op.office_id,op.remarks,pm.start_date,pm.end_date,pm.activate_flag,\n");
        strQuery.append("       ohp.order_head_post_id ohpId,ohm.order_head_id orderId,psr.bill_no,psr.id psrPostId\n");
        strQuery.append("  from org_post_mst         pm,org_post_details_rlt pd,hr_pay_post_psr_mpg  psr\n"); 
        strQuery.append("       left join hr_pay_officepost_mpg op on op.post_id = psr.post_id\n");
        strQuery.append("       left outer join hr_pay_order_head_post_mpg ohp on ohp.post_id = psr.post_id\n"); 
        strQuery.append("       left outer join hr_pay_order_head_mpg ohm on ohm.order_head_id = ohp.order_head_id\n"); 
        strQuery.append(" where pm.post_id = pd.post_id and pm.post_id = psr.post_id and\n");
        strQuery.append("       pd.post_id = ").append(selPostId).append(" and pd.lang_id = ").append(langId).append("\n");
        strQuery.append("       and pd.loc_id = ").append(locId).append(" and psr.loc_id = pd.loc_id");

        
        Query query = hibSession.createSQLQuery(strQuery.toString());
        
        dtlsList =(ArrayList) query.list();
        logger.info("dtlsList size is:::::::::"+dtlsList.size());    
		return dtlsList;
	}
	
		public List getBillNoData(long finYrId, long locId) {
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" select billHeadId,billId from HrPayBillHeadMpg where ");
		query.append(" cmnLocationMst.locId=").append(locId);
		//query.append(" and finYearId=").append(finYrId); This line is necessary but commented because in hr_pay_bill_subhead_mpg table there are entries with respect fin yr id 21 only.
		query.append(" order by billId ");
		logger.info("Query for get bill no is---->>>>" + query.toString());
		
		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();
		logger.info("the size of resList is ::"+resList.size());
		return resList;
	}
	public ArrayList getOrderNameList(long billSubHeadId, long locId)
	{
		ArrayList dtlsList = new ArrayList(); 
		Session hibSession = getSession();
        StringBuffer strQuery = new StringBuffer();        
        strQuery.append(" select ohm.order_head_id, om.order_name, om.order_date\n" );
        strQuery.append("  from hr_pay_order_head_mpg   ohm,\n" );
        strQuery.append("       hr_pay_bill_subhead_mpg bhm,\n" );
        strQuery.append("       hr_pay_order_mst        om\n" );
        strQuery.append(" where ohm.subhead_id = bhm.subhead_code and bhm.loc_id = ").append(locId).append(" and\n"); 
        strQuery.append("       om.order_id = ohm.order_id and bhm.bill_subhead_id= ").append(billSubHeadId);
        strQuery.append("       order by om.order_name ");

        
        Query query = hibSession.createSQLQuery(strQuery.toString());
        
        dtlsList =(ArrayList) query.list();
        logger.info("dtlsList size in getOrderNameList method is:::::::::"+dtlsList.size());    
		return dtlsList;
	}
	public ArrayList getOrderDetails (long lOrderHeadId, long locId)
	{
		ArrayList dtlsList = new ArrayList(); 
		Session hibSession = getSession();
        StringBuffer strQuery = new StringBuffer();        
        
        strQuery.append(" select om.order_id, om.order_name, om.order_date, om.end_date\n" );
        strQuery.append("  from hr_pay_order_mst om, hr_pay_order_head_mpg ohm\n" );
        strQuery.append(" where om.order_id = ohm.order_id and  ohm.order_head_id = ").append(lOrderHeadId);
        Query query = hibSession.createSQLQuery(strQuery.toString());
        
        dtlsList =(ArrayList) query.list();
        logger.info("OrderDetails size in getOrderDetails method is:::::::::"+dtlsList.size());    
		return dtlsList;
	}
	
	
// Added by Muneendra
	
	public ArrayList getSchemeDetails (long billSubHeadId)
	{
		ArrayList dtlsList = new ArrayList(); 
		Session hibSession = getSession();
        StringBuffer strQuery = new StringBuffer();        
        
        strQuery.append(" select concat(SCHEME_CODE,SCHEME_NAME) from ORG_SCHEME_MST where BUDSUBHD_ID in ( Select SUBHeaD_code from HR_PAY_BILL_SUBHEAD_MPG where BILL_SUBHEAD_ID  = " + billSubHeadId+ ")");
        Query query = hibSession.createSQLQuery(strQuery.toString());
        

       // Select SCHEME_CODE,SCHEME_NAME from ORG_SCHEME_MST where BUDSUBHD_ID in (Select SUBHeaD_code from HR_PAY_BILL_SUBHEAD_MPG where BILL_NO  = 14 )
        
        dtlsList =(ArrayList) query.list();
        logger.info("Scheme Details size in getSchemeDetails method is:::::::::"+dtlsList.size());    
		return dtlsList;
	}
	
	
	
	public ArrayList getConfiguredEmpData(long userPostID,long langId,long locId,long proofTypeId)
	{
		ArrayList dtlsList = new ArrayList(); 
		Session hibSession = getSession();
        StringBuffer strQuery = new StringBuffer();      
        strQuery.append(" \n select pd.post_id,up.user_id,o.emp_id  orgEmpId,e.emp_id hrEmpId,\n");
        strQuery.append("       o.emp_prefix,o.emp_fname,o.emp_mname,o.emp_lname,e.emp_gender,o.grade_id orgGradeId,o.emp_dob,o.emp_doj,o.emp_srvc_exp,\n"); 
        strQuery.append("       g.gpf_acc_no,g.grade_id gpfGradeId,proofdtls.proof_num panNo,e.emp_type,e.email,\n"); 
        strQuery.append("       bd.bank_id,bd.bank_branch_id,bd.bank_acct_type,bd.bank_acct_no,bd.bank_acct_start_dt,\n"); 
        strQuery.append("       pd.dsgn_id,psr.psr_no,pd.post_name, pd.post_short_name,(select lp.lookup_name from cmn_lookup_mst lp where lp.lookup_id=p.post_type_lookup_id) postTypeId,pd.branch_id postBranchId,ompg.office_id,ompg.parent_post_id,ompg.remarks,up.start_date, up.end_date,up.activate_flag,\n"); 
        strQuery.append("       bh.bill_subhead_id,\n"); 
        strQuery.append("       (select oh.order_head_id FROm hr_pay_order_head_mpg oh where oh.order_head_id = ohp.order_head_id) ordreHeadId,psr.id psrpostId,up.emp_post_id usrpstId , \n"); 
        strQuery.append("       bd.bank_dtl_id,ohp.order_head_post_id ohpid\n");

        strQuery.append(" ,(select om.order_name from hr_pay_order_mst om where om.order_id =\n");
        strQuery.append("                (select oh.order_id FROm hr_pay_order_head_mpg oh where oh.order_head_id = ohp.order_head_id)) orderName\n"); 
        strQuery.append("       ,(select om.order_date from hr_pay_order_mst om where om.order_id =\n" );
        strQuery.append("                (select oh.order_id FROm hr_pay_order_head_mpg oh where oh.order_head_id = ohp.order_head_id)) orderStartDate\n" ); 
        strQuery.append("       ,(select om.end_date from hr_pay_order_mst om where om.order_id =\n" );
        strQuery.append("                (select oh.order_id FROm hr_pay_order_head_mpg oh where oh.order_head_id = ohp.order_head_id)) orderEndDate");

        strQuery.append(",(select om.order_id from hr_pay_order_mst om where om.order_id =\n" );
        strQuery.append("         (select oh.order_id FROm hr_pay_order_head_mpg oh where oh.order_head_id = ohp.order_head_id)) orderMstId,e.cadre_id cadreid,e.group_id groupid,\n");
        strQuery.append("       e.branch_id branchid,e.branch_join_date branchjoindate,e.cadre_join_date cadrejoindate,e.gis_group gisgroup,e.gis_applicable gisapplicable,\n");
        strQuery.append("       e.membership_date membershipdate,e.dcps_ac_no dcpsacno ,e.office_city_id officecityid,e.uid_no uidno,e.parrent_filed_depthod parentfielddepthod,e.scheme_id schemeid,e.contact_no contactno,e.date_of_initial_appointment dateofinitialappointment");

        strQuery.append(" From org_post_mst p,\n"); 
        strQuery.append("       hr_pay_post_psr_mpg psr,\n"); 
        strQuery.append("       hr_pay_bill_subhead_mpg bh,\n"); 
        strQuery.append("       hr_pay_order_head_post_mpg ohp,\n"); 
        strQuery.append("       org_designation_mst dm,\n"); 
        strQuery.append("       org_post_details_rlt pd\n"); 
        strQuery.append("       left outer join org_userpost_rlt up on up.post_id = pd.post_id \n");//and up.end_date is null\n"); 
        strQuery.append("       left outer join org_emp_mst o on o.user_id = up.user_id and o.lang_id = ").append(langId).append("\n"); 
        strQuery.append("       left outer join hr_eis_emp_mst e on e.emp_mpg_id = o.emp_id\n"); 
        strQuery.append("       left outer join hr_pay_gpf_details g on g.user_id = o.user_id\n"); 
        strQuery.append("       left outer join hr_eis_proof_dtl proofdtls on proofdtls.user_id = o.user_id and proofdtls.prooftype_lookupid = ").append(proofTypeId).append("\n"); 
        strQuery.append("       left outer join hr_eis_bank_dtls bd on bd.bank_emp_id = e.emp_id\n"); 
        strQuery.append("       left outer join hr_pay_officepost_mpg ompg on ompg.post_id = pd.post_id and ompg.loc_id = pd.loc_id\n"); 
        strQuery.append(" where pd.post_id = p.post_id\n"); 
        strQuery.append("       and psr.post_id = pd.post_id\n"); 
        strQuery.append("       and psr.loc_id = pd.loc_id\n"); 
        strQuery.append("       and bh.bill_subhead_id = psr.bill_no\n"); 
        strQuery.append("       and bh.loc_id = pd.loc_id\n"); 
        strQuery.append("       and ohp.post_id = pd.post_id\n"); 
        strQuery.append("       and pd.dsgn_id = dm.dsgn_id\n"); 
        strQuery.append("       and pd.loc_id = ").append(locId).append("\n"); 
        strQuery.append("       and up.emp_post_id = ").append(userPostID).append(" ");
        //strQuery.append("       and o.user_id = ").append(userID).append(" ");
        

        Query query = hibSession.createSQLQuery(strQuery.toString());
        logger.info("Query to get configured emp:: "+ query.toString());
        dtlsList =(ArrayList) query.list();
        logger.info("dtlsList size is:::::::::"+dtlsList.size());    
		return dtlsList;
	}


	public List getGroupCodes(long groupId)
	{
		Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            String strQuery = "SELECT GG.GROUP_CODE FROM HR_EIS_GROUP_MST GG WHERE GG.GROUP_ID = " + groupId  ;
            Query query = hibSession.createSQLQuery(strQuery.toString());
            list = query.list();
            
            logger.info("get Scheduler list size in  SchedulerDAOImpl:::::::::::::" +list.size());
        return list;
	
	}
	
	public List getEmpLocalName(long empLocalId,long LangId)
	{
		Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            String strQuery = "select mmst.EMP_FNAME,mmst.EMP_MNAME,mmst.EMP_LNAME FROM ORG_EMP_MST mmst where mmst.USER_ID= " + empLocalId + " and mmst.LANG_ID= " + LangId  ;
            Query query = hibSession.createSQLQuery(strQuery.toString());
            list = query.list();
            
            logger.info("get Scheduler list size in  SchedulerDAOImpl:::::::::::::" +list.size());
            logger.info("get Scheduler list size in  SchedulerDAOImpl:::::::::::::" +list.size());
        return list;
	
	}
	
	public long getIfscCode(long bankBranchId)
	{
		Criteria objCrt = null;
        List  list = null;
        long ifscCode =0L;
            Session hibSession = getSession();
            String strQuery = "SELECT  IFSC_CODE FROM HR_EIS_BRANCH_MST where BRANCH_ID = " + bankBranchId  ;
            Query query = hibSession.createSQLQuery(strQuery.toString());
            list = query.list();
            if(list.get(0) !=null)
            {
             ifscCode = Long.parseLong(list.get(0).toString());
            }
            else
            {
            	ifscCode = 0;
            }
            logger.info("get Scheduler list size in  SchedulerDAOImpl:::::::::::::" +list.size());
            logger.info("get Scheduler list size in  SchedulerDAOImpl:::::::::::::" +list.size());
        return ifscCode;
	
	}
	
	
	
}
