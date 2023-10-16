package com.tcs.sgv.onlinebillprep.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnOnlinebillEmp;

/**
*	TrnOnlinebillEmpDAOImpl
*	Its a DAO for Methods Common to all Bills..
*	
*	@author Keyur Shah, Amit Singh
*	@version 1.0
*/
public class TrnOnlinebillEmpDAOImpl extends GenericDaoHibernateImpl<TrnOnlinebillEmp, Long> 
		implements TrnOnlinebillEmpDAO
{

	Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;
	
	public TrnOnlinebillEmpDAOImpl(Class<TrnOnlinebillEmp> type, SessionFactory sessionFactory)
	{
	    super(type);
	    setSessionFactory(sessionFactory);
	    
	    ghibSession = sessionFactory.getCurrentSession();
	}  
	
	/**
	 * Method for get Emp Details by bill no.
	 * 
	 */
	public List getEmpDtlsByBillNo(String[] lStrBillNo)
	{
		List lLstReturn = new ArrayList();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
	
	        lSBQuery.append(" FROM TrnOnlinebillEmp A WHERE A.billNo = :billNo ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        for(String lStr: lStrBillNo)
	    	{
	    		lQuery.setParameter("billNo", Long.decode(lStr));
	            List lLstVO = lQuery.list();
	            gLogger.info("Query for getEmpDtlsByBillNo : " + lQuery.toString());
	            gLogger.info("And Parameter is " + lStr);
	            
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lLstReturn.add(lLstVO.get(0));
	            }
	    	}
	   }
 	   catch(Exception e)
	   {
	    	 gLogger.error("Error in getEmpDtlsByBillNo. Error is : " + e, e);
	   }
	    	
	   return lLstReturn;
	}
}
