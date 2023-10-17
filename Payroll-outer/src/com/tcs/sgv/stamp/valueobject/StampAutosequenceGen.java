package com.tcs.sgv.stamp.valueobject;
// Generated Oct 24, 2007 4:57:59 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;

/**
 * StampAutosequenceGen generated by hbm2java
 */
public class StampAutosequenceGen implements java.io.Serializable {

	// Fields    

	private BigDecimal locCode;

	private Long indentNo;

	private Long challanNo;

	// Constructors

	/** default constructor */
	public StampAutosequenceGen() {
	}

	/** minimal constructor */
	public StampAutosequenceGen(BigDecimal locCode) {
		this.locCode = locCode;
	}

	/** full constructor */
	public StampAutosequenceGen(BigDecimal locCode, Long indentNo,
			Long challanNo) {
		this.locCode = locCode;
		this.indentNo = indentNo;
		this.challanNo = challanNo;
	}

	// Property accessors
	public BigDecimal getLocCode() {
		return this.locCode;
	}

	public void setLocCode(BigDecimal locCode) {
		this.locCode = locCode;
	}

	public Long getIndentNo() {
		return this.indentNo;
	}

	public void setIndentNo(Long indentNo) {
		this.indentNo = indentNo;
	}

	public Long getChallanNo() {
		return this.challanNo;
	}

	public void setChallanNo(Long challanNo) {
		this.challanNo = challanNo;
	}

}