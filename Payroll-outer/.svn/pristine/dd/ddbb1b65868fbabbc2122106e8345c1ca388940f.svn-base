package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeChargable;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;


public class MonthlyPensionBillDAOImpl extends GenericDaoHibernateImpl<MstPensionerHdr, Long> implements MonthlyPensionBillDAO{

	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());
	
	/* Global Variable for Session Class */
	private Session ghibSession = null;
	
	
	public MonthlyPensionBillDAOImpl(Class<MstPensionerHdr> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}	
	
	/**
	 *  To display values in combo box Scheme
	 * @param langId long
	 * @return List
	 */
	public List getAllScheme(long langId) throws Exception
	{
		ArrayList arrScheme = new ArrayList();
		try{
			Session hibSession = getSession();
			
			StringBuilder lSBQuery = new StringBuilder();
			
			ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
			String lStrName = bundleConst.getString("MNTH.SCHEMETYPE");
				
			lSBQuery.append(" select c1.lookupName from CmnLookupMst c1,CmnLookupMst c2 ");
	        lSBQuery.append(" where c1.cmnLanguageMst.langId = c2.cmnLanguageMst.langId ");
	        lSBQuery.append(" and c1.cmnLanguageMst.langId = :lLangId ");
	        lSBQuery.append(" and c1.parentLookupId = c2.lookupId ");
	        lSBQuery.append(" and c2.lookupName = :lLookupName ");
	        
	        Query lQuery = hibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lLangId", langId);
	        lQuery.setParameter("lLookupName", lStrName);
			
	        List listScheme = lQuery.list();
			
			if (listScheme!=null && !listScheme.isEmpty()) 
			{	
				Iterator it = listScheme.iterator();
				while(it.hasNext()) 
				{
					ComboValuesVO vo= new ComboValuesVO(  );
					Object tuple = (Object)it.next();
					
					vo.setId(tuple.toString());
					vo.setDesc(tuple.toString());
					arrScheme.add(vo);
				}	
			}	
		}
		catch(Exception e)
		   {
	 		  logger.error(" Error is : " + e, e);
	 		  throw(e);
		   }		
		
		
		return arrScheme;
	}
	
	public List getValidPensionerCode(String bankCode, String branchCode, String headCode, String scheme, String lStrMyCases) throws Exception
	{
		ArrayList lArrPnsnrCode = new ArrayList();  
		try{
			Session hibSession = getSession();
			
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT DISTINCT(M.pensionerCode), M.acountNo,T.ppoNo ");
	        lSBQuery.append(" FROM MstPensionerDtls M,TrnPensionRqstHdr T,TrnPensionerSeenDtls S ");  
	        lSBQuery.append(" WHERE M.pensionerCode = T.pensionerCode ");
	        lSBQuery.append(" AND S.pensionerCode = T.pensionerCode ");
	        lSBQuery.append(" AND S.seenFlage = 'Y' ");
	        lSBQuery.append(" AND M.bankCode = :lBankCode ");
	        lSBQuery.append(" AND M.branchCode = :lBranchCode ");
	        lSBQuery.append(" AND T.headCode = :lHeadCode ");
	        lSBQuery.append(" AND T.schemeType = :lScheme ");
	        if(lStrMyCases != null && lStrMyCases.length() > 0 && !"()".equals(lStrMyCases))
	        {
	        	 //lSBQuery.append(" AND T.pensionRequestId in "+lStrMyCases);
	        	int i  = lStrMyCases.split("~").length;
	        	if(i > 0)
	        	{
	        		 lSBQuery.append(" AND ( T.pensionRequestId in");
	        	}
	        	for(int k = 0;k <i;k++)
	        	{
	        		lSBQuery.append(lStrMyCases.split("~")[k]);
	        		if(i > 1 && k<i-1)
	        		{
	        			lSBQuery.append(" OR T.pensionRequestId in");
	        		}
	        	}
	        }
	        else
	        {
	        	lSBQuery.append(" AND (1 = 2 ");
	        }
	        //lSBQuery.append(" )ORDER BY  to_number(M.acountNo) ");
	        lSBQuery.append(" )ORDER BY  T.ppoNo ");
	        
	        Query lQuery = hibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lBankCode", bankCode);
	        lQuery.setParameter("lBranchCode", branchCode);
	        lQuery.setParameter("lHeadCode", new BigDecimal(headCode));
	        lQuery.setParameter("lScheme", scheme);
	        
	        List listPnsnerCode = lQuery.list();
	        
					
	        if (listPnsnerCode!=null && !listPnsnerCode.isEmpty()) 
			{	
				Iterator it = listPnsnerCode.iterator();
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					lArrPnsnrCode.add(tuple[0]);
				}	
			}	
		}
		 catch(Exception e)
		 {
			 logger.error(" Error is : " + e, e);
			 throw(e);
		 }		
		return lArrPnsnrCode;
	}
	
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtls(String lStrPnsrCode, String lStrStatus, String lStrHeadCode, String lStrCaseStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.pensionerCode = :lpnsrCode ");
	        lSBQuery.append(" AND A.status = :lStatus AND A.headCode = :lHeadCode  ");
	        lSBQuery.append(" AND A.caseStatus = :lCaseStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lpnsrCode", lStrPnsrCode);
	        lQuery.setParameter("lStatus", lStrStatus);
	        lQuery.setParameter("lHeadCode", new BigDecimal(lStrHeadCode));
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
	
	public List getAccountNo(String lStrPensionerCode,String bankCode, String branchCode) throws Exception
	{
		ArrayList lArrPnsnrCode = new ArrayList();  
		try
		{
			Session hibSession = getSession();

			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT DISTINCT M.acountNo ");
	        lSBQuery.append(" FROM MstPensionerDtls M ");
	        lSBQuery.append(" WHERE M.pensionerCode = :lPnsrCode ");
	        lSBQuery.append(" AND M.bankCode = :lBankCode ");
	        lSBQuery.append(" AND M.branchCode = :lBranchCode ");
	                
	        Query lQuery = hibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lBankCode", bankCode);
	        lQuery.setParameter("lBranchCode", branchCode);
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode);
	        
	        List listPnsnerCode = lQuery.list();
			
	        if (listPnsnerCode!=null && listPnsnerCode.size()>0) 
			{	
				Iterator it = listPnsnerCode.iterator();
				while(it.hasNext()) 
				{
					Object tuple = (Object)it.next();
					lArrPnsnrCode.add(tuple);
				}	
			}	
	        	
		}
		catch(Exception e)
		   {
				  logger.error(" Error is : " + e, e);
				  throw(e);
		   }
		return lArrPnsnrCode;
	}
	
	
	public TrnPensionBillHdr getTrnPensionBillHdr(String lStrBillNo) throws Exception
	{
		TrnPensionBillHdr lobjTrnPensionBillHdr = new TrnPensionBillHdr();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
	        lSBQuery.append(" FROM  TrnPensionBillHdr H WHERE H.billNo = :lbillNo ");
	        
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lbillNo", lStrBillNo);
	       
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjTrnPensionBillHdr = (TrnPensionBillHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error(" Error is : " + e, e);
 		  throw(e);
	   }
		return lobjTrnPensionBillHdr;
	}
	
	/**
	 * get {@link TrnPensionBillDtls} VO
	 * @param String: Bill No
	 * @return List of VO: TrnPensionBillDtls
	 */

	public ArrayList<TrnPensionBillDtls> getTrnPensionBillDtls(String lStrBillId) throws Exception
	{
		List <TrnPensionBillDtls> lLstobjTrnPensionBillDtls = new ArrayList<TrnPensionBillDtls>();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
	        lSBQuery.append(" FROM TrnPensionBillDtls A WHERE A.trnPensionBillHdrId = :lBillId ");
	        //lSBQuery.append(" AND A.pensionerCode IN (SELECT s.pensionerCode ");
	        //lSBQuery.append(" from TrnPensionerSeenDtls s where s.seenFlage = 'Y') ");
	        //lSBQuery.append(" ORDER BY  to_number(A.accountNo) ");
	        lSBQuery.append(" ORDER BY  A.ppoNo ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lBillId", new Long(lStrBillId));
	        //lQuery.setMaxResults(500);
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	for(int i=0;i<lLstVO.size();i++)
	            	{
	            		lLstobjTrnPensionBillDtls.add((TrnPensionBillDtls) lLstVO.get(i));
	            	}
	            	
	            }
		 
	   }
	   catch(Exception e)
	   {
			  logger.error(" Error is : " + e, e);
			  throw(e);
	   }
		return (ArrayList<TrnPensionBillDtls>) lLstobjTrnPensionBillDtls;
		
	}
	
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPnsnerCode(String lStrPnsrCode, String lStrStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.pensionerCode = :lpnsrCode AND A.status = :lStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lpnsrCode", lStrPnsrCode);
	        lQuery.setParameter("lStatus", lStrStatus);
	        
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
	
	public Integer getLastMonth(String lStrPnsnrCode, String lStrScheme) throws Exception
	{
		Integer lastMonth = 0;
		
		ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
		
		String lStrBillType1 = bundleConst.getString("MNTH.PENSION1");
		String lStrBillType2 = bundleConst.getString("MNTH.MONTHLY");
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
	        lSBQuery.append(" select max(h.forMonth) from TrnPensionBillHdr h, TrnPensionBillDtls d ");
	        lSBQuery.append(" where h.trnPensionBillHdrId = d.trnPensionBillHdrId ");
	        lSBQuery.append(" and d.pensionerCode = :lpnsrCode and ((h.scheme = :lscheme ");
	        lSBQuery.append(" and h.billType = :lbillType2) or h.billType = :lbillType1) ");
	        //lSBQuery.append(" and (h.billType = :lbillType1 or h.billType = :lbillType2) ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lpnsrCode", lStrPnsnrCode);
	        lQuery.setParameter("lscheme", lStrScheme);
	        lQuery.setParameter("lbillType1", lStrBillType1);
	        lQuery.setParameter("lbillType2", lStrBillType2);
	        
	        List lLstVO = lQuery.list();
	            if(lLstVO != null && !lLstVO.isEmpty())
	            {
	            	if(lLstVO.get(0) != null)
	            	{
	            		lastMonth = Integer.parseInt(lLstVO.get(0).toString());
	            	}
	            		
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error(" Error is : " + e, e);
 		  throw(e);
	   }
	   return lastMonth;
	}


	public Double getRecoveryDtls(String lStrPensionerCode, String lStrStatus, String lForMonth) throws Exception
	{
		Double lRecoveryAmt = 0D;
				
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
	        lSBQuery.append(" SELECT SUM(A.amount) FROM TrnPensionRecoveryDtls A WHERE A.pensionerCode = :lPnsrCode ");
	        if(lStrStatus != null && ! lStrStatus.equals("-1"))
	        {
	        	lSBQuery.append(" AND A.recoveryFromFlag = :lStatus ");
	        }
	        lSBQuery.append(" AND A.fromMonth <= :lForMonth AND A.toMonth >= :lForMonth ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        if(lStrStatus != null && ! lStrStatus.equals("-1"))
	        {
	        	 lQuery.setParameter("lStatus", lStrStatus);
	        }
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode);
	        lQuery.setParameter("lForMonth", Integer.valueOf(lForMonth));
	        
	        List lLst = lQuery.list();
	        
	            if(lLst != null && lLst.size() > 0 && lLst.get(0)!= null)
	            {
            		lRecoveryAmt = Double.parseDouble(lLst.get(0).toString());
	            }
	   }
		catch(Exception e)
	   {
			logger.error(" Error is : " + e, e);
			throw(e);
	   }
		return lRecoveryAmt;
	}
	
	public ArrayList getItCutDtls(String lStrPensionerCode,String lStrForMonth) throws Exception
	{
		ArrayList arrItCutDtls = new ArrayList();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
			lSBQuery.append(" SELECT H.typeFlag,sum(H.amount) FROM TrnPensionItCutDtls H WHERE H.pensionerCode = :lPnsrCode ");
	        lSBQuery.append(" AND ((H.fromMonth <= :lForMonth AND H.toMonth >= :lForMonth and (H.typeFlag <> 'PP' or H.typeFlag <> 'PermanentBenefit')) ");
	        lSBQuery.append(" or (H.typeFlag = 'PP' or H.typeFlag = 'PermanentBenefit')) GROUP BY H.typeFlag ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode.toString());
	        lQuery.setParameter("lForMonth", Integer.valueOf(lStrForMonth.toString()));
	       
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	Iterator itr = lLstVO.iterator();
	            	while (itr.hasNext())
	            	{
	            		Object[] tuple = (Object[])itr.next();
					
						arrItCutDtls.add((String) tuple[0]);
						arrItCutDtls.add((BigDecimal) tuple[1]);
					}
	            }
	   }
	   catch(Exception e)
	   {
	 		  logger.error(" Error is : " + e, e);
	 		  throw(e);
	   }
		return arrItCutDtls;
	}
	
	/*public Double getArrearDtls(String lStrPensionerCode,String lStrForMonth) throws Exception
	{
		Double lArrearAmt = 0D;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT SUM(H.differenceAmount) FROM TrnPensionArrearDtls H WHERE H.pensionerCode = :lPnsrCode ");
	        lSBQuery.append(" AND H.paymentFromYyyymm <= :lForMonth AND H.paymentToYyyymm >= :lForMonth ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode.toString());
	        lQuery.setParameter("lForMonth", Integer.valueOf(lStrForMonth.toString()));
	       
	        List lLst = lQuery.list();
	        
            if(lLst != null && lLst.size() > 0 && lLst.get(0)!= null)
            {
            	lArrearAmt = Double.parseDouble(lLst.get(0).toString());
            }
	   }
	   catch(Exception e)
	   {
	 		  logger.error(" Error is : " + e, e);
	 		  throw(e);
	   }
		return lArrearAmt;
	}*/
	
	public ArrayList getArrearDtls(String lStrPensionerCode,String lStrForMonth) throws Exception
	{
		ArrayList larrArrearDtls = new ArrayList();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
	        lSBQuery.append(" SELECT H.arrearFieldType,SUM(H.differenceAmount) FROM TrnPensionArrearDtls H WHERE H.pensionerCode = :lPnsrCode ");
	        lSBQuery.append(" AND H.paymentFromYyyymm <= :lForMonth AND H.paymentToYyyymm >= :lForMonth GROUP BY H.arrearFieldType ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPnsrCode", lStrPensionerCode.toString());
	        lQuery.setParameter("lForMonth", Integer.valueOf(lStrForMonth.toString()));
	       
	        List lLstVO = lQuery.list();
	        if(lLstVO != null && lLstVO.size() > 0)
            {
            	Iterator itr = lLstVO.iterator();
            	while (itr.hasNext())
            	{
            		Object[] tuple = (Object[])itr.next();
				
            		larrArrearDtls.add((String) tuple[0]);
            		larrArrearDtls.add((BigDecimal) tuple[1]);
				}
            }
	   }
	   catch(Exception e)
	   {
	 		  logger.error(" Error is : " + e, e);
	 		  throw(e);
	   }
		return larrArrearDtls;
	}
	
	/**
	 * Method to get family pensioner details
	 * @param String PensionerCode
	 * @return List
	 */
	public ArrayList<MstPensionerFamilyDtls> getMstPensionerFamilyDtls(String lStrPnsnrCode) throws Exception
	{
		List <MstPensionerFamilyDtls> lLstobjMstPensionerFamilyDtls = new ArrayList<MstPensionerFamilyDtls>();
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
	        lSBQuery.append(" FROM MstPensionerFamilyDtls H WHERE H.pensionerCode = :lPnsrCode ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        lQuery.setParameter("lPnsrCode", lStrPnsnrCode);
	       
	        List lLstVO = lQuery.list();
	        
            if(lLstVO != null && !lLstVO.isEmpty())
            {
            	for(int i=0;i<lLstVO.size();i++)
            	{
            		lLstobjMstPensionerFamilyDtls.add((MstPensionerFamilyDtls) lLstVO.get(i));
            	}
            }
	       
	   }
	   catch(Exception e)
	   {
	 		  logger.error(" Error is : " + e, e);
	 		  throw(e);
	   }
		return (ArrayList<MstPensionerFamilyDtls>) lLstobjMstPensionerFamilyDtls;
	}
	
	
	/**
     * Method to check valid bill
     * @param  String forMonth, String bankCode, String branchCode, String headCode
     * @return boolean
     */
    public boolean isValidBill(String forMonth, String bankCode, String branchCode, String headCode, String scheme) throws Exception {
        StringBuffer lSBQuery = new StringBuffer();
        Session session = getSession();
        try {
            lSBQuery.append(" SELECT max(tpr.forMonth) FROM TrnPensionBillHdr tpr WHERE");
            //lSBQuery.append(" tpr.bankCode = :bankCode ");
            lSBQuery.append(" tpr.branchCode = :branchCode ");
            lSBQuery.append(" AND tpr.headCode = :headCode ");
            lSBQuery.append(" AND tpr.scheme = :scheme ");
            
            Query lQuery = session.createQuery(lSBQuery.toString());

            //lQuery.setParameter("bankCode", bankCode);
            lQuery.setParameter("branchCode", branchCode);
            lQuery.setParameter("headCode", new BigDecimal(headCode));
            lQuery.setParameter("scheme", scheme);
            
            List lList = lQuery.list();
            if (lList != null && lList.size() > 0) {
            	Integer maxMonth = (Integer)lList.get(0);
            	Integer currMonth = Integer.valueOf(forMonth);
            	if(maxMonth != null)
            	{
	            	if(currMonth <= maxMonth)
	            	{
	            		return true;
	            	}
            	}
            }
        } catch (Exception e) {
        	logger.error(" Error is : " + e, e);
            throw e;
        }
        return false;
    }
    
    public String getBankName(String lStrBank) throws Exception
    {
    	String lStrBankName = null;
    	StringBuffer lSBQuery = new StringBuffer();
    	try
    	{
    		ghibSession = getSession();
    		lSBQuery.append(" SELECT B.bankName FROM MstBank B WHERE B.bankCode = :bankCode ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("bankCode", lStrBank);
            
            List lLst = lQuery.list();
	        
            if(lLst != null && lLst.size() > 0 && lLst.get(0)!= null)
            {
            	lStrBankName = lLst.get(0).toString();
            }
    	}
    	catch (Exception e) {
        	logger.error(" Error is : " + e, e);
            throw e;
        }
    	return lStrBankName;
    }
    
    public String getBranchName(String lStrBank, String lStrBranch) throws Exception
    {
    	String lStrBranchName = null;
    	StringBuffer lSBQuery = new StringBuffer();
    	try
    	{
    		ghibSession = getSession();
    		lSBQuery.append(" SELECT B.branchName FROM RltBankBranch B WHERE B.branchCode = :branchCode ");
    		lSBQuery.append(" AND B.bankCode = :bankCode ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("bankCode", Long.valueOf(lStrBank));
            lQuery.setParameter("branchCode", Long.valueOf(lStrBranch));
            
            List lLst = lQuery.list();
	        
            if(lLst != null && lLst.size() > 0 && lLst.get(0)!= null)
            {
            	lStrBranchName = lLst.get(0).toString();
            }
    	}
    	catch (Exception e) {
        	logger.error(" Error is : " + e, e);
            throw e;
        }
    	return lStrBranchName;
    }
    
    public List getMonthDtls(String lStrMonth, Long langId) throws Exception
	{
		List lArrlist = new ArrayList();  
		try{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			/*
			select m.month_code, m.month_name
			from sgva_month_mst m, cmn_language_mst l
			where m.lang_id = l.lang_short_name
			and m.month_no = 2
			and l.lang_short_name = 'en_US'
			*/
	        lSBQuery.append(" select m.monthCode, m.monthName ");
	        lSBQuery.append(" from SgvaMonthMst m, CmnLanguageMst l ");
	        lSBQuery.append(" where m.langId = l.langShortName ");
	        lSBQuery.append(" and m.monthNo = :lMonth ");
	        lSBQuery.append(" and l.langId = :lLangId ");
	      	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lMonth", Byte.parseByte(lStrMonth));
	        lQuery.setParameter("lLangId", langId);

	        List listDtls = lQuery.list();
					
	        if (listDtls!=null && !listDtls.isEmpty()) 
			{	
				Iterator it = listDtls.iterator();
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					lArrlist.add(tuple[0]);
					lArrlist.add(tuple[1]);
				}	
			}	
		}
		 catch(Exception e)
		 {
			 logger.error(" Error is : " + e, e);
			 throw(e);
		 }		
		return lArrlist;
	}
    
    public List getAllBranchPrint(String lLocation) throws Exception
	{
		ArrayList arrBranch = new ArrayList();
		try{
			Session hibSession = getSession();			
			StringBuilder lSBQuery = new StringBuilder();
			//select r.branch_code,r.branch_name from rlt_bank_branch r where r.location_code = 100109
	        lSBQuery.append(" SELECT H.branchCode,H.branchName FROM RltBankBranch H ");
	        lSBQuery.append(" WHERE H.locationCode = :Location ORDER BY H.branchCode ");
	        
	        Query lQuery = hibSession.createQuery(lSBQuery.toString());
	        lQuery.setParameter("Location",lLocation);
	        
	        List listBranch = lQuery.list();
	        if (listBranch!=null && !listBranch.isEmpty()) 
			{	
				Iterator it = listBranch.iterator();
				while(it.hasNext()) 
				{
					ComboValuesVO vo= new ComboValuesVO(  );
					Object[] tuple = (Object[])it.next();
					vo.setId(tuple[0].toString());
					vo.setDesc(tuple[1].toString());
					arrBranch.add(vo);
				}	
			}
		}
		catch(Exception e){
	 		logger.error(" Error is : " + e, e);
	 		throw(e);
		}
		return arrBranch;
	}
    
    public String getBankCode(String lStrBranch, String lStrLocCode) throws Exception
	{
		String bankCode = null;
		try{
			Session hibSession = getSession();			
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" SELECT H.bankCode FROM RltBankBranch H ");
	        lSBQuery.append(" WHERE H.branchCode = :BranchCode ");
	        lSBQuery.append(" AND H.locationCode = :locCode ");
	        
	        Query lQuery = hibSession.createQuery(lSBQuery.toString());
	        lQuery.setParameter("BranchCode", Long.valueOf(lStrBranch));
	        lQuery.setParameter("locCode", lStrLocCode);

	        List listBranch = lQuery.list();
	        if (listBranch!=null && !listBranch.isEmpty()) 
			{	
				bankCode = listBranch.get(0).toString();
			}
		}
		catch(Exception e){
	 		logger.error(" Error is : " + e, e);
	 		throw(e);
		}
		return bankCode;
	}
     
     public String getBillNo(String lStrDate, String lStrBank, String lStrBranch, String lStrHeadcode, String lStrScheme) throws Exception
 	{
 		String billNo = null;
 		/*
 		select hd.bill_no from trn_pension_bill_hdr hd 
 		where hd.for_month = '200803'
 		and hd.bank_code = '21000'
 		and hd.branch_code = '100001'
 		and hd.head_code = 1
 		and hd.scheme = 'IRLA'
 		and hd.bill_type = 'Monthly'
 			*/
 		try{
 			Session hibSession = getSession();
 			
 			StringBuilder lSBQuery = new StringBuilder();
 			
 	        lSBQuery.append(" select hd.billNo from TrnPensionBillHdr hd ");
 	        lSBQuery.append(" where hd.forMonth = :ForMonth ");
 	        //lSBQuery.append(" and hd.bankCode = :BankCode ");
	        lSBQuery.append(" and hd.branchCode = :BranchCode ");
	        lSBQuery.append(" and hd.headCode = :Headcode ");
 	        lSBQuery.append(" and hd.scheme = :Scheme ");
 	        lSBQuery.append(" and hd.billType = 'Monthly' ");
 	        
 	        Query lQuery = hibSession.createQuery(lSBQuery.toString());
 	        lQuery.setParameter("ForMonth", Integer.valueOf(lStrDate));
 	        //lQuery.setParameter("BankCode", lStrBank);
 	        lQuery.setParameter("BranchCode", lStrBranch);
 	        lQuery.setParameter("Headcode", new BigDecimal(lStrHeadcode));
 	        lQuery.setParameter("Scheme", lStrScheme);

 	        List listBill = lQuery.list();
 	        if (listBill!=null && !listBill.isEmpty()) 
 			{	
 	        	billNo = listBill.get(0).toString();
 			}
 		}
 		catch(Exception e){
 	 		logger.error(" Error is : " + e, e);
 	 		throw(e);
 		}
 		return billNo;
 	}
     
     public RltPensionHeadcodeChargable getRltPensionHeadcodeChargable(String headcode) throws Exception
     {
    	 RltPensionHeadcodeChargable lObjHeadChargeable = new RltPensionHeadcodeChargable();
    	 try{
  			Session hibSession = getSession();
  			
  			StringBuilder lSBQuery = new StringBuilder();
  			
  	        lSBQuery.append(" from RltPensionHeadcodeChargable hd ");
  	        lSBQuery.append(" where hd.headCode = :Headcode ");
  	        lSBQuery.append(" and billType = 'PENSION' ");
  	        
  	        Query lQuery = hibSession.createQuery(lSBQuery.toString());
  	        
  	        lQuery.setParameter("Headcode", new Long(headcode));

  	        List listBill = lQuery.list();
  	        if (listBill!=null && !listBill.isEmpty()) 
  			{	
  	        	lObjHeadChargeable = (RltPensionHeadcodeChargable) listBill.get(0);
  			}
  		}
  		catch(Exception e){
  	 		logger.error(" Error is : " + e, e);
  	 		throw(e);
  		}
  		return lObjHeadChargeable;
     }
     
 /*    public ArrayList<TrnPensionBillDtls> getTrnPensionBillDtlsReport(String lStrDate,String lStrBranch,String lStrScheme) throws Exception
 	{
 		List <TrnPensionBillDtls> lLstobjTrnPensionBillDtls = new ArrayList<TrnPensionBillDtls>();
 		
 		try
 		{
 			StringBuilder lSBQuery = new StringBuilder();
 			ghibSession = getSession();
 			
 	        lSBQuery.append(" FROM TrnPensionBillDtls A WHERE A.trnPensionBillHdrId IN ");
 	        lSBQuery.append(" (SELECT H.trnPensionBillHdrId FROM TrnPensionBillHdr H ");
 	        lSBQuery.append(" WHERE H.forMonth = :forMonth AND H.branchCode = :branch AND H.scheme = :scheme) ");
 	        lSBQuery.append(" AND A.pensionerCode IN (SELECT s.pensionerCode ");
 	        lSBQuery.append(" from TrnPensionerSeenDtls s where s.seenFlage = 'Y') ");
 	        lSBQuery.append(" ORDER BY  A.trnPensionBillHdrId, to_number(A.accountNo) ");
 	        
 	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());

 	        lQuery.setParameter("forMonth", Integer.parseInt(lStrDate));
 	        lQuery.setParameter("branch", lStrBranch);
 	        lQuery.setParameter("scheme", lStrScheme);
 	        //lQuery.setMaxResults(500);
 	        List lLstVO = lQuery.list();
 	          
 	            if(lLstVO != null && lLstVO.size() > 0)
 	            {
 	            	for(int i=0;i<lLstVO.size();i++)
 	            	{
 	            		lLstobjTrnPensionBillDtls.add((TrnPensionBillDtls) lLstVO.get(i));
 	            	}
 	            	
 	            }
 		 
 	   }
 	   catch(Exception e)
 	   {
 			  logger.error(" Error is : " + e, e);
 			  throw(e);
 	   }
 		return (ArrayList<TrnPensionBillDtls>) lLstobjTrnPensionBillDtls;
 		
 	}*/
 	
     public List getHeaderDtl(String lStrBranchCode, String lStrLocId,long lLangId) throws Exception
     {
         List resList = null;
         /*
         select l.loc_name,b.bank_name,rb.branch_name,l.location_code from cmn_location_mst l,mst_bank b,rlt_bank_branch rb 
         where l.lang_id = '1'  and l.location_code = '100055' and b.bank_code = rb.bank_code 
         and l.lang_id = b.lang_id and rb.branch_code = '12001' and rb.location_code = l.location_code
         
         select l.locName,b.bankName,rb.branchName from CmnLocationMst l,MstBank b,RltBankBranch rb 
         where b.bankCode = rb.bankCode and rb.locationCode = l.locationCode and l.langId = b.langId 
         and l.langId = '1' and l.locationCode = '100055' and   rb.branchCode = '12001' 
         */
         try
         {
             Session ghibSession = getSession();
             StringBuilder lSBQuery = new StringBuilder();
             
             lSBQuery.append(" select l.locName,b.bankName,rb.branchName from CmnLocationMst l,MstBank b,RltBankBranch rb ");
             lSBQuery.append(" where b.bankCode = rb.bankCode and rb.locationCode = l.locationCode and l.cmnLanguageMst.langId = b.langId ");
             lSBQuery.append(" and l.cmnLanguageMst.langId = '1' and l.locationCode = :lStrLocId and   rb.branchCode = :lBranchCode ");
                         
             Query lQuery = ghibSession.createQuery(lSBQuery.toString());
     
             lQuery.setParameter("lBranchCode", Long.valueOf(lStrBranchCode));
             lQuery.setParameter("lStrLocId", lStrLocId);
            // lQuery.setParameter("lLangId", lLangId);
             
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
}