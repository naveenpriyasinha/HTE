package com.tcs.sgv.eis.valueobject;




public class HrEisEmpMstHstId implements java.io.Serializable {

	// Fields    

	private long empId;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HrEisEmpMstHstId() {
	}

	/** full constructor */
	public HrEisEmpMstHstId(long empId, Integer trnCounter) {
		this.empId = empId;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getEmpId() {
		return this.empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
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
		if (!(other instanceof HrEisEmpMstHstId))
			return false;
		HrEisEmpMstHstId castOther = (HrEisEmpMstHstId) other;

		return (((Long)this.getEmpId() == castOther.getEmpId()) || ((Long)this.getEmpId() != null
				&& (Long)castOther.getEmpId() != null && new Long(this.getEmpId()).equals(
				castOther.getEmpId())))
				&& ((this.getTrnCounter() == castOther.getTrnCounter()) || (this
						.getTrnCounter() != null
						&& castOther.getTrnCounter() != null && this
						.getTrnCounter().equals(castOther.getTrnCounter())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (new Long(getEmpId()) == null ? 0 : new Long(this.getEmpId()).hashCode());
		result = 37
				* result
				+ (getTrnCounter() == null ? 0 : this.getTrnCounter()
						.hashCode());
		return result;
	}

}
