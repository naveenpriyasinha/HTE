package com.tcs.sgv.onlinebillprep.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface TABillService {
	
	public ResultObject getTABillRqstDtls(Map lMapInput);

	public ResultObject saveBill(Map inputMap);
	
    public ResultObject getTABillData(Map inputMap);
}
