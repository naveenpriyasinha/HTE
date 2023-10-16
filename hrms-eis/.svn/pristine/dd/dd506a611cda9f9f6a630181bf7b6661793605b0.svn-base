package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;

public class ChangePostOfficeDAOImpl extends GenericDaoHibernateImpl<HrPayOfficepostMpg, Long> implements ChangePostOfficeDAO
{
	Log logger = LogFactory.getLog(getClass());

	public ChangePostOfficeDAOImpl(Class<HrPayOfficepostMpg> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	public List getOfficeList(long locId)
	{
		Session hibSession = getSession();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append("select office from ");
		queryBuff.append("OrgDdoMst ddo, DdoOffice office ");
		queryBuff.append("where ddo.locationCode = "+locId+" ");
		queryBuff.append("and office.dcpsDdoCode = ddo.ddoCode ");
		Query query = hibSession.createQuery(queryBuff.toString());
		logger.info("getOfficeList " + query.toString());
		return query.list();
	}
	
	public List getEmployeeListByOfficeId(long currentOffId)
	{
		Session hibSession = getSession();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append("SELECT empMst.EMP_FNAME, empMst.EMP_MNAME, empMst.EMP_LNAME , empMst.USER_ID, userpost.POST_ID ");
		queryBuff.append("FROM HR_PAY_OFFICEPOST_MPG office, ORG_USERPOST_RLT userpost, ORG_EMP_MST empMst ");
		queryBuff.append("where office.OFFICE_ID = "+currentOffId+" ");
		queryBuff.append("and userpost.POST_ID = office.POST_ID ");
		queryBuff.append("and userpost.ACTIVATE_FLAG = 1 ");
		queryBuff.append("and empMst.USER_ID = userpost.USER_ID ");
		Query query = hibSession.createSQLQuery(queryBuff.toString());
		logger.info("getEmployeeListByOfficeId " + query.toString());
		return query.list();
	}
	
	public int updateHrPayOfficePostMpg(String postIds, long currentOffId, long newOffId)
	{
		Session hibSession = getSession();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append("update HR_PAY_OFFICEPOST_MPG set OFFICE_ID = "+newOffId+" where POST_ID in ("+postIds+") and OFFICE_ID = "+currentOffId+" ");
		Query query = hibSession.createSQLQuery(queryBuff.toString());
		logger.info("updateHrPayOfficePostMpg " + query.toString());
		return query.executeUpdate();
	}
	
	public int updaateMstDcpsEmp(String postIds, long currentOffId, long newOffId)
	{
		Session hibSession = getSession();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append("update MST_DCPS_EMP set CURR_OFF = "+newOffId+" where ORG_EMP_MST_ID in ");
		queryBuff.append("( SELECT emp.EMP_ID FROM ORG_USERPOST_RLT userpost, ORG_EMP_MST emp ");
		queryBuff.append("where userpost.POST_ID in ("+postIds+") and userpost.ACTIVATE_FLAG = 1 ");
		queryBuff.append("and emp.USER_ID = userpost.USER_ID) and CURR_OFF = "+currentOffId+" ");
		Query query = hibSession.createSQLQuery(queryBuff.toString());
		logger.info("updateHrPayOfficePostMpg " + query.toString());
		return query.executeUpdate();
	}
}
