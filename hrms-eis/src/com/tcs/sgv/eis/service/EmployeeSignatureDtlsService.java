package com.tcs.sgv.eis.service;
import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface EmployeeSignatureDtlsService 
{
	ResultObject insertEmployeeSignatureData(Map<Object, Object> objectArgs);
	ResultObject getEmployeeSignatureDtlsData(Map<Object, Object> objectArgs);
	ResultObject getEmployeeSignatureDtlsIdData(Map<Object, Object> objectArgs);
	ResultObject checkDate(Map<Object, Object> objectArgs);
}
