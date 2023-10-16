package com.tcs.sgv.pensionpay.valueobject;

// Generated Mar 22, 2011 6:54:11 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * StgDeliveryDtls generated by hbm2java
 */
public class StgDeliveryDtls implements java.io.Serializable {

	private Long fileId;
	private Long delvId;
	private Long delvType;
	private Date uploadDate;
	private Long uploadBy;
	private String delvStatus;
	private Long fileAttachmentId;
	private String locationCode;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getDelvId() {
		return delvId;
	}

	public void setDelvId(Long delvId) {
		this.delvId = delvId;
	}

	public Long getDelvType() {
		return delvType;
	}

	public void setDelvType(Long delvType) {
		this.delvType = delvType;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Long getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(Long uploadBy) {
		this.uploadBy = uploadBy;
	}

	public String getDelvStatus() {
		return delvStatus;
	}

	public void setDelvStatus(String delvStatus) {
		this.delvStatus = delvStatus;
	}
	
	public Long getFileAttachmentId() {
		return fileAttachmentId;
	}

	public void setFileAttachmentId(Long fileAttachmentId) {
		this.fileAttachmentId = fileAttachmentId;
	}
	
	public String getLocationCode() {
		return locationCode;
	}
	
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

}
