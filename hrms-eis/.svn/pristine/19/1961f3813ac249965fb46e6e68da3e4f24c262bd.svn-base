package com.tcs.sgv.eis.dao;

import java.math.BigInteger;
import java.sql.Connection;
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

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.service.AliasToEntityMapResult;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGISDtls;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayChangedRecords;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;
import com.tcs.sgv.eis.valueobject.PaybillEmpCompMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

/**
 * @author 602715
 * 
 */
@SuppressWarnings("unchecked")
public class PayBillDAOImpl extends GenericDaoHibernateImpl<HrPayPaybill, Long> implements PayBillDAO {

	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");

	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");

	long parentlocationId = Long.parseLong(payrollBundle.getString("PARENTID"));

	String strParentlocationId = payrollBundle.getString("PARENTID");

	String parentLocationCode = payrollBundle.getString("PARENTCODE");

	String parentlocationIdStr = payrollBundle.getString("PARENTID");

	long paybillTypeId = Long.parseLong(payrollBundle.getString("paybillTypeId"));

	long arrearbillTypeId = Long.parseLong(payrollBundle.getString("arrearbillTypeId"));

	// added by ravysh
	long supplBill_type_First = Long.parseLong(payrollBundle.getString("supplPaybillFirst"));// Supplementary
	// Paybill-1
	// type
	// id
	// is
	// 2500339

	long supplBill_type_Second = Long.parseLong(payrollBundle.getString("supplPaybillSecond"));// Supplementary
	// Paybill-2
	// type
	// id
	// is
	// 2500340

	long supplBill_type_Third = Long.parseLong(payrollBundle.getString("supplPaybillThird"));// Supplementary
	// Paybill-3
	// type
	// id
	// is
	// 2500341

	// public final int FIN_YEAR_ID =
	// Integer.parseInt(constantsBundle.getString("FIN_YEAR_ID")); // Financial
	// Year for Budget Heads.

	public final int createFlag = Integer.parseInt(payrollBundle.getString("created"));

	public final int approveFlag = Integer.parseInt(payrollBundle.getString("approved"));

	// Added by Paurav for getting Logger for Financial Year Function
	private static Log logger = LogFactory.getLog(PayBillDAOImpl.class);

	// Ended by Paurav
	public PayBillDAOImpl(Class<HrPayPaybill> type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	// added by Ankit bhatt on 3rd January

	public List getEligibleEmployeesBillWise(long locId, long billNo, Date givenDate) {
		// List trnBillRegList = null;

		logger.info("getEligibleEmployeesBillWise");
		logger.info("The value of locId" + locId);
		logger.info("The value of billNo" + billNo);
		logger.info("The value of givenDate" + givenDate);
		Session hiSession = getSession();

		logger.info("payrollBundle.getString(getEligibleEmpForPaybill1) "
				+ payrollBundle.getString("getEligibleEmpForPaybill1"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill2) "
				+ payrollBundle.getString("getEligibleEmpForPaybill2"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill3) "
				+ payrollBundle.getString("getEligibleEmpForPaybill3"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill4) "
				+ payrollBundle.getString("getEligibleEmpForPaybill4"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill5) "
				+ payrollBundle.getString("getEligibleEmpForPaybill5"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill6) "
				+ payrollBundle.getString("getEligibleEmpForPaybill6"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill6) "
				+ payrollBundle.getString("getEligibleEmpForPaybill7"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill6) "
				+ payrollBundle.getString("getEligibleEmpForPaybill8"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill6) "
				+ payrollBundle.getString("getEligibleEmpForPaybill9"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill6) "
				+ payrollBundle.getString("getEligibleEmpForPaybill10"));
		StringBuffer strQuery = new StringBuffer(payrollBundle.getString("getEligibleEmpForPaybill1"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill2"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill3"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill4"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill5"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill6"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill7"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill8"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill9"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill10"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill11"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill12"));

		logger.info("Query is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);

		logger.info("Query for getting non vacant post for bill no " + billNo + " is ");
		logger.info(" " + query.toString());

		return query.list();
	}

	public List getVacantPostsBillWise(long locId, long billNo, Date givenDate) {
		// List trnBillRegList = null;
		Session hiSession = getSession();

		logger.info(
				"payrollBundle.getString(getEligibleVacantPost1) " + payrollBundle.getString("getEligibleVacantPost1"));
		logger.info(
				"payrollBundle.getString(getEligibleVacantPost2) " + payrollBundle.getString("getEligibleVacantPost2"));
		logger.info(
				"payrollBundle.getString(getEligibleVacantPost3) " + payrollBundle.getString("getEligibleVacantPost3"));
		logger.info(
				"payrollBundle.getString(getEligibleVacantPost4) " + payrollBundle.getString("getEligibleVacantPost4"));
		logger.info(
				"payrollBundle.getString(getEligibleVacantPost5) " + payrollBundle.getString("getEligibleVacantPost5"));

		StringBuffer strQuery = new StringBuffer(payrollBundle.getString("getEligibleVacantPost1"));
		strQuery.append(payrollBundle.getString("getEligibleVacantPost2"));
		strQuery.append(payrollBundle.getString("getEligibleVacantPost3"));
		strQuery.append(payrollBundle.getString("getEligibleVacantPost4"));
		strQuery.append(payrollBundle.getString("getEligibleVacantPost5"));

		logger.info("Query is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);

		logger.info("Query for getting vacant post for bill no " + billNo + " is ");
		logger.info(" " + query.toString());

		return query.list();
	}

	/*
	 * public List getEmpLoanData(String empIdStr, long locId) { Session hiSession =
	 * getSession();
	 * 
	 * logger.info("payrollBundle.getString(getEmpLoanDetail1) " +
	 * payrollBundle.getString("getEmpLoanDetail1"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail2) " +
	 * payrollBundle.getString("getEmpLoanDetail2"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail3) " +
	 * payrollBundle.getString("getEmpLoanDetail3"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail3.3) " +
	 * payrollBundle.getString("getEmpLoanDetail3.3"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail4) " +
	 * payrollBundle.getString("getEmpLoanDetail4"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail5) " +
	 * payrollBundle.getString("getEmpLoanDetail5"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail6) " +
	 * payrollBundle.getString("getEmpLoanDetail6"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail7) " +
	 * payrollBundle.getString("getEmpLoanDetail7"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail8) " +
	 * payrollBundle.getString("getEmpLoanDetail8"));
	 * logger.info("payrollBundle.getString(getEmpLoanDetail9) " +
	 * payrollBundle.getString("getEmpLoanDetail9")); logger.info("EmpIDStr is " +
	 * empIdStr); StringBuffer strQuery = new
	 * StringBuffer(payrollBundle.getString("getEmpLoanDetail1"));
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail2"));
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail3"));
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail3.3")).append(" ");
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail4"));
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail5"));
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail6"));
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail7"));
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail8"));
	 * strQuery.append(payrollBundle.getString("getEmpLoanDetail9"));
	 * strQuery.append(" loan.emp_id in ( "); strQuery.append(empIdStr);
	 * strQuery.append(")"); strQuery.append(" order by loan.emp_id");
	 * 
	 * logger.info("Query is " + strQuery); Query query =
	 * hiSession.createSQLQuery(strQuery.toString()); //query.setParameter("locId",
	 * locId); query.setParameter("lookup1", 2500137); query.setParameter("lookup2",
	 * 2500136); query.setParameter("locId", locId);
	 * logger.info("query.getQueryString() " + query.getQueryString());
	 * 
	 * logger.info("Query for getting emp loan details  is "); logger.info(" " +
	 * query.toString());
	 * 
	 * return query.list();
	 * 
	 * }
	 */
	public List getEmpLoanData(String empIdStr, long locId, long billNo, Date givenDate) {
		Session hiSession = getSession();

		logger.info("payrollBundle.getString(getEmpLoanDetail1) " + payrollBundle.getString("getEmpLoanDetail1"));
		logger.info("payrollBundle.getString(getEmpLoanDetail2) " + payrollBundle.getString("getEmpLoanDetail2"));
		logger.info("payrollBundle.getString(getEmpLoanDetail3) " + payrollBundle.getString("getEmpLoanDetail3"));
		logger.info("payrollBundle.getString(getEmpLoanDetail3.3) " + payrollBundle.getString("getEmpLoanDetail3.3"));
		logger.info("payrollBundle.getString(getEmpLoanDetail4) " + payrollBundle.getString("getEmpLoanDetail4"));
		logger.info("payrollBundle.getString(getEmpLoanDetail5) " + payrollBundle.getString("getEmpLoanDetail5"));
		logger.info("payrollBundle.getString(getEmpLoanDetail6) " + payrollBundle.getString("getEmpLoanDetail6"));
		logger.info("payrollBundle.getString(getEmpLoanDetail7) " + payrollBundle.getString("getEmpLoanDetail7"));
		logger.info("payrollBundle.getString(getEmpLoanDetail8) " + payrollBundle.getString("getEmpLoanDetail8"));
		logger.info("payrollBundle.getString(getEmpLoanDetail9) " + payrollBundle.getString("getEmpLoanDetail9"));
		logger.info("EmpIDStr is " + empIdStr);
		StringBuffer strQuery = new StringBuffer(payrollBundle.getString("getEmpLoanDetail1"));
		strQuery.append(payrollBundle.getString("getEmpLoanDetail2"));
		strQuery.append(payrollBundle.getString("getEmpLoanDetail3"));
		strQuery.append(payrollBundle.getString("getEmpLoanDetail3.3")).append(" ");
		strQuery.append(payrollBundle.getString("getEmpLoanDetail4"));
		strQuery.append(payrollBundle.getString("getEmployeeList"));
		strQuery.append(payrollBundle.getString("getEmpLoanDetail5"));
		strQuery.append(payrollBundle.getString("getEmpLoanDetail6"));
		strQuery.append(payrollBundle.getString("getEmpLoanDetail7"));
		strQuery.append(payrollBundle.getString("getEmpLoanDetail8"));
		strQuery.append(payrollBundle.getString("getEmpLoanDetail9"));

		// strQuery.append(" loan.emp_id in ( ");
		// strQuery.append(empIdStr);
		// strQuery.append(")");
		strQuery.append(" order by loan.emp_id");

		logger.info("Query is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		// query.setParameter("locId", locId);
		query.setParameter("lookup1", 2500137);
		query.setParameter("lookup2", 2500136);
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);
		logger.info("query.getQueryString() " + query.getQueryString());

		logger.info("Query for getting emp loan details  is ");
		logger.info(" " + query.toString());

		return query.list();

	}

	@SuppressWarnings("deprecation")
	public List getEmpQtrData(String empIdStr, long locId, long billNo, Date givenDate) {
		Session hiSession = getSession();

		logger.info("EmpIDStr is " + empIdStr);
		StringBuffer strQuery = new StringBuffer(payrollBundle.getString("getEmpQtrDtls1"));
		strQuery.append(" ");
		strQuery.append(payrollBundle.getString("getEmployeeList").toString());
		strQuery.append(" ");
		strQuery.append(payrollBundle.getString("getEmpQtrDtls2"));
		strQuery.append(payrollBundle.getString("getEmpQtrDtls3"));

		logger.info("Query is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);
		query.setParameter("month", (givenDate.getMonth() + 1));
		return query.list();
	}

	public List getGroupIdFromEmpId(String empIds, long locId, long billNo, Date givenDate) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"SELECT grade.GRADE_ID, grade.GRADE_CODE,gis.EMP_ID FROM HR_EIS_GIS_DTLS gis inner join ");
		strQuery.append(payrollBundle.getString("getEmployeeList").toString());
		strQuery.append(
				" on gis.emp_id = emp_list.emp_id, org_grade_mst grade where gis.GIS_GROUP_GRADE_ID = grade.GRADE_ID ");
		// strQuery.append(" order by gis.EMP_ID");
		logger.info("Query in getGradenameFromGradeId is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);
		return query.list();
	}

	public List getBrokenPeriodData(String empIds, int month, long year, long locId, long billNo, Date givenDate) {
		Session hibSession = getSession();
		// List arr= new ArrayList();
		StringBuffer strQuery = new StringBuffer(
				"select broken.eis_emp_id,BROKEN_PERIOD_ID,BASIC_PAY from MST_DCPS_BROKEN_PERIOD_PAY broken inner join ");
		strQuery.append(payrollBundle.getString("getEmployeeList").toString());
		strQuery.append(" on emp_list.emp_id = broken.eis_emp_id where broken.month_id= ");
		strQuery.append(month);
		strQuery.append(" and broken.year_id=" + year);
		strQuery.append(" order by broken.eis_emp_id");
		logger.info("Query in getBrokenPeriodData is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);
		return query.list();

	}

	// ended

	// added By hemant for arrear gp difference report
	public List getGPDiffArrearBillList(long billNo, List monthYearList, long langId, long locId, String billType,
			int arrearMonth, int arrearYear) {
		List trnBillRegList = null;
		Session hiSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(
				" select  Max(hreisempms10_.emp_id) as empId, max(hrpaypaybi0_.psr_no) psr,       concat (concat ( concat(orgempmst11_.emp_prefix, concat(' ', orgempmst11_.emp_fname)),concat(' ' , orgempmst11_.emp_mname)),concat( ' ' , orgempmst11_.emp_lname)) empName, \n"
						+ " case         when max(dsgn.dsgn_shrt_name) is null then          orgdesigna9_.dsgn_shrt_name         else          max(dsgn.dsgn_shrt_name) || '(' || orggradems14_.Grade_Name || ')'       end dsgn_name ,              max( hrpaypaybi0_.pe + hrpaypaybi0_.po-hrpaypaybi0_.gpay) BasicPaid ,\n"
						+ " max(hrpaypaybi0_.gpay) as GPPaid,       max(hrpaypaybi0_.DA) as DAPaid,       max(hrpaypaybi0_.HRA) as HRAPaid,       bhm.paybill_year,       bhm.paybill_month,       max((select b.other_current_basic          from hr_eis_other_dtls b    where b.other_id  in (select a.other_id  from hr_pay_paybill a where a.id in (hrpaypaybi0_.id)))) BasicPayable ,  Max((select  d.scale_grade_pay from hr_eis_scale_mst d where d.scale_id in (select C.SGD_SCALE_ID from hr_eis_sgd_mpg c where c.loc_id = "
						+ locId
						+ " and  c.sgd_map_id IN (select b.emp_sgd_id  from hr_eis_other_dtls b  where b.other_id  in (select a.other_id  from hr_pay_paybill a where a.id in (hrpaypaybi0_.id )))))) GP,  \n"
						+ " Max(hrpaypaybi0_.net_total), orgempmst11_.user_id UserId ,  hreisempms10_.emp_type  , Max(hreisother1_.isavailedhra) , max(hreisother1_.city), max(orgempmst11_.Emp_Gpf_Num) gpfNumber, max( (SELECT  ep.proof_num FROM HR_EIS_PROOF_DTL EP WHERE EP.USER_ID =orgempmst11_.user_id AND orgpostdet8_.loc_id = "
						+ locId
						+ "  )) Pan_Number , max(trb.token_num), max(trb.bill_date), max(trb.bill_net_amount) \n" +

						" from HR_PAY_PAYBILL hrpaypaybi0_ left outer join hr_eis_emp_mst hreisempms10_ on hrpaypaybi0_.EMP_ID = hreisempms10_.EMP_ID \n"
						+

						" left outer join org_emp_mst orgempmst11_ on hreisempms10_.emp_mpg_ID = orgempmst11_.EMP_ID and orgempmst11_.lang_id = "
						+ langId + " \n"
						+ " left outer join org_grade_mst orggradems14_ on orgempmst11_.grade_id = orggradems14_.Grade_Id and orggradems14_.lang_id = "
						+ langId + " \n"
						+ " left outer join HR_EIS_OTHER_DTLS_HST hreisother1_ on hrpaypaybi0_.EMP_ID = hreisother1_.EMP_ID and hreisother1_.trn_counter = hrpaypaybi0_.other_trn_cntr\n"
						+ " left outer join HR_EIS_SGD_MPG hreissgdmp2_ on hreisother1_.EMP_SGD_ID = hreissgdmp2_.SGD_MAP_ID\n"
						+ " left outer join HR_EIS_GD_MPG hreisgdmpg3_ on hreissgdmp2_.SGD_GD_ID = hreisgdmpg3_.GD_MAP_ID\n"
						+ " left outer join hr_eis_scale_mst hreisscale4_ on hreissgdmp2_.SGD_SCALE_ID = hreisscale4_.scale_id and hreisscale4_.lang_id = "
						+ langId + " \n"
						+ " left outer join HR_PAY_ORDER_HEAD_POST_MPG hrpayorder7_ on hrpaypaybi0_.post_id = hrpayorder7_.post_id\n"
						+ " left outer join HR_PAY_ORDER_HEAD_MPG hrpayorder6_ on hrpayorder7_.ORDER_HEAD_ID = hrpayorder6_.ORDER_HEAD_ID\n"
						+ " left outer join org_post_mst orgpostmst10_ on hrpaypaybi0_.post_id = orgpostmst10_.post_id\n"
						+ " left outer join org_post_details_rlt orgpostdet8_ on orgpostmst10_.post_id = orgpostdet8_.post_id and orgpostdet8_.lang_id = "
						+ langId + " \n"
						+ " left outer join org_designation_mst orgdesigna9_ on orgpostdet8_.dsgn_id = orgdesigna9_.dsgn_id and orgdesigna9_.lang_id = "
						+ langId + " \n"
						+ " left outer join org_designation_mst dsgn on hreisgdmpg3_.sgd_desig_id = dsgn.dsgn_id and dsgn.lang_id = "
						+ langId + ", \n"
						+ " paybill_head_mpg bhm,hr_pay_bill_subhead_mpg hpbsm ,  trn_bill_register trb,paybill_billreg_mpg prm  \n"
						+

						" where orgpostdet8_.loc_id = " + locId + " and\n"
						+ " orgpostdet8_.loc_id = hrpaypaybi0_.loc_id and\n"
						+ " hrpaypaybi0_.paybill_grp_id = bhm.paybill_id\n" + " and bhm.approve_flag in (" + createFlag
						+ ", " + approveFlag + ") and bhm.bill_Type_Id = " + billType + " and ");

		for (int i = 0; i < monthYearList.size(); i = i + 2) {
			if (i == 0)
				strQuery.append(" ( ");
			strQuery.append(" bhm.paybill_month =  " + monthYearList.get(i) + " and bhm.paybill_year = "
					+ monthYearList.get(i + 1));
			if (i == (monthYearList.size() - 2))
				strQuery.append(" ) and ");
			else
				strQuery.append(" OR ");
		}

		strQuery.append(" bhm.bill_no = hpbsm.bill_subhead_id and hpbsm.bill_no = " + billNo + " and \n"
				+ " orgpostdet8_.loc_id = " + locId
				+ " and hreisempms10_.emp_id > 0 and prm.trn_bill_id=trb.bill_no and prm.paybill_id = bhm.paybill_id  and \n"
				+ "  hrpaypaybi0_.emp_id in ( select a.emp_id  from hr_pay_arrear_paybill a where  a.paybill_grp_id in (select b.paybill_id  from paybill_head_mpg b where b.paybill_month = "
				+ arrearMonth + " and b.paybill_year = " + arrearYear + " and b.approve_flag in (" + createFlag + ", "
				+ approveFlag + "))) \n"
				+ " group by hreisother1_.EMP_ID,          hreisempms10_.EMP_TYPE,          orgempmst11_.grade_id,          orgdesigna9_.dsgn_shrt_name,          orggradems14_.Grade_Name,          orgempmst11_.emp_fname,          orgempmst11_.emp_mname,          orgempmst11_.emp_lname,\n"
				+ " hreisscale4_.scale_start_amt,          hreisscale4_.scale_higher_incr_amt,          hreisscale4_.scale_higher_end_amt,          hreisscale4_.scale_incr_amt,          hreisscale4_.scale_end_amt,          hreisother1_.PHY_CHALLENGED,          hreisother1_.OTHER_CURRENT_BASIC,\n"
				+ " orgempmst11_.emp_prefix,          orgempmst11_.user_id,          orgdesigna9_.dsgn_id,          orgpostdet8_.post_id,          orgempmst11_.user_id,          orgpostmst10_.post_type_lookup_id,          hrpayorder6_.ORDER_ID,          orgpostmst10_.end_date,          scale_grade_pay,\n"
				+ " hrpaypaybi0_.post_id,          bhm.paybill_year,          bhm.paybill_month \n"
				+ " order by  psr, empId, bhm.paybill_year,bhm.paybill_month ");

		logger.info("Query For TrnBillRegester" + strQuery.toString());
		Query query = hiSession.createSQLQuery(strQuery.toString());
		trnBillRegList = query.list();

		return trnBillRegList;
	}

	public List hrPaySalRevMstArrearData(long trnBill, long locId) {
		List trnBillRegList = null;
		Session hiSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(
				"select sal from HrPaySalRevMst sal where sal.salRevId in ( select ap.salRevId  from HrPayArrearPaybill ap where ap.paybillGrpId  in ( \n"
						+ "select br.hrPayPaybill  from PaybillBillregMpg br where br.trnBillRegister.billNo  in ("
						+ trnBill + ") and br.trnBillRegister.locationCode = '" + locId + "' )))");
		logger.info("Query For TrnBillRegester : " + strQuery.toString());
		Query query = hiSession.createQuery(strQuery.toString());
		logger.info("::::::::::::::::::::::::::::::::::::::::::::::::geArrearBillOtherDataList query in DAOIMPL : "
				+ query);
		trnBillRegList = query.list();
		return trnBillRegList;
	}

	public List getTrnBillRegister(long billNo) {
		List trnBillRegList = null;
		Session hiSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("  from TrnBillRegister f where f.billNo=" + billNo);

		logger.info("Query For TrnBillRegester" + strQuery.toString());
		Query query = hiSession.createQuery(strQuery.toString());
		trnBillRegList = query.list();

		return trnBillRegList;
	}

	public long getBillNumberFromTRNBillNumber(long trnBillNo, long locId, long billTypeId) {
		List trnBillRegList = null;
		long billNumber = 0;
		Session hiSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select bs.bill_no from hr_pay_bill_subhead_mpg bs where bs.bill_subhead_id in (\n"
				+ "select distinct(ph.bill_no) from paybill_head_mpg ph where ph.loc_id = " + locId
				+ " and ph.approve_flag in (" + createFlag + ", " + approveFlag + ") and ph.bill_type_id = "
				+ billTypeId + " and ph.paybill_id in (\n"
				+ "select br.paybill_id  from paybill_billreg_mpg br where br.trn_bill_id  in (\n"
				+ "select t.bill_no  from trn_bill_register t where t.bill_no = " + trnBillNo
				+ " and t.location_code = '" + locId + "' )))");

		logger.info("Query For TrnBillRegester : " + strQuery.toString());
		Query query = hiSession.createSQLQuery(strQuery.toString());
		logger.info(":::::::::::::::::::::::::::::::::::::::::::::::: query in DAOIMPL : " + query);
		trnBillRegList = query.list();

		logger.info(":::::::::::::::::::::::::::::::::::::::::::::::: trnBillRegList in DAOIMPL : " + trnBillRegList);

		billNumber = Long.parseLong(trnBillRegList.get(0).toString());
		return billNumber;
	}

	public List getPaybillListForLeave(long empId, long month, long year, long approveflag) {
		List payBillList = null;
		Session hibSession = getSession();

		String created = payrollBundle.getString("created");
		String approved = payrollBundle.getString("approved");

		/*
		 * Criteria crit = hibSession.createCriteria(PaybillHeadMpg.class);
		 * 
		 * crit.add(Restrictions.eq("month", month)); crit.add(Restrictions.eq("year",
		 * year)); crit.add(Restrictions.eq("approveFlag", approveflag));
		 * 
		 * crit.createAlias("hrPayPaybill", "hrPaybill");
		 * crit.createAlias("hrPaybill.hrEisEmpMst", "hrEisEmp");
		 * 
		 * crit.add(Restrictions.eq("hrEisEmp.empId", empId));
		 */

		String strQuery = "from HrPayPaybill pb where pb.paybillGrpId in(select  distinct(phm.hrPayPaybill) from PaybillHeadMpg phm where phm.month="
				+ month + " and phm.year=" + year + " and phm.approveFlag in (" + approved + "," + created
				+ ")) and pb.hrEisEmpMst.empId=" + empId;
		Query query = hibSession.createQuery(strQuery);
		payBillList = query.list();

		return payBillList;
	}

	public List getAllData() {
		List payBillList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayPaybill";
		Query query = hibSession.createQuery(strQuery);
		payBillList = query.list();

		return payBillList;
	}

	public List getAllEmpData() // function added by Ankit Bhatt, used in
	// GeneratePayslipService.
	{
		List payBillEmpList = null;
		Session hibSession = getSession();
		String strQuery = "from HrEisEmpMst as emp where emp.empId in "
				+ "(select distinct(payBill.hrEisEmpMst.empId) from HrPayPaybill as payBill)"
				+ " order by emp.orgEmpMst.empFname";
		;
		Query query = hibSession.createQuery(strQuery);
		payBillEmpList = query.list();

		return payBillEmpList;
	}

	public List findEmpNamePayslip(String empName, long langId) // find function
	// in Payslip
	// Parameter
	// Page
	{

		List empNames = new ArrayList();
		Session hibSession = getSession();
		String query1 = "from HrEisEmpMst as empLookup where (lower(empLookup.orgEmpMst.empFname) like" + " lower('"
				+ empName + "%') or lower(empLookup.orgEmpMst.empMname) like" + " lower('" + empName
				+ "%') or lower(empLookup.orgEmpMst.empLname) like" + " lower('" + empName
				+ "%')) and empLookup.empId in "
				+ "(select distinct(payBill.hrEisEmpMst.empId) from HrPayPaybill as payBill) "
				+ "order by empLookup.orgEmpMst.empFname";

		Query sqlQuery1 = hibSession.createQuery(query1);
		empNames = sqlQuery1.list();
		// //logger.info("Find from Payslip Para " + empNames.size());
		return empNames;
	}

	public List getPayslipData(int month, int year) {
		List payslipList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayPaybill as payBill where payBill.month=" + month + " and payBill.year=" + year;
		Query query = hibSession.createQuery(strQuery);
		payslipList = query.list();

		return payslipList;
	}

	// function used for 10-20 days issues for getting the sum of all value from
	// paybill table

	/*
	 * This method is not use in Payroll Application
	 * 
	 * public List getPayslipDataList(int month, int year, String category, String
	 * Grade, String dsgnId, String subHeadId) {
	 * 
	 * long AISGradeCode = Long.parseLong(constantsBundle
	 * .getString("AISGradeCode")); long GradeCode3 = Long.parseLong(constantsBundle
	 * .getString("GradeCode3")); long GradeCode4 = Long.parseLong(constantsBundle
	 * .getString("GradeCode4")); long GradeCode1 = Long.parseLong(constantsBundle
	 * .getString("GradeCode1")); long GradeCode2 = Long.parseLong(constantsBundle
	 * .getString("GradeCode2"));
	 * 
	 * if (category.equals("AIS")) Grade = AISGradeCode + ""; else if
	 * (category.equals("Gadgeted")) Grade = GradeCode1 + "','" + GradeCode2; else
	 * if (category.equals("Non-Gadgeted")) Grade = GradeCode3 + "','" + GradeCode4;
	 * 
	 * List paySlipList = null; Session hibSession = getSession(); String strQuery =
	 * "select max(bill.id),bill.hrEisEmpMst.orgEmpMst.empId,bill.paybill_month,bill.paybill_year,"
	 * +
	 * "sum(bill.spl_pay),sum(bill.po),sum(bill.ls),sum(bill.d_pay),sum(bill.da),sum(bill.hra),sum(bill.cla),"
	 * +
	 * "sum(bill.ma),sum(bill.wa),sum(bill.trans_all),sum(bill.fes_adv),sum(bill.food_adv),sum(bill.pay_recover),"
	 * +
	 * "sum(bill.gross_amt),sum(bill.slo),sum(bill.it),sum(bill.hrr),sum(bill.pli),sum(bill.pt),sum(bill.sis_gis_1979),"
	 * +
	 * "sum(bill.sis_if_1981),sum(bill.sis_sf_1981),sum(bill.ais_if_1980),sum(bill.ais_sf_1980),sum(bill.gpf_c),"
	 * +
	 * "sum(bill.car_sct_moped_adv),sum(bill.oca_cycle_adv),sum(bill.hba),sum(bill.fan_adv),sum(bill.jeep_r),"
	 * +
	 * "sum(bill.gpf_iv),sum(bill.total_ded),sum(bill.net_total),sum(bill.pe),sum(bill.le),sum(bill.ltc),"
	 * +
	 * "sum(bill.other_allow),sum(bill.bonus),sum(bill.other_chrgs),sum(bill.surcharge),sum(bill.rent_b),sum(bill.bli),"
	 * +
	 * "sum(bill.gpf_adv),sum(bill.misc_recov),sum(bill.per_pay),sum(bill.dp_gazzeted),sum(bill.car_sct_moped_int),"
	 * + "sum(bill.oca_cycle_int),sum(bill.hba_int),sum(bill.fan_int)" +
	 * " from HrPayPaybill bill, "
	 * 
	 * + "  HR_EIS_OTHER_DTLS oth,  " + "  HR_EIS_SGD_MPG    sgd, " +
	 * "  HR_EIS_GD_MPG     gd, " + "  hr_eis_emp_mst             emp, " +
	 * "  org_emp_mst                orgemp, " + "  HR_PAY_ORDER_HEAD_MPG      oh, "
	 * + "  HR_PAY_ORDER_HEAD_POST_MPG ohp, " + "  org_userpost_rlt           up "
	 * 
	 * + "  where bill.paybill_month = " + month + " and bill.paybill_year = " +
	 * year + " and oth.emp_id = bill.emp_id and " +
	 * "  oth.emp_sgd_id = sgd.sgd_map_id and sgd.sgd_gd_id = gd.gd_map_id and " ;
	 * 
	 * if (Grade != null && !Grade.equals("") && !Grade.equals("-1")) strQuery +=
	 * "  gd.sgd_grade_id in  ('" + Grade + "') and "; //
	 * if(dsgnId!=null&&!dsgnId.equals("")&&!dsgnId.equals("-1")) //
	 * strQuery+="  gd.sgd_desig_id = "+dsgnId+" and ";
	 * 
	 * // Added by Paurav for considering desitnations while generation payslip if
	 * (dsgnId != null && !dsgnId.equals("")) { strQuery += " gd.sgd_desig_id in ("
	 * + dsgnId + ") and "; } // Ended by Paurav if (subHeadId != null &&
	 * !subHeadId.equals("") && !subHeadId.equals("-1")) strQuery +=
	 * "  oh.SUBHEAD_ID = " + subHeadId + " and "; strQuery +=
	 * "  orgemp.user_id = up.user_id   and ohp.ORDER_HEAD_ID = oh.ORDER_HEAD_ID and "
	 * +
	 * "  up.post_id = ohp.POST_ID and oth.emp_id = emp.emp_id and up.activate_flag=1 and orgemp.emp_id = emp.emp_mpg_id"
	 * 
	 * + " group by bill.emp_id,bill.paybill_month,bill.paybill_year"; //
	 * +" having bill.paybill_month="+month+" and bill.paybill_year="+year;
	 * 
	 * ////logger.info("the sql query is " + strQuery); Query query =
	 * hibSession.createSQLQuery(strQuery); paySlipList = query.list();
	 * 
	 * return paySlipList; }
	 */
	/*
	 * public List<HrPayPaybill> getPayslipDataForForm16(int year,long EmpId) { List
	 * payslipList = null;
	 * 
	 * Session hibSession = getSession(); ResourceBundle constantsBundle1 =
	 * ResourceBundle.getBundle("resources.Payroll");
	 * logger.info("thje resource bundle is "+constantsBundle1);
	 * 
	 * String created = constantsBundle1.getString("created"); String approved =
	 * constantsBundle1.getString("approved");
	 * 
	 * //String strQuery =
	 * "from HrPayPaybill as payBill where payBill.hrEisEmpMst.empId=" +EmpId+
	 * " and payBill.createdDate>='" + strStartDate + "' and payBill.createdDate<='"
	 * + strEndDate+"' order by payBill.createdDate"; //String strQuery =
	 * "from HrPayPaybill as payBill where payBill.hrEisEmpMst.empId=" +EmpId+
	 * " and (payBill.month>='4' and payBill.year='" + year +
	 * "') or (payBill.month<='3' and payBill.year='" + (year+1) +
	 * "') order by year,month";
	 * 
	 * String strQuery =
	 * "select max(bill.id),bill.emp_id,bill.paybill_month,bill.paybill_year,"+
	 * "sum(bill.spl_pay),sum(bill.po),sum(bill.ls),sum(bill.d_pay),sum(bill.da),sum(bill.hra),sum(bill.cla),"
	 * +
	 * "sum(bill.ma),sum(bill.wa),sum(bill.trans_all),sum(bill.fes_adv),sum(bill.food_adv),sum(bill.pay_recover),"
	 * +
	 * "sum(bill.gross_amt),sum(bill.slo),sum(bill.it),sum(bill.hrr),sum(bill.pli),sum(bill.pt),sum(bill.sis_gis_1979),"
	 * +
	 * "sum(bill.sis_if_1981),sum(bill.sis_sf_1981),sum(bill.ais_if_1980),sum(bill.ais_sf_1980),sum(bill.gpf_c),"
	 * +
	 * "sum(bill.car_sct_moped_adv),sum(bill.oca_cycle_adv),sum(bill.hba),sum(bill.fan_adv),sum(bill.jeep_r),"
	 * +
	 * "sum(bill.gpf_iv),sum(bill.total_ded),sum(bill.net_total),sum(bill.pe),sum(bill.le),sum(bill.ltc),"
	 * +
	 * "sum(bill.other_allow),sum(bill.bonus),sum(bill.other_chrgs),sum(bill.surcharge),sum(bill.rent_b),sum(bill.bli),"
	 * +
	 * "sum(bill.gpf_adv),sum(bill.misc_recov),sum(bill.per_pay),sum(bill.dp_gazzeted),max(bill.created_date)"
	 * +" from hr_pay_paybill bill where bill.emp_id=" +EmpId+
	 * " and ((bill.paybill_month>='4' and bill.paybill_year='" + year +
	 * "') or (bill.paybill_month<='3' and bill.paybill_year='" + (year+1)+"') )"
	 * +" and bill.approve_flag in ("+approved+","+created+") " +
	 * " group by bill.emp_id,bill.paybill_month,bill.paybill_year order by paybill_year,paybill_month"
	 * ;
	 * 
	 * //logger.info("the sql query is "+strQuery);
	 * 
	 * 
	 * 
	 * Query query = hibSession.createSQLQuery(strQuery); payslipList =
	 * query.list();
	 * 
	 * List paySlipNewList = new ArrayList();
	 * 
	 * for(Iterator it = payslipList.iterator(); it.hasNext();) { HrPayPaybill
	 * payBillVO = new HrPayPaybill();
	 * 
	 * Object[] rowList = (Object[]) it.next(); double x=0; int a = 0;
	 * 
	 * 
	 * 
	 * payBillVO.setId(Long.parseLong((rowList[0]!=null?rowList[0]:a).toString())
	 * );//id
	 * 
	 * HrEisEmpMst hrEis = new HrEisEmpMst(); long empId =
	 * Long.parseLong((rowList[1]!=null?rowList[1]:a).toString());
	 * hrEis.setEmpId(empId); payBillVO.setHrEisEmpMst(hrEis);//emp id //payBillVO
	 * .setMonth(Double.parseDouble((rowList[2]!=null?rowList[2]:x).toString ()));
	 * //month //payBillVO.setYear(Double.parseDouble((rowList[3]!=null?rowList
	 * [3]:x).toString()));//year payBillVO.setAllow0101(Double.parseDouble((rowList
	 * [4]!=null?rowList[4]:x).toString()));//special pay payBillVO.setBasic0101(
	 * Double.parseDouble((rowList[5]!=null?rowList[5]:x).toString()));//pay of
	 * officer payBillVO.setLs(Double.parseDouble((rowList[6]!=null?rowList[6]:x)
	 * .toString()));//leave salary
	 * payBillVO.setAllow0119(Double.parseDouble((rowList
	 * [7]!=null?rowList[7]:x).toString()));//DP
	 * payBillVO.setAllow0103(Double.parseDouble
	 * ((rowList[8]!=null?rowList[8]:x).toString())); //da payBillVO.setAllow0110
	 * (Double.parseDouble((rowList[9]!=null?rowList[9]:x).toString()));//hra
	 * payBillVO .setAllow0111(Double.parseDouble((rowList[10]!=null?rowList[10]:x
	 * ).toString()));//cla payBillVO.setAllow0107(Double.parseDouble((rowList[11
	 * ]!=null?rowList[11]:x).toString()));//ma
	 * payBillVO.setAllow1301(Double.parseDouble
	 * ((rowList[12]!=null?rowList[12]:x).toString()));//wa payBillVO.setAllow0113
	 * (Double.parseDouble((rowList[13]!=null?rowList[13]:x
	 * ).toString()));//Transport allowance payBillVO.setAdv5701(Double.parseDouble
	 * ((rowList[14]!=null?rowList[14]:x).toString()));//fest adv
	 * payBillVO.setAdv5801
	 * (Double.parseDouble((rowList[15]!=null?rowList[15]:x).toString()));//food adv
	 * payBillVO.setDeduc0101(Double.parseDouble((rowList[16]!=null?rowList[16
	 * ]:x).toString()));//pay recovery payBillVO.setGrossAmt(Double.parseDouble(
	 * (rowList[17]!=null?rowList[17]:x).toString()));//gross amount
	 * payBillVO.setSlo
	 * (Double.parseDouble((rowList[18]!=null?rowList[18]:x).toString()));//slo
	 * payBillVO
	 * .setIt(Double.parseDouble((rowList[19]!=null?rowList[19]:x).toString
	 * ()));//it
	 * payBillVO.setDeduc9550(Double.parseDouble((rowList[20]!=null?rowList
	 * [20]:x).toString()));//hrr payBillVO.setDeduc9530(Double.parseDouble((rowList
	 * [21]!=null?rowList[21]:x).toString()));//pli payBillVO.setDeduc9570(Double
	 * .parseDouble((rowList[22]!=null?rowList[22]:x).toString()));//pt payBillVO
	 * .setDeduc9580(Double.parseDouble((rowList[23]!=null?rowList[23]:x
	 * ).toString()));//sis_gis 1979
	 * payBillVO.setDeduc9581(Double.parseDouble((rowList
	 * [24]!=null?rowList[24]:x).toString()));//sis_if_1981 payBillVO.setDeduc9582
	 * (Double.parseDouble((rowList[25]!=null?rowList[25]:x
	 * ).toString()));//sis_sf_1981
	 * payBillVO.setDeduc9583(Double.parseDouble((rowList
	 * [26]!=null?rowList[26]:x).toString()));//ais_if_1980 payBillVO.setDeduc9584
	 * (Double.parseDouble((rowList[27]!=null?rowList[27]:x
	 * ).toString()));//ais_sf_1980
	 * payBillVO.setDeduc9670(Double.parseDouble((rowList
	 * [28]!=null?rowList[28]:x).toString()));//gpf c payBillVO.setLoan9592(Double
	 * .parseDouble((rowList[29]!=null?rowList[29]:x).toString()));//car scooter
	 * moped loan
	 * payBillVO.setLoan9740(Double.parseDouble((rowList[30]!=null?rowList
	 * [30]:x).toString()));//oca cycle loan
	 * payBillVO.setLoan9591(Double.parseDouble
	 * ((rowList[31]!=null?rowList[31]:x).toString()));//hba payBillVO.setLoan9720
	 * (Double.parseDouble((rowList[32]!=null?rowList[32]:x).toString()));//fan
	 * advance payBillVO.setDeduc9780(Double.parseDouble((rowList[33]!=null?rowList
	 * [33]:x).toString()));//jeep rent payBillVO.setDeduc9531((Double.parseDouble
	 * ((rowList[34]!=null?rowList[34]:x).toString())));//gpf iv
	 * payBillVO.setTotalDed ((Double.parseDouble((rowList[35]!=null?rowList[35]:x
	 * ).toString())));//total deduc
	 * payBillVO.setNetTotal((Double.parseDouble((rowList
	 * [36]!=null?rowList[36]:x).toString())));//net total payBillVO.setBasic0102
	 * ((Double.parseDouble((rowList[37]!=null?rowList[37]:x).toString())));//Pe
	 * payBillVO
	 * .setLe((Double.parseDouble((rowList[38]!=null?rowList[38]:x).toString
	 * ())));//le
	 * payBillVO.setAllow0105((Double.parseDouble((rowList[39]!=null?rowList
	 * [39]:x).toString())));//ltc
	 * payBillVO.setAllow0104((Double.parseDouble((rowList
	 * [40]!=null?rowList[40]:x).toString())));//other allow payBillVO.setAllow0108
	 * ((Double.parseDouble((rowList[41]!=null?rowList[41]: x).toString())));//bonus
	 * payBillVO.setAllow5006((Double.parseDouble((rowList
	 * [42]!=null?rowList[42]:x).toString())));//other charhges
	 * payBillVO.setSurcharge ((Double.parseDouble((rowList[43]!=null?rowList[43]:
	 * x).toString())));//surcharge
	 * payBillVO.setDeduc9560((Double.parseDouble((rowList
	 * [44]!=null?rowList[44]:x).toString())));//rent b
	 * payBillVO.setDeduc9540((Double
	 * .parseDouble((rowList[45]!=null?rowList[45]:x).toString())));//bli payBillVO
	 * .setAdv9670((Double.parseDouble((rowList[46]!=null?rowList[46]:x)
	 * .toString())));//gpf advance
	 * payBillVO.setDeduc9910((Double.parseDouble((rowList
	 * [47]!=null?rowList[47]:x).toString())));//misc recovery
	 * payBillVO.setAllow0102 ((Double.parseDouble((rowList[48]!=null?rowList[48]:
	 * x).toString())));//personal pay payBillVO.setAllow0120((Double.parseDouble
	 * ((rowList[49]!=null?rowList[49]:x).toString())));//DP_GAZZETED
	 * payBillVO.setCreatedDate((Date)rowList[50]);//created Date
	 * 
	 * paySlipNewList.add(payBillVO); }
	 * 
	 * return paySlipNewList; }
	 */
	public List<HrPayPaybill> getPayslipDataForForm16(int year, long EmpId) {
		List payslipList = null;

		Session hibSession = getSession();
		ResourceBundle constantsBundle1 = ResourceBundle.getBundle("resources.Payroll");
		logger.info("thje resource bundle is " + constantsBundle1);

		String created = constantsBundle1.getString("created");
		String approved = constantsBundle1.getString("approved");

		// String strQuery =
		// "from HrPayPaybill as payBill where payBill.hrEisEmpMst.empId="
		// +EmpId+ " and payBill.createdDate>='" + strStartDate +
		// "' and payBill.createdDate<='" +
		// strEndDate+"' order by payBill.createdDate";
		// String strQuery =
		// "from HrPayPaybill as payBill where payBill.hrEisEmpMst.empId="
		// +EmpId+ " and (payBill.month>='4' and payBill.year='" + year +
		// "') or (payBill.month<='3' and payBill.year='" + (year+1) +
		// "') order by year,month";

		String strQuery = "select max(bill.id),bill.emp_id,phm.paybill_month,phm.paybill_year,"
				+ "sum(bill.spl_pay),sum(bill.po),sum(bill.ls),sum(bill.d_pay),sum(bill.da),sum(bill.hra),sum(bill.cla),"
				+ "sum(bill.ma),sum(bill.wa),sum(bill.trans_all),sum(bill.fes_adv),sum(bill.food_adv),sum(bill.pay_recover),"
				+ "sum(bill.gross_amt),sum(bill.slo),sum(bill.it),sum(bill.hrr),sum(bill.pli),sum(bill.pt),sum(bill.sis_gis_1979),"
				+ "sum(bill.sis_if_1981),sum(bill.sis_sf_1981),sum(bill.ais_if_1980),sum(bill.ais_sf_1980),sum(bill.gpf_c),"
				+ "sum(bill.car_sct_moped_adv),sum(bill.oca_cycle_adv),sum(bill.hba),sum(bill.fan_adv),sum(bill.jeep_r),"
				+ "sum(bill.gpf_iv),sum(bill.total_ded),sum(bill.net_total),sum(bill.pe),sum(bill.le),sum(bill.ltc),"
				+ "sum(bill.other_allow),sum(bill.bonus),sum(bill.other_chrgs),sum(bill.surcharge),sum(bill.rent_b),sum(bill.bli),"
				+ "sum(bill.gpf_adv),sum(bill.misc_recov),sum(bill.per_pay),sum(bill.dp_gazzeted),max(bill.created_date)"
				+ " from hr_pay_paybill bill,paybill_head_mpg phm where bill.emp_id=" + EmpId
				+ " and ((phm.paybill_month>='4' and phm.paybill_year='" + year
				+ "') or (phm.paybill_month<='3' and phm.paybill_year='" + (year + 1) + "') )"
				+ " and phm.approve_flag in (" + approved + "," + created + ") and phm.paybill_id = bill.id "
				+ " group by bill.emp_id,phm.paybill_month,phm.paybill_year order by phm.year,phm.paybill_month";

		// logger.info("the sql query is " + strQuery);

		Query query = hibSession.createSQLQuery(strQuery);
		payslipList = query.list();

		List paySlipNewList = new ArrayList();

		for (Iterator it = payslipList.iterator(); it.hasNext();) {
			HrPayPaybill payBillVO = new HrPayPaybill();

			Object[] rowList = (Object[]) it.next();
			double x = 0;
			int a = 0;

			payBillVO.setId(Long.parseLong((rowList[0] != null ? rowList[0] : a).toString()));// id

			HrEisEmpMst hrEis = new HrEisEmpMst();
			long empId = Long.parseLong((rowList[1] != null ? rowList[1] : a).toString());
			hrEis.setEmpId(empId);
			payBillVO.setHrEisEmpMst(hrEis);// emp id
			// payBillVO.setMonth(Double.parseDouble((rowList[2]!=null?rowList[2]:x).toString()));
			// //month
			// payBillVO.setYear(Double.parseDouble((rowList[3]!=null?rowList[3]:x).toString()));//year
			payBillVO.setAllow0101(Double.parseDouble((rowList[4] != null ? rowList[4] : x).toString()));// special
			// pay
			payBillVO.setBasic0101(Double.parseDouble((rowList[5] != null ? rowList[5] : x).toString()));// pay
			// of
			// officer

			// salary
			payBillVO.setAllow0119(Double.parseDouble((rowList[7] != null ? rowList[7] : x).toString()));// DP
			payBillVO.setAllow0103(Double.parseDouble((rowList[8] != null ? rowList[8] : x).toString())); // da
			payBillVO.setAllow0110(Double.parseDouble((rowList[9] != null ? rowList[9] : x).toString()));// hra
			payBillVO.setAllow0111(Double.parseDouble((rowList[10] != null ? rowList[10] : x).toString()));// cla
			payBillVO.setAllow0107(Double.parseDouble((rowList[11] != null ? rowList[11] : x).toString()));// ma
			payBillVO.setAllow1301(Double.parseDouble((rowList[12] != null ? rowList[12] : x).toString()));// wa
			payBillVO.setAllow0113(Double.parseDouble((rowList[13] != null ? rowList[13] : x).toString()));// Transport
			// allowance
			payBillVO.setAdv5701(Double.parseDouble((rowList[14] != null ? rowList[14] : x).toString()));// fest
			// adv
			payBillVO.setAdv5801(Double.parseDouble((rowList[15] != null ? rowList[15] : x).toString()));// food
			// adv
			payBillVO.setDeduc0101(Double.parseDouble((rowList[16] != null ? rowList[16] : x).toString()));// pay
			// recovery
			payBillVO.setGrossAmt(Double.parseDouble((rowList[17] != null ? rowList[17] : x).toString()));// gross
			// amount

			payBillVO.setIt(Double.parseDouble((rowList[19] != null ? rowList[19] : x).toString()));// it
			payBillVO.setDeduc9550(Double.parseDouble((rowList[20] != null ? rowList[20] : x).toString()));// hrr

			payBillVO.setDeduc9570(Double.parseDouble((rowList[22] != null ? rowList[22] : x).toString()));// pt

			// 1979
			payBillVO.setDeduc9583(Double.parseDouble((rowList[26] != null ? rowList[26] : x).toString()));// ais_if_1980
			// cycle
			// loan
			payBillVO.setLoan9591(Double.parseDouble((rowList[31] != null ? rowList[31] : x).toString()));// hba
			payBillVO.setLoan9720(Double.parseDouble((rowList[32] != null ? rowList[32] : x).toString()));// fan
			// advance
			payBillVO.setDeduc9780(Double.parseDouble((rowList[33] != null ? rowList[33] : x).toString()));// jeep
			// rent
			payBillVO.setDeduc9531((Double.parseDouble((rowList[34] != null ? rowList[34] : x).toString())));// gpf
			// iv
			payBillVO.setTotalDed((Double.parseDouble((rowList[35] != null ? rowList[35] : x).toString())));// total
			// deduc
			payBillVO.setNetTotal((Double.parseDouble((rowList[36] != null ? rowList[36] : x).toString())));// net
			// total
			payBillVO.setBasic0102((Double.parseDouble((rowList[37] != null ? rowList[37] : x).toString())));// Pe

			payBillVO.setAllow0105((Double.parseDouble((rowList[39] != null ? rowList[39] : x).toString())));// ltc
			payBillVO.setAllow0104((Double.parseDouble((rowList[40] != null ? rowList[40] : x).toString())));// other
			// allow
			payBillVO.setAllow0108((Double.parseDouble((rowList[41] != null ? rowList[41] : x).toString())));// bonus

			// charhges
			payBillVO.setSurcharge((Double.parseDouble((rowList[43] != null ? rowList[43] : x).toString())));// surcharge
			payBillVO.setDeduc9560((Double.parseDouble((rowList[44] != null ? rowList[44] : x).toString())));// rent
			// b

			payBillVO.setAdv9670((Double.parseDouble((rowList[46] != null ? rowList[46] : x).toString())));// gpf
			// advance
			payBillVO.setDeduc9910((Double.parseDouble((rowList[47] != null ? rowList[47] : x).toString())));// misc
			// recovery
			payBillVO.setAllow0102((Double.parseDouble((rowList[48] != null ? rowList[48] : x).toString())));// personal
			// pay
			payBillVO.setAllow0120((Double.parseDouble((rowList[49] != null ? rowList[49] : x).toString())));// DP_GAZZETED
			payBillVO.setCreatedDate((Date) rowList[50]);// created Date

			paySlipNewList.add(payBillVO);
		}

		return paySlipNewList;
	}

	/*
	 * public List getDemandNoByLocId(String locCode, String langName) { List
	 * resList = null; try { Session hibSession = getSession(); StringBuffer query =
	 * new StringBuffer();query.append(
	 * "select bpncode,demandCode from SgvaBuddemandMst a where a.bpncode in ");
	 * query
	 * .append("(select g.bpncode from SgvaBudbpnMapping g where g.deptId in ");
	 * query.append("(select t.departmentId from RltLocationDepartment t "); if
	 * (!locCode.equalsIgnoreCase(parentlocationIdStr))
	 * query.append("where t.locCode='" + locCode).append("')) and ");
	 * query.append("a.langId = '").append(langName).append("' and a.finYrId=" +
	 * (long)FIN_YEAR_ID);
	 * 
	 * ////logger.info("Query for demand no is " + query); Query sqlQuery =
	 * hibSession.createQuery(query.toString()); resList = sqlQuery.list();
	 * ////logger.info("List size for demand no is " + resList.size()); } catch
	 * (Exception e) { logger.error("Error is: "+ e.getMessage()); } return resList;
	 * }
	 */

	public List getMjrHeadByDemandNoandFin(String demandNo, String langName, int FIN_YR_ID) {
		List resList = null;
		try {
			logger.info("into this function in 665");
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append("select b.budmjrhdCode from SgvaBudmjrhdMst b where b.demandCode='").append(demandNo);
			query.append("' and b.langId='").append(langName).append("' and b.finYrId=").append(FIN_YR_ID);
			// logger.info("Query for Major Head is " +
			// query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for Major Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getSubMjrHeadByMjrHeadandFin(String demandNo, String langName, String mjrHead_Code, int FIN_YR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append("select q.BUDSUBMJRHD_CODE from sgva_budsubmjrhd_mst q where q.demand_code=").append(demandNo);
			query.append(" and q.budmjrhd_code=").append(mjrHead_Code).append(" and q.lang_id='").append(langName)
					.append("' and q.fin_yr_id=").append(FIN_YR_ID);
			// logger.info("Query for Sub Major Head is " +
			// query.toString());
			Query sqlQuery = hibSession.createSQLQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for SubMajor Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	/*
	 * 
	 * when hbm genetrae tghen remove above method public List
	 * getSubMjrHeadByMjrHead(String demandNo, String langName, String mjrHead_Code)
	 * { List resList = null; try { Session hibSession = getSession(); StringBuffer
	 * query = new StringBuffer(); query .append(
	 * "select q.budsubmjrhdCode from SgvaBudsubhdMst q where q.demandCode='")
	 * .append(demandNo);
	 * query.append("' and q.budmjrhdCode='").append(mjrHead_Code)
	 * .append("' and q.langId='").append(langName).append(
	 * "' and q.finYrId=").append(FIN_YEAR_ID);
	 * //logger.info("Query for Sub Major Head is " + query.toString()); Query
	 * sqlQuery = hibSession.createQuery(query.toString()); resList =
	 * sqlQuery.list(); //logger.info("List size for SubMajor Head is " +
	 * resList.size()); } catch (Exception e) { logger.error("Error is: "+
	 * e.getMessage()); } return resList; }
	 */

	// in hti the vo and Hbm not found for this sgva_budminhd_mst table
	public List getMnrHeadByMjrHeadandFin(String demandNo, String mjrHead_Code, String subMjrHeadCode, String langName,
			int FIN_YR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append("select h.BUDMINHD_CODE from sgva_budminhd_mst h where  h.demand_code=").append(demandNo)
					.append(" and ");
			query.append(" h.budmjrhd_code=").append(mjrHead_Code).append(" and ");
			query.append("h.budsubmjrhd_code=").append(subMjrHeadCode).append(" and h.lang_id='").append(langName)
					.append("' and h.fin_yr_id=").append(FIN_YR_ID);
			// logger.info("Query for Minor Head is " +
			// query.toString());
			Query sqlQuery = hibSession.createSQLQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for Minor Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getSubHeadByMnrHeadandFin(String demandNo, String mjrHead_Code, String subMjrHeadCode,
			String mnrHeadCode, String langName, int FIN_YR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(
					"select shm.element_code,a1.budsubhdCode,a1.budsubhdDescLong from HrPayOrderSubHeadMpg shm,SgvaBudsubhdMst a1 where shm.sgvaBudsubhdMst.budsubhdId in( ");
			query.append(
					" select a.budsubhdId from SgvaBudsubhdMst a where a.budsubhdId = a1.budsubhdId and a.demandCode='")
					.append(demandNo);
			query.append("' and a.budmjrhdCode='").append(mjrHead_Code).append("' and a.budsubmjrhdCode='")
					.append(subMjrHeadCode);
			query.append("' and a.budminhdCode='").append(mnrHeadCode).append("' and a.langId='").append(langName)
					.append("' and a.finYrId=").append(FIN_YR_ID).append(" )");
			// logger.info("Query for Sub Head is " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for Sub Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getDetailHeadByMnrHeadandFin(String mjrHead_Code, String subMjrHeadCode, String mnrHeadCode,
			String subHeadCode, String langName, int FIN_YR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append("select a.budsubhdCode from SgvaBudsubhdMst a where a.budmjrhdCode='").append(mjrHead_Code)
					.append("' and a.budsubmjrhdCode='").append(subMjrHeadCode).append("' ");
			query.append(" and a.budminhdCode='").append(mnrHeadCode);
			query.append("' and a.budsubhdCode='").append(subHeadCode);
			query.append("' and a.langId='").append(langName).append("' and a.finYrId=").append(FIN_YR_ID);
			// logger.info("Query for Detail Head is " +
			// query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			// System.out .println("List size for Detail Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getEmpDatabymonthyear(long empId, int month, int year) // used
	// in
	// GeneratePayslipService.
	{
		List payBillEmpList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayPaybill as payBill where payBill.hrEisEmpMst.empId=" + empId
				+ " and  payBill.month=" + month + " and payBill.year=" + year + " order by payBill.createdDate desc";
		Query query = hibSession.createQuery(strQuery);
		payBillEmpList = query.list();

		return payBillEmpList;
	}

	// Added by Paurav for getting Financial Year Info

	// intr

	public SgvcFinYearMst getFinYrInfo(Date lDate, Long lLangId) {
		StringBuffer lSBQuery = new StringBuffer();
		SgvcFinYearMst lObjFinYrVO = null;
		Session ghibSession = null;

		try {
			lSBQuery.append(" FROM SgvcFinYearMst A WHERE ");
			lSBQuery.append(" :date BETWEEN A.fromDate AND A.toDate ");
			lSBQuery.append(" AND A.langId = :langId ");

			ghibSession = getSession();
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			SimpleDateFormat lDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			lQuery.setParameter("date", lDateFormat.parse(lDateFormat.format(lDate)));
			lQuery.setParameter("langId", getLangById(lLangId));

			logger.info("Query for getFinYrInfo : " + lQuery.toString());
			logger.info("And Parameter is " + lDate + " " + lLangId);

			List lList = lQuery.list();

			if (lList != null && lList.size() > 0) {
				lObjFinYrVO = (SgvcFinYearMst) lList.get(0);
			}
		} catch (Exception e) {
			logger.error("Error in getFinYrInfo is : " + e, e);
		}

		return lObjFinYrVO;
	}

	public SgvcFinYearMst getFinYrInfoSchdl(Date lDate, Long lLangId) {
		StringBuffer lSBQuery = new StringBuffer();
		SgvcFinYearMst lObjFinYrVO = null;
		Session ghibSession = null;

		try {
			lSBQuery.append(" FROM SgvcFinYearMst A WHERE ");
			lSBQuery.append(" :date BETWEEN A.fromDate AND A.toDate ");
			lSBQuery.append(" AND A.langId = :langId ");

			ghibSession = getSession();
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			SimpleDateFormat lDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			lQuery.setParameter("date", lDateFormat.parse(lDateFormat.format(lDate)));
			lQuery.setParameter("langId", getLangById(lLangId));

			logger.info("Query for getFinYrInfo : " + lQuery.toString());
			logger.info("And Parameter is " + lDate + " " + lLangId);

			List lList = lQuery.list();

			if (lList != null && lList.size() > 0) {
				lObjFinYrVO = (SgvcFinYearMst) lList.get(0);
			}
		} catch (Exception e) {
			logger.error("Error in getFinYrInfo is : " + e, e);
		}

		return lObjFinYrVO;
	}

	// Ended By Paurav
	// added by Ankit Bhatt for checking that Location has defined Bills or not
	public boolean isBillsDefined(String locationCode, long langId) {
		List billHeadData = null;
		Session hibSession = getSession();

		StringBuffer strQuery = new StringBuffer("from MstDcpsBillGroup  pbh");
		if (!locationCode.equals(parentlocationIdStr) && !locationCode.equals(parentLocationCode))
			strQuery.append(" where pbh.LocId='" + locationCode + "' and pbh.LangId=" + langId);

		logger.info("Query in isBillsDefined is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		billHeadData = query.list();
		logger.info("Paybill data is " + billHeadData);
		logger.info("Size is " + billHeadData.size());
		if (billHeadData.size() == 0)
			return false;
		else
			return true;
	}

	// ended by Ankit Bhatt

	// added by Ankit Bhatt for getting all the posts which are mapped to Bill
	public List getPostByBillNo(long locId, long billNo) {
		List postList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("from HrPayPsrPostMpg where billNo=");
		strQuery.append(billNo);
		if (locId != parentlocationId)
			strQuery.append("and loc_id=" + locId);
		logger.info("Query in getPostByBillNo is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		postList = query.list();
		return postList;
	}

	// ended by Ankit Bhatt

	// added by Ankit Bhatt for Checking entry for One head , for one category
	// and in one month
	/*
	 * this method is not used in payroll application public boolean
	 * checkPaybillData(long subHeadId, int month, int year, long gradeId[], long
	 * empId) { List paybillData = null; Session hibSession = getSession();
	 * StringBuffer strQuery = new StringBuffer(
	 * "from PaybillHeadMpg as payBillHeadMpg,HrPayPaybill as paybill where payBillHeadMpg.sgvaBudsubhdMst.budsubhdId="
	 * ); strQuery.append(subHeadId); strQuery.append(" and ("); int i = 0; for (i =
	 * 0; i < (gradeId.length - 2); i++)
	 * strQuery.append("payBillHeadMpg.orgGradeMst.gradeId =" + gradeId[i] +
	 * " or ");
	 * 
	 * strQuery.append("payBillHeadMpg.orgGradeMst.gradeId=" + gradeId[i] +
	 * ") and paybill.month= " + month); strQuery.append(" and paybill.year=" +
	 * year); strQuery.append(" and paybill.hrEisEmpMst.empId=" + empId);
	 * logger.info("Query in checkPaybillData is " + strQuery.toString()); Query
	 * query = hibSession.createQuery(strQuery.toString()); paybillData =
	 * query.list(); logger.info("Paybill data is " + paybillData);
	 * logger.info("Size is " + paybillData.size()); if (paybillData.size() == 0)
	 * return false; else return true; }
	 */
	public boolean checkPaybillData(int month, int year, String postId, long employeeId) {
		List paybillData = null;
		Session hibSession = getSession();

		ResourceBundle constantsBundle1 = ResourceBundle.getBundle("resources.Payroll");
		logger.info("thje resource bundle is " + constantsBundle1);

		String created = constantsBundle1.getString("created");
		String approved = constantsBundle1.getString("approved");

		StringBuffer strQuery = new StringBuffer(
				"from PaybillHeadMpg as payBillHeadMpg,HrPayPaybill as paybill where  ");
		strQuery.append(
				" payBillHeadMpg.hrPayPaybill = paybill.paybillGrpId and payBillHeadMpg.billTypeId.lookupId in( "
						+ paybillTypeId);
		strQuery.append("," + supplBill_type_First + "," + supplBill_type_Second + "," + supplBill_type_Third + ")");
		// int i = 0;
		/*
		 * for(i=0;i<(gradeId.length-2);i++)
		 * strQuery.append("payBillHeadMpg.orgGradeMst.gradeId ="+ gradeId[i] + " or ");
		 */

		// strQuery.append("payBillHeadMpg.orgGradeMst.gradeId="+ gradeId[i] +
		// ") and paybill.month= " + month);
		strQuery.append(" and payBillHeadMpg.year=" + year + " and payBillHeadMpg.month= " + month);
		/// if (employeeId == 0)
		strQuery.append(" and paybill.orgPostMst.postId=" + postId);

		strQuery.append(" and payBillHeadMpg.approveFlag in ('" + created + "','" + approved + "') ");
		if (employeeId != 0) {
			strQuery.append(" and paybill.hrEisEmpMst.empId = " + employeeId);
		}

		logger.info("Query in new checkPaybillData is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		paybillData = query.list();
		logger.info("Paybill data is " + paybillData);
		logger.info("Size is " + paybillData.size());
		if (paybillData.size() == 0)
			return false;
		else
			return true;
	}

	public List getBillNoFromTrnNo(long trnBillNo) {
		List billNo = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"from PaybillBillregMpg as mpg where mpg.trnBillRegister.billNo=" + trnBillNo);
		logger.info("Query in getBillNoFromTrnNo is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		billNo = query.list();
		return billNo;
	}

	public List getBillFromTrnNo(long trnBillNo) {
		List billNo = null;
		Session hibSession = getSession();
		// //added By Mrugesh

		StringBuffer strQuery = new StringBuffer(
				"from PaybillBillregMpg as mpg where mpg.trnBillRegister.billNo=" + trnBillNo);
		// ended
		logger.info("Query in getBillNoFromTrnNo is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		billNo = query.list();
		return billNo;
	}

	/*
	 * Not use in Hole Payroll Application public List getSelectedHead(long
	 * paybillNo) { List headList = null; Session hibSession = getSession();
	 * StringBuffer strQuery = new StringBuffer(
	 * "from SgvaBudsubhdMst t where t.budsubhdId in" +
	 * "(select sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg where hrPayPaybill ="
	 * + paybillNo + ")"); logger.info("Query in getSelectedHead is " +
	 * strQuery.toString()); Query query =
	 * hibSession.createQuery(strQuery.toString()); headList = query.list(); return
	 * headList; }
	 */
	public List getSelectedHead(long paybillNo, long arrearBillId) {
		List headList = null;
		Session hibSession = getSession();
		logger.info("Manish here:::::::::::::");
		StringBuffer strQuery = new StringBuffer(
				" select t.budsubhdCode,t.budsubhdDescLong,t.bpnCode,t.demandCode,t.budmjrhdCode,t.budsubmjrhdCode,t.budminhdCode from SgvaBudsubhdMst t where t.budsubhdId in "
						+ "(select sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg where ");

		if (arrearBillId != 0) {
			strQuery.append(" hrPayPaybill =" + arrearBillId + ")");
		} else {
			strQuery.append(" hrPayPaybill =" + paybillNo + ")");
		}

		logger.info("Query in getSelectedHead is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		headList = query.list();
		logger.info("Manish also here::::::::::");
		return headList;
	}

	/*
	 * This method is not used in hole application
	 * 
	 * public List getSelectedDept(long bpnCode) { List headList = null; Session
	 * hibSession = getSession(); StringBuffer strQuery = new StringBuffer();
	 * strQuery .append("select * from CmnLocationMst m where m.location_code in ");
	 * strQuery.append(
	 * "(select locCode from RltLocationDepartment q where q.departmentId in ");
	 * strQuery
	 * .append(" (select deptId from SgvaBudbpnMapping p where p.bpncode='");
	 * strQuery.append(bpnCode); strQuery.append("'))");
	 * logger.info("Query in getSelectedHead is " + strQuery.toString()); Query
	 * query = hibSession.createSQLQuery(strQuery.toString()); headList =
	 * query.list(); return headList; }
	 */
	public List getSelectedMonthYear(long paybillNo) {
		List headList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("from HrPayPaybill where id=" + paybillNo);
		logger.info("Query in getSelectedMonthYear is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		headList = query.list();
		return headList;
	}

	public List getSelectedMonthYear(long paybillNo, long arrearBillId) {
		List headList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");

		if (arrearBillId != 0) {
			// Modified By Mrugesh
			// strQuery.append("from HrPayArrearPaybill where id=" +
			// arrearBillId);
			strQuery.append("from PaybillHeadMpg where hrPayPaybill=" + arrearBillId);
			// Ended By Mrugesh
		} else {
			// TODO
			// strQuery.append("from HrPayPaybill where id=" + paybillNo); by
			// rahul Date:06-11-08
			// May be temp solution
			// strQuery.append("from PaybillHeadMpg where hrPayPaybill.paybillGrpId="
			// + paybillNo);
			// Updated by Urvin Shah
			strQuery.append("from PaybillHeadMpg where hrPayPaybill=" + paybillNo);
			// End.
		}

		logger.info("Query in getSelectedMonthYear is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		headList = query.list();
		return headList;
	}

	public List getSelectedDept(String bpnCode) {
		List headList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select m.locName from CmnLocationMst m where m.locationCode in ");
		strQuery.append("(select locCode from RltLocationDepartment q where q.departmentId in ");
		strQuery.append(" (select deptId from SgvaBudbpnMapping p where p.bpncode='");
		strQuery.append(bpnCode);
		strQuery.append("'))");
		logger.info("Query in getSelectedHead is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		headList = query.list();
		return headList;
	}

	public List getBillDtls() {
		List resList = null;
		try {

			ResourceBundle constantsBundle1 = ResourceBundle.getBundle("resources.Payroll");
			logger.info("thje resource bundle is " + constantsBundle1);

			String created = constantsBundle1.getString("created");
			String approved = constantsBundle1.getString("approved");

			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(" select distinct a.id, ");
			query.append(" c.month, ");
			query.append(" c.year, ");
			query.append(" d.demandCode, ");
			query.append(" d.budmjrhdCode, ");
			query.append(" d.budsubmjrhdCode, ");
			query.append(" d.budminhdCode, ");
			query.append(" d.budsubhdCode ");
			query.append(" from HrPayPaybill     a, ");
			// query.append(" paybill_billreg_mpg b, ");
			query.append(" PaybillHeadMpg    c, ");
			query.append("  SgvaBudsubhdMst   d ");
			query.append("  where  a.id = c.hrPayPaybill and ");
			query.append(" c.sgvaBudsubhdMst.budsubhdId = d.budsubhdId and c.approveFlag  in('" + created + "','"
					+ approved + "') ");
			// logger.info("Query for billdtls " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for Sub Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getconcernedDept(long langId, long userId) {
		List headList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(" select m.locId,m.locName ");
		strQuery.append(" from CmnLocationMst m ");
		strQuery.append(" where m.locationCode in ");
		strQuery.append(" (select p.locationCode ");
		strQuery.append(" from OrgUserMst         u, ");
		strQuery.append(" OrgPostDetailsRlt pd, ");
		strQuery.append(" OrgPostMst         p, ");
		strQuery.append(" OrgUserpostRlt     up ");
		strQuery.append(" where u.userId = up.orgUserMst.userId and pd.orgPostMst.postId = p.postId and ");
		strQuery.append(" up.orgPostMstByPostId.postId = p.postId and pd.cmnLanguageMst.langId = '" + langId
				+ "' and u.userId = '" + userId + "') and ");
		strQuery.append(" m.cmnLanguageMst.langId = '" + langId + "'  ");
		logger.info("Query in getSelecteddept is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		// logger.info("SqlQuery in getSelecteddept is " + query.);

		headList = query.list();
		return headList;
	}

	/*
	 * Developed By Urvin shah. Date:- 29-12-2007 Method Name:-
	 * getInvestAmtOfCurrFinYear() Purpose:- This method fetch Investment total of a
	 * perticular employee. return@totalInvestAmt
	 */

	public long getInvestAmtOfCurrFinYear(long empId) {
		long totalInvestAmt = 0l;
		Object objTotalInvestAmt;
		StringBuffer strQuery = new StringBuffer();
		Session hibSession = getSession();
		strQuery.append("select sum(invest.amount) from HrInvestEmpDtls invest ");
		strQuery.append(" where invest.hrEisEmpMst.empId = " + empId);
		strQuery.append(" group by invest.hrEisEmpMst.empId");
		Query query = hibSession.createQuery(strQuery.toString());
		objTotalInvestAmt = query.uniqueResult();
		if (objTotalInvestAmt != null) {
			totalInvestAmt = (Long) objTotalInvestAmt;
		}
		logger.info("Employee Investment Details:-" + totalInvestAmt);
		return totalInvestAmt;
	}

	/*
	 * Developed By Urvin shah. Date:- 29-12-2007 Method
	 * name:-getExemptAmtOfCurrFinYear(); Purpose:- This method fetch Investment
	 * total of a perticular employee. return@totalExemptAmt.
	 */

	public long getExemptAmtOfCurrFinYear(long empId) {
		long totalExemptAmt = 0l;
		Object objTotalExemptAmt;

		StringBuffer strQuery = new StringBuffer();
		Session hibSession = getSession();
		strQuery.append("select sum(exempt.amount) from HrItExemptEmpDtls exempt ");
		strQuery.append(" where exempt.hrEisEmpMst.empId = " + empId);
		strQuery.append(" group by exempt.hrEisEmpMst.empId");
		Query query = hibSession.createQuery(strQuery.toString());
		objTotalExemptAmt = (Long) query.uniqueResult();
		if (objTotalExemptAmt != null) {
			totalExemptAmt = (Long) objTotalExemptAmt;
		}
		logger.info("Employee Investment Details:-" + totalExemptAmt);
		return totalExemptAmt;
	}

	/*
	 * Developed By Urvin shah. Date:- 29-12-2007 Method name:-getPastIncTaxData();
	 * Purpose:- This method fetch Past Months' Total Gross Amount, Prividand Fund,
	 * Income Tax, and Proffessional Tax of Current Financial Year of a perticular
	 * employee. return@totalExemptAmt.
	 */

	public Map getPastIncTaxData(long empId, int monthGiven, int yearGiven) {
		StringBuffer strQuery = new StringBuffer();
		Session hibSession = getSession();
		Map mapIncomeTaxData = new HashMap();
		Query query;
		Object[] objList;
		double totalPastGrossAmt = 0d;
		double totalPastProffTaxAmt = 0d;
		double totalPastIncomeTax = 0d;
		// double totalPastPF = 0d;
		// int nextMonths = 0;
		if (monthGiven > 3) {
			// nextMonths = 15 - monthGiven;
			strQuery.append(
					"select sum(payBill.grossAmt),sum(payBill.it),sum(payBill.deduc9570),sum(payBill.deduc9670),sum(payBill.deduc9531),sum(payBill.deduc9620),sum(payBill.deduc9534) from HrPayPaybill payBill ,PaybillHeadMpg billHeadMpg ");
			strQuery.append(
					" where billHeadMpg.hrPayPaybill = payBill.paybillGrpId and payBill.hrEisEmpMst.empId = " + empId);
			strQuery.append(" and billHeadMpg.month < " + monthGiven);
			strQuery.append(" and billHeadMpg.year = " + yearGiven);
			strQuery.append(" and billHeadMpg.approveFlag = 1");
			strQuery.append(" group by payBill.hrEisEmpMst.empId");
		} else {
			// nextMonths = 3 - monthGiven;
			strQuery.append(
					"select sum(payBill.grossAmt),sum(payBill.it),sum(payBill.deduc9570),sum(payBill.deduc9670),sum(payBill.deduc9531),sum(payBill.deduc9620),sum(payBill.deduc9534) from HrPayPaybill payBill,PaybillHeadMpg billHeadMpg ");
			strQuery.append(
					" where billHeadMpg.hrPayPaybill = payBill.paybillGrpId and payBill.hrEisEmpMst.empId = " + empId);
			strQuery.append(" and billHeadMpg.approveFlag = 1");
			strQuery.append(" and ((billHeadMpg.month < " + monthGiven + " and billHeadMpg.year=" + yearGiven + ")");
			yearGiven = yearGiven - 1;
			strQuery.append(" or (payBill.month > 3 and payBill.year = " + yearGiven + "))");
			strQuery.append(" group by payBill.hrEisEmpMst.empId");
		}
		yearGiven = yearGiven + 1;
		query = hibSession.createQuery(strQuery.toString());
		objList = (Object[]) query.uniqueResult();
		if (objList != null) {
			totalPastGrossAmt = (Double) objList[0];
			totalPastIncomeTax = (Double) objList[1];
			totalPastProffTaxAmt = (Double) objList[2];
			// totalPastPF = (Double) objList[3] + (Double) objList[4] + (Double) objList[5]
			// + (Double) objList[6];
			logger.info("Past Gross Amount is:-" + totalPastGrossAmt);
			logger.info("Past Income Tax Amount is:-" + totalPastIncomeTax);
			logger.info("Past Professional is:-" + totalPastProffTaxAmt);
		} else
			logger.info("No Record Found");
		mapIncomeTaxData.put("totalPastGrossAmt", totalPastGrossAmt);
		mapIncomeTaxData.put("totalPastProffTaxAmt", totalPastProffTaxAmt);
		mapIncomeTaxData.put("totalPastIncomeTax", totalPastIncomeTax);
		return mapIncomeTaxData;
	}

	/*
	 * Developed By Urvin shah. Date:- 29-12-2007 Method name:-getPastIncTaxData();
	 * Purpose:- This method fetch Next Months'Proffessional Tax of Current
	 * Financial Year of a perticular employee. return@totalNextProffTaxAmt.
	 */

	public double getNextProfTax(long empId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		Query query;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long ptId = Long.parseLong(resourceBundle.getString("ptId"));
		Object objTotalNextProffTaxAmt;
		double totalNextProffTaxAmt = 0d;

		strQuery.append("select deducDtls.empDeducAmount from HrPayDeductionDtls deducDtls");
		strQuery.append(
				" where deducDtls.hrPayDeducTypeMst.deducCode = " + ptId + " and deducDtls.hrEisEmpMst.empId=" + empId);

		query = hibSession.createQuery(strQuery.toString());
		objTotalNextProffTaxAmt = (Double) query.uniqueResult();
		if (objTotalNextProffTaxAmt != null) {
			totalNextProffTaxAmt = (Double) objTotalNextProffTaxAmt;
		}
		logger.info("Employee Professioanl Tax Details:-" + totalNextProffTaxAmt);
		return totalNextProffTaxAmt;
	}

	/*
	 * Developed By Urvin shah. Date:- 29-12-2007 Method name:-getNextPFAmt();
	 * Purpose:- This method fetch Next Months'Providand Amount, which could be the
	 * GPF,AIS PF,GPF-IV of Current Financial Year of a perticular employee.
	 * return@totalNextProffTaxAmt.
	 */

	public double getNextPFAmt(long empId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		Query query;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long ptId = Long.parseLong(resourceBundle.getString("ptId"));
		Object objTotalNextProffTaxAmt;
		double totalNextProffTaxAmt = 0d;

		strQuery.append("select deducDtls.empDeducAmount from HrPayDeductionDtls deducDtls");
		strQuery.append(
				" where deducDtls.hrPayDeducTypeMst.deducCode = " + ptId + " and deducDtls.hrEisEmpMst.empId=" + empId);

		query = hibSession.createQuery(strQuery.toString());
		objTotalNextProffTaxAmt = (Double) query.uniqueResult();
		if (objTotalNextProffTaxAmt != null) {
			totalNextProffTaxAmt = (Double) objTotalNextProffTaxAmt;
		}
		logger.info("Employee Professioanl Tax Details:-" + totalNextProffTaxAmt);
		return totalNextProffTaxAmt;
	}

	/*
	 * Developed By Urvin shah. Date:- 29-12-2007 Method name:-getPastIncTaxData();
	 * Purpose:- This method fetch Next Months'Allowances Total of Current Financial
	 * Year of a perticular employee. return@totalNextProffTaxAmt.
	 */

	public double getAllowancesTotal(long empId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		Query query;
		Object objTotalAllowancesAmt;
		double totalAllowancesAmt = 0d;
		strQuery.append("select otherDtls.totalSalary from HrEisOtherDtls otherDtls where otherDtls.hrEisEmpMst.empId="
				+ empId);
		query = hibSession.createQuery(strQuery.toString());
		objTotalAllowancesAmt = query.uniqueResult();

		if (objTotalAllowancesAmt != null) {
			totalAllowancesAmt = (Double) objTotalAllowancesAmt;
		}
		logger.info("Total Next Months' Allowances Amount is" + totalAllowancesAmt);
		return totalAllowancesAmt;
	}

	/*
	 * Developed By Urvin shah. Date:- 29-12-2007 Method name:-getPastIncTaxData();
	 * Purpose:- This method fetch Next Months' Advances Amount of Current Financial
	 * Year of a perticular employee. return@totalNextProffTaxAmt.
	 */

	public long getTotNextAdvacesAmt(long empId, long loanTypeId, int monthGiven) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		Query query;
		// Object objTotalAllowancesAmt;
		Object[] objList;
		long loanPrinAmt = 0l; // Loan Principal Amount
		long loanRecPrinAmt = 0l; // Recovered Pricipal Amount of the Loan.
		long loanTotInstNo = 0l; // Total Number of Installment of Principal
		// Amount.
		long loanRecInstNo; // Total Recovered Installment of Principal Amount
		long loanEmiAmt; // EMI Amount of the Princlipal Amount
		long loanRemainedInstNo = 0l; // Total Remained Installments of loan.
		long totalNextAdvaceAmt = 0l; // Total Next Monaths' Advances Amount in
		// the current Financial Year.

		strQuery.append(
				" select loanDtls.loanPrinAmt,loanDtls.loanPrinInstNo,loanDtls.loanPrinEmiAmt,loanPrinDtls.totalRecoveredInst,loanPrinDtls.totalRecoveredAmt from HrLoanEmpPrinRecoverDtls loanPrinDtls,HrLoanEmpDtls loanDtls");
		strQuery.append(" where loanDtls.hrEisEmpMst.empId=" + empId);
		strQuery.append(" and loanDtls.empLoanId = loanPrinDtls.hrLoanEmpDtls.empLoanId");
		strQuery.append(
				" and loanPrinDtls.totalRecoveredAmt < loanDtls.loanPrinAmt and loanDtls.hrLoanAdvMst.loanAdvId = "
						+ loanTypeId);

		query = hibSession.createQuery(strQuery.toString());
		objList = (Object[]) query.uniqueResult();
		if (objList != null) {
			int nextMonths;
			logger.info("Total Food Loan Data is:-" + objList.length);
			if (monthGiven > 3) {
				nextMonths = 15 - monthGiven;
			} else {
				nextMonths = 3 - monthGiven;
			}
			loanPrinAmt = (Long) objList[0];
			loanTotInstNo = (Long) objList[1];
			loanEmiAmt = (Long) objList[2];
			loanRecInstNo = (Long) objList[3];
			loanRecPrinAmt = (Long) objList[4];

			loanRemainedInstNo = loanTotInstNo - (loanRecInstNo + 1);
			loanRecPrinAmt += loanEmiAmt;
			logger.info("Total Installment Number is:-" + loanTotInstNo);
			logger.info("Total Principal Amount is:-" + loanPrinAmt);
			logger.info("The Monthly EMI Amount is:-" + loanEmiAmt);
			logger.info("The Remained Installment Number is:-" + loanRemainedInstNo);
			logger.info("The Recovered Principal Amount is:-" + loanRecPrinAmt);
			logger.info("Next Months are :-" + nextMonths);
			if (loanRecPrinAmt < loanPrinAmt) {
				if (loanRemainedInstNo < nextMonths) {
					logger.info("The Value is:-" + (loanEmiAmt * loanRemainedInstNo));
					totalNextAdvaceAmt = loanEmiAmt * loanRemainedInstNo;
				} else
					totalNextAdvaceAmt = loanEmiAmt * nextMonths;
			}
		}
		logger.info("Total next Months' Advance Amount of the current Financial year is:-" + totalNextAdvaceAmt);
		return totalNextAdvaceAmt;
	}

	// Added by Mrugesh
	public List getSelectedMjrHeadDesc(String mjrHead) {
		List mjrHeadDescList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select mjr.budmjrhdDescLong from SgvaBudmjrhdMst mjr where mjr.budmjrhdCode = '" + mjrHead
						+ "' and mjr.langId = " + "'en_US'");
		logger.info("Query in getSelectedMjrHeadDesc is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		mjrHeadDescList = query.list();
		return mjrHeadDescList;
	}

	// in hti the vo and Hbm not found for this sgva_budsubmjrhd_mst table
	public List getSelectedSubMjrHeadDesc(String subMjrHead) {
		List subMjrheadDescList = null;
		Session hibSession = getSession();
		StringBuffer strQuery1 = new StringBuffer(
				"select subMjr.budsubmjrhd_desc_long from sgva_budsubmjrhd_mst subMjr where subMjr.budsubmjrhd_code = '"
						+ subMjrHead + "' and subMjr.lang_id = " + "'en_US'");
		logger.info("Query in getSelectedSubMjrHeadDesc is " + strQuery1.toString());
		Query query1 = hibSession.createSQLQuery(strQuery1.toString());
		subMjrheadDescList = query1.list();

		return subMjrheadDescList;
	}

	// in hti the vo and Hbm not found for this sgva_budminhd_mst table

	public List getSelectedMinHeadDesc(String mnrHead) {
		List mnrHeadDescList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select min.budminhd_desc_long from sgva_budminhd_mst min where min.budminhd_code = '" + mnrHead
						+ "' and min.lang_id = " + "'en_US'");
		logger.info("Query in getSelectedMinHeadDesc is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		mnrHeadDescList = query.list();
		return mnrHeadDescList;
	}

	public List getSelectedDemandDesc(String dmdCode) {
		List demandDescList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select dmd.demand_desc from sgva_buddemand_mst dmd where dmd.demand_code = '" + dmdCode
						+ "' and dmd.lang_id = " + "'en_US'");
		logger.info("Query in getSelectedDemandDesc is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		demandDescList = query.list();
		return demandDescList;
	}

	public String getBillCtrlNoFromTrnNo(long trnBillNo) {
		String billNo = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select tb.billCntrlNo from TrnBillRegister tb where tb.billNo=" + trnBillNo);
		logger.info("Query in getBillCtrlNoFromTrnNo is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		billNo = query.uniqueResult().toString();
		return billNo;
	}

	// Ended by Mrugesh

	// added by manoj for logically delete of paybills

	/*
	 * this method is not used in new structure public void logicalDelete(long
	 * trnBillNo) {
	 * 
	 * Session hibSession = getSession(); ResourceBundle constantsBundle =
	 * ResourceBundle .getBundle("resources.Payroll");
	 * 
	 * String logicalDelete = constantsBundle.getString("logicalDelete"); Date date1
	 * = new Date();
	 * 
	 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	 * 
	 * String deleteDate = sdf.format(date1);
	 * 
	 * StringBuffer strQuery = new StringBuffer(
	 * "update HrPayPaybill bill set bill.approve_flag = '" + logicalDelete +
	 * "',bill.approve_reject_date = '" + deleteDate +
	 * "' where  bill.paybillGrpId = " + trnBillNo); logger
	 * .info("Query in getBillCtrlNoFromTrnNo is " + strQuery.toString()); Query
	 * query = hibSession.createSQLQuery(strQuery.toString());
	 * 
	 * StringBuffer strQuery1 = new StringBuffer(
	 * "update HrPayPaybillHst bill set bill.approve_flag = '" + logicalDelete +
	 * "',bill.approve_reject_date = '" + deleteDate +
	 * "' where bill.paybillGrpId = " + trnBillNo);
	 * logger.info("Query in getBillCtrlNoFromTrnNo is " + strQuery1.toString());
	 * Query query1 = hibSession.createSQLQuery(strQuery1.toString());
	 * 
	 * }
	 */
	// end by manoj for logically delete of paybills
	public List getDemandNoByLocId(String locCode, String langName, long FIN_YEAR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append("select bpncode,demandCode from SgvaBuddemandMst a where a.bpncode in ");
			query.append("(select g.bpncode from SgvaBudbpnMapping g where g.deptId in ");
			query.append("(select t.departmentId from RltLocationDepartment t ");
			if (!locCode.equalsIgnoreCase(parentlocationIdStr))
				query.append("where t.locCode='" + locCode + "'))");
			query.append("and  a.langId = '").append(langName).append("' and a.finYrId=" + FIN_YEAR_ID);

			// logger.info("Query for demand no is " + query);
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for demand no is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getMjrHeadByDemandNo(String demandNo, String langName, long FIN_YEAR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(" select b.budmjrhdCode from SgvaBudmjrhdMst b where b.demandCode='").append(demandNo);
			query.append("' and b.langId='").append(langName).append("' and b.finYrId=").append(FIN_YEAR_ID);
			// logger.info("Query for Major Head is " +
			// query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for Major Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getSubMjrHeadByMjrHead(String demandNo, String langName, String mjrHead_Code, long FIN_YEAR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append("select q.BUDSUBMJRHD_CODE from sgva_budsubmjrhd_mst q where q.demand_code='")
					.append(demandNo);
			query.append("' and q.budmjrhd_code='").append(mjrHead_Code).append("' and q.lang_id='").append(langName)
					.append("' and q.fin_yr_id=").append(FIN_YEAR_ID);
			// logger.info("Query for Sub Major Head is " +
			// query.toString());
			Query sqlQuery = hibSession.createSQLQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for SubMajor Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	/*
	 * Comneted By samir jOshi for Vo Is nOt generated After Generat vo remove it
	 * public List getSubMjrHeadByMjrHead(String demandNo, String langName, String
	 * mjrHead_Code, long FIN_YEAR_ID) { List resList = null; try { Session
	 * hibSession = getSession(); StringBuffer query = new StringBuffer(); query
	 * .append(
	 * "select q.budsubmjrhdCode from SgvaBudsubhdMst q where q.demandCode='")
	 * .append(demandNo);
	 * query.append("' and q.budmjrhdCode='").append(mjrHead_Code)
	 * .append("' and q.langId='").append(langName).append(
	 * "' and q.finYrId=").append(FIN_YEAR_ID);
	 * //logger.info("Query for Sub Major Head is " + query.toString()); Query
	 * sqlQuery = hibSession.createQuery(query.toString()); resList =
	 * sqlQuery.list(); //logger.info("List size for SubMajor Head is " +
	 * resList.size()); } catch (Exception e) { logger.error("Error is: "+
	 * e.getMessage()); } return resList; }
	 */

	// in hti the vo and Hbm not found for this sgva_budminhd_mst table
	public List getMnrHeadByMjrHead(String demandNo, String mjrHead_Code, String subMjrHeadCode, String langName,
			long FIN_YEAR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append("select budminhd_code from sgva_budminhd_mst h where  h.demand_Code='").append(demandNo)
					.append("' and ");
			query.append(" h.budmjrhd_Code='").append(mjrHead_Code).append("' and ");
			query.append("h.budsubmjrhd_Code='").append(subMjrHeadCode).append("' and h.lang_id='").append(langName)
					.append("' and h.fin_yr_id=").append(FIN_YEAR_ID);
			// logger.info("Query for Minor Head is " +
			// query.toString());
			Query sqlQuery = hibSession.createSQLQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for Minor Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getSubHeadByMnrHead(String demandNo, String mjrHead_Code, String subMjrHeadCode, String mnrHeadCode,
			String langName, long FIN_YEAR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(
					"select a.budsubhdId ,a.budsubhdCode ,a.budsubhdDescLong from SgvaBudsubhdMst a where a.demandCode='")
					.append(demandNo);
			query.append("' and a.budmjrhdCode='").append(mjrHead_Code).append("' and a.budsubmjrhdCode='")
					.append(subMjrHeadCode).append("' ");
			query.append(" and a.budminhdCode='").append(mnrHeadCode).append("' and a.langId='").append(langName)
					.append("' and a.finYrId=").append(FIN_YEAR_ID);
			logger.info("Query for Sub Head is " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			logger.info("List size for Sub Head is " + resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getDetailHeadByMnrHead(String mjrHead_Code, String subMjrHeadCode, String mnrHeadCode,
			String subHeadCode, String langName, long FIN_YEAR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append("from SgvaBudsubhdMst a where a.budmjrhdCode='").append(mjrHead_Code)
					.append("' and a.budsubmjrhdCode='").append(subMjrHeadCode).append("' ");
			query.append(" and a.budminhdCode='").append(mnrHeadCode);
			query.append("' and a.budsubhdCode='").append(subHeadCode);
			query.append("' and a.langId='").append(langName).append("' and a.finYrId=").append(FIN_YEAR_ID);
			// logger.info("Query for Detail Head is " +
			// query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			// System.out .println("List size for Detail Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	public List getBillNo(long finYrId, long locId, String ddoCode) {
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// query.append("select dcpsDdoBillGroupId,dcpsDdoBillDescription from
		// MstDcpsBillGroup where finYearId=" + finYrId);
		query.append("select dcpsDdoBillGroupId,dcpsDdoBillDescription    from MstDcpsBillGroup as billgrp ");
		if (locId != parentlocationId)
			query.append(" where  billgrp.LocId=" + locId + " and billgrp.dcpsDdoCode='" + ddoCode
					+ "' and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') order by billgrp.dcpsDdoBillDescription asc");

		logger.info("Query for get bill no is---->>>>" + query.toString());

		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();
		logger.info("the size of resList is ::" + resList.size());
		return resList;
	}

	public List getAllDataFromSubHeadCode(long subHeadCode, long finYrId) {
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(
				"select bs.budsubhdCode,bs.demandCode,bs.budmjrhdCode,bs.budsubmjrhdCode,bs.budminhdCode from SgvaBudsubhdMst bs,HrPayOrderSubHeadMpg os where os.element_code = "
						+ subHeadCode + " and os.finYearId= " + finYrId
						+ " and os.sgvaBudsubhdMst.budsubhdId = bs.budsubhdId");

		logger.info("Query form get getAllDataFromSubHeadCode is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();

		return resList;
	}

	public List getAllDataFromBillSubHeadId(long billSubHeadId) {
		List subHeadCode = null;
		Session hibSession = getSession();
		// intr
		// modified by saurabh
		StringBuffer strQuery = new StringBuffer(
				"select ms.scheme_id,ms.scheme_code, ms.scheme_name, ms.demand_code,ms.major_head,");
		strQuery.append(
				" ms.sub_major_head,ms.minor_head,ms.sub_minor_head,ms.sub_head,ms.charged,ms.plan,bg.SUB_SCHEME_CODE");
		strQuery.append(
				" from mst_dcps_bill_group AS bg,mst_scheme AS ms where bg.SCHEME_CODE=ms.scheme_code and bg.BILL_GROUP_ID=");
		strQuery.append(billSubHeadId);
		strQuery.append(" and ms.lang_id='1'");
		logger.info("Query in getAllDataFromBillSubHeadId is------>>>>>> " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		subHeadCode = query.list();
		return subHeadCode;
	}

	public List getDsgnNameFromId(String dsgnId) {
		StringTokenizer st = new StringTokenizer(dsgnId, ",");
		String strDsgnId = null;
		List dsgnShrtName = new ArrayList();
		Session hibSession = getSession();
		String strQuery = "";
		while (st.hasMoreTokens()) {
			strDsgnId = st.nextToken();

			strQuery = "select dsgnShrtName from OrgDesignationMst d where d.dsgnId =" + strDsgnId;
			logger.info("Query in getDsgnNameFromId is " + strQuery.toString());
			Query query = hibSession.createQuery(strQuery.toString());
			String dsgnName = query.uniqueResult().toString();
			dsgnShrtName.add(dsgnName);

		}
		return dsgnShrtName;
	}

	public List getClassNameFromId(String classId) {
		List gradShrtName = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select  gradeName from OrgGradeMst where gradeId in(" + classId + ")");
		logger.info("Query in getClassNameFromId is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		gradShrtName = query.list();
		return gradShrtName;
	}

	public List getPayBillDataList(int month, int year, String subHeadId, long postType, String gradeIds,
			String dsgnIds, long billNo) {

		long approved = Long.parseLong(payrollBundle.getString("approved"));

		List paySlipList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");
		strQuery.append("select bill from HrPayPaybill as bill  "
				+ " where bill.paybillGrpId in (select ph.hrPayPaybill from PaybillHeadMpg as ph,MstDcpsBillGroup billSubHead where ph.month = "
				+ month + "   and ph.year = "

				+ year + " and ph.approveFlag = " + approved + " and billSubHead.dcpsDdoBillGroupId = " + billNo
				+ " and billSubHead.dcpsDdoBillGroupId = ph.billNo.billHeadId )" + " and "
				+ " bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId in (" + gradeIds + ") ");

		if (postType != 0) {
			strQuery.append(" and bill.orgPostMst.postTypeLookupId = " + postType);
		}

		// strQuery.append(" and billSubHead.billHeadId = "+billNo
		// +" and billSubHead.billHeadId = ph.billNo.billHeadId " );

		Query sqlQry = hibSession.createQuery(strQuery.toString());
		logger.info("The query from HrPayPaybill is---->>>" + sqlQry);
		paySlipList = sqlQry.list();

		return paySlipList;
	}

	public List getPayBillListForEmp(int month, int year, String subHeadId, long postType, long empId, String gradeIds,
			String dsgnIds, long billNo) {

		long approved = Long.parseLong(payrollBundle.getString("approved"));

		List paySlipList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");

		/*
		 * strQuery.
		 * append("select bill from HrPayPaybill as bill ,PaybillHeadMpg as ph,OrgPostDetailsRlt as pd,MstDcpsBillGroup billSubHead "
		 * + " where ph.hrPayPaybill = bill.paybillGrpId and " +
		 * " bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId in (" +gradeIds+") and " +
		 * " pd.orgPostMst.postId = bill.orgPostMst.postId and ph.month = " +month);
		 * 
		 * if(postType != 0) {
		 * strQuery.append(" and bill.orgPostMst.postTypeLookupId = "+postType); }
		 * 
		 * strQuery.append("   and ph.year = "+year +
		 * " and ph.approveFlag = "+approved);
		 * 
		 * strQuery.append(" and billSubHead.billHeadId = "+billNo
		 * +" and billSubHead.billHeadId = ph.billNo.billHeadId " );
		 */

		strQuery.append("select bill from HrPayPaybill as bill ,OrgPostDetailsRlt as pd "
				+ " where bill.paybillGrpId in (select ph.hrPayPaybill from PaybillHeadMpg as ph,MstDcpsBillGroup billSubHead where ph.month = "
				+ month + "   and ph.year = "

				+ year + " and ph.approveFlag = " + approved + " and billSubHead.dcpsDdoBillGroupId = " + billNo
				+ " and billSubHead.dcpsDdoBillGroupId = ph.billNo.billHeadId )" + " and "
				+ " bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId in (" + gradeIds + ") and "
				+ " pd.orgPostMst.postId = bill.orgPostMst.postId ");

		if (postType != 0) {
			strQuery.append(" and bill.orgPostMst.postTypeLookupId = " + postType);

			if (empId != 0) {
				strQuery.append(" and bill.orgEmpMst.empId = " + empId);
			}
		}
		// strQuery.append(" and billSubHead.billHeadId = "+billNo
		// +" and billSubHead.billHeadId = ph.billNo.billHeadId " );

		Query sqlQry = hibSession.createQuery(strQuery.toString());
		logger.info("The query from HrPayPaybill is---->>>" + sqlQry);
		paySlipList = sqlQry.list();

		return paySlipList;
	}

	/**
	 * Author:- Urvin Shah Date:- 08/08/2008 Method Name:- List
	 * getOuterSignature(long trnBillNo) Purpose:- This method fetch the data for
	 * the Outer-Signatury information.
	 */

	public List getOuterSignature(long trnBillNo) {
		List lstSignData = null;
		Session hibSession = getSession();
		String strQuery = "select dg.dsgnName, dept.locName, d.cardexNo,c.cityName" + " from OrgDdoMst d,"
				+ "TrnBillRegister   r," + "OrgDesignationMst dg," + "OrgPostMst        p," + "CmnLocationMst  dept,"
				+ "OrgPostDetailsRlt rlt," + "CmnCityMst c"
				+ " where r.ddoDeptId = dept.locationCode and r.ddoCode = d.ddoCode and"
				+ " d.langId=1 and c.cmnLanguageMst.langId = 1 and dg.cmnLanguageMst.langId = 1 and  "
				+ " d.postId = p.postId and rlt.orgDesignationMst.dsgnId = dg.dsgnId and rlt.orgPostMst.postId = d.postId and dept.locCityId=c.cityId and r.billNo="
				+ trnBillNo;
		logger.info("Query in getClassNameFromId is " + strQuery.toString());

		Query query = hibSession.createQuery(strQuery.toString());
		lstSignData = query.list();
		return lstSignData;
	}

	public List getPaybillListForOuter(long paybillGrpId) {

		List paySlipList = null;
		Session hibSession = getSession();
		String strQuery = "select  max(bill.id),bill.paybillGrpId,sum(bill.loan9591),sum(bill.loanInt9591),sum(bill.loan9592),sum(bill.loanInt9592)"
				+ " from HrPayPaybill bill where bill.paybillGrpId = " + paybillGrpId + " group by bill.paybillGrpId";
		// +" having bill.paybill_month="+month+" and bill.paybill_year="+year;

		// logger.info("the sql query is " + strQuery);
		Query query = hibSession.createQuery(strQuery);
		paySlipList = query.list();

		return paySlipList;
	}

	public List getArrearbillListForOuter(long paybillGrpId) {

		List paySlipList = null;
		Session hibSession = getSession();
		String strQuery = "select max(bill.id),bill.paybillGrpId,sum(bill.loan9591),sum(bill.loanInt9591),sum(bill.loan9592),sum(bill.loanInt9592)"
				+ " from HrPayArrearPaybill bill where bill.paybillGrpId = " + paybillGrpId
				+ " group by bill.paybillGrpId";
		// +" having bill.paybill_month="+month+" and bill.paybill_year="+year;

		// logger.info("the sql query is " + strQuery);
		Query query = hibSession.createQuery(strQuery);
		paySlipList = query.list();

		return paySlipList;
	}

	public List getReportSignature(long locid) {
		List lstSignData = null;
		Session hibSession = getSession();
		String strQuery = "select dg.dsgnName, dept.locName, d.cardexNo,c.cityName" + " from OrgDdoMst d,"
				+ "OrgDesignationMst dg," + "OrgPostMst        p," + "CmnLocationMst  dept," + "CmnCityMst c,"
				+ "OrgPostDetailsRlt rlt" + " where "
				+ "     d.postId = p.postId and rlt.orgDesignationMst.dsgnId = dg.dsgnId and dept.locCityId=c.cityId and dept.cmnLanguageMst.langId=1 ";
		if (locid != parentlocationId)
			strQuery += " and dept.locId=" + locid + " and d.cardexNo<>'300001'"
					+ " and d.langId=1 and c.cmnLanguageMst.langId = 1 and dg.cmnLanguageMst.langId = 1 ";
		strQuery += " and rlt.orgPostMst.postId = d.postId and rlt.orgPostMst.postId = p.postId and rlt.cmnLocationMst.locId="
				+ locid;
		logger.info("Query in getClassNameFromId is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		lstSignData = query.list();
		return lstSignData;
	}

	/*
	 * This method can be used to generate citywise report.
	 */

	public String getCityName(String cityId) {
		logger.info("cityId is: " + cityId);
		String cityName = null;
		String strQuery = null;
		Query query = null;
		Session hibSession = getSession();

		strQuery = "SELECT cmnCityMst.cityName " + "FROM CmnCityMst cmnCityMst " + "WHERE cmnCityMst.cityId= '" + cityId
				+ "'";
		query = hibSession.createQuery(strQuery);
		cityName = query.list().get(0).toString();
		logger.info("retuning city name : " + cityName);
		return cityName;
	}// end method: getCityName

	public Map getMasterDataIds() {
		// for id from loan and advances table
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

		String fest_adv_id = "";
		String food_adv_id = "";
		String pay_recovery_id = "";
		String gpf_adv_id = "";

		long allowLookupId = Long.parseLong(resourceBundle.getString("allowLookupId"));
		// long deducLookupId =
		// Long.parseLong(resourceBundle.getString("deducLookupId"));
		// long loanLookupId = Long.parseLong(resourceBundle.getString("loanLookupId"));
		long advLookupId = Long.parseLong(resourceBundle.getString("advLookupId"));
		// long dpLookupId = Long.parseLong(resourceBundle.getString("dpLookupId"));
		// long basicLookupId =
		// Long.parseLong(resourceBundle.getString("basicLookupId"));
		// long miscLookupId = Long.parseLong(resourceBundle.getString("miscLookupId"));
		HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, getSessionFactory());

		List<HrPayEdpCompoMpg> hrAdvList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(advLookupId));// for
		// advances

		long edpCodeId = 0;
		for (int j = 0; j < hrAdvList.size(); j++) {
			HrPayEdpCompoMpg hrEdpComp = hrAdvList.get(j);
			edpCodeId = hrEdpComp.getRltBillTypeEdp().getTypeEdpId();

			GenericDaoHibernateImpl genericDAO = new GenericDaoHibernateImpl(RltBillTypeEdp.class);
			genericDAO.setSessionFactory(getSessionFactory());

			logger.info("edpCodeId ---> " + edpCodeId);
			List<RltBillTypeEdp> rltEDPList = genericDAO.getListByColumnAndValue("typeEdpId", edpCodeId);

			if (rltEDPList.size() > 0) {
				if (rltEDPList.get(0).getEdpCode() != null) {
					edpCodeId = Long.parseLong(rltEDPList.get(0).getEdpCode().trim());
					// changed by manish
					// edpCodeId = Long.parseLong(rltEDPList.get(0).getEdpCode());
				}
				// if(edpCodeId==5701)//festival advance
				if (edpCodeId == 7057)// festival advance
				{
					logger.info("Inside 7057");
					fest_adv_id = hrEdpComp.getTypeId();
				}
				// else if(edpCodeId==5801)//food advance
				else if (edpCodeId == 7058)// food advance
				{
					logger.info("Inside 7058");
					food_adv_id = hrEdpComp.getTypeId();
				} else if (edpCodeId == 101)// for pay recovery of pay arrears
				{
					pay_recovery_id = hrEdpComp.getTypeId();
				} else if (edpCodeId == 9670)// for GPF advance
				{
					gpf_adv_id = hrEdpComp.getTypeId();
				}
			}
			logger.info("Fest Adv id and Food Adv Id is " + fest_adv_id + "..." + food_adv_id);
		}

		String advCodeID = "";
		long edpID = 0;
		String edpCdId = "";
		long spID = 0;
		long ppID = 0;
		long bonusId = 0;

		List<HrPayEdpCompoMpg> allowList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(allowLookupId));

		for (int j = 0; j < allowList.size(); j++) {
			HrPayEdpCompoMpg hrEdpComp = allowList.get(j);

			advCodeID = String.valueOf(hrEdpComp.getTypeId());
			edpID = hrEdpComp.getRltBillTypeEdp().getTypeEdpId();
			logger.info("In Master Data fetching " + advCodeID + " " + edpID);

			GenericDaoHibernateImpl genericDAO = new GenericDaoHibernateImpl(RltBillTypeEdp.class);
			// GenericDaoHibernateImpl genericDAO = new
			// GenericDaoHibernateImpl(RltBillTypeEdp.class,serv.getSessionFactory())
			genericDAO.setSessionFactory(getSessionFactory());
			List<RltBillTypeEdp> rltEDPList = genericDAO.getListByColumnAndValue("typeEdpId", edpID);

			if (rltEDPList.size() > 0) {
				if (rltEDPList.get(0).getEdpCode() != null) {
					edpCdId = rltEDPList.get(0).getEdpCode();
					logger.info("edpCdId is " + edpCdId);
					// as only one code is mapped to one type of component
				}
				if (edpCdId.equals("0101"))// Personal Pay
				{
					ppID = Long.parseLong(advCodeID);
				} else if (edpCdId.equals("0102"))// Special Pay
				{
					spID = Long.parseLong(advCodeID);
				} else if (edpCdId.equals("0108"))// Bonus
				{
					bonusId = Long.parseLong(advCodeID);
				}
			}
		}

		Map mapMasterDataId = new HashMap();
		mapMasterDataId.put("fest_adv_id", fest_adv_id);
		mapMasterDataId.put("food_adv_id", food_adv_id);
		mapMasterDataId.put("spID", spID);
		mapMasterDataId.put("ppID", ppID);
		mapMasterDataId.put("gpf_adv_id", gpf_adv_id);
		mapMasterDataId.put("pay_recovery_id", pay_recovery_id);
		mapMasterDataId.put("bonusId", bonusId);
		return mapMasterDataId;

	}

	public boolean isBillsDefined(long locationCode) {
		List billHeadData = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("from MstDcpsBillGroup  pbh");
		if (locationCode != parentlocationId)
			strQuery.append(" where pbh.LocId='" + locationCode + "'");

		logger.info("Query in isBillsDefined is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		billHeadData = query.list();
		logger.info("Paybill data is " + billHeadData);
		logger.info("Size is " + billHeadData.size());
		if (billHeadData.size() == 0)
			return false;
		else
			return true;
	}

	public boolean isBillsDefined(String locationCode) {
		List billHeadData = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("from MstDcpsBillGroup  pbh");
		if (!locationCode.equals(strParentlocationId))
			strQuery.append(" where pbh.LocId='" + locationCode + "'");

		logger.info("Query in isBillsDefined is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		billHeadData = query.list();
		logger.info("Paybill data is " + billHeadData);
		logger.info("Size is " + billHeadData.size());
		if (billHeadData.size() == 0)
			return false;
		else
			return true;
	}

	public String getBillnoFormTrnBillReg(long trnBillNo, long locationCode) {
		List billHeadData = new ArrayList();
		String billNo = "";
		StringBuffer strQuery = new StringBuffer("");
		Session hibSession = getSession();

		strQuery.append("select mpg.billId from MstDcpsBillGroup mpg where mpg.dcpsDdoBillGroupId in ");
		strQuery.append("(select ph.billNo from PaybillHeadMpg ph where ph.cmnLocationMst.locationCode=" + locationCode
				+ " and ph.hrPayPaybill in ");
		strQuery.append(
				"(select t.hrPayPaybill from PaybillBillregMpg t where t.trnBillRegister= " + trnBillNo + ")) ");
		logger.info("Query in isBillsDefined is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		billHeadData = query.list();
		logger.info("Bill no data is " + billHeadData);
		logger.info("Bill no size is is " + billHeadData.size());
		if (billHeadData.size() > 0) {
			billNo = billHeadData.get(0).toString();
			// //logger.info("from dao billNo====>"+billNo);
		}

		return billNo;
	}

	/*
	 * public boolean checkPaybill(int month, int year, long billno, long locId,
	 * String billTypeLookupId) {
	 * 
	 * List paybillData = new ArrayList(); int size = 0; Session hibSession =
	 * getSession();
	 * 
	 * // this is the Check For Bill generated or not StringBuffer strQuery = new
	 * StringBuffer("select count(*) from HrPayPsrPostMpg as psr where psr.loc_id= "
	 * + locId + " and  psr.billNo=" + billno + " and psr.postId not in" +
	 * " (select paybill.orgPostMst.postId from "); if
	 * ("paybill".equalsIgnoreCase(billTypeLookupId) ||
	 * "multiplemonthpaybill".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append("HrPayPaybill"); } else if
	 * ("arrears".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" HrPayArrearPaybill"); }
	 * 
	 * strQuery.append(" as paybill where paybill.cmnLocationMst.locId=" + locId +
	 * " and paybill.paybillGrpId in " +
	 * " (select phm.hrPayPaybill from PaybillHeadMpg phm  where phm.cmnLocationMst.locId= "
	 * + locId + " and phm.billNo=" + billno + " and phm.month=" + month +
	 * " and phm.year=" + year + "  and phm.approveFlag<2 and ");
	 * 
	 * if ("paybill".equalsIgnoreCase(billTypeLookupId) ||
	 * "multiplemonthpaybill".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" phm.billTypeId.lookupId in ( " + paybillTypeId);
	 * strQuery.append("," + supplBill_type_First + "," + supplBill_type_Second +
	 * "," + supplBill_type_Third + ")"); } else if
	 * ("arrears".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" phm.billTypeId.lookupId = " + arrearbillTypeId); }
	 * 
	 * strQuery.append(" ))"); logger.info("Query in checkPaybillData is " +
	 * strQuery.toString()); Query query =
	 * hibSession.createQuery(strQuery.toString()); paybillData = query.list(); size
	 * = Integer.parseInt(paybillData.get(0).toString()); logger.info("sizee is===>"
	 * + size); logger.info("Paybill data is " + paybillData);
	 * logger.info("Size is " + paybillData.size()); // this is the Check fro
	 * Suplementry Bill if (size <= 0) { strQuery = new StringBuffer(
	 * " select count(*) from OrgUserpostRlt as up, OrgEmpMst orgEmpMst, HrEisEmpMst hrEisEmpMst, HrEisOtherDtls hrEisOtherDtls "
	 * + "	where 	up.activateFlag=1 and " +
	 * "			hrEisOtherDtls.hrEisEmpMst.empId = hrEisEmpMst.empId and " +
	 * "			hrEisEmpMst.orgEmpMst.empId = orgEmpMst.empId and " +
	 * "			orgEmpMst.orgUserMst.userId = up.orgUserMst.userId and" +
	 * "			orgEmpMst.empSrvcFlag = 1 and " +
	 * "			up.orgPostMstByPostId.postId in " +
	 * " (select paybill.orgPostMst.postId from "); if
	 * ("paybill".equalsIgnoreCase(billTypeLookupId) ||
	 * "multiplemonthpaybill".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" HrPayPaybill "); } else if
	 * ("arrears".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" HrPayArrearPaybill "); }
	 * 
	 * strQuery.
	 * append(" as paybill where  paybill.hrEisEmpMst.empId is null and paybill.paybillGrpId in "
	 * + " (select phm.hrPayPaybill from PaybillHeadMpg phm  where phm.billNo=" +
	 * billno + " and phm.month=" + month + " and phm.year=" + year +
	 * "  and phm.approveFlag<2 and ");
	 * 
	 * if ("paybill".equalsIgnoreCase(billTypeLookupId) ||
	 * "multiplemonthpaybill".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" phm.billTypeId.lookupId in ( " + paybillTypeId);
	 * strQuery.append("," + supplBill_type_First + "," + supplBill_type_Second +
	 * "," + supplBill_type_Third + ")"); } else if
	 * ("arrears".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" phm.billTypeId.lookupId = " + arrearbillTypeId); }
	 * strQuery.append(" )"); // added by ravysh strQuery.
	 * append(" and paybill.orgPostMst.postId not in (select paybill.orgPostMst.postId from "
	 * ); if ("paybill".equalsIgnoreCase(billTypeLookupId) ||
	 * "multiplemonthpaybill".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" HrPayPaybill "); } else if
	 * ("arrears".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" HrPayArrearPaybill "); } strQuery.
	 * append(" as paybill where paybill.hrEisEmpMst.empId is not null and paybill.paybillGrpId in "
	 * + " (select phm.hrPayPaybill from PaybillHeadMpg phm  where phm.billNo=" +
	 * billno + " and phm.month=" + month + " and phm.year=" + year +
	 * "  and phm.approveFlag<2 and "); if
	 * ("paybill".equalsIgnoreCase(billTypeLookupId) ||
	 * "multiplemonthpaybill".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" phm.billTypeId.lookupId in ( " + paybillTypeId);
	 * strQuery.append("," + supplBill_type_First + "," + supplBill_type_Second +
	 * "," + supplBill_type_Third + ")"); } else if
	 * ("arrears".equalsIgnoreCase(billTypeLookupId)) {
	 * strQuery.append(" phm.billTypeId.lookupId = " + arrearbillTypeId); }
	 * strQuery.append(" )))");
	 * logger.info("Query in new checkPaybillData for posts is " +
	 * strQuery.toString()); query = hibSession.createQuery(strQuery.toString());
	 * paybillData = query.list(); size =
	 * Integer.parseInt(paybillData.get(0).toString());
	 * logger.info("suplimentry bill size for posts  is===>" + size);
	 * logger.info("suplimentry Paybill data for posts is " + paybillData);
	 * logger.info(" suplimentry Size for posts is " + paybillData.size()); } //
	 * suplimentry Bill logic Ended
	 * 
	 * if (size <= 0 && "arrears".equalsIgnoreCase(billTypeLookupId)) { strQuery =
	 * new
	 * StringBuffer("select count(*) from HrPayArrearBillpostMpg as bpmg where bpmg.cmnLocationMst.locId= "
	 * + locId + " and  bpmg.MstDcpsBillGroup.billHeadId=" + billno +
	 * " and bpmg.orgPostMst.postId not in" +
	 * " (select paybill.orgPostMst.postId from ");
	 * strQuery.append(" HrPayArrearPaybill");
	 * 
	 * strQuery.append(" as paybill where paybill.cmnLocationMst.locId=" + locId +
	 * " and paybill.paybillGrpId in " +
	 * " (select phm.hrPayPaybill from PaybillHeadMpg phm  where phm.cmnLocationMst.locId= "
	 * + locId + " and phm.billNo=" + billno + " and phm.month=" + month +
	 * " and phm.year=" + year + "  and phm.approveFlag<2 and ");
	 * 
	 * strQuery.append(" phm.billTypeId.lookupId = " + arrearbillTypeId);
	 * 
	 * strQuery.append(" ))");
	 * logger.info("Query in checkPaybillData arrear bill post mapping  is " +
	 * strQuery.toString()); query = hibSession.createQuery(strQuery.toString());
	 * paybillData = query.list(); size =
	 * Integer.parseInt(paybillData.get(0).toString()); }
	 * 
	 * if (size <= 0) return false; else return true;
	 * 
	 * }
	 */

	/*
	 * public long getPsrNoFromPostId(long postId) { Session hibSession =
	 * getSession(); StringBuffer strQuery = new StringBuffer(); long psrno = 0;
	 * Query query;strQuery.append(
	 * "select psr.psr_no from hr_pay_post_psr_mpg psr where psr.post_id = "
	 * +postId); query = hibSession.createSQLQuery(strQuery.toString());
	 * 
	 * psrno = Long.parseLong(query.uniqueResult().toString()); return psrno; }
	 */
	public long getPsrNoFromPostId(long postId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		long psrno = 0;
		Query query;
		strQuery.append("select psr.psr_no from hr_pay_post_psr_mpg psr where psr.post_id = " + postId);
		query = hibSession.createSQLQuery(strQuery.toString());
		List resultList = query.list();

		if (resultList != null && resultList.size() > 0 && resultList.size() < 2)
			psrno = Long.parseLong(query.uniqueResult().toString());

		return psrno;
	}

	public long getTrnBillNoFrombillNoMonthYear(long billNo, int month, int year, long locId, long billTypeId,
			String arrearType) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		long trnBillNo = 0;
		Query query;
		strQuery.append(" select pg.trn_bill_id from paybill_billreg_mpg pg where pg.paybill_id  in ( ");
		strQuery.append(" select ph.paybill_id  ");
		strQuery.append(" from paybill_head_mpg ph ");
		strQuery.append(" where ph.loc_id = " + locId + " and ");
		strQuery.append(" ph.bill_no in (select bh.bill_subhead_id ");
		strQuery.append(" from hr_pay_bill_subhead_mpg bh ");
		strQuery.append(" where bh.bill_no = " + billNo + " ) ");
		strQuery.append(" and ph.paybill_month in (" + month + ") and ph.paybill_year = " + year
				+ " and ph.approve_flag in (0,1))  ");
		strQuery.append(" and pg.bill_type_id = " + billTypeId + " ");
		if (billTypeId == arrearbillTypeId && Long.parseLong(arrearType) > 0)
			strQuery.append(
					" and pg.paybill_id in (select  distinct a.paybill_grp_id  FROm hr_pay_arrear_paybill a where a.sal_rev_id = "
							+ arrearType + ")");
		query = hibSession.createSQLQuery(strQuery.toString());
		List resultList = query.list();

		if (resultList != null && resultList.size() == 1)
			trnBillNo = Long.parseLong(query.uniqueResult().toString());
		else
			trnBillNo = 0;

		return trnBillNo;
	}

	public List getSelectedMinHeadDesc(String mnrHead, String demandNo, String mjrHead, long FinYrId) {
		List mnrHeadDescList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select min.budminhd_desc_long from sgva_budminhd_mst min where min.budminhd_code = '" + mnrHead
						+ "' and min.lang_id = " + "'en_US' and  min.demand_code = '" + demandNo
						+ "' and min.budmjrhd_code = '" + mjrHead + "' and min.fin_yr_id = '" + FinYrId + "' ");
		logger.info("Query in getSelectedMinHeadDesc is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		mnrHeadDescList = query.list();
		return mnrHeadDescList;
	}

	public String getTANDtlsbyMonthLocId(int month, int year, long locId) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");

		if (month > 0 && year > 0) {
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		java.util.Date startMonthDate = cal.getTime();

		String startDate = sdfObj.format(startMonthDate);

		String TANNo = "";
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");
		strQuery.append(" select tan.tan_no ");
		strQuery.append(" from HR_PAY_tan_DTLS tan, cmn_location_mst l ");
		strQuery.append(" where tan.loc_id = " + locId + " and ");
		strQuery.append(" ((tan.end_date >= '" + startDate + "' and ");
		strQuery.append(" tan.start_date < '" + startDate + "') or tan.end_date is null) and ");
		strQuery.append(" tan.loc_id = l.loc_id ");

		logger.info("Query in TANDtlsbyMonthLocId is " + strQuery.toString());

		Query query = hibSession.createSQLQuery(strQuery.toString());
		if (query.list() != null && query.list().size() == 1)
			TANNo = query.list().get(0).toString();

		return TANNo;
	}

	// added by ravysh to find number of supplimentary bills generated
	public long getSupplBillNo(int month, int year, long billno, long locId) {
		// DBsysdateConfiguration sbConf = new DBsysdateConfiguration();

		// String getNVL = sbConf.getNVL();
		long lSupplBillNo = 0;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//
		// select phm.hrPayPaybill from PaybillHeadMpg phm where
		// phm.billNo="+billno+
		// " and phm.month="+month+
		// " and phm.year="+year+" and phm.approveFlag<2 and ")
		//
		strQuery.append(" select max(phm.billTypeId.lookupId) ");
		strQuery.append(" from PaybillHeadMpg phm  where phm.billNo=" + billno + " and ");
		strQuery.append(" phm.month=" + month + " and phm.year=" + year + "  and phm.approveFlag<2 and ");
		strQuery.append(" phm.cmnLocationMst.locId=" + locId + " ");

		logger.info("Query in getSupplBillNo is " + strQuery.toString());

		Query query = hibSession.createQuery(strQuery.toString());

		if (query.list() != null && query.list().size() == 1 && query.list().get(0) != null)
			lSupplBillNo = Long.valueOf(query.list().get(0).toString());

		return lSupplBillNo;
	}

	public List getArrearData(String startDate, String endDate) {

		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();

		strQuery.append(" from HrPaySalRevMst where revPayOutDate >='" + startDate + "' and revPayOutDate <='" + endDate
				+ "'  ");

		Query query = hibSession.createQuery(strQuery.toString());

		List dataList = query.list();

		return dataList;
	}

	public List getarrearBillDataList(int month, int year, long billNo, long salRevId, long locId) {

		long approved = Long.parseLong(payrollBundle.getString("approved"));

		List paySlipList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");

		strQuery.append(" select bill,bpm from HrPayPaybill as bill ,HrPayArrearBillpostMpg bpm ");
		strQuery.append(
				" where bill.paybillGrpId in (select ph.hrPayPaybill from PaybillHeadMpg as ph,MstDcpsBillGroup billSubHead where ph.month = ");
		strQuery.append(month);
		strQuery.append(" and ph.year = ");
		strQuery.append(year);
		strQuery.append(" and ph.approveFlag = ");
		strQuery.append(approved);
		strQuery.append(" and ph.cmnLocationMst.locId = ");
		strQuery.append(locId);
		strQuery.append(" and billSubHead.dcpsDdoBillGroupId = ph.billNo.billHeadId )  ");
		strQuery.append(" and bill.orgPostMst.postId = bpm.orgPostMst.postId and   bpm.hrPaySalRevMst.salRevId = "
				+ salRevId + "  ");
		strQuery.append(
				" and bpm.MstDcpsBillGroup.dcpsDdoBillGroupId = " + billNo + " and bill.hrEisEmpMst is not null ");

		Query Qry = hibSession.createQuery(strQuery.toString());
		logger.info("The query from HrPayPaybill arrear is---->>>" + Qry);
		paySlipList = Qry.list();

		return paySlipList;
	}

	public List getarrearBillDataListPaybillIndependent(int month, int year, long billNo, long salRevId, long locId) {

		// long approved = Long.parseLong(payrollBundle.getString("approved"));

		List paySlipList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");

		strQuery.append(" select bpm.arrearBillpostId,bpm from HrPayArrearBillpostMpg bpm ");
		strQuery.append(" where ");
		strQuery.append(" bpm.hrPaySalRevMst.salRevId = " + salRevId + "  ");
		strQuery.append(" and bpm.MstDcpsBillGroup.dcpsDdoBillGroupId = " + billNo + " ");

		Query Qry = hibSession.createQuery(strQuery.toString());
		logger.info("The query from HrPayPaybill arrear PaybillIndependent is---->>>" + Qry);
		paySlipList = Qry.list();

		return paySlipList;
	}

	public List getArrearList(long locId) {

		// long approved = Long.parseLong(payrollBundle.getString("approved"));

		List paySlipList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");

		strQuery.append(
				" from  HrPaySalRevMst a where a.cmnLocationMst.locId = " + locId + " order by a.salRevId desc ");

		Query Qry = hibSession.createQuery(strQuery.toString());
		logger.info("The query from getArrearListr is---->>>" + Qry);
		paySlipList = Qry.list();

		return paySlipList;
	}

	// added by ravysh for Form16 of all employees of a department
	public List getForm16Data(long locId, String empId, long year) {

		List qryList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");
		long nextYear = year + 1;

		strQuery.append("select a.emp_id,\n" + "       (case\n" + "         when trim(mt.emp_mname) is null then\n"
				+ "          (mt.emp_prefix ||' '|| mt.emp_fname || ' ' || mt.emp_lname)\n" + "         else\n"
				+ "          (mt.emp_prefix ||' '|| mt.emp_fname || ' ' || trim(mt.emp_mname) || ' ' || mt.emp_lname)\n"
				+ "       end) as emp_Name,\n" + "       kk.dsgn_name,\n" + "       pd.proof_num,\n"
				+ "       a.GROSS,\n" + "       (a.TA+nvl(fd.travel_allow,0)) TA,\n" + "       a.WA,\n"
				+ "       (a.HBA+nvl(fd.repay_hba,0)) HBA,\n" + "       (a.HBA_INT+nvl(fd.hba_interest,0)) HBA_INT,\n"
				+ "       (a.PT+nvl(fd.pro_tax,0)) PT,\n" + "       a.IT,\n" + "       b.AMT_PLI,\n"
				+ "       c.AMT_LIC,\n" + "       d.AMT_NSC,\n" + "       e.AMT_PPF,\n" + "       f.AMT_ULIP,\n"
				+ "       g.AMT_NSC_INT,\n" + "       h.AMT_PENSION,\n" + "       i.AMT_ANNUITY,\n"
				+ "       j.AMT_INFRASTR_BOND,\n" + "       k.AMT_TUITION_FEE,\n" + "       l.AMT_EQUITY,\n"
				+ "       m.AMT_PENS_SCHEME,\n" + "       n.AMT_MUTUALFUND,\n" + "       o.AMT_POST_OFF,\n"
				+ "       p.AMT_FIXD_DEP,\n" + "       r.AMT_DEP_NHB,\n" + "       s.AMT_EQUITY_SHARE,\n"
				+ "       t.AMT_NSS,\n" + "       u.AMT_MEDICLM,\n" + "       v.AMT_MED_TREAT,\n"
				+ "       w.AMT_DONATION,\n" + "       x.AMT_PERM_DISABL,\n" + "       y.AMT_EDU_LOAN,\n"
				+ "       z.AMT_CHILD_EDU,\n" + "       zz.AMT_VEH_EXEMP,\n" + "       xx.AMT_HRA_EXEMP,\n"
				+ "       fd.other_allow,\n" + "       fd.foreign_allow,\n" + "       fd.challan_tax,\n"
				+ "       fd.arrear_tax,\n" + "       (a.gpf+nvl(fd.gpf_cpf,0)) gpf,\n"
				+ "       (a.govt_ins+nvl(fd.govt_insurance,0)) govt_ins,\n" + "       a.gender,"
				+ "       ss.AMT_RELIEF_89");

		for (int i = 1; i <= 14; i++) {
			strQuery.append(",");
			strQuery.append(" deduc_dtls" + i + ".bank_name as deduc_dtls_bank" + i + "\n");
			strQuery.append(",");
			strQuery.append(" deduc_dtls" + i + ".deduc_dtls_date as deduc_dtls_date" + i + "\n");
		}
		for (int i = 1; i <= 12; i++) {

			strQuery.append(",");
			strQuery.append(
					" (nvl(month_tax" + i + ".it,0)+nvl(deduc_dtls" + i + ".income_tax,0)) as month_tax" + i + "\n");
		}
		strQuery.append(
				",psr.psr_no,fd.hba_int_claimed,fd.hba_repay_claimed,fd.hba_interest,fd.repay_hba,fd.challanNumber\n");
		// to be added by hemant
		strQuery.append("  from (select p.emp_id as emp_id,\n" + "               sum(p.gross_amt) as GROSS,\n"
				+ "               sum(p.trans_all) as TA,\n" + "               sum(p.wa) as WA,\n"
				+ "               sum(p.hba) as HBA,\n" + "               sum(p.hba_int) as HBA_INT,\n"
				+ "               sum(p.pt) as PT,\n"
				+ "               sum(p.it) as IT, sum(p.gpf_c+p.gpf_iv+p.gpf_adv+p.gpf_iv_adv+p.cpf+p.ais_pf+p.da_gpf+p.da_gpfiv) as gpf,"
				+ "  				sum(p.sis_gis_1979+p.sis_if_1981+p.sis_sf_1981+p.ais_if_1980+p.ais_sf_1980) as govt_ins,"
				+ "				max(ett.emp_gender) as gender\n"
				+ "         		 from hr_pay_paybill p, hr_eis_emp_mst ett\n"
				+ "        		 where p.emp_id is not null and p.paybill_grp_id in (select h.paybill_id from paybill_head_mpg h where \n"
				+ "               ((h.paybill_month >= 3 and h.paybill_year = " + year + ") or\n"
				+ "               (h.paybill_month <= 2 and h.paybill_year = " + nextYear + ")) and\n"
				+ "               h.approve_flag = 1 and h.bill_type_id = 2500337 and\n" + "               h.loc_id = "
				+ locId + ") and ett.emp_id=p.emp_id\n" + "         group by p.emp_id) a\n"
				+ "         left outer join\n" + "         (select sum(q.amount) AMT_PLI,\n"
				+ "           q.invest_type_id,\n" + "            q.emp_id\n"
				+ "             from hr_invest_emp_dtls q where q.invest_type_id = 16\n"
				+ "             group by q.invest_type_id,\n" + "                     q.emp_id\n"
				+ "         ) b on a.emp_id = b.emp_id\n" + "         left outer join (select sum(q.amount) AMT_LIC,\n"
				+ "                   q.invest_type_id,\n" + "                q.emp_id\n"
				+ "                from hr_invest_emp_dtls q where q.invest_type_id = 12\n"
				+ "                 group by q.invest_type_id,\n" + "               q.emp_id\n"
				+ "          ) c on a.emp_id = c.emp_id left outer join (select sum(q.amount) AMT_NSC,\n"
				+ " q.invest_type_id,\n" + " q.emp_id\n" + " from hr_invest_emp_dtls q where q.invest_type_id = 20\n"
				+ "group by q.invest_type_id,\n" + " q.emp_id\n" + ") d on a.emp_id = d.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_PPF,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 14\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") e on a.emp_id = e.emp_id\n" + "left outer join (select sum(q.amount) AMT_ULIP,\n"
				+ " q.invest_type_id,\n" + " q.emp_id\n" + " from hr_invest_emp_dtls q where q.invest_type_id = 18\n"
				+ "group by q.invest_type_id,\n" + " q.emp_id\n" + ") f on a.emp_id = f.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_NSC_INT,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 22\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") g on a.emp_id = g.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_PENSION,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 24\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") h on a.emp_id = h.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_ANNUITY,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 26\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") i on a.emp_id = i.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_INFRASTR_BOND,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 28\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") j on a.emp_id = j.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_TUITION_FEE,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 30\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") k on a.emp_id = k.emp_id\n" + "left outer join (select sum(q.amount) AMT_EQUITY,\n"
				+ " q.invest_type_id,\n" + " q.emp_id\n" + " from hr_invest_emp_dtls q where q.invest_type_id = 32\n"
				+ "group by q.invest_type_id,\n" + " q.emp_id\n" + ") l on a.emp_id = l.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_PENS_SCHEME,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 34\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") m on a.emp_id = m.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_MUTUALFUND,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 36\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") n on a.emp_id = n.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_POST_OFF,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 38\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") o on a.emp_id = o.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_FIXD_DEP,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 40\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") p on a.emp_id = p.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_DEP_NHB,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 42\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") r on a.emp_id = r.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_EQUITY_SHARE,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 44\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") s on a.emp_id = s.emp_id\n" + "left outer join (select sum(q.amount) AMT_NSS,\n"
				+ " q.invest_type_id,\n" + " q.emp_id\n" + " from hr_invest_emp_dtls q where q.invest_type_id = 46\n"
				+ "group by q.invest_type_id,\n" + " q.emp_id\n" + ") t on a.emp_id = t.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_MEDICLM,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 4\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") u on a.emp_id = u.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_MED_TREAT,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 6\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") v on a.emp_id = v.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_DONATION,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 8\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") w on a.emp_id = w.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_PERM_DISABL,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 10\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") x on a.emp_id = x.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_EDU_LOAN,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 12\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") y on a.emp_id = y.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_CHILD_EDU,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 13\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") z on a.emp_id = z.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_VEH_EXEMP,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 14\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") zz on a.emp_id = zz.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_HRA_EXEMP,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 15\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") xx on a.emp_id = xx.emp_id\n"
				+ "left outer join hr_eis_emp_mst et on a.emp_id=et.emp_id \n"
				+ "left outer join org_emp_mst mt on et.emp_mpg_id=mt.emp_id \n"
				+ "left outer join hr_eis_proof_dtl pd on mt.user_id=pd.user_id and pd.prooftype_lookupid=300166\n"
				+ "left outer join (select rlt.post_id,rlt.user_id,dg.dsgn_name,lc.loc_name from org_userpost_rlt rlt,"
				+ "org_post_details_rlt dt,org_designation_mst dg,cmn_location_mst lc where lc.loc_id=dt.loc_id and "
				+ "dt.post_id=rlt.post_id and dg.dsgn_id=dt.dsgn_id and rlt.emp_post_id=(select max(rr.emp_post_id) "
				+ "from org_userpost_rlt rr where rr.user_id=rlt.user_id group by rr.user_id)) kk on kk.user_id=mt.user_id\n"
				+ "left outer join hr_form16_dtls fd on fd.emp_id=a.emp_id and fd.fin_yr_id=" + year + "\n");
		for (int i = 1; i <= 14; i++) {
			strQuery.append(
					" left outer join (select ftd.form16_dtl_id,bmt.bank_name,ftd.deduc_dtls_date,ftd.income_tax from  "
							+ "hr_form16_tax_deduc_dtls ftd,hr_form16_bank_mst bmt where ftd.deduc_dtls_bank_id=bmt.bank_id "
							+ "and ftd.deduc_dtls_month=" + i + ") deduc_dtls" + i + " on deduc_dtls" + i
							+ ".form16_dtl_id=fd.form16_dtl_id \n");
		}
		for (int i = 1; i <= 12; i++) {
			if (i < 3)
				strQuery.append(" left outer join (select pay.it,pay.emp_id from hr_pay_paybill pay where "
						+ "pay.paybill_grp_id in (select head.paybill_id from paybill_head_mpg head where "
						+ "head.paybill_month=" + i + " and head.paybill_year=" + nextYear
						+ " and head.bill_type_id=2500337 " + "and head.approve_flag=1 and head.loc_id=" + locId
						+ ")) month_tax" + i + " on month_tax" + i + ".emp_id=a.emp_id \n");
			else
				strQuery.append(" left outer join (select pay.it,pay.emp_id from hr_pay_paybill pay where "
						+ "pay.paybill_grp_id in (select head.paybill_id from paybill_head_mpg head where "
						+ "head.paybill_month=" + i + " and head.paybill_year=" + year
						+ " and head.bill_type_id=2500337 " + "and head.approve_flag=1 and head.loc_id=" + locId
						+ ")) month_tax" + i + " on month_tax" + i + ".emp_id=a.emp_id \n");
		}

		strQuery.append("left outer join (select sum(qq.amount) AMT_RELIEF_89,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 17\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") ss on a.emp_id = ss.emp_id\n");
		strQuery.append("left outer join hr_pay_post_psr_mpg psr on  kk.post_id=psr.post_id");
		strQuery.append(" where pd.proof_num is not null ");
		if (!empId.equals(""))
			strQuery.append(" and a.emp_id in (" + empId + ")");

		Query Qry = hibSession.createSQLQuery(strQuery.toString());
		logger.info("The query from getForm16Data of all employees is---->>>" + Qry);
		qryList = Qry.list();

		return qryList;

	}

	public List getSignaturebyMonthandLocId(long locId, int month, int year) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();

		Calendar cal = Calendar.getInstance();
		DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
		SimpleDateFormat sdfObj = sbConf.GetDateFormat();

		if (month > 0 && year > 0) {
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		java.util.Date startMonthDate = cal.getTime();

		String startDate = sdfObj.format(startMonthDate);

		int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		cal.set(Calendar.DAY_OF_MONTH, totalDays);

		java.util.Date endMonthDate = cal.getTime();

		String endDate = sdfObj.format(endMonthDate);

		Query query;

		strQuery.append(" select sgn.hrEisEmpMst.empId, ");
		strQuery.append(" sgn.orgDesignationMst.dsgnId, ");
		strQuery.append(" sgn.orgDesignationMst.dsgnName, ");
		strQuery.append(
				" concat(concat(concat(sgn.hrEisEmpMst.orgEmpMst.empFname, concat(' ',sgn.hrEisEmpMst.orgEmpMst.empMname)),' '),sgn.hrEisEmpMst.orgEmpMst.empLname), ");
		strQuery.append(
				" sgn.cmnLocationMst.locName,sgn.cmnLocationMst.locPin,city.cityName,sgn.cmnLocationMst.locAddr1,sgn.empFullname ");
		strQuery.append("  from HrPaySignatureDtls sgn,CmnCityMst city ");
		strQuery.append(" where sgn.cmnLocationMst.locId = " + locId + " and ");
		strQuery.append(" (sgn.startDate <= '" + endDate + "' and (sgn.endDate >= '" + startDate
				+ "' or  sgn.endDate is null )) and sgn.cmnLocationMst.locCityId=city.cityId");
		query = hibSession.createQuery(strQuery.toString());
		List resultList = query.list();

		return resultList;
	}

	public List getForm16DataByUser(long locId, String empId, long year) {

		List qryList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("");
		// long nextYear = year + 1;

		strQuery.append("select a.emp_id,\n" + "       (case\n" + "         when trim(mt.emp_mname) is null then\n"
				+ "          (mt.emp_prefix ||' '|| mt.emp_fname || ' ' || mt.emp_lname)\n" + "         else\n"
				+ "          (mt.emp_prefix ||' '|| mt.emp_fname || ' ' || trim(mt.emp_mname) || ' ' || mt.emp_lname)\n"
				+ "       end) as emp_Name,\n" + "       kk.dsgn_name,\n" + "       pd.proof_num,\n"
				+ "       a.GROSS,\n" + "       a.TA,\n" + "       a.WA,\n" + "       a.HBA,\n" + "       a.HBA_INT,\n"
				+ "       a.PT,\n"
				+ "       (deduc_dtls1.income_tax+deduc_dtls2.income_tax+deduc_dtls3.income_tax+deduc_dtls4.income_tax+"
				+ "deduc_dtls5.income_tax+deduc_dtls6.income_tax+deduc_dtls7.income_tax+deduc_dtls8.income_tax+deduc_dtls9.income_tax"
				+ "+deduc_dtls10.income_tax+deduc_dtls11.income_tax+deduc_dtls12.income_tax) as IT,\n"
				+ "       b.AMT_PLI,\n" + "       c.AMT_LIC,\n" + "       d.AMT_NSC,\n" + "       e.AMT_PPF,\n"
				+ "       f.AMT_ULIP,\n" + "       g.AMT_NSC_INT,\n" + "       h.AMT_PENSION,\n"
				+ "       i.AMT_ANNUITY,\n" + "       j.AMT_INFRASTR_BOND,\n" + "       k.AMT_TUITION_FEE,\n"
				+ "       l.AMT_EQUITY,\n" + "       m.AMT_PENS_SCHEME,\n" + "       n.AMT_MUTUALFUND,\n"
				+ "       o.AMT_POST_OFF,\n" + "       p.AMT_FIXD_DEP,\n" + "       r.AMT_DEP_NHB,\n"
				+ "       s.AMT_EQUITY_SHARE,\n" + "       t.AMT_NSS,\n" + "       u.AMT_MEDICLM,\n"
				+ "       v.AMT_MED_TREAT,\n" + "       w.AMT_DONATION,\n" + "       x.AMT_PERM_DISABL,\n"
				+ "       y.AMT_EDU_LOAN,\n" + "       z.AMT_CHILD_EDU,\n" + "       zz.AMT_VEH_EXEMP,\n"
				+ "       xx.AMT_HRA_EXEMP,\n" + "       0 as Other_Allow,\n" + "       0 as Foreign_Allow,\n"
				+ "       a.challan_tax,\n" + "       a.arrear_tax,\n" + "       a.gpf,\n" + "       a.govt_ins,\n"
				+ "        et.emp_gender," + "       ss.AMT_RELIEF_89");

		for (int i = 1; i <= 14; i++) {
			strQuery.append(",");
			strQuery.append(" deduc_dtls" + i + ".bank_name as deduc_dtls_bank" + i + "\n");
			strQuery.append(",");
			strQuery.append(" deduc_dtls" + i + ".deduc_dtls_date as deduc_dtls_date" + i + "\n");
		}
		for (int i = 1; i <= 12; i++) {

			strQuery.append(",");
			strQuery.append(" deduc_dtls" + i + ".income_tax as month_tax" + i + "\n");
		}
		strQuery.append(",'' as psr,'' as hbaIntClmd,'' as hbaRepayClmd,'' as hbaInt,'' as hbaRepay, challanNumber\n");
		// to be added by hemant
		strQuery.append("  from (select ddt.other_allow GROSS,\n" + "       ddt.foreign_allow WA,\n"
				+ "       ddt.challan_tax challan_tax,\n" + "       ddt.arrear_tax arrear_tax,\n"
				+ "       ddt.travel_allow TA,\n" + "       ddt.pro_tax PT,\n" + "       ddt.hba_interest HBA_INT,\n"
				+ "       ddt.gpf_cpf gpf,\n" + "       ddt.govt_insurance govt_ins,\n"
				+ "       ddt.repay_hba HBA,ddt.emp_id,ddt.form16_dtl_id,ddt.Challannumber\n"
				+ "  from hr_form16_dtls ddt where ddt.fin_yr_id=" + year + ") a\n" + "         left outer join\n"
				+ "         (select sum(q.amount) AMT_PLI,\n" + "           q.invest_type_id,\n"
				+ "            q.emp_id\n" + "             from hr_invest_emp_dtls q where q.invest_type_id = 16\n"
				+ "             group by q.invest_type_id,\n" + "                     q.emp_id\n"
				+ "         ) b on a.emp_id = b.emp_id\n" + "         left outer join (select sum(q.amount) AMT_LIC,\n"
				+ "                   q.invest_type_id,\n" + "                q.emp_id\n"
				+ "                from hr_invest_emp_dtls q where q.invest_type_id = 12\n"
				+ "                 group by q.invest_type_id,\n" + "               q.emp_id\n"
				+ "          ) c on a.emp_id = c.emp_id left outer join (select sum(q.amount) AMT_NSC,\n"
				+ " q.invest_type_id,\n" + " q.emp_id\n" + " from hr_invest_emp_dtls q where q.invest_type_id = 20\n"
				+ "group by q.invest_type_id,\n" + " q.emp_id\n" + ") d on a.emp_id = d.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_PPF,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 14\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") e on a.emp_id = e.emp_id\n" + "left outer join (select sum(q.amount) AMT_ULIP,\n"
				+ " q.invest_type_id,\n" + " q.emp_id\n" + " from hr_invest_emp_dtls q where q.invest_type_id = 18\n"
				+ "group by q.invest_type_id,\n" + " q.emp_id\n" + ") f on a.emp_id = f.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_NSC_INT,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 22\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") g on a.emp_id = g.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_PENSION,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 24\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") h on a.emp_id = h.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_ANNUITY,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 26\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") i on a.emp_id = i.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_INFRASTR_BOND,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 28\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") j on a.emp_id = j.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_TUITION_FEE,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 30\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") k on a.emp_id = k.emp_id\n" + "left outer join (select sum(q.amount) AMT_EQUITY,\n"
				+ " q.invest_type_id,\n" + " q.emp_id\n" + " from hr_invest_emp_dtls q where q.invest_type_id = 32\n"
				+ "group by q.invest_type_id,\n" + " q.emp_id\n" + ") l on a.emp_id = l.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_PENS_SCHEME,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 34\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") m on a.emp_id = m.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_MUTUALFUND,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 36\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") n on a.emp_id = n.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_POST_OFF,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 38\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") o on a.emp_id = o.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_FIXD_DEP,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 40\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") p on a.emp_id = p.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_DEP_NHB,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 42\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") r on a.emp_id = r.emp_id\n"
				+ "left outer join (select sum(q.amount) AMT_EQUITY_SHARE,\n" + " q.invest_type_id,\n" + " q.emp_id\n"
				+ " from hr_invest_emp_dtls q where q.invest_type_id = 44\n" + "group by q.invest_type_id,\n"
				+ " q.emp_id\n" + ") s on a.emp_id = s.emp_id\n" + "left outer join (select sum(q.amount) AMT_NSS,\n"
				+ " q.invest_type_id,\n" + " q.emp_id\n" + " from hr_invest_emp_dtls q where q.invest_type_id = 46\n"
				+ "group by q.invest_type_id,\n" + " q.emp_id\n" + ") t on a.emp_id = t.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_MEDICLM,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 4\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") u on a.emp_id = u.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_MED_TREAT,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 6\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") v on a.emp_id = v.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_DONATION,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 8\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") w on a.emp_id = w.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_PERM_DISABL,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 10\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") x on a.emp_id = x.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_EDU_LOAN,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 12\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") y on a.emp_id = y.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_CHILD_EDU,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 13\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") z on a.emp_id = z.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_VEH_EXEMP,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 14\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") zz on a.emp_id = zz.emp_id\n"
				+ "left outer join (select sum(qq.amount) AMT_HRA_EXEMP,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 15\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") xx on a.emp_id = xx.emp_id\n"
				+ "left outer join hr_eis_emp_mst et on a.emp_id=et.emp_id \n"
				+ "left outer join org_emp_mst mt on et.emp_mpg_id=mt.emp_id \n"
				+ "left outer join hr_eis_proof_dtl pd on mt.user_id=pd.user_id and pd.prooftype_lookupid=300166\n"
				+ "left outer join (select rlt.post_id,rlt.user_id,dg.dsgn_name,lc.loc_name from org_userpost_rlt rlt,"
				+ "org_post_details_rlt dt,org_designation_mst dg,cmn_location_mst lc where lc.loc_id=dt.loc_id and "
				+ "dt.post_id=rlt.post_id and dg.dsgn_id=dt.dsgn_id and rlt.emp_post_id=(select max(rr.emp_post_id) "
				+ "from org_userpost_rlt rr where rr.user_id=rlt.user_id group by rr.user_id)) kk on kk.user_id=mt.user_id\n");
		for (int i = 1; i <= 14; i++) {
			strQuery.append(
					" left outer join (select ftd.form16_dtl_id,bmt.bank_name,ftd.deduc_dtls_date,ftd.income_tax from  "
							+ "hr_form16_tax_deduc_dtls ftd,hr_form16_bank_mst bmt where ftd.deduc_dtls_bank_id=bmt.bank_id "
							+ "and ftd.deduc_dtls_month=" + i + ") deduc_dtls" + i + " on deduc_dtls" + i
							+ ".form16_dtl_id=a.form16_dtl_id \n");
		}

		strQuery.append("left outer join (select sum(qq.amount) AMT_RELIEF_89,qq.emp_id\n"
				+ " from hr_it_exempt_emp_dtls qq where qq.itexempt_type_id= 17\n" + "group by qq.itexempt_type_id,\n"
				+ " qq.emp_id\n" + ") ss on a.emp_id = ss.emp_id\n");

		strQuery.append(" where pd.proof_num is not null and et.loc_id=" + locId);
		if (!empId.equals(""))
			strQuery.append(" and a.emp_id in (" + empId + ")");

		Query Qry = hibSession.createSQLQuery(strQuery.toString());
		logger.info("The query from getForm16Data of all employees is---->>>" + Qry);
		qryList = Qry.list();

		return qryList;

	}

	// Added by rahul- to solve issue when employees grade changes along with
	// bill no
	/*
	 * public List getPayBillGrpIDFromBillNo(long billType, String month, String
	 * year, String BillNo) { ArrayList dtlsList = new ArrayList(); Session
	 * hibSession = getSession(); StringBuffer strBfr = new StringBuffer();
	 * strBfr.append("     select distinct bhm.paybill_id\n");
	 * strBfr.append(" from paybill_head_mpg bhm, hr_pay_bill_subhead_mpg hpbsm\n");
	 * strBfr.append(" where bhm.approve_flag in (0, 1) and bhm.bill_Type_Id = " +
	 * billType + " and\n"); strBfr.append("      bhm.paybill_month = '" + month +
	 * "' and bhm.paybill_year = '" + year + "' and\n"); strBfr.
	 * append("      bhm.bill_no = hpbsm.bill_subhead_id and hpbsm.bill_no = " +
	 * BillNo + " ");
	 * 
	 * Query query = hibSession.createSQLQuery(strBfr.toString());
	 * 
	 * dtlsList = (ArrayList) query.list(); logger.info("dtlsList size is:::::::::"
	 * + dtlsList.size()); return dtlsList; }
	 */

	//
	public List getTotalHeadValue(long billNo, int month, int year, String columnName, String conditionValue) {
		ArrayList dtlsList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("select sum(pb.");
		strBfr.append(columnName);
		strBfr.append(")");
		strBfr.append(" from hr_pay_paybill pb where pb.paybill_grp_id in ");
		strBfr.append("(select hm.paybill_id from paybill_head_mpg hm where hm.paybill_month=");
		strBfr.append(month);
		strBfr.append(" and hm.paybill_year=");
		strBfr.append(year);
		strBfr.append(" and hm.approve_flag in(0,1) and hm.bill_no=");
		strBfr.append(billNo);
		strBfr.append(")");
		if (conditionValue != null && !conditionValue.trim().equals("")) {
			strBfr.append(" and pb.");
			strBfr.append(conditionValue);
		}
		Query query = hibSession.createSQLQuery(strBfr.toString());

		dtlsList = (ArrayList) query.list();
		logger.info("dtlsList size is:::::::::" + dtlsList.size());
		logger.info("Query for error check is:::::::::" + strBfr.toString());
		return dtlsList;
	}

	public List getPaybillEdpMpgList() {
		ArrayList lstPaybillEdpMpg = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("select EXP_RCP_REC,paybill_column,edp_code,condition_val from hr_pay_edp_mst");
		Query query = hibSession.createSQLQuery(strBfr.toString());

		lstPaybillEdpMpg = (ArrayList) query.list();
		logger.info("dtlsList size for edp column mapping is:::::::::" + lstPaybillEdpMpg.size());
		return lstPaybillEdpMpg;
	}

	public List getPaybillGrouopId(long billNo, int month, int year) {
		ArrayList lstPaybillEdpMpg = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("select paybill_id from paybill_head_mpg ph where ph.paybill_month=");
		strBfr.append(month);
		strBfr.append(" and ph.paybill_year=");
		strBfr.append(year);
		strBfr.append(" and ph.approve_flag in(0,1) and ph.bill_no=");
		strBfr.append(billNo);
		Query query = hibSession.createSQLQuery(strBfr.toString());

		lstPaybillEdpMpg = (ArrayList) query.list();
		logger.info("dtlsList size for edp column mapping is:::::::::" + lstPaybillEdpMpg.size());
		return lstPaybillEdpMpg;
	}

	/**
	 * Method will give the list of all the employees with employee id, post id for
	 * given month and year. Bill number will not be taken care as this is generic
	 * method, and bill number is not generic.
	 * 
	 * @author Ankit Bhatt
	 * @param billNo
	 * @param month
	 * @param year
	 * @return List of Changed employees for given month and year
	 */
	public List<HrPayChangedRecords> getChangedRecords(long billNo, int month, int year) {
		ArrayList<HrPayChangedRecords> lstChangedRecords = new ArrayList<HrPayChangedRecords>();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("from HrPayChangedRecords ph where ph.changedMonth=");
		strBfr.append(month);
		strBfr.append(" and ph.changedYear=");
		strBfr.append(year);
		Query query = hibSession.createQuery(strBfr.toString());

		lstChangedRecords = (ArrayList) query.list();
		logger.info("dtlsList size for edp column mapping is:::::::::" + lstChangedRecords.size());
		return lstChangedRecords;
	}

	/**
	 * Method will return the records from Paybill table according to the month,
	 * year, bill number and location ID. if vacantFlag = true then, List of only
	 * vacant Posts will be returned and if false, then all the records of given
	 * month an year will be returned accroding to the location id.
	 * 
	 * @param month
	 * @param year
	 * @param locId
	 * @param billNo
	 * @return List of Paybill record from hr_pay_paybill table.
	 */
	public List getPaybillDataByLoc(int month, int year, long locId, long billNo, boolean vacantFlag) {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("select pb.post_id,pb.id,pb.paybill_grp_id from hr_pay_paybill pb where pb.loc_id=");
		strBfr.append(locId);
		strBfr.append(" and pb.paybill_grp_id in ");
		strBfr.append("(select ph.paybill_id from paybill_head_mpg ph where ph.bill_no in ");
		strBfr.append(" (select mpg.bill_subhead_id from hr_pay_bill_subhead_mpg mpg where ");
		strBfr.append(" mpg.bill_subhead_id=");
		strBfr.append(billNo);
		strBfr.append(" and mpg.loc_id=");
		strBfr.append(locId);
		strBfr.append(") and ph.paybill_month=");
		strBfr.append(month);
		strBfr.append(" and ph.paybill_year=");
		strBfr.append(year);
		strBfr.append(" and ph.approve_flag=4)");
		if (vacantFlag)
			strBfr.append(" and pb.emp_id is null ");
		else
			strBfr.append(" and pb.emp_id is not null ");
		Query query = hibSession.createSQLQuery(strBfr.toString());
		return query.list();
	}

	/**
	 * Method will delete the record from hr_pay_paybill and paybill_head_mpg tables
	 * based on given month, year and post Id. It will delete record which has
	 * approve flag 0 in paybill_head_mpg table
	 * 
	 * @param postId
	 * @param month
	 * @param year
	 * @return rowCount of Deleted Rows
	 */
	public int deletePaybillRecord(long postId, int month, int year) throws Exception {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("delete from hr_pay_payslip_non_govt where paybill_id in ");
		strBfr.append("(select id from hr_pay_paybill pb where pb.paybill_grp_id in ");
		strBfr.append("(select paybill_id from paybill_head_mpg where paybill_month=");
		strBfr.append(month);
		strBfr.append(" and paybill_year=");
		strBfr.append(year);
		strBfr.append(" and approve_flag=4) and ");
		strBfr.append(" pb.post_id = ");
		strBfr.append(postId);
		strBfr.append(")");
		logger.info("Hummmmmmmm query is " + strBfr.toString());
		Query query = hibSession.createSQLQuery(strBfr.toString());
		int rowsDeleted = query.executeUpdate();

		strBfr = new StringBuffer();
		strBfr.append("delete from hr_pay_paybill where paybill_grp_id in ");
		strBfr.append(" (select paybill_id from paybill_head_mpg where paybill_month=");
		strBfr.append(month);
		strBfr.append(" and paybill_year=");
		strBfr.append(year);
		strBfr.append(" and approve_flag=4) and ");
		strBfr.append(" post_id = ");
		strBfr.append(postId);
		query = hibSession.createSQLQuery(strBfr.toString());
		rowsDeleted += query.executeUpdate();

		return rowsDeleted;
		// return 0;

	}

	/**
	 * Method will update the approve_flag of paybill_head_mpg table.
	 * 
	 * @param paybillGrpId
	 * @return
	 */
	public int updatePaybillHeadMpg(long paybillGrpId) {
		logger.info("Inside update paybill by vaibhav");
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("update paybill_head_mpg ph set ph.approve_flag=0,ph.voucher_no=0 where ph.paybill_id=");
		strBfr.append(paybillGrpId);
		Query updateQuery = hibSession.createSQLQuery(strBfr.toString());
		return updateQuery.executeUpdate();
	}

	/**
	 * Method will read the Paybill Table for given month year, post Id and approve
	 * flag of paybill.
	 * 
	 * @param paybillGrpId
	 * @return
	 */
	public HrPayPaybill getPaybillDataByPost(long postId, int approvalFlag, int month, int year) {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("from HrPayPaybill as pb where pb.paybillGrpId in");
		strBfr.append("(select hm.hrPayPaybill from PaybillHeadMpg as hm where hm.month=");
		strBfr.append(month);
		strBfr.append(" and hm.year=");
		strBfr.append(year);
		strBfr.append(" and hm.approveFlag=");
		strBfr.append(approvalFlag);
		strBfr.append(")");
		strBfr.append(" and pb.orgPostMst.postId=");
		strBfr.append(postId);
		logger.info("query to read paybill object is " + strBfr.toString());
		Query updateQuery = hibSession.createQuery(strBfr.toString());
		Object paybillObject = updateQuery.uniqueResult();
		if (paybillObject != null)
			return (HrPayPaybill) updateQuery.uniqueResult();
		else
			return null;
	}

	/**
	 * Method will read the Paybill Table for given month year, post Id and approve
	 * flag of paybill.
	 * 
	 * @param paybillGrpId
	 * @return
	 */
	public HrPayPaybill getPaybillDataByEmpId(long empId, int approvalFlag, int month, int year) {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("from HrPayPaybill as pb where pb.paybillGrpId in");
		strBfr.append("(select hm.hrPayPaybill from PaybillHeadMpg as hm where hm.month=");
		strBfr.append(month);
		strBfr.append(" and hm.year=");
		strBfr.append(year);
		strBfr.append(" and hm.approveFlag=");
		strBfr.append(approvalFlag);
		strBfr.append(")");
		strBfr.append(" and pb.hrEisEmpMst.orgEmpMst.empId=");
		strBfr.append(empId);
		logger.info("query to read paybill object is " + strBfr.toString());
		Query updateQuery = hibSession.createQuery(strBfr.toString());
		Object paybillObject = updateQuery.uniqueResult();
		if (paybillObject != null)
			return (HrPayPaybill) updateQuery.uniqueResult();
		else
			return null;
	}

	public List getChangedRecords(int month, int year, long postId, long locId) {

		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("select * from HR_PAY_CHANGED_RECORDS hpcr where hpcr.POST_ID=" + postId + " and hpcr.LOC_ID="
				+ locId + " and hpcr.CHANGE_MONTH=" + month + " and hpcr.CHANGE_YEAR=" + year);
		logger.info("query for checking duplicate records in hr_pay_changed_records::::::::" + strBfr.toString());
		Query query = hibSession.createSQLQuery(strBfr.toString());
		logger.info("query executed::::: " + query.list().size());
		return query.list();
	}

	/**
	 * Method will return the records from Paybill table according to the month,
	 * year, bill number and location ID.
	 * 
	 * @param month
	 * @param year
	 * @param locId
	 * @param billNo
	 * @return List of Paybill record from hr_pay_paybill table.
	 */
	public List getAllPaybillDataByLoc(int month, int year, long locId, long billNo, int approveFlag) {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("from HrPayPaybill pb where pb.cmnLocationMst.locId=");
		strBfr.append(locId);
		strBfr.append("and pb.paybillGrpId in ");
		strBfr.append("(select ph.hrPayPaybill from PaybillHeadMpg ph where ph.billNo in ");
		strBfr.append("(select mpg.dcpsDdoBillGroupId from MstDcpsBillGroup mpg where ");
		strBfr.append("mpg.dcpsDdoBillGroupId=");
		strBfr.append(billNo);
		strBfr.append(" and mpg.LocId=");
		strBfr.append(locId);
		strBfr.append(") and ph.month=");
		strBfr.append(month);
		strBfr.append("and ph.year=");
		strBfr.append(year);
		strBfr.append("and ph.approveFlag=");
		strBfr.append(approveFlag);
		strBfr.append(")");
		Query query = hibSession.createQuery(strBfr.toString());
		return query.list();
	}

	/**
	 * Method will delete the record from hr_pay_paybill_loan_dtls table based on
	 * given Paybill Id and Loan type id.
	 * 
	 * @param paybillId
	 *            - long
	 * @param Loan
	 *            type ID - String - Optional, if we pass "" as loan type id then
	 *            all records for the Paybill ID will be deleted.
	 * @return rowCount of Deleted Rows
	 */
	public int deletePaybillLoanRecord(long paybillId, String loanTypeIds) throws Exception {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("delete from hr_pay_paybill_loan_dtls where paybill_id=");
		strBfr.append(paybillId);
		if (!loanTypeIds.trim().equals("")) {
			strBfr.append("and loan_type_id in(");
			strBfr.append(loanTypeIds);
			strBfr.append(")");
		}
		Query query = hibSession.createSQLQuery(strBfr.toString());
		return query.executeUpdate();
	}

	/**
	 * Method will delete the record from paybill_head_mpg table based on given
	 * Paybill Id.
	 * 
	 * @param paybillId
	 *            - long
	 * @return rowCount of Deleted Rows
	 */
	public int deletePaybillHeadRecord(long paybillId) throws Exception {
		logger.info("Inside delete paybill head mpg method");
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("delete from paybill_head_mpg where paybill_id=");
		strBfr.append(paybillId);
		strBfr.append(" and approve_flag=4");
		Query query = hibSession.createSQLQuery(strBfr.toString());
		return query.executeUpdate();
	}

	/**
	 * Method will return the records from Paybill table according to the month,
	 * year, bill number and location ID. if vacantFlag = true then, List of only
	 * vacant Posts will be returned and if false, then all the records of given
	 * month an year will be returned accroding to the location id.
	 * 
	 * @param month
	 * @param year
	 * @param locId
	 * @param billNo
	 * @return List of Paybill record from hr_pay_paybill table.
	 */
	public List getPaybillDataByLoc(int month, int year, long locId, long billNo, boolean vacantFlag, int approveFlag) {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("select pb.post_id,pb.id,pb.paybill_grp_id from hr_pay_paybill pb where pb.loc_id=");
		strBfr.append(locId);
		strBfr.append(" and pb.paybill_grp_id in ");
		strBfr.append("(select ph.paybill_id from paybill_head_mpg ph where ph.bill_no in ");
		strBfr.append("(select mpg.bill_group_id from mst_dcps_bill_group mpg where ");
		strBfr.append(" mpg.bill_group_id=");
		strBfr.append(billNo);
		strBfr.append(" and mpg.loc_id=");
		strBfr.append(locId);
		strBfr.append(") and ph.paybill_month=");
		strBfr.append(month);
		strBfr.append(" and ph.paybill_year=");
		strBfr.append(year);
		strBfr.append(" and ph.approve_flag=");
		strBfr.append(approveFlag);
		strBfr.append(")");
		if (vacantFlag)
			strBfr.append(" and pb.emp_id is null ");
		else
			strBfr.append(" and pb.emp_id is not null ");
		Query query = hibSession.createSQLQuery(strBfr.toString());
		return query.list();
	}

	// Added by abhilash

	public boolean checkPaybill(int month, int year, long billno, long locId, String billTypeLookupId) {

		List paybillData = new ArrayList();
		int size = 0;
		Session hibSession = getSession();
		logger.info("In the first checkPaybill ");
		// this is the Check For Bill generated or not
		StringBuffer strQuery = new StringBuffer(
				"select * from hr_pay_paybill mpg where mpg.id in (select hm.PAYBILL_ID from PAYBILL_HEAD_MPG hm where hm.PAYBILL_MONTH= "
						+ month + " and hm.PAYBILL_YEAR= " + year + " and hm.APPROVE_FLAG in (0,1) and hm.LOC_ID= "
						+ locId + " and hm.BILL_NO= " + billno + " 	" + ")");
		logger.info(strQuery);
		// strQuery.append(" ))");
		logger.info("Query in checkPaybillData is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		paybillData = query.list();
		size = paybillData.size();
		logger.info("sizee is===>" + size);
		logger.info("Paybill data is " + paybillData);
		logger.info("Size is " + paybillData.size());

		if (size <= 0)
			return false;
		else
			return true;

	}

	// Added by abhilash for maharashtra

	/*
	 * public boolean checkPaybill(int month, int year, long billno, long locId) {
	 * logger.info("In theSecond checkPaybill "); List paybillData = new
	 * ArrayList(); int size = 0; Session hibSession = getSession();
	 * 
	 * // this is the Check For Bill generated or not StringBuffer strQuery = new
	 * StringBuffer("select * from hr_pay_paybill mpg where mpg.id in (select hm.PAYBILL_ID from PAYBILL_HEAD_MPG hm where hm.PAYBILL_MONTH= "
	 * + month + " and hm.PAYBILL_YEAR= " + year +
	 * " and hm.APPROVE_FLAG in (0,1) and hm.LOC_ID= " + locId + " and hm.BILL_NO= "
	 * + billno + " 	" + ")"); logger.info(strQuery); //strQuery.append(" ))");
	 * logger.info("Query in checkPaybillData is " + strQuery.toString()); Query
	 * query = hibSession.createSQLQuery(strQuery.toString()); paybillData =
	 * query.list(); size = paybillData.size(); logger.info("sizee is===>" + size);
	 * logger.info("Paybill data is " + paybillData); logger.info("Size is " +
	 * paybillData.size());
	 * 
	 * if (size <= 0) return false; else return true;
	 * 
	 * }
	 */

	public boolean checkPaybill(int month, int year, long billno, long locId) {

		List paybillData = new ArrayList();
		int size = 0;
		Session hibSession = getSession();

		// this is the Check For Bill generated or not
		StringBuffer strQuery = new StringBuffer(
				"select * from hr_pay_paybill mpg where mpg.id in (select hm.PAYBILL_ID from PAYBILL_HEAD_MPG hm where hm.PAYBILL_MONTH= "
						+ month + " and hm.PAYBILL_YEAR= " + year + " and hm.APPROVE_FLAG <>3 and hm.LOC_ID= " + locId
						+ " and hm.BILL_NO= " + billno + " 	" + ")");
		logger.info(strQuery);
		// strQuery.append(" ))");
		logger.info("Query in checkPaybillData is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		paybillData = query.list();
		size = paybillData.size();
		logger.info("sizee is===>" + size);
		logger.info("Paybill data is " + paybillData);
		logger.info("Size is " + paybillData.size());

		if (size <= 0)
			return false;
		else
			return true;

	}

	public Long checkPreviousPaybill(int month, int year, long billno, long locId) {

		List paybillData = new ArrayList();
		Long size = 0L;// it was int

		Long approveFlag = -0L;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from PAYBILL_HEAD_MPG hm where  concat(hm.PAYBILL_YEAR,hm.PAYBILL_month) <> '" + year
				+ "" + month + "'");
		sb.append("and hm.APPROVE_FLAG not in (1,3) and hm.paybill_year>2015  and hm.LOC_ID= " + locId + "  ");
		sb.append("and hm.BILL_NO= " + billno
				+ " and hm.LOC_ID not in	('4000840','4000841','1494','1495','1498','4000230','4000238','4000247','4000254','4000261','4000273', ");
		sb.append("'4000305','4000314','4000434','4000440','4000443','4000449','4000452','4000458','4003040', ");
		sb.append("'4003041','4000838','4001532','4001539','4001542','4000279' )");
		// this is the Check For Bill generated or n commented by poonam for 7101002011
		// paybill validation
		// StringBuffer strQuery = new StringBuffer("select
		// hm.PAYBILL_ID,hm.APPROVE_FLAG from PAYBILL_HEAD_MPG hm where
		// hm.PAYBILL_MONTH= " + month + " and hm.PAYBILL_YEAR= " + year + " and
		// hm.APPROVE_FLAG in (0,1) and hm.LOC_ID= " + locId + " and hm.BILL_NO= " +
		// billno + " ");
		// StringBuffer strQuery = new StringBuffer(
		/*
		 * "select count(1) from PAYBILL_HEAD_MPG hm where  concat(hm.PAYBILL_YEAR,hm.PAYBILL_month) <> '"
		 * +year+""+month+"' " +
		 * " and hm.APPROVE_FLAG not in (1,3) and hm.paybill_year>2015  and hm.LOC_ID= "
		 * + locId + " and hm.BILL_NO= " + billno + " and hm.LOC_ID not in
		 * ('4000840','4000841','1494','1495','1498','4000230','4000238','4000247','
		 * 4000254','4000261','4000273',
		 * '4000279','4000305','4000314','4000434','4000440','4000443','4000449','
		 * 4000452','4000458','4003040',
		 * '4003041','4000838','4001532','4001539','4001542')");
		 * 
		 */
		// commented by poonam
		// logger.info("Query for check previous bill is:"+sb.toString());
		// strQuery.append(" ))");
		logger.info("Query in checkPreviousPaybill is " + sb.toString());
		Query query = hibSession.createSQLQuery(sb.toString());
		paybillData = query.list();

		size = Long.parseLong(query.uniqueResult().toString());// 1 added by poonam
		// commented by poonam for 1 paybill validation
		/*
		 * size = paybillData != null ? paybillData.size() : 0;
		 * 
		 * //-1 = bill not generated //0 = bill generated but not yet approved //1 =
		 * bill has been approved
		 * 
		 * Long approveFlag = -1L; if (size <= 0) {
		 * 
		 * 
		 * approveFlag = -1L; } else { Object[] data = (Object[]) paybillData.get(0); if
		 * (data != null && data.length > 1 && data[1] != null) { approveFlag =
		 * ((BigInteger) data[1]).longValue(); }
		 * 
		 * }1 whole
		 */

		if (size == 0) {
			approveFlag = 1L;
		} // added by poonam for 1 paybill validation

		return approveFlag;
	}

	// end

	public boolean checkPaybillForOuter(int month, int year, long billno, long locId) {

		List paybillData = new ArrayList();
		int size = 0;
		Session hibSession = getSession();

		// this is the Check For Bill generated or not-
		StringBuffer strQuery = new StringBuffer(
				"select * from PAYBILL_BILLREG_MPG mpg where mpg.PAYBILL_ID in (select hm.PAYBILL_ID from PAYBILL_HEAD_MPG hm where hm.PAYBILL_MONTH= "
						+ month + " and hm.PAYBILL_YEAR= " + year + " and hm.APPROVE_FLAG in (0,1) and hm.LOC_ID= "
						+ locId + " and hm.BILL_NO= " + billno + " 	" + ")");
		logger.info(strQuery);
		// strQuery.append(" ))");
		logger.info("Query in checkPaybillData is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		paybillData = query.list();
		size = paybillData.size();
		logger.info("sizee is===>" + size);
		logger.info("Paybill data is " + paybillData);
		logger.info("Size is " + paybillData.size());

		if (size <= 0)
			return false;
		else
			return true;

	}

	public List hrPayPayBillId(long month, long year, long locId, long billNo) {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		List list = null;
		// int rowsDeleted = query.executeUpdate();
		// List paybillData = new ArrayList();
		// int size = 0;

		strBfr = new StringBuffer();
		strBfr.append("select * from hr_pay_paybill pb where pb.id in ");
		strBfr.append("(select head.paybill_id from paybill_head_mpg head where head.paybill_month=");
		strBfr.append(month);
		strBfr.append(" and head.paybill_year=");
		strBfr.append(year);
		strBfr.append(" and head.loc_id=");
		strBfr.append(locId);
		strBfr.append(" and head.bill_no=");
		strBfr.append(billNo);
		strBfr.append(" and head.approve_flag=4");
		strBfr.append(")");

		Query query = hibSession.createSQLQuery(strBfr.toString());

		list = query.list();
		// size = list.size();
		logger.info("List getAllEmployyTypeData size in DAO:-->" + list.size());
		return list;
	}

	public int deleteRecords(long Id) {

		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("delete from paybill_head_mpg head where head.paybill_id=" + Id);

		Query query = hibSession.createSQLQuery(strBfr.toString());
		int rowsDeleted = query.executeUpdate();

		strBfr = new StringBuffer();
		strBfr.append("delete from hr_pay_paybill paybill where paybill.id=" + Id);

		query = hibSession.createSQLQuery(strBfr.toString());
		rowsDeleted += query.executeUpdate();

		return rowsDeleted;

	}

	public long getSupplBillNoForOuter(int month, int year, long billno, long locId) {
		// DBsysdateConfiguration sbConf = new DBsysdateConfiguration();

		// String getNVL = sbConf.getNVL();
		long lSupplBillNo = 0;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//
		// select phm.hrPayPaybill from PaybillHeadMpg phm where
		// phm.billNo="+billno+
		// " and phm.month="+month+
		// " and phm.year="+year+" and phm.approveFlag<2 and ")
		//
		strQuery.append(" select max(phm.billTypeId.lookupId) ");
		strQuery.append(" from PaybillHeadMpg phm  where phm.billNo=" + billno + " and ");
		strQuery.append(" phm.month=" + month + " and phm.year=" + year + "  and phm.approveFlag=0 and ");
		strQuery.append(" phm.cmnLocationMst.locId=" + locId + " ");

		logger.info("Query in getSupplBillNo is " + strQuery.toString());

		Query query = hibSession.createQuery(strQuery.toString());

		if (query.list() != null && query.list().size() == 1 && query.list().get(0) != null)
			lSupplBillNo = Long.valueOf(query.list().get(0).toString());

		return lSupplBillNo;
	}

	// ended by abhilash
	// Ended

	public List getPostId(long userID) {

		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("select  rr.post_id from  ORG_USERPOST_RLT  rr where rr.USER_ID=" + userID);
		logger.info("query for finding post id based on user id::::::::::::::::" + strBfr.toString());
		Query query = hibSession.createSQLQuery(strBfr.toString());
		return query.list();
	}

	// Added by Abhilash For Scheme
	public List getSubHeadByMnrHeadandFin1(String demandNo, String mjrHead_Code, String subMjrHeadCode,
			String mnrHeadCode, String langName, int FIN_YR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(
					"select a1.budsubhdId,a1.budsubhdCode,a1.budsubhdDescLong from SgvaBudsubhdMst a1 where a1.budsubhdId in( ");
			query.append(
					" select a.budsubhdId from SgvaBudsubhdMst a where a.budsubhdId = a1.budsubhdId and a.demandCode='")
					.append(demandNo);
			query.append("' and a.budmjrhdCode='").append(mjrHead_Code).append("' and a.budsubmjrhdCode='")
					.append(subMjrHeadCode);
			query.append("' and a.budminhdCode='").append(mnrHeadCode).append("' and a.langId='").append(langName)
					.append("' and a.finYrId=").append(FIN_YR_ID).append(" )");
			// logger.info("Query for Sub Head is " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			// logger.info("List size for Sub Head is " +
			// resList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	// Ended by Abhilash

	// Added by Abhilash For Scheme
	public List getHeadChargable(String subHead, String langName, long FIN_YEAR_ID) {
		List resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(
					"select concat(concat(concat(concat(concat(concat(concat(concat(concat(o.DEMAND_CODE,''), o.BUDMJRHD_CODE),''),''),o.BUDSUBMJRHD_CODE),''),o.BUDMINHD_CODE),''),o.BUDSUBHD_CODE) from SGVA_BUDSUBHD_MST o where o.BUDSUBHD_ID='")
					.append(subHead).append(" ");
			query.append("' and o.LANG_ID='").append(langName).append("' and o.FIN_YR_ID=").append(FIN_YEAR_ID);

			Query sqlQuery = hibSession.createSQLQuery(query.toString());
			resList = sqlQuery.list();
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return resList;
	}

	// Ended by Abhilash

	// added by japen
	public List<HrPayPaybill> getBillData(long locId, long billNo, long month, long year) {
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select pb from HrPayPaybill pb,PaybillHeadMpg ph where pb.paybillGrpId = ph.hrPayPaybill and ");
		query.append(
				"ph.billNo.dcpsDdoBillGroupId = :billNo and ph.month = :month and ph.year = :year and ph.approveFlag in (0,1) ");
		query.append("and pb.hrEisEmpMst.empId is not null ");
		logger.info("Query from PaybillDAO " + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		sqlQuery.setParameter("billNo", billNo);
		sqlQuery.setParameter("month", Double.parseDouble(String.valueOf(month)));
		sqlQuery.setParameter("year", Double.parseDouble(String.valueOf(year)));
		return sqlQuery.list();
	}

	public Map getBillDataForInner(long locId, long billNo, long month, long year, long parentLocId) {
		ArrayList<HrPayPaybill> resList = new ArrayList<HrPayPaybill>();
		Map innerMap = new HashMap();
		Map postGroupMap = new HashMap();
		Map postDesigMap = new HashMap();
		boolean flag = false;
		String dstnStr = "";
		int count = 0;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			/*
			 * query.
			 * append("  select distinct(pb),lookUp.lookupName ,rltGrp.dcpsClassGroup,dcpsDesig.desigCode,coalesce(org.empLname,'z') from  HrPayPaybill pb left outer join pb.hrEisEmpMst as  eis  left outer join "
			 * +
			 * "  eis.orgEmpMst as org left outer join pb.orgPostMst.postTypeLookupId as lookUp ,OrgDesignationMst dm,OrgPostDetailsRlt pd,RltBillgroupClassgroup rltGrp   "
			 * ); query.append(",MstDcpsDesignation dcpsDesig where ");
			 * query.append("( pb.cmnLocationMst.locId = " + locId + " and");
			 * query.append("  pb.paybillGrpId in "); query.
			 * append(" (select ph.hrPayPaybill from PaybillHeadMpg ph where ph.billNo in "
			 * ); query.
			 * append(" (select mpg.dcpsDdoBillGroupId from MstDcpsBillGroup mpg where mpg.dcpsDdoBillGroupId = "
			 * + billNo); query.append(" and   mpg.LocId= " + locId + ")");
			 * query.append(" and ph.month=" + month); query.append(" and ph.year= " +
			 * year); query.append(" and ph.approveFlag in(0,1))) "); query.
			 * append(" and pd.orgPostMst.postId=pb.orgPostMst.postId and pd.orgDesignationMst.dsgnId=dm.dsgnId"
			 * ); query.append(" and  rltGrp.dcpsBillGroupId = " + billNo);
			 * query.append(" and dm.dsgnId=dcpsDesig.orgDesignationId ");
			 * query.append(" and dcpsDesig.fieldDeptId=" + parentLocId); query.
			 * append(" order by lookUp.lookupName ,rltGrp.dcpsClassGroup,dcpsDesig.desigCode,"
			 * ); query.append("coalesce(org.empLname,'z')");
			 */
			query.append(
					" select distinct(pb),lookUp.lookupName ,rltGrp.dcpsClassGroup,dcpsDesig.dsgnCode,pb.empLname,pd.orgPostMst.postId,pd.orgDesignationMst.dsgnId,pd.orgDesignationMst.dsgnName from  HrPayPaybill pb left outer join pb.hrEisEmpMst as  eis  left outer join "
							+ "  eis.orgEmpMst as org left outer join pb.orgPostMst.postTypeLookupId as lookUp ,OrgPostDetailsRlt pd,RltBillgroupClassgroup rltGrp   ");
			// query.append(",MstPayrollDesignationMst dcpsDesig,HrEisSgdMpg sgdMpg ,
			// HrEisGdMpg gd ,OrgGradeMst grade,PaybillHeadMpg ph where ");
			query.append(
					",MstPayrollDesignationMst dcpsDesig, HrEisGdMpg gd ,OrgGradeMst grade,PaybillHeadMpg ph where ");
			query.append(" ph.billNo=" + billNo);
			query.append(" and ph.month=" + month);
			query.append(" and ph.year= " + year);
			query.append(" and ph.approveFlag in(0,1,2) ");
			query.append("  and pb.paybillGrpId =ph.hrPayPaybill ");
			query.append(" and pd.orgPostMst.postId=pb.orgPostMst.postId ");
			query.append(" and  rltGrp.dcpsBillGroupId = " + billNo);
			query.append(
					" and  rltGrp.dcpsClassGroup = grade.gradeName and gd.orgGradeMst.gradeId=grade.gradeId and gd.orgDesignationMst.dsgnId=pd.orgDesignationMst.dsgnId and ");
			query.append("   gd.cmnLocationMst.locId = ");
			query.append(locId);
			// query.append(" and sgdMpg.hrEisGdMpg.gdMapId= gd.gdMapId " );
			query.append(" and pd.orgDesignationMst.dsgnId=dcpsDesig.orgDesignationId ");
			query.append(" and dcpsDesig.fieldDeptId=" + parentLocId);
			query.append(" order by lookUp.lookupName ,rltGrp.dcpsClassGroup,dcpsDesig.dsgnCode,");
			query.append("pb.empLname");

			logger.info("Query from PaybillDAO " + query.toString());
			logger.info("Manis hhere query for getting bill data in inner is " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			List lst = sqlQuery.list();
			logger.info("Query size" + lst.size());
			int size = lst.size();
			// System.out.println("Java logic started");
			for (int i = 0; i < size; i++) {

				Object[] obj = (Object[]) lst.get(i);
				resList.add((HrPayPaybill) obj[0]);
				if (obj[1] != null && obj[1].toString().equalsIgnoreCase("Temporary") && count == 0) {
					flag = true;
					count = 1;
					// break;
				}
				dstnStr = obj[6].toString() + "," + obj[7].toString();
				postGroupMap.put(obj[5].toString(), obj[3]);
				postDesigMap.put(obj[5].toString(), dstnStr);
			}

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());

		}

		innerMap.put("flag", flag);
		innerMap.put("paybillVoLst", resList);
		innerMap.put("postGroupMap", postGroupMap);
		innerMap.put("postDesigMap", postDesigMap);
		return innerMap;

	}

	public List getPayBillVOByIdList(List paybillIdList, long ParentLocId) {
		Session hibSession = getSession();
		ArrayList tls = new ArrayList();
		for (int q = 0; q < paybillIdList.size(); q++) {
			String strQuery = " from HrPayPaybill pb where pb.id in (" + paybillIdList.get(q) + ") ";
			Query query = hibSession.createQuery(strQuery);
			HrPayPaybill bp = (HrPayPaybill) query.list().get(0);
			tls.add(bp);
		}
		return tls;
	}

	public List<OrgPostDetailsRlt> getTotalPost(long dsgnId, long billNo, long locId, boolean postFlag, String group,
			String givenDate) {

		// long count=0;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" select distinct pd from  OrgPostDetailsRlt pd ,HrPayPsrPostMpg psr ");
		if (group != null && !group.equals("")) {
			query.append(",RltBillgroupClassgroup rltGrp,OrgGradeMst grade,HrEisGdMpg gd");
		}
		// ,RltBillgroupClassgroup rltGrp
		query.append(" where pd.orgDesignationMst.dsgnId =" + dsgnId);
		query.append(" and pd.orgPostMst.postId=psr.postId and psr.billNo=" + billNo + " and psr.loc_id=" + locId);
		if (group != null && !group.equals("")) {
			// query.append(" and rltGrp.dcpsClassGroup='"+group+"' and
			// rltGrp.dcpsBillGroupId =" + billNo);
			// query.append(" and gis.hrEisEmpMst.orgEmpMst.orgUserMst.userId=
			// userpost.orgUserMst.userId");
			// query.append(" and gis.orgGradeMst.gradeName='"+group+"'");
			// query.append(" and userpost.orgPostMstByPostId.postId=psr.postId ");
			query.append(" and gd.cmnLocationMst.locId=" + locId
					+ " and gd.orgDesignationMst.dsgnId=pd.orgDesignationMst.dsgnId");
			query.append(" and gd.orgGradeMst.gradeId=grade.gradeId and rltGrp.dcpsClassGroup=grade.gradeName");
			query.append(" and rltGrp.dcpsBillGroupId=" + billNo);
		}
		if (postFlag == false) {
			long id = 10001198129L;
			// for statutory posts with permnant posts
			// query.append(" and pd.orgPostMst.postTypeLookupId.lookupId=" + id + " and
			// pd.orgPostMst.endDate is null");
			query.append(
					" and pd.orgPostMst.postTypeLookupId.lookupId in (10001198129,10001198155) and  pd.orgPostMst.endDate is null");
		} else {
			long id = 10001198130L;
			query.append(" and pd.orgPostMst.postTypeLookupId.lookupId=" + id + " and  pd.orgPostMst.endDate >= '"
					+ givenDate + "'");
		}
		Query sqlQuery = hibSession.createQuery(query.toString());
		List list = sqlQuery.list();
		// if(list!=null && list.size()>0)
		// count= Integer.parseInt(list.get(0).toString());

		// return count;
		logger.info("check this query for OrgPostDetailsRlt getVacantPost " + query.toString());
		logger.info("size is OrgPostDetailsRlt getVacantPost " + list.size());
		return list;
	}

	public List getTotalPostNew(long dsgnId, long billNo, long locId, boolean postFlag, String group,
			String givenDate) {
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" select  pd.POST_ID from  ORG_POST_DETAILS_RLT pd ,HR_PAY_POST_PSR_MPG psr ");
		query.append(",RLT_DCPS_BILLGROUP_CLASSGROUP rltGrp,ORG_GRADE_MST grade,HR_EIS_GD_MPG gd, orG_post_mst post ");
		query.append(" where pd.DSGN_ID=" + dsgnId);
		query.append(" and pd.post_Id=psr.post_Id and psr.bill_No=" + billNo + " and psr.loc_id=" + locId);
		query.append(" and gd.loc_Id=" + locId + " and gd.SGD_DESIG_ID=pd.dsgn_Id");
		query.append(" and gd.SGD_GRADE_ID=grade.grade_Id and rltGrp.dcps_Class_Group=grade.grade_Name");
		query.append(" and rltGrp.dcps_BillGroup_Id=" + billNo);
		query.append(" and pd.post_id=post.post_id");
		if (postFlag == false)
			// for permanant and statutory posts
			// query.append(" and post.POST_TYPE_LOOKUP_ID=" + 10001198129L + " and
			// post.end_Date is null ");
			query.append(" and post.POST_TYPE_LOOKUP_ID in (10001198129,10001198155) and  post.end_Date is null ");
		else
			query.append(" and post.POST_TYPE_LOOKUP_ID=" + 10001198130L + " and post.end_Date >= '" + givenDate + "'");

		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		List list = sqlQuery.list();

		logger.info("check this query for OrgPostDetailsRlt getVacantPost old" + query.toString());
		logger.info("size is OrgPostDetailsRlt getVacantPost old " + list.size());
		return list;
	}

	// by javed
	public List<OrgPostDetailsRlt> getTotalPost(long dsgnId, long billNo, long locId) {
		// long count=0;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" select pd from  OrgPostDetailsRlt pd ,HrPayPsrPostMpg psr where pd.orgDesignationMst.dsgnId ="
				+ dsgnId);
		query.append(" and pd.orgPostMst.postId=psr.postId and psr.billNo=" + billNo + " and psr.loc_id=" + locId);
		/*
		 * if(postFlag == false) { long id = 10001198129L;
		 * //query.append(" and pd.orgPostMst.cmnLookupMst.lookupId="+ new
		 * Long(10001198129)); query.append(" and pd.orgPostMst.cmnLookupMst.lookupId="+
		 * id); } else { long id = 10001198130L;
		 * //query.append(" and pd.orgPostMst.cmnLookupMst.lookupId="+ new
		 * Long(10001198130)); query.append(" and pd.orgPostMst.cmnLookupMst.lookupId="+
		 * id); }
		 */
		Query sqlQuery = hibSession.createQuery(query.toString());
		List list = sqlQuery.list();
		// if(list!=null && list.size()>0)
		// count= Integer.parseInt(list.get(0).toString());

		// return count;
		logger.info("check this query for OrgPostDetailsRlt getVacantPost " + query.toString());
		logger.info("size is OrgPostDetailsRlt getVacantPost " + list.size());
		return list;
	}

	/*
	 * public long getVacantPost(List<OrgPostDetailsRlt> postList, long month, long
	 * year, long locId, long paybillGrpId) { long count = 0;
	 * 
	 * Session hibSession = getSession();
	 * 
	 * //query.
	 * append(" select count(pd) from  OrgPostDetailsRlt pd ,HrPayPsrPostMpg psr,PaybillHeadMpg headMpg where pd.dsgnId ="
	 * +dsgnId);
	 * //query.append(" and pd.orgPostMst.postId=psr.postId and psr.billNo="+billNo
	 * +" and psr.loc_id="+locId); //query.append(" headMpg.billNo="+ billNo+
	 * " and headMpg.hrPayPaybill"); for (int i = 0; i < postList.size(); i++) {
	 * StringBuffer query = new StringBuffer();
	 * query.append("from  HrPayPaybill pb where pb.orgPostMst.postId=" +
	 * postList.get(i).getOrgPostMst().getPostId());
	 * //query.append(" and pb.month="+month+ " and pb.year="+year);
	 * query.append(" and pb.paybillGrpId=" + paybillGrpId);
	 * query.append(" and pb.hrEisEmpMst.empId is null"); Query sqlQuery =
	 * hibSession.createQuery(query.toString());
	 * logger.info(" check this query for getVacantPost " + query.toString()); List
	 * list = sqlQuery.list(); if (list != null && list.size() > 0) count += 1; }
	 * 
	 * logger.info("count before returning is " + count);
	 * 
	 * return count; }
	 */
	public long getVacantPost(List postList, long month, long year, long locId, long paybillGrpId) {
		long count = 0;

		Session hibSession = getSession();
		String postIdStr = "";
		int size = postList.size();
		size -= 1;
		int i;
		logger.info(" check this query for postList " + postList.size());
		for (i = 0; i < size; i++) {
			postIdStr += Long.valueOf(postList.get(i).toString()) + ",";
		}
		logger.info(" check this query for postList i " + i);
		postIdStr += Long.valueOf(postList.get(i).toString());

		StringBuffer query = new StringBuffer();
		query.append("from  HrPayPaybill pb where pb.orgPostMst.postId in (" + postIdStr + ")");
		query.append(" and pb.paybillGrpId=" + paybillGrpId);
		query.append(" and pb.hrEisEmpMst.empId is null");
		Query sqlQuery = hibSession.createQuery(query.toString());

		/*
		 * query.append("SELECT  count(*) FROM HR_PAY_PAYBILL  pb where pb.POST_ID in ("
		 * +postIdStr+") and pb.PAYBILL_GRP_ID ="+paybillGrpId);
		 * query.append(" and pb.emp_Id is null"); Query sqlQuery =
		 * hibSession.createSQLQuery(query.toString());
		 */
		logger.info(" check this query for getVacantPost " + query.toString());
		List list = sqlQuery.list();
		if (list != null && !list.isEmpty())
			count = list.size();
		logger.info("count before returning is " + count);

		return count;
	}

	// ended by manish
	public int countVacantData(long locId, long billNo, long month, long year) {
		// List resList = null;
		int count = 0;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			// query.append("");
			query.append(" select count(*) AS counter from hr_pay_paybill AS pb where pb.loc_id=  " + locId
					+ " and pb.emp_id is null");
			query.append(" and pb.paybill_grp_id in ");

			query.append(" (select ph.paybill_id from paybill_head_mpg AS ph where ph.bill_no in");
			query.append(" ( select mpg.BILL_GROUP_ID from mst_dcps_bill_group AS mpg where mpg.BILL_GROUP_ID=" + billNo
					+ " and mpg.Loc_id=" + locId + " ) ");
			query.append(" and ph.paybill_month= " + month);
			query.append(" and ph.paybill_year=  " + year);
			query.append(" and ph.approve_flag in(0,1)) ");

			Query sqlQuery = hibSession.createSQLQuery(query.toString());
			List list = sqlQuery.list();
			if (list != null && list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());

		}

		return count;

	}

	// added by japen

	public List getPreviousBillData(long empId, int month, int year, long locId) {

		List llist = null;
		logger.info("Inside nPaybillDao Impl in getPrevious bill data method");
		logger.info("Inside nPaybillDao Impl in getPrevious bill data method");
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			// query.append(" SELECT * FROM HR_PAY_PAYbill pb where PAYBILL_GRP_ID in ");
			// query.append(" (select ph.paybill_id from paybill_head_mpg ph where
			// ph.bill_no in ");
			// query.append(" select mpg.bill_subhead_id from hr_pay_bill_subhead_mpg mpg
			// where mpg.loc_id="+locId+" ) ");
			// query.append(" and ph.paybill_year="+year+" and ph.paybill_month="+month+"
			// and ph.approve_flag in(0,1)) and pb.EMP_ID="+empId+")");
			query.append("select * from HR_PAY_PAYBILL pb where pb.EMP_ID = " + empId + " and pb.PAYBILL_MONTH=" + month
					+ " and pb.PAYBILL_YEAR=" + year + " and pb.LOC_ID=" + locId);
			query.append(
					" and PAYBILL_GRP_ID  in  (select ph.paybill_id  from paybill_head_mpg ph where ph.APPROVE_FLAG in(0,1))");
			Query sqlQuery = hibSession.createSQLQuery(query.toString());
			llist = sqlQuery.list();

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());

		}

		return llist;
	}

	public Map getEdpCompoMapForHrPayPaybill(long locId) {
		Map returnMap = new HashMap();
		List llist = null;
		List allColumList = new ArrayList();
		List deducColumList = new ArrayList();
		List loanColumnList = new ArrayList();
		List advColumnList = new ArrayList();

		try {

			Session hibSession = getSession();

			String queryStr = "select paybill_column,edp_code,EDP_CODE_TYPE from HR_PAY_EDP_MST where paybill_column is not null and edp_code in (SELECT tedp.EDP_CODE FROM RLT_BILL_TYPE_EDP tedp,HR_PAY_EDP_COMPO_MPG mpg where tedp.TYPE_EDP_ID = mpg.TYPE_EDP_ID and (mpg.CMN_LOOKUP_ID = 2500135 or CMN_LOOKUP_ID = 2500134 or CMN_LOOKUP_ID = 2500136 or CMN_LOOKUP_ID = 2500137 ))";
			logger.info(queryStr);
			Query sqlQuery = hibSession.createSQLQuery(queryStr);
			llist = sqlQuery.list();

			for (int i = 0; i < llist.size(); i++) {

				Object[] arr = (Object[]) llist.get(i);
				if (arr[2].equals("A")) {
					allColumList.add(arr[0]);
				} else if (arr[2].equals("D")) {
					deducColumList.add(arr[0]);
				} else if (arr[2].equals("L")) {
					loanColumnList.add(arr[0]);
				} else if (arr[2].equals("AD")) {
					advColumnList.add(arr[0]);
				}
				returnMap.put(arr[0].toString(), arr[1].toString());
			}

			returnMap.put("allColumList", allColumList);
			logger.info("size of allocolumnList is " + allColumList);
			returnMap.put("deducColumList", deducColumList);
			logger.info("size of deduction column list is " + deducColumList);
			returnMap.put("advColumnList", advColumnList);
			logger.info("size of advColumnList is " + advColumnList.size());
			returnMap.put("loanColumnList", loanColumnList);
			logger.info("size of loanColumnList is " + loanColumnList.size());
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}

		return returnMap;
	}

	public List getPreviousBillDataForSingleColumn(long empId, int month, int year, long locId, String clm) {

		List llist = null;
		/*
		 * logger.info("Inside nPaybillDao Impl in getPrevious bill data method");
		 * logger.info("Inside nPaybillDao Impl in getPrevious bill data method");
		 */try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();

			// query.append("select "+clm+" from HR_PAY_PAYBILL pb where pb.EMP_ID =
			// "+empId+" and pb.PAYBILL_MONTH="+month+" and pb.PAYBILL_YEAR="+year+" and
			// pb.LOC_ID="+locId);
			query.append("SELECT " + clm + " from  HR_PAY_PAYBILL pb where pb.EMP_ID = " + empId + " and pb.loc_id=  "
					+ locId + " and  pb.PAYBILL_GRP_ID in");
			query.append("(select ph.paybill_id from paybill_head_mpg ph  where ph.paybill_month=" + month);
			query.append(" and ph.paybill_year=" + year);
			query.append(" and ph.approve_flag in(0,1) )");

			Query sqlQuery = hibSession.createSQLQuery(query.toString());
			llist = sqlQuery.list();

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());

		}

		return llist;
	}

	public List getBillNoOuter(long finYrId, long locId) {
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append("select MstDcpsBillGroup from MstDcpsBillGroup MstDcpsBillGroup
		// where finYearId=" + finYrId);
		query.append("select MstDcpsBillGroup from MstDcpsBillGroup MstDcpsBillGroup where ");
		query.append(" LocId=" + locId
				+ " and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') order by dcpsDdoBillGroupId");
		logger.info("Query for get bill no is---->>>>" + query.toString());

		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();
		logger.info("the getBillNo size of resList is ::" + resList.size());
		return resList;
	}

	public List getBillNumber(long billNo, long finId, long locId) {
		List<MstDcpsBillGroup> resList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append(" from MstDcpsBillGroup sub where sub.finYearId="+finId+" and
		// sub.cmnLocationMst.locId=" + locId + " and sub.billId="+billNo);
		query.append(" from MstDcpsBillGroup sub where sub.LocId=" + locId + " and sub.dcpsDdoBillGroupId=" + billNo);

		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();
		return resList;
	}

	/*
	 * //added by javed
	 * 
	 * //public List getLoanDetailsForHBACA(long loanId,String BillNo,long
	 * month,long year) public List getLoanDetailsForHBACA(long loanId,long
	 * BillNo,long Month,long year,long locId) { HrLoanEmpDtls hrloanempDtls = new
	 * HrLoanEmpDtls(); HrLoanEmpPrinRecoverDtls printloan = new
	 * HrLoanEmpPrinRecoverDtls();
	 * 
	 * List list = new ArrayList(); Session hibSession = getSession();
	 * 
	 * String HQ_QUERY =
	 * "select printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empPrefix||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname,loanempDtls.loanPrinAmt,loanempDtls.loanPrinInstNo,printloan.totalRecoveredAmt,printloan.totalRecoveredInst from HrLoanEmpPrinRecoverDtls printloan,HrLoanEmpDtls loanempDtls where printloan.hrLoanEmpDtls.empLoanId=loanempDtls.empLoanId"
	 * +" and printloan.totalRecoveredAmt!=0 and loanempDtls.hrLoanAdvMst.loanAdvId="
	 * +loanId;
	 * 
	 * 
	 * String HQL_QUERY =
	 * "select printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empPrefix||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname,"
	 * +"paybillLoan.totalAmt,paybillLoan.recoveredAmt,paybillLoan.totalInst,paybillLoan.recoveredInst from HrPayPaybillLoanDtls paybillLoan,HrLoanEmpPrinRecoverDtls printloan "
	 * +" where printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId "
	 * +" and paybillLoan.cmnLocationMst.locId="
	 * +locId+" and paybillLoan.hrLoanAdvMst.loanAdvId="+loanId
	 * +" and paybillLoan.paybillId.id in ("
	 * +" select ph.hrPayPaybill from PaybillHeadMpg ph where ph.billNo in ("
	 * +" select mpg.dcpsDdoBillGroupId from MstDcpsBillGroup mpg where mpg.dcpsDdoBillGroupId="
	 * +BillNo +" and mpg.LocId="+locId+") and ph.month="+Month+""
	 * +" and ph.year="+year +" and ph.approveFlag in(0,1))"; Query query =
	 * hibSession.createQuery(HQL_QUERY);
	 * logger.info("==> getLoanDetailsForHBACA query :: "+query); list=
	 * query.list(); return list; } //ended
	 */
	public String getEmployeeNameFromEmployeeId(String employeeid) {
		// Criteria objCrt = null;
		List list = null;
		String EmployeeName;
		Session hibSession = getSession();
		logger.info("going to execute query....");

		StringBuffer strQuery = new StringBuffer(
				"SELECT  emp.emp_fname||' '||emp.emp_mname||' '||emp.emp_lname FROM org_emp_mst emp where emp.emp_id in(SELECT eisemp.emp_mpg_id FROM hr_eis_emp_mst eisemp where eisemp.emp_id="
						+ employeeid + ") ");
		Query query = hibSession.createSQLQuery(strQuery.toString());

		// Query query = hibSession.createQuery(strQuery);
		// Query query = hibSession.createQuery("from HrEisBranchMst");
		list = query.list();
		logger.info("List size in DAO:-->" + list.size());
		EmployeeName = list.get(0).toString();
		return EmployeeName;
	}

	public List<HrPayPaybill> getBillDataForDiff(long locId, long billNo, long month, long year) {
		List<HrPayPaybill> resList = null;
		try {
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			// query.append("");
			/*
			 * query.append(" select * from hr_pay_paybill pb where pb.loc_id=  "+locId);
			 * query.append(" and pb.paybill_grp_id in ");
			 * 
			 * query.
			 * append(" (select ph.paybill_id from paybill_head_mpg ph where ph.bill_no in"
			 * ); query.
			 * append(" ( select mpg.bill_subhead_id from hr_pay_bill_subhead_mpg mpg where mpg.bill_no="
			 * +billNo +" and mpg.loc_id="+locId+" ) ");
			 * query.append(" and ph.paybill_month= "+month);
			 * query.append(" and ph.paybill_year=  "+year);
			 * query.append(" and ph.approve_flag in(0,1)) ");
			 */

			query.append(" from HrPayPaybill pb where pb.cmnLocationMst.locId = " + locId);
			query.append(" and pb.paybillGrpId in ");
			query.append(" (select ph.hrPayPaybill from PaybillHeadMpg ph where ph.billNo in ");
			query.append(" (select mpg.dcpsDdoBillGroupId from MstDcpsBillGroup mpg where mpg.dcpsDdoBillGroupId = "
					+ billNo + " and   mpg.LocId= " + locId + ")");
			query.append(" and ph.month=" + month);
			query.append(" and ph.year= " + year);
			query.append(" and ph.approveFlag in(0,1)) order by pb.hrEisEmpMst.empId");

			logger.info("Query from PaybillDAO " + query.toString());
			/*
			 * query.append(" pb from HrPayPaybill pb where pb.cmnLocationMst.locId =   "
			 * +locId); query.append(" and pb.paybillGrpId in  ");
			 * 
			 * query.
			 * append(" (ph.hrPayPaybill from PaybillHeadMpg ph where ph.billNo.billHeadId in "
			 * );
			 * query.append(" (mpg.billHeadId from MstDcpsBillGroup mpg where mpg.billId = "
			 * +billNo +"  and mpg.cmnLocationMst.locId="+locId+" ) ");
			 * query.append(" and ph.month = "+month);
			 * query.append(" and ph.year =  "+year);
			 * query.append(" and ph.approveFlag in(0,1)) ");
			 */

			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());

		}

		return resList;

	}

	// added by Ankit Bhatt for getting DDO code from Logged in Post
	public List getDDOCodeByLoggedInPost(long postId) {
		logger.info("Inside getDDOCodeByLoggedInPost");
		List<OrgDdoMst> ddoList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append(" from MstDcpsBillGroup sub where sub.finYearId="+finId+" and
		// sub.cmnLocationMst.locId=" + locId + " and sub.billId="+billNo);
		// query.append(" from OrgDdoMst as ddo where ddo.postId=" + postId);
		query.append(" from OrgDdoMst as ddo where ddo.postId=" + postId);
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		ddoList = sqlQuery.list();
		return ddoList;
	}

	public static String getLangById(Long lLngLangId) {
		String lStrReturn = null;

		try {
			if (lLngLangId == 1) {
				lStrReturn = "en_US";
			} else if (lLngLangId == 2) {
				lStrReturn = "gu";
			}
		} catch (Exception e) {
			logger.error("Error in getLangById is : " + e, e);
		}

		return lStrReturn;
	}

	// ended by Ankit Bhatt

	public List getPayBillGrpIDFromBillNo(long billType, String month, String year, String BillNo) {

		ArrayList dtlsList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("     select distinct bhm.paybill_id\n");
		strBfr.append(" from paybill_head_mpg bhm, mst_dcps_bill_group hpbsm\n");
		strBfr.append(" where bhm.approve_flag in (0, 1) and bhm.bill_Type_Id = " + billType + " and\n");
		strBfr.append("      bhm.paybill_month = '" + month + "' and bhm.paybill_year = '" + year + "' and\n");
		strBfr.append("      bhm.bill_no = hpbsm.BILL_GROUP_ID and hpbsm.BILL_GROUP_ID = " + BillNo + " ");

		Query query = hibSession.createSQLQuery(strBfr.toString());

		dtlsList = (ArrayList) query.list();
		logger.info("dtlsList size is:::::::::" + dtlsList.size());
		return dtlsList;
	}

	public String getOffice(long locId) {
		List list = new ArrayList();
		String rtnStr = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("SELECT off_name FROM mst_dcps_ddo_office where LOC_ID=" + locId);

		Query hsqlQuery = hibSession.createSQLQuery(query.toString());
		list = hsqlQuery.list();

		if (list != null && list.size() > 0)
			rtnStr = list.get(0).toString();

		return rtnStr;
	}

	public String getBillGeneratedDate(long billNo, int month, int year, long locId) {
		logger.info("in getBillGeneratedDate...........");
		List list = new ArrayList();

		String Generated = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append(" select  rlt.createdDate ");
		query.append(" from PaybillHeadMpg rlt");
		query.append(" where rlt.billNo = " + billNo + " and rlt.month=" + month + " and rlt.year=" + year + " ");

		query.append(" and rlt.approveFlag in(0,1) ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getBillGeneratedDate query :: " + sqlQuery);
		list = sqlQuery.list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				Generated = list.get(0).toString();
			}
		}

		else {
			Generated = "Not Available";
		}
		return Generated;
	}

	public List getGpfAcctNos(long empId, long langId) {
		// ArrayList list =new ArrayList();
		// String gpfAcctNo = null;
		/*
		 * Session hibSession = getSession(); StringBuffer query=new StringBuffer();
		 * query.
		 * append("SELECT gpf.GPF_ACC_NO FROM hr_pay_gpf_details gpf where gpf.USER_ID in (SELECT emp.user_id FROM org_emp_mst emp where emp.emp_id="
		 * +empId+" and emp.lang_id="+langId+") ");
		 * 
		 * Query hsqlQuery = hibSession.createSQLQuery(query.toString());
		 */
		List list = null;
		Session hibSession = getSession();
		String strQuery = "SELECT gpf.GPF_ACC_NO FROM hr_pay_gpf_details gpf where gpf.USER_ID in (SELECT emp.user_id FROM org_emp_mst emp where emp.emp_id="
				+ empId + " and emp.lang_id=" + langId + ")  ";
		Query query = hibSession.createSQLQuery(strQuery.toString());
		list = query.list();
		/*
		 * if(list.get(0) !=null) { gpfAcctNo = list.get(0).toString(); } else {
		 * gpfAcctNo = ""; }
		 */
		return list;
	}

	public List getGpfAcctNo(long locId) {
		logger.info("Inside getGpfAcctNo");
		List<HrPayGpfBalanceDtls> ddoList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append(" from MstDcpsBillGroup sub where sub.finYearId="+finId+" and
		// sub.cmnLocationMst.locId=" + locId + " and sub.billId="+billNo);
		query.append(" from HrPayGpfBalanceDtls as ddo where ddo.cmnLocationMst.locId=" + locId);
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		ddoList = sqlQuery.list();
		return ddoList;
	}

	public List getOfficeListFromEmpId(long empId, long locId) {
		logger.info("Inside getGpfAcctNo");
		List<HrPayGpfBalanceDtls> ddoList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append(" from MstDcpsBillGroup sub where sub.finYearId="+finId+" and
		// sub.cmnLocationMst.locId=" + locId + " and sub.billId="+billNo);
		// query.append(" from DdoOffice as office where office.dcpsDdoCode in(select
		// emp.ddoCode from MstEmp emp where emp.orgEmpMstId="+empId+") and
		// office.dcpsDdoOfficeName='Yes' and office.LocId=" + locId );
		query.append(
				" from DdoOffice as office where office.LangId in(select emp.cmnLanguageMst.langId from OrgEmpMst emp where emp.empId="
						+ empId + ") and office.dcpsDdoOfficeDdoFlag='Yes' and office.LocId=" + locId);
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		ddoList = sqlQuery.list();
		return ddoList;
	}

	public List getOfficeList(long locId) {
		logger.info("Inside getDDOCodeByLoggedInPost");
		List<DdoOffice> ddoList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append(" from MstDcpsBillGroup sub where sub.finYearId="+finId+" and
		// sub.cmnLocationMst.locId=" + locId + " and sub.billId="+billNo);
		query.append(" from DdoOffice as ddo where ddo.LocId=" + locId);
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		ddoList = sqlQuery.list();
		return ddoList;
	}

	public List getDesignationList(long langId) {
		logger.info("Inside getDesignationList");
		List<OrgDesignationMst> ddoList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append(" from MstDcpsBillGroup sub where sub.finYearId="+finId+" and
		// sub.cmnLocationMst.locId=" + locId + " and sub.billId="+billNo);
		query.append(" from OrgDesignationMst as ddo where ddo.cmnLanguageMst.langId=" + langId
				+ " and ddo.activateFlag=1 ");
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		ddoList = sqlQuery.list();
		return ddoList;
	}

	public List getGpfAccoNos(long userId) {
		logger.info("Inside getGpfAccoNos");
		List<HrPayGpfBalanceDtls> ddoList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append(" from MstDcpsBillGroup sub where sub.finYearId="+finId+" and
		// sub.cmnLocationMst.locId=" + locId + " and sub.billId="+billNo);
		query.append(" from HrPayGpfBalanceDtls as ddo where ddo.userId=" + userId);
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		ddoList = sqlQuery.list();
		return ddoList;
	}

	public List getEmployeeName(String empId) {
		logger.info("Inside getEmployeeName");
		List<OrgEmpMst> empList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" from OrgEmpMst as ddo where ddo.empId=" + empId);
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		empList = sqlQuery.list();
		return empList;
	}

	public List getHRRList(long BillNo, long Month, long year) {
		logger.info("in getHRRList...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		/*
		 * String
		 * HQL_QUERY="select orgEmpMst.empPrefix||' '||orgEmpMst.empFname||' '||orgEmpMst.empLname,det.orgDesignationMst.dsgnName,"
		 * +"orgEmpMst.empId,pb.deduc9550 from OrgEmpMst orgEmpMst, OrgUserpostRlt up, OrgPostDetailsRlt det,HrPayPaybill pb "
		 * +"where pb.hrEisEmpMst.empId=orgEmpMst.empId"
		 * +" and up.orgUserMst.userId=orgEmpMst.orgUserMst.userId"
		 * +" and det.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1"
		 * +" and pb.deduc9550>0" +" and pb.id in ("
		 * +" select ph.hrPayPaybill from PaybillHeadMpg ph where ph.billNo in ("
		 * +" select mpg.billHeadId from HrPayBillHeadMpg mpg where mpg.billId="+BillNo+
		 * "" +" and mpg.cmnLocationMst.locId=300022) and ph.month="+Month+""
		 * +" and ph.year="+year+"" +" and ph.approveFlag in(0,1))";
		 */

		String HQL_QUERY = "select orgEmpMst.empPrefix||' '||orgEmpMst.empFname||' '||orgEmpMst.empLname,det.orgDesignationMst.dsgnName,"
				+ "orgEmpMst.empId,pb.deduc9550,"
				+ "qtrmst.cmnAddressMst.houseName,qtrmst.cmnAddressMst.socBuildName,qtrmst.cmnAddressMst.street,qtrmst.cmnAddressMst.area "
				+ "from OrgEmpMst orgEmpMst, OrgUserpostRlt up, OrgPostDetailsRlt det,HrPayPaybill pb,"
				+ "HrEisQtrMst qtrmst " + "where pb.hrEisEmpMst.empId=orgEmpMst.empId"
				+ " and up.orgUserMst.userId=orgEmpMst.orgUserMst.userId"
				+ " and det.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1"
				+ " and qtrmst.orgUserMstByAllocatedTo=pb.hrEisEmpMst.orgEmpMst.orgUserMst.userId"
				+ " and pb.deduc9550>0" + " and pb.id in ("
				+ " select ph.hrPayPaybill from PaybillHeadMpg ph where ph.billNo in ("
				+ " select mpg.billHeadId from HrPayBillHeadMpg mpg where mpg.billId=" + BillNo + ""
				+ " and mpg.cmnLocationMst.locId=300022) and ph.month=" + Month + "" + " and ph.year=" + year + ""
				+ " and ph.approveFlag in(0,1))";

		Query query = hibSession.createQuery(HQL_QUERY);
		logger.info("==> getHRRList query :: " + query);
		list = query.list();

		return list;
	}

	/*
	 * public ArrayList getEmployeeDetailsList(String desgnCode,long langId,long
	 * locId) { ArrayList dtlsList = new ArrayList(); Session hibSession =
	 * getSession(); StringBuffer strQuery = new StringBuffer(); strQuery.
	 * append(" SELECT ee.emp_id ,concat(ee.emp_prefix,' ',ee.emp_fname,' ',ee.emp_mname,' ',ee.emp_lname),od.dsgn_name,gpf.GPF_ACC_NO\n"
	 * ); strQuery.
	 * append("  FROM ORG_EMP_MST EE ,org_user_mst ou,org_userpost_rlt our,org_post_details_rlt op,org_designation_mst od,hr_pay_gpf_details gpf\n"
	 * ); strQuery.
	 * append(" where ee.user_id=our.user_id and ee.user_id=ou.user_id and our.post_id=op.post_id and op.dsgn_id=od.dsgn_id and\n"
	 * ); strQuery.
	 * append(" ee.user_id=gpf.USER_ID and ou.user_id=gpf.USER_ID and od.dsgn_code="
	 * +desgnCode );
	 * 
	 * 
	 * Query query = hibSession.createSQLQuery(strQuery.toString());
	 * 
	 * dtlsList =(ArrayList) query.list();
	 * logger.info("dtlsList size is:::::::::"+dtlsList.size()); return dtlsList; }
	 */

	public List getEmployeeDetailsList(String dsgnCode, long locId, long langId, String dcpsDdoOfficeIdPk, String EmpId,
			long designationId, String GPFAcctNo) {
		logger.info("in getEmployeeDetailsList...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		/*
		 * String
		 * HQL_QUERY="select DISTINCT orgEmpMst.empId,concat(orgEmpMst.empPrefix,' ',orgEmpMst.empFname,' ',orgEmpMst.empLname),od.dsgnName,"
		 * +" gpf.gpfAccNo,ddo.dcpsDdoOfficeName"
		 * +" from OrgEmpMst orgEmpMst, OrgUserMst ou,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od,"
		 * +" HrPayGpfBalanceDtls gpf,DdoOffice ddo,MstEmp mss"
		 * +" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId"
		 * +" and orgEmpMst.orgUserMst.userId=ou.userId"
		 * //+" and det.postId=up.orgPostMstByPostId.postId "
		 * +" and orgEmpMst.orgUserMst.userId=gpf.userId" +" and ou.userId=gpf.userId"
		 * 
		 * +" and mss.orgEmpMstId=orgEmpMst.empId and ddo.LocId="
		 * +locId+" and ddo.LangId="+langId+"" +" and od.dsgnCode="+dsgnCode+"";
		 */

		logger.info("dsgnCode*****in dao impl***" + dsgnCode);

		/*
		 * StringBuffer query = new StringBuffer(); query.
		 * append(" select  distinct orgEmpMst.empId,concat(orgEmpMst.empPrefix,' ',orgEmpMst.empFname,' ',orgEmpMst.empLname),od.dsgnName, "
		 * ); query.
		 * append(" gpf.gpfAccNo,ddo.dcpsDdoOfficeName, orgEmpMst.empPrefix,orgEmpMst.empFname,orgEmpMst.empLname,ou.userId,ou.userName"
		 * ); query.
		 * append(" from OrgEmpMst orgEmpMst, OrgUserMst ou,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od, "
		 * ); query.append(" HrPayGpfBalanceDtls gpf,MstEmp mss ");
		 * query.append(" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId ");
		 * query.append(" and orgEmpMst.orgUserMst.userId=ou.userId ");
		 * query.append(" and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
		 * query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");
		 * 
		 * query.append(" and ou.userId=gpf.userId ");
		 * query.append(" and mss.orgEmpMstId=orgEmpMst.empId  "); query.
		 * append("and ddo.dcpsDdoOfficeDdoFlag='Yes' and ddo.LangId=1 and ddo.LocId="
		 * +locId+" and od.dsgnCode="+dsgnCode+"");
		 */

		StringBuffer query = new StringBuffer();
		/*
		 * query.
		 * append(" select  distinct orgEmpMst.empId,concat(orgEmpMst.empPrefix,' ',orgEmpMst.empFname,' ',orgEmpMst.empLname),od.dsgnName, "
		 * ); query.
		 * append(" gpf.gpfAccNo,office.dcpsDdoOfficeName,orgEmpMst.empPrefix,orgEmpMst.empFname,orgEmpMst.empLname,ou.userId,ou.userName"
		 * ); query.
		 * append(" from OrgEmpMst orgEmpMst, OrgUserMst ou,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od, "
		 * ); query.append(" HrPayGpfBalanceDtls gpf,MstEmp mss,DdoOffice office ");
		 * query.append(" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId ");
		 * query.append(" and orgEmpMst.orgUserMst.userId=ou.userId ");
		 * query.append(" and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
		 * query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");
		 * 
		 * query.append(" and ou.userId=gpf.userId ");
		 * query.append(" and mss.orgEmpMstId=orgEmpMst.empId  ");
		 * query.append(" and mss.locId=office.LocId  ");
		 * query.append(" and office.LocId="+locId+"  ");
		 * query.append(" and  det.cmnLocationMst.locId="
		 * +locId+" and det.cmnLanguageMst.langId="+langId+" and od.dsgnCode="+dsgnCode+
		 * "");
		 */

		query.append(
				" select  orgEmpMst.empId,concat(orgEmpMst.empPrefix,' ',orgEmpMst.empFname,' ',orgEmpMst.empMname,' ',orgEmpMst.empLname),od.dsgnName, ");
		query.append(
				" gpf.gpfAccNo,ddo.dcpsDdoOfficeName, orgEmpMst.empPrefix,orgEmpMst.empFname,orgEmpMst.empLname,ou.userId,ou.userName,det.orgPostMst.postId,hrEis.sevarthEmpCode,gpf.pfSeries");
		query.append(
				" from OrgEmpMst orgEmpMst, OrgUserMst ou,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od, ");
		query.append(" HrPayGpfBalanceDtls gpf,DdoOffice ddo, HrEisEmpMst hrEis ");
		// query.append(" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId ");
		query.append(" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId ");
		query.append(" and orgEmpMst.orgUserMst.userId=ou.userId ");
		query.append(" and ou.userId=gpf.userId ");
		query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");
		query.append(" and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
		query.append(" and hrEis.orgEmpMst.empId=orgEmpMst.empId and up.activateFlag=1 ");

		// added for map with employee and office
		// query.append(" and mss.orgEmpMstId=orgEmpMst.empId ");
		// query.append(" and mss.currOff=ddo.dcpsDdoOfficeIdPk ");

		// ended for map with employee and office

		// query.append(" and mss.locId=ddo.LocId ");
		query.append(" and det.cmnLocationMst.locId=" + locId + " ");
		query.append(" and ddo.LocId=" + locId + " ");

		if (EmpId != null && !(" ").equalsIgnoreCase(EmpId) && !EmpId.equals("-1") && !EmpId.equals(""))
		// if(EmpId !=0 && EmpId>0)
		{
			query.append(" and orgEmpMst.empId=" + EmpId + " ");
		}

		if (designationId != 0 && designationId > 0) {
			query.append(" and od.dsgnId=" + designationId + " ");
		}
		if (dsgnCode != null && !dsgnCode.equals("") && !dsgnCode.equals("-1")) {
			query.append(" and od.dsgnCode='" + dsgnCode + "' ");
		}

		if (dcpsDdoOfficeIdPk != null && !dcpsDdoOfficeIdPk.equals("") && !dcpsDdoOfficeIdPk.equals("-1")) {
			query.append(" and ddo.dcpsDdoOfficeIdPk=" + dcpsDdoOfficeIdPk + " ");
		}
		if (GPFAcctNo != null && !GPFAcctNo.equals("-1") && !GPFAcctNo.equals(""))
		// if(EmpId !=0 && EmpId>0)
		{
			query.append(" and gpf.gpfAccNo='" + GPFAcctNo + "' ");
		}
		query.append(
				" order by concat(orgEmpMst.empPrefix,' ',orgEmpMst.empFname,' ',orgEmpMst.empMname,' ',orgEmpMst.empLname) asc");
		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getEmployeeDetailsList query ::******* " + sqlQuery);
		list = sqlQuery.list();

		logger.info("getEmployeeDetailsList size::::::::::::::" + list.size());

		return list;
	}

	public List getEmployeeDetailsListFromOffice(String dcpsDdoOfficeIdPk, long locId, long langId) {
		logger.info("in getEmployeeDetailsListFromOffice...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		logger.info("else part is coming");
		query.append(
				" select  orgEmpMst.empId,concat(orgEmpMst.empPrefix,' ',orgEmpMst.empFname,' ',orgEmpMst.empLname),od.dsgnName, ");
		query.append(
				" gpf.gpfAccNo,ddo.dcpsDdoOfficeName, orgEmpMst.empPrefix,orgEmpMst.empFname,orgEmpMst.empLname,ou.userId,ou.userName,det.orgPostMst.postId");
		query.append(
				" from OrgEmpMst orgEmpMst, OrgUserMst ou,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od, ");
		query.append(" HrPayGpfBalanceDtls gpf,DdoOffice ddo,MstEmp mss ");
		// query.append(" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId ");
		query.append(" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId and up.activateFlag=1 ");
		query.append(" and orgEmpMst.orgUserMst.userId=ou.userId ");
		query.append(" and ou.userId=gpf.userId ");
		query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");
		query.append(" and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
		query.append(" and mss.locId=ddo.LocId  ");
		query.append(" and det.cmnLocationMst.locId=" + locId + " ");
		query.append(" and ddo.LocId=" + locId + " ");
		query.append(" and ddo.dcpsDdoOfficeIdPk=" + dcpsDdoOfficeIdPk + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		list = sqlQuery.list();
		logger.info("Query to be executed is " + sqlQuery.toString());

		logger.info("==> getEmployeeDetailsList query :: " + sqlQuery);
		logger.info("==> getEmployeeDetailsList query**********8 :: " + sqlQuery.toString());

		logger.info("==> getEmployeeDetailsList query :: " + sqlQuery);

		return list;
	}

	/*
	 * public List getEmployeeIdFromEployeeName(String empLocalFName, String
	 * empLocalMName, String empLocalLName) { List EmployeeNamesList = null;
	 * 
	 * long EmployeeId = 0; Session hiSession = getSession(); StringBuffer strQuery
	 * = new StringBuffer();
	 * strQuery.append(" SELECT ee.empId,ee.orgUserMst.userId \n");
	 * strQuery.append("  FROM OrgEmpMst ee \n");
	 * strQuery.append(" where ee.empFname="+empLocalFName+" and ee.empLname="
	 * +empLocalLName+" ");
	 * 
	 * if(empLocalMName != null && !empLocalMName.equals("")
	 * &&!empLocalMName.equals("-1")) {
	 * strQuery.append(" and emp.empMname="+empLocalMName+" "); }
	 * 
	 * logger.info("Query For TrnBillRegester : " + strQuery.toString()); Query
	 * query = hiSession.createSQLQuery(strQuery.toString()); logger.
	 * info(":::::::::::::::::::::::::::::::::::::::::::::::: query in DAOIMPL : " +
	 * query); EmployeeNamesList = query.list();
	 * 
	 * logger.info(":::::::::::::: trnBillRegList in DAOIMPL : " +
	 * EmployeeNamesList.size());
	 * 
	 * //EmployeeId = Long.parseLong(EmployeeNamesList.get(0).toString()); return
	 * EmployeeNamesList;
	 * 
	 * }
	 */

	public List getEmployeeIdFromEployeeName(String empLocalFName, String empLocalMName, String empLocalLName) {
		logger.info("in getEmployeeIdFromEployeeName...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append(" SELECT ee.empId,ee.orgUserMst.userId ");
		query.append(" FROM OrgEmpMst ee ");
		query.append(" where ee.empFname='" + empLocalFName + "' ");

		if (empLocalLName != null && !empLocalLName.equals("") && !empLocalLName.equals("-1")) {
			query.append(" and ee.empLname='" + empLocalLName + "' ");
		}

		if (empLocalMName != null && !empLocalMName.equals("") && !empLocalMName.equals("-1")) {
			query.append(" and ee.empMname='" + empLocalMName + "' ");
		}

		Query sqlQuery = hibSession.createQuery(query.toString());
		list = sqlQuery.list();
		logger.info("==> getEmployeeIdFromEployeeName query :: " + sqlQuery);

		return list;
	}

	public long getDesignationIdEployeeName(long userId) {
		logger.info("in getEmployeeIdFromEployeeName...........");
		List list = new ArrayList();

		long designationId = 0;
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  distinct od.dsgnId ");
		query.append(" from OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od ");
		query.append(" where det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
		query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");
		query.append(" and up.orgUserMst.userId=" + userId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getEmployeeIdFromEployeeName query :: " + sqlQuery);
		list = sqlQuery.list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				designationId = Long.parseLong(list.get(0).toString());
			}
		} else {
			designationId = 0;
		}

		return designationId;
	}

	public List getEmployeeDetailsListFromEmployeeName(long designationId, long locId, long langId, long empId) {
		logger.info("in getEmployeeDetailsListFromEmployeeName...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append(
				" select  distinct orgEmpMst.empId,concat(orgEmpMst.empPrefix,' ',orgEmpMst.empFname,' ',orgEmpMst.empLname),od.dsgnName, ");
		query.append(
				" gpf.gpfAccNo,ddo.dcpsDdoOfficeName, orgEmpMst.empPrefix,orgEmpMst.empFname,orgEmpMst.empLname,ou.userId,ou.userName");
		query.append(
				" from OrgEmpMst orgEmpMst, OrgUserMst ou,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od, ");
		query.append(" HrPayGpfBalanceDtls gpf,DdoOffice ddo,MstEmp mss ");
		query.append(" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId ");
		query.append(" and orgEmpMst.orgUserMst.userId=ou.userId ");
		query.append(" and ou.userId=gpf.userId ");
		query.append(" and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
		query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");
		query.append(" and mss.locId=ddo.LocId  ");
		query.append(" and det.cmnLocationMst.locId=" + locId + " ");
		query.append(" and ddo.LocId=" + locId + " ");
		query.append(" and od.dsgnId=" + designationId + " ");
		query.append(" and orgEmpMst.empId=" + empId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());
		list = sqlQuery.list();
		logger.info("==> getEmployeeDetailsList query :: " + sqlQuery);

		return list;
	}

	public long getDesignationIdEployeeId(String empId) {
		logger.info("in getDesignationIdEployeeId...........");
		List list = new ArrayList();

		long designationId = 0;
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		if (empId != null && !empId.equals("") && !empId.equals("-1")) {
			query.append(" select   od.dsgnId ");
			query.append(" from OrgEmpMst emp,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od ");
			query.append(
					" where emp.orgUserMst.userId=up.orgUserMst.userId and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
			query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId and up.activateFlag=1 ");

			if (empId != null && !empId.equals("") && !empId.equals("-1"))
				query.append(" and emp.empId=" + empId + " ");

			Query sqlQuery = hibSession.createQuery(query.toString());

			logger.info("==> getDesignationIdEployeeId query :: " + sqlQuery);

			list = sqlQuery.list();
			logger.info("==> getDesignationIdEployeeId query list:: " + list.size());
			if (list.size() > 0) {
				if (list.get(0) != null) {
					designationId = Long.parseLong(list.get(0).toString());
				}
			} else {
				designationId = 0;
			}

		}

		else {
			designationId = 0;
		}

		return designationId;
	}

	public String getdesignationIdEployeeId(String empId) {
		logger.info("in getDesignationIdEployeeId...........");
		List list = new ArrayList();

		String designationId = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		if (empId != null && !empId.equals("") && !empId.equals("-1")) {
			query.append(" select  od.dsgnCode ");
			query.append(" from OrgEmpMst emp,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od ");
			query.append(
					" where emp.orgUserMst.userId=up.orgUserMst.userId and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
			query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId and up.activateFlag=1 ");

			if (empId != null && !empId.equals("") && !empId.equals("-1"))
				query.append(" and emp.empId=" + empId + " ");

			Query sqlQuery = hibSession.createQuery(query.toString());

			logger.info("==> getDesignationIdEployeeId query :: " + sqlQuery);

			list = sqlQuery.list();
			logger.info("==> getDesignationIdEployeeId query list:: " + list.size());
			if (list.size() > 0) {
				if (list.get(0) != null) {
					designationId = list.get(0).toString();
				}
			} else {
				designationId = "";
			}

		}

		else {
			designationId = "";
		}

		return designationId;
	}

	public String getGPFAcctNoEployeeId(String EmployeeId) {
		logger.info("in getGPFAcctNoEployeeId...........");
		List list = new ArrayList();

		String GPFacctNo = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  distinct gpf.gpfAccNo ");
		query.append(" from OrgEmpMst emp,HrPayGpfBalanceDtls gpf");
		query.append(" where emp.orgUserMst.userId=gpf.userId and emp.empId=" + EmployeeId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getGPFAcctNoEployeeId query :: " + sqlQuery);

		list = sqlQuery.list();
		logger.info("==> getGPFAcctNoEployeeId query list:: " + list.size());
		if (list.size() > 0) {
			if (list.get(0) != null) {
				GPFacctNo = list.get(0).toString();
			}
		} else {
			GPFacctNo = "";
		}

		return GPFacctNo;
	}

	public String getEmployeeIdFromOfficeId(String EmployeeId, String OfficeId) {
		logger.info("in getEployeeIdFromOfficeId...........");
		List list = new ArrayList();

		String EMployeeId = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		if (EmployeeId != null && !EmployeeId.equals("")) {
			query.append(" select  distinct emp.orgEmpMstId ");
			query.append(" from MstEmp emp");
			query.append(" where emp.orgEmpMstId=" + EmployeeId + " and emp.currOff in ");
			query.append(
					" (select ddo.dcpsDdoOfficeIdPk from DdoOffice ddo where ddo.dcpsDdoOfficeIdPk=" + OfficeId + ") ");

			Query sqlQuery = hibSession.createQuery(query.toString());

			logger.info("==> getEployeeIdFromOfficeId query :: " + sqlQuery);

			list = sqlQuery.list();
			logger.info("==> getEployeeIdFromOfficeId query list:: " + list.size());
			if (list.size() > 0) {
				if (list.get(0) != null) {
					EMployeeId = list.get(0).toString();
				}
			} else {
				EMployeeId = "";
			}

		} else {
			EMployeeId = "no";
		}

		return EMployeeId;
	}

	public long getDesignationIdFromDsgnCode(String desgnCode) {
		logger.info("in getDesignationIdFromDsgnCode...........");
		List list = new ArrayList();

		long designationId = 0;
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  od.dsgnId ");
		query.append(" from OrgDesignationMst od ");
		query.append(" where od.dsgnCode='" + desgnCode + "' ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getDesignationIdFromDsgnCode query :: " + sqlQuery);
		logger.info("==> getDesignationIdFromDsgnCode query :: " + sqlQuery);
		list = sqlQuery.list();

		if (list.size() > 0) {
			if (list.get(0) != null) {
				designationId = Long.parseLong(list.get(0).toString());
			}
		} else {
			designationId = 0;
		}

		return designationId;
	}

	public String getEmployeeIdFromDsgnId(long DESIGNATIONId) {
		logger.info("in getEmployeeIdFromDsgnId...........");
		List list = new ArrayList();

		String employeeid = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  distinct e.empId ");
		query.append(" from OrgEmpMst e,OrgUserpostRlt up, OrgPostDetailsRlt det ");
		query.append(
				" where e.orgUserMst.userId=up.orgUserMst.userId and up.orgPostMstByPostId.postId=det.orgPostMst.postId and det.orgDesignationMst.dsgnId="
						+ DESIGNATIONId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getEmployeeIdFromDsgnId query :: " + sqlQuery);
		list = sqlQuery.list();

		if (list.size() > 0) {
			if (list.get(0) != null) {
				employeeid = list.get(0).toString();
			}
		} else {
			employeeid = "no";
		}

		return employeeid;
	}

	public long getDesignationIdEployeeId(long empId) {
		logger.info("in getDesignationIdEployeeId...........");
		List list = new ArrayList();

		long designationId = 0;
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  od.dsgnId ");
		query.append(" from OrgEmpMst emp,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od ");
		query.append(
				" where emp.orgUserMst.userId=up.orgUserMst.userId and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
		query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId and up.activateFlag=1 ");

		query.append(" and emp.empId=" + empId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getDesignationIdEployeeId query :: " + sqlQuery);
		list = sqlQuery.list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				designationId = Long.parseLong(list.get(0).toString());
			}
		}

		else {
			designationId = 0;
		}
		return designationId;
	}

	public List getEmployeeDetailsListFromEmployeeId(long designationId, long locId, long langId, long empId) {
		logger.info("in getEmployeeDetailsListFromEmployeeName...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append(
				" select  distinct orgEmpMst.empId,concat(orgEmpMst.empPrefix,' ',orgEmpMst.empMname,' ',orgEmpMst.empFname,' ',orgEmpMst.empLname),od.dsgnName, ");
		query.append(
				" gpf.gpfAccNo,ddo.dcpsDdoOfficeName, orgEmpMst.empPrefix,orgEmpMst.empFname,orgEmpMst.empLname,ou.userId,ou.userName");
		query.append(
				" from OrgEmpMst orgEmpMst, OrgUserMst ou,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od, ");
		query.append(" HrPayGpfBalanceDtls gpf,DdoOffice ddo,MstEmp mss ");
		query.append(" where orgEmpMst.orgUserMst.userId=up.orgUserMst.userId ");
		query.append(" and orgEmpMst.orgUserMst.userId=ou.userId ");
		query.append(" and ou.userId=gpf.userId ");
		query.append(" and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
		query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");
		query.append(" and det.cmnLocationMst.locId=" + locId + " ");
		query.append(" and ddo.LocId=" + locId + " ");
		query.append(" and od.dsgnId=" + designationId + " ");
		query.append(" and orgEmpMst.empId=" + empId + " ");
		// query.append("and ddo.dcpsDdoOfficeDdoFlag='Yes' and ddo.LangId=1 and
		// ddo.LocId="+locId+" and od.dsgnCode="+dsgnCode+"");

		Query sqlQuery = hibSession.createQuery(query.toString());
		list = sqlQuery.list();
		logger.info("==> getEmployeeDetailsList query :: " + sqlQuery);

		return list;
	}

	public List getEmployeeIdAndUserIDFromGPFAcctNo(String GPFAcctNo) {
		logger.info("in getEmployeeIdAndUserIDFromGPFAcctNo...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append(" SELECT ee.empId,ee.orgUserMst.userId ");
		query.append(" FROM OrgEmpMst ee,HrPayGpfBalanceDtls gpf ");
		query.append(" where ee.orgUserMst.userId=gpf.userId and gpf.gpfAccNo like '%" + GPFAcctNo + "%' ");

		Query sqlQuery = hibSession.createQuery(query.toString());
		list = sqlQuery.list();
		logger.info("==> getEmployeeIdAndUserIDFromGPFAcctNo query :: " + sqlQuery);
		logger.info("==> getEmployeeIdAndUserIDFromGPFAcctNo list size isssssssssss :: " + list.size());

		return list;
	}

	public String getTreasuryCode(long locationId) {
		logger.info("in getDesignationIdEployeeId...........");
		List list = new ArrayList();

		String treasurycode = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  distinct rlt.locationCode ");
		query.append(" from RltDdoOrg rlt,DdoOffice ddo");
		query.append(" where rlt.ddoCode = ddo.dcpsDdoCode and ddo.dcpsDdoOfficeDdoFlag='Yes'");

		query.append(" and ddo.LocId=" + locationId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getTreasuryCode query :: " + sqlQuery);
		list = sqlQuery.list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				treasurycode = list.get(0).toString();
			}
		}

		else {
			treasurycode = "Not Available";
		}
		return treasurycode;
	}

	public String getDsgnNameForFormB(long locationId, long loggedInpostId) {
		logger.info("in getDsgnNameForFormB...........");
		List list = new ArrayList();

		String designName = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  rlt.designName ");
		query.append(" from OrgDdoMst rlt");
		// query.append(" where rlt.postId ="+loggedInpostId+" and
		// rlt.locationCode="+locationId+" ");
		query.append(" where rlt.locationCode=" + locationId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getDsgnNameForFormB query :: " + sqlQuery);
		list = sqlQuery.list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				designName = list.get(0).toString();
			}
		}

		else {
			designName = "Not Available";
		}
		return designName;
	}

	public String getDdoCode(long locId, String DeptName) {
		List list = new ArrayList();
		String rtnStr = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("SELECT ddo_code FROM mst_dcps_ddo_office where LOC_ID=" + locId
				+ " and DDO_OFFICE='Yes' and off_name like '%" + DeptName + "%' order By Off_Name");

		Query hsqlQuery = hibSession.createSQLQuery(query.toString());
		list = hsqlQuery.list();

		if (list != null && list.size() > 0)
			rtnStr = list.get(0).toString();

		return rtnStr;
	}

	public long getEmpIdFromHreisempMst(long empId) {
		logger.info("in getEmpIdFromHreisempMst...........");
		List list = new ArrayList();

		long employeeid = 0;

		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  distinct rlt.empId ");
		query.append(" from HrEisEmpMst rlt");
		query.append(" where rlt.orgEmpMst.empId =" + empId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getEmpIdFromHreisempMst query :: " + sqlQuery);

		if (sqlQuery.list() != null) {
			list = sqlQuery.list();
			if (list != null && list.size() > 0) {
				if (list.get(0) != null) {
					employeeid = Long.parseLong(list.get(0).toString());
				}
			}
		}

		else {
			employeeid = 0;
		}

		logger.info("employeeid " + employeeid);
		return employeeid;
	}

	public String getGpfAcctNuFromEmpId(long EMPLOYEEID) {
		logger.info("in getGpfAcctNuFromEmpId...........");
		List list = new ArrayList();

		String gpf = "";

		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  gpf.gpfAccNo ");
		query.append(" from HrPayGpfBalanceDtls gpf");
		query.append(" where gpf.userId in (select emp.orgUserMst.userId from OrgEmpMst emp where emp.empId="
				+ EMPLOYEEID + ") ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getGpfAcctNuFromEmpId query :: " + sqlQuery);
		list = sqlQuery.list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				gpf = list.get(0).toString();
			}
		}

		else {
			gpf = "";
		}
		return gpf;
	}

	public List getGpfAcctNuFromByEisEmpId(long empId) {
		logger.info("in getGpfAcctNuFromEmpId...........");
		// List list = new ArrayList();

		// String gpf = "";

		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append(
				"select dtls.gpfAccNo,dtls.pfSeries   from  HrPayGpfBalanceDtls dtls , HrEisEmpMst eisEmp  where eisEmp.orgEmpMst.orgUserMst.userId = dtls.userId and eisEmp.empId="
						+ empId);
		// query.append("select g.gpf_acc_no from hr_pay_gpf_details g,hr_eis_emp_mst
		// em,org_emp_mst oe where oe.emp_id = em.emp_mpg_id and oe.user_id= g.USER_ID
		// and em.emp_id ="+EMPLOYEEID);
		// select from HrPayGpfDtls dtls , HrEisEmpMst eisEmp where
		// eisEMp.orgEmpMst.orgUserMst.userId = dtls.orgUserMst.userId and eisEmp.empId=

		// "select from HrPayGpfDtls dtls , HrEisEmpMst eisEmp where
		// eisEMp.orgEmpMst.orgUserMst.userId = dtls.orgUserMst.userId and
		// eisEmp.empId="+EMPLOYEEID

		Query sqlQuery = hibSession.createQuery(query.toString());

		/*
		 * list= sqlQuery.list(); if(list.size()>0) { if(list.get(0) !=null) { gpf =
		 * list.get(0).toString(); } }
		 * 
		 * else { gpf=""; } logger.info("returning value of gpf is"+gpf);
		 */
		return sqlQuery.list();
	}

	public String getGpfAccNoByUserId(long userId) {
		String gpfAccNo = "";
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select GPF_ACC_NO  from hr_pay_gpf_details where USER_ID =" + userId);

		Query sqlQuery = hibSession.createSQLQuery(query.toString());

		list = sqlQuery.list();
		if (list != null && list.size() > 0)
			gpfAccNo = list.get(0).toString();

		return gpfAccNo;
	}

	public List getLoanDetailsForHBACA(long loanId, long BillNo, long Month, long year, long locId) {
		// HrLoanEmpDtls hrloanempDtls = new HrLoanEmpDtls();
		// HrLoanEmpPrinRecoverDtls printloan = new HrLoanEmpPrinRecoverDtls();

		List list = new ArrayList();
		Session hibSession = getSession();

		// String HQ_QUERY = "select
		// printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empPrefix||'
		// '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||'
		// '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname,loanempDtls.loanPrinAmt,loanempDtls.loanPrinInstNo,printloan.totalRecoveredAmt,printloan.totalRecoveredInst
		// from HrLoanEmpPrinRecoverDtls printloan,HrLoanEmpDtls loanempDtls where
		// printloan.hrLoanEmpDtls.empLoanId=loanempDtls.empLoanId"
		// +" and printloan.totalRecoveredAmt!=0 and
		// loanempDtls.hrLoanAdvMst.loanAdvId="+loanId;

		String HQL_QUERY = "select paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname||' '||paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empMname,";
		HQL_QUERY += "paybillLoan.totalAmt,paybillLoan.recoveredAmt,paybillLoan.totalInst,paybillLoan.recoveredInst";
		HQL_QUERY += ",dsgn.dsgnName,paybillLoan.hrLoanEmpDtls.hrEisEmpMst.sevarthEmpCode ";
		if (loanId == 70)
			HQL_QUERY += ",paybillLoan.paybillId.loanInt5056";
		else if (loanId == 73)
			HQL_QUERY += ",paybillLoan.paybillId.loanInt5067";
		else if (loanId == 59)
			HQL_QUERY += ",paybillLoan.paybillId.adv5059";
		else if (loanId == 52)
			HQL_QUERY += ",paybillLoan.paybillId.adv5052";
		else if (loanId == 68)
			HQL_QUERY += ",paybillLoan.paybillId.adv5068";
		else if (loanId == 72)
			HQL_QUERY += ",paybillLoan.paybillId.loanInt5051";
		else
			HQL_QUERY += ",paybillLoan.paybillId.adv5053";

		HQL_QUERY += ",hrloanemp.voucherNo,hrloanemp.voucherDate";

		// HQL_QUERY+= " from HrPayPaybillLoanDtls paybillLoan,HrLoanEmpDtls
		// hrloan,OrgUserpostRlt up, OrgPostDetailsRlt det ";
		HQL_QUERY += " from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
		HQL_QUERY += " OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
		HQL_QUERY += " HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
		HQL_QUERY += " HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde,HrPayPaybillLoanDtls paybillLoan,";
		HQL_QUERY += " HrLoanEmpDtls hrloanemp";

		if (loanId == 70 || loanId == 73 || loanId == 68 || loanId == 72)
			HQL_QUERY += " ,HrLoanEmpIntRecoverDtls printloan ";

		// HQL_QUERY+=" where
		// printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId ";
		// HQL_QUERY+=" where hrloan.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and
		// up.orgUserMst.userId=paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.orgUserMst.userId";
		// HQL_QUERY+=" and det.orgPostMst.postId=up.orgPostMstByPostId.postId and
		// up.activateFlag=1";
		// HQL_QUERY+=" and paybillLoan.cmnLocationMst.locId="+locId+" and
		// paybillLoan.hrLoanAdvMst.loanAdvId="+loanId+" ";

		HQL_QUERY += " where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
		HQL_QUERY += " and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";
		HQL_QUERY += " and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";
		HQL_QUERY += " and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";
		HQL_QUERY += " and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and paybillLoan.paybillId.id=paybill.id";

		HQL_QUERY += " and hrloanemp.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and hrloanemp.hrEisEmpMst.empId=eisemp.empId and paybillLoan.hrLoanAdvMst.loanAdvId="
				+ loanId + "";

		if (loanId == 70)
			HQL_QUERY += " and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
		if (loanId == 73)
			HQL_QUERY += " and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
		if (loanId == 72)
			HQL_QUERY += " and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
		if (loanId == 68)
			HQL_QUERY += " and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId";

		HQL_QUERY += " and head.billTypeId=2500337 and userpost.activateFlag=1 and head.billNo=" + BillNo
				+ " and head.month='" + Month + "' and head.year='" + year + "' and head.approveFlag in(0,1)";
		HQL_QUERY += " and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId=" + locId
				+ " and head.hrPayPaybill=paybill.paybillGrpId ";

		Query query = hibSession.createQuery(HQL_QUERY);
		logger.info("==> getLoanDetailsForHBACA query :: " + query);
		logger.info("==> getLoanDetailsForHBACA query :: " + query);
		list = query.list();
		return list;
	}

	public List getTreasuryCodeAndTreasuryName(String ddocode) {
		logger.info("in getTreasuryCodeAndTreasuryName...........");
		List list = new ArrayList();

		// String treasurycode = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  distinct rlt.locName,ddo.locationCode ");
		query.append(" from CmnLocationMst rlt,RltDdoOrg ddo");
		query.append(" where rlt.locationCode = ddo.locationCode and ddo.ddoCode='" + ddocode + "'");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getTreasuryCodeAndTreasuryName query :: " + sqlQuery);
		list = sqlQuery.list();

		return list;
	}

	public List getLoanDetailsFor7610(long loanId, long BillNo, long Month, long year, long locId) {

		List list = new ArrayList();
		Session hibSession = getSession();

		// String HQ_QUERY = "select
		// printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empPrefix||'
		// '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||'
		// '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname,loanempDtls.loanPrinAmt,loanempDtls.loanPrinInstNo,printloan.totalRecoveredAmt,printloan.totalRecoveredInst
		// from HrLoanEmpPrinRecoverDtls printloan,HrLoanEmpDtls loanempDtls where
		// printloan.hrLoanEmpDtls.empLoanId=loanempDtls.empLoanId"
		// +" and printloan.totalRecoveredAmt!=0 and
		// loanempDtls.hrLoanAdvMst.loanAdvId="+loanId;

		String HQL_QUERY = "select printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empMname,";
		HQL_QUERY += "paybillLoan.totalAmt,paybillLoan.recoveredAmt,paybillLoan.totalInst,paybillLoan.recoveredInst";
		HQL_QUERY += ",det.orgDesignationMst.dsgnName,printloan.hrLoanEmpDtls.hrEisEmpMst.sevarthEmpCode ";
		if (loanId == 58)
			HQL_QUERY += ",paybillLoan.paybillId.loan5058";
		else if (loanId == 51)
			HQL_QUERY += ",paybillLoan.paybillId.loan5051";
		else if (loanId == 56)
			HQL_QUERY += ",paybillLoan.paybillId.loan5056";
		else if (loanId == 67)
			HQL_QUERY += ",paybillLoan.paybillId.loan5067";
		// HQL_QUERY+= "
		// ,printloan.hrLoanEmpDtls.voucherNo,printloan.hrLoanEmpDtls.loanDate ";
		HQL_QUERY += " ,printloan.hrLoanEmpDtls.voucherNo,printloan.hrLoanEmpDtls.voucherDate ";
		HQL_QUERY += " from PaybillHeadMpg ph,HrPayPaybillLoanDtls paybillLoan,HrLoanEmpPrinRecoverDtls printloan,OrgUserpostRlt up, OrgPostDetailsRlt det,HrEisEmpMst eisemp,HrEisOtherDtls othe,HrPayOrderHeadPostMpg headpost,HrPayOrderHeadMpg orderhead ";
		HQL_QUERY += " where printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId ";
		HQL_QUERY += " and up.orgUserMst.userId=printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.orgUserMst.userId";
		HQL_QUERY += " and det.orgPostMst.postId=up.orgPostMstByPostId.postId and othe.hrEisEmpMst.empId=eisemp.empId and eisemp.empId=paybillLoan.paybillId.hrEisEmpMst.empId and up.activateFlag=1 and paybillLoan.recoveryType=300428";
		HQL_QUERY += " and headpost.orderHeadId=orderhead.orderHeadId and headpost.postId=paybillLoan.paybillId.orgPostMst.postId and headpost.postId=det.orgPostMst.postId and  paybillLoan.cmnLocationMst.locId="
				+ locId + " and paybillLoan.hrLoanAdvMst.loanAdvId=" + loanId + "";
		HQL_QUERY += " and paybillLoan.paybillId.paybillGrpId = ";
		HQL_QUERY += " ph.hrPayPaybill and ph.billNo.dcpsDdoBillGroupId=" + BillNo;
		HQL_QUERY += " and ph.month=" + Month + "";
		HQL_QUERY += " and ph.year=" + year + " and ph.cmnLocationMst.locId=" + locId + "";
		HQL_QUERY += " and ph.approveFlag in(0,1)";
		Query query = hibSession.createQuery(HQL_QUERY);

		logger.info("==> getLoanDetailsForHBACA query :: " + query);
		logger.info("==> getLoanDetailsForHBACA query :: " + query);

		list = query.list();

		return list;

	}

	// added by manish

	public List getHRRecoveryList(long BillNo, long Month, long year, long locationId, long CustodianId, String selDate,
			String endDate) {
		logger.info("in getHRRecoveryList...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		String HQL_QUERY = "select orgEmpMst.empPrefix||' '||orgEmpMst.empLname||' '||orgEmpMst.empFname||' '||orgEmpMst.empMname,det.orgDesignationMst.dsgnName,"
				+ "pb.hrEisEmpMst.sevarthEmpCode,pb.deduc9550,qtrdtls.cmnAddressMst.addressId, "
				// +"qtrdtls.cmnAddressMst.houseName,qtrdtls.cmnAddressMst.socBuildName,qtrdtls.cmnAddressMst.street,qtrdtls.cmnAddressMst.area,"
				+ "pb.basic0101,pb.basic0102,pb.deduc1041,pb.deduc9707 "
				+ "from OrgEmpMst orgEmpMst, OrgUserpostRlt up, OrgPostDetailsRlt det,HrPayPaybill pb,"
				+ "HrEisQtrMst qtrdtls " + "where pb.hrEisEmpMst.orgEmpMst.empId=orgEmpMst.empId"
				+ " and up.orgUserMst.userId=orgEmpMst.orgUserMst.userId"
				+ " and det.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1 and ( qtrdtls.allocationEndDate>='"
				+ selDate + "' or qtrdtls.allocationEndDate is null) " + " and ( qtrdtls.allocationStartDate<='"
				+ endDate
				+ "' or qtrdtls.allocationStartDate is null) and qtrdtls.orgUserMstByAllocatedTo.userId=pb.hrEisEmpMst.orgEmpMst.orgUserMst.userId"
				// +" and pb.deduc9550>0"
				+ " and pb.paybillGrpId in (" + " select ph.hrPayPaybill from PaybillHeadMpg ph where ph.billNo in ("
				+ " select mpg.dcpsDdoBillGroupId from MstDcpsBillGroup mpg where mpg.dcpsDdoBillGroupId=" + BillNo + ""
				+ " and mpg.LocId=" + locationId + ") and ph.month=" + Month
				+ " and qtrdtls.hrCustodianTypeMst.custodianId=" + CustodianId + "" + " and ph.year=" + year + ""
				+ " and ph.approveFlag in(0,1))";

		Query query = hibSession.createQuery(HQL_QUERY);
		logger.info("==> getHRRecoveryList query :: " + query);
		list = query.list();
		logger.info("list:::::::::::::: " + list.size());
		return list;
	}

	public List getHRRecoveryList(long BillNo, long Month, long year, long locationId, String selDate, String endDate) {
		logger.info("in getHRRecoveryList...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		String HQL_QUERY = "select orgEmpMst.empPrefix||' '||orgEmpMst.empLname||' '||orgEmpMst.empFname||' '||orgEmpMst.empMname,det.orgDesignationMst.dsgnName,"
				+ "pb.hrEisEmpMst.sevarthEmpCode,pb.deduc9550,qtrdtls.cmnAddressMst.addressId, "
				// +"qtrdtls.cmnAddressMst.houseName,qtrdtls.cmnAddressMst.socBuildName,qtrdtls.cmnAddressMst.street,qtrdtls.cmnAddressMst.area,"
				+ "pb.basic0101,pb.basic0102,pb.deduc1041,pb.deduc9707 "
				+ "from OrgEmpMst orgEmpMst, OrgUserpostRlt up, OrgPostDetailsRlt det,HrPayPaybill pb,PaybillHeadMpg ph,"
				+ "HrEisQtrMst qtrdtls " + "where pb.hrEisEmpMst.orgEmpMst.empId=orgEmpMst.empId"
				+ " and up.orgUserMst.userId=orgEmpMst.orgUserMst.userId"
				+ " and det.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1 and ( qtrdtls.allocationEndDate>='"
				+ selDate + "' or qtrdtls.allocationEndDate is null)" + " and ( qtrdtls.allocationStartDate<='"
				+ endDate
				+ "' or qtrdtls.allocationStartDate is null) and qtrdtls.orgUserMstByAllocatedTo.userId=pb.hrEisEmpMst.orgEmpMst.orgUserMst.userId"
				// +" and pb.deduc9550>0"
				+ " and pb.paybillGrpId  = ph.hrPayPaybill and ph.billNo =" + BillNo + "" + "  and ph.month=" + Month
				+ "" + " and ph.year=" + year + "" + " and ph.approveFlag in(0,1))";

		Query query = hibSession.createQuery(HQL_QUERY);
		logger.info("==> getHRRecoveryList query :: " + query);
		list = query.list();
		logger.info("list:::::::::::::: " + list.size());
		return list;
	}

	// Added by muneendra for address in HRR report
	public List<CmnAddressMst> getAddressList(long addressId, long locationId) {
		logger.info("In getAddressList...........");
		List<CmnAddressMst> addressList = new ArrayList();
		Session hibSession = getSession();

		String strQuery = " from CmnAddressMst cam where cam.addressId = " + addressId + " and cam.locId = "
				+ locationId;
		Query query = hibSession.createQuery(strQuery);
		logger.info("==> getAddressList query :: " + query);
		addressList = query.list();
		logger.info("list:::::::::::::: " + addressList.size());
		return addressList;

	}

	public List<CmnAddressMst> getAddressList(long addressId) {
		logger.info("In getAddressList...........");
		List<CmnAddressMst> addressList = new ArrayList();
		Session hibSession = getSession();

		String strQuery = " from CmnAddressMst cam where cam.addressId = " + addressId;
		Query query = hibSession.createQuery(strQuery);
		logger.info("==> getAddressList query :: " + query);
		addressList = query.list();
		logger.info("list:::::::::::::: " + addressList.size());
		return addressList;

	}

	// Modified by Kishan - start
	public Map<Long, HrPayPayslip> getPayslipData(long billNo, int month, int year) {
		List<HrPayPayslip> payslipList = null;
		Session hibSession = getSession();
		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append("from  HrPayPayslip paySlip ");
		sqlQuery.append("where paySlip.month =" + month + " ");
		sqlQuery.append("and paySlip.year =" + year + " ");
		sqlQuery.append("and paySlip.mstDcpsBillGroup.dcpsDdoBillGroupId = " + billNo + " ");
		// sqlQuery.append("and paySlip.hrEisEmpMst.empId in ( " + empId + ") ");
		Query query = hibSession.createQuery(sqlQuery.toString());
		payslipList = query.list();
		int size = payslipList.size();
		Map<Long, HrPayPayslip> dataMap = new HashMap<Long, HrPayPayslip>();
		logger.info("The result of get payslip data query is" + query.toString());
		HrPayPayslip payPayslip = null;
		for (int i = 0; i < size; i++) {
			payPayslip = (HrPayPayslip) payslipList.get(i);
			dataMap.put(payPayslip.getHrEisEmpMst().getEmpId(), payPayslip);
		}
		return dataMap;
	}

	// Modified by Kishan - end

	public double getPayslipData(long empId, long billNo, int month, int year, String col) {
		List payslipList = null;
		Session hibSession = getSession();
		// select paySlip."+paySlipCol+"

		logger.info("The pandey  of get payslip data query is" + col.toString());

		logger.info("The pandey  of get payslip data query is" + col);

		String strQuery = " select paySlip." + col + " from   Hr_Pay_Payslip as paySlip where paySlip.PAYSLIP_MONTH="
				+ month + " and paySlip.PAYSLIP_YEAR=" + year + " and paySlip.BILL_NO=" + billNo
				+ " and paySlip.EMP_ID=" + empId;
		Query query = hibSession.createSQLQuery(strQuery);
		payslipList = query.list();
		if (payslipList != null && payslipList.size() > 0)
			return Double.parseDouble(payslipList.get(0).toString());
		else
			return 0;

	}

	public Map getPaySlipEdpMpgMap() {
		Map paybillEdpMpg = new HashMap();
		List lstPaybillEdpMpg = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append(
				"select mst.edp_code,mst.payslip_col from hr_pay_edp_mst mst  , rlt_bill_type_edp rlt ,hr_pay_edp_compo_mpg mpg where rlt.subject_id = 3 and rlt.edp_code=mst.edp_code and mpg.TYPE_EDP_ID=rlt.type_edp_id and payslip_col is not null and mpg.CMN_LOOKUP_ID in (2500134)");
		Query query = hibSession.createSQLQuery(strBfr.toString());

		lstPaybillEdpMpg = (ArrayList) query.list();
		logger.info("dtlsList size for edp column mapping is:::::::::" + lstPaybillEdpMpg.size());
		for (int i = 0; i < lstPaybillEdpMpg.size(); i++) {
			Object arr[] = (Object[]) lstPaybillEdpMpg.get(i);

			paybillEdpMpg.put(arr[0].toString(), arr[1].toString());
		}
		return paybillEdpMpg;
	}

	public Map getPaySlipEdpDeductionsMpgMap() {
		Map paybillEdpMpg = new HashMap();
		List lstPaybillEdpMpg = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append(
				"select mst.edp_code,mst.payslip_col,mst.EXP_RCP_REC from hr_pay_edp_mst mst  , rlt_bill_type_edp rlt ,hr_pay_edp_compo_mpg mpg where rlt.subject_id = 3 and rlt.edp_code=mst.edp_code and mpg.TYPE_EDP_ID=rlt.type_edp_id and payslip_col is not null and mpg.CMN_LOOKUP_ID in (2500135,2500136,2500137)");
		Query query = hibSession.createSQLQuery(strBfr.toString());

		lstPaybillEdpMpg = (ArrayList) query.list();
		logger.info("dtlsList size for edp column mapping is:::::::::" + lstPaybillEdpMpg.size());
		for (int i = 0; i < lstPaybillEdpMpg.size(); i++) {
			Object arr[] = (Object[]) lstPaybillEdpMpg.get(i);
			if (arr[2].equals("INT"))
				paybillEdpMpg.put(arr[0].toString() + "INT", arr[1].toString());
			else
				paybillEdpMpg.put(arr[0].toString(), arr[1].toString());
		}
		return paybillEdpMpg;
	}

	public Map getPaySlipEdpNonGovernmentMpgMap() {
		Map paybillEdpMpg = new HashMap();
		List lstPaybillEdpMpg = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append(
				"select mst.edp_code,mst.payslip_col from hr_pay_edp_mst mst  , rlt_bill_type_edp rlt ,hr_pay_edp_compo_mpg mpg where rlt.subject_id = 3 and rlt.edp_code=mst.edp_code and mpg.TYPE_EDP_ID=rlt.type_edp_id and payslip_col is not null and mpg.CMN_LOOKUP_ID in (300160)");
		Query query = hibSession.createSQLQuery(strBfr.toString());

		lstPaybillEdpMpg = (ArrayList) query.list();
		logger.info("dtlsList size for edp column mapping is:::::::::" + lstPaybillEdpMpg.size());
		for (int i = 0; i < lstPaybillEdpMpg.size(); i++) {
			Object arr[] = (Object[]) lstPaybillEdpMpg.get(i);

			paybillEdpMpg.put(arr[0].toString(), arr[1].toString());
		}
		return paybillEdpMpg;
	}

	public Map getEdpShortNameEdpAllowancesMpgMap() {

		Map paybillEdpMpg = new HashMap();
		List lstPaybillEdpMpg = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append(
				"SELECT rlt.edp_code,rlt.EDP_SHORT_NAME FROM rlt_bill_type_edp rlt,hr_pay_edp_compo_mpg mpg where subject_id=3 and  EDP_SHORT_NAME is not null  and rlt.type_edp_id=mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID in (2500134)");
		Query query = hibSession.createSQLQuery(strBfr.toString());

		lstPaybillEdpMpg = (ArrayList) query.list();
		logger.info("dtlsList size forgetEdpShortNameEdpMpgMap is:::::::::" + lstPaybillEdpMpg.size());
		for (int i = 0; i < lstPaybillEdpMpg.size(); i++) {
			Object arr[] = (Object[]) lstPaybillEdpMpg.get(i);

			paybillEdpMpg.put(arr[0].toString(), arr[1].toString());
		}
		return paybillEdpMpg;
	}

	public Map getEdpShortNameEdpDeductionsMpgMap() {

		Map paybillEdpMpg = new HashMap();
		List lstPaybillEdpMpg = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append(
				"SELECT rlt.edp_code,rlt.EDP_SHORT_NAME,rlt.exp_rcp_rec FROM rlt_bill_type_edp rlt,hr_pay_edp_compo_mpg mpg where subject_id=3 and  EDP_SHORT_NAME is not null  and rlt.type_edp_id=mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID in (2500135,2500136,2500137)");
		Query query = hibSession.createSQLQuery(strBfr.toString());

		lstPaybillEdpMpg = (ArrayList) query.list();
		logger.info("dtlsList size forgetEdpShortNameEdpMpgMap is:::::::::" + lstPaybillEdpMpg.size());
		for (int i = 0; i < lstPaybillEdpMpg.size(); i++) {
			Object arr[] = (Object[]) lstPaybillEdpMpg.get(i);
			if (arr[2].equals("INT"))
				paybillEdpMpg.put(arr[0].toString() + "INT", arr[1].toString());
			else
				paybillEdpMpg.put(arr[0].toString(), arr[1].toString());
		}
		return paybillEdpMpg;
	}

	public Map getEdpShortNameEdpNonGovernmentMpgMap() {

		Map paybillEdpMpg = new HashMap();
		List lstPaybillEdpMpg = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append(
				"SELECT rlt.edp_code,rlt.EDP_SHORT_NAME FROM rlt_bill_type_edp rlt,hr_pay_edp_compo_mpg mpg where subject_id=3 and  EDP_SHORT_NAME is not null  and rlt.type_edp_id=mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID in (300160)");
		Query query = hibSession.createSQLQuery(strBfr.toString());

		lstPaybillEdpMpg = (ArrayList) query.list();
		logger.info("dtlsList size forgetEdpShortNameEdpMpgMap is:::::::::" + lstPaybillEdpMpg.size());
		for (int i = 0; i < lstPaybillEdpMpg.size(); i++) {
			Object arr[] = (Object[]) lstPaybillEdpMpg.get(i);

			paybillEdpMpg.put(arr[0].toString(), arr[1].toString());
		}
		return paybillEdpMpg;
	}

	// Kishan - start
	public Map<String, Map> getAllEdpCode(long locId) {
		Map<String, Map> returnMap = new HashMap<String, Map>();
		List resultList = null;
		Map allowEdpMap = new HashMap();
		Map deducEdpMap = new HashMap();
		Map loanEdpMap = new HashMap();
		Map advanceEdpMap = new HashMap();
		Map paylsipColEdpMpg = new HashMap();
		Map paylsipColEdpDeductionMpg = new HashMap();
		Map edpShortNameEdpAllowancesMpg = new HashMap();
		Map edpShortNameEdpDeductionsMpg = new HashMap();
		Map typeIdPayslipCol = new HashMap();
		Map paylsipColEdpLoan = new HashMap();
		Map edpShortNameEdpLoanMpg = new HashMap();

		Session hibSession = getSession();
		StringBuffer queryBuff = new StringBuffer();
		/*
		 * queryBuff.append("select mpg.type_id, ");//0
		 * queryBuff.append("rlt.edp_code, ");//1
		 * queryBuff.append("mpg.CMN_LOOKUP_ID, ");//2
		 * queryBuff.append("mst.edp_code, ");//3
		 * queryBuff.append("mst.payslip_col, ");//4
		 * queryBuff.append("mst.EXP_RCP_REC, ");//5
		 * queryBuff.append("rlt.EDP_SHORT_NAME ");//6 queryBuff.
		 * append("from rlt_bill_type_edp rlt, hr_pay_edp_compo_mpg mpg, hr_pay_edp_mst mst "
		 * ); queryBuff.append("where mpg.TYPE_EDP_ID  = rlt.type_edp_id ");
		 * queryBuff.append("and rlt.subject_id = 3 ");
		 * queryBuff.append("and rlt.edp_code=mst.edp_code ");
		 * queryBuff.append("and mst.payslip_col is not null ");
		 * queryBuff.append("order by mpg.CMN_LOOKUP_ID ");
		 */

		queryBuff.append("SELECT COMPO_TYPE, ");// 0
		queryBuff.append("COMPO_ID, ");// 1
		queryBuff.append("EDP_CODE, ");// 2
		queryBuff.append("PAYSLIP_COLUMN, ");// 3
		queryBuff.append("EDP_SHORT_NAME ");// 4
		queryBuff.append("FROM HR_PAY_COMPO_COLUMN_MPG ORDER by COMPO_TYPE ");

		logger.info("got all edp code query is -->" + queryBuff.toString());

		Query sqlQuery = hibSession.createSQLQuery(queryBuff.toString());
		resultList = sqlQuery.list();
		int size = resultList.size();
		Object[] data = null;
		long lookupId = 0;
		for (int i = 0; i < size; i++) {
			data = (Object[]) resultList.get(i);
			lookupId = Long.valueOf(data[0].toString());

			logger.info("Executing for lookupId " + lookupId);
			if (lookupId == 2500134) {

				allowEdpMap.put(data[1].toString(), data[2].toString());
				paylsipColEdpMpg.put(data[2].toString(), data[3].toString());
				edpShortNameEdpAllowancesMpg.put(data[2].toString(), data[4].toString());
			} else if (lookupId == 2500135) {

				deducEdpMap.put(data[1].toString(), data[2].toString());
				paylsipColEdpDeductionMpg.put(data[2].toString(), data[3].toString());
				edpShortNameEdpDeductionsMpg.put(data[1].toString(), data[4].toString());
			} else if (lookupId == 2500136) {
				advanceEdpMap.put(data[1].toString(), data[2].toString());
				paylsipColEdpDeductionMpg.put(data[2].toString(), data[3].toString());
				edpShortNameEdpLoanMpg.put(data[1].toString(), data[4].toString());
				typeIdPayslipCol.put(data[1].toString(), data[3].toString());
				paylsipColEdpLoan.put(data[1].toString().concat("_REC"), data[3].toString());
			} else if (lookupId == 2500137) {

				loanEdpMap.put(data[1].toString(), data[2].toString());
				paylsipColEdpDeductionMpg.put(data[2].toString(), data[3].toString());
				edpShortNameEdpLoanMpg.put(data[1].toString(), data[4].toString());
				paylsipColEdpLoan.put(data[1].toString().concat("_LOAN"), data[3].toString());
				typeIdPayslipCol.put(data[1].toString(), data[3].toString());
			}
			data = null;
		}

		returnMap.put("allowEdpMap", allowEdpMap);
		returnMap.put("deducEdpMap", deducEdpMap);
		returnMap.put("loanEdpMap", loanEdpMap);
		returnMap.put("advanceEdpMap", advanceEdpMap);
		returnMap.put("paylsipColEdpMpg", paylsipColEdpMpg);
		returnMap.put("paylsipColEdpDeductionMpg", paylsipColEdpDeductionMpg);
		returnMap.put("edpShortNameEdpAllowancesMpg", edpShortNameEdpAllowancesMpg);
		returnMap.put("edpShortNameEdpDeductionsMpg", edpShortNameEdpDeductionsMpg);
		returnMap.put("typeIdPayslipCol", typeIdPayslipCol);
		returnMap.put("paylsipColEdpLoan", paylsipColEdpLoan);
		returnMap.put("edpShortNameEdpLoanMpg", edpShortNameEdpLoanMpg);
		allowEdpMap = null;
		deducEdpMap = null;
		loanEdpMap = null;
		advanceEdpMap = null;
		paylsipColEdpMpg = null;
		paylsipColEdpDeductionMpg = null;
		edpShortNameEdpAllowancesMpg = null;
		edpShortNameEdpDeductionsMpg = null;
		typeIdPayslipCol = null;
		resultList = null;
		hibSession = null;
		queryBuff = null;
		sqlQuery = null;
		paylsipColEdpLoan = null;
		edpShortNameEdpLoanMpg = null;
		return returnMap;
	}

	// Kishan - end

	public Map getEdpCompoMapForDeductions(long locId) {
		Map returnMap = new HashMap();
		List llist = null;
		// List allColumList = new ArrayList();
		// List deducColumList = new ArrayList();
		// List loanColumnList = new ArrayList();
		// List advColumnList = new ArrayList();

		try {

			Session hibSession = getSession();

			String queryStr = "select mpg.type_id,rlt.edp_code from  rlt_bill_type_edp rlt, hr_pay_edp_compo_mpg mpg where mpg.TYPE_EDP_ID  = rlt.type_edp_id and rlt.subject_id=3 and mpg.CMN_LOOKUP_ID = 2500135";
			logger.info(queryStr);
			Query sqlQuery = hibSession.createSQLQuery(queryStr);
			llist = sqlQuery.list();

			for (int i = 0; i < llist.size(); i++) {

				Object[] arr = (Object[]) llist.get(i);

				returnMap.put(arr[0].toString(), arr[1].toString());
			}

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}

		return returnMap;
	}

	public Map getEdpCompoMapForLoans(long locId) {
		Map returnMap = new HashMap();
		List llist = null;
		// List allColumList = new ArrayList();
		// List deducColumList = new ArrayList();
		// List loanColumnList = new ArrayList();
		// List advColumnList = new ArrayList();

		try {

			Session hibSession = getSession();

			String queryStr = "select mpg.type_id,rlt.edp_code from  rlt_bill_type_edp rlt, hr_pay_edp_compo_mpg mpg where mpg.TYPE_EDP_ID  = rlt.type_edp_id and rlt.subject_id=3 and mpg.CMN_LOOKUP_ID = 2500137";
			logger.info(queryStr);
			Query sqlQuery = hibSession.createSQLQuery(queryStr);
			llist = sqlQuery.list();

			for (int i = 0; i < llist.size(); i++) {

				Object[] arr = (Object[]) llist.get(i);

				returnMap.put(arr[0].toString(), arr[1].toString());
			}

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}

		return returnMap;
	}

	public Map getEdpCompoMapForAdvances(long locId) {
		Map returnMap = new HashMap();
		List llist = null;
		// List allColumList = new ArrayList();
		// List deducColumList = new ArrayList();
		// List loanColumnList = new ArrayList();
		// List advColumnList = new ArrayList();

		try {

			Session hibSession = getSession();

			String queryStr = "select mpg.type_id,rlt.edp_code from  rlt_bill_type_edp rlt, hr_pay_edp_compo_mpg mpg where mpg.TYPE_EDP_ID  = rlt.type_edp_id and rlt.subject_id=3 and mpg.CMN_LOOKUP_ID = 2500136";
			logger.info(queryStr);
			Query sqlQuery = hibSession.createSQLQuery(queryStr);
			llist = sqlQuery.list();

			for (int i = 0; i < llist.size(); i++) {

				Object[] arr = (Object[]) llist.get(i);

				returnMap.put(arr[0].toString(), arr[1].toString());
			}

		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}

		return returnMap;
	}

	// ended by manish

	// added by manish
	public List<HrPayLocComMpg> getDepartmentComponents(long locId, long empId) {
		// HrPayLocComMpg componentMst = null;
		Session hibSession = getSession();
		List<HrPayLocComMpg> list = new ArrayList<HrPayLocComMpg>();

		StringBuffer query = new StringBuffer();

		query.append(
				"select compoMst from  HrPayLocComMpg compoMst,HrEisEmpCompGrpMst empCompoGrpMst  where compoMst.hrpaycompgrpmst.cmnLocationMst.locId="
						+ locId + " and compoMst.isactive=1" + "and empCompoGrpMst.hrEisEmpMst.empId=" + empId
						+ " and empCompoGrpMst.hrPayCompGrpMst.compoGrpId=compoMst.hrpaycompgrpmst.compoGrpId and empCompoGrpMst.isactive=1  and compoMst.cmnLookupMst.lookupId in (2500136,2500137,300160) ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		list = sqlQuery.list();

		logger.info("check this query for payslip " + query.toString() + " " + list.size());
		logger.info("check this query for payslip " + query.toString() + " " + list.size());

		return list;

	}

	public List<HrPayLocComMpg> getDepartmentComponentsWithoutFk(long locId) {
		// HrPayLocComMpg componentMst = null;
		Session hibSession = getSession();
		List<HrPayLocComMpg> list = new ArrayList<HrPayLocComMpg>();

		StringBuffer query = new StringBuffer();

		query.append(
				"select compoMst from  HrPayLocComMpg compoMst  where compoMst.hrpaycompgrpmst.cmnLocationMst.locId="
						+ locId
						+ " and compoMst.hrpaycompgrpmst.isactive=1  and compoMst.isactive=1 and compoMst.cmnLookupMst.lookupId in (2500136,2500137,300160)  ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		list = sqlQuery.list();

		logger.info("check this query for payslip " + query.toString() + " " + list.size());
		logger.info("check this query for payslip " + query.toString() + " " + list.size());

		return list;

	}

	// ended by manish

	public HrPayPaybillLoanDtls getLoanDtlsFromPaybill(long paybillId, String loanId) {
		HrPayPaybillLoanDtls hrPayPaybillLoanDtls = null;
		Session hibSession = getSession();
		List<HrPayPaybillLoanDtls> list = new ArrayList<HrPayPaybillLoanDtls>();
		StringBuffer query = new StringBuffer();
		query.append(" from HrPayPaybillLoanDtls loanDtls where  loanDtls.paybillId.id = ");
		query.append(paybillId);
		query.append(" and loanDtls.hrLoanAdvMst.loanAdvId = ");
		query.append(loanId);
		Query sqlQuery = hibSession.createQuery(query.toString());
		list = sqlQuery.list();
		hrPayPaybillLoanDtls = list != null && list.size() > 0 ? list.get(0) : null;
		return hrPayPaybillLoanDtls;
	}

	// added by manish
	public List getAllPaybillHeadMpgData(int month, int year, long billNo, int approveFlag, long paybillGrpId) {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		// strBfr.append("from HrPayPaybill pb where pb.cmnLocationMst.locId=");
		// strBfr.append(locId);
		// strBfr.append("and pb.paybillGrpId in ");
		strBfr.append("from PaybillHeadMpg ph where ph.billNo in ");
		strBfr.append("(select mpg.dcpsDdoBillGroupId from MstDcpsBillGroup mpg where ");
		strBfr.append("mpg.dcpsDdoBillGroupId=");
		strBfr.append(billNo);
		// strBfr.append(" and mpg.LocId=");
		// strBfr.append(locId);
		strBfr.append(") and ph.month=");
		strBfr.append(month);
		strBfr.append(" and ph.year=");
		strBfr.append(year);
		strBfr.append(" and ph.approveFlag=");
		strBfr.append(approveFlag);
		// strBfr.append(" and ph.hrPayPaybill.paybillGrpId=");
		strBfr.append(" and ph.hrPayPaybill=");
		strBfr.append(paybillGrpId);
		// strBfr.append(")");
		Query query = hibSession.createQuery(strBfr.toString());
		return query.list();
	}

	// ended

	// Added by abhilash for gpfacctno

	public String getGPFAcctNoFromEmployeeId(long employeeId, long locationId) {
		String gpfAcctNo = "";
		List list = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select gpf.gpfAccNo from HrPayGpfBalanceDtls gpf where gpf.userId in(select u.userId from OrgUserMst u where u.userId in (select e.orgUserMst.userId from OrgEmpMst e where e.empId="
						+ employeeId + ")) and gpf.cmnLocationMst.locId=" + locationId);
		logger.info("Query in getGPFAcctNoFromEmployeeId is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());

		list = query.list();
		if (list.size() > 0) {
			gpfAcctNo = query.uniqueResult().toString();
		} else {
			gpfAcctNo = "";
		}
		return gpfAcctNo;
	}

	// Ended by abhilash for gpfacctno
	// by khushal
	public List getDcpsClassGroup(long billNo) {
		Session hibSession = getSession();
		// StringBuffer strQuery = new StringBuffer("SELECT DCPS_CLASS_GROUP FROM
		// rlt_dcps_billgroup_classgroup where DCPS_BILLGROUP_ID="+billNo+ "");
		StringBuffer strQuery = new StringBuffer(
				"SELECT bgcg.dcpsClassGroup FROM RltBillgroupClassgroup bgcg where bgcg.dcpsBillGroupId=" + billNo
						+ "");

		Query query = hibSession.createQuery(strQuery.toString());
		logger.info("--Khushalquerytesting" + query);
		return query.list();
	}

	// end by khushal
	// Added by abhilash for GradeName

	public String getGradenameFromGradeId(long Grade, long langId) {
		String gradeName = "";
		List list = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("select gr.gradeName from OrgGradeMst gr where gr.gradeId=" + Grade
				+ " and gr.cmnLanguageMst=" + langId);
		logger.info("Query in getGradenameFromGradeId is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());

		list = query.list();
		if (list.size() > 0) {
			gradeName = list.get(0).toString();
		} else {
			gradeName = "Not Available";
		}
		return gradeName;
	}

	// Ended by abhilash for GradeName

	// Added by abhilash for dsgnName
	public String getDsgnNameFromDsgnId(long Designation, long langId) {
		logger.info("in getDsgnNameForFormB...........");
		List list = new ArrayList();

		String designName = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  distinct rlt.dsgnName ");
		query.append(" from OrgDesignationMst rlt");
		query.append(" where rlt.dsgnId =" + Designation + " and rlt.cmnLanguageMst.langId=" + langId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getDsgnNameFromDsgnId query :: " + sqlQuery);
		list = sqlQuery.list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				designName = list.get(0).toString();
			}
		}

		else {
			designName = "Not Available";
		}
		return designName;
	}

	// Ended by abhilash for dsgnName

	// added by manish
	public Long getDDOPostIdForDDOAsst(Long lLngPostId) {

		Long lLongDdoPostId = null;
		List lLstDdoDtls = null;

		try {

			Session hibSession = getSession();
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT ddoPostId");
			lSBQuery.append(" FROM  RltDdoAsst");
			lSBQuery.append(" WHERE asstPostId = :postId ");
			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("postId", lLngPostId);

			lLstDdoDtls = lQuery.list();

			if (lLstDdoDtls.size() != 0) {
				lLongDdoPostId = Long.valueOf(lLstDdoDtls.get(0).toString());
			}

		} catch (Exception e) {
			logger.error("Error is :" + e, e);

		}
		return lLongDdoPostId;
	}

	// ende by manish
	// added by ankit
	public OrgPostDetailsRlt getOrgPostDtlsObj(long postId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"from OrgPostDetailsRlt as dtls where dtls.orgPostMst.postId=" + postId);
		logger.info("Query in getGradenameFromGradeId is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		return (OrgPostDetailsRlt) query.uniqueResult();
	}

	// ended

	// Added by Abhilash For Getting Paybill_grp_id for outer

	public long getPaybillgrpIdForOuter(long BillNo, long month, long year, long locationId) {

		List list = new ArrayList();
		Session hibSession = getSession();
		long paybillgroupid = 0;
		logger.info("getPaybillgrpIdForOuter enterrrrrrrrrr");

		String query = " select head.hrPayPaybill";

		query += " from HrPayPaybill paybill,PaybillHeadMpg head";

		query += " where ";

		query += " head.billNo.dcpsDdoBillGroupId=" + BillNo + " and head.month='" + month + "' and head.year='" + year
				+ "' and head.approveFlag in(0,1) and head.billTypeId=2500337 ";

		query += "  and head.hrPayPaybill=paybill.paybillGrpId";

		Query query1 = hibSession.createQuery(query);

		logger.info("==> getPaybillgrpIdForOuter query :: " + query1);

		list = query1.list();
		if (list.size() > 0) {
			paybillgroupid = Long.parseLong(list.get(0).toString());
		} else {
			paybillgroupid = 0;
		}

		return paybillgroupid;

	}

	// Added by Abhilash For Showing Order Name in Outer
	// modified by manish
	public List getDsgnAndOrderNamesForOuter(long BillNo, long month, long year, long locationId, long paybillGroupId,
			long parentLocId) {

		/*
		 * List list = new ArrayList(); Session hibSession = getSession(); StringBuffer
		 * sb = new StringBuffer(); sb.append(" select "); // sb.
		 * append(" distinct postdtls.orgPostMst.postTypeLookupId.lookupName,dcpsDesig.desigCode,"
		 * ); sb
		 * .append("distinct dsgn.dsgnId,dsgn.dsgnName,ordermst.orderName,ordermst.orderDate,postdtls.orgPostMst.endDate,postdtls.orgPostMst.postTypeLookupId.lookupName,dcpsDesig.dsgnCode,rltGrp.dcpsClassGroup "
		 * ); // sb.append(", "); sb
		 * .append(" from  OrgDesignationMst dsgn,OrgPostDetailsRlt postdtls,RltBillgroupClassgroup rltGrp,MstPayrollDesignationMst dcpsDesig,"
		 * ); sb
		 * .append(" PaybillHeadMpg head,HrPayPaybill paybill left outer join paybill.hrEisEmpMst.orgEmpMst,"
		 * ); sb
		 * .append("HrPayOrderMst ordermst,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde"
		 * ); sb.append(",HrEisGISDtls gis "); sb.append(" where ");
		 * sb.append("gis.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and "); //and
		 * gis.orgGradeMst.gradeName =rltGrp.dcpsClassGroup
		 * 
		 * sb
		 * .append(" dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and postdtls.orgPostMst.postId=paybill.orgPostMst.postId "
		 * ); sb
		 * .append(" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and orde.orderId=ordermst.orderId "
		 * ); sb.append(" and head.billNo=" + BillNo + " and head.month=" + month);
		 * sb.append(" and head.year=" + year);
		 * sb.append(" and head.approveFlag in(0,1) and head.billTypeId=2500337 ");
		 * sb.append(" and head.cmnLocationMst.locId=" + locationId); sb
		 * .append(" and postdtls.orgPostMst.postId=payorder.postId and head.hrPayPaybill=paybill.paybillGrpId "
		 * ); sb.append(" and paybill.paybillGrpId=" + paybillGroupId);
		 * sb.append(" and dcpsDesig.fieldDeptId=" + parentLocId +
		 * " and  rltGrp.dcpsBillGroupId = " + BillNo +
		 * " and   dsgn.dsgnId=dcpsDesig.orgDesignationId "); // sb.
		 * append(" group by postdtls.orgPostMst.postTypeLookupId.lookupName,dsgn.dsgnId"
		 * ); // sb.
		 * append(" order by postdtls.orgPostMst.postTypeLookupId.lookupName,rltGrp.dcpsClassGroup,dcpsDesig.desigCode"
		 * ); sb
		 * .append(" order by postdtls.orgPostMst.postTypeLookupId.lookupName,dcpsDesig.dsgnCode,rltGrp.dcpsClassGroup"
		 * ); Query query1 = hibSession.createQuery(sb.toString());
		 * //System.out.println("enjoy below query" + sb.toString());
		 * logger.info("enjoy below query" + sb.toString()); list = query1.list();
		 * return list;
		 */

		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();

		sb.append(" select ");
		sb.append(
				"       distinct  commonpost4_.dsgn_id ,        commonpost4_.dsgn_name ,        hrpayorder8_.ORDER_NAME , ");
		sb.append(
				"        hrpayorder8_.ORDER_DATE ,        orgpostmst12_.end_date ,        commonpost4_.post_type  ,        commonpost4_.DSGN_CODE  , ");
		// sb.append(" empgrade3_.CLSGRP as col_7_0_ ");
		sb.append(" rlt.DCPS_CLASS_GROUP");
		sb.append("    from ");
		sb.append("        HR_PAY_PAYBILL hrpaypaybi5_ ");
		sb.append("    left outer join ");
		sb.append(
				"        hr_eis_emp_mst hreisempms6_             on hrpaypaybi5_.EMP_ID=hreisempms6_.emp_id     left outer join        org_emp_mst orgempmst7_             on hreisempms6_.emp_mpg_id=orgempmst7_.emp_id ");
		// sb.append(" left outer join EMP_GRADE empgrade3_ on
		// empgrade3_.GISEMPID=coalesce ( hrpaypaybi5_.EMP_ID, 0 ) and
		// empgrade3_.BILLGRP= "+BillNo+" , ");

		sb.append(
				"    ,   COMMMON_POST_DESIG commonpost4_  ,        HR_PAY_ORDER_MST hrpayorder8_,        HR_PAY_ORDER_HEAD_POST_MPG hrpayorder9_,        HR_PAY_ORDER_HEAD_MPG hrpayorder10_, ");
		sb.append("  hr_eis_gd_mpg gd , org_grade_mst grade , RLT_DCPS_BILLGROUP_CLASSGROUP rlt ,");
		sb.append("        org_post_mst orgpostmst12_  , HR_EIS_SGD_MPG sgd         where ");

		sb.append("        orgpostmst12_.post_id = commonpost4_.post_id ");

		sb.append("  and rlt.DCPS_CLASS_GROUP=grade.grade_name and rlt.DCPS_BILLGROUP_ID=" + BillNo);
		sb.append(" and  gd.sgd_grade_id=grade.grade_id and gd.sgd_desig_id=commonpost4_.dsgn_id");
		sb.append(" and gd.loc_id = " + locationId);
		sb.append(" and sgd.SGD_GD_ID = gd.GD_MAP_ID ");
		sb.append("        and commonpost4_.post_id=hrpaypaybi5_.post_id ");
		sb.append("        and hrpayorder10_.ORDER_HEAD_ID=hrpayorder9_.ORDER_HEAD_ID ");
		sb.append("        and commonpost4_.post_id= hrpayorder9_.POST_ID ");
		sb.append("        and hrpayorder10_.ORDER_ID=hrpayorder8_.ORDER_ID ");
		sb.append("        and hrpaypaybi5_.PAYBILL_GRP_ID in ( ");
		sb.append("        select paybill_id from  PAYBILL_HEAD_MPG paybillhea4_ ");
		sb.append("        where  paybillhea4_.loc_id=" + locationId);
		sb.append("        and paybillhea4_.bill_no= " + BillNo);
		sb.append("        and paybillhea4_.PAYBILL_MONTH=" + month);
		sb.append("        and paybillhea4_.PAYBILL_YEAR=" + year);
		sb.append("        and ");
		sb.append("            paybillhea4_.approve_flag in ( ");
		sb.append("                0 , 1 ");
		sb.append("            ) ");
		sb.append("        ) ");
		sb.append(" ");
		sb.append("        and commonpost4_.post_id=hrpayorder9_.POST_ID ");
		sb.append("        and commonpost4_.FIELD_DEPTID=" + parentLocId);
		sb.append(" ");
		sb.append("    order by ");
		sb.append("        commonpost4_.post_type,      rlt.DCPS_CLASS_GROUP,  commonpost4_.DSGN_CODE ");
		logger.info("outer first page query is " + sb.toString());
		Query query1 = hibSession.createSQLQuery(sb.toString());
		list = query1.list();
		List idList = new ArrayList();
		List realList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Object[] arr = (Object[]) list.get(i);
			String verifyString = arr[0].toString() + arr[5].toString() + arr[7].toString();
			if (arr[5].toString().contains("Perma")) {
				arr[4] = "";
			}
			if (!idList.contains(verifyString)) {
				idList.add(verifyString);
				realList.add(arr);
			}

		}
		return realList;

	}

	public HrEisGISDtls getGroupIdFromEmpId(long empId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer("from HrEisGISDtls  as gis where gis.hrEisEmpMst.empId=" + empId);
		logger.info("Query in getGradenameFromGradeId is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		return (HrEisGISDtls) query.uniqueResult();
	}

	// ende by manish

	public long getOrgGradeMst(long desigId, long locId) {
		Session hibSession = getSession();
		long retVar = 0;
		StringBuffer strQuery = new StringBuffer("select dcpsDesig.cadreTypeId ");
		strQuery.append("from  MstDcpsDesignation dcpsDesig,CmnLocationMst loc  ");
		strQuery.append("where dcpsDesig.orgDesignationId=" + desigId + " ");
		strQuery.append("and loc.locId = " + locId + " ");
		strQuery.append("and dcpsDesig.fieldDeptId = loc.parentLocId ");
		logger.info("Query in OrgGradeMst is " + strQuery.toString());
		logger.info("Query in OrgGradeMst is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		List lst = query.list();
		if (lst != null && lst.size() > 0) {
			retVar = Long.parseLong(lst.get(0).toString());
		}

		logger.info("return value before returning is " + retVar);
		return retVar;
	}

	public List getBrokenPeriodData(long empId, long locId, int month, long year) {
		Session hibSession = getSession();
		// List arr= new ArrayList();
		StringBuffer strQuery = new StringBuffer("from  MstBrokenPeriodPay broken where broken.eisEmpId=" + empId);
		strQuery.append(" and broken.monthId=" + month);
		strQuery.append(" and broken.yearId=" + year);
		strQuery.append(" and broken.locId=" + locId);
		logger.info("Query in getBrokenPeriodData is " + strQuery.toString());
		logger.info("Query in getBrokenPeriodData is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		// arr=query.list();
		logger.info("Check This Query for broken Period ");
		return query.list();

	}

	public List getBrokenPeriodAllowancesData(long allowCode, long empId) {
		Session hibSession = getSession();
		// List arr= new ArrayList();
		StringBuffer strQuery = new StringBuffer(
				"from  brokenAllow  RltBrokenPeriodAllow brokenAllow where brokenAllow.allowCode=" + allowCode);
		strQuery.append(" and brokenAllow.rltBrokenPeriodId.eisEmpId=" + empId);

		logger.info("Query in getBrokenPeriodData is " + strQuery.toString());
		logger.info("Query in getBrokenPeriodData is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		return query.list();
	}

	public List getBrokenPeriodDeductionsData(long deducCode, long empId) {
		Session hibSession = getSession();
		// List arr= new ArrayList();
		StringBuffer strQuery = new StringBuffer(
				"from  brokenDeduc  RltBrokenPeriodDeduc brokenDeduc where brokenDeduc.deducCode=" + deducCode);
		strQuery.append(" and brokenDeduc.rltBrokenPeriodId.eisEmpId=" + empId);

		logger.info("Query in getBrokenPeriodData is " + strQuery.toString());
		logger.info("Query in getBrokenPeriodData is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		// arr=query.list();
		return query.list();
	}

	// added by abhilash for getting bill list
	public List getDDOCodeByLoggedInlocId(long locId) {
		logger.info("Inside getDDOCodeByLoggedInPost");
		List<OrgDdoMst> ddoList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// intr
		// query.append(" from MstDcpsBillGroup sub where sub.finYearId="+finId+" and
		// sub.cmnLocationMst.locId=" + locId + " and sub.billId="+billNo);
		// query.append(" from OrgDdoMst as ddo where ddo.postId=" + postId);
		query.append(" from OrgDdoMst as ddo where locationCode='" + locId + "' ");
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		ddoList = sqlQuery.list();
		return ddoList;
	}

	// ended by abhilash

	// added by manish

	public List getLoanIntList() {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select comp.typeId from  com.tcs.sgv.eis.valueobject.RltBillTypeEdp rlt, HrPayEdpCompoMpg comp  where rlt.typeEdpId=comp.rltBillTypeEdp.typeEdpId and rlt.expRcpRec='INT' and comp.cmnLookupId in (2500137)");
		// System.out.println(strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		return query.list();
	}

	// ended by manish

	public String getPFSeriesFromEmployeeId(long employeeId, long locationId) {
		String pfSeries = "";
		List list = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select gpf.pfSeries from HrPayGpfBalanceDtls gpf where gpf.userId in(select u.userId from OrgUserMst u where u.userId in (select e.orgUserMst.userId from OrgEmpMst e where e.empId="
						+ employeeId + ")) and gpf.cmnLocationMst.locId=" + locationId);
		logger.info("Query in getGPFAcctNoFromEmployeeId is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());

		list = query.list();
		if (list.size() > 0) {
			logger.info("Query in getGPFAcctNoFromEmployeeId is " + list.get(0));
			if (list.get(0) != null) {
				pfSeries = query.uniqueResult().toString();
			} else {
				pfSeries = "";
			}
		} else {
			pfSeries = "";
		}
		return pfSeries;
	}

	// added by abhilash
	public List getAmountOfGroupWise(long locId, long paybillGroupid, long month, long year, long empId, String gis) {
		logger.info("Inside getAmountOfGroupWise");
		List<PaybillHeadMpg> list = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();

		if (gis.equals("1005")) {
			query.append(" select paybill.deduc1005 "); // GIS
		} else if (gis.equals("1004")) {
			query.append(" select paybill.deduc1004 "); // CENTRAL_GIS
		} else if (gis.equals("1001")) {
			query.append(" select paybill.deduc1001 "); // GIS_IAS
		} else if (gis.equals("1000")) {
			query.append(" select paybill.deduc1000  "); // GIS_IPS
		} else {
			query.append(" select paybill.deduc1002  "); // GIS_IFS
		}

		query.append(",gis.orgGradeMst.gradeName from HrEisGISDtls gis,HrPayPaybill paybill ");
		query.append(" where gis.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and  paybill.hrEisEmpMst.empId=" + empId
				+ " and paybill.cmnLocationMst.locId=" + locId + " and paybill.month=" + month + " and paybill.year="
				+ year + " and paybill.paybillGrpId=" + paybillGroupid
				+ " and paybill.hrEisEmpMst.empId is not null   ");
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be getAmountOfGroupWise is " + sqlQuery.toString());

		list = sqlQuery.list();
		return list;
	}

	public long getPaybillgroupId(long locId, long billNo, long month, long year) {
		logger.info("Inside getPaybillgroupId");
		List list = null;
		long paybillgroupid = 0;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" select bill.hrPayPaybill from PaybillHeadMpg bill where bill.cmnLocationMst.locId='" + locId
				+ "' and bill.billNo=" + billNo + " and bill.month=" + month + " and bill.year=" + year
				+ " and bill.approveFlag in(0,1) ");
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		list = sqlQuery.list();

		if (list.size() > 0) {
			if (list.get(0) != null) {
				paybillgroupid = Long.parseLong(list.get(0).toString());
			}
		}

		else {
			paybillgroupid = 0;
		}
		return paybillgroupid;

	}

	// ended by abhilash

	// added by manish
	public OrgDdoMst getOrgDdoMstFromDDOCode(String ddoCode) {

		List<OrgDdoMst> ddoList = null;
		OrgDdoMst orgDdoMst = new OrgDdoMst();
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" from OrgDdoMst as ddo where ddoCode='" + ddoCode + "' ");
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());
		ddoList = sqlQuery.list();
		if (ddoList != null && ddoList.size() > 0)
			orgDdoMst = ddoList.get(0);
		return orgDdoMst;
	}

	// ended by manish

	// Added by Abhilash

	public List<HrPayLocComMpg> getDepartmentComponentsWithoutFkForNonGovt(long locId) {
		// HrPayLocComMpg componentMst = null;
		Session hibSession = getSession();
		List<HrPayLocComMpg> list = new ArrayList<HrPayLocComMpg>();

		StringBuffer query = new StringBuffer();

		query.append(
				"select compoMst from  HrPayLocComMpg compoMst  where compoMst.hrpaycompgrpmst.cmnLocationMst.locId="
						+ locId
						+ " and compoMst.hrpaycompgrpmst.isactive=1  and compoMst.isactive=1 and compoMst.cmnLookupMst.lookupId=2500135 and compoMst.compId in (91,92,93,94,95,96,97,124,125,126,127) ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		list = sqlQuery.list();

		logger.info("check this query for payslip getDepartmentComponentsWithoutFkForNonGovt" + query.toString() + " "
				+ list.size());
		logger.info("check this query for payslip getDepartmentComponentsWithoutFkForNonGovt " + query.toString() + " "
				+ list.size());

		return list;

	}

	public long getDesignationIdEployeeIdForPaySlip(String empId) {
		logger.info("in getDesignationIdEployeeId...........");
		List list = new ArrayList();

		long designationId = 0;
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		if (empId != null && !empId.equals("") && !empId.equals("-1")) {
			query.append(" select  od.dsgnCode ");
			query.append(
					" from OrgEmpMst emp,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od, HrEisEmpMst hrEmp ");
			query.append(
					" where emp.orgUserMst.userId=up.orgUserMst.userId and up.activateFlag = 1 and det.orgPostMst.postId=up.orgPostMstByPostId.postId ");
			query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");

			query.append(" and emp.empId='" + empId + "' ");
			query.append(" and emp.empId = hrEmp.orgEmpMst.empId");

			Query sqlQuery = hibSession.createQuery(query.toString());

			logger.info("==> getDesignationIdEployeeId query :: " + sqlQuery);

			list = sqlQuery.list();
			logger.info("==> getDesignationIdEployeeId query list:: " + list.size());
			if (list.size() > 0) {
				if (list.get(0) != null) {
					designationId = Long.parseLong(list.get(0).toString());
				}
			} else {
				designationId = 0;
			}

		}

		else {
			designationId = 0;
		}

		return designationId;
	}

	// Ended by Abhilash
	// added by abhilash newly
	public String getEmployeeIdFromSevarthId(String sevarthId) {
		logger.info("in getEmployeeIdFromSevarthId...........");
		String employeeId = "";

		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();

		if (sevarthId != null && !sevarthId.equals("") && !sevarthId.equals("-1")) {
			query.append(" select  distinct eis.orgEmpMst.empId ");
			query.append(" from OrgEmpMst emp,HrEisEmpMst eis ");
			query.append(" where emp.empId = eis.orgEmpMst.empId");
			query.append(" and lower(eis.sevarthEmpCode) like lower('%" + sevarthId + "%') ");

			Query sqlQuery = hibSession.createQuery(query.toString());

			logger.info("==> getEmployeeIdFromSevarthId query :: " + sqlQuery);

			list = sqlQuery.list();
			logger.info("==> getEmployeeIdFromSevarthId query list size is ************" + list.size());
			if (list.size() > 0) {
				if (list.get(0) != null) {
					employeeId = list.get(0).toString();
				}
			} else {
				employeeId = "";
			}

		}

		else {
			employeeId = "";
		}

		return employeeId;
	}

	// for employidlist from sevarthid
	public List getEmployeeIdsFromSevarthId(String sevarthId) {
		logger.info("in getEmployeeIdsFromSevarthId...........");
		// String employeeId = "";

		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();

		if (sevarthId != null && !sevarthId.equals("") && !sevarthId.equals("-1")) {
			query.append(" select  distinct eis.orgEmpMst.empId,eis.empId ");
			query.append(" from OrgEmpMst emp,HrEisEmpMst eis ");
			query.append(" where emp.empId = eis.orgEmpMst.empId");
			query.append(" and lower(eis.sevarthEmpCode) like lower('" + sevarthId + "%') ");

			Query sqlQuery = hibSession.createQuery(query.toString());

			logger.info("==> getEmployeeIdsFromSevarthId query :: " + sqlQuery);

			list = sqlQuery.list();
			logger.info("==> getEmployeeIdsFromSevarthId query list size is ************" + list.size());

		}

		return list;
	}

	public List getEmployeeIdListFromEployeeName(String empLocalFName, String empLocalMName, String empLocalLName) {
		logger.info("in getEmployeeIdFromEployeeName...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append(" SELECT ee.empId,ee.orgUserMst.userId ");
		query.append(" FROM OrgEmpMst ee ");
		query.append(" where lower(ee.empFname) like lower('" + empLocalFName + "%') ");

		if (empLocalLName != null && !empLocalLName.equals("") && !empLocalLName.equals("-1")) {
			query.append(" and lower(ee.empLname) like lower('" + empLocalLName + "%') ");
		}

		if (empLocalMName != null && !empLocalMName.equals("") && !empLocalMName.equals("-1")) {
			query.append(" and lower(ee.empMname) like lower('" + empLocalMName + "%') ");
		}

		Query sqlQuery = hibSession.createQuery(query.toString());
		list = sqlQuery.list();
		logger.info("==> getEmployeeIdFromEployeeName query :: " + sqlQuery);

		return list;
	}

	public List getEmployeeIdAndUserIDFromGPFAcctNo(String GPFAcctNo, String PfSeries) {
		logger.info("in getEmployeeIdAndUserIDFromGPFAcctNo...........");
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append(" SELECT ee.empId,ee.orgUserMst.userId ");
		query.append(" FROM OrgEmpMst ee,HrPayGpfBalanceDtls gpf ");
		query.append(" where ee.orgUserMst.userId=gpf.userId  ");

		if (!GPFAcctNo.equals("") && !GPFAcctNo.equals("-1") && GPFAcctNo != null) {
			query.append(" and gpf.gpfAccNo like '%" + GPFAcctNo + "%' ");
		}

		if (!PfSeries.equals("") && !PfSeries.equals("-1") && PfSeries != null) {
			query.append(" and lower(gpf.pfSeries) like lower('%" + PfSeries + "%') ");
		}

		Query sqlQuery = hibSession.createQuery(query.toString());
		list = sqlQuery.list();
		logger.info("==> getEmployeeIdAndUserIDFromGPFAcctNo with two parameters query :: " + sqlQuery);
		logger.info(
				"==> getEmployeeIdAndUserIDFromGPFAcctNo with two parameters list size isssssssssss :: " + list.size());

		return list;
	}

	// end

	public List getEmployeeIdListFromDsgnId(long DESIGNATIONId) {
		logger.info("in getEmployeeIdFromDsgnId...........");
		List list = new ArrayList();

		// String employeeid = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  distinct e.empId,e.orgUserMst.userId ");
		query.append(" from OrgEmpMst e,OrgUserpostRlt up, OrgPostDetailsRlt det ");
		query.append(
				" where e.orgUserMst.userId=up.orgUserMst.userId and up.orgPostMstByPostId.postId=det.orgPostMst.postId and det.orgDesignationMst.dsgnId="
						+ DESIGNATIONId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getEmployeeIdFromDsgnId query :: " + sqlQuery);
		list = sqlQuery.list();

		return list;
	}

	public List getGPFAcctNoPfSeriesFromEployeeId(String EmployeeId) {
		logger.info("in getGPFAcctNoEployeeId...........");
		List list = new ArrayList();

		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  gpf.gpfAccNo,gpf.pfSeries ");
		query.append(" from OrgEmpMst emp,HrPayGpfBalanceDtls gpf");
		query.append(" where emp.orgUserMst.userId=gpf.userId and emp.empId=" + EmployeeId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getGPFAcctNoEployeeId query :: " + sqlQuery);

		list = sqlQuery.list();
		logger.info("==> getGPFAcctNoEployeeId query list:: " + list.size());

		return list;
	}

	public List getGPFAcctNoPfSeriesFromEployeeId(long EmployeeId) {
		logger.info("in getGPFAcctNoEployeeId...........");
		List list = new ArrayList();

		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(" select  gpf.gpfAccNo,gpf.pfSeries ");
		query.append(" from OrgEmpMst emp,HrPayGpfBalanceDtls gpf");
		query.append(" where emp.orgUserMst.userId=gpf.userId and emp.empId=" + EmployeeId + " ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getGPFAcctNoEployeeId query :: " + sqlQuery);

		list = sqlQuery.list();
		logger.info("==> getGPFAcctNoEployeeId query list:: " + list.size());
		return list;
	}

	// Ended by Abhilash ammaiah

	// Added for showing header in gis report
	public String getGisName(String gis) {
		logger.info("in getGisName...........");
		List list = new ArrayList();

		String gisName = "";
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();

		query.append(
				" select lookup.lookupName from  CmnLookupMst lookup where lookup.parentLookupId=2500370 and lookup.lookupShortName='"
						+ gis + "' ");

		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("==> getGisName query :: " + sqlQuery);

		list = sqlQuery.list();
		logger.info("==> getGisName query list:: " + list.size());
		if (list.size() > 0) {
			if (list.get(0) != null) {
				gisName = list.get(0).toString();
			} else {
				gisName = "";
			}
		} else {
			gisName = "";
		}

		return gisName;
	}

	public long getAccountHeadCode(long TypeId) {
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select edpmpg from HrPayEdpCompoMpg edpmpg where edpmpg.typeId = " + TypeId
				+ " and edpmpg.cmnLookupId = 2500135 ");
		Query sqlQuery = hibSession.createQuery(query.toString());
		HrPayEdpCompoMpg hrPayEdpCompoMpg = (HrPayEdpCompoMpg) sqlQuery.uniqueResult();
		return hrPayEdpCompoMpg.getHeadOfAcc();
	}

	// Ended

	// Added By Kishan
	public Map<Long, Map<String, Object>> getPayslipData(long billNo, int month, int year, Map coloumnMap) // finsd by
																											// teju/
																											// pandey
	{
		List<Map<String, Object>> resList = null;
		/*
		 * String col =
		 * "paySlip.TECH,paySlip.DA_ARR,paySlip.ADD_HRA,paySlip.ARMOUER,paySlip.ADD_DA,paySlip.BMI,paySlip.ARM,paySlip.CASH,"
		 * +
		 * "paySlip.LTA,paySlip.MED_STU,paySlip.FRA,paySlip.TEMP_HRA,paySlip.CONVEYANCE,paySlip.TEMP_CLA,"
		 * +
		 * "paySlip.CID,paySlip.Overtime_Allow,paySlip.CDA,paySlip.FORCE25,paySlip.DA_7PC,paySlip.ATS50,paySlip.CTA,"
		 * +
		 * "paySlip.FORCE100,paySlip.DA_ON_TA,paySlip.CPF,paySlip.BASIC_ARR,paySlip.FLYING_PAY_PILOT,paySlip.KIT_PILOT,"
		 * +
		 * "paySlip.CHPL_PILOT,paySlip.RT_PILOT,paySlip.PG,paySlip.UA,paySlip.AP,paySlip.SDA,paySlip.PA,paySlip.SUMA,paySlip.NPA,"
		 * +
		 * "paySlip.TAA,paySlip.ATS30,paySlip.FPA,paySlip.HA,paySlip.MA,paySlip.QUALIFICATION_PILOT,paySlip.INSPECTION_PILOT,"
		 * +
		 * "paySlip.DA,paySlip.INSTRUCTIONAL_PILOT,paySlip.OA,paySlip.PER_PAY,paySlip.MILITERY_PILOT,paySlip.SPL_PAY,"
		 * +
		 * "paySlip.SPECIAL_PAY_PILOT,paySlip.FLYING_ALLOW_PILOT,paySlip.OUTFIT_PILOT,paySlip.PER_TRA,paySlip.OTHER_ALLS,"
		 * +
		 * "paySlip.WA,paySlip.TEMP_TA,paySlip.WRI_PAY,paySlip.KMA,paySlip.GALLNTRY,paySlip.MECH,paySlip.LFA,paySlip.ESIS,"
		 * +
		 * "paySlip.EA,paySlip.FITNESS,paySlip.ELA,paySlip.MEA,paySlip.MESH,paySlip.NAA,paySlip.DP,paySlip.TRANS_ARREAR,"
		 * +
		 * "paySlip.TASIXTH,paySlip.REFRESHMENT_ALLOW,paySlip.HRA,paySlip.PEON_ALLOWANCE,paySlip.CLA,paySlip.TA,"
		 * +
		 * "paySlip.INCENTIVE_BDDS,paySlip.SVNPC_TA, paySlip.P_TAX,paySlip.I_TAX,paySlip.FESTIVAL_ADV,paySlip.DCPS,"
		 * +
		 * "paySlip.DCPS_DELAY,paySlip.DCPS_PAY,payslip.DCPS_DA,paySlip.BMI,paySlip.GPFGRPABC,paySlip.HBA_LAND_INT";
		 * // added by lekhchand paySlip.SVNPC_TA
		 */

		String col = "";
		Iterator it = coloumnMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			col = col + "paySlip." + pairs.getValue() + ",";
		}
		if (!col.equals(""))
			col = col.substring(0, col.lastIndexOf(','));

		Session hibSession = getSession();

		logger.info("teju /  pandey");

		// logger.info("
		// paySlip.TECH,paySlip.DA_ARR,paySlip.ADD_HRA,paySlip.ARMOUER,paySlip.ADD_DA,paySlip.BMI,paySlip.ARM,paySlip.CASH,paySlip.LTA,paySlip.MED_STU,paySlip.FRA,paySlip.TEMP_HRA,paySlip.CONVEYANCE,paySlip.TEMP_CLA,paySlip.CID,paySlip.Overtime_Allow,paySlip.CDA,paySlip.FORCE25,paySlip.DA_7PC,paySlip.ATS50,paySlip.CTA,paySlip.FORCE100,paySlip.DA_ON_TA,paySlip.CPF,paySlip.BASIC_ARR,paySlip.FLYING_PAY_PILOT,paySlip.KIT_PILOT,paySlip.CHPL_PILOT,paySlip.RT_PILOT,paySlip.PG,paySlip.UA,paySlip.AP,paySlip.SDA,paySlip.PA,paySlip.SUMA,paySlip.NPA,paySlip.TAA,paySlip.ATS30,paySlip.FPA,paySlip.HA,paySlip.MA,paySlip.QUALIFICATION_PILOT,paySlip.INSPECTION_PILOT,paySlip.DA,paySlip.INSTRUCTIONAL_PILOT,paySlip.OA,paySlip.PER_PAY,paySlip.MILITERY_PILOT,paySlip.SPL_PAY,paySlip.SPECIAL_PAY_PILOT,paySlip.FLYING_ALLOW_PILOT,paySlip.OUTFIT_PILOT,paySlip.PER_TRA,paySlip.OTHER_ALLS,paySlip.WA,paySlip.TEMP_TA,paySlip.WRI_PAY,paySlip.KMA,paySlip.GALLNTRY,paySlip.MECH,paySlip.LFA,paySlip.ESIS,paySlip.EA,paySlip.FITNESS,paySlip.ELA,paySlip.MEA,paySlip.MESH,paySlip.NAA,paySlip.DP,paySlip.TRANS_ARREAR,paySlip.TASIXTH,paySlip.REFRESHMENT_ALLOW,paySlip.HRA,paySlip.PEON_ALLOWANCE,paySlip.CLA,paySlip.TA,paySlip.INCENTIVE_BDDS
		// "+col);

		String strQuery = " select paySlip.EMP_ID," + col
				+ " from   Hr_Pay_Payslip paySlip where paySlip.PAYSLIP_MONTH=" + month + " and paySlip.PAYSLIP_YEAR="
				+ year + " and paySlip.BILL_NO=" + billNo + " ";
		Query query = hibSession.createSQLQuery(strQuery);
		query.setResultTransformer(AliasToEntityMapResult.INSTANCE);
		resList = query.list();
		long empID = 0;
		int size = resList.size();
		Map<Long, Map<String, Object>> resultMap = new HashMap<Long, Map<String, Object>>();
		for (int i = 0; i < size; i++) {
			Map<String, Object> dataMap = resList.get(i);
			empID = ((BigInteger) dataMap.get("EMP_ID")).longValue();
			resultMap.put(empID, dataMap);
		}
		return resultMap;
	}

	// Added By Kishan - start
	public Map<Long, Map<Long, Map<String, Object>>> getPayslipDataForLoan(String empId, long billNo, int month,
			int year, Map coloumnMap, String loanId) {
		List<Map<String, Object>> resList = null;
		Session hibSession = getSession();
		String col = "";
		Iterator it = coloumnMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			col = col + "paySlip." + pairs.getValue() + ",";
		}
		if (!col.equals(""))
			col = col.substring(0, col.lastIndexOf(','));

		StringBuffer sb = new StringBuffer();
		sb.append("select paySlip.EMP_ID," + col
				+ ", loan.RECOVERED_INST||'/'||loan.TOTAL_INST AS INSTALLMENT,loan.LOAN_TYPE_ID from ");
		sb.append("Hr_Pay_Payslip paySlip, HR_PAY_PAYBILL_LOAN_DTLS loan ");
		sb.append("where paySlip.PAYSLIP_MONTH = " + month + " ");
		sb.append("and paySlip.PAYSLIP_YEAR = " + year + " ");
		sb.append("and paySlip.BILL_NO = " + billNo + " ");
		sb.append("and paySlip.EMP_ID in (" + empId + ") ");
		sb.append("and loan.PAYBILL_ID = paySlip.PAYBILL_ID ");
		sb.append("and loan.LOAN_TYPE_ID in ( " + loanId + ") ");
		sb.append("order by paySlip.EMP_ID ");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.setResultTransformer(AliasToEntityMapResult.INSTANCE);
		resList = query.list();
		int size = resList.size();
		long empID = 0;
		Map<Long, Map<Long, Map<String, Object>>> resultMap = new HashMap<Long, Map<Long, Map<String, Object>>>();
		long currEmpId = 0;
		long previousEmpId = 0;
		for (int i = 0; i < size; i++) {
			Map<String, Object> dataMap = resList.get(i);
			empID = ((BigInteger) dataMap.get("EMP_ID")).longValue();
			currEmpId = empID;
			if (previousEmpId != currEmpId) {
				Map<Long, Map<String, Object>> amounMap = new HashMap<Long, Map<String, Object>>();
				amounMap.put(((BigInteger) dataMap.get("LOAN_TYPE_ID")).longValue(), dataMap);
				resultMap.put(empID, amounMap);
				previousEmpId = currEmpId;
			} else {
				Map<Long, Map<String, Object>> amounMap = (Map) resultMap.get(empID);
				amounMap.put(((BigInteger) dataMap.get("LOAN_TYPE_ID")).longValue(), dataMap);
				resultMap.put(empID, amounMap);
			}

		}
		return resultMap;
	}

	// Added By Kishan - start
	public Map<Long, Map<Long, Map<String, Object>>> getPayslipDataForLoan(long billNo, int month, int year,
			Map coloumnMap) {
		List<Map<String, Object>> resList = null;
		Session hibSession = getSession();
		String col = "";
		Iterator it = coloumnMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			col = col + "paySlip." + pairs.getValue() + ",";
		}
		if (!col.equals(""))
			col = col.substring(0, col.lastIndexOf(','));

		StringBuffer sb = new StringBuffer();
		// sb.append("select paySlip.EMP_ID," + col + ",loan.LOAN_TYPE_ID from ");
		sb.append("select paySlip.EMP_ID," + col
				+ ", loan.RECOVERED_INST||'/'||loan.TOTAL_INST AS INSTALLMENT,loan.LOAN_TYPE_ID from ");
		sb.append("Hr_Pay_Payslip paySlip, HR_PAY_PAYBILL_LOAN_DTLS loan ");
		sb.append("where paySlip.PAYSLIP_MONTH = " + month + " ");
		sb.append("and paySlip.PAYSLIP_YEAR = " + year + " ");
		sb.append("and paySlip.BILL_NO = " + billNo + " ");
		sb.append("and paySlip.EMP_ID = paySlip.EMP_ID ");
		sb.append("and loan.PAYBILL_ID = paySlip.PAYBILL_ID ");
		// sb.append("and loan.LOAN_TYPE_ID in ( " + loanId + ") ");
		sb.append("order by paySlip.EMP_ID ");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.setResultTransformer(AliasToEntityMapResult.INSTANCE);
		logger.info("The query for getpayslipdata for loan" + query.toString());
		resList = query.list();
		int size = resList.size();
		long empID = 0;
		Map<Long, Map<Long, Map<String, Object>>> resultMap = new HashMap<Long, Map<Long, Map<String, Object>>>();
		long currEmpId = 0;
		long previousEmpId = 0;
		for (int i = 0; i < size; i++) {
			Map<String, Object> dataMap = resList.get(i);
			empID = ((BigInteger) dataMap.get("EMP_ID")).longValue();
			currEmpId = empID;
			if (previousEmpId != currEmpId) {
				Map<Long, Map<String, Object>> amounMap = new HashMap<Long, Map<String, Object>>();
				amounMap.put(((BigInteger) dataMap.get("LOAN_TYPE_ID")).longValue(), dataMap);
				resultMap.put(empID, amounMap);
				previousEmpId = currEmpId;
			} else {
				Map<Long, Map<String, Object>> amounMap = (Map) resultMap.get(empID);
				amounMap.put(((BigInteger) dataMap.get("LOAN_TYPE_ID")).longValue(), dataMap);
				resultMap.put(empID, amounMap);
			}

		}
		return resultMap;
	}
	// Added By Kishan - end

	// added by Vivek
	public Integer calculateTotalBill(int month, int year, long billno, long locId) {

		Session hibSession = getSession();
		Integer iniCount = 0, finCount = 0;

		// this is the Check For Bill generated or not
		int finYear = year;
		if (month < 4)
			finYear = year - 1;
		StringBuffer strQuery = new StringBuffer(
				"select count from PAYBILL_HEAD_MPG hm where hm.PAYBILL_MONTH>3 and hm.PAYBILL_YEAR= " + finYear
						+ " and hm.APPROVE_FLAG in (0,1) and hm.LOC_ID= " + locId + " and hm.BILL_NO= " + billno
						+ " 	");
		logger.info(strQuery);
		// strQuery.append(" ))");
		logger.info("Query in calculateTotalBill is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		iniCount = (Integer) query.uniqueResult();
		logger.info("iniCount in  calculateTotalBill is " + iniCount);
		if (month < 4) {
			StringBuffer strQuery2 = new StringBuffer(
					"select count from PAYBILL_HEAD_MPG hm where hm.PAYBILL_MONTH<4 and hm.PAYBILL_YEAR> " + year
							+ " and hm.APPROVE_FLAG in (0,1) and hm.LOC_ID= " + locId + " and hm.BILL_NO= " + billno
							+ " 	");
			logger.info(strQuery2);
			// strQuery.append(" ))");
			logger.info("Query in calculateTotalBill in if is " + strQuery2.toString());
			Query query2 = hibSession.createSQLQuery(strQuery2.toString());
			finCount = (Integer) query2.uniqueResult();
		}
		logger.info("count in  calculateTotalBill is " + finCount + iniCount);
		return finCount + iniCount;
	}

	public int[] getBillCreationDate(long billno, long locId) {
		Date createdDate = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"select CREATED_DATE from MST_DCPS_BILL_GROUP where BILL_GROUP_ID=" + billno + " ");
		logger.info(strQuery);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		logger.info("Query in getBillCreationDate is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		createdDate = (Date) query.uniqueResult();
		int year = Integer.parseInt(simpleDateformat.format(createdDate));
		int month = Integer.parseInt(sdf.format(createdDate));
		int tempMonth = month;
		int prevYear = year;

		logger.info("reaultset in  getBillCreationDate is " + createdDate);
		// StringBuffer strQuery2 = new StringBuffer("select count from PAYBILL_HEAD_MPG
		// hm where hm.PAYBILL_MONTH>3 and hm.PAYBILL_YEAR= 2012 and hm.APPROVE_FLAG in
		// (0,1) and hm.LOC_ID= " + locId + " and hm.BILL_NO= " + billno + " and
		// hm.PAYBILL_MONTH<"+month);
		if (month < 4)
			prevYear = year - 1;

		StringBuffer strQuery2 = new StringBuffer(
				"select min(PAYBILL_MONTH) from PAYBILL_HEAD_MPG where PAYBILL_year in (" + prevYear + "," + year
						+ ") AND BILL_NO=" + billno);
		logger.info(strQuery2);
		Query query2 = hibSession.createSQLQuery(strQuery2.toString());
		Object rs = query2.uniqueResult();
		java.math.BigInteger count = (rs != null) ? (java.math.BigInteger) rs : java.math.BigInteger.ZERO;
		String temp = count.toString(10);
		int val = Integer.parseInt(temp);
		int[] data = { month, year, val };
		return data;

	}

	// added by vivek ends

	public List getBillNo1(long finYrId, long locId, String ddoCode) // used for report of payslip
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		// query.append("select dcpsDdoBillGroupId,dcpsDdoBillDescription from
		// MstDcpsBillGroup where finYearId=" + finYrId);
		query.append("select dcpsDdoBillGroupId,dcpsDdoBillDescription    from MstDcpsBillGroup as billgrp ");
		if (locId != parentlocationId)
			query.append(" where  billgrp.LocId=" + locId
					+ " and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') order by dcpsDdoBillGroupId");

		logger.info("Query for get bill no is---->>>>" + query.toString());

		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();
		logger.info("the size of resList is ::" + resList.size());
		return resList;
	}

	// added by vaibhav tyagi: start

	public void insertPaybillEmpCompoMpg(PaybillEmpCompMpg paybillEmpCompMpg) {
		Session hibSession = getSession();

		Long paybillId = paybillEmpCompMpg.getPaybillID();
		Long paybillHeadMpg = paybillEmpCompMpg.getPaybillHeadMpg();
		Long empCompoId = paybillEmpCompMpg.getPaybillEmpCompId();
		Long compoType = paybillEmpCompMpg.getCompoType();
		String compoId = paybillEmpCompMpg.getCompoId();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into HR_PAY_PAYBILL_EMP_COMP_MPG values(" + empCompoId + "," + paybillHeadMpg + ","
				+ paybillId + "," + compoType + ",'" + compoId + "')");
		Query query = hibSession.createSQLQuery(sb.toString());
		logger.info("HR_PAY_PAYBILL_EMP_COMP_MPG query is: " + sb.toString());
		query.executeUpdate();

	}

	// added by roshan

	public String getPercenatgeOfBasicType(Long EmpId) {
		logger.info("in paybillDaoImpl To get the basic type..........." + EmpId);
		String percenatgeOfBasicType = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" select PERCENTAGE_OF_BASIC from org_emp_mst where emp_id=" + EmpId + " ");
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		logger.info("==>  :percenatgeOfBasicType query: " + query.toString());
		percenatgeOfBasicType = sqlQuery.uniqueResult().toString();
		return percenatgeOfBasicType;
	}
	// end by roshan

	// added by vaibhjav tyagi : start
	public int updatePostMst(Long empPostId, String empEndDate) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String empEnd = null;
		try {
			empEnd = sdf2.format(sdf1.parse(empEndDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("in paybillDaoImpl To get the basic type..........." + empPostId);
		int status = 0;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("update org_post_mst set POST_TYPE_LOOKUP_ID=null,END_DATE='" + empEnd + "' where post_id="
				+ empPostId + " ");
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		status = sqlQuery.executeUpdate();
		logger.info("==>  updatePostMst query: " + query.toString());
		return status;
	}

	public List getTotalPostNew1(long dsgnId, long billNo, long locId, boolean postFlag, String group, String givenDate,
			long month, long year) {
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" select  pd.POST_ID from  ORG_POST_DETAILS_RLT pd ,HR_PAY_PAYBILL pay, PAYBILL_HEAD_MPG hd ");
		query.append(",RLT_DCPS_BILLGROUP_CLASSGROUP rltGrp,ORG_GRADE_MST grade,HR_EIS_GD_MPG gd, orG_post_mst post ");
		query.append(" where pd.DSGN_ID=" + dsgnId);
		query.append(" and hd.APPROVE_FLAG in (0,1) and hd.PAYBILL_MONTH=" + month + " and hd.PAYBILL_YEAR=" + year
				+ " and hd.bill_No=" + billNo);
		query.append(" and pd.post_Id=pay.post_Id and hd.PAYBILL_ID= pay.PAYBILL_GRP_ID and pay.loc_id=" + locId);
		query.append(" and gd.loc_Id=" + locId + " and gd.SGD_DESIG_ID=pd.dsgn_Id");
		query.append(" and gd.SGD_GRADE_ID=grade.grade_Id and rltGrp.dcps_Class_Group=grade.grade_Name");
		query.append(" and rltGrp.dcps_BillGroup_Id=" + billNo);
		query.append(" and pd.post_id=post.post_id");
		if (postFlag == false)
			// for statutory and permanant posts
			// query.append(" and post.POST_TYPE_LOOKUP_ID=" + 10001198129L + " and
			// post.end_Date is null ");
			query.append(" and post.POST_TYPE_LOOKUP_ID in ( 10001198129, 10001198155 ) and  post.end_Date is null ");
		else

			query.append(" and post.POST_TYPE_LOOKUP_ID=" + 10001198130L + " and (post.end_Date >= '" + givenDate
					+ "'or  post.end_Date is null)");

		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		List list = sqlQuery.list();

		logger.info("check this query for OrgPostDetailsRlt getVacantPost new " + query.toString());
		logger.info("size is OrgPostDetailsRlt getVacantPost new" + list.size());
		return list;
	}
	// added by vaibhav tyagi : end

	// added by Roshan
	public long getLocationCode(long billNo) {
		logger.info("hii i m in finding location code for bill number");
		List list = null;
		long locationCode = 0;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select loc_id from mst_Dcps_bill_group where bill_group_id=" + billNo + "");
		logger.info("Query for get getBillNoData is---->>>>" + sb.toString());
		Query sqlQuery = session.createSQLQuery(sb.toString());
		list = sqlQuery.list();

		if (list != null && list.size() > 0) {
			locationCode = Long.parseLong(list.get(0).toString());

		}
		logger.info("getBillNoData is-by roshan************************--->>>>" + locationCode);
		return locationCode;

	}

	public String getddoCodeForBillId(String billNo) {
		logger.info("hii i m in finding ddocode for bill number");
		List list = null;
		String ddoCode = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select ddo_code from mst_Dcps_bill_group where bill_group_id=" + billNo + "");
		logger.info("Query for get getBillNoData is---->>>>" + sb.toString());
		Query sqlQuery = session.createSQLQuery(sb.toString());
		list = sqlQuery.list();

		if (list != null && list.size() > 0) {
			ddoCode = list.get(0).toString();

		}
		logger.info("getBillNoData is-by roshan************************--->>>>" + ddoCode);
		return ddoCode;

	}

	public Long checkEmpCount(String billNo) {
		logger.info("hii i m in finding checkEmpCount");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from mst_dcps_emp where BILLGROUP_ID=" + billNo + "");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is *************" + query.toString());
		String count = query.uniqueResult().toString();
		Long totalEmp = Long.valueOf(Long.parseLong(count));
		logger.info("totalEmp" + totalEmp);
		return totalEmp.longValue();

	}

	public String getalreadybillsforemployee(long billNo, long billmonth, long billyear) {
		logger.info("in the DAO of getalreadybill");
		Session hiSession = getSession();
		StringBuffer SBQuery = new StringBuffer();
		SBQuery.append(
				" SELECT count(1) FROM HR_PAY_PAYBILL hr inner join PAYBILL_HEAD_MPG pay on hr.PAYBILL_GRP_ID = pay.PAYBILL_ID");
		SBQuery.append(
				" where hr.EMP_ID in (select he.emp_id from HR_EIS_EMP_MST he inner join mst_dcps_emp mde on mde.org_emp_mst_id=he.emp_mpg_id where BILLGROUP_ID= :BillgrpID)");
		SBQuery.append(
				" and pay.PAYBILL_MONTH = :Billmonth and pay.PAYBILL_YEAR = :Billyear and pay.APPROVE_FLAG in(0,1,2)");
		logger.info("Query is " + SBQuery);
		Query query = hiSession.createSQLQuery(SBQuery.toString());
		query.setParameter("BillgrpID", billNo);
		query.setParameter("Billmonth", billmonth);
		query.setParameter("Billyear", billyear);
		logger.info("The final query is" + query.toString());
		return (query.uniqueResult().toString());
	}

	// added by abhishek for the calculation of TA rates
	public long getOldBasic(long empId) {
		logger.info("hii i m in finding getOldBasic");
		List list = null;
		long oldBasic = 0;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"select OTHER_CURRENT_BASIC_WITHOUT_DEDUCTION from hr_eis_other_dtls where emp_id =(select emp_id from hr_eis_emp_mst where EMP_ID="
						+ empId + ")");

		logger.info("Query for get oldBasic is---->>>>" + sb.toString());
		Query sqlQuery = session.createSQLQuery(sb.toString());
		list = sqlQuery.list();

		if (list != null && list.size() > 0) {
			oldBasic = Long.parseLong(list.get(0).toString());

		}
		logger.info("oldBasic is-by roshan************************--->>>>" + oldBasic);
		return oldBasic;

	}

	public long getNewBasic(long empId) {
		logger.info("hii i m in finding getNewBasic");
		List list = null;
		long newBasic = 0;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"select OTHER_CURRENT_BASIC from hr_eis_other_dtls where emp_id =(select emp_id from hr_eis_emp_mst where EMP_ID="
						+ empId + ")");

		logger.info("Query for get getNewBasic is---->>>>" + sb.toString());
		Query sqlQuery = session.createSQLQuery(sb.toString());
		list = sqlQuery.list();

		if (list != null && list.size() > 0) {
			newBasic = Long.parseLong(list.get(0).toString());

		}
		logger.info("getNewBasic is-by roshan************************--->>>>" + newBasic);
		return newBasic;

	}

	public long getPerOFBAsic(long empId) {
		logger.info("hii i m in finding getNewBasic");
		List list = null;
		long newBasic = 0;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"select PERCENTAGE_OF_BASIC from ORG_EMP_MST where emp_id =(select emp_MPG_id from hr_eis_emp_mst where EMP_ID="
						+ empId + ")");

		logger.info("Query for get getNewBasic is---->>>>" + sb.toString());
		Query sqlQuery = session.createSQLQuery(sb.toString());
		list = sqlQuery.list();

		if (list != null && list.size() > 0) {
			newBasic = Long.parseLong(list.get(0).toString());

		}
		logger.info("GETpERBASIC-by roshan************************--->>>>" + newBasic);
		return newBasic;

	}

	public List getLeaveDtls(long empId, long year) {
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();

		query.append(
				" SELECT leave.ddo_code,leave.LEAVE_FROM_DATE,leave.LEAVE_TO_DATE,dcps.designation FROM HR_PAY_DDO_LEAVE_SANCTION_DTLS leave  ");
		query.append(
				" inner join mst_dcps_emp dcps on dcps.ddo_code=leave.DDO_CODE inner join hr_eis_emp_mst eis on eis.SEVARTH_EMP_CD=dcps.SEVARTH_ID ");
		query.append(" where eis.EMP_ID= " + empId + " and leave.year=" + year + " ");
		logger.info("Query for get bill no is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		// sqlQuery.setParameter(empId, empId);
		resList = sqlQuery.list();
		logger.info("the size of resList is ::" + resList.size());
		return resList;
	}

	// added end by abhishek
	public Map getBillData1(long locId, long billNo, long month, long year, long parentLocId) {
		logger.info("locId " + locId + " billNo " + billNo + " month " + month + " year " + year + " parentLocID "
				+ parentLocId);
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		Map billData = new HashMap();
		Map empInnerMap = new HashMap();
		Map vacantPostInnerMap = new HashMap();
		Map desigMap = new HashMap();
		Object obj[] = null;
		Connection con = null;
		java.sql.PreparedStatement preStat = null;
		java.sql.ResultSet rs = null;

		try {
			HrPayPaybill hrPayPayBillOBj = null;
			con = hibSession.connection();
			logger.info("Before entering into the procedure");
			preStat = con.prepareStatement("{call ifms.getChangeStatementData(?,?,?,?,?)}");
			logger.info("after the execution of procedure into the procedure");
			preStat.setLong(1, billNo); // /billNo
			preStat.setLong(2, month); // /paybill month
			preStat.setLong(3, year); // /paybill year
			preStat.setLong(4, locId); // /locaID
			preStat.setLong(5, parentLocId); // /parentLocID
			logger.info("after the setting element of procedure into the procedure");
			preStat.executeUpdate();
			logger.info("after the execution of procedure");
			rs = (java.sql.ResultSet) preStat.getResultSet();// getObject(2);
			if (rs != null) {
				while (rs.next()) {
					hrPayPayBillOBj = new HrPayPaybill();
					hrPayPayBillOBj.setId(rs.getLong("ID")); // id
					hrPayPayBillOBj.setPsrNo(rs.getLong("PSR_NO")); // PSR_NO
					if (rs.getObject("EMP_ID") != null) { // EMP_ID
						HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
						hrEisEmpMst.setEmpId(rs.getLong("EMP_ID"));

						OrgEmpMst orgEmpMst = new OrgEmpMst();
						orgEmpMst.setEmpId(rs.getLong("orgEmpMst_EMP_ID"));
						orgEmpMst.setEmpFname(rs.getString("orgEmpMst_EMP_FNAME"));
						orgEmpMst.setEmpMname(rs.getString("orgEmpMst_EMP_MNAME"));
						orgEmpMst.setEmpLname(rs.getString("orgEmpMst_EMP_LNAME"));
						hrEisEmpMst.setOrgEmpMst(orgEmpMst);
						hrPayPayBillOBj.setHrEisEmpMst(hrEisEmpMst);
						hrEisEmpMst = null;
						orgEmpMst = null;
					} else
						hrPayPayBillOBj.setHrEisEmpMst(null);

					hrPayPayBillOBj.setMonth(rs.getDouble("PAYBILL_MONTH")); // PAYBILL_MONTH
					hrPayPayBillOBj.setYear(rs.getDouble("PAYBILL_YEAR")); // PAYBILL_YEAR
					hrPayPayBillOBj.setAllow0102(rs.getDouble("SPL_PAY")); // SPL_PAY
					hrPayPayBillOBj.setAllow0101(rs.getDouble("PER_PAY")); // PER_PAY
					hrPayPayBillOBj.setBasic0101(rs.getDouble("PO")); // PO
					hrPayPayBillOBj.setBasic0102(rs.getDouble("PE")); // PE
					hrPayPayBillOBj.setAllow0119(rs.getDouble("DP_GAZZETED")); // DP_GAZZETED
					hrPayPayBillOBj.setAllow0120(rs.getDouble("D_PAY")); // D_PAY
					hrPayPayBillOBj.setAllow0103(rs.getDouble("DA")); // DA
					/* Added By Shivram */
					hrPayPayBillOBj.setAllow9207(rs.getDouble("SVNPC_DA")); // DA_7PC
					/* Ended By Shivram */
					/* Added By lekhchand */
					hrPayPayBillOBj.setAllow9212(rs.getDouble("SVNPC_TA")); // SVNPC_TA TA
					/* Ended By lekhchand */
					hrPayPayBillOBj.setAllow0110(rs.getDouble("HRA")); // HRA
					hrPayPayBillOBj.setAllow0111(rs.getDouble("CLA")); // CLA
					hrPayPayBillOBj.setAllow0104(rs.getDouble("OTHER_ALLOW")); // OTHER_ALLOW
					hrPayPayBillOBj.setAllow0107(rs.getDouble("MA")); // MA
					hrPayPayBillOBj.setAllow0108(rs.getDouble("BONUS")); // BONUS
					hrPayPayBillOBj.setAllow1301(rs.getDouble("WA")); // WA
					hrPayPayBillOBj.setAllow0113(rs.getDouble("TRANS_ALL")); // TRANS_ALL
					hrPayPayBillOBj.setDeduc0101(rs.getDouble("PAY_RECOVER")); // PAY_RECOVER
					hrPayPayBillOBj.setGrossAmt(rs.getDouble("GROSS_AMT")); // GROSS_AMT
					hrPayPayBillOBj.setDeduc9510(rs.getDouble("IT")); // IT
					hrPayPayBillOBj.setSurcharge(rs.getDouble("SURCHARGE")); // SURCHARGE
					hrPayPayBillOBj.setDeduc9999(rs.getDouble("DA_GPF")); // DA_GPF
					hrPayPayBillOBj.setDeduc9998(rs.getDouble("DA_GPFIV")); // DA_GPFIV
					hrPayPayBillOBj.setDeduc9550(rs.getDouble("HRR")); // HRR
					hrPayPayBillOBj.setDeduc9560(rs.getDouble("RENT_B")); // RENT_B
					hrPayPayBillOBj.setDeduc9570(rs.getDouble("PT")); // PT
					hrPayPayBillOBj.setDeduc9583(rs.getDouble("GPF_GRP_D")); // GPF_GRP_D
					hrPayPayBillOBj.setAdv9670(rs.getDouble("GPF_ADV")); // GPF_ADV
					hrPayPayBillOBj.setLoan9591(rs.getDouble("HBA")); // HBA
					hrPayPayBillOBj.setLoan9720(rs.getDouble("FAN_ADV")); // FAN_ADV
					hrPayPayBillOBj.setDeduc9780(rs.getDouble("GPF_GRP_ABC")); // GPF_GRP_ABC
					hrPayPayBillOBj.setDeduc9910(rs.getDouble("MISC_RECOV")); // MISC_RECOV
					hrPayPayBillOBj.setDeduc9911(rs.getDouble("JanJulGIS")); // JanJulGIS
					hrPayPayBillOBj.setDeduc9531(rs.getDouble("GPF_IV")); // GPF_IV
					hrPayPayBillOBj.setTotalDed(rs.getDouble("TOTAL_DED")); // /TOTAL_DED
					hrPayPayBillOBj.setNetTotal(rs.getDouble("NET_TOTAL"));
					hrPayPayBillOBj.setGpay0101(rs.getDouble("GPAY")); // GPAY
					// added by seven pc employye arrear lekhchand all ded 03112022
					hrPayPayBillOBj.setAllow9213(rs.getDouble("SVNPC_GPF_ARR")); // GPF arrear
					hrPayPayBillOBj.setAllow9214(rs.getDouble("SVNPC_DCPS_ARR")); // DCPS arrear
					hrPayPayBillOBj.setAllow9215(rs.getDouble("SVNPC_TA_ARR")); // TA Arrear lekh
					hrPayPayBillOBj.setDeduc9216(rs.getDouble("SVNPC_GPF_ARR_DEDU")); // GFP Dedu arrear
					hrPayPayBillOBj.setDeduc9217(rs.getDouble("SVNPC_GPF_RECO")); // GPF Dedu recovey
					hrPayPayBillOBj.setDeduc9218(rs.getDouble("SVNPC_DCPS_RECO")); // DCPS Dedu arrear
					// Added by Kinjal for GAP
					hrPayPayBillOBj.setDeduc9134(rs.getLong("ACC_POLICY")); // ACC_POLICY

					/* Added By Naveen */
					hrPayPayBillOBj.setAllow9208(rs.getDouble("NPS_EMPLR")); // NPS_EMPLR Employer Contribution (NPS // 14%)
					System.out.println("ALOOW:NAVEEN"+rs.getDouble("NPS_EMPLR"));															
					hrPayPayBillOBj.setDeduc9142(rs.getDouble("NPS_EMPLR_CONTRI_DED")); // NPS_EMPLR Employer DEDCUTION
					System.out.println("ALOOW:NAVEEN DEDCUT::"+rs.getDouble("NPS_EMPLR_CONTRI_DED"));	// (NPS 14%)
					/* Ended By Naveen */
					com.tcs.sgv.common.valueobject.CmnDatabaseMst cmnDBmst = new CmnDatabaseMst(); // db_id
					cmnDBmst.setDbId(rs.getLong("db_id"));
					hrPayPayBillOBj.setCmnDatabaseMst(cmnDBmst);
					cmnDBmst = null;

					// created_by_post
					com.tcs.sgv.ess.valueobject.OrgPostMst orgPostMst = new com.tcs.sgv.ess.valueobject.OrgPostMst();
					orgPostMst.setPostId(rs.getLong("created_by_post"));
					hrPayPayBillOBj.setOrgPostMstByCreatedByPost(orgPostMst);
					orgPostMst = null;

					com.tcs.sgv.common.valueobject.CmnLocationMst cmnLocationMst = new CmnLocationMst(); // loc_id
					cmnLocationMst.setLocId(rs.getLong("loc_id"));
					hrPayPayBillOBj.setCmnLocationMst(cmnLocationMst);
					cmnLocationMst = null;

					OrgUserMst orgUserMst = new OrgUserMst(); // created_by
					orgUserMst.setUserId(rs.getLong("created_by"));
					hrPayPayBillOBj.setOrgUserMstByCreatedBy(orgUserMst);
					orgUserMst = null;

					hrPayPayBillOBj.setCreatedDate(rs.getDate("CREATED_DATE")); // CREATED_DATE
					hrPayPayBillOBj.setTrnCounter(rs.getInt("TRN_COUNTER")); // trnCounter
					hrPayPayBillOBj.setPaybillGrpId(rs.getLong("PAYBILL_GRP_ID")); // PAYBILL_GRP_ID
					hrPayPayBillOBj.setAdvIV9670(rs.getDouble("GPF_IV_ADV")); // GPF_IV_ADV

					orgPostMst = new com.tcs.sgv.ess.valueobject.OrgPostMst(); // post_id
					orgPostMst.setPostId(rs.getLong("post_id"));
					hrPayPayBillOBj.setOrgPostMst(orgPostMst);
					orgPostMst = null;

					hrPayPayBillOBj.setApproveRejectDate(rs.getDate("approve_reject_date")); // approveRejectDate
					hrPayPayBillOBj.setDeduc9534(rs.getLong("DCPS")); // DCPS
					hrPayPayBillOBj.setDeduc9535(rs.getLong("DCPS_Delay")); // DCPS_Delay
					hrPayPayBillOBj.setDeduc9536(rs.getLong("DCPS_Pay")); // DCPS_Pay
					hrPayPayBillOBj.setDeduc9537(rs.getLong("DCPS_DA")); // DCPS_DA
					hrPayPayBillOBj.setDeduc1000(rs.getLong("GIS_IPS")); // GIS_IPS
					hrPayPayBillOBj.setDeduc1001(rs.getLong("GIS_IAS")); // GIS_IAS
					hrPayPayBillOBj.setDeduc1002(rs.getLong("GIS_IFS")); // GIS_IFS
					hrPayPayBillOBj.setAllow1003(rs.getLong("TECH_ALLOW")); // TECH_ALLOW
					hrPayPayBillOBj.setDeduc1004(rs.getLong("CENTRAL_GIS")); // CENTRAL_GIS
					hrPayPayBillOBj.setDeduc1005(rs.getLong("GIS")); // GIS
					hrPayPayBillOBj.setDeduc9135(rs.getLong("REVENUE_STAMP")); // revenue satmp
					HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
					hrEisOtherDtls.setOtherId(rs.getLong("other_id"));
					hrPayPayBillOBj.setHrEisOtherDtls(hrEisOtherDtls);
					hrEisOtherDtls = null;

					hrPayPayBillOBj.setOtherTrnCntr(rs.getLong("other_trn_cntr")); // other_trn_cntr
					hrPayPayBillOBj.setAllow1012(rs.getLong("EMERGENCY_ALLOW")); // EMERGENCY_ALLOW
					hrPayPayBillOBj.setAllow1013(rs.getLong("ESIS")); // ESIS
					hrPayPayBillOBj.setAllow1014(rs.getLong("ELA")); // ELA
					hrPayPayBillOBj.setAllow1015(rs.getLong("FITNESS_ALLOW")); // FITNESS_ALLOW
					hrPayPayBillOBj.setAllow1016(rs.getLong("GALLANTRY_AWARDS")); // GALLANTRY_AWARDS
					hrPayPayBillOBj.setAllow1017(rs.getLong("KIT_MAINTENANCE")); // KIT_MAINTENANCE
					hrPayPayBillOBj.setAllow1018(rs.getLong("LISENCE_FEE")); // LISENCE_FEE
					hrPayPayBillOBj.setAllow1019(rs.getLong("MECHANICAL_ALLOW")); // MECHANICAL_ALLOW
					hrPayPayBillOBj.setAllow1020(rs.getLong("MEDICAL_EDUCATION_ALLOW")); // MEDICAL_EDUCATION_ALLOW
					hrPayPayBillOBj.setAllow1021(rs.getLong("MESS_ALLOW")); // MESS_ALLOW
					hrPayPayBillOBj.setAllow1022(rs.getLong("NAXEL_AREA_ALLOW")); // NAXEL_AREA_ALLOW
					hrPayPayBillOBj.setAllow1023(rs.getLong("NON_PRAC_ALLOW")); // NON_PRAC_ALLOW
					hrPayPayBillOBj.setAllow1024(rs.getLong("SUMPTUARY")); // SUMPTUARY
					hrPayPayBillOBj.setAllow1025(rs.getLong("PROJECT_ALLOW")); // PROJECT_ALLOW
					hrPayPayBillOBj.setAllow1026(rs.getLong("SDA"));// SDA
					hrPayPayBillOBj.setAllow1027(rs.getLong("ADD_PAY")); // ADD_PAY
					hrPayPayBillOBj.setAllow1028(rs.getLong("UNIFORM_ALLOW")); // UNIFORM_ALLOW
					hrPayPayBillOBj.setAllow1029(rs.getLong("PG_ALLOWANCE")); // PG_ALLOWANCE
					hrPayPayBillOBj.setAllow1030(rs.getLong("family_palnning"));// family_palnning
					hrPayPayBillOBj.setAllow1031(rs.getLong("HILLY_ALLOWANCE")); // HILLY_ALLOWANCE
					hrPayPayBillOBj.setAllow1032(rs.getLong("TAA")); // TAA
					hrPayPayBillOBj.setAllow1033(rs.getLong("ATS_INCENTIVE_30")); // ATS_INCENTIVE_30
					hrPayPayBillOBj.setAllow1034(rs.getLong("ATS_INCENTIVE_50")); // ATS_INCENTIVE_50
					hrPayPayBillOBj.setAllow1035(rs.getLong("FORCE_1_25")); // FORCE_1_25
					hrPayPayBillOBj.setAllow1036(rs.getLong("FORCE_1_100")); // FORCE_1_100
					hrPayPayBillOBj.setAllow1006(rs.getLong("ARM_ALLOWANCE")); // ARM_ALLOWANCE
					hrPayPayBillOBj.setAllow1007(rs.getLong("ARMOURER"));// ARMOURER
					hrPayPayBillOBj.setAllow1008(rs.getLong("BMI")); // BMI
					hrPayPayBillOBj.setAllow1009(rs.getLong("CASH_ALLOWANCE")); // CASH_ALLOWANCE
					hrPayPayBillOBj.setAllow1010(rs.getLong("CID")); // CID
					hrPayPayBillOBj.setAllow1011(rs.getLong("CONVEYANCE"));// CONVEYANCE
					hrPayPayBillOBj.setDeduc1037(rs.getLong("GPF_IAS_OTHER")); // GPF_IAS_OTHER
					hrPayPayBillOBj.setDeduc1038(rs.getLong("GPF_IAS")); // GPF_IAS
					hrPayPayBillOBj.setDeduc1039(rs.getLong("GPF_IPS")); // GPF_IPS
					hrPayPayBillOBj.setDeduc1040(rs.getLong("GPF_IFS")); // GPF_IFS
					hrPayPayBillOBj.setDeduc1041(rs.getLong("SERVICE_CHARGE")); // SERVICE_CHARGE
					hrPayPayBillOBj.setDeduc1042(rs.getLong("OTHER_DEDUCTION")); // OTHER_DEDUCTION
					hrPayPayBillOBj.setDeduc9709(rs.getLong("Maha_State_Life_insurance"));
					hrPayPayBillOBj.setLoan5051(rs.getLong("HBA_LAND")); // HBA_LAND
					hrPayPayBillOBj.setLoanInt5051(rs.getLong("HBA_LAND_INT")); // HBA_LAND_INT
					hrPayPayBillOBj.setAdv5052(rs.getDouble("PAY_ADVANCE")); // PAY_ADVANCE
					hrPayPayBillOBj.setAdv5053(rs.getDouble("TRAVEL_ADVANCE")); // TRAVEL_ADVANCE
					hrPayPayBillOBj.setAdv5054(rs.getDouble("GPF_ADV_GRP_ABC")); // GPF_ADV_GRP_ABC
					hrPayPayBillOBj.setAdv5055(rs.getDouble("GPF_ADV_GRP_D")); // GPF_ADV_GRP_D
					hrPayPayBillOBj.setLoan5056(rs.getLong("MCA_LAND")); // MCA_LAND
					hrPayPayBillOBj.setLoanInt5056(rs.getLong("MCA_LAND_INT")); // MCA_LAND_INT
					hrPayPayBillOBj.setLoan5057(rs.getLong("OTHER_VEH_ADV")); // OTHER_VEH_ADV
					hrPayPayBillOBj.setLoanInt5057(rs.getLong("OTHER_VEH_ADV_INT")); // OTHER_VEH_ADV_INT
					hrPayPayBillOBj.setLoan5058(rs.getLong("COMPUTER_ADV")); // COMPUTER_ADV
					hrPayPayBillOBj.setLoanInt5058(rs.getLong("COMPUTER_ADV_INT")); // COMPUTER_ADV_INT
					hrPayPayBillOBj.setAdv5059(rs.getDouble("FESTIVAL_ADVANCE")); // FESTIVAL_ADVANCE
					hrPayPayBillOBj.setLoan5060(rs.getLong("OTHER_ADV")); // OTHER_ADV
					hrPayPayBillOBj.setLoanInt5060(rs.getLong("OTHER_ADV_INT")); // OTHER_ADV_INT
					hrPayPayBillOBj.setLoan5061(rs.getLong("CO_HSG_SOC")); // CO_HSG_SOC
					hrPayPayBillOBj.setLoanInt5061(rs.getLong("CO_HSG_SOC_INT")); // CO_HSG_SOC_INT
					hrPayPayBillOBj.setAdv5062(rs.getDouble("GPF_OTHER_STATE")); // GPF_OTHER_STATE
					hrPayPayBillOBj.setAdv5063(rs.getLong("GPF_IAS_LOAN")); // GPF_IAS_LOAN
					hrPayPayBillOBj.setLoan5064(rs.getLong("HBA_AIS")); // HBA_AIS
					hrPayPayBillOBj.setLoanInt5064(rs.getLong("HBA_AIS_INT")); // HBA_AIS_INT
					hrPayPayBillOBj.setLoan5065(rs.getLong("MCA_AIS")); // MCA_AIS
					hrPayPayBillOBj.setLoanInt5065(rs.getLong("MCA_AIS_INT")); // MCA_AIS_INT
					hrPayPayBillOBj.setLoan5066(rs.getLong("COMP_AIS")); // COMP_AIS
					hrPayPayBillOBj.setLoanInt5066(rs.getLong("COMP_AIS_INT")); // COMP_AIS_INT
					hrPayPayBillOBj.setLoan5067(rs.getLong("HBA_HOUSE")); // HBA_HOUSE
					hrPayPayBillOBj.setLoanInt5067(rs.getLong("HBA_HOUSE_INT")); // HBA_HOUSE_INT
					hrPayPayBillOBj.setAdv5068(rs.getLong("EXC_PAYRC")); // EXC_PAYRC
					hrPayPayBillOBj.setDeduc9701(rs.getLong("GIS_ZP")); // GIS_ZP
					hrPayPayBillOBj.setDeduc9702(rs.getLong("GPF_ABC_Arr_Mr")); // GPF_ABC_Arr_Mr
					hrPayPayBillOBj.setDeduc9703(rs.getLong("GPF_D_Arr_Mr")); // GPF_D_Arr_Mr
					hrPayPayBillOBj.setDeduc9704(rs.getLong("GPF_IAS_Arr_Mr")); // GPF_IAS_Arr_Mr
					hrPayPayBillOBj.setDeduc9705(rs.getLong("GPF_IFS_Arr_Mr")); // GPF_IFS_Arr_Mr
					hrPayPayBillOBj.setDeduc9706(rs.getLong("GPF_IPS_Arr_Mr")); // GPF_IPS_Arr_Mr
					hrPayPayBillOBj.setDeduc9707(rs.getLong("HRR_Arr")); // HRR_Arr
					hrPayPayBillOBj.setDeduc9708(rs.getLong("JanJulGisArr")); // JanJulGisArr
					hrPayPayBillOBj.setDeduc9710(rs.getLong("Other_Rec"));// Other_Rec
					hrPayPayBillOBj.setDeduc9711(rs.getLong("PLI")); // PLI
					hrPayPayBillOBj.setDeduc9712(rs.getLong("PT_Arr")); // PT_Arr
					hrPayPayBillOBj.setDeduc9713(rs.getLong("Other_Ded_Try")); // Other_Ded_Try
					hrPayPayBillOBj.setAllow1147(rs.getLong("Add_DA")); // Add_DA
					hrPayPayBillOBj.setAllow1148(rs.getLong("Add_HRA")); // Add_HRA
					hrPayPayBillOBj.setAllow1149(rs.getLong("DA_Arr")); // DA_Arr
					hrPayPayBillOBj.setAllow1150(rs.getLong("Temp_CLA_5thPay")); // Temp_CLA_5thPay
					hrPayPayBillOBj.setAllow1151(rs.getLong("Franking_Allow")); // Franking_Allow
					hrPayPayBillOBj.setAllow1152(rs.getLong("Temp_HRA_5thPay")); // Temp_HRA_5thPay
					hrPayPayBillOBj.setAllow1153(rs.getLong("Leave_Travel_Assistance")); // Leave_Travel_Assistance
					hrPayPayBillOBj.setAllow1154(rs.getLong("Medical_Study_Allow")); // Medical_Study_Allow
					hrPayPayBillOBj.setAllow1155(rs.getLong("Other_Allowances")); // Other_Allowances
					hrPayPayBillOBj.setAllow1156(rs.getLong("Permanent_Travelling")); // Permanent_Travelling
					hrPayPayBillOBj.setAllow1157(rs.getLong("Temp_TA_5thPay")); // Temp_TA_5thPay
					hrPayPayBillOBj.setAllow1158(rs.getLong("Wash_Allow")); // Wash_Allow
					hrPayPayBillOBj.setAllow1161(rs.getLong("REFRESHMENT_ALLOW")); // REFRESHMENT_ALLOW
					hrPayPayBillOBj.setAllow1163(rs.getLong("PEON_ALLOWANCE")); // PEON_ALLOWANCE
					hrPayPayBillOBj.setAllow1159(rs.getLong("Writer_pay_allow"));// Writer_pay_allow
					hrPayPayBillOBj.setAllow1160(rs.getLong("Tribal_allow")); // Tribal_allow
					hrPayPayBillOBj.setAllow9912(rs.getLong("CTA")); // CTA
					hrPayPayBillOBj.setAllow9913(rs.getLong("CDA")); // CDA
					hrPayPayBillOBj.setAllow1165(rs.getLong("INCENTIVE_BDDS")); // INCENTIVE_BDDS
					hrPayPayBillOBj.setAllow1166(rs.getLong("RT_PILOT"));// RT_PILOT
					hrPayPayBillOBj.setAllow1167(rs.getLong("CHPL_PILOT")); // CHPL_PILOT
					hrPayPayBillOBj.setAllow1168(rs.getLong("KIT_PILOT")); // KIT_PILOT
					hrPayPayBillOBj.setAllow1169(rs.getLong("FLYING_PAY_PILOT")); // FLYING_PAY_PILOT
					hrPayPayBillOBj.setAllow1170(rs.getLong("INSTRUCTIONAL_PILOT")); // INSTRUCTIONAL_PILOT
					hrPayPayBillOBj.setAllow1171(rs.getLong("QUALIFICATION_PILOT")); // QUALIFICATION_PILOT
					hrPayPayBillOBj.setAllow1172(rs.getLong("INSPECTION_PILOT")); // INSPECTION_PILOT
					hrPayPayBillOBj.setAllow1173(rs.getLong("FLYING_ALLOW_PILOT")); // FLYING_ALLOW_PILOT
					hrPayPayBillOBj.setAllow1174(rs.getLong("OUTFIT_PILOT")); // OUTFIT_PILOT
					hrPayPayBillOBj.setAllow1175(rs.getLong("MILITERY_PILOT")); // MILITERY_PILOT
					hrPayPayBillOBj.setAllow1176(rs.getLong("SPECIAL_PAY_PILOT")); // SPECIAL_PAY_PILOT
					hrPayPayBillOBj.setAllow1177(rs.getLong("CPF")); // CPF
					hrPayPayBillOBj.setEmpLname(rs.getString("EMP_LNAME")); // EMP_LNAME
					hrPayPayBillOBj.setAllow1178(rs.getLong("BASIC_ARR")); // BASIC_ARR
					hrPayPayBillOBj.setAllow1179(rs.getLong("DA_ON_TA")); // DA_ON_TA
					hrPayPayBillOBj.setScaleId(rs.getLong("scale_id")); // scale_id
					// hrPayPayBillOBj.setBrokenFlag(rs.getString("FRM_BROKEN_PERIOD_OR_NOT"));
					// //FRM_BROKEN_PERIOD_OR_NOT
					// hrPayPayBillOBj.setDeduc9915(rs.getLong("PENSION"));
					// //PENSION
					// hrPayPayBillOBj.setDeduc9916(rs.getLong("CPF_DED"));
					// //CPF_DED
					// hrPayPayBillOBj.setDeduc9917(rs.getLong("Srv_chrg_Arrs"));
					// //Srv_chrg_Arrs

					if (hrPayPayBillOBj != null && hrPayPayBillOBj.getHrEisEmpMst() != null) {
						empInnerMap.put(hrPayPayBillOBj.getHrEisEmpMst().getEmpId(), hrPayPayBillOBj);
						logger.info("hrPayPayBillOBj.getHrEisEmpMst() " + hrPayPayBillOBj.getHrEisEmpMst().getEmpId());
						desigMap.put(hrPayPayBillOBj.getOrgPostMst().getPostId(), rs.getString("col_7_0_")); // col_7_0_
						// ==
						// orgdesigna11_.dsgn_name
					}

					if (hrPayPayBillOBj != null && hrPayPayBillOBj.getHrEisEmpMst() == null) {
						vacantPostInnerMap.put(hrPayPayBillOBj.getOrgPostMst(), hrPayPayBillOBj);
						desigMap.put(hrPayPayBillOBj.getOrgPostMst().getPostId(), rs.getString("col_7_0_"));
					}

					hrPayPayBillOBj = null;
					billData.put("netAmount", rs.getLong("col_10_0_")); // col_10_0_
					// --
					// net
					// amount
					billData.put("grossAmount", rs.getLong("col_11_0_"));
				}

				billData.put("HrEisEmpMst", empInnerMap);
				// billData.put("OrgPostMst", vacantPostInnerMap);

				billData.put("desigMap", desigMap);
				logger.info("billData.size() " + empInnerMap.size());

				empInnerMap = null;
				desigMap = null;
			}

			logger.info("billData.size() " + billData.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				preStat.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return billData;
	}

	public Object getBillName(String billNO) {
		Object voucherdtls = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select DESCRIPTION FROM MST_DCPS_BILL_GROUP where BILL_GROUP_ID=" + billNO);
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		logger.info("Query to be executed is " + sqlQuery.toString());

		voucherdtls = sqlQuery.uniqueResult();
		return voucherdtls;
	}

	public Long checkbankDetails(String billNo) {
		logger.info("hii i m in finding checkEmpCount");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"SELECT count(1) FROM org_ddo_mst where account_no is not null and length(ifs_code)=11 and ddo_code =(select ddo_code from MST_DCPS_BILL_GROUP where BILL_GROUP_ID="
						+ billNo + " ) ");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is *************" + query.toString());
		String count = query.uniqueResult().toString();
		Long totalEmp = Long.valueOf(Long.parseLong(count));
		logger.info("totalEmp" + totalEmp);
		return totalEmp.longValue();

	}

	public String getActiveAllowDeptforCS(long billNo, long locationId, long month, long year, String compoType) {
		long prevMonth, prevYear;
		if (month != 1) {
			prevMonth = month - 1;
			prevYear = year;
		} else {
			prevMonth = 12;
			prevYear = year - 1;
		}

		String compntList = "";
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" SELECT distinct COMPO_ID FROM HR_PAY_PAYBILL_EMP_COMP_MPG where PAYBILL_HEAD_MPG_ID in ( ");
		query.append(" SELECT PAYBILL_ID FROM PAYBILL_HEAD_MPG where BILL_NO = " + billNo);
		query.append(" and PAYBILL_MONTH in (" + month + "," + prevMonth + ")");
		query.append(" and PAYBILL_YEAR in( " + year + "," + prevYear + ")");
		query.append(" and APPROVE_FLAG in (0,1,2,4,5,6,7,8,9) and LOC_ID = " + locationId + ")");
		query.append(" and compo_type in( " + compoType + ")");
		logger.info("query.... " + query);
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		int i = 0;
		if (sqlQuery != null && sqlQuery.list().size() > 0) {
			for (; i < sqlQuery.list().size() - 1; i++) {
				if (sqlQuery.list().get(i).toString().endsWith(","))
					compntList = compntList + sqlQuery.list().get(i).toString();
				else
					compntList = compntList + sqlQuery.list().get(i).toString() + ",";
			}
			compntList = compntList + sqlQuery.list().get(i).toString();
		}
		logger.info("allowType " + compntList);
		return compntList;
	}
	// added or commented by poonam for vocation admin office

	// added by poonam for validation of paybill

	public String checkOtherPaybill(int month, int year, long billno, long locId) {

		List paybillData = new ArrayList();

		Session hibSession = getSession();
		String dtls = "";
		// this is the Check For Bill generated or not
		StringBuffer strQuery = new StringBuffer(
				"select Paybill_year ||'#'||paybill_month from PAYBILL_HEAD_MPG hm where  concat(hm.PAYBILL_YEAR,hm.PAYBILL_month) <> '"
						+ year + "" + month + "' "
						+ " and hm.APPROVE_FLAG not in (1,3)  and hm.paybill_year>2015  and hm.LOC_ID= " + locId
						+ " and hm.BILL_NO= " + billno + "  order by Paybill_year,paybill_month desc    ");
		logger.info(strQuery);
		// strQuery.append(" ))");
		logger.info("Query in checkPreviousPaybill is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());

		dtls = query.list().get(0).toString();

		return dtls;
	}

	// Added by Kinjal for Outer changes
	public List getGPFBifurcatedlist(long paybillGroupId, int year, int month) {
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();

		query.append(
				" SELECT emp.HEAD_ACT_CODE,sum(GPF_GRP_ABC +  GPF_ABC_ARR_MR + GPF_ADV_GRP_ABC),sum(GPF_GRP_D + GPF_D_ARR_MR + GPF_ADV_GRP_D) FROM HR_PAY_PAYBILL paybill  ");
		query.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID = paybill.PAYBILL_GRP_ID ");
		query.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID=paybill.EMP_ID ");
		query.append(" inner join Org_emp_mst emp on emp.EMP_ID=eis.EMP_MPG_ID  ");
		query.append(" where paybill.PAYBILL_GRP_ID = " + paybillGroupId + " and head.PAYBILL_YEAR =" + year
				+ " and head.PAYBILL_MONTH =" + month + " and HEAD_ACT_CODE <>0");
		query.append(" group by emp.HEAD_ACT_CODE");
		logger.info("Query for get bill no is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		// sqlQuery.setParameter(empId, empId);
		// sqlQuery.setParameter(year, year);
		resList = sqlQuery.list();
		logger.info("the size of resList is ::" + resList.size());
		return resList;
	}

	// added by saurabh for check on head of account code
	public Long checkHeadAccount(String billNo) {
		logger.info("hii i m in finding checkEmpCount");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from org_emp_mst oem inner join mst_dcps_emp emp ");

		sb.append("on oem.EMP_ID=emp.ORG_EMP_MST_ID inner join  MST_DCPS_BILL_GROUP bill ");
		sb.append(
				"on emp.DDO_CODE=bill.DDO_CODE where oem.HEAD_ACT_CODE is not null and bill.DDO_CODE=(select ddo_code from MST_DCPS_BILL_GROUP where BILL_GROUP_ID="
						+ billNo + " ) ");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is *************" + query.toString());
		String count = query.uniqueResult().toString();
		Long totalEmp = Long.valueOf(Long.parseLong(count));
		logger.info("totalEmp" + totalEmp);
		return totalEmp.longValue();

	}

	// added by Saurabh S

	public Long getEmpCount(long billNo, long month, long year) {
		// logger.info("hii i m in the getEmpCount billNo"+billNo);
		// logger.info("hii i m in the getEmpCount month"+month);
		// logger.info("hii i m in the getEmpCount year"+year);
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		Long totalEmp = 0l;
		try {
			// sb.append(" select distinct count(emp_id) from HR_PAY_PAYBILL where
			// PAYBILL_GRP_ID in (select paybill_id from paybill_head_mpg where BILL_NO="
			// +billNo+ " and PAYBILL_MONTH=" +month+ " and PAYBILL_YEAR="+year+" and
			// APPROVE_FLAG = 0) " );
			sb.append(
					" select  count(hp.emp_id) from HR_PAY_PAYBILL hp where hp.PAYBILL_GRP_ID in ( select phm.paybill_id from paybill_head_mpg phm where phm.BILL_NO = :billNo and phm.PAYBILL_MONTH = :month and phm.PAYBILL_YEAR = :year and phm.APPROVE_FLAG = 0 ) ");
			Query query = session.createSQLQuery(sb.toString());
			logger.info("query is *************" + query.toString());
			// session.connection().commit();
			query.setParameter("billNo", billNo);
			query.setParameter("month", month);
			query.setParameter("year", year);
			session.connection().commit();
			String count = query.uniqueResult().toString();
			logger.info("count is *************" + count);
			totalEmp = Long.valueOf(Long.parseLong(count));
			logger.info("totalEmp is *************" + totalEmp);
		} catch (Exception e) {
			logger.info("getEmpCount is **Exception***********" + e);
		}
		return totalEmp.longValue();

	}

	public List get7pclevel(String sevarthid) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		// sb.append("select SEVEN_PC_LEVEL from mst_dcps_emp where
		// SEVARTH_ID='"+sevarthid+"'");
		sb.append(
				"SELECT rltgrade.LEVEL FROM MST_DCPS_EMP mstdcpsemp inner join RLT_PAYBAND_GP_STATE_7PC rltgrade ON mstdcpsemp.SEVEN_PC_LEVEL =rltgrade.LEVEL_ID "
						+ " where  mstdcpsemp.SEVARTH_ID = '" + sevarthid
						+ "'  ORDER by  rltgrade.LEVEL FETCH FIRST 1 ROWS ONLY");
		Query sqlQuery = hibSession.createSQLQuery(sb.toString());

		logger.info("   inside5 the get7pclevel pandey query for 7pc lvel" + sqlQuery.list().get(0));

		List lst = sqlQuery.list();
		return lst;
	}
	// Ended by Tejashree

	public String getBillTypeOfbillGroup(String billNo) {
		logger.info("hii i m in finding getBillType");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from mst_dcps_bill_group where BILL_GROUP_ID=" + billNo);
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is *************" + query.toString());
		String billType = query.uniqueResult().toString();
		logger.info("billType" + billType);
		return billType;
	}

	public String findLocIdForDDO(String lStrDdocode) {
		logger.info("hii i m in finding getBillType");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT LOCATION_CODE FROM ORG_DDO_MST where DDO_CODE = '"+lStrDdocode+"'" );
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is findLocIdForDDO*************" + query.toString());
		String billType = query.uniqueResult().toString();
		logger.info("billType" + billType);
		return billType;
	}
	
}
