package com.tcs.sgv.pensionproc.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

public class UploadReportDaoImpl extends GenericDaoHibernateImpl {
	public UploadReportDaoImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		this.ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	public void setReportHeaderAndFooter(ReportVO lObjReport, LoginDetails lObjLoginVO) throws Exception {

		StringBuilder lSbHeaderVal = null;
		try {
			String lStrLocCode = lObjLoginVO.getLocation().getLocationCode().trim();
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
			} // name
			// from
			// sgvc_reports

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Error is : " + e, e);
			throw (e);
		}

	}	public List getPPOList(String DelvId) throws Exception{

		List ppoDetails=new ArrayList();

		try{

			StringBuilder lSBQuery = new StringBuilder();
			//	lSBQuery.append("SELECT clm.LOC_NAME,to_char(sdd.UPLOAD_DATE,'dd/mm/yyyy'),clm.LOC_ID,count(distinct sp.PNSR_PPO_NO) ");
			lSBQuery.append("SELECT clm.LOC_NAME,to_char(sdd.UPLOAD_DATE,'dd/MM/yyyy hh:mm'),clm.LOC_ID,count(distinct sp.PNSR_PPO_NO) ");
			lSBQuery.append(" FROM STG_DELIVERY_DTLS sdd ");
			lSBQuery.append("join STG_PPO_ALLOC spa on spa.DELV_ID=sdd.DELV_ID ");
			lSBQuery.append("join  STG_PENSIONER sp on sp.DELV_ID=sdd.DELV_ID ");
			lSBQuery.append("join CMN_LOCATION_MST clm on clm.LOC_ID=sp.TREASURY_DATA_CD and clm.DEPARTMENT_ID=100003 ");
			lSBQuery.append("join TRN_PENSION_RQST_HDR tpr on tpr.PPO_NO=sp.PNSR_PPO_NO ");
			lSBQuery.append("where sdd.DELV_ID=:DelvId and sdd.DELV_TYPE=140006 and sdd.DELV_STATUS in('Successful','SuccessWithError') ");
			lSBQuery.append("group by clm.LOC_NAME,sdd.UPLOAD_DATE,clm.LOC_ID ");
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

			lQuery.setParameter("DelvId", DelvId);
			ppoDetails = lQuery.list();
		}
		catch (Exception e) {
			gLogger.error("error is :" + e, e);
		}
		return ppoDetails;
	}


	public List getPPODtlsList(String DelvId,String LocId) throws Exception{

		List ppoDetails=new ArrayList();

		try{

			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT distinct sp.PNSR_PPO_NO,sp.PEN_NAME,sp.PNSN_CLASS,clm.LOC_NAME FROM STG_DELIVERY_DTLS sdd ");
			lSBQuery.append("join STG_PPO_ALLOC spa on spa.DELV_ID=sdd.DELV_ID ");
			lSBQuery.append("join  STG_PENSIONER sp on sp.DELV_ID=sdd.DELV_ID ");
			lSBQuery.append("join CMN_LOCATION_MST clm on clm.LOC_ID=sp.TREASURY_DATA_CD and clm.DEPARTMENT_ID=100003 ");
			lSBQuery.append("join TRN_PENSION_RQST_HDR tpr on tpr.PPO_NO=sp.PNSR_PPO_NO ");
			lSBQuery.append("where sdd.DELV_ID=:DelvId and sdd.DELV_TYPE=140006 and sp.TREASURY_DATA_CD=:LocId and sdd.DELV_STATUS in('Successful','SuccessWithError')  ");
			lSBQuery.append("and sp.PNSR_PPO_NO is not null ");
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

			lQuery.setParameter("DelvId", DelvId);
			lQuery.setParameter("LocId", LocId);
			ppoDetails = lQuery.list();
		}
		catch (Exception e) {
			gLogger.error("error is :" + e, e);
		}
		return ppoDetails;
	}
	public StringBuilder getReportHeader(String lStrLocCode) throws Exception {

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

}
