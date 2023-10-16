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


import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;

import com.tcs.sgv.eis.valueobject.HrPayLeaveSalaryDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;


public class EmpLeaveSalaryDAOImpl extends GenericDaoHibernateImpl<HrPayLeaveSalaryDtl, Long> implements EmpLeaveSalaryDAO {
	
	Log logger = LogFactory.getLog( getClass() );
	
	public EmpLeaveSalaryDAOImpl(Class<HrPayLeaveSalaryDtl> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	@SuppressWarnings("unchecked")
	public HrPayLeaveSalaryDtl getEmpLeaveSalaryDtlByLeaveDateAndUSerId(long UserId,Date leaveDate)
	{
		
		HrPayLeaveSalaryDtl leaveSalaryDtl=new HrPayLeaveSalaryDtl();
		List list =new ArrayList();
 		Session hibSession = getSession();
      	Criteria crit = hibSession.createCriteria(HrPayLeaveSalaryDtl.class);
 	    crit.add(Restrictions.eq("orgUserMstByUserId.userId", UserId)); 
 	    crit.add(Restrictions.like("leaveDate", leaveDate));
 	    
 	   list= crit.list();
 	    if(list.size()>0)
 	    	leaveSalaryDtl=(HrPayLeaveSalaryDtl)list.get(0);
 	    
 	    
 	    
		return leaveSalaryDtl;
	}
	
	
	public double getEmpLeaveSalaryByUSerId(long UserId,Date prevMonthCreatedDate,int monthGiven,int yearGiven)
	{
		double leavesalary=0;
		int date=1;
		Calendar calendar = Calendar.getInstance();
		
		int currentMonth=Calendar.MONTH;
		
		
		calendar.set(yearGiven, monthGiven, date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(yearGiven, monthGiven-1, days);
		
		Date today=new Date();
		if(currentMonth!=monthGiven-1)
			today=calendar.getTime();
		
		List list =new ArrayList();
 		Session hibSession = getSession();
 		StringBuffer query=new StringBuffer();
		 query.append("select sum(ls.LSAmount) from HrPayLeaveSalaryDtl ls where ls.orgUserMstByUserId.userId=:UserId  and ls.status=1 and " );
		 query.append(" ls.leaveDate>=:CreatedDate and ls.leaveDate<:Today " );
		
		Query hsqlQuery = hibSession.createQuery(query.toString());	 
		hsqlQuery.setParameter("UserId", UserId);
		hsqlQuery.setParameter("CreatedDate", prevMonthCreatedDate);
		hsqlQuery.setParameter("Today", today);
		
		list= hsqlQuery.list();
	 
	   if(list.get(0)!=null)
		   leavesalary=  Double.parseDouble(list.get(0).toString());
	   
		return leavesalary;
	}
	
	public boolean checkUserByUSerId(long UserId)
	{
		boolean userLSExist=false;
		
		
		List list =new ArrayList();
 		Session hibSession = getSession();
      	Criteria crit = hibSession.createCriteria(HrPayLeaveSalaryDtl.class);
 	    crit.add(Restrictions.eq("orgUserMstByUserId.userId", UserId)); 
 	   crit.add(Restrictions.eq("status", 1)); 
 	    
 	   list= crit.list();
 	    if(list.size()>0)
 	    	userLSExist=true;
		return userLSExist;
	}
	
	public double getOneDayLS(long UserId,int DutyType,Date leaveDate)
	{
		
		double oneDayLS=0;
		double Basic=0;
		
		int month=leaveDate.getMonth();
		int year=leaveDate.getYear();
		int date = 1;

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
        
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		
		List list =new ArrayList();
 		Session hibSession = getSession();
 		StringBuffer query=new StringBuffer();
		 query.append(" select otd.otherCurrentBasic from  HrEisOtherDtls otd where otd.hrEisEmpMst.orgEmpMst.orgUserMst.userId="+UserId );
		 
		Query hsqlQuery = hibSession.createQuery(query.toString());	 
		list= hsqlQuery.list();
		if(list.size()>0)
			Basic=Double.parseDouble(list.get(0).toString());
		
		oneDayLS=Math.round(Basic/days);
		if(DutyType==0)
			oneDayLS=Math.round(oneDayLS/2);
		
		return oneDayLS;
	}

}
