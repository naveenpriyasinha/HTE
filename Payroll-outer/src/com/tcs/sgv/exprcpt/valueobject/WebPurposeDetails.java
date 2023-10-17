package com.tcs.sgv.exprcpt.valueobject;

import java.util.Date;

// Generated Oct 23, 2007 4:22:01 PM by Hibernate Tools 3.2.0.beta8

/**
 * WebPurposeDetails generated by hbm2java
 */
public class WebPurposeDetails implements java.io.Serializable
{

    // Fields    

    private String  id;
    
    private String wpdChallanId;

    private String wpdMajorHead;

    private String wpdSubMajorHead;

    private String wpdMinorHead;

    private String wpdSubMinorHead;

    private String wpdDetailHead;

    private String wpdAmount;

    private String wpdCreatedBy;

    private Date wpdCreatedDt;

    private String wpdUpdatedBy;

    private Date wpdUpdatedDt;

    // Constructors

    public String getWpdAmount()
    {
        return wpdAmount;
    }


    public void setWpdAmount(String wpdAmount)
    {
        this.wpdAmount = wpdAmount;
    }


    public String getWpdChallanId()
    {
        return wpdChallanId;
    }


    public void setWpdChallanId(String wpdChallanId)
    {
        this.wpdChallanId = wpdChallanId;
    }


    public String getWpdCreatedBy()
    {
        return wpdCreatedBy;
    }


    public void setWpdCreatedBy(String wpdCreatedBy)
    {
        this.wpdCreatedBy = wpdCreatedBy;
    }


    public Date getWpdCreatedDt()
    {
        return wpdCreatedDt;
    }


    public void setWpdCreatedDt(Date wpdCreatedDt)
    {
        this.wpdCreatedDt = wpdCreatedDt;
    }


    public String getWpdDetailHead()
    {
        return wpdDetailHead;
    }


    public void setWpdDetailHead(String wpdDetailHead)
    {
        this.wpdDetailHead = wpdDetailHead;
    }


    public String getWpdMajorHead()
    {
        return wpdMajorHead;
    }


    public void setWpdMajorHead(String wpdMajorHead)
    {
        this.wpdMajorHead = wpdMajorHead;
    }


    public String getWpdMinorHead()
    {
        return wpdMinorHead;
    }


    public void setWpdMinorHead(String wpdMinorHead)
    {
        this.wpdMinorHead = wpdMinorHead;
    }


    public String getWpdSubMajorHead()
    {
        return wpdSubMajorHead;
    }


    public void setWpdSubMajorHead(String wpdSubMajorHead)
    {
        this.wpdSubMajorHead = wpdSubMajorHead;
    }


    public String getWpdSubMinorHead()
    {
        return wpdSubMinorHead;
    }


    public void setWpdSubMinorHead(String wpdSubMinorHead)
    {
        this.wpdSubMinorHead = wpdSubMinorHead;
    }


    public String getWpdUpdatedBy()
    {
        return wpdUpdatedBy;
    }


    public void setWpdUpdatedBy(String wpdUpdatedBy)
    {
        this.wpdUpdatedBy = wpdUpdatedBy;
    }


    public Date getWpdUpdatedDt()
    {
        return wpdUpdatedDt;
    }


    public void setWpdUpdatedDt(Date wpdUpdatedDt)
    {
        this.wpdUpdatedDt = wpdUpdatedDt;
    }


    /** default constructor */
    public WebPurposeDetails()
    {
    }


    /** full constructor */
    public WebPurposeDetails(String id)
    {
        this.id = id;
    }


    // Property accessors
    public String getId()
    {
        return this.id;
    }


    public void setId(String id)
    {
        this.id = id;
    }

}