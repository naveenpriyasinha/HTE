package com.tcs.sgv.common.valueobject;

// Generated May 29, 2007 11:25:21 AM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * SgvaBudrlsOrderDtls generated by hbm2java
 */
public class SgvaBudrlsOrderDtls implements java.io.Serializable {

	// Fields    

	private long rlsOrderDtlsId;

	private long fkMonthMpgId;

	private String divisionId;

	private BigDecimal finalStateGovtAmt;

	private BigDecimal finalCentralGovtAmt;

	private String approvedRejected;

	private String remarks;

	private String langId;

	private String locId;

	private Date crtDt;

	private String crtUsr;

	private String lstUpdUsr;

	private Date lstUpdDt;

	private String applicationId;

	// Constructors

	/** default constructor */
	public SgvaBudrlsOrderDtls() {
	}

	/** minimal constructor */
	public SgvaBudrlsOrderDtls(long rlsOrderDtlsId, long fkMonthMpgId,
			String divisionId, String approvedRejected, String langId,
			String locId, Date crtDt, String crtUsr, String applicationId) {
		this.rlsOrderDtlsId = rlsOrderDtlsId;
		this.fkMonthMpgId = fkMonthMpgId;
		this.divisionId = divisionId;
		this.approvedRejected = approvedRejected;
		this.langId = langId;
		this.locId = locId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.applicationId = applicationId;
	}

	/** full constructor */
	public SgvaBudrlsOrderDtls(long rlsOrderDtlsId, long fkMonthMpgId,
			String divisionId, BigDecimal finalStateGovtAmt,
			BigDecimal finalCentralGovtAmt, String approvedRejected,
			String remarks, String langId, String locId, Date crtDt,
			String crtUsr, String lstUpdUsr, Date lstUpdDt, String applicationId) {
		this.rlsOrderDtlsId = rlsOrderDtlsId;
		this.fkMonthMpgId = fkMonthMpgId;
		this.divisionId = divisionId;
		this.finalStateGovtAmt = finalStateGovtAmt;
		this.finalCentralGovtAmt = finalCentralGovtAmt;
		this.approvedRejected = approvedRejected;
		this.remarks = remarks;
		this.langId = langId;
		this.locId = locId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
		this.applicationId = applicationId;
	}

	// Property accessors
	public long getRlsOrderDtlsId() {
		return this.rlsOrderDtlsId;
	}

	public void setRlsOrderDtlsId(long rlsOrderDtlsId) {
		this.rlsOrderDtlsId = rlsOrderDtlsId;
	}

	public long getFkMonthMpgId() {
		return this.fkMonthMpgId;
	}

	public void setFkMonthMpgId(long fkMonthMpgId) {
		this.fkMonthMpgId = fkMonthMpgId;
	}

	public String getDivisionId() {
		return this.divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public BigDecimal getFinalStateGovtAmt() {
		return this.finalStateGovtAmt;
	}

	public void setFinalStateGovtAmt(BigDecimal finalStateGovtAmt) {
		this.finalStateGovtAmt = finalStateGovtAmt;
	}

	public BigDecimal getFinalCentralGovtAmt() {
		return this.finalCentralGovtAmt;
	}

	public void setFinalCentralGovtAmt(BigDecimal finalCentralGovtAmt) {
		this.finalCentralGovtAmt = finalCentralGovtAmt;
	}

	public String getApprovedRejected() {
		return this.approvedRejected;
	}

	public void setApprovedRejected(String approvedRejected) {
		this.approvedRejected = approvedRejected;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

}
