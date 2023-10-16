package com.tcs.sgv.pensionpay.valueobject;


// Generated Dec 27, 2007 11:32:31 AM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * RltPensioncaseBill generated by hbm2java
 */
public class RltPensioncaseBill implements java.io.Serializable {

	// Fields    

	private long rltPensioncaseBillId;

	private String ppoNo;

	private String billType;

	private Long billNo;

	private String status;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private String locationCode;

	private Long tokenNo;

	private String billCntrlNo;
	
	private String payMode;

	private BigDecimal payAmount;

	// Constructors

	/** default constructor */
	public RltPensioncaseBill() {
	}

	/** minimal constructor */
	public RltPensioncaseBill(long rltPensioncaseBillId, String ppoNo,
			String billType, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate) {
		this.rltPensioncaseBillId = rltPensioncaseBillId;
		this.ppoNo = ppoNo;
		this.billType = billType;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public RltPensioncaseBill(long rltPensioncaseBillId, String ppoNo,
			String billType, Long billNo, String status,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, String locationCode,
			Long tokenNo, String billCntrlNo,String payMode,BigDecimal payAmount) {
		this.rltPensioncaseBillId = rltPensioncaseBillId;
		this.ppoNo = ppoNo;
		this.billType = billType;
		this.billNo = billNo;
		this.status = status;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.locationCode = locationCode;
		this.tokenNo = tokenNo;
		this.billCntrlNo = billCntrlNo;
		this.payMode = payMode;
		this.payAmount= payAmount;
	}

	// Property accessors
	public Long getRltPensioncaseBillId() {
		return this.rltPensioncaseBillId;
	}

	public void setRltPensioncaseBillId(long rltPensioncaseBillId) {
		this.rltPensioncaseBillId = rltPensioncaseBillId;
	}

	public String getPpoNo() {
		return this.ppoNo;
	}

	public void setPpoNo(String ppoNo) {
		this.ppoNo = ppoNo;
	}

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Long getBillNo() {
		return this.billNo;
	}

	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Long getTokenNo() {
		return this.tokenNo;
	}

	public void setTokenNo(Long tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getPayMode() {
		return this.payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	
	public String getBillCntrlNo() {
		return this.billCntrlNo;
	}

	public void setBillCntrlNo(String billCntrlNo) {
		this.billCntrlNo = billCntrlNo;
	}
	
	
	public BigDecimal getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

}
