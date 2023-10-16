package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnHeadVouchers;
import com.tcs.sgv.pension.valueobject.TrnPensionPsbDtls;

public class TrnPensionPsbDtlsDAOImpl extends GenericDaoHibernateImpl<TrnPensionPsbDtls, Long>  implements TrnPensionPsbDtlsDAO
{
 private Session ghibSession = null;
    
    Log gLogger = LogFactory.getLog(getClass());
    
    public TrnPensionPsbDtlsDAOImpl(Class<TrnPensionPsbDtls> type,SessionFactory sessionFactory)
    {
    	super(type);        
        setSessionFactory(sessionFactory);
        ghibSession = sessionFactory.getCurrentSession();
    } 
    public Date getBillDateFromVoucher(String voucherNo) throws Exception 
	{
		Date billDate = null;
		Query lQuery;
		List lLst;
	    try
	    {			
	    	StringBuffer query = new StringBuffer();
			Session hibSession = getSession();
		    long lLngVoucherNo = Long.parseLong(voucherNo);
		    
		    query.append(" SELECT tb.billDate FROM TrnBillRegister tb " +
		    		"WHERE tb.voucherNo = :voucherNo "); 
		    
		    lQuery = hibSession.createQuery(query.toString());		    
		    lQuery.setParameter("voucherNo",lLngVoucherNo);		    
		    lLst = lQuery.list();
		    
		    if(lLst != null && lLst.size() > 0)
            {
		    	billDate = (Date)lLst.get(0);
            }
	    }
		catch(Exception e)
		{
			gLogger.error("Error is : " + e,e);
			throw(e);
		}
	    return billDate;
	}
    
    public long getPKForVoucher(String voucherNo) throws Exception 
	{
		long myPK = 0;
		Query lQuery;
		List lLst;
	    try
	    {			
	    	StringBuffer query = new StringBuffer();
			Session hibSession = getSession();
		    long lLngVoucherNo = Long.parseLong(voucherNo);
		    
		    query.append(" SELECT tb.billNo FROM TrnBillRegister tb " +
		    		"WHERE tb.voucherNo = :voucherNo "); 
		    
		    lQuery = hibSession.createQuery(query.toString());		    
		    lQuery.setParameter("voucherNo",lLngVoucherNo);		    
		    lLst = lQuery.list();
		    
		    if(lLst != null && lLst.size() > 0)
            {
		    	myPK = (Long)lLst.get(0);
            }
	    }
		catch(Exception e)
		{
			gLogger.error("Error is : " + e,e);
			throw(e);
		}
	    return myPK;
	}
    
    public long getNextVoucherMjrHdWiseForPSB(String locationCode,String lStrMjrHd)throws Exception
	{
		TrnBillRegister voucherVo = new TrnBillRegister();
		long maxVoucherNum = 0;
		Query lQuery;
		StringBuffer query = new StringBuffer();
	    try
	    {			
		    Session hibSession = getSession();			    
		    voucherVo.setBudmjrHd(lStrMjrHd);
		    query.append(" SELECT MAX(voucherNo) FROM TrnBillRegister " +
		    		"WHERE budmjrHd =:majorHead " +		    		
		    		"  AND locationCode =:locationCode GROUP BY budmjrHd "); 
		    
		    Calendar cln = Calendar.getInstance();
		    cln.set(Calendar.DAY_OF_MONTH, 1);
		    
		    lQuery = hibSession.createQuery(query.toString());		    
		    lQuery.setParameter("majorHead",lStrMjrHd);		    
		    		    
		    lQuery.setParameter("locationCode",locationCode);	
	
		    List oVoucherNo = lQuery.list();		
		    if(oVoucherNo != null && oVoucherNo.size() > 0)
		    {
	    		long voucher = (Long) oVoucherNo.get(0);		    						
	    		maxVoucherNum = voucher;
		    }
		    maxVoucherNum = maxVoucherNum + 1;
	    }
		catch(Exception e)
		{
			gLogger.error("Error is : " + e,e);
			throw(e);
		}
	    return maxVoucherNum;
	}
    public String validatePPONumberForMonth(String PPONO,int monthYear) throws Exception
	{		
		String errorMsg="";		
		Long validateMonth=0L;
		try
		{			
			Session hibSession = getSession();
			StringBuilder lSBQuery = new StringBuilder(); 
			lSBQuery.append("select count(ppoNo) from TrnPensionPsbDtls where ppoNo = :lPpoNo and monthYear= :lMonthYear");		
			Query lQuery = hibSession.createQuery(lSBQuery.toString());			
		    lQuery.setParameter("lPpoNo", PPONO);
		    lQuery.setParameter("lMonthYear", monthYear);
		    List lLstVO = lQuery.list();		    
            if(lLstVO != null && lLstVO.size() > 0)
            {
            	validateMonth = (Long)lLstVO.get(0);
            }
            if(validateMonth >0)
            {
            	errorMsg = "The Payment for the selected month is already stored";
            }			
		}
		catch(Exception e)
		{
			gLogger.error(" Error is : " + e, e);
	 		throw(e);
		}
		return errorMsg;
	}
    public List getPSBPostedVoucher(String locationCode) throws Exception
	{			
		List lLst;
		Query lQuery;		
		try
		{
			StringBuffer query = new StringBuffer();
			Session hibSession = getSession();
			query.append(" select ppd.voucherNo, prd.headCode, mph.description ,sum(ppd.actualPayment) "+
			" from TrnPensionPsbDtls ppd, TrnPensionRqstHdr prd, MstPensionHeadcode mph "+ 
			" where ppd.ppoNo = prd.ppoNo and prd.locationCode=:lLocationcode and" +
			" mph.headCode = prd.headCode ");
			query.append(" group by prd.headCode, mph.description ,ppd.voucherNo order by ppd.voucherNo desc ");
			lQuery = hibSession.createQuery(query.toString());			
			lQuery.setParameter("lLocationcode", locationCode);
			lLst = lQuery.list();
		}
		catch(Exception e)
		{
			gLogger.error("Error is : " + e,e);
			throw(e);
		}
		return lLst;
	}
	
	public List getHeadVouchers(String fromMonthYear,String toMonthYear,String headCode,String branchCode,String locationCode) throws Exception
	{
		List arrHeadVouchers = new ArrayList();		
		List lLst;
		Query lQuery;		
		int myToMonthYear=0;
		BigDecimal myHeadCode;
		TrnHeadVouchers lObjTrnHeadVouchers;
		try
		{
			StringBuffer query = new StringBuffer();
			Session hibSession = getSession();
			query.append(" select prd.headCode, mph.description, sum(ppd.actualPayment) "+
			" from TrnPensionPsbDtls ppd, TrnPensionRqstHdr prd, MstPensionHeadcode mph "+ 
			" where ppd.ppoNo = prd.ppoNo and prd.locationCode=:lLocationcode and" +
			" mph.headCode = prd.headCode and ppd.voucherNo =0 ");
			if(!fromMonthYear.equals("-1"))
			{
				query.append(" and ppd.monthYear >= "+fromMonthYear);				
			}
			if(!toMonthYear.equals("-1"))
			{
				myToMonthYear = Integer.parseInt(toMonthYear);
				query.append(" and ppd.monthYear <= "+myToMonthYear);				
			}
			if(!headCode.equals("-1"))
			{
				myHeadCode = new BigDecimal(headCode);
				query.append(" and prd.headCode = "+myHeadCode);				
			}
			if(!branchCode.equals("-1"))
			{
				query.append(" and ppd.bankCode ='"+branchCode+"'");				
			}
			query.append(" group by prd.headCode, mph.description ");
			lQuery = hibSession.createQuery(query.toString());			
			lQuery.setParameter("lLocationcode", locationCode);
			lLst = lQuery.list();
			if(lLst != null && !lLst.isEmpty())
			{
				for(Object row : lLst)
				{
					Object cols[] = (Object[]) row;
					lObjTrnHeadVouchers = new TrnHeadVouchers();
					lObjTrnHeadVouchers.setHeadCode((BigDecimal) cols[0]);
					lObjTrnHeadVouchers.setHeadCodeDescription(cols[1].toString());
					String strAmount = cols[2].toString();
					lObjTrnHeadVouchers.setAmount(new BigDecimal(strAmount));
					arrHeadVouchers.add(lObjTrnHeadVouchers);
				}								
			}		
		}
		catch(Exception e)
		{
			gLogger.error("Error is : " + e,e);
			throw(e);
		}
		return arrHeadVouchers;		
	}
	public List updatePPODtlsForDtlPost(String fromMonthYear,String toMonthYear,String dtlPostHeadcode,String bankCode,String voucherNo,String locationCode) throws Exception
	{
		ArrayList arrHeadVouchers = new ArrayList();		
		List lLst;
		Query lQuery;		
		int myToMonthYear=0;		
		
		BigDecimal lBDDtlPostHeadcode = new BigDecimal(dtlPostHeadcode);
		try
		{
			StringBuffer query = new StringBuffer();
			Session hibSession = getSession();
			query.append("select ppd.psbDtlId "+
					" from TrnPensionPsbDtls ppd, TrnPensionRqstHdr prd"+ 
			" where ppd.ppoNo = prd.ppoNo and prd.locationCode=:lLocationcode " +
			" and ppd.voucherNo = 0 and prd.headCode=:lDtlPostHeadcode");
			if(!fromMonthYear.equals("-1"))
			{
				query.append(" and ppd.monthYear >= "+fromMonthYear);				
			}
			if(!toMonthYear.equals("-1"))
			{
				myToMonthYear = Integer.parseInt(toMonthYear);
				query.append(" and ppd.monthYear <= "+myToMonthYear);				
			}			
			if(!bankCode.equals("-1"))
			{
				query.append(" and ppd.bankCode ='"+bankCode+"'");				
			}			
			lQuery = hibSession.createQuery(query.toString());			
			lQuery.setParameter("lLocationcode", locationCode);
			lQuery.setParameter("lDtlPostHeadcode", lBDDtlPostHeadcode);
			lLst = lQuery.list();
			if(lLst != null && !lLst.isEmpty())
			{
				for(Object row : lLst)
				{
					String myPK =  row.toString();
					arrHeadVouchers.add(myPK);
				}								
			}		
		}
		catch(Exception e)
		{
			gLogger.error("Error is : " + e,e);
			throw(e);
		}
		return arrHeadVouchers;		
	}	
}
