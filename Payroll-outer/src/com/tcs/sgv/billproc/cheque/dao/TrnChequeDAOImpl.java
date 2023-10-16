package com.tcs.sgv.billproc.cheque.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.cheque.valueobject.BillPartyVO;
import com.tcs.sgv.billproc.cheque.valueobject.ChequeVO;
import com.tcs.sgv.billproc.cheque.valueobject.RltBillCheque;
import com.tcs.sgv.billproc.cheque.valueobject.TrnChequeDtls;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * com.tcs.sgv.billproc.cheque.dao.TrnChequeDAOImpl
 * 
 * This is implementation class for getting cheque details
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History 
 * ===================== 
 * Vidhya M 23-Oct-2007 Made changes for  code formatting
 */

public class TrnChequeDAOImpl extends
		GenericDaoHibernateImpl<TrnChequeDtls, Long> implements TrnChequeDAO {
	static HashMap hMapChqNo = new HashMap();

	Log logger = LogFactory.getLog(getClass());

	ResourceBundle bundleConst = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	public TrnChequeDAOImpl(Class<TrnChequeDtls> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * Method to receive Chque Vo Frm Chque No
	 * 
	 * @param  String  : lStrChkNo
	 * @return vo
	 * @author bhavik
	 */
	public TrnChequeDtls getChqVoFrmChqNo(String lStrChkNo) {
		Session hibSession = getSession();

		Query query = hibSession
				.createQuery("from TrnChequeDtls where chequeNo like "
						+ lStrChkNo);
		List chequeDtls = query.list();
		TrnChequeDtls trnChequeDtls = null;

		if (chequeDtls != null && chequeDtls.size() > 0) {
			trnChequeDtls = (TrnChequeDtls) chequeDtls.get(0);
		}
		return trnChequeDtls;
	}
	
	/**
	 * Method to checking the chque and bill combination
	 * 
	 * @param  String [] : lObjBillArr[],
			   String [] : lObjChkArr[]
	 * @return boolean
	 * @author bhavik
	 */
	public boolean isCheckBillChkCombi(String lObjBillArr[],
			String lObjChkArr[]) {
		Session hibSession = getSession();
		int i, j, count = 0;
		Map mp = new HashMap();

		logger.info(" *********************8 bill length is "
				+ lObjBillArr.length);

		for (i = 0; i < lObjBillArr.length; i++) {
			Query query = hibSession
					.createQuery("select count(billNo)  from RltBillCheque where billNo ="
							+ lObjBillArr[i]
							+ " and chequeId in (select chequeId from TrnChequeDtls where status != '"
							+ bundleConst.getString("STATUS.CheqCancel") + "')");

			List billCount = query.list();
			Iterator it = billCount.iterator();
			Long countSize = null;

			while (it.hasNext()) {
				countSize = (Long) it.next();
			}

			Integer countInList = 0;
			for (j = 0; j < lObjBillArr.length; j++) {
				if (lObjBillArr[i].equals(lObjBillArr[j])) {
					countInList++;
				}
			}
			logger.info("countInList.toString() " + countInList.toString()
					+ " :::  " + countSize.toString());
			if (!(countInList.toString().equals(countSize.toString()))) {
				logger.info("Fail 1111 ");
				return false;
			}

		}

		for (i = 0; i < lObjBillArr.length; i++) {
			logger.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "
					+ lObjBillArr[i]);
		}

		for (i = 0; i < lObjChkArr.length; i++) {
			Query query = hibSession
					.createQuery("select count(chequeId)  from RltBillCheque where chequeId = "
							+ lObjChkArr[i]
							+ " and chequeId in (select chequeId from TrnChequeDtls where status != '"
							+ bundleConst.getString("STATUS.CheqCancel") + "')");

			List billCount = query.list();
			Iterator it = billCount.iterator();
			Long countSize = null;

			while (it.hasNext()) {
				countSize = (Long) it.next();
			}

			Integer countInList = 0;
			for (j = 0; j < lObjChkArr.length; j++) {
				logger.info("+++++++++" + lObjChkArr[i] + "   @@@@@@@@@  "
						+ lObjChkArr[j]);
				if (lObjChkArr[i].equals(lObjChkArr[j])) {
					countInList++;
				}
			}
			if (!(countInList.toString().equals(countSize.toString()))) {
				logger.info("countInList.toString() " + countInList.toString()
						+ " :::  " + countSize.toString());
				logger.info("Fail 222 ");
				return false;
			}

		}

		logger.info("sucess");
		return true;
	}
	/**
	 * Method to getting the chq list
	 * 
	 * @param  String : lStrStatus,
	 * 		   String : billList,
	 *		   String : lObjStrSrc[],
	 *		   Long   : lLngLangId
	 * @return List
	 * @author vidhya
	 */
	public List getAllCheques(String lStrStatus, String billList,
			String lObjStrSrc[], Long lLngLangId,String lStrPage,Long lLngCurUserId) {
		List lChequeList = null;
		List returnList = new ArrayList();
		List billChequeList = null;
		List trnBillRegister = null;
		List trnBillRegisterData = new ArrayList();
		List billCntrlList = new ArrayList();
		List tokenList = new ArrayList();
		Session hibSession = getSession();

		/* getting cheque numbers from bills incoming */
		logger.info("::: SearchTxt is  :::" + lObjStrSrc[0] + " ::: "
				+ lObjStrSrc[1]);

		String queryPrep = " FROM TrnChequeDtls  where status in ('"
				+ lStrStatus
				+ "','BILL_EXP','CHQ_RNM')  AND chequeId IN (select chequeId from RltBillCheque where billNo in ("
				+ billList + ")  ";
		if (lObjStrSrc[1] != null && lObjStrSrc[1].equals("tokSrc")) {
			queryPrep = queryPrep
					+ " and billNo in (select billNo from TrnBillRegister where tokenNum like '%"
					+ lObjStrSrc[0] + "%' )) ";
		} else if (lObjStrSrc[1] != null && lObjStrSrc[1].equals("billCntrlNo")) {
			queryPrep = queryPrep
					+ " and billNo in (select billNo from TrnBillRegister where billCntrlNo like '%"
					+ lObjStrSrc[0] + "%' )) ";
		} else {
			if (lObjStrSrc[0] != null || lObjStrSrc[1] != null) {

				if (lObjStrSrc[1].equals("fromDt")) {
					queryPrep = queryPrep + ") AND ";
					queryPrep = queryPrep + " fromDt  like to_date('"
							+ lObjStrSrc[0] + "','dd/mm/yyyy') ";
				} else if (!lObjStrSrc[1].equals("writerName")) {
					queryPrep = queryPrep + ") AND ";
					queryPrep = queryPrep + lObjStrSrc[1] + " like '%"
							+ lObjStrSrc[0] + "%' ";
				} else {
					queryPrep = queryPrep + " ) ";
				}
			} else {
				queryPrep = queryPrep + " ) ";
			}
		}
		if(lStrPage.equals("Written"))
		{
			queryPrep = queryPrep + " and createdUserId =" + lLngCurUserId ;
		}
		
		queryPrep = queryPrep + " and status != '"
				+ bundleConst.getString("STATUS.CheqCancel")
				+ "' order by createdUserId,createdDate desc";

		logger.info("Query is ::: " + queryPrep);

		Query query = hibSession.createQuery(queryPrep);

		// Query query = hibSession.createQuery(" FROM TrnChequeDtls where
		// status = '"+lStrStatus+"' AND chequeId IN (select chequeId from
		// RltBillCheque where billNo in ("+billList+") ) order by createdDate
		// desc");

		lChequeList = query.list();
		logger.info(" LIst is ---------- " + lChequeList);
		for (int i = 0; i < lChequeList.size(); i++) {
			ChequeVO chequeVO = new ChequeVO();
			TrnChequeDtls trnCheque = (TrnChequeDtls) lChequeList.get(i);
			//System.out.println("\n\n~~~~~~~~~~~~~~~~~~~"+ trnCheque.getGroupId());
			chequeVO.setTrnChequeDtls(trnCheque);

			chequeVO.setWriterName(getWriterName(trnCheque.getChequeId(),
					lLngLangId));

			query = hibSession
					.createQuery("  FROM RltBillCheque  where chequeId = "
							+ trnCheque.getChequeId());
			billChequeList = query.list();

			chequeVO.setBillChequeRlt(billChequeList);
			for (int k = 0; k < billChequeList.size(); k++) {
				RltBillCheque rltBillCheque = (RltBillCheque) billChequeList
						.get(k);
				Map returnMap = getBillNoTokenNo(rltBillCheque.getBillNo());

				query = hibSession
						.createQuery("  FROM TrnBillRegister  where billNo="
								+ rltBillCheque.getBillNo());

				trnBillRegister = query.list();

				TrnBillRegister billRegData = (TrnBillRegister) trnBillRegister
						.get(0);

				billCntrlList.add(returnMap.get("BillControlNo"));

				if (billRegData != null) {
					trnBillRegisterData.add(billRegData);
				}

				tokenList.add(returnMap.get("TokenNo"));
			}
			chequeVO.setTokenNo(tokenList);
			chequeVO.setBillCntrlNo(billCntrlList);
			chequeVO.setTrnBillRegisterData(trnBillRegisterData);

			if (lObjStrSrc[1] != null && lObjStrSrc[1].equals("writerName")) {
				if (chequeVO.getWriterName().contains(lObjStrSrc[0])) {
					returnList.add(chequeVO);
				}
			} else {
				returnList.add(chequeVO);
			}
			billCntrlList = new ArrayList();
			tokenList = new ArrayList();
			trnBillRegisterData = new ArrayList();
		}
		return returnList;
	}
	
	/**
	 * Method to getting billcontrol no and token no from billno
	 * 
	 * @param  long : billNo
	 * @return Map
	 * @author vidhya
	 */
	public Map getBillNoTokenNo(long billNo) {
		Map resultMap = new HashMap();
		Session hibSession = getSession();
		Query query = hibSession
				.createQuery("SELECT billCntrlNo, tokenNum FROM TrnBillRegister where billNo="
						+ billNo);
		List returnList = query.list();
		Iterator it = returnList.iterator();
		while (it.hasNext()) {
			Object[] tuple = (Object[]) it.next();
			resultMap.put("BillControlNo", tuple[0]);
			resultMap.put("TokenNo", tuple[1]);
		}
		return resultMap;
	}

	/**
	 * Method to getting Latest Cheque Number
	 * 
	 * @param  String  : lStrLocCode
	 * @return long
	 * @author vidhya
	 */
	public long getLatestChequeNum(String lStrLocCode) {
		java.math.BigInteger returnChequeNo = null;
		long chequeNo = 0;
		int linItemNo = 1;
		Session hibSession = getSession();
		Query query1 = hibSession
				.createSQLQuery(" SELECT CHEQUE_NO,M.CHEQUE_START_RANGE,M.CHEQUE_END_RANGE, M.LINE_ITEM_NO FROM TRN_CHEQUE_DTLS C, MST_TSRY_CHEQUES M WHERE C.CHEQUE_NO >= M.CHEQUE_START_RANGE AND C.CHEQUE_NO <= M.CHEQUE_END_RANGE AND M.LOCATION_CODE = '"
						+ lStrLocCode
						+ "'"
						+ " AND CHEQUE_NO = (SELECT MAX(CHEQUE_NO) FROM TRN_CHEQUE_DTLS) ");
		List rangeIt = query1.list();
		logger.info("List in fist query " + rangeIt);
		if (rangeIt != null && rangeIt.size() > 0) {
			for (int i = 0; i < rangeIt.size(); i++) {
				Object[] tuple = (Object[]) rangeIt.get(i);
				logger.info("1List .......... " + tuple[0]);
				returnChequeNo = new java.math.BigInteger(tuple[0].toString());
				BigInteger endVal = new java.math.BigInteger(tuple[2]
						.toString());

				if (returnChequeNo.longValue() == endVal.longValue()) {
					linItemNo = ((java.math.BigInteger) tuple[3]).intValue() + 1;
					Query query2 = hibSession
							.createSQLQuery(" SELECT M.CHEQUE_START_RANGE,M.CHEQUE_END_RANGE, M.LINE_ITEM_NO FROM MST_TSRY_CHEQUES M WHERE M.LOCATION_CODE = '"
									+ lStrLocCode
									+ "' AND LINE_ITEM_NO ="
									+ linItemNo);
					List list = query2.list();
					if (list != null && list.size() > 0) {
						Iterator itr = list.iterator();
						if (itr.hasNext()) {
							Object[] tup = (Object[]) itr.next();
							returnChequeNo = new java.math.BigInteger(tup[0]
									.toString());
						}
						chequeNo = returnChequeNo.longValue();
					}
				} else {
					chequeNo = returnChequeNo.longValue() + 1;
				}
				// chequeNo = returnChequeNo.longValue() + 1;
			}
		} else {
			query1 = hibSession
					.createSQLQuery(" SELECT M.CHEQUE_START_RANGE,M.CHEQUE_END_RANGE, M.LINE_ITEM_NO FROM MST_TSRY_CHEQUES M WHERE M.LOCATION_CODE = '"
							+ lStrLocCode + "' AND LINE_ITEM_NO=" + linItemNo);
			rangeIt = query1.list();
			logger.info("List in Second query " + rangeIt);
			if (rangeIt != null && rangeIt.size() > 0) {
				Iterator itr = query1.list().iterator();
				if (itr.hasNext()) {
					Object[] tup = (Object[]) itr.next();
					returnChequeNo = new java.math.BigInteger(tup[0].toString());
				}
				chequeNo = returnChequeNo.longValue();
			}
			logger.info(" max cheque Number is " + chequeNo);
		}

		return chequeNo;
	}
	/**
	 * Method to getting  Latest Cheque Number
	 * 
	 * @param  String  : lStrLocCode
	 * @return long
	 * @author vidhya
	 */
	public synchronized long getLatestChequeNum1(String lStrLocCode) // sychronize
																		// problem
																		// for
																		// chq
																		// number
	{

		java.math.BigInteger returnChequeNo = null;
		long chequeNo = 0;
		if (hMapChqNo.containsKey(lStrLocCode)) {
			chequeNo = Long.parseLong(hMapChqNo.get(lStrLocCode).toString());
			chequeNo++;
			hMapChqNo.put(lStrLocCode, chequeNo);
			return chequeNo;
		} else {
			int linItemNo = 1;
			Session hibSession = getSession();
			Query query1 = hibSession
					.createSQLQuery(" SELECT CHEQUE_NO,M.CHEQUE_START_RANGE,M.CHEQUE_END_RANGE, M.LINE_ITEM_NO FROM TRN_CHEQUE_DTLS C, MST_TSRY_CHEQUES M WHERE C.CHEQUE_NO >= M.CHEQUE_START_RANGE AND C.CHEQUE_NO <= M.CHEQUE_END_RANGE AND M.LOCATION_CODE = '"
							+ lStrLocCode
							+ "'"
							+ "  AND CHEQUE_NO = (SELECT MAX(CHEQUE_NO) FROM TRN_CHEQUE_DTLS) ");
			List rangeIt = query1.list();
			logger.info("List in fist query " + rangeIt);
			if (rangeIt != null && rangeIt.size() > 0) {
				for (int i = 0; i < rangeIt.size(); i++) {
					Object[] tuple = (Object[]) rangeIt.get(i);
					logger.info("1List .......... " + tuple[0]);
					returnChequeNo = new java.math.BigInteger(tuple[0]
							.toString());
					BigInteger endVal = new java.math.BigInteger(tuple[2]
							.toString());

					if (returnChequeNo.longValue() == endVal.longValue()) {
						linItemNo = ((java.math.BigInteger) tuple[3])
								.intValue() + 1;
						Query query2 = hibSession
								.createSQLQuery(" SELECT M.CHEQUE_START_RANGE,M.CHEQUE_END_RANGE, M.LINE_ITEM_NO FROM MST_TSRY_CHEQUES M WHERE M.LOCATION_CODE = '"
										+ lStrLocCode
										+ "' AND LINE_ITEM_NO = "
										+ linItemNo);
						List list = query2.list();
						if (list != null && list.size() > 0) {
							Iterator itr = list.iterator();
							if (itr.hasNext()) {
								Object[] tup = (Object[]) itr.next();
								returnChequeNo = new java.math.BigInteger(
										tup[0].toString());
							}
							chequeNo = returnChequeNo.longValue();
						}
					} else {
						chequeNo = returnChequeNo.longValue() + 1;
					}
					// chequeNo = returnChequeNo.longValue() + 1;
				}
			} else {
				query1 = hibSession
						.createSQLQuery("SELECT M.CHEQUE_START_RANGE,M.CHEQUE_END_RANGE, M.LINE_ITEM_NO FROM MST_TSRY_CHEQUES M WHERE M.LOCATION_CODE =  '"
								+ lStrLocCode
								+ "' AND LINE_ITEM_NO="
								+ linItemNo);
				rangeIt = query1.list();
				logger.info("List in Second query " + rangeIt);
				if (rangeIt != null && rangeIt.size() > 0) {
					Iterator itr = query1.list().iterator();
					if (itr.hasNext()) {
						Object[] tup = (Object[]) itr.next();
						returnChequeNo = new java.math.BigInteger(tup[0]
								.toString());
					}
					chequeNo = returnChequeNo.longValue();
				}
				logger.info(" max cheque Number is " + chequeNo);
			}

			hMapChqNo.put(lStrLocCode, chequeNo);
		}
		return chequeNo;
	}
	/**
	 * Method to getting  Maximum Advice Number
	 * 
	 * @param  String : locationCode
	 * @return long
	 * @author vidhya
	 */
	public long getMaxAdviceNo(String locationCode) {
		logger.info(" came in ........ dao for mvmnt");
		Session hibSession = getSession();
		Query sqlQuery = hibSession
				.createSQLQuery(" SELECT MAX(ADVICE_NO) FROM TRN_CHEQUE_DTLS WHERE LOCATION_CODE = '"
						+ locationCode + "'");
		Iterator iterator = sqlQuery.list().iterator();
		long maxAdviceId = 0;
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null)
				maxAdviceId = Long.parseLong(obj.toString());
		}
		maxAdviceId = maxAdviceId + 1;
		logger.info(" \n Returning ID " + maxAdviceId);
		return maxAdviceId;
	}
	/**
	 * Method to getting  Token Number For Cheque
	 * 
	 * @param  long : chequeId
	 * @return List
	 * @author vidhya
	 */
	public List getTokenNumberForCheq(long chequeId) {
		List tokenList = new ArrayList();
		String query = "SELECT B.TOKEN_NUM  FROM TRN_CHEQUE_DTLS C, RLT_BILL_CHEQUE R, TRN_BILL_REGISTER B "
				+ "  WHERE C.CHEQUE_ID = R.CHEQUE_ID AND R.BILL_NO = B.BILL_NO AND C.CHEQUE_ID = '"
				+ chequeId + "'";
		Session hibSession = getSession();
		Query sqlQuery = hibSession.createSQLQuery(query);
		Iterator iterator = sqlQuery.list().iterator();
		Long tokenNum;
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null) {
				tokenNum = Long.parseLong(obj.toString());
				tokenList.add(tokenNum);
			}
		}
		return tokenList;
	}
	/**
	 * Method to getting Token Number For Cheque
	 * 
	 * @param  long  : chequeId,
	 * 		   Long  : lLngLangId
	 * @return String
	 * @author vidhya
	 */
	public String getWriterName(long chequeId, Long lLngLangId) {
		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery(" SELECT EMP_FNAME , EMP_MNAME,EMP_LNAME FROM ORG_EMP_MST WHERE USER_ID IN (SELECT CREATED_USER_ID FROM TRN_CHEQUE_DTLS WHERE CHEQUE_ID = '"
						+ chequeId + "' ) AND LANG_ID= " + lLngLangId);
		Iterator it = query.list().iterator();
		String name = "";
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			name = name + obj[0].toString() + " ";
			name = name + obj[1].toString() + " ";
			name = name + obj[2].toString() + " ";
		}
		logger.info("++++++++++++" + name);
		return name;
	}
	/**
	 * Method to for checking the valid advice 
	 * 
	 * @param  String : chequeId
	 * 		   
	 * @return boolean
	 * @author vidhya
	 */
	public boolean isValidateAdvice(String chequeId) {
		Session hibSession = getSession();
		boolean returnFalg = false;
		Query query = hibSession
				.createSQLQuery(" SELECT * FROM TRN_CHEQUE_DTLS WHERE CHEQUE_ID ="
						+ chequeId + " AND ADVICE_NO IS NOT NULL ");

		List result = query.list();
		if (result != null && result.size() > 0) {
			returnFalg = false;
		} else {
			returnFalg = true;
		}
		return returnFalg;
	}
	/**
	 * Method to for getting group id
	 *    
	 * @return  int
	 * @author vidhya
	 */
	public synchronized int getGroupId() {
		Session hibSession = getSession();
		int groupId = 0;
		Query query = hibSession
				.createSQLQuery(" SELECT MAX(TO_NUMBER(GROUP_ID)) FROM TRN_CHEQUE_DTLS ");
		List result = query.list();
		if (result != null && result.size() > 0) {
			Iterator itr = result.iterator();
			while (itr.hasNext()) {
				Object obj = itr.next();
				if (obj != null) {
					groupId = Integer.parseInt(obj.toString());
					//System.out.println("======= Group ID ===== " + groupId);
				}
			}
		}
		++groupId;
		//System.out.println("-------- Returning group id ----- " + groupId);
		return groupId;
	}

	/**
	 * This method returns sum of all cheques depending upon types of cheques.
	 * 
	 * @param chqTypes
	 *            array of types of cheques
	 * @return BigDecimal
	 */
	public BigDecimal getUnpaidChequesSum(Date fromDate, Date toDate,
			List chqTypes, String nLocationId) {
		BigDecimal sum = new BigDecimal("0.0");
		try {
			Session hibSession = getSession();
			if (chqTypes != null && chqTypes.size() > 0) {
				StringBuffer sql = new StringBuffer(); // clearedDt is null and
				sql
						.append(" select sum(chequeAmt) from TrnChequeDtls chq where status='"
								+ bundleConst.getString("STATUS.CheqDispToDDO")
								+ "' and chequeType in (");
				for (int i = 0; i < chqTypes.size(); i++) {
					sql.append("'" + chqTypes.get(i) + "'");
					if ((i + 1) != chqTypes.size())
						sql.append(",");
				}
				sql.append(")");
				if (fromDate != null && toDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String fDate = sdf.format(fromDate);
					String tDate = sdf.format(toDate);
					sql.append(" and adviceDt between to_date('" + fDate
							+ "','yyyy-mm-dd') and to_date('" + tDate
							+ "','yyyy-mm-dd') and locationCode='"
							+ nLocationId + "'");
				} else {
					sql.append(" and adviceDt!=null and locationCode='"
							+ nLocationId + "'");
				}
				//System.out.println("xxxxxxxxx : " + sql.toString());
				Query query = hibSession.createQuery(sql.toString());
				Iterator it = query.list().iterator();

				if (it.hasNext())
					sum = (BigDecimal) it.next();
			}
		} catch (Exception ex) {
			logger.error("Error Occured #\n" + ex);
		}
		return sum;
	}

	/**
	 * This method returns sum of all cheques depending upon types of cheques
	 * and between dates.
	 * 
	 * @param fromDate
	 *            from date (cheque cleared date)
	 * @param toDate
	 *            to date (cheque cleared date)
	 * @return List
	 */
	public BigDecimal getPaidChequesSum(Date fromDate, Date toDate,
			List chqTypes, String nLocationId) {
		BigDecimal sum = new BigDecimal("0.0");
		try {
			Session hibSession = getSession();
			if (chqTypes != null && chqTypes.size() > 0) {
				StringBuffer sql = new StringBuffer();
				sql
						.append(" select sum(chequeAmt) from TrnChequeDtls chq where chequeType in (");
				for (int i = 0; i < chqTypes.size(); i++) {
					sql.append("'" + chqTypes.get(i).toString() + "'");
					if ((i + 1) != chqTypes.size())
						sql.append(",");
				}
				sql.append(")");
				if (fromDate != null && toDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String fDate = sdf.format(fromDate);
					String tDate = sdf.format(toDate);
					sql.append(" and clearedDt between to_date('" + fDate
							+ "','yyyy-mm-dd') and to_date('" + tDate
							+ "','yyyy-mm-dd')");
				} else {
					sql.append(" and clearedDt!=null");
				}
				sql.append(" and locationCode = '" + nLocationId + "'");

				//System.out.println("SQL Query : " + sql.toString());
				logger.debug("SQL Query : " + sql.toString());
				Query query = hibSession.createQuery(sql.toString());
				Iterator it = query.list().iterator();
				if (it.hasNext())
					sum = (BigDecimal) it.next();
			}
		} catch (Exception ex) {
			logger.error("Error Occured #\n" + ex);
		}
		return sum;
	}

	/**
	 * This method returns sum of all cheques depending upon types of cheques
	 * and between dates.
	 * 
	 * @param fromDate
	 *            from date (cheque cleared date)
	 * @param toDate
	 *            to date (cheque cleared date)
	 * @return List
	 */
	public BigDecimal getPaidLcChequesSum(Date fromDate, Date toDate,
			String sLocationId) {
		BigDecimal sum = new BigDecimal("0.0");
		try {
			Session hibSession = getSession();

			StringBuffer sql = new StringBuffer();
			sql.append(" select sum(amount) from RptPaymentDtls chq where ");

			if (fromDate != null && toDate != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fDate = sdf.format(fromDate);
				String tDate = sdf.format(toDate);
				sql.append("  chqClrDt between to_date('" + fDate
						+ "','yyyy-mm-dd') and to_date('" + tDate
						+ "','yyyy-mm-dd')");
			} else {
				sql.append(" and chqClrDt !=null and chqTypeCode = '"
						+ DBConstants.CHEQ_TYPE_LC + "'");
			}
			sql
					.append(" and active = 'Y' and tsryCode = '" + sLocationId
							+ "'");

			//System.out.println("SQL Query : " + sql.toString());
			logger.debug("SQL Query : " + sql.toString());
			Query query = hibSession.createQuery(sql.toString());
			Iterator it = query.list().iterator();
			if (it.hasNext())
				sum = (BigDecimal) it.next();

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error Occured #\n" + ex);
		}
		return sum;
	}
	
	/**
	 * Method to checking chque bill combination 
	 * 
	 * @param  String[] : cheques,
			   String[] : groups
	 * @return boolean
	 * @author bhavik
	 */
	public boolean isCheckBillChequeCombination(String[] cheques,
			String[] groups) {
		Session session = getSession();
		Query query = null;
		if (groups != null && groups.length > 0 && cheques != null
				&& cheques.length > 0) {
			for (int cnt = 0; cnt < groups.length; cnt++) {
				query = session
						.createSQLQuery(" SELECT cheque_id FROM TRN_CHEQUE_DTLS WHERE GROUP_ID= '"
								+ groups[cnt] + "' and status != 'CANCELLED' ");
				List result = query.list();
				if (result != null && result.size() > 0) {
					for (int grpCnt = 0; grpCnt < result.size(); grpCnt++) {
						long chequeId = Long.parseLong(result.get(grpCnt)
								.toString());
						System.out
								.println("\n---------- Cheque Id obtained is from query is ----- "
										+ chequeId);
						int count = 0;
						for (int j = 0; j < cheques.length; j++) {
							System.out
									.println("\n---------- cheque is array ----- "
											+ cheques[j]);
							if (chequeId == Long.parseLong(cheques[j])) {
								count++;
							}
						}
						if (count > 0) {
							count = 0;
						} else {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Method to checking chque bill combination 
	 * 
	 * @param  	Long : startNo, 
	 * 			Long : endNo
	 * @return String[]
	 * @author bhavik
	 */
	public String[] getChequeStatus(Long startNo, Long endNo) {
		Session hibSession = getSession();
		String strQuery = "SELECT CHEQUE_NO FROM TRN_CHEQUE_DTLS  WHERE CHEQUE_NO >= "
				+ startNo + " AND CHEQUE_NO <=" + endNo;
		Query query = hibSession.createSQLQuery(strQuery);
		List chequeDtls = query.list();
		String[] chequeNos = null;
		if (chequeDtls.size() > 0) {
			chequeNos = new String[chequeDtls.size()];
			Iterator it = chequeDtls.iterator();
			int count = 0;
			while (it.hasNext()) {
				try {
					String strChequeNo = it.next().toString();
					chequeNos[count] = strChequeNo;
					count++;
				} catch (Exception ex) {

				}
			}
		}
		return chequeNos;
	}
	/**
	 * Method to geting Party For Bill
	 * 
	 * @param  	Long billNo, 
	 * 			Long langId
	 * @return List
	 * @author vidhya
	 */
	public List getPartyForBill(Long billNo, Long langId) {
		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery(" SELECT P.PARTY_ID,P.PARTY_NAME p, P.PARTY_ADDR,P.ACCNT_NUM,BP.PARTY_AMT, BP.BILL_NO, P.PARTY_CODE, BP.PARTY_NAME p1,BP.PARTY_ADDR p1, BP.ACCNT_NO p3 FROM RLT_BILL_PARTY BP left outer join  MST_PARTY P on (BP.PARTY_CODE = P.PARTY_CODE and P.LANG_ID = "
						+ langId + ")  WHERE   BP.BILL_NO = " + billNo);
		List result = query.list();
		List partylist = null;
		if (result != null && result.size() > 0) {
			Iterator it = result.iterator();
			partylist = new ArrayList();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				BillPartyVO mstParty = new BillPartyVO();
				if (obj[0] != null) {
					mstParty.setPartyId(Long.parseLong(obj[0].toString()));
				}
				if (obj[1] != null && !obj[1].toString().equals("")) {
					mstParty.setPartyName(obj[1].toString());
				} else {
					mstParty.setPartyName(obj[7].toString());
				}
				if (obj[2] != null && !obj[2].toString().equals("")) {
					mstParty.setPartyAddr(obj[2].toString());
				} else {
					mstParty.setPartyAddr(obj[8].toString());
				}
				if (obj[3] != null && !obj[3].toString().equals("")) {
					mstParty.setAccntNum(obj[3].toString());
				} else {
					mstParty.setAccntNum(obj[9].toString());
				}
				if (obj[4] != null) {
					mstParty.setPartyAmt(obj[4].toString());
				}
				if (obj[5] != null) {
					mstParty.setBillNo(Long.parseLong(obj[5].toString()));
				}
				if (obj[6] != null) {
					mstParty.setPartyCode(obj[6].toString());
				}
				partylist.add(mstParty);
			}
		}
		return partylist;
	}

}
