package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.core.dao.GenericDao;

public interface EmpAllwMapDAO extends GenericDao<HrPayEmpallowMpg,Long>{
	
	List getAllAllowanceTypeFromMaster();
	List getAllAllowanceTypeFromMpg(long empid,int month,int year);
	HrPayEmpallowMpg getHrPayEmpallowMpg(long empId,long allowCod,int month,int year);
	List getAllAllowanceType(long payCommissionId,long locId,long empId);
	List getAllAllowanceType();
}
