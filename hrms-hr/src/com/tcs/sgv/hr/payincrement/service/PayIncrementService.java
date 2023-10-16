package com.tcs.sgv.hr.payincrement.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface PayIncrementService {

	public  ResultObject Month(Map objectArgs);

	/*
	 * method to search the conditions basic search method with all other
	 * methods being called
	 */
	public  ResultObject SearchDesig(Map objectArgs);

	/* transaction table data storage */
	public  ResultObject transactiondata(Map objectArgs);

	/* final display page view method */
	public  ResultObject displayDtls(Map objectArgs);

	public  ResultObject ApprovePayIncrement(Map objectArgs);

	public  ResultObject UpdateMstf(Map objectArgs);

	public  ResultObject History(Map objectArgs);

}