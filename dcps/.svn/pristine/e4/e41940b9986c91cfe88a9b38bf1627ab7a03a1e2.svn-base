package com.tcs.sgv.lna.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class LNAApprovedRequestDAOImpl extends GenericDaoHibernateImpl implements LNAApprovedRequestDAO {
	Session ghibSession = null;

	public LNAApprovedRequestDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public List getLNAApprovedRequestList(String lStrLocCode) {

		List lLnaApprovedListCA = new ArrayList();
		List lLnaApprovedListHBA = new ArrayList();
		List lLnaApprovedListMCA = new ArrayList();
		StringBuilder lSBQueryCA = new StringBuilder();
		StringBuilder lSBQueryHBA = new StringBuilder();
		StringBuilder lSBQueryMCA = new StringBuilder();

		lSBQueryCA.append("SELECT CA.transactionId,CA.applicationDate,CA.sevaarthId,ME.name,CA.amountSanctioned,CA.advanceType,CLM.lookupName");
		lSBQueryCA.append(" FROM MstEmp ME,MstLnaCompAdvance CA,CmnLookupMst CLM,OrgDdoMst DDO");
		lSBQueryCA.append(" where CA.statusFlag = 'A' AND ME.sevarthId = CA.sevaarthId AND CA.advanceSubType = CLM.lookupId");
		lSBQueryCA.append(" AND ME.ddoCode = DDO.ddoCode AND DDO.hodLocCode = :LocCode");
		Query lQueryCA = ghibSession.createQuery(lSBQueryCA.toString());
		lQueryCA.setParameter("LocCode", lStrLocCode);
		lLnaApprovedListCA = lQueryCA.list();

		lSBQueryHBA.append("SELECT HA.transactionId,HA.applicationDate,HA.sevaarthId,ME.name,HA.amountSanctioned,HA.advanceType,CLM.lookupName");
		lSBQueryHBA.append(" FROM MstEmp ME,MstLnaHouseAdvance HA,CmnLookupMst CLM,OrgDdoMst DDO");
		lSBQueryHBA.append(" where HA.statusFlag = 'A' AND ME.sevarthId = HA.sevaarthId AND HA.advanceSubType = CLM.lookupId");
		lSBQueryHBA.append(" AND ME.ddoCode = DDO.ddoCode AND DDO.hodLocCode = :LocCode");
		Query lQueryHBA = ghibSession.createQuery(lSBQueryHBA.toString());
		lQueryHBA.setParameter("LocCode", lStrLocCode);
		lLnaApprovedListHBA = lQueryHBA.list();

		lSBQueryMCA.append("SELECT MA.transactionId,MA.applicationDate,MA.sevaarthId,ME.name,MA.amountSanctioned,MA.advanceType,CLM.lookupName");
		lSBQueryMCA.append(" FROM MstEmp ME,MstLnaMotorAdvance MA,CmnLookupMst CLM,OrgDdoMst DDO");
		lSBQueryMCA.append(" where MA.statusFlag = 'A' AND ME.sevarthId = MA.sevaarthId AND MA.advanceSubType = CLM.lookupId");
		lSBQueryMCA.append(" AND ME.ddoCode = DDO.ddoCode AND DDO.hodLocCode = :LocCode");
		Query lQueryMCA = ghibSession.createQuery(lSBQueryMCA.toString());
		lQueryMCA.setParameter("LocCode", lStrLocCode);
		lLnaApprovedListMCA = lQueryMCA.list();

		lLnaApprovedListCA.addAll(lLnaApprovedListHBA);
		lLnaApprovedListCA.addAll(lLnaApprovedListMCA);

		return lLnaApprovedListCA;
	}

}
