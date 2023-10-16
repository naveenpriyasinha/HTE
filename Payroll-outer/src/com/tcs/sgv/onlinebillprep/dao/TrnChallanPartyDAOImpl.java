package com.tcs.sgv.onlinebillprep.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnChallanParty;

/**
 * Data Access Object for Insert, Update, Delete on TrnMedblAprvdDtls VO
 * 
 * @param type
 * @param sessionFactory
 */
public class TrnChallanPartyDAOImpl extends
		GenericDaoHibernateImpl<TrnChallanParty, Long> implements
		TrnChallanPartyDAO {

	public TrnChallanPartyDAOImpl(Class<TrnChallanParty> type,
			SessionFactory sessionFactory) {

		super(type);
		setSessionFactory(sessionFactory);
	}
}
