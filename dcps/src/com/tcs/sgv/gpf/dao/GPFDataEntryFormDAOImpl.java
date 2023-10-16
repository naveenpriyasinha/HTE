/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Dec 2, 2011		Jayraj Chudasama								
 *******************************************************************************
 */
package com.tcs.sgv.gpf.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * Class Description - 
 *
 *
 * @author Jayraj Chudasama
 * @version 0.1
 * @since JDK 5.0
 * Dec 2, 2011
 */
public class GPFDataEntryFormDAOImpl extends GenericDaoHibernateImpl implements GPFDataEntryFormDAO{

	Session ghibSession = null;
	public GPFDataEntryFormDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}
	
	public List getEmployeeNameAndPay(String lStrEmpCode) throws Exception 
	{	
		List lLstEmpDtls = null;		
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT name,basicPay ");
			lSBQuery.append(" FROM MstEmp");
			lSBQuery.append(" WHERE sevarthId = UPPER(:sevarthId)");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevarthId", lStrEmpCode);			
			lLstEmpDtls = lQuery.list();			
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		return lLstEmpDtls;
	}
	
	public String getGpfAccNo(String lStrSevaarthId) throws Exception 
	{
		String lStrGpfAccNo = "";
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT gpfAccNo");
			lSBQuery.append(" FROM MstEmpGpfAcc");
			lSBQuery.append(" WHERE sevaarthId = :sevaarthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lStrGpfAccNo = lQuery.list().get(0).toString();
		}catch (Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		return lStrGpfAccNo;
	}	
	
	public String getEmpGpfAccID (String lStrSevaarthId) throws Exception
	{
		String lStrEmpGpfAccID = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT mstEmpGpfAccId");
			lSBQuery.append(" FROM MstEmpGpfAcc");
			lSBQuery.append(" WHERE sevaarthId = :sevaarthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lStrEmpGpfAccID = lQuery.list().get(0).toString();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lStrEmpGpfAccID;
	}
	
	public List getNRADetails(String lStrGpfAccNo, String lStrReqType) throws Exception
	{
		List lLstNRADetails = null;
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT CLM.lookupName,MGA.amountSanctioned,MGA.sanctionedDate,MGA.voucherNo,MGA.voucherDate,MGA.noOfInstallments,MGA.installmentAmount,MGA.mstGpfAdvanceId,MGA.purposeCategory ");
			lSBQuery.append("FROM MstGpfAdvance MGA,CmnLookupMst CLM ");
			lSBQuery.append("WHERE MGA.advanceType='NRA' AND CLM.lookupId=MGA.purposeCategory AND MGA.gpfAccNo = :gpfAccNo ");
			if(lStrReqType.equals("Draft")){
				lSBQuery.append("AND MGA.statusFlag='D' ");
			}else if(lStrReqType.equals("Forward")){
				lSBQuery.append("AND MGA.statusFlag='F' ");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstNRADetails = lQuery.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstNRADetails;
	}
	
	public List getRADetailsHis(String lStrGpfAccNo, String lStrReqType) throws Exception
	{
		List lLstRADetails = null;
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT CLM.lookupName,MGA.amountSanctioned,MGA.sanctionedDate,MGA.voucherNo,MGA.voucherDate,MGA.noOfInstallments,MGA.installmentAmount,MGA.oddInstallment,MGA.installmentsLeft,MGA.mstGpfAdvanceId,MGA.purposeCategory ");			
			lSBQuery.append("FROM MstGpfAdvance MGA,CmnLookupMst CLM ");
			lSBQuery.append("WHERE MGA.advanceType='RA' AND CLM.lookupId = MGA.purposeCategory AND MGA.gpfAccNo = :gpfAccNo ");
			if(lStrReqType.equals("Draft")){
				lSBQuery.append("AND MGA.statusFlag='D' ");
			}else if(lStrReqType.equals("Forward")){
				lSBQuery.append("AND MGA.statusFlag='F' ");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstRADetails = lQuery.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstRADetails;
	}
	
	public List getRADetailsCur(String lStrGpfAccNo, String lStrReqType) throws Exception
	{
		List lLstRADetails = null;
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT CLM.lookupName,MGA.amountSanctioned,MGA.noOfInstallments,MGA.installmentAmount,MGA.installmentsLeft,MGA.oddInstallment,MGA.sanctionedDate,MGA.voucherNo,MGA.voucherDate,MGA.purposeCategory,MGA.mstGpfAdvanceId,MGA.purposeCategory ");
			lSBQuery.append("FROM MstGpfAdvance MGA, CmnLookupMst CLM ");
			lSBQuery.append("WHERE MGA.advanceType='RA' AND CLM.lookupId = MGA.purposeCategory AND MGA.gpfAccNo = :gpfAccNo ");
			if(lStrReqType.equals("Draft")){
				lSBQuery.append("AND MGA.statusFlag='DC' ");
			}else if(lStrReqType.equals("Forward")){
				lSBQuery.append("AND MGA.statusFlag='FC' ");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstRADetails = lQuery.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstRADetails;
	}
	
	public List getSubscriptionDetails(String lStrGpfAccNo, String lStrReqType) throws Exception
	{
		List lLstSubDetails = null;
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT MGE.monthlySubscription,SMM.monthName,SFY.finYearCode,MGE.gpfEmpSubscriptionId,MGE.effectFromMonth,MGE.finYearId ");
			lSBQuery.append("FROM MstGpfEmpSubscription MGE,SgvcFinYearMst SFY,SgvaMonthMst SMM ");
			lSBQuery.append("WHERE MGE.effectFromMonth = SMM.monthId AND MGE.finYearId = SFY.finYearId AND MGE.gpfAccNo = :gpfAccNo ");
			if(lStrReqType.equals("Draft")){
				lSBQuery.append("AND MGE.statusFlag='D' ");
			}else if(lStrReqType.equals("Forward")){
				lSBQuery.append("AND MGE.statusFlag='F' ");
			}
			lSBQuery.append("ORDER BY SMM.monthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstSubDetails = lQuery.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstSubDetails;
	}	
	
	public List getScheduleDetails(String lStrGpfAccNo, String lStrReqType) throws Exception
	{
		List lLstVoucherDetails = null;
		List lLstChallanDetails = null;
		try{
			StringBuilder lSBQueryVchr = new StringBuilder();
			lSBQueryVchr.append("SELECT 'Voucher',SMM.monthName,SFY.finYearCode,MGM.voucherNo,MGM.voucherDate,MGM.advanceRecovery,MGM.instNo,MGM.mstGpfMonthlyId,MGM.monthId,MGM.finYearId ");
			lSBQueryVchr.append("FROM MstGpfMonthly MGM,SgvcFinYearMst SFY,SgvaMonthMst SMM ");
			lSBQueryVchr.append("WHERE MGM.status='PD' AND MGM.monthId = SMM.monthId AND MGM.finYearId = SFY.finYearId AND MGM.gpfAccNo = :gpfAccNo ");
			lSBQueryVchr.append("ORDER BY MGM.finYearId, SMM.monthId");
			Query lQueryVchr = ghibSession.createQuery(lSBQueryVchr.toString());
			lQueryVchr.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstVoucherDetails = lQueryVchr.list();
			
			
			StringBuilder lSBQueryChallan = new StringBuilder();
			lSBQueryChallan.append("SELECT 'Challan',challanNo,challanDate,amount,instFrom,instTo,mstGpfChallanDtlsId ");
			lSBQueryChallan.append("FROM MstGpfChallanDtls ");
			lSBQueryChallan.append("WHERE gpfAccNo = :gpfAccNo ");
			if(lStrReqType.equals("Draft")){
				lSBQueryChallan.append("AND statusFlag = 'D'");
			}else if(lStrReqType.equals("Forward")){
				lSBQueryChallan.append("AND statusFlag = 'F'");
			}
			Query lQueryChln = ghibSession.createQuery(lSBQueryChallan.toString());
			lQueryChln.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstChallanDetails = lQueryChln.list();
			
			lLstVoucherDetails.addAll(lLstChallanDetails);
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstVoucherDetails;
	}
	
	public List getEmpListForVerification(String lStrDdoCode) throws Exception
	{
		List lLstEmpDtls = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT TEG.name, TEG.sevaarthId, MEG.currentBalance, MEG.monthlySubscription, TEG.deoRemarks, TEG.trnEmpGpfAccId ");
			lSBQuery.append("FROM TrnEmpGpfAcc TEG, MstEmpGpfAcc MEG ");
			lSBQuery.append("WHERE TEG.statusFlag = 'F' AND TEG.sevaarthId = MEG.sevaarthId AND MEG.ddoCode = :ddoCode");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("ddoCode", lStrDdoCode);
			lLstEmpDtls = lQuery.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstEmpDtls;
	}
	
	public String getBillGroupId(String lStrSevaarthId) throws Exception
	{
		String lStrBillGroupId="";
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT billGroupId ");
			lSBQuery.append("FROM MstEmp ");
			lSBQuery.append("WHERE sevarthId = :sevarthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevarthId", lStrSevaarthId);
			lStrBillGroupId = lQuery.list().get(0).toString();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lStrBillGroupId;
	}
	
	public String getTranIdForRAAdvance(String lStrGpfAccNo, String lStrReqType) throws Exception
	{
		String lStrTranId="";
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT transactionId ");
			lSBQuery.append("FROM MstGpfAdvance ");
			if(lStrReqType.equals("Draft")){
				lSBQuery.append("WHERE statusFlag ='DC' AND gpfAccNo = :gpfAccNo");
			}else if(lStrReqType.equals("Forward")){
				lSBQuery.append("WHERE statusFlag ='FC' AND gpfAccNo = :gpfAccNo");
			}			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());	
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			if(lQuery.list().size() >0){
				lStrTranId = lQuery.list().get(0).toString();
			}
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lStrTranId;
	}
	
	public String getDdoCodeForDDO(Long lLngPostId) throws Exception {

		String lStrDdoCode = null;
		List lLstDdoDtls = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT OD.ddoCode");
			lSBQuery.append(" FROM  OrgDdoMst OD");
			lSBQuery.append(" WHERE OD.postId = :postId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("postId", lLngPostId);

			lLstDdoDtls = lQuery.list();

			lStrDdoCode = lLstDdoDtls.get(0).toString();

		} catch (Exception e) {
			logger.error("Error is :" + e, e);

		}
		return lStrDdoCode;
	}
	
	public List getEmpListForDraftReq() throws Exception {
		
		List lLstEmpDtls = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT TEG.name, TEG.sevaarthId, MEG.currentBalance, MEG.monthlySubscription, TEG.deoRemarks ");
			lSBQuery.append("FROM TrnEmpGpfAcc TEG, MstEmpGpfAcc MEG ");
			lSBQuery.append("WHERE TEG.statusFlag = 'D' AND TEG.sevaarthId = MEG.sevaarthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());			
			lLstEmpDtls = lQuery.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstEmpDtls;
	}
	
	public String getTrnEmpGpfAccID (String lStrSevaarthId, String lStrReqType) throws Exception
	{
		String lStrEmpGpfAccID = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT trnEmpGpfAccId");
			lSBQuery.append(" FROM TrnEmpGpfAcc");
			lSBQuery.append(" WHERE sevaarthId = :sevaarthId");
			if(lStrReqType.equals("Draft")){
				lSBQuery.append(" AND statusFlag = 'D'");
			}else if(lStrReqType.equals("Forward")){
				lSBQuery.append(" AND statusFlag = 'F'");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lStrEmpGpfAccID = lQuery.list().get(0).toString();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lStrEmpGpfAccID;
	}	
	
	public List getPrevDetailsVoucher(String lStrGpfAccNo) throws Exception
	{
		List lLstPrvVoucher = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("FROM MstGpfMonthly ");
			lSBQuery.append("WHERE status='PD' AND gpfAccNo = :gpfAccNo ");			
			Query lQueryVchr = ghibSession.createQuery(lSBQuery.toString());
			lQueryVchr.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstPrvVoucher = lQueryVchr.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstPrvVoucher;
	}
	
	public List getPrevDetailsChallan(String lStrGpfAccNo) throws Exception
	{
		List lLstPrvVoucher = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("FROM MstGpfChallanDtls ");
			lSBQuery.append("WHERE gpfAccNo = :gpfAccNo AND statusFlag = 'D'");			
			Query lQueryVchr = ghibSession.createQuery(lSBQuery.toString());
			lQueryVchr.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstPrvVoucher = lQueryVchr.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstPrvVoucher;
	}
	
	public List chkIfEmpExist(String lStrGpfAccNo) throws Exception
	{
		List lLstEmpDtls = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();			
			lSBQuery.append("FROM TrnEmpGpfAcc ");
			lSBQuery.append("WHERE statusFlag = 'F' AND gpfAccNo = :gpfAccNo");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstEmpDtls = lQuery.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstEmpDtls;
	}

	public List getAdvanceDetails(String lStrGpfAccNo) throws Exception
	{
		List lLstAdvanceDetails = null;
		try{
			StringBuilder lSBQuery = new StringBuilder();			
			lSBQuery.append("FROM MstGpfAdvance ");
			lSBQuery.append("WHERE statusFlag IN('F','FC') AND gpfAccNo = :gpfAccNo ");						
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lLstAdvanceDetails = lQuery.list();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
		
		return lLstAdvanceDetails;
	}
		
	public void updateTrnEmpGpfAcc(String lStrPk, String lStrDeoRemark, String lStrDdoRemark, String lStrReqType) throws Exception
	{
		StringBuilder lSBQuery = new StringBuilder();	
		Query lQuery = null;
		
		try{
			lSBQuery.append("update TrnEmpGpfAcc");
			if(lStrReqType.equals("Save")){
				lSBQuery.append(" set deoRemarks = :deoRemarks, statusFlag='D'");
				lSBQuery.append(" WHERE trnEmpGpfAccId = :lStrPk");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("deoRemarks",lStrDeoRemark);
				lQuery.setParameter("lStrPk", Long.parseLong(lStrPk));
			}else if(lStrReqType.equals("Forward")){
				lSBQuery.append(" set deoRemarks = :deoRemarks, statusFlag='F'");
				lSBQuery.append(" WHERE trnEmpGpfAccId = :lStrPk");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("deoRemarks",lStrDeoRemark);
				lQuery.setParameter("lStrPk", Long.parseLong(lStrPk));
			}else if(lStrReqType.equals("Approve")){
				lSBQuery.append(" set ddoRemarks = :ddoRemarks, statusFlag='A'");
				lSBQuery.append(" WHERE sevaarthId = :lStrPk AND statusFlag='F'");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("ddoRemarks",lStrDdoRemark);
				lQuery.setParameter("lStrPk", lStrPk);
			}
						
			lQuery.executeUpdate();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
	}
	
	public void updateMstEmpGpfAcc(String lStrSevaarthID, String lStrCurBalance, String lStrMonthlySubscription) throws Exception
	{
		StringBuilder lSBQuery = new StringBuilder();	
		Query lQuery = null;
		
		try{
			lSBQuery.append("update MstEmpGpfAcc");		
			lSBQuery.append(" set currentBalance = :currentBalance, monthlySubscription = :monthlySubscription");
			lSBQuery.append(" WHERE sevaarthId = :sevaarthId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("currentBalance",Double.parseDouble(lStrCurBalance));
			lQuery.setParameter("monthlySubscription",Double.parseDouble(lStrMonthlySubscription));
			lQuery.setParameter("sevaarthId", lStrSevaarthID);
			lQuery.executeUpdate();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
	}
	
	public void updateEmpSubscription(String lStrSubscriptionID, String lStrReqType) throws Exception
	{
		StringBuilder lSBQuery = new StringBuilder();	
		Query lQuery = null;
		
		try{
			lSBQuery.append("update MstGpfEmpSubscription ");
			if(lStrReqType.equals("Approve")){
				lSBQuery.append("set statusFlag = 'A' ");
			}else if(lStrReqType.equals("Discard")){
				lSBQuery.append("set statusFlag = 'X' ");
			}
			lSBQuery.append("WHERE gpfEmpSubscriptionId = :gpfEmpSubscriptionId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfEmpSubscriptionId",Long.parseLong(lStrSubscriptionID));
			lQuery.executeUpdate();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
	}
	
	public void updateGpfMonthly(String lStrMonthlyID, Integer lIntMonth, Long lLngFinYear, String lStrGpfAccNo) throws Exception
	{
		Double lDblRegularSub = null;
		List lLstRegularSubDtls = null;
		Query lQuerySub = null;
		try{
			lDblRegularSub = getMonthlySubscription(lIntMonth, lLngFinYear, lStrGpfAccNo);
			
			//Add Regular Subscription in MstGpfMonthly table
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("update MstGpfMonthly ");
			lSBQuery.append("set status = 'A' , regularSubscription =:regularSubscription ");
			lSBQuery.append("WHERE mstGpfMonthlyId = :mstGpfMonthlyId");
			Query lQuery = null;
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("mstGpfMonthlyId",Long.parseLong(lStrMonthlyID));
			lQuery.setParameter("regularSubscription",lDblRegularSub);
			lQuery.executeUpdate();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
	}
	
	public void updateChallanDetails(String lStrChallanID) throws Exception
	{
		StringBuilder lSBQuery = new StringBuilder();	
		Query lQuery = null;
		
		try{
			lSBQuery.append("update MstGpfChallanDtls ");
			lSBQuery.append("set statusFlag = 'A' WHERE mstGpfChallanDtlsId = :mstGpfChallanDtlsId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("mstGpfChallanDtlsId",Long.parseLong(lStrChallanID));
			lQuery.executeUpdate();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
	}
	
	public void updateAdvanceDetails(String lStrAdvanceID) throws Exception
	{
		StringBuilder lSBQuery = new StringBuilder();	
		Query lQuery = null;
		
		try{
			lSBQuery.append("update MstGpfAdvance ");
			lSBQuery.append("set statusFlag = 'X' WHERE mstGpfAdvanceId = :mstGpfAdvanceId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("mstGpfAdvanceId",Long.parseLong(lStrAdvanceID));
			lQuery.executeUpdate();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
	}
	
	public Long getMonthlyIDForMonth(String lStrGpfAccNo, Integer lIntMonthId, Long lLngYearId) throws Exception {
		List lLstScheduleData = new ArrayList();
		Long lLngMonthlyID = null;
		StringBuilder lSBQuery = new StringBuilder();
		try {
			lSBQuery.append("SELECT mstGpfMonthlyId from MstGpfMonthly ");
			lSBQuery
					.append(" WHERE gpfAccNo= :gpfAccNo AND finYearId = :finYearId AND monthId =:monthId");

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lQuery.setParameter("finYearId", lLngYearId);
			lQuery.setParameter("monthId", lIntMonthId.longValue());

			lLstScheduleData = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception in isDataExistInMonthly of GPFDataEntryFormDAOImpl  : ", e);			
		}
		if (lLstScheduleData != null && lLstScheduleData.size() > 0) {
			lLngMonthlyID = (Long) lLstScheduleData.get(0);
		} 
		return lLngMonthlyID;
	}
	
	public Double getMonthlySubscription(Integer lIntMonth, Long lLngFinYear, String lStrGpfAccNo)throws Exception {
		
		Double lDblRegularSub = null;
		List lLstRegularSubDtls = null;
		Query lQuerySub = null;
		try{
			StringBuilder lSBQuerySub = new StringBuilder();
			if(lIntMonth >= 1 && lIntMonth <=3){
				lSBQuerySub.append("SELECT monthlySubscription FROM MstGpfEmpSubscription WHERE effectFromMonth <= :effectFromMonth" +
				" AND finYearId = :finYearId AND gpfAccNo = :gpfAccNo ORDER BY effectFromMonth DESC");				
				lQuerySub = ghibSession.createQuery(lSBQuerySub.toString());				
				lQuerySub.setParameter("finYearId", lLngFinYear);
				lQuerySub.setParameter("gpfAccNo", lStrGpfAccNo);
				lQuerySub.setParameter("effectFromMonth", lIntMonth);
				lLstRegularSubDtls = lQuerySub.list();
				
				if(lLstRegularSubDtls.size() == 0){
					lSBQuerySub = new StringBuilder();
					lSBQuerySub.append("SELECT monthlySubscription FROM MstGpfEmpSubscription WHERE " +
					"finYearId = :prevFinYearId AND gpfAccNo = :gpfAccNo ORDER BY effectFromMonth DESC");			
					lQuerySub = ghibSession.createQuery(lSBQuerySub.toString());					
					lQuerySub.setParameter("prevFinYearId", lLngFinYear -1);
					lQuerySub.setParameter("gpfAccNo", lStrGpfAccNo);
					lLstRegularSubDtls = lQuerySub.list();
				}
			}
			else{
				lSBQuerySub.append("SELECT monthlySubscription FROM MstGpfEmpSubscription WHERE effectFromMonth <= :effectFromMonth " +
					"AND finYearId = :finYearId AND gpfAccNo = :gpfAccNo ORDER BY effectFromMonth DESC");
				lQuerySub = ghibSession.createQuery(lSBQuerySub.toString());
				lQuerySub.setParameter("effectFromMonth", lIntMonth);
				lQuerySub.setParameter("finYearId", lLngFinYear);				
				lQuerySub.setParameter("gpfAccNo", lStrGpfAccNo);
				lLstRegularSubDtls = lQuerySub.list();
			}	
			
			if(lLstRegularSubDtls != null && !lLstRegularSubDtls.isEmpty()){
				lDblRegularSub = (Double) lLstRegularSubDtls.get(0);
			}else{
				lSBQuerySub = new StringBuilder();
				lSBQuerySub.append("SELECT monthlySubscription FROM MstEmpGpfAcc WHERE gpfAccNo = :gpfAccNo");
				Query lQueryEmpAcc = null;
				lQueryEmpAcc = ghibSession.createQuery(lSBQuerySub.toString());
				lQueryEmpAcc.setParameter("gpfAccNo", lStrGpfAccNo);
				lDblRegularSub = (Double) lQueryEmpAcc.list().get(0);
			}
		}catch (Exception e) {
			logger.error("Exception in getMonthlySubscription of GPFDataEntryFormDAOImpl  : ", e);
		}
		
		return lDblRegularSub;
	}
	
	public Double getChallanPaidForMonth(String lStrGpfAccNo, Integer lLngMonthId, String lStrFinYear,Long lLngChallanType) throws Exception{

		List lLstChallan = null;
		Double lDblChallanAmount = 0d;
		Integer lIntYearCode = Integer.parseInt(lStrFinYear.substring(0, 4));
		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT SUM(amount)");
			lSBQuery.append(" FROM MstGpfChallanDtls");
			lSBQuery
					.append(" WHERE gpfAccNo=:gpfAccNo AND MONTH(challanDate) =:monthId AND YEAR(challanDate)=:yearCode AND challanType=:challanType");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lQuery.setParameter("monthId", lLngMonthId);
			lQuery.setParameter("yearCode", lIntYearCode);
			lQuery.setParameter("challanType", lLngChallanType);

			lLstChallan = lQuery.list();
			if (lLstChallan != null && lLstChallan.size() > 0 && lLstChallan.get(0) != null) {
				lDblChallanAmount = (Double) lLstChallan.get(0);
			}
		} catch (Exception e) {
			logger.error("Exception in getChallanPaidForMonth of ScheduleGenerationDAOImpl  : ", e);			
		}
		return lDblChallanAmount;
	}
}
