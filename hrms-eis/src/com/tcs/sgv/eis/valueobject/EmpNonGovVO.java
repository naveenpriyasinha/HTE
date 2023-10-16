package com.tcs.sgv.eis.valueobject;

public class EmpNonGovVO {

	private long nonGovId;
	private long empId;
	private int deducCode;
	private long amount;
	//Added By roshan for paybill NGR 
	private String otherRecType;
	public String getOtherRecType() {
		return otherRecType;
	}
	public void setOtherRecType(String otherRecType) {
		this.otherRecType = otherRecType;
	}
	/**
	 * @return the nonGovId
	 */
	public long getNonGovId() {
		return nonGovId;
	}
	/**
	 * @param nonGovId the nonGovId to set
	 */
	public void setNonGovId(long nonGovId) {
		this.nonGovId = nonGovId;
	}
	/**
	 * @return the empId
	 */
	public long getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	/**
	 * @return the deducCode
	 */
	public int getDeducCode() {
		return deducCode;
	}
	/**
	 * @param deducCode the deducCode to set
	 */
	public void setDeducCode(int deducCode) {
		this.deducCode = deducCode;
	}
	/**
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
}
