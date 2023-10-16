package com.tcs.sgv.user.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;


/**
 * @author 212951
 *
 */
public interface UserPostMappingDao extends GenericDao
{
	public List getUserPostMappingData(long langId); // Added By Divyesh for user-post mapping screen
}
