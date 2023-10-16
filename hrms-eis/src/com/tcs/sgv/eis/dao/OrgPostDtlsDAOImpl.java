package com.tcs.sgv.eis.dao;
//Comment By Maruthi For import Organisation.
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisPostDtls;

public class OrgPostDtlsDAOImpl extends GenericDaoHibernateImpl<HrEisPostDtls, Long>  {

		
	public OrgPostDtlsDAOImpl(Class<HrEisPostDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	

	
	
}
