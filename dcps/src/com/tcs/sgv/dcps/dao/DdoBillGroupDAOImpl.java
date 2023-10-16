/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 28, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAOImpl;


/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 28, 2011
 */
public class DdoBillGroupDAOImpl extends GenericDaoHibernateImpl implements DdoBillGroupDAO {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger.getLogger(TrnPnsnProcInwardPensionDAOImpl.class);

	public DdoBillGroupDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	public List getSchemesforDDOComboVO(String lStrDDOCode) {

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		String query = "select distinct mst.schemeCode,mst.schemeName FROM RltDcpsDdoScheme rlt,MstScheme mst where mst.schemeCode=rlt.dcpsSchemeCode and rlt.dcpsDdoCode = :ddoCode order by mst.schemeCode,mst.schemeName ";

		// String lquery = "select schemeCode,schemeName from MstDcpsSchemes";

		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("ddoCode", lStrDDOCode);

		List resultList = selectQuery.list();

		cmbVO = new ComboValuesVO();

		if (resultList != null && resultList.size() > 0) {
			cmbVO = new ComboValuesVO();
			cmbVO.setId("-1");
			cmbVO.setDesc("-- Select --");
			finalList.add(cmbVO);
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) it.next();
				cmbVO.setId(obj[0].toString());
				cmbVO.setDesc(obj[1].toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}

	/*public List getSavedBillGroups(String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("SELECT dcpsDdoBillGroupId, dcpsDdoBillDescription,subBGOrNot,dcpsDdoSchemeCode FROM MstDcpsBillGroup ");
		lSBQuery.append(" WHERE dcpsDdoCode = :ddoCode group by dcpsDdoBillGroupId, dcpsDdoBillDescription,subBGOrNot,dcpsDdoSchemeCode order by dcpsDdoSchemeCode,subBGOrNot,dcpsDdoBillDescription,dcpsDdoBillGroupId desc");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);

		List lLstBillGroup = lQuery.list();

		return lLstBillGroup;
	}*/
	
//added by vaibhav tyagi: start
	
	public List getSavedBillGroups(String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("SELECT dcpsDdoBillGroupId, dcpsDdoBillDescription,subBGOrNot,dcpsDdoSchemeCode FROM MstDcpsBillGroup ");
		lSBQuery.append(" WHERE dcpsDdoCode = :ddoCode and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y')   ");
		// Added later not to take deleted and dcps bills
		lSBQuery.append(" group by dcpsDdoBillGroupId, dcpsDdoBillDescription,subBGOrNot,dcpsDdoSchemeCode ");
		lSBQuery.append(" order by dcpsDdoSchemeCode,subBGOrNot,dcpsDdoBillDescription,dcpsDdoBillGroupId desc");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);

		List lLstBillGroup = lQuery.list();

		return lLstBillGroup;
	}
	
	//added by vaibhav tyagi: end

	public List<MstEmp> getEmpListForBGIdNotNull(String lStrDDOCode) throws Exception {

		List<MstEmp> empList = null;

		try {

			String query = "from MstEmp where ddoCode= :DDOCode and billGroupId is not null";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);
			empList = stQuery.list();

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return empList;
	}

	public MstDcpsBillGroup getBillGroupDtlsForBillGroupId(Long lLongBillGroupId) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" FROM MstDcpsBillGroup where dcpsDdoBillGroupId = :dcpsDdoBillGroupId");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsDdoBillGroupId", lLongBillGroupId);

		MstDcpsBillGroup lObjMstDcpsBillGroup = (MstDcpsBillGroup) lQuery.uniqueResult();

		return lObjMstDcpsBillGroup;
	}

	public List<MstEmp> getShowGroupList(String lStrDDOCode) throws Exception {

		List showgroupList = null;
		try {

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(" from MstEmp where billGroupId is not null and ddoCode= :DDOCode ");
			logger.info("DDO CODE in dao: "+lStrDDOCode);
			Session session = getSession();
			Query stQuery = session.createQuery(SBQuery.toString());
			//Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);
			showgroupList = stQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			throw (e);
		}
		return showgroupList;
	}

	public List getBillGroups(String lStrDDOCode) throws Exception {

		String query = "select dcpsDdoBillGroupId,dcpsDdoBillDescription from MstDcpsBillGroup where dcpsDdoCode = :dcpsDdoCode";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("dcpsDdoCode", lStrDDOCode);

		List lLstResult = selectQuery.list();
		ComboValuesVO lObjComboValuesVO = null;
		if (lLstResult != null && lLstResult.size() != 0) {
			lLstReturnList = new ArrayList<Object>();
			Object obj[];
			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());
				lObjComboValuesVO.setDesc(obj[1].toString());
				lLstReturnList.add(lObjComboValuesVO);
			}
		} else {
			lLstReturnList = new ArrayList<Object>();
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("--Select--");
			lLstReturnList.add(lObjComboValuesVO);
		}

		return lLstReturnList;
	}
	
	/*public List getBillGroupsWithSchemeCode(String lStrDDOCode) throws Exception {

		String query = "select dcpsDdoSchemeCode,dcpsDdoBillGroupId,dcpsDdoBillDescription from MstDcpsBillGroup where dcpsDdoCode = :dcpsDdoCode";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("dcpsDdoCode", lStrDDOCode);

		List lLstResult = selectQuery.list();
		

		return lLstResult;
	}*/
	//added by vaibhav tyagi : start
	public List getBillGroupsWithSchemeCode(String lStrDDOCode) throws Exception {

		String query = "select dcpsDdoSchemeCode,dcpsDdoBillGroupId,dcpsDdoBillDescription from MstDcpsBillGroup where dcpsDdoCode = :dcpsDdoCode and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') ";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("dcpsDdoCode", lStrDDOCode);

		List lLstResult = selectQuery.list();


		return lLstResult;
	}
	//added by vaibhav tyagi: end
	

	public List getDDOOffices(String lStrDDOCode) throws Exception {

		String query = "select dcpsDdoOfficeIdPk,dcpsDdoOfficeName from DdoOffice where dcpsDdoCode = :dcpsDdoCode";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("dcpsDdoCode", lStrDDOCode);

		List lLstResult = selectQuery.list();
		ComboValuesVO lObjComboValuesVO = null;
		if (lLstResult != null && lLstResult.size() != 0) {
			lLstReturnList = new ArrayList<Object>();
			Object obj[];
			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());
				lObjComboValuesVO.setDesc(obj[1].toString());
				lLstReturnList.add(lObjComboValuesVO);
			}
		} else {
			lLstReturnList = new ArrayList<Object>();
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("--Select--");
			lLstReturnList.add(lObjComboValuesVO);
		}

		return lLstReturnList;
	}
	
	// Old getEmpListCount

	/*
	public Integer getEmpListCount(String lStrDDOCode, Map displayTag) throws Exception {

		Integer count;

		try {

			String query = "select count(*) from MstEmp where ddoCode= :DDOCode and billGroupId is null and regStatus in (1,2)";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);
			count = Integer.parseInt(stQuery.list().get(0).toString());

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return count;
	}
	*/
	
	public Integer getEmpListCount(String lStrDDOCode, Map displayTag) throws Exception {

		Integer count;

		try {

			StringBuilder SBQuery = new StringBuilder();
			
			SBQuery.append(" select count(EM.dcpsEmpId) from MstEmp EM , OrgEmpMst OM, OrgUserpostRlt OP ");
			SBQuery.append(" where EM.ddoCode= :DDOCode and EM.billGroupId is null and EM.regStatus in (1,2)");
			SBQuery.append(" and EM.orgEmpMstId = OM.empId");
			SBQuery.append(" and OP.orgUserMst.userId = OM.orgUserMst.userId");
			SBQuery.append(" and OP.activateFlag = 1");
			
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);
			count = Integer.parseInt(stQuery.list().get(0).toString());

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return count;
	}
	
	public Integer getTotalSubBGsForScheme(String lStrDDOCode, String lStrSchemeCode) throws Exception {

		Integer count;

		try {

			String query = "select count(*) from MstDcpsBillGroup where dcpsDdoCode= :DDOCode and dcpsDdoSchemeCode = :SchemeCode and subBGOrNot = 1 and (billDeleted is null or billDeleted <> 'Y')"; 

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);
			stQuery.setParameter("SchemeCode", lStrSchemeCode);
			count = Integer.parseInt(stQuery.list().get(0).toString());

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return count;
	}
	
	/*public Integer getTotalEmployeesForBillGroup(Long lLongBillGroupId) throws Exception {

		Integer count;

		try {

			String query = "select count(*) from MstEmp where billGroupId = :billGroupId"; 

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("billGroupId", lLongBillGroupId);
			count = Integer.parseInt(stQuery.list().get(0).toString());

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return count;
	}*/
	
	//added by vaibhav tyagi : start
	public Integer getTotalEmployeesForBillGroup(Long lLongBillGroupId) throws Exception {

		Integer count = 0;

		try {

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(" select count(EM.DCPS_EMP_ID) from mst_dcps_emp EM ");
			SBQuery.append(" join org_emp_mst OE on EM.ORG_EMP_MST_ID = OE.EMP_ID");
			
			SBQuery.append(" join ORG_USERPOST_RLT OUP on OUP.USER_ID = OE.USER_ID and OUP.ACTIVATE_FLAG = 1");
			SBQuery.append(" and EM.REG_STATUS in (1,2)");
			SBQuery.append(" and EM.BILLGROUP_ID = :billGroupId ");
			
			Query stQuery = ghibSession.createSQLQuery(SBQuery.toString());
			stQuery.setParameter("billGroupId", lLongBillGroupId);
			count = Integer.parseInt(stQuery.list().get(0).toString());

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return count;
	}
	//added by vaibhav tyagi : end

	//Old getEmpListMethod getting Employees under no bill-group
	
	/*
	public List<MstEmp> getEmpList(String lStrDDOCode, Map displayTag) throws Exception {

		List<MstEmp> empList = new ArrayList<MstEmp>();

		try {

			String[] columnValues = new String[]{"", "dcpsEmpId", "dcpsId", "name", "designation", "payCommission", "payScale", "basicPay"};

			String query = "from MstEmp where ddoCode= :DDOCode and billGroupId is null and regStatus in (1,2) ";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);

			SBQuery.append(" ORDER BY ");

			String orderString = (displayTag.containsKey(Constants.KEY_SORT_ORDER) ? (String) displayTag.get(Constants.KEY_SORT_ORDER) : "asc");

			Integer orderbypara = null;

			if (displayTag.containsKey(Constants.KEY_SORT_PARA)) {
				orderbypara = (Integer) displayTag.get(Constants.KEY_SORT_PARA);
				SBQuery.append(columnValues[orderbypara.intValue()] + " " + orderString);
			} else {
				SBQuery.append(" name ASC");
			}

			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag.get(Constants.KEY_PAGE_NO) : 1);

			stQuery.setFirstResult((pageNo.intValue() - 1) * Constants.PDWL_PAGE_SIZE);
			stQuery.setMaxResults(Constants.PDWL_PAGE_SIZE);

			empList = stQuery.list();
			
			if(empList.size() == 0)
			{
				empList = null ;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return empList;
	}
	
	*/
	
	/*
	 	lSBQuery.append(" (SELECT OP.orgPostMstByPostId.postId from OrgUserpostRlt OP,OrgEmpMst OM, MstEmp EM where OP.orgUserMst.userId = OM.orgUserMst.userId ");
		lSBQuery.append(" and EM.orgEmpMstId = OM.empId and EM.dcpsEmpId = :dcpsEmpId and OP.activateFlag = 1 )"); 

	 */
	
	public List<MstEmp> getEmpList(String lStrDDOCode, Map displayTag) throws Exception {

		List<MstEmp> empList = new ArrayList<MstEmp>();

		try {

			String[] columnValues = new String[]{"", "EM.dcpsEmpId", "EM.dcpsId", "EM.name", "EM.designation", "EM.payCommission", "EM.payScale", "EM.basicPay"};

			//String query = "EM from MstEmp EM where EM.ddoCode= :DDOCode and EM.billGroupId is null and regStatus in (1,2) ";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(" select EM from MstEmp EM , OrgEmpMst OM, OrgUserpostRlt OP ");
			SBQuery.append(" where EM.ddoCode= :DDOCode and EM.billGroupId is null and EM.regStatus in (1,2)");
			SBQuery.append(" and EM.orgEmpMstId = OM.empId");
			SBQuery.append(" and OP.orgUserMst.userId = OM.orgUserMst.userId");
			SBQuery.append(" and OP.activateFlag = 1");
			

			SBQuery.append(" ORDER BY ");

			String orderString = (displayTag.containsKey(Constants.KEY_SORT_ORDER) ? (String) displayTag.get(Constants.KEY_SORT_ORDER) : "asc");

			Integer orderbypara = null;

			if (displayTag.containsKey(Constants.KEY_SORT_PARA)) {
				orderbypara = (Integer) displayTag.get(Constants.KEY_SORT_PARA);
				SBQuery.append(columnValues[orderbypara.intValue()] + " " + orderString);
			} else {
				SBQuery.append(" EM.name ASC");
			}

			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag.get(Constants.KEY_PAGE_NO) : 1);

			stQuery.setFirstResult((pageNo.intValue() - 1) * Constants.PDWL_PAGE_SIZE);
			stQuery.setMaxResults(Constants.PDWL_PAGE_SIZE);

			empList = stQuery.list();
			
			if(empList.size() == 0)
			{
				empList = null ;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return empList;
	}
	
	public List<MstEmp> getEmpListForBGIdNull(String lStrDDOCode, Map displayTag) throws Exception {

		List<MstEmp> empList = new ArrayList<MstEmp>();

		try {

			//String[] columnValues = new String[]{"", "EM.dcps_Emp_Id", "EM.dcps_Id", "EM.emp_name", "EM.designation", "EM.PAY_COMMISSION", "EM.PAYSCALE", "EM.BASIC_PAY"};
			
			String[] columnValues = new String[]{"EM.dcps_Emp_Id", "EM.dcps_Id", "EM.emp_name", "EM.designation"};
			
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(" select EM.dcps_emp_id,EM.EMP_NAME,nvl(OD.DSGN_NAME,'') from mst_dcps_emp EM");
			SBQuery.append(" join org_emp_mst OM on EM.ORG_EMP_MST_ID = OM.EMP_ID ");
			SBQuery.append(" join org_userpost_rlt OP on OP.USER_ID = OM.USER_ID ");
			SBQuery.append(" left join org_designation_mst OD on OD.DSGN_ID = EM.DESIGNATION ");
			SBQuery.append(" where EM.ddo_code= :DDOCode");
			SBQuery.append(" and EM.BILLGROUP_ID is null");
			SBQuery.append(" and EM.REG_STATUS in (1,2)");
			SBQuery.append(" and OP.ACTIVATE_FLAG = 1");
			
			SBQuery.append(" ORDER BY ");

			String orderString = (displayTag.containsKey(Constants.KEY_SORT_ORDER) ? (String) displayTag.get(Constants.KEY_SORT_ORDER) : "asc");

			Integer orderbypara = null;

			if (displayTag.containsKey(Constants.KEY_SORT_PARA)) {
				orderbypara = (Integer) displayTag.get(Constants.KEY_SORT_PARA);
				SBQuery.append(columnValues[orderbypara.intValue()] + " " + orderString);
			} else {
				SBQuery.append(" EM.emp_name ASC");
			}

			Query stQuery = ghibSession.createSQLQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag.get(Constants.KEY_PAGE_NO) : 1);

			//stQuery.setFirstResult((pageNo.intValue() - 1) * Constants.PDWL_PAGE_SIZE);
			//stQuery.setMaxResults(Constants.PDWL_PAGE_SIZE);
			//added by vaibhav tyagi :start
			stQuery.setFirstResult((pageNo.intValue() - 1) * Constants.PDWL_PAGE_SIZE_MAX);
			stQuery.setMaxResults(Constants.PDWL_PAGE_SIZE_MAX);
			//added by vaibhav tyagi :end
			empList = stQuery.list();
			
			if(empList.size() == 0)
			{
				empList = null ;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return empList;
	}
	

	public String getBillGroupDetailsForBillGroupId(Long lLongBillGroupId) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("select dcpsDdoBillDescription FROM MstDcpsBillGroup where dcpsDdoBillGroupId = :dcpsDdoBillGroupId");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsDdoBillGroupId", lLongBillGroupId);

		String lStrBillGroupDtls = lQuery.uniqueResult().toString();

		return lStrBillGroupDtls;
	}

	/*
	public List<MstEmp> getEmpListForGivenBillGroup(Long lLongBillGroupId, String lStrDDOCode) throws Exception {

		List empList = null;

		try {

			StringBuilder  SBQuery = new StringBuilder(); 
						
			SBQuery.append(" select EM from MstEmp EM , OrgEmpMst OM, OrgUserpostRlt OP ");
			SBQuery.append(" where EM.ddoCode= :DDOCode and EM.billGroupId= :billGroupId and EM.regStatus in (1,2)");
			SBQuery.append(" and EM.orgEmpMstId = OM.empId");
			SBQuery.append(" and OP.orgUserMst.userId = OM.orgUserMst.userId");
			SBQuery.append(" and OP.activateFlag = 1");
			
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("billGroupId", lLongBillGroupId);
			stQuery.setParameter("DDOCode", lStrDDOCode);
			
			logger.info("getEmpListForGivenBillGroup is*********"+stQuery.toString());
			empList = stQuery.list();
			if (empList.size() == 0) {
				empList = null;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return empList;
	}
	*/
	
	public List<MstEmp> getEmpListForGivenBillGroup(Long lLongBillGroupId, String lStrDDOCode) throws Exception {

		List empList = null;

		try {

			StringBuilder  SBQuery = new StringBuilder(); 
						
			SBQuery.append(" Select EM.dcps_emp_id,EM.EMP_NAME,nvl(OD.DSGN_NAME,'') from mst_dcps_emp EM");
			SBQuery.append(" join org_emp_mst OM on EM.ORG_EMP_MST_ID = OM.EMP_ID ");
			SBQuery.append(" join org_userpost_rlt OP on OP.USER_ID = OM.USER_ID ");
			SBQuery.append(" left join org_designation_mst OD on OD.DSGN_ID = EM.DESIGNATION ");
			SBQuery.append(" where EM.ddo_code = :DDOCode");
			SBQuery.append(" and EM.BILLGROUP_ID = :billGroupId");
			SBQuery.append(" and EM.REG_STATUS in (1,2)");
			SBQuery.append(" and OP.ACTIVATE_FLAG = 1");
			SBQuery.append(" order by EM.EMP_NAME");
			
			Query stQuery = ghibSession.createSQLQuery(SBQuery.toString());
			stQuery.setParameter("billGroupId", lLongBillGroupId);
			stQuery.setParameter("DDOCode", lStrDDOCode);
			
			empList = stQuery.list();
			if (empList.size() == 0) {
				empList = null;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return empList;
	}

	public Boolean checkGroupExistsOrNotForBG(Long lLongBillGroupId) throws Exception {

		List ClassGroupList = null;
		Boolean lBlGroupExistsOrNotFlag = false;
		try {

			String query = "from RltBillgroupClassgroup where dcpsBillGroupId= :billGroupId";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("billGroupId", lLongBillGroupId);
			ClassGroupList = stQuery.list();
			if (ClassGroupList.size() != 0) {
				lBlGroupExistsOrNotFlag = true;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return lBlGroupExistsOrNotFlag;
	}

	public void deleteClassGroupsForGivenBGId(Long lBillGroupId) {

		getSession();
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" delete from RltBillgroupClassgroup where dcpsBillGroupId = :dcpsBillGroupId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsBillGroupId", lBillGroupId);
		lQuery.executeUpdate();

	}

	public List getClassGroupsForGivenBGId(Long lBillGroupId) throws Exception {

		List ClassGroupList = null;

		try {

			String query = "select dcpsClassGroup from RltBillgroupClassgroup where dcpsBillGroupId = :dcpsBillGroupId";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("dcpsBillGroupId", lBillGroupId);
			ClassGroupList = stQuery.list();

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return ClassGroupList;
	}
	
	public void updateBillNoInPayroll (Long lLongEmpId,Long lLongbillGroupId,String lStrAttachOrDetach) {

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" Update HrPayPsrPostMpg HR SET HR.billNo = :billNo where HR.postId = ");
		lSBQuery.append(" (SELECT OP.orgPostMstByPostId.postId from OrgUserpostRlt OP,OrgEmpMst OM, MstEmp EM where OP.orgUserMst.userId = OM.orgUserMst.userId ");
		lSBQuery.append(" and EM.orgEmpMstId = OM.empId and EM.dcpsEmpId = :dcpsEmpId and OP.activateFlag = 1 )");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		
		if(lStrAttachOrDetach.equals("Attach"))
		{
			lQuery.setParameter("billNo", lLongbillGroupId);
		}
		else
		{
			lQuery.setParameter("billNo", null);
		}
		
		lQuery.setParameter("dcpsEmpId", lLongEmpId);
		lQuery.executeUpdate();
	}
	
	public Integer getPostListCount(String locationId, Map displayTag) throws Exception {

		Integer count;

		try {

			String query = "select count(*) from HrPayPsrPostMpg psr,OrgPostMst postMst where psr.postId = postMst.postId and";
			query += " psr.loc_id ='"+ locationId +"' and psr.billNo is null and postMst.activateFlag = 1 and postMst.locationCode ='"+ locationId+"'" ;
			query += " and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ";
			query += " and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='"+locationId+"')";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			count = Integer.parseInt(stQuery.list().get(0).toString());

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			//e.printStackTrace();
			throw (e);
		}
		return count;
	}
	
	
	public List<OrgPostDetailsRlt> getPostList(String locationId, Map displayTag) throws Exception {

		List<OrgPostDetailsRlt> postList = new ArrayList<OrgPostDetailsRlt>();

		try {

			String query = "select rlt from HrPayPsrPostMpg psr,OrgPostMst postMst,OrgPostDetailsRlt rlt where psr.postId = postMst.postId ";
			query += " and postMst.activateFlag = 1 and rlt.orgPostMst.postId = postMst.postId and psr.loc_id ='"+locationId+"'"+" and psr.billNo is null and postMst.locationCode ='"+locationId+"'";
			query += " and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ";
			query += " and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='"+locationId+"')";
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);

			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			
			postList = stQuery.list();
			
			if(postList.size() == 0)
			{
				postList = null ;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return postList;
	}
	
	public List<OrgPostDetailsRlt> getPostListForGivenBillGroup(Long lLongBillGroupId, String locationId) throws Exception {

		List postList = null;

		try {
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("select rlt from HrPayPsrPostMpg psr,OrgPostMst postMst,OrgPostDetailsRlt rlt where psr.postId = postMst.postId ");
			SBQuery.append(" and rlt.orgPostMst.postId = postMst.postId and psr.loc_id ='"+locationId+"'"+" and postMst.locationCode ='"+locationId+"'");
			SBQuery.append(" and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ");
			SBQuery.append(" and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='");
			SBQuery.append(locationId+"') and postMst.activateFlag = 1 and psr.billNo = ");
			SBQuery.append(lLongBillGroupId);

			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			logger.info("getPostListForGivenBillGroup query is**************"+stQuery.toString());
			postList = stQuery.list();
			if (postList.size() == 0) {
				postList = null;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return postList;
	}
	
	public void updateBillNoOfPostInPayroll (Long lLongPostId,Long lLongbillGroupId,String lStrAttachOrDetach) {

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" Update HrPayPsrPostMpg HR SET HR.billNo = :billNo where HR.postId = :postId");
		
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		
		if(lStrAttachOrDetach.equals("Attach"))
		{
			lQuery.setParameter("billNo", lLongbillGroupId);
		}
		else
		{
			lQuery.setParameter("billNo", null);
		}
		
		lQuery.setParameter("postId", lLongPostId);
		lQuery.executeUpdate();
	}


	//Added by Abhilash for Showing post types
	
	public List<OrgPostMst> getPostTypeListFromPostId(long postId) throws Exception {

		List posTypeList = null;

		try {
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("select postMst from OrgPostMst postMst where  postMst.postId ="+postId+" ");

			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			
			logger.info("In getPostTypeListFromPostId query is**************"+stQuery.toString());
			
			
			posTypeList = stQuery.list();
			
			logger.info("In getPostTypeListFromPostId posTypeList list size is**************"+posTypeList.size());
			if (posTypeList.size() == 0) {
				posTypeList = null;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return posTypeList;
	}
	
	/*
	public List getPostListGivenBillGroup(String locationId, Map displayTag) throws Exception 
	{

		List  postList = new ArrayList();

		try {

			String query = "select postMst.postId,rlt.postName from HrPayPsrPostMpg psr,OrgPostMst postMst,OrgPostDetailsRlt rlt where psr.postId = postMst.postId ";
			query += " and postMst.activateFlag = 1 and rlt.orgPostMst.postId = postMst.postId and psr.loc_id ='"+locationId+"'"+" and psr.billNo is null and postMst.locationCode ='"+locationId+"'";
			query += " and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ";
			query += " and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='"+locationId+"')";
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);

			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			
			postList = stQuery.list();
			
			if(postList.size() == 0)
			{
				postList = null ;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return postList;
	}
	*/
	
	/*public List getPostListForLocation(String locationId, Map displayTag) throws Exception 
	{

		List  postList = new ArrayList();

		try {

			String query = "select postMst.postId,rlt.postName from HrPayPsrPostMpg psr,OrgPostMst postMst,OrgPostDetailsRlt rlt where psr.postId = postMst.postId ";
			query += " and postMst.activateFlag = 1 and rlt.orgPostMst.postId = postMst.postId and psr.loc_id ='"+locationId+"'"+" and psr.billNo is null and postMst.locationCode ='"+locationId+"'";
			query += " and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ";
			query += " and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='"+locationId+"')";
			StringBuffer lSB = new StringBuffer();
			lSB.append("select rlt.POST_ID,rlt.POST_NAME,post.POST_TYPE_LOOKUP_ID from ORG_POST_DETAILS_RLT rlt inner join HR_PAY_POST_PSR_MPG psr on psr.POST_ID = rlt.POST_ID ");
			lSB.append(" inner join ORG_POST_MST post on post.post_id = rlt.post_id and post.location_code = rlt.loc_id ");
			lSB.append(" left outer join ORG_USERPOST_RLT user on user.POST_ID = psr.POST_ID and user.POST_ID = rlt.POST_ID and user.ACTIVATE_FLAG = 1 ");
			lSB.append(" where user.POST_ID is null and rlt.LOC_ID = psr.LOC_ID and rlt.LOC_ID = :locId and psr.bill_no is null");
			
			Query stQuery = ghibSession.createSQLQuery(lSB.toString());
			stQuery.setParameter("locId", locationId);
			postList = stQuery.list();
			
			if(postList.size() == 0)
			{
				postList = null ;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return postList;
	}
	*/
	//added by vaibhav tyagi: start
	public List getPostListForLocation(String locationId, Map displayTag) throws Exception 
	{

		List  postList = new ArrayList();

		try {

			/*String query = "select postMst.postId,rlt.postName from HrPayPsrPostMpg psr,OrgPostMst postMst,OrgPostDetailsRlt rlt where psr.postId = postMst.postId ";
			query += " and postMst.activateFlag = 1 and rlt.orgPostMst.postId = postMst.postId and psr.loc_id ='"+locationId+"'"+" and psr.billNo is null and postMst.locationCode ='"+locationId+"'";
			query += " and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ";
			query += " and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='"+locationId+"')";*/
			StringBuffer lSB = new StringBuffer();
			lSB.append("select rlt.POST_ID,rlt.POST_NAME,post.POST_TYPE_LOOKUP_ID from ORG_POST_DETAILS_RLT rlt inner join HR_PAY_POST_PSR_MPG psr on psr.POST_ID = rlt.POST_ID ");
			lSB.append(" inner join ORG_POST_MST post on post.post_id = rlt.post_id and post.location_code = rlt.loc_id ");
			lSB.append(" left outer join ORG_USERPOST_RLT user on user.POST_ID = psr.POST_ID and user.POST_ID = rlt.POST_ID and user.ACTIVATE_FLAG = 1 ");
			lSB.append(" where user.POST_ID is null and rlt.LOC_ID = psr.LOC_ID and rlt.LOC_ID = :locId and psr.bill_no is null");
			lSB.append(" and post.ACTIVATE_FLAG=1 and post.POST_TYPE_LOOKUP_ID in (10001198130,10001198129)");
			Query stQuery = ghibSession.createSQLQuery(lSB.toString());
			stQuery.setParameter("locId", locationId);
			logger.info(" queryyyy " + lSB.toString());
			logger.info("locationId " + locationId);
			postList = stQuery.list();
			
			if(postList.size() == 0)
			{
				postList = null ;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return postList;
	}
	//added by vaibhav tyagi: end
	/*
	public List getPostTypeListForGivenBillGroup(Long lLongBillGroupId, String locationId) throws Exception 
	{

		List postList = null;

		try {
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("select postMst.postId,rlt.postName,rlt.orgPostMst.postTypeLookupId from HrPayPsrPostMpg psr,OrgPostMst postMst,OrgPostDetailsRlt rlt where psr.postId = postMst.postId ");
			SBQuery.append(" and rlt.orgPostMst.postId = postMst.postId and psr.loc_id ='"+locationId+"'"+" and postMst.locationCode ='"+locationId+"'");
			SBQuery.append(" and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ");
			SBQuery.append(" and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='");
			SBQuery.append(locationId+"') and postMst.activateFlag = 1 and psr.billNo = ");
			SBQuery.append(lLongBillGroupId);

			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			logger.info("getPostTypeListForGivenBillGroup query is**************"+stQuery.toString());
			postList = stQuery.list();
			if (postList.size() == 0) {
				postList = null;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return postList;
	}
	*/
	
	/*public List getPostTypeListForGivenBillGroup(Long lLongBillGroupId, String locationId) throws Exception 
	{

		List postList = null;

		try {
			StringBuilder SBQuery = new StringBuilder();
			
			SBQuery.append("select postMst.postId,rlt.postName,rlt.orgPostMst.postTypeLookupId from HrPayPsrPostMpg psr,OrgPostMst postMst,OrgPostDetailsRlt rlt where psr.postId = postMst.postId ");
			SBQuery.append(" and rlt.orgPostMst.postId = postMst.postId and psr.loc_id ='"+locationId+"'"+" and postMst.locationCode ='"+locationId+"'");
			SBQuery.append(" and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ");
			SBQuery.append(" and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='");
			SBQuery.append(locationId+"') and postMst.activateFlag = 1 and psr.billNo = ");
			SBQuery.append(lLongBillGroupId);
			StringBuffer lSB = new StringBuffer();
			lSB.append("select rlt.POST_ID,rlt.POST_NAME,post.POST_TYPE_LOOKUP_ID from ORG_POST_DETAILS_RLT rlt inner join HR_PAY_POST_PSR_MPG psr on psr.POST_ID = rlt.POST_ID ");
			lSB.append(" inner join ORG_POST_MST post on post.post_id = rlt.post_id and post.location_code = rlt.loc_id ");
			lSB.append(" left outer join ORG_USERPOST_RLT user on user.POST_ID = psr.POST_ID and user.POST_ID = rlt.POST_ID and user.ACTIVATE_FLAG = 1 ");
			lSB.append(" where user.POST_ID is null and rlt.LOC_ID = psr.LOC_ID and psr.BILL_NO = :billNo ");
			
			Query stQuery = ghibSession.createSQLQuery(lSB.toString());
			logger.info("getPostTypeListForGivenBillGroup query is**************"+stQuery.toString());
			stQuery.setParameter("billNo", lLongBillGroupId);
			postList = stQuery.list();
			if (postList.size() == 0) {
				postList = null;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return postList;
	}*/
//added by vaibhav tyagi :start
	
	public List getPostTypeListForGivenBillGroup(Long lLongBillGroupId, String locationId) throws Exception 
	{

		List postList = null;

		try {
			StringBuilder SBQuery = new StringBuilder();
			/*SBQuery.append("select postMst.postId,rlt.postName,rlt.orgPostMst.postTypeLookupId from HrPayPsrPostMpg psr,OrgPostMst postMst,OrgPostDetailsRlt rlt where psr.postId = postMst.postId ");
			SBQuery.append(" and rlt.orgPostMst.postId = postMst.postId and psr.loc_id ='"+locationId+"'"+" and postMst.locationCode ='"+locationId+"'");
			SBQuery.append(" and postMst.postId not in (select userPost.orgPostMstByPostId.postId from OrgUserpostRlt userPost,OrgPostMst post where userPost.activateFlag = 1  ");
			SBQuery.append(" and userPost.orgPostMstByPostId.postId=post.postId and post.locationCode ='");
			SBQuery.append(locationId+"') and postMst.activateFlag = 1 and psr.billNo = ");
			SBQuery.append(lLongBillGroupId);*/
			StringBuffer lSB = new StringBuffer();
			lSB.append("select rlt.POST_ID,rlt.POST_NAME,post.POST_TYPE_LOOKUP_ID from ORG_POST_DETAILS_RLT rlt inner join HR_PAY_POST_PSR_MPG psr on psr.POST_ID = rlt.POST_ID ");
			lSB.append(" inner join ORG_POST_MST post on post.post_id = rlt.post_id and post.location_code = rlt.loc_id ");
			lSB.append(" left outer join ORG_USERPOST_RLT user on user.POST_ID = psr.POST_ID and user.POST_ID = rlt.POST_ID and user.ACTIVATE_FLAG = 1 ");
			lSB.append(" where user.POST_ID is null and rlt.LOC_ID = psr.LOC_ID and psr.BILL_NO = '"+lLongBillGroupId+"' ");
			lSB.append(" and post.ACTIVATE_FLAG=1 and post.POST_TYPE_LOOKUP_ID in (10001198130,10001198129)");
			Query stQuery = ghibSession.createSQLQuery(lSB.toString());
			logger.info("getPostTypeListForGivenBillGroup query is**************"+stQuery.toString());
			//stQuery.setParameter("billNo", lLongBillGroupId);
			if(stQuery.list()!=null && stQuery.list().size()>0 && stQuery.list().get(0)!=null)
			{
				postList = stQuery.list();
			}
			/*if (postList.size() == 0) {
				postList = null;
			}*/

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return postList;
	}
	
	//added by vaibhav tyagi :end
	public long getPostTypeId(long postId)
	{
		logger.info("coming into getPostTypeId method");
		List<OrgPostMst> postTypeList = new ArrayList();
		long postTypeId = 0;
		CmnLookupMst cmnLookupMst = null;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" select o.POST_TYPE_LOOKUP_ID");
		sb.append(" from org_post_mst o where o.post_id="+postId+" ");

		logger.info("The getPostTypeId Query String is:-"+sb.toString());
	    Query query = hibSession.createSQLQuery(sb.toString());
	    //postTypeList = query.list();
	    
	    
	    List resultList = query.list();
	    logger.info("getPostTypeId List size is:-"+resultList.size());
	    

		if (resultList != null && resultList.size() == 1)
			postTypeId = Long.parseLong(query.uniqueResult().toString());
		
	  /*  if(postTypeList!=null && !postTypeList.isEmpty())
	    	cmnLookupMst = postTypeList.get(0).getPostTypeLookupId();
	    if(cmnLookupMst!=null)
	    postTypeId = cmnLookupMst.getLookupId();*/
	    
		logger.info("postTypeId is***************"+postTypeId);
	    
	    logger.info("Parent Post List size is:-"+postTypeList.size());
		return postTypeId;
		
	}		
	
	
	public String getdsgnNameFromEmployeeId(long employeeId)
	{
		logger.info("coming into getdsgnNameFromEmployeeId method");
		List list = new ArrayList();
		
		String designationName= "";
		Session hibSession = getSession();
		
		StringBuffer query = new StringBuffer();
		
		query.append(" select  distinct od.dsgnName " ); 
		query.append(" from OrgEmpMst emp,HrEisEmpMst eis,OrgUserpostRlt up, OrgPostDetailsRlt det,OrgDesignationMst od ");  
		query.append(" where emp.empId=eis.orgEmpMst.empId and emp.empId="+employeeId+" and up.orgUserMst.userId =emp.orgUserMst.userId and det.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1 "); 
		query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId ");
		
		
			Query sqlQuery = hibSession.createQuery(query.toString());
			
			logger.info("==> getdsgnNameFromEmployeeId query :: "+sqlQuery.toString());
	        list= sqlQuery.list();	 
	        if(list.size()>0 && !list.isEmpty())
	        {
	        if(list.get(0) !=null)
            {
	        	designationName = (list.get(0).toString());
            }
	        }
	        else
	        {
	        	designationName="";
	        }  	 			 			 		
        
 		return designationName;
	}
	
	/*public Boolean checkContributionsExistInTheBillGroup(Long lLongBillGroupId,String lStrDdoCode) throws Exception {

		List billGroupList = null;
		Boolean lBlFlag = false;
		try {

			String query = "from MstDcpsContriVoucherDtls where billGroupId= :billGroupId and ddoCode = :ddoCode";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("billGroupId", lLongBillGroupId);
			stQuery.setParameter("ddoCode", lStrDdoCode);
			
			billGroupList = stQuery.list();
			if (billGroupList.size() != 0) {
				lBlFlag = true;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return lBlFlag;
	}*/
	//added by vaibhav tyagi:start
	public Boolean checkContributionsExistInTheBillGroup(Long lLongBillGroupId,String lStrDdoCode) throws Exception {

		List billGroupList = null;
		Boolean lBlFlag = false;
		try {

			//String query = "from MstDcpsContriVoucherDtls where billGroupId= :billGroupId and ddoCode = :ddoCode";
			
			String query = "from TrnDcpsContribution where dcpsDdoBillGroupId = :billGroupId and regStatus = 0 ";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("billGroupId", lLongBillGroupId);
			//stQuery.setParameter("ddoCode", lStrDdoCode);
			
			billGroupList = stQuery.list();
			if (billGroupList.size() != 0) {
				lBlFlag = true;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return lBlFlag;
	}
	//added by vaibhav tyagi :end
	//Ended by Abhilash for Showing post types
	
	
	//added by saurabh
	public Long checkSubBillCount(String schemeCode, String strDdoCode,String subSchemeCode) {
		Session hibSession = getSession();

		Long finalCheckFlag=null;
		StringBuffer sb= new StringBuffer();
		gLogger.info("schemeCode: "+schemeCode+" subSchemeCode "+subSchemeCode);

		if(!subSchemeCode.equals(null) && subSchemeCode!="")
		{
			sb.append("SELECT count(1) FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+schemeCode+"' and SUB_SCHEME_CODE='"+subSchemeCode+"' and DDO_CODE = '"+strDdoCode+"' and SUB_BG_OR_NOT = '1' and  BILL_DELETED is null ");
		}
		else
		{
			sb.append("SELECT count(1) FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+schemeCode+"' and DDO_CODE = '"+strDdoCode+"' and SUB_BG_OR_NOT = '1' and BILL_DELETED is null");
		}
		

		//sb.append("SELECT count(1) FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+schemeCode+"' and DDO_CODE = '"+strDdoCode+"' and SUB_BG_OR_NOT = '1' and BILL_DELETED <>'Y'");

		gLogger.info("Query to check sub bill group count:  " + sb);
		Query sqlQuery1 = hibSession.createSQLQuery(sb.toString());
		finalCheckFlag=Long.parseLong(sqlQuery1.uniqueResult().toString());

		return finalCheckFlag;
	}
	
	//ended by saurabh
	
	public List checkForVoucherEntry(Long lLongBillGroupId,String locid) throws Exception {

		List billGroupList = null;
		boolean lBlFlag = true;
		logger.info("lLongBillGroupId ----------"+lLongBillGroupId);
		try {

			String query = "select paybill_month,paybill_year from Paybill_Head_Mpg where bill_No = :billGroupId and approve_Flag not in (1,3)  ";

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			SBQuery.append("and loc_id not in ('4000840','4000841','1494','1495','1498','4000230','4000238','4000247','4000254','4000261','4000273', '4000305','4000314','4000434', ");
			SBQuery.append("'4000440','4000443','4000449','4000452','4000458','4003040', '4003041','4000838','4001532','4001539','4001542','4000279' ) ");
			Query stQuery = ghibSession.createSQLQuery(SBQuery.toString());
			stQuery.setParameter("billGroupId", lLongBillGroupId);
			logger.info("stQuery-------"+stQuery);
			billGroupList = stQuery.list();
			logger.info("billGroupList.size()-------"+billGroupList.size());
		/*	if (billGroupList.size() != 0) {
				lBlFlag = false;
			}*/

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return billGroupList;
	}
	
	
	
	//Added by saurabh for Subscheme code
	/*
		public List fetchSubSchemeDetails(String schemeCode) {
			Session hibSession = getSession();

			List subSchemeDetails=null;
			StringBuffer sb= new StringBuffer();
			gLogger.info("schemeCode: "+schemeCode);

			sb.append(" SELECT SS.SUBSCHEME_CD,SSM.DISCRIPTION,MS.DEMAND_CODE FROM scheme_subscheme SS ");
			sb.append(" inner join SUBSCHEME_MASTER SSM on SSM.SUBSCHEME_CD=SS.SUBSCHEME_CD  ");
			sb.append(" inner join MST_SCHEME MS on MS.SCHEME_CODE=SS.SCHEME_CD  ");
			sb.append(" where SCHEME_CD='"+schemeCode+"'  ");

			//sb.append("SELECT count(1) FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+schemeCode+"' and DDO_CODE = '"+strDdoCode+"' and SUB_BG_OR_NOT = '1' and BILL_DELETED <>'Y'");

			gLogger.info("Query to check sub bill group count:  " + sb);
			Query sqlQuery1 = hibSession.createSQLQuery(sb.toString());
			subSchemeDetails =sqlQuery1.list();

			return subSchemeDetails;
		}*/
		
	public List fetchSubSchemeDetails(String schemeCode, String ddoCode, int level) { 
        Session hibSession = getSession(); 

        List subSchemeDetails=null; 
        StringBuffer sb= new StringBuffer(); 
        gLogger.info("schemeCode: "+schemeCode); 

        sb.append(" SELECT distinct SS.SUBSCHEME_CD,SSM.DISCRIPTION,MS.DEMAND_CODE FROM scheme_subscheme SS "); 
        sb.append(" inner join SUBSCHEME_MASTER SSM on SSM.SUBSCHEME_CD=SS.SUBSCHEME_CD  "); 
        sb.append(" inner join MST_SCHEME MS on MS.SCHEME_CODE=SS.SCHEME_CD  "); 
        sb.append(" inner join RLT_DCPS_DDO_SCHEMES dcpsBill on dcpsBill.SUB_SCHEME_CODE = ss.SUBSCHEME_CD "); 
        sb.append(" where dcpsBill.SCHEME_CODE='"+schemeCode+"'   "); 
        if(level==1) 
        { 
                sb.append(" and dcpsBill.DDO_CODE ='"+ddoCode+"' "); 
        } 
        else 
        { 
                sb.append(" and dcpsBill.DDO_CODE in (select zp_ddo_code from rlt_zp_ddo_map where rept_ddo_code ='"+ddoCode+"')"); 
        } 

        //sb.append("SELECT count(1) FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+schemeCode+"' and DDO_CODE = '"+strDdoCode+"' and SUB_BG_OR_NOT = '1' and BILL_DELETED <>'Y'"); 

        gLogger.info("Query to check sub bill group count:  " + sb); 
        Query sqlQuery1 = hibSession.createSQLQuery(sb.toString()); 
        logger.info("Query..123"+sqlQuery1.toString());
        subSchemeDetails =sqlQuery1.list(); 

        return subSchemeDetails; 
} 
	
	
	
	
		public String getSubSchemeName(String subSchemeCode) {
			Session hibSession = getSession();

			String subSchemeName=null;
			StringBuffer sb= new StringBuffer();
			gLogger.info("schemeCode: "+subSchemeCode);

			sb.append(" SELECT * FROM SUBSCHEME_MASTER where SUBSCHEME_CD='"+subSchemeCode+"' ");
			
			//sb.append("SELECT count(1) FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+schemeCode+"' and DDO_CODE = '"+strDdoCode+"' and SUB_BG_OR_NOT = '1' and BILL_DELETED <>'Y'");

			gLogger.info("Query to check sub bill group count:  " + sb);
			Query sqlQuery1 = hibSession.createSQLQuery(sb.toString());
			List list = sqlQuery1.list();
			if(list.size()>0 && !list.isEmpty())
			{
				if(list.get(0) !=null)
				{
					subSchemeName = (list.get(0).toString());
				}
			}
			

			return subSchemeName;
		}
	


	@Override
	public Integer getTotalVacantPostsForBillGroup(Long dcpsDdoBillGroupId) {
		// TODO Auto-generated method stub
		return null;
	}
}
