package com.tcs.sgv.dcps.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;

public class ViewCreatedPostDtlsDAOImpl extends GenericDaoHibernateImpl<PaybillHeadMpg,Long>{
	public ViewCreatedPostDtlsDAOImpl(Class<PaybillHeadMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getCreatedPostDtls(String lStrDdocode) {
		
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT nvl(emp.EMP_NAME,'VACANT'),postDtls.POST_NAME||");
		sb.append("(case when postMst.POST_TYPE_LOOKUP_ID=10001198129 then 'P' else 'T' end),");
		sb.append("(case when postMst.POST_TYPE_LOOKUP_ID = 10001198129 then 'Permanent Post' else 'Temporary Post' end),");
		sb.append("(case when postMst.ACTIVATE_FLAG=1 then 'ACTIVE' else 'DEACTIVE' end),dsgn.DESIG_DESC, rlt.ZP_DDO_CODE ");
		sb.append("FROM org_post_mst postMst ");
		sb.append("left outer join ORG_POST_DETAILS_RLT postDtls on postMst.post_id=postDtls.post_id ");
	    sb.append("left outer join MST_PAYROLL_DESIGNATION dsgn on dsgn.ORG_DESIGNATION_ID= postMst.DSGN_CODE ");
	    sb.append("left outer join RLT_DCPS_PAYROLL_EMP rltPay on rltPay.post_id=postMst.POST_ID ");  
	    sb.append("left outer join MST_DCPS_EMP emp on emp.DCPS_EMP_ID=rltPay.DCPS_EMP_ID ");
	    sb.append("left outer join org_ddo_mst ddo on postMst.LOCATION_CODE= ddo.LOCATION_CODE ");
	    sb.append("left outer join rlt_zp_ddo_map rlt on rlt.ZP_DDO_CODE= ddo.DDO_CODE ");
	    sb.append("where postMst.POST_TYPE_LOOKUP_ID in (10001198129,10001198130) and ");
	    sb.append("ddo.ddo_code = '"+lStrDdocode+"' GROUP by emp.EMP_NAME,postDtls.POST_NAME,postMst.POST_TYPE_LOOKUP_ID,postMst.ACTIVATE_FLAG,dsgn.DESIG_DESC,rlt.ZP_DDO_CODE");
		
		logger.info("query to getGeneratedBillList is " + sb.toString());
		Query sqlQuery=hibSession.createSQLQuery(sb.toString());	
		
		return sqlQuery.list();
	}
	public List getDDOOfcAddDesgName(String locId) {
		// TODO Auto-generated method stub
		Session hibeSession = getSession();
		// String query =
		// "select  dcps.ADDRESS1,dcps.DDO_CODE,  ddo.DDO_OFFICE, ddo.DSGN_NAME from MST_DCPS_DDO_OFFICE dcps, ORG_DDO_MST ddo where ddo.DDO_CODE = dcps.DDO_CODE and ddo.LOCATION_CODE="+locId;
		String query = "SELECT nvl(emp.EMP_NAME,'VACANT'),postDtls.POST_NAME||(case when postMst.POST_TYPE_LOOKUP_ID=10001198129 then 'P' else 'T' end),\r\n"
				+ "(case when postMst.POST_TYPE_LOOKUP_ID = 10001198129 then 'Permanent Post' else 'Temporary Post' end),\r\n"
				+ "(case when postMst.ACTIVATE_FLAG=1 then 'ACTIVE' else 'DEACTIVE' end),\r\n"
				+ "dsgn.DESIG_DESC FROM org_post_mst postMst \r\n"
				+ "left outer join ORG_POST_DETAILS_RLT postDtls on postMst.post_id=postDtls.post_id \r\n"
				+ "left outer join MST_PAYROLL_DESIGNATION dsgn on dsgn.ORG_DESIGNATION_ID= postMst.DSGN_CODE \r\n"
				+ "left outer join RLT_DCPS_PAYROLL_EMP rltPay on rltPay.post_id=postMst.POST_ID \r\n"
				+ "left outer join MST_DCPS_EMP emp on emp.DCPS_EMP_ID=rltPay.DCPS_EMP_ID \r\n"
				+ "left outer join org_ddo_mst ddo on postMst.LOCATION_CODE= ddo.LOCATION_CODE \r\n"
				+ "where postMst.POST_TYPE_LOOKUP_ID in (10001198129,10001198130) \r\n"
				+ "GROUP by emp.EMP_NAME,postDtls.POST_NAME,postMst.POST_TYPE_LOOKUP_ID,postMst.ACTIVATE_FLAG,dsgn.DESIG_DESC";
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
	
}
