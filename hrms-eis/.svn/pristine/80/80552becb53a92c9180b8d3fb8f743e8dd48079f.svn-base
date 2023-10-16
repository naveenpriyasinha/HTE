package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;

public class LoanPrinRecoverDAOImpl extends GenericDaoHibernateImpl<HrLoanEmpPrinRecoverDtls, Long> implements LoanPrinRecoverDAO {
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");
	public LoanPrinRecoverDAOImpl(Class<HrLoanEmpPrinRecoverDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public List<HrLoanEmpPrinRecoverDtls> getAllData(long empLoanId)
    {
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrLoanEmpPrinRecoverDtls.class);
            objCrt.add(Restrictions.eq("hrLoanEmpDtls.empLoanId",empLoanId));
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
	
	public List<HrLoanEmpPrinRecoverDtls> getAllData(String empIds,long locId,long billNo,Date givenDate)
    {
        Session hiSession = getSession();				
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT princDtls.EMP_LOAN_ID,princDtls.TOTAL_RECOVERED_AMT,princDtls.TOTAL_RECOVERED_INST,loanEmp.ODD_INST_NO,loanEmp.ODD_INST_AMT FROM HR_LOAN_EMP_PRIN_RECOVER_DTLS princDtls,");		
		strQuery.append(" HR_LOAN_EMP_DTLS loanEmp  inner join ");
		strQuery.append(payrollBundle.getString("getEmployeeList").toString());
		strQuery.append(" on emp_list.emp_id=loanEmp.emp_id where princDtls.EMP_LOAN_ID=loanEmp.EMP_LOAN_ID  ");
		//strQuery.append(empIds);
		strQuery.append(" and loanEmp.LOAN_ACTIVATE_FLAG=1 order by princDtls.EMP_LOAN_ID");
		
		logger.info("Query for princ recover is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate",givenDate);
		return query.list();		
    }
	public Map<Long,HrLoanEmpPrinRecoverDtls> getAllDataPrinApprove(String empLoanId)
    {
        
		Map<Long,HrLoanEmpPrinRecoverDtls> prinMap = new HashMap<Long,HrLoanEmpPrinRecoverDtls>();
        try
        {
            Session hibSession = getSession();
            StringBuffer strQuery= new StringBuffer();
            List lst  = new ArrayList();
            /*	StringBuilder lSBQuery = new StringBuilder();
		StringBuilder lSbHeaderVal = new StringBuilder();
             * */
            //strQuery.append(" select  loanPrin.hrLoanEmpDtls.empLoanId,loanPrin   from  HrLoanEmpPrinRecoverDtls loanPrin where  loanPrin.hrLoanEmpDtls.empLoanId in (");
            strQuery.append(" SELECT hrLoanEmpDtls.emp_Loan_Id,loanPrin.PRIN_RECOVER_ID,loanPrin.EMP_LOAN_ID,loanPrin.TOTAL_RECOVERED_AMT,loanPrin.TOTAL_RECOVERED_INST,loanPrin.DB_ID,loanPrin.LOC_ID,loanPrin.CREATED_BY" + 
            		",loanPrin.CREATED_BY_POST,loanPrin.CREATED_DATE,loanPrin.UPDATED_BY,loanPrin.UPDATED_BY_POST,loanPrin.UPDATED_DATE,loanPrin.TRN_COUNTER FROM ifms.Hr_Loan_Emp_Prin_Recover_Dtls loanPrin ");
            strQuery.append(" inner join  ifms.hr_Loan_Emp_Dtls as  hrLoanEmpDtls on loanPrin.emp_Loan_Id=hrLoanEmpDtls.emp_Loan_Id");
            strQuery.append(" where  loanPrin.emp_Loan_Id in (");
            if(!empLoanId.equals("") && empLoanId!=null) {
    			strQuery.append(empLoanId);
        	}else  {
        		strQuery.append(0);
        	}
    		strQuery.append(")");
    		Query query = hibSession.createSQLQuery(strQuery.toString());
    		lst  =query.list();
    		if(lst != null && !lst.isEmpty() )
    		{
    			int size = lst.size();
    			for(int i=0;i<size ;i++)
    			{
    			Object[] obj =(Object[])lst.get(i);
    			prinMap.put(Long.valueOf(String.valueOf(obj[0])),(HrLoanEmpPrinRecoverDtls)obj[1]);
    			
    			}
    		}
    		logger.info("Principal Recovery Map is "+prinMap);	
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return prinMap;
       
    }
}
