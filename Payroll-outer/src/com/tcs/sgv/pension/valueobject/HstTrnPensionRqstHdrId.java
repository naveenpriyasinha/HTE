// Generated Apr 23, 2008 2:54:58 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.pension.valueobject;


/**
 * HstTrnPensionRqstHdrId generated by hbm2java
 */
public class HstTrnPensionRqstHdrId implements java.io.Serializable   
{

    // Fields    

    private Long pensionRequestId;

    private Integer trnCounter;


    // Constructors

    /** default constructor */
    public HstTrnPensionRqstHdrId()
    {
    }


    /** full constructor */
    public HstTrnPensionRqstHdrId(Long pensionRequestId, Integer trnCounter)
    {
        this.pensionRequestId = pensionRequestId;
        this.trnCounter = trnCounter;
    }


    // Property accessors
    public Long getPensionRequestId()
    {
        return this.pensionRequestId;
    }


    public void setPensionRequestId(Long pensionRequestId)
    {
        this.pensionRequestId = pensionRequestId;
    }


    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }


    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
    }


    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof HstTrnPensionRqstHdrId))
            return false;
        HstTrnPensionRqstHdrId castOther = (HstTrnPensionRqstHdrId) other;

        return ((this.getPensionRequestId() == castOther.getPensionRequestId()) || (this.getPensionRequestId() != null
                && castOther.getPensionRequestId() != null && this.getPensionRequestId().equals(
                castOther.getPensionRequestId())))
                && ((this.getTrnCounter() == castOther.getTrnCounter()) || (this.getTrnCounter() != null
                        && castOther.getTrnCounter() != null && this.getTrnCounter()
                        .equals(castOther.getTrnCounter())));
    }


    public int hashCode()
    {
        int result = 17;

        result = 37 * result + (getPensionRequestId() == null ? 0 : this.getPensionRequestId().hashCode());
        result = 37 * result + (getTrnCounter() == null ? 0 : this.getTrnCounter().hashCode());
        return result;
    }

}
