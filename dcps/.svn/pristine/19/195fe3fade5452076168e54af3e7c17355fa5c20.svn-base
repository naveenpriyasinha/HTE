package com.tcs.sgv.dcps.dao;

import java.util.Date;
import java.util.List;

import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmpDetails;
import com.tcs.sgv.eis.valueobject.MstPayrollDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public interface ChangeDetailsDao {

	List getApprovedFormsForDDO(String ddoCode, String changeStatus);

	List<MstEmpNmn> getNomineesFromBackup(String strEmpId);


	Long getOtherId(Long orgEmpMstId);

	void insertOrgUserpostRltForChanges(OrgUserpostRlt orgUserpostRlt,
			RltDcpsPayrollEmp empData, MstEmp objDcpsEmpMst);

	List<MstPayrollDesignationMst> getMstDcpsDsgnObject(long parentLocId,
			long dsgnId);

	Long getPostId(String ddoCode);

	void updateGisData(long empGISId, Date orderDate, long gisGroupRevId,
			Date revMemshipdate, String ordername, long gisRevApplicableId,
			String remarks, long userId, Long postIdOfAsstDDO);

	void updateGrade(String cadreTypeId, long dcpsEmpId, long userId);


	List searchEmps(String strSevarthId, String strName, String strDdoCode, String changeStatus);

	RltDcpsPayrollEmpDetails getPayrollVOForEmpId(Long dcpsEmpId);

	void updateChangeStaus(Long dcpsEmpId);

	void upDateGpfAcNo(String gpfNo, long userId, String pfSeriesDesc);

	List getApprovedFormsForDDO(String ddoCode);

	String getBillGrpId(long parseLong);


}
