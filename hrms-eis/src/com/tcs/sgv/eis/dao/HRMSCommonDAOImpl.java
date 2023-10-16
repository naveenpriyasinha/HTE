package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpDsgnMpg;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class HRMSCommonDAOImpl extends GenericDaoHibernateImpl implements HRMSCommonDAO{

	public HRMSCommonDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	public OrgUserpostRlt getOrgUserPostRltByUserIdAndDate(long userId, Date appliedDate) 
	{
		OrgUserpostRlt orgUserpostRlt=null;
		try
		{
			List arCurrentPostingDataOnHeldByUserId= null;	
			Session hibSession = getSession();
			Criteria crit = hibSession.createCriteria(OrgUserpostRlt.class);
			crit.add(Restrictions.eq("orgUserMst.userId", userId))
				.add(Restrictions.disjunction()
						.add(Restrictions.conjunction()
								.add(Restrictions.isNotNull("endDate"))
								.add(Restrictions.le("startDate",appliedDate))
								.add(Restrictions.ge("endDate",appliedDate))
						)
						.add(Restrictions.conjunction()
								.add(Restrictions.isNull("endDate"))
								.add(Restrictions.le("startDate",appliedDate))
						)
				);
			crit.createCriteria("cmnLookupUserPostType").add(Restrictions.eq("lookupName", "Primary_Post"));
			crit.addOrder(Order.asc("startDate"));	
															
			arCurrentPostingDataOnHeldByUserId = crit.list();
			logger.info("========in dao PostVOList========"+arCurrentPostingDataOnHeldByUserId.size());
			
			if(arCurrentPostingDataOnHeldByUserId!=null && arCurrentPostingDataOnHeldByUserId.size()>0)
				orgUserpostRlt = (OrgUserpostRlt)arCurrentPostingDataOnHeldByUserId.get(0);
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		
		return orgUserpostRlt;
	}
	public OrgEmpDsgnMpg getEmpDesignationByUserIdAndDate(long empId, Date appliedDate) 
	{
		OrgEmpDsgnMpg orgEmpDsgnMpg = null;
		try
		{
			List orgEmpDsgnMpgList= null;	
			Session hibSession = getSession();
			Criteria crit = hibSession.createCriteria(OrgEmpDsgnMpg.class);
			crit.add(Restrictions.eq("orgEmpMst.empId", empId))
				.add(Restrictions.disjunction()
						.add(Restrictions.conjunction()
								.add(Restrictions.isNotNull("endDate"))
								.add(Restrictions.le("startDate",appliedDate))
								.add(Restrictions.ge("endDate",appliedDate))
						)
						.add(Restrictions.conjunction()
								.add(Restrictions.isNull("endDate"))
								.add(Restrictions.le("startDate",appliedDate))
						)
				);
				
															
			orgEmpDsgnMpgList = crit.list();
			logger.info("========in dao orgEmpDsgnMpgList========"+orgEmpDsgnMpgList.size());
			
			if(orgEmpDsgnMpgList!=null && orgEmpDsgnMpgList.size()>0)
				orgEmpDsgnMpg = (OrgEmpDsgnMpg)orgEmpDsgnMpgList.get(0);
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		
		return orgEmpDsgnMpg;
	}
}
