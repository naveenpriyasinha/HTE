package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

@SuppressWarnings("serial")
public class HrEisGISDtls implements java.io.Serializable {
	
	private long empGISId;
	
	private HrEisEmpMst hrEisEmpMst;
	
	private HrPayDeducTypeMst hrPayDeducTypeMst;
	
	private OrgGradeMst orgGradeMst;
	
	private Date membershipDate;
	 
	private String hrPayOrderMst;
	
	private Date GISOrderDate;
	
	private String remarks;
	
	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedByUser;

	private OrgUserMst orgUserMstByCreatedByUser; 
	
	private Date createdByDate;
	
	private Date updatedByDate;



	public long getEmpGISId() {
		return empGISId;
	}

	public void setEmpGISId(long empGISId) {
		this.empGISId = empGISId;
	}

	public HrEisEmpMst getHrEisEmpMst() {
		return hrEisEmpMst;
	}

	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst) {
		this.hrEisEmpMst = hrEisEmpMst;
	}

	public HrPayDeducTypeMst getHrPayDeducTypeMst() {
		return hrPayDeducTypeMst;
	}
	
	public void setHrPayDeducTypeMst(HrPayDeducTypeMst hrPayDeducTypeMst) {
		this.hrPayDeducTypeMst = hrPayDeducTypeMst;
	}

	public OrgGradeMst getOrgGradeMst() {
		return orgGradeMst;
	}

	public void setOrgGradeMst(OrgGradeMst orgGradeMst) {
		this.orgGradeMst = orgGradeMst;
	}

	public Date getMembershipDate() {
		return membershipDate;
	}

	
	public void setMembershipDate(Date membershipDate) {
		this.membershipDate = membershipDate;
	}

	public String getHrPayOrderMst() {
		return hrPayOrderMst;
	}

	
	public void setHrPayOrderMst(String hrPayOrderMst) {
		this.hrPayOrderMst = hrPayOrderMst;
	}

	public Date getGISOrderDate() {
		return GISOrderDate;
	}

	public void setGISOrderDate(Date orderDate) {
		GISOrderDate = orderDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public OrgUserMst getOrgUserMstByUpdatedByUser() {
		return orgUserMstByUpdatedByUser;
	}

	public void setOrgUserMstByUpdatedByUser(OrgUserMst orgUserMstByUpdatedByUser) {
		this.orgUserMstByUpdatedByUser = orgUserMstByUpdatedByUser;
	}

	public OrgUserMst getOrgUserMstByCreatedByUser() {
		return orgUserMstByCreatedByUser;
	}

	public void setOrgUserMstByCreatedByUser(OrgUserMst orgUserMstByCreatedByUser) {
		this.orgUserMstByCreatedByUser = orgUserMstByCreatedByUser;
	}

	public Date getCreatedByDate() {
		return createdByDate;
	}

	public void setCreatedByDate(Date createdByDate) {
		this.createdByDate = createdByDate;
	}

	public Date getUpdatedByDate() {
		return updatedByDate;
	}

	public void setUpdatedByDate(Date updatedByDate) {
		this.updatedByDate = updatedByDate;
	}

}
