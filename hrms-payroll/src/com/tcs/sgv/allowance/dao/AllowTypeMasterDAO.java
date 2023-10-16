/**
 * This is an Interface for Data access object for Allowance Type Master.
 * Made By:- Urvin shah.
 * Date:- 14/07/2007.
 */
package com.tcs.sgv.allowance.dao;

import java.util.List;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.core.dao.GenericDao;

public interface AllowTypeMasterDAO extends GenericDao<HrPayAllowTypeMst, Long>{

	List getAllowTypeMasterData(long langId);	
	public List checkAllowNameAvailability(String newAllowName,long langId);
	public HrPayAllowTypeMst getAllowTypeMasterDataByCode(long allowTypeCode,long langId);
	public HrPayAllowTypeMst getAllowTypeMasterData(long allowCode,long langId);
}
