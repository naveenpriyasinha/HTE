package com.tcs.sgv.pdpla.valueobject;

// default package
// Generated Oct 10, 2007 3:28:48 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPdChqDetail generated by hbm2java
 */
public class TrnPdChqDetail implements java.io.Serializable {

	// Fields    

	private long paymentId;

	private String accountNoChq;

	private Date paymentDate;

	private String detailHd;

	private BigDecimal amount;

	private String transactionType;

	private String internalTc;

	private String fdAgCode;

	private String narration;

	private int chqNo;

	// Constructors

	/** default constructor */
	public TrnPdChqDetail() {
	}

	/** minimal constructor */
	public TrnPdChqDetail(long paymentId, String accountNoChq,
			Date paymentDate, BigDecimal amount, String transactionType,
			int chqNo) {
		this.paymentId = paymentId;
		this.accountNoChq = accountNoChq;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.transactionType = transactionType;
		this.chqNo = chqNo;
	}

	/** full constructor */
	public TrnPdChqDetail(long paymentId, String accountNoChq,
			Date paymentDate, String detailHd, BigDecimal amount,
			String transactionType, String internalTc, String fdAgCode,
			String narration, int chqNo) {
		this.paymentId = paymentId;
		this.accountNoChq = accountNoChq;
		this.paymentDate = paymentDate;
		this.detailHd = detailHd;
		this.amount = amount;
		this.transactionType = transactionType;
		this.internalTc = internalTc;
		this.fdAgCode = fdAgCode;
		this.narration = narration;
		this.chqNo = chqNo;
	}

	// Property accessors
	public long getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getAccountNoChq() {
		return this.accountNoChq;
	}

	public void setAccountNoChq(String accountNoChq) {
		this.accountNoChq = accountNoChq;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDetailHd() {
		return this.detailHd;
	}

	public void setDetailHd(String detailHd) {
		this.detailHd = detailHd;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getInternalTc() {
		return this.internalTc;
	}

	public void setInternalTc(String internalTc) {
		this.internalTc = internalTc;
	}

	public String getFdAgCode() {
		return this.fdAgCode;
	}

	public void setFdAgCode(String fdAgCode) {
		this.fdAgCode = fdAgCode;
	}

	public String getNarration() {
		return this.narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public int getChqNo() {
		return this.chqNo;
	}

	public void setChqNo(int chqNo) {
		this.chqNo = chqNo;
	}

}
