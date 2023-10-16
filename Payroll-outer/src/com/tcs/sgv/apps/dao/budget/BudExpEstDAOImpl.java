/*
 * PACKAGE         : com.tcs.sgv.apps.dao.budget
 * filename        : BudExpEstDAOImpl. java
 * @verion         : 1.0
 * @author         : Keyur Shah
 * date            : 01/12/2005
 * description     : This is the Implementation class of BudExpEstDAOImpl
 *-----------------------------------------------------------------------------
 */
package com.tcs.sgv.apps.dao.budget;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.valuebeans.budget.BudExpEstDeptVO;
import com.tcs.sgv.common.util.DBConnection;


/**
 * It implements the methods of DAO.
 *
 * @author Keyur Shah
 * @version 1.0
 */
public class BudExpEstDAOImpl implements BudExpEstDAO
{
    /** Declaring the Logger Class */
    private static final Logger glogger = Logger.getLogger(BudExpEstDAOImpl.class);

    /** [TODO Add field documentation here]*/
    private Connection conn = null;
    
    /**
     * Constructor with parameter jdbc Connection
     * @param conn
     */
    public BudExpEstDAOImpl(SessionFactory sessionFactory) {
    	if (sessionFactory!=null)
    		this.conn = sessionFactory.getCurrentSession().connection();
    }
    
    public BudExpEstDAOImpl()
    {
    	
    }
    
    /**
     * It gives all the Departments based on Language ID.
     *
     * @param lStrLangId Language ID
     *
     * @return ArrayList containing list of all the Departments.
     *
     * @throws SQLException SQL Exception
     * @throws Exception Exception
     */
    public ArrayList getAllDept(String lStrLangId) throws SQLException, Exception
    {
        Connection lcon = null;

        PreparedStatement lPstmt = null;
        ResultSet lrs = null;
        StringBuffer lsb = null;
        lsb = new StringBuffer();

        ArrayList lArrDeptList = new ArrayList();

        try
        {
            if(lcon == null)
            {
                lcon = getConnection();
            }

            // Fetching Dept Short Name - Changed by Keyur
            lsb.append(" SELECT D.DEPT_ID DEPT_ID, D.DEPT_NAME DEPT_NAME, D.DEPT_SHRT_NAME DEPT_SHRT_NAME  ");

            //Start:Commented by Ravi to avoid non-Secretariat departments
            //lsb.append(" WHERE D.LANG_ID = ?  ORDER BY D.DEPT_NAME");
            //End:Commented by Ravi to avoid non-Secretariat departments
            //Start:Added by Ravi to avoid non-Secretariat departments
            lsb.append(" FROM SGVO_DEPT_MST D WHERE D.LANG_ID = ? AND D.DEPT_TYPE='SEC' AND DEPT_ID NOT IN ('DP5','DP101','DP45') ORDER BY D.DEPT_NAME");

            //End:Added by Ravi to avoid non-Secretariat departments
            lPstmt = lcon.prepareStatement(lsb.toString());
            lPstmt.setString(1, lStrLangId);
            lrs = lPstmt.executeQuery();
            glogger.debug("Query for getAllDept is " + lsb.toString());

            while(lrs.next())
            {
                BudExpEstDeptVO lobjBudExpEstDeptVO = new BudExpEstDeptVO();
                lobjBudExpEstDeptVO.setDeptID(lrs.getString("DEPT_ID"));
                lobjBudExpEstDeptVO.setDeptName(lrs.getString("DEPT_NAME"));
                lobjBudExpEstDeptVO.setDeptShrtName(lrs.getString("DEPT_SHRT_NAME"));
                lArrDeptList.add(lobjBudExpEstDeptVO);
            }
        }
        catch(SQLException sqle)
        {
            glogger.error("Error occured in Method getAllDept" + sqle, sqle);
            sqle.printStackTrace();
            throw sqle;
        }
        finally
        {
            closeResultSet(lrs);
            closeStatement(lPstmt);
            closeConnection(lcon);
        }

        return lArrDeptList;
    }
    
    public ArrayList getAllDeptRpt(String lStrLangId, String lstrLocId) throws SQLException, Exception
    {
        ArrayList lArrDept = new ArrayList();
        lArrDept = getAllDept(lStrLangId);

        return lArrDept;
    }

    
	/**
	 * This method close a jdbc connection
	 * @param con
	 */
    private Connection getConnection() {
    	if(conn == null)
    	{
    		conn = DBConnection.getConnection();
    	}
    	return conn;
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
    
}
