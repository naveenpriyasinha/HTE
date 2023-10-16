package com.tcs.sgv.eis.dao;
//Comment By Maruthi For import Organisation.
import java.util.List;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;

public interface SalRevMstDAO extends GenericDao<HrPaySalRevMst, Long> {

	public List getAllSalRevMstData( long revStatus);
	public List getRltBillTypeEdpData();
	public HrPaySalRevMst getSalRevMst(long salrRevId);
	public List getOrderDataFromPara(String newOrderName,String date , long revStatus);
	public List<HrPaySalRevMst> getActiveArrears(int monthGiven, int yearGiven,CmnLocationMst cmnLocationMst,String salRevId);
}

