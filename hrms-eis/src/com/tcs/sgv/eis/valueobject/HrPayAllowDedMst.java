package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

@SuppressWarnings("serial")
public class HrPayAllowDedMst implements java.io.Serializable {

	 	private long allowDedId;
	 	private String allowDedShrtName;
	 	private String allowDedName;
	 	private int type;
	 	private int otherAllowRecov;
	 	private Integer agTresry;
	 	private String componentHead;
	 	private int langId;
	 	private long allwDedCode;
	 	private int displayOrder;
	 	private Integer displaySubOrder;
	 	private int ruleBasedFlag;
	 	private int status;
	 	private Date startDate;
	 	private Double interestRate;
	 	private int taxExemptedFlag;
	 	private Integer taxExemptionSection;
	 	private OrgUserMst orgUserMstBycrtdBy;
	 	private OrgPostMst orgPostMstBycrtdByPost;
	 
	 	private Date crtdDate;
	 	private OrgUserMst orgUserMstByupdatedBy;
	 	private OrgPostMst orgPostMstByupdatedByPost;
	 	
	 	private Date updDate;
	 	private Integer trnCounter;
	 	private int usedInOtherCalculation;
	 	
	 	
	 	public int getUsedInOtherCalculation() {
			return usedInOtherCalculation;

		}
		public void setUsedInOtherCalculation(int usedInOtherCalculation) {
			this.usedInOtherCalculation = usedInOtherCalculation;
		}
		public long getAllowDedId() {
			return allowDedId;
		}
		public void setAllowDedId(long allowDedId) {
			this.allowDedId = allowDedId;
		}
		public String getAllowDedShrtName() {
			return allowDedShrtName;
		}
		public void setAllowDedShrtName(String allowDedShrtName) {
			this.allowDedShrtName = allowDedShrtName;
		}
		public String getAllowDedName() {
			return allowDedName;
		}
		public void setAllowDedName(String allowDedName) {
			this.allowDedName = allowDedName;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getOtherAllowRecov() {
			return otherAllowRecov;
		}
		public void setOtherAllowRecov(int otherAllowRecov) {
			this.otherAllowRecov = otherAllowRecov;
		}
		public Integer getAgTresry() {
			return agTresry;
		}
		public void setAgTresry(Integer agTresry) {
			this.agTresry = agTresry;
		}
		public String getComponentHead() {
			return componentHead;
		}
		public void setComponentHead(String componentHead) {
			this.componentHead = componentHead;
		}
		public int getLangId() {
			return langId;
		}
		public void setLangId(int langId) {
			this.langId = langId;
		}
		public long getAllwDedCode() {
			return allwDedCode;
		}
		public void setAllwDedCode(long allwDedCode) {
			this.allwDedCode = allwDedCode;
		}
		public int getDisplayOrder() {
			return displayOrder;
		}
		public void setDisplayOrder(int displayOrder) {
			this.displayOrder = displayOrder;
		}
		public int getRuleBasedFlag() {
			return ruleBasedFlag;
		}
		public void setRuleBasedFlag(int ruleBasedFlag) {
			this.ruleBasedFlag = ruleBasedFlag;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Double getInterestRate() {
			return interestRate;
		}
		public void setInterestRate(Double interestRate) {
			this.interestRate = interestRate;
		}
		
		public Date getCrtdDate() {
			return crtdDate;
		}
		public void setCrtdDate(Date crtdDate) {
			this.crtdDate = crtdDate;
		}
		
		public Date getUpdDate() {
			return updDate;
		}
		public void setUpdDate(Date updDate) {
			this.updDate = updDate;
		}
		public Integer getTrnCounter() {
			return trnCounter;
		}
		public void setTrnCounter(Integer trnCounter) {
			this.trnCounter = trnCounter;
		}
		
		/**
		 * @return the orgUserMstBycrtdBy
		 */
		public OrgUserMst getOrgUserMstBycrtdBy() {
			return orgUserMstBycrtdBy;
		}
		/**
		 * @param orgUserMstBycrtdBy the orgUserMstBycrtdBy to set
		 */
		public void setOrgUserMstBycrtdBy(OrgUserMst orgUserMstBycrtdBy) {
			this.orgUserMstBycrtdBy = orgUserMstBycrtdBy;
		}
		/**
		 * @return the orgPostMstBycrtdByPost
		 */
		public OrgPostMst getOrgPostMstBycrtdByPost() {
			return orgPostMstBycrtdByPost;
		}
		/**
		 * @param orgPostMstBycrtdByPost the orgPostMstBycrtdByPost to set
		 */
		public void setOrgPostMstBycrtdByPost(OrgPostMst orgPostMstBycrtdByPost) {
			this.orgPostMstBycrtdByPost = orgPostMstBycrtdByPost;
		}
		
		/**
		 * @return the orgUserMstByupdatedBy
		 */
		public OrgUserMst getOrgUserMstByupdatedBy() {
			return orgUserMstByupdatedBy;
		}
		/**
		 * @param orgUserMstByupdatedBy the orgUserMstByupdatedBy to set
		 */
		public void setOrgUserMstByupdatedBy(OrgUserMst orgUserMstByupdatedBy) {
			this.orgUserMstByupdatedBy = orgUserMstByupdatedBy;
		}
		/**
		 * @return the orgPostMstByupdatedByPost
		 */
		public OrgPostMst getOrgPostMstByupdatedByPost() {
			return orgPostMstByupdatedByPost;
		}
		/**
		 * @param orgPostMstByupdatedByPost the orgPostMstByupdatedByPost to set
		 */
		public void setOrgPostMstByupdatedByPost(OrgPostMst orgPostMstByupdatedByPost) {
			this.orgPostMstByupdatedByPost = orgPostMstByupdatedByPost;
		}
		
		/**
		 * @return the displaySubOrder
		 */
		public Integer getDisplaySubOrder() {
			return displaySubOrder;
		}
		/**
		 * @param displaySubOrder the displaySubOrder to set
		 */
		public void setDisplaySubOrder(Integer displaySubOrder) {
			this.displaySubOrder = displaySubOrder;
		}
		/**
		 * @return the taxExemptedFlag
		 */
		public int getTaxExemptedFlag() {
			return taxExemptedFlag;
		}
		/**
		 * @param taxExemptedFlag the taxExemptedFlag to set
		 */
		public void setTaxExemptedFlag(int taxExemptedFlag) {
			this.taxExemptedFlag = taxExemptedFlag;
		}
		/**
		 * @return the taxExemptionSection
		 */
		public Integer getTaxExemptionSection() {
			return taxExemptionSection;
		}
		/**
		 * @param taxExemptionSection the taxExemptionSection to set
		 */
		public void setTaxExemptionSection(Integer taxExemptionSection) {
			this.taxExemptionSection = taxExemptionSection;
		}
		
		 	
}	  
	
	
	

