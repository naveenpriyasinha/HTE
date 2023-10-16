package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public interface EmpLoanDAO extends GenericDao<HrLoanEmpDtls, Long>{
	public List getLoanData();
	public List getEmpLoanData(OrgEmpMst orgEmpMst);
	public List getEmployeeLoanData(String locationCode,long EmpId,long langId,String ... strings);
	public HrLoanEmpDtls getEmpLoanDetail(Long loanid);
	public List getPaybillLoanDtls(long empId,long loanTypeId,long recoveryType,int monthGiven,int yearGiven);
}
