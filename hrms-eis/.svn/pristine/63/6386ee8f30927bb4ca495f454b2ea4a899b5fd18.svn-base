package com.tcs.sgv.eis.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;

@SuppressWarnings({"unchecked","deprecation"})
public class NonGovDeducDAOImpl extends GenericDaoHibernateImpl<HrPayNonGovDeduction, Long> implements NonGovDeducDAO
{
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");

	public NonGovDeducDAOImpl(Class<HrPayNonGovDeduction> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
	String sdate = sbConf.getSysdate();

	
	public List getNonGovDeducData()
	{
		List nonGovDeducList = new ArrayList();
		Session hibSession = getSession();
		Query query = hibSession.createQuery(" from HrPayNonGovDeduction as hrPayNonGovDeduction  order by hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empFname,hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empMname,hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empLname");
		logger.info("after query");
		nonGovDeducList = query.list();
		logger.info("Y r in non Governement Deduction dao" + nonGovDeducList.size());
		return nonGovDeducList;
	}

	public List getNonGovDeducDatabyEmployeeid(long empId)
	{
		List nonGovDeducList = new ArrayList();
		Session hibSession = getSession();
		Query query = hibSession.createQuery(" from HrPayNonGovDeduction as hrPayNonGovDeduction where hrPayNonGovDeduction.hrEisEmpMst.empId=" + empId);
		logger.info("after query");
		nonGovDeducList = query.list();
		logger.info("Y r in non Governement Deduction dao serch by employee id" + nonGovDeducList.size());
		return nonGovDeducList;
	}

	public List getNonGovDeducDatabyAccno(String accno)
	{
		List nonGovDeducList = new ArrayList();
		Session hibSession = getSession();
		Query query = hibSession.createQuery("  from HrPayNonGovDeduction as hrPayNonGovDeduction where hrPayNonGovDeduction.nonGovDeducAccNo='" + accno + "'");
		logger.info("after query");
		nonGovDeducList = query.list();
		logger.info("Y r in non Governement Deduction dao serch by employee id" + nonGovDeducList.size());
		return nonGovDeducList;
	}

	public List getNonGovDeducDatabyAccno(String accNo, long deducType, String locationCode)
	{
		List nonGovDeducList = new ArrayList();
		Session hibSession = getSession();
		Query query = hibSession.createQuery("  from HrPayNonGovDeduction as hrPayNonGovDeduction where hrPayNonGovDeduction.nonGovDeducAccNo='" + accNo + "' and hrPayNonGovDeduction.hrPayDeducTypeMst.deducCode=" + deducType + " and hrPayNonGovDeduction.cmnLocationMst.locationCode=" + locationCode + "  order by  hrPayNonGovDeduction.nonGovDeducId desc  ");
		logger.info("after query");
		nonGovDeducList = query.list();
		logger.info("Y r in non Governement Deduction dao serch by employee id" + nonGovDeducList.size());
		return nonGovDeducList;
	}

	public List getNonGovDeducDataPayslip(Date currDate, long empId)
	{ //function used in GeneratePayslip Service.
		List nonGovDeducList = new ArrayList();
		Session hibSession = getSession();
		String currDateMonYear = (currDate.getMonth() + 1) + "/" + (currDate.getYear() + 1900);
		Query query = hibSession.createQuery(" from HrPayNonGovDeduction as hrPayNonGovDeduction" + " where hrPayNonGovDeduction.hrEisEmpMst.empId=" + empId + " and to_date(to_char(hrPayNonGovDeduction.nonGovDeducEfftEndDt,'MM/YYYY'),'MM/YYYY') >= " + "to_date('" + currDateMonYear + "','MM/YYYY') and to_date(to_char(hrPayNonGovDeduction.nonGovDeducEfftStartDt,'MM/YYYY'),'MM/YYYY') <= " + "to_date('" + currDateMonYear + "','MM/YYYY')");

		logger.info("after query " + query.toString());
		nonGovDeducList = query.list();
		logger.info("Y r in non Governement Deduction dao" + nonGovDeducList.size());
		return nonGovDeducList;
	}

	public List getNonGovDeducDataFromPayslipId(long paySlipId)
	{ //function used in GeneratePayslip Service.
		List nonGovDeducList = new ArrayList();
		Session hibSession = getSession();
		Query query = hibSession.createQuery(" from HrPayPayslipNonGovt as hrPayPayslipNonGovt" + " where hrPayPayslipNonGovt.paybillID.id = " + paySlipId);

		logger.info("after query " + query.toString());
		nonGovDeducList = query.list();
		logger.info("Y r in non Governement Deduction dao" + nonGovDeducList.size());
		return nonGovDeducList;
	}

	public List getNonGovDeducDataPayslip(Date currDate)
	{ //function used in GeneratePayslip Service.
		List nonGovDeducList = new ArrayList();
		Session hibSession = getSession();
		String currDateMonYear = (currDate.getMonth() + 1) + "/" + (currDate.getYear() + 1900);
		Query query = hibSession.createQuery(" from HrPayNonGovDeduction as hrPayNonGovDeduction" + " where to_date('" + currDateMonYear + "','MM/YYYY') between " + "to_date(to_char(hrPayNonGovDeduction.nonGovDeducEfftStartDt,'MM/YYYY'),'MM/YYYY') and " + "to_date(to_char(hrPayNonGovDeduction.nonGovDeducEfftEndDt,'MM/YYYY'),'MM/YYYY')");

		logger.info("after non-government dao query " + query.toString());
		nonGovDeducList = query.list();
		logger.info("Y r in non Governement Deduction dao" + nonGovDeducList.size());
		return nonGovDeducList;
	}

	public List getNonGovDeducDataPayslipHistory(int empId, long deducType)
	{ //function used in GeneratePayslip Service for L.I.C History.
		List nonGovDeducList = new ArrayList();
		Session hibSession = getSession();
		String queryString = " from HrPayNonGovDeduction as hrPayNonGovDeduction" + " where hrPayNonGovDeduction.hrPayDeducTypeMst.deducCode=" + deducType + " and hrPayNonGovDeduction.hrEisEmpMst.empId=" + empId;
		Query query = hibSession.createQuery(queryString);
		logger.info("after query " + query.toString());
		nonGovDeducList = query.list();
		return nonGovDeducList;
	}

	public HrPayNonGovDeduction getHrPayNonGovDeduction(long nonGovDeducId)
	{
		HrPayNonGovDeduction hrPayNonGovDeduction = new HrPayNonGovDeduction();
		Session hibSession = getSession();
		Query query = hibSession.createQuery(" from HrPayNonGovDeduction as hrPayNonGovDeduction where hrPayNonGovDeduction.nonGovDeducId=" + nonGovDeducId);
		logger.info("after query");
		hrPayNonGovDeduction = (HrPayNonGovDeduction) query.uniqueResult();
		logger.info("Y r in non Governement Deduction dao" + hrPayNonGovDeduction);
		return hrPayNonGovDeduction;
	}

	public List getEmployees(long langId)
	{
		List lstEmpName = null;
		Session hibSession = getSession();
		String queryString = " from OrgEmpMst orgEmpMst where orgEmpMst.orgUserMst in (select orgempmst.orgUserMst from OrgEmpMst orgempmst where orgempmst.empId in (select hrEisEmpMst.empId from HrEisEmpMst hrEisEmpMst)) and orgEmpMst.cmnLanguageMst.langId =" + langId;
		Query query = hibSession.createQuery(queryString);
		lstEmpName = query.list();
		logger.info("The Size Emplist is:-" + lstEmpName.size());
		return lstEmpName;
	}

	public List getNonGovDeducData(List userList, long EmpId)
	{
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrPayNonGovDeduction.class);

		objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
		objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
		objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");
		if (userList.size() > 0)
			objCrt.add(Restrictions.in("orgEmpMst.orgUserMst", userList));
		if (EmpId != 0)
			objCrt.add(Restrictions.eq("hrEisEmpMst.empId", EmpId));
		objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
		
		objCrt.addOrder(Order.asc("orgEmpMst.empMname"));
		objCrt.addOrder(Order.asc("orgEmpMst.empLname"));

		return objCrt.list();
	}

	//getNonGovDeducData is modified by Hemant (307727)
	public List getNonGovDeducData(String locationCode, long EmpId, long langId, String AccNo, String StartDate, String EndDate)
	{
		List dataList = new ArrayList();
		Session hibSession = getSession();
		try
		{
			String query = " from  HrPayNonGovDeduction e where  ";
			if (!locationCode.equals(""))//location specific
			{
				query += " e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and ";
				query += "pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='" + locationCode + "' and c.cmnLanguageMst.langId=" + langId + ")) and e.hrEisEmpMst.orgEmpMst.empSrvcExp >= " + sdate;
			}
			if (AccNo != null && !AccNo.equals(""))
				query += " and e.nonGovDeducAccNo = '" + AccNo + "' ";
			if (EmpId != 0)
				query += " and e.hrEisEmpMst.orgEmpMst.empId  =" + EmpId;
			if (StartDate != null && !StartDate.equals("") || EndDate != null && !EndDate.equals(""))
			{
				DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
				String getStartDate = sbConf.to_char("e.nonGovDeducEfftStartDt");
				String getEndDate = sbConf.to_char("e.nonGovDeducEfftEndDt");
				if (StartDate != null && !StartDate.equals(""))
					query += " and " + getStartDate + " = '" + StartDate + "' ";
				if (EndDate != null && !EndDate.equals(""))
					query += " and " + getEndDate + " = '" + EndDate + "' ";
			}
			query += " order by e.hrPayDeducTypeMst.deducCode,e.nonGovDeducAccNo";//,e.hrEisEmpMst.orgEmpMst.empFname,e.hrEisEmpMst.orgEmpMst.empMname,e.hrEisEmpMst.orgEmpMst.empLname ";	
			Query sqlQuery = hibSession.createQuery(query);
			dataList = sqlQuery.list();
		}
		catch (Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE HANDLING THE START AND END DATE IN NonGovDeducDAOImpl.java : ", e);
			return dataList;
		}
		return dataList;
	}

	public List chkNonGovtForPayslip(long empId, int month, int year)
	{
		List dataList = new ArrayList();
		Session hibSession = getSession();

		/*String query = " from  HrPayPayslipNonGovt e where  ";
		query+=" e.paybillID.hrEisEmpMst.empId = "+empId;
		query+=" and e.paybillID.month = "+month;
		query+=" and e.paybillID.year = "+year;*/
		// Updated By Urvin Shah
		String query = " select e from  HrPayPayslipNonGovt e,PaybillHeadMpg h where ";
		query += " e.paybillID.hrEisEmpMst.empId = " + empId;
		query += " and e.paybillID.paybillGrpId=h.hrPayPaybill and h.month = " + month;
		query += " and h.year = " + year ;
		// End
		Query sqlQuery = hibSession.createQuery(query);
		logger.info("The non gov payslip query is------>>>>>" + sqlQuery);
		dataList = sqlQuery.list();
		return dataList;
	}

	public List getNonGovDeducDataByEmpId(long empId)
	{
		List dataList = new ArrayList();
		String query = "";
		Session hibSession = getSession();
		query = " from HrPayNonGovDeduction as nonGovDeduc where nonGovDeduc.hrEisEmpMst.empId=" + empId + " order by nonGovDeduc.nonGovDeducId";
		Query sqlQuery = hibSession.createQuery(query);
		dataList = sqlQuery.list();
		return dataList;
	}

	public List getNonGovDeducDataByEmps(String empIdStr,long locId,long billNo,Date givenDate)
	{
		//System.out.println("getNonGovDeducDataByEmps query is ");
 		StringBuffer query= new StringBuffer();
        Session hibSession = getSession();
        query.append("SELECT nd.NON_GOV_DEDUC_ID,nd.EMP_ID,nd.NON_GOV_DEDUC_TYPE,nd.NON_GOV_DEDUC_AMOUNT FROM   ");
        query.append(" HR_PAY_DEDUC_TYPE_MST dm , HR_PAY_NON_GOV_DEDUCTION nd inner join ");
        query.append(payrollBundle.getString("getEmployeeList"));
        query.append(" on emp_list.emp_id = nd.EMP_ID where "); 
        query.append("nd.NON_GOV_DEDUC_TYPE = dm.DEDUC_CODE "); 
        query.append("and dm.DEDUC_TYPE = 300160 "); 
        /*query.append("and compoGrp.LOC_ID = "); 
        query.append(locId);
        query.append(" and compoGrp.IS_ACTIVE = 1 "); 
        query.append("and compoGrp.COMPO_GROUP_ID = compoMpg.COMPO_GRP_ID "); 
        query.append("and compoMpg.COMPO_TYPE = 2500135 "); 
        query.append("and compoMpg.COMPO_ID = dm.DEDUC_CODE ");
        query.append("and compoMpg.ISACTIVE = 1 ");*/
        query.append(" order by nd.EMP_ID,nd.NON_GOV_DEDUC_ID ");
        Query sqlQuery = hibSession.createSQLQuery(query.toString());
        sqlQuery.setParameter("locId", locId);
        sqlQuery.setParameter("billNo", billNo);
        sqlQuery.setParameter("givenDate",givenDate);
        logger.info("Query for getNonGovDeducDataByEmps"+sqlQuery.toString());
        logger.info("locId"+locId);
        logger.info("billNo"+ billNo);
        logger.info("givenDate"+givenDate);
        return sqlQuery.list();
        
 	}

	public List chkNonGovtForPayslip(String empIdStr, int month, int year)
	{
		List dataList = new ArrayList();
		Session hibSession = getSession();

		/*String query = " from  HrPayPayslipNonGovt e where  ";
		query+=" e.paybillID.hrEisEmpMst.empId = "+empId;
		query+=" and e.paybillID.month = "+month;
		query+=" and e.paybillID.year = "+year;*/
		// Updated By Urvin Shah
		String query = "select e.paybillID.hrEisEmpMst.empId,nonGovtId from  HrPayPayslipNonGovt e,PaybillHeadMpg h where ";
		query += " e.paybillID.hrEisEmpMst.empId in( " + empIdStr + ") ";
		query += " and e.paybillID.paybillGrpId=h.hrPayPaybill and h.month = " + month;
		query += " and h.year = " + year + " and approveFlag in (0,1) order by e.paybillID.hrEisEmpMst.empId";
		// End
		Query sqlQuery = hibSession.createQuery(query);
		logger.info("The non gov payslip query is------>>>>>" + sqlQuery);
		dataList = sqlQuery.list();
		return dataList;
	}
	
	public List checkNonGovtPayslipEntries(String empIdStr, int month, int year)
	{
		List dataList = new ArrayList();
		Session hibSession = getSession();

		/*String query = " from  HrPayPayslipNonGovt e where  ";
		query+=" e.paybillID.hrEisEmpMst.empId = "+empId;
		query+=" and e.paybillID.month = "+month;
		query+=" and e.paybillID.year = "+year;*/
		// Updated By Urvin Shah
		String query = "select e.nonGovtId,e.paybillID.hrEisEmpMst.empId,e.deducCode,e.deducAmount from  HrPayPayslipNonGovt e,PaybillHeadMpg h where ";
		query += " e.paybillID.hrEisEmpMst.empId in( " + empIdStr + ") ";
		query += " and e.paybillID.paybillGrpId=h.hrPayPaybill and  h.month = " + month;
		query += " and h.year = " + year + " and approveFlag in (0,1) order by e.paybillID.hrEisEmpMst.empId";
		// End
		Query sqlQuery = hibSession.createQuery(query);
		logger.info("The non gov payslip query is------>>>>>" + sqlQuery);
		dataList = sqlQuery.list();
		return dataList;
	}

	public List getNonGovDataById(List nonGovDeducId)
	{
		Criteria objCrt = null;
		List nonGovList = new ArrayList();
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrPayNonGovDeduction.class);
		objCrt.add(Restrictions.in("nonGovDeducId", nonGovDeducId));
		objCrt.addOrder(Order.asc("nonGovDeducAccNo"));
		nonGovList = objCrt.list();
		return nonGovList;
	}

	public List getPayslipNonGovtDataByEmp(long empId, long month, long year)
	{
		List lstPayslipNonGovData = new ArrayList();
		String query = "";
		Session hibSession = getSession();
		ResourceBundle rsPayroll = ResourceBundle.getBundle("resources.Payroll");
		long aprovFlag = Long.parseLong(rsPayroll.getString("created"));
		query = " select hrPayPayslipNonGovt  from HrPayPayslipNonGovt as hrPayPayslipNonGovt,PaybillHeadMpg h where hrPayPayslipNonGovt.paybillID.hrEisEmpMst.orgEmpMst.empId=" + empId + " and hrPayPayslipNonGovt.paybillID.paybillGrpId=h.hrPayPaybill and h.month =" + month + " and h.year=" + year + " and h.approveFlag=" + aprovFlag;
		Query sqlQuery = hibSession.createQuery(query);
		lstPayslipNonGovData = sqlQuery.list();
		logger.info("The paybill List size is:-" + lstPayslipNonGovData.size());
		return lstPayslipNonGovData;
	}

	public List getPaybillNonGovtDataByEmp(long empId, long month, long year)
	{
		List lstPaybillNonGovData = new ArrayList();
		String query = "";
		Session hibSession = getSession();
		ResourceBundle rsPayroll = ResourceBundle.getBundle("resources.Payroll");
		long aprovFlag = Long.parseLong(rsPayroll.getString("created"));
		query = " select p from HrPayPaybill as p,PaybillHeadMpg h where p.hrEisEmpMst.orgEmpMst.empId=" + empId + " and p.paybillGrpId=h.hrPayPaybill and  h.month =" + month + " and h.year=" + year + " and h.approveFlag=" + aprovFlag ;
		Query sqlQuery = hibSession.createQuery(query);
		lstPaybillNonGovData = sqlQuery.list();
		return lstPaybillNonGovData;
	}

	public List getOtherNonGovAmt(long empId, long nonGovType, String AccNo, String startDate, String endDate)
	{

		List dataList = new ArrayList();
		Session hibSession = getSession();
		try
		{
			String query = " from  HrPayNonGovDeduction e where  ";
			if (AccNo != null && !AccNo.equals(""))
				query += "  e.nonGovDeducAccNo != '" + AccNo + "' and ";
			if (nonGovType != 0)
				query += "  e.hrPayDeducTypeMst.deducCode = '" + nonGovType + "' and";
			if (empId != 0)
				query += "  e.hrEisEmpMst.orgEmpMst.empId  =" + empId;
			if (startDate != null && !startDate.equals("") || endDate != null && !endDate.equals(""))
			{
				DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
				String getStartDate = sbConf.to_char("e.nonGovDeducEfftStartDt");
				String getEndDate = sbConf.to_char("e.nonGovDeducEfftEndDt");
				if (startDate != null && !startDate.equals(""))
					query += " and " + getStartDate + " <= '" + startDate + "' ";
				if (endDate != null && !endDate.equals(""))
					query += " and ( " + getEndDate + " >= '" + endDate + "' or e.nonGovDeducEfftEndDt is null   ) ";
			}

			Query sqlQuery = hibSession.createQuery(query);
			dataList = sqlQuery.list();
		}
		catch (Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE HANDLING THE START AND END DATE IN NonGovDeducDAOImpl.java : ", e);
			return dataList;
		}
		return dataList;
	}

	public List getListByLocId(long locId, long billNo)
	{
		Session hibSession = getSession();
		StringBuffer strLonQueryBuf = new StringBuffer();
		strLonQueryBuf.append("select distinct (nd.emp_id),up.post_id from hr_pay_non_gov_deduction nd ");
		strLonQueryBuf.append(" inner join hr_eis_other_dtls od on od.emp_id  = nd.emp_id ");
		strLonQueryBuf.append(" inner join hr_eis_emp_mst eisemp on eisemp.emp_id  = od.emp_id ");
		strLonQueryBuf.append(" inner join org_emp_mst orgemp on orgemp.emp_id = eisemp.emp_mpg_id ");
		strLonQueryBuf.append(" inner join org_userpost_rlt up on up.user_id = orgemp.user_id ");
		if (billNo != 0)
		{
			strLonQueryBuf.append(" inner join hr_pay_post_psr_mpg psr on psr.post_id = up.post_id ");
			strLonQueryBuf.append(" inner join mst_dcps_bill_group mpg on mpg.bill_group_id = psr.bill_no ");
			strLonQueryBuf.append(" where up.activate_flag=1 and mpg.bill_group_id=");
			strLonQueryBuf.append(billNo);
			strLonQueryBuf.append(" and mpg.loc_id=");
			strLonQueryBuf.append(locId);
			strLonQueryBuf.append(" and nd.loc_id=");
			strLonQueryBuf.append(locId);
		}
		else
		{
			strLonQueryBuf.append(" where up.activate_flag=1 ");
			strLonQueryBuf.append(" and nd.loc_id=");
			strLonQueryBuf.append(locId);
		}
		Query loanQuery = hibSession.createSQLQuery(strLonQueryBuf.toString());
		return loanQuery.list();
	}

	//Added by Kishan

	public List getNonGovDeducType(long locid, long deducType)
	{
		List nonGovDeducTypeList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("select hrPayDeducTypeMst from HrPayLocComMpg hrpaylocmpg,HrPayCompGrpMst hrpaycompgrpmst,HrPayDeducTypeMst hrPayDeducTypeMst ");
		queryBuffer.append("where hrpaylocmpg.hrpaycompgrpmst.cmnLocationMst.locId = " + locid + " ");
		queryBuffer.append("and hrpaylocmpg.cmnLookupMst.lookupId = 2500135 and hrpaylocmpg.isactive = 1 ");
		queryBuffer.append("and hrpaylocmpg.hrpaycompgrpmst.compoGrpId=hrpaycompgrpmst.compoGrpId ");
		queryBuffer.append("and hrpaycompgrpmst.isactive = 1 ");
		queryBuffer.append("and hrPayDeducTypeMst.deducType = " + deducType + " ");
		queryBuffer.append("and hrpaylocmpg.compId = hrPayDeducTypeMst.deducCode ");
		Query query = hibSession.createQuery(queryBuffer.toString());
		//Query query = hibSession.createQuery(" from HrPayDeducTypeMst as hrPayDeducTypeMst where hrPayDeducTypeMst.cmnLanguageMst.langId="+langId+" and hrPayDeducTypeMst.deducType = "+deducType);
		nonGovDeducTypeList = query.list();
		logger.info("Y r in non Governement Deduction dao:-" + nonGovDeducTypeList.size());
		return nonGovDeducTypeList;
	}

	public List getEmployeeByDeductionType(long deducType, long locId, long dsgnId, long billNo, long employeeId)
	{Session hibSession = getSession();
	StringBuffer strQueryBuf = new StringBuffer();
	strQueryBuf.append("SELECT eis.EMP_ID,org.EMP_LNAME||' '|| org.EMP_FNAME||' ' || org.EMP_MNAME||' (' ||dsgn.DSGN_SHRT_NAME||')' as name ");
	strQueryBuf.append(",case when non.NON_GOV_DEDUC_TYPE = " + deducType + " then non.NON_GOV_DEDUC_AMOUNT ELSE 123456789012345 END AS DEDUC_AMOUNT, non_gov_type "); //123456789012345 = flag which means this employee not in  HR_PAY_NON_GOV_DEDUCTION so need to insert
	strQueryBuf.append("FROM HR_PAY_POST_PSR_MPG psr ");
	strQueryBuf.append(", ORG_USERPOST_RLT usr ");
	strQueryBuf.append(", ORG_EMP_MST org ");
	strQueryBuf.append(", HR_EIS_EMP_MST eis ");
	strQueryBuf.append("left outer join HR_PAY_NON_GOV_DEDUCTION non on ");
	strQueryBuf.append("non.EMP_ID = eis.EMP_ID ");
	//strQueryBuf.append("and non.LOC_ID = "+locId+" "); 
	strQueryBuf.append("and non.NON_GOV_DEDUC_TYPE = " + deducType + " ");
	strQueryBuf.append(", ORG_DESIGNATION_MST dsgn ");
	strQueryBuf.append(", ORG_POST_DETAILS_RLT postrlt ");
	strQueryBuf.append("where ");
	if(billNo != 1)
		strQueryBuf.append("psr.BILL_NO = " + billNo + " and ");
	strQueryBuf.append("usr.POST_ID = psr.POST_ID ");
	strQueryBuf.append("and psr.LOC_ID = " + locId + " ");
	strQueryBuf.append("and usr.USER_ID = org.USER_ID ");
	strQueryBuf.append("and usr.ACTIVATE_FLAG = 1 ");
	strQueryBuf.append("and eis.EMP_MPG_ID = org.EMP_ID ");
	strQueryBuf.append("and postrlt.POST_ID = usr.POST_ID ");
	strQueryBuf.append("and dsgn.DSGN_ID = postrlt.DSGN_ID ");
	if (dsgnId > 0)
	{
		strQueryBuf.append("and dsgn.DSGN_ID = " + dsgnId + " ");
	}
	if (employeeId > 0)
	{
		strQueryBuf.append("and org.EMP_ID = " + employeeId + " ");
	}
	strQueryBuf.append("order by name");

	Query deducQuery = hibSession.createSQLQuery(strQueryBuf.toString());
	logger.info("The getEmployeeByDeductionType query is------>>>>>" + deducQuery);
	return deducQuery.list();}

	public List getNonGovDeducDataByEmpId(String empId, long deducType)
	{
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		strQueryBuf.append("from HrPayNonGovDeduction hrPayNonGovDeduction where hrPayNonGovDeduction.hrEisEmpMst.empId in ( " + empId + " ) and ");
		strQueryBuf.append("hrPayNonGovDeduction.hrPayDeducTypeMst.deducCode = :deducType");
		Query deducQuery = hibSession.createQuery(strQueryBuf.toString());
		deducQuery.setLong("deducType", deducType);
		logger.info("The getNonGovDeducDataByEmpId query is------>>>>>" + deducQuery);
		List resultList = deducQuery.list();
		return resultList;
	}

	public List getPayPaySlipDataByEmpId(String empId, long billNo, int month, int year,long locId, long deducCode)
	{
		//System.out.println("Kishan Shah from getPayPaySlipDataByEmpId");
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		strQueryBuf.append("select payslip,paybill.hrEisEmpMst.empId FROM HrPayPayslipNonGovt payslip, PaybillHeadMpg head, HrPayPaybill paybill ");
		
		if(billNo == 1)
		{
			strQueryBuf.append(",MstDcpsBillGroup bill "); 
		}
		strQueryBuf.append("where payslip.paybillID = paybill.id ");
		strQueryBuf.append("and paybill.hrEisEmpMst.empId in ( " + empId + ") ");
		strQueryBuf.append("and paybill.month = " + month + " and paybill.year = " + year + " ");
		strQueryBuf.append("and paybill.paybillGrpId = head.hrPayPaybill ");
		if(billNo == 1)
		{
			strQueryBuf.append("and head.billNo.dcpsDdoBillGroupId = bill.dcpsDdoBillGroupId ");
			strQueryBuf.append("and bill.LocId = "+locId+" ");
		}
		else
		{
			strQueryBuf.append("and head.billNo.dcpsDdoBillGroupId = " + billNo + " ");
		}
		strQueryBuf.append("and head.approveFlag in (0,1) ");
		strQueryBuf.append("and payslip.deducCode = "+deducCode+" ");

		Query deducQuery = hibSession.createQuery(strQueryBuf.toString());
		List resultList = deducQuery.list();

		return resultList;
	}

	public List getPayBillIdByEmpId(String empId, int month, int year, long BillNo, long locId)
	{
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		/*strQueryBuf.append("SELECT paybill.id,eis.EMP_ID FROM ORG_EMP_MST empMst ,HR_EIS_EMP_MST eis, ORG_USERPOST_RLT userPost,HR_PAY_POST_PSR_MPG psr, ");
		strQueryBuf.append("PAYBILL_HEAD_MPG head,HR_PAY_PAYBILL paybill where empMst.EMP_ID = eis.EMP_MPG_ID and userPost.USER_ID = empMst.USER_ID ");
		strQueryBuf.append("and userPost.ACTIVATE_FLAG = 1 and userPost.POST_ID = psr.POST_ID and psr.BILL_NO = head.BILL_NO ");
		strQueryBuf.append("and head.APPROVE_FLAG in (0,1) and head.PAYBILL_MONTH = "+month+" and head.PAYBILL_YEAR = "+year+" ");
		strQueryBuf.append("and paybill.PAYBILL_GRP_ID = head.PAYBILL_ID and paybill.id = (select max(id) from HR_PAY_PAYBILL where emp_id = eis.EMP_ID ) ");
		strQueryBuf.append("and eis.EMP_ID in ( "+empId+") ");*/

		strQueryBuf.append("SELECT paybill.ID,paybill.EMP_ID FROM HR_PAY_PAYBILL paybill, PAYBILL_HEAD_MPG head ");
		if(BillNo == 1)
			strQueryBuf.append(", MST_DCPS_BILL_GROUP bill ");
		strQueryBuf.append("where paybill.PAYBILL_GRP_ID = head.PAYBILL_ID ");
		if(BillNo == 1)
		{
			strQueryBuf.append("and head.BILL_NO = bill.BILL_GROUP_ID ");
			strQueryBuf.append("and bill.LOC_ID = "+locId+" ");
		}
		else
		{
			strQueryBuf.append("and head.BILL_NO = "+BillNo+" ");
		}
		strQueryBuf.append("and head.PAYBILL_MONTH = " + month + " ");
		strQueryBuf.append("and head.PAYBILL_YEAR = " + year + " ");
		strQueryBuf.append("and head.APPROVE_FLAG in (0,1) ");
		strQueryBuf.append("and paybill.EMP_ID in (" + empId + ") ");
		
		Query deducQuery = hibSession.createSQLQuery(strQueryBuf.toString());
		List payBillIdList = deducQuery.list();
		return payBillIdList;
	}

	public List checkPayBillIdCount(String payBillId,long deducCode)
	{
		//System.out.println("Kishan shah");
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		strQueryBuf.append("select payslip from HrPayPayslipNonGovt payslip where payslip.paybillID in ( " + payBillId + ") ");
		strQueryBuf.append("and payslip.deducCode = "+deducCode+" ");
		Query deducQuery = hibSession.createQuery(strQueryBuf.toString());

		return deducQuery.list();
	}
	
	public long getBillNoByEmpID(long empId)
	{
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		strQueryBuf.append("SELECT BILL_NO FROM HR_PAY_POST_PSR_MPG where POST_ID = (SELECT post_id FROM ORG_USERPOST_RLT where ACTIVATE_FLAG = 1 and USER_ID = ");
		strQueryBuf.append("(SELECT USER_ID FROM ORG_EMP_MST where EMP_ID = " + empId + "))");
		Query deducQuery = hibSession.createSQLQuery(strQueryBuf.toString());
		BigInteger result = (BigInteger) deducQuery.uniqueResult();
		String bill_no_str = "" + result;
		long bill_no_long = Long.parseLong(bill_no_str);
		return bill_no_long;
	}

	public List getHrEisEmpVO(String empId)
	{
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		strQueryBuf.append("SELECT hrEisEmpMst from HrEisEmpMst hrEisEmpMst where hrEisEmpMst.empId in (" + empId + ")");
		Query deducQuery = hibSession.createQuery(strQueryBuf.toString());
		return deducQuery.list();
	}

	public List getPayBillMonthAndYear(long BillNo)
	{
		//This method is not used anywhere just put here for future reference - Kishan
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		strQueryBuf.append("select max(head.PAYBILL_MONTH),head.PAYBILL_YEAR from PAYBILL_HEAD_MPG head,PAYBILL_HEAD_MPG head2 ");
		strQueryBuf.append("where head.BILL_NO  = :BillNo ");
		strQueryBuf.append("and head.BILL_NO=head2.BILL_NO ");
		strQueryBuf.append("and head.APPROVE_FLAG in (0,1) ");
		strQueryBuf.append("group by head.PAYBILL_YEAR ");
		strQueryBuf.append("having  head.PAYBILL_YEAR = max(head2.PAYBILL_YEAR) ");
		Query monthQuery = hibSession.createSQLQuery(strQueryBuf.toString());
		monthQuery.setLong("BillNo", BillNo);
		return monthQuery.list();
	}

	public List getHrPayNonGovDeductionData(long empId)
	{
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();

		strQueryBuf.append("select hrPayNonGovDeduction from HrPayNonGovDeduction hrPayNonGovDeduction where hrPayNonGovDeduction.hrEisEmpMst.empId in (" + empId + ") ");
		Query dataQuery = hibSession.createQuery(strQueryBuf.toString());
		return dataQuery.list();
	}
	
	public boolean isBillGenOrApp(long BillNo, int month, int year)
	{
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		strQueryBuf.append("select paybill from PaybillHeadMpg paybill ");
		strQueryBuf.append("where paybill.month = "+month+" ");
		strQueryBuf.append("and paybill.year = "+year+" ");
		strQueryBuf.append("and paybill.billNo.dcpsDdoBillGroupId = "+BillNo+" ");
		strQueryBuf.append("and paybill.approveFlag in (0,1) ");
		Query dataQuery = hibSession.createQuery(strQueryBuf.toString());
		List data = dataQuery.list();
		if(!data.isEmpty() && data.size() > 0)
			return true;
		else
			return false;
	}

	//Ended By Kishan	
	 //Added By roshan for CMP Validation
	public boolean isBillFwded(long BillNo, int month, int year)
	{
		Session hibSession = getSession();
		StringBuffer strQueryBuf = new StringBuffer();
		strQueryBuf.append("select PAYBILL_ID from PAYBILL_HEAD_MPG   ");
		strQueryBuf.append("where PAYBILL_MONTH= "+month+" ");
		strQueryBuf.append("and  PAYBILL_YEAR= "+year+" ");
		strQueryBuf.append("and BILL_NO=  "+BillNo+" ");
		strQueryBuf.append("and  APPROVE_FLAG not in (3,-1)");
		Query dataQuery = hibSession.createSQLQuery(strQueryBuf.toString());
		List data = dataQuery.list();
		logger.info("isBillFwded="+dataQuery);
		if(!data.isEmpty() && data.size() > 0)
			return true;
		else
			return false;
	}
	
	//Added By roshan for CMP Validation
}
