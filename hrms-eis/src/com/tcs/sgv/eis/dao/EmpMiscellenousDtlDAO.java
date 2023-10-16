package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisInscplcyDtl;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;


public interface EmpMiscellenousDtlDAO extends GenericDao
{
	List<HrEisBankDtls> getBankDtlVOList(long userId);
	List<HrEisInscplcyDtl> getInsuranceDtlVOList(long userId);
	List getOrgEmpMstVOList(long userId) ;
	List<HrEisProofDtl> getPassportDtlVOList(long userId, long lookupId);
	HrEisProofDtl getEmpPanNo(long userId,long lookupId);
	List getHrEmpMstList(long empId);
}
