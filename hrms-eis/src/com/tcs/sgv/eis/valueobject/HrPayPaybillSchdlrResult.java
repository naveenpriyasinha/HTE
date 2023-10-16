package com.tcs.sgv.eis.valueobject;

import java.util.Date;

public class HrPayPaybillSchdlrResult  implements java.io.Serializable{

	private long ddoCode; 
	private long billNo;
	private long locid; 
	private long id;
	private long month;
	private long year;
	private long threadId;
	private Date createdDate;
	private Character  successFlag;
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public long getDdoCode() {
		return ddoCode;
	}
	public void setDdoCode(long ddoCode) {
		this.ddoCode = ddoCode;
	}
	public long getBillNo() {
		return billNo;
	}
	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}
	public long getLocid() {
		return locid;
	}
	public void setLocid(long locid) {
		this.locid = locid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMonth() {
		return month;
	}
	public void setMonth(long month) {
		this.month = month;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	public long getThreadId() {
		return threadId;
	}
	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Character getSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(Character successFlag) {
		this.successFlag = successFlag;
	}
	
}
