package com.tcs.sgv.onlinebillprep.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.SgvoDeptMst;

/**
 * 
 * @author 602399
 * 
 */

public interface BillCommonDAO
{
    public SgvcFinYearMst getFinYrInfo(Date lDate, Long lLangId);
    public SgvoDeptMst getDeptDtls(String lStrDeptId, Long lLngLangId);
    public List getMonthDtls(Long lLngLangId);
    public List getParentPost(Long lLngPostId);
    public int insertDigiMapping(String appId, int seq, String pkVal, String fkVal, int rowNum, String rowId, String key);
    public String getMonthName(Long lLngLangId, Long lLngBillNo);
    public List getBillEDPDtls(Long lLngBillNo, Long lLngLangId);
}
