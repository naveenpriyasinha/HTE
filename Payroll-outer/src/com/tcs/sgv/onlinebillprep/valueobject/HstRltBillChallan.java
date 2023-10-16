package com.tcs.sgv.onlinebillprep.valueobject;
// default package
// Generated Oct 5, 2007 6:12:39 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * HstRltBillChallan generated by hbm2java
 */
public class HstRltBillChallan implements java.io.Serializable {

	// Fields    

	private HstRltBillChallanId id;

	private Long billNo;

	private Long challanNo;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private String locationCode;

	// Constructors

	/** default constructor */
	public HstRltBillChallan() {
	}

	/** minimal constructor */
	public HstRltBillChallan(HstRltBillChallanId id, Long billNo,
			Long challanNo, Long createdUserId,
			Long createdPostId, Date createdDate, Long dbId,
			String locationCode) {
		this.id = id;
		this.billNo = billNo;
		this.challanNo = challanNo;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	/** full constructor */
	public HstRltBillChallan(HstRltBillChallanId id, Long billNo,
			Long challanNo, Long createdUserId,
			Long createdPostId, Date createdDate,
			Long updatedUserId, Long updatedPostId,
			Date updatedDate, Long dbId, String locationCode) {
		this.id = id;
		this.billNo = billNo;
		this.challanNo = challanNo;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	// Property accessors
	public HstRltBillChallanId getId() {
		return this.id;
	}

	public void setId(HstRltBillChallanId id) {
		this.id = id;
	}

	public Long getBillNo() {
		return this.billNo;
	}

	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}

	public Long getChallanNo() {
		return this.challanNo;
	}

	public void setChallanNo(Long challanNo) {
		this.challanNo = challanNo;
	}

	public Long getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Long getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(Long createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Long getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getDbId() {
		return this.dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

}
