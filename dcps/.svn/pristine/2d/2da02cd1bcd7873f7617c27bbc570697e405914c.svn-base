package com.tcs.sgv.dcps.dao;



import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpDetails;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmpDetails;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class LegacyDataEntryDaoImpl extends GenericDaoHibernateImpl implements
		LegacyDataEntryDao {
	
	
	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	/**
	 * 
	 * @param type
	 * @param sessionFactory
	 */
	public LegacyDataEntryDaoImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}
	
	
	public List LegacyDataEntry(String strDdoCode){
		logger.info("lStrUser...."+strDdoCode);
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		logger.info("hi i am    in new reg ddo dao");
		lSBQuery.append(" select emp.dcps_emp_id,emp.emp_name,emp.sevarth_id,dcps.EMP_CONTRIBUTION,dcps.EMPLOYER_CONTRIBUTION,dcps.EMP_INTEREST,dcps.EMPLOYER_INTEREST,dcps.TOTAL,dcps.DDO_CODE from mst_dcps_emp emp left join DCPS_LEGACY_DATA dcps " +
				" on dcps.dcps_emp_id=emp.dcps_emp_id where emp.zp_status=10 and emp.DCPS_OR_GPF='Y' and emp.ddo_code='"+strDdoCode+"'");
		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("query  getEmployeeList for Legact data entry"+lQuery.toString());
			
		EmpList = lQuery.list();
		logger.info("size is "+EmpList.size());
		return EmpList;	
	}
	
	
	
	public List viewLegacyDataEntry(String strDdoCode){
		logger.info("lStrUser...."+strDdoCode);
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		logger.info("hi i am    in new reg ddo dao");
		lSBQuery.append(" select emp.dcps_emp_id,emp.emp_name,emp.sevarth_id,dcps.TOTAL,dcps.DDO_CODE from mst_dcps_emp emp inner join DCPS_LEGACY_DATA dcps " +
				" on dcps.dcps_emp_id=emp.dcps_emp_id where dcps.status='2' and emp.ddo_code='"+strDdoCode+"'");
		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("query  getEmployeeList for Legact data entry"+lQuery.toString());
			
		EmpList = lQuery.list();
		logger.info("size is "+EmpList.size());
		return EmpList;	
	}
	
	
	
	
	public void approveSavedData(String strEmpId){
		Session session=getSession();
		logger.info("lStrUser...."+strEmpId);
		try{
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
	
		logger.info("hi i am    in new reg ddo dao");
		sb.append(" update dcps_legacy_data set dcps_legacy_data.status='1' where dcps_legacy_data.dcps_emp_id='"+strEmpId+"' "); 
		lQuery = session.createSQLQuery(sb.toString());
			logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			
			lQuery.executeUpdate();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void rejectSavedData(String strEmpId){
		Session session=getSession();
		logger.info("lStrUser...."+strEmpId);
		try{
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
	
		logger.info("hi i am    in new reg ddo dao");
		sb.append(" update dcps_legacy_data set dcps_legacy_data.status='0' where dcps_legacy_data.dcps_emp_id='"+strEmpId+"' "); 
		lQuery = session.createSQLQuery(sb.toString());
			logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			
			lQuery.executeUpdate();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public void saveData(String empIds, String empContri,String employerContri, String empInterest,String employerInterest,String total, Map objectArgs)   {
		Session session=getSession();
		
		final Map loginMap = (Map) objectArgs.get("baseLoginMap");
        final long lLongLoggedInLocation = Long.valueOf(loginMap.get("locationId").toString()).longValue();
       Long userId;
       Long primaryPostId;
       Long npsId= 0l;
	try {
		userId = StringUtility.convertToLong(loginMap.get("userId").toString()).longValue();
		primaryPostId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString()).longValue();
	    
		
		
		 npsId = IFMSCommonServiceImpl.getNextSeqNum("DCPS_LEGACY_DATA", objectArgs);
		logger.info("npsId******"+npsId);;
		logger.info("----insert in DCPS_LEGACY_DATA ---123");
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into DCPS_LEGACY_DATA(NPS_ID,DDO_CODE,SEVARTH_ID,DCPS_ID,DCPS_EMP_ID,EMP_CONTRIBUTION,EMPLOYER_CONTRIBUTION, ");
		sb.append(" EMP_INTEREST,EMPLOYER_INTEREST,TOTAL,YEAR,MONTH,STATUS,CREATED_DATE,CREATED_BY,CREATED_BY_POST) ");
		sb.append(" VALUES ('"+npsId+"',(select ddo_code from mst_dcps_emp where dcps_emp_id='"+empIds+"'),(select sevarth_id from mst_dcps_emp where dcps_emp_id='"+empIds+"' ),(select DCPS_ID from mst_dcps_emp where dcps_emp_id='"+empIds+"'),'"+empIds+"','"+empContri+"','"+employerContri+"','"+empInterest+"','"+employerInterest+"','"+total+"',0,0,2,sysdate,'"+userId+"','"+primaryPostId+"') ");
		
		
		
		logger.info("---- insert in DCPS_LEGACY_DATA ---");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- query---"+sb);
		query.executeUpdate();
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	
	
	
	public String getDdoCode(Long lLngPostId) {
		logger.info("---- insert ingetDdoCode ---");
		Session session=getSession();
		String lStrDdoCode = null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" SELECT zp_ddo_code");
		lSBQuery.append(" FROM rlt_zp_ddo_map where rept_ddo_post_id='"+lLngPostId+"' ");
		logger.info("---- insert in getDdoCode---");
		Query query = session.createSQLQuery(lSBQuery.toString());
		List lLstCodeList = query.list();

		if(lLstCodeList != null)
		{
			if(lLstCodeList.size() != 0)
			{
				if(lLstCodeList.get(0) != null)
				{
					lStrDdoCode = lLstCodeList.get(0).toString();
				}
			}
		}

		return lStrDdoCode;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}