package com.tcs.sgv.common.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.BillMovementDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

/**
 * BillMovementServiceImpl This is used for inserting entries in Trn_bill_mvmnt
 * table while workflow is invoked Date of Creation : 10th July 2007 Author :
 * Vidhya Mashru
 * 
 * Revision History ===================== Vidhya M 23-Oct-2007 For making
 * changes for code formating
 * 24-OCT-2007 Change in insertBillMovementWF for handle Exception By Bhavesh
 */
public class BillMovementServiceImpl extends ServiceImpl implements
		BillMovementService {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle bundleConst = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");
	/**
	 *	This is used to insert entry in trn_bill_mvmnt 
	 * @param Map: inputMap
	 * @return ResultObject
	 */
	public ResultObject insertBillMovement(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			Enumeration enu = request.getAttributeNames();

			while (enu.hasMoreElements()) {
				logger.info("Inside Bill MovementSErvice,REquest name : "
						+ enu.nextElement().toString());
			}
			Enumeration enudm = request.getSession(false).getAttributeNames();
			while (enudm.hasMoreElements()) {
				logger.info("\n-- " + enudm.nextElement().toString());
			}
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");

			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(
					FrmServiceMst.class, serv.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl
					.readService("GENERATE_ID_SRVC");
			Map m = new HashMap();
			String s1 = "trn_bill_mvmnt";
			m.put("tablename", s1);
			m.put("serviceLocator", serv);
			ResultObject resultObj = serv.executeService(servDetails, m);
			int receivedcode = resultObj.getResultCode();
			if (receivedcode == -1) {
				logger.info("Error in exection of previoucservice");
				return resultObj;
			}
			Map resultMap = (Map) resultObj.getResultValue();
			String val = resultMap.get("newID").toString();
			Integer lLngCaseID = Integer.valueOf(val);
			logger
					.info(" \n\n --------- In BillMovementSErvice, New Id is --- "
							+ lLngCaseID + "\n\n ");
			BillMovementDAOImpl mvmntDAO = new BillMovementDAOImpl(
					TrnBillMvmnt.class, serv.getSessionFactory());
			TrnBillMvmnt tnrMvmtnVO = (TrnBillMvmnt) inputMap
					.get("BillMovementVO");
			Long lLngBillNo = tnrMvmtnVO.getBillNo();
			logger.info("Inside Bill MVMNS SERVIcE, Bill NO:::::::::::::: "
					+ lLngBillNo);

			/* Added by Hiral */
			Long userId = SessionHelper.getUserId(request);
			tnrMvmtnVO.setBillMvmtId(lLngCaseID);
			tnrMvmtnVO.setMovemntId(mvmntDAO.getmaxMovementId(lLngBillNo));
			Long lngMvmntId = (tnrMvmtnVO.getMovemntId() - 1);
			int mvmntStatus = mvmntDAO.updateMvmntIdInRmrks(lLngBillNo,
					lngMvmntId, userId);
			int objFlag = mvmntDAO.updateObjectionFlag(lLngBillNo);
			int rmrksFlag = mvmntDAO.updateRmrksFlag(lLngBillNo);
			/* End : Added by Hiral */

			logger.info(" after movement....Status.. ******* "
					+ tnrMvmtnVO.getMvmntStatus());
			mvmntDAO.create(tnrMvmtnVO);
			Map result = new HashMap();
			result.put("MESSAGECODE", 1038);
			objRes.setResultValue(result);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error("Error in insertBillMovement" + e,e);
			
		}
		return objRes;
	}
	
	/**
	 * This method is called from workflow framework as a rule internally to insert into trn_bill_mvmnt
	 * @param Map
	 * @return Map
	 */

	public Map insertBillMovementWF(Map inputMap) {
		logger.info(":::::::::::::: Insia=de");
		WorkFlowVO wfVO = (WorkFlowVO) inputMap.get("WorkFlowVO");
		logger.info(":::::::::::::: Insia=de" + wfVO);
		Map appMap = wfVO.getAppMap();
		HttpServletRequest request = (HttpServletRequest) appMap
				.get("requestObj");
		Enumeration enu = request.getAttributeNames();

		while (enu.hasMoreElements()) {
			logger.info("Inside Bill MovementSErvice,REquest name : "
					+ enu.nextElement().toString());
		}
		Enumeration enudm = request.getSession(false).getAttributeNames();
		while (enudm.hasMoreElements()) {
			logger.info("\n-- " + enudm.nextElement().toString());
		}

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) appMap.get("serviceLocator");
		try {
			Long lLngCaseID = BptmCommonServiceImpl.getNextSeqNum(
					"trn_bill_mvmnt", appMap).longValue();
			logger
					.info(" \n\n --------- In BillMovementSErvice, New Id is --- "
							+ lLngCaseID + "\n\n ");

			BillMovementDAOImpl mvmntDAO = new BillMovementDAOImpl(
					TrnBillMvmnt.class, serv.getSessionFactory());
			TrnBillMvmnt tnrMvmtnVO = (TrnBillMvmnt) appMap
					.get("BillMovementVO");
			if (tnrMvmtnVO != null) {
				TrnBillMvmnt tnrMvmtnVO1 = getMovementInstance(tnrMvmtnVO,
						appMap);
				Long lLngBillNo = tnrMvmtnVO.getBillNo();
				logger.info("Inside Bill MVMNS SERVIcE, Bill NO:::::::::::::: "
						+ lLngBillNo);
				tnrMvmtnVO1.setBillMvmtId(lLngCaseID);
				tnrMvmtnVO1.setBillNo(Long.parseLong(wfVO.getJobRefId()));
				if (tnrMvmtnVO1.getMovemntId() == null) {
					tnrMvmtnVO1.setMovemntId(mvmntDAO
							.getmaxMovementId(lLngBillNo));
				}
				/* Added by Hiral */
				Long userId = SessionHelper.getUserId(request);
				//System.out.println("value of Bill Movement Id in remarks : "					+ tnrMvmtnVO1.getBillMvmtId());
				Long lngMvmntId = (tnrMvmtnVO1.getBillMvmtId());
				int mvmntStatus = mvmntDAO.updateMvmntIdInRmrks(lLngBillNo,
						lngMvmntId, userId);
				int objFlag = mvmntDAO.updateObjectionFlag(lLngBillNo);
				int rmrksFlag = mvmntDAO.updateRmrksFlag(lLngBillNo);

				/* End : Added by Hiral */
				// Add By Bhavik For ag approve for online bill receving Process
				if (bundleConst.getString("CMN.AGAPPRV").equals("Y")) {
					if (!BptmCommonServiceImpl.isPhyBill(tnrMvmtnVO1
							.getBillNo(), appMap)) {
						tnrMvmtnVO1.setReceivedFlag((short) 1);
					}
				}
				logger.info(" after movement..STAUS.... ******* "
						+ tnrMvmtnVO1.getMvmntStatus());
				logger
						.info("::::::::::::::::::::::::::::::::::::::::::::::MVMNT DEBUG :: Before Create Mvmnt ID :: "
								+ tnrMvmtnVO1.getBillMvmtId());
				System.out
						.println("::::::::::::::::::::::::::::::::::::::::::::::MVMNT DEBUG :: Before Create Mvmnt ID :: "
								+ tnrMvmtnVO1.getBillMvmtId());
				Long lObjMvmntId = mvmntDAO.create(tnrMvmtnVO1);
				logger
						.info("::::::::::::::::::::::::::::::::::::::::::::::MVMNT DEBUG :: After Create Mvmnt ID :: "
								+ lObjMvmntId);
				System.out
						.println("::::::::::::::::::::::::::::::::::::::::::::::MVMNT DEBUG :: After Create Mvmnt ID :: "
								+ lObjMvmntId);
				Map result = new HashMap();
				result.put("MESSAGECODE", 1038);
				objRes.setResultValue(result);
				objRes.setViewName("ajaxData");
			}
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
      inputMap.put("ERROR",e);
			e.printStackTrace();
			logger.error("Error in insertBillMovementFromWf" + e,e);
		}
		return inputMap;
	}

		/**
		 * 	This is used to generate a copy of object from object of TrnBillMvmnt to avoid PK problem of hibernate
		 * 
		 * @param TrnBillMvmnt
		 * @param Map
		 * @return TrnBillMvmnt
		 */
	public TrnBillMvmnt getMovementInstance(TrnBillMvmnt trnBillMvmnt,
			Map inputMap) {
		ResourceBundle lObjRsrcBndle = ResourceBundle
				.getBundle("resources/billproc/BillprocConstants");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		Long lLngPostId = SessionHelper.getPostId(inputMap);
		Long lLngUserId = SessionHelper.getUserId(request);
		/*
		 * if(request.getParameter("toPost")!=null) { String lLngPostId[] =
		 * ()request.getParameter("toPost"); StringTokenizer st = new
		 * StringTokenizer(billsNo[i],"~"); lStrBillNo[i] = st.nextToken();
		 * //System.out.println("Value of toPost after Forwarding bill"
		 * +lLngPostId); }
		 */
		TrnBillMvmnt newTrnBillMvmnt = new TrnBillMvmnt();
		newTrnBillMvmnt.setBillNo(trnBillMvmnt.getBillNo());
		newTrnBillMvmnt.setStatusUpdtDate(trnBillMvmnt.getStatusUpdtDate());
		newTrnBillMvmnt.setCreatedDate(trnBillMvmnt.getCreatedDate());
		newTrnBillMvmnt.setCreatedPostId(trnBillMvmnt.getCreatedPostId());
		newTrnBillMvmnt.setCreatedUserId(trnBillMvmnt.getCreatedUserId());
		newTrnBillMvmnt.setDbId(trnBillMvmnt.getDbId());
		newTrnBillMvmnt.setStatusUpdtPostid(trnBillMvmnt.getStatusUpdtPostid());
		newTrnBillMvmnt.setStatusUpdtUserid(trnBillMvmnt.getStatusUpdtUserid());

		//System.out.println("Value of toPost from MvmntVO : "				+ trnBillMvmnt.getStatusUpdtPostid());

		// newTrnBillMvmnt.setHrchyUserId();
		newTrnBillMvmnt.setLocationCode(trnBillMvmnt.getLocationCode());
		newTrnBillMvmnt.setMovemntId(trnBillMvmnt.getMovemntId());
		if (trnBillMvmnt.getMvmntStatus().equalsIgnoreCase(
				lObjRsrcBndle.getString("STATUS.BillInward"))) {
			newTrnBillMvmnt.setMvmntStatus(lObjRsrcBndle
					.getString("STATUS.BillFwdCardex"));
			newTrnBillMvmnt.setMovemntId(trnBillMvmnt.getMovemntId() + 1);
		} else {
			newTrnBillMvmnt.setMvmntStatus(trnBillMvmnt.getMvmntStatus());
		}
		newTrnBillMvmnt.setReceivedDate(trnBillMvmnt.getReceivedDate());
		newTrnBillMvmnt.setReceivedFlag(trnBillMvmnt.getReceivedFlag());
		newTrnBillMvmnt.setReceivingUserId(trnBillMvmnt.getReceivingUserId());
		newTrnBillMvmnt.setUpdatedDate(trnBillMvmnt.getUpdatedDate());
		newTrnBillMvmnt.setUpdatedPostId(trnBillMvmnt.getUpdatedPostId());
		newTrnBillMvmnt.setUpdatedUserId(trnBillMvmnt.getUpdatedUserId());

		return newTrnBillMvmnt;
	}

}
