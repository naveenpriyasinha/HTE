package com.tcs.sgv.pension.service;

import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;

public interface NewPensionBillService {
	
	public ResultObject getNewPensionBillData(Map inputMap);
	public ResultObject getSavedNewPensionBillData(Map inputMap);
}
