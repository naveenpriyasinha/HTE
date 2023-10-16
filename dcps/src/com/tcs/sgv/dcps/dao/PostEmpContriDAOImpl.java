/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	May 30, 2011		Meeta Thacker								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.PostEmpContri;

/**
 * Class Description -
 * 
 * 
 * @author Meeta Thacker
 * @version 0.1
 * @since JDK 5.0 May 30, 2011
 */
public class PostEmpContriDAOImpl extends GenericDaoHibernateImpl implements PostEmpContriDAO {
	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	public PostEmpContriDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}

	public List getAllContributions(String userType, Long finYear, String contriMonth) {

		List listPostEmpContri = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery
				.append(" SELECT dcpsPostEmpContriIdPk,finYear,contriMonth,billNo,billAmount,voucherNo,voucherDate,statusFlag ");
		lSBQuery.append(" FROM PostEmpContri WHERE ");
		if (userType.equals("SRKA")) {
			lSBQuery.append("finYear = :finYear And contriMonth = :contriMonth And statusFlag IN ('D','R','A','F')");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("finYear", finYear);
			lQuery.setParameter("contriMonth", contriMonth);
		}
		if (userType.equals("PAO")) {
			lSBQuery.append(" statusFlag='F'");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
		}

		listPostEmpContri = lQuery.list();

		return listPostEmpContri;
	}

	public Long getSancBudget(Long finYear) {

		List<Long> listSancBudget = null;

		Long lLngTotalBudget = 0L;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery
				.append(" SELECT totalBudget FROM SanctionedBudget WHERE dcpsSancBudgetFinYear= :finYear ORDER BY CreatedDate DESC");

		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("finYear", finYear);
		listSancBudget = lQuery.list();

		if (listSancBudget.size() != 0 && listSancBudget != null) {
			if (listSancBudget.get(0) != null) {
				lLngTotalBudget = listSancBudget.get(0);
			}
		}
		return lLngTotalBudget;
	}

	public Long getSancBudgetPK(Long finYear) {

		Long lSancBudgetPK = null;
		List listSancBudget = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" SELECT dcpsSanctionedBudgetIdPk FROM SanctionedBudget WHERE dcpsSancBudgetFinYear= :finYear");

		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("finYear", finYear);
		listSancBudget = lQuery.list();
		if (listSancBudget != null && listSancBudget.size() != 0) {
			lSancBudgetPK = (Long) listSancBudget.get(0);
		}
		return lSancBudgetPK;
	}

	public PostEmpContri getPostEmpContriVOForGivenMonthAndYear(Long monthId, Long yearId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<PostEmpContri> tempList = null;
		PostEmpContri PostEmpContriVO = null;
		String lStrMonth = monthId.toString().trim();
		lSBQuery.append("FROM PostEmpContri WHERE finYear = :yearId and contriMonth = :monthId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setLong("yearId", yearId);
		lQuery.setParameter("monthId", lStrMonth);

		tempList = lQuery.list();
		if (tempList != null && tempList.size() != 0) {
			PostEmpContriVO = tempList.get(0);
		}
		return PostEmpContriVO;

	}

	public Long getExpenditure(Long finYear) {
		List<Long> listExpenditure = null;

		Long lLngExpenditure = 0L;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" SELECT sum(billAmount) FROM PostEmpContri WHERE finYear= :finYear ");

		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("finYear", finYear);
		listExpenditure = lQuery.list();

		if (listExpenditure.size() != 0 && listExpenditure != null) {
			if (listExpenditure.get(0) != null) {
				lLngExpenditure = listExpenditure.get(0).longValue();
			}
		}
		return lLngExpenditure;
	}

	public String getBillNumber(Long finYear) {
		List listExpenditure = null;

		Long lLngCount = 0L;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" SELECT count(billNo) FROM PostEmpContri WHERE finYear= :finYear");

		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("finYear", finYear);
		listExpenditure = lQuery.list();

		if (listExpenditure.size() != 0 && listExpenditure != null) {
			if (listExpenditure.get(0) != null) {
				lLngCount = Long.parseLong(listExpenditure.get(0).toString());
			}

		}

		return String.format("%03d", lLngCount + 1);
	}

	public Long getExcessAmount(Long finYear) {

		List<Long> listExcess = null;

		Long lLngExcessAmount = 0L;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery
				.append(" SELECT excessExpenditure FROM PostEmpContri WHERE finYear= :finYear ORDER BY CreatedDate DESC");

		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("finYear", finYear);
		listExcess = lQuery.list();

		if (listExcess.size() != 0 && listExcess != null) {
			if (listExcess.get(0) != null) {
				lLngExcessAmount = listExcess.get(0);
			}
		}
		return lLngExcessAmount;
	}

	public Double getExpInCurrBill(String finYearCode, Long monthId) {

		List<Double> listExpInCurrBill = null;
		Double lDoubleExpIncurrBill = 0d;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("select sum(VA) from (SELECT CV.voucher_amount AS VA");
		lSBQuery
				.append(" FROM mst_dcps_contri_voucher_dtls CV,mst_dcps_bill_group BG,mst_dcps_treasurynet_data TN,sgvc_fin_year_mst FY");
		lSBQuery
				.append(" WHERE BG.bill_group_id = CV.bill_group_id and FY.fin_year_desc = TN.year_desc and CV.post_emplr_contri_status = 0 AND CV.voucher_status = 1 ");
		lSBQuery
				.append(" AND CV.voucher_no = tn.voucher_no AND CV.treasury_code=TN.treasury_code AND  CV.ddo_code = TN.ddo_code AND CV.voucher_amount = tn.dcps_amount AND CV.voucher_date=tn.voucher_date AND BG.scheme_code = TN.to_scheme ");
		lSBQuery.append(" AND (FY.fin_year_code < :finYearCode OR (FY.fin_year_code = :finYearCode");
		if (monthId <= 3) {
			lSBQuery.append(" AND (CV.month_id <= :monthId OR CV.month_id>3)))");
		} else {
			lSBQuery.append(" AND CV.month_id <= :monthId))");
		}
		lSBQuery.append(" UNION");
		lSBQuery.append(" SELECT CV.voucher_amount AS VA FROM mst_dcps_contri_voucher_dtls CV,sgvc_fin_year_mst FY");
		lSBQuery
				.append(" WHERE FY.fin_year_id=CV.year_id AND CV.post_emplr_contri_status = 0 And  CV.manually_matched = 1");
		lSBQuery.append(" AND (FY.fin_year_code < :finYearCode OR (FY.fin_year_code = :finYearCode");
		if (monthId <= 3) {
			lSBQuery.append(" AND (CV.month_id <= :monthId OR CV.month_id>3))))");
		} else {
			lSBQuery.append(" AND CV.month_id <= :monthId)))");
		}

		// lSBQuery.append(" SELECT sum(CV.voucher_amount) ");
		// lSBQuery.append(" FROM mst_dcps_contri_voucher_dtls CV");
		// lSBQuery.append(" JOIN mst_dcps_bill_group BG ON BG.bill_group_id = CV.bill_group_id");
		// lSBQuery
		// .append(" JOIN mst_dcps_treasurynet_data TN ON CV.voucher_no = tn.voucher_no AND CV.treasury_code=TN.treasury_code AND  CV.ddo_code = TN.ddo_code AND");
		// lSBQuery
		// .append(" (( CV.voucher_amount = tn.dcps_amount AND CV.voucher_date=tn.voucher_date) OR (CV.manually_matched = 1))  AND BG.scheme_code = TN.to_scheme ");
		// lSBQuery.append(" JOIN sgvc_fin_year_mst FY ON FY.fin_year_desc = TN.year_desc");
		// lSBQuery.append(" JOIN sgva_month_mst FM ON FM.month_id = CV.month_id");
		// lSBQuery.append(" WHERE CV.post_emplr_contri_status = 0");
		// lSBQuery.append(" AND CV.voucher_status = 1");
		// lSBQuery
		// .append(" AND (FY.fin_year_code < :finYearCode OR (FY.fin_year_code = :finYearCode AND CV.month_id <= :monthId))");

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		lQuery.setParameter("finYearCode", finYearCode);
		lQuery.setParameter("monthId", monthId);
		listExpInCurrBill = lQuery.list();

		if (listExpInCurrBill.size() != 0 && listExpInCurrBill != null) {
			if (listExpInCurrBill.get(0) != null) {
				lDoubleExpIncurrBill = listExpInCurrBill.get(0);
			}
		}
		return lDoubleExpIncurrBill;
	}

	public void updateTrnDcpsContributionList(String finYearCode, Long monthId) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" UPDATE trn_dcps_contribution SET employer_contri_flag='Y',status='G' ");
		lSBQuery.append(" WHERE rlt_contri_voucher_id IN ");
		lSBQuery.append(" (SELECT CV.mst_dcps_contri_voucher_dtls ");
		lSBQuery
				.append(" FROM mst_dcps_contri_voucher_dtls CV,mst_dcps_bill_group BG,mst_dcps_treasurynet_data TN,sgvc_fin_year_mst FY");
		lSBQuery
				.append(" WHERE BG.bill_group_id = CV.bill_group_id and FY.fin_year_desc = TN.year_desc and CV.post_emplr_contri_status = 0 AND CV.voucher_status = 1 ");
		lSBQuery
				.append(" AND CV.voucher_no = tn.voucher_no AND CV.treasury_code=TN.treasury_code AND  CV.ddo_code = TN.ddo_code AND CV.voucher_amount = tn.dcps_amount AND CV.voucher_date=tn.voucher_date AND BG.scheme_code = TN.to_scheme ");
		lSBQuery.append(" AND (FY.fin_year_code < :finYearCode OR (FY.fin_year_code = :finYearCode");
		if (monthId <= 3) {
			lSBQuery.append(" AND (CV.month_id <= :monthId OR CV.month_id>3)))");
		} else {
			lSBQuery.append(" AND CV.month_id <= :monthId))");
		}
		lSBQuery.append(" UNION");
		lSBQuery
				.append(" SELECT CV.mst_dcps_contri_voucher_dtls FROM mst_dcps_contri_voucher_dtls CV,sgvc_fin_year_mst FY");
		lSBQuery
				.append(" WHERE FY.fin_year_id=CV.year_id AND CV.post_emplr_contri_status = 0 And  CV.manually_matched = 1");
		lSBQuery.append(" AND (FY.fin_year_code < :finYearCode OR (FY.fin_year_code = :finYearCode");
		if (monthId <= 3) {
			lSBQuery.append(" AND (CV.month_id <= :monthId OR CV.month_id>3))))");
		} else {
			lSBQuery.append(" AND CV.month_id <= :monthId)))");
		}

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		lQuery.setParameter("finYearCode", finYearCode);
		lQuery.setParameter("monthId", monthId);
		lQuery.executeUpdate();

	}

	public void updateBillNoAndYearIdForPostEmpcontri(String lStrBillno, Long lLongYearId, String finYearCode,
			Long monthId) {

		StringBuilder lSBQueryForGettingIds = new StringBuilder();
		List<BigInteger> lListVoucherPks = null;
		Long dcpsContriVoucherDtlsId = null;
		BigInteger bigIntdcpsContriVoucherDtlsId = null;

		lSBQueryForGettingIds.append(" SELECT CV.mst_dcps_contri_voucher_dtls ");
		lSBQueryForGettingIds.append(" FROM mst_dcps_contri_voucher_dtls CV");
		lSBQueryForGettingIds.append(" JOIN mst_dcps_bill_group BG ON BG.bill_group_id = CV.bill_group_id");
		lSBQueryForGettingIds
				.append(" JOIN mst_dcps_treasurynet_data TN ON CV.voucher_no = tn.voucher_no AND CV.treasury_code=TN.treasury_code AND  CV.ddo_code = TN.ddo_code AND");
		lSBQueryForGettingIds
				.append(" (( CV.voucher_amount = tn.dcps_amount AND CV.voucher_date=tn.voucher_date) OR (CV.manually_matched = 1))  AND BG.scheme_code = TN.to_scheme ");
		lSBQueryForGettingIds.append(" JOIN sgvc_fin_year_mst FY ON FY.fin_year_desc = TN.year_desc");
		lSBQueryForGettingIds.append(" JOIN sgva_month_mst FM ON FM.month_id = CV.month_id");
		lSBQueryForGettingIds.append(" WHERE CV.post_emplr_contri_status = 0");
		lSBQueryForGettingIds.append(" AND CV.voucher_status = 1");
		lSBQueryForGettingIds
				.append(" AND (FY.fin_year_code < :finYearCode OR (FY.fin_year_code = :finYearCode AND CV.month_id <= :monthId))");

		Query lQueryForGettingIds = ghibSession.createSQLQuery(lSBQueryForGettingIds.toString());
		lQueryForGettingIds.setParameter("finYearCode", finYearCode);
		lQueryForGettingIds.setParameter("monthId", monthId);

		lListVoucherPks = lQueryForGettingIds.list();
		StringBuilder lSBQuery = null;
		Query lQuery = null;

		for (Integer lInt = 0; lInt < lListVoucherPks.size(); lInt++) {
			bigIntdcpsContriVoucherDtlsId = lListVoucherPks.get(lInt);
			dcpsContriVoucherDtlsId = bigIntdcpsContriVoucherDtlsId.longValue();
			lSBQuery = new StringBuilder();

			lSBQuery.append(" Update MstDcpsContriVoucherDtls ");
			lSBQuery
					.append(" SET emplrBillNo = :billNo ,emplrYearId = :yearId,postEmplrContriStatus = :postEmplrContriStatus ");
			lSBQuery.append(" WHERE dcpsContriVoucherDtlsId = :dcpsContriVoucherDtlsId ");

			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("billNo", lStrBillno);
			lQuery.setParameter("yearId", lLongYearId);
			lQuery.setParameter("dcpsContriVoucherDtlsId", dcpsContriVoucherDtlsId);
			lQuery.setParameter("postEmplrContriStatus", 2l);
			lQuery.executeUpdate();
		}
	}

	public void updateVoucherPostEmpStatusOnApproval(String lStrBillno, Long lLongYearId) {

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" Update MstDcpsContriVoucherDtls ");
		lSBQuery.append(" SET postEmplrContriStatus = :postEmplrContriStatus , status = :status ");
		lSBQuery.append(" WHERE postEmplrContriStatus = :previousEmplrContriStatus ");
		lSBQuery.append(" AND emplrBillNo = :billNo ");
		lSBQuery.append(" AND emplrYearId = :yearId ");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("billNo", lStrBillno);
		lQuery.setParameter("yearId", lLongYearId);
		lQuery.setParameter("postEmplrContriStatus", 1l);
		lQuery.setParameter("previousEmplrContriStatus", 2l);
		lQuery.setCharacter("status", 'G');
		lQuery.executeUpdate();
	}

	public void updatePostEmplrVoucherDtlsOfApprovedBills(String lStrBillno, Long lLongYearId,
			Long lLongEmplrVoucherNo, Date lDateEmplrVoucherDate) {

		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" Update MstDcpsContriVoucherDtls ");
		lSBQuery.append(" SET emplrVoucherNo = :emplrVoucherNo, emplrVoucherDate = :emplrVoucherDate ");
		lSBQuery.append(" WHERE  emplrBillNo = :billNo ");
		lSBQuery.append(" AND emplrYearId = :yearId ");
		lSBQuery.append(" AND postEmplrContriStatus = 1");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("billNo", lStrBillno);
		lQuery.setParameter("yearId", lLongYearId);
		lQuery.setParameter("emplrVoucherNo", lLongEmplrVoucherNo);
		lQuery.setParameter("emplrVoucherDate", lDateEmplrVoucherDate);
		lQuery.executeUpdate();
	}

}
