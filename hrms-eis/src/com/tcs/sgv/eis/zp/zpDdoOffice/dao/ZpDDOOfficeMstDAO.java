package com.tcs.sgv.eis.zp.zpDdoOffice.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;

public interface ZpDDOOfficeMstDAO extends GenericDao<ZpAdminOfficeMst, Long>
{
	public List searchZpAdminOfficeDetailsList (ZpAdminOfficeMst zpAdminOfficeMstVO, int startIndex, int pageSize) throws Exception;
	
	public List getAllDDOOfficeDtlsData();
	
	//added by vaibhav tyagi: start
	public List getAllDDOOfficeDtlsData(String districtSelected,String talukaSelected,String adminTypeSelected);
	//added by vaibhav tyagi: end
}
