package com.tcs.sgv.billproc.counter.valueobject;

// Generated Jul 16, 2007 7:41:45 PM by Hibernate Tools 3.2.0.beta8


/**
 * 
 * This is Hst Org Token Status Id tables hibernate generated vo
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History 
 * ===================== 
 * Bhavik 23-Oct-2007 Made changes for  code formatting
 */
public class HstOrgTokenStatusId implements java.io.Serializable {

	// Fields    

	private Long tokenId;

	private Integer trnCounter;

	// Constructors

	/** default constructor */
	public HstOrgTokenStatusId() {
	}

	/** full constructor */
	public HstOrgTokenStatusId(Long tokenId, Integer trnCounter) {
		this.tokenId = tokenId;
		this.trnCounter = trnCounter;
	}

	// Property accessors
	public Long getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
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
		if (!(other instanceof HstOrgTokenStatusId))
			return false;
		HstOrgTokenStatusId castOther = (HstOrgTokenStatusId) other;

		return ((this.getTokenId() == castOther.getTokenId()) || (this
				.getTokenId() != null
				&& castOther.getTokenId() != null && this.getTokenId().equals(
				castOther.getTokenId())))
				&& (this.getTrnCounter() == castOther.getTrnCounter());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTokenId() == null ? 0 : this.getTokenId().hashCode());
		result = 37 * result + (int) this.getTrnCounter();
		return result;
	}

}
