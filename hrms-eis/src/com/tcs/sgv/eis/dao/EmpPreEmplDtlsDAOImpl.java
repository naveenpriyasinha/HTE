package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisPreEmplDtl;

public class EmpPreEmplDtlsDAOImpl extends GenericDaoHibernateImpl implements EmpPreEmplDtlsDAO
{
	public EmpPreEmplDtlsDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List<HrEisPreEmplDtl> getPreEmplDtlVOList(long userId) 
	{
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisPreEmplDtl.class);
		crit.add(Restrictions.eq("orgUserMstByUserId.userId", userId));
		List<HrEisPreEmplDtl> PreEmplDtlVOList= crit.list();
		return PreEmplDtlVOList;
	}
}
