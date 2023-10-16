package com.tcs.sgv.dss.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dss.valueobject.RptPaymentDtls;

public class DSSRptPaymentDtlsDAOImpl extends GenericDaoHibernateImpl<RptPaymentDtls,BigDecimal> implements DssDataServiceDAO
{
	public  DSSRptPaymentDtlsDAOImpl(Class<RptPaymentDtls> type, SessionFactory sessionFac)
	{
		super(type);
		setSessionFactory(sessionFac);
	}
	
	
	Log logger = LogFactory.getLog(getClass()); 
	String voname = "-";
	
	public HashMap insertVO(RptPaymentDtls lObjRptPaymentDtls)
	{
		HashMap returnMap = new HashMap();
		boolean flag  = false;
		String reason = "inserted succesfully";
		
		try
		{
			logger.info("---------------------------------insertVO function of DssRptPaymentDtlsDAOImpl starts ---------------------");
			
			if(lObjRptPaymentDtls.getFinYrId().equals(new BigDecimal(0)))
				lObjRptPaymentDtls.setFinYrId(getFinYrId());	
			
			lObjRptPaymentDtls.toString();
			
			Session session = getSession();
			session.persist(lObjRptPaymentDtls);
			flag = true;
			
			
					
		}catch(Exception e)
		{
			logger.error("This is Error: -", e);
			reason = e.toString();
			voname = this.toString();
		}
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", voname);
		logger.info("---------------------------------insertVO function of DssRptPaymentDtlsDAOImpl ends ---------------------");
		return returnMap;
	}



	public HashMap deleteVo(BigDecimal Chq_no,String chq_Type_code)
	{
		HashMap returnMap = new HashMap();
		String reason = "";
		boolean flag = false;
		String query = null;
		Session session = null;
		Connection lcon = null;
		Statement stmt = null;
		int i = 0;
		
		try
		{
			logger.info("-----------------------deleteVO function of DssRptPaymentDTlsDAOImpl starts--------------------");
		
 			query = "update rpt_payment_dtls p set p.active = 'N' where p.payment_id = "+ 
 						   "(select payment_id  from rpt_payment_dtls  where chq_no = "+ Chq_no +
 						   " and  chq_type_code = '"+chq_Type_code+"')";
 			               
 			session = getSession();
 			lcon = (Connection)session.connection();
 			stmt = lcon.createStatement();
 			 
 			 
 			i = stmt.executeUpdate(query);
 			reason = "deleted successfully";
 			flag = true;
		}
		catch(Exception e)
		{
			flag = false;
			reason = e.toString();
			logger.error("This is Error: -", e);
		}
		
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", "RptPaymentDtls");
		logger.info("-----------------------deleteVO function of DssRptPaymentDTlsDAOImpl ends--------------------");
		return returnMap;
	}

	
	public HashMap updateVo(RptPaymentDtls lObjRptPaymentDtls)
	{
		HashMap returnMap = new HashMap();
		String reason = "";
		boolean flag = false;
		try
		{	
			lObjRptPaymentDtls.toString();
			
 			Session session = getSession();
 			session.update(lObjRptPaymentDtls);
 			reason = "updated successfully"; 			 					  
 			flag = true;
		}
		catch(Exception e)
		{
			flag = false;
			reason = e.toString();
			logger.error("This is Error: -", e);
		}
		
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", "RptPaymentDtls"); 
		return returnMap;
		
		
	}



	public RptPaymentDtls getData(BigDecimal CHQ_NO , String CHQ_TYPE_CODE)
	{
		
		RptPaymentDtls lObjRptPaymentDtls = null;
		Session session = null;
		String lStrquery = null;
		SQLQuery query = null;
		List list = null;
		Iterator literator =  null;
		Object lObjExpId = null;
		
		
		try
		{
			logger.info("-------------------getData Function of DssRptPaymentDtlsSAOImpl ends--------------------------");
			
			session = getSession();
			lStrquery = "select PAYMENT_ID "+
	 			               "from rpt_payment_dtls "+
	 			               "where CHQ_NO = "+CHQ_NO +" and CHQ_TYPE_CODE = '"+CHQ_TYPE_CODE+"'";
			
			logger.info("The Query :-> " + lStrquery);
			
			query = session.createSQLQuery(lStrquery);
			list = query.list();
			literator = list.iterator();
			lObjExpId = literator.next();
			
			lObjRptPaymentDtls = read(new BigDecimal(lObjExpId.toString()));
		
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
		}
		
		logger.info("-------------------getData Function of DssRptPaymentDtlsSAOImpl ends--------------------------");
		return lObjRptPaymentDtls;
		
	}


	public BigDecimal getFinYrId()
	{
		try
		{
			logger.info("-------------------------getFinYrId Function of DssRptPaymentDtlsDAOImpl starts--------------------");
			
				String lStrquery = "select mst.fin_year_id from sgvc_fin_year_mst mst "+
						"where sysdate between mst.from_date and mst.to_date ";
				
				logger.info("The query is :- " + lStrquery);
				BigDecimal lFinYrIdReturn = new BigDecimal(0);
				Session session = getSession();
				SQLQuery query = session.createSQLQuery(lStrquery);
				List list = query.list();
				Iterator literator = list.iterator();
				if(literator.hasNext())
				{
					Object obj = (Object) literator.next();
					lFinYrIdReturn = new BigDecimal(obj.toString());
				}
				
				logger.info("-------------------------getFinYrId Function of DssRptPaymentDtlsDAOImpl ends--------------------");
				return lFinYrIdReturn;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			return null;
		}
	}
	





	
}
