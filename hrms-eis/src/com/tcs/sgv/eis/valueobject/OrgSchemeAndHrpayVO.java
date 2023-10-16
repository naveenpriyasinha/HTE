package com.tcs.sgv.eis.valueobject;

import java.io.Serializable;

public class OrgSchemeAndHrpayVO implements Serializable 
{

	private long schemeCode;
	private String schemeName;
	private String budName;
	private long budSubId;
	public OrgSchemeAndHrpayVO()
	{
		
	}
	public OrgSchemeAndHrpayVO(long schemeCode, String schemeName,
			String budName,long budSubId) {
		super();
		this.schemeCode = schemeCode;
		this.schemeName = schemeName;
		this.budName = budName;
		this.budSubId = budSubId;
	}
	public long getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(long schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getBudName() {
		return budName;
	}
	public void setBudName(String budName) {
		this.budName = budName;
	}
	public long getBudSubId() {
		return budSubId;
	}
	public void setBudSubId(long budSubId) {
		this.budSubId = budSubId;
	}
	
	
	

	
	
	
	
	
}
