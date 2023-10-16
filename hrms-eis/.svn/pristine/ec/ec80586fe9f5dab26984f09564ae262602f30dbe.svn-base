package com.tcs.sgv.eis.dao;
//Comment By Maruthi For import Organisation.
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrInvestTypeMst;
import com.tcs.sgv.eis.valueobject.HrItSectionMst;

public class InvestmentTypeMstDAOImpl extends GenericDaoHibernateImpl<HrInvestTypeMst, Long> implements InvestmentTypeMstDAO {
	Log logger = LogFactory.getLog(getClass());
	
 	public InvestmentTypeMstDAOImpl(Class<HrInvestTypeMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	public List getInvestmentTypes(CmnLanguageMst cmnLanguageMst) {
 		List lstInvestTypes = new ArrayList();
 		Criteria crtInvestTypes = null;
 		Session hibSession = getSession();
 		crtInvestTypes = hibSession.createCriteria(HrInvestTypeMst.class);
 		crtInvestTypes.add(Restrictions.like("cmnLanguageMst",cmnLanguageMst));
 		crtInvestTypes.setFetchMode("hrInvestTypeMst", FetchMode.JOIN);
 		//crtInvestTypes.createAlias("hrInvestTypeMst", "hrInvestTypeMst");
 		crtInvestTypes.addOrder(Order.asc("investName"));
 		lstInvestTypes = crtInvestTypes.list();
 		return lstInvestTypes; 		
 	}
 	public List getSectionList(CmnLanguageMst cmnLanguageMst) {
 		List lstSections = new ArrayList();
 		Criteria crtSection = null;
 		Session hibSession = getSession();
 		crtSection = hibSession.createCriteria(HrItSectionMst.class);
 		crtSection.add(Restrictions.like("cmnLanguageMst",cmnLanguageMst));
 		lstSections = crtSection.list();
 		return lstSections;
 	}
}
