/**
 * This is an Interface for Data access object for Allowance Type Master.
 * Made By:- Urvin shah.
 * Date:- 14/07/2007.
 */
package com.tcs.sgv.payslip.dao;

import java.util.List;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.payslip.valueobject.HrCommonPayslipArgs;

public interface commonPayslipDAO extends GenericDao
{
	public List getPayslipData(int month,int year,long empId,long deptId,String billNo,String dsgnArray,String classArray);
	public List getClassFromBillNo(int month,int year,String billNo);
	public List getDesignationFromClassId(int month,int year,String classArray,String billNo);
}

