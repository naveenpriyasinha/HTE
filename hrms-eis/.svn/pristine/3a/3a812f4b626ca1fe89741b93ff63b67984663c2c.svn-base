package com.tcs.sgv.eis.zp.zpDistrictOffice.dao;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;
import java.util.List;

public abstract interface ZpDistrictOfficeDAO extends GenericDao
{
	public abstract List getAdminOffice();

	public abstract List getAdminOfficePK(String paramLong);

	public abstract List getAllDistrictOfficeDtlsData(long paramLong);

	public abstract ZpDistrictOfficeMst getDistOfficeDtls(long paramLong);

	public abstract List getDistrict(long paramLong);

	//added by samdhan
	public abstract Long checkDistOfficeCount(String distCode,String adminOfficeCode);
}