package com.tcs.sgv.nps.valueobject;

public class EmpDtlsCustomVO {
	private long empId;
	private String empName;
	private long empType;
	private String dcpsorgpf;
	
	
	private String EmpSevaarthId;
	private String EmpEmployeeName;
	private String EmpPRANNO;
	private String EmpServiceEndDate;
	
	public String getEmpSevaarthId() {
		return EmpSevaarthId;
	}
	public void setEmpSevaarthId(String empSevaarthId) {
		EmpSevaarthId = empSevaarthId;
	}
	public String getEmpEmployeeName() {
		return EmpEmployeeName;
	}
	public void setEmpEmployeeName(String empEmployeeName) {
		EmpEmployeeName = empEmployeeName;
	}
	public String getEmpPRANNO() {
		return EmpPRANNO;
	}
	public void setEmpPRANNO(String empPRANNO) {
		EmpPRANNO = empPRANNO;
	}
	public String getEmpServiceEndDate() {
		return EmpServiceEndDate;
	}
	public void setEmpServiceEndDate(String empServiceEndDate) {
		EmpServiceEndDate = empServiceEndDate;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public long getEmpType() {
		return empType;
	}
	public void setEmpType(long empType) {
		this.empType = empType;
	}
	public String getDcpsorgpf() {
		return dcpsorgpf;
	}
	public void setDcpsorgpf(String dcpsorgpf) {
		this.dcpsorgpf = dcpsorgpf;
	}
}
