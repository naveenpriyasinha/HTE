package com.tcs.sgv.pension.service;

import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * DCRG Bill Specific Srvice Interface.
 * 
 * @author Aparna Kansara
 * @version 1.0
 */

public interface DCRGBillService 
{
	//	To Show DCRG Bill Specific Data
	public ResultObject getDCRGBillData(Map iMapInput);
	
	//	To save DCRG Bill Specific Data
	public ResultObject saveDCRGBill(Map inputMap);
	
	// To view DCRG Bill After Saved
	public ResultObject viewDCRGBillData(Map iMapInput);
}
