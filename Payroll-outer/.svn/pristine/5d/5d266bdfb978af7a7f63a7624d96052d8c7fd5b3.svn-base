package com.tcs.sgv.pension.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;

/**
 * TrnPensionItCutDtls specific Service Interface
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */

public interface TrnPensionItCutDtlsDAO extends GenericDao<TrnPensionItCutDtls, Long>
{
	List getPKForTableTrnPensionItCutDtls(final long lBDPenReqId,final String lStrPenCode,final String lStrTypeFlag)  throws Exception;
	List<TrnPensionItCutDtls> getTrnPensionItCutDtlsVO(String lStrCutType,final String lStrLangId,final long lBDPensReqId,final String lStrPenCode) throws Exception;	
}
