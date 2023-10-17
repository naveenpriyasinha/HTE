package com.tcs.sgv.eis.valueobject;
// Generated Jul 10, 2008 4:27:01 PM by Hibernate Tools 3.2.0.beta8

/**
 * HrEisEmpChgnameTxnId generated by hbm2java
 */
public class HrEisEmpChgnameTxnId implements java.io.Serializable {

	// Fields    

	private long reqId;

	private long langId;

	// Constructors

	/** default constructor */
	public HrEisEmpChgnameTxnId() {
	}

	/** full constructor */
	public HrEisEmpChgnameTxnId(long reqId, long langId) {
		this.reqId = reqId;
		this.langId = langId;
	}

	// Property accessors
	public long getReqId() {
		return this.reqId;
	}

	public void setReqId(long reqId) {
		this.reqId = reqId;
	}

	public long getLangId() {
		return this.langId;
	}

	public void setLangId(long langId) {
		this.langId = langId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HrEisEmpChgnameTxnId))
			return false;
		HrEisEmpChgnameTxnId castOther = (HrEisEmpChgnameTxnId) other;

		return (this.getReqId() == castOther.getReqId())
				&& (this.getLangId() == castOther.getLangId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getReqId();
		result = 37 * result + (int) this.getLangId();
		return result;
	}

}