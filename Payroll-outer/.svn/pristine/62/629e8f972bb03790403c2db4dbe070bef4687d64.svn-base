package com.tcs.sgv.pension.dao;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionTransferDtls;

public class TrnPensionTransferDtlsDAOImpl extends GenericDaoHibernateImpl<TrnPensionTransferDtls, Long> implements TrnPensionTransferDtlsDAO {
	
	/* Global Variable for Logger Class */
	private Log gLogger = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory = null;

    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    
    public TrnPensionTransferDtlsDAOImpl(Class<TrnPensionTransferDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
		this.sessionFactory = sessionFactory;
	}
    
    public Session getSession() 
    {
         boolean allowCreate = true;
         return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
    
    public Long getTrnsferDtlsPk(String lStrPppoNo, String lStrStatus, String lStrLocId) throws Exception
    {
    	
    	Long lTransferDtlsPK = null;
    	
    	try 
    	{
    		
    		Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT transferDtlsId FROM TrnPensionTransferDtls ");
	        lSBQuery.append(" WHERE ppoNo = :lPPONO AND status = :lStrStatus AND fromLocation = :lStrLocId ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPPONO", lStrPppoNo);
	        lQuery.setParameter("lStrStatus", lStrStatus);
	        lQuery.setParameter("lStrLocId", lStrLocId);
	       
	        List lLstVO = lQuery.list();
	          
            if(lLstVO != null && lLstVO.size() > 0)
            {
            	lTransferDtlsPK = new Long(lLstVO.get(0).toString());
            }
		} 
    	catch (Exception e) 
    	{
    		gLogger.error("Error is : ", e);
    		throw(e);
		}
    	
    	return lTransferDtlsPK;
    }
}
