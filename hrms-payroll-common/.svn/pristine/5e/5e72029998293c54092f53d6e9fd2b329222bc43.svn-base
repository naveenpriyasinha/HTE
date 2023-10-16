package com.tcs.sgv.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.HrDeclarationFlagDtlsData;
import com.tcs.sgv.common.valueobject.HrDeclarationFlagMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class HrDeclarationFlagMstDAOImpl extends GenericDaoHibernateImpl implements  HrDeclarationFlagMstDAO 
{

	public HrDeclarationFlagMstDAOImpl(Class type, SessionFactory sessionFactory)
	{
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllDeclarationOnApplicationIdAndLangId(long appId, long langId)
	{
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(langId);		
		List declDataLstObj = new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrDeclarationFlagMst.class);
		crit.add(Restrictions.eq("applicationId", appId))
			.add(Restrictions.eq("displayFlag","Y"))
			.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
		declDataLstObj =crit.list();		
		return declDataLstObj; 
	}
	public List getDeclarationComponentLabelOnDeclarationIdAndLangId(long declarationId, long langId)
	{
		List declDataLstObj = new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrDeclarationFlagDtlsData.class);
		crit.add(Restrictions.eq("hrDeclarationFlagMst.declarationId", declarationId))
			.add(Restrictions.eq("cmnLanguageMst.langId", langId))
			.addOrder(Order.asc("order"));
		declDataLstObj =crit.list();		
		return declDataLstObj;
	}
}
