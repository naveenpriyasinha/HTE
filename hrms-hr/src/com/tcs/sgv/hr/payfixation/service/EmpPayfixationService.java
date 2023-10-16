package com.tcs.sgv.hr.payfixation.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface EmpPayfixationService {
	public ResultObject checkUserId(Map objectArgs);
	public ResultObject savePayFixatnDtlsInDB(Map objectArgs);
	public ResultObject showPayFixationOptions(Map objectArgs);
}
