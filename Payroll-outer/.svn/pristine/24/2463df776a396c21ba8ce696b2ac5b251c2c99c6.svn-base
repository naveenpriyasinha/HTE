package com.tcs.sgv.lcm.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcChequeBook;

public class LcChequeReconciliationDAOImpl
extends GenericDaoHibernateImpl implements LcChequeReconciliationDAO
{
	public LcChequeReconciliationDAOImpl(Class<TrnLcChequeBook> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory); 
    }
	
	final Logger glogger=Logger.getLogger(LcDivisionAccMstDAOImpl.class);	
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	
	public LcDivisionInformationVO getChequeReconciliationDtls(long lIChequeNo) 
    {
	
		Session hibSession = getSession();		
		LcDivisionInformationVO lcDivInformationVO = null;	
		
		String lStrChqDt = "";
		String lStrChqAmt = "";
		String lStrChqCancelDt="";
		String lStrChqClrDt="";
		
		String lStrChqDtlsQuery = "select chq.cheque_dt,chq.cheque_amt,chq.chq_cancel_dt,chq.chq_clr_dt from trn_lc_cheque_posting chq " +
								  "where chq.cheque_no="+lIChequeNo;

		glogger.info("---------lStrChqDtlsQuery in  getChequeReconciliationDtls is ::"+lStrChqDtlsQuery);
		
		Query lSQLQuery = hibSession.createSQLQuery(lStrChqDtlsQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		while(it.hasNext())
		{	
			Object[] tuple = (Object[])it.next();
			if(tuple[0] != null)
				lStrChqDt = (tuple[0].toString());
			if(tuple[1] != null)
				lStrChqAmt = (tuple[1].toString());
			if(tuple[2] != null)
				lStrChqCancelDt = (tuple[2].toString());
			if(tuple[3] != null)
				lStrChqClrDt = (tuple[3].toString());
			
			lcDivInformationVO = new LcDivisionInformationVO();	
			
			lcDivInformationVO.setLc_order_id(lStrChqDt); //Cheque Date
			lcDivInformationVO.setOpening_lc(lStrChqAmt);//chq amt
			lcDivInformationVO.setDepartmentCode(lStrChqCancelDt);// Chq Cancel Dt
			lcDivInformationVO.setDepartment_name(lStrChqClrDt);//Chq Clear Dt
				
	    }
	
		return lcDivInformationVO;
   }
	
  	 
   public boolean updateChqClearDateInReconciliation(long lLngChqNo,String lStrChqClrDt)	
   {
	   try
		{		    
		    glogger.info("-----CHQ CLR DAO UPDATE------------"+lLngChqNo);
		    glogger.info("-----CHQ CLR DAO UPDATE------------"+lStrChqClrDt);
		    
		    Connection lCon= getSession().connection();
			Statement lStmt= lCon.createStatement();
			glogger.info("-----CHQ CLR DAO UPDATE 1------------"+lLngChqNo);
			int row =lStmt.executeUpdate("update  trn_lc_cheque_posting set chq_clr_dt=to_date('"+lStrChqClrDt+"','dd/MM/yyyy') where cheque_no="+lLngChqNo+" and chq_cancel_dt is null ");
			glogger.info("-----CHQ CLR DAO UPDATE 2------------"+row);
		    if(row > 0)
			   return true;
		    else
		       return false;
		}
		catch(Exception e)
		{
			glogger.error("Error in dssdataserviceimpl", e);
			return false;
		}

   }
}
