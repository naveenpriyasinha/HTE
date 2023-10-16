package com.tcs.sgv.dcps.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.dao.DdoWiseExpenditureReportDAO;
import org.hibernate.Query;
 
public class DdoWiseExpenditureReportDAOImpl  extends GenericDaoHibernateImpl  implements DdoWiseExpenditureReportDAO {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger.getLogger(DdoWiseExpenditureReportDAOImpl.class);

	
	public DdoWiseExpenditureReportDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}
	
	public List getDDOWiseExpenditure(String DdoCode,String Region,String year, String month,String flag) {
		Session hibSession = getSession();
		List exenditureDataList = null;
		StringBuilder SBQuery = new StringBuilder();
		try { 
			  System.out.println("String "+DdoCode); 
			SBQuery.append("SELECT distinct Map.ZP_DDO_CODE,DDO.DDO_NAME,BILLGRP.DESCRIPTION,"
					//+ "BILL.BILL_GROSS_AMT ,BILL.BILL_NET_AMOUNT,,BILL.PAYBILL_ID\r\n"  
					//+" CONSMST.GROSS_AMT AS GROSS_AMT_CONSMST, CONSMST.NET_AMT AS NET_TOTAL_CONSMST  FROM \r\n" +
					 + "sum(BILL.BILL_GROSS_AMT),sum(BILL.BILL_NET_AMOUNT) FROM "+ 
					"IFMS.PAYBILL_HEAD_MPG  BILL inner join  ifms.CONSOLIDATED_BILL_MPG CONSMPG on BILL.PAYBILL_ID =CONSMPG.PAYBILL_ID\r\n" + 
					"inner join CONSOLIDATED_BILL_MST as CONSMST on CONSMST.CONS_BILL_ID=CONSMPG.CONS_BILL_ID \r\n" + 
					"INNER JOIN IFMS.ORG_DDO_MST DDO ON BILL.LOC_ID=BIGINT(DDO.LOCATION_CODE) INNER JOIN IFMS.RLT_ZP_DDO_MAP MAP ON MAP.ZP_DDO_CODE=DDO.DDO_CODE\r\n" +
					"inner join ifms.MST_DCPS_BILL_GROUP as  BILLGRP on BILLGRP.BILL_GROUP_ID=BILL.BILL_NO"+
					" WHERE  MAP.REPT_DDO_CODE<>'2222222222' and MAP.status=1 and (DDO.DDO_NAME is not null and  trim(DDO.DDO_NAME)!='') ");
			 
				if(!year.isEmpty() && !year.equals(null) && flag.equals("report") && !year.equals("0") &&  month.equals("0")) {
					int nextYear=Integer.parseInt(year);
					SBQuery.append(" and ((BILL.PAYBILL_YEAR="+year+" and BILL.PAYBILL_MONTH>=4) or (BILL.PAYBILL_YEAR="+(nextYear+1)+" and BILL.PAYBILL_MONTH<=3))"); 
					
				} else if(!year.isEmpty() && !year.equals(null) && !year.equals("0") && flag.equals("report") && !month.equals(null) && !month.equals("0")) {
					SBQuery.append(" and BILL.PAYBILL_YEAR="+year+" "); 
				}
				
				if(!DdoCode.isEmpty() && !DdoCode.equals(null) && flag.equals("report") && !DdoCode.equals("All")  && !DdoCode.equals("0") ) {  
					SBQuery.append(" and MAP.REPT_DDO_CODE='"+DdoCode+"'"); 
					
				}
			
				if(!DdoCode.isEmpty() && !DdoCode.equals(null) && flag.equals("report") && !DdoCode.equals("All") && !DdoCode.equals("0") ) {  
					SBQuery.append(" or MAP.ZP_DDO_CODE='"+DdoCode+"'"); 
					
				}
				
			
				if(!month.isEmpty() && !month.equals(null) && !month.equals("0")) {  
					SBQuery.append(" and BILL.PAYBILL_MONTH="+month+" " );
					
				} 
				SBQuery.append(" and CONSMST.STATUS in(1,5) group by Map.ZP_DDO_CODE,DDO.DDO_NAME,BILLGRP.DESCRIPTION order by Map.ZP_DDO_CODE,DDO.DDO_NAME,BILLGRP.DESCRIPTION");
				logger.info("Query.."+SBQuery.toString());
				Query lQuery = hibSession.createSQLQuery(SBQuery.toString());
			logger.info("Query.."+SBQuery.toString());
				exenditureDataList = lQuery.list();

		} catch (Exception e) {
			logger.error("Error is :" + e, e);

		}
		
		
		return exenditureDataList;
	}

	 public List getSubDDOs(Long postID) {//, String talukaId, String ddoSelected){
	    	List ddoDtls = null;
	    	Session session = getSession();
	    	StringBuffer sb = new StringBuffer();
	    	logger.info("---- getSubDDOs DAO---");
	    	logger.info("postID : "+postID);
	    	String cond="";
	    	if(!postID.toString().equals("200460")) {
	    		cond="and ((rep.REPT_DDO_POST_ID='" + postID + "') or (rep.FINAL_DDO_POST_ID='" + postID + "'))";
	    	}
	    	else
	    	{
	    		cond="";
	    	}
	    	sb.append("SELECT ddo.DDO_office,ddo.ddo_code FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE office where ddo.DDO_OFFICE !='null' and office.status_flag=1 and ddo.DDO_CODE = rep.ZP_DDO_CODE and office.DDO_CODE=ddo.DDO_CODE and rep.status =1 "+cond+" order by ddo.DDO_CODE asc ");
	    	logger.info("---- getSubDDOs DAo---"+sb);
	    	Query query = session.createSQLQuery(sb.toString());
	    	logger.info("output: "+query.list().get(0));
	    	ddoDtls = query.list();
	    	logger.info("list size :"+ddoDtls.size());
	    	return ddoDtls;
	    }

	@Override
	public List getDDOWiseExpenditure(String Region, String year, String month) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
