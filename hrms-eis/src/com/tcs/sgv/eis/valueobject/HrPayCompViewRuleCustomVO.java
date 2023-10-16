package com.tcs.sgv.eis.valueobject;




@SuppressWarnings("serial")
public class HrPayCompViewRuleCustomVO implements java.io.Serializable {

	 	private long ruleGrpId;
		private String deptName;
	 	private String locationName;
	 	private String className;
	 	private String empCatgryName;
	 	private String genderName;
	 	private String desgnName;
	 	private String postTypeName;
	 	private String phyChallenged;
	 	private String scaleLowLmtVal;
	 	private String scaleUprLmtVal;
	 	private String basicLowLmtVal;
	 	private String basicUprLmtVal;
	 	private String gpLowLmtVal;
	 	private String gpUprLmtVal;
	 	private String ruleAmount;
	 	private String payCommissionName;
	 	private Integer status;
	 	private String cityName;
	 	private String quarterTypeName;
		private String ruleFormulaDisplay;
		private Integer returnType;
		private String rulePercentage;
		
		private String grossLowLmtVal;
	 	private String grossUprLmtVal;
		
		//***************
		private String deptCode;
	 	private String locationCode;
	 	private String classCode;
	 	private int empCatgryCode;
	 	private int genderCode;
	 	private String desgnCode;
	 	private int postTypeCode;
	 	private int phyChallengedCode;
	 	private String cityCode;
	 	private long quarterTypeCode;
	 	private int payCommissionValue;		
		private String dojYear;
		private int dojYearId;

	
		public int getDojYearId() {
			return dojYearId;
		}
		public void setDojYearId(int dojYearId) {
			this.dojYearId = dojYearId;
		}
		
		public String getDojYear() {
			return dojYear;
		}
		public void setDojYear(String dojYear) {
			this.dojYear = dojYear;
		}
		public String getRulePercentage() {
			return rulePercentage;
		}
		public void setRulePercentage(String rulePercentage) {
			this.rulePercentage = rulePercentage;
		}
		public Integer getReturnType() {
			return returnType;
		}
		public void setReturnType(Integer returnType) {
			this.returnType = returnType;
		}
		public long getRuleGrpId() {
			return ruleGrpId;
		}
		public void setRuleGrpId(long ruleGrpId) {
			this.ruleGrpId = ruleGrpId;
		}
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		public String getLocationName() {
			return locationName;
		}
		public void setLocationName(String locationName) {
			this.locationName = locationName;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getEmpCatgryName() {
			return empCatgryName;
		}
		public void setEmpCatgryName(String empCatgryName) {
			this.empCatgryName = empCatgryName;
		}
		public String getGenderName() {
			return genderName;
		}
		public void setGenderName(String genderName) {
			this.genderName = genderName;
		}
		public String getDesgnName() {
			return desgnName;
		}
		public void setDesgnName(String desgnName) {
			this.desgnName = desgnName;
		}
		public String getPostTypeName() {
			return postTypeName;
		}
		public void setPostTypeName(String postTypeName) {
			this.postTypeName = postTypeName;
		}
		public String getPhyChallenged() {
			return phyChallenged;
		}
		public void setPhyChallenged(String phyChallenged) {
			this.phyChallenged = phyChallenged;
		}
		public String getScaleLowLmtVal() {
			return scaleLowLmtVal;
		}
		public void setScaleLowLmtVal(String scaleLowLmtVal) {
			this.scaleLowLmtVal = scaleLowLmtVal;
		}
		public String getScaleUprLmtVal() {
			return scaleUprLmtVal;
		}
		public void setScaleUprLmtVal(String scaleUprLmtVal) {
			this.scaleUprLmtVal = scaleUprLmtVal;
		}
		public String getBasicLowLmtVal() {
			return basicLowLmtVal;
		}
		public void setBasicLowLmtVal(String basicLowLmtVal) {
			this.basicLowLmtVal = basicLowLmtVal;
		}
		public String getBasicUprLmtVal() {
			return basicUprLmtVal;
		}
		public void setBasicUprLmtVal(String basicUprLmtVal) {
			this.basicUprLmtVal = basicUprLmtVal;
		}
		public String getGpLowLmtVal() {
			return gpLowLmtVal;
		}
		public void setGpLowLmtVal(String gpLowLmtVal) {
			this.gpLowLmtVal = gpLowLmtVal;
		}
		public String getGpUprLmtVal() {
			return gpUprLmtVal;
		}
		public void setGpUprLmtVal(String gpUprLmtVal) {
			this.gpUprLmtVal = gpUprLmtVal;
		}
		public String getRuleAmount() {
			return ruleAmount;
		}
		public void setRuleAmount(String ruleAmount) {
			this.ruleAmount = ruleAmount;
		}
		public String getPayCommissionName() {
			return payCommissionName;
		}
		public void setPayCommissionName(String payCommissionName) {
			this.payCommissionName = payCommissionName;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		
		public String getRuleFormulaDisplay() {
			return ruleFormulaDisplay;
		}
		public void setRuleFormulaDisplay(String ruleFormulaDisplay) {
			this.ruleFormulaDisplay = ruleFormulaDisplay;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getQuarterTypeName() {
			return quarterTypeName;
		}
		public void setQuarterTypeName(String quarterTypeName) {
			this.quarterTypeName = quarterTypeName;
		}
		public String getDeptCode() {
			return deptCode;
		}
		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}
		public String getLocationCode() {
			return locationCode;
		}
		public void setLocationCode(String locationCode) {
			this.locationCode = locationCode;
		}
		public String getClassCode() {
			return classCode;
		}
		public void setClassCode(String classCode) {
			this.classCode = classCode;
		}
		public int getEmpCatgryCode() {
			return empCatgryCode;
		}
		public void setEmpCatgryCode(int empCatgryCode) {
			this.empCatgryCode = empCatgryCode;
		}
		public int getGenderCode() {
			return genderCode;
		}
		public void setGenderCode(int genderCode) {
			this.genderCode = genderCode;
		}
		public String getDesgnCode() {
			return desgnCode;
		}
		public void setDesgnCode(String desgnCode) {
			this.desgnCode = desgnCode;
		}
		public int getPostTypeCode() {
			return postTypeCode;
		}
		public void setPostTypeCode(int postTypeCode) {
			this.postTypeCode = postTypeCode;
		}
		public int getPhyChallengedCode() {
			return phyChallengedCode;
		}
		public void setPhyChallengedCode(int phyChallengedCode) {
			this.phyChallengedCode = phyChallengedCode;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public long getQuarterTypeCode() {
			return quarterTypeCode;
		}
		public void setQuarterTypeCode(long quarterTypeCode) {
			this.quarterTypeCode = quarterTypeCode;
		}
		public int getPayCommissionValue() {
			return payCommissionValue;
		}
		public void setPayCommissionValue(int payCommissionValue) {
			this.payCommissionValue = payCommissionValue;
		}
		public String getGrossLowLmtVal() {
			return grossLowLmtVal;
		}
		public void setGrossLowLmtVal(String grossLowLmtVal) {
			this.grossLowLmtVal = grossLowLmtVal;
		}
		public String getGrossUprLmtVal() {
			return grossUprLmtVal;
		}
		public void setGrossUprLmtVal(String grossUprLmtVal) {
			this.grossUprLmtVal = grossUprLmtVal;
		}
		
		
}	  
	
	
	

