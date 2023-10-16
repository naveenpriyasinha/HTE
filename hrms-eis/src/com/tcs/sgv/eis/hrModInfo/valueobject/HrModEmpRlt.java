package com.tcs.sgv.eis.hrModInfo.valueobject;

// default package
// Generated Sep 19, 2007 5:29:06 AM by Hibernate Tools 3.2.0.beta8

import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import java.util.Date;

/**
 * HrModEmpRlt generated by hbm2java
 */
public class HrModEmpRlt implements java.io.Serializable {

	// Fields    

	private long reqId;

	private HrModMst hrModMst;

	private Long userId;

	private Long basicSal;

	private HrEisScaleMst scaleId;

	private OrgDesignationMst dsgnId;

	private Long postId;

	private Date createdDate;

	private Long createdBy;

	private Long createdByPost;

	private Long updatedBy;

	private Long updatedByPost;

	

	// Constructors

	/** default constructor */
	public HrModEmpRlt() {
	}

	/** minimal constructor */
	public HrModEmpRlt(long reqId) {
		this.reqId = reqId;
	}

	/** full constructor */
	public HrModEmpRlt(long reqId, HrModMst hrModMst, Long userId,
			Long basicSal, HrEisScaleMst scaleId, OrgDesignationMst dsgnId,
			Long postId, Date createdDate, Long createdBy,
			Long createdByPost, Long updatedBy,
			Long updatedByPost ) {
		this.reqId = reqId;
		this.hrModMst = hrModMst;
		this.userId = userId;
		this.basicSal = basicSal;
		this.scaleId = scaleId;
		this.dsgnId = dsgnId;
		this.postId = postId;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.updatedBy = updatedBy;
		this.updatedByPost = updatedByPost;
		
	}

	// Property accessors
	/**
	 * @return  the reqId
	 * @uml.property  name="reqId"
	 */
	public long getReqId() {
		return this.reqId;
	}

	/**
	 * @param reqId  the reqId to set
	 * @uml.property  name="reqId"
	 */
	public void setReqId(long reqId) {
		this.reqId = reqId;
	}

	/**
	 * @return  the hrModMst
	 * @uml.property  name="hrModMst"
	 */
	public HrModMst getHrModMst() {
		return this.hrModMst;
	}

	/**
	 * @param hrModMst  the hrModMst to set
	 * @uml.property  name="hrModMst"
	 */
	public void setHrModMst(HrModMst hrModMst) {
		this.hrModMst = hrModMst;
	}

	/**
	 * @return  the userId
	 * @uml.property  name="userId"
	 */
	public Long getUserId() {
		return this.userId;
	}

	/**
	 * @param userId  the userId to set
	 * @uml.property  name="userId"
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return  the basicSal
	 * @uml.property  name="basicSal"
	 */
	public Long getBasicSal() {
		return this.basicSal;
	}

	/**
	 * @param basicSal  the basicSal to set
	 * @uml.property  name="basicSal"
	 */
	public void setBasicSal(Long basicSal) {
		this.basicSal = basicSal;
	}

	/**
	 * @return  the scaleId
	 * @uml.property  name="scaleId"
	 */
	public HrEisScaleMst getScaleId() {
		return this.scaleId;
	}

	/**
	 * @param scaleId  the scaleId to set
	 * @uml.property  name="scaleId"
	 */
	public void setScaleId(HrEisScaleMst scaleId) {
		this.scaleId = scaleId;
	}

	/**
	 * @return  the dsgnId
	 * @uml.property  name="dsgnId"
	 */
	public OrgDesignationMst getDsgnId() {
		return this.dsgnId;
	}

	/**
	 * @param dsgnId  the dsgnId to set
	 * @uml.property  name="dsgnId"
	 */
	public void setDsgnId(OrgDesignationMst dsgnId) {
		this.dsgnId = dsgnId;
	}

	/**
	 * @return  the postId
	 * @uml.property  name="postId"
	 */
	public Long getPostId() {
		return this.postId;
	}

	/**
	 * @param postId  the postId to set
	 * @uml.property  name="postId"
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	/**
	 * @return  the createdDate
	 * @uml.property  name="createdDate"
	 */
	public Date getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * @param createdDate  the createdDate to set
	 * @uml.property  name="createdDate"
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return  the createdBy
	 * @uml.property  name="createdBy"
	 */
	public Long getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * @param createdBy  the createdBy to set
	 * @uml.property  name="createdBy"
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return  the createdByPost
	 * @uml.property  name="createdByPost"
	 */
	public Long getCreatedByPost() {
		return this.createdByPost;
	}

	/**
	 * @param createdByPost  the createdByPost to set
	 * @uml.property  name="createdByPost"
	 */
	public void setCreatedByPost(Long createdByPost) {
		this.createdByPost = createdByPost;
	}

	/**
	 * @return  the updatedBy
	 * @uml.property  name="updatedBy"
	 */
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * @param updatedBy  the updatedBy to set
	 * @uml.property  name="updatedBy"
	 */
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return  the updatedByPost
	 * @uml.property  name="updatedByPost"
	 */
	public Long getUpdatedByPost() {
		return this.updatedByPost;
	}

	/**
	 * @param updatedByPost  the updatedByPost to set
	 * @uml.property  name="updatedByPost"
	 */
	public void setUpdatedByPost(Long updatedByPost) {
		this.updatedByPost = updatedByPost;
	}

	

}
