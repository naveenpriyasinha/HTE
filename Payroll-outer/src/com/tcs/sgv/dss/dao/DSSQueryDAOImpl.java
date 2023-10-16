package com.tcs.sgv.dss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;


public class DSSQueryDAOImpl extends GenericDaoHibernateImpl<DDODetailsVO, Integer> implements
DSSQueryDAO
{
	private static final Logger glogger=Logger.getLogger(DSSQueryDAOImpl.class);
	static
	{
		glogger.info("Inside DSSQueryDAOImpl");
	}
	private Connection lCon = null;
	ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/dss/DSSConstants");
	
	public DSSQueryDAOImpl(Class<DDODetailsVO> type,SessionFactory sessionFactory) 
	{
		super(type);
		glogger.info("Calling Constructor");
		setSessionFactory(sessionFactory);
	}
	public DSSQueryDAOImpl() 
	{
		
		super(DDODetailsVO.class);
		glogger.info("Calling Constructor");
	}
	
	/* Added By Shyamal */

	
	public String getFinfromYear()
	{
		String toDay = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		int year = Integer.parseInt(toDay.substring(0, 4));
		int month = Integer.parseInt(toDay.substring(5, 7));
		
		if(month <=3 && month >=1)
		{
			year = year -1;
		}
	
		String fdate = year+"-04-01"; 
		return fdate;
	}
	
	public ArrayList getTopNPartyDtlRpt(String topN,String toDate,String fromDate,long langId) 
	{			
		ArrayList arrOuterList = null;
		try 
		{
			Session hibSession = getSession();
			String toDay = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
			String fdate = getFinfromYear();
			String query = "select * from (select payDtls.party_name, "+
					       " ddoDtls.ddo_name, "+
					       " payDtls.chq_no, "+
					       " payDtls.amount, "+
					       " locDtls.loc_name "+
					  " from (select pay.party_name, "+
					               " pay.ddo_code, "+
					               " pay.chq_no, "+
					               " pay.amount, "+
					               " pay.tsry_code "+
					          " from rpt_payment_dtls pay "+
					         " where pay.chq_status_code = '"+lObjRsrcBndle.getString("chq_status")+"' and "+
					               " pay.chq_status_dt >= to_date('"+fromDate+"', 'YYYY-MM-DD') and "+
					               " pay.chq_status_dt <= to_date('"+toDate+"', 'YYYY-MM-DD') and "+
					               " pay.active = 'Y' " +
					               " ) payDtls, " +
					       " (select ddo.ddo_code, ddo.ddo_name "+
					          " from org_ddo_mst ddo "+
					         " where ddo.lang_id = "+langId+" ) ddoDtls, "+
					       
					       " (select loc.location_code, loc.loc_name "+
					          " from cmn_location_mst loc "+
					         " where loc.lang_id = "+langId+") locDtls "+
					
					 " where payDtls.ddo_code = ddoDtls.ddo_code and "+
					       " payDtls.tsry_code = locDtls.location_code " +
							 " order by payDtls.amount desc)";
						    if(topN!=null)
							{
									glogger.info("inside top N"+topN);
									 query  +=  " where ROWNUM <= "+topN;
							}
								
							
								
			glogger.info("the parth getTopNPartyDtlRpt query is :-  " + query);
								
			Query sqlQuery=hibSession.createSQLQuery(query);
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			if (resList!=null && resList.size()>0) 
			{
				arrOuterList=new ArrayList();
				Iterator it = resList.iterator();
				double chq = 0;
				double diffChq = 0;
				int lSr = 1;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
					ArrayList arrInner = new ArrayList();
					arrInner.add(lSr);
					arrInner.add(tuple[0]);
					arrInner.add(tuple[1]);
					arrInner.add(tuple[2]);
					if(tuple[3] != null)
					{
						chq = Double.parseDouble(tuple[3].toString());
						chq = chq /10000000;
						long chq1 = Math.round(chq);
						diffChq += (chq- chq1);
						arrInner.add(testNumberFormat.format(chq1));
					}
					else
					{
						arrInner.add("-");
					}
					arrInner.add(tuple[4]);
					arrOuterList.add(arrInner);
					lSr++;
				}
				ArrayList arrLast = new ArrayList();
				arrLast.add("Rounded Diffrence");
				arrLast.add("");
				arrLast.add("");
				arrLast.add("");
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setMinimumFractionDigits(3);
				testNumberFormat.setMaximumFractionDigits(3);
				arrLast.add(testNumberFormat.format(diffChq));
				arrLast.add("");
				arrOuterList.add(arrLast);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}
	
	public ArrayList getRecExpOption(String lStrLangId, String lstrLocId) 
    {
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the locId is "+lstrLocId);
		glogger.info("------------------------------------Inside getRecExpOption--------------------");
		ArrayList arrOfficeName = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String locId=null;
        String lName=null;
        try
        {
            lCon = DBConnection.getConnection(  );
            StringBuffer lsb = new StringBuffer(  );
            
            lsb = new StringBuffer("SELECT lookup_id,lookup_name " +
            		" FROM cmn_lookup_mst " +
            		" where parent_lookup_id in(select lookup_id from cmn_lookup_mst where lookup_name = '"+lObjRsrcBndle.getString("exp_rec")+"')");
            lStmt = lCon.prepareStatement( lsb.toString() );
            lRs = lStmt.executeQuery();
            while(lRs.next())
            {
            	ComboValuesVO vo= new ComboValuesVO(  );
            	locId = lRs.getString("lookup_name");
            	lName= lRs.getString("lookup_name");
                glogger.info("Vlaue" +lName);
                vo.setId(locId);
                vo.setDesc(lName);
                arrOfficeName.add(vo);
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
        return arrOfficeName;
    }
	
	public ArrayList getDurationOption(String lStrLangId, String lstrLocId) 
    {
		glogger.info("-");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the locId is "+lstrLocId);
		glogger.info("------------------------------------Inside getDurationOption--------------------");
		ArrayList arrOfficeName = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String locId=null;
        String lName=null;
        try
        {
            lCon = DBConnection.getConnection(  );
            StringBuffer lsb = new StringBuffer(  );
            /* lsb = new StringBuffer("SELECT lookup_id,lookup_name " +
            		"FROM cmn_lookup_mst " +
            		"where lang_id ="+lStrLangId+
            		" and parent_lookup_id in(select lookup_id from cmn_lookup_mst where lang_id = "+lStrLangId+" and lookup_name = 'optionDuration')"); */
            
            lsb = new StringBuffer("SELECT lookup_id,lookup_name " +
            		"FROM cmn_lookup_mst " +
            		"where parent_lookup_id in(select lookup_id from cmn_lookup_mst where lookup_name = '"+lObjRsrcBndle.getString("optionDuration")+"')");
            lStmt = lCon.prepareStatement( lsb.toString() );
            lRs = lStmt.executeQuery();
            while(lRs.next())
            {
            	ComboValuesVO vo= new ComboValuesVO(  );
            	locId = lRs.getString("lookup_id");
                lName = lRs.getString("lookup_name");
                glogger.info("Vlaue" +lName);
                vo.setId(locId);
                vo.setDesc(lName);
                arrOfficeName.add(vo);
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        	glogger.error( "SQLException::"+se.getMessage(), se );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        return arrOfficeName;
    }
	
	public ArrayList getTopNMjrheadDtlRpt(String option,String topN,String toDate,String fromDate,long langId) 
	{			
		ArrayList arrOuterList = null;
		try 
		{
			String lsb = null;;
			String topn = null;
			Session hibSession = getSession();
			String toDay = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String lStrLangId = getStrLangId(langId) ;
			Connection con = DBConnection.getConnection();
			Statement stmt = null;
			ResultSet rst = null;
			String mjrhd_string = "";
			/*int year = Integer.parseInt(toDay.substring(0, 4));
			int month = Integer.parseInt(toDay.substring(5, 7));
			
			if(month <=3 && month >=1)
			{
				year = year -1;
			}
		
			String fdate = year+"-04-01";*/ 
			String fdate = getFinfromYear();
			if(option != null)
			{
				if(option.equals("Expenditure"))
				{
					glogger.info("in the expenditure.");
					
					
					lsb = "select * from (select bdjDtls.budmjrhd_code, bdjDtls.budmjrhd_desc_long, expDtls.amnt "+
						  " from (select exp.mjr_hd, "+
						               " sum(nvl(exp.gross_amnt, 0) - nvl(exp.recovery_amt, 0)) amnt "+
						          " from rpt_expenditure_dtls exp "+
						         " where exp.exp_dt >= to_date('"+fromDate+"', 'YYYY-MM-DD') and "+
						               " exp.exp_dt <= to_date('"+toDate+"', 'YYYY-MM-DD') and "+
						               " exp.active = 'Y' " +
		
						         " group by exp.mjr_hd) expDtls, "+
						       " (select distinct bdj.budmjrhd_code, bdj.budmjrhd_desc_long "+
						          " from sgva_budmjrhd_mst bdj "+
						          " where bdj.lang_id ='"+lStrLangId+"'  and "+
					                " bdj.fin_yr_id = "+
					                " (select fin_year_id "+
					                   " from sgvc_fin_year_mst "+
					                  " where from_date = to_date('"+fdate+"', 'YYYY-MM-DD'))) bdjDtls "+
						 " where to_char(expDtls.mjr_hd) = bdjDtls.budmjrhd_code " +
						
						 " order by expDtls.amnt desc ) " +
						 " where rownum <= "+topN ; 
					/*
					String lsb1 = "  select sum(expd.gross_amnt - expd.recovery_amt) amnt, expd.mjr_hd mjr" +
								  " from rpt_expenditure_dtls expd "+
								  " where expd.exp_dt >= to_date('"+fromDate+"', 'yyyy-dd-mm') and "+
								        " expd.exp_dt <= to_date('"+toDate+"', 'yyyy-dd-mm') and "+
								        " expd.active = 'Y' "+
								  " group by expd.mjr_hd "+
								  " order by amnt desc";
													
						
					stmt = con.createStatement();
					rst = stmt.executeQuery(lsb1);
					
					while(rst.next())
					{
						mjrhd_string = mjrhd_string + "," +rst.getObject("mjr");
					}
					
					lsb1 = "select "
					*/
					
					
					
					
					

				}
				else if(option.equals("Receipt"))
				{
					
					lsb = " select * from (select bdjDtls.budmjrhd_code, bdjDtls.budmjrhd_desc_long, recDtls.amnt "+
						   " from (select rec.mjr_hd, sum(rec.amount) amnt "+
						           " from rpt_receipt_dtls rec "+
						          " where rec.active = 'Y' and "+
						                " rec.revenue_dt >= to_date('"+fromDate+"', 'YYYY-MM-DD') and "+
						                " rec.revenue_dt <= to_date('"+toDate+"', 'YYYY-MM-DD') " +
						          " group by rec.mjr_hd) recDtls, "+
						        " (select distinct bdj.budmjrhd_code, bdj.budmjrhd_desc_long "+
						           " from sgva_budmjrhd_mst bdj "+
						          " where bdj.lang_id ='"+lStrLangId+"'  and "+
						                " bdj.fin_yr_id = "+
						                " (select fin_year_id "+
						                   " from sgvc_fin_year_mst "+
						                  " where from_date = to_date('"+fdate+"', 'YYYY-MM-DD'))) bdjDtls "+
						  " where recDtls.mjr_hd = bdjDtls.budmjrhd_code" +
						  " order by recDtls.amnt desc )" +
						  " where rownum <= "+topN ;
					glogger.info(" in the receipt.");
				}
			}
			
			glogger.info("the query of TopNmajor head is :- " + lsb );
			
			Query sqlQuery=hibSession.createSQLQuery(lsb);
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			if (resList!=null && resList.size()>0) 
			{
				arrOuterList=new ArrayList();
				Iterator it = resList.iterator();
				int Sr = 1;
				double amnt = 0;
				double diffAmnt = 0;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrInner = new ArrayList();
					NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
					arrInner.add(Sr);
					arrInner.add(tuple[0]);
					arrInner.add(tuple[1]);
					if(tuple[2] != null)
					{
						amnt = Double.parseDouble(tuple[2].toString());
						amnt = amnt /10000000;
						long amnt1 = Math.round(amnt);
						diffAmnt += (amnt- amnt1);
						arrInner.add(testNumberFormat.format(amnt1));
					}
					else
					{
						arrInner.add("-");
					}
					arrOuterList.add(arrInner);
					Sr++;
				}
				ArrayList arrLast = new ArrayList();
				arrLast.add("Rounded Diffrence");
				arrLast.add("");
				arrLast.add("");
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setMinimumFractionDigits(3);
				testNumberFormat.setMaximumFractionDigits(3);
				arrLast.add(testNumberFormat.format(diffAmnt));
				
				arrOuterList.add(arrLast);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}
	
	public ArrayList getTopNBillsDtlRpt(String topN,String toDate,String fromDate, long langId) 
	{			
		
		ArrayList arrOuterList = null;
		try 
		{
			String lsb = null;
			String topn = null;
			Session hibSession = getSession();
		
			String toDay = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			/*int year = Integer.parseInt(toDay.substring(0, 4));
			int month = Integer.parseInt(toDay.substring(5, 7));
			
			if(month <=3 && month >=1)
			{
				year = year -1;
			}
		
			String fdate = year+"-04-01";*/ 
			String fdate = getFinfromYear();
			String query = "select * from (select expDtls.exp_no, "+
				        " expDtls.exp_type_code, "+
				        " expDtls.amnt, "+
				        " ddoDtls.ddo_name, "+
				        " locDtls.loc_name "+
				   " from (select exp.exp_no, "+
				                " exp.exp_type_code, "+
				                " (exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
				                " exp.ddo_code, "+
				                " exp.tsry_code "+
				           " from rpt_expenditure_dtls exp "+
				          " where exp.exp_dt >= to_date('"+fromDate+"', 'YYYY-MM-DD') and "+
				                " exp.exp_dt <= to_date('"+toDate+"', 'YYYY-MM-DD') and " +
				                " exp_type_code = '"+lObjRsrcBndle.getString("Bill")+"' and"+
				                " exp.active = 'Y') expDtls, "+
				        " (select lom.location_code, lom.loc_name "+
				           " from cmn_location_mst lom "+
				          " where lom.lang_id = "+langId+" ) locDtls, "+
				        " (select ddo.ddo_code, ddo.ddo_name "+
				           " from org_ddo_mst ddo "+
				          " where ddo.lang_id = "+langId+" ) ddoDtls "+
				  " where expDtls.ddo_code = ddoDtls.ddo_code and "+
				        " locDtls.location_code = expDtls.tsry_code ";
				
				query += " order by expDtls.amnt desc )";
				
				if(topN!=null)
				{
					glogger.info("inside top N"+topN);
					 query  +=  " where ROWNUM <= "+topN;
				}
				
				glogger.info("The Parth topnbill report query is " + query );
				
			Query sqlQuery=hibSession.createSQLQuery(query);
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			if (resList!=null && resList.size()>0) 
			{
				arrOuterList=new ArrayList();
				Iterator it = resList.iterator();
				int Sr = 1;
				double exp = 0;
				double diffExp = 0;
				
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					
					ArrayList arrInner = new ArrayList();
					NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
					arrInner.add(Sr);
					arrInner.add(tuple[0]);
					arrInner.add(tuple[1]);
					if(tuple[2] != null)
					{
						exp = Double.parseDouble(tuple[2].toString());
						exp = exp /10000000;
						long exp1 = Math.round(exp);
						diffExp += (exp- exp1);
						arrInner.add(testNumberFormat.format(exp1));
					}
					else
					{
						arrInner.add("-");
					}
					arrInner.add(tuple[3]);
					arrInner.add(tuple[4]);
					arrOuterList.add(arrInner);
					Sr++;
				}
				ArrayList arrLast = new ArrayList();
				arrLast.add("Rounded Diffrence");
				arrLast.add("");
				arrLast.add("");
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setMinimumFractionDigits(3);
				testNumberFormat.setMaximumFractionDigits(3);
				arrLast.add(testNumberFormat.format(diffExp));
				arrLast.add("");
				arrLast.add("");
				arrOuterList.add(arrLast);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}
	
	public ArrayList getTopNChallansDtlRpt(String topN,String toDate,String fromDate,long langId) 
	{			
	
		ArrayList arrOuterList = null;
		try 
		{
			String lsb = null;;
			String topn = null;
			Session hibSession = getSession();
		
			String toDay = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		/*	int year = Integer.parseInt(toDay.substring(0, 4));
			int month = Integer.parseInt(toDay.substring(5, 7));
			
			if(month <=3 && month >=1)
			{
				year = year -1;
			}
		
			String fdate = year+"-04-01"; */
			String fdate = getFinfromYear();
			String query = "select * from (select recDtls.rcpt_no, recDtls.mjr_hd, recDtls.amount, locDtls.loc_name "+
					  " from (select rec.rcpt_no, rec.mjr_hd, rec.amount, rec.tsry_code "+
					          " from rpt_receipt_dtls rec "+
					         " where rec.active = 'Y' and " +
					         " rcpt_type_code = '"+lObjRsrcBndle.getString("Challan")+"' and"+
					               " rec.revenue_dt >= to_date('"+fromDate+"', 'YYYY-MM-DD') and "+
					               " rec.revenue_dt <= to_date('"+toDate+"', 'YYYY-MM-DD')) recDtls, "+
					       " (select distinct loc.location_code, loc.loc_name "+
					          " from cmn_location_mst loc "+
					         " where loc.lang_id = "+langId+") locDtls "+
					 " where recDtls.tsry_code = locDtls.location_code";

				
				query += " order by recDtls.amount desc )";
				
				if(topN!=null)
				{
					glogger.info("inside top N"+topN);
					 query  +=  " where ROWNUM <= "+topN;
				}
				
				
				glogger.info("parth getTopNChallansDtlRpt query is :-> " + query);
				
			Query sqlQuery=hibSession.createSQLQuery(query);
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			double ldbchallans = 0;
			double ldbdiffChallans = 0;
			if (resList!=null && resList.size()>0) 
			{
				arrOuterList=new ArrayList();
				Iterator it = resList.iterator();
				int lSr = 1;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					
					ArrayList arrInner = new ArrayList();
					arrInner.add(lSr);
					arrInner.add(tuple[0]);
					arrInner.add(tuple[1]);
					if(tuple[2] != null)
					{
						NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
						ldbchallans = Double.parseDouble(tuple[2].toString());
						ldbchallans = ldbchallans /10000000;
						long ldbchallans1 = Math.round(ldbchallans);
						ldbdiffChallans += (ldbchallans- ldbchallans1);
						arrInner.add(testNumberFormat.format(ldbchallans1));
					}
					else
					{
						arrInner.add("-");
					}
					arrInner.add(tuple[3]);
					arrOuterList.add(arrInner);
					lSr++;
				}
				ArrayList arrLast = new ArrayList();
				arrLast.add("Rounded Diffrence");
				arrLast.add("");
				arrLast.add("");
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setMinimumFractionDigits(3);
				testNumberFormat.setMaximumFractionDigits(3);
				arrLast.add(testNumberFormat.format(ldbdiffChallans));
				arrLast.add("");
				
				arrOuterList.add(arrLast);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}

	public ArrayList getOptionList(String lStrLangId, String lstrLocId) 
    {
		glogger.info("-");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the locId is "+lstrLocId);
		glogger.info("------------------------------------Inside location  Type query--------------------");
		ArrayList arrOfficeName = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String locId=null;
       
        String lName=null;
        try
        {
            lCon = DBConnection.getConnection(  );
            StringBuffer lsb = new StringBuffer(  );
            lsb = new StringBuffer("SELECT lookup_id,lookup_name " +
            		"FROM cmn_lookup_mst " +
            		"where parent_lookup_id in(select lookup_id from cmn_lookup_mst where lookup_name = '"+lObjRsrcBndle.getString("exp_rec")+"')");
            lStmt = lCon.prepareStatement( lsb.toString() );
            lRs = lStmt.executeQuery();
            
            while(lRs.next())
            {
            	ComboValuesVO vo= new ComboValuesVO(  );
            	locId = lRs.getString("lookup_id");
                lName = lRs.getString("lookup_name");
                glogger.info("Vlaue of offoce" +lName);
                vo.setId(locId);
                vo.setDesc(lName);
                arrOfficeName.add(vo);
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
        return arrOfficeName;
    }

	public ArrayList getTopNDDOsDtlRpt(String topN,String toDate,String fromDate,long langId) 
	{			
		ArrayList arrOuterList = null;
		try 
		{
			
			String lsb = null;;
			String topn = null;
			Session hibSession = getSession();
			String finYear = getFinfromYear(); 
			String query = "select * from (select ddoDtls.ddo_name, grantDtls.grantamnt,grantDtls.grantamntc, grantDtlsNP.gamnt, grantDtlsNP.gamntc,expDtls.expamnt "+
						" from "+
						" (select ddo.ddo_name , ddo.ddo_code  from org_ddo_mst ddo where lang_id = "+langId+") ddoDtls, "+
                
						 " (SELECT SUM(S.GRANT_AMOUNT_STATE)  grantamnt , sum(S.Grant_Amount_Css) grantamntc, S.To_Ddo_Code "+
								 " FROM  SGVA_GRANT_ORDER_DETAIL S , sgva_grant_scheme_co_ddo_mpg bdj "+   
								 " where S.FIN_YEAR_ID = (select fin_year_id from sgvc_fin_year_mst where from_date = to_date('"+finYear+"', 'YYYY-MM-DD')) "+
								 " AND S.PLAN_NONPLAN = '"+lObjRsrcBndle.getString("PL")+"' "+ 
								 " AND S.DEMAND_CODE = bdj.demand_code "+   
								 " AND  S.BUDMJRHD_CODE = bdj.budmjrhd_code "+ 
								 " AND S.BUDSUBMJRHD_CODE = bdj.budsubmjrhd_code "+ 
								 " AND  S.BUDMINHD_CODE = bdj.budminhd_code "+  
								 " AND S.BUDSUBHD_CODE = bdj.budsubhd_code "+ 
								 " AND S.To_Co_Code = bdj.co_ddo_code "+ 
								 " AND s.GRANT_RELEASE_STATUS = '"+lObjRsrcBndle.getString("Release")+"' "+ 
								 " group by S.TO_DDO_CODE ) grantDtls, "+ 
                 
							" (SELECT SUM(S.GRANT_AMOUNT_STATE) gamnt , sum(S.GRANT_AMOUNT_CSS) gamntc, S.To_Ddo_Code "+
									 " FROM  SGVA_GRANT_ORDER_DETAIL S , sgva_grant_scheme_co_ddo_mpg bdj "+  
									 " where S.FIN_YEAR_ID = (select fin_year_id from sgvc_fin_year_mst where from_date = to_date('"+finYear+"', 'YYYY-MM-DD')) "+
									 " AND S.PLAN_NONPLAN = '"+lObjRsrcBndle.getString("NP")+"' "+  
									 " AND S.DEMAND_CODE = bdj.demand_code "+   
									 " AND  S.BUDMJRHD_CODE = bdj.budmjrhd_code "+ 
									 " AND S.BUDSUBMJRHD_CODE = bdj.budsubmjrhd_code "+ 
									 " AND  S.BUDMINHD_CODE = bdj.budminhd_code "+  
									 " AND S.BUDSUBHD_CODE = bdj.budsubhd_code "+   
									 " AND S.To_Co_Code = bdj.co_ddo_code "+ 
									 " AND s.GRANT_RELEASE_STATUS = '"+lObjRsrcBndle.getString("Release")+"' "+ 
									 " group by S.TO_DDO_CODE ) grantDtlsNP, "+ 
						 " (select sum(exp.gross_amnt - nvl(exp.recovery_amt,0)) expamnt , exp.ddo_code "+
							       " from rpt_expenditure_dtls exp "+ 
							       " where exp.exp_dt >= to_date('"+fromDate+"', 'YYYY-MM-DD') and "+ 
							       " exp.exp_dt <= to_date('"+toDate+"', 'YYYY-MM-DD') and " +
							       " exp.active = 'Y' " +
							     
							       " group by  exp.ddo_code ) expDtls "+   
						" where "+
							      " grantDtls.To_Ddo_Code = ddoDtls.ddo_code "+
							      " and grantDtlsNP.To_Ddo_Code = ddoDtls.ddo_code "+
							      " and expDtls.ddo_code = ddoDtls.ddo_code ";               
				
				query += " order by expDtls.expamnt desc ) ";
				
				
				if(topN!=null)
				{
					glogger.info("inside top N"+topN);
					query  +=  " where ROWNUM <= "+topN;
					//query += " limit 10";
				}
				
				
				glogger.info("getTopNDDOsDtlRpt report parth query is :- " + query);
			
			Query sqlQuery=hibSession.createSQLQuery(query);
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			int Sr=1;
			if (resList!=null && resList.size()>0) 
			{
				arrOuterList=new ArrayList();
				Iterator it = resList.iterator();
				double grant = 0.0;
				double exp = 0.0;
				double diffExp = 0;
				double diffGrant = 0;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrInner = new ArrayList();
					NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
					arrInner.add(Sr);
					arrInner.add(tuple[0]);
					grant = Double.parseDouble(tuple[1].toString()) + Double.parseDouble(tuple[2].toString()) + Double.parseDouble(tuple[3].toString()) + Double.parseDouble(tuple[4].toString());
					grant = grant /10000;
					long grant1 = Math.round(grant);
					diffGrant += (grant- grant1);
					arrInner.add(testNumberFormat.format(grant1));
					
					if(tuple[5] != null)
					{
						exp = Double.parseDouble(tuple[5].toString());
						exp = exp /10000000;
						long exp1 = Math.round(exp);
						diffExp += (exp- exp1);
						arrInner.add(testNumberFormat.format(exp1));
					}
					else
					{
						arrInner.add("-");
					}
					arrOuterList.add(arrInner);
				}
				ArrayList arrLast = new ArrayList();
				arrLast.add("Rounded Diffrence");
				arrLast.add("");
				
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setMinimumFractionDigits(3);
				testNumberFormat.setMaximumFractionDigits(3);
				arrLast.add(testNumberFormat.format(diffGrant));
				arrLast.add(testNumberFormat.format(diffExp));
				
				arrOuterList.add(arrLast);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}
	
	
	public ArrayList getTopNTreasuryByExpDtlRpt(String topN,String toDate,String fromDate, long langId) {
		
	
		ArrayList arrOuterList = null;
		ArrayList arrReturn = null;
		try 
		{
			String topn = null;
			glogger.info("-------- in the topNT By Expenditure");
			Session hibSession = getSession();
	
			String fyear = getFinfromYear();
			String query = "select tsry.location_code,tsry.loc_name, expDtls.amnt, recDtls.ramnt "+
					"  from (select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
					               " exp.tsry_code "+
					          " from rpt_expenditure_dtls exp "+
					         " where exp.exp_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
					               " exp.exp_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
					               " exp.active = 'Y'  "+
					         " group by exp.tsry_code) expDtls, "+
					       " (select sum(rec.amount - nvl(rec.disbursement_amt, 0)) ramnt, "+
					               " rec.tsry_code "+
					          " from rpt_receipt_dtls rec "+
					         " where rec.revenue_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
					               " rec.revenue_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
					               " rec.active = 'Y'  "+
					         " group by rec.tsry_code) recDtls, "+
					       " (select loc.loc_id,loc.location_code, loc.loc_name "+
					          " from cmn_location_mst loc "+
					         " where loc.lang_id = "+langId+" and "+
					               " loc.department_id in "+
					               " (select department_id "+
					                  " from org_department_mst "+
					                 " where department_code in ('"+lObjRsrcBndle.getString("to")+"', '"+lObjRsrcBndle.getString("pao")+"'))) tsry "+
					 " where expDtls.tsry_code = tsry.location_code and "+
					       " recDtls.tsry_code = tsry.location_code";
			
			
			glogger.info("getTopNTreasuryByExpDtlRpt parth the query is :-  " + query ) ;
			
			
			Query sqlQuery=hibSession.createSQLQuery(query);
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			if (resList!=null && resList.size()>0) 
			{

				arrOuterList=new ArrayList();
				Iterator it = resList.iterator();
				Double exp;
				Double rec;
				float diffExp = 0;
				float diffRec = 0;
				int i =1;
				int Sr = 1;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrTemp = new ArrayList();
					ArrayList arrInner = new ArrayList();
					exp = Double.parseDouble(tuple[2].toString());
					rec = Double.parseDouble(tuple[3].toString());
					arrTemp = getSubExp2(tuple[0].toString(),toDate,fromDate,langId);
					exp += Double.parseDouble(arrTemp.get(0).toString());
					rec += Double.parseDouble(arrTemp.get(1).toString());
					exp = exp /10000000;
					rec = rec/10000000;
					long exp1 = Math.round(exp);
					long rec1 = Math.round(rec); 
					diffExp += (exp- exp1);
					diffRec += (rec - rec1);
					glogger.info(" difference is "+diffExp);
					glogger.info(" location name is "+tuple[1]);
					//arrInner.add(Sr);
					arrInner.add(tuple[1]);
					glogger.info("exp is "+exp);
					NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
					arrInner.add(testNumberFormat.format(exp1));
					glogger.info("rec is "+rec);
					arrInner.add(testNumberFormat.format(rec1));
					glogger.info("arrInneer is "+arrInner);
					arrOuterList.add(arrInner);
					
					i++;
				}
		
			}
			glogger.info(" Main outer list is "+ arrOuterList);
			ArrayList arrTemp = new ArrayList();
			arrTemp = sortExp(arrOuterList);
			arrReturn = new ArrayList();
			if(arrTemp != null)
			{
				for(int i=0,Sr=1;i<Integer.parseInt(topN) && i<arrTemp.size();i++,Sr++)
				{
					glogger.info("temp is "+ arrTemp.get(i));
					ArrayList arrIn = new ArrayList();
					arrIn.add(Sr);
					arrIn.add(((ArrayList)arrTemp.get(i)).get(0));
					arrIn.add(((ArrayList)arrTemp.get(i)).get(1));
					arrIn.add(((ArrayList)arrTemp.get(i)).get(2));
					arrReturn.add(arrIn);
					//arrReturn.add(arrTemp.get(i)); 
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrReturn;
		
		
	}

	public ArrayList getTopNTreasuryByRecDtlRpt(String topN,String toDate,String fromDate,long langId) {

		ArrayList arrOuterList = null;
		ArrayList arrReturn = null;
		try 
		{
			String topn = null;
			String fyear = getFinfromYear();
			glogger.info("-------- in the topNT By Expenditure");
			Session hibSession = getSession();
		
			String query = "select tsry.location_code,tsry.loc_name, expDtls.amnt, recDtls.ramnt "+
			"  from (select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
			               " exp.tsry_code "+
			          " from rpt_expenditure_dtls exp "+
			         " where exp.exp_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
			               " exp.exp_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
			               " exp.active = 'Y'  "+
			         " group by exp.tsry_code) expDtls, "+
			       " (select sum(rec.amount - nvl(rec.disbursement_amt, 0)) ramnt, "+
			               " rec.tsry_code "+
			          " from rpt_receipt_dtls rec "+
			         " where rec.revenue_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
			               " rec.revenue_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
			               " rec.active = 'Y' "+
			         " group by rec.tsry_code) recDtls, "+
			       " (select loc.loc_id,loc.location_code, loc.loc_name "+
			          " from cmn_location_mst loc "+
			         " where loc.lang_id = "+langId+" and "+
			               " loc.department_id in "+
			               " (select department_id "+
			                  " from org_department_mst "+
			                 " where department_code in ('"+lObjRsrcBndle.getString("to")+"', '"+lObjRsrcBndle.getString("pao")+"'))) tsry "+
			 " where expDtls.tsry_code = tsry.location_code and "+
			       " recDtls.tsry_code = tsry.location_code";
			
			glogger.info("the getTopNTreasuryByRecDtlRpt parth query is " + query );
			
			Query sqlQuery=hibSession.createSQLQuery(query);
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			if (resList!=null && resList.size()>0) 
			{
				arrOuterList=new ArrayList();
				Iterator it = resList.iterator();
				Double exp;
				Double rec;
				float diffExp = 0;
				float diffRec = 0;
				int i =1;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrTemp = new ArrayList();
					ArrayList arrInner = new ArrayList();
					exp = Double.parseDouble(tuple[2].toString());
					rec = Double.parseDouble(tuple[3].toString());
					arrTemp = getSubExp2(tuple[0].toString(),toDate,fromDate,langId);
					exp += Double.parseDouble(arrTemp.get(0).toString());
					rec += Double.parseDouble(arrTemp.get(1).toString());
					exp = exp /10000000;
					rec = rec/10000000;
					long exp1 = Math.round(exp);
					long rec1 = Math.round(rec); 
					diffExp += (exp- exp1);
					diffRec += (rec - rec1);
					glogger.info(" difference is "+diffExp);
					glogger.info(" location name is "+tuple[1]);
					arrInner.add(tuple[1]);
					glogger.info("exp is "+exp);
					NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
					glogger.info("rec is "+rec);
					arrInner.add(testNumberFormat.format(rec1));
					arrInner.add(testNumberFormat.format(exp1));
					glogger.info("arrInneer is "+arrInner);
					arrOuterList.add(arrInner);
					i++;
				}
			}
			ArrayList arrTemp = new ArrayList();
					arrTemp = sortRec(arrOuterList);
					arrReturn = new ArrayList();
			if(arrTemp != null)
			{
				for(int i=0,Sr=1;i<Integer.parseInt(topN) && i<arrTemp.size();i++,Sr++)
				{
					glogger.info("temp is "+ arrTemp.get(i));
					
					ArrayList arrIn = new ArrayList();
					arrIn.add(Sr);
					arrIn.add(((ArrayList)arrTemp.get(i)).get(0));
					arrIn.add(((ArrayList)arrTemp.get(i)).get(1));
					arrIn.add(((ArrayList)arrTemp.get(i)).get(2));
					arrReturn.add(arrIn);
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrReturn;
		
	}
	
	public ArrayList sortExp(ArrayList Arrlst)
	{
		glogger.info("in the get objection method" + Arrlst);
	
		ArrayList arrInner = new ArrayList();
		ArrayList arrInner1 = new ArrayList();
		glogger.info("Loc size is "+Arrlst);
		try
		{
		for(int i=0;i<Arrlst.size();i++)
		{
			for(int j=i+1;j<Arrlst.size();j++)
			{
				arrInner = (ArrayList)Arrlst.get(i);
				arrInner1 = (ArrayList)Arrlst.get(j);
				long temp1 = Long.parseLong(arrInner.get(1).toString());
				long temp2 = Long.parseLong(arrInner1.get(1).toString());
				glogger.info("values are "+ temp1+" "+ temp2);
				if(temp1 < temp2)
				{
					Arrlst.set(i, arrInner1);
					Arrlst.set(j, arrInner);
				}
			}
		}
		}
		catch(Exception e)
		{
			glogger.info("Exception is "+e);
		}
		glogger.info("sorted list is "+Arrlst);
		return Arrlst;
	}
	
	public ArrayList sortRec(ArrayList Arrlst)
	{
		glogger.info("in the get objection method" + Arrlst);
		ArrayList arrInner = new ArrayList();
		ArrayList arrInner1 = new ArrayList();
		glogger.info("Loc size is "+Arrlst);
		try
		{
			if(Arrlst != null)
			{
			for(int i=0;i<Arrlst.size();i++)
		{
			for(int j=i+1;j<Arrlst.size();j++)
			{
				arrInner = (ArrayList)Arrlst.get(i);
				arrInner1 = (ArrayList)Arrlst.get(j);
				long temp1 = Long.parseLong(arrInner.get(2).toString());
				long temp2 = Long.parseLong(arrInner1.get(2).toString());
				glogger.info("values are "+ temp1+" "+ temp2);
				if(temp1 < temp2)
				{
					Arrlst.set(i, arrInner1);
					Arrlst.set(j, arrInner);
				}
			}
		}
			}
		}
		catch(Exception e)
		{
			glogger.info("Exception is "+e);
		}
		glogger.info("sorted list is "+Arrlst);
		return Arrlst;
	}
	
	// cmplted Top N analysis//
	
	// others are started //
	
	public ArrayList getLocation(String lStrLangId, String lstrLocId) 
    {
		glogger.info("------------------------------------Inside location  Type query--------------------");
		glogger.info("lang id is "+lStrLangId);
		glogger.info("location id is "+lstrLocId);
		ArrayList arrOfficeName = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String locId=null;
        String lName=null;
//        Session hsession = getSession();
        try
        {
            lCon = DBConnection.getConnection(  );
        	
            String query = "select lom.loc_id,lom.loc_name"+ 
            			" from cmn_location_mst lom "+
            			" where lom.department_id in (select dep.department_id from org_department_mst dep where dep.department_code in ('"+lObjRsrcBndle.getString("to")+"', '"+lObjRsrcBndle.getString("pao")+"'))";
            lStmt = lCon.prepareStatement(query);
            lRs = lStmt.executeQuery();

            while(lRs.next())
            {
            	ComboValuesVO vo= new ComboValuesVO(  );
            	locId = lRs.getString("loc_id");
                lName = lRs.getString("Loc_name");
                glogger.info("Vlaue of loc Name" +lName);
                vo.setId(locId);
                vo.setDesc(lName);
                arrOfficeName.add(vo);
            }
 
        }
     
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        return arrOfficeName;
    }
	/* cmplt top N */
	
	public ArrayList getTreasuryRpt(String office,String toDate,String fromDate,long langId)
	{
		
		
		// private StyleVO[] reportStyleVO = null;
		
		 StyleVO[] reportStyleVO = new StyleVO[1];
   	 	reportStyleVO[0]=new StyleVO();
   	 	reportStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
   	 	reportStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 
   	 	
   	 	
		ArrayList arrOuterList = null;
		Connection con = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet rst = null;
		float diffExp = 0;
		float diffRec = 0;
		float diffPending = 0;
		float diffApproved = 0;
		float diffRejected = 0;
		float diffTotalLiabilities = 0;
		
		double total_exp = 0;
		double total_Rec = 0;
		double total_Pending = 0;
		double total_Approved = 0;
		double total_Rejected = 0;
		double total_liabilities = 0;
		double total_total_lib = 0;
		
		StyledData dataStyle = null;
		
	
		try 
		{
			String topn = null;
			glogger.info("-------- in the Treasury");
			Session hibSession = getSession();
			
			glogger.info("the resourcebundle's values "+ lObjRsrcBndle.getString("to"));
			
			/*String query = "select location_code, loc.loc_name, "+
				    "   ( select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt "+
				          " from rpt_expenditure_dtls exp "+
				         " where exp.exp_dt >= to_date('"+fromDate+"', 'YYYY-MM-DD') and "+
				               " exp.exp_dt <= to_date('"+toDate+"', 'YYYY-MM-DD') and "+
				               " exp.active = 'Y' and "+
				               " exp.tsry_code = loc.location_code "+
				         " group by exp.tsry_code ), "+
				         " (select sum(rec.amount - nvl(rec.disbursement_amt, 0)) ramnt "+
				          " from rpt_receipt_dtls rec "+
				         " where rec.revenue_dt >= to_date('"+fromDate+"', 'YYYY-MM-DD') and "+
				               " rec.revenue_dt <= to_date('"+toDate+"', 'YYYY-MM-DD') and "+
				               " rec.active = 'Y' and "+
				               " rec.tsry_code = loc.location_code "+
				         " group by rec.tsry_code) "+ 
				" from cmn_location_mst loc "+
				" where loc.department_id in "+
				               " (select department_id "+
				                  " from org_department_mst "+
				                 " where department_code in ('"+lObjRsrcBndle.getString("to")+"', '"+lObjRsrcBndle.getString("pao")+"'))";*/
			
			
			String query = "select location_code, "+
							"loc.loc_name, "+
							"nvl((select nvl(sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)),0) amnt "+
		          "from rpt_expenditure_dtls exp "+
		         "where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
		               "exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
		               "exp.active = 'Y' and exp.tsry_code = loc.location_code and "+
		               "exp.exp_status_code in "+ 
		               "(select status_code "+
		                  "from mst_status_liability msl "+
		                 "where msl.liability_id = "+lObjRsrcBndle.getString("Cheque.Issued")+") "+
		 "group by exp.tsry_code),0) , "+ 
		 
		 "nvl((select nvl(sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)),0) amnt "+
		          "from rpt_expenditure_dtls exp "+
		         "where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
		               "exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
		               "exp.active = 'Y' and exp.tsry_code = loc.location_code and "+
		               "exp.exp_status_code in "+
		               "(select status_code "+
		                  "from mst_status_liability msl "+
		                 "where msl.liability_id = "+lObjRsrcBndle.getString("Audit.Approval")+") "+
		 "group by exp.tsry_code),0) , "+ 
		 
		 "nvl((select nvl(sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)),0) amnt "+
		          "from rpt_expenditure_dtls exp "+
		         "where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
		               "exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
		               "exp.active = 'Y' and exp.tsry_code = loc.location_code and "+
		               "exp.exp_status_code in "+
		               "(select status_code "+
		                  "from mst_status_liability msl "+
		                 "where msl.liability_id = "+lObjRsrcBndle.getString("Bill.Progress")+") "+
		 "group by exp.tsry_code),0) , " +
		 
		 
		 "nvl((select nvl(sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)),0) amnt "+
         "from rpt_expenditure_dtls exp "+
        "where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
              "exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
              "exp.active = 'Y' and exp.tsry_code = loc.location_code and "+
              "exp.exp_status_code in "+
              /*"(select status_code "+
                 "from mst_status_liability msl "+*/
                /*"where msl.liability_id = "*/"('"+lObjRsrcBndle.getString("Bill.Rejected")+"' ,' "+lObjRsrcBndle.getString("Bill.Rejected")+"') "+
                "group by exp.tsry_code),0) , " +
 
		 
		 "nvl((select nvl(sum(rec.amount - nvl(rec.disbursement_amt, 0)),0) ramnt "+
		                             "from rpt_receipt_dtls rec "+
		                            "where rec.revenue_dt >= "+
		                                  "to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
		                                  "rec.revenue_dt <= "+
		                                  "to_date('"+toDate+"', 'yyyy-MM-dd') and "+
		                                  "rec.active = 'Y' and "+
		                                  "rec.tsry_code = loc.location_code "+
		                            "group by rec.tsry_code),0) "+
		                            

		  "from cmn_location_mst loc "+
		 "where loc.department_id in "+
		       "(select department_id"+
		         " from org_department_mst "+
		         "where department_code in ('"+lObjRsrcBndle.getString("Treasury.Office")+"', '"+lObjRsrcBndle.getString("Pay.Accounts.Office")+"'))";
			
			if(!office.equals("-1"))
			{
				glogger.info("-------- in the office--------");
				query += " and loc.location_code = '"+office+"'";
			}
			
			glogger.info("The parth query is :-- > " + query );
			
			
			
			stmt = con.createStatement();
			rst = stmt.executeQuery(query);
			
			
			
			Query sqlQuery=hibSession.createSQLQuery(query);
			
			List resList=sqlQuery.list();
			
			glogger.info("size of List-->"+resList.size());
			
			arrOuterList=new ArrayList();
			int i =1;
			while(rst.next())
			{
				
				double exp =0 ;
				double rec =0;
				double pending = 0;
				double approved = 0;
				double rejected = 0;
				
				
				
				
				
				ArrayList arrTemp = new ArrayList();
				ArrayList arrInner = new ArrayList();
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				
				
				glogger.info("--------------------------The row values display-------------------------");
				glogger.info("rs - 0 " + rst.getObject(1));
				glogger.info("rtsa - 1 " + rst.getObject(2));
				glogger.info("rs - 2 " + rst.getObject(3));
				glogger.info("rs - 3 " + rst.getObject(4));
				glogger.info("Touple - 4 " + rst.getObject(5));
				glogger.info("Touple - 5 " + rst.getObject(6));
				glogger.info("---------------------------------------------------------------------------");
				
				
				exp = Double.parseDouble(rst.getObject(3).toString());
				rec = Double.parseDouble(rst.getObject(7).toString());
				pending = Double.parseDouble(rst.getObject(5).toString());
				approved = Double.parseDouble(rst.getObject(4).toString());
				rejected = Double.parseDouble(rst.getObject(6).toString());
				
				
				
				arrTemp = getSubExp1(rst.getObject(1).toString(),toDate,fromDate,langId);
				
				 
				exp += Double.parseDouble(arrTemp.get(0).toString());
				pending += Double.parseDouble(arrTemp.get(1).toString());
				approved += Double.parseDouble(arrTemp.get(2).toString());
				rejected += Double.parseDouble(arrTemp.get(3).toString());
				rec += Double.parseDouble(arrTemp.get(4).toString());
				
				exp = exp /10000000;
				rec = rec/10000000;
				pending = pending/10000000;
				approved = approved/10000000;
				rejected = rejected/10000000;
				total_liabilities = pending+approved;
				
				long exp1 = Math.round(exp);
				long rec1 = Math.round(rec); 
				long pending1 = Math.round(pending);
				long approved1 = Math.round(approved);
				long rejected1 = Math.round(rejected);
				long total_liabilities_long = Math.round(total_liabilities);
				
				diffExp += (exp- exp1);
				diffRec += (rec - rec1);
				diffPending += (pending - pending1);
				diffApproved += (approved - approved1);
				diffRejected += (rejected - rejected1);
				diffTotalLiabilities += (total_liabilities - total_liabilities_long);
				
				total_exp = total_exp + exp;
				total_Rec = total_Rec + rec;
				total_Pending = total_Pending + pending;
				total_Approved = total_Approved + approved;
				total_Rejected = total_Rejected + rejected;
				total_total_lib = total_total_lib +  total_liabilities;
				
				arrInner.add(i);
				
				arrInner.add(rst.getObject(2));
				arrInner.add(testNumberFormat.format(exp1));
				arrInner.add(testNumberFormat.format(rec1));
						
				arrInner.add(rst.getObject(1));
				arrInner.add(fromDate);
				arrInner.add(toDate);
				
				arrInner.add(testNumberFormat.format(pending1));
				arrInner.add(testNumberFormat.format(approved1));
				arrInner.add(testNumberFormat.format(total_liabilities_long));
				glogger.info("The tupple value 3 is " + (rst.getObject(4)).toString());
				arrInner.add(testNumberFormat.format(rejected1));
				
				arrOuterList.add(arrInner);
				i++;
			}
				
				
			
				
			
			
		
				

				ArrayList arrLast = new ArrayList();
				arrLast.add("<b>Total</b>");
				arrLast.add("");
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setGroupingUsed(false);
				testNumberFormat.setMaximumFractionDigits(0);
				testNumberFormat.setMinimumFractionDigits(0);
				
				 dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(testNumberFormat.format(Math.round(total_exp)));
				arrLast.add(dataStyle);
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(testNumberFormat.format(Math.round(total_Rec)));
				arrLast.add(dataStyle);
				
				arrLast.add("");
				arrLast.add("");
				arrLast.add("");
				
				
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(testNumberFormat.format(Math.round(total_Pending)));
				arrLast.add(dataStyle);
				
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(testNumberFormat.format(Math.round(total_Approved)));
				arrLast.add(dataStyle);
				
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(testNumberFormat.format(Math.round(total_total_lib)));
				arrLast.add(dataStyle);
				
				
				dataStyle = new StyledData();
				dataStyle.setStyles(reportStyleVO);
				dataStyle.setData(testNumberFormat.format(Math.round(total_Rejected)));
				arrLast.add(dataStyle);
				
				
				
				
				arrOuterList.add(arrLast);
			
			
			
			
				 arrLast = new ArrayList();
				arrLast.add("Rounded Difference");
				arrLast.add("");
				 testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setGroupingUsed(false);
				testNumberFormat.setMaximumFractionDigits(3);
				testNumberFormat.setMinimumFractionDigits(3);
				
				arrLast.add(testNumberFormat.format(diffExp));
				arrLast.add(testNumberFormat.format(diffRec));
				
				
				arrLast.add("");
				arrLast.add("");
				arrLast.add("");
				
				arrLast.add(testNumberFormat.format(diffPending));
				arrLast.add(testNumberFormat.format(diffApproved));
				arrLast.add(testNumberFormat.format(diffTotalLiabilities));
				arrLast.add(testNumberFormat.format(diffRejected));
				
				
				arrOuterList.add(arrLast);
				
				
				
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}
	
	public ArrayList getSubExp1(String locationCode,String toDate,String fromDate,long langId)
	{
		glogger.info("in the get getSubExp method");
		Session hibSession = getSession();
		ArrayList arrInner = new ArrayList();

		String fyear = getFinfromYear();
		String query = " select tsry.location_code, expDtls.amnt, ApprvexpDtls.amnt ,PendingexpDtls.amnt, RejectedexpDtls.amnt ,  recDtls.ramnt  " +
				" from (select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
					               " exp.tsry_code "+
					          " from rpt_expenditure_dtls exp "+
					         " where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
					               " exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
					               " exp.active = 'Y' and "+
					               "exp.exp_status_code in "+ 
					               "(select status_code "+
					                  "from mst_status_liability msl "+
					                 "where msl.liability_id = 100035) "+
					         " group by exp.tsry_code) expDtls, "+
					         
					         "(select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
						               " exp.tsry_code "+
						          " from rpt_expenditure_dtls exp "+
						         " where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
						               " exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
						               " exp.active = 'Y' and "+
						               "exp.exp_status_code in "+ 
						               "(select status_code "+
						                  "from mst_status_liability msl "+
						                 "where msl.liability_id = 100034) "+
						         " group by exp.tsry_code) ApprvexpDtls, "+
					         
					        "(select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
					               " exp.tsry_code "+
					          " from rpt_expenditure_dtls exp "+
					         " where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
					               " exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
					               " exp.active = 'Y' and "+
					               "exp.exp_status_code in "+ 
					               "(select status_code "+
					                  "from mst_status_liability msl "+
					                 "where msl.liability_id = 100033) "+
					         " group by exp.tsry_code) PendingexpDtls, "+
					         
					         
					        "(select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
						               " exp.tsry_code "+
						          " from rpt_expenditure_dtls exp "+
						         " where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
						               " exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
						               " exp.active = 'Y' and "+
						               "exp.exp_status_code in "+ 
						               "('"+lObjRsrcBndle.getString("Bill.Rejected")+"' ,'"+lObjRsrcBndle.getString("Bill.Rejected")+"') "+
						         " group by exp.tsry_code) RejectedexpDtls, "+
					         
					         
					         
					         
					         
					       " (select sum(rec.amount - nvl(rec.disbursement_amt, 0)) ramnt, "+
					               " rec.tsry_code "+
					          " from rpt_receipt_dtls rec "+
					         " where rec.revenue_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
					               " rec.revenue_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
					               " rec.active = 'Y' "+
					         " group by rec.tsry_code) recDtls, "+
					         "  (select loc_id, location_code "+
						          " From cmn_location_mst "+
						         " where lang_id = "+langId+" and "+
						               " parent_loc_id = "+locationCode+ " ) tsry "+
						 " where expDtls.tsry_code = tsry.location_code and "+
						       " recDtls.tsry_code = tsry.location_code ";
					
		
		glogger.info("the getSubExp1  query is :- > " + query);
		
		Query sqlQuery=hibSession.createSQLQuery(query);
		List resList=sqlQuery.list();
		double exp = 0.0;
		double rec = 0.0;
		double pending = 0.0;
		double approved = 0.0;
		double rejected = 0.0;
	
		if(resList!= null && resList.size()>0 )
		{
			glogger.info("In the result set");	
			Iterator it = resList.iterator();
			try
			{
				while(it.hasNext())
				{
					
					Object[] tuple = (Object[])it.next();
					
					if(tuple[1] != null)
						exp += Double.parseDouble(tuple[1].toString());
					if(tuple[2] != null)
						pending += Double.parseDouble(tuple[2].toString());
					if(tuple[3] != null)
						approved += Double.parseDouble(tuple[3].toString());
					if(tuple[4] != null)
						rejected += Double.parseDouble(tuple[4].toString());
					if(tuple[5] != null)
						rec += Double.parseDouble(tuple[5].toString());
				}
				
				
				glogger.info(" size of return arraylist is "+arrInner.size() + arrInner.get(0) + arrInner.get(1));
			
			}catch(Exception e){e.printStackTrace();}
		}
		
		else
		{
			arrInner.add(exp);
			arrInner.add(pending);
			arrInner.add(approved);
			arrInner.add(rejected);
			arrInner.add(rec);
		}
		return arrInner;
	}
	
	
	
	public ArrayList getSubExp2(String locationCode,String toDate,String fromDate,long langId)
	{
		glogger.info("in the get getSubExp method");
		Session hibSession = getSession();
		ArrayList arrInner = new ArrayList();

		String fyear = getFinfromYear();
		String query = " select tsry.location_code, expDtls.amnt, ApprvexpDtls.amnt ,PendingexpDtls.amnt, RejectedexpDtls.amnt ,  recDtls.ramnt  " +
				" from (select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
					               " exp.tsry_code "+
					          " from rpt_expenditure_dtls exp "+
					         " where exp.exp_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
					               " exp.exp_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
					               " exp.active = 'Y' and "+
					               "exp.exp_status_code in "+ 
					               "(select status_code "+
					                  "from mst_status_liability msl "+
					                 "where msl.liability_id = 100035) "+
					         " group by exp.tsry_code) expDtls, "+
					         
					         "(select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
						               " exp.tsry_code "+
						          " from rpt_expenditure_dtls exp "+
						         " where exp.exp_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
						               " exp.exp_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
						               " exp.active = 'Y' and "+
						               "exp.exp_status_code in "+ 
						               "(select status_code "+
						                  "from mst_status_liability msl "+
						                 "where msl.liability_id = 100034) "+
						         " group by exp.tsry_code) ApprvexpDtls, "+
					         
					        "(select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
					               " exp.tsry_code "+
					          " from rpt_expenditure_dtls exp "+
					         " where exp.exp_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
					               " exp.exp_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
					               " exp.active = 'Y' and "+
					               "exp.exp_status_code in "+ 
					               "(select status_code "+
					                  "from mst_status_liability msl "+
					                 "where msl.liability_id = 100033) "+
					         " group by exp.tsry_code) PendingexpDtls, "+
					         
					         
					        "(select sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)) amnt, "+
						               " exp.tsry_code "+
						          " from rpt_expenditure_dtls exp "+
						         " where exp.exp_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
						               " exp.exp_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
						               " exp.active = 'Y' and "+
						               "exp.exp_status_code in "+ 
						               "('"+lObjRsrcBndle.getString("Bill.Rejected")+"' ,'"+lObjRsrcBndle.getString("Bill.Rejected")+"') "+
						         " group by exp.tsry_code) RejectedexpDtls, "+
					         
					         
					         
					         
					         
					       " (select sum(rec.amount - nvl(rec.disbursement_amt, 0)) ramnt, "+
					               " rec.tsry_code "+
					          " from rpt_receipt_dtls rec "+
					         " where rec.revenue_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
					               " rec.revenue_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') and "+
					               " rec.active = 'Y' "+
					         " group by rec.tsry_code) recDtls, "+
					         "  (select loc_id, location_code "+
						          " From cmn_location_mst "+
						         " where lang_id = "+langId+" and "+
						               " parent_loc_id = "+locationCode+ " ) tsry "+
						 " where expDtls.tsry_code = tsry.location_code and "+
						       " recDtls.tsry_code = tsry.location_code ";
					
		
		glogger.info("the getSubExp1  query is :- > " + query);
		
		Query sqlQuery=hibSession.createSQLQuery(query);
		List resList=sqlQuery.list();
		double exp = 0.0;
		double rec = 0.0;
		double pending = 0.0;
		double approved = 0.0;
		double rejected = 0.0;
	
		if(resList!= null && resList.size()>0 )
		{
			glogger.info("In the result set");	
			Iterator it = resList.iterator();
			try
			{
				while(it.hasNext())
				{
					
					Object[] tuple = (Object[])it.next();
					
					if(tuple[1] != null)
						exp += Double.parseDouble(tuple[1].toString());
					if(tuple[2] != null)
						pending += Double.parseDouble(tuple[2].toString());
					if(tuple[3] != null)
						approved += Double.parseDouble(tuple[3].toString());
					if(tuple[4] != null)
						rejected += Double.parseDouble(tuple[4].toString());
					if(tuple[5] != null)
						rec += Double.parseDouble(tuple[5].toString());
				}
				
				
				glogger.info(" size of return arraylist is "+arrInner.size() + arrInner.get(0) + arrInner.get(1));
			
			}catch(Exception e){e.printStackTrace();}
		}
		
		else
		{
			arrInner.add(exp);
			arrInner.add(pending);
			arrInner.add(approved);
			arrInner.add(rejected);
			arrInner.add(rec);
		}
		return arrInner;
	}
	
	
	public ArrayList getsubTreasuryRpt(String locationCode,String toDate,String fromDate,long langId)
	{
		ArrayList arrOuterList =new ArrayList();
		
		
		StyleVO[] reportStyleVO = new StyleVO[1];
   	 	reportStyleVO[0]=new StyleVO();
   	 	reportStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
   	 	reportStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 
		
		Connection con = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet rst = null;
		float diffExp = 0;
		float diffRec = 0;
		float diffPending = 0;
		float diffApproved = 0;
		float diffRejected = 0;
		float diffTotalLiabilities = 0;
		
		double total_exp = 0;
		double total_Rec = 0;
		double total_Pending = 0;
		double total_Approved = 0;
		double total_Rejected = 0;
		double total_liabilities = 0;
		double total_total_lib = 0;
		
	
		try 
		{
			String topn = null;
			glogger.info("-------- in the Treasury");
			Session hibSession = getSession();
	
			String query = "select loc.location_code, "+
				       " loc.loc_name, "+
				       "nvl((select nvl(sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)),0) amnt "+
				          "from rpt_expenditure_dtls exp "+
				         "where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
				               "exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
				               "exp.active = 'Y' and exp.tsry_code = loc.location_code and "+
				               "exp.exp_status_code in "+ 
				               "(select status_code "+
				                  "from mst_status_liability msl "+
				                 "where msl.liability_id = 100035) "+
				 "group by exp.tsry_code),0) , "+ 
				 
				 "nvl((select nvl(sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)),0) amnt "+
				          "from rpt_expenditure_dtls exp "+
				         "where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
				               "exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
				               "exp.active = 'Y' and exp.tsry_code = loc.location_code and "+
				               "exp.exp_status_code in "+
				               "(select status_code "+
				                  "from mst_status_liability msl "+
				                 "where msl.liability_id = 100034) "+
				 "group by exp.tsry_code),0) , "+ 
				 
				 "nvl((select nvl(sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)),0) amnt "+
				          "from rpt_expenditure_dtls exp "+
				         "where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
				               "exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
				               "exp.active = 'Y' and exp.tsry_code = loc.location_code and "+
				               "exp.exp_status_code in "+
				               "(select status_code "+
				                  "from mst_status_liability msl "+
				                 "where msl.liability_id = 100033) "+
				 "group by exp.tsry_code),0) , " +
				 
				 "nvl((select nvl(sum(exp.gross_amnt - nvl(exp.recovery_amt, 0)),0) amnt "+
		          "from rpt_expenditure_dtls exp "+
		         "where exp.exp_dt >= to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
		               "exp.exp_dt <= to_date('"+toDate+"', 'yyyy-MM-dd') and "+
		               "exp.active = 'Y' and exp.tsry_code = loc.location_code and "+
		               "exp.exp_status_code in "+
		               "('"+lObjRsrcBndle.getString("Bill.Rejected")+"'"+" , '"+lObjRsrcBndle.getString("Bill.Rejected")+"') "+
		 "group by exp.tsry_code),0) , " +
				 
				 
				 
				 
				 "nvl((select nvl(sum(rec.amount - nvl(rec.disbursement_amt, 0)),0) ramnt "+
				                             "from rpt_receipt_dtls rec "+
				                            "where rec.revenue_dt >= "+
				                                  "to_date('"+fromDate+"', 'yyyy-MM-dd') and "+
				                                  "rec.revenue_dt <= "+
				                                  "to_date('"+toDate+"', 'yyyy-MM-dd') and "+
				                                  "rec.active = 'Y' and "+
				                                  "rec.tsry_code = loc.location_code "+
				                            "group by rec.tsry_code),0) "+
				  " from cmn_location_mst loc "+
				 " where loc.lang_id ="+langId+" and parent_loc_id = "+ locationCode;
			
			glogger.info("the sub treasury query is --> " + query );
							
			stmt = con.createStatement();
			rst = stmt.executeQuery(query);
			int i =1;
			while(rst.next())
			{
				
				double exp =0 ;
				double rec =0;
				double pending = 0;
				double approved = 0;
				double rejected = 0;
				
				
				
				
				
				ArrayList arrTemp = new ArrayList();
				ArrayList arrInner = new ArrayList();
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				
				
				glogger.info("--------------------------The row values display-------------------------");
				glogger.info("rs - 0 " + rst.getObject(1));
				glogger.info("rtsa - 1 " + rst.getObject(2));
				glogger.info("rs - 2 " + rst.getObject(3));
				glogger.info("rs - 3 " + rst.getObject(4));
				glogger.info("Touple - 4 " + rst.getObject(5));
				glogger.info("Touple - 5 " + rst.getObject(6));
				glogger.info("---------------------------------------------------------------------------");
				
				
				exp = Double.parseDouble(rst.getObject(3).toString());
				rec = Double.parseDouble(rst.getObject(7).toString());
				pending = Double.parseDouble(rst.getObject(5).toString());
				approved = Double.parseDouble(rst.getObject(4).toString());
				rejected = Double.parseDouble(rst.getObject(6).toString());
				
				
				
				
				
				
				
				
				exp = exp /10000000;
				rec = rec/10000000;
				pending = pending/10000000;
				approved = approved/10000000;
				rejected = rejected / 10000000;
				total_liabilities = pending+approved;
				
				long exp1 = Math.round(exp);
				long rec1 = Math.round(rec); 
				long pending1 = Math.round(pending);
				long approved1 = Math.round(approved);
				long rejected1 = Math.round(rejected);
				long total_liabilities_long = Math.round(total_liabilities);
				
				diffExp += (exp- exp1);
				diffRec += (rec - rec1);
				diffPending += (pending - pending1);
				diffApproved += (approved - approved1);
				diffRejected += (rejected - rejected1);
				diffTotalLiabilities += (total_liabilities - total_liabilities_long);
				
				
				
				total_exp = total_exp + exp;
				total_Rec = total_Rec + rec;
				total_Pending = total_Pending + pending;
				total_Approved = total_Approved + approved;
				total_Rejected = total_Rejected + rejected;
				
				total_total_lib = total_total_lib +  total_liabilities;
				
				
				
				arrInner.add(i);
				i = i+1;
				arrInner.add(rst.getObject(2));
				arrInner.add(testNumberFormat.format(exp1));
				arrInner.add(testNumberFormat.format(rec1));
						
				
				
				arrInner.add(testNumberFormat.format(pending));
				arrInner.add(testNumberFormat.format(approved));
				arrInner.add(testNumberFormat.format(total_liabilities));
				glogger.info("The tupple value 3 is " + (rst.getObject(4)).toString());
				arrInner.add(testNumberFormat.format(approved));
				
				arrOuterList.add(arrInner);
				
			}
				
				
				ArrayList arrLast = new ArrayList();
				arrLast.add("Total");
				arrLast.add("");
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setGroupingUsed(false);
				testNumberFormat.setMaximumFractionDigits(0);
				testNumberFormat.setMinimumFractionDigits(0);
				
				StyledData dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData(testNumberFormat.format(Math.round(total_exp)));
					arrLast.add(dataStyle);
					
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData(testNumberFormat.format(Math.round(total_Rec)));
					arrLast.add(dataStyle);
					
					
					
					
					
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData(testNumberFormat.format(Math.round(total_Pending)));
					arrLast.add(dataStyle);
					
					
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData(testNumberFormat.format(Math.round(total_Approved)));
					arrLast.add(dataStyle);
					
					
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData(testNumberFormat.format(Math.round(total_total_lib)));
					arrLast.add(dataStyle);
					
					
					dataStyle = new StyledData();
					dataStyle.setStyles(reportStyleVO);
					dataStyle.setData(testNumberFormat.format(Math.round(total_Rejected)));
					arrLast.add(dataStyle);
					
					
				
				
				
				
				arrOuterList.add(arrLast);
					
			
			
		
			
				 arrLast = new ArrayList();
				arrLast.add("Rounded Difference");
				arrLast.add("");
				 testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setGroupingUsed(false);
				testNumberFormat.setMaximumFractionDigits(3);
				testNumberFormat.setMinimumFractionDigits(3);
				
				arrLast.add(testNumberFormat.format(diffExp));
				arrLast.add(testNumberFormat.format(diffRec));
				
				
				
				
				arrLast.add(testNumberFormat.format(diffPending));
				arrLast.add(testNumberFormat.format(diffApproved));
				arrLast.add(testNumberFormat.format(diffTotalLiabilities));
				arrLast.add(testNumberFormat.format(diffRejected));
				
				arrOuterList.add(arrLast);
			
			
			
			
			
			
			
			
			/*Query sqlQuery=hibSession.createSQLQuery(query);
			List resList=sqlQuery.list();
			
			if (resList!=null && resList.size()>0) 
			{
				arrOuterList=new ArrayList();
				Iterator it = resList.iterator();
				double exp = 0;
				double rec = 0;
				float diffExp = 0;
				float diffRec = 0;
				int i =1;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrTemp = new ArrayList();
					ArrayList arrInner = new ArrayList();
					if(tuple[2] != null)
					{
						exp = Double.parseDouble(tuple[2].toString());
					}
					if(tuple[3] != null)
					{
						rec = Double.parseDouble(tuple[3].toString());
					}
					exp = exp /10000000;
					rec = rec/10000000;
					
					long exp1 = Math.round(exp);
					long rec1 = Math.round(rec); 
					diffExp += (exp- exp1);
					diffRec += (rec - rec1);
					 
					glogger.info(" sub loc is :"+tuple[0]+"expenditure is :"+exp1+"receipt is "+rec1+"difference is "+diffExp);
					
					arrInner.add(i);
					arrInner.add(tuple[1]);
					NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
					arrInner.add(testNumberFormat.format(exp1));
					arrInner.add(testNumberFormat.format(rec1));
					arrOuterList.add(arrInner);
					i++;
				}
				ArrayList arrLast = new ArrayList();
				arrLast.add("Rounded Difference");
				arrLast.add("");
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setGroupingUsed(false);
				testNumberFormat.setMaximumFractionDigits(3);
				testNumberFormat.setMinimumFractionDigits(3);
				arrLast.add(testNumberFormat.format(diffExp));
				arrLast.add(testNumberFormat.format(diffRec));
				arrOuterList.add(arrLast);
			}
			*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}
	
	public ArrayList getDDO(String lStrLangId, String lstrLocId, String locationId) 
    {
		glogger.info("------------------------------------Inside location  Type query--------------------");
		glogger.info("location id is "+lstrLocId);
		ArrayList arrOfficeName = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String locId=null;
        String lName=null;
        try
        {
            lCon = DBConnection.getConnection(  );
            String query = "select ddo.ddo_id , ddo.ddo_name From org_ddo_mst ddo ";
            glogger.info("Location Id is "+locationId);
            if(!locationId.equals("0"))
            {	
            	query += " where ddo.ddo_code in "+
            			" (select ddo_code from rlt_ddo_org d where d.office_code = "+locationId+")";
            	
            }
            glogger.info("the query is" + query);
            lStmt = lCon.prepareStatement(query);
            lRs = lStmt.executeQuery();
            while(lRs.next())
            {
            	ComboValuesVO vo= new ComboValuesVO(  );
            	locId = lRs.getString("ddo_id");
                lName = lRs.getString("ddo_name");
                glogger.info("Vlaue of loc Name" +lName);
                vo.setId(locId);
                vo.setDesc(lName);
                arrOfficeName.add(vo);
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
        return arrOfficeName;
    }
	
	public ArrayList getDDORpt(String office,String toDate,String fromDate,String ddoId, String finYear,long langId) {
		ArrayList arrOuterList = null;
		try 
		{
			
			String topn = null;
			glogger.info("-------- in the Treasury");
			Session hibSession = getSession();
			
			String query = " select ddo.ddo_name , ddo.ddo_code ,"+   
						   " (SELECT SUM(S.GRANT_AMOUNT_STATE) "+ 
								" FROM  SGVA_GRANT_ORDER_DETAIL S , sgva_grant_scheme_co_ddo_mpg bdj "+  
								" where S.FIN_YEAR_ID = "+ finYear +
								" AND S.PLAN_NONPLAN = '"+lObjRsrcBndle.getString("PL")+"' "+  
								" AND S.TO_DDO_CODE = ddo.ddo_code "+
								" AND S.DEMAND_CODE = bdj.demand_code   "+ 
								" AND  S.BUDMJRHD_CODE = bdj.budmjrhd_code "+ 
								" AND S.BUDSUBMJRHD_CODE = bdj.budsubmjrhd_code "+ 
								" AND  S.BUDMINHD_CODE = bdj.budminhd_code  "+ 
								" AND S.BUDSUBHD_CODE = bdj.budsubhd_code "+  
								"  AND S.To_Co_Code = bdj.co_ddo_code "+
								" AND s.GRANT_RELEASE_STATUS = '"+lObjRsrcBndle.getString("Release")+"' "+
								" group by S.TO_DDO_CODE ) grantDtls, "+
							" (SELECT SUM(S.GRANT_AMOUNT_STATE) "+ 
									" FROM  SGVA_GRANT_ORDER_DETAIL S , sgva_grant_scheme_co_ddo_mpg bdj "+  
									" where S.FIN_YEAR_ID = "+ finYear +
									" AND S.PLAN_NONPLAN = '"+lObjRsrcBndle.getString("NP")+"' "+  
									" AND S.TO_DDO_CODE = ddo.ddo_code "+
									" AND S.DEMAND_CODE = bdj.demand_code   "+ 
									" AND  S.BUDMJRHD_CODE = bdj.budmjrhd_code "+ 
									" AND S.BUDSUBMJRHD_CODE = bdj.budsubmjrhd_code "+ 
									" AND  S.BUDMINHD_CODE = bdj.budminhd_code  "+ 
									" AND S.BUDSUBHD_CODE = bdj.budsubhd_code "+  
									"  AND S.To_Co_Code = bdj.co_ddo_code "+
									" AND s.GRANT_RELEASE_STATUS = '"+lObjRsrcBndle.getString("Release")+"' "+
									" group by S.TO_DDO_CODE ) grantDtlsNP, "+
						" (select sum(exp.gross_amnt - nvl(exp.recovery_amt,0)) "+
							      " from rpt_expenditure_dtls exp "+
							      " where exp.exp_dt >= to_date('"+fromDate+"', 'dd/mm/yyyy') and "+
							      " exp.exp_dt <= to_date('"+toDate+"', 'dd/mm/yyyy') "+ 
							      " and exp.ddo_code = ddo.ddo_code "+
							      " group by  exp.ddo_code ) amnt "+          
						" from org_ddo_mst ddo where lang_Id = "+langId;
			
			int j =0;
			if(!ddoId.equals("0"))
			{
				glogger.info("-------- in the DDO--------");
				//query += " where ddo.ddo_id = "+ ddoId;
				query += " and ddo.ddo_id = "+ ddoId;
				
				j = 1;
			}
			if(!office.equals("0"))
			{
				glogger.info("-------- in the office--------");
				/*if(j == 1)
				{
					query += " and ";
				}
				else
				{
					query += " where";
				}*/
			//	query += " ddo.post_id in (select do.ddo_post_id from rlt_ddo_org do where do.office_id ="+ office+")";
				//query += "   ddo.ddo_code in "+
				query += "   and ddo.ddo_code in "+
							" (select do.ddo_code from rlt_ddo_org do where do.office_code = "+ office+")";
			}
			Query sqlQuery=hibSession.createSQLQuery(query);
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			
			arrOuterList=new ArrayList();
			float diffExp = 0;
			float diffGrant = 0;
			if (resList!=null && resList.size()>0) 
			{
				
				Iterator it = resList.iterator();
				int i =1;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrTemp = new ArrayList();
					ArrayList arrInner = new ArrayList();
					arrInner.add(i);
					arrInner.add(tuple[0]);
					double grant =0;
					double exp =0;
					
					if(tuple[2] != null || tuple[3] != null )
					{
						
						if(tuple[2] != null)
						grant = Double.parseDouble(tuple[2].toString());
						if(tuple[3] != null)
						grant += Double.parseDouble(tuple[3].toString());
						grant = grant /10000000;
						long grant1 = Math.round(grant);
						diffGrant += (grant- grant1);
						NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
						arrInner.add(testNumberFormat.format(grant1));
					}
					else
					{
						arrInner.add("-");
					}
					if(tuple[4] != null)
					{
						//arrInner.add(tuple[3]);
						exp = Double.parseDouble(tuple[4].toString());
						exp = exp /10000000;
						long exp1 = Math.round(exp);
						diffExp += (exp- exp1);
						NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
						arrInner.add(testNumberFormat.format(exp1));
					}
					else
					{
						arrInner.add("-");
					}
					arrInner.add(fromDate);
					arrInner.add(toDate);
					arrInner.add(tuple[1]);
					arrOuterList.add(arrInner);
					i++;
				}
			}
			ArrayList arrLast = new ArrayList();
			arrLast.add("Rounded Difference");
			arrLast.add("");
			NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
			testNumberFormat.setGroupingUsed(false);
			testNumberFormat.setMaximumFractionDigits(3);
			testNumberFormat.setMinimumFractionDigits(3);
			arrLast.add(testNumberFormat.format(diffGrant));
			arrLast.add(testNumberFormat.format(diffExp));
			arrLast.add(""); 
			arrLast.add("");
			arrLast.add("");
			arrOuterList.add(arrLast);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}
	
	public String getStrLangId(Long lILangId)
	{
		ArrayList arrOfficeName = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String locId=null;
        String lName=null;
        String lStrLangId = null;
        try
        {
            lCon = DBConnection.getConnection(  );
            String query = "select lang_short_name from cmn_language_mst where lang_id="+lILangId;
            lStmt = lCon.prepareStatement(query);
            lRs = lStmt.executeQuery();
            while(lRs.next())
            {
            	 lStrLangId = lRs.getString("lang_short_name");
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
        return lStrLangId;
		
	} 
	
	public ArrayList getDDOBillRpt(String office,String toDate,String fromDate,String ddocode,long langId) {
		ArrayList arrOuterList = null;
		try 
		{
			String topn = null;
			glogger.info("-------- in the Treasury");
			Session hibSession = getSession();
			glogger.info("DDo code is --------"+ddocode);

			
			String query = " select regDtls.bill_cntrl_no ,btypeDtls.subject_desc , regDtls.bill_gross_amount , regDtls.bill_date,mvntDtls.status_updt_date "+
						" from "+
							" ( select reg.bill_cntrl_no , reg.bill_gross_amount , reg.bill_date , reg.bill_no , reg.subject_id "+ 
								"from trn_bill_register reg "+
								" where reg.ddo_code = '"+ddocode+"') regDtls , "+ 
							" ( select btype.subject_desc , btype.subject_id "+
								" from mst_bill_type where lang_id= "+langId+" btype) btypeDtls, "+
							" (select mvnt.status_updt_date, mvnt.bill_no "+
								" from trn_bill_mvmnt mvnt "+
								" where mvnt.mvmnt_status = '"+lObjRsrcBndle.getString("bill_approved")+"'"+
								" and mvnt.status_updt_date >= to_date('"+fromDate+"', 'YYYY-MM-DD')" +
								" and mvnt.status_updt_date <= to_date('"+toDate+"', 'YYYY-MM-DD') ) mvntDtls "+
						" where regDtls.subject_id = btypeDtls.subject_id "+
							" and regDtls.bill_no = mvntDtls.bill_no ";
			
			Query sqlQuery=hibSession.createSQLQuery(query);
			
			List resList=sqlQuery.list();
			glogger.info("size of List-->"+resList.size());
			arrOuterList=new ArrayList();
			float diffAmount = 0;
			if (resList!=null && resList.size()>0) 
			{
				
				Iterator it = resList.iterator();
				int i =1;
				
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrTemp = new ArrayList();
					ArrayList arrInner = new ArrayList();
					arrInner.add(i);
					arrInner.add(tuple[0]);
					arrInner.add(tuple[1]);
					//arrInner.add(tuple[2]);
					double amount = 0;
					if(tuple[2] != null)
					{
						//arrInner.add(tuple[2]);
						amount = Double.parseDouble(tuple[2].toString());
						amount = amount /10000000;
						long amount1 = Math.round(amount);
						diffAmount += (amount- amount1);
						NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
						arrInner.add(testNumberFormat.format(amount1));
					}
					else
					{
						arrInner.add("-");
					}
					arrInner.add(tuple[3]);
					arrInner.add(tuple[4]);
					arrOuterList.add(arrInner);
					i++;
				}
				ArrayList arrLast = new ArrayList();
				arrLast.add("Rounded Difference");
				arrLast.add("");
				arrLast.add("");
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				testNumberFormat.setGroupingUsed(false);
				testNumberFormat.setMaximumFractionDigits(3);
				testNumberFormat.setMinimumFractionDigits(3);
				arrLast.add(testNumberFormat.format(diffAmount));
			
				arrLast.add("");
				arrLast.add("");
				
				arrOuterList.add(arrLast);
			
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
		}		
		return arrOuterList;
	}
	
	/* End Shyamal */


	/* End Shyamal */
	
	/* Added By Maneesh*/
	
	public ArrayList getAllDept(int lintLangId) 
	{	
		glogger.info("Inside getAllDept");
	
		ArrayList lArrDept=null;
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer lSb=new StringBuffer();
		
		lSb.append(" select mst.location_code,mst.loc_name from cmn_location_mst mst "); 
		lSb.append(" where mst.department_id = (select dep.department_id from org_department_mst dep ");
		lSb.append(" where dep.department_code = '"+lObjRsrcBndle.getString("SachivalayDept")+"' and dep.lang_id="+lintLangId+") ");
		lSb.append(" order by mst.loc_name  ");
		
		glogger.info("Query for getAllDept() -- "+lSb.toString());
		
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			
			
			lArrDept=new ArrayList();
			while(rs.next())
			{
				glogger.info("Dept Ids are:"+rs.getString("location_code"));
				glogger.info("Dept Names are:"+rs.getString("loc_name"));
				lArrDept.add(rs.getString("location_code"));
				lArrDept.add(rs.getString("loc_name"));
				glogger.info("Depts are:"+lArrDept);
				
			}
		}
		catch(Exception e)
		{
			glogger.error("Exception in getAllDepts");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getAllDept() "+e);
			}
		}
		
		return lArrDept;
			
	}
	
	public HashMap getBudEstForDept(HashMap lHshMapBudPara)                      
	{	
		glogger.info("In getBudEstForDept()");
		HashMap<String,Double> lHashData = new HashMap<String,Double>();
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer strbuffer=new StringBuffer();
		int lintPlanType=0;
		String lStrPlanType="";
		String deptBud_id="";
		String lStrLang_Id = "";
		String bud_values="";
		int lintFinYrId=0 ;
		
		double ldbPlanAmt=0 ,ldbNonPlanAmt =0 ,ldbCssAmt =0,ldbBudTotal=0;
		double lDblDeptBudEstAmt=0;
		
		if(lHshMapBudPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHshMapBudPara.get("FinYrId").toString());
		}
		
		if(lHshMapBudPara.get("LangId")!=null)
		{
			lStrLang_Id = lObjRsrcBndle.getString(lHshMapBudPara.get("LangId").toString());
		}
		
		if(lHshMapBudPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHshMapBudPara.get("PlanType").toString());
			glogger.info("PlanType Type for Budget == "+lintPlanType);
		
			if(lintPlanType==-1)
			{
				lStrPlanType="Total";
			}
			else if(lintPlanType==0)
			{
				lStrPlanType="All";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
				 lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				 lStrPlanType="NP";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				 lStrPlanType="CSS";
			}
			
			if(lStrPlanType.equalsIgnoreCase("Total") || lStrPlanType.equalsIgnoreCase("All"))
			{
				glogger.info(" Inside -- Total OR All");
				
				strbuffer.append(" SELECT LOCDEPT.LOC_CODE DEPT_ID,");
				strbuffer.append("(SELECT (SUM(AMT.NXT_YR_BE) / 10000) ");
				strbuffer.append(" FROM SGVA_EXPEST_FINALAMT AMT, Sgva_Budbpn_Mapping b ");
				strbuffer.append(" WHERE AMT.PLAN_NONPLAN = 'PL' AND AMT.LEVEL_ID = 4 AND ");
				strbuffer.append(" AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = b.fin_yr_id and ");
				strbuffer.append(" b.fin_yr_id = "+lintFinYrId+" AND ");
				strbuffer.append(" (AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND ");
				strbuffer.append(" AMT.FORM_TYPE NOT LIKE 'RE' and amt.budbpn_code = b.bpncode ");
				strbuffer.append(" AND LOCDEPT.DEPARTMENT_ID = b.dept_id and b.lang_id='"+lStrLang_Id+"'and b.status='Active' ");
				strbuffer.append(" group by b.dept_Id ) PL_AMT, ");
				strbuffer.append(" (SELECT (SUM(AMT.NXT_YR_BE) / 10000) ");
				strbuffer.append(" FROM SGVA_EXPEST_FINALAMT AMT, Sgva_Budbpn_Mapping b ");
				strbuffer.append(" WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND ");
				strbuffer.append(" AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = b.fin_yr_id and ");
				strbuffer.append(" b.fin_yr_id = "+lintFinYrId+" AND ");
				strbuffer.append(" (AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND ");
				strbuffer.append(" AMT.FORM_TYPE NOT LIKE 'RE' and amt.budbpn_code = b.bpncode ");
				strbuffer.append(" AND LOCDEPT.DEPARTMENT_ID = b.dept_id and b.lang_id='"+lStrLang_Id+"'  and b.status='Active' ");
				strbuffer.append(" group by b.dept_Id) NP_AMT, ");
				strbuffer.append(" (select (SUM(AMT.NXT_YR_BE) / 10000) ");
				strbuffer.append(" FROM SGVA_EXPEST_FINALAMT AMT, ");
				strbuffer.append(" Sgva_Budbpn_Mapping b, ");
				strbuffer.append(" (select submst.bpn_code, ");
				strbuffer.append(" submst.demand_code, ");
				strbuffer.append(" submst.budmjrhd_code, ");
				strbuffer.append(" submst.budsubmjrhd_code, ");
				strbuffer.append(" submst.budminhd_code, ");
				strbuffer.append(" submst.budsubhd_code ");
				strbuffer.append(" from sgva_budsubhd_mst submst ");
				strbuffer.append(" where submst.css = 'Y' and submst.lang_id = '"+lStrLang_Id+"' and ");
				strbuffer.append(" submst.fin_yr_id ="+lintFinYrId+" and submst.status = 'Active') css ");
				strbuffer.append(" WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND ");
				strbuffer.append(" AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = b.fin_yr_id and ");
				strbuffer.append(" b.fin_yr_id ="+lintFinYrId+" AND ");
				strbuffer.append(" (AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND ");
				strbuffer.append(" AMT.FORM_TYPE NOT LIKE 'RE' and amt.budbpn_code = b.bpncode AND ");
				strbuffer.append(" B.LANG_ID = '"+lStrLang_Id+"' and b.status = 'Active' and ");
				strbuffer.append(" amt.buddemand_code = css.demand_code and ");
				strbuffer.append(" amt.budmjrhd_code = css.budmjrhd_code and ");
				strbuffer.append(" amt.budsubmjrhd_code = css.budsubmjrhd_code and ");
				strbuffer.append(" amt.budminhd_code = css.budminhd_code and ");
				strbuffer.append(" amt.budsubhd_code = css.budsubhd_code and ");
				strbuffer.append(" amt.budbpn_code = css.bpn_code and ");
				strbuffer.append(" b.dept_id = locdept.department_id ");
				strbuffer.append(" group by b.dept_id) CSS_AMT ");
				strbuffer.append(" FROM RLT_LOCATION_DEPARTMENT LOCDEPT ");
				
			}
			else if(lStrPlanType.equalsIgnoreCase("PL"))
			{
				glogger.info("------- Inside Plan---------  ");
			
				strbuffer.append(" select (select locdept.loc_code from rlt_location_department locdept where locdept.department_id=b.dept_Id)DEPT_ID,"); 
				strbuffer.append(" sum(f.nxt_Yr_Be / 10000)"+lStrPlanType+" ");
				strbuffer.append(" from Sgva_Expest_Finalamt f, Sgva_Budbpn_Mapping b ");
				strbuffer.append(" where f.fin_Yr_Id = b.fin_yr_id and b.fin_yr_id = "+lintFinYrId+" and f.level_Id = 4 and ");
				strbuffer.append(" f.budbpn_Code = b.bpncode and f.gia_Flg = 'N' and ");
				strbuffer.append(" (f.rec_Flag not in ('R', 'C', 'M') or f.rec_Flag is null) and ");
				strbuffer.append(" f.form_Type <> 'RE' and f.plan_Nonplan = '"+lStrPlanType+"' and ");
				strbuffer.append(" b.lang_Id = '"+lStrLang_Id+"' ");
				strbuffer.append(" group by b.dept_Id ");
				
			}
			
			else if(lStrPlanType.equalsIgnoreCase("NP"))
			{
				glogger.info("---------Inside NP------");
				strbuffer.append(" SELECT LOCDEPT.LOC_CODE DEPT_ID, ");
				strbuffer.append(" (SELECT (SUM(AMT.NXT_YR_BE) / 10000) ");
				strbuffer.append(" FROM SGVA_EXPEST_FINALAMT AMT, Sgva_Budbpn_Mapping b ");
				strbuffer.append(" WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND ");
				strbuffer.append(" AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = b.fin_yr_id and ");
				strbuffer.append(" b.fin_yr_id = "+lintFinYrId+" AND ");
				strbuffer.append(" (AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND ");
				strbuffer.append(" AMT.FORM_TYPE NOT LIKE 'RE' and amt.budbpn_code = b.bpncode AND ");
				strbuffer.append(" LOCDEPT.DEPARTMENT_ID = b.dept_id and b.lang_id = '"+lStrLang_Id+"' ");
				strbuffer.append(" group by b.dept_Id) NP_AMT, ");
				strbuffer.append(" (select (SUM(AMT.NXT_YR_BE) / 10000) ");
				strbuffer.append(" FROM SGVA_EXPEST_FINALAMT AMT, ");
				strbuffer.append(" Sgva_Budbpn_Mapping b, ");
				strbuffer.append(" (select submst.bpn_code,");
				strbuffer.append(" submst.demand_code, ");
				strbuffer.append(" submst.budmjrhd_code, ");
				strbuffer.append(" submst.budsubmjrhd_code, ");
				strbuffer.append(" submst.budminhd_code, ");
				strbuffer.append(" submst.budsubhd_code ");
				strbuffer.append(" from sgva_budsubhd_mst submst ");
				strbuffer.append(" where submst.css = 'Y' and submst.lang_id = '"+lStrLang_Id+"' and ");
				strbuffer.append(" submst.fin_yr_id = "+lintFinYrId+" and submst.status = 'Active') css ");
				strbuffer.append(" WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND ");
				strbuffer.append(" AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = b.fin_yr_id and ");
				strbuffer.append(" b.fin_yr_id = "+lintFinYrId+" AND ");
				strbuffer.append(" (AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND ");
				strbuffer.append(" AMT.FORM_TYPE NOT LIKE 'RE' and amt.budbpn_code = b.bpncode AND ");
				strbuffer.append(" B.LANG_ID = '"+lStrLang_Id+"' and b.status = 'Active' and ");
				strbuffer.append(" amt.buddemand_code = css.demand_code and ");
				strbuffer.append(" amt.budmjrhd_code = css.budmjrhd_code and ");
				strbuffer.append(" amt.budsubmjrhd_code = css.budsubmjrhd_code and ");
				strbuffer.append(" amt.budminhd_code = css.budminhd_code and ");
				strbuffer.append(" amt.budsubhd_code = css.budsubhd_code and ");
				strbuffer.append(" amt.budbpn_code = css.bpn_code and ");
				strbuffer.append(" b.dept_id = locdept.department_id ");
				strbuffer.append(" group by b.dept_id) CSS_AMT ");
				strbuffer.append(" FROM RLT_LOCATION_DEPARTMENT LOCDEPT ");
			}
			else if(lStrPlanType.equalsIgnoreCase("CSS"))
			{
				glogger.info("----Inside CSS for budget--------- ");
				/*strbuffer.append(" select loc.loc_code DEPT_ID, (sum(css.css_Amount)/10000)"+lStrPlanType +" "); 
				strbuffer.append(" from Sgva_Budcss_Mst css, Sgva_Budbpn_Mapping bpn , rlt_location_department loc ");
				strbuffer.append(" where bpn.bpncode = css.budbpn_Code and bpn.lang_Id ='"+lStrLang_Id+"' and ");
				strbuffer.append(" loc.department_id= bpn.dept_Id and ");
				strbuffer.append(" css.fin_Yr_Id = bpn.fin_yr_id and bpn.fin_yr_id="+ lintFinYrId+" ");
				strbuffer.append(" group by loc.loc_code");*/ 
				
				
				strbuffer.append(" SELECT LOCDEPT.LOC_CODE DEPT_ID,(SUM(AMT.NXT_YR_BE) / 10000) CSS ");
				strbuffer.append(" FROM SGVA_EXPEST_FINALAMT AMT, ");
				strbuffer.append(" Sgva_Budbpn_Mapping b,rlt_location_department LOCDEPT, ");
				strbuffer.append(" (select submst.bpn_code, ");
				strbuffer.append(" submst.demand_code, ");
				strbuffer.append(" submst.budmjrhd_code, ");
				strbuffer.append(" submst.budsubmjrhd_code, ");
				strbuffer.append(" submst.budminhd_code, ");
				strbuffer.append(" submst.budsubhd_code ");
				strbuffer.append(" from sgva_budsubhd_mst submst ");
				strbuffer.append(" where submst.css = 'Y' and submst.lang_id = '"+lStrLang_Id+"' and ");
				strbuffer.append(" submst.fin_yr_id = "+ lintFinYrId+" and submst.status='Active') css ");
				strbuffer.append(" WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND AMT.GIA_FLG = 'N' AND ");
				strbuffer.append(" AMT.FIN_YR_ID = b.fin_yr_id and b.fin_yr_id ="+ lintFinYrId+" AND ");
				strbuffer.append(" (AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND ");
				strbuffer.append(" AMT.FORM_TYPE NOT LIKE 'RE' and amt.budbpn_code = b.bpncode AND ");
				strbuffer.append(" B.LANG_ID = '"+lStrLang_Id+"' and b.status='Active' and "); 
				strbuffer.append(" amt.buddemand_code = css.demand_code and ");
				strbuffer.append(" amt.budmjrhd_code = css.budmjrhd_code and ");
				strbuffer.append(" amt.budsubmjrhd_code = css.budsubmjrhd_code and ");
				strbuffer.append(" amt.budminhd_code = css.budminhd_code and ");
				strbuffer.append(" amt.budsubhd_code = css.budsubhd_code and ");
				strbuffer.append(" amt.budbpn_code = css.bpn_code and b.dept_id=locdept.department_id ");
				strbuffer.append(" group by LOCDEPT.LOC_CODE");
			}
			glogger.info("Query for getBudEstForDept -- "+strbuffer.toString());
			try
			 {
				lCon=DBConnection.getConnection();
				lStmt=lCon.createStatement();
				rs=lStmt.executeQuery(strbuffer.toString());
				
				if(lStrPlanType.equalsIgnoreCase("Total"))
				{
					while(rs.next())
					{
						glogger.info("In while");
						if(rs.getString("DEPT_ID")!=null)
						{	
							deptBud_id=rs.getString("DEPT_ID")+"_"+lStrPlanType;
							glogger.info("--------Dept Id for budget --------"+deptBud_id);
							
							ldbPlanAmt=rs.getDouble("PL_AMT");
							ldbNonPlanAmt=rs.getDouble("NP_AMT")-rs.getDouble("CSS_AMT");
							ldbCssAmt=rs.getDouble("CSS_AMT");
							ldbBudTotal=(ldbPlanAmt+ldbNonPlanAmt);
							glogger.info("--------Total for budget --------"+ldbBudTotal);
							lHashData.put(deptBud_id, ldbBudTotal);
						}
					}
				}
				else if(lStrPlanType.equalsIgnoreCase("All"))
				{
					glogger.info("Inside All for Budget");
					while(rs.next())
					{
						if(rs.getString("DEPT_ID")!=null)
						{	
							ldbPlanAmt=rs.getDouble("PL_AMT");
							ldbNonPlanAmt=rs.getDouble("NP_AMT")-rs.getDouble("CSS_AMT");
							ldbCssAmt=rs.getDouble("CSS_AMT");
							ldbBudTotal=(ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt);
							glogger.info("Total for budget:"+ldbBudTotal);
						
							lHashData.put(rs.getString("DEPT_ID")+"_PL", ldbPlanAmt);
							lHashData.put(rs.getString("DEPT_ID")+"_NP", ldbNonPlanAmt);
							lHashData.put(rs.getString("DEPT_ID")+"_CSS",ldbCssAmt);
							lHashData.put(rs.getString("DEPT_ID")+"_Total", ldbBudTotal);
						}
					}
				}
				
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					glogger.info("Inside NP for Budget");
					while(rs.next())
					{
						if(rs.getString("DEPT_ID")!=null)
						{	
							glogger.info("Actual Non plan Amount is::"+rs.getDouble("NP_AMT"));
							glogger.info("Actual CSS Amount is::"+rs.getDouble("CSS_AMT"));
							ldbNonPlanAmt=rs.getDouble("NP_AMT")-rs.getDouble("CSS_AMT");
							glogger.info("----Subtracted NonPlan Amount is::----"+ldbNonPlanAmt);
							lHashData.put(rs.getString("DEPT_ID")+"_"+lStrPlanType, ldbNonPlanAmt);
						}
					}
				}
				else
				{
					glogger.info("In else");
					while(rs.next())
					{
						if(rs.getString("DEPT_ID")!=null)
						{
							deptBud_id=rs.getString("DEPT_ID")+"_"+lStrPlanType;
							glogger.info("--------Dept Id for budget --------"+deptBud_id);
							lDblDeptBudEstAmt=rs.getDouble(lStrPlanType);
							glogger.info("--------Total for budget --------"+lDblDeptBudEstAmt);
							lHashData.put(deptBud_id, lDblDeptBudEstAmt);
						}
					}
				}
			 }		 
			 catch(Exception e)
			 {
				e.printStackTrace();
			 }
			 finally
			 {
				try
				{
					rs.close();
					lStmt.close();
					lCon.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			 }
		}
		else
		{
			glogger.info("Budget Type not Found for Budget Estimate");
		}
		glogger.info("Beforing returning from getBudEstForDept");
		 return lHashData;
	}
	
	public HashMap getDeptGrantAmt(HashMap lHshMapGrantPara) 
	{
		glogger.info("---Inside  getDeptGrantAmt---- ");
		
		HashMap<String,Double> lHashData = new HashMap<String,Double>();
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		String lStrDeptGrant_id=null;
		
		double lDblDeptGrantEstAmt=0;
		StringBuffer strbuffer=null;
		String lStrPlanType="";
		String lStrLangId = "";
		
		int lintPlanType=0;
		int lintFinYrId=0 ;
		double ldbPlanAmt=0 ,ldbNonPlanAmt =0 ,ldbCssAmt =0,ldbTotalGrant=0;
		
		
		
		if(lHshMapGrantPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHshMapGrantPara.get("FinYrId").toString());
		}
		if(lHshMapGrantPara.get("LangId")!=null)
		{
			lStrLangId = lObjRsrcBndle.getString(lHshMapGrantPara.get("LangId").toString());
		}
		
		if(lHshMapGrantPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHshMapGrantPara.get("PlanType").toString());
			glogger.info("Budget Type for Grant == "+lintPlanType);
		
			if(lintPlanType==-1)
			{
				 lStrPlanType="Total";
			}
			else if(lintPlanType==0)
			{
				lStrPlanType="All";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
				 lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				 lStrPlanType="NP";
			}
			
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				 lStrPlanType="CSS";
			}
			 if(lStrPlanType.equalsIgnoreCase("Total") || lStrPlanType.equalsIgnoreCase("All"))
			 {
				 glogger.info("--Inside Total--");
				 strbuffer=new StringBuffer();
				 strbuffer.append(" select locdept.loc_code DEPT_ID, ");
				 strbuffer.append(" (select sum(grnt.Plan_Amount*1000) "); 
				 strbuffer.append(" from Sgva_Budgrant_Mst grnt where grnt.budbpn_code in ");
				 strbuffer.append(" (select bpn.bpncode from sgva_budbpn_mapping bpn where ");
				 strbuffer.append(" bpn.dept_id= locdept.Department_Id)and grnt.fin_yr_id="+lintFinYrId+")PL_Amt, ");
				 strbuffer.append(" (select sum(grnt.NonPlan_Amount*1000) ");
				 strbuffer.append(" from Sgva_Budgrant_Mst grnt where grnt.budbpn_code in ");
				 strbuffer.append(" (select bpn.bpncode from sgva_budbpn_mapping bpn where ");
				 strbuffer.append(" bpn.dept_id= locdept.Department_Id)and grnt.fin_yr_id="+lintFinYrId+")NP_Amt, ");
				 strbuffer.append(" (select sum(grnt.CSS_Amount*1000) ");
				 strbuffer.append(" from Sgva_Budgrant_Mst grnt where grnt.budbpn_code in ");
				 strbuffer.append(" (select bpn.bpncode from sgva_budbpn_mapping bpn where ");
				 strbuffer.append(" bpn.dept_id= locdept.Department_Id)and grnt.fin_yr_id="+lintFinYrId+")CSS_Amt ");
				 strbuffer.append(" from rlt_location_department locdept ");
				 
				 
			/*	 strbuffer.append(" SELECT locdept.loc_code DEPT_ID, ");
				 strbuffer.append("(select NVL(SUM(S.GRANT_AMOUNT_STATE + S.GRANT_AMOUNT_CSS), 0) ");
				 strbuffer.append(" FROM SGVA_GRANT_ORDER_DETAIL S, rlt_location_department locdept ");
				 strbuffer.append(" WHERE S.FIN_YEAR_ID = "+lintFinYrId+" AND S.PLAN_NONPLAN = 'PL' AND ");
				 strbuffer.append(" s.demand_code in ");
				 strbuffer.append(" (select bd.demand_code ");
				 strbuffer.append(" from sgva_buddemand_mst bd ");
				 strbuffer.append(" where bd.bpncode in ");
				 strbuffer.append(" (select bpn.bpncode ");
				 strbuffer.append(" from sgva_budbpn_mapping bpn ");
				 strbuffer.append(" where bpn.dept_id = locdept.Department_Id and ");
				 strbuffer.append(" bpn.fin_yr_id = "+lintFinYrId+" and bpn.status = 'Active' and ");
				 strbuffer.append(" bpn.lang_id = '"+lStrLangId+"') and ");
				 strbuffer.append(" bd.lang_id = '"+lStrLangId+"' and bd.fin_yr_id = "+lintFinYrId+" and ");
				 strbuffer.append(" bd.status = 'Active') and ");
				 strbuffer.append(" s.to_dept_id = locdept.department_id AND ");
				 strbuffer.append(" S.GRANT_RELEASE_STATUS = 'R' AND s.fin_year_id = "+lintFinYrId+" and ");
				 strbuffer.append(" s.file_creator_type = 'FD') PL_Amt, ");
				 strbuffer.append(" (select NVL(SUM(S.GRANT_AMOUNT_STATE), 0) ");
				 strbuffer.append(" FROM SGVA_GRANT_ORDER_DETAIL S, rlt_location_department locdept ");
				 strbuffer.append(" WHERE S.FIN_YEAR_ID = "+lintFinYrId+" AND S.PLAN_NONPLAN = 'NP' AND ");
				 strbuffer.append(" s.demand_code in ");
				 strbuffer.append(" (select bd.demand_code ");
				 strbuffer.append(" from sgva_buddemand_mst bd ");
				 strbuffer.append(" where bd.bpncode in ");
				 strbuffer.append(" (select bpn.bpncode ");
				 strbuffer.append(" from sgva_budbpn_mapping bpn ");
				 strbuffer.append(" where bpn.dept_id = locdept.Department_Id and ");
				 strbuffer.append(" bpn.fin_yr_id = "+lintFinYrId+" and bpn.status = 'Active' and ");
				 strbuffer.append(" bpn.lang_id = '"+lStrLangId+"') and ");
				 strbuffer.append(" bd.lang_id = '"+lStrLangId+"' and bd.fin_yr_id = "+lintFinYrId+" and ");
				 strbuffer.append(" bd.status = 'Active') and ");
				 strbuffer.append(" s.to_dept_id = locdept.department_id AND ");
				 strbuffer.append(" S.GRANT_RELEASE_STATUS = 'R' AND s.fin_year_id = "+lintFinYrId+" and ");
				 strbuffer.append(" s.file_creator_type = 'FD')NP_Amt, ");
				 strbuffer.append(" (select NVL(SUM(S.Grant_Amount_Css), 0) ");
				 strbuffer.append(" FROM SGVA_GRANT_ORDER_DETAIL S, rlt_location_department locdept ");
				 strbuffer.append(" WHERE S.FIN_YEAR_ID = "+lintFinYrId+" AND S.PLAN_NONPLAN = 'NP' AND ");
				 strbuffer.append(" s.demand_code in ");
				 strbuffer.append(" (select bd.demand_code ");
				 strbuffer.append(" from sgva_buddemand_mst bd ");
				 strbuffer.append(" where bd.bpncode in ");
				 strbuffer.append(" (select bpn.bpncode ");
				 strbuffer.append(" from sgva_budbpn_mapping bpn ");
				 strbuffer.append(" where bpn.dept_id = locdept.Department_Id and ");
				 strbuffer.append(" bpn.fin_yr_id = "+lintFinYrId+" and bpn.status = 'Active' and ");
				 strbuffer.append(" bpn.lang_id = '"+lStrLangId+"') and ");
				 strbuffer.append(" bd.lang_id = '"+lStrLangId+"' and bd.fin_yr_id = "+lintFinYrId+" and ");
				 strbuffer.append(" bd.status = 'Active') and ");
				 strbuffer.append(" s.to_dept_id = locdept.department_id AND ");
				 strbuffer.append(" S.GRANT_RELEASE_STATUS = 'R' AND s.fin_year_id = "+lintFinYrId+")CSS_Amt ");
				 strbuffer.append(" from rlt_location_department locdept ");*/
			 }
			 else
			 {
				glogger.info("inside  else for Grant");
				strbuffer=new StringBuffer();
				 
				strbuffer.append(" select locdept.loc_code DEPT_ID, ");
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					strbuffer.append(" (select sum((grnt.Plan_Amount)*1000) ");
				}
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					strbuffer.append(" (select sum((grnt.NonPlan_Amount)*1000) ");
				}
				else if(lStrPlanType.equalsIgnoreCase("CSS"))
				{
					strbuffer.append(" (select sum((grnt.CSS_Amount)*1000) ");
				}
				strbuffer.append(" from Sgva_Budgrant_Mst grnt where grnt.budbpn_code in ");
				strbuffer.append(" (select bpn.bpncode from sgva_budbpn_mapping bpn where ");
				strbuffer.append(" bpn.dept_id= locdept.Department_Id)and grnt.fin_yr_id="+lintFinYrId+")"+lStrPlanType+" ");
				strbuffer.append(" from rlt_location_department locdept ");
				
				
			/*	strbuffer.append(" SELECT locdept.loc_code DEPT_ID, ");
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					strbuffer.append(" NVL(SUM(S.GRANT_AMOUNT_STATE + S.GRANT_AMOUNT_CSS), 0)"+lStrPlanType+" ");
				}
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					strbuffer.append(" NVL(SUM(S.GRANT_AMOUNT_STATE), 0)"+lStrPlanType+" ");
				}
				else if(lStrPlanType.equalsIgnoreCase("CSS"))
				{
					strbuffer.append(" NVL(SUM(S.Grant_Amount_Css), 0))"+lStrPlanType+" ");
				}
		       
				strbuffer.append(" FROM SGVA_GRANT_ORDER_DETAIL S, rlt_location_department locdept ");
				strbuffer.append(" WHERE S.FIN_YEAR_ID = "+lintFinYrId+" AND ");
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					strbuffer.append(" S.PLAN_NONPLAN = 'PL' AND ");
				}
				else if(lStrPlanType.equalsIgnoreCase("PL")||lStrPlanType.equalsIgnoreCase("CSS"))
				{
					strbuffer.append(" S.PLAN_NONPLAN = 'NP' AND ");
				}
		        
				strbuffer.append(" s.demand_code in ");
				strbuffer.append(" (select bd.demand_code ");
				strbuffer.append(" from sgva_buddemand_mst bd ");
				strbuffer.append(" where bd.bpncode in ");
				strbuffer.append(" (select bpn.bpncode ");
				strbuffer.append(" from sgva_budbpn_mapping bpn ");
				strbuffer.append(" where bpn.dept_id = locdept.Department_Id and ");
				strbuffer.append(" bpn.fin_yr_id = "+lintFinYrId+" and bpn.status = 'Active' and ");
				strbuffer.append(" bpn.lang_id = '"+lStrLangId+"') and ");
				strbuffer.append(" bd.lang_id = '"+lStrLangId+"' and bd.fin_yr_id = "+lintFinYrId+" and ");
				strbuffer.append(" bd.status = 'Active') and ");
				strbuffer.append(" s.to_dept_id = locdept.department_id AND ");
				strbuffer.append(" S.GRANT_RELEASE_STATUS = 'R' AND s.fin_year_id = "+lintFinYrId+" and ");
				strbuffer.append(" s.file_creator_type = 'FD' ");
				strbuffer.append(" group by locdept.loc_code ");*/
			 }
			glogger.info("Query for getDeptGrantAmount -- "+strbuffer.toString());
			 try
			 {
				glogger.info("Inside Try for Grant");
				lCon=DBConnection.getConnection();
				lStmt=lCon.createStatement();
				rs=lStmt.executeQuery(strbuffer.toString());
				
				if(lStrPlanType.equalsIgnoreCase("Total"))
				{
					while(rs.next())
					{
						lStrDeptGrant_id=rs.getString("DEPT_ID")+"_"+lStrPlanType;
						glogger.info("--DeptID in Grant is--"+lStrDeptGrant_id);
						
						ldbPlanAmt= rs.getDouble("PL_Amt");
						ldbNonPlanAmt = rs.getDouble("NP_Amt");
						ldbCssAmt = rs.getDouble("CSS_Amt");
						ldbTotalGrant = ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt;
						glogger.info("Total in grant:"+ldbTotalGrant);
						
						lHashData.put(lStrDeptGrant_id, ldbTotalGrant);
					}
				}
				else if(lStrPlanType.equalsIgnoreCase("All"))
				{
					while(rs.next())
					{
						ldbPlanAmt= rs.getDouble("PL_Amt");
						ldbNonPlanAmt = rs.getDouble("NP_Amt");
						ldbCssAmt = rs.getDouble("CSS_Amt");
						ldbTotalGrant = ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt;
						glogger.info("Total in grant:"+ldbTotalGrant);
						
						lHashData.put(rs.getString("DEPT_ID")+"_PL", ldbPlanAmt);
						lHashData.put(rs.getString("DEPT_ID")+"_NP", ldbNonPlanAmt);
						lHashData.put(rs.getString("DEPT_ID")+"_CSS", ldbCssAmt);
						lHashData.put(rs.getString("DEPT_ID")+"_Total", ldbTotalGrant);
					}
				}
				else
				{
					while(rs.next())
					{
						lStrDeptGrant_id=rs.getString("DEPT_ID")+"_"+lStrPlanType;
						glogger.info("--DeptID for grant is--"+lStrDeptGrant_id);
						lDblDeptGrantEstAmt=rs.getDouble(lStrPlanType);
						glogger.info("--------Value for Grant --------"+lDblDeptGrantEstAmt);
						lHashData.put(lStrDeptGrant_id, lDblDeptGrantEstAmt);
					}
				}
			 }
			 catch(Exception e)
			 {
				e.printStackTrace();
			 }
			 
			 finally
			 {
				try
				{
					rs.close();
					lStmt.close();
					lCon.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			 }
		}
		else
		{
			glogger.info("Budget Type not Found for Grant");
		}
		glogger.info("Brfore returning from getDeptGrantAmt");
		return lHashData;		
	}
	
	
	public HashMap getExpForDeptMjrHd(HashMap lHashPara)
	{
		glogger.info("-- Inside getExpForDeptMjrHd() -- ");
		HashMap<String,Double> lHashData = new HashMap<String,Double>();
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;
		StringBuffer lsb=new StringBuffer();
		String lStrPlanType="";
		String MjrHdRevExp_Id="";
		String bud_values="";
		String lStrLangId = lObjRsrcBndle.getString(lHashPara.get("LangId").toString());
		
		
		int lintLangId=0, lintFinYrId=0,lintPlanType=0 ;
		double ldbPlanAmt=0 ,ldbNonPlanAmt =0 ,ldbCSSAmt =0, lDeptMjrHdExpAmt=0;
		int lintPLId=0,lintNPId=0,lintCSSId=0;
		int lintLocId=Integer.parseInt(lHashPara.get("DeptId").toString());
		
		
		
		if(lHashPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHashPara.get("FinYrId").toString());
		}
		
		if(lHashPara.get("LangId")!=null)
		{
			lintLangId = Integer.parseInt(lHashPara.get("LangId").toString());
		}
		
		if(lHashPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHashPara.get("PlanType").toString());
			glogger.info("Budget Type for ExpForDeptMjrHd == "+lintPlanType);
			 
			if(lintPlanType==-1)
			{
				 lStrPlanType="Total";
			}
			else if(lintPlanType==0)
			{
				 lStrPlanType="All";
			}
			
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
			//	bud_values = "('"+lObjRsrcBndle.getString("Plan_1")+"','"+lObjRsrcBndle.getString("Plan_2")+"','"+lObjRsrcBndle.getString("Plan_3")+"')";
				lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				lStrPlanType="NP";
			}
			
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				lStrPlanType="CSS";
			}
			if(lStrPlanType.equalsIgnoreCase("Total")||lStrPlanType.equalsIgnoreCase("All"))
			{
				lsb.append(" select lpad(mjr_hd,4,'0') mjr_hd, ");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_1,");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_2,");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_3,");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_1, ");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_2, ");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_3, ");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_1, ");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_2, ");
				lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_3 ");
				lsb.append(" from (select expdtls.demand_no, expdtls.mjr_hd, expdtls.gross_amnt,expdtls.recovery_amt,"); 
				lsb.append(" expdtls.bud_type_code from rpt_expenditure_dtls expdtls where ");
				lsb.append(" expdtls.exp_dt between TO_DATE('"+lHashPara.get("FDate").toString()+"', 'dd/mm/yyyy') and ");
				lsb.append(" TO_DATE('"+lHashPara.get("EndDate")+"', 'dd/mm/yyyy')and ");
				if(lHashPara.get("MjrEndHd") != null)
				{
					lsb.append(" expdtls.mjr_hd >="+ Integer.parseInt(lHashPara.get("MjrStartHd").toString())+" and expdtls.mjr_hd <= "+Integer.parseInt(lHashPara.get("MjrEndHd").toString())+" and");
				}
				else if(lHashPara.get("MjrStartHd") != null && lHashPara.get("MjrEndHd") == null)
				{
					lsb.append(" expdtls.mjr_hd ="+Integer.parseInt(lHashPara.get("MjrStartHd").toString())+ "and ");
				}
				lsb.append(" lpad(expdtls.demand_no,3,'0') in  ");
				lsb.append(" (select dmd.demand_code from sgva_buddemand_mst dmd where dmd.bpncode in ");
				lsb.append(" (select bpn.bpncode from sgva_budbpn_mapping bpn where bpn.dept_id = ");
				lsb.append(" (SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
				lsb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHashPara.get("DeptId").toString())+" ");
				lsb.append(" AND LOC.LANG_ID = "+lintLangId+") ");
				lsb.append(" and bpn.lang_id = '"+lStrLangId+"')))a ");				
		    	lsb.append("group by mjr_hd  ");
			}
				
			else
			{
				lsb.append(" select lpad(mjr_hd,4,'0') mjr_hd, ");
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					glogger.info("Inside plan");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_1,");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_2,");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_3");
				}
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					glogger.info("Inside NP");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_1,");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_2,");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_3");
				}
				else if(lStrPlanType.equalsIgnoreCase("CSS"))
				{
					glogger.info("CSS");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_1,");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_2,");
					lsb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_3");
				}
				lsb.append(" from (select expdtls.demand_no, expdtls.mjr_hd, expdtls.gross_amnt,expdtls.recovery_amt,"); 
				lsb.append(" expdtls.bud_type_code from rpt_expenditure_dtls expdtls where ");
				lsb.append(" expdtls.exp_dt between TO_DATE('"+lHashPara.get("FDate").toString()+"', 'dd/mm/yyyy') and ");
				lsb.append(" TO_DATE('"+lHashPara.get("EndDate")+"', 'dd/mm/yyyy')and ");
				if(lHashPara.get("MjrEndHd") != null)
				{
					lsb.append(" expdtls.mjr_hd >="+ Integer.parseInt(lHashPara.get("MjrStartHd").toString())+" and expdtls.mjr_hd <= "+Integer.parseInt(lHashPara.get("MjrEndHd").toString())+" and");
				}
				else if(lHashPara.get("MjrStartHd") != null && lHashPara.get("MjrEndHd") == null)
				{
					lsb.append(" expdtls.mjr_hd ="+Integer.parseInt(lHashPara.get("MjrStartHd").toString())+ "and ");
				}
				lsb.append(" lpad(expdtls.demand_no,3,'0') in ");
				lsb.append(" (select dmd.demand_code from sgva_buddemand_mst dmd where dmd.bpncode in ");
				lsb.append(" (select bpn.bpncode from sgva_budbpn_mapping bpn where bpn.dept_id = ");
				lsb.append(" (SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
				lsb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHashPara.get("DeptId").toString())+" ");
				lsb.append(" AND LOC.LANG_ID = "+lintLangId+") ");
				lsb.append(" and bpn.lang_id = '"+lStrLangId+"')))a ");
		    	lsb.append("group by mjr_hd  ");
			}
		
			glogger.info("Query to getExpForDeptMjrHd -- "+lsb.toString());
			
			try
			 {
				glogger.info("Inside try for MajorHeadExpenditure");
				lCon=DBConnection.getConnection();
				lStmt=lCon.createStatement();
				rs=lStmt.executeQuery(lsb.toString());
				
				if(lStrPlanType.equalsIgnoreCase("Total"))
				{
					while(rs.next())
					{
						MjrHdRevExp_Id=rs.getString("mjr_hd")+"_"+lStrPlanType;
						glogger.info("--------MjrHd for Expenditure --------"+MjrHdRevExp_Id);
						
						ldbPlanAmt=rs.getDouble("Plan_1")+rs.getDouble("Plan_2")+rs.getDouble("Plan_3");
						ldbNonPlanAmt=rs.getDouble("NPL_1")+rs.getDouble("NPL_2")+rs.getDouble("NPL_3");
						ldbCSSAmt=rs.getDouble("CSS_1")+rs.getDouble("CSS_2")+rs.getDouble("CSS_3");
						lDeptMjrHdExpAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCSSAmt);
						glogger.info("--------Total for Expenditure --------"+lDeptMjrHdExpAmt);
						lHashData.put(MjrHdRevExp_Id, lDeptMjrHdExpAmt);
					}
				}
				else if(lStrPlanType.equalsIgnoreCase("All"))
				{
					while(rs.next())
					{
					
						ldbPlanAmt=rs.getDouble("Plan_1")+rs.getDouble("Plan_2")+rs.getDouble("Plan_3");
						ldbNonPlanAmt=rs.getDouble("NPL_1")+rs.getDouble("NPL_2")+rs.getDouble("NPL_3");
						ldbCSSAmt=rs.getDouble("CSS_1")+rs.getDouble("CSS_2")+rs.getDouble("CSS_3");
						lDeptMjrHdExpAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCSSAmt);
						lHashData.put(rs.getString("mjr_hd")+"_PL", ldbPlanAmt);
						lHashData.put(rs.getString("mjr_hd")+"_NP", ldbNonPlanAmt);
						lHashData.put(rs.getString("mjr_hd")+"_CSS", ldbCSSAmt);
						lHashData.put(rs.getString("mjr_hd")+"_Total", lDeptMjrHdExpAmt);
					}
				}
				else
				{
					if(lStrPlanType.equalsIgnoreCase("PL"))
					{
						while(rs.next())
						{
							MjrHdRevExp_Id=rs.getString("mjr_hd")+"_"+lStrPlanType;
							glogger.info("--------Dept Id for Expenditure --------"+MjrHdRevExp_Id);
							lDeptMjrHdExpAmt=rs.getDouble("Plan_1")+rs.getDouble("Plan_2")+rs.getDouble("Plan_3");
							glogger.info("--------Total for Expenditure--------"+lDeptMjrHdExpAmt);
							lHashData.put(MjrHdRevExp_Id, lDeptMjrHdExpAmt);
						}
					}
					else if(lStrPlanType.equalsIgnoreCase("NP"))
					{
						while(rs.next())
						{
							MjrHdRevExp_Id=rs.getString("mjr_hd")+"_"+lStrPlanType;
							glogger.info("--------Dept Id for Expenditure --------"+MjrHdRevExp_Id);
							lDeptMjrHdExpAmt=rs.getDouble("NPL_1")+rs.getDouble("NPL_2")+rs.getDouble("NPL_3");
							glogger.info("--------Total for Expenditure--------"+lDeptMjrHdExpAmt);
							lHashData.put(MjrHdRevExp_Id, lDeptMjrHdExpAmt);
						}
					}
					else if(lStrPlanType.equalsIgnoreCase("CSS"))
					{
						while(rs.next())
						{
							MjrHdRevExp_Id=rs.getString("mjr_hd")+"_"+lStrPlanType;
							glogger.info("--------Dept Id for Expenditure --------"+MjrHdRevExp_Id);
							lDeptMjrHdExpAmt=rs.getDouble("CSS_1")+rs.getDouble("CSS_2")+rs.getDouble("CSS_3");
							glogger.info("--------Total for Expenditure--------"+lDeptMjrHdExpAmt);
							lHashData.put(MjrHdRevExp_Id, lDeptMjrHdExpAmt);
						}
					}
					
				}		
			 }
			 catch(Exception e)
			 {
				e.printStackTrace();
			 }
			 finally
			 {
				try
				{
					rs.close();
					lStmt.close();
					lCon.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			 }
		}
		else
		{
			glogger.info("Exp Type not Found for Expenditure");
		}
	 return lHashData;
	}

	
	
	public HashMap getExpForDept(HashMap lHashPara)
	{
		glogger.info("-- Inside getExpForDept() -- ");
		HashMap<String,Double> lHashData = new HashMap<String,Double>();
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;
		StringBuffer lsb=new StringBuffer();
		String lStrPlanType="";
		String DepRevExp_Id="";
		String bud_Plan_values="";
		String bud_NonPlan_values="";
		String bud_CSS_values="";
		
		int lintLangId=0, lintFinYrId=0,lintPlanType=0 ;
		double ldbPlanAmt=0 ,ldbNonPlanAmt =0 ,ldbCSSAmt =0,lDeptExpAmt=0;
		int lintPLId=0,lintNPId=0,lintCSSId=0;
		
		if(lHashPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHashPara.get("FinYrId").toString());
		}
		
		if(lHashPara.get("LangId")!=null)
		{
			lintLangId = Integer.parseInt(lHashPara.get("LangId").toString());
		}
		
		if(lHashPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHashPara.get("PlanType").toString());
			glogger.info("Budget Type for Expenditure == "+lintPlanType);
		
			if(lintPlanType==-1)
			{
				bud_Plan_values = "'"+lObjRsrcBndle.getString("Plan_1")+"','"+lObjRsrcBndle.getString("Plan_2")+"','"+lObjRsrcBndle.getString("Plan_3")+"'";
				bud_NonPlan_values = "'"+lObjRsrcBndle.getString("NPL_1")+"','"+lObjRsrcBndle.getString("NPL_2")+"','"+lObjRsrcBndle.getString("NPL_3")+"'"; 
				bud_CSS_values = "'"+lObjRsrcBndle.getString("CSS_1")+"','"+lObjRsrcBndle.getString("CSS_2")+"','"+lObjRsrcBndle.getString("CSS_3")+"'";
				 lStrPlanType="Total"; 
			}
			else if(lintPlanType==0)
			{
				bud_Plan_values = "'"+lObjRsrcBndle.getString("Plan_1")+"','"+lObjRsrcBndle.getString("Plan_2")+"','"+lObjRsrcBndle.getString("Plan_3")+"'";
				bud_NonPlan_values = "'"+lObjRsrcBndle.getString("NPL_1")+"','"+lObjRsrcBndle.getString("NPL_2")+"','"+lObjRsrcBndle.getString("NPL_3")+"'"; 
				bud_CSS_values = "'"+lObjRsrcBndle.getString("CSS_1")+"','"+lObjRsrcBndle.getString("CSS_2")+"','"+lObjRsrcBndle.getString("CSS_3")+"'";
				lStrPlanType="All"; 
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
				bud_Plan_values = "'"+lObjRsrcBndle.getString("Plan_1")+"','"+lObjRsrcBndle.getString("Plan_2")+"','"+lObjRsrcBndle.getString("Plan_3")+"'";
				lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				bud_NonPlan_values = "'"+lObjRsrcBndle.getString("NPL_1")+"','"+lObjRsrcBndle.getString("NPL_2")+"','"+lObjRsrcBndle.getString("NPL_3")+"'";
				lStrPlanType="NP";
			}
			
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				bud_CSS_values = "'"+lObjRsrcBndle.getString("CSS_1")+"','"+lObjRsrcBndle.getString("CSS_2")+"','"+lObjRsrcBndle.getString("CSS_3")+"'";
				lStrPlanType="CSS";
			}
			
		
			if(lStrPlanType.equalsIgnoreCase("Total") || lStrPlanType.equalsIgnoreCase("All"))
			{
				
				lsb.append(" select locdept.loc_code DEPT_ID, ");
				lsb.append(" (select nvl(sum(expdtls.gross_amnt - expdtls.recovery_amt) / 10000000,0) ");
				lsb.append(" from rpt_expenditure_dtls expdtls where ");
				
				if(lHashPara.get("MjrEndHd") != null)
				{
					lsb.append(" expdtls.mjr_hd >="+ Integer.parseInt(lHashPara.get("MjrStartHd").toString())+" and expdtls.mjr_hd <= "+Integer.parseInt(lHashPara.get("MjrEndHd").toString())+" and ");
				}
				else if(lHashPara.get("MjrStartHd") != null && lHashPara.get("MjrEndHd") == null)
				{
					lsb.append(" expdtls.mjr_hd ="+Integer.parseInt(lHashPara.get("MjrStartHd").toString())+ " and ");
				}
				lsb.append(" expdtls.bud_type_code in ("+bud_Plan_values+") and expdtls.dept_code =locdept.loc_code  and ");
				lsb.append(" expdtls.exp_dt >= TO_DATE('"+ lHashPara.get("FDate")+"', 'dd/mm/yyyy') and ");
				lsb.append(" expdtls.exp_dt <= TO_DATE('"+ lHashPara.get("EndDate")+"', 'dd/mm/yyyy') )PL_Amt, ");
				lsb.append(" (select sum(expdtls.gross_amnt - expdtls.recovery_amt) / 10000000 ");
				lsb.append(" from rpt_expenditure_dtls expdtls where ");
				if(lHashPara.get("MjrEndHd") != null)
				{
					lsb.append(" expdtls.mjr_hd >="+ Integer.parseInt(lHashPara.get("MjrStartHd").toString())+" and expdtls.mjr_hd <= "+Integer.parseInt(lHashPara.get("MjrEndHd").toString())+" and ");
				}
				else if(lHashPara.get("MjrStartHd") != null && lHashPara.get("MjrEndHd") == null)
				{
					lsb.append(" expdtls.mjr_hd ="+Integer.parseInt(lHashPara.get("MjrStartHd").toString())+ " and ");
				}
				lsb.append(" expdtls.bud_type_code in("+bud_NonPlan_values+") and expdtls.dept_code =locdept.loc_code  and ");
				lsb.append(" expdtls.exp_dt >= TO_DATE('"+ lHashPara.get("FDate")+"', 'dd/mm/yyyy') and ");
				lsb.append(" expdtls.exp_dt <= TO_DATE('"+ lHashPara.get("EndDate")+"', 'dd/mm/yyyy'))NP_Amt, ");
				lsb.append(" (select sum(expdtls.gross_amnt - expdtls.recovery_amt) / 10000000 ");
				lsb.append(" from rpt_expenditure_dtls expdtls where ");
				if(lHashPara.get("MjrEndHd") != null)
				{
					lsb.append(" expdtls.mjr_hd >="+ Integer.parseInt(lHashPara.get("MjrStartHd").toString())+" and expdtls.mjr_hd <= "+Integer.parseInt(lHashPara.get("MjrEndHd").toString())+" and ");
				}
				else if(lHashPara.get("MjrStartHd") != null && lHashPara.get("MjrEndHd") == null)
				{
					lsb.append(" expdtls.mjr_hd ="+Integer.parseInt(lHashPara.get("MjrStartHd").toString())+ " and ");
				}
				lsb.append(" expdtls.bud_type_code in ("+bud_CSS_values+") and expdtls.dept_code = locdept.loc_code and ");
				lsb.append(" expdtls.exp_dt >= TO_DATE('"+ lHashPara.get("FDate")+"', 'dd/mm/yyyy') and ");
				lsb.append(" expdtls.exp_dt <= TO_DATE('"+ lHashPara.get("EndDate")+"', 'dd/mm/yyyy') )CSS_Amt ");
				
				lsb.append(" from rlt_location_department locdept ");
			}
				
			else
			{
				lsb.append(" select locdept.loc_code DEPT_ID, ");
				lsb.append(" (select nvl(sum(expdtls.gross_amnt - expdtls.recovery_amt) / 10000000,0) ");
				lsb.append(" from rpt_expenditure_dtls expdtls where ");
				if(lHashPara.get("MjrEndHd") != null)
				{
					lsb.append(" expdtls.mjr_hd >="+ Integer.parseInt(lHashPara.get("MjrStartHd").toString())+" and expdtls.mjr_hd <= "+Integer.parseInt(lHashPara.get("MjrEndHd").toString())+" and ");
				}
				else if(lHashPara.get("MjrStartHd") != null && lHashPara.get("MjrEndHd") == null)
				{
					lsb.append(" expdtls.mjr_hd ="+Integer.parseInt(lHashPara.get("MjrStartHd").toString())+ " and ");
				}
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					lsb.append(" expdtls.bud_type_code in ("+bud_Plan_values+") ");
				}
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					lsb.append(" expdtls.bud_type_code in ("+bud_NonPlan_values+") ");
				}
				else if(lStrPlanType.equalsIgnoreCase("CSS"))
				{
					lsb.append(" expdtls.bud_type_code in ("+bud_CSS_values+") ");
				}
				lsb.append(" and expdtls.dept_code =locdept.loc_code and ");
				lsb.append(" expdtls.exp_dt >= TO_DATE('"+ lHashPara.get("FDate")+"', 'dd/mm/yyyy') and ");
				lsb.append(" expdtls.exp_dt <= TO_DATE('"+ lHashPara.get("EndDate")+"', 'dd/mm/yyyy') )"+ lStrPlanType+ " ");
				lsb.append(" from rlt_location_department locdept ");
			}
		
			glogger.info("Query to getExpForDept -- "+lsb.toString());
		
		try
		 {
			glogger.info("Inside try for Expenditure");
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lsb.toString());
			
			if(lStrPlanType.equalsIgnoreCase("Total"))
			{
				while(rs.next())
				{
					DepRevExp_Id=rs.getString("DEPT_ID")+"_"+lStrPlanType;
					glogger.info("--------Dept Id for Expenditure --------"+DepRevExp_Id);
					
					ldbPlanAmt=rs.getDouble("PL_Amt");
					ldbNonPlanAmt=rs.getDouble("NP_Amt");
					ldbCSSAmt = rs.getDouble("CSS_Amt");
					lDeptExpAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCSSAmt);
					glogger.info("--------Total for Expenditure --------"+lDeptExpAmt);
					lHashData.put(DepRevExp_Id, lDeptExpAmt);
				}
			}
			else if(lStrPlanType.equalsIgnoreCase("All"))
			{
				while(rs.next())
				{	
					ldbPlanAmt=rs.getDouble("PL_Amt");
					glogger.info("Plan Amt changed:"+ldbPlanAmt);
					ldbNonPlanAmt=rs.getDouble("NP_Amt");
					ldbCSSAmt = rs.getDouble("CSS_Amt");
					glogger.info("CSS Amt:"+ldbCSSAmt);
					lDeptExpAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCSSAmt);
					glogger.info("--------Total for Expenditure --------"+lDeptExpAmt);
					
					lHashData.put(rs.getString("DEPT_ID")+"_PL", ldbPlanAmt);
					lHashData.put(rs.getString("DEPT_ID")+"_NP", ldbNonPlanAmt);
					lHashData.put(rs.getString("DEPT_ID")+"_CSS", ldbCSSAmt);
					lHashData.put(rs.getString("DEPT_ID")+"_Total", lDeptExpAmt);
				}
			}
			else
			{
				while(rs.next())
				{
					DepRevExp_Id=rs.getString("DEPT_ID")+"_"+lStrPlanType;
					glogger.info("--------Dept Id for Expenditure --------"+DepRevExp_Id);
					lDeptExpAmt=rs.getDouble(lStrPlanType);
					glogger.info("--------Total for Expenditure--------"+lDeptExpAmt);
					lHashData.put(DepRevExp_Id, lDeptExpAmt);
				}
			}		
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
		 }
		 finally
		 {
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		 }
	}
	else
	{
		glogger.info("Exp Type not Found for Expenditure");
	}
		glogger.info("Before leaving getExpForDept");
	 return lHashData;
	}

	public ArrayList getMjrRangeForFundName(String strFundName)
	{
		StringBuffer lSb=new StringBuffer();
		Statement lStmt=null;
		Connection lCon=null;
		ResultSet rs=null;
		String lStrMjrHdRange="";
		String[] lArrMjHdRange=null;
		ArrayList lArrRangeLst=null;
		
		lSb.append("select major_hd  from config_recptexp_fund_mjrhd c"+
		" where c.fund_name='"+ strFundName + "'"); 
		
		glogger.info("Query for getStrFundMjrRange() "+ lSb.toString());
		try
		{
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			if(rs.next())
			{
				lArrRangeLst = new ArrayList();
				lStrMjrHdRange=rs.getString("major_hd");
				if(lStrMjrHdRange.contains("-"))
				{
					lArrMjHdRange = lStrMjrHdRange.split("-");
					if(lArrMjHdRange!=null)
					{
						lArrRangeLst.add(lArrMjHdRange[0]);
						lArrRangeLst.add(lArrMjHdRange[1]);
					}
				}
				else
				{
					lArrRangeLst.add(lStrMjrHdRange);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getMjrRangeForFundName() "+e);
			}
		}
		glogger.info("Before returning from  getMjrRangeForFundName-- "+lArrRangeLst);
		return lArrRangeLst;
	}
	
	public ArrayList getAllBudgetTypes(String lStrLangId,String lStrLocId)
	{
		glogger.info("In getAllBudgetTypes");
		glogger.info("LangId in getAllBudgetTypes == ");
		glogger.info(lStrLangId);
		ArrayList lArrBudTypes = new ArrayList();
		
		Statement lStmt=null;
		Connection lCon=null;
		ResultSet rs=null;
		int lintLangId = Integer.parseInt(lObjRsrcBndle.getString(lStrLangId));
		
		StringBuffer lSb=new StringBuffer();
		glogger.info("String buffer is generated");
		lSb.append(" select mst.lookup_id id, mst.lookup_name name ");
		lSb.append(" from cmn_lookup_mst mst where mst.parent_lookup_id in "); 
		lSb.append(" (select m1.lookup_id from cmn_lookup_mst m1 where m1.lookup_short_name='"+lObjRsrcBndle.getString("BudType")+"' and m1.lang_id="+lintLangId+") ");
		 
		glogger.info("Query for getAllBudgetTypes() "+ lSb.toString());
		
		try
		{
			lCon=DBConnection.getConnection();
			glogger.info("connnection is generated");
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			glogger.info("Querry is executed");
			while(rs.next())
			{
				ComboValuesVO lcmnVO = new ComboValuesVO();
				lcmnVO.setId(rs.getObject("id").toString());
				lcmnVO.setDesc(rs.getString("name"));
				lArrBudTypes.add(lcmnVO);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getAllBudgetTypes() "+e);
			}
		}
		
		return lArrBudTypes;
	}

	public ArrayList getAllMjrHdsForDept(HashMap lHshPara)
	{
		glogger.info("inside getAllMjrHdsForDept");
		//HashMap lhshMjrHd = null;
		int lintFinYrId=0;
		ArrayList lArrMjrHd=null;
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer lSb=new StringBuffer();
		String lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		lintFinYrId = Integer.parseInt(lHshPara.get("FinYrId").toString());
		
		lSb.append(" SELECT T.BUDMJRHD_CODE, T.BUDMJRHD_DESC_LONG FROM SGVA_BUDMJRHD_MST T ");
		lSb.append(" WHERE T.BPN_CODE IN (SELECT B.BPNCODE FROM SGVA_BUDBPN_MAPPING B, ");
		lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC WHERE ");
		lSb.append(" LOC.LOC_CODE="+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND "); 
		lSb.append(" LOC.LANG_ID="+Integer.parseInt(lHshPara.get("LangId").toString())+") ");
		lSb.append(" WHERE B.DEPT_ID = LOC   AND B.LANG_ID = '"+lStrLangId+"') AND ");
		lSb.append(" T.LANG_ID = '"+lStrLangId+"' AND t.fin_yr_id="+lintFinYrId +" AND T.STATUS LIKE 'Active' ORDER BY T.BUDMJRHD_CODE ");
  			
		glogger.info("Query for getAllMjrHdsForDept() -- "+lSb.toString());
		try
		{
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			
			lArrMjrHd = new ArrayList();
			while(rs.next())
			{
				lArrMjrHd.add(rs.getString("BUDMJRHD_CODE"));
				lArrMjrHd.add(rs.getString("BUDMJRHD_DESC_LONG"));
			}
		}
		catch(Exception e)
		{
			glogger.error("Exception in getAllMjrHdsForDept");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getAllMjrHdsForDept() "+e);
			}
		}
		glogger.info("List of Majorheads are:"+lArrMjrHd);
		return lArrMjrHd;
	}
	
	
	public ArrayList getAllSchemesForDept(HashMap lHshPara)
	{
		glogger.info("inside getAllSchemesForDept");
		//HashMap lhshMjrHd = null;
		int lintFinYrId=0;
		ArrayList lArrMjrHd = null;
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer lSb=new StringBuffer();
		String lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		lintFinYrId = Integer.parseInt(lHshPara.get("FinYrId").toString());
		
		lSb.append(" SELECT (S.DEMAND_CODE||':'||S.BUDMJRHD_CODE||':'||S.BUDSUBMJRHD_CODE||':'|| ");
		lSb.append(" S.BUDMINHD_CODE||':'||S.BUDSUBHD_CODE) SCHEME_CODE, S.BUDSUBHD_DESC_LONG SBHDDESC ");
		lSb.append(" FROM SGVA_BUDSUBHD_MST S,(SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
		lSb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHshPara.get("DeptId").toString()));
		lSb.append(" AND LOC.LANG_ID = "+Integer.parseInt(lHshPara.get("LangId").toString())+") ");
		lSb.append(" WHERE S.BPN_CODE IN (SELECT B.BPNCODE FROM SGVA_BUDBPN_MAPPING B ");
		lSb.append(" WHERE B.DEPT_ID = LOC AND B.LANG_ID = '"+lStrLangId+"') AND ");
		lSb.append(" S.LOC_ID = 'LC1' AND S.LANG_ID = '"+lStrLangId+"' AND s.fin_yr_id="+lintFinYrId +" AND S.STATUS LIKE 'Active' ");
		lSb.append(" ORDER BY S.DEMAND_CODE, S.BUDMJRHD_CODE, S.BUDSUBMJRHD_CODE, S.BUDMINHD_CODE, S.BUDSUBHD_CODE");
  			
		glogger.info("Query for getAllSchemesForDept() -- "+lSb.toString());
		try
		{
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			
			lArrMjrHd = new ArrayList();
			while(rs.next())
			{
				lArrMjrHd.add(rs.getString("SCHEME_CODE"));
				lArrMjrHd.add(rs.getString("SBHDDESC"));
			}
		}
		catch(Exception e)
		{
			glogger.error("Exception in getAllSchemesForDept");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getAllSchemesForDept() "+e);
			}
		}
		return lArrMjrHd;
	}
	
	
	public HashMap getBudEstForMjrHd(HashMap lHshPara)
	{
		glogger.info("---------inside getBudEstForMjrHd-------------");
		HashMap<String,Double> lhshMjrHd = new HashMap<String,Double>();
		HashMap lhshCSSamt=new HashMap();
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer lSb=new StringBuffer();
		String lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		String lStrPlanType="";
		String lStrMjrHdRevExp_Id="";
		int lintLangId=0;
		int lintFinYrId = 0;
		int lintPlanType = 0;
		double ldbPlanAmt=0 ,ldbNonPlanAmt ,ldbCSSAmt, lDeptMjrHdEstAmt=0;
		
		
		if(lHshPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHshPara.get("FinYrId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lintLangId = Integer.parseInt(lHshPara.get("LangId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		}
		if(lHshPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHshPara.get("PlanType").toString());
			glogger.info("Budget Type for Budget == "+lintPlanType);
		
			if(lintPlanType==-1)
			{
				 lStrPlanType="Total";
			}
			else if(lintPlanType==0)
			{
				 lStrPlanType="All";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
				 lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				 lStrPlanType="NP";
			}	
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				 lStrPlanType="CSS";
			}	
			
			if(lStrPlanType.equalsIgnoreCase("Total")||lStrPlanType.equalsIgnoreCase("All"))
			{
				glogger.info("------------Inside All OR Total------------");
				glogger.info("------Inside Total OR All For MajorHeadWise Budget------------");
				lSb.append(" SELECT TAB.budmjrhd_Code,");
				lSb.append(" sum(decode(plan_nonplan, 'PL', nxt_yr_be, 0)) / 10000 PL, ");
				lSb.append(" sum(decode(plan_nonplan, 'NP', nxt_yr_be, 0)) / 10000 NP ");
				lSb.append(" from (select f.nxt_yr_be, ");
				lSb.append(" f.budmjrhd_code, ");
				lSb.append(" f.plan_nonplan, ");
				lSb.append(" f.buddemand_code, ");
				lSb.append(" f.budbpn_code ");
				lSb.append(" from  Sgva_Expest_Finalamt F,sgva_budbpn_mapping bpn, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHshPara.get("DeptId").toString()));
				lSb.append(" AND LOC.LANG_ID = "+lintLangId+")");
				lSb.append(" WHERE F.level_Id = 4 AND F.fin_Yr_Id ="+lintFinYrId+" AND F.form_Type <> 'RE' AND ");
				lSb.append(" (F.rec_Flag NOT IN ('R', 'C', 'M') OR F.rec_Flag IS NULL) AND ");
				lSb.append(" F.gia_Flg = 'N' AND F.dept_Id = LOC and bpn.fin_yr_id="+lintFinYrId+" and bpn.dept_id=LOC ");
				lSb.append(" and f.budbpn_code=bpn.bpncode  and bpn.lang_id='"+lStrLangId+"' and bpn.status='Active')TAB ");
				lSb.append(" GROUP BY TAB.budmjrhd_Code,TAB.BUDBPN_CODE ");
				lSb.append(" ORDER BY TAB.budmjrhd_Code ");
			
			}
			else if(lStrPlanType.equalsIgnoreCase("PL") || lStrPlanType.equalsIgnoreCase("NP"))
			{
				glogger.info("Inside PL||NP for majorhead");
				
				lSb.append(" SELECT TAB.budmjrhd_Code, sum(decode(plan_nonplan, '"+lStrPlanType+"', nxt_yr_be, 0))/10000 "+lStrPlanType+" ");
				lSb.append(" from (select f.nxt_yr_be,f.budmjrhd_code,f.plan_nonplan,f.buddemand_code from "); 
				lSb.append(" Sgva_Expest_Finalamt F, Sgva_Budbpn_Mapping bpn,");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ="+Integer.parseInt(lHshPara.get("DeptId").toString()));
				lSb.append(	" AND LOC.LANG_ID ="+lintLangId+") ");
				lSb.append(" WHERE F.level_Id = 4 AND F.fin_Yr_Id ="+lintFinYrId+" AND ");
				lSb.append(" F.form_Type <> 'RE' AND (F.rec_Flag NOT IN ('R', 'C', 'M') OR F.rec_Flag IS NULL) AND ");
				lSb.append(" F.gia_Flg = 'N' AND F.dept_Id = LOC and bpn.fin_yr_id="+lintFinYrId+" and bpn.dept_id=LOC ");
				lSb.append(" and f.budbpn_code=bpn.bpncode  and bpn.lang_id='"+lStrLangId+"' and bpn.status='Active')TAB ");
				lSb.append(" GROUP BY budmjrhd_Code "); 
				lSb.append(" ORDER BY TAB.budmjrhd_Code ");
			}
			else if(lStrPlanType.equalsIgnoreCase("CSS"))
			{
				glogger.info("----------Inside CSS for getBudEstForMjrHd--------");
				lSb.append(" SELECT F.Budmjrhd_Code,");
				lSb.append(" SUM(DECODE(PLAN_NONPLAN, 'NP', NXT_YR_BE, 0)) / 10000 CSS ");
				lSb.append(" FROM SGVA_EXPEST_FINALAMT F, ");
				lSb.append(" SGVA_BUDSUBHD_MST SUB, ");
				lSb.append(" SGVA_BUDBPN_MAPPING BPN, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ="+Integer.parseInt(lHshPara.get("DeptId").toString()+" AND LOC.LANG_ID ="+lintLangId+") "));
				lSb.append(" WHERE SUB.CSS = 'Y' AND F.BUDDEMAND_CODE = SUB.DEMAND_CODE AND ");
				lSb.append(" F.BUDMJRHD_CODE = SUB.BUDMJRHD_CODE AND ");
				lSb.append(" F.BUDSUBMJRHD_CODE = SUB.BUDSUBMJRHD_CODE AND ");
				lSb.append(" F.BUDMINHD_CODE = SUB.BUDMINHD_CODE AND ");
				lSb.append(" F.BUDSUBHD_CODE = SUB.BUDSUBHD_CODE AND F.BUDBPN_CODE = SUB.BPN_CODE AND ");
				lSb.append(" SUB.STATUS = 'Active' and F.DEPT_ID = LOC AND F.LEVEL_ID = 4 AND ");
				lSb.append(" F.FIN_YR_ID = "+lintFinYrId+" AND F.FORM_TYPE <> 'RE' AND ");
				lSb.append(" (F.REC_FLAG NOT IN ('R', 'C', 'M') OR F.REC_FLAG IS NULL) AND ");
				lSb.append(" F.GIA_FLG = 'N' AND F.LANG_ID = '"+lStrLangId+"' AND SUB.LANG_ID = '"+lStrLangId+"' AND ");
				lSb.append(" SUB.FIN_YR_ID ="+lintFinYrId+" AND BPN.FIN_YR_ID = "+lintFinYrId+" AND BPN.DEPT_ID = LOC AND ");
				lSb.append(" F.BUDBPN_CODE = BPN.BPNCODE AND BPN.LANG_ID = '"+lStrLangId+"' and ");
				lSb.append(" bpn.fin_yr_id =sub.fin_yr_id and bpn.fin_yr_id="+lintFinYrId+" and bpn.status = 'Active' ");
				lSb.append(" GROUP BY F.Budmjrhd_Code ");
				lSb.append(" ORDER BY F.Budmjrhd_Code ");
			
			}
			
		glogger.info("Query for getBudEstForMjrHd() -- "+lSb.toString());
		try
		{
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			
			
			if(lStrPlanType.equalsIgnoreCase("Total"))
			{
				while(rs.next())
				{
					ldbNonPlanAmt=0;
					ldbCSSAmt=0;
					lStrMjrHdRevExp_Id=rs.getString("budmjrhd_Code")+"_"+lStrPlanType;
					glogger.info("Majorheads  in Total Are:"+lStrMjrHdRevExp_Id);
					glogger.info("--------MjrHd for Budget --------"+lStrMjrHdRevExp_Id);
					
					ldbPlanAmt=rs.getDouble("PL");
					lhshCSSamt=getCSSAmount(rs.getString("budmjrhd_Code"),Integer.parseInt(lHshPara.get("DeptId").toString()),lHshPara);
					
					glogger.info("------------>Inside Else-------1");
					String temp=rs.getString("budmjrhd_Code");
					if(!temp.equals(""))
					{
						if(lhshCSSamt.get(temp)!=null)
						{
							glogger.info("Actual NP Amount is::"+rs.getDouble("NP"));
							glogger.info("CSS Amount::"+Double.parseDouble(lhshCSSamt.get(temp).toString()));
							ldbNonPlanAmt=rs.getDouble("NP")-Double.parseDouble(lhshCSSamt.get(temp).toString());
							glogger.info("Subtracted Amount-------"+ldbNonPlanAmt);
						}
						else
						{
							ldbNonPlanAmt=rs.getDouble("NP")-0;
						}
						
					}
					
				
					glogger.info("NonPlan Amount in Total:"+ldbNonPlanAmt);
					
					String temp1=rs.getString("budmjrhd_Code");
					if(!temp.equals(""))
					{
						if(lhshCSSamt.get(temp1)!=null)
						{
							ldbCSSAmt=Double.parseDouble(lhshCSSamt.get(rs.getString("budmjrhd_Code")).toString());
							glogger.info("-------CSS is::-"+ldbCSSAmt);
						}
						else
						{
							ldbCSSAmt=0;
						}
						
					}
					glogger.info("PlanAmount is::"+ldbPlanAmt+"NonPlanAmount is ::"+ldbNonPlanAmt+"CSS Amount is::"+ldbCSSAmt);
					lDeptMjrHdEstAmt=(ldbPlanAmt+ldbNonPlanAmt);
					glogger.info("--------Total for Budget --------"+lDeptMjrHdEstAmt);
					lhshMjrHd.put(lStrMjrHdRevExp_Id, lDeptMjrHdEstAmt);
				}
			}
			else if(lStrPlanType.equalsIgnoreCase("All")) 
			{
				glogger.info("--------Inside all of getBudEstForMjrHd-------");
				while(rs.next())
				{
					ldbNonPlanAmt=0;
					ldbCSSAmt=0;
					
					lStrMjrHdRevExp_Id=rs.getString("budmjrhd_Code");
					glogger.info("----MajorHeads ARe-----"+lStrMjrHdRevExp_Id);
					ldbPlanAmt=rs.getDouble("PL");
					
					lhshCSSamt=getCSSAmount(rs.getString("budmjrhd_Code"),Integer.parseInt(lHshPara.get("DeptId").toString()),lHshPara);
					
					glogger.info("------------>Inside Else-------1");
					String temp=rs.getString("budmjrhd_Code");
					if(!temp.equals(""))
					{
						if(lhshCSSamt.get(temp)!=null)
						{
							glogger.info("Actual NP Amount is::"+rs.getDouble("NP"));
							glogger.info("CSS Amount::"+Double.parseDouble(lhshCSSamt.get(temp).toString()));
							ldbNonPlanAmt=rs.getDouble("NP")-Double.parseDouble(lhshCSSamt.get(temp).toString());
							glogger.info("Subtracted Amount-------"+ldbNonPlanAmt);
						}
						else
						{
							ldbNonPlanAmt=rs.getDouble("NP")-0;
						}
						
					}
					
				
					glogger.info("NonPlan Amount in All:"+ldbNonPlanAmt);
					
					String temp1=rs.getString("budmjrhd_Code");
					if(!temp.equals(""))
					{
						if(lhshCSSamt.get(temp1)!=null)
						{
							ldbCSSAmt=Double.parseDouble(lhshCSSamt.get(rs.getString("budmjrhd_Code")).toString());
							glogger.info("-------CSS is::-"+ldbCSSAmt);
						}
						else
						{
							ldbCSSAmt=0;
						}
						
					}
					
					glogger.info("CSS Amount in All:"+ldbCSSAmt);
					lDeptMjrHdEstAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCSSAmt);
					glogger.info("Total Amount-->"+lDeptMjrHdEstAmt);
					
					lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_PL", ldbPlanAmt);
					lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_NP", ldbNonPlanAmt);
					lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_CSS", ldbCSSAmt);
					lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_Total", lDeptMjrHdEstAmt);
				}
			}
			
			else if(lStrPlanType.equalsIgnoreCase("NP"))
			{
				glogger.info("-------Inside NonPlan Case------");
				while(rs.next())
				{
					ldbNonPlanAmt=0;
					lhshCSSamt=getCSSAmount(rs.getString("budmjrhd_Code"),Integer.parseInt(lHshPara.get("DeptId").toString()),lHshPara);
					
					String temp=rs.getString("budmjrhd_Code");
					if(!temp.equals(""))
					{
						if(lhshCSSamt.get(temp)!=null)
						{
							glogger.info("Actual NP Amount is::"+rs.getDouble("NP"));
							glogger.info("CSS Amount::"+Double.parseDouble(lhshCSSamt.get(temp).toString()));
							ldbNonPlanAmt=rs.getDouble("NP")-Double.parseDouble(lhshCSSamt.get(temp).toString());
							glogger.info("Subtracted Amount-------"+ldbNonPlanAmt);
							
							lStrMjrHdRevExp_Id=rs.getString("budmjrhd_Code")+"_"+lStrPlanType;
							lhshMjrHd.put(lStrMjrHdRevExp_Id,ldbNonPlanAmt);
						}
						else
						{
							ldbNonPlanAmt=rs.getDouble("NP")-0;
							
							lStrMjrHdRevExp_Id=rs.getString("budmjrhd_Code")+"_"+lStrPlanType;
							lhshMjrHd.put(lStrMjrHdRevExp_Id,ldbNonPlanAmt);
						}
						
					}
				}
				
			}
			
			else
			{
				while(rs.next())
				{
					lStrMjrHdRevExp_Id=rs.getString("budmjrhd_Code")+"_"+lStrPlanType;
					glogger.info("--------Dept Id for Budget--------"+lStrMjrHdRevExp_Id);
					lDeptMjrHdEstAmt=rs.getDouble(lStrPlanType);
					glogger.info("--------Total for Budget--------"+lDeptMjrHdEstAmt);
					lhshMjrHd.put(lStrMjrHdRevExp_Id, lDeptMjrHdEstAmt);
				}
			}
		}
		catch(Exception e)
		{
			glogger.error("Exception in getBudEstForMjrHd");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getBudEstForMjrHd() "+e);
			}
		}
		}
		return lhshMjrHd ;
	}
		
	public HashMap getGrantForMjrHd(HashMap lHshPara)
	{
		glogger.info("inside getGrantForMjrHd");
		HashMap lhshMjrHd = null;
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		String lStrMjhd="";
		String lStrPlanType = "";
		double ldbPlanAmt=0;
		double ldbNonPlanAmt=0;
		double ldbCssAmt=0;
		double ldbTotalGrant=0;
		int lintPlanType = 0;
		StringBuffer lSb=new StringBuffer();
		
		String lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		glogger.info("Lang Id : "+lStrLangId);
		
		if(lHshPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHshPara.get("PlanType").toString());
			glogger.info("Budget Type for Budget == "+lintPlanType);
		
			if(lintPlanType==-1)
			{
				 lStrPlanType="Total";
			}
			else if(lintPlanType==0)
			{
				lStrPlanType="All";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
				 lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				 lStrPlanType="NP";
			}	
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				 lStrPlanType="CSS";
			}	
			if(lStrPlanType.equalsIgnoreCase("Total")||lStrPlanType.equalsIgnoreCase("All"))
			{
				glogger.info("-------Inside Total--------");
				lSb.append(" select grnt.budmjrhd_code, ");
				lSb.append(" sum(grnt.plan_amount)*1000 PL, ");
				lSb.append(" sum(grnt.nonplan_amount)*1000 NP, ");
				lSb.append(" sum(grnt.css_amount)*1000 CSS ");
				lSb.append(" from Sgva_Budgrant_Mst grnt, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID loc ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ="+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND LOC.LANG_ID ="+Integer.parseInt(lHshPara.get("LangId").toString())+")");
				lSb.append(" where grnt.budbpn_code in ");
				lSb.append(" (select bpn.bpncode ");
				lSb.append(" from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id = loc and bpn.lang_id='"+lStrLangId+"') ");
				lSb.append(" and grnt.fin_yr_id="+Integer.parseInt(lHshPara.get("FinYrId").toString())+" ");
				lSb.append(" group by budmjrhd_code ");
				
				
			/*	lSb.append(" SELECT s.budmjrhd_code , ");
				lSb.append("(select NVL(SUM(S.GRANT_AMOUNT_STATE + S.GRANT_AMOUNT_CSS), 0) ");
				lSb.append(" FROM SGVA_GRANT_ORDER_DETAIL S, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID loc ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND LOC.LANG_ID = "+Integer.parseInt(lHshPara.get("LangId").toString())+") ");
				lSb.append(" WHERE S.FIN_YEAR_ID = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" AND S.PLAN_NONPLAN = 'PL' AND ");
				lSb.append(" s.demand_code in ");
				lSb.append(" (select bd.demand_code ");
				lSb.append(" from sgva_buddemand_mst bd ");
				lSb.append(" where bd.bpncode in ");
				lSb.append(" (select bpn.bpncode ");
				lSb.append(" from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id = loc and bpn.fin_yr_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" bpn.status = 'Active' and bpn.lang_id = '"+lStrLangId+"') and ");
				lSb.append(" bd.lang_id = '"+lStrLangId+"' and bd.fin_yr_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" bd.status = 'Active') AND ");
				lSb.append(" S.GRANT_RELEASE_STATUS = 'R' AND s.fin_year_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" s.file_creator_type = 'FD') PL, ");
				lSb.append(" (select NVL(SUM(S.GRANT_AMOUNT_STATE), 0) ");
				lSb.append(" FROM SGVA_GRANT_ORDER_DETAIL S, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID loc ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND LOC.LANG_ID = "+Integer.parseInt(lHshPara.get("LangId").toString())+") ");
				lSb.append(" WHERE S.FIN_YEAR_ID = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" AND S.PLAN_NONPLAN = 'NP' AND ");
				lSb.append(" s.demand_code in ");
				lSb.append(" (select bd.demand_code ");
				lSb.append(" from sgva_buddemand_mst bd ");
				lSb.append(" where bd.bpncode in ");
				lSb.append(" (select bpn.bpncode ");
				lSb.append(" from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id = loc and bpn.fin_yr_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" bpn.status = 'Active' and bpn.lang_id = '"+lStrLangId+"') and ");
				lSb.append(" bd.lang_id = '"+lStrLangId+"' and bd.fin_yr_id ="+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" bd.status = 'Active') AND ");
				lSb.append(" S.GRANT_RELEASE_STATUS = 'R' AND s.fin_year_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" s.file_creator_type = 'FD') NP,");
				lSb.append(" (select NVL(SUM(S.Grant_Amount_Css), 0)");
				lSb.append(" FROM SGVA_GRANT_ORDER_DETAIL S,");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID loc ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND LOC.LANG_ID = "+Integer.parseInt(lHshPara.get("LangId").toString())+") ");
				lSb.append(" WHERE S.FIN_YEAR_ID = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" AND S.PLAN_NONPLAN = 'NP' AND ");
				lSb.append(" s.demand_code in ");
				lSb.append(" (select bd.demand_code ");
				lSb.append(" from sgva_buddemand_mst bd ");
				lSb.append(" where bd.bpncode in ");
				lSb.append(" (select bpn.bpncode ");
				lSb.append(" from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id = loc and bpn.fin_yr_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" bpn.status = 'Active' and bpn.lang_id = '"+lStrLangId+"') and ");
				lSb.append(" bd.lang_id = '"+lStrLangId+"' and bd.fin_yr_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" bd.status = 'Active') AND ");
				lSb.append(" S.GRANT_RELEASE_STATUS = 'R' AND s.fin_year_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+") CSS ");
				lSb.append(" from SGVA_GRANT_ORDER_DETAIL S ");*/
			}
			else
			{
				glogger.info("inside  else for Grant");
				
				 
				lSb.append(" select grnt.budmjrhd_code, ");
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					lSb.append(" (sum(grnt.plan_amount) * 1000) "+lStrPlanType);
				}
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					lSb.append(" (sum(grnt.nonplan_amount) * 1000) "+lStrPlanType);
				}
				else if(lStrPlanType.equalsIgnoreCase("CSS"))
				{
					lSb.append(" (sum(grnt.css_amount) * 1000) "+lStrPlanType);
				}
				lSb.append(" from Sgva_Budgrant_Mst grnt, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID loc ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ="+Integer.parseInt(lHshPara.get("DeptId").toString())+"AND LOC.LANG_ID ="+Integer.parseInt(lHshPara.get("LangId").toString())+")");
				lSb.append(" where grnt.budbpn_code in ");
				lSb.append(" (select bpn.bpncode ");
				lSb.append(" from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id = loc and bpn.lang_id='"+lStrLangId+"') ");
				lSb.append(" and grnt.fin_yr_id="+Integer.parseInt(lHshPara.get("FinYrId").toString())+" ");
				lSb.append(" group by budmjrhd_code "); 
				
				
			/*	lSb.append(" SELECT s.budmjrhd_code mjrhd, ");
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					lSb.append(" NVL(SUM(S.GRANT_AMOUNT_STATE + S.GRANT_AMOUNT_CSS), 0)"+lStrPlanType+" ");
				}
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					lSb.append(" NVL(SUM(S.GRANT_AMOUNT_STATE), 0)"+lStrPlanType+"");
				}
				else if(lStrPlanType.equalsIgnoreCase("CSS"))
				{
					lSb.append(" NVL(SUM(S.Grant_Amount_Css), 0)"+lStrPlanType+" ");
				}
				
				lSb.append(" FROM SGVA_GRANT_ORDER_DETAIL S, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID loc ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND LOC.LANG_ID = "+Integer.parseInt(lHshPara.get("LangId").toString())+")) ");
				lSb.append(" WHERE S.FIN_YEAR_ID ="+Integer.parseInt(lHshPara.get("FinYrId").toString())+" AND ");
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					lSb.append(" S.PLAN_NONPLAN = 'PL' AND ");
				}
				else if(lStrPlanType.equalsIgnoreCase("NP")||lStrPlanType.equalsIgnoreCase("CSS"))
				{
					lSb.append(" S.PLAN_NONPLAN = 'NP' AND ");
				}
				lSb.append(" s.demand_code in ");
				lSb.append(" (select bd.demand_code ");
				lSb.append(" from sgva_buddemand_mst bd ");
				lSb.append(" where bd.bpncode in ");
				lSb.append(" (select bpn.bpncode ");
				lSb.append(" from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id = loc and ");
				lSb.append(" bpn.fin_yr_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and bpn.status = 'Active' and ");
				lSb.append(" bpn.lang_id = '"+lStrLangId+"') and bd.lang_id = '"+lStrLangId+"' and ");
				lSb.append(" bd.fin_yr_id = "+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and bd.status = 'Active') and ");
				lSb.append(" S.GRANT_RELEASE_STATUS = 'R' AND s.fin_year_id ="+Integer.parseInt(lHshPara.get("FinYrId").toString())+" and ");
				lSb.append(" s.file_creator_type = 'FD' ");
				lSb.append(" group by  s.budmjrhd_code ");*/
				
			}
		  			
			glogger.info("Query for getGrantForMjrHd() -- "+lSb.toString());
			try
			{
				lCon=DBConnection.getConnection();
				lStmt=lCon.createStatement();
				rs=lStmt.executeQuery(lSb.toString());
				
				lhshMjrHd = new HashMap();
				
				if(lStrPlanType.equalsIgnoreCase("Total"))
				{
					while(rs.next())
					{
						lStrMjhd=rs.getString("budmjrhd_Code")+"_"+lStrPlanType;
						glogger.info("majorhd Id is--"+lStrMjhd);
						ldbPlanAmt= rs.getDouble("PL");
						ldbNonPlanAmt = rs.getDouble("NP");
						ldbCssAmt = rs.getDouble("CSS");
						ldbTotalGrant = ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt;
						glogger.info("Grant Total in MajorHead"+ldbTotalGrant);
						lhshMjrHd.put(lStrMjhd,ldbTotalGrant);
					}
				}
				else if(lStrPlanType.equalsIgnoreCase("All"))
				{
					glogger.info("--------Inside all of getGrantForMjrHd-------");
					while(rs.next())
					{
						ldbPlanAmt= rs.getDouble("PL");
						glogger.info("Grant Plan Amount:"+ldbPlanAmt);
						ldbNonPlanAmt = rs.getDouble("NP");
						glogger.info("Grant NonPlan Amount:"+ldbNonPlanAmt);
						ldbCssAmt = rs.getDouble("CSS");
						glogger.info("Grant CSS Amount:"+ldbCssAmt);
						
						ldbTotalGrant = ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt;
						glogger.info("MajorHd Code for Grant:"+rs.getString("budmjrhd_Code").toString());
						
						lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_PL", ldbPlanAmt);
						lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_NP", ldbNonPlanAmt);
						lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_CSS", ldbCssAmt);
						lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_Total", ldbTotalGrant);
					}
				}
				
				else
				{
					while(rs.next())
					{
						ldbTotalGrant=rs.getDouble(lStrPlanType);
						glogger.info("--------Total for grant--------"+ldbTotalGrant);
						lhshMjrHd.put(rs.getString("budmjrhd_Code")+"_"+lStrPlanType, ldbTotalGrant);
					}
				}
				
			}
			catch(Exception e)
			{
				glogger.error("Exception in getGrantForMjrHd");
				e.printStackTrace();
			}
			finally
			{
				try
				{
					rs.close();
					lStmt.close();
					lCon.close();
				}
				catch(Exception e)
				{
					glogger.error("Exception in closing connection in getGrantForMjrHd() "+e);
				}
			}
		}
		return lhshMjrHd;
	}
	
	
	public HashMap getBudEstForScheme(HashMap lHshPara)
	{
		glogger.info("inside getBudEstForScheme");
		HashMap<String,Double> lhshMjrHd = new HashMap<String,Double>();
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer lSb=new StringBuffer();
		String lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		String lStrPlanType="";
		String lStrMjrHdRevExp_Id="";
		int lintLangId=0;
		int lintFinYrId = 0;
		int lintPlanType = 0;
		double ldbPlanAmt=0 ,ldbNonPlanAmt =0 , lDeptMjrHdEstAmt=0,ldbCSSAmt=0;
		int lintPLId=0,lintNPId=0;
		
		if(lHshPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHshPara.get("FinYrId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lintLangId = Integer.parseInt(lHshPara.get("LangId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		}
		if(lHshPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHshPara.get("PlanType").toString());
			glogger.info("Budget Type for Scheme == "+lintPlanType);
		
			if(lintPlanType==-1)
			{
				 lStrPlanType="Total";
			}
			else if(lintPlanType==0)
			{
				lStrPlanType="All";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
				 lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				 lStrPlanType="NP";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				 lStrPlanType="CSS";
			}
			
			if(lStrPlanType.equalsIgnoreCase("Total")||lStrPlanType.equalsIgnoreCase("All"))
			{
				lSb.append(" select (SCH.buddemand_Code || ':' || SCH.budmjrhd_Code || ':' || ");
				lSb.append(" SCH.budsubmjrhd_Code || ':' || SCH.budminhd_Code || ':' || ");
				lSb.append(" SCH.budsubhd_Code) scheme, ");
				lSb.append(" sum(decode(SCH.plan_nonplan, 'PL', nxt_yr_be, 0)) / 10000 PL, ");
				lSb.append(" sum(decode(SCH.plan_nonplan, 'NP', nxt_yr_be, 0)) / 10000 NP, ");
				lSb.append(" NVL((SELECT (SUM(CSS.CSS_AMOUNT) / 10000) ");
				lSb.append(" FROM SGVA_BUDCSS_MST CSS ");
				lSb.append(" WHERE CSS.FIN_YR_ID ="+lintFinYrId+" AND ");
				lSb.append(" CSS.BUDMJRHD_CODE = SCH.BUDMJRHD_CODE AND ");
				lSb.append(" CSS.BUDBPN_CODE = SCH.BUDBPN_CODE and ");
				lSb.append(" CSS.LANG_ID = '"+lStrLangId+"' and css.budsubmjrhd_code=SCH.budsubmjrhd_Code and css.budminhd_code= ");
				lSb.append(" SCH.budminhd_Code and css.budsubhd_code=SCH.budsubhd_Code),0) CSS ");
				lSb.append(" from (select F.buddemand_Code, ");
				lSb.append(" F.budmjrhd_Code, ");
				lSb.append(" F.budsubmjrhd_Code, ");
				lSb.append(" F.budminhd_Code, ");
				lSb.append(" F.budsubhd_Code, ");
				lSb.append(" F.nxt_yr_be, ");
				lSb.append(" F.plan_nonplan, ");
				lSb.append(" F.BUDBPN_CODE ");
				lSb.append(" from Sgva_Expest_Finalamt F,sgva_budbpn_mapping bpn, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND LOC.LANG_ID = "+lintLangId+") ");
				lSb.append(" WHERE F.level_Id = 4 AND F.gia_Flg = 'N' AND ");
				lSb.append(" F.form_Type <> 'RE' AND ");
				lSb.append(" (F.rec_Flag NOT IN ('R', 'C', 'M') OR F.rec_Flag IS NULL) AND ");
				lSb.append(" F.dept_Id = LOC and F.BUDBPN_CODE=bpn.bpncode "); 
				lSb.append(" and bpn.lang_id='"+lStrLangId+"' and f.fin_yr_id=bpn.fin_yr_id and bpn.fin_yr_id="+lintFinYrId+") SCH ");
				lSb.append(" GROUP BY SCH.buddemand_Code, ");
				lSb.append(" SCH.budmjrhd_Code, ");
				lSb.append(" SCH.budsubmjrhd_Code, ");
				lSb.append(" SCH.budminhd_Code, ");
				lSb.append(" SCH.budsubhd_Code, ");
				lSb.append(" SCH.BUDBPN_CODE ");
				lSb.append(" ORDER BY SCH.buddemand_Code, ");
				lSb.append(" SCH.budmjrhd_Code, ");
				lSb.append(" SCH.budsubhd_code, ");
				lSb.append(" SCH.budsubmjrhd_Code, ");
				lSb.append(" SCH.budminhd_Code ");
			}
			else if(lStrPlanType.equalsIgnoreCase("PL") || lStrPlanType.equalsIgnoreCase("NP"))
			{
				lSb.append(" select (SCH.buddemand_Code || ':' || SCH.budmjrhd_Code || ':' || ");
				lSb.append(" SCH.budsubmjrhd_Code || ':' || SCH.budminhd_Code || ':' || ");
				lSb.append(" SCH.budsubhd_Code) scheme, ");
				lSb.append(" sum(decode(SCH.plan_nonplan, '"+lStrPlanType+"', nxt_yr_be, 0)) / 10000 "+lStrPlanType+" ");
				lSb.append("from (select F.buddemand_Code,F.budmjrhd_Code,F.budsubmjrhd_Code, ");
				lSb.append(" F.budminhd_Code,F.budsubhd_Code,F.nxt_yr_be,F.plan_nonplan ");
				lSb.append(" from Sgva_Expest_Finalamt F, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ="+Integer.parseInt(lHshPara.get("DeptId").toString())+"AND LOC.LANG_ID ="+lintLangId+") ");
				lSb.append(" WHERE F.fin_Yr_Id ="+lintFinYrId+" AND F.level_Id = 4 AND F.gia_Flg = 'N' AND ");
				lSb.append(" F.form_Type <> 'RE' AND(F.rec_Flag NOT IN ('R', 'C', 'M') OR F.rec_Flag IS NULL) AND ");
				lSb.append(" F.dept_Id = LOC) SCH GROUP BY SCH.buddemand_Code,SCH.budmjrhd_Code, ");
				lSb.append(" SCH.budsubmjrhd_Code,SCH.budminhd_Code,SCH.budsubhd_Code ");
				lSb.append(" ORDER BY SCH.buddemand_Code,SCH.budmjrhd_Code,SCH.budsubhd_code, "); 
				lSb.append(" SCH.budsubmjrhd_Code,SCH.budminhd_Code "); 
			}
			else if(lStrPlanType.equalsIgnoreCase("CSS"))
			{
				glogger.info("Inside CSS--");
				lSb.append(" select (SCH.buddemand_Code || ':' || SCH.budmjrhd_Code || ':' || ");
				lSb.append(" SCH.budsubmjrhd_Code || ':' || SCH.budminhd_Code || ':' || ");
				lSb.append(" SCH.budsubhd_Code) scheme, ");
				lSb.append(" NVL((SELECT (SUM(CSS.CSS_AMOUNT) / 10000) ");
				lSb.append(" FROM SGVA_BUDCSS_MST CSS ");
				lSb.append(" WHERE CSS.FIN_YR_ID ="+lintFinYrId+" AND ");
				lSb.append(" CSS.BUDMJRHD_CODE = SCH.BUDMJRHD_CODE AND ");
				lSb.append(" CSS.BUDBPN_CODE = SCH.BUDBPN_CODE and ");
				lSb.append(" CSS.LANG_ID = '"+lStrLangId+"' and css.budsubmjrhd_code=SCH.budsubmjrhd_Code and css.budminhd_code= ");
				lSb.append(" SCH.budminhd_Code and css.budsubhd_code = SCH.budsubhd_Code),0) "+lStrPlanType+" ");
				lSb.append(" from (select F.buddemand_Code, ");
				lSb.append(" F.budmjrhd_Code, ");
				lSb.append(" F.budsubmjrhd_Code, ");
				lSb.append(" F.budminhd_Code, ");
				lSb.append(" F.budsubhd_Code, ");
				lSb.append(" F.nxt_yr_be, ");
				lSb.append(" F.plan_nonplan, ");
				lSb.append(" F.BUDBPN_CODE ");
				lSb.append(" from Sgva_Expest_Finalamt F,sgva_budbpn_mapping bpn, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC ");
				lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE = "+Integer.parseInt(lHshPara.get("DeptId").toString())+" ");
				lSb.append(" AND LOC.LANG_ID = "+lintLangId+") ");
				lSb.append(" WHERE  F.level_Id = 4 AND F.gia_Flg = 'N' AND ");
				lSb.append(" F.form_Type <> 'RE' AND ");
				lSb.append(" (F.rec_Flag NOT IN ('R', 'C', 'M') OR F.rec_Flag IS NULL) AND ");
				lSb.append(" F.dept_Id = LOC and F.BUDBPN_CODE=bpn.bpncode "); 
				lSb.append(" and bpn.lang_id='"+lStrLangId+"' and f.fin_yr_id=bpn.fin_yr_id and bpn.fin_yr_id="+lintFinYrId+") SCH ");
				lSb.append(" GROUP BY SCH.buddemand_Code, ");
				lSb.append(" SCH.budmjrhd_Code, ");
				lSb.append(" SCH.budsubmjrhd_Code, ");
				lSb.append(" SCH.budminhd_Code, ");
				lSb.append(" SCH.budsubhd_Code, ");
				lSb.append(" SCH.BUDBPN_CODE ");
				lSb.append(" ORDER BY SCH.buddemand_Code, ");
				lSb.append(" SCH.budmjrhd_Code, ");
				lSb.append(" SCH.budsubhd_code, ");
				lSb.append(" SCH.budsubmjrhd_Code, ");
				lSb.append(" SCH.budminhd_Code ");
			}
		glogger.info("Query for getBudEstForScheme() -- "+lSb.toString());
		try
		{
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			
			
			if(lStrPlanType.equalsIgnoreCase("Total"))
			{
				while(rs.next())
				{
					lStrMjrHdRevExp_Id=rs.getString("scheme")+"_"+lStrPlanType;
					glogger.info("--------MjrHd for Scheme --------"+lStrMjrHdRevExp_Id);
					
					ldbPlanAmt=rs.getDouble("PL");
					ldbNonPlanAmt=rs.getDouble("NP")-rs.getDouble("CSS");
					ldbCSSAmt=rs.getDouble("CSS");
					lDeptMjrHdEstAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCSSAmt);
					glogger.info("--------Total for Scheme --------"+lDeptMjrHdEstAmt);
					lhshMjrHd.put(lStrMjrHdRevExp_Id, lDeptMjrHdEstAmt);
				}
			}
			else if(lStrPlanType.equalsIgnoreCase("All"))
			{
				glogger.info("--------Inside all of getBudEstForScheme-------");
				while(rs.next())
				{
					glogger.info("----Inside While of BudgetScheme----");
					glogger.info("----Scheme Code for budget----"+rs.getString("scheme").toString());
					ldbPlanAmt=rs.getDouble("PL");
					ldbNonPlanAmt=rs.getDouble("NP")-rs.getDouble("CSS");
					glogger.info("NonPlanAmt in Scheme:"+ldbNonPlanAmt);
					ldbCSSAmt=rs.getDouble("CSS");
					lDeptMjrHdEstAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCSSAmt);
					glogger.info("Total for SchemeMjrHd"+lDeptMjrHdEstAmt);
					
					lhshMjrHd.put(rs.getString("scheme")+"_PL", ldbPlanAmt);
					lhshMjrHd.put(rs.getString("scheme")+"_NP", ldbNonPlanAmt);
					lhshMjrHd.put(rs.getString("scheme")+"_CSS", ldbCSSAmt);
					lhshMjrHd.put(rs.getString("scheme")+"_Total", lDeptMjrHdEstAmt);
				}
			}
			
			else
			{
				while(rs.next())
				{
					lStrMjrHdRevExp_Id=rs.getString("scheme")+"_"+lStrPlanType;
					glogger.info("--------Dept Id for scheme--------"+lStrMjrHdRevExp_Id);
					lDeptMjrHdEstAmt=rs.getDouble(lStrPlanType);
					glogger.info("--------Total for Budget--------"+lDeptMjrHdEstAmt);
					lhshMjrHd.put(lStrMjrHdRevExp_Id, lDeptMjrHdEstAmt);
				}
			}
		}
		catch(Exception e)
		{
			glogger.error("Exception in getBudEstForMjrHd");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getBudEstForScheme() "+e);
			}
		}
	}
		return lhshMjrHd ;
	}
	
	
	public HashMap getGrantForScheme(HashMap lHshPara)
	{
		glogger.info("inside get GrantForScheme");
		HashMap<String,Double> lhshMjrHd = new HashMap<String,Double>();
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer lSb=new StringBuffer();
		String lStrLangId ="";
		String lStrPlanType="";
		String lStrMjrHdSchemeExp_Id="";
		int lintLangId=0;
		int lintFinYrId = 0;
		int lintPlanType = 0;
		double ldbPlanAmt=0 ,ldbNonPlanAmt =0 , ldbCssAmt=0,lDeptSchemeEstAmt=0;
		
		
		if(lHshPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHshPara.get("FinYrId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lintLangId = Integer.parseInt(lHshPara.get("LangId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		}
		if(lHshPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHshPara.get("PlanType").toString());
			glogger.info("Budget Type for Scheme == "+lintPlanType);
		
			if(lintPlanType==-1)
			{
				 lStrPlanType="Total";
			}
			else if(lintPlanType==0)
			{
				lStrPlanType="All";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
				 lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				 lStrPlanType="NP";
			}	
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				 lStrPlanType="CSS";
			}	
			if(lStrPlanType.equalsIgnoreCase("Total")||lStrPlanType.equalsIgnoreCase("All"))
			{
				lSb.append(" select (grnt.buddemand_code || ':' || grnt.budmjrhd_code || ':' || ");
				lSb.append(" grnt.budsubmjrhd_code || ':' || grnt.budminhd_code || ':' || ");
				lSb.append(" grnt.budsubhd_code) scheme, "); 
				lSb.append(" sum(grnt.plan_amount) PL, ");
				lSb.append(" sum(grnt.nonplan_amount) NP, ");
				lSb.append(" sum(grnt.css_amount) CSS ");
				lSb.append(" from Sgva_Budgrant_Mst grnt, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ="+Integer.parseInt(lHshPara.get("DeptId").toString())+"AND LOC.LANG_ID = "+lintLangId+") where grnt.budbpn_code in ");
				lSb.append(" (select bpn.bpncode from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id = loc and bpn.lang_id = '"+lStrLangId+"') ");
				lSb.append(" group by grnt.buddemand_code, grnt.budmjrhd_code, grnt.budsubmjrhd_code, ");
				lSb.append(" grnt.budminhd_code, grnt.budsubhd_code order by grnt.buddemand_code, ");
				lSb.append(" grnt.budmjrhd_code,grnt.budsubmjrhd_code,grnt.budminhd_code, grnt.budsubhd_code "); 
			}
			else 
			{
				lSb.append(" select (grnt.buddemand_code || ':' || grnt.budmjrhd_code || ':' || ");
				lSb.append(" grnt.budsubmjrhd_code || ':' || grnt.budminhd_code || ':' || ");
				lSb.append(" grnt.budsubhd_code) scheme, "); 
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					lSb.append(" sum(grnt.plan_amount) PL ");
				}
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					lSb.append(" sum(grnt.nonplan_amount) NP ");
				}
				else if(lStrPlanType.equalsIgnoreCase("CSS"))
				{
					lSb.append(" sum(grnt.css_amount) CSS ");
				}
				lSb.append(" from Sgva_Budgrant_Mst grnt, ");
				lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ="+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND LOC.LANG_ID = "+lintLangId+") where grnt.budbpn_code in ");
				lSb.append(" (select bpn.bpncode from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id = loc and bpn.lang_id = '"+lStrLangId+"') ");
				lSb.append(" group by grnt.buddemand_code, grnt.budmjrhd_code, grnt.budsubmjrhd_code, ");
				lSb.append(" grnt.budminhd_code, grnt.budsubhd_code order by grnt.buddemand_code, ");
				lSb.append(" grnt.budmjrhd_code,grnt.budsubmjrhd_code,grnt.budminhd_code, grnt.budsubhd_code ");
			}
			
		glogger.info("Query for getGrantForScheme() -- "+lSb.toString());
		try
		{
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			
			
			if(lStrPlanType.equalsIgnoreCase("Total"))
			{
				while(rs.next())
				{
					lStrMjrHdSchemeExp_Id=rs.getString("scheme")+"_"+lStrPlanType;
					glogger.info("--------MjrHd for Scheme --------"+lStrMjrHdSchemeExp_Id);
					
					ldbPlanAmt=rs.getDouble("PL");
					ldbNonPlanAmt=rs.getDouble("NP");
					ldbCssAmt = rs.getDouble("CSS");
					lDeptSchemeEstAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt);
					glogger.info("--------Total for Scheme --------"+lDeptSchemeEstAmt);
					lhshMjrHd.put(lStrMjrHdSchemeExp_Id, lDeptSchemeEstAmt);
				}
			}
			
			else if(lStrPlanType.equalsIgnoreCase("All"))
			{
				glogger.info("--------Inside all of getGrantForScheme-------");
				while(rs.next())
				{
					glogger.info("Inside While for getGrantForScheme");
					glogger.info("Scheme Code for Grant"+rs.getString("scheme").toString());
					ldbPlanAmt= rs.getDouble("PL");
					ldbNonPlanAmt = rs.getDouble("NP");
					ldbCssAmt = rs.getDouble("CSS");
					lDeptSchemeEstAmt = ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt;
					
					lhshMjrHd.put(rs.getString("scheme")+"_PL", ldbPlanAmt);
					lhshMjrHd.put(rs.getString("scheme")+"_NP", ldbNonPlanAmt);
					lhshMjrHd.put(rs.getString("scheme")+"_CSS", 0.0);
					lhshMjrHd.put(rs.getString("scheme")+"_Total", lDeptSchemeEstAmt);
				}
			}
			else
			{
				while(rs.next())
				{
					lStrMjrHdSchemeExp_Id=rs.getString("scheme")+"_"+lStrPlanType;
					glogger.info("--------Dept Id for scheme--------"+lStrMjrHdSchemeExp_Id);
					lDeptSchemeEstAmt=rs.getDouble(lStrPlanType);
					glogger.info("--------Total for Budget--------"+lDeptSchemeEstAmt);
					lhshMjrHd.put(lStrMjrHdSchemeExp_Id, lDeptSchemeEstAmt);
				}
			}
		}
		catch(Exception e)
		{
			glogger.error("Exception in getGrantForScheme");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getGrantForScheme() "+e);
			}
			
		}
	}
		return lhshMjrHd ;
}
	
	
	public HashMap getExpForSchemeMjrHd(HashMap lHshPara)
	{
		glogger.info("inside get getExpForSchemeMjrHd");
		HashMap<String,Double> lhshMjrHd = new HashMap<String,Double>();
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer lSb=new StringBuffer();
		String bud_values="";
		String lStrPlanType="";
		String lStrMjrHdSchemeExp_Id="";
		int lintLangId=0;
		int lintFinYrId = 0;
		int lintPlanType = 0,lintPLId=0,lintNPId=0,lintCSSId=0;
		double ldbPlanAmt=0 ,ldbNonPlanAmt =0 , ldbCssAmt=0,lDeptMjrHdEstAmt=0;
		
		String lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		int  lintlocId=Integer.parseInt(lHshPara.get("DeptId").toString());
		
		glogger.info("LocId is--------"+lintlocId);
		
		if(lHshPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHshPara.get("FinYrId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lintLangId = Integer.parseInt(lHshPara.get("LangId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		}
		glogger.info("LangID is-------"+lStrLangId);
		
		if(lHshPara.get("PlanType")!=null)
		{
			lintPlanType = Integer.parseInt(lHshPara.get("PlanType").toString());
			glogger.info("Budget Type for Scheme == "+lintPlanType);
		
			if(lintPlanType==-1)
			{
				 lStrPlanType="Total";
			}
			else if(lintPlanType==0)
			{
				 lStrPlanType="All";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("PlanBudgetType")))
			{
				lStrPlanType="PL";
			}
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("NonPlanBudgetType")))
			{
				lStrPlanType="NP";
			}	
			else if(lintPlanType==Integer.parseInt(lObjRsrcBndle.getString("CSSBudgetType")))
			{
				lStrPlanType="CSS";
			}	
			if(lStrPlanType.equalsIgnoreCase("Total")||lStrPlanType.equalsIgnoreCase("All"))
			{
				lSb.append(" select (lpad(a.demand_no,3,'0') || ':' || lpad(a.mjr_hd,4,'0') || ':' || ");
				lSb.append(" lpad(a.sub_mjr_hd,2,'0') || ':' || lpad(a.min_hd,3,'0') || ':' || ");
				lSb.append(" lpad(a.sub_hd,2,'0')) scheme, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_1, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_2, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("Plan_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_3, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_1, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_2, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("NPL_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_3, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_1, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_2, ");
				lSb.append(" sum(decode(a.bud_type_code,'"+lObjRsrcBndle.getString("CSS_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_3 ");
				lSb.append(" from (select expdtls.demand_no, expdtls.mjr_hd, expdtls.sub_mjr_hd, ");
				lSb.append(" expdtls.min_hd, expdtls.sub_hd,expdtls.gross_amnt,expdtls.recovery_amt, ");
				lSb.append(" expdtls.bud_type_code from rpt_expenditure_dtls expdtls ");
				lSb.append(" where expdtls.exp_dt between TO_DATE('"+lHshPara.get("FDate").toString()+"', 'dd/mm/yyyy') and ");
				lSb.append(" TO_DATE('"+lHshPara.get("EndDate").toString()+"', 'dd/mm/yyyy') and ");
				if(lHshPara.get("MjrEndHd")!=null)
				{
					lSb.append(" expdtls.mjr_hd>="+ Integer.parseInt(lHshPara.get("MjrStartHd").toString())+" and expdtls.mjr_hd<="+Integer.parseInt(lHshPara.get("MjrEndHd").toString())+" and ");
				}
				else if(lHshPara.get("MjrStartHd") != null && lHshPara.get("MjrEndHd") == null)
				{
					lSb.append(" expdtls.mjr_hd="+ Integer.parseInt(lHshPara.get("MjrStartHd").toString())+")and ");
				}
				lSb.append(" lpad(expdtls.demand_no,3,'0') in (select dmd.demand_code from sgva_buddemand_mst dmd ");
				lSb.append(" where dmd.bpncode in (select bpn.bpncode from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id =(SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ="+Integer.parseInt(lHshPara.get("DeptId").toString())+" AND LOC.LANG_ID = "+lintLangId+") and ");
				lSb.append(" bpn.lang_id = '"+lStrLangId+"')))a "); 
				lSb.append(" group by a.demand_no , a.mjr_hd , a.sub_mjr_hd , a.min_hd , a.sub_hd ");
			}
			else 
			{
				lSb.append(" select (lpad(a.demand_no,3,'0') || ':' || lpad(a.mjr_hd,4,'0') || ':' || ");
				lSb.append(" lpad(a.sub_mjr_hd,2,'0') || ':' || lpad(a.min_hd,3,'0') || ':' || ");
				lSb.append(" lpad(a.sub_hd,2,'0')) scheme, ");
				if(lStrPlanType.equalsIgnoreCase("PL"))
				{
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("Plan_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_1, ");
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("Plan_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_2, ");
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("Plan_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 Plan_3 ");
				}
				else if(lStrPlanType.equalsIgnoreCase("NP"))
				{
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("NPL_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_1, ");
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("NPL_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_2, ");
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("NPL_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 NPL_3 ");
				}
				else if(lStrPlanType.equalsIgnoreCase("CSS"))
				{
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("CSS_1")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_1, ");
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("CSS_2")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_2, ");
					lSb.append(" sum(decode(a.bud_type_code ,'"+lObjRsrcBndle.getString("CSS_3")+"', a.gross_amnt - a.recovery_amt, 0)) /10000000 CSS_3 ");
				}
				lSb.append(" from (select expdtls.demand_no, expdtls.mjr_hd, expdtls.sub_mjr_hd, ");
				lSb.append(" expdtls.min_hd, expdtls.sub_hd,expdtls.gross_amnt,expdtls.recovery_amt, ");
				lSb.append(" expdtls.bud_type_code from rpt_expenditure_dtls expdtls ");
				lSb.append(" where expdtls.exp_dt between TO_DATE('"+lHshPara.get("FDate").toString()+"', 'dd/mm/yyyy') and ");
				lSb.append(" TO_DATE('"+lHshPara.get("EndDate").toString()+"', 'dd/mm/yyyy') and ");
				if(lHshPara.get("MjrEndHd")!=null)
				{
					lSb.append(" expdtls.mjr_hd>="+ Integer.parseInt(lHshPara.get("MjrStartHd").toString())+" and expdtls.mjr_hd<="+Integer.parseInt(lHshPara.get("MjrEndHd").toString())+" and ");
				}
				else if(lHshPara.get("MjrStartHd") != null && lHshPara.get("MjrEndHd") == null)
				{
					lSb.append(" expdtls.mjr_hd="+ Integer.parseInt(lHshPara.get("MjrStartHd").toString())+")and ");
				}
				lSb.append(" lpad(expdtls.demand_no,3,'0') in (select dmd.demand_code from sgva_buddemand_mst dmd ");
				lSb.append(" where dmd.bpncode in (select bpn.bpncode from sgva_budbpn_mapping bpn ");
				lSb.append(" where bpn.dept_id =(SELECT LOC.DEPARTMENT_ID LOC FROM RLT_LOCATION_DEPARTMENT LOC ");
				lSb.append(" WHERE LOC.LOC_CODE ='"+Integer.parseInt(lHshPara.get("DeptId").toString())+"' AND LOC.LANG_ID = "+lintLangId+") and ");
				lSb.append(" bpn.lang_id = '"+lStrLangId+"')))a "); 
				lSb.append(" group by a.demand_no , a.mjr_hd , a.sub_mjr_hd , a.min_hd , a.sub_hd ");
			}
			
		glogger.info("Query for getExpForSchemeMjrHd() -- "+lSb.toString());
		
		try
		{
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			
			
			if(lStrPlanType.equalsIgnoreCase("Total"))
			{
				while(rs.next())
				{
					lStrMjrHdSchemeExp_Id=rs.getString("scheme")+"_"+lStrPlanType;
					glogger.info("--------MjrHdExp for Scheme --------"+lStrMjrHdSchemeExp_Id);
					
					ldbPlanAmt=rs.getDouble("Plan_1")+rs.getDouble("Plan_2")+rs.getDouble("Plan_3");
					ldbNonPlanAmt=rs.getDouble("NPL_1")+rs.getDouble("NPL_2")+rs.getDouble("NPL_3");
					ldbCssAmt = rs.getDouble("CSS_1")+rs.getDouble("CSS_2")+rs.getDouble("CSS_3");
					lDeptMjrHdEstAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt);
					glogger.info("--------TotalExp for Scheme --------"+lDeptMjrHdEstAmt);
					lhshMjrHd.put(lStrMjrHdSchemeExp_Id, lDeptMjrHdEstAmt);
				}
			}
			
			else if(lStrPlanType.equalsIgnoreCase("All"))
			{
				glogger.info("----Inside All of getExpForScheme----");
				while(rs.next())
				{
					glogger.info("----Inside While of ExpScheme----");
					ldbPlanAmt=rs.getDouble("Plan_1")+rs.getDouble("Plan_2")+rs.getDouble("Plan_3");
					glogger.info("PlanAmount::"+ldbPlanAmt);
					ldbNonPlanAmt=rs.getDouble("NPL_1")+rs.getDouble("NPL_2")+rs.getDouble("NPL_3");
					glogger.info("NonPlanAmount::"+ldbNonPlanAmt);
					ldbCssAmt=rs.getDouble("CSS_1")+rs.getDouble("CSS_2")+rs.getDouble("CSS_3");
					glogger.info("CSSAmount::"+ldbCssAmt);
					lDeptMjrHdEstAmt=(ldbPlanAmt+ldbNonPlanAmt+ldbCssAmt);
					glogger.info("TotalAmount::"+lDeptMjrHdEstAmt);
					
					lhshMjrHd.put(rs.getString("scheme")+"_PL", ldbPlanAmt);
					glogger.info(rs.getString("scheme")+"_PL"+" --- "+lhshMjrHd.get(rs.getString("scheme")+"_PL"));
					lhshMjrHd.put(rs.getString("scheme")+"_NP", ldbNonPlanAmt);
					glogger.info(rs.getString("scheme")+"_NP"+ " --- "+lhshMjrHd.get(rs.getString("scheme")+"_NP"));
					lhshMjrHd.put(rs.getString("scheme")+"_CSS", ldbCssAmt);
					glogger.info(rs.getString("scheme")+"_CSS"+" --- "+lhshMjrHd.get(rs.getString("scheme")+"_CSS"));
					lhshMjrHd.put(rs.getString("scheme")+"_Total", lDeptMjrHdEstAmt);
				}
			}
			else if(lStrPlanType.equalsIgnoreCase("PL"))
			{
				while(rs.next())
				{
					lStrMjrHdSchemeExp_Id=rs.getString("scheme")+"_"+lStrPlanType;
					glogger.info("--------Dept Id for scheme--------"+lStrMjrHdSchemeExp_Id);
					lDeptMjrHdEstAmt=rs.getDouble("Plan_1")+rs.getDouble("Plan_2")+rs.getDouble("Plan_3");
					glogger.info("--------Total for scheme--------"+lDeptMjrHdEstAmt);
					lhshMjrHd.put(lStrMjrHdSchemeExp_Id, lDeptMjrHdEstAmt);
				}
			}
			else if(lStrPlanType.equalsIgnoreCase("NP"))
			{
				while(rs.next())
				{
					lStrMjrHdSchemeExp_Id=rs.getString("scheme")+"_"+lStrPlanType;
					glogger.info("--------Dept Id for scheme--------"+lStrMjrHdSchemeExp_Id);
					lDeptMjrHdEstAmt=rs.getDouble("NPL_1")+rs.getDouble("NPL_2")+rs.getDouble("NPL_3");
					glogger.info("--------Total for scheme--------"+lDeptMjrHdEstAmt);
					lhshMjrHd.put(lStrMjrHdSchemeExp_Id, lDeptMjrHdEstAmt);
				}
			}
			else if(lStrPlanType.equalsIgnoreCase("CSS"))
			{
				while(rs.next())
				{
					lStrMjrHdSchemeExp_Id=rs.getString("scheme")+"_"+lStrPlanType;
					glogger.info("--------Dept Id for scheme--------"+lStrMjrHdSchemeExp_Id);
					lDeptMjrHdEstAmt=rs.getDouble("CSS_1")+rs.getDouble("CSS_2")+rs.getDouble("CSS_3");
					glogger.info("--------Total for scheme--------"+lDeptMjrHdEstAmt);
					lhshMjrHd.put(lStrMjrHdSchemeExp_Id, lDeptMjrHdEstAmt);
				}
			}
			
		}
		catch(Exception e)
		{
			glogger.error("Exception in getExpForSchemeMjrHd");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getExpForSchemeMjrHd() "+e);
			}
		}
	}
		return lhshMjrHd ;
	}
	//End By Maneesh
	
	//-----------------------------STATE PROFILE REPORT------------------------------
	public int getFinancialYear(String lStrFromDate,String lStrToDate)
	{
		
		glogger.info("-- In getFinancialYear() -- ");
		int lintFinYear = 0;
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;

		StringBuffer lsb=new StringBuffer();
		
		lsb.append("SELECT fin_year_id FROM sgvc_fin_year_mst s where ");	
		
		if(!lStrFromDate.equals("") && !lStrToDate.equals(""))
		{
			
			lsb.append("s.from_date <= To_DATE('"+lStrFromDate+"','DD/MM/YYYY') and ");
			lsb.append("s.to_date >= TO_DATE('"+lStrToDate+"','DD/MM/YYYY') ");
			lsb.append("and s.lang_id='en_US' ");
		}
		else if(!lStrFromDate.equals("") && lStrToDate.equals(""))
		{
			lsb.append("s.from_date <= To_DATE('"+lStrFromDate+"','DD/MM/YYYY') and "); 
			lsb.append("s.to_date >= current_date and s.lang_id='en_US' ");
		}
		else if(lStrFromDate.equals("") && !lStrToDate.equals(""))
		{
			lsb.append("s.from_date <= current_date and "); 
			lsb.append("s.to_date >= To_DATE('"+lStrToDate+"','DD/MM/YYYY') and s.lang_id='en_US' ");
		}
		else if(lStrFromDate.equals("") && lStrToDate.equals(""))
		{
			lsb.append("s.from_date <= current_date and s.to_date >= current_date and s.lang_id='en_US' ");
		}
		
		glogger.info("Query to get getFinancialYear -- "+lsb.toString());
		
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lsb.toString());
			if(rs.next())
			{
				lintFinYear=rs.getInt("fin_year_id");
			}
			glogger.info("Financial Year == "+lintFinYear);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return lintFinYear;
	}
	
	public String getFundRange(long lintFundType)
	{
		ArrayList lSubFundLst = new ArrayList();
		HashMap lhshFundMrj = null;
		Statement lStmt=null;
		Connection lCon=null;
		ResultSet rs=null;
		String lMajorHdRange ="";
		String lSb = "select major_hd from config_recptexp_fund_mjrhd c"+
				" where c.fund_type='"+ lintFundType + "'"; 
		
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			rs.next();
			
			lMajorHdRange =  rs.getString("major_hd");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getSubFundMjrRange() "+e);
			}
		}
		
		return lMajorHdRange;
	}
	
	public ArrayList getSubFundMjrRange(long lintFundType)
	{
		ArrayList lSubFundLst = new ArrayList();
		HashMap lhshFundMrj = null;
		Statement lStmt=null;
		Connection lCon=null;
		ResultSet rs=null;
		StringBuffer lSb=new StringBuffer();
		
		lSb.append("select fund_name,major_hd ,fund_type from config_recptexp_fund_mjrhd c"+
		" where c.parent_fund_type='"+ lintFundType + "'"); 
		
		glogger.info("Query for getSubFundMjrRange() -- "+lSb.toString());
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			while(rs.next())
			{
				lhshFundMrj = new HashMap();
				lhshFundMrj.put("FundName", rs.getString("fund_name"));
				lhshFundMrj.put("FundId", rs.getInt("fund_type"));
				lhshFundMrj.put("MjrHdRange", rs.getString("major_hd"));
				lSubFundLst.add(lhshFundMrj);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getSubFundMjrRange() "+e);
			}
		}
		return lSubFundLst;
	}
	
	
	public ArrayList getSubFundMjrRange(int lintFundType)
	{
		ArrayList lSubFundLst = new ArrayList();
		HashMap lhshFundMrj = null;
		Statement lStmt=null;
		Connection lCon=null;
		ResultSet rs=null;
		StringBuffer lSb=new StringBuffer();
		
		lSb.append("select fund_name,major_hd ,fund_type from config_recptexp_fund_mjrhd c"+
		" where c.parent_fund_type='"+ lintFundType + "'"); 
		
		glogger.info("Query for getSubFundMjrRange() -- "+lSb.toString());
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			while(rs.next())
			{
				lhshFundMrj = new HashMap();
				lhshFundMrj.put("FundName", rs.getString("fund_name"));
				lhshFundMrj.put("FundId", rs.getInt("fund_type"));
				lhshFundMrj.put("MjrHdRange", rs.getString("major_hd"));
				lSubFundLst.add(lhshFundMrj);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getSubFundMjrRange() "+e);
			}
		}
		return lSubFundLst;
	}
	
	public HashMap getConsolidatedRcpBudAmt(ArrayList lArrMjrHd,int lFinYrId)
	{
		HashMap<String,Double> lHashData = new HashMap<String,Double>();
		Statement lStmt = null;
		glogger.info("--------------Inside getConsolidatedRcpAmt------------");
		ResultSet rs = null;
		
		double lngRevenueExptotal=0;
		double Bud_PL=0;
		double Bud_NP=0;
		double Bud_CSS=0;
		
		
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("select sum(EXP.nxt_yr_be_rcpt)/10000 amt from sgva_rcptest_finalamt EXP"+
						 " where ");
		if(lArrMjrHd.size()==2)
			buffer.append(" budmjrhd_code between '"+lArrMjrHd.get(0)+"' and '" +lArrMjrHd.get(1)+"' ");
		else
			buffer.append(" budmjrhd_code = '"+lArrMjrHd.get(0)+"' ");	
		buffer.append(" and fin_yr_id="+lFinYrId);
		buffer.append(" AND LEVEL_ID = 4");
		
		glogger.info("Query for ConsolidatedRcpBud == "+buffer.toString());
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				lngRevenueExptotal=rs.getDouble("amt");
			}
			glogger.info("ConsolidatedReceiptTotal Amount == "+lngRevenueExptotal);
			lHashData.put("Bud_PL",Bud_PL);
			lHashData.put("Bud_NP",Bud_NP);
			lHashData.put("Bud_CSS",Bud_CSS);
			lHashData.put("ConsolidatedReceiptTotal", lngRevenueExptotal);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return lHashData;
	}
	
	
	public HashMap getReceiptData(ArrayList lArrMjrHdRange,int liFinYrId,String lStrFrmDate,String lStrToDate)
	{
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;
		HashMap hmp=new HashMap();
		double lLngTotalExp=0;
		
		StringBuffer lsb=null;
		
		int liQueryType=0;//0-plan 1-nonplan 2-css
		
		if (lStrFrmDate == "" || lStrFrmDate == null )
		{
			lStrFrmDate = getFromDate(lStrToDate);
		}
		try
		{
			lCon=getSession().connection();
	
				lsb=new StringBuffer();
				lsb.append(" select sum(recpd.amount - NVL(recpd.DISBURSEMENT_AMT,0))/10000000 totexp from  rpt_receipt_dtls recpd where active = 'Y' and ");
				lsb.append(" recpd.REVENUE_DT>=TO_DATE('"+lStrFrmDate+"','DD/MM/YYYY') and recpd.REVENUE_DT<=TO_DATE('"+lStrToDate+"','DD/MM/YYYY') ");
				if(lArrMjrHdRange.size()==1)
					lsb.append(" and MJR_HD="+Integer.parseInt(lArrMjrHdRange.get(0).toString()));
				else
					lsb.append("and MJR_HD between "+Integer.parseInt(lArrMjrHdRange.get(0).toString())+" and "+Integer.parseInt(lArrMjrHdRange.get(1).toString()));
				
				glogger.info("Query for Receipt from TXT -- "+lsb.toString());
				
				lStmt=lCon.createStatement();
				rs=lStmt.executeQuery(lsb.toString());
				while(rs.next())
				{
					lLngTotalExp = rs.getDouble("totexp");
				}
				rs.close();
				lStmt.close();
				lCon.close();
				hmp.put("Receipt_Total",lLngTotalExp );
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return hmp;

	}
	
	public HashMap getProgressiveExpAmt(ArrayList lArrMjrHdRange,int liFinYrId, String lToDate)
	{
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;
		double lLngTotalExp=0;
		double lLngTotalPrgExp=0;
		StringBuffer lsb=new StringBuffer();
		HashMap hmp=new HashMap();
		try
		{	
			lCon=getSession().connection();
			
			lsb.append(" select  sum(r.bill_gross_amount)/10000000 totexp from ");
			lsb.append(" Trn_Bill_Register r,  Trn_Bill_Budhead_Dtls billhd, ");
			lsb.append(" Cmn_Lookup_Mst lkup, Cmn_Language_Mst lngmst where r.cheque_status is not null and r.fin_Year_Id ="+liFinYrId);
			if(lArrMjrHdRange.size()==1)
			{
				lsb.append(" and r.cheque_disp_dt is not null and r.cheque_disp_dt<=TO_DATE('"+lToDate+"','DD/MM/YYYY') and r.bill_No = billhd.bill_No and r.budmjr_hd='"+lArrMjrHdRange.get(0)+"' ");//and r.version_Id = (select max(r1.version_Id) from ");
			}
			else
			{
				lsb.append(" and r.cheque_disp_dt is not null and r.cheque_disp_dt<=TO_DATE('"+lToDate+"','DD/MM/YYYY') and r.bill_No = billhd.bill_No and r.budmjr_hd between '"+lArrMjrHdRange.get(0)+"' and '"+lArrMjrHdRange.get(1)+"' ");//and r.version_Id = (select max(r1.version_Id) from ");
			}
			//lsb.append(" Trn_Bill_Register r1 where r1.bill_Cntrl_No = r.bill_Cntrl_No) and billhd.version_id = ");
			//lsb.append(" ( select max(billhddtls.version_Id) from trn_bill_budhead_dtls  billhddtls where ");
			//lsb.append(" billhddtls.bill_no = billhd.bill_No) ");
			lsb.append(" and billhd.bud_Type = lkup.lookup_Id and ");
			lsb.append(" lkup.lookup_Short_Name = 'Plan' and lkup.lang_Id = lngmst.lang_Id and ");
			lsb.append(" lngmst.lang_Short_Name = 'en_US' ");
			
			glogger.info("Query for ProgExp_PL -- "+lsb.toString());
			
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lsb.toString());
			while(rs.next())
			{
				lLngTotalExp=rs.getDouble("totexp");
			}
			hmp.put("ProgExp_PL", lLngTotalExp);
			glogger.info("ProgExp_PL == "+lLngTotalExp);
			lLngTotalPrgExp = lLngTotalPrgExp + lLngTotalExp;
			rs.close();
			lStmt.close();
			
			
			lsb=new StringBuffer();
			lsb.append(" select  sum(r.bill_gross_amount)/10000000 totexp from ");
			lsb.append(" Trn_Bill_Register r,  Trn_Bill_Budhead_Dtls billhd, ");
			lsb.append(" Cmn_Lookup_Mst lkup, Cmn_Language_Mst lngmst where r.cheque_status is not null and r.fin_Year_Id ="+liFinYrId);
			if(lArrMjrHdRange.size()==1)
			{
				lsb.append(" and r.cheque_disp_dt is not null and r.cheque_disp_dt<=TO_DATE('"+lToDate+"','DD/MM/YYYY') and r.bill_No = billhd.bill_No and r.budmjr_hd='"+lArrMjrHdRange.get(0)+"' ");//and r.version_Id = (select max(r1.version_Id) from ");
			}
			else
			{
				lsb.append(" and r.cheque_disp_dt is not null and r.cheque_disp_dt<=TO_DATE('"+lToDate+"','DD/MM/YYYY') and r.bill_No = billhd.bill_No and r.budmjr_hd between '"+lArrMjrHdRange.get(0)+"' and '"+lArrMjrHdRange.get(1)+"' ");//and r.version_Id = (select max(r1.version_Id) from ");
			}
			//lsb.append(" Trn_Bill_Register r1 where r1.bill_Cntrl_No = r.bill_Cntrl_No) and billhd.version_id = ");
			//lsb.append(" ( select max(billhddtls.version_Id) from trn_bill_budhead_dtls  billhddtls where ");
			//lsb.append(" billhddtls.bill_no = billhd.bill_No) ");
			lsb.append(" and billhd.bud_Type = lkup.lookup_Id and ");
			lsb.append(" lkup.lookup_Short_Name = 'Non-Plan' and lkup.lang_Id = lngmst.lang_Id and ");
			lsb.append(" lngmst.lang_Short_Name = 'en_US' ");
			
			glogger.info("Query for ProgExp_NP -- "+lsb.toString());
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lsb.toString());
			while(rs.next())
			{
				lLngTotalExp=rs.getDouble("totexp");
			}
			hmp.put("ProgExp_NP", lLngTotalExp);
			glogger.info("ProgExp_NP == "+lLngTotalExp);
			lLngTotalPrgExp = lLngTotalPrgExp + lLngTotalExp;
			
			hmp.put("ProgExp_Total", lLngTotalPrgExp);
			glogger.info("ProgExp_Total == "+lLngTotalPrgExp);
			rs.close();
			lStmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return hmp;
	}
	
	
	public HashMap getFundMjrRange(int lintFundType)
	{
		HashMap lhshFundMrj = new HashMap();
		StringBuffer lSb=new StringBuffer();
		Statement lStmt=null;
		Connection lCon=null;
		ResultSet rs=null;
		
		lSb.append("select fund_name,major_hd ,fund_type from config_recptexp_fund_mjrhd c"+
		" where c.fund_type='"+ lintFundType + "'"); 
		
		glogger.info("Query for getFundMjrRange() "+ lSb.toString());
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			if(rs.next())
			{
				lhshFundMrj.put("FundName", rs.getString("fund_name"));
				lhshFundMrj.put("FundId", rs.getInt("fund_type"));
				lhshFundMrj.put("MjrHdRange",rs.getString("major_hd"));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getFundMjrRange() "+e);
			}
		}
		return lhshFundMrj;
	}
	

	public HashMap getConsolidatedExpBudAmt(ArrayList lArrLstMjrHd ,int lFinYrId )
	{
		HashMap<String,Double> lHashData = new HashMap<String,Double>();	
		glogger.info("--------------Inside  getConsolidatedExpBudAmt------------>");
	
		double douRevExptotal_PL_CH=0;
		double douRevExptotal_PL_VT=0;
		double douRevExptotal_NP_CH=0;
		double douRevExptotal_NP_VT=0;
		
		StringBuffer buffer=new StringBuffer();
		Statement lStmt = null;
		ResultSet rs = null;
		
		buffer.append("select sum(EXP.nxt_yr_be)/10000 amt from sgva_expest_finalamt EXP"+
						 " where ");
		if(lArrLstMjrHd.size()==2)
			buffer.append(" budmjrhd_code between '"+lArrLstMjrHd.get(0)+"' and '" +lArrLstMjrHd.get(1)+"' ");
		else
			buffer.append(" budmjrhd_code = '"+lArrLstMjrHd.get(0)+"' ");
		buffer.append(" and plan_nonplan='PL' and charg_vote='CH' and fin_yr_id="+lFinYrId);
		buffer.append(" AND EXP.GIA_FLG = 'N' AND EXP.FORM_TYPE != 'RE' AND ");
		buffer.append(" (EXP.REC_FLAG NOT IN ('R', 'C', 'M') OR EXP.REC_FLAG IS NULL) AND EXP.LEVEL_ID = 4");	
		
		glogger.info("Query for Consolidated_PL_CH -- "+buffer.toString());
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				douRevExptotal_PL_CH=rs.getDouble("amt");
			}
			glogger.info("getConsolidatedExpBudAmt_PL_Charged == "+douRevExptotal_PL_CH);
			lHashData.put("ConsExp_PL_CH", douRevExptotal_PL_CH);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
					
		buffer = new StringBuffer();
		buffer.append("select sum(EXP.nxt_yr_be)/10000 amt from sgva_expest_finalamt EXP"+
					 " where ");
		if(lArrLstMjrHd.size()==2)
			buffer.append(" budmjrhd_code between '"+lArrLstMjrHd.get(0)+"' and '" +lArrLstMjrHd.get(1)+"' ");	
		else	
			buffer.append(" budmjrhd_code = '"+lArrLstMjrHd.get(0)+"' ");
		buffer.append(" and plan_nonplan='PL' and charg_vote='VT' and fin_yr_id="+lFinYrId);
		buffer.append(" AND EXP.GIA_FLG = 'N' AND EXP.FORM_TYPE != 'RE' AND ");
		buffer.append(" (EXP.REC_FLAG NOT IN ('R', 'C', 'M') OR EXP.REC_FLAG IS NULL) AND EXP.LEVEL_ID = 4");	
		
		glogger.info("Query for Consolidated_PL_VT -- "+buffer.toString());
		
		try
		{
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				douRevExptotal_PL_VT=rs.getDouble("amt");
			}
			glogger.info("getConsolidatedExpBudAmt_PL_VT == "+douRevExptotal_PL_VT);
			lHashData.put("ConsExp_PL_VT", douRevExptotal_PL_VT);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			
		buffer = new StringBuffer();
		buffer.append("select sum(EXP.nxt_yr_be)/10000 amt from sgva_expest_finalamt EXP"+
				" where ");
		if(lArrLstMjrHd.size()==2)
			buffer.append(" budmjrhd_code between '"+lArrLstMjrHd.get(0)+"' and '" +lArrLstMjrHd.get(1)+"' ");
			
		else
		   buffer.append(" budmjrhd_code = '"+lArrLstMjrHd.get(0)+"' ");
		buffer.append(" and plan_nonplan='NP' and charg_vote='CH' and fin_yr_id="+lFinYrId);   
		buffer.append(" AND EXP.GIA_FLG = 'N' AND EXP.FORM_TYPE != 'RE' AND ");
		buffer.append(" (EXP.REC_FLAG NOT IN ('R', 'C', 'M') OR EXP.REC_FLAG IS NULL) AND EXP.LEVEL_ID = 4");	
		
		glogger.info("Query for Consolidated_NP_CH -- "+buffer.toString());
		
	   try
		{
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				douRevExptotal_NP_CH=rs.getDouble("amt");
			}
			glogger.info("getConsolidatedExpBudAmt_NP_CH == "+douRevExptotal_NP_CH);
			lHashData.put("ConsExp_NP_CH", douRevExptotal_NP_CH);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		   
		   
		 buffer = new StringBuffer();
		 buffer.append("select sum(EXP.nxt_yr_be)/10000 amt from sgva_expest_finalamt EXP "+
			" where ");
		 if(lArrLstMjrHd.size()==2)
			 buffer.append(" budmjrhd_code between '"+lArrLstMjrHd.get(0)+"' and '" +lArrLstMjrHd.get(1)+"' ");
		 else
			 buffer.append(" budmjrhd_code = '"+lArrLstMjrHd.get(0)+"' ");
		 buffer.append(" and plan_nonplan='NP' and charg_vote='VT' and fin_yr_id="+lFinYrId);
		 buffer.append(" AND EXP.GIA_FLG = 'N' AND EXP.FORM_TYPE != 'RE' AND ");
		 buffer.append(" (EXP.REC_FLAG NOT IN ('R', 'C', 'M') OR EXP.REC_FLAG IS NULL) AND EXP.LEVEL_ID = 4");	
		 
		 glogger.info("Query for Consolidated_NP_VT -- "+buffer.toString());
		 try
		 {
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				douRevExptotal_NP_VT=rs.getDouble("amt");
			}
			glogger.info("getConsolidatedExpBudAmt_NP_VT== "+douRevExptotal_NP_VT);
			lHashData.put("ConsExp_NP_VT", douRevExptotal_NP_VT);
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
		 }
		 finally
		 {
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		 }
		 return lHashData;
	}
	
	public double getCSSAmount(ArrayList lArrMjrHdRange,int liFinYrId)
	{
		double NXT_YR_BE=0;
		
		glogger.info("-- Inside getCSSAmount() -- ");
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;
		
		String lsb = null;
		
		/*
		if(lArrMjrHdRange.size()!=2)
		{
			lsb.append(" select sum(css.css_amount)/10000 CSSAMOUNT from sgva_css_mst css ");
			lsb.append(" where css.budmjrhd_code='"+lArrMjrHdRange.get(0)+"' and css.fin_yr_id="+liFinYrId );
		}
		else
		{
			lsb.append(" select sum(css.css_amount)/10000 CSSAMOUNT from sgva_css_mst css ");
			lsb.append(" where css.budmjrhd_code between '"+lArrMjrHdRange.get(0)+"' and '"+lArrMjrHdRange.get(1)+"' and css.fin_yr_id="+liFinYrId );
		}*/
		
		
		lsb = "select sum(NXT_YR_BE) sum " +
		  "from "+ 
	       "(SELECT (SUM(AMT.NXT_YR_BE) / 10000) NXT_YR_BE, amt.budmjrhd_code "+
	          "FROM SGVA_EXPEST_FINALAMT AMT, "+
	               "(select submst.bpn_code, "+
	                       "submst.demand_code, "+
	                       "submst.budmjrhd_code, "+
	                       "submst.budsubmjrhd_code, "+
	                       "submst.budminhd_code, "+
	                       "submst.budsubhd_code "+
	                  "from sgva_budsubhd_mst submst "+
	                 "where submst.css = 'Y' and submst.lang_id = 'en_US' and "+
	                       "submst.fin_yr_id = 20 and submst.status = 'Active') css "+
	         "WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND "+
	                "AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = 20 and "+
	               "(AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND "+
	               "AMT.FORM_TYPE NOT LIKE 'RE' and "+ 
	               "amt.buddemand_code = css.demand_code and "+
	               "amt.budmjrhd_code = css.budmjrhd_code and "+
	               "amt.budsubmjrhd_code = css.budsubmjrhd_code and "+
	               "amt.budminhd_code = css.budminhd_code and "+
	               "amt.budsubhd_code = css.budsubhd_code and "+
	               "amt.budbpn_code = css.bpn_code and "+
	               "css.budmjrhd_code between '"+lArrMjrHdRange.get(0)+"' and '"+lArrMjrHdRange.get(1)+"' "+
	         "group by amt.budmjrhd_code) ";
	               
		
		
		
		
		
		
		
		glogger.info("Query to get CSS Amount -- "+lsb.toString());
		glogger.info("Query to get CSS Amount -- "+lsb.toString());
		
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lsb.toString());
			if(rs.next())
			{
				NXT_YR_BE = rs.getDouble("sum");
			}
			glogger.info("CSS Amount == "+NXT_YR_BE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return NXT_YR_BE;
	}
	
	
	public HashMap getGrantAmt(ArrayList larrMjrHdRange,long lFinYrId ,String LangId)
	{
		HashMap<String,Double> lHashData = new HashMap<String,Double>();
		Statement lStmt = null;
		ResultSet rs = null;
		
		glogger.info("--------------Inside getGrantAmt------------>");
		double PL_Amount=0;
		double NP_Amount=0;
		double CSS_Amount=0;
		double GrantTotalAmt = 0;
		StringBuffer buffer=new StringBuffer();
	
		buffer.append("select sum(nonplan_amount)/10000 npl , " +
				
					"sum(plan_amount)/10000 pl , sum(css_amount)/10000 css " +
					"from sgva_budgrant_mst where");
		if(larrMjrHdRange.size()==2)
		{
			buffer.append(" budmjrhd_code between '"+larrMjrHdRange.get(0)+"' and '" +larrMjrHdRange.get(1)+"' ");
		}
		else
		{
			buffer.append(" budmjrhd_code = '"+larrMjrHdRange.get(0)+"' ");	
		}
	
		buffer.append(" and fin_yr_id="+lFinYrId+" and lang_id='"+LangId+"'");
		
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			rs.next();
			
			PL_Amount=rs.getDouble("pl");
			NP_Amount=rs.getDouble("npl");
			CSS_Amount=rs.getDouble("css");
			
			
			lHashData.put("GrantAmt_PL", PL_Amount);
			lHashData.put("GrantAmt_NP", NP_Amount);
			lHashData.put("GrantAmt_CSS", CSS_Amount);
			
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
				 
		
		/*buffer.append("select sum(plan_amount)/10000 amt from sgva_budgrant_mst"+
					 " where ");
		if(larrMjrHdRange.size()==2)
		{
			buffer.append(" budmjrhd_code between '"+larrMjrHdRange.get(0)+"' and '" +larrMjrHdRange.get(1)+"' ");
		}
		else
		{
			buffer.append(" budmjrhd_code = '"+larrMjrHdRange.get(0)+"' ");	
		}
		buffer.append(" and fin_yr_id="+ lFinYrId +" and lang_id= '"+LangId+"'");
	
		glogger.info("Query for GrantAmt_PL -- "+buffer.toString());
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				PL_Amount=rs.getDouble("amt");
			}
			glogger.info("GrantTotal == "+PL_Amount);
			lHashData.put("GrantAmt_PL", PL_Amount);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
				
		buffer = new StringBuffer();*/
		
		
				
/*		buffer.append("select sum(nonplan_amount)/10000 amt from sgva_budgrant_mst"+
						" where ");
		if(larrMjrHdRange.size()==2)
			buffer.append(" budmjrhd_code between '"+larrMjrHdRange.get(0)+"' and '" +larrMjrHdRange.get(1)+"' ");
			
		else
			buffer.append(" budmjrhd_code = '"+larrMjrHdRange.get(0)+"' ");	
		buffer.append(" and fin_yr_id="+lFinYrId+" and lang_id='"+LangId+"'");
		
		glogger.info("Query for GrantAmt_NP -- "+buffer.toString());
		try
		{
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				NP_Amount=rs.getDouble("amt");
			}
			glogger.info("GrantTotal == "+NP_Amount);
			lHashData.put("GrantAmt_NP", NP_Amount);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			
		buffer = new StringBuffer();
		buffer.append("select sum(css_amount)/10000 amt from sgva_budgrant_mst"+
						" where ");
			if(larrMjrHdRange.size()==2)
				buffer.append(" budmjrhd_code between '"+larrMjrHdRange.get(0)+"' and '" +larrMjrHdRange.get(1)+"' ");
			
		else
			buffer.append(" budmjrhd_code = '"+larrMjrHdRange.get(0)+"' ");
		buffer.append(" and fin_yr_id="+ lFinYrId +" and lang_id='"+LangId+"'");
		 
		glogger.info("Query for GrantAmt_CSS -- "+buffer.toString());
		
	    try
		{
				
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				CSS_Amount=rs.getDouble("amt");
			}
			glogger.info("GrantTotal == "+CSS_Amount);
			lHashData.put("GrantAmt_CSS", CSS_Amount);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
		catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/
		GrantTotalAmt = PL_Amount+NP_Amount+CSS_Amount;
		glogger.info("Total Grant Amount == "+GrantTotalAmt);
		lHashData.put("GrantAmt_Total", GrantTotalAmt);
		return lHashData;
}		
	

	public HashMap getExpenditureData(ArrayList lArrMjrHdRange,int liFinYrId,String lStrFrmDate,String lStrToDate)
	{
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;
		HashMap hmp=new HashMap();
		double lLngTotalExpPL=0;
		double lLngTotalExpNPL=0;
		double lLngTotalExpCSS=0;
		double lLngTotalExp = 0;
		Integer liMjrHd = 0;
		Integer liMjrHd1 = 0;
		Integer liMjrHd2 = 0;
		StringBuffer lsb=null;
		int liQueryType=0;//0-plan 1-nonplan 2-css
		try
		{
			if (lStrFrmDate == "" || lStrFrmDate == null )
			{
				lStrFrmDate = getFromDate(lStrToDate);
			}
			
			
			lCon=getSession().connection();
			
			lsb = new StringBuffer();
			lsb.append( "select  *  from " + 
					  
				      "(select sum(rexpd.GROSS_AMNT - NVL(rexpd.RECOVERY_AMT,0)) / 10000000 PL from "+
				     " rpt_expenditure_dtls rexpd "+
				     " where rexpd.exp_dt >= to_date('"+lStrFrmDate+"', 'dd/mm/yyyy') and "+
				       " rexpd.exp_dt <= to_date('"+lStrToDate+"', 'dd/mm/yyyy') " +
				       		
				       " and rexpd.BUD_TYPE_CODE in ('"+lObjRsrcBndle.getString("Plan_1")+"','"+lObjRsrcBndle.getString("Plan_2")+"','"+lObjRsrcBndle.getString("Plan_3")+"')");
		
		if(lArrMjrHdRange.size()==1)
		{
			liMjrHd=new Integer(lArrMjrHdRange.get(0).toString());
			lsb.append(" and MJR_HD="+liMjrHd);
		}
		else
		{
			 liMjrHd1=new Integer(lArrMjrHdRange.get(0).toString());
			 liMjrHd2=new Integer(lArrMjrHdRange.get(1).toString());
			lsb.append("and MJR_HD between "+liMjrHd1+" and "+liMjrHd2);
		}

		lsb.append("),");
				       
		    lsb.append(   "(select sum(rexpd.GROSS_AMNT - NVL(rexpd.RECOVERY_AMT,0)) / 10000000 NPL from "+
				     " rpt_expenditure_dtls rexpd "+
				     " where rexpd.exp_dt >= to_date('"+lStrFrmDate+"', 'dd/mm/yyyy') and "+
				       " rexpd.exp_dt <= to_date('"+lStrToDate+"', 'dd/mm/yyyy') "+
				      
				       " and rexpd.BUD_TYPE_CODE in ('"+lObjRsrcBndle.getString("NPL_1")+"','"+lObjRsrcBndle.getString("NPL_2")+"','"+lObjRsrcBndle.getString("NPL_3")+"')");


		if(lArrMjrHdRange.size()==1)
		{
			 liMjrHd=new Integer(lArrMjrHdRange.get(0).toString());
			lsb.append(" and MJR_HD="+liMjrHd);
		}
		else
		{
			 liMjrHd1=new Integer(lArrMjrHdRange.get(0).toString());
			 liMjrHd2=new Integer(lArrMjrHdRange.get(1).toString());
			lsb.append("and MJR_HD between "+liMjrHd1+" and "+liMjrHd2);
		}

		lsb.append("),");




				       
				      lsb.append( "(select sum(rexpd.GROSS_AMNT - NVL(rexpd.RECOVERY_AMT,0)) / 10000000 CSS from "+
				     "rpt_expenditure_dtls rexpd "+
				     "where rexpd.exp_dt >= to_date('"+lStrFrmDate+"', 'dd/mm/yyyy') and "+
				       "rexpd.exp_dt <= to_date('"+lStrToDate+"', 'dd/mm/yyyy') "+
				       
				       "and rexpd.BUD_TYPE_CODE in ('"+lObjRsrcBndle.getString("CSS_1")+"','"+lObjRsrcBndle.getString("CSS_2")+"','"+lObjRsrcBndle.getString("CSS_3")+"')");




		
		if(lArrMjrHdRange.size()==1)
		{
			liMjrHd=new Integer(lArrMjrHdRange.get(0).toString());
			lsb.append(" and MJR_HD="+liMjrHd);
		}
		else
		{
			 liMjrHd1=new Integer(lArrMjrHdRange.get(0).toString());
			liMjrHd2=new Integer(lArrMjrHdRange.get(1).toString());
			lsb.append("and MJR_HD between "+liMjrHd1+" and "+liMjrHd2);

		}
		lsb.append(")");

			
		/*	if(lArrMjrHdRange.size()==1)
			{
				Integer liMjrHd=new Integer(lArrMjrHdRange.get(0).toString());
				lsb.append(" and MJR_HD="+liMjrHd);
			}
			else
			{
				Integer liMjrHd1=new Integer(lArrMjrHdRange.get(0).toString());
				Integer liMjrHd2=new Integer(lArrMjrHdRange.get(1).toString());
				lsb.append("and MJR_HD between "+liMjrHd1+" and "+liMjrHd2);
			}*/
			
			glogger.info("Query for TxtExp -- "+lsb.toString());
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lsb.toString());
			rs.next();
			
			lLngTotalExpPL=rs.getDouble("PL");
			lLngTotalExpNPL=rs.getDouble("NPL");
			lLngTotalExpCSS=rs.getDouble("CSS");
			
			rs.close();
			lStmt.close();

			
					       
/*			while(liQueryType<3)
			{
				lsb=new StringBuffer();
				lsb.append(" select sum(rexpd.GROSS_AMNT - rexpd.RECOVERY_AMT)/10000000 totexp from  rpt_expenditure_dtls rexpd where ");
				if(lStrFrmDate != null && !lStrFrmDate.equals(""))
				{
					lsb.append(" p.rcptdate>=TO_DATE('"+lStrFrmDate+"','DD/MM/YYYY') and p.rcptdate<=TO_DATE('"+lStrToDate+"','DD/MM/YYYY') ");
				}
				else
				{
					lsb.append(" p.rcptdate>=TO_DATE('"+getFromDate(lStrToDate)+"','DD/MM/YYYY') and p.rcptdate<=TO_DATE('"+lStrToDate+"','DD/MM/YYYY') ");
				}
				if(liQueryType==0)
				{
					lsb.append(" and  budget = ");//plan
				}
				if(liQueryType==1)
				{
					lsb.append(" and  budget =  ");//non plan
				}
				if(liQueryType==2)
				{
					lsb.append(" and  budget= ");//css
				}
				if(lArrMjrHdRange.size()==1)
				{
					Integer liMjrHd=new Integer(lArrMjrHdRange.get(0).toString());
					lsb.append(" and MJR_HD="+liMjrHd);
				}
				else
				{
					Integer liMjrHd1=new Integer(lArrMjrHdRange.get(0).toString());
					Integer liMjrHd2=new Integer(lArrMjrHdRange.get(1).toString());
					lsb.append("and MJR_HD between "+liMjrHd1+" and "+liMjrHd2);
				}
				
				glogger.info("Query for TxtExp -- "+lsb.toString());
				lStmt=lCon.createStatement();
				rs=lStmt.executeQuery(lsb.toString());
				while(rs.next())
				{
					if(liQueryType==0)
					{
						lLngTotalExpPL=rs.getDouble("totexp");
					}
					if(liQueryType==1)
					{
						lLngTotalExpNPL=rs.getDouble("totexp");
					}
					if(liQueryType==2)
					{
						lLngTotalExpCSS=rs.getDouble("totexp");
					}
				}
				rs.close();
				lStmt.close();
				liQueryType++;
			}*/
			lCon.close();
			hmp.put("Exp_PL",lLngTotalExpPL );
			hmp.put("Exp_NP",lLngTotalExpNPL );
			hmp.put("Exp_CSS",lLngTotalExpCSS );
			lLngTotalExp = lLngTotalExpPL + lLngTotalExpNPL + lLngTotalExpCSS;
			hmp.put("Exp_Total",lLngTotalExp );
			glogger.info("Total expenditure from TXT -- "+lLngTotalExp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return hmp;

	}

	
	public HashMap getPublicContingencyFund(ArrayList larrMjrHdRange,long lFinYrId,String LangId)
	{
		HashMap<String,Double> lHashData = new HashMap<String,Double>();
		Statement lStmt = null;
		ResultSet rs = null;
		glogger.info("--------------Inside getPublicContingencyFund------------");
		
		double lngContFundtotal=0;
		StringBuffer buffer=new StringBuffer();
			
		buffer.append("select sum(nxt_yr_be_disb)/10000-sum(nxt_yr_be_rcpt)/10000 amt FROM sgva_rcptest_finalamt"+
						 " where ");
		if(larrMjrHdRange.size()==2)
		{
			buffer.append(" budmjrhd_code between '"+larrMjrHdRange.get(0)+"' and '" +larrMjrHdRange.get(1)+"' ");
		}
		else
		{
			buffer.append(" budmjrhd_code = '"+larrMjrHdRange.get(0)+"' ");
		}		
		buffer.append(" and fin_yr_id="+lFinYrId+" and lang_id= '"+LangId+"'");
		buffer.append(" AND LEVEL_ID = 4");
		
		glogger.info("Query for getPublicContingencyFund == "+buffer.toString());
		try
		{
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(buffer.toString());
			if(rs.next())
			{
				lngContFundtotal=rs.getDouble("amt");
			}
			glogger.info("ContinGencyFundTotal Amount == "+lngContFundtotal);
			lHashData.put("Cont_Public_FundTotal", lngContFundtotal);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return lHashData;
	}


	public String getFromDate(String DateFrom)
	{
		String lFromDate = null;
		int dd = 0 , mm = 0 , yyyy = 0;
		StringTokenizer s = new StringTokenizer(DateFrom  , "/");
		dd = Integer.parseInt(s.nextToken());
		mm = Integer.parseInt(s.nextToken());
		yyyy  = Integer.parseInt(s.nextToken());
		
		if(mm == 01 || mm == 02 || mm == 03 ) 
			yyyy--;
		lFromDate = "01/04/"+String.valueOf(yyyy);
		
		return lFromDate;
	}

	public String getStringLangId(Long lLngLangId)
	   {
		   glogger.info("-inside getStringLangId :: "+ lLngLangId);
		   Connection lCon=null;
			Statement lStmt = null;
			ResultSet rs = null;
			StringBuffer lSb=new StringBuffer();
		   //ArrayList lArrDeduction = new ArrayList();
		   String lStrLangId = "";

		   lSb.append("  select lang_short_name from cmn_language_mst where lang_id="+lLngLangId +" ");
		   glogger.info("Query is ---->"+lSb.toString());
		   try
			{
				lCon=DBConnection.getConnection();
				lStmt=lCon.createStatement();
				rs=lStmt.executeQuery(lSb.toString());
				
				while(rs.next())
				{
					lStrLangId=rs.getString("lang_short_name");
				}
			}
			catch(Exception e)
			{
				glogger.error("Exception in getAllDepts");
				e.printStackTrace();
			}
			finally
			{
				try
				{
					rs.close();
					lStmt.close();
					lCon.close();
				}
				catch(Exception e)
				{
					glogger.error("Exception in closing connection in getAllDept() "+e);
				}
			}
			return lStrLangId;
	   }
	
	
	
	public String getFundName(long fundId)
	{
		glogger.info("Value sof fundId is----------------->" + fundId);
		glogger.info("Insider the getFundName||||||||||||||||||||||||||||||||||||||||||");
		String lQuery = "select fund_name from config_recptexp_fund_mjrhd where fund_type = "+fundId;
		glogger.info("Insider the getFundName||||||||||||||||||||||||||||||||||||||||||");
		String fund_name = "";
		glogger.info("Insider the getFundName||||||||||||||||||||||||||||||||||||||||||");
		
		if(fundId == 110001 )
		{
			return("3)SurPlus/Deficit (1-2)");
		}
		else if(fundId == 110002)
		{
			return("6)SurPlus/Deficit (4-5)");
		}
		else if(fundId == 100001)
		{
			return("I)Consolidated Fund");
		}
		else if(fundId == 110003)
		{
			return("7)Consolidated Fund Receipt(1+4)");
		}
		else if(fundId == 110004)
		{
			return("8)Consolidated Fund Expenditure(2+5)");
		}
		else if(fundId == 110005)
		{
			return("9)Net Consolidated(7-8)");
		}
		else if(fundId == 110006)
		{
			return("II-Contigency Fund");
		}
		else if(fundId == 110007)
		{
			return("III-Public Account");
		}
		else if(fundId == 110008)
		{
			return("Rounded Difference");
		}

		
		else if(fundId == 100002)
		{	
			return("1)Revenue Receipt");
		}
		
		else if(fundId == 100006)
		{	
			return("2)Revenue Expenditure");
		}
		else if(fundId == 100007)
		{	
			return("4)Capital Receipt");
		}
		else if(fundId == 100011)
		{	
			return("5)Capital Expenditure");
		}
		else if(fundId == 100013)
		{	
			return("11)Public Account(NET)");
		}
		else if(fundId == 100012)
		{	
			return("12)Contigency(NET)");
		}
				
		else
			return(" ");
				
		
				
		
	}
	
	public String getStringLangId(long lLngLangId)
	   {
		   glogger.info("-inside getStringLangId :: "+ lLngLangId);
		   Connection lCon=null;
			Statement lStmt = null;
			ResultSet rs = null;
			StringBuffer lSb=new StringBuffer();
		   //ArrayList lArrDeduction = new ArrayList();
		   String lStrLangId = "";

		   lSb.append("  select lang_short_name from cmn_language_mst where lang_id="+lLngLangId +" ");
		   glogger.info("Query is ---->"+lSb.toString());
		   try
			{
				lCon=DBConnection.getConnection();
				lStmt=lCon.createStatement();
				rs=lStmt.executeQuery(lSb.toString());
				
				while(rs.next())
				{
					lStrLangId=rs.getString("lang_short_name");
				}
			}
			catch(Exception e)
			{
				glogger.error("Exception in getAllDepts");
				e.printStackTrace();
			}
			finally
			{
				try
				{
					rs.close();
					lStmt.close();
					lCon.close();
				}
				catch(Exception e)
				{
					glogger.error("Exception in closing connection in getAllDept() "+e);
				}
			}
			return lStrLangId;
	   }
	
	
	
	public HashMap getMjrHDWiseBudRcptData(int StartLimit,int EndLimit,int FinYearId,String langId)
	{
		
		glogger.info("hi i am in getMjrHDWiseBudget Data Function");
		HashMap<String,Double> lHashReturn = new HashMap<String,Double>(); 
		Statement lStmt = null;
		ResultSet rs = null;
		String lsb = null;
		Connection lCon = null;
		int total = 0;
		double lTotalAmount = 0.0;
		try
		{
			lCon=(Connection)getSession().connection();
			glogger.info("connection has been fetched");
		
			lStmt=(Statement)lCon.createStatement();
			
			
			lsb = " select count(count( s.budmjrhd_code)) Total from sgva_rcptest_finalamt s " +
			  "	where s.fin_yr_id = "+ FinYearId +" and s.lang_id = '"+ langId +"' "+
			  "	and s.level_id = 4 and s.budmjrhd_code >= "+ StartLimit +" and s.budmjrhd_code <= "+ EndLimit +" group by s.budmjrhd_code" ;
	
			rs=lStmt.executeQuery(lsb.toString());
			rs.next();
			total = rs.getInt("Total");
			glogger.info("Query for CurrBudSubMjrHead :-" + lsb);
			
			
			lsb = " select (sum(s.nxt_yr_be_rcpt)/10000) sum, s.budmjrhd_code major from sgva_rcptest_finalamt s " +
			  "	where s.fin_yr_id = "+ FinYearId +" and s.lang_id = '"+ langId +"' "+
			  "	and s.level_id = 4 and s.budmjrhd_code >= "+ StartLimit +" and s.budmjrhd_code <= "+ EndLimit +" group by s.budmjrhd_code" ;
	
			
			
			glogger.info("Query for CurrBudSubMjrHead :-" + lsb);
			
					
			glogger.info("TTTTTTTTTTTTotal number of rows int htis result set is :- " + total);
			rs=lStmt.executeQuery(lsb.toString());
			glogger.info("result has been ftched bnow i ma going for");
			rs.next();

			if(total != 0)
			{
				for(int z = StartLimit ; z <= EndLimit ; z++ )
				{
					glogger.info("H i iam in for loop :- " + z);
					if(rs.getInt("major") == z)
					{
						
						
						glogger.info("Hi i am in Ifw Loop");
						lHashReturn.put(z+"PL",0.0);
						glogger.info(z+"PL" );
						lHashReturn.put(z+"NP",0.0);
						lHashReturn.put(z+"CSS",0.0);
						lHashReturn.put(z+"Total",rs.getDouble("sum"));
						lTotalAmount += Math.round(Double.parseDouble((((rs.getObject("sum").toString())))));
						
						glogger.info(rs.getObject("major").toString());
						glogger.info(rs.getObject("sum").toString());
						
						if(total != 1)
						{
							rs.next();
							total--;
						}
						
							
							
					}
					else
					{
						glogger.info("Hi i am in Else loop");
						lHashReturn.put(z+"PL",0.0);
						glogger.info("Values in else = " + z+"PL" );
						lHashReturn.put(z+"NP",0.0);
						lHashReturn.put(z+"CSS",0.0);
						lHashReturn.put(z+"Total",0.0);
					}
				
				
					
				}
			}
			else
			{
				for(int z = StartLimit ; z <= EndLimit ; z++ )
				{
					glogger.info("Hi i am in Else loop");
					lHashReturn.put(z+"PL",0.0);
					glogger.info("Values in else = " + z+"PL" );
					lHashReturn.put(z+"NP",0.0);
					lHashReturn.put(z+"CSS",0.0);
					lHashReturn.put(z+"Total",0.0);
				}
				
			}
			
			rs.close();
			lStmt.close();
			
		}
		catch(Exception e)
		{
			glogger.info(e);
		}
		
		lHashReturn.put("Total_Bud", lTotalAmount );
		
		return lHashReturn;
	}
	
	
	
	public HashMap getMjrHDWiseCurrRcptData(int StartLimit,int EndLimit,int FinYearId,String langId , String lStrFrmDate,String lStrToDate)
	{
		
		glogger.info("hi i am in getMjrHDWiseBudgetExxpenditure Data Function");
		HashMap<String,Double> lHashReturn = new HashMap(); 
		Statement lStmt = null;
		ResultSet rs = null;
		String lsb = null;
		Connection lCon = null;
		String budget_type = null;
		double lTotalAmount = 0.0;
		int total = 0;
		
		if(lStrFrmDate.equals(""))
		{
			lStrFrmDate = getFromDate(lStrToDate);
		}
		
		try
		{
			lCon=(Connection)getSession().connection();
			glogger.info("connection has been fetched");
		
			lStmt=(Statement)lCon.createStatement();
			
			
			lsb = "select  count(count(MJR_HD)) Total "+
		      "	from rpt_receipt_dtls recpd " +
		      "	where active = 'Y' and recpd.REVENUE_DT >= TO_DATE('"+lStrFrmDate+"', 'DD/MM/YYYY') and "+
		      " recpd.REVENUE_DT <= TO_DATE('"+lStrToDate+"', 'DD/MM/YYYY') and " +
		      " MJR_HD between "+ StartLimit +" and "+ EndLimit +" " +
		      "	group by MJR_HD ";
			
			glogger.info("Query for CurrBudSubMjrHead :-" + lsb);
			rs=lStmt.executeQuery(lsb);
			rs.next();
			total = rs.getInt("Total");
			rs.close();
			
			lsb = "select sum((recpd.amount - NVL(recpd.DISBURSEMENT_AMT, 0)) / 10000000)  sum , "+
		      " MJR_HD major" +
		      "	from rpt_receipt_dtls recpd " +
		      "	where active = 'Y' and recpd.REVENUE_DT >= TO_DATE('"+lStrFrmDate+"', 'DD/MM/YYYY') and "+
		      " recpd.REVENUE_DT <= TO_DATE('"+lStrToDate+"', 'DD/MM/YYYY') and " +
		      " MJR_HD between "+ StartLimit +" and "+ EndLimit +" " +
		      "	group by MJR_HD ";
			
			glogger.info("The query of getMjrHDWiseCurrRcptData is -->  " + lsb);
			glogger.info("The query of getMjrHDWiseCurrRcptData is -->  " + lsb);
			
			
			rs=lStmt.executeQuery(lsb);
			
			
			glogger.info("TTTTTTTTTTTTotal number of rows int htis result set is :- " + total);
			
			glogger.info("result has been ftched bnow i ma going for");
			rs.next();
			
			if(total!=0)
			{
				for(int z = StartLimit ; z <= EndLimit ; z++ )
				{
					glogger.info("H i iam in for loop :- " + z);
					
					if(rs.getInt("major") == z)
					{
						
						glogger.info("Hi i am in Ifw Loop");
						lHashReturn.put(z+"PL",0.0);
						glogger.info(z+"PL" );
						lHashReturn.put(z+"NP",0.0);
						lHashReturn.put(z+"CSS",0.0);
						lHashReturn.put(z+"Total",(rs.getDouble("sum")));
						lTotalAmount += (Double.parseDouble((rs.getObject("sum").toString())));
						
						glogger.info(rs.getObject("major").toString());
						glogger.info(rs.getObject("sum").toString());
						
						if(total!=1)
						{
							rs.next();
							total--;
						}
						
							
					}
					else
					{
						glogger.info("Hi i am in Else loop");
						lHashReturn.put(z+"PL",0.0);
						glogger.info("Values in else = " + z+"PL" );
						lHashReturn.put(z+"NP",0.0);
						lHashReturn.put(z+"CSS",0.0);
						lHashReturn.put(z+"Total",0.0);
					}
				
				
					
					
				}
			
			}
			else
			{
				for(int z = StartLimit ; z <= EndLimit ; z++ )
				{
					glogger.info("H i iam in for loop :- " + z);
					glogger.info("Hi i am in Else loop");
					lHashReturn.put(z+"PL",0.0);
					glogger.info("Values in else = " + z+"PL" );
					lHashReturn.put(z+"NP",0.0);
					lHashReturn.put(z+"CSS",0.0);
					lHashReturn.put(z+"Total",0.0);
					
				}
				
			}
			
			rs.close();
			lStmt.close();
			
		}
		catch(Exception e)
		{
			glogger.info(e);
		}
		
		lHashReturn.put("Total_Curr", (lTotalAmount ));
		
		return lHashReturn;
	}
	
	public HashMap getMjrHDWiseBudExpData(int StartLimit,int EndLimit,int FinYearId,String langId ,String lStrFrmDate ,String lstrTodate)
	{
		
		glogger.info("hi i am in getMjrHDWiseBudgetExxpenditure Data Function");
		glogger.info("hi i am in getMjrHDWiseBudgetExxpenditure Data Function");
		HashMap<String,Double> lHashReturn = new HashMap<String,Double>(); 
		Statement lStmt = null;
		ResultSet rs = null;
		String lsb = null;
		Connection lCon = null;
		String budget_type = null;
		double lTotalAmount = 0.0;
		int total = 0;
	
		try
		{	
			lCon=(Connection)getSession().connection();
			lStmt=(Statement)lCon.createStatement();
			glogger.info("connection has been fetched");
		
			
			for(int i=1 ; i<=2 ; i++)
			{
				if(i==1)
					budget_type = "PL";
				else if(i==2)
					budget_type = "NP";
				else
					budget_type = "CSS";
				
				lsb = "select count(count(t.budmjrhd_code)) total" +
			  	  "	from sgva_expest_finalamt t "+
			  	  "	where t.fin_yr_id = "+FinYearId+" and t.lang_id = '"+langId+"' and " +
			      " t.budmjrhd_code > '"+StartLimit+"' and t.budmjrhd_code < '"+EndLimit+"' and " +
			      " t.plan_nonplan = '"+budget_type+"' and t.charg_vote in ('CH','VT') and t.level_id = 4 and t.form_Type <> 'RE' AND " +
			      " (t.rec_Flag NOT IN ('R', 'C', 'M') OR t.rec_Flag IS NULL) AND " +
			      " t.gia_Flg = 'N'" +
			      "	group by t.budmjrhd_code ";
				glogger.info("Query for TxtExp -- "+lsb.toString());
				
				rs=lStmt.executeQuery(lsb);
				rs.next();
				total = rs.getInt("total");
				
				lsb = "select (sum(t.nxt_yr_be) / 10000) sum ,t.budmjrhd_code major " +
			  	  "	from sgva_expest_finalamt t "+
			  	  "	where t.fin_yr_id = "+FinYearId+" and t.lang_id = '"+langId+"' and " +
			      " t.budmjrhd_code > '"+StartLimit+"' and t.budmjrhd_code < '"+EndLimit+"' and " +
			      " t.plan_nonplan = '"+budget_type+"' and t.charg_vote in ('CH','VT') and t.level_id = 4 and t.form_Type <> 'RE' AND " +
			      " (t.rec_Flag NOT IN ('R', 'C', 'M') OR t.rec_Flag IS NULL) AND " +
			      " t.gia_Flg = 'N'" +
			      "	group by t.budmjrhd_code ";
				
				
				glogger.info("Query for TxtExp -- "+lsb.toString());	
				rs=lStmt.executeQuery(lsb.toString());
				glogger.info("result has been ftched bnow i ma going for");
				
				rs.next();
				
				if(total!=0)
				{
					lTotalAmount = 0;
					
					for(int z = StartLimit ; z <= EndLimit ; z++ )
					{
		//				glogger.info("H i iam in for loop z value:- " + z);
		//				glogger.info("H i iam in for loopmajor values is value:- " + rs.getInt("major")+" "+budget_type);
						
						if(rs.getInt("major") == z)
						{
							
		//					glogger.info("Hi i am in Ifw Loop");
							lHashReturn.put(z+budget_type,rs.getDouble("sum"));
							
							
							lTotalAmount += (Double.parseDouble((rs.getObject("sum").toString())));
							
	//						glogger.info(rs.getObject("major").toString());
	//						glogger.info(rs.getObject("sum").toString());
							
							if(total!=1)
							{
								rs.next();
								total--;
							}
							
								
						 }
						 else
						 {
		//					glogger.info("Hi i am in Else loop");
							lHashReturn.put(z+budget_type,0.0);
							
							
						 }
					
					
						
						
					}
					
					lHashReturn.put(budget_type+"total",lTotalAmount);
				
				}
				else
				{
					for(int z = StartLimit ; z <= EndLimit ; z++ )
					{
				//		glogger.info("H i iam in for loop :- " + z);
				//		glogger.info("Hi i am in Else loop");
						lHashReturn.put(z+budget_type,0.0);
						
						
					}
					
				}
				
				
			}		
					
			rs.close();
			lStmt.close();
		}
		catch(Exception e)
		{
			glogger.info(e);
		}
		
		lHashReturn.put("Total_Bud", lTotalAmount );
		
		return lHashReturn;
	}
	
	public HashMap getMjrHDWiseBudCSSData(int StartLimit,int EndLimit,int FinYearId,String langId ,String lStrFrmDate ,String lstrTodate)
	{
		
		glogger.info("hi i am in getMjrHDWiseBudgetCSS Data Function");
		glogger.info("hi i am in getMjrHDWiseBudgetCSS Data Function");
		HashMap<String,Double> lHashReturn = new HashMap<String,Double>(); 
		Statement lStmt = null;
		ResultSet rs = null;
		String lsb = null;
		Connection lCon = null;
		String budget_type = null;
		double lTotalAmount = 0.0;
		int total = 0;
	
		try
		{	
			lCon=(Connection)getSession().connection();
			lStmt=(Statement)lCon.createStatement();
			glogger.info("connection has been fetched");
		
		
					budget_type = "CSS";
				
	/*			lsb = "SELECT count(count( amt.budmjrhd_code)) total " +
		  		"FROM SGVA_EXPEST_FINALAMT AMT, " +
			       "Sgva_Budbpn_Mapping b, " +
			       "(select submst.bpn_code, "+
			               "submst.demand_code,"+
			               "submst.budmjrhd_code,"+
			               "submst.budsubmjrhd_code,"+
			               "submst.budminhd_code, "+
			               "submst.budsubhd_code "+
			          "from sgva_budsubhd_mst submst "+
			         "where submst.css = 'Y' and submst.lang_id = 'en_US' and "+
			               "submst.fin_yr_id = " + FinYearId +" and submst.status='Active') css "+
			 "WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND AMT.GIA_FLG = 'N' AND "+
			       "AMT.FIN_YR_ID = b.fin_yr_id and b.fin_yr_id = 20 AND"+
			       "(AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND "+
			       "AMT.FORM_TYPE NOT LIKE 'RE'  AND "+
			       "B.LANG_ID = 'en_US' and b.status='Active' and "+
			       "amt.budmjrhd_code = css.budmjrhd_code "+  
			       "and css.budmjrhd_code between '"+StartLimit+"' and '"+EndLimit+"'" +
			 "group by amt.budmjrhd_code ";
				*/
				
				lsb = "SELECT count(count( amt.budmjrhd_code)) total " +
		          "FROM SGVA_EXPEST_FINALAMT AMT, "+
	               "(select submst.bpn_code, "+
	                       "submst.demand_code, "+
	                       "submst.budmjrhd_code, "+
	                       "submst.budsubmjrhd_code, "+
	                       "submst.budminhd_code, "+
	                       "submst.budsubhd_code "+
	                  "from sgva_budsubhd_mst submst "+
	                 "where submst.css = 'Y' and submst.lang_id = 'en_US' and "+
	                       "submst.fin_yr_id = " + FinYearId +" and submst.status = 'Active') css "+
	         "WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND "+
	                "AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = " + FinYearId +" and "+
	               "(AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND "+
	               "AMT.FORM_TYPE NOT LIKE 'RE' and "+ 
	               "amt.buddemand_code = css.demand_code and "+
	               "amt.budmjrhd_code = css.budmjrhd_code and "+
	               "amt.budsubmjrhd_code = css.budsubmjrhd_code and "+
	               "amt.budminhd_code = css.budminhd_code and "+
	               "amt.budsubhd_code = css.budsubhd_code and "+
	               "amt.budbpn_code = css.bpn_code and "+
	               "css.budmjrhd_code between '"+StartLimit+"' and '"+EndLimit+"' "+
	         "group by amt.budmjrhd_code ";
		
		
				
				
				
				
				glogger.info("Query for TxtExp -- "+lsb.toString());
				
				rs=lStmt.executeQuery(lsb);
				rs.next();
				total = rs.getInt("total");
				
				
				
			    lsb =   "(SELECT (SUM(AMT.NXT_YR_BE) / 10000) sum, amt.budmjrhd_code major "+
			          "FROM SGVA_EXPEST_FINALAMT AMT, "+
			               "(select submst.bpn_code, "+
			                       "submst.demand_code, "+
			                       "submst.budmjrhd_code, "+
			                       "submst.budsubmjrhd_code, "+
			                       "submst.budminhd_code, "+
			                       "submst.budsubhd_code "+
			                  "from sgva_budsubhd_mst submst "+
			                 "where submst.css = 'Y' and submst.lang_id = 'en_US' and "+
			                       "submst.fin_yr_id = " + FinYearId +" and submst.status = 'Active') css "+
			         "WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND "+
			                "AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = " + FinYearId +" and "+
			               "(AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND "+
			               "AMT.FORM_TYPE NOT LIKE 'RE' and "+ 
			               "amt.buddemand_code = css.demand_code and "+
			               "amt.budmjrhd_code = css.budmjrhd_code and "+
			               "amt.budsubmjrhd_code = css.budsubmjrhd_code and "+
			               "amt.budminhd_code = css.budminhd_code and "+
			               "amt.budsubhd_code = css.budsubhd_code and "+
			               "amt.budbpn_code = css.bpn_code and "+
			               "css.budmjrhd_code between '"+StartLimit+"' and '"+EndLimit+"' "+
			         "group by amt.budmjrhd_code) ";
				
				
				
				
				
				
				
				
				
				
				
				
				
		/*		lsb = "SELECT (SUM(AMT.NXT_YR_BE) / 10000) sum,  amt.budmjrhd_code major " +
		  		"FROM SGVA_EXPEST_FINALAMT AMT, " +
		       "Sgva_Budbpn_Mapping b, " +
		       "(select submst.bpn_code, "+
		               "submst.demand_code,"+
		               "submst.budmjrhd_code,"+
		               "submst.budsubmjrhd_code,"+
		               "submst.budminhd_code, "+
		               "submst.budsubhd_code "+
		          "from sgva_budsubhd_mst submst "+
		         "where submst.css = 'Y' and submst.lang_id = 'en_US' and "+
		               "submst.fin_yr_id = " + FinYearId +" and submst.status='Active') css "+
		 "WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND AMT.GIA_FLG = 'N' AND "+
		       "AMT.FIN_YR_ID = b.fin_yr_id and b.fin_yr_id = 20 AND"+
		       "(AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND "+
		       "AMT.FORM_TYPE NOT LIKE 'RE'  AND "+
		       "B.LANG_ID = 'en_US' and b.status='Active' and "+
		       "amt.budmjrhd_code = css.budmjrhd_code "+  
		       "and css.budmjrhd_code between '"+StartLimit+"' and '"+EndLimit+"'" +
		 "group by amt.budmjrhd_code ";
		*/
				
				
				glogger.info("the night query is :0- " + lsb.toString() );
				glogger.info("Query for TxtExp -- "+lsb.toString());	
				rs=lStmt.executeQuery(lsb.toString());
				glogger.info("result has been ftched bnow i ma going for");
				
				rs.next();
				
				if(total!=0)
				{
					lTotalAmount = 0;
					
					for(int z = StartLimit ; z <= EndLimit ; z++ )
					{
						//glogger.info("H i iam in for loop z value:- " + z);
						//glogger.info("H i iam in for loopmajor values is value:- " + rs.getInt("major")+" "+budget_type);
						
						if(rs.getInt("major") == z)
						{
							
							//glogger.info("Hi i am in Ifw Loop");
							lHashReturn.put(z+budget_type,rs.getDouble("sum"));
							
							
							lTotalAmount += (Double.parseDouble((rs.getObject("sum").toString())));
							
				//			glogger.info(rs.getObject("major").toString());
				//			glogger.info(rs.getObject("sum").toString());
							
							if(total!=1)
							{
								rs.next();
								total--;
							}
							
								
						 }
						 else
						 {
					//		glogger.info("Hi i am in Else loop");
							lHashReturn.put(z+budget_type,0.0);
							
							
						 }
					
					
						
						
					}
					
					lHashReturn.put(budget_type+"total",lTotalAmount);
				
				}
				else
				{
					for(int z = StartLimit ; z <= EndLimit ; z++ )
					{
			//			glogger.info("H i iam in for loop :- " + z);
			//			glogger.info("Hi i am in Else loop");
						lHashReturn.put(z+budget_type,0.0);
						
						
					}
					
				}
				
				
		
					
			rs.close();
			lStmt.close();
		}
		catch(Exception e)
		{
			glogger.info(e);
		}
		
		lHashReturn.put("Total_Bud", lTotalAmount );
		
		return lHashReturn;
	}
	

	public HashMap getMjrHDWiseGrantData(int StartLimit,int EndLimit,int FinYearId,String langId ,String lStrFrmDate ,String lstrTodate)
	{
		
		glogger.info("hi i am in getMjrHDWiseBudgetCSS Data Function");
		glogger.info("hi i am in getMjrHDWiseBudgetCSS Data Function");
		HashMap<String,Double> lHashReturn = new HashMap<String,Double>(); 
		Statement lStmt = null;
		ResultSet rs = null;
		String lsb = null;
		Connection lCon = null;
		String budget_type = null;
		double lTotalAmount = 0.0;
		int total = 0;
	
		try
		{	
			lCon=(Connection)getSession().connection();
			lStmt=(Statement)lCon.createStatement();
			glogger.info("connection has been fetched");
		
		
			budget_type = "CSS";
	
				
			lsb = "SELECT count(count( amt.budmjrhd_code)) total " +
		          "FROM SGVA_EXPEST_FINALAMT AMT, "+
	               "(select submst.bpn_code, "+
	                       "submst.demand_code, "+
	                       "submst.budmjrhd_code, "+
	                       "submst.budsubmjrhd_code, "+
	                       "submst.budminhd_code, "+
	                       "submst.budsubhd_code "+
	                  "from sgva_budsubhd_mst submst "+
	                 "where submst.css = 'Y' and submst.lang_id = 'en_US' and "+
	                       "submst.fin_yr_id = " + FinYearId +" and submst.status = 'Active') css "+
	         "WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND "+
	                "AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = " + FinYearId +" and "+
	               "(AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND "+
	               "AMT.FORM_TYPE NOT LIKE 'RE' and "+ 
	               "amt.buddemand_code = css.demand_code and "+
	               "amt.budmjrhd_code = css.budmjrhd_code and "+
	               "amt.budsubmjrhd_code = css.budsubmjrhd_code and "+
	               "amt.budminhd_code = css.budminhd_code and "+
	               "amt.budsubhd_code = css.budsubhd_code and "+
	               "amt.budbpn_code = css.bpn_code and "+
	               "css.budmjrhd_code between '"+StartLimit+"' and '"+EndLimit+"' "+
	         "group by amt.budmjrhd_code ";
		
		
				
				
				
				
				glogger.info("Query for TxtExp -- "+lsb.toString());
				
				rs=lStmt.executeQuery(lsb);
				rs.next();
				total = rs.getInt("total");
				
				
				
			    lsb =   "(SELECT (SUM(AMT.NXT_YR_BE) / 10000) sum, amt.budmjrhd_code major "+
			          "FROM SGVA_EXPEST_FINALAMT AMT, "+
			               "(select submst.bpn_code, "+
			                       "submst.demand_code, "+
			                       "submst.budmjrhd_code, "+
			                       "submst.budsubmjrhd_code, "+
			                       "submst.budminhd_code, "+
			                       "submst.budsubhd_code "+
			                  "from sgva_budsubhd_mst submst "+
			                 "where submst.css = 'Y' and submst.lang_id = 'en_US' and "+
			                       "submst.fin_yr_id = " + FinYearId +" and submst.status = 'Active') css "+
			         "WHERE AMT.PLAN_NONPLAN = 'NP' AND AMT.LEVEL_ID = 4 AND "+
			                "AMT.GIA_FLG = 'N' AND AMT.FIN_YR_ID = " + FinYearId +" and "+
			               "(AMT.REC_FLAG NOT IN ('R', 'C', 'M') OR AMT.REC_FLAG IS NULL) AND "+
			               "AMT.FORM_TYPE NOT LIKE 'RE' and "+ 
			               "amt.buddemand_code = css.demand_code and "+
			               "amt.budmjrhd_code = css.budmjrhd_code and "+
			               "amt.budsubmjrhd_code = css.budsubmjrhd_code and "+
			               "amt.budminhd_code = css.budminhd_code and "+
			               "amt.budsubhd_code = css.budsubhd_code and "+
			               "amt.budbpn_code = css.bpn_code and "+
			               "css.budmjrhd_code between '"+StartLimit+"' and '"+EndLimit+"' "+
			         "group by amt.budmjrhd_code) ";
				
						
				glogger.info("the night query is :0- " + lsb.toString() );
				glogger.info("Query for TxtExp -- "+lsb.toString());	
				rs=lStmt.executeQuery(lsb.toString());
				glogger.info("result has been ftched bnow i ma going for");
				
				rs.next();
				
				if(total!=0)
				{
					lTotalAmount = 0;
					
					for(int z = StartLimit ; z <= EndLimit ; z++ )
					{
						
						
						if(rs.getInt("major") == z)
						{
							
						
							lHashReturn.put(z+budget_type,rs.getDouble("sum"));
							
							
							lTotalAmount += (Double.parseDouble((rs.getObject("sum").toString())));
							
		
							
							if(total!=1)
							{
								rs.next();
								total--;
							}
							
								
						 }
						 else
						 {

							lHashReturn.put(z+budget_type,0.0);
							
							
						 }
					
					
						
						
					}
					
					lHashReturn.put(budget_type+"total",lTotalAmount);
				
				}
				else
				{
					for(int z = StartLimit ; z <= EndLimit ; z++ )
					{

						lHashReturn.put(z+budget_type,0.0);
						
						
					}
					
				}
				
				
		
					
			rs.close();
			lStmt.close();
		}
		catch(Exception e)
		{
			glogger.info(e);
		}
		
		lHashReturn.put("Total_Bud", lTotalAmount );
		
		return lHashReturn;
	}
	
	
	
	
	
	public String getTodayDate()
	{
		Date d = new Date();
		glogger.info(d.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = sdf.format(d);
		glogger.info(date1);
		return date1;
	}
	
	
	public HashMap getSubFundExpGrantAmt(int StartLimit,int EndLimit,long lFinYrId ,String LangId)
	{
		HashMap<String,Double> lHashData = new HashMap<String,Double>();
		
		
		Statement lStmt = null;
		ResultSet rs = null;
		
		glogger.info("--------------Inside getGrantAmt------------>");
		double PL_Amount=0;
		double NP_Amount=0;
		double CSS_Amount=0;
		double GrantTotalAmt = 0;
		String lsb = "";
		int total = 0;
		try
		{
		
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			
			lsb = "select count(count(budmjrhd_code)) total " +
			"from sgva_budgrant_mst where" +
			" budmjrhd_code between '"+StartLimit+"' and '" +EndLimit+"' " +	
			" and fin_yr_id="+lFinYrId+" and lang_id='"+LangId+"' " +
			"group by budmjrhd_code";
	
			glogger.info("Query String for lsb is :-> " + lsb);
			rs=lStmt.executeQuery(lsb);
			rs.next();
			total = rs.getInt("total");
		
			lsb = "select sum(nonplan_amount)/10000 NP , " +
					" sum(plan_amount)/10000 PL , sum(css_amount)/10000 CSS ,  budmjrhd_code major " +
					" from sgva_budgrant_mst where " +
					" budmjrhd_code between '"+StartLimit+"' and '" +EndLimit+"' " +	
					" and fin_yr_id="+lFinYrId+" and lang_id='"+LangId+"' " + 
					" group by budmjrhd_code";
		
			glogger.info("Query String for lsb is :-> " + lsb);
			
			rs=lStmt.executeQuery(lsb.toString());
			rs.next();
			
			if(total!=0)
			{
				for(int z = StartLimit ; z <= EndLimit ; z++ )
				{
			//		glogger.info("H i iam in for loop :- " + z);
					
					if(rs.getInt("major") == z)
					{
						
			//			glogger.info("Hi i am in Ifw Loop");
						lHashData.put(z+"PL",rs.getDouble("PL"));
			//			glogger.info(z+"PL" );
						lHashData.put(z+"NP",rs.getDouble("NP"));
						lHashData.put(z+"CSS",rs.getDouble("CSS"));
				
			//			glogger.info(rs.getObject("major").toString());
						
						
						if(total!=1)
						{
							rs.next();
							total--;
						}
						
							
					}
					else
					{
		//				glogger.info("Hi i am in Else loop");
						lHashData.put(z+"PL",0.0);
	//					glogger.info("Values in else = " + z+"PL" );
						lHashData.put(z+"NP",0.0);
						lHashData.put(z+"CSS",0.0);
						lHashData.put(z+"Total",0.0);
					}
				
				}
			
			}
			else
			{
				for(int z = StartLimit ; z <= EndLimit ; z++ )
				{
		//			glogger.info("H i iam in for loop :- " + z);
		//			glogger.info("Hi i am in Else loop");
					lHashData.put(z+"PL",0.0);
		//			glogger.info("Values in else = " + z+"PL" );
					lHashData.put(z+"NP",0.0);
					lHashData.put(z+"CSS",0.0);
					lHashData.put(z+"Total",0.0);
					
				}
				
			}
			
			
			
			
			
			
			
			
			
			
			
	
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		
		return lHashData;
	}			 
	
	
	public HashMap getMjrHdWiseExpenditureData(int StartLimit,int EndLimit,int liFinYrId,String lStrFrmDate,String lStrToDate)
	{
		HashMap<String,Double> lHashReturn = null;

		glogger.info("Hi i am in getMjrHdWiseExpenditureData Fundciton");
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;
		HashMap hmp=new HashMap();
		double lLngTotalExpPL=0;
		double lLngTotalExpNPL=0;
		double lLngTotalExpCSS=0;
		double lLngTotalExp = 0;
		Integer liMjrHd = 0;
		Integer liMjrHd1 = 0;
		Integer liMjrHd2 = 0;
		String lsb=null;
		String bud_values = "";
		int total = 0;
		String budget_type = null;
		double lTotalAmount = 0.0;
		int liQueryType=0;//0-plan 1-nonplan 2-css
		if(lStrFrmDate.equals(""))
		{
			lStrFrmDate = getFromDate(lStrToDate);
		}
		
		try
		{
		
			lCon=getSession().connection();
			lStmt=lCon.createStatement();
			lHashReturn = new HashMap<String,Double>();
			
			
			
			for(int i=1 ; i<=3 ; i++)
			{
				
				if(i==1)
				{
					bud_values = "('"+lObjRsrcBndle.getString("Plan_1")+"','"+lObjRsrcBndle.getString("Plan_2")+"','"+lObjRsrcBndle.getString("Plan_3")+"')";
					budget_type = "PL";
				}
				if(i==2)
				{
					bud_values = "('"+lObjRsrcBndle.getString("NPL_1")+"','"+lObjRsrcBndle.getString("NPL_2")+"','"+lObjRsrcBndle.getString("NPL_3")+"')";
					budget_type = "NP";
				}
					
				if(i==3)
				{
					bud_values = "('"+lObjRsrcBndle.getString("CSS_1")+"','"+lObjRsrcBndle.getString("CSS_2")+"','"+lObjRsrcBndle.getString("CSS_3")+"')";
					budget_type = "CSS";
				}
				
				
				lsb = " select count(count(MJR_HD)) total from  rpt_expenditure_dtls p where "+
					  " p.exp_dt >= TO_DATE('"+lStrFrmDate+"','DD/MM/YYYY') and p.exp_dt <= TO_DATE('"+lStrToDate+"','DD/MM/YYYY') " +
					  " and p.BUD_TYPE_CODE in "+ bud_values +
					  " and MJR_HD between "+StartLimit+" and "+EndLimit+
					  " group by MJR_HD" ;
				
				
				
				glogger.info("Query for TxtExp -- "+lsb.toString());
				
				rs=lStmt.executeQuery(lsb);
				glogger.info("rs has been fetcjhed");
				rs.next();
				
				glogger.info("rs is jumped over another sentences");
				total = rs.getInt("total");
				glogger.info("total that has been fetche dis :-->" + total);
				
				lsb = " select sum(p.GROSS_AMNT - NVL(p.RECOVERY_AMT,0))/10000000 sum , MJR_HD major from  rpt_expenditure_dtls p where "+
				  " p.exp_dt >= TO_DATE('"+lStrFrmDate+"','DD/MM/YYYY') and p.exp_dt <= TO_DATE('"+lStrToDate+"','DD/MM/YYYY') " +
				  " and p.BUD_TYPE_CODE in "+ bud_values +
				  " and MJR_HD between "+StartLimit+" and "+EndLimit+
				  " group by MJR_HD" ;
			
				
				
				glogger.info("Query for TxtExp -- "+lsb.toString());	
				
				rs=lStmt.executeQuery(lsb.toString());
				glogger.info("result has been ftched bnow i ma going for");
				
				
				
				if(total!=0)
				{
					rs.next();
					lTotalAmount = 0;
					
					for(int z = StartLimit ; z <= EndLimit ; z++ )
					{
		//				glogger.info("H i iam in for loop z value:- " + z);
		//				glogger.info("H i iam in for loopmajor values is value:- " + rs.getInt("major")+" "+budget_type);
						
						if(rs.getInt("major") == z)
						{
				//			glogger.info("Hi i am in Ifw Loop");
							lHashReturn.put(z+budget_type,rs.getDouble("sum"));
							lTotalAmount += (Double.parseDouble((rs.getObject("sum").toString())));
				//			glogger.info(rs.getObject("major").toString());
				//			glogger.info(rs.getObject("sum").toString());
							if(total!=1)
							{
								rs.next();
								total--;
							}
						 }
						 else
						 {
			//				glogger.info("Hi i am in Else loop");
							lHashReturn.put(z+budget_type,0.0);
						 }
					
					}
					lHashReturn.put(budget_type+"total",lTotalAmount);
				}
				else
				{
					for(int z = StartLimit ; z <= EndLimit ; z++ )
					{
		//				glogger.info("H i iam in for loop :- " + z);
		//				glogger.info("Hi i am in Else loop");
		//				glogger.info("Budfgget type "+ z + budget_type);
						lHashReturn.put(z+budget_type,0.0);
		//				glogger.info(lHashReturn.get(z+budget_type));
					}
				}
			}		
					
	
			/*		lCon=getSession().connection();
				
				while(liQueryType<3)
				{
					lsb=new StringBuffer();
					lsb.append(" select sum(rexpd.GROSS_AMNT - rexpd.RECOVERY_AMT)/10000000 totexp , MJR_HD major from  rpt_expenditure_dtls rexpd where ");
					if(lStrFrmDate != null && !lStrFrmDate.equals(""))
					{
						lsb.append(" p.rcptdate>=TO_DATE('"+lStrFrmDate+"','DD/MM/YYYY') and p.rcptdate<=TO_DATE('"+lStrToDate+"','DD/MM/YYYY') ");
					}
					else
					{
						lsb.append(" p.rcptdate>=TO_DATE('"+getFromDate(lStrToDate)+"','DD/MM/YYYY') and p.rcptdate<=TO_DATE('"+lStrToDate+"','DD/MM/YYYY') ");
					}
					if(liQueryType==0)
					{
						lsb.append(" and  p.bud_type in (6,7,8,9) ");//plan
					}
					if(liQueryType==1)
					{
						lsb.append(" and  p.bud_type in (1,5)  ");//non plan
					}
					if(liQueryType==2)
					{
						lsb.append(" and bud_type in (2,3,4)");//css
					}
					
					
					lsb.append("and MJR_HD between '"+StartLimit+"' and '"+EndLimit+"'");
					lsb.append("group by MJR_HD");
					
					
					glogger.info("Query for TxtExp -- "+lsb.toString());
					lStmt=lCon.createStatement();
					rs=lStmt.executeQuery(lsb.toString());
					rs.next();
					while(rs.next())
					{
						if(liQueryType==0)
						{
							lLngTotalExpPL=rs.getDouble("totexp");
						}
						if(liQueryType==1)
						{
							lLngTotalExpNPL=rs.getDouble("totexp");
						}
						if(liQueryType==2)
						{
							lLngTotalExpCSS=rs.getDouble("totexp");
						}
					}
					rs.close();
					lStmt.close();
					liQueryType++;
				}
*/
			rs.close();
			lStmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		glogger.info("Bye i am going back for thia function-------------------------------");
		
		return lHashReturn;
	}
	
	public HashMap getCSSAmount(String lStrMajorHdCode,int lIntLocId,HashMap lHshPara)
	{
		glogger.info("-----Inside CSSAmount------------");
		HashMap<String,Double> lhshMjrHd = new HashMap<String,Double>();
		Connection lCon=null;
		Statement lStmt = null;
		ResultSet rs = null;
		StringBuffer lSb=new StringBuffer();
		String lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		String lStrPlanType="";
		String lStrMjrHdCode="";
		int lintLangId=0;
		int lintFinYrId = 0;
		int lintPlanType = 0;
		double ldbCSSAmt=0 ;
		
		glogger.info("--------- Loc Code in getCSSAmount is::---"+lIntLocId);
		
		if(lHshPara.get("FinYrId")!=null)
		{
			lintFinYrId = Integer.parseInt(lHshPara.get("FinYrId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lintLangId = Integer.parseInt(lHshPara.get("LangId").toString());
		}
		if(lHshPara.get("LangId")!=null)
		{
			lStrLangId = lObjRsrcBndle.getString(lHshPara.get("LangId").toString());
		}
		
		lSb.append(" SELECT F.Budmjrhd_Code,");
		lSb.append(" SUM(DECODE(PLAN_NONPLAN, 'NP', NXT_YR_BE, 0)) / 10000 CSS ");
		lSb.append(" FROM SGVA_EXPEST_FINALAMT F, ");
		lSb.append(" SGVA_BUDSUBHD_MST SUB, ");
		lSb.append(" SGVA_BUDBPN_MAPPING BPN, ");
		lSb.append(" (SELECT LOC.DEPARTMENT_ID LOC ");
		lSb.append(" FROM RLT_LOCATION_DEPARTMENT LOC ");
		lSb.append(" WHERE LOC.LOC_CODE ="+lIntLocId+" AND LOC.LANG_ID ="+lintLangId+") ");
		lSb.append(" WHERE SUB.CSS = 'Y' AND F.BUDDEMAND_CODE = SUB.DEMAND_CODE AND ");
		lSb.append(" F.BUDMJRHD_CODE = SUB.BUDMJRHD_CODE AND ");
		lSb.append(" F.BUDSUBMJRHD_CODE = SUB.BUDSUBMJRHD_CODE AND ");
		lSb.append(" F.BUDMINHD_CODE = SUB.BUDMINHD_CODE AND ");
		lSb.append(" F.BUDSUBHD_CODE = SUB.BUDSUBHD_CODE AND F.BUDBPN_CODE = SUB.BPN_CODE AND ");
		lSb.append(" SUB.STATUS = 'Active' and F.DEPT_ID = LOC AND F.LEVEL_ID = 4 AND ");
		lSb.append(" F.FIN_YR_ID = "+lintFinYrId+" AND F.FORM_TYPE <> 'RE' AND ");
		lSb.append(" (F.REC_FLAG NOT IN ('R', 'C', 'M') OR F.REC_FLAG IS NULL) AND ");
		lSb.append(" F.GIA_FLG = 'N' AND F.LANG_ID = '"+lStrLangId+"' AND SUB.LANG_ID = '"+lStrLangId+"' AND ");
		lSb.append(" SUB.FIN_YR_ID ="+lintFinYrId+" AND BPN.FIN_YR_ID = "+lintFinYrId+" AND BPN.DEPT_ID = LOC AND ");
		lSb.append(" F.BUDBPN_CODE = BPN.BPNCODE AND BPN.LANG_ID = '"+lStrLangId+"' and ");
		lSb.append(" bpn.fin_yr_id =sub.fin_yr_id and bpn.fin_yr_id="+lintFinYrId+" and bpn.status = 'Active' ");
		lSb.append(" and F.Budmjrhd_Code="+lStrMajorHdCode+" ");
		lSb.append(" GROUP BY F.Budmjrhd_Code ");
		lSb.append(" ORDER BY F.Budmjrhd_Code ");
		
		glogger.info("----CSS Query is-----"+lSb.toString());
		
		try
		{
			lCon=DBConnection.getConnection();
			lStmt=lCon.createStatement();
			rs=lStmt.executeQuery(lSb.toString());
			
			while(rs.next())
			{
				lStrMjrHdCode=rs.getString("Budmjrhd_Code");
				glogger.info("------CSS Majorhead Code is::"+lStrMjrHdCode);
				ldbCSSAmt=rs.getDouble("CSS");
				glogger.info("------CSS Amount::"+ldbCSSAmt);
				lhshMjrHd.put(lStrMjrHdCode, ldbCSSAmt);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				glogger.error("Exception in closing connection in getCSSAmount() "+e);
			}
		}
		return lhshMjrHd;
	}
	
	
	/*public String getFromDate(String DateFrom)
	{
		String lFromDate = null;
		
		if(DateFrom.equalsIgnoreCase(""))
		{
			DateFrom = getTodayDate();
		}
		
			int dd = 0 , mm = 0 , yyyy = 0;
			StringTokenizer s = new StringTokenizer(DateFrom  , "/");
			dd = Integer.parseInt(s.nextToken());
			mm = Integer.parseInt(s.nextToken());
			yyyy  = Integer.parseInt(s.nextToken());
			
			if(mm == 01 || mm == 02 || mm == 03 ) 
				yyyy--;
			lFromDate = "01/04/"+String.valueOf(yyyy);
			
			return lFromDate;
		
		
	
	
	}*/

	/* End By Maneesh*/
	
	
	public String getTreasuryName(String locid)
	{
		String treasury_name = "";
		Connection con = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet rst = null;
		
		glogger.info("hi i am in getTReasruName report-----------------------------");
		try
		{
			String query = "select loc_name from cmn_location_mst where location_code = " + locid;
			
			glogger.info("the getTreasuryname query is : - " + query);
			stmt = con.createStatement();
			rst = stmt.executeQuery(query);
			
			while(rst.next())
			{
				treasury_name = rst.getString("loc_name");
				glogger.info("the name of the treasury is :- " + treasury_name );
			}
		}
		catch(Exception e)
		{
			glogger.error(e);
		}
		
		
		return treasury_name; 
	}
	
	public String getDDOName(String ddoid)
	{
		String ddo_name = "";
		Connection con = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet rst = null;
		
		glogger.info("hi i am in getTReasruName report-----------------------------");
		try
		{
			String query = "select ddo_name from org_ddo_mst where ddo_id = " + ddoid;
			
			glogger.info("the getddoname query is : - " + query);
			stmt = con.createStatement();
			rst = stmt.executeQuery(query);
			
			while(rst.next())
			{
				ddo_name = rst.getString("loc_name");
				glogger.info("the name of the ddo is :- " + ddo_name );
			}
		}
		catch(Exception e)
		{
			glogger.error(e);
		}
		
		
		return ddo_name; 
	}
}



