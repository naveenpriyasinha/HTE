// default package
// Generated Aug 25, 2007 12:43:11 PM by Hibernate Tools 3.2.0.beta8

package com.tcs.sgv.eis.valueobject;

/**
 * HrPayVpfDtlsHstId generated by hbm2java
 */
public class HrPayVpfDtlsHstId implements java.io.Serializable {

	// Fields    

	private long payVpfId;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HrPayVpfDtlsHstId() {
	}

	/** full constructor */
	public HrPayVpfDtlsHstId(long payVpfId, Integer trnCounter) {
		this.payVpfId = payVpfId;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public long getPayVpfId() {
		return this.payVpfId;
	}

	public void setPayVpfId(long payVpfId) {
		this.payVpfId = payVpfId;
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
		if (!(other instanceof HrPayVpfDtlsHstId))
			return false;
		HrPayVpfDtlsHstId castOther = (HrPayVpfDtlsHstId) other;

		return ((new Long(this.getPayVpfId()) == castOther.getPayVpfId()) || (new Long(this
				.getPayVpfId()) != null
				&& new Long(castOther.getPayVpfId()) != null && new Long(this.getPayVpfId())
				.equals(castOther.getPayVpfId())))
				&& (this.getTrnCounter() == castOther.getTrnCounter());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (new Long(getPayVpfId()) == null ? 0 : new Long(this.getPayVpfId()).hashCode());
		result = 37 * result + (int) this.getTrnCounter();
		return result;
	}

}
