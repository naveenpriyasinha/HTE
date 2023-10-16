package com.tcs.sgv.eis.zp.zpAdminOffice.dao;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import org.hibernate.SessionFactory;
public class ZpAdminOfficeDAOImpl extends GenericDaoHibernateImpl implements ZpAdminOfficeDAO
{
	public ZpAdminOfficeDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

}
