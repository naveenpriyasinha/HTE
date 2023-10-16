package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class HrForm16TaxDeducDtls implements java.io.Serializable{

	private long deducDtlsId;
	private int deducDtlsMonth;
	private double incomeTax;
	private Date deducDtlsDate;
	private HrForm16BankMst deducDtlsBankId;
	private HrForm16Dtls form16DtlId;
	private OrgUserMst createdBy;
	private Date createdDate;
	
	public HrForm16TaxDeducDtls(){
		
	}

	public long getDeducDtlsId() {
		return deducDtlsId;
	}

	public int getDeducDtlsMonth() {
		return deducDtlsMonth;
	}

	public Date getDeducDtlsDate() {
		return deducDtlsDate;
	}

	public HrForm16BankMst getDeducDtlsBankId() {
		return deducDtlsBankId;
	}

	public HrForm16Dtls getForm16DtlId() {
		return form16DtlId;
	}

	public OrgUserMst getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setDeducDtlsId(long deducDtlsId) {
		this.deducDtlsId = deducDtlsId;
	}

	public void setDeducDtlsMonth(int deducDtlsMonth) {
		this.deducDtlsMonth = deducDtlsMonth;
	}

	public void setDeducDtlsDate(Date deducDtlsDate) {
		this.deducDtlsDate = deducDtlsDate;
	}

	public void setDeducDtlsBankId(HrForm16BankMst deducDtlsBankId) {
		this.deducDtlsBankId = deducDtlsBankId;
	}

	public void setForm16DtlId(HrForm16Dtls form16DtlId) {
		this.form16DtlId = form16DtlId;
	}

	public void setCreatedBy(OrgUserMst createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}

	public double getIncomeTax() {
		return incomeTax;
	}
	
	
	
}
