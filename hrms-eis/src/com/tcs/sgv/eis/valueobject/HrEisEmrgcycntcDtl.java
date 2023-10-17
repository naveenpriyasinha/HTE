package com.tcs.sgv.eis.valueobject;

// default package
// Generated Nov 20, 2007 8:49:33 AM by Hibernate Tools 3.2.0.beta8

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;
import com.tcs.sgv.hod.common.valueobject.CmnContactMst;

import java.util.Date;

/**
 * HrEisEmpEmergencyContact generated by hbm2java
 */
public class HrEisEmrgcycntcDtl implements java.io.Serializable {

	// Fields    

	private long userId;

	private OrgUserMst orgUserMstByUserId;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLookupMst cmnLookupMst;

	private CmnContactMst cmnContactMst;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByCreatedBy;

	private OrgUserMst orgUserMstByUpdatedBy;

	private Character contactType;

	private long familyId;
	
	private CmnAddressMst cmnAddressMst;

	private String contactFirstName;

	private String contactMiddleName;

	private String contactLastName;

	private Date createdDate;

	private Date updatedDate;
	
	private String otherRelation;

	// Constructors

	/** default constructor */
	public HrEisEmrgcycntcDtl() {
	}

	/** minimal constructor */
	public HrEisEmrgcycntcDtl(long userId,
			OrgUserMst orgUserMstByUserId) {
		this.userId = userId;
		this.orgUserMstByUserId = orgUserMstByUserId;
	}

	/** full constructor */
	public HrEisEmrgcycntcDtl(long userId,
			OrgUserMst orgUserMstByUserId, CmnDatabaseMst cmnDatabaseMst,
			OrgPostMst orgPostMstByUpdatedByPost,
			OrgPostMst orgPostMstByCreatedByPost, CmnLookupMst cmnLookupMst,
			CmnContactMst cmnContactMst, CmnLocationMst cmnLocationMst,
			OrgUserMst orgUserMstByCreatedBy, OrgUserMst orgUserMstByUpdatedBy,
			Character contactType, long familyId,
			String contactFirstName, String contactMiddleName,String otherRelation,
			String contactLastName, Date createdDate, Date updatedDate,CmnAddressMst cmnAddressMst) {
		this.userId = userId;
		this.orgUserMstByUserId = orgUserMstByUserId;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLookupMst = cmnLookupMst;
		this.cmnContactMst = cmnContactMst;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.contactType = contactType;
		this.familyId = familyId;
		this.contactFirstName = contactFirstName;
		this.contactMiddleName = contactMiddleName;
		this.contactLastName = contactLastName;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.cmnAddressMst = cmnAddressMst;
		this.otherRelation =otherRelation;
	}

	// Property accessors
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return this.orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(
			OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public CmnLookupMst getCmnLookupMst() {
		return this.cmnLookupMst;
	}

	public void setCmnLookupMst(CmnLookupMst cmnLookupMst) {
		this.cmnLookupMst = cmnLookupMst;
	}

	public CmnContactMst getCmnContactMst() {
		return this.cmnContactMst;
	}

	public void setCmnContactMst(CmnContactMst cmnContactMst) {
		this.cmnContactMst = cmnContactMst;
	}

	public CmnLocationMst getCmnLocationMst() {
		return this.cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return this.orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return this.orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	public Character getContactType() {
		return this.contactType;
	}

	public void setContactType(Character contactType) {
		this.contactType = contactType;
	}

	public long getFamilyId() {
		return this.familyId;
	}

	public void setFamilyId(long familyId) {
		this.familyId = familyId;
	}

	public String getContactFirstName() {
		return this.contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactMiddleName() {
		return this.contactMiddleName;
	}

	public void setContactMiddleName(String contactMiddleName) {
		this.contactMiddleName = contactMiddleName;
	}

	public String getContactLastName() {
		return this.contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
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

	public CmnAddressMst getCmnAddressMst() {
		return cmnAddressMst;
	}

	public void setCmnAddressMst(CmnAddressMst cmnAddressMst) {
		this.cmnAddressMst = cmnAddressMst;
	}

	public String getOtherRelation() {
		return otherRelation;
	}

	public void setOtherRelation(String otherRelation) {
		this.otherRelation = otherRelation;
	}

}