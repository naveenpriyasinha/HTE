package com.tcs.sgv.billproc.cheque.dao;
/** 
 *  com.tcs.sgv.billproc.cheque.dao.TrnChequeDAO
 *  
 *  This is implementation class for  getting cheque details
 *  
 * 	Date of Creation : 10th July 2007
 * 
 *  Author : Vidhya Mashru 
 *  
 *  Revision History 
 *  =====================
 *  Vidhya M    23-Oct-2007  Made changes for code formatting
 */
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TrnChequeDAO {
	public BigDecimal getUnpaidChequesSum(Date fromDate, Date toDate, List chqTypes,String Loc);
	public BigDecimal getPaidChequesSum(Date fromDate, Date toDate, List chqTypes,String Loc);
	public String getWriterName(long chequeId, Long lLngLangId);
}
