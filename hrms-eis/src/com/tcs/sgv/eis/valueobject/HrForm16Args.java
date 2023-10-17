// default package
package com.tcs.sgv.eis.valueobject;
// Generated Aug 31, 2007 6:38:30 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * HrForm16Args generated by hbm2java
 */
public class HrForm16Args implements java.io.Serializable {

	// Fields    

	private long argId;

	private long dispOrder;

	private String voProperty;

	private String displayName;
	
	private long valueInCol;

	private long locId;

	private long dbId;

	private long createdBy;

	private Date createdDate;

	private long updateBy;

	private long createdByPost;
	
	// Constructors

	/** default constructor */
	public HrForm16Args() {
	}

	/** minimal constructor */
	public HrForm16Args(long argId) {
		this.argId = argId;
	}

	/** full constructor */
	public HrForm16Args(long argId, long dispOrder,
			long valueInCol, String voProperty, String displayName,
			long locId, long dbId, long createdBy,
			Date createdDate, long updatedBy, long createdByPost) {
		this.argId = argId;
		this.dispOrder = dispOrder;
		this.voProperty = voProperty;
		this.displayName = displayName;
		this.locId = locId;
		this.dbId = dbId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updateBy = updatedBy;
		this.createdByPost = createdByPost;
	}

	// Property accessors
	public long getArgId() {
		return this.argId;
	}

	public void setArgId(long argId) {
		this.argId = argId;
	}

	public long getDispOrder() {
		return this.dispOrder;
	}

	public void setDispOrder(long dispOrder) {
		this.dispOrder = dispOrder;
	}

	public long getValueInCol() {
		return this.valueInCol;
	}

	public void setValueInCol(long valueInCol) {
		this.valueInCol = valueInCol;
	}

	public String getVoProperty() {
		return this.voProperty;
	}

	public void setVoProperty(String voProperty) {
		this.voProperty = voProperty;
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

	public long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(long updateBy) {
		this.updateBy = updateBy;
	}

	public long getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(long createdByPost) {
		this.createdByPost = createdByPost;
	}

}