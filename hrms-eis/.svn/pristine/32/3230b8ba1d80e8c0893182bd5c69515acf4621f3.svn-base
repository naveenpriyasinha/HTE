package com.tcs.sgv.eis.dao;
/*
 * @author 601210
 * 
 */
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisCuriculrDtl;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class CoCurricularDAOImpl extends GenericDaoHibernateImpl implements CoCurricularDAO
{	
	@SuppressWarnings("unchecked")
	public CoCurricularDAOImpl(Class<HrEisCuriculrDtl> type, SessionFactory sessionFactory)
	{
    	super(type);
    	setSessionFactory(sessionFactory);
	}
	static long deleteLookUpId=36;
	static long deactiveLookUpId=19;
	static long activeLookUpId=18;
	static long en_UsLangId=1;
/*
 * (non-Javadoc)
 * @see com.tcs.sgv.eis.personal.dao.CoCurricularDAO#getEmpCoCurricularDetails(long)
 * return List
 */
	public List getEmpCoCurricularDetails(OrgUserMst userId)
	{		
		List empCoCurrDtlsListObj=new ArrayList();				
		Session hibSession = getSession();		
		Criteria crit = hibSession.createCriteria(HrEisCuriculrDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", userId))
			.addOrder(Order.desc("yearId")); 
		empCoCurrDtlsListObj = crit.list();
		return empCoCurrDtlsListObj;
	}
}