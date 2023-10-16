package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionRevalidationDtls;

public class TrnPensionRevalidationDtlsDao  extends GenericDaoHibernateImpl<TrnPensionRevalidationDtls, Long>
{

	public TrnPensionRevalidationDtlsDao(Class<TrnPensionRevalidationDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	 
	  public ArrayList<TrnPensionRevalidationDtls> getRevalidationVosByRqstId(String lStrPPONo) throws Exception
	  {
		    Session hiSession = getSession();
	        StringBuilder strQuery = new StringBuilder();
	        List resultList;
	        ArrayList<TrnPensionRevalidationDtls> lLstRes = new ArrayList<TrnPensionRevalidationDtls>();
	        Iterator itr;
	        try
	        {
	            strQuery.append(" FROM TrnPensionRevalidationDtls ");
	            strQuery.append(" WHERE ppoNo = '" + lStrPPONo +"' ORDER BY revalidCount ASC ");

	            Query hqlQuery = hiSession.createQuery(strQuery.toString());
	            resultList = hqlQuery.list();
				if(resultList != null && resultList.size() > 0)
				{
					itr = resultList.iterator();
					while(itr.hasNext())
					{
						TrnPensionRevalidationDtls tuple = null;
						tuple = (TrnPensionRevalidationDtls)itr.next();
						lLstRes.add((TrnPensionRevalidationDtls)tuple);
					}
				}
	            return lLstRes;
	        }
	        catch (Exception e)
	        {
	            throw e;
	        }
		}
	  public List getPkListForCaseRevalidationdDtls(String  lStrPPONO) throws Exception
	    {
	        Session hiSession = getSession();
	        StringBuilder strQuery = new StringBuilder();
	        List resultList;
	        List lLstRes = new ArrayList();
	        Iterator itr;
	        try
	        {
	            strQuery.append(" SELECT revalidatonDate ");
	            strQuery.append(" FROM TrnPensionRevalidationDtls ");
	            strQuery.append(" WHERE ppoNo = '" + lStrPPONO +"'");
	            strQuery.append(" AND revalidCount = (SELECT MAX(revalidCount) FROM TrnPensionRevalidationDtls TPR WHERE TPR.ppoNo = '" + lStrPPONO +"' )");

	            Query hqlQuery = hiSession.createQuery(strQuery.toString());
	            resultList = hqlQuery.list();
	            if(resultList != null && resultList.size() > 0)
	            {
	                itr = resultList.iterator();
	                while(itr.hasNext())
	                {
	                    Object tuple = null;
	                    tuple = (Object)itr.next();
	                    lLstRes.add(tuple);
	                }
	            }
	            return lLstRes;
	        }
	        catch (Exception e)
	        {
	            logger.error("Error is" + e, e);
	            throw e;
	        }
	    }
}
