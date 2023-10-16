package com.tcs.sgv.lna.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;

public class LNARequestProcessDAOImpl extends GenericDaoHibernateImpl implements LNARequestProcessDAO {
	Session ghibSession = null;
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/lna/LNAConstants");

	public LNARequestProcessDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public List getGPFEmployeeDetail(Long lLngEmpId) {
		List lnaEmpList = new ArrayList();
		try {

			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("select ME.name,CLM.lookupName,ME.gender,ME.basicPay,ME.orgEmpMstId,ME.group,ODM.dsgnName,");
			lSBQuery.append("HRGD.pfSeries || '/' || HRGD.gpfAccNo,ME.dob,ME.doj,ME.appointmentDate,ME.building_address,");
			lSBQuery.append("ME.building_street,ME.landmark,ME.district,CSM1.stateName,ME.pincode,ME.cntctNo,ME.cellNo,");
			lSBQuery.append("OEM.empSrvcExp,DO.dcpsDdoOfficeName,DO.dcpsDdoOfficeAddress1,DO.dcpsDdoOfficeVillage,CTM.talukaName,CDM.districtName,CSM.stateName,");
			lSBQuery.append("DO.dcpsDdoOfficeTelNo1,DO.dcpsDdoOfficeTelNo2");
			lSBQuery.append(" FROM MstEmp ME,HrPayGpfBalanceDtls HRGD,OrgEmpMst OEM,OrgDesignationMst ODM,CmnLookupMst CLM,DdoOffice DO,");
			lSBQuery.append("CmnStateMst CSM,CmnStateMst CSM1,CmnDistrictMst CDM,CmnTalukaMst CTM WHERE");
			lSBQuery.append(" CLM.lookupId = ME.payCommission AND ME.currOff = DO.dcpsDdoOfficeIdPk AND CSM1.stateId = ME.state");
			lSBQuery.append(" AND CSM.stateId = DO.dcpsDdoOfficeState AND CDM.districtId = DO.dcpsDdoOfficeDistrict AND CTM.talukaId=DO.dcpsDdoOfficeTaluka");
			lSBQuery.append(" and ME.orgEmpMstId = OEM.empId and HRGD.userId = OEM.orgUserMst.userId and ME.designation = ODM.dsgnId and OEM.empId = :empId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("empId", lLngEmpId);
			lnaEmpList = lQuery.list();

		} catch (Exception e) {

			logger.error(" Error is : " + e, e);

		}
		return lnaEmpList;
	}

	public List getDCPSEmployeeDetail(Long lLngEmpId) {
		List lnaEmpList = new ArrayList();
		try {

			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("select ME.name,CLM.lookupName,ME.gender,ME.basicPay,ME.orgEmpMstId,ME.group,ODM.dsgnName,");
			lSBQuery.append("ME.dcpsId,ME.dob,ME.doj,ME.appointmentDate,ME.building_address,");
			lSBQuery.append("ME.building_street,ME.landmark,ME.district,CSM1.stateName,ME.pincode,ME.cntctNo,ME.cellNo,");
			lSBQuery.append("OEM.empSrvcExp,DO.dcpsDdoOfficeName,DO.dcpsDdoOfficeAddress1,DO.dcpsDdoOfficeVillage,CTM.talukaName,CDM.districtName,CSM.stateName,");
			lSBQuery.append("DO.dcpsDdoOfficeTelNo1,DO.dcpsDdoOfficeTelNo2");
			lSBQuery.append(" FROM MstEmp ME,OrgEmpMst OEM,OrgDesignationMst ODM,CmnLookupMst CLM,DdoOffice DO,");
			lSBQuery.append("CmnStateMst CSM,CmnStateMst CSM1,CmnDistrictMst CDM,CmnTalukaMst CTM WHERE");
			lSBQuery.append(" CLM.lookupId = ME.payCommission AND ME.currOff = DO.dcpsDdoOfficeIdPk AND CSM1.stateId = ME.state");
			lSBQuery.append(" AND CSM.stateId = DO.dcpsDdoOfficeState AND CDM.districtId = DO.dcpsDdoOfficeDistrict AND CTM.talukaId=DO.dcpsDdoOfficeTaluka");
			lSBQuery.append(" and ME.orgEmpMstId = OEM.empId and ME.designation = ODM.dsgnId and OEM.empId = :empId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("empId", lLngEmpId);
			lnaEmpList = lQuery.list();

		} catch (Exception e) {

			logger.error(" Error is : " + e, e);

		}
		return lnaEmpList;
	}

	public List getEmployeeDetailForApprover(String lStrPostId) {
		List empListForDeoAppover = new ArrayList();
		List advanceRequestList = new ArrayList();
		List houseAdvanceRequestList = new ArrayList();
		StringBuilder lSBQueryCA = new StringBuilder();
		StringBuilder lSBQueryMA = new StringBuilder();
		StringBuilder lSBQueryHA = new StringBuilder();
		lSBQueryCA.append("select CA.transactionId,CA.applicationDate,CA.sevaarthId,ME.name,'800028',CA.computerAdvanceId,CLM.lookupName");
		lSBQueryCA.append(" FROM MstLnaCompAdvance CA,WfJobMst WJ, OrgEmpMst OEM, MstEmp ME,CmnLookupMst CLM");
		lSBQueryCA.append(" WHERE CA.sevaarthId = ME.sevarthId AND CA.statusFlag = 'F' AND OEM.empId = ME.orgEmpMstId");
		lSBQueryCA.append(" AND WJ.jobRefId = CA.computerAdvanceId AND WJ.lstActPostId = :postId AND WJ.wfDocMst.docId = :docId");
		lSBQueryCA.append(" AND CLM.lookupId = CA.advanceSubType");
		Query lQuery = ghibSession.createQuery(lSBQueryCA.toString());
		lQuery.setParameter("docId", Long.parseLong(gObjRsrcBndle.getString("LNA.CompAdvanceIDHODASST")));
		lQuery.setParameter("postId", lStrPostId);
		empListForDeoAppover = lQuery.list();

		lSBQueryMA.append("select MCA.transactionId,MCA.applicationDate,MCA.sevaarthId,ME.name,'800030',MCA.motorAdvanceId,CLM.lookupName");
		lSBQueryMA.append(" FROM MstLnaMotorAdvance MCA,WfJobMst WJ, OrgEmpMst OEM, MstEmp ME,CmnLookupMst CLM");
		lSBQueryMA.append(" WHERE MCA.sevaarthId = ME.sevarthId AND MCA.statusFlag = 'F' AND OEM.empId = ME.orgEmpMstId");
		lSBQueryMA.append(" AND WJ.jobRefId = MCA.motorAdvanceId AND WJ.lstActPostId = :postId AND WJ.wfDocMst.docId = :docId");
		lSBQueryMA.append(" AND CLM.lookupId = MCA.advanceSubType");
		Query lQueryForAdvance = ghibSession.createQuery(lSBQueryMA.toString());
		lQueryForAdvance.setParameter("docId", Long.parseLong(gObjRsrcBndle.getString("LNA.MotorAdvanceIDHODASST")));
		lQueryForAdvance.setParameter("postId", lStrPostId);
		advanceRequestList = lQueryForAdvance.list();

		lSBQueryHA.append("select HBA.transactionId,HBA.applicationDate,HBA.sevaarthId,ME.name,'800029',HBA.houseAdvanceId,CLM.lookupName");
		lSBQueryHA.append(" FROM MstLnaHouseAdvance HBA,WfJobMst WJ, OrgEmpMst OEM, MstEmp ME,CmnLookupMst CLM");
		lSBQueryHA.append(" WHERE HBA.sevaarthId = ME.sevarthId AND HBA.statusFlag = 'F' AND OEM.empId = ME.orgEmpMstId");
		lSBQueryHA.append(" AND WJ.jobRefId = HBA.houseAdvanceId AND WJ.lstActPostId = :postId AND WJ.wfDocMst.docId = :docId");
		lSBQueryHA.append(" AND CLM.lookupId = HBA.advanceSubType");
		Query lQueryForHouseAdvance = ghibSession.createQuery(lSBQueryHA.toString());
		lQueryForHouseAdvance.setParameter("docId", Long.parseLong(gObjRsrcBndle.getString("LNA.HouseAdvanceIDHODASST")));
		lQueryForHouseAdvance.setParameter("postId", lStrPostId);
		houseAdvanceRequestList = lQueryForHouseAdvance.list();

		empListForDeoAppover.addAll(advanceRequestList);
		empListForDeoAppover.addAll(houseAdvanceRequestList);
		return empListForDeoAppover;
	}

	public String getNewTransactionId(String lStrSevaarthId, Long lLngAdvanceType) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList<Long>();
		String lStrTrnsId = "";
		String lStrMonth = "";
		Long count = 0L;

		Calendar cal = Calendar.getInstance();

		Integer lIntMonth = cal.get(Calendar.MONTH) + 1;
		Integer lIntYear = cal.get(Calendar.YEAR);
		if (lIntMonth.toString().length() == 1) {
			lStrMonth = "0" + lIntMonth;
		} else {
			lStrMonth = lIntMonth.toString();
		}
		lStrTrnsId = lStrSevaarthId.charAt(0) + lStrMonth + lIntYear.toString().substring(2, 4);

		lSBQuery.append(" select count(*) FROM MstLnaRequest WHERE transactionId LIKE :lStrTrnsId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("lStrTrnsId", lStrTrnsId + '%');
		tempList = lQuery.list();
		count = tempList.get(0);
		return String.format(lStrTrnsId + "%06d", count + 1);
	}

	public Boolean checkEligibilityForLNA(String lStrSevaarthId) {
		Date empDOJ;
		Integer lInt = null;
		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("select ME.doj");
			lSBQuery.append(" FROM MstEmp ME");
			lSBQuery.append(" WHERE ME.sevarthId = :SevaarthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("SevaarthId", lStrSevaarthId);
			empDOJ = (Date) lQuery.uniqueResult();
			Date futureDate = empDOJ;
			futureDate.setYear(futureDate.getYear() + 5);
			Date currDate = new Date();
			lInt = currDate.compareTo(futureDate);
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		if (lInt < 0) {
			return true;
		} else {
			return false;
		}

	}

	public HrEisScaleMst getPayScaleData(Long empId) {
		HrEisScaleMst hrOtherInfo = new HrEisScaleMst();
		String lStrQuery = "select  empLookup.hrEisSgdMpg.hrEisScaleMst  from HrEisOtherDtls as empLookup where empLookup.hrEisEmpMst.orgEmpMst.empId = :empId";
		Query lQuery = ghibSession.createQuery(lStrQuery);
		lQuery.setParameter("empId", empId);
		hrOtherInfo = (HrEisScaleMst) lQuery.uniqueResult();
		return hrOtherInfo;
	}

	public List getCheckList(String lStrSevaarthId, Long lLngReqType, Long lLngReqSubType) {
		StringBuilder lSBQuery = new StringBuilder();
		List documentCheckList = new ArrayList();
		lSBQuery.append("select checklistName,checked");
		lSBQuery.append(" FROM MstLnaDocChecklist");
		lSBQuery.append(" WHERE sevaarthID = :SevaarthId AND lnaReqType = :ReqType AND reqSubType = :ReqSubType ORDER BY MstLnaDocChecklistId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("SevaarthId", lStrSevaarthId);
		lQuery.setParameter("ReqType", lLngReqType);
		lQuery.setParameter("ReqSubType", lLngReqSubType);
		documentCheckList = lQuery.list();
		return documentCheckList;
	}

	public List getChecklistPk(String lStrSevaarthId, Long lLngRequestType, Long lLngReqSubType) {
		StringBuilder lSBQuery = new StringBuilder();
		List checkListPk = new ArrayList();
		lSBQuery.append("select MstLnaDocChecklistId");
		lSBQuery.append(" FROM MstLnaDocChecklist");
		lSBQuery.append(" WHERE sevaarthID = :SevaarthId AND lnaReqType = :ReqType AND reqSubType = :ReqSubType");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("SevaarthId", lStrSevaarthId);
		lQuery.setParameter("ReqType", lLngRequestType);
		lQuery.setParameter("ReqSubType", lLngReqSubType);
		checkListPk = lQuery.list();
		return checkListPk;
	}

	public List getEmployeeDcpsOrGpf(String lStrSevaarthId, String empName, String criteria, String lStrDdoCode, String lStrHodLocCode) {
		List lLstdcpsOrGpf;
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery;
		lSBQuery.append("select ME.dcpsOrGpf,ME.orgEmpMstId,ME.sevarthId");
		lSBQuery.append(" FROM MstEmp ME,OrgDdoMst ODM WHERE ME.ddoCode = ODM.ddoCode AND ODM.hodLocCode = :HodLocCode");
		if (criteria.equals("1")) {
			lSBQuery.append(" AND ME.sevarthId = :SevaarthId ");
			if (lStrDdoCode != null && !"".equals(lStrDdoCode)) {
				lSBQuery.append(" AND ME.ddoCode = :ddoCode");
			}
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("SevaarthId", lStrSevaarthId.toUpperCase().trim());
		} else if (criteria.equals("2")) {
			lSBQuery.append(" AND UPPER(ME.name) = :empName");
			if (lStrDdoCode != null && !"".equals(lStrDdoCode)) {
				lSBQuery.append(" AND ME.ddoCode = :ddoCode");
			}
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("empName", empName.toUpperCase().trim());
		} else {
			lSBQuery.append(" AND ME.sevarthId = :SevaarthId AND  UPPER(ME.name) = :empName");
			if (lStrDdoCode != null && !"".equals(lStrDdoCode)) {
				lSBQuery.append(" AND ME.ddoCode = :ddoCode");
			}
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("SevaarthId", lStrSevaarthId.toUpperCase().trim());
			lQuery.setParameter("empName", empName.toUpperCase().trim());
		}
		if (lStrDdoCode != null && !"".equals(lStrDdoCode)) {
			lQuery.setParameter("ddoCode", lStrDdoCode);
		}
		lQuery.setParameter("HodLocCode", lStrHodLocCode);
		lLstdcpsOrGpf = lQuery.list();
		return lLstdcpsOrGpf;
	}

	public List getEmpNameForAutoComplete(String searchKey, String lStrDdoCode, String lStrHodLocCode) {
		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		lSBQuery.append("select ME.orgEmpMstId,ME.name from MstEmp ME,OrgDdoMst ODM where UPPER(ME.name) LIKE :searchKey");
		lSBQuery.append("  AND ME.ddoCode = ODM.ddoCode AND ODM.hodLocCode = :HodLocCode");
		if (lStrDdoCode != null && !"".equals(lStrDdoCode)) {
			lSBQuery.append(" and ddoCode = :ddoCode");
		}
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("searchKey", '%' + searchKey + '%');
		lQuery.setParameter("HodLocCode", lStrHodLocCode);
		if (lStrDdoCode != null && !"".equals(lStrDdoCode)) {
			lQuery.setParameter("ddoCode", lStrDdoCode);
		}
		List resultList = lQuery.list();

		if (resultList != null && !resultList.isEmpty()) {
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

	public List getDraftRequestList(String lStrCriteria, String lStrName, Date lDtSaveDate, String lStrHodLocCode) {
		List empDraftList = new ArrayList();
		List empDraftList2 = new ArrayList();
		List empDraftList3 = new ArrayList();
		StringBuilder lSBQueryCA = new StringBuilder();
		StringBuilder lSBQueryMCA = new StringBuilder();
		StringBuilder lSBQueryHBA = new StringBuilder();
		Query lQuery;
		Query lQueryMCA;
		Query lQueryHBA;
		Date lDtToDate = null;
		if (lDtSaveDate != null) {
			lDtToDate = (Date) lDtSaveDate.clone();
			Date lDtTemp = lDtSaveDate;
			lDtToDate.setDate(lDtTemp.getDate() + 1);
		}
		lSBQueryCA.append("select ME.name,CA.sevaarthId,'800028',CLM.lookupName,CA.applicationDate,CA.statusFlag,CA.computerAdvanceId,CA.hoRemarks,CA.createdDate");
		lSBQueryCA.append(" FROM MstLnaCompAdvance CA, MstEmp ME,CmnLookupMst CLM,OrgDdoMst DDO");
		lSBQueryCA.append(" WHERE CA.sevaarthId = ME.sevarthId AND CA.statusFlag IN ('D','R')");
		lSBQueryCA.append(" AND CLM.lookupId = CA.advanceSubType");
		lSBQueryCA.append(" AND ME.ddoCode = DDO.ddoCode AND DDO.hodLocCode = :hodLocCode");
		if (lStrCriteria.equalsIgnoreCase("name")) {
			lSBQueryCA.append(" AND UPPER(ME.name) = :name ");
			lQuery = ghibSession.createQuery(lSBQueryCA.toString());
			lQuery.setParameter("name", lStrName.toUpperCase().trim());
		} else if (lStrCriteria.equalsIgnoreCase("date")) {
			lSBQueryCA.append(" AND CA.createdDate >= :fromDate AND CA.createdDate <= :toDate");
			lQuery = ghibSession.createQuery(lSBQueryCA.toString());
			lQuery.setDate("fromDate", lDtSaveDate);
			lQuery.setDate("toDate", lDtToDate);
		} else if (lStrCriteria.equalsIgnoreCase("both")) {
			lSBQueryCA.append(" AND UPPER(ME.name) = :name ");
			lSBQueryCA.append(" AND CA.createdDate >= :fromDate AND CA.createdDate <= :toDate");
			lQuery = ghibSession.createQuery(lSBQueryCA.toString());
			lQuery.setParameter("name", lStrName.toUpperCase().trim());
			lQuery.setDate("fromDate", lDtSaveDate);
			lQuery.setDate("toDate", lDtToDate);
		} else {
			lQuery = ghibSession.createQuery(lSBQueryCA.toString());
		}
		lQuery.setParameter("hodLocCode", lStrHodLocCode);
		empDraftList = lQuery.list();

		lSBQueryMCA.append("select ME.name,MCA.sevaarthId,'800030',CLM.lookupName,MCA.applicationDate,MCA.statusFlag,MCA.motorAdvanceId,MCA.hoRemarks,MCA.createdDate");
		lSBQueryMCA.append(" FROM MstLnaMotorAdvance MCA, MstEmp ME,CmnLookupMst CLM,OrgDdoMst DDO");
		lSBQueryMCA.append(" WHERE MCA.sevaarthId = ME.sevarthId AND MCA.statusFlag IN ('D','R')");
		lSBQueryMCA.append(" AND CLM.lookupId = MCA.advanceSubType");
		lSBQueryMCA.append(" AND ME.ddoCode = DDO.ddoCode AND DDO.hodLocCode = :hodLocCode");
		if (lStrCriteria.equalsIgnoreCase("name")) {
			lSBQueryMCA.append(" AND UPPER(ME.name) = :name ");
			lQueryMCA = ghibSession.createQuery(lSBQueryMCA.toString());
			lQueryMCA.setParameter("name", lStrName.toUpperCase().trim());
		} else if (lStrCriteria.equalsIgnoreCase("date")) {
			lSBQueryMCA.append(" AND MCA.createdDate >= :fromDate AND MCA.createdDate <= :toDate");
			lQueryMCA = ghibSession.createQuery(lSBQueryMCA.toString());
			lQueryMCA.setDate("fromDate", lDtSaveDate);
			lQueryMCA.setDate("toDate", lDtToDate);
		} else if (lStrCriteria.equalsIgnoreCase("both")) {
			lSBQueryMCA.append(" AND UPPER(ME.name) = :name ");
			lSBQueryMCA.append(" AND MCA.createdDate >= :fromDate AND MCA.createdDate <= :toDate");
			lQueryMCA = ghibSession.createQuery(lSBQueryMCA.toString());
			lQueryMCA.setParameter("name", lStrName.toUpperCase().trim());
			lQueryMCA.setDate("fromDate", lDtSaveDate);
			lQueryMCA.setDate("toDate", lDtToDate);
		} else {
			lQueryMCA = ghibSession.createQuery(lSBQueryMCA.toString());
		}
		lQueryMCA.setParameter("hodLocCode", lStrHodLocCode);
		empDraftList2 = lQueryMCA.list();

		lSBQueryHBA.append("select ME.name,HBA.sevaarthId,'800029',CLM.lookupName,HBA.applicationDate,HBA.statusFlag,HBA.houseAdvanceId,HBA.hoRemarks,HBA.createdDate");
		lSBQueryHBA.append(" FROM MstLnaHouseAdvance HBA, MstEmp ME,CmnLookupMst CLM,OrgDdoMst DDO");
		lSBQueryHBA.append(" WHERE HBA.sevaarthId = ME.sevarthId AND HBA.statusFlag IN ('D','R')");
		lSBQueryHBA.append(" AND CLM.lookupId = HBA.advanceSubType");
		lSBQueryHBA.append(" AND ME.ddoCode = DDO.ddoCode AND DDO.hodLocCode = :hodLocCode");
		if (lStrCriteria.equalsIgnoreCase("name")) {
			lSBQueryHBA.append(" AND UPPER(ME.name) = :name ");
			lQueryHBA = ghibSession.createQuery(lSBQueryHBA.toString());
			lQueryHBA.setParameter("name", lStrName.toUpperCase().trim());
		} else if (lStrCriteria.equalsIgnoreCase("date")) {
			lSBQueryHBA.append(" AND HBA.createdDate >= :fromDate AND HBA.createdDate <= :toDate");
			lQueryHBA = ghibSession.createQuery(lSBQueryHBA.toString());
			lQueryHBA.setDate("fromDate", lDtSaveDate);
			lQueryHBA.setDate("toDate", lDtToDate);
		} else if (lStrCriteria.equalsIgnoreCase("both")) {
			lSBQueryHBA.append(" AND UPPER(ME.name) = :name ");
			lSBQueryHBA.append(" AND HBA.createdDate >= :fromDate AND HBA.createdDate <= :toDate");
			lQueryHBA = ghibSession.createQuery(lSBQueryHBA.toString());
			lQueryHBA.setParameter("name", lStrName.toUpperCase().trim());
			lQueryHBA.setDate("fromDate", lDtSaveDate);
			lQueryHBA.setDate("toDate", lDtToDate);
		} else {
			lQueryHBA = ghibSession.createQuery(lSBQueryHBA.toString());

		}
		lQueryHBA.setParameter("hodLocCode", lStrHodLocCode);
		empDraftList3 = lQueryHBA.list();

		empDraftList.addAll(empDraftList2);
		empDraftList.addAll(empDraftList3);
		return empDraftList;

	}

	public List getEmpLoanDetails(Long lLngUserId) {
		List lLnaStatusListCA = new ArrayList();
		List lLnaStatusListHBA = new ArrayList();
		List lLnaStatusListMCA = new ArrayList();
		StringBuilder lSBQueryCA = new StringBuilder();
		StringBuilder lSBQueryHBA = new StringBuilder();
		StringBuilder lSBQueryMCA = new StringBuilder();

		lSBQueryCA.append("SELECT ME.name,CA.applicationDate,CA.sevaarthId,CA.advanceType,CLM.lookupName,CA.statusFlag");
		lSBQueryCA.append(" FROM MstEmp ME,MstLnaCompAdvance CA,CmnLookupMst CLM,OrgEmpMst ORM");
		lSBQueryCA.append(" where ORM.orgUserMst.userId = :UserId AND ORM.empId = ME.orgEmpMstId");
		lSBQueryCA.append(" AND ME.sevarthId = CA.sevaarthId AND CLM.lookupId = CA.advanceSubType ORDER BY 2");
		Query lQueryCA = ghibSession.createQuery(lSBQueryCA.toString());
		lQueryCA.setLong("UserId", lLngUserId);
		lLnaStatusListCA = lQueryCA.list();

		lSBQueryHBA.append("SELECT ME.name,HA.applicationDate,HA.sevaarthId,HA.advanceType,CLM.lookupName,HA.statusFlag");
		lSBQueryHBA.append(" FROM MstEmp ME,MstLnaHouseAdvance HA,CmnLookupMst CLM,OrgEmpMst ORM");
		lSBQueryHBA.append(" where ORM.orgUserMst.userId = :UserId AND ORM.empId = ME.orgEmpMstId");
		lSBQueryHBA.append(" AND ME.sevarthId = HA.sevaarthId AND CLM.lookupId = HA.advanceSubType ORDER BY 2");
		Query lQueryHBA = ghibSession.createQuery(lSBQueryHBA.toString());
		lQueryHBA.setLong("UserId", lLngUserId);
		lLnaStatusListHBA = lQueryHBA.list();

		lSBQueryMCA.append("SELECT ME.name,MA.applicationDate,MA.sevaarthId,MA.advanceType,CLM.lookupName,MA.statusFlag");
		lSBQueryMCA.append(" FROM MstEmp ME,MstLnaMotorAdvance MA,CmnLookupMst CLM,OrgEmpMst ORM");
		lSBQueryMCA.append(" where ORM.orgUserMst.userId = :UserId AND ORM.empId = ME.orgEmpMstId");
		lSBQueryMCA.append(" AND ME.sevarthId = MA.sevaarthId AND CLM.lookupId = MA.advanceSubType  ORDER BY 2");
		Query lQueryMCA = ghibSession.createQuery(lSBQueryMCA.toString());
		lQueryMCA.setLong("UserId", lLngUserId);
		lLnaStatusListMCA = lQueryMCA.list();

		lLnaStatusListCA.addAll(lLnaStatusListHBA);
		lLnaStatusListCA.addAll(lLnaStatusListMCA);

		return lLnaStatusListCA;
	}

	public List getEmpBankDetails(String lStrSevaarthId) {
		List lLstEmpBankDtls = new ArrayList();
		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT MBP.bankName,RBB.branchName,ME.bankAccountNo,ME.IFSCCode");
			lSBQuery.append(" FROM MstEmp ME,RltBankBranchPay RBB,MstBankPay MBP");
			lSBQuery.append(" WHERE ME.sevarthId = :SevaarthId and MBP.bankCode = ME.bankName and RBB.branchId = ME.branchName");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("SevaarthId", lStrSevaarthId);
			lLstEmpBankDtls = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstEmpBankDtls;
	}

}
