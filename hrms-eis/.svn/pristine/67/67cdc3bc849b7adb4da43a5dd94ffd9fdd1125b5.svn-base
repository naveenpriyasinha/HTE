package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpParamRlt;


/**
 * Interface for PayComponentRuleGroupParamRltDAOImpl
 * @author 229271
 */
public interface PayComponentRuleGroupParamRltDAO extends GenericDao<HrPayRuleGrpParamRlt, Long>{
	
	public List<HrPayRuleGrpParamRlt> getPayCompRuleParamMpgList(long payCompCode);
	public List getAllActiveRuleParamMpgList(int payCompType);
	public List getAllActiveDeductionRuleParamMpgList();
	public List getAllActiveRuleParamMpgList(int payCompType,int usedInFormulaFlag);
	public List getAllActiveDeductionRuleParamMpgList(int usedInFormulaFlag);
	
	
	
}
