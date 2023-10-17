package com.tcs.sgv.lcm.valueobject;

// Generated Oct 12, 2007 12:32:14 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnLcDistribution generated by hbm2java
 */
public class TrnLcDistribution implements java.io.Serializable {

	// Fields    

	private BigDecimal lcOrderId;

	private BigDecimal lineCntr;

	private String lcOrderNo;

	private String divisionCode;

	private Date lcIssueDt;

	private String entryTypeCode;

	private Date lcValidFrm;

	private Date lcValidTo;

	private Date inwardDt;

	private BigDecimal inwardNo;

	private String partyRefNo;

	private Date partyRefDt;

	private String grntOrdNo;

	private Date grntOrdDt;

	private BigDecimal lcAmt;

	private BigDecimal lcAvailableAmt;

	private Character active;

	private Byte ltstCntr;

	private Long finYearId;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private String locCode;

	private BigDecimal dbId;

	private BigDecimal trnCntr;

	// Constructors

	/** default constructor */
	public TrnLcDistribution() {
	}

	/** minimal constructor */
	public TrnLcDistribution(BigDecimal lcOrderId) {
		this.lcOrderId = lcOrderId;
	}

	/** full constructor */
	public TrnLcDistribution(BigDecimal lcOrderId, BigDecimal lineCntr,
			String lcOrderNo, String divisionCode, Date lcIssueDt,
			String entryTypeCode, Date lcValidFrm, Date lcValidTo,
			Date inwardDt, BigDecimal inwardNo, String partyRefNo,
			Date partyRefDt, String grntOrdNo, Date grntOrdDt,
			BigDecimal lcAmt, BigDecimal lcAvailableAmt, Character active,
			Byte ltstCntr, Long finYearId, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId,
			Date updatedDate, String locCode, BigDecimal dbId,
			BigDecimal trnCntr) {
		this.lcOrderId = lcOrderId;
		this.lineCntr = lineCntr;
		this.lcOrderNo = lcOrderNo;
		this.divisionCode = divisionCode;
		this.lcIssueDt = lcIssueDt;
		this.entryTypeCode = entryTypeCode;
		this.lcValidFrm = lcValidFrm;
		this.lcValidTo = lcValidTo;
		this.inwardDt = inwardDt;
		this.inwardNo = inwardNo;
		this.partyRefNo = partyRefNo;
		this.partyRefDt = partyRefDt;
		this.grntOrdNo = grntOrdNo;
		this.grntOrdDt = grntOrdDt;
		this.lcAmt = lcAmt;
		this.lcAvailableAmt = lcAvailableAmt;
		this.active = active;
		this.ltstCntr = ltstCntr;
		this.finYearId = finYearId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.locCode = locCode;
		this.dbId = dbId;
		this.trnCntr = trnCntr;
	}

	// Property accessors
	public BigDecimal getLcOrderId() {
		return this.lcOrderId;
	}

	public void setLcOrderId(BigDecimal lcOrderId) {
		this.lcOrderId = lcOrderId;
	}

	public BigDecimal getLineCntr() {
		return this.lineCntr;
	}

	public void setLineCntr(BigDecimal lineCntr) {
		this.lineCntr = lineCntr;
	}

	public String getLcOrderNo() {
		return this.lcOrderNo;
	}

	public void setLcOrderNo(String lcOrderNo) {
		this.lcOrderNo = lcOrderNo;
	}

	public String getDivisionCode() {
		return this.divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public Date getLcIssueDt() {
		return this.lcIssueDt;
	}

	public void setLcIssueDt(Date lcIssueDt) {
		this.lcIssueDt = lcIssueDt;
	}

	public String getEntryTypeCode() {
		return this.entryTypeCode;
	}

	public void setEntryTypeCode(String entryTypeCode) {
		this.entryTypeCode = entryTypeCode;
	}

	public Date getLcValidFrm() {
		return this.lcValidFrm;
	}

	public void setLcValidFrm(Date lcValidFrm) {
		this.lcValidFrm = lcValidFrm;
	}

	public Date getLcValidTo() {
		return this.lcValidTo;
	}

	public void setLcValidTo(Date lcValidTo) {
		this.lcValidTo = lcValidTo;
	}

	public Date getInwardDt() {
		return this.inwardDt;
	}

	public void setInwardDt(Date inwardDt) {
		this.inwardDt = inwardDt;
	}

	public BigDecimal getInwardNo() {
		return this.inwardNo;
	}

	public void setInwardNo(BigDecimal inwardNo) {
		this.inwardNo = inwardNo;
	}

	public String getPartyRefNo() {
		return this.partyRefNo;
	}

	public void setPartyRefNo(String partyRefNo) {
		this.partyRefNo = partyRefNo;
	}

	public Date getPartyRefDt() {
		return this.partyRefDt;
	}

	public void setPartyRefDt(Date partyRefDt) {
		this.partyRefDt = partyRefDt;
	}

	public String getGrntOrdNo() {
		return this.grntOrdNo;
	}

	public void setGrntOrdNo(String grntOrdNo) {
		this.grntOrdNo = grntOrdNo;
	}

	public Date getGrntOrdDt() {
		return this.grntOrdDt;
	}

	public void setGrntOrdDt(Date grntOrdDt) {
		this.grntOrdDt = grntOrdDt;
	}

	public BigDecimal getLcAmt() {
		return this.lcAmt;
	}

	public void setLcAmt(BigDecimal lcAmt) {
		this.lcAmt = lcAmt;
	}

	public BigDecimal getLcAvailableAmt() {
		return this.lcAvailableAmt;
	}

	public void setLcAvailableAmt(BigDecimal lcAvailableAmt) {
		this.lcAvailableAmt = lcAvailableAmt;
	}

	public Character getActive() {
		return this.active;
	}

	public void setActive(Character active) {
		this.active = active;
	}

	public Byte getLtstCntr() {
		return this.ltstCntr;
	}

	public void setLtstCntr(Byte ltstCntr) {
		this.ltstCntr = ltstCntr;
	}

	public Long getFinYearId() {
		return this.finYearId;
	}

	public void setFinYearId(Long finYearId) {
		this.finYearId = finYearId;
	}

	public BigDecimal getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(BigDecimal createdUserId) {
		this.createdUserId = createdUserId;
	}

	public BigDecimal getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(BigDecimal createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(BigDecimal updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public BigDecimal getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(BigDecimal updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getLocCode() {
		return this.locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public BigDecimal getDbId() {
		return this.dbId;
	}

	public void setDbId(BigDecimal dbId) {
		this.dbId = dbId;
	}

	public BigDecimal getTrnCntr() {
		return this.trnCntr;
	}

	public void setTrnCntr(BigDecimal trnCntr) {
		this.trnCntr = trnCntr;
	}

}