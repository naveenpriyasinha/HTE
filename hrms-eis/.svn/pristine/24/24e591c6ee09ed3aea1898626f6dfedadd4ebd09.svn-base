package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpParamRlt;
import com.tcs.sgv.payroll.util.PayrollConstants;


public class PayComponentRuleGroupParamRltDAOImpl extends GenericDaoHibernateImpl<HrPayRuleGrpParamRlt, Long> implements PayComponentRuleGroupParamRltDAO {

	public PayComponentRuleGroupParamRltDAOImpl(Class<HrPayRuleGrpParamRlt> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 		
	@SuppressWarnings("unchecked")
	public List<HrPayRuleGrpParamRlt> getPayCompRuleParamMpgList(long payCompCode){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		
	 		strQuery.append("select ruleGrpParamRlt FROM HrPayRuleGrpParamRlt ruleGrpParamRlt, HrPayRuleGrpMst ruleGrpMst, HrPayAllowDedMst payCompMst ");
	 		strQuery.append(" WHERE ruleGrpParamRlt.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId and ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=");
	 		strQuery.append(payCompCode);
	 		strQuery.append(" and payCompMst.status=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode and payCompMst.langId=1");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
	@SuppressWarnings("unchecked")
	public List getAllActiveRuleParamMpgList(int payCompType){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		
	 		strQuery.append("select ruleGrpParamRlt,ruleGrpMst.ruleGrpId,payRuleParamMst.paramId,payRuleParamMst.paramType FROM HrPayRuleGrpParamRlt ruleGrpParamRlt, HrPayRuleGrpMst ruleGrpMst,HrPayRuleParamMst payRuleParamMst, HrPayAllowDedMst payCompMst ");
	 		strQuery.append(" WHERE ruleGrpParamRlt.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId and ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode");
	 		strQuery.append(" and  ruleGrpParamRlt.paramId.paramId=payRuleParamMst.paramId ");
	 		strQuery.append(" and payCompMst.status=1 and payCompMst.type=");
	 		strQuery.append(payCompType);
	 		strQuery.append(" and payCompMst.langId=1");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
	
	@SuppressWarnings("unchecked")
	public List getAllActiveDeductionRuleParamMpgList(){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		
	 		strQuery.append("select ruleGrpParamRlt,ruleGrpMst.ruleGrpId,payRuleParamMst.paramId,payRuleParamMst.paramType FROM HrPayRuleGrpParamRlt ruleGrpParamRlt, HrPayRuleGrpMst ruleGrpMst,HrPayRuleParamMst payRuleParamMst, HrPayAllowDedMst payCompMst ");
	 		strQuery.append(" WHERE ruleGrpParamRlt.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId and ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode");
	 		strQuery.append(" and  ruleGrpParamRlt.paramId.paramId=payRuleParamMst.paramId ");
	 		strQuery.append(" and payCompMst.status=1 and payCompMst.type in (");
	 		strQuery.append(PayrollConstants.PAYROLL_DEDUCTION_TYPE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_SCHEME_TYPE);
	 		strQuery.append(")");
	 		strQuery.append(" and payCompMst.langId=1");
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
	@SuppressWarnings("unchecked")
	public List getAllActiveRuleParamMpgList(int payCompType,int usedInFormulaFlag){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		
	 		strQuery.append("select ruleGrpParamRlt,ruleGrpMst.ruleGrpId,payRuleParamMst.paramId,payRuleParamMst.paramType FROM HrPayRuleGrpParamRlt ruleGrpParamRlt, HrPayRuleGrpMst ruleGrpMst,HrPayRuleParamMst payRuleParamMst, HrPayAllowDedMst payCompMst ");
	 		strQuery.append(" WHERE ruleGrpParamRlt.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId and ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode");
	 		strQuery.append(" and  ruleGrpParamRlt.paramId.paramId=payRuleParamMst.paramId ");
	 		strQuery.append(" and payCompMst.status=1 and payCompMst.type=");
	 		strQuery.append(payCompType);
	 		strQuery.append(" and payCompMst.langId=1");
	 		strQuery.append(" and payCompMst.usedInOtherCalculation = ");
	 		strQuery.append(usedInFormulaFlag);
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}
	
	@SuppressWarnings("unchecked")
	public List getAllActiveDeductionRuleParamMpgList(int usedInFormulaFlag){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
 		
	 		strQuery.append("select ruleGrpParamRlt,ruleGrpMst.ruleGrpId,payRuleParamMst.paramId,payRuleParamMst.paramType  FROM HrPayRuleGrpParamRlt ruleGrpParamRlt, HrPayRuleGrpMst ruleGrpMst,HrPayRuleParamMst payRuleParamMst, HrPayAllowDedMst payCompMst ");
	 		strQuery.append(" WHERE ruleGrpParamRlt.ruleGrpId.ruleGrpId=ruleGrpMst.ruleGrpId and ruleGrpMst.ruleStatus=1 and ruleGrpMst.allwDedCode=payCompMst.allwDedCode");
	 		strQuery.append(" and  ruleGrpParamRlt.paramId.paramId=payRuleParamMst.paramId ");
	 		strQuery.append(" and payCompMst.status=1 and payCompMst.type in (");
	 		strQuery.append(PayrollConstants.PAYROLL_DEDUCTION_TYPE);
	 		strQuery.append(",");
	 		strQuery.append(PayrollConstants.PAYROLL_SCHEME_TYPE);
	 		strQuery.append(")");
	 		strQuery.append(" and payCompMst.langId=1");
	 		strQuery.append(" and payCompMst.usedInOtherCalculation = ");
	 		strQuery.append(usedInFormulaFlag);
	 		
	 		Query query = session.createQuery(strQuery.toString());
 		
 		return query.list();
 	}	

}
