package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

@SuppressWarnings("unchecked")
public class NetMismatchDAOImpl extends GenericDaoHibernateImpl<HrPayPaybill, Long> implements NetMismatchDAO
{
	Log logger = LogFactory.getLog(getClass());
	public NetMismatchDAOImpl(Class<HrPayPaybill> type,SessionFactory sessionFactory) 
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	public List getPayBillColumns(long locId,String allowCmpId, String deducCmpId)
	{
		List resultList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer allowQueryStr = new StringBuffer();
		allowQueryStr.append("SELECT PAYBILL_COLUMN FROM hr_pay_edp_mst where EDP_CODE in "); 
		allowQueryStr.append("( "); 
		allowQueryStr.append("	SELECT EDP_CODE FROM RLT_BILL_TYPE_EDP where TYPE_EDP_ID in "); 
		allowQueryStr.append("	( "); 
		allowQueryStr.append("		SELECT TYPE_EDP_ID FROM HR_PAY_EDP_COMPO_MPG where cmn_lookup_id = 2500134 and TYPE_ID in "); 
		allowQueryStr.append("		( "); 
		allowQueryStr.append("			SELECT allow_code FROM HR_PAY_ALLOW_TYPE_MST where ALLOW_CODE not in "); 
		allowQueryStr.append("			( "); 
		allowQueryStr.append(allowCmpId);			
//		allowQueryStr.append("				SELECT COMPO_ID FROM HR_PAY_LOC_COMPONENT_MPG where COMPO_GRP_ID = "); 
//		allowQueryStr.append("				( "); 
//		allowQueryStr.append("					SELECT COMPO_GROUP_ID FROM HR_PAY_COMPONENT_GRP_MST where LOC_ID = "+locId+" and IS_ACTIVE = 1 "); 
//		allowQueryStr.append("				) and ISACTIVE = 1 and COMPO_TYPE = 2500134 "); 
		allowQueryStr.append("			) "); 
		allowQueryStr.append("		) "); 
		allowQueryStr.append("	) "); 
		allowQueryStr.append(") and exp_rcp_rec = 'EXP' ");
		Query allowQuery = hibSession.createSQLQuery(allowQueryStr.toString());
		resultList = allowQuery.list();
		
		StringBuffer deducQueryStr = new StringBuffer();
		deducQueryStr.append("SELECT PAYBILL_COLUMN FROM hr_pay_edp_mst where EDP_CODE in "); 
		deducQueryStr.append("( "); 
		deducQueryStr.append("	SELECT EDP_CODE FROM RLT_BILL_TYPE_EDP where TYPE_EDP_ID in "); 
		deducQueryStr.append("	( "); 
		deducQueryStr.append("		SELECT TYPE_EDP_ID FROM HR_PAY_EDP_COMPO_MPG where cmn_lookup_id = 2500135 and TYPE_ID in "); 
		deducQueryStr.append("		( "); 
		deducQueryStr.append("			SELECT DEDUC_CODE FROM HR_PAY_DEDUC_TYPE_MST where DEDUC_CODE not in "); 
		deducQueryStr.append("			( ");
		deducQueryStr.append(deducCmpId);
//		deducQueryStr.append("				SELECT COMPO_ID FROM HR_PAY_LOC_COMPONENT_MPG where COMPO_GRP_ID = "); 
//		deducQueryStr.append("				( "); 
//		deducQueryStr.append("					SELECT COMPO_GROUP_ID FROM HR_PAY_COMPONENT_GRP_MST where LOC_ID = "+locId+" and IS_ACTIVE = 1 "); 
//		deducQueryStr.append("				) and ISACTIVE = 1 and COMPO_TYPE = 2500135 "); 
		deducQueryStr.append("			) "); 
		deducQueryStr.append("		) "); 
		deducQueryStr.append("	) "); 
		deducQueryStr.append(") and exp_rcp_rec = 'RCP' ");
		Query deducQuery = hibSession.createSQLQuery(deducQueryStr.toString());
		resultList.addAll(deducQuery.list());
		
		StringBuffer loanPrinQueryStr = new StringBuffer();
		loanPrinQueryStr.append("SELECT PAYBILL_COLUMN FROM hr_pay_edp_mst where EDP_CODE in "); 
		loanPrinQueryStr.append("( "); 
		loanPrinQueryStr.append("	SELECT EDP_CODE FROM RLT_BILL_TYPE_EDP where TYPE_EDP_ID in "); 
		loanPrinQueryStr.append("	( "); 
		loanPrinQueryStr.append("		SELECT TYPE_EDP_ID FROM HR_PAY_EDP_COMPO_MPG where cmn_lookup_id = 2500136 and TYPE_ID in "); 
		loanPrinQueryStr.append("		( "); 
		loanPrinQueryStr.append("			SELECT LOAN_ADV_ID FROM HR_LOAN_ADV_MST where LOAN_ADV_ID not in "); 
		loanPrinQueryStr.append("			( "); 
		loanPrinQueryStr.append("				SELECT COMPO_ID FROM HR_PAY_LOC_COMPONENT_MPG where COMPO_GRP_ID = "); 
		loanPrinQueryStr.append("				( "); 
		loanPrinQueryStr.append("					SELECT COMPO_GROUP_ID FROM HR_PAY_COMPONENT_GRP_MST where LOC_ID = "+locId+" and IS_ACTIVE = 1 "); 
		loanPrinQueryStr.append("				) and ISACTIVE = 1 and COMPO_TYPE = 2500136 "); 
		loanPrinQueryStr.append("			) "); 
		loanPrinQueryStr.append("		) "); 
		loanPrinQueryStr.append("	) "); 
		loanPrinQueryStr.append(") and exp_rcp_rec = 'REC' ");
		Query loanPrinQuery = hibSession.createSQLQuery(loanPrinQueryStr.toString());
		resultList.addAll(loanPrinQuery.list());
		
		StringBuffer loanIntQueryStr = new StringBuffer();
		loanIntQueryStr.append("SELECT PAYBILL_COLUMN FROM hr_pay_edp_mst where EDP_CODE in "); 
		loanIntQueryStr.append("( "); 
		loanIntQueryStr.append("	SELECT EDP_CODE FROM RLT_BILL_TYPE_EDP where TYPE_EDP_ID in "); 
		loanIntQueryStr.append("	( "); 
		loanIntQueryStr.append("		SELECT TYPE_EDP_ID FROM HR_PAY_EDP_COMPO_MPG where cmn_lookup_id = 2500137 and TYPE_ID in "); 
		loanIntQueryStr.append("		( "); 
		loanIntQueryStr.append("			SELECT LOAN_ADV_ID FROM HR_LOAN_ADV_MST where LOAN_ADV_ID not in "); 
		loanIntQueryStr.append("			( "); 
		loanIntQueryStr.append("				SELECT COMPO_ID FROM HR_PAY_LOC_COMPONENT_MPG where COMPO_GRP_ID = "); 
		loanIntQueryStr.append("				( "); 
		loanIntQueryStr.append("					SELECT COMPO_GROUP_ID FROM HR_PAY_COMPONENT_GRP_MST where LOC_ID = "+locId+" and IS_ACTIVE = 1 "); 
		loanIntQueryStr.append("				) and ISACTIVE = 1 and COMPO_TYPE = 2500137 "); 
		loanIntQueryStr.append("			) "); 
		loanIntQueryStr.append("		) "); 
		loanIntQueryStr.append("	) "); 
		loanIntQueryStr.append(") and exp_rcp_rec = 'INT' ");
		Query loanIntQuery = hibSession.createSQLQuery(loanIntQueryStr.toString());
		resultList.addAll(loanIntQuery.list());
		
		logger.info("Result List size is "+resultList.size());
		
		return resultList;
	}

	
	public List getTotalHeadValue(long empId, int month, int year, String paybillColumnListSum,String condition_coloumn_names) 
	{
		logger.info("getTotalHeadValue --> start");
		List dtlsList = new ArrayList();
		Session hibSession = getSession();
		paybillColumnListSum = "emp.EMP_LNAME||' '||emp.EMP_FNAME||' '||emp.EMP_MNAME as name," + paybillColumnListSum;
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("SELECT ");
		strBfr.append(paybillColumnListSum+" ");
		strBfr.append("FROM HR_PAY_PAYBILL pb, PAYBILL_HEAD_MPG hm ,ORG_EMP_MST emp, HR_EIS_EMP_MST eis,ORG_USERPOST_RLT userpost,HR_PAY_POST_PSR_MPG psr ");
		strBfr.append("where pb.PAYBILL_GRP_ID = hm.PAYBILL_ID ");
		strBfr.append("and hm.APPROVE_FLAG in (0,1) ");
		strBfr.append("and hm.BILL_NO = psr.BILL_NO ");
		strBfr.append("and hm.PAYBILL_MONTH = "+month+" ");
		strBfr.append("and hm.PAYBILL_YEAR = "+year+" ");
		strBfr.append("and pb.EMP_ID = eis.EMP_ID ");
		strBfr.append("and eis.EMP_MPG_ID = emp.EMP_ID ");
		strBfr.append("and eis.EMP_ID = "+empId+" ");
		strBfr.append("and userpost.USER_ID = emp.USER_ID ");
		strBfr.append("and psr.POST_ID = userpost.POST_ID ");
		strBfr.append("and "+condition_coloumn_names+" ");
		strBfr.append("order by name ");
		Query query = hibSession.createSQLQuery(strBfr.toString());

		dtlsList = (ArrayList) query.list();
		logger.info("dtlsList size is:::::::::" + dtlsList.size());
		return dtlsList;
	}
	
	public List getAllEmployeeByLocId(long locID)
	{
		logger.info("getAllEmployeeByLocId start");
		Session session = getSession();
		StringBuffer HQL_QUERY = new StringBuffer();
		HQL_QUERY.append("select org.empLname||' '||org.empFname||' '||org.empMname as name,eis.empId from HrEisEmpMst eis, OrgEmpMst org, OrgUserpostRlt userpost, OrgPostMst post ");
		HQL_QUERY.append("where ");
		HQL_QUERY.append("eis.orgEmpMst.empId = org.empId ");
		HQL_QUERY.append("and org.orgUserMst.userId = userpost.orgUserMst.userId ");
		HQL_QUERY.append("and userpost.orgPostMstByPostId.postId = post.postId ");
		HQL_QUERY.append("and post.locationCode = " + locID + " ");
		HQL_QUERY.append("and post.activateFlag = 1 ");
		HQL_QUERY.append("and userpost.activateFlag = 1 ");
		Query query = session.createQuery(HQL_QUERY.toString());
		logger.info("getAllEmployeeByLocId end");
		return query.list();
	}
	
	
	public List getEmpCmpMpgData(long empId,long locID)
	{
		logger.info("getEmpCmpMpgData start");
		Session session = getSession();
		StringBuffer HQL_QUERY = new StringBuffer();
		HQL_QUERY.append("select cmpmpg from HrEisEmpCompGrpMst grp, HrEisEmpMst eis, OrgEmpMst org, OrgUserpostRlt userpost, OrgPostMst post, HrEisEmpCompMpg cmpmpg ");
		HQL_QUERY.append("where grp.hrEisEmpMst.empId = "+empId+" ");
		HQL_QUERY.append("and eis.orgEmpMst.empId = org.empId ");
		HQL_QUERY.append("and org.orgUserMst.userId = userpost.orgUserMst.userId ");
		HQL_QUERY.append("and userpost.orgPostMstByPostId.postId = post.postId ");
		HQL_QUERY.append("and post.locationCode = "+locID+" ");
		HQL_QUERY.append("and post.activateFlag = 1 ");
		HQL_QUERY.append("and grp.isactive = 1 ");
		HQL_QUERY.append("and userpost.activateFlag = 1 ");
		HQL_QUERY.append("and cmpmpg.hrEisEmpCompGrpMst.EmpCompGrpId = grp.EmpCompGrpId ");
		HQL_QUERY.append("and cmpmpg.isactive = 1 ");
		Query query = session.createQuery(HQL_QUERY.toString());
		logger.info("getEmpCmpMpgData end");
		return query.list();
	}
	
	
	
}
