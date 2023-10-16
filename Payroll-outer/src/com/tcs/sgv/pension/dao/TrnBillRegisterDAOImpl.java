package com.tcs.sgv.pension.dao;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class TrnBillRegisterDAOImpl extends GenericDaoHibernateImpl<TrnBillRegister, Long> implements TrnBillRegisterDAO
{	
    Log gLogger = LogFactory.getLog(getClass());
    
	public TrnBillRegisterDAOImpl(Class<TrnBillRegister> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public Date getBillDateForBillNo(long billNo) throws Exception
	{
		Date myDate = null;
		Query lQuery;
		List lLst;
	    try
	    {			
	    	StringBuffer query = new StringBuffer();
			Session hibSession = getSession();
		    
		    query.append(" SELECT billDate FROM TrnBillRegister " +
		    		"WHERE billNo = :billNo"); 
		    
		    lQuery = hibSession.createQuery(query.toString());		    
		    lQuery.setParameter("billNo",billNo);		    
		    lLst = lQuery.list();
		    
		    if(lLst != null && lLst.size() > 0)
            {
		    	myDate = (Date)lLst.get(0);
            }
	    }
		catch(Exception e)
		{
			gLogger.error("Error is : " + e,e);
			throw(e);
		}
	    return myDate;
	}
}
