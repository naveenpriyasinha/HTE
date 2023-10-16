package com.tcs.sgv.allowance.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.allowance.valueobject.HrPayArgumentMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;


public class HrPayArgMstDAO extends GenericDaoHibernateImpl<HrPayArgumentMst, Long>{

	public HrPayArgMstDAO(Class<HrPayArgumentMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getArgMstData(long argId) {
		
        Criteria objCrt = null;
        List  list = null;        
        Session hibSession = getSession();
    	  
	     
	      Criteria crit = hibSession.createCriteria(HrPayArgumentMst.class);
	      crit.add(Restrictions.like("HrPayArgumentMst.argumentId", argId));
	      list= crit.list();
	             
        return list;
    }
	
	/**
	 * This method will collect all the data of hr_pay_allow_type_mst for perticular allowance code.
	 * @param HrPayAllowTypeMst
	 * @param sessionFactory
	 * @return HrPayAllowTypeMst
	 */
	
		
}
