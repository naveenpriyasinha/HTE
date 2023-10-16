package com.tcs.sgv.eis.valueobject;

import java.util.List;

public class Form16CustomVO {

	private HrForm16Dtls form16Dtls;
	private List<HrForm16TaxDeducDtls> list;
	
	public Form16CustomVO()
	{
		
	}

	public HrForm16Dtls getForm16Dtls() {
		return form16Dtls;
	}

	public List<HrForm16TaxDeducDtls> getList() {
		return list;
	}

	public void setForm16Dtls(HrForm16Dtls form16Dtls) {
		this.form16Dtls = form16Dtls;
	}

	public void setList(List<HrForm16TaxDeducDtls> list) {
		this.list = list;
	}
	
	
}
