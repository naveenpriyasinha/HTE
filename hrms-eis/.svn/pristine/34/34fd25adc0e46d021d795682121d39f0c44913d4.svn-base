package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
 
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public interface PayBillDAO extends GenericDao<HrPayPaybill, Long>{
 public List getAllData();
 public List getPayslipData(int month,int year);
 public List getOuterSignature(long trnBillNo);
 public List getReportSignature(long trnBillNo);
 public String getBillnoFormTrnBillReg(long trnBillNo,long locationCode);
 
 public List getPaybillListForLeave(long empId,long month,long year,long approveflag);
 public List getAllPaybillDataByLoc(int month, int year, long locId,long billNo,int approveFlag);
 public String getCityName(String cityId);
 public boolean checkPaybill(int month,int year,long billno,long locId,String billTypeLookupId);
 public List getTotalHeadValue(long billNo,int month, int year,String columnName,String condtion);
 public List getPaybillEdpMpgList();
 public List getPaybillGrouopId(long billNo, int month, int year);
 public List getPreviousBillData(long empId, int month, int year,long locId);
 public String getEmployeeNameFromEmployeeId(String employeeid);
 public Long checkPreviousPaybill(int month, int year, long billno, long locId);
 public Integer calculateTotalBill(int month,int year, long billno, long locId);

	public int[] getBillCreationDate(long billno,long locId);
	
//	public String CheckDdoFieldDept(String ddoCode);
    
    public String checkOtherPaybill(int month, int year, long billno, long locId);
    public List getGPFBifurcatedlist(long paybillGroupId, int year, int month);
    public Long checkHeadAccount(String billNo);
    public Long getEmpCount(long billno,long month,long year);//added by Saurabh S
}
