package com.tcs.sgv.eis.valueobject;

// Generated Jul 30, 2007 3:09:23 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;

/**
 * HrPayDeductionDtls generated by hbm2java
 */
public class HrPayDeductionDtls implements java.io.Serializable {

	// Fields    

	private long deducDtlsCode;

	private HrEisEmpMst hrEisEmpMst;

	private HrPayDeducTypeMst hrPayDeducTypeMst;	

	private double empDeducAmount;

	private long empCurrentStatus;
	
	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByCreatedBy;

	private OrgUserMst orgUserMstByUpdatedBy;

	
	private Date createdDate;


	private Date updatedDate;
	
	private Integer trnCounter;

	private long month;
	
	private long year;
	

	// Constructors

	public CmnDatabaseMst getCmnDatabaseMst() {
		return cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	/** default constructor */
	public HrPayDeductionDtls() {
	}

	/** minimal constructor */
	public HrPayDeductionDtls(long deducDtlsCode, long empId) {
		this.deducDtlsCode = deducDtlsCode;
	}

	/** constructor */
	public HrPayDeductionDtls(long deducDtlsCode,
			long empDeducId, double empDeducAmount,
			long empCurrentStatus,
			Date createdDate,
			 Date updatedDate,Integer trnCounter) {
		this.deducDtlsCode = deducDtlsCode;
		this.empDeducAmount = empDeducAmount;
		this.empCurrentStatus = empCurrentStatus;			
		this.createdDate = createdDate;		
		this.updatedDate = updatedDate;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getDeducDtlsCode() {
		return this.deducDtlsCode;
	}

	public void setDeducDtlsCode(long deducDtlsCode) {
		this.deducDtlsCode = deducDtlsCode;
	}



	public HrEisEmpMst getHrEisEmpMst() {
		return hrEisEmpMst;
	}

	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst) {
		this.hrEisEmpMst = hrEisEmpMst;
	}

	public double getEmpDeducAmount() {
		return this.empDeducAmount;
	}

	public void setEmpDeducAmount(double empDeducAmount) {
		this.empDeducAmount = empDeducAmount;
	}

	public long getEmpCurrentStatus() {
		return this.empCurrentStatus;
	}

	public void setEmpCurrentStatus(long empCurrentStatus) {
		this.empCurrentStatus = empCurrentStatus;
	}

	
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	public HrPayDeducTypeMst getHrPayDeducTypeMst() {
		return hrPayDeducTypeMst;
	}

	public void setHrPayDeducTypeMst(HrPayDeducTypeMst hrPayDeducTypeMst) {
		this.hrPayDeducTypeMst = hrPayDeducTypeMst;
	}

	public Integer getTrnCounter() {
		return trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

}