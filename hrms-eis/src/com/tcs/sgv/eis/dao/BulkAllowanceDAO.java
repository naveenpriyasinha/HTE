package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;

public interface BulkAllowanceDAO extends GenericDao<HrPayEmpallowMpg, Long>{

	List getAllBankMasterData(long langId);
	public MstBankPay getBankIdData(long BankId,long langId);
	public List checkBankName(String bankName,long langId);
	public HrEisBankMst getBankMstVOByBranchCodeAndLang(String strBankCode, long langId); // added by shilpi
}
