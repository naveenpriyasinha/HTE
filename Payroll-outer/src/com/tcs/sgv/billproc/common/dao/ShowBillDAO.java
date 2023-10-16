/**
 * 
 */
package com.tcs.sgv.billproc.common.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.billproc.common.valueobject.TrnShowBill;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.service.ServiceLocator;

/**
 * ShowBillDAO 
 * This interface contains method which sets all the necessary
 * fields which are needed to show the bill to user in editable mode. 
 * It also contains the method for getting Attachment ID and ojections selected by user.
 * This interface and its methods are implemented in ShowBillDAOImpl.
 * 
 * Date of Creation : 13th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 * 
 */
public interface ShowBillDAO {
	public TrnShowBill getBilldetails(Map inputMap);

	public void setDDODetails(String lStrCardexNo, Long lLngLangId,
			Long lLngDbId, ServiceLocator serv, TrnShowBill lObjTrnShowBill,
			String lLngLocCode);

	public void setReceiptDetails(String lStrBillNo,
			TrnShowBill lObjTrnShowBill, ServiceLocator serv,
			TrnBillRegister lObjBillRegister, Map inputMap);

	public void setBudHeadDetails(String lStrBillNo,
			TrnShowBill lObjTrnShowBill, Map inputMap);

	public String setLastRemarks(String lStrBillNo, Long lLngUserId);

	public void setExempted(TrnShowBill lObjTrnShowBill,
			TrnBillRegister lObjBillRegister);

	public void setAttachId(TrnShowBill lObjTrnShowBill,
			TrnBillRegister lObjBillRegister);

	public void setBillDetails(TrnShowBill lObjTrnShowBill,
			TrnBillRegister lObjBillRegister, ServiceLocator serv, Long lLangId);

	public Long getAttachId(Long billNo);

	public List getSelectedObj(String billNo, Long userId);
}
