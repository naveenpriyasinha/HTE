package com.tcs.sgv.pension.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * Cut Service Interface.
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */

public interface CutService 
{
	ResultObject getCutDtls(Map inputMap);
	ResultObject saveCutDtls(Map inputMap);
}
