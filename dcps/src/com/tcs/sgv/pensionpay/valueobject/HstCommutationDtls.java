/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Aug 29, 2011		Anjana Suvariya								
 *******************************************************************************
 */
package com.tcs.sgv.pensionpay.valueobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Class Description - 
 *
 *
 * @author Anjana Suvariya
 * @version 0.1
 * @since JDK 5.0
 * Aug 29, 2011
 */
public class HstCommutationDtls implements Serializable{
	
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 4623334674009124713L;
	
	private Long cvpDtlsId;
	private Long dbId;
	private Long locationCode;
	private String pensionerCode;    
	private String orderNo;
	private BigDecimal paymentAmount;
	private String voucherNo;
	private Date voucherDate; 
	private Long createdUserId;
	private Long createdPostId;
	private Date createdDate;
	private Long updatedUserId;
	private Long updatedPostId;
	private Date updatedDate;
	private Date orderDate;
	private BigDecimal totalOrderAmount;
	private Long billNo;
	
	
	public HstCommutationDtls()
	{}

	/**
	 * 
	 * @param cvpDtlsId
	 * @param dbId
	 * @param locationCode
	 * @param pensionerCode
	 * @param orderNo
	 * @param paymentAmount
	 * @param voucherNo
	 * @param voucherDate
	 * @param createdUserId
	 * @param createdPostId
	 * @param createdDate
	 * @param updatedUserId
	 * @param updatedPostId
	 * @param updatedDate
	 * @param orderDate
	 * @param totalOrderAmount
	 * @param billNo
	 */

		
	public HstCommutationDtls(Long cvpDtlsId, Long dbId, Long locationCode,
			String pensionerCode, String orderNo, BigDecimal paymentAmount,
			String voucherNo, Date voucherDate, Long createdUserId,
			Long createdPostId, Date createdDate, Long updatedUserId,
			Long updatedPostId, Date updatedDate, Date orderDate,
			BigDecimal totalOrderAmount, Long billNo) {
		super();
		this.cvpDtlsId = cvpDtlsId;
		this.dbId = dbId;
		this.locationCode = locationCode;
		this.pensionerCode = pensionerCode;
		this.orderNo = orderNo;
		this.paymentAmount = paymentAmount;
		this.voucherNo = voucherNo;
		this.voucherDate = voucherDate;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.orderDate = orderDate;
		this.totalOrderAmount = totalOrderAmount;
		this.billNo = billNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((createdPostId == null) ? 0 : createdPostId.hashCode());
		result = prime * result
				+ ((createdUserId == null) ? 0 : createdUserId.hashCode());
		result = prime * result
				+ ((cvpDtlsId == null) ? 0 : cvpDtlsId.hashCode());
		result = prime * result + ((dbId == null) ? 0 : dbId.hashCode());
		result = prime * result
				+ ((locationCode == null) ? 0 : locationCode.hashCode());
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderNo == null) ? 0 : orderNo.hashCode());
		result = prime * result
				+ ((paymentAmount == null) ? 0 : paymentAmount.hashCode());
		result = prime * result
				+ ((pensionerCode == null) ? 0 : pensionerCode.hashCode());
		result = prime
				* result
				+ ((totalOrderAmount == null) ? 0 : totalOrderAmount.hashCode());
		result = prime * result
				+ ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result
				+ ((updatedPostId == null) ? 0 : updatedPostId.hashCode());
		result = prime * result
				+ ((updatedUserId == null) ? 0 : updatedUserId.hashCode());
		result = prime * result
				+ ((voucherDate == null) ? 0 : voucherDate.hashCode());
		result = prime * result
				+ ((voucherNo == null) ? 0 : voucherNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HstCommutationDtls other = (HstCommutationDtls) obj;
		if (billNo == null) {
			if (other.billNo != null)
				return false;
		} else if (!billNo.equals(other.billNo))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createdPostId == null) {
			if (other.createdPostId != null)
				return false;
		} else if (!createdPostId.equals(other.createdPostId))
			return false;
		if (createdUserId == null) {
			if (other.createdUserId != null)
				return false;
		} else if (!createdUserId.equals(other.createdUserId))
			return false;
		if (cvpDtlsId == null) {
			if (other.cvpDtlsId != null)
				return false;
		} else if (!cvpDtlsId.equals(other.cvpDtlsId))
			return false;
		if (dbId == null) {
			if (other.dbId != null)
				return false;
		} else if (!dbId.equals(other.dbId))
			return false;
		if (locationCode == null) {
			if (other.locationCode != null)
				return false;
		} else if (!locationCode.equals(other.locationCode))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderNo == null) {
			if (other.orderNo != null)
				return false;
		} else if (!orderNo.equals(other.orderNo))
			return false;
		if (paymentAmount == null) {
			if (other.paymentAmount != null)
				return false;
		} else if (!paymentAmount.equals(other.paymentAmount))
			return false;
		if (pensionerCode == null) {
			if (other.pensionerCode != null)
				return false;
		} else if (!pensionerCode.equals(other.pensionerCode))
			return false;
		if (totalOrderAmount == null) {
			if (other.totalOrderAmount != null)
				return false;
		} else if (!totalOrderAmount.equals(other.totalOrderAmount))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		if (updatedPostId == null) {
			if (other.updatedPostId != null)
				return false;
		} else if (!updatedPostId.equals(other.updatedPostId))
			return false;
		if (updatedUserId == null) {
			if (other.updatedUserId != null)
				return false;
		} else if (!updatedUserId.equals(other.updatedUserId))
			return false;
		if (voucherDate == null) {
			if (other.voucherDate != null)
				return false;
		} else if (!voucherDate.equals(other.voucherDate))
			return false;
		if (voucherNo == null) {
			if (other.voucherNo != null)
				return false;
		} else if (!voucherNo.equals(other.voucherNo))
			return false;
		return true;
	}

	public Long getCvpDtlsId() {
	
		return cvpDtlsId;
	}

	
	public void setCvpDtlsId(Long cvpDtlsId) {
	
		this.cvpDtlsId = cvpDtlsId;
	}

	
	public Long getDbId() {
	
		return dbId;
	}

	
	public void setDbId(Long dbId) {
	
		this.dbId = dbId;
	}

	
	public Long getLocationCode() {
	
		return locationCode;
	}

	
	public void setLocationCode(Long locationCode) {
	
		this.locationCode = locationCode;
	}

	
	public String getPensionerCode() {
	
		return pensionerCode;
	}

	
	public void setPensionerCode(String pensionerCode) {
	
		this.pensionerCode = pensionerCode;
	}

	
	public String getOrderNo() {
	
		return orderNo;
	}

	
	public void setOrderNo(String orderNo) {
	
		this.orderNo = orderNo;
	}

	
	public BigDecimal getPaymentAmount() {
	
		return paymentAmount;
	}

	
	public void setPaymentAmount(BigDecimal paymentAmount) {
	
		this.paymentAmount = paymentAmount;
	}

	
	public String getVoucherNo() {
	
		return voucherNo;
	}

	
	public void setVoucherNo(String voucherNo) {
	
		this.voucherNo = voucherNo;
	}

	
	public Date getVoucherDate() {
	
		return voucherDate;
	}

	
	public void setVoucherDate(Date voucherDate) {
	
		this.voucherDate = voucherDate;
	}

	
	public Long getCreatedUserId() {
	
		return createdUserId;
	}

	
	public void setCreatedUserId(Long createdUserId) {
	
		this.createdUserId = createdUserId;
	}

	
	public Long getCreatedPostId() {
	
		return createdPostId;
	}

	
	public void setCreatedPostId(Long createdPostId) {
	
		this.createdPostId = createdPostId;
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

	
	public Date getOrderDate() {
	
		return orderDate;
	}

	
	public void setOrderDate(Date orderDate) {
	
		this.orderDate = orderDate;
	}
	
	public BigDecimal getTotalOrderAmount() {
	
		return totalOrderAmount;
	}
	
	public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
	
		this.totalOrderAmount = totalOrderAmount;
	}

	public Long getBillNo() {
		return billNo;
	}

	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}

	
	
}
