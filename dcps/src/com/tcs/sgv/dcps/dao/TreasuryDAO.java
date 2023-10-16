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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.dcps.valueobject.MstDummyOffice;

/**
 * Class Description -
 * 
 * 
 * @author Shivani Rana
 * @version 0.1
 * @since JDK 5.0 Jan 31, 2011
 */
public interface TreasuryDAO extends GenericDao {

	public List getYears() throws Exception;

	public List getAllFormsForDDO(String lStrDDODode, String lStrPostId)
			throws Exception;

	public List getDummyOffices() throws Exception;

	public List getEmpDeptn(String lStrQuery,String lStrSevarthId,String lStrEmpName) throws Exception;

	public Long getHstEmpDeputationPkVal(Long lLngDcpsEmpId) throws Exception;

	public List getYearsForSixPCYearly() throws Exception;

	public List getEmpSearchDeptn(String lStrEmpName,String lStrSevarthId) throws Exception ;

	public List getEmpListForSixPCArrearsYearlyTO(String lStrDDOCode,
			Long finYearId, String lStrPostId) throws Exception;

	public List getAllDDOListForPhyFormRcvd(String lStrPostId,
			String lStrUserType) throws Exception;

	public List getDummyOfficesList(Map inputMap) throws Exception;

	public MstDummyOffice getDummyOfficeInfo(String dummyOfficeId)
			throws Exception;

	public String getSchemeCodeForBillGroupId(Long billGroupId);
	
	public List getEmployeesFromDummyOffice(String dummyOfficeId,Long monthId,Long yearId) throws Exception;
}
