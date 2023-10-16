package com.tcs.sgv.pension.dao;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;

public interface TrnPensionBillDtlsDAO extends GenericDao<TrnPensionBillDtls, Long>
{
	TrnPensionBillDtls getTrnPensionBillDtls(long TrnPensionBillHdrPK) throws Exception;
	String getDesgDesc(String lStrDesignation) throws Exception;
	int getMaxOfForMonth(String lStrPensionerCode,String lStrPPONumber,String lStrRecoveryFromFlag)  throws Exception;
}
