package com.tcs.sgv.eis.zp.zpDistrictOffice.service;

import com.tcs.sgv.core.valueobject.ResultObject;
import java.util.Map;

public abstract interface ZpDistrictOfficeService
{
  public abstract ResultObject saveZpDistrictOfficeDtls(Map paramMap)
    throws Exception;

  public abstract ResultObject getDistrictOfficeDtls(Map paramMap);
}