package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;

public interface OrgSchemeMstDAO extends GenericDao<OrgSchemeMstVO, Long>
{

	 List getLocIds(long postId);
	
	
}
