// default package

package com.tcs.sgv.eis.valueobject;
// Generated Aug 24, 2007 7:34:40 PM by Hibernate Tools 3.2.0.beta8



/**
 * HrPayDeductionDtlsHstId generated by hbm2java
 */
public class HrPayDeductionDtlsHstId implements java.io.Serializable {

	// Fields    

	private long deducDtlsCode;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HrPayDeductionDtlsHstId() {
	}

	/** full constructor */
	public HrPayDeductionDtlsHstId(long deducDtlsCode, Integer trnCounter) {
		this.deducDtlsCode = deducDtlsCode;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getDeducDtlsCode() {
		return this.deducDtlsCode;
	}

	public void setDeducDtlsCode(long deducDtlsCode) {
		this.deducDtlsCode = deducDtlsCode;
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
		if (!(other instanceof HrPayDeductionDtlsHstId))
			return false;
		HrPayDeductionDtlsHstId castOther = (HrPayDeductionDtlsHstId) other;

		return ((this.getDeducDtlsCode() == castOther.getDeducDtlsCode()) || (new Long(this
				.getDeducDtlsCode()) != null
				&& new Long(castOther.getDeducDtlsCode()) != null &&new Long( this
				.getDeducDtlsCode()).equals(castOther.getDeducDtlsCode())))
				&& (this.getTrnCounter() == castOther.getTrnCounter());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (new Long(getDeducDtlsCode()) == null ? 0 : new Long(this.getDeducDtlsCode())
						.hashCode());
		result = 37 * result + (int) this.getTrnCounter();
		return result;
	}

}