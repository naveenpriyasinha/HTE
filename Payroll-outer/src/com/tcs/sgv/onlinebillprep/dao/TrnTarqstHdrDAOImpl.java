package com.tcs.sgv.onlinebillprep.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTarqstHdr;

/**
 * Data Access Object for Insert, Update, Delete on TrnMedblAprvdDtls VO
 * 
 * @param type
 * @param sessionFactory
 */
public class TrnTarqstHdrDAOImpl extends
		GenericDaoHibernateImpl<TrnTarqstHdr, Long> implements TrnTarqstHdrDAO {
	Log gLogger = LogFactory.getLog(getClass());

	public TrnTarqstHdrDAOImpl(Class<TrnTarqstHdr> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}
}