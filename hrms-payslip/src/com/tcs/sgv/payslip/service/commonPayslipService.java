package com.tcs.sgv.payslip.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;
public interface commonPayslipService {
	public ResultObject getcommonPayslipPara(Map objectArgs);
	public ResultObject getcommonPayslipData(Map objectArgs);
}
