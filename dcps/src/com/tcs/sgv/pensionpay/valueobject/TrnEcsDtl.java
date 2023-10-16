package com.tcs.sgv.pensionpay.valueobject;

// default package
// Generated Sep 12, 2008 1:03:55 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnEcsDtl generated by hbm2java
 */
public class TrnEcsDtl implements java.io.Serializable {

	// Fields

	private Long ecsDtlId;

	private Long ecsCode;

	private BigDecimal ecsAttachmentId;

	private BigDecimal createdUserId;

	private BigDecimal createdPost;

	private Date createdDate;

	private BigDecimal updatedUser;

	private BigDecimal updatedPost;

	private Date updatedDate;

	private BigDecimal mandateSerialNo;

	// Constructors

	/** default constructor */
	public TrnEcsDtl() {
	}

	/** minimal constructor */
	public TrnEcsDtl(Long ecsDtlId) {
		this.ecsDtlId = ecsDtlId;
	}

	/** full constructor */
	public TrnEcsDtl(Long ecsDtlId, Long ecsCode, BigDecimal ecsAttachmentId,
			BigDecimal createdUserId, BigDecimal createdPost, Date createdDate,
			BigDecimal updatedUser, BigDecimal updatedPost, Date updatedDate) {
		this.ecsDtlId = ecsDtlId;
		this.ecsCode = ecsCode;
		this.ecsAttachmentId = ecsAttachmentId;
		this.createdUserId = createdUserId;
		this.createdPost = createdPost;
		this.createdDate = createdDate;
		this.updatedUser = updatedUser;
		this.updatedPost = updatedPost;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public Long getEcsDtlId() {
		return this.ecsDtlId;
	}

	public void setEcsDtlId(Long ecsDtlId) {
		this.ecsDtlId = ecsDtlId;
	}

	public Long getEcsCode() {
		return this.ecsCode;
	}

	public void setEcsCode(Long ecsCode) {
		this.ecsCode = ecsCode;
	}

	public BigDecimal getEcsAttachmentId() {
		return this.ecsAttachmentId;
	}

	public void setEcsAttachmentId(BigDecimal ecsAttachmentId) {
		this.ecsAttachmentId = ecsAttachmentId;
	}

	public BigDecimal getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(BigDecimal createdUserId) {
		this.createdUserId = createdUserId;
	}

	public BigDecimal getCreatedPost() {
		return this.createdPost;
	}

	public void setCreatedPost(BigDecimal createdPost) {
		this.createdPost = createdPost;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(BigDecimal updatedUser) {
		this.updatedUser = updatedUser;
	}

	public BigDecimal getUpdatedPost() {
		return this.updatedPost;
	}

	public void setUpdatedPost(BigDecimal updatedPost) {
		this.updatedPost = updatedPost;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getMandateSerialNo() {
		return mandateSerialNo;
	}

	public void setMandateSerialNo(BigDecimal mandateSerialNo) {
		this.mandateSerialNo = mandateSerialNo;
	}

}
