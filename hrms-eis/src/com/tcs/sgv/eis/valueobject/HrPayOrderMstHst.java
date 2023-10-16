package com.tcs.sgv.eis.valueobject;



import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
* HrPayAllowTypeMst generated by hbm2java
*/
public class HrPayOrderMstHst implements java.io.Serializable {

	// Fields    

	private HrPayOrderMstHstId id;

	private String orderName;

	private Date orderDate;
	
	private Date endDate;
	
	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;
	
	private Date updatedDate;
	
	private String locationCode;
	
	private CmnLanguageMst cmnLanguageMst;

	private long attachment_Id;
	
	

	// Constructors

	public long getAttachment_Id() {
		return attachment_Id;
	}

	public void setAttachment_Id(long attachment_Id) {
		this.attachment_Id = attachment_Id;
	}

	/** default constructor */
	public HrPayOrderMstHst() {
	}

	/** full constructor */
	public HrPayOrderMstHst(HrPayOrderMstHstId id,String orderName,Date orderDate,Date endDate,OrgUserMst orgUserMstByCreatedBy , OrgPostMst orgPostMstByCreatedByPost, Date createdDate,
			OrgUserMst orgUserMstByUpdatedBy, OrgPostMst orgPostMstByUpdatedByPost , Date updatedDate,String locationCode,CmnLanguageMst cmnLanguageMst) {
		
		this.id = id;
		this.orderName = orderName;
		this.orderDate = orderDate;
		this.endDate = endDate;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.createdDate = createdDate;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.updatedDate = updatedDate;
		this.cmnLanguageMst = cmnLanguageMst;
		this.locationCode=locationCode;
	}

	

	public HrPayOrderMstHstId getId() {
		return this.id;
	}

	public void setId(HrPayOrderMstHstId id) {
		this.id = id;
	}

	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	public CmnLanguageMst getCmnLanguageMst() {
		return cmnLanguageMst;
	}

	public void setCmnLanguageMst(CmnLanguageMst cmnLanguageMst) {
		this.cmnLanguageMst = cmnLanguageMst;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
