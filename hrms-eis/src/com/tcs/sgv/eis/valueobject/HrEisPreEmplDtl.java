// default package
package com.tcs.sgv.eis.valueobject;
// Generated Jan 23, 2008 4:17:15 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.valueobject.CmnOrganizationMst;

/**
 * HrEisEmpPreEmplDtls generated by hbm2java
 */
public class HrEisPreEmplDtl implements java.io.Serializable
{

	// Fields    

	private long empPreemplPk;

	private CmnOrganizationMst cmnOrganizationMst;

	private OrgUserMst orgUserMstByCreatedBy;

	private OrgPostMst orgPostMstByCreatedByPost;

	private OrgUserMst orgUserMstByUserId;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedPost;

	private Date dateOfJoining;

	private Date dateOfReleving;

	private String designation;

	private String jobProfile;

	private String remarks;

	private Date createdDate;

	private Date updatedDate;
	
	private Long durationYears;

	private Long durationMonths;
	
	private Long durationDays;
	
	private CmnLookupMst cmnLookupMstByIsContinue;

	// Constructors

	/** default constructor */
	public HrEisPreEmplDtl() {
	}

	/** minimal constructor */
	public HrEisPreEmplDtl(long empPreemplPk,
			CmnOrganizationMst cmnOrganizationMst,
			OrgUserMst orgUserMstByCreatedBy,
			OrgPostMst orgPostMstByCreatedByPost,
			OrgUserMst orgUserMstByUserId, CmnLocationMst cmnLocationMst,
			CmnDatabaseMst cmnDatabaseMst, Date createdDate,CmnLookupMst cmnLookupMstByIsContinue,Long durationYears,Long durationMonths,Long durationDays) {
		this.empPreemplPk = empPreemplPk;
		this.cmnOrganizationMst = cmnOrganizationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.orgUserMstByUserId = orgUserMstByUserId;
		this.cmnLocationMst = cmnLocationMst;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.createdDate = createdDate;
		this.cmnLookupMstByIsContinue=cmnLookupMstByIsContinue;
		this.durationYears=durationYears;
		this.durationMonths=durationMonths;
		this.durationDays=durationDays;
	}

	/** full constructor */
	public HrEisPreEmplDtl(long empPreemplPk,
			CmnOrganizationMst cmnOrganizationMst,
			OrgUserMst orgUserMstByCreatedBy,
			OrgPostMst orgPostMstByCreatedByPost,
			OrgUserMst orgUserMstByUserId, CmnLocationMst cmnLocationMst,
			OrgUserMst orgUserMstByUpdatedBy, CmnDatabaseMst cmnDatabaseMst,
			OrgPostMst orgPostMstByUpdatedPost, Date dateOfJoining,
			Date dateOfReleving, String designation, String jobProfile,
			String remarks, Date createdDate, Date updatedDate,CmnLookupMst cmnLookupMstByIsContinue,Long durationYears,Long durationMonths,Long durationDays) {
		this.empPreemplPk = empPreemplPk;
		this.cmnOrganizationMst = cmnOrganizationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.orgUserMstByUserId = orgUserMstByUserId;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedPost = orgPostMstByUpdatedPost;
		this.dateOfJoining = dateOfJoining;
		this.dateOfReleving = dateOfReleving;
		this.designation = designation;
		this.jobProfile = jobProfile;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.cmnLookupMstByIsContinue=cmnLookupMstByIsContinue;
		this.durationYears=durationYears;
		this.durationMonths=durationMonths;
		this.durationDays=durationDays;
	}

	// Property accessors
	public long getEmpPreemplPk() {
		return this.empPreemplPk;
	}

	public void setEmpPreemplPk(long empPreemplPk) {
		this.empPreemplPk = empPreemplPk;
	}

	public CmnOrganizationMst getCmnOrganizationMst() {
		return this.cmnOrganizationMst;
	}

	public void setCmnOrganizationMst(CmnOrganizationMst cmnOrganizationMst) {
		this.cmnOrganizationMst = cmnOrganizationMst;
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

	public OrgUserMst getOrgUserMstByUserId() {
		return this.orgUserMstByUserId;
	}

	public void setOrgUserMstByUserId(OrgUserMst orgUserMstByUserId) {
		this.orgUserMstByUserId = orgUserMstByUserId;
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

	public Date getDateOfJoining() {
		return this.dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getDateOfReleving() {
		return this.dateOfReleving;
	}

	public void setDateOfReleving(Date dateOfReleving) {
		this.dateOfReleving = dateOfReleving;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getJobProfile() {
		return this.jobProfile;
	}

	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	public CmnLookupMst getCmnLookupMstByIsContinue() {
		return cmnLookupMstByIsContinue;
	}

	public void setCmnLookupMstByIsContinue(CmnLookupMst cmnLookupMstByIsContinue) {
		this.cmnLookupMstByIsContinue = cmnLookupMstByIsContinue;
	}

	public Long getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Long durationDays) {
		this.durationDays = durationDays;
	}

	public Long getDurationMonths() {
		return durationMonths;
	}

	public void setDurationMonths(Long durationMonths) {
		this.durationMonths = durationMonths;
	}

	public Long getDurationYears() {
		return durationYears;
	}

	public void setDurationYears(Long durationYears) {
		this.durationYears = durationYears;
	}
}
