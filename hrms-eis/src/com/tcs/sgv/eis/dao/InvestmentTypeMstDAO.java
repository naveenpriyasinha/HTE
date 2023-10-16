package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrInvestTypeMst;

public interface InvestmentTypeMstDAO extends GenericDao<HrInvestTypeMst, Long>{	
	public List getInvestmentTypes(CmnLanguageMst cmnLanguageMst);
	public List getSectionList(CmnLanguageMst cmnLanguageMst);
}
