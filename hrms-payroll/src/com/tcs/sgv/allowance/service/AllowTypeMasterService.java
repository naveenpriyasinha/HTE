/**
 * This is an interface of Allowance Type Master Service.
 * Made By:- Urvin shah
 * Date:- 14/07/2007
 */
package com.tcs.sgv.allowance.service;

import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;

public interface AllowTypeMasterService {
	public ResultObject insertAllowTypeMasterDtls(Map objectArgs);
	public ResultObject getAllowTypeMasterDtls(Map objectArgs);
	public ResultObject getAllowTypeDataByCode(Map objectArgs);
	public ResultObject checkAllowAvailability(Map objectArgs);
}
