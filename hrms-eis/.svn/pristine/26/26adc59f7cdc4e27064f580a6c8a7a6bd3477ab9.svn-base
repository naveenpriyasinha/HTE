package com.tcs.sgv.eis.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.CityCatMpg;


public class CityCatMpgDAOImpl extends GenericDaoHibernateImpl<CityCatMpg, Long> {

	public CityCatMpgDAOImpl(Class<CityCatMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	Log logger = LogFactory.getLog(getClass());
	
	public CityCatMpg getCityCat(Long cityId)
    {
		CityCatMpg cityCatInfo = new CityCatMpg();
        Session hibSession = getSession();
        String query1 = "from CityCatMpg where cityId = "
                    + cityId ;
        
        Query sqlQuery1 = hibSession.createQuery(query1);
        cityCatInfo = (CityCatMpg)sqlQuery1.uniqueResult();
        logger.info("query is----"+sqlQuery1);
        return cityCatInfo;
    }
}
