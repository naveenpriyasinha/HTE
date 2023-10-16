package com.tcs.sgv.exprcpt.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface VoucherService {

  public ResultObject getVouchers(Map objectArgs);
  public ResultObject accVouchersFromChqBranch(Map objectArgs);
  public ResultObject getVouchersForDist(Map objectArgs);
  public ResultObject distributeVouchers(Map objectArgs);
  public ResultObject getVouchersForDetPost(Map objectArgs);
  public ResultObject accVouchersForDetPost(Map objectArgs);
  public ResultObject getVouchersListForDet(Map objectArgs);
  public ResultObject getSearchVoucher(Map objectArgs);
  public ResultObject getVoucherDtls(Map objectArgs);
  public ResultObject deleteExtAddEdpCode(Map objectArgs);
  public ResultObject insertVoucherDtls(Map objectArgs);
  public ResultObject insertEdpDtls(Map objectArgs);
  public ResultObject getEdpDtlsByBillType(Map objectArgs);
}
