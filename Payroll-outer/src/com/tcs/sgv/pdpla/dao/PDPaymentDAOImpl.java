package com.tcs.sgv.pdpla.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChqDetail;


public class PDPaymentDAOImpl extends GenericDaoHibernateImpl<TrnPdChqDetail,Long> implements PDPaymentDAO
{
	Log gLogger = LogFactory.getLog(getClass());
	//Session ghibSession = null;
	
	public PDPaymentDAOImpl(Class<TrnPdChqDetail> type, SessionFactory sessionFactory)
    {
		super(type);
        setSessionFactory(sessionFactory);
    }
}
