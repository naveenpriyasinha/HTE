package com.tcs.sgv.allowance.dao;

import java.util.List;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.allowance.valueobject.HrPayExpressionMst;

public interface ExpressionMasterDAO extends GenericDao<HrPayExpressionMst, Long>{

	List getAllAllowances(long langId);
	List getAllExprMasterData(long langId);
	List checkRuleDesc(String ruleDesc,long langId);
	List getExprData();
	List getAllAllowanceNames(long langId);
	List getAllComponentNames(long langId);
	public HrPayExpressionMst getRuleIdData(long RuleId);	
	public List getAllallowCodeData(List AllowcodeList);
	public List getAllallowCode();
}
