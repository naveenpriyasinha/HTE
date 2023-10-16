package com.tcs.sgv.eis.zp.ReportingDDO.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.valueobject.ResultObject;


public interface ReportingDDODao extends GenericDao
{
	public List  getDistrict(long stateId);
	public List  getTaluka(long districtId);
	public List getSubTreasury(long treasuryId);
	public List getTreasury();
	public void updateType(String ddoCode);
	public List getLvl2DDo();
	public List getSeqTable(String lStrLocCode);
	public List  getDDounderDept(String admincode,String fieldLoc, String treasuryId);
	public List getDesgnOffc(String ddoCode);
	//public void insertOrgDdoMst(String lStrDdoCode,String lStrDdoName,String lStrDdoPrsnlName,Long lLngPostId,Long lLngUserIdCrtd,String lStrLocationCode,Long lLngPostIdCrtd,String lstrDeptLocCode,Map inputMap);
	//added by Demolisher
	public List getTreasuryNamefromTreasuryId(Long treasuryId);
	//ended by Demolisher
	
}
