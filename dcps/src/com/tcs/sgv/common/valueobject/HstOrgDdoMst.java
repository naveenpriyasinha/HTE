package com.tcs.sgv.common.valueobject;

// Generated Mar 17, 2009 8:31:17 PM by Hibernate Tools 3.2.0.beta8

import java.io.Serializable;

import java.util.Date;

/**
 * HstOrgDdoMst generated by hbm2java
 */
public class HstOrgDdoMst implements java.io.Serializable {

	// Fields    

	private HstOrgDdoMstId id;

	private String ddoCode;

	private String ddoName;

	private Long postId;

	private Long attachmentId;

	private Long langId;

	private Date startDate;

	private Date endDate;

	private Long activateFlag;

	private Long createdBy;

	private Long createdByPost;

	private Date createdDate;

	private Long updatedBy;

	private Long updatedByPost;

	private Date updatedDate;

	private Long dbId;

	private String shortName;

	private String majorHead;

	private String demand;

	private Integer ddoNo;

	private Integer cardexNo;

	private boolean adminFlag;

	private String officeCode;

	private String locationCode;

	private String deptLocCode;

	private String hodLocCode;

	private boolean isCo;

	private boolean isCs;

	private Short type;

	// Constructors

	/** default constructor */
	public HstOrgDdoMst() {
	}

	/** minimal constructor */
	public HstOrgDdoMst(HstOrgDdoMstId id, String ddoCode,
			String ddoName, Long postId, Long langId,
			Date startDate, Long activateFlag, Long createdBy,
			Long createdByPost, Date createdDate, Long dbId,
			boolean adminFlag, boolean isCo, boolean isCs) {
		this.id = id;
		this.ddoCode = ddoCode;
		this.ddoName = ddoName;
		this.postId = postId;
		this.langId = langId;
		this.startDate = startDate;
		this.activateFlag = activateFlag;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.dbId = dbId;
		this.adminFlag = adminFlag;
		this.isCo = isCo;
		this.isCs = isCs;
	}

	/** full constructor */
	public HstOrgDdoMst(HstOrgDdoMstId id, String ddoCode,
			String ddoName, Long postId, Long attachmentId,
			Long langId, Date startDate, Date endDate,
			Long activateFlag, Long createdBy,
			Long createdByPost, Date createdDate, Long updatedBy,
			Long updatedByPost, Date updatedDate, Long dbId,
			String shortName, String majorHead, String demand,
			Integer ddoNo, Integer cardexNo, boolean adminFlag,
			String officeCode, String locationCode, String deptLocCode,
			String hodLocCode, boolean isCo, boolean isCs, Short type) {
		this.id = id;
		this.ddoCode = ddoCode;
		this.ddoName = ddoName;
		this.postId = postId;
		this.attachmentId = attachmentId;
		this.langId = langId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activateFlag = activateFlag;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedByPost = updatedByPost;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.shortName = shortName;
		this.majorHead = majorHead;
		this.demand = demand;
		this.ddoNo = ddoNo;
		this.cardexNo = cardexNo;
		this.adminFlag = adminFlag;
		this.officeCode = officeCode;
		this.locationCode = locationCode;
		this.deptLocCode = deptLocCode;
		this.hodLocCode = hodLocCode;
		this.isCo = isCo;
		this.isCs = isCs;
		this.type = type;
	}

	// Property accessors
	public HstOrgDdoMstId getId() {
		return this.id;
	}

	public void setId(HstOrgDdoMstId id) {
		this.id = id;
	}

	public String getDdoCode() {
		return this.ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public String getDdoName() {
		return this.ddoName;
	}

	public void setDdoName(String ddoName) {
		this.ddoName = ddoName;
	}

	public Long getPostId() {
		return this.postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Long getLangId() {
		return this.langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
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

	public Long getActivateFlag() {
		return this.activateFlag;
	}

	public void setActivateFlag(Long activateFlag) {
		this.activateFlag = activateFlag;
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

	public Long getDbId() {
		return this.dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getMajorHead() {
		return this.majorHead;
	}

	public void setMajorHead(String majorHead) {
		this.majorHead = majorHead;
	}

	public String getDemand() {
		return this.demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public Integer getDdoNo() {
		return this.ddoNo;
	}

	public void setDdoNo(Integer ddoNo) {
		this.ddoNo = ddoNo;
	}

	public Integer getCardexNo() {
		return this.cardexNo;
	}

	public void setCardexNo(Integer cardexNo) {
		this.cardexNo = cardexNo;
	}

	public boolean isAdminFlag() {
		return this.adminFlag;
	}

	public void setAdminFlag(boolean adminFlag) {
		this.adminFlag = adminFlag;
	}

	public String getOfficeCode() {
		return this.officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getDeptLocCode() {
		return this.deptLocCode;
	}

	public void setDeptLocCode(String deptLocCode) {
		this.deptLocCode = deptLocCode;
	}

	public String getHodLocCode() {
		return this.hodLocCode;
	}

	public void setHodLocCode(String hodLocCode) {
		this.hodLocCode = hodLocCode;
	}

	public boolean isIsCo() {
		return this.isCo;
	}

	public void setIsCo(boolean isCo) {
		this.isCo = isCo;
	}

	public boolean isIsCs() {
		return this.isCs;
	}

	public void setIsCs(boolean isCs) {
		this.isCs = isCs;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}
