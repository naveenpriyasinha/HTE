/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	May 25, 2011		Meeta Thacker								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * Class Description - 
 *
 *
 * @author Meeta Thacker
 * @version 0.1
 * @since JDK 5.0
 * May 25, 2011
 */
public class TickerMessageDAOImpl extends GenericDaoHibernateImpl implements TickerMessageDAO{

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	public TickerMessageDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}
	public String getTickerMessage(){
		String strTickerMessage = null;
		List lstMessages = null;
		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery
				.append(" SELECT message1,message2,message3 ");
		lSBQuery.append(" FROM TickerMessage ORDER BY dcpsTickerMessageIdPk DESC LIMIT 1");
		
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		
		lstMessages = lQuery.list();
		Object obj[] = (Object[])lstMessages.get(0);
		strTickerMessage = obj[0].toString() + " --**-- " + obj[1].toString() + " --**-- " + obj[2].toString();
		return strTickerMessage;
	}
}
