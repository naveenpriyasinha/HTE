package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayDDOSchemeMpgVO;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;

public interface HrPayDdoSchemeMpgDAO  extends GenericDao<HrPayDDOSchemeMpgVO, Long>
{
	List getAllData(long langId);
}
