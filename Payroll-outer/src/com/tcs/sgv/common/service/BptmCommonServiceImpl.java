/**
 * 
 */
package com.tcs.sgv.common.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.audit.dao.AuditorObjectionDAOImpl;
import com.tcs.sgv.billproc.common.dao.RemarksDAOImpl;
import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.billproc.counter.dao.BdgtHeadDtlsDAOImpl;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.billproc.counter.dao.TrnRcptDtlsDAOImpl;
import com.tcs.sgv.common.dao.BillMovementDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.BudgetHdDtlsDAOImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.LocationDAO;
import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.RltBillObjection;
import com.tcs.sgv.common.valueobject.RltLocationDepartment;
import com.tcs.sgv.common.valueobject.SgvaBudbpnMapping;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAO;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.RltBillChallanDAOImpl;
import com.tcs.sgv.onlinebillprep.valueobject.RltBillChallan;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

/**
 * BptmCommonServiceImpl 
 * This class is used for common methods which are
 * frequently used. It contains method for inserting remarks, billDetails,
 * budgetHead, ReceiptDetails, movement, bill-challan mapping. It also contains
 * method for getting intimation, department List, Lookup Values, nextSeqNum,
 * Bill Control No.
 * 
 * Date of Creation : 7th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * ===================== 
 * Hiral 23-Oct-2007 For making changes for code formating
 */
public class BptmCommonServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());

	static HashMap hMapBillControl = new HashMap();

	static ResourceBundle bundleConst = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	/**
	 * Method to get all User wise Intimation
	 * 
	 * @param Map :
	 *            objectArgs
	 * @return ResultObject
	 */
	public static List getMyIntimation(Map objectArgs) {
		WorkFlowVO workFlowVO = new WorkFlowVO();

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Connection conn = serv.getSessionFactory().getCurrentSession()
				.connection();
		workFlowVO.setAppMap(objectArgs);
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");

		workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
		workFlowVO.setCrtPost(SessionHelper.getPostId(objectArgs).toString());
		workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
		workFlowVO.setConnection(conn);
		OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);

		List lObjListIntimation = new ArrayList();
		try {
			lObjListIntimation = orgUtil.getAllIntimationByPostId(SessionHelper
					.getPostId(objectArgs).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("lObjListIntimation Size ::::::::::::::"				+ lObjListIntimation.size());
		return lObjListIntimation;
	}

	/**
	 * Method to get all Departments of Sachivalay.
	 * 
	 * @param Map :
	 *            inputMap
	 * @return List
	 */
	public static List getDeptList(Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		String locale = SessionHelper.getLocale(request);

		LocationDAOImpl lObjLocDAOImpl = new LocationDAOImpl(
				CmnLocationMst.class, serv.getSessionFactory());
		ResourceBundle lObjRsrcBndle = ResourceBundle
				.getBundle("resources/billproc/BillprocConstants");

		List deptList = lObjLocDAOImpl.getDeptLocation(lObjRsrcBndle.getObject(
				"CMN.SachivalayDept").toString(), locale);
		return deptList;
	}

	/**
	 * This method will generate a sequence number in an auto-incremental mode.
	 * 
	 * @param String :
	 *            tableName
	 * @param Map :
	 *            inputMap
	 * @return Long
	 */

	public static List getLookupValues(String lStrLookupName, Long lLngLangId,
			Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		CmnLookupMstDAOImpl lObjCmnLookupDAOImpl = new CmnLookupMstDAOImpl(
				CmnLookupMst.class, serv.getSessionFactory());
		List lLstLookupValues = lObjCmnLookupDAOImpl
				.getAllChildrenByLookUpNameAndLang(lStrLookupName, lLngLangId);
		return lLstLookupValues;
	}

	/**
	 * Method to get all Bill Codes.
	 * 
	 * @param Map :
	 *            inputMap
	 * @return List
	 */
	public static List getBillCodeList(Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResourceBundle lObjRsrcBndle = ResourceBundle
				.getBundle("resources/billproc/BillprocConstants");
		CmnLookupMstDAOImpl lObjCmnLookupDAOImpl = new CmnLookupMstDAOImpl(
				CmnLookupMst.class, serv.getSessionFactory());

		List lLstBillCode = lObjCmnLookupDAOImpl.getAllChildren(lObjRsrcBndle
				.getObject("CMN.BillCode").toString());
		return lLstBillCode;
	}

	/**
	 * Method to get List of available Bill Categories(Regular, TC and Nil).
	 * 
	 * @param Map :
	 *            inputMap
	 * @return List
	 */
	public static List getBillCategoryList(Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResourceBundle lObjRsrcBndle = ResourceBundle
				.getBundle("resources/billproc/BillprocConstants");
		CmnLookupMstDAOImpl lObjCmnLookupDAOImpl = new CmnLookupMstDAOImpl(
				CmnLookupMst.class, serv.getSessionFactory());

		List lLstTCBill = lObjCmnLookupDAOImpl.getAllChildren(lObjRsrcBndle
				.getObject("CMN.TcBillType").toString());
		return lLstTCBill;
	}

	/**
	 * Method to get List of available Bill Categories(Regular, TC and Nil).
	 * 
	 * @param Map :
	 *            inputMap
	 * @return List
	 */
	public static String getExpType(Map inputMap, String lStrExpType) {
		//System.out.println("Value of lStrExpType : " + lStrExpType);
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResourceBundle lObjRsrcBndle = ResourceBundle
				.getBundle("resources/billproc/BillprocConstants");
		CmnLookupMstDAOImpl lObjCmnLookupDAOImpl = new CmnLookupMstDAOImpl(
				CmnLookupMst.class, serv.getSessionFactory());
		String lStrExpTypeId = null;
		List lLstTCBill = lObjCmnLookupDAOImpl
				.getAllChildrenByLookUpNameAndLang(lObjRsrcBndle.getObject(
						"CMN.Exp_type").toString(), SessionHelper
						.getLangId(inputMap));
		// List lLstTCBill =
		// lObjCmnLookupDAOImpl.getAllChildren(lObjRsrcBndle.getObject("CMN.Exp_type").toString());
		//System.out.println("Size of lLstTCBill : " + lLstTCBill.size());

		//System.out.println("lLstTCBill : " + lLstTCBill);
		for (int i = 0; i < lLstTCBill.size(); i++) {
			CmnLookupMst lObjLookup = (CmnLookupMst) lLstTCBill.get(i);
			//System.out.println("Value of LookupName : "					+ lObjLookup.getLookupName());
			if (lObjLookup.getLookupName().equalsIgnoreCase(lStrExpType)) {
				lStrExpTypeId = Long.toString(lObjLookup.getLookupId());
				//System.out.println("lStrExpTypeId : " + lStrExpTypeId);
			}
		}
		//System.out.println("Value of lLngExpTypeId to be returned : "				+ lStrExpTypeId);
		return lStrExpTypeId;
	}

	/**
	 * Method to get List of GO/NGOs.
	 * 
	 * @param Map :
	 *            inputMap
	 * @return List
	 */
	public static List getGoNgoList(Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResourceBundle lObjRsrcBndle = ResourceBundle
				.getBundle("resources/billproc/BillprocConstants");
		CmnLookupMstDAOImpl lObjCmnLookupDAOImpl = new CmnLookupMstDAOImpl(
				CmnLookupMst.class, serv.getSessionFactory());

		List lLstGoNgo = lObjCmnLookupDAOImpl.getAllChildren(lObjRsrcBndle
				.getObject("CMN.GoNgo").toString());
		return lLstGoNgo;
	}

	/**
	 * Method to get List of Budget Type(Plan/Non-Plan)
	 * 
	 * @param Map :
	 *            inputMap
	 * @return List
	 */
	public static List getBudgetTypeList(Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResourceBundle lObjRsrcBndle = ResourceBundle
				.getBundle("resources/billproc/BillprocConstants");
		CmnLookupMstDAOImpl lObjCmnLookupDAOImpl = new CmnLookupMstDAOImpl(
				CmnLookupMst.class, serv.getSessionFactory());

		List lLstBudType = lObjCmnLookupDAOImpl.getAllChildren(lObjRsrcBndle
				.getObject("CMN.BudgetType").toString());
		return lLstBudType;
	}

	/**
	 * Method to insert Remarks details in "trn_bill_remarks" (Also generates
	 * and sets Primary Key in sequence)
	 * 
	 * @param TrnBillRemarks :
	 *            lObjRemarksVO
	 * @param Map :
	 *            inputMap
	 * @return void
	 */
	public static void insertRemarks(TrnBillRemarks lObjRemarksVO, Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		String lStrRemarks = lObjRemarksVO.getRemarks();
		// if(lStrRemarks!=null && lStrRemarks.length()>0)
		{
			RemarksDAOImpl lObjRemarksDAOImpl = new RemarksDAOImpl(
					TrnBillRemarks.class, serv.getSessionFactory());
			Long lLngRemarksId = getNextSeqNum("trn_bill_remarks", inputMap);
			lObjRemarksVO.setRmrksId(lLngRemarksId);
			//lObjRemarksDAOImpl.create(lObjRemarksVO);
		}
	}

	/**
	 * Method to insert Bill Movement details in "trn_bill_mvmnt" (Also
	 * generates and sets Primary Key in sequence)
	 * 
	 * @param TrnBillMvmnt :
	 *            lObjBillMvmntVO
	 * @param Map :
	 *            inputMap
	 * @return void
	 */
	public static void insertMovement(TrnBillMvmnt lObjBillMvmntVO, Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		BillMovementDAOImpl mvmntDAO = new BillMovementDAOImpl(
				TrnBillMvmnt.class, serv.getSessionFactory());
		Long lLngBillMvmntId = getNextSeqNum("trn_bill_mvmnt", inputMap);
		lObjBillMvmntVO.setBillMvmtId(lLngBillMvmntId);
		//mvmntDAO.create(lObjBillMvmntVO);
	}

	/**
	 * Method to insert Receipt(Challan) Details in "trn_receipt_details" (Also
	 * generates and sets Primary Key in sequence)
	 * 
	 * @param TrnReceiptDetails :
	 *            lObjRcptDtlsVO
	 * @param Map :
	 *            inputMap
	 * @return void
	 */
	public static void insertReceiptDetails(TrnReceiptDetails lObjRcptDtlsVO,
			Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		TrnRcptDtlsDAOImpl lObjRcptDtlsDAO = new TrnRcptDtlsDAOImpl(
				TrnReceiptDetails.class, serv.getSessionFactory());
		Long lLngRcptDtls = getNextSeqNum("trn_receipt_details", inputMap);
		lObjRcptDtlsVO.setReceiptDetailId(lLngRcptDtls);
		//lObjRcptDtlsDAO.create(lObjRcptDtlsVO);
	}

	/**
	 * Method to insert Bill related Details in "trn_bill_register" (Also
	 * generates and sets Primary Key in sequence)
	 * 
	 * @param TrnBillRegister :
	 *            lObjTrnBillRegVO
	 * @param Map :
	 *            inputMap
	 * @return void
	 */
	public static void insertBillRegister(TrnBillRegister lObjTrnBillRegVO,
			Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		Long lLngBillNo = getNextSeqNum("trn_bill_register", inputMap);
		lObjTrnBillRegVO.setBillNo(lLngBillNo);
		//lObjPhyBillDAOImpl.create(lObjTrnBillRegVO);
	}

	/**
	 * Method to insert Budget Head related Details in "trn_bill_budhead_dtls"
	 * (Also generates and sets Primary Key in sequence)
	 * 
	 * @param TrnBillRegister :
	 *            lObjTrnBillRegVO
	 * @param Map :
	 *            inputMap
	 * @return void
	 */
	public static void insertBudHeadDtls(TrnBillBudheadDtls lObjBudHeadVO,
			Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		BdgtHeadDtlsDAOImpl lObjBdgtHeadDtlsDAOImpl = new BdgtHeadDtlsDAOImpl(
				TrnBillBudheadDtls.class, serv.getSessionFactory());
		Long lLngBudHeadId = getNextSeqNum("trn_bill_budhead_dtls", inputMap);
		lObjBudHeadVO.setBillBudId(lLngBudHeadId);
		//lObjBdgtHeadDtlsDAOImpl.create(lObjBudHeadVO);
	}

	/**
	 * Method to insert Bill-Challan relation Details in "RLT_BILL_CHALLAN"
	 * (Also generates and sets Primary Key in sequence)
	 * 
	 * @param TrnBillRegister :
	 *            lObjTrnBillRegVO
	 * @param Map :
	 *            inputMap
	 * @return void
	 */
	public static void insertRltBillChallan(RltBillChallan lObjRltBillChallan,
			Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		RltBillChallanDAOImpl lObjBillChallanDAOImpl = new RltBillChallanDAOImpl(
				RltBillChallan.class, serv.getSessionFactory());
		Long lLngBillChallanId = getNextSeqNum("rlt_bill_challan", inputMap);
		lObjRltBillChallan.setBillChallanId(lLngBillChallanId);
		//lObjBillChallanDAOImpl.create(lObjRltBillChallan);
	}

	/**
	 * This method will generate a sequence number in an auto-incremental mode.
	 * 
	 * @param String :
	 *            tableName
	 * @param Map :
	 *            inputMap
	 * @return Long
	 */
	public synchronized static Long getNextSeqNum(String lStrTableName,
			Map inputMap) {
		ResultObject lResObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try {
			if (lResObj != null) {
				//System.out.println("========= Came inside =====for table "						+ lStrTableName);
				Long lId = IDGenerateDelegate
						.getNextId(lStrTableName, inputMap);
				//System.out.println("============nextID " + lId);
				return (long) lId;
			}

		} catch (Exception e) {
			//System.out.println("Error in nextSeqNum " + e);
			e.printStackTrace();
		}
		return (long) -1;
	}

	public synchronized static String getBillControlNumber(Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		DDOInfoServiceImpl lObjDDOInfoSrvcImpl = new DDOInfoServiceImpl();
		String lStrDDOCode = (String) inputMap.get("DDOCode");
		String hMapData = null;
		String billCntrlNum = "";

		//System.out.println("================" + lStrDDOCode);
		String lStrDDONum = "";
		Long langId = SessionHelper.getLangId(inputMap);
		Long locId = SessionHelper.getLocationId(inputMap);
		Long dbId = SessionHelper.getDbId(inputMap);
		List orgDDOMstList = lObjDDOInfoSrvcImpl.getDDOInfo(lStrDDOCode,
				langId, dbId, serv);
		if (orgDDOMstList != null && orgDDOMstList.size() > 0) {
			OrgDdoMst orgDDOMst = (OrgDdoMst) orgDDOMstList.get(0);
			//lStrDDONum = orgDDOMst.getDdoNo();
		}

		BptmCommonServicesDAO bptmDAO = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		CmnLocationMstDaoImpl locDAO = new CmnLocationMstDaoImpl(
				CmnLocationMst.class, serv.getSessionFactory());
		CmnLocationMst locVO = locDAO.getLocationVO(locId);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date(System.currentTimeMillis()));
		int returnData = 0;
		//System.out.println("size is :------------------------------------"				+ hMapBillControl.size() + " asdfasdf asdfasdfas "				+ hMapBillControl.containsKey(lStrDDOCode));
		if (hMapBillControl.containsKey(lStrDDOCode)) {
			returnData = Integer.parseInt(hMapBillControl.get(lStrDDOCode)
					.toString());
			returnData++;
			NumberFormat f = new DecimalFormat("#00");
			billCntrlNum = lStrDDONum + "/" + locVO.getLocShortName() + "/"
					+ f.format(gc.get(gc.YEAR)).substring(2)
					+ f.format(gc.get(gc.MONTH) + 1) + "/" + returnData;
			hMapBillControl.put(lStrDDOCode, returnData);
		} else {
			String BillCtrlNo = bptmDAO.getBillCtrlNo(inputMap);
			if (BillCtrlNo == null || BillCtrlNo.equals("")) {
				NumberFormat f = new DecimalFormat("#00");
				billCntrlNum = lStrDDONum + "/" + locVO.getLocShortName() + "/"
						+ f.format(gc.get(gc.YEAR)).substring(2)
						+ f.format(gc.get(gc.MONTH) + 1) + "/" + 1;

				//System.out.println(" Bill Control Nm " + billCntrlNum);
			} else {
				billCntrlNum = BillCtrlNo;
			}
			String arrBillControlNo[] = billCntrlNum.split("/");
			int billNumber = Integer
					.parseInt(arrBillControlNo[arrBillControlNo.length - 1]);
			hMapBillControl.put(lStrDDOCode, billNumber);
		}
		//System.out.println("return billCntrlNum is " + billCntrlNum);
		return billCntrlNum;
	}

	public static String getBillCntrlNumSeq(Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		BptmCommonServicesDAO bptmDAO = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		String BillCtrlNo = bptmDAO.getBillCtrlNoForOBPM(inputMap);
		return BillCtrlNo;
	}

	public static List generateBillControlNum(Map inputMap) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");

		ArrayList billCntrlList = new ArrayList();
		BptmCommonServicesDAO bptmDAO = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		CmnLocationMstDaoImpl locDAO = new CmnLocationMstDaoImpl(
				CmnLocationMst.class, serv.getSessionFactory());
		DDOInfoServiceImpl lObjDDOInfoSrvcImpl = new DDOInfoServiceImpl();
		PhyBillDAOImpl phyDAO = new PhyBillDAOImpl(TrnBillRegister.class, serv
				.getSessionFactory());
		String lStrDDONum = "";
		Long langId = SessionHelper.getLangId(inputMap);
		Long locId = SessionHelper.getLocationId(inputMap);
		Long dbId = SessionHelper.getDbId(inputMap);

		String[] billNumList = request.getParameterValues("hidBillNo");

		try {

			if (billNumList != null && billNumList.length > 0) {
				for (int i = 0; i < billNumList.length; i++) {
					TrnBillRegister trnBillRegister = phyDAO.read(Long
							.parseLong(billNumList[i]));
					List orgDDOMstList = lObjDDOInfoSrvcImpl.getDDOInfo(
							trnBillRegister.getDdoCode(), langId, dbId, serv);
					if (orgDDOMstList != null && orgDDOMstList.size() > 0) {
						OrgDdoMst orgDDOMst = (OrgDdoMst) orgDDOMstList.get(0);
						//lStrDDONum = orgDDOMst.getDdoNo();
					}

					CmnLocationMst locVO = locDAO
							.getLocationVOByLocationCodeAndLangId(
									trnBillRegister.getTsryOfficeCode(), langId);
					System.out
							.println("-------------- Got Locatino ------------ "
									+ locVO.getLocName());
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(new Date(System.currentTimeMillis()));
					String BillCtrlNo = bptmDAO.getBillCtrlNo(inputMap);
					String billCntrlNum = "";
					if (BillCtrlNo == null || BillCtrlNo.equals("")) {
						NumberFormat f = new DecimalFormat("#00");
						billCntrlNum = lStrDDONum + "/"
								+ locVO.getLocShortName() + "/"
								+ f.format(gc.get(gc.YEAR)).substring(2)
								+ f.format(gc.get(gc.MONTH) + 1) + "/" + 1;

						//System.out.println(" Bill Control Nm " + billCntrlNum);
					} else {
						billCntrlNum = BillCtrlNo;
					}

					trnBillRegister.setBillCntrlNo(billCntrlNum);

					phyDAO.update(trnBillRegister);

					billCntrlList.add(billCntrlNum);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return billCntrlList;
	}

	/**
	 * 
	 * @author 602399
	 * 
	 */
	public ResultObject getTotalGrossAmount(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			BptmCommonServicesDAO lBptmObj = new BptmCommonServicesDAOImpl(
					BptmCommonServiceImpl.class, serv.getSessionFactory());

			BigDecimal lBDDDOExp = lBptmObj.getTotalGrossAmount(inputMap);

			inputMap.put("TotalDDOExp", lBDDDOExp);

			objRes.setResultValue(inputMap);
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error("Error in getTotalGrossAmount and Error is : " + e, e);
		}

		return objRes;
	}

	public static String getLookupIdFromName(String lookupName, Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrLookupId = "";
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			Long langId = SessionHelper.getLangId(inputMap);
			BptmCommonServicesDAO lBptmObj = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			lStrLookupId = lBptmObj.getLookupIdFromName(lookupName, langId);
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			//System.out.println("Look up Id error" + e);
			e.printStackTrace();
		}
		return lStrLookupId;
	}

	public static boolean isPhyBill(Long billNo, Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		boolean lPhyFlag = false;
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			BptmCommonServicesDAO lBptmObj = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			lPhyFlag = lBptmObj.isPhyBill(billNo);
		} catch (Exception e) {
			//System.out.println("physical bill or not " + e);
			e.printStackTrace();
		}
		return lPhyFlag;
	}

	/**
	 * Method to get Department ID from Demand No.
	 * 
	 * @param String :
	 *            lStrDemandCode
	 * @param Long :
	 *            lLngLangId
	 * @param Long :
	 *            lLngDbId
	 * @param ServiceLocator :
	 *            serv
	 * 
	 * @return Long
	 */
	public static String getDeptByDemand(String lStrDemandCode,
			Long lLngLangId, Long lLngDbId, ServiceLocator serv) {
		// String lLngDeptCode = null;
		String lStrDeptCode = "";
		List lLstLocDept = null;
		BudgetHdDtlsDAOImpl lObjBudHdDtls = null;
		SgvaBudbpnMapping lObjBudBpnMap = null;
		BillCommonDAO lObjBillDAO = new BillCommonDAOImpl(serv
				.getSessionFactory());
		LocationDAO lObjLocDao = new LocationDAOImpl(CmnLocationMst.class, serv
				.getSessionFactory());
		SgvcFinYearMst lFinYrVO = lObjBillDAO.getFinYrInfo(Calendar
				.getInstance().getTime(), lLngLangId);

		if (lFinYrVO != null)
			lObjBudHdDtls = new BudgetHdDtlsDAOImpl(lFinYrVO.getFinYearId(),
					serv.getSessionFactory());

		if (lObjBudHdDtls != null)
			lObjBudBpnMap = lObjBudHdDtls.getBPNInfoFrmDmd(lStrDemandCode,
					lLngLangId);

		if (lObjBudBpnMap != null) {
			//System.out.println("Value of SgvaBudbpnMapping : "					+ lObjBudBpnMap.toString());			//System.out.println("Value of lObjBudBpnMap.getDeptId() : "					+ lObjBudBpnMap.getDeptId());
			lStrDeptCode = lObjBudBpnMap.getDeptId();
			System.out
					.println("Value of Department Code from getBPNInfoFrmDmd : "
							+ lStrDeptCode);
			lLstLocDept = lObjLocDao.getLocByDept(lStrDeptCode, lLngLangId,
					lLngDbId);
			//System.out.println("Value of LocationDepartmetn : " + lLstLocDept);

			if (lLstLocDept != null) {
				RltLocationDepartment lObjRltLocDept = (RltLocationDepartment) lLstLocDept
						.get(0);
				lStrDeptCode = lObjRltLocDept.getLocCode();
			}
		}
		//System.out.println("Value of Department Code : " + lStrDeptCode);
		return lStrDeptCode;
	}

	public static String getShortBudType(String lStrPLNPL) {
		String lStrGrantBudType = null;
		if (lStrPLNPL.equals("State_Nonplan")
				|| lStrPLNPL.equals("Other_Agencies")
				|| lStrPLNPL.equals("PlanBud_80")) {
			lStrGrantBudType = "NP";
		} else if (lStrPLNPL.equals("State_Plan")
				|| lStrPLNPL.equals("PlanBud_15")
				|| lStrPLNPL.equals("StatePlan_5")) {
			lStrGrantBudType = "PL";
		} else if (lStrPLNPL.equals("Central_Plan")
				|| lStrPLNPL.equals("Fully_Centrally")
				|| lStrPLNPL.equals("Partly_Centrally")) {
			lStrGrantBudType = "CSS";
		}
		return lStrGrantBudType;
	}

	public static void raiseObjection(String lStrObjCode, Long lLngBillNo,
			Map inputMap) {
		//System.out.println("Inside raise Objection,lStrObjCode : "				+ lStrObjCode);
		//System.out.println("Inside raise Objection,lLngBillNo : " + lLngBillNo);

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		RltBillObjection rltBillObj = new RltBillObjection();
		rltBillObj.setBillObjcId(BptmCommonServiceImpl.getNextSeqNum(
				"rlt_bill_objection", inputMap));
		rltBillObj.setBillNo(lLngBillNo);
		rltBillObj.setObjectionCode(lStrObjCode);
		rltBillObj.setCreatedDate(new Date(System.currentTimeMillis()));
		rltBillObj.setCreatedPostId(SessionHelper.getPostId(inputMap));
		rltBillObj.setCreatedUserId(SessionHelper.getUserId(request));
		rltBillObj.setDbId(SessionHelper.getDbId(inputMap));
		rltBillObj.setLineItemNo((long) 1);
		rltBillObj.setLocationCode(SessionHelper.getLocationCode(inputMap));
		rltBillObj.setObjFlag("PO");
		rltBillObj.setPostId(SessionHelper.getPostId(inputMap));
		rltBillObj.setUserId(SessionHelper.getUserId(request));
		AuditorObjectionDAOImpl auditObj = new AuditorObjectionDAOImpl(
				RltBillObjection.class, serv.getSessionFactory());
		//auditObj.create(rltBillObj);
	}
}
