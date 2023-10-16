package com.tcs.sgv.eis.valueobject;

import java.util.Date;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;



/**
 * HrEisQtrMst generated by hbm2java
 */
public class HrEisQtrMst implements java.io.Serializable {

	// Fields    

	private long quarterId;

	private OrgUserMst orgUserMstByCreatedBy;

	private CmnAttachmentMst cmnAttachmentMstByBillPaidDetails;

	private OrgUserMst orgUserMstByAllocatedTo;

	private CmnAttachmentMst cmnAttachmentMstByClearanceCertificate;

	private CmnAddressMst cmnAddressMst;

	private CmnLocationMst cmnLocationMstByPoliceStId;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private HrEisQuaterTypeMst hrQuaterTypeMst;
	
	private String quarterName;

	private HrCustodianTypeMst hrCustodianTypeMst;
	
	
	
	

	/**
	 * @return the hrCustodianTypeMst
	 */
	public HrCustodianTypeMst getHrCustodianTypeMst() {
		return hrCustodianTypeMst;
	}

	/**
	 * @param hrCustodianTypeMst the hrCustodianTypeMst to set
	 */
	public void setHrCustodianTypeMst(HrCustodianTypeMst hrCustodianTypeMst) {
		this.hrCustodianTypeMst = hrCustodianTypeMst;
	}

	public HrEisQuaterTypeMst getHrQuaterTypeMst() {
		return hrQuaterTypeMst;
	}

	public void setHrQuaterTypeMst(HrEisQuaterTypeMst hrQuaterTypeMst) {
		this.hrQuaterTypeMst = hrQuaterTypeMst;
	}

	private CmnLocationMst cmnLocationMstByLocId;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private Date allocationStartDate;

	private Date possessionDate;
	
	private Date allocationEndDate;

	private Character isDeleted;

	private Date createdDate;

	private Date updatedDate;
	
	private long trnCounter;
  
	private CmnLookupMst rateTypeLookup;
	private HrEisQtrMst HrEisQtrMstQtrId;
	
	//added by Ankit Bhatt
	private CmnLookupMst quarterGovtType;
	private CmnLookupMst quarterAllocatedToType;
	private long serviceCharge;
	private long garageCharge;
	private long quarterRent;
	private String vacantLetterNo;
	private Date vacantOrderDate;
	//ended
	
	
	// Constructors

	/** default constructor */
	public HrEisQtrMst() {
	}

	/** minimal constructor */
	public HrEisQtrMst(long quarterId) {
		this.quarterId = quarterId;
	}

	/** full constructor */
	public HrEisQtrMst(long quarterId, OrgUserMst orgUserMstByCreatedBy,
			CmnAttachmentMst cmnAttachmentMstByBillPaidDetails,
			OrgUserMst orgUserMstByAllocatedTo,
			CmnAttachmentMst cmnAttachmentMstByClearanceCertificate,
			CmnAddressMst cmnAddressMst,
			CmnLocationMst cmnLocationMstByPoliceStId,
			OrgPostMst orgPostMstByUpdatedByPost,
			OrgPostMst orgPostMstByCreatedByPost, HrEisQuaterTypeMst hrQuaterTypeMst,
			CmnLocationMst cmnLocationMstByLocId,String quarterName,
			CmnDatabaseMst cmnDatabaseMst, OrgUserMst orgUserMstByUpdatedBy,
			Date allocationStartDate, Date allocationEndDate,Date possessionDate,
			Character isDeleted, Date createdDate, Date updatedDate,long trnCounter,CmnLookupMst rateTypeLookup,
			HrEisQtrMst HrEisQtrMstQtrId) {
		this.quarterId = quarterId;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.cmnAttachmentMstByBillPaidDetails = cmnAttachmentMstByBillPaidDetails;
		this.orgUserMstByAllocatedTo = orgUserMstByAllocatedTo;
		this.cmnAttachmentMstByClearanceCertificate = cmnAttachmentMstByClearanceCertificate;
		this.cmnAddressMst = cmnAddressMst;
		this.quarterName = quarterName;
		this.cmnLocationMstByPoliceStId = cmnLocationMstByPoliceStId;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.hrQuaterTypeMst = hrQuaterTypeMst;
		this.cmnLocationMstByLocId = cmnLocationMstByLocId;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.allocationStartDate = allocationStartDate;
		this.possessionDate = possessionDate;
		this.allocationEndDate = allocationEndDate;
		this.isDeleted = isDeleted;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.trnCounter=trnCounter;
		this.rateTypeLookup=rateTypeLookup;
		this.HrEisQtrMstQtrId=HrEisQtrMstQtrId;
	}

	// Property accessors
	public long getQuarterId() {
		return this.quarterId;
	}

	public void setQuarterId(long quarterId) {
		this.quarterId = quarterId;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return this.orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public CmnAttachmentMst getCmnAttachmentMstByBillPaidDetails() {
		return this.cmnAttachmentMstByBillPaidDetails;
	}

	public void setCmnAttachmentMstByBillPaidDetails(
			CmnAttachmentMst cmnAttachmentMstByBillPaidDetails) {
		this.cmnAttachmentMstByBillPaidDetails = cmnAttachmentMstByBillPaidDetails;
	}

	public OrgUserMst getOrgUserMstByAllocatedTo() {
		return this.orgUserMstByAllocatedTo;
	}

	public void setOrgUserMstByAllocatedTo(OrgUserMst orgUserMstByAllocatedTo) {
		this.orgUserMstByAllocatedTo = orgUserMstByAllocatedTo;
	}

	public CmnAttachmentMst getCmnAttachmentMstByClearanceCertificate() {
		return this.cmnAttachmentMstByClearanceCertificate;
	}

	public void setCmnAttachmentMstByClearanceCertificate(
			CmnAttachmentMst cmnAttachmentMstByClearanceCertificate) {
		this.cmnAttachmentMstByClearanceCertificate = cmnAttachmentMstByClearanceCertificate;
	}

	public CmnAddressMst getCmnAddressMst() {
		return cmnAddressMst;
	}

	public void setCmnAddressMst(CmnAddressMst cmnAddressMst) {
		this.cmnAddressMst = cmnAddressMst;
	}

	public CmnLocationMst getCmnLocationMstByPoliceStId() {
		return this.cmnLocationMstByPoliceStId;
	}

	public void setCmnLocationMstByPoliceStId(
			CmnLocationMst cmnLocationMstByPoliceStId) {
		this.cmnLocationMstByPoliceStId = cmnLocationMstByPoliceStId;
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

	

	public HrEisQuaterTypeMst getHrEisQuaterTypeMst() {
		return hrQuaterTypeMst;
	}

	public void setHrEisQuaterTypeMst(HrEisQuaterTypeMst hrQuaterTypeMst) {
		this.hrQuaterTypeMst = hrQuaterTypeMst;
	}

	public CmnLocationMst getCmnLocationMstByLocId() {
		return this.cmnLocationMstByLocId;
	}

	public void setCmnLocationMstByLocId(CmnLocationMst cmnLocationMstByLocId) {
		this.cmnLocationMstByLocId = cmnLocationMstByLocId;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return this.cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return this.orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
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

	public long getTrnCounter() {
		return trnCounter;
	}

	public void setTrnCounter(long trnCounter) {
		this.trnCounter = trnCounter;
	}

	

	public HrEisQtrMst getHrEisQtrMstQtrId() {
		return HrEisQtrMstQtrId;
	}

	public void setHrEisQtrMstQtrId(HrEisQtrMst HrEisQtrMstQtrId) {
		this.HrEisQtrMstQtrId = HrEisQtrMstQtrId;
	}

	public CmnLookupMst getRateTypeLookup() {
		return rateTypeLookup;
	}

	public void setRateTypeLookup(CmnLookupMst rateTypeLookup) {
		this.rateTypeLookup = rateTypeLookup;
	}

	public Date getPossessionDate() {
		return possessionDate;
	}

	public void setPossessionDate(Date possessionDate) {
		this.possessionDate = possessionDate;
	}

	/**
	 * @return the quarterGovtType
	 */
	public CmnLookupMst getQuarterGovtType() {
		return quarterGovtType;
	}

	/**
	 * @param quarterGovtType the quarterGovtType to set
	 */
	public void setQuarterGovtType(CmnLookupMst quarterGovtType) {
		this.quarterGovtType = quarterGovtType;
	}

	/**
	 * @return the quarterAllocatedToType
	 */
	public CmnLookupMst getQuarterAllocatedToType() {
		return quarterAllocatedToType;
	}

	/**
	 * @param quarterAllocatedToType the quarterAllocatedToType to set
	 */
	public void setQuarterAllocatedToType(CmnLookupMst quarterAllocatedToType) {
		this.quarterAllocatedToType = quarterAllocatedToType;
	}

	/**
	 * @return the serviceCharge
	 */
	public long getServiceCharge() {
		return serviceCharge;
	}

	/**
	 * @param serviceCharge the serviceCharge to set
	 */
	public void setServiceCharge(long serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	/**
	 * @return the garageCharge
	 */
	public long getGarageCharge() {
		return garageCharge;
	}

	/**
	 * @param garageCharge the garageCharge to set
	 */
	public void setGarageCharge(long garageCharge) {
		this.garageCharge = garageCharge;
	}

	/**
	 * @return the quarterRent
	 */
	public long getQuarterRent() {
		return quarterRent;
	}

	/**
	 * @param quarterRent the quarterRent to set
	 */
	public void setQuarterRent(long quarterRent) {
		this.quarterRent = quarterRent;
	}

	/**
	 * @return the vacantLetterNo
	 */
	public String getVacantLetterNo() {
		return vacantLetterNo;
	}

	/**
	 * @param vacantLetterNo the vacantLetterNo to set
	 */
	public void setVacantLetterNo(String vacantLetterNo) {
		this.vacantLetterNo = vacantLetterNo;
	}

	/**
	 * @return the vacantOrderDate
	 */
	public Date getVacantOrderDate() {
		return vacantOrderDate;
	}

	/**
	 * @param vacantOrderDate the vacantOrderDate to set
	 */
	public void setVacantOrderDate(Date vacantOrderDate) {
		this.vacantOrderDate = vacantOrderDate;
	}
	
	

}
