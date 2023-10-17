package com.tcs.sgv.eis.dao;

import java.util.List;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisDeptMst;

public interface AddDeptDAO extends GenericDao<HrEisDeptMst, Long>{

	List getAllDeptMasterData();
	public List getDeptName();
}