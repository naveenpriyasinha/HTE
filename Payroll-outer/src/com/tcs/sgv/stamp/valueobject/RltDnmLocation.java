package com.tcs.sgv.stamp.valueobject;
// Generated Oct 23, 2007 11:22:17 AM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * RltDnmLocation generated by hbm2java
 */
public class RltDnmLocation implements java.io.Serializable {

	// Fields    

	private BigDecimal dnmLocId;

	private int dnmCode;

	private long locCode;

	private Integer minStock;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal dbId;

	private Short grpCode;

	private String status;

	// Constructors

	/** default constructor */
	public RltDnmLocation() {
	}

	/** minimal constructor */
	public RltDnmLocation(BigDecimal dnmLocId, int dnmCode, long locCode) {
		this.dnmLocId = dnmLocId;
		this.dnmCode = dnmCode;
		this.locCode = locCode;
	}

	/** full constructor */
	public RltDnmLocation(BigDecimal dnmLocId, int dnmCode, long locCode,
			Integer minStock, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId,
			Date updatedDate, BigDecimal dbId, Short grpCode, String status) {
		this.dnmLocId = dnmLocId;
		this.dnmCode = dnmCode;
		this.locCode = locCode;
		this.minStock = minStock;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.grpCode = grpCode;
		this.status = status;
	}

	// Property accessors
	public BigDecimal getDnmLocId() {
		return this.dnmLocId;
	}

	public void setDnmLocId(BigDecimal dnmLocId) {
		this.dnmLocId = dnmLocId;
	}

	public int getDnmCode() {
		return this.dnmCode;
	}

	public void setDnmCode(int dnmCode) {
		this.dnmCode = dnmCode;
	}

	public long getLocCode() {
		return this.locCode;
	}

	public void setLocCode(long locCode) {
		this.locCode = locCode;
	}

	public Integer getMinStock() {
		return this.minStock;
	}

	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
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

	public Short getGrpCode() {
		return this.grpCode;
	}

	public void setGrpCode(Short grpCode) {
		this.grpCode = grpCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}