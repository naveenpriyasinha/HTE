package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.List;
import com.tcs.sgv.pension.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeChargable;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public interface MonthlyPensionBillDAO {
	
	public List getAllScheme(long langId) throws Exception;
	public List getValidPensionerCode(String bankCode, String branchCode, String headCode, String scheme, String lStrMyCases) throws Exception;
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtls(String lStrPnsrCode, String lStrStatus, String lStrHeadCode, String lStrCaseStatus) throws Exception;
	public List getAccountNo(String lStrPensionerCode,String bankCode, String branchCode) throws Exception;
	public ArrayList<TrnPensionBillDtls> getTrnPensionBillDtls(String lStrBillNo) throws Exception;
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsFromPnsnerCode(String lStrPnsrCode, String lStrStatus) throws Exception;
	public TrnPensionBillHdr getTrnPensionBillHdr(String lStrBillNo) throws Exception;
	public Integer getLastMonth(String lStrPnsnrCode, String lStrScheme) throws Exception;
	public ArrayList<MstPensionerFamilyDtls> getMstPensionerFamilyDtls(String lStrPnsnrCode) throws Exception;
	public boolean isValidBill(String forMonth, String bankCode, String branchCode, String headCode, String scheme) throws Exception;
	public String getBankName(String lStrBank) throws Exception;
	public String getBranchName(String lStrBank, String lStrBranch) throws Exception;
	public List getMonthDtls(String lStrMonth, Long langId) throws Exception;
	public ArrayList getArrearDtls(String lStrPensionerCode,String lStrForMonth) throws Exception;
	public ArrayList getItCutDtls(String lStrPensionerCode,String lStrForMonth) throws Exception;
	public Double getRecoveryDtls(String lStrPensionerCode, String lStrStatus, String lForMonth) throws Exception;
	public String getBankCode(String lStrBranch, String lStrLocCode) throws Exception;
	public String getBillNo(String lStrDate, String lStrBank, String lStrBranch, String lStrHeadcode, String lStrScheme) throws Exception;
	public RltPensionHeadcodeChargable getRltPensionHeadcodeChargable(String headcode) throws Exception;
	//public ArrayList<TrnPensionBillDtls> getTrnPensionBillDtlsReport(String lStrDate,String lStrBranch,String lStrScheme) throws Exception;
}
