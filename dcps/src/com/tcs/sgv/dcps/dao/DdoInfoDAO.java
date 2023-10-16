/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 19, 2011		Bhargav Trivedi								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.dcps.valueobject.DDOInformationDetail;


/**
 * Class Description -
 * @author Bhargav Trivedi
 * @version 0.1
 * @since JDK 5.0 Mar 19, 2011
 */
public interface DdoInfoDAO extends GenericDao {

	public DDOInformationDetail getDdoInfo(String lStrDdoCode);

	public Boolean checkDdoExistOrNot(String lStrDdoCode);

	public OrgDdoMst getDdoInformation(String lStrDdoCode);
	
	public void updateDdoName(Long lLngDdoAstPostId, String lStrName);
	//added by roshan
	//added by roshan
	public List getApproveDdoInformation(Long postId);

	public void updateDdoNameInOrgEmpMst(Long lngUserId,
			String strDdoPersonalName);

	//public long getDDOHistory(String ddoCode);

	//public List getDDoHistoryDetails(String ddoCode);

	// String getDetails(Long lngUserId);

	public void insertDetails(String strPkforHRPayDdoHistory,String ddoCode, String ddoName,
			String serviceFromDate, String serviceToDate,String status, String userId);

	public void updateDetails(Long valueOf, String updatedDdoName,
			String updatedFromDate, String updatedToDate);

	public long getDDOHistory(String ddoCode);

	public List getDDoHistoryDetails(String ddoCode);

	public String getDetails(Long lngUserId);

	public String getCretaedDate(Long lngUserId);

	public void insertNewDetails(String strPkforHRPayDdoHistory,String ddoCode, String updatedDdoName,
			String updatedFromDate, String updatedToDate, String status, String userId);

	public String getDDOtype(String strDdoCode);

	public List getLevel1DDOList(String strDdoCode);

	public List getLevel2DDOList(String strDdoCode);

	public List getLevel3DDOList(String strDdoCode);

	public List getDDoHistoryDetailsForApprove(String ddo);

	public void updateStatus(String srNo, String userID, String ddoName);

	public void rejectDDOHistory(String srNo, String userID, String ddoName);

	public void updateDDOName(String srNo, String userID, String ddoName);
//added by samadhan to get user name in organisation details
	public String getUserName(Long lngUserId);

	public void updateDDONameORGDDOMST(String ddoName, String strDdoCode);

	public List getDDoHistoryFromSecondLevel(String strDdoCode);

}
