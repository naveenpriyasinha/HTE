package com.tcs.sgv.billproc.cheque.valueobject;

// Generated Jun 25, 2007 12:25:26 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 *  com.tcs.sgv.billproc.cheque.valueobject.TrnChequeDtls
 *  
 *  This is Java bean for getting all cheque details
 *  
 * 	Date of Creation : 10th July 2007
 * 
 *  Author : Vidhya Mashru 
 *  
 *  Revision History 
 *  =====================
 *  Vidhya M    23-Oct-2007  Made changes for code formatting
 */
public class TrnChequeDtls implements java.io.Serializable {

	// Fields    

	private long chequeId;

	private BigDecimal chequeAmt;

	private Date fromDt;

	private Date toDt;

	private String partyName;

	private Date clearedDt;

	private Long chequeNo;

	private Date printDate;

	private Long printUser;

	private Long adviceNo;

	private Date adviceDt;

	private String status;

	private Long prevChequeId;

	private Long finYearId;

	private long createdUserId;

	private long createdPostId;

	private Date createdDate;

	private Long updatedPostId;

	private Long updatedUserId;

	private Date updatedDate;

	private Long dbId;

	private String chequeType;

	private String groupId;

	private String ddoParty;

	private Integer trnCounter;

	private String remarks;

	private Date statusDate;
	
	private String partyAddr;

	private String accntNum;
	
	private String partyCode;
	
	private String locationCode;
	
	// Constructors

	/** default constructor */
	public TrnChequeDtls() {
	}

	/** minimal constructor */
	public TrnChequeDtls(long chequeId, BigDecimal chequeAmt, Date fromDt,
			Date toDt, String partyName, String status, long createdUserId,
			long createdPostId,Date createdDate) {
		this.chequeId = chequeId;
		this.chequeAmt = chequeAmt;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.partyName = partyName;
		this.status = status;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public TrnChequeDtls(long chequeId, BigDecimal chequeAmt, Date fromDt,
			Date toDt, String partyName, Date clearedDt, Long chequeNo,
			Date printDate, Long printUser, Long adviceNo, Date adviceDt,
			String status, Long prevChequeId, Long finYearId,
			long createdUserId, long createdPostId, Date createdDate,
			Long updatedPostId, Long updatedUserId, Date updatedDate,
			Long dbId, String chequeType, String groupId, String ddoParty,
			Integer trnCounter, String remarks,Date statusDate,String partyAddr, String accntNum,String partyCode,String locationCode) {
		this.chequeId = chequeId;
		this.chequeAmt = chequeAmt;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.partyName = partyName;
		this.clearedDt = clearedDt;
		this.chequeNo = chequeNo;
		this.printDate = printDate;
		this.printUser = printUser;
		this.adviceNo = adviceNo;
		this.adviceDt = adviceDt;
		this.status = status;
		this.prevChequeId = prevChequeId;
		this.finYearId = finYearId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedPostId = updatedPostId;
		this.updatedUserId = updatedUserId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.chequeType = chequeType;
		this.statusDate = statusDate;
		this.groupId = groupId;
		this.ddoParty = ddoParty;
		this.trnCounter = trnCounter;
		this.remarks = remarks;
		this.partyAddr = partyAddr;
		this.accntNum = accntNum;
		this.partyCode = partyCode;
		this.locationCode =locationCode; 
	}

	// Property accessors
	public long getChequeId() {
		return this.chequeId;
	}

	public void setChequeId(long chequeId) {
		this.chequeId = chequeId;
	}

	public BigDecimal getChequeAmt() {
		return this.chequeAmt;
	}

	public void setChequeAmt(BigDecimal chequeAmt) {
		this.chequeAmt = chequeAmt;
	}

	public Date getFromDt() {
		return this.fromDt;
	}

	public void setFromDt(Date fromDt) {
		this.fromDt = fromDt;
	}

	public Date getToDt() {
		return this.toDt;
	}

	public void setToDt(Date toDt) {
		this.toDt = toDt;
	}

	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Date getClearedDt() {
		return this.clearedDt;
	}

	public void setClearedDt(Date clearedDt) {
		this.clearedDt = clearedDt;
	}

	public Long getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(Long chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Date getPrintDate() {
		return this.printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public Long getPrintUser() {
		return this.printUser;
	}

	public void setPrintUser(Long printUser) {
		this.printUser = printUser;
	}

	public Long getAdviceNo() {
		return this.adviceNo;
	}

	public void setAdviceNo(Long adviceNo) {
		this.adviceNo = adviceNo;
	}

	public Date getAdviceDt() {
		return this.adviceDt;
	}

	public void setAdviceDt(Date adviceDt) {
		this.adviceDt = adviceDt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPrevChequeId() {
		return this.prevChequeId;
	}

	public void setPrevChequeId(Long prevChequeId) {
		this.prevChequeId = prevChequeId;
	}

	public Long getFinYearId() {
		return this.finYearId;
	}

	public void setFinYearId(Long finYearId) {
		this.finYearId = finYearId;
	}

	public long getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(long createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Long getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
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

	public String getChequeType() {
		return this.chequeType;
	}

	public void setChequeType(String chequeType) {
		this.chequeType = chequeType;
	}

	
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getDdoParty() {
		return this.ddoParty;
	}

	public void setDdoParty(String ddoParty) {
		this.ddoParty = ddoParty;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getAccntNum() {
		return accntNum;
	}

	public void setAccntNum(String accntNum) {
		this.accntNum = accntNum;
	}

	public String getPartyAddr() {
		return partyAddr;
	}

	public void setPartyAddr(String partyAddr) {
		this.partyAddr = partyAddr;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	
	
	

}
