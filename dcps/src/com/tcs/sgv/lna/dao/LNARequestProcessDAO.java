package com.tcs.sgv.lna.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;

public interface LNARequestProcessDAO extends GenericDao {
	public List getGPFEmployeeDetail(Long lLngEmpId);

	public List getDCPSEmployeeDetail(Long lLngEmpId);

	public List getEmployeeDcpsOrGpf(String lStrSevaarthId, String empName, String criteria, String lStrDdoCode, String lStrHodLocCode);

	public List getEmployeeDetailForApprover(String lStrPostId);

	public String getNewTransactionId(String lStrSevaarthId, Long lLngAdvanceType);

	public Boolean checkEligibilityForLNA(String lStrSevaarthId);

	public HrEisScaleMst getPayScaleData(Long empId);

	public List getCheckList(String lStrSevaarthId, Long lLngReqType, Long lLngReqSubType);

	public List getChecklistPk(String lStrSevaarthId, Long lLngRequestType, Long lLngReqSubType);

	public List getEmpNameForAutoComplete(String searchKey, String lStrDdoCode, String lStrHodLocCode);

	public List getDraftRequestList(String lStrCriteria, String lStrName, Date lDtSaveDate, String lStrHodLocCode);

	public List getEmpLoanDetails(Long lLngUserId);

	public List getEmpBankDetails(String lStrSevaarthId);
}
