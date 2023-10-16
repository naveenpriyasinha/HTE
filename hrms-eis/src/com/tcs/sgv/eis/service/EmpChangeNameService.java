package com.tcs.sgv.eis.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface EmpChangeNameService {
	ResultObject showEmpOldName(Map objectArgs);
	ResultObject getReqIdForChangeNameService(Map objectArgs);
	ResultObject submitEmpChangeNameReqService(Map objectArgs);
	ResultObject approveEmpChangeNameRequest(Map objectArgs);
}
