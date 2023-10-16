package com.tcs.sgv.billproc.common.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.common.valueobject.TrnShowBill;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDao;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.DDOInfoDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.DDOInfoServiceImpl;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;

/**
 * ShowBillDAOImpl 
 * This class is use to set all the necessary fields which are
 * needed to show the bill to user in editable mode. 
 * It sets the details of bill, budget Head structure, challan, 
 * remarks, ddo, exempted and AttachmentID. 
 * It also contains the method for getting Attachment ID and
 * ojections selected by user.
 * 
 * Date of Creation : 13th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 * Hiral Shah   23-Oct-2007   For making changes for code formating
 * Hiral Shah	26-Oct-2007	  Changes for bill type code
 */
public class ShowBillDAOImpl extends
		GenericDaoHibernateImpl<TrnShowBill, Integer> implements ShowBillDAO {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	public ShowBillDAOImpl(Class<TrnShowBill> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	/**
	 * Method to get all the details of particular bill as passed in parameter.
	 * 
	 * @param Map :
	 *            inputMap
	 * 
	 * @return TrnShowBill
	 */
	public TrnShowBill getBilldetails(Map inputMap) {
		String lStrBillNo = inputMap.get("billNo") != null ? inputMap.get(
				"billNo").toString() : "";
		String tcBill = "";

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		logger.info("Value of Auditor Net Amount in Words : "
				+ request.getParameter("txtAdtAmtWords"));

		PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		DDOInfoServiceImpl lObjDdoInfo = new DDOInfoServiceImpl();
		TrnShowBill lObjTrnShowBill = new TrnShowBill();
		TrnBillRegister lObjBillRegister = lObjPhyBillDAOImpl.read(Long
				.parseLong(lStrBillNo));
		// Add by bhavik
		logger.info(" Billlllllllll no is ::::::::::::::::::" + lStrBillNo);
		lObjTrnShowBill.setObjCount(lObjPhyBillDAOImpl.getObjectionsForUser("",
				Long.parseLong(lStrBillNo)));
		// bhavik ends
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		Long lLngDbId = SessionHelper.getDbId(inputMap);
		Long lLngUserId = SessionHelper.getUserId(request);
		String lLngLocCode = SessionHelper.getLocationCode(inputMap);
		String lLngBillCtgry = null;

		logger.info("Value of Ddo Code from TrnBillRegister : "
				+ lObjBillRegister.getDdoCode());
		List lLstDdoInfo = lObjDdoInfo.getDDOInfo(
				lObjBillRegister.getDdoCode(), lLngLangId, lLngDbId, serv);
		OrgDdoMst lObjOrgDdoMst = (OrgDdoMst) lLstDdoInfo.get(0);
		String lStrCardexNo = null;//lObjOrgDdoMst.getCardexNo();
		setDDODetails(lStrCardexNo, lLngLangId, lLngDbId, serv,
				lObjTrnShowBill, lLngLocCode);

		setBillDetails(lObjTrnShowBill, lObjBillRegister, serv, lLngLangId);

		if (lObjBillRegister.getGoNgo() != null
				&& (!(lObjBillRegister.getGoNgo().toString().equals("")))) {
			logger.info("Value of GO/NGO in Show BILL DAO :: "
					+ lObjBillRegister.getGoNgo());
			lObjTrnShowBill.setGoNgo(lObjBillRegister.getGoNgo());
		}

		lLngBillCtgry = lObjBillRegister.getTcBill();
		lObjTrnShowBill.setTcBill(lLngBillCtgry);
		List lLstObj = getSelectedObj(lStrBillNo, lLngUserId);
		String lStrObj = "";
		if (lLstObj != null) {
			for (int i = 0; i < lLstObj.size(); i++) {
				lStrObj = lStrObj + "~" + lLstObj.get(i);
			}
			logger.info("Value of ~~~~~~ joined string : " + lStrObj);
			lObjTrnShowBill.setObjections(lStrObj);
		}
		tcBill = lObjCmnSrvcDAOImpl.getLookUpText((lLngBillCtgry.toString()),
				lLngLangId);
		if (tcBill.equalsIgnoreCase("TC")) {
			setReceiptDetails(lStrBillNo, lObjTrnShowBill, serv,
					lObjBillRegister, inputMap);
		}

		setBudHeadDetails(lStrBillNo, lObjTrnShowBill, inputMap);

		setExempted(lObjTrnShowBill, lObjBillRegister);

		setAttachId(lObjTrnShowBill, lObjBillRegister);

		logger.info("Before if for BudTypeDesc : ["
				+ lObjTrnShowBill.getBudType() + "]");
		if (lObjTrnShowBill.getBudType() != null
				&& lObjTrnShowBill.getBudType().length() > 0) {
			logger.info("Inside if");
			String lookupId = lObjCmnSrvcDAOImpl.getLookupIdFromName(
					lObjTrnShowBill.getBudType(), lLngLangId);
			lObjTrnShowBill.setBudTypeDesc(lObjCmnSrvcDAOImpl.getLookUpDesc(
					Integer.parseInt(lookupId), lLngLangId));
		}
		logger.info("^^^^^^^^^Department : " + lObjTrnShowBill.getDepartment());
		logger.info("^^^^^^^^^^ Dept Id : " + lObjTrnShowBill.getDeptCode());
		logger.info("^^^^^^^^ DDO CODE : " + lObjTrnShowBill.getDdoCode());
		logger.info("^^^^^^^^^^Bill DAte :  " + lObjTrnShowBill.getBillDate());
		logger.info("^^^^^^^^^Gross Amount : "
				+ lObjTrnShowBill.getBillGrossAmount());
		logger.info("^^^^^^^^^Net Amount : "
				+ lObjTrnShowBill.getBillNetAmount());
		logger.info("^^^^^^^^^GO/NGO  : " + lObjTrnShowBill.getGoNgo());
		logger.info("^^^^^^^^^Bill Category  : " + lObjTrnShowBill.getTcBill());
		logger.info("^^^^^^^^^Demand No : " + lObjTrnShowBill.getDmndNo());
		logger.info("^^^^^^^^^Major Head : " + lObjTrnShowBill.getBudmjrHd());
		logger.info("^^^^^^^^^Sub Major Head : "
				+ lObjTrnShowBill.getBudSubMjrHd());
		logger.info("^^^^^^^^^Minor Head : " + lObjTrnShowBill.getBudMinHd());
		logger.info("^^^^^^^^^Sub Head : " + lObjTrnShowBill.getBudSubHd());
		logger.info("^^^^^^^^^Detail Head : "
				+ lObjTrnShowBill.getBudDetailHd());
		logger.info("^^^^^^^^^Budget Type : " + lObjTrnShowBill.getBudType());
		logger
				.info("^^^^^^^^^Scheme Code : "
						+ lObjTrnShowBill.getSchemeCode());
		logger.info("^^^^^^^^^DDO Name : " + lObjTrnShowBill.getDdoName());
		logger.info(" ^^^^^^^^^DDO User ID : " + lObjTrnShowBill.getDdoNo());
		logger
				.info("^^^^^^^^^Cardex Number : "
						+ lObjTrnShowBill.getCardexNo());
		logger.info("^^^^^^^^^Last Remarks : "
				+ lObjTrnShowBill.getLastRemark());
		logger.info("^^^^^^^^^List for Challan details : "
				+ lObjTrnShowBill.getListReceipt());
		logger.info("^^^^^^^^^ Previous Bill no :  "
				+ lObjTrnShowBill.getPrevBillNo());
		logger.info("^^^^^^^^^ Grant Amount " + lObjTrnShowBill.getGrantAmt());
		return lObjTrnShowBill;
	}

	/**
	 * Method to set DDO Details for particular bill.
	 * 
	 * @param String :
	 *            lStrCardexNo
	 * @param Long :
	 *            lLngLangId
	 * @param Long :
	 *            lLngDbId
	 * @param ServiceLocator :
	 *            serv
	 * @param TrnShowBill :
	 *            lObjTrnShowBill
	 * 
	 * @return void
	 */

	public void setDDODetails(String lStrCardexNo, Long lLngLangId,
			Long lLngDbId, ServiceLocator serv, TrnShowBill lObjTrnShowBill,
			String lLngLocCode) {
		DDOInfoDAOImpl lObjDdoInfoDao = new DDOInfoDAOImpl(serv
				.getSessionFactory());
		List lLstDdoInfo = lObjDdoInfoDao.getDDOInfoByCardex(lStrCardexNo,
				lLngLocCode, lLngLangId, lLngDbId);
		OrgDdoMst lObjDdoOrgMst = (OrgDdoMst) lLstDdoInfo.get(0);
		lObjTrnShowBill.setDdoName(lObjDdoOrgMst.getDdoName());
		//lObjTrnShowBill.setDdoNo(lObjDdoOrgMst.getDdoNo());
		lObjTrnShowBill.setCardexNo(Long.parseLong(lStrCardexNo));
		lObjTrnShowBill.setDdoCode(lObjDdoOrgMst.getDdoCode());
	}

	/**
	 * Method to set Receipt Details for particular TC bill.
	 * 
	 * @param String :
	 *            lStrBillNo
	 * @param TrnShowBill :
	 *            lObjTrnShowBill
	 * @param ServiceLocator :
	 *            serv
	 * @param TrnBillRegister :
	 *            lObjBillRegister
	 * 
	 * @return void
	 */
	public void setReceiptDetails(String lStrBillNo,
			TrnShowBill lObjTrnShowBill, ServiceLocator serv,
			TrnBillRegister lObjBillRegister, Map inputMap) {
		BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		List lListRcptDtls = lObjCmnSrvcDAOImpl.getTrnRcptFromBill(Long
				.parseLong(lStrBillNo), inputMap);
		String[] lStrChallanMjrHd = new String[lListRcptDtls.size()];
		Date[] lDtChallanDt = new Date[lListRcptDtls.size()];
		String[] lStrChallanNo = new String[lListRcptDtls.size()];
		BigDecimal[] lBDChallanAmt = new BigDecimal[lListRcptDtls.size()];

		for (int i = 0; i < lListRcptDtls.size(); i++) {
			TrnReceiptDetails lObjTrnRcptDtls = (TrnReceiptDetails) lListRcptDtls
					.get(i);
			lStrChallanMjrHd[i] = lObjTrnRcptDtls.getMajorHead();
			lDtChallanDt[i] = lObjTrnRcptDtls.getReceiptDate();
			lStrChallanNo[i] = lObjTrnRcptDtls.getReceiptNo();
			lBDChallanAmt[i] = lObjTrnRcptDtls.getAmount();
			logger.info("Value of Challan Major head " + (i) + " : "
					+ lStrChallanMjrHd[i]);
			logger.info("Value of Challan Date " + (i) + " : "
					+ lDtChallanDt[i]);
			logger.info("Value of Challan Number " + (i) + " : "
					+ lStrChallanNo[i]);
			logger.info("Value of Challan Amount " + (i) + " : "
					+ lBDChallanAmt[i]);
		}
		lObjTrnShowBill.setListReceipt(lListRcptDtls);
	}

	/**
	 * Method to set Budget Head Details for particular bill.
	 * 
	 * @param String :
	 *            lStrBillNo
	 * @param TrnShowBill :
	 *            lObjTrnShowBill
	 * @param ServiceLocator :
	 *            serv
	 * 
	 * @return void
	 */
	public void setBudHeadDetails(String lStrBillNo,
			TrnShowBill lObjTrnShowBill, Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		BptmCommonServicesDAOImpl lObjCmnSrvcDao = new BptmCommonServicesDAOImpl(
				TrnBillBudheadDtls.class, serv.getSessionFactory());
		TrnBillBudheadDtls lObjBudHeadVO = null;
		lObjBudHeadVO = lObjCmnSrvcDao.getBudHdVoFromBillNo(Long
				.parseLong(lStrBillNo));

		if (lObjBudHeadVO != null) {
			lObjTrnShowBill.setDmndNo(lObjBudHeadVO.getDmndNo());
			lObjTrnShowBill.setBudSubMjrHd(lObjBudHeadVO.getBudSubmjrHd());
			lObjTrnShowBill.setBudMinHd(lObjBudHeadVO.getBudMinHd());
			lObjTrnShowBill.setBudSubHd(lObjBudHeadVO.getBudSubHd());
			lObjTrnShowBill.setBudDetailHd(lObjBudHeadVO.getBudDtlHd());
			if (lObjBudHeadVO.getBudType() != null) {
				lObjTrnShowBill.setBudType(lObjBudHeadVO.getBudType());
			}
			if (lObjBudHeadVO.getSchemeNo() != null) {
				lObjTrnShowBill.setSchemeCode(lObjBudHeadVO.getSchemeNo());
			}
		}
	}

	/**
	 * Method to set Last Remark Details for particular bill of Current User.
	 * 
	 * @param String :
	 *            lStrBillNo
	 * @param TrnShowBill :
	 *            lObjTrnShowBill
	 * @param Long :
	 *            lLngUserId
	 * 
	 * @return void
	 */
	public String setLastRemarks(String lStrBillNo, Long lLngUserId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		String lStrLastRmrks = "";
		try {
			lSBQuery.append("SELECT tbr.remarks FROM TrnBillRemarks tbr ");
			lSBQuery.append(" WHERE userId = :userId ");
			lSBQuery.append(" AND billNo = :billNo ");
			lSBQuery.append(" AND rmrksFlag = 'MR' ");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("userId", lLngUserId);
			lQuery.setParameter("billNo", Long.parseLong(lStrBillNo));

			logger.info("Query for setLastRemarks : " + lQuery.toString());
			logger.info("And Parameter is " + lLngUserId + " " + lStrBillNo);
			List lLastRemarks = lQuery.list();

			logger.info("Value of lLastRemarks : " + lLastRemarks);
			logger.info("Size of lLastRemarks : " + lLastRemarks.size());
			if (lLastRemarks != null && lLastRemarks.size() > 0) {
				lStrLastRmrks = (String) lLastRemarks.get(0);
			}
		} catch (Exception e) {
			logger.error("Exception occured in setLastRemarks : " + e, e);
		}
		logger.info("Returning value from setLastRemarks,lLastrmrks : "
				+ lStrLastRmrks);
		return lStrLastRmrks;
	}

	/**
	 * Method to set Exempted and Bill Code details of particular Bill
	 * 
	 * @param TrnShowBill :
	 *            lObjTrnShowBill
	 * @param TrnBillRegister :
	 *            lObjBillRegister
	 * 
	 * @return void
	 */

	public void setExempted(TrnShowBill lObjTrnShowBill,
			TrnBillRegister lObjBillRegister) {
		String lStrExempted = lObjBillRegister.getExempted().toString();
		lObjTrnShowBill.setExempted(lStrExempted);
		logger.info("Value of Exempted :: " + lStrExempted);
		if (lStrExempted.equalsIgnoreCase(gObjRsrcBndle.getString("CMN.Yes"))) {
			String billCode = lObjBillRegister.getBillCode().toString();
			logger.info("Value of Bill Code in SHWO BILL DAO " + billCode);
			lObjTrnShowBill.setBillCode(billCode);
		}
	}

	/**
	 * Method to get Attachment ID for particular bill.
	 * 
	 * @param TrnShowBill :
	 *            lObjTrnShowBill
	 * @param TrnBillRegister :
	 *            lObjBillRegister
	 * 
	 * @return void
	 */
	public void setAttachId(TrnShowBill lObjTrnShowBill,
			TrnBillRegister lObjBillRegister) {
		Long lLngAttchId = lObjBillRegister.getAttachmentId() != null ? lObjBillRegister
				.getAttachmentId()
				: null;

		if (lLngAttchId != null) {
			lObjTrnShowBill.setAttachId(lLngAttchId);
		}
	}

	/**
	 * Method to set Basic Details for particular bill.
	 * 
	 * @param TrnShowBill :
	 *            lObjTrnShowBill
	 * @param TrnBillRegister :
	 *            lObjBillRegister
	 * 
	 * @return void
	 */
	public void setBillDetails(TrnShowBill lObjTrnShowBill,
			TrnBillRegister lObjBillRegister, ServiceLocator serv, Long lLangId) {
		BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());

		lObjTrnShowBill.setBillNo(lObjBillRegister.getBillNo());
		lObjTrnShowBill.setBillCntrlNo(lObjBillRegister.getBillCntrlNo());
		if (lObjBillRegister.getRefNum() != null)
			lObjTrnShowBill.setReferenceNo(String.valueOf(lObjBillRegister
					.getRefNum()));
		lObjTrnShowBill.setTokenNum(lObjBillRegister.getTokenNum());
		lObjTrnShowBill.setBillDate(lObjBillRegister.getBillDate());
		
		String lStrBillType = null;
		
/*		Changes for Bill Type Code  	 */		
		Long lLngSubId = lObjBillRegister.getSubjectId();
		if(lLngSubId!=null)
		{
			logger.info("IIIIIIIIIIIn shoBill, Inside if for Bill type, value of BillType : " +lLngSubId);
			lStrBillType = lObjCmnSrvcDAOImpl.getBillTypeFromSubId(lObjBillRegister.getSubjectId());				
			logger.info("Inside if for Bill type, value of Subject Id : " +lStrBillType);
			lObjTrnShowBill.setBillType(lObjBillRegister.getSubjectId());
			lObjTrnShowBill.setBillTypeCode(lStrBillType);
		}		
/*	Ends : Changes for Bill Type Code   */
		
		String GrantAmt = lObjBillRegister.getGrantAmount() != null ? lObjBillRegister
				.getGrantAmount().toString()
				: null;
		lObjTrnShowBill.setGrantAmt(GrantAmt);

		lObjTrnShowBill.setBillGrossAmount(lObjBillRegister
				.getBillGrossAmount());
		lObjTrnShowBill.setBillNetAmount(lObjBillRegister.getBillNetAmount());
		lObjTrnShowBill.setBudmjrHd(lObjBillRegister.getBudmjrHd());

		if (lObjBillRegister.getAuditorNetAmount() != null) {
			lObjTrnShowBill.setAuditorNetAmount(lObjBillRegister
					.getAuditorNetAmount());
		}
		if (lObjBillRegister.getAudPostId() != null)
			lObjTrnShowBill.setAuditorPostId(lObjBillRegister.getAudPostId()
					.toString());
		if (lObjBillRegister.getDdoDeptId() != null)
			lObjTrnShowBill.setDeptCode(lObjBillRegister.getDdoDeptId());
		else
			lObjTrnShowBill.setDeptCode("-1");

		if (lObjBillRegister.getPrevBillNo() != null) {
			String prevBillId = lObjCmnSrvcDAOImpl
					.getBillCntrlNoFromBillNo(lObjBillRegister.getPrevBillNo()
							.toString());
			logger.info("Value of previous Bill No from show Bill :: "
					+ prevBillId);
			lObjTrnShowBill.setPrevBillNo(prevBillId);
		}
		CmnLocationMstDao lObjCmnLocMstDAO = new CmnLocationMstDaoImpl(
				CmnLocationMst.class, serv.getSessionFactory());
		CmnLocationMst lObjCmnLocMst = null;

		if (lObjBillRegister.getDdoDeptId() != null
				&& lObjBillRegister.getDdoDeptId().toString().length() > 0) {
			lObjCmnLocMst = lObjCmnLocMstDAO
					.getLocationVOByLocationCodeAndLangId(lObjBillRegister
							.getDdoDeptId(), lLangId);
			lObjTrnShowBill.setDepartment(lObjCmnLocMst.getLocName());
		}

		lObjTrnShowBill.setStatus(lObjBillRegister.getCurrBillStatus());
	}

	/**
	 * Method to get Attachment ID for particular bill.
	 * 
	 * @param Long :
	 *            billNo
	 * 
	 * @return Long
	 */
	public Long getAttachId(Long billNo) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		Long attachId = null;
		try {
			lSBQuery
					.append("SELECT tbr.attachmentId FROM TrnBillRegister tbr ");
			lSBQuery.append(" WHERE billNo = :billNo ");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());

			lQuery.setParameter("billNo", billNo);

			logger.info("Query for getAttachId : " + lQuery.toString());
			logger.info("And Parameter is " + billNo);
			lListQuery = lQuery.list();

			if (lListQuery != null)
				attachId = (Long) lListQuery.get(0);
		} catch (Exception e) {
			logger.error("Exception occured in getAttachId : " + e, e);
		}
		logger.info("Returning value from getAttachId,attachId : " + attachId);
		return attachId;
	}

	/**
	 * Method to get List of all selected Objection by User.
	 * 
	 * @param String :
	 *            billNo
	 * @param Long :
	 *            userId
	 * 
	 * @return Long
	 */
	public List getSelectedObj(String billNo, Long userId) {
		StringBuffer lSBQuery = new StringBuffer();
		Session hibSession = getSession();
		List lListQuery = null;
		try {
			lSBQuery
					.append("SELECT rbo.objectionCode FROM RltBillObjection rbo ");
			lSBQuery.append(" WHERE rbo.userId = :userId ");
			lSBQuery.append(" AND rbo.billNo = :billNo ");

			Query lQuery = hibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("userId", userId);
			lQuery.setParameter("billNo", Long.parseLong(billNo));

			logger.info("Query in getSelectedObj : " + lQuery.toString());
			logger.info("and Parameters are : " + userId + "  " + billNo);
			lListQuery = lQuery.list();
		} catch (Exception e) {
			logger.error("Exception occured in getSelectedObj : " + e, e);
		}
		return lListQuery;
	}
}