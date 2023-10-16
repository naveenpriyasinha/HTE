package com.tcs.sgv.pension.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;

public class RltPensioncaseBillDAOImpl extends GenericDaoHibernateImpl<RltPensioncaseBill, Long> implements RltPensioncaseBillDAO
{
    Log gLogger = LogFactory.getLog(getClass());
    
	public RltPensioncaseBillDAOImpl(Class<RltPensioncaseBill> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getPKForRltPensioncaseBill(String lStrPPONo,String lStrBillType,String lStrStatus) throws Exception
	{
		List lLstPK = null;
		StringBuffer lSBQuery = new StringBuffer();
				
		try 
		{	
            Session ghibSession = getSession();
            lSBQuery.append(" select rltPensioncaseBillId ");
			lSBQuery.append(" from RltPensioncaseBill ");
			lSBQuery.append(" where ppoNo = :PPONo AND billType = :billType AND status = :status ");
			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());	
			
			lQuery.setParameter("PPONo", lStrPPONo);
			lQuery.setParameter("billType", lStrBillType);
			lQuery.setParameter("status", lStrStatus);
			
			lLstPK = lQuery.list();
		} 
		catch (Exception e) 
		{
        	gLogger.error("Error is : " + e,e);
        	throw(e);
		}
		return lLstPK;
	}
}
