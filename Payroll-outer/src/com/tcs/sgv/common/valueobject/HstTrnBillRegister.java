package com.tcs.sgv.common.valueobject;

// Generated Jun 19, 2007 11:15:23 AM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * HstTrnBillRegister generated by hbm2java
 */
public class HstTrnBillRegister implements java.io.Serializable {

	// Fields    

	private HstTrnBillRegisterId id;

	private String billCntrlNo;

	private Date billDate;

	private long subjectId;

	private Long tokenNum;

	private String tcBill;

	private long phyBill;

	private String demandCode;

	private String budmjrHd;

	private String ddoDeptId;

	private Date inwardDt;

	private Date chequeDispDt;

	private String receiptId;

	private Long prevBillNo;

	private BigDecimal billGrossAmount;

	private BigDecimal billNetAmount;

	private BigDecimal principle;

	private BigDecimal grossInterest;

	private BigDecimal incomeTax;

	private Long versionId;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private Long audDeptId;

	private Long audUserId;

	private Long ddoPostId;

	private String exempted;

	private String billCode;

	private String goNgo;

	private Long agStatus;

	private String currBillStatus;

	private String finYearId;

	private Long audPostId;

	private String chequeStatus;

	private Long attachmentId;

	private String ddoCode;

	private Date agStatusDate;

	private Date currBillStatusDate;

	private String paymentStatus;

	private Date paymentStatusDate;

	private BigDecimal grantAmount;

	private String deptCode;
	
	private String tsryOfficeCode;

	private String locationCode;
	
	private Long refNum;
	
	private BigDecimal auditorNetAmount;
    
    private Long scannedDocId;
    
    // Constructors

    /** default constructor */
	public HstTrnBillRegister() {
	}

	public HstTrnBillRegister(HstTrnBillRegisterId id, Date billDate,
			long subjectId, long phyBill, String locationCode, String ddoDeptId,
			Long createdUserId,	Long createdPostId, Date createdDate) {
		this.id = id;
		this.billDate = billDate;
		this.subjectId = subjectId;
		this.phyBill = phyBill;
		this.locationCode = locationCode;
		this.ddoDeptId = ddoDeptId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public HstTrnBillRegister(HstTrnBillRegisterId id, String billCntrlNo, Date billDate,
			long subjectId, Long tokenNum, String tcBill, long phyBill,
			String demandCode, String budmjrHd,String ddoDeptId,
			Date inwardDt,Date chequeDispDt, String receiptId, Long prevBillNo,
			BigDecimal billGrossAmount, BigDecimal billNetAmount,
			BigDecimal principle, BigDecimal grossInterest,
			BigDecimal incomeTax, Long versionId, Long createdUserId,
			Long createdPostId, Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, Long dbId,
			Long audDeptId, Long audUserId, Long ddoPostId, String exempted,
			String billCode, String goNgo, Long agStatus, String currBillStatus,
			String finYearId, Long audPostId, String chequeStatus,
			Long attachmentId, String ddoCode, Date agStatusDate,
			Date currBillStatusDate, String paymentStatus,
			Date paymentStatusDate, BigDecimal grantAmount, String deptCode,
			String tsryOfficeCode, String locationCode,Long refNum,
            BigDecimal auditorNetAmount, Long scannedDocId) {
		this.id = id;
		this.billCntrlNo = billCntrlNo;
		this.billDate = billDate;
		this.subjectId = subjectId;
		this.tokenNum = tokenNum;
		this.tcBill = tcBill;
		this.phyBill = phyBill;
		this.demandCode = demandCode;
		this.budmjrHd = budmjrHd;
		this.ddoDeptId = ddoDeptId;
		this.inwardDt = inwardDt;
		this.chequeDispDt = chequeDispDt;
		this.receiptId = receiptId;
		this.prevBillNo = prevBillNo;
		this.billGrossAmount = billGrossAmount;
		this.billNetAmount = billNetAmount;
		this.principle = principle;
		this.grossInterest = grossInterest;
		this.incomeTax = incomeTax;
		this.versionId = versionId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;		
		this.dbId = dbId;
		this.audDeptId = audDeptId;
		this.audUserId = audUserId;
		this.ddoPostId = ddoPostId;
		this.exempted = exempted;
		this.billCode = billCode;
		this.goNgo = goNgo;
		this.agStatus = agStatus;
		this.currBillStatus = currBillStatus;
		this.finYearId = finYearId;
		this.audPostId = audPostId;
		this.chequeStatus = chequeStatus;
		this.attachmentId = attachmentId;
		this.ddoCode = ddoCode;
		this.agStatusDate = agStatusDate;
		this.currBillStatusDate = currBillStatusDate;
		this.paymentStatus = paymentStatus;
		this.paymentStatusDate = paymentStatusDate;
		this.grantAmount = grantAmount;
		this.deptCode = deptCode;
		this.tsryOfficeCode = tsryOfficeCode;
		this.locationCode = locationCode;
		this.refNum = refNum;
		this.auditorNetAmount = auditorNetAmount;
        this.scannedDocId = scannedDocId;
	}

	// Property accessors
	public HstTrnBillRegisterId getId() {
		return this.id;
	}

	public void setId(HstTrnBillRegisterId id) {
		this.id = id;
	}

	public String getBillCntrlNo() {
		return this.billCntrlNo;
	}

	public void setBillCntrlNo(String billCntrlNo) {
		this.billCntrlNo = billCntrlNo;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getTokenNum() {
		return this.tokenNum;
	}

	public void setTokenNum(Long tokenNum) {
		this.tokenNum = tokenNum;
	}

	public String getTcBill() {
		return this.tcBill;
	}

	public void setTcBill(String tcBill) {
		this.tcBill = tcBill;
	}

	public long getPhyBill() {
		return this.phyBill;
	}

	public void setPhyBill(long phyBill) {
		this.phyBill = phyBill;
	}

	public String getDemandCode() {
		return this.demandCode;
	}

	public void setDemandCode(String demandCode) {
		this.demandCode = demandCode;
	}

	public String getBudmjrHd() {
		return this.budmjrHd;
	}

	public void setBudmjrHd(String budmjrHd) {
		this.budmjrHd = budmjrHd;
	}

	public String getDdoDeptId() {
		return this.ddoDeptId;
	}

	public void setDdoDeptId(String ddoDeptId) {
		this.ddoDeptId = ddoDeptId;
	}

	public Date getInwardDt() {
		return this.inwardDt;
	}

	public void setInwardDt(Date inwardDt) {
		this.inwardDt = inwardDt;
	}

	public Date getChequeDispDt() {
		return this.chequeDispDt;
	}

	public void setChequeDispDt(Date chequeDispDt) {
		this.chequeDispDt = chequeDispDt;
	}

	public String getReceiptId() {
		return this.receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public Long getPrevBillNo() {
		return this.prevBillNo;
	}

	public void setPrevBillNo(Long prevBillNo) {
		this.prevBillNo = prevBillNo;
	}

	public BigDecimal getBillGrossAmount() {
		return this.billGrossAmount;
	}

	public void setBillGrossAmount(BigDecimal billGrossAmount) {
		this.billGrossAmount = billGrossAmount;
	}

	public BigDecimal getBillNetAmount() {
		return this.billNetAmount;
	}

	public void setBillNetAmount(BigDecimal billNetAmount) {
		this.billNetAmount = billNetAmount;
	}

	public BigDecimal getPrinciple() {
		return this.principle;
	}

	public void setPrinciple(BigDecimal principle) {
		this.principle = principle;
	}

	public BigDecimal getGrossInterest() {
		return this.grossInterest;
	}

	public void setGrossInterest(BigDecimal grossInterest) {
		this.grossInterest = grossInterest;
	}

	public BigDecimal getIncomeTax() {
		return this.incomeTax;
	}

	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	public Long getVersionId() {
		return this.versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
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

	public Long getAudDeptId() {
		return this.audDeptId;
	}

	public void setAudDeptId(Long audDeptId) {
		this.audDeptId = audDeptId;
	}

	public Long getAudUserId() {
		return this.audUserId;
	}

	public void setAudUserId(Long audUserId) {
		this.audUserId = audUserId;
	}

	public Long getDdoPostId() {
		return this.ddoPostId;
	}

	public void setDdoPostId(Long ddoPostId) {
		this.ddoPostId = ddoPostId;
	}

	public String getExempted() {
		return this.exempted;
	}

	public void setExempted(String exempted) {
		this.exempted = exempted;
	}

	public String getBillCode() {
		return this.billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getGoNgo() {
		return this.goNgo;
	}

	public void setGoNgo(String goNgo) {
		this.goNgo = goNgo;
	}

	public Long getAgStatus() {
		return this.agStatus;
	}

	public void setAgStatus(Long agStatus) {
		this.agStatus = agStatus;
	}

	public String getCurrBillStatus() {
		return this.currBillStatus;
	}

	public void setCurrBillStatus(String currBillStatus) {
		this.currBillStatus = currBillStatus;
	}

	public String getFinYearId() {
		return this.finYearId;
	}

	public void setFinYearId(String finYearId) {
		this.finYearId = finYearId;
	}

	public Long getAudPostId() {
		return this.audPostId;
	}

	public void setAudPostId(Long audPostId) {
		this.audPostId = audPostId;
	}

	public String getChequeStatus() {
		return this.chequeStatus;
	}

	public void setChequeStatus(String chequeStatus) {
		this.chequeStatus = chequeStatus;
	}

	public Long getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getDdoCode() {
		return this.ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public Date getAgStatusDate() {
		return this.agStatusDate;
	}

	public void setAgStatusDate(Date agStatusDate) {
		this.agStatusDate = agStatusDate;
	}

	public Date getCurrBillStatusDate() {
		return this.currBillStatusDate;
	}

	public void setCurrBillStatusDate(Date currBillStatusDate) {
		this.currBillStatusDate = currBillStatusDate;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentStatusDate() {
		return this.paymentStatusDate;
	}

	public void setPaymentStatusDate(Date paymentStatusDate) {
		this.paymentStatusDate = paymentStatusDate;
	}

	public BigDecimal getGrantAmount() {
		return this.grantAmount;
	}

	public void setGrantAmount(BigDecimal grantAmount) {
		this.grantAmount = grantAmount;
	}
	
	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getTsryOfficeCode() {
		return tsryOfficeCode;
	}

	public void setTsryOfficeCode(String tsryOfficeCode) {
		this.tsryOfficeCode = tsryOfficeCode;
	}
	
	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Long getRefNum() {
		return refNum;
	}

	public void setRefNum(Long refNum) {
		this.refNum = refNum;
	}
	
	 public BigDecimal getAuditorNetAmount() {
			return this.auditorNetAmount;
	}

	public void setAuditorNetAmount(BigDecimal auditorNetAmount) {
			this.auditorNetAmount = auditorNetAmount;
	}
    
    public Long getScannedDocId()
    {
        return scannedDocId;
    }

    public void setScannedDocId(Long scannedDocId)
    {
        this.scannedDocId = scannedDocId;
    }
}