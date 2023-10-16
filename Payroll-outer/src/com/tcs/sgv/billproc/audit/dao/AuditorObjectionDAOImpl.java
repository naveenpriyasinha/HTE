package com.tcs.sgv.billproc.audit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.valueobject.RltBillObjection;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * AuditorObjectionDAOImpl This class contains various methods for objections
 * and remarks. It also contains the method for updating objections and getting
 * remarks and objections based on different criteria.
 * 
 * Date of Creation : 10th July 2007 Author : Hiral Shah
 * 
 *  Revision History
 *	=====================
 *  Hiral    23-Oct-2007   For making changes for code formating
 *
 */
public class AuditorObjectionDAOImpl extends
		GenericDaoHibernateImpl<RltBillObjection, Long> implements
		AuditorObjectionDAO {
	Log logger = LogFactory.getLog(getClass());

	public AuditorObjectionDAOImpl(Class<RltBillObjection> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	/**
	 * This method returns the next line item no for rlt_bill_objection table
	 * 
	 * @param billNo :
	 *            String
	 * @param userId :
	 *            String
	 * 
	 * @return int
	 */
	public int getNextLineItem(String lStrBillNo, String lStrUserId) {
		int intLineItemNo = 0;
		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery("SELECT BILL_NO,MAX(LINE_ITEM_NO)  FROM RLT_BILL_OBJECTION "
						+ " WHERE USER_ID = "
						+ lStrUserId
						+ " AND BILL_NO = "
						+ lStrBillNo + " GROUP BY BILL_NO");
		List lLstDataList = query.list();
		Iterator it = lLstDataList.iterator();

		if (lLstDataList != null) {
			if (lLstDataList.size() <= 0) {
				intLineItemNo = 1;
				return intLineItemNo;
			} else {
				Object[] lObjTuple = (Object[]) it.next();
				intLineItemNo = Integer.parseInt(lObjTuple[1].toString());
				return intLineItemNo;
			}
		}
		return intLineItemNo;
	}

	/**
	 * This method returns status of updating objections value in
	 * rlt_bill_objection
	 * 
	 * @param objList :
	 *            List
	 * @param billNo :
	 *            String
	 * @param userId :
	 *            String
	 * @param objectArgs :
	 *            Map
	 * 
	 * @return int
	 */
	public int updateObjection(List lLstObj, String lStrBillNo,
			String lStrUserId, Map lMapInput) {
		int i = 0;
		long lLngMaxLineItem = 0;
		int updateStatus = 0;
		RltBillObjection lObjRltBillObjectionVO = new RltBillObjection();
		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery("SELECT MAX(LINE_ITEM_NO) FROM RLT_BILL_OBJECTION WHERE USER_ID = "
						+ lStrUserId
						+ " AND BILL_NO = "
						+ lStrBillNo
						+ " AND OBJ_FLAG='MO'");
		List lst = query.list();

		if (lst.size() > 0) {
			Iterator it = lst.iterator();
			if (it.hasNext()) {
				Object lObjTuple = (Object) it.next();
				if (lObjTuple != null)
					lLngMaxLineItem = Long.parseLong(lObjTuple.toString());
				else
					lLngMaxLineItem = 0;
			}
			logger.info("Value of max line item no in updating objections :: "
					+ lLngMaxLineItem);

			if (lLngMaxLineItem > 0) {
				query = hibSession
						.createSQLQuery("DELETE FROM RLT_BILL_OBJECTION WHERE USER_ID = "
								+ lStrUserId
								+ " AND BILL_NO = "
								+ lStrBillNo
								+ " AND OBJ_FLAG = 'MO' AND LINE_ITEM_NO = "
								+ lLngMaxLineItem);
				updateStatus = query.executeUpdate();
				logger
						.info("Query to delete objection from rlt_bill_objection table :: "
								+ query);
				if (lLstObj != null) {
					for (i = 0; i < lLstObj.size(); i++) {
						lObjRltBillObjectionVO = (RltBillObjection) lLstObj
								.get(i);
						logger.info("Inside if(lLngMaxLineItem > 0)");
						Long lLngObjId = BptmCommonServiceImpl.getNextSeqNum(
								"rlt_bill_objection", lMapInput);
						lObjRltBillObjectionVO.setBillObjcId(lLngObjId);
						logger
								.info("Value of Line Item No in update Objections :: "
										+ lObjRltBillObjectionVO
												.getObjectionCode());

						//create(lObjRltBillObjectionVO);
					}
				}
			} else {
				if (lLstObj != null) {
					for (i = 0; i < lLstObj.size(); i++) {
						lObjRltBillObjectionVO = (RltBillObjection) lLstObj
								.get(i);
						logger
								.info("Inside else of if(lLngMaxLineItem > 0), for objection  code : "
										+ lObjRltBillObjectionVO
												.getObjectionCode());
						Long lLngObjId = BptmCommonServiceImpl.getNextSeqNum(
								"rlt_bill_objection", lMapInput);
						lObjRltBillObjectionVO.setBillObjcId(lLngObjId);
						//create(lObjRltBillObjectionVO);
					}
				}
			}
		}
		query = hibSession
				.createSQLQuery("UPDATE RLT_BILL_OBJECTION SET OBJ_FLAG='PO' WHERE OBJ_FLAG='MO' "
						+ " AND USER_ID <>"
						+ lStrUserId
						+ " AND BILL_NO = "
						+ lStrBillNo);
		logger
				.info("Query to update status of objection flag in Bill_objection :: "
						+ query);
		query.executeUpdate();
		return updateStatus;
	}

	/**
	 * This method returns List of Users who have either raised objecitons or
	 * given remarks on particular bill.
	 * 
	 * @param String :
	 *            lStrBillNo
	 * @param Long :
	 *            lLngLangId
	 * 
	 * @return List
	 */
	public List getRmaksObjUsers(String lStrBillNo, Long lLngLangId) {
		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery("SELECT DISTINCT R.USER_ID FROM RLT_BILL_OBJECTION R "
						+ " WHERE R.OBJ_FLAG = 'PO' AND R.BILL_NO = "
						+ lStrBillNo
						+ " UNION SELECT DISTINCT T.USER_ID FROM TRN_BILL_REMARKS T "
						+ " WHERE T.RMRKS_FLAG = 'PR' AND T.BILL_NO = "
						+ lStrBillNo);

		logger.info("Query to get Previous Users in getRmaksObjUsers : "
				+ query);
		List lLstUser = query.list();
		return lLstUser;
	}

	/**
	 * This method returns List of all objections raised previously by all
	 * users(excluding current user) on particular bill.
	 * 
	 * @param String :
	 *            lStrBillNo
	 * @param Long :
	 *            lLngLangId
	 * @param String :
	 *            lStrUserId
	 * 
	 * @return List
	 */
	public List getPrevObjections(String lStrBillNo, Long lLngLangId,
			String lStrUserId) {
		Session hibSession = getSession();
		List lLstObj = null;
		Query query = hibSession
				.createSQLQuery("SELECT M.OBJECTION_CODE, M.OBJECTION_DESC, TO_CHAR(R.CREATED_DATE, 'DD/MM/YYYY HH:MI:SS AM'), TO_CHAR(R.UPDATED_DATE, 'DD/MM/YYYY HH:MI:SS AM') FROM RLT_BILL_OBJECTION R, MST_OBJECTION M WHERE R.OBJECTION_CODE = M.OBJECTION_CODE AND R.OBJ_FLAG = 'PO' AND R.BILL_NO = "
						+ lStrBillNo
						+ " AND M.LANG_ID = "
						+ lLngLangId
						+ " AND R.USER_ID = " + lStrUserId);

		logger
				.info("Query to get Previous User Objections in getPrevObjections : "
						+ query);

		lLstObj = query.list();
		if (lLstObj.size() > 0)
			return lLstObj;
		else
			return null;

	}

	/**
	 * This method returns List of all objections raised previously by all
	 * users(excluding current user) on particular bill.
	 * 
	 * @param String :
	 *            lStrBillNo
	 * @param Long :
	 *            lLngLangId
	 * @param String :
	 *            lStrUserId
	 * 
	 * @return List
	 */
	public List getAllObjections(String lStrBillNo, Long lLngLangId) {
		Session hibSession = getSession();
		List lLstObj = null;
		/*
		 * Query query = hibSession.createSQLQuery("select m.objection_code,
		 * m.objection_desc,to_char(r.created_date,'dd/mm/yyyy HH:MI:SS
		 * AM'),to_char(r.updated_date,'dd/mm/yyyy HH:MI:SS AM') from " + "
		 * rlt_bill_objection r, mst_objection m where " + "
		 * r.objection_code=m.objection_code and r.bill_no=" +lStrBillNo +" and
		 * m.lang_id = " +lLngLangId);
		 */
		List lListUsers = null;
		Query query1 = hibSession
				.createSQLQuery("SELECT UNIQUE(R.POST_ID) FROM  RLT_BILL_OBJECTION R, MST_OBJECTION M,ORG_EMP_MST OEM,TRN_BILL_REGISTER TBR WHERE R.OBJECTION_CODE=M.OBJECTION_CODE AND OEM.USER_ID=R.USER_ID AND R.BILL_NO=TBR.BILL_NO AND R.BILL_NO="
						+ lStrBillNo + " AND M.LANG_ID = " + lLngLangId);
		lListUsers = query1.list();

		Query query = hibSession
				.createSQLQuery("SELECT R.USER_ID,OEM.EMP_PREFIX,OEM.EMP_FNAME,OEM.EMP_MNAME,OEM.EMP_LNAME, R.POST_ID,R.BILL_NO,TBR.BILL_CNTRL_NO,M.OBJECTION_CODE, M.OBJECTION_DESC,TO_CHAR(R.CREATED_DATE,'DD/MM/YYYY HH:MI:SS AM'),TO_CHAR(R.UPDATED_DATE,'DD/MM/YYYY HH:MI:SS AM') FROM  RLT_BILL_OBJECTION R, MST_OBJECTION M,ORG_EMP_MST OEM,TRN_BILL_REGISTER TBR WHERE R.OBJECTION_CODE=M.OBJECTION_CODE AND OEM.USER_ID=R.USER_ID AND R.BILL_NO=TBR.BILL_NO AND R.BILL_NO="
						+ lStrBillNo + " AND M.LANG_ID = " + lLngLangId);

		logger
				.info("Query to get Previous User Objections in getPrevObjections : "
						+ query);

		lLstObj = query.list();
		if (lLstObj.size() > 0)
			return lLstObj;
		else
			return null;

	}

	/**
	 * This method returns List of all objections raised previously by all
	 * users(excluding current user) on particular bill.
	 * 
	 * @param String :
	 *            lStrBillNo
	 * @param Long :
	 *            lLngLangId
	 * @param String :
	 *            lStrUserId
	 * 
	 * @return List
	 */
	public Map getAllObjectionData(String[] BillControlNo, Long lLngLangId) {
		Map map = new HashMap();
		try {
			Session hibSession = getSession();
			for (int i = 0; i < BillControlNo.length; i++) {
				Map mapIn = new HashMap();
				Query query1 = hibSession
						.createSQLQuery("SELECT UNIQUE(R.POST_ID),OEM.EMP_PREFIX,OEM.EMP_FNAME,OEM.EMP_MNAME,OEM.EMP_LNAME,R.CREATED_DATE FROM  RLT_BILL_OBJECTION R, MST_OBJECTION M,ORG_EMP_MST OEM,TRN_BILL_REGISTER TBR WHERE R.OBJECTION_CODE=M.OBJECTION_CODE AND OEM.USER_ID=R.USER_ID AND R.BILL_NO=TBR.BILL_NO AND TBR.BILL_CNTRL_NO ='"
								+ BillControlNo[i]
								+ "' AND M.LANG_ID = "
								+ lLngLangId + " ORDER BY R.CREATED_DATE");
				List uniqUser = query1.list();
				List uniqUserList = new ArrayList();
				List uniqUserListName = new ArrayList();

				if (uniqUser != null && uniqUser.size() > 0) {
					Iterator it = uniqUser.iterator();
					int k = 1;
					while (it.hasNext()) {
						Object[] tuple = (Object[]) it.next();
						String postId = tuple[0].toString();
						String userName = "[" + k + "] " + (String) tuple[1]
								+ " " + (String) tuple[2] + " "
								+ (String) tuple[3] + " " + (String) tuple[4];
						uniqUserList.add(postId);
						uniqUserListName.add(userName);
						// Query query = hibSession.createSQLQuery("select
						// r.user_id,oem.emp_prefix,oem.emp_fname,oem.emp_mname,oem.emp_lname,
						// r.post_id,r.bill_no,tbr.bill_cntrl_no,m.objection_code,
						// m.objection_desc,to_char(r.created_date,'dd/mm/yyyy
						// HH:MI:SS AM'),to_char(r.updated_date,'dd/mm/yyyy
						// HH:MI:SS AM') from rlt_bill_objection r,
						// mst_objection m,org_emp_mst oem,trn_bill_register tbr
						// where r.objection_code=m.objection_code and
						// oem.user_id=r.user_id and r.bill_no=tbr.bill_no and
						// r.post_id="+postId+" and
						// tbr.bill_cntrl_no='"+BillControlNo[i] +"' and
						// m.lang_id = " +lLngLangId);
						Query query = hibSession
								.createSQLQuery("SELECT M.OBJECTION_CODE, M.OBJECTION_DESC,TO_CHAR(R.CREATED_DATE,'DD/MM/YYYY HH:MI:SS AM') FROM  RLT_BILL_OBJECTION R, MST_OBJECTION M,ORG_EMP_MST OEM,TRN_BILL_REGISTER TBR WHERE R.OBJECTION_CODE=M.OBJECTION_CODE AND OEM.USER_ID=R.USER_ID AND R.BILL_NO=TBR.BILL_NO AND R.POST_ID="
										+ postId
										+ " AND TBR.BILL_CNTRL_NO='"
										+ BillControlNo[i]
										+ "' AND M.LANG_ID = "
										+ lLngLangId
										+ " ORDER BY R.CREATED_DATE");
						mapIn.put(userName, query.list());
						// objectionList= query.list();
						k++;
					}
				}
				// map.put(lStrBillNo[i], mapIn);
				map.put(BillControlNo[i], mapIn);
				// map.put(lStrBillNo[i]+"user", uniqUserList);
				// map.put(lStrBillNo[i]+"userName", uniqUserListName);
			}
		} catch (Exception e) {
			logger.error("Error occurred in getAllObjectionData : " + e, e);
		}
		return map;
	}

	/**
	 * This method returns List of all remarks given previously by all
	 * users(excluding current user) on particular bill.
	 * 
	 * @param String :
	 *            lStrBillNo
	 * @param String :
	 *            lStrUserId
	 * 
	 * @return List
	 */
	public List getPrevRemarks(String lStrBillNo, String lStrUserId) {
		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery("SELECT T.REMARKS, TO_CHAR(T.CREATED_DT, 'DD/MM/YYYY HH:MI:SS AM'), TO_CHAR(T.UPDATED_DATE, 'DD/MM/YYYY HH:MI:SS AM') FROM TRN_BILL_REMARKS T WHERE T.RMRKS_FLAG = 'PR' AND T.BILL_NO = "
						+ lStrBillNo + " AND T.USER_ID = " + lStrUserId);
		logger.info("Query to get Previous User Remarks in getPrevRemarks: "
				+ query);
		List lLstObj = query.list();

		return lLstObj;

	}
}
