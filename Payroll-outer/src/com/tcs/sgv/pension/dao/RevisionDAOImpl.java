package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;

/**
 * DAO for Queries related to Revision of Pension Case
 * @author Aparna Kansara
 * @version 1.0
 *
 */

public class RevisionDAOImpl extends GenericDaoHibernateImpl<TrnPensionerRivisionDtls, Long> implements RevisionDAO{
	
	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	/* Global Variable for Session Class */
	
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

    public RevisionDAOImpl(Class<TrnPensionerRivisionDtls> type,SessionFactory sessionFactory)
	{
		super(type);
        setSessionFactory(sessionFactory);
	}
		
    /**
     * get {@link TrnPensionRqstHdr} VO
     * @param String: PPO no
     * 		  String: Status
     * @return VO: TrnPensionRqstHdr
     * 
     */

	public TrnPensionRqstHdr getTrnPensionRqstHdrDtls(String lStrPPONO, String lStrStatus, String lStrCaseStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.ppoNo = :lppoNo AND A.status = :lStatus ");
	        lSBQuery.append(" AND A.caseStatus = :lCaseStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lppoNo", lStrPPONO);
	        lQuery.setParameter("lStatus", lStrStatus);
	        lQuery.setParameter("lCaseStatus", lStrCaseStatus);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && !lLstVO.isEmpty())
	            {
	            	lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error(" Error is : " + e, e);
 		  throw(e);
	   }
		return lobjPensionRqstHdr;
	}
	
	/**
	 * get {@link TrnPensionerRivisionDtls} VO
	 * 
	 * @param String: Pensioner code
	 * @return Arraylist of VO: TrnPensionerRivisionDtls
	 */
	
	public ArrayList<TrnPensionerRivisionDtls> getTrnPensionerRivisionDtls(String lStrPensionerCode) throws Exception
	{
		List <TrnPensionerRivisionDtls> lLstobjTrnPensionerRivisionDtls = new ArrayList<TrnPensionerRivisionDtls>();
				
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionerRivisionDtls A WHERE A.pensionerCode = :lPnsrCode ORDER BY A.revisionCounter ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode);
	        
	        List lLstVO = lQuery.list();
	        
	            if(lLstVO != null && !lLstVO.isEmpty())
	            {
	            	for(int i=0;i<lLstVO.size();i++)
	            	{
	            		lLstobjTrnPensionerRivisionDtls.add((TrnPensionerRivisionDtls) lLstVO.get(i));
	            	}
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error(" Error is : " + e, e);
 		  throw(e);
	   }
		return (ArrayList<TrnPensionerRivisionDtls>) lLstobjTrnPensionerRivisionDtls;
	}
	
	public Long getCaseIdfromPpoNo(String ppoNo,String Status) throws Exception 
	{
		Long lbgdcCaseId = 0L;
		StringBuilder strQuery = new StringBuilder();
		List resultList;
		try 
		{
            Session ghibSession = getSession();
            strQuery.append(" SELECT r.pensionRequestId ");
			strQuery.append(" FROM TrnPensionRqstHdr r ");
			strQuery.append(" WHERE r.ppoNo =:ppoNo and r.caseStatus =:status");

			Query hqlQuery = ghibSession.createQuery(strQuery.toString());
			hqlQuery.setString("ppoNo", ppoNo.trim());
			hqlQuery.setString("status", Status.trim());

			resultList = hqlQuery.list();

			if (resultList != null && resultList.size() > 0)

				lbgdcCaseId = (Long) resultList.get(0);

			return lbgdcCaseId;
		} catch (Exception e) {
			logger.error("Error is" + e, e);
			throw e;
		}
	}
    

    /**
     * get {@link TrnPensionerRivisionDtls} VO
     * 
     * Written By Sagar
     */
    public Date getLastRevisionDate(String lPnsnerCode) throws Exception
    {
        Date lLstRevisionDate = null;        
        String lYFlage = bundleConst.getString("CMN.Y");
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" select rv.rivisionDate FROM TrnPensionerRivisionDtls rv ");
            lSBQuery.append(" WHERE rv.pensionerCode = :PnsnerCode AND rv.activeFlag = :YFlage");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
            lQuery.setParameter("PnsnerCode", lPnsnerCode);
            lQuery.setParameter("YFlage", lYFlage);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    lLstRevisionDate = (Date) lLstVO.get(0);
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lLstRevisionDate;
    }
    
    public String getTiPercent(String headCode, String commDate) throws Exception
    {
    	/*
    	 	select r.uptoBasic,r.rate from RltPensionHeadcodeRate r
			where r.headCode=1 and r.fieldType='TI'
			and r.effectiveFromDate <= '1/Jan/2008'
			and (r.effective_to_date >= '1/Jan/2008' or r.effective_to_date is null)
			order by to_number(r.upto_basic)
			
			select r.upto_basic,r.rate from rlt_pension_headcode_rate r
			where r.head_code=1 and r.field_type='TI'
			and r.effective_from_date <= '1/Jan/2008'
			and (r.effective_to_date >= '1/Jan/2008' or r.effective_to_date is null)
			order by to_number(r.upto_basic)
    	 */
    	
    	 String lTiPercent= null;
    	 BigDecimal tiPer = new BigDecimal(0);
		 try
	     {
	         Session ghibSession = getSession();
	         StringBuilder lSBQuery = new StringBuilder();
	         
	         lSBQuery.append(" select h.tiRate from MstPensionHeadcode h ");
	         lSBQuery.append(" where h.headCode = "+headCode+" and h.activeFlag = 'Y'");
	         
	         Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	 
	         List lLstVO = lQuery.list();
	           
	             if(lLstVO != null && lLstVO.size() > 0)
	             {
	            	 tiPer = (BigDecimal) lLstVO.get(0);
	            	 lTiPercent = tiPer.toString();
	             }
	    }
	    catch(Exception e)
	    {
	       logger.error("Error is : " + e, e);
	       throw(e);
	    }
	
	     return lTiPercent;
    }
}
