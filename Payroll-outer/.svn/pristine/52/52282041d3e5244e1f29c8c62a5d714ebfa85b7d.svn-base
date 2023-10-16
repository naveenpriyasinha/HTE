/*
 * PACKAGE         : com.tcs.sgv.apps.dao.budget
 * filename        : BudHdDAO. java
 * @verion         : 1.0
 * @author         : Desai Chirag
 * date            : 01/12/2005
 * description     : This is the Implementation class of BudHdDAO
 *-----------------------------------------------------------------------------
 */
package com.tcs.sgv.apps.dao.budget;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.sgv.apps.valuebeans.budget.BudExpEstDeptVO;


/**
 * DOCUMENT ME!
 *
 * @version 1.0
 */
public interface BudHdDAO
{
    /**
     * This Method returns BPN Code based on Demand passed.
     *
     * @param lStrDmdCode Demand Code
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     *
     * @return ArrayList Containing BPN Information
     *
     * @throws SQLException SQL Exception
     * @throws Exception Exception
     */
    public ArrayList getAllBPNByDemand(String lStrDmdCode, String lStrLangId, String lStrLocId)
        throws SQLException, Exception;

    /**
     * Method getAllBPNCode returns all Budget Publication Code from SGVA_BUDBPN_MAPPING
     *
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     * @param lStrDeptID Department ID
     *
     * @return ArrayList in BudGADVO which contains GAD Code,GAD Name
     */
    public ArrayList getAllBPNCode(String lStrDeptID, String lStrLangId, String lStrLocId)
        throws SQLException, Exception;

    /**
     * method getAllBranch Depends on DepartmentID
     *
     * @param lStrUnitID[] unit ID
     * @param lStrDeptID DepartmentID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudBranchVO of All BranchID Aand BranchName
     */
    public ArrayList getAllBranchName(String[] lStrUnitID, String lStrDeptID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception;

    /**
     * method getAllBrnchByDept Depends on DepartmentID
     *
     * @param lStrDeptID DepartmentID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudBranchVO of All BranchID Aand BranchName
     */
    public ArrayList getAllBrnchByDept(String lStrDeptID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception;

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
    public ArrayList getAllBudBranch(String lStrDeptID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception; // Added By Keyur

    /**
     * Method getAllDemand returns List of all Demands from
     *
     * @param lStrDeptID Department Id
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudDmdVO which returns List of all Demand Code depends on Department Id
     */
    public ArrayList getAllDemand(String lStrDeptID, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception;

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
        throws SQLException, Exception;

    /**
     * Method getAllGADCd returns all GAD Code from SGVA_BUDGAD_MST
     *
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudGADVO which contains GAD Code,GAD Name
     */
    public ArrayList getAllGADCd(String lStrLangId, String lStrLocId, String lStrSts)
        throws SQLException, Exception;

    /**
     * Method getAllGrpMnrHds returns all Group Minro Heads from SGVA_BUDGRPMNR_MST
     *
     * @param LStrLangId Language ID
     * @param LStrLocId Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudGADVO which contains GAD Code,GAD Name
     */
    public ArrayList getAllGrpMnrHds(String lStrLangId, String lStrLocId, String lStrSts)
        throws SQLException, Exception;

    //************************     FOR HOD CODE for Expenditure Estimation*****************************

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
        throws SQLException, Exception;

    //************************ Hod list For Receipt Estimation*******************************

    /**
     * Method getAllHods returns all HodID and Description from SGVA_BUDHOD_MST for Receipt Estimation
     *
     * @param lStrStatus Status (Active)
     * @param lArrUnitID UnitID[]
     * @param lStrDeptID DepartmentId
     * @param lStrLangId Language ID
     * @param lStrLocId Location ID
     *
     * @return ArrayList which contains HodIDs and Hod Description
     */
    public ArrayList getAllHods(String lStrStatus, String[] lArrUnitID, String lStrDeptID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception;

    //***************************************** Getting Major Head for only Receipt Estimation ******************************************** 

    /**
     * method getAllMjrHds() return the MajorHead list based on Department which is used only for Receipt Estimation
     *
     * @param lStrdeptID (Optional for FD) Department
     * @param lStrMjrHdType Major Head Type ( R-Reciept or E-Expenditure)
     * @param lStrStatus Status of the Major Head (In this case Status="Y")
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList of All Major Head Code and description
     */
    public ArrayList getAllMjrHd(String lStrDeptID, String lStrMjrHdType, String lStrStatus, String lStrLangID, String lStrLocID)
        throws SQLException, Exception;

    //***************************************** Getting Major Head for Expenditure Estimation ******************************************** 

    /**
     * Method getAllMjrHds returns List of all Sub Major Head from sgva_budsubmjrhd_mst
     *
     * @param lStrMjrHdID Major Head Id
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudSbMjrHdVO which returns List of all Sub Major Heads
     */
    public ArrayList getAllMjrHds(String lStrDeptId, String lStrDemandCode, String lStrMhrHdType, String lStrLangID, String lStrLocID,
        String lStrSts) throws SQLException, Exception;

    // Added by Ravishankar-End
    //Added By Ronak on 29/04/2006

    /**
     * Method getAllObjectHd Code returns List of all Object Codes from SGVA_BUDOBJHD_MST
     *
     * @param lStrStatus Status
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return [TODO Add return documentation here]
     */
    public ArrayList getAllObjectHd(String lStrStatus, String lStrLangId, String lStrLocId)
        throws SQLException, Exception;

    /**
     * Method getAllOfficeCd returns all Office Code from SGVA_BUDOFFICE_MST
     *
     * @param LStrLangId Language ID
     * @param LStrLocId Location ID
     *
     * @return ArrayList in BudOfficeCdVO which contains Office Code,Office Name
     */
    public ArrayList getAllOfficeCd(String lStrLangId, String lStrLocId, String lStrSts)
        throws SQLException, Exception;

    // Added by Ravishankar-Begin

    /**
     * Method getAllShare Code returns List of all Share Codes from SGVA_BUDSHARE_MST
     *
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return [TODO Add return documentation here]
     */
    public ArrayList getAllShare(String lStrLangID, String lStrLocID) throws SQLException, Exception;

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
        throws SQLException, Exception;

    //Added By payal on 9/10/1006

    /**
     * This method getDeptList returns Department List and HodId,Hod Name based on Dept Id of logged User
     *
     * @param ArrayList DepartmentIds OF Logged User
     * @param lStrLangId Lang Id
     * @param lStrLocId LOC ID
     *
     * @return ArrayList
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getDeptForHod(ArrayList lArrDept, String lStrLangId, String lStrLocId)
        throws SQLException, Exception;

    /**
     * It returns Department related Information.
     *
     * @param lStrDeptID Dept ID
     * @param lStrLangID Lang ID
     *
     * @return BudExpEstDeptVO
     */
    public BudExpEstDeptVO getDeptInfo(String lStrDeptID, String lStrLangID);

    /**
     * Method getDmdByBPN returns List of all Demands from
     *
     * @param lStrBPNCode BPN Code
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudDmdVO which returns List of all Demand Code depends on Department Id
     */
    public ArrayList getDmdByBPN(String lStrBPNCode, String lStrExpType, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception;

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
     * @param lStrSts Status
     *
     * @return ArrayList in BudSbHdVO which returns List of all Sub Head Code,ID,Desc depends
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getDtlHds(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCode, String lStrSbMjrHdCode,
        String lStrMnrHdCode, String lStrSubHdCode, String lStrLangID, String lStrLocID)
        throws SQLException, Exception; // Added by Abhijit

    //Added by Ravi for getting EBUD Unit Id for FD level.

    /**
     * DOCUMENT ME!
     *
     * @param lStrDeptID Deaprtment ID
     *
     * @return [TODO Add return documentation here]
     */
    public ArrayList getEBUDUnitID(String lStrDeptID) throws SQLException, Exception;

    /**
     * Method getEmployeeTypeCode returns the Employee Type "FA" or "MIN" or ""
     *
     * @param String[] lStrUnitID
     *
     * @return [TODO Add return documentation here]
     */
    public String getEmployeeType(String[] lStrUnitID);

    //Added By Ronak on 04/12/1006

    /**
     * Method getLSLvlTenUnitID()
     *
     * @param strDeptID of string DeptID
     * @param strSubID of string SubID
     *
     * @return String (return string of unit id)
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public String getLSLvlTenUnitID(String strDeptID, String strSubID) throws SQLException, Exception;

    //Added By Ronak on 12/10/1006

    /**
     * Method getLSUnitID()
     *
     * @param strUnitID array of string UnitID
     * @param strSubID array of string SubID
     *
     * @return String (return string of unit id)
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public String getLSUnitID(String[] strUnitID, String strSubID) throws SQLException, Exception;

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
        throws SQLException, Exception;

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
        throws SQLException, Exception;

    /**
     * method getMjrHdByDmd
     *
     * @param lStrMjrHdType Major Head Type ( R-Reciept or E-Expenditure)
     * @param liDmdID Demand ID ( ID-Expenditure and -1 -Receipt)
     * @param lStrStatus Status of the Major Head (In this case Status="Y")
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList of All Major Head Code and description
     */
    public ArrayList getMjrHdByDmd(String lStrDemandCode, String lStrBPNCode, String lStrMhrHdType, String lStrLangID,
        String lStrLocID, String lStrSts) throws SQLException, Exception;

    /**
     * Ronak N. Shah method getMjrHdByDmd
     *
     * @param lStrMjrHdType Major Head Type ( R-Reciept or E-Expenditure)
     * @param lStrMjrHdType Major Head Sub Type ( R-Revenue or C-Capital)
     * @param liDmdID Demand ID ( ID-Expenditure and -1 -Receipt)
     * @param lStrStatus Status of the Major Head (In this case Status="Y")
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList of All Major Head Code and description
     */
    public ArrayList getMjrHdByDmd(String lStrDemandCode, String lStrBPNCode, String lStrMhrHdType, String lStrMhrHdSubType,
        String lStrLangID, String lStrLocID, String lStrSts) throws SQLException, Exception;

    /**
     * Method getMnrHds returns List of all Minor Head from SGVA_BUDMINHD_MST
     *
     * @param lIntDemandID Demand Code
     * @param lStrBPNCode BPN Code
     * @param lIntMjrHdID Major Head Code
     * @param lIntSbMjrHdID Sub Major Head Code
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudExpEstMnrHdVO which returns List of all Minor Head Code,ID,Desc depends on Major Head ID, Sub Major
     *         Head ID
     */
    public ArrayList getMnrHds(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCode, String lStrSbMjrHdCode,
        String lStrHdType, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception;

    /**
     * It returns ModEntry for Scheme Report based on Emp Id
     *
     * @param lStrEmpID Emp ID
     *
     * @return Mode Entry
     */
    public String getModEntryForSchemeReport(String lStrEmpID);

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
     */
    public ArrayList getSbHds(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCode, String lStrSbMjrHdCode,
        String lStrMnrHdCode, String lStrLangID, String lStrLocID, String lStrSts)
        throws SQLException, Exception;

    //CHIRAG ADDED THIS METHOD ON 03-dec-2005 FROM HERE

    /**
     * Method getSbMjrHds returns List of all Sub Major Head from sgva_budsubmjrhd_mst
     *
     * @param lStrMjrHdID Major Head Id
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     * @param lStrSts Status
     *
     * @return ArrayList in BudSbMjrHdVO which returns List of all Sub Major Heads
     */
    public ArrayList getSbMjrHds(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCOde, String lStrHdType,
        String lStrLangID, String lStrLocID, String lStrSts) throws SQLException, Exception;

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
        String lStrLangID, String lStrLocID, String lStrSts) throws SQLException, Exception;

    /**
     * Method getUnitID() for particular Department of Budget branch
     *
     * @param lStrSubID SubjectID
     * @param lStrUnitID UnitID
     *
     * @return String contains UnitID in which unit_type=BUD and Level=10
     */
    public ArrayList getUnitID(String lStrDeptID, String[] lStrUnitID) throws SQLException, Exception;

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
    public ArrayList getUnitID(String lStrDeptID) throws SQLException, Exception;

    // Added by Ronak Shah on 10-Oct-06

    /**
     * It returns Unit Ids for Intimation for FD.
     *
     * @param lStrFDDeptID FD Dept ID
     * @param lStrLowerLvlDeptID Lower Lvl Dept ID
     *
     * @return ArrayList
     *
     * @throws SQLException [TODO Add exception documentation here]
     * @throws Exception [TODO Add exception documentation here]
     */
    public ArrayList getUnitIDForEBUD(String lStrFDDeptID, String lStrLowerLvlDeptID, String lStrBPNCode)
        throws SQLException, Exception;

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
     */
    public boolean validateHds(String lStrMjrHdCode, String lStrSbMjrHdCode, String lStrMinHdCode, String lStrSbHdCode,
        String lStrMjrHdType, String lStrStatus, String lStrLangID, String lStrLocID)
        throws SQLException, Exception;

    //************************     FOR GETTING BRANCH NAME*****************************

    /**
     * method getAllBranch
     *
     * @param lStrUnitID UnitID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudBranchVO of All BranchID Aand BranchName
     */
    ArrayList getAllBranch(String[] lStrUnitID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception;

    /**
     * method getAllDepartment Depends depends on Array of UnitID
     *
     * @param lArrUnitID UnitID
     * @param lStrLangID Language ID
     * @param lStrLocID Location ID
     *
     * @return ArrayList in BudDeptVO which contains DepartmentID and DepartMent Name
     */
    ArrayList getAllDept(String[] lArrUnitID, String lStrLangID, String lStrLocID)
        throws SQLException, Exception;
        
public ArrayList getAllDemand(String langId,String locId) throws Exception;

public ArrayList getAllMjrHdsForDept(String lStrDeptId, String lStrLangID, String lStrLocID);
    
    public ArrayList getAllSchemesForDept(String lStrDeptId, String lStrLangID, String lStrLocID);

    public ArrayList getSchemeCode(String lStrBPNCode, String lStrDemandCode, String lStrMjrHdCode, String lStrSbMjrHdCode,
	        String lStrMnrHdCode,String lStrSubHd, String lStrLangID, String lStrLocID, String lStrSts) throws Exception;
	        
        
}
