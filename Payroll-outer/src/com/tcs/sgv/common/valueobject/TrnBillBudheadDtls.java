package com.tcs.sgv.common.valueobject;

// Generated Jun 20, 2007 2:53:44 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * TrnBillBudheadDtls generated by hbm2java
 */
public class TrnBillBudheadDtls implements java.io.Serializable {

	// Fields    

	private long billBudId;

	private long billNo;

	private String drawing;

	private String fund;

	private String dmndNo;

	private String bpnNo;

	private String budMjrHd;

	private String budSubmjrHd;

	private String budMinHd;

	private String budSubHd;

	private String budDtlHd;

	private Long versionId;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private String budType;

	private String clsExp;

	private String schemeNo;

	private String headChrg;

	private String finYearId;

	private Integer trnCounter;
	
	private String locationCode;

	// Constructors

	/** default constructor */
	public TrnBillBudheadDtls() {
	}

	/** minimal constructor */
	public TrnBillBudheadDtls(long billBudId, long billNo, String locationCode,
			Long createdUserId,	Long createdPostId, Date createdDate) {
		this.billBudId = billBudId;
		this.billNo = billNo;		
		this.locationCode = locationCode;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public TrnBillBudheadDtls(long billBudId, long billNo, String drawing,
			String fund, String dmndNo, String bpnNo, String budMjrHd,
			String budSubmjrHd, String budMinHd, String budSubHd,
			String budDtlHd, Long versionId, Long createdUserId,
			Long createdPostId, Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, Long dbId,
			String budType, String clsExp, String schemeNo, String headChrg,
			String finYearId, Integer trnCounter, String locationCode) {
		this.billBudId = billBudId;
		this.billNo = billNo;
		this.drawing = drawing;
		this.fund = fund;
		this.dmndNo = dmndNo;
		this.bpnNo = bpnNo;
		this.budMjrHd = budMjrHd;
		this.budSubmjrHd = budSubmjrHd;
		this.budMinHd = budMinHd;
		this.budSubHd = budSubHd;
		this.budDtlHd = budDtlHd;
		this.versionId = versionId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.budType = budType;
		this.clsExp = clsExp;
		this.schemeNo = schemeNo;
		this.headChrg = headChrg;
		this.finYearId = finYearId;
		this.trnCounter = trnCounter;
		this.locationCode = locationCode;
	}

	// Property accessors
	public long getBillBudId() {
		return this.billBudId;
	}

	public void setBillBudId(long billBudId) {
		this.billBudId = billBudId;
	}

	public long getBillNo() {
		return this.billNo;
	}

	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}

	public String getDrawing() {
		return this.drawing;
	}

	public void setDrawing(String drawing) {
		this.drawing = drawing;
	}

	public String getFund() {
		return this.fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getDmndNo() {
		return this.dmndNo;
	}

	public void setDmndNo(String dmndNo) {
		this.dmndNo = dmndNo;
	}

	public String getBpnNo() {
		return this.bpnNo;
	}

	public void setBpnNo(String bpnNo) {
		this.bpnNo = bpnNo;
	}

	public String getBudMjrHd() {
		return this.budMjrHd;
	}

	public void setBudMjrHd(String budMjrHd) {
		this.budMjrHd = budMjrHd;
	}

	public String getBudSubmjrHd() {
		return this.budSubmjrHd;
	}

	public void setBudSubmjrHd(String budSubmjrHd) {
		this.budSubmjrHd = budSubmjrHd;
	}

	public String getBudMinHd() {
		return this.budMinHd;
	}

	public void setBudMinHd(String budMinHd) {
		this.budMinHd = budMinHd;
	}

	public String getBudSubHd() {
		return this.budSubHd;
	}

	public void setBudSubHd(String budSubHd) {
		this.budSubHd = budSubHd;
	}

	public String getBudDtlHd() {
		return this.budDtlHd;
	}

	public void setBudDtlHd(String budDtlHd) {
		this.budDtlHd = budDtlHd;
	}

	public Long getVersionId() {
		return this.versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public Long getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Long getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(Long createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Long getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId) {
		this.updatedPostId = updatedPostId;
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

	public String getBudType() {
		return this.budType;
	}

	public void setBudType(String budType) {
		this.budType = budType;
	}

	public String getClsExp() {
		return this.clsExp;
	}

	public void setClsExp(String clsExp) {
		this.clsExp = clsExp;
	}

	public String getSchemeNo() {
		return this.schemeNo;
	}

	public void setSchemeNo(String schemeNo) {
		this.schemeNo = schemeNo;
	}

	public String getHeadChrg() {
		return this.headChrg;
	}

	public void setHeadChrg(String headChrg) {
		this.headChrg = headChrg;
	}

	public String getFinYearId() {
		return this.finYearId;
	}

	public void setFinYearId(String finYearId) {
		this.finYearId = finYearId;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
}
