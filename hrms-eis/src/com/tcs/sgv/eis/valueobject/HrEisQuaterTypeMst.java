// default package
// Generated Jul 30, 2007 1:48:55 PM by Hibernate Tools 3.1.0.beta5
package com.tcs.sgv.eis.valueobject;
import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrEisQuaterTypeMst generated by hbm2java
 */
public class HrEisQuaterTypeMst implements java.io.Serializable {

	// Fields    

	private long quaId;
	private String quaType;
	private long quaRent;
	private CmnLanguageMst cmnLanguageMst;
	private CmnDatabaseMst cmnDatabaseMst;
	private OrgPostMst orgPostMstByUpdatedByPost;
	private OrgPostMst orgPostMstByCreatedByPost;
	private CmnLocationMst cmnLocationMst;
	private OrgUserMst orgUserMstByUpdatedBy;
	private OrgUserMst orgUserMstByCreatedBy;
	private Date createdDate;	
	private Date updatedDate;
	private String applicationType;
	private String qtrCode;
	
	//private Set hrEisOtherDtlss = new HashSet(0);

	// Constructors

	/** default constructor */
	public HrEisQuaterTypeMst() {
	}

	/** minimal constructor */
	public HrEisQuaterTypeMst(long quaId, String quaType,
			long quaRent) {
		this.quaId = quaId;
		this.quaType = quaType;
		this.quaRent = quaRent;
	}

	/** full constructor */
	public HrEisQuaterTypeMst(long quaId, String quaType,
			long quaRent, long dbId, CmnLanguageMst cmnLanguageMst,CmnDatabaseMst cmnDatabaseMst ,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByCreatedBy, OrgPostMst orgPostMstByCreatedByPost,
			Date createdDate, String applicationType,String qtrCode) {
		this.quaId = quaId;
		this.quaType = quaType;
		this.quaRent = quaRent;
		this.cmnLanguageMst = cmnLanguageMst;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;		
		this.createdDate = createdDate;
		this.applicationType=applicationType;
		this.qtrCode=qtrCode;
		//this.hrEisOtherDtlss = hrEisOtherDtlss;
	}

	// Property accessors
	public long getQuaId() {
		return this.quaId;
	}

	public void setQuaId(long quaId) {
		this.quaId = quaId;
	}

	public String getQuaType() {
		return this.quaType;
	}

	public void setQuaType(String quaType) {
		this.quaType = quaType;
	}

	public long getQuaRent() {
		return this.quaRent;
	}

	public void setQuaRent(long quaRent) {
		this.quaRent = quaRent;
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

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getQtrCode() {
		return qtrCode;
	}

	public void setQtrCode(String qtrCode) {
		this.qtrCode = qtrCode;
	}

	/*public Set getHrEisOtherDtlss() {
		return hrEisOtherDtlss;
	}

	public void setHrEisOtherDtlss(Set hrEisOtherDtlss) {
		this.hrEisOtherDtlss = hrEisOtherDtlss;
	}*/

	
}