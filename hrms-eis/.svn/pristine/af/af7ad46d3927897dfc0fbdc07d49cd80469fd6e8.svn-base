package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class CmnlookupMstDAOImpl extends GenericDaoHibernateImpl<CmnLookupMst, Long> implements  CmnlookupMstDAO
{

	public CmnlookupMstDAOImpl(Class<CmnLookupMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }

	public List getAllData(String AccountType)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
           
            String strQuery = "from CmnLookupMst cmnLookupMst where cmnLookupMst.AccountType ="  +AccountType ;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	
}
