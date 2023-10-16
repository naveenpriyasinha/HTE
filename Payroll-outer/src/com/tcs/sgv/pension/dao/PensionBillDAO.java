package com.tcs.sgv.pension.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.valueobject.RltBillParty;

public interface PensionBillDAO 
{
	public List getClaimantDDOInfo(Long lClaimantpostID) throws Exception;
	public List<RltBillParty> getPensionerPartyDtls(Map inputMap) throws Exception;
	public List getPartyAcNoDtls(String lStrPnsnerCode) throws Exception;
}
