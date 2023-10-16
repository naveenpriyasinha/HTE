/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jan 31, 2011		Shivani Rana								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstDummyOffice;
import com.tcs.sgv.pensionproc.dao.PensionProcComparators;

/**
 * Class Description -
 * 
 * 
 * @author Shivani Rana
 * @version 0.1
 * @since JDK 5.0 Jan 31, 2011
 */
public class TreasuryDAOImpl extends GenericDaoHibernateImpl implements
		TreasuryDAO {

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	SessionFactory sessionFactory = null;

	public TreasuryDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public List getAllDDOListForPhyFormRcvd(String lStrPostId,
			String lStrUserType) {

		List listAllForms = null;
		List<ComboValuesVO> listAllFormsCombo = new ArrayList<ComboValuesVO>();
		StringBuilder lSBQuery = new StringBuilder();
		ComboValuesVO cmbVO;
		List lLstResultList = null;
		Iterator itr;
		Object[] obj;
		try {
			//System.out.println("lStrPostId is  " + lStrPostId);
			if (lStrUserType == null) {
				lSBQuery
						.append("select distinct DDO.ddoCode, DDO.ddoName from OrgDdoMst DDO, MstEmp emp,WfJobMst wf");
				lSBQuery
						.append(" WHERE DDO.ddoCode = emp.ddoCode AND wf.jobRefId = emp.dcpsEmpId AND wf.lstActPostId = :postId ");
				Query lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("postId", lStrPostId);
				listAllForms = lQuery.list();
			} else {
				lSBQuery
						.append("select distinct DDO.ddoCode, DDO.ddoName from OrgDdoMst DDO, MstEmp emp,WfJobMst wf");
				lSBQuery
						.append(" WHERE DDO.ddoCode = emp.ddoCode AND wf.jobRefId = emp.dcpsEmpId AND wf.lstActPostId = :postId ");
				Query lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("postId", lStrPostId);
				lLstResultList = lQuery.list();
				cmbVO = new ComboValuesVO();
				if (lLstResultList != null && lLstResultList.size() > 0) {
					itr = lLstResultList.iterator();
					while (itr.hasNext()) {

						obj = (Object[]) itr.next();
						cmbVO.setId(obj[0].toString());
						cmbVO.setDesc(obj[1].toString().replaceAll("&", "And"));
						listAllFormsCombo.add(cmbVO);
					}
				}
			}

		} catch (Exception e) {
			gLogger
					.error("Exception occured from getAllDDOListForPhyFormRcvd of TreasuryDAOImpl is :: "
							+ e);
			e.printStackTrace();
		}
		if (lStrUserType == null) {
			return listAllForms;
		} else {
			return listAllFormsCombo;
		}

	}

	public List getAllFormsForDDO(String lStrDDODode, String lStrPostId) {

		getSession();
		List listAllForms = null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery
				.append("SELECT EM.dcpsEmpId,EM.ddoCode,EM.name,EM.gender,EM.dob,");
		lSBQuery.append("EM.designation FROM MstEmp EM, WfJobMst wf");
		lSBQuery
				.append(" WHERE EM.ddoCode =:ddoCode AND  wf.jobRefId = EM.dcpsEmpId AND wf.lstActPostId = :postId ");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDDODode);
		lQuery.setParameter("postId", lStrPostId);
		listAllForms = lQuery.list();

		return listAllForms;

	}

	public List getYearsForSixPCYearly() {
		String query = "select finYearId,finYearDesc from SgvcFinYearMst";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
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

	public List getDummyOffices() throws Exception {

		List<ComboValuesVO> lLstOffice = new ArrayList<ComboValuesVO>();
		List lLstResultList = null;
		StringBuilder lSBQuery = null;
		Query hqlQuery = null;
		ComboValuesVO cmbVO;
		try {
			ghibSession = getSession();
			lSBQuery = new StringBuilder();

			lSBQuery
					.append("SELECT dummyOfficeId,dummyOfficeName FROM MstDummyOffice WHERE  dummyOfficeId IS NOT NULL ");

			hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			lLstResultList = hqlQuery.list();
			Collections.sort(lLstResultList,
					new PensionProcComparators.ObjectArrayComparator(false, 1,
							0, 2, 0, true));
			cmbVO = new ComboValuesVO();
			if (lLstResultList != null && lLstResultList.size() > 0) {
				Iterator it = lLstResultList.iterator();
				while (it.hasNext()) {
					cmbVO = new ComboValuesVO();
					Object[] obj = (Object[]) it.next();
					cmbVO.setId(obj[0].toString());
					cmbVO.setDesc(obj[1].toString());
					lLstOffice.add(cmbVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.info("Error is  " + e);
		}
		return lLstOffice;

	}

	public List getEmpSearchDeptn(String lStrEmpName,String lStrSevarthId) throws Exception {
		
		List lLstEmpDeputn = null;
		Query hqlQuery = null;
		StringBuilder lSBQuery = new StringBuilder();
		Session session = getSession();
		lStrEmpName = lStrEmpName.toUpperCase();

		try {

			lSBQuery.append(" SELECT emp.dcpsEmpId,emp.dcpsId,emp.name ");
			lSBQuery.append(" FROM MstEmp emp WHERE emp.regStatus IN (1,2)  ");

			/*
			 * if (lStrQuery.equals("Attach")) {
			 * lStrQuery.append(" AND emp.currOff IS NULL "); }
			 * 
			 * if (lStrQuery.equals("Detach")) {
			 * lStrQuery.append(" AND emp.currOff IS NOT NULL "); }
			 */
			
			if (lStrEmpName != null && lStrEmpName.length() > 1) {
				lSBQuery.append(" AND UPPER(emp.name) = :lStrEmpName ");
			}
			if (lStrSevarthId != null && lStrSevarthId.length() > 1) {
				lSBQuery.append(" AND UPPER(emp.sevarthId) = :lStrSevarthId ");
			}
			hqlQuery = session.createQuery(lSBQuery.toString());

			if (lStrEmpName != null && lStrEmpName.length() > 1) {
				hqlQuery.setParameter("lStrEmpName", lStrEmpName.trim());
			}
			if (lStrSevarthId != null && lStrSevarthId.length() > 1) {
				hqlQuery.setParameter("lStrSevarthId", lStrSevarthId.trim());
			}
			lLstEmpDeputn = hqlQuery.list();

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			throw e;
		}
		return lLstEmpDeputn;
	}

	public List getEmpDeptn(String lStrQuery,String lStrSevarthId,String lStrEmpName) throws Exception {
		
		List lLstEmpDeptn = null;
		SQLQuery sqlQuery = null;
		StringBuilder lSBQuery = new StringBuilder();
		Session session = getSession();

		try {
			if (lStrQuery.equals("Attach")) {
				lSBQuery.append(" SELECT emp.dcps_emp_id,emp.DCPS_ID,emp_name,nvl(hed.off_code,'0'),nvl(hed.hst_dcps_empdeptn_id,0),hed.attach_date,hed.reason,hed.remarks,emp.DESELECTION_DATE ");
				lSBQuery.append(" FROM mst_dcps_emp emp LEFT JOIN hst_dcps_emp_deputation hed ON hed.dcps_emp_id = emp.dcps_emp_id ");
				lSBQuery.append(" WHERE emp.REG_STATUS IN (1,2) AND emp.EMP_ON_DEPTN IN (1,2) ");
				if(lStrSevarthId != null && !"".equals(lStrSevarthId))
				{
					lSBQuery.append(" AND UPPER(emp.sevarth_id) = '" + lStrSevarthId.trim().toUpperCase() +"'");
				}
				if(lStrEmpName != null && !"".equals(lStrEmpName))
				{
					lSBQuery.append(" AND UPPER(emp.emp_name) = '" + lStrEmpName.trim().toUpperCase() +"'");
				}
				lSBQuery.append(" order by emp.emp_name,hed.off_code ASC");
				// 1 for deputed employees and 2 for those who have been de-selected by DDO
			}
			
			if (lStrQuery.equals("Detach")) {
				lSBQuery.append(" SELECT emp.dcps_emp_id,emp.DCPS_ID,emp_name,hed.off_code,hed.hst_dcps_empdeptn_id,hed.detach_date,hed.reason,hed.remarks,hed.attach_date ");
				lSBQuery.append(" FROM mst_dcps_emp emp JOIN hst_dcps_emp_deputation hed ON hed.dcps_emp_id = emp.dcps_emp_id ");
				lSBQuery.append(" WHERE emp.REG_STATUS IN (1,2) AND emp.EMP_ON_DEPTN = 1 AND hed.detach_date IS NULL ");
				if(lStrSevarthId != null && !"".equals(lStrSevarthId))
				{
					lSBQuery.append(" AND UPPER(emp.sevarth_id) = '" + lStrSevarthId.trim().toUpperCase() +"'");
				}
				if(lStrEmpName != null && !"".equals(lStrEmpName))
				{
					lSBQuery.append(" AND UPPER(emp.emp_name) = '" + lStrEmpName.trim().toUpperCase() +"'");
				}
				lSBQuery.append(" order by emp.emp_name,hed.off_code ASC");
			}

			sqlQuery = session.createSQLQuery(lSBQuery.toString());
			lLstEmpDeptn = sqlQuery.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
			throw e;
		}
		return lLstEmpDeptn;

	}

	public Long getHstEmpDeputationPkVal(Long lLngDcpsEmpId) throws Exception {
		Long lLngHstDcpsEmpId = null;
		try {

			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery
					.append(" SELECT HED.hstdcpsEmpDeptnId FROM  HstEmpDeputation HED");
			lSBQuery.append(" WHERE HED.dcpsEmpId.dcpsEmpId = :lLngDcpsEmpId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("lLngDcpsEmpId", lLngDcpsEmpId);

			lLngHstDcpsEmpId = (Long) lQuery.list().get(0);

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);

		}
		return lLngHstDcpsEmpId;
	}

	public List getEmpListForSixPCArrearsYearlyTO(String lStrDDOCode,
			Long finYearId, String lStrPostId) {

		List empList = null;
		try {
			StringBuilder SBQuery = new StringBuilder();

			SBQuery
					.append("SELECT fy.fin_year_desc,EM.DCPS_ID,EM.Emp_name,SPC.TOTAL_AMOUNT,SPC.AMOUNT_PAID,nvl(YPC.YEARLY_AMOUNT,0),nvl(ypc.DCPS_SIXPC_YEARLY_ID,0),EM.DCPS_EMP_ID,fy.fin_year_id,SPC.dcps_sixpc_id,YPC.status_flag");
			SBQuery
					.append(" FROM sgvc_fin_year_mst fy,mst_dcps_emp EM,wf_job_mst job, ");
			SBQuery
					.append(" mst_dcps_sixpc SPC LEFT OUTER JOIN rlt_dcps_sixpc_yearly YPC ON SPC.dcps_emp_id=YPC.dcps_emp_id and YPC.fin_year_id="
							+ finYearId);
			SBQuery.append(" WHERE fy.fin_year_id =" + finYearId);
			SBQuery
					.append(" AND job.lst_act_post_id = '"
							+ lStrPostId
							+ "' AND job.job_ref_id = YPC.DCPS_SIXPC_YEARLY_ID AND YPC.DCPS_EMP_ID = EM.DCPS_EMP_ID AND EM.REG_STATUS = 1   AND EM.DDO_CODE= '"
							+ lStrDDOCode + "'");

			Query stQuery = ghibSession.createSQLQuery(SBQuery.toString());

			empList = stQuery.list();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);
		}
		return empList;

	}

	public List getYears() throws Exception {

		String query = "select finYearId,finYearCode from SgvcFinYearMst where finYearCode between '2008' and '2015'";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
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

	public List getDummyOfficesList(Map inputMap) throws Exception {

		List lLstDummyOfficeDtls = null;
		StringBuilder lSBQuery = null;
		try {
			lSBQuery = new StringBuilder();
			lSBQuery
					.append(" SELECT dummy.dummyOfficeId,dummy.dummyOfficeName,dummy.statusFlag FROM MstDummyOffice dummy");
			Query stQuery = ghibSession.createQuery(lSBQuery.toString());
			lLstDummyOfficeDtls = stQuery.list();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);
		}
		return lLstDummyOfficeDtls;
	}

	public MstDummyOffice getDummyOfficeInfo(String dummyOfficeId)
			throws Exception {

		MstDummyOffice lObjDummyOffice = null;
		StringBuilder lSBQuery = null;

		try {

			lSBQuery = new StringBuilder();
			lSBQuery
					.append("FROM MstDummyOffice where dummyOfficeId= :dummyOfficeId");
			Query stQuery = ghibSession.createQuery(lSBQuery.toString());
			stQuery.setParameter("dummyOfficeId", dummyOfficeId);

			lObjDummyOffice = (MstDummyOffice) stQuery.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);
		}
		return lObjDummyOffice;
	}

	public List getEmployeesFromDummyOffice(String dummyOfficeId,Long monthId,Long yearId)
			throws Exception {

		List lLstDummyOfficeDtls = null;
		StringBuilder lSBQuery = null;

		try {
			lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT EM.emp_name,EM.dcps_emp_id,EM.dcps_id,nvl(DC.CHALLAN_NO,''),DC.CHALLAN_DATE,nvl(DC.CONTRIBUTION,''),nvl(DC.DCPS_DEPTN_CONTRI_ID,0),nvl(DC.CHALLAN_NO_EMPLR,''),DC.CHALLAN_DATE_EMPLR,nvl(DC.CONTRIBUTION_EMPLR,'') ");
			lSBQuery
					.append(" FROM mst_dcps_emp EM ");
			lSBQuery.append(" LEFT OUTER JOIN trn_dcps_deputation_contribution DC ON EM.DCPS_EMP_ID = DC.DCPS_EMP_ID AND");
			lSBQuery.append(" DC.FIN_YEAR_ID = "+ yearId + " AND DC.MONTH_ID = " + monthId);
			lSBQuery.append(" JOIN mst_dcps_dummy_office DF ON DF.dummyOffice_id = EM.curr_off");
			lSBQuery.append(" WHERE DF.dummyOffice_id = '" + dummyOfficeId + "'");
			Query stQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lLstDummyOfficeDtls = stQuery.list();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);
		}
		return lLstDummyOfficeDtls;
	}

	public String getSchemeCodeForBillGroupId(Long billGroupId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<String> tempList = new ArrayList();
		String schemeCode = null;

		lSBQuery
				.append("Select dcpsDdoSchemeCode FROM MstDcpsBillGroup WHERE dcpsDdoBillGroupId = :billGroupId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("billGroupId", billGroupId);

		tempList = lQuery.list();
		
		if(tempList.size()!= 0 )
		{
			schemeCode = tempList.get(0).toString();
		}
		
		return schemeCode;

	}

}
