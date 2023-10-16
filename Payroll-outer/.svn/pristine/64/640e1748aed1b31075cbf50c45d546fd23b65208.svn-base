/**
 * 
 */
package com.tcs.sgv.billproc.counter.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * OrgTokenStatusDAOImpl 
 * This class is use to set different status of token, i.e., U(used),A(available),L(lost)
 * It also validates the usage of particular token. 
 * If token is invalid, then it cannot be assigned to any bill.
 * 
 * Date of Creation : 11th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 *  Hiral    23-Oct-2007   For making changes for code formating
 *
 */
public class OrgTokenStatusDAOImpl extends
		GenericDaoHibernateImpl<OrgTokenStatus, Long> implements
		OrgTokenStatusDAO {
	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	public OrgTokenStatusDAOImpl(Class<OrgTokenStatus> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	/**
	 * Method to check the status and validity of Token to be issued
	 * @param  Long : lLngTokenNo
	 * @param  Long : lLngLocId
	 * 
	 * @return boolean
	 */
	public boolean isValidateToken(Long lLngTokenNo, String lStrLocCode) {
		StringBuffer lSBQuery = new StringBuffer();
		Session session = getSession();
		try {
			lSBQuery.append(" FROM OrgTokenStatus ots WHERE");
			lSBQuery.append(" ots.tokenNo = :tokenNo ");
			lSBQuery.append(" AND ots.tokenOfficeCode = :tokenOfficeCode ");
			lSBQuery.append(" AND ots.tokenStatus='A'");

			Query lQuery = session.createQuery(lSBQuery.toString());

			lQuery.setParameter("tokenNo", lLngTokenNo);
			lQuery.setParameter("tokenOfficeCode", lStrLocCode);

			logger.info("Query for validateToken : " + lQuery.toString());
			logger.info("And Parameter is " + lLngTokenNo + " " + lStrLocCode);

			List lList = lQuery.list();

			if (lList != null && lList.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			logger.error("Exception occurred in validateToken : " + e, e);
		}
		return false;
	}

	/**
	 * Method to update the Status of Token that is issued with the Bill.
	 * @param  Long : lLngTokenNo
	 * @param  Long : lLngLocId
	 * @param  Long : lLngBillNo
	 * @param  Long : lLngUserId
	 * @param  Long : lLngPostId
	 * 
	 * @return void
	 */
	public void updateTokenStatus(Long lLngTokenNo, String lStrLocCode,
			Long lLngBillNo, Long lLngUserId, Long lLngPostId) {
		Long lLngTokenId = getTokenId(lLngTokenNo, lStrLocCode);
		Date lDtCurDate = new java.util.Date(System.currentTimeMillis());
		OrgTokenStatus lObjOrgTokenStatus = new OrgTokenStatus();
		lObjOrgTokenStatus = read(lLngTokenId);
		lObjOrgTokenStatus.setIssueDate(lDtCurDate);
		lObjOrgTokenStatus.setReedemedDate(null);
		lObjOrgTokenStatus.setLostDate(null);
		lObjOrgTokenStatus.setBillNo(lLngBillNo);
		lObjOrgTokenStatus.setTokenStatus("U");
		lObjOrgTokenStatus.setUpdatedPostId(lLngPostId);
		lObjOrgTokenStatus.setUpdatedUserId(lLngUserId);
		lObjOrgTokenStatus.setUpdatedDate(lDtCurDate);
		//update(lObjOrgTokenStatus);
	}

	/**
	 * Method to update the Status of Token that is Lost.
	 * @param  Long : lLngTokenNo
	 * @param  Long : lLngBillNo
	 * @param  Long : lLngUserId
	 * @param  Long : lLngPostId
	 * 
	 * @return void
	 */
	public void updateTokenLost(Long lLngTokenNo, String lStrLocCode,
			Long lLngUserId, Long lLngPostId) {
		Long lLngTokenId = getTokenId(lLngTokenNo, lStrLocCode);
		Date lDtCurDate = new java.util.Date(System.currentTimeMillis());
		OrgTokenStatus lObjOrgTokenStatus = new OrgTokenStatus();
		lObjOrgTokenStatus = read(lLngTokenId);
		lObjOrgTokenStatus.setLostDate(lDtCurDate);
		lObjOrgTokenStatus.setReedemedDate(null);
		lObjOrgTokenStatus.setIssueDate(null);
		lObjOrgTokenStatus.setBillNo(null);
		lObjOrgTokenStatus.setTokenStatus("L");
		lObjOrgTokenStatus.setUpdatedPostId(lLngPostId);
		lObjOrgTokenStatus.setUpdatedUserId(lLngUserId);
		lObjOrgTokenStatus.setUpdatedDate(lDtCurDate);
		//update(lObjOrgTokenStatus);
	}

	/**
	 * Method to update the Status of Token that is Redeemed with the Bill.
	 * @param  Long : lLngTokenNo
	 * @param  Long : lLngBillNo
	 * @param  Long : lLngUserId
	 * @param  Long : lLngPostId
	 * 
	 * @return void
	 */
	public void updateTokenRedeem(Long lLngTokenNo, String lStrLocCode,
			Long lLngUserId, Long lLngPostId) {
		Long lLngTokenId = getTokenId(lLngTokenNo, lStrLocCode);
		Date lDtCurDate = new java.util.Date(System.currentTimeMillis());
		OrgTokenStatus lObjOrgTokenStatus = new OrgTokenStatus();
		lObjOrgTokenStatus = read(lLngTokenId);
		lObjOrgTokenStatus.setReedemedDate(lDtCurDate);
		lObjOrgTokenStatus.setBillNo(null);
		lObjOrgTokenStatus.setIssueDate(null);
		lObjOrgTokenStatus.setLostDate(null);
		lObjOrgTokenStatus.setTokenStatus("A");
		lObjOrgTokenStatus.setUpdatedPostId(lLngPostId);
		lObjOrgTokenStatus.setUpdatedUserId(lLngUserId);
		lObjOrgTokenStatus.setUpdatedDate(lDtCurDate);
		//update(lObjOrgTokenStatus);
	}

	/**
	 * Method to get the Token ID value for respective Token Number.
	 * @param  Long : lLngLocId
	 * @param  Long : lLngBillNo
	 * 
	 * @return Long
	 */
	public Long getTokenId(Long lLngTokenNo, String lStrLocCode) {
		StringBuffer lSBQuery = new StringBuffer();
		Long lLngTokenId = new Long(0);
		Session session = getSession();
		try {
			lSBQuery
					.append(" SELECT ots.tokenId FROM OrgTokenStatus ots WHERE");
			lSBQuery.append(" ots.tokenNo = :tokenNo ");
			lSBQuery.append(" AND ots.tokenOfficeCode = :tokenOfficeCode ");

			Query lQuery = session.createQuery(lSBQuery.toString());

			lQuery.setParameter("tokenNo", lLngTokenNo);
			lQuery.setParameter("tokenOfficeCode", lStrLocCode);

			logger.info("Query for getTokenId : " + lQuery.toString());
			logger.info("And Parameter is " + lLngTokenNo + " " + lStrLocCode);

			List lList = lQuery.list();

			if (lList != null && lList.size() > 0) {
				Iterator it = lList.iterator();
				while (it.hasNext()) {
					lLngTokenId = Long.parseLong(it.next().toString());
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred in getTokenId : " + e, e);
		}
		logger.info("Returning value from getTokenId : " + lLngTokenId);
		return lLngTokenId;
	}
}