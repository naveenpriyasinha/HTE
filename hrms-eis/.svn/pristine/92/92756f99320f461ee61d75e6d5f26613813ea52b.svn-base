package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import net.sf.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.QuarterApproval;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDdoOffice.dao.ZpDDOOfficeMstDAOImpl;
import com.tcs.sgv.eis.zp.zpDdoOffice.valueobject.ZpRltDdoMap;

//Added by Demolisher

public class QuaterAprDAOImpl extends GenericDaoHibernateImpl<QuarterApproval, Long>{
	
	public QuaterAprDAOImpl(Class<QuarterApproval> type,SessionFactory sessionFactory) {
		super(type);
		// TODO Auto-generated constructor stub
		setSessionFactory(sessionFactory);
	}


	
	public List getAllDDOOfficeDtlsData() {
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM QTR_APR";
		
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}
	
	public List getAllDDOOfficeDtlsData(long ddocode) {
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM QTR_APR WHERE RLT_DDO="+ddocode;
		
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}
	
	public long getDDOpostId(long postId)
	{
		
		List<String> list = new ArrayList<String>();
		Session hibSession = getSession(); 
		Query query = hibSession.createSQLQuery("SELECT REPT_DDO_POST_ID FROM RLT_ZP_DDO_MAP WHERE ZP_DDO_CODE =(SELECT DDO_CODE FROM ORG_DDO_MST WHERE LOCATION_CODE="+postId+")");
		list = query.list();
		 if(list!= null && list.size()>0)
	        {
			 postId= Long.parseLong(list.get(0));
	        }
		return postId;
	}
	
	public void statusApprove(long empId) throws Exception
	{
		//Criteria objCrt = null;
		//List list = null;
		int records = -1;
		Session hibSession = getSession();
		try{
		logger.info("UPDATE QTR_APR SET STATUS_FLAG='A' WHERE EMP_ID="+empId);
		String strQuery = "UPDATE QTR_APR SET STATUS_FLAG='A' WHERE EMP_ID="+empId;
		logger.info("UPDATE QTR_APR SET STATUS_FLAG='A' WHERE EMP_ID="+empId);
		Query query = hibSession.createSQLQuery(strQuery);
		//list = query.list();
		 query.executeUpdate();
		}
		catch (Exception e) {
			logger.error(" Error in statusApprove : " + e, e);
			throw e;
		}
	}
	public void statusReject(long empId) throws Exception
	{
		//Criteria objCrt = null;
		List list = null;
		int records = -1;
		Session hibSession = getSession();
		try{
		logger.info("UPDATE QTR_APR SET STATUS_FLAG='R' WHERE EMP_ID="+empId);
		String strQuery = "UPDATE QTR_APR SET STATUS_FLAG='R' WHERE EMP_ID="+empId;
		logger.info("UPDATE QTR_APR SET STATUS_FLAG='R' WHERE EMP_ID="+empId);
		Query query = hibSession.createSQLQuery(strQuery);
		//list = query.list();
		 query.executeUpdate();
		}
		catch (Exception e) {
			logger.error(" Error in statusReject : " + e, e);
			throw e;
		}
		
	}
	public List viewPRdetails(long ddocode)
	{
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM QTR_APR WHERE RLT_DDO="+ddocode;
		
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}
	
}
