package com.tcs.sgv.pension.valueobject;
// Generated Feb 6, 2008 4:19:03 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionBillHdr generated by hbm2java
 */
public class TrnPensionBillHdr implements java.io.Serializable {

	// Fields    

	private Long trnPensionBillHdrId;

	private String billType;

	private String billNo;

	private String tokenNo;

	private Date billDate;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal headCode;

	private Integer forMonth;

	private String bankCode;

	private String branchCode;

	private String scheme;

    private Integer trnCounter;
	// Constructors

	/** default constructor */
	public TrnPensionBillHdr() {
	}

	/** minimal constructor */
	public TrnPensionBillHdr(Long trnPensionBillHdrId,
			BigDecimal createdUserId, BigDecimal createdPostId, Date createdDate) {
		this.trnPensionBillHdrId = trnPensionBillHdrId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public TrnPensionBillHdr(Long trnPensionBillHdrId, String billType,
			String billNo, String tokenNo, Date billDate,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, BigDecimal headCode,
			Integer forMonth, String bankCode, String branchCode, String scheme,Integer trnCounter) {
		this.trnPensionBillHdrId = trnPensionBillHdrId;
		this.billType = billType;
		this.billNo = billNo;
		this.tokenNo = tokenNo;
		this.billDate = billDate;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.headCode = headCode;
		this.forMonth = forMonth;
		this.bankCode = bankCode;
		this.branchCode = branchCode;
		this.scheme = scheme;
        this.trnCounter = trnCounter;
	}

	// Property accessors
	public Long getTrnPensionBillHdrId() {
		return this.trnPensionBillHdrId;
	}

	public void setTrnPensionBillHdrId(Long trnPensionBillHdrId) {
		this.trnPensionBillHdrId = trnPensionBillHdrId;
	}

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getTokenNo() {
		return this.tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
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

	public BigDecimal getHeadCode() {
		return this.headCode;
	}

	public void setHeadCode(BigDecimal headCode) {
		this.headCode = headCode;
	}

	public Integer getForMonth() {
		return this.forMonth;
	}

	public void setForMonth(Integer forMonth) {
		this.forMonth = forMonth;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getScheme() {
		return this.scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
    public Integer getTrnCounter()
    {
        return this.trnCounter;
    }

    public void setTrnCounter(Integer trnCounter)
    {
        this.trnCounter = trnCounter;
    }
}