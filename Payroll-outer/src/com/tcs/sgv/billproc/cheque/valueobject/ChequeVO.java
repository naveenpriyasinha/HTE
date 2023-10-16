package com.tcs.sgv.billproc.cheque.valueobject;
import java.util.List;
/** 
 *  com.tcs.sgv.billproc.cheque.valueobject.ChequeVO
 *  
 *  This is Java bean to get cheque details
 *  
 * 	Date of Creation : 10th July 2007
 * 
 *  Author : Vidhya Mashru 
 *  
 *  Revision History 
 *  =====================
 *  Vidhya M    23-Oct-2007  Made changes for code formatting
 */
public class ChequeVO implements java.io.Serializable 
{
	TrnChequeDtls trnChequeDtls;
	String writerName;
	List billChequeRlt;	
	List tokenNo;
	List billCntrlNo;
	List trnBillRegisterData;
	
	public List getBillChequeRlt() {
		return billChequeRlt;
	}
	public void setBillChequeRlt(List billChequeRlt) {
		this.billChequeRlt = billChequeRlt;
	}
	
	public TrnChequeDtls getTrnChequeDtls() {
		return trnChequeDtls;
	}
	public void setTrnChequeDtls(TrnChequeDtls trnChequeDtls) {
		this.trnChequeDtls = trnChequeDtls;
	}
	public List getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(List tokenNo) {
		this.tokenNo = tokenNo;
	}
	public List getBillCntrlNo() {
		return billCntrlNo;
	}
	public void setBillCntrlNo(List billCntrlNo) {
		this.billCntrlNo = billCntrlNo;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public List getTrnBillRegisterData() {
		return trnBillRegisterData;
	}
	public void setTrnBillRegisterData(List trnBillRegisterData) {
		this.trnBillRegisterData = trnBillRegisterData;
	}
	
}
