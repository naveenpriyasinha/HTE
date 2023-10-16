package com.tcs.sgv.pension.report;

/**
 * For Common Dyanamic Reports.
 * 
 * @author Sagar
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class CommonReportDAOImpl implements CommonReportDAO
{
    /* Global Variable for Logger Class */
    private Log logger = LogFactory.getLog(getClass());

    private SessionFactory sessionFactory = null;

    /* Global Variable for Session Class */
    
    
    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

    public CommonReportDAOImpl(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getSession() 
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
    
    /**
     * Create For Getting a Header Detail of Pension Ledger Report
     * 
     * @author Sagar
     */
    public List getLedgerRptHeaderDtl(String lStrPPONO,String lStrLocId,long lLangId) throws Exception
    {
        List resList = null;
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT l.locName,l.locAddr1,l.locAddr2,");
            lSBQuery.append(" h.firstName || ' ' || h.middleName || ' ' || h.lastName, r.ppoNo, r.schemeType,");
            lSBQuery.append(" (SELECT b.bankName FROM MstBank b WHERE b.bankCode = d.bankCode AND b.langId = '1' and b.locationCode = :lStrLocId),");
            lSBQuery.append(" (SELECT rb.branchName FROM RltBankBranch rb WHERE rb.branchCode = d.branchCode and rb.locationCode = :lStrLocId), d.acountNo");
            lSBQuery.append(" FROM MstPensionerHdr h, MstPensionerDtls d, TrnPensionRqstHdr r, CmnLocationMst l");
            lSBQuery.append(" WHERE h.pensionerCode = d.pensionerCode AND r.pensionerCode = h.pensionerCode ");
            lSBQuery.append(" AND r.caseStatus = :lStatus AND h.caseStatus = :lStatus AND d.caseStatus = :lStatus");        
            lSBQuery.append(" AND l.cmnLanguageMst.langId = :lLangId AND r.ppoNo = :lppoNo AND l.locationCode = :lStrLocId");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
            lQuery.setParameter("lppoNo", lStrPPONO);
            lQuery.setParameter("lStrLocId", lStrLocId);
            lQuery.setParameter("lLangId", lLangId);
            lQuery.setParameter("lStatus", "APPROVED");
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    resList = lLstVO;
                }
        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw(e);
        }        
        return resList;
    }
    
    /**
     * Create For Getting a Details of Pension Ledger Report for Monthly
     * 
     * @author Sagar
     */
    public List getLedgerRptDtl(String lStrPPONO,Integer lFromMonth, Integer lToMonth) throws Exception
    {
        List resList = new ArrayList();
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT hd.billType,hd.forMonth,d.pensionAmount,d.pensnCutAmount,");
            lSBQuery.append(" d.dpAmount, d.tiAmount, d.medicalAllowenceAmount, d.cvpMonthAmount, d.personalPensionAmount,");
            lSBQuery.append(" d.irAmount, d.arrearAmount, d.tiArrearAmount, d.specialCutAmount,");
            lSBQuery.append(" d.reducedPension, d.incomeTaxCutAmount,");
            lSBQuery.append(" d.recoveryAmount,hd.billDate");
            lSBQuery.append(" FROM TrnPensionBillHdr hd,TrnPensionBillDtls d");
            lSBQuery.append(" WHERE hd.trnPensionBillHdrId = d.trnPensionBillHdrId");        
            lSBQuery.append(" AND d.ppoNo = :lppoNo ");
            
            if(lFromMonth != null && lFromMonth > 0 && lToMonth != null && lToMonth > 0)
            {
            	lSBQuery.append(" AND hd.forMonth > :lFromMonth AND hd.forMonth < :lToMonth");
            }
            
            lSBQuery.append(" ORDER BY hd.forMonth , hd.billType DESC");
            //lSBQuery.append(" ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
            lQuery.setParameter("lppoNo", lStrPPONO);
            
            if(lFromMonth != null && lFromMonth > 0)
            {
                lQuery.setParameter("lFromMonth", lFromMonth);
            }
            
            if(lToMonth != null && lToMonth > 0)
            {
                lQuery.setParameter("lToMonth", lToMonth);
            }
                        
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    resList = lLstVO;
                }
        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw(e);
        }        
        return resList;
    }
    
    /**
	 * Methods for Pension Payment Report
	 */
	public List getPensionPaymentData1 (String ppoNo) throws Exception
    {
	Log gLogger = LogFactory.getLog(getClass());
    
	
	ArrayList arrInner = null;;
			
    try
    {
        Session hiSession  = getSession();
        StringBuilder lSBQuery  = new StringBuilder();
         
		lSBQuery.append(" SELECT  tr.ppo_no,mh.first_name,mh.middle_name,mh.last_name,mh.pensner_addr, ");
		lSBQuery.append(" (tr.basic_pension_amount + ( tr.basic_pension_amount * tr.dp_percent / 100 )) AS basic, ");
		lSBQuery.append(" (tr.basic_pension_amount + ( tr.basic_pension_amount * tr.dp_percent / 100 ) - tr.cvp_monthly_amount) AS reducedPnsnh ");	
		
		lSBQuery.append(" FROM trn_pension_rqst_hdr tr, mst_pensioner_hdr mh");
				
		lSBQuery.append(" WHERE tr.pensioner_code = mh.pensioner_code ");
		lSBQuery.append(" AND tr.case_status = 'APPROVED'");
		lSBQuery.append(" AND mh.case_status = 'APPROVED'");
		lSBQuery.append(" AND tr.ppo_no = '" + ppoNo + "'");
								
		Query hbQuery = hiSession.createSQLQuery(lSBQuery.toString());
		
		List resList = hbQuery.list(); 	

		if (resList != null && !resList.isEmpty()) 
		{
						
			Iterator it = resList.iterator();
						
			while(it.hasNext()) 
			{
				Object[] tuple = (Object[])it.next();
			    
				arrInner = new ArrayList();
				
				arrInner.add(tuple[0]); //PPO NO. 
				arrInner.add(tuple[1]); //First Name
				arrInner.add(tuple[2]); //Middle Name
				arrInner.add(tuple[3]); //Last Name 
				arrInner.add(tuple[4]); //Address
				arrInner.add(tuple[5]); //Basic
				arrInner.add(tuple[6]); //Reduced Pension
			}
         }
        
    }
    catch (Exception e)
    {
        gLogger.error("Error is " + e, e);
        throw e;
    }
    return arrInner;
   }

 
 public List getPensionPaymentData2 (String ppoNo,Integer year) throws Exception
    {
	Log gLogger = LogFactory.getLog(getClass());
    
	ArrayList arrList = null;
	ArrayList arrInner = null;;
	
    try
    {
        Session hiSession  = getSession();
        StringBuilder lSBQuery  = new StringBuilder();
         
		lSBQuery.append(" SELECT dh.for_month,(dt.reduced_pension + dt.income_tax_cut_amount + dt.recovery_amount ) AS paidAmount, ");
		lSBQuery.append(" dt.income_tax_cut_amount,dt.arrear_amount ");
		lSBQuery.append(" FROM trn_pension_bill_dtls dt, trn_pension_bill_hdr dh ");	
		lSBQuery.append(" WHERE dt.trn_pension_bill_hdr_id = dh.trn_pension_bill_hdr_id");
		lSBQuery.append(" AND dh.bill_type IN ('Monthly','PENSION') ");
		lSBQuery.append(" AND dt.ppo_no = '"+ppoNo+"'");
		lSBQuery.append(" AND dh.for_month >=" + (year)+"03" +" AND dh.for_month <= " + (year+1)+"02");
		lSBQuery.append(" ORDER BY dh.for_month");
								
		Query hbQuery = hiSession.createSQLQuery(lSBQuery.toString());
		
		List resList = hbQuery.list(); 	

		if (resList != null && !resList.isEmpty()) 
		{
			arrList=new ArrayList();
			
			Iterator it = resList.iterator();
						
			while(it.hasNext()) 
			{
				Object[] tuple = (Object[])it.next();
			    
				arrInner = new ArrayList();
				
				arrInner.add(tuple[0]); //For Month 
				arrInner.add(tuple[1]); //Paid Amount
				arrInner.add(tuple[2]); //IT Cut Amount
				arrInner.add(tuple[3]); //Arrear Amount
		       
				arrList.add(arrInner);
			}
         }
        
    }
    catch (Exception e)
    {
        gLogger.error("Error is " + e, e);
        throw e;
    }
    return arrList;
   }
 
 	public List<TrnPensionRqstHdr> getPaidRqstList(String frmDate, String toDate) throws Exception
 	{
 		List<TrnPensionRqstHdr> lLstRqstHdr = new ArrayList<TrnPensionRqstHdr>();
 		
 		try
 		{
 			Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.paidDate <= '"+toDate+"' ");
            if(frmDate != null)
            {
            	lSBQuery.append(" AND A.paidDate >= '"+frmDate+"' ");
            }
            lSBQuery.append(" ORDER BY A.paidDate ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
            //lQuery.setParameter("toDate", toDate);
            //lQuery.setParameter("frmDate", frmDate);
            logger.info(">>>>>>>>>>>> the query is:::"+lQuery);
            logger.info("toDate"+toDate+"frmDate"+frmDate);
            List lLstVO = lQuery.list();
            logger.info("lLstVO.size()"+lLstVO.size());
            if(lLstVO != null && lLstVO.size() > 0)
            {
            	lLstRqstHdr = lLstVO;
            }
 		}
 		catch(Exception e)
 		{
 			 logger.error("Error is " + e, e);
 			 throw(e);
 		}
 		return lLstRqstHdr;
 	}
 	
 	public ArrayList getBilldtls(String penCode) throws Exception
 	{
 		ArrayList lArrBillDtls = new ArrayList();
 		
 		try{
 			Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT A.reducedPension, B.forMonth FROM TrnPensionBillDtls A,TrnPensionBillHdr B ");
            lSBQuery.append(" WHERE A.trnPensionBillHdrId = B.trnPensionBillHdrId ");
            lSBQuery.append(" AND A.pensionerCode = :penCode AND B.billType = 'PENSION' ) ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
            lQuery.setParameter("penCode", penCode);
            
            List lLstVO = lQuery.list();
              
            if(lLstVO != null && lLstVO.size() > 0)
            {
            	Object[] tuple = (Object[])lLstVO.get(0);
            	lArrBillDtls.add(tuple[0]);
            	lArrBillDtls.add(tuple[1]);
            	
            	logger.info("tuple[0]"+tuple[0]);
            	logger.info("tuple[1]"+tuple[1]);
            }
 		}
 		catch(Exception e)
 		{
 			logger.error("Error is."+e,e);
 			throw(e);
 		}
 		return lArrBillDtls;
 	}
 	
 	public Double getPensionCut(String penCode, String forMonth) throws Exception
 	{
 		Double lPensionCut = 0D;
 		
 		/*select sum(i.amount) from trn_pension_it_cut_dtls i 
 		 where i.pensioner_code = 10001000421
 		 and ((i.type_flag = 'PT' and i.from_month <= '200804' and i.to_month >= '200804') or i.type_flag = 'PP')*/
 		
 		try{
 			Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" select sum(i.amount) from TrnPensionItCutDtls i ");
            lSBQuery.append(" where i.pensionerCode = "+penCode);
            lSBQuery.append(" and ((i.typeFlag = 'PT' and i.fromMonth <= '"+forMonth+"' and i.toMonth >= '"+forMonth+"') or i.typeFlag = 'PP') ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
           // lQuery.setParameter("forMonth", forMonth);
            
            List lLstVO = lQuery.list();
              
            if(lLstVO != null && lLstVO.size() > 0)
            {
            	if(lLstVO.get(0) != null)
            	{
            		lPensionCut = Double.parseDouble(lLstVO.get(0).toString());
            	}
            }
 		}
 		catch(Exception e)
 		{
 			logger.error("Error is."+e,e);
 			throw(e);
 		}
 		return lPensionCut;
 	}
 	
 	public List getFamilyDtls(String penCode) throws Exception
 	{
 		List lLstFamDet = new ArrayList();
 		
 		/*select f.name from mst_pensioner_family_dtls f
 		where f.pensioner_code = 10001000421
 		and f.percentage > 0*/
 		
 		try{
 			Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" select f.name from MstPensionerFamilyDtls f ");
            lSBQuery.append(" where f.pensionerCode = "+penCode);
            lSBQuery.append(" and f.percentage > 0 ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    
            List lLstVO = lQuery.list();
              
            if(lLstVO != null && lLstVO.size() > 0)
            {
            	for(int i=0; i<lLstVO.size(); i++)
            	{
            		lLstFamDet.add(lLstVO.get(i));
            	}
            }
 		}
 		catch(Exception e)
 		{
 			logger.error("Error is."+e,e);
 			throw(e);
 		}
 		return lLstFamDet;
 	}
	public Map getRecoverChallans(Map inputMap)
 	{
	     List resultList;
	     List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsMCA = new ArrayList<TrnPensionRecoveryDtls>();
	     List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsHBA = new ArrayList<TrnPensionRecoveryDtls>();
	     List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsOTHRS = new ArrayList<TrnPensionRecoveryDtls>();
	     try
	     {
	         Session ghibSession = getSession();
	         StringBuilder strQuery = new StringBuilder();
	         String lStrPPONo = (String) inputMap.get("ppoNo");
	         String lstrFromDate = (String) inputMap.get("FromDate");
	         String lstrToDate = (String) inputMap.get("ToDate");
	         strQuery.append(" SELECT TPR.recoveryType,TPR.accountNumber,TPR.edpCode,TPR.mjrhdCode,TPR.submjrhdCode,TPR.minhdCode,TPR.subhdCode,TPR.amount");
	         strQuery.append(" FROM TrnPensionRecoveryDtls TPR,RltBillCheque RBC,TrnChequeDtls TCD WHERE TPR.pensionerCode IN (SELECT pensionerCode FROM TrnPensionRqstHdr TRH where TRH.ppoNo ='"+lStrPPONo+"') ");
	         strQuery.append(" AND TCD.chequeId = RBC.chequeId AND RBC.billNo = TPR.billNo AND TCD.chqDsptchDate >='"+lstrFromDate+"' AND TCD.chqDsptchDate <= '"+lstrToDate+"'");
	         Query hqlQuery = ghibSession.createQuery(strQuery.toString());
	         resultList = hqlQuery.list();
	         if (resultList != null && resultList.size() > 0)
	            {
	                Iterator it = resultList.iterator();
	                while (it.hasNext())
	                {
	                	 Object tuple[] = (Object[]) it.next();
	                	 if(tuple[0] != null)
	                	 {
	                		 if(tuple[0].equals("MCA"))
	                		 {
	                			 TrnPensionRecoveryDtls  lObjVo = new TrnPensionRecoveryDtls();
	                			 lObjVo.setRecoveryType(tuple[0].toString());
	                			 if(tuple[1] != null)
	                			 {
	                				 lObjVo.setAccountNumber(tuple[1].toString());
	                			 }
	                			 if(tuple[2] != null)
	                			 {
	                				 lObjVo.setEdpCode(tuple[2].toString());
	                			 }
	                			 if(tuple[3] != null)
	                			 {
	                				 lObjVo.setMjrhdCode(tuple[3].toString());
	                			 }
	                			 if(tuple[4] != null)
	                			 {
	                				 lObjVo.setSubmjrhdCode(tuple[4].toString());
	                			 }
	                			 if(tuple[5] != null)
	                			 {
	                				 lObjVo.setMinhdCode(tuple[5].toString());
	                			 }
	                			 if(tuple[6] != null)
	                			 {
	                				 lObjVo.setSubhdCode(tuple[6].toString());
	                			 }
	                			 if(tuple[7] != null)
	                			 {
	                				 lObjVo.setAmount( new BigDecimal(tuple[7].toString()));
	                			 }
	                			lLstTrnPensionRecoveryDtlsMCA.add(lObjVo);
	                		 }
	                		 else if(tuple[0].equals("HBA"))
	                		 {
	                			 TrnPensionRecoveryDtls  lObjVo = new TrnPensionRecoveryDtls();
	                			 lObjVo.setRecoveryType(tuple[0].toString());
	                			 if(tuple[1] != null)
	                			 {
	                				 lObjVo.setAccountNumber(tuple[1].toString());
	                			 }
	                			 if(tuple[2] != null)
	                			 {
	                				 lObjVo.setEdpCode(tuple[2].toString());
	                			 }
	                			 if(tuple[3] != null)
	                			 {
	                				 lObjVo.setMjrhdCode(tuple[3].toString());
	                			 }
	                			 if(tuple[4] != null)
	                			 {
	                				 lObjVo.setSubmjrhdCode(tuple[4].toString());
	                			 }
	                			 if(tuple[5] != null)
	                			 {
	                				 lObjVo.setMinhdCode(tuple[5].toString());
	                			 }
	                			 if(tuple[6] != null)
	                			 {
	                				 lObjVo.setSubhdCode(tuple[6].toString());
	                			 }
	                			 if(tuple[7] != null)
	                			 {
	                				 lObjVo.setAmount( new BigDecimal(tuple[7].toString()));
	                			 }
	                			lLstTrnPensionRecoveryDtlsHBA.add(lObjVo);
	                		 }
	                		 else
	                		 {
	                			 TrnPensionRecoveryDtls  lObjVo = new TrnPensionRecoveryDtls();
	                			 lObjVo.setRecoveryType(tuple[0].toString());
	                			 if(tuple[1] != null)
	                			 {
	                				 lObjVo.setAccountNumber(tuple[1].toString());
	                			 }
	                			 if(tuple[2] != null)
	                			 {
	                				 lObjVo.setEdpCode(tuple[2].toString());
	                			 }
	                			 if(tuple[3] != null)
	                			 {
	                				 lObjVo.setMjrhdCode(tuple[3].toString());
	                			 }
	                			 if(tuple[4] != null)
	                			 {
	                				 lObjVo.setSubmjrhdCode(tuple[4].toString());
	                			 }
	                			 if(tuple[5] != null)
	                			 {
	                				 lObjVo.setMinhdCode(tuple[5].toString());
	                			 }
	                			 if(tuple[6] != null)
	                			 {
	                				 lObjVo.setSubhdCode(tuple[6].toString());
	                			 }
	                			 if(tuple[7] != null)
	                			 {
	                				 lObjVo.setAmount( new BigDecimal(tuple[7].toString()));
	                			 }
	                			lLstTrnPensionRecoveryDtlsOTHRS.add(lObjVo);
	                		 }
	                	 }
	                }
	            }
	         inputMap.put("MCA",lLstTrnPensionRecoveryDtlsMCA);
	         inputMap.put("HBA",lLstTrnPensionRecoveryDtlsHBA);
	         inputMap.put("OTHERS",lLstTrnPensionRecoveryDtlsOTHRS);
	     }
	     catch(Exception e)
	     {
    		logger.error("BALA CHANDRA"+e,e);
	     }
	     return inputMap;
 	}
}
