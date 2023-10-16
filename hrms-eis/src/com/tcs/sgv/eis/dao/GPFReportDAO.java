package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;


@SuppressWarnings("unchecked")
public interface GPFReportDAO extends GenericDao<HrPayPaybill, Long>
{
	public List getGpfForClassFourTotalDtls(String BillNo,String month,String year,long locationId,String PFSeries);
	public List getGpfForOtherThanClassFourTotalDtls(String BillNo,String month,String year,long locationId,String PFSeries);
	public List getGpfForClassFour(String BillNo,String month,String year,long locationId,long empUserId);
	public List getGpfForOtherThanClassFour(String BillNo,String month,String year,long locationId,long empUserId);
	public List getLoanDetailsForAIS(long loanId,long BillNo,long Month,long year,long locId);
	public List getLoanDetailsForAISInt(long loanId,long BillNo,long Month,long year,long locId);
	public List getLoanDetailsForHBACA(long loanId,long BillNo,long Month,long year,long locId);
	public List getLoanDetailsFor7610(long loanId,long BillNo,long Month,long year,long locId);
	public List getGpfTotalDtls(String BillNo,String month,String year,long locationId, String PFSeries, long loantype);
}