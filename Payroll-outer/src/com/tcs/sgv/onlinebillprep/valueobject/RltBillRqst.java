package com.tcs.sgv.onlinebillprep.valueobject;
// Generated Sep 7, 2007 2:33:51 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * RltBillRqst generated by hbm2java
 */
public class RltBillRqst implements java.io.Serializable
{

    // Fields    

    private Long rltBillRqstId;

    private Long rqstId;

    private Long billNo;

    private Long trnCounter;

    private Long createdUserId;

    private Long createdPostId;

    private Date createdDate;

    private Long updatedUserId;

    private Long updatedPostId;

    private Date updatedDate;

    private Long dbId;

    private Long status;

    private String locationCode;


    // Constructors

    /** default constructor */
    public RltBillRqst()
    {
    }


    /** minimal constructor */
    public RltBillRqst(Long rltBillRqstId, Long rqstId, Long billNo, Long trnCounter,
            Long createdUserId, Long createdPostId, Date createdDate, Long dbId, Long status,
            String locationCode)
    {
        this.rltBillRqstId = rltBillRqstId;
        this.rqstId = rqstId;
        this.billNo = billNo;
        this.trnCounter = trnCounter;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.dbId = dbId;
        this.status = status;
        this.locationCode = locationCode;
    }


    /** full constructor */
    public RltBillRqst(Long rltBillRqstId, Long rqstId, Long billNo, Long trnCounter,
            Long createdUserId, Long createdPostId, Date createdDate, Long updatedUserId,
            Long updatedPostId, Date updatedDate, Long dbId, Long status, String locationCode)
    {
        this.rltBillRqstId = rltBillRqstId;
        this.rqstId = rqstId;
        this.billNo = billNo;
        this.trnCounter = trnCounter;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.updatedUserId = updatedUserId;
        this.updatedPostId = updatedPostId;
        this.updatedDate = updatedDate;
        this.dbId = dbId;
        this.status = status;
        this.locationCode = locationCode;
    }


    // Property accessors
    public Long getRltBillRqstId()
    {
        return this.rltBillRqstId;
    }


    public void setRltBillRqstId(Long rltBillRqstId)
    {
        this.rltBillRqstId = rltBillRqstId;
    }


    public Long getRqstId()
    {
        return this.rqstId;
    }


    public void setRqstId(Long rqstId)
    {
        this.rqstId = rqstId;
    }


    public Long getBillNo()
    {
        return this.billNo;
    }


    public void setBillNo(Long billNo)
    {
        this.billNo = billNo;
    }


    public Long getTrnCounter()
    {
        return this.trnCounter;
    }


    public void setTrnCounter(Long trnCounter)
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


    public Long getStatus()
    {
        return this.status;
    }


    public void setStatus(Long status)
    {
        this.status = status;
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
