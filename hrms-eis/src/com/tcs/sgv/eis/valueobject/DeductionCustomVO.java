package com.tcs.sgv.eis.valueobject;

public class DeductionCustomVO {

	private long deducId;
	private String deducDesc;
	private double deductionVal;
	private long allwDedId;

	public long getAllwDedId()
	{
		return allwDedId;
	}
	public void setAllwDedId(long allwDedId)
	{
		this.allwDedId = allwDedId;
	}
	/**
	 * @return the deductionVal
	 */
	public double getDeductionVal() {
		return deductionVal;
	}
	/**
	 * @param deductionVal the deductionVal to set
	 */
	public void setDeductionVal(double deductionVal) {
		this.deductionVal = deductionVal;
	}
	/**
	 * @return the deducId
	 */
	public long getDeducId() {
		return deducId;
	}
	/**
	 * @param deducId the deducId to set
	 */
	public void setDeducId(long deducId) {
		this.deducId = deducId;
	}
	/**
	 * @return the deducDesc
	 */
	public String getDeducDesc() {
		return deducDesc;
	}
	/**
	 * @param deducDesc the deducDesc to set
	 */
	public void setDeducDesc(String deducDesc) {
		this.deducDesc = deducDesc;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer("DeductionCustomVO :");
		sb.append(" deducId 		"+deducId);
		sb.append(" deducDesc 	"+deducDesc);
		sb.append(" deductionVal "+deductionVal+"\n");
		return sb.toString();
	}
}
