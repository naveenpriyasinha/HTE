package com.tcs.sgv.lcm.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface LcChequeCancelService 
{
	public ResultObject getChequeDtls(Map objectArgs);
	public ResultObject updateChqCancel(Map objectArgs);
}
