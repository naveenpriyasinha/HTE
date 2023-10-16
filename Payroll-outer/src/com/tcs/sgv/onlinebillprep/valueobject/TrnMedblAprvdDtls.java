package com.tcs.sgv.onlinebillprep.valueobject;
// Generated Sep 7, 2007 2:37:30 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnMedblAprvdDtls generated by hbm2java
 */
public class TrnMedblAprvdDtls implements java.io.Serializable
{

    // Fields    

    private Long trnMedblAprvdDtlsId;

    private Long trnMedblHdrId;

    private String sctnEstblshmnt;

    private BigDecimal claimAmt;

    private Integer trnCounter;

    private Long createdUserId;

    private Long createdPostId;

    private Date createdDate;

    private Long updatedUserId;

    private Long updatedPostId;

    private Date updatedDate;

    private Long dbId;

    private String trtmtTime;

    private String locationCode;


    // Constructors

    /** default constructor */
    public TrnMedblAprvdDtls()
    {
    }


    /** minimal constructor */
    public TrnMedblAprvdDtls(Long trnMedblAprvdDtlsId, Long trnMedblHdrId, String sctnEstblshmnt,
            BigDecimal claimAmt, Integer trnCounter, Long createdUserId, Long createdPostId,
            Date createdDate, Long dbId, String locationCode)
    {
        this.trnMedblAprvdDtlsId = trnMedblAprvdDtlsId;
        this.trnMedblHdrId = trnMedblHdrId;
        this.sctnEstblshmnt = sctnEstblshmnt;
        this.claimAmt = claimAmt;
        this.trnCounter = trnCounter;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.dbId = dbId;
        this.locationCode = locationCode;
    }


    /** full constructor */
    public TrnMedblAprvdDtls(Long trnMedblAprvdDtlsId, Long trnMedblHdrId, String sctnEstblshmnt,
            BigDecimal claimAmt, Integer trnCounter, Long createdUserId, Long createdPostId,
            Date createdDate, Long updatedUserId, Long updatedPostId, Date updatedDate, Long dbId,
            String trtmtTime, String locationCode)
    {
        this.trnMedblAprvdDtlsId = trnMedblAprvdDtlsId;
        this.trnMedblHdrId = trnMedblHdrId;
        this.sctnEstblshmnt = sctnEstblshmnt;
        this.claimAmt = claimAmt;
        this.trnCounter = trnCounter;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.updatedUserId = updatedUserId;
        this.updatedPostId = updatedPostId;
        this.updatedDate = updatedDate;
        this.dbId = dbId;
        this.trtmtTime = trtmtTime;
        this.locationCode = locationCode;
    }


    // Property accessors
    public Long getTrnMedblAprvdDtlsId()
    {
        return this.trnMedblAprvdDtlsId;
    }


    public void setTrnMedblAprvdDtlsId(Long trnMedblAprvdDtlsId)
    {
        this.trnMedblAprvdDtlsId = trnMedblAprvdDtlsId;
    }


    public Long getTrnMedblHdrId()
    {
        return this.trnMedblHdrId;
    }


    public void setTrnMedblHdrId(Long trnMedblHdrId)
    {
        this.trnMedblHdrId = trnMedblHdrId;
    }


    public String getSctnEstblshmnt()
    {
        return this.sctnEstblshmnt;
    }


    public void setSctnEstblshmnt(String sctnEstblshmnt)
    {
        this.sctnEstblshmnt = sctnEstblshmnt;
    }


    public BigDecimal getClaimAmt()
    {
        return this.claimAmt;
    }


    public void setClaimAmt(BigDecimal claimAmt)
    {
        this.claimAmt = claimAmt;
    }


    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }


    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
    }


    public Long getCreatedUserId()
    {
        return this.createdUserId;
    }


    public void setCreatedUserId(Long createdUserId)
    {
        this.createdUserId = createdUserId;
    }


    public Long getCreatedPostId()
    {
        return this.createdPostId;
    }


    public void setCreatedPostId(Long createdPostId)
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


    public Long getUpdatedUserId()
    {
        return this.updatedUserId;
    }


    public void setUpdatedUserId(Long updatedUserId)
    {
        this.updatedUserId = updatedUserId;
    }


    public Long getUpdatedPostId()
    {
        return this.updatedPostId;
    }


    public void setUpdatedPostId(Long updatedPostId)
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


    public Long getDbId()
    {
        return this.dbId;
    }


    public void setDbId(Long dbId)
    {
        this.dbId = dbId;
    }


    public String getTrtmtTime()
    {
        return this.trtmtTime;
    }


    public void setTrtmtTime(String trtmtTime)
    {
        this.trtmtTime = trtmtTime;
    }


    public String getLocationCode()
    {
        return this.locationCode;
    }


    public void setLocationCode(String locationCode)
    {
        this.locationCode = locationCode;
    }

}
