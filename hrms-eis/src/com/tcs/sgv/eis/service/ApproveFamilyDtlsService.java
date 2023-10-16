package com.tcs.sgv.eis.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface ApproveFamilyDtlsService 
{
	ResultObject getApproveFamilyDtls(Map<String, Object> objectArgs);
	ResultObject ShowFamilyDtls(Map<String, Object> objectArgs);
	ResultObject forwardFamilyDtlsForApproval(Map<String, Object> objectArgs);
	ResultObject saveFamilyApproveDtls(Map<String, Object> objectArgs);
	ResultObject rejectFamilyDtlsReqest(Map<String, Object> objectArgs);
	ResultObject cancleFamilyRequestSRVC(Map<String, Object> objectArgs);
}
