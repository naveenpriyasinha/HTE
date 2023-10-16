package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;


public class LoanEmpPrinRecvDAOImpl extends GenericDaoHibernateImpl<HrLoanEmpPrinRecoverDtls, Long> implements LoanEmpPrinRecvDAO {

 	public LoanEmpPrinRecvDAOImpl(Class<HrLoanEmpPrinRecoverDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	public List insertPrinRecvLoan()
    {
 		logger.info("------inside LoanEmpPrinRecvDAOImpl-----------");
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrLoanEmpPrinRecoverDtls.class);
        }
        catch(Exception e)
        {
        	logger.info("There some error in DAOImpl");
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
 	
 	public List getLoandetailForForm16(long loanId,long empId,String startDate,String endDate)
    {
 		logger.info("------inside getLoandetailForForm16-----------");
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            StringBuffer sb = new StringBuffer();
            
            sb.append(" from HrLoanEmpPrinRecoverHst hst where hst.hrLoanEmpDtls.hrLoanAdvMst.loanAdvId = '"+loanId);
            sb.append("' and hst.hrLoanEmpDtls.hrEisEmpMst.empId = '"+empId);
            sb.append("' and hst.updatedDate between '"+startDate+"' and '"+endDate+"'");
            
            Query result = hibSession.createQuery(sb.toString());
            
            return result.list();
        }
        catch(Exception e)
        {
        	logger.info("There some error in DAOImpl");
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
 	
 	public List getLoanIntdetailForForm16(long loanId,long empId,String startDate,String endDate)
    {
 		logger.info("------inside getLoanIntdetailForForm16-----------");
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            StringBuffer sb = new StringBuffer();
            
            sb.append(" from HrLoanEmpIntRecoverHst hst where hst.hrLoanEmpDtls.hrLoanAdvMst.loanAdvId = '"+loanId);
            sb.append("' and hst.hrLoanEmpDtls.hrEisEmpMst.empId = '"+empId);
            sb.append("' and hst.updatedDate between '"+startDate+"' and '"+endDate+"'");
            
            Query result = hibSession.createQuery(sb.toString());
            
            return result.list();
        }
        catch(Exception e)
        {
        	logger.info("There some error in DAOImpl");
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
 	
}
 	