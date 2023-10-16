package com.tcs.sgv.common.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDao;

/**
 * 
 * Common DAO for SELECT/DELETE/QUERIES (NAMED QURIES - written in hbm files)
 * 
 * @author Amar Patel
 *
 */

public interface IFMSCommonDAO extends GenericDao{
	
	List findByNamedQuery(String queryName) throws Exception;
	List findByNamedQuery(String queryName, Map<String, Object> options) throws Exception;
	int deleteOrUpdateByNamedQuery(String queryName) throws Exception;
	int deleteOrUpdateByNamedQuery(String queryName, Map<String, Object> options) throws Exception;
	Map<String,List<CmnLookupMst>> getPartialCommonLookupMstVo(List lLstLookUpNames, Long lLangId) throws Exception; 
}
