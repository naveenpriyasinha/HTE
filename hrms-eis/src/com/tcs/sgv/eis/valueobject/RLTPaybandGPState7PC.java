package com.tcs.sgv.eis.valueobject;

//Creded by Manoj 


public class RLTPaybandGPState7PC {


	
	private  String id;
	private String payInPayband;
	private String gradePay;
	private String level;
	private String levelId;

	public  String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "RLTPaybandGPState7PC [payInPayband=" + payInPayband
				+ ", gradePay=" + gradePay + ", level=" + level + ", levelId="
				+ levelId + "]";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPayInPayband() {
		return payInPayband;
	}

	public void setPayInPayband(String payInPayband) {
		this.payInPayband = payInPayband;
	}

	public String getGradePay() {
		return gradePay;
	}

	public void setGradePay(String gradePay) {
		this.gradePay = gradePay;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	

}