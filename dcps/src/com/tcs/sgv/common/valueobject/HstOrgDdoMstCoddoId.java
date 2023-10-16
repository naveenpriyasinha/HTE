// default package
// Generated Sep 18, 2009 3:53:28 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.common.valueobject;
/**
 * HstOrgDdoMstCoddoId generated by hbm2java
 */
public class HstOrgDdoMstCoddoId implements java.io.Serializable {

	// Fields    

	private long ddoId;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HstOrgDdoMstCoddoId() {
	}

	/** full constructor */
	public HstOrgDdoMstCoddoId(long ddoId, Integer trnCounter) {
		this.ddoId = ddoId;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getDdoId() {
		return this.ddoId;
	}

	public void setDdoId(long ddoId) {
		this.ddoId = ddoId;
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
		if (!(other instanceof HstOrgDdoMstCoddoId))
			return false;
		HstOrgDdoMstCoddoId castOther = (HstOrgDdoMstCoddoId) other;

		return (this.getDdoId() == castOther.getDdoId())
				&& (this.getTrnCounter() == castOther.getTrnCounter());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getDdoId();
		result = 37 * result + this.getTrnCounter();
		return result;
	}

}
