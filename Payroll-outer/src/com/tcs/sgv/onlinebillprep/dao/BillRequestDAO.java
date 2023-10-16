package com.tcs.sgv.onlinebillprep.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.onlinebillprep.valueobject.RltBillRqst;
import com.tcs.sgv.onlinebillprep.valueobject.TrnAprvdRqst;

public interface BillRequestDAO 
{
	 public List getApprovedRequest(Date lDtFrmDt, Date lDtToDt, Long lLngBillType,String lDDOCode,
	    		Long lLngDBId,String locCode, Long lLngLangId,String lStrEmpDesgn,String lStrEmpType);
	public TrnAprvdRqst getAprvdRqstDtls(Long lLngRqstId);
    public List<RltBillRqst> getRqstInfoFrmBill(Long lLngBillNo);
}
