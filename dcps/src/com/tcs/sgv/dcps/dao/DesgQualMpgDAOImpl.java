package com.tcs.sgv.dcps.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.DesgQualMpg;

public class DesgQualMpgDAOImpl extends GenericDaoHibernateImpl{

	public DesgQualMpgDAOImpl(Class<DesgQualMpg> type,SessionFactory sessionFactory)  {
		super(type);
		// TODO Auto-generated constructor stub
		setSessionFactory(sessionFactory);
	}
	

}
