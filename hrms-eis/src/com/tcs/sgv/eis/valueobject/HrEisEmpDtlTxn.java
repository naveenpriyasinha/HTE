package com.tcs.sgv.eis.valueobject;
// default package
// Generated Nov 13, 2007 5:22:25 AM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

/**
 * HrEisEmpTrn generated by hbm2java
 */
public class HrEisEmpDtlTxn implements java.io.Serializable {

	// Fields    

	private HrEisEmpDtlTxnId id;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private CmnAddressMst cmnAddressMstByEmpBirthPlaceAddressId;

	private CmnAttachmentMst cmnAttachmentMstByEmpThumbAttachmentId;

	private OrgEmpMst orgEmpMst;

	private CmnAddressMst cmnAddressMstByEmpPermanentAddressId;

	private CmnLookupMst cmnLanguageMstByEmpMotherTongueId;

	private CmnLocationMst cmnLocationMst;

	private CmnAddressMst cmnAddressMstByEmpNativePlaceAddressId;

	private CmnLookupMst cmnLookupMstByEmpCasteId;

	private CmnLanguageMst cmnLanguageMstByLangId;

	private CmnDatabaseMst cmnDatabaseMst;

	private CmnAttachmentMst cmnAttachmentMstByEmpPhotoAttachmentId;

	private CmnLookupMst cmnLookupMstByEmpSubCasteId;

	private CmnAddressMst cmnAddressMstByEmpCurrentAddressId;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLookupMst cmnLookupMstByEmpReligionId;

	private CmnLookupMst cmnLookupMstByEmpCategoryId;

	private CmnLookupMst cmnLookupMstByEmpMaritalStatusId;

	private OrgUserMst orgUserMstByCreatedBy;

	private OrgUserMst orgUserMstByUpdatedBy;

	private Character empGender;

	private Date createdDate;

	private Date updatedDate;

	private String actionFlag;

	private String empFname;

	private String empMname;

	private String empLname;

	private Date empDob;

	private String empPrefix;

	private String addressTypeFlag;

	private Long empStdCode;

	private Long empPhoneNumber;

	private Long empMobileNumber;

	private String empEmail;
	
	private CmnCountryMst cmnCountryMstByEmpNationality;

	// Constructors

	/** default constructor */
	public HrEisEmpDtlTxn() {
	}

	/** minimal constructor */
	public HrEisEmpDtlTxn(HrEisEmpDtlTxnId id, OrgEmpMst orgEmpMst,
			CmnLanguageMst cmnLanguageMstByLangId) {
		this.id = id;
		this.orgEmpMst = orgEmpMst;
		this.cmnLanguageMstByLangId = cmnLanguageMstByLangId;
	}

	/** full constructor */
	public HrEisEmpDtlTxn(HrEisEmpDtlTxnId id, OrgPostMst orgPostMstByUpdatedByPost,
			CmnAddressMst cmnAddressMstByEmpBirthPlaceAddressId,
			CmnAttachmentMst cmnAttachmentMstByEmpThumbAttachmentId,
			OrgEmpMst orgEmpMst,
			CmnAddressMst cmnAddressMstByEmpPermanentAddressId,
			CmnLookupMst cmnLanguageMstByEmpMotherTongueId,
			CmnLocationMst cmnLocationMst,
			CmnAddressMst cmnAddressMstByEmpNativePlaceAddressId,
			CmnLookupMst cmnLookupMstByEmpCasteId,
			CmnLanguageMst cmnLanguageMstByLangId,
			CmnDatabaseMst cmnDatabaseMst,
			CmnAttachmentMst cmnAttachmentMstByEmpPhotoAttachmentId,
			CmnLookupMst cmnLookupMstByEmpSubCasteId,
			CmnAddressMst cmnAddressMstByEmpCurrentAddressId,
			OrgPostMst orgPostMstByCreatedByPost,
			CmnLookupMst cmnLookupMstByEmpReligionId,
			CmnLookupMst cmnLookupMstByEmpCategoryId,
			CmnLookupMst cmnLookupMstByEmpMaritalStatusId,
			OrgUserMst orgUserMstByCreatedBy, OrgUserMst orgUserMstByUpdatedBy,
			Character empGender, Date createdDate, Date updatedDate,
			String actionFlag, String empFname, String empMname,
			String empLname, Date empDob, String empPrefix,
			String addressTypeFlag, Long empStdCode, Long empPhoneNumber,
			Long empMobileNumber, String empEmail,CmnCountryMst cmnCountryMstByEmpNationality) {
		this.id = id;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.cmnAddressMstByEmpBirthPlaceAddressId = cmnAddressMstByEmpBirthPlaceAddressId;
		this.cmnAttachmentMstByEmpThumbAttachmentId = cmnAttachmentMstByEmpThumbAttachmentId;
		this.orgEmpMst = orgEmpMst;
		this.cmnAddressMstByEmpPermanentAddressId = cmnAddressMstByEmpPermanentAddressId;
		this.cmnLanguageMstByEmpMotherTongueId = cmnLanguageMstByEmpMotherTongueId;
		this.cmnLocationMst = cmnLocationMst;
		this.cmnAddressMstByEmpNativePlaceAddressId = cmnAddressMstByEmpNativePlaceAddressId;
		this.cmnLookupMstByEmpCasteId = cmnLookupMstByEmpCasteId;
		this.cmnLanguageMstByLangId = cmnLanguageMstByLangId;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.cmnAttachmentMstByEmpPhotoAttachmentId = cmnAttachmentMstByEmpPhotoAttachmentId;
		this.cmnLookupMstByEmpSubCasteId = cmnLookupMstByEmpSubCasteId;
		this.cmnAddressMstByEmpCurrentAddressId = cmnAddressMstByEmpCurrentAddressId;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLookupMstByEmpReligionId = cmnLookupMstByEmpReligionId;
		this.cmnLookupMstByEmpCategoryId = cmnLookupMstByEmpCategoryId;
		this.cmnLookupMstByEmpMaritalStatusId = cmnLookupMstByEmpMaritalStatusId;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.empGender = empGender;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.actionFlag = actionFlag;
		this.empFname = empFname;
		this.empMname = empMname;
		this.empLname = empLname;
		this.empDob = empDob;
		this.empPrefix = empPrefix;
		this.addressTypeFlag = addressTypeFlag;
		this.empStdCode = empStdCode;
		this.empPhoneNumber = empPhoneNumber;
		this.empMobileNumber = empMobileNumber;
		this.empEmail = empEmail;
		this.cmnCountryMstByEmpNationality=cmnCountryMstByEmpNationality;
	}

	// Property accessors
	public HrEisEmpDtlTxnId getId() {
		return this.id;
	}

	public void setId(HrEisEmpDtlTxnId id) {
		this.id = id;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return this.orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(
			OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public CmnAddressMst getCmnAddressMstByEmpBirthPlaceAddressId() {
		return this.cmnAddressMstByEmpBirthPlaceAddressId;
	}

	public void setCmnAddressMstByEmpBirthPlaceAddressId(
			CmnAddressMst cmnAddressMstByEmpBirthPlaceAddressId) {
		this.cmnAddressMstByEmpBirthPlaceAddressId = cmnAddressMstByEmpBirthPlaceAddressId;
	}

	public CmnAttachmentMst getCmnAttachmentMstByEmpThumbAttachmentId() {
		return this.cmnAttachmentMstByEmpThumbAttachmentId;
	}

	public void setCmnAttachmentMstByEmpThumbAttachmentId(
			CmnAttachmentMst cmnAttachmentMstByEmpThumbAttachmentId) {
		this.cmnAttachmentMstByEmpThumbAttachmentId = cmnAttachmentMstByEmpThumbAttachmentId;
	}

	public OrgEmpMst getOrgEmpMst() {
		return this.orgEmpMst;
	}

	public void setOrgEmpMst(OrgEmpMst orgEmpMst) {
		this.orgEmpMst = orgEmpMst;
	}

	public CmnAddressMst getCmnAddressMstByEmpPermanentAddressId() {
		return this.cmnAddressMstByEmpPermanentAddressId;
	}

	public void setCmnAddressMstByEmpPermanentAddressId(
			CmnAddressMst cmnAddressMstByEmpPermanentAddressId) {
		this.cmnAddressMstByEmpPermanentAddressId = cmnAddressMstByEmpPermanentAddressId;
	}

	public CmnLookupMst getCmnLanguageMstByEmpMotherTongueId()
	{
		return this.cmnLanguageMstByEmpMotherTongueId;
	}

	public void setCmnLanguageMstByEmpMotherTongueId(CmnLookupMst cmnLanguageMstByEmpMotherTongueId) {
		this.cmnLanguageMstByEmpMotherTongueId = cmnLanguageMstByEmpMotherTongueId;
	}

	public CmnLocationMst getCmnLocationMst() {
		return this.cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public CmnAddressMst getCmnAddressMstByEmpNativePlaceAddressId() {
		return this.cmnAddressMstByEmpNativePlaceAddressId;
	}

	public void setCmnAddressMstByEmpNativePlaceAddressId(
			CmnAddressMst cmnAddressMstByEmpNativePlaceAddressId) {
		this.cmnAddressMstByEmpNativePlaceAddressId = cmnAddressMstByEmpNativePlaceAddressId;
	}

	public CmnLookupMst getCmnLookupMstByEmpCasteId() {
		return this.cmnLookupMstByEmpCasteId;
	}

	public void setCmnLookupMstByEmpCasteId(
			CmnLookupMst cmnLookupMstByEmpCasteId) {
		this.cmnLookupMstByEmpCasteId = cmnLookupMstByEmpCasteId;
	}

	public CmnLanguageMst getCmnLanguageMstByLangId() {
		return this.cmnLanguageMstByLangId;
	}

	public void setCmnLanguageMstByLangId(CmnLanguageMst cmnLanguageMstByLangId) {
		this.cmnLanguageMstByLangId = cmnLanguageMstByLangId;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return this.cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public CmnAttachmentMst getCmnAttachmentMstByEmpPhotoAttachmentId() {
		return this.cmnAttachmentMstByEmpPhotoAttachmentId;
	}

	public void setCmnAttachmentMstByEmpPhotoAttachmentId(
			CmnAttachmentMst cmnAttachmentMstByEmpPhotoAttachmentId) {
		this.cmnAttachmentMstByEmpPhotoAttachmentId = cmnAttachmentMstByEmpPhotoAttachmentId;
	}

	public CmnLookupMst getCmnLookupMstByEmpSubCasteId() {
		return this.cmnLookupMstByEmpSubCasteId;
	}

	public void setCmnLookupMstByEmpSubCasteId(
			CmnLookupMst cmnLookupMstByEmpSubCasteId) {
		this.cmnLookupMstByEmpSubCasteId = cmnLookupMstByEmpSubCasteId;
	}

	public CmnAddressMst getCmnAddressMstByEmpCurrentAddressId() {
		return this.cmnAddressMstByEmpCurrentAddressId;
	}

	public void setCmnAddressMstByEmpCurrentAddressId(
			CmnAddressMst cmnAddressMstByEmpCurrentAddressId) {
		this.cmnAddressMstByEmpCurrentAddressId = cmnAddressMstByEmpCurrentAddressId;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return this.orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(
			OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public CmnLookupMst getCmnLookupMstByEmpReligionId() {
		return this.cmnLookupMstByEmpReligionId;
	}

	public void setCmnLookupMstByEmpReligionId(
			CmnLookupMst cmnLookupMstByEmpReligionId) {
		this.cmnLookupMstByEmpReligionId = cmnLookupMstByEmpReligionId;
	}

	public CmnLookupMst getCmnLookupMstByEmpCategoryId() {
		return this.cmnLookupMstByEmpCategoryId;
	}

	public void setCmnLookupMstByEmpCategoryId(
			CmnLookupMst cmnLookupMstByEmpCategoryId) {
		this.cmnLookupMstByEmpCategoryId = cmnLookupMstByEmpCategoryId;
	}

	public CmnLookupMst getCmnLookupMstByEmpMaritalStatusId() {
		return this.cmnLookupMstByEmpMaritalStatusId;
	}

	public void setCmnLookupMstByEmpMaritalStatusId(
			CmnLookupMst cmnLookupMstByEmpMaritalStatusId) {
		this.cmnLookupMstByEmpMaritalStatusId = cmnLookupMstByEmpMaritalStatusId;
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

	public Character getEmpGender() {
		return this.empGender;
	}

	public void setEmpGender(Character empGender) {
		this.empGender = empGender;
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

	public String getActionFlag() {
		return this.actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public String getEmpFname() {
		return this.empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpMname() {
		return this.empMname;
	}

	public void setEmpMname(String empMname) {
		this.empMname = empMname;
	}

	public String getEmpLname() {
		return this.empLname;
	}

	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}

	public Date getEmpDob() {
		return this.empDob;
	}

	public void setEmpDob(Date empDob) {
		this.empDob = empDob;
	}

	public String getEmpPrefix() {
		return this.empPrefix;
	}

	public void setEmpPrefix(String empPrefix) {
		this.empPrefix = empPrefix;
	}

	public String getAddressTypeFlag() {
		return this.addressTypeFlag;
	}

	public void setAddressTypeFlag(String addressTypeFlag) {
		this.addressTypeFlag = addressTypeFlag;
	}

	public Long getEmpStdCode() {
		return this.empStdCode;
	}

	public void setEmpStdCode(Long empStdCode) {
		this.empStdCode = empStdCode;
	}

	public Long getEmpPhoneNumber() {
		return this.empPhoneNumber;
	}

	public void setEmpPhoneNumber(Long empPhoneNumber) {
		this.empPhoneNumber = empPhoneNumber;
	}

	public Long getEmpMobileNumber() {
		return this.empMobileNumber;
	}

	public void setEmpMobileNumber(Long empMobileNumber) {
		this.empMobileNumber = empMobileNumber;
	}

	public String getEmpEmail() {
		return this.empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public CmnCountryMst getCmnCountryMstByEmpNationality() {
		return cmnCountryMstByEmpNationality;
	}

	public void setCmnCountryMstByEmpNationality(
			CmnCountryMst cmnCountryMstByEmpNationality) {
		this.cmnCountryMstByEmpNationality = cmnCountryMstByEmpNationality;
	}

}