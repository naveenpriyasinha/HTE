package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtlHst;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxn;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ApproveFamilyDtlsDAOImpl  extends GenericDaoHibernateImpl implements ApproveFamilyDtlsDAO
{
	private static final Log logger = LogFactory.getLog(ApproveFamilyDtlsDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public ApproveFamilyDtlsDAOImpl(Class type, SessionFactory sessionFactory)
	{
    	super(type);
    	setSessionFactory(sessionFactory);
	}
	
	public List getAllFamilyPendingReq() {			
		logger.info("getAllFamilyPendingReq  for Approve called ");	
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);		
		crit.add(Restrictions.like("actionFlag","P"))
			.add(Restrictions.like("draftFlag", "N"))
			.setProjection(Projections.projectionList()
			.add(Projections.groupProperty("id.reqId"))			
			.add(Projections.groupProperty("createdDate"))
			.add(Projections.groupProperty("orgUserMstByUserId")))			
			.addOrder(Order.asc("createdDate")); 
		familyListObj = crit.list();				
		return familyListObj;
	}

	public List getPendingReqData( long lreqId) {		
		logger.info("getAllFamilyPendingReq  for Approve called  on reqId : " + lreqId);	
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);		
		crit.add(Restrictions.like("draftFlag", "N"))			
			.add(Restrictions.eq("id.reqId", lreqId))
			.addOrder(Order.desc("cmnLookupMstByFmDeadOrAlive.lookupId"))
			.addOrder(Order.asc("fmRelationOther"))
			.addOrder(Order.desc("fmDateOfBirth")); 
		familyListObj = crit.list();					
		return familyListObj;		
	}

	public List getOperationDataFromMst(long empId,long req_Id) {
		logger.info("getAllFamilyPendingReq  for Approve called  on reqId : " + req_Id);
		OrgUserMst orgUEmpMst = new OrgUserMst();
		orgUEmpMst.setUserId(empId);
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);		
		crit.add(Restrictions.eq("orgUserMstByUserId",orgUEmpMst))
			.add(Restrictions.eq("id.reqId",req_Id)); 
		familyListObj = crit.list();					
		return familyListObj;		
	}

	public HrEisFamilyDtl getMasterRecordForDeleteOrUpdate(long member_ID,long lempId) {
		logger.info("getMaster Table Record for update and then Approve : " + member_ID);		
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);		
		crit.add(Restrictions.eq("memberId",member_ID))
			.add(Restrictions.eq("orgUserMstByUserId.userId",lempId)); 
		familyListObj = crit.list();
		if(familyListObj.isEmpty()==false)
		{
			HrEisFamilyDtl ht = (HrEisFamilyDtl) familyListObj.get(0);
			return ht;	
		}
		else
		{
			return null;
		}
	}

	public long getMaxMemberId(long member_ID, long lempId) {
		long srNo=1;
		logger.info("Get Maximum Member Id : " + " lEmpId : " + lempId);
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);		
		crit.add(Restrictions.eq("orgUserMstByUserId.userId",lempId))
			.setProjection(Projections.projectionList()
			.add(Projections.max("memberId"))); 
		familyListObj = crit.list();
		Object row= (Object) familyListObj.get(0);		
		if(row != null){
			srNo=Long.parseLong(row.toString().trim());
			srNo=srNo+1;			
		}				
		return srNo;
	}

	public List getEmpApprovedFamilyDtlsForApprovePage(long reqId) 
	{
		logger.info("get ApprovedDtsl ,userId and show on Approve Page, Family Dtls , getEmpApprovedFamilyDtlsForApprovePage  " + reqId);
		List appEmpEduDtls=new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("id.reqId",reqId))
			.addOrder(Order.desc("fmDateOfBirth"));
		ListIterator l = crit.list().listIterator();
		while(l.hasNext())
		{
			HrEisFamilyDtlTxn eisEmpFamilyTrn = (HrEisFamilyDtlTxn)l.next();
			Criteria crit1 = hibSession.createCriteria(HrEisFamilyDtlHst.class);
			//crit1.add(Expression.le("updatedDate", eisEmpFamilyTrn.getCreatedDate()))
			crit1.add(Expression.disjunction()
								.add(Restrictions.conjunction()
										.add(Restrictions.isNotNull("updatedDate"))
										.add(Restrictions.le("updatedDate", eisEmpFamilyTrn.getCreatedDate())))
								.add(Restrictions.conjunction()
										.add(Restrictions.isNull("updatedDate"))
										.add(Restrictions.le("createdDate", eisEmpFamilyTrn.getCreatedDate()))))
				.add(Restrictions.eq("orgUserMstByUserId",eisEmpFamilyTrn.getOrgUserMstByUserId()))
				.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("id.memberId"))				
				.add(Projections.max("id.trnCounter")))
				.addOrder(Order.desc("cmnLookupMstByFmDeadOrAlive.lookupId"))
				.addOrder(Order.asc("fmRelationOther"))
				.addOrder(Order.desc("fmDateOfBirth"));
			List temp = crit1.list();
			ListIterator  li  = temp.listIterator();
			while(li.hasNext())
			{
				Object[] eisEmpQualificationhst = (Object[])li.next();		
				Criteria crit2 = hibSession.createCriteria(HrEisFamilyDtlHst.class);
				crit2.add(Restrictions.eq("id.memberId",eisEmpQualificationhst[0]))
					 .add(Restrictions.ne("deleteFlag","Y"))
				     .add(Restrictions.eq("id.trnCounter",eisEmpQualificationhst[1]))
				     .addOrder(Order.desc("cmnLookupMstByFmDeadOrAlive.lookupId"))
				     .addOrder(Order.asc("fmRelationOther"))
				     .addOrder(Order.desc("fmDateOfBirth")); 
				List temp1= crit2.list();
				if(temp1.isEmpty()==false)
				{
					if(temp1.get(0)==null){logger.info("===null===");}
					else	
					{
						appEmpEduDtls.add(temp1.get(0));
					}
				}
			}
			break;
		}
		return appEmpEduDtls;
	}
	
	public long getCurrentRequestIdFromCorrsId(long corrsId)//Created By Sunil
	{
		logger.info("corrsId===========In DaoImpl=="+corrsId);
		long reqId=0;
		List lstCurrentReq = new ArrayList();							
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("corrsId",corrsId));
		lstCurrentReq = crit.list();
		logger.info("lstCurrentReq Size====="+lstCurrentReq.size());
		if(!lstCurrentReq.isEmpty())
		{
			HrEisFamilyDtlTxn eisEmpFamilyTrn = (HrEisFamilyDtlTxn)lstCurrentReq.get(0);		
			reqId=eisEmpFamilyTrn.getId().getReqId();
		} 
		logger.info("reqId===========In DaoImpl=="+reqId);
		return reqId;
	}
	
	public List getFamilyPendingDtlsOnReqId(long reqId)//Created By Sunil 
	{
		logger.info("getNomineePendingDtlsOnReqId  for Approve,  ApproveFamilyDtlsDAOImpl called :::  "+reqId);	
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);		
		crit.add(Restrictions.eq("id.reqId",reqId)); 
		familyListObj = crit.list();				
		return familyListObj;
	}
}
