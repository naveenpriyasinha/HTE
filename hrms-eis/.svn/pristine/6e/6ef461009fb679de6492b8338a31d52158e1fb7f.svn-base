package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayAllowDedMst;
import com.tcs.sgv.eis.valueobject.HrPayPaycommissionMst;
import com.tcs.sgv.payroll.util.PayrollConstants;


public class PayComponentMasterDAOImpl extends GenericDaoHibernateImpl<HrPayAllowDedMst, Long> implements PayComponentMasterDAO {

	
	
 	public PayComponentMasterDAOImpl(Class<HrPayAllowDedMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	/**
 	 * 
 	 * @return list of pay commissions.
 	 */
 	@SuppressWarnings("unchecked")
	public List<HrPayPaycommissionMst> getPayCommisions(int langId, long commissionId){
 		
 		Session session = getSession();
 		
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayPaycommissionMst hrPayCommissionMst");
	 		strQuery.append(" WHERE hrPayCommissionMst.langId=");
	 		strQuery.append(langId);
	 		if(commissionId!=0){
	 			strQuery.append(" and hrPayCommissionMst.id=");
	 			strQuery.append(commissionId);
	 		}
	 		Query query = session.createQuery(strQuery.toString());

 		
		return query.list();
 	}
 	@SuppressWarnings("unchecked")
	public List<HrPayAllowDedMst> getPayComponents(int langId,int status){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" WHERE hrPayAllowDedMst.langId=");
	 		strQuery.append(langId);
	 			strQuery.append(" and hrPayAllowDedMst.status=");
	 			strQuery.append(status);
	 		strQuery.append(" order by hrPayAllowDedMst.displayOrder, hrPayAllowDedMst.allowDedId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
 	@SuppressWarnings("unchecked")
	public List<HrPayAllowDedMst> getPayComponentsForViewList(int langId){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" WHERE hrPayAllowDedMst.langId=");
	 		strQuery.append(langId);
	 			strQuery.append(" and hrPayAllowDedMst.type <> 4");
	 		
	 		strQuery.append(" order by hrPayAllowDedMst.displayOrder, hrPayAllowDedMst.allowDedId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
 	
 	@SuppressWarnings("unchecked")
	public List<HrPayAllowDedMst> getPayActiveComponets(long langId){
		
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" WHERE hrPayAllowDedMst.status= 1 and  hrPayAllowDedMst.langId=");
	 		strQuery.append(langId);
	 		strQuery.append(" order by hrPayAllowDedMst.displayOrder, hrPayAllowDedMst.allowDedId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
 	
	/**
	 * Added By Manish Khunt
	 * @param payCompType
	 * @return
	 */
	public List<HrPayAllowDedMst> getPayActiveComponets(int  payCompType){
		
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" WHERE hrPayAllowDedMst.status= 1 and  hrPayAllowDedMst.type=");
	 		strQuery.append(payCompType);
	 		strQuery.append(" order by hrPayAllowDedMst.displayOrder, hrPayAllowDedMst.allowDedId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
 	
 	
 	public int countColumnInner(String type) throws Exception
 	{
 		Session session = getSession();
 		StringBuffer strQue = new StringBuffer();
 		strQue.append("select count(distinct(DISPLAY_ORDER)) from HR_PAY_ALLOW_DED_MST where STATUS=1 and  TYPE in (");
 		strQue.append(type);
 		strQue.append(")");
 		Query query = session.createSQLQuery(strQue.toString());
 		int colCount = Integer.parseInt(query.list().get(0).toString());
 		return colCount;
 		
 	}
 	
 	@SuppressWarnings("unchecked")
	public List getCompoData(String type) throws Exception
 	{
 		Session session = getSession();
 		StringBuffer strQue = new StringBuffer();
 		strQue.append(" from HrPayAllowDedMst hrPayAllowDedMst where hrPayAllowDedMst.status=1 and  hrPayAllowDedMst.type in (");
 		strQue.append(type);
 		strQue.append(") order by hrPayAllowDedMst.displayOrder,hrPayAllowDedMst.allowDedId");
 		Query query = session.createQuery(strQue.toString());
 		return query.list();
 		
 	}
 	
 	public int chkVacantDisplayOrder(int displayOrder,int displaySubOrder,long payCompCode)
 	{
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		strQuery.append("from HrPayAllowDedMst hrPayAllowDedMst");
 		strQuery.append(" where hrPayAllowDedMst.displayOrder=");
 		strQuery.append(displayOrder);
 		if(displaySubOrder!=0)
 		{
 		strQuery.append(" and hrPayAllowDedMst.displaySubOrder=");
 		strQuery.append(displaySubOrder);
 		}
 		if(payCompCode!=0)
 		{
 			strQuery.append(" and hrPayAllowDedMst.allowDedId<>");
 	 		strQuery.append(payCompCode);
 		}
	 	Query query = session.createQuery(strQuery.toString());
 		return query.list().size();
 	}
 	public void updateDisplayOrder(int displayOrder)
 	{
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("update HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" set hrPayAllowDedMst.displayOrder = hrPayAllowDedMst.displayOrder+1 ");
	 		strQuery.append(" where hrPayAllowDedMst.displayOrder>=");
	 		strQuery.append(displayOrder);
	 		session.createQuery(strQuery.toString()).executeUpdate();
 	}
 	public void updateDisplaySubOrder(int displayOrder,int displaySubOrder)
 	{
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		strQuery.append("update HrPayAllowDedMst hrPayAllowDedMst");
 		strQuery.append(" set hrPayAllowDedMst.displaySubOrder = hrPayAllowDedMst.displaySubOrder+1 ");
 		strQuery.append(" where hrPayAllowDedMst.displaySubOrder>=");
 		strQuery.append(displaySubOrder);
 		strQuery.append(" and hrPayAllowDedMst.displayOrder=");
 		strQuery.append(displayOrder);
	 		
	 	session.createQuery(strQuery.toString()).executeUpdate();
 	}
 	@SuppressWarnings("unchecked")
	public List<HrPayAllowDedMst> getRuleBasedPayCompForViewList(long langId){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" WHERE hrPayAllowDedMst.langId=");
	 		strQuery.append(langId);
	 		strQuery.append(" and hrPayAllowDedMst.type not in (1,4)");
	 		strQuery.append(" and hrPayAllowDedMst.ruleBasedFlag = 1");
	 		strQuery.append(" and hrPayAllowDedMst.status = 1");
	 		strQuery.append(" and hrPayAllowDedMst.allowDedId not in (");
	 		strQuery.append(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_GRADE_PAY_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_AG_DEDUCTION_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_TREASURY_DEDUCTION_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_OTHER_ALLOWANCE_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_OTHER_RECOVERIES_CODE);
	 		strQuery.append(")");
	 		
	 		strQuery.append(" order by hrPayAllowDedMst.displayOrder,hrPayAllowDedMst.displaySubOrder, hrPayAllowDedMst.allowDedId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
 	@SuppressWarnings("unchecked")
	public List<HrPayAllowDedMst> getActivePayComponentsForSalaryView(long langId){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" WHERE hrPayAllowDedMst.langId=");
	 		strQuery.append(langId);
	 		strQuery.append(" and hrPayAllowDedMst.type <> 1");
	 		strQuery.append(" and hrPayAllowDedMst.status = 1");
	 		strQuery.append(" and hrPayAllowDedMst.allowDedId not in ( ");
	 		
	 	/*	strQuery.append(PayrollConstants.PAYROLL_TOTAL_AG_DEDUCTION_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_TREASURY_DEDUCTION_CODE);
	 		strQuery.append(",");*/
	 		strQuery.append(PayrollConstants.PAYROLL_OTHER_ALLOWANCE_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_OTHER_RECOVERIES_CODE);
	 		strQuery.append(")");
	 		
	 		strQuery.append(" order by hrPayAllowDedMst.displayOrder,hrPayAllowDedMst.displaySubOrder, hrPayAllowDedMst.allowDedId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
 	@SuppressWarnings("unchecked")
	public List<HrPayAllowDedMst> getRuleBasedPayCompForViewList(long langId,long payCompCode){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" WHERE hrPayAllowDedMst.langId=");
	 		strQuery.append(langId);
	 		strQuery.append(" and hrPayAllowDedMst.type not in (1,4)");
	 		strQuery.append(" and hrPayAllowDedMst.ruleBasedFlag = 1");
	 		strQuery.append(" and hrPayAllowDedMst.status = 1");
	 		strQuery.append(" and hrPayAllowDedMst.allowDedId not in (");
	 		strQuery.append(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_GRADE_PAY_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_AG_DEDUCTION_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_TREASURY_DEDUCTION_CODE);
	 		strQuery.append(")");
	 		
	 		if(payCompCode!=0)
	 		{
	 			strQuery.append(" and hrPayAllowDedMst.allowDedId =");
	 			strQuery.append(payCompCode);
	 		}
	 		
	 		strQuery.append(" order by hrPayAllowDedMst.displayOrder,hrPayAllowDedMst.displaySubOrder, hrPayAllowDedMst.allowDedId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
 	public List<HrPayAllowDedMst> getRuleBasedPayCompForViewListForMaha(long langId,long payCompCode){
			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayAllowDedMst hrPayAllowDedMst");
	 		strQuery.append(" WHERE hrPayAllowDedMst.langId=");
	 		strQuery.append(langId);
	 		strQuery.append(" and hrPayAllowDedMst.type not in (1,4)");
	 		strQuery.append(" and hrPayAllowDedMst.ruleBasedFlag = 1");
	 		strQuery.append(" and hrPayAllowDedMst.status = 1");
	 		/*strQuery.append(" and hrPayAllowDedMst.allowDedId not in (");
	 		strQuery.append(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_GRADE_PAY_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_AG_DEDUCTION_CODE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_TREASURY_DEDUCTION_CODE);
	 		strQuery.append(")");*/
	 		
	 		if(payCompCode!=0)
	 		{
	 			strQuery.append(" and hrPayAllowDedMst.allowDedId =");
	 			strQuery.append(payCompCode);
	 		}
	 		
	 		strQuery.append(" order by hrPayAllowDedMst.displayOrder,hrPayAllowDedMst.displaySubOrder, hrPayAllowDedMst.allowDedId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
 	@SuppressWarnings("unchecked")
 	public List getPayCompUsedInFormula(int payCompType,long payComsn,long langId){

 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();

 		strQuery.append("select hrPayAllowDedMst,comsnRlt.commissionId FROM HrPayAllowDedMst hrPayAllowDedMst,HrPayAllowDedCommissionRlt comsnRlt ");
 		strQuery.append(" WHERE hrPayAllowDedMst.langId=");
 		strQuery.append(langId);
 		strQuery.append(" and hrPayAllowDedMst.type not in (1,4)");
 		strQuery.append(" and hrPayAllowDedMst.usedInOtherCalculation = 1");
 		strQuery.append(" and hrPayAllowDedMst.status = 1");
 		strQuery.append(" and hrPayAllowDedMst.allwDedCode not in (");
 		strQuery.append(PayrollConstants.PAYROLL_BASIC_PAY_CODE);
 		strQuery.append(",");
 		strQuery.append(PayrollConstants.PAYROLL_GRADE_PAY_CODE);
 		strQuery.append(",");
 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_AG_DEDUCTION_CODE);
 		strQuery.append(",");
 		strQuery.append(PayrollConstants.PAYROLL_TOTAL_TREASURY_DEDUCTION_CODE);
 		strQuery.append(",");
 		strQuery.append(PayrollConstants.PAYROLL_OTHER_ALLOWANCE_CODE);
 		strQuery.append(",");
 		strQuery.append(PayrollConstants.PAYROLL_OTHER_RECOVERIES_CODE);
 		strQuery.append(")");

 		if(payCompType != 0){
 			if(payCompType==PayrollConstants.PAYROLL_ALLOWANCE_TYPE)
 			{
 				strQuery.append(" and hrPayAllowDedMst.type =");
 				strQuery.append(PayrollConstants.PAYROLL_ALLOWANCE_TYPE);
 			}
 		}
 		if(payComsn != 0){
 			strQuery.append(" and comsnRlt.commissionId =");
 			strQuery.append(payComsn);
 		}
 		strQuery.append(" and comsnRlt.allwDedCode = hrPayAllowDedMst.allwDedCode and comsnRlt.status=1");
 		strQuery.append(" order by hrPayAllowDedMst.displayOrder,hrPayAllowDedMst.displaySubOrder, hrPayAllowDedMst.allowDedId");

 		Query query = session.createQuery(strQuery.toString());

 		List dataList = (ArrayList)query.list();

 		return dataList;
 	}
}
