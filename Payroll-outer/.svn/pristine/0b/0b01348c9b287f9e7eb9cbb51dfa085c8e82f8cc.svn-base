package com.tcs.sgv.common.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * BudgetHdService 
 * This interface contains methods for budget head structure
 * i.e., to get Head Structure, to get Grant Amount, to validate Head Structure,
 * to get Scheme No. frequently used. It contains method for inserting remarks,
 * billDetails, budgetHead, ReceiptDetails, movement, bill-challan mapping. It
 * also contains method for getting intimation, department List, Lookup Values,
 * nextSeqNum, Bill Control No.
 * This interface is implemented in BudgetHdServiceImpl.
 *  
 * Date of Creation : 7th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * ===================== 
 * 
 */
public interface BudgetHdService {
	public ResultObject getDmnds(Map objectArgs);

	public ResultObject getMajorHead(Map objectArgs);

	public ResultObject getSubMajorHead(Map objectArgs);

	public ResultObject getMinorHead(Map objectArgs);

	public ResultObject getSubHead(Map objectArgs);

	public ResultObject getDetailHead(Map objectArgs);

	public ResultObject getDetailHeadByDmnd(Map objectArgs);

	public boolean validateHeads(Map objectArgs);

	public ResultObject getGrantAmount(Map objectArgs);

	public ResultObject getDeptByLocId(Map objectArgs);

	public ResultObject getMajorHeadByDemand(Map objectArgs);

	public ResultObject getSubMajorHeadByDemand(Map objectArgs);

	public ResultObject getMinorHeadByDemand(Map objectArgs);

	public ResultObject getSubHeadByDemand(Map objectArgs);

	public String getSchemeCodeBySubHead(Map objectArgs);

	public ResultObject getSchemeNoByDmnd(Map objectArgs);
}
