package com.tcs.sgv.pension.dao;

import java.util.List;
import java.util.Map;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public interface TreasuryTransferCaseDAO {
	
	public List getPensionerDtl(String lPPONo, Long lLngLangId,String lStrLocCode) throws Exception;
	public List getAllTreasuryLst() throws Exception;
	public List getAllStateLst(Long lLngLangId) throws Exception;
	public TrnPensionRqstHdr getTrnPensionRqstHdrForReceive(String lStrPPONO,String lStrLocCode) throws Exception;
	public MstPensionerDtls getMstPensionerDtls(String lStrPensionerCode) throws Exception;
	public List getTrasferCases(Map inputMap) throws Exception;
	public Long getRejectedTransferCasePK(String lStrPPONo,String lStrLocCode) throws Exception;
}
