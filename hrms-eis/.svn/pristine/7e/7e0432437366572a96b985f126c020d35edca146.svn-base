package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
public interface SchedulerBillDAO extends GenericDao<HrPayPaybillScheduler, Long>{
	List getAllData();
	List getAllData2(long locId,long billNo,long day);
	List getPayBillNo(long locId,long billNo);
	public List getBillStructure(long billNo);
	public boolean checkPaybill(int month, int year, long billno);
	public List getAllData(int day,String threadId);
}
