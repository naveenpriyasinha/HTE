package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeChargable;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public interface BudgetDtlsDAO {
	
	public TrnPensionRqstHdr getTrnPensionRqstHdrDtls(String lStrPPONO, String lStrStatus) throws Exception;
	public RltPensionHeadcodeChargable getMstPensionHeadcodeDtls(String lStrHeadCode,String lStrBillType) throws Exception;
	
	public List getExpEdpDtl(String lStrPPONO,String lStrEdpCode) throws Exception;
	public List getRcptEdpDtlByBillType(String lStrPnsnerCode,String lStrBillType) throws Exception;
	public List getRcptEdpDtlForMnthly(String lForMonth, String lStrTypeFlag,String lStrPnsnCode) throws Exception ;
	public Map getEdpDetails(BigDecimal headCode) throws Exception;
}
