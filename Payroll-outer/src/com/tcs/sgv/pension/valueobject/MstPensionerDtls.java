package com.tcs.sgv.pension.valueobject;

// Generated Nov 29, 2007 2:27:30 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPensionerDtls generated by hbm2java
 */
public class MstPensionerDtls implements java.io.Serializable,Cloneable {

	// Fields    

	private Long pensionerDtlsId;

	private String pensionerCode;

	private String bankCode;

	private String branchCode;

	private String acountNo;

	private String locationCode;

	private String activeFlag;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private String oldStateCode;

	private String oldTreasury;

	private String oldSubTreasury;
	
	private String caseStatus;

    private String bankEmailId;
	// Constructors

	/** default constructor */
	public MstPensionerDtls() {
	}

	/** minimal constructor */
	public MstPensionerDtls(Long pensionerDtlsId) {
		this.pensionerDtlsId = pensionerDtlsId;
	}

	/** full constructor */
	public MstPensionerDtls(Long pensionerDtlsId, String pensionerCode,
			String bankCode, String branchCode, String acountNo,
			String locationCode, String activeFlag, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId,
			Date updatedDate, String oldStateCode, String oldTreasury,
			String oldSubTreasury, String caseStatus,String bankEmailId) {
		this.pensionerDtlsId = pensionerDtlsId;
		this.pensionerCode = pensionerCode;
		this.bankCode = bankCode;
		this.branchCode = branchCode;
		this.acountNo = acountNo;
		this.locationCode = locationCode;
		this.activeFlag = activeFlag;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.oldStateCode = oldStateCode;
		this.oldTreasury = oldTreasury;
		this.oldSubTreasury = oldSubTreasury;
		this.caseStatus = caseStatus;
        this.bankEmailId = bankEmailId;
	}

	// Property accessors
	public Long getPensionerDtlsId() {
		return this.pensionerDtlsId;
	}

	public void setPensionerDtlsId(Long pensionerDtlsId) {
		this.pensionerDtlsId = pensionerDtlsId;
	}

	public String getPensionerCode() {
		return this.pensionerCode;
	}

	public void setPensionerCode(String pensionerCode) {
		this.pensionerCode = pensionerCode;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getAcountNo() {
		return this.acountNo;
	}

	public void setAcountNo(String acountNo) {
		this.acountNo = acountNo;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public BigDecimal getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(BigDecimal createdUserId) {
		this.createdUserId = createdUserId;
	}

	public BigDecimal getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(BigDecimal createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(BigDecimal updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public BigDecimal getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(BigDecimal updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getOldStateCode() {
		return this.oldStateCode;
	}

	public void setOldStateCode(String oldStateCode) {
		this.oldStateCode = oldStateCode;
	}

	public String getOldTreasury() {
		return this.oldTreasury;
	}

	public void setOldTreasury(String oldTreasury) {
		this.oldTreasury = oldTreasury;
	}

	public String getOldSubTreasury() {
		return this.oldSubTreasury;
	}

	public void setOldSubTreasury(String oldSubTreasury) {
		this.oldSubTreasury = oldSubTreasury;
	}
	
	public void setCaseStatus(String caseStatus)
	{
		this.caseStatus = caseStatus;
	}
	
	public String getCaseStatus()
	{
		return this.caseStatus;
	}
    public void setBankEmailId(String bankEmailId)
    {
        this.bankEmailId = bankEmailId;
    }
	public String getBankEmailId()
    {
	    return this.bankEmailId;
    }
	public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
}
