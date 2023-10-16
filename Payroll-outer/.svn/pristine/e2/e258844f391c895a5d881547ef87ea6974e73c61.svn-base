/*
 * PACKAGE            : com.tcs.sgv.apps.dao.budget
 * filename           : BudHdDAOImpl. java
 * @version           : 1.0
 * @author            : Ravishankara B. , Keyur Shah , Payal Patel
 * Date               : 02/12/2005
 * Description        : This is the Implementation class of BudHdDAO
 *-----------------------------------------------------------------------------
 */
package com.tcs.sgv.apps.dao.budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.apps.util.budget.BudConstants;
import com.tcs.sgv.apps.valuebeans.budget.BudBranchVO;
import com.tcs.sgv.apps.valuebeans.budget.BudDeptVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstBPNCodeVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstDeptVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstDmdVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstGadVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstGrpMnrVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstMnrHdVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstOfficeVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstSbHdVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstShareVO;
import com.tcs.sgv.apps.valuebeans.budget.BudHodVO;
import com.tcs.sgv.apps.valuebeans.budget.BudMjrHdVO;
import com.tcs.sgv.apps.valuebeans.budget.BudObjHdVO;
import com.tcs.sgv.apps.valuebeans.budget.BudSbMjrHdVO;
import com.tcs.sgv.common.util.IFMSUtil;


/**
 * [TODO add class documentation here]
 *
 * @author $author$
 * @version $Revision$
 */
public class BudHdDAOImpl implements BudHdDAO
{
	/** Declaring the Logger Class */
    private final Logger glogger = Logger.getLogger(BudHdDAOImpl.class);

    /** [TODO Add field documentation here] */
    private final String lStrBPN = BudConstants.RCPTEST_BPNCode;

    /** [TODO Add field documentation here] */
    private final String lStrDemandCode = BudConstants.RCPTEST_DemandCode;

    /** [TODO Add field documentation here] */
    private ResourceBundle lBudConstantsBundle = ResourceBundle.getBundle("common/budget/SGVConstantsBud", Locale.getDefault());

    /** [TODO Add field documentation here] */
    private String lStrConstLangID = lBudConstantsBundle.getString("RCPTEST.TXNLangID"); //en_US

    /** [TODO Add field documentation here] */
    private String lStrConstLocID = lBudConstantsBundle.getString("RCPTEST.TXNLocID"); //LC1

    /** [TODO Add field documentation here] */
    private String lStrBranchType = lBudConstantsBundle.getString("RCPTEST.BranchType"); //BranchType=BUD

    /** [TODO Add field documentation here] */
    private int liUntLvl4Brnch = Integer.parseInt(lBudConstantsBundle.getString("RCPTEST.UnitLvlID")); //level=10  

    /** [TODO Add field documentation here] */
    private int liUntLvl4Min = Integer.parseInt(lBudConstantsBundle.getString("EXPFRMCF.MinLvlID")); //level=60  

    /** [TODO Add field documentation here] */
    private final int liUntLvl4Secy = Integer.parseInt(lBudConstantsBundle.getString("EXPFRMCF.SecyLvlID")); //level=50  

    /** [TODO Add field documentation here] */
    private String lStrStatus = lBudConstantsBundle.getString("EXPEST.Status"); // Active

    /** [TODO Add field documentation here] */
    private String lStrEBUDBranchType = lBudConstantsBundle.getString("EXPFRMCF.EBUDBranchType"); //BranchType=EBUD

    /** [TODO Add field documentation here]*/
    private Connection conn = null;
    
    /**
     * Constructor with parameter jdbc Connection
     * @param conn
     */
    public BudHdDAOImpl(SessionFactory sessionFactory) {
    	if (sessionFactory!=null)
    		this.conn = sessionFactory.getCurrentSession().connection();
    }
	
    /**
     * This Method returns ArrayList Containing all Active BPN Information for passed demand, lang_id, loc_id.
     *
     * @param lStrDmdCode Demand Code
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     *
     * @return ArrayList Containing all Active BPN Information
     *
     * @throws SQLException SQL Exception
     * @throws Exception Exception
     */
    public ArrayList getAllBPNByDemand(String lStrDmdCode, String lStrLangId, String lStrLocId)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtBPN = null;
        ResultSet lRsBPN = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrBPN = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon = this.conn;
            }

            lSbSql.append("SELECT D.BPNCODE BPN_CODE, INITCAP(M.BPN_DESC) BPN_DESC");
            lSbSql.append(" FROM SGVA_BUDDEMAND_MST D, SGVA_BUDBPN_MAPPING M ");
            lSbSql.append(
                " WHERE M.Status = 'Active' AND D.BPNCODE = M.BPNCODE AND D.DEMAND_CODE = ? AND D.LANG_ID = ? AND D.LOC_ID = ? ");
            lSbSql.append(" AND M.LANG_ID = ? AND M.LOC_ID = ? ORDER BY D.BPNCODE");

            lPStmtBPN = lCon.prepareStatement(lSbSql.toString());

            lPStmtBPN.setString(1, lStrDmdCode);
            lPStmtBPN.setString(2, lStrLangId.trim());
            lPStmtBPN.setString(3, lStrLocId.trim());
            lPStmtBPN.setString(4, lStrLangId.trim());
            lPStmtBPN.setString(5, lStrLocId.trim());

            glogger.debug("getAllBPNByDemand Query is : " + lSbSql.toString());

            glogger.debug("Params for getAllBPNByDemand are :  " + lStrDmdCode + "," + lStrLangId.trim() + "," + lStrLocId.trim() +
                lStrLangId.trim() + "," + lStrLocId.trim());

            lRsBPN = lPStmtBPN.executeQuery();

            while(lRsBPN.next())
            {
                BudExpEstBPNCodeVO lObjBudExpEstBPNCodeVO = new BudExpEstBPNCodeVO(); //New Instance of VO

                lObjBudExpEstBPNCodeVO.setBPNCode(lRsBPN.getString("BPN_CODE")); //BPN Code
                lObjBudExpEstBPNCodeVO.setBPNDescription(lRsBPN.getString("BPN_DESC")); //BPN Description

                lArrBPN.add(lObjBudExpEstBPNCodeVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllBPNByDemand Method And Error is " + se, se);
            se.printStackTrace();
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllBPNByDemand Method And Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
        finally
        {
           closeResultSet(lRsBPN);
           closeStatement(lPStmtBPN);
           closeConnection(lCon);
        }

        return lArrBPN;
    }

    /**
     * Method getAllBPNCode returns all Active Budget Publication Code from SGVA_BUDBPN_MAPPING for passed department, lang_id, loc_id
     *
     * @param lStrDeptID Department ID
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     *
     * @return ArrayList in Containing Active BPN information
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllBPNCode(String lStrDeptID, String lStrLangId, String lStrLocId)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtBPN = null;
        ResultSet lRsBPN = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrBPN = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append("SELECT T.BUDBPN_MAPPING_ID, T.BPNCODE, INITCAP(T.BPN_DESC) BPN_DESC");
            lSbSql.append(" FROM SGVA_BUDBPN_MAPPING T");
            lSbSql.append(" WHERE T.DEPT_ID = ? ");
            lSbSql.append(" AND T.LOC_ID = ?");
            lSbSql.append(" AND T.LANG_ID = ?");
            lSbSql.append(" AND T.STATUS = 'Active' ORDER BY T.BPNCODE");

            lPStmtBPN = lCon.prepareStatement(lSbSql.toString());
            //System.out.println("location  id : " + lStrLocId);
            lPStmtBPN.setString(1, lStrDeptID);
            lPStmtBPN.setString(2, lStrLocId.trim());
            lPStmtBPN.setString(3, lStrLangId.trim());

            glogger.debug("getAllBPNCode Query is : " + lSbSql.toString());

            glogger.debug("Params for getAllBPNByDemand are :  " + lStrDeptID + "," + lStrLocId.trim() + "," + lStrLangId.trim());
            lRsBPN = lPStmtBPN.executeQuery();

            while(lRsBPN.next())
            {
                BudExpEstBPNCodeVO lObjBudExpEstBPNCodeVO = new BudExpEstBPNCodeVO(); //New Instance of VO

                lObjBudExpEstBPNCodeVO.setBPNID(lRsBPN.getInt(1)); //BPN Code ID
                lObjBudExpEstBPNCodeVO.setBPNCode(lRsBPN.getString(2)); //BPN Code
                lObjBudExpEstBPNCodeVO.setBPNDescription(lRsBPN.getString(3)); //BPN Description

                lArrBPN.add(lObjBudExpEstBPNCodeVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllBPNCode Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllBPNCode Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsBPN);
           closeStatement(lPStmtBPN);
           closeConnection(lCon);
        }

        return lArrBPN;
    }

    /**
     * method getAllBranch() fetch all the branch which is associated with logged user
     *
     * @param lStrUnitID UnitID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudBranchVO of All BranchID Aand BranchName
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllBranch(String[] lStrUnitID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtBranch = null;
        ResultSet lrsBranch = null;

        StringBuffer lsbBranch = new StringBuffer();
        ArrayList brnchList = new ArrayList();
        BudBranchVO objBudBranchVO = null;

        int liUnitCnt = 0;

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            lsbBranch.append("SELECT UNIT_ID, UNIT_NAME FROM("); //Added by Ravi
            lsbBranch.append(" SELECT U.UNIT_ID,U.UNIT_NAME,U.LEVEL_ID ,U.LANG_ID,U.LOC_ID FROM SGVO_UNIT_MST U");
            lsbBranch.append(" START WITH ");

            for(int i = 0; i < lStrUnitID.length; i++)
            {
                if(i == 0)
                {
                    lsbBranch.append(" (");
                }

                lsbBranch.append(" U.UNIT_ID =?");

                if(i == (lStrUnitID.length - 1))
                {
                    lsbBranch.append(" )");
                }
                else
                {
                    lsbBranch.append(" OR");
                }
            }

            lsbBranch.append(" AND U.LANG_ID = ? AND U.LOC_ID= ?");
            lsbBranch.append(" CONNECT BY PRIOR U.PARENT_UNIT_ID = U.UNIT_ID)");
            lsbBranch.append(" WHERE LEVEL_ID = ?  AND LANG_ID = ?");
            lsbBranch.append(" AND LOC_ID = ? ");
            lPstmtBranch = lcon.prepareStatement(lsbBranch.toString());

            //lPstmt.setString(1,lStrUnitID[0].trim());
            for(int j = 0; j < lStrUnitID.length; j++)
            {
                lPstmtBranch.setString(++liUnitCnt, lStrUnitID[j].trim());
            }

            lPstmtBranch.setString(++liUnitCnt, lStrLangID.trim());
            lPstmtBranch.setString(++liUnitCnt, lStrLocID.trim());
            lPstmtBranch.setInt(++liUnitCnt, liUntLvl4Brnch);
            lPstmtBranch.setString(++liUnitCnt, lStrLangID.trim());
            lPstmtBranch.setString(++liUnitCnt, lStrLocID.trim());

            glogger.debug(" getAllBranch Query is : " + lsbBranch.toString());
            lrsBranch = lPstmtBranch.executeQuery();

            while(lrsBranch.next())
            {
                objBudBranchVO = new BudBranchVO();

                if(lrsBranch.getString("UNIT_ID") != null)
                {
                    objBudBranchVO.setBranchID(lrsBranch.getString("UNIT_ID"));
                }

                if(lrsBranch.getString("UNIT_NAME") != null)
                {
                    objBudBranchVO.setBranchName(lrsBranch.getString("UNIT_NAME"));
                }

                brnchList.add(objBudBranchVO);
            }
        }
        catch(SQLException e)
        {
            glogger.error("Error occured in SQL: getAllBranch Method of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in getAllBranch Method of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsBranch);
           closeStatement(lPstmtBranch);
           closeConnection(lcon);
        }

        return brnchList;
    }

    /**
     * method getAllBranch Depends for DepartmentID
     *
     * @param lStrUnitID unit ID
     * @param lStrDeptID DepartmentID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudBranchVO of All BranchID Aand BranchName
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllBranchName(String[] lStrUnitID, String lStrDeptID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtBrnch = null;
        PreparedStatement lPstmtLevel = null;
        ResultSet lrsBranch = null;
        ResultSet lrsLevel = null;

        StringBuffer lsbBranch = new StringBuffer();
        StringBuffer lsbLevel = null;
        ArrayList branchList = null;
        branchList = new ArrayList();
        lsbBranch = new StringBuffer();
        lsbLevel = new StringBuffer();

        BudBranchVO objBrnchVO = null;
        int[] liLevel = new int[lStrUnitID.length];
        int liCnt = 0;

        boolean lbExist = false;
        String lStrUnitIDExisting = "";

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            if(lStrUnitID.length == 0)
            {
                glogger.error("Empty Array of UnitID in getAllBranchName()");

                return null;
            }

            lsbLevel.append("SELECT LEVEL_ID FROM SGVO_UNIT_MST WHERE ");
            lsbLevel.append(" LANG_ID=? AND LOC_ID=? AND ");
            lsbLevel.append(" UNIT_ID IN ( ");

            for(int i = 0; i < lStrUnitID.length; i++)
            {
                if(i == (lStrUnitID.length - 1))
                {
                    lsbLevel.append(" ?");
                }
                else
                {
                    lsbLevel.append(" ?,");
                }
            }

            lsbLevel.append(" )");
            lPstmtLevel = lcon.prepareStatement(lsbLevel.toString());

            lPstmtLevel.setString(1, lStrLangID);
            lPstmtLevel.setString(2, lStrLocID);

            int liLvlCnt = 2;

            for(int j = 0; j < lStrUnitID.length; j++)
            {
                lPstmtLevel.setString(++liLvlCnt, lStrUnitID[j].trim());
            }

            glogger.debug(" getAllBranchName Query is 1 : " + lsbLevel.toString());
            lrsLevel = lPstmtLevel.executeQuery();

            int j = 0;

            while(lrsLevel.next())
            {
                if(lrsLevel.getInt("LEVEL_ID") != 0)
                {
                    liLevel[j++] = lrsLevel.getInt("LEVEL_ID");
                }
            }

            /*lsb.append("SELECT U.UNIT_ID,U.UNIT_NAME FROM SGVO_UNIT_MST U");
               lsb.append(" WHERE U.DEPT_ID=? AND U.LEVEL_ID = ? AND U.LANG_ID = ?");
               lsb.append(" AND LOC_ID = INITCAP(?) ");
             */
            for(int i = 0; i < lStrUnitID.length; i++)
            {
                lsbBranch.delete(0, lsbBranch.length());
                liCnt = 0;
                lsbBranch.append("SELECT UNIQUE UNIT_ID,UNIT_NAME,UNIT_TYPE FROM("); // Edited by Keyur
                lsbBranch.append(" SELECT U.UNIT_ID,U.UNIT_NAME,U.DEPT_ID,U.LANG_ID,");
                lsbBranch.append(" U.LEVEL_ID,U.LOC_ID,U.UNIT_TYPE FROM SGVO_UNIT_MST U START WITH ");

                //FOR LOOP FOR UNITID
                lsbBranch.append(" U.UNIT_ID =? ");

                //EDITED BY RAVI
                lsbBranch.append(" CONNECT BY PRIOR ");

                if(liLevel[i] > 10)
                {
                    lsbBranch.append(" U.UNIT_ID = U.PARENT_UNIT_ID)");
                }
                else
                {
                    lsbBranch.append(" U.PARENT_UNIT_ID = U.UNIT_ID)");
                }

                lsbBranch.append(" WHERE LEVEL_ID = ?");

                //To fetch All Branches i fdept is not selected...
                if((lStrDeptID != null) && (!(lStrDeptID.equals(""))) && (!(lStrDeptID.equals("0"))))
                {
                    lsbBranch.append(" AND DEPT_ID=?");
                }

                lsbBranch.append(" AND LANG_ID = ? AND LOC_ID= ?");

                lPstmtBrnch = lcon.prepareStatement(lsbBranch.toString());
                lPstmtBrnch.setString(++liCnt, lStrUnitID[i]);
                lPstmtBrnch.setInt(++liCnt, liUntLvl4Brnch);

                //To fetch All Branches i fdept is not selected...
                if((lStrDeptID != null) && (!(lStrDeptID.equals(""))) && (!(lStrDeptID.equals("0"))))
                {
                    lPstmtBrnch.setString(++liCnt, lStrDeptID.trim());
                }

                lPstmtBrnch.setString(++liCnt, lStrLangID.trim());
                lPstmtBrnch.setString(++liCnt, lStrLocID.trim());
                glogger.debug(" getAllBranchName Query is 2 : " + lsbBranch.toString());
                lrsBranch = lPstmtBrnch.executeQuery();

                while(lrsBranch.next())
                {
                    objBrnchVO = new BudBranchVO();
                    lbExist = false;

                    for(int k = 0; k < branchList.size(); k++)
                    {
                        lStrUnitIDExisting = ((BudBranchVO) branchList.get(k)).getBranchID();

                        if(lStrUnitIDExisting.equals((String) lrsBranch.getString("UNIT_ID")))
                        {
                            lbExist = true;
                        }
                    }

                    if(!lbExist)
                    {
                        if(lrsBranch.getString("UNIT_ID") != null)
                        {
                            objBrnchVO.setBranchID(lrsBranch.getString("UNIT_ID"));
                        }

                        if(lrsBranch.getString("UNIT_NAME") != null)
                        {
                            objBrnchVO.setBranchName(lrsBranch.getString("UNIT_NAME"));
                        }

                        if(lrsBranch.getString("UNIT_TYPE") != null)
                        {
                            objBrnchVO.setBranchType(lrsBranch.getString("UNIT_TYPE"));
                        }

                        branchList.add(objBrnchVO);
                    }
                }

                //end of while
               closeResultSet(lrsBranch); // Added By Keyur
                lrsBranch = null;
               closeStatement(lPstmtBrnch); // Added By Keyur
                lPstmtBrnch = null;
            }
        }

        //end of try
        catch(SQLException e)
        {
            glogger.error("Error occured in SQL:getAllBranchName Method of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in getAllBranchName Method of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsBranch);
           closeResultSet(lrsLevel);
           closeStatement(lPstmtBrnch);
           closeStatement(lPstmtLevel);
           closeConnection(lcon);
        }

        return branchList;
    }

    /**
     * method getAllBrnchByDept Depends on DepartmentID
     *
     * @param lStrDeptID DepartmentID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudBranchVO of All BranchID Aand BranchName
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllBrnchByDept(String lStrDeptID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtBrnch = null;

        ResultSet lrsBranch = null;
        StringBuffer lsbBranch = new StringBuffer();
        ArrayList branchList = new ArrayList();

        BudBranchVO objBrnchVO = null;

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            lsbBranch.append("SELECT T.UNIT_ID,T.UNIT_NAME FROM SGVO_UNIT_MST T");
            lsbBranch.append(" WHERE T.DEPT_ID=? AND T.LEVEL_ID=?");
            lsbBranch.append(" AND T.LANG_ID = ? AND T.LOC_ID= ?");

            lPstmtBrnch = lcon.prepareStatement(lsbBranch.toString());

            lPstmtBrnch.setString(1, lStrDeptID.trim());
            lPstmtBrnch.setInt(2, liUntLvl4Brnch);
            lPstmtBrnch.setString(3, lStrLangID.trim());
            lPstmtBrnch.setString(4, lStrLocID.trim());
            glogger.debug(" getAllBrnchByDept Query is  : " + lsbBranch.toString());
            glogger.debug("Params for getAllBrnchByDept are :  " + lStrDeptID.trim() + "," + liUntLvl4Brnch + "," + lStrLangID.trim() +
                "," + lStrLocID.trim());

            lrsBranch = lPstmtBrnch.executeQuery();

            while(lrsBranch.next())
            {
                objBrnchVO = new BudBranchVO();

                if(lrsBranch.getString("UNIT_ID") != null)
                {
                    objBrnchVO.setBranchID(lrsBranch.getString("UNIT_ID"));
                }

                if(lrsBranch.getString("UNIT_NAME") != null)
                {
                    objBrnchVO.setBranchName(lrsBranch.getString("UNIT_NAME"));
                }

                branchList.add(objBrnchVO);
            }

            //end of while
        }

        //end of try
        catch(SQLException e)
        {
            glogger.error("Error occured in SQL:getAllBrnchByDept Method of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in getAllBrnchByDept Method of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsBranch);
           closeStatement(lPstmtBrnch);
           closeConnection(lcon);
        }

        return branchList;
    }

    /**
     * It returns all the Budget Branches for given department.
     *
     * @param lStrDeptID Department ID
     * @param lStrLangID Lang ID
     * @param lStrLocID Loc ID
     *
     * @return ArrayList of Budget Branches
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllBudBranch(String lStrDeptID, String lStrLangID, String lStrLocID) // Added By Keyur
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrBudBranch = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(" SELECT U.UNIT_ID UND_ID, U.UNIT_NAME UNT_NAME FROM SGVO_UNIT_MST U WHERE U.DEPT_ID = ? ");
            lSbSql.append(" AND U.UNIT_TYPE = ? AND U.LANG_ID = ? AND U.LOC_ID = ? ORDER BY U.UNIT_NAME");

            lPStmt = lCon.prepareStatement(lSbSql.toString());
            lPStmt.setString(1, lStrDeptID);
            lPStmt.setString(2, lStrBranchType);
            lPStmt.setString(3, lStrLangID);
            lPStmt.setString(4, lStrLocID);

            glogger.debug("getAllBudBranch Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllBudBranch are :  " + lStrDeptID + "," + lStrBranchType + "," + lStrLangID + "," +
                lStrLocID);
            lRs = lPStmt.executeQuery();

            while(lRs.next())
            {
                BudBranchVO lObjBranchVO = new BudBranchVO();

                lObjBranchVO.setBranchID(lRs.getString("UND_ID"));
                lObjBranchVO.setBranchName(lRs.getString("UNT_NAME"));

                lArrBudBranch.add(lObjBranchVO);
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllBudBranch Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllBudBranch Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRs);
           closeStatement(lPStmt);
           closeConnection(lCon);
        }

        return lArrBudBranch;
    }

    /**
     * Method getAllDemand returns List of all Demands for given Dept including Common Demands
     *
     * @param lStrDeptID Department Id
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts [TODO Add parameter documentation here]
     *
     * @return ArrayList in BudDmdVO which returns List of all Demand Code depends on Department Id
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllDemand(String lStrDeptID, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtDemand = null;
        ResultSet lRsDemand = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrDemand = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(" SELECT DEMAND_ID, DEMAND_CODE, INITCAP(DEMAND_DESC) DEMAND_DESC, BPNCODE");
            lSbSql.append(" FROM SGVA_BUDDEMAND_MST ");
            lSbSql.append(" WHERE LOC_ID = ?");
            lSbSql.append(" AND LANG_ID = ? ");
            lSbSql.append(" AND STATUS = ?");
            lSbSql.append(" AND BPNCODE IN ");
            lSbSql.append(" (SELECT BPNCODE ");
            lSbSql.append("  FROM SGVA_BUDBPN_MAPPING ");
            lSbSql.append("  WHERE DEPT_ID = ? ");
            lSbSql.append(" AND LOC_ID = ?");
            lSbSql.append(" AND LANG_ID = ?");
            lSbSql.append(" AND STATUS = ?)");
            lSbSql.append(" UNION");
            lSbSql.append(" SELECT DEMAND_ID,DEMAND_CODE,INITCAP(DEMAND_DESC) DEMAND_DESC,BPNCODE");
            lSbSql.append(" FROM SGVA_BUDDEMAND_MST");
            lSbSql.append(" WHERE DEMAND_TYPE = 'Common'");
            lSbSql.append(" AND LANG_ID = ? ");
            lSbSql.append(" AND STATUS = ?");
            lSbSql.append(" AND LOC_ID = ?");
            lSbSql.append(" ORDER BY DEMAND_CODE");

            lPStmtDemand = lCon.prepareStatement(lSbSql.toString());
            lPStmtDemand.setString(1, lStrLocID);
            lPStmtDemand.setString(2, lStrLangID.trim());
            lPStmtDemand.setString(3, lStrSts.trim());
            lPStmtDemand.setString(4, lStrDeptID);
            lPStmtDemand.setString(5, lStrLocID.trim());
            lPStmtDemand.setString(6, lStrLangID.trim());
            lPStmtDemand.setString(7, lStrSts);
            lPStmtDemand.setString(8, lStrLangID);
            lPStmtDemand.setString(9, lStrSts);
            lPStmtDemand.setString(10, lStrLocID);

            glogger.debug("getAllDemand Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllDemand are :  " + lStrLocID + "," + lStrLangID.trim() + "," + lStrSts.trim() + "," +
                lStrDeptID + "," + lStrLocID.trim() + "," + lStrLangID.trim() + "," + lStrSts + "," + lStrLangID + "," + lStrSts +
                "," + lStrLangID);
            lRsDemand = lPStmtDemand.executeQuery();

            while(lRsDemand.next())
            {
                BudExpEstDmdVO lObjBudExpEstDmdVO = new BudExpEstDmdVO(); //Instance of Value Object

                lObjBudExpEstDmdVO.setDemandID(lRsDemand.getInt(1)); //For Expenditure Demand ID
                lObjBudExpEstDmdVO.setDemandCode(lRsDemand.getString(2)); //For Demand Code
                lObjBudExpEstDmdVO.setDemandDesc(lRsDemand.getString(3)); //For Demand Description
                lObjBudExpEstDmdVO.setBPNCode(lRsDemand.getString(4)); //For Demand Description
                lArrDemand.add(lObjBudExpEstDmdVO); //Adding VO's Object to Array List
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllDemand Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllDemand Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsDemand);
           closeStatement(lPStmtDemand);
           closeConnection(lCon);
        }

        return lArrDemand;
    }

    /**
     * It returns all the Demands for given Department excluding Common Demands
     *
     * @param lStrDeptID Department Id
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts [TODO Add parameter documentation here]
     *
     * @return ArrayList in BudDmdVO which returns List of all Demand Code depends on Department Id
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllDemandByDept(String lStrDeptID, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtDemand = null;
        ResultSet lRsDemand = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrDemand = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(" SELECT DEMAND_ID, DEMAND_CODE, INITCAP(DEMAND_DESC) DEMAND_DESC, BPNCODE");
            lSbSql.append(" FROM SGVA_BUDDEMAND_MST ");
            lSbSql.append(" WHERE LOC_ID = ?");
            lSbSql.append(" AND LANG_ID = ? ");
            lSbSql.append(" AND STATUS = ?");
            lSbSql.append(" AND BPNCODE IN ");
            lSbSql.append(" (SELECT BPNCODE ");
            lSbSql.append("  FROM SGVA_BUDBPN_MAPPING ");
            lSbSql.append("  WHERE DEPT_ID = ? ");
            lSbSql.append(" AND LOC_ID = ?");
            lSbSql.append(" AND LANG_ID = ?");
            lSbSql.append(" AND STATUS = ?)");
            lSbSql.append(" ORDER BY DEMAND_CODE");

            lPStmtDemand = lCon.prepareStatement(lSbSql.toString());
            lPStmtDemand.setString(1, lStrLocID);
            lPStmtDemand.setString(2, lStrLangID.trim());
            lPStmtDemand.setString(3, lStrSts.trim());
            lPStmtDemand.setString(4, lStrDeptID);
            lPStmtDemand.setString(5, lStrLocID.trim());
            lPStmtDemand.setString(6, lStrLangID.trim());
            lPStmtDemand.setString(7, lStrSts);

            glogger.debug("ggetAllDemandByDept Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllDemandByDept are :  " + lStrLocID + "," + lStrLangID.trim() + "," + lStrSts.trim() + "," +
                lStrDeptID + "," + lStrLocID.trim() + "," + lStrLangID.trim() + "," + lStrSts);

            lRsDemand = lPStmtDemand.executeQuery();

            while(lRsDemand.next())
            {
                BudExpEstDmdVO lObjBudExpEstDmdVO = new BudExpEstDmdVO(); //Instance of Value Object

                lObjBudExpEstDmdVO.setDemandID(lRsDemand.getInt(1)); //For Expenditure Demand ID
                lObjBudExpEstDmdVO.setDemandCode(lRsDemand.getString(2)); //For Demand Code
                lObjBudExpEstDmdVO.setDemandDesc(lRsDemand.getString(3)); //For Demand Description
                lObjBudExpEstDmdVO.setBPNCode(lRsDemand.getString(4)); //For Demand Description
                lArrDemand.add(lObjBudExpEstDmdVO); //Adding VO's Object to Array List
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllDemandByBpn Method And Error is " + se, se);
            se.printStackTrace();
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllDemandByBpn Method And Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
        finally
        {
           closeResultSet(lRsDemand);
           closeStatement(lPStmtDemand);
           closeConnection(lCon);
        }

        return lArrDemand;
    }

    //End of getAllDemand Metod
    //End of getAllDemand Metod
    //*********** -For Department List ********************

    /**
     * method getAllDepartment Depends depends on Array of UnitID
     *
     * @param lArrUnitID UnitID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudDeptVO which contains DepartmentID and DepartMent Name
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllDept(String[] lArrUnitID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtDept = null;
        ResultSet lrsDept = null;
        StringBuffer lsbDept = null;

        ArrayList lArrDeptList = null;
        lArrDeptList = new ArrayList();

        int liCnt = 2;
        BudDeptVO objBudDeptVO = null;

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            if(lArrUnitID.length == 0)
            {
                glogger.error("Empty Array of UnitID in getAllDept()");

                return null;
            }

            lsbDept = new StringBuffer();
            lsbDept.append("SELECT UNIQUE D.DEPT_ID, D.DEPT_NAME FROM SGVO_UNIT_MST U,SGVO_DEPT_MST D");
            lsbDept.append(" WHERE (U.DEPT_ID=D.DEPT_ID) ");

            //Start:Added by Ravi to eliminate no-secretary Departments
            lsbDept.append(" AND D.DEPT_TYPE = 'SEC' ");

            //End:Added by Ravi to eliminate no-secretary Departments
            lsbDept.append(" AND (U.LANG_ID= ?) AND (D.LANG_ID=U.LANG_ID)");
            lsbDept.append(" AND (U.LOC_ID= ?) AND (U.LOC_ID=D.LOC_ID)");

            for(int i = 0; i < lArrUnitID.length; i++)
            {
                if(i == 0)
                {
                    lsbDept.append(" AND ( ");
                }

                lsbDept.append(" U.UNIT_ID =?");

                if(i == (lArrUnitID.length - 1))
                {
                    lsbDept.append(" )");
                }
                else
                {
                    lsbDept.append(" OR");
                }
            }

            lPstmtDept = lcon.prepareStatement(lsbDept.toString());
            lPstmtDept.setString(1, lStrLangID.trim());
            lPstmtDept.setString(2, lStrLocID.trim());

            for(int j = 0; j < lArrUnitID.length; j++)
            {
                lPstmtDept.setString(++liCnt, lArrUnitID[j].trim());
            }

            glogger.debug("getAllDept Query is : " + lsbDept.toString());

            lrsDept = lPstmtDept.executeQuery();

            while(lrsDept.next())
            {
                objBudDeptVO = new BudDeptVO();

                if(lrsDept.getString("DEPT_ID") != null)
                {
                    objBudDeptVO.setDeptID(lrsDept.getString("DEPT_ID"));
                }

                if(lrsDept.getString("DEPT_NAME") != null)
                {
                    objBudDeptVO.setDeptName(lrsDept.getString("DEPT_NAME"));
                }

                lArrDeptList.add(objBudDeptVO);
            }

            //END OF IF
        }
        catch(SQLException e)
        {
            glogger.error("Error occured in SQL Query : getAllDept Method of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in getAllDept Method of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsDept);
           closeStatement(lPstmtDept);
           closeConnection(lcon);
        }

        return lArrDeptList;
    }

    //end of method

    /**
     * Method getAllGADCd returns all GAD Code from SGVA_BUDGAD_MST
     *
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudExpEstGADVO which contains GAD Code,GAD Name
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllGADCd(String lStrLangId, String lStrLocId, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtGad = null;
        ResultSet lRsGad = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrGad = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(
                "SELECT GAD.BUDGAD_ID,GAD.BUDGAD_CODE,INITCAP(GAD.BUDGAD_DESC_LONG) BUDGAD_DESC_LONG,INITCAP(GAD.BUDGAD_DESC_SHORT) BUDGAD_DESC_SHORT");
            lSbSql.append(" FROM SGVA_BUDGAD_MST GAD");
            lSbSql.append(" WHERE GAD.LOC_ID = ?");
            lSbSql.append(" AND GAD.LANG_ID = ? ");
            lSbSql.append(" AND GAD.STATUS = ? ORDER BY GAD.BUDGAD_CODE");

            lPStmtGad = lCon.prepareStatement(lSbSql.toString());
            lPStmtGad.setString(1, lStrLocId);
            lPStmtGad.setString(2, lStrLangId);
            lPStmtGad.setString(3, lStrSts);

            glogger.debug("getAllGADCd Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllGADCd are :  " + lStrLocId + "," + lStrLangId + "," + lStrSts);
            lRsGad = lPStmtGad.executeQuery();

            while(lRsGad.next())
            {
                BudExpEstGadVO lObjBudExpEstGADVO = new BudExpEstGadVO(); //Instance of Value Object

                lObjBudExpEstGADVO.setGadID(lRsGad.getInt(1)); //For Expenditure GAD ID
                lObjBudExpEstGADVO.setGadCode(lRsGad.getString(2)); //For GAD Code
                lObjBudExpEstGADVO.setGadLngDesc(lRsGad.getString(3)); //For GAD Long Description
                lObjBudExpEstGADVO.setGadShrtDesc(lRsGad.getString(4)); //For GAD Short Description

                lArrGad.add(lObjBudExpEstGADVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllGADCd Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllGADCd Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsGad);
           closeStatement(lPStmtGad);
           closeConnection(lCon);
        }

        return lArrGad;
    }

    //End of getAllGADCd Metod [GAD Code]

    /**
     * Method getAllGrpMnrHds returns all Group Minro Heads from SGVA_BUDGRPMNR_MST
     *
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudGADVO which contains GAD Code,GAD Name
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllGrpMnrHds(String lStrLangId, String lStrLocId, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtGrpMnr = null;
        ResultSet lRsGrpMnr = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrGrpMnr = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(
                "SELECT GRP.BUDGRPMNR_ID,GRP.BUDGRPMNR_CODE,INITCAP(GRP.BUDGRPMNR_DESC_LONG) BUDGRPMNR_DESC_LONG,INITCAP(GRP.BUDGRPMNR_DESC_SHORT) BUDGRPMNR_DESC_SHORT");
            lSbSql.append(" FROM SGVA_BUDGRPMNR_MST GRP");
            lSbSql.append(" WHERE GRP.LOC_ID = ?");
            lSbSql.append(" AND GRP.LANG_ID = ?");
            lSbSql.append(" AND GRP.STATUS = ? ORDER BY GRP.BUDGRPMNR_CODE");

            lPStmtGrpMnr = lCon.prepareStatement(lSbSql.toString());
            lPStmtGrpMnr.setString(1, lStrLocId);
            lPStmtGrpMnr.setString(2, lStrLangId);
            lPStmtGrpMnr.setString(3, lStrSts);

            glogger.debug("getAllGrpMnrHds Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllGrpMnrHds are :  " + lStrLocId + "," + lStrLangId + "," + lStrSts);
            lRsGrpMnr = lPStmtGrpMnr.executeQuery();

            while(lRsGrpMnr.next())
            {
                BudExpEstGrpMnrVO lObjBudExpEstGrpMnrVO = new BudExpEstGrpMnrVO(); //Instance of Value Object

                lObjBudExpEstGrpMnrVO.setGrpMnrID(lRsGrpMnr.getInt(1)); //For Expenditure Group Minor Head ID
                lObjBudExpEstGrpMnrVO.setGrpMnrCode(lRsGrpMnr.getString(2)); //For Group Minor Head Code
                lObjBudExpEstGrpMnrVO.setGrpMnrLngDesc(lRsGrpMnr.getString(3)); //For Group Minor Head Long Description
                lObjBudExpEstGrpMnrVO.setGrpMnrShrtDesc(lRsGrpMnr.getString(4)); //For Group Minor Head Short Description

                lArrGrpMnr.add(lObjBudExpEstGrpMnrVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllGADCd Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllGADCd Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsGrpMnr);
           closeStatement(lPStmtGrpMnr);
           closeConnection(lCon);
        }

        return lArrGrpMnr;
    }

    /**
     * It returns HODs for given Department and 'Administrative Branch' as one of the HODs required for Standing Charges application
     *
     * @param lStrDeptId Department ID
     * @param lStrLangID Lang ID
     * @param lStrLocID Loc ID
     * @param lStrCrtdUsr Used by Standing Charges application.
     *
     * @return ArrayList of HODs
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllHods(String lStrDeptId, String lStrLangID, String lStrLocID, String lStrCrtdUsr)
        throws SQLException, Exception
    {
        glogger.debug("In BudHdDAO : getAllHods method ");

        Connection lCon = null;
        PreparedStatement lPStmtHOD = null;
        ResultSet lRsHOD = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrHOD = new ArrayList();

        // 
        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(" SELECT * FROM (SELECT A.HOD_ID HOD_ID, TRIM(A.HOD_DESC_LONG) HOD_DESC_LONG");
            lSbSql.append(" FROM SGVA_BUDHOD_MST A, SGVA_BUDHOD_MPG B WHERE (A.HOD_ID = B.HOD_ID");
            lSbSql.append(" AND A.STATUS = ? AND A.LANG_ID = ? AND A.LOC_ID = ? AND B.LOC_ID = ?");
            lSbSql.append(" AND B.DEPT_ID = ?) UNION SELECT H.HOD_ID HOD_ID , H.HOD_DESC_LONG HOD_DESC_LONG FROM");
            lSbSql.append(" SGVA_BUDHOD_MST H WHERE H.CRT_USR = ? AND H.LANG_ID = ?) ORDER BY HOD_DESC_LONG");

            lPStmtHOD = lCon.prepareStatement(lSbSql.toString());

            lPStmtHOD.setString(1, lStrStatus);
            lPStmtHOD.setString(2, lStrLangID.trim());
            lPStmtHOD.setString(3, lStrLocID.trim());
            lPStmtHOD.setString(4, lStrLocID.trim());
            lPStmtHOD.setString(5, lStrDeptId.trim());
            lPStmtHOD.setString(6, lStrCrtdUsr);
            lPStmtHOD.setString(7, lStrLangID);

            glogger.debug("getAllHods Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllHods are :  " + lStrStatus + "," + lStrLangID.trim() + "," + lStrLocID.trim() + "," +
                lStrLocID.trim() + "," + lStrDeptId.trim() + "," + lStrStatus + "," + lStrLangID);
            lRsHOD = lPStmtHOD.executeQuery();

            while(lRsHOD.next())
            {
                BudHodVO lObjBudHodVO = new BudHodVO();

                lObjBudHodVO.setHodID(lRsHOD.getInt("HOD_ID")); //For HOD ID
                lObjBudHodVO.setHodDesc(lRsHOD.getString("HOD_DESC_LONG")); //For HOD DESCRIPTION 
                lArrHOD.add(lObjBudHodVO);
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllHods Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllHods Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsHOD);
           closeStatement(lPStmtHOD);
           closeConnection(lCon);
        }

        return lArrHOD; //Return HODS ArrayList
    }

    //************************ Hod list For receipt Estimation*******************************

    /**
     * Method getAllHods returns all HodID and Description from SGVA_BUDHOD_MST for Receipt Estimation
     *
     * @param lStrStatus Status (Active)
     * @param lArrUnitID UnitID[]
     * @param lStrDeptID DepartmentId
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList which contains HodIDs and Hod Description
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllHods(String lStrStatus, String[] lArrUnitID, String lStrDeptID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtHod = null;
        ResultSet lrsHod = null;

        StringBuffer lsbHod = new StringBuffer();
        ArrayList hodList = new ArrayList();
        BudHodVO objHodVO = null;

        int liCnt = 5;

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            if(lArrUnitID.length == 0)
            {
                glogger.error("Empty Array of UnitID in getAllDept()");

                return null;
            }

            lsbHod.append("SELECT A.HOD_ID,A.HOD_DESC_LONG HOD_DESC_LONG FROM SGVA_BUDHOD_MST A,SGVA_BUDHOD_MPG B");
            lsbHod.append(" WHERE A.HOD_ID=B.HOD_ID");
            lsbHod.append(" AND A.STATUS= ?");
            lsbHod.append(" AND A.LANG_ID= ?");
            lsbHod.append(" AND A.LOC_ID= ?");
            lsbHod.append(" AND B.DEPT_ID IN(SELECT DEPT_ID FROM SGVO_UNIT_MST WHERE");
            lsbHod.append(" LANG_ID= ?");
            lsbHod.append(" AND LOC_ID= ? ");

            for(int i = 0; i < lArrUnitID.length; i++)
            {
                if(i == 0)
                {
                    lsbHod.append("AND (");
                }

                lsbHod.append(" UNIT_ID =?");

                if(i == (lArrUnitID.length - 1))
                {
                    lsbHod.append(" ))");
                }
                else
                {
                    lsbHod.append(" OR");
                }
            }

            if((lStrDeptID != null) && !(lStrDeptID.equals("0")))
            {
                lsbHod.append(" AND DEPT_ID= ? ");
            }

            lPstmtHod = lcon.prepareStatement(lsbHod.toString());
            lPstmtHod.setString(1, lStrStatus.trim());
            lPstmtHod.setString(2, lStrLangID.trim());
            lPstmtHod.setString(3, lStrLocID.trim());
            lPstmtHod.setString(4, lStrLangID.trim());
            lPstmtHod.setString(5, lStrLocID.trim());

            for(int j = 0; j < lArrUnitID.length; j++)
            {
                lPstmtHod.setString(++liCnt, lArrUnitID[j].trim());
            }

            if((lStrDeptID != null) && !(lStrDeptID.equals("0")))
            {
                lPstmtHod.setString(++liCnt, lStrDeptID.trim());
            }

            glogger.debug("getAllHods (bRANCHWISE) Query is : " + lsbHod.toString());
            lrsHod = lPstmtHod.executeQuery();

            while(lrsHod.next())
            {
                objHodVO = new BudHodVO();

                if(lrsHod.getInt("HOD_ID") != 0)
                {
                    objHodVO.setHodID(lrsHod.getInt("HOD_ID"));
                }

                if(lrsHod.getString("HOD_DESC_LONG") != null)
                {
                    objHodVO.setHodDesc(lrsHod.getString("HOD_DESC_LONG"));
                }

                hodList.add(objHodVO);
            }
        }
        catch(SQLException e)
        {
            glogger.error("Error occured in SQL:getAllHods Method of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in getAllHods Method of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsHod);
           closeStatement(lPstmtHod);
           closeConnection(lcon);
        }

        return hodList;
    }

    //************************************ For Hod details **************************************************

    /**
     * Method getAllHods returns all HodID and Description from SGVA_BUDHOD_MST
     *
     * @param lStrDeptId Status
     * @param lStrLangID Language ID
     * @param lStrDeptId DepartmentID
     *
     * @return ArrayList which contains HodIDs and Hod Description
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllHodsRpt(String lStrDeptId, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        ArrayList lArrHods = new ArrayList();
        lArrHods = getAllHods(lStrDeptId, lStrLangID, lStrLocID, "Active");

        return lArrHods;
    }

    //***************************************** Getting Major Head for only Receipt Estimation ******************************************** 

    /**
     * method getAllMjrHds() return the MajorHead list based on Department which is used only for Receipt Estimation
     *
     * @param lStrDeptID for FD) Department
     * @param lStrMjrHdType Major Head Type ( R-Reciept or E-Expenditure)
     * @param lStrStatus Status of the Major Head (In this case Status="Y")
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList of All Major Head Code and description
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllMjrHd(String lStrDeptID, String lStrMjrHdType, String lStrStatus, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtMjrHd = null;
        ResultSet lrsMjrHd = null;

        StringBuffer lsbMjrHd = new StringBuffer();
        ArrayList lArrMjrHd = new ArrayList();

        BudMjrHdVO objMjrHdvo = null;

        glogger.debug("lStrDeptID: in dao:" + lStrDeptID);

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            if(lStrDeptID.equals("0")) //|| (lStrDeptID.equals("0")))
            {
                return new ArrayList();
            }

            lsbMjrHd.append("SELECT BUDMJRHD_ID,BUDMJRHD_CODE,INITCAP(BUDMJRHD_DESC_LONG) BUDMJRHD_DESC_LONG FROM SGVA_BUDMJRHD_MST");
            lsbMjrHd.append(" WHERE BUDMJRHDTYPE= ? ");
            lsbMjrHd.append(" AND DEMAND_CODE=?");
            lsbMjrHd.append(" AND STATUS= ?");
            lsbMjrHd.append(" AND LANG_ID= ?");
            lsbMjrHd.append(" AND LOC_ID= ?");

            if(!(lStrDeptID.equals("")))
            {
                lsbMjrHd.append(" AND BUDMJRHD_CODE ");
                lsbMjrHd.append("IN (SELECT G.BUDMJRHD_CODE FROM SGVA_BUDMJRHD_MPG G");
                lsbMjrHd.append(" WHERE G.DEPT_ID=? ) "); //AND G.LANG_ID=? AND G.LOC_ID=?
            }

            lsbMjrHd.append(" ORDER BY BUDMJRHD_CODE");

            lPstmtMjrHd = lcon.prepareStatement(lsbMjrHd.toString());
            lPstmtMjrHd.setString(1, lStrMjrHdType.trim());
            lPstmtMjrHd.setString(2, lStrDemandCode);
            lPstmtMjrHd.setString(3, lStrStatus.trim());
            lPstmtMjrHd.setString(4, lStrLangID.trim());
            lPstmtMjrHd.setString(5, lStrLocID.trim());

            if(!(lStrDeptID.equals("")))
            {
                lPstmtMjrHd.setString(6, lStrDeptID.trim());

                //lPstmtMjrHd.setString(7,lStrLangID.trim());
                //lPstmtMjrHd.setString(8,lStrLocID.trim());
            }
            //System.out.println(" --------------------- " +  lsbMjrHd.toString());	
            glogger.debug("getAllMjrHd for Receipt Query is : " + lsbMjrHd.toString());
            glogger.debug("Params for getAllMjrHd for Receipt Query are :  " + lStrMjrHdType.trim() + "," + lStrDemandCode + "," +
                lStrStatus.trim() + "," + lStrLangID.trim() + "," + lStrLocID.trim() + ",[Optional]," + lStrDeptID.trim());
            lrsMjrHd = lPstmtMjrHd.executeQuery();

            while(lrsMjrHd.next())
            {
                objMjrHdvo = new BudMjrHdVO();

                if(lrsMjrHd.getInt("BUDMJRHD_ID") != 0)
                {
                    objMjrHdvo.setMjrHdID(lrsMjrHd.getInt("BUDMJRHD_ID"));
                }

                if(lrsMjrHd.getString("BUDMJRHD_CODE") != null)
                {
                    objMjrHdvo.setMjrHdCode(lrsMjrHd.getString("BUDMJRHD_CODE"));
                }

                if(lrsMjrHd.getString("BUDMJRHD_DESC_LONG") != null)
                {
                    objMjrHdvo.setMjrHdLongDesc(lrsMjrHd.getString("BUDMJRHD_DESC_LONG"));
                }

                lArrMjrHd.add(objMjrHdvo);
            }
        }
        catch(SQLException e)
        {	e.printStackTrace();
            glogger.error("Error occured in SQL:getAllMjrhd Method of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in getAllMjrhd Method of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsMjrHd);
           closeStatement(lPstmtMjrHd);
           closeConnection(lcon);
        }

        return lArrMjrHd;
    }

    /**
     * Method getAllMjrHds returns List of all Major Head from sgva_budmjrhd_mst depends on Department For Expediture Estimation
     *
     * @param lStrDeptId Major Head Id
     * @param lStrDemandCode Demand Code
     * @param lStrMhrHdType Major Head Type
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudMjrHdVO which returns List of all Sub Major Heads
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllMjrHds(String lStrDeptId, String lStrDemandCode, String lStrMhrHdType, String lStrLangID, String lStrLocID,
        String lStrSts) throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtMjrHd = null;
        ResultSet lRsMjrHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrMjrHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append("SELECT MJR.BUDMJRHD_ID, MJR.BUDMJRHD_CODE, INITCAP(TRIM(MJR.BUDMJRHD_DESC_LONG)) BUDMJRHD_DESC_LONG");
            lSbSql.append(" FROM SGVA_BUDMJRHD_MST MJR WHERE MJR.BUDMJRHDTYPE = ? AND MJR.DEMAND_CODE = ? AND ((MJR.BPN_CODE IN ");
            lSbSql.append(" (SELECT M.BPNCODE FROM SGVA_BUDBPN_MAPPING M WHERE M.DEPT_ID = ? AND M.LANG_ID = ? AND M.STATUS = ?) AND");
            lSbSql.append(" MJR.STATUS = ? AND MJR.LANG_ID = ? AND MJR.LOC_ID = ?) OR MJR.BPN_CODE IN (SELECT DISTINCT D.BPNCODE");
            lSbSql.append(" FROM SGVA_BUDDEMAND_MST D WHERE D.DEMAND_TYPE = 'Common') AND MJR.STATUS = ? AND MJR.LANG_ID = ? AND");
            lSbSql.append(" MJR.LOC_ID = ?) ORDER BY MJR.BUDMJRHD_CODE");

            lPStmtMjrHd = lCon.prepareStatement(lSbSql.toString());
            lPStmtMjrHd.setString(1, lStrMhrHdType.trim());
            lPStmtMjrHd.setString(2, lStrDemandCode.trim());
            lPStmtMjrHd.setString(3, lStrDeptId.trim());
            lPStmtMjrHd.setString(4, lStrLangID.trim());
            lPStmtMjrHd.setString(5, lStrSts.trim());
            lPStmtMjrHd.setString(6, lStrSts.trim());
            lPStmtMjrHd.setString(7, lStrLangID.trim());
            lPStmtMjrHd.setString(8, lStrLocID.trim());
            lPStmtMjrHd.setString(9, lStrSts.trim());
            lPStmtMjrHd.setString(10, lStrLangID.trim());
            lPStmtMjrHd.setString(11, lStrLocID.trim());

            glogger.debug("getAllMjrHds Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllMjrHds are :  " + lStrMhrHdType.trim() + "," + lStrDemandCode.trim() + "," +
                lStrDeptId.trim() + "," + lStrLangID.trim() + "," + lStrSts.trim() + "," + lStrSts.trim() + "," + lStrLangID.trim() +
                "," + lStrLocID.trim() + "," + lStrSts.trim() + "," + lStrLangID.trim() + "," + lStrLocID.trim());
            lRsMjrHd = lPStmtMjrHd.executeQuery();
            //System.out.println("SQL QUERY " + lSbSql.toString());	
            while(lRsMjrHd.next())
            {
                BudMjrHdVO lObjBudMjrHdVO = new BudMjrHdVO();

                lObjBudMjrHdVO.setMjrHdID(lRsMjrHd.getInt(1)); //For Sub Major Head ID
                lObjBudMjrHdVO.setMjrHdCode(lRsMjrHd.getString(2)); //For Sub Major Head Code
                lObjBudMjrHdVO.setMjrHdLongDesc(lRsMjrHd.getString(3)); //For Sub Major Head Long Description
                lArrMjrHd.add(lObjBudMjrHdVO);
            }
        }
        catch(SQLException se)
        {
        	//System.out.println("se exception : "+se);
            glogger.error("Error in BudHdDAO Impl SQL in getAllMjrHds Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            glogger.error("Error in BudHdDAO Impl IO in getAllMjrHds Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsMjrHd);
           closeStatement(lPStmtMjrHd);
           closeConnection(lCon);
        }

        return lArrMjrHd;
    }

    //Added By Ronak Shah on 29/04/2006

    /**
     * This Method returns ArrayList Containing all Active BPN Information for passed Status, lang_id, loc_id.
     *
     * @param lStrStatus Status
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     *
     * @return ArrayList Containing all Active Object Head Information
     *
     * @throws SQLException SQL Exception
     * @throws Exception Exception
     */
    public ArrayList getAllObjectHd(String lStrStatus, String lStrLangId, String lStrLocId)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtObjHd = null;
        ResultSet lRsObjHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrObjHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            //SELECT OM.BUDOBJHD_CODE , OM.BUDOBJHD_DESC_SHORT FROM SGVA_BUDOBJHD_MST OM
            //WHERE OM.STATUS='Active' AND OM.LANG_ID=? AND OM.LOC_ID=? ORDER BY OM.BUDOBJHD_CODE
            lSbSql.append("SELECT OM.BUDOBJHD_CODE , INITCAP(OM.BUDOBJHD_DESC_SHORT) BUDOBJHD_DESC_SHORT, OM.BUDOBJHD_TYPE OBJ_TYPE");
            lSbSql.append(" FROM SGVA_BUDOBJHD_MST OM WHERE OM.STATUS='Active' ");
            lSbSql.append(" AND OM.LANG_ID=? AND OM.LOC_ID=?  ORDER BY OM.BUDOBJHD_CODE ");

            lPStmtObjHd = lCon.prepareStatement(lSbSql.toString());

            lPStmtObjHd.setString(1, lStrLangId.trim());
            lPStmtObjHd.setString(2, lStrLocId.trim());

            glogger.debug("getAllObjectHd Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllObjectHd are :  " + lStrLangId.trim() + "," + lStrLocId.trim());
            lRsObjHd = lPStmtObjHd.executeQuery();

            while(lRsObjHd.next())
            {
                BudObjHdVO lObjBudObjHdVO = new BudObjHdVO(); //New Instance of VO

                lObjBudObjHdVO.setObjHdCode(lRsObjHd.getString("BUDOBJHD_CODE")); //Object Head Code
                lObjBudObjHdVO.setObjHdShrtDesc(lRsObjHd.getString("BUDOBJHD_DESC_SHORT")); //Object Head Short Description
                lObjBudObjHdVO.setObjHdType(lRsObjHd.getString("OBJ_TYPE")); // Added by keyur to get the type

                lArrObjHd.add(lObjBudObjHdVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllObjectHd Method And Error is " + se, se);
            se.printStackTrace();
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllObjectHd Method And Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
        finally
        {
           closeResultSet(lRsObjHd);
           closeStatement(lPStmtObjHd);
           closeConnection(lCon);
        }

        return lArrObjHd;
    }

    // Added by Vidhya for getting all object heads - Start

    /**
     * This Method returns ArrayList Containing all Active Object Head Codes along with Description Information for passed Status,
     * lang_id, loc_id.
     *
     * @param lStrStatus Status
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     *
     * @return ArrayList Containing all Active Object Head Information
     *
     * @throws SQLException SQL Exception
     * @throws Exception Exception
     */
    public ArrayList getAllObjectHdDesc(String lStrStatus, String lStrLangId, String lStrLocId)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtObjHd = null;
        ResultSet lRsObjHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrObjHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            //SELECT OM.BUDOBJHD_CODE , OM.BUDOBJHD_DESC_SHORT FROM SGVA_BUDOBJHD_MST OM
            //WHERE OM.STATUS='Active' AND OM.LANG_ID=? AND OM.LOC_ID=? ORDER BY OM.BUDOBJHD_CODE
            lSbSql.append("SELECT OM.BUDOBJHD_CODE , INITCAP(OM.BUDOBJHD_DESC_SHORT) BUDOBJHD_DESC_SHORT FROM SGVA_BUDOBJHD_MST OM ");
            lSbSql.append("WHERE OM.STATUS='Active' AND OM.LANG_ID=? AND OM.LOC_ID=?  ORDER BY OM.BUDOBJHD_CODE ");

            lPStmtObjHd = lCon.prepareStatement(lSbSql.toString());

            lPStmtObjHd.setString(1, lStrLangId.trim());
            lPStmtObjHd.setString(2, lStrLocId.trim());

            glogger.debug("getAllObjectHd Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllObjectHd are :  " + lStrLangId.trim() + "," + lStrLocId.trim());
            lRsObjHd = lPStmtObjHd.executeQuery();

            while(lRsObjHd.next())
            {
                BudObjHdVO lObjBudObjHdVO = new BudObjHdVO(); //New Instance of VO

                lObjBudObjHdVO.setObjHdCode(lRsObjHd.getString("BUDOBJHD_CODE")); //Object Head Code
                lObjBudObjHdVO.setObjHdShrtDesc(lRsObjHd.getString("BUDOBJHD_CODE") + "-" + lRsObjHd.getString("BUDOBJHD_DESC_SHORT")); //Object Head Short Description

                lArrObjHd.add(lObjBudObjHdVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllObjectHd Method And Error is " + se, se);
            se.printStackTrace();
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllObjectHd Method And Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
        finally
        {
           closeResultSet(lRsObjHd);
           closeStatement(lPStmtObjHd);
           closeConnection(lCon);
        }

        return lArrObjHd;
    }

    // Added by Vidhya for getting all object heads - Start
    //***************************************** For Branch Details ******************************************** 

    /**
     * Method getAllOfficeCd returns all Office Code from SGVA_BUDOFFICE_MST
     *
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     * @param lStrSts [TODO Add parameter documentation here]
     *
     * @return ArrayList in BudOfficeCdVO which contains Office Code,Office Name
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllOfficeCd(String lStrLangId, String lStrLocId, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtOffice = null;
        ResultSet lRsOffice = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrOffice = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(
                "SELECT OFFICE.BUDOFFICE_ID,OFFICE.BUDOFFICE_CODE,INITCAP(OFFICE.BUDOFFICE_DESC_LONG) BUDOFFICE_DESC_LONG,INITCAP(OFFICE.BUDOFFICE_DESC_SHORT) BUDOFFICE_DESC_SHORT");
            lSbSql.append(" FROM SGVA_BUDOFFICE_MST OFFICE");
            lSbSql.append(" WHERE OFFICE.LOC_ID = ?");
            lSbSql.append(" AND OFFICE.LANG_ID = ? ");
            lSbSql.append(" AND OFFICE.STATUS = ? ORDER BY OFFICE.BUDOFFICE_CODE");

            lPStmtOffice = lCon.prepareStatement(lSbSql.toString());
            lPStmtOffice.setString(1, lStrLocId);
            lPStmtOffice.setString(2, lStrLangId);
            lPStmtOffice.setString(3, lStrSts);

            glogger.debug("getAllOfficeCd Query is : " + lSbSql.toString());
            glogger.debug("Params for getAllOfficeCd are :  " + lStrLocId + "," + lStrLangId + "," + lStrSts);
            lRsOffice = lPStmtOffice.executeQuery();

            while(lRsOffice.next())
            {
                BudExpEstOfficeVO lObjBudExpOfficeVO = new BudExpEstOfficeVO(); //Instance of Value Object

                lObjBudExpOfficeVO.setOfficeID(lRsOffice.getInt(1)); //For Expenditure Office ID
                lObjBudExpOfficeVO.setOfficeCode(lRsOffice.getString(2)); //For Office Code
                lObjBudExpOfficeVO.setOfficeLngDesc(lRsOffice.getString(3)); //For Office Long Description
                lObjBudExpOfficeVO.setOfficeShrtDesc(lRsOffice.getString(4)); //For Office Short Description

                lArrOffice.add(lObjBudExpOfficeVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllOfficeCd Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllOfficeCd Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsOffice);
           closeStatement(lPStmtOffice);
           closeConnection(lCon);
        }

        return lArrOffice;
    }

    /**
     * Method getAllShare Code returns List of all Share Codes from SGVA_BUDSHARE_MST
     *
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return [TODO Add return documentation here]
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */

    //Added By Ravishankar
    public ArrayList getAllShare(String lStrLangID, String lStrLocID) throws SQLException, Exception
    {
        Connection lCon = null;
        ArrayList lArrShare = new ArrayList();
        ResultSet lRsShare = null;
        StringBuffer lSbSql = new StringBuffer();
        Statement lStmtShare = null;
        ComboValuesVO lObjComboValuesVO = null;

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lStmtShare = lCon.createStatement();

            lSbSql.append(" SELECT  T.BUDSHARE_CODE,INITCAP(T.BUDSHARE_DESC_LONG) BUDSHARE_DESC_LONG");
            lSbSql.append(" FROM SGVA_BUDSHARE_MST T");
            lSbSql.append(" WHERE T.LOC_ID = '" + lStrLocID.trim() + "'");
            lSbSql.append(" AND T.LANG_ID = '" + lStrLangID.trim() + "'");
            lSbSql.append(" AND T.LANG_ID = '" + lStrLangID.trim() + "'");
            lSbSql.append(" AND T.STATUS = 'Active' ORDER BY T.BUDSHARE_CODE");

            glogger.debug("getAllShare Query is : " + lSbSql.toString());

            lStmtShare.executeQuery(lSbSql.toString());

            lRsShare = lStmtShare.getResultSet(); //Result set for Sub Head

            while(lRsShare.next())
            {
                lObjComboValuesVO = new ComboValuesVO(); //Instance of Value Object           

                lObjComboValuesVO.setId(lRsShare.getString(1)); //For Share Code
                lObjComboValuesVO.setDesc(lRsShare.getString(2)); //For Share code Long Description

                lArrShare.add(lObjComboValuesVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getShareCode Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getShareCode Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsShare);
           closeStatement(lStmtShare);
           closeConnection(lCon);
        }

        return lArrShare;
    }

    //End of getgetShareCode Method
    //ADDED BY Ekta

    /**
     * Method getAllShareCd returns all ShareCode from SGVA_BUDSHARE_MST
     *
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudExpEstShareVO which contains Share Code,Share Name
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getAllShareCd(String lStrLangId, String lStrLocId, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        ArrayList lArrShare = new ArrayList();
        ResultSet lRsShare = null;
        StringBuffer lSbSql = new StringBuffer();
        Statement lStmtShare = null;

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lStmtShare = lCon.createStatement();

            lSbSql.append(
                "SELECT SH.BUDSHARE_ID,SH.BUDSHARE_CODE,INITCAP(SH.BUDSHARE_DESC_LONG) BUDSHARE_DESC_LONG,INITCAP(SH.BUDSHARE_DESC_SHORT) BUDSHARE_DESC_SHORT");
            lSbSql.append(" FROM SGVA_BUDSHARE_MST SH");
            lSbSql.append(" WHERE SH.LOC_ID = '" + lStrLocId + "'");
            lSbSql.append(" AND SH.LANG_ID = '" + lStrLangId + "'");
            lSbSql.append(" AND SH.STATUS = '" + lStrSts + "' ORDER BY SH.BUDSHARE_CODE");

            glogger.debug("getAllShareCd Query is " + lSbSql.toString());
            lStmtShare.executeQuery(lSbSql.toString());

            lRsShare = lStmtShare.getResultSet();

            while(lRsShare.next())
            {
                BudExpEstShareVO lObjBudExpEstShareVO = new BudExpEstShareVO(); //Instance of Value Object

                lObjBudExpEstShareVO.setShareID(lRsShare.getInt(1)); //For Expenditure Share ID
                lObjBudExpEstShareVO.setShareCode(lRsShare.getString(2)); //For Share Code
                lObjBudExpEstShareVO.setShareLngDesc(lRsShare.getString(3)); //For Share Long Description
                lObjBudExpEstShareVO.setShareShrtDesc(lRsShare.getString(4)); //For Share Short Description

                lArrShare.add(lObjBudExpEstShareVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllShareCd Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllShareCd Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsShare);
           closeStatement(lStmtShare);
           closeConnection(lCon);
        }

        glogger.debug("Size of lArrShare in DAO--->" + lArrShare.size());

        return lArrShare;
    }

    //Added By payal on 9/10/1006 For HOD Extension

    /**
     * This method getDeptForHod returns Department List and HodId,Hod Name based on Dept Id of logged User
     *
     * @param lArrDept DepartmentId OF Logged User
     * @param lStrLangId Lang Id
     * @param lStrLocId LOC ID
     *
     * @return ArrayList
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getDeptForHod(ArrayList lArrDept, String lStrLangId, String lStrLocId)
        throws SQLException, Exception
    {
        glogger.debug("Enter into getDeptForHod of BudHdDAOImpl");

        Connection lCon = null;
        PreparedStatement lPStmtHod = null;
        ResultSet lrsHod = null;

        ArrayList lArrHod = new ArrayList();
        StringBuffer lsbHod = new StringBuffer();

        Hashtable lHashInfo = new Hashtable();

        String lStrDeptId = "";
        int liCnt = 2;

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            glogger.debug("lArrDept.size()::" + lArrDept.size());
            glogger.debug("lArrDept:" + lArrDept);

            lsbHod.append(" SELECT S.HOD_ID, TRIM(S.HOD_DESC_LONG) HOD_DESC_LONG, H.DEPT_ID, ");
            lsbHod.append(" (SELECT D.DEPT_NAME FROM SGVO_DEPT_MST D ");
            lsbHod.append("  WHERE D.DEPT_ID = H.DEPT_ID AND D.LANG_ID = ? AND D.LOC_ID = ?) DEPTNM ");
            lsbHod.append("  FROM SGVA_BUDHOD_MST S, SGVA_BUDHOD_MPG H WHERE");

            //lsbHod.append("  WHERE S.HOD_DEPT_ID = ? ");
            //FOR LOOP FOR Arrylist of DEPTID
            lsbHod.append(" S.HOD_DEPT_ID IN (");

            for(int i = 0; i < lArrDept.size(); i++)
            {
                if(i < lArrDept.size())
                {
                    lsbHod.append(" ? ");
                }

                if(i == (lArrDept.size() - 1))
                {
                    lsbHod.append(" )");
                }
                else
                {
                    lsbHod.append(" , ");
                }
            }

            lsbHod.append("  AND S.LANG_ID = ? AND S.LOC_ID = ? AND H.HOD_ID = S.HOD_ID AND ");
            lsbHod.append("  H.LANG_ID = ? AND H.LOC_ID = ? ");

            lPStmtHod = lCon.prepareStatement(lsbHod.toString());

            glogger.debug("Query For getDeptForHod is :  " + lsbHod.toString());
            glogger.debug("Params For getDeptForHod are : " + lStrLangId + "," + lStrLocId + "," + lStrDeptId.trim() + "," +
                lStrConstLangID.trim() + "," + lStrConstLocID.trim());

            lPStmtHod.setString(1, lStrLangId);
            lPStmtHod.setString(2, lStrLocId);

            //lPStmtHod.setString(3, lStrDeptId);//lStrDeptId=Dept Id of Logged user
            for(int j = 0; j < lArrDept.size(); j++)
            {
                lPStmtHod.setString(++liCnt, (String) lArrDept.get(j));
            }

            lPStmtHod.setString(++liCnt, lStrLangId);
            lPStmtHod.setString(++liCnt, lStrLocId);
            lPStmtHod.setString(++liCnt, lStrConstLangID);
            lPStmtHod.setString(++liCnt, lStrConstLocID);

            lrsHod = lPStmtHod.executeQuery();

            while(lrsHod.next())
            {
                lHashInfo = new Hashtable();

                if(lrsHod.getInt("HOD_ID") != 0)
                {
                    lHashInfo.put("MJR_CODE", lrsHod.getInt("HOD_ID") + "");
                }

                if(lrsHod.getString("HOD_DESC_LONG") != null)
                {
                    lHashInfo.put("HOD_DESC", lrsHod.getString("HOD_DESC_LONG"));
                }

                if(lrsHod.getString("DEPT_ID") != null)
                {
                    lHashInfo.put("DEPTID", lrsHod.getString("DEPT_ID"));
                }

                if(lrsHod.getString("DEPTNM") != null)
                {
                    lHashInfo.put("DEPTNAME", lrsHod.getString("DEPTNM"));
                }

                lArrHod.add(lHashInfo);
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAOImpl SQL in getDeptsForHod Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAOImp in getDeptForHod Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsHod);
           closeStatement(lPStmtHod);
           closeConnection(lCon);
        }

        glogger.debug("Return hashtable size:" + lArrHod.size());

        return lArrHod;
    }

    /**
     * It returns Department related Information.
     *
     * @param lStrDeptID Dept ID
     * @param lStrLangID Lang ID
     *
     * @return BudExpEstDeptVO
     */
    public BudExpEstDeptVO getDeptInfo(String lStrDeptID, String lStrLangID)
    {
        BudExpEstDAO lObjExpEstDAO = (BudExpEstDAO) DAOFactory.Create(BudConstants.EXPEST_EXPESTDAO);
        BudExpEstDeptVO lObjReturnDeptVO = new BudExpEstDeptVO();

        try
        {
            ArrayList lArrDeptList = lObjExpEstDAO.getAllDept(lStrLangID);
            BudExpEstDeptVO lObjBudExpEstDeptVO = new BudExpEstDeptVO();

            for(int lIntCnt = 0; lIntCnt < lArrDeptList.size(); lIntCnt++)
            {
                lObjBudExpEstDeptVO = (BudExpEstDeptVO) lArrDeptList.get(lIntCnt);

                if(lObjBudExpEstDeptVO.getDeptID().equals(lStrDeptID))
                {
                    lObjReturnDeptVO = lObjBudExpEstDeptVO;

                    break;
                }
            }
        }
        catch(Exception e)
        {
            glogger.error("Error in getDeptInfo" + e, e);
            e.printStackTrace();
        }

        return lObjReturnDeptVO;
    }

    /**
     * Method getDmdByBPN returns List of all Demands for Given BPN including Common Demands
     *
     * @param lStrBPNCode BPN Code
     * @param lStrExpType [TODO Add parameter documentation here]
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudDmdVO which returns List of all Demand Code depends on Department Id
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getDmdByBPN(String lStrBPNCode, String lStrExpType, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtDemand = null;
        ResultSet lRsDemand = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrDemand = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(" SELECT D.DEMAND_ID, D.DEMAND_CODE, INITCAP(TRIM(NVL(D.DEMAND_DESC,''))) DEMAND_DESC");
            lSbSql.append(" FROM SGVA_BUDDEMAND_MST D");
            lSbSql.append(" WHERE D.LANG_ID = ?");
            lSbSql.append(" AND D.BPNCODE = ? ");
            lSbSql.append(" AND D.LOC_ID = ?");
            lSbSql.append(" AND D.STATUS = ?");
            lSbSql.append(" AND D.DEMAND_TYPE = ?");
            lSbSql.append(" UNION");
            lSbSql.append(" SELECT DEMAND_ID,DEMAND_CODE,INITCAP(DEMAND_DESC) DEMAND_DESC");
            lSbSql.append(" FROM SGVA_BUDDEMAND_MST");
            lSbSql.append(" WHERE DEMAND_TYPE = 'Common'");
            lSbSql.append(" AND STATUS = ?");
            lSbSql.append(" AND LANG_ID = ? ");
            lSbSql.append(" AND STATUS = ?");
            lSbSql.append(" AND LOC_ID = ?");
            lSbSql.append(" ORDER BY DEMAND_CODE");

            lPStmtDemand = lCon.prepareStatement(lSbSql.toString());
            lPStmtDemand.setString(1, lStrLangID.trim());
            lPStmtDemand.setString(2, lStrBPNCode.trim());
            lPStmtDemand.setString(3, lStrLocID.trim());
            lPStmtDemand.setString(4, lStrSts.trim());
            lPStmtDemand.setString(5, lStrExpType.trim());
            lPStmtDemand.setString(6, lStrSts.trim());
            lPStmtDemand.setString(7, lStrLangID.trim());
            lPStmtDemand.setString(8, lStrSts.trim());
            lPStmtDemand.setString(9, lStrLocID.trim());

            glogger.debug("getDmdByBPN Query is : " + lSbSql.toString());
            glogger.debug("Params for getDmdByBPN are :  " + lStrLangID.trim() + "," + lStrBPNCode.trim() + "," + lStrLocID.trim() +
                "," + lStrSts.trim() + "," + lStrExpType.trim() + "," + lStrSts.trim() + "," + lStrLangID.trim() + "," +
                lStrSts.trim() + "," + lStrLocID.trim());

            lRsDemand = lPStmtDemand.executeQuery();

            while(lRsDemand.next())
            {
                BudExpEstDmdVO lObjBudExpEstDmdVO = new BudExpEstDmdVO(); //Instance of Value Object

                lObjBudExpEstDmdVO.setDemandID(lRsDemand.getInt(1)); //For Expenditure Demand ID
                lObjBudExpEstDmdVO.setDemandCode(lRsDemand.getString(2)); //For Demand Code
                lObjBudExpEstDmdVO.setDemandDesc(lRsDemand.getString(3)); //For Demand Description

                lArrDemand.add(lObjBudExpEstDmdVO); //Adding VO's Object to Array List
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getDmdByBPN Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getDmdByBPN Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsDemand);
           closeStatement(lPStmtDemand);
           closeConnection(lCon);
        }

        return lArrDemand;
    }

    /**
     * Method getDtlHds returns List of all Detail Head from SGVA_BUDDTLHD_MST
     *
     * @param lStrBPNCode BPN CODE
     * @param lStrDemandCode Demand ID
     * @param lStrMjrHdCode Major Head ID
     * @param lStrSbMjrHdCode Sub Major Head ID
     * @param lStrMnrHdCode Minor Head ID
     * @param lStrSubHdCode Minor Head ID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudSbHdVO which returns List of all Sub Head Code,ID,Desc depends
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getDtlHds(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCode, String lStrSbMjrHdCode,
        String lStrMnrHdCode, String lStrSubHdCode, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtDtlHd = null;
        ResultSet lRsDtlHd = null;
        String lStrSts = lBudConstantsBundle.getString("RCPTEST.Status");

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrDtlHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(" SELECT T.BUDDTLHD_CODE, INITCAP(NVL(T.BUDDTLHD_DESC_LONG,'------------')) BUDDTLHD_DESC_LONG ");
            lSbSql.append(" FROM SGVA_BUDDTLHD_MST T");
            lSbSql.append(" WHERE ((T.BPN_CODE = ?");
            lSbSql.append(" AND T.BUDDEMAND_CODE = ?");
            lSbSql.append(" AND T.BUDMJRHD_CODE = ? ");
            lSbSql.append(" AND T.BUDSUBMJRHD_CODE = ? ");
            lSbSql.append(" AND T.BUDMINHD_CODE = ? ");
            lSbSql.append(" AND T.BUDSUBHD_CODE = ? )");
            lSbSql.append(" OR T.DTLHD_TYPE = 'C' )");
            lSbSql.append(" AND T.LOC_ID = ?");
            lSbSql.append(" AND T.LANG_ID = ?");
            lSbSql.append(" AND T.STATUS = ? ORDER BY T.BUDDTLHD_CODE");

            lPStmtDtlHd = lCon.prepareStatement(lSbSql.toString());

            lPStmtDtlHd.setString(1, lStrBPNCode.trim());
            lPStmtDtlHd.setString(2, lStrDemandCode.trim());
            lPStmtDtlHd.setString(3, lStrMjrHdCode.trim());
            lPStmtDtlHd.setString(4, lStrSbMjrHdCode.trim());
            lPStmtDtlHd.setString(5, lStrMnrHdCode.trim());
            lPStmtDtlHd.setString(6, lStrSubHdCode.trim());
            lPStmtDtlHd.setString(7, lStrLocID.trim());
            lPStmtDtlHd.setString(8, lStrLangID.trim());
            lPStmtDtlHd.setString(9, lStrSts.trim());

            glogger.debug("Sub Head Query is : " + lSbSql.toString());
            glogger.debug("Params for getDtlHds are :  " + lStrBPNCode.trim() + "," + lStrDemandCode.trim() + "," +
                lStrMjrHdCode.trim() + "," + lStrSbMjrHdCode.trim() + "," + lStrMnrHdCode.trim() + "," + lStrSubHdCode.trim() + "," +
                lStrLocID.trim() + "," + lStrLangID.trim() + "," + lStrSts.trim());
            lRsDtlHd = lPStmtDtlHd.executeQuery();

            while(lRsDtlHd.next())
            {
                ComboValuesVO lObjBudDtlVO = new ComboValuesVO(); //Instance of Value Object

                lObjBudDtlVO.setId(lRsDtlHd.getString("BUDDTLHD_CODE")); //For Detail head Code
                lObjBudDtlVO.setDesc(lRsDtlHd.getString("BUDDTLHD_DESC_LONG")); //For Detail head Description

                glogger.debug("Value set is : " + lObjBudDtlVO.getId() + "_" + lObjBudDtlVO.getDesc());

                lArrDtlHd.add(lObjBudDtlVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getDtlHds Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getDtlHds Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsDtlHd);
           closeStatement(lPStmtDtlHd);
           closeConnection(lCon);
        }
        return lArrDtlHd;
    }

    //End of getSbMjrHds Metod
    //Added by Ravi for getting Unit ID of EBUD- for FD
    public ArrayList getEBUDUnitID(String lStrDeptID) throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtUnit = null;
        ResultSet lrsUnit = null;
        StringBuffer lsbUnit = new StringBuffer();
        ArrayList lArrUnitID = new ArrayList();
        glogger.debug("Entering into getUnitID():");

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            lsbUnit.append("SELECT UNIT_ID FROM SGVO_UNIT_MST");
            lsbUnit.append(" WHERE LEVEL_ID=? AND DEPT_ID=? AND UNIT_TYPE=?");
            lsbUnit.append(" AND LANG_ID=? AND LOC_ID=?");

            lPstmtUnit = lcon.prepareStatement(lsbUnit.toString());

            glogger.debug("Query For getUnitID is :  " + lsbUnit.toString());
            glogger.debug("Params For getUnitID are : " + liUntLvl4Brnch + "," + lStrDeptID.trim() + "," + lStrEBUDBranchType.trim() +
                "," + lStrConstLangID.trim() + "," + lStrConstLocID.trim());

            glogger.debug("liUntLvl4Brnch:" + liUntLvl4Brnch); //liUntLvl4Brnch=10
            glogger.debug("lStrBranchType:" + lStrEBUDBranchType);
            glogger.debug("lStrConstLangID:" + lStrConstLangID);
            glogger.debug("lStrConstLocID:" + lStrConstLocID);
            glogger.debug("Dept:" + lStrDeptID);

            lPstmtUnit.setInt(1, liUntLvl4Brnch);
            lPstmtUnit.setString(2, lStrDeptID.trim());
            lPstmtUnit.setString(3, lStrEBUDBranchType.trim());
            lPstmtUnit.setString(4, lStrConstLangID.trim());
            lPstmtUnit.setString(5, lStrConstLocID.trim());

            lrsUnit = lPstmtUnit.executeQuery();

            while(lrsUnit.next())
            {
                if(lrsUnit.getString("UNIT_ID") != null)
                {
                    lArrUnitID.add(lrsUnit.getString("UNIT_ID")); // 
                }

                glogger.debug(lArrUnitID);
            }
        }

        //try
        catch(SQLException e)
        {
            glogger.error("SQL Exception occured in  getUnitID() of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in  getUnitID() of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsUnit);
           closeStatement(lPstmtUnit);
           closeConnection(lcon);
        }

        //finally
        return lArrUnitID;
    }

    /**
     * Method getEmployeeType returns the Employee Type of logged in user ("MIN" or "FA" or "")
     *
     * @param lStrUnitID Unit ID Array
     *
     * @return [TODO Add return documentation here]
     */

    //Added By Ravishankar
    public String getEmployeeType(String[] lStrUnitID)
    {
        String lStrEmpType = "";
        Connection lCon = null;
        ResultSet lRs = null;
        StringBuffer lSbSql = new StringBuffer();
        PreparedStatement lpStmt = null;
        int cnt = 0;
        int k = 0;
        int[] liLevel = new int[lStrUnitID.length];

        try
        {
            lCon =getConnection();

            //Get Level IDs for input Unit IDs
            lSbSql.append(" SELECT U.LEVEL_ID FROM SGVO_UNIT_MST U WHERE U.UNIT_ID IN (");

            for(int i = 0; i < lStrUnitID.length; i++) //For each input unit
            {
                if(i == (lStrUnitID.length - 1))
                {
                    lSbSql.append(" ?");
                }
                else
                {
                    lSbSql.append(" ?,");
                }
            }

            lSbSql.append(" )");
            lSbSql.append(" AND LANG_ID=? AND LOC_ID=?");

            lpStmt = lCon.prepareStatement(lSbSql.toString());

            for(int i = 0; i < lStrUnitID.length; i++) //For each input unit
            {
                lpStmt.setString(++cnt, lStrUnitID[i].trim());
            }

            lpStmt.setString(++cnt, lStrConstLangID.trim()); //Constant Lang ID='en_US'
            lpStmt.setString(++cnt, lStrConstLocID.trim()); //Constant Loc ID='LC1'

            lRs = lpStmt.executeQuery();

            while(lRs.next())
            {
                if(lRs.getString("LEVEL_ID") != null)
                {
                    /*if(lRs.getString("LEVEL_ID").equals("" + liUntLvl4Min)) //liUntLvl4Min=60...Level of Minister
                       {
                           lStrEmpType = "MIN";
                       }*/

                    //Commented by Ravi for approval rights of US and Above
                    //Edited By Ravi for Await rights to CS,ACS etc.
                    //if((Integer.parseInt(lRs.getString("LEVEL_ID")) < liUntLvl4Min) &&
                    //(Integer.parseInt(lRs.getString("LEVEL_ID")) >= liUntLvl4Secy)) //liUntLvl4Secy=50...Level of Secretary
                    if((Integer.parseInt(lRs.getString("LEVEL_ID")) >= liUntLvl4Secy)) //liUntLvl4Secy = 30 (US level- changed by Ravi)
                    {
                        lStrEmpType = "SEC";
                    }

                    liLevel[k++] = Integer.parseInt(lRs.getString("LEVEL_ID"));
                }
            }

            //Commented by Ravi for Prioritize FA over MIN
            /*if(!lStrEmpType.equals("")) //Return if Minister or Secy
               {
                   return lStrEmpType;
               }*/
           closeResultSet(lRs); // Added By Keyur
            lRs = null;
           closeStatement(lpStmt); // Added By Keyur
            lpStmt = null;

            for(int i = 0; i < lStrUnitID.length; i++) //For each input unit
            {
                lSbSql.delete(0, lSbSql.length()); //Clear String Buffer
                lSbSql.append(" SELECT UNIQUE UNIT_TYPE  FROM(");
                lSbSql.append(" SELECT U.UNIT_TYPE,U.LANG_ID,");
                lSbSql.append(" U.LEVEL_ID,U.LOC_ID FROM SGVO_UNIT_MST U START WITH ");
                lSbSql.append(" U.UNIT_ID =?");
                lSbSql.append(" CONNECT BY PRIOR");

                if(liLevel[i] > 10)
                {
                    lSbSql.append(" U.UNIT_ID = U.PARENT_UNIT_ID)");
                }
                else
                {
                    lSbSql.append(" U.PARENT_UNIT_ID = U.UNIT_ID)");
                }

                lSbSql.append(" WHERE LEVEL_ID = ? AND LANG_ID = ? AND LOC_ID= ?");

                lpStmt = lCon.prepareStatement(lSbSql.toString());

                lpStmt.setString(1, lStrUnitID[i].trim());
                lpStmt.setString(2, "" + liUntLvl4Brnch); //Constant Lang ID='en_US'
                lpStmt.setString(3, lStrConstLangID.trim()); //Constant Lang ID='en_US'
                lpStmt.setString(4, lStrConstLocID.trim()); //Constant Loc ID='LC1'

                lRs = lpStmt.executeQuery();

                while(lRs.next())
                {
                    if(lRs.getString("UNIT_TYPE") != null)
                    {
                        if(lRs.getString("UNIT_TYPE").equals("FA"))
                        {
                            lStrEmpType = "FA";
                        }
                    }
                }

               closeResultSet(lRs); // Added By Keyur
                lRs = null;
               closeStatement(lpStmt); // Added By Keyur
                lpStmt = null;
            }

            //for
        }

        //try
        catch(SQLException sqle)
        {
            glogger.error("SQL Exception in getEmployeeType method of BudHdDAOImpl : " + sqle);
        }
        catch(Exception e)
        {
            glogger.error("Exception in getEmployeeType method of BudHdDAOImpl : " + e);
        }
        finally
        {
           closeResultSet(lRs);
           closeStatement(lpStmt);
           closeConnection(lCon);
        }

        return lStrEmpType;
    }

    //Added By Ronak on 04/12/1006

    /**
     * Method getLSLvlTenUnitID()
     *
     * @param lStrDeptID of string DeptID
     * @param lStrSubID of string SubID
     *
     * @return String (return string of unit id)
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public String getLSLvlTenUnitID(String lStrDeptID, String lStrSubID) throws SQLException, Exception
    {
        String strLvlTenUnitID = null;
        Connection lcon = null;
        PreparedStatement lPstmtUnit = null;
        ResultSet lrsUnit = null;
        StringBuffer lsbUnit = new StringBuffer();
        StringBuffer lSb = new StringBuffer();
        glogger.debug("Entering into getUnitID():");

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            /*
               SELECT U.UNIT_ID FROM SGVO_UNIT_MST U WHERE U.UNIT_ID IN
                (
                SELECT S.UNIT_ID FROM SGVO_UNITLSB_MPG S WHERE S.MULTILGSUB_ID IN
                (
                SELECT L.LGSUB_ID FROM SGVO_LSB_MST L WHERE L.MULTISUB_ID='350011')
                ) AND U.DEPT_ID='DP101' AND U.LANG_ID='en_US' AND U.LEVEL_ID=10
             */
            lSb.append("SELECT U.UNIT_ID FROM SGVO_UNIT_MST U WHERE U.UNIT_ID IN ");
            lSb.append("  ( SELECT S.UNIT_ID FROM SGVO_UNITLSB_MPG S WHERE S.MULTILGSUB_ID IN ");
            lSb.append(" ( SELECT L.LGSUB_ID FROM SGVO_LSB_MST L WHERE L.MULTISUB_ID=?) ");
            lSb.append(" ) AND U.DEPT_ID=? AND U.LANG_ID='en_US' AND U.LEVEL_ID=10");

            lPstmtUnit = lcon.prepareStatement(lSb.toString());
            glogger.debug("Query For getting dept id is :  " + lSb.toString());

            lPstmtUnit.setString(1, lStrSubID);
            lPstmtUnit.setString(2, lStrDeptID);

            lrsUnit = lPstmtUnit.executeQuery();

            if(lrsUnit.next())
            {
                if(lrsUnit.getString("UNIT_ID") != null)
                {
                    strLvlTenUnitID = lrsUnit.getString("UNIT_ID");
                }

                glogger.debug("Getting:" + strLvlTenUnitID);
            }
        }

        //try
        catch(SQLException e)
        {
            glogger.error("SQL Exception occured in  getLSLvlTenUnitID() of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in  getLSLvlTenUnitID() of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsUnit);
           closeStatement(lPstmtUnit);
           closeConnection(lcon);
        }

        //finally
        return strLvlTenUnitID;
    }

    /**
     * Method getLSUnitID()
     *
     * @param strUnitIDs array of string UnitID
     * @param strSubID array of string SubID
     *
     * @return String (return string of unit id)
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public String getLSUnitID(String[] strUnitIDs, String strSubID) throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtHds = null;
        ResultSet lrsHds = null;
        StringBuffer lsbHds = new StringBuffer();
        ArrayList UnitIdList = new ArrayList();
        String strUnitID = null;

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            /*
               SELECT LM.UNIT_ID FROM SGVO_UNITLSB_MPG LM WHERE LM.MULTILGSUB_ID IN
                (
                SELECT M.LGSUB_ID FROM SGVO_LSB_MST M WHERE M.MULTISUB_ID='350012'
                )
             */
            lsbHds.append("SELECT LM.UNIT_ID FROM SGVO_UNITLSB_MPG LM WHERE LM.MULTILGSUB_ID IN");
            lsbHds.append(" (SELECT M.LGSUB_ID FROM SGVO_LSB_MST M WHERE M.MULTISUB_ID= ?  )");

            //glogger.debug(lsb.toString());
            lPstmtHds = lcon.prepareStatement(lsbHds.toString());

            lPstmtHds.setString(1, strSubID.trim());
            lrsHds = lPstmtHds.executeQuery();

            while(lrsHds.next())
            {
                if(lrsHds.getString("UNIT_ID") != null)
                {
                    UnitIdList.add(lrsHds.getString("UNIT_ID"));
                }
            }

            // logic
            int cnt = 0;

            for(int i = 0; i < strUnitIDs.length; i++)
            {
                if(UnitIdList.contains(strUnitIDs[i]))
                {
                    strUnitID = strUnitIDs[i];
                    cnt++;
                }
            }

            if((cnt > 1) || (cnt == 0))
            {
                strUnitID = strUnitIDs[0];
            }
        }
        catch(SQLException e)
        {
            glogger.error("Error occured in SQL:getLSUnitID Method of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in getLSUnitID Method of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsHds);
           closeStatement(lPstmtHds);
           closeConnection(lcon);
        }

        glogger.debug("" + strUnitID);

        return strUnitID;
    }

    /**
     * It returns Minister In Charge List
     *
     * @param lStrDeptId Dept ID
     * @param lStrLangId Lang ID
     * @param lStrLocId LOC ID
     *
     * @return ArrayList of Values
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getMinInCharge(String lStrDeptId, String lStrLangId, String lStrLocId)
        throws SQLException, Exception
    {
        Connection lCon = null;
        ArrayList lArrMinister = new ArrayList();
        ResultSet lRs = null;
        StringBuffer lSbSql = new StringBuffer();
        PreparedStatement lStmt = null;
        ComboValuesVO lObjComboVO = null;

        //
        try
        {
            lCon =getConnection();

            //lSbSql.append(
            //" SELECT EMP.FIRST_NAME||' '||EMP.MIDDLE_NAME||' '||EMP.LAST_NAME FULL_NAME,EMP.EMP_ID  FROM SGVO_UNITEMP_MPG MPG,SGVO_UNIT_MST MST, SGVC_EMP_MST EMP WHERE MPG.UNIT_ID = MST.UNIT_ID AND MST.DEPT_ID = ? AND ");
            //lSbSql.append(
            //" MPG.EMP_ID = EMP.EMP_ID AND EMP.DSGN_ID =? AND MST.LANG_ID = EMP.LANG_ID AND MST.LOC_ID = EMP.LOC_ID AND EMP.LANG_ID =? AND EMP.LOC_ID =?");
            lSbSql.append("SELECT EMP.FIRST_NAME || ' ' || EMP.MIDDLE_NAME || ' ' || EMP.LAST_NAME FULL_NAME, ");
            lSbSql.append("       EMP.EMP_ID ");
            lSbSql.append("  FROM SGVO_UNITEMP_MPG MPG, SGVO_UNIT_MST MST, SGVC_EMP_MST EMP ");
            lSbSql.append(" WHERE MPG.UNIT_ID = MST.UNIT_ID AND MST.DEPT_ID = ? AND ");
            lSbSql.append("       MPG.EMP_ID = EMP.EMP_ID AND mst.level_id >= 58 AND ");
            lSbSql.append("       MST.LANG_ID = EMP.LANG_ID AND MST.LOC_ID = EMP.LOC_ID AND ");
            lSbSql.append("       EMP.LANG_ID = ? AND EMP.LOC_ID = ? ");
            glogger.debug("\n" + lSbSql.toString());
            lStmt = lCon.prepareStatement(lSbSql.toString());
            lStmt.setString(1, lStrDeptId);
            glogger.debug(" Setting  dept id " + lStrDeptId);

            //lStmt.setString(2, lBudConstantsBundle.getString("BUD.StmnstrDsgn"));
            //glogger.debug(" Setting  Dgsn Of Minister " + lBudConstantsBundle.getString("BUD.StmnstrDsgn"));
            lStmt.setString(2, lStrLangId);
            glogger.debug("Setting langId " + lStrLangId);
            lStmt.setString(3, lStrLocId);
            glogger.debug(" setting loc id ..." + lStrLocId);
            lRs = lStmt.executeQuery();

            while(lRs.next())
            {
                lObjComboVO = new ComboValuesVO();
                lObjComboVO.setId(lRs.getString("EMP_ID"));
                lObjComboVO.setDesc(lRs.getString("FULL_NAME"));
                lArrMinister.add(lObjComboVO); //Adding VO's Object to Array List
            }

            /*if(lArrMinister.size()==0)//Commented by Ravi
               {
                  closeResultSet(lRs); // Added By Keyur
                   lRs = null;
                  closeStatement(lStmt); // Added By Keyur
                   lStmt = null;
                   lSbSql = new StringBuffer();
                   lSbSql.append(
                       " SELECT EMP.FIRST_NAME||' '||EMP.MIDDLE_NAME||' '||EMP.LAST_NAME FULL_NAME,EMP.EMP_ID  FROM SGVO_UNITEMP_MPG MPG,SGVO_UNIT_MST MST, SGVC_EMP_MST EMP WHERE MPG.UNIT_ID = MST.UNIT_ID AND MST.DEPT_ID = ? AND ");
                   lSbSql.append(
                       " MPG.EMP_ID = EMP.EMP_ID AND EMP.DSGN_ID =? AND MST.LANG_ID = EMP.LANG_ID AND MST.LOC_ID = EMP.LOC_ID AND EMP.LANG_ID =? AND EMP.LOC_ID =?");
                   glogger.debug("\n" + lSbSql.toString());
                   lStmt = lCon.prepareStatement(lSbSql.toString());
                   lStmt.setString(1, lStrDeptId);
                   glogger.debug(" Setting  dept id " + lStrDeptId);
                   lStmt.setString(2, lBudConstantsBundle.getString("BUD.ChfmnstrDsgn"));
                   glogger.debug(" Setting  Dgsn Of Minister " + lBudConstantsBundle.getString("BUD.ChfmnstrDsgn"));
                   lStmt.setString(3, lStrLangId);
                   glogger.debug("Setting langId " + lStrLangId);
                   lStmt.setString(4, lStrLocId);
                   glogger.debug(" setting loc id ..." + lStrLocId);
                   lRs = lStmt.executeQuery();
                   while(lRs.next())
                   {
                       lObjComboVO = new ComboValuesVO();
                       lObjComboVO.setId(lRs.getString("EMP_ID"));
                       lObjComboVO.setDesc(lRs.getString("FULL_NAME"));
                       lArrMinister.add(lObjComboVO); //Adding VO's Object to Array List
                   }
               }*/
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getMinInCharge Method And Error is " + se, se);
            se.printStackTrace();
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getMinInCharge Method And Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
        finally
        {
           closeResultSet(lRs);
           closeStatement(lStmt);
           closeConnection(lCon);
        }

        glogger.debug("Size of lArrMinister in DAO--->" + lArrMinister.size());

        return lArrMinister;
    }

    /**
     * This method returns MjrHds based on BPN passed. Its used in BudExpEst Book Printing By Keyur.
     *
     * @param lStrBPNCode BPN Code
     * @param lStrMhrHdType Major Head Type
     * @param lStrLangID [TODO Add parameter documentation here]
     * @param lStrLocID [TODO Add parameter documentation here]
     * @param lStrSts [TODO Add parameter documentation here]
     *
     * @return [TODO Add return documentation here]
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getMjrHdByBPN(String lStrBPNCode, String lStrMhrHdType, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtMjrHd = null;
        ResultSet lRsMjrHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrMjrHd = new ArrayList();
        Hashtable lHashInfo = null;

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(" SELECT T.BUDMJRHD_CODE MJR_CODE, INITCAP(NVL(T.BUDMJRHD_DESC_LONG, '---------------')) MJR_DESC,");
            lSbSql.append(" T.DEMAND_CODE DMD_CODE, INITCAP(D.DEMAND_DESC) DMD_DESC FROM SGVA_BUDMJRHD_MST T, SGVA_BUDDEMAND_MST D");
            lSbSql.append(" WHERE T.BUDMJRHDTYPE = ? AND T.BPN_CODE = ? AND T.LOC_ID = ? AND");
            lSbSql.append(" T.LANG_ID = ? AND T.STATUS LIKE ? AND T.DEMAND_CODE = D.DEMAND_CODE AND D.LANG_ID = ?");
            lSbSql.append(" ORDER BY T.BUDMJRHD_CODE");

            lPStmtMjrHd = lCon.prepareStatement(lSbSql.toString());
            lPStmtMjrHd.setString(1, lStrMhrHdType);
            lPStmtMjrHd.setString(2, lStrBPNCode);
            lPStmtMjrHd.setString(3, lStrLocID);
            lPStmtMjrHd.setString(4, lStrLangID);
            lPStmtMjrHd.setString(5, lStrSts);
            lPStmtMjrHd.setString(6, lStrLangID);

            glogger.debug("getMjrHdByBPN Query is : " + lSbSql.toString());
            glogger.debug("Params for getMjrHdByBPN are :  " + lStrMhrHdType + "," + lStrBPNCode + "," + lStrLocID + "," + lStrLangID +
                "," + lStrSts + "," + lStrLangID);

            lRsMjrHd = lPStmtMjrHd.executeQuery();

            while(lRsMjrHd.next())
            {
                lHashInfo = new Hashtable();
                lHashInfo.put("MJR_CODE", lRsMjrHd.getString("MJR_CODE"));
                lHashInfo.put("MJR_DESC", lRsMjrHd.getString("MJR_DESC"));
                lHashInfo.put("DMD_CODE", lRsMjrHd.getString("DMD_CODE"));
                lHashInfo.put("DMD_DESC", lRsMjrHd.getString("DMD_DESC"));
                lArrMjrHd.add(lHashInfo);
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getMjrHdByBPN Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getMjrHdByBPN Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsMjrHd);
           closeStatement(lPStmtMjrHd);
           closeConnection(lCon);
        }

        return lArrMjrHd;
    }

    /**
     * method getMjrHdByDmd
     *
     * @param lStrSts [TODO Add parameter documentation here]
     * @param lStrBPNCode Demand ID ( ID-Expenditure and -1 -Receipt)
     * @param lStrMhrHdType Major Head Type ( R-Reciept or E-Expenditure)
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status of the Major Head (In this case Status="Y")
     *
     * @return ArrayList of All Major Head Code and description
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getMjrHdByDmd(String lStrDemandCode, String lStrBPNCode, String lStrMhrHdType, String lStrLangID,
        String lStrLocID, String lStrSts) throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtMjrHd = null;
        ResultSet lRsMjrHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrMjrHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(
                " SELECT T.BUDMJRHD_ID, T.BUDMJRHD_CODE,INITCAP(TRIM(T.BUDMJRHD_DESC_LONG)) BUDMJRHD_DESC_LONG,T.BUDMJRHDSUBTYPE");
            lSbSql.append(" FROM SGVA_BUDMJRHD_MST T");
            lSbSql.append(" WHERE T.BUDMJRHDTYPE LIKE ?");
            lSbSql.append(" AND T.BPN_CODE = ?");
            lSbSql.append(" AND T.DEMAND_CODE = ?");
            lSbSql.append(" AND T.LOC_ID = ?");
            lSbSql.append(" AND T.LANG_ID = ?");
            lSbSql.append(" AND T.STATUS = ? ORDER BY T.BUDMJRHD_CODE"); // Edited by Keyur

            lPStmtMjrHd = lCon.prepareStatement(lSbSql.toString());
            lPStmtMjrHd.setString(1, lStrMhrHdType.trim());
            lPStmtMjrHd.setString(2, lStrBPNCode.trim());
            lPStmtMjrHd.setString(3, lStrDemandCode.trim());
            lPStmtMjrHd.setString(4, lStrLocID.trim());
            lPStmtMjrHd.setString(5, lStrLangID.trim());
            lPStmtMjrHd.setString(6, lStrSts); // Added by Keyur

            glogger.debug("getMjrHdByDmd Query is : " + lSbSql.toString());
            glogger.debug("Params for getMjrHdByDmd are :  " + lStrMhrHdType.trim() + "," + lStrBPNCode.trim() + "," +
                lStrDemandCode.trim() + "," + lStrLocID.trim() + "," + lStrLangID.trim());

            lRsMjrHd = lPStmtMjrHd.executeQuery();

            while(lRsMjrHd.next())
            {
                BudMjrHdVO lObjBudMjrHdVO = new BudMjrHdVO();

                lObjBudMjrHdVO.setMjrHdID(lRsMjrHd.getInt(1)); //For Sub Major Head ID
                lObjBudMjrHdVO.setMjrHdCode(lRsMjrHd.getString(2)); //For Sub Major Head Code
                lObjBudMjrHdVO.setMjrHdLongDesc(lRsMjrHd.getString(3)); //For Sub Major Head Long Description
                lObjBudMjrHdVO.setMjrHdSubType(lRsMjrHd.getString(4)); //For Major head sub type
                lArrMjrHd.add(lObjBudMjrHdVO);
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getAllMjrHds Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getAllMjrHds Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsMjrHd);
           closeStatement(lPStmtMjrHd);
           closeConnection(lCon);
        }

        return lArrMjrHd;
    }

    /**
     * Ronak N. Shah method getMjrHdByDmd
     *
     * @param lStrMhrHdType Major Head Type ( R-Reciept or E-Expenditure)
     * @param lStrMhrHdType Major Head Sub Type ( R-Revenue or C-Capital)
     * @param lStrMhrHdType Demand ID ( ID-Expenditure and -1 -Receipt)
     * @param lStrSts [TODO Add parameter documentation here]
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status of the Major Head (In this case Status="Y")
     *
     * @return ArrayList of All Major Head Code and description
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getMjrHdByDmd(String lStrDemandCode, String lStrBPNCode, String lStrMhrHdType, String lStrMhrHdSubType,
        String lStrLangID, String lStrLocID, String lStrSts) throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtMjrHd = null;
        ResultSet lRsMjrHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrMjrHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(
                " SELECT T.BUDMJRHD_ID, T.BUDMJRHD_CODE,INITCAP(NVL(TRIM(T.BUDMJRHD_DESC_LONG),'---------------'))BUDMJRHD_DESC_LONG");
            lSbSql.append(" FROM SGVA_BUDMJRHD_MST T");
            lSbSql.append(" WHERE T.BUDMJRHDTYPE = ?");
            lSbSql.append(" AND T.BUDMJRHDSUBTYPE = ?");
            lSbSql.append(" AND T.BPN_CODE = ?");
            lSbSql.append(" AND T.DEMAND_CODE = ?");
            lSbSql.append(" AND T.LOC_ID = ?");
            lSbSql.append(" AND T.LANG_ID = ?");
            lSbSql.append(" AND T.STATUS = ?"); // Edited by Keyur

            lPStmtMjrHd = lCon.prepareStatement(lSbSql.toString());
            lPStmtMjrHd.setString(1, lStrMhrHdType.trim());
            lPStmtMjrHd.setString(2, lStrMhrHdSubType.trim());
            lPStmtMjrHd.setString(3, lStrBPNCode.trim());
            lPStmtMjrHd.setString(4, lStrDemandCode.trim());
            lPStmtMjrHd.setString(5, lStrLocID.trim());
            lPStmtMjrHd.setString(6, lStrLangID.trim());
            lPStmtMjrHd.setString(7, lStrSts); // Added by Keyur

            glogger.debug("getMjrHdByDmd Query is : " + lSbSql.toString());
            glogger.debug("Params for getMjrHdByDmd are :  " + lStrMhrHdType.trim() + "," + lStrMhrHdSubType.trim() + "," +
                lStrBPNCode.trim() + "," + lStrDemandCode.trim() + "," + lStrLocID.trim() + "," + lStrLangID.trim());

            lRsMjrHd = lPStmtMjrHd.executeQuery();

            while(lRsMjrHd.next())
            {
                BudMjrHdVO lObjBudMjrHdVO = new BudMjrHdVO();

                lObjBudMjrHdVO.setMjrHdID(lRsMjrHd.getInt(1)); //For Sub Major Head ID
                lObjBudMjrHdVO.setMjrHdCode(lRsMjrHd.getString(2)); //For Sub Major Head Code
                lObjBudMjrHdVO.setMjrHdLongDesc(lRsMjrHd.getString(3)); //For Sub Major Head Long Description

                lArrMjrHd.add(lObjBudMjrHdVO);
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getMjrHdByDmd Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getMjrHdByDmd Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsMjrHd);
           closeStatement(lPStmtMjrHd);
           closeConnection(lCon);
        }

        return lArrMjrHd;
    }

    //END OF getMjrHdByDmd Method

    /**
     * Method getMnrHds returns List of all Minor Head from SGVA_BUDMINHD_MST
     *
     * @param lStrBPNCode BPN Code
     * @param lStrDemandCode Demand Code
     * @param lStrMjrHdCode Major Head Code
     * @param lStrSbMjrHdCode Sub Major Head Code
     * @param lStrHdType [TODO Add parameter documentation here]
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudExpEstMnrHdVO which returns List of all Minor Head Code,ID,Desc depends on Major Head ID, Sub Major
     *         Head ID
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getMnrHds(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCode, String lStrSbMjrHdCode,
        String lStrHdType, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtMnrHd = null;
        ResultSet lRsMnrHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrMnrHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(
                " SELECT M.BUDMINHD_ID, M.BUDMINHD_CODE,INITCAP(NVL(M.BUDMINHD_DESC_LONG,'------------')) BUDMINHD_DESC_LONG");
            lSbSql.append(" FROM SGVA_BUDMINHD_MST M");
            lSbSql.append(" WHERE M.BPN_CODE = ? ");
            lSbSql.append(" AND M.DEMAND_CODE = ? ");
            lSbSql.append(" AND M.BUDMJRHD_CODE = ? ");
            lSbSql.append(" AND M.BUDSUBMJRHD_CODE = ? ");
            lSbSql.append(" AND M.LOC_ID = ?");
            lSbSql.append(" AND M.LANG_ID = ?");
            lSbSql.append(" AND M.STATUS LIKE ? ORDER BY M.BUDMINHD_CODE");

            lPStmtMnrHd = lCon.prepareStatement(lSbSql.toString());

            lPStmtMnrHd.setString(1, lStrBPNCode.trim());
            lPStmtMnrHd.setString(2, lStrDemandCode.trim());
            lPStmtMnrHd.setString(3, lStrMjrHdCode.trim());
            lPStmtMnrHd.setString(4, lStrSbMjrHdCode.trim());
            lPStmtMnrHd.setString(5, lStrLocID.trim());
            lPStmtMnrHd.setString(6, lStrLangID.trim());
            lPStmtMnrHd.setString(7, lStrSts.trim());

            glogger.debug("Query For getMnrHds is : " + lSbSql.toString());
            glogger.debug("Params for getMnrHds are :  " + lStrBPNCode.trim() + "," + lStrDemandCode.trim() + "," +
                lStrMjrHdCode.trim() + "," + lStrSbMjrHdCode.trim() + "," + lStrLocID.trim() + "," + lStrLangID.trim() + "," +
                lStrSts.trim());

            lRsMnrHd = lPStmtMnrHd.executeQuery();

            while(lRsMnrHd.next())
            {
                BudExpEstMnrHdVO lObjBudExpEstMnrHdVO = new BudExpEstMnrHdVO();

                lObjBudExpEstMnrHdVO.setMnrHdID(lRsMnrHd.getInt(1)); //For Minor Head ID
                lObjBudExpEstMnrHdVO.setMnrHdCode(lRsMnrHd.getString(2)); //For Minor Head Code
                lObjBudExpEstMnrHdVO.setMnrHdLngDesc(lRsMnrHd.getString(3)); //For Minor Head Long Description

                lArrMnrHd.add(lObjBudExpEstMnrHdVO);
            }
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getMnrHds Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getMnrHds Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsMnrHd);
           closeStatement(lPStmtMnrHd);
           closeConnection(lCon);
        }

        return lArrMnrHd;
    }

    /**
     * It returns ModEntry for Scheme Report based on Emp Id
     *
     * @param lStrEmpID Emp ID
     *
     * @return Mode Entry
     */
    public String getModEntryForSchemeReport(String lStrEmpID)
    {
        Connection lCon = null;
        ResultSet lRsStr = null;
        PreparedStatement lPstmt = null;
        String lStrModEntry = "";
        StringBuffer lSbStr = null;

        try
        {
            lCon =getConnection();
            lSbStr = new StringBuffer();

            lSbStr.append("SELECT MD.MOD_ID MOD_ID, MD.SUB_URL MOD_URL FROM SGVS_USR_GRPS_MPG UG, SGVS_MOD_GRP_MPG MG,");
            lSbStr.append(" SGVS_MOD_MST MD WHERE UG.EMPGRP_ID = ? AND UG.GRP_ID = MG.GRP_ID AND MG.MOD_ID IN (?,?)");
            lSbStr.append(" AND MG.MOD_ID = MD.MOD_ID AND UG.LANG_ID = ? AND MG.LANG_ID = ? AND MD.LANG_ID = ?");

            lPstmt = lCon.prepareStatement(lSbStr.toString());
            lPstmt.setString(1, lStrEmpID);
            lPstmt.setString(2, lBudConstantsBundle.getString("BudSchmeRpt.ModEntry1"));
            lPstmt.setString(3, lBudConstantsBundle.getString("BudSchmeRpt.ModEntry2"));
            lPstmt.setString(4, lStrConstLangID);
            lPstmt.setString(5, lStrConstLangID);
            lPstmt.setString(6, lStrConstLangID);

            glogger.debug("Query For getModEntryForSchemeReport is : " + lSbStr.toString());
            glogger.debug("Emp Id for method is : " + lStrEmpID);

            lRsStr = lPstmt.executeQuery();

            while(lRsStr.next())
            {
                lStrModEntry = lRsStr.getString("MOD_URL");
            }

            glogger.debug("lStrModEntry is : " + lStrModEntry);
        }

        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAOImpl SQL in getModEntryForSchemeReport Method And Error is  " + se, se);
            se.printStackTrace();
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAOImpl SQL in getModEntryForSchemeReport Method  And Error is  " + e, e);
            e.printStackTrace();
        }
        finally
        {
           closeResultSet(lRsStr);
           closeStatement(lPstmt);
           closeConnection(lCon);
        }

        return lStrModEntry;
    }

    //End of getMnrHds Metod [Minor Head Method]

    /**
     * Method getSbHds returns List of all Minor Head from SGVA_BUDSUBHD_MST
     *
     * @param lStrBPNCode BPN CODE
     * @param lStrDemandCode Demand ID
     * @param lStrMjrHdCode Major Head ID
     * @param lStrSbMjrHdCode Sub Major Head ID
     * @param lStrMnrHdCode Minor Head ID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudSbHdVO which returns List of all Sub Head Code,ID,Desc depends
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getSbHds(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCode, String lStrSbMjrHdCode,
        String lStrMnrHdCode, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtSbHd = null;
        ResultSet lRsSbHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrSbHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(
                " SELECT T.BUDSUBHD_ID, T.BUDSUBHD_CODE, INITCAP(NVL(T.BUDSUBHD_DESC_LONG,'------------')) BUDSUBHD_DESC_LONG,");
            lSbSql.append(" NVL(T.EDPCODE,' ') EDPCODE, NVL(T.PLNGCODE,' ') PLANCODE FROM sgva_budsubhd_mst T");
            lSbSql.append(" WHERE T.BPN_CODE = ?");
            lSbSql.append(" AND T.DEMAND_CODE = ?");
            lSbSql.append(" AND T.BUDMJRHD_CODE = ? ");
            lSbSql.append(" AND T.BUDSUBMJRHD_CODE = ? ");
            lSbSql.append(" AND T.BUDMINHD_CODE = ? ");
            lSbSql.append(" AND T.LOC_ID = ?");
            lSbSql.append(" AND T.LANG_ID = ?");
            lSbSql.append(" AND T.STATUS LIKE ? ORDER BY T.BUDSUBHD_CODE");

            lPStmtSbHd = lCon.prepareStatement(lSbSql.toString());

            lPStmtSbHd.setString(1, lStrBPNCode.trim());
            lPStmtSbHd.setString(2, lStrDemandCode.trim());
            lPStmtSbHd.setString(3, lStrMjrHdCode.trim());
            lPStmtSbHd.setString(4, lStrSbMjrHdCode.trim());
            lPStmtSbHd.setString(5, lStrMnrHdCode.trim());
            lPStmtSbHd.setString(6, lStrLocID.trim());
            lPStmtSbHd.setString(7, lStrLangID.trim());
            lPStmtSbHd.setString(8, lStrSts.trim());

            glogger.debug("Sub Head Query is : " + lSbSql.toString());
            glogger.debug("Params for getSbHds are :  " + lStrBPNCode.trim() + "," + lStrDemandCode.trim() + "," +
                lStrMjrHdCode.trim() + "," + lStrSbMjrHdCode.trim() + "," + lStrMnrHdCode.trim() + "," + lStrLocID.trim() + "," +
                lStrLangID.trim() + "," + lStrSts.trim());
            lRsSbHd = lPStmtSbHd.executeQuery();

            while(lRsSbHd.next())
            {
                BudExpEstSbHdVO lObjBudExpEstSbHdVO = new BudExpEstSbHdVO(); //Instance of Value Object

                lObjBudExpEstSbHdVO.setSbHdID(lRsSbHd.getInt(1)); //For Expenditure Sub  Head ID
                lObjBudExpEstSbHdVO.setSbHdCode(lRsSbHd.getString(2)); //For Sub Head Code
                lObjBudExpEstSbHdVO.setSbHdLngDesc(lRsSbHd.getString(3)); //For Sub Head Long Description
                lObjBudExpEstSbHdVO.setEDPCode(lRsSbHd.getString("EDPCODE")); // Added by Keyur for EDP Code
                lObjBudExpEstSbHdVO.setPlanningCode(lRsSbHd.getString("PLANCODE")); // Added by Keyur for Planning Code

                glogger.debug("Value set is : " + lObjBudExpEstSbHdVO.getEDPCode() + "_" + lObjBudExpEstSbHdVO.getPlanningCode());

                //lObjBudExpEstSbHdVO.setSbHdShrtDesc(lRsSbHd.getString(4));//For Sub Head Short Description
                lArrSbHd.add(lObjBudExpEstSbHdVO); //Adding VO's Object to Array List
            }
        }

        //End of Try
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getSbHds Method And Error is " + se, se);
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getSbHds Method And Error is " + e, e);
            throw e;
        }
        finally
        {
           closeResultSet(lRsSbHd);
           closeStatement(lPStmtSbHd);
           closeConnection(lCon);
        }

        return lArrSbHd;
    }

    //End of getSbHds Metod [Sub Head Code]
    //************************************ For Branch details **************************************************
    //CHIRAG ADDED THIS METHOD ON 03-dec-2005 FROM HERE

    /**
     * Method getSbMjrHds returns List of all Sub Major Head from sgva_budsubmjrhd_mst
     *
     * @param lStrBPNCode Major Head Id
     * @param lStrDemandCode [TODO Add parameter documentation here]
     * @param lStrMjrHdCOde [TODO Add parameter documentation here]
     * @param lStrHdType [TODO Add parameter documentation here]
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudSbMjrHdVO which returns List of all Sub Major Heads
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getSbMjrHds(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCOde, String lStrHdType,
        String lStrLangID, String lStrLocID, String lStrSts) throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtSbHd = null;
        ResultSet lRsSbHd = null;

        StringBuffer lSbSql = new StringBuffer();
        ArrayList lArrSbHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lSbSql.append(" SELECT S.BUDSUBMJRHD_ID, S.BUDSUBMJRHD_CODE, INITCAP(S.BUDSUBMJRHD_DESC_LONG) BUDSUBMJRHD_DESC_LONG");
            lSbSql.append(" FROM SGVA_BUDSUBMJRHD_MST S");
            lSbSql.append(" WHERE S.BPN_CODE = ?");
            lSbSql.append(" AND S.DEMAND_CODE = ?");
            lSbSql.append(" AND S.BUDMJRHD_CODE = ? ");
            lSbSql.append(" AND S.LOC_ID = ?");
            lSbSql.append(" AND S.LANG_ID = ?");
            lSbSql.append(" AND S.STATUS LIKE ? ORDER BY S.BUDSUBMJRHD_CODE");

            lPStmtSbHd = lCon.prepareStatement(lSbSql.toString());

            lPStmtSbHd.setString(1, lStrBPNCode.trim());
            lPStmtSbHd.setString(2, lStrDemandCode.trim());
            lPStmtSbHd.setString(3, lStrMjrHdCOde.trim());
            lPStmtSbHd.setString(4, lStrLocID.trim());
            lPStmtSbHd.setString(5, lStrLangID.trim());
            lPStmtSbHd.setString(6, lStrSts.trim());

            glogger.debug("SbMjrHd Query is : " + lSbSql.toString());
            glogger.debug("Params for getSbMjrHds are :  " + lStrBPNCode.trim() + "," + lStrDemandCode.trim() + "," +
                lStrMjrHdCOde.trim() + "," + lStrLocID.trim() + "," + lStrLangID.trim() + "," + lStrSts.trim());

            lRsSbHd = lPStmtSbHd.executeQuery();
            while(lRsSbHd.next())
            {
                BudSbMjrHdVO lObjBudSbMjrHdVO = new BudSbMjrHdVO();

                lObjBudSbMjrHdVO.setSbMjrHdId(lRsSbHd.getInt(1)); //For Sub Major Head ID
                lObjBudSbMjrHdVO.setSbMjrHdCode(lRsSbHd.getString(2)); //For Sub Major Head Code
                lObjBudSbMjrHdVO.setSbMjrHdLngDesc(lRsSbHd.getString(3)); //For Sub Major Head Long Description

                lArrSbHd.add(lObjBudSbMjrHdVO);
            }
               
            
            
            
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getSbMjrHds Method And Error is " + se, se);
            se.printStackTrace();
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getSbMjrHds Method And Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
        finally
        {
           closeResultSet(lRsSbHd);
           closeStatement(lPStmtSbHd);
           closeConnection(lCon);
        }

        return lArrSbHd;
    }

    /**
     * Method getSectSubSect returns List of all Sector and Sub sector from sgva_budmjrhd_mst
     *
     * @param lStrBPNCode Major Head Id
     * @param lStrDemandCode [TODO Add parameter documentation here]
     * @param lStrMjrHdCOde [TODO Add parameter documentation here]
     * @param lStrHdType [TODO Add parameter documentation here]
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in ComboValuesVO which returns List of all Sector Codes and their Description and Subsector Code and its
     *         Description
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getSectSubSect(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCOde, String lStrHdType,
        String lStrLangID, String lStrLocID, String lStrSts) throws SQLException, Exception
    {
        Connection lCon = null;
        PreparedStatement lPStmtSectSbsectHd = null;
        ResultSet lRsSecSubSecHd = null;

        StringBuffer lScSbScSql = new StringBuffer();
        ArrayList lArrSbHd = new ArrayList();

        try
        {
            if(lCon == null)
            {
                lCon =getConnection();
            }

            lScSbScSql.append(" SELECT MJR.SECTOR_CODE SECTOR_CODE,");
            lScSbScSql.append("(SELECT NVL(SEC.SECTOR_DESC_LONG,'---')  FROM SGVA_BUDSECTOR_MST SEC WHERE SEC.SECTOR_CODE IN ");
            lScSbScSql.append(" (SELECT MJR.SECTOR_CODE FROM SGVA_BUDMJRHD_MST MJR WHERE MJR.BUDMJRHD_CODE = ? ");
            lScSbScSql.append(" AND MJR.BPN_CODE = ? AND MJR.DEMAND_CODE = ? AND MJR.BUDMJRHDTYPE = 'E' AND MJR.LANG_ID=?) AND ");
            lScSbScSql.append(" SEC.LANG_ID = ?  AND SEC.SECTOR_TYPE = 'E' ) SECTOR_DESC ");
            lScSbScSql.append("FROM SGVA_BUDMJRHD_MST MJR ");
            lScSbScSql.append("WHERE MJR.BUDMJRHD_CODE = ? AND MJR.BPN_CODE = ? AND ");
            lScSbScSql.append("MJR.DEMAND_CODE = ? AND MJR.BUDMJRHDTYPE = 'E' AND ");
            lScSbScSql.append("MJR.LOC_ID = ? AND MJR.LANG_ID = ? ");

            lPStmtSectSbsectHd = lCon.prepareStatement(lScSbScSql.toString());

            lPStmtSectSbsectHd.setString(1, lStrMjrHdCOde.trim());
            lPStmtSectSbsectHd.setString(2, lStrBPNCode.trim());
            lPStmtSectSbsectHd.setString(3, lStrDemandCode.trim());
            lPStmtSectSbsectHd.setString(4, lStrLangID.trim());
            lPStmtSectSbsectHd.setString(5, lStrLangID.trim());
            lPStmtSectSbsectHd.setString(6, lStrMjrHdCOde.trim());
            lPStmtSectSbsectHd.setString(7, lStrBPNCode.trim());
            lPStmtSectSbsectHd.setString(8, lStrDemandCode.trim());
            lPStmtSectSbsectHd.setString(9, lStrLocID.trim());
            lPStmtSectSbsectHd.setString(10, lStrLangID.trim());

            glogger.debug("SbMjrHd Query is : " + lScSbScSql.toString());
            glogger.debug("Params for getSectorCode are :  " + lStrMjrHdCOde.trim() + "," + lStrBPNCode.trim() + "," +
                lStrDemandCode.trim() + "," + lStrLocID.trim() + "," + lStrLangID.trim());

            lRsSecSubSecHd = lPStmtSectSbsectHd.executeQuery();

            while(lRsSecSubSecHd.next())
            {
                ComboValuesVO lObjBudDtlVO = new ComboValuesVO(); //Instance of Value Object

                lObjBudDtlVO.setId(lRsSecSubSecHd.getString("SECTOR_CODE")); //For Detail head Code
                lObjBudDtlVO.setDesc(lRsSecSubSecHd.getString("SECTOR_DESC")); //For Detail head Description

                glogger.debug("Value set is : " + lObjBudDtlVO.getId() + "_" + lObjBudDtlVO.getDesc());

                lArrSbHd.add(lObjBudDtlVO); //Adding VO's Object to Array List
            }

           closeResultSet(lRsSecSubSecHd);
            lRsSecSubSecHd = null;
           closeStatement(lPStmtSectSbsectHd);
            lPStmtSectSbsectHd = null;

            lScSbScSql.delete(0, lScSbScSql.length());

            lScSbScSql.append("  SELECT MJR.SUBSECTOR_CODE SUBSECTOR_CODE,");
            lScSbScSql.append(" (SELECT NVL(SUBSEC.SUBSECTOR_DESC_LONG,'---') FROM SGVA_BUDSUBSECTOR_MST SUBSEC ");
            lScSbScSql.append(" WHERE SUBSEC.SUBSECTOR_CODE IN (SELECT MJR.SUBSECTOR_CODE FROM SGVA_BUDMJRHD_MST MJR ");
            lScSbScSql.append("WHERE MJR.BUDMJRHD_CODE = ? AND MJR.BPN_CODE = ? AND MJR.DEMAND_CODE = ? AND MJR.BUDMJRHDTYPE = 'E') ");
            lScSbScSql.append("  AND SUBSEC.SECTOR_CODE IN (SELECT MJR.SECTOR_CODE ");
            lScSbScSql.append(
                " FROM SGVA_BUDMJRHD_MST MJR WHERE MJR.BUDMJRHD_CODE = ? AND MJR.BPN_CODE = ? AND MJR.DEMAND_CODE = ? AND MJR.BUDMJRHDTYPE = 'E') ");
            lScSbScSql.append(" AND SUBSEC.SUBSECTOR_TYPE = 'E' AND SUBSEC.LANG_ID = ?) SUBSECTOR_DESC ");
            lScSbScSql.append("  FROM SGVA_BUDMJRHD_MST MJR ");
            lScSbScSql.append("  WHERE MJR.BUDMJRHD_CODE = ? AND MJR.BPN_CODE = ? AND ");
            lScSbScSql.append("  MJR.DEMAND_CODE = ? AND MJR.BUDMJRHDTYPE = 'E' AND ");
            lScSbScSql.append("  MJR.LOC_ID = ? AND MJR.LANG_ID = ? ");

            lPStmtSectSbsectHd = lCon.prepareStatement(lScSbScSql.toString());

            lPStmtSectSbsectHd.setString(1, lStrMjrHdCOde.trim());
            lPStmtSectSbsectHd.setString(2, lStrBPNCode.trim());
            lPStmtSectSbsectHd.setString(3, lStrDemandCode.trim());

            lPStmtSectSbsectHd.setString(4, lStrMjrHdCOde.trim());
            lPStmtSectSbsectHd.setString(5, lStrBPNCode.trim());
            lPStmtSectSbsectHd.setString(6, lStrDemandCode.trim());
            lPStmtSectSbsectHd.setString(7, lStrLangID.trim());

            lPStmtSectSbsectHd.setString(8, lStrMjrHdCOde.trim());
            lPStmtSectSbsectHd.setString(9, lStrBPNCode.trim());
            lPStmtSectSbsectHd.setString(10, lStrDemandCode.trim());
            lPStmtSectSbsectHd.setString(11, lStrLocID.trim());
            lPStmtSectSbsectHd.setString(12, lStrLangID.trim());

            lRsSecSubSecHd = lPStmtSectSbsectHd.executeQuery();

            while(lRsSecSubSecHd.next())
            {
                ComboValuesVO lObjBudDtlVO = new ComboValuesVO(); //Instance of Value Object

                lObjBudDtlVO.setId(lRsSecSubSecHd.getString("SUBSECTOR_CODE")); //For Detail head Code
                lObjBudDtlVO.setDesc(lRsSecSubSecHd.getString("SUBSECTOR_DESC")); //For Detail head Description
                glogger.debug("Value set is : " + lObjBudDtlVO.getId() + "_" + lObjBudDtlVO.getDesc());

                lArrSbHd.add(lObjBudDtlVO); //Adding VO's Object to Array List
            }

           closeResultSet(lRsSecSubSecHd);
            lRsSecSubSecHd = null;
           closeStatement(lPStmtSectSbsectHd);
            lPStmtSectSbsectHd = null;
        }
        catch(SQLException se)
        {
            glogger.error("Error in BudHdDAO Impl SQL in getSectSubSect Method And Error is " + se, se);
            se.printStackTrace();
            throw se;
        }
        catch(Exception e)
        {
            glogger.error("Error in BudHdDAO Impl IO in getSectSubSect Method And Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
        finally
        {
           closeResultSet(lRsSecSubSecHd);
           closeStatement(lPStmtSectSbsectHd);
           closeConnection(lCon);
        }

        return lArrSbHd;
    }

    //End of getSbMjrHds Metod

    /**
     * Method getUnitID() for particular Department of Budget
     *
     * @param lStrDeptID SubjectID
     *
     * @return ArrayList contains UnitID in which unit_type=BUD and Level=10
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getUnitID(String lStrDeptID) throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtUnit = null;
        ResultSet lrsUnit = null;
        StringBuffer lsbUnit = new StringBuffer();
        String lStrUnitID = null;
        glogger.debug("Entering into getUnitID():");

        ArrayList lArrReturn = new ArrayList(); // Added By Keyur

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            lsbUnit.append("SELECT UNIT_ID FROM SGVO_UNIT_MST");
            lsbUnit.append(" WHERE LEVEL_ID=? AND DEPT_ID=? AND UNIT_TYPE=?");
            lsbUnit.append(" AND LANG_ID=? AND LOC_ID=?");

            lPstmtUnit = lcon.prepareStatement(lsbUnit.toString());

            glogger.debug("Query For getUnitID is :  " + lsbUnit.toString());
            glogger.debug("Params For getUnitID are : " + liUntLvl4Brnch + "," + lStrDeptID.trim() + "," + lStrBranchType.trim() +
                "," + lStrConstLangID.trim() + "," + lStrConstLocID.trim());

            glogger.debug("liUntLvl4Brnch:" + liUntLvl4Brnch); //liUntLvl4Brnch=10
            glogger.debug("lStrBranchType:" + lStrBranchType);
            glogger.debug("lStrConstLangID:" + lStrConstLangID);
            glogger.debug("lStrConstLocID:" + lStrConstLocID);
            glogger.debug("Dept:" + lStrDeptID);

            lPstmtUnit.setInt(1, liUntLvl4Brnch);
            lPstmtUnit.setString(2, lStrDeptID.trim());
            lPstmtUnit.setString(3, lStrBranchType.trim());
            lPstmtUnit.setString(4, lStrConstLangID.trim());
            lPstmtUnit.setString(5, lStrConstLocID.trim());

            lrsUnit = lPstmtUnit.executeQuery();

            while(lrsUnit.next())
            {
                if(lrsUnit.getString("UNIT_ID") != null)
                {
                    lStrUnitID = lrsUnit.getString("UNIT_ID"); // 
                    lArrReturn.add(lStrUnitID);
                }

                glogger.debug(lStrUnitID);
            }
        }

        //try
        catch(SQLException e)
        {
            glogger.error("SQL Exception occured in  getUnitID() of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in  getUnitID() of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsUnit);
           closeStatement(lPstmtUnit);
           closeConnection(lcon);
        }

        //finally
        return lArrReturn;
    }

    /**
     * Method getUnitID() for particular Department of Budget
     *
     * @param lStrDeptID SubjectID
     * @param lStrUnitID [TODO Add parameter documentation here]
     *
     * @return ArrayList contains UnitID in which unit_type=BUD and Level=10
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getUnitID(String lStrDeptID, String[] lStrUnitID) throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmt = null;
        ResultSet lRs = null;
        StringBuffer lSbQuery = new StringBuffer();
        String lStrRetUnitID = null;
        glogger.debug("Entering into getUnitID():");

        ArrayList lArrReturn = new ArrayList(); // Added By Keyur
        int[] lIntLvl = new int[lStrUnitID.length];

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            if(lStrUnitID.length == 0)
            {
                glogger.error("Empty Array of UnitID in getUnitID()");

                return null;
            }

            lSbQuery.append("SELECT LEVEL_ID FROM SGVO_UNIT_MST WHERE ");
            lSbQuery.append(" LANG_ID=? AND LOC_ID=? AND ");
            lSbQuery.append(" UNIT_ID IN ( ");

            for(int i = 0; i < lStrUnitID.length; i++)
            {
                if(i == (lStrUnitID.length - 1))
                {
                    lSbQuery.append(" ?");
                }
                else
                {
                    lSbQuery.append(" ?,");
                }
            }

            lSbQuery.append(" )");
            lPstmt = lcon.prepareStatement(lSbQuery.toString());

            lPstmt.setString(1, lStrConstLangID);
            lPstmt.setString(2, lStrConstLocID);

            int lIntLvlCnt = 2;

            for(int j = 0; j < lStrUnitID.length; j++)
            {
                lPstmt.setString(++lIntLvlCnt, lStrUnitID[j].trim());
            }

            glogger.debug(" getUnitID Query is 1 : " + lSbQuery.toString());
            lRs = lPstmt.executeQuery();

            int j = 0;

            while(lRs.next())
            {
                if(lRs.getInt("LEVEL_ID") != 0)
                {
                    lIntLvl[j++] = lRs.getInt("LEVEL_ID");
                }
            }

           closeResultSet(lRs);
           closeStatement(lPstmt);

            lRs = null;
            lPstmt = null;

            lSbQuery.delete(0, lSbQuery.length());

            int lIntQuryCnt = 0;

            for(int lIntCnt = 0; lIntCnt < lStrUnitID.length; lIntCnt++)
            {
                lSbQuery.delete(0, lSbQuery.length());
                lIntQuryCnt = 0;

                lSbQuery.append("SELECT UNIQUE UNIT_ID FROM (");
                lSbQuery.append(" SELECT U.UNIT_ID, U.LEVEL_ID, U.DEPT_ID, U.UNIT_TYPE, U.LANG_ID, U.LOC_ID ");
                lSbQuery.append(" FROM SGVO_UNIT_MST U START WITH U.UNIT_ID = ? CONNECT BY PRIOR ");

                if(lIntLvl[lIntCnt] > 10)
                {
                    lSbQuery.append(" U.UNIT_ID = U.PARENT_UNIT_ID)");
                }
                else
                {
                    lSbQuery.append(" U.PARENT_UNIT_ID = U.UNIT_ID)");
                }

                lSbQuery.append(" WHERE LEVEL_ID = ? AND DEPT_ID=? AND UNIT_TYPE=?");
                lSbQuery.append(" AND LANG_ID=? AND LOC_ID=? ");

                lPstmt = lcon.prepareStatement(lSbQuery.toString());

                lPstmt.setString(++lIntQuryCnt, lStrUnitID[lIntCnt]);
                lPstmt.setInt(++lIntQuryCnt, liUntLvl4Brnch);
                lPstmt.setString(++lIntQuryCnt, lStrDeptID.trim());
                lPstmt.setString(++lIntQuryCnt, lStrBranchType.trim());
                lPstmt.setString(++lIntQuryCnt, lStrConstLangID.trim());
                lPstmt.setString(++lIntQuryCnt, lStrConstLocID.trim());

                lRs = lPstmt.executeQuery();

                while(lRs.next())
                {
                    if(lRs.getString("UNIT_ID") != null)
                    {
                        lStrRetUnitID = lRs.getString("UNIT_ID"); // 

                        if(!lArrReturn.contains(lStrRetUnitID))
                        {
                            lArrReturn.add(lStrRetUnitID);
                        }
                    }

                    glogger.debug(lStrRetUnitID);
                }

               closeResultSet(lRs);
               closeStatement(lPstmt);

                lRs = null;
                lPstmt = null;
            }
        }

        //try
        catch(SQLException e)
        {
            glogger.error("SQL Exception occured in  getUnitID() of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in  getUnitID() of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lRs);
           closeStatement(lPstmt);
           closeConnection(lcon);
        }

        //finally
        return lArrReturn;
    }

    /**
     * It returns Unit Ids for Intimation for FD.
     *
     * @param lStrFDDeptID FD Dept ID
     * @param lStrLowerLvlDeptID Lower Lvl Dept ID
     * @param lStrBPNCode [TODO Add parameter documentation here]
     *
     * @return ArrayList
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getUnitIDForEBUD(String lStrFDDeptID, String lStrLowerLvlDeptID, String lStrBPNCode)
        throws SQLException, Exception
    {
        ArrayList lArrFDUnits = new ArrayList();
        Connection lcon = null;
        PreparedStatement lPstmtUnit = null;
        ResultSet lrsUnit = null;
        StringBuffer lsbUnit = new StringBuffer();
        StringBuffer lSb = new StringBuffer();
        glogger.debug("Entering into getUnitID():");

        try
        {
            lArrFDUnits = getEBUDUnitID(lStrFDDeptID);

            if(lcon == null)
            {
                lcon =getConnection();
            }

            lsbUnit = new StringBuffer();
            lsbUnit.append("SELECT UNIT_ID FROM SGVA_EBUD_MPG WHERE BPN_CODE= ?");
            lPstmtUnit = lcon.prepareStatement(lsbUnit.toString());
            glogger.debug("Query For getUnitID query is :  " + lsbUnit.toString());
            lPstmtUnit.setString(1, lStrBPNCode.trim());

            lrsUnit = lPstmtUnit.executeQuery();

            while(lrsUnit.next())
            {
                if(lrsUnit.getString("UNIT_ID") != null)
                {
                    if(!lArrFDUnits.contains(lrsUnit.getString("UNIT_ID")))
                    {
                        lArrFDUnits.add(lrsUnit.getString("UNIT_ID")); //   
                    }
                }

                glogger.debug(lArrFDUnits);
            }
        }

        //try
        catch(SQLException e)
        {
            glogger.error("SQL Exception occured in  getUnitIDForEBUD() of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in  getUnitIDForEBUD() of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsUnit);
           closeStatement(lPstmtUnit);
           closeConnection(lcon);
        }

        //finally
        return lArrFDUnits;
    }

    //end of method
    //********************** FOR VALIDATE SUB HEAD ID,SUB MAJOR HEAD,SUB MINOR HEAD*******

    /**
     * Method validateHds() to validate the MajorHead,subHead,MinorHead combination
     *
     * @param lStrMjrHdCode Major Head ID
     * @param lStrSbMjrHdCode Sub Major Head Code
     * @param lStrMinHdCode Minor Head Code
     * @param lStrSbHdCode sub Head Code
     * @param lStrMjrHdType Major Head Type ( R-Reciept or E-Expenditure)
     * @param lStrStatus Status
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return boolean (return true if combination is valid else return false)
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public boolean validateHds(String lStrMjrHdCode, String lStrSbMjrHdCode, String lStrMinHdCode, String lStrSbHdCode,
        String lStrMjrHdType, String lStrStatus, String lStrLangID, String lStrLocID)
        throws SQLException, Exception
    {
        Connection lcon = null;
        PreparedStatement lPstmtHds = null;
        ResultSet lrsHds = null;
        StringBuffer lsbHds = new StringBuffer();
        ArrayList sbHdList = new ArrayList();

        boolean lbStatus = true;

        try
        {
            if(lcon == null)
            {
                lcon =getConnection();
            }

            lsbHds.append("SELECT SB.BUDSUBHD_ID FROM SGVA_BUDSUBHD_MST SB");
            lsbHds.append(" WHERE SB.BUDMJRHD_CODE= ? AND");
            lsbHds.append(" SB.BUDSUBMJRHD_CODE= ? AND");
            lsbHds.append(" SB.BUDMINHD_CODE= ? AND");
            lsbHds.append(" SB.BUDSUBHD_CODE= ? AND");
            lsbHds.append(" SB.STATUS=? AND SB.DEMAND_CODE=? AND SB.BPN_CODE= ?");
            lsbHds.append(" AND SB.LANG_ID=? AND SB.LOC_ID=?");

            //glogger.debug(lsb.toString());
            lPstmtHds = lcon.prepareStatement(lsbHds.toString());

            glogger.debug("Query For validateHds is : " + lsbHds.toString());
            glogger.debug("Params For validateHds are : " + lStrMjrHdCode.trim() + "," + lStrSbMjrHdCode.trim() + "," +
                lStrMinHdCode.trim() + "," + lStrSbHdCode.trim() + "," + lStrStatus.trim() + "," + lStrDemandCode.trim() + "," +
                lStrBPN.trim() + "," + lStrLangID.trim() + "," + lStrLocID.trim());

            lPstmtHds.setString(1, lStrMjrHdCode.trim());
            lPstmtHds.setString(2, lStrSbMjrHdCode.trim());
            lPstmtHds.setString(3, lStrMinHdCode.trim());
            lPstmtHds.setString(4, lStrSbHdCode.trim());
            lPstmtHds.setString(5, lStrStatus.trim());
            lPstmtHds.setString(6, lStrDemandCode.trim());
            lPstmtHds.setString(7, lStrBPN.trim());
            lPstmtHds.setString(8, lStrLangID.trim());
            lPstmtHds.setString(9, lStrLocID.trim());

            lrsHds = lPstmtHds.executeQuery();

            if(lrsHds.next())
            {
                if(lrsHds.getInt("BUDSUBHD_ID") != 0)
                {
                    sbHdList.add(lrsHds.getInt("BUDSUBHD_ID") + "");
                    glogger.debug("" + lrsHds.getInt("BUDSUBHD_ID"));
                }

                lbStatus = true;
            }
            else
            {
                lbStatus = false;
            }
        }
        catch(SQLException e)
        {
            glogger.error("Error occured in SQL:validateHds Method of BudHdDAOImpl" + e);
            throw e;
        }
        catch(Exception e)
        {
            glogger.error("Error occured in validateHds Method of BudHdDAOImpl" + e);
            throw e;
        }
        finally
        {
           closeResultSet(lrsHds);
           closeStatement(lPstmtHds);
           closeConnection(lcon);
        }

        glogger.debug("" + lbStatus);

        return lbStatus;
    }

public ArrayList getAllDemand(String langId,String locId) throws Exception
{
    ArrayList retval = null;
    Connection lcon = null;
    PreparedStatement lPStmtDemand = null;
    ResultSet lRsDemand = null;

    StringBuffer lSbSql = new StringBuffer();
  try
  {
  
      lcon =getConnection();
      retval = new ArrayList();
      
       lSbSql.append("SELECT DEMMST.DEMAND_ID,");
       lSbSql.append("DEMMST.DEMAND_CODE,");
       lSbSql.append("DEMMST.DEMAND_DESC,");
       lSbSql.append("DEMMST.LANG_ID,");
       lSbSql.append("DEMMST.LOC_ID,");
       lSbSql.append("DEMMST.CRT_DT, DEMMST.CRT_USR,");
       lSbSql.append("DEMMST.LST_UPD_DT,");
       lSbSql.append("DEMMST.LST_UPD_USR,");
       lSbSql.append("DEMMST.STATUS,");
       lSbSql.append("DEMMST.TERMINATED_YR_ID,");
       lSbSql.append("DEMMST.DEMAND_TYPE,");
       lSbSql.append("DEMMST.BPNCODE,");
       lSbSql.append("DEMMST.INCHARGE ");

       lSbSql.append("FROM SGVA_BUDDEMAND_MST DEMMST ");

      lSbSql.append("WHERE DEMMST.LANG_ID = ? AND DEMMST.LOC_ID = ? AND DEMMST.STATUS='Active' ORDER BY DEMMST.DEMAND_CODE");

      lPStmtDemand = lcon.prepareStatement(lSbSql.toString());
      
      lPStmtDemand.setString(1,langId);
      lPStmtDemand.setString(2,locId);
      
      lRsDemand = lPStmtDemand.executeQuery();
      
      glogger.debug("Query to Fetch All Demand : " + lSbSql.toString());
      //System.out.println("Query : " + lSbSql.toString());      
      while(lRsDemand.next())
      {
                BudExpEstDmdVO lObjBudExpEstDmdVO = new BudExpEstDmdVO(); //Instance of Value Object

                lObjBudExpEstDmdVO.setDemandID(lRsDemand.getInt("DEMAND_ID")); //For Expenditure Demand ID
                lObjBudExpEstDmdVO.setDemandCode(lRsDemand.getString("DEMAND_CODE")); //For Demand Code
                lObjBudExpEstDmdVO.setDemandDesc(lRsDemand.getString("DEMAND_DESC")); //For Demand Description
                lObjBudExpEstDmdVO.setBPNCode(lRsDemand.getString("BPNCODE")); //For Demand Description
                retval.add(lObjBudExpEstDmdVO); //Adding VO's Object to Array List
      }
      
  } 
  catch(Exception _e)
  {
    glogger.error("Error in getAllDemands"+_e);
  }
  finally
  {
           closeResultSet(lRsDemand);
           closeStatement(lPStmtDemand);
           closeConnection(lcon);
  }

  
  return retval;

 }
 
	/**
	 * This method close a jdbc connection
	 * @param con
	 */
	private Connection getConnection() {
		return this.conn;
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

	
	public ArrayList getAllMjrHdsForDept(String lStrDeptId, String lStrLangID, String lStrLocID)
	{
		Connection lCon = null;
	    PreparedStatement lPStmtMjrHd = null;
	    ResultSet lRsMjrHd = null;
	
	    ArrayList lArrMjrHd = new ArrayList();
	
	    try
	    {
	        if(lCon == null)
	        {
	            lCon = getConnection();
	        }
	
	        String lStrQuery =  " SELECT T.BUDMJRHD_CODE, T.BUDMJRHD_DESC_LONG FROM SGVA_BUDMJRHD_MST T " + 
	        					" WHERE T.BPN_CODE IN (SELECT B.BPNCODE FROM SGVA_BUDBPN_MAPPING B WHERE B.DEPT_ID = ? AND B.LANG_ID = ?) AND " +
	        					" T.LOC_ID = ? AND T.LANG_ID = ? AND T.STATUS LIKE 'Active' ORDER BY T.BUDMJRHD_CODE ";
	
	        lPStmtMjrHd = lCon.prepareStatement( lStrQuery );
	        lPStmtMjrHd.setString(1, lStrDeptId.trim());
	        lPStmtMjrHd.setString(2, lStrLangID.trim());
	        lPStmtMjrHd.setString(3, lStrLocID.trim());
	        lPStmtMjrHd.setString(4, lStrLangID.trim());
	
	        glogger.debug("getAllMjrHds Query is : " + lStrQuery);
	        glogger.debug("Params for getAllMjrHds are :  " + lStrDeptId + "," + lStrLangID + "," + lStrLocID + "," + lStrLangID);
	        lRsMjrHd = lPStmtMjrHd.executeQuery();
	        
	        while(lRsMjrHd.next())
	        {
	            ComboValuesVO lObjComboValue = new ComboValuesVO();
	
	            lObjComboValue.setId(lRsMjrHd.getString(1));
	            String lStrDesc = lRsMjrHd.getString(2);
	            lObjComboValue.setDesc(lStrDesc.equals(null) ?  "------------------" :  IFMSUtil.getInitCapString(lStrDesc));
	                        
	            lArrMjrHd.add(lObjComboValue);
	        }        
	    }
	    catch(SQLException se)
	    {
	        glogger.error("Error in BudHdDAO Impl SQL in getAllMjrHdsForDept Method And Error is " + se, se);
	    }
	    catch(Exception e)
	    {
	        glogger.error("Error in BudHdDAO Impl IO in getAllMjrHdsForDept Method And Error is " + e, e);
	    }
	    finally
	    {
	        closeResultSet(lRsMjrHd);
	        closeStatement(lPStmtMjrHd);
	        closeConnection(lCon);
	    }
	
	    return lArrMjrHd;
	}

	/**
	 * It returns all Schemes (Sub Heads) for given dept.
	 *
	 * @param lStrDeptId Dept Id
	 * @param lStrLangID Lang Id
	 * @param lStrLocID Loc Id
	 *
	 * @return
	 */
	public ArrayList getAllSchemesForDept(String lStrDeptId, String lStrLangID, String lStrLocID)
	{
	
	    Connection lCon = null;
	    PreparedStatement lPStmt = null;
	    ResultSet lRs = null;
	
	    StringBuffer lSbSql = new StringBuffer();
	    ArrayList lArrSchemeData = new ArrayList();
	
	    try
	    {
	        if(lCon == null)
	        {
	            lCon = getConnection();
	        }
	
	        lSbSql.append(" SELECT CONCAT(S.DEMAND_CODE,':',S.BUDMJRHD_CODE,':',S.BUDSUBMJRHD_CODE,':',S.BUDMINHD_CODE,':',S.BUDSUBHD_CODE) SCHEME_CODE, " +
	        				" S.BUDSUBHD_DESC_LONG SBHDDESC FROM SGVA_BUDSUBHD_MST S WHERE S.BPN_CODE IN " +
	        				" (SELECT B.BPNCODE FROM SGVA_BUDBPN_MAPPING B WHERE B.DEPT_ID = ? AND B.LANG_ID = ?) AND " +
	        				" S.LOC_ID = ? AND S.LANG_ID = ? AND S.STATUS LIKE 'Active' " +
	        				" ORDER BY S.DEMAND_CODE, S.BUDMJRHD_CODE, S.BUDSUBMJRHD_CODE, S.BUDMINHD_CODE, S.BUDSUBHD_CODE ");
	
	        lPStmt = lCon.prepareStatement(lSbSql.toString());
	        lPStmt.setString(1, lStrDeptId.trim());
	        lPStmt.setString(2, lStrLangID.trim());
	        lPStmt.setString(3, lStrLocID.trim());
	        lPStmt.setString(4, lStrLangID.trim());
	        
	        glogger.debug("getAllSchemesForDept Query is : " + lSbSql.toString());
	        glogger.debug("Params for getAllSchemesForDept are :  " + lStrDeptId + "," + lStrLangID + "," + lStrLocID + "," + lStrLangID);
	        lRs = lPStmt.executeQuery();
	
	        while(lRs.next())
	        {
	            ComboValuesVO lObjCombo = new ComboValuesVO();
	
	            lObjCombo.setId(lRs.getString("SCHEME_CODE"));
	            String lStrDesc = lRs.getString("SBHDDESC");
	            lObjCombo.setDesc(lStrDesc.equals(null) ?  "------------------" : IFMSUtil.getInitCapString(lStrDesc));
	            
	            lArrSchemeData.add(lObjCombo);
	        }
	    }
	    catch(SQLException se)
	    {
	        glogger.error("Error in BudHdDAO Impl SQL in getAllSchemesForDept Method And Error is " + se, se);
	    }
	    catch(Exception e)
	    {
	        glogger.error("Error in BudHdDAO Impl IO in getAllSchemesForDept Method And Error is " + e, e);
	    }
	    finally
	    {
	        closeResultSet(lRs);
	        closeStatement(lPStmt);
	        closeConnection(lCon);
	    }
	
	    return lArrSchemeData;
	}

	 public ArrayList getSchemeCode(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCode, String lStrSbMjrHdCode,
		        String lStrMnrHdCode,String lStrSubHd, String lStrLangID, String lStrLocID, String lStrSts)
		        throws SQLException, Exception
		    {
		        Connection lCon = null;
		        PreparedStatement lPStmtSbHd = null;
		        ResultSet lRsSbHd = null;

		        StringBuffer lSbSql = new StringBuffer();
		        ArrayList lArrSbHd = new ArrayList();

		        try
		        {
		            if(lCon == null)
		            {
		                lCon =getConnection();
		            }

		            lSbSql.append(
		                " SELECT T.BUDSUBHD_ID, T.BUDSUBHD_CODE, INITCAP(T.BUDSUBHD_DESC_LONG) BUDSUBHD_DESC_LONG,");
		            lSbSql.append(" NVL(T.EDPCODE,' ') EDPCODE, T.PLNGCODE PLANCODE FROM sgva_budsubhd_mst T");
		            lSbSql.append(" WHERE T.BPN_CODE = ?");
		            lSbSql.append(" AND T.DEMAND_CODE = ?");
		            lSbSql.append(" AND T.BUDMJRHD_CODE = ? ");
		            lSbSql.append(" AND T.BUDSUBMJRHD_CODE = ? ");
		            lSbSql.append(" AND T.BUDMINHD_CODE = ? ");
		            lSbSql.append(" AND BUDSUBHD_CODE = ? ");
		            lSbSql.append(" AND T.LOC_ID = ?");
		            lSbSql.append(" AND T.LANG_ID = ?");
		            lSbSql.append(" AND T.STATUS LIKE ?  ORDER BY T.BUDSUBHD_CODE");

		            lPStmtSbHd = lCon.prepareStatement(lSbSql.toString());

		            lPStmtSbHd.setString(1, lStrBPNCode.trim());
		            lPStmtSbHd.setString(2, lStrDemandCode.trim());
		            lPStmtSbHd.setString(3, lStrMjrHdCode.trim());
		            lPStmtSbHd.setString(4, lStrSbMjrHdCode.trim());
		            lPStmtSbHd.setString(5, lStrMnrHdCode.trim());
		            lPStmtSbHd.setString(6, lStrSubHd.trim());
		            lPStmtSbHd.setString(7, lStrLocID.trim());
		            lPStmtSbHd.setString(8, lStrLangID.trim());
		            lPStmtSbHd.setString(9, lStrSts.trim());

		            glogger.debug("Sub Head Query is : " + lSbSql.toString());
		            glogger.debug("Params for getSbHds are :  " + lStrBPNCode.trim() + "," + lStrDemandCode.trim() + "," +
		                lStrMjrHdCode.trim() + "," + lStrSbMjrHdCode.trim() + "," + lStrMnrHdCode.trim() + "," + lStrLocID.trim() + "," +
		                lStrLangID.trim() + "," + lStrSts.trim());
		            lRsSbHd = lPStmtSbHd.executeQuery();

		            while(lRsSbHd.next())
		            {
		                BudExpEstSbHdVO lObjBudExpEstSbHdVO = new BudExpEstSbHdVO(); //Instance of Value Object

		                lObjBudExpEstSbHdVO.setSbHdID(lRsSbHd.getInt(1)); //For Expenditure Sub  Head ID
		                lObjBudExpEstSbHdVO.setSbHdCode(lRsSbHd.getString(2)); //For Sub Head Code
		                lObjBudExpEstSbHdVO.setSbHdLngDesc(lRsSbHd.getString(3)); //For Sub Head Long Description
		                lObjBudExpEstSbHdVO.setEDPCode(lRsSbHd.getString("EDPCODE")); // Added by Keyur for EDP Code
		                if(lRsSbHd.getString("PLANCODE")==null)		    
		                	lObjBudExpEstSbHdVO.setPlanningCode("");
		                else
		                	lObjBudExpEstSbHdVO.setPlanningCode(lRsSbHd.getString("PLANCODE")); // Added by Keyur for Planning Code

		                glogger.debug("Value set is : " + lObjBudExpEstSbHdVO.getEDPCode() + "_" + lObjBudExpEstSbHdVO.getPlanningCode());

		                //lObjBudExpEstSbHdVO.setSbHdShrtDesc(lRsSbHd.getString(4));//For Sub Head Short Description
		                lArrSbHd.add(lObjBudExpEstSbHdVO); //Adding VO's Object to Array List
		            }
		        }

		        //End of Try
		        catch(SQLException se)
		        {
		            glogger.error("Error in BudHdDAO Impl SQL in getSbHds Method And Error is " + se, se);
		            throw se;
		        }
		        catch(Exception e)
		        {
		            glogger.error("Error in BudHdDAO Impl IO in getSbHds Method And Error is " + e, e);
		            throw e;
		        }
		        finally
		        {
		           closeResultSet(lRsSbHd);
		           closeStatement(lPStmtSbHd);
		           closeConnection(lCon);
		        }

		        return lArrSbHd;
		    }


}//end of class
