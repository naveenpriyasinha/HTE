package com.tcs.sgv.lcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcChequeBook;

public class LcChequeBookDAOImpl 
extends GenericDaoHibernateImpl implements LcChequeBookDAO
{
	public LcChequeBookDAOImpl(Class<TrnLcChequeBook> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory); 
    }
	
	final Logger glogger=Logger.getLogger(LcDivisionAccMstDAOImpl.class);	
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	
  /* public List getChequeBookDtls(String lStrLocShrtNm,int lILangId) 
   {
	
	Session hibSession = getSession();
	ArrayList lArrChequeBook = new ArrayList();
	LcDivisionInformationVO lcDivInformationVO = null;		
	
	String lStrChequeBookQuery = "select chb.lc_acc_no,chb.issue_dt,chb.chq_sr_start,chb.chq_sr_end, d.loc_name,d.loc_short_name"+
								 " from trn_lc_cheque_book chb,mst_lc_division_account acc,cmn_location_mst d where chb.lc_acc_no =(select acc.lc_acc_no"+
								 " from mst_lc_division_account acc where acc.division_id =(select d.loc_id"+
								 " from cmn_location_mst d where d.loc_short_name ='"+lStrLocShrtNm+"' and d.lang_id="+lILangId+"))"+
								 " and d.loc_id=acc.division_id and acc.lc_acc_no=chb.lc_acc_no  order by chb.chq_sr_start ";
    glogger.info("---------lStrChequeBookQuery in  LcChequeBookDAOImpl is ::"+lStrChequeBookQuery);
	
	Query lSQLQuery = hibSession.createSQLQuery(lStrChequeBookQuery);
	List lResList = lSQLQuery.list();
	Iterator it = lResList.iterator();
	while(it.hasNext())
	{	
		Object[] tuple = (Object[])it.next();
		String lStrLcAccNo = (tuple[0].toString());
		String lStrLcIssueDt = (tuple[1].toString());
		String lStrLcChqSrFrom = (tuple[2].toString());
		String lStrLcChqSrTo = (tuple[3].toString());	
		String lStrLcDivNm = (tuple[4].toString());
		String lStrLcDivCode = (tuple[5].toString());
		
		lcDivInformationVO = new LcDivisionInformationVO();	
		
		lStrLcIssueDt=changeDateFormat(lStrLcIssueDt);
		
		lcDivInformationVO.setLc_order_id(lStrLcAccNo);
		lcDivInformationVO.setLc_valid_from(lStrLcIssueDt);
		lcDivInformationVO.setDepartment_name(lStrLcChqSrFrom);
		lcDivInformationVO.setDistrict_name(lStrLcChqSrTo);
		lcDivInformationVO.setDivision_name(lStrLcDivNm);
		lcDivInformationVO.setDivisionId(lStrLcDivCode);
		
		glogger.info("------DAO CHQ SR START--------"+lcDivInformationVO.getDepartment_name());
		glogger.info("------DAO CHQ SR END--------"+lcDivInformationVO.getDistrict_name());
		
		lArrChequeBook.add(lcDivInformationVO);		
		
	}
	
		return lArrChequeBook;
   }*/
   
   public List getChequeBookDtls(String lStrDivCode,int lILangId) 
   {	
	 ArrayList lArrChequeBook = new ArrayList();
	 LcDivisionInformationVO lcDivInformationVO = null;		
	 
	 glogger.info("---------DIVISION CODE IN DAO----------------"+lStrDivCode);
	 
	 Connection lCon = null;
     PreparedStatement lStmt = null;
     ResultSet lRs = null;
     
     String lStrLocCode="";
     String lStrLocName="";
     String lStrLocShrtNm="";
     String lStrAccNo="";
     
     String lStrChqSrStart="";
     String lStrChqSrEnd="";
     String lStrChqBkDate="";
     try
     {
     	 lCon = DBConnection.getConnection();
         StringBuffer lSBuff = new StringBuffer();
         
         lSBuff.append("select loc.location_code loc_code, loc.loc_short_name locShrtNm ,loc.loc_name loc_name, ");
         lSBuff.append("(select acc.lc_acc_no from mst_lc_division_account acc where acc.division_code= ");
         lSBuff.append("(select loc.location_code from cmn_location_mst loc where loc.location_code='"+lStrDivCode+"' and loc.lang_id="+lILangId+")) acc_no ");
         lSBuff.append("from cmn_location_mst loc ");
         lSBuff.append("where loc.location_code='"+lStrDivCode+"' and loc.lang_id="+lILangId);         
         
         glogger.info("---------QUERY 1----------------"+lSBuff.toString());
         
         lStmt = lCon.prepareStatement( lSBuff.toString() );
         lRs = lStmt.executeQuery();
        
         if(lRs.next())
         {         	
        	 if(lRs.getObject("loc_code") != null)
        	    lStrLocCode = lRs.getString("loc_code");
        	 if(lRs.getObject("loc_name") != null)
        	    lStrLocName = lRs.getString("loc_name");
        	 if(lRs.getObject("locShrtNm") != null)
        		 lStrLocShrtNm = lRs.getString("locShrtNm");
        	 if(lRs.getObject("acc_no") != null)
        	    lStrAccNo = lRs.getString("acc_no");
         }
         
         lSBuff = new StringBuffer();
         glogger.info("---------ACC NO----------------"+lStrAccNo);
         if(!lStrAccNo.equals(""))
         {
        	 glogger.info("---------IN IF lStrAccNo---------------");
        	 lSBuff.append("select TO_CHAR(bk.issue_dt,'dd/MM/yyyy') dat,bk.chq_sr_start strt,bk.chq_sr_end endd ");
        	 lSBuff.append("from trn_lc_cheque_book bk ");
        	 lSBuff.append("where bk.lc_acc_no='"+lStrAccNo+"' order by bk.chq_book_id");
        	 
        	 glogger.info("---------QUERY 2----------------"+lSBuff.toString());
        	 
        	 lStmt = lCon.prepareStatement( lSBuff.toString() );
             lRs = lStmt.executeQuery();
            
            	
        	 while(lRs.next())
             {         	
            	 if(lRs.getObject("dat") != null)
            		 lStrChqBkDate = lRs.getString("dat");
            	 if(lRs.getObject("strt") != null)
            		 lStrChqSrStart = lRs.getString("strt");
            	 if(lRs.getObject("endd") != null)
            		 lStrChqSrEnd = lRs.getString("endd");
            	 
            	 lcDivInformationVO = new LcDivisionInformationVO();
         		
         		 lcDivInformationVO.setLc_order_id(lStrAccNo);
         		 lcDivInformationVO.setLc_valid_from(lStrChqBkDate);
         		 lcDivInformationVO.setDepartment_name(lStrChqSrStart);
         		 lcDivInformationVO.setDistrict_name(lStrChqSrEnd);
         		 lcDivInformationVO.setDivision_name(lStrLocName);
         		 lcDivInformationVO.setDivisionId(lStrLocCode);
         		 lcDivInformationVO.setDepartmentCode(lStrLocShrtNm);
         		 
         		lArrChequeBook.add(lcDivInformationVO);	
             } 
        	 if(lArrChequeBook.size()==0)
        	 {
        		 glogger.info("---------IN IF lArrChequeBook---------------");
        		 lcDivInformationVO = new LcDivisionInformationVO();
         		
         		 lcDivInformationVO.setLc_order_id(lStrAccNo);
         		 lcDivInformationVO.setLc_valid_from(lStrChqBkDate);
         		 lcDivInformationVO.setDepartment_name(lStrChqSrStart);
         		 lcDivInformationVO.setDistrict_name(lStrChqSrEnd);
         		 lcDivInformationVO.setDivision_name(lStrLocName);
         		 lcDivInformationVO.setDivisionId(lStrLocCode);
         		 lcDivInformationVO.setDepartmentCode(lStrLocShrtNm);
         		 
         		lArrChequeBook.add(lcDivInformationVO);	
        	 }            
         }
         else
         {
        	 glogger.info("---------IN ELSE---------------");        	
        	 glogger.info("---------IN RETURN DUE TO INVALID DIV CODE---------------");
        	 return lArrChequeBook;
            
         }
         
     }
     catch( SQLException se )
     {
         se.printStackTrace();
     	glogger.error( "SQLException::"+se.getMessage(), se );
        
     }
     catch( Exception e )
     {
         e.printStackTrace();
     	glogger.error( "Exception::"+e.getMessage(), e );
     }
	
	
	
		
	return lArrChequeBook;
   }

	
   public String getChequeSeriesValidation(long lIChqSrStart,long lIChqSrEnd) 
   {
	
	Session hibSession = getSession();
	ArrayList lArrChequeBook = new ArrayList();
	LcDivisionInformationVO lcDivInformationVO = new LcDivisionInformationVO();		
	
	String lStrChequeBookQuery = "select b.chq_book_id from trn_lc_cheque_book b where (("+lIChqSrStart+" >= b.chq_sr_start) and ("+lIChqSrStart+" <= b.chq_sr_end)) "+
								 " or (("+lIChqSrEnd+" <= b.chq_sr_end) and ("+lIChqSrEnd+" >= b.chq_sr_start)) or "+
								 " ((("+lIChqSrStart+" <= (select min(b.chq_sr_start) from trn_lc_cheque_book)) and ("+lIChqSrEnd+" >= (select max(b.chq_sr_end) from trn_lc_cheque_book))))";
	
    glogger.info("---------lStrChequeBookQuery in  LcChequeBookDAOImpl is ::"+lStrChequeBookQuery);
	
	Query lSQLQuery = hibSession.createSQLQuery(lStrChequeBookQuery);
	List lResList = lSQLQuery.list();
	Iterator it = lResList.iterator();
	String lStrResult="";
	while(it.hasNext())
	{	
		Object tuple = (Object)it.next();		
		lStrResult = tuple.toString();
	}
	
		return lStrResult;
   }
   
   public boolean saveLcChequeBook(TrnLcChequeBook distVo)
	{
		try
		{
		Session s= getSession();
		
		s.save(distVo);
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
