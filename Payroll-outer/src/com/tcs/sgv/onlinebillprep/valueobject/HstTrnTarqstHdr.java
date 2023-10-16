package com.tcs.sgv.onlinebillprep.valueobject;
// default package
// Generated Sep 28, 2007 12:36:58 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * HstTrnTarqstHdr generated by hbm2java
 */
public class HstTrnTarqstHdr implements java.io.Serializable {

	// Fields    

	private HstTrnTarqstHdrId id;

	private Long trnAprvdRqstId;

	private BigDecimal empPay;

	private BigDecimal empPta;

	private BigDecimal empCa;

	private String monthCode;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private String headQtrEn;

	private String headQtrGu;

	private BigDecimal snctndAmt;

	// Constructors

	/** default constructor */
	public HstTrnTarqstHdr() {
	}

	/** minimal constructor */
	public HstTrnTarqstHdr(HstTrnTarqstHdrId id, Long trnAprvdRqstId,
			BigDecimal empPay, BigDecimal snctndAmt) {
		this.id = id;
		this.trnAprvdRqstId = trnAprvdRqstId;
		this.empPay = empPay;
		this.snctndAmt = snctndAmt;
	}

	/** full constructor */
	public HstTrnTarqstHdr(HstTrnTarqstHdrId id, Long trnAprvdRqstId,
			BigDecimal empPay, BigDecimal empPta, BigDecimal empCa,
			String monthCode, Long updatedUserId,
			Long updatedPostId, Date updatedDate, String headQtrEn,
			String headQtrGu, BigDecimal snctndAmt) {
		this.id = id;
		this.trnAprvdRqstId = trnAprvdRqstId;
		this.empPay = empPay;
		this.empPta = empPta;
		this.empCa = empCa;
		this.monthCode = monthCode;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.headQtrEn = headQtrEn;
		this.headQtrGu = headQtrGu;
		this.snctndAmt = snctndAmt;
	}

	// Property accessors
	public HstTrnTarqstHdrId getId() {
		return this.id;
	}

	public void setId(HstTrnTarqstHdrId id) {
		this.id = id;
	}

	public Long getTrnAprvdRqstId() {
		return this.trnAprvdRqstId;
	}

	public void setTrnAprvdRqstId(Long trnAprvdRqstId) {
		this.trnAprvdRqstId = trnAprvdRqstId;
	}

	public BigDecimal getEmpPay() {
		return this.empPay;
	}

	public void setEmpPay(BigDecimal empPay) {
		this.empPay = empPay;
	}

	public BigDecimal getEmpPta() {
		return this.empPta;
	}

	public void setEmpPta(BigDecimal empPta) {
		this.empPta = empPta;
	}

	public BigDecimal getEmpCa() {
		return this.empCa;
	}

	public void setEmpCa(BigDecimal empCa) {
		this.empCa = empCa;
	}

	public String getMonthCode() {
		return this.monthCode;
	}

	public void setMonthCode(String monthCode) {
		this.monthCode = monthCode;
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

	public String getHeadQtrEn() {
		return this.headQtrEn;
	}

	public void setHeadQtrEn(String headQtrEn) {
		this.headQtrEn = headQtrEn;
	}

	public String getHeadQtrGu() {
		return this.headQtrGu;
	}

	public void setHeadQtrGu(String headQtrGu) {
		this.headQtrGu = headQtrGu;
	}

	public BigDecimal getSnctndAmt() {
		return this.snctndAmt;
	}

	public void setSnctndAmt(BigDecimal snctndAmt) {
		this.snctndAmt = snctndAmt;
	}

}
