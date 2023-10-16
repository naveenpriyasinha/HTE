/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 29, 2011		Meeta Thacker								
 *******************************************************************************
 */
package com.tcs.sgv.gpf.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;

/**
 * Class Description -
 * 
 * 
 * @author Meeta Thacker
 * @version 0.1
 * @since JDK 5.0 Jun 29, 2011
 */
public class GPFRequestProcessDAOImpl extends GenericDaoHibernateImpl implements GPFRequestProcessDAO {
	Session ghibSession = null;

	public GPFRequestProcessDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	/*
	 * Method to get employee basic information (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getEmployeeDetail(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public List getEmployeeDetail(String lStrSevaarthId, String empName, String criteria, String lStrDdoCode,
			String lStrUser) throws Exception {
		List gpfEmpList = new ArrayList();
		try {

			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			lSBQuery
					.append("select ME.name,MEG.currentBalance,ME.gender,ME.basicPay,OM.dsgnName,MEG.monthlySubscription,");
			lSBQuery.append("MEG.gpfAccNo,ME.doj,ME.building_address,");
			lSBQuery.append("ME.building_street,ME.landmark,ME.district,ME.state,ME.pincode,ME.cntctNo,ME.cellNo,");
			lSBQuery.append("OEM.empSrvcExp,CLM.lookupName,ME.payScale,OEM.empId,ME.basicPay,ME.dob,MEG.sevaarthId");
			lSBQuery.append(" FROM MstEmp ME,MstEmpGpfAcc MEG,OrgEmpMst OEM,OrgDesignationMst OM,CmnLookupMst CLM");
			lSBQuery
					.append(" WHERE CLM.lookupId = ME.payCommission and ME.dcpsEmpId = MEG.mstGpfEmpId and ME.dcpsOrGpf='N' and OEM.empId = ME.orgEmpMstId");
			lSBQuery.append(" and ME.designation = OM.dsgnId AND ME.group ='D' ");
			if (lStrUser.equals("DEO")) {
				lSBQuery.append(" AND MEG.ddoCode=:ddoCode");
			}

			if (criteria.equals("1")) {

				lSBQuery.append(" and MEG.sevaarthId = :sevaarthId");

				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("sevaarthId", lStrSevaarthId);

			} else if (criteria.equals("2")) {

				lSBQuery.append(" and ME.name = :name");

				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("name", empName);
			} else {
				lSBQuery.append(" and MEG.sevaarthId = :sevaarthId and ME.name = :name");

				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("sevaarthId", lStrSevaarthId);
				lQuery.setParameter("name", empName);
			}
			if (lStrUser.equals("DEO")) {
				lQuery.setParameter("ddoCode", lStrDdoCode);
			}
			gpfEmpList = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception in getEmployeeDetail of GPFRequestProcessDAOImpl  : ", e);			
			throw e;
		}
		return gpfEmpList;
	}

	/*
	 * Method to get the Request Worklist for the workflow users (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getGPFRequestList(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.util.Date)
	 */
	public List getGPFRequestList(String lStrUserType, String lStrPostId, String lStrLocationCode, String lStrCriteria,
			String lStrName, Date lDtSaveDate) {
		List empListForDeoAppover = new ArrayList();
		List advanceRequestList = new ArrayList();
		List finalWithdrawReqList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		StringBuilder lSBQueryForAdvance = new StringBuilder();
		StringBuilder lSBQueryForFinal = new StringBuilder();
		Query lQuery = null;
		Query lQueryForAdvance = null;
		Query lQueryForFinalWithdraw = null;
		Date lDtToDate = null;
		Date lDtTemp = lDtSaveDate;
		try {
			if (lDtSaveDate != null) {
				lDtToDate = (Date) lDtSaveDate.clone();
				lDtToDate.setDate(lDtTemp.getDate() + 1);
			}

			lSBQuery
					.append("select ME.name,CS.transactionId,MEG.sevaarthId,CS.gpfAccNo,CS.createdDate,'CS' ,CS.gpfEmpSubscriptionId");
			if (lStrUserType.equals("DEOAPP")) {
				lSBQuery.append(",CS.deoActionDate");
			} else if (lStrUserType.equals("HO")) {
				lSBQuery.append(",CS.hoReceiveDate");
			}
			lSBQuery.append(" FROM MstGpfEmpSubscription CS,WfJobMst WJ, MstEmpGpfAcc MEG, MstEmp ME");
			lSBQuery
					.append(" WHERE CS.gpfAccNo = MEG.gpfAccNo AND CS.statusFlag LIKE 'F%' AND MEG.mstGpfEmpId = ME.dcpsEmpId and ME.dcpsOrGpf='N' ");
			lSBQuery
					.append(" AND WJ.jobRefId = CS.gpfEmpSubscriptionId AND WJ.lstActPostId = :postId AND WJ.wfDocMst.docId = :docId AND ME.group ='D'");

			if (lStrCriteria.equals("name")) {
				lSBQuery.append(" And ME.name = :name");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("name", lStrName);
			} else if (lStrCriteria.equals("date")) {
				lSBQuery.append(" And CS.createdDate >= :fromDate AND CS.createdDate < :toDate");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setDate("fromDate", lDtSaveDate);
				lQuery.setDate("toDate", lDtToDate);
			} else if (lStrCriteria.equals("both")) {
				lSBQuery.append(" And ME.name = :name ");
				lSBQuery.append(" And CS.createdDate >= :fromDate AND CS.createdDate < :toDate");
				lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("name", lStrName);
				lQuery.setDate("fromDate", lDtSaveDate);
				lQuery.setDate("toDate", lDtToDate);
			} else {
				lQuery = ghibSession.createQuery(lSBQuery.toString());
			}

			lQuery.setParameter("docId", 800001L);
			lQuery.setParameter("postId", lStrPostId);
			// lQuery.setParameter("locCode", lStrLocationCode);
			empListForDeoAppover = lQuery.list();

			lSBQueryForAdvance
					.append("select ME.name ,MGA.transactionId,MEG.sevaarthId,MGA.gpfAccNo,MGA.createdDate,MGA.advanceType,MGA.mstGpfAdvanceId");
			if (lStrUserType.equals("DEOAPP")) {
				lSBQueryForAdvance.append(",MGA.deoActionDate");
			} else if (lStrUserType.equals("HO")) {
				lSBQueryForAdvance.append(",MGA.hoReceiveDate");
			}
			lSBQueryForAdvance.append(" FROM MstGpfAdvance MGA,WfJobMst WJ, MstEmpGpfAcc MEG, MstEmp ME");
			lSBQueryForAdvance
					.append(" WHERE MGA.gpfAccNo = MEG.gpfAccNo AND MGA.statusFlag LIKE 'F%' AND MEG.mstGpfEmpId = ME.dcpsEmpId and ME.dcpsOrGpf='N' ");
			lSBQueryForAdvance
					.append(" AND WJ.jobRefId = MGA.mstGpfAdvanceId AND WJ.lstActPostId = :postId AND WJ.wfDocMst.docId = :docId AND ME.group ='D'");

			if (lStrCriteria.equals("name")) {
				lSBQueryForAdvance.append(" And ME.name = :name");
				lQueryForAdvance = ghibSession.createQuery(lSBQueryForAdvance.toString());
				lQueryForAdvance.setParameter("name", lStrName);
			} else if (lStrCriteria.equals("date")) {
				lSBQueryForAdvance.append(" And MGA.createdDate >= :fromDate AND MGA.createdDate < :toDate");
				lQueryForAdvance = ghibSession.createQuery(lSBQueryForAdvance.toString());
				lQueryForAdvance.setDate("fromDate", lDtSaveDate);
				lQueryForAdvance.setDate("toDate", lDtToDate);
			} else if (lStrCriteria.equals("both")) {
				lSBQueryForAdvance.append(" And ME.name = :name ");
				lSBQueryForAdvance.append(" And MGA.createdDate >= :fromDate AND MGA.createdDate < :toDate");
				lQueryForAdvance = ghibSession.createQuery(lSBQueryForAdvance.toString());
				lQueryForAdvance.setParameter("name", lStrName);
				lQueryForAdvance.setDate("fromDate", lDtSaveDate);
				lQueryForAdvance.setDate("toDate", lDtToDate);
			} else {
				lQueryForAdvance = ghibSession.createQuery(lSBQueryForAdvance.toString());
			}

			lQueryForAdvance.setParameter("docId", 800002L);
			lQueryForAdvance.setParameter("postId", lStrPostId);
			// lQueryForAdvance.setParameter("locCode", lStrLocationCode);
			advanceRequestList = lQueryForAdvance.list();

			lSBQueryForFinal
					.append("select ME.name ,TGF.transactionId,MEG.sevaarthId,TGF.gpfAccNo,TGF.createdDate,'FW',TGF.trnGpfFinalWithdrawalId");
			if (lStrUserType.equals("DEOAPP")) {
				lSBQueryForFinal.append(",TGF.deoActionDate");
			} else if (lStrUserType.equals("HO")) {
				lSBQueryForFinal.append(",TGF.hoReceiveDate");
			}
			lSBQueryForFinal.append(" FROM TrnGpfFinalWithdrawal TGF,WfJobMst WJ, MstEmpGpfAcc MEG, MstEmp ME");
			lSBQueryForFinal
					.append(" WHERE TGF.gpfAccNo = MEG.gpfAccNo AND TGF.reqStatus LIKE 'F%' AND MEG.mstGpfEmpId = ME.dcpsEmpId and ME.dcpsOrGpf='N' ");
			lSBQueryForFinal
					.append(" AND WJ.jobRefId = TGF.trnGpfFinalWithdrawalId AND WJ.lstActPostId = :postId AND WJ.wfDocMst.docId = :docId AND ME.group ='D'");

			if (lStrCriteria.equals("name")) {
				lSBQueryForFinal.append(" And ME.name = :name");
				lQueryForFinalWithdraw = ghibSession.createQuery(lSBQueryForFinal.toString());
				lQueryForFinalWithdraw.setParameter("name", lStrName);
			} else if (lStrCriteria.equals("date")) {
				lSBQueryForFinal.append(" And TGF.createdDate >= :fromDate AND TGF.createdDate < :toDate");
				lQueryForFinalWithdraw = ghibSession.createQuery(lSBQueryForFinal.toString());
				lQueryForFinalWithdraw.setDate("fromDate", lDtSaveDate);
				lQueryForFinalWithdraw.setDate("toDate", lDtToDate);
			} else if (lStrCriteria.equals("both")) {
				lSBQueryForFinal.append(" And ME.name = :name");
				lSBQueryForFinal.append(" And TGF.createdDate >= :fromDate AND TGF.createdDate < :toDate");
				lQueryForFinalWithdraw = ghibSession.createQuery(lSBQueryForFinal.toString());
				lQueryForFinalWithdraw.setParameter("name", lStrName);
				lQueryForFinalWithdraw.setDate("fromDate", lDtSaveDate);
				lQueryForFinalWithdraw.setDate("toDate", lDtToDate);
			} else {
				lQueryForFinalWithdraw = ghibSession.createQuery(lSBQueryForFinal.toString());
			}
			lQueryForFinalWithdraw.setParameter("docId", 800003L);
			lQueryForFinalWithdraw.setParameter("postId", lStrPostId);
			// lQueryForFinalWithdraw.setParameter("locCode", lStrLocationCode);
			finalWithdrawReqList = lQueryForFinalWithdraw.list();

			empListForDeoAppover.addAll(advanceRequestList);
			empListForDeoAppover.addAll(finalWithdrawReqList);
		} catch (Exception e) {
			logger.error("Exception in getGPFRequestList of GPFRequestProcessDAOImpl  : ", e);			
		}
		return empListForDeoAppover;
	}

	/*
	 * Method to get the Withdrawal(Non-refundable advance )details for the
	 * employee (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getWithdrawalDetail(java.lang
	 * .String, java.lang.String)
	 */
	public List getWithdrawalDetail(String lStrGpfAccNo, String lStrAdvanceType) {
		List withdrawalList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		try {
			lSBQuery
					.append("select transactionId,createdDate,advanceAmt,amountSanctioned,sancAuthorityName,sanctionedDate");
			lSBQuery.append(" FROM MstGpfAdvance");
			lSBQuery.append(" WHERE gpfAccNo = :gpfAccNo AND statusFlag = 'A'");
			lSBQuery.append(" AND advanceType = :advanceType");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lQuery.setParameter("advanceType", lStrAdvanceType);

			withdrawalList = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception in getWithdrawalDetail of GPFRequestProcessDAOImpl  : ", e);			
		}
		return withdrawalList;
	}

	/*
	 * Method to get the Advance total of employee from starting of the year to
	 * till date (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getAdvanceHistory(java.lang.
	 * String, java.lang.Long)
	 */
	public List getAdvanceHistory(String lStrGpfAccNo, Long lLngYearId) {
		List advanceHistoryList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		try {
			lSBQuery.append("SELECT advanceType, SUM(amountSanctioned)");
			lSBQuery.append(" FROM MstGpfAdvance");
			lSBQuery
					.append(" WHERE gpfAccNo = :gpfAccNo AND finYearId = :finYearId AND statusFlag = 'A' GROUP BY advanceType ORDER BY advanceType");

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lQuery.setParameter("finYearId", lLngYearId);

			advanceHistoryList = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception in getAdvanceHistory of GPFRequestProcessDAOImpl  : ", e);			
		}
		return advanceHistoryList;
	}

	/*
	 * Method to get the GPF Account balance of Employee (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getGPFAccountBalance(java.lang
	 * .String, java.lang.Long)
	 */
	public List getGPFAccountBalance(String lStrGpfAccNo, Long lLngYearId) {
		List lLstAccountBalance = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();

		Long lLngNxtYearId = lLngYearId + 1l;
		try {
			lSBQuery
					.append("SELECT COALESCE(SUM(MGM.regular_Subscription),0),COALESCE(SUM(MGM.advance_Recovery),0),COALESCE(SUM(deputation_Challan),0),COALESCE(SUM(pre_Pay_Of_Advance),0),COALESCE(SUM(excess_Payment),0),COUNT(*)");
			lSBQuery.append(" FROM Mst_Gpf_Monthly MGM");
			lSBQuery.append(" WHERE ((fin_Year_Id = " + lLngYearId + " AND month_Id>3) OR (fin_Year_Id = "
					+ lLngNxtYearId + " AND month_Id<=3)) AND MGM.gpf_Acc_No = '" + lStrGpfAccNo + "'");

			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			// lQuery.setParameter("finYearId", lLngYearId);
			// lQuery.setParameter("nxtFinYearId", lLngNxtYearId);
			// / lQuery.setParameter("monthId", lLngMonthId);
			// lQuery.setParameter("gpfAccNo", lStrGpfAccNo);

			lLstAccountBalance = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception in getGPFAccountBalance of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lLstAccountBalance;
	}

	/*
	 * Method to get DP or GP according to Pay Commission of employee
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getDPOrGP(java.lang.Long,
	 * java.lang.String)
	 */
	public Long getDPOrGP(Long lLngEmpId, String lStrPayComm) {
		List salaryList = new ArrayList();
		Long lLngGradePay = 0L;
		StringBuilder lSBQuery = new StringBuilder();
		try {
			if (lStrPayComm.equals("6th Pay Commission")) {
				lSBQuery.append("SELECT HPEM.empAllowAmount");
				lSBQuery.append("   FROM HrEisOtherDtls HOD, HrPayEmpallowMpg HPEM");
				lSBQuery
						.append("  WHERE HPEM.hrEisEmpMst.orgEmpMst.empId = :empId AND HPEM.hrPayAllowTypeMst.allowCode = 145 AND HPEM.month=-1 AND HPEM.year=-1");

				Query lQuery = ghibSession.createQuery(lSBQuery.toString());

				lQuery.setParameter("empId", lLngEmpId);

				salaryList = lQuery.list();
				if (salaryList != null && salaryList.size() > 0) {

					lLngGradePay = (Long) salaryList.get(0);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getDPOrGP of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lLngGradePay;
	}

	/*
	 * Method to generate the transaction id for new GPF request (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getNewTransactionId(java.lang
	 * .String)
	 */
	public String getNewTransactionId(String lStrSevaarthId) {

		StringBuilder lSBQuery = new StringBuilder();
		new StringBuilder();
		new StringBuilder();
		List tempList = new ArrayList<Long>();
		new ArrayList<Long>();
		new ArrayList<Long>();
		Long lLngNewTransactionId = 0L;
		String lStrNewTrnId = null;
		String lStrTrnsId = "";
		String lStrMonth = "";

		Calendar cal = Calendar.getInstance();
		try {
			Integer lIntMonth = cal.get(Calendar.MONTH) + 1;
			Integer lIntYear = cal.get(Calendar.YEAR);
			if (lIntMonth.toString().length() == 1) {
				lStrMonth = "0" + lIntMonth;
			} else {
				lStrMonth = lIntMonth.toString();
			}
			// code to get the First letter of Transaction Id (i.e. organization
			// id) from Sevaarth Id of employee

			// lStrTrnsId = lStrSevaarthId.charAt(0) + lStrMonth +
			// lIntYear.toString().substring(2, 4);
			lStrTrnsId = "2" + lStrMonth + lIntYear.toString().substring(2, 4);
			lSBQuery.append(" SELECT MAX(transactionId) FROM MstGpfReq WHERE transactionId LIKE :lStrTrnsId");

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("lStrTrnsId", lStrTrnsId + '%');
			tempList = lQuery.list();

			if (tempList != null && tempList.size() > 0 && tempList.get(0) != null) {
				lLngNewTransactionId = (Long.parseLong(tempList.get(0).toString())) + 1L;
				lStrNewTrnId = lLngNewTransactionId.toString();
			} else {
				lStrNewTrnId = String.format(lStrTrnsId + "%06d", 1);
			}
		} catch (Exception e) {
			logger.error("Exception in getNewTransactionId of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lStrNewTrnId;
	}

	/*
	 * Method to get the DDO Code for DDO Assistant post id (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getDdoCode(java.lang.Long)
	 */
	public String getDdoCode(Long lLngAsstPostId) {

		StringBuilder lSBQuery = new StringBuilder();
		List lLstCodeList = null;
		String lStrDdoCode = "";
		try {
			lSBQuery.append(" SELECT OD.ddoCode");
			lSBQuery.append(" FROM RltDdoAsst RD, OrgDdoMst OD");
			lSBQuery.append(" WHERE OD.postId = RD.ddoPostId AND RD.asstPostId = :asstPostId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("asstPostId", lLngAsstPostId);

			lLstCodeList = lQuery.list();

			lStrDdoCode = lLstCodeList.get(0).toString();
		} catch (Exception e) {
			logger.error("Exception in getDdoCode of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lStrDdoCode;
	}

	/*
	 * Method to get the DDO Code for Verifier or HO post id (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getDDOPostIdForVerifierHo(java
	 * .lang.Long, java.lang.String)
	 */
	public Long getDDOPostIdForVerifierHo(Long lLngPostId, String lStrUserType) {

		Long lLongDdoPostId = null;
		List lLstDdoDtls = null;

		try {
			getSession();
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT ddoPostId");
			lSBQuery.append(" FROM  RltGpfDdoVerifierHo");

			if (lStrUserType.equals("DEOAPP")) {
				lSBQuery.append(" WHERE verifierPostId = :postId ");
			} else if (lStrUserType.equals("HO")) {
				lSBQuery.append(" WHERE hoPostId = :postId ");
			}

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("postId", lLngPostId);

			lLstDdoDtls = lQuery.list();

			if (lLstDdoDtls.size() != 0) {
				lLongDdoPostId = Long.valueOf(lLstDdoDtls.get(0).toString());
			}

		} catch (Exception e) {
			logger.error("Exception in getDDOPostIdForVerifierHo of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lLongDdoPostId;
	}

	/*
	 * Method to ge the DDO Code for DDO post id (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getDdoCodeForDDO(java.lang.Long)
	 */
	public String getDdoCodeForDDO(Long lLngPostId) {

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
			logger.error("Exception in getDdoCodeForDDO of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lStrDdoCode;
	}

	// 
	// public Double getMonthlySubscription(String lStrGpfAccNo, Integer
	// lIntMonthId, Integer lIntFinYearId) {
	//
	// List<Double> lMonthlySubsList = new ArrayList<Double>();
	// Double lDblMonthlySubs = null;
	// StringBuilder lSBQuery = new StringBuilder();
	// lSBQuery.append("select monthlySubscription");
	// lSBQuery.append(" FROM MstGpfEmpSubscription");
	// lSBQuery.append(" WHERE gpfAccNo = :gpfAccNo AND activeFlag = 1");
	// lSBQuery.append(" AND effectFromMonth <= :MonthId AND finYearId = :finYearId");
	// Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	// lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
	// lQuery.setParameter("MonthId", lIntMonthId);
	// lQuery.setParameter("finYearId",
	// Long.parseLong(lIntFinYearId.toString()));
	//
	// lMonthlySubsList = lQuery.list();
	// if (lMonthlySubsList != null && lMonthlySubsList.size() != 0) {
	// lDblMonthlySubs = lMonthlySubsList.get(0);
	// }
	// return lDblMonthlySubs;
	// }

	/*
	 * Method to get the Advance(Refundable advance )details for the employee
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getAdvanceDetail(java.lang.String
	 * , java.lang.String)
	 */
	public List getAdvanceDetail(String lStrGpfAccNo, String lStrAdvanceType) {
		List advanceList = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		try {
			lSBQuery
					.append("select transactionId,createdDate,advanceAmt,amountSanctioned,noOfInstallments,installmentAmount,outstandingAmount,sancAuthorityName,sanctionedDate");
			lSBQuery.append(" FROM MstGpfAdvance");
			lSBQuery.append(" WHERE gpfAccNo = :gpfAccNo AND statusFlag = 'A'");
			lSBQuery.append(" AND advanceType = :advanceType");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lQuery.setParameter("advanceType", lStrAdvanceType);

			advanceList = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception in getAdvanceDetail of GPFRequestProcessDAOImpl  : ", e);			
		}
		return advanceList;
	}

	/*
	 * Method to get the pay scale data from payroll module (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getPayScaleData(java.lang.Long)
	 */
	public HrEisScaleMst getPayScaleData(Long lLngEmpId) {
		HrEisScaleMst hrOtherInfo = new HrEisScaleMst();
		Session hibSession = getSession();
		try {
			String query1 = "select  empLookup.hrEisSgdMpg.hrEisScaleMst  from HrEisOtherDtls as empLookup where empLookup.hrEisEmpMst.orgEmpMst.empId = "
					+ lLngEmpId;
			Query sqlQuery1 = hibSession.createQuery(query1);

			if (sqlQuery1.list().size() < 1) {
				hrOtherInfo = null;
			} else {
				hrOtherInfo = (HrEisScaleMst) sqlQuery1.uniqueResult();
				logger.info("setting sqlQuery's uniqueResult");
			}
		} catch (Exception e) {
			logger.error("Exception in getPayScaleData of GPFRequestProcessDAOImpl  : ", e);			
		}
		return hrOtherInfo;
	}

	/*
	 * Method to check if employee has already taken a non-refundable advance
	 * within 1 year (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#withdrawalExistsForFinYear(java
	 * .lang.String, java.lang.Long)
	 */
	public Boolean withdrawalExistsForFinYear(String strGpfAccNo, Long lLngFinYearId) {
		List savedRA = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		try {
			lSBQuery.append("select MGA.transactionId");
			lSBQuery.append(" FROM MstGpfAdvance MGA");
			lSBQuery
					.append(" WHERE MGA.gpfAccNo = :gpfAccNo AND MGA.statusFlag = 'A' AND MGA.advanceType = 'NRA' AND ((current_date - MGA.sanctionedDate)/365) < 1");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", strGpfAccNo);
			// lQuery.setParameter("finYear", lLngFinYearId);
			savedRA = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception in withdrawalExistsForFinYear of GPFRequestProcessDAOImpl  : ", e);			
		}
		if (savedRA.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Method to get the employee name list for auto complete (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getEmpNameForAutoComplete(java
	 * .lang.String, java.lang.String)
	 */
	public List getEmpNameForAutoComplete(String searchKey, String lStrDdoCode) {
		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;
		try {
			sb.append("select dcpsEmpId,name from MstEmp where UPPER(name) LIKE UPPER(:searchKey) AND group ='D'");
			if (lStrDdoCode != null) {
				if (!"".equals(lStrDdoCode)) {
					sb.append(" and ddoCode = :ddoCode");
				}
			}
			selectQuery = ghibSession.createQuery(sb.toString());
			selectQuery.setParameter("searchKey", searchKey + '%');
			selectQuery.setParameter("ddoCode", lStrDdoCode);

			List resultList = selectQuery.list();

			cmbVO = new ComboValuesVO();

			if (resultList != null && resultList.size() > 0) {
				Iterator it = resultList.iterator();
				while (it.hasNext()) {
					cmbVO = new ComboValuesVO();
					obj = (Object[]) it.next();
					cmbVO.setId(obj[1].toString());
					cmbVO.setDesc(obj[1].toString());
					finalList.add(cmbVO);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpNameForAutoComplete of GPFRequestProcessDAOImpl  : ", e);			
		}
		return finalList;
	}

	/*
	 * Method to get the Opening balance of current financial year (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getOpeningBalForCurrYear(java
	 * .lang.String, java.lang.Long)
	 */
	public Double getOpeningBalForCurrYear(String lStrGpfAccNo, Long lLngYearId) throws Exception {
		Double lDblOpeningBal = null;
		List lLstOpeningBal = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		try {
			lSBQuery
					.append("SELECT closingBalance FROM MstGpfYearly WHERE gpfAccNo = :gpfAccNo AND finYearId = :finYearId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("gpfAccNo", lStrGpfAccNo);
			lQuery.setParameter("finYearId", lLngYearId - 1);

			lLstOpeningBal = lQuery.list();
			if (lLstOpeningBal != null && lLstOpeningBal.size() > 0) {
				lDblOpeningBal = Double.parseDouble((lLstOpeningBal.get(0).toString()));
			} else {
				lDblOpeningBal = 0d;
			}
		} catch (Exception e) {
			logger.error("Exception in getOpeningBalForCurrYear of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lDblOpeningBal;
	}

	/*
	 * Method to get location code from post id of user (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getLocationCodeOfUser(java.lang
	 * .Long)
	 */
	public String getLocationCodeOfUser(Long lLngPostId) throws Exception {
		StringBuilder lSBQuery = new StringBuilder();
		List lLstCodeList = null;
		String lStrLocationCode = "";
		try {
			lSBQuery.append(" SELECT PM.locationCode");
			lSBQuery.append(" FROM OrgPostMst PM");
			lSBQuery.append(" WHERE PM.postId = :postId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("postId", lLngPostId);

			lLstCodeList = lQuery.list();

			lStrLocationCode = lLstCodeList.get(0).toString();
		} catch (Exception e) {
			logger.error("Exception in getLocationCodeOfUser of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lStrLocationCode;
	}

	/*
	 * Method to get Draft/Rejected GPF Request List (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getDraftRequestList(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String,
	 * java.util.Date)
	 */
	public List getDraftRequestList(String lStrPostId, String lStrDdoCode, String lStrCriteria, String lStrName,
			Date lDtSaveDate) throws Exception {
		List lLstChangeSubs = new ArrayList();
		List lLstAdvance = new ArrayList();
		List lLstFinalWithdraw = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		StringBuilder lSBQueryForAdvance = new StringBuilder();
		StringBuilder lSBQueryForFinal = new StringBuilder();
		Date lDtToDate = null;
		Date lDtTemp = lDtSaveDate;
		try {
			if (lDtSaveDate != null) {
				lDtToDate = (Date) lDtSaveDate.clone();
				lDtToDate.setDate(lDtTemp.getDate() + 1);
			}

			lSBQuery
					.append("select ME.name,CS.transactionId,MEG.sevaarthId,CS.gpfAccNo,CS.applicationDate,'CS' ,CS.gpfEmpSubscriptionId,CS.approverRemarks,CS.hoRemarks,CS.statusFlag,CS.createdDate");
			lSBQuery.append(" FROM MstGpfEmpSubscription CS, MstEmpGpfAcc MEG, MstEmp ME");
			lSBQuery
					.append(" WHERE CS.gpfAccNo = MEG.gpfAccNo AND CS.statusFlag IN ('D','R') AND MEG.mstGpfEmpId = ME.dcpsEmpId and ME.dcpsOrGpf='N' ");
			lSBQuery.append(" AND ME.ddoCode=:ddoCode AND ME.group ='D' ");

			if (lStrCriteria.equalsIgnoreCase("name")) {
				lSBQuery.append(" AND ME.name = :name ");
				Query lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("name", lStrName);
				lQuery.setParameter("ddoCode", lStrDdoCode);
				lLstChangeSubs = lQuery.list();
			} else if (lStrCriteria.equalsIgnoreCase("date")) {
				lSBQuery.append(" AND CS.createdDate >= :fromDate AND CS.createdDate < :toDate");
				Query lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setDate("fromDate", lDtSaveDate);
				lQuery.setDate("toDate", lDtToDate);
				lQuery.setParameter("ddoCode", lStrDdoCode);
				lLstChangeSubs = lQuery.list();
			} else if (lStrCriteria.equalsIgnoreCase("both")) {
				lSBQuery.append(" AND ME.name = :name AND CS.createdDate >= :fromDate AND CS.createdDate < :toDate ");
				Query lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("name", lStrName);
				lQuery.setDate("fromDate", lDtSaveDate);
				lQuery.setDate("toDate", lDtToDate);
				lQuery.setParameter("ddoCode", lStrDdoCode);
				lLstChangeSubs = lQuery.list();
			} else {
				Query lQuery = ghibSession.createQuery(lSBQuery.toString());
				lQuery.setParameter("ddoCode", lStrDdoCode);
				lLstChangeSubs = lQuery.list();
			}

			lSBQueryForAdvance
					.append("select ME.name ,MGA.transactionId,MEG.sevaarthId,MGA.gpfAccNo,MGA.applicationDate,MGA.advanceType,MGA.mstGpfAdvanceId,MGA.verifierRemarks,MGA.approverRemarks,MGA.statusFlag,MGA.createdDate");
			lSBQueryForAdvance.append(" FROM MstGpfAdvance MGA, MstEmpGpfAcc MEG, MstEmp ME");
			lSBQueryForAdvance
					.append(" WHERE MGA.gpfAccNo = MEG.gpfAccNo AND MGA.statusFlag IN ('D','R') AND MEG.mstGpfEmpId = ME.dcpsEmpId and ME.dcpsOrGpf='N' ");
			lSBQueryForAdvance.append(" AND ME.ddoCode=:ddoCode AND ME.group ='D' ");

			if (lStrCriteria.equalsIgnoreCase("name")) {
				lSBQueryForAdvance.append(" AND ME.name = :name ");
				Query lQueryForAdvance = ghibSession.createQuery(lSBQueryForAdvance.toString());
				lQueryForAdvance.setParameter("name", lStrName);
				lQueryForAdvance.setParameter("ddoCode", lStrDdoCode);
				lLstAdvance = lQueryForAdvance.list();
			} else if (lStrCriteria.equalsIgnoreCase("date")) {
				lSBQueryForAdvance.append(" AND MGA.createdDate >= :fromDate AND MGA.createdDate < :toDate ");
				Query lQueryForAdvance = ghibSession.createQuery(lSBQueryForAdvance.toString());
				lQueryForAdvance.setDate("fromDate", lDtSaveDate);
				lQueryForAdvance.setDate("toDate", lDtToDate);
				lQueryForAdvance.setParameter("ddoCode", lStrDdoCode);
				lLstAdvance = lQueryForAdvance.list();
			} else if (lStrCriteria.equalsIgnoreCase("both")) {
				lSBQueryForAdvance
						.append(" AND ME.name = :name AND MGA.createdDate >= :fromDate AND MGA.createdDate < :toDate ");
				Query lQueryForAdvance = ghibSession.createQuery(lSBQueryForAdvance.toString());
				lQueryForAdvance.setParameter("name", lStrName);
				lQueryForAdvance.setDate("fromDate", lDtSaveDate);
				lQueryForAdvance.setDate("toDate", lDtToDate);
				lQueryForAdvance.setParameter("ddoCode", lStrDdoCode);
				lLstAdvance = lQueryForAdvance.list();
			} else {
				Query lQueryForAdvance = ghibSession.createQuery(lSBQueryForAdvance.toString());
				lQueryForAdvance.setParameter("ddoCode", lStrDdoCode);
				lLstAdvance = lQueryForAdvance.list();
			}

			lSBQueryForFinal
					.append("select ME.name ,TGF.transactionId,MEG.sevaarthId,TGF.gpfAccNo,TGF.applicationDate,'FW',TGF.trnGpfFinalWithdrawalId,TGF.approverRemarks,TGF.hoRemarks,TGF.reqStatus,TGF.createdDate");
			lSBQueryForFinal.append(" FROM TrnGpfFinalWithdrawal TGF, MstEmpGpfAcc MEG, MstEmp ME");
			lSBQueryForFinal
					.append(" WHERE TGF.gpfAccNo = MEG.gpfAccNo AND TGF.reqStatus IN ('D','R') AND MEG.mstGpfEmpId = ME.dcpsEmpId and ME.dcpsOrGpf='N' ");
			lSBQueryForFinal.append(" AND ME.ddoCode=:ddoCode AND ME.group ='D' ");

			if (lStrCriteria.equalsIgnoreCase("name")) {
				lSBQueryForFinal.append(" AND ME.name = :name ");
				Query lQueryForFinalWithdraw = ghibSession.createQuery(lSBQueryForFinal.toString());
				lQueryForFinalWithdraw.setParameter("name", lStrName);
				lQueryForFinalWithdraw.setParameter("ddoCode", lStrDdoCode);
				lLstFinalWithdraw = lQueryForFinalWithdraw.list();
			} else if (lStrCriteria.equalsIgnoreCase("date")) {
				lSBQueryForFinal.append(" AND TGF.createdDate >= :fromDate AND TGF.createdDate < :toDate ");
				Query lQueryForFinalWithdraw = ghibSession.createQuery(lSBQueryForFinal.toString());
				lQueryForFinalWithdraw.setDate("fromDate", lDtSaveDate);
				lQueryForFinalWithdraw.setDate("toDate", lDtToDate);
				lQueryForFinalWithdraw.setParameter("ddoCode", lStrDdoCode);
				lLstFinalWithdraw = lQueryForFinalWithdraw.list();
			} else if (lStrCriteria.equalsIgnoreCase("both")) {
				lSBQueryForFinal
						.append(" AND ME.name = :name AND TGF.createdDate >= :fromDate AND TGF.createdDate < :toDate ");
				Query lQueryForFinalWithdraw = ghibSession.createQuery(lSBQueryForFinal.toString());
				lQueryForFinalWithdraw.setParameter("name", lStrName);
				lQueryForFinalWithdraw.setDate("fromDate", lDtSaveDate);
				lQueryForFinalWithdraw.setDate("toDate", lDtToDate);
				lQueryForFinalWithdraw.setParameter("ddoCode", lStrDdoCode);
				lLstFinalWithdraw = lQueryForFinalWithdraw.list();
			} else {
				Query lQueryForFinalWithdraw = ghibSession.createQuery(lSBQueryForFinal.toString());
				lQueryForFinalWithdraw.setParameter("ddoCode", lStrDdoCode);
				lLstFinalWithdraw = lQueryForFinalWithdraw.list();
			}

			lLstChangeSubs.addAll(lLstAdvance);
			lLstChangeSubs.addAll(lLstFinalWithdraw);
		} catch (Exception e) {
			logger.error("Exception in getDraftRequestList of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lLstChangeSubs;

	}

	/*
	 * Method to get the District Name from Id (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getDistrictNameForId(java.lang
	 * .Long)
	 */
	public String getDistrictNameForId(Long lLngDistrictId) {
		String lStrDistrictName = null;
		List lLstDistrict = null;
		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT CDM.districtName");
			lSBQuery.append(" FROM  CmnDistrictMst CDM");
			lSBQuery.append(" WHERE CDM.districtId = :districtId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("districtId", lLngDistrictId);

			lLstDistrict = lQuery.list();

			lStrDistrictName = lLstDistrict.get(0).toString();
		} catch (Exception e) {
			logger.error("Exception in getDistrictNameForId of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lStrDistrictName;
	}

	/*
	 * Method to get the State Name from Id (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFRequestProcessDAO#getStateNameForId(java.lang.
	 * Long)
	 */
	public String getStateNameForId(Long lLngStateId) {

		String lStrStateName = null;
		List lLstState = null;
		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT CSM.stateName");
			lSBQuery.append(" FROM  CmnStateMst CSM");
			lSBQuery.append(" WHERE CSM.stateId = :stateId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("stateId", lLngStateId);

			lLstState = lQuery.list();

			lStrStateName = lLstState.get(0).toString();
		} catch (Exception e) {
			logger.error("Exception in getStateNameForId of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lStrStateName;
	}
	
	public boolean isDataEntryComplete(String lStrSevaarthId)
	{
		Boolean lBlData = false;
		List lLstDataEntry = null;
		try {
			StringBuilder lSBQuery = new StringBuilder();			
			lSBQuery.append("FROM  TrnEmpGpfAcc ");
			lSBQuery.append(" WHERE statusFlag = 'A' AND sevaarthId =:sevaarthId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);

			lLstDataEntry = lQuery.list();

			if(lLstDataEntry != null && lLstDataEntry.size() > 0)
			{
				lBlData =  true;
			}else{
				lBlData = false;
			}
		} catch (Exception e) {
			logger.error("Exception in getStateNameForId of GPFRequestProcessDAOImpl  : ", e);			
		}
		return lBlData;
	}
}
