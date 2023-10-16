package com.tcs.sgv.lcm.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface LcDistributionMstService 
{
	public ResultObject getInitialLcDistributionMstDtls(Map objectArgs);
	public ResultObject getDivisionNmAndBalAmt(Map objectArgs);
	public ResultObject insertLcDistributionMst(Map objectArgs);
}
