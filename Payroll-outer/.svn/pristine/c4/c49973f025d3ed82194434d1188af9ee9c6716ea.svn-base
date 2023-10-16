package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.valueobject.SgvaMonthMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeRate;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeSpecial;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
public interface CommonPensionDAO 
{
	List getAuditorBankCodeList(BigDecimal lBDUserId,BigDecimal lBDPostId) throws Exception;
	List getAuditorBranchCodeList(BigDecimal lBDUserId,BigDecimal lBDPostId,String lStrAuditorBankCode, String lStrLocCode) throws Exception;	
	List getAllHeadCode() throws Exception;
	TrnPensionRqstHdr getTrnPensionRqstHdrDtls(String lStrPPONO, String lStrStatus) throws Exception;
	String getAllHeadCodeDesc(String strHeadCode,long langId) throws Exception;
	List getPensionerDtlsfromPpoNo(String ppoNo,long langId) throws Exception;
	List getAllState(long langId) throws Exception;
	List getDistrictsOfState(BigDecimal stateId,long langId) throws Exception;
	List getAllBank(Long langId,String locCode) throws Exception;
	List getBranchsOfBank(String bankCode, Long langId,String locCode) throws Exception;
	List getAllDepartment(long langId) throws Exception;
	List getAllDesignation(long langId) throws Exception;
	List getSubTreasurysOfTreasury(String locCode,long langId) throws Exception;
	List getAllTreasury(long langId) throws Exception;
	List getHodFromDepartmet(BigDecimal strDepCode,long langId) throws Exception;
	List<SgvaMonthMst> getSgvaMonthMstVO(String lStrLangId) throws Exception;	
	String getMonthNo(String lStrLangId,String lStrMonthName) throws Exception;
	List<SgvcFinYearMst> getSgvcFinYearMstVO(String lStrLangId) throws Exception;
    List getBranchByBranchId(String branchCode,Long langId,String locCode) throws Exception;
    List getPensionderDtlsFromBillNo(long BIllNo) throws Exception;
    Double getArrearCutAmtDtls(String lStrPnsnCode) throws Exception;
    Double getRecoverAmtDtls(String pensionerCode) throws Exception;
    List getBillTypeFromHeadCode(BigDecimal lBDHeadCode) throws Exception;
    List getAllBranchsForLocation(String langId, String locationCode) throws Exception;
    String getPensionerCodefromPpoNo(String ppoNo) throws Exception;
    String getBankCodeForPensioner(String lStrPensionerCode,String lStrBranchCode) throws Exception;
    List<TrnPensionRqstHdr> getPensionerDtlsDiffFromPpoNo(String ppoNo) throws Exception;
    List getCurrentUserFromPPO(String lstrPPONo, Long lLngLangId) throws Exception;
    int getLastBillCreatedMonth(String lStrPensionerCode) throws Exception;
    List getDescFromAnyTablesByCode(List inputParameters) throws Exception;
    RltPensionHeadcodeRate getTiAndMdeAllowFromByHeadCode(String lStrHeadCode,String lStrRetDate,String lStrTypeFlag,String lStrBasicPension) throws Exception;
    List getSanctionAuthPrefix(Long lLngId) throws Exception;
    String getPrifixSancAuth(Long authorityCode,Long lLngLangId);
    List getPayScaleList(Map inputMap,Long lLngId) throws Exception;
    Long getLocCodeFromRltPPOTrsryMapping(Long lLngLocCode) throws Exception;
    RltPensionHeadcodeSpecial getTiRateForSpecialHeadCode(String lStrHeadCode,String lStrRetDate,String lStrBasic) throws Exception;
}
