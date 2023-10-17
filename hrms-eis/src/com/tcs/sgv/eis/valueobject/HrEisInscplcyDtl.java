package com.tcs.sgv.eis.valueobject;

// default package
// Generated Jan 10, 2008 9:57:12 AM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrEisEmpInsrcpolicyDtls generated by hbm2java
 */
public class HrEisInscplcyDtl implements java.io.Serializable {

	// Fields    

	private long empInsrcpolicyDtlsPk;

	private OrgUserMst orgUserMstByCreatedBy;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedPost;

	private OrgUserMst orgUserMstByOrgUserMstUserIdFk;

	private String policyNumber;

	private String nameOfPolicy;

	private String insrcCompanyName;

	private Date dateOfPolicy;

	private Long durationYear;

	private Long durationMonth;

	private Long insuredAmount;

	private Date createdDate;

	private Date updatedDate;

	// Constructors

	/** default constructor */
	public HrEisInscplcyDtl() {
	}

	/** minimal constructor */
	public HrEisInscplcyDtl(long empInsrcpolicyDtlsPk,
			OrgUserMst orgUserMstByOrgUserMstUserIdFk, String policyNumber) {
		this.empInsrcpolicyDtlsPk = empInsrcpolicyDtlsPk;
		this.orgUserMstByOrgUserMstUserIdFk = orgUserMstByOrgUserMstUserIdFk;
		this.policyNumber = policyNumber;
	}

	/** full constructor */
	public HrEisInscplcyDtl(long empInsrcpolicyDtlsPk,
			OrgUserMst orgUserMstByCreatedBy,
			OrgPostMst orgPostMstByCreatedByPost,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByUpdatedBy,
			CmnDatabaseMst cmnDatabaseMst, OrgPostMst orgPostMstByUpdatedPost,
			OrgUserMst orgUserMstByOrgUserMstUserIdFk, String policyNumber,
			String nameOfPolicy, String insrcCompanyName, Date dateOfPolicy,
			Long durationYear, Long durationMonth, Long insuredAmount,
			Date createdDate, Date updatedDate) {
		this.empInsrcpolicyDtlsPk = empInsrcpolicyDtlsPk;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedPost = orgPostMstByUpdatedPost;
		this.orgUserMstByOrgUserMstUserIdFk = orgUserMstByOrgUserMstUserIdFk;
		this.policyNumber = policyNumber;
		this.nameOfPolicy = nameOfPolicy;
		this.insrcCompanyName = insrcCompanyName;
		this.dateOfPolicy = dateOfPolicy;
		this.durationYear = durationYear;
		this.durationMonth = durationMonth;
		this.insuredAmount = insuredAmount;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public long getEmpInsrcpolicyDtlsPk() {
		return this.empInsrcpolicyDtlsPk;
	}

	public void setEmpInsrcpolicyDtlsPk(long empInsrcpolicyDtlsPk) {
		this.empInsrcpolicyDtlsPk = empInsrcpolicyDtlsPk;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return this.orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return this.orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(
			OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public CmnLocationMst getCmnLocationMst() {
		return this.cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return this.orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return this.cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public OrgPostMst getOrgPostMstByUpdatedPost() {
		return this.orgPostMstByUpdatedPost;
	}

	public void setOrgPostMstByUpdatedPost(OrgPostMst orgPostMstByUpdatedPost) {
		this.orgPostMstByUpdatedPost = orgPostMstByUpdatedPost;
	}

	public OrgUserMst getOrgUserMstByOrgUserMstUserIdFk() {
		return this.orgUserMstByOrgUserMstUserIdFk;
	}

	public void setOrgUserMstByOrgUserMstUserIdFk(
			OrgUserMst orgUserMstByOrgUserMstUserIdFk) {
		this.orgUserMstByOrgUserMstUserIdFk = orgUserMstByOrgUserMstUserIdFk;
	}

	public String getPolicyNumber() {
		return this.policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getNameOfPolicy() {
		return this.nameOfPolicy;
	}

	public void setNameOfPolicy(String nameOfPolicy) {
		this.nameOfPolicy = nameOfPolicy;
	}

	public String getInsrcCompanyName() {
		return this.insrcCompanyName;
	}

	public void setInsrcCompanyName(String insrcCompanyName) {
		this.insrcCompanyName = insrcCompanyName;
	}

	public Date getDateOfPolicy() {
		return this.dateOfPolicy;
	}

	public void setDateOfPolicy(Date dateOfPolicy) {
		this.dateOfPolicy = dateOfPolicy;
	}

	public Long getDurationYear() {
		return this.durationYear;
	}

	public void setDurationYear(Long durationYear) {
		this.durationYear = durationYear;
	}

	public Long getDurationMonth() {
		return this.durationMonth;
	}

	public void setDurationMonth(Long durationMonth) {
		this.durationMonth = durationMonth;
	}

	public Long getInsuredAmount() {
		return this.insuredAmount;
	}

	public void setInsuredAmount(Long insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}