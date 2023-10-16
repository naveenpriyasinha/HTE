package com.tcs.sgv.hr.payincrement.dao;

import java.util.List;

import com.tcs.sgv.hr.payincrement.valueobject.HrEisPayInc;
import com.tcs.sgv.hr.payincrement.valueobject.HrPayincTxn;

public interface PayIncDAO {

	/*method to search with date as the input from user jsp*/
	public  List displaydetails4(String startdate, String Enddate);

	/*method to search payscale of corresponding employee */
	public  List Payscale(long scaleid);

	/*to get data from transaction table with given request id*/
	public  List displaydetails6(long Userid);

	/*method to search basic salary*/
	public  List displaydetails8(long empid);

	public  List displaydetails11(long empid);

	public  void UpdateTran(HrPayincTxn eispayintran);

	/*public long incamt(Long reqid) {
	 long incamt = 0;
	 Session hibSession = getSession();
	 String hql1 = "from  HrModEmpRlt where reqId=('" + reqid
	 + "')";
	 List payfix = new ArrayList();
	 Query myQuery = hibSession.createQuery(hql1);
	 payfix = myQuery.list();
	 if (payfix.size() != 0) {
	 HrModEmpRlt modrlt = (HrModEmpRlt) payfix.get(0);
	 incamt = modrlt.getScaleId().getScaleIncrAmt();
	 
	 }
	 
	 return incamt;
	 }*/
	public  long incamt(long reqId);

	public  HrEisPayInc getdata(long empid);

	public  HrPayincTxn status(long reqid);

	public  String modstatus(long reqid);

	public  List history(long empid);
	
	public long getHigherHierachyPostId(String postId, long docId);
}