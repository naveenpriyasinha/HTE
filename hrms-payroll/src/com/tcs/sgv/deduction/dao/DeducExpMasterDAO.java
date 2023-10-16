package com.tcs.sgv.deduction.dao;

import java.util.List;

import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;
import com.tcs.sgv.core.dao.GenericDao;

public interface DeducExpMasterDAO extends GenericDao<HrDeducExpMst, Long>{

	List getDeducExpMasterData(long langId);
	List getDeducExpData();
	List getAllDeductionNames(long langId);
	public HrDeducExpMst getDeducExpMasterByCode(long deducRuleId);
	public List checkDeducRule(String deducRuleDesc,long langId);
}
