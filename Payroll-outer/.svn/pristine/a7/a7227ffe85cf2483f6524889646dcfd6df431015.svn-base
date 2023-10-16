/**
 * 
 */
package com.tcs.sgv.billproc.common.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * RemarksDAOImpl
 * This class updates the remarks and if remark is not there then that remark gets inserted.
 * It also contains the method for getting nextLineItemNo which is use to validate remarks
 * 
 * Date of Creation : 12th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 * Hiral    23-Oct-2007   For making changes for code formating
 *
 */
public class RemarksDAOImpl extends
		GenericDaoHibernateImpl<TrnBillRemarks, Long> implements RemarksDAO {
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle bundleConst = ResourceBundle
	.getBundle("resources/billproc/BillprocConstants");

	public RemarksDAOImpl(Class<TrnBillRemarks> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	/**
	 * Method to get next line item number from 'trn_bill_remarks' table.
	 * 
	 * @param String :
	 *            billNo
	 * @param String =
	 *            userId
	 * 
	 * @return int
	 */

	public int getNextLineItem(String lStrBillNo, String lStrUserId) {
		int intLineItemNo = 0;
		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery("SELECT BILL_NO,MAX(LINE_ITEM_NO)  FROM TRN_BILL_REMARKS  "
						+ " WHERE USER_ID = "
						+ lStrUserId
						+ " AND BILL_NO = "
						+ lStrBillNo + " GROUP BY BILL_NO");
		List dataList = query.list();
		Iterator it = dataList.iterator();

		if (dataList != null) {
			if (dataList.size() <= 0) {
				intLineItemNo = 1;
				return intLineItemNo;
			} else {
				Object[] lObjTuple = (Object[]) it.next();
				intLineItemNo = Integer.parseInt(lObjTuple[1].toString());
				intLineItemNo++;
				return intLineItemNo;
			}
		}
		return intLineItemNo;
	}

	/**
	 * Method to update Remarks
	 * 
	 * @param TrnBillRemarks :
	 *            lObjRemarksVO
	 * 
	 * @return int
	 */

	public int updateRemarks(Map inputMap) {
		TrnBillRemarks lObjRemarksVO = (TrnBillRemarks) inputMap
				.get("TrnBillRemarksVO");
		Long lngUserId = lObjRemarksVO.getUserId();
		Long lngBillNo = lObjRemarksVO.getBillNo();

		long lLngMaxLineItem = 0;

		Long lLngRemarksId = null;
		int intUpdateStatus = 0;
		String lStrRemarks = lObjRemarksVO.getRemarks();

		Session hibSession = getSession();
		Query query = hibSession
				.createSQLQuery("SELECT MAX(LINE_ITEM_NO) FROM TRN_BILL_REMARKS WHERE USER_ID = "
						+ lngUserId
						+ " AND BILL_NO = "
						+ lngBillNo
						+ " AND RMRKS_FLAG='"+bundleConst.getString("CMN.MyRemarks")+"'");
		List lLstQuery = query.list();

		if (lLstQuery.size() > 0) {
			Iterator it = lLstQuery.iterator();
			if (it.hasNext()) {
				Object lObjTuple = (Object) it.next();
				if (lObjTuple != null)
					lLngMaxLineItem = Long.parseLong(lObjTuple.toString());
				else
					lLngMaxLineItem = 0;
			}
			if (lLngMaxLineItem != 0) {
				{
					query = hibSession
							.createSQLQuery("UPDATE TRN_BILL_REMARKS SET REMARKS = '"
									+ lStrRemarks
									+ "' WHERE RMRKS_FLAG='"+bundleConst.getString("CMN.MyRemarks")+"' AND USER_ID="
									+ lngUserId + " AND BILL_NO = " + lngBillNo);
					intUpdateStatus = query.executeUpdate();
				}
			} else {
				lLngRemarksId = BptmCommonServiceImpl.getNextSeqNum(
						"trn_bill_remarks", inputMap);
				Integer lIntLineItemNo = getNextLineItem(lngBillNo.toString(),
						lngUserId.toString());

				lObjRemarksVO.setRmrksId(lLngRemarksId);
				lObjRemarksVO.setLineItemNo(Long.parseLong(lIntLineItemNo
						.toString()));
				lObjRemarksVO.setRmrksFlag(bundleConst.getString("CMN.MyRemarks"));

				//create(lObjRemarksVO);
			}
		}
		query = hibSession
				.createSQLQuery("UPDATE TRN_BILL_REMARKS SET RMRKS_FLAG='"+bundleConst.getString("CMN.PrevRemarks")+"' WHERE RMRKS_FLAG='"+bundleConst.getString("CMN.MyRemarks")+"' "
						+ " AND USER_ID<>"
						+ lngUserId
						+ " AND BILL_NO = "
						+ lngBillNo);
		query.executeUpdate();
		return intUpdateStatus;
	}
}
