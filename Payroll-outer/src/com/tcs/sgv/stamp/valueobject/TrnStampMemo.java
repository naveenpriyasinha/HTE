package com.tcs.sgv.stamp.valueobject;
// Generated Oct 15, 2007 12:20:22 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnStampMemo generated by hbm2java
 */
public class TrnStampMemo implements java.io.Serializable {

	// Fields    

	private BigDecimal memoId;

	private short grpCode;

	private long locCode;

	private String status;

	private BigDecimal amnt;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal dbId;

	// Constructors

	/** default constructor */
	public TrnStampMemo() {
	}

	/** minimal constructor */
	public TrnStampMemo(BigDecimal memoId, short grpCode, long locCode,
			String status, BigDecimal amnt) {
		this.memoId = memoId;
		this.grpCode = grpCode;
		this.locCode = locCode;
		this.status = status;
		this.amnt = amnt;
	}

	/** full constructor */
	public TrnStampMemo(BigDecimal memoId, short grpCode, long locCode,
			String status, BigDecimal amnt, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId,
			Date updatedDate, BigDecimal dbId) {
		this.memoId = memoId;
		this.grpCode = grpCode;
		this.locCode = locCode;
		this.status = status;
		this.amnt = amnt;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
	}

	// Property accessors
	public BigDecimal getMemoId() {
		return this.memoId;
	}

	public void setMemoId(BigDecimal memoId) {
		this.memoId = memoId;
	}

	public short getGrpCode() {
		return this.grpCode;
	}

	public void setGrpCode(short grpCode) {
		this.grpCode = grpCode;
	}

	public long getLocCode() {
		return this.locCode;
	}

	public void setLocCode(long locCode) {
		this.locCode = locCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmnt() {
		return this.amnt;
	}

	public void setAmnt(BigDecimal amnt) {
		this.amnt = amnt;
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

	public BigDecimal getDbId() {
		return this.dbId;
	}

	public void setDbId(BigDecimal dbId) {
		this.dbId = dbId;
	}

}