package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;

public interface BankDetailDAO extends GenericDao<HrEisBankDtls, Long>{

	List getAllBankDetailData();
	public List getAllBankEmpId();
	public HrEisBankDtls getBankDtlsIdData(long BankDtlId);
	public List getAllBankDetailDataByEmpId(long empId);
	public List getAllBankDetailData(List userList ,long EmpId);
	public List getAllBankDetailData(String locationCode ,long EmpId,long langId);
}
