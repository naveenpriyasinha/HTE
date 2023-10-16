package com.tcs.sgv.onlinebillprep.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablTrvlDtls;

/**
 * Data Access Object for Insert, Update, Delete on TrnMedblAprvdDtls VO
 * 
 * @param type
 * @param sessionFactory
 */
public class TrnTablTrvlDtlsDAOImpl extends
		GenericDaoHibernateImpl<TrnTablTrvlDtls, Long> implements
		TrnTablTrvlDtlsDAO {

	public TrnTablTrvlDtlsDAOImpl(Class<TrnTablTrvlDtls> type,
			SessionFactory sessionFactory) {

		super(type);
		setSessionFactory(sessionFactory);
	}
}
