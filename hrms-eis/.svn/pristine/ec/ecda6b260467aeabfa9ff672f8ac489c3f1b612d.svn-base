package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;

public interface BankMasterDAO extends GenericDao<MstBankPay, Long>{

	List getAllBankMasterData(long langId);
	public MstBankPay getBankIdData(long BankId,long langId);
	public List checkBankName(String bankName,long langId);
	public HrEisBankMst getBankMstVOByBranchCodeAndLang(String strBankCode, long langId); // added by shilpi
}
