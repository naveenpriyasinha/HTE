package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayRuleParamMst;




public class PayComponentParamMstDAOImpl extends GenericDaoHibernateImpl<HrPayRuleParamMst, Long> {
	Log logger = LogFactory.getLog(getClass());
	public PayComponentParamMstDAOImpl(Class<HrPayRuleParamMst> type, SessionFactory sessionFactory)
     {
		super(type);
		setSessionFactory(sessionFactory);
	  }

	@SuppressWarnings("unchecked")
	public List<HrPayRuleParamMst> getRuleParameters(){
 			
 		Session session = getSession();
 		StringBuffer strQuery = new StringBuffer();
 		
	 		strQuery.append("FROM HrPayRuleParamMst hrPayruleParamMst");
	 		
	 		strQuery.append(" order by hrPayruleParamMst.paramId");
	 		
	 		Query query = session.createQuery(strQuery.toString());
	 		
	 		//System.out.println("===> getRuleParameters query :: "+query);
 		
 		return query.list();
 	}
}


