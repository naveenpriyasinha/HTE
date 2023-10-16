package com.tcs.sgv.stamp.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.TrnStampRegesterDtls;

public class TrnStampRegisterDtlsDAOImpl extends GenericDaoHibernateImpl<TrnStampRegesterDtls,Long>
{
	public TrnStampRegisterDtlsDAOImpl(Class<TrnStampRegesterDtls> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	
}
