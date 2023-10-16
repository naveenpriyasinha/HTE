package com.tcs.sgv.payslip.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.payslip.valueobject.HrCommonPayslipArgs;

public class commonPayslipArgsDAOImpl extends GenericDaoHibernateImpl<HrCommonPayslipArgs, Long> implements commonPayslipArgsDAO {

	public commonPayslipArgsDAOImpl(Class<HrCommonPayslipArgs> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
public List getAllAllowanceData()
	{
		List paySlipArgsList = null;
		Session hibSession = getSession();
        String strQuery = "from HrCommonPayslipArgs where argsType = 'a' order by dispOrder";
        Query query = hibSession.createQuery(strQuery);
        paySlipArgsList = query.list();
		
		return paySlipArgsList;
	}	


public List getAllDeducData()
{
	List paySlipArgsList = null;
	Session hibSession = getSession();
    String strQuery = "from HrCommonPayslipArgs where argsType = 'd'  order by dispOrder";
    Query query = hibSession.createQuery(strQuery);
    paySlipArgsList = query.list();
	
	return paySlipArgsList;
}	

public List getAllNonGovDeducData()
{
	List paySlipArgsList = null;
	Session hibSession = getSession();
    String strQuery = "from HrCommonPayslipArgs where argsType = 'ng'  order by dispOrder";
    Query query = hibSession.createQuery(strQuery);
    paySlipArgsList = query.list();
	
	return paySlipArgsList;
}	

}
