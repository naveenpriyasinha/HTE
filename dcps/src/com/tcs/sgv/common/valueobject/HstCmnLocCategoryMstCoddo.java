package com.tcs.sgv.common.valueobject;
// Generated Oct 1, 2009 2:32:44 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * HstCmnLocCategoryMstCoddo generated by hbm2java
 */
public class HstCmnLocCategoryMstCoddo implements java.io.Serializable {

	// Fields    

	private HstCmnLocCategoryMstCoddoId id;
	private Long locCategoryCode;
	private String locCategoryName;
	private Long parentLocCategoryId;
	private byte langId;
	private long activateFlag;
	private long createdBy;
	private long createdByPost;
	private Date createdDate;
	private Long updatedBy;
	private Long updatedByPost;
	private Date updatedDate;

	// Constructors

	/** default constructor */
	public HstCmnLocCategoryMstCoddo() {
	}

	/** minimal constructor */
	public HstCmnLocCategoryMstCoddo(HstCmnLocCategoryMstCoddoId id,
			Long locCategoryCode, String locCategoryName,
			Long parentLocCategoryId, byte langId, long activateFlag,
			long createdBy, long createdByPost, Date createdDate) {
		this.id = id;
		this.locCategoryCode = locCategoryCode;
		this.locCategoryName = locCategoryName;
		this.parentLocCategoryId = parentLocCategoryId;
		this.langId = langId;
		this.activateFlag = activateFlag;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public HstCmnLocCategoryMstCoddo(HstCmnLocCategoryMstCoddoId id,
			Long locCategoryCode, String locCategoryName,
			Long parentLocCategoryId, byte langId, long activateFlag,
			long createdBy, long createdByPost, Date createdDate,
			Long updatedBy, Long updatedByPost, Date updatedDate) {
		this.id = id;
		this.locCategoryCode = locCategoryCode;
		this.locCategoryName = locCategoryName;
		this.parentLocCategoryId = parentLocCategoryId;
		this.langId = langId;
		this.activateFlag = activateFlag;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedByPost = updatedByPost;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public HstCmnLocCategoryMstCoddoId getId() {
		return this.id;
	}

	public void setId(HstCmnLocCategoryMstCoddoId id) {
		this.id = id;
	}

	public Long getLocCategoryCode() {
		return this.locCategoryCode;
	}

	public void setLocCategoryCode(Long locCategoryCode) {
		this.locCategoryCode = locCategoryCode;
	}

	public String getLocCategoryName() {
		return this.locCategoryName;
	}

	public void setLocCategoryName(String locCategoryName) {
		this.locCategoryName = locCategoryName;
	}

	public Long getParentLocCategoryId() {
		return this.parentLocCategoryId;
	}

	public void setParentLocCategoryId(Long parentLocCategoryId) {
		this.parentLocCategoryId = parentLocCategoryId;
	}

	public byte getLangId() {
		return this.langId;
	}

	public void setLangId(byte langId) {
		this.langId = langId;
	}

	public long getActivateFlag() {
		return this.activateFlag;
	}

	public void setActivateFlag(long activateFlag) {
		this.activateFlag = activateFlag;
	}

	public long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public long getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(long createdByPost) {
		this.createdByPost = createdByPost;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedByPost() {
		return this.updatedByPost;
	}

	public void setUpdatedByPost(Long updatedByPost) {
		this.updatedByPost = updatedByPost;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
