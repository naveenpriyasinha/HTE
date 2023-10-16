package com.tcs.sgv.pensionpay.valueobject;


// Generated May 28, 2008 10:38:56 AM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionTransferDtls generated by hbm2java
 */
public class TrnPensionTransferDtls implements java.io.Serializable {

	// Fields    

	private Long transferDtlsId;

	private String ppoNo;

	private Date transferDate;

	private Date receiveDate;

	private String fromLocation;

	private String toLocation;

	private String status;

	private BigDecimal transferPostId;

	private BigDecimal receivePostId;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private String pensionerCode;
	
	private String remarks;

	private Character agFlag;
	
	private String registrationNo;
	
	private String otherStateName;
	
	private Date ppoInwardDate;
	
	private Date ppoRegDate;
	
	private Date commensionDate;
	
	private String requestId;
	// Constructors

	/** default constructor */
	public TrnPensionTransferDtls() {
	}

	/** minimal constructor */
	public TrnPensionTransferDtls(Long transferDtlsId, String ppoNo,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, String pensionerCode) {
		this.transferDtlsId = transferDtlsId;
		this.ppoNo = ppoNo;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.pensionerCode = pensionerCode;
	}

	/** full constructor */
	public TrnPensionTransferDtls(Long transferDtlsId, String ppoNo,
			Date transferDate, Date receiveDate, String fromLocation,
			String toLocation, String status, BigDecimal transferPostId,
			BigDecimal receivePostId, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId,
			Date updatedDate, String pensionerCode,String remarks,Character 
			agFlag,String registrationNo,String otherStateName,Date ppoInwardDate,Date ppoRegDate,Date commensionDate,String requestId) {
		this.transferDtlsId = transferDtlsId;
		this.ppoNo = ppoNo;
		this.transferDate = transferDate;
		this.receiveDate = receiveDate;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.status = status;
		this.transferPostId = transferPostId;
		this.receivePostId = receivePostId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.pensionerCode = pensionerCode;
		this.remarks = remarks;
		this.agFlag = agFlag;
		this.registrationNo = registrationNo;
		this.otherStateName = otherStateName;
		this.ppoInwardDate = ppoInwardDate;
		this.ppoRegDate = ppoRegDate;
		this.commensionDate = commensionDate;
		this.requestId = requestId;
	}

	// Property accessors
	public Long getTransferDtlsId() {
		return this.transferDtlsId;
	}

	public void setTransferDtlsId(Long transferDtlsId) {
		this.transferDtlsId = transferDtlsId;
	}

	public String getPpoNo() {
		return this.ppoNo;
	}

	public void setPpoNo(String ppoNo) {
		this.ppoNo = ppoNo;
	}

	public Date getTransferDate() {
		return this.transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Date getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getFromLocation() {
		return this.fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return this.toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTransferPostId() {
		return this.transferPostId;
	}

	public void setTransferPostId(BigDecimal transferPostId) {
		this.transferPostId = transferPostId;
	}

	public BigDecimal getReceivePostId() {
		return this.receivePostId;
	}

	public void setReceivePostId(BigDecimal receivePostId) {
		this.receivePostId = receivePostId;
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

	public String getPensionerCode() {
		return this.pensionerCode;
	}

	public void setPensionerCode(String pensionerCode) {
		this.pensionerCode = pensionerCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Character getAgFlag() {
		return agFlag;
	}

	public void setAgFlag(Character agFlag) {
		this.agFlag = agFlag;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getOtherStateName() {
		return otherStateName;
	}

	public void setOtherStateName(String otherStateName) {
		this.otherStateName = otherStateName;
	}

	public Date getPpoInwardDate() {
		return ppoInwardDate;
	}

	public void setPpoInwardDate(Date ppoInwardDate) {
		this.ppoInwardDate = ppoInwardDate;
	}

	public Date getPpoRegDate() {
		return ppoRegDate;
	}

	public void setPpoRegDate(Date ppoRegDate) {
		this.ppoRegDate = ppoRegDate;
	}

	public Date getCommensionDate() {
		return commensionDate;
	}

	public void setCommensionDate(Date commensionDate) {
		this.commensionDate = commensionDate;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
	

}
