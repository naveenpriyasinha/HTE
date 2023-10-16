package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;

public interface OtherDetailDAO extends GenericDao<HrEisOtherDtls, Long>
{
	public List getAllOtherData();
	//public HrEisOtherDtls getOtherData(String sid, Long langid);
	
	
	public List getEmpAvailable(long empId);
	public List getQuaterType(long langId);
	public List getPostDetailData(long langId,long userId);
	public HrEisOtherDtls getOtherData(Long empId);
	public List getAllOtherEmpDataHrr(long userId);
	public List getBillwiseAllOldCommissionEmployeees( long langId, long locId, long billId );
	public List getSgdId(long empId);
	public List getPayCommission();
	public List getDCPSFlag(long empId);
	public List getDCPSData(long empId,int month,long finYrId);
}
