package com.tcs.sgv.pension.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;

public class TrnPensionMedRembrsmntDAOImpl extends GenericDaoHibernateImpl<TrnPensionMedRembrsmnt, Long>  implements TrnPensionMedRembrsmntDAO
{
    Log gLogger = LogFactory.getLog(getClass());
    
    public TrnPensionMedRembrsmntDAOImpl(Class<TrnPensionMedRembrsmnt> type,SessionFactory sessionFactory)
    {
    	super(type);        
        setSessionFactory(sessionFactory);
    }    
	
    public TrnPensionMedRembrsmnt getTrnPensionMedRembrsmntVO(long lLngBillhdrid) throws Exception
    {
    	StringBuffer lSBQuery = new StringBuffer(400);
    	
    	TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
    	
    	List lLstReturn = null;
       	
    	Query lQuery =null;
    	
    	try 
		{
            Session ghibSession = getSession();
            lSBQuery.append("FROM TrnPensionMedRembrsmnt tpmr WHERE tpmr.billHdrId = :BillHdrId ");				
			
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("BillHdrId",lLngBillhdrid);
			
			lLstReturn = lQuery.list();
			
			if (lLstReturn.get(0)!= null && lLstReturn.size() > 0) 
			{
				lObjTrnPensionMedRembrsmnt = (TrnPensionMedRembrsmnt)(lLstReturn.get(0));
			}			
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);  
			throw(e);
		}
		return lObjTrnPensionMedRembrsmnt;
    }
    
    public long getPkForTrnPensionMedRembrsmnt(String lStrPpoNo) throws Exception 
    {
          StringBuilder query = new StringBuilder();
          long lLngPk = 0;
          try
          {
              Session ghibSession = getSession();
              query.append(" SELECT d.medRembrsmntId FROM TrnPensionMedRembrsmnt d ");
              query.append(" WHERE d.ppoNo = :ppoNo ");
              
              Query hqlQuery = ghibSession.createQuery(query.toString());
              hqlQuery.setParameter("ppoNo",lStrPpoNo);
              List list = hqlQuery.list();
              
              if(list != null && list.size()>0)
              {
                  Iterator itr = list.iterator();
                  while(itr.hasNext())
                  {
                	  lLngPk = (Long)(itr.next());
                  }
              }
          }
          catch(Exception e)
          {
        	  gLogger.error("Error is"+e,e);
              throw e;
          }
    	return lLngPk;
    }
}
