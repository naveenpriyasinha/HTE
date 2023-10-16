package com.tcs.sgv.pension.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class TrnVoucherDetailsDAOImpl extends GenericDaoHibernateImpl<TrnBillRegister, Long>  implements TrnVoucherDetailsDAO
{
    
    Log gLogger = LogFactory.getLog(getClass());
    
    public TrnVoucherDetailsDAOImpl(Class<TrnBillRegister> type,SessionFactory sessionFactory)
    {
    	super(type);        
        setSessionFactory(sessionFactory);
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
		    		"WHERE budmjrHd =:majorHead AND " +
		    		" voucherDate BETWEEN :fromDate and :toDate " +
		    		"  AND locationCode =:locationCode GROUP BY budmjrHd "); 
		    
		    Calendar cln = Calendar.getInstance();
		    cln.set(Calendar.DAY_OF_MONTH, 1);
		    
		    lQuery = hibSession.createQuery(query.toString());		    
		    lQuery.setParameter("majorHead",lStrMjrHd);		    
		    lQuery.setParameter("fromDate",cln.getTime());		    
		    lQuery.setParameter("toDate",cln.getTime());		    
		    lQuery.setParameter("locationCode",locationCode);	
	
		    List oVoucherNo = lQuery.list();		
		    if(oVoucherNo != null && oVoucherNo.size() > 0)
		    {
		    	
		    	{
		    		java.math.BigDecimal voucher = (java.math.BigDecimal)oVoucherNo.get(0);
		    		if(voucher != null)
		    		{				
		    			maxVoucherNum = voucher.longValue();
		    		}
		    	}
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
}
