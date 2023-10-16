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
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;


public class LoanIntRecoverDAOImpl extends GenericDaoHibernateImpl<HrLoanEmpIntRecoverDtls, Long> implements LoanIntRecoverDAO {
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");
	public LoanIntRecoverDAOImpl(Class<HrLoanEmpIntRecoverDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public List<HrLoanEmpIntRecoverDtls> getAllData(long empLoanId)
    {
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrLoanEmpIntRecoverDtls.class);
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
		strQuery.append("SELECT intDtls.EMP_LOAN_ID,intDtls.TOTAL_RECOVERED_INT,intDtls.TOTAL_RECOVERED_INT_INST,loanEmp.ODD_INST_NO,loanEmp.ODD_INST_AMT FROM HR_LOAN_EMP_INT_RECOVER_DTLS intDtls,");		
		strQuery.append(" HR_LOAN_EMP_DTLS loanEmp inner join ");
		strQuery.append(payrollBundle.getString("getEmployeeList").toString());
		strQuery.append(" on emp_list.emp_id=loanEmp.emp_id where intDtls.EMP_LOAN_ID=loanEmp.EMP_LOAN_ID and loanEmp.LOAN_ACTIVATE_FLAG=1 ");
		strQuery.append(" order by intDtls.EMP_LOAN_ID");
		
		logger.info("Query is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate",givenDate);
		return query.list();		
    }
	public Map<Long,HrLoanEmpIntRecoverDtls> getAllDataIntApprove(String empLoanId)
    {
        
		Map<Long,HrLoanEmpIntRecoverDtls> intMap = new HashMap<Long,HrLoanEmpIntRecoverDtls>();
        try
        {
            Session hibSession = getSession();
            StringBuffer strQuery= new StringBuffer();
            List lst  = new ArrayList();
            
           // strQuery.append(" select loanInt.hrLoanEmpDtls.empLoanId, loanInt from  HrLoanEmpIntRecoverDtls loanInt where  loanInt.hrLoanEmpDtls.empLoanId in (");
            strQuery.append(" select hrLoanEmpDtls.emp_Loan_Id, loanInt.INT_RECOVER_ID,loanInt.EMP_LOAN_ID,loanInt.TOTAL_RECOVERED_INT,loanInt.TOTAL_RECOVERED_INT_INST,loanInt.DB_ID,loanInt.LOC_ID,loanInt.CREATED_BY," + 
            		"loanInt.CREATED_BY_POST,loanInt.CREATED_DATE,loanInt.UPDATED_BY,loanInt.UPDATED_BY_POST,loanInt.UPDATED_DATE,loanInt.TRN_COUNTER from  ifms.Hr_Loan_Emp_Int_Recover_Dtls loanInt inner join ifms.hr_Loan_Emp_Dtls as ");
            strQuery.append(" hrLoanEmpDtls on loanInt.emp_Loan_Id=hrLoanEmpDtls.emp_Loan_Id ");
            strQuery.append(" where  loanInt.emp_Loan_Id in (");
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
    			intMap.put(Long.valueOf(String.valueOf(obj[0])),(HrLoanEmpIntRecoverDtls)obj[1]);
    			
    			}
    		}
    		logger.info("Interest Recovery Map is "+intMap);	
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return intMap;
       
    }
	
	//Added By Roshan for paybill validation 
	public String getEmpNameFromEmpId(long eisEmpId) {
		String empName=null;
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("SELECT distinct dcps.emp_name || '(' || dcps.SEVARTH_ID || ')' FROM MST_DCPS_EMP dcps ");
		queryBuffer.append("inner join HR_EIS_EMP_MST eis on dcps.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
		queryBuffer.append("where eis.emp_id="+eisEmpId+" ");
		queryBuffer.append("and dcps.reg_status in(1,2) ");
		Query query = hibSession.createSQLQuery(queryBuffer.toString());
		logger.info("Query is :: "+queryBuffer.toString());
		empName = query.uniqueResult().toString();
		return empName;
	}
}
