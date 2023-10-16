/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 16, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.valueobject;

import java.util.Date;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Mar 16, 2011
 */
public class MstSixPCArrears implements java.io.Serializable {

	private Long dcpsSixPCId;
	private MstEmp dcpsEmpId;
	private Long totalAmount;
	private Long amountPaid;
	private Character statusFlag;
	private Integer noOfInstallment;
	private Date fromDate;
	private Date toDate;
	private String remarks;
	private Long langId;
	private Long locId;
	private Long dbId;
	private Long createdPostId;
	private Long createdUserId;
	private Date createdDate;
	private Long updatedUserId;
	private Long updatedPostId;
	private Date updatedDate;

	public MstSixPCArrears() {
	}

	public MstSixPCArrears(Long dcpsSixPCId, MstEmp dcpsEmpId,
			Long totalAmount, Long amountPaid, Character statusFlag,Integer noOfInstallment,Date fromDate,Date toDate,
			String remarks, Long langId, Long locId, Long dbId,
			Long createdPostId, Long createdUserId, Date createdDate,
			Long updatedUserId, Long updatedPostId, Date updatedDate) {
		super();
		this.dcpsSixPCId = dcpsSixPCId;
		this.dcpsEmpId = dcpsEmpId;
		this.totalAmount = totalAmount;
		this.amountPaid = amountPaid;
		this.statusFlag = statusFlag;
		this.noOfInstallment = noOfInstallment;
		this.fromDate= fromDate;
		this.toDate = toDate;
		this.remarks = remarks;
		this.langId = langId;
		this.locId = locId;
		this.dbId = dbId;
		this.createdPostId = createdPostId;
		this.createdUserId = createdUserId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
	}

	public Long getDcpsSixPCId() {
		return dcpsSixPCId;
	}

	public void setDcpsSixPCId(Long dcpsSixPCId) {
		this.dcpsSixPCId = dcpsSixPCId;
	}

	public MstEmp getDcpsEmpId() {
		return dcpsEmpId;
	}

	public void setDcpsEmpId(MstEmp dcpsEmpId) {
		this.dcpsEmpId = dcpsEmpId;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Long amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Character getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Character statusFlag) {
		this.statusFlag = statusFlag;
	}

	
	public Integer getNoOfInstallment() {
		return noOfInstallment;
	}

	public void setNoOfInstallment(Integer noOfInstallment) {
		this.noOfInstallment = noOfInstallment;
	}
	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getLangId() {
		return langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
	}

	public Long getLocId() {
		return locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public Long getDbId() {
		return dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public Long getCreatedPostId() {
		return createdPostId;
	}

	public void setCreatedPostId(Long createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Long getUpdatedPostId() {
		return updatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
