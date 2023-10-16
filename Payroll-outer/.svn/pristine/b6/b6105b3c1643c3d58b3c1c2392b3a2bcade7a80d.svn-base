package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;

public interface MRCaseDAO 
{
	//Methods for MR CASE  start--------------------------------------------------------------------------------->
	
	public String getFinYearDesc(long gLngFinYearId) throws Exception;
	public int getEntriesCount(Date currDate) throws Exception;
	public int getSavedMRCaseDtlsCount(Map inputMap) throws Exception;
	public List getSavedMRCaseDtls(Map inputMap) throws Exception;
	public String getPpoNoFromMedRemId(Long lLngmedRembrsmntId) throws Exception;
	public String getBranchCodeFromMedRemId(Long lLngmedRembrsmntId) throws Exception;
	public  String getPensionerNameFromPPO(String ppoNo) throws Exception;
	public TrnPensionMedRembrsmnt getTrnPensionMedRembrsmntVO(Long lLngmedRembrsmntId) throws Exception;
	public String getBranchByBranchId(String branchCode,Long gLngLangId,String gStrLocationCode) throws Exception;
	ArrayList<TrnPensionMedRembrsmnt> getTrnPensionMedRembrsmntVOArray(String strSelectedRequests) throws Exception;
	public List getLocationReport(String locCode,long langID) throws Exception;
	public BigDecimal getAuditorPostId(String lStrBankCode,String lStrScheme) throws Exception;
	public List getAuditorNameListFromPostId(String strSelectedAuditors) throws Exception;
	public String getUserName(Long gLngUserId) throws Exception;
	public BigDecimal getAuditorPostIdByMedRemId(Long lLngMedRemId) throws Exception;
	public List getMRCasesListWithinPeriod(String fromDate,String toDate) throws Exception;
	public List getAuditorNameListWithinPeriod(String fromDate,String toDate) throws Exception;
	public List getIdsForWithinPeriodRequests(String fromDate,String toDate) throws Exception;
	public String getAuditorNameFromPostId(BigDecimal postId) throws Exception;
	
	//Methods for MR CASE  end--------------------------------------------------------------------------------->
	
	//Methods for MR BILL  start--------------------------------------------------------------------------------->	
	
	public List getMRBillData(String lStrFromDate,String lStrToDate,String lStrBranchCode,BigDecimal BDHeadCode) throws Exception;
	public List getTrnPensionMedRembrsmnt(Long lTrnPensionBillHdrPK) throws Exception;
	public List getBnkBrnchHdcodeList(Long lTrnPensionBillHdrPK) throws Exception;
	
	//Methods for MR BILL  end--------------------------------------------------------------------------------->	

}
