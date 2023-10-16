package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;


public class LoanEmpIntRecvDAOImpl extends GenericDaoHibernateImpl<HrLoanEmpIntRecoverDtls, Long> implements LoanEmpIntRecvDAO {

 	public LoanEmpIntRecvDAOImpl(Class<HrLoanEmpIntRecoverDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	public List insertIntRecvLoan()
    {
 		logger.info("------inside LoanEmpIntRecvDAOImpl-----------");
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrLoanEmpIntRecoverDtls.class);
        }
        catch(Exception e)
        {
        	logger.info("There some error in DAOImpl");
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
}
 	