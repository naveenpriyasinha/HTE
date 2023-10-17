package com.tcs.sgv.deduction.valueobject;
// Generated Jul 27, 2007 3:29:32 PM by Hibernate Tools 3.1.0.beta5

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrPayDeducTypeMst generated by hbm2java
 */
public class HrPayDeducTypeMst implements java.io.Serializable {

	// Fields    

	private long deducCode;    
	private String deducName;
	private String deducDisplayName;
	private String deducDesc;
	private CmnLanguageMst cmnLanguageMst;
	private CmnDatabaseMst cmnDatabaseMst;
	private OrgPostMst orgPostMstByUpdatedByPost;
	private OrgPostMst orgPostMstByCreatedByPost;
	private CmnLocationMst cmnLocationMst;
	private OrgUserMst orgUserMstByUpdatedBy;
	private OrgUserMst orgUserMstByCreatedBy;
	private Date createdDate;	
	private Date updatedDate;	
	private Long deducType;
	private Long deducTypeCode;
	private Set hrDeducExpMsts = new HashSet(0);   
	private long sequence_no;
	private long payCommissionId;
	private CmnLookupMst deductionBy;
	private long allwDedId;
	// Constructors

	public long getAllwDedId()
	{
		return allwDedId;
	}

	public void setAllwDedId(long allwDedId)
	{
		this.allwDedId = allwDedId;
	}

	/** default constructor */
	public HrPayDeducTypeMst() {
	}

	/** minimal constructor */
	public HrPayDeducTypeMst(long deducCode) {
		this.deducCode = deducCode;
	}

	/** full constructor */
	public HrPayDeducTypeMst(long deducCode, String deducName,
			String deducDesc, long langId, long dbId,
			CmnLanguageMst cmnLanguageMst,CmnDatabaseMst cmnDatabaseMst ,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByCreatedBy, OrgPostMst orgPostMstByCreatedByPost,
			Date createdDate,long deducType,Long deducTypeCode, Set hrDeducExpMsts) {
		this.deducCode = deducCode;
		this.deducName = deducName;
		this.deducDesc = deducDesc;
		this.cmnLanguageMst = cmnLanguageMst;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.createdDate = createdDate;
		this.deducType=deducType;
		this.hrDeducExpMsts = hrDeducExpMsts;
		this.deducTypeCode=deducTypeCode;
	}

	

	public CmnDatabaseMst getCmnDatabaseMst() {
		return cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public CmnLanguageMst getCmnLanguageMst() {
		return cmnLanguageMst;
	}

	public void setCmnLanguageMst(CmnLanguageMst cmnLanguageMst) {
		this.cmnLanguageMst = cmnLanguageMst;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getDeducCode() {
		return deducCode;
	}

	public void setDeducCode(long deducCode) {
		this.deducCode = deducCode;
	}

	public String getDeducDesc() {
		return deducDesc;
	}

	public void setDeducDesc(String deducDesc) {
		this.deducDesc = deducDesc;
	}

	public String getDeducName() {
		return deducName;
	}

	public void setDeducName(String deducName) {
		this.deducName = deducName;
	}

	public Set getHrDeducExpMsts() {
		return hrDeducExpMsts;
	}

	public void setHrDeducExpMsts(Set hrDeducExpMsts) {
		this.hrDeducExpMsts = hrDeducExpMsts;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getDeducType() {
		return deducType;
	}

	public void setDeducType(Long deducType) {
		this.deducType = deducType;
	}
	// Property accessors

	public String getDeducDisplayName() {
		return deducDisplayName;
	}

	public void setDeducDisplayName(String deducDisplayName) {
		this.deducDisplayName = deducDisplayName;
	}

	public Long getDeducTypeCode() {
		return deducTypeCode;
	}

	public void setDeducTypeCode(Long deducTypeCode) {
		this.deducTypeCode = deducTypeCode;
	}

	public long getSequence_no() {
		return sequence_no;
	}

	public void setSequence_no(long sequence_no) {
		this.sequence_no = sequence_no;
	}

	/**
	 * @return the payCommissionId
	 */
	public long getPayCommissionId() {
		return payCommissionId;
	}

	/**
	 * @param payCommissionId the payCommissionId to set
	 */
	public void setPayCommissionId(long payCommissionId) {
		this.payCommissionId = payCommissionId;
	}

	public CmnLookupMst getDeductionBy() {
		return deductionBy;
	}

	public void setDeductionBy(CmnLookupMst deductionBy) {
		this.deductionBy = deductionBy;
	}
	
	
	
}