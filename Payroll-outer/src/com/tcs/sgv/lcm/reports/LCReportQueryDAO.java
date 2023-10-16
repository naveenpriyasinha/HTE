package com.tcs.sgv.lcm.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcBudgetPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDtlPosting;

public interface LCReportQueryDAO 
{
	public ArrayList getMonths(String lStrLangId, String lstrLocId) ;
	public ArrayList getBanks(String lStrLangId, String lstrLocId) ;
	public long getLongLangId(String lStrLangId);
	public String getStrLangId(long lILangId) ;
	
	public void setSessionFactory(SessionFactory sessionFactory);
	
	public ArrayList getLcExpenditureReport(String lStrFinYr,int lIAdvNo,String lStrMonthCode,String lStrBankCode,String lStrLcValidFrmDt,String lStrLcValidToDt,String lStrEntryDtFrmDt,String lStrEntryDtToDt,long lLngLangId,String lStrLoginLocCode,String lStrAdvStatus);
	
	public TrnLcDtlPosting getDtlPostingDtlsForUpdate(String lStrLcExpId) ;
	public LcDivisionInformationVO getDivisionDtlsForUpdate(String lStrDivCode,String lStrLangId) ;
	public ArrayList getChqPostingDtlsForUpdate(String lStrLcExpId) ;
	public ArrayList getBudgetPostingDtlsForUpdate(String lStrLcExpId) ;
	public ArrayList getDeductionPostingDtlsForUpdate(String lStrLcExpId);
	
	public boolean deleteDtlPosting(String lStrLcExpId) ;
	public boolean deleteChequePosting(long lILcExpId,long lIChqId) ;
	public boolean deleteBudgetPosting(long lILcExpId,long lIBudId) ;
	public boolean deleteDeductionPosting(String lStrLcExpId) ;
	
	public ArrayList getTreasuryLcUsageReport(long lLngLoginLangId) ;
	public ArrayList getDivisionLcUsageReport(long liDistCode,long lLngLoginLangId) ;
	
	public boolean updateDtlPosting(TrnLcDtlPosting lcDtlPostingVo) ;
	public boolean updateChequePosting(TrnLcChequePosting lcChqPostingVo) ;
	public boolean updateBudgetPosting(TrnLcBudgetPosting lcBudPostingVo) ;
	public boolean updateDeductionPosting(TrnLcDeductionPosting lcDedPostingVo) ;
	
	public String changeDateFormat(String lStrDtFrm);
	public long getLocIdByPostId(long lLngPostId,long lLngLangId);
	
	public ArrayList getDivisionsOfLoginTsry(Hashtable otherArgs,String lStrLangId, String lStrLocId);
	public ArrayList getDivisionsOfLoginTsry(String lStrLangId, long lLngPostId);
	public ArrayList getDepartment(String lStrLangId, String lStrLocId);
	public ArrayList getApproved(String lStrLangId, String lStrLocId) ;
	
	public ArrayList getLcTsryReportForVerification(String lStrDivCode,int lIAdvNo,String lStrMonthCode,String lStrBankCode,String lStrDeptCode,String lStrApprovedCode,String lStrFrmDt,String lStrToDt,long lLngLangId,long lLngLoginLocId,long lIFinYrId);
	public boolean approveAdviceReceived(long lILcExpId) ;
	public boolean updateApprovedLcAmount(long lILcExpId,String lStrSign) ;
	public boolean updateApprovedLcAmount(String lStrLcDivisionCode,double lDblExpTotal,String lStrSign);
	public double getLcExpAmount(long lIBudId,long lILcExpId);
	public String getLcAdviceApprovedStatus(long lILcExpId);
	
	public ArrayList getLcBudIdByLcExpId(long lILcExpId);
	public ArrayList getLcDedIdByLcExpId(long lILcExpId);
	public String getTsryDeptIdByLocationCode(String lStrLocCode);
	
	public ArrayList getDivisionWisePaidChequesReport(String lStrDivisionCode[],String lStrFrmDt,String lStrToDt,long lLngLangId,long lIFinYrId,String LstrLoginLocCode)  ;
	public ArrayList getDivisionWisePaidChequesReportDivisionDtls(String lStrDivCode,String lStrFrmDt,String lStrToDt,long lLngLangId,long lIFinYrId);
	
	public ArrayList getDivisionWiseLCPaymentLedgerReport(String lStrDivisionCode,String lStrFrmDt,String lStrToDt,String lStrAccDt,long lLngLangId,long lIFinYrId) ;
	public ArrayList getDivShrtNmAndDivNm(String lStrDivCode, long lLngLangId) ;
	
	public ArrayList getLCPaymentApprovalReport(String lStrDivisionCode,String lStrFrmDt,String lStrToDt,String lStrAdviceNo,long lLngLangId,long lIFinYrId,String LstrLoginLocCode) ;
	
	public ArrayList getAllDivisions(String lStrLangId, String lStrLocId) ;
	public ArrayList getPaidUnpaidChq(String lStrLangId, String lStrLocId);
	public ArrayList getLCChequeStatusReport(String lStrDivisionCode,String lStrDeptCode,String lStrPaidUnpaid,String lStrFrmDt,String lStrToDt,String lStrChqNo,long lLngLangId,long lIFinYrId,String LstrLoginLocCode) ;

	public ArrayList getPlanNonPlan(String lStrLangId, String lStrLocId) ;
	public ArrayList getRevenueCapital(String lStrLangId, String lStrLocId);
	public ArrayList getDeptWiseTsryWise(String lStrLangId, String lStrLocId);
	public ArrayList getLCExpenditureTrackingDATReportDeptWise(String lStrDeptCode,String lStrPlanNonPlan,String lStrRevenueCapital,String lStrFrmDt,String lStrToDt,long lLngLoginLangId,long lIFinYrId,String LstrLoginLocCode);
	public ArrayList getLCExpenditureTrackingDATReportDeptDtls(String lStrDeptCode,String lStrPlanNonPlan,String lStrRevenueCapital,String lStrFrmDt,String lStrToDt,long lLngLoginLangId,long lIFinYrId,String LstrLoginLocCode);
	
	public ArrayList getLCExpenditureTrackingDATReportTsryWise(String lStrDeptCode,String lStrPlanNonPlan,String lStrRevenueCapital,String lStrFrmDt,String lStrToDt,long lLngLoginLangId,long lIFinYrId,String LstrLoginLocCode);
	public ArrayList getLCExpenditureTrackingDATReportTsryDtls(String lStrTsryCode,String lStrDeptCode,String lStrPlanNonPlan,String lStrRevenueCapital,String lStrFrmDt,String lStrToDt,long lLngLoginLangId,long lIFinYrId,String LstrLoginLocCode);
	
	public ArrayList getAllDivisonUnderPost(String[] lStrDivisionCode , String lStrLcNo , String lStrFromDate , String lStrToDate,String lStrLoginLocCode,long lLngLangId);
	public HashMap LcFormBLetterOfCredit(String lStrLcNo);
	public List getLCLedgerAccountsReport(ArrayList lArrDivList,String lStrACDate,String lStrFromDate,String lStrToDate,String lStrChqNo);

	public ArrayList getLCDivisionDashboardReport(String lStrDivCode,String lStrFrmDt, String lStrToDt, long lLngLoginLangId,long lIFinYrId,String lStrLoginLocCode);
	
	public ArrayList getLCBalanceApprovalReport(String[] lStrDivisionCode,String lStrFrmDt, String lStrToDt, long lLngLangId,long lIFinYrId,String lStrLoginLocCode);
	public String getTsryNameByLoggedInTsryCode(String lStrLocCode);
}
