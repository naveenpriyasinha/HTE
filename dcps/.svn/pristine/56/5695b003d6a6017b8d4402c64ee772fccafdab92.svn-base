package com.tcs.sgv.dcps.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;

public interface TerminalRequestDAO extends GenericDao {

	public List getEmpDtlsForName(String lStrName, String lStrDDOCode) throws Exception;

	public List getAllMissingCreditsForEmp(Long lLongEmpId, Date lDtDOJ, Date lDtTerminationDate) throws Exception;

	public List getAllMissingCreditsSavedForTerminalId(Long lLongTerminalId) throws Exception;

	public List getAllTerminalRequests(String lStrDDOCode, String gStrLocationCode, String lStrUser, String lStrUse)
			throws Exception;

	public Boolean checkTerminalRequestRaisedOrNot(Long dcpsEmpId) throws Exception;

	public void deleteMissingCreditsSavedForTerminalId(Long lLongTerminalId) throws Exception;

	Double getOpeningBalanceForDcpsId(String lStrDcpsId, Long lLngFinYearId) throws Exception;

	Double getTotalMissingCreditsForEmp(String lStrDcpsId) throws Exception;

	Double getTier2ContributionForYear(String lStrDcpsId, Long lLngFinYearId) throws Exception;

	List getContributionTillDate(String lStrDcpsId, Long lLngYearId) throws Exception;

	List getMissingCreditsForDcpsId(String lStrDcpsId) throws Exception;

	Double getPendingEmployerContributionForYear(String lStrDcpsId, Long lLngYearId) throws Exception;

	Double getPaidEmployerContributionForYear(String lStrDcpsId, Long lLngYearId) throws Exception;

	Date getStartDateForFinYear(Long lLngFinYearId) throws Exception;

	Date getEndDateForFinYear(Long lLngFinYearId) throws Exception;

	Long getDcpsEmpIdForDcpsId(String lStrDcpsId) throws Exception;
}
