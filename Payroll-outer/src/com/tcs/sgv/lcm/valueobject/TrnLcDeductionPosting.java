 package com.tcs.sgv.lcm.valueobject;
// Generated Aug 21, 2007 4:38:04 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnLcDeductionPosting generated by hbm2java
 */
public class TrnLcDeductionPosting implements java.io.Serializable {

	// Fields    

	private BigDecimal lcDedId;

	private BigDecimal lcExpId;

	private String edpCode;

	private BigDecimal amount;

	private Byte ltstCntr;

	private Character active;

	private Long finYearId;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private String locCode;

	private BigDecimal dbId;

	// Constructors

	/** default constructor */
	public TrnLcDeductionPosting() {
	}

	/** minimal constructor */
	public TrnLcDeductionPosting(BigDecimal lcDedId) {
		this.lcDedId = lcDedId;
	}

	/** full constructor */
	public TrnLcDeductionPosting(BigDecimal lcDedId, BigDecimal lcExpId,
			String edpCode, BigDecimal amount, Byte ltstCntr, Character active,
			Long finYearId, BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, String locCode,
			BigDecimal dbId) {
		this.lcDedId = lcDedId;
		this.lcExpId = lcExpId;
		this.edpCode = edpCode;
		this.amount = amount;
		this.ltstCntr = ltstCntr;
		this.active = active;
		this.finYearId = finYearId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.locCode = locCode;
		this.dbId = dbId;
	}

	// Property accessors
	public BigDecimal getLcDedId() {
		return this.lcDedId;
	}

	public void setLcDedId(BigDecimal lcDedId) {
		this.lcDedId = lcDedId;
	}

	public BigDecimal getLcExpId() {
		return this.lcExpId;
	}

	public void setLcExpId(BigDecimal lcExpId) {
		this.lcExpId = lcExpId;
	}

	public String getEdpCode() {
		return this.edpCode;
	}

	public void setEdpCode(String edpCode) {
		this.edpCode = edpCode;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Byte getLtstCntr() {
		return this.ltstCntr;
	}

	public void setLtstCntr(Byte ltstCntr) {
		this.ltstCntr = ltstCntr;
	}

	public Character getActive() {
		return this.active;
	}

	public void setActive(Character active) {
		this.active = active;
	}

	public Long getFinYearId() {
		return this.finYearId;
	}

	public void setFinYearId(Long finYearId) {
		this.finYearId = finYearId;
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

	public String getLocCode() {
		return this.locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public BigDecimal getDbId() {
		return this.dbId;
	}

	public void setDbId(BigDecimal dbId) {
		this.dbId = dbId;
	}

}