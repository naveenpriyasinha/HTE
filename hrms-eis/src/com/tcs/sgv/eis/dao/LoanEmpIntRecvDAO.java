package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;

public interface LoanEmpIntRecvDAO extends GenericDao<HrLoanEmpIntRecoverDtls, Long>{
	public List insertIntRecvLoan();
	
	
}
