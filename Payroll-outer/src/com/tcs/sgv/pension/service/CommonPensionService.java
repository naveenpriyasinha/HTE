package com.tcs.sgv.pension.service;

import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * Common Pension Service Interface.
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */

public interface CommonPensionService 
{
	ResultObject getAuditorBankCodeList(Map inputMap);
	ResultObject getHeadCodeDesc(Map inputMap);
	String getMyCases(Map inputMap) throws Exception;
    ResultObject getSancAuthPrefix(Map inputMap);
    String getMyMRCases(Map inputMap) throws Exception;
}
