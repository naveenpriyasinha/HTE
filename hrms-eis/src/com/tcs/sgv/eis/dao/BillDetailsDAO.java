package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.CMPRecord;
import com.tcs.sgv.eis.valueobject.HrPayCmpDtls;

public interface BillDetailsDAO extends GenericDao {

	List getCommonDetails(String AuthNo);

	List getPaybillIdFromAuthNo(String authNo);

	List getCommonDetailsforUID(String AuthNo);

	List getNonGovDeductionforDDO(String payBillGrpId);

	List getDdoDtls(String ddoCode);

	void updateCMPFlag(String authNo,String flag);

	List getNonGovDedFromPaybillID(long paybillId);

	public List getOtherDedForDDO(String authNo);

	public long PRNGenerator(String treasuryCode, int noOfPayees);

	public void saveCmpDetails(CMPRecord record);

	public void saveCmpPayDetails(HrPayCmpDtls cmpDtls);

	List<CMPRecord> getListOfBillDetails(String authNo);

	int getCount(String authNo);

	List getNonGovDedFromPaybillID(String authNo);

	void modifyCMPfileGen(String authNo);

}
