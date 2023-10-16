package com.tcs.sgv.lcm.valueobject;

public class LcDivisionInformationVO {

	private String divisionId;
	private String departmentCode;
	private String districtCode;
	private String lc_order_id;
	private String lc_valid_from;
	private String opening_lc;
	private String division_name;
	private String department_name;
	private String district_name;
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getDivision_name() {
		return division_name;
	}
	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getLc_order_id() {
		return lc_order_id;
	}
	public void setLc_order_id(String lc_order_id) {
		this.lc_order_id = lc_order_id;
	}
	public String getLc_valid_from() {
		return lc_valid_from;
	}
	public void setLc_valid_from(String lc_valid_from) {
		this.lc_valid_from = lc_valid_from;
	}
	public String getOpening_lc() {
		return opening_lc;
	}
	public void setOpening_lc(String opening_lc) {
		this.opening_lc = opening_lc;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
}
