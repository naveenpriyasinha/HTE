package com.tcs.sgv.pension.valueobject;

// Generated Jun 11, 2008 12:03:10 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * RltPensionHeadcodeRate generated by hbm2java
 */
public class RltPensionHeadcodeRate implements java.io.Serializable {

	// Fields    

	private Long pensionHeadcodeRateId;

	private BigDecimal headCode;

	private String fieldType;

	private Date effectiveFromDate;

	private Date effectiveToDate;

	private BigDecimal rate;

	private BigDecimal minAmount;

	private BigDecimal uptoBasic;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal oldRate;

	// Constructors

	/** default constructor */
	public RltPensionHeadcodeRate() {
	}

	/** minimal constructor */
	public RltPensionHeadcodeRate(Long pensionHeadcodeRateId,
			BigDecimal headCode, String fieldType, BigDecimal createdUserId,
			BigDecimal createdPostId) {
		this.pensionHeadcodeRateId = pensionHeadcodeRateId;
		this.headCode = headCode;
		this.fieldType = fieldType;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
	}

	/** full constructor */
	public RltPensionHeadcodeRate(Long pensionHeadcodeRateId,
			BigDecimal headCode, String fieldType, Date effectiveFromDate,
			Date effectiveToDate, BigDecimal rate, BigDecimal minAmount,
			BigDecimal uptoBasic, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId,
			Date updatedDate, BigDecimal oldRate) {
		this.pensionHeadcodeRateId = pensionHeadcodeRateId;
		this.headCode = headCode;
		this.fieldType = fieldType;
		this.effectiveFromDate = effectiveFromDate;
		this.effectiveToDate = effectiveToDate;
		this.rate = rate;
		this.minAmount = minAmount;
		this.uptoBasic = uptoBasic;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.oldRate = oldRate;
	}

	// Property accessors
	public Long getPensionHeadcodeRateId() {
		return this.pensionHeadcodeRateId;
	}

	public void setPensionHeadcodeRateId(Long pensionHeadcodeRateId) {
		this.pensionHeadcodeRateId = pensionHeadcodeRateId;
	}

	public BigDecimal getHeadCode() {
		return this.headCode;
	}

	public void setHeadCode(BigDecimal headCode) {
		this.headCode = headCode;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Date getEffectiveFromDate() {
		return this.effectiveFromDate;
	}

	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	public Date getEffectiveToDate() {
		return this.effectiveToDate;
	}

	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getUptoBasic() {
		return this.uptoBasic;
	}

	public void setUptoBasic(BigDecimal uptoBasic) {
		this.uptoBasic = uptoBasic;
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

	public BigDecimal getOldRate() {
		return this.oldRate;
	}

	public void setOldRate(BigDecimal oldRate) {
		this.oldRate = oldRate;
	}

}