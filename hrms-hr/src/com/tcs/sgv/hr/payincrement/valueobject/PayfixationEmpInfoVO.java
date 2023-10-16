package com.tcs.sgv.hr.payincrement.valueobject;



import java.util.Date;

public class PayfixationEmpInfoVO {

	private String empName;
	private String address;
	private String designation;
	private Long salary;
	private Date doj;
	private Date dor;
	private Long empId;
	private Long desigid;
	public PayfixationEmpInfoVO()
	{
			}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Date getDor() {
		return dor;
	}
	public void setDor(Date dor) {
		this.dor = dor;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public Long getDesigid() {
		return desigid;
	}
	public void setDesigid(Long desigid) {
		this.desigid = desigid;
	}
	
}
