package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;

/**
 * TrnPensionRecoveryDtlsDAOImpl specific DAO Implementation
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public class TrnPensionRecoveryDtlsDAOImpl extends GenericDaoHibernateImpl<TrnPensionRecoveryDtls, Long>
        implements TrnPensionRecoveryDtlsDAO
{
    Log gLogger = LogFactory.getLog(getClass());
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    String lStrRecoveryFromFlagChallan = bundleConst.getString("RECOVERY.CHALLAN");

    public TrnPensionRecoveryDtlsDAOImpl(Class<TrnPensionRecoveryDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
       
    }
    public ArrayList<TrnPensionRecoveryDtls> getTrnPensionRecoveryDtls(String lStrPensionerCode, String lStrStatus) throws Exception
	{
		List <TrnPensionRecoveryDtls> lLstobjTrnPensionRecoveryDtls = new ArrayList<TrnPensionRecoveryDtls>();
				
		try
		{
			Session ghibSession = getSession();
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRecoveryDtls A WHERE A.pensionerCode = :lPnsrCode AND A.recoveryFromFlag = :lStatus  And billNo is null ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode);
	        lQuery.setParameter("lStatus", lStrStatus);
	        
	        List lLstVO = lQuery.list();
	        
	            if(lLstVO != null && !lLstVO.isEmpty())
	            {
	            	for(int i=0;i<lLstVO.size();i++)
	            	{
	            		lLstobjTrnPensionRecoveryDtls.add((TrnPensionRecoveryDtls) lLstVO.get(i));
	            	}
	            }
	   }
 	   catch(Exception e)
	   {
 		  gLogger.error(" Error is : " + e, e);
 		  throw(e);
	   }

		return (ArrayList<TrnPensionRecoveryDtls>) lLstobjTrnPensionRecoveryDtls;
	}
    public ArrayList<TrnPensionRecoveryDtls> getTrnPensionRecoveryDtlsVOArray(String lStrPensionerCode,
            String lStrRecoveryFromFlag) throws Exception
    {
        ArrayList<TrnPensionRecoveryDtls> lObjTrnPensionRqstHdrArray = new ArrayList<TrnPensionRecoveryDtls>();

        StringBuffer lSBQuery = new StringBuffer();

        Query lQuery = null;

        try
        {
        	Session ghibSession = getSession();
            if (lStrPensionerCode != null)
            {
                lSBQuery.append(" FROM TrnPensionRecoveryDtls ");
                lSBQuery.append(" WHERE pensionerCode = :pensionerCode ");
                lSBQuery.append(" AND recoveryFromFlag = :recoveryFromFlag ");
                lQuery = ghibSession.createQuery(lSBQuery.toString());

                lQuery.setParameter("pensionerCode", lStrPensionerCode);
                lQuery.setParameter("recoveryFromFlag", lStrRecoveryFromFlag);

                lObjTrnPensionRqstHdrArray = (ArrayList<TrnPensionRecoveryDtls>) lQuery.list();
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is " + e, e);
            throw (e);
        }
        return lObjTrnPensionRqstHdrArray;
    }
    public List getPKForTableTrnPensionRecoveryDtls(Long lLngPensionRequestId, String lStrPensionerCode) throws Exception
	{
		List<Long> lLstReturn = null;		
		StringBuffer lSBQuery = new StringBuffer();
		
		try
		{
			Session ghibSession = getSession();
		    lSBQuery.append(" SELECT trnPensionRecoveryDtlsId ");
		    lSBQuery.append(" FROM TrnPensionRecoveryDtls ");
		    lSBQuery.append(" WHERE pensionRequestId = :pensionRequestId ");
		    lSBQuery.append(" AND pensionerCode = :pensionerCode ");
		    lSBQuery.append(" AND recoveryFromFlag != :recoverytypeflag ");
		
		    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		
		    lQuery.setParameter("pensionRequestId", lLngPensionRequestId);
		    lQuery.setParameter("pensionerCode", lStrPensionerCode);
		    lQuery.setParameter("recoverytypeflag", lStrRecoveryFromFlagChallan);
		
		    lLstReturn = (List<Long>) lQuery.list();
		}
		catch (Exception e)
		{
		    gLogger.error(
		            "Exception occurred in getPKForTableTrnPensionRecoveryDtls() method of PensionRecoveryInfoDAOImpl Class "
		                    + e, e);
		    throw (e);
		}
		
		return lLstReturn;
	}
}
