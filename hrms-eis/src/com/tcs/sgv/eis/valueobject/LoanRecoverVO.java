package com.tcs.sgv.eis.valueobject;

public class LoanRecoverVO {
	private long empLoanId;
	private int recoveredInst;
	private long recoveredAmount;
	private int oddInstNo;
	private long oddInstAmount;
	
	/**
	 * @return the empLoanId
	 */
	public long getEmpLoanId() {
		return empLoanId;
	}
	/**
	 * @param empLoanId the empLoanId to set
	 */
	public void setEmpLoanId(long empLoanId) {
		this.empLoanId = empLoanId;
	}
	/**
	 * @return the recoveredInst
	 */
	public int getRecoveredInst() {
		return recoveredInst;
	}
	/**
	 * @param recoveredInst the recoveredInst to set
	 */
	public void setRecoveredInst(int recoveredInst) {
		this.recoveredInst = recoveredInst;
	}
	/**
	 * @return the recoveredAmount
	 */
	public long getRecoveredAmount() {
		return recoveredAmount;
	}
	/**
	 * @param recoveredAmount the recoveredAmount to set
	 */
	public void setRecoveredAmount(long recoveredAmount) {
		this.recoveredAmount = recoveredAmount;
	}
	/**
	 * @return the oddInstNo
	 */
	public int getOddInstNo() {
		return oddInstNo;
	}
	/**
	 * @param oddInstNo the oddInstNo to set
	 */
	public void setOddInstNo(int oddInstNo) {
		this.oddInstNo = oddInstNo;
	}
	/**
	 * @return the oddInstAmount
	 */
	public long getOddInstAmount() {
		return oddInstAmount;
	}
	/**
	 * @param oddInstAmount the oddInstAmount to set
	 */
	public void setOddInstAmount(long oddInstAmount) {
		this.oddInstAmount = oddInstAmount;
	}
	
	

}
