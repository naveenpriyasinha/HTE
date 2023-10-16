package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayOfficeMst;

public interface uploadNewEmployeeConfigarationDAO extends GenericDao<HrPayOfficeMst, Long>
{
	List getOfficeNames(String officeName);
}
