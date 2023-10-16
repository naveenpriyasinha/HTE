package com.tcs.sgv.eis.valueobject;
//Comment By Maruthi For import Organisation.

import java.util.Date;

import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class HrPayPayslipNonGovt implements java.io.Serializable {

	
	private long nonGovtId;
	
	private HrPayPaybill paybillID;
	
	private long deducCode;
	
	private long deducAmount;
	
	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;

	private OrgPostMst orgPostMstByCreatedByPost;
	
	//added by vaibhav tyagi: start
	private String nonGovType;
	//added by vaibhav tyagi: end

	public HrPayPayslipNonGovt(long nonGovtId, HrPayPaybill paybillID,
			long deducCode, long deducAmount, OrgUserMst orgUserMstByCreatedBy,
			Date createdDate, OrgPostMst orgPostMstByCreatedByPost,String nonGovType) {
		super();
		this.nonGovtId = nonGovtId;
		this.paybillID = paybillID;
		this.deducCode = deducCode;
		this.deducAmount = deducAmount;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.createdDate = createdDate;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.nonGovType = nonGovType;
	}

	public HrPayPayslipNonGovt() {
		// TODO Auto-generated constructor stub
	}

	public long getNonGovtId() {
		return nonGovtId;
	}

	public void setNonGovtId(long nonGovtId) {
		this.nonGovtId = nonGovtId;
	}

	public HrPayPaybill getPaybillID() {
		return paybillID;
	}

	public void setPaybillID(HrPayPaybill paybillID) {
		this.paybillID = paybillID;
	}

	public long getDeducCode() {
		return deducCode;
	}

	public void setDeducCode(long deducCode) {
		this.deducCode = deducCode;
	}

	public long getDeducAmount() {
		return deducAmount;
	}

	public void setDeducAmount(long deducAmount) {
		this.deducAmount = deducAmount;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public void setNonGovType(String nonGovType) {
		this.nonGovType = nonGovType;
	}

	public String getNonGovType() {
		return nonGovType;
	}
	
	
}