package com.tcs.sgv.pension.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;

public class TrnPensionBillDtlsDAOImpl  extends GenericDaoHibernateImpl<TrnPensionBillDtls, Long> implements TrnPensionBillDtlsDAO{

	
	private final Log logger = LogFactory.getLog(getClass());
	
	public TrnPensionBillDtlsDAOImpl(Class<TrnPensionBillDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public TrnPensionBillDtls getTrnPensionBillDtls(long TrnPensionBillHdrPK) throws Exception
    {
        TrnPensionBillDtls lobjTrnPensionBillDtls = new TrnPensionBillDtls();
        
        try
        {
        	Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" FROM TrnPensionBillDtls A WHERE A.trnPensionBillHdrId = :ltrnPensionBillHdrId ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
            lQuery.setParameter("ltrnPensionBillHdrId", TrnPensionBillHdrPK);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    lobjTrnPensionBillDtls = (TrnPensionBillDtls) lLstVO.get(0);
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lobjTrnPensionBillDtls;
    }
	public String getDesgDesc(String lStrDesignation) throws Exception
	{
		String lStrDesgDesc = null;
		
		try
		{
			Session ghibSession = getSession();
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT A.dsgnName FROM OrgDesignationMst A WHERE A.dsgnCode = :lDesgCode");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lDesgCode", lStrDesignation);
	                
	        List lLst = lQuery.list();
	          
	            if(lLst != null && lLst.size() > 0)
	            {
	            	lStrDesgDesc = (lLst.get(0)).toString();
	            }
	   }
		   catch(Exception e)
	   {
			  logger.error(" Error is : " + e, e);
			  throw(e);
	   }
		return lStrDesgDesc;
	}
	public int getMaxOfForMonth(String lStrPensionerCode,String lStrPPONumber,String lStrRecoveryFromFlag)  throws Exception
    {
        int lIntReturn = 0;
        StringBuffer lSBQuery = new StringBuffer();
        try
        {
        	Session ghibSession = getSession();
        	lSBQuery.append(" SELECT max(B.forMonth) ");
            lSBQuery.append(" FROM TrnPensionBillDtls A,TrnPensionBillHdr B ");
            lSBQuery.append(" WHERE A.trnPensionBillHdrId = B.trnPensionBillHdrId ");
            lSBQuery.append(" AND A.pensionerCode = :pensionerCode ");
            lSBQuery.append(" AND A.ppoNo = :ppoNo ");
            lSBQuery.append(" AND B.billType = :billType ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("pensionerCode", lStrPensionerCode);
            lQuery.setParameter("ppoNo", lStrPPONumber);
            lQuery.setParameter("billType", lStrRecoveryFromFlag);
            
            List lLst = lQuery.list();
           
            if (lLst != null && lLst.size() > 0)
            {
            	if(lLst.get(0) != null)
            	{
	                lIntReturn = Integer.parseInt((lLst.get(0)).toString());
            	}
            }            
        }
        catch (Exception e)
        {
            logger.error("Error is " + e, e);
            throw (e);
        }
        return lIntReturn;
    }
}
