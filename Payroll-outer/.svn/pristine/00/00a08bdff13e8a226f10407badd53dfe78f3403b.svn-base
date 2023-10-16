package com.tcs.sgv.lcm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
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

public class LcChequeCancelDAOImpl 
extends GenericDaoHibernateImpl implements LcChequeCancelDAO
{
	public LcChequeCancelDAOImpl(Class<TrnLcChequeBook> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory); 
    }
	
	final Logger glogger=Logger.getLogger(LcDivisionAccMstDAOImpl.class);	
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	
	public LcDivisionInformationVO getChequeDtls(long lIChequeNo) 
    {
	
		Session hibSession = getSession();		
		LcDivisionInformationVO lcDivInformationVO = null;	
		
		String lStrChqDt = "";
		String lStrChqAmt = "";
		String lStrPartyName = "";
		String lStrAdviceName = "";
		String lStrDivCode = "";
		String lStrAvlBal = "";
		String lStrDivId = "";
		String lStrChqClrDt = "";
		String lStrChqCancelDt = "";
		
		
		String lStrChqDtlsQuery = "select to_char(chq.cheque_dt,'dd/MM/yyyy'),chq.cheque_amt,chq.party_name,dtl.advice_no, "+
								  " (select mst.location_code from cmn_location_mst mst where mst.location_code = dtl.division_code) div,"+
								  " (select dist.lc_available_amt from trn_lc_distribution dist where dist.division_code = dtl.division_code"+
								  " and dist.line_cntr = (select max(d.line_cntr) from trn_lc_distribution d where d.division_code=dtl.division_code)),dtl.division_code, "+
								  " chq.chq_clr_dt, chq.chq_cancel_dt "+
								  " from trn_lc_dtl_posting dtl,trn_lc_cheque_posting chq"+
								  " where chq.lc_exp_id = dtl.lc_exp_id and chq.cheque_no="+lIChequeNo  ;
		
	    glogger.info("---------lStrChqDtlsQuery in  LcChequeCancelDAOImpl is ::"+lStrChqDtlsQuery);
		
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
				lStrPartyName = (tuple[2].toString());
			if(tuple[3] != null)
				lStrAdviceName = (tuple[3].toString());	
			if(tuple[4] != null)
				lStrDivCode = (tuple[4].toString());
			if(tuple[5] != null)
				lStrAvlBal = (tuple[5].toString());
			if(tuple[6] != null)
				lStrDivId = (tuple[6].toString());			 
			if(tuple[7] != null)
				lStrChqClrDt = (tuple[7].toString());
			if(tuple[8] != null)
				lStrChqCancelDt = (tuple[8].toString());
			
			lcDivInformationVO = new LcDivisionInformationVO();	
			
			lcDivInformationVO.setLc_order_id(lStrAdviceName); //advice no
			lcDivInformationVO.setOpening_lc(lStrChqAmt);//chq amt
			lcDivInformationVO.setLc_valid_from(lStrChqDt);//chq dt
			lcDivInformationVO.setDepartment_name(lStrPartyName);//party name			
			lcDivInformationVO.setDivisionId(lStrDivCode);//div code
			lcDivInformationVO.setDivision_name(lStrAvlBal);//Available Balance
			lcDivInformationVO.setDistrict_name(lStrDivId);//Division Id
			lcDivInformationVO.setDepartmentCode(lStrChqClrDt);//Chq Clear Date
			lcDivInformationVO.setDistrictCode(lStrChqCancelDt);//Chq Cancel Date
		
	}
	
		return lcDivInformationVO;
   }
	
 public String getAdviceStatusByCheque(long lLngChqNo)	
   {
	    String lStrAdviceStatus="";   
	    try
		{	
		   glogger.info("-----CHQ CANCEL DAO UPDATE------------"+lLngChqNo);
		    Connection lCon= getSession().connection();
			Statement lStmt= lCon.createStatement();
			glogger.info("-----CHQ CANCEL DAO UPDATE 1------------"+lLngChqNo);
			String Query="select dtl.advice_approved from trn_lc_dtl_posting dtl,trn_lc_cheque_posting chq where chq.lc_exp_id=dtl.lc_exp_id and chq.cheque_no="+lLngChqNo;
			ResultSet lRs =lStmt.executeQuery(Query);
			glogger.info("-----CHQ CANCEL DAO UPDATE 2------------");
		    if(lRs.next())
		    {
		    	if(lRs.getObject("advice_approved") != null )
		    		lStrAdviceStatus=lRs.getString("advice_approved");
		    }
		    	
		}
		catch(Exception e)
		{
			glogger.error("Error in dssdataserviceimpl", e);			
		}
		return lStrAdviceStatus;
   }
	 
   public boolean updateChqCancel(long lLngChqNo, String lStrCancelReason)	
   {
	   try
		{		    
			glogger.info("-----CHQ CANCEL DAO UPDATE------------"+lLngChqNo);
		    Connection lCon= getSession().connection();
			Statement lStmt= lCon.createStatement();
			glogger.info("-----CHQ CANCEL DAO UPDATE 1------------"+lLngChqNo);
			int row =lStmt.executeUpdate("update  trn_lc_cheque_posting set chq_cancel_dt=sysdate,chq_cancel_reason='"+lStrCancelReason+"' where cheque_no="+lLngChqNo);
			glogger.info("-----CHQ CANCEL DAO UPDATE 2------------"+row);
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
