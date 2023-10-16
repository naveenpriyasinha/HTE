package com.tcs.sgv.dss.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.SessionFactory;

public interface DSSQueryDAO{
/* Added By Shyamal*/
	
	
	public void setSessionFactory(SessionFactory sessionFactory);
	public ArrayList getTopNPartyDtlRpt(String topN,String toDate,String fromDate,long langId);
	public ArrayList getTopNMjrheadDtlRpt(String option,String topN,String toDate,String fromDate,long langId);
	public ArrayList getTopNBillsDtlRpt(String topN,String toDate,String fromDate,long langId);
	public ArrayList getTopNChallansDtlRpt(String topN,String toDate,String fromDate,long langId);
	public ArrayList getTopNDDOsDtlRpt(String topN,String toDate,String fromDate,long langId);
	public ArrayList getTopNTreasuryByExpDtlRpt(String topN,String toDate,String fromDate,long langId);
	public ArrayList getTopNTreasuryByRecDtlRpt(String topN,String toDate,String fromDate,long langId);
	public ArrayList getTreasuryRpt(String office,String toDate,String fromDate,long langId);
	public ArrayList getsubTreasuryRpt(String office,String toDate,String fromDate,long langId);
	public ArrayList getDDORpt(String office,String toDate,String fromDate,String ddo, String finYear,long langId);
	public ArrayList getDDOBillRpt(String office,String toDate,String fromDate,String ddo,long langId);
	public String getTreasuryName(String locid);
	public String getStrLangId(Long lILangId);
	public String getDDOName(String ddoid);

	/* End by Shyamal */
	
	/* Added By Parth*/
	
	//public HashMap getAllMjrHdsForDept(HashMap lHshPara);
	
	public HashMap getBudEstForScheme(HashMap lHshPara);
	
	public HashMap getGrantForScheme(HashMap lHshPara);
	
	public HashMap getExpForSchemeMjrHd(HashMap lHshPara);
	
	public int getFinancialYear(String lStrFromDate,String lStrToDate);
	
	public ArrayList getSubFundMjrRange(int lintFundType);
	
	public HashMap getConsolidatedRcpBudAmt(ArrayList lArrMjrHd,int lFinYrId);
	
	public HashMap getReceiptData(ArrayList lArrMjrHdRange,int liFinYrId,String lStrFrmDate,String lStrToDate);
	
	//public HashMap getProgressiveExpAmt(ArrayList lArrMjrHdRange,int liFinYrId, String lToDate);
	
	public HashMap getFundMjrRange(int lintFundType);
	
	public HashMap getConsolidatedExpBudAmt(ArrayList lArrLstMjrHd ,int lFinYrId );
	
	public HashMap getExpenditureData(ArrayList lArrMjrHdRange,int liFinYrId,String lStrFrmDate,String lStrToDate);
	
	//public double getCSSAmount(ArrayList lArrMjrHdRange,int liFinYrId);
	
	public HashMap getGrantAmt(ArrayList larrMjrHdRange,long lFinYrId ,String LangId);
	
	public HashMap getPublicContingencyFund(ArrayList larrMjrHdRange,long lFinYrId,String LangId);
	
	public String getStringLangId(Long lLngLangId);
	
	public ArrayList getSubFundMjrRange(long lintFundType);
	
	public HashMap getProgressiveExpAmt(ArrayList lArrMjrHdRange,int liFinYrId, String lToDate);
	
	public String getFundName(long fundId);
	
	public HashMap getMjrHDWiseBudRcptData(int StartLimit,int EndLimit,int FinYearId,String langId);
	
	public HashMap getMjrHDWiseCurrRcptData(int StartLimit,int EndLimit,int FinYearId,String langId ,String lStrFrmDate , String lStrToDate);
	
	public HashMap getMjrHDWiseBudExpData(int StartLimit,int EndLimit,int FinYearId,String langId , String lStrFrmDate,String lstrTodate);
	
	public HashMap getMjrHDWiseBudCSSData(int StartLimit,int EndLimit,int FinYearId,String langId ,String lStrFrmDate ,String lstrTodate);

	
	public String getFundRange(long lintFundType);
	
	public HashMap getSubFundExpGrantAmt(int StartLimit,int EndLimit,long lFinYrId ,String LangId);
	
	public HashMap getMjrHdWiseExpenditureData(int StartLimit,int EndLimit,int liFinYrId,String lStrFrmDate,String lStrToDate);

	/* Ended By Parth*/
	
	/* Added By Maneesh*/
	public ArrayList getAllDept(int lintLangId);
	
	public HashMap getDeptGrantAmt(HashMap lHshMapGrantPara);
	
	public HashMap getBudEstForDept(HashMap lHshMapBudPara);

	public HashMap getExpForDept(HashMap lHashPara);
	
	public ArrayList getMjrRangeForFundName(String strFundName);
	
	public ArrayList getAllBudgetTypes(String lStrLangId,String lStrLocId);
	
	public ArrayList getAllMjrHdsForDept(HashMap lHshPara);
	
	public HashMap getBudEstForMjrHd(HashMap lHshPara);
	
	public HashMap getGrantForMjrHd(HashMap lHshPara);
	
	public HashMap getExpForDeptMjrHd(HashMap lHashPara);
	
	public ArrayList getAllSchemesForDept(HashMap lHshPara);
	
	public double getCSSAmount(ArrayList lArrMjrHdRange,int liFinYrId);

	public String getStringLangId(long lLngLangId);
	
	public String getTodayDate();
	
	public String getFromDate(String DateFrom);
	
	public HashMap getCSSAmount(String lStrMajorHdCode,int lIntLocId,HashMap lHshPara);
	
	/* End By Maneesh*/
}