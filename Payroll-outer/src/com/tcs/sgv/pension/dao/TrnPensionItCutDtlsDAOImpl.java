package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;

/**
 * TrnPensionItCutDtls specific DAO Implementation
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */

public class TrnPensionItCutDtlsDAOImpl extends GenericDaoHibernateImpl<TrnPensionItCutDtls, Long>  implements TrnPensionItCutDtlsDAO
{
	private Session ghibSession = null;
	private final Log logger = LogFactory.getLog(getClass());
    public TrnPensionItCutDtlsDAOImpl(Class<TrnPensionItCutDtls> type,SessionFactory sessionFactory)
    {
    	super(type);        
        setSessionFactory(sessionFactory);
        ghibSession = sessionFactory.getCurrentSession();
    } 
    public List getPKForTableTrnPensionItCutDtls(final long lBDPenReqId,final String lStrPenCode,final String lStrTypeFlag)  throws Exception 
	{
		List<Long> lLstReturn = null;
		
		StringBuffer lSBQuery = new StringBuffer(200);
		
		String lStrTypeFlag1 = null;
		String lStrTypeFlag2= null;		
						
		try 
		{
			Query lQuery =null;
			
			if("PP".equals(lStrTypeFlag)||"PT".equals(lStrTypeFlag))
			{				
				lStrTypeFlag1 = "PP";
				lStrTypeFlag2 = "PT";
				
				lSBQuery.append(" SELECT pensionItCutDtlsId FROM TrnPensionItCutDtls WHERE pensionRequestId = :pensionRequestId AND pensionerCode = :pensionerCode AND (typeFlag = :typeFlag1 OR typeFlag = :typeFlag2) ");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				
				lQuery.setParameter("pensionRequestId", lBDPenReqId);			
				lQuery.setParameter("pensionerCode", lStrPenCode);
				lQuery.setParameter("typeFlag1", lStrTypeFlag1);
				lQuery.setParameter("typeFlag2", lStrTypeFlag2);
			}
			else if("PermanentBenefit".equals(lStrTypeFlag)||"TemporaryBenefit".equals(lStrTypeFlag))
			{				
				lStrTypeFlag1 = "PermanentBenefit";
				lStrTypeFlag2 = "TemporaryBenefit";
				
				lSBQuery.append(" SELECT pensionItCutDtlsId FROM TrnPensionItCutDtls WHERE pensionRequestId = :pensionRequestId AND pensionerCode = :pensionerCode AND (typeFlag = :typeFlag1 OR typeFlag = :typeFlag2) ");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				
				lQuery.setParameter("pensionRequestId", lBDPenReqId);			
				lQuery.setParameter("pensionerCode", lStrPenCode);
				lQuery.setParameter("typeFlag1", lStrTypeFlag1);
				lQuery.setParameter("typeFlag2", lStrTypeFlag2);
			}
			else
			{			
				lSBQuery.append(" SELECT pensionItCutDtlsId FROM TrnPensionItCutDtls WHERE pensionRequestId = :pensionRequestId AND pensionerCode = :pensionerCode AND typeFlag = :typeFlag ");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				
				lQuery.setParameter("pensionRequestId", lBDPenReqId);			
				lQuery.setParameter("pensionerCode", lStrPenCode);
				lQuery.setParameter("typeFlag", lStrTypeFlag);
			}
			
			lLstReturn = (List<Long>) lQuery.list();			
		} 
		catch (Exception e) 
		{
			logger.info("Error is : "+e,e);  
			throw(e);
		}
		return lLstReturn;
	}
    public List<TrnPensionItCutDtls> getTrnPensionItCutDtlsVO(String lStrCutType,final String lStrLangId,final long lBDPensReqId,final String lStrPenCode) throws Exception 
	{
		List<TrnPensionItCutDtls> lObjTrnPensionItCutDtls = new ArrayList<TrnPensionItCutDtls>();
		StringBuffer lSBQuery = new StringBuffer(400);
		
		String lStrCutType1 = null;
		
		Query lQuery =null;
				
		try 
		{
			if("IT Cut".equals(lStrCutType))
			{
				lStrCutType1 = "IT";
				lSBQuery.append(" FROM TrnPensionItCutDtls WHERE typeFlag = :typeFlag AND pensionRequestId = :pensionRequestId AND pensionerCode = :pensionerCode ");				
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				
				lQuery.setParameter("typeFlag",lStrCutType1);
				lQuery.setParameter("pensionRequestId",lBDPensReqId);
				lQuery.setParameter("pensionerCode",lStrPenCode);
			}
			if("Special Cut".equals(lStrCutType))
			{
				lStrCutType1 = "ST";
				lSBQuery.append(" FROM TrnPensionItCutDtls WHERE typeFlag = :typeFlag AND pensionRequestId = :pensionRequestId AND pensionerCode = :pensionerCode ");				
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				
				lQuery.setParameter("typeFlag",lStrCutType1);
				lQuery.setParameter("pensionRequestId",lBDPensReqId);
				lQuery.setParameter("pensionerCode",lStrPenCode);
			}
			if("Pension Cut".equals(lStrCutType))
			{
				lSBQuery.append(" FROM TrnPensionItCutDtls WHERE (typeFlag like 'PP' OR typeFlag like 'PT') AND pensionRequestId = :pensionRequestId AND pensionerCode = :pensionerCode ");				
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				
				lQuery.setParameter("pensionRequestId",lBDPensReqId);
				lQuery.setParameter("pensionerCode",lStrPenCode);
			}
			if("Other Benefit".equals(lStrCutType))
			{
				lSBQuery.append(" FROM TrnPensionItCutDtls WHERE typeFlag like '%Benefit' AND pensionRequestId = :pensionRequestId AND pensionerCode = :pensionerCode ");				
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				
				lQuery.setParameter("pensionRequestId",lBDPensReqId);
				lQuery.setParameter("pensionerCode",lStrPenCode);
			}
			
			lObjTrnPensionItCutDtls = (ArrayList<TrnPensionItCutDtls>) lQuery.list();	
		} 
		catch (Exception e) 
		{
			logger.info("Error is : "+e,e);  
			throw(e);
		}
		return lObjTrnPensionItCutDtls;
	}
}
