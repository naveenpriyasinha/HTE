package com.tcs.sgv.eis.vacancy.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;

public interface VacanyDao extends GenericDao{

	List getQuarterList(long langId);
	
	List getMonthList(long langId);
	
	List getLocationList(long deptId);
	
	List getDesignationList(long langId);
	
	List getDeptList(long langId);
	
	OrgDesignationMst getDesignationMstfromDsgnId(long dsgnId);
	
	List getDepartmentName(long langId,OrgDepartmentMst orgDepartmentMstInRe);
	
	List getVacancnyVofromDsgnCode(String dsgnCode);
}
