package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class HrPayUpdatebillRemarks implements java.io.Serializable {
	
	private long id;
	
	private HrPayUpdatebillDtls hrPayUpdatebillDtls;

	private RltBillTypeEdp rltBillTypeEdp;
	
	private String remarks;

	/** default constructor */
	public HrPayUpdatebillRemarks() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HrPayUpdatebillDtls getHrPayUpdatebillDtls() {
		return hrPayUpdatebillDtls;
	}

	public void setHrPayUpdatebillDtls(HrPayUpdatebillDtls hrPayUpdatebillDtls) {
		this.hrPayUpdatebillDtls = hrPayUpdatebillDtls;
	}

	public RltBillTypeEdp getRltBillTypeEdp() {
		return rltBillTypeEdp;
	}

	public void setRltBillTypeEdp(RltBillTypeEdp rltBillTypeEdp) {
		this.rltBillTypeEdp = rltBillTypeEdp;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
