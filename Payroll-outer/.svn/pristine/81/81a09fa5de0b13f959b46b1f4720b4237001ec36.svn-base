package com.tcs.sgv.pension.dao;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;

public interface TrnPensionBillHdrDAO extends GenericDao<TrnPensionBillHdr, Long>
{
	int getBillGenerationMonth(String lStrPensionerCode) throws Exception;
	TrnPensionBillHdr getTrnPensionBillHdr(String lStrBillNo, String lStrBillType) throws Exception;
	TrnPensionBillHdr getUniqueTrnPensionBillHdr(Long lLngBillHdrId) throws Exception;
}
