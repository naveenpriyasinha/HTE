/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 28, 2011		Anjana Suvariya								
 *******************************************************************************
 */
package com.tcs.sgv.common.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;


/**
 * Class Description -
 * 
 * 
 * @author Anjana Suvariya
 * @version 0.1
 * @since JDK 5.0 Apr 28, 2011
 */
public interface CommonDAO {

	List<ComboValuesVO> getAllDesignation(Long lLngLangId) throws Exception;

	List<ComboValuesVO> getAllDepartment(Long lLngDepartmentId, Long lLngLangId) throws Exception;

	List<ComboValuesVO> getAllState(Long lLngLangId) throws Exception;

	List<ComboValuesVO> getDistrictsOfState(Long lLngStateId, Long lLngLangId) throws Exception;

	List<ComboValuesVO> getCityList(Long lLngLangId) throws Exception;

	List<ComboValuesVO> getBankList(Map inputMap, Long lLngLangId) throws Exception;

	List<ComboValuesVO> getBranchListFromBankCode(Long lLngBankCode, String lStrLocCode, Long lLnglangId) throws Exception;

	List<ComboValuesVO> getMonthList(String lStrLangId) throws Exception;

	List<ComboValuesVO> getYearList(String lStrLangId) throws Exception;

	List isIFSCCodeExsist(String lStrBranchCode) throws Exception;

	String getRoleByPost(Long lLngPostId) throws Exception;

	String getAuditorNameByPostId(Long lLngPostId) throws Exception;

	String getTreasuryName(Long lLngLangId, Long lLngLocationCode) throws Exception;

	String getBankNameFromBankCode(String lStrBankCode, Long lLngLandId) throws Exception;

	String getBranchNameFromBranchCode(String lStrBankCode, String lStrBranchCode, Long lLngLandId) throws Exception;

}
