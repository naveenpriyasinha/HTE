package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrItExemptTypeMst;

public class ExemptTypeMstDAOImpl extends GenericDaoHibernateImpl<HrItExemptTypeMst, Long> implements ExemptTypeMstDAO {
	Log logger = LogFactory.getLog(getClass());	
 	public ExemptTypeMstDAOImpl(Class<HrItExemptTypeMst> type, SessionFactory sessionFactory) {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	/**
 	 * This method fetch All Exemption Types in list.
 	 * return @ list
 	 */
 	public List getAllExemptTypeData(CmnLanguageMst cmnLanguageMst) {
        Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrItExemptTypeMst.class);
        objCrt.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
        return objCrt.list();
    }
}
