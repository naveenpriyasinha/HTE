package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.posting.valueobject.HrEisPostingOrderDtl;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisBiometricDtl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public interface EmpPersonalInfoDao  extends GenericDao
{
	List getAllEmpKnownLanguage(long userId);
	List getEmpIdData(long EmpId,CmnLanguageMst cmnLanguageMst);
	List getOrgEmpMstVOList(long userId);
	List getEmpContactData(long empId);
	HrEisBiometricDtl getBiomatricDetails(long bioId);
	OrgEmpMst getOrgEmpMstVO(long empid);
	HrEisEmpMst getHrEisEmpMstDtls(long empid);
	HrEisPostingOrderDtl getPostingOrderDtls(long userId ,long ReasonId);//Change For Inserting Employment Status In Hr_Eis_Emp_Mst
	//HdBiometricsMst getBiomatricDetailsByEmpId(long empId);//By sunil
}



