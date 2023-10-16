package com.tcs.sgv.billproc.report;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.SessionFactory;

/**
 * com.tcs.sgv.billproc.reprot.ReportQueryDAO
 * 
 * This is Interface for reporting framework reports to be generated
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History 
 * ===================== 
 * Vidhya M 23-Oct-2007 Made changes for  code formatting
 */
public interface ReportQueryDAO {
	public void setSessionFactory(SessionFactory sessionFactory);

	public ArrayList getInwardedReport(Long subjectId, String fromDate,
			String toDate, Long modeId, String mjrHead, String ddoPostId[],
			String audUserId[], Long locId, String strLocationCode, Long langID);

	public ArrayList getBillDtlByBTraRpt(Long subjectId, String fromDate,
			String toDate, String mjrHead, String ddoPostId[], Long tokenNo,
			Long locId, String strLocationCode, Long langID);

	public ArrayList getChqDetailByDelRpt(Long subjectId, String fromDate,
			String toDate, Long modeId, String ddoUserId[], String audUserId[],
			Long chequeNo, Double chequeAmt, Long locId,
			String strLocationCode, Long langID);

	public ArrayList getAuditTrackingRpt(String fromDate, String toDate,
			Long subjectId, String mjrHead, String ddoUserId[], Long tokenNo,
			String auditstatus, Long locId, String strLocationCode, Long langID);

	public ArrayList getUndeliveredChequesDetails(String fromDate,
			String toDate, Long locId, String strLocationCode);

	public ArrayList getUnpaidChequeDetail(String fromDate, String toDate,
			Long locId, String strLocationCode);

	public ArrayList getChequeStatus(String fromDate, String toDate,
			String Status, Long locId, String strLocationCode, Long langID);

	// Added by Milind Vijay Shah
	public List getTokenReportDetails(String strTokenStatus, String strLocID,
			String strLocationCode);

	public List getTokenDetailsData(String strTokenStatus, String strLocID,
			String strLocationCode);

	public List getCancelledChequeData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode);

	public List getRenamedChequeData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode);

	public List getRevalidationData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode);

	public List getChequeDuplicationData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode);

	public List getChequeAdviceCancellationData(String strFromDate,
			String strToDate, String strLocID, String strLocationCode);

	public List getTimeBarredData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode);

	public List getOutSideChequeData(String strFromDate, String strToDate,
			String strLocID, String strLocationCode);

	public List getChequeData(String strChequeNo, String strLocationCode);

	public List getCardexWiseSummary(String strFromDate, String strToDate,
			String strLocID, String strLocationCode, Long langId);

	public ArrayList getAudWiseDailyRptDtls(String fromDate, String toDate,
			Long audUserId, Long vitoCode, Long locId, String strLocationCode,
			Long langID);

	public ArrayList getChequeAdviceRpt(String fromDate, String toDate,
			Long vitoCode, Long locId, String strLocationCode);

	public ArrayList getAuditorWiseBillDetail(String fromDate, String toDate,
			Long billType, String[] auditorName, Long vitoCode, Long locId,
			String strLocationCode, Long langID);

	public ArrayList getBillTranRegForAudDtls(String fromDate, String toDate,
			String[] audUserId, Long vitoCode, Long locId,
			String strLocationCode, Long langID);

	public ArrayList getBillTranRegAfterAudDtls(String fromDate, String toDate,
			String[] audUserId, String status, Long vitoCode, Long locId,
			String strLocationCode, Long langID);

	public ArrayList getChequeDrawnRegisterRpt(String fromDate, String toDate,
			Long vitoCode, Long locId, String strLocationCode, Long langID);

	public ArrayList getDateWiseReceiptRpt(String fMjrHead, String tMjrHead,
			String fromDate, String toDate, Long locId);

	public List genAudWiseBillTypeVito(Long userId, Long loc_Id,
			Long subjectId, Long audUserId, String vitoType,
			List vitoStringList, String strLocationCode, Long langID);

	public List genBillTranRegForAudit(Long userId, Long loc_Id,
			Long audUserId, String vitoType, List vitoStringList,
			String strLocationCode, Long langID);

	public ArrayList getBillType(String lStrLangId, String lstrLocId);

	public ArrayList getMajorHead(String lStrLangId, String lstrLocId);

	public ArrayList getDDOName(Hashtable hashtable, String lStrLangId,
			String lstrLocId);

	public ArrayList getAuditorName(Hashtable hashtable, String lStrLangId,
			String lstrLocId);

	public ArrayList getBillTrackDtls(Long billno, String strLocationCode,
			Long langID);

	public List getExpMajorHead(Long userId, Long locId,
			String strLocationCode, Long langID);

	public List getBillListByAuditor(Long audUserId, String vitoType,
			Long locId, String strLocationCode);

	public List getAuditorList(Long userId, Long locId, Long langID,
			String strLocationCode);

	public List getChqAdviceDtls(Long userId, Long loc_Id, List vitoStringList,
			String strLocationCode);

	public ArrayList getStatus(String lStrLangId, String lstrLocId);

}
