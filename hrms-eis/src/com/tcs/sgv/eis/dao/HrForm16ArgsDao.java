
package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrForm16Args;

public class HrForm16ArgsDao extends GenericDaoHibernateImpl<HrForm16Args, Long>{

	public HrForm16ArgsDao(Class<HrForm16Args> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
public List getForm16ArgsData()
	{
		List paySlipArgsList = null;
		Session hibSession = getSession();
        String strQuery = "from HrForm16Args order by dispOrder";
        Query query = hibSession.createQuery(strQuery);
        paySlipArgsList = query.list();
		
		return paySlipArgsList;
	}	

}

