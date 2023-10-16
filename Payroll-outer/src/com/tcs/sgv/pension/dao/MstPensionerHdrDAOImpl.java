package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;

public class MstPensionerHdrDAOImpl extends
		GenericDaoHibernateImpl<MstPensionerHdr, Long> implements MstPensionerHdrDAO {	  
	
	Log gLogger = LogFactory.getLog(getClass());

	public MstPensionerHdrDAOImpl(Class<MstPensionerHdr> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	public String getName(String lStrPensionerCode) throws Exception
	{
		String lStrName = null;
		
		List<String> lLstResult = null;
		
		StringBuffer lSBQuery = new StringBuffer(200);
		
		Query lQuery =null;
		try 
		{
            Session hiSession = getSession();   
            lSBQuery.append(" SELECT firstName||' '||middleName||' '||lastName FROM MstPensionerHdr WHERE pensionerCode = :pensionerCode ");
			
			lQuery = hiSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("pensionerCode",lStrPensionerCode);
			
			lLstResult = (List<String>) lQuery.list();	
			
			if(lLstResult != null &&! lLstResult.isEmpty())
			{
				lStrName = (String) lLstResult.get(0);
			}			
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);
			throw (e);
		}
		return lStrName;
	}
    
    
    public String getPANNo(String lStrPensionerCode) throws Exception
	{
		String lStrPANNo = null;
		
		List<String> lLstResult = null;
		
		StringBuffer lSBQuery = new StringBuffer(100);
		
		Query lQuery =null;
        
		try 
		{
            Session hiSession = getSession();   
            lSBQuery.append(" SELECT panNo FROM MstPensionerHdr WHERE pensionerCode = :pensionerCode ");
			
			lQuery = hiSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("pensionerCode",lStrPensionerCode);
			
			lLstResult = (List<String>) lQuery.list();	
			
			if(lLstResult != null &&! lLstResult.isEmpty())
			{
				lStrPANNo = (String) lLstResult.get(0);
			}			
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);
			throw (e);
		}
		return lStrPANNo;
	}
    
	public Long getPensionerIdfromPensnrCode(String lStrPnsnrCode,String lStrCaseStatus) throws Exception 
	{
		Session hiSession = getSession();
		Long lbgdcCaseId = 0L;
		StringBuilder strQuery = new StringBuilder();
		List resultList;
		try {

			strQuery.append(" SELECT r.pensionerId ");
			strQuery.append(" FROM MstPensionerHdr r ");
			strQuery
					.append(" WHERE r.pensionerCode =:pensionerCode AND r.caseStatus =:caseStatus");

			Query hqlQuery = hiSession.createQuery(strQuery.toString());
			hqlQuery.setString("pensionerCode", lStrPnsnrCode.trim());
			hqlQuery.setString("caseStatus", lStrCaseStatus);
			resultList = hqlQuery.list();

			if (resultList != null && resultList.size() > 0)

				lbgdcCaseId = (Long) resultList.get(0);

			return lbgdcCaseId;
		} catch (Exception e) {
			throw e;
		}
	}
	public List<MstPensionerHdr> getMstPensionerHdrDiff(String lStrPnsnrCode) throws Exception
	{
		StringBuffer lSBQuery = new StringBuffer();
        Query lQuery = null;
        List<MstPensionerHdr> lLstResLst = new ArrayList<MstPensionerHdr>();
        Session ghibSession = getSession();
        try
        {
     	   lSBQuery.append(" FROM MstPensionerHdr D WHERE D.pensionerCode  = '"+ lStrPnsnrCode +"' AND ");
     	   lSBQuery.append(" (D.caseStatus = 'NEW' OR D.caseStatus = 'APPROVED' ) ");
     	   lQuery = ghibSession.createQuery(lSBQuery.toString());

     	   lLstResLst = (List<MstPensionerHdr>)lQuery.list();
        }
        catch(Exception e)
        {
     	   throw e;
        }
 	 return lLstResLst;
	}
	public MstPensionerHdr getMstPensionerHdrDtls(String lStrPensionerCode) throws Exception
	{
		MstPensionerHdr lobjMstPensionerHdr = new MstPensionerHdr();
		
		try
		{
            Session hiSession = getSession();   
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM  MstPensionerHdr H WHERE H.pensionerCode = :lPnsrCode ");
	        Query lQuery = hiSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode);
	       
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && !lLstVO.isEmpty())
	            {
	            	lobjMstPensionerHdr = (MstPensionerHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  gLogger.error(" Error is : " + e, e);
 		  throw(e);
	   }
		return lobjMstPensionerHdr;
	}
	public List getMRRelatedInfo(String lStrPPONO,String lStrCaseStatus,String gStrLocationCode) throws Exception
	{
		StringBuilder lSBQuery = new StringBuilder();
        Query lQuery = null;
        List lLstResLst = new ArrayList();
        Session ghibSession = getSession();
        try
        {
     	   lSBQuery.append(" SELECT  tprh.schemeType,tprh.medicalAllowenceAmount,mph.pnsnrPrefix||' '||mph.firstName||' '||mph.middleName||' '||mph.lastName, ");
     	   lSBQuery.append(" mpd.branchCode,tprh.headCode,rbb.branchName ");
     	   lSBQuery.append(" FROM TrnPensionRqstHdr tprh,MstPensionerHdr mph,MstPensionerDtls mpd,RltBankBranch rbb ");
     	   lSBQuery.append(" WHERE tprh.pensionerCode = mph.pensionerCode AND tprh.ppoNo = :ppoNo AND tprh.caseStatus = :caseStatus ");
     	   lSBQuery.append(" AND tprh.caseStatus = mph.caseStatus ");
     	   lSBQuery.append(" AND mpd.pensionerCode = mph.pensionerCode ");
     	   lSBQuery.append(" AND rbb.branchCode = mpd.branchCode and rbb.locationCode = :locationCode ");
     	   
     	   lQuery = ghibSession.createQuery(lSBQuery.toString());
     	   
     	  lQuery.setParameter("ppoNo",lStrPPONO);
     	  lQuery.setParameter("caseStatus",lStrCaseStatus);
     	  lQuery.setParameter("locationCode",gStrLocationCode);
     	   
     	   lLstResLst = (List)lQuery.list();
        }
        catch(Exception e)
        {
     	   throw e;
        }
        return lLstResLst;
	}
	
	public String getDesigName(long lStrDesignation) throws Exception
	{
		String lStrDesigName = null;
		
		List<String> lLstResult = null;
		
		StringBuffer lSBQuery = new StringBuffer(200);
		
		Query lQuery =null;
		try 
		{
            Session hiSession = getSession();   
            lSBQuery.append(" SELECT dsgnName FROM OrgDesignationMst odm WHERE odm.dsgnId = :lStrDesignation");
			
			lQuery = hiSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("lStrDesignation",lStrDesignation);
			
			lLstResult = (List<String>) lQuery.list();	
			
			if(lLstResult != null &&! lLstResult.isEmpty())
			{
				lStrDesigName = (String) lLstResult.get(0);
			}			
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);
			throw (e);
		}
		return lStrDesigName;
		
	}
	
}
