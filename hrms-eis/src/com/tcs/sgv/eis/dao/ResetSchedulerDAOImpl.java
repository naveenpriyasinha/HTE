package com.tcs.sgv.eis.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;

public class ResetSchedulerDAOImpl extends GenericDaoHibernateImpl<HrEisOtherDtls, Long> 
{
	public ResetSchedulerDAOImpl(Class<HrEisOtherDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	
 	public boolean resetJobs(Map schMap)
 	{
 		Session hibSession = getSession();
 		Query query = null;
 		long forMonth = Long.valueOf(schMap.get("forMonth").toString());
 		long forYear = Long.valueOf(schMap.get("forYear").toString());
 		long forHour = Long.valueOf(schMap.get("forHour").toString());
 		long forMinute = Long.valueOf(schMap.get("forMinute").toString());
 		long startThread = Long.valueOf(schMap.get("startThread").toString());
 		long endThread = Long.valueOf(schMap.get("endThread").toString());
 		long counterVal = Long.valueOf(schMap.get("counterVal").toString());
 		long day = Long.valueOf(schMap.get("day").toString());
 		String typeOfReset = schMap.get("counterVal").toString();
 		 		
 		StringBuffer strQuery = new StringBuffer();
 		if(!(forMonth ==3 && forYear == 2012)){
 			strQuery.append("insert into HR_PAY_PAYBILL_SCHEDULER( ");
 			strQuery.append(" select (nvl((select max(shedular_id) from HR_PAY_PAYBILL_SCHEDULER),0) + row_number() over()) ");
 			strQuery.append(" ,billMst.DDO_CODE,billMst.BILL_GROUP_ID,:DAY,'1','1','1','1',sysdate,'1','1',null,'1',billMst.LOC_ID, ");
 			strQuery.append("nextval FOR THREAD_SEQ_SCHEDULER, ");
 			strQuery.append(forMonth);
 			strQuery.append(",");
 			strQuery.append(forYear);
 			strQuery.append(" from (SELECT a.DDO_CODE,a.LOCATION_CODE LOC_ID,b.BILLGROUP_ID BILL_GROUP_ID FROM org_ddo_mst a inner join mst_dcps_emp b on b.DDO_CODE=a.DDO_CODE where b.BILLGROUP_ID is not null group by a.DDO_CODE,a.LOCATION_CODE,b.BILLGROUP_ID) billMst ");
 			strQuery.append(" inner join PAYBILL_HEAD_MPG head on billMst.BILL_GROUP_ID = head.BILL_NO and head.PAYBILL_MONTH =");
 			if(forMonth != 1 )
 				strQuery.append(forMonth-1);
 			else
 				strQuery.append(12);
 			strQuery.append(" and head.PAYBILL_YEAR=");
 			strQuery.append(forYear);
 			strQuery.append(" and head.APPROVE_FLAG =1 )");
 		}else{
 			strQuery.append("insert into HR_PAY_PAYBILL_SCHEDULER( ");
 			strQuery.append(" select (nvl((select max(shedular_id) from HR_PAY_PAYBILL_SCHEDULER),0) + row_number() over()) ");
 			strQuery.append(" ,billMst.DDO_CODE,billMst.BILL_GROUP_ID,:DAY,'1','1','1','1',sysdate,'1','1',null,'1',billMst.LOC_ID, ");
 			strQuery.append("nextval FOR THREAD_SEQ_SCHEDULER, ");
 			strQuery.append(forMonth);
 			strQuery.append(",");
 			strQuery.append(forYear);
 			strQuery.append(" from (SELECT a.DDO_CODE,a.LOCATION_CODE LOC_ID,b.BILLGROUP_ID BILL_GROUP_ID FROM org_ddo_mst a inner join mst_dcps_emp b on b.DDO_CODE=a.DDO_CODE where b.BILLGROUP_ID is not null group by a.DDO_CODE,a.LOCATION_CODE,b.BILLGROUP_ID) billMst ");
 			strQuery.append(" left outer join PAYBILL_HEAD_MPG head on billMst.BILL_GROUP_ID = head.BILL_NO and head.PAYBILL_MONTH =");
			strQuery.append(forMonth);
 			strQuery.append(" and head.PAYBILL_YEAR=");
 			strQuery.append(forYear);
 			strQuery.append(" and head.APPROVE_FLAG in (0,1) where head.id is null )");
 			
 		}
 		logger.info("Query for get getBillNos is---->>>>"+strQuery.toString());
 		query = hibSession.createSQLQuery(strQuery.toString());	
 		logger.info("Query for get getBillNos is---->>>>"+query.toString());
 		query.setParameter("DAY",day);
 		//create query
 				
 		int count = query.executeUpdate();
		//execute query
 		if(count > 0)
 			return true;
 		else 
 			return false;
	  
 	}//end method
 	public boolean resetTimerAndCounter(Map schMap)
 	{
 		long forHour = Long.valueOf(schMap.get("forHour").toString());
 		long forMinute = Long.valueOf(schMap.get("forMinute").toString());
 		long startThread = Long.valueOf(schMap.get("startThread").toString());
 		long endThread = Long.valueOf(schMap.get("endThread").toString());
 		long counterVal = Long.valueOf(schMap.get("counterVal").toString());
 		String typeOfReset = schMap.get("counterVal").toString();
 		Session hibSession = getSession();
 		Query query = null;

 		StringBuffer strQuery = new StringBuffer();
 		strQuery.append("update HR_PAY_PAYBILLSCHDLR_DTLS set counter = :counter,START_THREAD = :startThread,END_THREAD = :endThread,THREAD_RANGE = :threadRange,CRON_TIME = :cronTime");
 		logger.info("Query for get getBillNos is---->>>>"+strQuery.toString());

 		//create query
 		query = hibSession.createSQLQuery(strQuery.toString());			
 		query.setParameter("counter", counterVal);
 		query.setParameter("startThread", startThread);
 		query.setParameter("endThread", endThread);
 		query.setParameter("threadRange", (endThread-startThread)+1);
 		query.setParameter("cronTime", forMinute+"~"+forHour);
		//execute query
		int count = query.executeUpdate();
		if(count > 0)
			return true;
		else
			return false;
 	}//end method
 	public void truncateForMonthYear(Map schMap)
 	{
 		long forMonth = Long.valueOf(schMap.get("forMonth").toString());
 		long forYear = Long.valueOf(schMap.get("forYear").toString());
 		Session hibSession = getSession();
 		Query query = null;

 		StringBuffer strQuery = new StringBuffer();
 		strQuery.append("delete from HR_PAY_PAYBILL_SCHEDULER where month = :month and year = :year");
 		logger.info("Query for get getBillNos is---->>>>"+strQuery.toString());

 		//create query
 		query = hibSession.createSQLQuery(strQuery.toString());		
 		query.setParameter("month", forMonth);
 		query.setParameter("year", forYear);
 		
 		query.executeUpdate();
 		
 	}//end method
 	

}
