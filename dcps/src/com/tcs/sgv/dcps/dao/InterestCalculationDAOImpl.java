/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 16, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsContributionYearly;
import com.tcs.sgv.dcps.valueobject.MstDcpsSixPCInterestYearly;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAOImpl;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Jun 16, 2011
 */
public class InterestCalculationDAOImpl extends GenericDaoHibernateImpl
		implements InterestCalculationDAO {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger
			.getLogger(TrnPnsnProcInwardPensionDAOImpl.class);

	public InterestCalculationDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	public List getAllDCPSEmployeesForIntrstCalc(Long treasuryCode,String ddoCode,
			String lStrFromDate, String lStrToDate) {

		List listAllEmpsForIntrstCalc = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" SELECT DISTINCT dcps_emp_id FROM trn_dcps_contribution TR ");
		lSBQuery.append(" JOIN mst_dcps_contri_voucher_dtls VC  ON VC.mst_dcps_contri_voucher_dtls=TR.rlt_contri_voucher_id   ");
		lSBQuery.append(" LEFT JOIN mst_dcps_treasurynet_data tn ON VC.voucher_no = tn.voucher_no ");
		lSBQuery.append(" WHERE ");
		lSBQuery.append(" VC.treasury_code=" + treasuryCode);
		
		if(!ddoCode.equals(""))
		{
			lSBQuery.append(" AND VC.ddo_code= '" + ddoCode + "' ");
		}
		
		lSBQuery.append(" AND VC.voucher_date BETWEEN '" + lStrFromDate
				+ "' AND '" + lStrToDate + "'");
		lSBQuery.append(" AND ((VC.voucher_no = tn.voucher_no AND VC.voucher_amount = tn.dcps_amount AND VC.voucher_date=tn.voucher_date) OR (VC.manually_matched = 1))");
		lSBQuery.append(" AND VC.voucher_status = 1");

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		listAllEmpsForIntrstCalc = lQuery.list();

		return listAllEmpsForIntrstCalc;
	}
	
	public Boolean checkEmployeeEligibleForIntrstCalc(Long treasuryCode,String ddoCode,Long dcpsEmpId,
			String lStrFromDate, String lStrToDate) {

		List listAllEmpsForIntrstCalc = null;
		Boolean lBlFlag = false;
		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" SELECT DISTINCT dcps_emp_id FROM trn_dcps_contribution TR ");
		lSBQuery.append(" JOIN mst_dcps_contri_voucher_dtls VC ON VC.mst_dcps_contri_voucher_dtls=TR.rlt_contri_voucher_id ");
		lSBQuery.append(" LEFT JOIN mst_dcps_treasurynet_data tn ON VC.voucher_no = tn.voucher_no ");
		lSBQuery.append(" WHERE ");
		lSBQuery.append(" VC.treasury_code=" + treasuryCode);
		lSBQuery.append(" AND VC.ddo_code= '" + ddoCode + "' ");
		lSBQuery.append(" AND TR.dcps_emp_id = " + dcpsEmpId);
		
		lSBQuery.append(" AND VC.voucher_date BETWEEN '" + lStrFromDate
				+ "' AND '" + lStrToDate + "'");
		lSBQuery.append(" AND ((VC.voucher_no = tn.voucher_no AND VC.voucher_amount = tn.dcps_amount AND VC.voucher_date=tn.voucher_date) OR (VC.manually_matched = 1))");
		lSBQuery.append(" AND VC.voucher_status = 1");

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		listAllEmpsForIntrstCalc = lQuery.list();
		
		if(listAllEmpsForIntrstCalc != null && listAllEmpsForIntrstCalc.size()!=0 )
		{
			lBlFlag = true ;
		}

		return lBlFlag;
	}

	public List getContriDtlsForGivenEmployee(Long treasuryCode,
			String lStrFromDate, String lStrToDate, Long dcpsEmpId) {

		List listContriDtlsForGivenEmp = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery
				.append(" SELECT VC.voucher_date,TR.CONTRIBUTION,TR.month_id,VC.year_id,TR.EMPLOYER_CONTRI_FLAG FROM trn_dcps_contribution TR ");
		lSBQuery.append(" JOIN mst_dcps_contri_voucher_dtls VC ON ");
		//lSBQuery.append(" VC.year_id = TR.FIN_YEAR_ID AND VC.month_id=TR.month_id AND VC.treasury_code = TR.loc_id ");
		//lSBQuery.append(" AND VC.bill_group_id = TR.BILL_GROUP_ID AND VC.ddo_code=TR.ddo_code ");
		lSBQuery.append(" VC.MST_DCPS_CONTRI_VOUCHER_DTLS = TR.RLT_CONTRI_VOUCHER_ID");
		
		//Line added for Matching
		lSBQuery.append(" LEFT JOIN mst_dcps_treasurynet_data tn ON VC.voucher_no = tn.voucher_no ");
		//Line overs
		lSBQuery.append(" WHERE VC.treasury_code = " + treasuryCode);
		lSBQuery.append(" AND VC.voucher_date BETWEEN '" + lStrFromDate
				+ "' AND '" + lStrToDate + "'");
		//Line added for Matching
		lSBQuery.append(" AND ((VC.voucher_no = tn.voucher_no AND VC.voucher_amount = tn.dcps_amount AND VC.voucher_date=tn.voucher_date) OR (VC.manually_matched = 1))");
		//Line overs
		lSBQuery.append(" AND TR.dcps_emp_id = " + dcpsEmpId);

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		listContriDtlsForGivenEmp = lQuery.list();

		return listContriDtlsForGivenEmp;
	}

	public Double getInterestRateForGivenYear(String lStrYear) {

		Double interestRate = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("SELECT interest FROM mst_dcps_interest_rate WHERE '"
				+ lStrYear + "' >= effective_from AND ('" + lStrYear
				+ "' < applicable_to OR applicable_to IS NULL )");

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		interestRate = Double.parseDouble(lQuery.uniqueResult().toString());

		return interestRate;
	}

	public MstDcpsContributionYearly getContriYearlyVOForYear(
			Long dcpsEmpId, Long previousYearId) {

		StringBuilder lSBQuery = new StringBuilder();
		MstDcpsContributionYearly lObjMstDcpsContributionYearly = null;

		Query lQuery = null;

		lSBQuery
				.append(" SELECT YC FROM MstDcpsContributionYearly YC,MstEmp EM WHERE YC.dcpsId = EM.dcpsId AND EM.dcpsEmpId = :dcpsEmpId AND YC.yearId = :yearId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);
		lQuery.setParameter("yearId", previousYearId);

		lObjMstDcpsContributionYearly = (MstDcpsContributionYearly) lQuery
				.uniqueResult();

		return lObjMstDcpsContributionYearly;
	}

	public String getDcpsIdForEmpId(Long dcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<String> tempList = new ArrayList();
		String dcpsId = null;

		lSBQuery
				.append(" select dcpsId FROM MstEmp WHERE dcpsEmpId = :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);

		tempList = lQuery.list();
		if(tempList != null)
		{
			if(tempList.size() != 0)
			{
				dcpsId = tempList.get(0);
			}
		}
		
		return dcpsId;

	}

	public Long getYearIdForYearCode(String yearCode) {

		Long lLongYearId = null;
		StringBuilder lSBQuery = null;
		Query hqlQuery = null;
		try {
			ghibSession = getSession();
			lSBQuery = new StringBuilder();
			lSBQuery
					.append(" SELECT finYearId FROM SgvcFinYearMst WHERE finYearCode = :yearCode");
			hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setParameter("yearCode", yearCode);
			lLongYearId = Long.valueOf(hqlQuery.list().get(0).toString());

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.info("Error  is " + e);

		}
		return lLongYearId;
	}
	
/*	
	public List getAllDCPSEmployeesForIntrstCalcSixPC(Long treasuryCode,String ddoCode) {

		List listAllEmpsForIntrstCalc = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" SELECT DISTINCT EM.dcpsEmpId FROM MstEmp EM,OrgDdoMst OD,RltDdoOrg RD,MstSixPCArrears PC");
		lSBQuery.append(" WHERE EM.dcpsEmpId = PC.dcpsEmpId ");
		lSBQuery.append(" AND RD.ddoCode = OD.ddoCode ");
		lSBQuery.append(" AND RD.locationCode = :treasuryCode ");
		
		if(!ddoCode.equals(""))
		{
			lSBQuery.append(" AND EM.ddoCode = :ddoCode");
		}

		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("treasuryCode", treasuryCode.toString());
		
		if(!ddoCode.equals(""))
		{
			lQuery.setParameter("ddoCode", ddoCode);
		}

		listAllEmpsForIntrstCalc = lQuery.list();

		return listAllEmpsForIntrstCalc;
	}

	
	public MstDcpsSixPCInterestYearly getSixPCYearlyInterestVOForYear(
			Long dcpsEmpId, Long previousYearId) {

		StringBuilder lSBQuery = new StringBuilder();
		MstDcpsSixPCInterestYearly lObjMstDcpsSixPCInterestYearly = null;

		Query lQuery = null;

		lSBQuery.append(" SELECT YC FROM MstDcpsSixPCInterestYearly YC,MstEmp EM WHERE YC.dcpsId = EM.dcpsId AND EM.dcpsEmpId = :dcpsEmpId AND YC.yearId = :yearId ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);
		lQuery.setParameter("yearId", previousYearId);

		lObjMstDcpsSixPCInterestYearly = (MstDcpsSixPCInterestYearly) lQuery
				.uniqueResult();

		return lObjMstDcpsSixPCInterestYearly;
	}
	
*/
	
	public List getArrearsDtlsForGivenEmployee(Long dcpsEmpId,Long yearId) {

		List listArrearsDtlsForGivenEmp = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" select RL.yearlyAmount,RL.dcpsEmpId from RltDcpsSixPCYearly RL,MstEmp EM");
		lSBQuery.append(" where RL.dcpsEmpId = :dcpsEmpId");
		lSBQuery.append(" and RL.finYearId = :yearId ");
		lSBQuery.append(" and RL.dcpsEmpId = EM.dcpsEmpId ");
		lSBQuery.append(" and RL.statusFlag = 'A'");

		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("yearId", yearId);
		lQuery.setParameter("dcpsEmpId", dcpsEmpId);
		
		listArrearsDtlsForGivenEmp = lQuery.list();

		return listArrearsDtlsForGivenEmp;
	}

	public List getAllEmpsUnderDDO(String lStrDDOCode) throws Exception {

		List empList = null;
		
		try {
			ghibSession = getSession();
		
			String query = "select EM.dcpsEmpId,EM.dcpsId,EM.name from MstEmp EM where ddoCode= :DDOCode and EM.dcpsId is not null order by EM.name,EM.dcpsEmpId,EM.dcpsId ";
		
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append(query);
			Query stQuery = ghibSession.createQuery(SBQuery.toString());
			stQuery.setParameter("DDOCode", lStrDDOCode);
			empList = stQuery.list();
		
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
			return empList;
		}
}
