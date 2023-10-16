package com.tcs.sgv.exprcpt.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.exprcpt.valueobject.TrnVoucherDetails;

public interface VoucherDAO  {
	public List getVouchers(Long postId, List billLst, Long langId, String slocCode);
	public void accVouchersFromChqBranch(Map objectArg);
	public List getVouchersForDist(Long postId, List billLst,String slocCode);
	public List<TrnVoucherDetails> getUndistributedVouchers(Long postId, List billLst, String slocCode);
	public void markVouchDist(Map objectArg);
	public List getVouchersForDetPost(Long postId, List billLst,Long langId,String slocCode) ;
	public void accVouchersForDetPost(Map objectArg);
	public List getVouchersListForDet(Long postId, List billLst,Long langId, String slocCode,Long lngVchrSt) ;
	public int countVouchersDist(Long postId, List billLst,String slocCode) ;
	public List getSearchVoucher(Map map, List billLst);
	public List getMajHdByBillNo(long billNo);
	public TrnVoucherDetails getNextVoucherMjrHdWise(String billNo,String locationCode);
	public TrnVoucherDetails getNextVoucherMonthWise(String billNo,String locationCode);
}

