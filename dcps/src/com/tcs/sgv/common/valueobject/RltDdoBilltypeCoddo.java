// default package
// Generated Sep 14, 2009 5:49:09 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.common.valueobject;
import java.util.Date;

/**
 * RltDdoBilltypeCoddo generated by hbm2java
 */
public class RltDdoBilltypeCoddo implements java.io.Serializable {

	// Fields    

	private Long rltDdobilltypeId;

	private Byte subjectId;

	private Integer createdBy;

	private Integer createdByPost;

	private Date createdDate;

	private Integer trnCounter;

	private Short dbId;

	private String ddoCode;

	// Constructors

	/** default constructor */
	public RltDdoBilltypeCoddo() {
	}

	/** full constructor */
	public RltDdoBilltypeCoddo(Long rltDdobilltypeId, Byte subjectId,
			Integer createdBy, Integer createdByPost, Date createdDate,
			Integer trnCounter, Short dbId, String ddoCode) {
		this.rltDdobilltypeId = rltDdobilltypeId;
		this.subjectId = subjectId;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.trnCounter = trnCounter;
		this.dbId = dbId;
		this.ddoCode = ddoCode;
	}

	// Property accessors
	public Long getRltDdobilltypeId() {
		return this.rltDdobilltypeId;
	}

	public void setRltDdobilltypeId(Long rltDdobilltypeId) {
		this.rltDdobilltypeId = rltDdobilltypeId;
	}

	public Byte getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Byte subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(Integer createdByPost) {
		this.createdByPost = createdByPost;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public Short getDbId() {
		return this.dbId;
	}

	public void setDbId(Short dbId) {
		this.dbId = dbId;
	}

	public String getDdoCode() {
		return this.ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

}
