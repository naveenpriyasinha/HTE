package com.tcs.sgv.eis.zp.ZpReportingDDO.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;

public interface ZpReportingDAO extends GenericDao
{
	public List getDDOLstpage(long postId,long month,long year);
	public List getSchemeCodeByPost(Long post);
	public List getSubDDOByPost(Long post) ;
	public List getReptDDOBillDtls(String ddocode,long month,long year,String  schemeCode, String subSchemecode);
	public List getSubDDOsOffc(Long postId);
	public Object getLocID(String ddoCode);
	public int rejectPaybillByLevelTwo(String billNo, long month, long year);
}
