package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public interface NomineeDtlsDAO extends GenericDao
{
	List getEmpFamilyDtls(OrgUserMst orgEmpMst);
	List getEmpFamilyDtlsForName(OrgUserMst orgEmpMst, String fNameStrObj);
	List<Object> getApprovedNomineeData(OrgUserMst orgEmpMst,CmnLookupMst cmnLookupMst);
	List getPendingNomineeData(OrgUserMst orgEmpMst,CmnLookupMst cmnLookupMst);
	List getPendingDtlsOnReqId(long reqId, OrgUserMst orgEmpMst);
	List getDraftData(OrgUserMst orgEmpMst,CmnLookupMst cmnLookupMst);
	List getDraftDtlsOnReqId(long reqId, OrgUserMst orgEmpMst);
	List getPendingDtlsOnEmpId(OrgUserMst orgEmpMst,CmnLookupMst cmnLookupMst);
	List getNomineeRecordCheckWithFmKey(long empId, long memberId);
	List getDraftDataForShareDtls(OrgUserMst orgEmpMst, CmnLookupMst purposeLookupMst);
	List getDraftReqForMember(OrgUserMst orgEmpMst, CmnLookupMst purposeLookupMst);
	List getPendingReqForMember(OrgUserMst orgEmpMst, CmnLookupMst purposeLookupMst);
	List getApprovedNomineeDataOnUserMst(OrgUserMst orgUserMst);	
	List getNomineeDtls(OrgUserMst orgUserMst,CmnLookupMst cmnLookupMst);//IFMS
	List getNomineeRecordCheckWithFamilyRec(long userId, long memberId);//IFMS
	List getEmpFamilyDtlsForFamilyNomination(OrgUserMst objOrgUserMst);
}
