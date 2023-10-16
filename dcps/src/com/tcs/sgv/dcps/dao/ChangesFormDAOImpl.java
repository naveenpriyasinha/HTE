/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 18, 2011		Kapil Devani								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.HstDcpsChanges;
import com.tcs.sgv.dcps.valueobject.HstDcpsNomineeChanges;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.TrnDcpsChanges;

/**
 * Class Description -
 * 
 * 
 * @author Kapil Devani
 * @version 0.1
 * @since JDK 5.0 Mar 18, 2011
 */
public class ChangesFormDAOImpl extends GenericDaoHibernateImpl implements
		ChangesFormDAO {

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	/**
	 * @param type
	 */
	public ChangesFormDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	public List getAllDcpsEmployees(String lStrDesignationId, String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();

		List<MstEmp> EmpList = null;

		lSBQuery.append("select dcpsEmpId,name,dcpsId,sevarthId FROM MstEmp where regStatus IN (1,2) and ddoCode = :ddoCode");
		logger.info("lStrDdoCode -----------------> "+lStrDdoCode);
		if((lStrDesignationId!=null)&&(lStrDesignationId!="")&&(Long.parseLong(lStrDesignationId)!=-1)){
		lSBQuery.append(" and designation="+lStrDesignationId+"");
		logger.info("designation -----------------> "+lStrDesignationId);
		}
		lSBQuery.append(" order by name");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		logger.info("lQuery -----------------> "+lQuery.toString());
		//lQuery.setString("designationId", lStrDesignationId);
		lQuery.setString("ddoCode", lStrDdoCode);

		EmpList = lQuery.list();

		return EmpList;
	}

	public MstEmp getEmpDetails(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();

		MstEmp EmpList = null;

		lSBQuery.append("FROM MstEmp where dcpsEmpId = :dcpsEmpId)");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		EmpList = (MstEmp) lQuery.uniqueResult();

		return EmpList;
	}

	public List getCurrentOffices() {

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		String query = "select dcpsDdoOfficeIdPk,dcpsDdoOfficeName from DdoOffice";

		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
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

	public List getNominees(String empId) {

		StringBuilder lSBQuery = new StringBuilder();

		List<MstEmpNmn> NomineesList = null;

		lSBQuery.append(" FROM MstEmpNmn WHERE dcpsEmpId.dcpsEmpId = :empId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("empId", Long.parseLong(empId));

		NomineesList = lQuery.list();

		return NomineesList;
	}

	public List getChangesDraftsForDesig(String lStrDesignationId,
			String lStrUserType,String lstrDdoCode,String talukaId, String ddoSelected) {
		logger.info("hi i am ddoasst by roshann   "+lstrDdoCode);
		StringBuilder lSBQuery = new StringBuilder();

		List ChangesDraftsList = null;

		lSBQuery
				.append(" SELECT nvl(CN.UPDATED_DATE,CN.CREATED_DATE),EM.DCPS_ID,EM.EMP_NAME,EM.DCPS_EMP_ID,CN.TYPE_OF_CHANGES,"
						+ " CN.DCPS_CHANGES_ID,EM.SEVARTH_ID,rlt.zp_ddo_code FROM MST_DCPS_EMP EM join hst_dcps_changes CN ON CN.DCPS_EMP_ID=EM.DCPS_EMP_ID join RLT_ZP_DDO_MAP RLT ON RLT.ZP_DDO_CODE=EM.DDO_CODE inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code inner join org_ddo_mst org on org.ddo_code=off.ddo_code ");
		if((talukaId!=null)&&(talukaId!="")&&(Long.parseLong(talukaId)!=-1)){
			lSBQuery.append(" and off.taluka="+talukaId);
		}
		if((ddoSelected!=null)&&(ddoSelected!="")){
			lSBQuery.append(" and (off.ddo_code like '"+ddoSelected+"%' or lower(org.ddo_office) like lower('%"+ddoSelected+"%'))");
		}
		
		if((lStrDesignationId!=null)&&(lStrDesignationId!="")&&(Long.parseLong(lStrDesignationId)!=-1)){
			logger.info("hiii ii m roshan searching for designation");
		lSBQuery
		.append(" WHERE EM.DESIGNATION='" + lStrDesignationId + "' ");
		}
		if (lStrUserType.equals("DDOAsst")) {
			
			lSBQuery
					.append(" AND (CN.FORM_STATUS IS NULL OR CN.FORM_STATUS = -1) AND RLT.ZP_DDO_CODE='"+lstrDdoCode+"'");
		}
		if (lStrUserType.equals("DDO")) {
			lSBQuery.append(" AND (CN.FORM_STATUS = 0) AND RLT.REPT_DDO_CODE='"+lstrDdoCode+"'");
		}

		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		ChangesDraftsList = lQuery.list();
		logger.info("size of change drafts form is by roshan"+ChangesDraftsList.size());

		return ChangesDraftsList;
	}

	public HstDcpsChanges getChangesDetails(Long dcpsChangesId) {

		StringBuilder lSBQuery = new StringBuilder();

		HstDcpsChanges HstDcpsChangesObj = null;

		lSBQuery
				.append("FROM HstDcpsChanges where dcpsChangesId = :dcpsChangesId)");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);

		HstDcpsChangesObj = (HstDcpsChanges) lQuery.uniqueResult();

		return HstDcpsChangesObj;
	}

	public Long getPersonalChangesIdforChangesId(Long dcpsChangesId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long changesPersonalId = 0L;

		lSBQuery
				.append(" select dcpsPersonalChangesId FROM HstDcpsPersonalChanges WHERE dcpsChangesId = :dcpsChangesId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);

		tempList = lQuery.list();
		changesPersonalId = tempList.get(0);
		return changesPersonalId;

	}

	public RltDcpsPayrollEmp getEmpPayrollDetailsForEmpId(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();

		RltDcpsPayrollEmp EmpList = null;

		lSBQuery.append("FROM RltDcpsPayrollEmp where dcpsEmpId = :dcpsEmpId)");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		EmpList = (RltDcpsPayrollEmp) lQuery.uniqueResult();

		return EmpList;
	}

	public Long getOfficeChangesIdforChangesId(Long dcpsChangesId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long changesOfficeId = 0L;

		lSBQuery
				.append(" select dcpsOfficeChangesId FROM HstDcpsOfficeChanges WHERE dcpsChangesId = :dcpsChangesId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);

		tempList = lQuery.list();
		changesOfficeId = tempList.get(0);
		return changesOfficeId;

	}

	public Long getOtherChangesIdforChangesId(Long dcpsChangesId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long changesOtherId = 0L;

		lSBQuery
				.append(" select dcpsOtherChangesId FROM HstDcpsOtherChanges WHERE dcpsChangesId = :dcpsChangesId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);

		tempList = lQuery.list();
		changesOtherId = tempList.get(0);
		return changesOtherId;

	}

	public void deleteNomineesFromHstForGivenEmployee(Long lLongDcpsHstChangesId) {

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery
				.append(" delete from HstDcpsNomineeChanges where dcpsChangesId = :dcpsChangesId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesId", lLongDcpsHstChangesId);
		lQuery.executeUpdate();
	}

	public Long getLatestRefIdForNomineeChanges(Long dcpsEmpId,
			Long dcpsChangesId) {

		getSession();
		StringBuilder lSBQuery = new StringBuilder();
		Long maxRefId = null;

		lSBQuery
				.append(" select max(changesNomineeRefId) from HstDcpsNomineeChanges where dcpsEmpId= :dcpsEmpId and dcpsChangesId= :dcpsChangesId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);

		maxRefId = (Long) lQuery.list().get(0);

		return maxRefId;

	}

	public List getNomineesFromHst(Long changesNomineeRefId, Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();

		List<HstDcpsNomineeChanges> NomineesHstList = null;

		lSBQuery
				.append(" FROM HstDcpsNomineeChanges WHERE changesNomineeRefId = :changesNomineeRefId and dcpsEmpId = :dcpsEmpId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("changesNomineeRefId", changesNomineeRefId);
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		NomineesHstList = lQuery.list();

		return NomineesHstList;
	}

	public List getChangesFromTrnForChangesId(Long dcpsChangesId) {

		StringBuilder lSBQuery = new StringBuilder();

		List<TrnDcpsChanges> TrnDcpsChangesList = null;

		lSBQuery
				.append(" FROM TrnDcpsChanges WHERE dcpsChangesId = :dcpsChangesId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);

		TrnDcpsChangesList = lQuery.list();

		return TrnDcpsChangesList;
	}

	public Boolean checkPkInTrnExistsForTheChange(String fieldName,
			String oldValue, Long dcpsChangesId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = true;

		lSBQuery
				.append(" select dcpsChangesIdPk FROM TrnDcpsChanges WHERE fieldName = :fieldName and newValue = :oldValue and dcpsChangesId = :dcpsChangesId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("fieldName", fieldName);
		lQuery.setParameter("oldValue", oldValue);
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);

		tempList = lQuery.list();
		if (tempList.size() == 0 && !oldValue.equals("")) {
			flag = false;
		}
		return flag;
	}

	public Long getPksFromTrnForTheChange(String fieldName, String oldValue,
			Long dcpsChangesId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long trnPkId = 0L;
		Query lQuery;

		if (oldValue.equals("")) {
			lSBQuery
					.append(" select dcpsChangesIdPk FROM TrnDcpsChanges WHERE fieldName = :fieldName and dcpsChangesId = :dcpsChangesId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
		} else {
			lSBQuery
					.append(" select dcpsChangesIdPk FROM TrnDcpsChanges WHERE fieldName = :fieldName and newValue = :oldValue and dcpsChangesId = :dcpsChangesId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("oldValue", oldValue);
		}

		lQuery.setParameter("fieldName", fieldName);
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);

		tempList = lQuery.list();
		trnPkId = tempList.get(0);
		return trnPkId;
	}

	public Long getPkFromTrnForTheChangeInPhotoSign(Long dcpsChangesId,
			String fieldName) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long trnPkId = 0L;

		lSBQuery
				.append(" select dcpsChangesIdPk FROM TrnDcpsChanges WHERE fieldName = :fieldName and dcpsChangesId = :dcpsChangesId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);
		lQuery.setParameter("fieldName", fieldName);

		tempList = lQuery.list();
		trnPkId = tempList.get(0);
		return trnPkId;
	}

	public void deleteTrnVOForPk(Long TrnIdPk) {

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery
				.append(" delete from TrnDcpsChanges where dcpsChangesIdPk = :dcpsChangesIdPk");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesIdPk", TrnIdPk);
		lQuery.executeUpdate();
	}

	public void deleteTrnVOForDcpsChangesId(Long dcpsChangesId) {

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery
				.append(" delete from TrnDcpsChanges where dcpsChangesId = :dcpsChangesId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsChangesId", dcpsChangesId);
		lQuery.executeUpdate();
	}

	public Date getDobForTheEmployee(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Date> tempList = new ArrayList();
		Date dob = null;

		lSBQuery.append(" select dob FROM MstEmp WHERE dcpsEmpId = :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);
		logger.info("dob is "+lSBQuery.toString());

		tempList = lQuery.list();
		dob = tempList.get(0);
		return dob;

	}

	public Long getNextRefIdForHstNomineeChanges(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		Long count = null;

		lSBQuery
				.append(" select max(changesNomineeRefId) from HstDcpsNomineeChanges where dcpsEmpId= :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		count = (Long) lQuery.list().get(0);

		if (count == null) {
			count = 0l;
		}
		count = count + 1;
		return count;

	}

	public List getPhotoSignNewValue(Long lLngChangesId) {

		List lLstNewValue = new ArrayList<Long>();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" select newValue from TrnDcpsChanges where dcpsChangesId = :changesId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("changesId", lLngChangesId);
		lLstNewValue = lQuery.list();
		return lLstNewValue;

	}

	public String getGroupIdForCadreId(Long cadreId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<String> tempList = new ArrayList<String>();
		String groupId = null;

		lSBQuery
				.append(" Select groupId FROM DcpsCadreMst WHERE cadreId = :cadreId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("cadreId", cadreId);

		tempList = lQuery.list();
		if (tempList != null && tempList.size() != 0) {
			groupId = tempList.get(0);
		}
		return groupId;
	}

	@Override
	public String districtName(String strDdoCode) {
		String districtId="";
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get district---");
		//sb.append("SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and ((rep.REPT_DDO_POST_ID='" + postId + "') or (rep.FINAL_DDO_POST_ID='" + postId + "'))");
		//added by roshan
		sb.append("SELECT DIstrict FROM MST_DCPS_DDO_OFFICE where ddo_code="+strDdoCode+" and DDO_OFFICE='Yes'");
		//end by roshan
		logger.info("---- get district---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		districtId = (String) query.uniqueResult();
		logger.info("district name is "+districtId);
		return districtId;
		
	}

	@Override
	public List allTaluka(String districtID) {
		List talukaList=null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get Taluka list---");
		
		sb.append("SELECT TALUKA_ID,TALUKA_NAME FROM CMN_TALUKA_MST where DISTRICT_ID="+districtID);
		//end by roshan
		logger.info("---- get district---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		talukaList = query.list();
		logger.info("taluka size is "+talukaList.size());
		return talukaList;
	}

}
