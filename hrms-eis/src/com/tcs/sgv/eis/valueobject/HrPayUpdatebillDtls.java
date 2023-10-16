package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class HrPayUpdatebillDtls implements java.io.Serializable {
	
	private long id;
	
	private HrEisEmpMst hrEisEmpMst;
	
	private HrPayPaybill hrPayPaybill;
	
	private String remarks;
	
	private Integer updatePaybillMonth;
	
	private Integer updatePaybillYear;
	
	private long attachmentId;

	private CmnDatabaseMst cmnDatabaseMst;
	
	private CmnLocationMst cmnLocationMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;

	private Date updatedDate;

	/** default constructor */
	public HrPayUpdatebillDtls() {
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public HrEisEmpMst getHrEisEmpMst() {
		return hrEisEmpMst;
	}


	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst) {
		this.hrEisEmpMst = hrEisEmpMst;
	}



	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Integer getUpdatePaybillMonth() {
		return updatePaybillMonth;
	}


	public void setUpdatePaybillMonth(Integer updatePaybillMonth) {
		this.updatePaybillMonth = updatePaybillMonth;
	}


	public Integer getUpdatePaybillYear() {
		return updatePaybillYear;
	}


	public void setUpdatePaybillYear(Integer updatePaybillYear) {
		this.updatePaybillYear = updatePaybillYear;
	}


	public long getAttachmentId() {
		return attachmentId;
	}


	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
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


	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return orgUserMstByUpdatedBy;
	}


	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
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


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}
	
		/**  minimal constructor */
		public HrPayUpdatebillDtls(CmnDatabaseMst cmnDatabaseMst, OrgPostMst orgPostMstByUpdatedByPost,OrgPostMst orgPostMstByCreatedByPost, OrgUserMst orgUserMstByCreatedBy, long otherId, HrEisEmpMst hrEisEmpMst,  Date createdDate) {
		super();
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.id = id;
		this.hrEisEmpMst = hrEisEmpMst;
		this.createdDate = createdDate;
	}


		/**  full constructor */
		public HrPayUpdatebillDtls(long id,CmnDatabaseMst cmnDatabaseMst, OrgPostMst orgPostMstByUpdatedByPost, OrgPostMst orgPostMstByCreatedByPost, HrEisQuaterTypeMst hrQuaterTypeMst,OrgUserMst orgUserMstByUpdatedBy, OrgUserMst orgUserMstByCreatedBy,  HrEisEmpMst hrEisEmpMst,
				 Date createdDate, Date updatedDate) {
		super();
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.id = id;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

		public HrPayPaybill getHrPayPaybill() {
			return hrPayPaybill;
		}

		public void setHrPayPaybill(HrPayPaybill hrPayPaybill) {
			this.hrPayPaybill = hrPayPaybill;
		}

}
