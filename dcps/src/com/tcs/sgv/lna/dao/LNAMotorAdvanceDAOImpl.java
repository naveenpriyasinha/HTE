package com.tcs.sgv.lna.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class LNAMotorAdvanceDAOImpl extends GenericDaoHibernateImpl implements LNAMotorAdvanceDAO {
	Session ghibSession = null;

	public LNAMotorAdvanceDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public List getMotorAdvance(String lStrSevaarthId, Long lLngRequestType) {
		List motorcarAdvanceList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select motorAdvanceId");
		lSBQuery.append(" FROM MstLnaMotorAdvance");
		lSBQuery.append(" WHERE sevaarthId = :sevaarthId AND statusFlag = 'D' AND advanceType = :RequestType");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("sevaarthId", lStrSevaarthId);
		lQuery.setParameter("RequestType", lLngRequestType);
		motorcarAdvanceList = lQuery.list();
		return motorcarAdvanceList;
	}

	public List getMotorAdvanceToDEOApprover(Long lLngMotorAdvnId) {
		List motorcarAdvanceList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select motorAdvanceId");
		lSBQuery.append(" FROM MstLnaMotorAdvance");
		lSBQuery.append(" WHERE motorAdvanceId = :MotorAdvnId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("MotorAdvnId", lLngMotorAdvnId);
		motorcarAdvanceList = lQuery.list();
		return motorcarAdvanceList;
	}

	public Boolean requestDataAlreadyExists(String lStrSevaarthId, Long lLngRequestType) {
		List motorcarAdvanceList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select motorAdvanceId");
		lSBQuery.append(" FROM MstLnaMotorAdvance");
		lSBQuery.append(" WHERE sevaarthId = :sevaarthId AND statusFlag = 'A' AND advanceType = :RequestType");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("sevaarthId", lStrSevaarthId);
		lQuery.setParameter("RequestType", lLngRequestType);
		motorcarAdvanceList = lQuery.list();
		if (motorcarAdvanceList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean requestPendingStatus(String lStrSevaarthId) {
		List motorcarAdvanceList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select motorAdvanceId");
		lSBQuery.append(" FROM MstLnaMotorAdvance");
		lSBQuery.append(" WHERE sevaarthId = :sevaarthId AND statusFlag = 'F'");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("sevaarthId", lStrSevaarthId);
		motorcarAdvanceList = lQuery.list();
		if (motorcarAdvanceList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
