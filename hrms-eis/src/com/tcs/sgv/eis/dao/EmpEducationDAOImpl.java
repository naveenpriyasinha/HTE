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

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisQualDtl;
import com.tcs.sgv.eis.valueobject.HrEisQualDtlTxn;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmpEducationDAOImpl extends GenericDaoHibernateImpl implements EmpEducationDAO
{

	Log logger = LogFactory.getLog(getClass());
	@SuppressWarnings("unchecked")
	public EmpEducationDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getEmpMaxSrNo(OrgUserMst empID) 
	{
		logger.info("getEmpMaxSrNo EducationDAOImpl  called   "+empID);	
		List empQuaDtlsListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", empID)); 
		empQuaDtlsListObj = crit.list();																										
		return empQuaDtlsListObj;
	}
	//IFMS
	public List getEmpEducationDtls(OrgUserMst orgUserMst) 
	{
		logger.info("getEmpMaxSrNo EducationDAOImpl  called   "+orgUserMst.getUserId());	
		List empQuaDtlsListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId.userId", orgUserMst.getUserId()))
			.add(Restrictions.like("deleteFlag","N"))
			.addOrder(Order.asc("yearOfPassing")); 
		empQuaDtlsListObj = crit.list();																										
		return empQuaDtlsListObj;
	}
	public List getEmpDtlsSortedByDate(OrgUserMst userId) 
	{
		logger.info("getEmpDtlsSortedByDate EducationDAOImpl  called   "+userId);	
		List empQuaDtlsListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", userId))
		.add(Restrictions.ne("deleteFlag","Y"))
		.addOrder(Order.asc("yearOfPassing"));			
		empQuaDtlsListObj = crit.list();																										
		return empQuaDtlsListObj;
	}		
	public List getEmpMaxReqID(OrgUserMst userId) 
	{
		logger.info("getEmpMaxRequest ID EducationDAOImpl  called   "+userId);	
		List empQuaDtlsListObj=new ArrayList();				
		Session hibSession = getSession();						
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.like("orgUserMstByUserId", userId))				
		.setProjection(Projections.projectionList()
				.add(Projections.max("id.reqId"))); 
		empQuaDtlsListObj = crit.list();			
		return empQuaDtlsListObj;
	}		
	public List getDrftDtlsLst(OrgUserMst empID) 
	{
		logger.info("getEmpDraft Lst EducationDAOImpl  called   "+empID);	
		List empQuaListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))
		.add(Restrictions.eq("draftFlag","Y"))
		.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("id.reqId"))
				.add(Projections.groupProperty("createdDate")))			
				.addOrder(Order.desc("createdDate"));
		empQuaListObj = crit.list();			
		return empQuaListObj;			
	}

	public List getEmpRequest(OrgUserMst empID) 
	{
		logger.info("get Created Data and Req ID for Emp Education Pending Request EducationDAOImpl  called   "+empID);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))
		.add(Restrictions.like("actionFlag", "P"))
		.add(Restrictions.like("draftFlag", "N"))			
		.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("id.reqId"))
				.add(Projections.groupProperty("createdDate"))
				.add(Projections.groupProperty("corrsId")))
				.addOrder(Order.desc("createdDate"));
		empRequestListObj = crit.list();							
		return empRequestListObj;			
	}
	public List getPendingRequestLst(long reqId,OrgUserMst empID) 
	{
		logger.info("getEmp Education Other Pending Request DAOImpl  called   "+empID);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))
		.add(Restrictions.like("actionFlag", "P"))
		.add(Restrictions.like("draftFlag", "N"))							
		.add(Restrictions.eq("id.reqId",reqId));							
		empRequestListObj = crit.list();			
		return empRequestListObj;			
	}
	public List getDrftDtlsLstonReqID(OrgUserMst empID, long reqId) 
	{
		logger.info("getEmpDraft Lst DAOImpl  called   "+empID);	
		List empQuaListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))								
		.add(Restrictions.eq("id.reqId",reqId));				
		empQuaListObj = crit.list();						
		return empQuaListObj;
	}		
	public List addOrdeleteDrftDtlsLstReqId(OrgUserMst empID, long request_ID) 
	{
		logger.info("add draft dtls in table DAOImpl  called   "+empID);	
		List empQuaListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))								
		.add(Restrictions.eq("id.reqId",request_ID));				
		empQuaListObj = crit.list();						
		return empQuaListObj;
	}
	public List deleteReqId(OrgUserMst empID, long delete_ID) 
	{			
		logger.info("Delete Emp Education from Master Req Id DAOImpl  called   "+empID);	
		List empQuaDtlsListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", empID))
		.add(Restrictions.ne("deleteFlag","Y"))
		.add(Restrictions.like("srNo",delete_ID)); 
		empQuaDtlsListObj = crit.list();																										
		return empQuaDtlsListObj;
	}
	public List findRequestForthisRecord(long srNo, OrgUserMst userId) 
	{
		logger.info("findReuestForthisRecord  EducationDAOImpl  called  empid:  "+userId + "  SrNo : " + srNo);	
		List empQuaListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", userId))								
		.add(Restrictions.eq("rowNumber",srNo))
		.add(Restrictions.like("actionFlag", "P"));
		empQuaListObj = crit.list();						
		return empQuaListObj;
	}
	public List findParentIdForthisReequest(long request_ID, OrgUserMst empID) 
	{
		logger.info("findParentIdForthisReequest  EducationDAOImpl  called  empid:  "+empID + "  request ID : " + request_ID);	
		List empQuaListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))								
		.add(Restrictions.eq("id.reqId",request_ID))
		.add(Restrictions.like("actionFlag", "P"))
		.add(Restrictions.like("draftFlag", "Y"));
		empQuaListObj = crit.list();						
		return empQuaListObj;			
	}
	public List deleteDraftReqId(OrgUserMst orgEmpMst, long reqId) 
	{
		logger.info("Delete Draft Emp Education from Trn Table EmpEducationDAOImpl  called   "+ orgEmpMst + "  Req ID : " + reqId);	
		List empQuaDraftDtlsListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgEmpMst))
		.add(Restrictions.like("id.reqId",reqId)); 
		empQuaDraftDtlsListObj = crit.list();																										
		return empQuaDraftDtlsListObj;
	}
}