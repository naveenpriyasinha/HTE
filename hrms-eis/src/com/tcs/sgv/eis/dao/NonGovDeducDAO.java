package com.tcs.sgv.eis.dao;

//Comment By Maruthi For import Organisation.
import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;

@SuppressWarnings("unchecked")
public interface NonGovDeducDAO extends GenericDao<HrPayNonGovDeduction, Long>
{

	public List getNonGovDeducData();

	public List getNonGovDeducType(long langId, long deducType);

	//public List getNonGovDeducPeriods();
	public List getEmployees(long langId);

	public HrPayNonGovDeduction getHrPayNonGovDeduction(long nonGovDeducId);

	public List getNonGovDeducDataPayslipHistory(int empId, long deducType);

	public List getNonGovDeducDataPayslip(Date currDate, long empId);

	public List getNonGovDeducDataFromPayslipId(long payslipId);

	public List chkNonGovtForPayslip(long empId, int month, int year);

	public List getNonGovDeducDataByEmpId(long empId);

	public List getNonGovDeducDatabyAccno(String accNo, long deducType, String locationCode);

	public List getNonGovDataById(List nonGovDeducId);

	public List getPayslipNonGovtDataByEmp(long empId, long month, long year);

	public List getPaybillNonGovtDataByEmp(long empId, long month, long year);

	public List getOtherNonGovAmt(long empId, long nonGovType, String AccNo, String startDate, String endDate);

	public List getNonGovDeducDataByEmps(String empIdStr,long locId,long billNo,Date givenDate);

	public List chkNonGovtForPayslip(String empIdStr, int month, int year);
	
	public List checkNonGovtPayslipEntries(String empIdStr, int month, int year);
}
