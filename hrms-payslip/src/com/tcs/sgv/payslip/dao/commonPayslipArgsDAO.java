package com.tcs.sgv.payslip.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayPayslipArgs;
import com.tcs.sgv.payslip.valueobject.HrCommonPayslipArgs;

public interface commonPayslipArgsDAO extends GenericDao<HrCommonPayslipArgs, Long>{
 public List getAllAllowanceData();
 public List getAllDeducData();
 public List getAllNonGovDeducData();
}
