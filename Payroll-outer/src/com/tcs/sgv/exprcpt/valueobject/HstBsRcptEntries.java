package com.tcs.sgv.exprcpt.valueobject;

// Generated May 14, 2007 5:47:53 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * HstBsRcptEntries generated by hbm2java
 */
public class HstBsRcptEntries implements java.io.Serializable {

	// Fields    

	private long hstRcptEntryId;

	private long bsRcptEntryId;

	private long bsDetailId;

	private int receiptNo;

	private String depositorName;

	private String majorHead;

	private BigDecimal amount;

	private String initial;

	private String remarks;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private long updatedPostId;

	private long updtaedUserId;

	private Date updatedDate;

	private long locId;

	private long dbId;

	// Constructors

	/** default constructor */
	public HstBsRcptEntries() {
	}

	/** minimal constructor */
	public HstBsRcptEntries(long hstRcptEntryId, long bsRcptEntryId,
			long bsDetailId, int receiptNo, String depositorName,
			String majorHead, BigDecimal amount, long createdUserId,
			long createdPostId, long updatedPostId, long updtaedUserId,
			long locId, long dbId) {
		this.hstRcptEntryId = hstRcptEntryId;
		this.bsRcptEntryId = bsRcptEntryId;
		this.bsDetailId = bsDetailId;
		this.receiptNo = receiptNo;
		this.depositorName = depositorName;
		this.majorHead = majorHead;
		this.amount = amount;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.updatedPostId = updatedPostId;
		this.updtaedUserId = updtaedUserId;
		this.locId = locId;
		this.dbId = dbId;
	}

	/** full constructor */
	public HstBsRcptEntries(long hstRcptEntryId, long bsRcptEntryId,
			long bsDetailId, int receiptNo, String depositorName,
			String majorHead, BigDecimal amount, String initial,
			String remarks, long createdUserId, long createdPostId,
			Date createdDate, long updatedPostId, long updtaedUserId,
			Date updatedDate, long locId, long dbId) {
		this.hstRcptEntryId = hstRcptEntryId;
		this.bsRcptEntryId = bsRcptEntryId;
		this.bsDetailId = bsDetailId;
		this.receiptNo = receiptNo;
		this.depositorName = depositorName;
		this.majorHead = majorHead;
		this.amount = amount;
		this.initial = initial;
		this.remarks = remarks;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedPostId = updatedPostId;
		this.updtaedUserId = updtaedUserId;
		this.updatedDate = updatedDate;
		this.locId = locId;
		this.dbId = dbId;
	}

	// Property accessors
	public long getHstRcptEntryId() {
		return this.hstRcptEntryId;
	}

	public void setHstRcptEntryId(long hstRcptEntryId) {
		this.hstRcptEntryId = hstRcptEntryId;
	}

	public long getBsRcptEntryId() {
		return this.bsRcptEntryId;
	}

	public void setBsRcptEntryId(long bsRcptEntryId) {
		this.bsRcptEntryId = bsRcptEntryId;
	}

	public long getBsDetailId() {
		return this.bsDetailId;
	}

	public void setBsDetailId(long bsDetailId) {
		this.bsDetailId = bsDetailId;
	}

	public int getReceiptNo() {
		return this.receiptNo;
	}

	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getDepositorName() {
		return this.depositorName;
	}

	public void setDepositorName(String depositorName) {
		this.depositorName = depositorName;
	}

	public String getMajorHead() {
		return this.majorHead;
	}

	public void setMajorHead(String majorHead) {
		this.majorHead = majorHead;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getInitial() {
		return this.initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(long createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(long updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public long getUpdtaedUserId() {
		return this.updtaedUserId;
	}

	public void setUpdtaedUserId(long updtaedUserId) {
		this.updtaedUserId = updtaedUserId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public long getLocId() {
		return this.locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public long getDbId() {
		return this.dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

}
