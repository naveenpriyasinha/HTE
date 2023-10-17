package com.tcs.sgv.common.valueobject;

// Generated May 30, 2007 5:12:32 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * SgvcFinYearMst generated by hbm2java
 */
public class SgvcFinYearMst implements java.io.Serializable {

	// Fields    

	private long finYearId;

	private Date fromDate;

	private Date toDate;

	private String langId;

	private String locId;

	private Date lstUpdDt;

	private String lstUpdUsr;

	private Date crtDt;

	private String crtUsr;

	private String finYearDesc;

	private String finYearType;

	private String finYearCode;

	private Long prevFinYearId;

	private Long nextFinYearId;

	// Constructors

	/** default constructor */
	public SgvcFinYearMst() {
	}

	/** minimal constructor */
	public SgvcFinYearMst(long finYearId, Date fromDate, Date toDate,
			String langId, String locId, Date lstUpdDt, String lstUpdUsr,
			Date crtDt, String crtUsr, String finYearDesc, String finYearCode) {
		this.finYearId = finYearId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.langId = langId;
		this.locId = locId;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.finYearDesc = finYearDesc;
		this.finYearCode = finYearCode;
	}

	/** full constructor */
	public SgvcFinYearMst(long finYearId, Date fromDate, Date toDate,
			String langId, String locId, Date lstUpdDt, String lstUpdUsr,
			Date crtDt, String crtUsr, String finYearDesc, String finYearType,
			String finYearCode, Long prevFinYearId, Long nextFinYearId) {
		this.finYearId = finYearId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.langId = langId;
		this.locId = locId;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.finYearDesc = finYearDesc;
		this.finYearType = finYearType;
		this.finYearCode = finYearCode;
		this.prevFinYearId = prevFinYearId;
		this.nextFinYearId = nextFinYearId;
	}

	// Property accessors
	public long getFinYearId() {
		return this.finYearId;
	}

	public void setFinYearId(long finYearId) {
		this.finYearId = finYearId;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getLangId() {
		return this.langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getLocId() {
		return this.locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getFinYearDesc() {
		return this.finYearDesc;
	}

	public void setFinYearDesc(String finYearDesc) {
		this.finYearDesc = finYearDesc;
	}

	public String getFinYearType() {
		return this.finYearType;
	}

	public void setFinYearType(String finYearType) {
		this.finYearType = finYearType;
	}

	public String getFinYearCode() {
		return this.finYearCode;
	}

	public void setFinYearCode(String finYearCode) {
		this.finYearCode = finYearCode;
	}

	public Long getPrevFinYearId() {
		return this.prevFinYearId;
	}

	public void setPrevFinYearId(Long prevFinYearId) {
		this.prevFinYearId = prevFinYearId;
	}

	public Long getNextFinYearId() {
		return this.nextFinYearId;
	}

	public void setNextFinYearId(Long nextFinYearId) {
		this.nextFinYearId = nextFinYearId;
	}

}