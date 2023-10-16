package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionCaseMvmnt;

public class TrnPensionCaseMvmntDAOImpl extends GenericDaoHibernateImpl<TrnPensionCaseMvmnt, Long>
{

    private Log logger = LogFactory.getLog(getClass());
    public TrnPensionCaseMvmntDAOImpl(Class<TrnPensionCaseMvmnt> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }


    public List<TrnPensionCaseMvmnt> getCaseMvmntDtlsByStatusAndPPONo(String lStrPPONo, String lStrStatus)
    {
        List<TrnPensionCaseMvmnt> lLstCaseMvmnt = null;
        Session ghibSession = getSession();
        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" FROM  TrnPensionCaseMvmnt H WHERE H.ppoNo =:ppoNo AND H.status =:status ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("ppoNo", lStrPPONo);
            lQuery.setParameter("status", lStrStatus);

            List lLstVO = lQuery.list();

            if (lLstVO != null && lLstVO.size() > 0)
            {
                lLstCaseMvmnt = new ArrayList<TrnPensionCaseMvmnt>();
                for (int i = 0; i < lLstVO.size(); i++)
                {
                    lLstCaseMvmnt.add((TrnPensionCaseMvmnt) lLstVO.get(i));
                }
            }
           
        }
        catch (Exception e)
        {
            logger.error("Error is :"+e,e);
        }
        return lLstCaseMvmnt;
    }
}
