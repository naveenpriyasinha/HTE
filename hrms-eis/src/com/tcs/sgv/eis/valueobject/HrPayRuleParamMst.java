package com.tcs.sgv.eis.valueobject;

import java.util.Date;

@SuppressWarnings("serial")
public class HrPayRuleParamMst implements java.io.Serializable {

	 	private int paramId;
	 	private String paramName;
	 	private int paramType;
	 	private String propertyValName;
	 	private String propertyUpperValName;
	 	private String crtdBy;
	 	private Date crtdDate;
	 	private String updatedBy;
	 	private Date updDate;
		
	 	
	 	
	 	public int getParamId() {
			return paramId;
		}
		public void setParamId(int paramId) {
			this.paramId = paramId;
		}
		public String getParamName() {
			return paramName;
		}
		public void setParamName(String paramName) {
			this.paramName = paramName;
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
		public int getParamType() {
			return paramType;
		}
		public void setParamType(int paramType) {
			this.paramType = paramType;
		}
		public String getCrtdBy() {
			return crtdBy;
		}
		public void setCrtdBy(String crtdBy) {
			this.crtdBy = crtdBy;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		public String getPropertyValName() {
			return propertyValName;
		}
		public void setPropertyValName(String propertyValName) {
			this.propertyValName = propertyValName;
		}
		public String getPropertyUpperValName() {
			return propertyUpperValName;
		}
		public void setPropertyUpperValName(String propertyUpperValName) {
			this.propertyUpperValName = propertyUpperValName;
		}
		
		
}	  
	
	
	

