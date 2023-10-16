package com.tcs.sgv.onlinebillprep.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.onlinebillprep.valueobject.RltBillRqst;

/**
 * Data Access Object for Insert, Update, Delete on TrnMedblAprvdDtls VO
 * 
 * @param type
 * @param sessionFactory
 */

public class RltBillRqstDAOImpl extends
		GenericDaoHibernateImpl<RltBillRqst, Long> implements RltBillRqstDAO {

	public RltBillRqstDAOImpl(Class<RltBillRqst> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

}
