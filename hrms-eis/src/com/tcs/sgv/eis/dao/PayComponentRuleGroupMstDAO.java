package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayRuleGrpMst;


/**
 * Interface for PayComponentRuleGroupMstDAOImpl
 * @author 229271
 */
public interface PayComponentRuleGroupMstDAO extends GenericDao<HrPayRuleGrpMst, Long>{
	

	public List<HrPayRuleGrpMst> getAllActiveRuleList(int payCompType);
	public List<HrPayRuleGrpMst> getAllActiveDeductionRuleList();
	public List<HrPayRuleGrpMst> getPayCompRuleList(long payCompCode);
	public List<HrPayRuleGrpMst> getAllActiveRuleList(int payCompType,int usedInFormulaFlag);
	public List<HrPayRuleGrpMst> getAllActiveDeductionRuleList(int usedInFormulaFlag);

}
