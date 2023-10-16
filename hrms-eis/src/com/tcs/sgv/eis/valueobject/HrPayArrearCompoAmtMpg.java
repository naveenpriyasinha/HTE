package com.tcs.sgv.eis.valueobject;

import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;


public class HrPayArrearCompoAmtMpg  implements java.io.Serializable {

	private long compoAmtMpgId;
	
	private HrPayArrearBillpostMpg arrearBillpostMpg;

	private RltBillTypeEdp rltBillTypeEdp;
	
	private double amount;

	public long getCompoAmtMpgId() {
		return compoAmtMpgId;
	}

	public void setCompoAmtMpgId(long compoAmtMpgId) {
		this.compoAmtMpgId = compoAmtMpgId;
	}

	public HrPayArrearBillpostMpg getArrearBillpostMpg() {
		return arrearBillpostMpg;
	}

	public void setArrearBillpostMpg(HrPayArrearBillpostMpg arrearBillpostMpg) {
		this.arrearBillpostMpg = arrearBillpostMpg;
	}

	public RltBillTypeEdp getRltBillTypeEdp() {
		return rltBillTypeEdp;
	}

	public void setRltBillTypeEdp(RltBillTypeEdp rltBillTypeEdp) {
		this.rltBillTypeEdp = rltBillTypeEdp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}
