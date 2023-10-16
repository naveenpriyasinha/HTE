/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	May 30, 2011		Meeta Thacker								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.dcps.valueobject.PostEmpContri;

/**
 * Class Description -
 * 
 * 
 * @author Meeta Thacker
 * @version 0.1
 * @since JDK 5.0 May 30, 2011
 */
public interface PostEmpContriDAO extends GenericDao {
	List getAllContributions(String userType, Long finYear, String contriMonth);

	Long getSancBudget(Long finYear);

	Long getSancBudgetPK(Long finYear);

	public PostEmpContri getPostEmpContriVOForGivenMonthAndYear(Long monthId, Long yearId);

	public Long getExpenditure(Long finYear);

	public String getBillNumber(Long finYear);

	public Long getExcessAmount(Long finYear);

	public Double getExpInCurrBill(String finYearCode, Long monthId);

	public void updateBillNoAndYearIdForPostEmpcontri(String lStrBillno, Long lLongYearId, String finYearCode,
			Long monthId);

	public void updateVoucherPostEmpStatusOnApproval(String lStrBillno, Long lLongYearId);

	public void updatePostEmplrVoucherDtlsOfApprovedBills(String lStrBillno, Long lLongYearId,
			Long lLongEmplrVoucherNo, Date lDateEmplrVoucherDate);

	void updateTrnDcpsContributionList(String finYearCode, Long monthId);
}
