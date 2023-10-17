package com.tcs.sgv.common.valueobject;
// default package
// Generated Jan 17, 2008 2:14:46 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrDeclarationFlagMst generated by hbm2java
 */
public class HrDeclarationFlagMst implements java.io.Serializable {

	// Fields    

	private long declarationId;

	private CmnLanguageMst cmnLanguageMst;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private String description;

	private String component;

	private long applicationId;

	private Long subApplicationTypeId;

	private Date createdDate;

	private Date updatedDate;

	private Long declarationCode;

	private String displayFlag;
	
	private Set hrDeclarationFlagDtlsDatas = new HashSet(0);

	private Set hrPensionFlagDtlses = new HashSet(0);
	
	// Constructors

	/** default constructor */
	public HrDeclarationFlagMst() {
	}

	/** minimal constructor */
	public HrDeclarationFlagMst(long declarationId,
			OrgPostMst orgPostMstByCreatedByPost,
			OrgUserMst orgUserMstByCreatedBy, long applicationId,
			Date createdDate) {
		this.declarationId = declarationId;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.applicationId = applicationId;
		this.createdDate = createdDate;		
	}

	/** full constructor */
	public HrDeclarationFlagMst(long declarationId,
			CmnLanguageMst cmnLanguageMst, CmnDatabaseMst cmnDatabaseMst,
			OrgPostMst orgPostMstByUpdatedByPost,
			OrgPostMst orgPostMstByCreatedByPost,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByUpdatedBy,
			OrgUserMst orgUserMstByCreatedBy, String description,
			String component, long applicationId, Long subApplicationTypeId,
			Date createdDate, Date updatedDate, Long declarationCode,String displayFlag,
			Set hrDeclarationFlagDtlsDatas, Set hrPensionFlagDtlses) {
		this.declarationId = declarationId;
		this.cmnLanguageMst = cmnLanguageMst;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.description = description;
		this.component = component;
		this.applicationId = applicationId;
		this.subApplicationTypeId = subApplicationTypeId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.declarationCode = declarationCode;
		this.hrDeclarationFlagDtlsDatas = hrDeclarationFlagDtlsDatas;
		this.hrPensionFlagDtlses = hrPensionFlagDtlses;
		this.displayFlag = displayFlag;
	}

	// Property accessors
	public long getDeclarationId() {
		return this.declarationId;
	}

	public void setDeclarationId(long declarationId) {
		this.declarationId = declarationId;
	}

	public CmnLanguageMst getCmnLanguageMst() {
		return this.cmnLanguageMst;
	}

	public void setCmnLanguageMst(CmnLanguageMst cmnLanguageMst) {
		this.cmnLanguageMst = cmnLanguageMst;
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

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return this.orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getSubApplicationTypeId() {
		return this.subApplicationTypeId;
	}

	public void setSubApplicationTypeId(Long subApplicationTypeId) {
		this.subApplicationTypeId = subApplicationTypeId;
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

	public Long getDeclarationCode() {
		return this.declarationCode;
	}

	public void setDeclarationCode(Long declarationCode) {
		this.declarationCode = declarationCode;
	}

	public Set getHrDeclarationFlagDtlsDatas() {
		return this.hrDeclarationFlagDtlsDatas;
	}

	public void setHrDeclarationFlagDtlsDatas(Set hrDeclarationFlagDtlsDatas) {
		this.hrDeclarationFlagDtlsDatas = hrDeclarationFlagDtlsDatas;
	}

	public Set getHrPensionFlagDtlses() {
		return this.hrPensionFlagDtlses;
	}

	public void setHrPensionFlagDtlses(Set hrPensionFlagDtlses) {
		this.hrPensionFlagDtlses = hrPensionFlagDtlses;
	}

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

}