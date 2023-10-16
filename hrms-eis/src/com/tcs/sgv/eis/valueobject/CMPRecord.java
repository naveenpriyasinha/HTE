package com.tcs.sgv.eis.valueobject;

import java.util.Date;

public class CMPRecord {

	private String bulkUploadFlag;
	private String billId;
	private String treasuryCode;
	private String ddoCode;
	private String benefName;
	private String accNo;
	private String ifscCode;
	private String micrCode;
	private String accType;
	private String amount; 
	private String paymentRefNo;
	private String payBydate;
	private String schemeCode;
	private String ddoBillNo;
	private String authNo;
	private String toBilNo;
	private String billDate;
	private String narration;
	private String noOfPayees;
	private String billNetAmt;
	private String emailId;
	private String cellNo;
	
	public String getBulkUploadFlag() {
		return bulkUploadFlag;
	}
	public void setBulkUploadFlag(String bulkUploadFlag) {
		this.bulkUploadFlag = bulkUploadFlag;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getTreasuryCode() {
		return treasuryCode;
	}
	public void setTreasuryCode(String treasuryCode) {
		this.treasuryCode = treasuryCode;
	}
	public String getDdoCode() {
		return ddoCode;
	}
	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}
	public String getBenefName() {
		return benefName;
	}
	public void setBenefName(String benefName) {
		this.benefName = benefName;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPaymentRefNo() {
		return paymentRefNo;
	}
	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}
	public String getPayBydate() {
		return payBydate;
	}
	public void setPayBydate(String payBydate) {
		this.payBydate = payBydate;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getDdoBillNo() {
		return ddoBillNo;
	}
	public void setDdoBillNo(String ddoBillNo) {
		this.ddoBillNo = ddoBillNo;
	}
	public String getAuthNo() {
		return authNo;
	}
	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}
	public String getToBilNo() {
		return toBilNo;
	}
	public void setToBilNo(String toBilNo) {
		this.toBilNo = toBilNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getNoOfPayees() {
		return noOfPayees;
	}
	public void setNoOfPayees(String noOfPayees) {
		this.noOfPayees = noOfPayees;
	}
	public String getBillNetAmt() {
		return billNetAmt;
	}
	public void setBillNetAmt(String billNetAmt) {
		this.billNetAmt = billNetAmt;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String toString(){
	 return bulkUploadFlag+billId+treasuryCode+ddoCode+benefName+accNo+ifscCode+micrCode+accType+amount+paymentRefNo+payBydate+schemeCode+ddoBillNo+authNo+toBilNo+billDate+narration+noOfPayees+billNetAmt+emailId+cellNo;
	}
}
