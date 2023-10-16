package com.tcs.sgv.stamp.valueobject;

public class MstStampGroupVO implements java.io.Serializable 
{
	private String srNo;
	private String grpID;
	private String stampGroupCode;
	private String stampGroupName;
	private String stampBaseGroupName;
	private String description;
	private String majorHead;
	private String subMajorHead;
	private String minorHead;
	private String subHead;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMajorHead() {
		return majorHead;
	}
	public void setMajorHead(String majorHead) {
		this.majorHead = majorHead;
	}
	public String getMinorHead() {
		return minorHead;
	}
	public void setMinorHead(String minorHead) {
		this.minorHead = minorHead;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getStampBaseGroupName() {
		return stampBaseGroupName;
	}
	public void setStampBaseGroupName(String stampBaseGroupName) {
		this.stampBaseGroupName = stampBaseGroupName;
	}
	public String getStampGroupCode() {
		return stampGroupCode;
	}
	public void setStampGroupCode(String stampGroupCode) {
		this.stampGroupCode = stampGroupCode;
	}
	public String getStampGroupName() {
		return stampGroupName;
	}
	public void setStampGroupName(String stampGroupName) {
		this.stampGroupName = stampGroupName;
	}
	public String getSubHead() {
		return subHead;
	}
	public void setSubHead(String subHead) {
		this.subHead = subHead;
	}
	public String getSubMajorHead() {
		return subMajorHead;
	}
	public void setSubMajorHead(String subMajorHead) {
		this.subMajorHead = subMajorHead;
	}
	public String getGrpID() {
		return grpID;
	}
	public void setGrpID(String grpID) {
		this.grpID = grpID;
	}
	
}
