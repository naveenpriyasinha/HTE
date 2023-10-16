package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayAllowDedMst;
import com.tcs.sgv.eis.valueobject.HrPayPaycommissionMst;


/**
 * Interface for HrPayCommissionMst D
 * @author Ravysh Tiwari
 */
public interface PayComponentMasterDAO extends GenericDao<HrPayAllowDedMst, Long>{
	
	public List<HrPayPaycommissionMst> getPayCommisions(int langId, long commissionId);
	public List<HrPayAllowDedMst> getPayComponents(int langId,int status);
	public List<HrPayAllowDedMst> getPayComponentsForViewList(int langId);

	public int chkVacantDisplayOrder(int displayOrder,int displaySubOrder,long payCompCode);
	public void updateDisplayOrder(int displayOrder);
	public void updateDisplaySubOrder(int displayOrder,int displaySubOrder);
	
	public List<HrPayAllowDedMst> getActivePayComponentsForSalaryView(long langId);
	public List<HrPayAllowDedMst> getRuleBasedPayCompForViewList(long langId);
	public List<HrPayAllowDedMst> getRuleBasedPayCompForViewList(long langId,long payCompCode);
	public List<HrPayAllowDedMst> getRuleBasedPayCompForViewListForMaha(long langId,long payCompCode);
	public List getPayCompUsedInFormula(int payCompType,long payComsn,long langId);
}
