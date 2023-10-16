package com.tcs.sgv.exprcpt.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.TrnRcptBudheadDtls;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class RcptBudDtlsDAOImpl extends GenericDaoHibernateImpl<TrnRcptBudheadDtls, Long> //implements RcptBudDtlsDAO
{

	public RcptBudDtlsDAOImpl(Class<TrnRcptBudheadDtls> type, SessionFactory sessionFactory) 
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
}
