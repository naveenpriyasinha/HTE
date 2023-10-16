package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayPayslipArgs;

public interface PayslipArgsDAO extends GenericDao<HrPayPayslipArgs, Long>{
 public List getAllAllowanceData();
 public List getAllDeducData();
}
