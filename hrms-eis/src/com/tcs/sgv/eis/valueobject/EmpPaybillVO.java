package com.tcs.sgv.eis.valueobject;

import java.util.Date;

public class EmpPaybillVO {
	
	String empLname;
	public String getEmpLname()
	{
		return empLname;
	}
	public void setEmpLname(String empLname)
	{
		this.empLname = empLname;
	}
	long orgEmpId;
	long eisEmpId;
	long basicAmt;
	long incomeTax;
	long desigId;
	long gradeId;
	long scaleId;
	long scaleStartAmt;
	long scaleEndAmt;
	String dcpsOrGPF;
	long postId;
	long userId;
	long payCommissionId;
	long empType;
	long gradeCode;
	int isAvailedHRA;
	Date empDOB;
	Date empSrvcExp;
	Date empDOJ;
	String isPhyHandicapped;
	long gradePay;
	long empCity;
	long qtrRentAmt;
	long postPSRNo;
	long otherDtlsId;
	long gisGradeId;
	long gisGradeCode;
	String postCityClass;
	String accNo;
	String ifscCode;
	String empClassGroup;
	/*Added by Naveen*/
	public String getEmpClassGroup() {
		return empClassGroup;
	}
	public void setEmpClassGroup(String empClassGroup) {
		this.empClassGroup = empClassGroup;
	}
	/*Added By shivram*/
	public long sevenBasicAmt;
	
	public long getSevenBasicAmt() {
		return sevenBasicAmt;
	}
	public void setSevenBasicAmt(long sevenBasicAmt) {
		this.sevenBasicAmt = sevenBasicAmt;
	}
	/*---Ended By Shivram*/
	


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
	
	/**
	 * @return the orgEmpId
	 */
	public long getOrgEmpId() {
		return orgEmpId;
	}
	/**
	 * @param orgEmpId the orgEmpId to set
	 */
	public void setOrgEmpId(long orgEmpId) {
		this.orgEmpId = orgEmpId;
	}
	/**
	 * @return the eisEmpId
	 */
	public long getEisEmpId() {
		return eisEmpId;
	}
	/**
	 * @param eisEmpId the eisEmpId to set
	 */
	public void setEisEmpId(long eisEmpId) {
		this.eisEmpId = eisEmpId;
	}
	/**
	 * @return the basicAmt
	 */
	public long getBasicAmt() {
		return basicAmt;
	}
	/**
	 * @param basicAmt the basicAmt to set
	 */
	public void setBasicAmt(long basicAmt) {
		this.basicAmt = basicAmt;
	}
	/**
	 * @return the incomeTax
	 */
	public long getIncomeTax() {
		return incomeTax;
	}
	/**
	 * @param incomeTax the incomeTax to set
	 */
	public void setIncomeTax(long incomeTax) {
		this.incomeTax = incomeTax;
	}
	/**
	 * @return the desigId
	 */
	public long getDesigId() {
		return desigId;
	}
	/**
	 * @param desigId the desigId to set
	 */
	public void setDesigId(long desigId) {
		this.desigId = desigId;
	}
	/**
	 * @return the gradeId
	 */
	public long getGradeId() {
		return gradeId;
	}
	/**
	 * @param gradeId the gradeId to set
	 */
	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}
	/**
	 * @return the scaleId
	 */
	public long getScaleId() {
		return scaleId;
	}
	/**
	 * @param scaleId the scaleId to set
	 */
	public void setScaleId(long scaleId) {
		this.scaleId = scaleId;
	}
	/**
	 * @return the scaleStartAmt
	 */
	public long getScaleStartAmt() {
		return scaleStartAmt;
	}
	/**
	 * @param scaleStartAmt the scaleStartAmt to set
	 */
	public void setScaleStartAmt(long scaleStartAmt) {
		this.scaleStartAmt = scaleStartAmt;
	}
	/**
	 * @return the scaleEndAmt
	 */
	public long getScaleEndAmt() {
		return scaleEndAmt;
	}
	/**
	 * @param scaleEndAmt the scaleEndAmt to set
	 */
	public void setScaleEndAmt(long scaleEndAmt) {
		this.scaleEndAmt = scaleEndAmt;
	}
	/**
	 * @return the dcpsOrGPF
	 */
	public String getDcpsOrGPF() {
		return dcpsOrGPF;
	}
	/**
	 * @param dcpsOrGPF the dcpsOrGPF to set
	 */
	public void setDcpsOrGPF(String dcpsOrGPF) {
		this.dcpsOrGPF = dcpsOrGPF;
	}
	/**
	 * @return the postId
	 */
	public long getPostId() {
		return postId;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(long postId) {
		this.postId = postId;
	}
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @return the payCommissionId
	 */
	public long getPayCommissionId() {
		return payCommissionId;
	}
	/**
	 * @param payCommissionId the payCommissionId to set
	 */
	public void setPayCommissionId(long payCommissionId) {
		this.payCommissionId = payCommissionId;
	}
	/**
	 * @return the empType
	 */
	public long getEmpType() {
		return empType;
	}
	/**
	 * @param empType the empType to set
	 */
	public void setEmpType(long empType) {
		this.empType = empType;
	}
	/**
	 * @return the gradeCode
	 */
	public long getGradeCode() {
		return gradeCode;
	}
	/**
	 * @param gradeCode the gradeCode to set
	 */
	public void setGradeCode(long gradeCode) {
		this.gradeCode = gradeCode;
	}
	/**
	 * @return the isAvailedHRA
	 */
	public int getIsAvailedHRA() {
		return isAvailedHRA;
	}
	/**
	 * @param isAvailedHRA the isAvailedHRA to set
	 */
	public void setIsAvailedHRA(int isAvailedHRA) {
		this.isAvailedHRA = isAvailedHRA;
	}
	/**
	 * @return the empDOB
	 */
	public Date getEmpDOB() {
		return empDOB;
	}
	/**
	 * @param empDOB the empDOB to set
	 */
	public void setEmpDOB(Date empDOB) {
		this.empDOB = empDOB;
	}
	/**
	 * @return the empSrvcExp
	 */
	public Date getEmpSrvcExp() {
		return empSrvcExp;
	}
	/**
	 * @param empSrvcExp the empSrvcExp to set
	 */
	public void setEmpSrvcExp(Date empSrvcExp) {
		this.empSrvcExp = empSrvcExp;
	}
	/**
	 * @return the empDOJ
	 */
	public Date getEmpDOJ() {
		return empDOJ;
	}
	/**
	 * @param empDOJ the empDOJ to set
	 */
	public void setEmpDOJ(Date empDOJ) {
		this.empDOJ = empDOJ;
	}
	/**
	 * @return the isPhyHandicapped
	 */
	public String getIsPhyHandicapped() {
		return isPhyHandicapped;
	}
	/**
	 * @param isPhyHandicapped the isPhyHandicapped to set
	 */
	public void setIsPhyHandicapped(String isPhyHandicapped) {
		this.isPhyHandicapped = isPhyHandicapped;
	}
	/**
	 * @return the gradePay
	 */
	public long getGradePay() {
		return gradePay;
	}
	/**
	 * @param gradePay the gradePay to set
	 */
	public void setGradePay(long gradePay) {
		this.gradePay = gradePay;
	}
	/**
	 * @return the empCity
	 */
	public long getEmpCity() {
		return empCity;
	}
	/**
	 * @param empCity the empCity to set
	 */
	public void setEmpCity(long empCity) {
		this.empCity = empCity;
	}
	/**
	 * @return the qtrRentAmt
	 */
	public long getQtrRentAmt() {
		return qtrRentAmt;
	}
	/**
	 * @param qtrRentAmt the qtrRentAmt to set
	 */
	public void setQtrRentAmt(long qtrRentAmt) {
		this.qtrRentAmt = qtrRentAmt;
	}
	/**
	 * @return the postPSRNo
	 */
	public long getPostPSRNo() {
		return postPSRNo;
	}
	/**
	 * @param postPSRNo the postPSRNo to set
	 */
	public void setPostPSRNo(long postPSRNo) {
		this.postPSRNo = postPSRNo;
	}
	/**
	 * @return the otherDtlsId
	 */
	public long getOtherDtlsId() {
		return otherDtlsId;
	}
	/**
	 * @param otherDtlsId the otherDtlsId to set
	 */
	public void setOtherDtlsId(long otherDtlsId) {
		this.otherDtlsId = otherDtlsId;
	}
	/**
	 * @return the gisGradeId
	 */
	public long getGisGradeId() {
		return gisGradeId;
	}
	/**
	 * @param gisGradeId the gisGradeId to set
	 */
	public void setGisGradeId(long gisGradeId) {
		this.gisGradeId = gisGradeId;
	}
	/**
	 * @return the gisGradeCode
	 */
	public long getGisGradeCode() {
		return gisGradeCode;
	}
	/**
	 * @param gisGradeCode the gisGradeCode to set
	 */
	public void setGisGradeCode(long gisGradeCode) {
		this.gisGradeCode = gisGradeCode;
	}
	/**
	 * @return the postCityClass
	 */
	public String getPostCityClass() {
		return postCityClass;
	}
	/**
	 * @param postCityClass the postCityClass to set
	 */
	public void setPostCityClass(String postCityClass) {
		this.postCityClass = postCityClass;
	}
	
	
}
