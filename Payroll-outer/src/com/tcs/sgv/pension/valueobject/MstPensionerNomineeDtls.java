// default package
// Generated Feb 29, 2008 6:26:18 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.pension.valueobject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPensionerNomineeDtls generated by hbm2java
 */
public class MstPensionerNomineeDtls implements java.io.Serializable
{

    // Fields    

    private Long nomineeId;

    private String pensionerCode;

    private String name;

    private BigDecimal percent;

    private Integer trnCounter;

    private BigDecimal createdUserId;

    private BigDecimal createdPostId;

    private Date createdDate;

    private BigDecimal updatedUserId;

    private BigDecimal updatedPostId;

    private Date updatedDate;


    // Constructors

    /** default constructor */
    public MstPensionerNomineeDtls()
    {
    }


    /** minimal constructor */
    public MstPensionerNomineeDtls(Long nomineeId, String pensionerCode, BigDecimal createdUserId,
            BigDecimal createdPostId, Date createdDate)
    {
        this.nomineeId = nomineeId;
        this.pensionerCode = pensionerCode;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
    }


    /** full constructor */
    public MstPensionerNomineeDtls(Long nomineeId,String pensionerCode, String name,
            BigDecimal percent, Integer trnCounter, BigDecimal createdUserId,
            BigDecimal createdPostId, Date createdDate, BigDecimal updatedUserId, BigDecimal updatedPostId,
            Date updatedDate)
    {
        this.nomineeId = nomineeId;
        this.pensionerCode = pensionerCode;
        this.name = name;
        this.percent = percent;
        this.trnCounter = trnCounter;
        this.createdUserId = createdUserId;
        this.createdPostId = createdPostId;
        this.createdDate = createdDate;
        this.updatedUserId = updatedUserId;
        this.updatedPostId = updatedPostId;
        this.updatedDate = updatedDate;
    }


    // Property accessors
    public Long getNomineeId()
    {
        return this.nomineeId;
    }


    public void setNomineeId(Long nomineeId)
    {
        this.nomineeId = nomineeId;
    }

    public String getPensionerCode()
    {
        return this.pensionerCode;
    }


    public void setPensionerCode(String pensionerCode)
    {
        this.pensionerCode = pensionerCode;
    }


    public String getName()
    {
        return this.name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public BigDecimal getPercent()
    {
        return this.percent;
    }


    public void setPercent(BigDecimal percent)
    {
        this.percent = percent;
    }


    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }


    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
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

}
