// default package
// Generated Feb 29, 2008 7:16:19 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.pension.valueobject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * RltPensionHeadcodeChargable generated by hbm2java
 */
public class RltPensionHeadcodeChargable implements java.io.Serializable
{

    // Fields    

    private Long pensionHeadcodeChargableId;

    private Long headCode;

    private String billType;

    private String demandNo;

    private String mjrhdCode;

    private String submjrhdCode;

    private String minhdCode;

    private String subhdCode;

    private String dtlhdCode;

    private String edpCode;

    private Long finYearId;

    private BigDecimal createdUserId;

    private BigDecimal createdPostId;

    private Date createdDate;

    private BigDecimal updatedUserId;

    private BigDecimal updatedPostId;

    private Date updatedDate;
        
    private String activeFlag;

    // Constructors

    /** default constructor */
    public RltPensionHeadcodeChargable()
    {
    }


    /** minimal constructor */
    public RltPensionHeadcodeChargable(Long pensionHeadcodeChargableId)
    {
        this.pensionHeadcodeChargableId = pensionHeadcodeChargableId;
    }


    /** full constructor */
    public RltPensionHeadcodeChargable(Long pensionHeadcodeChargableId, Long headCode,
            String billType, String demandNo, String mjrhdCode, String submjrhdCode, String minhdCode,
            String subhdCode, String dtlhdCode, String edpCode, Long finYearId, BigDecimal createdUserId,
            BigDecimal createdPostId, Date createdDate, BigDecimal updatedUserId, BigDecimal updatedPostId,
            Date updatedDate)
    {
        this.pensionHeadcodeChargableId = pensionHeadcodeChargableId;
        this.headCode = headCode;
        this.billType = billType;
        this.demandNo = demandNo;
        this.mjrhdCode = mjrhdCode;
        this.submjrhdCode = submjrhdCode;
        this.minhdCode = minhdCode;
        this.subhdCode = subhdCode;
        this.dtlhdCode = dtlhdCode;
        this.edpCode = edpCode;
        this.finYearId = finYearId;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.updatedUserId = updatedUserId;
        this.updatedPostId = updatedPostId;
        this.updatedDate = updatedDate;
    }


    // Property accessors
    public Long getPensionHeadcodeChargableId()
    {
        return this.pensionHeadcodeChargableId;
    }


    public void setPensionHeadcodeChargableId(Long pensionHeadcodeChargableId)
    {
        this.pensionHeadcodeChargableId = pensionHeadcodeChargableId;
    }


    public Long getheadCode()
    {
        return this.headCode;
    }


    public void setheadCode(Long headCode)
    {
        this.headCode = headCode;
    }


    public String getBillType()
    {
        return this.billType;
    }


    public void setBillType(String billType)
    {
        this.billType = billType;
    }


    public String getDemandNo()
    {
        return this.demandNo;
    }


    public void setDemandNo(String demandNo)
    {
        this.demandNo = demandNo;
    }


    public String getMjrhdCode()
    {
        return this.mjrhdCode;
    }


    public void setMjrhdCode(String mjrhdCode)
    {
        this.mjrhdCode = mjrhdCode;
    }


    public String getSubmjrhdCode()
    {
        return this.submjrhdCode;
    }


    public void setSubmjrhdCode(String submjrhdCode)
    {
        this.submjrhdCode = submjrhdCode;
    }


    public String getMinhdCode()
    {
        return this.minhdCode;
    }


    public void setMinhdCode(String minhdCode)
    {
        this.minhdCode = minhdCode;
    }


    public String getSubhdCode()
    {
        return this.subhdCode;
    }


    public void setSubhdCode(String subhdCode)
    {
        this.subhdCode = subhdCode;
    }


    public String getDtlhdCode()
    {
        return this.dtlhdCode;
    }


    public void setDtlhdCode(String dtlhdCode)
    {
        this.dtlhdCode = dtlhdCode;
    }


    public String getEdpCode()
    {
        return this.edpCode;
    }


    public void setEdpCode(String edpCode)
    {
        this.edpCode = edpCode;
    }


    public Long getFinYearId()
    {
        return this.finYearId;
    }


    public void setFinYearId(Long finYearId)
    {
        this.finYearId = finYearId;
    }


    public BigDecimal getCreatedUserId()
    {
        return this.createdUserId;
    }


    public void setCreatedUserId(BigDecimal createdUserId)
    {
        this.createdUserId = createdUserId;
    }


    public BigDecimal getCreatedPostId()
    {
        return this.createdPostId;
    }


    public void setCreatedPostId(BigDecimal createdPostId)
    {
        this.createdPostId = createdPostId;
    }


    public Date getCreatedDate()
    {
        return this.createdDate;
    }


    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }


    public BigDecimal getUpdatedUserId()
    {
        return this.updatedUserId;
    }


    public void setUpdatedUserId(BigDecimal updatedUserId)
    {
        this.updatedUserId = updatedUserId;
    }


    public BigDecimal getUpdatedPostId()
    {
        return this.updatedPostId;
    }


    public void setUpdatedPostId(BigDecimal updatedPostId)
    {
        this.updatedPostId = updatedPostId;
    }


    public Date getUpdatedDate()
    {
        return this.updatedDate;
    }


    public void setUpdatedDate(Date updatedDate)
    {
        this.updatedDate = updatedDate;
    }
    
    public void setActiveFlag(String activeFlag)
    {
        this.activeFlag = activeFlag;
    }
    
    public String getActiveFlag()
    {
        return this.activeFlag;
    }

}
