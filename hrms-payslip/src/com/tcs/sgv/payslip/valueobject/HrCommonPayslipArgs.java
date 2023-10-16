// default package
package com.tcs.sgv.payslip.valueobject;

import java.util.Date;

public class HrCommonPayslipArgs implements java.io.Serializable {

	// Fields    

	private long argsId;

	private long dispOrder;

	private long edpCode;

	private String propertyName;

	private String displayName;

	private long locId;

	private long dbId;

	private long createdBy;

	private Date createdDate;

	private long updatedBy;

	private long createdByPost;

	private String argsType;

	// Constructors

	/** default constructor */
	public HrCommonPayslipArgs() {
	}

	/** minimal constructor */
	public HrCommonPayslipArgs(long argsId) {
		this.argsId = argsId;
	}

	/** full constructor */
	public HrCommonPayslipArgs(long argsId, long dispOrder,
			long edpCode, String propertyName, String displayName,
			long locId, long dbId, long createdBy,
			Date createdDate, long updatedBy, long createdByPost,
			String argsType) {
		this.argsId = argsId;
		this.dispOrder = dispOrder;
		this.edpCode = edpCode;
		this.propertyName = propertyName;
		this.displayName = displayName;
		this.locId = locId;
		this.dbId = dbId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.createdByPost = createdByPost;
		this.argsType = argsType;
	}

	// Property accessors
	public long getArgsId() {
		return this.argsId;
	}

	public void setArgsId(long argsId) {
		this.argsId = argsId;
	}

	public long getDispOrder() {
		return this.dispOrder;
	}

	public void setDispOrder(long dispOrder) {
		this.dispOrder = dispOrder;
	}

	public long getEdpCode() {
		return this.edpCode;
	}

	public void setEdpCode(long edpCode) {
		this.edpCode = edpCode;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public long getLocId() {
		return this.locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public long getDbId() {
		return this.dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public long getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(long createdByPost) {
		this.createdByPost = createdByPost;
	}

	public String getArgsType() {
		return this.argsType;
	}

	public void setArgsType(String argsType) {
		this.argsType = argsType;
	}

}
