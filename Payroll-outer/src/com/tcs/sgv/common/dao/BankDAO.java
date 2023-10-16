package com.tcs.sgv.common.dao;
import java.util.List;

public interface BankDAO {

	public List getAllBanks(String locCode);
	public List getUnVerifyBank(String locCode);
	public List getUnVerifyDate(String bankCode,String locCode);
}
