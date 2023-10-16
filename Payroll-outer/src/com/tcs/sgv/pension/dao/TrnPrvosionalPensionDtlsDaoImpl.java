package com.tcs.sgv.pension.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPrvosionalPensionDtls;

public class TrnPrvosionalPensionDtlsDaoImpl extends GenericDaoHibernateImpl<TrnPrvosionalPensionDtls, Long> implements TrnPrvosionalPensionDtlsDao
{
    /* Global Variable for Session Class */
    
    public TrnPrvosionalPensionDtlsDaoImpl(Class<TrnPrvosionalPensionDtls> type,SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
    
    public Long getPrvosionalPensionDtlsIdByPenCode(String pensionerCode) throws Exception
    {
        Long prvisionalpnsnId = null;
        StringBuilder query = new StringBuilder();
        try
        {
            Session ghibSession = getSession();
            query.append(" SELECT d.prvosionalPensionDtlsId from TrnPrvosionalPensionDtls d ");
            query.append(" WHERE d.pensionerCode = :pensionerCode ");
            
            Query hqlQuery = ghibSession.createQuery(query.toString());
            
            hqlQuery.setParameter("pensionerCode", pensionerCode.toString());
            
            List list = hqlQuery.list();
            if(list != null && list.size()>0)
            {
                Iterator itr = list.iterator();
                while(itr.hasNext())
                {
                    prvisionalpnsnId = (Long)(itr.next());
                }
                
            }
        }
        catch(Exception e)
        {
            throw e;
        }
        return prvisionalpnsnId;
    }
}
