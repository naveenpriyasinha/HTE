package com.tcs.sgv.common.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;

public interface CHBCalculationDAO extends GenericDao{

	String getDdoCodeForCHB(Long lngPostId);

	Long getDcpsEmpIdFromSevaarthIdOrName(String strEmpName, String strSevarthId);

	boolean checkMultipleEntryInHstEmpForEmpIdOrNot(Long longDcpsEmpId);

	List getEmpListForSelection(String strEmpName, String strSevarthId);

	List getEmpNameForAutoCompleteFORCHB(String upperCase, String strSearchType,
			String strDDOCode, String strSearchBy);

	String getDdoCodeForDDOForCHB(String strLocationCode);

	Long getRatePerHoursForCHBCalculation(String sevarthID, String txtHours);

	Long updateRatePerHoursForCHBCalculation(String sevarthID, String basicValue);

	List getMonthList();

	List getYearList();

	String getEmpId(String strEmpName);

	Integer checkCHBData(String empId, String month, String year);

	void insertCHBDataValue(long chbDataId, String empId, String month,
			String year, String workingHours, String BasicValue, String updatedDate, String createdBy, String updatedBy, String strDdoCode);

	void updateCHBDataValue(String empId, String month, String year,
			String workingHours, String BasicValue, String createdDate, String createdBy,
			String updatedBy, String strDdoCode);

	
	
}
