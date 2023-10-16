package com.tcs.sgv.pension.valueobject;

// Generated May 30, 2008 6:24:02 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPensionRop1996 generated by hbm2java
 */
public class MstPensionRop1996 implements java.io.Serializable {

	// Fields    

	private Long pensionRop96Id;

	private BigDecimal oldAmount;

	private BigDecimal newAmount;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	// Constructors

	/** default constructor */
	public MstPensionRop1996() {
	}

	/** minimal constructor */
	public MstPensionRop1996(Long pensionRop96Id,
			BigDecimal createdUserId, BigDecimal createdPostId) {
		this.pensionRop96Id = pensionRop96Id;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
	}

	/** full constructor */
	public MstPensionRop1996(Long pensionRop96Id, BigDecimal oldAmount,
			BigDecimal newAmount, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId, Date updatedDate) {
		this.pensionRop96Id = pensionRop96Id;
		this.oldAmount = oldAmount;
		this.newAmount = newAmount;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public Long getPensionRop96Id() {
		return this.pensionRop96Id;
	}

	public void setPensionRop96Id(Long pensionRop96Id) {
		this.pensionRop96Id = pensionRop96Id;
	}

	public BigDecimal getOldAmount() {
		return this.oldAmount;
	}

	public void setOldAmount(BigDecimal oldAmount) {
		this.oldAmount = oldAmount;
	}

	public BigDecimal getNewAmount() {
		return this.newAmount;
	}

	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
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

}
