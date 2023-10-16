package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtlTxn;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class NomineeDtlsDAOImpl extends GenericDaoHibernateImpl implements NomineeDtlsDAO
{
	private static final Log logger = LogFactory.getLog(NomineeDtlsDAOImpl.class);
	@SuppressWarnings("unchecked")
	public NomineeDtlsDAOImpl(Class type, SessionFactory sessionFactory)
	{
    	super(type);
    	setSessionFactory(sessionFactory);
	}
	static long deleteLookUpId=36;
	static long deactiveLookUpId=19;
	static long activeLookUpId=18;
	static long en_UsLangId=1;
	
	public List getEmpFamilyDtls(OrgUserMst objOrgUserMst)
	{							
		Session hibSession = getSession();																									
		logger.info("getEmpFamilyDtls  for Nominee, NomineeDtlsDAOImpl  called   " +objOrgUserMst.getUserId());	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", objOrgUserMst))
			.add(Restrictions.like("deleteFlag", "N")); 
		
		empFamilyDtlsListObj = crit.list();					
		return empFamilyDtlsListObj;				
	}

	public List getEmpFamilyDtlsForName(OrgUserMst orgEmpMst, String fNameStrObj) 
	{
		Session hibSession = getSession();																									
		logger.info("getEmpFamilyDtlsForName  , NomineeDtlsDAOImpl  called   " +orgEmpMst);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgEmpMst))
			.add(Restrictions.like("fmFirstName", fNameStrObj))
			.add(Restrictions.like("deleteFlag", "N")); 
		empFamilyDtlsListObj = crit.list();	
		return empFamilyDtlsListObj;
	}

	public List getApprovedNomineeData(OrgUserMst orgEmpMst,CmnLookupMst cmnLookupMst) 
	{
		Session hibSession = getSession();																									
		logger.info("getting Nominee Approved  , NomineeDtlsDAOImpl  called   " +orgEmpMst);	
		List empFamilyDtlsListObj=new ArrayList<Object>();													
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgEmpMst))
			.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",cmnLookupMst))
			.add(Restrictions.like("deleteFlag","N"))
			.addOrder(Order.desc("nomnSharePercent")); 
		empFamilyDtlsListObj = crit.list();			
		return empFamilyDtlsListObj;
	}
	
	public List getPendingNomineeData(OrgUserMst orgUserMst,CmnLookupMst cmnLookupMst) 
	{
		Session hibSession = getSession();																									
		logger.info("getting Nominee Pending , NomineeDtlsDAOImpl  called   " +orgUserMst);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",cmnLookupMst))
			.add(Restrictions.like("actionFlag","P"))
			.add(Restrictions.like("draftFlag", "N"))			
			.setProjection(Projections.projectionList()
			.add(Projections.groupProperty("id.reqId"))
			.add(Projections.groupProperty("createdDate"))
			.add(Projections.groupProperty("corrsId")))
			.addOrder(Order.asc("createdDate")); 
		empFamilyDtlsListObj = crit.list();			
		return empFamilyDtlsListObj;
	}

	public List getPendingDtlsOnReqId(long reqId, OrgUserMst orgUserMst) 
	{
		logger.info("get Pending Dtls for View,Pending Request getPendingDtlsOnReqId, NomineeDtlsDAOImpl called   "+orgUserMst + "  reqId " + reqId);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.eq("id.reqId", reqId))
			.add(Restrictions.like("actionFlag", "P"))
			.add(Restrictions.like("draftFlag", "N"));												
			empRequestListObj = crit.list();							
		return empRequestListObj;
	}

	public List getDraftData(OrgUserMst orgUserMst,CmnLookupMst cmnLookupMst)
	{
		Session hibSession = getSession();																									
		logger.info("getting Nominee Draft Data , NomineeDtlsDAOImpl  called   " +orgUserMst);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",cmnLookupMst))
			.add(Restrictions.like("actionFlag", "P"))			
			.add(Restrictions.like("draftFlag", "Y"))			
			.setProjection(Projections.projectionList()					
			.add(Projections.property("createdDate"))
			.add(Projections.groupProperty("id.reqId")))					
			.addOrder(Order.asc("createdDate")); 
		empFamilyDtlsListObj = crit.list();			
		return empFamilyDtlsListObj;
	}
	
	public List getDraftDtlsOnReqId(long reqId, OrgUserMst orgUserMst) 
	{
		logger.info("get Pending Dtls for View,Pending Request getPendingDtlsOnReqId, NomineeDtlsDAOImpl called   "+orgUserMst + "  reqId " + reqId);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.eq("id.reqId", reqId));												
			empRequestListObj = crit.list();							
		return empRequestListObj;
	}

	public List getPendingDtlsOnEmpId(OrgUserMst orgUserMst,CmnLookupMst cmnLookupMst) 
	{
		logger.info("get Pending Dtls for View,Pending Request getPendingDtlsOnReqId, NomineeDtlsDAOImpl called   "+orgUserMst);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",cmnLookupMst))
			.add(Restrictions.like("actionFlag", "P"));												
			empRequestListObj = crit.list();							
		return empRequestListObj;
	}

	public List getNomineeRecordCheckWithFmKey(long userId, long memberId)
	{
		logger.info("get nominee dtls getNomineeRecordCheckWithFmKey, NomineeDtlsDAOImpl called empId :   "+userId + "  memeberId :   "+memberId);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtl.class);
		crit.add(Restrictions.eq("orgUserMstByUserId.userId", userId))			
			.add(Restrictions.like("familyMemberId", memberId))
			.add(Restrictions.eq("deleteFlag", "N"));												
			empRequestListObj = crit.list();		
		return empRequestListObj;
	}
	//IFMS
	public List getNomineeRecordCheckWithFamilyRec(long userId, long memberId)
	{
		logger.info("get nominee dtls getNomineeRecordCheckWithFamilyRec, NomineeDtlsDAOImpl called userId :   "+userId + "  memeberId :   "+memberId);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtl.class);
		crit.add(Restrictions.eq("orgUserMstByUserId.userId", userId))			
			.add(Restrictions.like("familyMemberId", memberId))	
		     .add(Restrictions.like("deleteFlag","N")); 
			empRequestListObj = crit.list();		
		return empRequestListObj;
	}


	public List getDraftDataForShareDtls(OrgUserMst orgUserMst, CmnLookupMst purposeLookupMst)
	{
		Session hibSession = getSession();					
		logger.info("getting Nominee Draft Data , NomineeDtlsDAOImpl  called   " +orgUserMst);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",purposeLookupMst))
			.add(Restrictions.or(
				Restrictions.like("actionFlag","P"),
				Restrictions.like("draftFlag","Y")));			
		empFamilyDtlsListObj = crit.list();			
		return empFamilyDtlsListObj;
	}

	public List getDraftReqForMember(OrgUserMst orgUserMst, CmnLookupMst purposeLookupMst)
	{
		Session hibSession = getSession();	
		long familyMemberId=0;
		logger.info("getDraftReqForMember , NomineeDtlsDAOImpl  called   " +orgUserMst);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",purposeLookupMst))
			.add(Restrictions.like("draftFlag","Y"))
			.add(Restrictions.ne("familyMemberId",familyMemberId));		
		empFamilyDtlsListObj = crit.list();			
		return empFamilyDtlsListObj;
	}

	public List getPendingReqForMember(OrgUserMst orgUserMst, CmnLookupMst purposeLookupMst)
	{
		Session hibSession = getSession();	
		long familyMemberId=0;
		logger.info("getPendingReqForMember , NomineeDtlsDAOImpl  called   " +orgUserMst);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",purposeLookupMst))
			.add(Restrictions.like("actionFlag","P"))
			.add(Restrictions.like("draftFlag","N"))
			.add(Restrictions.ne("familyMemberId",familyMemberId));		
		empFamilyDtlsListObj = crit.list();			
		return empFamilyDtlsListObj;
	}

	public List getApprovedNomineeDataOnUserMst(OrgUserMst orgUserMst) {
		Session hibSession = getSession();		
		logger.info("getApprovedNomineeDataOnUserMst , NomineeDtlsDAOImpl  called   " +orgUserMst);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMst))			
			.add(Restrictions.like("deleteFlag","N")); 	
		empFamilyDtlsListObj = crit.list();			
		return empFamilyDtlsListObj;
	}
	//IFMS
	public List getNomineeDtls(OrgUserMst orgUserMst,CmnLookupMst cmnLookupMst) 
	{
		List empNomineeDtls=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtl.class);
		crit.add(Restrictions.eq("orgUserMstByUserId.userId", orgUserMst.getUserId()))
		.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",cmnLookupMst))
			.add(Restrictions.like("deleteFlag","N")) 
			.addOrder(Order.desc("nomnSharePercent")); 
		empNomineeDtls = crit.list();							
		return empNomineeDtls;
	}
	public List getEmpFamilyDtlsForFamilyNomination(OrgUserMst objOrgUserMst)// Added by sunil 21/07/08
	{							
		Session hibSession = getSession();																									
		logger.info("getEmpFamilyDtls  for Nominee, NomineeDtlsDAOImpl  called   " +objOrgUserMst.getUserId());	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", objOrgUserMst))
			.add(Restrictions.like("deleteFlag", "N"))
			.add(Restrictions.or(Restrictions.like("fmRelationOther",""), Restrictions.isNull("fmRelationOther")));
		empFamilyDtlsListObj = crit.list();					
		return empFamilyDtlsListObj;				
	}

}