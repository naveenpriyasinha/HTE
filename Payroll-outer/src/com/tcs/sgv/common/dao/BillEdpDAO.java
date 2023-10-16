package com.tcs.sgv.common.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;

public interface BillEdpDAO {
	
	public List getExpObjHdDtlByBillType(Long subjectId, Long billNo, Long langId, String slocCode);
	public List getRecObjHdDtlByBillType(Long subjectId, Long billNo, Long langId, String slocCode);
	public List getRcptEdpDtlByBillType(Long subjectId,Long billNo,Long langId,String slocCode,String tcBill,String majorHead);
	public TrnBillEdpDtls getBillEdpDtl(String typeEdpId, Long billNo,String sLocCode,String sExpRecRcp);
	
}
