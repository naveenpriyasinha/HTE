package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;

@SuppressWarnings("unchecked")
public class UpdatePaybillDAOImpl extends GenericDaoHibernateImpl<HrPayPaybill, Long>
{
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");

	public UpdatePaybillDAOImpl(Class<HrPayPaybill> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	//Modified By Kishan - Start
	public List getEmpDataByMonthAndYear(long empId, int month, int year)
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT bill,head,billGroup.dcpsDdoBillDescription ");
		strQuery.append("FROM HrPayPaybill bill, PaybillHeadMpg head, MstDcpsBillGroup billGroup,HrEisEmpMst eis  ");
		strQuery.append("where head.month = " + month + " ");
		strQuery.append("and head.year = " + year + " ");
		if((month == 4 || month == 3 ) && year == 2012 )
			strQuery.append("and head.approveFlag = 1 ");
		else
			strQuery.append("and head.approveFlag = 0 ");
		strQuery.append("and head.hrPayPaybill = bill.paybillGrpId ");
		strQuery.append("and bill.hrEisEmpMst.empId = eis.empId ");
		strQuery.append("and head.billTypeId.lookupId = 2500337 ");
		strQuery.append("and billGroup.dcpsDdoBillGroupId = head.billNo.dcpsDdoBillGroupId ");
		strQuery.append("and eis.empId = " + empId + " ");
		Query result = hibSession.createQuery(strQuery.toString());
		return result.list();
	}

	//Modified By Kishan - End

	public List getEdpCodeForEmpForGeneratedPaybill(int month, int year, long empId, long type, long billNo)
	{
		List<RltBillTypeEdp> edpMstList = null;
		Session hibSession = getSession();
		StringBuffer stringBuff = new StringBuffer();
		stringBuff.append("SELECT empCompo.COMPO_ID ");
		stringBuff.append("FROM PAYBILL_HEAD_MPG head, HR_PAY_PAYBILL paybill, HR_EIS_EMP_MST eis, HR_PAY_PAYBILL_EMP_COMP_MPG empCompo ");
		stringBuff.append("where head.BILL_NO = "+billNo+" and head.PAYBILL_MONTH = "+month+" and head.PAYBILL_YEAR = "+year+" ");
		if((month == 4 || month == 3 ) && year == 2012 )
			stringBuff.append("and head.APPROVE_FLAG = 1 ");
		else
			stringBuff.append("and head.APPROVE_FLAG = 0 ");
		
		stringBuff.append("and paybill.PAYBILL_GRP_ID = head.PAYBILL_ID and paybill.EMP_ID = eis.EMP_ID and eis.EMP_ID = "+empId+" ");
		stringBuff.append("and empCompo.HR_PAY_PAYBILL_ID = paybill.ID and empCompo.COMPO_TYPE = "+type+" ");
		Query query = hibSession.createSQLQuery(stringBuff.toString());
		List tempList = query.list();
		String typeId = "";
		if(!tempList.isEmpty())
		{
			typeId = tempList.get(0).toString();
			stringBuff = new StringBuffer();
			stringBuff.append("select rlt from com.tcs.sgv.eis.valueobject.RltBillTypeEdp rlt, HrPayEdpCompoMpg edp ");
			stringBuff.append("where rlt.activateFlag=1 and rlt.subjectId=3 ");
			stringBuff.append("and rlt.typeEdpId = edp.rltBillTypeEdp.typeEdpId ");
			stringBuff.append("and edp.cmnLookupId=" + type + " ");
			stringBuff.append("and edp.typeId in ("+typeId+") ");
			Query query1 = hibSession.createQuery(stringBuff.toString());
			logger.info("Query to Fetch RltBillTypeEdp Object with empId,month,year,type and subjectId------>" + query1);
			edpMstList = query1.list();
		}
		return edpMstList;
	}

	public long getOtherDetailPKFromEmployeeID(long empId)
	{
		long otherId = 0l;
		Object objTotalInvestAmt;
		Session hibSession = getSession();
		String queryStr = "select od.otherId from HrEisOtherDtls od where od.hrEisEmpMst.empId= " + empId;
		Query query = hibSession.createQuery(queryStr);
		objTotalInvestAmt = query.uniqueResult();
		otherId = Long.parseLong(objTotalInvestAmt.toString());
		System.out.println("OtherId--->" + otherId);
		return otherId;

	}

	public Map<String, String> getPaySlipCol()
	{
		Session hibSession = getSession();
		Map<String, String> payslipColMap = new HashMap<String, String>();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT EDP_CODE,EXP_RCP_REC,PAYSLIP_COL FROM HR_PAY_EDP_MST where EXP_RCP_REC in ('EXP','RCP') and PAYSLIP_COL is not null ");
		Query result = hibSession.createSQLQuery(strQuery.toString());
		List ansList = result.list();
		int size = ansList.size();
		Object[] data = null;
		for (int i = 0; i < size; i++)
		{
			data = (Object[]) ansList.get(i);
			payslipColMap.put(data[0].toString(), data[2].toString());
		}
		return payslipColMap;

	}

	public List getPaybillHeadObj(long billNo, int month, int year)
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select head from PaybillHeadMpg head ");
		strQuery.append("where head.month = " + month + " ");
		strQuery.append("and head.year = " + year + " ");
		if((month == 4 || month == 3 ) && year == 2012 )
			strQuery.append("and head.approveFlag = 1 ");
		else
			strQuery.append("and head.approveFlag = 0 ");
		strQuery.append("and head.billNo.dcpsDdoBillGroupId = " + billNo + " ");
		Query result = hibSession.createQuery(strQuery.toString());
		return result.list();
	}

	public int updatePaySlipData(List payslipDataList, long billNo, int month, int year, long empId)
	{
		int size = payslipDataList.size();
		String updatingColVal = "";
		for (int i = 0; i < size; i++)
		{
			Object[] data = (Object[]) payslipDataList.get(i);
			updatingColVal = updatingColVal + data[0].toString() + "=" + new Double(data[1].toString()).longValue() + ",";
		}
		if (!updatingColVal.equals(""))
			updatingColVal = updatingColVal.substring(0, updatingColVal.lastIndexOf(','));

		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("update HR_PAY_PAYSLIP set " + updatingColVal + " ");
		strQuery.append("where PAYSLIP_MONTH = " + month + " ");
		strQuery.append("and PAYSLIP_YEAR = " + year + " ");
		strQuery.append("and BILL_NO = " + billNo + " ");
		strQuery.append("and EMP_ID = "+empId+" ");
		Query result = hibSession.createSQLQuery(strQuery.toString());
		int resultSize = result.executeUpdate();
		return resultSize;
	}
	
	public List getEdpCodeForLoanAdv(long empId, long type)
	{
		List loanList = null;

		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();

		sb.append("from com.tcs.sgv.eis.valueobject.RltBillTypeEdp as rlt  where rlt.activateFlag=1 and rlt.subjectId=3 and rlt.typeEdpId in");

		sb.append(" (select edpMpg1.rltBillTypeEdp.typeEdpId from HrPayEdpCompoMpg edpMpg1 where edpMpg1.cmnLookupId=" + type + " and edpMpg1.typeId in");
		sb.append("(select loanDtls.hrLoanAdvMst.loanAdvId from HrLoanEmpDtls loanDtls where loanDtls.loanActivateFlag=1");
		sb.append(" and loanDtls.hrEisEmpMst.orgEmpMst.empId=" + empId + "))");

		logger.info("Query for fetching loans / advances as per type " + sb.toString());
		Query loanListQuery = hibSession.createQuery(sb.toString());
		loanList = loanListQuery.list();
		return loanList;
	}
	
	public List getApprovedBills(long month, long year, long locId, long slectedAction)
	{
		Session hibSession = getSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT bill.BILL_GROUP_ID,bill.DESCRIPTION ");
		queryString.append("FROM ORG_DDO_MST ddo, PAYBILL_HEAD_MPG head, MST_DCPS_BILL_GROUP bill ");
		queryString.append("where ddo.LOCATION_CODE = "+locId+" ");
		queryString.append("and bill.DDO_CODE = ddo.DDO_CODE ");
		queryString.append("and bill.BILL_GROUP_ID = head.BILL_NO ");
//		queryString.append("and head.APPROVE_FLAG = 1 ");
		if(slectedAction == 1)
		{
			queryString.append("and head.APPROVE_FLAG = 1 ");
		}
		else
		{
			if((month == 4 || month == 3 ) && year == 2012 )
				queryString.append("and head.APPROVE_FLAG = 1 ");
			else
				queryString.append("and head.APPROVE_FLAG = 0 ");
		}
		queryString.append("and head.PAYBILL_MONTH = "+month+" ");
		queryString.append("and head.PAYBILL_YEAR = "+year+" ");
		queryString.append("order by bill.BILL_GROUP_ID ");
		Query resultQuery = hibSession.createSQLQuery(queryString.toString());
		return resultQuery.list();
	}
	
	public List getEmployeeList(long month, long year, long billNo, long locId)
	{
		Session hibSession = getSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT paybill.EMP_ID , emp.EMP_FNAME, emp.EMP_MNAME, emp.EMP_LNAME ");
		queryString.append("FROM PAYBILL_HEAD_MPG head, HR_PAY_PAYBILL paybill, HR_EIS_EMP_MST eis, ORG_EMP_MST emp ");
		queryString.append("where head.BILL_NO = "+billNo+" ");
		queryString.append("and head.PAYBILL_MONTH = "+month+" ");
		queryString.append("and head.PAYBILL_YEAR = "+year+" ");
//		queryString.append("and head.APPROVE_FLAG = 1 ");
		if((month == 4 || month == 3 ) && year == 2012 )
			queryString.append("and head.APPROVE_FLAG = 1 ");
		else
			queryString.append("and head.APPROVE_FLAG = 0 ");
		
		queryString.append("and head.PAYBILL_ID = paybill.PAYBILL_GRP_ID ");
		queryString.append("and paybill.EMP_ID is not null ");
		queryString.append("and eis.EMP_ID = paybill.EMP_ID ");
		queryString.append("and emp.EMP_ID = eis.EMP_MPG_ID ");
		Query resultQuery = hibSession.createSQLQuery(queryString.toString());
		return resultQuery.list();
		
	}
	
	public List getBasicDetail(long empId)
	{
		Session hibSession = getSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT gpf.PF_SERIES,gpf.GPF_ACC_NO FROM HR_PAY_GPF_DETAILS gpf, ORG_EMP_MST emp, HR_EIS_EMP_MST eis ");
		queryString.append("where gpf.USER_ID = emp.USER_ID ");
		queryString.append("and eis.EMP_ID = "+empId+" ");
		queryString.append("and emp.EMP_ID = eis.EMP_MPG_ID ");
		Query resultQuery = hibSession.createSQLQuery(queryString.toString());
		return resultQuery.list();
	}
	
	public List getVoucherEntry(long month,long year,long billNo)
	{
		Session hibSession = getSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT VOUCHER_NO, VOUCHER_DATE ");
		queryString.append("FROM PAYBILL_HEAD_MPG where BILL_NO = "+billNo+" and PAYBILL_MONTH = "+month+" and PAYBILL_YEAR = "+year+" ");
		queryString.append("and  APPROVE_FLAG = 1 ");
		Query resultQuery = hibSession.createSQLQuery(queryString.toString());
		return resultQuery.list();
	}
	
	public int updateVoucherDate(int month, int year,long billNo,long voucherNo,Date voucherDate)
	{
		Session hibSession = getSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("update PAYBILL_HEAD_MPG set VOUCHER_NO = :voucherNo,VOUCHER_DATE = :voucherDate ");
		queryString.append("where BILL_NO = "+billNo+" and PAYBILL_MONTH = "+month+" and APPROVE_FLAG = 1 and PAYBILL_YEAR = "+year+" ");
		Query resultQuery = hibSession.createSQLQuery(queryString.toString());
		resultQuery.setLong("voucherNo", voucherNo);
		resultQuery.setDate("voucherDate", voucherDate);
		int count = resultQuery.executeUpdate();
		return count;
	}

}
