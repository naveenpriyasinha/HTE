package com.tcs.sgv.eis.valueobject;

import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;

public class ArrearReportCustomVO {
	
public PaybillHeadMpg paybillHeadMpg;

public HrPayArrearPaybill arrearBill;

public OrgDesignationMst orgDesignationMst;

public PaybillBillregMpg paybillBillRegMpg;

public TrnBillRegister trnBillRegister;

public long arrearAmtByEdpCoode;

public long prevAmtByEdpCoode;

public String panNo;


public long getPrevAmtByEdpCoode() {
	return prevAmtByEdpCoode;
}

public void setPrevAmtByEdpCoode(long prevAmtByEdpCoode) {
	this.prevAmtByEdpCoode = prevAmtByEdpCoode;
}

public long getArrearAmtByEdpCoode() {
	return arrearAmtByEdpCoode;
}

public void setArrearAmtByEdpCoode(long arrearAmtByEdpCoode) {
	this.arrearAmtByEdpCoode = arrearAmtByEdpCoode;
}

public TrnBillRegister getTrnBillRegister() {
	return trnBillRegister;
}

public void setTrnBillRegister(TrnBillRegister trnBillRegister) {
	this.trnBillRegister = trnBillRegister;
}

public HrPayArrearPaybill getArrearBill() {
	return arrearBill;
}

public void setArrearBill(HrPayArrearPaybill arrearBill) {
	this.arrearBill = arrearBill;
}

public PaybillHeadMpg getPaybillHeadMpg() {
	return paybillHeadMpg;
}

public void setPaybillHeadMpg(PaybillHeadMpg paybillHeadMpg) {
	this.paybillHeadMpg = paybillHeadMpg;
}

public PaybillBillregMpg getPaybillBillRegMpg() {
	return paybillBillRegMpg;
}

public void setPaybillBillRegMpg(PaybillBillregMpg paybillBillRegMpg) {
	this.paybillBillRegMpg = paybillBillRegMpg;
}

public OrgDesignationMst getOrgDesignationMst() {
	return orgDesignationMst;
}

public void setOrgDesignationMst(OrgDesignationMst orgDesignationMst) {
	this.orgDesignationMst = orgDesignationMst;
}

public String getPanNo() {
	return panNo;
}

public void setPanNo(String panNo) {
	this.panNo = panNo;
}

	

}
