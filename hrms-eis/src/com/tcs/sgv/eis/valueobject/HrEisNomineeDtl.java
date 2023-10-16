package com.tcs.sgv.eis.valueobject;

// default package
// Generated Mar 8, 2008 1:13:59 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;


/**
 * HrEmpnomineeDtl generated by hbm2java
 */
public class HrEisNomineeDtl implements java.io.Serializable {

	// Fields    

	private long memberId;

	private CmnLookupMst cmnLookupMstByNomnRelation;

	private OrgUserMst orgUserMstByUserId;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private CmnLookupMst cmnLookupMstByNomnBenefitTypeId;

	private CmnAddressMst cmnAddressMstByNomnAddress;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnAddressMst cmnAddressMstByGuardianAddress;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private CmnLookupMst cmnLookupMstByGuardianRelation;

	private String nomnName;

	private Double nomnSharePercent;

	private Date createdDate;

	private Date updatedDate;

	private String nomnMinor;

	private CmnLocationMst cmnLocationMst;

	private Date nomnDob;

	private String nomnOtherRelation;

	private String guardianFirstname;

	private String guardianMiddlename;

	private String guardianLastname;

	private String guardianRelationOther;

	private String deleteFlag;

	private long familyMemberId;

	private Integer  trnCounter;

	// Constructors

	/** default constructor */
	public HrEisNomineeDtl() {
	}

	/** minimal constructor */
	public HrEisNomineeDtl(long memberId, OrgUserMst orgUserMstByUserId) {
		this.memberId = memberId;
		this.orgUserMstByUserId = orgUserMstByUserId;
	}

	/** full constructor */
	public HrEisNomineeDtl(long memberId,
			CmnLookupMst cmnLookupMstByNomnRelation,
			OrgUserMst orgUserMstByUserId, CmnDatabaseMst cmnDatabaseMst,
			OrgPostMst orgPostMstByUpdatedByPost,
			CmnLookupMst cmnLookupMstByNomnBenefitTypeId,
			CmnAddressMst cmnAddressMstByNomnAddress,
			OrgPostMst orgPostMstByCreatedByPost,
			CmnAddressMst cmnAddressMstByGuardianAddress,
			OrgUserMst orgUserMstByUpdatedBy, OrgUserMst orgUserMstByCreatedBy,
			CmnLookupMst cmnLookupMstByGuardianRelation, String nomnName,
			Double nomnSharePercent, Date createdDate, Date updatedDate,
			String nomnMinor, CmnLocationMst cmnLocationMst, Date nomnDob,
			String nomnOtherRelation, String guardianFirstname,
			String guardianMiddlename, String guardianLastname,
			String guardianRelationOther, String deleteFlag,
			long familyMemberId, Integer  trnCounter) {
		this.memberId = memberId;
		this.cmnLookupMstByNomnRelation = cmnLookupMstByNomnRelation;
		this.orgUserMstByUserId = orgUserMstByUserId;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.cmnLookupMstByNomnBenefitTypeId = cmnLookupMstByNomnBenefitTypeId;
		this.cmnAddressMstByNomnAddress = cmnAddressMstByNomnAddress;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnAddressMstByGuardianAddress = cmnAddressMstByGuardianAddress;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.cmnLookupMstByGuardianRelation = cmnLookupMstByGuardianRelation;
		this.nomnName = nomnName;
		this.nomnSharePercent = nomnSharePercent;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.nomnMinor = nomnMinor;
		this.cmnLocationMst = cmnLocationMst;
		this.nomnDob = nomnDob;
		this.nomnOtherRelation = nomnOtherRelation;
		this.guardianFirstname = guardianFirstname;
		this.guardianMiddlename = guardianMiddlename;
		this.guardianLastname = guardianLastname;
		this.guardianRelationOther = guardianRelationOther;
		this.deleteFlag = deleteFlag;
		this.familyMemberId = familyMemberId;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public CmnLookupMst getCmnLookupMstByNomnRelation() {
		return this.cmnLookupMstByNomnRelation;
	}

	public void setCmnLookupMstByNomnRelation(
			CmnLookupMst cmnLookupMstByNomnRelation) {
		this.cmnLookupMstByNomnRelation = cmnLookupMstByNomnRelation;
	}

	public OrgUserMst getOrgUserMstByUserId() {
		return this.orgUserMstByUserId;
	}

	public void setOrgUserMstByUserId(OrgUserMst orgUserMstByUserId) {
		this.orgUserMstByUserId = orgUserMstByUserId;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return this.cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return this.orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(
			OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public CmnLookupMst getCmnLookupMstByNomnBenefitTypeId() {
		return this.cmnLookupMstByNomnBenefitTypeId;
	}

	public void setCmnLookupMstByNomnBenefitTypeId(
			CmnLookupMst cmnLookupMstByNomnBenefitTypeId) {
		this.cmnLookupMstByNomnBenefitTypeId = cmnLookupMstByNomnBenefitTypeId;
	}

	public CmnAddressMst getCmnAddressMstByNomnAddress() {
		return this.cmnAddressMstByNomnAddress;
	}

	public void setCmnAddressMstByNomnAddress(
			CmnAddressMst cmnAddressMstByNomnAddress) {
		this.cmnAddressMstByNomnAddress = cmnAddressMstByNomnAddress;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return this.orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(
			OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public CmnAddressMst getCmnAddressMstByGuardianAddress() {
		return this.cmnAddressMstByGuardianAddress;
	}

	public void setCmnAddressMstByGuardianAddress(
			CmnAddressMst cmnAddressMstByGuardianAddress) {
		this.cmnAddressMstByGuardianAddress = cmnAddressMstByGuardianAddress;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return this.orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return this.orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public CmnLookupMst getCmnLookupMstByGuardianRelation() {
		return this.cmnLookupMstByGuardianRelation;
	}

	public void setCmnLookupMstByGuardianRelation(
			CmnLookupMst cmnLookupMstByGuardianRelation) {
		this.cmnLookupMstByGuardianRelation = cmnLookupMstByGuardianRelation;
	}

	public String getNomnName() {
		return this.nomnName;
	}

	public void setNomnName(String nomnName) {
		this.nomnName = nomnName;
	}

	public Double getNomnSharePercent() {
		return this.nomnSharePercent;
	}

	public void setNomnSharePercent(Double nomnSharePercent) {
		this.nomnSharePercent = nomnSharePercent;
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

	public String getNomnMinor() {
		return this.nomnMinor;
	}

	public void setNomnMinor(String nomnMinor) {
		this.nomnMinor = nomnMinor;
	}

	
	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public Date getNomnDob() {
		return this.nomnDob;
	}

	public void setNomnDob(Date nomnDob) {
		this.nomnDob = nomnDob;
	}

	public String getNomnOtherRelation() {
		return this.nomnOtherRelation;
	}

	public void setNomnOtherRelation(String nomnOtherRelation) {
		this.nomnOtherRelation = nomnOtherRelation;
	}

	public String getGuardianFirstname() {
		return this.guardianFirstname;
	}

	public void setGuardianFirstname(String guardianFirstname) {
		this.guardianFirstname = guardianFirstname;
	}

	public String getGuardianMiddlename() {
		return this.guardianMiddlename;
	}

	public void setGuardianMiddlename(String guardianMiddlename) {
		this.guardianMiddlename = guardianMiddlename;
	}

	public String getGuardianLastname() {
		return this.guardianLastname;
	}

	public void setGuardianLastname(String guardianLastname) {
		this.guardianLastname = guardianLastname;
	}

	public String getGuardianRelationOther() {
		return this.guardianRelationOther;
	}

	public void setGuardianRelationOther(String guardianRelationOther) {
		this.guardianRelationOther = guardianRelationOther;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public long getFamilyMemberId() {
		return this.familyMemberId;
	}

	public void setFamilyMemberId(long familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	public Integer  getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer  trnCounter) {
		this.trnCounter = trnCounter;
	}

}
