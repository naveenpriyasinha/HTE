package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;

public class PaybillRegMpgDAOImpl extends GenericDaoHibernateImpl<PaybillBillregMpg,Long> {
	public PaybillRegMpgDAOImpl(Class<PaybillBillregMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public List getData(long trnBillNo)
	{
		List payBillNoList = null;
		Session hibSession = getSession();
        String strQuery = "from PaybillBillregMpg as mpg where mpg.trnBillRegister.billNo=" + trnBillNo;
        Query query = hibSession.createQuery(strQuery);
        payBillNoList = query.list();
		
		return payBillNoList;
	}
}
