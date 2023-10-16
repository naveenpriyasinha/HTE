package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;

public interface MapEmpWithArrearDAO {
	public List getSalRevOrderData(long locId);
	public List getEmployeeByBillNoDesig(String billSubHeadId,long dsgnId,long locId,String orderEffDate,String orderEffEndDate);
	public List getSalRevOrdersData(long lOrderId);
	public List getEmpArrearBillMpgs(long locId);
	public List getEmpArrearCmpAmtDtls(long langId,long salRevId,long billSubheadId,String orderEffDate,String orderEffEndDate);
	public List getEmpArrearCmpCount(long postId,long langId,long salRevId,long billSubheadId);
	public List getArrearBillPostMpgData(long salRevId,long billSubheadId);
	public List getArrearBillCmpAmtMpgData(long arrearBillpostId);
	public List getEmpListIndependentOfPaybill(long locId,String billSubHeadId,String orderEffDate,String orderEffEndDate,long postId);
	public MstDcpsBillGroup getHrPBLSubheadMpgByBillSubheadId(long billSubheadId);
	public PaybillHeadMpg checkPaybillHeadMpgData(int month,int year,long phmBillNo,long billTypeId);
	public OrgDdoMst getOrgDDOMstVO(long postId);
	public boolean checkIsPaybillGenerated(long postId,long locId,int month,int year);
	public List getEmpDetailsFromEmpID(long empId,long locId);
}
