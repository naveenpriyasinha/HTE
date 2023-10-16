package com.tcs.sgv.eis.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.posting.valueobject.HrEisPostingOrderDtl;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisBiometricDtl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisLangPrfcncyDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpcontactMst;

public class EmpPersonalInfoDaoImpl extends GenericDaoHibernateImpl implements EmpPersonalInfoDao
{
		private static final Log logger = LogFactory.getLog( EmpPersonalInfoDaoImpl.class );
		@SuppressWarnings("unchecked")
		public EmpPersonalInfoDaoImpl(Class type, SessionFactory sessionFactory)
		{
				super(type);
				setSessionFactory(sessionFactory);
		}
	
		public List getEmpIdData(long EmpId,CmnLanguageMst cmnLanguageMst)
	    {
	 		List list =new ArrayList();
	 		
	 		OrgEmpMst orgEmpMst = new OrgEmpMst();
	 		orgEmpMst.setEmpId(EmpId);
	 		
	 		Session hibSession = getSession();
	      	Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
	 	    crit.add(Restrictions.eq("orgEmpMst", orgEmpMst)); 
	 	    list= crit.list();
	        return list;
	    }
	
	public List getOrgEmpMstVOList(long userId) {
		List objList = new ArrayList();	
		Session hibSession =getSession();
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.like("orgUserMst.userId", userId));
		objList=crit.list();			
		return objList;
	}
	
	public List getEmpContactData(long empId) {
		logger.info("EMP ID For getEmpContactData "+empId);
		List objList = new ArrayList();	
		Session hibSession =getSession();
		Criteria crit = hibSession.createCriteria(OrgEmpcontactMst.class);
		crit.add(Restrictions.like("orgEmpMstByEmpId.empId", empId));
		
		OrgEmpcontactMst element=null;
		objList=crit.list();	
		
		for (Iterator iter = objList.iterator(); iter.hasNext();)
		{
			element = (OrgEmpcontactMst) iter.next();
			element.getEmpContactId();
			element.getCmnLookupMst().getLookupId();
			element.getContactNumber();
			
			/*logger.info("getEmpContactId Element of OrgEmpcontactMst :: "+element.getEmpContactId());
			logger.info("getLookupId Element of OrgEmpcontactMst :: "+element.getCmnLookupMst().getLookupId());
			logger.info("ContactNumber Element of OrgEmpcontactMst :: "+element.getContactNumber());*/
		}
		logger.info("Size of List :: "+objList.size());
		return objList;
	}
	

	public HrEisBiometricDtl getBiomatricDetails(long bioId) {
		List objList = new ArrayList();	
		Session hibSession =getSession();
		Criteria crit = hibSession.createCriteria(HrEisBiometricDtl.class);
		crit.add(Restrictions.like("biometricId", bioId));
		HrEisBiometricDtl element=null;
		objList=crit.list();
		if(objList.isEmpty()==false)
		{
			for (Iterator iter = objList.iterator(); iter.hasNext();)
			{
				element = (HrEisBiometricDtl) iter.next();
				element.getBiometricId();
			}
			return (HrEisBiometricDtl) objList;
		}
		else
		{ 
			return null; 
		}		
	}
	public OrgEmpMst getOrgEmpMstVO(long empid)
	{
		List list =new ArrayList();
 		Session hibSession = getSession();	    
 		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
 	    crit.add(Restrictions.like("empId", empid)); 	 	    
 	    list= crit.list();
 		OrgEmpMst orgEmpMst= (OrgEmpMst)list.get(0);
		return orgEmpMst;
	}
	public List getAllEmpKnownLanguage(long userId) 
	{
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(HrEisLangPrfcncyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId.userId",userId));
	    list= crit.list();	
	    return list;
	}
	public HrEisEmpMst getHrEisEmpMstDtls(long empid)
	{
		List list =new ArrayList();
 		Session hibSession = getSession();	    
 		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
 	    crit.add(Restrictions.like("orgEmpMst.empId", empid));
 	    list= crit.list();
 	    if(list.isEmpty()==false)
 	    {
 	    	HrEisEmpMst hrEisEmpMst = (HrEisEmpMst)list.get(0);
 	    	return hrEisEmpMst;	
 	    }			
 	    return null;
	}	

//Change For Inserting Employment Status In Hr_Eis_Emp_Mst
	public HrEisPostingOrderDtl getPostingOrderDtls(long userId ,long ReasonId)
	{
		logger.info("ReasonId  ======================"+ReasonId);
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(HrEisPostingOrderDtl.class);
	    crit.add(Restrictions.eq("orgUserMstByUserId.userId", userId))
			.add(Restrictions.eq("cmnLookupMst.lookupId",ReasonId));
	   
	    list= crit.list();
	    logger.info("Size============================"+list.size());
	    if(!list.isEmpty())
	    {
	    	HrEisPostingOrderDtl objHrEisPostingOrderDtl = (HrEisPostingOrderDtl)list.get(0);
	    	logger.info("objHrEisPostingOrderDtls======="+objHrEisPostingOrderDtl.getPostingDtlsPk());
	    	return objHrEisPostingOrderDtl;	
	    }			
	    return null;
	}	
	
	/*public HdBiometricsMst getBiomatricDetailsByEmpId(long empId) *//**Added By Sunil*//*
	{
		List objList = new ArrayList();	
		Session hibSession =getSession();
		HdBiometricsMst biometricsMstObj=null;
		try
		{
			Criteria crit = hibSession.createCriteria(HdBiometricsMst.class);
			crit.add(Restrictions.eq("orgEmpMstByEmpCase.empId", empId));
			objList=crit.list();
			if(!objList.isEmpty())
			{
				biometricsMstObj = (HdBiometricsMst)objList.get(0);
			}
		}
		catch(Exception e){logger.error(e);}
		return biometricsMstObj;
	}*/
}
