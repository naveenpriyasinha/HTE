package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;


public class CVPBillDAOImpl implements CVPBillDAO {
	
	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory = null;

	/* Global Variable for Session Class */
	Session ghibSession = null;
	
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

	public CVPBillDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		ghibSession = sessionFactory.getCurrentSession();
	}

	/**
     * get {@link TrnPensionRqstHdr} VO
     * 
     * Written By Aparna
     */

	public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPPONo(String lStrPPONO, String lStrStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.ppoNo = :lppoNo AND A.status = :lStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lppoNo", lStrPPONO.toString());
	        lQuery.setParameter("lStatus", lStrStatus);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null &&! lLstVO.isEmpty())
	            {
	            	lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lobjPensionRqstHdr;
	}
	
	/**
	 * second kind of query on trn_pension_rqst_hdr
	 * arguements: pensioner code and status
	 * Written by Aparna
	 */
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPnsnerCode(String lStrPnsrCode, String lStrStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.pensionerCode = :lpnsrCode AND A.status = :lStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lpnsrCode", lStrPnsrCode);
	        lQuery.setParameter("lStatus", lStrStatus);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
	            }
	   }
		catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lobjPensionRqstHdr;
	}
	
	/**
     * get {@link getMstPensionerHdrDtls} VO
     * 
     * Written By Aparna
     */
	public MstPensionerHdr getMstPensionerHdrDtls(String lStrPensionerCode) throws Exception
	{
		MstPensionerHdr lobjMstPensionerHdr = new MstPensionerHdr();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM  MstPensionerHdr H WHERE H.pensionerCode = :lPnsrCode ");
	        
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode.toString());
	       
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjMstPensionerHdr = (MstPensionerHdr) lLstVO.get(0);
	            }
	   }
		catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }
		return lobjMstPensionerHdr;
	}
	
	/**
     * get {@link TrnPensionRecoveryDtls} VO
     * 
     * Written By Aparna
     */
	public ArrayList<TrnPensionRecoveryDtls> getTrnPensionRecoveryDtls(String lStrPensionerCode, String lStrStatus) throws Exception
	{
		List <TrnPensionRecoveryDtls> lLstobjTrnPensionRecoveryDtls = new ArrayList<TrnPensionRecoveryDtls>();
				
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRecoveryDtls A WHERE A.pensionerCode = :lPnsrCode AND A.recoveryFromFlag = :lStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode);
	        lQuery.setParameter("lStatus", lStrStatus);
	        
	        List lLstVO = lQuery.list();
	        
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	for(int i=0;i<lLstVO.size();i++)
	            	{
	            		lLstobjTrnPensionRecoveryDtls.add((TrnPensionRecoveryDtls) lLstVO.get(i));
	            	}
	            }
	   }
		catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return (ArrayList<TrnPensionRecoveryDtls>) lLstobjTrnPensionRecoveryDtls;
	}	
	
	/**
	 * get {@link TrnPensionBillDtls} VO
	 * 
	 * Written By Aparna
	 */

	public TrnPensionBillDtls getTrnPensionBillDtls(String lStrBillNo) throws Exception
	{
		TrnPensionBillDtls lobjPensionBillDtls = new TrnPensionBillDtls();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionBillDtls A WHERE A.billNo = :lBillNo ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lBillNo", lStrBillNo);
	                
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjPensionBillDtls = (TrnPensionBillDtls) lLstVO.get(0);
	            }
	   }
		catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }
		return lobjPensionBillDtls;
	}	
}

