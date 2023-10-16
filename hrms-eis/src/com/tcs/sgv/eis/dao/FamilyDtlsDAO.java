package com.tcs.sgv.eis.dao;

import java.util.List;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


public interface FamilyDtlsDAO extends GenericDao 
{
	List getEmpRequest(OrgUserMst empID);
	List getDrftDtlsLst(OrgUserMst empID);
	List findRequestForthisRecord(long memberId, OrgUserMst empID);	
	List getEmpDraftRequest(OrgUserMst empID);
	List getFamilyPendingRecordForView(long reqId,OrgUserMst userId);
	List getFamilyDraftRecordForView(long reqId, OrgUserMst empID);
	List getFamilyDtlsRecordForDelete(long delId, OrgUserMst orgEmpMst);
	List addDraftDtls(OrgUserMst orgUserMst, long request_ID);
	List deletRequest(long memberId);
	List getTheFamilyMember(long userId,long familyMemberId);
	boolean chekDeleteRequest(long memberId, OrgUserMst orgUserMst);
	List getChild(OrgUserMst orgUserMst);
	List getAllData(OrgUserMst orgUserMst, CmnLookupMst cmnLookupMstSon, CmnLookupMst cmnLookupMstDaughter);
	List getDependantRecordCheckWithDate(long userId, long memberId);
	List getDependantRecordOnUserId(long userId) ;
	List getDependantRecordOnUserIdForWelFare(long userId); 
	List getEmpFamilyDtls(OrgUserMst empID);
	List getEmpMaxMemberID(OrgUserMst empID); 
	List getEmpFamilyMaxMemberID(OrgUserMst empID);//IFMS
	List getAllDepartmentByLangId(long langId);
	List getAliveEmpFamilyDtls(OrgUserMst orgUSerMst);
	List<CmnLookupMst> getAllRelationByLookUpNameAndLang(String aStrLookUpName,long langID,String restrictedLookupName);
}