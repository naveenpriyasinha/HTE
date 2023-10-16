package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisCadreMst;
import com.tcs.sgv.eis.valueobject.HrEisGrpMangMst;

public class CadreMasterDAOImpl extends GenericDaoHibernateImpl<HrEisCadreMst, Long>{

	public CadreMasterDAOImpl(Class<HrEisCadreMst> type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}


	public List<HrEisCadreMst> getCadreData()
	{
		List cadreList = new ArrayList();
		Session hibSession1 = getSession();
		String hql="from HrEisCadreMst ";
        Query query = hibSession1.createQuery(hql.toString());      
        cadreList =query.list();
        logger.info("typeList size:::::::::::::::::::: " + cadreList.listIterator());
        return cadreList;                        
	}



	public List<HrEisCadreMst> getCadreDataForView(long cadreId)
	{
		List cadreList = new ArrayList();
		Session hibSession1 = getSession();
		String hql="from HrEisCadreMst hcm where hcm.cadreId = " + cadreId;
        Query query = hibSession1.createQuery(hql.toString());      
        cadreList =query.list();
        logger.info("typeList size:::::::::::::::::::: " + cadreList.listIterator());
        return cadreList;                        
	}








}
