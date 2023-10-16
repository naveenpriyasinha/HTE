package com.tcs.sgv.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.dao.budget.BudHdDAOImpl;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstBPNCodeVO;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstSbHdVO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.GrantDtlDAO;
import com.tcs.sgv.common.dao.GrantDtlDAOImpl;
import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvaGrantOrderDetail;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAO;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;

/**
 * BudgetHdServiceImpl 
 * This class is used for methods for budget head structure
 * i.e., to get Head Structure, to get Grant Amount, to validate Head Structure,
 * to get Scheme No. frequently used. It contains method for inserting remarks,
 * billDetails, budgetHead, ReceiptDetails, movement, bill-challan mapping. It
 * also contains method for getting intimation, department List, Lookup Values,
 * nextSeqNum, Bill Control No.
 * 
 * Date of Creation : 7th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * ===================== 
 * Hiral 23-Oct-2007 For making changes for code formating
 */
public class BudgetHdServiceImpl extends ServiceImpl implements BudgetHdService {

	/**
	 * This method returns all demands as ajax data in map as resultValue
	 * 
	 * @param objectArgs
	 * @return
	 */
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	public ResultObject getDmnds(Map objectArgs) {
		logger.info("inside get demands");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();
			String langId = request.getParameter("langId");
			String deptId = request.getParameter("deptId");
			String locId = request.getParameter("locId");
			if (locId != null) {
				deptId = new LocationDAOImpl(CmnLocationMst.class,
						sessionFactory).getDeptCodeByLocId(locId, langId);
				logger.info("DEPT ID -----------------: " + deptId);
			}
			ArrayList lArrDmdVO = new BudHdDAOImpl(sessionFactory)
					.getAllDemandByDept(deptId, langId, gObjRsrcBndle
							.getString("CMN.Loc1"), "Active");
			String xmlData = new AjaxXmlBuilder().addItems(lArrDmdVO,
					"demandCode", "demandCode").toString();
			objectArgs.put("ajaxKey", xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getDmnds : " + e, e);

		}
		return objRes;
	}

	/**
	 * This method returns all major head(as per criteria) as ajax data in map
	 * as resultValue
	 * 
	 * @param objectArgs
	 * @return
	 */
	public ResultObject getMajorHead(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();
			String deptId = request.getParameter("deptId");
			String demandCode = request.getParameter("demandCode");
			String bpnCode = request.getParameter("bpnCode");
			String type = request.getParameter("type");
			String langId = request.getParameter("langId");
			String locId = request.getParameter("locId");
			if (locId != null) {
				deptId = new LocationDAOImpl(CmnLocationMst.class,
						sessionFactory).getDeptCodeByLocId(locId, langId);
				logger.info("DEPT ID -----------------: " + deptId);
			}

			logger.info("deptId : " + deptId);
			logger.info("demandCode : " + demandCode);
			logger.info("langId : " + langId);

			ArrayList lArrDmdVO = new BudHdDAOImpl(sessionFactory)
					.getAllMjrHds(deptId, demandCode, "E", langId,
							gObjRsrcBndle.getString("CMN.Loc1"), "Active");
			// mjrHdCode vo
			logger.info("SIZE : " + lArrDmdVO.size());
			String xmlData = new AjaxXmlBuilder().addItems(lArrDmdVO,
					"mjrHdCode", "mjrHdCode").toString();

			logger.info("XML DATA : " + xmlData);
			objectArgs.put("ajaxKey", xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getMajorHead : " + e, e);
		}
		return objRes;
	}

	/**
	 * This method returns all sub major head(as per criteria) as ajax data in
	 * map as resultValue
	 * 
	 * @param objectArgs
	 * @return
	 */
	public ResultObject getSubMajorHead(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();

			String deptId = request.getParameter("deptId");
			String demandCode = request.getParameter("demandCode");
			String mjrHeadCode = request.getParameter("mjrHeadCode");
			String hdType = "E";
			String langId = request.getParameter("langId");
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String status = "Active";
			String deptLocId = request.getParameter("locId");
			if (deptLocId != null) {
				deptId = new LocationDAOImpl(CmnLocationMst.class,
						sessionFactory).getDeptCodeByLocId(deptLocId, langId);
				logger.info("DEPT ID -----------------: " + deptId);
			}

			ArrayList lArrSbMjrHdVO = new ArrayList();
			BudHdDAOImpl budHdDAO = new BudHdDAOImpl(sessionFactory);

			ArrayList lArrDeptVO = budHdDAO
					.getAllBPNCode(deptId, langId, locId);
			if (lArrDeptVO != null) {
				Iterator it = lArrDeptVO.iterator();
				while (it.hasNext()) {
					BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) it
							.next();
					lArrSbMjrHdVO.addAll(budHdDAO.getSbMjrHds(bpnCodeVO
							.getBPNCode(), demandCode, mjrHeadCode, hdType,
							langId, locId, status));
				}
			}

			String xmlData = new AjaxXmlBuilder().addItems(lArrSbMjrHdVO,
					"sbMjrHdCode", "sbMjrHdCode").toString();
			objectArgs.put("ajaxKey", xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getSubMajorHead : " + e, e);
		}
		return objRes;
	}

	/**
	 * This method returns all minor head(as per criteria) as ajax data in map
	 * as resultValue
	 * 
	 * @param objectArgs
	 * @return
	 */
	public ResultObject getMinorHead(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();

			String deptId = request.getParameter("deptId");
			String langId = request.getParameter("langId");
			String demandCode = request.getParameter("demandCode");
			String mjrHdCode = request.getParameter("mjrHeadCode");
			String sbMjrHdCode = request.getParameter("subMjrHeadCode");
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String sts = "Active";
			String deptLocId = request.getParameter("locId");
			if (deptLocId != null) {
				deptId = new LocationDAOImpl(CmnLocationMst.class,
						sessionFactory).getDeptCodeByLocId(deptLocId, langId);
				logger.info("DEPT ID -----------------: " + deptId);
			}

			ArrayList lArrSbMjrHdVO = new ArrayList();
			BudHdDAOImpl budHdDAO = new BudHdDAOImpl(sessionFactory);

			ArrayList lArrDeptVO = budHdDAO
					.getAllBPNCode(deptId, langId, locId);
			logger.info("" + lArrDeptVO.size());
			if (lArrDeptVO != null) {
				Iterator it = lArrDeptVO.iterator();
				while (it.hasNext()) {
					BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) it
							.next();
					lArrSbMjrHdVO.addAll(budHdDAO.getMnrHds(bpnCodeVO
							.getBPNCode(), demandCode, mjrHdCode, sbMjrHdCode,
							null, langId, locId, sts));
				}
			}

			String xmlData = new AjaxXmlBuilder().addItems(lArrSbMjrHdVO,
					"mnrHdCode", "mnrHdCode").toString();
			objectArgs.put("ajaxKey", xmlData);
			logger.info("xmlData\n" + xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getMinorHead : " + e, e);
		}
		return objRes;
	}

	/**
	 * This method returns all sub head(as per criteria) as ajax data in map as
	 * resultValue
	 * 
	 * @param objectArgs
	 * @return
	 */
	public ResultObject getSubHead(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();

			String deptId = request.getParameter("deptId");
			String langId = request.getParameter("langId");
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String demandCode = request.getParameter("demandCode");
			String mjrHdCode = request.getParameter("mjrHeadCode");
			String sbMjrHdCode = request.getParameter("subMjrHeadCode");
			String MnrHdCode = request.getParameter("mnrHeadCode");
			String sts = "Active";
			String deptLocId = request.getParameter("locId");
			if (deptLocId != null) {
				deptId = new LocationDAOImpl(CmnLocationMst.class,
						sessionFactory).getDeptCodeByLocId(deptLocId, langId);
				logger.info("DEPT ID -----------------: " + deptId);
			}

			ArrayList lArrSbMjrHdVO = new ArrayList();
			BudHdDAOImpl budHdDAO = new BudHdDAOImpl(sessionFactory);

			ArrayList lArrDeptVO = budHdDAO
					.getAllBPNCode(deptId, langId, locId);
			if (lArrDeptVO != null) {
				Iterator it = lArrDeptVO.iterator();
				while (it.hasNext()) {
					BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) it
							.next();
					lArrSbMjrHdVO.addAll(budHdDAO.getSbHds(bpnCodeVO
							.getBPNCode(), demandCode, mjrHdCode, sbMjrHdCode,
							MnrHdCode, langId, locId, sts));
				}
			}

			String xmlData = new AjaxXmlBuilder().addItems(lArrSbMjrHdVO,
					"sbHdCode", "sbHdCode").toString();
			objectArgs.put("ajaxKey", xmlData);
			logger.info("xmlData\n" + xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getSubHead : " + e, e);
		}
		return objRes;
	}

	/**
	 * This method returns head(as per criteria) as ajax data in map as
	 * resultValue
	 * 
	 * @param objectArgs
	 * @return
	 */
	public ResultObject getDetailHead(Map objectArgs) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();

			String deptId = request.getParameter("deptId");
			String langId = request.getParameter("langId");
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String demandCode = request.getParameter("demandCode");
			String mjrHdCode = request.getParameter("mjrHeadCode");
			String sbMjrHdCode = request.getParameter("subMjrHeadCode");
			String mnrHdCode = request.getParameter("mnrHeadCode");
			String subHdCode = request.getParameter("subHeadCode");
			String sts = "Active";
			String deptLocId = request.getParameter("locId");
			if (deptLocId != null) {
				deptId = new LocationDAOImpl(CmnLocationMst.class,
						sessionFactory).getDeptCodeByLocId(deptLocId, langId);
				logger.info("DEPT ID -----------------: " + deptId);
			}

			ArrayList lArrSbMjrHdVO = new ArrayList();
			BudHdDAOImpl budHdDAO = new BudHdDAOImpl(sessionFactory);

			ArrayList lArrDeptVO = budHdDAO
					.getAllBPNCode(deptId, langId, locId);
			if (lArrDeptVO != null) {
				Iterator it = lArrDeptVO.iterator();
				while (it.hasNext()) {
					BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) it
							.next();
					logger.info("bpnCode VO :  " + bpnCodeVO.getBPNCode());
					lArrSbMjrHdVO.addAll(budHdDAO.getDtlHds(bpnCodeVO
							.getBPNCode(), demandCode, mjrHdCode, sbMjrHdCode,
							mnrHdCode, subHdCode, langId, locId));

				}
			}

			String xmlData = new AjaxXmlBuilder().addItems(lArrSbMjrHdVO, "id",
					"id").toString();
			logger.info("xmlDATA : " + xmlData);
			objectArgs.put("ajaxKey", xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getDetailHead : " + e, e);
		}
		return objRes;
	}

	/**
	 * This method returns head(as per criteria) as ajax data in map as
	 * resultValue
	 * 
	 * @param objectArgs
	 * @return
	 */
	public ResultObject getDetailHeadByDmnd(Map objectArgs) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();

			String deptId = request.getParameter("deptId");
			String langId = request.getParameter("langId");
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String demandCode = request.getParameter("demandCode");
			String mjrHdCode = request.getParameter("mjrHeadCode");
			String sbMjrHdCode = request.getParameter("subMjrHeadCode");
			String mnrHdCode = request.getParameter("mnrHeadCode");
			String subHdCode = request.getParameter("subHeadCode");
			String sts = "Active";
			String deptLocId = request.getParameter("locId");
			if (deptLocId != null) {
				deptId = new LocationDAOImpl(CmnLocationMst.class,
						sessionFactory).getDeptCodeByLocId(deptLocId, langId);
				logger.info("DEPT ID -----------------: " + deptId);
			}

			ArrayList lArrSbMjrHdVO = new ArrayList();
			BudHdDAOImpl budHdDAO = new BudHdDAOImpl(sessionFactory);

			ArrayList lArrDeptVO = budHdDAO.getAllBPNByDemand(demandCode,
					langId, locId);
			if (lArrDeptVO != null) {
				Iterator it = lArrDeptVO.iterator();
				while (it.hasNext()) {
					BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) it
							.next();
					logger.info("bpnCode VO :  " + bpnCodeVO.getBPNCode());
					lArrSbMjrHdVO.addAll(budHdDAO.getDtlHds(bpnCodeVO
							.getBPNCode(), demandCode, mjrHdCode, sbMjrHdCode,
							mnrHdCode, subHdCode, langId, locId));

				}
			}

			String xmlData = new AjaxXmlBuilder().addItems(lArrSbMjrHdVO, "id",
					"id").toString();
			logger.info("xmlDATA : " + xmlData);
			objectArgs.put("ajaxKey", xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getDetailHeadByDmnd : " + e, e);
		}
		return objRes;
	}

	public boolean validateHeads(Map objectArgs) {
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		logger.info("Value of Request in validate Heads : " + request);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		Long lLngLangId = SessionHelper.getLangId(objectArgs);

		String lStrDDOCode = request.getParameter("DDOCode") != null ? request
				.getParameter("DDOCode") : null;
		String lStrDemandCode = request.getParameter("cmbDemand") != null ? request
				.getParameter("cmbDemand")
				: null;
		String lStrBudMjrHd = request.getParameter("cmbMajorHead") != null ? request
				.getParameter("cmbMajorHead")
				: null;
		String lStrBudSubMjrHd = request.getParameter("cmbSubMajorHead") != null ? request
				.getParameter("cmbSubMajorHead")
				: null;
		String lStrBudMinHd = request.getParameter("cmbMinorHead") != null ? request
				.getParameter("cmbMinorHead")
				: null;
		String lStrBudSubHd = request.getParameter("cmbSubHead") != null ? request
				.getParameter("cmbSubHead")
				: null;
		logger.info("Value of subHead from Request :  "
				+ request.getParameter("cmbSubHead"));

		logger.info("Value of DDO code : " + lStrDDOCode);
		logger.info("Value of Demand Code : " + lStrDemandCode);
		logger.info("Value of  Major Head : " + lStrBudMjrHd);
		logger.info("Value of  Sub Major Head : " + lStrBudSubMjrHd);
		logger.info("Value of  Minor Head : " + lStrBudMinHd);
		logger.info("Value of  Sub Head : " + lStrBudSubHd);

		objectArgs.put("DemandCode", lStrDemandCode);
		objectArgs.put("MajorHead", lStrBudMjrHd);
		objectArgs.put("SubMajorHead", lStrBudSubMjrHd);
		objectArgs.put("MinorHead", lStrBudMinHd);
		objectArgs.put("SubHead", lStrBudSubHd);
		objectArgs.put("LangId", lLngLangId.toString());
		boolean lHeadStatus = false;
		try {
			BillCommonDAO lObjBillDAO = new BillCommonDAOImpl(serv
					.getSessionFactory());
			SgvcFinYearMst lFinYrVO = lObjBillDAO.getFinYrInfo(Calendar
					.getInstance().getTime(), lLngLangId);
			BudgetHdDtlsServiceImpl lObjBudHdDtlsSrvc = new BudgetHdDtlsServiceImpl();
			lHeadStatus = lObjBudHdDtlsSrvc.validateBudgetHeads(serv, lFinYrVO
					.getFinYearId(), lStrDemandCode, lStrBudMjrHd,
					lStrBudSubMjrHd, lStrBudMinHd, lStrBudSubHd, "00",
					lLngLangId, "");
			logger.info("Value of Head Status(in boolean) : " + lHeadStatus);
		} catch (Exception e) {
			logger.error("Error in validateHeads : " + e, e);
		}
		return lHeadStatus;
	}

	/**
	 * Method to get Grant Amount.
	 * 
	 * @param :
	 *            Map - objectArgs
	 * 
	 * @return : ResultObject - objRes
	 */
	public ResultObject getGrantAmount(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			logger
					.info("inside get Grant  Amount-------------------------------------");
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");

			String xmlData = "";
			String lStrSchemeCode = "";
			Long lLngLangId = SessionHelper.getLangId(objectArgs);
			BigDecimal lBdGrantAmt = null;
			BigDecimal lBdTotalGrossAmt = null;
			BigDecimal lBdCalcGrantAmt = null;

			String lStrPLNPL = request.getParameter("cmbPlan") != null ? request
					.getParameter("cmbPlan")
					: null;
			String lStrDDOCode = request.getParameter("DDOCode") != null ? request
					.getParameter("DDOCode")
					: null;
			String lStrDemandCode = request.getParameter("cmbDemand") != null ? request
					.getParameter("cmbDemand")
					: null;
			String lStrBudMjrHd = request.getParameter("cmbMajorHead") != null ? request
					.getParameter("cmbMajorHead")
					: null;
			String lStrBudSubMjrHd = request.getParameter("cmbSubMajorHead") != null ? request
					.getParameter("cmbSubMajorHead")
					: null;
			String lStrBudMinHd = request.getParameter("cmbMinorHead") != null ? request
					.getParameter("cmbMinorHead")
					: null;
			String lStrBudSubHd = request.getParameter("cmbSubHead") != null ? request
					.getParameter("cmbSubHead")
					: null;
			logger.info("Value of subHead from Request :  "
					+ request.getParameter("cmbSubHead"));

			SgvcFinYearMst lFinYrVO = null;
			BillCommonDAO lObjBillDAO = new BillCommonDAOImpl(serv
					.getSessionFactory());
			lFinYrVO = lObjBillDAO.getFinYrInfo(Calendar.getInstance()
					.getTime(), lLngLangId);
			GrantDtlDAO lGrantDAO = (GrantDtlDAO) new GrantDtlDAOImpl(serv
					.getSessionFactory());
			BptmCommonServicesDAOImpl lObjBptmCmnSrvcDao = new BptmCommonServicesDAOImpl(
					SgvaGrantOrderDetail.class, serv.getSessionFactory());

			boolean lHeadStatus = validateHeads(objectArgs);
			if (lHeadStatus) {
				if (lStrBudSubHd != null && lStrBudSubHd.length() > 0) {
					Long lLngFinYrId = lFinYrVO.getFinYearId();
					lBdGrantAmt = lGrantDAO.getGrantAmtForDDO(lLngFinYrId,
							BptmCommonServiceImpl.getShortBudType(lStrPLNPL),
							lStrDDOCode, lStrDemandCode, lStrBudMjrHd,
							lStrBudSubMjrHd, lStrBudMinHd, lStrBudSubHd);
					logger.info("Value of Grant Amount from : " + lBdGrantAmt);
				}
			}

			logger.info("Value of Grant Amount in getGrantAmount : "
					+ lBdGrantAmt);
			if (lBdGrantAmt != null) {
				objectArgs.put("demandCode", lStrDemandCode);
				objectArgs.put("majorHead", lStrBudMjrHd);
				objectArgs.put("subMajorHead", lStrBudSubMjrHd);
				objectArgs.put("minorHead", lStrBudMinHd);
				objectArgs.put("subHead", lStrBudSubHd);
				objectArgs.put("budgetType", lStrPLNPL);
				objectArgs.put("ddoCode", lStrDDOCode);

				lBdTotalGrossAmt = lObjBptmCmnSrvcDao
						.getTotalGrossAmount(objectArgs);
				logger
						.info("Value of Total Gross Amount : "
								+ lBdTotalGrossAmt);

				lBdCalcGrantAmt = lBdGrantAmt.subtract(lBdTotalGrossAmt);
				logger.info("Value of Calculated Grant Amount  : "
						+ lBdCalcGrantAmt);
			} else {
				lBdCalcGrantAmt = new BigDecimal(0);
			}
			lStrSchemeCode = getSchemeCodeBySubHead(objectArgs);
			if (lStrSchemeCode.length() > 0 && !lStrSchemeCode.equals("-1"))
				xmlData = new AjaxXmlBuilder().addItem("id_txtGrantAmt",
						String.valueOf(lBdCalcGrantAmt)).addItem(
						"id_txtSchemeCode", lStrSchemeCode).addItem(
						"id_headStatus", String.valueOf(lHeadStatus))
						.toString();
			else
				/*xmlData = new AjaxXmlBuilder().addItem("id_txtGrantAmt",
						String.valueOf(lBdCalcGrantAmt)).addItem(
						"id_txtSchemeCode", null).addItem("id_headStatus",
						String.valueOf(lHeadStatus)).toString();*/
			logger.info("Value of XML data in getGrantAmount : " + xmlData);
			objectArgs.put("ajaxKey", xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getGrantAmount : " + e, e);
		}
		return objRes;
	}

	/**
	 * This method returns sachiavalya department by location Id as ajax data in
	 * map as resultValue
	 * 
	 * @param objectArgs
	 * @return ResultObject
	 */
	public ResultObject getDeptByLocId(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");

			String locId = null;
			try {
				locId = new String(request.getParameter("locId"));
			} catch (Exception ex) {
			}
			SessionFactory sessionFactory = serv.getSessionFactory();
			String deptCode = new LocationDAOImpl(CmnLocationMst.class,
					sessionFactory).getDeptCodeByLocId(locId, SessionHelper
					.getLocale(request));
			String data = new AjaxXmlBuilder().addItem("deptId", deptCode)
					.toString();
			logger.info("deptID " + data);
			objectArgs.put("ajaxKey", data);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getDeptByLocId : " + e, e);
		}
		return objRes;
	}

	/**
	 * @Description : Method to get All major heads based on demand Code.
	 * @Input : Map - objectArgs
	 * @Output : ResultObject - objRes
	 * @Author : 203818
	 */
	public ResultObject getMajorHeadByDemand(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");

			SessionFactory sessionFactory = serv.getSessionFactory();
			String deptId = request.getParameter("deptId");
			String demandCode = request.getParameter("demandCode");
			String bpnCode = request.getParameter("bpnCode");
			String type = request.getParameter("type");
			String langId = SessionHelper.getLocale(request);
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			logger.info("deptId : " + deptId);
			logger.info("demandCode : " + demandCode);
			logger.info("langId : " + langId);
			logger.info("Value of Location id : " + locId);
			BudHdDAOImpl lObjBudHdDAOImpl = new BudHdDAOImpl(sessionFactory);
			ArrayList lArrBPNNo = lObjBudHdDAOImpl.getAllBPNByDemand(
					demandCode, langId, locId);
			String BPNCode = null;

			if (lArrBPNNo != null && lArrBPNNo.size() > 0) {
				logger.info("Value of BPN Code in Major Head :: "
						+ lArrBPNNo.get(0));
				BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) lArrBPNNo
						.get(0);
				BPNCode = (String) bpnCodeVO.getBPNCode();
				logger.info("Value of String BPN Code : " + BPNCode);
				ArrayList lArrMjrHdVO = lObjBudHdDAOImpl.getMjrHdByDmd(
						demandCode, BPNCode, "%", langId, locId, "Active");
				logger.info("SIZE of MajorHead By Deamnd: "
						+ lArrMjrHdVO.size());
				String xmlData = new AjaxXmlBuilder().addItems(lArrMjrHdVO,
						"mjrHdCode", "mjrHdCode").toString();
				logger.info("XML DATA : " + xmlData);
				objectArgs.put("ajaxKey", xmlData);
			}
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getMajorHeadByDemand : " + e, e);
		}
		return objRes;
	}

	/**
	 * @Description : Method to get All Sub Major Heads based on demand Code and
	 *              Major Head.
	 * @Input : Map - objectArgs
	 * @Output : ResultObject - objRes
	 * @Author : 203818
	 */
	public ResultObject getSubMajorHeadByDemand(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();
			BudHdDAOImpl BudHdDAOImpl = new BudHdDAOImpl(sessionFactory);

			String deptId = request.getParameter("deptId");
			String demandCode = request.getParameter("demandCode");
			String mjrHeadCode = request.getParameter("mjrHeadCode");
			String hdType = "E";
			String langId = SessionHelper.getLocale(request);
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String status = "Active";
			String deptLocId = request.getParameter("locId");
			BudHdDAOImpl lObjBudHdDAOImpl = new BudHdDAOImpl(sessionFactory);
			ArrayList lArrBPNNo = lObjBudHdDAOImpl.getAllBPNByDemand(
					demandCode, langId, locId);
			String BPNCode = null;
			if (lArrBPNNo != null && lArrBPNNo.size() > 0) {
				logger.info("Value of BPN Code in Sub Major Head:: "
						+ lArrBPNNo.get(0));
				BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) lArrBPNNo
						.get(0);
				BPNCode = (String) bpnCodeVO.getBPNCode();
				ArrayList lArrMjrHdVO = lObjBudHdDAOImpl.getSbMjrHds(BPNCode,
						demandCode, mjrHeadCode, "E", langId, locId, status);
				logger.info("Value of SubMajorHeadByDeamnd :: " + lArrMjrHdVO);
				String xmlData = new AjaxXmlBuilder().addItems(lArrMjrHdVO,
						"sbMjrHdCode", "sbMjrHdCode").toString();
				objectArgs.put("ajaxKey", xmlData);
			}
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getSubMajorHeadByDemand : " + e, e);
		}
		return objRes;
	}

	/**
	 * @Description : Method to get All Minor Heads based on demand Code, major
	 *              head and Sub major head.
	 * @Input : Map - objectArgs
	 * @Output : ResultObject - objRes
	 * @Author : 203818
	 */
	public ResultObject getMinorHeadByDemand(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();
			BudHdDAOImpl BudHdDAOImpl = new BudHdDAOImpl(sessionFactory);

			String deptId = request.getParameter("deptId");
			String demandCode = request.getParameter("demandCode");
			String mjrHdCode = request.getParameter("mjrHeadCode");
			String sbMjrHdCode = request.getParameter("subMjrHeadCode");
			String hdType = null;
			String langId = SessionHelper.getLocale(request);
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String sts = "Active";
			String deptLocId = request.getParameter("locId");
			BudHdDAOImpl lObjBudHdDAOImpl = new BudHdDAOImpl(sessionFactory);
			ArrayList lArrBPNNo = lObjBudHdDAOImpl.getAllBPNByDemand(
					demandCode, langId, locId);
			String BPNCode = null;
			if (lArrBPNNo != null && lArrBPNNo.size() > 0) {
				logger.info("Value of BPN Code in Sub Major Head:: "
						+ lArrBPNNo.get(0));
				BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) lArrBPNNo
						.get(0);
				BPNCode = (String) bpnCodeVO.getBPNCode();
				ArrayList lArrMnrHdVO = lObjBudHdDAOImpl.getMnrHds(BPNCode,
						demandCode, mjrHdCode, sbMjrHdCode, "E", langId, locId,
						sts);
				logger.info("Value of MinorHeadByDeamnd :: " + lArrMnrHdVO);
				String xmlData = new AjaxXmlBuilder().addItems(lArrMnrHdVO,
						"mnrHdCode", "mnrHdCode").toString();
				objectArgs.put("ajaxKey", xmlData);
			}
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getMinorHeadByDemand : " + e, e);
		}
		return objRes;
	}

	/**
	 * @Description : Method to get All Sub Heads based on demand Code, major
	 *              head, sub major head and minor head.
	 * @Input : Map - objectArgs
	 * @Output : ResultObject - objRes
	 * @Author : 203818
	 */
	public ResultObject getSubHeadByDemand(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			SessionFactory sessionFactory = serv.getSessionFactory();
			BudHdDAOImpl BudHdDAOImpl = new BudHdDAOImpl(sessionFactory);

			String deptId = request.getParameter("deptId");
			String langId = SessionHelper.getLocale(request);
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String demandCode = request.getParameter("demandCode");
			String mjrHdCode = request.getParameter("mjrHeadCode");
			String sbMjrHdCode = request.getParameter("subMjrHeadCode");
			String MnrHdCode = request.getParameter("mnrHeadCode");
			String sts = "Active";
			String deptLocId = request.getParameter("locId");
			BudHdDAOImpl lObjBudHdDAOImpl = new BudHdDAOImpl(sessionFactory);
			ArrayList lArrBPNNo = lObjBudHdDAOImpl.getAllBPNByDemand(
					demandCode, langId, locId);
			String BPNCode = null;
			if (lArrBPNNo != null && lArrBPNNo.size() > 0) {
				logger.info("Value of BPN Code in Sub Major Head:: "
						+ lArrBPNNo.get(0));
				BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) lArrBPNNo
						.get(0);
				BPNCode = (String) bpnCodeVO.getBPNCode();
				ArrayList lArrSubHdVO = lObjBudHdDAOImpl.getSbHds(BPNCode,
						demandCode, mjrHdCode, sbMjrHdCode, MnrHdCode, langId,
						locId, sts);
				logger.info("Value of MinorHeadByDeamnd :: " + lArrSubHdVO);
				String xmlData = new AjaxXmlBuilder().addItems(lArrSubHdVO,
						"sbHdCode", "sbHdCode").toString();
				objectArgs.put("ajaxKey", xmlData);
				logger.info("xmlData\n" + xmlData);
			}

			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getSubHeadByDemand : " + e, e);
		}
		return objRes;
	}

	public String getSchemeCodeBySubHead(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		String lStrSchemeCode = "";
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			BptmCommonServicesDAO lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());

			SessionFactory sessionFactory = serv.getSessionFactory();
			Long langId = SessionHelper.getLangId(objectArgs);
			String locale = gObjRsrcBndle.getString("CMN.LangId");
			String locId = gObjRsrcBndle.getString("CMN.Loc1");
			String demandCode = request.getParameter("cmbDemand");
			String mjrHdCode = request.getParameter("cmbMajorHead");
			String sbMjrHdCode = request.getParameter("cmbSubMajorHead");
			String MnrHdCode = request.getParameter("cmbMinorHead");
			String SubHdCode = request.getParameter("cmbSubHead");
			String lStrBudType = request.getParameter("cmbPlan");
			String sts = "Active";
			BudHdDAOImpl lObjBudHdDAOImpl = new BudHdDAOImpl(sessionFactory);
			ArrayList lArrBPNNo = lObjBudHdDAOImpl.getAllBPNByDemand(
					demandCode, locale, locId);
			String BPNCode = null;
			String lStrPlNP = "";

			if (lStrBudType != null && !lStrBudType.equals("")) {
				lStrPlNP = lObjCmnSrvcDAOImpl.getLookUpText((lStrBudType),
						langId);
			}

			if (lArrBPNNo != null && lArrBPNNo.size() > 0) {
				logger.info("Value of BPN Code in Scheme Code:: "
						+ lArrBPNNo.get(0));
				BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) lArrBPNNo
						.get(0);
				BPNCode = (String) bpnCodeVO.getBPNCode();
				String lStrStateNp = gObjRsrcBndle
						.getString("CMN.StateNonPlan");
				String lStrFinOther = gObjRsrcBndle
						.getString("CMN.FinOtherAgency");

				logger.info("lStrStateNp : " + lStrStateNp);
				logger.info("lStrFinOther : " + lStrFinOther);
				logger.info("lStrPlNP : " + lStrPlNP);

				if (lStrPlNP.equalsIgnoreCase(lStrStateNp)
						|| lStrPlNP.equalsIgnoreCase(lStrFinOther)) {
					logger.info("Inside If");
					lStrSchemeCode = "000000";
				} else {
					logger.info("Inside Else");
					ArrayList lArrScheme = lObjBudHdDAOImpl.getSchemeCode(
							BPNCode, demandCode, mjrHdCode, sbMjrHdCode,
							MnrHdCode, SubHdCode, locale, locId, sts);
					logger.info("Value of SchemeCodes :: " + lArrScheme);
					if (lArrScheme != null && lArrScheme.size() > 0) {
						lStrSchemeCode = ((BudExpEstSbHdVO) lArrScheme.get(0))
								.getPlanningCode();
					}
				}
				if (lStrSchemeCode == null || lStrSchemeCode.length() <= 0) {
					lStrSchemeCode = "-1";
				}
				logger.info("lStrSchemeCode : " + lStrSchemeCode);
			}
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getSchemeCodeBySubHead : " + e, e);
		}
		return lStrSchemeCode;
	}

	/**
	 * This method returns scheme no on basis of demand and other parameters.
	 * 
	 * @param objectArgs
	 * @return ResultObject
	 */
	public ResultObject getSchemeNoByDmnd(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("Inside scheme No...........");
		try {
			String schemeNo = getSchemeCodeBySubHead(objectArgs);
			if (schemeNo == null)
				schemeNo = "";
			String xmlData = new AjaxXmlBuilder().addItem("txtSchemeNo",
					schemeNo).toString();
			logger.info("Scheme No. " + xmlData);
			objectArgs.put("ajaxKey", xmlData);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error in getSchemeNoByDmnd : " + e, e);
		}
		return objRes;
	}
}