package com.tcs.sgv.eis.service;

import java.util.Map;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisNomineeDtl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


public interface NomineeDetailsService 
{
	ResultObject getFamilyNomineeInfo(Map<String, Object> objectArgs,boolean blnWorkFlowEnabled,long iUserId);
	ResultObject getNomineeDetails(Map objectArgs);
	void deleteEmpNomineeDtls(Map<String, Object> objectArgs,HrEisNomineeDtl eisEmpNomineeDtlsVO, long memberId, long reqId, long nominId, String draft, CmnLocationMst cmnLocationMst, CmnLanguageMst cmnLangMst, CmnDatabaseMst cmnDatabaseMst, OrgPostMst orgPostMst, OrgUserMst orgUserMst);
	ResultObject getFamilyMemberDtlsOnName(Map<String, Object> objectArgs);
	ResultObject submitNomineeDtls(Map<String, Object> objectArgs);
	ResultObject showNomineePage(Map<String, Object> objectArgs);
	ResultObject getDraftDtlsOnReqId(Map<String, Object> objectArgs);
	ResultObject getPendingDtlsOnReqId(Map<String, Object> objectArgs);	
	
}
