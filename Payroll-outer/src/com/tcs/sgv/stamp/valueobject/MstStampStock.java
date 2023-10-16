package com.tcs.sgv.stamp.valueobject;
// Generated Oct 18, 2007 11:32:50 AM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstStampStock generated by hbm2java
 */
public class MstStampStock implements java.io.Serializable {

	// Fields    

	private BigDecimal stockId;

	private byte categoryCde;

	private long locCode;

	private short grpCode;

	private int dnmCode;

	private int dnmValue;

	private Integer sheetsDl;

	private Integer stampsDl;

	private int totalStampsDl;

	private Integer sheetsSl;

	private Integer stampsSl;

	private int totalStampsSl;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal dbId;

	// Constructors

	/** default constructor */
	public MstStampStock() {
	}

	/** minimal constructor */
	public MstStampStock(BigDecimal stockId, byte categoryCde, long locCode,
			short grpCode, int dnmCode, int dnmValue, int totalStampsDl,
			int totalStampsSl) {
		this.stockId = stockId;
		this.categoryCde = categoryCde;
		this.locCode = locCode;
		this.grpCode = grpCode;
		this.dnmCode = dnmCode;
		this.dnmValue = dnmValue;
		this.totalStampsDl = totalStampsDl;
		this.totalStampsSl = totalStampsSl;
	}

	/** full constructor */
	public MstStampStock(BigDecimal stockId, byte categoryCde, long locCode,
			short grpCode, int dnmCode, int dnmValue, Integer sheetsDl,
			Integer stampsDl, int totalStampsDl, Integer sheetsSl,
			Integer stampsSl, int totalStampsSl, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId,
			Date updatedDate, BigDecimal dbId) {
		this.stockId = stockId;
		this.categoryCde = categoryCde;
		this.locCode = locCode;
		this.grpCode = grpCode;
		this.dnmCode = dnmCode;
		this.dnmValue = dnmValue;
		this.sheetsDl = sheetsDl;
		this.stampsDl = stampsDl;
		this.totalStampsDl = totalStampsDl;
		this.sheetsSl = sheetsSl;
		this.stampsSl = stampsSl;
		this.totalStampsSl = totalStampsSl;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
	}

	// Property accessors
	public BigDecimal getStockId() {
		return this.stockId;
	}

	public void setStockId(BigDecimal stockId) {
		this.stockId = stockId;
	}

	public byte getCategoryCde() {
		return this.categoryCde;
	}

	public void setCategoryCde(byte categoryCde) {
		this.categoryCde = categoryCde;
	}

	public long getLocCode() {
		return this.locCode;
	}

	public void setLocCode(long locCode) {
		this.locCode = locCode;
	}

	public short getGrpCode() {
		return this.grpCode;
	}

	public void setGrpCode(short grpCode) {
		this.grpCode = grpCode;
	}

	public int getDnmCode() {
		return this.dnmCode;
	}

	public void setDnmCode(int dnmCode) {
		this.dnmCode = dnmCode;
	}

	public int getDnmValue() {
		return this.dnmValue;
	}

	public void setDnmValue(int dnmValue) {
		this.dnmValue = dnmValue;
	}

	public Integer getSheetsDl() {
		return this.sheetsDl;
	}

	public void setSheetsDl(Integer sheetsDl) {
		this.sheetsDl = sheetsDl;
	}

	public Integer getStampsDl() {
		return this.stampsDl;
	}

	public void setStampsDl(Integer stampsDl) {
		this.stampsDl = stampsDl;
	}

	public int getTotalStampsDl() {
		return this.totalStampsDl;
	}

	public void setTotalStampsDl(int totalStampsDl) {
		this.totalStampsDl = totalStampsDl;
	}

	public Integer getSheetsSl() {
		return this.sheetsSl;
	}

	public void setSheetsSl(Integer sheetsSl) {
		this.sheetsSl = sheetsSl;
	}

	public Integer getStampsSl() {
		return this.stampsSl;
	}

	public void setStampsSl(Integer stampsSl) {
		this.stampsSl = stampsSl;
	}

	public int getTotalStampsSl() {
		return this.totalStampsSl;
	}

	public void setTotalStampsSl(int totalStampsSl) {
		this.totalStampsSl = totalStampsSl;
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
