package com.tcs.sgv.eis.zp.ZpReportingDDO.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;
public class ZpReportingDAOImpl extends GenericDaoHibernateImpl implements ZpReportingDAO
{
	public ZpReportingDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getDDOLstpage(long postId,long month,long year)
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("SELECT zp.ZP_DDO_CODE, hd.BILL_NO, bill.DESCRIPTION,hd.BILL_GROSS_AMT, hd.BILL_NET_AMOUNT,hd.PAYBILL_ID FROM RLT_ZP_DDO_MAP zp, PAYBILL_HEAD_MPG hd, MST_DCPS_BILL_GROUP bill");
		query.append(" where zp.ZP_DDO_CODE = bill.DDO_CODE and bill.BILL_GROUP_ID= hd.BILL_NO and hd.APPROVE_FLAG = 0 and hd.PAYBILL_MONTH="+month+" AND HD.PAYBILL_YEAR="+year+" and zp.REPT_DDO_POST_ID =" +postId);


		logger.info("Query for get DDO---->>>>" + query.toString());
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		resList = sqlQuery.list();
		logger.info("the DDO size of resList is ::" + resList.size());
		return resList;
	}
	
	
	public List getSubDDOByPost(Long postId) 
    {     	
    List bill = null;    
 	Session session = getSession();    
 	StringBuffer sb = new StringBuffer();  
   	logger.info("---- getSubDDOByPost DAO---"); 
    sb.append("SELECT zp.zp_ddo_code, ofc.off_name FROM RLT_ZP_DDO_MAP zp, MST_DCPS_DDO_OFFICE ofc where  ofc.ddo_code = zp.ZP_DDO_CODE and zp.status = 1 and zp.REPT_DDO_POST_ID = " +postId); 
    logger.info("---- getSubDDOByPost DAo---"+sb);
    Query query = session.createSQLQuery(sb.toString()); 
    bill = query.list();     
    return bill;
    
	   }
	
	public List getSchemeCodeByPost(Long post) 
    {     	
    List bill = null;    
 	Session session = getSession();    
 	StringBuffer sb = new StringBuffer();  
   	logger.info("---- getSchemeCodeByPost DAO---"); 
    sb.append("SELECT scheme_id,scheme_code,scheme_name FROM MST_SCHEME where scheme_code in(select scheme_code from mst_dcps_bill_group where ddo_code in(select Zp_ddo_code from rlt_zp_DDO_map where rept_DDO_post_id="+post+" ))"); 
    logger.info("---- getSchemeCodeByPost DAo---"+sb);
    Query query = session.createSQLQuery(sb.toString()); 
    bill = query.list();     
    return bill;
    
	   }
	public List getReptDDOBillDtls(String ddocode,long month,long year,String schemeCode,String subSchemecode)
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		//commented by vaibhav tyagi
		//query.append("SELECT  bill.DDO_CODE,hd.BILL_NO, bill.DESCRIPTION,hd.BILL_GROSS_AMT, hd.BILL_NET_AMOUNT,hd.PAYBILL_ID FROM  PAYBILL_HEAD_MPG hd, MST_DCPS_BILL_GROUP bill where bill.DDO_CODE in("+ddocode+") and bill.BILL_GROUP_ID= hd.BILL_NO and hd.APPROVE_FLAG = 0 and hd.PAYBILL_MONTH="+month+" AND HD.PAYBILL_YEAR= "+year+ " and bill.SCHEME_CODE='"+schemeCode+"'");
		//added by vaibhav tyagi: start
		query.append("SELECT  bill.DDO_CODE,hd.BILL_NO, bill.DESCRIPTION,hd.BILL_GROSS_AMT, hd.BILL_NET_AMOUNT,hd.PAYBILL_ID FROM  PAYBILL_HEAD_MPG hd, MST_DCPS_BILL_GROUP bill where bill.DDO_CODE in("+ddocode+") and bill.BILL_GROUP_ID= hd.BILL_NO and hd.APPROVE_FLAG = 0 and hd.PAYBILL_MONTH="+month+" AND HD.PAYBILL_YEAR= "+year+ " and bill.SCHEME_CODE='"+schemeCode+"' and hd.status=2");
		//Added by saurabh for subscheme
				if(!subSchemecode.equals("-") && !subSchemecode.equals("-1") && !subSchemecode.equals(null))
				{
					query.append(" and bill.sub_SCHEME_CODE='"+subSchemecode+"' ");
				}
		
		//added by vaibhav tyagi: end
		logger.info("Query for getReptDDOBillDtls---->>>>" + query.toString());
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		resList = sqlQuery.list();
		logger.info("the DDO size of getReptDDOBillDtls is ::" + resList.size());
		return resList;
	}
	
	
	public List getSubDDOsOffc(Long postId){
    	List ddoDtls = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSubDDOs DAO---");
    	sb.append("SELECT ddo.DDO_CODE ,ddo.DDO_NAME FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and rep.REPT_DDO_POST_ID="+postId);
    	logger.info("---- getSubDDOs DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	ddoDtls = query.list();
    	return ddoDtls;
    }

	public Object getLocID(String ddoCode){
		String locId=null;
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getLocID DAO---");
    	sb.append("SELECT location_code from org_ddo_mst where ddo_code="+ddoCode);
    	logger.info("---- getLocID DAo---"+sb.toString());
    	Query query = session.createSQLQuery(sb.toString());
    	locId = query.uniqueResult().toString();
    	return locId;
    }
	
	public int rejectPaybillByLevelTwo(String billNo, long month, long year) {
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- rejectPaybillByLevelTwo---");
    	sb.append("update PAYBILL_HEAD_MPG set APPROVE_FLAG=2,status=0 where PAYBILL_MONTH="+month+" and PAYBILL_YEAR= "+year+" and PAYBILL_ID ="+billNo+" and status=2");
    	logger.info("---- rejectPaybillByLevelTwo DAo---"+sb.toString());
    	Query query = session.createSQLQuery(sb.toString());
    	return query.executeUpdate();
	}

}
