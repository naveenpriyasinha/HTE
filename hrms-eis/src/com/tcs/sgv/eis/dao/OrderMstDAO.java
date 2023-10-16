package com.tcs.sgv.eis.dao;

//comment By Maruthi For Unused imports Removed.
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;

public interface OrderMstDAO extends GenericDao<HrPayOrderMst, Long> {
	public List getAllData(String ddo,String locationCode);
	
	public List getAllOrderData(long locId,String TodaysDate);
	
	public List getAllPostData(long locId,String TodaysDate);
	
	 //public List getAllData(String locCode);
	public List getOrderMstDataById(Map headValues, long postType);

	public HrPayOrderMst getOrderMstDataByid(long orderId);

	public List checkOrderNameAvailability(String newOrderName);

	public List getOrderDataFromPara(String newOrderName, String dept, Date date);

	public List getSearchOrderData(String strOrderName,
			String ordermasterstrStartDate, String strEndDate, String strLocCode);

	public List getOrderName();

	public List getSubDDOs(String locId, String talukaId, String ddoCodeSelected);

	public List getpostRole(Long postId);

	public List getSubGRType(Long parentGRID);

	public List getSubLocationDDOs(String postId);

	// Added By Mayuresh

	public List getExpiryData(long locationCode);

	// Ended By Mayuresh

	// Added by Roshan

	public String districtName(String ddoCode);

	public List allTaluka(String districtID);

	public long findUsertype(String ddoCode);

	public long findLevel(String ddoCode);

	public List getOfficeInATaluka(Long talukaId, String ddoCode);

	long checkUser(String ddoCode);
	
	long findUsertypeToCheckLevel4(String ddoCode);
	
	long findUsertypeToCheckLevel3(String ddoCode);

	public List getFilterDdoCode(String locationCode);

	public List getAllOrderData(String ddo);
	

}
