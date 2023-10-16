package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPaySignatureDtls;


public interface EmployeeSignatureDtlsDAO extends GenericDao
{
	public List getAllDesignationDtlsData();
	public List getAllEmployeeSignatureDtlsData(long locid);
	public List getAllEmployeeSignatureDtlsData(long locid,long empid);
	public HrPaySignatureDtls getHrPaySignatureDtlsIdData(long Id);
	public List getAllEmployeeSignatureEmpId();
	public List checkStartDate(String startDate,long locid,long id);
	public List checkEndDate(String endDate,long locid,long id);
}
