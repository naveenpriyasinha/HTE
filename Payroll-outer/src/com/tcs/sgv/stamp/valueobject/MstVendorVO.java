package com.tcs.sgv.stamp.valueobject;

import java.io.Serializable;
import java.math.BigDecimal;

public class MstVendorVO implements Serializable {

	private BigDecimal venId;
	private int venCode;
	private String venName;
	private String venAdd;
	private String venRegNo;
	private String placeOfBusiness;
	private String discAllowed;
	private String active;
	private String startDate;
	private String endDate;
	
	
	public BigDecimal getVenId() {
		return venId;
	}
	public void setVenId(BigDecimal venId) {
		this.venId = venId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getDiscAllowed() {
		return discAllowed;
	}
	public void setDiscAllowed(String discAllowed) {
		this.discAllowed = discAllowed;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPlaceOfBusiness() {
		return placeOfBusiness;
	}
	public void setPlaceOfBusiness(String placeOfBusiness) {
		this.placeOfBusiness = placeOfBusiness;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getVenAdd() {
		return venAdd;
	}
	public void setVenAdd(String venAdd) {
		this.venAdd = venAdd;
	}
	public int getVenCode() {
		return venCode;
	}
	public void setVenCode(int venCode) {
		this.venCode = venCode;
	}
	public String getVenName() {
		return venName;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public String getVenRegNo() {
		return venRegNo;
	}
	public void setVenRegNo(String venRegNo) {
		this.venRegNo = venRegNo;
	}
	
	
}
