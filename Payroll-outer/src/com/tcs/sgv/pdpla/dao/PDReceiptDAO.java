package com.tcs.sgv.pdpla.dao;

import java.util.Map;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallanDetail;

public interface PDReceiptDAO extends GenericDao<TrnPdChallanDetail,Long>
{
	public Map getSequence(String lPdPlaAccountNo);
}
