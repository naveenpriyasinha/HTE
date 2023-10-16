package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;
import com.tcs.sgv.eis.valueobject.HrPayPayslipHst;

@SuppressWarnings( { "unchecked", "deprecation" })
public class PaySlipDAOImpl extends GenericDaoHibernateImpl<HrPayPayslip, Long> implements PaySlipDAO
{

	//Added By Paurav for defining a logger globally
	Log logger = LogFactory.getLog(getClass());

	//Ended By Paurav

	public PaySlipDAOImpl(Class<HrPayPayslip> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getAllData()
	{
		List payBillList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayPayslip";
		Query query = hibSession.createQuery(strQuery);
		payBillList = query.list();

		return payBillList;
	}

	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	public final long paybillTypeId = Integer.parseInt(resourceBundle.getString("paybillTypeId"));

	public List getAllData(long deptId)
	{
		List payBillList = null;
		Session hibSession = getSession();
		// String strQuery = "from HrPayPayslip";
		/* String strQuery = " from HrPayPayslip as paySlip where paySlip.hrEisEmpMst.orgEmpMst.orgUserMst.userId in "+ 
		    "  ( select distinct(up.orgUserMst.userId) from OrgUserpostRlt up where up.orgPostMstByPostId.postId in "+
		    "  ( select pd.orgPostMst.postId from OrgPostDetailsRlt pd where pd.cmnLocationMst.locId = '"+deptId+"' ))";
		*/
		StringBuilder strQuery = new StringBuilder();

		strQuery.append("select max(payslip.paySlipId), max(payslip.hrPayPaybill),max(payslip.hrEisEmpMst),");
		strQuery.append("max(payslip.month),max(payslip.year),max(payslip.billNo),max(payslip.hrQuaterTypeMst),");
		strQuery.append(" max(payslip.incrementAmt),max(payslip.itAccNo),max(payslip.gpfAccNo),max(payslip.tokenNo),");
		strQuery.append(" max(payslip.billAmt),max(payslip.budgetHead),max(payslip.paySlipDate),sum(payslip.basicPay), ");
		strQuery.append(" sum(payslip.leavePay),sum(payslip.splPay),sum(payslip.perPay),sum(payslip.da), ");
		strQuery.append(" sum(payslip.cla),sum(payslip.hra),sum(payslip.wa),sum(payslip.ma),sum(payslip.ir), ");
		strQuery.append(" sum(payslip.trAllow),sum(payslip.ITax),sum(payslip.hrr),sum(payslip.PTax),sum(payslip.gis1979), ");
		strQuery.append(" sum(payslip.gpf),sum(payslip.gpfAdv),sum(payslip.fesAdv),sum(payslip.foodAdv),sum(payslip.fanAdv), ");
		strQuery.append(" sum(payslip.hba),sum(payslip.vehAdv),sum(payslip.miscRec),sum(payslip.dp),sum(payslip.cpf), ");
		strQuery.append(" sum(payslip.jeepRent),sum(payslip.netTotal) ");
		strQuery.append(" from HrPayPayslip as payslip group by payslip.hrEisEmpMst.empId");

		Query query = hibSession.createQuery(strQuery.toString());
		payBillList = query.list();

		return payBillList;
	}

	public List getPayslipData(int month, int year)
	{
		List paySlipList = null;
		Session hibSession = getSession();

		StringBuilder strQuery = new StringBuilder();

		strQuery.append("select max(payslip.paySlipId), max(payslip.hrPayPaybill),max(payslip.hrEisEmpMst),");
		strQuery.append("max(payslip.month),max(payslip.year),max(payslip.billNo),max(payslip.hrQuaterTypeMst),");
		strQuery.append(" max(payslip.incrementAmt),max(payslip.itAccNo),max(payslip.gpfAccNo),max(payslip.tokenNo),");
		strQuery.append(" max(payslip.billAmt),max(payslip.budgetHead),max(payslip.paySlipDate),sum(payslip.basicPay), ");
		strQuery.append(" sum(payslip.leavePay),sum(payslip.splPay),sum(payslip.perPay),sum(payslip.da), ");
		strQuery.append(" sum(payslip.cla),sum(payslip.hra),sum(payslip.wa),sum(payslip.ma),sum(payslip.ir), ");
		strQuery.append(" sum(payslip.trAllow),sum(payslip.ITax),sum(payslip.hrr),sum(payslip.PTax),sum(payslip.gis1979), ");
		strQuery.append(" sum(payslip.gpf),sum(payslip.gpfAdv),sum(payslip.fesAdv),sum(payslip.foodAdv),sum(payslip.fanAdv), ");
		strQuery.append(" sum(payslip.hba),sum(payslip.vehAdv),sum(payslip.miscRec),sum(payslip.dp),sum(payslip.gpf), ");
		strQuery.append(" sum(payslip.jeepRent),sum(payslip.netTotal) ");
		strQuery.append(" from HrPayPayslip as payslip ");
		strQuery.append(" where payslip.month=" + month + " and payslip.year=" + year);
		strQuery.append(" group by payslip.hrEisEmpMst.empId");

		Query query = hibSession.createQuery(strQuery.toString());
		paySlipList = query.list();

		return paySlipList;
	}

	public List getPayslipData(int month, int year, long empId)
	{
		List paySlipList = null;
		Session hibSession = getSession();
		StringBuilder strQuery = new StringBuilder();
		strQuery.append("select max(payslip.paySlipId), max(payslip.hrPayPaybill),max(payslip.hrEisEmpMst),");
		strQuery.append("max(payslip.month),max(payslip.year),max(payslip.billNo),max(payslip.hrQuaterTypeMst),");
		strQuery.append(" max(payslip.incrementAmt),max(payslip.itAccNo),max(payslip.gpfAccNo),max(payslip.tokenNo),");
		strQuery.append(" max(payslip.billAmt),max(payslip.budgetHead),max(payslip.paySlipDate),sum(payslip.basicPay), ");
		strQuery.append(" sum(payslip.leavePay),sum(payslip.splPay),sum(payslip.perPay),sum(payslip.da), ");
		strQuery.append(" sum(payslip.cla),sum(payslip.hra),sum(payslip.wa),sum(payslip.ma),sum(payslip.ir), ");
		strQuery.append(" sum(payslip.trAllow),sum(payslip.ITax),sum(payslip.hrr),sum(payslip.PTax),sum(payslip.gis1979), ");
		strQuery.append(" sum(payslip.gpf),sum(payslip.gpfAdv),sum(payslip.fesAdv),sum(payslip.foodAdv),sum(payslip.fanAdv), ");
		strQuery.append(" sum(payslip.hba),sum(payslip.vehAdv),sum(payslip.miscRec),sum(payslip.dp),sum(payslip.gpf), ");
		strQuery.append(" sum(payslip.jeepRent),sum(payslip.netTotal) ");
		strQuery.append(" from HrPayPayslip as payslip ");
		strQuery.append(" where payslip.month=" + month + " and payslip.year=" + year + " and payslip.hrEisEmpMst.empId=" + empId);
		strQuery.append(" group by payslip.hrEisEmpMst.empId");

		Query query = hibSession.createQuery(strQuery.toString());
		/*String strQuery = "from HrPayPayslip as paySlip where paySlip.hrEisEmpMst.empId=" + empId + 
		" and paySlip.month=" + month + " and paySlip.year=" + year; 
		Query query = hibSession.createQuery(strQuery);*/
		paySlipList = query.list();

		return paySlipList;
	}

	public List getPayslipDataDept(int month, int year, long deptId)
	{
		List paySlipList = null;
		Session hibSession = getSession();
		StringBuilder strQuery = new StringBuilder();

		strQuery.append("select max(payslip.paySlipId), max(payslip.hrPayPaybill),MAX(payslip.hrEisEmpMst),");
		strQuery.append("max(payslip.month),max(payslip.year),max(payslip.billNo),max(payslip.hrQuaterTypeMst),");
		strQuery.append(" max(payslip.incrementAmt),max(payslip.itAccNo),max(payslip.gpfAccNo),max(payslip.tokenNo),");
		strQuery.append(" max(payslip.billAmt),max(payslip.budgetHead),max(payslip.paySlipDate),sum(payslip.basicPay), ");
		strQuery.append(" sum(payslip.leavePay),sum(payslip.splPay),sum(payslip.perPay),sum(payslip.da), ");
		strQuery.append(" sum(payslip.cla),sum(payslip.hra),sum(payslip.wa),sum(payslip.ma),sum(payslip.ir), ");
		strQuery.append(" sum(payslip.trAllow),sum(payslip.ITax),sum(payslip.hrr),sum(payslip.PTax),sum(payslip.gis1979), ");
		strQuery.append(" sum(payslip.gpf),sum(payslip.gpfAdv),sum(payslip.fesAdv),sum(payslip.foodAdv),sum(payslip.fanAdv), ");
		strQuery.append(" sum(payslip.hba),sum(payslip.vehAdv),sum(payslip.miscRec),sum(payslip.dp),sum(payslip.gpf), ");
		strQuery.append(" sum(payslip.jeepRent),sum(payslip.netTotal) ");

		strQuery.append(" from HrPayPayslip as payslip where payslip.hrEisEmpMst.orgEmpMst.orgUserMst.userId in " + "  ( select distinct(up.orgUserMst.userId) from OrgUserpostRlt up where up.orgPostMstByPostId.postId in " + "  ( select pd.orgPostMst.postId from OrgPostDetailsRlt pd where pd.cmnLocationMst.locId = '" + deptId + "' ))" + " and payslip.month=" + month + " and payslip.year=" + year + " group by payslip.hrEisEmpMst.empId ");

		Query query = hibSession.createQuery(strQuery.toString());
		paySlipList = query.list();

		return paySlipList;
	}

	public List getAllEmpData() //function added by Ankit Bhatt, used in GeneratePayslipService(getPayslipPara)
	{
		List payBillEmpList = null;
		Session hibSession = getSession();
		String strQuery = "from HrEisEmpMst as emp where emp.empId in " + "(select distinct(paySlip.hrEisEmpMst.empId) from HrPayPayslip as paySlip)" + " order by emp.orgEmpMst.empFname";
		;
		Query query = hibSession.createQuery(strQuery);
		payBillEmpList = query.list();

		return payBillEmpList;
	}

	public List getEmpPayslipData(long empId)
	{
		List payBillList = null;
		Session hibSession = getSession();

		StringBuilder strQuery = new StringBuilder();

		strQuery.append("select max(payslip.paySlipId), max(payslip.hrPayPaybill),max(payslip.hrEisEmpMst),");
		strQuery.append("max(payslip.month),max(payslip.year),max(payslip.billNo),max(payslip.hrQuaterTypeMst),");
		strQuery.append(" max(payslip.incrementAmt),max(payslip.itAccNo),max(payslip.gpfAccNo),max(payslip.tokenNo),");
		strQuery.append(" max(payslip.billAmt),max(payslip.budgetHead),max(payslip.paySlipDate),sum(payslip.basicPay), ");
		strQuery.append(" sum(payslip.leavePay),sum(payslip.splPay),sum(payslip.perPay),sum(payslip.da), ");
		strQuery.append(" sum(payslip.cla),sum(payslip.hra),sum(payslip.wa),sum(payslip.ma),sum(payslip.ir), ");
		strQuery.append(" sum(payslip.trAllow),sum(payslip.ITax),sum(payslip.hrr),sum(payslip.PTax),sum(payslip.gis1979), ");
		strQuery.append(" sum(payslip.gpf),sum(payslip.gpfAdv),sum(payslip.fesAdv),sum(payslip.foodAdv),sum(payslip.fanAdv), ");
		strQuery.append(" sum(payslip.hba),sum(payslip.vehAdv),sum(payslip.miscRec),sum(payslip.dp),sum(payslip.gpf), ");
		strQuery.append(" sum(payslip.jeepRent),sum(payslip.netTotal) ");
		strQuery.append(" from HrPayPayslip as payslip where payslip.hrEisEmpMst.empId=" + empId);
		strQuery.append(" group by payslip.hrEisEmpMst.empId,payslip.month");

		Query query = hibSession.createQuery(strQuery.toString());
		payBillList = query.list();

		return payBillList;
	}

	public List getEmpPayslipHistoryData(Date currDate)
	{
		List<HrPayPayslipHst> payBillList = null;
		Session hibSession = getSession();

		//		String currDateMonYear = (currDate.getMonth() + 1) + "/" + (currDate.getYear() + 1900);
		String compareFromDate = "";
		String compareToDate = "";
		compareToDate = (currDate.getMonth() + 1) + "/" + (currDate.getYear() + 1900);
		if ((currDate.getMonth() + 1) <= 3)
		{
			compareFromDate = "4/" + ((currDate.getYear() + 1900) - 1);
			//compareToDate = (currDate.getMonth()+1) + "/" + (currDate.getYear() +1900);
		}
		else
		{
			compareFromDate = "4/" + (currDate.getYear() + 1900);
			//compareToDate = "2/" + ((currDate.getYear() +1900)+1);
		}

		/*String strQuery = " from HrPayPayslipHst as paySlipHst where paySlipHst.hrEisEmpMst.empId=" + empId +
				" and to_date('" + currDateMonYear + "','MM/YYYY') between  " +
				"to_date('" + compareFromDate + "','MM/YYYY') and to_date('" + compareToDate + "','MM/YYYY')";*/

		//employee specific query
		/*String strQuery = "select sum(paySlipHst.trAllow) as TA, sum(paySlipHst.PTax) as PT,sum(paySlipHst.gpf) as GPF," +
				"sum(paySlipHst.hba) as HBA,sum(paySlipHst.ITax) as IT " +
				" from HrPayPayslipHst as paySlipHst where paySlipHst.hrEisEmpMst.empId=" + empId +
		" and to_date('" + currDateMonYear + "','MM/YYYY') >=  " +
		"to_date('" + compareFromDate + "','MM/YYYY')";*/
		//
		//query for fetching all records.
		/*String strQuery = " from HrPayPayslipHst as paySlipHst where " +
		    " to_date('" + currDateMonYear + "','MM/YYYY') >=  " +
		    "to_date('" + compareFromDate + "','MM/YYYY')";*/
		//
		String strQuery = " from HrPayPayslipHst as paySlipHst where " + " to_date(paySlipHst.month || '/' || paySlipHst.year,'MM/YYYY') between " + "to_date('" + compareFromDate + "','MM/YYYY') and " + "to_date('" + compareToDate + "','MM/YYYY')";
		logger.info("History query for payslip History table " + strQuery);
		Query query = hibSession.createQuery(strQuery);
		payBillList = query.list();

		return payBillList;
	}

	public List getLedgerFormData(int month, int Year, long locId, String billNo)
	{
		Log logger = LogFactory.getLog(getClass());
		//		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		//		long HBA_loan = Long.parseLong(constantsBundle.getString("HBA_loan"));//37
		//		long Moped_loan = Long.parseLong(constantsBundle.getString("Moped_loan"));//27
		//		long Scooter_Loan = Long.parseLong(constantsBundle.getString("Scooter_Loan"));//31
		//		long Car_Loan = Long.parseLong(constantsBundle.getString("Car_Loan"));//23
		ArrayList DataList = new ArrayList();
		//		List payBillList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();

		Calendar cal = Calendar.getInstance();
		DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
		SimpleDateFormat sdfObj = sbConf.GetDateFormat();

		cal.set(Calendar.YEAR, Year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		java.util.Date startMonthDate = cal.getTime();
		String startDate = sdfObj.format(startMonthDate);

		int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		cal.set(Calendar.DAY_OF_MONTH, totalDays);

		java.util.Date endMonthDate = cal.getTime();

		String endDate = sdfObj.format(endMonthDate);

		query.append(" select sum(pay.basic0101+pay.basic0102) , ");//pay
		query.append("  sum(pay.allow0101) , ");//
		query.append("  sum(pay.allow0102) , ");//SPPAY
		query.append("  sum(pay.deduc9670+pay.deduc9531) , ");//GPF
		query.append("  sum(pay.allow0101) , ");//PERPAY
		query.append("  sum(pay.adv9670+pay.advIV9670) , ");//PF_ADV
		query.append("  sum(pay.ls) , ");//LS
		query.append("  sum(pay.deduc9581+pay.deduc9582) , ");//SIS
		query.append("  sum(pay.allow0103) , ");//DA
		query.append("  sum(pay.loan9720) , ");//FAN
		query.append("  sum(pay.allow0119+pay.allow0120) , ");//DPAy
		query.append("  sum(pay.it) , ");//IT
		query.append("  sum(pay.allow0111) , ");//CLA
		query.append("  sum(pay.deduc9550) , ");//HRR
		query.append("  sum(pay.allow0110) , ");//HRA
		query.append("  sum(pay.deduc9570) , ");//PTAX
		query.append("  sum(pay.allow1301) , ");//WA
		query.append("  sum(pay.loan9591) , ");//HBA
		query.append("  sum(pay.allow0104) , ");//oth_A
		query.append("  sum(pay.loanInt9591) , ");//HBA_IN
		query.append("  sum(pay.allow0113) , ");//TA
		query.append("  sum(pay.loan9592) , ");//MCA
		query.append("  sum(pay.allow0107) , ");//MA
		query.append("  sum(pay.loanInt9592) , ");//MCA_IN
		query.append("  sum(pay.allow0101) , ");//IR
		query.append("  sum(pay.deduc9780) , ");//jeeP_R
		/*        query.append("  sum(pay.adv5701) , ");//FA
		        query.append("  sum(pay.adv5801) , ");////FGA
		*/

		query.append("  sum(pay.adv7057) , ");//FA
		query.append("  sum(pay.adv7058) , ");////FGA

		query.append("  sum(pay.deduc9910),  ");//MISC
		query.append("  sum(pay.grossAmt+pay.adv7057+pay.adv7058),  ");//Gross
		query.append("  sum(pay.totalDed),  ");//Tded
		query.append("  sum(pay.netTotal),  ");//net
		query.append("  sum(pay.deduc9534)  ");//CPS

		query.append(" from HrPayPaybill pay,  ");
		query.append("  OrgUserpostRlt           USRPST, ");
		query.append("  HrPayOrderHeadMpg ORDHD, ");
		query.append("  HrPayOrderHeadPostMpg ORDPST, ");
		query.append("  OrgPostDetailsRlt pst ,HrPayOrderSubHeadMpg hposm");

		query.append("  where  ");

		query.append("   ORDHD.subheadId in (select distinct hposm.element_code from HrPayOrderSubHeadMpg hposm,PaybillHeadMpg bhm ,HrPayPaybill pay where bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId and pay.paybillGrpId = bhm.hrPayPaybill )");
		query.append("  and ORDPST.orderHeadId = ORDHD.orderHeadId ");
		query.append("  and USRPST.orgPostMstByPostId.postId = ORDPST.postId ");
		query.append("  and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
		query.append("  and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ");

		query.append("  and ( USRPST.endDate is null or ");
		query.append("   (USRPST.endDate>='" + startDate + "' and USRPST.startDate<='" + endDate + "'    )) ");
		query.append("   and pay.approveFlag in (0,1) ");

		// to avoid duplicay of records using post id col of paybill
		query.append("   and pay.orgPostMst.postId= ORDPST.postId ");

		if (billNo != null && !billNo.equals("") && !billNo.equals("-1"))
		{

			StringTokenizer st1 = new StringTokenizer(billNo, "~");
			String subheadCode = "";
			String classIds = "";
			String desgnIds = "";
			int l = 0;
			while (st1.hasMoreTokens())
			{
				if (l == 0)
					subheadCode = st1.nextToken();
				else if (l == 1)
					classIds = st1.nextToken();
				else if (l == 2)
					desgnIds = st1.nextToken();
				l++;
			}

			query.append(" and ORDHD.subheadId = " + subheadCode + " ");
			query.append(" and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId in (" + classIds + ") ");
			query.append("  and pst.orgDesignationMst.dsgnId  in  (" + desgnIds + ") ");

		}
		query.append("  and   ");
		query.append(" pst .cmnLocationMst.locId=" + locId + " and ");
		query.append("   pay.month='" + month + "' ");
		query.append("  and pay.year= '" + Year + "' and pay.approveFlag in (0,1)  order by pay.createdDate desc ");

		logger.info("***Query for getLedgerFormData details**" + query.toString());

		Query sqlQuery = hibSession.createQuery(query.toString());
		//		ArrayList dataList = new ArrayList();
		List RowList = sqlQuery.list();
		logger.info("*************************" + RowList.size());
		int cnt = 1;
		//		long empId = 0;

		double x = 0d;
		Iterator itr = RowList.iterator();
		while (itr.hasNext())
		{
			Object[] rowList = (Object[]) itr.next();
			double pay = Double.parseDouble((rowList[0] != null ? rowList[0] : x).toString());
			double CPF = Double.parseDouble((rowList[32] != null ? rowList[32] : x).toString());
			double SPPAY = Double.parseDouble((rowList[2] != null ? rowList[2] : x).toString());
			double GPF = Double.parseDouble((rowList[3] != null ? rowList[3] : x).toString());
			double PERPAY = Double.parseDouble((rowList[4] != null ? rowList[4] : x).toString());
			double PF_ADV = Double.parseDouble((rowList[5] != null ? rowList[5] : x).toString());
			double LS = Double.parseDouble((rowList[6] != null ? rowList[6] : x).toString());
			double SIS = Double.parseDouble((rowList[7] != null ? rowList[7] : x).toString());
			double DA = Double.parseDouble((rowList[8] != null ? rowList[8] : x).toString());
			double FAN = Double.parseDouble((rowList[9] != null ? rowList[9] : x).toString());
			double DPAy = Double.parseDouble((rowList[10] != null ? rowList[10] : x).toString());
			double IT = Double.parseDouble((rowList[11] != null ? rowList[11] : x).toString());
			double CLA = Double.parseDouble((rowList[12] != null ? rowList[12] : x).toString());
			double HRR = Double.parseDouble((rowList[13] != null ? rowList[13] : x).toString());
			double HRA = Double.parseDouble((rowList[14] != null ? rowList[14] : x).toString());
			double PTAX = Double.parseDouble((rowList[15] != null ? rowList[15] : x).toString());
			double WA = Double.parseDouble((rowList[16] != null ? rowList[16] : x).toString());
			double HBA = Double.parseDouble((rowList[17] != null ? rowList[17] : x).toString());
			double oth_A = Double.parseDouble((rowList[18] != null ? rowList[18] : x).toString());
			double HBA_IN = Double.parseDouble((rowList[19] != null ? rowList[19] : x).toString());
			double TA = Double.parseDouble((rowList[20] != null ? rowList[20] : x).toString());
			double MCA = Double.parseDouble((rowList[21] != null ? rowList[21] : x).toString());
			double MA = Double.parseDouble((rowList[22] != null ? rowList[22] : x).toString());
			double MCA_IN = Double.parseDouble((rowList[23] != null ? rowList[23] : x).toString());
			double IR = Double.parseDouble((rowList[24] != null ? rowList[24] : x).toString());
			double jeeP_R = Double.parseDouble((rowList[25] != null ? rowList[25] : x).toString());
			double FA = Double.parseDouble((rowList[26] != null ? rowList[26] : x).toString());
			double FGA = Double.parseDouble((rowList[27] != null ? rowList[27] : x).toString());
			double MISC = Double.parseDouble((rowList[28] != null ? rowList[28] : x).toString());
			double Gross = Double.parseDouble((rowList[29] != null ? rowList[29] : x).toString());
			double Tded = Double.parseDouble((rowList[30] != null ? rowList[30] : x).toString());
			double net = Double.parseDouble((rowList[31] != null ? rowList[31] : x).toString());

			ArrayList row = new ArrayList();
			row.add(pay);
			row.add(CPF);
			row.add(SPPAY);
			row.add(GPF);
			row.add(PERPAY);
			row.add(PF_ADV);
			row.add(LS);
			row.add(SIS);
			row.add(DA);
			row.add(FAN);
			row.add(DPAy);
			row.add(IT);
			row.add(CLA);
			row.add(HRR);
			row.add(HRA);
			row.add(PTAX);
			row.add(WA);
			row.add(HBA);
			row.add(oth_A);
			row.add(HBA_IN);
			row.add(TA);
			row.add(MCA);
			row.add(MA);
			row.add(MCA_IN);
			row.add(IR);
			row.add(jeeP_R);
			row.add(FA);
			row.add(FGA);
			row.add(MISC);
			row.add(Gross);
			row.add(Tded);
			row.add(net);

			DataList.add(row);
			cnt++;
		}

		logger.info("**********************" + DataList.size());
		return DataList;
	}

	public List getPayCertificate(long month, long Year, long empId)
	{
		Log logger = LogFactory.getLog(getClass());

		/*if((month-1)==0)
		{
			month=12;
			Year--;
		}
		else
		{
			month--;
		}
		*/
		Calendar cal = Calendar.getInstance();

		DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
		SimpleDateFormat sdfObj = sbConf.GetDateFormat();

		cal.set(Calendar.YEAR, (int) Year);
		cal.set(Calendar.MONTH, (int) month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		java.util.Date startMonthDate = cal.getTime();
		String startDate = sdfObj.format(startMonthDate);

		ArrayList DataList = new ArrayList();
		Session hibSession = getSession();
		String query = " select ";
		query += " slip.hrEisEmpMst.orgEmpMst.empPrefix||' '||slip.hrEisEmpMst.orgEmpMst.empFname||' '||slip.hrEisEmpMst.orgEmpMst.empMname||' '||slip.hrEisEmpMst.orgEmpMst.empLname,";// emp name
		query += " sum(slip.basicPay) ,";//pay 1
		query += "  sum(slip.leavePay),";//LS 2
		query += "  sum(slip.dp),";//DPay 3
		query += "  sum(slip.da),";//DA 4
		query += "  sum(slip.splPay),";//SPPAY 5
		query += "  sum(slip.perPay),";//PERPAY 6
		query += "  sum(slip.cla),";//CLA 7 
		query += "  sum(slip.hra),";//HRA 8
		query += "  sum(slip.ma),";//MA 9
		query += "  sum(slip.wa),";//WA 10
		query += "  sum(slip.trAllow), ";//TA 11 
		query += "  sum(slip.ITax),";//IT 12
		query += "  sum(slip.PTax),";//PTAX 13 
		//query += "  sum(slip.sis),";//sis 14
		query += "  sum(slip.gis1979),";//GIS/AIS gis1979 14        
		query += "  sum(slip.gpf),";//GPF 15
		query += "  sum(slip.vehAdv),";//MCA 16
		query += "  sum(slip.hba),";//HBA 17

		query += "  scale.scaleStartAmt,  ";//18
		query += "  scale.scaleEndAmt, ";//19

		//query += "  odtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt,";//18
		//query += "  odtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt,";//19
		query += "  bdtl.bankAcctNo,";//20
		query += "  bdtl.hrEisBankMst.bankName,";//21
		query += "  bdtl.hrEisBranchMst.branchName,";//22
		query += "  bdtl.hrEisBranchMst.branchAddr,";//23
		query += "  odtl.incrementDate,";//24
		query += "  slip.gpfAccNo,";//25
		//query += "  slip.itAccNo, ";//26  This account is null in payslip table so taken from proof dtls
		query += "  (select max(proofNum) FROm HrEisProofDtl panDtls where panDtls.orgPostMstByUserId.userId = slip.hrEisEmpMst.orgEmpMst.orgUserMst.userId), ";//26
		query += "  pst.orgDesignationMst.dsgnName, ";//27 
		query += "  scale.scaleHigherEndAmt, ";//28
		query += "  sum(slip.gisIf),";//Insuracne Fund 29
		query += "  sum(slip.gisSf),";//Saving Fund 30
		query += "  sum(slip.payRecovery),";//Pay recovery 31
		query += "  sum(slip.miscRec),";//Misc Recovery 32
		query += "  sum(slip.jeepRent),";//Jeep Rent 33
		query += "  sum(slip.hrr),";//House Rent 34
		query += "  sum(slip.DAGPF),";//DA GPF 35        
		query += "  sum(scale.scaleGradePay)";//grade pay 36

		//query += "	odtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnName ";//27

		query += " from HrPayPayslip slip,";
		//query+=" HrEisOtherDtls odtl,";
		query += " HrEisBankDtls bdtl, ";

		query += " HrEisOtherDtls as odtl ";

		query += " left outer join odtl.hrEisSgdMpg as sgd ";
		query += "left outer join sgd.hrEisScaleMst scale, ";

		query += " OrgUserpostRlt           USRPST, ";
		query += " HrPayOrderHeadMpg ORDHD, ";
		query += " HrPayOrderHeadPostMpg ORDPST, ";
		query += " OrgPostDetailsRlt pst ";//,HrPayOrderSubHeadMpg hposm ";

		//query+=" HrEisOtherDtls dtl,HrEisBankDtls b";
		query += " where  ";

		query += " slip.hrEisEmpMst.empId=odtl.hrEisEmpMst.empId and ";
		query += " bdtl.hrEisEmpMst.empId = slip.hrEisEmpMst.empId and ";

		//query += " and pbhd.sgvaBudsubhdMst.budsubhdId = ORDHD.subheadId ";
		//query += " ORDHD.subheadId in (select distinct hposm.element_code from HrPayOrderSubHeadMpg hposm,PaybillHeadMpg bhm ,HrPayPaybill pay where bhm.sgvaBudsubhdMst.budsubhdId = hposm.sgvaBudsubhdMst.budsubhdId and pay.paybillGrpId = bhm.hrPayPaybill )";      
		query += " ORDPST.orderHeadId = ORDHD.orderHeadId ";
		query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
		query += " and USRPST.orgUserMst.userId = slip.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
		query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId   and  ";
		query += " (USRPST.endDate is null or ";
		query += "  (USRPST.endDate>='" + startDate + "'  )) and ";
		if (empId != -1)
			query += " slip.hrEisEmpMst.empId = " + empId + " and ";

		query += "  slip.month='" + (month) + "' and";
		query += "  slip.year= '" + Year + "' ";

		query += "  group by slip.hrEisEmpMst.orgEmpMst.empPrefix,";
		query += "  slip.hrEisEmpMst.orgEmpMst.empFname,";
		query += "  slip.hrEisEmpMst.orgEmpMst.empMname,";
		query += "  slip.hrEisEmpMst.orgEmpMst.empLname,";
		query += "  scale.scaleStartAmt,scale.scaleHigherEndAmt,";
		query += "  scale.scaleEndAmt,";
		query += "  bdtl.bankAcctNo,";
		query += "  bdtl.hrEisBankMst.bankName,";
		query += "  bdtl.hrEisBranchMst.branchName,";
		query += "  bdtl.hrEisBranchMst.branchAddr,";
		query += "  odtl.incrementDate,";
		query += "  slip.gpfAccNo,";
		query += "  slip.itAccNo, ";
		query += "  pst.orgDesignationMst.dsgnName,slip.createdDate,slip.hrEisEmpMst.orgEmpMst.orgUserMst.userId  ";

		query += "  order by slip.createdDate desc";

		logger.info("***Query for getPayCertificate details**" + query);

		Query sqlQuery = hibSession.createQuery(query);

		//		ArrayList dataList = new ArrayList();
		List RowList = sqlQuery.list();
		logger.info("*************************" + RowList.size());
		int cnt = 1;

		Iterator itr = RowList.iterator();
		while (itr.hasNext())
		{
			Object[] rowList = (Object[]) itr.next();
			String empName = (rowList[0] != null ? rowList[0] : "").toString();
			Long basicPay = Long.parseLong((rowList[1] != null ? rowList[1] : "").toString());
			Long GradePay = Long.parseLong((rowList[36] != null ? rowList[36] : "").toString());
			basicPay -= GradePay;
			Long LS = Long.parseLong((rowList[2] != null ? rowList[2] : "").toString());
			Long DPAy = Long.parseLong((rowList[3] != null ? rowList[3] : "").toString());
			Long DA = Long.parseLong((rowList[4] != null ? rowList[4] : "").toString());
			Long SPPAY = Long.parseLong((rowList[5] != null ? rowList[5] : "").toString());
			Long PERPAY = Long.parseLong((rowList[6] != null ? rowList[6] : "").toString());
			Long CLA = Long.parseLong((rowList[7] != null ? rowList[7] : "").toString());
			Long HRA = Long.parseLong((rowList[8] != null ? rowList[8] : "").toString());
			Long MA = Long.parseLong((rowList[9] != null ? rowList[9] : "").toString());
			Long WA = Long.parseLong((rowList[10] != null ? rowList[10] : "").toString());
			Long TA = Long.parseLong((rowList[11] != null ? rowList[11] : "").toString());
			Long IT = Long.parseLong((rowList[12] != null ? rowList[12] : "").toString());
			Long PTAX = Long.parseLong((rowList[13] != null ? rowList[13] : "").toString());
			Long SIS = Long.parseLong((rowList[14] != null ? rowList[14] : "").toString()) + Long.parseLong((rowList[29] != null ? rowList[29] : "").toString()) + Long.parseLong((rowList[30] != null ? rowList[30] : "").toString()); // GIS79/AISS + GISIF/AISS + GISSF/AISS
			Long GPF = Long.parseLong((rowList[15] != null ? rowList[15] : "").toString());
			Long MCA = Long.parseLong((rowList[16] != null ? rowList[16] : "").toString());
			Long HBA = Long.parseLong((rowList[17] != null ? rowList[17] : "").toString());

			Long scaleStartAmt = Long.parseLong((rowList[18] != null ? rowList[18] : "0").toString());
			Long scaleEndAmt = Long.parseLong((rowList[19] != null ? rowList[19] : "0").toString());
			if (Long.parseLong((rowList[28] != null ? rowList[28] : "0").toString()) != 0)
				scaleEndAmt = Long.parseLong((rowList[28] != null ? rowList[28] : "0").toString());
			Long bankAcctNo = Long.parseLong((rowList[20] != null ? rowList[20] : "").toString());
			String bankName = (rowList[21] != null ? rowList[21] : "").toString();
			String branchName = (rowList[22] != null ? rowList[22] : "").toString();
			String branchAdd = (rowList[23] != null ? rowList[23] : "").toString();
			//String incMonth = 	(rowList[24]!=null?rowList[24]:"").toString();	
			String incMonth = "";
			if (rowList[24] != null)
			{
				sdfObj = new SimpleDateFormat("MMMM");
				java.util.Date loanTime = (java.util.Date) rowList[24];
				long time = loanTime.getTime();
				Date loanDate = new Date();
				loanDate.setTime(time);
				incMonth = sdfObj.format(loanDate);
			}
			String gpfAccNo = (rowList[25] != null ? rowList[25] : "").toString();
			String itAccNo = (rowList[26] != null ? rowList[26] : "").toString();
			String designation = (rowList[27] != null ? rowList[27] : "").toString();

			ArrayList row = new ArrayList();
			row.add(empName);
			row.add(basicPay + LS);
			row.add(DPAy);
			row.add(DA);
			row.add(SPPAY);
			row.add(PERPAY);
			row.add(CLA);
			row.add(HRA);
			row.add(MA);
			row.add(WA);
			row.add(TA);
			row.add(IT);
			row.add(PTAX);
			row.add(SIS);
			row.add(GPF);
			row.add(MCA);
			row.add(HBA);

			// fir fixed pay

			if (scaleStartAmt == 0)
			{
				scaleStartAmt = basicPay + LS;
				scaleEndAmt = basicPay + LS;
			}
			row.add(scaleStartAmt);
			row.add(scaleEndAmt);
			row.add(bankAcctNo);
			row.add(bankName);
			row.add(branchName);
			row.add(branchAdd);
			row.add(incMonth);
			row.add(gpfAccNo);
			row.add(itAccNo);
			row.add(designation);

			row.add(month);
			row.add(Year);
			row.add(month);

			row.add((rowList[31] != null ? rowList[31] : "0").toString());//Pay Recovery
			row.add((rowList[32] != null ? rowList[32] : "0").toString());//Misc Recovery
			row.add((rowList[33] != null ? rowList[33] : "0").toString());//Jeep Rent
			row.add((rowList[34] != null ? rowList[34] : "0").toString());//HRR
			row.add((rowList[35] != null ? rowList[35] : "0").toString());//DAGPF
			row.add(GradePay);//gpay

			DataList.add(row);
			cnt++;
		}

		logger.info("**********************" + DataList.size());
		return DataList;
	}

	public List getPayCertificateLoan(long month, long year, long empId)
	{

		ArrayList DataList = new ArrayList();

		Calendar cal = Calendar.getInstance();
		DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
		SimpleDateFormat sdfObj = sbConf.GetDateFormat();

		cal.set(Calendar.YEAR, (int) year);
		cal.set(Calendar.MONTH, (int) month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		java.util.Date startMonthDate = cal.getTime();
		String startDate = sdfObj.format(startMonthDate);

		cal.set(Calendar.YEAR, (int) year);
		cal.set(Calendar.MONTH, (int) month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		cal.set(Calendar.DAY_OF_MONTH, totalDays);

		java.util.Date endMonthDate = cal.getTime();

		String endDate = sdfObj.format(endMonthDate);

		Session hibSession = getSession();
		String query = " select ";
		query += "   phst.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empPrefix||' '||phst.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||phst.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empMname||' '||phst.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname, ";
		query += "   phst.hrLoanEmpDtls.loanPrinAmt ,";//princi amt 1
		query += "  phst.hrLoanEmpDtls.loanPrinInstNo,";//princi install no 2
		query += "  phst.hrLoanEmpDtls.loanPrinEmiAmt,";//princi emi 3
		query += "  phst.totalRecoveredAmt,";//principal recovered 4
		query += "  phst.totalRecoveredInst,";//princi inst recovered 5

		query += "  ihst.hrLoanEmpDtls.loanInterestAmt, ";//int amt 6
		query += "  ihst.hrLoanEmpDtls.loanIntInstNo, ";//int install no 7
		query += "  ihst.hrLoanEmpDtls.loanIntEmiAmt, ";//int emi 8
		query += "  ihst.totalRecoveredInt,";//int recovered 9
		query += "  ihst.totalRecoveredIntInst,";//int installment recovered 10 
		query += "  ihst.hrLoanEmpDtls.hrLoanAdvMst.loanAdvName,";//Loan Name 11
		query += "  ihst.hrLoanEmpDtls.loanAccountNo";//Loan acctount no 12

		/*query += "  slip.trAllow, ";//TA 11 
		query += "  slip.ITax,";//IT 12
		query += "  slip.PTax,";//PTAX 13 
		query += "  slip.sis,";//sis 14
		query += "  slip.gpf,";//GPF 15
		query += "  slip.vehAdv,";//MCA 16
		query += "  slip.hba,";//HBA 17
		query += "  odtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt,";//18
		query += "  odtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt,";//19
		query += "  bdtl.bankAcctNo,";//20
		query += "  bdtl.hrEisBankMst.bankName,";//21
		query += "  bdtl.hrEisBranchMst.branchName,";//22
		query += "  bdtl.hrEisBranchMst.branchAddr,";//23
		query += "  odtl.incrementDate,";//24
		query += "  slip.gpfAccNo,";//25
		query += "  slip.itAccNo ";//26
		*/
		query += " from ";
		//query+=" HrLoanEmpIntRecoverDtls idtl ,";
		query += " HrLoanEmpPrinRecoverHst phst ,";
		query += " HrLoanEmpIntRecoverHst ihst ";

		//query+=" HrEisOtherDtls dtl,HrEisBankDtls b";
		query += " where  ";
		//query+=" pdtl.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId=idtl.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId and ";
		query += " phst.hrLoanEmpDtls.empLoanId = ihst.hrLoanEmpDtls.empLoanId and ";
		//query+=" ihst.hrLoanEmpDtls.empLoanId = idtl.hrLoanEmpDtls.empLoanId and ";

		if (empId != -1)
			query += " phst.hrLoanEmpDtls.hrEisEmpMst.empId = " + empId + " and ";

		query += "  ((phst.updatedDate>='" + startDate + "' and";
		query += "  phst.updatedDate<='" + endDate + "') or ";

		query += "  (ihst.updatedDate>='" + startDate + "' and";
		query += "  ihst.updatedDate<='" + endDate + "')) ";

		logger.info("***Query for getLedgerFormData details**" + query);

		Query sqlQuery = hibSession.createQuery(query);

		//		ArrayList dataList = new ArrayList();
		List RowList = sqlQuery.list();
		logger.info("*************************" + RowList.size());
		int cnt = 1;

		Iterator itr = RowList.iterator();
		while (itr.hasNext())
		{
			Object[] rowList = (Object[]) itr.next();
			String empName = (rowList[0] != null ? rowList[0] : "").toString();
			Long princiAmt = Long.parseLong((rowList[1] != null ? rowList[1] : "").toString());
			Long princiInstallNo = Long.parseLong((rowList[2] != null ? rowList[2] : "").toString());
			Long princiEmi = Long.parseLong((rowList[3] != null ? rowList[3] : "").toString());
			Long principalRecovered = Long.parseLong((rowList[4] != null ? rowList[4] : "").toString());
			Long princiInstRecovered = Long.parseLong((rowList[5] != null ? rowList[5] : "").toString());
			Long intAmt = Long.parseLong((rowList[6] != null ? rowList[6] : "").toString());
			Long intInstallNo = Long.parseLong((rowList[7] != null ? rowList[7] : "").toString());
			Long intEmi = Long.parseLong((rowList[8] != null ? rowList[8] : "").toString());
			Long intRecovered = Long.parseLong((rowList[9] != null ? rowList[9] : "").toString());
			Long intInstallmentRecovered = Long.parseLong((rowList[10] != null ? rowList[10] : "").toString());
			String LoanName = (rowList[11] != null ? rowList[11] : "").toString();
			String LoanAcctountNo = rowList[12] != null ? (String) rowList[12] : "";
			/*Long PTAX = Long.parseLong((rowList[13]!=null?rowList[13]:"").toString());
			Long SIS = Long.parseLong((rowList[14]!=null?rowList[14]:"").toString());
			Long GPF = Long.parseLong((rowList[15]!=null?rowList[15]:"").toString());		            
			Long MCA = Long.parseLong((rowList[16]!=null?rowList[16]:"").toString());	
			Long HBA = Long.parseLong((rowList[17]!=null?rowList[17]:"").toString());
			Long scaleStartAmt = Long.parseLong((rowList[18]!=null?rowList[18]:"").toString());
			Long scaleEndAmt = Long.parseLong((rowList[19]!=null?rowList[19]:"").toString());
			Long bankAcctNo = Long.parseLong((rowList[20]!=null?rowList[20]:"").toString());		            
			String bankName = 	(rowList[21]!=null?rowList[21]:"").toString();	 
			String branchName = 	(rowList[22]!=null?rowList[22]:"").toString();	 
			String branchAdd = 	(rowList[23]!=null?rowList[23]:"").toString();			            
			//String incMonth = 	(rowList[24]!=null?rowList[24]:"").toString();	
			String incMonth = "";
			if(rowList[24]!=null)
				{
					SimpleDateFormat sdfObj = new SimpleDateFormat("MMMM");
					java.sql.Date loanTime = (java.sql.Date)rowList[24];
					long time = loanTime.getTime();
			        Date loanDate = new Date();
			        loanDate.setTime(time);
			        incMonth = sdfObj.format(loanDate);
				}
			String gpfAccNo = 	(rowList[25]!=null?rowList[25]:"").toString();			            
			String itAccNo = 	(rowList[26]!=null?rowList[26]:"").toString();	*/

			ArrayList row = new ArrayList();
			row.add(empName);

			if (princiAmt >= principalRecovered && intRecovered == 0)
			{
				row.add("Principal");
				row.add(princiAmt);
				row.add(princiInstallNo);
				row.add(princiEmi);
				row.add(principalRecovered);
				row.add(princiInstRecovered);

			}
			if (princiAmt <= principalRecovered && intRecovered != 0)
			{
				row.add("Interest");
				row.add(intAmt);
				row.add(intInstallNo);
				row.add(intEmi);
				row.add(intRecovered);
				row.add(intInstallmentRecovered);

			}

			row.add(LoanName);
			row.add(LoanAcctountNo);
			/*row.add(PTAX);
			row.add(SIS);
			row.add(GPF);
			row.add(MCA);
			row.add(HBA);
			row.add(scaleStartAmt);
			row.add(scaleEndAmt);
			row.add(bankAcctNo);
			row.add(bankName);
			row.add(branchName);
			row.add(branchAdd);
			row.add(incMonth);
			row.add(gpfAccNo);
			row.add(itAccNo);*/

			row.add(month);
			row.add(year);
			row.add(month);
			DataList.add(row);
			cnt++;
		}

		logger.info("**********************" + DataList.size());
		return DataList;
	}

	public List getPayCertificateLoanData(long month, long year, long empId)
	{

		ArrayList DataList = new ArrayList();

		Session hibSession = getSession();
		String query = " select ";
		query += "   hploan.paybillId.hrEisEmpMst.orgEmpMst.empPrefix||' '||hploan.paybillId.hrEisEmpMst.orgEmpMst.empFname||' '||hploan.paybillId.hrEisEmpMst.orgEmpMst.empMname||' '||hploan.paybillId.hrEisEmpMst.orgEmpMst.empLname, ";
		query += "   hploan.hrLoanEmpDtls.loanPrinAmt ,";//princi amt 1
		query += "  hploan.hrLoanEmpDtls.loanPrinInstNo,";//princi install no 2
		query += "  hploan.hrLoanEmpDtls.loanPrinEmiAmt,";//princi emi 3
		query += "  hploan.recoveredAmt,";// recovered 4
		query += "  hploan.recoveredInst,";// inst recovered 5       
		query += "  hploan.hrLoanEmpDtls.loanInterestAmt, ";//int amt 6
		query += "  hploan.hrLoanEmpDtls.loanIntInstNo, ";//int install no 7
		query += "  hploan.hrLoanEmpDtls.loanIntEmiAmt, ";//int emi 8
		query += "  hploan.recoveryType.lookupId,";//recoveryType 9
		query += "  hploan.hrLoanEmpDtls.hrLoanAdvMst.loanAdvName,";//Loan Name 10
		query += "  hploan.hrLoanEmpDtls.loanAccountNo";//Loan acctount no 11
		query += " from ";

		query += " HrPayPaybillLoanDtls hploan,PaybillHeadMpg bhm  ";
		query += " where  ";

		query += " bhm.hrPayPaybill = hploan.paybillId.paybillGrpId  and  " + " bhm.approveFlag in (" + resourceBundle.getString("created") + "," + resourceBundle.getString("approved") + ") and" + " bhm.month=" + month + " and bhm.year=" + year + "  " + " and bhm.orgGradeMst.gradeId = hploan.paybillId.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  ";

		if (empId != -1)
			query += " and hploan.paybillId.hrEisEmpMst.empId = " + empId + "  ";

		logger.info("***Query for getPayCertificateLoanData details**" + query);

		Query hqlQuery = hibSession.createQuery(query);

		//		ArrayList dataList = new ArrayList();
		List RowList = hqlQuery.list();
		logger.info("***************getPayCertificateLoan Data **********" + RowList.size());
		int cnt = 1;

		Iterator itr = RowList.iterator();
		while (itr.hasNext())
		{
			Object[] rowList = (Object[]) itr.next();
			String empName = (rowList[0] != null ? rowList[0] : "").toString();
			long princiAmt = Long.parseLong((rowList[1] != null ? rowList[1] : "").toString());
			long princiInstallNo = Long.parseLong((rowList[2] != null ? rowList[2] : "").toString());
			long princiEmi = Long.parseLong((rowList[3] != null ? rowList[3] : "").toString());
			long totalRecovered = Long.parseLong((rowList[4] != null ? rowList[4] : "").toString());
			long totalInstRecovered = Long.parseLong((rowList[5] != null ? rowList[5] : "").toString());
			long intAmt = Long.parseLong((rowList[6] != null ? rowList[6] : "").toString());
			long intInstallNo = Long.parseLong((rowList[7] != null ? rowList[7] : "").toString());
			long intEmi = Long.parseLong((rowList[8] != null ? rowList[8] : "").toString());
			long recoveryType = Long.parseLong((rowList[9] != null ? rowList[9] : "").toString());
			String LoanName = (rowList[10] != null ? rowList[10] : "").toString();
			String LoanAcctountNo = rowList[11] != null ? (String) rowList[11] : "";

			ArrayList row = new ArrayList();
			row.add(empName);

			if (recoveryType == Long.parseLong(resourceBundle.getString("principal")))
			{
				row.add("Principal");
				row.add(princiAmt);
				row.add(princiInstallNo);
				row.add(princiEmi);
				row.add(totalRecovered);
				row.add(totalInstRecovered);
			}
			else
			{
				row.add("Interest");
				row.add(intAmt);
				row.add(intInstallNo);
				row.add(intEmi);
				row.add(totalRecovered);
				row.add(totalInstRecovered);
			}

			row.add(LoanName);
			row.add(LoanAcctountNo);
			row.add(month);
			row.add(year);
			row.add(month);
			DataList.add(row);
			cnt++;
		}

		logger.info("*************getPayCertificateLoanData *********" + DataList.size());
		return DataList;
	}

	public List getPayslipData(int month, int year, String category, String Grade, String dsgnId, String subHeadId, long langId, String givenBillNo)
	{
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		long AISGradeCode = Long.parseLong(constantsBundle.getString("AISGradeCode"));
		long GradeCode3 = Long.parseLong(constantsBundle.getString("GradeCode3"));
		long GradeCode4 = Long.parseLong(constantsBundle.getString("GradeCode4"));
		long GradeCode1 = Long.parseLong(constantsBundle.getString("GradeCode1"));
		long GradeCode2 = Long.parseLong(constantsBundle.getString("GradeCode2"));

		if (category.equals("AIS"))
			Grade = AISGradeCode + "";
		else if (category.equals("Gadgeted"))
			Grade = GradeCode1 + "','" + GradeCode2;
		else if (category.equals("Non-Gadgeted"))
			Grade = GradeCode3 + "','" + GradeCode4;

		List paySlipList = null;
		Session hibSession = getSession();
		StringBuilder strQuery = new StringBuilder();
		/*strQuery.append(" select *  from hr_pay_payslip ps, org_emp_mst o ");
		//Added By Paurav for checking employees of particular designation
		if(dsgnId!=null && !dsgnId.equals(""))
		{
			strQuery.append(", org_userpost_rlt usrpst, org_post_details_rlt pst ");
		}
		//Ended By Paurav
		strQuery.append(" where ps.emp_id = o.emp_id "); 
		if(dsgnId!=null && !dsgnId.equals(""))
		{
			strQuery.append(" and o.user_id = usrpst.user_id and pst.post_id = usrpst.post_id ");
			strQuery.append(" and usrpst.activate_flag = 1 and pst.dsgn_id in (").append(dsgnId).append(") ");
		}
		if(Grade!=null&&!Grade.equals("")&&!Grade.equals("-1"))
		    strQuery.append(" and o.grade_id in ('"+Grade+"') ");
		strQuery.append(" and ps.paybill_id in (select p.id from hr_pay_paybill p where p.paybill_grp_id in ");
		strQuery.append("   (select ph.paybill_id  from paybill_head_mpg ph ");
		if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
			strQuery.append(" 	where ph.subhead_id = "+subHeadId+"	");
		strQuery.append(" )) and ps.month = "+month+" and ps.year = "+year+" ");	
		
		//Added By Paurav for checking lang id
		strQuery.append(" and o.lang_id = ").append(langId);
		if(dsgnId!=null && !dsgnId.equals("")) strQuery.append(" and pst.lang_id = ").append(langId);
		//Ended By Paurav */

		//Added by Paurav. Query Changed by Paurav to directly get the list of employees not in payslip
		strQuery.append(" select pb.id from  org_emp_mst o, hr_pay_paybill pb ,paybill_head_mpg pbh");
		if (dsgnId != null && !dsgnId.equals(""))
		{
			strQuery.append(", org_post_details_rlt pst,  org_userpost_rlt usrpst ");
		}
		strQuery.append(" where pb.emp_id = o.emp_id ");

		strQuery.append(" and pbh.approve_flag = 0 and pb.paybill_grp_id=pbh.paybill_id");//only unapproved records NOT rejected or deleted

		if (dsgnId != null && !dsgnId.equals(""))
		{
			strQuery.append("and o.user_id = usrpst.user_id and pst.post_id = usrpst.post_id ");
			strQuery.append(" and usrpst.activate_flag = 1 and pst.dsgn_id in (").append(dsgnId).append(") ");
		}

		if (Grade != null && !Grade.equals("") && !Grade.equals("-1"))
			strQuery.append(" and o.grade_id in('").append(Grade).append("') ");

		if (subHeadId != null && !subHeadId.equals("") && !subHeadId.equals("-1"))
		{
			strQuery.append(" and pb.paybill_grp_id in (select distinct ph.paybill_id  from paybill_head_mpg ph ");
			strQuery.append(" where ph.subhead_id = '").append(subHeadId).append("') ");
		}

		if (givenBillNo != null && !givenBillNo.equals("") && !givenBillNo.equals("-1"))
		{
			strQuery.append("  and pb.paybill_grp_id in (select b.paybill_id from Paybill_Billreg_Mpg b,trn_bill_register t ");
			strQuery.append("  where t.bill_no = b.trn_bill_id ");
			strQuery.append("  and t.bill_no =" + givenBillNo + " )  ");

		}

		strQuery.append(" and pbh.paybill_month = '").append(month).append("' and pbh.paybill_year = '").append(year).append("' ");
		strQuery.append(" and pb.id not in (select paybill_id from hr_pay_payslip ps where ps.payslip_month = pbh.paybill_month and ps.payslip_year = pbh.paybill_year) ");

		strQuery.append(" and o.lang_id = 1 ");
		if (dsgnId != null && !dsgnId.equals(""))
			strQuery.append(" and pst.lang_id = 1 ");

		logger.info("Query for getting unapproved from paybill in PayslipDAO getPayslipData method is \n " + strQuery.toString());

		Query query = hibSession.createSQLQuery(strQuery.toString());
		paySlipList = query.list();
		return paySlipList;
	}

	/**
	 * @method getLoanDetailByEmpId()
	 * @author Urvin shah.
	 * @date   31/03/2008. 
	 * @param empId
	 * @return loanDetails
	 */

	public List getLoanDetailByEmpId(long empId, String strCurrMonthMinDate, String strCurrMonthMaxDate)
	{
		List loanDetails = new ArrayList();
		StringBuilder queryString = new StringBuilder();
		Session hibSession = getSession();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		int activeFlagStatus = Integer.parseInt(resourceBundle.getString("activate"));
		queryString.append("select loanDtls.hrEisEmpMst.empId,");
		queryString.append("loanDtls.hrLoanAdvMst.loanAdvId,");
		queryString.append("loanDtls.loanPrinAmt,");
		queryString.append("loanDtls.loanInterestAmt,");
		queryString.append("loanDtls.loanAccountNo,");
		queryString.append("loanDtls.loanPrinInstNo,");
		queryString.append("loanDtls.loanIntInstNo,");
		queryString.append("(select max(prin.totalRecoveredAmt)");
		queryString.append(" from HrLoanEmpPrinRecoverHst prin");
		queryString.append(" where loanDtls.empLoanId = prin.hrLoanEmpDtls.empLoanId and");
		queryString.append(" prin.updatedDate between to_date('" + strCurrMonthMinDate + "', 'dd/MM/yyyy') and");
		queryString.append(" to_date('" + strCurrMonthMaxDate + "','dd/MM/yyyy')");
		queryString.append(" group by prin.hrLoanEmpDtls.empLoanId),");
		queryString.append("(select max(prin.totalRecoveredInst)");
		queryString.append(" from HrLoanEmpPrinRecoverHst prin");
		queryString.append(" where loanDtls.empLoanId = prin.hrLoanEmpDtls.empLoanId and");
		queryString.append(" prin.updatedDate between to_date('" + strCurrMonthMinDate + "', 'dd/MM/yyyy') and");
		queryString.append(" to_date('" + strCurrMonthMaxDate + "', 'dd/MM/yyyy')");
		queryString.append(" group by prin.hrLoanEmpDtls.empLoanId),");
		queryString.append("(select max(interest.totalRecoveredInt)");
		queryString.append(" from HrLoanEmpIntRecoverHst interest");
		queryString.append(" where loanDtls.empLoanId = interest.hrLoanEmpDtls.empLoanId and");
		queryString.append(" interest.updatedDate between to_date('" + strCurrMonthMinDate + "', 'dd/MM/yyyy') and");
		queryString.append(" to_date('" + strCurrMonthMaxDate + "','dd/MM/yyyy')");
		queryString.append(" group by interest.hrLoanEmpDtls.empLoanId),");
		queryString.append("(select max(interest.totalRecoveredIntInst)");
		queryString.append(" from HrLoanEmpIntRecoverHst interest");
		queryString.append(" where loanDtls.empLoanId = interest.hrLoanEmpDtls.empLoanId and");
		queryString.append(" interest.updatedDate between to_date('" + strCurrMonthMinDate + "', 'dd/MM/yyyy') and");
		queryString.append(" to_date('" + strCurrMonthMaxDate + "', 'dd/MM/yyyy')");
		queryString.append(" group by interest.hrLoanEmpDtls.empLoanId)");
		queryString.append(" from HrLoanEmpDtls loanDtls");
		queryString.append(" where loanDtls.loanActivateFlag = " + activeFlagStatus + " and loanDtls.hrEisEmpMst.empId = " + empId);
		queryString.append(" and loanDtls.loanDate <= to_date('" + strCurrMonthMaxDate + "','dd/MM/yyyy')");

		logger.info("Query is:-" + queryString.toString());

		/*queryString.append("select loanDtls.hrEisEmpMst.empId,");
		queryString.append("loanDtls.hrLoanAdvMst.loanAdvId,");
		queryString.append("loanDtls.loanPrinAmt,");
		queryString.append("loanDtls.loanInterestAmt,");
		queryString.append("loanDtls.loanAccountNo,");
		queryString.append("prinDtls.totalRecoveredAmt,");
		queryString.append("interestDtls.totalRecoveredInt ");
		queryString.append(" from HrLoanEmpDtls loanDtls, HrLoanEmpPrinRecoverDtls prinDtls,HrLoanEmpIntRecoverDtls interestDtls ");
		queryString.append(" where loanDtls.empLoanId=prinDtls.hrLoanEmpDtls.empLoanId ");
		queryString.append(" and loanDtls.empLoanId=interestDtls.hrLoanEmpDtls.empLoanId ");
		queryString.append(" and loanDtls.loanActivateFlag=1");
		queryString.append(" and loanDtls.hrEisEmpMst.empId="+empId);*/
		Query loanDtlQuery = hibSession.createQuery(queryString.toString());
		loanDetails = loanDtlQuery.list();
		return loanDetails;
	}

	/**
	 * Author 	:  Urvin Shah
	 * Date		:  05/04/2008
	 * Function	:  This method fetch the Current Financial Year's Income Tax related Data up to the given Date.		 
	 * @param currDate
	 * @param empId
	 * @return payBillList
	 */

	public List getCurrentYearPayslipDataByEmpId(Date currDate, long empId)
	{
		List payBillList = null;
		Session hibSession = getSession();

		//		String currDateMonYear = (currDate.getMonth() + 1) + "/" + (currDate.getYear() + 1900);
		String compareFromDate = "";
		String compareToDate = "";
		compareToDate = (currDate.getMonth() + 1) + "/" + (currDate.getYear() + 1900);

		if ((currDate.getMonth() + 1) <= 3)
		{
			compareFromDate = "4/" + ((currDate.getYear() + 1900) - 1);
		}
		else
		{
			compareFromDate = "4/" + (currDate.getYear() + 1900);
		}
		StringBuilder strQuery = new StringBuilder();

		strQuery.append("select sum(payBill.allow0113),");
		strQuery.append("sum(payBill.deduc9570),");
		strQuery.append("sum(payBill.deduc9670),");
		strQuery.append("sum(payBill.loan9591),");
		strQuery.append("sum(payBill.loanInt9591),");
		strQuery.append("sum(payBill.deduc9580),");
		strQuery.append("sum(payBill.deduc9581),");
		strQuery.append("sum(payBill.deduc9582),");
		strQuery.append("sum(payBill.deduc9583),");
		strQuery.append("sum(payBill.deduc9584), ");
		strQuery.append("sum(payBill.it), ");
		strQuery.append("sum(payBill.deduc9531), ");
		strQuery.append("sum(payBill.grossAmt) ");
		strQuery.append("from HrPayPaybill as payBill ");
		strQuery.append("where payBill.hrEisEmpMst.empId=" + empId);
		strQuery.append(" and payBill.approveFlag=1");
		strQuery.append(" and to_date(payBill.month || '/' || payBill.year,'MM/YYYY') between ");
		strQuery.append("to_date('" + compareFromDate + "','MM/YYYY') and ");
		strQuery.append("to_date('" + compareToDate + "','MM/YYYY') group by payBill.hrEisEmpMst.empId");

		Query query = hibSession.createQuery(strQuery.toString());
		payBillList = query.list();
		return payBillList;
	}

	public long getPayBillGrpId(String givenBillNo, long locId, int currMonth, int currYear)
	{
		Session hibSession = getSession();
		logger.info("Inside get Group ID function of PayslipDAO - getPayBillGrpId");
		logger.info("givenBillNo is " + givenBillNo);
		logger.info("given month is " + currMonth);
		logger.info("given year is " + currYear);

		StringBuffer strQuery = new StringBuffer();
		if (givenBillNo != null && !givenBillNo.equals("") && !givenBillNo.equals("-1"))
		{
			//Commented By Mrugesh for SQL to HQL conversion
			/*strQuery.append("  select b.paybill_id from Paybill_Billreg_Mpg b,trn_bill_register t ");
			strQuery.append("  where t.bill_no = b.trn_bill_id ");
			strQuery.append("  and t.bill_no ="+ givenBillNo + "   ");*/
			//Ended By Mrugesh for SQL to HQL conversion
			//Added By Mrugesh for SQL to HQL conversion
			/*strQuery.append("  select b.hrPayPaybill from PaybillBillregMpg b,TrnBillRegister t ");
			strQuery.append("  where t.billNo = b.trnBillRegister.billNo ");
			strQuery.append("  and t.billNo ="+ givenBillNo + "   ");*/

			strQuery.append(" select mpg.hrPayPaybill from PaybillHeadMpg as mpg where ");
			strQuery.append(" mpg.billNo.dcpsDdoBillGroupId = " + givenBillNo + "  and mpg.cmnLocationMst.locId = " + locId + " and mpg.month = " + currMonth + " and mpg.year=" + currYear + " and mpg.approveFlag=0 and mpg.billTypeId = " + paybillTypeId);

			//Ended By Mrugesh for SQL to HQL conversion

		}

		logger.info("HQL Query for getting getPayBillGrpId s \n " + strQuery.toString());

		Query query = hibSession.createQuery(strQuery.toString());
		List dataList = query.list();
		long PayBillGrpId = 0;
		if (dataList != null && dataList.size() > 0)
		{
			PayBillGrpId = Long.parseLong((dataList.get(0) != null ? dataList.get(0).toString() : "0").toString());

		}
		return PayBillGrpId;
	}

	/*	select b.paybill_id from paybill_head_mpg b where b.bill_no in
		(select pbs.bill_subhead_id  from hr_pay_bill_subhead_mpg pbs where pbs.bill_no=1 ) and loc_id = 2910 and paybill_month = 11 and paybill_year=2008 and approve_flag=0;
	*/

	public long getPaybillIdForInner(String givenBillNo, long locId, int currMonth, int currYear)
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		if (givenBillNo != null && !givenBillNo.equals("") && !givenBillNo.equals("-1"))
		{
			//Commented By Mrugesh for SQL to HQL conversion
			/*strQuery.append("  select b.paybill_id from paybill_head_mpg b where b.bill_no in ");
			strQuery.append(" (select pbs.bill_subhead_id  from hr_pay_bill_subhead_mpg pbs where pbs.bill_no="+givenBillNo+" ) and loc_id = "+locId+" and paybill_month = "+currMonth+" and paybill_year="+currYear+" and approve_flag=0 ");*/
			//Ended By Mrugesh for SQL to HQL conversion
			//Added By Mrugesh for SQL to HQL conversion
			strQuery.append("  select b.hrPayPaybill from PaybillHeadMpg b where b.billNo in ");
			strQuery.append(" (select pbs.billHeadId  from HrPayBillHeadMpg pbs where pbs.billId=" + givenBillNo + " ) and b.cmnLocationMst.locId = " + locId + " and b.month = " + currMonth + " and b.year=" + currYear + " and b.approveFlag=0 and b.billTypeId = " + paybillTypeId);
			//Ended By Mrugesh for SQL to HQL conversion
			//strQuery.append("  and t.bill_no ="+ givenBillNo + "   ");

		}

		logger.info("Query for getting getPayBillGrpId s \n " + strQuery.toString());

		Query query = hibSession.createQuery(strQuery.toString());
		List dataList = query.list();
		long PayBillGrpId = 0;
		if (dataList != null && dataList.size() > 0)
		{
			PayBillGrpId = Long.parseLong((dataList.get(0) != null ? dataList.get(0).toString() : "0").toString());

		}
		return PayBillGrpId;
	}

	public List checkPayslipForInner(String givenBillNo, long locId, int currMonth, int currYear)
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		if (givenBillNo != null && !givenBillNo.equals("") && !givenBillNo.equals("-1"))
		{
			//Commented By Mrugesh for SQL to HQL conversion
			/*strQuery.append("  select b.paybill_id from paybill_head_mpg b where b.bill_no in ");
			strQuery.append(" (select pbs.bill_subhead_id  from hr_pay_bill_subhead_mpg pbs where pbs.bill_no="+givenBillNo+" ) and loc_id = "+locId+" and paybill_month = "+currMonth+" and paybill_year="+currYear+" and approve_flag=1 ");*/
			//strQuery.append("  and t.bill_no ="+ givenBillNo + "   ");
			//Ended By Mrugesh for SQL to HQL conversion
			//Added By Mrugesh for SQL to HQL conversion
			strQuery.append("  from PaybillHeadMpg as mpg where ");
			strQuery.append(" mpg.billNo.dcpsDdoBillGroupId = " + givenBillNo + "  and mpg.cmnLocationMst.locId = " + locId + " and mpg.month = " + currMonth + " and mpg.year=" + currYear + " and mpg.approveFlag=1 and mpg.billTypeId = " + paybillTypeId);
			//strQuery.append(" (select pbs.billHeadId  from HrPayBillHeadMpg pbs where pbs.billId="+givenBillNo+" ) and b.cmnLocationMst.locId = "+locId+" and b.month = "+currMonth+" and b.year="+currYear+" and b.approveFlag=1 and b.billTypeId = "+paybillTypeId);
			//Ended By Mrugesh for SQL to HQL conversion

		}

		logger.info("HQL Query for getting checking payslip records exist or not  " + strQuery.toString());

		Query query = hibSession.createQuery(strQuery.toString());
		List dataList = query.list();
		return dataList;
	}

	public List getBillData(long finYrId, long locId)
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		//Commented By Mrugesh for SQL to HQL conversion
		//query.append("select s.subhead_code ,s.class_id,s.dsgn_id,s.bill_no from hr_pay_bill_subhead_mpg s where fin_year_id="+finYrId+"and loc_id="+locId+"order by bill_no ");
		//Ended By Mrugesh for SQL to HQL conversion

		//Added By Mrugesh for SQL to HQL conversion
		query.append("select s.subheadId ,s.class_Id,s.dsgn_Id,s.billId from HrPayBillHeadMpg s left outer join s.postType  where s.finYearId= " + finYrId + " and s.cmnLocationMst.locId= " + locId + " order by s.billId ");

		//Ended By Mrugesh for SQL to HQL conversion
		logger.info("HQL Query for get getBillData is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();

		return resList;
	}

	//added by manish
	public double getNonGovernmentData(long paybillId, String col)
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();

		query.append("select " + col + " from hr_pay_payslip_non_govt where paybill_id=" + paybillId);

		logger.info("HQL Query for get getBillData is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		resList = sqlQuery.list();
		if (resList != null && resList.size() > 0)
			return Double.parseDouble(resList.get(0).toString());
		else
			return 0;
	}

	//Modified By Kishan
	public Map<Long, Map<String, Long>> getNonGovDataFromPaybillId(String paybillId, long locId)
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();

		sb.append("SELECT payslip.EMP_ID, ");//0
		sb.append("deduc.DEDUC_NAME, ");//1
		sb.append("ngpayslip.NON_GOV_DEDUC_AMOUNT ");//2
		sb.append("FROM HR_PAY_PAYSLIP_NON_GOVT ngpayslip, HR_PAY_COMPONENT_GRP_MST compoGrp, HR_PAY_LOC_COMPONENT_MPG locComp, ");
		sb.append("HR_PAY_DEDUC_TYPE_MST deduc, HR_PAY_PAYSLIP payslip ");
		sb.append("where ");
		sb.append("ngpayslip.PAYBILL_ID in ( " + paybillId + " ) ");
		sb.append("and ngpayslip.NON_GOV_DEDUC_CODE = deduc.DEDUC_CODE ");
		sb.append("and deduc.DEDUC_CODE = locComp.COMPO_ID and locComp.COMPO_TYPE = 2500135 ");
		sb.append("and locComp.ISACTIVE = 1 ");
		sb.append("and locComp.COMPO_GRP_ID = compoGrp.COMPO_GROUP_ID ");
		sb.append("and compoGrp.IS_ACTIVE = 1 and compoGrp.LOC_ID = " + locId + " ");
		sb.append("and payslip.PAYBILL_ID = ngpayslip.PAYBILL_ID ");
		sb.append("order by payslip.EMP_ID ");

		logger.info("HQL Query for get getBillData is---->>>>" + sb.toString());
		Query sqlQuery = hibSession.createSQLQuery(sb.toString());
		resList = sqlQuery.list();
		Map<Long, Map<String, Long>> dataMap = new HashMap<Long, Map<String, Long>>();
		if (!resList.isEmpty())
		{
			int size = resList.size();
			Object[] data = null;
			long currEmpId = 0;
			long previousEmpId = 0;
			long empId = 0;

			for (int i = 0; i < size; i++)
			{
				data = (Object[]) resList.get(i);
				empId = Long.valueOf(data[0].toString());
				currEmpId = empId;
				if (currEmpId != previousEmpId)
				{
					Map<String, Long> amountMap = new HashMap<String, Long>();
					amountMap.put(data[1].toString(), Long.valueOf(data[2].toString()));
					dataMap.put(currEmpId, amountMap);
					previousEmpId = currEmpId;
				}
				else
				{
					Map<String, Long> amountMap = (Map) dataMap.get(currEmpId);
					amountMap.put(data[1].toString(), Long.valueOf(data[2].toString()));
					dataMap.put(currEmpId, amountMap);
				}
			}
		}
		return dataMap;
	}

	//Modified By Kishan

	//ended
	public List getGPFAcctNoEmployeeId(String EmployeeId)
	{
		logger.info("in getGPFAcctNoEployeeId...........");
		List list = new ArrayList();
		

		//		String GPFacctNo = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append("select gpf from OrgEmpMst emp,HrPayGpfBalanceDtls gpf");
		query.append(" where emp.orgUserMst.userId=gpf.userId and emp.empId=" + EmployeeId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getGPFAcctNoEployeeId query :: " + sqlQuery);

		list = sqlQuery.list();
		logger.info("==> getGPFAcctNoEployeeId query list:: " + list.size());
		if (list.size() > 0)
		{
			if (list.get(0) != null)
			{
				return list;
			}
		}
		else
		{
			return null;
		}

		return list;
	}

	//Added by Abhilash
	public List getDsgnsFromBillNo(int month, int year, String billNo, long locId)
	{
		 List  classList = null;
	     Session hibSession = getSession();
	     StringBuffer strQuery = new StringBuffer();
	     strQuery.append(" SELECT dsgn.DSGN_ID,dsgn.DSGN_NAME,o.emp_id, o.EMP_LNAME ||' '|| o.EMP_FNAME||' '|| o.EMP_MNAME ");
	     strQuery.append(" FROM HR_PAY_PAYBILL  hrpaybill,org_emp_mst  o,org_post_details_rlt pd,org_designation_mst  dsgn,hr_eis_emp_mst  em,org_grade_mst grdmst,PAYBILL_HEAD_MPG paybillhead ");
	     strQuery.append(" WHERE grdmst.grade_id = o.grade_id and em.emp_mpg_id=o.emp_id and hrpaybill.EMP_ID = em.emp_id AND hrpaybill.PAYBILL_GRP_ID=paybillhead.PAYBILL_ID and paybillhead.PAYBILL_MONTH ="+month+" AND  paybillhead.PAYBILL_YEAR ="+year+" and paybillhead.approve_flag = "+resourceBundle.getString("approved")+"  ");
	     if(billNo!=null && !billNo.trim().equals(""))
	    	 strQuery.append("  AND  paybillhead.bill_no ="+billNo+" AND ");
	     strQuery.append(" o.emp_id = em.emp_mpg_id AND  pd.dsgn_id = dsgn.dsgn_id AND pd.post_id = hrpaybill.post_id AND o.lang_id = 1 AND pd.LOC_ID="+locId+" AND dsgn.lang_id = 1");
	     Query query = hibSession.createSQLQuery(strQuery.toString());
		 classList = query.list();
	     return classList;
	}

	//modified by Kishan - start
	public List<HrPayPaybill> getBillData(long locId, long billNo, long month, long year, long dsgnIdFromBillNo, long employeeId)
	{
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select pb from HrPayPaybill pb,PaybillHeadMpg ph ");
		if (dsgnIdFromBillNo > 0)
		{
			query.append(",OrgPostDetailsRlt postdetail, OrgDesignationMst designation ");
			query.append("where ");
			query.append("pb.orgPostMst.postId = postdetail.orgPostMst.postId ");
			query.append("and postdetail.cmnLocationMst.locId = " + locId + " ");
			query.append("and postdetail.orgDesignationMst.dsgnId = designation.dsgnId ");
			query.append("and designation.dsgnId = " + dsgnIdFromBillNo + " ");
		}
		else if (employeeId > 0)
		{
			query.append(",OrgPostDetailsRlt postdetail,OrgUserpostRlt userpost,OrgEmpMst emp ");
			query.append("where ");
			query.append("pb.orgPostMst.postId = postdetail.orgPostMst.postId ");
			query.append("and postdetail.cmnLocationMst.locId = " + locId + " ");
			query.append("and postdetail.orgPostMst.postId = userpost.orgPostMstByPostId.postId ");
			query.append("and userpost.orgUserMst.userId = emp.orgUserMst.userId ");
			query.append("and emp.empId = " + employeeId + " ");
		}
		query.append("and pb.cmnLocationMst.locId = " + locId + " ");
		query.append("and pb.paybillGrpId = ph.hrPayPaybill ");
		query.append("and ph.billNo = " + billNo + " ");
		query.append("and ph.month = " + month + " ");
		query.append("and ph.year = " + year + " ");
		query.append("and ph.approveFlag in (0,1) ");
		query.append("and pb.hrEisEmpMst.empId is not null ");
		query.append("order by pb.hrEisEmpMst.empId ");
		logger.info("Query from PaybillDAO " + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		return sqlQuery.list();
	}

	//modified by Kishan - end

	//Ended by Abhilash

	//Modified By Kishan - Start
	public List getVoucheNoandVoucherDate(long locId, long billNo, long month, long year)
	{
		logger.info("in getVoucheNoandVoucherDate...........");
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select head.voucherNumber, head.voucherDate from PaybillHeadMpg head ");
		query.append("where head.billNo = " + billNo + " ");
		query.append("and head.month = " + month + " ");
		query.append("and head.year = " + year + " ");
		query.append("and head.approveFlag = 1 ");
		query.append("and head.billTypeId = 2500337 ");
		query.append("and head.cmnLocationMst.locId = " + locId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("==> getVoucheNoandVoucherDate query :: " + sqlQuery);
		return sqlQuery.list();
	}

	//Modified By Kishan - End

	public List getHrPayPaybillFromHeadMpg(long paybillId)
	{
		logger.info("in getHrPayPaybillFromHeadMpg...........");
		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select pb from HrPayPaybill pb, PaybillHeadMpg head ");
		query.append(" where head.hrPayPaybill= :paybillId and head.hrPayPaybill = pb.paybillGrpId");
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("==> getHrPayPaybillFromHeadMpg query :: " + sqlQuery);
		sqlQuery.setParameter("paybillId", paybillId);
		list = sqlQuery.list();
		logger.info("==> getHrPayPaybillFromHeadMpg query list:: " + list.size());
		return list;
	}

	public String getHrEisProofDtls(long userId)
	{
		logger.info("in getHrEisProofDtls..........." + userId);
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" SELECT proof FROM HrEisProofDtl proof where proof.orgPostMstByUserId.userId = :userId ");
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("==> getHrEisProofDtls query :: " + sqlQuery);
		sqlQuery.setParameter("userId", userId);
		HrEisProofDtl hrEisProofDtl = (HrEisProofDtl) sqlQuery.uniqueResult();
		if (hrEisProofDtl != null)
			return hrEisProofDtl.getProofNum();
		else
			return null;
	}

	public int updateHrLoanEmpDtls(HrLoanEmpDtls hrLoanEmpDtls)
	{
		logger.info("in updateHrLoanEmpDtls...........");
		Session hibSession = getSession();
		StringBuffer lStrBuffer = new StringBuffer();
		lStrBuffer.append(" update hr_loan_emp_dtls set MULTI_INST_RECVD = :mInstRec,MULTI_AMT_RECVD = :mAmtRecd,");
		lStrBuffer.append(" UPDATED_DATE = :updatedDate,loan_rec_type = :recType,LOAN_REC_REMARKS = :recRemarks  where emp_loan_id = :empLoanId");
		logger.info("==> updateHrLoanEmpDtls query :: " + lStrBuffer);
		Query sqlQuery = hibSession.createSQLQuery(lStrBuffer.toString());
		sqlQuery.setParameter("mInstRec", hrLoanEmpDtls.getMulLoanInstRecvd());
		sqlQuery.setParameter("mAmtRecd", hrLoanEmpDtls.getMulLoanAmtRecvd());
		sqlQuery.setParameter("updatedDate", new Date());
		sqlQuery.setParameter("recType", hrLoanEmpDtls.getMulLoanRecoveryMode());
		sqlQuery.setParameter("recRemarks", hrLoanEmpDtls.getMulLoanRecRemarks());
		sqlQuery.setParameter("empLoanId", hrLoanEmpDtls.getEmpLoanId());
		int count = sqlQuery.executeUpdate();
		return count;
	}

	public Map<Long, Object> getBasicDetail(String empId, int month, int year)
	{
		List dataList = null;
		Session hibSession = getSession();
		StringBuffer lStrBuffer = new StringBuffer();
	//	lStrBuffer.append("SELECT bank.BANK_ACCT_NO, ");//0  comment by lekhchand for local 
		lStrBuffer.append("SELECT mst.BANK_ACNT_NO, ");//0
		lStrBuffer.append("emp.EMP_FNAME, ");//1
		lStrBuffer.append("emp.EMP_MNAME, ");//2
		lStrBuffer.append("emp.EMP_LNAME, ");//3
		lStrBuffer.append("eis.SEVARTH_EMP_CD, ");//4
		lStrBuffer.append("dsgnMst.DSGN_NAME, ");//5
		lStrBuffer.append("gpf.PF_SERIES, ");//6
		lStrBuffer.append("gpf.GPF_ACC_NO, ");//7
		lStrBuffer.append("scale.SCALE_DESC, ");//8
		lStrBuffer.append("paybill.PO, ");//9
		lStrBuffer.append("scale.SCALE_GRADE_PAY, ");//10
		lStrBuffer.append("eis.EMP_ID ");//11
		lStrBuffer.append("FROM ");
		lStrBuffer.append("HR_EIS_BANK_DTLS bank, ORG_EMP_MST emp, HR_EIS_EMP_MST eis, HR_PAY_PAYSLIP payslip, ORG_POST_DETAILS_RLT postrlt, ");
		lStrBuffer.append("ORG_DESIGNATION_MST dsgnMst, HR_PAY_PAYBILL paybill, HR_PAY_GPF_DETAILS gpf, ");
		lStrBuffer.append(" HR_EIS_SCALE_MST scale ,mst_dcps_emp mst ");
		lStrBuffer.append("where bank.BANK_EMP_ID = eis.EMP_ID ");
		lStrBuffer.append("and emp.EMP_ID = eis.EMP_MPG_ID ");
		lStrBuffer.append("and eis.EMP_ID in (" + empId + ") ");
		lStrBuffer.append("and payslip.EMP_ID = eis.EMP_ID ");
		lStrBuffer.append("and emp.EMP_ID = mst.org_emp_mst_id ");
		lStrBuffer.append("and paybill.ID = payslip.PAYBILL_ID ");
		lStrBuffer.append("and postrlt.POST_ID = paybill.POST_ID and dsgnMst.DSGN_ID = postrlt.DSGN_ID and payslip.PAYSLIP_MONTH = " + month + " ");
		lStrBuffer.append("and payslip.PAYSLIP_YEAR = " + year + " and gpf.USER_ID = emp.USER_ID  ");
		lStrBuffer.append("and scale.SCALE_ID = mst.payscale ");
		Query sqlQuery = hibSession.createSQLQuery(lStrBuffer.toString());
		logger.info("==> getBasicDetail query :: " + sqlQuery);
		dataList = sqlQuery.list();
		Map<Long, Object> dataMap = new HashMap<Long, Object>();

		if (!dataList.isEmpty())
		{
			int size = dataList.size();
			String banckAccNo = "";
			String empFname = "";
			String empMname = "";
			String empLname = "";
			String sevarthId = "";
			String empName = "";
			String dsgnName = "";
			String pfSeries = "";
			String gpfAccNo = "";
			String scaleDesc = "";
			long otherCurrBasic = 0;
			long gradePay = 0;
			long empID = 0;
			Object[] data = null;

			for (int i = 0; i < size; i++)
			{
				data = (Object[]) dataList.get(i);
				banckAccNo = data[0] != null ? data[0].toString() : "";
				empFname = data[1] != null ? data[1].toString().concat(" ") : "";
				empMname = data[2] != null ? data[2].toString().concat(" ") : "";
				empLname = data[3] != null ? data[3].toString() : "";
				sevarthId = data[4] != null ? data[4].toString() : "";
				empName = "(" + sevarthId + ") " + empFname + empMname + empLname;
				dsgnName = data[5] != null ? data[5].toString() : "";
				pfSeries = data[6] != null ? data[6].toString() : "";
				gpfAccNo = data[7] != null ? data[7].toString() : "";
				scaleDesc = data[8] != null ? data[8].toString() : "";
				otherCurrBasic = data[9] != null ? Long.valueOf(data[9].toString()) : 0;
				gradePay = data[10] != null ? Long.valueOf(data[10].toString()) : 0;
				empID = data[11] != null ? Long.valueOf(data[11].toString()) : 0;
				Object[] dataPut = { banckAccNo, empName, dsgnName, pfSeries, gpfAccNo, scaleDesc, otherCurrBasic, gradePay };
				dataMap.put(empID, dataPut);
				banckAccNo = "";
				empFname = "";
				empMname = "";
				empLname = "";
				sevarthId = "";
				empName = "";
				dsgnName = "";
				pfSeries = "";
				gpfAccNo = "";
				scaleDesc = "";
				otherCurrBasic = 0;
				gradePay = 0;
				empID = 0;
			}
		}

		return dataMap;
	}

	public int updateHrPaybillTrnLog(Date startDate, Date endDate, long paybillId)
	{
		logger.info("in updateHrPaybillTrnLog...........");
		Session hibSession = getSession();
		StringBuffer lStrBuffer = new StringBuffer();
		logger.info("startDate:::::"+startDate);
		logger.info("endDate:::::"+endDate);
		logger.info("paybillId:::::"+paybillId);
		lStrBuffer.append(" update HR_PAYBILL_TRN_LOG set APPROVAL_START_TIME = :startDate,APPROVAL_END_TIME = :endDate where PAYBILL_HEAD_MPG_ID=:paybillId");
		logger.info("==> updateHrPaybillTrnLog query :: " + lStrBuffer);
		Query sqlQuery = hibSession.createSQLQuery(lStrBuffer.toString());
		sqlQuery.setParameter("startDate", startDate);
		sqlQuery.setParameter("endDate", endDate);
		sqlQuery.setParameter("paybillId", paybillId);
		return sqlQuery.executeUpdate();
	}

}
