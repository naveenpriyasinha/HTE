/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 8, 2011		Bhargav Trivedi								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * Class Description -
 * 
 * 
 * @author Bhargav Trivedi
 * @version 0.1
 * @since JDK 5.0 Apr 8, 2011
 */
public class SixPCArrearsDAOImpl extends GenericDaoHibernateImpl implements
		SixPCArrearsDAO {

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	/**
	 * @param type
	 */
	public SixPCArrearsDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.dao.sixPCArrearsDAO#getDdoCodeForDDO(java.lang.Long)
	 */
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
			gLogger.error("Error is :" + e, e);

		}
		return lStrDdoCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.dcps.dao.SixPCArrearsDAO#getDdoCode(java.lang.Long)
	 */
	public String getDdoCode(Long lLngAsstPostId) throws Exception {
		StringBuilder lSBQuery = null;
		String lStrDdoCode = null;
		List lLstCodeList = null;
		Query hqlQuery = null;
		try {
			lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT OD.ddoCode");
			lSBQuery.append(" FROM RltDdoAsst RD, OrgDdoMst OD");
			lSBQuery
					.append(" WHERE OD.postId = RD.ddoPostId AND RD.asstPostId = :asstPostId ");
			hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			hqlQuery.setParameter("asstPostId", lLngAsstPostId);
			lLstCodeList = hqlQuery.list();
			lStrDdoCode = lLstCodeList.get(0).toString();
		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
		}
		return lStrDdoCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.dao.SixPCArrearsDAO#getEmpListForSixPCArrears(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getEmpListForSixPCArrears(String lStrDDOCode,
			String lStrDesignation, Map displayTag) throws Exception {

		List lLstEmpList = null;
		StringBuilder lSBQuery = null;
		Query hqlQuery = null;
		try {
			lSBQuery = new StringBuilder();
			String[] columnValues = new String[] { "EM.name", "EM.dcpsId",
					"EM.name", "PC.fromDate", "PC.toDate", "PC.totalAmount",
					"PC.statusFlag", "PC.remarks" };

			if (lStrDesignation != null && lStrDesignation.length() > 0
					&& !lStrDesignation.equals("All Designations")) {

				lSBQuery
						.append(" SELECT EM.dcpsEmpId,EM.dcpsId,EM.name,PC.totalAmount,PC.dcpsSixPCId,PC.statusFlag,PC.remarks,EM.doj,PC.fromDate,PC.toDate,CASE WHEN PC.statusFlag ='D' then 'DRAFT'  WHEN PC.statusFlag ='F' then 'DRAFT' WHEN PC.statusFlag ='R' then 'REJECTED' WHEN PC.statusFlag ='A' then 'APPROVED'\r\n" + 
								"       else '0' end as status ");
				lSBQuery
						.append(" FROM MstEmp EM ,MstSixPCArrears PC WHERE EM.dcpsEmpId=PC.dcpsEmpId");
				lSBQuery
						.append(" AND EM.ddoCode= :DDOCode AND EM.dcpsId is not null and EM.designation = :designation");
				lSBQuery
						.append(" AND (PC.statusFlag = 'D' OR PC.statusFlag  = 'R') ");
				// lSBQuery.append(" GROUP BY EM.dcpsEmpId ");
				lSBQuery.append(" ORDER BY ");

			} else {
				lSBQuery
						.append(" SELECT EM.dcpsEmpId,EM.dcpsId,EM.name,PC.totalAmount,PC.dcpsSixPCId,PC.statusFlag,PC.remarks,EM.doj,PC.fromDate,PC.toDate,CASE WHEN PC.statusFlag ='D' then 'DRAFT'  WHEN PC.statusFlag ='F' then 'DRAFT' WHEN PC.statusFlag ='R' then 'REJECTED' WHEN PC.statusFlag ='A' then 'APPROVED'\r\n" + 
								" else '0' end as status  ");
				lSBQuery
						.append(" FROM MstEmp EM ,MstSixPCArrears PC WHERE (PC.statusFlag = 'D' OR PC.statusFlag = 'R') AND EM.dcpsEmpId=PC.dcpsEmpId");
				lSBQuery
						.append(" AND EM.ddoCode= :DDOCode AND EM.dcpsId is not null ");
				// lSBQuery.append(" GROUP BY EM.dcpsEmpId ");
				lSBQuery.append(" ORDER BY ");
			}

			String orderString = (displayTag
					.containsKey(Constants.KEY_SORT_ORDER) ? (String) displayTag
					.get(Constants.KEY_SORT_ORDER)
					: "asc");

			Integer orderbypara = null;

			if (displayTag.containsKey(Constants.KEY_SORT_PARA)) {
				orderbypara = (Integer) displayTag.get(Constants.KEY_SORT_PARA);
				lSBQuery.append(columnValues[orderbypara.intValue()] + " "
						+ orderString);
			} else {
				lSBQuery.append(" EM.name ASC");
			}

			hqlQuery = ghibSession.createQuery(lSBQuery.toString());

			if (lStrDesignation != null && lStrDesignation.length() > 0
					&& !lStrDesignation.equals("All Designations")) {
				hqlQuery.setParameter("designation", lStrDesignation);
			}

			hqlQuery.setParameter("DDOCode", lStrDDOCode);

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag
					.get(Constants.KEY_PAGE_NO)
					: 1);
			hqlQuery.setFirstResult((pageNo.intValue() - 1)
					* Constants.PDWL_PAGE_SIZE);
			hqlQuery.setMaxResults(Constants.PDWL_PAGE_SIZE);
			lLstEmpList = hqlQuery.list();

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
		}
		return lLstEmpList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.dao.SixPCArrearsDAO#getEmpListForSixPCArrearsDDO(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getEmpListForSixPCArrearsDDO(String lStrDDOCode,
			String lStrDesignation, Map displayTag) throws Exception {

		List lLstEmpList = null;
		StringBuilder lSBQuery = null;
		Query hqlQuery = null;
		try {
			lSBQuery = new StringBuilder();
			String[] columnValues = new String[] { "EM.name", "EM.dcpsId",
					"EM.name", "PC.fromDate", "PC.toDate", "PC.totalAmount",
					"PC.statusFlag", "PC.remarks" };

			if (lStrDesignation != null && lStrDesignation.length() > 0
					&& !lStrDesignation.equals("All Designations")) {
				lSBQuery
						.append(" SELECT EM.dcpsEmpId,EM.dcpsId,EM.name,PC.totalAmount,PC.dcpsSixPCId,PC.statusFlag,PC.remarks,EM.doj,PC.fromDate,PC.toDate,CASE WHEN PC.statusFlag ='D' then 'DRAFT'  WHEN PC.statusFlag ='F' then 'DRAFT' WHEN PC.statusFlag ='R' then 'REJECTED' WHEN PC.statusFlag ='A' then 'APPROVED' else '0' end as status  ");
				lSBQuery
						.append(" FROM MstEmp EM ,MstSixPCArrears PC ,ZpRltDdoMap ZP  WHERE EM.dcpsEmpId=PC.dcpsEmpId and ZP.ZP_DDO_CODE = EM.ddoCode");
				lSBQuery
						.append(" AND EM.ddoCode= :DDOCode AND EM.dcpsId is not null AND EM.designation = :designation");
				lSBQuery
						.append(" AND (PC.statusFlag = 'F' OR PC.statusFlag  = 'A') ");
				// lSBQuery.append(" GROUP BY EM.dcpsEmpId ");
				lSBQuery.append(" ORDER BY ");
			} else {
				lSBQuery
						.append(" SELECT EM.dcpsEmpId,EM.dcpsId,EM.name,PC.totalAmount,PC.dcpsSixPCId,PC.statusFlag,PC.remarks,EM.doj,PC.fromDate,PC.toDate,CASE WHEN PC.statusFlag ='D' then 'DRAFT'  WHEN PC.statusFlag ='F' then 'DRAFT' WHEN PC.statusFlag ='R' then 'REJECTED' WHEN PC.statusFlag ='A' then 'APPROVED' else '0' end as status  ");
				lSBQuery
						.append(" FROM MstEmp EM ,MstSixPCArrears PC ,ZpRltDdoMap ZP WHERE (PC.statusFlag = 'F' OR PC.statusFlag = 'A') AND EM.dcpsEmpId=PC.dcpsEmpId and ZP.ZP_DDO_CODE = EM.ddoCode");
				lSBQuery
						.append(" AND ZP.REPT_DDO_CODE= :DDOCode AND EM.dcpsId is not null ");
				// lSBQuery.append(" GROUP BY EM.dcpsEmpId ");
				lSBQuery.append(" ORDER BY ");
			}

			String orderString = (displayTag
					.containsKey(Constants.KEY_SORT_ORDER) ? (String) displayTag
					.get(Constants.KEY_SORT_ORDER)
					: "desc");

			Integer orderbypara = null;

			if (displayTag.containsKey(Constants.KEY_SORT_PARA)) {
				orderbypara = (Integer) displayTag.get(Constants.KEY_SORT_PARA);
				lSBQuery.append(columnValues[orderbypara.intValue()] + " "
						+ orderString);
			} else {
				lSBQuery.append(" EM.name DESC");
			}

			hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			if (lStrDesignation != null && lStrDesignation.length() > 0
					&& !lStrDesignation.equals("All Designations")) {
				hqlQuery.setParameter("designation", lStrDesignation);
			}

			hqlQuery.setParameter("DDOCode", lStrDDOCode);

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag
					.get(Constants.KEY_PAGE_NO)
					: 1);
			hqlQuery.setFirstResult((pageNo.intValue() - 1)
					* Constants.PDWL_PAGE_SIZE);
			hqlQuery.setMaxResults(Constants.PDWL_PAGE_SIZE);

			lLstEmpList = hqlQuery.list();

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
		}
		return lLstEmpList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.dao.SixPCArrearsDAO#getEmpListForSixPCArrearsCount(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.util.Map)
	 */
	public Integer getEmpListForSixPCArrearsCount(String lStrDDOCode,
			String lStrDesignation) throws Exception {

		Integer count = null;
		StringBuilder lSBQuery = null;
		Query hqlQuery = null;

		try {
			lSBQuery = new StringBuilder();

			if (lStrDesignation != null && lStrDesignation.length() > 0
					&& !lStrDesignation.equals("All Designations")) {

				lSBQuery.append(" SELECT count(*) ");
				lSBQuery
						.append(" FROM MstEmp EM ,MstSixPCArrears PC ,ZpRltDdoMap ZP WHERE EM.dcpsEmpId=PC.dcpsEmpId and ZP.ZP_DDO_CODE = EM.ddoCode");
				lSBQuery
						.append(" AND EM.ddoCode= :DDOCode AND EM.dcpsId is not null AND EM.designation = :designation");
				lSBQuery
						.append(" AND (PC.statusFlag = 'D' OR PC.statusFlag  = 'R') ");
				// lSBQuery.append(" GROUP BY EM.dcpsEmpId ");

			} else {

				lSBQuery.append(" SELECT count(*) ");
				lSBQuery
						.append(" FROM MstEmp EM ,MstSixPCArrears PC ,ZpRltDdoMap ZP WHERE (PC.statusFlag = 'D' OR PC.statusFlag = 'R') AND EM.dcpsEmpId=PC.dcpsEmpId and ZP.ZP_DDO_CODE = EM.ddoCode");
				lSBQuery
						.append(" AND ZP.REPT_DDO_CODE= :DDOCode AND EM.dcpsId is not null");
				// lSBQuery.append(" GROUP BY EM.dcpsEmpId ");
			}

			hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			if (lStrDesignation != null && lStrDesignation.length() > 0
					&& !lStrDesignation.equals("All Designations")) {
				hqlQuery.setParameter("designation", lStrDesignation);
			}

			hqlQuery.setParameter("DDOCode", lStrDDOCode);

			if (hqlQuery.list() != null && hqlQuery.list().size() > 0) {
				count = Integer.parseInt(hqlQuery.list().get(0).toString());
			} else {
				count = 0;
			}

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			e.printStackTrace();
			throw (e);
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.dcps.dao.SixPCArrearsDAO#getEmpListForSixPCArrearsDDOCount
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.util.Map)
	 */
	public Integer getEmpListForSixPCArrearsDDOCount(String lStrDDOCode,
			String lStrDesignation) throws Exception {

		Integer count = null;
		StringBuilder lSBQuery = null;
		Query hqlQuery = null;
		try {
			lSBQuery = new StringBuilder();

			if (lStrDesignation != null && lStrDesignation.length() > 0
					&& !lStrDesignation.equals("All Designations")) {

				lSBQuery.append(" SELECT count(*) ");
				lSBQuery
						.append(" FROM MstEmp EM ,MstSixPCArrears PC ,ZpRltDdoMap ZP WHERE EM.dcpsEmpId=PC.dcpsEmpId and ZP.ZP_DDO_CODE = EM.ddoCode");
				lSBQuery
						.append(" AND EM.ddoCode= :DDOCode AND EM.dcpsId is not null ");
				lSBQuery.append(" AND EM.designation = :designation ");
				lSBQuery
						.append(" AND (PC.statusFlag = 'F' OR PC.statusFlag  = 'A') ");
				// lSBQuery.append(" GROUP BY EM.dcpsEmpId ");
			} else {
				lSBQuery.append(" SELECT count(*) ");
				lSBQuery
						.append(" FROM MstEmp EM ,MstSixPCArrears PC ,ZpRltDdoMap ZP WHERE (PC.statusFlag = 'F' OR PC.statusFlag = 'A') AND EM.dcpsEmpId=PC.dcpsEmpId and ZP.ZP_DDO_CODE = EM.ddoCode");
				lSBQuery
						.append(" AND ZP.REPT_DDO_CODE= :DDOCode AND EM.dcpsId is not null ");
				// lSBQuery.append(" GROUP BY EM.dcpsEmpId ");
			}

			hqlQuery = ghibSession.createQuery(lSBQuery.toString());
			if (lStrDesignation != null && lStrDesignation.length() > 0
					&& !lStrDesignation.equals("All Designations")) {
				hqlQuery.setParameter("designation", lStrDesignation);
			}

			hqlQuery.setParameter("DDOCode", lStrDDOCode);

			if (hqlQuery.list() != null && hqlQuery.list().size() > 0) {
				count = Integer.parseInt(hqlQuery.list().get(0).toString());
			} else {
				count = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
		}
		return count;

	}

}
