package com.tcs.sgv.pdpla.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.MstPdAccount;



public class DepositAccMstDAOImpl extends GenericDaoHibernateImpl<MstPdAccount,Long> implements DepositAccMstDAO
{
	Log gLogger = LogFactory.getLog(getClass());
	//Session ghibSession = null;
	
	public DepositAccMstDAOImpl(Class<MstPdAccount> type, SessionFactory sessionFactory)
    {
		super(type);
        setSessionFactory(sessionFactory);
    }
	
	}	










   

