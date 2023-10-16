package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.common.valueobject.MstEdp;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionPsbDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * TrnPensionRqstHdrDAOImpl specific DAO Implementation 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public class TrnPensionRqstHdrDAOImpl extends GenericDaoHibernateImpl<TrnPensionRqstHdr, Long>  implements TrnPensionRqstHdrDAO
{
    
    Log gLogger = LogFactory.getLog(getClass());
    
    public TrnPensionRqstHdrDAOImpl(Class<TrnPensionRqstHdr> type,SessionFactory sessionFactory)
    {
    	super(type);        
        setSessionFactory(sessionFactory);
    }    
    public Long getPensionRqstHdrIdByPnsnrCode(String pensionerCode) throws Exception
    {
        Long pnsnRqstHdrId = null;
        StringBuilder query = new StringBuilder();
        try
        {
            Session ghibSession = getSession();
            query.append(" SELECT d.pensionRequestId from TrnPensionRqstHdr d ");
            query.append(" WHERE d.pensionerCode = :pensionerCode ");
            
            Query hqlQuery = ghibSession.createQuery(query.toString());
            
            hqlQuery.setParameter("pensionerCode", pensionerCode.toString());
            
            List list = hqlQuery.list();
            if(list != null && list.size()>0)
            {
                Iterator itr = list.iterator();
                while(itr.hasNext())
                {
                    pnsnRqstHdrId = (Long)(itr.next());
                }
                
            }
        }
        catch(Exception e)
        {
            gLogger.error("Error is"+e,e);
            throw e;
        }
        return pnsnRqstHdrId;
    }
    public List getPksForTrnPensionRqstHdr(String lStrStatus,String lStrPpoNo) throws Exception 
    {
          StringBuilder query = new StringBuilder();
          List lLstPk = new ArrayList();
          try
          {
              Session ghibSession = getSession();
              query.append(" SELECT d.pensionRequestId,d.pensionerCode FROM TrnPensionRqstHdr d ");
              query.append(" WHERE d.ppoNo = '"+ lStrPpoNo +"' AND caseStatus ='" + lStrStatus + "'");
              Query hqlQuery = ghibSession.createQuery(query.toString());
              lLstPk = hqlQuery.list();
          }
          catch(Exception e)
          {
        	  gLogger.error("Error is"+e,e);
              throw e;
          }
    	return lLstPk;
    }
    public String getPPONo(String lStrPensionerCode) throws Exception
    {
		String lStrPPONo = null;
		
		List<String> lLstResult = null;
		
		StringBuffer lSBQuery = new StringBuffer(100);
		
		Query lQuery =null;
				
		try 
		{
            Session ghibSession = getSession();
            lSBQuery.append(" SELECT ppoNo FROM TrnPensionRqstHdr WHERE pensionerCode = :pensionerCode ");
						
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("pensionerCode",lStrPensionerCode);
			
			lLstResult = (List<String>) lQuery.list();	
			
			if(lLstResult != null &&! lLstResult.isEmpty())
			{
				lStrPPONo = (String) lLstResult.get(0);
			}			
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);
			throw (e);
		}
		return lStrPPONo;
    }
    public TrnPensionRqstHdr getTrnPensionRqstHdrVO(long lBDPenReqId,String lStrPenCode) throws Exception
    {
    	StringBuffer lSBQuery = new StringBuffer(400);
    	
    	TrnPensionRqstHdr lObjTrnPensionRqstHdr = new TrnPensionRqstHdr();
    	
    	List lLstReturn = null;
       	
    	Query lQuery =null;
    	
    	try 
		{
            Session ghibSession = getSession();
            lSBQuery.append("FROM TrnPensionRqstHdr WHERE pensionRequestId = :pensionRequestId AND pensionerCode = :pensionerCode ");				
			
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("pensionRequestId",lBDPenReqId);
			lQuery.setParameter("pensionerCode",lStrPenCode);
			
			lLstReturn = lQuery.list();
			
			if (lLstReturn.get(0)!= null && lLstReturn.size() > 0) 
			{
				lObjTrnPensionRqstHdr = (TrnPensionRqstHdr)(lLstReturn.get(0));
			}			
		} 
		catch (Exception e) 
		{
			gLogger.info("Error is : "+e,e);  
			throw(e);
		}
		return lObjTrnPensionRqstHdr;
    }
    public String validatePPONumberForScheme(String PPONO) throws Exception
	{
		String lStrScheme="";
		String errorMsg="";
		try
		{			
			Session hibSession = getSession();
			String mySQL = "select prh.schemeType from TrnPensionRqstHdr prh where prh.ppoNo=:lPpoNo";
			Query lQuery = hibSession.createQuery(mySQL);		
		    lQuery.setParameter("lPpoNo",PPONO);
		    List lLstVO = lQuery.list();		      
            if(lLstVO != null && !lLstVO.isEmpty())
            {
            	lStrScheme = lLstVO.get(0).toString();
            }        
            if(!lStrScheme.equalsIgnoreCase("PSB")){
            	errorMsg = "This PPO Number is not associated with PSB Scheme";
            } 			
		}
		catch(Exception e)
		{
			gLogger.error(" Error is : " + e, e);
	 		throw(e);
		}
		return errorMsg;
	}
    public String validatePPONumberForBank(String PPONO,String branchCode) throws Exception
	{
		String lStrBranchCode="";
		String errorMsg="";
		try
		{			
			Session hibSession = getSession();
			StringBuilder lSBQuery = new StringBuilder(); 
			lSBQuery.append("select mph.branchCode from TrnPensionRqstHdr prh, MstPensionerDtls mph where prh.ppoNo=:lPpoNo " +
							" and prh.pensionerCode = mph.pensionerCode");		
			Query lQuery = hibSession.createQuery(lSBQuery.toString());			
		    lQuery.setParameter("lPpoNo", PPONO);
		    List lLstVO = lQuery.list();		    
            if(lLstVO != null && lLstVO.size() > 0)
            {
            	lStrBranchCode = lLstVO.get(0).toString();
            }            
            if(!lStrBranchCode.equalsIgnoreCase(branchCode)){
            	errorMsg = "This PPO Number is not associated with the selected bank";
            }			
		}
		catch(Exception e)
		{
			gLogger.error(" Error is : " + e, e);
	 		throw(e);
		}
		return errorMsg;
	}
    public Map getPaymentDetails(String PPONO) throws Exception
	{
		Map resultValueMap = new HashMap();
		List lLstVO;
		Query lQuery;
		ArrayList arrPaymentDetails = new ArrayList();
		ArrayList arrMonthYear = new ArrayList();
		try
		{			
			Session hibSession = getSession();
			String mySql = "select prh.headCode, mph.firstName || ' ' || mph.middleName || ' ' || mph.lastName, prh.ppoNo,mh.description " +
					" from TrnPensionRqstHdr prh, MstPensionerHdr mph, MstPensionHeadcode mh" +
					" where prh.ppoNo=:lPPONO and prh.pensionerCode = mph.pensionerCode and mh.headCode = prh.headCode";			
			lQuery = hibSession.createQuery(mySql);			
	        lQuery.setParameter("lPPONO", PPONO);
	        lLstVO = lQuery.list();	        
			if (lLstVO!=null && lLstVO.size()>0) 
			{	
				for(Object row : lLstVO)
				{
					Object[] cols = (Object[])row;
					resultValueMap.put("headCode", cols[0]);
					resultValueMap.put("pensionerName", cols[1]);
					resultValueMap.put("headCodeDesc",cols[3]);
				}
			}
			
			mySql = "select monthYear,actualPayment,bankPayment,lessPayment,excessPayment,differenceAmount,incomeTaxCutAmount,pensnCutAmount,specialCutAmount,dpAmount,tiAmount,medicalAllowenceAmount,arrearAmount,recoveryAmount,cvpMonthlyAmount,personalPensionAmount,irAmount,basicPension from TrnPensionPsbDtls where ppoNo = :lPPONO ORDER BY monthYear DESC";
			lQuery = hibSession.createQuery(mySql);
			lQuery.setParameter("lPPONO", PPONO);
			lLstVO = lQuery.list();
			
			if(lLstVO != null && lLstVO.size()>0)
			{
				for(Object row : lLstVO)
				{
					Object cols[] = (Object[]) row;
					TrnPensionPsbDtls lObjTrnPensionPsbDtls = new TrnPensionPsbDtls();
					lObjTrnPensionPsbDtls.setMonthYear((Integer)cols[0]);
					arrMonthYear.add((Integer)cols[0]);
					lObjTrnPensionPsbDtls.setActualPayment((BigDecimal) cols[1]);
					lObjTrnPensionPsbDtls.setBankPayment((BigDecimal) cols[2]);
					lObjTrnPensionPsbDtls.setLessPayment((BigDecimal) cols[3]);
					lObjTrnPensionPsbDtls.setExcessPayment((BigDecimal) cols[4]);
					lObjTrnPensionPsbDtls.setDifferenceAmount((BigDecimal) cols[5]);
					lObjTrnPensionPsbDtls.setIncomeTaxCutAmount((BigDecimal) cols[6]);
					lObjTrnPensionPsbDtls.setPensnCutAmount((BigDecimal) cols[7]);
					lObjTrnPensionPsbDtls.setSpecialCutAmount((BigDecimal) cols[8]);
					lObjTrnPensionPsbDtls.setDpAmount((BigDecimal) cols[9]);
					lObjTrnPensionPsbDtls.setTiAmount((BigDecimal) cols[10]);
					lObjTrnPensionPsbDtls.setMedicalAllowenceAmount((BigDecimal) cols[11]);
					lObjTrnPensionPsbDtls.setArrearAmount((BigDecimal) cols[12]);
					lObjTrnPensionPsbDtls.setRecoveryAmount((BigDecimal) cols[13]);
					lObjTrnPensionPsbDtls.setCvpMonthlyAmount((BigDecimal) cols[14]);
					lObjTrnPensionPsbDtls.setPersonalPensionAmount((BigDecimal) cols[15]);
					lObjTrnPensionPsbDtls.setIrAmount((BigDecimal) cols[16]);
					lObjTrnPensionPsbDtls.setBasicPension((BigDecimal) cols[17]);
					arrPaymentDetails.add(lObjTrnPensionPsbDtls);
				}
			}		
			
			resultValueMap.put("paymentDetailsList", arrPaymentDetails);	
			resultValueMap.put("MonthYearList", arrMonthYear);
		}catch(Exception e){
			gLogger.error(" Error is : " + e, e);
	 		throw(e);
		}
		return resultValueMap;
	}
    public Map getActualPaymentDetails(long lLngPensionReqId,String lStrPensionerCode)throws Exception
    {
    	Map resultValueMap = new HashMap();
		List lLstVO;
		Query lQuery;
		ArrayList arrPaymentDetails = new ArrayList();
		
		try
		{			
			Session hibSession = getSession();
			String mySql = "SELECT reducedPension,incomeTaxCutAmount,pensnCutAmount,dpAmount,tiAmount,medicalAllowenceAmount,arrearAmount,recoveryAmount,specialCutAmount,cvpMonthAmount,personalPensionAmount,irAmount,pensionAmount FROM TrnPensionBillDtls WHERE pensionerCode = :pensionerCode";
			lQuery = hibSession.createQuery(mySql);
			lQuery.setParameter("pensionerCode", lStrPensionerCode);
			lLstVO = lQuery.list();
			
			if(lLstVO != null && lLstVO.size()>0)
			{
				for(Object row : lLstVO)
				{
					Object cols[] = (Object[]) row;
					TrnPensionBillDtls lObjTrnPensionBillDtls = new TrnPensionBillDtls();

					lObjTrnPensionBillDtls.setReducedPension((BigDecimal) cols[0]);
					lObjTrnPensionBillDtls.setIncomeTaxCutAmount((BigDecimal) cols[1]);
					lObjTrnPensionBillDtls.setPensnCutAmount((BigDecimal) cols[2]);
					lObjTrnPensionBillDtls.setDpAmount((BigDecimal) cols[3]);
					lObjTrnPensionBillDtls.setTiAmount((BigDecimal) cols[4]);
					lObjTrnPensionBillDtls.setMedicalAllowenceAmount((BigDecimal) cols[5]);
					lObjTrnPensionBillDtls.setArrearAmount((BigDecimal) cols[6]);
					lObjTrnPensionBillDtls.setRecoveryAmount((BigDecimal) cols[7]);
					lObjTrnPensionBillDtls.setSpecialCutAmount((BigDecimal) cols[8]);
					lObjTrnPensionBillDtls.setCvpMonthAmount((BigDecimal) cols[9]);
					lObjTrnPensionBillDtls.setPersonalPensionAmount((BigDecimal) cols[10]);
					lObjTrnPensionBillDtls.setIrAmount((BigDecimal) cols[11]);
					lObjTrnPensionBillDtls.setPensionAmount((BigDecimal) cols[12]);
					
					arrPaymentDetails.add(lObjTrnPensionBillDtls);
				}
			}		
			
			resultValueMap.put("ActualPaymentDetailsList", arrPaymentDetails);
		}catch(Exception e){
			gLogger.error(" Error is : " + e, e);
	 		throw(e);
		}
		return resultValueMap;
    }
    public List getPensionRqstHdrCodeId(String lStrPPONo,String lStrStatus) throws Exception
	{	
		List lLstResult = null;
		StringBuffer lSBQuery = new StringBuffer();
				
		try 
		{			
            Session ghibSession = getSession();
            lSBQuery.append(" select pensionRequestId,pensionerCode ");
			lSBQuery.append(" from TrnPensionRqstHdr ");
			lSBQuery.append(" where ppoNo = :PPONo and status = :CaseStatus ");			
			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			
			lQuery.setParameter("PPONo", lStrPPONo);
			lQuery.setParameter("CaseStatus", lStrStatus);
			lLstResult = lQuery.list();
		} 
		catch (Exception e) 
		{
        	gLogger.error("Error is : " + e,e);
        	throw(e);
		}
		return lLstResult;
	}
    public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPPONo(String lStrPPONO, String lStrStatus, String lStrCaseStatus) throws Exception
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
 		  gLogger.error(" Error is : " + e, e);
 		  throw(e);
	   }

		return lobjPensionRqstHdr;
	}
    public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPnsnerCode(String lStrPnsrCode, String lStrStatus, String lStrCaseStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.pensionerCode = :lpnsrCode AND A.status = :lStatus ");
	        lSBQuery.append(" AND A.caseStatus = :lCaseStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lpnsrCode", lStrPnsrCode);
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
 		  gLogger.error(" Error is : " + e, e);
 		  throw(e);
	   }

		return lobjPensionRqstHdr;
	}
    public TrnPensionRqstHdr getTrnPensionRqstHdrVO(String lStrPPONo) throws Exception
    {
        TrnPensionRqstHdr lObjTrnPensionRqstHdr = new TrnPensionRqstHdr();
        StringBuffer lSBQuery = new StringBuffer();

        Query lQuery = null;

        try
        {
            Session ghibSession = getSession();
            if (lStrPPONo != null)
            {
                lSBQuery.append(" FROM TrnPensionRqstHdr ");
                lSBQuery.append(" WHERE ppoNo = :ppoNo ");

                lQuery = ghibSession.createQuery(lSBQuery.toString());

                lQuery.setParameter("ppoNo", lStrPPONo);

                List lLstVO = lQuery.list();

                if (lLstVO != null && lLstVO.size() > 0)
                {
                    lObjTrnPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Exception occurred in getTrnPensionRqstHdrVO() method of PensionRecoveryInfoDAOImpl Class "
                    + e, e);
            throw (e);
        }
        return lObjTrnPensionRqstHdr;
    }
    public MstEdp getMstEdpVO(String lStrEdpCode, Long gLngLangId) throws Exception
    {

        MstEdp lObjMstEdp = new MstEdp();
        StringBuffer lSBQuery = new StringBuffer();

        Query lQuery = null;

        try
        {
            Session ghibSession = getSession();
            if (lStrEdpCode != null)
            {
                lSBQuery.append(" FROM MstEdp ");
                lSBQuery.append(" WHERE edpCode = :edpCode ");
                lSBQuery.append(" AND langId = :langId ");
                lQuery = ghibSession.createQuery(lSBQuery.toString());

                lQuery.setParameter("edpCode", lStrEdpCode);
                lQuery.setParameter("langId", gLngLangId);

                List lLstVO = lQuery.list();

                if (lLstVO != null && lLstVO.size() > 0)
                {
                    lObjMstEdp = (MstEdp) lLstVO.get(0);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Exception occurred in getMstEdpVO() method of PensionRecoveryInfoDAOImpl Class " + e, e);
            throw (e);
        }

        return lObjMstEdp;
    }
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPnsnerCode(String lStrPnsrCode,String lStrCaseStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.pensionerCode = :lpnsrCode AND A.caseStatus = :lCaseStatus ");
	        lSBQuery.append(" AND A.caseStatus = :lCaseStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lpnsrCode", lStrPnsrCode);
	        lQuery.setParameter("lCaseStatus", lStrCaseStatus);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && !lLstVO.isEmpty())
	            {
	            	lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  gLogger.error(" Error is : " + e, e);
 		  throw(e);
	   }

		return lobjPensionRqstHdr;
		
	}
	//new method
    public TrnPensionRqstHdr getTrnPensionRqstHdrVOforApproved(String lStrPPONo,String lStrCaseStatus) throws Exception
    {
        TrnPensionRqstHdr lObjTrnPensionRqstHdr = new TrnPensionRqstHdr();
        StringBuffer lSBQuery = new StringBuffer();

        Query lQuery = null;

        try
        {
            Session ghibSession = getSession();
            if (lStrPPONo != null)
            {
                lSBQuery.append(" FROM TrnPensionRqstHdr ");
                lSBQuery.append(" WHERE ppoNo = :ppoNo AND caseStatus = :caseStatus");

                lQuery = ghibSession.createQuery(lSBQuery.toString());

                lQuery.setParameter("ppoNo", lStrPPONo);
                lQuery.setParameter("caseStatus",lStrCaseStatus);

                List lLstVO = lQuery.list();

                if (lLstVO != null && lLstVO.size() > 0)
                {
                    lObjTrnPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Exception occurred in getTrnPensionRqstHdrVO() method of PensionRecoveryInfoDAOImpl Class "
                    + e, e);
            throw (e);
        }
        return lObjTrnPensionRqstHdr;
    }
}
