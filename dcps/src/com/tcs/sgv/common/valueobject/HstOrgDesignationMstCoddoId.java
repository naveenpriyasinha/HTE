// default package
// Generated Sep 14, 2009 5:48:27 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.common.valueobject;

/**
 * HstOrgDesignationMstCoddoId generated by hbm2java
 */
public class HstOrgDesignationMstCoddoId implements java.io.Serializable {

	// Fields    

	private long dsgnId;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HstOrgDesignationMstCoddoId() {
	}

	/** full constructor */
	public HstOrgDesignationMstCoddoId(long dsgnId, Integer trnCounter) {
		this.dsgnId = dsgnId;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getDsgnId() {
		return this.dsgnId;
	}

	public void setDsgnId(long dsgnId) {
		this.dsgnId = dsgnId;
	}

	public Integer getTrnCounter() {
		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HstOrgDesignationMstCoddoId))
			return false;
		HstOrgDesignationMstCoddoId castOther = (HstOrgDesignationMstCoddoId) other;

		return (this.getDsgnId() == castOther.getDsgnId())
				&& (this.getTrnCounter() == castOther.getTrnCounter());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getDsgnId();
		result = 37 * result + this.getTrnCounter();
		return result;
	}

}
