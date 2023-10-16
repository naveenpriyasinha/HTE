package com.tcs.sgv.common.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.util.IWDMSDBConnection;


/**
 * 
 * @author 602399
 * 
 */

public class GrantDtlDAOImpl implements GrantDtlDAO
{
	Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;


	public GrantDtlDAOImpl(SessionFactory sessionFactory)
	{
		ghibSession = sessionFactory.getCurrentSession();
	}

	public BigDecimal getGrantAmtForDDO(Long lLngFinYrId, String lStrPLNPL,
			String lStrDDOCode, String lStrDemandCode, String lStrBudMjrHd,
			String lStrBudSubMjrHd, String lStrBudMinHd, String lStrBudSubHd)
	{
		//System.out.println("Inside GetGranAmt............");
		BigDecimal lBgDcmlGrntAmt = new BigDecimal("0");

		Connection lCon = null;
		PreparedStatement lPstmt = null;
		ResultSet lRs = null;
		StringBuilder lSb = new StringBuilder();

		try
		{
			lCon = IWDMSDBConnection.getConnection();

			lSb.append(" SELECT NVL(SUM(S.GRANT_AMOUNT_STATE),0) GRANT_SUM FROM SGVA_GRANT_ORDER_DETAIL S");
			lSb.append(" WHERE S.FIN_YEAR_ID = ? AND S.PLAN_NONPLAN = ? AND S.TO_DDO_CODE = ?");
			lSb.append(" AND S.DEMAND_CODE = ? AND S.BUDMJRHD_CODE = ? AND S.BUDSUBMJRHD_CODE = ? AND");
			lSb.append(" S.BUDMINHD_CODE = ? AND S.BUDSUBHD_CODE = ? AND s.GRANT_RELEASE_STATUS = 'R'");

			lPstmt = lCon.prepareStatement(lSb.toString());

			lPstmt.setLong(1, lLngFinYrId);
			lPstmt.setString(2, lStrPLNPL);
			lPstmt.setString(3, lStrDDOCode);
			lPstmt.setString(4, lStrDemandCode);
			lPstmt.setString(5, lStrBudMjrHd);
			lPstmt.setString(6, lStrBudSubMjrHd);
			lPstmt.setString(7, lStrBudMinHd);
			lPstmt.setString(8, lStrBudSubHd);
			gLogger.info("Query for getGrantAmtForDDO : " +lSb.toString());
			gLogger.info("Parameters are : -" +lLngFinYrId +"-" +lStrPLNPL +"-" +lStrDDOCode +"-" +lStrDemandCode
					+"-" +lStrBudMjrHd +"-" +lStrBudSubMjrHd +"-" +lStrBudMinHd +"-" +lStrBudSubHd);
			lRs = lPstmt.executeQuery();

			if(lRs.next())
			{
				lBgDcmlGrntAmt = lRs.getBigDecimal("GRANT_SUM");
			}
		}
		catch(SQLException se)
		{
			gLogger.error("SQL Error in getGrantAmt. Error is : " + se,se);
		}
		catch(Exception e)
		{
			gLogger.error("Error in getGrantAmt. Error is : " + e,e);
		}
		finally
		{
			try
			{
				lRs.close();
				lPstmt.close();
				lCon.close();

				lRs = null;
				lPstmt = null;
				lCon = null;
			}
			catch(SQLException se)
			{
				gLogger.error("Error in closing DB Resources in getGrantAmt. Error is : " + se,se);
			}
		}

		return lBgDcmlGrntAmt;
	}

	public double getGrantAmtForMjrHd(int lLngFinYrId, String lStrPLNPL,
			ArrayList larrMjrHdRange)
	{
		//System.out.println("Inside GetGranAmtForMjrHd............");
		double lBgDcmlGrntAmt = 0.0;

		Connection lCon = null;
		Statement stmt = null;
		ResultSet lRs = null;
		StringBuilder lsb = new StringBuilder();

		try
		{
			lCon = IWDMSDBConnection.getConnection();

			lsb.append(" select sum(GRANT_SUM) sum from (SELECT NVL(SUM(S.GRANT_AMOUNT_STATE)/10000, 0) GRANT_SUM ");
			lsb.append(" FROM SGVA_GRANT_ORDER_DETAIL S ");
			lsb.append(" WHERE S.FIN_YEAR_ID = "+ lLngFinYrId +" AND S.PLAN_NONPLAN = '" + lStrPLNPL +"' AND  ");
			if(larrMjrHdRange.size()==2)
			{
				lsb.append(" budmjrhd_code between '"+larrMjrHdRange.get(0)+"' and '" +larrMjrHdRange.get(1)+"' ");
			}
			else
			{
				lsb.append(" budmjrhd_code = '"+larrMjrHdRange.get(0)+"' ");	
			}
			lsb.append( " AND ");
			lsb.append(" s.GRANT_RELEASE_STATUS = 'R' " );
			lsb.append(" group by S.BUDMJRHD_CODE) ");
			stmt = lCon.createStatement();

			gLogger.info("Query for getGrantAmtForMjrHd : " +lsb.toString());
			gLogger.info("Parameters are : -" +lLngFinYrId +"-" +lStrPLNPL +"-" + larrMjrHdRange);

			lRs = stmt.executeQuery(lsb.toString());

			if(lRs.next())
			{

				lBgDcmlGrntAmt = lRs.getDouble("sum");
			}
			gLogger.info("The grant amount for " +  lStrPLNPL +" is :- " + lBgDcmlGrntAmt);

		}
		catch(SQLException se)
		{
			gLogger.error("SQL Error in getGrantAmt. Error is : " + se,se);
		}
		catch(Exception e)
		{
			gLogger.error("Error in getGrantAmt. Error is : " + e,e);
		}
		finally
		{
			try
			{
				lRs.close();
				stmt.close();
				lCon.close();

				lRs = null;
				stmt = null;
				lCon = null;
			}
			catch(SQLException se)
			{
				gLogger.error("Error in closing DB Resources in getGrantAmt. Error is : " + se,se);
			}
		}

		return lBgDcmlGrntAmt;
	}


	public double getGrantAmtForMjrHdWise(int lLngFinYrId, String lStrPLNPL,
			ArrayList larrMjrHdRange)
	{
		//System.out.println("Inside GetGranAmtForMjrHd............");
		double lBgDcmlGrntAmt = 0.0;

		Connection lCon = null;
		Statement stmt = null;
		ResultSet lRs = null;
		StringBuilder lsb = new StringBuilder();

		try
		{
			lCon = IWDMSDBConnection.getConnection();

			lsb.append(" select sum(GRANT_SUM) sum from (SELECT NVL(SUM(S.GRANT_AMOUNT_STATE)/10000, 0) GRANT_SUM ");
			lsb.append(" FROM SGVA_GRANT_ORDER_DETAIL S ");
			lsb.append(" WHERE S.FIN_YEAR_ID = "+ lLngFinYrId +" AND S.PLAN_NONPLAN = '" + lStrPLNPL +"' AND  ");
			if(larrMjrHdRange.size()==2)
			{
				lsb.append(" budmjrhd_code between '"+larrMjrHdRange.get(0)+"' and '" +larrMjrHdRange.get(1)+"' ");
			}
			else
			{
				lsb.append(" budmjrhd_code = '"+larrMjrHdRange.get(0)+"' ");	
			}
			lsb.append( " AND ");
			lsb.append(" s.GRANT_RELEASE_STATUS = 'R' " );
			lsb.append(" group by S.BUDMJRHD_CODE) ");
			stmt = lCon.createStatement();

			gLogger.info("Query for getGrantAmtForMjrHd : " +lsb.toString());
			gLogger.info("Parameters are : -" +lLngFinYrId +"-" +lStrPLNPL +"-" + larrMjrHdRange);

			lRs = stmt.executeQuery(lsb.toString());

			if(lRs.next())
			{

				lBgDcmlGrntAmt = lRs.getDouble("sum");
			}
			gLogger.info("The grant amount for " +  lStrPLNPL +" is :- " + lBgDcmlGrntAmt);

		}
		catch(SQLException se)
		{
			gLogger.error("SQL Error in getGrantAmt. Error is : " + se,se);
		}
		catch(Exception e)
		{
			gLogger.error("Error in getGrantAmt. Error is : " + e,e);
		}
		finally
		{
			try
			{
				lRs.close();
				stmt.close();
				lCon.close();

				lRs = null;
				stmt = null;
				lCon = null;
			}
			catch(SQLException se)
			{
				gLogger.error("Error in closing DB Resources in getGrantAmt. Error is : " + se,se);
			}
		}

		return lBgDcmlGrntAmt;
	}

	public HashMap getMjrHDWiseGrantData(int StartLimit,int EndLimit,int FinYearId,String lStrPlNpl)
	{

		gLogger.info("hi i am in  getMjrHDWiseGrantData Data Function");
		gLogger.info("hi i am in  getMjrHDWiseGrantData Data Function");

		HashMap<String,Double> lHashReturn = new HashMap<String,Double>(); 

		Connection lCon = null;
		Statement lStmt = null;
		ResultSet lRs = null;
		StringBuilder lsb = new StringBuilder();

		double lTotalAmount = 0.0;
		int total = 0;

		try
		{	
			lCon = IWDMSDBConnection.getConnection();
			lStmt=(Statement)lCon.createStatement();
			gLogger.info("connection has been fetched");

			lsb.append(" SELECT count(count(budmjrhd_code )) total ");
			lsb.append(" FROM SGVA_GRANT_ORDER_DETAIL S ");
			lsb.append(" WHERE S.FIN_YEAR_ID = "+ FinYearId +" AND S.PLAN_NONPLAN = '" + lStrPlNpl +"' AND  ");
			lsb.append(" budmjrhd_code between '"+StartLimit+"' and '" +EndLimit+"' ");
			lsb.append( " AND ");
			lsb.append(" s.GRANT_RELEASE_STATUS = 'R' " );
			lsb.append(" group by S.BUDMJRHD_CODE ");

			gLogger.info("Query for  getMjrHDWiseGrantData counter -- "+lsb.toString());
			lRs=lStmt.executeQuery(lsb.toString());
			lRs.next();
			total = lRs.getInt("total");
			
			lsb = new StringBuilder();

			lsb.append(" SELECT NVL(SUM(S.GRANT_AMOUNT_STATE)/10000, 0) sum ,  budmjrhd_code major ");
			lsb.append(" FROM SGVA_GRANT_ORDER_DETAIL S ");
			lsb.append(" WHERE S.FIN_YEAR_ID = "+ FinYearId +" AND S.PLAN_NONPLAN = '" + lStrPlNpl +"' AND  ");
			lsb.append(" budmjrhd_code between '"+StartLimit+"' and '" +EndLimit+"' ");
			lsb.append( " AND ");
			lsb.append(" s.GRANT_RELEASE_STATUS = 'R' " );
			lsb.append(" group by S.BUDMJRHD_CODE ");

			gLogger.info("the night query   getMjrHDWiseGrantData is :0- " + lsb.toString() );
			gLogger.info("Query for  getMjrHDWiseGrantData -- "+lsb.toString());	
			lRs=lStmt.executeQuery(lsb.toString());
			gLogger.info("result has been ftched bnow i ma going for");

			lRs.next();

			if(total!=0)
			{
				lTotalAmount = 0;

				for(int z = StartLimit ; z <= EndLimit ; z++ )
				{
					if(lRs.getInt("major") == z)
					{
						lHashReturn.put(z+lStrPlNpl,lRs.getDouble("sum"));
						lTotalAmount += (Double.parseDouble((lRs.getObject("sum").toString())));

						if(total!=1)
						{
							lRs.next();
							total--;
						}
					}
					else
					{
						lHashReturn.put(z+lStrPlNpl,0.0);
					}
				}

				lHashReturn.put(lStrPlNpl+"total",lTotalAmount);

			}
			else
			{
				for(int z = StartLimit ; z <= EndLimit ; z++ )
				{
					lHashReturn.put(z+lStrPlNpl,0.0);
				}
			}

			lRs.close();
			lStmt.close();

		}
		catch(Exception e)
		{
			gLogger.info(e);
		}

		lHashReturn.put("Total_Bud", lTotalAmount );

		return lHashReturn;
	}
	
	public long getGrantPeriod(String todate,long lFinYearId)
	{
		long longGrantPeriod = 0;
		long current_year = 0;
		
		int month = 0;
		int final_month = 0;
		int last_day = 0;
		
		
		String month_code = null;
		String lStr_to_date = null;
		
		Date lDatePrgDate = null;
		Date lToDate = null;
		Date lFromDate = null;
		Connection lCon = null;
		Statement lStmt = null;
		ResultSet lRs = null;
		
		//System.out.println("-------------Inside the getGrantPeriod method of GrantDtlDAOImpl.............................");
		try
		{
			//System.out.println("------Inside Try----------");
			lCon = IWDMSDBConnection.getConnection();
			
			
			ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dss/DSSConstants");
			SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy");
			lDatePrgDate = (Date)sdf.parse(todate);
			//System.out.println("----------Progessive Date----------------"+lDatePrgDate);
			
			GregorianCalendar lGCPrgfrmDate= new GregorianCalendar();
			lGCPrgfrmDate.setTime(lDatePrgDate);
			month = lGCPrgfrmDate.get(lGCPrgfrmDate.MONTH);
			//System.out.println("----------Month is-----------"+month);
			
			//set monthcode from the properties file.
			
			month_code = gObjRsrcBndle.getString(String.valueOf(month));
			//System.out.println("---------Month_code is------------"+month_code);
			
			//Run the query
			//System.out.println("---------Before Query-----------");
			
			String lStrQuery = " select month_no M from sgva_month_mst where month_code = 'MON'||(select max(code) ppt"+
					  " from (select (to_number(ltrim(smm.month_code, 'MON'))) code "+
					          " from sgva_month_mst smm "+
					         " where smm.month_code in "+
					               " (select sgod.to_month_code "+
					                  " from sgva_grant_order_detail sgod "+
					                 " where ((select to_number(ltrim(smm.month_code, 'MON')) "+
					                           " from sgva_month_mst smm "+ 
					                          " where month_code = sgod.from_month_code and "+
					                                " smm.lang_id = 'en_US') <= "+
					                        " (select to_number(ltrim('"+ month_code +"', 'MON')) from dual) and "+
					                       " sgod.fin_year_id ="+lFinYearId+")))) and lang_id = 'en_US' ";
			//System.out.println("----------After Query--------------");
			
			lStmt = lCon.createStatement();

			//System.out.println(" Query for getGrantPeriod parth 19th october: " +lStrQuery.toString());
		

			lRs = lStmt.executeQuery(lStrQuery.toString());

			if(lRs.next())
			{
		
				//System.out.println("Inside the Resultset 's calcultion------------------ for final month---------");
				
				//System.out.println("-------AGAGAGAAF----------"+lRs.getObject("M").toString());
				
				final_month = Integer.parseInt(lRs.getObject("M").toString());
				
				//System.out.println("-------Month_No  is------ : " + final_month);
			}
			
			
			lStrQuery = " select fin_year_code from sgvc_fin_year_mst where fin_year_id ="+ lFinYearId;
			
			//System.out.println("Query for get finyear  parth 19th october second: " +lStrQuery.toString());
			
			lStmt = lCon.createStatement();
			lRs = lStmt.executeQuery(lStrQuery.toString());
			//System.out.println("result set has been fetched -------------------------");
			
			if(lRs.next())
			{
				//System.out.println("inside if of Result set---------------------------------------");
				current_year = Integer.parseInt(lRs.getObject("fin_year_code").toString());
				//System.out.println("current_year value is :- " + current_year);
			}
			
			
			
			//generate the fromdate & to_date
			
			
			lFromDate = (Date)sdf.parse("01/04/"+current_year);
			
			//System.out.println("the iFromDate value is :- " + lFromDate );
			
			if(final_month == 1 || final_month == 2 || final_month == 3)
			{
				
				current_year = current_year +1;
			}
			
			
			if(final_month == 2)
			{
				//check for the Leap Year
				last_day = 29;
				//else last_day = 28
				
			}
			else
			{
				if(final_month == 1 || final_month == 3 || final_month == 5 || final_month == 7 || final_month == 8 || final_month == 10 || final_month == 12 )
					last_day = 31;
				else
					last_day = 30;
			}
			
			
			lToDate = (Date)sdf.parse(last_day+"/"+ final_month +"/"+current_year);
			
			
			
			// calculate the total days.
			
			//System.out.println("----------------------------------------------------------------------");
			//System.out.println("the date todate := " + lToDate + " and the from date :- " + lFromDate );
			//System.out.println("the total grand period is :- " + longGrantPeriod );
			//System.out.println("-------------------------------------------------------------------------");
			
			
			
			longGrantPeriod = (lToDate.getTime() - lFromDate.getTime()) / (1000*60*60*24);
		
			
			
		}
		catch(Exception e)
		{
			gLogger.error("Error in getGrantAmt. Error is : " + e);
			//System.out.println(e);
		}
		
		
		
		
		
		
		
		return longGrantPeriod;
		
	}
	
	
}
