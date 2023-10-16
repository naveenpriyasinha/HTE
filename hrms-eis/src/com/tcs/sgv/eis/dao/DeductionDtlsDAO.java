package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;

public interface DeductionDtlsDAO extends GenericDao<HrPayDeductionDtls,Long>{
	
	List getAllDeductionTypeFromMaster();
	List getAllDeductionTypeFromMpg(long empid,int month , int year );
	//HrPayDeductionDtls getHrPayDeductionDtls(long empId,long deducCode,int month,int year);
	List getDeductionType();
}
