package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAO;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class EmpInfoDAOImpl extends GenericDaoHibernateImpl<HrEisEmpMst, Long> implements EmpInfoDAO {
	Log logger = LogFactory.getLog(getClass());
	DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
	String sdate = sbConf.getSysdate();

	public EmpInfoDAOImpl(Class<HrEisEmpMst> type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getAllEmpData(CmnLanguageMst cmnLanguageMst) {
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class);
		// objCrt.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));

		return objCrt.list();
	}

	public List getAllEmpData() {
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class);

		objCrt.setFetchMode("orgEmpMst", FetchMode.JOIN);
		objCrt.createAlias("orgEmpMst", "orgEmpMst");
		objCrt.add(Restrictions.like("orgEmpMst.empSrvcExp", sdate));

		return objCrt.list();
	}

	public List getAllEmpData(List empIdList) {
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class);
		if (empIdList.size() > 0)
			objCrt.add(Restrictions.not(Restrictions.in("empId", empIdList)));

		return objCrt.list();
	}

	public List getEmpIdData(long EmpId, CmnLanguageMst cmnLanguageMst) {
		List list = new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.eq("empId", EmpId));
		// crit.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));

		list = crit.list();
		return list;
	}

	public List getEmpIdData(long EmpId) {
		List list = new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.eq("orgEmpMst.empId", EmpId));

		// crit.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		list = crit.list();
		return list;
	}

	public List getOrgEmpIdData(long EmpId, CmnLanguageMst cmnLanguageMst) {
		List list = new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.eq("empId", EmpId));
		crit.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		list = crit.list();

		return list;
	}

	/*
	 * Function:- This method Collect only those employees who are in org_emp_mst
	 * but not in hr_eis_emp_mst with specific Language.
	 * 
	 */
	public List getOrgEmpData(long langId) {
		List list = new ArrayList();
		Session hibSession = getSession();
		String query = "from OrgEmpMst as orgEmpMst where orgEmpMst.cmnLanguageMst.langId = " + langId
				+ " and orgEmpMst.empId not in(select hrEisEmpMst.empId from HrEisEmpMst as hrEisEmpMst)";
		Query hsqlQuery = hibSession.createQuery(query);
		list = hsqlQuery.list();
		return list;
	}

	public List getOrgEmpList(long langId, List bankDtlsEmpIdList) {
		List list = new ArrayList();
		Session hibSession = getSession();

		Criteria objCrt = null;
		langId = 1;
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,
				getSessionFactory());
		CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

		objCrt = hibSession.createCriteria(HrEisEmpMst.class);
		objCrt = objCrt.createAlias("orgEmpMst", "empid");
		objCrt.add(Restrictions.eq("empid.cmnLanguageMst", cmnLanguageMst));
		// objCrt.add(Restrictions.not(Restrictions.in("empId", bankDtlsEmpIdList)));
		logger.info("the list size from dao  is " + objCrt.list().size());
		// String query = "from OrgEmpMst as orgEmpMst where
		// orgEmpMst.cmnLanguageMst.langId = "+langId +" and orgEmpMst.empId not
		// in(select hrEisEmpMst.empId from HrEisEmpMst as hrEisEmpMst)";
		// Query hsqlQuery = hibSession.createQuery(query);
		list = objCrt.list();
		return list;
	}

	public List findEmpName(String empName, long langId) {

		List empNames = new ArrayList();
		Session hibSession = getSession();
		String query1 = "from HrEisEmpMst as empLookup where (lower(empLookup.orgEmpMst.empFname) like" + " lower('"
				+ empName + "%') or lower(empLookup.orgEmpMst.empMname) like" + " lower('" + empName
				+ "%') or lower(empLookup.orgEmpMst.empLname) like" + " lower('" + empName
				+ "%')) and empLookup.empId in "
				+ "(select distinct(dtls.hrEisEmpMst.empId) from HrEisOtherDtls as dtls) "
				+ "order by empLookup.orgEmpMst.empFname";

		Query sqlQuery1 = hibSession.createQuery(query1);
		empNames = sqlQuery1.list();
		return empNames;
	}

	public List getEmpNamesFromOtherDtls() {

		List empNames = new ArrayList();
		Session hibSession = getSession();
		String query1 = "from HrEisEmpMst as empLookup where empLookup.empId in "
				+ " (select distinct(eisOtherdtls.hrEisEmpMst.empId) from HrEisOtherDtls as eisOtherdtls)"
				+ "order by empLookup.orgEmpMst.empFname";

		Query sqlQuery1 = hibSession.createQuery(query1);
		empNames = sqlQuery1.list();
		return empNames;
	}

	public List getOrgData(OrgUserMst elementCode, long langId) {
		Criteria objCrt = null;
		try {
			Session hibSession = getSession();
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,
					getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			objCrt = hibSession.createCriteria(OrgEmpMst.class);
			objCrt.add(Restrictions.eq("orgUserMst", elementCode));
			objCrt.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
		} catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}
		return objCrt.list();
	}

	public OrgEmpMst getEmployee(OrgUserMst orgUserMst, long langId) {
		OrgEmpMst orgEmpMst = new OrgEmpMst();
		Session hibSession = getSession();
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(langId);
		logger.info("The langId is:-" + langId);
		Criteria crtEmpMst = hibSession.createCriteria(OrgEmpMst.class);
		crtEmpMst.add(Restrictions.eq("orgUserMst", orgUserMst));
		crtEmpMst.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		orgEmpMst = (OrgEmpMst) crtEmpMst.uniqueResult();
		logger.info("The userId is:-" + orgEmpMst.getOrgUserMst().getUserId());
		logger.info("The empId is:-" + orgEmpMst.getEmpId());
		logger.info("The empId is:-" + orgEmpMst.getEmpFname() + orgEmpMst.getEmpMname() + orgEmpMst.getEmpLname());
		return orgEmpMst;
	}

	public List findorgEmpName(String empName, long langId) {

		List empNames = new ArrayList();
		Session hibSession = getSession();
		String query1 = "from OrgEmpMst as empLookup where (lower(empLookup.empFname) like lower('%" + empName + "%') "
				+ " or lower(empLookup.empMname) like lower('%" + empName + "%') "
				+ " or lower(empLookup.empLname) like lower('%" + empName + "%') )"
				+ " and empLookup.cmnLanguageMst.langId='" + langId + "' " + " and empLookup.empId not in "
				+ "(select distinct(dtls.orgEmpMst.empId) from HrEisEmpMst as dtls)";
		Query sqlQuery1 = hibSession.createQuery(query1);
		empNames = sqlQuery1.list();
		return empNames;
	}

	public List<HrEisEmpMst> getHrEmpFromOrgEmp(OrgEmpMst orgEmpInfoMst) {
		HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
		List list = new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.eq("orgEmpMst", orgEmpInfoMst));
		// crit.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		list = crit.list();
		return list;
	}

	public List<HrEisEmpMst> getHrEmpFromOrgEmpId(long empId) {
		HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
		List list = new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.eq("orgEmpMst.empId", empId));
		// crit.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		list = crit.list();
		return list;
	}

	/**
	 * added by Ankit Bhatt - used in Paybill Generation. This method will fetch
	 * such employees whose Grade will be given in Paybill Parameter Page. For e.g
	 * IAS/AIS or Gedgeted or Non-Gadgeted.
	 */

	public List getEmpDataByGrade(long empId, CmnLanguageMst cmnLanguageMst) {
		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer query1 = new StringBuffer();
		query1.append("from HrEisEmpMst as empLookup where empLookup.orgEmpMst.empId = " + empId);
		query1.append(" and ");
		query1.append("empLookup.orgEmpMst.cmnLanguageMst.langId='" + cmnLanguageMst.getLangId() + "'");

		logger.info("Query for Grade in EmpInfoDAO is " + query1.toString());
		Query sqlQuery1 = hibSession.createQuery(query1.toString());
		list = sqlQuery1.list();
		return list;
	}

	public List getUserListByLogin(long langId, long userId) {
		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer query1 = new StringBuffer();
		query1.append(" select u.userId,u.userName ");
		query1.append(" from CmnLocationMst c, ");
		query1.append(" OrgUserpostRlt up, ");
		query1.append(" OrgPostDetailsRlt     p, ");
		query1.append(" OrgUserMst     u ");
		query1.append(" where u.userId = up.orgUserMst.userId and c.locId = p.cmnLocationMst.locId and ");
		query1.append(" up.orgPostMstByPostId.postId = p.orgPostMst.postId and c.cmnLanguageMst.langId = '" + langId
				+ "' and ");
		query1.append(" c.locId in ");
		query1.append(" (select c.locId from CmnLocationMst c,OrgUserpostRlt up, ");
		query1.append(" OrgPostDetailsRlt     p, ");
		query1.append(" OrgUserMst     u ");
		query1.append(" where u.userId = up.orgUserMst.userId and c.locId = p.cmnLocationMst.locId and ");
		query1.append(" up.orgPostMstByPostId.postId = p.orgPostMst.postId and c.cmnLanguageMst.langId = '" + langId
				+ "' and ");
		query1.append(" u.userId = '" + userId + "') ");

		logger.info("Query for getUserListByLogin is " + query1.toString() + "---- ");
		Query sqlQuery1 = hibSession.createQuery(query1.toString());
		list = sqlQuery1.list();
		return list;
	}

	public List getAllEmpDatabyDept(List userList) {
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class);

		objCrt.setFetchMode("orgEmpMst", FetchMode.JOIN);
		objCrt.createAlias("orgEmpMst", "orgEmpMst");

		objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
		objCrt.addOrder(Order.asc("orgEmpMst.empMname"));
		objCrt.addOrder(Order.asc("orgEmpMst.empLname"));
		if (userList != null && userList.size() > 0)
			objCrt.add(Restrictions.in("orgEmpMst.orgUserMst", userList));

		return objCrt.list();
	}

	public List getOrgEmpData(long langId, List userList) {
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(OrgEmpMst.class);
		List EmpList = getAllEmpData();
		List EmpIdList = new ArrayList();
		for (int x = 0; x < EmpList.size(); x++) {
			HrEisEmpMst empMst = new HrEisEmpMst();
			empMst = (HrEisEmpMst) EmpList.get(x);
			EmpIdList.add(empMst.getEmpId());
		}
		if (userList != null && userList.size() > 0)
			objCrt.add(Restrictions.in("orgUserMst", userList));
		if (EmpIdList != null && EmpIdList.size() > 0)
			objCrt.add(Restrictions.not(Restrictions.in("empId", EmpIdList)));
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(1);// hardcoded

		objCrt.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		return objCrt.list();
	}

	public List getUserListByLogin(long userId, long langId, long selectedId) {
		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer query1 = new StringBuffer();
		query1.append(" select u.userId,u.userName ");
		query1.append(" from CmnLocationMst c, ");
		query1.append(" OrgUserpostRlt up, ");
		query1.append(" OrgPostDetailsRlt     p, ");
		query1.append(" OrgUserMst     u ");
		query1.append(" where u.userId = up.orgUserMst.userId and c.locId = p.cmnLocationMst.locId and ");
		query1.append(" up.orgPostMstByPostId.postId = p.orgPostMst.postId and c.cmnLanguageMst.langId = '" + langId
				+ "' and ");
		query1.append(" c.locId in ");
		query1.append(" (select c.locId from CmnLocationMst c,OrgUserpostRlt up, ");
		query1.append(" OrgPostDetailsRlt     p, ");
		query1.append(" OrgUserMst     u ");
		query1.append(" where u.userId = up.orgUserMst.userId and c.locId = p.cmnLocationMst.locId and ");
		query1.append(" up.orgPostMstByPostId.postId = p.orgPostMst.postId and c.cmnLanguageMst.langId = '" + langId
				+ "' and ");
		query1.append(" u.userId in ('" + userId + "')  )  and u.userId='" + selectedId + "'");

		logger.info("Query for getUserListByLogin is " + query1.toString() + "---- ");
		Query sqlQuery1 = hibSession.createQuery(query1.toString());
		list = sqlQuery1.list();
		return list;
	}

	// for getting the list of emp of particular department
	public List getHrEmpFromDeptId(long deptId, String empName) {
		List list = new ArrayList();
		Session hibSession = getSession();

		/*
		 * Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		 * crit.setFetchMode("orgEmpMst", FetchMode.JOIN); crit.createAlias("orgEmpMst",
		 * "orgEmpMst"); crit.setFetchMode("orgEmpMst.orgUserMst", FetchMode.JOIN);
		 * crit.createAlias("orgEmpMst.orgUserMst", "user");
		 * crit.createAlias("user.orgUserpostRlts", "userPosts");
		 * crit.createAlias("userPosts.orgPostMstByPostId", "postMst");
		 * crit.setFetchMode("postMst", FetchMode.JOIN);
		 * crit.createAlias("postMst.orgPostDetailsRlt", "postDetailsRlt");
		 * crit.createAlias("postDetailsRlt.cmnLocationMst", "loc");
		 * crit.add(Restrictions.like("loc.locId", deptId));
		 * 
		 * //Creating logical OR expression and adding it to criteria query.
		 * if(!empName.equals("")) { //Criterion nameString =
		 * Restrictions.eq("nameString",empName); String creteriaEmpName = empName+"%";
		 * //Criterion fName = Restrictions.ilike("orgEmpMst.empFname",creteriaEmpName);
		 * Criterion mName = Restrictions.ilike("orgEmpMst.empMname",creteriaEmpName);
		 * Criterion lName = Restrictions.ilike("orgEmpMst.empLname",creteriaEmpName);
		 * Criterion fName =
		 * Restrictions.sqlRestriction("lower(emp_Fname) like lower(?)",creteriaEmpName
		 * , Hibernate.STRING); //LogicalExpression logicExprName =
		 * Restrictions.or(fName,mName); //logicExprName =
		 * Restrictions.or(logicExprName,lName); //LogicalExpression logicExprLname =
		 * Restrictions.or(lName,nameString);
		 * 
		 * //crit.add(logicExprName);
		 * 
		 * Criterion disjunct =
		 * Restrictions.disjunction().add(fName).add(mName).add(lName);
		 * 
		 * //crit.add(logicExprMname); crit.add(disjunct); } //crit.setMaxResults(1);
		 */
		StringBuffer sb = new StringBuffer();

		sb.append(" from HrEisEmpMst hmst where hmst.orgEmpMst.orgUserMst.userId in ");
		sb.append(
				" ( select distinct(up.orgUserMst.userId) from OrgUserpostRlt up where up.orgPostMstByPostId.postId in ");
		sb.append(" ( select pd.orgPostMst.postId from OrgPostDetailsRlt pd where pd.cmnLocationMst.locId = '" + deptId
				+ "' )) and " + "  hmst.empId in (select slip.hrEisEmpMst.empId from HrPayPayslip slip) ");
		if (!empName.equalsIgnoreCase("")) {
			sb.append(" and (lower(hmst.orgEmpMst.empFname) like lower('" + empName);
			sb.append("%') or lower(hmst.orgEmpMst.empMname) like lower('" + empName);
			sb.append("%') or lower(hmst.orgEmpMst.empLname) like lower('" + empName + "%'))");
		}
		Query crit = hibSession.createQuery(sb.toString());
		list = crit.list();
		return list;

	}

	/*
	 * public List getAllEmpDatabyDept(long empId,String locationCode,long langId) {
	 * List empList=new ArrayList(); Session hibSession = getSession();
	 * 
	 * String queryString = " from  HrEisEmpMst e where  ";
	 * if(!locationCode.equals(""))//location specific { queryString+
	 * =" e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag =  1 and "
	 * ; queryString+
	 * ="pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='"
	 * +locationCode+"' and c.cmnLanguageMst.langId="+langId+"))";
	 * 
	 * query1+
	 * ="  e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in "
	 * ; query1+
	 * =" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = "
	 * +userId+" ) ) "; }
	 * 
	 * queryString+
	 * =" order by e.orgEmpMst.empFname,e.orgEmpMst.empMname,e.orgEmpMst.empLname ";
	 * Query sqlQuery = hibSession.createQuery(queryString);
	 * 
	 * empList = sqlQuery.list(); return empList; }
	 */

	public List getAllOrgDatabyDept(long locationId) {
		List empList = new ArrayList();
		Session hibSession = getSession();

		String queryString = " from  OrgEmpMst e where  ";

		if (locationId != 0)// location specific
		{
			/*
			 * query1+
			 * ="  e.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in "
			 * ; query1+
			 * =" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  ) ) "
			 * ;
			 */
			queryString += " e.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag = 1 and  ";
			queryString += "pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locId="
					+ locationId + " or c.parentLocId=" + locationId
					+ ")) and e.empId not in (select hrEmpMst.orgEmpMst.empId from HrEisEmpMst hrEmpMst)";

		}

		queryString += " order by e.empFname,e.empMname,e.empLname ";
		Query sqlQuery = hibSession.createQuery(queryString);

		empList = sqlQuery.list();
		return empList;
	}

	public List getAllOrgDatabyDept(String locationCode, long langId) {
		List empList = new ArrayList();
		Session hibSession = getSession();

		String queryString = " from  OrgEmpMst e where  ";

		if (langId != 0) {
			queryString += " e.cmnLanguageMst.langId = 1 and ";
		}

		if (!locationCode.equals(""))// location specific
		{
			/*
			 * query1+
			 * ="  e.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in "
			 * ; query1+
			 * =" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  ) ) "
			 * ;
			 */
			queryString += " e.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag = 1 and  ";
			queryString += "pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='"
					+ locationCode + "' and c.cmnLanguageMst.langId=" + langId
					+ ")) and e.empId not in (select hrEmpMst.orgEmpMst.empId from HrEisEmpMst hrEmpMst)";

		}

		queryString += " order by e.empFname,e.empMname,e.empLname ";
		Query sqlQuery = hibSession.createQuery(queryString);

		empList = sqlQuery.list();
		return empList;
	}

	public List getAllPostFromUser(OrgUserMst orgUserMst, long activateFlag) {
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(OrgUserpostRlt.class);

		objCrt.add(Restrictions.eq("orgUserMst", orgUserMst));
		if (activateFlag != -1)
			objCrt.add(Restrictions.eq("activateFlag", activateFlag));
		else
			objCrt.addOrder(Order.asc("endDate"));

		return objCrt.list();
	}

	// for getting the list of emp of particular department
	public List getHrEmpFromDeptId(long deptId, String empName, String month, String year) {
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer sb = new StringBuffer();

		sb.append(" from HrEisEmpMst hmst where hmst.orgEmpMst.orgUserMst.userId in ");
		sb.append(
				" ( select distinct(up.orgUserMst.userId) from OrgUserpostRlt up where up.orgPostMstByPostId.postId in ");
		sb.append(" ( select pd.orgPostMst.postId from OrgPostDetailsRlt pd where pd.cmnLocationMst.locId = '" + deptId
				+ "' )) and ");

		if (month.equals("") || month.equals("-1") || year.equals("") || year.equals("-1"))
			sb.append("   hmst.empId in (select slip.hrEisEmpMst.empId from HrPayPayslip slip  ) ");
		else
			sb.append("   hmst.empId in (select slip.hrEisEmpMst.empId from HrPayPayslip slip where slip.month=" + month
					+ " and slip.year=" + year + " ) ");
		if (!empName.equalsIgnoreCase("")) {
			sb.append(" and (lower(hmst.orgEmpMst.empFname) like lower('" + empName);
			sb.append("%') or lower(hmst.orgEmpMst.empMname) like lower('" + empName);
			sb.append("%') or lower(hmst.orgEmpMst.empLname) like lower('" + empName + "%'))");
		}

		Query crit = hibSession.createQuery(sb.toString());
		list = crit.list();
		return list;

	}

	public List getHrEmpFromDeptId(long deptId, String empName, String month, String year, long gradeId,
			String isPayBillGenChk) {
		List list = new ArrayList();
		Session hibSession = getSession();

		StringBuffer sb = new StringBuffer();

		sb.append(" from HrEisEmpMst hmst where hmst.orgEmpMst.orgUserMst.userId in ");
		sb.append(
				" ( select distinct(up.orgUserMst.userId) from OrgUserpostRlt up where up.orgPostMstByPostId.postId in ");
		sb.append(" ( select pd.orgPostMst.postId from OrgPostDetailsRlt pd where pd.cmnLocationMst.locId = '" + deptId
				+ "' ))  ");

		if (gradeId > 0) {
			sb.append(" and hmst.orgEmpMst.orgGradeMst.gradeId = '" + gradeId + "'  ");
		}

		if (isPayBillGenChk != null && !isPayBillGenChk.equals("")) {
			if (month.equals("") || month.equals("-1") || year.equals("") || year.equals("-1"))
				sb.append("  and  hmst.empId in (select slip.hrEisEmpMst.empId from HrPayPayslip slip  ) ");
			else
				sb.append("  and  hmst.empId in (select slip.hrEisEmpMst.empId from HrPayPayslip slip where slip.month="
						+ month + " and slip.year=" + year + " ) ");
		}
		if (!empName.equalsIgnoreCase("")) {
			sb.append(" and (lower(hmst.orgEmpMst.empFname) like lower('" + empName);
			sb.append("%') or lower(hmst.orgEmpMst.empMname) like lower('" + empName);
			sb.append("%') or lower(hmst.orgEmpMst.empLname) like lower('" + empName + "%'))");
		}

		Query crit = hibSession.createQuery(sb.toString());
		list = crit.list();
		return list;

	}

	public List getAllEmpDataFromHst(long empId, long locationId, int month, int year) {
		List empList = new ArrayList();
		Session hibSession = getSession();

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, year);
		cal2.set(Calendar.MONTH, month);

		int maxDays = cal2.getActualMaximum(5);

		cal2.set(Calendar.DATE, maxDays);

		java.util.Date currDate1 = cal2.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

		String currDate = sdf.format(currDate1);

		logger.info("the date is " + currDate);

		String queryString = " from  HrEisEmpMstHst e   ";
		queryString += " where e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId  and ";
		queryString += "pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locId=" + locationId
				+ " or c.parentLocId=" + locationId + "))";
		/*
		 * query1+
		 * ="  e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in "
		 * ; query1+
		 * =" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = "
		 * +userId+" ) ) ";
		 */
		queryString += " and e.updatedDate <= '" + currDate + "' and e.id.empId = " + empId;
		queryString += " order by e.updatedDate desc ";
		Query sqlQuery = hibSession.createQuery(queryString);

		empList = sqlQuery.list();
		if (empList == null || empList.size() <= 0) {
			String queryString1 = " from  HrEisEmpMstHst e   ";
			queryString1 += " where e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId  and ";
			queryString1 += "pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locId="
					+ locationId + " or c.parentLocId=" + locationId + "))";
			queryString1 += " and e.id.empId = " + empId;
			queryString1 += " order by e.updatedDate asc ";
			Query sqlQuery1 = hibSession.createQuery(queryString1);

			empList = sqlQuery1.list();
		}
		return empList;
	}

	public List getDDOListFromDeptId(long deptId, long langId) {
		List lListReturn = new ArrayList();

		String query = "";

		try {

			Session hibSession = getSession();

			query = "select ddo.ddoId,emp.empPrefix,emp.empFname,emp.empMname,emp.empLname from OrgEmpMst emp,OrgDdoMst ddo,OrgPostDetailsRlt pd,OrgUserpostRlt up ";
			// query="select e.emp_id,e.emp_prefix,e.emp_fname,e.emp_mname,e.emp_lname ";
			// query+=" from org_ddo_mst dd, org_post_details_rlt pd1,org_userpost_rlt
			// up,org_emp_mst e";
			query += " where emp.orgUserMst.userId = up.orgUserMst.userId and pd.cmnLocationMst.locId = " + deptId;
			query += " and pd.cmnLanguageMst.langId = " + langId + " and ddo.postId = up.orgPostMstByPostId.postId ";
			query += " and up.orgPostMstByPostId.postId = pd.orgPostMst.postId  and emp.cmnLanguageMst.langId = "
					+ langId;

			Query result = hibSession.createQuery(query);
			lListReturn = result.list();

		} catch (Exception e) {
			logger.error("Error in getDDOListFromDeptId and Error is : " + e, e);
		}

		return lListReturn;
	}

	public List getDDOInfoByDdoId(Long lLngDdoId, Long lLngLangId) {
		OrgDdoMst orgDdoMst = new OrgDdoMst();
		List list = new ArrayList();

		try {
			StringBuilder lSBQuery = new StringBuilder();

			Session hibSession = getSession();

			lSBQuery.append("select A.postId FROM OrgDdoMst A WHERE A.ddoId = " + lLngDdoId);
			lSBQuery.append(" AND A.langId = " + lLngLangId);

			Query lQuery = hibSession.createQuery(lSBQuery.toString());

			logger.info("Query for getDDOInfoByPost : " + lQuery.toString());
			logger.info("And Parameter is " + lLngDdoId + " " + lLngLangId);
			list = lQuery.list();
		} catch (Exception e) {
			logger.error("Error in getDDOInfoByPost and Error is : " + e, e);
		}

		return list;
	}

	/**
	 * Date :- 23-12-2008 Author:- Urvin Shah Purpose:-This method fetch the empId
	 * form the hr_eis_emp_mst from the empId of the org_emp_mst.
	 */
	public long getEmpIdByOrgEmpId(long empId) {
		long employeeId = 0;
		String strQuery;
		Session hibSession = getSession();
		strQuery = "select hrEisEmpMst.empId from HrEisEmpMst hrEisEmpMst where hrEisEmpMst.orgEmpMst.empId=" + empId;
		Query lQuery = hibSession.createQuery(strQuery);
		if (lQuery.uniqueResult().toString() != null)
			employeeId = Long.parseLong(lQuery.uniqueResult().toString());

		return employeeId;
	}

	/**
	 * Date :- 23-02-2009 Author:- Urvin Shah Purpose:- This method will fetch all
	 * the employees which are going to confirm in the current/selected month and
	 * return the List.
	 */

	public List getConfirmationData(String locationCode, Date startDate, Date endDate) {
		Criteria objCrt = null;
		List lstConfirmEmp = new ArrayList();
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class);
		objCrt.add(Restrictions.eq("cmnLocationMst.locationCode", locationCode));
		objCrt.add(Restrictions.between("nxtIncrDate", startDate, endDate));
		lstConfirmEmp = objCrt.list();
		return lstConfirmEmp;
	}

	public List getEmpByLocCodeGradeCodeAndDsgCode(String locationCode, String gradeCode, String dsgnCode) {
		List lstEmployee;
		String strQuery;
		Session hibSession;
		StringBuffer sbQuery = new StringBuffer();
		hibSession = getSession();
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		long activateFlag = Long.parseLong(rb.getString("activate"));
		logger.info("The Value of the Activate Flag is:-" + activateFlag);
		sbQuery.append(" from HrEisEmpMst empMst where empMst.cmnLocationMst.locationCode='" + locationCode
				+ "' and empMst.orgEmpMst.orgUserMst.userId in (");
		sbQuery.append("select up.orgUserMst.userId from OrgUserpostRlt up where up.activateFlag=" + activateFlag
				+ " and up.orgPostMstByPostId.locationCode='" + locationCode + "')");
		logger.info("The Quesry String is:-" + sbQuery.toString());
		Query objQuery = hibSession.createQuery(sbQuery.toString());
		lstEmployee = objQuery.list();

		return lstEmployee;
	}

	// added by praveen
	public List getEmpByDsgCode(String locationCode, long dsgnCode) {
		List lstEmployee;
		String strQuery;
		Session hibSession;
		StringBuffer sbQuery = new StringBuffer();
		hibSession = getSession();
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		long activateFlag = Long.parseLong(rb.getString("activate"));
		logger.info("The Value of the Activate Flag is:-" + activateFlag);
		// sbQuery.append(" from HrEisEmpMst empMst where
		// empMst.orgEmpMst.orgUserMst.userId in (");
		// sbQuery.append("select up.orgUserMst.userId from OrgUserpostRlt up where
		// up.activateFlag="+activateFlag+" )");
		sbQuery.append(
				" select emp.empId,emp.empFname,emp.empMname,emp.empLname from OrgEmpMst emp where emp.orgUserMst.userId in(select up.orgUserMst.userId from OrgUserpostRlt up where up.orgPostMstByPostId in (select p.postId from OrgPostMst p where p.locationCode='"
						+ locationCode + "' and p.dsgnCode=" + dsgnCode
						+ ")) and emp.empId in(select e.orgEmpMst.empId from HrEisEmpMst  e where e.cmnLocationMst.locId='"
						+ locationCode + "') ");
		logger.info("The Quesry String is:-" + sbQuery.toString());
		Query objQuery = hibSession.createQuery(sbQuery.toString());
		lstEmployee = objQuery.list();
		logger.info("size of emplist" + lstEmployee.size());

		return lstEmployee;
	}

	public List getEmpByBillNo(String locationCode, long langId, long billNo, long loanAdvId) {
		List lstEmployee;
		String strQuery;
		Session hibSession;
		StringBuffer sbQuery = new StringBuffer();
		hibSession = getSession();
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		long activateFlag = Long.parseLong(rb.getString("activate"));
		logger.info("The Value of the Activate Flag is:-" + activateFlag);
		// sbQuery.append(" from HrEisEmpMst empMst where
		// empMst.orgEmpMst.orgUserMst.userId in (");
		// sbQuery.append("select up.orgUserMst.userId from OrgUserpostRlt up where
		// up.activateFlag="+activateFlag+" )");
		// sbQuery.append(" select emp.empId,emp.empFname,emp.empMname,emp.empLname from
		// OrgEmpMst emp where emp.orgUserMst.userId in(select up.orgUserMst.userId from
		// OrgUserpostRlt up where up.orgPostMstByPostId in (select p.postId from
		// OrgPostMst p where p.locationCode='"+locationCode+"' and
		// p.dsgnCode="+dsgnCode+")) and emp.empId in(select e.orgEmpMst.empId from
		// HrEisEmpMst e where e.cmnLocationMst.locId='"+locationCode+"') ");
		sbQuery.append(" select e.empId, e.empFname, e.empMname, e.empLname ");
		sbQuery.append(" from OrgEmpMst e ");
		sbQuery.append(
				" where e.cmnLanguageMst.langId = '" + langId + "' and e.activateFlag = " + activateFlag + " and ");
		sbQuery.append("e.empId in ");
		sbQuery.append("(select emp.orgEmpMst.empId ");
		sbQuery.append(" from HrEisEmpMst emp ");
		sbQuery.append(" where emp.cmnLocationMst.locId = '" + locationCode + "' and ");
		/*
		 * sbQuery.append(" emp.empId in ");
		 * sbQuery.append(" (select oth.hrEisEmpMst.empId ");
		 * sbQuery.append(" from HrEisOtherDtls oth ");
		 * sbQuery.append(" where oth.hrEisSgdMpg.sgdMapId in ");
		 * sbQuery.append(" (select sgd.sgdMapId ");
		 * sbQuery.append(" from HrEisSgdMpg sgd ");
		 * sbQuery.append(" where sgd.hrEisScaleMst.scaleId in ");
		 * sbQuery.append("(select sm.scaleId ");
		 * sbQuery.append(" from HrEisScaleMst sm "); sbQuery.append(" where  ");
		 * sbQuery.append(" sm.cmnLocationMst.locId = '"+locationCode+"')))and ");
		 */
		sbQuery.append(
				" emp.empId not in (select loan.hrEisEmpMst.empId from HrLoanEmpDtls loan where loan.hrLoanAdvMst.loanAdvId = "
						+ loanAdvId + " and loan.loanActivateFlag = 1)) and ");
		sbQuery.append(" e.orgUserMst.userId in ");
		sbQuery.append(" (select up.orgUserMst.userId ");
		sbQuery.append(" from OrgUserpostRlt up ");
		sbQuery.append(" where up.activateFlag = " + activateFlag + " and ");
		sbQuery.append(" up.orgPostMstByPostId.postId in ");
		sbQuery.append(" (select psr.postId ");
		sbQuery.append(" from HrPayPsrPostMpg psr ");
		sbQuery.append(" where psr.billNo = " + billNo + "  and psr.loc_id = '" + locationCode + "')) ");

		logger.info("The Quesry String is:-" + sbQuery.toString());
		Query objQuery = hibSession.createQuery(sbQuery.toString());
		lstEmployee = objQuery.list();
		logger.info("size of emplist" + lstEmployee.size());

		return lstEmployee;
	}

// 	added by praveen
	/*
	 * public List getHrEmpFromOrgEmp(long empId,long landId,String locationCode ) {
	 * HrEisEmpMst hrEisEmpMst = new HrEisEmpMst(); List list = new ArrayList();
	 * Session hibSession = getSession();
	 * 
	 * String queryString =
	 * "select e.empId,e.orgEmpMst.empPrefix||e.orgEmpMst.empFname||e.orgEmpMst.empMname||e.orgEmpMst.empLname,e.empType from  HrEisEmpMst e "
	 * ; queryString+=" where e.empId="
	 * +empId+" and e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag =  1 and "
	 * ; queryString+
	 * =" pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='"
	 * +locationCode+"' and c.cmnLanguageMst.langId="+landId+"))"; queryString+
	 * =" order by e.orgEmpMst.empFname||e.orgEmpMst.empMname||e.orgEmpMst.empLname "
	 * ;
	 * 
	 * Query sqlQuery = hibSession.createQuery(queryString); list= sqlQuery.list();
	 * return list; } public List getAllEmpDatabyDept(String locationCode,long
	 * langId) { HrEisEmpMst hrEisEmpMst = new HrEisEmpMst(); List list = new
	 * ArrayList(); Session hibSession = getSession();
	 * 
	 * String queryString =
	 * "select e.empId,e.orgEmpMst.empPrefix|| ' ' || e.orgEmpMst.empFname|| ' ' || e.orgEmpMst.empMname|| ' ' || e.orgEmpMst.empLname,e.empType from  HrEisEmpMst e "
	 * ; queryString+
	 * =" where e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag =  1 and "
	 * ; queryString+
	 * =" pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='"
	 * +locationCode+"' and c.cmnLanguageMst.langId="+langId+"))"; queryString+
	 * =" order by e.orgEmpMst.empFname||e.orgEmpMst.empMname||e.orgEmpMst.empLname "
	 * ;
	 * 
	 * Query sqlQuery = hibSession.createQuery(queryString); list= sqlQuery.list();
	 * return list; }
	 */
	// added by javed
	public List getAllEmpDatabyDept(String locationCode, long langId) {
		// List empList=new ArrayList();
		Session hibSession = getSession();
		hibSession.flush();
		// 11 jan 2012
		// String queryString = "select e.empId,e.orgEmpMst.empPrefix||'
		// '||e.orgEmpMst.empFname||' '||e.orgEmpMst.empMname||'
		// '||e.orgEmpMst.empLname,e.empType from HrEisEmpMst e ";
		String queryString = "select e.empId,concat(e.orgEmpMst.empPrefix,' ',coalesce(e.orgEmpMst.empLname,' '),' ',coalesce(e.orgEmpMst.empFname,' '),' ',coalesce(e.orgEmpMst.empMname,' ')),e.empType ,case WHEN mst.dcpsOrGpf ='Y' THEN 'DCPS' WHEN mst.dcpsOrGpf ='N' THEN 'GPF' END  ";
		queryString = String.valueOf(queryString)
				+ " from  HrEisEmpMst e,OrgUserpostRlt up,OrgPostDetailsRlt pd, MstEmp mst";
		queryString = String.valueOf(queryString)
				+ " where e.orgEmpMst.orgUserMst.userId=up.orgUserMst.userId and pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag =  1 and mst.sevarthId= e.sevarthEmpCode and ";
		queryString += " pd.cmnLocationMst.locId =" + locationCode;
		queryString += " order by e.orgEmpMst.empLname||e.orgEmpMst.empFname||e.orgEmpMst.empMname ";

		Query sqlQuery = hibSession.createQuery(queryString);
		logger.info("====> getAllEmpDatabyDept sqlQuery :: " + sqlQuery);

		//empList = sqlQuery.list();
		List empList = sqlQuery.list();
		return empList;
	}

	public List getHrEmpFromOrgEmp(long empId, long landId, String locationCode) {
		HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
		List list = new ArrayList();
		Session hibSession = getSession();

		logger.info("getHrEmpFromOrgEmp*****************");

		String queryString = "select e.empId,concat(e.orgEmpMst.empPrefix,' ',e.orgEmpMst.empFname,' ',e.orgEmpMst.empLname),e.empType,mstdcps.dcpsOrGpf from  HrEisEmpMst e,MstEmp mstdcps";
		queryString += " where e.orgEmpMst.empId=" + empId 
				+ " and  mstdcps.orgEmpMstId = e.orgEmpMst and e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag =  1 and ";
		queryString += " pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='"
				+ locationCode + "' and c.cmnLanguageMst.langId=" + landId + "))";
		queryString += " order by e.orgEmpMst.empFname||e.orgEmpMst.empMname||e.orgEmpMst.empLname ";

		Query sqlQuery = hibSession.createQuery(queryString);
		list = sqlQuery.list();
		return list;
	}

	// ended

	public OrgUserpostRlt getuserpostrtlfromEmpId(long empId) {
		List list = null;
		Session hibSession = getSession();
		logger.info("in getuserpostrtlfromEmpId ...");

		String strQuery = "select uprlt from OrgUserpostRlt uprlt,OrgEmpMst empmst,HrEisEmpMst eMst where uprlt.activateFlag=1 and "
				+ "uprlt.orgUserMst.userId=empmst.orgUserMst.userId and eMst.orgEmpMst.empId=empmst.empId and eMst.empId="
				+ empId;
		Query query = hibSession.createQuery(strQuery);

		logger.info(" getuserpostrtlfromEmpId queryqueryquery  " + query);
		list = query.list();
		OrgUserpostRlt orgUserpostRlt = null;

		if (list != null && list.size() > 0) {
			orgUserpostRlt = (OrgUserpostRlt) query.uniqueResult();
		}

		return orgUserpostRlt;

	}

	public String getDesigNameFromEmpId(String empId) {
		String DesigName = "";
		Session hibsession = getSession();

		String HQL_QUERY = "select det.orgDesignationMst.dsgnName from OrgUserpostRlt up, OrgPostDetailsRlt det,HrEisEmpMst eisEmp where eisEmp.empId="
				+ empId + ""
				+ " and up.orgUserMst.userId = eisEmp.orgEmpMst.orgUserMst.userId and det.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1";

		Query lQuery = hibsession.createQuery(HQL_QUERY);
		if (lQuery.uniqueResult().toString() != null)
			DesigName = lQuery.uniqueResult().toString();
		logger.info("===> getDesigName lQuery :: " + lQuery);
		logger.info("===> in getDesigName :: " + DesigName);
		return DesigName;

	}

	public String getPostNameFromEmpId(String empId) {
		String DesigName = "";
		Session hibsession = getSession();

		String HQL_QUERY = "select det.postShortName from OrgEmpMst emp,HrEisEmpMst emst, OrgUserpostRlt up, OrgPostDetailsRlt det where emst.empId="
				+ empId + ""
				+ " and emst.orgEmpMst.empId=emp.empId and up.orgUserMst.userId = emp.orgUserMst.userId and det.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1";

		Query lQuery = hibsession.createQuery(HQL_QUERY);
		if (lQuery.uniqueResult().toString() != null)
			DesigName = lQuery.uniqueResult().toString();
		logger.info("===> getDesigName lQuery :: " + lQuery);
		logger.info("===> in getDesigName :: " + DesigName);
		return DesigName;

	}

}
