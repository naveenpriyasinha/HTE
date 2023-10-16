package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.eis.valueobject.HrForm16Dtls;
import com.tcs.sgv.eis.valueobject.HrForm16TaxDeducDtls;

public interface HrForm16TaxDeducDao {
 public List<HrForm16TaxDeducDtls> getDeducDtls(HrForm16Dtls form16Dtls);
}
