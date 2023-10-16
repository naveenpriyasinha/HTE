package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrItSectionMst;

public interface SectionMasterDAO extends GenericDao<HrItSectionMst, Long>{

	public List getAllSectionData();
}
