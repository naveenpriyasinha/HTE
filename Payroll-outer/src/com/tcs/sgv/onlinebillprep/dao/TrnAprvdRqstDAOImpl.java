package com.tcs.sgv.onlinebillprep.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnAprvdRqst;

/**
 * Data Access Object for Insert, Update, Delete on TrnMedblAprvdDtls VO
 * 
 * @param type
 * @param sessionFactory
 */
public class TrnAprvdRqstDAOImpl extends
		GenericDaoHibernateImpl<TrnAprvdRqst, Long> implements TrnAprvdRqstDAO {
	public TrnAprvdRqstDAOImpl(Class<TrnAprvdRqst> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}
}
