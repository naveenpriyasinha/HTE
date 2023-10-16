package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;

public interface HrEdpComponentMpgDAO extends GenericDao<HrPayEdpCompoMpg, Long>{
	
	public List<HrPayEdpCompoMpg> getAllData();

}
