package com.tcs.sgv.billproc.counter.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.MstBillType;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;

/** PhyBillVOGeneratorImpl
 *  This class generates VO for TrnBillRegister, RptExpenditureDtls, RptReceiptDtls
 *   based on the values provided by end-user.
 *  These VOs are then used to insert/update the date in respective tables. 
 *   
 * 	Date of Creation : 4th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *  Hiral Shah    23-Oct-2007   For making changes for code formating
 *  Hiral Shah	  26-Oct-2007	For making changes of Bill Type Code
 */

public class PhyBillVOGeneratorImpl extends ServiceImpl implements
		VOGeneratorService {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	/**
	 * Method to generate VO for 'trn_bill_register' table.
	 * 
	 * @param :
	 *            Map : p_objServiceArgs
	 * 
	 * @return : ResultObject
	 */
	public ResultObject generateMap(Map inputMap) {
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			String lStrBillCode = null;
			TrnBillRegister lObjBillRegister = new TrnBillRegister();
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(
					SgvcFinYearMst.class, serv.getSessionFactory());
			BptmCommonServicesDAOImpl lObjBptmCmnSrvc = new BptmCommonServicesDAOImpl(
					MstBillType.class, serv.getSessionFactory());
			String lStrDeptCode = "";
			Long lLngDbId = SessionHelper.getDbId(inputMap);
			Long lLngUserId = SessionHelper.getUserId(request);
			Long lLngPostId = SessionHelper.getPostId(inputMap);
			Long lLngLangId = SessionHelper.getLangId(inputMap);
			logger.info("Value of location code from SessionHelper : "
					+ SessionHelper.getLocationCode(inputMap));
			String lStrLocCode = SessionHelper.getLocationCode(inputMap);

			String lStrBillCntrlNo = (StringUtility.getParameter(
					"txtBillControlNo", request).length() > 0) ? StringUtility
					.getParameter("txtBillControlNo", request) : null;
			String lStrDemandCode = (StringUtility.getParameter("cmbDemand",
					request).length() > 0) ? StringUtility.getParameter(
					"cmbDemand", request) : null;
			String lStrBudMjrHd = (StringUtility.getParameter("cmbMajorHead",
					request).length() > 0) ? StringUtility.getParameter(
					"cmbMajorHead", request) : null;
			String lStrExempted = (StringUtility.getParameter("radExempted",
					request).length() > 0) ? StringUtility.getParameter(
					"radExempted", request) : null;
			String lStrDdoCode = (StringUtility
					.getParameter("DDOCode", request).length() > 0) ? StringUtility
					.getParameter("DDOCode", request)
					: null;
			String lStrCurrBillStatus = gObjRsrcBndle
					.getString("STATUS.BillInward");
			String lDtBillDate = StringUtility.getParameter("txtBillDate",
					request);
			String lStrBillCtgry = (StringUtility.getParameter("cmbTCCtgry",
					request).length() > 0) ? StringUtility.getParameter(
					"cmbTCCtgry", request) : null;

			BigDecimal lBdNetAmt = (StringUtility.getParameter("txtNetAmt",
					request).length() > 0) ? new java.math.BigDecimal(
					StringUtility.getParameter("txtNetAmt", request)) : null;
			BigDecimal lBdGrossAmt = (StringUtility.getParameter("txtGrossAmt",
					request).length() > 0) ? new java.math.BigDecimal(
					StringUtility.getParameter("txtGrossAmt", request)) : null;
			BigDecimal lBdGrantAmt = (StringUtility.getParameter("txtGrantAmt",
					request).length() > 0) ? new java.math.BigDecimal(
					StringUtility.getParameter("txtGrantAmt", request)) : null;

			Long lLngAuditPost = (StringUtility.getParameter("cmbAuditorName",
					request).length() > 0) ? Long.parseLong(StringUtility
					.getParameter("cmbAuditorName", request)) : null;

			Long lLngTokenNo = (StringUtility.getParameter("txtTokenNo",
					request).length() > 0) ? Long.parseLong(StringUtility
					.getParameter("txtTokenNo", request)) : null;
			String lStrBillType = (StringUtility.getParameter("cmbBillType",
					request).length() > 0) ? StringUtility
					.getParameter("cmbBillType", request) : null;
			String lStrGoNgo = (StringUtility.getParameter("cmbGONGO", request)
					.length() > 0) ? StringUtility.getParameter("cmbGONGO",
					request) : null;

			Integer lIntFinYearId = lObjFinYearDAOImpl.getFinYearIdByCurDate();

			Date lDtCurDate = new java.util.Date();

			if (lStrExempted.equalsIgnoreCase(gObjRsrcBndle
					.getString("CMN.Yes"))) {
				lStrBillCode = StringUtility.getParameter("cmbBillCode",
						request).length() > 0 ? StringUtility.getParameter(
						"cmbBillCode", request) : null;
				lObjBillRegister.setBillCode(lStrBillCode);
			}

			lObjBillRegister.setBillCntrlNo(lStrBillCntrlNo);
			lObjBillRegister.setBillDate(new SimpleDateFormat("dd/MM/yyyy")
					.parse(lDtBillDate));
/*     Changes for Bill Type Code   */			
			Long lLngSubjectId = null;
			if(lObjBptmCmnSrvc.getSubIdFromBillType(lStrBillType)!=null)
			{
				logger.info("Inside if for Bill type, value of BillType : " +lStrBillType);
				lLngSubjectId = lObjBptmCmnSrvc.getSubIdFromBillType(lStrBillType);				
				logger.info("Inside if for Bill type, value of Subject Id : " +lLngSubjectId);
				lObjBillRegister.setSubjectId(lLngSubjectId);
			}
/*     Changes for Bill Type Code   */			
			lObjBillRegister.setTokenNum(lLngTokenNo);
			lObjBillRegister.setTcBill(lStrBillCtgry);

			lObjBillRegister.setPhyBill(Short.parseShort(gObjRsrcBndle
					.getString("CMN.One")));
			lObjBillRegister.setDemandCode(lStrDemandCode);
			lObjBillRegister.setBudmjrHd(lStrBudMjrHd);
			lObjBillRegister.setInwardDt(lDtCurDate);
			lObjBillRegister.setTsryOfficeCode(lStrLocCode);

			logger.info("Value of Request getParameter for txtDepartment : "
					+ request.getParameter("txtDepartment"));
			if (request.getParameter("txtDepartment") != null)
				if (!request.getParameter("txtDepartment").toString()
						.equalsIgnoreCase("-1"))
					lObjBillRegister.setDdoDeptId(request
							.getParameter("txtDepartment"));

			lObjBillRegister.setBillGrossAmount(lBdGrossAmt);
			lObjBillRegister.setBillNetAmount(lBdNetAmt);
			lObjBillRegister.setVersionId(Long.parseLong(gObjRsrcBndle
					.getString("CMN.One")));
			lObjBillRegister.setExempted(lStrExempted);
			lObjBillRegister.setGoNgo(lStrGoNgo);
			lObjBillRegister.setCurrBillStatus(lStrCurrBillStatus);

			/* To set Dept Code */
			if (lStrDemandCode != null)
				lStrDeptCode = BptmCommonServiceImpl.getDeptByDemand(
						lStrDemandCode, lLngLangId, lLngDbId, serv);
			logger
					.info("Value of department Code in Physical Bill VO Gennerator : "
							+ lStrDeptCode);
			if (lStrDeptCode != null)
				lObjBillRegister.setDeptCode(lStrDeptCode);
			/* End : To set Dept Code */
			logger.info("Value of Auditor Post in PhysicalBillVOGEn : "
					+ lLngAuditPost);
			if (lLngAuditPost != null && lLngAuditPost == -1) {
				lLngAuditPost = null;
				lObjBillRegister.setAudPostId(lLngAuditPost);
			}
			if (lLngAuditPost != null && lLngAuditPost != -1)
				lObjBillRegister.setAudPostId(lLngAuditPost);

			lObjBillRegister.setFinYearId(lIntFinYearId.toString());
			lObjBillRegister.setTrnCounter(new Integer(1));
			lObjBillRegister.setDdoCode(lStrDdoCode);
			lObjBillRegister.setCurrBillStatusDate(lDtCurDate);
			lObjBillRegister.setGrantAmount(lBdGrantAmt);

			lObjBillRegister.setCreatedDate(lDtCurDate);
			lObjBillRegister.setCreatedUserId(lLngUserId);
			lObjBillRegister.setCreatedPostId(lLngPostId);
			lObjBillRegister.setUpdatedDate(lDtCurDate);
			lObjBillRegister.setUpdatedPostId(lLngPostId);
			lObjBillRegister.setUpdatedUserId(lLngUserId);
			lObjBillRegister.setLocationCode(lStrLocCode);
			lObjBillRegister.setDbId(lLngDbId);

			if (request.getParameter("actionBtn") != null) {
				String actionFlag = request.getParameter("actionBtn");
				if (actionFlag == null) {
					lObjBillRegister.setCurrBillStatus(gObjRsrcBndle
							.getString("STATUS.BillInward"));
				} else if (actionFlag != null) {
					if (actionFlag.equalsIgnoreCase("forward")) {
						lObjBillRegister.setCurrBillStatus(gObjRsrcBndle
								.getString("STATUS.BillFwdCardex"));
					}
				}
			}
			inputMap.put("lObjTrnBillRegisterVO", lObjBillRegister);
			retObj.setResultValue(inputMap);

		} catch (Exception e) {
			retObj.setResultValue(null);
			retObj.setThrowable(e);
			retObj.setResultCode(ErrorConstants.ERROR);
			retObj.setViewName("errorPage");
			logger.error("Error occurred in generateMap for PhyBillVOGEN : "
					+ e, e);
		}
		return retObj;
	}
	/**
	 * This method generates VO for RptExpenditureDtls
	 * 
	 * @param   Map : inputMap
	 * 
	 * @return  RptExpenditureDtls
	 */
	public RptExpenditureDtls generateTrnExpVO(Map inputMap) {
		TrnBillRegister lObjTrnBillRegister = (TrnBillRegister) inputMap
				.get("lObjTrnBillRegisterVO");
		RptExpenditureDtls lObjRptExpDtls = null;
		if (inputMap.get("RptExpenditureVO") != null) {
			lObjRptExpDtls = (RptExpenditureDtls) inputMap
					.get("RptExpenditureVO");
		} else {
			lObjRptExpDtls = new RptExpenditureDtls();
		}

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(
				SgvcFinYearMst.class, serv.getSessionFactory());
		BptmCommonServicesDAOImpl lObjBptmCmnSrvc = new BptmCommonServicesDAOImpl(
				TrnBillBudheadDtls.class, serv.getSessionFactory());
		LocationDAOImpl lObjCmnLocMstDao = new LocationDAOImpl(
				CmnLocationMst.class, serv.getSessionFactory());

		String lStrDeptCode = "";
		Long lLngDbId = SessionHelper.getDbId(inputMap);
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		String lStrLocCode = SessionHelper.getLocationCode(inputMap);

		CmnDistrictMst lObjCmnDistMst = lObjCmnLocMstDao.getDistCodeFrmLocId(
				lStrLocCode, lLngLangId);
		logger
				.info("Value of Distrcit VO from LocationDAO : "
						+ lObjCmnDistMst);

		String lStrTemp = "";
		Integer lIntSchemeNo = null;
		try {
			Date lDtCurDate = new java.util.Date();
			TrnBillBudheadDtls lObjTrnBudHead = null;
			if (inputMap.get("TrnBudHeadDtls") != null
					&& inputMap.get("TrnBudHeadDtls").toString().length() > 0) {
				lObjTrnBudHead = (TrnBillBudheadDtls) inputMap
						.get("TrnBudHeadDtls");
			} else {			
				lObjTrnBudHead = lObjBptmCmnSrvc
						.getBudHdVoFromBillNo(lObjTrnBillRegister.getBillNo());
			}

			lObjRptExpDtls.setSubMjrHd(lObjTrnBudHead.getBudSubmjrHd());
			lObjRptExpDtls.setMinHd(lObjTrnBudHead.getBudMinHd());
			lObjRptExpDtls.setSubHd(lObjTrnBudHead.getBudSubHd());
			lObjRptExpDtls.setDtlHd(lObjTrnBudHead.getBudDtlHd());

			if (lObjTrnBudHead.getBudType() != null) {
				lStrTemp = lObjTrnBudHead.getBudType().toString();
				logger.info("Value of getBudType() : " + lStrTemp);
			}
			lObjRptExpDtls.setBudTypeCode(lStrTemp);

			if (lObjTrnBudHead.getSchemeNo() != null)
				lIntSchemeNo = Integer.parseInt(lObjTrnBudHead.getSchemeNo());
			lObjRptExpDtls.setScheme(lIntSchemeNo);

			lStrDeptCode = BptmCommonServiceImpl.getDeptByDemand(
					lObjTrnBillRegister.getDemandCode(), lLngLangId, lLngDbId,
					serv);
			logger
					.info("Value of department Code in Physical Bill VO Gennerator : "
							+ lStrDeptCode);
			if (lStrDeptCode != null)
				lObjRptExpDtls.setDeptCode(lStrDeptCode.toString());
			lObjRptExpDtls.setDdoCode(lObjTrnBillRegister.getDdoCode());
			String lStrExpTypeCode = BptmCommonServiceImpl.getExpType(inputMap,
					gObjRsrcBndle.getString("CMN.Bill"));
			logger.info("Value of ExpTypeCode in generateExpTypeCode : "
					+ lStrExpTypeCode);

			lObjRptExpDtls.setTsryCode(lStrLocCode);
			lObjRptExpDtls.setExpTypeCode(gObjRsrcBndle.getString("CMN.Bill"));
			lObjRptExpDtls.setDemandNo(lObjTrnBillRegister.getDemandCode());
			logger.info("Value of Bill Status in generateTrnExpVO : "
					+ lObjTrnBillRegister.getCurrBillStatus());
			lObjRptExpDtls.setExpStatusCode(lObjTrnBillRegister
					.getCurrBillStatus());

			lObjRptExpDtls.setMjrHd(lObjTrnBillRegister.getBudmjrHd());
			lObjRptExpDtls.setFinYrId(new java.math.BigDecimal(
					lObjFinYearDAOImpl.getFinYearIdByCurDate()));
			lObjRptExpDtls.setExpStatusDt(lDtCurDate);
			lObjRptExpDtls.setExpCrtDt(lObjTrnBillRegister.getBillDate());
			if (lObjTrnBillRegister.getCurrBillStatus().equals(
					gObjRsrcBndle.getString("STATUS.VoucherGen"))) {
				lObjRptExpDtls.setExpDt(lDtCurDate);
			}
			if (lObjCmnDistMst != null)
				lObjRptExpDtls
						.setDistrictCode(lObjCmnDistMst.getDistrictCode());
			if (lObjTrnBillRegister.getDeptCode() != null)
				lObjRptExpDtls.setDeptCode(lObjTrnBillRegister.getDeptCode());

			inputMap.put("amount", lObjTrnBillRegister.getBillGrossAmount());
			lObjRptExpDtls.setGrossAmnt(inputMap);

			inputMap.put("amount", lObjTrnBillRegister.getBillNetAmount());
			lObjRptExpDtls.setNetAmt(inputMap);

			logger.info("Going out of VO gen for RptExpenditureDtls");
			return lObjRptExpDtls;
		} catch (Exception e) {
			logger.error("Got in Exception in RptExpenditureDtls : " + e, e);
			return lObjRptExpDtls;
		}
	}
	
	/**
	 * This method generates VO for RptReceiptDtls
	 * 
	 * @param  Map : inputMap
	 * 
	 * @return RptReceiptDtls
	 */
	
	public RptReceiptDtls generateRptRcptVO(Map inputMap) {
		TrnBillRegister lObjTrnBillRegister = null;
		TrnReceiptDetails lObjTrnRcptDtls = null;

		Date lDtCurDate = new java.util.Date();
		if (inputMap.containsKey("lObjTrnBillRegisterVO")) {
			lObjTrnBillRegister = (TrnBillRegister) inputMap
					.get("lObjTrnBillRegisterVO");
		}
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(
				SgvcFinYearMst.class, serv.getSessionFactory());

		String lStrLocCode = SessionHelper.getLocationCode(inputMap);
		String lStrRcptStatus = null;
		List lLstRptRcpt = new ArrayList();
		RptReceiptDtls lObjRptRcptDtls = null;

		LocationDAOImpl lObjCmnLocMstDao = new LocationDAOImpl(
				CmnLocationMst.class, serv.getSessionFactory());
		CmnDistrictMst lObjCmnDistMst = lObjCmnLocMstDao.getDistCodeFrmLocId(
				lStrLocCode, SessionHelper.getLangId(inputMap));
		logger
				.info("Value of Distrcit VO from LocationDAO : "
						+ lObjCmnDistMst);

		String lStrRcptTypeCode = "";
		if (inputMap.get("RptReceiptVOArrLst") != null) {			
			lLstRptRcpt = (List) inputMap.get("RptReceiptVOArrLst");
			if (lLstRptRcpt.get(0) != null) {
				lObjRptRcptDtls = (RptReceiptDtls) lLstRptRcpt.get(0);
			} else {
				lObjRptRcptDtls = new RptReceiptDtls();
			}
		} else {
			lObjRptRcptDtls = new RptReceiptDtls();
		}
		BptmCommonServicesDAOImpl lObjBptmCmnSrvc = new BptmCommonServicesDAOImpl(
				TrnBillBudheadDtls.class, serv.getSessionFactory());
		if (lObjTrnBillRegister != null) {
			if (inputMap.get("TrnReceiptDetailsVO") != null
					&& inputMap.get("TrnReceiptDetailsVO").toString().length() > 0) {				
				lObjTrnRcptDtls = (TrnReceiptDetails) inputMap
						.get("TrnReceiptDetailsVO");
				logger.info("Value of Trn Receipt Details in VOGEN : "
						+ lObjTrnRcptDtls.getReceiptDetailId());
			} else {			
				lObjTrnRcptDtls = lObjBptmCmnSrvc.getRcptVoFromBillNo(
						lObjTrnBillRegister.getBillNo(), inputMap);
			}
			logger.info("Value of lObjTrnRcptDtls in PhyBillVO Genreator :  "
					+ lObjTrnRcptDtls);
		}

		try {
			if (lObjTrnBillRegister != null) {
				String lStrDdoCode = lObjTrnBillRegister.getDdoCode();
				String lStrChallanCatgCode = gObjRsrcBndle
						.getString("CMN.Others");
				String lStrDemandCode = lObjTrnBillRegister.getDemandCode();
				String lStrReject = gObjRsrcBndle
						.getString("STATUS.BillRejected");
				String lStrCurState = lObjTrnBillRegister.getCurrBillStatus();

				if (lObjRptRcptDtls != null)
					lStrRcptStatus = lObjRptRcptDtls.getRcptStatusCode();

				if (!lStrDemandCode.equals("999")) {
					lStrRcptTypeCode = gObjRsrcBndle.getString("CMN.TcBill");
				} else {
					lStrRcptTypeCode = gObjRsrcBndle.getString("CMN.Bill");
					if (lObjRptRcptDtls.getRcptNo().intValue() == -1)
						lObjRptRcptDtls.setDisbursementAmt(lObjTrnBillRegister
								.getBillGrossAmount());
				}
				
				lObjRptRcptDtls.setTsryCode(lStrLocCode);
				lObjRptRcptDtls.setDdoCode(lStrDdoCode);
				lObjRptRcptDtls.setRcptTypeCode(lStrRcptTypeCode);				
				lObjRptRcptDtls.setChallanCatgCode(lStrChallanCatgCode);

				if (!lStrDemandCode.equals("999")) {
					lObjRptRcptDtls.setMjrHd(lObjTrnRcptDtls.getMajorHead());					
				}

				if (lStrDemandCode.equals("999")) {
					lObjRptRcptDtls.setDemandNo(lStrDemandCode);
					lObjRptRcptDtls.setMjrHd(lObjTrnBillRegister.getBudmjrHd());
				}
				if (lObjCmnDistMst != null)
					lObjRptRcptDtls.setDistrictCode(lObjCmnDistMst
							.getDistrictCode());

				lObjRptRcptDtls.setFinYrId(new java.math.BigDecimal(
						lObjFinYearDAOImpl.getFinYearIdByCurDate()));

				if ((lStrRcptStatus == null || lStrRcptStatus.length() <= 0)
						|| !(lStrRcptStatus.equalsIgnoreCase(lStrReject))) {
					lObjRptRcptDtls.setRcptStatusCode(gObjRsrcBndle
							.getString("STATUS.Pending"));
					if (lObjRptRcptDtls.getRcptStatusDate() == null) {						
						lObjRptRcptDtls.setRcptStatusDate(lDtCurDate);
					}
				}

				if (lStrCurState.equalsIgnoreCase(lStrReject))
				{
					lObjRptRcptDtls.setRcptStatusCode(gObjRsrcBndle
							.getString("STATUS.Reject"));
					if (lStrRcptStatus != null
							&& !(lStrRcptStatus.equalsIgnoreCase(gObjRsrcBndle
									.getString("STATUS.Reject")))) {
						lObjRptRcptDtls.setRcptStatusDate(lDtCurDate);
					}
				}
				if (Long.valueOf(lObjTrnRcptDtls.getReceiptDetailId()) != null
						&& (!lStrDemandCode.equals("999"))) {
					logger
							.info("Value of TrnReceiptID in if condition is :::: "
									+ lObjTrnRcptDtls.getReceiptDetailId());
					lObjRptRcptDtls.setTrnReceiptId(new java.math.BigDecimal(
							lObjTrnRcptDtls.getReceiptDetailId()));
				}
				if (Long.valueOf(lObjTrnBillRegister.getBillNo()) != null
						&& (!lStrDemandCode.equals("999")))
					lObjRptRcptDtls.setRcptNo(new java.math.BigDecimal(
							lObjTrnBillRegister.getBillNo()));
			}
		} catch (Exception e) {
			logger.error("Error in generateRptRcptVO : " + e, e);
			e.printStackTrace();
			lLstRptRcpt.add(lObjRptRcptDtls);
			return lObjRptRcptDtls;
		}
		return lObjRptRcptDtls;
	}
}
