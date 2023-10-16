package com.tcs.sgv.eis.dao;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BDSIntegrationDAOImpl extends GenericDaoHibernateImpl
{
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");

	public BDSIntegrationDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getSchemeAndSubHeadOfBill(long billGroup) {
		List resultList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		/*sb.append("SELECT scheme.schemeCode, scheme.subHead FROM MstDcpsBillGroup bg , MstScheme scheme where bg.dcpsDdoSchemeCode = scheme.schemeCode and ");
		sb.append("bg.dcpsDdoBillGroupId = ");
		sb.append(billGroup);*/
		//modified by saurabh
		//sb.append("SELECT SCHEME_CODE FROM CONSOLIDATED_BILL_MST where CONS_BILL_ID= ");
		sb.append("SELECT SCHEME_CODE,sub_scheme_code FROM CONSOLIDATED_BILL_MST where CONS_BILL_ID= ");
		sb.append(billGroup);
		

		Query query = hibSession.createSQLQuery(sb.toString());
		resultList = query.list();
		return resultList; }

	public List getLoanAdvEdpDetails() {
		List resultList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT loan.LOAN_ADV_ID,compo.CMN_LOOKUP_ID,EDP.PAYBILL_COLUMN,compo.HEAD_OF_ACCOUNT_CODE FROM HR_PAY_EDP_MST edp , HR_PAY_EDP_COMPO_MPG compo ,RLT_BILL_TYPE_EDP type,HR_LOAN_ADV_MST loan where ");
		sb.append("compo.CMN_LOOKUP_ID IN (2500137,2500136) and ");
		sb.append("compo.TYPE_EDP_ID = type.TYPE_EDP_ID and type.EXP_RCP_REC = edp.EXP_RCP_REC ");
		 sb.append("and type.EDP_CODE = edp.EDP_CODE and type.EXP_RCP_REC in ('REC','INT') and loan.LOAN_ADV_ID = compo.TYPE_ID and DISP_GROUP <> 2500383");

		Query query = hibSession.createSQLQuery(sb.toString());
		resultList = query.list();
		return resultList;
	}

	public List getDeducEdpDetails() {
		List resultList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT deduc.DEDUC_CODE,compo.CMN_LOOKUP_ID,EDP.PAYBILL_COLUMN,compo.HEAD_OF_ACCOUNT_CODE FROM HR_PAY_EDP_MST edp , HR_PAY_EDP_COMPO_MPG compo ,RLT_BILL_TYPE_EDP type,HR_PAY_DEDUC_TYPE_MST deduc where ");
		sb.append("compo.CMN_LOOKUP_ID IN (2500135) and ");
		sb.append("compo.TYPE_EDP_ID = type.TYPE_EDP_ID and type.EXP_RCP_REC = edp.EXP_RCP_REC ");
		sb.append("and type.EDP_CODE = edp.EDP_CODE and type.EXP_RCP_REC in ('RCP') and deduc.DEDUC_CODE = compo.TYPE_ID ");
		Query query = hibSession.createSQLQuery(sb.toString());
		resultList = query.list();
		return resultList;
	}
	public List getSchemeForZeroRCDeduction(long paybillId) {
		List resultList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT SCHEME_CODE FROM HR_PAY_PAYBILL_EMP_COMP_MPG where PAYBILL_HEAD_MPG_ID=:paybillId and SCHEME_CODE is not null");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.setParameter("paybillId", paybillId);
		resultList = query.list();
		return resultList;
	}
	public int getNoOfEmpHvnNGR(long paybillId) {
		int resultList = 0;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT count(payslip.PAYBILL_ID) FROM HR_PAY_PAYBILL paybill , HR_PAY_PAYSLIP_NON_GOVT payslip where paybill.id=payslip.paybill_id and PAYBILL_grp_id=:paybillId and paybill.loc_id in (SELECT ddo.LOCATION_CODE FROM HR_PAY_CMP_DDO_CODE cmpddo , org_ddo_mst ddo where ddo.DDO_CODE = cmpddo.DDO_CODE and cmpddo.ACTIVATE_FLAG=1)" );
		Query query = hibSession.createSQLQuery(sb.toString());
		query.setParameter("paybillId", paybillId);
		resultList = Integer.valueOf(query.uniqueResult().toString());
		return resultList;
	}

	public int getTypeOfBill(long paybillId) {
		int resultList = 0;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT BILL_CATEGORY FROM PAYBILL_HEAD_MPG where PAYBILL_ID=:paybillId" );
		Query query = hibSession.createSQLQuery(sb.toString());
		query.setParameter("paybillId", paybillId);
		try{
			resultList = Integer.valueOf(query.uniqueResult().toString());
		}
		catch (NullPointerException e){
			resultList=2;
		}
		return resultList;
	}
	
	public boolean isCmpDDO(String ddoCode) {
		boolean result = false;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM HR_PAY_CMP_DDO_CODE  where DDO_CODE = '"+ddoCode+"' and ACTIVATE_FLAG = 1 " );
		Query query = hibSession.createSQLQuery(sb.toString());
		
		try{
			if(query != null && query.list() != null && query.list().size() == 1)
				result = true;
			}
		catch (NullPointerException e){
			result= false;
		}
		return result;
	}
	
	  public List getdcpsIASIFSIPSDetails(long billno,long month,long year) {
		    List resultList = new ArrayList();
		    Session hibSession = getSession();
		    StringBuffer sb = new StringBuffer();
		    sb.append(" SELECT sum(pay.dcps),sum(pay.DCPS_DELAY),sum(pay.DCPS_Da),sum(pay.DCPS_PAY),emp.AC_DCPS_MAINTAINED_BY,sum(pay.JANJULGISARR) FROM HR_PAY_PAYBILL pay  ");
		    sb.append(" inner join PAYBILL_HEAD_MPG head on pay.PAYBILL_GRP_ID = head.PAYBILL_ID  ");
		    sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID=pay.EMP_ID ");
		    sb.append(" inner join MST_DCPS_EMP emp on emp.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
	sb.append(" where pay.dcps <> 0 and emp.AC_DCPS_MAINTAINED_BY in (700240,700241,700242) and head.BILL_NO = "+billno+" ");
	sb.append(" and head.PAYBILL_MONTH ="+month+" and head.PAYBILL_YEAR="+year+" and head.APPROVE_FLAG in (0,5) ");
	sb.append(" group by emp.AC_DCPS_MAINTAINED_BY ");

		    Query query = hibSession.createSQLQuery(sb.toString());
		    resultList = query.list();
		    return resultList;
		  }
	
}