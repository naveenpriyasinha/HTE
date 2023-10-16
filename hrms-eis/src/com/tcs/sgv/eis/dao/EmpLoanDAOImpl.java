package com.tcs.sgv.eis.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public class EmpLoanDAOImpl extends GenericDaoHibernateImpl<HrLoanEmpDtls, Long> implements EmpLoanDAO
{
	Log logger = LogFactory.getLog(getClass());

	public EmpLoanDAOImpl(Class<HrLoanEmpDtls> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
	String sdate = sbConf.getSysdate();
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");

	String activateFlag = constantsBundle.getString("activate");
	String pausedFlag = constantsBundle.getString("paused");

	public List<HrLoanEmpDtls> getLoanData()
	{

		Criteria objCrt = null;
		try
		{
			Session hibSession = getSession();

			int activate = 0;
			if (activateFlag != null && !activateFlag.equals(""))
				activate = Integer.parseInt(activateFlag);

			objCrt = hibSession.createCriteria(HrLoanEmpDtls.class);

			objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
			objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");

			objCrt.setFetchMode("hrEisEmpMst.orgEmpMst", FetchMode.JOIN);
			objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");

			objCrt.add(Restrictions.eq("loanActivateFlag", activate));
			//11 jan 2012
			objCrt.addOrder(Order.asc("orgEmpMst.empLname"));
			objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
			objCrt.addOrder(Order.asc("orgEmpMst.empMname"));

		}
		catch (Exception e)
		{

			logger.error("Error is: " + e.getMessage());
		}
		return objCrt.list();
	}

	public List<HrLoanEmpDtls> getEmpLoanData(OrgEmpMst orgEmpMst)
	{
		Criteria objCrt = null;
		try
		{
			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(HrLoanEmpDtls.class);
			objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
			objCrt.add(Restrictions.eq("hrEisEmpMst.orgEmpMst", orgEmpMst));
		}
		catch (Exception e)
		{
			logger.info("There some error in DAOImpl");
			logger.error("Error is: " + e.getMessage());
		}
		return objCrt.list();
	}

	public List<HrLoanEmpDtls> getEmpLoanDetail(Long empId, String loanAdvId)
	{
		HrLoanEmpDtls hrEmpLoan = new HrLoanEmpDtls();
		Criteria objCrt = null;
		Query sqlQuery1;
		List resultList = new ArrayList();

		Session hibSession = getSession();
		StringBuffer query1 = new StringBuffer("from HrLoanEmpDtls as empLookup where empLookup.hrLoanAdvMst.loanAdvId in (");
		query1.append(loanAdvId).append(") and hrEisEmpMst.orgEmpMst.empId = " + empId + " and loanActivateFlag in (").append(activateFlag).append(",").append(pausedFlag).append(")");
		sqlQuery1 = hibSession.createQuery(String.valueOf(query1));
		resultList = sqlQuery1.list();
		logger.info("the size of the list is from dao of loan " + resultList);
		logger.info("check this query for advance list with empid " + empId + " " + query1);
		/*objCrt = hibSession.createCriteria(HrLoanEmpDtls.class);
		objCrt.add(Restrictions.eq("hrEisEmpMst.empId", empId));
		objCrt.add(Restrictions.in("hrLoanAdvMst.loanAdvId", loanAdvId));*/
		logger.info(query1);
		logger.info(" ODD CHECK  ");
		return resultList;

	}

	public HrLoanEmpDtls getEmpLoanDetail(Long loanid)
	{
		HrLoanEmpDtls hrEmpLoan = new HrLoanEmpDtls();
		try
		{
			logger.info("inside getEmpLoanDetail---------------");
			Session hibSession = getSession();
			String query1 = "from HrLoanEmpDtls as empLookup where empLookup.empLoanId = " + loanid;
			Query sqlQuery1 = hibSession.createQuery(query1);
			hrEmpLoan = (HrLoanEmpDtls) sqlQuery1.uniqueResult();
			logger.info("loan Id  is-------" + loanid);
			logger.info("query is----" + sqlQuery1);

		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}
		return hrEmpLoan;
	}

	public List<HrLoanAdvMst> getLoanNameByEDP(String edpCode)
	{
		List<HrLoanAdvMst> loanList = null;

		Session hibSession = getSession();
		String query1 = "from HrLoanAdvMst as mst where mst.loanAdvId in" + " (select m.typeId from HrPayEdpCompoMpg as m where m.rltBillTypeEdp.edpCode='" + edpCode + "')";
		logger.info("Query for EDP Code is " + query1);
		Query loanListQuery = hibSession.createQuery(query1);
		loanList = loanListQuery.list();
		if (loanList.size() > 0 && loanList != null)
		{
			for (Iterator it = loanList.iterator(); it.hasNext();)
			{
				HrLoanAdvMst hrLoanAdvMst = new HrLoanAdvMst();
				hrLoanAdvMst = (HrLoanAdvMst) it.next();
				logger.info("DAO Query from EDP Code get exceuted..." + hrLoanAdvMst.getLoanAdvName() + loanList.size());
			}
		}
		return loanList;

	}

	public List getEmpLoanData(List userList, long EmpId)
	{
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrLoanEmpDtls.class);

		objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
		objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
		objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");
		if (userList.size() > 0)
			objCrt.add(Restrictions.in("orgEmpMst.orgUserMst", userList));
		if (EmpId != 0)
			objCrt.add(Restrictions.eq("hrEisEmpMst.empId", EmpId));
		//12 jan 2012
		objCrt.addOrder(Order.asc("orgEmpMst.empLname"));
		objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
		objCrt.addOrder(Order.asc("orgEmpMst.empMname"));

		return objCrt.list();
	}

	public List getEmpLoanData(String locationCode, long EmpId, long langId, String... strings)
	{

		List empList = new ArrayList();

		Session hibSession = getSession();
		logger.info("strings" + strings);
		StringBuffer query = new StringBuffer(" select e from  HrLoanEmpDtls e,OrgUserpostRlt up,OrgPostDetailsRlt pd where  ");
		if (!locationCode.equals(""))//location specific
		{
			query.append(" e.hrEisEmpMst.orgEmpMst.orgUserMst.userId=up.orgUserMst.userId and pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1 and ");
			query.append(" pd.cmnLocationMst.locId=").append(Long.parseLong(locationCode)).append(" and e.hrEisEmpMst.orgEmpMst.empSrvcExp >= ").append(sdate);
		}

		if (EmpId != 0)
			query.append(" and e.hrEisEmpMst.orgEmpMst.empId  = ").append(EmpId);

		if (strings != null && strings.length > 0 && "yes".equalsIgnoreCase(strings[0])) // This means the request is from Salary Config and only Active Loans needs to be taken
			query.append(" and e.loanActivateFlag = ").append(activateFlag);
		else
			query.append(" and e.loanActivateFlag in (").append(activateFlag).append(",").append(pausedFlag).append(")");

		//12 jan 2012
		query.append(" order by e.hrEisEmpMst.orgEmpMst.empLname,e.hrEisEmpMst.orgEmpMst.empFname,e.hrEisEmpMst.orgEmpMst.empMname ");

		Query sqlQuery = hibSession.createQuery(String.valueOf(query));

		empList = sqlQuery.list();

		return empList;
	}//end method

	/**
	 *  Author	:	Varun Sharma
	 *  Purpose	: 	Returns list all loans for the given employee including outstanding amount for Principle and Interest  
	 *  Date	: 	03-03-2009
	 */

	public List getEmployeeLoanData(String locationCode, long EmpId, long langId, String... strings)
	{

		logger.info("inside getEmpLoanDataForBasicDetail in dao");
		List empList = new ArrayList();

		try
		{

			Session hibSession = getSession();

			StringBuffer hqlQuery = new StringBuffer();
			hqlQuery.append("SELECT e  ");
			hqlQuery.append("	FROM HrLoanEmpDtls e , OrgUserpostRlt up,OrgPostDetailsRlt pd  ");
			hqlQuery.append("		WHERE 	");

			if (!locationCode.equals(""))//location specific
			{
				hqlQuery.append(" e.hrEisEmpMst.orgEmpMst.orgUserMst.userId = up.orgUserMst.userId  and  pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1 and ");
				hqlQuery.append(" pd.cmnLocationMst.locationCode = '" + locationCode + "' and pd.cmnLocationMst.cmnLanguageMst.langId=" + langId + "  and e.hrEisEmpMst.orgEmpMst.empSrvcExp >= " + sdate + " and ");
			}

			if (EmpId != 0)
				hqlQuery.append(" e.hrEisEmpMst.orgEmpMst.empId  = " + EmpId);

			if (strings != null && strings.length > 0 && "yes".equalsIgnoreCase(strings[0])) // This means the request is from Salary Config and only Active Loans needs to be taken
				hqlQuery.append(" and e.loanActivateFlag = ").append(activateFlag);
			else
				hqlQuery.append(" and e.loanActivateFlag in (").append(activateFlag).append(",").append(pausedFlag).append(")");
			// hqlQuery.append(" and ((e.loanPrinInstNo > p.totalRecoveredInst and e.loanPrinEmiAmt > 0) ");
			//  hqlQuery.append(" or (e.loanIntInstNo > i.totalRecoveredIntInst and e.loanIntEmiAmt > 0 ))");
			//12 jan 2012
			hqlQuery.append(" order by e.hrEisEmpMst.orgEmpMst.empLname,e.hrEisEmpMst.orgEmpMst.empFname,e.hrEisEmpMst.orgEmpMst.empMname ");

			Query query = hibSession.createQuery(hqlQuery.toString());

			empList = query.list();
			logger.info("list size is: " + empList.size());
		}
		catch (Exception e)
		{
			logger.info("Exception Occured: ");
			logger.info(e);
			logger.error("Error is: " + e.getMessage());
		}
		return empList;
	}

	/**
	 *  Author:	 Urvin Shah
	 *  Purpose: This method return the list of the records for which the paybill is already generated for the same employee for the same month and year.
	 *  Date: 	 01-09-2008 
	 */

	public List getPaybillLoanDtls(long empId, long loanTypeId, long recoveryType, int monthGiven, int yearGiven)
	{
		List lstLoanDtls = new ArrayList();
		Session hibSession = getSession();
		//String strQuery = " from HrPayPaybillLoanDtls hrPayPaybillLoanDtls where hrPayPaybillLoanDtls.recoveryType.lookupId="+ recoveryType+" and hrPayPaybillLoanDtls.hrLoanAdvMst.loanAdvId="+loanTypeId+" and  hrPayPaybillLoanDtls.paybillId.month="+(double)monthGiven+" and hrPayPaybillLoanDtls.paybillId.year="+(double)yearGiven +" and hrPayPaybillLoanDtls.paybillId.hrEisEmpMst.empId="+empId;
		String strQuery = " from HrPayPaybillLoanDtls hrPayPaybillLoanDtls where hrPayPaybillLoanDtls.recoveryType.lookupId=" + recoveryType + " and hrPayPaybillLoanDtls.hrLoanAdvMst.loanAdvId=" + loanTypeId + " and hrPayPaybillLoanDtls.paybillId.paybillGrpId in (select distinct(hrPayPaybill) from PaybillHeadMpg headMpg where headMpg.month =" + (double) monthGiven + " and headMpg.year=" + (double) yearGiven + " and headMpg.approveFlag in (0,1)) and hrPayPaybillLoanDtls.paybillId.hrEisEmpMst.empId=" + empId;
		Query sqlQuery = hibSession.createQuery(strQuery);
		lstLoanDtls = sqlQuery.list();
		return lstLoanDtls;
	}

	/**
	 * Method will fetch loan list according to location code and
	 * bill number. If bill number is 0 then it will fetch all the
	 * employees for given location. It will fetch loan data whose
	 * activate flag will be 1.
	 * @param locId
	 * @param billNo
	 * @return List of active loans.
	 */
	public List getEmployeeByLocationId(long locId, long billNo, long langId)
	{
		Session hibSession = getSession();
		StringBuffer strLonQueryBuf = new StringBuffer();
		strLonQueryBuf.append("select distinct (le.emp_id),up.post_id from hr_loan_emp_dtls AS le ");
		strLonQueryBuf.append("inner join hr_eis_other_dtls AS od on od.emp_id  = le.emp_id ");
		strLonQueryBuf.append("inner join hr_eis_emp_mst AS eisemp on eisemp.emp_id  = od.emp_id ");
		strLonQueryBuf.append("inner join org_emp_mst AS orgemp on orgemp.emp_id = eisemp.emp_mpg_id ");
		strLonQueryBuf.append("inner join org_userpost_rlt AS up on up.user_id = orgemp.user_id ");
		if (billNo != 0)
		{
			strLonQueryBuf.append("inner join hr_pay_post_psr_mpg AS psr on psr.post_id = up.post_id ");
			strLonQueryBuf.append("inner join mst_dcps_bill_group AS mpg on mpg.bill_group_id = psr.bill_no ");
			strLonQueryBuf.append(" where up.activate_flag=1 and mpg.bill_group_id=");
			strLonQueryBuf.append(billNo);
			strLonQueryBuf.append(" and mpg.loc_id=");
			strLonQueryBuf.append(locId);
			strLonQueryBuf.append(" and le.loan_activate_flag=1 and le.loc_id=");
			strLonQueryBuf.append(locId);
		}
		else
		{
			strLonQueryBuf.append(" where up.activate_flag=1 and le.loan_activate_flag=1 ");
			strLonQueryBuf.append(" and le.loc_id=");
			strLonQueryBuf.append(locId);
		}
		strLonQueryBuf.append(" and orgemp.LANG_ID=");
		strLonQueryBuf.append(langId);
		Query loanQuery = hibSession.createSQLQuery(strLonQueryBuf.toString());
		return loanQuery.list();
	}

	/**
	 * Method will change the activate flag value of given
	 * employee loan ID (PK of hr_loan_emp_dtls) to given value.
	 * @param empLoanId
	 * @param status
	 * @return integer - Rows updated
	 */
	public int changeLoanStatus(long empLoanId, int status)
	{
		Session hibSession = getSession();
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("update hr_loan_emp_dtls set loan_activate_flag=");
		strBuffer.append(status);
		strBuffer.append(" where emp_loan_id=");
		strBuffer.append(empLoanId);
		Query updateQuery = hibSession.createSQLQuery(strBuffer.toString());
		return updateQuery.executeUpdate();
	}

	/**
	* @author Amish Parikh
	* @purpose	To prepare Pay Certificate 
	* @return	List of rows of employee loan details  
	* This method will return all the active loans given through Principle Amount mapped to an employee having given empId.
	*/

	public List getMasterdataForEmpLoanPrin(long empId)
	{

		List loanPrinLst = new ArrayList();
		Session hibSession = getSession();

		String queryStr = "from HrLoanEmpPrinRecoverDtls as empLoanPrinDtls where empLoanPrinDtls.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId = " + empId + " and empLoanPrinDtls.hrLoanEmpDtls.loanActivateFlag = 1 and empLoanPrinDtls.hrLoanEmpDtls.loanPrinEmiAmt <> 0";

		Query query = hibSession.createQuery(queryStr);
		logger.info("query is " + query);
		loanPrinLst = query.list();
		return loanPrinLst;

	}

	/**
	 * @author Amish Parikh
	 * @purpose	To prepare Pay Certificate 
	 * @return	List of rows of employee loan details  
	 * This method will return all the active loans given through Interest Amount mapped to an employee having given empId.
	 */

	public List getMasterdataForEmpLoanInt(long empId)
	{

		List loanIntLst = new ArrayList();
		Session hibSession = getSession();

		String queryStr = "from HrLoanEmpIntRecoverDtls as empLoanIntDtls where empLoanIntDtls.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId = " + empId + " and empLoanIntDtls.hrLoanEmpDtls.loanActivateFlag = 1 and empLoanIntDtls.hrLoanEmpDtls.loanIntEmiAmt <> 0";

		Query query = hibSession.createQuery(queryStr);
		logger.info("query is " + query);
		loanIntLst = query.list();
		return loanIntLst;

	}

	//End Amish

	public Map<String, String> getEmpDCPS_GPS(String empIdStr)
	{
		Map<String, String> result = new HashMap<String, String>();

		result.put("type", "G.P.F.NO/DCPS");

		//get HR_LOAN_EMP_DTLS
		Session session = getSession();
		Query q1 = session.createSQLQuery("SELECT dcps.DCPS_ID FROM MST_DCPS_EMP dcps,ORG_EMP_MST org,HR_LOAN_EMP_DTLS loan,hr_Eis_emp_mst eis where dcps.org_emp_mst_id=org.emp_id and eis.emp_mpg_id=org.emp_id and eis.emp_id=loan.emp_id and loan.EMP_LOAN_ID in ( " + empIdStr + ")");
		String o1 = (String) q1.uniqueResult();
		Query q2 = session.createSQLQuery("SELECT gpf.PF_SERIES ||'/'|| gpf.GPF_ACC_NO FROM HR_PAY_GPF_DETAILS gpf,ORG_EMP_MST org,HR_EIS_EMP_MST eis,HR_LOAN_EMP_DTLS loan  where org.USER_ID = gpf.USER_ID  and org.EMP_ID=eis.EMP_MPG_ID and eis.EMP_ID=loan.EMP_ID and loan.EMP_LOAN_ID in ( " + empIdStr + ")");
		String o2 = (String) q2.uniqueResult();
		if (o1 != null)
			result.put("value", o1);
		else if (o2 != null)
			result.put("value", o2);
		else
			result.put("value", "no account found");
		return result;
	}

	public List getEmpsByBillNoForBulkFA(long langId, long billNo, long loanAdvId)
	{
		List lstEmployee;
		Session session = getSession();
		StringBuffer lSB = new StringBuffer();
		lSB.append(" SELECT emp.EMP_ID,emp.EMP_FNAME,emp.EMP_MNAME,emp.EMP_LNAME FROM org_emp_mst emp ");
		lSB.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID = emp.EMP_ID inner join ORG_USERPOST_RLT user on user.USER_ID = emp.USER_ID and user.ACTIVATE_FLAG = 1  ");
		lSB.append(" inner join ORG_POST_DETAILS_RLT rlt on rlt.POST_ID = user.POST_ID  ");
		lSB.append(" inner join HR_PAY_POST_PSR_MPG psr on psr.POST_ID = rlt.POST_ID and psr.POST_ID = user.POST_ID and rlt.LOC_ID = psr.LOC_ID ");
		lSB.append(" left outer join HR_LOAN_EMP_DTLS loan on loan.EMP_ID = eis.EMP_ID and loan.LOAN_TYPE_ID = :loanType and loan.LOAN_ACTIVATE_FLAG = 1 ");
		lSB.append(" where emp.LANG_ID = :langId and loan.EMP_ID is null and psr.BILL_NO = :billNo ");

		Query strQuery = session.createSQLQuery(lSB.toString());
		strQuery.setParameter("billNo", billNo);
		strQuery.setParameter("langId", langId);
		strQuery.setParameter("loanType", loanAdvId);
		lstEmployee = strQuery.list();
		return lstEmployee;
	}

	/**
	 * added by manish khunt
	 * @param empId
	 * @param loanAdvId
	 * @return
	 */
	public Map<String, HrLoanEmpDtls> getEmpLoanDetailAll(String empId)
	{

		Map<String, HrLoanEmpDtls> loanEmpMap = new HashMap<String, HrLoanEmpDtls>();
		Query sqlQuery1;
		List resultList = new ArrayList();

		try
		{
			Session hibSession = getSession();
			StringBuffer query1 = new StringBuffer("select empLookup.hrEisEmpMst.empId,empLookup.hrLoanAdvMst.loanAdvId,empLookup  from HrLoanEmpDtls as empLookup where ");
			query1.append(" empLookup.hrEisEmpMst.empId in( " + empId + ") and loanActivateFlag in (").append(activateFlag).append(",").append(pausedFlag).append(")");
			sqlQuery1 = hibSession.createQuery(String.valueOf(query1));
			resultList = sqlQuery1.list();
			if (resultList != null && !resultList.isEmpty())
			{
				int size = resultList.size();
				for (int i = 0; i < size; i++)
				{
					Object[] arr = (Object[]) resultList.get(i);
					loanEmpMap.put(String.valueOf(arr[0]) + "," + String.valueOf(arr[1]), (HrLoanEmpDtls) arr[2]);
				}
			}
		}
		catch (Exception e)
		{

		}
		return loanEmpMap;

	}

	/**
	 * added by Amish
	 * @param billNo
	 * @param month
	 * @param year
	 * @return
	 */
	public boolean revertInstallments(long billNo, int month, int year)
	{
		Object localObject = getSession();
		try
		{
			Object localObject2 = ((Session) localObject).connection();
			CallableStatement localCallableStatement = ((Connection) localObject2).prepareCall("{call REVERSE_LOAN_INSTALLMENT(?, ?, ?)}");
			localCallableStatement.setLong(1, month);
			localCallableStatement.setInt(2, year);
			localCallableStatement.setLong(3, billNo);
			localCallableStatement.executeUpdate();
			((Connection) localObject2).close();
		}
		catch (Exception e)
		{
			logger.info("Error in revertInstallments--->" + e);
			return false;
		}
		//((Session)localObject).close();
		return true;
	}
}
