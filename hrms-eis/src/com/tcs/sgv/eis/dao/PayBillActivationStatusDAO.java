package com.tcs.sgv.eis.dao;

import java.util.List;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;
import com.tcs.sgv.eis.valueobject.HrPaybillActivationStatusMpg;

public interface PayBillActivationStatusDAO extends GenericDao<HrPaybillActivationStatusMpg, Long>
{
	

	public List getActiveData(long langId,long loc_id);
	public List getDeactiveData(long langId,long loc_id);
	public List getSpecificDataAct(String name,long billNo,long langId,long locId);
	public List getSpecificDataDct(String name,long billNo,long langId,long locId);
	public HrPaybillActivationStatusMpg searchPost(long postId); 
	
}
