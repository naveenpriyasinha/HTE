package com.tcs.sgv.lna.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class LNAComputerAdvanceDAOImpl extends GenericDaoHibernateImpl implements LNAComputerAdvanceDAO {
	Session ghibSession = null;

	public LNAComputerAdvanceDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public List getComputerAdvance(String lStrSevaarthId, Long lLngRequestType) {
		List computerAdvanceList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select computerAdvanceId");
		lSBQuery.append(" FROM MstLnaCompAdvance");
		lSBQuery.append(" WHERE sevaarthId = :sevaarthId AND statusFlag = 'D' AND advanceType = :RequestType");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("sevaarthId", lStrSevaarthId);
		lQuery.setParameter("RequestType", lLngRequestType);
		computerAdvanceList = lQuery.list();
		return computerAdvanceList;
	}

	public List getComAdvanceToDEOApprover(Long lLngComAdvnId) {
		List gpfEmpApproverlist = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select computerAdvanceId");
		lSBQuery.append(" FROM MstLnaCompAdvance");
		lSBQuery.append(" WHERE computerAdvanceId = :ComAdvnId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ComAdvnId", lLngComAdvnId);
		gpfEmpApproverlist = lQuery.list();
		return gpfEmpApproverlist;
	}

	public Boolean requestDataAlreadyExists(String lStrSevaarthId) {
		List CARequest = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select MCA.computerAdvanceId");
		lSBQuery.append(" FROM MstLnaCompAdvance MCA");
		lSBQuery.append(" WHERE MCA.sevaarthId = :SevaarthId AND MCA.statusFlag = 'A'");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("SevaarthId", lStrSevaarthId);
		CARequest = lQuery.list();
		if (CARequest.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean requestPendingStatus(String lStrSevaarthId) {
		List CARequest = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select MCA.computerAdvanceId");
		lSBQuery.append(" FROM MstLnaCompAdvance MCA");
		lSBQuery.append(" WHERE MCA.sevaarthId = :SevaarthId AND MCA.statusFlag = 'F'");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("SevaarthId", lStrSevaarthId);
		CARequest = lQuery.list();
		if (CARequest.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
