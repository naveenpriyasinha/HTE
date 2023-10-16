package com.tcs.sgv.pension.dao;

import java.util.List;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;

public interface TrnPensionerRivisionDtlsDAO extends GenericDao<TrnPensionerRivisionDtls,Long>
{
	public List getPKForTableRevisionDtls(String lStrPenCode);
	public Long getPKForRevisionDtls(String lStrPenCode) ;
}
