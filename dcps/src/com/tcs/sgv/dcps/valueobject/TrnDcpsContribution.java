/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 19, 2011		Vihan Khatri								
 *******************************************************************************
 */

/**
 * Class Description - 
 *
 *
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0
 * Mar 19, 2011
 */
package com.tcs.sgv.dcps.valueobject;

import java.util.Date;

public class TrnDcpsContribution implements java.io.Serializable {

	private Long dcpsContributionId;
	private Long dcpsEmpId;
	private Long treasuryCode;
	private String ddoCode;
	private Long dcpsDdoBillGroupId;
	private String schemeCode;
	private String payCommission;
	private String typeOfPayment;
	private Long finYearId;
	private Long monthId;
	private Double basicPay;
	private Double DP;
	private Double DA;
	private Double contribution;
	private Long regStatus;
	private Long langId;
	private Long locId;
	private Long dbId;
	private Long createdPostId;
	private Long createdUserId;
	private Date createdDate;
	private Long updatedUserId;
	private Long updatedPostId;
	private Date updatedDate;
	private Date startDate;
	private Date endDate;
	private Long rltContriVoucherId;
	private Character employerContriFlag;
	private Long delayedFinYearId;
	private Long delayedMonthId;
	private Character status;
	private Long voucherNo;
	private Date voucherDate;
	/*NPS Contribution add by Naveen Priya Sinha*/
	private Double contributionNps;
	
	public Long getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(Long voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public Long getDcpsContributionId() {
		return dcpsContributionId;
	}

	public void setDcpsContributionId(Long dcpsContributionId) {
		this.dcpsContributionId = dcpsContributionId;
	}

	public Long getDcpsEmpId() {
		return dcpsEmpId;
	}

	public void setDcpsEmpId(Long dcpsEmpId) {
		this.dcpsEmpId = dcpsEmpId;
	}

	public Long getTreasuryCode() {
		return treasuryCode;
	}

	public void setTreasuryCode(Long treasuryCode) {
		this.treasuryCode = treasuryCode;
	}

	public String getDdoCode() {
		return ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public Long getDcpsDdoBillGroupId() {
		return dcpsDdoBillGroupId;
	}

	public void setDcpsDdoBillGroupId(Long dcpsDdoBillGroupId) {
		this.dcpsDdoBillGroupId = dcpsDdoBillGroupId;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getPayCommission() {
		return payCommission;
	}

	public void setPayCommission(String payCommission) {
		this.payCommission = payCommission;
	}

	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	public Long getFinYearId() {
		return finYearId;
	}

	public void setFinYearId(Long finYearId) {
		this.finYearId = finYearId;
	}

	public Long getMonthId() {
		return monthId;
	}

	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}

	public Double getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(Double basicPay) {
		this.basicPay = basicPay;
	}

	public Double getDP() {
		return DP;
	}

	public void setDP(Double dp) {
		DP = dp;
	}

	public Double getDA() {
		return DA;
	}

	public void setDA(Double da) {
		DA = da;
	}

	public Double getContribution() {
		return contribution;
	}

	public void setContribution(Double contribution) {
		this.contribution = contribution;
	}

	public Long getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(Long regStatus) {
		this.regStatus = regStatus;
	}

	public Long getLangId() {
		return langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
	}

	public Long getLocId() {
		return locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public Long getDbId() {
		return dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public Long getCreatedPostId() {
		return createdPostId;
	}

	public void setCreatedPostId(Long createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Long getUpdatedPostId() {
		return updatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Long getRltContriVoucherId() {
		return rltContriVoucherId;
	}

	public void setRltContriVoucherId(Long rltContriVoucherId) {
		this.rltContriVoucherId = rltContriVoucherId;
	}
	
	public Character getEmployerContriFlag() {
		return employerContriFlag;
	}

	public void setEmployerContriFlag(Character employerContriFlag) {
		this.employerContriFlag = employerContriFlag;
	}
	
	public Long getDelayedFinYearId() {
		return delayedFinYearId;
	}

	public void setDelayedFinYearId(Long delayedFinYearId) {
		this.delayedFinYearId = delayedFinYearId;
	}

	public Long getDelayedMonthId() {
		return delayedMonthId;
	}

	public void setDelayedMonthId(Long delayedMonthId) {
		this.delayedMonthId = delayedMonthId;
	}
	
	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Double getContributionNps() {
		return contributionNps;
	}

	public void setContributionNps(Double contributionNps) {
		this.contributionNps = contributionNps;
	}

}