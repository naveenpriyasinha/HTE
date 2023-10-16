package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayLeaveEncashDtl;




public interface EmpLeaveEncashDtlDAO extends GenericDao<HrPayLeaveEncashDtl, Long>
{
	
	public double getEmpLeaveEncashByUSerId(long UserId,Date prevMonthCreatedDate);

}