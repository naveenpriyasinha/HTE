package com.tcs.sgv.hr.payincrement.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface PayIncVOGenerator {

	public  ResultObject generateMap(Map objectArgs);

	public  ResultObject Display(Map objectArgs);

	public  ResultObject transaction(Map objectArgs);

	public  ResultObject UpdateMstt(Map objectArgs);

	public  ResultObject UpdateMstf(Map objectArgs);

}