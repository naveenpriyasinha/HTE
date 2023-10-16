package com.tcs.sgv.common.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;

public interface UpdateInstitutionNameDAO extends GenericDao{

	List getAllData(String DDOCodeForname);

	void updateOrgInstName(String orgDdoCode, String orgInstName);

}
