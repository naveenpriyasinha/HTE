package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayPayslipArgs;

public class PayslipArgsDAOImpl extends GenericDaoHibernateImpl<HrPayPayslipArgs, Long> implements PayslipArgsDAO {

	public PayslipArgsDAOImpl(Class<HrPayPayslipArgs> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
public List getAllAllowanceData()
	{
		List paySlipArgsList = null;
		Session hibSession = getSession();
        String strQuery = "from HrPayPayslipArgs where argsType = 'a' order by dispOrder";
        Query query = hibSession.createQuery(strQuery);
        paySlipArgsList = query.list();
		
		return paySlipArgsList;
	}	


public List getAllDeducData()
{
	List paySlipArgsList = null;
	Session hibSession = getSession();
    String strQuery = "from HrPayPayslipArgs where argsType = 'd' or argsType='l' order by dispOrder";
    Query query = hibSession.createQuery(strQuery);
    paySlipArgsList = query.list();
	
	return paySlipArgsList;
}	

}
