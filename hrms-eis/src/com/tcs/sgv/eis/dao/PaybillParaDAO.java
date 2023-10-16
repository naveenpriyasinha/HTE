package com.tcs.sgv.eis.dao;

import java.util.List;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;

public interface PaybillParaDAO extends GenericDao<HrEisBankMst, Long>{

	List getAllBankMasterData(long langId);
	public HrEisBankMst getBankIdData(long BankId,long langId);
	public List checkBankName(String bankName,long langId);
}
