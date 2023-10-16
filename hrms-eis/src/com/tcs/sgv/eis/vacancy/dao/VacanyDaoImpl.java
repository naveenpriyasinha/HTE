package com.tcs.sgv.eis.vacancy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;

public class VacanyDaoImpl extends GenericDaoHibernateImpl implements VacanyDao{

	List departmentList=new ArrayList();
	
	public VacanyDaoImpl(Class type, SessionFactory sessionFactory){
        super(type);
        setSessionFactory(sessionFactory);
	}
	
	public List getQuarterList(long langId){		
		
		List qtrlist = null;
		try {
			Session hibSession = getSession();
			String query="";
			if(langId == 1)
			{
				query = "from CmnLookupMst as mst where mst.lookup_name='Quarter' and langId= '"+ langId + "'";
			}else{
				query = "from CmnLookupMst as mst where mst.lookup_name='Quarter' and langId= '"+ langId + "'";
			}
			
			Query sqlQuery = hibSession.createQuery(query);
			qtrlist = sqlQuery.list();			

		} catch (Exception e) {			
			logger.error("ERROR in getQuarterList method of vacacny Report >>> ",e);
		}
		return qtrlist;
	}
	
	public List getMonthList(long langId){		
		
		List parentList = null;
		List qtrlist = null;
		long parentLookupId = 0l;
		
		try {
			Session hibSession = getSession();
			String query="Select mst.lookupId from CmnLookupMst as mst where mst.lookupName='Month' and  mst.cmnLanguageMst.langId= '"+ langId + "'";
			
			//sqlInRe = new StringBuffer("select * from cmn_lookup_mst where parent_lookup_id in (select lookup_id from cmn_lookup_mst where lookup_name='Month' and lang_id="+langId+") order by order_no");
			Query sqlQuery = hibSession.createQuery(query);
			parentList = sqlQuery.list();	
			
			for (Iterator iter = parentList.iterator(); iter.hasNext();) {
				Long element = (Long) iter.next();
				parentLookupId = element;
			}
			
			if(langId == 1)
			{
				query = "from CmnLookupMst as mst where mst.parentLookupId="+ parentLookupId +" order by mst.orderNo desc";
			}else{
				query = "from CmnLookupMst as mst where mst.parentLookupId="+ parentLookupId +" order by mst.orderNo desc";
			}
			sqlQuery = hibSession.createQuery(query);
			qtrlist = sqlQuery.list();

		} catch (Exception e) {			
			logger.error("ERROR in getMonthList method of vacacny Report >>> ",e);
		}
		return qtrlist;
	}
	
	public List getLocationList(long deptId){
		
		List locList = new ArrayList();
		Session hibSession = getSession();
		String query="from CmnLocationMst as mst where mst.departmentId ="+deptId;
		Query sqlQuery = hibSession.createQuery(query);
		locList = sqlQuery.list();	
			
		return locList;
	}
	
	public List getDesignationList(long langId){	
		
//		List lstOrgPostDtlsRlt = null;
//		List orgPostDsgnList = new ArrayList();
		List orgDesignList = null; 
		
		Session hibSession = getSession();
		String query="from OrgDesignationMst as mst where mst.cmnLanguageMst.langId =:langId";
		Query sqlQuery = hibSession.createQuery(query).setParameter("langId", langId);
		orgDesignList = sqlQuery.list(); 
		
		return orgDesignList;
	}
	
	public List getDeptList(long langId){
		
		List deptList = new ArrayList();
		Session hibSession = getSession();
		String query="from OrgDepartmentMst as mst where mst.cmnLanguageMst.langId =:langId";
		Query sqlQuery = hibSession.createQuery(query).setParameter("langId", langId);
		deptList = sqlQuery.list();	
			
		return deptList;
	}
	
	public OrgDesignationMst getDesignationMstfromDsgnId(long dsgnId){

		List dsgnList = new ArrayList();
		Session hibSession = getSession();
		OrgDesignationMst orgDesignationMst = new OrgDesignationMst();
		String query="from OrgDesignationMst as mst where mst.dsgnId =:dsgnId";
		Query sqlQuery = hibSession.createQuery(query).setParameter("dsgnId", dsgnId);
		dsgnList = sqlQuery.list();	
			
		if(!dsgnList.isEmpty()){
			orgDesignationMst = (OrgDesignationMst)dsgnList.get(0);
		}
		return orgDesignationMst;
	}
	
	public List getDepartmentName(long langId,OrgDepartmentMst orgDepartmentMst) 
	{
		try{
			departmentList=getAllChildDepts(orgDepartmentMst,langId);
		}catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		return departmentList;
	}

	public List getAllChildDepts(OrgDepartmentMst orgDepartmentMstInRe,long langIdInRe) throws Exception 
	{
		try 
		{
			departmentList.add(orgDepartmentMstInRe);
			long parentDepartmentId=orgDepartmentMstInRe.getDepartmentId();
			List deptList = new ArrayList();
			
			Session hibSession = getSession();
			String query="select mst from  OrgDepartmentMst as mst where mst.parentDepId=:parentDepartmentId and mst.cmnLanguageMst.langId=:langId";
			Query sqlQuery = hibSession.createQuery(query).setParameter("parentDepartmentId", parentDepartmentId).setParameter("langId", langIdInRe);
			deptList = sqlQuery.list();	
			
			OrgDepartmentMst orgDepartmentMst = null;
			if(!deptList.isEmpty()){
				for (Iterator iter = deptList.iterator(); iter.hasNext();) {
					OrgDepartmentMst element = (OrgDepartmentMst) iter.next();
					
					orgDepartmentMst = new OrgDepartmentMst();
					orgDepartmentMst.setParentDepId(element.getParentDepId());
					orgDepartmentMst.setDepartmentId(element.getDepartmentId());
					orgDepartmentMst.setDepCode(element.getDepCode());
					orgDepartmentMst.setDepName(element.getDepName());
					if(element.getParentDepId() != -1){
						getAllChildDepts(orgDepartmentMst,langIdInRe);
					}else{
						return departmentList;
					}
				}
			}
		}
		catch (SQLException e) 
		{
			logger.error("ERROR in getAllChildDepts method of vacacny Report >>> ",e);
		}
		
		return  departmentList;
	}
	
	public List getVacancnyVofromDsgnCode(String dsgnCode){
	
		List list = new ArrayList();
		Session hibSession = getSession();
		String query = "from HrEisVacancyAdminMst mst where mst.dsgnCode='"+dsgnCode+"'";
		Query sqlQuery = hibSession.createQuery(query);
		list = sqlQuery.list();
		return list;
	}
	
	public List getLocationCodeFromDeptId(long deptId){
		
		List locationCode = null;
		Session session = getSession();
		String querystr = "select mst.locationCode from CmnLocationMst mst where mst.departmentId ="+deptId;
		Query query = session.createQuery(querystr);
		locationCode = query.list();
		
		return locationCode;
	}
}
