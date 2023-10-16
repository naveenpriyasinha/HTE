/**
 * 
 */
package com.tcs.sgv.billproc.common.dao;

import java.util.Map;

/**
 * RemarksDAO 
 * This interface contains method for updating remarks. 
 * It also contains the method for getting nextLineItemNo which is use to validate remarks
 * This interface and its methods are implemented in RemarksDAOImpl.
 * 
 * Date of Creation : 12th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 * 
 */
public interface RemarksDAO {
	public int getNextLineItem(String billNo, String userId);
	public int updateRemarks(Map inputMap);
}
