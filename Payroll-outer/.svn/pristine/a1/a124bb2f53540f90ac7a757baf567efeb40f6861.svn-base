package com.tcs.sgv.common.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.RltBillParty;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class RltBillPartyDAOImpl extends GenericDaoHibernateImpl<RltBillParty,Long> implements RltBillPartyDAO
{
    private Log gLogger = LogFactory.getLog(getClass());
    
    public RltBillPartyDAOImpl(Class<RltBillParty> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
    
    public List<RltBillParty> getPartyByBill(Long lLngBillNo, SessionFactory sessionFactory)
    {
        List<RltBillParty> lLstBillParty = null;
        StringBuilder lSbQuery = new StringBuilder();
        
        Session hibSession = sessionFactory.getCurrentSession();
        
        try
        {
            lSbQuery.append("from RltBillParty where billNo = :billno");
            
            Query lQuery = hibSession.createQuery(lSbQuery.toString());            
            lQuery.setParameter("billno",lLngBillNo);
            
            gLogger.info("Query for getPartyByBill is : " + lSbQuery.toString());
            
            lLstBillParty = lQuery.list();
        }
        catch(Exception e)
        {
            gLogger.error("Error in getPartyByBill. Error is : " + e,e);
        }
        
        return lLstBillParty;
    }
}
