// default package
// Generated Oct 8, 2007 1:56:26 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.onlinebillprep.valueobject;

import java.util.Date;

/**
 * TrnOnlinebillEmp generated by hbm2java
 */
public class TrnOnlinebillEmp implements java.io.Serializable {

	// Fields    

	private Long trnOnlinebillEmpId;

	private Long billNo;

	private String empName;

	private String empDesgn;

	private String monthCode;

	private String deptName;

	private Integer trnCounter;

	private Long createdUserId;

	private Long createdPostId;

	private Date createdDate;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private Long dbId;

	private String locationCode;

	// Constructors

	/** default constructor */
	public TrnOnlinebillEmp() {
	}

	/** minimal constructor */
	public TrnOnlinebillEmp(Long trnOnlinebillEmpId, Long billNo,
			String deptName, Long createdUserId,
			Long createdPostId, Date createdDate, Long dbId,
			String locationCode) {
		this.trnOnlinebillEmpId = trnOnlinebillEmpId;
		this.billNo = billNo;
		this.deptName = deptName;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	/** full constructor */
	public TrnOnlinebillEmp(Long trnOnlinebillEmpId, Long billNo,
			String empName, String empDesgn, String monthCode,
			String deptName, Integer trnCounter, Long createdUserId,
			Long createdPostId, Date createdDate,
			Long updatedUserId, Long updatedPostId,
			Date updatedDate, Long dbId, String locationCode) {
		this.trnOnlinebillEmpId = trnOnlinebillEmpId;
		this.billNo = billNo;
		this.empName = empName;
		this.empDesgn = empDesgn;
		this.monthCode = monthCode;
		this.deptName = deptName;
		this.trnCounter = trnCounter;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.locationCode = locationCode;
	}

	// Property accessors
	public Long getTrnOnlinebillEmpId() {
		return this.trnOnlinebillEmpId;
	}

	public void setTrnOnlinebillEmpId(Long trnOnlinebillEmpId) {
		this.trnOnlinebillEmpId = trnOnlinebillEmpId;
	}

	public Long getBillNo() {
		return this.billNo;
	}

	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesgn() {
		return this.empDesgn;
	}

	public void setEmpDesgn(String empDesgn) {
		this.empDesgn = empDesgn;
	}

	public String getMonthCode() {
		return this.monthCode;
	}

	public void setMonthCode(String monthCode) {
		this.monthCode = monthCode;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

}