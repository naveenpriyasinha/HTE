package com.tcs.sgv.eis.zp.ReportingDDO.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ConsolidatedBillLvlMpgDaoImpl extends GenericDaoHibernateImpl implements ConsolidatedBillMpgDao
{

	Session hibSession=null;
	public ConsolidatedBillLvlMpgDaoImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}
	
	public List getApproveList(long postId)
	{
		List temp = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		//logger.info("---- getApproveList---");
		//modified by saurabh
		sb.append("select bill.CONS_BILL_ID,bill.scheme_code,bill.GROSS_AMT,bill.NET_AMT,bill.status,bill.sub_scheme_code from CONSOLIDATED_BILL_MST bill,CONSOLIDATED_BILL_LEVEL_POST_MPG mpg");//SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
		sb.append(" where bill.CONS_BILL_ID=mpg.CONS_BILL_ID and (");
		sb.append("(mpg.LEVEL2_POST_ID="+postId+" and mpg.status=0 and mpg.level=2) or ");
		sb.append("(mpg.LEVEL3_POST_ID="+postId+" and mpg.status=2 and mpg.level=3) or ");
		sb.append("( mpg.LEVEL4_POST_ID="+postId+" and mpg.status=3 and mpg.level=4) )");
		
		logger.info("---- getApproveList DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		temp = query.list();
		logger.info("---- query---"+temp.size());
		return temp;	
	}
	public void approveBill(String billId){
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- approveBill---");
    	sb.append("update CONSOLIDATED_BILL_MST set status=1 where ");
    	sb.append("CONS_BILL_ID="+billId);
    	logger.info("---- approveBill DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	query.executeUpdate();
    	StringBuffer sb2 = new StringBuffer();
    	sb2.append("update CONSOLIDATED_BILL_MPG set status=1 where ");
    	sb2.append("CONS_BILL_ID="+billId);
    	Query query2 = session.createSQLQuery(sb2.toString());
    	query2.executeUpdate();
    	StringBuffer sb3 = new StringBuffer();
    	sb3.append("update CONSOLIDATED_BILL_LEVEL_POST_MPG set status=1 where ");
    	sb3.append("CONS_BILL_ID="+billId);
    	Query query3 = session.createSQLQuery(sb3.toString());
    	query3.executeUpdate();
	}
	

	public List getForwadingList(long postId)
	{
		List temp = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		//logger.info("---- getApproveList---");
		sb.append("select bill.CONS_BILL_ID,bill.scheme_code,bill.GROSS_AMT,bill.NET_AMT,bill.status from CONSOLIDATED_BILL_MST bill,CONSOLIDATED_BILL_LEVEL_POST_MPG mpg");//SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
		sb.append(" where bill.CONS_BILL_ID=mpg.CONS_BILL_ID and (");
		sb.append("( mpg.LEVEL2_POST_ID="+postId+" and mpg.status=0 and mpg.level>2) OR");
		sb.append("( mpg.LEVEL3_POST_ID="+postId+" and mpg.status=2 and mpg.level>3) OR");
		sb.append("(mpg.LEVEL4_POST_ID="+postId+" and mpg.status=3 and mpg.level>4))");
		
		logger.info("---- getApproveList DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		temp = query.list();
		logger.info("---- query---"+temp.size());
		return temp;	
	}
	public void forwardBill(String billId,long status){
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- approveBill---");
    	sb.append("update CONSOLIDATED_BILL_MST set status="+status+" where ");
    	sb.append("CONS_BILL_ID="+billId);
    	logger.info("---- approveBill DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	query.executeUpdate();
    	StringBuffer sb2 = new StringBuffer();
    	sb2.append("update CONSOLIDATED_BILL_MPG set status="+status+" where ");
    	sb2.append("CONS_BILL_ID="+billId);
    	Query query2 = session.createSQLQuery(sb2.toString());
    	query2.executeUpdate();
    	StringBuffer sb3 = new StringBuffer();
    	sb3.append("update CONSOLIDATED_BILL_LEVEL_POST_MPG set status="+status+" where ");
    	sb3.append("CONS_BILL_ID="+billId);
    	Query query3 = session.createSQLQuery(sb3.toString());
    	query3.executeUpdate();
	}
	
	public List getRejectingList(long postId)
	{
		List temp = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		//logger.info("---- getApproveList---");
		sb.append("select bill.CONS_BILL_ID,bill.scheme_code,bill.GROSS_AMT,bill.NET_AMT,bill.status from CONSOLIDATED_BILL_MST bill,CONSOLIDATED_BILL_LEVEL_POST_MPG mpg");//SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
		sb.append(" where bill.CONS_BILL_ID=mpg.CONS_BILL_ID and ( ");
		sb.append("( mpg.LEVEL2_POST_ID="+postId+" and mpg.status=0 and mpg.level>1) OR ");
		sb.append("( mpg.LEVEL3_POST_ID="+postId+" and mpg.status=2 and mpg.level>2) OR ");
		sb.append(" ( mpg.LEVEL4_POST_ID="+postId+" and mpg.statusS=3 and mpg.level>3))");
		
		logger.info("---- getApproveList DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		temp = query.list();
		logger.info("---- query---"+temp.size());
		return temp;	
	}
	public void rejectBill(String billId){
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- approveBill---");
    	sb.append("update CONSOLIDATED_BILL_MST set status=-1 where ");
    	sb.append("CONS_BILL_ID="+billId);
    	logger.info("---- approveBill DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	query.executeUpdate();
    	StringBuffer sb2 = new StringBuffer();
    	sb2.append("update CONSOLIDATED_BILL_MPG set status=-1 where ");
    	sb2.append("CONS_BILL_ID="+billId);
    	Query query2 = session.createSQLQuery(sb2.toString());
    	query2.executeUpdate();
    	StringBuffer sb3 = new StringBuffer();
    	sb3.append("update CONSOLIDATED_BILL_LEVEL_POST_MPG set status=-1 where ");
    	sb3.append("CONS_BILL_ID="+billId);
    	Query query3 = session.createSQLQuery(sb3.toString());
    	query3.executeUpdate();
	}

}
