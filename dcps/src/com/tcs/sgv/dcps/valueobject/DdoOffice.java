package com.tcs.sgv.dcps.valueobject;

import java.util.Date;

public class DdoOffice implements java.io.Serializable 
{

	private Long dcpsDdoOfficeIdPk;
	private String dcpsDdoCode;
	private String dcpsDdoOfficeName;
	private String dcpsDdoOfficeDdoFlag;
	private String dcpsDdoOfficeState;
	private String dcpsDdoOfficeDistrict;
	private String dcpsDdoOfficeTaluka;
	private String dcpsDdoOfficeTown;
	private String dcpsDdoOfficeVillage;
	private String dcpsDdoOfficeAddress1;
	private String dcpsDdoOfficeAddress2;
	private Long dcpsDdoOfficePin;
	private String dcpsDdoOfficeCityClass;
	private Long dcpsDdoOfficeTelNo1;
	private Long dcpsDdoOfficeTelNo2;
	private Long dcpsDdoOfficeFax;
	private String dcpsDdoOfficeEmail;
	private String dcpsDdoOfficeTribalFlag;
	private String dcpsDdoOfficeHillyFlag;
	private String dcpsDdoOfficeNaxaliteAreaFlag;
	private Long LangId;
	private Long LocId;
	private Long DbId;
	private Long PostId;
	private Long UserId;
	private Date CreatedDate;
	private Long UpdatedPostId;
	private Long UpdatedUserId;
	private Date UpdatedDate;
	private Long DiceCode;
	private Long statusFlag;
	private String dcpsDdoOfficeGrant;
	private String reasonForRejection;
	private String uniqueInstituteNo;
	
	

	public String getUniqueInstituteNo() {
		return uniqueInstituteNo;
	}

	public void setUniqueInstituteNo(String uniqueInstituteNo) {
		this.uniqueInstituteNo = uniqueInstituteNo;
	}

	public String getReasonForRejection() {
		return reasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}

	public String getDcpsDdoOfficeGrant() {
		return dcpsDdoOfficeGrant;
	}

	public void setDcpsDdoOfficeGrant(String dcpsDdoOfficeGrant) {
		this.dcpsDdoOfficeGrant = dcpsDdoOfficeGrant;
	}

	public Long getStatusFlag()
	{
		return statusFlag;
	}

	public void setStatusFlag(Long statusFlag)
	{
		this.statusFlag = statusFlag;
	}

	public Long getDiceCode()
	{
		return DiceCode;
	}

	public void setDiceCode(Long diceCode)
	{
		DiceCode = diceCode;
	}

	public Long getDcpsDdoOfficeIdPk() {
		return dcpsDdoOfficeIdPk;
	}

	public void setDcpsDdoOfficeIdPk(Long dcpsDdoOfficeIdPk) {
		this.dcpsDdoOfficeIdPk = dcpsDdoOfficeIdPk;
	}

	public String getDcpsDdoOfficeName() {
		return dcpsDdoOfficeName;
	}

	public String getDcpsDdoCode() {
		return dcpsDdoCode;
	}

	public void setDcpsDdoCode(String dcpsDdoCode) {
		this.dcpsDdoCode = dcpsDdoCode;
		// dcpsDdoCode
	}

	public void setDcpsDdoOfficeName(String dcpsDdoOfficeName) {
		this.dcpsDdoOfficeName = dcpsDdoOfficeName;
	}

	public String getDcpsDdoOfficeDdoFlag() {
		return dcpsDdoOfficeDdoFlag;
	}

	public void setDcpsDdoOfficeDdoFlag(String dcpsDdoOfficeDdoFlag) {
		this.dcpsDdoOfficeDdoFlag = dcpsDdoOfficeDdoFlag;
	}

	public String getDcpsDdoOfficeState() {
		return dcpsDdoOfficeState;
	}

	public void setDcpsDdoOfficeState(String dcpsDdoOfficeState) {
		this.dcpsDdoOfficeState = dcpsDdoOfficeState;
	}

	public String getDcpsDdoOfficeDistrict() {
		return dcpsDdoOfficeDistrict;
	}

	public void setDcpsDdoOfficeDistrict(String dcpsDdoOfficeDistrict) {
		this.dcpsDdoOfficeDistrict = dcpsDdoOfficeDistrict;
	}

	public String getDcpsDdoOfficeTaluka() {
		return dcpsDdoOfficeTaluka;
	}

	public void setDcpsDdoOfficeTaluka(String dcpsDdoOfficeTaluka) {
		this.dcpsDdoOfficeTaluka = dcpsDdoOfficeTaluka;
	}

	public String getDcpsDdoOfficeTown() {
		return dcpsDdoOfficeTown;
	}

	public void setDcpsDdoOfficeTown(String dcpsDdoOfficeTown) {
		this.dcpsDdoOfficeTown = dcpsDdoOfficeTown;
	}

	public String getDcpsDdoOfficeVillage() {
		return dcpsDdoOfficeVillage;
	}

	public void setDcpsDdoOfficeVillage(String dcpsDdoOfficeVillage) {
		this.dcpsDdoOfficeVillage = dcpsDdoOfficeVillage;
	}

	public String getDcpsDdoOfficeAddress1() {
		return dcpsDdoOfficeAddress1;
	}

	public void setDcpsDdoOfficeAddress1(String dcpsDdoOfficeAddress1) {
		this.dcpsDdoOfficeAddress1 = dcpsDdoOfficeAddress1;
	}

	public String getDcpsDdoOfficeAddress2() {
		return dcpsDdoOfficeAddress2;
	}

	public void setDcpsDdoOfficeAddress2(String dcpsDdoOfficeAddress2) {
		this.dcpsDdoOfficeAddress2 = dcpsDdoOfficeAddress2;
	}

	public Long getDcpsDdoOfficePin() {
		return dcpsDdoOfficePin;
	}

	public void setDcpsDdoOfficePin(Long dcpsDdoOfficePin) {
		this.dcpsDdoOfficePin = dcpsDdoOfficePin;
	}

	public String getDcpsDdoOfficeCityClass() {
		return dcpsDdoOfficeCityClass;
	}

	public void setDcpsDdoOfficeCityClass(String dcpsDdoOfficeCityClass) {
		this.dcpsDdoOfficeCityClass = dcpsDdoOfficeCityClass;
	}

	public Long getDcpsDdoOfficeTelNo1() {
		return dcpsDdoOfficeTelNo1;
	}

	public void setDcpsDdoOfficeTelNo1(Long dcpsDdoOfficeTelNo1) {
		this.dcpsDdoOfficeTelNo1 = dcpsDdoOfficeTelNo1;
	}

	public Long getDcpsDdoOfficeTelNo2() {
		return dcpsDdoOfficeTelNo2;
	}

	public void setDcpsDdoOfficeTelNo2(Long dcpsDdoOfficeTelNo2) {
		this.dcpsDdoOfficeTelNo2 = dcpsDdoOfficeTelNo2;
	}

	public Long getDcpsDdoOfficeFax() {
		return dcpsDdoOfficeFax;
	}

	public void setDcpsDdoOfficeFax(Long dcpsDdoOfficeFax) {
		this.dcpsDdoOfficeFax = dcpsDdoOfficeFax;
	}

	public String getDcpsDdoOfficeEmail() {
		return dcpsDdoOfficeEmail;
	}

	public void setDcpsDdoOfficeEmail(String dcpsDdoOfficeEmail) {
		this.dcpsDdoOfficeEmail = dcpsDdoOfficeEmail;
	}

	public String getDcpsDdoOfficeTribalFlag() {
		return dcpsDdoOfficeTribalFlag;
	}

	public void setDcpsDdoOfficeTribalFlag(String dcpsDdoOfficeTribalFlag) {
		this.dcpsDdoOfficeTribalFlag = dcpsDdoOfficeTribalFlag;
	}

	public String getDcpsDdoOfficeHillyFlag() {
		return dcpsDdoOfficeHillyFlag;
	}

	public void setDcpsDdoOfficeHillyFlag(String dcpsDdoOfficeHillyFlag) {
		this.dcpsDdoOfficeHillyFlag = dcpsDdoOfficeHillyFlag;
	}

	public String getDcpsDdoOfficeNaxaliteAreaFlag() {
		return dcpsDdoOfficeNaxaliteAreaFlag;
	}

	public void setDcpsDdoOfficeNaxaliteAreaFlag(
			String dcpsDdoOfficeNaxaliteAreaFlag) {
		this.dcpsDdoOfficeNaxaliteAreaFlag = dcpsDdoOfficeNaxaliteAreaFlag;
	}

	public Long getLangId() {
		return LangId;
	}

	public void setLangId(Long langId) {
		LangId = langId;
	}

	public Long getLocId() {
		return LocId;
	}

	public void setLocId(Long locId) {
		LocId = locId;
	}

	public Long getDbId() {
		return DbId;
	}

	public void setDbId(Long dbId) {
		DbId = dbId;
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

}
