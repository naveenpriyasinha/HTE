package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionCaseOutwrd;

public class TrnPensionCaseOutwrdDAO extends GenericDaoHibernateImpl<TrnPensionCaseOutwrd, Long>
{
	public TrnPensionCaseOutwrdDAO(Class<TrnPensionCaseOutwrd> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public ArrayList<TrnPensionCaseOutwrd> getPensionCaseOutwardDtls(String lStrPPONo) throws Exception
	{
		Session hiSession = getSession();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        ArrayList<TrnPensionCaseOutwrd> lLstRes = new ArrayList<TrnPensionCaseOutwrd>();
        Iterator itr;
        try
        {
            strQuery.append(" FROM TrnPensionCaseOutwrd ");
            strQuery.append(" WHERE ppoNo =:ppoNo order by outwrdDate,inwrdDate ");

            Query hqlQuery = hiSession.createQuery(strQuery.toString());
            hqlQuery.setString("ppoNo", lStrPPONo.trim());
            resultList = hqlQuery.list();
			if(resultList != null && resultList.size() > 0)
			{
				itr = resultList.iterator();
				while(itr.hasNext())
				{
					TrnPensionCaseOutwrd tuple = null;
					tuple = (TrnPensionCaseOutwrd)itr.next();
					lLstRes.add((TrnPensionCaseOutwrd)tuple);
				}
			}
            return lLstRes;
        }
        catch (Exception e)
        {
            throw e;
        }
	}
}
