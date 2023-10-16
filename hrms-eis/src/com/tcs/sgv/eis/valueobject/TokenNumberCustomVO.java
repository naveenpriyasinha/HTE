package com.tcs.sgv.eis.valueobject;


public class TokenNumberCustomVO implements java.io.Serializable {
	
	private long id;
	
	private String schemeCode;
	
	private String schemeName;
	
	private double billGross;
	
	private double billNetTotal;
	
	private long billGrpid;
	
	private String billDescip;
	
	private long appFlag;

	private String billStatus;
	
	private long grossTotalAmount;
	
	private long netTotalAmount;
	
	private String reasonForRejection;
	
	  //Added By saurabh for subSchemeCode
   	private String subSchemeCode;  
   	public String getSubSchemeCode()
   	       {
   	           return subSchemeCode;
   	       }

   	       public void setSubSchemeCode(String subSchemeCode)
   	       {
   	           this.subSchemeCode = subSchemeCode;
   	       }
   	
   	//ended By saurabh for subSchemeCode
	/**
	 * @return the grossTotalAmount
	 */
	public long getGrossTotalAmount() {
		return grossTotalAmount;
	}

	/**
	 * @param grossTotalAmount the grossTotalAmount to set
	 */
	public void setGrossTotalAmount(long grossTotalAmount) {
		this.grossTotalAmount = grossTotalAmount;
	}

	/**
	 * @return the netTotalAmount
	 */
	public long getNetTotalAmount() {
		return netTotalAmount;
	}

	/**
	 * @param netTotalAmount the netTotalAmount to set
	 */
	public void setNetTotalAmount(long netTotalAmount) {
		this.netTotalAmount = netTotalAmount;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the schemeCode
	 */
	public String getSchemeCode() {
		return schemeCode;
	}

	/**
	 * @param schemeCode the schemeCode to set
	 */
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	/**
	 * @return the schemeName
	 */
	public String getSchemeName() {
		return schemeName;
	}

	/**
	 * @param schemeName the schemeName to set
	 */
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	/**
	 * @return the billGross
	 */
	public double getBillGross() {
		return billGross;
	}

	/**
	 * @param billGross the billGross to set
	 */
	public void setBillGross(double billGross) {
		this.billGross = billGross;
	}

	/**
	 * @return the billNetTotal
	 */
	public double getBillNetTotal() {
		return billNetTotal;
	}

	/**
	 * @param billNetTotal the billNetTotal to set
	 */
	public void setBillNetTotal(double billNetTotal) {
		this.billNetTotal = billNetTotal;
	}

	/**
	 * @return the billGrpid
	 */
	public long getBillGrpid() {
		return billGrpid;
	}

	/**
	 * @param billGrpid the billGrpid to set
	 */
	public void setBillGrpid(long billGrpid) {
		this.billGrpid = billGrpid;
	}

	/**
	 * @return the billDescip
	 */
	public String getBillDescip() {
		return billDescip;
	}

	/**
	 * @param billDescip the billDescip to set
	 */
	public void setBillDescip(String billDescip) {
		this.billDescip = billDescip;
	}

	/**
	 * @return the appFlag
	 */
	public long getAppFlag() {
		return appFlag;
	}

	/**
	 * @param appFlag the appFlag to set
	 */
	public void setAppFlag(long appFlag) {
		this.appFlag = appFlag;
	}

	/**
	 * @return the billStatus
	 */
	public String getBillStatus() {
		return billStatus;
	}

	/**
	 * @param billStatus the billStatus to set
	 */
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	


	public String getReasonForRejection() {
		return reasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
}
