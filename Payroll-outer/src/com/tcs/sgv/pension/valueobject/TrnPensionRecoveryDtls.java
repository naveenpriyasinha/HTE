package com.tcs.sgv.pension.valueobject;
// Generated Apr 23, 2008 12:42:34 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionRecoveryDtls generated by hbm2java
 */
public class TrnPensionRecoveryDtls implements java.io.Serializable {

	// Fields    

	private Long trnPensionRecoveryDtlsId;

	private Long pensionRequestId;

	private String pensionerCode;

	private String recoveryFromFlag;

	private Date dateOfReceipt;

	private String recoveryType;

	private String accountNumber;

	private String edpCode;

	private String mjrhdCode;

	private String submjrhdCode;

	private String minhdCode;

	private String subhdCode;

	private BigDecimal amount;

	private Integer fromMonth;

	private Integer toMonth;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private String deductionType;

	private String billNo;

     private Integer trnCounter;
	// Constructors

	/** default constructor */
	public TrnPensionRecoveryDtls() {
	}

	/** minimal constructor */
	public TrnPensionRecoveryDtls(Long trnPensionRecoveryDtlsId,
            Long pensionRequestId, String pensionerCode) {
		this.trnPensionRecoveryDtlsId = trnPensionRecoveryDtlsId;
		this.pensionRequestId = pensionRequestId;
		this.pensionerCode = pensionerCode;
	}

	/** full constructor */
	public TrnPensionRecoveryDtls(Long trnPensionRecoveryDtlsId,
            Long pensionRequestId, String pensionerCode,
			String recoveryFromFlag, Date dateOfReceipt, String recoveryType,
			String accountNumber, String edpCode, String mjrhdCode,
			String submjrhdCode, String minhdCode, String subhdCode,
			BigDecimal amount, Integer fromMonth, Integer toMonth,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, String deductionType,
			String billNo,Integer trnCounter) {
		this.trnPensionRecoveryDtlsId = trnPensionRecoveryDtlsId;
		this.pensionRequestId = pensionRequestId;
		this.pensionerCode = pensionerCode;
		this.recoveryFromFlag = recoveryFromFlag;
		this.dateOfReceipt = dateOfReceipt;
		this.recoveryType = recoveryType;
		this.accountNumber = accountNumber;
		this.edpCode = edpCode;
		this.mjrhdCode = mjrhdCode;
		this.submjrhdCode = submjrhdCode;
		this.minhdCode = minhdCode;
		this.subhdCode = subhdCode;
		this.amount = amount;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.deductionType = deductionType;
		this.billNo = billNo;
        this.trnCounter = trnCounter;
	}

	// Property accessors
	public Long getTrnPensionRecoveryDtlsId() {
		return this.trnPensionRecoveryDtlsId;
	}

	public void setTrnPensionRecoveryDtlsId(Long trnPensionRecoveryDtlsId) {
		this.trnPensionRecoveryDtlsId = trnPensionRecoveryDtlsId;
	}

	public Long getPensionRequestId() {
		return this.pensionRequestId;
	}

	public void setPensionRequestId(Long pensionRequestId) {
		this.pensionRequestId = pensionRequestId;
	}

	public String getPensionerCode() {
		return this.pensionerCode;
	}

	public void setPensionerCode(String pensionerCode) {
		this.pensionerCode = pensionerCode;
	}

	public String getRecoveryFromFlag() {
		return this.recoveryFromFlag;
	}

	public void setRecoveryFromFlag(String recoveryFromFlag) {
		this.recoveryFromFlag = recoveryFromFlag;
	}

	public Date getDateOfReceipt() {
		return this.dateOfReceipt;
	}

	public void setDateOfReceipt(Date dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	public String getRecoveryType() {
		return this.recoveryType;
	}

	public void setRecoveryType(String recoveryType) {
		this.recoveryType = recoveryType;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getEdpCode() {
		return this.edpCode;
	}

	public void setEdpCode(String edpCode) {
		this.edpCode = edpCode;
	}

	public String getMjrhdCode() {
		return this.mjrhdCode;
	}

	public void setMjrhdCode(String mjrhdCode) {
		this.mjrhdCode = mjrhdCode;
	}

	public String getSubmjrhdCode() {
		return this.submjrhdCode;
	}

	public void setSubmjrhdCode(String submjrhdCode) {
		this.submjrhdCode = submjrhdCode;
	}

	public String getMinhdCode() {
		return this.minhdCode;
	}

	public void setMinhdCode(String minhdCode) {
		this.minhdCode = minhdCode;
	}

	public String getSubhdCode() {
		return this.subhdCode;
	}

	public void setSubhdCode(String subhdCode) {
		this.subhdCode = subhdCode;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getFromMonth() {
		return this.fromMonth;
	}

	public void setFromMonth(Integer fromMonth) {
		this.fromMonth = fromMonth;
	}

	public Integer getToMonth() {
		return this.toMonth;
	}

	public void setToMonth(Integer toMonth) {
		this.toMonth = toMonth;
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

	public String getDeductionType() {
		return this.deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
    }
    
    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }
}