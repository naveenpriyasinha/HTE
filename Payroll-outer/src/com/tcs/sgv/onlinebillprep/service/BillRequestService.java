package com.tcs.sgv.onlinebillprep.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface BillRequestService
{
    public ResultObject getBillsAndLocations(Map orgsMap);
    public ResultObject getApprovedRequest(Map orgsMap);
    public ResultObject getRqstData(Map inputMap);
    public ResultObject createBillFrmScratch(Map inputMap);
    public ResultObject getBillType(Map inputMap);
}
