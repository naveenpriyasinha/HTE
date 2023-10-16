package com.tcs.sgv.eis.dao;

import java.util.List;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


public interface EmpEducationDAO extends GenericDao 
{	
	List getEmpMaxSrNo(OrgUserMst empId);
	List getEmpDtlsSortedByDate(OrgUserMst empID);
	List getEmpMaxReqID(OrgUserMst empID);
	List getDrftDtlsLst(OrgUserMst empID);
	List getEmpRequest(OrgUserMst empID);
	List getPendingRequestLst(long reqId,OrgUserMst empID);
	List getDrftDtlsLstonReqID(OrgUserMst empID, long reqId);
	List addOrdeleteDrftDtlsLstReqId(OrgUserMst empID, long request_ID);
	List deleteReqId(OrgUserMst empID, long delete_ID);
	List findRequestForthisRecord(long srNo, OrgUserMst empID);
	List findParentIdForthisReequest(long request_ID, OrgUserMst empID);
	List deleteDraftReqId(OrgUserMst orgEmpMst, long reqId);
	List getEmpEducationDtls(OrgUserMst empId);//IFMS
}