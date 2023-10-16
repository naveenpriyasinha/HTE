package com.tcs.sgv.eis.valueobject;



import java.util.Date;

import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
* HrPayAllowTypeMst generated by hbm2java
*/
public class HrPayOrderHeadMpgHst implements java.io.Serializable {

	// Fields    

	private HrPayOrderHeadMpgHstId id;

	private long orderId;

	private Long subheadId;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private Date updatedDate;
	

	// Constructors

	/** default constructor */
	public HrPayOrderHeadMpgHst() {
	}

	/** full constructor */
	public HrPayOrderHeadMpgHst(HrPayOrderHeadMpgHstId id, Long subheadId,OrgUserMst orgUserMstByCreatedBy , OrgPostMst orgPostMstByCreatedByPost, Date createdDate,
			OrgUserMst orgUserMstByUpdatedBy, OrgPostMst orgPostMstByUpdatedByPost , Date updatedDate) {
		
		this.id = id;
		this.orderId = orderId;
		this.subheadId = subheadId;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.createdDate = createdDate;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.updatedDate = updatedDate;
	}

	

	public HrPayOrderHeadMpgHstId getId() {
		return this.id;
	}

	public void setId(HrPayOrderHeadMpgHstId id) {
		this.id = id;
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Long getSubheadId() {
		return this.subheadId;
	}

	public void setSubheadId(Long subheadId) {
		this.subheadId = subheadId;
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
	

	public void setOrgPostMstByUpdatedByPost(
			OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}
	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return this.orgPostMstByUpdatedByPost;
	}
	
	

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}
	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return this.orgUserMstByUpdatedBy;
	}
	
	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}
	
	public OrgUserMst getOrgUserMstByCreatedBy() {
		return this.orgUserMstByCreatedBy;
	}

	
	public void setOrgPostMstByCreatedByPost(
			OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}
	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return this.orgPostMstByCreatedByPost;
	}

}
