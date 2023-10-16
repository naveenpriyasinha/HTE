package com.tcs.sgv.dcps.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;

public interface MapDiseCodeToMultiDDODAO extends GenericDao {

	List getDiseForAutoComplete(String strFirstDise);

	List getOfficeName(String diseCode);

	void updateMapDise(String diseCode);

	List getSchoolList(String adminType, String district, String reptDDo);

	List getAllDistrict();

	List getAllAdminType();

	List getReptDDO(String adminType, String districtSelected);

	List getFinalDDo(String adminTypeSelected, String districtSelected);

	void updateDetails(String string, String string2, String string3);

	List getDistOfcList(String adminTypeSelected, String districtSelected, String regionSel);

	void allowDistrictOffice(String allowedOffice, String allOffice);

	void actDeactMDC(String typeOfAction);

	void insertActPeriodForMDC(String typeOfAction, String mdcFromDate, String mdcToDate);

	String updateMDCStatus();

	List getTotalSchoolsConfigReagonWise(String fromDate, String toDate);

	List getApprovedSchoolsReagonWise(String fromDate, String toDate);

	List getPendingSchoolsReagonWise(String fromDate, String toDate);

	List getRejectedSchoolsReagonWise(String fromDate, String toDate);

	List getDataEntryInitiatedSchoolsReagonWise(String fromDate, String toDate);

	List getTotalEmpConfig();

	List getDraftForms();

	List getForwardedForms();

	List getApprovedForms();

	List getRejectedForms();

	List definedLevel2DDOList(String adminTypeSelected, String districtSelected);

	List getAllRegion();

	List getTotalSchoolsConfig();

	List getApprovedSchools();

	List getPendingSchools();

	List getRejectedSchools();

	List getDataEntryInitiatedSchools();

	String getSchoolNameForDiseCode(String diseCode, String ddoCode);

	void insertDiseCodeDetails(String schoolName, String diseCode, String officeCode, String districtId);

	void updateDiseCodeDetails(String schoolName, String diseCode);

	List getSchoolList(String ddoCode);

	void updateDiseDetails(String string, String string2, String string3, String string4);

	int getCOunt(String string, String ddoCode);


	List getGRlList();


	List getDistListForRegion(String strRegion);

	void createNewGR(String grName, String grCreatedDate,
			long attachment_Id_order);

	
	



}
