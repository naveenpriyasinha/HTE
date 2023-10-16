package com.tcs.sgv.common.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.counter.dao.TrnRcptDtlsDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;

/**
 * BptmCommonServicesDAOImpl 
 * This class contains various utility methods such as
 * to get challan id from code and vice versa, to get Bill Control No from Bill
 * No and vice versa, to get Receipt VO, to get Budget Head Structure VO, to get
 * LookupId from Name, to get Emp Name from user Id, to get Lookup Text and
 * description, to get Bill type, to get bill control no, to get max of
 * reference number, to get Total Gross Amount. 
 * It also contains utility methods for checking validity of Previous Bill No, 
 * to check validity of Receipt No, to check bill(physical or online).
 * 
 * 
 * Date of Creation : 13th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * ===================== 
 * Hiral 	24-Oct-2007 	For making changes for code formating
 * 
 */
public class BptmCommonServicesDAOImpl extends GenericDaoHibernateImpl
		implements BptmCommonServicesDAO {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle lObjRsrcBndle = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	public BptmCommonServicesDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	/**
	 * Method to execute Query to get All Bill Types
	 * 
	 * @param Long :
	 *            lLngLangId
	 * 
	 * @return List
	 */
	public List getBillType(Long lLngLangId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		try {
			lSBQuery
					.append("SELECT mbt.subjectId, mbt.billTypeCode, mbt.subjectDesc FROM MstBillType mbt ");
			lSBQuery.append(" WHERE mbt.langId = :langId ");
			lSBQuery.append(" ORDER BY mbt.subjectDesc, mbt.billTypeCode");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("langId", Short.parseShort(lLngLangId
					.toString()));

			logger.info("Query in getBillType : " + lSBQuery.toString());
			logger.info(" And Parameter : " + lLngLangId);
			lListQuery = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception occured in getBillType : " + e, e);
		}
		logger.info("Returning Value from getBillType : " + lListQuery);
		return lListQuery;
	}

	/*
	 * Query sqlQuery = hibSession.createSQLQuery( "SELECT SUBJECT_ID,
	 * BILL_TYPE_CODE, SUBJECT_DESC " + " FROM MST_BILL_TYPE WHERE LANG_ID = "
	 * +lLngLangId + " ORDER BY BILL_TYPE_CODE, SUBJECT_DESC"); logger.info("In
	 * BptmCommonServicesDAOImpl, Query to get Bill Types : " +sqlQuery);
	 * logger.info("In BptmCommonServicesDAOImpl, Parameter1 Language ID : "
	 * +lLngLangId); List lLstQList = sqlQuery.list(); return lLstQList; }
	 */
	/**
	 * Method to get Maximum of Bill Control No + 1(to generate Bill Control No.
	 * in an auto incremental mode)
	 * 
	 * @param Map :
	 *            inputMap
	 * 
	 * @return String
	 */
	public String getBillCtrlNo(Map inputMap) {
		Session hibSession = getSession();
		String lStrDDONum = (String) inputMap.get("DDOCode");
		String lStrBillNo = new String("0");
		StringBuffer lStrQuery = new StringBuffer();
		lStrQuery
				.append("SELECT billCntrlNo  FROM TrnBillRegister where billNo in (select max(billNo) from TrnBillRegister where extract(year from createdDate) = extract(year from sysdate) ");

		if (lStrDDONum != null && !lStrDDONum.equals("")) {
			lStrQuery.append(" and ddoCode ='" + lStrDDONum + "'");
		}
		lStrQuery.append(" and locationCode='"
				+ SessionHelper.getLocationCode(inputMap) + "') ");
		Query query = hibSession.createQuery(lStrQuery.toString());
		logger
				.info("In BptmCommonServicesDAOImpl, Query to get Bill Control No. : "
						+ query.toString());
		List lLstQList = query.list();
		if (lLstQList != null) {
			Iterator it = lLstQList.iterator();
			if (it != null && it.hasNext()) {
				Object lObjBilNo = (Object) it.next();
				if (lObjBilNo != null) {
					lStrBillNo = lObjBilNo.toString();
					logger.info(" Return from Query........ " + lStrBillNo);
				}
			}
		}
		String[] billNo = lStrBillNo.split("/");
		String newBillNo = "";
		if (billNo != null && billNo.length > 1) {
			lStrBillNo = billNo[billNo.length - 1];
			//System.out.println("----------- lStrBillNo return ing is "+ lStrBillNo);
			for (int i = 0; i < billNo.length; i++) {
				if (i != (billNo.length - 1)) {
					newBillNo = newBillNo + billNo[i] + "/";
				} else {
					newBillNo = newBillNo
							+ String.valueOf(Long.parseLong(lStrBillNo) + 1);
				}
				logger.info("============== " + newBillNo);
			}
		}
		return newBillNo;
	}

	public String getBillCtrlNoForOBPM(Map inputMap) {
		Session hibSession = getSession();
		String lStrDDONum = (String) inputMap.get("DDOCode");
		String lStrBillNo = new String("0");
		StringBuffer lStrQuery = new StringBuffer();
		lStrQuery
				.append("SELECT billCntrlNo  FROM TrnBillRegister where billNo in (select max(billNo) from TrnBillRegister where extract(year from createdDate) = extract(year from sysdate) ");

		if (lStrDDONum != null && !lStrDDONum.equals("")) {
			lStrQuery.append(" and ddoCode ='" + lStrDDONum + "'");
		}
		lStrQuery.append(" and locationCode='"
				+ SessionHelper.getLocationCode(inputMap) + "' ) ");
		Query query = hibSession.createQuery(lStrQuery.toString());
		logger
				.info("In BptmCommonServicesDAOImpl, Query to get Bill Control No. : "
						+ query.toString());
		List lLstQList = query.list();
		if (lLstQList != null) {
			Iterator it = lLstQList.iterator();
			if (it != null && it.hasNext()) {
				Object lObjBilNo = (Object) it.next();
				if (lObjBilNo != null) {
					lStrBillNo = lObjBilNo.toString();
					logger.info(" Return from Query........ " + lStrBillNo);
				}
			}
		}
		
		if(lStrBillNo!=null && lStrBillNo.length()>0&&lStrBillNo.indexOf("(")>-1)
			lStrBillNo=lStrBillNo.substring(0, lStrBillNo.indexOf("("));
		
		String[] billNo = lStrBillNo.split("/");
		if (billNo != null && billNo.length > 1) {
			lStrBillNo = billNo[billNo.length - 1];
		}
		int billCntrlno = Integer.parseInt(lStrBillNo) + 1;
		lStrBillNo = String.valueOf(billCntrlno);
		return lStrBillNo;
	}

	/**
	 * Method to get Post ID from the provided User Id.
	 * 
	 * @param String :
	 *            lStrUserId
	 * 
	 * @return String
	 * @author Bhavik
	 */
	/*
	 * public String getPostIdFromUserId(String lStrUserId) { StringBuffer
	 * lSBQuery = new StringBuffer(); Session hibSession = getSession(); List
	 * lListQuery = null; String lStrPost = ""; try { lSBQuery.append("SELECT
	 * our.postId FROM OrgUserpostRlt our "); lSBQuery.append(" WHERE our.userId =
	 * :userId");
	 * 
	 * Query lQuery = hibSession.createQuery(lSBQuery.toString());
	 * lQuery.setParameter("userId", Long.parseLong(lStrUserId));
	 * 
	 * logger.info("Query For getPostIdFromUserId is : " +lSBQuery.toString());
	 * logger.info("And Parameter for getPostIdFromUserId : "+lStrUserId);
	 * 
	 * lListQuery = lQuery.list(); if(lListQuery!=null) lStrPost =
	 * (String)lListQuery.get(0); } catch(Exception e) { logger.error("Exception
	 * occured in getPostIdFromUserId : " +e,e); } logger.info("Returning Value
	 * from getPostIdFromUserId is : " +lStrPost); return lStrPost; }
	 */
	/*
	 * String str = "SELECT O.POST_ID FROM ORG_USERPOST_RLT O WHERE O.USER_ID = "
	 * +lStrUserId; logger.info("Query For getPostIdFromUserId is :::: "+str);
	 * logger.info("Parameter USER ID for getPostIdFromUserId is ::::
	 * "+lStrUserId); Query query = hibSession.createSQLQuery(str); List
	 * lLstQList = query.list(); Iterator it = lLstQList.iterator(); String
	 * postId = null; while(it.hasNext()) { Object tuple = (Object)it.next();
	 * postId = tuple.toString(); } logger.info("User id return from
	 * getPostIdFromUserId is ::: "+postId); return postId; }
	 */
	/**
	 * Method to get Department ID from Post Id.
	 * 
	 * @param String :
	 *            lStrPostId
	 * @param Long :
	 *            lLngLangId
	 * 
	 * @return String
	 * @author Bhavik
	 */
	/*
	 * public String getDeptIdFromPost(String lStrPostId, Long lLngLangId) {
	 * StringBuffer lSBQuery = new StringBuffer(); Session hibSession =
	 * getSession(); List lListQuery = null; String lStrDept = ""; try {
	 * lSBQuery.append("SELECT opm.locationCode FROM OrgPostMst opm ");
	 * lSBQuery.append(" WHERE opm.postId = :postId "); lSBQuery.append(" AND
	 * opm.langId = :langId ");
	 * 
	 * Query lQuery = hibSession.createQuery(lSBQuery.toString());
	 * lQuery.setParameter("postId", Long.parseLong(lStrPostId));
	 * lQuery.setParameter("langId", lLngLangId);
	 * 
	 * logger.info("Query For getDeptIdFromPost is : " +lSBQuery.toString());
	 * logger.info("And Parameter for getDeptIdFromPost : "+lStrPostId +" "
	 * +lLngLangId);
	 * 
	 * lListQuery = lQuery.list(); if(lListQuery!=null) lStrDept =
	 * (String)lListQuery.get(0); } catch(Exception e) { logger.error("Exception
	 * occured in getDeptIdFromPost : " +e,e); } logger.info("Returning Value
	 * from getDeptIdFromPost is : " +lStrDept); return lStrDept; }
	 */
	/*
	 * Session hibSession = getSession(); String str = "SELECT LOCATION_CODE
	 * FROM ORG_POST_MST WHERE POST_ID = "+lStrPostId +" AND LANG_ID = "
	 * +lLngLangId; logger.info("Query For getDeptIdFromPost is :::: "+ str);
	 * logger.info("Parameter POST ID For getDeptIdFromPost is :::: "
	 * +lStrPostId); logger.info("Parameter LANG ID For getDeptIdFromPost is
	 * :::: " +lLngLangId); Query query = hibSession.createSQLQuery(str); List
	 * lLstQList = query.list(); Iterator it = lLstQList.iterator(); String
	 * lStrDeptId=null ; while(it.hasNext()) { Object tuple = (Object)it.next();
	 * lStrDeptId = tuple.toString(); } logger.info("Department id return from
	 * getDeptIdFromPost is ::: "+lStrDeptId); return lStrDeptId; }
	 */
	/**
	 * Method to get Lookup Text from Lookup ID.
	 * 
	 * @param int :
	 *            lIntLookupId
	 * @param Long :
	 *            lLngLangId
	 * 
	 * @return String
	 */
	public String getLookUpText(String lStrLookupName, Long lLngLangId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		String lStrLookupText = "";
		try {
			lSBQuery.append("SELECT clm.lookupName FROM CmnLookupMst clm ");
			lSBQuery.append(" WHERE clm.lookupName = :lookupName ");
			lSBQuery.append(" AND clm.cmnLanguageMst.langId = :langId ");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("lookupName", lStrLookupName);
			lQuery.setParameter("langId", lLngLangId);

			logger.info("Query For getLookUpText  is : " + lSBQuery.toString());
			logger.info("And Parameter for getLookUpText : " + lStrLookupName
					+ "  " + lLngLangId);

			lListQuery = lQuery.list();
			if (lListQuery != null)
				lStrLookupText = (String) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception occured in getLookUpText : " + e, e);
		}
		logger
				.info("Returning Value from getLookUpText is : "
						+ lStrLookupText);
		return lStrLookupText;
	}

	/*
	 * Session hibSession = getSession(); String lStrLookupName = new String();
	 * Query query = hibSession.createSQLQuery("SELECT LOOKUP_NAME FROM
	 * CMN_LOOKUP_MST WHERE LOOKUP_NAME = '" +lIntLookupName +"' AND LANG_ID = "
	 * +lLngLangId); logger.info("Query For getLookUpText is :::: "+ query);
	 * logger.info("Parameter LOOKUP ID For getLookUpText is :::: "+
	 * lIntLookupName); logger.info("Parameter LANG ID For getLookUpText is :::: "+
	 * lLngLangId); List lLstQList =query.list(); Iterator it =
	 * lLstQList.iterator(); while(it.hasNext()) { Object obj = (Object)
	 * it.next(); lStrLookupName = obj.toString(); } return lStrLookupName; }
	 */
	/**
	 * Method to get Lookup Description from Lookup ID.
	 * 
	 * @param int :
	 *            lIntLookupId
	 * @param Long :
	 *            lLngLangId
	 * 
	 * @return String
	 */
	public String getLookUpDesc(int lIntLookupId, Long lLngLangId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		String lStrLookupDesc = "";
		try {
			lSBQuery.append("SELECT clm.lookupDesc FROM CmnLookupMst clm ");
			lSBQuery.append(" WHERE clm.lookupId = :lookupId ");
			lSBQuery.append(" AND clm.cmnLanguageMst.langId = :langId ");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("lookupId", Long.parseLong(String
					.valueOf(lIntLookupId)));
			lQuery.setParameter("langId", lLngLangId);

			logger.info("Query For getLookUpDesc  is : " + lSBQuery.toString());
			logger.info("And Parameter for getLookUpDesc : " + lIntLookupId
					+ "  " + lLngLangId);

			lListQuery = lQuery.list();
			if (lListQuery != null)
				lStrLookupDesc = (String) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception occured in getLookUpDesc : " + e, e);
		}
		logger
				.info("Returning Value from getLookUpDesc is : "
						+ lStrLookupDesc);
		return lStrLookupDesc;
	}

	/*
	 * Session hibSession = getSession(); String lStrLookupDesc = null; Query
	 * query = hibSession.createSQLQuery("SELECT LOOKUP_DESC FROM CMN_LOOKUP_MST
	 * WHERE LOOKUP_ID = " +lIntLookupId +" AND LANG_ID = " +lLngLangId);
	 * logger.info("Query For getLookUpText is :::: "+ query);
	 * logger.info("Parameter LOOKUP ID For getLookUpText is :::: "+
	 * lIntLookupId); logger.info("Parameter LANG ID For getLookUpText is :::: "+
	 * lLngLangId); List lLstQList =query.list(); Iterator it =
	 * lLstQList.iterator(); while(it.hasNext()) { Object obj = (Object)
	 * it.next(); lStrLookupDesc = obj.toString(); } return lStrLookupDesc; }
	 */

	/**
	 * Method to get User ID from Post Id.
	 * 
	 * @param String :
	 *            postId
	 * @param Long :
	 *            lLngLangId
	 * 
	 * @return String
	 * @author Bhavik
	 */
	public String getUserIdFromPost(String lStrPostId, Long lLngLangId) {
		/*
		 * StringBuffer lSBQuery = new StringBuffer(); Session hibSession =
		 * getSession(); List lListQuery = null; String lStrUserId = ""; Long
		 * lLngUserId = null; try { lSBQuery.append("SELECT
		 * our.orgUserMst.userId FROM OrgUserpostRlt our "); lSBQuery.append("
		 * WHERE our.orgPostMstByPostId.postId = :postId ");
		 * 
		 * Query lQuery = hibSession.createQuery(lSBQuery.toString());
		 * lQuery.setParameter("postId", Long.parseLong(lStrPostId));
		 * 
		 * logger.info("Query For getUserIdFromPost is : "
		 * +lSBQuery.toString()); logger.info("And Parameter for
		 * getUserIdFromPost : "+lStrPostId);
		 * 
		 * lListQuery = lQuery.list(); if(lListQuery!=null) lLngUserId =
		 * (Long)lListQuery.get(0); if(lLngUserId!=null) lStrUserId =
		 * lLngUserId.toString(); } catch(Exception e) { logger.error("Exception
		 * occured in getUserIdFromPost : " +e,e); } logger.info("Returning
		 * Value from getUserIdFromPost is : " +lStrUserId); return lStrUserId; }
		 */
		Session hibSession = getSession();

		String str = "SELECT O.USER_ID FROM ORG_USERPOST_RLT O WHERE O.POST_ID = "
				+ lStrPostId;
		logger.info("Query For getUserIdFromPost is :::: " + str);
		logger
				.info("Parameter POST ID For getLookUpText is :::: "
						+ lStrPostId);
		Query query = hibSession.createSQLQuery(str);
		List lLstQList = query.list();
		Iterator it = lLstQList.iterator();
		String lStrUserId = null;
		while (it.hasNext()) {
			Object tuple = (Object) it.next();
			lStrUserId = tuple.toString();
		}
		logger.info("User id return from getUserIdFromPost is ::: "
				+ lStrUserId);
		return lStrUserId;
	}

	/**
	 * Method to get User Details for all the posts and levels passed.
	 * 
	 * @param String[] :
	 *            postId
	 * @param String[] :
	 *            levelId
	 * @param Long :
	 *            langId
	 * 
	 * @return List
	 * @author Bhavik
	 */
	public List getUserFromPost(String[] postId, String[] levelId,
			Long lLngLangId) {
		Session hibSession = getSession();
		int lIntLoopI = 0;
		String str = "SELECT EMP_FNAME, EMP_LNAME, R.POST_ID,EMP_MNAME,MM.DSGN_NAME   FROM ORG_EMP_MST O, ORG_USERPOST_RLT R ,ORG_DESIGNATION_MST  MM,ORG_POST_MST P  WHERE  R.POST_ID = P.POST_ID AND P.DSGN_CODE = MM.DSGN_CODE and O.LANG_ID = "
				+ lLngLangId + " AND R.POST_ID IN(";
		for (lIntLoopI = 0; lIntLoopI < postId.length - 1; lIntLoopI++) {
			str = str + postId[lIntLoopI] + ", ";
		}
		str = str + postId[lIntLoopI] + ") AND R.USER_ID=O.USER_ID";
		logger
				.info("Query to get related users as per posts for Multiple forward : :: "
						+ str);
		Query query = hibSession.createSQLQuery(str);
		List lLstQList = query.list();
		Iterator it = lLstQList.iterator();
		List userList = new ArrayList();
		int lIntLoopJ = 0;
		while (it.hasNext()) {
			Object[] tuple = (Object[]) it.next();
			String[] result = new String[3];
			String middleName = tuple[3] !=null ?tuple[3].toString() : "";
			String name = " " + tuple[0] + " " + middleName + " " + tuple[1]
					+ " [" + tuple[4] + "]";
			logger.info("Name of Audiotr for the List to be forwarded ::: "
					+ name);
			logger.info("Post of Auditor  for the List to be forwarded :::: "
					+ tuple[2]);
			result[0] = name;
			result[1] = tuple[2].toString();
			result[2] = levelId[lIntLoopJ];
			userList.add(result);
			lIntLoopJ++;
		}
		logger.info("SIZE OF LIST FROM GET USERFROM POST :::: " + lLstQList);
		return userList;
	}

	/**
	 * Method to check validity of Previous Bill Ctrl No. And if it is valid
	 * then return related Bill No for that Bill Ctrl No.
	 * 
	 * @param String :
	 *            prevBillNo
	 * 
	 * @return long
	 */
	public long checkPreBillNo(String lStrPrevBillNo) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		long billNo = -1;
		try {
			lSBQuery.append("SELECT tbr.billCntrlNo FROM TrnBillRegister tbr ");
			lSBQuery.append(" WHERE tbr.billCntrlNo = :billCntrlNo");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("billCntrlNo", lStrPrevBillNo);

			logger.info("Query for checkPreBillNo : " + lQuery.toString());
			logger.info("Parameter prevBillNo : " + lStrPrevBillNo);

			lListQuery = lQuery.list();
			if (lListQuery != null && lListQuery.size() > 0) {
				if (lListQuery.get(0).toString().length() > 0) {
					String lStrBillCtrlNo = (String) lListQuery.get(0);
					logger.info("Value of lStrBillCtrlNo in checkPreBillNo : "
							+ lStrBillCtrlNo);
					billNo = getBillNoFromBillCtrlNo(lStrBillCtrlNo);
				}
			}

		} catch (Exception e) {
			logger.error("Exception in checkPreBillNo : " + e, e);
		}
		logger.info("Returning Value from checkPreBillNo : " + billNo);
		return billNo;
	}

	/*
	 * logger.info("In CheckPreBillNo"); Query query =
	 * hibSession.createSQLQuery("SELECT BILL_CNTRL_NO FROM TRN_BILL_REGISTER T
	 * WHERE BILL_CNTRL_NO='" +lStrPrevBillNo +"'"); logger.info("Query to get
	 * Bill Cntrl No. for Previous Bill Control No. : " +query);
	 * logger.info("Parameter prevBillNo : " +lStrPrevBillNo); List lLstQList
	 * =query.list(); logger.info("Value form List of Query :: "
	 * +lLstQList.toString()); String billCtrlNo = null; long billNo = -1;
	 * if(lLstQList!=null && lLstQList.size()>0) { Iterator it =
	 * lLstQList.iterator(); //System.out.println("Value form Iterator of Query :: "
	 * +it.toString()); while(it.hasNext()) { billCtrlNo = (String)it.next();
	 * //System.out.println("Value of Bill Control No :: " +billCtrlNo); } billNo =
	 * getBillNoFromBillCtrlNo(billCtrlNo); } logger.info("VAlue of Bill No
	 * after checking the validations ::" +billNo); return billNo; }
	 */
	/**
	 * Method to get bill_no form bill_control_no.
	 * 
	 * @param String :
	 *            bill control no
	 * @return Long
	 */
	public Long getBillNoFromBillCtrlNo(String lStrBillCtrl) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		Long lLngBillNo = null;
		try {
			lSBQuery.append("SELECT tbr.billNo FROM TrnBillRegister tbr ");
			lSBQuery.append(" WHERE tbr.billCntrlNo = :billCntrlNo");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("billCntrlNo", lStrBillCtrl);

			logger.info("Query for getBillNoFromBillCtrlNo : "
					+ lQuery.toString());
			logger.info("Parameter BillNo : " + lStrBillCtrl);

			lListQuery = lQuery.list();
			if (lListQuery != null && lListQuery.size() > 0)
				lLngBillNo = (Long) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception in getBillNoFromBillCtrlNo : " + e, e);
		}
		logger.info("Returning Value from getBillNoFromBillCtrlNo : "
				+ lLngBillNo);
		return lLngBillNo;
	}

	/*
	 * Session hibSession = getSession(); Query query =
	 * hibSession.createSQLQuery("SELECT BILL_NO FROM TRN_BILL_REGISTER WHERE
	 * BILL_CNTRL_NO='" +lStrBillCtrl +"'"); logger.info("Query for getBillNo
	 * from Bill cntrl No. : " +query); List lLstQList = query.list(); Long
	 * lLngBillNo = null; if(lLstQList!=null && lLstQList.size()>0) { Iterator
	 * it = lLstQList.iterator();
	 * 
	 * while(it.hasNext()) { Object obj = (Object) it.next(); lLngBillNo =
	 * Long.parseLong(obj.toString()); } } logger.info("Value of Bill Number to
	 * be returned : " +lLngBillNo); return lLngBillNo; }
	 */
	/**
	 * Method to get Bill_Control_No from Bill No.
	 * 
	 * @param String :
	 *            billNo
	 * 
	 * @return String
	 */
	public String getBillCntrlNoFromBillNo(String lStrBillNo) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		String lStrBillCtrlNo = null;
		try {
			lSBQuery.append("SELECT tbr.billCntrlNo FROM TrnBillRegister tbr");
			lSBQuery.append(" WHERE tbr.billNo = :billNo");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("billNo", Long.parseLong(lStrBillNo));

			logger.info("Query for getBillCntrlNoFromBillNo : "
					+ lQuery.toString());
			logger.info("Parameter BillNo : " + lStrBillNo);

			lListQuery = lQuery.list();
			if (lListQuery != null && lListQuery.size() > 0)
				lStrBillCtrlNo = (String) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception in getBillCntrlNoFromBillNo : " + e, e);
		}
		logger.info("Returning Value from getBillCntrlNoFromBillNo : "
				+ lStrBillCtrlNo);
		return lStrBillCtrlNo;
	}

	/*
	 * Session hibSession = getSession(); Query query =
	 * hibSession.createSQLQuery("SELECT BILL_CNTRL_NO FROM TRN_BILL_REGISTER
	 * WHERE BILL_NO=" +lStrBillNo); logger.info("Query for getBillCntrlNo from
	 * Bill No. : " +query); List lLstQList = query.list(); String
	 * lStrBillCntrlNo = null; if(lLstQList!=null && lLstQList.size()>0) {
	 * Iterator it = lLstQList.iterator(); while(it.hasNext()) { Object obj =
	 * (Object) it.next(); lStrBillCntrlNo = obj.toString(); } }
	 * logger.info("Value of Bill Contorl Number to be returned : "
	 * +lStrBillCntrlNo); return lStrBillCntrlNo; }
	 */
	/**
	 * Method to check validity of Receipt No. And if it is valid then return
	 * related Receipt ID No for that Receipt No.
	 * 
	 * @param String :
	 *            lStrRcptNo
	 * 
	 * @return long
	 */
	public long checkReceiptNo(String lStrRcptNo) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		long rcptId = -1;
		try {
			lSBQuery.append("SELECT trd.receiptNo FROM TrnReceiptDetails trd ");
			lSBQuery.append(" WHERE trd.receiptNo = :receiptNo");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("receiptNo", lStrRcptNo);

			logger.info("Query for checkReceiptNo : " + lQuery.toString());
			logger.info("Parameter BillNo : " + lStrRcptNo);

			lListQuery = lQuery.list();
			if (lListQuery != null && lListQuery.size() > 0) {
				rcptId = -1;
			} else {
				rcptId = 1;
			}
		} catch (Exception e) {
			logger.error("Exception in checkReceiptNo : " + e, e);
		}
		logger.info("Returning Value from checkReceiptNo : " + lStrRcptNo);
		return rcptId;
	}

	/*
	 * Session hibSession = getSession();
	 * 
	 * logger.info("In checkReceiptNo"); Query query =
	 * hibSession.createSQLQuery("SELECT RECEIPT_NO FROM TRN_RECEIPT_DETAILS
	 * WHERE RECEIPT_NO=" +lStrRcptNo); logger.info("Query to get Receipt No.
	 * for Entered Receipt No. : " +query); logger.info("Parameter lStrRcptNo : "
	 * +lStrRcptNo); List lLstQList =query.list(); logger.info("Value form List
	 * of Query :: " +lLstQList.toString()); long rcptId = -1;
	 * if(lLstQList!=null && lLstQList.size()>0) { rcptId = -1; } else { rcptId =
	 * 1; } return rcptId; }
	 */
	/**
	 * Method to get Challan ID from Challan Code
	 * 
	 * @param String :
	 *            challanCode
	 * 
	 * @return Long
	 */
	public Long getChallanIdfromCode(String lStrChallanCode) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		Long lLngChallanId = null;
		try {
			lSBQuery
					.append("SELECT trd.receiptDetailId FROM TrnReceiptDetails trd");
			lSBQuery.append(" WHERE trd.receiptNo = :receiptNo");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("receiptNo", lStrChallanCode);

			logger
					.info("Query for getChallanIdfromCode : "
							+ lQuery.toString());
			logger.info("Parameter lStrChallanCode : " + lStrChallanCode);

			lListQuery = lQuery.list();
			if (lListQuery != null)
				lLngChallanId = (Long) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception in getChallanIdfromCode : " + e, e);
		}
		logger.info("Returning Value from getChallanIdfromCode : "
				+ lLngChallanId);
		return lLngChallanId;
	}

	/*
	 * Session hibSession = getSession(); Query query =
	 * hibSession.createSQLQuery("SELECT RECEIPT_DETAIL_ID FROM
	 * TRN_RECEIPT_DETAILS WHERE RECEIPT_NO=" +lStrChallanCode);
	 * 
	 * logger.info("Query for getBillCntrlNo from Bill No. : " +query); List
	 * lLstQList = query.list(); Long lLngChallanId = null; if(lLstQList!=null &&
	 * lLstQList.size()>0) { Iterator it = lLstQList.iterator();
	 * while(it.hasNext()) { Object obj = (Object) it.next(); lLngChallanId =
	 * Long.parseLong(obj.toString()); } } logger.info("Value of Bill Contorl
	 * Number to be returned : " +lLngChallanId); return lLngChallanId; }
	 */
	/**
	 * Method to get challan code from challan_id
	 * 
	 * @param String :
	 *            challan id
	 * 
	 * @return Long
	 */
	public String getChallanCodefromId(String lStrChallanId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		String lStrChallanCode = null;
		try {
			lSBQuery.append("SELECT trd.receiptNo FROM TrnReceiptDetails trd");
			lSBQuery.append(" WHERE trd.receiptDetailId = :receiptDetailId");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("receiptDetailId", Long
					.parseLong(lStrChallanId));

			logger
					.info("Query for getChallanCodefromId : "
							+ lQuery.toString());
			logger.info("Parameter lStrChallanId : " + lStrChallanId);

			lListQuery = lQuery.list();
			if (lListQuery != null)
				lStrChallanCode = (String) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception in getChallanCodefromId : " + e, e);
		}
		logger.info("Returning Value from getChallanCodefromId : "
				+ lStrChallanCode);
		return lStrChallanCode;
	}

	/**
	 * To get total expenditure for the current financial year.
	 * 
	 * @param Map:
	 *            mp
	 * @return BigDecimal
	 */
	public BigDecimal getTotalGrossAmount(Map mp) {
		String demandNo = (String) mp.get("demandCode");
		String mjrHd = (String) mp.get("majorHead");
		String subMjrHd = (String) mp.get("subMajorHead");
		String minorHd = (String) mp.get("minorHead");
		String subHd = (String) mp.get("subHead");
		String bdgtType = (String) mp.get("budgetType");
		String ddoCode = (String) mp.get("ddoCode");
		ServiceLocator serv = (ServiceLocator) mp.get("serviceLocator");

		Long lLngLangId = SessionHelper.getLangId(mp);
		BillCommonDAOImpl lObjBillCmnDao = new BillCommonDAOImpl(serv
				.getSessionFactory());
		SgvcFinYearMst lObjFinYrMst = lObjBillCmnDao.getFinYrInfo(
				new java.util.Date(System.currentTimeMillis()), lLngLangId);

		long lFinYrId = lObjFinYrMst.getFinYearId();

		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery("select bill_no from trn_bill_budhead_dtls where "
						+ " dmnd_no='"
						+ demandNo
						+ "' and bud_mjr_hd='"
						+ mjrHd
						+ "' and bud_submjr_hd='"
						+ subMjrHd
						+ "' and bud_min_hd ='"
						+ minorHd
						+ "' and bud_sub_hd='"
						+ subHd
						+ "' and bud_type='"
						+ bdgtType + "' and fin_year_id=" + lFinYrId);
		logger
				.info("Query to get Bill No. for related Majorhead combinations :: "
						+ query);
		List lst = query.list();
		String billNo[] = new String[lst.size()];
		Iterator it = lst.iterator();
		int i = 0;
		while (it.hasNext()) {
			Object obj = (Object) it.next();
			billNo[i] = obj.toString();

			i++;
		}

		String str = " SELECT DDO_CODE, BILL_GROSS_AMOUNT FROM TRN_BILL_REGISTER WHERE CHEQUE_DISP_DT IS NOT NULL ";
		if (billNo.length > 0) {
			str = str + " AND BILL_NO IN (";
			logger.info("Total No. of bills for same head Structure : "
					+ billNo.length);
			for (i = 0; i < (billNo.length - 1); i++) {
				str = str + billNo[i] + ",";
			}

			str = str + billNo[billNo.length - 1] + ")";

		} else {
			str = str + " AND BILL_NO IN (NULL)";
		}
		str = str + " AND DDO_CODE = '" + ddoCode + "'";
		str = str
				+ " AND BILL_NO NOT IN (SELECT BILL_NO FROM TRN_BILL_MVMNT WHERE MVMNT_STATUS='BRJCT_AUD') ";

		logger.info("Query to get net amount for all approved bills : " + str);
		query = hibSession.createSQLQuery(str);
		lst = query.list();
		it = lst.iterator();

		BigDecimal totalNetAmt = new BigDecimal(0);
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			logger.info("At index 0----------- : " + obj[0].toString());
			logger.info("At index 1----------- : " + obj[1].toString());
			logger
					.info("Net amount from related Major head and Bill Register query :: "
							+ obj[1]);
			BigDecimal amt = BigDecimal.valueOf(Double.parseDouble(obj[1]
					.toString()));
			totalNetAmt = totalNetAmt.add(amt);
		}
		logger.info("Total Net Amount is :::: " + totalNetAmt);
		return totalNetAmt;
	}

	/**
	 * to get employee name from user id
	 * 
	 * @param lStrUserId
	 * @param lLngLangId
	 * @return
	 */

	public String getEmpNameFrmUserId(String lStrUserId, Long lLngLangId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		String lStrEmpName = null;
		try {
			lSBQuery
					.append("SELECT oem.empPrefix, oem.empFname, oem.empMname, oem.empLname FROM OrgEmpMst oem");
			lSBQuery.append(" WHERE oem.orgUserMst.userId = :userId");
			lSBQuery.append(" AND oem.cmnLanguageMst.langId = :langId");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("userId", Long.parseLong(lStrUserId));
			lQuery.setParameter("langId", lLngLangId);

			logger.info("Query for getEmpNameFrmUserId : " + lQuery.toString());
			logger.info("Parameter lStrChallanId : " + lStrUserId + "  "
					+ lLngLangId);

			lListQuery = lQuery.list();
			logger.info("Value of List from getEmpNameFrmUserId : "
					+ lListQuery);
			if (lListQuery != null) {
				Iterator it = lListQuery.iterator();
				while (it.hasNext()) {
					Object[] obj = (Object[]) it.next();
					lStrEmpName = obj[0] + " " + obj[1] + " " + obj[2] + " "
							+ obj[3];
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpNameFrmUserId : " + e, e);
		}
		logger
				.info("Returning Value from getEmpNameFrmUserId : "
						+ lStrEmpName);
		return lStrEmpName;
	}

	/*
	 * Session hibSession = getSession(); Query query =
	 * hibSession.createSQLQuery("select e.emp_prefix, e.emp_fname, e.emp_mname,
	 * e.emp_lname from org_emp_mst e where e.user_id=" +lStrUserId +" and
	 * e.lang_id=" +lLngLangId); //System.out.println("Query to get Empname in
	 * getEmpNameFrmUserId : " +query); List lLstEmpName = query.list(); String
	 * lStrEmpName = ""; if(lLstEmpName!=null && lLstEmpName.size()>0) {
	 * Iterator it = lLstEmpName.iterator(); while(it.hasNext()) { Object[] obj =
	 * (Object []) it.next(); lStrEmpName = obj[0] +" " +obj[1] +" " +obj[2] +" "
	 * +obj[3]; } } return lStrEmpName; }
	 */
	/**
	 * Getting lookup id from lookup name
	 * 
	 * @param String:
	 *            lookupName
	 * @param Long :
	 *            lLngLangId
	 * @return String
	 */

	public String getLookupIdFromName(String lookupName, Long lLngLangId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		String lStrLookupId = null;
		try {
			lSBQuery.append("SELECT clm.lookupId FROM CmnLookupMst clm");
			lSBQuery.append(" WHERE clm.lookupName = :lookupName ");
			lSBQuery.append(" AND clm.cmnLanguageMst.langId = :langId");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("lookupName", lookupName);
			lQuery.setParameter("langId", lLngLangId);

			logger.info("Query for getLookupIdFromName : " + lQuery.toString());
			logger.info("Parameter : " + lookupName + "  " + lLngLangId);

			lListQuery = lQuery.list();
			if (lListQuery != null) {
				logger.info("Value of list.get(0) : " + lListQuery.get(0));
				if (lListQuery.get(0) != null
						&& lListQuery.get(0).toString().length() > 0) {
					lStrLookupId = String.valueOf(lListQuery.get(0));
					logger.info("lStrLookupId : " + lStrLookupId);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getLookupIdFromName : " + e, e);
		}
		logger.info("Returning Value from getLookupIdFromName : "
				+ lStrLookupId);
		return lStrLookupId;
	}

	/*
	 * Session hibSession = getSession(); Query query =
	 * hibSession.createSQLQuery("SELECT LOOKUP_ID FROM CMN_LOOKUP_MST L WHERE
	 * L.LOOKUP_NAME = '"+lookupName+"' AND LANG_ID ="+langId); List lLstQList =
	 * query.list(); Iterator it = lLstQList.iterator(); String lLongLookupId =
	 * null; while(it.hasNext()) { Object obj = (Object) it.next(); if(obj !=
	 * null) { lLongLookupId = obj.toString(); } } return lLongLookupId; }
	 */

	/**
	 * This is to check whether bill is physical or online
	 * 
	 * @param Long:billNo
	 * @return boolean
	 */
	public boolean isPhyBill(Long billNo) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		boolean phyBill = true;
		try {
			lSBQuery.append("SELECT tbr.phyBill FROM TrnBillRegister tbr ");
			lSBQuery.append(" WHERE tbr.billNo = :billNo ");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("billNo", billNo);

			logger.info("Query for isPhyBill : " + lQuery.toString());
			logger.info("Parameter : " + billNo);

			lListQuery = lQuery.list();
			if (lListQuery != null && lListQuery.size() > 0) {
				logger
						.info("Inside if(lListQuery!=null && lListQuery.size()>0) of isPhyBill");
				if (lListQuery.get(0) != null
						&& lListQuery.get(0).toString().length() > 0) {
					String lStrPhyFlag = lListQuery.get(0).toString();
					if (lStrPhyFlag.equals("1")) {
						phyBill = true;
					} else {
						phyBill = false;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in isPhyBill : " + e, e);
		}
		logger.info("Returning Value from isPhyBill : " + phyBill);
		return phyBill;
	}

	/**
	 * To get BillVO from cheque number
	 * 
	 * @param lLngChqNo
	 * @return TrnBillRegister
	 */
	public TrnBillRegister getBillVoFromChqNo(Long lLngChqNo) {
		Long lLngBillNo = null;
		Session hibSession = getSession();
		TrnBillRegister lObjTrnBillRegVo = new TrnBillRegister();
		Query query = hibSession
				.createSQLQuery("SELECT R.BILL_NO FROM RLT_BILL_CHEQUE R WHERE R.CHEQUE_ID IN "
						+ " (SELECT T.CHEQUE_ID FROM TRN_CHEQUE_DTLS T WHERE T.CHEQUE_NO="
						+ lLngChqNo + ")");

		//System.out.println("Query to get Bill no in getBillVoFromChqId : "+ query);
		List lLstBillNo = query.list();
		if (lLstBillNo != null) {
			Iterator it = lLstBillNo.iterator();
			while (it.hasNext()) {
				Object obj = (Object) it.next();
				lLngBillNo = Long.parseLong(obj.toString());
			}
			if (lLngBillNo != null) {
				lObjTrnBillRegVo = (TrnBillRegister) read(lLngBillNo);
			}
		}
		return lObjTrnBillRegVo;
	}

	/**
	 * Method to obtain VO for 'Trn_Receipt_Details' from Bill No.
	 * 
	 * @param Long :
	 *            lLngBillNo
	 * @param Map :
	 *            inputMap
	 * 
	 * @return TrnReceiptDetails
	 */
	public TrnBillBudheadDtls getBudHdVoFromBillNo(Long lLngBillNo) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lQueryList = null;

		TrnBillBudheadDtls lObjBudHeadVO = null;

		try {
			lSBQuery.append(" FROM TrnBillBudheadDtls tbd WHERE");
			lSBQuery.append(" tbd.billNo = :billNo ");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("billNo", lLngBillNo);

			logger
					.info("Query to get Bill Budget Id from TrnBillBudHeadDtls in updateBudHeadDetails : "
							+ lQuery);
			logger.info("and Parameter is billNo : " + lLngBillNo);
			// query = hibSession.createSQLQuery("select BILL_BUD_ID from
			// trn_bill_budhead_dtls where bill_no = " +billNo);
			lQueryList = lQuery.list();

			if (lQueryList.size() > 0) {
				lObjBudHeadVO = (TrnBillBudheadDtls) lQueryList.get(0);
			}
		} catch (Exception e) {
			logger.error("Exception occured in getBudHeadId : " + e, e);
		}
		return lObjBudHeadVO;
	}

	/**
	 * Method to obtain VO for 'Trn_Receipt_Details' from Bill No.
	 * 
	 * @param Long :
	 *            lLngBillNo
	 * @param Map :
	 *            inputMap
	 * 
	 * @return TrnReceiptDetails
	 */
	public TrnReceiptDetails getRcptVoFromBillNo(Long lLngBillNo, Map inputMap) {
		Long lLngRcptId = null;
		Session hibSession = getSession();
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		TrnRcptDtlsDAOImpl lObjRcptDtlsDao = new TrnRcptDtlsDAOImpl(
				TrnReceiptDetails.class, serv.getSessionFactory());

		TrnReceiptDetails lObjRcptDetails = new TrnReceiptDetails();
		Query query = hibSession
				.createSQLQuery("SELECT RECEIPT_ID FROM TRN_BILL_REGISTER WHERE BILL_NO= "
						+ lLngBillNo);
		//System.out.println("Query to get Bill no in getRcptVoFromBillNo : "+ query);
		List lLstRcptId = query.list();
		if (lLstRcptId != null) {
			Iterator it = lLstRcptId.iterator();
			while (it.hasNext()) {
				Object obj = (Object) it.next();
				if (obj != null) {
					lLngRcptId = Long.parseLong(obj.toString());
				}
			}
			if (lLngRcptId != null) {
				lObjRcptDetails = lObjRcptDtlsDao.read(lLngRcptId);
			}
		}
		return lObjRcptDetails;
	}

	/**
	 * Method to obtain VO for 'Trn_Receipt_Details' from Bill No after joining
	 * it with 'rlt_bill_challan'.
	 * 
	 * @param Long :
	 *            lLngBillNo
	 * @param Map :
	 *            inputMap
	 * 
	 * @return List
	 */
	public List getTrnRcptFromBill(Long lLngBillNo, Map inputMap) {
		Session hibSession = getSession();		
		StringBuffer lSBQuery = new StringBuffer();
		List lListResult = new ArrayList();
		try {
			lSBQuery.append(" FROM TrnReceiptDetails trd  ");
			lSBQuery.append(" WHERE trd.receiptDetailId IN ");
			lSBQuery
					.append("(SELECT rbc.challanNo FROM RltBillChallan rbc WHERE rbc.billNo = :billNo)");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("billNo", lLngBillNo);

			logger.info("Query in getTrnRcptFromBill : " + lSBQuery.toString());
			logger.info(" And Parameter : " + lLngBillNo);

			//System.out.println("Query to get Bill no in getRcptVoFromBillNo : "+ lQuery);
			List lLstRcptId = lQuery.list();
			if (lLstRcptId != null) {
				for (int i = 0; i < lLstRcptId.size(); i++) {
					TrnReceiptDetails lObjRcptDetails = (TrnReceiptDetails) lLstRcptId
							.get(i);
					lListResult.add(lObjRcptDetails);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getTrnRcptFromBill : " + e);
		}
		return lListResult;
	}

	/**
	 * To get maximum reference number for that particular day TO wise
	 * 
	 * @param String :
	 *            locationCode
	 * @return Long
	 */

	public synchronized Long getMaxRefNum(String locationCode) {
		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery("SELECT MAX(REF_NUM) FROM TRN_BILL_REGISTER WHERE EXTRACT (DAY FROM CREATED_DATE) = EXTRACT (DAY FROM SYSDATE) AND TSRY_OFFICE_CODE ='"
						+ locationCode + "'");
		List resultList = query.list();
		Long nextRefNum = new Long(0);
		if (resultList != null && resultList.size() > 0) {
			Object obj = resultList.get(0);
			if (obj != null)
				nextRefNum = Long.parseLong(obj.toString());
		}
		return ++nextRefNum;
	}
	
	/**
	 * Method to get Subject Id from bill Type
	 * 
	 * @param String :
	 *            lStrBillType
	 * @return Long
	 */
	public Long getSubIdFromBillType(String lStrBillType) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		Long lLngSubjectId = null;
		try {
			lSBQuery.append("SELECT mbt.subjectId FROM MstBillType mbt ");
			lSBQuery.append(" WHERE mbt.billShrtCode = :billShrtCode");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("billShrtCode", lStrBillType);

			logger.info("Query for getSubIdFromBillType : "
					+ lQuery.toString());
			logger.info("Parameter BillNo : " + lStrBillType);

			lListQuery = lQuery.list();
			if (lListQuery != null && lListQuery.size() > 0)
				lLngSubjectId = (Long) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception in getSubIdFromBillType : " + e, e);
		}
		logger.info("Returning Value from getSubIdFromBillType : "
				+ lLngSubjectId);
		return lLngSubjectId;
	}

	/**
	 * Method to get Bill Type from Subject Id 
	 * 
	 * @param Long :
	 *            lLngSubjectId
	 * 
	 * @return String
	 */
	public String getBillTypeFromSubId(Long lLngSubjectId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		String lStrBillType = null;
		try {
			lSBQuery.append("SELECT mbt.billShrtCode FROM MstBillType mbt");
			lSBQuery.append(" WHERE mbt.subjectId = :subjectId");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("subjectId", lLngSubjectId);

			logger.info("Query for getBillTypeFromSubId : "
					+ lQuery.toString());
			logger.info("Parameter subjectId : " + lLngSubjectId);

			lListQuery = lQuery.list();
			if (lListQuery != null && lListQuery.size() > 0)
				lStrBillType = (String) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception in getBillTypeFromSubId : " + e, e);
		}
		logger.info("Returning Value from getBillTypeFromSubId : "
				+ lStrBillType);
		return lStrBillType;
	}


}
