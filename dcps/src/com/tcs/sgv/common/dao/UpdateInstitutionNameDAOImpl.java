package com.tcs.sgv.common.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class UpdateInstitutionNameDAOImpl extends GenericDaoHibernateImpl implements UpdateInstitutionNameDAO{

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;
	
	public UpdateInstitutionNameDAOImpl(Class type , SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	@Override
	public List getAllData(String DDOCodeForname) {

		List lLstEmpSelect = null;
		Query sqlQuery = null;
		StringBuilder lStrQuery = new StringBuilder();
		//Date lDtCurrDate = SessionHelper.getCurDate();

		try {

			lStrQuery.append(" SELECT DDO_CODE,DDO_OFFICE FROM ORG_DDO_MST where DDO_CODE ='"+DDOCodeForname+"' " );
			
			sqlQuery = ghibSession.createSQLQuery(lStrQuery.toString());

			gLogger.info("Query****"+lStrQuery.toString());
			lLstEmpSelect = sqlQuery.list();

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			//throw e;
		}
		return lLstEmpSelect;
	}

	@Override
	public void updateOrgInstName(String orgDdoCode, String orgInstName) {
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		logger.info("---- updateOrgInstName ---"); 
		sb.append("update ORG_DDO_MST set DDO_OFFICE = '"+orgInstName+"'  where DDO_CODE = '"+orgDdoCode+"' ");
		sb1.append("update MST_DCPS_DDO_OFFICE set OFF_NAME = '"+orgInstName+"'  where DDO_CODE = '"+orgDdoCode+"' ");
		logger.info("---- updateOrgInstName DAo---"+sb.toString());
		logger.info("---- updateOrgInstName DAo1---"+sb1.toString());
		Query query = session.createSQLQuery(sb.toString());
		Query query1 = session.createSQLQuery(sb1.toString());
		query.executeUpdate();
		query1.executeUpdate();
		
	}
}
