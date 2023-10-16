package com.tcs.sgv.pdpla.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pdpla.valueobject.MstPdAccount;

public interface GetHeadDetailDAO extends GenericDao<MstPdAccount, BigDecimal>
{

	Map getMajorHead(String pdPlaAccountNo);
	
}
