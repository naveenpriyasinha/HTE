package com.tcs.sgv.common.valueobject;

// Generated Aug 2, 2007 10:45:39 AM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * RltBankBranch generated by hbm2java
 */
public class RltBankBranch implements java.io.Serializable {

	// Fields    

	private long branchId;

	private long bankCode;

	private String branchName;

	private String branchAddress;

	private Long branchCode;

	private Long headOff;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private String locationCode;
	
	private Long micrCode;
	
	private Long langId;
	
	private Long reportingBranchCode;
	
	private String bsrCode;
	
	
	// Constructors

	/** default constructor */
	public RltBankBranch() {
	}

	/** minimal constructor */
	public RltBankBranch(long branchId, long bankCode, String branchName,
			long createdUserId, long createdPostId, String locationCode,
			Date createdDate) {
		this.branchId = branchId;
		this.bankCode = bankCode;
		this.branchName = branchName;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.locationCode = locationCode;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public RltBankBranch(long branchId, long bankCode, String branchName,
			String branchAddress, Long branchCode, Long headOff,
			long createdUserId, long createdPostId, Date createdDate,
			Long updatedUserId, Long updatedPostId, Date updatedDate,
			Long dbId,String locationCode,Long micrCode,Long reportingBranchCode,String bsrCode) {
		this.branchId = branchId;
		this.bankCode = bankCode;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
		this.branchCode = branchCode;
		this.headOff = headOff;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.locationCode=locationCode;
		this.micrCode = micrCode;
		this.reportingBranchCode = reportingBranchCode;
		this.bsrCode=bsrCode;
	}

	// Property accessors
	public long getBranchId() {
		return this.branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public long getBankCode() {
		return this.bankCode;
	}

	/**
	 * @return the bsrCode
	 */
	public String getBsrCode() {
		return bsrCode;
	}

	/**
	 * @param bsrCode the bsrCode to set
	 */
	public void setBsrCode(String bsrCode) {
		this.bsrCode = bsrCode;
	}

	public void setBankCode(long bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return this.branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public Long getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(Long branchCode) {
		this.branchCode = branchCode;
	}

	public Long getHeadOff() {
		return this.headOff;
	}

	public void setHeadOff(Long headOff) {
		this.headOff = headOff;
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

	public Long getDbId() {
		return this.dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Long getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(Long micrCode) {
		this.micrCode = micrCode;
	}

	public Long getLangId() {
		return langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
	}
	
	public Long getReportingBranchCode() {
		return this.reportingBranchCode;
	}

	public void setReportingBranchCode(Long reportingBranchCode) {
		this.reportingBranchCode = reportingBranchCode;
	}

}