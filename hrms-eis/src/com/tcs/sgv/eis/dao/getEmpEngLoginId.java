package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public class getEmpEngLoginId extends GenericDaoHibernateImpl
{
		
	public getEmpEngLoginId(Class type, SessionFactory sessionFactory)
	{
    	super(type);
    	setSessionFactory(sessionFactory);
	}
	public long getEmpEnglishLoginId(long userId)
	{
		Session hibSession = getSession();		
		List otherDtl1 = new ArrayList();
		Criteria EmpCrit2 = hibSession.createCriteria(OrgEmpMst.class);
		EmpCrit2.add(Restrictions.eq("orgUserMst.userId",userId));
		EmpCrit2.add(Restrictions.eq("cmnLanguageMst.langId",Long.valueOf(1)));
		otherDtl1=EmpCrit2.list();
		OrgEmpMst orgEmpobj = (OrgEmpMst)otherDtl1.get(0);
		long EnEmpId=orgEmpobj.getEmpId();
		return EnEmpId;
	}
}
