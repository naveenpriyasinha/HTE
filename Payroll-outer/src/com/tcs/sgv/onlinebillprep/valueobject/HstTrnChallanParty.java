package com.tcs.sgv.onlinebillprep.valueobject;
// default package
// Generated Oct 5, 2007 5:55:46 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * HstTrnChallanParty generated by hbm2java
 */
public class HstTrnChallanParty implements java.io.Serializable {

	// Fields    

	private HstTrnChallanPartyId id;

	private Long challanId;

	private String partyName;

	private BigDecimal partyAmount;

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
	public HstTrnChallanParty() {
	}

	/** minimal constructor */
	public HstTrnChallanParty(HstTrnChallanPartyId id, Long challanId,
			Long createdUserId, Long createdPostId,
			Date createdDate, Long dbId, String locationCode) {
		this.id = id;
		this.challanId = challanId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	/** full constructor */
	public HstTrnChallanParty(HstTrnChallanPartyId id, Long challanId,
			String partyName, BigDecimal partyAmount,
			Long createdUserId, Long createdPostId,
			Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, Long dbId,
			String locationCode) {
		this.id = id;
		this.challanId = challanId;
		this.partyName = partyName;
		this.partyAmount = partyAmount;
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
	public HstTrnChallanPartyId getId() {
		return this.id;
	}

	public void setId(HstTrnChallanPartyId id) {
		this.id = id;
	}

	public Long getChallanId() {
		return this.challanId;
	}

	public void setChallanId(Long challanId) {
		this.challanId = challanId;
	}

	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public BigDecimal getPartyAmount() {
		return this.partyAmount;
	}

	public void setPartyAmount(BigDecimal partyAmount) {
		this.partyAmount = partyAmount;
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
