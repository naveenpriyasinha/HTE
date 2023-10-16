package com.tcs.sgv.dss.service;

import java.util.HashMap;
import java.util.Map;

import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptPaymentDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;

public interface DssDataService
{
	public HashMap insertExpData(Map inputMap);
	
	public HashMap insertExpEdpData(Map inputMap);
	
	public HashMap insertReceiptData(Map inputMap);
	
	public HashMap insertPaymentData(Map inputMap);
	
	public HashMap deleteExpData(Map inputMap);
	
	public HashMap deletePaymentData(Map inputMap);
	
	public HashMap deleteReceiptData(Map inputMap);
	
	public HashMap deleteExpEdpData(Map inputMap);
	
	public RptExpenditureDtls getExpData(Map inputMap);
	
	public RptExpEdpDtls getExpEdpData(Map inputMap);
	
	public RptReceiptDtls getReceiptData(Map inputMap);
	
	public RptPaymentDtls getPaymentData( Map inputMap);
	
	public HashMap updateExpData(Map inputMap);
	
	public HashMap updateExpEdpData(Map inputMap);
	
	public HashMap updateReceiptData(Map inputMap);
	
	public HashMap updatePaymentData(Map inputMap);
	
	public HashMap BulkUpdateData(Map inputMap);
	
	
}
