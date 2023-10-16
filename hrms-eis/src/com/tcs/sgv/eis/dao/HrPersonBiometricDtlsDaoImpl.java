package com.tcs.sgv.eis.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class HrPersonBiometricDtlsDaoImpl extends GenericDaoHibernateImpl implements HrPersonBiometricDtlsDao{
	public HrPersonBiometricDtlsDaoImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
}
