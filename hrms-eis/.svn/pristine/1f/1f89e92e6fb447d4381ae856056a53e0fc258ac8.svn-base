package com.tcs.sgv.eis.zp.zpDepartmentMst.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;
public class ZpDepartmentDAOImpl extends GenericDaoHibernateImpl implements ZpDepartmentDAO
{
	public ZpDepartmentDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public Long getadminOfficetypeCode(Long seladminDeptId) {
		Session ghibSession = getSession();
		StringBuilder lSBQuery = new StringBuilder();
		List tempList = new ArrayList();
		Long count = 0L;

		lSBQuery.append(" SELECT admintypecode FROM ZpDepartmentMst WHERE admintypecode = :seladminDeptId ");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("seladminDeptId", seladminDeptId);

		tempList = lQuery.list();

		if (tempList != null && tempList.get(0) != null) {
			count = Long.valueOf(tempList.get(0).toString().trim());
		} else {
			count = 0l;
		}

		return count;

	}
	public Long getCountAdminOfficetypeCode(Long seladminDeptId) {
		Session ghibSession = getSession();
		StringBuilder lSBQuery = new StringBuilder();
		List tempList = new ArrayList();
		Long count = 0L;

		lSBQuery.append(" SELECT COUNT(deptId) FROM ZpDepartmentMst WHERE admintypecode = :seladminDeptId ");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("seladminDeptId", seladminDeptId);

		tempList = lQuery.list();

		if (tempList != null && tempList.get(0) != null) {
			count = Long.valueOf(tempList.get(0).toString().trim());
		} else {
			count = 0l;
		}

		return count;

	}
	
	public List getAllAdminDepartmentOfficeData(long langId) {
		Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "from com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst where langId =" + langId 
		+ " order by departmentName";
		Query query = hibSession.createQuery(strQuery);
		list = query.list();

		return list;
	}
	
	public ZpDepartmentMst getDepartmentDtls(long deptId) {
		ZpDepartmentMst zpDepartmentMst = new ZpDepartmentMst();

		Session hibSession = getSession();
		String query1 = "from com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst where deptId =" + deptId; 
		Query sqlQuery1 = hibSession.createQuery(query1);
		zpDepartmentMst = (ZpDepartmentMst) sqlQuery1.uniqueResult();

		return zpDepartmentMst;
	}
	
	public List retriveDepts() 
	{
		List temp=null;
		//hibSession = getSession();
		try
		{		
			Session hibSession = getSession();
			String branchQuery = "select aa.DEPT_ID,aa.DEPT_NAME,aa.DEPT_CODE FROM MST_ZP_DEPT aa";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();
			
			
			logger.error("List Size"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List retriveDepts(String OfcCode) 
	{
		List temp=null;
		//hibSession = getSession();
		try
		{		
			Session hibSession = getSession();
			String branchQuery = "select aa.DEPT_ID,aa.DEPT_NAME,aa.DEPT_CODE FROM MST_ZP_DEPT aa where ADMIN_OFF_TYPE_CODE="+OfcCode;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();
			
			
			logger.error("List Size"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	
	 public List getallDeptName()
	    { 
	    	List temp=null;
	    	Session hibSession = getSession();
	    	try
			{
	    	  
		      String mainquery = " SELECT DEPT_CODE,DEPT_NAME  from ZP_DEPT_NAME_MST";
		      Query sqlQuery= hibSession.createSQLQuery(mainquery);
		      temp= sqlQuery.list();
		      logger.error("DEPt Name:::="+temp.size());
			}
	    	catch(Exception e){
	    			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
	    			e.printStackTrace();
	    	}
		return temp;
	    }	
	

}
