package com.tcs.sgv.eis.dao;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisInscplcyDtl;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;


public class EmpMiscellenousDtlDAOImpl  extends GenericDaoHibernateImpl implements EmpMiscellenousDtlDAO
{
	private static final Log logger = LogFactory.getLog(EmpMiscellenousDtlDAOImpl.class);	
	public EmpMiscellenousDtlDAOImpl(Class type, SessionFactory sessionFactory)
	{
    	super(type);
    	setSessionFactory(sessionFactory);
	}
	public List<HrEisBankDtls> getBankDtlVOList(long empId) 
	{
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisBankDtls.class);
		crit.add(Restrictions.eq("hrEisEmpMst.empId", empId));
		List<HrEisBankDtls> BankDtlVOList= crit.list();
		return BankDtlVOList;
	}
	public List<HrEisInscplcyDtl> getInsuranceDtlVOList(long userId) 
	{
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisInscplcyDtl.class);
		crit.add(Restrictions.eq("orgUserMstByOrgUserMstUserIdFk.userId", userId));
		List<HrEisInscplcyDtl> InsuranceDtlVOList= crit.list();
		return InsuranceDtlVOList;
	}
	
	public List getOrgEmpMstVOList(long userId)
	{
		List objList = new ArrayList();	
		Session hibSession =getSession();
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.like("orgUserMst.userId", userId))
			.addOrder(Order.asc("cmnLanguageMst"));			
		objList=crit.list();			
		return objList;
	}
	
	public List<HrEisProofDtl> getPassportDtlVOList(long userId, long lookupId) 
	{
		logger.info("======suerId ======"+ userId);
		logger.info("======lookupId ======"+ lookupId);
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisProofDtl.class);
		crit.add(Restrictions.eq("orgPostMstByUserId.userId", userId));
		crit.add(Restrictions.eq("cmnLookupMst.lookupId", lookupId));
		List<HrEisProofDtl> PassportDtlVOList= crit.list();
		return PassportDtlVOList;
	}
	
	public HrEisProofDtl getEmpPanNo(long userId,long lookupId) 
	{
		HrEisProofDtl eisEmpProofDtls = null;
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisProofDtl.class);
		crit.add(Restrictions.eq("orgPostMstByUserId.userId", userId));
		crit.add(Restrictions.eq("cmnLookupMst.lookupId", lookupId));
		List<HrEisProofDtl> PanDtlVOList= crit.list();
		if (!PanDtlVOList.isEmpty())
		{
			eisEmpProofDtls = PanDtlVOList.get(0);
		}
		return eisEmpProofDtls;
	}
		
	public List getHrEmpMstList(long empId)
	{
		List objList = new ArrayList();	
		Session hibSession =getSession();
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.like("orgEmpMst.empId", empId));
		objList=crit.list();			
		return objList;
	}
}
