package com.tcs.sgv.eis.service;

import java.util.Map;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
public interface FamilyDetailsService 
{
	void deleteEmpFamilyDtls(Map<String, Object> objectArgs, long memberId, long reqId, long srNo, String draft, CmnLocationMst cmnLocationMst, CmnLanguageMst cmnLangMst, CmnDatabaseMst cmnDatabaseMst, OrgPostMst orgPostMst, OrgUserMst orgUserMst);
	ResultObject getFamilyDetails(Map<String,Object> objectArgs);
	ResultObject openDraftRequestDtls(Map<String, Object> objectArgs, long reqId);
	ResultObject deleteDraftRequestDtls(Map<String, Object> objectArgs, long reqId);
	ResultObject getFamilyPendingRecordForView(Map<String, Object> objectArgs, long reqId,int flag);
	ResultObject submitFamilyDtls(Map<String, Object> objectArgs);
	ResultObject getFamilyInfo(Map<String, Object> objectArgs) ;
	ResultObject getDraftData(Map<String, Object> objectArgs);
	ResultObject getComboDtls(Map<String, Object> objectArgs,boolean blnWorkFlowEnabled ,long selectedUserId);
	ResultObject getFamilyPendingRequestForView(Map<String, Object> objectArgs);
	ResultObject getSelEmpTypeComboDtls(Map objectArgs,String strCmbid);// Change By sunil on 04/06/08 for Employment Dtls
}
