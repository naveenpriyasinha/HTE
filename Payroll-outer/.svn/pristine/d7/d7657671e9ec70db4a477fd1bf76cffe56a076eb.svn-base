package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeRate;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeSpecial;
import com.tcs.sgv.pension.valueobject.RltPensionRevisedPayment;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

public class NewPensionBillDAOImpl implements NewPensionBillDAO {
	
	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory = null;
	
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

	public NewPensionBillDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    public Session getSession() 
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
	
	
	/**
     * get {@link TrnPensionRqstHdr} VO
     * 
     * Written By Sagar
     */

	public TrnPensionRqstHdr getPPONoFromPnsnerCode(String lStrPnsnerCode, String lStrStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.pensionerCode = :lPnsnerCode AND A.status = :lStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lPnsnerCode", lStrPnsnerCode.toString());
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
     * get {@link TrnPensionItCutDtls} VO
     * 
     * Written By Sagar
     */

	
	public ArrayList<TrnPensionItCutDtls> getTrnPensionItCutDtls(String lStrPensionerCode) throws Exception
	{
		List <TrnPensionItCutDtls> lLstobjTrnPensionItCutDtls = new ArrayList<TrnPensionItCutDtls>();
		
		try
		{
            Session ghibSession = getSession();
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM  TrnPensionItCutDtls H WHERE H.pensionerCode = :lPnsrCode ");
	        
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode.toString());
	       
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	for(int i=0;i<lLstVO.size();i++)
	            	{
	            		lLstobjTrnPensionItCutDtls.add((TrnPensionItCutDtls) lLstVO.get(i));
	            	}
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return (ArrayList<TrnPensionItCutDtls>) lLstobjTrnPensionItCutDtls;
	}
	
	/**
     * get {@link TrnPensionRecoveryDtls} VO
     * 
     * Written By Sagar
     */

	public Double getTrnPensionRecoveryDtls(String lStrPensionerCode, String lStrStatus) throws Exception
	{
		Double lPensionRecovery = 0D;
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT SUM(A.amount) FROM TrnPensionRecoveryDtls A WHERE A.pensionerCode = :lPnsrCode AND A.recoveryFromFlag = :lStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode);
	        lQuery.setParameter("lStatus", lStrStatus);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
                    if(lLstVO.get(0) != null)
                    {
                        lPensionRecovery = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lPensionRecovery;
	}
    
    /**
     * get Totat Recovery For the Given Month.
     * 
     * Written By Sagar
     */
    public Double getTotalRecoveryForMonth(String lStrPensionerCode,int ForMonth) throws Exception
    {
        Double lTotalRecoveryAmt = 0D;
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT sum(rc.amount) FROM TrnPensionRecoveryDtls rc WHERE rc.pensionerCode = :lPensionerCode");
            lSBQuery.append(" And rc.fromMonth <= :lForMonth AND rc.toMonth >= :lForMonth"); 
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
            lQuery.setParameter("lPensionerCode", lStrPensionerCode);
            lQuery.setParameter("lForMonth", ForMonth);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0) != null)
                    {
                        lTotalRecoveryAmt = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lTotalRecoveryAmt;
    }
	
    /**
     * get Totat Pension Cut (PT And PP) For the Given Month.
     * 
     * Written By Sagar
     */
    public Double getTotalPensionCutForMonth(String lStrPensionerCode,int ForMonth) throws Exception
    {
        Double lTotalPensionCutAmt = 0D;
        
        String lStrPPCut = bundleConst.getString("CUT.PP");
        String lStrPTCut = bundleConst.getString("CUT.PT");
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT SUM(it.amount) FROM TrnPensionItCutDtls it WHERE it.pensionerCode = :lPensionerCode");
            lSBQuery.append(" AND (it.typeFlag = :lPTCut AND it.fromMonth <= :lForMonth AND it.toMonth >= :lForMonth)"); 
            lSBQuery.append(" OR (it.typeFlag = :lPPCut AND it.pensionerCode = :lPensionerCode)");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lPTCut", lStrPTCut);
            lQuery.setParameter("lPPCut", lStrPPCut);
            lQuery.setParameter("lPensionerCode", lStrPensionerCode);
            lQuery.setParameter("lForMonth", ForMonth);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0) != null)
                    {
                        lTotalPensionCutAmt = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lTotalPensionCutAmt;
    }
    
    /**
     * get Totat Other Benefit (PermanentBenefit & TemporaryBenefit) For the Given Month.
     * 
     * Written By Sagar
     */
    public Double getTotalOtherBenefitsForMonth(String lStrPensionerCode,int ForMonth) throws Exception
    {
        Double lTotalBenefitsAmt = 0D;
        
        String lStrPBenefit = bundleConst.getString("CMN.PBENEFIT");
        String lStrTBenefit = bundleConst.getString("CMN.TBENEFIT");
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT SUM(it.amount) FROM TrnPensionItCutDtls it WHERE it.pensionerCode = :lPensionerCode");
            lSBQuery.append(" AND (it.typeFlag = :lTBenefit AND it.fromMonth <= :lForMonth AND it.toMonth >= :lForMonth)"); 
            lSBQuery.append(" OR (it.typeFlag = :lPBenefit AND it.pensionerCode = :lPensionerCode)");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lPBenefit", lStrPBenefit);
            lQuery.setParameter("lTBenefit", lStrTBenefit);
            lQuery.setParameter("lPensionerCode", lStrPensionerCode);
            lQuery.setParameter("lForMonth", ForMonth);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0) != null)
                    {
                        lTotalBenefitsAmt = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lTotalBenefitsAmt;
    }
    
    /**
     * get Totat Special Cut (SP) For the Given Month.
     * 
     * Written By Sagar
     */
    public Double getTotalSpecialCutForMonth(String lStrPensionerCode,int ForMonth) throws Exception
    {
        Double lTotalSpecialCutAmt = 0D;
        
        String lStrSTCut = bundleConst.getString("CUT.ST");
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT SUM(it.amount) FROM TrnPensionItCutDtls it WHERE it.pensionerCode = :lPensionerCode");
            lSBQuery.append(" AND it.typeFlag = :lSTCut AND it.fromMonth <= :lForMonth AND it.toMonth >= :lForMonth"); 
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lSTCut", lStrSTCut);
            lQuery.setParameter("lPensionerCode", lStrPensionerCode);
            lQuery.setParameter("lForMonth", ForMonth);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0) != null)
                    {
                        lTotalSpecialCutAmt = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lTotalSpecialCutAmt;
    }
    
    /**
     * get Totat Pensioner IT Cut For the Given Month.
     * 
     * Written By Sagar
     */
    public Double getTotalITCutForMonth(String lStrPensionerCode,int ForMonth) throws Exception
    {
        Double lTotalPnsnerITCutAmt = 0D;
        
        String lStrITCut = bundleConst.getString("CUT.IT");
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT SUM(it.amount) FROM TrnPensionItCutDtls it WHERE it.pensionerCode = :lPensionerCode");
            lSBQuery.append(" AND it.typeFlag = :lITCut AND it.fromMonth <= :lForMonth AND it.toMonth >= :lForMonth"); 
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lITCut", lStrITCut);
            lQuery.setParameter("lPensionerCode", lStrPensionerCode);
            lQuery.setParameter("lForMonth", ForMonth);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0) != null)
                    {
                        lTotalPnsnerITCutAmt = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lTotalPnsnerITCutAmt;
    }
    
	/**
     * get {@link TrnPensionRqstHdr} VO
     * 
     * Written By Sagar
     */

	public RltPensioncaseBill getRltPensioncaseBillPK(String lStrPPONO,String lStrBillType,String lStrStatus) throws Exception
	{
		RltPensioncaseBill lobjRltPensioncaseBill = null;
				
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append(" FROM RltPensioncaseBill R WHERE R.ppoNo = :lppoNo AND R.billType = :lBillType AND R.status = :lStatus");
	        	
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lppoNo", lStrPPONO.toString());
	        lQuery.setParameter("lBillType", lStrBillType);
	        lQuery.setParameter("lStatus", lStrStatus);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjRltPensioncaseBill = (RltPensioncaseBill) lLstVO.get(0);	            	
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lobjRltPensioncaseBill;
	}	
	
    /**
     * get {@link TrnPensionBillDtls} VO
     * 
     * Written By Sagar
     */

    public TrnPensionBillDtls getTrnPensionBillDtls(Long TrnPensionBillHdrPK) throws Exception
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

    
    /**
     * get {@link TrnPensionArrearDtls} VO
     * 
     * Written By Sagar
     */
    public List<TrnPensionArrearDtls> getTrnPensionArrearDtls(String lStrPensionerCode,String lStrBillNo) throws Exception
    {
        List <TrnPensionArrearDtls> lLstobjTrnPensionArrearDtls = new ArrayList<TrnPensionArrearDtls>();
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" FROM  TrnPensionArrearDtls A WHERE A.pensionerCode = :lPnsrCode AND A.billNo = :lBillNo ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lPnsrCode", lStrPensionerCode);
            lQuery.setParameter("lBillNo", lStrBillNo);
           
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    for(int i=0;i<lLstVO.size();i++)
                    {
                        lLstobjTrnPensionArrearDtls.add((TrnPensionArrearDtls) lLstVO.get(i));
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

    return (List<TrnPensionArrearDtls>) lLstobjTrnPensionArrearDtls;
    }
    
    /**
     * Feching Applied ROP(s) of Pensioner.
     * 
     * Written By Sagar
     */
    public List<TrnPnsncaseRopRlt> getROPAppliedToPensner(String lStrPPONO) throws Exception
    {
    	List <TrnPnsncaseRopRlt> lPnsnerCaseRopLst = new ArrayList<TrnPnsncaseRopRlt>();
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" FROM TrnPnsncaseRopRlt r WHERE r.ppoNo = :lPPONO ORDER BY r.rop ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lPPONO", lStrPPONO);
           
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    for(int i=0;i<lLstVO.size();i++)
                    {
                    	lPnsnerCaseRopLst.add((TrnPnsncaseRopRlt) lLstVO.get(i));
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

    return (List<TrnPnsncaseRopRlt>) lPnsnerCaseRopLst;
    }
    
    
    /**
     * Getting New ROP Amount accordign to ROP and column.
     * 
     * Written By Sagar
     */

	public Double getNewBasicFromROPMst(String lStrROP, String lStrColumnNo, Double lStrOldBasic) throws Exception
	{
		Double lDNewBasicAmt = 0D;
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        if(lStrROP != null && lStrROP.equalsIgnoreCase("1986"))
	        {
	        	lSBQuery.append(" SELECT column"+lStrColumnNo+"Amount FROM MstPensionRop1986 ");
	        }
	        else if(lStrROP != null && lStrROP.equalsIgnoreCase("1996"))
	        {
	        	lSBQuery.append(" SELECT newAmount FROM MstPensionRop1996 ");
	        }
	        
	        lSBQuery.append(" WHERE oldAmount = :lOldBasic");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lOldBasic", new BigDecimal(lStrOldBasic));	        
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
                    if(lLstVO.get(0) != null)
                    {
                    	lDNewBasicAmt = Double.valueOf(lLstVO.get(0).toString());
                    }
                }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lDNewBasicAmt;
	}
	
	
	/**
     * Getting Rate or Amount from Rlt_Pension_Headcode_Rate 
     * accordign to HeadCode, FieldType, and ForDate. 
     * Written By Sagar
     */

	public RltPensionHeadcodeRate getRateFromHeadcodeRateRlt(Long lHeadcode, String lStrFieldType, Double lStrPnsnBasic, Date lForDate) throws Exception
	{
		RltPensionHeadcodeRate lObjPensionHeadcodeRate = null;
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
            // Format a Date for compare between from and to date.
            String lStrForDate = new SimpleDateFormat("dd/MMM/yyyy").format(lForDate);
            
	        lSBQuery.append(" FROM RltPensionHeadcodeRate ");
	        lSBQuery.append(" WHERE fieldType = :lFieldType AND headCode = :lHeadcode");
	        
	        lSBQuery.append(" AND ((effectiveFromDate <= '"+lStrForDate+"' AND effectiveToDate >= '"+lStrForDate+"') OR ");
	        lSBQuery.append(" (effectiveFromDate <= '"+lStrForDate+"' AND effectiveToDate IS NULL)) ");
	        //lSBQuery.append(" ");	        
	        
	        if(lStrFieldType.equalsIgnoreCase("TI") && lStrPnsnBasic != 0)
	        {
	        	if(lStrPnsnBasic <= 1750)
	        	{
	        		lSBQuery.append(" AND uptoBasic = 1750 ");
	        	}
	        	else if(lStrPnsnBasic <= 3000)
	        	{
	        		lSBQuery.append(" AND uptoBasic = 3000 ");
	        	}
	        	else
	        	{
	        		lSBQuery.append(" AND uptoBasic = 999999");
	        	}
	        }
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lFieldType", lStrFieldType);
	        lQuery.setParameter("lHeadcode", new BigDecimal(lHeadcode));
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
                    if(lLstVO.get(0) != null)
                    {
                    	lObjPensionHeadcodeRate = (RltPensionHeadcodeRate) lLstVO.get(0);
                    }
                }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lObjPensionHeadcodeRate;
	}
	
	
	/**
     * Getting Rate or Amount from Rlt_Pension_Headcode_Special 
     * accordign to HeadCode, FieldType, and ForDate. 
     * Written By Sagar
     */
	public RltPensionHeadcodeSpecial getBasicFromHeadcodeSpecialRlt(Long lHeadcode, Double lStrOldPnsnBasic, Date lForDate) throws Exception
	{
		RltPensionHeadcodeSpecial lObjPensionHeadcodeSpecialVO = null;
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
            // Format a Date for compare between from and to date.
            String lStrForDate = new SimpleDateFormat("dd/MMM/yyyy").format(lForDate);
            
	        lSBQuery.append(" SELECT * FROM RltPensionHeadcodeSpecial ");
	        lSBQuery.append(" WHERE headCode = :lHeadcode");
	        
	        lSBQuery.append(" AND ((fromDate <= '"+lStrForDate+"' AND toDate >= '"+lStrForDate+"') OR ");
	        lSBQuery.append(" (fromDate <= '"+lStrForDate+"' AND toDate IS NULL)) ");
	        //lSBQuery.append(" ");	        
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lOldPnsnBasic", lStrOldPnsnBasic);
	        lQuery.setParameter("lHeadcode", lHeadcode);
	        
	        List lLstVO = lQuery.list();
	          
            if(lLstVO != null && lLstVO.size() > 0)
            {
                if(lLstVO.get(0) != null)
                {
                	lObjPensionHeadcodeSpecialVO = (RltPensionHeadcodeSpecial)lLstVO.get(0);
                }
            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error is : " + e, e);
 		  throw(e);
	   }

		return lObjPensionHeadcodeSpecialVO;
	}
	
	/**
     * Feching PK of ROP(s) of Pensioner.
     * 
     * Written By Sagar
     */
    public Long getPKOfPnsncaseROPRlt(String lStrPPONO,String lStrROP) throws Exception
    {
    	Long lPnsnRopRltPK = null;
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append("SELECT r.pnsncaseRopRltId FROM TrnPnsncaseRopRlt r WHERE r.ppoNo = :lPPONO and r.rop = :lROP ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lPPONO", lStrPPONO);
            lQuery.setParameter("lROP", lStrROP);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                	lPnsnRopRltPK = Long.parseLong(lLstVO.get(0).toString());
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

    return lPnsnRopRltPK;
    }
    
    
    /**
     * Feching Payment Month for the given forMonth .
     * 
     * Written By Sagar
     */
    public RltPensionRevisedPayment getPaymentMnthDtls(long lPensionRatePk,String lStrFieldType,String lStrForDate) throws Exception
    {
    	RltPensionRevisedPayment lRevisedPaymentVo = null;
        
        try
        {
        	Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" FROM RltPensionRevisedPayment where headcodeRatePk = :PensionRatePk and fieldType = :FieldType " );
            lSBQuery.append(" And forPayMonth <= '"+lStrForDate+"' AND toPayMonth >= '"+lStrForDate+"'");
	        
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("FieldType", lStrFieldType);
            lQuery.setParameter("PensionRatePk", lPensionRatePk);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0)!= null)
                    {
                    	lRevisedPaymentVo = (RltPensionRevisedPayment) lLstVO.get(0);
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

    return lRevisedPaymentVo;
    }
    

}
