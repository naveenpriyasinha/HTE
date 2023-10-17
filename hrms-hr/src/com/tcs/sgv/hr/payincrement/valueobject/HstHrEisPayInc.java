package com.tcs.sgv.hr.payincrement.valueobject;

// default package
// Generated Oct 9, 2007 12:45:08 PM by Hibernate Tools 3.2.0.beta8


import java.util.Date;

/**
 * HstHrEisPayInc generated by hbm2java
 */
public class HstHrEisPayInc implements java.io.Serializable {

	// Fields    

	private HstHrEisPayIncId id;
	private Long userId;
	private Date nextIncDt;

	private Date lastIncDt;

	private Long dbId;

	private Long locId;

	private Long createdBy;

	private Long createdByPost;

	private Date createdDate;

	private Long updatedBy;

	private Long updatedByPost;

	private Date updatedDate;

	private Long basicSal;

	private String deffdFlag;

	private Long lwp;

	private Long incAmt;

	private String presentPayScale;

	private String remarks;



	// Constructors

	/** default constructor */
	public HstHrEisPayInc() {
	}

	/** minimal constructor */
	public HstHrEisPayInc(HstHrEisPayIncId id) {
		this.id = id;
	}

	/** full constructor */
	public HstHrEisPayInc(HstHrEisPayIncId id, Date nextIncDt, Date lastIncDt,
			Long dbId, Long locId, Long createdBy,Long userId,
			Long createdByPost, Date createdDate, Long updatedBy,
			Long updatedByPost, Date updatedDate, Long basicSal,
			String deffdFlag, Long lwp, Long incAmt,
			String presentPayScale, String remarks ) {
		this.id = id;
		this.nextIncDt = nextIncDt;
		this.lastIncDt = lastIncDt;
		this.dbId = dbId;
		this.locId = locId;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedByPost = updatedByPost;
		this.updatedDate = updatedDate;
		this.basicSal = basicSal;
		this.deffdFlag = deffdFlag;
		this.lwp = lwp;
		this.incAmt = incAmt;
		this.presentPayScale = presentPayScale;
		this.remarks = remarks;
		this.userId=userId;
	}

	// Property accessors
	public HstHrEisPayIncId getId() {
		return this.id;
	}

	public void setId(HstHrEisPayIncId id) {
		this.id = id;
	}

	public Date getNextIncDt() {
		return this.nextIncDt;
	}

	public void setNextIncDt(Date nextIncDt) {
		this.nextIncDt = nextIncDt;
	}

	public Date getLastIncDt() {
		return this.lastIncDt;
	}

	public void setLastIncDt(Date lastIncDt) {
		this.lastIncDt = lastIncDt;
	}

	public Long getDbId() {
		return this.dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public Long getLocId() {
		return this.locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(Long createdByPost) {
		this.createdByPost = createdByPost;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedByPost() {
		return this.updatedByPost;
	}

	public void setUpdatedByPost(Long updatedByPost) {
		this.updatedByPost = updatedByPost;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getBasicSal() {
		return this.basicSal;
	}

	public void setBasicSal(Long basicSal) {
		this.basicSal = basicSal;
	}

	public String getDeffdFlag() {
		return this.deffdFlag;
	}

	public void setDeffdFlag(String deffdFlag) {
		this.deffdFlag = deffdFlag;
	}

	public Long getLwp() {
		return this.lwp;
	}

	public void setLwp(Long lwp) {
		this.lwp = lwp;
	}

	public Long getIncAmt() {
		return this.incAmt;
	}

	public void setIncAmt(Long incAmt) {
		this.incAmt = incAmt;
	}

	public String getPresentPayScale() {
		return this.presentPayScale;
	}

	public void setPresentPayScale(String presentPayScale) {
		this.presentPayScale = presentPayScale;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

}