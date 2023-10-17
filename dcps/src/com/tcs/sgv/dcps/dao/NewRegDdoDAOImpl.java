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

import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpDetails;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmpDetails;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class NewRegDdoDAOImpl extends GenericDaoHibernateImpl implements
		NewRegDdoDAO {

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	/**
	 * 
	 * @param type
	 * @param sessionFactory
	 */
	public NewRegDdoDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}

	/**
	 * DAO method to used to get the Bank Names according from database
	 * 
	 * @param
	 * @return List
	 */

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

	public List getOfficeDetails(Long lLngOfficeId) {

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery
				.append("SELECT dcpsDdoOfficeAddress1,dcpsDdoOfficeTelNo1,dcpsDdoOfficeTelNo2,dcpsDdoOfficeFax,dcpsDdoOfficeEmail,dcpsDdoOfficeAddress2,dcpsDdoOfficeCityClass from DdoOffice where dcpsDdoOfficeIdPk = :officeId");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("officeId", lLngOfficeId);

		List resultList = query.list();
		return resultList;
	}

	/**
	 * DAO method to used to get the designation based on office names
	 * 
	 * @param String
	 * @return List
	 */
	public List getDesignations(String lStrCurrOffice) throws Exception {

		List<Object> lLstReturnList = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery
					.append("SELECT OD.designationName FROM RltOfficeDesig OD,MstOffice OM ");
			lSBQuery
					.append(" WHERE OM.officeId = OD.officeId AND OM.officeName = :officeName");
			Session lObjSession = getReadOnlySession();
			Query lObjQuery = lObjSession.createQuery(lSBQuery.toString());
			lObjQuery.setParameter("officeName", lStrCurrOffice);

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			if (lLstResult != null && lLstResult.size() != 0) {
				lLstReturnList = new ArrayList<Object>();

				String lStrDesigName = null;
				for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
					lStrDesigName = (String) lLstResult.get(liCtr);
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(lStrDesigName);
					lObjComboValuesVO.setDesc(lStrDesigName);

					lLstReturnList.add(lObjComboValuesVO);
				}
			} else {
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId("-1");
				lObjComboValuesVO.setDesc("--Select--");
				lLstReturnList.add(lObjComboValuesVO);
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
			throw e;
		}

		return lLstReturnList;

	}

	/**
	 * DAO method to used to get the Bank Names according from database
	 * 
	 * @param
	 * @return List
	 */

	/**
	 * DAO method to used to get the group names based on cadre
	 * 
	 * @param String
	 * @return List
	 */

	public List getGroupName(Long cadreId) {

		Session hibSession = getSession();
		String query = "select CLM.lookupName,CD.superAntunAge FROM DcpsCadreMst CD,CmnLookupMst CLM WHERE CD.groupId=CLM.lookupId AND CD.cadreId= :cadreId";
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		List lLstGroupsSuperAnnAges = null;
		Query selectQuery = hibSession.createQuery(sb.toString());
		selectQuery.setParameter("cadreId", cadreId);
		lLstGroupsSuperAnnAges = selectQuery.list();
		return lLstGroupsSuperAnnAges;
	}

	/**
	 * DAO method to used to get the group names based on cadre
	 * 
	 * @param Integer
	 *            ,String
	 * @return List
	 */

	/**
	 * DAO method to used to get the nominee details for the employee
	 * 
	 * @param String
	 * @return List
	 */
	public List getNominees(String empId) {

		getSession();
		StringBuilder lSBQuery = new StringBuilder();

		List<MstEmpNmn> NomineesList = null;

		lSBQuery.append(" FROM MstEmpNmn WHERE dcpsEmpId.dcpsEmpId = :empId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("empId", Long.parseLong(empId));

		NomineesList = lQuery.list();

		return NomineesList;
	}

	/**
	 * DAO method to used to get the count of the employees with the same name,
	 * designation and department useful for generation of DCPS ID
	 * 
	 * @param String
	 *            , String, String, String, String
	 * @return List
	 */

	public Long getCountOfEmpOfSameNameAndDesigAndSameDept(String lStrEmpName,
			String lStrDesig, String lStrDept) {

		getSession();
		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long count = 0L;

		lSBQuery.append(" select count(*) FROM MstEmp WHERE name = :Fname");
		lSBQuery.append(" and designation = :designation");
		lSBQuery.append(" and parentDept = :ParentDept");
		lSBQuery.append(" and regStatus = :regStatus");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("Fname", lStrEmpName);
		lQuery.setParameter("designation", lStrDesig);
		lQuery.setParameter("ParentDept", lStrDept);
		lQuery.setParameter("regStatus", 1L);

		tempList = lQuery.list();
		count = tempList.get(0);
		return count;

	}

	public void deleteNomineesForGivenEmployee(Long lLongEmpId) {

		getSession();
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery
				.append(" delete from MstEmpNmn where dcpsEmpId.dcpsEmpId = :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", lLongEmpId);
		lQuery.executeUpdate();

	}
	
	public void deleteRltPayrollEmpForGivenEmployee(Long lLongEmpId) {

		getSession();
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery
				.append(" delete from RltDcpsPayrollEmp where dcpsEmpId = :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", lLongEmpId);
		lQuery.executeUpdate();

	}

	public void updatePhyStatus(Long lLongEmpId) {

		getSession();
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery
				.append(" Update RltPhyFormStatus SET PhyFormRcvd = :RltPhyFormStatus where dcpsEmpId = :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("RltPhyFormStatus", 0L);
		lQuery.setParameter("dcpsEmpId", lLongEmpId);
		lQuery.executeUpdate();

	}

	public List getAllDcpsEmployees(String lStrUser, String lStrPostId,
			String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		List<MstEmp> EmpList = null;

		if (lStrUser.equals("Asst")) {
			lSBQuery
					.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.regStatus,EM.sentBackRemarks");
			lSBQuery
					.append(" FROM MstEmp EM where EM.regStatus IN (0,-1) AND EM.formStatus = :formStatus and EM.ddoCode= :ddoCode order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks ");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("formStatus", 0L);
			lQuery.setParameter("ddoCode", lStrDdoCode);
		} else if (lStrUser.equals("DDO")) {
			lSBQuery
					.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.regStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender");
			lSBQuery
					.append(" FROM MstEmp EM, WfJobMst WJ, OrgDesignationMst DM");
			lSBQuery
					.append(" WHERE WJ.jobRefId = EM.dcpsEmpId AND WJ.lstActPostId = :postId AND EM.regStatus = :regStatus AND  WJ.wfDocMst.docId = :docId ");
			lSBQuery
					.append(" AND EM.designation=DM.dsgnId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("regStatus", 0L);
			lQuery.setParameter("postId", lStrPostId);
			lQuery.setParameter("docId", 700001L);
		}

		EmpList = lQuery.list();

		return EmpList;
	}

	public List getAllDcpsEmployeesForDesig(String lStrUser, String lStrPostId,
			String lStrDdoCode, String lStrSearchValue) {

		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		List<MstEmp> EmpList = null;

		lSBQuery
				.append("Select EM.dcpsEmpId, EM.name,EM.dob,EM.regStatus, EM.sentBackRemarks");
		lSBQuery
				.append(" FROM MstEmp EM where regStatus IN (0,-1) AND EM.formStatus = :formStatus and EM.ddoCode= :ddoCode ");
		lSBQuery.append(" and EM.designation = :designation");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("designation", lStrSearchValue.trim());
		lQuery.setParameter("formStatus", 0L);
		lQuery.setParameter("ddoCode", lStrDdoCode);

		EmpList = lQuery.list();

		return EmpList;
	}

	public List getAllDcpsEmployeesForCaseStatus(String lStrUser,
			String lStrPostId, String lStrDdoCode, String lStrSearchValue) {

		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		List<MstEmp> EmpList = null;

		lSBQuery
				.append("Select EM.dcpsEmpId, EM.name,EM.dob,EM.regStatus, EM.sentBackRemarks");
		lSBQuery
				.append(" FROM MstEmp EM where EM.formStatus = :formStatus and EM.ddoCode= :ddoCode ");

		if (lStrSearchValue.trim().equals("Draft")) {
			lSBQuery.append(" and EM.regStatus = 0");
		}
		if (lStrSearchValue.trim().equals("Rejected")) {
			lSBQuery.append(" and EM.regStatus = -1");
		}

		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("formStatus", 0L);
		lQuery.setParameter("ddoCode", lStrDdoCode);

		EmpList = lQuery.list();

		return EmpList;
	}

	public DdoOffice getDdoOfficeVO(Long ddoOfficeId) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("FROM DdoOffice");
		lSBQuery.append(" WHERE dcpsDdoOfficeIdPk = :ddoOfficeId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoOfficeId", ddoOfficeId);

		DdoOffice lObjDdoOffice = (DdoOffice) lQuery.uniqueResult();

		return lObjDdoOffice;
	}

	public MstEmp getEmpVOForEmpId(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("FROM MstEmp");
		lSBQuery.append(" WHERE dcpsEmpId = :dcpsEmpId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		MstEmp lObjEmpVO = (MstEmp) lQuery.uniqueResult();

		return lObjEmpVO;
	}
	
	public MstEmpDetails getEmpVOArchivedForEmpId(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("FROM MstEmpDetails");
		lSBQuery.append(" WHERE dcpsEmpId = :dcpsEmpId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		MstEmpDetails lObjEmpVO = (MstEmpDetails) lQuery.uniqueResult();

		return lObjEmpVO;
	}

	public MstEmp getEmpVOForDCPSId(String dcpsId,String ddoCode) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("FROM MstEmp");
		lSBQuery.append(" WHERE dcpsId = :dcpsId and ddoCode = :ddoCode");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsId", dcpsId);
		lQuery.setParameter("ddoCode", ddoCode);

		MstEmp lObjEmpVO = (MstEmp) lQuery.uniqueResult();

		return lObjEmpVO;
	}

	public Boolean checkDcpsEmpPayrollIdForEmpIdExists(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = true;

		lSBQuery
				.append(" select dcpsPayrollEmpId FROM RltDcpsPayrollEmp WHERE dcpsEmpId = :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		tempList = lQuery.list();
		if (tempList.size() == 0) {
			flag = false;
		}
		return flag;

	}

	public Long getDcpsEmpPayrollIdForEmpId(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long dcpsPayrollEmpId = 0L;

		lSBQuery
				.append(" select dcpsPayrollEmpId FROM RltDcpsPayrollEmp WHERE dcpsEmpId = :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		tempList = lQuery.list();
		dcpsPayrollEmpId = tempList.get(0);
		return dcpsPayrollEmpId;

	}

	public Long getTotalEmployees() {

		getSession();
		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long count = 0L;

		lSBQuery.append(" select count(*) FROM MstEmp ");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());

		tempList = lQuery.list();
		count = tempList.get(0);
		return count;

	}

	public RltDcpsPayrollEmp getPayrollVOForEmpId(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		RltDcpsPayrollEmp lObjPayrollVO = null;
		Query lQuery = null;

		lSBQuery.append("FROM RltDcpsPayrollEmp");
		lSBQuery.append(" WHERE dcpsEmpId = :dcpsEmpId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		lObjPayrollVO = (RltDcpsPayrollEmp) lQuery.uniqueResult();

		return lObjPayrollVO;
	}
	
	//Added by Kinjal for Change Details
	public RltDcpsPayrollEmpDetails getPayrollDetailsVOForEmpId(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		RltDcpsPayrollEmpDetails lObjPayrollVO = null;
		Query lQuery = null;

		lSBQuery.append("FROM RltDcpsPayrollEmpDetails");
		lSBQuery.append(" WHERE dcpsEmpId = :dcpsEmpId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		lObjPayrollVO = (RltDcpsPayrollEmpDetails) lQuery.uniqueResult();

		return lObjPayrollVO;
	}

	public List getApprovalByDDODatesforAll(String lStrDDODode,
			String lStrPostId) {

		List listAllForms = null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery
				.append("SELECT EM.dcpsEmpId,EM.approvalByDDODate FROM MstEmp EM, WfJobMst wf");
		lSBQuery
				.append(" WHERE EM.ddoCode =:ddoCode AND wf.jobRefId = EM.dcpsEmpId AND wf.lstActPostId = :postId ");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("postId", lStrPostId);
		lQuery.setParameter("ddoCode", lStrDDODode);
		listAllForms = lQuery.list();

		return listAllForms;
	}

	public List getAllPayScales() {

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		String query = "select payScaleId,payDescription from MstDcpsPayscale";

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

	public List getFormListForDDO(String lStrDDOCode) {

		List listAllForms = null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery
				.append(" SELECT EM.ddoCode,EM.dcpsEmpId,EM.name,EM.phyRcvdFormStatus,EM.regStatus,EM.formStatus,OM.dsgnName,DO.dcpsDdoOfficeName,EM.gender,EM.dob,EM.dcpsId ");
		lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst OM,DdoOffice DO,ZpRltDdoMap zp ");
		lSBQuery
				.append(" WHERE EM.designation = OM.dsgnId and EM.ddoCode = zp.ZP_DDO_CODE AND DO.dcpsDdoOfficeIdPk = EM.currOff AND zp.REPT_DDO_CODE = :ddoCode AND EM.regStatus IN (0,-1) order by EM.name,EM.ddoCode,EM.dcpsEmpId,EM.phyRcvdFormStatus,EM.regStatus,EM.formStatus,OM.dsgnName,DO.dcpsDdoOfficeName,EM.gender,EM.dob,EM.dcpsId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDDOCode);
		listAllForms = lQuery.list();

		return listAllForms;
	}

	public List getApprovedFormsForDDO(String lStrDDOCode) {

		List listAllForms = null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery
				.append(" SELECT EM.dcps_Emp_Id,EM.emp_name,EM.DCPS_ID,OM.dsgn_Name,DO.OFF_NAME,EM.gender,EM.dob,EM.sevarth_Id,Em.ddo_Code ");
		lSBQuery.append(" FROM Mst_dcps_Emp EM,Org_Designation_Mst OM,mst_dcps_ddo_office DO ");
		lSBQuery.append(" WHERE EM.designation = OM.dsgn_Id AND DO.DCPS_DDO_OFFICE_MST_ID = EM.curr_Off AND EM.ddo_Code in (select ZP_DDO_CODE from rlt_zp_ddo_map where ((REPT_DDO_CODE='"+lStrDDOCode+"') or (final_ddo_code='"+lStrDDOCode+"') or (Special_DDO_CODE='"+lStrDDOCode+"'))) AND EM.reg_Status in (1,2) ");
		
		lSBQuery.append(" order by EM.reg_Status,EM.dcps_Emp_Id,EM.emp_name,EM.dob,EM.sentBack_Remarks,EM.gender");
	
		
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		//lQuery.setParameter("ddoCode", lStrDDOCode);
		listAllForms = lQuery.list();

		return listAllForms;
	}
	public List getAllApprovedEmpsUnderDDO(String lStrDDOCode,String lStrSevaarthId,String lStrName) {

		List listAllApprovedEmps = null;
		StringBuilder lSBQuery = new StringBuilder();
		Date lDtCurrDate = SessionHelper.getCurDate();
		
		lSBQuery.append(" SELECT EM.dcpsEmpId,EM.name,EM.sevarthId,EM.ddoAsstOrNot,EM.orgEmpMstId,OU.userName ");
		lSBQuery.append(" FROM MstEmp EM,OrgEmpMst OE,OrgUserMst OU ");
		lSBQuery.append(" WHERE EM.ddoCode = :ddoCode AND EM.regStatus in (1,2) ");
		lSBQuery.append(" AND OE.empId = EM.orgEmpMstId ");
		lSBQuery.append(" AND OE.orgUserMst.userId = OU.userId");
		lSBQuery.append(" AND EM.orgEmpMstId is not null");
		
		if(lStrSevaarthId != null)
		{
			if(!"".equals(lStrSevaarthId))
			{
				lSBQuery.append(" AND EM.sevarthId = :sevarthId ");
			}
		}
		if(lStrName != null)
		{
			if(!"".equals(lStrName))
			{
				lSBQuery.append(" AND EM.name = :name ");
			}
		}
		
		lSBQuery.append(" AND ( EM.servEndDate is null or EM.servEndDate  >= :currentDate ) ");
		
		lSBQuery.append(" order by EM.ddoAsstOrNot,EM.name,EM.sevarthId,EM.dcpsEmpId,EM.orgEmpMstId,OU.userName ");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDDOCode);
		lQuery.setDate("currentDate", lDtCurrDate);
		
		if(lStrSevaarthId != null)
		{
			if(!"".equals(lStrSevaarthId))
			{
				lQuery.setParameter("sevarthId", lStrSevaarthId.trim());
			}
		}
		if(lStrName != null)
		{
			if(!"".equals(lStrName))
			{
				lQuery.setParameter("name", lStrName.trim());
			}
		}
		
		listAllApprovedEmps = lQuery.list();

		return listAllApprovedEmps;
	}

	public List getDesigsForAutoComplete(String searchKey) {
		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;

		sb
				.append("select dsgnId,dsgnName from OrgDesignationMst where dsgnName LIKE :searchKey ");
		selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("searchKey", searchKey + '%');

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
				cmbVO.setId(obj[1].toString());
				cmbVO.setDesc(obj[1].toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}

	public String getDistrictForDDO(String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();
		List<String> tempList = new ArrayList();
		String lStrDistrict = null;

		lSBQuery
				.append(" Select CD.districtName FROM OrgDdoMst OD,CmnLocationMst CL,CmnDistrictMst CD WHERE OD.locationCode = CL.locId and CL.locDistrictId = CD.districtId and OD.ddoCode = :lStrDdoCode");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("lStrDdoCode", lStrDdoCode.trim());

		tempList = lQuery.list();

		if (tempList.size() != 0) {
			lStrDistrict = tempList.get(0);
		} else {
			lStrDistrict = "";
		}

		return lStrDistrict;
	}
	
	public void lockAccountForOrgEmpId (Long lLongOrgEmpMstId) {

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" update org_user_mst set ACTIVATE_FLAG = 2 where USER_ID = (select USER_ID from org_emp_mst where EMP_ID = " + lLongOrgEmpMstId + ")");
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		lQuery.executeUpdate();
	}
	
	public void unlockAccountForOrgEmpId (Long lLongOrgEmpMstId) {

		StringBuilder lSBQuery = new StringBuilder();
		//lSBQuery.append(" update org_user_mst set ACTIVATE_FLAG = 1,PWDCHANGED_DATE = null where USER_ID = (select USER_ID from org_emp_mst where EMP_ID = " + lLongOrgEmpMstId + ")");
		lSBQuery.append(" update org_user_mst set ACTIVATE_FLAG = 1,PASSWORD = '0b76f0f411f6944f9d192da0fcbfb292' where USER_ID = (select USER_ID from org_emp_mst where EMP_ID = " + lLongOrgEmpMstId + ")");
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		lQuery.executeUpdate();
	}
	
	/*public void unlockAccountForOrgEmpId (Long lLongOrgEmpMstId) {

		StringBuilder lSBQuery = new StringBuilder();
		//lSBQuery.append(" update org_user_mst set ACTIVATE_FLAG = 1,PWDCHANGED_DATE = null where USER_ID = (select USER_ID from org_emp_mst where EMP_ID = " + lLongOrgEmpMstId + ")");
		lSBQuery.append(" update org_user_mst set ACTIVATE_FLAG = 1 where USER_ID = (select USER_ID from org_emp_mst where EMP_ID = " + lLongOrgEmpMstId + ")");
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		lQuery.executeUpdate();
	}*/
	
	public Object[] getUserNameAndPwdForEmpId(Long lLongOrgEmpId) {

		Object[] lObjUserNameAndPwd = new Object[2];
		StringBuilder lSBQuery = new StringBuilder();

		List lListUserNameAndPwd = new ArrayList();

		lSBQuery
				.append(" select user_name, password from org_user_mst where user_id in (select emp_id from org_emp_mst where emp_id = " +lLongOrgEmpId + ")");

		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		lListUserNameAndPwd = lQuery.list();

		if (lListUserNameAndPwd.size() != 0) {
			lObjUserNameAndPwd = (Object[]) lListUserNameAndPwd.get(0);
		}

		return lObjUserNameAndPwd;

	}
	
	public void updateDDOAsstStatusInMstEmp (Long lLongDcpsEmpId,String lStrRequest) {

		StringBuilder lSBQuery = new StringBuilder();
		if(lStrRequest.trim().equals("Assign"))
		{
			lSBQuery.append(" update mst_dcps_emp set DDOASST_OR_NOT = 1 where DCPS_EMP_ID = " + lLongDcpsEmpId );
		}
		else
		{
			lSBQuery.append(" update mst_dcps_emp set DDOASST_OR_NOT = null where DCPS_EMP_ID = " + lLongDcpsEmpId );
		}
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		lQuery.executeUpdate();
	}
	
	public Long getPostForEmpId(Long lLongOrgEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		List tempList = new ArrayList();
		Long postId = 0L;

		lSBQuery.append(" SELECT post_id FROM org_post_mst where POST_ID in (SELECT post_id from ORG_USERPOST_RLT where USER_ID in (select USER_ID from org_emp_mst where EMP_ID = "+ lLongOrgEmpId  + " ) and ACTIVATE_FLAG = 1 )");

		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		tempList = lQuery.list();
		postId = Long.valueOf(tempList.get(0).toString());
		return postId;

	}
	
	public Long getUserIdForEmpId(Long lLongOrgEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		List tempList = new ArrayList();
		Long userId = 0L;

		lSBQuery.append(" SELECT USER_ID FROM org_emp_mst where EMP_ID = " + lLongOrgEmpId);

		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		tempList = lQuery.list();
		userId = Long.valueOf(tempList.get(0).toString());
		return userId;

	}
	
	public Long getUserIdForPostId(Long PostId) {

		StringBuilder lSBQuery = new StringBuilder();
		List tempList = new ArrayList();
		Long userId = 0L;

		lSBQuery.append(" SELECT USER_ID FROM ORG_USERPOST_RLT where POST_ID = " + PostId + " and ACTIVATE_FLAG = 1 ");

		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		tempList = lQuery.list();
		userId = Long.valueOf(tempList.get(0).toString());
		return userId;

	}
	
	public Boolean checkEntryInRltDDOAsstTable(Long lLongAsstPostId,Long lLongDDOPostId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = true;

		lSBQuery.append(" select rltDdoAsstId FROM RltDdoAsst WHERE asstPostId = :asstPostId and ddoPostId = :ddoPostId ");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("asstPostId", lLongAsstPostId);
		lQuery.setParameter("ddoPostId", lLongDDOPostId);

		tempList = lQuery.list();
		if (tempList.size() == 0) {
			flag = false;
		}
		return flag;

	}
	
	public Boolean checkEntryInAclPostRoleTable(Long lLongAsstPostId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = true;

		lSBQuery.append(" SELECT POST_ROLE_ID FROM ACL_POSTROLE_RLT WHERE POST_ID = " + lLongAsstPostId + " and ROLE_ID = 700001 " );
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		tempList = lQuery.list();
		if (tempList.size() == 0) {
			flag = false;
		}
		return flag;

	}
	
	public AclRoleMst getRoleVOForRoleId(Long roleId) {

		StringBuilder lSBQuery = new StringBuilder();
		AclRoleMst lObjAclRoleMst = null;
		Query lQuery = null;
		List tempList = null;

		lSBQuery.append(" from AclRoleMst");
		lSBQuery.append(" WHERE roleId = :roleId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("roleId", roleId);

		tempList = lQuery.list();
		if(tempList != null && tempList.size() != 0)
		{
			lObjAclRoleMst = (AclRoleMst) tempList.get(0);
		}

		return lObjAclRoleMst;
	}
	
	public OrgPostMst getPostVOForPostId(Long postId) {

		StringBuilder lSBQuery = new StringBuilder();
		OrgPostMst lObjOrgPostMst = null;
		Query lQuery = null;
		List tempList = null;

		lSBQuery.append(" from OrgPostMst");
		lSBQuery.append(" WHERE postId = :postId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("postId", postId);

		tempList = lQuery.list();
		if(tempList != null && tempList.size() != 0)
		{
			lObjOrgPostMst = (OrgPostMst) tempList.get(0);
		}

		return lObjOrgPostMst;
	}
	
	
	public OrgUserMst getUserVOForUserId(Long userId) {

		StringBuilder lSBQuery = new StringBuilder();
		OrgUserMst lObjOrgUserMst = null;
		Query lQuery = null;
		List tempList = null;

		lSBQuery.append(" from OrgUserMst");
		lSBQuery.append(" WHERE userId = :userId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("userId", userId);

		tempList = lQuery.list();
		if(tempList != null && tempList.size() != 0)
		{
			lObjOrgUserMst = (OrgUserMst) tempList.get(0);
		}

		return lObjOrgUserMst;
	}
	
	public Boolean checkEntryInWFOrgPostMpgMst(Long lLongPostId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = true;

		lSBQuery.append(" SELECT POST_ID FROM WF_ORG_POST_MPG_MST WHERE POST_ID = " + lLongPostId);
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		tempList = lQuery.list();
		if (tempList.size() == 0) {
			flag = false;
		}
		return flag;

	}
	
	public Boolean checkEntryInWFOrgUserMpgMst(Long lLongUserId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = true;

		lSBQuery.append(" SELECT USER_ID FROM WF_ORG_USR_MPG_MST WHERE USER_ID = " + lLongUserId);
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		tempList = lQuery.list();
		if (tempList.size() == 0) {
			flag = false;
		}
		return flag;

	}
	
	public void insertWFOrgPostMpg(Long lLongPostId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		try {
			lSBQuery.append("INSERT INTO WF_ORG_POST_MPG_MST VALUES \n");
			lSBQuery.append("(:postId,:dbId,:projectId) \n"); 

			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("postId",lLongPostId );
			lQuery.setParameter("dbId", 99);
			lQuery.setParameter("projectId", 101);
			
			lQuery.executeUpdate();

		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
			throw (e);
		}
	}
	
	public void insertWFOrgUsrMpg(Long lLongUserId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		try {
			lSBQuery.append("INSERT INTO WF_ORG_USR_MPG_MST VALUES \n");
			lSBQuery.append("(:userId,:dbId,:projectId) \n"); 

			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("userId",lLongUserId);
			lQuery.setParameter("dbId", 99);
			lQuery.setParameter("projectId", 101);
			
			lQuery.executeUpdate();

		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
			throw (e);
		}
	}
	
	public void insertAclPostRoleRlt(Long lLongAclPostRoleId,Long lLongRoleIdOfDDOAsst,Long lLongPostId,Long lLongDDOPostId,Date gDtCurDate) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		
		try {
			lSBQuery.append("INSERT INTO ACL_POSTROLE_RLT VALUES \n");
			lSBQuery.append("(:postRoleId,:postId,:roleId,:startDate,:endDate,:activeFlag,:createdBy,:createdDate,:createdByPost,:updatedBy,:updatedDate,:updatedByPost) \n"); 

			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("postRoleId",lLongAclPostRoleId );
			lQuery.setParameter("postId", lLongPostId);
			lQuery.setParameter("roleId", lLongRoleIdOfDDOAsst);
			lQuery.setParameter("startDate", gDtCurDate);
			lQuery.setParameter("endDate", null );
			lQuery.setParameter("activeFlag", 1);
			lQuery.setParameter("createdBy", lLongDDOPostId);
			lQuery.setParameter("createdDate", gDtCurDate);
			lQuery.setParameter("createdByPost", lLongDDOPostId );
			lQuery.setParameter("updatedBy", null );
			lQuery.setParameter("updatedDate", null);
			lQuery.setParameter("updatedByPost", null );
			lQuery.executeUpdate();

		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
			throw (e);
		}
	}
	
	public void insertWfHierachyPostMpg(Long lLongHierarchySeqId ,Long lLongHierarchyRefId ,Long lLongPostId,Long lLongCreatedByUserId,Date gDtCurDate,Long LocId ) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		try {
			lSBQuery.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
			lSBQuery.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("hierachySeqId", lLongHierarchySeqId);
			lQuery.setParameter("parentHierachy", null );
			lQuery.setParameter("postId", lLongPostId);
			lQuery.setParameter("levelId", 10);
			lQuery.setParameter("hierachyRefId", lLongHierarchyRefId);
			lQuery.setParameter("crtUser",lLongCreatedByUserId );
			lQuery.setParameter("createdDate",gDtCurDate );
			lQuery.setParameter("lstUpdUser",null );
			lQuery.setParameter("lstUpdDate",null );
			lQuery.setParameter("startDate",gDtCurDate );
			lQuery.setParameter("endDate",null );
			lQuery.setParameter("activeFlag", 1);
			lQuery.setParameter("locId", LocId  );
			lQuery.setParameter("langId",1 );
			lQuery.setParameter("dueDays",null );

			lQuery.executeUpdate();

		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
			throw (e);
		}
	}
	
	public List<Long> getAllHierarchyRefIdsForLocation(Long LocationCode) {

		List<Long> listHierarchyRefIds = null;
		
		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" SELECT DISTINCT WFP.HIERACHY_REF_ID FROM WF_HIERACHY_POST_MPG WFP");
		lSBQuery.append(" JOIN WF_HIERARCHY_REFERENCE_MST WFR ON WFP.HIERACHY_REF_ID = WFR.HIERACHY_REF_ID ");
		lSBQuery.append(" JOIN WF_DOC_MST WFD ON WFR.DOC_ID = WFD.DOC_ID");
		lSBQuery.append(" WHERE WFD.DOC_ID in (700001,700002,700005,700006) and ");
		lSBQuery.append(" WFP.LOC_ID = " + LocationCode );

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		listHierarchyRefIds = lQuery.list();

		return listHierarchyRefIds;

	}

	public Boolean checkEntryInWfHierachyPostMpg(Long lLongHierarchyRefId,Long lLongPostId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = true;

		lSBQuery.append(" SELECT * FROM WF_HIERACHY_POST_MPG WFP where WFP.HIERACHY_REF_ID = " + lLongHierarchyRefId + " and WFP.POST_ID = '"+ lLongPostId  +"' and WFP.LEVEL_ID = 10 ");
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		tempList = lQuery.list();
		if (tempList.size() == 0) {
			flag = false;
		}
		return flag;

	}
	
	public Boolean checkIfNameExists(String lStrName) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = false;

		lSBQuery.append(" select dcpsEmpId FROM MstEmp WHERE upper(name) = :name");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("name", lStrName.trim().toUpperCase());

		tempList = lQuery.list();
		if(tempList != null)
		{
			if (tempList.size() != 0) {
				flag = true;
			}
		}
		return flag;
	}
// Added By Hemal  Suthar : START
	/*public List getAllDcpsEmployeesZP(String lStrUser, String lStrPostId,
			String lStrDdoCode,String lStrUse) {

		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		List<MstEmp> EmpList = null;

		if (lStrUser.equals("ZPDDOAsst")) {
			
			logger.info("hi i am ZPDDOAsst");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks");
			lSBQuery
			.append(" FROM MstEmp EM where EM.zpStatus IN (0,-1) AND EM.formStatus = :formStatus and EM.ddoCode= :ddoCode order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks ");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("formStatus", 0L);
			lQuery.setParameter("ddoCode", lStrDdoCode);
		} else if (lStrUser.equals("ZPDDO")) {
			
			logger.info("hi i am ZPDDO");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM");
			lSBQuery.append(" WHERE EM.zpStatus = :zpStatus AND EM.ddoCode = :ddoCode");

			lSBQuery.append(" AND EM.designation=DM.dsgnId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");

			
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("zpStatus", 2L);
			lQuery.setParameter("ddoCode", lStrDdoCode);

		} else if (lStrUser.equals("ReportingDDO") && lStrUse.equals("Forward")) {
			
			gLogger.info("hiiiiiii i m in dao");
			lSBQuery
			.append("Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender,EM.ddoCode");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM,ZpRltDdoMap ZP ");
			lSBQuery.append(" WHERE EM.ddoCode = ZP.ZP_DDO_CODE and ZP.ZPLEVEL > :zpLevel and EM.zpStatus = :zpStatus and EM.dcpsOrGpf='Y'");
			
			lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.REPT_DDO_POST_ID =:zpRepoPostId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");
		
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("zpStatus", 2L);
			lQuery.setParameter("zpLevel", 2L);
			lQuery.setParameter("zpRepoPostId", Long.valueOf(lStrPostId));
			
		}
		commented by roshan
		 * 
		 * else if (lStrUser.equals("ReportingDDO") && lStrUse.equals("Approval")) {
			
			logger.info("hi i am ReportingDDO   Approval");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender,EM.ddoCode");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM,ZpRltDdoMap ZP ");
			lSBQuery.append(" WHERE EM.ddoCode = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL = :zpLevel and EM.zpStatus = :zpStatus ");

			lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.REPT_DDO_POST_ID =:zpRepoPostId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("zpStatus", 2L);
			lQuery.setParameter("zpLevel", 2L);
			lQuery.setParameter("zpRepoPostId", Long.valueOf(lStrPostId));

		}
		
		//added by roshan
		
		else if (lStrUser.equals("ReportingDDO") && lStrUse.equals("Approval")) {
			
			logger.info("hi i am ReportingDDO   Approval");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender,EM.ddoCode");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM,ZpRltDdoMap ZP ");
			lSBQuery.append(" WHERE EM.ddoCode = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL >= :zpLevel and EM.zpStatus = :zpStatus and EM.dcpsOrGpf='N'");

			lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.REPT_DDO_POST_ID =:zpRepoPostId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("zpStatus", 2L);
			lQuery.setParameter("zpLevel", 2L);
			lQuery.setParameter("zpRepoPostId", Long.valueOf(lStrPostId));

		}
		else if (lStrUser.equals("FinalDDO") && lStrUse.equals("Forward")) {
			
			logger.info("hi i am FinalDDO  Forward");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender,EM.ddoCode,ZP.REPT_DDO_CODE");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM,ZpRltDdoMap ZP ");
			lSBQuery.append(" WHERE EM.ddoCode = ZP.ZP_DDO_CODE and ZP.ZPLEVEL >= :zpLevel and EM.zpStatus = :zpStatus ");

			lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.FINAL_DDO_POST_ID =:zpFinalPostId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("zpStatus", 3L);
			lQuery.setParameter("zpLevel", 3L);
			lQuery.setParameter("zpFinalPostId", Long.valueOf(lStrPostId));

		}
		else if (lStrUser.equals("FinalDDO") && lStrUse.equals("Approval")) {
			
			logger.info("hi i am FinalDDO Approval ");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender,EM.ddoCode,ZP.REPT_DDO_CODE");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM,ZpRltDdoMap ZP ");
			lSBQuery.append(" WHERE EM.ddoCode = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL = :zpLevel and EM.zpStatus = :zpStatus ");

			lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.FINAL_DDO_POST_ID =:zpFinalPostId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("zpStatus", 3L);
			lQuery.setParameter("zpLevel", 3L);
			lQuery.setParameter("zpFinalPostId", Long.valueOf(lStrPostId));
		}

		else if (lStrUser.equals("SpecialDDO") && lStrUse.equals("Approval")) {
			
			logger.info("hi i am SpecialDDO Approval");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender,EM.ddoCode,ZP.REPT_DDO_CODE,ZP.FINAL_DDO_CODE");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM,ZpRltDdoMap ZP ");
			lSBQuery.append(" WHERE EM.ddoCode = ZP.ZP_DDO_CODE and ZP.ZPLEVEL = :zpLevel and EM.zpStatus = :zpStatus ");

			lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.SPECIAL_DDO_POST_ID =:zpSpecialPostId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("zpStatus", 4L);
			lQuery.setParameter("zpLevel", 4L);
			lQuery.setParameter("zpSpecialPostId", Long.valueOf(lStrPostId));
		}

		EmpList = lQuery.list();
		gLogger.info("hiii size is"+EmpList.size());
		return EmpList;
	}*/
	//added by vaibhav tyagi: start
	public List getAllDcpsEmployeesZP(String lStrUser, String lStrPostId,
			String lStrDdoCode,String lStrUse,String reptddoSelected, String ddoSelected) {
		//added by vaibhav tyagi: end
		logger.info("lStrUser...."+lStrUser);
		logger.info("lStrPostId...."+lStrPostId);
		logger.info("lStrDdoCode...."+lStrDdoCode);
		logger.info("lStrUse...."+lStrUse);
		logger.info("ddoSelected...."+ddoSelected);
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		List<MstEmp> EmpList = null;

		if (lStrUser.equals("ZPDDOAsst")) {
			
			logger.info("hi i am ZPDDOAsst");
			lSBQuery.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks");
			lSBQuery.append(" FROM MstEmp EM where EM.zpStatus IN (0,-1) AND EM.formStatus = :formStatus and EM.ddoCode= :ddoCode order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks ");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("formStatus", 0L);
			lQuery.setParameter("ddoCode", lStrDdoCode);
		} else if (lStrUser.equals("ZPDDO")) {
			
			logger.info("hi i am ZPDDO");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM");
			lSBQuery.append(" WHERE EM.zpStatus = :zpStatus AND EM.ddoCode = :ddoCode");

			lSBQuery.append(" AND EM.designation=DM.dsgnId ");
			//added by vaibhav tyagi: start
			if((ddoSelected!=null)&&(ddoSelected!="")&&(Long.parseLong(ddoSelected)!=-1)){
				lSBQuery.append(" and EM.ddoCode="+ddoSelected);
			}
			//added by vaibhav tyagi: end
			lSBQuery.append(" order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");
    
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			logger.info("query is= "+lQuery.toString());
			lQuery.setParameter("zpStatus", 1L);
			lQuery.setParameter("ddoCode", lStrDdoCode);

		} else if (lStrUser.equals("ReportingDDO") && lStrUse.equals("Forward")) {
			
			logger.info("hi i am ReportingDDO   Approval");
			lSBQuery
			.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.zp_Status,EM.sentBack_Remarks,DM.dsgn_Name,EM.gender,EM.ddo_Code");
			lSBQuery.append(" FROM Mst_dcps_emp EM,Org_Designation_Mst DM,Rlt_zp_Ddo_Map ZP ");
			lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL = 2 and EM.zp_Status = 2");

			//commented by vaibhav tyagi
			//lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.REPT_DDO_POST_ID =:zpRepoPostId ");
			//added by vaibhav tyagi: start
			lSBQuery.append(" AND EM.designation=DM.dsgn_Id AND ZP.REPT_DDO_POST_ID ="+lStrPostId+" and EM.dcps_Or_Gpf='Y'");
			if((ddoSelected!=null)&&(ddoSelected!="")&&(Long.parseLong(ddoSelected)!=-1)){
				lSBQuery.append(" and EM.ddo_Code="+ddoSelected);
			}
			//added by vaibhav tyagi: end
			//lSBQuery.append(" order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");
			lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			//lQuery.setParameter("zpStatus", 2L);
			//lQuery.setParameter("zpLevel", 2L);
			//lQuery.setParameter("zpRepoPostId", Long.valueOf(lStrPostId));
			logger.info("query is=ReportingDDO  and  Forward ...for dcps employee "+lQuery.toString());

		}
	
		
		else if (lStrUser.equals("ReportingDDO") && lStrUse.equals("Approval")) {
			
			logger.info("hi i am ReportingDDO   Approval");
			lSBQuery
			.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.zp_Status,EM.sentBack_Remarks,DM.dsgn_Name,EM.gender,EM.ddo_Code");
			lSBQuery.append(" FROM Mst_dcps_emp EM,Org_Designation_Mst DM,Rlt_zp_Ddo_Map ZP ");
			lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE  and ZP.ZPLEVEL = 2 and EM.zp_Status = 2");

			//commented by vaibhav tyagi
			//lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.REPT_DDO_POST_ID =:zpRepoPostId ");
			//added by vaibhav tyagi: start
			lSBQuery.append(" AND EM.designation=DM.dsgn_Id AND ZP.REPT_DDO_POST_ID ="+lStrPostId+" and EM.dcps_Or_Gpf='N'");
			if((ddoSelected!=null)&&(ddoSelected!="")&&(Long.parseLong(ddoSelected)!=-1)){
				lSBQuery.append(" and EM.ddo_Code="+ddoSelected);
			}
			//added by vaibhav tyagi: end
			//lSBQuery.append(" order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");
			lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			//lQuery.setParameter("zpStatus", 2L);
			//lQuery.setParameter("zpLevel", 2L);
			//lQuery.setParameter("zpRepoPostId", Long.valueOf(lStrPostId));
			logger.info("query is=ReportingDDO  and  Approval ...for gpf employee "+lQuery.toString());

		}
	/*	commented by roshan
	 * else if (lStrUser.equals("FinalDDO") && lStrUse.equals("Forward")) {
			
			logger.info("hi i am FinalDDO  Forward  f");
			lSBQuery
			.append(" Select EM.dcpsEmpId,EM.name,EM.dob,EM.zpStatus,EM.sentBackRemarks,DM.dsgnName,EM.gender,EM.ddoCode");
			lSBQuery.append(" FROM MstEmp EM,OrgDesignationMst DM,ZpRltDdoMap ZP ");
			lSBQuery.append(" WHERE EM.ddoCode = ZP.ZP_DDO_CODE and ZP.ZPLEVEL > :zpLevel and EM.zpStatus = :zpStatus and ZP.REPT_DDO_CODE like '02%'");

			lSBQuery.append(" AND EM.designation=DM.dsgnId AND ZP.FINAL_DDO_POST_ID =:zpFinalPostId order by EM.regStatus,EM.dcpsEmpId,EM.name,EM.dob,EM.sentBackRemarks,DM.dsgnName,EM.gender");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("zpStatus", 3L);
			lQuery.setParameter("zpLevel", 3L);
			lQuery.setParameter("zpFinalPostId", Long.valueOf(lStrPostId));

		}*/
		//added by roshan
	else if (lStrUser.equals("FinalDDO") && lStrUse.equals("Forward")) {
			
		logger.info("hi i am final Forward  yyy");
		logger.info("hi i am FinalDDO  Forward  f");
		lSBQuery
		.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.zp_Status,EM.sentBack_Remarks,DM.dsgn_Name,EM.gender,EM.ddo_Code,zp.rept_ddo_code");
		lSBQuery.append(" FROM Mst_dcps_emp EM,Org_Designation_Mst DM,Rlt_zp_Ddo_Map ZP ");
		lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE and ZP.ZPLEVEL >= 3 and EM.zp_Status = 3 and EM.dcps_Or_Gpf='Y'");
		lSBQuery.append(" AND EM.designation=DM.dsgn_Id AND ZP.FINAL_DDO_POST_ID ="+lStrPostId+" ");
		//added by vaibhav tyagi: start  
		if((reptddoSelected!=null)&&(reptddoSelected!="")&&(Long.parseLong(reptddoSelected)!=-1)){
			lSBQuery.append(" and ZP.REPT_DDO_CODE="+reptddoSelected);
		}
		
		if((ddoSelected!=null)&&(ddoSelected!="")&&(Long.parseLong(ddoSelected)!=-1)){
			lSBQuery.append(" and EM.ddo_Code="+ddoSelected);
		}
		//added by vaibhav tyagi: end
		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		//lQuery.setParameter("zpStatus", 3L);
		//lQuery.setParameter("zpLevel", 3L);
		//	lQuery.setParameter("zpFinalPostId", Long.valueOf(lStrPostId));

		}
		//end by roshan
		else if (lStrUser.equals("FinalDDO") && lStrUse.equals("Approval")) {
			
			logger.info("hi i am FinalDDO Approval ");
			lSBQuery
			.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.zp_Status,EM.sentBack_Remarks,DM.dsgn_Name,EM.gender,EM.ddo_Code,zp.rept_ddo_code");
			lSBQuery.append(" FROM Mst_dcps_emp EM,Org_Designation_Mst DM,Rlt_zp_Ddo_Map ZP ");
			lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE and ZP.ZPLEVEL >= 3 and EM.zp_Status = 3 and EM.dcps_Or_Gpf='Y'");
			
			lSBQuery.append(" AND EM.designation=DM.dsgn_Id AND ZP.FINAL_DDO_POST_ID ="+lStrPostId+" ");
			//added by vaibhav tyagi: start  
			if((reptddoSelected!=null)&&(reptddoSelected!="")&&(Long.parseLong(reptddoSelected)!=-1)){
				lSBQuery.append(" and ZP.REPT_DDO_CODE="+reptddoSelected);
			}
			
			if((ddoSelected!=null)&&(ddoSelected!="")&&(Long.parseLong(ddoSelected)!=-1)){
				lSBQuery.append(" and EM.ddo_Code="+ddoSelected);
			}
			//added by vaibhav tyagi: end
			lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			//lQuery.setParameter("zpStatus", 3L);
			//lQuery.setParameter("zpLevel", 3L);
			//lQuery.setParameter("zpFinalPostId", Long.valueOf(lStrPostId));
		}

		else if (lStrUser.equals("SpecialDDO") && lStrUse.equals("Approval")) {
			
			logger.info("hi i am SpecialDDO Approval");
			lSBQuery
			.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.zp_Status,EM.sentBack_Remarks,DM.dsgn_Name,EM.gender,EM.ddo_Code,zp.rept_ddo_code,zp.final_ddo_code");
			lSBQuery.append(" FROM Mst_dcps_emp  EM,Org_Designation_Mst DM,Rlt_zp_Ddo_Map ZP ");
			lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE and ZP.ZPLEVEL = 4 and EM.zp_Status = 4");

			lSBQuery.append(" AND EM.designation=DM.dsgn_Id AND ZP.SPECIAL_DDO_POST_ID ="+lStrPostId+" ");
			//added by vaibhav tyagi: start
			if((ddoSelected!=null)&&(ddoSelected!="")&&(Long.parseLong(ddoSelected)!=-1)){
				lSBQuery.append(" and EM.ddo_Code="+ddoSelected);
			}
			//added by vaibhav tyagi: end
			lSBQuery.append(" order by EM.reg_Status,EM.dcps_Emp_Id,EM.emp_name,EM.dob,EM.sentBack_Remarks,DM.dsgn_Name,EM.gender");
		
			lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			gLogger.info("query is "+lSBQuery.toString());
			//lQuery.setParameter("zpStatus", 4L);
			//lQuery.setParameter("zpLevel", 4L);
			//lQuery.setParameter("zpSpecialPostId", Long.valueOf(lStrPostId));
		}

		EmpList = lQuery.list();

		return EmpList;
	}
	public List getLocationCodeForward(String EmpID)
	{
			List temp=null;
			try
			{		
				String branchQuery = "SELECT OD.LOCATION_CODE FROM mst_dcps_emp EM, org_ddo_mst OD where OD.ddo_code = EM.ddo_code and EM.dcps_emp_id ="+EmpID;
				Query sqlQuery= ghibSession.createSQLQuery(branchQuery);
				temp= sqlQuery.list();
				
				logger.error("List Size"+temp.size());
				
			}
			catch(Exception e){
				logger.error("Error in ZpDDOOfficeMstDAOImpl + getLocationCodeForward\n " + e);
				e.printStackTrace();
			}
			return temp;
		}
	
	
	public List getHirechyRefIDforForward(String POST_ID,String Subject,String locID)
	{
			List temp=null;
			try
			{		
				
				
				logger.info("POST_ID"+POST_ID);
				logger.info("Subject"+Subject);
				logger.info("locID"+locID);
				
				String branchQuery = "select mpg.HIERACHY_REF_ID from wf_hierachy_post_mpg mpg, wf_hierarchy_reference_mst mst where mpg.post_id = '"+POST_ID+"' and mst.description ='"+Subject+"' and mpg.HIERACHY_REF_ID = mst.HIERACHY_REF_ID and mst.LOC_CODE='"+locID+"'and mst.activate_flag=1 and mpg.activate_flag=1";
				Query sqlQuery= ghibSession.createSQLQuery(branchQuery);
				temp= sqlQuery.list();
				
				
				logger.error("List Size"+temp.size());
				
			}
			catch(Exception e){
				logger.error("Error in ZpDDOOfficeMstDAOImpl + getLocationCodeForward\n " + e);
				e.printStackTrace();
			}
			return temp;
		}
	

	
	
	public String getPostDesc(Long postId)
	{
			String temp=null;
			try
			{		
				String branchQuery = "SELECT POST_NAME FROM ORG_POST_DETAILS_RLT where POST_ID="+postId;
				Query sqlQuery= ghibSession.createSQLQuery(branchQuery);
				logger.error("---------------------getPostDesc "+sqlQuery.toString());
				Object postDesc= sqlQuery.uniqueResult();
				if(postDesc!=null)
					temp=postDesc.toString();
				
				logger.error("POst desc"+temp);
				
			}
			catch(Exception e){
				logger.error("Error in getPostDesc + " + e);
				e.printStackTrace();
			}
			return temp;
		}
	public long deleteNomineeDetails(String nomineeID) {
		String countOfNominee=null;
	try
	{		
		
		logger.info("nomineeID"+nomineeID);
		StringBuffer sb = new StringBuffer();
    	sb.append("delete from mst_dcps_emp_nmn where DCPS_EMP_NMN_ID="+nomineeID);
		Query sqlQuery= ghibSession.createSQLQuery(sb.toString());
		logger.error("---------------------delete nominee"+sqlQuery.toString());
		sqlQuery.executeUpdate();
		
		StringBuffer sb1 = new StringBuffer();
    	sb1.append("Select count(1) from mst_dcps_emp_nmn where DCPS_EMP_NMN_ID="+nomineeID);
		Query query= ghibSession.createSQLQuery(sb1.toString());
		logger.error("---------------------delete nominee"+query.toString());
		countOfNominee=(String) query.uniqueResult().toString();
		logger.info("no of nominne with same nomminee id is"+countOfNominee);
					
	}
	catch(Exception e){
		logger.error("Error in updationg \n " + e);
		e.printStackTrace();
	}
	return Long.valueOf(countOfNominee);}
	
	//Added by Vaibhav Tyagi :END
	//added by vaibhav tyagi: start
	public List getAllAsstDDOList(String strDdoCode){
		List asstDDO=null;
		StringBuffer sb= new StringBuffer();
		sb.append("select zp.zp_ddo_code, offc.ddo_office from rlt_zp_ddo_map zp inner join org_ddo_mst offc on offc.ddo_code=zp.zp_ddo_code where ((zp.rept_ddo_code='"+strDdoCode+"') or (zp.final_ddo_code='"+strDdoCode+"') or (zp.special_ddo_code='"+strDdoCode+"')) order by zp.zp_ddo_code asc");
		Query sqlQuery= ghibSession.createSQLQuery(sb.toString());
		logger.error("-------------query for taking data from first level***"+sqlQuery.toString());
		if(sqlQuery.list()!=null){
			asstDDO=sqlQuery.list();
		}
		return asstDDO;	
	}
	
	public List getAllAsstDDOListByFinalDDO(String strDdoCode,String reptddoSelected){
		List asstDDO=null;
		StringBuffer sb= new StringBuffer();
		sb.append("select zp.zp_ddo_code, offc.ddo_office from rlt_zp_ddo_map zp inner join org_ddo_mst offc on offc.ddo_code=zp.zp_ddo_code where zp.rept_ddo_code='"+reptddoSelected+"' and ((zp.final_ddo_code='"+strDdoCode+"') or (zp.special_ddo_code='"+strDdoCode+"')) order by zp.zp_ddo_code asc ");
		Query sqlQuery= ghibSession.createSQLQuery(sb.toString());
		logger.info("query DAO.."+sb.toString());
		if(sqlQuery.list()!=null){
			asstDDO=sqlQuery.list();
		}
		return asstDDO;
	}
	
	public List getAllReptDDOListByFinalDDO(String strDdoCode){
		List reptDDO=null;
		StringBuffer sb= new StringBuffer();
		sb.append("select ddo_code, ddo_office from org_ddo_mst where ddo_code in(select zp_ddo_code from rlt_zp_ddo_map where ((final_ddo_code='"+strDdoCode+"') or (special_ddo_code='"+strDdoCode+"') ))");
		Query sqlQuery= ghibSession.createSQLQuery(sb.toString());
		logger.info("query DAO.."+sb.toString());
		if(sqlQuery.list()!=null){
			reptDDO=sqlQuery.list();
		}
		return reptDDO;
	}
	
	//added by roshan kumar :start
	/*public List getAllDcpsEmployeesZPForBankUpdate(String ddoCode) {
		logger.info("lStrUser...."+ddoCode);
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		logger.info("hi i am ReportingDDO   Approval");
		lSBQuery
		.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.zp_Status,EM.sentBack_Remarks,Em.FATHER_OR_HUSBAND,EM.gender,EM.ddo_Code,em.zp_Status");
		lSBQuery.append(" FROM Mst_dcps_emp EM ");
		lSBQuery.append(" WHERE EM.zp_Status in (2,3,4,10)");
		lSBQuery.append(" AND em.ddo_code ='"+ddoCode+"'");
		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			
		EmpList = lQuery.list();
		logger.info("size is "+EmpList.size());
		return EmpList;
	}*/
	
	//added by roshan
	public List getEmployeeListForBasicUpdates(String strDdoCode){
		logger.info("lStrUser...."+strDdoCode);
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		logger.info("hi i am    in new reg ddo dao");
		//lSBQuery.append(" select emp.dcps_emp_id,emp.emp_name,emp.sevarth_id,emp.basic_pay,org.percentage_of_basic,emp.with_effect_from_date,eis.other_current_basic,eis.from_date,eis.to_date from mst_dcps_emp emp");
		/*Added By Shivram 20072019*/
		lSBQuery.append(" select emp.dcps_emp_id,emp.emp_name,emp.sevarth_id,case when emp.PAY_COMMISSION = 700005 then emp.SEVEN_PC_BASIC else emp.basic_pay end,org.percentage_of_basic,emp.with_effect_from_date,case when emp.PAY_COMMISSION = 700005 then eis.OTHER_SVN_PC_BASIC else eis.other_current_basic end,emp.PAY_COMMISSION from mst_dcps_emp emp");
		lSBQuery.append(" inner join org_emp_mst org on org.emp_id=emp.org_emp_mst_id " +
				" inner join hr_eis_emp_mst hr on hr.emp_mpg_id=org.emp_id" +
				" inner join hr_eis_other_dtls eis on eis.emp_id=hr.emp_id" +
				" where emp.SUPER_ANN_DATE>sysdate and emp.EMP_SERVEND_DT>sysdate and emp.zp_status=10 and emp.ddo_code='"+strDdoCode+"'");
		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			
		EmpList = lQuery.list();
		logger.info("size is "+EmpList.size());
		return EmpList;	
	}
	public void updateDetails(String empId, String perBasic, String basic, String wefDate,String payCommissionId) {
	    Session session = getSession();
	    this.logger.info("---- update org_emp_mst ---");
	    StringBuffer sb = new StringBuffer();
	    logger.info("");
	    sb.append("update org_emp_mst set percentage_of_basic=" + perBasic + " where emp_id=(select org_emp_mst_id from mst_dcps_Emp where dcps_emp_id=" + empId + ") ");
	    this.logger.info("---- update org_emp_mst ---");
	    Query query = session.createSQLQuery(sb.toString());
	    this.logger.info("---- query---" + sb);
	    query.executeUpdate();

	    this.logger.info("---- update hr_eis_other_dtls ---");
	    StringBuffer sb2 = new StringBuffer();
	    if(payCommissionId.equalsIgnoreCase("700005")){
	    	sb2.append("update HR_EIS_OTHER_DTLS set OLD_BASIC =(select SEVEN_PC_BASIC from mst_dcps_emp where dcps_emp_id=" + empId + "),OTHER_SVN_PC_BASIC =" +basic );
	    }else{
	    sb2.append("update HR_EIS_OTHER_DTLS set OLD_BASIC=(select basic_pay from mst_dcps_emp where dcps_emp_id=" + empId + ")," + "OTHER_CURRENT_BASIC=" + basic );
	    }
	    sb2.append(" where emp_id =(select emp_id from hr_eis_emp_mst " + 
	      "where EMP_MPG_ID=(select emp_id from org_emp_mst " + 
	      "where emp_id =(select org_emp_mst_id from mst_dcps_emp " + 
	      "where dcps_emp_id=" + empId + ")))");
	    Query query2 = session.createSQLQuery(sb2.toString());
	    this.logger.info("---- query---" + sb2);
	    query2.executeUpdate();

	    
	    this.logger.info("---- update mst_dcps_emp ---");
	    StringBuffer sb3 = new StringBuffer();
	    sb3.append("update mst_dcps_Emp set with_effect_from_date='" + wefDate + "' where dcps_emp_id=" + empId);
	    Query query3 = session.createSQLQuery(sb3.toString());
	    this.logger.info("---- query---" + sb3);
	    query3.executeUpdate();

	    this.logger.info("---- update org_userpost_rlt ---");
	    StringBuffer sb4 = new StringBuffer();
	    sb4.append("update org_userpost_rlt set START_DATE='" + wefDate + "' where user_id in (select user_id from org_emp_mst where emp_id in (select org_emp_mst_id from mst_dcps_emp where dcps_emp_id=" + empId + "))");
	    Query query4 = session.createSQLQuery(sb4.toString());
	    this.logger.info("---- query---" + sb4);
	    query4.executeUpdate();
	  }
	//Ended By Tejashree//

//added by roshan Kumar on 16 august
	
	public synchronized long getSequenceNO() {
		
		Long seqId=0l;
		Long nextSeqId=0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select GENERATED_ID from  CMN_TABLE_SEQ_MST where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'MST_BASIC_PAY_HISTORY'");
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		seqId = Long.valueOf(lQuery.uniqueResult().toString());
		nextSeqId=seqId+1;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CMN_TABLE_SEQ_MST SET GENERATED_ID="+nextSeqId+" where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'MST_BASIC_PAY_HISTORY'");
		Query query = ghibSession.createSQLQuery(sb.toString());
		query.executeUpdate();
		ghibSession.flush();
		return seqId;	
	}
	
	//START added by Abhishek for head of acct code update
	public List getEmpDtls(String strDDOCode) {
		gLogger.info(" In getEmpDtls");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ddo.DDO_CODE,ddo.OFF_NAME,ddo.HEAD_OF_ACCOUNT_CODE FROM MST_DCPS_DDO_OFFICE ddo ");
		sb.append(" INNER JOIN RLT_ZP_DDO_MAP ddomap on ddo.DDO_CODE = ddomap.ZP_DDO_CODE ");
		sb.append(" WHERE ddomap.REPT_DDO_CODE = '"+strDDOCode+"' ");	

		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is *******#####******"+query.toString());
		List list = query.list();
		return list;
	}
	
	
	@Override
	public void updateHeadOfAccountCode(String headOFAcctCode, String ddoCode) {

		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- updateHeadOfAccountCode DAO---");

		sb.append("update MST_DCPS_DDO_OFFICE set HEAD_OF_ACCOUNT_CODE='"+headOFAcctCode+"' where DDO_CODE= '"+ddoCode+"'");

		logger.info("---- updateHeadOfAccountCode DAo---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		query.executeUpdate();

	}
	//END added by Abhishek for head of acct code update

	public List getAllDcpsEmployeesZPForBankUpdate(String ddoCode) {
		logger.info("lStrUser...."+ddoCode);
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		logger.info("hi i am ReportingDDO   Approval");
		lSBQuery
		.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.dob,EM.zp_Status,EM.sentBack_Remarks,Em.FATHER_OR_HUSBAND,EM.gender,EM.ddo_Code,em.zp_Status,em.bank_acnt_no");
		lSBQuery.append(" FROM Mst_dcps_emp EM ");
		lSBQuery.append(" WHERE zp_status in (10) and ");
		lSBQuery.append(" em.ddo_code ='"+ddoCode+"'");
		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		logger.info("query is getAllDcpsEmployeesZPForBankUpdate"+lQuery.toString());

		EmpList = lQuery.list();
		logger.info("size is "+EmpList.size());
		return EmpList;
	}

	public List getBankNames() {
		List bankList=null;
		StringBuffer sb= new StringBuffer();
		sb.append("select bank_code, bank_name from mst_bank_pay order by bank_name");
		Query sqlQuery= ghibSession.createSQLQuery(sb.toString());
		logger.info("query DAO.."+sb.toString());
		if(sqlQuery.list()!=null){
			bankList=sqlQuery.list();
		}
		return bankList;
	}
	public List getBranchList(String cmbBank){
		List branchList=null;
		List<Object> lLstReturnList;
		StringBuffer sb= new StringBuffer();
		sb.append("select branch_code, branch_name,micr_code from rlt_bank_branch_pay where bank_code='"+cmbBank+"' order by branch_name");
		Query sqlQuery= ghibSession.createSQLQuery(sb.toString());
		logger.info("query DAO..**********"+sb.toString());
		if(sqlQuery.list()!=null){
			branchList=sqlQuery.list();
		}
		lLstReturnList = null;
		ComboValuesVO lObjComboValuesVO = null;
		if (branchList != null && branchList.size() != 0) {
			lLstReturnList = new ArrayList<Object>();
			Object obj[];
			for (int liCtr = 0; liCtr < branchList.size(); liCtr++) {

				obj = (Object[]) branchList.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());

				//String desc=obj[0].toString()+"-"+obj[1].toString();

				lObjComboValuesVO.setDesc(obj[1].toString()+"-"+obj[2].toString());
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


	public void updateBankDetails(String lngEmpID, String bankId,
			String branchId,String AccountNo) {

		try {
			logger.info("Inside updateDdoOffice....");
			logger.info("emp id is "+lngEmpID);
			logger.info("bank id is "+bankId);
			logger.info("branch id is "+branchId);

			StringBuilder SBQuery1 = new StringBuilder();
			SBQuery1.append("UPDATE MST_DCPS_EMP_DETAILS SET BANK_NAME='"+bankId+"', BRANCH_NAME='"+branchId+"',bank_acnt_no='"+AccountNo+"',IFSC_CODE=(select MICR_CODE From rlt_bank_branch_pay where branch_id='"+branchId+"')" +
					" WHERE DCPS_EMP_ID="+lngEmpID+"");
			Query lQuery1 = ghibSession.createSQLQuery(SBQuery1.toString());
			logger.info("the query is ********"+lQuery1.toString());
			lQuery1.executeUpdate();
			
			
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("UPDATE MST_DCPS_EMP SET BANK_NAME='"+bankId+"', BRANCH_NAME='"+branchId+"',bank_acnt_no='"+AccountNo+"',IFSC_CODE=(select MICR_CODE From rlt_bank_branch_pay where branch_id='"+branchId+"')" +
					" WHERE DCPS_EMP_ID="+lngEmpID+"");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			lQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
	}
	
	
	
	//Added by Kinjal for GAP
	public String getBillCreationDate(String billNo, String month, String year) {
        String ddoCode="";
       Session hibSession = getSession();
        StringBuffer queryBuffer = new StringBuffer();
        queryBuffer.append("SELECT to_char(CREATED_DATE,'dd/mm/yyyy') FROM PAYBILL_HEAD_MPG where BILL_NO = '"+billNo+"'and PAYBILL_MONTH = "+month+" and PAYBILL_YEAR= "+year+" and APPROVE_FLAG in (0,1,5)");    
        Query query = hibSession.createSQLQuery(queryBuffer.toString());
        logger.info("Query is :: "+queryBuffer.toString());
        if(query.uniqueResult()!=null){
            ddoCode = query.uniqueResult().toString();
        }
        return ddoCode;
    }
	
	// Added By Tejashree for rate per hour//
	
	public List getEmployeeListofRateperHours(String strDdoCode){
		logger.info("lStrUser...."+strDdoCode);
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List<MstEmp> EmpList = null;
		logger.info("hi Tejashree testing for getEmployeeListofRateperHours");
		lSBQuery.append(" select emp.dcps_emp_id,emp.emp_name,emp.sevarth_id,emp.basic_pay,emp.RATE_PER_HOUR,emp.working_hours,emp.with_effect_from_date,eis.other_current_basic from mst_dcps_emp emp");
		lSBQuery.append(" inner join org_emp_mst org on org.emp_id=emp.org_emp_mst_id " +
				" inner join hr_eis_emp_mst hr on hr.emp_mpg_id=org.emp_id" +
				" inner join hr_eis_other_dtls eis on eis.emp_id=hr.emp_id" +
				" where emp.zp_status=10 and emp.rate_per_hour!=0 and emp.ddo_code='"+strDdoCode+"'");
		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("query  getEmployeeListofRateperHours is"+lQuery.toString());
			
		EmpList = lQuery.list();
		logger.info("size is "+EmpList.size());
		return EmpList;	
	}
	public void updateDetailsRateperhour(String empId, String perBasic,String basic, String wefDate, String revisedBasic2){
		logger.info("Teju/pandey testing for revisedBasic2"+revisedBasic2);
		///(String empId, String rateperhours,String workinghours, String wefDate) {
			Session session=getSession();
			logger.info("---- update mst_dcps_emp ---");
			StringBuffer sb = new StringBuffer();
			sb.append("update mst_dcps_emp set RATE_PER_HOUR="+perBasic+" where dcps_emp_id="+empId+"");
			logger.info("---- update org_emp_mst ---");
			Query query = session.createSQLQuery(sb.toString());
			logger.info("---- query---"+sb);
			query.executeUpdate();
			
			
			logger.info("---- update mst_dcps_emp ---");
			StringBuffer sb2 = new StringBuffer();
			sb2.append("update mst_dcps_emp set  WORKING_HOURS="+basic+" where dcps_emp_id="+empId+"" );
			Query query2 = session.createSQLQuery(sb2.toString());
			logger.info("---- query---"+sb2);
			query2.executeUpdate();
			
			logger.info("---- update mst_dcps_emp ---");
			StringBuffer sb3 = new StringBuffer();
			sb3.append("update mst_dcps_Emp set with_effect_from_date='"+wefDate+"' where dcps_emp_id="+empId+"");		
			Query query3 = session.createSQLQuery(sb3.toString());
			logger.info("---- query---"+sb3);
			query3.executeUpdate();
			
			logger.info("---- update org_userpost_rlt ---");
			StringBuffer sb4 = new StringBuffer();
			sb4.append("update org_userpost_rlt set START_DATE='"+wefDate+"' where user_id in (select user_id from org_emp_mst where emp_id in (select org_emp_mst_id from mst_dcps_emp where dcps_emp_id="+empId+"))" );
			Query query4 = session.createSQLQuery(sb4.toString());
			logger.info("---- query---"+sb4);
			query4.executeUpdate();
			
			logger.info("---- update hr_eis_other_dtls ---");
			StringBuffer sb5 = new StringBuffer();
			sb5.append("update HR_EIS_OTHER_DTLS set OLD_BASIC=(select basic_pay from mst_dcps_emp where dcps_emp_id="+empId+")," +
					"OTHER_CURRENT_BASIC="+revisedBasic2+" " +
					"where emp_id =(select emp_id from hr_eis_emp_mst " +
					"where EMP_MPG_ID=(select emp_id from org_emp_mst " +
					"where emp_id =(select org_emp_mst_id from mst_dcps_emp " +
					"where dcps_emp_id="+empId+")))");
			Query query5 = session.createSQLQuery(sb5.toString());
			logger.info("---- query---"+sb2);
			query5.executeUpdate();
			
		}
		// Ended By Tejashree//

	@Override
	public Long chkDCPSIDalreadyExists(String dcpsId) {
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(*) FROM MST_DCPS_EMP where DCPS_ID = '"+dcpsId+"'");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is chkDCPSIDalreadyExists *************" + query.toString());
		String count = query.uniqueResult().toString();
		Long totalEmp = Long.valueOf(Long.parseLong(count));
		logger.info("totalEmp" + totalEmp);
		return totalEmp.longValue();

	}

	@Override
	public String chkempNameforDCPSIDalreadyExists(String dcpsId) {
		String temp=null;
		try
		{		
			String empName = "SELECT EMP_NAME FROM MST_DCPS_EMP where DCPS_ID = '"+dcpsId+"'";
			Query sqlQuery= ghibSession.createSQLQuery(empName);
			logger.error("---------------------chkempNameforDCPSIDalreadyExists "+sqlQuery.toString());
			Object emp= sqlQuery.uniqueResult();
			if(emp!=null)
				temp=emp.toString();
			logger.error("POst desc"+temp);
		}
		catch(Exception e){
			logger.error("Error in getPostDesc + " + e);
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public Long chkPANalreadyExists(String panNo,String dcpsEmpID) {
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(*) FROM MST_DCPS_EMP where PAN_NO = '"+panNo+"' and DCPS_EMP_ID <> '"+dcpsEmpID+"'");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is chkPANalreadyExists *************" + query.toString());
		String count = query.uniqueResult().toString();
		Long totalEmp = Long.valueOf(Long.parseLong(count));
		logger.info("totalEmp" + totalEmp);
		return totalEmp.longValue();

	}
	@Override
	public Long chkPANalreadyExistsForEmpConfig(String panNo) {
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(*) FROM MST_DCPS_EMP where PAN_NO = '"+panNo+"'");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is chkPANalreadyExistsForEmpConfig *************" + query.toString());
		String count = query.uniqueResult().toString();
		Long totalEmp = Long.valueOf(Long.parseLong(count));
		logger.info("totalEmp" + totalEmp);
		return totalEmp.longValue();
		
	}

	@Override
	public String chkempNameforPANalreadyExists(String panNo) {
		String temp=null;
		try
		{		
			String empName = "SELECT EMP_NAME FROM MST_DCPS_EMP where PAN_NO = '"+panNo+"'";
			Query sqlQuery= ghibSession.createSQLQuery(empName);
			logger.error("---------------------chkempNameforPANalreadyExists "+sqlQuery.toString());
			Object emp= sqlQuery.uniqueResult();
			if(emp!=null)
				temp=emp.toString();
			logger.error("POst desc"+temp);
		}
		catch(Exception e){
			logger.error("Error in getPostDesc + " + e);
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public Long chkPANalreadyExistsforCSRF(String panNo, String sevaarthId) {
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(*) FROM MST_DCPS_EMP where PAN_NO = '"+panNo+"' and SEVARTH_ID <> '"+sevaarthId+"'");
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is chkPANalreadyExistsforCSRF *************" + query.toString());
		String count = query.uniqueResult().toString();
		Long totalEmp = Long.valueOf(Long.parseLong(count));
		logger.info("totalEmp" + totalEmp);
		return totalEmp.longValue();

	}



}