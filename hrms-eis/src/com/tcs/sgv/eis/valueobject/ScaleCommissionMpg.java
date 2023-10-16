package com.tcs.sgv.eis.valueobject;

import java.io.Serializable;

import com.tcs.sgv.common.valueobject.CmnLookupMst;

public class ScaleCommissionMpg implements Serializable{

	public long getScMpgId() {
		return ScMpgId;
	}
	
	public void setScMpgId(long scMpgId) {
		ScMpgId = scMpgId;
	}
	
	public CmnLookupMst getPayBand() {
		return payBand;
	}
	
	public void setPayBand(CmnLookupMst payBand) {
		this.payBand = payBand;
	}
	
	public HrEisScaleMst getCommissionFive() {
		return commissionFive;
	}
	
	public void setCommissionFive(HrEisScaleMst commissionFive) {
		this.commissionFive = commissionFive;
	}
	
	public HrEisScaleMst getCommissionSix() {
		return commissionSix;
	}
	
	public void setCommissionSix(HrEisScaleMst commissionSix) {
		this.commissionSix = commissionSix;
	}
	
	//Full Constructor
	public ScaleCommissionMpg(long scMpgId, CmnLookupMst payBand,
			HrEisScaleMst commissionFive, HrEisScaleMst commissionSix) {
		super();
		ScMpgId = scMpgId;
		this.payBand = payBand;
		this.commissionFive = commissionFive;
		this.commissionSix = commissionSix;
	}

	//Default Constructor
	public ScaleCommissionMpg(){};

	
	// variable declaration
	private long ScMpgId;
	private CmnLookupMst payBand;
	private HrEisScaleMst commissionFive;
	private HrEisScaleMst commissionSix;
	
	
}//end class
