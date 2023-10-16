package com.tcs.sgv.hr.payincrement.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;

public interface EmpPayIncrDAO extends GenericDao{
	public List getEmpPayIncrListByMonthAndYear(Date StartDate,Date EndDate,long locationCode,long langId);
	public HrPayfixMst getEmpCurrentPayDtlsByUserId(long userId);
	public List getEmployeeAllPayDtlsByUserId(long userId);
	public Date getLastIncrDateByUserId(long userId);  // update by sandip
	public List getEmpPayIncrListByMonthAndYearAndBillNo(String startDate,String endDate,long billId,String locationCode,long langId);
	public List getEmpPayIncrListByMonthAndYearAndBillNoWithoutPayfix(String startDate,String endDate,long billId,String locationCode,long langId);
	
	public String getBillNoFromBillId(long billId);
}
