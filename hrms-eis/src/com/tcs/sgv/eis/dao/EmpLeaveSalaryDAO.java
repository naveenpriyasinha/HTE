package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayLeaveSalaryDtl;



public interface EmpLeaveSalaryDAO extends GenericDao<HrPayLeaveSalaryDtl, Long>
{
	
	public HrPayLeaveSalaryDtl getEmpLeaveSalaryDtlByLeaveDateAndUSerId(long UserId,Date leaveDate);
	public double getEmpLeaveSalaryByUSerId(long UserId,Date prevMonthCreatedDate,int monthGiven,int yearGiven);
	public double getOneDayLS(long UserId,int DutyType,Date leaveDate);
	public boolean checkUserByUSerId(long UserId);

}