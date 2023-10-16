package com.tcs.sgv.eis.valueobject;
// Generated Mar 26, 2008 9:04:24 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;

/**
 * HrEisQtrMstHst generated by hbm2java
 */
public class HrEisQtrMstHst implements java.io.Serializable {

	// Fields    

	private HrEisQtrMstHstId id;

	private OrgUserMst orgUserMstByCreatedBy;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private CmnAttachmentMst cmnAttachmentMstByBillPaidDetails;

	private OrgPostMst orgPostMstByCreatedByPost;

	private HrEisQuaterTypeMst hrQuaterTypeMst;

	private CmnAddressMst cmnAddressMst;

	private OrgUserMst orgUserMstByAllocatedTo;

	private CmnLocationMst cmnLocationMstByLocId;

	private OrgUserMst orgUserMstByUpdatedBy;

	private CmnDatabaseMst cmnDatabaseMst;

	private CmnAttachmentMst cmnAttachmentMstByClearanceCertificate;

	private CmnLocationMst cmnLocationMstByPoliceStId;
	
	private String quarterName;

	private Date allocationStartDate;
	
	private Date possessionDate;

	private Date allocationEndDate;

	private Character isDeleted;

	private Date createdDate;

	private Date updatedDate;
	private CmnLookupMst rateTypeLookup;
	private HrEisQtrMst hrEssQtrMstQtrId;
	// Constructors

	public HrEisQtrMst getHrEssQtrMstQtrId() {
		return hrEssQtrMstQtrId;
	}

	public void setHrEssQtrMstQtrId(HrEisQtrMst hrEssQtrMstQtrId) {
		this.hrEssQtrMstQtrId = hrEssQtrMstQtrId;
	}

	public CmnLookupMst getRateTypeLookup() {
		return rateTypeLookup;
	}

	public void setRateTypeLookup(CmnLookupMst rateTypeLookup) {
		this.rateTypeLookup = rateTypeLookup;
	}

	/** default constructor */
	public HrEisQtrMstHst() {
	}

	/** minimal constructor */
	public HrEisQtrMstHst(HrEisQtrMstHstId id,
			OrgUserMst orgUserMstByCreatedBy,
			OrgPostMst orgPostMstByCreatedByPost,
			CmnLocationMst cmnLocationMstByLocId,
			CmnDatabaseMst cmnDatabaseMst, Date createdDate) {
		this.id = id;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMstByLocId = cmnLocationMstByLocId;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public HrEisQtrMstHst(HrEisQtrMstHstId id,
			OrgUserMst orgUserMstByCreatedBy,
			OrgPostMst orgPostMstByUpdatedByPost,
			CmnAttachmentMst cmnAttachmentMstByBillPaidDetails,
			OrgPostMst orgPostMstByCreatedByPost, HrEisQuaterTypeMst hrQuaterTypeMst,
			CmnAddressMst cmnAddressMst, OrgUserMst orgUserMstByAllocatedTo,
			CmnLocationMst cmnLocationMstByLocId,String quarterName,
			OrgUserMst orgUserMstByUpdatedBy, CmnDatabaseMst cmnDatabaseMst,
			CmnAttachmentMst cmnAttachmentMstByClearanceCertificate,
			CmnLocationMst cmnLocationMstByPoliceStId, Date allocationStartDate,Date possessionDate,
			Date allocationEndDate, Character isDeleted, Date createdDate,
			Date updatedDate,CmnLookupMst rateTypeLookup,
			HrEisQtrMst hrEssQtrMstQtrId) {
		this.id = id;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.cmnAttachmentMstByBillPaidDetails = cmnAttachmentMstByBillPaidDetails;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.hrQuaterTypeMst = hrQuaterTypeMst;
		this.cmnAddressMst = cmnAddressMst;
		this.orgUserMstByAllocatedTo = orgUserMstByAllocatedTo;
		this.cmnLocationMstByLocId = cmnLocationMstByLocId;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.cmnAttachmentMstByClearanceCertificate = cmnAttachmentMstByClearanceCertificate;
		this.cmnLocationMstByPoliceStId = cmnLocationMstByPoliceStId;
		this.quarterName = quarterName;
		this.allocationStartDate = allocationStartDate;
		this.possessionDate = possessionDate;
		this.allocationEndDate = allocationEndDate;
		this.isDeleted = isDeleted;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.rateTypeLookup=rateTypeLookup;
		this.hrEssQtrMstQtrId=hrEssQtrMstQtrId;
	}

	// Property accessors
	public HrEisQtrMstHstId getId() {
		return this.id;
	}

	public void setId(HrEisQtrMstHstId id) {
		this.id = id;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return this.orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return this.orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(
			OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public CmnAttachmentMst getCmnAttachmentMstByBillPaidDetails() {
		return this.cmnAttachmentMstByBillPaidDetails;
	}

	public void setCmnAttachmentMstByBillPaidDetails(
			CmnAttachmentMst cmnAttachmentMstByBillPaidDetails) {
		this.cmnAttachmentMstByBillPaidDetails = cmnAttachmentMstByBillPaidDetails;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return this.orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(
			OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	

	public CmnAddressMst getCmnAddressMst() {
		return this.cmnAddressMst;
	}

	public void setCmnAddressMst(CmnAddressMst cmnAddressMst) {
		this.cmnAddressMst = cmnAddressMst;
	}

	public OrgUserMst getOrgUserMstByAllocatedTo() {
		return this.orgUserMstByAllocatedTo;
	}

	public void setOrgUserMstByAllocatedTo(OrgUserMst orgUserMstByAllocatedTo) {
		this.orgUserMstByAllocatedTo = orgUserMstByAllocatedTo;
	}

	public CmnLocationMst getCmnLocationMstByLocId() {
		return this.cmnLocationMstByLocId;
	}

	public void setCmnLocationMstByLocId(CmnLocationMst cmnLocationMstByLocId) {
		this.cmnLocationMstByLocId = cmnLocationMstByLocId;
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

	public CmnAttachmentMst getCmnAttachmentMstByClearanceCertificate() {
		return this.cmnAttachmentMstByClearanceCertificate;
	}

	public void setCmnAttachmentMstByClearanceCertificate(
			CmnAttachmentMst cmnAttachmentMstByClearanceCertificate) {
		this.cmnAttachmentMstByClearanceCertificate = cmnAttachmentMstByClearanceCertificate;
	}

	public CmnLocationMst getCmnLocationMstByPoliceStId() {
		return this.cmnLocationMstByPoliceStId;
	}

	public void setCmnLocationMstByPoliceStId(
			CmnLocationMst cmnLocationMstByPoliceStId) {
		this.cmnLocationMstByPoliceStId = cmnLocationMstByPoliceStId;
	}
	
	public String getQuarterName() {
		return this.quarterName;
	}

	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}

	public Date getAllocationStartDate() {
		return this.allocationStartDate;
	}

	public void setAllocationStartDate(Date allocationStartDate) {
		this.allocationStartDate = allocationStartDate;
	}

	public Date getAllocationEndDate() {
		return this.allocationEndDate;
	}

	public void setAllocationEndDate(Date allocationEndDate) {
		this.allocationEndDate = allocationEndDate;
	}

	public Character getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Character isDeleted) {
		this.isDeleted = isDeleted;
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

	public HrEisQuaterTypeMst getHrQuaterTypeMst() {
		return hrQuaterTypeMst;
	}

	public void setHrQuaterTypeMst(HrEisQuaterTypeMst hrQuaterTypeMst) {
		this.hrQuaterTypeMst = hrQuaterTypeMst;
	}

	public Date getPossessionDate() {
		return possessionDate;
	}

	public void setPossessionDate(Date possessionDate) {
		this.possessionDate = possessionDate;
	}

}
