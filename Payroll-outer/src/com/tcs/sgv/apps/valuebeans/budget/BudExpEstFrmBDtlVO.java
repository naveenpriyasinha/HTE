/*
 * PACKAGE         : com.tcs.sgv.apps.valuebeans.budget
 * filename        : BudExpEstFrmBDtlVO.java
 * @Author         : Keyur Shah
 * @version        : 1.0
 * REV. HISTORY :
 *-----------------------------------------------------------------------------
 *-----------------------------------------------------------------------------
 */
package com.tcs.sgv.apps.valuebeans.budget;

import com.tcs.sgv.common.valuebeans.BaseValueBean;


/**
 * This class Conatins all the Get and Set methods
 *
 * @author : Keyur Shah
 * @version 1.0
 */
public class BudExpEstFrmBDtlVO extends BaseValueBean implements Comparable
{
    /** Integer representing  Expenditure Estimation Form B Header ID */
    private int lIntExpEstFrmBHdrID = 0;

    /** Integer representing  Expenditure Estimation Form B Detail ID */
    private int lIntExpEstFrmBDtlID = 0;

    /** Integer representing  Expenditure Estimation Form User Amount ID */
    private int lIntExpEstUsrAmtID = 0;

    /** String representing  Object head Code */
    private String lStrObjHdCode = null;

    /** String representing  Object head Description */
    private String lStrObjHdDesc = null;

    /** String representing  Combination of Head Identifier */
    private String lStrCmbHdIdfr = null;

    /** Long representing  Last Year Actual Amount */
    private long llngLstYrAmt = -1;

    /** Long representing  Level Proposed Amount */
    private long llngLvlPrdAmt = -1;

    /** Long representing  Actual of last 8 months of the previous year */
    private long llngACTUL_LST_8MONTHAmt = -1;

    /** Long representing  Actual of first 4 months of the current year */
    private long llngACTUL_LST_4MONTHAmt = -1;

    /** Integer representing  Expenditure Estimation Grant IN Aid Parent ID */
    private int lIntGIA_PARENT_ID = 0;

    /** String representing  Expenditure Estimation Grant IN Aid Flag */
    private String lStrGIA_FLAG = null;

    /** String representing Object Head Type Added For recovery flag */
    private String lStrObjHdType = null;

    /** Long representing  Finance Level Amount For Previous Year (Budget Estimates) */
    private long llngFinLvlAmt = -1;

    /** Long representing  Finance Level Approved Amount For Current Year */
    private long llngFDApprvdAmt = -1;

    /** String representing  Remarks */
    private String lStrRemark = null;

    /** String representing  Application ID */
    private String lStrApplnID = null;

    /** String representing  Created userID */
    private String lStrCrtUsrID = null;

    /** Integer representing Terminated YearID */
    private int lIntTrYrID = 0;

    /** String representing  Language ID */
    private String lStrLangId = null;

    /** String representing  Location ID */
    private String lStrLocId = null;

    /** Long representing 2ndPrevious Year Actual Expenditure Amount */
    private long llngSecPrevActExpAmt = -1;

    /** Long representing 3rd Previous Year Actual Expenditure Amount */
    private long llngThrdPrevActExpAmt = -1;

    /** String representing Head Of the Department Name */
    private String lStrHodName;

    /** Long representing Formula Amount [Budget Guidelines Amount] */
    private long llngFormulaAmt = -1;

    /** Long representing  User Proposed Amount */
    private long llngUsrPrpAmt = -1;

    /** Long representing  HOD Proposed Amount */
    private long llngHodPrpAmt = -1;

    /** Long representing  Department Proposed Amount */
    private long llngDeptPrpAmt = -1;

    /** [TODO Add field documentation here] */
    private String lStrRecoveryFlag = null;

    /** GIA Child Count */
    private String lStrParentObjHead = null;

    /**
     * It sets Actual of last 4 months of the previous year
     *
     * @param llngACTUL_LST_4MONTHAmt Actual Amount of last 4 months of the previous year
     */
    public void setACTUL_LST_4MONTHAmt(long llngACTUL_LST_4MONTHAmt)
    {
        this.llngACTUL_LST_4MONTHAmt = llngACTUL_LST_4MONTHAmt;
    }

    /**
     * It returns Actual of last 4 months of the previous year
     *
     * @return Actual Amount of last 4 months of the previous year
     */
    public long getACTUL_LST_4MONTHAmt()
    {
        return llngACTUL_LST_4MONTHAmt;
    }

    /**
     * It sets Actual of last 8 months of the previous year
     *
     * @param llngACTUL_LST_8MONTHAmt Actual Amount of last 8 months of the previous year
     */
    public void setACTUL_LST_8MONTHAmt(long llngACTUL_LST_8MONTHAmt)
    {
        this.llngACTUL_LST_8MONTHAmt = llngACTUL_LST_8MONTHAmt;
    }

    /**
     * It returns Actual of last 8 months of the previous year
     *
     * @return Actual Amount of last 8 months of the previous year
     */
    public long getACTUL_LST_8MONTHAmt()
    {
        return llngACTUL_LST_8MONTHAmt;
    }

    /**
     * It sets Application ID
     *
     * @param lStrApplnID Application ID
     */
    public void setApplnID(String lStrApplnID)
    {
        this.lStrApplnID = lStrApplnID;
    }

    /**
     * It returns Application ID
     *
     * @return Application ID
     */
    public String getApplnID()
    {
        return lStrApplnID;
    }

    /**
     * It sets Combination of Head Identifier
     *
     * @param lStrCmbHdIdfr Combination of Head Identifier
     */
    public void setCmbHdIdfr(String lStrCmbHdIdfr)
    {
        this.lStrCmbHdIdfr = lStrCmbHdIdfr;
    }

    /**
     * It returns Combination of Head Identifier
     *
     * @return Combination of Head Identifier
     */
    public String getCmbHdIdfr()
    {
        return lStrCmbHdIdfr;
    }

    /**
     * It sets Create User Id
     *
     * @param lStrCrtUsrID Create User Id
     */
    public void setCrtUsrID(String lStrCrtUsrID)
    {
        this.lStrCrtUsrID = lStrCrtUsrID;
    }

    /**
     * It returns Create User ID
     *
     * @return Create User Id
     */
    public String getCrtUsrID()
    {
        return lStrCrtUsrID;
    }

    /**
     * It sets Department Proposed Amount
     *
     * @param llngDeptPrpAmt Department Proposed Amount method
     */
    public void setDeptPrpAmt(long llngDeptPrpAmt)
    {
        this.llngDeptPrpAmt = llngDeptPrpAmt;
    }

    /**
     * It returns Department Proposed Amount
     *
     * @return Department Proposed Amount method
     */
    public long getDeptPrpAmt()
    {
        return llngDeptPrpAmt;
    }

    /**
     * It sets Expenditure Form B Detail ID
     *
     * @param lIntExpEstFrmBDtlID Expenditure Estimation Form B Detail ID
     */
    public void setExpEstFrmBDtlID(int lIntExpEstFrmBDtlID)
    {
        this.lIntExpEstFrmBDtlID = lIntExpEstFrmBDtlID;
    }

    /**
     * It returns Expenditure Estimation Form B Detail ID
     *
     * @return Expenditure Form B Detail ID
     */
    public int getExpEstFrmBDtlID()
    {
        return lIntExpEstFrmBDtlID;
    }

    //*****************************   Get And Set Method Start Here    *******************************

    /**
     * It sets Expenditure Estimation Form B Header ID
     *
     * @param lIntExpEstFrmBHdrID Expenditure Estimation Form B Header ID
     */
    public void setExpEstFrmBHdrID(int lIntExpEstFrmBHdrID)
    {
        this.lIntExpEstFrmBHdrID = lIntExpEstFrmBHdrID;
    }

    /**
     * It returns Expenditure Estimation Form B Header ID
     *
     * @return Expenditure Estimation Form B Header ID
     */
    public int getExpEstFrmBHdrID()
    {
        return lIntExpEstFrmBHdrID;
    }

    /**
     * It sets Expenditure Estimation Form User Amount ID Value
     *
     * @param lIntExpEstUsrAmtID Expenditure Estimation Form User Amount ID
     */
    public void setExpEstUsrAmtID(int lIntExpEstUsrAmtID)
    {
        this.lIntExpEstUsrAmtID = lIntExpEstUsrAmtID;
    }

    /**
     * It returns Expenditure Estimation Form User Amount ID Value
     *
     * @return Expenditure Estimation Form User Amount ID
     */
    public int getExpEstUsrAmtID()
    {
        return lIntExpEstUsrAmtID;
    }

    /**
     * It sets Finanace level Approved Amt
     *
     * @param llngFDApprvdAmt Finanace level Approved Amt
     */
    public void setFDApprvdAmt(long llngFDApprvdAmt)
    {
        this.llngFDApprvdAmt = llngFDApprvdAmt;
    }

    /**
     * It returns Finanace level Approved Amt
     *
     * @return Finanace level Approved Amt
     */
    public long getFDApprvdAmt()
    {
        return llngFDApprvdAmt;
    }

    /**
     * It sets Finanace level Approved For Previous Year - (Budget Estimates) Amount
     *
     * @param llngFinLvl_Amt Finanace level Amount
     */
    public void setFinLvlAmt(long llngFinLvl_Amt)
    {
        this.llngFinLvlAmt = llngFinLvl_Amt;
    }

    /**
     * It returns Finanace level Approved For Previous Year - (Budget Estimates) Amount
     *
     * @return Finanace level Amount
     */
    public long getFinLvlAmt()
    {
        return llngFinLvlAmt;
    }

    /**
     * It sets Formula Amount
     *
     * @param llngFormulaAmt Formula Amount
     */
    public void setFormulaAmt(long llngFormulaAmt)
    {
        this.llngFormulaAmt = llngFormulaAmt;
    }

    /**
     * It returns Formula Amount
     *
     * @return Formula Amount
     */
    public long getFormulaAmt()
    {
        return llngFormulaAmt;
    }

    /**
     * It sets GIA Flag
     *
     * @param lStrGIA_FLAG GIA Flag
     */
    public void setGIA_FLAG(String lStrGIA_FLAG)
    {
        this.lStrGIA_FLAG = lStrGIA_FLAG;
    }

    /**
     * It returns GIA Flag
     *
     * @return GIA Flag
     */
    public String getGIA_FLAG()
    {
        return lStrGIA_FLAG;
    }

    /**
     * It sets Expenditure Form B GIA Parent ID
     *
     * @param lIntGIA_PARENT_ID GIA Parent ID
     */
    public void setGIA_PARENT_ID(int lIntGIA_PARENT_ID)
    {
        this.lIntGIA_PARENT_ID = lIntGIA_PARENT_ID;
    }

    /**
     * It returns Expenditure Form B GIA Parent ID
     *
     * @return GIA Parent ID
     */
    public int getGIA_PARENT_ID()
    {
        return lIntGIA_PARENT_ID;
    }

    /**
     * It sets HOD Name
     *
     * @param newLStrHodName HOD Name
     */
    public void setHodName(String newLStrHodName)
    {
        lStrHodName = newLStrHodName;
    }

    /**
     * It returns HOD Name
     *
     * @return HOD Name
     */
    public String getHodName()
    {
        return lStrHodName;
    }

    /**
     * It sets HOD Proposed Amount
     *
     * @param llngHodPrpAmt HOD Proposed Amount
     */
    public void setHodPrpAmt(long llngHodPrpAmt)
    {
        this.llngHodPrpAmt = llngHodPrpAmt;
    }

    /**
     * It returns HOD Proposed Amount
     *
     * @return HOD Proposed Amount
     */
    public long getHodPrpAmt()
    {
        return llngHodPrpAmt;
    }

    /**
     * It sets Language ID
     *
     * @param lStrLangId Language ID
     */
    public void setLangId(String lStrLangId)
    {
        this.lStrLangId = lStrLangId;
    }

    /**
     * It returns Language ID
     *
     * @return Language ID
     */
    public String getLangId()
    {
        return lStrLangId;
    }

    /**
     * It sets Location Id
     *
     * @param lLocId Location ID
     */
    public void setLocId(String lLocId)
    {
        this.lStrLocId = lLocId;
    }

    /**
     * It returns Location Id
     *
     * @return Location ID
     */
    public String getLocId()
    {
        return lStrLocId;
    }

    /**
     * It sets Last Year Actual Amount
     *
     * @param llngLstYrAmt Last Year Actual Amount
     */
    public void setLstYrAmt(long llngLstYrAmt)
    {
        this.llngLstYrAmt = llngLstYrAmt;
    }

    /**
     * It returns Last Year Actual Amount
     *
     * @return Last Year Actual Amount
     */
    public long getLstYrAmt()
    {
        return llngLstYrAmt;
    }

    /**
     * It sets Level Proposed Amount
     *
     * @param llngLvlPrdAmt Level Proposed Amount
     */
    public void setLvlPrdAmt(long llngLvlPrdAmt)
    {
        this.llngLvlPrdAmt = llngLvlPrdAmt;
    }

    /**
     * It returns Level Proposed Amount
     *
     * @return Level Proposed Amount
     */
    public long getLvlPrdAmt()
    {
        return llngLvlPrdAmt;
    }

    /**
     * It sets Expenditure Estimation Object Head Code
     *
     * @param lStrObjHdCode Object Head Code
     */
    public void setObjHdCode(String lStrObjHdCode)
    {
        this.lStrObjHdCode = lStrObjHdCode;
    }

    /**
     * It returns Expenditure Estimation Object Head Code
     *
     * @return Object Head Code
     */
    public String getObjHdCode()
    {
        return lStrObjHdCode;
    }

    /**
     * It sets Expenditure Estimation Object Head Description
     *
     * @param lStrObjHdDesc Object Head Description
     */
    public void setObjHdDesc(String lStrObjHdDesc)
    {
        this.lStrObjHdDesc = lStrObjHdDesc;
    }

    /**
     * It returns Expenditure Estimation Object Head Description
     *
     * @return Object Head Description
     */
    public String getObjHdDesc()
    {
        return lStrObjHdDesc;
    }

    /**
     * It sets Object Head Type
     *
     * @param lStrObjHdType Object Head Type
     */
    public void setObjHdType(String lStrObjHdType)
    {
        this.lStrObjHdType = lStrObjHdType;
    }

    /**
     * It returns Object Head Type
     *
     * @return Object Head Type
     */
    public String getObjHdType()
    {
        return lStrObjHdType;
    }

    /**
     * It sets Parent GIA Object Head
     *
     * @param lStrParentObjHead Parent GIA Object Head
     */
    public void setParentGIAObjHd(String lStrParentObjHead)
    {
        this.lStrParentObjHead = lStrParentObjHead;
    }

    /**
     * It returns Parent GIA Object Head
     *
     * @return String
     */
    public String getParentGIAObjHd()
    {
        return lStrParentObjHead;
    }

    /**
     * It sets the Recovery Flag
     *
     * @param lStrRecoveryFlag Recovery Flag
     */
    public void setRecoveryFlag(String lStrRecoveryFlag)
    {
        this.lStrRecoveryFlag = lStrRecoveryFlag;
    }

    /**
     * It returns Recovery Flag
     *
     * @return Recovery Flag
     */
    public String getRecoveryFlag()
    {
        return lStrRecoveryFlag;
    }

    /**
     * It sets Remarks
     *
     * @param lStrRemark Remarks
     */
    public void setRemark(String lStrRemark)
    {
        this.lStrRemark = lStrRemark;
    }

    /**
     * It returns Remarks
     *
     * @return Remarks
     */
    public String getRemark()
    {
        return lStrRemark;
    }

    /**
     * It sets 2nd Previous Year Actual Expenditure Amount
     *
     * @param llngSecPrevActExpAmt 2nd Previous Year Actual Expenditure Amount
     */
    public void setSecPrevActExpAmt(long llngSecPrevActExpAmt)
    {
        this.llngSecPrevActExpAmt = llngSecPrevActExpAmt;
    }

    /**
     * It returns 2nd Previous Year Actual Expenditure Amount
     *
     * @return 2nd Previous Year Actual Expenditure Amount
     */
    public long getSecPrevActExpAmt()
    {
        return llngSecPrevActExpAmt;
    }

    /**
     * It sets 3rd Previous Year Actual Expenditure Amount
     *
     * @param llngThrdPrevActExpAmt 3rd Previous Year Actual Expenditure Amount
     */
    public void setThrdPrevActExpAmt(long llngThrdPrevActExpAmt)
    {
        this.llngThrdPrevActExpAmt = llngThrdPrevActExpAmt;
    }

    /**
     * It returns 3rd Previous Year Actual Expenditure Amount
     *
     * @return 3rd Previous Year Actual Expenditure Amount
     */
    public long getThrdPrevActExpAmt()
    {
        return llngThrdPrevActExpAmt;
    }

    /**
     * It sets User Proposed Amount
     *
     * @param llngUsrPrpAmt User Proposed Amount
     */
    public void setUsrPrpAmt(long llngUsrPrpAmt)
    {
        this.llngUsrPrpAmt = llngUsrPrpAmt;
    }

    /**
     * It returns User Proposed Amount
     *
     * @return User Proposed Amount
     */
    public long getUsrPrpAmt()
    {
        return llngUsrPrpAmt;
    }

    /**
     * Compares Two Objects
     *
     * @param objDtls Object Details
     *
     * @return Comparison Result
     */
    public int compareTo(Object objDtls)
    {
        BudExpEstFrmBDtlVO objDtlsVO = (BudExpEstFrmBDtlVO) objDtls;
        int lIntRetVal = 0;

        if(((this.lStrObjHdCode).compareTo(objDtlsVO.lStrObjHdCode)) != 0)
        {
            lIntRetVal = ((this.lStrObjHdCode).compareTo(objDtlsVO.lStrObjHdCode));
        }
        else if(((this.lStrRecoveryFlag).compareTo(objDtlsVO.lStrRecoveryFlag)) != 0)
        {
            lIntRetVal = ((this.lStrRecoveryFlag).compareTo(objDtlsVO.lStrRecoveryFlag));
        }
        else
        {
            lIntRetVal = 0;
        }

        return lIntRetVal;
    }

    //*****************************   Get And Set Method Ends Here    *******************************

    /**
     * It returns output of all the methods of this interface on the console. param VO Name
     */

    /*
       public void discribeVO(MyVO mv)
        {
              Method[] mtd =  mv.getClass().getDeclaredMethods();
              String StrName=null;
              ////System.out.println(mtd.length);
              try
              {
                for(int i=0; i< mtd.length; i++)
                {
                  StrName=mtd[i].getName();
                  if(StrName.subString(0,3).equals("get"))
                  {
                    //System.out.println(mtd[i].getName() + " : " + mtd[i].invoke(mv,null));
                  }
                }
              }catch(Exception e)
              {
                 e.printStackTrace();
              }
              finally
              {
                mtd = null;
                StrName = null;
              }
        }
     */
}


//Interface Ends Here
