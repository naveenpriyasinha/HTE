package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class EmpStatisticsDDOwiseDAOImpl extends GenericDaoHibernateImpl {

	public EmpStatisticsDDOwiseDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getEmpStatisticsDDOwise(String lStrDdocode) {
		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		// strBfr.append(" select eis.SEVARTH_EMP_CD,org.EMP_FNAME||' '||org.emp_mname||' '||org.emp_lname, dcp.basic_pay,rlt.POST_NAME,post.START_DATE,post.END_DATE, ");
		strBfr.append(" select eis.SEVARTH_EMP_CD,org.EMP_FNAME||' '||org.emp_mname||' '||org.emp_lname,case when dcp.PAY_COMMISSION = 700005 then dcp.BASIC_PAY ELSE other.OTHER_CURRENT_BASIC END,rlt.POST_NAME,post.START_DATE,post.END_DATE, ");// Added
																																																													// details
		// strBfr.append(" org.EMP_DOJ,dcp.EMP_SERVEND_DT,bill.DESCRIPTION,gpf.PF_SERIES,gpf.GPF_ACC_NO,grade.GRADE_NAME,scale.SCALE_DESC,other.PHY_CHALLENGED,post.POST_TYPE_LOOKUP_ID,org.EMP_DOB,dcp.TYPE_EMP,grade1.GRADE_NAME,bank.BANK_NAME,dcp.BANK_ACNT_NO,bnkBrnch.BRANCH_NAME,dcp.SEVEN_PC_BASIC");
		strBfr.append(" org.EMP_DOJ,dcp.EMP_SERVEND_DT,bill.DESCRIPTION,gpf.PF_SERIES,gpf.GPF_ACC_NO,grade.GRADE_NAME,scale.SCALE_DESC,other.PHY_CHALLENGED,"
				+ "post.POST_TYPE_LOOKUP_ID,org.EMP_DOB,dcp.TYPE_EMP,grade1.GRADE_NAME,bank.BANK_NAME,dcp.BANK_ACNT_NO,bnkBrnch.BRANCH_NAME,"
				+ "other.OTHER_SVN_PC_BASIC,rltppay.LEVEL,case when dcp.pran_no is null then '-' else  dcp.pran_no end");// changed
																																																																												// basic
		strBfr.append(" from hr_Eis_emp_mst eis left outer join org_emp_mst org on eis.EMP_MPG_ID = org.EMP_ID ");
		strBfr.append(" left outer join MST_DCPS_EMP dcp on org.EMP_ID = dcp.ORG_EMP_MST_ID ");
		strBfr.append(" left outer join RLT_PAYBAND_GP_STATE_7PC rltppay on rltppay.LEVEL_ID = dcp.SEVEN_PC_LEVEL ");
		strBfr.append(" left outer join ORG_USERPOST_RLT up on up.USER_ID = org.USER_ID and up.ACTIVATE_FLAG = 1 ");
		strBfr.append(" left outer join ORG_POST_MST post on post.POST_ID = up.POST_ID and post.ACTIVATE_FLAG = 1 ");
		strBfr.append(" left outer join ORG_POST_DETAILS_RLT rlt on post.POST_ID = rlt.POST_ID and rlt.LOC_ID = post.LOCATION_CODE ");
		strBfr.append(" left outer join HR_PAY_POST_PSR_MPG psr on psr.POST_ID = post.POST_ID and psr.LOC_ID = post.LOCATION_CODE ");
		strBfr.append(" left outer join HR_PAY_GPF_DETAILS gpf on gpf.USER_ID = up.USER_ID ");
		strBfr.append(" left outer join HR_EIS_GIS_DTLS gis on gis.EMP_ID = eis.EMP_ID ");
		strBfr.append(" left outer join HR_EIS_OTHER_DTLS other on other.EMP_ID = eis.EMP_ID ");
		strBfr.append(" left outer join HR_EIS_SGD_MPG sgd on other.EMP_SGD_ID = sgd.SGD_MAP_ID and sgd.LOC_ID = post.LOCATION_CODE ");
		strBfr.append(" left outer join HR_EIS_SCALE_MST scale on scale.SCALE_ID = sgd.SGD_SCALE_ID ");
		strBfr.append(" left outer join ORG_GRADE_MST grade on grade.GRADE_ID = org.GRADE_ID ");
		strBfr.append(" left outer join ORG_GRADE_MST grade1 on grade1.GRADE_ID = gis.GIS_GROUP_GRADE_ID ");
		strBfr.append(" left outer join org_ddo_mst ddo on ddo.LOCATION_CODE = post.LOCATION_CODE ");
		strBfr.append(" left outer join MST_DCPS_BILL_GROUP bill on bill.BILL_GROUP_ID = psr.BILL_NO ");
		strBfr.append(" left outer join MST_BANK_PAY BANK ON BANK.BANK_CODE=DCP.BANK_NAME ");
		strBfr.append(" left outer join RLT_BANK_BRANCH_PAY bnkBrnch on bnkBrnch.BRANCH_CODE=DCP.BRANCH_NAME ");
		strBfr.append(" where ddo.DDO_CODE = :Ddocode  order by bill.BILL_GROUP_ID");
		Query lQuery = hibSession.createSQLQuery(strBfr.toString());
		lQuery.setParameter("Ddocode", lStrDdocode);
		System.out.println("The EmployeeStatsticsQuery.." + strBfr.toString());
		list = lQuery.list();
		return list;
	}

	// added by shailesh
	public List getDDOOfcAddDesgName(String locId, String lStrDdocode) {
		Session hibeSession = getSession();
		String query=null;
		
		if(locId.equals("380002")) {
			 query = "select  dcps.ADDRESS1,dcps.DDO_CODE,  ddo.DDO_OFFICE, ddo.DSGN_NAME, dist.DISTRICT_NAME,tal.TALUKA_NAME,stte.STATE_NAME  from"
					+ " MST_DCPS_DDO_OFFICE dcps, ORG_DDO_MST ddo, CMN_DISTRICT_MST dist,  CMN_TALUKA_MST tal, CMN_STATE_MST stte where ddo.DDO_CODE = "
					+ "dcps.DDO_CODE and dcps.DISTRICT = dist.DISTRICT_ID and dcps.TALUKA = tal.TALUKA_ID and dcps.STATE = stte.STATE_ID and dist.LANG_ID=1 "
					+ "and ddo.DDO_CODE=" + lStrDdocode;
			
		}else {
			 query = "select  dcps.ADDRESS1,dcps.DDO_CODE,  ddo.DDO_OFFICE, ddo.DSGN_NAME, dist.DISTRICT_NAME,tal.TALUKA_NAME,stte.STATE_NAME  from"
					+ " MST_DCPS_DDO_OFFICE dcps, ORG_DDO_MST ddo, CMN_DISTRICT_MST dist,  CMN_TALUKA_MST tal, CMN_STATE_MST stte where ddo.DDO_CODE = "
					+ "dcps.DDO_CODE and dcps.DISTRICT = dist.DISTRICT_ID and dcps.TALUKA = tal.TALUKA_ID and dcps.STATE = stte.STATE_ID and dist.LANG_ID=1 "
					+ "and ddo.LOCATION_CODE=" + locId;
			
		}
		// String query =
		// "select  dcps.ADDRESS1,dcps.DDO_CODE,  ddo.DDO_OFFICE, ddo.DSGN_NAME from MST_DCPS_DDO_OFFICE dcps, ORG_DDO_MST ddo where ddo.DDO_CODE = dcps.DDO_CODE and ddo.LOCATION_CODE="+locId;
		
		Query lQuery = hibeSession.createSQLQuery(query);
		List resultList = lQuery.list();
		// Iterator it = resultList.iterator();
		// ArrayList arrlst = new ArrayList();
		/*
		 * while(it.hasNext()){ arrlst = (ArrayList)it.next(); }
		 */
		logger.info("size of list is .." + resultList.size());
		return resultList;
	}

	public List getDdoCodeForAutoComplete(String searchKey) {
		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;
		Session hibSession = getSession();
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		lSBQuery.append("select ddoCode from OrgDdoMst where ddoCode LIKE :searchKey order by ddoCode");
		lQuery = hibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("searchKey", searchKey + '%');

		List resultList = lQuery.list();

		if (resultList != null && !resultList.isEmpty()) {
			for (Integer lInt = 0; lInt < resultList.size(); lInt++) {
				cmbVO = new ComboValuesVO();
				cmbVO.setId(resultList.get(lInt).toString());
				cmbVO.setDesc(resultList.get(lInt).toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}
}
