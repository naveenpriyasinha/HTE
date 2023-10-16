
package com.tcs.sgv.pdpla.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallan;


public class PdPlaChallanListDAOImpl  extends GenericDaoHibernateImpl<TrnPdChallan,Long> implements PdPlaChallanListDAO
{
public PdPlaChallanListDAOImpl(Class<TrnPdChallan> type, SessionFactory sessionFactory) 
    {
		super(type);
		setSessionFactory(sessionFactory);
	}
	
public ArrayList ListShow() 
{
	try
	
	{
		
		Session hibSession = getSession();
		Query q = hibSession.createSQLQuery("SELECT PAYEE_NM,CHALLAN_NO,CHALLAN_DATE,PD_MJRHD,AMOUNT FROM TRN_PD_CHALLAN WHERE STATUS=0");
		ArrayList queryList = (ArrayList)q.list();
		//System.out.println("Size: " + queryList.size());
		//System.out.println("aftr createSQLQuery"+queryList);
		return queryList;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
  
}

	



