package com.tcs.sgv.eis.service;
import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;
public interface  EmpPersonalInfoService
{
	ResultObject addEditEmpInfo(Map objectArgs);
	ResultObject saveEditEmpInfoData(Map objectArgs);
	ResultObject getComboDtls(Map<String, Object> objectArgs);
	ResultObject getEmpNextCmbDtls(Map<String, Object> objectArgs);
}
