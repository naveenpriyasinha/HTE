package com.tcs.sgv.common.dao;

import com.tcs.sgv.common.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.Qualification;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.common.valueobject.TrnBillRegister;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class QualificationDAOImpl extends GenericDaoHibernateImpl implements Qualification
{

	public QualificationDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	public List getQualification() throws Exception{
		
		Session hibSession = getSession();
		List lListQuery = new ArrayList();
		
		try {
				
		
			      Query lQuery = hibSession.createSQLQuery("SELECT QUALIFICATION FROM QUALIFICATION");
			      logger.info(lQuery);
				lListQuery = lQuery.list();
		
	} catch (Exception e) {
		logger.error("Exception occured while getting bill type list : " + e, e);
		throw e;
	}
	return lListQuery;
	
}
	public List getQualifications() throws Exception{
		
		Session hibSession = getSession();
		List lListQuery = new ArrayList();
		
		try {
				
		
			      Query lQuery = hibSession.createSQLQuery("SELECT Q_ID,QUALIFICATION FROM QUALIFICATION");
			      logger.info(lQuery);
				lListQuery = lQuery.list();
		
	} catch (Exception e) {
		logger.error("Exception occured while getting bill type list : " + e, e);
		throw e;
	}
	return lListQuery;
	
}
}