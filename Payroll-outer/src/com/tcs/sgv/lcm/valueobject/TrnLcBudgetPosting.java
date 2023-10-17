 package com.tcs.sgv.lcm.valueobject;
// Generated Aug 21, 2007 4:37:31 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnLcBudgetPosting generated by hbm2java
 */
public class TrnLcBudgetPosting implements java.io.Serializable {

	// Fields    

	private BigDecimal lcBudId;

	private BigDecimal lcExpId;

	private String lcOrderNo;

	private String classOfExp;

	private String fund;

	private String drwOff;

	private String demandNo;

	private String budgetType;

	private Integer schemeNo;

	private String mjrHd;

	private String subMjrHd;

	private String minHd;

	private String subHd;

	private String dtlHd;

	private String objHd;

	private BigDecimal expAmt;

	private Byte ltstCntr;

	private Character active;

	private Long finYearId;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private String locCode;

	private BigDecimal dbId;

	// Constructors

	/** default constructor */
	public TrnLcBudgetPosting() {
	}

	/** minimal constructor */
	public TrnLcBudgetPosting(BigDecimal lcBudId) {
		this.lcBudId = lcBudId;
	}

	/** full constructor */
	public TrnLcBudgetPosting(BigDecimal lcBudId, BigDecimal lcExpId,
			String lcOrderNo, String classOfExp, String fund, String drwOff,
			String demandNo, String budgetType, Integer schemeNo, String mjrHd,
			String subMjrHd, String minHd, String subHd, String dtlHd,
			String objHd, BigDecimal expAmt, Byte ltstCntr, Character active,
			Long finYearId, BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, String locCode,
			BigDecimal dbId) {
		this.lcBudId = lcBudId;
		this.lcExpId = lcExpId;
		this.lcOrderNo = lcOrderNo;
		this.classOfExp = classOfExp;
		this.fund = fund;
		this.drwOff = drwOff;
		this.demandNo = demandNo;
		this.budgetType = budgetType;
		this.schemeNo = schemeNo;
		this.mjrHd = mjrHd;
		this.subMjrHd = subMjrHd;
		this.minHd = minHd;
		this.subHd = subHd;
		this.dtlHd = dtlHd;
		this.objHd = objHd;
		this.expAmt = expAmt;
		this.ltstCntr = ltstCntr;
		this.active = active;
		this.finYearId = finYearId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.locCode = locCode;
		this.dbId = dbId;
	}

	// Property accessors
	public BigDecimal getLcBudId() {
		return this.lcBudId;
	}

	public void setLcBudId(BigDecimal lcBudId) {
		this.lcBudId = lcBudId;
	}

	public BigDecimal getLcExpId() {
		return this.lcExpId;
	}

	public void setLcExpId(BigDecimal lcExpId) {
		this.lcExpId = lcExpId;
	}

	public String getLcOrderNo() {
		return this.lcOrderNo;
	}

	public void setLcOrderNo(String lcOrderNo) {
		this.lcOrderNo = lcOrderNo;
	}

	public String getClassOfExp() {
		return this.classOfExp;
	}

	public void setClassOfExp(String classOfExp) {
		this.classOfExp = classOfExp;
	}

	public String getFund() {
		return this.fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getDrwOff() {
		return this.drwOff;
	}

	public void setDrwOff(String drwOff) {
		this.drwOff = drwOff;
	}

	public String getDemandNo() {
		return this.demandNo;
	}

	public void setDemandNo(String demandNo) {
		this.demandNo = demandNo;
	}

	public String getBudgetType() {
		return this.budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public Integer getSchemeNo() {
		return this.schemeNo;
	}

	public void setSchemeNo(Integer schemeNo) {
		this.schemeNo = schemeNo;
	}

	public String getMjrHd() {
		return this.mjrHd;
	}

	public void setMjrHd(String mjrHd) {
		this.mjrHd = mjrHd;
	}

	public String getSubMjrHd() {
		return this.subMjrHd;
	}

	public void setSubMjrHd(String subMjrHd) {
		this.subMjrHd = subMjrHd;
	}

	public String getMinHd() {
		return this.minHd;
	}

	public void setMinHd(String minHd) {
		this.minHd = minHd;
	}

	public String getSubHd() {
		return this.subHd;
	}

	public void setSubHd(String subHd) {
		this.subHd = subHd;
	}

	public String getDtlHd() {
		return this.dtlHd;
	}

	public void setDtlHd(String dtlHd) {
		this.dtlHd = dtlHd;
	}

	public String getObjHd() {
		return this.objHd;
	}

	public void setObjHd(String objHd) {
		this.objHd = objHd;
	}

	public BigDecimal getExpAmt() {
		return this.expAmt;
	}

	public void setExpAmt(BigDecimal expAmt) {
		this.expAmt = expAmt;
	}

	public Byte getLtstCntr() {
		return this.ltstCntr;
	}

	public void setLtstCntr(Byte ltstCntr) {
		this.ltstCntr = ltstCntr;
	}

	public Character getActive() {
		return this.active;
	}

	public void setActive(Character active) {
		this.active = active;
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

}