package com.tcs.sgv.exprcpt.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
public interface ReportQueryDAO 
{	
	public void setSessionFactory(SessionFactory sessionFactory);

	
	
	public ArrayList getDateWiseReceiptRpt(String fMjrHead, String tMjrHead , String fromDate,String toDate,String locId, String langId);
	public ArrayList getHeadWiseChallanRpt(Date fromDate, Date toDate,String locId, String langId);
	public ArrayList getReceiptSubHeadRpt(String fMjrHead, String tMjrHead , Date fromDate,Date toDate,String locId, String langId);
	public ArrayList getBankWiseChallanRpt(String fMjrHead, String tMjrHead , Date fromDate,Date toDate,String locId, String langId);
	public ArrayList getPaymentSubHeadRpt(String fMjrHead, String tMjrHead , String fromDate,String toDate,String locId,String langId);
	public ArrayList getPaidChequeSummaryRpt( String fromDate, String toDate, String locId, String langId);
	public ArrayList getPaidChequeRpt( String fromDate, String toDate, String locId, String langId);
	public ArrayList getUnPaidChequeSummaryRpt( String fromDate, String toDate, String locId, String langId);
	public ArrayList getRBDRpt( String fromDate, String toDate, String locId, String langId);
	public ArrayList getCancelledlapsedTCRpt( String fromDate, String toDate, String locId, String langId, Long chequeTypeId);
	public ArrayList getTCMemoRegRpt(String fMjrHead, String tMjrHead , String fromDate,String toDate,String locId,String langId);
	public ArrayList getSubTCMemoRpt(String fromDate,String toDate,String majorHead,String locId,String langId);
	public List getOutstandingBalanceReport(String sYear,String sMonth,String sLocation_code);
	public ArrayList getTCMemoRegPayRpt(String fMjrHead, String tMjrHead , String fromDate,String toDate,String locId,String langId);
	public ArrayList getSubTCMemoPayRpt(String fromDate,String toDate, String majorHead, String locId, String langId);
	public String getLangName(Long LangId);
	public Long getLangId(String LangId);
	
}
