package com.tcs.sgv.gpf.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * @author 397138
 * 
 */
public class GPFApprovedRequestDAOImpl extends GenericDaoHibernateImpl implements GPFApprovedRequestDAO {
	Session ghibSession = null;

	public GPFApprovedRequestDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFApprovedRequestDAO#getGPFApprovedRequestList(java
	 * .lang.String)
	 */
	public List getGPFApprovedRequestList(String lStrLocationCode) {

		List lGpfApprovedListAdvance = new ArrayList();
		List lGpfApprovedListFinal = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		StringBuilder lSBQueryWd = new StringBuilder();
		try {
			lSBQuery
					.append("select MGA.transactionId,MGA.applicationDate,MG.sevaarthId,MGE.name,MGA.gpfAccNo,MGA.advanceType,MGE.basicPay,MGA.amountSanctioned,MGA.orderNo");
			lSBQuery.append(" FROM MstGpfAdvance MGA,MstEmpGpfAcc MG,MstEmp MGE,OrgDdoMst ODM");
			lSBQuery
					.append(" WHERE MGA.gpfAccNo = MG.gpfAccNo AND MGA.statusFlag = 'A' AND MG.mstGpfEmpId = MGE.dcpsEmpId AND MGE.dcpsOrGpf='N' AND MG.ddoCode=ODM.ddoCode AND ODM.locationCode=:locationCode AND MGE.group ='D'");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("locationCode", lStrLocationCode);
			lGpfApprovedListAdvance = lQuery.list();

			lSBQueryWd
					.append("select TGFW.transactionId,TGFW.applicationDate,MG.sevaarthId,MGE.name,TGFW.gpfAccNo,'FW',MGE.basicPay,TGFW.amountSanctioned,TGFW.orderNo");
			lSBQueryWd.append(" FROM TrnGpfFinalWithdrawal TGFW,MstEmpGpfAcc MG,MstEmp MGE,OrgDdoMst ODM");
			lSBQueryWd
					.append(" WHERE TGFW.gpfAccNo = MG.gpfAccNo AND TGFW.reqStatus = 'A' AND MG.mstGpfEmpId = MGE.dcpsEmpId AND MGE.dcpsOrGpf='N' AND MG.ddoCode=ODM.ddoCode AND ODM.locationCode=:locationCode AND MGE.group ='D'");
			Query lQueryF = ghibSession.createQuery(lSBQueryWd.toString());
			lQueryF.setParameter("locationCode", lStrLocationCode);
			lGpfApprovedListFinal = lQueryF.list();

			// add the Final Requests List to Advance List
			lGpfApprovedListAdvance.addAll(lGpfApprovedListFinal);

		} catch (Exception e) {
			logger.error("Exception in getGPFApprovedRequestList of GPFApprovedRequestDAOImpl  : ", e);			
		}
		return lGpfApprovedListAdvance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.sgv.gpf.dao.GPFApprovedRequestDAO#getNewOrderRefId()
	 */
	public String getNewOrderRefId() {

		StringBuilder lSBQuery = new StringBuilder();
		new StringBuilder();
		new StringBuilder();
		List tempList = new ArrayList<Long>();
		new ArrayList<Long>();
		new ArrayList<Long>();
		Long lLngNewOrderRefId = 0L;
		String lStrNewOrdRefId = null;
		String lStrOrderId = "";
		String lStrMonth = "";

		Calendar cal = Calendar.getInstance();

		try {
			Integer lIntMonth = cal.get(Calendar.MONTH) + 1;
			Integer lIntYear = cal.get(Calendar.YEAR);
			if (lIntMonth.toString().length() == 1) {
				lStrMonth = "0" + lIntMonth;
			} else {
				lStrMonth = lIntMonth.toString();
			}
			// code to get the First letter of Order Id (i.e. organization id)
			// from sevaarth Id
			// lStrOrderId = lStrSevaarthId.charAt(0) + lStrMonth +
			// lIntYear.toString().substring(2, 4);
			lStrOrderId = "2" + lStrMonth + lIntYear.toString().substring(2, 4);
			lSBQuery.append(" SELECT MAX(orderNo) FROM MstGpfReq WHERE orderNo LIKE :lStrOrderId");

			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("lStrOrderId", lStrOrderId + '%');
			tempList = lQuery.list();

			// in case its the first request of the month, the counter starts
			// from 1, or next sequence number
			if (tempList != null && tempList.size() > 0 && tempList.get(0) != null) {
				lLngNewOrderRefId = (Long.parseLong(tempList.get(0).toString())) + 1L;
				lStrNewOrdRefId = lLngNewOrderRefId.toString();
			} else {
				lStrNewOrdRefId = String.format(lStrOrderId + "%06d", 1);
			}
		} catch (Exception e) {
			logger.error("Exception in getNewOrderRefId of GPFApprovedRequestDAOImpl  : ", e);			
		}

		return lStrNewOrdRefId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.dao.GPFApprovedRequestDAO#getGpfReqID(java.lang.String)
	 */
	public String getGpfReqID(String transactionId) {
		StringBuilder lSBQuery = new StringBuilder();
		List lLstGpfReqId = null;
		String lStrGpfReqId = "";

		try {
			lSBQuery.append("SELECT mstGpfReqId FROM MstGpfReq ");
			lSBQuery.append("WHERE ");
			lSBQuery.append("transactionId =:transactionId");
			Query lHqlQuery = ghibSession.createQuery(lSBQuery.toString());
			lHqlQuery.setParameter("transactionId", transactionId);
			lLstGpfReqId = lHqlQuery.list();

			if (lLstGpfReqId != null && lLstGpfReqId.size() > 0) {
				lStrGpfReqId = lLstGpfReqId.get(0).toString();
			}
		} catch (Exception e) {
			logger.error("Exception in getGpfReqID of GPFApprovedRequestDAOImpl  : ", e);			
		}

		return lStrGpfReqId;
	}

}
