package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnCaseRevalidationDtls;

public class TrnCaseRevalidationDtlsDAO  extends GenericDaoHibernateImpl<TrnCaseRevalidationDtls, Long>
{

	  public TrnCaseRevalidationDtlsDAO(Class<TrnCaseRevalidationDtls> name, SessionFactory sessionFactory)
     {
		  super(name);
     	  setSessionFactory(sessionFactory);
     }
	 
	  public ArrayList<TrnCaseRevalidationDtls> getRevalidationVosByRqstId(Long lLngRqstHdrId) throws Exception
	  {
		    Session hiSession = getSession();
	        StringBuilder strQuery = new StringBuilder();
	        List resultList;
	        ArrayList<TrnCaseRevalidationDtls> lLstRes = new ArrayList<TrnCaseRevalidationDtls>();
	        Iterator itr;
	        try
	        {
	            strQuery.append(" FROM TrnCaseRevalidationDtls ");
	            strQuery.append(" WHERE pensionRequestId = " + lLngRqstHdrId +" ORDER BY revalidCount ASC ");

	            Query hqlQuery = hiSession.createQuery(strQuery.toString());
	            resultList = hqlQuery.list();
				if(resultList != null && resultList.size() > 0)
				{
					itr = resultList.iterator();
					while(itr.hasNext())
					{
						TrnCaseRevalidationDtls tuple = null;
						tuple = (TrnCaseRevalidationDtls)itr.next();
						lLstRes.add((TrnCaseRevalidationDtls)tuple);
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
