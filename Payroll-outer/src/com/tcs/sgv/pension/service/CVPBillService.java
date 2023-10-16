package com.tcs.sgv.pension.service;

import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * CVP Bill Specific Srvice Interface.
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */

public interface CVPBillService 
{
	//To save CVP Bill Specific Data
	ResultObject saveCVPBill(Map inputMap);
	
	//To Show CVP Bill Specific Data
	ResultObject getCVPBillData(Map iMapInput);
	
	//To Show saved bill
	ResultObject viewCVPBillData(Map inputMap);
}
