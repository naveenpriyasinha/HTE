package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface PensionCaseDAO {

    public boolean isValidPPONo(String lStrPPONo) throws Exception;

    public List getSentCaseDtls(Map inputMap,Long gLngUserId,Long gLngLangId) throws Exception;
    public List getDesignationList(String lStrLocId,Long lLngDocumentId) throws Exception;    
    public BigDecimal getPostIdOfPresentAuditor(String lStrLocId,Long lLngDocumentId,String lStrPpoNo) throws Exception;
    
    public int getSentCaseDtlsCount(Map inputMap,Long gLngLangId,Long gLngUserId);    
}
