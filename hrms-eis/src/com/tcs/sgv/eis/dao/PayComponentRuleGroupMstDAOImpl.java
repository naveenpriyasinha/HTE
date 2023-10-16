package com.tcs.sgv.eis.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpMst;
import com.tcs.sgv.payroll.util.PayrollConstants;


public class PayComponentRuleGroupMstDAOImpl extends GenericDaoHibernateImpl<HrPayRuleGrpMst, Long> implements PayComponentRuleGroupMstDAO {

	public PayComponentRuleGroupMstDAOImpl(Class<HrPayRuleGrpMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 		
	@SuppressWarnings("unchecked")
	public List<HrPayRuleGrpMst> getAllActiveRuleList(int payCompType){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		List<HrPayRuleGrpMst> ruleMstList = new ArrayList();
 		
 		strQuery.append("select ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula,ruleGrpMst.returnValue, ");
 		strQuery.append("ruleGrpMst.ruleStatus, count(ruleParam.ruleGrpId.ruleGrpId) ");
 		
 		strQuery.append(" ");
 		strQuery.append(" FROM HrPayRuleGrpMst ruleGrpMst, HrPayAllowDedMst payCompMst, HrPayRuleGrpParamRlt ruleParam");
 		strQuery.append(" WHERE ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode and payCompMst.status=1 and payCompMst.usedInOtherCalculation=0");
 		strQuery.append(" and payCompMst.type=");
 		strQuery.append(payCompType);
 		
 		strQuery.append(" and ruleParam.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId");
 		strQuery.append(" and payCompMst.langId=1 ");
 		strQuery.append(" group by ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula, ");
 		strQuery.append(" ruleGrpMst.returnValue,ruleGrpMst.ruleStatus");
 		strQuery.append(" order by count(ruleParam.ruleGrpId.ruleGrpId) DESC ");
 		
 		Query query = session.createQuery(strQuery.toString());
		
 		HrPayRuleGrpMst ruleGrpMst = null;
 		
 		Iterator itr = query.list().iterator(); 

 		while (itr.hasNext())
 		{
 			Object[] row = (Object[]) itr.next();
 			ruleGrpMst = new HrPayRuleGrpMst();
 			ruleGrpMst.setRuleGrpId(Long.parseLong(row[0].toString()));
 			ruleGrpMst.setAllwDedCode(Long.parseLong(row[1].toString()));
 			ruleGrpMst.setReturnType(Integer.parseInt(row[2].toString()));
 			ruleGrpMst.setReturnFormula(row[3]!=null?row[3].toString():"");
 			ruleGrpMst.setReturnValue(row[4]!=null?new BigDecimal(row[4].toString()):new BigDecimal(0));
 			ruleGrpMst.setRuleStatus(Integer.parseInt(row[5].toString()));
 			
 			ruleMstList.add(ruleGrpMst);
 		}
 		
 		return ruleMstList;
 	}
	@SuppressWarnings("unchecked")
	public List<HrPayRuleGrpMst> getAllActiveDeductionRuleList(){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		List<HrPayRuleGrpMst> ruleMstList = new ArrayList();
 		
 		strQuery.append("select ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula,ruleGrpMst.returnValue, ");
 		strQuery.append("ruleGrpMst.ruleStatus, count(ruleParam.ruleGrpId.ruleGrpId) ");
 		
 		strQuery.append(" ");
 		strQuery.append(" FROM HrPayRuleGrpMst ruleGrpMst, HrPayAllowDedMst payCompMst, HrPayRuleGrpParamRlt ruleParam");
 		strQuery.append(" WHERE ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode and payCompMst.status=1 and payCompMst.usedInOtherCalculation=0");
 		strQuery.append(" and payCompMst.type in (");
 		strQuery.append(PayrollConstants.PAYROLL_DEDUCTION_TYPE);
 		strQuery.append(",");
 		strQuery.append(PayrollConstants.PAYROLL_SCHEME_TYPE);
 		strQuery.append(")");
 		strQuery.append(" and ruleParam.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId");
 		strQuery.append(" and payCompMst.langId=1 ");
 		strQuery.append(" group by ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula, ");
 		strQuery.append(" ruleGrpMst.returnValue,ruleGrpMst.ruleStatus");
 		strQuery.append(" order by count(ruleParam.ruleGrpId.ruleGrpId) DESC ");
 		
 		Query query = session.createQuery(strQuery.toString());
		
 		HrPayRuleGrpMst ruleGrpMst = null;
 		
 		Iterator itr = query.list().iterator(); 

 		while (itr.hasNext())
 		{
 			Object[] row = (Object[]) itr.next();
 			ruleGrpMst = new HrPayRuleGrpMst();
 			ruleGrpMst.setRuleGrpId(Long.parseLong(row[0].toString()));
 			ruleGrpMst.setAllwDedCode(Long.parseLong(row[1].toString()));
 			ruleGrpMst.setReturnType(Integer.parseInt(row[2].toString()));
 			ruleGrpMst.setReturnFormula(row[3]!=null?row[3].toString():"");
 			ruleGrpMst.setReturnValue(row[4]!=null?new BigDecimal(row[4].toString()):new BigDecimal(0));
 			ruleGrpMst.setRuleStatus(Integer.parseInt(row[5].toString()));
 			
 			ruleMstList.add(ruleGrpMst);
 		}

 		return ruleMstList;
 	}

	@SuppressWarnings("unchecked")
	public List<HrPayRuleGrpMst> getPayCompRuleList(long payCompCode){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		List<HrPayRuleGrpMst> ruleMstList = new ArrayList();
 		
	 		strQuery.append("select ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula,ruleGrpMst.returnValue, ");
	 		strQuery.append("ruleGrpMst.ruleStatus, count(ruleParam.ruleGrpId.ruleGrpId) ");
	 		
	 		strQuery.append(" ");
	 		strQuery.append(" FROM HrPayRuleGrpMst ruleGrpMst, HrPayAllowDedMst payCompMst, HrPayRuleGrpParamRlt ruleParam");
	 		strQuery.append(" WHERE ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode and payCompMst.status=1 ");
	 		strQuery.append(" and payCompMst.allwDedCode=");
	 		strQuery.append(payCompCode);
	 		
	 		strQuery.append(" and ruleParam.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId");
	 		strQuery.append(" and payCompMst.langId=1 ");
	 		strQuery.append(" group by ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula, ");
	 		strQuery.append(" ruleGrpMst.returnValue,ruleGrpMst.ruleStatus");
	 		strQuery.append(" order by count(ruleParam.ruleGrpId.ruleGrpId) DESC ");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
	 		HrPayRuleGrpMst ruleGrpMst = null;
	 		
	 		Iterator itr = query.list().iterator(); 

	 		while (itr.hasNext())
	 		{
	 			Object[] row = (Object[]) itr.next();
	 			ruleGrpMst = new HrPayRuleGrpMst();
	 			ruleGrpMst.setRuleGrpId(Long.parseLong(row[0].toString()));
	 			ruleGrpMst.setAllwDedCode(Long.parseLong(row[1].toString()));
	 			ruleGrpMst.setReturnType(Integer.parseInt(row[2].toString()));
	 			ruleGrpMst.setReturnFormula(row[3]!=null?row[3].toString():"");
	 			ruleGrpMst.setReturnValue(row[4]!=null?new BigDecimal(row[4].toString()):new BigDecimal(0));
	 			ruleGrpMst.setRuleStatus(Integer.parseInt(row[5].toString()));
	 			
	 			ruleMstList.add(ruleGrpMst);
	 		}
	 		
	 		return ruleMstList;
 	}
	@SuppressWarnings("unchecked")
	public List<HrPayRuleGrpMst> getAllActiveRuleList(int payCompType,int usedInFormulaFlag){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		List<HrPayRuleGrpMst> ruleMstList = new ArrayList();
 		
 		strQuery.append("select ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula,ruleGrpMst.returnValue, ");
 		strQuery.append("ruleGrpMst.ruleStatus, count(ruleParam.ruleGrpId.ruleGrpId) ");
 		
 		strQuery.append(" ");
 		strQuery.append(" FROM HrPayRuleGrpMst ruleGrpMst, HrPayAllowDedMst payCompMst, HrPayRuleGrpParamRlt ruleParam");
 		strQuery.append(" WHERE ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode and payCompMst.status=1 ");
 		strQuery.append(" and payCompMst.type=");
 		strQuery.append(payCompType);
 		
 		strQuery.append(" and ruleParam.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId");
 		strQuery.append(" and payCompMst.langId=1 ");
 		strQuery.append(" and payCompMst.usedInOtherCalculation = ");
 		strQuery.append(usedInFormulaFlag);
 		
 		strQuery.append(" group by ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula, ");
 		strQuery.append(" ruleGrpMst.returnValue,ruleGrpMst.ruleStatus");
 		strQuery.append(" order by count(ruleParam.ruleGrpId.ruleGrpId) DESC ");
 		
 		Query query = session.createQuery(strQuery.toString());
		
 		HrPayRuleGrpMst ruleGrpMst = null;
 		
 		Iterator itr = query.list().iterator(); 

 		while (itr.hasNext())
 		{
 			Object[] row = (Object[]) itr.next();
 			ruleGrpMst = new HrPayRuleGrpMst();
 			ruleGrpMst.setRuleGrpId(Long.parseLong(row[0].toString()));
 			ruleGrpMst.setAllwDedCode(Long.parseLong(row[1].toString()));
 			ruleGrpMst.setReturnType(Integer.parseInt(row[2].toString()));
 			ruleGrpMst.setReturnFormula(row[3]!=null?row[3].toString():"");
 			ruleGrpMst.setReturnValue(row[4]!=null?new BigDecimal(row[4].toString()):new BigDecimal(0));
 			ruleGrpMst.setRuleStatus(Integer.parseInt(row[5].toString()));
 			
 			ruleMstList.add(ruleGrpMst);
 		}
 		
 		return ruleMstList;
 	}
	@SuppressWarnings("unchecked")
	public List<HrPayRuleGrpMst> getAllActiveDeductionRuleList(int usedInFormulaFlag){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		List<HrPayRuleGrpMst> ruleMstList = new ArrayList();
 		
 		strQuery.append("select ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula,ruleGrpMst.returnValue, ");
 		strQuery.append("ruleGrpMst.ruleStatus, count(ruleParam.ruleGrpId.ruleGrpId) ");
 		
 		strQuery.append(" ");
 		strQuery.append(" FROM HrPayRuleGrpMst ruleGrpMst, HrPayAllowDedMst payCompMst, HrPayRuleGrpParamRlt ruleParam");
 		strQuery.append(" WHERE ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode and payCompMst.status=1 ");
 		strQuery.append(" and payCompMst.type in (");
 		strQuery.append(PayrollConstants.PAYROLL_DEDUCTION_TYPE);
 		strQuery.append(",");
 		strQuery.append(PayrollConstants.PAYROLL_SCHEME_TYPE);
 		strQuery.append(")");
 		strQuery.append(" and ruleParam.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId");
 		strQuery.append(" and payCompMst.langId=1 ");
 		strQuery.append(" and payCompMst.usedInOtherCalculation = ");
 		strQuery.append(usedInFormulaFlag);
 		strQuery.append(" group by ruleGrpMst.ruleGrpId,ruleGrpMst.allwDedCode,ruleGrpMst.returnType,ruleGrpMst.returnFormula, ");
 		strQuery.append(" ruleGrpMst.returnValue,ruleGrpMst.ruleStatus");
 		strQuery.append(" order by count(ruleParam.ruleGrpId.ruleGrpId) DESC ");
 		
 		Query query = session.createQuery(strQuery.toString());
		
 		HrPayRuleGrpMst ruleGrpMst = null;
 		
 		Iterator itr = query.list().iterator(); 

 		while (itr.hasNext())
 		{
 			Object[] row = (Object[]) itr.next();
 			ruleGrpMst = new HrPayRuleGrpMst();
 			ruleGrpMst.setRuleGrpId(Long.parseLong(row[0].toString()));
 			ruleGrpMst.setAllwDedCode(Long.parseLong(row[1].toString()));
 			ruleGrpMst.setReturnType(Integer.parseInt(row[2].toString()));
 			ruleGrpMst.setReturnFormula(row[3]!=null?row[3].toString():"");
 			ruleGrpMst.setReturnValue(row[4]!=null?new BigDecimal(row[4].toString()):new BigDecimal(0));
 			ruleGrpMst.setRuleStatus(Integer.parseInt(row[5].toString()));
 			
 			ruleMstList.add(ruleGrpMst);
 		}

 		return ruleMstList;
 	}
}
