/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 7, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstEmp;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 7, 2011
 */
public interface DcpsCommonDAO extends GenericDao {

	public List getMonths();

	public List getYears();

	List getFinyears();

	public String getDdoCode(Long lLngAsstPostId);

	public String getDdoCodeForDDO(Long lLngPostId);
	//added by Demolisher	
	public List<String>  getDdoCodeLvLForDDO(Long lLngPostId);
	//ended by Demolisher
	List<ComboValuesVO> getAllDesignation(Long lLngDeptId, Long langId)
			throws Exception;

	List<ComboValuesVO> getAllDepartment(Long lLngDepartmentId, Long langId)
			throws Exception;
	
	List<ComboValuesVO> getHigherDepartment(Long lLngDepartmentId, Long langId)
	throws Exception;

	List<ComboValuesVO> getAllHODDepartment(Long lLngDepartmentId, Long langId)
			throws Exception;

	public List getBillGroups() throws Exception;

	public Date getLastDate(Integer month, Integer year);

	public Date getFirstDate(Integer month, Integer year);

	public Object[] getSchemeNameFromBillGroup(Long billGroupId);

	public String getYearCodeForYearId(Long yearId);

	public String getMonthForId(Long monthId);

	public List getCadres();

	public List getBankNames() throws Exception;

	public List getBranchNames(Long lLngBankCode) throws Exception;

	public List getBranchNamesWithBsrCodes(Long lLngBankCode) throws Exception;

	public Long getIFSCCodeForBranch(Long branchName) throws Exception;

	public List getStateNames(Long langId) throws Exception;

	public List getDistricts(Long lStrCurrState) throws Exception;

	public List getTaluka(Long lStrCurrDst) throws Exception;

	public List getDesignations(String lStrCurrOffice) throws Exception;

	public String getCmnLookupNameFromId(Long lookupId);

	public String getDesigNameFromId(Long lookupId);

	public List getCurrentOffices(String lStrDdoCode);

	public OrgDdoMst getDDOInfoVOForDDOCode(String ddoCode);

	public String getDdoNameForCode(String lStrDdoCode);

	public OrgDdoMst getDdoVOForDdoCode(String ddoCode);

	public String getTreasuryNameForDDO(String lStrDdoCode);

	public String getTreasuryCodeForDDO(String lStrDdoCode);

	public List getCadreForDept(Long lLngDeptCode) throws Exception;
	
	public List getallCadreForDept() throws Exception;

	public List getParentDeptForDDO(String lStrDdoCode);

	public List getDesigsForPFDAndCadre(Long fieldDeptId) throws Exception;

	List getDeptNameFromDdoCode(String lStrDdoCode);

	public List getAllTreasuries() throws Exception;

	public String getLocNameforLocId(Long locId);

	public String getCadreNameforCadreId(Long cadreId);
	
	public Long getCadreCodeforCadreId(Long cadreId) ;
	
	public Long getCadreIdforCadreCodeAndFieldDept(Long cadreCode,Long fieldDeptId);

	public String getGroupIdforCadreId(Long cadreId);

	public String getDddoOfficeNameNameforId(Long dcpsDdoOfficeIdPk);

	public List getOfficesForPost(Long lLongPostId) throws Exception;

	List<ComboValuesVO> getAllOrgType() throws Exception;

	public MstEmp getEmpVOForEmpId(Long dcpsEmpId) throws Exception;

	List getDatesFromFinYearId(Long yearId) throws Exception;

	String getCurrentInterestRate();

	String getFinYearForYearId(Long yearId);

	Float getCurrentDARate(String payComm);

	String getTreasuryNameForTreasuryId(Long treasuryId);

	public String getBranchNameForBranchCode(String branchId);

	public String getBankNameForBankCode(String bankCode);

	public List getAllDDOForTreasury(String lStrTreasuryLocCode);

	public List getLookupValuesForParent(Long lLngParentLookupId)
			throws Exception;

	public Boolean checkPFDForDDO(String lStrDdoCode);

	public List getBillGroups(String lStrDDOCode) throws Exception;

	public Long getDDOPostIdForDDOAsst(Long lLngPostId);

	public Long getFinYearIdForYearCode(String yearCode);

	public DdoOffice getDdoMainOffice(String lStrDdoCode);

	public String getTreasuryCityForDDO(String lStrDdoCode);

	public String getTreasuryShortNameForDDO(String lStrDdoCode);
	
	public String getFinYearCodeForYearId(Long yearId);
	
	public List getAllOffices() ;
	
	public String getFinYearIdForDate(Date FinDate);
	
	public List getStates(Long langId);
	
	public String getAdminBudgetCodeForDDO(String lStrDDOCode) throws Exception ;
	
	public Long getDDOAsstPostIdForDDO(String lStrDDOCode);
	
	public Long getUserIdForPostId(Long lLongPostId) ;

	public Long getDDOPostIdForDDO(String lStrDDOCode);
	
	//added by Demolisher
	public List getInstituteType();
	
	public String getLocationCodeforDDOloginfromEmpId(String empId);
	//ended by Demolisher
	
	// Mayuresh
	public List getAppointment();
	
	public String getFieldDeptOfDDO(String lStrDdoCode) ;
	public String getRptDDOCodeForZPDDO(String lStrDdoCode);
	public String getGrType(String postId);
	public String getLocationCodeForDDO(String lStrDdoCode);
	
	 public List getDDODtls(String DDOCode);
	    public List getSubDDOs(Long locId, String talukaId, String ddoSelected);
	    public List getpostRole(Long postId);
	    public List getSubDDOCode(String DDOCode);
	    public List getSubOffc(String postId);
	    public String getDDofromOffc(String offcId);
	    
	    public String getFinYearDescForYearCode(String finYearCode) ;
		
		public List getBillGroupsNotDeletedAndNotDCPS(String lStrDDOCode) throws Exception ;
		public List getFinyearsForDelayedType() ;
		
	// Added by Mayuresh
	public List getDDOSchemesForSubDDOs(String DDOCode);
	// Ended

	public String districtName(String ddoCode);

	public List allTaluka(String districtID);

	//added by samadhan for uid validation
	public Long checkUIDNumber(String uid, Long dcpsEmpId);


	public String getCurrOffc(String ofcID);

	public String getaccNoLengthForBankId(String bankId);

	public Long getPinCodeCount(String pinCode, String dist);

	public List getCity(Long strCurrDst);
	
	// added by samdhan for generating PF account number
	public int getPfSeriesCount(String pfSeriesDesc);

	public List getDesigsForPFDAndCadre(Long lngParentDept, Long cadre)throws Exception;

	public String getDistrictId(String ddoCode);

	public List getDdoCodeForMdp();
	
	//added by poonam for city class mapping
	  public List getCityClassName() ;
	  

			/*public void updateCityClassForEmployee(String arrCityClass,
			String arrSevarthCode, String dictrictName,String talukaName);
*/
		List getEmpListForCityClassUpdation(String ddoCode);
		public List getTalukaForCityClass() throws Exception ;

		public void updateCityClassForEmployee(String arrCityClass,
				String arrSevarthCode, String dictrictName,String talukaName);

		public void updateMajorHeadOfAccountCode(String string, String string2);

		public List getEmpListForMajorHeadUpdation(String ddoCode);

		public String getPayBandIdSevenPC(String sevenPcLevel);

		
	
	    
}



