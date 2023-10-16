package com.tcs.sgv.pension.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pension.valueobject.TrnPensionPsbDtls;

public interface TrnPensionPsbDtlsDAO extends GenericDao<TrnPensionPsbDtls, Long> 
{
	String validatePPONumberForMonth(String PPONO,int monthYear) throws Exception;
	List getPSBPostedVoucher(String locationCode) throws Exception;
	List getHeadVouchers(String fromMonthYear,String toMonthYear,String headCode,String branchCode,String locationCode) throws Exception;
	List updatePPODtlsForDtlPost(String fromMonthYear,String toMonthYear,String dtlPostHeadcode,String bankCode,String voucherNo,String locationCode) throws Exception;
	long getNextVoucherMjrHdWiseForPSB(String locationCode,String lStrMjrHd)throws Exception;
	Date getBillDateFromVoucher(String voucherNo) throws Exception ;
	long getPKForVoucher(String voucherNo) throws Exception ;
}
