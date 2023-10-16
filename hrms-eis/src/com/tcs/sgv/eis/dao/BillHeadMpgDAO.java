package com.tcs.sgv.eis.dao;
//Comment By Maruthi for  import Organisation.
import java.util.List;
import com.tcs.sgv.dcps.valueobject.*;

import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;

public interface BillHeadMpgDAO extends GenericDao<MstDcpsBillGroup, Long>{
	public List getAllData();
	public List getAllSubhdData();
	public SgvaBudsubhdMst getAllDatabySubHeadId(long subHeadId);
	public List chkBillHeadDataById(long billId,long headId,long bsubhead_id);
}
