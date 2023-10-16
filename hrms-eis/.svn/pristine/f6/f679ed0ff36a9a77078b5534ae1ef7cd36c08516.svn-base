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

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtl;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtlHst;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtlTxn;

public class ApproveNomineeDtlsDAOImpl extends GenericDaoHibernateImpl implements ApproveNomineeDtlsDAO
{
	private static final  Log logger = LogFactory.getLog(ApproveNomineeDtlsDAOImpl.class);
	public ApproveNomineeDtlsDAOImpl(Class type, SessionFactory sessionFactory)
	{
    	super(type);
    	setSessionFactory(sessionFactory);
	}
	
	
	public List getNomineePendingDtls()
    {							
		logger.info("getNomineePendingDtls  for Approve,  ApproveNomineeDtlsDAOImplcalled ");	
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);		
		crit.add(Restrictions.like("actionFlag","P"))
			.add(Restrictions.like("draftFlag", "N"))
			.setProjection(Projections.projectionList()
			.add(Projections.groupProperty("id.reqId"))			
			.add(Projections.groupProperty("createdDate")))			
			.addOrder(Order.asc("createdDate")); 
		familyListObj = crit.list();				
		return familyListObj;				
	}

	public List getNomineePendingDtlsOnReqId(long reqId) {
		logger.info("getNomineePendingDtlsOnReqId  for Approve,  ApproveNomineeDtlsDAOImplcalled :::  "+reqId);	
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);		
		crit.add(Restrictions.eq("id.reqId",reqId))
			.addOrder(Order.desc("nomnSharePercent")); 
		familyListObj = crit.list();				
		return familyListObj;
	}

	public long getMaxMemberIdForNomineeDtls(long userId) 
	{
		logger.info("getMaxMemberIdForNomineeDtls  for Approve,  ApproveNomineeDtlsDAOImplcalled :::  "+userId);	
		long maxMemberId=1;		
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtl.class);		
		crit.add(Restrictions.eq("orgUserMstByUserId.userId",userId))
			.setProjection(Projections.projectionList()
			.add(Projections.max("memberId"))); 
		familyListObj = crit.list();
		Object row= (Object) familyListObj.get(0);		
		if(row != null){
			maxMemberId=Long.parseLong(row.toString().trim());
			maxMemberId=maxMemberId+1;			
		}				
		return maxMemberId;
	}
	
	public List getApprovedNomineeDataForApproveRecord(CmnLookupMst cmnLookupMst, long reqId, long userId) 
	{
		logger.info("get ApprovedDtsl ,userId and show on Approve Page, Nominee Dtls , getApprovedNomineeDataForApproveRecord  " + reqId);
		List appEmpEduDtls=new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",cmnLookupMst))
			.add(Restrictions.eq("id.reqId",reqId));			
		ListIterator l = crit.list().listIterator();
		
		while(l.hasNext())
		{
			HrEisNomineeDtlTxn eisEmpNomineeTrn = (HrEisNomineeDtlTxn)l.next();			
			Criteria crit1 = hibSession.createCriteria(HrEisNomineeDtlHst.class);
			//crit1.add(Expression.le("updatedDate", eisEmpNomineeTrn.getCreatedDate()))
			crit1.add(Expression.disjunction()
					.add(Restrictions.conjunction()
							.add(Restrictions.isNotNull("updatedDate"))
							.add(Restrictions.le("updatedDate", eisEmpNomineeTrn.getCreatedDate())))
					.add(Restrictions.conjunction()
							.add(Restrictions.isNull("updatedDate"))
							.add(Restrictions.le("createdDate", eisEmpNomineeTrn.getCreatedDate()))))
				 .add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",cmnLookupMst))
				 .add(Restrictions.eq("orgUserMstByUserId.userId",userId))
				.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("id.memberId"))
				.add(Projections.max("id.trnCounter")))
				.addOrder(Order.desc("nomnSharePercent")); 
			List temp = crit1.list();
			ListIterator  li  = temp.listIterator();
			while(li.hasNext())
			{
				Object[] eisEmpQualificationhst = (Object[])li.next();		
				Criteria crit2 = hibSession.createCriteria(HrEisNomineeDtlHst.class);
				crit2.add(Restrictions.eq("id.memberId",eisEmpQualificationhst[0]))					 
					 .add(Restrictions.ne("deleteFlag","Y"))
					 .add(Restrictions.like("cmnLookupMstByNomnBenefitTypeId",cmnLookupMst))
				     .add(Restrictions.eq("id.trnCounter",eisEmpQualificationhst[1]))
				     .addOrder(Order.desc("nomnSharePercent")); 
				List temp1= crit2.list(); 
				if(temp1.isEmpty()==false)
				{
					if(temp1.get(0)==null){logger.info("==null==");}
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
		Criteria crit = hibSession.createCriteria(HrEisNomineeDtlTxn.class);
		crit.add(Restrictions.eq("corrsId",corrsId));
		lstCurrentReq = crit.list();
		logger.info("lstCurrentReq Size====="+lstCurrentReq.size());
		if(!lstCurrentReq.isEmpty())
		{
			HrEisNomineeDtlTxn eisEmpQualificationTrn = (HrEisNomineeDtlTxn)lstCurrentReq.get(0);		
			reqId=eisEmpQualificationTrn.getId().getReqId();
		} 
		logger.info("reqId===========In DaoImpl=="+reqId);
		return reqId;
	}
}
