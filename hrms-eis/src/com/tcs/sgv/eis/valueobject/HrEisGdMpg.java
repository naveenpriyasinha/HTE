package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
/**
 * HrEisGdMpg generated by hbm2java
 */
public class HrEisGdMpg implements java.io.Serializable {

	// Fields    

	private long gdMapId;

	//private long sgdGradeId;

	//private long sgdDesigId;
	OrgGradeMst orgGradeMst;
	
	OrgDesignationMst orgDesignationMst;
	
	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;

	private Date updatedDate;

	private long elementCode;

	// Constructors

	/** default constructor */
	public HrEisGdMpg() {
	}

	/** minimal constructor */
	public HrEisGdMpg(long gdMapId,
			OrgGradeMst orgGradeMst,	
			OrgDesignationMst orgDesignationMst,
			CmnDatabaseMst cmnDatabaseMst,
			OrgPostMst orgPostMstByCreatedByPost,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByCreatedBy
			, Date createdDate,
			long createdByPost) {
		this.gdMapId = gdMapId;
		this.orgGradeMst = orgGradeMst;
		this.orgDesignationMst = orgDesignationMst;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public HrEisGdMpg(long gdMapId,
			OrgGradeMst orgGradeMst,	
			OrgDesignationMst orgDesignationMst,
			CmnDatabaseMst cmnDatabaseMst,
			OrgPostMst orgPostMstByUpdatedByPost,
			OrgPostMst orgPostMstByCreatedByPost,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByUpdatedBy,
			OrgUserMst orgUserMstByCreatedBy,  Date createdDate,
			long updatedBy, Date updatedDate,
			long elementCode) {
		this.gdMapId = gdMapId;
		this.orgGradeMst = orgGradeMst;
		this.orgDesignationMst = orgDesignationMst;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.elementCode = elementCode;
	}

	// Property accessors
	public long getGdMapId() {
		return this.gdMapId;
	}

	public void setGdMapId(long gdMapId) {
		this.gdMapId = gdMapId;
	}

/*	public long getSgdGradeId() {
		return this.sgdGradeId;
	}

	public void setSgdGradeId(long sgdGradeId) {
		this.sgdGradeId = sgdGradeId;
	}

	public long getSgdDesigId() {
		return this.sgdDesigId;
	}

	public void setSgdDesigId(long sgdDesigId) {
		this.sgdDesigId = sgdDesigId;
	}*/
	
	
	public OrgGradeMst getOrgGradeMst() {
		return this.orgGradeMst;
	}
	
	public void setOrgGradeMst(OrgGradeMst orgGradeMst) {
		this.orgGradeMst = orgGradeMst;
	}
	
	public OrgDesignationMst getOrgDesignationMst() {
		return this.orgDesignationMst;
	}
	
	public void setOrgDesignationMst(OrgDesignationMst orgDesignationMst) {
		this.orgDesignationMst = orgDesignationMst;
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


	public long getElementCode() {
		return this.elementCode;
	}

	public void setElementCode(long elementCode) {
		this.elementCode = elementCode;
	}

}