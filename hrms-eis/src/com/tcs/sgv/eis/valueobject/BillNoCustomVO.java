package com.tcs.sgv.eis.valueobject;

public class BillNoCustomVO implements java.io.Serializable 
{

	private long billId;
	private long schemeId;
	private long subHeadCode;
	private String demandNo;
	private long mjrHead;
	private long subMjrHead;
	private long mnrHead;
	private long subHead;
	private String charged;
	private String planned;
	
	
	public long getBillId() {
		return billId;
	}
	public void setBillId(long billId) {
		this.billId = billId;
	}
	public long getSubHeadCode() {
		return subHeadCode;
	}
	public void setSubHeadCode(long subHeadCode) {
		this.subHeadCode = subHeadCode;
	}
	public String getDemandNo() {
		return demandNo;
	}
	public void setDemandNo(String demandNo) {
		this.demandNo = demandNo;
	}
	public long getMjrHead() {
		return mjrHead;
	}
	public void setMjrHead(long mjrHead) {
		this.mjrHead = mjrHead;
	}
	public long getMnrHead() {
		return mnrHead;
	}
	public void setMnrHead(long mnrHead) {
		this.mnrHead = mnrHead;
	}
	public long getSubHead() {
		return subHead;
	}
	public void setSubHead(long subHead) {
		this.subHead = subHead;
	}
	public long getSubMjrHead() {
		return subMjrHead;
	}
	public void setSubMjrHead(long subMjrHead) {
		this.subMjrHead = subMjrHead;
	}
	/**
	 * @return the schemeId
	 */
	public long getSchemeId() {
		return schemeId;
	}
	/**
	 * @param schemeId the schemeId to set
	 */
	public void setSchemeId(long schemeId) {
		this.schemeId = schemeId;
	}
	/**
	 * @return the charged
	 */
	public String getCharged() {
		return charged;
	}
	/**
	 * @param charged the charged to set
	 */
	public void setCharged(String charged) {
		this.charged = charged;
	}
	/**
	 * @return the planned
	 */
	public String getPlanned() {
		return planned;
	}
	/**
	 * @param planned the planned to set
	 */
	public void setPlanned(String planned) {
		this.planned = planned;
	}
	
	
	
	
}