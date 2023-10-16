package com.tcs.sgv.eis.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface EmpPreEmplDtlsService 
{
	ResultObject getEmpPreEmplDtls(Map objectArgs);
	ResultObject savePreEmplDtlsInDB(Map objectArgs);
}
