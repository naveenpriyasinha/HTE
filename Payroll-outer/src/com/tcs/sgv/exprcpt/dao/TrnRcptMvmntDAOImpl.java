package com.tcs.sgv.exprcpt.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.TrnRcptMvmnt;


public class TrnRcptMvmntDAOImpl extends GenericDaoHibernateImpl<TrnRcptMvmnt, Long> //implements ReceiptDetailsDAO
{
	public TrnRcptMvmntDAOImpl(Class<TrnRcptMvmnt> type, SessionFactory sessionFactory) 
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
}
