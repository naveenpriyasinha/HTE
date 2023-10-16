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
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisQualDtl;
import com.tcs.sgv.eis.valueobject.HrEisQualDtlHst;
import com.tcs.sgv.eis.valueobject.HrEisQualDtlTxn;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ApproveEducationDAOImpl extends GenericDaoHibernateImpl implements ApproveEducationDAO
{	
	private static final Log logger = LogFactory.getLog(ApproveEducationDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public ApproveEducationDAOImpl(Class type, SessionFactory sessionFactory)
	{
    	super(type);
    	setSessionFactory(sessionFactory);
	}
	
	public List getAllPendingRequest()
	{
		List pendingReqlstObj = new ArrayList();							
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.like("actionFlag","P"))
			.add(Restrictions.like("draftFlag", "N"))
			.setProjection(Projections.projectionList()
			.add(Projections.groupProperty("id.reqId"))			
			.add(Projections.groupProperty("createdDate"))
			.add(Projections.groupProperty("orgUserMstByUserId")))			
			.addOrder(Order.desc("createdDate")); 
		pendingReqlstObj = crit.list();			
		return pendingReqlstObj;
	}
	
	public List getPendingRequestonReqIdandEmpId(long reqId)
	{
		List pendingReqlstObj = new ArrayList();							
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("id.reqId",reqId))
			.add(Restrictions.like("draftFlag", "N"))
			.addOrder(Order.asc("yearOfPassing"));
		pendingReqlstObj = crit.list();
		return pendingReqlstObj;
	}
	
	public List getEmpMaxReqID(OrgUserMst empId) {
		logger.info("get Emp Max SR NO For Approval , Education Dtls , ApproveEducationDAOImpl   " + empId);	
		List FindMaxSrNo=new ArrayList();				
		Session hibSession = getSession();						
		Criteria crit = hibSession.createCriteria(HrEisQualDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", empId))
			.setProjection(Projections.projectionList()
			.add(Projections.max("srNo"))); 
		FindMaxSrNo = crit.list();			
		return FindMaxSrNo;
	}
	
	public List getEmpEduDtslForApprove(OrgUserMst empId, long parentId) 
	{
		logger.info("get Emp Dtls For Approval , Education Dtls , ApproveEducationDAOImpl  " + empId + "   Row no : "+ parentId);
		List updateEmpEduDtls=new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisQualDtl.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empId))
			.add(Restrictions.eq("srNo", parentId));
		updateEmpEduDtls = crit.list();
		return updateEmpEduDtls;
	}
	
	public List getApproveRequestonEmpId(OrgUserMst orgUserMst,long reqId) 
	{
		logger.info("get  Approved Dtsl for this user Id and show on Approve Page, Education Dtls , ApproveEducationDAOImpl  " + orgUserMst.getUserId());
		List appEmpEduDtls=new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId.userId", orgUserMst.getUserId()))
			.add(Restrictions.eq("id.reqId",reqId));					
		
		ListIterator l = crit.list().listIterator();
		while(l.hasNext())
		{
			HrEisQualDtlTxn eisEmpQualification = (HrEisQualDtlTxn)l.next();			
			Criteria crit1 = hibSession.createCriteria(HrEisQualDtlHst.class);
			//crit1.add(Expression.le("updatedDate", eisEmpQualification.getCreatedDate()))
			crit1.add(Expression.disjunction()
								.add(Restrictions.conjunction()
										.add(Restrictions.isNotNull("updatedDate"))
										.add(Restrictions.le("updatedDate", eisEmpQualification.getCreatedDate())))
								.add(Restrictions.conjunction()
										.add(Restrictions.isNull("updatedDate"))
										.add(Restrictions.le("createdDate", eisEmpQualification.getCreatedDate()))))
				.add(Restrictions.eq("orgUserMstByUserId.userId", orgUserMst.getUserId()))
				.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("id.srNo"))
				.add(Projections.max("id.trnCounter")))
				.addOrder(Order.asc("yearOfPassing"));
			List temp = crit1.list();	
			ListIterator  li  = temp.listIterator();
			while(li.hasNext())
			{
				Object[] eisEmpQualificationhst = (Object[])li.next();		
				Criteria crit2 = hibSession.createCriteria(HrEisQualDtlHst.class);
				crit2.add(Restrictions.like("id.srNo",eisEmpQualificationhst[0]))
					 .add(Restrictions.ne("deleteFlag","Y"))
				     .add(Restrictions.like("id.trnCounter",eisEmpQualificationhst[1]))
					 .addOrder(Order.asc("yearOfPassing"));	
				List temp1= crit2.list(); 
				if(!temp1.isEmpty())
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
	
	public long getEmpIdFromReqId(long reqId) {
		long empId=0;
		List pendingReqlstObj = new ArrayList();							
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("id.reqId",reqId))
			.add(Restrictions.like("draftFlag", "N")); 
		pendingReqlstObj = crit.list();
		if(pendingReqlstObj.isEmpty()==false)
		{
			HrEisQualDtlTxn eisEmpQualificationTrn = (HrEisQualDtlTxn)pendingReqlstObj.get(0);		
			empId=eisEmpQualificationTrn.getOrgUserMstByUserId().getUserId();
		} 
		return empId;
	}
	
	public List getApproveRequestonUserId(OrgUserMst orgUserMst) {
		logger.info("get  Approved Dtsl for this Emp Id and show on Approve Page, Education Dtls , ApproveEducationDAOImpl  " + orgUserMst);
		List appEmpEduDtls=new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisQualDtl.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.ne("deleteFlag","Y"));	
		appEmpEduDtls = crit.list();
		return appEmpEduDtls;
	}

	public List getModEmpRlt(long reqId, HrModMst modMst)
	{
		List modEmp=new ArrayList();
		Session hibSession=getSession();
		Criteria crit=hibSession.createCriteria(HrModEmpRlt.class);
		crit.add(Restrictions.eq("reqId", reqId))
		.add(Restrictions.eq("hrModMst", modMst));
		modEmp=crit.list();
		
		return modEmp;
	}
	
	public List getEmpDtls(long userId)
	{
		OrgUserMst orgUserObj = new OrgUserMst();
		orgUserObj.setUserId(userId);
		List otherDtl = new ArrayList();
		Session hibSession = getSession();
		Criteria EmpCrit = hibSession.createCriteria(OrgEmpMst.class);
		EmpCrit.add(Restrictions.eq("orgUserMst", orgUserObj));
		otherDtl = EmpCrit.list();
		return otherDtl;
	}
	
	public List getEmpOtherDtls(long empId)
	{ 
		List valAmt=new ArrayList();
		Session hibSession = getSession();
		Criteria EmpCrit1 = hibSession.createCriteria(HrEisOtherDtls.class);
	    	EmpCrit1 .add(Restrictions.eq("hrEisEmpMst.empId",empId));
		valAmt=EmpCrit1.list();
		logger.info("getEmpOtherDtls Executed. Search in HrEisOtherDtls. Size of list:::"+valAmt.size());
		return valAmt;
	}
	
	public long getCurrentRequestIdFromCorrsId(long corrsId)//Created By Sunil
	{
		logger.info("corrsId===========In DaoImpl=="+corrsId);
		long reqId=0;
		List lstCurrentReq = new ArrayList();							
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisQualDtlTxn.class);
		crit.add(Restrictions.eq("corrsId",corrsId));
		lstCurrentReq = crit.list();
		logger.info("lstCurrentReq Size====="+lstCurrentReq.size());
		if(!lstCurrentReq.isEmpty())
		{
			HrEisQualDtlTxn eisEmpQualificationTrn = (HrEisQualDtlTxn)lstCurrentReq.get(0);		
			reqId=eisEmpQualificationTrn.getId().getReqId();
		} 
		logger.info("reqId===========In DaoImpl=="+reqId);
		return reqId;
	}
}
