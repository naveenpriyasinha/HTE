/**
 * 
 */
package com.tcs.sgv.billproc.common.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * ShowBillService 
 * This interface contains method which is used to show every
 * details of the bill to end-user, when he/she opens the bill in editable mode.
 * It also loads the set of initial values required in bill. 
 * It also shows the attachments attached with that bill.
 * This interface and its methods are implemented in ShowBillDAO.
 * 
 * Date of Creation : 13th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 * 
 */
public interface ShowBillService {
	public ResultObject showBillDetails(Map objectArgs);

	public ResultObject loadValues(Map inputMap);

	public ResultObject openAttachments(Map objectArgs);

}
