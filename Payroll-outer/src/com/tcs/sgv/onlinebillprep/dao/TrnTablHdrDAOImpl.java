package com.tcs.sgv.onlinebillprep.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablHdr;

/**
 * Data Access Object for Insert, Update, Delete on TrnMedblAprvdDtls VO
 * 
 * @param type
 * @param sessionFactory
 */
public class TrnTablHdrDAOImpl extends
		GenericDaoHibernateImpl<TrnTablHdr, Long> implements TrnTablHdrDAO {

	public TrnTablHdrDAOImpl(Class<TrnTablHdr> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}
}
