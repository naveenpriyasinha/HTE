package com.tcs.sgv.exprcpt.dao;

import java.util.Date;
import java.util.List;

/**
 * A interface for expenditure and receipt reports
 *  
 * @author 206819
 */
public interface ReportDAO {

	public List getPayByBudHds(String tsryLocId, Date fromDate, Date toDate, boolean subTrsy,long lang_id);
	public List getRptMetaData(String type);
	public List getRcptByBudHd(String tsryLocId, Date fromDate, Date toDate, boolean subTrsy,String fromMjrHd, String toMjrHd, String operator,long lang_id);
	public List getReceiptByBudHds(String tsryLocId, Date fromDate, Date toDate, boolean subTrsy,long lang_id);
	public List getPaymentAsDisbursment(String tsryLocId, Date fromDate, Date toDate, boolean subTrsy,long lang_id);
	public String getMajHdStatusPayRpt(Date fromDate, Date toDate, String tsryLocId, String subtsryLocId,String[] majorHead, String type, String lStrLangId, Long sLocId);
	public String getMajHdStatusRcptRpt(Date fromDate, Date toDate, String tsryLocId, String subtsryLocId,String[] majorHead, String type, String lStrLangId, Long sLocId);
	
	//public List getBookPaymentByBudHds(Long tsryLocId, Date fromDate, Date toDate, boolean subTrsy);
	//public List getBookReceiptByBudHds(Long tsryLocId, Date fromDate, Date toDate, boolean subTrsy);
}
