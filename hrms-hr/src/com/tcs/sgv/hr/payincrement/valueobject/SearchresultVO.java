package com.tcs.sgv.hr.payincrement.valueobject;


public class SearchresultVO {

	private long payfixid;
	private String empPrefix;
	private String empFName;
	private String empMName;
	private String empLName;
	private String designation;
	private String presentpayscale;
	private String revisedpayscale;
	private String reasonPayfixed;
	private String dateofPayfixation;
	
	public SearchresultVO()
	{
			}
	public long getPayfixid() {
		return payfixid;
	}
	public void setPayfixid(long payfixid) {
		this.payfixid = payfixid;
	}
	public String getEmpPrefix() {
		return empPrefix;
	}
	public void setEmpPrefix(String empPrefix) {
		this.empPrefix = empPrefix;
	}
	public String getEmpFName() {
		return empFName;
	}
	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}
	public String getEmpMName() {
		return empMName;
	}
	public void setEmpMName(String empMName) {
		this.empMName = empMName;
	}
	public String getEmpLName() {
		return empLName;
	}
	public void setEmpLName(String empLName) {
		this.empLName = empLName;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPresentpayscale() {
		return presentpayscale;
	}
	public void setPresentpayscale(String presentpayscale) {
		this.presentpayscale = presentpayscale;
	}
	public String getRevisedpayscale() {
		return revisedpayscale;
	}
	public void setRevisedpayscale(String revisedpayscale) {
		this.revisedpayscale = revisedpayscale;
	}
	public String getReasonPayfixed() {
		return reasonPayfixed;
	}
	public void setReasonPayfixed(String reasonPayfixed) {
		this.reasonPayfixed = reasonPayfixed;
	}
	public String getDateofPayfixation() {
		return dateofPayfixation;
	}
	public void setDateofPayfixation(String dateofPayfixation) {
		this.dateofPayfixation = dateofPayfixation;
	}
}
