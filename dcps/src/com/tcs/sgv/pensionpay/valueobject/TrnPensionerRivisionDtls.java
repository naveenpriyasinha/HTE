package com.tcs.sgv.pensionpay.valueobject;

// Generated May 12, 2008 2:16:26 PM by Hibernate Tools 3.2.0.beta8

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TrnPensionerRivisionDtls generated by hbm2java
 */
public class TrnPensionerRivisionDtls implements Serializable {

	// Fields

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 8980431946369366889L;

	private Long rivisionDtlsId;

	private Long pensionRequestId;

	private String pensionerCode;

	private Byte revisionCounter;

	private Date rivisionDate;

	private BigDecimal basicPension;

	private BigDecimal cvpAmount;

	private BigDecimal dcrgAmount;

	private BigDecimal cvpMonthlyAmount;

	private BigDecimal fp1Amount;

	private BigDecimal fp2Amount;

	private BigDecimal dpPercent;

	private BigDecimal tiPercent;

	private BigDecimal medicalAllowenceAmount;

	private String activeFlag;

	private BigDecimal dbId;

	private String locationCode;

	private BigDecimal createdUserId;

	private BigDecimal createdPostId;

	private Date createdDate;

	private BigDecimal updatedUserId;

	private BigDecimal updatedPostId;

	private Date updatedDate;

	private BigDecimal orgBf11156;

	private BigDecimal redBf11156;

	private BigDecimal orgAf11156;

	private BigDecimal redAf11156;

	private BigDecimal orgAf10560;

	private BigDecimal redAf10560;

	private BigDecimal trnCounter;

	private BigDecimal daPercent;

	private String revisionOrderNo;

	private Date revisionWEFDate;

	private Date cvpPaidDate;

	private Date cvpRestorationDate;

	private Date cvpRestorationAppDate;

	private Date inwdDate;

	private Date dcrgPaidDate;

	private Date fp1Date;

	private Date fp2Date;

	private String cvpPayMode;

	private String dcrgPayMode;

	private String revisionFlag;
	
	private String ropType;

	// Constructors

	/** default constructor */
	public TrnPensionerRivisionDtls() {

	}

	/** minimal constructor */
	public TrnPensionerRivisionDtls(Long rivisionDtlsId) {

		this.rivisionDtlsId = rivisionDtlsId;
	}

	/** full constructor */
	public TrnPensionerRivisionDtls(Long rivisionDtlsId, Long pensionRequestId,
			String pensionerCode, Byte revisionCounter, Date rivisionDate,
			BigDecimal basicPension, BigDecimal cvpAmount,
			BigDecimal dcrgAmount, BigDecimal cvpMonthlyAmount,
			BigDecimal fp1Amount, BigDecimal fp2Amount, BigDecimal dpPercent,
			BigDecimal tiPercent, BigDecimal medicalAllowenceAmount,
			String activeFlag, BigDecimal dbId, String locationCode,
			BigDecimal createdUserId, BigDecimal createdPostId,
			Date createdDate, BigDecimal updatedUserId,
			BigDecimal updatedPostId, Date updatedDate, BigDecimal orgBf11156,
			BigDecimal redBf11156, BigDecimal orgAf11156,
			BigDecimal redAf11156, BigDecimal orgAf10560,
			BigDecimal redAf10560, BigDecimal trnCounter, BigDecimal daPercent,
			String revisionOrderNo, Date revisionWEFDate, Date cvpPaidDate,
			Date cvpRestorationDate, Date cvpRestorationAppDate, Date inwdDate,
			Date dcrgPaidDate, Date fp1Date, Date fp2Date, String cvpPayMode,
			String dcrgPayMode, String revisionFlag,String ropType) {

		this.rivisionDtlsId = rivisionDtlsId;
		this.pensionRequestId = pensionRequestId;
		this.pensionerCode = pensionerCode;
		this.revisionCounter = revisionCounter;
		this.rivisionDate = rivisionDate;
		this.basicPension = basicPension;
		this.cvpAmount = cvpAmount;
		this.dcrgAmount = dcrgAmount;
		this.cvpMonthlyAmount = cvpMonthlyAmount;
		this.fp1Amount = fp1Amount;
		this.fp2Amount = fp2Amount;
		this.dpPercent = dpPercent;
		this.tiPercent = tiPercent;
		this.medicalAllowenceAmount = medicalAllowenceAmount;
		this.activeFlag = activeFlag;
		this.dbId = dbId;
		this.locationCode = locationCode;
		this.createdUserId = createdUserId;
		this.createdPostId = createdPostId;
		this.createdDate = createdDate;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.orgBf11156 = orgBf11156;
		this.redBf11156 = redBf11156;
		this.orgAf11156 = orgAf11156;
		this.redAf11156 = redAf11156;
		this.orgAf10560 = orgAf10560;
		this.redAf10560 = redAf10560;
		this.trnCounter = trnCounter;
		this.daPercent = daPercent;
		this.revisionOrderNo = revisionOrderNo;
		this.revisionWEFDate = revisionWEFDate;
		this.cvpPaidDate = cvpPaidDate;
		this.cvpRestorationDate = cvpRestorationDate;
		this.cvpRestorationAppDate = cvpRestorationAppDate;
		this.inwdDate = inwdDate;
		this.dcrgPaidDate = dcrgPaidDate;
		this.fp1Date = fp1Date;
		this.fp2Date = fp2Date;
		this.cvpPayMode = cvpPayMode;
		this.dcrgPayMode = dcrgPayMode;
		this.revisionFlag = revisionFlag;
		this.ropType=ropType;
	}

	// Property accessors
	public Long getRivisionDtlsId() {

		return this.rivisionDtlsId;
	}

	public void setRivisionDtlsId(Long rivisionDtlsId) {

		this.rivisionDtlsId = rivisionDtlsId;
	}

	public Long getPensionRequestId() {

		return this.pensionRequestId;
	}

	public void setPensionRequestId(Long pensionRequestId) {

		this.pensionRequestId = pensionRequestId;
	}

	public String getPensionerCode() {

		return this.pensionerCode;
	}

	public void setPensionerCode(String pensionerCode) {

		this.pensionerCode = pensionerCode;
	}

	public Byte getRevisionCounter() {

		return this.revisionCounter;
	}

	public void setRevisionCounter(Byte revisionCounter) {

		this.revisionCounter = revisionCounter;
	}

	public Date getRivisionDate() {

		return this.rivisionDate;
	}

	public void setRivisionDate(Date rivisionDate) {

		this.rivisionDate = rivisionDate;
	}

	public BigDecimal getBasicPension() {

		return this.basicPension;
	}

	public void setBasicPension(BigDecimal basicPension) {

		this.basicPension = basicPension;
	}

	public BigDecimal getCvpAmount() {

		return this.cvpAmount;
	}

	public void setCvpAmount(BigDecimal cvpAmount) {

		this.cvpAmount = cvpAmount;
	}

	public BigDecimal getDcrgAmount() {

		return this.dcrgAmount;
	}

	public void setDcrgAmount(BigDecimal dcrgAmount) {

		this.dcrgAmount = dcrgAmount;
	}

	public BigDecimal getCvpMonthlyAmount() {

		return this.cvpMonthlyAmount;
	}

	public void setCvpMonthlyAmount(BigDecimal cvpMonthlyAmount) {

		this.cvpMonthlyAmount = cvpMonthlyAmount;
	}

	public BigDecimal getFp1Amount() {

		return this.fp1Amount;
	}

	public void setFp1Amount(BigDecimal fp1Amount) {

		this.fp1Amount = fp1Amount;
	}

	public BigDecimal getFp2Amount() {

		return this.fp2Amount;
	}

	public void setFp2Amount(BigDecimal fp2Amount) {

		this.fp2Amount = fp2Amount;
	}

	public BigDecimal getDpPercent() {

		return this.dpPercent;
	}

	public void setDpPercent(BigDecimal dpPercent) {

		this.dpPercent = dpPercent;
	}

	public BigDecimal getTiPercent() {

		return this.tiPercent;
	}

	public void setTiPercent(BigDecimal tiPercent) {

		this.tiPercent = tiPercent;
	}

	public BigDecimal getMedicalAllowenceAmount() {

		return this.medicalAllowenceAmount;
	}

	public void setMedicalAllowenceAmount(BigDecimal medicalAllowenceAmount) {

		this.medicalAllowenceAmount = medicalAllowenceAmount;
	}

	public String getActiveFlag() {

		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {

		this.activeFlag = activeFlag;
	}

	public BigDecimal getDbId() {

		return this.dbId;
	}

	public void setDbId(BigDecimal dbId) {

		this.dbId = dbId;
	}

	public String getLocationCode() {

		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {

		this.locationCode = locationCode;
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

	public BigDecimal getOrgBf11156() {

		return this.orgBf11156;
	}

	public void setOrgBf11156(BigDecimal orgBf11156) {

		this.orgBf11156 = orgBf11156;
	}

	public BigDecimal getRedBf11156() {

		return this.redBf11156;
	}

	public void setRedBf11156(BigDecimal redBf11156) {

		this.redBf11156 = redBf11156;
	}

	public BigDecimal getOrgAf11156() {

		return this.orgAf11156;
	}

	public void setOrgAf11156(BigDecimal orgAf11156) {

		this.orgAf11156 = orgAf11156;
	}

	public BigDecimal getRedAf11156() {

		return this.redAf11156;
	}

	public void setRedAf11156(BigDecimal redAf11156) {

		this.redAf11156 = redAf11156;
	}

	public BigDecimal getOrgAf10560() {

		return this.orgAf10560;
	}

	public void setOrgAf10560(BigDecimal orgAf10560) {

		this.orgAf10560 = orgAf10560;
	}

	public BigDecimal getRedAf10560() {

		return this.redAf10560;
	}

	public void setRedAf10560(BigDecimal redAf10560) {

		this.redAf10560 = redAf10560;
	}

	public BigDecimal getTrnCounter() {

		return this.trnCounter;
	}

	public void setTrnCounter(BigDecimal trnCounter) {

		this.trnCounter = trnCounter;
	}

	public BigDecimal getDaPercent() {

		return this.daPercent;
	}

	public void setDaPercent(BigDecimal daPercent) {

		this.daPercent = daPercent;
	}

	public String getRevisionOrderNo() {

		return this.revisionOrderNo;
	}

	public void setRevisionOrderNo(String revisionOrderNo) {

		this.revisionOrderNo = revisionOrderNo;
	}

	public Date getRevisionWEFDate() {

		return this.revisionWEFDate;
	}

	public void setRevisionWEFDate(Date revisionWEFDate) {

		this.revisionWEFDate = revisionWEFDate;
	}

	public Date getCvpPaidDate() {

		return this.cvpPaidDate;
	}

	public void setCvpPaidDate(Date cvpPaidDate) {

		this.cvpPaidDate = cvpPaidDate;
	}

	public Date getCvpRestorationDate() {

		return this.cvpRestorationDate;
	}

	public void setCvpRestorationDate(Date cvpRestorationDate) {

		this.cvpRestorationDate = cvpRestorationDate;
	}

	public Date getCvpRestorationAppDate() {

		return this.cvpRestorationAppDate;
	}

	public void setCvpRestorationAppDate(Date cvpRestorationAppDate) {

		this.cvpRestorationAppDate = cvpRestorationAppDate;
	}

	public Date getInwdDate() {

		return this.inwdDate;
	}

	public void setInwdDate(Date inwdDate) {

		this.inwdDate = inwdDate;
	}

	public Date getDcrgPaidDate() {

		return this.dcrgPaidDate;
	}

	public void setDcrgPaidDate(Date dcrgPaidDate) {

		this.dcrgPaidDate = dcrgPaidDate;
	}

	public Date getFp1Date() {

		return this.fp1Date;
	}

	public void setFp1Date(Date fp1Date) {

		this.fp1Date = fp1Date;
	}

	public Date getFp2Date() {

		return this.fp2Date;
	}

	public void setFp2Date(Date fp2Date) {

		this.fp2Date = fp2Date;
	}

	public void setCvpPayMode(String cvpPayMode) {

		this.cvpPayMode = cvpPayMode;
	}

	public String getCvpPayMode() {

		return this.cvpPayMode;
	}

	public void setDcrgPayMode(String dcrgPayMode) {

		this.dcrgPayMode = dcrgPayMode;
	}

	public String getDcrgPayMode() {

		return this.dcrgPayMode;
	}

	public void setRevisionFlag(String revisionFlag) {

		this.revisionFlag = revisionFlag;
	}

	public String getRevisionFlag() {

		return this.revisionFlag;
	}
	
	public String getRopType() {
	
		return ropType;
	}
	
	public void setRopType(String ropType) {
	
		this.ropType = ropType;
	}
	
	
}
