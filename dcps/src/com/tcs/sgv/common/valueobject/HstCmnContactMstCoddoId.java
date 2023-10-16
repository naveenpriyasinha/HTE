// default package
// Generated Sep 15, 2009 3:48:10 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.common.valueobject;
/**
 * HstCmnContactMstCoddoId generated by hbm2java
 */
public class HstCmnContactMstCoddoId implements java.io.Serializable {

	// Fields    

	private long srNo;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HstCmnContactMstCoddoId() {
	}

	/** full constructor */
	public HstCmnContactMstCoddoId(long srNo, Integer trnCounter) {
		this.srNo = srNo;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getSrNo() {
		return this.srNo;
	}

	public void setSrNo(long srNo) {
		this.srNo = srNo;
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
		if (!(other instanceof HstCmnContactMstCoddoId))
			return false;
		HstCmnContactMstCoddoId castOther = (HstCmnContactMstCoddoId) other;

		return (this.getSrNo() == castOther.getSrNo())
				&& (this.getTrnCounter() == castOther.getTrnCounter());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getSrNo();
		result = 37 * result + this.getTrnCounter();
		return result;
	}

}
