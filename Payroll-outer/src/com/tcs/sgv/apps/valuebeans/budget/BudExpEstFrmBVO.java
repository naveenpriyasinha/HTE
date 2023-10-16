/*
 * PACKAGE         : com.tcs.sgv.apps.valuebeans.budget
 * filename        : BudExpEstFrmBVO.java
 * AUTHOR          : Keyur Shah
 * @version        : 1.0
 * REV. HISTORY :
 *-----------------------------------------------------------------------------
 *-----------------------------------------------------------------------------
 */
package com.tcs.sgv.apps.valuebeans.budget;

import java.lang.reflect.Method;

import com.tcs.sgv.common.valuebeans.BaseValueBean;


/**
 * This class Conatins all the Get and Set methods
 *
 * @version 1.0
 */
public class BudExpEstFrmBVO extends BaseValueBean implements Cloneable
{
    /** Integer representing  Expenditure Estimation Form B Header ID */
    private int lIntExpEstFrmBHdrID = 0;

    /** Integer representing  Financial Year Id */
    private int lIntFinYrID = 0;

    /** String representing Minister In charge */
    private String lStrMinIncharge = null;

    /** String representing  BPN Code */
    private String lStrBPNCode = null;

    /** String representing  Demand Code */
    private String lStrDemandCode = null;

    /** String representing  Major head Code */
    private String lStrMjrHdCode = null;

    /** String representing  Sub Major head Code */
    private String lStrSbMjrHdCode = null;

    /** String representing  Minor Head Code */
    private String lStrMnrHdCode = null;

    /** String representing  Group Head Code */
    private String lStrGrpMnrHdCode = null;

    /** String representing  Sub Head Code */
    private String lStrSbHdCode = null;

    /** String representing  Detail Head Code */
    private String lStrDtlHdCode = null;

    /** String representing  Office Head Code */
    private String lStrOfficeHdCode = null;

    /** String representing  GAD Head Code */
    private String lStrGadHdCode = null;

    /** String representing Charge Type Charged/Voted */
    private String lStrChargeType = null;

    /** String representing  Plan/NonPlan */
    private String lStrPlanType = null;

    /** Integer representing  HOD ID */
    private int lIntHodID = 0;

    /** String representing  File created Status */
    private String lStrFileCrtd = null;

    /** String representing  File Added Status */
    private String lStrAddedFile = null;

    /** Int representing  Level ID */
    private int lIntLvlID = 0;

    /** String representing  Branch ID */
    private String lStrBrID = null;

    /** String representing  File Node ID */
    private String lStrFileNodeID = null;

    /** String representing  Unit ID */
    private String lStrUnitID = null;

    /** String representing Dept ID */
    private String lStrDeptID = null;

    /** String representing Budget Branch Flag */
    private String lStrBudBranchFlag = null;

    /** String representing  Correction Flag */
    private String lStrCorrectionFlag = null;

    /** Integer representing  Correction Parent ID */
    private int lIntCorrParentId = 0;

    /** Integer representing  Version ID */
    private int lIntVersionId = 0;

    /** Integer representing  Parent ID */
    private int lIntParentID = 0;

    /** String representing  Status of the File */
    private String lStrFileSts = null;

    /** Byte representing Hod Remarks */
    private byte[] lBytRemarks;

    /** String representing  Created userID */
    private String lStrCrtUsrID = null;

    /** String representing Application ID */
    private String lStrApplnID = null;

    /** String representing  Language ID */
    private String lStrLangId = null;

    /** String representing  Location ID */
    private String lStrLocId = null;

    // Fields added by Keyur. Addeed fields are GIA Flag, GIA Parent ID, File Extension and File Name

    /** Integer representing  Expenditure Estimation Grant IN Aid Parent ID */
    private int lIntGIA_PARENT_ID = 0;

    /** String representing  Expenditure Estimation Grant IN Aid Flag */
    private String lStrGIA_FLAG = null;

    /** String representing  Remark File Extension */
    private String lStrFileExtnsn = null;

    /** String representing  Remark File Name */
    private String lStrFileName = null;

    /** String representing  Financial Year Desc */
    private String lStrFinYrDesc = null;

    /** String representing  HOD Desc */
    private String lStrHodName = null;

    /** String representing  Branch Name */
    private String lStrBrName = null;

    /** String representing  Department Name */
    private String lStrDeptName = null;

    /** String representing File Node Name */
    private String lStrFileNodeName = null;

    /** String representing Sector Code */
    private String lStrSectorCd = null;

    /** String representing Sub Sector Code */
    private String lStrSubSectorCd = null;

    /** String representing Sector Description */
    private String lStrSectorDesc = null;

    /** String representing Sub Sector Description */
    private String lStrSubSectorDesc = null;

    /** String representing  BPN Description */
    private String lStrBPNDesc = null;

    /** String representing  Demand Code Description */
    private String lStrDmdDesc = null;

    /** String representing  Major Head Long Description */
    private String lStrMjrHdLngDesc = null;

    /** String representing  Sub Major head Long Description */
    private String lStrSbMjrLngDesc = null;

    /** String representing  Major Head Long Description */
    private String lStrMnrHdLngDesc = null;

    /** String representing  Sub Head Long Description */
    private String lStrSubHdLngDesc = null;

    /** String representing  Group Minor Head Description */
    private String lStrGrpMnrHdLngDesc = null;

    /** String representing  Office Code Description */
    private String lStrOfficeCdDesc = null;

    /** String representing  GAD Code Description */
    private String lStrGADCdDesc = null;

    /** String representing  Detailed Head Code Description */
    private String lStrDtlHdCodeDesc = null;

    /** Array representing  Form B Detail Reference */
    private BudExpEstFrmBDtlVO[] lObjBudExpEstFrmBDtlVO = null;

    /** Object representing  Form B Attachment Reference */
    private BudExpEstFrmBAttachVO[] lObjBudExpEstFrmBAttachVO = null;

    /** Object representing  Form B Post Reference */
    private BudExpEstFrmBPostVO lObjBudExpEstFrmBPostVO = null;

    /** Byte representing FD Remarks */
    private byte[] lBytFDRemarks;

    /** String representing  FD Remark File Extension */
    private String lStrFDFileExtnsn = null;

    /** String representing  FD Remark File Name */
    private String lStrFDFileName = null;

    /**
     * It sets File Added Flag
     *
     * @param lStrAddedFile File Added
     */
    public void setAddedFile(String lStrAddedFile)
    {
        this.lStrAddedFile = lStrAddedFile;
    }

    /**
     * It returns File Added Flag
     *
     * @return File Added
     */
    public String getAddedFile()
    {
        return lStrAddedFile;
    }

    /**
     * It sets Application Id
     *
     * @param lStrApplnID
     */
    public void setApplnID(String lStrApplnID)
    {
        this.lStrApplnID = lStrApplnID;
    }

    /**
     * It returns Application Id
     *
     * @return Application Id
     */
    public String getApplnID()
    {
        return lStrApplnID;
    }

    /**
     * It sets Budget Publication Number Code
     *
     * @param lStrBPNCode
     */
    public void setBPNCode(String lStrBPNCode)
    {
        this.lStrBPNCode = lStrBPNCode;
    }

    /**
     * It returns Budget Publication Number Code
     *
     * @return Budget Publication Number Code
     */
    public String getBPNCode()
    {
        return lStrBPNCode;
    }

    /**
     * It sets Budget Publication Number Desc
     *
     * @param lStrBPNDesc
     */
    public void setBPNDesc(String lStrBPNDesc)
    {
        this.lStrBPNDesc = lStrBPNDesc;
    }

    /**
     * It returns Budget Publication Number Desc
     *
     * @return Budget Publication Number Desc
     */
    public String getBPNDesc()
    {
        return lStrBPNDesc;
    }

    /**
     * It sets Branch ID
     *
     * @param lStrBrID Branch ID
     */
    public void setBrID(String lStrBrID)
    {
        this.lStrBrID = lStrBrID;
    }

    /**
     * It returns Branch ID
     *
     * @return Branch ID
     */
    public String getBrID()
    {
        return lStrBrID;
    }

    /**
     * It sets Branch Name
     *
     * @param lStrBrName Branch Name
     */
    public void setBrName(String lStrBrName)
    {
        this.lStrBrName = lStrBrName;
    }

    /**
     * It returns Branch Name
     *
     * @return Branch Name
     */
    public String getBrName()
    {
        return lStrBrName;
    }

    /**
     * It sets Budget Branch Flag
     *
     * @param lStrBudBranchFlag Budget Branch Flag
     */
    public void setBudBranchFlag(String lStrBudBranchFlag)
    {
        this.lStrBudBranchFlag = lStrBudBranchFlag;
    }

    /**
     * It returns Budget Branch Flag
     *
     * @return Budget Branch Flag
     */
    public String getBudBranchFlag()
    {
        return lStrBudBranchFlag;
    }

    /**
     * It returns Form B Attachment
     *
     * @return Form B Attachment
     */
    public BudExpEstFrmBAttachVO[] getBudExpEstFrmBAttachVO()
    {
        return lObjBudExpEstFrmBAttachVO;
    }

    /**
     * It sets Form B Detail Reference
     *
     * @param newlObjBudExpEstFrmBDtlVO Form B Detail Reference
     */
    public void setBudExpEstFrmBDtl(BudExpEstFrmBDtlVO[] newlObjBudExpEstFrmBDtlVO)
    {
        this.lObjBudExpEstFrmBDtlVO = newlObjBudExpEstFrmBDtlVO;
    }

    /* end : for adding dtl vo */

    /**
     * It returns Form B Detail Reference
     *
     * @return Form B Detail Reference
     */
    public BudExpEstFrmBDtlVO[] getBudExpEstFrmBDtl()
    {
        return lObjBudExpEstFrmBDtlVO;
    }

    /**
     * It sets Form B Post Reference
     *
     * @param lObjBudExpEstFrmBPostVO Form B Post Reference
     */
    public void setBudExpEstFrmBPostVO(BudExpEstFrmBPostVO lObjBudExpEstFrmBPostVO)
    {
        this.lObjBudExpEstFrmBPostVO = lObjBudExpEstFrmBPostVO;
    }

    /**
     * It returns Form B Post Reference
     *
     * @return Form B Post Reference
     */
    public BudExpEstFrmBPostVO getBudExpEstFrmBPostVO()
    {
        return lObjBudExpEstFrmBPostVO;
    }

    /**
     * It sets Form B Attachment
     *
     * @param lObjBudExpEstFrmBAttachVO Form B Attachment
     */
    public void setBudExpFrmBAttachVO(BudExpEstFrmBAttachVO[] lObjBudExpEstFrmBAttachVO)
    {
        this.lObjBudExpEstFrmBAttachVO = lObjBudExpEstFrmBAttachVO;
    }

    /**
     * It sets Charge Type
     *
     * @param lStrChargeType Charge Type
     */
    public void setChargeType(String lStrChargeType)
    {
        this.lStrChargeType = lStrChargeType;
    }

    /**
     * It returns Charge Type
     *
     * @return Charge Type
     */
    public String getChargeType()
    {
        return lStrChargeType;
    }

    /**
     * It sets Correction Parent ID
     *
     * @param lIntCorrParentId Correction Parent ID
     */
    public void setCorrParentId(int lIntCorrParentId)
    {
        this.lIntCorrParentId = lIntCorrParentId;
    }

    /**
     * It returns Correction Parent ID
     *
     * @return Correction Parent ID
     */
    public int getCorrParentId()
    {
        return lIntCorrParentId;
    }

    /**
     * It sets Correction Flag
     *
     * @param lStrCorrectionFlag Correction Flag
     */
    public void setCorrectionFlag(String lStrCorrectionFlag)
    {
        this.lStrCorrectionFlag = lStrCorrectionFlag;
    }

    /**
     * It returns Correction Flag
     *
     * @return Correction Flag
     */
    public String getCorrectionFlag()
    {
        return lStrCorrectionFlag;
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
     * @return Create User ID
     */
    public String getCrtUsrID()
    {
        return lStrCrtUsrID;
    }

    /**
     * It sets Demand Code
     *
     * @param lStrDemandCode Demand Code
     */
    public void setDemandCode(String lStrDemandCode)
    {
        this.lStrDemandCode = lStrDemandCode;
    }

    /**
     * It returns Demand Code
     *
     * @return Demand Code
     */
    public String getDemandCode()
    {
        return lStrDemandCode;
    }

    /**
     * It sets Demand Desc
     *
     * @param lStrDmdDesc
     */
    public void setDemandDesc(String lStrDmdDesc)
    {
        this.lStrDmdDesc = lStrDmdDesc;
    }

    /**
     * It returns Demand Desc
     *
     * @return Demand Desc
     */
    public String getDemandDesc()
    {
        return lStrDmdDesc;
    }

    /**
     * It returns Department Id
     *
     * @return Department Id
     */
    public String getDeptID()
    {
        return lStrDeptID;
    }

    /**
     * It sets Department ID
     *
     * @param lStrDeptID Department ID
     */
    public void setDeptId(String lStrDeptID)
    {
        this.lStrDeptID = lStrDeptID;
    }

    /**
     * It sets Department Name
     *
     * @param lStrDeptName Department Name
     */
    public void setDeptName(String lStrDeptName)
    {
        this.lStrDeptName = lStrDeptName;
    }

    /**
     * It returns Department Name
     *
     * @return Department Name
     */
    public String getDeptName()
    {
        return lStrDeptName;
    }

    /**
     * It sets Detail Head Code
     *
     * @param lStrDtlHdCode Detail Head Code
     */
    public void setDtlHdCode(String lStrDtlHdCode)
    {
        this.lStrDtlHdCode = lStrDtlHdCode;
    }

    /**
     * It returns Detail Head Code
     *
     * @return Detail Head Code
     */
    public String getDtlHdCode()
    {
        return lStrDtlHdCode;
    }

    //

    /**
     * It sets Detailed Head Code Description
     *
     * @param lStrDtlHdCodeDesc
     */
    public void setDtlHdCodeDesc(String lStrDtlHdCodeDesc)
    {
        this.lStrDtlHdCodeDesc = lStrDtlHdCodeDesc;
    }

    /**
     * It returns Detailed Head Code Description
     *
     * @return Detailed Head Code
     */
    public String getDtlHdCodeDesc()
    {
        return lStrDtlHdCodeDesc;
    }

    //*****************************   Get And Set Method Start Here    *******************************

    /**
     * It sets Expenditure Form B Header ID
     *
     * @param lIntExpEstFrmBHdrID Expenditure Form B Header ID
     */
    public void setExpEstFrmBHdrID(int lIntExpEstFrmBHdrID)
    {
        this.lIntExpEstFrmBHdrID = lIntExpEstFrmBHdrID;
    }

    /**
     * It returns Expenditure Form B Header ID
     *
     * @return Expenditure Form B Header ID
     */
    public int getExpEstFrmBHdrID()
    {
        return lIntExpEstFrmBHdrID;
    }

    /**
     * It sets FD File Extension
     *
     * @param lStrFDFileExtnsn FD File Extension
     */
    public void setFDFileExtnsn(String lStrFDFileExtnsn)
    {
        this.lStrFDFileExtnsn = lStrFDFileExtnsn;
    }

    /**
     * It returns FD File Extension
     *
     * @return File Extension
     */
    public String getFDFileExtnsn()
    {
        return lStrFDFileExtnsn;
    }

    /**
     * It sets FD File Name
     *
     * @param lStrFDFileName FD File Name
     */
    public void setFDFileName(String lStrFDFileName)
    {
        this.lStrFDFileName = lStrFDFileName;
    }

    /**
     * It returns FD FileName
     *
     * @return File Name
     */
    public String getFDFileName()
    {
        return lStrFDFileName;
    }

    /**
     * It sets FD Remarks
     *
     * @param lBytFDRemarks FD Remarks
     */
    public void setFDRemarks(byte[] lBytFDRemarks)
    {
        this.lBytFDRemarks = lBytFDRemarks;
    }

    /**
     * It returns FD Remarks
     *
     * @return FD Remarks
     */
    public byte[] getFDRemarks()
    {
        return lBytFDRemarks;
    }

    /**
     * It sets File Created Value
     *
     * @param lStrFileCrtd File Created Value
     */
    public void setFileCrtd(String lStrFileCrtd)
    {
        this.lStrFileCrtd = lStrFileCrtd;
    }

    /**
     * It returns File Created Value
     *
     * @return File Created Value
     */
    public String getFileCrtd()
    {
        return lStrFileCrtd;
    }

    /**
     * It sets File Extension
     *
     * @param lStrFileExtnsn File Extension
     */
    public void setFileExtnsn(String lStrFileExtnsn)
    {
        this.lStrFileExtnsn = lStrFileExtnsn;
    }

    /**
     * It returns File Extension
     *
     * @return File Extension
     */
    public String getFileExtnsn()
    {
        return lStrFileExtnsn;
    }

    /**
     * It sets File Name
     *
     * @param lStrFileName File Name
     */
    public void setFileName(String lStrFileName)
    {
        this.lStrFileName = lStrFileName;
    }

    /**
     * It returns FileName
     *
     * @return File Name
     */
    public String getFileName()
    {
        return lStrFileName;
    }

    /**
     * It sets File Node ID
     *
     * @param lStrFileNodeID File Node ID
     */
    public void setFileNodeID(String lStrFileNodeID)
    {
        this.lStrFileNodeID = lStrFileNodeID;
    }

    /**
     * It returns File Node ID
     *
     * @return File Node ID
     */
    public String getFileNodeID()
    {
        return lStrFileNodeID;
    }

    /**
     * It sets File Node Name
     *
     * @param lStrFileNodeName File Node Name
     */
    public void setFileNodeName(String lStrFileNodeName)
    {
        this.lStrFileNodeName = lStrFileNodeName;
    }

    /**
     * It returns File Node Name
     *
     * @return File Node Name
     */
    public String getFileNodeName()
    {
        return lStrFileNodeName;
    }

    /**
     * It sets File Status
     *
     * @param lStrFileSts File Status
     */
    public void setFileSts(String lStrFileSts)
    {
        this.lStrFileSts = lStrFileSts;
    }

    /**
     * It returns File Status
     *
     * @return File Status
     */
    public String getFileSts()
    {
        return lStrFileSts;
    }

    /**
     * It sets Financial Year Description
     *
     * @param lStrFinYrDesc Financial Year Description
     */
    public void setFinYrDesc(String lStrFinYrDesc)
    {
        this.lStrFinYrDesc = lStrFinYrDesc;
    }

    /**
     * It returns Financial Year Description
     *
     * @return Financial Year Description
     */
    public String getFinYrDesc()
    {
        return lStrFinYrDesc;
    }

    /**
     * It sets Financial Year Id
     *
     * @param lIntFinYrID Financial Year Id
     */
    public void setFinYrID(int lIntFinYrID)
    {
        this.lIntFinYrID = lIntFinYrID;
    }

    /**
     * It returns Financial Year Id
     *
     * @return Financial Year Id
     */
    public int getFinYrID()
    {
        return lIntFinYrID;
    }

    /**
     * It sets GAD Code Desc
     *
     * @param lStrGADCdDesc
     */
    public void setGADHdDesc(String lStrGADCdDesc)
    {
        this.lStrGADCdDesc = lStrGADCdDesc;
    }

    /**
     * It returns GAD Code Desc
     *
     * @return GAD Code Desc
     */
    public String getGADHdDesc()
    {
        return lStrGADCdDesc;
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

    // Methods Added by Keyur. Method added for GIA Flag, GIA Parent ID, File Extension and File Name

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
     * It sets GAD Head Code
     *
     * @param lStrGadHdCode GAD Head Code
     */
    public void setGadHdCode(String lStrGadHdCode)
    {
        this.lStrGadHdCode = lStrGadHdCode;
    }

    /**
     * It returns GAD Head Code
     *
     * @return GAD Head Code
     */
    public String getGadHdCode()
    {
        return lStrGadHdCode;
    }

    /**
     * It sets Group Minor Head Code
     *
     * @param lStrGrpMnrHdCode Group Minor Head Code
     */
    public void setGrpMnrHdCode(String lStrGrpMnrHdCode)
    {
        this.lStrGrpMnrHdCode = lStrGrpMnrHdCode;
    }

    /**
     * It returns Group Minor Head Code
     *
     * @return Group Minor Head Code
     */
    public String getGrpMnrHdCode()
    {
        return lStrGrpMnrHdCode;
    }

    /**
     * It sets Group Minor Head Desc
     *
     * @param lStrGrpMnrHdLngDesc
     */
    public void setGrpMnrHdDesc(String lStrGrpMnrHdLngDesc)
    {
        this.lStrGrpMnrHdLngDesc = lStrGrpMnrHdLngDesc;
    }

    /**
     * It returns Group Minor Head Desc
     *
     * @return Group Minor Head Desc
     */
    public String getGrpMnrHdDesc()
    {
        return lStrGrpMnrHdLngDesc;
    }

    /**
     * It sets HOD ID
     *
     * @param lIntHodID HOD ID
     */
    public void setHodID(int lIntHodID)
    {
        this.lIntHodID = lIntHodID;
    }

    /**
     * It returns HOD ID
     *
     * @return HOD ID
     */
    public int getHodID()
    {
        return lIntHodID;
    }

    /**
     * It sets HOD Name
     *
     * @param lStrHodName HOD Name
     */
    public void setHodName(String lStrHodName)
    {
        this.lStrHodName = lStrHodName;
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
     * It sets Location Id Location ID
     *
     * @param lStrLocId Location ID
     */
    public void setLocId(String lStrLocId)
    {
        this.lStrLocId = lStrLocId;
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
     * It sets Level ID
     *
     * @param lIntLvlID
     */
    public void setLvlID(int lIntLvlID)
    {
        this.lIntLvlID = lIntLvlID;
    }

    /**
     * It returns Level ID
     *
     * @return Level ID
     */
    public int getLvlID()
    {
        return lIntLvlID;
    }

    /**
     * It sets Minister In Charge
     *
     * @param lStrMinIncharge Minister In Charge
     */
    public void setMinIncharge(String lStrMinIncharge)
    {
        this.lStrMinIncharge = lStrMinIncharge;
    }

    /**
     * It returns Minister In Charge
     *
     * @return Minister In Charge
     */
    public String getMinIncharge()
    {
        return lStrMinIncharge;
    }

    /**
     * It sets MajorHead Code
     *
     * @param lStrMjrHdCode MajorHead Code
     */
    public void setMjrHdCode(String lStrMjrHdCode)
    {
        this.lStrMjrHdCode = lStrMjrHdCode;
    }

    /**
     * It returns MajorHead Code
     *
     * @return MajorHead Code
     */
    public String getMjrHdCode()
    {
        return lStrMjrHdCode;
    }

    /**
     * It sets Major Head Desc
     *
     * @param lStrMjrHdLngDesc
     */
    public void setMjrHdDesc(String lStrMjrHdLngDesc)
    {
        this.lStrMjrHdLngDesc = lStrMjrHdLngDesc;
    }

    /**
     * It returns Major Head Desc
     *
     * @return Major Head Desc
     */
    public String getMjrHdDesc()
    {
        return lStrMjrHdLngDesc;
    }

    /**
     * It sets Minor Head Code
     *
     * @param lStrMnrHdCode Minor Head Code
     */
    public void setMnrHdCode(String lStrMnrHdCode)
    {
        this.lStrMnrHdCode = lStrMnrHdCode;
    }

    /**
     * It returns Minor Head Code
     *
     * @return Minor Head Code
     */
    public String getMnrHdCode()
    {
        return lStrMnrHdCode;
    }

    /**
     * It sets Minor Head Desc
     *
     * @param lStrMnrHdLngDesc
     */
    public void setMnrHdDesc(String lStrMnrHdLngDesc)
    {
        this.lStrMnrHdLngDesc = lStrMnrHdLngDesc;
    }

    /**
     * It returns Minor Head Desc
     *
     * @return Minor Head Desc
     */
    public String getMnrHdDesc()
    {
        return lStrMnrHdLngDesc;
    }

    /**
     * It sets Office Head Code
     *
     * @param lStrOfficeHdCode Office Head Code
     */
    public void setOfficeHdCode(String lStrOfficeHdCode)
    {
        this.lStrOfficeHdCode = lStrOfficeHdCode;
    }

    /**
     * It returns Office Head Code
     *
     * @return Office Head Code
     */
    public String getOfficeHdCode()
    {
        return lStrOfficeHdCode;
    }

    /**
     * It sets Office Code Desc
     *
     * @param lStrOfficeCdDesc
     */
    public void setOfficeHdDesc(String lStrOfficeCdDesc)
    {
        this.lStrOfficeCdDesc = lStrOfficeCdDesc;
    }

    /**
     * It returns Office Code Desc
     *
     * @return Office Code Desc
     */
    public String getOfficeHdDesc()
    {
        return lStrOfficeCdDesc;
    }

    /**
     * It sets Parent ID
     *
     * @param lIntParentID Parent ID
     */
    public void setParentID(int lIntParentID)
    {
        this.lIntParentID = lIntParentID;
    }

    /**
     * It returns Parent ID
     *
     * @return Parent ID
     */
    public int getParentID()
    {
        return lIntParentID;
    }

    /**
     * It sets Plan Type
     *
     * @param lStrPlanType Plan Type
     */
    public void setPlanType(String lStrPlanType)
    {
        this.lStrPlanType = lStrPlanType;
    }

    /**
     * It returns Plan Type
     *
     * @return Plan Type
     */
    public String getPlanType()
    {
        return lStrPlanType;
    }

    /**
     * It sets Hod Remarks
     *
     * @param lBytRemarks Hod Remarks
     */
    public void setRemarks(byte[] lBytRemarks)
    {
        this.lBytRemarks = lBytRemarks;
    }

    /**
     * It returns Hod Remarks
     *
     * @return Hod Remarks
     */
    public byte[] getRemarks()
    {
        return lBytRemarks;
    }

    /**
     * It sets Sub Head Code
     *
     * @param lStrSbHdCode Sub Head Code
     */
    public void setSbHdCode(String lStrSbHdCode)
    {
        this.lStrSbHdCode = lStrSbHdCode;
    }

    /**
     * It returns Sub Head Code
     *
     * @return Sub Head Code
     */
    public String getSbHdCode()
    {
        return lStrSbHdCode;
    }

    /**
     * It sets Sub Head Desc
     *
     * @param lStrSubHdLngDesc
     */
    public void setSbHdDesc(String lStrSubHdLngDesc)
    {
        this.lStrSubHdLngDesc = lStrSubHdLngDesc;
    }

    /**
     * It returns Sub Head Desc
     *
     * @return Sub Head Desc
     */
    public String getSbHdDesc()
    {
        return lStrSubHdLngDesc;
    }

    /**
     * It sets Sub Major Head Code
     *
     * @param lStrSbMjrHdCode Sub Major Head Code
     */
    public void setSbMjrHdCode(String lStrSbMjrHdCode)
    {
        this.lStrSbMjrHdCode = lStrSbMjrHdCode;
    }

    /**
     * It returns Sub Major Head Code
     *
     * @return Sub Major Head Code
     */
    public String getSbMjrHdCode()
    {
        return lStrSbMjrHdCode;
    }

    /**
     * It sets Sub Major Head Desc
     *
     * @param lStrSbMjrLngDesc
     */
    public void setSbMjrHdDesc(String lStrSbMjrLngDesc)
    {
        this.lStrSbMjrLngDesc = lStrSbMjrLngDesc;
    }

    /**
     * It returns Sub Major Head Desc
     *
     * @return Sub Major Head Desc
     */
    public String getSbMjrHdDesc()
    {
        return lStrSbMjrLngDesc;
    }

    /**
     * It sets Sector Code
     *
     * @param lStrSectorCd Sector Code
     */
    public void setSectorCode(String lStrSectorCd)
    {
        this.lStrSectorCd = lStrSectorCd;
    }

    /**
     * It returns Sector Code
     *
     * @return Sector Code
     */
    public String getSectorCode()
    {
        return lStrSectorCd;
    }

    /**
     * It sets Sector Desc
     *
     * @param lStrSectorDesc Sector Desc
     */
    public void setSectorDesc(String lStrSectorDesc)
    {
        this.lStrSectorDesc = lStrSectorDesc;
    }

    /**
     * It returns Sector Desc
     *
     * @return Sector Desc
     */
    public String getSectorDesc()
    {
        return lStrSectorDesc;
    }

    /**
     * It sets SubSector Code
     *
     * @param lStrSubSectorCd SubSector Code
     */
    public void setSubSectorCode(String lStrSubSectorCd)
    {
        this.lStrSubSectorCd = lStrSubSectorCd;
    }

    /**
     * It returns SubSector Code
     *
     * @return SubSector Code
     */
    public String getSubSectorCode()
    {
        return lStrSubSectorCd;
    }

    /**
     * It sets SubSector Desc
     *
     * @param lStrSubSectorDesc SubSector Desc
     */
    public void setSubSectorDesc(String lStrSubSectorDesc)
    {
        this.lStrSubSectorDesc = lStrSubSectorDesc;
    }

    /**
     * It returns SubSector Desc
     *
     * @return SubSector Desc
     */
    public String getSubSectorDesc()
    {
        return lStrSubSectorDesc;
    }

    /**
     * It sets unit ID
     *
     * @param lStrUnitID unit ID
     */
    public void setUnitId(String lStrUnitID)
    {
        this.lStrUnitID = lStrUnitID;
    }

    /**
     * It returns Unit ID
     *
     * @return unit ID
     */
    public String getUnitId()
    {
        return lStrUnitID;
    }

    /**
     * It sets Hod Version ID
     *
     * @param lIntVersionId Hod Version ID
     */
    public void setVersionId(int lIntVersionId)
    {
        this.lIntVersionId = lIntVersionId;
    }

    /**
     * It returns Hod Version ID
     *
     * @return Hod Version ID
     */
    public int getVersionId()
    {
        return lIntVersionId;
    }

    /**
     * It is used to add form B detail VO into form B detail VO array of Form B header
     *
     * @param budExpEstFrmBDtlVO [TODO Add parameter documentation here]
     */
    public void addFormBdtlList(BudExpEstFrmBDtlVO budExpEstFrmBDtlVO)
    {
        if(lObjBudExpEstFrmBDtlVO == null)
        {
            lObjBudExpEstFrmBDtlVO = new BudExpEstFrmBDtlVO[0];
        }

        int total = lObjBudExpEstFrmBDtlVO.length;
        BudExpEstFrmBDtlVO[] newlObjBudExpEstFrmBDtlVO = new BudExpEstFrmBDtlVO[total + 1];
        System.arraycopy(lObjBudExpEstFrmBDtlVO, 0, newlObjBudExpEstFrmBDtlVO, 0, total);
        newlObjBudExpEstFrmBDtlVO[total] = budExpEstFrmBDtlVO;
        setBudExpEstFrmBDtl(newlObjBudExpEstFrmBDtlVO);
    }

    /**
     * DOCUMENT ME!
     *
     * @return [TODO Add return documentation here]
     *
     * @throws CloneNotSupportedException [TODO Add exception documentation here]
     */
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    /**
     * [TODO Add parameter documentation here]
     *
     * @param mv [TODO Add parameter documentation here]
     */
    public void discribeVO(BudExpEstFrmBVO mv)
    {
        Method[] mtd = mv.getClass().getDeclaredMethods();
        String StrName = null;

        try
        {
            for(int i = 0; i < mtd.length; i++)
            {
                StrName = mtd[i].getName();

                if(StrName.substring(0, 3).equals("get"))
                {
                    //System.out.println(mtd[i].getName() + " : " + mtd[i].invoke(mv, null));
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            mtd = null;
            StrName = null;
        }
    }
}
