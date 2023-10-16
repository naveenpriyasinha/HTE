/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 9, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;
import java.util.ResourceBundle;

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
 * @since JDK 5.0 Jun 9, 2011
 */
public class MatchContriEntryDAOImpl extends GenericDaoHibernateImpl implements
		MatchContriEntryDAO {

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	private final ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/pensionproc/PensionCaseConstants");

	public MatchContriEntryDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}

	public List getAllTreasuriesForMatchedEntries(String lStrFromDate,
			String lStrToDate) {

		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();

		sb
				.append(" SELECT vd.treasury_code, LM.loc_name, SUM(vd.voucher_amount),SUM(tn.DCPS_AMOUNT) FROM ");
		sb
				.append(" mst_dcps_contri_voucher_dtls vd, mst_dcps_treasurynet_data tn, mst_dcps_bill_group bg, cmn_location_mst LM , sgvc_fin_year_mst FY");
		sb.append(" WHERE vd.treasury_code = tn.treasury_code AND  ");
		sb.append(" vd.ddo_code = tn.ddo_code AND ");
		sb.append(" vd.bill_group_id = bg.bill_group_id AND ");
		sb.append(" bg.scheme_code = tn.to_scheme AND ");
		sb.append(" ((vd.voucher_no = tn.voucher_no AND vd.voucher_amount = tn.dcps_amount AND vd.voucher_date=tn.voucher_date) OR (vd.manually_matched = 1))");
		sb.append(" AND vd.treasury_code = LM.loc_id AND");
		sb.append(" FY.fin_year_desc = tn.year_desc AND");
		sb.append(" FY.fin_year_id = vd.year_id AND");
		sb.append(" vd.voucher_date  BETWEEN '" + lStrFromDate + "' AND '"
				+ lStrToDate + "'");
		sb.append(" GROUP BY LM.loc_id,vd.treasury_code, LM.loc_name");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList = selectQuery.list();

		return lLstReturnList;
	}

	public List getAllTreasuriesForUnMatchedEntries(String lStrFromDate,
			String lStrToDate) {

		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT vd.treasury_code,LM.loc_name,SUM(vd.voucher_amount),SUM(tn.DCPS_AMOUNT)");
		sb.append(" FROM mst_dcps_bill_group bg,cmn_location_mst LM,sgvc_fin_year_mst FY,mst_dcps_contri_voucher_dtls vd ");
		sb.append(" LEFT JOIN mst_dcps_treasurynet_data tn ON vd.voucher_no = tn.voucher_no ");
		sb.append(" WHERE vd.treasury_code = LM.loc_id ");
		sb.append(" AND ");
		sb.append(" ( ");
		sb.append(" (tn.voucher_no IS NULL AND FY.fin_year_id = vd.year_id  AND vd.bill_group_id = bg.bill_group_id AND vd.manually_matched <> 1) ");
		sb.append("  OR" );
		sb.append(" (vd.voucher_no = tn.voucher_no AND vd.voucher_date <> tn.voucher_date AND FY.fin_year_desc = tn.year_desc  AND FY.fin_year_id = vd.year_id  AND vd.treasury_code = tn.treasury_code  AND vd.ddo_code = tn.ddo_code  AND vd.bill_group_id = bg.bill_group_id  AND bg.scheme_code = tn.to_scheme  AND FY.fin_year_desc = tn.year_desc AND FY.fin_year_id = vd.year_id AND vd.manually_matched <> 1)");
		sb.append("  OR ");
		sb.append(" (vd.voucher_no = tn.voucher_no AND vd.voucher_date = tn.voucher_date AND vd.voucher_amount <> tn.dcps_amount AND FY.fin_year_desc = tn.year_desc  AND FY.fin_year_id = vd.year_id  AND vd.treasury_code = tn.treasury_code  AND vd.ddo_code = tn.ddo_code  AND vd.bill_group_id = bg.bill_group_id  AND bg.scheme_code = tn.to_scheme  AND FY.fin_year_desc = tn.year_desc AND FY.fin_year_id = vd.year_id AND vd.manually_matched <> 1) ");
		sb.append(" )");
		sb.append(" AND vd.voucher_date  BETWEEN '" + lStrFromDate + "' AND '"
				+ lStrToDate + "'");
		sb.append(" GROUP BY LM.loc_id,vd.treasury_code, LM.loc_name");
		
		

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList = selectQuery.list();

		return lLstReturnList;
	}
	
	public List getUnMatchedVouchersForMatching(String lStrFromDate,
			String lStrToDate, Long treasuryCode) {

		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT vd.mst_dcps_contri_voucher_dtls,vd.voucher_no,vd.voucher_amount,tn.dcps_amount ");
		sb.append(" FROM  mst_dcps_contri_voucher_dtls vd, mst_dcps_treasurynet_data tn, mst_dcps_bill_group bg, sgvc_fin_year_mst FY ");
		sb.append(" WHERE vd.treasury_code = tn.treasury_code ");
		sb.append(" AND vd.ddo_code = tn.ddo_code ");
		sb.append(" AND vd.bill_group_id = bg.bill_group_id ");
		sb.append(" AND bg.scheme_code = tn.to_scheme ");
		sb.append(" AND vd.voucher_no = tn.voucher_no ");
		sb.append(" AND vd.voucher_amount <> tn.dcps_amount ");
		sb.append(" AND vd.manually_matched = 0");
		sb.append(" AND FY.fin_year_desc = tn.year_desc ");
		sb.append(" AND FY.fin_year_id = vd.year_id ");
		sb.append(" AND vd.voucher_date  BETWEEN '" + lStrFromDate + "' AND '"
				+ lStrToDate + "'");
		sb.append(" AND vd.treasury_code = " + treasuryCode);
		sb.append(" AND vd.manually_matched = 0");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList = selectQuery.list();

		return lLstReturnList;
	}
	
	public List getUnMatchedVouchersAllForMatching(String lStrFromDate,
			String lStrToDate, Long treasuryCode) {

		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT DISTINCT vd.mst_dcps_contri_voucher_dtls,vd.voucher_no,vd.voucher_amount,vd.voucher_date");
		sb.append(" FROM  mst_dcps_contri_voucher_dtls vd, mst_dcps_bill_group bg, sgvc_fin_year_mst FY,trn_dcps_contribution tr ");
		sb.append(" WHERE vd.bill_group_id = bg.bill_group_id ");
		sb.append(" AND vd.manually_matched = 0");
		sb.append(" AND FY.fin_year_id = vd.year_id ");
		sb.append(" AND vd.voucher_date BETWEEN '" + lStrFromDate + "' AND '"+ lStrToDate + "'");
		sb.append(" AND vd.treasury_code = " + treasuryCode);
		sb.append(" AND tr.RLT_CONTRI_VOUCHER_ID = vd.MST_DCPS_CONTRI_VOUCHER_DTLS");
		sb.append(" AND tr.REG_STATUS = 1");
		sb.append(" AND vd.VOUCHER_NO IS NOT NULL AND vd.VOUCHER_DATE IS NOT NULL");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList = selectQuery.list();

		return lLstReturnList;
	}
	
	public void updateVouchersManuallyMatched (Long voucherIdPk) throws Exception {
		
		StringBuilder lSBQuery = null;
		Query lQuery = null;
		try {
			lSBQuery = new StringBuilder();
			
			lSBQuery.append(" UPDATE MstDcpsContriVoucherDtls VC SET VC.manuallyMatched = 1 WHERE dcpsContriVoucherDtlsId = :voucherIdPk");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("voucherIdPk", voucherIdPk);
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
			throw(e);
		}
	}
}
