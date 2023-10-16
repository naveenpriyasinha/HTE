package com.tcs.sgv.onlinebillprep.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.common.valueobject.RltBillParty;

public interface OnlineBillDAO {
	
	public List getMySavedBills(Map lMapInput);
    public List getPKForTable(Long lLngBillNo);
    public TrnBillRemarks getRmrksForCurrUser(Long lLngBillNo, Long lLngUserId);
    public List<RltBillParty> getBillPartyRltInfo(Long lLngBillNo);
    public List getBillsSentToTO(Long lLngPostId);
}
