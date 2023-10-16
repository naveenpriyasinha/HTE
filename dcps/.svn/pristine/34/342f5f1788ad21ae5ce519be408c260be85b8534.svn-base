/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 30, 2011		Meeta Thacker								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
 * @since JDK 5.0 Apr 30, 2011
 */
public class DARateDAOImpl extends GenericDaoHibernateImpl implements DARateDAO {

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionproc/PensionCaseConstants");

	public DARateDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}

	public List getAllDARates() {

		List listDARates = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" SELECT DA.daRateIdPk,LM.lookupDesc,DA.daRate,DA.effectiveFromDate,DA.applicableToDate,DA.status ");
		lSBQuery.append(" FROM DARate DA,CmnLookupMst LM where DA.payCommission = LM.lookupId order by DA.status DESC");

		lQuery = ghibSession.createQuery(lSBQuery.toString());

		gLogger.error("lQuery is;" +lQuery.toString());
		listDARates = lQuery.list();

		return listDARates;
	}

	public void UpdatePreviousDARate(Date dtToDate, String lStrPayComm) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" Update DARate set applicableToDate = :ToDate,status = 0 where applicableToDate IS NULL and payCommission = :payCommission");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		gLogger.error("UpdatePreviousDARate is;" +lQuery.toString());
		lQuery.setParameter("ToDate", dtToDate);
		gLogger.error("dtToDate is;" +dtToDate);
		lQuery.setParameter("payCommission", lStrPayComm);
		gLogger.error("payCommission is;" +lStrPayComm);

		lQuery.executeUpdate();
	}
	
	/*public int daRate(String effdate, String appdate, String paycommission) {
		int drate;
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		
		lSBQuery.append("SELECT DA.DA_RATE FROM MST_DCPS_DA_RATE DA,CMN_LOOKUP_MST LM where DA.PAY_COMMISSION = LM.LOOKUP_ID and DA.EFFECTIVE_FROM <=:effdate and DA.APPLICABLE_TO >=:appdate and LM.LOOKUP_DESC=:paycommission order by DA.status DESC");
		lQuery.setParameter("effdate",effdate);
		gLogger.error("effective from date;" +effdate);
		lQuery.setParameter("appdate",appdate);
		gLogger.error("applicable to date;" +appdate);
		lQuery.setParameter("paycommision", paycommission);
		gLogger.error("paycommission is;" +paycommission);
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		drate= lQuery.executeUpdate();
		return drate;
	}*/

}
