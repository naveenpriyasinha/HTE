/**
 * 
 */
package com.tcs.sgv.billproc.counter.service;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * PhyBillServiceImpl 
 * This interface contains method declaration for opening
 * inwarded bills online as well as physical, show saved bills, intimation ,
 * getting Hyrarchy User 
 * 
 * Date of Creation : 7th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History =====================
 * 
 */
public interface PhyBillService 
{	
	public ResultObject getDigiSig(Map objectArgs);
	public ResultObject receiveOnlineBill(Map objectArgs);
	public ResultObject getMyIntimation(Map objectArgs);
	public ResultObject getHyrarchyUser(Map objectArgs);
	public ResultObject updateApproveBills(Map objectArgs);
	public ResultObject getSavedBills(Map objectArgs);
	public Map insertPhyBillDetails(Map objectArgs);
	public ResultObject CreateBillFromWF(Map inputMap);
	public ResultObject forwardBillFromWF(Map inputMap);
	public ResultObject loadInwPhyBillScreen(Map inputMap);
	public List getMyBills(Map inputMap);
	public ResultObject saveAttachment(Map objMap);
	public ResultObject returnBillFromWF(Map inputMap);
	public ResultObject releseToken (Map inputMap);
	public ResultObject getTreasuryUsers(Map objectArgs);	
}
