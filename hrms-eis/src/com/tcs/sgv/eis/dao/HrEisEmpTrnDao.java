package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisBiometricDtl;

public interface HrEisEmpTrnDao  extends GenericDao
{
	List getEditRequestResult(long reqId) ;
	HrEisBiometricDtl getBiomatricDetailsBybioId(long bioId); 
	//HdBiometricsMst getBiomatricDetailsByEmpId(long empId);//Added By Sunil
}



