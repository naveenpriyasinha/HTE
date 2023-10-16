/*
   * PACKAGE         : com.tcs.sgv.apps.dao.budget
   * filename        : BudFinYrDAOImpl. java
   *@version         : 1.0
   * @author         : Payal Patel
   *date             : 17/11/2005
   * description     : This is the Implementation class of BudFinYrDAO
   *-----------------------------------------------------------------------------
*/


package com.tcs.sgv.apps.dao.budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.tcs.sgv.apps.valuebeans.budget.BudFinYrVO;
import com.tcs.sgv.common.util.DBConnection;

/**
 * @author Payal Patel
 * @version 1.0
 */
public class BudFinYrDAOImpl implements BudFinYrDAO
{
    /**
    * Declaring the Logger Class
    */
    private static final Logger glogger=Logger.getLogger(BudFinYrDAOImpl.class);  
    private static ResourceBundle lBudConstantsBundle= ResourceBundle.getBundle("resources/common/budget/SGVConstantsBud", Locale.getDefault());
    private static String lStrLangID = lBudConstantsBundle.getString("RCPTEST.TXNLangID");
    private static String lStrLocID = lBudConstantsBundle.getString("RCPTEST.TXNLocID");
//******************************* Financial Year *****************************************
/**
* method getFinYr
* @return ArrayList of Financial Year
*/

  public ArrayList getFinYr() throws SQLException
  {
    /*Connection lcon = null;
    PreparedStatement lPstmt = null;        
    ResultSet lrs = null;
    StringBuffer lsb=null;*/
    ArrayList lArrfinYr= new ArrayList();
//    lsb=new StringBuffer();
    int noOfPreviousYear = Integer.parseInt(lBudConstantsBundle.getString("EXP_PRV_FIN_YEAR"));
    int noOfNextYear = Integer.parseInt(lBudConstantsBundle.getString("EXP_NXT_FIN_YEAR"));    
    
//    BudFinYrVO bvo=null;
    
       try
        {
/*          if(lcon ==null)
          {
             lcon = DBConnection.getConnection();
          }*/

/*
 * 
 * SELECT FIN.FIN_YEAR_ID, FIN.FIN_YEAR_DESC, FIN.FIN_YEAR_CODE
  FROM SGVC_FIN_YEAR_MST FIN WHERE FIN.LANG_ID= ?  AND FIN.LOC_ID=UPPER(?) AND FIN.FIN_YEAR_CODE BETWEEN ? AND ?
 ORDER BY TO_NUMBER(FIN.FIN_YEAR_CODE) DESC
          
          lsb.append("SELECT F.FIN_YEAR_ID ,F.FIN_YEAR_DESC,F.FIN_YEAR_CODE ");
          lsb.append(" FROM SGVC_FIN_YEAR_MST F WHERE F.LANG_ID= ?  AND F.LOC_ID=UPPER(?) AND FIN.FIN_YEAR_CODE BETWEEN ? AND ?");
          lsb.append(" ORDER BY TO_NUMBER(F.FIN_YEAR_CODE) DESC");
          
          lPstmt= lcon.prepareStatement(lsb.toString());
          lPstmt.setString(1,lStrLangID.trim());
          lPstmt.setString(2,lStrLocID.trim());
                                
          lrs=lPstmt.executeQuery();
          
          while(lrs.next())
          {
            bvo=new BudFinYrVO(); 
            if(lrs.getInt("FIN_YEAR_ID")!=0)
            {
              bvo.setFinYrID(lrs.getInt("FIN_YEAR_ID"));
            }
            if(lrs.getString("FIN_YEAR_DESC")!=null)
            {
              bvo.setFinYrDesc(lrs.getString("FIN_YEAR_DESC"));
            }
            bvo.setFinYrCode(lrs.getString("FIN_YEAR_CODE"));
            lArrfinYr.add(bvo);
          }*/
        if(glogger.isDebugEnabled())
        {
          glogger.debug("No Of Previous Year(s) From Current Year::"+noOfPreviousYear);
          glogger.debug("No Of Next Year(s) From Current Year::"+noOfNextYear);
        }
        lArrfinYr = getFinancialYears(noOfPreviousYear,noOfNextYear);
        
        }
        catch(SQLException sqle)
        {
          glogger.error("Error occured in getFinYr() ,Method of BudFinYrDAOImpl"+sqle);
          throw sqle;
        }


    return lArrfinYr;
  }
  /* BELOW IS THE METHOD CALL FROM REPORT FRAMEWORK*/
  public ArrayList getFinanceYr(String lStrLangID, String lStrLocID) throws SQLException
  {
    ArrayList lArrRetFinYr  = new ArrayList();
    lArrRetFinYr = getFinYr();
    return lArrRetFinYr;
  }
 /**
* method getFinYrDesc
* @return Hashtable of Financial Year
*/
 
  // Added By Ronak Shah on 25/05/06 : moved by Vinay Tanwani 28/06/2006
    public Hashtable getFinYrDesc(long lngCurYr) throws SQLException
    {
        Connection lcon = null;
        PreparedStatement lPstmt = null;
        StringBuffer lsb = new StringBuffer();
        ResultSet lRs = null;
        Hashtable hsFinYr = null;

        try
        {
            lcon = DBConnection.getConnection();

            /*
               -- FOR GETTING Financial Year Description for next and pervious yr Vinay
               SELECT FM.FIN_YEAR_DESC CUR,
               (SELECT FIN_YEAR_DESC
               FROM SGVC_FIN_YEAR_MST
               WHERE FIN_YEAR_ID = FM.PREV_FIN_YEAR_ID) AS PRV,
               (SELECT FIN_YEAR_DESC
               FROM SGVC_FIN_YEAR_MST
               WHERE FIN_YEAR_ID = FM.NEXT_FIN_YEAR_ID) AS NEXT
               FROM SGVC_FIN_YEAR_MST FM
               WHERE FM.FIN_YEAR_ID = ?
             */
             
             /*
              *-- FOR GETTING Financial Year Description for next and pervious yr Purav 
              * 
              * 
              * 
              * select FIN2.fin_year_desc PrevYear,//2006-2007
                FIN1.fin_year_desc CurrYear,//2007-2008
                FIN.fin_year_desc  NextYear //2008-2009

                from SGVC_FIN_YEAR_MST FIN,
                SGVC_FIN_YEAR_MST FIN1,
                SGVC_FIN_YEAR_MST FIN2

                where FIN1.fin_year_id = FIN.prev_fin_year_id and
                FIN2.fin_year_id = FIN1.prev_fin_year_id and FIN.fin_year_id = 21 // 2008-2009
              * */
            
                lsb.append("select FIN2.fin_year_desc PRV,");
                lsb.append("FIN1.fin_year_desc CUR,");
                lsb.append("FIN.fin_year_desc NXT");

                lsb.append(" from SGVC_FIN_YEAR_MST FIN,");
                lsb.append("SGVC_FIN_YEAR_MST FIN1,");
                lsb.append("SGVC_FIN_YEAR_MST FIN2");

                lsb.append(" where FIN1.fin_year_id = FIN.prev_fin_year_id and ");
                lsb.append("FIN2.fin_year_id = FIN1.prev_fin_year_id and ");
                
                lsb.append("FIN.fin_year_id = ?");

            lPstmt = lcon.prepareStatement(lsb.toString());

            int iCnt = 0;
            lPstmt.setLong(++iCnt, lngCurYr); // Fin Year Id

            lRs = lPstmt.executeQuery();

            while(lRs.next())
            {
                hsFinYr = new Hashtable();
                hsFinYr.put("PRV_YR", (lRs.getString("PRV") != null) ? lRs.getString("PRV") : "");
                hsFinYr.put("CUR_YR", (lRs.getString("CUR") != null) ? lRs.getString("CUR") : "");
                hsFinYr.put("NXT_YR", (lRs.getString("NXT") != null) ? lRs.getString("NXT") : "");
            }
        }
        catch(SQLException sqle)
        {
            glogger.error("Error in getFinYrDesc method  " + sqle);
            throw sqle;
        }
        finally
        {
            closeStatement(lPstmt);
            closeResultSet(lRs);
            closeConnection(lcon);
        }

        return hsFinYr;
    }

    /**
     * Returns an array of financial years starting from 
     * (current financial year - previous) to (current financial year - next).
     * 
     * @param previous number of previous financial year from current
     * @param next number of next financial years from current
     * @return ArrayList list of financial years
     */
    private ArrayList getFinancialYears( int previous, int next ) throws SQLException
    {
        Date today = new Date();
        Calendar startCD = Calendar.getInstance();
        Calendar stopCD = Calendar.getInstance();
        startCD.setTime( today );
        stopCD.setTime( today );
        startCD.add( Calendar.YEAR, (0-previous) );
        stopCD.add( Calendar.YEAR, next );
        
        ArrayList lArrFinYearList = new ArrayList(  );
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        BudFinYrVO budFinYrVO = null;

        try
        {
            lCon = DBConnection.getConnection(  );
            
            StringBuffer lsb = new StringBuffer(  );

            /* sample query to run in plsql
            select (sysdate-730), t.* 
            from sgvc_fin_year_mst t
            where ((sysdate-730) between t.from_date and t.to_date)
            or ((sysdate+730) between t.from_date and t.to_date)
            or ((sysdate-730) < t.from_date and t.to_date < (sysdate+730))
            */
            lsb = new StringBuffer( "SELECT t.fin_year_id, t.fin_year_desc, " );
            lsb.append( "t.fin_year_code " );
            lsb.append( "FROM sgvc_fin_year_mst t " );
            lsb.append( "WHERE t.LANG_ID= ?  AND t.LOC_ID=UPPER(?) AND (? between t.from_date and t.to_date) " );
            lsb.append( "OR (? BETWEEN t.from_date AND t.to_date) " );
            lsb.append( "OR (? < t.from_date AND t.to_date < ?) " );
            lsb.append( "ORDER BY FIN_YEAR_CODE DESC" );
            
            glogger.info( "Query: Financial Year::" + lsb.toString(  ) );
            java.sql.Date startDate = new java.sql.Date( startCD.getTimeInMillis() );
            java.sql.Date stopDate = new java.sql.Date( stopCD.getTimeInMillis() );
            
            lStmt = lCon.prepareStatement( lsb.toString() );
            lStmt.setString(1,lStrLangID.trim());
            lStmt.setString(2,lStrLocID.trim());
            lStmt.setDate( 3, startDate );
            lStmt.setDate( 4, stopDate );
            lStmt.setDate( 5, startDate );
            lStmt.setDate( 6, stopDate );
            
            lRs = lStmt.executeQuery();

            int lIntFinYearID = 0;
            String lStrFinYearDesc = "";

            while( lRs.next(  ) )
            {
                budFinYrVO = new BudFinYrVO(  );
                lIntFinYearID = lRs.getInt( "fin_year_id" );
                lStrFinYearDesc = lRs.getString( "fin_year_desc" );
                budFinYrVO.setFinYrID( lIntFinYearID );
                budFinYrVO.setFinYrDesc( lStrFinYearDesc );
                lArrFinYearList.add( budFinYrVO );
            }
        }
        catch( SQLException se )
        {
            glogger.error( "SQLException::"+se.getMessage(), se );
            throw se;
        }
        catch( Exception e )
        {
            glogger.error( "Exception::"+e.getMessage(), e );
        }
        finally
        {
            closeResultSet(lRs);
            closeStatement(lStmt);
            closeConnection(lCon);
            lRs = null; lStmt = null; lCon = null;
        }

        return lArrFinYearList;
    }
    
	/**
	 * This method close a jdbc resultset
	 * @param con
	 */
	private void closeResultSet(ResultSet rs) {
		try{
			rs.close();
		} catch(Exception ex){			
		}
	}

	/**
	 * This method close a jdbc statement
	 * @param con
	 */
	private void closeStatement(Statement stmt) {
		try{
			stmt.close();
		} catch(Exception ex){			
		}
	}

	/**
	 * This method close a database connection
	 * @param con
	 */
	private void closeConnection(Connection con) {
		try{
			con.close();
		} catch(Exception ex){			
		}
	}

	public Map getFinYrInfo(int lIntFinYrId) throws SQLException,Exception
    {
        
        Connection lCon = null;
        PreparedStatement lPstmt = null;
        ResultSet lRs = null;
        StringBuffer lsb = new StringBuffer();
        
        Map<String,Object> lHashFinYrInfo = new HashMap<String,Object>();
        
        try
        {
            lCon = DBConnection.getConnection(  );
            lsb.append(" select fin_year_id, from_date, to_date, fin_year_desc, " +
                    " fin_year_code, prev_fin_year_id, next_fin_year_id from sgvc_fin_year_mst" +
                    " where fin_year_id = ? ");
            
            lPstmt = lCon.prepareStatement( lsb.toString());
            lPstmt.setInt(1,lIntFinYrId);
            
            lRs = lPstmt.executeQuery();
            
            if(lRs.next())
            {
                lHashFinYrInfo.put("FIN_YR_ID", lRs.getInt("fin_year_id"));
                lHashFinYrInfo.put("FROM_DATE", lRs.getDate("from_date"));
                lHashFinYrInfo.put("TO_DATE", lRs.getDate("to_date"));
                lHashFinYrInfo.put("FIN_YR_DESC", lRs.getString("fin_year_desc"));
                lHashFinYrInfo.put("FIN_YR_CODE", lRs.getString("fin_year_code"));
                lHashFinYrInfo.put("PREV_FIN_YR", lRs.getString("prev_fin_year_id"));
                lHashFinYrInfo.put("NXT_FIN_YR", lRs.getString("next_fin_year_id"));
            }
        }
        catch(SQLException se)
        {
            glogger.error("SQL Error in executing method and error is : " + se,se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("SQL Error in executing method and error is : " + e,e);
            throw e;
        }
        finally
        {
            closeResultSet(lRs);
            closeStatement(lPstmt);
            closeConnection(lCon);
        }
        
        return lHashFinYrInfo;
    }
	
	
}// end of class