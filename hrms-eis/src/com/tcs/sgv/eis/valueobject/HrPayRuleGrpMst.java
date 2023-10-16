package  com.tcs.sgv.eis.valueobject;

import java.math.BigDecimal;
import java.util.Date;

import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

@SuppressWarnings("serial")
public class HrPayRuleGrpMst implements java.io.Serializable {

	 	//private long grpMstId;
		private long ruleGrpId;
	 	private long allwDedCode;
	 	private int returnType;
	 	private String returnFormula;
	 	private BigDecimal returnValue;
	 	private int ruleStatus;
	 	private OrgUserMst orgUserMstBycrtdBy;
	 	private OrgPostMst orgPostMstBycrtdByPost;
	 	private Date crtdDate;
	 	private OrgUserMst orgUserMstByupdatedBy;
	 	private OrgPostMst orgPostMstByupdatedByPost;
	 	private Date updDate;
	 	private Integer trnCounter;
		
	 	
	 	
	 	public int getReturnType() {
			return returnType;
		}
		public void setReturnType(int returnType) {
			this.returnType = returnType;
		}
		public long getRuleGrpId() {
			return ruleGrpId;
		}
		public void setRuleGrpId(long ruleGrpId) {
			this.ruleGrpId = ruleGrpId;
		}
		public long getAllwDedCode() {
			return allwDedCode;
		}
		public void setAllwDedCode(long allwDedCode) {
			this.allwDedCode = allwDedCode;
		}
		public OrgUserMst getOrgUserMstBycrtdBy() {
			return orgUserMstBycrtdBy;
		}
		public void setOrgUserMstBycrtdBy(OrgUserMst orgUserMstBycrtdBy) {
			this.orgUserMstBycrtdBy = orgUserMstBycrtdBy;
		}
		public OrgPostMst getOrgPostMstBycrtdByPost() {
			return orgPostMstBycrtdByPost;
		}
		public void setOrgPostMstBycrtdByPost(OrgPostMst orgPostMstBycrtdByPost) {
			this.orgPostMstBycrtdByPost = orgPostMstBycrtdByPost;
		}
		public Date getCrtdDate() {
			return crtdDate;
		}
		public void setCrtdDate(Date crtdDate) {
			this.crtdDate = crtdDate;
		}
		public OrgUserMst getOrgUserMstByupdatedBy() {
			return orgUserMstByupdatedBy;
		}
		public void setOrgUserMstByupdatedBy(OrgUserMst orgUserMstByupdatedBy) {
			this.orgUserMstByupdatedBy = orgUserMstByupdatedBy;
		}
		public OrgPostMst getOrgPostMstByupdatedByPost() {
			return orgPostMstByupdatedByPost;
		}
		public void setOrgPostMstByupdatedByPost(OrgPostMst orgPostMstByupdatedByPost) {
			this.orgPostMstByupdatedByPost = orgPostMstByupdatedByPost;
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
		public String getReturnFormula() {
			return returnFormula;
		}
		public void setReturnFormula(String returnFormula) {
			this.returnFormula = returnFormula;
		}
		public BigDecimal getReturnValue() {
			return returnValue;
		}
		public void setReturnValue(BigDecimal returnValue) {
			this.returnValue = returnValue;
		}
		public int getRuleStatus() {
			return ruleStatus;
		}
		public void setRuleStatus(int ruleStatus) {
			this.ruleStatus = ruleStatus;
		}
	 	
	 	
	 	
		 	
}	  
	
	
	

