package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

@SuppressWarnings("unchecked")
public interface NetMismatchDAO extends GenericDao<HrPayPaybill, Long>
{
	
	public List getPayBillColumns(long locId,String allowCmpId, String deducCmpId);
	public List getTotalHeadValue(long empId, int month, int year, String paybillColumnListSum,String condition_coloumn_names);
	public List getAllEmployeeByLocId(long locID);
	public List getEmpCmpMpgData(long empId,long locID);
}
