package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.RltBankBranchPay;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;

public interface BranchMasterDAO extends GenericDao<RltBankBranchPay, Long>
{

	List getAllBranchMasterData(long langId);
	//public RltBankBranch getBranchIdData(String branchId);
	public RltBankBranchPay getBranchIdData(String branchId);
	public List getAllCountries(long langId);
	public List checkBranchName(String branchName,long bank_id,long langId);
	public List getStateFromCountry(long countryId,long langId);
	public List getDistrictsFromState(long stateId,long langId);
	public List getTalukasFromDistrict(long distId,long langId);
	public List getVillagesFromTaluka(long talukaId,long langId);
	public List getAllBranchs(long bankId,long langId);
	public List getBranchIdFromBankCode(String branchId);
	/* Added By Shilpi - 14-03-08 - Starts */
	public List getBranchDetailsByCodeAndLangId(long BankId,long langId);
	public HrEisBranchMst getBranchMstVOByBranchCodeAndLang(String strBranchCode, long langId);
	/* Added By Shilpi - 14-03-08 - Ends */
}
