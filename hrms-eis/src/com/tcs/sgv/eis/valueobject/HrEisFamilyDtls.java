package com.tcs.sgv.eis.valueobject;

// default package
// Generated Nov 3, 2007 5:57:21 AM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

/**
 * HrEisEmpFamilyDtls generated by hbm2java
 */
public class HrEisFamilyDtls implements java.io.Serializable {

	// Fields    

	private long memberId;

	private CmnLookupMst cmnLookupMstByFmGender;

	private CmnAttachmentMst cmnAttachmentMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private CmnLookupMst cmnLookupMstByFmDeadOrAlive;

	private CmnLookupMst cmnLookupMstByFmRelation;

	private CmnLocationMst cmnLocationMst;

	private CmnLookupMst cmnLookupMstByFmMaritalStatus;

	private CmnLookupMst cmnLookupMstByFmEmploymentStatus;

	private OrgUserMst orgUserMstByUserId;

	private CmnDatabaseMst cmnDatabaseMst;

	private CmnAddressMst cmnAddressMst;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLookupMst cmnLookupMstByFmOccupation;

	private CmnLookupMst cmnLookupMstByFmQualification;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private String fmFirstName;

	private String fmMiddleName;

	private String fmLastName;

	private Date fmDateOfBirth;

	private String fmRemarks;

	private Date createdDate;

	private Date updatedDate;

	private String fmRelationOther;

	private String deleteFlag;

	private String companyName;

	private String designation;

	private String annualIncome;

	private String dependentOrNot;

	private Date dateOfDemise;

	private Integer trnCounter;

	private Date startDate;
	
	private Date endDate;
	
	private String otherOccupation;
	
	private CmnCountryMst cmnCountryMstByFmNationality;
	
	private OrgDepartmentMst orgDepartmentMstByFmDept;
	
	private CmnLookupMst cmnLookupMstBySubQualification;
	
	private CmnLookupMst cmnLookupMstByDiscipline;
	
	// Constructors

	/** default constructor */
	public HrEisFamilyDtls() {
	}

	/** minimal constructor */
	public HrEisFamilyDtls(long memberId, OrgUserMst orgUserMstByUserId) {
		this.memberId = memberId;
		this.orgUserMstByUserId = orgUserMstByUserId;
	}

	/** full constructor */
	public HrEisFamilyDtls(long memberId,
			CmnLookupMst cmnLookupMstByFmGender,
			CmnAttachmentMst cmnAttachmentMst,
			OrgPostMst orgPostMstByUpdatedByPost,
			CmnLookupMst cmnLookupMstByFmDeadOrAlive,
			CmnLookupMst cmnLookupMstByFmRelation,
			CmnLocationMst cmnLocationMst,
			CmnLookupMst cmnLookupMstByFmMaritalStatus,
			CmnLookupMst cmnLookupMstByFmEmploymentStatus,
			OrgUserMst orgUserMstByUserId, CmnDatabaseMst cmnDatabaseMst,
			CmnAddressMst cmnAddressMst, OrgPostMst orgPostMstByCreatedByPost,
			CmnLookupMst cmnLookupMstByFmOccupation,
			CmnLookupMst cmnLookupMstByFmQualification,
			OrgUserMst orgUserMstByUpdatedBy, OrgUserMst orgUserMstByCreatedBy,
			String fmFirstName, String fmMiddleName,
			String fmLastName, Date fmDateOfBirth,
			String fmRemarks, Date createdDate, Date updatedDate,
			String fmRelationOther, String deleteFlag,
			String companyName, String designation,Date startDate,Date endDate,
			String annualIncome,
			String dependentOrNot, Date dateOfDemise, Integer trnCounter, String otherOccupation,
			CmnCountryMst cmnCountryMstByFmNationality,OrgDepartmentMst orgDepartmentMstByFmDept,
			CmnLookupMst cmnLookupMstBySubQualification,CmnLookupMst cmnLookupMstByDiscipline) {
		this.memberId = memberId;
		this.cmnLookupMstByFmGender = cmnLookupMstByFmGender;
		this.cmnAttachmentMst = cmnAttachmentMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.cmnLookupMstByFmDeadOrAlive = cmnLookupMstByFmDeadOrAlive;
		this.cmnLookupMstByFmRelation = cmnLookupMstByFmRelation;
		this.cmnLocationMst = cmnLocationMst;
		this.cmnLookupMstByFmMaritalStatus = cmnLookupMstByFmMaritalStatus;
		this.cmnLookupMstByFmEmploymentStatus = cmnLookupMstByFmEmploymentStatus;
		this.orgUserMstByUserId = orgUserMstByUserId;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.cmnAddressMst = cmnAddressMst;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLookupMstByFmOccupation = cmnLookupMstByFmOccupation;
		this.cmnLookupMstByFmQualification = cmnLookupMstByFmQualification;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.fmFirstName = fmFirstName;
		this.fmMiddleName = fmMiddleName;
		this.fmLastName = fmLastName;
		this.fmDateOfBirth = fmDateOfBirth;
		this.fmRemarks = fmRemarks;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.fmRelationOther = fmRelationOther;
		this.deleteFlag = deleteFlag;
		this.companyName = companyName;
		this.designation = designation;
		this.annualIncome = annualIncome;
		this.dependentOrNot = dependentOrNot;
		this.dateOfDemise = dateOfDemise;
		this.trnCounter = trnCounter;
		this.startDate = startDate;
		this.endDate = endDate;
		this.otherOccupation = otherOccupation;
		this.cmnCountryMstByFmNationality=cmnCountryMstByFmNationality;
		this.orgDepartmentMstByFmDept=orgDepartmentMstByFmDept;
		this.cmnLookupMstBySubQualification=cmnLookupMstBySubQualification;
		this.cmnLookupMstByDiscipline=cmnLookupMstByDiscipline;
	}

	// Property accessors
	public long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public CmnLookupMst getCmnLookupMstByFmGender() {
		return this.cmnLookupMstByFmGender;
	}

	public void setCmnLookupMstByFmGender(CmnLookupMst cmnLookupMstByFmGender) {
		this.cmnLookupMstByFmGender = cmnLookupMstByFmGender;
	}

	public CmnAttachmentMst getCmnAttachmentMst() {
		return this.cmnAttachmentMst;
	}

	public void setCmnAttachmentMst(CmnAttachmentMst cmnAttachmentMst) {
		this.cmnAttachmentMst = cmnAttachmentMst;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return this.orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(
			OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public CmnLookupMst getCmnLookupMstByFmDeadOrAlive() {
		return this.cmnLookupMstByFmDeadOrAlive;
	}

	public void setCmnLookupMstByFmDeadOrAlive(
			CmnLookupMst cmnLookupMstByFmDeadOrAlive) {
		this.cmnLookupMstByFmDeadOrAlive = cmnLookupMstByFmDeadOrAlive;
	}

	public CmnLookupMst getCmnLookupMstByFmRelation() {
		return this.cmnLookupMstByFmRelation;
	}

	public void setCmnLookupMstByFmRelation(
			CmnLookupMst cmnLookupMstByFmRelation) {
		this.cmnLookupMstByFmRelation = cmnLookupMstByFmRelation;
	}

	public CmnLocationMst getCmnLocationMst() {
		return this.cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public CmnLookupMst getCmnLookupMstByFmMaritalStatus() {
		return this.cmnLookupMstByFmMaritalStatus;
	}

	public void setCmnLookupMstByFmMaritalStatus(
			CmnLookupMst cmnLookupMstByFmMaritalStatus) {
		this.cmnLookupMstByFmMaritalStatus = cmnLookupMstByFmMaritalStatus;
	}

	public CmnLookupMst getCmnLookupMstByFmEmploymentStatus() {
		return this.cmnLookupMstByFmEmploymentStatus;
	}

	public void setCmnLookupMstByFmEmploymentStatus(
			CmnLookupMst cmnLookupMstByFmEmploymentStatus) {
		this.cmnLookupMstByFmEmploymentStatus = cmnLookupMstByFmEmploymentStatus;
	}

	
	public OrgUserMst getOrgUserMstByUserId() {
		return orgUserMstByUserId;
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

	public CmnAddressMst getCmnAddressMst() {
		return this.cmnAddressMst;
	}

	public void setCmnAddressMst(CmnAddressMst cmnAddressMst) {
		this.cmnAddressMst = cmnAddressMst;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return this.orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(
			OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public CmnLookupMst getCmnLookupMstByFmOccupation() {
		return this.cmnLookupMstByFmOccupation;
	}

	public void setCmnLookupMstByFmOccupation(
			CmnLookupMst cmnLookupMstByFmOccupation) {
		this.cmnLookupMstByFmOccupation = cmnLookupMstByFmOccupation;
	}

	public CmnLookupMst getCmnLookupMstByFmQualification() {
		return this.cmnLookupMstByFmQualification;
	}

	public void setCmnLookupMstByFmQualification(
			CmnLookupMst cmnLookupMstByFmQualification) {
		this.cmnLookupMstByFmQualification = cmnLookupMstByFmQualification;
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

	public String getFmFirstName() {
		return this.fmFirstName;
	}

	public void setFmFirstName(String fmFirstName) {
		this.fmFirstName = fmFirstName;
	}

	public String getFmMiddleName() {
		return this.fmMiddleName;
	}

	public void setFmMiddleName(String fmMiddleName) {
		this.fmMiddleName = fmMiddleName;
	}

	public String getFmLastName() {
		return this.fmLastName;
	}

	public void setFmLastName(String fmLastName) {
		this.fmLastName = fmLastName;
	}

	public Date getFmDateOfBirth() {
		return this.fmDateOfBirth;
	}

	public void setFmDateOfBirth(Date fmDateOfBirth) {
		this.fmDateOfBirth = fmDateOfBirth;
	}

	public String getFmRemarks() {
		return this.fmRemarks;
	}

	public void setFmRemarks(String fmRemarks) {
		this.fmRemarks = fmRemarks;
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

	public String getFmRelationOther() {
		return this.fmRelationOther;
	}

	public void setFmRelationOther(String fmRelationOther) {
		this.fmRelationOther = fmRelationOther;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAnnualIncome() {
		return this.annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getDependentOrNot() {
		return this.dependentOrNot;
	}

	public void setDependentOrNot(String dependentOrNot) {
		this.dependentOrNot = dependentOrNot;
	}

	public Date getDateOfDemise() {
		return this.dateOfDemise;
	}

	public void setDateOfDemise(Date dateOfDemise) {
		this.dateOfDemise = dateOfDemise;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getOtherOccupation() {
		return this.otherOccupation;
	}

	public void setOtherOccupation(String otherOccupation) {
		this.otherOccupation = otherOccupation;
	}
	
	public CmnCountryMst getCmnCountryMstByFmNationality() {
		return cmnCountryMstByFmNationality;
	}

	public void setCmnCountryMstByFmNationality(
			CmnCountryMst cmnCountryMstByFmNationality) {
		this.cmnCountryMstByFmNationality = cmnCountryMstByFmNationality;
	}

	public OrgDepartmentMst getOrgDepartmentMstByFmDept() {
		return orgDepartmentMstByFmDept;
	}
	
	public void setOrgDepartmentMstByFmDept(
			OrgDepartmentMst orgDepartmentMstByFmDept) {
		this.orgDepartmentMstByFmDept = orgDepartmentMstByFmDept;
	}

	public CmnLookupMst getCmnLookupMstByDiscipline() {
		return cmnLookupMstByDiscipline;
	}

	public void setCmnLookupMstByDiscipline(CmnLookupMst cmnLookupMstByDiscipline) {
		this.cmnLookupMstByDiscipline = cmnLookupMstByDiscipline;
	}

	public CmnLookupMst getCmnLookupMstBySubQualification() {
		return cmnLookupMstBySubQualification;
	}

	public void setCmnLookupMstBySubQualification(
			CmnLookupMst cmnLookupMstBySubQualification) {
		this.cmnLookupMstBySubQualification = cmnLookupMstBySubQualification;
	}
}