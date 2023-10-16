/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 13, 2011		Bhargav Trivedi								
 *******************************************************************************
 */
package com.tcs.sgv.pensionpay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pensionpay.dao.SixPayFPArrearDAO;
import com.tcs.sgv.pensionpay.dao.SixPayFPArrearDAOImpl;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionSixpayfpArrear;


/**
 * Class Description -
 * 
 * 
 * @author Bhargav Trivedi
 * @version 0.1
 * @since JDK 5.0 Jun 13, 2011
 */
public class TrnPensionSixpayfpArrearVOGeneratorImpl extends ServiceImpl implements TrnPensionSixpayfpArrearVOGenerator {

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	Log gLogger = LogFactory.getLog(getClass());

	Date gDateDBDate = null;

	// Sets session information in the global variables
	private void setSessionInfo(Map inputMap) {

		gLngPostId = SessionHelper.getPostId(inputMap);
		gLngUserId = SessionHelper.getUserId(inputMap);
		gStrLocationCode = SessionHelper.getLocationCode(inputMap);
		gDateDBDate = DBUtility.getCurrentDateFromDB();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionpay.service.TrnPensionSixpayfpArrearVOGenerator#
	 * generateMap(java.util.Map)
	 */
	public ResultObject generateMap(Map inputMap) {

		gLogger.info("In generateMap of TrnPensionSixpayfpArrearVOGeneratorImpl........");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		Character lCharRvsnCntr = '1';
		SixPayFPArrearDAO lObjSixPayFPArrearDAO = null;
		String lStrPnsnrCode = null;
		String lStrRevision = null;
		Long lLngBillNo = null;
		Calendar lObjCalendar = null;
		Integer lIntMonth = 0;
		Integer lIntYear = 0;

		List<TrnPensionSixpayfpArrear> lLstCashSixPayArrear = null;
		try {
			setSessionInfo(inputMap);
			lObjSixPayFPArrearDAO = new SixPayFPArrearDAOImpl(TrnPensionSixpayfpArrear.class, serv.getSessionFactory());
			List<TrnPensionSixpayfpArrear> lLstTrnPensionSixpayfpArrearVO = new ArrayList<TrnPensionSixpayfpArrear>();
			TrnPensionSixpayfpArrear lObjTrnPensionSixpayfpArrearVO = new TrnPensionSixpayfpArrear();

			String[] lArrStrMonth = StringUtility.getParameterValues("cmbPayinMonth", request);
			String[] lArrStrYear = StringUtility.getParameterValues("cmbPayinYear", request);
			lStrRevision = StringUtility.getParameter("Revision", request);
			lStrPnsnrCode = StringUtility.getParameter("txtPnsnrCode", request);

			Integer lIntNoOfInstallment = 0;
			lLngBillNo = (Long) inputMap.get("BillNo");

			String lStrNoOfInstallment = StringUtility.getParameter("hdnNoOfInstallment", request);
			String lStrSixthPayCalcFlag = StringUtility.getParameter("hdnSixPayCalcFlag", request);

			if (!"".equals(lStrNoOfInstallment)) {
				lIntNoOfInstallment = Integer.parseInt(lStrNoOfInstallment);
			}

			if ("Y".equals(lStrSixthPayCalcFlag)) {
				lObjCalendar = Calendar.getInstance();
				lObjCalendar.setTime(gDateDBDate);
				lIntMonth = lObjCalendar.get(lObjCalendar.MONTH) + 1;
				lIntYear = lObjCalendar.get(lObjCalendar.YEAR);

				lArrStrMonth = new String[5];
				lArrStrYear = new String[5];
				for (int lIntCnt = 0; lIntCnt < lIntNoOfInstallment; lIntCnt++) {
					if (lIntMonth < 10) {
						lArrStrMonth[lIntCnt] = "0" + lIntMonth.toString();
					} else {
						lArrStrMonth[lIntCnt] = lIntMonth.toString();
					}
					lArrStrYear[lIntCnt] = lIntYear.toString();
				}
				for (int lIntCnt = lIntNoOfInstallment; lIntCnt < 5; lIntCnt++) {
					lArrStrMonth[lIntCnt] = "-1";
					lArrStrYear[lIntCnt] = "-1";
				}
			}

			lCharRvsnCntr = lObjSixPayFPArrearDAO.getMaxRvsnCntr(lStrPnsnrCode);

			if (lCharRvsnCntr != null) {
				if (lStrRevision.length() > 0) {
					inputMap.put("Mode", "Revise");
					lLstTrnPensionSixpayfpArrearVO = generateTrnPensionSixpayfpArrearVO(inputMap);
				} else {
					lLstTrnPensionSixpayfpArrearVO = lObjSixPayFPArrearDAO.getTrnPensionSixpayfpArrearVOs(lStrPnsnrCode, lCharRvsnCntr);
					if (lLstTrnPensionSixpayfpArrearVO != null && !lLstTrnPensionSixpayfpArrearVO.isEmpty()) {
						for (int lIntCnt = 0; lIntCnt < lLstTrnPensionSixpayfpArrearVO.size(); lIntCnt++) {

							lObjTrnPensionSixpayfpArrearVO = lLstTrnPensionSixpayfpArrearVO.get(lIntCnt);

							if (lArrStrYear[lIntCnt].trim().length() > 3) {
								lObjTrnPensionSixpayfpArrearVO.setPayInMonth(((lArrStrYear[lIntCnt].trim()).concat((lArrStrMonth[lIntCnt]).trim())).trim());
							} else {
								lObjTrnPensionSixpayfpArrearVO.setPayInMonth(null);
							}
							lObjTrnPensionSixpayfpArrearVO.setUpdatedDate(gDateDBDate);
							lObjTrnPensionSixpayfpArrearVO.setUpdatedPostId(BigDecimal.valueOf(gLngPostId));
							lObjTrnPensionSixpayfpArrearVO.setUpdatedUserId(BigDecimal.valueOf(gLngUserId));
						}

					}
					lLstCashSixPayArrear = generateCashArrearDtlsVo(inputMap);
					inputMap.put("Mode", "Update");
				}
			} else {
				String lStrArrearId = StringUtility.getParameter("Arrear_Id", request);
				if (lStrArrearId.length() > 0) {
					String lStrPayInMonth = StringUtility.getParameter("cmbPayinMonth", request);
					String lStrPayInYear = StringUtility.getParameter("cmbPayinYear", request);

					String[] lArrStrArrearId = lStrArrearId.split("~");
					for (int lIntCnt = 0; lIntCnt < lArrStrArrearId.length; lIntCnt++) {
						Long lLngArrearId = Long.valueOf(lArrStrArrearId[lIntCnt].trim());
						lObjTrnPensionSixpayfpArrearVO = (TrnPensionSixpayfpArrear) lObjSixPayFPArrearDAO.read(lLngArrearId);
						lObjTrnPensionSixpayfpArrearVO.setPayInMonth(lStrPayInYear.trim().concat(lStrPayInMonth.trim()));
						lObjTrnPensionSixpayfpArrearVO.setUpdatedDate(gDateDBDate);
						lObjTrnPensionSixpayfpArrearVO.setUpdatedPostId(BigDecimal.valueOf(gLngPostId));
						lObjTrnPensionSixpayfpArrearVO.setUpdatedUserId(BigDecimal.valueOf(gLngUserId));
						lLstTrnPensionSixpayfpArrearVO.add(lObjTrnPensionSixpayfpArrearVO);
					}
					inputMap.put("Mode", "Config");
				} else {
					lLstTrnPensionSixpayfpArrearVO = generateTrnPensionSixpayfpArrearVO(inputMap);
					lLstCashSixPayArrear = generateCashArrearDtlsVo(inputMap);
					inputMap.put("Mode", "Add");
				}
			}
			inputMap.put("lLstTrnPensionSixpayfpArrearVO", lLstTrnPensionSixpayfpArrearVO);
			inputMap.put("lLstCashSixPayArrear", lLstCashSixPayArrear);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(inputMap);
		} catch (Exception e) {
			gLogger.error("Error is  " + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");

		}
		return objRes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tcs.sgv.pensionpay.service.TrnPensionSixpayfpArrearVOGenerator#
	 * generateTrnPensionSixpayfpArrearVO(java.util.Map)
	 */
	public List<TrnPensionSixpayfpArrear> generateTrnPensionSixpayfpArrearVO(Map inputMap) {

		gLogger.info("In generateTrnPensionSixpayfpArrearVO method........");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		List<TrnPensionSixpayfpArrear> lLstTrnPensionSixpayfpArrearVO = new ArrayList<TrnPensionSixpayfpArrear>();
		TrnPensionSixpayfpArrear lObjTrnPensionSixpayfpArrearVO = null;
		SixPayFPArrearDAO lObjSixPayFPArrearDAO = null;
		List<TrnPensionSixpayfpArrear> lLstPrevSixPayFpArreasDtls = new ArrayList<TrnPensionSixpayfpArrear>();
		Map<Integer, BigDecimal> lMapPaidInstallmentAmt = new HashMap<Integer, BigDecimal>();
		Long lLngBillNo = null;
		Calendar lObjCalendar = null;
		Integer lIntMonth = 0;
		Integer lIntYear = 0;
		try {
			setSessionInfo(inputMap);
			Integer lIntTotalInstallment = 5;
			String lStrPnsnrCode = StringUtility.getParameter("txtPnsnrCode", request);
			String lStrTotAmnt = null;
			Integer lIntNoOfInstallment = 1;
			Integer lIntInstallmentsCntr = 1;
			Character lCharRvsnCntr = null;
			String lStrRemarks = null;
			String[] lStrArrRemarks = null;
			List<Integer> lLstPaidPPOEntries = new ArrayList<Integer>();
			String lStrRevision = StringUtility.getParameter("Revision", request);
			lObjSixPayFPArrearDAO = new SixPayFPArrearDAOImpl(TrnPensionSixpayfpArrear.class, serv.getSessionFactory());
			lLngBillNo = (Long) inputMap.get("BillNo");
			String lStrInstallmentAmt = StringUtility.getParameter("hdnInstallmentAmt", request);
			String lStrInstallment5Amt = StringUtility.getParameter("hdnInstallment5Amt", request);
			String lStrSixthPayCalcFlag = StringUtility.getParameter("hdnSixPayCalcFlag", request);
			String lStrNoOfInstallment = StringUtility.getParameter("hdnNoOfInstallment", request);

			if (lStrRevision.length() > 0) {
				lStrTotAmnt = StringUtility.getParameter("txtTotalAmt", request);
			} else {
				lStrTotAmnt = StringUtility.getParameter("txtTotAmt", request);
			}

			String[] lArrStrMonth = StringUtility.getParameterValues("cmbPayinMonth", request);

			String[] lArrStrYear = StringUtility.getParameterValues("cmbPayinYear", request);

			if ("Y".equals(lStrSixthPayCalcFlag)) {
				if (!"".equals(lStrNoOfInstallment)) {
					lIntNoOfInstallment = Integer.parseInt(lStrNoOfInstallment);
				}

				if (!"".equals(lStrInstallmentAmt) && !"".equals(lStrInstallment5Amt)) {
					Long lLngTotalAmt = (Long.parseLong(lStrInstallmentAmt) * 4) + Long.parseLong(lStrInstallment5Amt);
					lStrTotAmnt = lLngTotalAmt.toString();
				}
				lObjCalendar = Calendar.getInstance();
				lObjCalendar.setTime(gDateDBDate);
				lIntMonth = lObjCalendar.get(lObjCalendar.MONTH) + 1;
				lIntYear = lObjCalendar.get(lObjCalendar.YEAR);

				lArrStrMonth = new String[5];
				lArrStrYear = new String[5];
				for (int lIntCnt = 0; lIntCnt < lIntNoOfInstallment; lIntCnt++) {
					if (lIntMonth < 10) {
						lArrStrMonth[lIntCnt] = "0" + lIntMonth.toString();
					} else {
						lArrStrMonth[lIntCnt] = lIntMonth.toString();
					}
					lArrStrYear[lIntCnt] = lIntYear.toString();
				}
				for (int lIntCnt = lIntNoOfInstallment; lIntCnt < 5; lIntCnt++) {
					lArrStrMonth[lIntCnt] = "-1";
					lArrStrYear[lIntCnt] = "-1";
				}
			}

			lCharRvsnCntr = lObjSixPayFPArrearDAO.getMaxRvsnCntr(lStrPnsnrCode);
			if (lCharRvsnCntr == null) {
				lCharRvsnCntr = '1';
				lStrRevision = "";
			}

			if (lStrRevision.length() > 0) {
				// fetch old entries which have paid flag Y and activate flag Y
				// lLstPaidPPOEntries =
				// lObjSixPayFPArrearDAO.getPaidPPOEntries(lCharRvsnCntr,
				// lStrPnsnrCode);
				lLstPrevSixPayFpArreasDtls = lObjSixPayFPArrearDAO.getTrnPensionSixpayfpArrearVOs(lStrPnsnrCode, lCharRvsnCntr);
				if (lLstPrevSixPayFpArreasDtls != null) {
					for (TrnPensionSixpayfpArrear lObjTrnPensionSixpayfpArrear : lLstPrevSixPayFpArreasDtls) {
						if (('Y' == lObjTrnPensionSixpayfpArrear.getActiveFlag()) && ('Y' == lObjTrnPensionSixpayfpArrear.getPaidFlag())) {
							lLstPaidPPOEntries.add(lObjTrnPensionSixpayfpArrear.getInstallmentNo());
							lMapPaidInstallmentAmt.put(lObjTrnPensionSixpayfpArrear.getInstallmentNo(), lObjTrnPensionSixpayfpArrear.getInstallmentAmnt());
						}
					}
				}
				lObjSixPayFPArrearDAO.updateOldPPOEntries(lCharRvsnCntr, lStrPnsnrCode);
				Integer lIntRvsnCntr = Integer.valueOf(String.valueOf(lCharRvsnCntr));
				lIntRvsnCntr = lIntRvsnCntr + 1;
				lCharRvsnCntr = Character.valueOf(StringUtility.convertToChar(String.valueOf(lIntRvsnCntr)));
				lStrRemarks = StringUtility.getParameter("txtRemarks", request);
				lStrArrRemarks = lStrRemarks.split("~");
			}
			if ("Y".equals(lStrSixthPayCalcFlag)) {
				lStrArrRemarks = new String[5];
				for (int lIntCnt = 0; lIntCnt < 5; lIntCnt++) {
					lStrArrRemarks[lIntCnt] = "Configure from supplementary bill";
				}

			}

			Boolean lBlPaidFlag;
			BigDecimal lBDPrevAmnt;
			for (int lIntCnt = 0; lIntCnt < 5; lIntCnt++) {
				lBlPaidFlag = false;
				lBDPrevAmnt = BigDecimal.ZERO;
				lObjTrnPensionSixpayfpArrearVO = new TrnPensionSixpayfpArrear();

				lObjTrnPensionSixpayfpArrearVO.setPensionerCode(lStrPnsnrCode);
				lObjTrnPensionSixpayfpArrearVO.setArrearType('S');
				// newly added code for diff amnt identification starts
				if (lLstPaidPPOEntries != null && lLstPaidPPOEntries.size() > 0) {
					for (Integer lIntPaidCnt : lLstPaidPPOEntries) {
						if (lIntPaidCnt == (lIntCnt + 1)) {
							lBlPaidFlag = true;
						}
					}
				}
				// newly added code for diff amnt identification ends
				if (lArrStrYear[lIntCnt].trim().length() > 3) {
					lObjTrnPensionSixpayfpArrearVO.setPayInMonth(((lArrStrYear[lIntCnt].trim()).concat((lArrStrMonth[lIntCnt]).trim())).trim());
					if ("Y".equals(lStrSixthPayCalcFlag)) {
						lObjTrnPensionSixpayfpArrearVO.setConfigFromSuppl('Y');
					}
					else {
						lObjTrnPensionSixpayfpArrearVO.setConfigFromSuppl('N');
					}
				} else {
					lObjTrnPensionSixpayfpArrearVO.setPayInMonth(null);
					lObjTrnPensionSixpayfpArrearVO.setConfigFromSuppl('N');
				}
				if (lIntCnt == 4) {
					lObjTrnPensionSixpayfpArrearVO.setInstallmentAmnt(BigDecimal.valueOf(((Long.valueOf(lStrTotAmnt) - (Long.valueOf(lStrTotAmnt) % lIntTotalInstallment)) / 5l)
							+ Long.valueOf(lStrTotAmnt) % lIntTotalInstallment));
				} else {
					lObjTrnPensionSixpayfpArrearVO.setInstallmentAmnt(BigDecimal.valueOf((Long.valueOf(lStrTotAmnt) - (Long.valueOf(lStrTotAmnt) % lIntTotalInstallment)) / 5l));
				}
				// newly added code for diff amnt identification starts
				if (lBlPaidFlag == true) {
					// lBDPrevAmnt = lObjSixPayFPArrearDAO
					// .getPrevInstAmnt(lStrPnsnrCode,
					// StringUtility.convertToChar(String.valueOf((Integer.valueOf(String.valueOf(lCharRvsnCntr))
					// - 1))), (lIntCnt + 1));
					lBDPrevAmnt = lMapPaidInstallmentAmt.get(lIntCnt + 1);
					lObjTrnPensionSixpayfpArrearVO.setDiffAmnt(BigDecimal.valueOf((lObjTrnPensionSixpayfpArrearVO.getInstallmentAmnt().longValue() - lBDPrevAmnt.longValue())));
				} else {
					lObjTrnPensionSixpayfpArrearVO.setDiffAmnt(BigDecimal.ZERO);
				}
				if (lBlPaidFlag == true) {
					lObjTrnPensionSixpayfpArrearVO.setPaidFlag('Y');
				} else {
					lObjTrnPensionSixpayfpArrearVO.setPaidFlag('N');
				}
				// newly added code for diff amnt identification ends
				lObjTrnPensionSixpayfpArrearVO.setInstallmentNo(lIntInstallmentsCntr);
				lObjTrnPensionSixpayfpArrearVO.setActiveFlag('Y');
				lObjTrnPensionSixpayfpArrearVO.setRevisionCounter(lCharRvsnCntr);
				lObjTrnPensionSixpayfpArrearVO.setCreatedUserId(BigDecimal.valueOf(gLngUserId));
				lObjTrnPensionSixpayfpArrearVO.setCreatedPostId(BigDecimal.valueOf(gLngPostId));
				lObjTrnPensionSixpayfpArrearVO.setCreatedDate(gDateDBDate);
				lObjTrnPensionSixpayfpArrearVO.setUpdatedDate(gDateDBDate);
				lObjTrnPensionSixpayfpArrearVO.setUpdatedPostId(BigDecimal.valueOf(gLngPostId));
				lObjTrnPensionSixpayfpArrearVO.setUpdatedUserId(BigDecimal.valueOf(gLngUserId));
				if (lStrArrRemarks == null || lStrArrRemarks.length == 0) {
					lObjTrnPensionSixpayfpArrearVO.setRemarks(lStrRemarks);
				} else {
					if (lStrArrRemarks[lIntCnt] == "null") {
						lObjTrnPensionSixpayfpArrearVO.setRemarks(lStrRemarks);
					} else {
						lObjTrnPensionSixpayfpArrearVO.setRemarks(lStrArrRemarks[lIntCnt]);
					}

				}
				lObjTrnPensionSixpayfpArrearVO.setBillNo(lLngBillNo);
				++lIntInstallmentsCntr;

				lLstTrnPensionSixpayfpArrearVO.add(lObjTrnPensionSixpayfpArrearVO);

			}

		} catch (Exception e) {
			//e.printStackTrace();
			gLogger.error("Error in generateTrnPensionSixpayfpArrearVO method is :" + e, e);

		}

		return lLstTrnPensionSixpayfpArrearVO;

	}

	public List<TrnPensionSixpayfpArrear> generateCashArrearDtlsVo(Map inputMap) {

		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		List<TrnPensionSixpayfpArrear> lLstCashSixPayArrear = null;
		String[] paraArrCashArrearDtls = null;
		String lStrPayInMonth = null;
		String lStrPayInYear = null;
		String lStrCashAmount = null;
		TrnPensionSixpayfpArrear lObjTrnPensionSixpayfpArrear = null;
		String lStrPnsrCode = null;
		String lStrYearMonth = null;
		Long lLngBillNo = null;
		Calendar lObjCalendar = null;
		Integer lIntMonth = 0;
		Integer lIntYear = 0;
		try {
			String[] lArrCashArrearDtls = null;
			paraArrCashArrearDtls = StringUtility.getParameterValues("cashArrearDtls", request);
			lStrPnsrCode = StringUtility.getParameter("txtPnsnrCode", request).trim();

			String lStrSixthPayCalcFlag = StringUtility.getParameter("hdnSixPayCalcFlag", request);
			String lStrCashAmt = StringUtility.getParameter("hdnCashAmt", request);
			lLngBillNo = (Long) inputMap.get("BillNo");
			if ("Y".equals(lStrSixthPayCalcFlag)) {
				lObjCalendar = Calendar.getInstance();
				lObjCalendar.setTime(gDateDBDate);
				lIntMonth = lObjCalendar.get(lObjCalendar.MONTH) + 1;
				lIntYear = lObjCalendar.get(lObjCalendar.YEAR);
				paraArrCashArrearDtls = new String[1];
				paraArrCashArrearDtls[0] = lIntMonth.toString() + "~" + lIntYear.toString() + "~" + lStrCashAmt;
			}
			if (paraArrCashArrearDtls.length > 0) {
				lLstCashSixPayArrear = new ArrayList<TrnPensionSixpayfpArrear>();
				for (int cnt = 0; cnt < paraArrCashArrearDtls.length; cnt++) {
					lArrCashArrearDtls = paraArrCashArrearDtls[cnt].split("~");
					lStrPayInMonth = lArrCashArrearDtls[0].trim();
					lStrPayInYear = lArrCashArrearDtls[1].trim();
					lStrCashAmount = lArrCashArrearDtls[2].trim();
					if (lStrPayInMonth.length() > 0 && !"-1".equals(lStrPayInMonth)) {
						if (Integer.parseInt(lStrPayInMonth) < 10) {
							lStrPayInMonth = "0" + lStrPayInMonth;
						}
						lStrYearMonth = lStrPayInYear + lStrPayInMonth;
					} else {
						lStrYearMonth = null;
					}
					if (lStrYearMonth != null) {
						lObjTrnPensionSixpayfpArrear = new TrnPensionSixpayfpArrear();
						lObjTrnPensionSixpayfpArrear.setPensionerCode(lStrPnsrCode);
						lObjTrnPensionSixpayfpArrear.setArrearType('C');
						lObjTrnPensionSixpayfpArrear.setInstallmentNo(1);
						lObjTrnPensionSixpayfpArrear.setPayInMonth(lStrYearMonth);
						lObjTrnPensionSixpayfpArrear.setInstallmentAmnt(new BigDecimal(lStrCashAmount));
						lObjTrnPensionSixpayfpArrear.setPaidFlag('N');
						lObjTrnPensionSixpayfpArrear.setActiveFlag('Y');
						lObjTrnPensionSixpayfpArrear.setRevisionCounter('1');
						lObjTrnPensionSixpayfpArrear.setBillNo(lLngBillNo);
						lObjTrnPensionSixpayfpArrear.setCreatedUserId(BigDecimal.valueOf(gLngUserId));
						lObjTrnPensionSixpayfpArrear.setCreatedPostId(BigDecimal.valueOf(gLngPostId));
						lObjTrnPensionSixpayfpArrear.setCreatedDate(gDateDBDate);
						if ("Y".equals(lStrSixthPayCalcFlag)) {
							lObjTrnPensionSixpayfpArrear.setRemarks("Configure from supplementary bill");
							lObjTrnPensionSixpayfpArrear.setConfigFromSuppl('Y');
						}
						else
						{
							lObjTrnPensionSixpayfpArrear.setConfigFromSuppl('N');
						}
						lLstCashSixPayArrear.add(lObjTrnPensionSixpayfpArrear);
					}
				}
			}
		} catch (Exception e) {
			gLogger.error("Error in generateCashArrearDtlsVo method is :" + e, e);
		}
		return lLstCashSixPayArrear;
	}
}
