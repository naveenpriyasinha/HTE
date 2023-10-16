package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrItExemptTypeMst;

public interface ExemptTypeMstDAO extends GenericDao<HrItExemptTypeMst, Long>{
	public List getAllExemptTypeData(CmnLanguageMst cmnLanguageMst);
}
