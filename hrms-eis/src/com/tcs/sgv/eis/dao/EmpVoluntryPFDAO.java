package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;

public interface EmpVoluntryPFDAO extends GenericDao<HrPayVpfDtls, Long> {
	List getVoluntryDtls();
	public HrPayVpfDtls getHrPayVpfByCode(long vpfCode);
	List getVpfEmployees();

}
