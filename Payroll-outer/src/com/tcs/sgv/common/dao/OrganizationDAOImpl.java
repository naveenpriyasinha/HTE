package com.tcs.sgv.common.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class OrganizationDAOImpl extends GenericDaoHibernateImpl<Object,Long> implements OrganizationDAO {
	Log logger = LogFactory.getLog(OrganizationDAOImpl.class);

	public OrganizationDAOImpl(Class<Object> type,SessionFactory sessionFactory)
    {
		super(type);
        setSessionFactory(sessionFactory); 
    }

	/**
	 * This method returns user id by post id 
	 * @param postId 
	 * @return userId 
	 */
	public Long getUserByPostId(Long postId) {
		Long userId = null;
		try {
			//System.out.println("-----------------------------------------11111111111111111111111111111");
			Session hibSession = getSession();
			List resList = hibSession.createSQLQuery("SELECT o.user_id FROM org_userpost_rlt o WHERE o.post_id="+postId).list();
			//System.out.println("-----------------------------------------222222222222222222222");
			Iterator it = resList.iterator();		
			while(it.hasNext())
			{
				Object tuple = (Object)it.next();
				//System.out.println("TUPLLLLLLLLLLLLLLLLL " + tuple);
				userId = new Long(tuple.toString());
				//System.out.println("-----------------------------------------2333333333333333333333333333333333333333333333" + userId);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error("Error occured in OrganizationDAOImpl.getUserByPostId #\n"+ex);
		}

		return userId;
	}
}
