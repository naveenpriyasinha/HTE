package com.tcs.sgv.onlinebillprep.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.onlinebillprep.valueobject.TrnOnlinebillEmp;

public interface TrnOnlinebillEmpDAO extends GenericDao<TrnOnlinebillEmp, Long>
{
	public List getEmpDtlsByBillNo(String[] lStrBillNo);
}
