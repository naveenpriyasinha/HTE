package com.tcs.sgv.common.valueobject;

// Generated Apr 27, 2007 12:49:45 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnAttachDtls generated by hbm2java
 */
public class TrnAttachDtls implements java.io.Serializable {

	// Fields    

	private long attachDtlsId;

	private long attachId;

	private String applicationId;

	private byte[] attachFile;

	private String fileName;

	private String fileExt;

	private String attachDesc;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private BigDecimal locId;

	private BigDecimal dbId;

	// Constructors

	/** default constructor */
	public TrnAttachDtls() {
	}

	/** minimal constructor */
	public TrnAttachDtls(long attachDtlsId, long attachId,
			String applicationId, long createdUserId, long createdPostId) {
		this.attachDtlsId = attachDtlsId;
		this.attachId = attachId;
		this.applicationId = applicationId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
	}

	/** full constructor */
	public TrnAttachDtls(long attachDtlsId, long attachId,
			String applicationId, byte[] attachFile, String fileName,
			String fileExt, String attachDesc, long createdUserId,
			long createdPostId, Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, BigDecimal locId,
			BigDecimal dbId) {
		this.attachDtlsId = attachDtlsId;
		this.attachId = attachId;
		this.applicationId = applicationId;
		this.attachFile = attachFile;
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.attachDesc = attachDesc;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.locId = locId;
		this.dbId = dbId;
	}

	// Property accessors
	public long getAttachDtlsId() {
		return this.attachDtlsId;
	}

	public void setAttachDtlsId(long attachDtlsId) {
		this.attachDtlsId = attachDtlsId;
	}

	public long getAttachId() {
		return this.attachId;
	}

	public void setAttachId(long attachId) {
		this.attachId = attachId;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public byte[] getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(byte[] attachFile) {
		this.attachFile = attachFile;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return this.fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getAttachDesc() {
		return this.attachDesc;
	}

	public void setAttachDesc(String attachDesc) {
		this.attachDesc = attachDesc;
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

	public BigDecimal getLocId() {
		return this.locId;
	}

	public void setLocId(BigDecimal locId) {
		this.locId = locId;
	}

	public BigDecimal getDbId() {
		return this.dbId;
	}

	public void setDbId(BigDecimal dbId) {
		this.dbId = dbId;
	}

}