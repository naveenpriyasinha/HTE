package com.tcs.sgv.common.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.SgvaBudbpnMapping;

/**
 * 
 * @author 602399
 * 
 */

public interface BudgetHdDtlsDAO 
{
	public boolean isValidBudgetHeads(String lStrDemandCode, String lStrBudMjrHdCode, String lStrBudSubMjrHdCode, String lStrBudMinHdCode, String lStrBudSubHdCode, String lStrDtlsHd, Long lLongLangId, String lStrMjrHdType);
	
    public List getGrantHeadsForDDO(String lStrDDOCode, String lStrPlNpl, String lStrDDOStatus, Long lLngFinYrId, Long lLngLangId);
    
    public List getHeadsForDDOBySchemeNo(String lStrSchemeNo, String lStrDDOStatus, Long lLngFinYrId, Long lLngLangId);
    
    public SgvaBudbpnMapping getBPNInfoFrmDmd(String lStrDmdCode, Long lLngLangId);

	public List getSchemeNoByDDOGrantHead(String lStrDemandCode, String lStrMajorHead, String lStrSubMajorHead, String lStrMinorHead, String lStrSubHead , String lStrDDOStatus, Long lLngFinYrId, Long lLngLangId);
}
