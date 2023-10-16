/**
 * package : com.tcs.sgv.onlinebillprep.dao 
 * @verion : 1.0
 * @author Keyur Shah, Amit Singh. 
 *
 */
package com.tcs.sgv.onlinebillprep.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.dao.DDOInfoDAO;
import com.tcs.sgv.common.dao.DDOInfoDAOImpl;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.onlinebillprep.valueobject.RltBillRqst;
import com.tcs.sgv.onlinebillprep.valueobject.TrnAprvdRqst;

/**
 * BillRequestDAOImpl Its a DAO for Searching Data required for Creation of new
 * Bills.
 * 
 * @author Keyur Shah, Amit Singh
 * @version 1.0
 */
public class BillRequestDAOImpl implements BillRequestDAO {

	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory = null;

	/* Global Variable for Session Class */
	Session ghibSession = null;

	public BillRequestDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		ghibSession = sessionFactory.getCurrentSession();
	}

	/**
	 * It Searches for Approved Requests from the Selected Search Criteria by
	 * DDO for Creation of Bill.
	 * 
	 * @param Date,Date,Long,String,Long,String,Long,String,String
	 *            lDtFrmDt,lDtToDt,lLngBillType,lDDOCode,lLngDBId,locCode,lLngLangId,lStrEmpDesgn,lStrEmpType
	 * @return lList List
	 */
	public List getApprovedRequest(Date lDtFrmDt, Date lDtToDt,
			Long lLngBillType, String lDDOCode, Long lLngDBId, String locCode,
			Long lLngLangId, String lStrEmpDesgn, String lStrEmpType) {
		List lList = null;

		StringBuffer lsb = new StringBuffer();
		StringBuilder lStrLoc = new StringBuilder();

		DDOInfoDAO lObjDDOInfoDAO = new DDOInfoDAOImpl(sessionFactory);

		List lLstLoc = lObjDDOInfoDAO.getBillOfficeForDDO(lDDOCode, lLngLangId,
				lLngDBId);

		if (lLstLoc != null && lLstLoc.size() > 0) {
			CommonVO lObjVo = null;
			for (int i = 0; i < lLstLoc.size(); ++i) {
				lObjVo = (CommonVO) lLstLoc.get(i);
				lStrLoc.append(lObjVo.getCommonId() + ",");
			}
			lStrLoc.deleteCharAt(lStrLoc.length() - 1);
		}
		//System.out.println("Location String is = " + lStrLoc.toString());

		try {
			Query q = null;
			lsb
					.append(" SELECT r.trnAprvdRqstId, r.reqAprvdDt, r.empNameEn, r.empNameGu, r.empDesgnEn, ");
			lsb
					.append(" r.empDesgnGu, r.empType, m.subjectDesc, loc.locName, r.empId ,m.subjectId FROM TrnAprvdRqst r, ");
			lsb
					.append(" RltLocationDepartment rl, MstBillType m, CmnLocationMst loc ");
			lsb.append(" WHERE  r.reqAprvdDt between :FromDate AND ");
			lsb.append(" :ToDate AND r.empDeptId = rl.departmentId  AND ");
			lsb
					.append(" m.subjectId = r.billTypeId AND m.langId = :langId AND loc.locationCode = rl.locCode  AND r.isBillCrtd = 'N' ");

			if (lStrEmpType != null) {
				lsb.append(" And r.empType = :empType ");
			}

			if (lStrEmpDesgn != null) {
				lsb.append(" And  r.empDesgnEn = :empDesgn ");
			}

			if (!locCode.equals("-1")) {
				lsb.append("AND rl.locCode = :locCode ");
			}

			else {
				lsb.append("AND rl.locCode IN (" + lStrLoc.toString() + ") ");
			}

			if (lLngBillType != -1) {
				lsb.append("AND r.billTypeId = :billTypeId ");
			}

			q = ghibSession.createQuery(lsb.toString());

			if (!locCode.equals("-1")) {
				q.setParameter("locCode", locCode);

			}

			if (lLngBillType != -1) {
				q.setParameter("billTypeId", lLngBillType);
			}
			if (lStrEmpType != null) {
				q.setParameter("empType", lStrEmpType);
			}
			if (lStrEmpDesgn != null) {
				q.setParameter("empDesgn", lStrEmpDesgn);
			}

			SimpleDateFormat lDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			q.setParameter("FromDate", lDateFormat.parse(lDateFormat
					.format(lDtFrmDt)));
			q.setParameter("ToDate", lDateFormat.parse(lDateFormat
					.format(lDtToDt)));
			q.setParameter("langId", Short.parseShort(lLngLangId.toString()));

			logger.info("Query For getApprovedRequest is : " + lsb.toString());
			logger.info("Params for the Query are :" + lDtFrmDt + "," + lDtToDt
					+ "," + lLngBillType + "," + lStrLoc.toString());

			lList = q.list();

			if (lList != null) {
				logger.info("Size of queryList is : " + lList.size());

			} else {
				logger.info("queryList is NULL !!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in getApprovedRequest is : " + e, e);

		}

		return lList;
	}

	/**
	 * Returns TrnAprvdRqst VO on the basis of trnAprvdRqstId. Criteria by DDO
	 * for Creation of Bill.
	 * 
	 * @param Long
	 *            lLngRqstId
	 * @return lReturnObj TrnAprvdRqst
	 */
	public TrnAprvdRqst getAprvdRqstDtls(Long lLngRqstId) {
		TrnAprvdRqst lReturnObj = null;

		try {
			StringBuffer lSBQuery = new StringBuffer();

			lSBQuery.append(" FROM  TrnAprvdRqst A WHERE ");
			lSBQuery.append(" A.trnAprvdRqstId = :trnAprvdRqstId ");

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("trnAprvdRqstId", lLngRqstId);

			logger.info("Query for getAprvdRqstDetails : " + lQuery.toString());
			logger.info("And Parameter is " + lLngRqstId);

			List lList = lQuery.list();

			if (lList != null && lList.size() > 0) {
				lReturnObj = (TrnAprvdRqst) lList.get(0);
			}
		} catch (Exception e) {
			logger.error("Error in getAprvdRqstDetails is : " + e, e);
		}

		return lReturnObj;
	}

	/**
	 * Returns RltBillRqst VO on the basis of billNo. Criteria by DDO for
	 * Creation of Bill.
	 * 
	 * @param Long
	 *            lLngBillNo
	 * @return lLstBillRqst List<RltBillRqst>
	 */
	public List<RltBillRqst> getRqstInfoFrmBill(Long lLngBillNo) {
		List<RltBillRqst> lLstBillRqst = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append(" FROM RltBillRqst WHERE billNo = :billNo");

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("billNo", lLngBillNo);

			logger.info("Query for getAprvdRqstDetails : " + lQuery.toString());
			logger.info("Parameter is " + lLngBillNo);

			lLstBillRqst = (List<RltBillRqst>) lQuery.list();
		} catch (Exception e) {
			logger.error("Error in getRqstInfoFrmBill is : " + e, e);
		}
		return lLstBillRqst;
	}
}
