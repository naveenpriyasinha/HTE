package com.tcs.sgv.eis.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrEisBiometricDtl;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxn;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public class HrEisEmpTrnDaoImpl extends GenericDaoHibernateImpl implements HrEisEmpTrnDao
{
		private static final Log logger = LogFactory.getLog( HrEisEmpTrnDaoImpl.class );
		@SuppressWarnings("unchecked")
		public HrEisEmpTrnDaoImpl(Class type, SessionFactory sessionFactory)
		{
				super(type);
				setSessionFactory(sessionFactory);
		}

	/*
	 * (non-Javadoc)
	 * @see com.tcs.sgv.eis.dao.HrEisEmpTrnDao#getEditRequestResult(long)
	
	 */
 	public List getEditRequestResult(long reqId) 
 	{
			logger.info("*********************getEditRequestResult*****************");
			
			List searchEditList =null;
			Session hibSession =getSession();
			Criteria crit = hibSession.createCriteria(HrEisEmpDtlTxn.class);
			crit.add(Restrictions.like("id.reqId", reqId));
			/*Query query =hibSession.createQuery("from HrEisEmpTrn trn where trn.id.reqId =:reqId").setParameter("reqId",reqId);
			searchEditList =query.list();*/
			HrEisEmpDtlTxn element=null;
			searchEditList =crit.list();
								
			for (Iterator iter = searchEditList.iterator(); iter.hasNext();)
			{
				element = (HrEisEmpDtlTxn) iter.next();
				if (element != null && element.getCmnLanguageMstByEmpMotherTongueId() != null)
				{
					element.getCmnLanguageMstByEmpMotherTongueId().getLookupName();
				}
			}
			return searchEditList;
		}


public static long getLangId(ServiceLocator serv, Map objectArgs) 
{
	Map loginMap = (Map) objectArgs.get("baseLoginMap");
	long langId = Long.parseLong(loginMap.get("langId").toString());
	return langId;
}	
	
 	
 	public HrEisEmpMst getObjectOnOrgEmpMst(OrgEmpMst orgEmpMst) 
	{
		List objList = new ArrayList();	
		Session hibSession =getSession();
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.like("orgEmpMst", orgEmpMst));
		objList=crit.list();
		if(objList.isEmpty()==false)
		{
			if(objList.get(0)!=null)
			{
				return (HrEisEmpMst) objList.get(0);
			}
		}
		return null;
	}

	public List getOrgEmpMstVOList(long userId) {
		List objList = new ArrayList();	
		Session hibSession =getSession();
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.like("orgUserMst.userId", userId))
			.addOrder(Order.asc("cmnLanguageMst.langId"));
		objList=crit.list();			
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
	/** Added By Sandip Start */
	public HrEisBiometricDtl getBiomatricDetailsBybioId(long bioId) 
	{
		List objList = new ArrayList();	
		Session hibSession =getSession();
		HrEisBiometricDtl element=null;
		try
		{
			Criteria crit = hibSession.createCriteria(HrEisBiometricDtl.class);
			crit.add(Restrictions.eq("biometricId", bioId));
			objList=crit.list();
			if(!objList.isEmpty())
			{
				element = (HrEisBiometricDtl)objList.get(0);
			}
		}
		catch(Exception e){logger.error(e);}
		return element;
	}
	/** Added By Sandip End */
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
	//public List<HrEisContactTxn> getRequestedContactDtl(long reqId)
 	{
		//List<HrEisContactTxn> contactList =null;
		try
		{
			Session hibSession =getSession();
			//Criteria crit = hibSession.createCriteria(HrEisContactTxn.class);
			//crit.add(Restrictions.eq("reqId", reqId));
			//contactList =crit.list();
		}
		catch (Exception e)
		{
			logger.error("getRequestedContactDtl===>>>  ",e);
		}
		//return contactList;
 	}
}
