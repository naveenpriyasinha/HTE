/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Aug 17, 2011		512553								
 *******************************************************************************
 */
package com.tcs.sgv.pensionpay.valueobject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class Description - 
 *
 *
 * @author 512553
 * @version 0.1
 * @since JDK 5.0
 * Aug 17, 2011
 */
public class HstPnsnPmntDcrgDtls {
	
	   private Long dcrgDtlsId;
	   private Long dbId;	   
	   private Long locationCode;
	   private String pensionerCode;   
	   private String orderNo;
	   private Date orderDate;
	   private BigDecimal totalOrderAmount = BigDecimal.ZERO;
	   private BigDecimal paidAmount= BigDecimal.ZERO;
	   private String voucherNo;
	   private Date voucherDate;
	   private String paymentAuthority;
	   private Long createdUserId;
	   private Long createdPostId;
	   private Date createdDate;
	   private Long updatedUserId;
	   private Long updatedPostId;
	   private Date updatedDate;
	   private BigDecimal withHeldAmnt= BigDecimal.ZERO;
	   private BigDecimal totalRecoveryAmnt= BigDecimal.ZERO;
	   private BigDecimal amntAfterWithHeld= BigDecimal.ZERO;
	   private Long billNo;
	
	public Long getDcrgDtlsId() {
	
		return dcrgDtlsId;
	}
	
	public void setDcrgDtlsId(Long dcrgDtlsId) {
	
		this.dcrgDtlsId = dcrgDtlsId;
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
	
	public BigDecimal getPaidAmount() {
	
		return paidAmount;
	}
	
	public void setPaidAmount(BigDecimal paidAmount) {
	
		this.paidAmount = paidAmount;
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
	
	public String getPaymentAuthority() {
	
		return paymentAuthority;
	}
	
	public void setPaymentAuthority(String paymentAuthority) {
	
		this.paymentAuthority = paymentAuthority;
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
	
	public BigDecimal getWithHeldAmnt() {
		return withHeldAmnt;
	}

	public void setWithHeldAmnt(BigDecimal withHeldAmnt) {
		this.withHeldAmnt = withHeldAmnt;
	}

	public BigDecimal getTotalRecoveryAmnt() {
		return totalRecoveryAmnt;
	}

	public void setTotalRecoveryAmnt(BigDecimal totalRecoveryAmnt) {
		this.totalRecoveryAmnt = totalRecoveryAmnt;
	}

	public BigDecimal getAmntAfterWithHeld() {
		return amntAfterWithHeld;
	}

	public void setAmntAfterWithHeld(BigDecimal amntAfterWithHeld) {
		this.amntAfterWithHeld = amntAfterWithHeld;
	}

	public Long getBillNo() {
		return billNo;
	}

	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}
	
	
	   
}
