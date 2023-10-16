package com.tcs.sgv.onlinebillprep.valueobject;
// default package
// Generated Sep 7, 2007 2:38:36 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnTablAmtDtls generated by hbm2java
 */
public class TrnTablAmtDtls implements java.io.Serializable {

	// Fields    

	private Long trnTablAmtDtlsId;

	private Long trnTablHdrId;

	private BigDecimal rlwStmFare;

	private BigDecimal rdTrlFare;

	private BigDecimal ttlDaClmed;

	private BigDecimal incdntlExp;

	private BigDecimal grossTotal;

	private BigDecimal dedTaAdv;

	private Long dedPtaDays;

	private BigDecimal dedPta;

	private BigDecimal netClaimed;

	private BigDecimal snctndAmt;

	private Date billPassedDate;

	private BigDecimal billPassedAmt;

	private Long attachId;

	private String chqOrder;

	private String remarks;

	private Integer trnCounter;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private BigDecimal rdChrgsPsperkm;

	private String locationCode;

	// Constructors

	/** default constructor */
	public TrnTablAmtDtls() {
	}

	/** minimal constructor */
	public TrnTablAmtDtls(Long trnTablAmtDtlsId, Long trnTablHdrId,
			BigDecimal grossTotal, BigDecimal netClaimed, BigDecimal snctndAmt,
			Integer trnCounter, Long createdUserId,
			Long createdPostId, Date createdDate, Long dbId,
			String locationCode) {
		this.trnTablAmtDtlsId = trnTablAmtDtlsId;
		this.trnTablHdrId = trnTablHdrId;
		this.grossTotal = grossTotal;
		this.netClaimed = netClaimed;
		this.snctndAmt = snctndAmt;
		this.trnCounter = trnCounter;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	/** full constructor */
	public TrnTablAmtDtls(Long trnTablAmtDtlsId, Long trnTablHdrId,
			BigDecimal rlwStmFare, BigDecimal rdTrlFare, BigDecimal ttlDaClmed,
			BigDecimal incdntlExp, BigDecimal grossTotal, BigDecimal dedTaAdv,
			Long dedPtaDays, BigDecimal dedPta, BigDecimal netClaimed,
			BigDecimal snctndAmt, Date billPassedDate,
			BigDecimal billPassedAmt, Long attachId,
			String chqOrder, String remarks, Integer trnCounter,
			Long createdUserId, Long createdPostId,
			Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, Long dbId,
			BigDecimal rdChrgsPsperkm, String locationCode) {
		this.trnTablAmtDtlsId = trnTablAmtDtlsId;
		this.trnTablHdrId = trnTablHdrId;
		this.rlwStmFare = rlwStmFare;
		this.rdTrlFare = rdTrlFare;
		this.ttlDaClmed = ttlDaClmed;
		this.incdntlExp = incdntlExp;
		this.grossTotal = grossTotal;
		this.dedTaAdv = dedTaAdv;
		this.dedPtaDays = dedPtaDays;
		this.dedPta = dedPta;
		this.netClaimed = netClaimed;
		this.snctndAmt = snctndAmt;
		this.billPassedDate = billPassedDate;
		this.billPassedAmt = billPassedAmt;
		this.attachId = attachId;
		this.chqOrder = chqOrder;
		this.remarks = remarks;
		this.trnCounter = trnCounter;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.rdChrgsPsperkm = rdChrgsPsperkm;
		this.locationCode = locationCode;
	}

	// Property accessors
	public Long getTrnTablAmtDtlsId() {
		return this.trnTablAmtDtlsId;
	}

	public void setTrnTablAmtDtlsId(Long trnTablAmtDtlsId) {
		this.trnTablAmtDtlsId = trnTablAmtDtlsId;
	}

	public Long getTrnTablHdrId() {
		return this.trnTablHdrId;
	}

	public void setTrnTablHdrId(Long trnTablHdrId) {
		this.trnTablHdrId = trnTablHdrId;
	}

	public BigDecimal getRlwStmFare() {
		return this.rlwStmFare;
	}

	public void setRlwStmFare(BigDecimal rlwStmFare) {
		this.rlwStmFare = rlwStmFare;
	}

	public BigDecimal getRdTrlFare() {
		return this.rdTrlFare;
	}

	public void setRdTrlFare(BigDecimal rdTrlFare) {
		this.rdTrlFare = rdTrlFare;
	}

	public BigDecimal getTtlDaClmed() {
		return this.ttlDaClmed;
	}

	public void setTtlDaClmed(BigDecimal ttlDaClmed) {
		this.ttlDaClmed = ttlDaClmed;
	}

	public BigDecimal getIncdntlExp() {
		return this.incdntlExp;
	}

	public void setIncdntlExp(BigDecimal incdntlExp) {
		this.incdntlExp = incdntlExp;
	}

	public BigDecimal getGrossTotal() {
		return this.grossTotal;
	}

	public void setGrossTotal(BigDecimal grossTotal) {
		this.grossTotal = grossTotal;
	}

	public BigDecimal getDedTaAdv() {
		return this.dedTaAdv;
	}

	public void setDedTaAdv(BigDecimal dedTaAdv) {
		this.dedTaAdv = dedTaAdv;
	}

	public Long getDedPtaDays() {
		return this.dedPtaDays;
	}

	public void setDedPtaDays(Long dedPtaDays) {
		this.dedPtaDays = dedPtaDays;
	}

	public BigDecimal getDedPta() {
		return this.dedPta;
	}

	public void setDedPta(BigDecimal dedPta) {
		this.dedPta = dedPta;
	}

	public BigDecimal getNetClaimed() {
		return this.netClaimed;
	}

	public void setNetClaimed(BigDecimal netClaimed) {
		this.netClaimed = netClaimed;
	}

	public BigDecimal getSnctndAmt() {
		return this.snctndAmt;
	}

	public void setSnctndAmt(BigDecimal snctndAmt) {
		this.snctndAmt = snctndAmt;
	}

	public Date getBillPassedDate() {
		return this.billPassedDate;
	}

	public void setBillPassedDate(Date billPassedDate) {
		this.billPassedDate = billPassedDate;
	}

	public BigDecimal getBillPassedAmt() {
		return this.billPassedAmt;
	}

	public void setBillPassedAmt(BigDecimal billPassedAmt) {
		this.billPassedAmt = billPassedAmt;
	}

	public Long getAttachId() {
		return this.attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public String getChqOrder() {
		return this.chqOrder;
	}

	public void setChqOrder(String chqOrder) {
		this.chqOrder = chqOrder;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
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

	public BigDecimal getRdChrgsPsperkm() {
		return this.rdChrgsPsperkm;
	}

	public void setRdChrgsPsperkm(BigDecimal rdChrgsPsperkm) {
		this.rdChrgsPsperkm = rdChrgsPsperkm;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

}
