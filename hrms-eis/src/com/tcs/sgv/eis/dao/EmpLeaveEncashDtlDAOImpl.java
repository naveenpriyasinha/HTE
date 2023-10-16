package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;


import com.tcs.sgv.eis.valueobject.HrPayLeaveEncashDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;


public class EmpLeaveEncashDtlDAOImpl extends GenericDaoHibernateImpl<HrPayLeaveEncashDtl, Long> implements EmpLeaveEncashDtlDAO {
	
	Log logger = LogFactory.getLog( getClass() );
	
	public EmpLeaveEncashDtlDAOImpl(Class<HrPayLeaveEncashDtl> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
	public double getEmpLeaveEncashByUSerId(long UserId,Date prevMonthCreatedDate)
	{
		double leaveEncash=0;
		
		
		List list =new ArrayList();
 		Session hibSession = getSession();
 		StringBuffer query=new StringBuffer();
		 query.append("select sum(le.elAmount) from HrPayLeaveEncashDtl le where le.orgUserMstByUserId.userId=:UserId  and le.status=0 and " );
		 query.append(" le.createdDate>=:CreatedDate " );
		
		Query hsqlQuery = hibSession.createQuery(query.toString());	 
		hsqlQuery.setParameter("UserId", UserId);
		hsqlQuery.setParameter("CreatedDate", prevMonthCreatedDate);
		
		
	   list= hsqlQuery.list();
	   if(list.get(0)!=null)
		   leaveEncash=  Double.parseDouble(list.get(0).toString());
	   
		return leaveEncash;
	}

}
