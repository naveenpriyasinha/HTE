// default package
// Generated Mar 24, 2009 11:18:40 AM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.pensionpay.valueobject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionRqstTracking generated by hbm2java
 */
public class TrnPensionRqstTracking implements java.io.Serializable {

	// Fields    

	private Long pensionRqstTrackingId;
	private BigDecimal changeItem;
	private String itemFlag;
	private BigDecimal rqstReasonId;
	private String rqstReasonDesc;
	private String authorityNo;
	private Date authorityDate;
	private BigDecimal sancAuthorityCode;
	private String sancAuthorityName;
	private String address;
	private String inwdNo;
	private Date inwdDate;
	private BigDecimal status;
	private BigDecimal auditorPostId;
	private BigDecimal createdUserId;
	private BigDecimal createdPostId;
	private Date createdDate;
	private BigDecimal updatedUserId;
	private BigDecimal updatedPostId;
	private Date updatedDate;

	// Constructors

	/** default constructor */
	public TrnPensionRqstTracking() {
	}

	/** minimal constructor */
	public TrnPensionRqstTracking(Long pensionRqstTrackingId,
			BigDecimal changeItem, String itemFlag, BigDecimal rqstReasonId,
			String rqstReasonDesc, String authorityNo, Date authorityDate,
			BigDecimal sancAuthorityCode, String sancAuthorityName,
			String address, String inwdNo, Date inwdDate, BigDecimal status,
			BigDecimal auditorPostId, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate) {
		this.pensionRqstTrackingId = pensionRqstTrackingId;
		this.changeItem = changeItem;
		this.itemFlag = itemFlag;
		this.rqstReasonId = rqstReasonId;
		this.rqstReasonDesc = rqstReasonDesc;
		this.authorityNo = authorityNo;
		this.authorityDate = authorityDate;
		this.sancAuthorityCode = sancAuthorityCode;
		this.sancAuthorityName = sancAuthorityName;
		this.address = address;
		this.inwdNo = inwdNo;
		this.inwdDate = inwdDate;
		this.status = status;
		this.auditorPostId = auditorPostId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public TrnPensionRqstTracking(Long pensionRqstTrackingId,
			BigDecimal changeItem, String itemFlag, BigDecimal rqstReasonId,
			String rqstReasonDesc, String authorityNo, Date authorityDate,
			BigDecimal sancAuthorityCode, String sancAuthorityName,
			String address, String inwdNo, Date inwdDate, BigDecimal status,
			BigDecimal auditorPostId, BigDecimal createdUserId,
			BigDecimal createdPostId, Date createdDate,
			BigDecimal updatedUserId, BigDecimal updatedPostId, Date updatedDate) {
		this.pensionRqstTrackingId = pensionRqstTrackingId;
		this.changeItem = changeItem;
		this.itemFlag = itemFlag;
		this.rqstReasonId = rqstReasonId;
		this.rqstReasonDesc = rqstReasonDesc;
		this.authorityNo = authorityNo;
		this.authorityDate = authorityDate;
		this.sancAuthorityCode = sancAuthorityCode;
		this.sancAuthorityName = sancAuthorityName;
		this.address = address;
		this.inwdNo = inwdNo;
		this.inwdDate = inwdDate;
		this.status = status;
		this.auditorPostId = auditorPostId;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
	}

	// Property accessors
	public Long getPensionRqstTrackingId() {
		return this.pensionRqstTrackingId;
	}

	public void setPensionRqstTrackingId(Long pensionRqstTrackingId) {
		this.pensionRqstTrackingId = pensionRqstTrackingId;
	}

	public BigDecimal getChangeItem() {
		return this.changeItem;
	}

	public void setChangeItem(BigDecimal changeItem) {
		this.changeItem = changeItem;
	}

	public String getItemFlag() {
		return this.itemFlag;
	}

	public void setItemFlag(String itemFlag) {
		this.itemFlag = itemFlag;
	}

	public BigDecimal getRqstReasonId() {
		return this.rqstReasonId;
	}

	public void setRqstReasonId(BigDecimal rqstReasonId) {
		this.rqstReasonId = rqstReasonId;
	}

	public String getRqstReasonDesc() {
		return this.rqstReasonDesc;
	}

	public void setRqstReasonDesc(String rqstReasonDesc) {
		this.rqstReasonDesc = rqstReasonDesc;
	}

	public String getAuthorityNo() {
		return this.authorityNo;
	}

	public void setAuthorityNo(String authorityNo) {
		this.authorityNo = authorityNo;
	}

	public Date getAuthorityDate() {
		return this.authorityDate;
	}

	public void setAuthorityDate(Date authorityDate) {
		this.authorityDate = authorityDate;
	}

	public BigDecimal getSancAuthorityCode() {
		return this.sancAuthorityCode;
	}

	public void setSancAuthorityCode(BigDecimal sancAuthorityCode) {
		this.sancAuthorityCode = sancAuthorityCode;
	}

	public String getSancAuthorityName() {
		return this.sancAuthorityName;
	}

	public void setSancAuthorityName(String sancAuthorityName) {
		this.sancAuthorityName = sancAuthorityName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInwdNo() {
		return this.inwdNo;
	}

	public void setInwdNo(String inwdNo) {
		this.inwdNo = inwdNo;
	}

	public Date getInwdDate() {
		return this.inwdDate;
	}

	public void setInwdDate(Date inwdDate) {
		this.inwdDate = inwdDate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getAuditorPostId() {
		return this.auditorPostId;
	}

	public void setAuditorPostId(BigDecimal auditorPostId) {
		this.auditorPostId = auditorPostId;
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

}
