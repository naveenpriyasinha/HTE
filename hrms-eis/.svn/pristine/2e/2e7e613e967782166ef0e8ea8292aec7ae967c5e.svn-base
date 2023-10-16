package com.tcs.sgv.eis.dao;
//Comment By Maruthi For import Organisation.
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrForm16Header;

public class HrForm16HeaderDao extends GenericDaoHibernateImpl<HrForm16Header, Long>{
	Log logger = LogFactory.getLog( getClass() );
 	public HrForm16HeaderDao(Class<HrForm16Header> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	public HrForm16Header getForm16HeaderInfo(long year,long deptId)
 	{
 		HrForm16Header hrForm16Header = new HrForm16Header();
 		
 		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrForm16Header.class); 
        
        objCrt.createAlias("deptId", "cmnLocationMst");
        
        objCrt.add(Restrictions.eq("year", year));
        objCrt.add(Restrictions.eq("cmnLocationMst.locId", deptId));
        
        logger.info("the query is "+objCrt.toString()+" the year is "+year+" the dept is "+deptId);
 		
        hrForm16Header = (HrForm16Header)objCrt.uniqueResult();
        
 		return hrForm16Header;
 	}
 	
 	

}
