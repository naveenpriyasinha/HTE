package com.tcs.sgv.onlinebillprep.valueobject;
// default package
// Generated Aug 10, 2007 4:11:48 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnTarqstExpsumm generated by hbm2java
 */
public class TrnTarqstExpsumm implements java.io.Serializable
{

    // Fields    

    private Long trnTarqstExpsummId;

    private Long trnTarqstHdrId;

    private BigDecimal incdntlExp;

    private BigDecimal grossTtl;

    private BigDecimal dedTaAdv;

    private Long dedPtaDays;

    private BigDecimal dedPta;

    private Integer trnCounter;

    private Long updatedUserId;

    private Long updatedPostId;

    private Date updatedDate;
    
    private BigDecimal rlwStmFare;
    
    private BigDecimal ttlDaClmed;
    
    private BigDecimal netClaimed;

    // Constructors

    /** default constructor */
    public TrnTarqstExpsumm()
    {
    }


    /** minimal constructor */
    public TrnTarqstExpsumm(Long trnTarqstExpsummId,
            Long trnTarqstHdrId, BigDecimal grossTtl,
            Integer trnCounter)
    {
        this.trnTarqstExpsummId = trnTarqstExpsummId;
        this.trnTarqstHdrId = trnTarqstHdrId;
        this.grossTtl = grossTtl;
        this.trnCounter = trnCounter;
    }


    /** full constructor */
    public TrnTarqstExpsumm(Long trnTarqstExpsummId,
            Long trnTarqstHdrId, BigDecimal incdntlExp,
            BigDecimal grossTtl, BigDecimal dedTaAdv, Long dedPtaDays,
            BigDecimal dedPta, Integer trnCounter, Long updatedUserId,
            Long updatedPostId, Date updatedDate, BigDecimal rlwStmFare, 
            BigDecimal ttlDaClmed, BigDecimal netClaimed)
    {
        this.trnTarqstExpsummId = trnTarqstExpsummId;
        this.trnTarqstHdrId = trnTarqstHdrId;
        this.incdntlExp = incdntlExp;
        this.grossTtl = grossTtl;
        this.dedTaAdv = dedTaAdv;
        this.dedPtaDays = dedPtaDays;
        this.dedPta = dedPta;
        this.trnCounter = trnCounter;
        this.updatedUserId = updatedUserId;
        this.updatedPostId = updatedPostId;
        this.updatedDate = updatedDate;
        this.rlwStmFare = rlwStmFare;
        this.ttlDaClmed = ttlDaClmed;
        this.netClaimed = netClaimed;
    }


    // Property accessors
    public Long getTrnTarqstExpsummId()
    {
        return this.trnTarqstExpsummId;
    }


    public void setTrnTarqstExpsummId(Long trnTarqstExpsummId)
    {
        this.trnTarqstExpsummId = trnTarqstExpsummId;
    }


    public Long getTrnTarqstHdrId()
    {
        return this.trnTarqstHdrId;
    }


    public void setTrnTarqstHdrId(Long trnTarqstHdrId)
    {
        this.trnTarqstHdrId = trnTarqstHdrId;
    }


    public BigDecimal getIncdntlExp()
    {
        return this.incdntlExp;
    }


    public void setIncdntlExp(BigDecimal incdntlExp)
    {
        this.incdntlExp = incdntlExp;
    }


    public BigDecimal getGrossTtl()
    {
        return this.grossTtl;
    }


    public void setGrossTtl(BigDecimal grossTtl)
    {
        this.grossTtl = grossTtl;
    }


    public BigDecimal getDedTaAdv()
    {
        return this.dedTaAdv;
    }


    public void setDedTaAdv(BigDecimal dedTaAdv)
    {
        this.dedTaAdv = dedTaAdv;
    }


    public Long getDedPtaDays()
    {
        return this.dedPtaDays;
    }


    public void setDedPtaDays(Long dedPtaDays)
    {
        this.dedPtaDays = dedPtaDays;
    }


    public BigDecimal getDedPta()
    {
        return this.dedPta;
    }


    public void setDedPta(BigDecimal dedPta)
    {
        this.dedPta = dedPta;
    }


    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }


    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
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


    public BigDecimal getNetClaimed()
    {
        return netClaimed;
    }


    public void setNetClaimed(BigDecimal netClaimed)
    {
        this.netClaimed = netClaimed;
    }


    public BigDecimal getRlwStmFare()
    {
        return rlwStmFare;
    }


    public void setRlwStmFare(BigDecimal rlwStmFare)
    {
        this.rlwStmFare = rlwStmFare;
    }


    public BigDecimal getTtlDaClmed()
    {
        return ttlDaClmed;
    }


    public void setTtlDaClmed(BigDecimal ttlDaClmed)
    {
        this.ttlDaClmed = ttlDaClmed;
    }

}