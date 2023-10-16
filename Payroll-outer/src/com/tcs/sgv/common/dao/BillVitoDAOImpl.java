package com.tcs.sgv.common.dao;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.BillVitoRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class BillVitoDAOImpl extends GenericDaoHibernateImpl<BillVitoRegister,Integer> implements BillVitoDAO 
{
	public BillVitoDAOImpl(Class<BillVitoRegister> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
		
	}
	public long getMaxVitoCode(String vitoType)
	{
		//System.out.println(" came in ........ dao for BillVitoDAOImpl");
		Session hibSession = getSession();
		Query sqlQuery = hibSession.createSQLQuery(" SELECT max(vr.vito_code)  FROM bill_vito_register vr where vr.vito_type='"+vitoType+"'");
		Iterator iterator = sqlQuery.list().iterator();
		long maxVitoId = 0;
		while(iterator.hasNext())
		{
			Object obj = iterator.next();
			if(obj != null)
			maxVitoId = Long.parseLong(obj.toString());
		}
		maxVitoId = maxVitoId + 1;
		//System.out.println(" \n Returning ID "  + maxVitoId);
		return maxVitoId;
	}
}
