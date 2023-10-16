package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisSrvcexamDtl;

public class EmpServiceExaminationDtlsDAOImpl extends GenericDaoHibernateImpl implements EmpServiceExaminationDtlsDAO
{
	public EmpServiceExaminationDtlsDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory); 
	}
	public List<HrEisSrvcexamDtl> getExamDtlVOList(long userId) 
	{
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisSrvcexamDtl.class);
		crit.add(Restrictions.eq("orgUserMst.userId", userId));
		List<HrEisSrvcexamDtl> ExamDtlVOList= crit.list();
		return ExamDtlVOList;
	}
	
}
