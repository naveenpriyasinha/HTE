package com.tcs.sgv.pension.valueobject;

import java.math.BigDecimal;
import java.util.Date;

public class SavedPensionBillVO
{
    private String billCtgry;

    private Long billNo;

    private BigDecimal billGrossAmt;

    private BigDecimal billNetAmt;

    private Date billDate;

    private String bdjtMjrHd;

    private Long tokenNum;

    private Long refNum;

    private Long audPostId;

    private Long subjectId;

    private BigDecimal currBillStatus;

    private Long hierarchyRefId;

    private String ppoNo;

    private String billDesc;

    private String audtrName;

    private int objCount;

    private String billCntrlNo;

    private BigDecimal headCode;

    private String scheme;
    
    private String partyName;

    public void setBillDate(Date billDate)
    {
        this.billDate = billDate;
    }


    public Date getBillDate()
    {
        return this.billDate;
    }


    public void setCurrBillStatus(BigDecimal currBillStatus)
    {
        this.currBillStatus = currBillStatus;
    }


    public BigDecimal getCurrBillStatus()
    {
        return this.currBillStatus;
    }


    public void setObjCount(int oBjCount)
    {
        this.objCount = oBjCount;
    }


    public int getObjCount()
    {
        return this.objCount;
    }


    public void setHierarchyRefId(Long hierarchyRefId)
    {
        this.hierarchyRefId = hierarchyRefId;
    }


    public Long getHierarchyRefId()
    {
        return this.hierarchyRefId;
    }


    public void setSubjectID(Long subjectId)
    {
        this.subjectId = subjectId;
    }


    public Long getSubjectID()
    {
        return this.subjectId;
    }


    public void setAudPostId(Long audPostId)
    {
        this.audPostId = audPostId;
    }


    public Long getAudPostId()
    {
        return this.audPostId;
    }


    public void setRefNum(Long refNum)
    {
        this.refNum = refNum;
    }


    public Long getRefNum()
    {
        return this.refNum;
    }


    public void setTokenNum(Long tokenNum)
    {
        this.tokenNum = tokenNum;
    }


    public Long getTokenNum()
    {
        return this.tokenNum;
    }


    public void setBillGrossAmt(BigDecimal billGrossAmt)
    {
        this.billGrossAmt = billGrossAmt;
    }


    public void setBillNetAmt(BigDecimal billNetAmt)
    {
        this.billNetAmt = billNetAmt;
    }


    public void setHeadCode(BigDecimal headCode)
    {
        this.headCode = headCode;
    }


    public BigDecimal getBillGrossAmt()
    {
        return this.billGrossAmt;
    }


    public BigDecimal getBillNetAmt()
    {
        return this.billNetAmt;
    }


    public BigDecimal getHeadCode()
    {
        return this.headCode;
    }


    public void setBillCtgry(String billCtgry)
    {
        this.billCtgry = billCtgry;
    }


    public void setBdjtMjrHd(String bdjtMjrHd)
    {
        this.bdjtMjrHd = bdjtMjrHd;
    }


    public void setAudtrName(String audtrName)
    {
        this.audtrName = audtrName;
    }


    public void setBillDesc(String billDesc)
    {
        this.billDesc = billDesc;
    }


    public void setPpoNo(String ppoNo)
    {
        this.ppoNo = ppoNo;
    }


    public void setScheme(String scheme)
    {
        this.scheme = scheme;
    }


    public void setBillCntrlNo(String billCntrlNo)
    {
        this.billCntrlNo = billCntrlNo;
    }


    public void setBillNo(Long billNo)
    {
        this.billNo = billNo;
    }


    public String getBillCtgry()
    {
        return this.billCtgry;
    }


    public String getBdjtMjrHd()
    {
        return this.bdjtMjrHd;
    }


    public String getAudtrName()
    {
        return this.audtrName;
    }


    public String getBillDesc()
    {
        return this.billDesc;
    }


    public String getPpoNo()
    {
        return this.ppoNo;
    }


    public String getScheme()
    {
        return this.scheme;
    }


    public String getBillCntrlNo()
    {
        return this.billCntrlNo;
    }


    public Long getBillNo()
    {
        return this.billNo;
    }
    public String getPartyName()
    {
        return this.partyName;
    }
    public void setPartyName(String partyName)
    {
        this.partyName = partyName;
    }
}
