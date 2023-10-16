package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDao;

public interface ApproveNomineeDtlsDAO extends GenericDao 
{
	List getNomineePendingDtls();
	List getNomineePendingDtlsOnReqId(long reqId);
	long getMaxMemberIdForNomineeDtls(long userId);
	List getApprovedNomineeDataForApproveRecord(CmnLookupMst cmnLookupMst, long reqId,long userId);
	long getCurrentRequestIdFromCorrsId(long corrsId); //Divyesh
}
