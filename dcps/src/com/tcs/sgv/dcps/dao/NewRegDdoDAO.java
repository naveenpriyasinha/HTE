/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 8, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpDetails;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmpDetails;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 8, 2011
 */
public interface NewRegDdoDAO extends GenericDao {

	public List getDesignations(String lStrCurrOffice) throws Exception;

	public List getCurrentOffices();

	public List getGroupName(Long cadreId);

	public List getNominees(String empId);

	public void deleteNomineesForGivenEmployee(Long lLongEmpId);

	public List getOfficeDetails(Long lLngOfficeId);

	public List getAllDcpsEmployees(String lStrUser, String lStrPostId,
			String lStrDdoCode);

	public List getAllDcpsEmployeesForCaseStatus(String lStrUser,
			String lStrPostId, String lStrDdoCode, String lStrSearchValue);

	public List getAllDcpsEmployeesForDesig(String lStrUser, String lStrPostId,
			String lStrDdoCode, String lStrSearchValue);

	public void updatePhyStatus(Long lLongEmpId);

	public DdoOffice getDdoOfficeVO(Long ddoOfficeId);

	public Long getDcpsEmpPayrollIdForEmpId(Long dcpsEmpId);

	public Boolean checkDcpsEmpPayrollIdForEmpIdExists(Long dcpsEmpId);

	public Long getTotalEmployees();

	public RltDcpsPayrollEmp getPayrollVOForEmpId(Long dcpsEmpId);

	public MstEmp getEmpVOForEmpId(Long dcpsEmpId);
	
	public MstEmpDetails getEmpVOArchivedForEmpId(Long dcpsEmpId) ;

	public List getApprovalByDDODatesforAll(String lStrDDODode,
			String lStrPostId);

	public List getAllPayScales();

	public List getFormListForDDO(String lStrDDOCode);

	public List getDesigsForAutoComplete(String search);

	public String getDistrictForDDO(String lStrDdoCode);

	public List getApprovedFormsForDDO(String lStrDDOCode);
	
	public List getAllApprovedEmpsUnderDDO(String lStrDDOCode,String lStrSevaarthId,String lStrName) ;
	
	public MstEmp getEmpVOForDCPSId(String dcpsId,String ddoCode) ;
	
	public void deleteRltPayrollEmpForGivenEmployee(Long lLongEmpId) ;
	
	public void lockAccountForOrgEmpId (Long lLongOrgEmpMstId);
	
	public Long getPostForEmpId(Long lLongOrgEmpId) ;
	
	public Long getUserIdForEmpId(Long lLongOrgEmpId);
	
	public Long getUserIdForPostId(Long PostId) ;
	
	public Boolean checkEntryInRltDDOAsstTable(Long lLongAsstPostId,Long lLongDDOPostId);
	
	public Boolean checkEntryInAclPostRoleTable(Long lLongAsstPostId);
	
	public void unlockAccountForOrgEmpId (Long lLongOrgEmpMstId);
	
	public Object[] getUserNameAndPwdForEmpId(Long lLongOrgEmpId); 
	
	public AclRoleMst getRoleVOForRoleId(Long roleId);
	
	public OrgPostMst getPostVOForPostId(Long postId);
	
	public OrgUserMst getUserVOForUserId(Long userId) ;
	
	public void insertAclPostRoleRlt(Long lLongAclPostRoleId,Long lLongRoleIdOfDDOAsst,Long lLongPostId,Long lLongDDOPostId,Date gDtCurDate) throws Exception;
	
	public Boolean checkEntryInWFOrgUserMpgMst(Long lLongUserId);
	
	public Boolean checkEntryInWFOrgPostMpgMst(Long lLongPostId);
	
	public void insertWFOrgPostMpg(Long lLongPostId) throws Exception ;
	
	public void insertWFOrgUsrMpg(Long lLongUserId) throws Exception ;

	public void insertWfHierachyPostMpg(Long lLongHierarchySeqId ,Long lLongHierarchyRefId ,Long lLongPostId,Long lLongCreatedByUserId,Date gDtCurDate,Long LocId ) throws Exception;
	
	public List<Long> getAllHierarchyRefIdsForLocation(Long LocationCode);
	
	public Boolean checkEntryInWfHierachyPostMpg(Long lLongHierarchyRefId,Long lLongPostId);
	
	public void updateDDOAsstStatusInMstEmp (Long lLongDcpsEmpId,String lStrRequest);
	
	public Boolean checkIfNameExists(String lStrName) ;

	//commented by vaibhav tyagi
	//public List getAllDcpsEmployeesZP(String lStrUser, String lStrPostId,
			//String lStrDdoCode,String lStrUse);
	public String getPostDesc(Long postId);

	//added by vaibhav tyagi:start
	public List getAllAsstDDOList(String strDdoCode);
	public List getAllDcpsEmployeesZP(String lStrUser, String lStrPostId,
			String lStrDdoCode,String lStrUse, String reptddoSelected, String ddoSelected);
	public List getAllAsstDDOListByFinalDDO(String strDdoCode,String reptddoSelected);
	public List getAllReptDDOListByFinalDDO(String strDdoCode);
	//added by vaibhav tyagi:end
	
	
	public long deleteNomineeDetails(String nomineeID);

	public List getAllDcpsEmployeesZPForBankUpdate(String strDdoCode);

	public void updateDetails(String string, String string2, String string3,
			String string4, String string5);// changed By Tejashree

	public List getEmployeeListForBasicUpdates(String strDdoCode);
	
	public List getEmployeeListofRateperHours(String strDdoCode); // Added By Tejashree
	
	public void updateDetailsRateperhour(String string, String string2, String string3,
			String string4, String string5);
	
	public long getSequenceNO();

	public RltDcpsPayrollEmpDetails getPayrollDetailsVOForEmpId(Long longEmpId);

	public List getEmpDtls(String strDDOCode);

	public void updateHeadOfAccountCode(String string, String string2);

	public void updateBankDetails(String string, String string2,
			String string3, String string4);

	public List getBankNames();

	public List getBranchList(String cmbBank);

	public Long chkDCPSIDalreadyExists(String dcpsId);

	public String chkempNameforDCPSIDalreadyExists(String dcpsId);

	public Long chkPANalreadyExists(String panNo,String dcpsEmpID);
	
	public Long chkPANalreadyExistsForEmpConfig(String panNo);

	public String chkempNameforPANalreadyExists(String panNo);

	public Long chkPANalreadyExistsforCSRF(String panNo, String sevaarthId);
}