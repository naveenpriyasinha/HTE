package com.tcs.sgv.eis.zp.ReportingDDO.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ConsolidatedBillMpgDaoImpl extends GenericDaoHibernateImpl implements ConsolidatedBillMpgDao
{
	Session hibSession=null;
	public ConsolidatedBillMpgDaoImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}
	
	
	
	public List getGeneratedIds(String paybillId){
		List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- getGeneratedIds---");
    	sb.append("SELECT PAYBILL_ID,CONS_BILL_ID from CONSOLIDATED_BILL_MPG");//SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
    	sb.append(" where paybill_Id in("+paybillId);
    	sb.append(") and status in(0,1,2,3)");
    	logger.info("---- getBillDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;	
	}
	
	public int getStatusOfBill(String billNo,String month,String  year){
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- isDeleteable---");
    	sb.append("SELECT status from CONSOLIDATED_BILL_MPG");//SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
    	sb.append(" where paybill_Id = (SELECT paybill_id FROM PAYBILL_HEAD_MPG where BILL_NO="+billNo+" and  PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year+" and APPROVE_FLAG=0) and status=1");
    	//sb.append(" ");
    	logger.info("---- getBillDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	
    	Object obj=query.uniqueResult();
    	logger.info("---- Object DAo---"+obj);
    	String lstrStatus=(obj!=null)?obj.toString():"";
    	logger.info("---- lstrStatus DAo---"+lstrStatus);
    	int status=(lstrStatus!=null && !lstrStatus.equals(""))?Integer.parseInt(lstrStatus):0;
    	logger.info("---- status DAo---"+status);
    	return status;
    			
	}
	public int isDeleteable(String billNo,String month,String  year){
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- isDeleteable---");
    	sb.append("SELECT status from CONSOLIDATED_BILL_MPG");//SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
    	sb.append(" where paybill_Id in (SELECT paybill_id FROM PAYBILL_HEAD_MPG where BILL_NO="+billNo+" and  PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year+" and APPROVE_FLAG=0) and status>-1");
    	
    	//sb.append(" ");
    	logger.info("---- isDeleteable DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	
    	Object obj=query.uniqueResult();
    	String lstrStatus=(obj!=null)?obj.toString():"";
    	logger.info("---- lstrStatus DAo---"+lstrStatus);
    	int status=(lstrStatus==null || lstrStatus.equals("") || lstrStatus.equals("-1") )?1:0;
    	logger.info("---- status DAo---"+status);
    	return status;
    			
	}
	
	
	

    
    
}
