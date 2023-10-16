package com.tcs.sgv.nps.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.DateUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;

public class NpsGrossExpenditureReportDAOImpl extends GenericDaoHibernateImpl{
	
	 

	
		SessionFactory gObjSessionFactory = null;

		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();

		private static final Logger gLogger = Logger.getLogger("PensionpayReports");

		/** Global Variable for Resource Bundle */
		private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionpay/PensionCaseConstants");

		private ResourceBundle gObjRsrcBndleCaseLables = ResourceBundle.getBundle("resources/pensionpay/PensionLabels");

		/** Global Variable for Simple Date Format */
		private SimpleDateFormat gObjDtFormat = new SimpleDateFormat("dd/MM/yyyy");

		private static ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pensionpay/PensionConstants");
		
		
		public NpsGrossExpenditureReportDAOImpl(Class type, SessionFactory sessionFactory) {

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
		

		public List getNpsExpenditureReport(String lStrLocCode, String ddocode) {
	           
			ArrayList lArrListOuter1 = new ArrayList();
			List lLstResult= new ArrayList();
			Iterator lObjIterator = null;
			

			try{
			StringBuilder lSBQuery = new StringBuilder();
			
			lSBQuery.append("select 'RDD' as RDD,'2022-2023' as Year,sum(nvl(GROSS_AMT,0)) as Gross_Amount  from ifms.CONSOLIDATED_BILL_MST ");
			lSBQuery.append("where ((YEAR=2022 and MONTH>=4)or (YEAR=2023 and MONTH<4)) and status in (1,5)");
			

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
					//Financia Year
					if (lArrObj[0] != null) {
						lArrListInner.add(lArrObj[0].toString());
					} else {
						lArrListInner.add("");
					}
					//NEW_PENSION_SCHEME_REITREES
					if (lArrObj[1] != null) {
						lArrListInner.add(lArrObj[1].toString());
					} else {
						lArrListInner.add("");
					}
					//OLD_PENSION_SCHEME_REITREES
					if (lArrObj[2] != null) {
						lArrListInner.add(lArrObj[2].toString());
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

		



}
