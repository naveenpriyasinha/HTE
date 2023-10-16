package com.tcs.sgv.eis.zp.zpAdminOffice.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;

public interface ZpAdminOfficeMstDAO extends GenericDao<ZpAdminOfficeMst, Long>
{
	public List searchZpAdminOfficeDetailsList (ZpAdminOfficeMst zpAdminOfficeMstVO, int startIndex, int pageSize) throws Exception;
}
