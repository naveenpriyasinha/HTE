package com.tcs.sgv.eis.zp.zpDistrictOffice.service;

import com.tcs.sgv.core.valueobject.ResultObject;
import java.util.Map;

public abstract interface ZpDistrictOfficeVOGenerator
{
  public abstract ResultObject generateMap(Map paramMap)
    throws Exception;

  public abstract ResultObject validateZpAdminOfficeDetail(Map paramMap)
    throws Exception;
}