package com.tcs.sgv.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class UpdateEmpDOBAndDORDAOImpl extends GenericDaoHibernateImpl implements UpdateEmpDOBAndDORDAO {

	Session ghibSession = null;

	public UpdateEmpDOBAndDORDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public List getEmployeeInfo(String lStrSevaarthId) {
		
		logger.error("getEmployeeInfo dao ");
		List lLstEmployeeInfo = new ArrayList();
		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT EM.ddo_code,DDO.DDO_NAME,EM.cadre,DOF.OFF_NAME,EM.DOB,EM.EMP_SERVEND_DT,EM.ORG_EMP_MST_ID FROM mst_dcps_emp EM ");
			lSBQuery.append("join org_ddo_mst DDO on EM.DDO_CODE = DDO.DDO_CODE join mst_dcps_ddo_office DOF on EM.DDO_CODE = DOF.DDO_CODE ");
			lSBQuery.append("where EM.sevarth_id = :sevaarthId");
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lLstEmployeeInfo = lQuery.list();
			
			logger.error(" query "+lSBQuery.toString() );
		} catch (Exception e) {
			logger.error(" Error is getEmployeeInfo: " + e, e);
		}
		return lLstEmployeeInfo;
	}

	public void updateDOB(String lStrSevaarthId, Date lDtDOB, Date lDtDOR) {
		try {
			
			logger.error("updateDOB dao ");
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("update mst_dcps_emp set dob = :DOB,EMP_SERVEND_DT = :DOR,SUPER_ANN_DATE = :DOR where sevarth_id = :sevaarthId");
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lQuery.setDate("DOB", lDtDOB);
			lQuery.setDate("DOR", lDtDOR);
			lQuery.executeUpdate();
			
			
			logger.error("updateDOB dao ");
			StringBuilder lSBQuery1 = new StringBuilder();
			lSBQuery1.append("update mst_dcps_emp_details set dob = :DOB,EMP_SERVEND_DT = :DOR,SUPER_ANN_DATE = :DOR where sevarth_id = :sevaarthId");
			Query lQuery1 = ghibSession.createSQLQuery(lSBQuery1.toString());
			lQuery1.setParameter("sevaarthId", lStrSevaarthId);
			lQuery1.setDate("DOB", lDtDOB);
			lQuery1.setDate("DOR", lDtDOR);
			lQuery1.executeUpdate();
			
			
		} catch (Exception e) {
			logger.error(" Error is updateDOB: " + e, e);
		}
	}

	public void updateDOR(Long lLngOrgEmpId, Date lDtDOB, Date lDtDOR) {

		try {
			logger.error("updateDOR dao ");
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("update org_emp_mst set EMP_DOB = :DOB,EMP_SRVC_EXP = :DOR where emp_id = :EmpId");
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("EmpId", lLngOrgEmpId);
			lQuery.setDate("DOB", lDtDOB);
			lQuery.setDate("DOR", lDtDOR);
			lQuery.executeUpdate();
		} catch (Exception e) {
			logger.error(" Error is updateDOR: " + e, e);
		}
	}
}
