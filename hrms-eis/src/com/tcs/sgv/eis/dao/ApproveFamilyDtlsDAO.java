package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;

public interface ApproveFamilyDtlsDAO extends GenericDao 
{
	List getAllFamilyPendingReq();
	List getPendingReqData( long lreqId);
	List getOperationDataFromMst(long empId,long req_Id);
	HrEisFamilyDtl getMasterRecordForDeleteOrUpdate(long member_ID,long lempId);
	long getMaxMemberId(long member_ID, long lempId);
	List getEmpApprovedFamilyDtlsForApprovePage(long reqId);	
	long getCurrentRequestIdFromCorrsId(long corrsId);//Created By Sunil
	List getFamilyPendingDtlsOnReqId(long reqId);//Created By Sunil 
}
