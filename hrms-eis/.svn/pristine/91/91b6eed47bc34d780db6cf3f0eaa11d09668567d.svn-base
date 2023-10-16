package com.tcs.sgv.address.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxn;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;

public interface AddressApproveDao extends GenericDao{
	Map getAddressToApprove(long reqId);
	List<HrEisEmpDtlTxn> getHrEisEmPMst (long reqId);
	List<HrEisEmpMst> getObjectForHrEisEmpMst (List listOfId);
	void updateHrEisEmpTrn(long reqId);
	List<HrEisEmpDtlTxn> getHrEisEmpTrnObjectByRequestId(long reqId);

}
