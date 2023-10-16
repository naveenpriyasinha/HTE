package com.tcs.sgv.common.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.CmnDistrictMst;

public interface LocationDAO 
{
	public List getDeptLocation(String lookupName,String langName);
	public String getDeptCodeByLocaId(long billNo,String langName);
	public String getDeptCodeByLocId(String locId, String langName);
	public List getLocByDept(String deptName);
	public List getChdLocByPrntLocId(Long parentLocId);
	public List getLocByDept(String lStrDeptCode, Long lLngLangId, Long lLngDbId);
	public CmnDistrictMst getDistCodeFrmLocId(String lStrLocId, Long lLngLangId);
}
