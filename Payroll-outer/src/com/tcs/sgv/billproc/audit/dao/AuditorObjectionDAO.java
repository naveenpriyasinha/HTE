/**
 * 
 */
package com.tcs.sgv.billproc.audit.dao;

import java.util.List;
import java.util.Map;

/**
 * AuditorObjectionDAO 
 * This interface contains various methods for objections
 * and remarks. It also contains the method for updating objections and getting
 * remarks and objections based on different criteria. 
 * This interface and its methods are implemented in AuditorObjectionDAOImpl.
 * 
 * Date of Creation : 10th July 2007 Author : Hiral Shah
 * 
 * Revision History =====================
 * 
 */
public interface AuditorObjectionDAO {
	public int getNextLineItem(String billNo, String userId);

	public int updateObjection(List objList, String billNo, String userId,
			Map objectArgs);

	public List getRmaksObjUsers(String lStrBillNo, Long lLngLangId);

	public List getPrevObjections(String lStrBillNo, Long lLngLangId,
			String lStrUserId);

	public List getAllObjections(String lStrBillNo, Long lLngLangId);

	public Map getAllObjectionData(String[] BillControlNo, Long lLngLangId);

	public List getPrevRemarks(String lStrBillNo, String lStrUserId);
}
