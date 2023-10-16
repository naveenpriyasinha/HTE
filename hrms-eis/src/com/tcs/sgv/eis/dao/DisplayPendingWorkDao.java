package com.tcs.sgv.eis.dao;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;

	public interface DisplayPendingWorkDao  {

	public String findPendingWork(String ddoCode);

	public int findTotalNumberOfschool(String ddoCode);

	public int findGPFCount(String ddoCode);

	public int findDCPSCount(String ddoCode);

	public int findTotalCountOfPendingOffice(String ddoCode);

	public int findGPFCountByLevelThree(String ddoCode);

	public int findDCPSCountByLevelThree(String ddoCode);

	public long findCountDraftForms(String ddoCode);

	public List getMissedEmpStatisticsDDOwise(String strDdocode);

	public void sendToLevel1(String strDcpsEmpIds);

	public List getApproveEmployeeDDOwise(String strDdocode);

	public void deleteSelectedEmployee(String strDcpsEmpIds);

	public void updateServiceExpiryDate(String empId, String serviceEndDate);

	public List getMissedEmployeeDDOwise(String strDdocode);

	public long getDDOHistory(String ddoCode);

	public List getDDoHistoryDetails(String ddoCode);

	public String getDetails(Long lngUserId);

	public String getCretaedDate(Long lngUserId);

	public void insertDetails(String ddoCode, String ddoName, String fromDate,
			String toDate);

	public void updateDetails(Long valueOf,
			String updatedDdoName, String updatedFromDate,String updatedTodate);

	public long findUsertype(String ddoCode);

	public long findUsertypeToCheckLevel3(String ddoCode);

	public long findUsertypeToCheckLevel4(String ddoCode);

	public long checkUser(String ddoCode);

	public void updateDdoNameInOrgEmpMst(Long lngUserId, String ddoName);

	public void insertNewDetails(String ddoCode, String updatedtxtNameNew,
			String updatedtxtFromDateNew, String updatedtxtTodateNew);

	public void detachPost(String empId);

	public List getSchoolList(String strDdocode);

	public void deleteSchool(String string);

	public List getResult(String txtQuery);
	
	
	//START added by samadhan for update details on home page
	public String getMobileNo(String ddoCode);
	public String getEmail(String ddoCode);
	public String getInstituteName(String ddoCode);
	public String getTanNo(String strDdocode);
	public void updateUDise(String udise, String strDdocode);
	public void updateMobileNo(String mobileNo, String strDdocode);
	public void updateEmail(String email, String strDdocode);
	public String getInstituteNameForDiseCode(String diseCode);
	public void updateTanNo(String tanNo, String strDdocode);
	//END added by samadhan for update details on home page

	public void updateInstituteName(String instituteName, String udise, String lStrDdocode);


	public void updateSecretQuestionAndAnswer(String secretQuestion,
			String secretAnswer, String strDdocode);

	public Blob getAttachment();

	

	

	

	

	

}
