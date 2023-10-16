package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrLeaveEmpDtls;

public interface EmpLeaveDAO extends GenericDao<HrLeaveEmpDtls, Long>
{
	public List getAllEmpLeaveData();
	public HrLeaveEmpDtls getEmpLeaveInfo(Long leaveid);
	public HrLeaveEmpDtls getEmpLeaveInfoFromEmpId(Long empId, Long leaveTypeId,Date fromDate);

}