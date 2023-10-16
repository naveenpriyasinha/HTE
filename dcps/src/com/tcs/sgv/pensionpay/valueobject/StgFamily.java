package com.tcs.sgv.pensionpay.valueobject;

// Generated Mar 22, 2011 6:54:11 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * StgFamily generated by hbm2java
 */
public class StgFamily implements java.io.Serializable {

	private Long stgFamilyId;
	private Long delvId;
	private String caseStatus;
	private String pnsrFileNo;
	private String applnNo;
	private String relationship;
	private String apfName;
	private String dob;
	private String apfHandicap;
	private String efpFromDate;
	private String fpFromDate;
	private String apaEfp;
	private String apaFp;
	private String apfGrdnName;
	private String gdrnReltn;
	private Long createdBy;
	private Date createdDate;
	private Long updatedBy;
	private Date updatedDate;

	public StgFamily() {
	}

	public StgFamily(Long stgFamilyId, Long delvId, Date createdDate) {
		this.stgFamilyId = stgFamilyId;
		this.delvId = delvId;
		this.createdDate = createdDate;
	}

	public StgFamily(Long stgFamilyId, Long delvId, String caseStatus,
			String pnsrFileNo, String applnNo, String relationship,
			String apfName, String dob, String apfHandicap, String efpFromDate,
			String fpFromDate, String apaEfp, String apaFp, String apfGrdnName,
			String gdrnReltn, Long createdBy, Date createdDate, Long updatedBy,
			Date updatedDate) {
		this.stgFamilyId = stgFamilyId;
		this.delvId = delvId;
		this.caseStatus = caseStatus;
		this.pnsrFileNo = pnsrFileNo;
		this.applnNo = applnNo;
		this.relationship = relationship;
		this.apfName = apfName;
		this.dob = dob;
		this.apfHandicap = apfHandicap;
		this.efpFromDate = efpFromDate;
		this.fpFromDate = fpFromDate;
		this.apaEfp = apaEfp;
		this.apaFp = apaFp;
		this.apfGrdnName = apfGrdnName;
		this.gdrnReltn = gdrnReltn;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public Long getStgFamilyId() {
		return this.stgFamilyId;
	}

	public void setStgFamilyId(Long stgFamilyId) {
		this.stgFamilyId = stgFamilyId;
	}

	public Long getDelvId() {
		return this.delvId;
	}

	public void setDelvId(Long delvId) {
		this.delvId = delvId;
	}

	public String getCaseStatus() {
		return this.caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getPnsrFileNo() {
		return this.pnsrFileNo;
	}

	public void setPnsrFileNo(String pnsrFileNo) {
		this.pnsrFileNo = pnsrFileNo;
	}

	public String getApplnNo() {
		return this.applnNo;
	}

	public void setApplnNo(String applnNo) {
		this.applnNo = applnNo;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getApfName() {
		return this.apfName;
	}

	public void setApfName(String apfName) {
		this.apfName = apfName;
	}

	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getApfHandicap() {
		return this.apfHandicap;
	}

	public void setApfHandicap(String apfHandicap) {
		this.apfHandicap = apfHandicap;
	}

	public String getEfpFromDate() {
		return this.efpFromDate;
	}

	public void setEfpFromDate(String efpFromDate) {
		this.efpFromDate = efpFromDate;
	}

	public String getFpFromDate() {
		return this.fpFromDate;
	}

	public void setFpFromDate(String fpFromDate) {
		this.fpFromDate = fpFromDate;
	}

	public String getApaEfp() {
		return this.apaEfp;
	}

	public void setApaEfp(String apaEfp) {
		this.apaEfp = apaEfp;
	}

	public String getApaFp() {
		return this.apaFp;
	}

	public void setApaFp(String apaFp) {
		this.apaFp = apaFp;
	}

	public String getApfGrdnName() {
		return this.apfGrdnName;
	}

	public void setApfGrdnName(String apfGrdnName) {
		this.apfGrdnName = apfGrdnName;
	}

	public String getGdrnReltn() {
		return this.gdrnReltn;
	}

	public void setGdrnReltn(String gdrnReltn) {
		this.gdrnReltn = gdrnReltn;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
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

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
