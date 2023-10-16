package com.tcs.sgv.nps.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.DateUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;

public class NPSContributionFinancialYearWiseReportDAOImpl extends GenericDaoHibernateImpl {
	
	SessionFactory gObjSessionFactory = null;

	Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();

	private static final Logger gLogger = Logger.getLogger("PensionpayReports");

	/** Global Variable for Resource Bundle */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionpay/PensionCaseConstants");

	private ResourceBundle gObjRsrcBndleCaseLables = ResourceBundle.getBundle("resources/pensionpay/PensionLabels");

	/** Global Variable for Simple Date Format */
	private SimpleDateFormat gObjDtFormat = new SimpleDateFormat("dd/MM/yyyy");

	private static ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pensionpay/PensionConstants");

	public NPSContributionFinancialYearWiseReportDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		gObjSessionFactory = sessionFactory;
		setSessionFactory(sessionFactory);
	}

	public void setReportHeaderAndFooter(ReportVO lObjReport,
			LoginDetails lObjLoginVO) {
		StringBuilder lSbHeaderVal = null;
		try {
			String lStrLocCode = lObjLoginVO.getLocation().getLocationCode().trim();
			String lStrDdoCode=  lObjLoginVO.getLocation().getLocationCode().trim();
			lSbHeaderVal = getReportHeader(lStrLocCode);

			ReportAttributeVO[] lArrrReportAttributeVO = new ReportAttributeVO[2];
			lArrrReportAttributeVO[0] = new ReportAttributeVO();
			lArrrReportAttributeVO[0].setAttributeType(IReportConstants.ATTRIB_OTHER);
			lArrrReportAttributeVO[0].setLocation(IReportConstants.LOCATION_HEADER);
			lArrrReportAttributeVO[0].setAlignment(IReportConstants.ALIGN_RIGHT);
			lArrrReportAttributeVO[0].setAttributeValue("Date : " + DateUtility.getCurrentDateTime());

			lArrrReportAttributeVO[1] = new ReportAttributeVO();
			lArrrReportAttributeVO[1].setAttributeType(IReportConstants.ATTRIB_OTHER);
			lArrrReportAttributeVO[1].setLocation(IReportConstants.LOCATION_FOOTER);
			lArrrReportAttributeVO[1].setAlignment(IReportConstants.ALIGN_RIGHT);

			String lStrEmpName = ((lObjLoginVO.getEmployee().getEmpPrefix() != null) ? lObjLoginVO.getEmployee().getEmpPrefix() + " " : "")
					+ ((lObjLoginVO.getEmployee().getEmpFname() != null) ? lObjLoginVO.getEmployee().getEmpFname() + " " : "")
					+ ((lObjLoginVO.getEmployee().getEmpMname() != null) ? lObjLoginVO.getEmployee().getEmpMname() + " " : "")
					+ ((lObjLoginVO.getEmployee().getEmpLname() != null) ? lObjLoginVO.getEmployee().getEmpLname() + " " : "");

			lArrrReportAttributeVO[1].setAttributeValue("Prepared By : " + lStrEmpName);

			String lStrRptName = lObjReport.getReportName();
			lObjReport.setReportAttributes(lArrrReportAttributeVO);
			// for drill down report
			if (lStrRptName.indexOf(lSbHeaderVal.toString()) == -1) {

				lSbHeaderVal.append(lObjReport.getReportName() + "\r\n" + "\r\n"); // get
																					// original
				lObjReport.setReportName(lSbHeaderVal.toString());
			} 

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}

	private StringBuilder getReportHeader(String lStrLocCode) {
		StringBuilder lSBQuery = new StringBuilder();
		StringBuilder lSbHeaderVal = new StringBuilder();
		try {
			lSBQuery.append(" SELECT loc.loc_name,concat(concat(concat(concat(concat(concat(concat(concat(loc.loc_addr_1,' '),loc.loc_addr_2),' '),city.city_name),' '),dist.district_name),' '),state.state_name) , cntc.office_phone,cntc.email ");
			lSBQuery.append(" FROM cmn_city_mst city,cmn_district_mst dist,cmn_state_mst state  ,cmn_location_mst loc left outer join cmn_contact_mst cntc  on cntc.loc_id = loc.loc_id  ");
			lSBQuery.append(" WHERE city.city_id = loc.loc_city_id AND dist.district_id = city.district_id AND state.state_id = dist.state_id  ");
			lSBQuery.append(" AND loc.department_id =  100003 AND loc.location_code = :locationCode ");
			SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
			Query.setString("locationCode", lStrLocCode);
			List lLstFinal = Query.list();
			if (lLstFinal != null && !lLstFinal.isEmpty()) {
				Iterator it = lLstFinal.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					if (tuple[0] != null) {
						lSbHeaderVal.append(tuple[0].toString() + "\r\n"); // Office
																			// Name
					}
					if (tuple[1] != null) {
						lSbHeaderVal.append(tuple[1] + "\r\n"); // Office
																// Address
					}
					if (tuple[2] != null) {
						lSbHeaderVal.append("Ph: " + tuple[2].toString() + "\r\n"); // Office
																					// Phone
																					// no.
					}
					if (tuple[3] != null) {
						lSbHeaderVal.append("E-mail: " + tuple[3].toString() + "\r\n" + "\r\n" + "\r\n"); // Office
																											// E-mail
					}
				}
			}

		} catch (Exception e) {

			logger.error("Error is : " + e, e);
			throw (e);
		}
		return lSbHeaderVal;
	}

	

	public List getNPSContributionFinancialYearWiseReport(String lStrLocCode, String ddocode, String forYear, String forMonth) {
		ArrayList lArrListOuter1 = new ArrayList();
		List lLstResult= new ArrayList();
		Iterator lObjIterator = null;
		
        Map brokenMap=new HashMap();
		
		ArrayList DataList = new ArrayList();

		try{
		StringBuilder lSBQuery = new StringBuilder();
		
		lSBQuery.append("select temp.PAYBILL_YEAR,sum(temp.employee_count),");
		lSBQuery.append("sum(temp.DCPS)+sum(temp.DCPS_DELAY)+sum(temp.DCPS_PAY)+sum(temp.DCPS_DA), ");
		lSBQuery.append("sum(temp.DED_ADJUST) from ( SELECT HEAD.PAYBILL_YEAR,count(*) as employee_count, ");
		lSBQuery.append("NVL(SUM(BILL.DCPS),0) AS DCPS,NVL(SUM(BILL.DCPS_DELAY),0) AS DCPS_DELAY, ");
		lSBQuery.append("NVL(SUM(BILL.DCPS_PAY),0) AS DCPS_PAY,NVL(SUM(BILL.DCPS_DA),0) AS DCPS_DA ,0 as DED_ADJUST FROM IFMS.HR_PAY_PAYBILL BILL ");
		lSBQuery.append("INNER JOIN IFMS.PAYBILL_HEAD_MPG HEAD ON BILL.PAYBILL_GRP_ID=HEAD.PAYBILL_ID ");
		lSBQuery.append("INNER JOIN IFMS.ORG_DDO_MST DDO ON HEAD.LOC_ID=BIGINT(DDO.LOCATION_CODE) ");
		lSBQuery.append("INNER JOIN IFMS.RLT_ZP_DDO_MAP MAP ON MAP.ZP_DDO_CODE=DDO.DDO_CODE INNER JOIN IFMS.ORG_POST_DETAILS_RLT PDTL ON PDTL.POST_ID=BILL.POST_ID ");
		lSBQuery.append("INNER JOIN IFMS.MST_PAYROLL_DESIGNATION DESIG ON DESIG.ORG_DESIGNATION_ID=PDTL.DSGN_ID ");
		lSBQuery.append("INNER JOIN IFMS.CMN_LOOKUP_MST CLM ON CLM.LOOKUP_id = DESIG.CADRE_TYPE_ID  ");
		lSBQuery.append("inner join ifms.CONSOLIDATED_BILL_MPG as consMPG on consMPG.PAYBILL_ID=HEAD.PAYBILL_ID ");
		lSBQuery.append("inner join ifms.CONSOLIDATED_BILL_MST as cons on CONS.CONS_BILL_ID=consMPG.CONS_BILL_ID ");
		lSBQuery.append("WHERE  head.PAYBILL_YEAR in (2023) AND HEAD.APPROVE_FLAG IN (0,1)  AND MAP.REPT_DDO_CODE<>'"+ddocode+"' and cons.status in (1,5) and bill.EMP_ID is not null ");
		lSBQuery.append("GROUP BY HEAD.PAYBILL_YEAR ) as temp group by temp.PAYBILL_YEAR");

			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			List RowList=lQuery.list();
			
			lLstResult = lQuery.list();
			logger.info("Query1 is *********"+lSBQuery.toString());
			int i=1;
			lObjIterator = lLstResult.iterator();
			

			while (lObjIterator.hasNext()) {
				Object[] lArrObj = (Object[]) lObjIterator.next();
				List lArrListInner=new ArrayList();
				
				//Sr No
				if (lArrObj[0] != null) {
					lArrListInner.add( i);
				} else {
					lArrListInner.add("");
				}
				//Paybill_Year
				if (lArrObj[0] != null) {
					lArrListInner.add(lArrObj[0].toString());
				} else {
					lArrListInner.add("");
				}
				//Employee_Count
				if (lArrObj[1] != null) {
					lArrListInner.add(lArrObj[1].toString());
				} else {
					lArrListInner.add("");
				}
				//DCPS/NPS_Count
				if (lArrObj[2] != null) {
					lArrListInner.add(lArrObj[2].toString());
				} else {
					lArrListInner.add("");
				}
				//Ded_Adjust
				if (lArrObj[3] != null) {
					lArrListInner.add(lArrObj[3].toString());
				} else {
					lArrListInner.add("");
				}
				
				lArrListOuter1.add(lArrListInner);
				lArrListInner=null;
				lArrObj=null;
				i++;
		}

		}

		catch (Exception e) {
			gLogger.error("error is :" + e, e);
		       } 
			
			return lArrListOuter1;
	}

	
	public List getDDO(String lStrLangId, String lStrLocCode) {
		List<Object> lArrgetDDo = new ArrayList<Object>();
		try {
			Session lObjSession = ServiceLocator.getServiceLocator()
			.getSessionFactory().getCurrentSession();

			String lStrBufLang = "SELECT ddo.DDO_CODE,ddo.DDO_NAME|| ' ' || ddo.ddo_code FROM ORG_DDO_MST ddo inner join RLT_ZP_DDO_MAP map on ddo.DDO_CODE = map.ZP_DDO_CODE order by ddo.DDO_CODE";

			Query lObjQuery = lObjSession.createSQLQuery(lStrBufLang);
			

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			Object[] lArrData = null;

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					lArrgetDDo.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			logger.error("Error is : " + e, e);
		}

		return lArrgetDDo;
	}
	
	
}
