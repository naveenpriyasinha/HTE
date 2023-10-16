package com.tcs.sgv.eis.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface EmpEducationService {
	ResultObject deleteDraftRequest(Map<Object, Object> objectArgs, String reqId);
	ResultObject addorEditEmpEducationDtls(Map objectArgs, boolean btnWorkFlow);//IFMS
	ResultObject getComboDtls(Map<String, Object> objectArgs);
	ResultObject getEmpEduPendinRequest(Map<Object, Object> objectArgs,long reqId);
	ResultObject educationSaveAsDraft(Map objectArgs);
	ResultObject getDraftData(Map<Object, Object> objectArgs);
	ResultObject getDraftDataOnRequestId(Map<Object, Object> objectArgs, long reqId);
	ResultObject addDraftDtlsinTable(Map<Object, Object> objectArgs, String reqId);
	ResultObject deleteEmpEducationDtlsFromMaster(Map<Object, Object> objectArgs, long delete_ID);	
	ResultObject getComboDetail(Map objectArgs,String lintcmbid);
	ResultObject getEducationalDetails(Map objectArgs);
	ResultObject submitEmpEducationDtls(Map<Object, Object> objectArgs);
	ResultObject getEmployeeEducationDtls(Map<Object, Object> objectArgs); // IFMS
}
	