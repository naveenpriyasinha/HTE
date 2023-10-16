package com.tcs.sgv.common.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;

/**
 * BptmCommonServicesDAO
 * This interface contains method declaration to get challan id from code and vice versa, 
 * to get Bill Control No from Bill No and vice versa, 
 * to get Receipt VO, to get Budget Head Structure VO, 
 * to get LookupId from Name, to get Emp Name from user Id, 
 * to get Lookup Text and description, to get Bill type, to get bill control no, 
 * to get max of reference number, to get Total Gross Amount. 
 * It also contains utility methods for checking validity of Previous Bill No, 
 * to check validity of Receipt No, to check bill(physical or online).
 * This interface and its methods are implemented in BptmCommonServicesDAOImpl
 * 
 * Date of Creation : 13th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * ===================== 
 * Hiral 	24-Oct-2007 	For making changes for code formating
 * 
 */

public interface BptmCommonServicesDAO 
{		
	public List getBillType(Long langId);
	public String getBillCtrlNo(Map inputMap);
	public String getBillCtrlNoForOBPM(Map inputMap);
	public String getLookUpText(String lookupName, Long langId);
	public String getLookUpDesc(int lookupId, Long langId);
	public String getUserIdFromPost(String postId, Long langId);
	public List getUserFromPost(String[] postId,String[] levelId, Long langId);
	public long checkPreBillNo(String prevBillNo);
	public Long getBillNoFromBillCtrlNo(String billCtrl);
	public String getBillCntrlNoFromBillNo(String billNo);
	public long checkReceiptNo(String lStrRcptNo);
	public Long getChallanIdfromCode(String challanCode);
	public String getChallanCodefromId(String challanId);
	public BigDecimal getTotalGrossAmount(Map mp);
	public String getEmpNameFrmUserId(String lStrUserId, Long lLngLangId);
	public String getLookupIdFromName(String lookupName, Long langId);
	public boolean isPhyBill(Long billNo);
	public TrnBillRegister getBillVoFromChqNo(Long lLngChqNo);
	public TrnBillBudheadDtls getBudHdVoFromBillNo(Long lLngBillNo);      
    public TrnReceiptDetails getRcptVoFromBillNo(Long lLngBillNo, Map inputMap);
    public List getTrnRcptFromBill(Long lLngBillNo, Map inputMap); 
    public Long getMaxRefNum(String locationCode);
    public Long getSubIdFromBillType(String lStrBillType);
    public String getBillTypeFromSubId(Long lLngSubjectId);
	
}
