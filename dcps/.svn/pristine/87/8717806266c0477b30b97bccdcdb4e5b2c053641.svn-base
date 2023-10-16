/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 25, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 25, 2011
 */
public class TerminalRequestDAOImpl extends GenericDaoHibernateImpl implements TerminalRequestDAO {

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	/**
	 * 
	 * @param type
	 * @param sessionFactory
	 */
	public TerminalRequestDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);

		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}

	public List getEmpDtlsForName(String lStrName, String lStrDDOCode) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery
				.append("SELECT dcpsEmpId,dcpsId,doj FROM MstEmp where name = :name and ddoCode = :ddoCode and dcpsId is not null");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("name", lStrName.trim());
		query.setParameter("ddoCode", lStrDDOCode.trim());

		List resultList = query.list();
		return resultList;
	}

	public List getAllMissingCreditsForEmp(Long lLongEmpId, Date lDtDOJ, Date lDtTerminationDate) throws Exception {

		List listMissingCredits = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery
				.append(" select ofy.fin_year_desc,ofm.month_name,ofy.fin_year_id,ofm.month_id,nvl(MC.MISSING_CREDIT_ID,0),MC.AMOUNT_DEDUCTION,MC.VOUCHER_NO,MC.VOUCHER_DATE,MC.REMARKS from ");
		lSBQuery.append(" (");
		lSBQuery.append(" select fir.total_year as total_year,fir.total_month as total_month from ");
		lSBQuery
				.append(" (Select Fy.Fin_Year_code As Total_Year,Fm.Month_no Total_month From Sgva_Month_Mst Fm,Sgvc_Fin_Year_Mst Fy Where  (Date(Fy.Fin_Year_Code || '-' || Fm.Month_No || '-' ||01) Between '"
						+ sdf1.format(lDtDOJ)
						+ "' AND '"
						+ sdf1.format(lDtTerminationDate)
						+ "' ) And Fm.Lang_Id = 'en_US') As Fir");
		lSBQuery.append(" Left Outer Join  ");
		lSBQuery
				.append(" (Select Year(Tr.Startdate) As Pay_Year,Month(Tr.Startdate) As Pay_Month From Trn_Dcps_Contribution Tr Where Tr.Dcps_Emp_Id = "
						+ lLongEmpId
						+ " And Tr.Startdate > '"
						+ sdf1.format(lDtDOJ)
						+ "' And Tr.Enddate < '"
						+ sdf1.format(lDtTerminationDate)
						+ "'  or (Month(Tr.Enddate) = Month('"
						+ sdf1.format(lDtTerminationDate)
						+ "') and year(Tr.Enddate) = year('"
						+ sdf1.format(lDtTerminationDate) + "')) and Tr.type_of_payment in (700046,700047)) As Sec");
		lSBQuery.append(" On (Fir.Total_Year = Sec.Pay_Year And Fir.Total_Month = Sec.Pay_Month) ");
		lSBQuery.append(" Where Pay_Month Is Null And Pay_year is null");
		lSBQuery.append(" order by 1,2");
		lSBQuery.append(" ) as thi ");
		lSBQuery
				.append(" join sgvc_fin_year_mst ofy on Date(Thi.Total_Year || '-' || Thi.Total_Month || '-' || '01') between Ofy.From_date and Ofy.To_date");
		lSBQuery.append(" join sgva_month_mst ofm on thi.total_month = ofm.month_no and ofm.lang_id = 'en_US'");
		lSBQuery.append(" left outer join trn_dcps_missing_credits_dtls MC");
		lSBQuery
				.append(" on  MC.MONTH = ofm.month_id and MC.YEAR = ofy.fin_year_id and MC.DCPS_EMP_ID = " + lLongEmpId);
		lSBQuery.append(" order by ofy.fin_year_id,ofm.month_id");

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		listMissingCredits = lQuery.list();

		return listMissingCredits;

	}

	public List getAllTerminalRequests(String lStrDDOCode, String gStrLocationCode, String lStrUser, String lStrUse)
			throws Exception {

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" SELECT EM.name,TD.terminalId ");
		lSBQuery.append(" FROM TrnDcpsTerminalDtls TD,MstEmp EM ");
		lSBQuery.append(" where TD.dcpsEmpId = EM.dcpsEmpId ");

		if (lStrUser.equals("DDO")) {
			lSBQuery.append(" and TD.ddoCode = :ddoCode");
		}
		if (lStrUser.equals("DDO") && lStrUse.equals("FromDraft")) {
			lSBQuery.append(" AND TD.statusFlag = 0");
		}
		if (lStrUser.equals("TO") && lStrUse.equals("FromDDO")) {
			lSBQuery.append(" AND TD.statusFlag = 1 AND TD.treasuryCode = :treasuryCode");
		}
		if (lStrUser.equals("DDO") && lStrUse.equals("FromTO")) {
			lSBQuery.append(" AND TD.statusFlag = 2");
		}
		if (lStrUser.equals("SRKA") && lStrUse.equals("FromDDO")) {
			lSBQuery.append(" AND TD.statusFlag = 3");
		}

		lSBQuery.append(" order by EM.name,TD.terminalId ASC");

		Query query = ghibSession.createQuery(lSBQuery.toString());

		if (lStrUser.equals("DDO")) {
			query.setParameter("ddoCode", lStrDDOCode.trim());
		}

		if (lStrUser.equals("TO") && lStrUse.equals("FromDDO")) {
			query.setParameter("treasuryCode", Long.valueOf(gStrLocationCode));
		}

		List resultList = query.list();
		return resultList;
	}

	public List getAllMissingCreditsSavedForTerminalId(Long lLongTerminalId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();

		// Sequence of parameters got here should be same as that of the method
		// getAllMissingCreditsForEmp

		lSBQuery
				.append(" SELECT FY.finYearDesc,FM.monthName,MD.year,MD.month,MD.missingCreditId,MD.amountDeduction,MD.voucherNo,MD.voucherDate,MD.Remarks FROM TrnDcpsMissingCreditsDtls MD,SgvaMonthMst FM,SgvcFinYearMst FY ");
		lSBQuery.append(" Where FM.monthId = MD.month and FM.langId =  'en_US' ");
		lSBQuery.append(" and FY.finYearId = MD.year and FY.langId =  'en_US' ");
		lSBQuery.append(" and MD.rltTerminalId = :rltTerminalId ");
		lSBQuery.append(" order by MD.year,MD.month");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("rltTerminalId", lLongTerminalId);

		List resultList = query.list();
		return resultList;
	}

	public Boolean checkTerminalRequestRaisedOrNot(Long dcpsEmpId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = true;

		try {

			lSBQuery.append(" select terminalId FROM TrnDcpsTerminalDtls WHERE dcpsEmpId = :dcpsEmpId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("dcpsEmpId", dcpsEmpId);

			tempList = lQuery.list();

			if (tempList.size() == 0) {
				flag = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return flag;
	}

	public void deleteMissingCreditsSavedForTerminalId(Long lLongTerminalId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();

		try {

			lSBQuery.append(" delete from TrnDcpsMissingCreditsDtls where rltTerminalId = :terminalId");

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("terminalId", lLongTerminalId);
			lQuery.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}

	}

	public Double getOpeningBalanceForDcpsId(String lStrDcpsId, Long lLngFinYearId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		Double lDblClosingBal = 0d;
		lSBQuery.append(" SELECT MD.closeNet ");
		lSBQuery.append(" FROM MstDcpsContributionYearly MD ");
		lSBQuery.append(" Where MD.yearId = :yearId and MD.dcpsId =  :dcpsId ");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("yearId", lLngFinYearId - 1);
		query.setParameter("dcpsId", lStrDcpsId);

		List resultList = query.list();
		if (resultList != null && resultList.size() > 0) {
			lDblClosingBal = (Double) resultList.get(0);
		}
		return lDblClosingBal;
	}

	public Double getPaidEmployerContributionForYear(String lStrDcpsId, Long lLngYearId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		Double lDblTotalContribution = 0d;

		lSBQuery.append(" SELECT SUM(TDC.contribution) ");
		lSBQuery.append(" FROM TrnDcpsContribution TDC,MstEmp ME ");
		lSBQuery
				.append(" Where TDC.finYearId = :yearId and TDC.dcpsEmpId = ME.dcpsEmpId AND ME.dcpsId= :dcpsId and TDC.employerContriFlag='Y'");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("yearId", lLngYearId);
		query.setParameter("dcpsId", lStrDcpsId);

		List resultList = query.list();
		if (resultList != null && resultList.size() > 0 && resultList.get(0) != null) {
			lDblTotalContribution = (Double) resultList.get(0);
		}
		return lDblTotalContribution;
	}

	public Double getPendingEmployerContributionForYear(String lStrDcpsId, Long lLngYearId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		Double lDblTotalContribution = 0d;

		lSBQuery.append(" SELECT SUM(TDC.contribution) ");
		lSBQuery.append(" FROM TrnDcpsContribution TDC,MstEmp ME ");
		lSBQuery
				.append(" Where TDC.finYearId = :yearId and TDC.dcpsEmpId = ME.dcpsEmpId AND ME.dcpsId= :dcpsId and (TDC.employerContriFlag is null OR TDC.employerContriFlag!='Y')");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("yearId", lLngYearId);
		query.setParameter("dcpsId", lStrDcpsId);

		List resultList = query.list();
		if (resultList != null && resultList.size() > 0 && resultList.get(0) != null) {
			lDblTotalContribution = (Double) resultList.get(0);
		}
		return lDblTotalContribution;
	}

	public Double getTotalMissingCreditsForEmp(String lStrDcpsId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		Double lDblTotalContribution = 0d;

		lSBQuery.append(" SELECT SUM(TDMC.amountDeduction) ");
		lSBQuery.append(" FROM TrnDcpsMissingCreditsDtls TDMC,MstEmp ME ");
		lSBQuery.append(" Where TDMC.dcpsEmpId = ME.dcpsEmpId AND ME.dcpsId= :dcpsId ");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("dcpsId", lStrDcpsId);

		List resultList = query.list();
		if (resultList != null && resultList.size() > 0 && resultList.get(0) != null) {
			lDblTotalContribution = (Double) resultList.get(0);
		}
		return lDblTotalContribution;
	}

	public Double getTier2ContributionForYear(String lStrDcpsId, Long lLngFinYearId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		Double lDblTier2Contribution = 0d;

		lSBQuery.append(" SELECT RDY.yearlyAmount ");
		lSBQuery.append(" FROM RltDcpsSixPCYearly RDY,MstEmp ME ");
		lSBQuery
				.append(" Where RDY.finYearId=:yearId AND RDY.statusFlag='A' AND RDY.dcpsEmpId = ME.dcpsEmpId AND ME.dcpsId= :dcpsId ");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("dcpsId", lStrDcpsId);
		query.setParameter("yearId", lLngFinYearId);

		List resultList = query.list();
		if (resultList != null && resultList.size() > 0) {
			lDblTier2Contribution = (Double) resultList.get(0);
		}
		return lDblTier2Contribution;
	}

	public Date getEndDateForFinYear(Long lLngFinYearId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		Date lDtEndDate = null;

		lSBQuery.append(" SELECT FYM.toDate ");
		lSBQuery.append(" FROM SgvcFinYearMst FYM ");
		lSBQuery.append(" Where FYM.finYearId=:yearId ");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("yearId", lLngFinYearId);

		List resultList = query.list();
		if (resultList != null && resultList.size() > 0) {
			lDtEndDate = (Date) resultList.get(0);
		}
		return lDtEndDate;
	}

	public List getContributionTillDate(String lStrDcpsId, Long lLngYearId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" SELECT TDC.contribution,CV.voucherDate ");
		lSBQuery.append(" FROM TrnDcpsContribution TDC,MstDcpsContriVoucherDtls CV,MstEmp ME ");
		lSBQuery
				.append(" Where TDC.finYearId = :yearId and TDC.dcpsEmpId = ME.dcpsEmpId AND ME.dcpsId= :dcpsId  AND TDC.rltContriVoucherId=CV.dcpsContriVoucherDtlsId");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("yearId", lLngYearId);
		query.setParameter("dcpsId", lStrDcpsId);

		List resultList = query.list();

		return resultList;
	}

	public Date getStartDateForFinYear(Long lLngFinYearId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();
		Date lDtStartDate = null;

		lSBQuery.append(" SELECT FYM.fromDate ");
		lSBQuery.append(" FROM SgvcFinYearMst FYM ");
		lSBQuery.append(" Where FYM.finYearId=:yearId ");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("yearId", lLngFinYearId);

		List resultList = query.list();
		if (resultList != null && resultList.size() > 0) {
			lDtStartDate = (Date) resultList.get(0);
		}
		return lDtStartDate;
	}

	public List getMissingCreditsForDcpsId(String lStrDcpsId) throws Exception {

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" SELECT MD.amountDeduction,MD.voucherDate FROM TrnDcpsMissingCreditsDtls MD,MstEmp ME ");
		lSBQuery.append(" Where MD.dcpsEmpId = ME.dcpsEmpId AND ME.dcpsId= :dcpsId ");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("dcpsId", lStrDcpsId);

		List resultList = query.list();
		return resultList;
	}

	public Long getDcpsEmpIdForDcpsId(String lStrDcpsId) throws Exception {

		Long lLngDcpsEmpId = 0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("SELECT ME.dcpsEmpId FROM MstEmp ME ");
		lSBQuery.append(" Where ME.dcpsId=:dcpsId ");

		Query query = ghibSession.createQuery(lSBQuery.toString());
		query.setParameter("dcpsId", lStrDcpsId);

		List resultList = query.list();
		if (resultList != null && resultList.size() > 0) {
			lLngDcpsEmpId = (Long) resultList.get(0);
		}

		return lLngDcpsEmpId;
	}

}
