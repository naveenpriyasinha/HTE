package com.tcs.sgv.stamp.valueobject;
// Generated Oct 24, 2007 12:30:13 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstVendor generated by hbm2java
 */
public class MstVendor implements java.io.Serializable {

	// Fields    

	private BigDecimal venId;

	private short venCode;

	private String venName;

	private String venAdd;

	private String venRegNo;

	private String placeOfBusiness;

	private char discAllowed;

	private Character active;

	private Date startDate;

	private Date endDate;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal dbId;

	private String locCode;

	private String status;

	// Constructors

	/** default constructor */
	public MstVendor() {
	}

	/** minimal constructor */
	public MstVendor(BigDecimal venId, short venCode, String venName,
			String venRegNo, char discAllowed) {
		this.venId = venId;
		this.venCode = venCode;
		this.venName = venName;
		this.venRegNo = venRegNo;
		this.discAllowed = discAllowed;
	}

	/** full constructor */
	public MstVendor(BigDecimal venId, short venCode, String venName,
			String venAdd, String venRegNo, String placeOfBusiness,
			char discAllowed, Character active, Date startDate, Date endDate,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, BigDecimal dbId,
			String locCode, String status) {
		this.venId = venId;
		this.venCode = venCode;
		this.venName = venName;
		this.venAdd = venAdd;
		this.venRegNo = venRegNo;
		this.placeOfBusiness = placeOfBusiness;
		this.discAllowed = discAllowed;
		this.active = active;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.locCode = locCode;
		this.status = status;
	}

	// Property accessors
	public BigDecimal getVenId() {
		return this.venId;
	}

	public void setVenId(BigDecimal venId) {
		this.venId = venId;
	}

	public short getVenCode() {
		return this.venCode;
	}

	public void setVenCode(short venCode) {
		this.venCode = venCode;
	}

	public String getVenName() {
		return this.venName;
	}

	public void setVenName(String venName) {
		this.venName = venName;
	}

	public String getVenAdd() {
		return this.venAdd;
	}

	public void setVenAdd(String venAdd) {
		this.venAdd = venAdd;
	}

	public String getVenRegNo() {
		return this.venRegNo;
	}

	public void setVenRegNo(String venRegNo) {
		this.venRegNo = venRegNo;
	}

	public String getPlaceOfBusiness() {
		return this.placeOfBusiness;
	}

	public void setPlaceOfBusiness(String placeOfBusiness) {
		this.placeOfBusiness = placeOfBusiness;
	}

	public char getDiscAllowed() {
		return this.discAllowed;
	}

	public void setDiscAllowed(char discAllowed) {
		this.discAllowed = discAllowed;
	}

	public Character getActive() {
		return this.active;
	}

	public void setActive(Character active) {
		this.active = active;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(BigDecimal createdUserId) {
		this.createdUserId = createdUserId;
	}

	public BigDecimal getCreatedPostId() {
		return this.createdPostId;
	}

	public void setCreatedPostId(BigDecimal createdPostId) {
		this.createdPostId = createdPostId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(BigDecimal updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public BigDecimal getUpdatedPostId() {
		return this.updatedPostId;
	}

	public void setUpdatedPostId(BigDecimal updatedPostId) {
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getDbId() {
		return this.dbId;
	}

	public void setDbId(BigDecimal dbId) {
		this.dbId = dbId;
	}

	public String getLocCode() {
		return this.locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}