package com.tcs.sgv.dcps.valueobject;

import java.util.Date;

public class PostEmpContri {

	private Long dcpsPostEmpContriIdPk;
	private Long finYear;
	private String contriMonth;
	private String billNo;
	private Long billAmount;
	private Long expenditureTillDate;
	private Long excessExpenditure;
	private Long voucherNo;
	private Date voucherDate;
	private char statusFlag;
	private Long PostId;
	private Long UserId;
	private Date CreatedDate;
	private Long UpdatedPostId;
	private Long UpdatedUserId;
	private Date UpdatedDate;

	public Long getDcpsPostEmpContriIdPk() {
		return dcpsPostEmpContriIdPk;
	}

	public void setDcpsPostEmpContriIdPk(Long dcpsPostEmpContriIdPk) {
		this.dcpsPostEmpContriIdPk = dcpsPostEmpContriIdPk;
	}

	public Long getFinYear() {
		return finYear;
	}

	public void setFinYear(Long finYear) {
		this.finYear = finYear;
	}

	public String getContriMonth() {
		return contriMonth;
	}

	public void setContriMonth(String contriMonth) {
		this.contriMonth = contriMonth;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Long getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Long billAmount) {
		this.billAmount = billAmount;
	}

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

	public char getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(char statusFlag) {
		this.statusFlag = statusFlag;
	}

	public Long getPostId() {
		return PostId;
	}

	public void setPostId(Long postId) {
		PostId = postId;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public Long getUpdatedPostId() {
		return UpdatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId) {
		UpdatedPostId = updatedPostId;
	}

	public Long getUpdatedUserId() {
		return UpdatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		UpdatedUserId = updatedUserId;
	}

	public Date getUpdatedDate() {
		return UpdatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		UpdatedDate = updatedDate;
	}

	/**
	 * @return the expenditureTillDate
	 */
	public Long getExpenditureTillDate() {
		return expenditureTillDate;
	}

	/**
	 * @param expenditureTillDate
	 *            the expenditureTillDate to set
	 */
	public void setExpenditureTillDate(Long expenditureTillDate) {
		this.expenditureTillDate = expenditureTillDate;
	}

	/**
	 * @return the excessExpenditure
	 */
	public Long getExcessExpenditure() {
		return excessExpenditure;
	}

	/**
	 * @param excessExpenditure
	 *            the excessExpenditure to set
	 */
	public void setExcessExpenditure(Long excessExpenditure) {
		this.excessExpenditure = excessExpenditure;
	}

}
