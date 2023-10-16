package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public interface ApproveEducationDAO extends GenericDao
{
	List getAllPendingRequest();
	List getPendingRequestonReqIdandEmpId(long reqId);
	List getEmpMaxReqID(OrgUserMst empId);
	List getEmpEduDtslForApprove(OrgUserMst empId, long parentId);
	List getApproveRequestonEmpId(OrgUserMst orgUserMst,long reqId);
	long getEmpIdFromReqId(long reqId);
	List getApproveRequestonUserId(OrgUserMst orgUserMst);
	List getModEmpRlt(long reqId, HrModMst hrModMst);
	List getEmpDtls(long userIdLongObj);
	List getEmpOtherDtls(long empId);
	long getCurrentRequestIdFromCorrsId(long corrsId);//Created By Sunil
}
