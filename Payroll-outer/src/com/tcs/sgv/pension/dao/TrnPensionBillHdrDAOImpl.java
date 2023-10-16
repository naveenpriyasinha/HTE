package com.tcs.sgv.pension.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;

public class TrnPensionBillHdrDAOImpl extends GenericDaoHibernateImpl<TrnPensionBillHdr, Long> implements TrnPensionBillHdrDAO{
	
	private final Log logger = LogFactory.getLog(getClass());
	
	public TrnPensionBillHdrDAOImpl(Class<TrnPensionBillHdr> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public int getBillGenerationMonth(String lStrPensionerCode) throws Exception
    {
    	StringBuffer lSBQuery = new StringBuffer(400);
    	
    	int lIntMonth = 0;
    	
    	List lLstReturn = null;
   	
    	Query lQuery =null;
    	
		try 
		{
            Session ghibSession = getSession();
            lSBQuery.append(" SELECT max(A.forMonth) FROM TrnPensionBillHdr A, TrnPensionBillDtls B WHERE A.trnPensionBillHdrId = B.trnPensionBillHdrId AND B.pensionerCode = :pensionerCode ");				
			
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("pensionerCode",lStrPensionerCode);
			
			lLstReturn = lQuery.list();
			
			if (lLstReturn.get(0)!= null && lLstReturn.size() > 0) 
			{
				lIntMonth = Integer.parseInt(lLstReturn.get(0).toString());
			}			
		} 
		catch (Exception e) 
		{
			logger.info("Error is : "+e,e);  
			throw(e);
		}
		return lIntMonth;
    }    
	public TrnPensionBillHdr getTrnPensionBillHdr(String lStrBillNo, String lStrBillType) throws Exception
	{
		TrnPensionBillHdr lobjTrnPensionBillHdr = new TrnPensionBillHdr();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionBillHdr A WHERE A.billNo = :lBillNo AND A.billType = :lBillType ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lBillNo", lStrBillNo.toString());
	        lQuery.setParameter("lBillType", lStrBillType);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
                    lobjTrnPensionBillHdr = (TrnPensionBillHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lobjTrnPensionBillHdr;
	}
	//new
	public TrnPensionBillHdr getUniqueTrnPensionBillHdr(Long lLngBillHdrId) throws Exception
	{
		TrnPensionBillHdr lobjTrnPensionBillHdr = new TrnPensionBillHdr();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionBillHdr A WHERE A.trnPensionBillHdrId = :lLngBillHdrId ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lLngBillHdrId", lLngBillHdrId);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
                    lobjTrnPensionBillHdr = (TrnPensionBillHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lobjTrnPensionBillHdr;
	}
	
}
