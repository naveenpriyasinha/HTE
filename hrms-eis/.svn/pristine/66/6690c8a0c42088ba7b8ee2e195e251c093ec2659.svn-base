package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMstHst;
import com.tcs.sgv.eis.valueobject.HrEisEmrgcycntcDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpcontactMst;
import com.tcs.sgv.ess.valueobject.OrgEmpcontactMstHst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public interface EmpInfoDAO_WF extends GenericDao
{
	List getAllEmpData(CmnLanguageMst cmnLanguageMst);
	List getEmpIdData(long EmpId,CmnLanguageMst cmnLanguageMst);
	List findEmpName(String empName,long langId);
	long getAttachmentSrNo(long attachmentId,boolean blnIsFace);
	List getEmpNamesFromOtherDtls();
	HrEisEmpMst getHrEisEmpMstDtls(long loginEmpId);
	OrgEmpMst getOrgEmpMstVO(long loginEmpId);
	HrEisEmrgcycntcDtl getHrEisEmpEmergencyContactData(long userId);	
	HrEisEmpMst getHrEmpVOOnUserId(long userId,long langId);
	List getOrgEmpMstVOList(long userId ,long langId);
	OrgEmpcontactMst getOrgEmpContactMstDtls(long empid, CmnLookupMst cmnLookupMst); // Added By Sandip
	OrgEmpMst getEmployee(OrgUserMst orgUserMst,long langId);
	List getEmpIdData(long empId);
	HrEisEmpMstHst getHrEisEmpHstDataByEmpIdandDate(long empId, Date createdDate);
	OrgEmpcontactMstHst getEmpContactDtlsByEmpIdandCrtdDate(long empId, Date createdDate, CmnLookupMst cmnLookupMst);
}	
