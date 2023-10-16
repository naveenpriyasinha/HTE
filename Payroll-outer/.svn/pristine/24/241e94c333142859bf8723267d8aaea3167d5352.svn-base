package com.tcs.sgv.pension.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionerSeenDtls;
import java.util.ArrayList;
import java.util.List;

public class TrnPensionerSeenDtlsDaoImpl extends GenericDaoHibernateImpl<TrnPensionerSeenDtls, Long> implements
TrnPensionerSeenDtlsDao
{
    public TrnPensionerSeenDtlsDaoImpl(Class<TrnPensionerSeenDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
    public Long getTrnSeenDtlsPk(String PensionerCode,long langId) throws Exception 
    {
        
        List arrPKDtls = new ArrayList();
        StringBuilder strQuery = new StringBuilder();
        Long lBgDcmlPk = null;
        try
        {
            Session hiSession = getSession();
            strQuery.append(" SELECT seenDtlsId ");
            strQuery.append(" FROM TrnPensionerSeenDtls ");
            strQuery.append(" WHERE pensionerCode = :pensoinerCode ");
           
            Query hqlQuery = hiSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("pensoinerCode", PensionerCode);
           
            arrPKDtls  = hqlQuery.list();
            if(arrPKDtls != null && ! arrPKDtls.isEmpty())
            {
                lBgDcmlPk = (Long) arrPKDtls.get(0);
            }
        }
        catch(Exception e)
        {
            throw(e);
        }
        return lBgDcmlPk;
    }
}
