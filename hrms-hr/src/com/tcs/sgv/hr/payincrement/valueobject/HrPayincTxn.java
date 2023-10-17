package com.tcs.sgv.hr.payincrement.valueobject;
// default package
// Generated Sep 24, 2007 8:48:17 AM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrEisPayincTran generated by hbm2java
 */
public class HrPayincTxn implements java.io.Serializable {

	// Fields    

	private long reqTranId;

	private OrgUserMst orgUserMst;

	

	private Date lastIncDate;

	private Long lwp;

	

	private CmnDatabaseMst cmnDatabaseMst;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstCreatedBy;

	private OrgPostMst orgPostMstCreatedByPost;

	private Date createdDate;

	private OrgUserMst orgUserMstUpdatedBy;

	private OrgPostMst orgPostMstUpdatedByPost;

	private Date updatedDate;

	
	
	private Date actIncDate;

	private String compFlag;

	private Long sysComSalary;

	private Long userComSalary;

	private Date sysComNxtincDate;

	private Date userComNxtincDate;

	private Date defferedDate;

	private String remarks;

	private String approvalFlag;


	// Constructors

	/** default constructor */
	public HrPayincTxn() {
	}

	/** minimal constructor */
	public HrPayincTxn(Long reqTranId) {
		this.reqTranId = reqTranId;
	}

	/** full constructor */
	public HrPayincTxn(Long reqTranId,OrgUserMst orgUserMst, 
			 Date lastIncDate, Long lwp,
			 CmnDatabaseMst cmnDatabaseMst, CmnLocationMst cmnLocationMst,OrgUserMst orgUserMstCreatedBy,
			 OrgPostMst orgPostMstCreatedByPost, Date createdDate,
			 OrgUserMst orgUserMstUpdatedBy, OrgPostMst orgPostMstUpdatedByPost, Date updatedDate,
			Date actIncDate, String compFlag,
			Long sysComSalary, Long userComSalary,
			Date sysComNxtincDate, Date userComNxtincDate, Date defferedDate,
			String remarks, String approvalFlag) {
		
		this.reqTranId = reqTranId;
		this.orgUserMst = orgUserMst;
		
		this.lastIncDate = lastIncDate;
		this.lwp = lwp;
		
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstCreatedBy = orgUserMstCreatedBy;
		this.orgPostMstCreatedByPost = orgPostMstCreatedByPost;
		this.createdDate = createdDate;
		this.orgUserMstUpdatedBy = orgUserMstUpdatedBy;
		this.orgPostMstUpdatedByPost = orgPostMstUpdatedByPost;
		this.updatedDate = updatedDate;
		
		this.actIncDate = actIncDate;
		this.compFlag = compFlag;
		this.sysComSalary = sysComSalary;
		this.userComSalary = userComSalary;
		this.sysComNxtincDate = sysComNxtincDate;
		this.userComNxtincDate = userComNxtincDate;
		this.defferedDate = defferedDate;
		this.remarks = remarks;
		this.approvalFlag = approvalFlag;
	}

	// Property accessors
	public Long getReqTranId() {
		return this.reqTranId;
	}

	public void setReqTranId(Long reqTranId) {
		this.reqTranId = reqTranId;
	}

	

	public Date getLastIncDate() {
		return this.lastIncDate;
	}

	public void setLastIncDate(Date lastIncDate) {
		this.lastIncDate = lastIncDate;
	}

	public Long getLwp() {
		return this.lwp;
	}

	public void setLwp(Long lwp) {
		this.lwp = lwp;
	}

	
	public CmnDatabaseMst getCmnDatabaseMst() {
		return this.cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public CmnLocationMst getCmnLocationMst() {
		return this.cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public OrgUserMst getOrgUserMstCreatedBy() {
		return this.orgUserMstCreatedBy;
	}

	public void setOrgUserMstCreatedBy(OrgUserMst orgUserMstCreatedBy) {
		this.orgUserMstCreatedBy = orgUserMstCreatedBy;
	}

	public OrgPostMst getOrgPostMstCreatedByPost() {
		return this.orgPostMstCreatedByPost;
	}

	public void setOrgPostMstCreatedByPost(OrgPostMst orgPostMstCreatedByPost) {
		this.orgPostMstCreatedByPost = orgPostMstCreatedByPost;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public OrgUserMst getOrgUserMstUpdatedBy() {
		return this.orgUserMstUpdatedBy;
	}

	public void setOrgUserMstUpdatedBy(OrgUserMst orgUserMstUpdatedBy) {
		this.orgUserMstUpdatedBy = orgUserMstUpdatedBy;
	}

	public OrgPostMst getOrgPostMstUpdatedByPost() {
		return this.orgPostMstUpdatedByPost;
	}

	public void setOrgPostMstUpdatedByPost(OrgPostMst orgPostMstUpdatedByPost) {
		this.orgPostMstUpdatedByPost = orgPostMstUpdatedByPost;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	public Date getActIncDate() {
		return this.actIncDate;
	}

	public void setActIncDate(Date actIncDate) {
		this.actIncDate = actIncDate;
	}

	public String getCompFlag() {
		return this.compFlag;
	}

	public void setCompFlag(String compFlag) {
		this.compFlag = compFlag;
	}

	public Long getSysComSalary() {
		return this.sysComSalary;
	}

	public void setSysComSalary(Long sysComSalary) {
		this.sysComSalary = sysComSalary;
	}

	public Long getUserComSalary() {
		return this.userComSalary;
	}

	public void setUserComSalary(Long userComSalary) {
		this.userComSalary = userComSalary;
	}

	public Date getSysComNxtincDate() {
		return this.sysComNxtincDate;
	}

	public void setSysComNxtincDate(Date sysComNxtincDate) {
		this.sysComNxtincDate = sysComNxtincDate;
	}

	public Date getUserComNxtincDate() {
		return this.userComNxtincDate;
	}

	public void setUserComNxtincDate(Date userComNxtincDate) {
		this.userComNxtincDate = userComNxtincDate;
	}

	public Date getDefferedDate() {
		return this.defferedDate;
	}

	public void setDefferedDate(Date defferedDate) {
		this.defferedDate = defferedDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getApprovalFlag() {
		return this.approvalFlag;
	}

	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}

	public OrgUserMst getOrgUserMst() {
		return orgUserMst;
	}

	public void setOrgUserMst(OrgUserMst orgUserMst) {
		this.orgUserMst = orgUserMst;
	}

	

}