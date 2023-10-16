package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
public class HrPayOfficePostMpgDAOImpl extends GenericDaoHibernateImpl<HrPayOfficepostMpg, Long> implements HrPayOfficePostMpgDAO {
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");
	
 	public HrPayOfficePostMpgDAOImpl(Class<HrPayOfficepostMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	public HrPayOfficepostMpg getHrPayOfficepostMpg(long postId)
 	{
 		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("from  HrPayOfficepostMpg officePost where officePost.orgPostMstByPostId.postId="+postId);
		Query query = hibSession.createQuery(strQuery.toString());
		logger.info("query to get HrPayOfficepostMpg from post Id is below  "+strQuery.toString());
		return (HrPayOfficepostMpg)query.uniqueResult();
		
 	}
 	
 	public List getOfficeClass(String postId)
 	{
 		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("SELECT office.POST_ID, ddo.OFFICE_CITY_CLASS FROM HR_PAY_OFFICEPOST_MPG office, MST_DCPS_DDO_OFFICE ddo where office.OFFICE_ID = ddo.DCPS_DDO_OFFICE_MST_ID and office.POST_ID in ("+postId + ")");
		Query query = hibSession.createSQLQuery(strQuery.toString());
		logger.info("query to get HrPayOfficepostMpg from post Id is below  "+strQuery.toString());
		return query.list();
		
 	}
 	public List getOfficeClass(String postId,long locId, long billNo, Date givenDate)
 	{
 		Session hibSession = getSession();
	//	StringBuffer strQuery = new StringBuffer("SELECT office.POST_ID, ddo.OFFICE_CITY_CLASS FROM HR_PAY_OFFICEPOST_MPG office inner join ");//commented by poonam for city class
 		StringBuffer strQuery = new StringBuffer("SELECT office.POST_ID, nvl(Post_List.class,ddo.OFFICE_CITY_CLASS) FROM HR_PAY_OFFICEPOST_MPG office inner join ");
		strQuery.append(payrollBundle.getString("getPostList"));
		strQuery.append(" on Post_List.POST_ID=office.POST_ID, MST_DCPS_DDO_OFFICE ddo where office.OFFICE_ID = ddo.DCPS_DDO_OFFICE_MST_ID ");
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate",givenDate);
		logger.info("query to get HrPayOfficepostMpg from post Id is below  "+strQuery.toString());
		return query.list();
		
 	}
 	
 	
}

