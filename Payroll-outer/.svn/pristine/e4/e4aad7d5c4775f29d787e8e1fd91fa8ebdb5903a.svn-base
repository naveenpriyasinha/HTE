package com.tcs.sgv.onlinebillprep.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablRqst;

/**
 * Data Access Object for Insert, Update, Delete on TrnMedblAprvdDtls VO
 * 
 * @param type
 * @param sessionFactory
 */
public class TrnTablRqstDAOImpl extends
		GenericDaoHibernateImpl<TrnTablRqst, Long> implements TrnTablRqstDAO {
	Log gLogger = LogFactory.getLog(getClass());

	public TrnTablRqstDAOImpl(Class<TrnTablRqst> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}
}
