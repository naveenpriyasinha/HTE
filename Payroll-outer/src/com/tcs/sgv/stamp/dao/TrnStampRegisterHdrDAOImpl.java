package com.tcs.sgv.stamp.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.TrnStampRegisterHdr;

public class TrnStampRegisterHdrDAOImpl extends GenericDaoHibernateImpl<TrnStampRegisterHdr,Long>
{
	public TrnStampRegisterHdrDAOImpl(Class<TrnStampRegisterHdr> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}


}
