package com.tcs.sgv.eis.service;

public class CustomBulkAllowances {
	
	String edit;
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	private long empId;
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	private String employeeName;
	private double existingAmount;
	private double newAmount;
	private double difference;
	private long hrEisEmpId;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public double getExistingAmount() {
		return existingAmount;
	}
	public void setExistingAmount(double existingAmount) {
		this.existingAmount = existingAmount;
	}
	public double getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(double newAmount) {
		this.newAmount = newAmount;
	}
	public double getDifference() {
		return difference;
	}
	public void setDifference(double difference) {
		this.difference = difference;
	}
	public long getHrEisEmpId()
	{
		return hrEisEmpId;
	}
	public void setHrEisEmpId(long hrEisEmpId)
	{
		this.hrEisEmpId = hrEisEmpId;
	}

}
