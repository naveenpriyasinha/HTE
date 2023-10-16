/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 9, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Jun 9, 2011
 */
public interface MatchContriEntryDAO extends GenericDao {

	public List getAllTreasuriesForMatchedEntries(String fromDate, String toDate);

	public List getAllTreasuriesForUnMatchedEntries(String lStrFromDate,
			String lStrToDate);
	
	public List getUnMatchedVouchersForMatching(String lStrFromDate,
			String lStrToDate, Long treasuryCode);
	
	public List getUnMatchedVouchersAllForMatching(String lStrFromDate,
			String lStrToDate, Long treasuryCode);
	
	public void updateVouchersManuallyMatched (Long voucherIdPk) throws Exception;

}
