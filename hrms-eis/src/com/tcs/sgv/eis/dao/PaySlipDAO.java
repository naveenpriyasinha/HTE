package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;

public interface PaySlipDAO extends GenericDao<HrPayPayslip, Long>{
	public List getAllData();
	public List getAllData(long deptId);
	public List getPayslipData(int month,int year);
	public List getPayslipData(int month,int year,long empId);
	public List getEmpPayslipHistoryData(Date currDate);
	public List getPayslipDataDept(int month,int year,long deptId);
	public List getAllEmpData();
	public List getEmpPayslipData(long empId);
	public List getLedgerFormData(int month,int Year,long locId,String billNo);
	public List getPayCertificate(long month,long Year,long empId);
	public List getPayCertificateLoan(long month,long year,long empId);
	public List getPayslipData(int month,int year,String category,String Grade,String dsgnId,String subHeadId,long langId,String givenBillNo);
	public List getLoanDetailByEmpId(long empId,String strCurrMonthMinDate,String strCurrMonthMaxDate);
	public List getCurrentYearPayslipDataByEmpId(Date currDate,long empId);
	public long getPayBillGrpId(String givenBillNo,long locId,int currMonth,int currYear);
	public List getBillData(long finYrId,long locId); 
	//public List getGrossList(Date currDate);
}
