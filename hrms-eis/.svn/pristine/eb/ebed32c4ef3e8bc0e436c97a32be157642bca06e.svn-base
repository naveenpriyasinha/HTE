package com.tcs.sgv.eis.dao;

import java.util.List;
import java.util.Date;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;

public interface EmpInfoDAO extends GenericDao<HrEisEmpMst, Long>
{
	public abstract List getAllEmpData(CmnLanguageMst cmnLanguageMst);
	public List getEmpIdData(long EmpId,CmnLanguageMst cmnLanguageMst);
	public List findEmpName(String empName,long langId);
	public List getEmpNamesFromOtherDtls();
	public long getEmpIdByOrgEmpId(long empId);
	public List getConfirmationData(String locationCode,Date startDate,Date endDate);
	public List getEmpByLocCodeGradeCodeAndDsgCode(String locationCode,String gradeCode,String dsgnCode);
	//added by praveen
	public List getEmpByDsgCode(String locationCode,long dsgnCode);
	public List getEmpByBillNo(String locationCode,long langId,long billNo,long loanAdvId);
	//added by praveen

}
