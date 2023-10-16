package com.tcs.sgv.eis.employeeInfo.valueobject;

import java.util.Date;

public class EmpOtherDetailsVO {
	
	
	private String EmpName;
	private Date DateOfBirth;
	private String Addess;
	private String Designation;
	private long salary;
	private Date DateOfJoin;
	private long userId;
	private long empId;
	private long DesigId;
	private long postId;
	private long scaleStart;
	private long scaleInc;
	private long scaleEnd;
	private String cityName;
	private long AddressId;
	private long empPhotoId;
	private String empPostName;
	private long photoSrNo;
	private long depId;
	public long getDepId() {
		return depId;
	}
	public void setDepId(long depId) {
		this.depId = depId;
	}
	public long getPhotoSrNo() {
		return photoSrNo;
	}
	public void setPhotoSrNo(long photoSrNo) {
		this.photoSrNo = photoSrNo;
	}
	//private String desigCode;
	public String getAddess() {
		return Addess;
	}
	public void setAddess(String addess) {
		Addess = addess;
	}
	public long getAddressId() {
		return AddressId;
	}
	public void setAddressId(long addressId) {
		AddressId = addressId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Date getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public Date getDateOfJoin() {
		return DateOfJoin;
	}
	public void setDateOfJoin(Date dateOfJoin) {
		DateOfJoin = dateOfJoin;
	}
	public long getDesigId() {
		return DesigId;
	}
	public void setDesigId(long desigId) {
		DesigId = desigId;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public long getEmpPhotoId() {
		return empPhotoId;
	}
	public void setEmpPhotoId(long empPhotoId) {
		this.empPhotoId = empPhotoId;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public long getScaleEnd() {
		return scaleEnd;
	}
	public void setScaleEnd(long scaleEnd) {
		this.scaleEnd = scaleEnd;
	}
	public long getScaleInc() {
		return scaleInc;
	}
	public void setScaleInc(long scaleInc) {
		this.scaleInc = scaleInc;
	}
	public long getScaleStart() {
		return scaleStart;
	}
	public void setScaleStart(long scaleStart) {
		this.scaleStart = scaleStart;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getEmpPostName() {
		return empPostName;
	}
	public void setEmpPostName(String empPostName) {
		this.empPostName = empPostName;
	}
	
	
}
