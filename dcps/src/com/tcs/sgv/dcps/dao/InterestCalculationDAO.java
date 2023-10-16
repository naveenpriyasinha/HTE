/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 16, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.dcps.valueobject.MstDcpsContributionYearly;
import com.tcs.sgv.dcps.valueobject.MstDcpsSixPCInterestYearly;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Jun 16, 2011
 */
public interface InterestCalculationDAO extends GenericDao {

	public List getAllDCPSEmployeesForIntrstCalc(Long treasuryCode,String ddoCode,
			String lStrFromDate, String lStrToDate) ;
	
	public Boolean checkEmployeeEligibleForIntrstCalc(Long treasuryCode,String ddoCode,Long dcpsEmpId,
			String lStrFromDate, String lStrToDate) ;

	public List getContriDtlsForGivenEmployee(Long treasuryCode,
			String lStrFromDate, String lStrToDate, Long dcpsEmpId);

	public Double getInterestRateForGivenYear(String lStrYear);

	public MstDcpsContributionYearly getContriYearlyVOForYear(
			Long dcpsEmpId, Long previousYearId);

	public String getDcpsIdForEmpId(Long dcpsEmpId);

	public Long getYearIdForYearCode(String yearCode);
	
	/*public List getAllDCPSEmployeesForIntrstCalcSixPC(Long treasuryCode,String ddoCode);
	
	public MstDcpsSixPCInterestYearly getSixPCYearlyInterestVOForYear(Long dcpsEmpId, Long previousYearId);
	*/
	public List getArrearsDtlsForGivenEmployee(Long dcpsEmpId,Long yearId);
	
	public List getAllEmpsUnderDDO(String lStrDDOCode) throws Exception ;
}
